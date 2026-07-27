<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    public function up(): void
    {
        Schema::create('users', function (Blueprint $table) {
            $table->id();
            $table->string('name');
            $table->string('phone')->unique();
            $table->string('email')->nullable()->unique();
            $table->string('password');
            $table->enum('role', ['officer', 'coordinator', 'city_head', 'admin'])->default('officer');
            $table->foreignId('city_id')->nullable()->constrained()->nullOnDelete();
            $table->foreignId('area_id')->nullable()->constrained()->nullOnDelete();
            $table->unsignedBigInteger('city_head_id')->nullable();
            $table->decimal('basic_salary', 10, 2)->nullable();
            $table->decimal('security_deposit', 10, 2)->nullable();
            $table->decimal('performance_daily', 10, 2)->default(3000);
            $table->decimal('annual_sample_limit', 12, 2)->nullable();
            $table->decimal('annual_sample_used', 12, 2)->default(0);
            $table->integer('leave_sick_balance')->default(10);
            $table->integer('leave_casual_balance')->default(18);
            $table->string('profile_image')->nullable();
            $table->decimal('last_lat', 10, 7)->nullable();
            $table->decimal('last_lng', 10, 7)->nullable();
            $table->timestamp('last_location_at')->nullable();
            $table->boolean('is_active')->default(true);
            $table->rememberToken();
            $table->timestamps();
        });
    }

    public function down(): void { Schema::dropIfExists('users'); }
};
