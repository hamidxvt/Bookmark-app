<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class SampleItem extends Model
{
    protected $fillable = ['sample_request_id', 'product_id', 'quantity', 'unit_price', 'total_value'];

    protected $casts = ['unit_price' => 'decimal:2', 'total_value' => 'decimal:2'];

    public function sampleRequest() { return $this->belongsTo(SampleRequest::class); }
    public function product() { return $this->belongsTo(Product::class); }
}
