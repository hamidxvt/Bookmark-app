<?php

namespace App\Console\Commands;

use App\Models\Area;
use App\Models\Institution;
use App\Models\User;
use App\Models\Visit;
use Carbon\Carbon;
use Illuminate\Console\Command;

/**
 * Nightly visit planning engine — runs at midnight.
 *
 * For each active officer:
 *  Step 1 — Coordinator-assigned visits (placed first)
 *  Step 2 — Carry-forward missed visits
 *  Step 3 — Pre-scheduled follow-ups
 *  Step 4 — Area master data fill to reach 7 total
 *  Step 5 — Route optimise (nearest-neighbor algorithm)
 */
class GenerateDailyVisitsCommand extends Command
{
    protected $signature = 'visits:generate {--date= : Target date (Y-m-d), defaults to tomorrow}';
    protected $description = 'Generate the daily 7-visit plan for all active sales officers';

    private const TARGET_VISITS = 7;
    private const HIGH_PRIORITY_SCHOOLS = 2;
    private const MEDIUM_PRIORITY_SCHOOLS = 2;
    private const BOOKSHOPS = 2;

    public function handle(): void
    {
        $targetDate = $this->option('date')
            ? Carbon::parse($this->option('date'))
            : now()->addDay()->startOfDay();

        $officers = User::where('role', 'officer')->where('is_active', true)->get();
        $this->info("Generating visits for {$targetDate->format('Y-m-d')} — {$officers->count()} officers");

        foreach ($officers as $officer) {
            $this->generateForOfficer($officer, $targetDate);
        }

        $this->info('Done.');
    }

    private function generateForOfficer(User $officer, Carbon $date): void
    {
        $visits = collect();

        // Step 1: Coordinator-assigned visits
        $coordinator = Visit::where('officer_id', $officer->id)
            ->whereDate('scheduled_date', $date)
            ->where('source', 'coordinator')
            ->where('status', 'pending')
            ->with('institution')
            ->get();
        $visits = $visits->merge($coordinator);

        // Step 2: Carry-forward missed visits (they already exist in the table)
        // The miss handler creates them; just skip if already in list

        // Step 3: Pre-scheduled follow-ups
        if ($visits->count() < self::TARGET_VISITS) {
            $followups = Visit::where('officer_id', $officer->id)
                ->whereDate('scheduled_date', $date)
                ->where('source', 'followup')
                ->where('status', 'pending')
                ->with('institution')
                ->get();
            $visits = $visits->merge($followups);
        }

        // Step 4: Fill from area master data
        $alreadyScheduledIds = $visits->pluck('institution_id')->toArray();
        $remaining = self::TARGET_VISITS - $visits->count();

        if ($remaining > 0 && $officer->area_id) {
            $filler = $this->pickFromAreaPool($officer, $date, $alreadyScheduledIds, $remaining);
            foreach ($filler as $institution) {
                $visit = Visit::create([
                    'officer_id' => $officer->id,
                    'institution_id' => $institution->id,
                    'scheduled_date' => $date,
                    'status' => 'pending',
                    'source' => 'auto',
                    'route_order' => 99, // will be updated after route optimisation
                    'attempt_count' => 1,
                ]);
                $visits->push($visit->load('institution'));
            }
        }

        // Step 5: Route optimise (nearest-neighbor from officer's home / last known position)
        $this->optimiseRoute($officer, $visits, $date);

        $this->info("  Officer {$officer->name}: {$visits->count()} visits scheduled");
    }

    private function pickFromAreaPool(User $officer, Carbon $date, array $excludeIds, int $needed): \Illuminate\Support\Collection
    {
        $picked = collect();

        // 2 High-priority schools
        if ($needed > 0) {
            $highSchools = Institution::where('area_id', $officer->area_id)
                ->where('type', 'school')->where('priority', 'high')
                ->whereNotIn('id', $excludeIds)
                ->orderByRaw('(SELECT MAX(scheduled_date) FROM visits WHERE institution_id = institutions.id AND status = "completed") ASC NULLS FIRST')
                ->limit(min(self::HIGH_PRIORITY_SCHOOLS, $needed))
                ->get();
            $picked = $picked->merge($highSchools);
            $excludeIds = array_merge($excludeIds, $highSchools->pluck('id')->toArray());
            $needed -= $highSchools->count();
        }

        // 2 Medium-priority schools
        if ($needed > 0) {
            $medSchools = Institution::where('area_id', $officer->area_id)
                ->where('type', 'school')->where('priority', 'medium')
                ->whereNotIn('id', $excludeIds)
                ->orderByRaw('(SELECT MAX(scheduled_date) FROM visits WHERE institution_id = institutions.id AND status = "completed") ASC NULLS FIRST')
                ->limit(min(self::MEDIUM_PRIORITY_SCHOOLS, $needed))
                ->get();
            $picked = $picked->merge($medSchools);
            $excludeIds = array_merge($excludeIds, $medSchools->pluck('id')->toArray());
            $needed -= $medSchools->count();
        }

        // 2 Bookshops
        if ($needed > 0) {
            $shops = Institution::where('area_id', $officer->area_id)
                ->where('type', 'bookshop')
                ->whereNotIn('id', $excludeIds)
                ->orderByRaw('(SELECT MAX(scheduled_date) FROM visits WHERE institution_id = institutions.id AND status = "completed") ASC NULLS FIRST')
                ->limit(min(self::BOOKSHOPS, $needed))
                ->get();
            $picked = $picked->merge($shops);
        }

        return $picked;
    }

    /**
     * Nearest-neighbor TSP approximation.
     * Starts from officer's last known location and greedily picks the closest unvisited institution.
     */
    private function optimiseRoute(User $officer, \Illuminate\Support\Collection $visits, Carbon $date): void
    {
        if ($visits->isEmpty()) return;

        $startLat = $officer->last_lat ?? 24.8607;
        $startLng = $officer->last_lng ?? 67.0011;

        $remaining = $visits->filter(fn($v) => $v->institution)->values();
        $ordered = collect();
        $currentLat = $startLat;
        $currentLng = $startLng;

        while ($remaining->isNotEmpty()) {
            $nearest = $remaining->sortBy(fn($v) => $this->haversine(
                $currentLat, $currentLng,
                $v->institution->lat ?? $startLat,
                $v->institution->lng ?? $startLng
            ))->first();

            $ordered->push($nearest);
            $currentLat = $nearest->institution->lat ?? $currentLat;
            $currentLng = $nearest->institution->lng ?? $currentLng;
            $remaining = $remaining->reject(fn($v) => $v->id === $nearest->id)->values();
        }

        // Update route_order
        foreach ($ordered as $index => $visit) {
            Visit::where('id', $visit->id)->update(['route_order' => $index + 1]);
        }
    }

    private function haversine(float $lat1, float $lng1, float $lat2, float $lng2): float
    {
        $dLat = deg2rad($lat2 - $lat1);
        $dLng = deg2rad($lng2 - $lng1);
        $a = sin($dLat / 2) ** 2 + cos(deg2rad($lat1)) * cos(deg2rad($lat2)) * sin($dLng / 2) ** 2;
        return 6371 * 2 * atan2(sqrt($a), sqrt(1 - $a));
    }
}
