<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Models\Visit;
use Illuminate\Http\Request;

class AdminVisitController extends Controller
{
    public function index(Request $request)
    {
        $query = Visit::with(['officer', 'institution', 'outcome']);

        if ($request->user()->isCityHead()) {
            $query->whereHas('officer', fn($q) => $q->where('city_id', $request->user()->city_id));
        }
        if ($request->date) $query->whereDate('scheduled_date', $request->date);
        if ($request->status) $query->where('status', $request->status);
        if ($request->search) {
            $query->where(function ($q) use ($request) {
                $q->whereHas('officer', fn($qq) => $qq->where('name', 'like', "%{$request->search}%"))
                  ->orWhereHas('institution', fn($qq) => $qq->where('name', 'like', "%{$request->search}%"));
            });
        }

        $visits = $query->orderBy('scheduled_date', 'desc')->orderBy('route_order')->paginate(50);

        return response()->json(['visits' => $visits->items()->map(fn($v) => $this->format($v)), 'total' => $visits->total()]);
    }

    public function today(Request $request)
    {
        $query = Visit::with(['officer', 'institution'])->whereDate('scheduled_date', today());
        if ($request->user()->isCityHead()) {
            $query->whereHas('officer', fn($q) => $q->where('city_id', $request->user()->city_id));
        }
        return response()->json(['visits' => $query->get()->map(fn($v) => $this->format($v))]);
    }

    public function show(int $id)
    {
        $visit = Visit::with(['officer', 'institution', 'outcome'])->findOrFail($id);
        return response()->json(['visit' => $this->format($visit)]);
    }

    public function store(Request $request)
    {
        $request->validate([
            'officer_id' => 'required|exists:users,id',
            'institution_id' => 'required|exists:institutions,id',
            'scheduled_date' => 'required|date',
            'coordinator_notes' => 'nullable|string',
        ]);

        $visit = Visit::create([
            'officer_id' => $request->officer_id,
            'institution_id' => $request->institution_id,
            'scheduled_date' => $request->scheduled_date,
            'status' => 'pending',
            'source' => 'coordinator',
            'route_order' => 1,
            'attempt_count' => 1,
            'coordinator_notes' => $request->coordinator_notes,
        ]);

        return response()->json(['visit' => $this->format($visit->load('officer', 'institution'))], 201);
    }

    private function format(Visit $v): array
    {
        return [
            'id' => $v->id,
            'officer_id' => $v->officer_id,
            'officer_name' => $v->officer?->name,
            'institution_id' => $v->institution_id,
            'institution_name' => $v->institution?->name,
            'institution_address' => $v->institution?->address,
            'institution_type' => $v->institution?->type,
            'scheduled_date' => $v->scheduled_date?->format('Y-m-d'),
            'status' => $v->status,
            'source' => $v->source,
            'priority' => $v->institution?->priority,
            'route_order' => $v->route_order,
            'attempt_count' => $v->attempt_count,
            'contact_name' => $v->outcome?->contact_name,
            'designation' => $v->outcome?->designation,
            'contact_phone' => $v->outcome?->contact_phone,
            'visit_type' => $v->outcome?->visit_type,
            'notes' => $v->outcome?->notes,
            'followup_date' => $v->outcome?->followup_date?->format('Y-m-d'),
            'travel_time_mins' => $v->travel_time_mins,
            'onsite_time_mins' => $v->onsite_time_mins,
            'start_lat' => $v->start_lat,
            'start_lng' => $v->start_lng,
            'missed_reason' => $v->missed_reason,
            'missed_photo' => $v->missed_photo ? asset("storage/{$v->missed_photo}") : null,
            'missed_status' => $v->missed_status,
            'coordinator_notes' => $v->coordinator_notes,
        ];
    }
}
