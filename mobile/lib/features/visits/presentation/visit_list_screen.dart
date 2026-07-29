import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:shimmer/shimmer.dart';

import '../../../core/theme/app_theme.dart';
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
          onPressed: () => context.go('/dashboard'),
        ),
        actions: [
          IconButton(
            icon: const Icon(Icons.refresh_rounded),
            onPressed: () => ref.read(visitListProvider.notifier).refresh(),
          ),
          IconButton(
            icon: const Icon(Icons.add_location_alt_outlined),
            tooltip: 'Ad-hoc Visit',
            onPressed: () {},
          ),
        ],
      ),
      body: visitsAsync.when(
        loading: () => _VisitShimmer(),
        error: (err, _) => _ErrorView(
          message: err.toString(),
          onRetry: () => ref.read(visitListProvider.notifier).refresh(),
        ),
        data: (visits) => visits.isEmpty
            ? _EmptyState()
            : RefreshIndicator(
                onRefresh: () => ref.read(visitListProvider.notifier).refresh(),
                child: ListView.separated(
                  padding: const EdgeInsets.all(AppSpacing.md),
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
}

class _VisitTile extends StatelessWidget {
  final Visit visit;
  final int index;

  const _VisitTile({required this.visit, required this.index});

  @override
  Widget build(BuildContext context) {
    final isActionable = visit.isPlanned || visit.isInProgress;

    return GestureDetector(
      onTap: isActionable
          ? () => context.go('/visits/${visit.id}/complete')
          : null,
      child: Container(
        decoration: BoxDecoration(
          color: AppColors.surface,
          borderRadius: BorderRadius.circular(AppRadius.lg),
          border: Border.all(
            color: visit.isCompleted
                ? AppColors.success.withOpacity(0.3)
                : visit.isMissed
                    ? AppColors.error.withOpacity(0.3)
                    : AppColors.outline,
            width: 1,
          ),
          boxShadow: [
            BoxShadow(
              color: Colors.black.withOpacity(0.04),
              blurRadius: 8,
              offset: const Offset(0, 2),
            ),
          ],
        ),
        child: Padding(
          padding: const EdgeInsets.all(16),
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
                          child: Text(
                            visit.locationName,
                            style: Theme.of(context).textTheme.titleSmall?.copyWith(
                                  decoration: visit.isCompleted
                                      ? TextDecoration.lineThrough
                                      : null,
                                  color: visit.isCompleted
                                      ? AppColors.onBackground
                                      : AppColors.onSurface,
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
            'The route planning engine will populate\ntomorrow\'s visits at midnight.',
            textAlign: TextAlign.center,
            style: Theme.of(context).textTheme.bodyMedium,
          ),
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
