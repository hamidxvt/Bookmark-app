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

class VisitListScreen extends ConsumerStatefulWidget {
  const VisitListScreen({super.key});

  @override
  ConsumerState<VisitListScreen> createState() => _VisitListScreenState();
}

class _VisitListScreenState extends ConsumerState<VisitListScreen> {
  String _filter = 'All';
  final _filters = ['All', 'Pending', 'Completed', 'Missed'];

  List<Visit> _applyFilter(List<Visit> visits) {
    return switch (_filter) {
      'Pending'   => visits.where((v) => v.isPlanned).toList(),
      'Completed' => visits.where((v) => v.isCompleted).toList(),
      'Missed'    => visits.where((v) => v.status == 'MISSED' || v.status == 'missed').toList(),
      _           => visits,
    };
  }

  @override
  Widget build(BuildContext context, ) {
    final visitsAsync = ref.watch(visitListProvider);

    return Scaffold(
      backgroundColor: AppColors.background,
      body: SafeArea(
        child: Column(
          children: [
            // ── Sticky header ────────────────────────────────────────
            Container(
              color: AppColors.background,
              padding: const EdgeInsets.fromLTRB(16, 12, 16, 8),
              child: Row(
                children: [
                  const Expanded(
                    child: Text('Visits',
                        style: TextStyle(fontSize: 22, fontWeight: FontWeight.w800, color: AppColors.onSurface)),
                  ),
                  GestureDetector(
                    onTap: () => ref.read(visitListProvider.notifier).refresh(),
                    child: Container(
                      width: 40,
                      height: 40,
                      decoration: BoxDecoration(
                        color: AppColors.card,
                        borderRadius: BorderRadius.circular(14),
                        border: Border.all(color: AppColors.outline),
                      ),
                      child: const Icon(Icons.refresh_rounded, size: 18, color: AppColors.primary),
                    ),
                  ),
                ],
              ),
            ),

            Expanded(
              child: visitsAsync.when(
                loading: () => const _VisitShimmer(),
                error: (err, _) => _ErrorView(
                  message: err.toString(),
                  onRetry: () => ref.read(visitListProvider.notifier).refresh(),
                ),
                data: (allVisits) {
                  final done = allVisits.where((v) => v.isCompleted).length;
                  final total = allVisits.length;
                  final filtered = _applyFilter(allVisits);
                  return RefreshIndicator(
                    onRefresh: () => ref.read(visitListProvider.notifier).refresh(),
                    color: AppColors.primary,
                    child: CustomScrollView(
                      physics: const BouncingScrollPhysics(),
                      slivers: [
                        SliverToBoxAdapter(
                          child: Padding(
                            padding: const EdgeInsets.fromLTRB(16, 4, 16, 0),
                            child: Column(
                              children: [
                                // ── Route Hero Card ──────────────────
                                _RouteHeroCard(total: total, done: done),
                                const SizedBox(height: 14),
                                // ── Filter chips ─────────────────────
                                SizedBox(
                                  height: 38,
                                  child: ListView(
                                    scrollDirection: Axis.horizontal,
                                    children: _filters.map((f) {
                                      final active = _filter == f;
                                      return GestureDetector(
                                        onTap: () => setState(() => _filter = f),
                                        child: AnimatedContainer(
                                          duration: 200.ms,
                                          margin: const EdgeInsets.only(right: 8),
                                          padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
                                          decoration: BoxDecoration(
                                            color: active ? AppColors.primary : AppColors.card,
                                            borderRadius: BorderRadius.circular(AppRadius.full),
                                            border: Border.all(color: active ? AppColors.primary : AppColors.outline),
                                            boxShadow: active
                                                ? [BoxShadow(color: AppColors.primary.withOpacity(0.3), blurRadius: 8, offset: const Offset(0, 3))]
                                                : null,
                                          ),
                                          child: Text(f,
                                              style: TextStyle(
                                                fontSize: 13,
                                                fontWeight: FontWeight.w700,
                                                color: active ? Colors.white : AppColors.textSecondary,
                                              )),
                                        ),
                                      );
                                    }).toList(),
                                  ),
                                ),
                                const SizedBox(height: 12),
                              ],
                            ),
                          ),
                        ),

                        if (filtered.isEmpty)
                          const SliverFillRemaining(child: _EmptyFilterState())
                        else
                          SliverPadding(
                            padding: const EdgeInsets.fromLTRB(16, 0, 16, 100),
                            sliver: SliverList.separated(
                              itemCount: filtered.length,
                              separatorBuilder: (_, __) => const SizedBox(height: 10),
                              itemBuilder: (ctx, i) => _VisitCard(visit: filtered[i], index: i),
                            ),
                          ),
                      ],
                    ),
                  );
                },
              ),
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton.extended(
        onPressed: () => _showAdhocSheet(context, ref),
        icon: const Icon(Icons.add_rounded),
        label: const Text('New Visit', style: TextStyle(fontWeight: FontWeight.w800)),
        backgroundColor: AppColors.primary,
        foregroundColor: Colors.white,
        elevation: 4,
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

// ─────────────────────────────────────────────────────────────────────────────
// Route Hero Card
// ─────────────────────────────────────────────────────────────────────────────
class _RouteHeroCard extends StatelessWidget {
  final int total;
  final int done;
  const _RouteHeroCard({required this.total, required this.done});

  @override
  Widget build(BuildContext context) {
    final pct = total > 0 ? (done / total * 100).round() : 0;
    final remaining = total - done;
    final stops = total.clamp(2, 8);
    return Container(
      padding: const EdgeInsets.all(20),
      decoration: BoxDecoration(
        gradient: const LinearGradient(
          colors: AppColors.primaryGradient,
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
        borderRadius: BorderRadius.circular(AppRadius.xxl),
        boxShadow: [
          BoxShadow(
            color: AppColors.primary.withOpacity(0.4),
            blurRadius: 20,
            offset: const Offset(0, 8),
          ),
        ],
      ),
      child: Stack(
        children: [
          Positioned(
            right: -30,
            top: -30,
            child: Container(
              width: 120,
              height: 120,
              decoration: BoxDecoration(
                shape: BoxShape.circle,
                color: Colors.white.withOpacity(0.07),
              ),
            ),
          ),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                children: [
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text("TODAY'S ROUTE",
                            style: TextStyle(
                              fontSize: 11,
                              fontWeight: FontWeight.w700,
                              color: Colors.white.withOpacity(0.75),
                              letterSpacing: 1.0,
                            )),
                        const SizedBox(height: 3),
                        Text('$total Stops',
                            style: const TextStyle(
                              fontSize: 26,
                              fontWeight: FontWeight.w800,
                              color: Colors.white,
                              letterSpacing: -0.5,
                            )),
                      ],
                    ),
                  ),
                  Container(
                    padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 6),
                    decoration: BoxDecoration(
                      color: Colors.white.withOpacity(0.18),
                      borderRadius: BorderRadius.circular(AppRadius.full),
                    ),
                    child: Text('$pct%',
                        style: const TextStyle(fontSize: 13, fontWeight: FontWeight.w800, color: Colors.white)),
                  ),
                ],
              ),
              const SizedBox(height: 16),
              // Progress dots
              Row(
                children: List.generate(stops, (i) {
                  final isDone = i < done;
                  final isCur = i == done;
                  return Expanded(
                    child: Row(
                      children: [
                        Container(
                          width: 12,
                          height: 12,
                          decoration: BoxDecoration(
                            shape: BoxShape.circle,
                            color: isDone || isCur ? Colors.white : Colors.white.withOpacity(0.35),
                            boxShadow: isCur ? [BoxShadow(color: Colors.white.withOpacity(0.5), blurRadius: 6, spreadRadius: 1)] : null,
                          ),
                        ),
                        if (i < stops - 1)
                          Expanded(
                            child: Container(
                              height: 3,
                              color: isDone ? Colors.white : Colors.white.withOpacity(0.25),
                            ),
                          ),
                      ],
                    ),
                  );
                }),
              ),
              const SizedBox(height: 8),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text('$done Completed',
                      style: TextStyle(fontSize: 11.5, fontWeight: FontWeight.w700, color: Colors.white.withOpacity(0.8))),
                  Text('$remaining Remaining',
                      style: TextStyle(fontSize: 11.5, fontWeight: FontWeight.w700, color: Colors.white.withOpacity(0.8))),
                ],
              ),
            ],
          ),
        ],
      ),
    );
  }
}

