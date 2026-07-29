import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';

import '../../../core/theme/app_theme.dart';
import '../data/samples_repository.dart';

class SamplesScreen extends ConsumerWidget {
  const SamplesScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final samplesAsync = ref.watch(samplesProvider);

    return Scaffold(
      backgroundColor: AppColors.background,
      appBar: AppBar(
        title: const Text('Sample Books'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios_new_rounded),
          onPressed: () => context.go('/dashboard'),
        ),
        actions: [
          FilledButton.tonalIcon(
            icon: const Icon(Icons.add_rounded, size: 18),
            label: const Text('Request'),
            onPressed: () => _showRequestSheet(context, ref),
          ),
          const SizedBox(width: 8),
        ],
      ),
      body: samplesAsync.when(
        loading: () => const Center(child: CircularProgressIndicator()),
        error: (e, _) => Center(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              const Icon(Icons.cloud_off_rounded,
                  size: 48, color: AppColors.textMuted),
              const SizedBox(height: 12),
              Text('Failed to load samples',
                  style: Theme.of(context).textTheme.bodyMedium),
              const SizedBox(height: 8),
              TextButton(
                onPressed: () => ref.invalidate(samplesProvider),
                child: const Text('Retry'),
              ),
            ],
          ),
        ),
        data: (data) => RefreshIndicator(
          onRefresh: () async => ref.invalidate(samplesProvider),
          child: ListView(
            padding: const EdgeInsets.all(AppSpacing.md),
            children: [
              // Budget card
              _BudgetCard(data: data)
                  .animate()
                  .slideY(begin: -0.1)
                  .fadeIn(duration: 500.ms),
              const SizedBox(height: 20),

              if (data.active.isEmpty && data.recovered.isEmpty) ...[
                Center(
                  child: Padding(
                    padding: const EdgeInsets.symmetric(vertical: 40),
                    child: Column(
                      children: [
                        const Icon(Icons.library_books_outlined,
                            size: 56, color: AppColors.textMuted),
                        const SizedBox(height: 12),
                        Text('No samples yet',
                            style: Theme.of(context)
                                .textTheme
                                .bodyMedium
                                ?.copyWith(color: AppColors.textMuted)),
                        const SizedBox(height: 8),
                        const Text(
                          'Request sample books to distribute during visits.',
                          textAlign: TextAlign.center,
                        ),
                      ],
                    ),
                  ),
                ),
              ] else ...[
                if (data.active.isNotEmpty) ...[
                  Text('Active Samples (${data.active.length})',
                      style: Theme.of(context).textTheme.titleMedium),
                  const SizedBox(height: 12),
                  ...data.active.asMap().entries.map((e) => Padding(
                        padding: const EdgeInsets.only(bottom: 10),
                        child: _SampleTile(
                          sample: e.value,
                          onRecover: () async {
                            try {
                              await ref
                                  .read(samplesRepositoryProvider)
                                  .markRecovered(e.value.id);
                              ref.invalidate(samplesProvider);
                              if (context.mounted) {
                                ScaffoldMessenger.of(context).showSnackBar(
                                  const SnackBar(
                                    content: Text('Sample marked as recovered'),
                                    backgroundColor: AppColors.success,
                                  ),
                                );
                              }
                            } catch (err) {
                              if (context.mounted) {
                                ScaffoldMessenger.of(context).showSnackBar(
                                  SnackBar(content: Text('Failed: $err')),
                                );
                              }
                            }
                          },
                        ).animate(delay: (e.key * 80).ms).slideX(begin: 0.1).fadeIn(),
                      )),
                ],

                if (data.recovered.isNotEmpty) ...[
                  const SizedBox(height: 20),
                  Text('Recovered (${data.recovered.length})',
                      style: Theme.of(context).textTheme.titleMedium),
                  const SizedBox(height: 12),
                  ...data.recovered.asMap().entries.map((e) => Padding(
                        padding: const EdgeInsets.only(bottom: 10),
                        child: _SampleTile(
                          sample: e.value,
                          onRecover: null,
                        ).animate(delay: (e.key * 80).ms).slideX(begin: 0.1).fadeIn(),
                      )),
                ],
              ],
              const SizedBox(height: 40),
            ],
          ),
        ),
      ),
    );
  }

  void _showRequestSheet(BuildContext context, WidgetRef ref) {
    showModalBottomSheet(
      context: context,
      isScrollControlled: true,
      backgroundColor: Colors.transparent,
      builder: (ctx) => _RequestSheet(
        onSubmit: (productId, qty, notes) async {
          await ref.read(samplesRepositoryProvider).requestSamples(
                productId: productId,
                quantity: qty,
                notes: notes,
              );
          ref.invalidate(samplesProvider);
          if (ctx.mounted) Navigator.pop(ctx);
          if (context.mounted) {
            ScaffoldMessenger.of(context).showSnackBar(
              const SnackBar(
                content: Text('Sample request submitted for approval'),
                backgroundColor: AppColors.success,
              ),
            );
          }
        },
      ),
    );
  }
}

