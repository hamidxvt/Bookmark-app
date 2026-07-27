<?php
namespace App\Models;
use Illuminate\Database\Eloquent\Model;
class DailyContent extends Model {
    protected $fillable = ['quote', 'tip', 'day_start_message', 'half_day_message', 'day_end_message'];
}
