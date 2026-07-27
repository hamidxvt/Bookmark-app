<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Models\Institution;
use App\Models\Visit;
use Illuminate\Http\Request;

class InstitutionController extends Controller
{
    public function index(Request $request)
    {
        $query = Institution::with('area.city');
        if ($request->type) $query->where('type', $request->type);
        if ($request->search) $query->where(function ($q) use ($request) {
            $q->where('name', 'like', "%{$request->search}%")
              ->orWhereHas('area', fn($qq) => $qq->where('name', 'like', "%{$request->search}%"));
        });

        $institutions = $query->get()->map(fn($i) => [
            'id' => $i->id,
            'name' => $i->name,
            'type' => $i->type,
            'priority' => $i->priority,
            'address' => $i->address,
            'area' => $i->area?->name,
            'city' => $i->area?->city?->name,
            'lat' => $i->lat,
            'lng' => $i->lng,
            'total_visits' => $i->visits()->count(),
            'last_visit_date' => $i->lastVisitDate(),
        ]);

        return response()->json(['institutions' => $institutions]);
    }

    public function show(int $id)
    {
        $institution = Institution::with('area.city')->findOrFail($id);
        return response()->json(['institution' => $institution]);
    }

    public function visitHistory(int $id)
    {
        $visits = Visit::with(['officer', 'outcome'])
            ->where('institution_id', $id)
            ->latest('scheduled_date')
            ->get()
            ->map(fn($v) => [
                'id' => $v->id,
                'officer_name' => $v->officer?->name,
                'scheduled_date' => $v->scheduled_date?->format('Y-m-d'),
                'status' => $v->status,
                'visit_type' => $v->outcome?->visit_type,
                'notes' => $v->outcome?->notes,
                'contact_name' => $v->outcome?->contact_name,
            ]);

        return response()->json(['visits' => $visits]);
    }
}
