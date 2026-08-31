import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:shimmer/shimmer.dart';
import 'package:dio/dio.dart';

import '../../../core/theme/app_theme.dart';
import '../../../core/network/dio_client.dart';
import '../../../core/constants/api_constants.dart';
import '../data/visit_models.dart';
import '../data/visit_repository.dart';

class VisitListScreen extends ConsumerWidget {
  const VisitListScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final visitsAsync = ref.watch(visitListProvider);

    return Scaffold(
      backgroundColor: AppColors.background,
      appBar: AppBar(
        title: const Text('Today\'s Visits'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios_new_rounded),
          onPressed: () => context.pop(),
        ),
        actions: [
          IconButton(
            icon: const Icon(Icons.refresh_rounded),
            onPressed: () => ref.read(visitListProvider.notifier).refresh(),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton.extended(
        onPressed: () => _showAdhocSheet(context, ref),
        icon: const Icon(Icons.add_location_alt_rounded),
        label: const Text('Ad-hoc Visit'),
        backgroundColor: AppColors.primary,
        foregroundColor: Colors.white,
      ),
      body: visitsAsync.when(
        loading: () => _VisitShimmer(),
        error: (err, _) => _ErrorView(
          message: err.toString(),
          onRetry: () => ref.read(visitListProvider.notifier).refresh(),
        ),
        data: (visits) => visits.isEmpty
            ? _EmptyState(onAdhoc: () => _showAdhocSheet(context, ref))
            : RefreshIndicator(
                onRefresh: () => ref.read(visitListProvider.notifier).refresh(),
                child: ListView.separated(
                  padding: const EdgeInsets.fromLTRB(
                      AppSpacing.md, AppSpacing.md, AppSpacing.md, 100),
                  itemCount: visits.length,
                  separatorBuilder: (_, __) => const SizedBox(height: 10),
                  itemBuilder: (ctx, i) => _VisitTile(
                    visit: visits[i],
                    index: i,
                  ),
                ),
              ),
      ),
    );
  }

  void _showAdhocSheet(BuildContext context, WidgetRef ref) {
    showModalBottomSheet(
      context: context,
      isScrollControlled: true,
      backgroundColor: Colors.transparent,
      builder: (_) => _AdhocVisitSheet(onCreated: () {
        ref.read(visitListProvider.notifier).refresh();
      }),
    );
  }
}

class _VisitTile extends ConsumerWidget {
  final Visit visit;
  final int index;

  const _VisitTile({required this.visit, required this.index});

