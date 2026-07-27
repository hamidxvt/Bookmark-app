<?php
namespace App\Models;
use Illuminate\Database\Eloquent\Model;
class Product extends Model {
    protected $fillable = ['name', 'grade', 'subject', 'series', 'price', 'image', 'is_active'];
    protected $casts = ['price' => 'decimal:2', 'is_active' => 'boolean'];
    public function sampleItems() { return $this->hasMany(SampleItem::class); }
}