class _BudgetCard extends StatelessWidget {
  final SamplesData data;
  const _BudgetCard({required this.data});

  @override
  Widget build(BuildContext context) {
    final pct = data.budgetPercent;
    final color = pct > 0.8
        ? AppColors.error
        : pct > 0.6
            ? AppColors.warning
            : AppColors.success;

    return GradientBox(
      colors: AppColors.primaryGradient,
      borderRadius: BorderRadius.circular(AppRadius.xl),
      child: Padding(
        padding: const EdgeInsets.all(20),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text('Annual Sample Budget',
                style: TextStyle(color: Colors.white70, fontSize: 12)),
            const SizedBox(height: 4),
            Text(
              'PKR ${data.budgetRemaining.toStringAsFixed(0)}',
              style: const TextStyle(
                  color: Colors.white,
                  fontSize: 28,
                  fontWeight: FontWeight.w700),
            ),
            const SizedBox(height: 2),
            Text(
              'remaining of PKR ${data.budgetTotal.toStringAsFixed(0)}',
              style: const TextStyle(color: Colors.white60, fontSize: 12),
            ),
            const SizedBox(height: 16),
            ClipRRect(
              borderRadius: BorderRadius.circular(4),
              child: LinearProgressIndicator(
                value: pct,
                backgroundColor: Colors.white24,
                valueColor: AlwaysStoppedAnimation<Color>(color),
                minHeight: 8,
              ),
            ),
            const SizedBox(height: 8),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text('Used: PKR ${data.budgetUsed.toStringAsFixed(0)}',
                    style:
                        const TextStyle(color: Colors.white70, fontSize: 11)),
                Text('${(pct * 100).toStringAsFixed(0)}% used',
                    style:
                        const TextStyle(color: Colors.white70, fontSize: 11)),
              ],
            ),
          ],
        ),
      ),
    );
  }
}

class _SampleTile extends StatelessWidget {
  final SampleRequest sample;
  final VoidCallback? onRecover;
  const _SampleTile({required this.sample, this.onRecover});

  @override
  Widget build(BuildContext context) {
    final isWarning = sample.daysOut >= 10 && !sample.isRecovered;
    final isDanger = sample.daysOut >= 20 && !sample.isRecovered;

    return Container(
      padding: const EdgeInsets.all(14),
      decoration: BoxDecoration(
        color: AppColors.surface,
        borderRadius: BorderRadius.circular(AppRadius.lg),
        border: Border.all(
          color: isDanger
              ? AppColors.error.withOpacity(0.4)
              : isWarning
                  ? AppColors.warning.withOpacity(0.4)
                  : AppColors.outline,
        ),
      ),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Container(
            width: 44,
            height: 44,
            decoration: BoxDecoration(
              color: sample.isRecovered
                  ? AppColors.success.withOpacity(0.1)
                  : AppColors.accent.withOpacity(0.1),
              borderRadius: BorderRadius.circular(AppRadius.md),
            ),
            child: Icon(
              sample.isRecovered
                  ? Icons.check_circle_outline_rounded
                  : Icons.menu_book_rounded,
              color: sample.isRecovered ? AppColors.success : AppColors.accent,
              size: 22,
            ),
          ),
          const SizedBox(width: 12),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(sample.productName,
                    style: Theme.of(context)
                        .textTheme
                        .bodyMedium
                        ?.copyWith(fontWeight: FontWeight.w600)),
                if (sample.institutionName != null)
                  Text(sample.institutionName!,
                      style: Theme.of(context).textTheme.bodySmall),
                const SizedBox(height: 4),
                Row(
                  children: [
                    _Chip(
                        label: 'PKR ${sample.totalValue.toStringAsFixed(0)}',
                        color: AppColors.primary),
                    const SizedBox(width: 6),
                    _Chip(
                        label: '${sample.daysOut}d out',
                        color: isDanger
                            ? AppColors.error
                            : isWarning
                                ? AppColors.warning
                                : AppColors.textMuted),
                    const SizedBox(width: 6),
                    _Chip(
                        label: 'Qty: ${sample.quantity}',
                        color: AppColors.textMuted),
                  ],
                ),
              ],
            ),
          ),
          if (onRecover != null)
            TextButton(
              onPressed: onRecover,
              child: const Text('Recover',
                  style: TextStyle(fontSize: 12, color: AppColors.success)),
            ),
          if (sample.isRecovered)
            const Icon(Icons.verified_rounded,
                color: AppColors.success, size: 20),
        ],
      ),
    );
  }
}