  Future<void> _onTap(BuildContext context, WidgetRef ref) async {
    // Enforce 1 active visit at a time
    if (visit.isPlanned) {
      final visits = ref.read(visitListProvider).valueOrNull ?? [];
      final hasActiveVisit = visits.any((v) => v.isInProgress);
      if (hasActiveVisit) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: const Row(children: [
              Icon(Icons.warning_amber_rounded, color: Colors.white, size: 18),
              SizedBox(width: 8),
              Expanded(child: Text('Please complete your current active visit first!')),
            ]),
            backgroundColor: AppColors.warning,
            behavior: SnackBarBehavior.floating,
            duration: const Duration(seconds: 3),
          ),
        );
        return;
      }
    }
    context.push('/visits/${visit.id}/complete');
  }

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final isActionable = visit.isPlanned || visit.isInProgress;

    return GestureDetector(
      onTap: isActionable ? () => _onTap(context, ref) : null,
      child: Container(
        decoration: BoxDecoration(
          color: AppColors.surface,
          borderRadius: BorderRadius.circular(AppRadius.lg),
          border: Border.all(color: AppColors.outline.withOpacity(0.6), width: 0.5),
          boxShadow: [
            BoxShadow(
              color: Colors.black.withOpacity(0.04),
              blurRadius: 8,
              offset: const Offset(0, 2),
            ),
          ],
        ),
        child: ClipRRect(
          borderRadius: BorderRadius.circular(AppRadius.lg),
          child: IntrinsicHeight(
            child: Row(
              children: [
                // Status accent bar
                Container(
                  width: 4,
                  color: _statusColor(visit.status),
                ),
                Expanded(
                  child: Padding(
                    padding: const EdgeInsets.all(14),
                    child: Row(
            children: [
              // Sequence number
              Container(
                width: 36,
                height: 36,
                decoration: BoxDecoration(
                  color: _statusColor(visit.status).withOpacity(0.12),
                  shape: BoxShape.circle,
                ),
                child: Center(
                  child: Text(
                    '${visit.dailySequence}',
                    style: TextStyle(
                      color: _statusColor(visit.status),
                      fontWeight: FontWeight.w800,
                      fontSize: 14,
                    ),
                  ),
                ),
              ),
              const SizedBox(width: 12),

              // Details
              Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      children: [
                        Expanded(
                          child: GestureDetector(
                            onTap: visit.customerId != null
                                ? () => context.push('/customers/${visit.customerId}')
                                : null,
                            child: Text(
                              visit.locationName,
                              style: Theme.of(context).textTheme.titleSmall?.copyWith(
                                    decoration: visit.isCompleted
                                        ? TextDecoration.lineThrough
                                        : null,
                                    color: visit.customerId != null && !visit.isCompleted
                                        ? AppColors.primary
                                        : visit.isCompleted
                                            ? AppColors.onBackground
                                            : AppColors.onSurface,
                                  ),
                            ),
                          ),
                        ),
                        StatusBadge(status: visit.status),
                      ],
                    ),
                    const SizedBox(height: 4),
                    Row(
                      children: [
                        Icon(
                          visit.locationType == 'bookshop'
                              ? Icons.store_outlined
                              : Icons.school_outlined,
                          size: 13,
                          color: AppColors.onBackground,
                        ),
                        const SizedBox(width: 4),
                        Text(
                          visit.locationType == 'bookshop' ? 'Bookshop' : 'School',
                          style: Theme.of(context).textTheme.bodySmall,
                        ),
                        if (visit.isAdhoc) ...[
                          const SizedBox(width: 8),
                          Container(
                            padding: const EdgeInsets.symmetric(horizontal: 6, vertical: 2),
                            decoration: BoxDecoration(
                              color: Colors.orange.withOpacity(0.12),
                              borderRadius: BorderRadius.circular(4),
                            ),
                            child: const Text('Ad-hoc', style: TextStyle(fontSize: 10, color: Colors.orange, fontWeight: FontWeight.w600)),
                          ),
                        ],
                        if (visit.isCarryForward) ...[
                          const SizedBox(width: 8),
                          Container(
                            padding: const EdgeInsets.symmetric(horizontal: 6, vertical: 2),
                            decoration: BoxDecoration(
                              color: AppColors.warning.withOpacity(0.1),
                              borderRadius: BorderRadius.circular(4),
                            ),
                            child: Text(
                              'Carry ${visit.carryForwardCount}/5',
                              style: TextStyle(
                                fontSize: 10,
                                color: AppColors.warning,
                                fontWeight: FontWeight.w600,
                              ),
                            ),
                          ),
                        ],
                      ],
                    ),
                  ],
                ),
              ),

                  // Arrow
                  if (isActionable)
                    Icon(
                      Icons.chevron_right_rounded,
                      color: AppColors.outline,
                    ),
                ]),
              ),
            ),
            ],
          ),
        ),
      ),
    )
        .animate(delay: (index * 60).ms)
        .slideX(begin: 0.1, end: 0, duration: 400.ms, curve: Curves.easeOut)
        .fadeIn(duration: 400.ms);
  }

  Color _statusColor(String status) => switch (status) {
        'completed' => AppColors.success,
        'in_progress' => AppColors.warning,
        'missed' => AppColors.error,
        _ => AppColors.info,
      };
}

class _VisitShimmer extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Shimmer.fromColors(
      baseColor: Colors.grey[200]!,
      highlightColor: Colors.grey[100]!,
      child: ListView.separated(
        padding: const EdgeInsets.all(16),
        itemCount: 7,
        separatorBuilder: (_, __) => const SizedBox(height: 10),
        itemBuilder: (_, __) => Container(
          height: 80,
          decoration: BoxDecoration(
            color: Colors.white,
            borderRadius: BorderRadius.circular(AppRadius.lg),
          ),
        ),
      ),
    );
  }
}

class _EmptyState extends StatelessWidget {
  final VoidCallback? onAdhoc;
  const _EmptyState({this.onAdhoc});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Icon(Icons.route_rounded, size: 72, color: AppColors.outline),
          const SizedBox(height: 16),
          Text(
            'No visits planned today',
            style: Theme.of(context).textTheme.titleMedium,
          ),
          const SizedBox(height: 8),
          Text(
            'You can add an ad-hoc visit below,\nor ask your admin to run the scheduler.',
            textAlign: TextAlign.center,
            style: Theme.of(context).textTheme.bodyMedium,
          ),
          const SizedBox(height: 24),
          if (onAdhoc != null)
            FilledButton.icon(
              icon: const Icon(Icons.add_location_alt_rounded),
              label: const Text('Start Ad-hoc Visit'),
              onPressed: onAdhoc,
            ),
        ],
      ),
    );
  }
}

// ── Ad-hoc Visit Bottom Sheet ────────────────────────────────────────────────

class _AdhocVisitSheet extends ConsumerStatefulWidget {
  final VoidCallback onCreated;
  const _AdhocVisitSheet({required this.onCreated});

  @override
  ConsumerState<_AdhocVisitSheet> createState() => _AdhocVisitSheetState();
}

