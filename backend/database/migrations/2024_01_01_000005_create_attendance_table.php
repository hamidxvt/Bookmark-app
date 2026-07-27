<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    public function up(): void
    {
        Schema::create('attendance', function (Blueprint $table) {
            $table->id();
            $table->foreignId('officer_id')->constrained('users')->cascadeOnDelete();
            $table->date('date');
            $table->timestamp('day_start_at')->nullable();
            $table->decimal('day_start_lat', 10, 7)->nullable();
            $table->decimal('day_start_lng', 10, 7)->nullable();
            $table->timestamp('day_end_at')->nullable();
            $table->decimal('day_end_lat', 10, 7)->nullable();
            $table->decimal('day_end_lng', 10, 7)->nullable();
            $table->text('cannot_work_reason')->nullable();
            $table->enum('status', ['present', 'absent', 'cannot_work', 'on_leave'])->default('absent');
            $table->timestamps();

            $table->unique(['officer_id', 'date']);
        });
    }

    public function down(): void { Schema::dropIfExists('attendance'); }
};
