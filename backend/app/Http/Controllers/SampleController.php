<?php

namespace App\Http\Controllers;

use App\Models\Product;
use App\Models\SampleItem;
use App\Models\SampleRequest;
use Illuminate\Http\Request;

class SampleController extends Controller
{
    public function products()
    {
        $products = Product::where('is_active', true)->get();
        return response()->json(['products' => $products]);
    }

    public function request(Request $request)
    {
        $request->validate([
            'items' => 'required|array|min:1',
            'items.*.product_id' => 'required|exists:products,id',
            'items.*.quantity' => 'required|integer|min:1',
            'visit_id' => 'nullable|exists:visits,id',
        ]);

        $officer = $request->user();

        // Calculate total PKR
        $total = 0;
        $items = [];
        foreach ($request->items as $item) {
            $product = Product::find($item['product_id']);
            $lineValue = $product->price * $item['quantity'];
            $total += $lineValue;
            $items[] = ['product_id' => $item['product_id'], 'quantity' => $item['quantity'], 'unit_price' => $product->price, 'total_value' => $lineValue];
        }

        // Enforce annual sample limit
        if ($officer->remainingSampleBudget() < $total) {
            return response()->json([
                'message' => "Request of PKR {$total} exceeds your remaining annual sample limit of PKR {$officer->remainingSampleBudget()}",
            ], 422);
        }

        $sampleRequest = SampleRequest::create([
            'officer_id' => $officer->id,
            'visit_id' => $request->visit_id,
            'status' => 'pending',
            'total_pkr' => $total,
        ]);

        foreach ($items as $item) {
            $sampleRequest->items()->create($item);
        }

        return response()->json(['message' => 'Sample request submitted for approval']);
    }

    public function myRequests(Request $request)
    {
        $requests = SampleRequest::with('items.product')
            ->where('officer_id', $request->user()->id)
            ->latest()
            ->get()
            ->map(fn($r) => $this->formatRequest($r));

        return response()->json(['requests' => $requests]);
    }

    public function markRecovered(Request $request, int $id)
    {
        $sample = SampleRequest::where('officer_id', $request->user()->id)->findOrFail($id);
        $sample->update(['status' => 'recovered']);
        return response()->json(['message' => 'Marked as recovered']);
    }

    private function formatRequest(SampleRequest $r): array
    {
        return [
            'id' => $r->id,
            'status' => $r->status,
            'total_pkr' => $r->total_pkr,
            'created_at' => $r->created_at->toIso8601String(),
            'reminder_10_sent_at' => $r->reminder_10_sent_at?->toIso8601String(),
            'reminder_20_sent_at' => $r->reminder_20_sent_at?->toIso8601String(),
            'items' => $r->items->map(fn($i) => [
                'product_name' => $i->product?->name,
                'quantity' => $i->quantity,
                'value' => $i->total_value,
            ]),
        ];
    }
}