class _AdhocVisitSheetState extends ConsumerState<_AdhocVisitSheet> {
  final _searchCtrl = TextEditingController();
  List<Map<String, dynamic>> _results = [];
  Map<String, dynamic>? _selected;
  bool _searching = false;
  bool _creating = false;
  String _error = '';

  @override
  void dispose() {
    _searchCtrl.dispose();
    super.dispose();
  }

  Future<void> _search(String q) async {
    if (q.trim().isEmpty) {
      setState(() => _results = []);
      return;
    }
    setState(() => _searching = true);
    try {
      final dio = ref.read(dioClientProvider);
      final res = await dio.get(ApiConstants.customersSearch, params: {'q': q, 'limit': '20'});
      final data = res.data as Map<String, dynamic>;
      setState(() {
        _results = List<Map<String, dynamic>>.from(data['data'] ?? []);
        _searching = false;
      });
    } catch (_) {
      setState(() => _searching = false);
    }
  }

  Future<void> _createVisit() async {
    if (_selected == null) return;
    setState(() { _creating = true; _error = ''; });
    try {
      final dio = ref.read(dioClientProvider);
      final res = await dio.post(ApiConstants.adhocVisit, data: {
        'customerId': _selected!['id'],
        'notes': 'Ad-hoc visit',
      });
      final data = res.data as Map<String, dynamic>;
      if (data['success'] == true) {
        if (mounted) context.pop();
        widget.onCreated();
      } else {
        setState(() => _error = data['error'] ?? 'Failed to create visit');
      }
    } on DioException catch (e) {
      setState(() => _error = ApiException.fromDio(e).message);
    } catch (e) {
      setState(() => _error = e.toString());
    } finally {
      setState(() => _creating = false);
    }
  }

  @override
  Widget build(BuildContext context) {
    return DraggableScrollableSheet(
      initialChildSize: 0.8,
      minChildSize: 0.5,
      maxChildSize: 0.95,
      builder: (_, scrollCtrl) => Container(
        decoration: const BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.vertical(top: Radius.circular(20)),
        ),
        child: Column(
          children: [
            // Handle
            Container(
              margin: const EdgeInsets.symmetric(vertical: 12),
              width: 40,
              height: 4,
              decoration: BoxDecoration(
                color: AppColors.outline,
                borderRadius: BorderRadius.circular(2),
              ),
            ),

            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 20),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text('Ad-hoc Visit',
                      style: Theme.of(context).textTheme.titleLarge?.copyWith(
                            fontWeight: FontWeight.bold,
                          )),
                  const SizedBox(height: 4),
                  Text('Search for a customer to visit outside your scheduled route.',
                      style: Theme.of(context).textTheme.bodySmall),
                  const SizedBox(height: 16),

                  // Search bar
                  TextField(
                    controller: _searchCtrl,
                    autofocus: true,
                    decoration: InputDecoration(
                      hintText: 'Search customer name…',
                      prefixIcon: _searching
                          ? const Padding(
                              padding: EdgeInsets.all(12),
                              child: SizedBox(
                                  width: 20,
                                  height: 20,
                                  child: CircularProgressIndicator(strokeWidth: 2)),
                            )
                          : const Icon(Icons.search_rounded),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(12)),
                      filled: true,
                      fillColor: AppColors.background,
                    ),
                    onChanged: (v) => _search(v),
                  ),

                  if (_error.isNotEmpty) ...[
                    const SizedBox(height: 8),
                    Text(_error,
                        style: const TextStyle(color: Colors.red, fontSize: 13)),
                  ],
                ],
              ),
            ),

            // Results
            Expanded(
              child: _selected != null
                  ? _SelectedCustomerCard(
                      customer: _selected!,
                      creating: _creating,
                      onConfirm: _createVisit,
                      onClear: () => setState(() => _selected = null),
                    )
                  : Column(children: [
                      // Add new customer banner
                      GestureDetector(
                        onTap: () async {
                          context.pop();
                          final result = await context.push<int?>('/visits/0/add-customer');
                          if (result != null) widget.onCreated();
                        },
                        child: Container(
                          margin: const EdgeInsets.fromLTRB(16, 8, 16, 4),
                          padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 12),
                          decoration: BoxDecoration(
                            color: AppColors.primary.withOpacity(0.07),
                            borderRadius: BorderRadius.circular(12),
                            border: Border.all(color: AppColors.primary.withOpacity(0.25)),
                          ),
                          child: Row(children: [
                            Container(
                              width: 36, height: 36,
                              decoration: BoxDecoration(color: AppColors.primary, borderRadius: BorderRadius.circular(10)),
                              child: const Icon(Icons.person_add_rounded, color: Colors.white, size: 18),
                            ),
                            const SizedBox(width: 12),
                            Expanded(child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
                              const Text('Add New Customer', style: TextStyle(fontWeight: FontWeight.w700, fontSize: 14)),
                              Text('New school/shop not in the system', style: TextStyle(fontSize: 12, color: Colors.grey.shade500)),
                            ])),
                            Icon(Icons.arrow_forward_ios_rounded, size: 14, color: AppColors.primary),
                          ]),
                        ),
                      ),
                      Expanded(
                        child: ListView.builder(
                          controller: scrollCtrl,
                          padding: const EdgeInsets.symmetric(vertical: 4),
                          itemCount: _results.length,
                          itemBuilder: (_, i) {
                            final c = _results[i];
                            return ListTile(
                          leading: CircleAvatar(
                            backgroundColor: AppColors.primary.withOpacity(0.1),
                            child: Text(
                              (c['name'] ?? '?')[0].toUpperCase(),
                              style: TextStyle(
                                  color: AppColors.primary,
                                  fontWeight: FontWeight.bold),
                            ),
                          ),
                          title: Text(c['name'] ?? '',
                              style: const TextStyle(fontSize: 14)),
                          subtitle: Text(
                            [c['city'], c['type']].where((v) => v != null && v.toString().isNotEmpty).join(' · '),
                            style: const TextStyle(fontSize: 12),
                          ),
                          onTap: () => setState(() => _selected = c),
                        );
                      },
                    ),
                      ),
                    ]),
            ),
          ],
        ),
      ),
    );
  }
}