// ─────────────────────────────────────────────────────────────────────────────
// Visit Card (new design)
// ─────────────────────────────────────────────────────────────────────────────
class _VisitCard extends ConsumerWidget {
  final Visit visit;
  final int index;
  const _VisitCard({required this.visit, required this.index});

  Future<void> _onTap(BuildContext context, WidgetRef ref) async {
    if (visit.isPlanned) {
      final visits = ref.read(visitListProvider).valueOrNull ?? [];
      final hasActive = visits.any((v) => v.isInProgress);
      if (hasActive) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: const Row(children: [
              Icon(Icons.warning_amber_rounded, color: Colors.white, size: 18),
              SizedBox(width: 8),
              Expanded(child: Text('Complete your active visit first!')),
            ]),
            backgroundColor: AppColors.warning,
            behavior: SnackBarBehavior.floating,
            shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
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
    final statusColor = _statusColor(visit.status);
    final n = (index + 1).toString().padLeft(2, '0');

    return GestureDetector(
      onTap: isActionable ? () => _onTap(context, ref) : null,
      child: Container(
        decoration: BoxDecoration(
          color: AppColors.card,
          borderRadius: BorderRadius.circular(AppRadius.xl),
          border: Border.all(color: AppColors.outline),
          boxShadow: [
            BoxShadow(color: Colors.black.withOpacity(0.04), blurRadius: 8, offset: const Offset(0, 2))
          ],
        ),
        child: ClipRRect(
          borderRadius: BorderRadius.circular(AppRadius.xl),
          child: IntrinsicHeight(
            child: Row(
              children: [
                // Left accent bar
                Container(width: 5, color: statusColor),
                Expanded(
                  child: Padding(
                    padding: const EdgeInsets.all(14),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Row(
                          children: [
                            // Number / check badge
                            Container(
                              width: 36,
                              height: 36,
                              decoration: BoxDecoration(
                                color: statusColor.withOpacity(0.12),
                                borderRadius: BorderRadius.circular(13),
                              ),
                              child: Center(
                                child: visit.isCompleted
                                    ? Icon(Icons.check_rounded, size: 18, color: statusColor)
                                    : Text(n,
                                        style: TextStyle(
                                          color: statusColor,
                                          fontSize: 13,
                                          fontWeight: FontWeight.w800,
                                        )),
                              ),
                            ),
                            const SizedBox(width: 12),
                            Expanded(
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Row(
                                    children: [
                                      Expanded(
                                        child: Text(visit.locationName,
                                            style: const TextStyle(
                                              fontSize: 15,
                                              fontWeight: FontWeight.w800,
                                              color: AppColors.onSurface,
                                            ),
                                            maxLines: 1,
                                            overflow: TextOverflow.ellipsis),
                                      ),
                                      _StatusChip(status: visit.status),
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
                                        color: AppColors.textSecondary,
                                      ),
                                      const SizedBox(width: 4),
                                      Text(
                                        visit.locationType == 'bookshop' ? 'Bookshop' : 'School',
                                        style: const TextStyle(fontSize: 12.5, fontWeight: FontWeight.w600, color: AppColors.textSecondary),
                                      ),
                                      if (visit.isAdhoc) ...[
                                        const SizedBox(width: 8),
                                        _SmallBadge('Ad-hoc', Colors.orange),
                                      ],
                                      if (visit.isCarryForward) ...[
                                        const SizedBox(width: 6),
                                        _SmallBadge('Carry ${visit.carryForwardCount}/5', AppColors.warning),
                                      ],
                                    ],
                                  ),
                                ],
                              ),
                            ),
                          ],
                        ),

                        // Action buttons
                        if (isActionable) ...[
                          const SizedBox(height: 12),
                          Row(
                            children: [
                              Expanded(
                                child: GestureDetector(
                                  onTap: () => context.push('/map'),
                                  child: Container(
                                    height: 42,
                                    decoration: BoxDecoration(
                                      color: AppColors.background,
                                      borderRadius: BorderRadius.circular(AppRadius.lg),
                                    ),
                                    child: const Row(
                                      mainAxisAlignment: MainAxisAlignment.center,
                                      children: [
                                        Icon(Icons.navigation_rounded, size: 15, color: AppColors.onSurface),
                                        SizedBox(width: 6),
                                        Text('Navigate',
                                            style: TextStyle(fontSize: 13, fontWeight: FontWeight.w700,
                                                color: AppColors.onSurface)),
                                      ],
                                    ),
                                  ),
                                ),
                              ),
                              const SizedBox(width: 8),
                              Expanded(
                                child: GestureDetector(
                                  onTap: () => _onTap(context, ref),
                                  child: Container(
                                    height: 42,
                                    decoration: BoxDecoration(
                                      color: AppColors.navy,
                                      borderRadius: BorderRadius.circular(AppRadius.lg),
                                    ),
                                    child: const Row(
                                      mainAxisAlignment: MainAxisAlignment.center,
                                      children: [
                                        Text('View Details',
                                            style: TextStyle(fontSize: 13, fontWeight: FontWeight.w700, color: Colors.white)),
                                        SizedBox(width: 4),
                                        Icon(Icons.chevron_right_rounded, size: 16, color: Colors.white),
                                      ],
                                    ),
                                  ),
                                ),
                              ),
                            ],
                          ),
                        ],

                        // Missed reason prompt
                        if (visit.status == 'MISSED' || visit.status == 'missed') ...[
                          const SizedBox(height: 10),
                          Container(
                            padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 8),
                            decoration: BoxDecoration(
                              color: AppColors.missed.withOpacity(0.08),
                              borderRadius: BorderRadius.circular(AppRadius.md),
                            ),
                            child: const Text('Reason required — add why this visit was missed',
                                style: TextStyle(fontSize: 11.5, fontWeight: FontWeight.w700, color: AppColors.missed)),
                          ),
                        ],
                      ],
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    )
        .animate(delay: (index * 60).ms)
        .slideX(begin: 0.08, end: 0, duration: 350.ms, curve: Curves.easeOut)
        .fadeIn(duration: 350.ms);
  }

  Color _statusColor(String status) => switch (status.toLowerCase()) {
    'completed' => AppColors.success,
    'in_progress' => AppColors.warning,
    'missed' => AppColors.missed,
    _ => AppColors.info,
  };
}

class _StatusChip extends StatelessWidget {
  final String status;
  const _StatusChip({required this.status});

  @override
  Widget build(BuildContext context) {
    final (color, label, icon) = switch (status.toLowerCase()) {
      'completed'   => (AppColors.success, 'Completed', Icons.check_rounded),
      'in_progress' => (AppColors.warning, 'Active', Icons.radio_button_checked),
      'missed'      => (AppColors.missed, 'Missed', Icons.close_rounded),
      _             => (AppColors.info, 'Pending', Icons.access_time_rounded),
    };
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
      decoration: BoxDecoration(
        color: color.withOpacity(0.1),
        borderRadius: BorderRadius.circular(AppRadius.full),
      ),
      child: Row(
        mainAxisSize: MainAxisSize.min,
        children: [
          Icon(icon, size: 11, color: color),
          const SizedBox(width: 3),
          Text(label, style: TextStyle(fontSize: 10.5, fontWeight: FontWeight.w700, color: color)),
        ],
      ),
    );
  }
}

class _SmallBadge extends StatelessWidget {
  final String label;
  final Color color;
  const _SmallBadge(this.label, this.color);

  @override
  Widget build(BuildContext context) => Container(
        padding: const EdgeInsets.symmetric(horizontal: 6, vertical: 2),
        decoration: BoxDecoration(
          color: color.withOpacity(0.1),
          borderRadius: BorderRadius.circular(4),
        ),
        child: Text(label, style: TextStyle(fontSize: 10, color: color, fontWeight: FontWeight.w600)),
      );
}

class _EmptyFilterState extends StatelessWidget {
  const _EmptyFilterState();

  @override
  Widget build(BuildContext context) {
    return const Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Icon(Icons.filter_list_off_rounded, size: 56, color: AppColors.textMuted),
          SizedBox(height: 12),
          Text('No visits match this filter', style: TextStyle(fontSize: 14, color: AppColors.textSecondary)),
        ],
      ),
    );
  }
}

