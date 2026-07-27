<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Models\SampleRequest;
use Illuminate\Http\Request;

class AdminSampleController extends Controller
{
    public function requests(Request $request)
    {
        $query = SampleRequest::with(['officer', 'items.product']);
        if ($request->status) $query->where('status', $request->status);
        if ($request->user()->isCityHead()) {
            $query->whereHas('officer', fn($q) => $q->where('city_id', $request->user()->city_id));
        }
        return response()->json(['requests' => $query->latest()->get()->map(fn($r) => $this->format($r))]);
    }

    public function approve(Request $request, int $id)
    {
        $sr = SampleRequest::findOrFail($id);
        $sr->update(['status' => 'approved', 'approved_by' => $request->user()->id, 'approved_at' => now()]);
        // Deduct from officer's annual sample limit
        $sr->officer->increment('annual_sample_used', $sr->total_pkr);
        return response()->json(['message' => 'Sample request approved']);
    }

    public function reject(int $id)
    {
        SampleRequest::findOrFail($id)->update(['status' => 'rejected']);
        return response()->json(['message' => 'Sample request rejected']);
    }

    public function ledger(Request $request)
    {
        $query = SampleRequest::with(['officer', 'items.product']);
        if ($request->user()->isCityHead()) {
            $query->whereHas('officer', fn($q) => $q->where('city_id', $request->user()->city_id));
        }
        return response()->json(['requests' => $query->latest()->get()->map(fn($r) => $this->format($r))]);
    }

    private function format(SampleRequest $r): array
    {
        return [
            'id' => $r->id,
            'officer_name' => $r->officer?->name,
            'officer_id' => $r->officer_id,
            'total_pkr' => $r->total_pkr,
            'status' => $r->status,
            'created_at' => $r->created_at->toIso8601String(),
            'reminder_10_sent_at' => $r->reminder_10_sent_at?->toIso8601String(),
            'reminder_20_sent_at' => $r->reminder_20_sent_at?->toIso8601String(),
            'items' => $r->items->map(fn($i) => ['product_name' => $i->product?->name, 'quantity' => $i->quantity, 'value' => $i->total_value]),
        ];
    }
}