class _SelectedCustomerCard extends StatelessWidget {
  final Map<String, dynamic> customer;
  final bool creating;
  final VoidCallback onConfirm;
  final VoidCallback onClear;

  const _SelectedCustomerCard({
    required this.customer,
    required this.creating,
    required this.onConfirm,
    required this.onClear,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(20),
      child: Column(
        children: [
          Container(
            decoration: BoxDecoration(
              color: AppColors.primary.withOpacity(0.05),
              border: Border.all(color: AppColors.primary.withOpacity(0.2)),
              borderRadius: BorderRadius.circular(16),
            ),
            padding: const EdgeInsets.all(16),
            child: Row(
              children: [
                CircleAvatar(
                  radius: 26,
                  backgroundColor: AppColors.primary.withOpacity(0.1),
                  child: Text(
                    (customer['name'] ?? '?')[0].toUpperCase(),
                    style: TextStyle(
                        color: AppColors.primary,
                        fontWeight: FontWeight.bold,
                        fontSize: 20),
                  ),
                ),
                const SizedBox(width: 12),
                Expanded(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(customer['name'] ?? '',
                          style: const TextStyle(
                              fontWeight: FontWeight.bold, fontSize: 15)),
                      const SizedBox(height: 2),
                      if ((customer['city'] ?? '').toString().isNotEmpty)
                        Text(customer['city'],
                            style: TextStyle(
                                color: AppColors.onBackground, fontSize: 13)),
                      if ((customer['phone'] ?? '').toString().isNotEmpty)
                        Text(customer['phone'],
                            style: const TextStyle(fontSize: 12)),
                    ],
                  ),
                ),
                IconButton(
                    icon: const Icon(Icons.close_rounded),
                    onPressed: onClear),
              ],
            ),
          ),
          const Spacer(),
          SizedBox(
            width: double.infinity,
            child: FilledButton.icon(
              icon: creating
                  ? const SizedBox(
                      width: 18,
                      height: 18,
                      child: CircularProgressIndicator(
                          color: Colors.white, strokeWidth: 2))
                  : const Icon(Icons.check_circle_outline_rounded),
              label: Text(creating ? 'Starting Visit…' : 'Start Ad-hoc Visit'),
              onPressed: creating ? null : onConfirm,
              style: FilledButton.styleFrom(
                padding: const EdgeInsets.symmetric(vertical: 16),
                backgroundColor: AppColors.primary,
              ),
            ),
          ),
          const SizedBox(height: 8),
        ],
      ),
    );
  }
}

class _ErrorView extends StatelessWidget {
  final String message;
  final VoidCallback onRetry;
  const _ErrorView({required this.message, required this.onRetry});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Padding(
        padding: const EdgeInsets.all(32),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(Icons.cloud_off_rounded, size: 64, color: AppColors.error),
            const SizedBox(height: 16),
            Text('Failed to load visits',
                style: Theme.of(context).textTheme.titleMedium),
            const SizedBox(height: 8),
            Text(message,
                textAlign: TextAlign.center,
                style: Theme.of(context).textTheme.bodySmall),
            const SizedBox(height: 24),
            FilledButton.icon(
              icon: const Icon(Icons.refresh_rounded),
              label: const Text('Try Again'),
              onPressed: onRetry,
            ),
          ],
        ),
      ),
    );
  }
}
