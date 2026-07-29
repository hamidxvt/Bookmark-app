import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:intl/intl.dart';

import '../../../core/theme/app_theme.dart';
import '../../../core/widgets/responsive_layout.dart';
import '../../../core/services/gps_service.dart';
import '../../auth/presentation/auth_notifier.dart';
import '../../workday/data/workday_status_provider.dart';

class DashboardScreen extends ConsumerStatefulWidget {
  const DashboardScreen({super.key});

  @override
  ConsumerState<DashboardScreen> createState() => _DashboardScreenState();
}

class _DashboardScreenState extends ConsumerState<DashboardScreen> {
  @override
  void initState() {
    super.initState();
    // Start GPS tracking (background service + foreground timer)
    WidgetsBinding.instance.addPostFrameCallback((_) async {
      await ref.read(gpsServiceProvider).startTracking();
    });
  }

  @override
  Widget build(BuildContext context) {
    final auth = ref.watch(authProvider);
    final user = auth.user;
    final now = DateTime.now();
    final greeting = _greeting(now.hour);

    return Scaffold(
      backgroundColor: AppColors.background,
      body: CustomScrollView(
        slivers: [
          // ── Hero App Bar ──────────────────────────────────────────────
          SliverAppBar(
            expandedHeight: 180,
            pinned: true,
            backgroundColor: AppColors.primary,
            foregroundColor: Colors.white,
            flexibleSpace: FlexibleSpaceBar(
              background: GradientBox(
                colors: AppColors.primaryGradient,
                child: SafeArea(
                  child: Padding(
                    padding: const EdgeInsets.fromLTRB(20, 16, 20, 0),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Row(
                          children: [
                            CircleAvatar(
                              radius: 22,
                              backgroundColor: Colors.white.withOpacity(0.2),
                              child: Text(
                                (user?.name.isNotEmpty == true)
                                    ? user!.name[0].toUpperCase()
                                    : 'U',
                                style: const TextStyle(
                                  color: Colors.white,
                                  fontWeight: FontWeight.w700,
                                  fontSize: 18,
                                ),
                              ),
                            ),
                            const SizedBox(width: 12),
                            Expanded(
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                    greeting,
                                    style: const TextStyle(
                                      color: Colors.white70,
                                      fontSize: 13,
                                    ),
                                  ),
                                  Text(
                                    user?.name.isNotEmpty == true
                                        ? user!.name
                                        : 'Field Officer',
                                    style: const TextStyle(
                                      color: Colors.white,
                                      fontSize: 17,
                                      fontWeight: FontWeight.w700,
                                    ),
                                  ),
                                ],
                              ),
                            ),
                            IconButton(
                              icon: const Icon(Icons.logout_rounded, color: Colors.white70),
                              onPressed: () {
                                ref.read(authProvider.notifier).logout();
                                context.go('/login');
                              },
                            ),
                          ],
                        ),
                        const SizedBox(height: 12),
                        Text(
                          DateFormat('EEEE, dd MMMM yyyy').format(now),
                          style: const TextStyle(
                            color: Colors.white60,
                            fontSize: 13,
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
              ),
            ),
          ),

          SliverPadding(
            padding: const EdgeInsets.all(AppSpacing.md),
            sliver: SliverList(
              delegate: SliverChildListDelegate([
                // ── Day Status ──────────────────────────────────────────
                _DayStatusCard(),
                const SizedBox(height: AppSpacing.md),

                // ── KPI Row ────────────────────────────────────────────
                Text('Today\'s Summary',
                    style: Theme.of(context).textTheme.titleMedium),
                const SizedBox(height: AppSpacing.sm),
                Row(
                  children: [
                    Expanded(
                      child: _KpiCard(
                        icon: Icons.route_rounded,
                        label: 'Visits',
                        value: '7',
                        sub: 'Planned today',
                        color: AppColors.info,
                      ).animate(delay: 100.ms).slideX(begin: -0.2).fadeIn(),
                    ),
                    const SizedBox(width: 12),
                    Expanded(
                      child: _KpiCard(
                        icon: Icons.check_circle_outline_rounded,
                        label: 'Done',
                        value: '0',
                        sub: 'Completed',
                        color: AppColors.success,
                      ).animate(delay: 200.ms).slideX(begin: 0.2).fadeIn(),
                    ),
                  ],
                ),
                const SizedBox(height: 12),
                Row(
                  children: [
                    Expanded(
                      child: _KpiCard(
                        icon: Icons.calendar_today_rounded,
                        label: 'Leave',
                        value: '28',
                        sub: 'Days remaining',
                        color: AppColors.secondary,
                      ).animate(delay: 300.ms).slideX(begin: -0.2).fadeIn(),
                    ),
                    const SizedBox(width: 12),
                    Expanded(
                      child: _KpiCard(
                        icon: Icons.currency_rupee_rounded,
                        label: 'Earned',
                        value: '₨0',
                        sub: 'This month',
                        color: AppColors.warning,
                      ).animate(delay: 400.ms).slideX(begin: 0.2).fadeIn(),
                    ),
                  ],
                ),
                const SizedBox(height: AppSpacing.md),

                // ── Quick Actions ──────────────────────────────────────
                Text('Quick Actions',
                    style: Theme.of(context).textTheme.titleMedium),
                const SizedBox(height: AppSpacing.sm),
                Consumer(builder: (context, ref, _) {
                  final workday = ref.watch(workdayStatusProvider);
                  final status = workday.valueOrNull;
                  final dayStarted = status?.dayStarted ?? false;
                  final dayEnded = status?.dayEnded ?? false;

                  return GridView.count(
                    crossAxisCount: 3,
                    shrinkWrap: true,
                    physics: const NeverScrollableScrollPhysics(),
                    crossAxisSpacing: 12,
                    mainAxisSpacing: 12,
                    childAspectRatio: 0.9,
                    children: [
                      // Day action — context aware
                      if (!dayStarted)
                        _QuickAction(
                          icon: Icons.play_circle_outline_rounded,
                          label: 'Start Day',
                          color: AppColors.success,
                          onTap: () => context.go('/day-start'),
                        ).animate(delay: 100.ms).scale(begin: const Offset(0.8, 0.8)).fadeIn()
                      else if (!dayEnded)
                        _QuickAction(
                          icon: Icons.stop_circle_outlined,
                          label: 'End Day',
                          color: AppColors.error,
                          onTap: () => context.go('/day-end'),
                        ).animate(delay: 100.ms).scale(begin: const Offset(0.8, 0.8)).fadeIn()
                      else
                        _QuickAction(
                          icon: Icons.check_circle_rounded,
                          label: 'Day Done',
                          color: AppColors.success,
                          onTap: () {},
                        ).animate(delay: 100.ms).scale(begin: const Offset(0.8, 0.8)).fadeIn(),

                      _QuickAction(
                        icon: Icons.map_outlined,
                        label: 'My Visits',
                        color: AppColors.primary,
                        onTap: () => context.go('/visits'),
                      ).animate(delay: 150.ms).scale(begin: const Offset(0.8, 0.8)).fadeIn(),
                      _QuickAction(
                        icon: Icons.book_outlined,
                        label: 'Samples',
                        color: AppColors.secondary,
                        onTap: () => context.go('/samples'),
                      ).animate(delay: 200.ms).scale(begin: const Offset(0.8, 0.8)).fadeIn(),
                      _QuickAction(
                        icon: Icons.beach_access_outlined,
                        label: 'Leave',
                        color: AppColors.info,
                        onTap: () => context.go('/leaves'),
                      ).animate(delay: 250.ms).scale(begin: const Offset(0.8, 0.8)).fadeIn(),
                      _QuickAction(
                        icon: Icons.add_location_alt_outlined,
                        label: 'Ad-hoc',
                        color: AppColors.warning,
                        onTap: () => context.go('/visits'),
                      ).animate(delay: 300.ms).scale(begin: const Offset(0.8, 0.8)).fadeIn(),
                      _QuickAction(
                        icon: Icons.account_balance_wallet_outlined,
                        label: 'Earnings',
                        color: AppColors.warning,
                        onTap: () => context.go('/payroll'),
                      ).animate(delay: 350.ms).scale(begin: const Offset(0.8, 0.8)).fadeIn(),
                    ],
                  );
                }),
                const SizedBox(height: AppSpacing.md),

                // ── Motivational Quote ─────────────────────────────────
                _QuoteCard()
                    .animate(delay: 500.ms)
                    .slideY(begin: 0.2)
                    .fadeIn(duration: 600.ms),
                const SizedBox(height: AppSpacing.lg),
              ]),
            ),
          ),
        ],
      ),
    );
  }

  String _greeting(int hour) {
    if (hour < 12) return 'Good Morning';
    if (hour < 17) return 'Good Afternoon';
    return 'Good Evening';
  }
}

// ── Day Status Card ────────────────────────────────────────────────────────────
class _DayStatusCard extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        gradient: LinearGradient(
          colors: [
            AppColors.secondary.withOpacity(0.12),
            AppColors.secondary.withOpacity(0.04),
          ],
          begin: Alignment.centerLeft,
          end: Alignment.centerRight,
        ),
        borderRadius: BorderRadius.circular(AppRadius.lg),
        border: Border.all(
          color: AppColors.secondary.withOpacity(0.3),
          width: 1,
        ),
      ),
      child: Row(
        children: [
          Container(
            width: 44,
            height: 44,
            decoration: BoxDecoration(
              color: AppColors.secondary.withOpacity(0.15),
              shape: BoxShape.circle,
            ),
            child: const Icon(
              Icons.wb_sunny_rounded,
              color: AppColors.secondary,
              size: 24,
            ),
          ),
          const SizedBox(width: 12),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  'Day Not Started',
                  style: Theme.of(context).textTheme.titleSmall?.copyWith(
                        color: AppColors.secondary,
                        fontWeight: FontWeight.w700,
                      ),
                ),
                Text(
                  'Tap "Start Day" to begin tracking visits',
                  style: Theme.of(context).textTheme.bodySmall,
                ),
              ],
            ),
          ),
          const Icon(Icons.chevron_right_rounded, color: AppColors.secondary),
        ],
      ),
    ).animate().slideX(begin: -0.1).fadeIn(duration: 500.ms);
  }
}

