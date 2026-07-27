<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Models\Visit;
use Illuminate\Http\Request;

class MissedVisitController extends Controller
{
    public function pending(Request $request)
    {
        $query = Visit::with(['officer', 'institution'])
            ->where('status', 'missed')
            ->whereIn('missed_status', ['pending_review', 'approved', 'rejected']);

        // City head only sees their city
        if ($request->user()->isCityHead()) {
            $query->whereHas('officer', fn($q) => $q->where('city_id', $request->user()->city_id));
        }

        $visits = $query->latest()->get()->map(fn($v) => $this->format($v));
        return response()->json(['visits' => $visits]);
    }

    public function approve(Request $request, int $id)
    {
        $visit = $this->findVisit($request, $id);
        $visit->update([
            'missed_status' => 'approved',
            'missed_reviewed_by' => $request->user()->id,
            'missed_review_comment' => $request->comment,
            'missed_reviewed_at' => now(),
        ]);
        return response()->json(['message' => 'Approved — no penalty applied']);
    }

    public function reject(Request $request, int $id)
    {
        $visit = $this->findVisit($request, $id);
        $visit->update([
            'missed_status' => 'rejected',
            'missed_reviewed_by' => $request->user()->id,
            'missed_review_comment' => $request->comment,
            'missed_reviewed_at' => now(),
        ]);

        // Apply salary penalty
        $visit->applyPenalty();

        return response()->json(['message' => 'Rejected — daily performance allowance deducted']);
    }

    public function override(Request $request, int $id)
    {
        $request->validate(['decision' => 'required|in:approved,rejected']);

        $visit = Visit::findOrFail($id);
        $visit->update([
            'missed_status' => $request->decision,
            'missed_reviewed_by' => $request->user()->id,
            'missed_review_comment' => $request->comment ?? 'Admin override',
            'missed_reviewed_at' => now(),
        ]);

        if ($request->decision === 'rejected') $visit->applyPenalty();

        return response()->json(['message' => "Override applied: {$request->decision}"]);
    }

    private function findVisit(Request $request, int $id): Visit
    {
        $query = Visit::query();
        if ($request->user()->isCityHead()) {
            $query->whereHas('officer', fn($q) => $q->where('city_id', $request->user()->city_id));
        }
        return $query->findOrFail($id);
    }

    private function format(Visit $v): array
    {
        return [
            'id' => $v->id,
            'officer_name' => $v->officer?->name,
            'officer_id' => $v->officer_id,
            'institution_name' => $v->institution?->name,
            'institution_address' => $v->institution?->address,
            'scheduled_date' => $v->scheduled_date?->format('Y-m-d'),
            'attempt_count' => $v->attempt_count,
            'missed_reason' => $v->missed_reason,
            'missed_photo' => $v->missed_photo ? asset("storage/{$v->missed_photo}") : null,
            'missed_status' => $v->missed_status,
            'status' => $v->status,
        ];
    }
}
