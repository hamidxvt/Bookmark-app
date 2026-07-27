<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Models\Area;
use App\Models\City;
use App\Models\Product;
use Illuminate\Http\Request;

class MasterDataController extends Controller
{
    public function cities() { return response()->json(['cities' => City::all()]); }
    public function areas(Request $request)
    {
        $query = Area::query();
        if ($request->city_id) $query->where('city_id', $request->city_id);
        return response()->json(['areas' => $query->get()]);
    }
    public function products() { return response()->json(['products' => Product::where('is_active', true)->get()]); }
}
