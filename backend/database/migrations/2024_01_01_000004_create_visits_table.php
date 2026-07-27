<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    public function up(): void
    {
        Schema::create('visits', function (Blueprint $table) {
            $table->id();
            $table->foreignId('officer_id')->constrained('users')->cascadeOnDelete();
            $table->foreignId('institution_id')->constrained()->cascadeOnDelete();
            $table->date('scheduled_date');
            $table->enum('status', ['pending', 'ongoing', 'completed', 'missed', 'skipped'])->default('pending');
            $table->enum('source', ['auto', 'coordinator', 'adhoc', 'followup', 'carryforward'])->default('auto');
            $table->integer('route_order')->default(1);
            $table->integer('attempt_count')->default(1);
            $table->text('coordinator_notes')->nullable();
            $table->timestamp('started_at')->nullable();
            $table->timestamp('completed_at')->nullable();
            $table->decimal('start_lat', 10, 7)->nullable();
            $table->decimal('start_lng', 10, 7)->nullable();
            $table->decimal('checkin_lat', 10, 7)->nullable();
            $table->decimal('checkin_lng', 10, 7)->nullable();
            $table->integer('travel_time_mins')->nullable();
            $table->integer('onsite_time_mins')->nullable();
            $table->string('checkin_photo')->nullable();
            $table->string('missed_photo')->nullable();
            $table->text('missed_reason')->nullable();
            $table->enum('missed_status', ['pending_review', 'approved', 'rejected'])->nullable();
            $table->unsignedBigInteger('missed_reviewed_by')->nullable();
            $table->text('missed_review_comment')->nullable();
            $table->timestamp('missed_reviewed_at')->nullable();
            $table->timestamps();

            $table->index(['officer_id', 'scheduled_date']);
            $table->index(['institution_id', 'scheduled_date']);
        });

        Schema::create('visit_outcomes', function (Blueprint $table) {
            $table->id();
            $table->foreignId('visit_id')->unique()->constrained()->cascadeOnDelete();
            $table->string('contact_name');
            $table->string('designation');
            $table->string('contact_phone');
            $table->string('visit_type');
            $table->text('notes')->nullable();
            $table->date('followup_date')->nullable();
            $table->timestamp('editable_until')->nullable();
            $table->timestamps();
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('visit_outcomes');
        Schema::dropIfExists('visits');
    }
};