class _Chip extends StatelessWidget {
  final String label;
  final Color color;
  const _Chip({required this.label, required this.color});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 2),
      decoration: BoxDecoration(
        color: color.withOpacity(0.1),
        borderRadius: BorderRadius.circular(AppRadius.full),
      ),
      child: Text(label,
          style: TextStyle(
              fontSize: 10, color: color, fontWeight: FontWeight.w600)),
    );
  }
}

class _RequestSheet extends StatefulWidget {
  final Future<void> Function(int productId, int qty, String? notes) onSubmit;
  const _RequestSheet({required this.onSubmit});

  @override
  State<_RequestSheet> createState() => _RequestSheetState();
}

class _RequestSheetState extends State<_RequestSheet> {
  final _notesCtrl = TextEditingController();
  int _qty = 1;
  bool _submitting = false;

  @override
  void dispose() {
    _notesCtrl.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: const BoxDecoration(
        color: AppColors.surface,
        borderRadius: BorderRadius.vertical(top: Radius.circular(24)),
      ),
      padding: EdgeInsets.only(
        left: 20,
        right: 20,
        top: 20,
        bottom: MediaQuery.of(context).viewInsets.bottom + 20,
      ),
      child: Column(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Center(
            child: Container(
              width: 40,
              height: 4,
              decoration: BoxDecoration(
                color: AppColors.outline,
                borderRadius: BorderRadius.circular(2),
              ),
            ),
          ),
          const SizedBox(height: 16),
          Text('Request Sample Books',
              style: Theme.of(context).textTheme.titleMedium),
          const SizedBox(height: 4),
          Text(
            'Requests require admin approval. Value will be deducted from your annual budget.',
            style: Theme.of(context)
                .textTheme
                .bodySmall
                ?.copyWith(color: AppColors.textMuted),
          ),
          const SizedBox(height: 20),

          // Quantity selector
          Row(
            children: [
              Text('Quantity:', style: Theme.of(context).textTheme.bodyMedium),
              const Spacer(),
              IconButton(
                onPressed:
                    _qty > 1 ? () => setState(() => _qty--) : null,
                icon: const Icon(Icons.remove_circle_outline_rounded),
              ),
              Text('$_qty',
                  style: const TextStyle(
                      fontSize: 18, fontWeight: FontWeight.w700)),
              IconButton(
                onPressed: _qty < 20 ? () => setState(() => _qty++) : null,
                icon: const Icon(Icons.add_circle_outline_rounded),
              ),
            ],
          ),

          const SizedBox(height: 12),
          TextFormField(
            controller: _notesCtrl,
            maxLines: 2,
            textCapitalization: TextCapitalization.sentences,
            decoration: const InputDecoration(
              hintText: 'Notes (optional)…',
              alignLabelWithHint: true,
            ),
          ),
          const SizedBox(height: 20),
          SizedBox(
            width: double.infinity,
            child: FilledButton(
              onPressed: _submitting
                  ? null
                  : () async {
                      setState(() => _submitting = true);
                      try {
                        // productId 1 is a placeholder — in a full flow you'd pick from a list
                        await widget.onSubmit(
                            1, _qty, _notesCtrl.text.trim().isEmpty ? null : _notesCtrl.text.trim());
                      } catch (e) {
                        if (mounted) {
                          ScaffoldMessenger.of(context).showSnackBar(
                            SnackBar(content: Text('Failed: $e')),
                          );
                        }
                      } finally {
                        if (mounted) setState(() => _submitting = false);
                      }
                    },
              child: _submitting
                  ? const SizedBox(
                      height: 20,
                      width: 20,
                      child: CircularProgressIndicator(
                          strokeWidth: 2, color: Colors.white),
                    )
                  : const Text('Submit Request'),
            ),
          ),
        ],
      ),
    );
  }
}
