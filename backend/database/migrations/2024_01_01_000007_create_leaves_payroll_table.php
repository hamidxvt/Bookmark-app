<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    public function up(): void
    {
        Schema::create('leave_requests', function (Blueprint $table) {
            $table->id();
            $table->foreignId('officer_id')->constrained('users')->cascadeOnDelete();
            $table->date('date');
            $table->enum('type', ['sick', 'casual'])->default('casual');
            $table->enum('status', ['pending', 'approved', 'rejected', 'auto'])->default('pending');
            $table->text('reason')->nullable();
            $table->unsignedBigInteger('reviewed_by')->nullable();
            $table->timestamps();
        });

        Schema::create('payroll_ledgers', function (Blueprint $table) {
            $table->id();
            $table->foreignId('officer_id')->constrained('users')->cascadeOnDelete();
            $table->date('month');
            $table->decimal('basic_salary', 12, 2)->default(0);
            $table->decimal('security_deposit_held', 12, 2)->default(0);
            $table->decimal('performance_earned', 12, 2)->default(0);
            $table->decimal('deductions', 12, 2)->default(0);
            $table->json('deduction_reasons')->nullable();
            $table->timestamps();

            $table->unique(['officer_id', 'month']);
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('payroll_ledgers');
        Schema::dropIfExists('leave_requests');
    }
};
