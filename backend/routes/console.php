<?php

use Illuminate\Support\Facades\Schedule;

// Generate next day's visit plans at midnight
Schedule::command('visits:generate')->dailyAt('00:00');

// Process missed attendance and auto-deduct leave at 11 PM
Schedule::command('attendance:process')->dailyAt('23:00');

// Check sample reminders every morning at 8 AM
Schedule::command('samples:reminders')->dailyAt('08:00');