// ── KPI Card ──────────────────────────────────────────────────────────────────
class _KpiCard extends StatelessWidget {
  final IconData icon;
  final String label;
  final String value;
  final String sub;
  final Color color;

  const _KpiCard({
    required this.icon,
    required this.label,
    required this.value,
    required this.sub,
    required this.color,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: AppColors.surface,
        borderRadius: BorderRadius.circular(AppRadius.lg),
        border: Border.all(color: AppColors.outline, width: 0.5),
        boxShadow: [
          BoxShadow(
            color: color.withOpacity(0.08),
            blurRadius: 12,
            offset: const Offset(0, 4),
          ),
        ],
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Container(
            padding: const EdgeInsets.all(8),
            decoration: BoxDecoration(
              color: color.withOpacity(0.1),
              borderRadius: BorderRadius.circular(AppRadius.sm),
            ),
            child: Icon(icon, color: color, size: 20),
          ),
          const SizedBox(height: 12),
          Text(
            value,
            style: Theme.of(context).textTheme.headlineSmall?.copyWith(
                  color: AppColors.onSurface,
                  fontWeight: FontWeight.w800,
                ),
          ),
          const SizedBox(height: 2),
          Text(
            sub,
            style: Theme.of(context).textTheme.labelSmall?.copyWith(
                  color: AppColors.onBackground,
                ),
            maxLines: 1,
            overflow: TextOverflow.ellipsis,
          ),
        ],
      ),
    );
  }
}

