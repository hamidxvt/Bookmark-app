import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:intl/intl.dart';

import '../../../core/theme/app_theme.dart';
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
    WidgetsBinding.instance.addPostFrameCallback((_) async {
      await ref.read(gpsServiceProvider).startTracking();
    });
  }

  @override
  Widget build(BuildContext context) {
    final auth = ref.watch(authProvider);
    final user = auth.user;
    final now = DateTime.now();
    final initials = (user?.name.isNotEmpty == true)
        ? user!.name.trim().split(' ').take(2).map((p) => p[0]).join().toUpperCase()
        : 'OF';

    return Scaffold(
      backgroundColor: AppColors.background,
      body: CustomScrollView(
        slivers: [
          // ── Hero ──────────────────────────────────────────────────────────
          SliverAppBar(
            expandedHeight: 200,
            pinned: true,
            stretch: true,
            backgroundColor: AppColors.primary,
            foregroundColor: Colors.white,
            flexibleSpace: FlexibleSpaceBar(
              collapseMode: CollapseMode.parallax,
              background: Stack(
                fit: StackFit.expand,
                children: [
                  // Red brand gradient
                  Container(
                    decoration: const BoxDecoration(
                      gradient: LinearGradient(
                        colors: AppColors.primaryGradient,
                        begin: Alignment.topLeft,
                        end: Alignment.bottomRight,
                      ),
                    ),
                  ),
                  // Decorative circles
                  Positioned(
                    top: -40,
                    right: -40,
                    child: Container(
                      width: 180,
                      height: 180,
                      decoration: BoxDecoration(
                        shape: BoxShape.circle,
                        color: Colors.white.withOpacity(0.05),
                      ),
                    ),
                  ),
                  Positioned(
                    bottom: -20,
                    left: -30,
                    child: Container(
                      width: 120,
                      height: 120,
                      decoration: BoxDecoration(
                        shape: BoxShape.circle,
                        color: Colors.white.withOpacity(0.04),
                      ),
                    ),
                  ),
                  // Content
                  SafeArea(
                    child: Padding(
                      padding: const EdgeInsets.fromLTRB(20, 12, 20, 20),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Row(
                            children: [
                              // Avatar
                              Container(
                                width: 46,
                                height: 46,
                                decoration: BoxDecoration(
                                  shape: BoxShape.circle,
                                  color: Colors.white.withOpacity(0.15),
                                  border: Border.all(color: Colors.white.withOpacity(0.3), width: 1.5),
                                ),
                                child: Center(
                                  child: Text(
                                    initials,
                                    style: const TextStyle(
                                      color: Colors.white,
                                      fontWeight: FontWeight.w800,
                                      fontSize: 16,
                                    ),
                                  ),
                                ),
                              ),
                              const SizedBox(width: 12),
                              Expanded(
                                child: Column(
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: [
                                    Text(
                                      _greeting(now.hour),
                                      style: TextStyle(
                                        color: Colors.white.withOpacity(0.75),
                                        fontSize: 12,
                                        letterSpacing: 0.3,
                                      ),
                                    ),
                                    Text(
                                      user?.name.isNotEmpty == true ? user!.name : 'Field Officer',
                                      style: const TextStyle(
                                        color: Colors.white,
                                        fontSize: 18,
                                        fontWeight: FontWeight.w700,
                                        letterSpacing: -0.3,
                                      ),
                                    ),
                                  ],
                                ),
                              ),
                              // Profile button
                              GestureDetector(
                                onTap: () => context.go('/profile'),
                                child: Container(
                                  padding: const EdgeInsets.all(8),
                                  decoration: BoxDecoration(
                                    color: Colors.white.withOpacity(0.12),
                                    borderRadius: BorderRadius.circular(10),
                                    border: Border.all(color: Colors.white.withOpacity(0.2)),
                                  ),
                                  child: const Icon(Icons.person_rounded, color: Colors.white, size: 20),
                                ),
                              ),
                              const SizedBox(width: 8),
                              GestureDetector(
                                onTap: () {
                                  ref.read(authProvider.notifier).logout();
                                  context.go('/login');
                                },
                                child: Container(
                                  padding: const EdgeInsets.all(8),
                                  decoration: BoxDecoration(
                                    color: Colors.white.withOpacity(0.10),
                                    borderRadius: BorderRadius.circular(10),
                                    border: Border.all(color: Colors.white.withOpacity(0.15)),
                                  ),
                                  child: Icon(Icons.logout_rounded, color: Colors.white.withOpacity(0.8), size: 20),
                                ),
                              ),
                            ],
                          ),
                          const Spacer(),
                          Text(
                            DateFormat('EEEE, d MMMM yyyy').format(now),
                            style: TextStyle(
                              color: Colors.white.withOpacity(0.65),
                              fontSize: 12.5,
                              letterSpacing: 0.2,
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),

          SliverPadding(
            padding: const EdgeInsets.fromLTRB(16, 20, 16, 100),
            sliver: SliverList(
              delegate: SliverChildListDelegate([
                // ── Day Status ───────────────────────────────────────────────
                Consumer(builder: (_, ref, __) {
                  final ws = ref.watch(workdayStatusProvider);
                  final s = ws.valueOrNull;
                  final started = s?.dayStarted ?? false;
                  final ended = s?.dayEnded ?? false;
                  return _DayStatusCard(started: started, ended: ended).animate().slideX(begin: -0.06).fadeIn(duration: 400.ms);
                }),
                const SizedBox(height: 20),

                // ── Quick Actions ────────────────────────────────────────────
                _sectionHeader('Quick Actions'),
                const SizedBox(height: 10),
                Consumer(builder: (context, ref, _) {
                  final ws = ref.watch(workdayStatusProvider);
                  final s = ws.valueOrNull;
                  final started = s?.dayStarted ?? false;
                  final ended = s?.dayEnded ?? false;

                  return GridView.count(
                    crossAxisCount: 3,
                    shrinkWrap: true,
                    physics: const NeverScrollableScrollPhysics(),
                    crossAxisSpacing: 10,
                    mainAxisSpacing: 10,
                    childAspectRatio: 0.88,
                    children: [
                      // Context-aware day action
                      if (!started)
                        _QuickAction(
                          icon: Icons.play_circle_outline_rounded,
                          label: 'Start Day',
                          color: AppColors.success,
                          onTap: () => context.go('/day-start'),
                        ).animate(delay: 60.ms).scale(begin: const Offset(0.85, 0.85)).fadeIn()
                      else if (!ended)
                        _QuickAction(
                          icon: Icons.stop_circle_outlined,
                          label: 'End Day',
                          color: AppColors.error,
                          onTap: () => context.go('/day-end'),
                        ).animate(delay: 60.ms).scale(begin: const Offset(0.85, 0.85)).fadeIn()
                      else
                        _QuickAction(
                          icon: Icons.check_circle_rounded,
                          label: 'Day Done',
                          color: AppColors.success,
                          onTap: () {},
                        ).animate(delay: 60.ms).scale(begin: const Offset(0.85, 0.85)).fadeIn(),

                      _QuickAction(
                        icon: Icons.route_rounded,
                        label: 'My Visits',
                        color: AppColors.primary,
                        onTap: () => context.go('/visits'),
                      ).animate(delay: 120.ms).scale(begin: const Offset(0.85, 0.85)).fadeIn(),

                      _QuickAction(
                        icon: Icons.map_rounded,
                        label: 'Route Map',
                        color: AppColors.secondary,
                        onTap: () => context.go('/map'),
                      ).animate(delay: 180.ms).scale(begin: const Offset(0.85, 0.85)).fadeIn(),

                      _QuickAction(
                        icon: Icons.account_balance_wallet_outlined,
                        label: 'Earnings',
                        color: AppColors.warning,
                        onTap: () => context.go('/payroll'),
                      ).animate(delay: 240.ms).scale(begin: const Offset(0.85, 0.85)).fadeIn(),

                      _QuickAction(
                        icon: Icons.event_available_rounded,
                        label: 'My Leaves',
                        color: const Color(0xFF7C3AED),
                        onTap: () => context.go('/leaves'),
                      ).animate(delay: 360.ms).scale(begin: const Offset(0.85, 0.85)).fadeIn(),

                      _QuickAction(
                        icon: Icons.inventory_2_rounded,
                        label: 'Samples',
                        color: const Color(0xFF0891B2),
                        onTap: () => context.go('/samples'),
                      ).animate(delay: 420.ms).scale(begin: const Offset(0.85, 0.85)).fadeIn(),
                    ],
                  );
                }),
                const SizedBox(height: 20),

                // ── Today's Stats ────────────────────────────────────────────
                _sectionHeader("Today's Stats"),
                const SizedBox(height: 10),
                Row(children: [
                  Expanded(child: _StatCard(icon: Icons.school_outlined, label: 'Planned', value: '7', color: AppColors.info)
                      .animate(delay: 100.ms).slideX(begin: -0.1).fadeIn()),
                  const SizedBox(width: 10),
                  Expanded(child: _StatCard(icon: Icons.check_circle_outline_rounded, label: 'Done', value: '0', color: AppColors.success)
                      .animate(delay: 200.ms).slideX(begin: 0.1).fadeIn()),
                ]),
                const SizedBox(height: 10),
                Row(children: [
                  Expanded(child: _StatCard(icon: Icons.currency_rupee_rounded, label: 'Earned', value: '₨0', color: AppColors.warning)
                      .animate(delay: 300.ms).slideX(begin: -0.1).fadeIn()),
                  const SizedBox(width: 10),
                  Expanded(child: _StatCard(icon: Icons.gps_fixed_rounded, label: 'GPS', value: 'Live', color: AppColors.secondary)
                      .animate(delay: 400.ms).slideX(begin: 0.1).fadeIn()),
                ]),
                const SizedBox(height: AppSpacing.lg),
              ]),
            ),
          ),
        ],
      ),
    );
  }

  Widget _sectionHeader(String title) => Text(
    title,
    style: const TextStyle(
      fontSize: 15,
      fontWeight: FontWeight.w700,
      color: Color(0xFF1E293B),
      letterSpacing: -0.2,
    ),
  );

  String _greeting(int hour) {
    if (hour < 12) return 'Good Morning';
    if (hour < 17) return 'Good Afternoon';
    return 'Good Evening';
  }
}

// ── Day Status Card (glassmorphism) ──────────────────────────────────────────
class _DayStatusCard extends StatelessWidget {
  final bool started;
  final bool ended;
  const _DayStatusCard({required this.started, required this.ended});

  @override
  Widget build(BuildContext context) {
    final Color color = ended
        ? AppColors.success
        : started
            ? AppColors.secondary
            : AppColors.warning;
    final String title = ended
        ? 'Day Completed'
        : started
            ? 'Day In Progress'
            : 'Day Not Started';
    final String sub = ended
        ? 'Great work today! See you tomorrow.'
        : started
            ? 'GPS tracking active — visit your assigned schools'
            : 'Tap Start Day to activate GPS tracking';
    final IconData icon = ended
        ? Icons.check_circle_rounded
        : started
            ? Icons.gps_fixed_rounded
            : Icons.play_circle_outline_rounded;

    return ClipRRect(
      borderRadius: BorderRadius.circular(16),
      child: BackdropFilter(
        filter: ImageFilter.blur(sigmaX: 12, sigmaY: 12),
        child: Container(
          padding: const EdgeInsets.all(16),
          decoration: BoxDecoration(
            gradient: LinearGradient(
              colors: [color.withOpacity(0.13), color.withOpacity(0.05)],
              begin: Alignment.centerLeft,
              end: Alignment.centerRight,
            ),
            borderRadius: BorderRadius.circular(16),
            border: Border.all(color: color.withOpacity(0.3), width: 1),
          ),
          child: Row(children: [
            Container(
              width: 44,
              height: 44,
              decoration: BoxDecoration(
                color: color.withOpacity(0.15),
                shape: BoxShape.circle,
              ),
              child: Icon(icon, color: color, size: 22),
            ),
            const SizedBox(width: 12),
            Expanded(
              child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
                Text(title,
                    style: TextStyle(fontSize: 14, fontWeight: FontWeight.w700, color: color)),
                const SizedBox(height: 2),
                Text(sub,
                    style: const TextStyle(fontSize: 12, color: Color(0xFF64748B))),
              ]),
            ),
          ]),
        ),
      ),
    );
  }
}

// ── Quick Action (glassmorphism card) ────────────────────────────────────────
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
      child: ClipRRect(
        borderRadius: BorderRadius.circular(14),
        child: BackdropFilter(
          filter: ImageFilter.blur(sigmaX: 8, sigmaY: 8),
          child: Container(
            decoration: BoxDecoration(
              color: Colors.white.withOpacity(0.85),
              borderRadius: BorderRadius.circular(14),
              border: Border.all(color: color.withOpacity(0.15), width: 1),
              boxShadow: [
                BoxShadow(
                  color: color.withOpacity(0.08),
                  blurRadius: 12,
                  offset: const Offset(0, 4),
                ),
              ],
            ),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Container(
                  width: 44,
                  height: 44,
                  decoration: BoxDecoration(
                    gradient: LinearGradient(
                      colors: [color.withOpacity(0.15), color.withOpacity(0.08)],
                      begin: Alignment.topLeft,
                      end: Alignment.bottomRight,
                    ),
                    borderRadius: BorderRadius.circular(12),
                  ),
                  child: Icon(icon, color: color, size: 22),
                ),
                const SizedBox(height: 8),
                Text(
                  label,
                  textAlign: TextAlign.center,
                  style: const TextStyle(
                    fontSize: 11.5,
                    fontWeight: FontWeight.w600,
                    color: Color(0xFF1E293B),
                    letterSpacing: -0.1,
                  ),
                  maxLines: 2,
                  overflow: TextOverflow.ellipsis,
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

// ── Stat Card ────────────────────────────────────────────────────────────────
class _StatCard extends StatelessWidget {
  final IconData icon;
  final String label;
  final String value;
  final Color color;

  const _StatCard({
    required this.icon,
    required this.label,
    required this.value,
    required this.color,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(14),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(14),
        border: Border.all(color: const Color(0xFFE2E8F0), width: 0.8),
        boxShadow: [
          BoxShadow(
            color: color.withOpacity(0.06),
            blurRadius: 10,
            offset: const Offset(0, 3),
          ),
        ],
      ),
      child: Row(children: [
        Container(
          padding: const EdgeInsets.all(8),
          decoration: BoxDecoration(
            color: color.withOpacity(0.1),
            borderRadius: BorderRadius.circular(8),
          ),
          child: Icon(icon, color: color, size: 18),
        ),
        const SizedBox(width: 10),
        Expanded(
          child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
            Text(value,
                style: TextStyle(
                  fontSize: 18,
                  fontWeight: FontWeight.w800,
                  color: color,
                  letterSpacing: -0.5,
                )),
            Text(label,
                style: const TextStyle(
                  fontSize: 11,
                  color: Color(0xFF94A3B8),
                  fontWeight: FontWeight.w500,
                )),
          ]),
        ),
      ]),
    );
  }
}
