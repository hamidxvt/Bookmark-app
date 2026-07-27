<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class LocationController extends Controller
{
    public function update(Request $request)
    {
        $request->validate(['latitude' => 'required|numeric', 'longitude' => 'required|numeric']);

        $request->user()->update([
            'last_lat' => $request->latitude,
            'last_lng' => $request->longitude,
            'last_location_at' => now(),
        ]);

        return response()->json(['message' => 'Location updated']);
    }
}