// ── Quick Action ──────────────────────────────────────────────────────────────
class _QuickAction extends StatelessWidget {
  final IconData icon;
  final String label;
  final Color color;
  final VoidCallback onTap;

  const _QuickAction({
    required this.icon,
    required this.label,
    required this.color,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onTap,
      child: Container(
        padding: const EdgeInsets.all(12),
        decoration: BoxDecoration(
          color: AppColors.surface,
          borderRadius: BorderRadius.circular(AppRadius.lg),
          border: Border.all(color: AppColors.outline, width: 0.5),
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Container(
              padding: const EdgeInsets.all(10),
              decoration: BoxDecoration(
                color: color.withOpacity(0.12),
                borderRadius: BorderRadius.circular(AppRadius.md),
              ),
              child: Icon(icon, color: color, size: 24),
            ),
            const SizedBox(height: 8),
            Text(
              label,
              textAlign: TextAlign.center,
              style: Theme.of(context).textTheme.labelSmall?.copyWith(
                    fontWeight: FontWeight.w600,
                    color: AppColors.onSurface,
                  ),
              maxLines: 2,
              overflow: TextOverflow.ellipsis,
            ),
          ],
        ),
      ),
    );
  }
}

// ── Quote Card ────────────────────────────────────────────────────────────────
class _QuoteCard extends StatelessWidget {
  static const _quotes = [
    '"Every visit is an opportunity. Make it count."',
    '"Consistency builds trust. Show up every day."',
    '"Your territory is your business. Own it."',
    '"Small actions done daily create big results."',
    '"Be the officer that schools remember."',
  ];

  @override
  Widget build(BuildContext context) {
    final idx = DateTime.now().day % _quotes.length;
    return Container(
      padding: const EdgeInsets.all(20),
      decoration: BoxDecoration(
        gradient: LinearGradient(
          colors: [
            AppColors.primary.withOpacity(0.06),
            AppColors.secondary.withOpacity(0.06),
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
        borderRadius: BorderRadius.circular(AppRadius.lg),
        border: Border.all(color: AppColors.primary.withOpacity(0.15)),
      ),
      child: Row(
        children: [
          Icon(Icons.format_quote_rounded,
              color: AppColors.primary.withOpacity(0.4), size: 32),
          const SizedBox(width: 12),
          Expanded(
            child: Text(
              _quotes[idx],
              style: Theme.of(context).textTheme.bodyMedium?.copyWith(
                    fontStyle: FontStyle.italic,
                    color: AppColors.primary,
                    height: 1.5,
                  ),
            ),
          ),
        ],
      ),
    );
  }
}