class _VisitShimmer extends StatelessWidget {
  const _VisitShimmer();

  @override
  Widget build(BuildContext context) {
    return Shimmer.fromColors(
      baseColor: Colors.grey[200]!,
      highlightColor: Colors.grey[100]!,
      child: ListView.separated(
        padding: const EdgeInsets.all(16),
        itemCount: 6,
        separatorBuilder: (_, __) => const SizedBox(height: 10),
        itemBuilder: (_, __) => Container(
          height: 90,
          decoration: BoxDecoration(
            color: Colors.white,
            borderRadius: BorderRadius.circular(AppRadius.xl),
          ),
        ),
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
            Container(
              width: 72,
              height: 72,
              decoration: BoxDecoration(
                color: AppColors.missed.withOpacity(0.1),
                shape: BoxShape.circle,
              ),
              child: const Icon(Icons.cloud_off_rounded, size: 36, color: AppColors.missed),
            ),
            const SizedBox(height: 16),
            const Text('Failed to load visits',
                style: TextStyle(fontSize: 16, fontWeight: FontWeight.w800, color: AppColors.onSurface)),
            const SizedBox(height: 8),
            Text(message,
                textAlign: TextAlign.center,
                style: const TextStyle(fontSize: 13, color: AppColors.textSecondary)),
            const SizedBox(height: 24),
            GestureDetector(
              onTap: onRetry,
              child: Container(
                padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 14),
                decoration: BoxDecoration(
                  color: AppColors.primary,
                  borderRadius: BorderRadius.circular(AppRadius.lg),
                ),
                child: const Text('Try Again',
                    style: TextStyle(color: Colors.white, fontWeight: FontWeight.w800)),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
