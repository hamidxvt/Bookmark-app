<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    public function up(): void
    {
        Schema::create('products', function (Blueprint $table) {
            $table->id();
            $table->string('name');
            $table->string('grade')->nullable();
            $table->string('subject')->nullable();
            $table->string('series')->nullable();
            $table->decimal('price', 10, 2);
            $table->string('image')->nullable();
            $table->boolean('is_active')->default(true);
            $table->timestamps();
        });

        Schema::create('sample_requests', function (Blueprint $table) {
            $table->id();
            $table->foreignId('officer_id')->constrained('users')->cascadeOnDelete();
            $table->foreignId('visit_id')->nullable()->constrained()->nullOnDelete();
            $table->enum('status', ['pending', 'approved', 'rejected', 'recovered'])->default('pending');
            $table->decimal('total_pkr', 12, 2);
            $table->unsignedBigInteger('approved_by')->nullable();
            $table->timestamp('approved_at')->nullable();
            $table->timestamp('reminder_10_sent_at')->nullable();
            $table->timestamp('reminder_20_sent_at')->nullable();
            $table->boolean('deducted_from_payroll')->nullable();
            $table->timestamp('deducted_at')->nullable();
            $table->timestamps();
        });

        Schema::create('sample_items', function (Blueprint $table) {
            $table->id();
            $table->foreignId('sample_request_id')->constrained()->cascadeOnDelete();
            $table->foreignId('product_id')->constrained()->cascadeOnDelete();
            $table->integer('quantity');
            $table->decimal('unit_price', 10, 2);
            $table->decimal('total_value', 10, 2);
            $table->timestamps();
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('sample_items');
        Schema::dropIfExists('sample_requests');
        Schema::dropIfExists('products');
    }
};
