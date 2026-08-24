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
      backgroundColor: const Color(0xFFF5F6F8),
      body: Stack(
        children: [
          // ── Main content ───────────────────────────────────────────────────
          SingleChildScrollView(
            physics: const BouncingScrollPhysics(),
            child: Column(
              children: [
                // ── Hero AppBar section ────────────────────────────────────
                Container(
                  width: double.infinity,
                  padding: const EdgeInsets.fromLTRB(16, 16, 16, 24),
                  decoration: const BoxDecoration(
                    gradient: LinearGradient(
                      colors: AppColors.primaryGradient,
                      begin: Alignment.topLeft,
                      end: Alignment.bottomRight,
                    ),
                  ),
                  child: SafeArea(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        // Greeting + name section
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Expanded(
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                    _greeting(now.hour),
                                    style: TextStyle(
                                      color: Colors.white.withOpacity(0.8),
                                      fontSize: 13,
                                      fontWeight: FontWeight.w500,
                                      letterSpacing: 0.3,
                                    ),
                                  ),
                                  const SizedBox(height: 4),
                                  Text(
                                    user?.name ?? 'Officer',
                                    style: const TextStyle(
                                      color: Colors.white,
                                      fontSize: 22,
                                      fontWeight: FontWeight.w900,
                                      letterSpacing: -0.5,
                                    ),
                                    maxLines: 1,
                                    overflow: TextOverflow.ellipsis,
                                  ),
                                ],
                              ),
                            ),
                            GestureDetector(
                              onTap: () => context.go('/profile'),
                              child: Container(
                                width: 44,
                                height: 44,
                                decoration: BoxDecoration(
                                  color: Colors.white.withOpacity(0.15),
                                  borderRadius: BorderRadius.circular(12),
                                ),
                                child: const Icon(
                                  Icons.person_outline_rounded,
                                  color: Colors.white,
                                  size: 20,
                                ),
                              ),
                            ),
                            const SizedBox(width: 8),
                            GestureDetector(
                              onTap: () {
                                ref.read(authProvider.notifier).logout();
                                context.go('/login');
                              },
                              child: Container(
                                width: 44,
                                height: 44,
                                decoration: BoxDecoration(
                                  color: Colors.white.withOpacity(0.1),
                                  borderRadius: BorderRadius.circular(12),
                                ),
                                child: Icon(
                                  Icons.logout_rounded,
                                  color: Colors.white.withOpacity(0.8),
                                  size: 20,
                                ),
                              ),
                            ),
                          ],
                        ),
                        const SizedBox(height: 12),

                        // Date + logo
                        Row(
                          children: [
                            ClipRRect(
                              borderRadius: BorderRadius.circular(6),
                              child: Image.asset(
                                'assets/images/logo.png',
                                width: 20,
                                height: 20,
                                fit: BoxFit.contain,
                                errorBuilder: (_, __, ___) =>
                                    const SizedBox(width: 20, height: 20),
                              ),
                            ),
                            const SizedBox(width: 8),
                            Text(
                              DateFormat('EEEE, d MMM').format(now),
                              style: TextStyle(
                                color: Colors.white.withOpacity(0.65),
                                fontSize: 11.5,
                                fontWeight: FontWeight.w500,
                              ),
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),
                ),

                // ── Main content (no scroll issues) ────────────────────────
                Padding(
                  padding: const EdgeInsets.all(16),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      // Day status card
                      Consumer(
                        builder: (_, ref, __) {
                          final ws = ref.watch(workdayStatusProvider);
                          final s = ws.valueOrNull;
                          return _DayStatusCard(
                            started: s?.dayStarted ?? false,
                            ended: s?.dayEnded ?? false,
                          );
                        },
                      ),
                      const SizedBox(height: 14),

                      // ── Stats (RED background) ─────────────────────────────────
                      Container(
                        padding: const EdgeInsets.all(16),
                        decoration: BoxDecoration(
                          gradient: const LinearGradient(
                            colors: [Color(0xFFC8102E), Color(0xFFA01028)],
                            begin: Alignment.topLeft,
                            end: Alignment.bottomRight,
                          ),
                          borderRadius: BorderRadius.circular(18),
                          boxShadow: [
                            BoxShadow(
                              color: const Color(0xFFC8102E).withOpacity(0.15),
                              blurRadius: 12,
                              offset: const Offset(0, 6),
                            ),
                          ],
                        ),
                        child: Column(
                          children: [
                            Row(
                              children: [
                                Expanded(
                                  child: _RedStatTile(
                                    icon: Icons.school_outlined,
                                    label: 'Planned',
                                    value: '—',
                                  ),
                                ),
                                const SizedBox(width: 12),
                                Expanded(
                                  child: _RedStatTile(
                                    icon: Icons.check_circle_outline_rounded,
                                    label: 'Completed',
                                    value: '—',
                                  ),
                                ),
                              ],
                            ),
                            const SizedBox(height: 12),
                            Row(
                              children: [
                                Expanded(
                                  child: _RedStatTile(
                                    icon: Icons.attach_money_rounded,
                                    label: 'Earned (PKR)',
                                    value: 'Rs. 0',
                                  ),
                                ),
                                const SizedBox(width: 12),
                                Expanded(
                                  child: _RedStatTile(
                                    icon: Icons.gps_fixed_rounded,
                                    label: 'GPS',
                                    value: 'Live',
                                  ),
                                ),
                              ],
                            ),
                          ],
                        ),
                      ),
                      const SizedBox(height: 20),

                      // Quick Actions header
                      Row(
                        children: [
                          const Icon(Icons.grid_view_rounded,
                              size: 16, color: AppColors.primary),
                          const SizedBox(width: 6),
                          const Text(
                            'Quick Actions',
                            style: TextStyle(
                              fontSize: 14,
                              fontWeight: FontWeight.w700,
                              color: Color(0xFF1E293B),
                            ),
                          ),
                        ],
                      ),
                      const SizedBox(height: 12),

                      // Quick action grid (NO SCROLL)
                      Consumer(
                        builder: (ctx, ref, _) {
                          final ws = ref.watch(workdayStatusProvider);
                          final s = ws.valueOrNull;
                          final started = s?.dayStarted ?? false;
                          final ended = s?.dayEnded ?? false;

                          final actions = <_QuickActionData>[
                            if (!started)
                              _QuickActionData(
                                icon: Icons.play_circle_rounded,
                                label: 'Start Day',
                                color: AppColors.success,
                                route: '/day-start',
                              )
                            else if (!ended)
                              _QuickActionData(
                                icon: Icons.stop_circle_rounded,
                                label: 'End Day',
                                color: AppColors.error,
                                route: '/day-end',
                              )
                            else
                              _QuickActionData(
                                icon: Icons.check_circle_rounded,
                                label: 'Day Done',
                                color: AppColors.success,
                                route: null,
                              ),
                            _QuickActionData(
                              icon: Icons.checklist_rounded,
                              label: 'My Visits',
                              color: AppColors.primary,
                              route: '/visits',
                            ),
                            _QuickActionData(
                              icon: Icons.map_rounded,
                              label: 'Route Map',
                              color: const Color(0xFF7C3AED),
                              route: '/map',
                            ),
                            _QuickActionData(
                              icon: Icons.account_balance_wallet_rounded,
                              label: 'Earnings',
                              color: AppColors.warning,
                              route: '/payroll',
                            ),
                            _QuickActionData(
                              icon: Icons.calendar_today_rounded,
                              label: 'My Leaves',
                              color: const Color(0xFF0891B2),
                              route: '/leaves',
                            ),
                            _QuickActionData(
                              icon: Icons.science_rounded,
                              label: 'Samples',
                              color: const Color(0xFF059669),
                              route: '/samples',
                            ),
                          ];

                          return GridView.builder(
                            shrinkWrap: true,
                            physics: const NeverScrollableScrollPhysics(),
                            gridDelegate:
                                const SliverGridDelegateWithFixedCrossAxisCount(
                              crossAxisCount: 3,
                              crossAxisSpacing: 10,
                              mainAxisSpacing: 10,
                              childAspectRatio: 0.9,
                            ),
                            itemCount: actions.length,
                            itemBuilder: (ctx, i) => _QuickActionTile(
                              data: actions[i],
                              onTap: actions[i].route != null
                                  ? () => ctx.go(actions[i].route!)
                                  : null,
                            ),
                          );
                        },
                      ),
                      const SizedBox(height: 100), // Space for bottom nav
                    ],
                  ),
                ),
              ],
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

// ── Widgets ───────────────────────────────────────────────────────────────────
class _DayStatusCard extends StatelessWidget {
  final bool started;
  final bool ended;

  const _DayStatusCard({required this.started, required this.ended});

  @override
  Widget build(BuildContext context) {
    final Color color =
        ended ? AppColors.success : started ? AppColors.warning : const Color(0xFF64748B);
    final String title = ended
        ? 'Day Completed'
        : started
            ? 'Day In Progress'
            : 'Day Not Started';
    final String sub = ended
        ? 'Great work today! See you tomorrow.'
        : started
            ? 'GPS tracking active — visit your assigned customers'
            : 'Tap Start Day to begin GPS tracking';
    final IconData icon = ended
        ? Icons.check_circle_rounded
        : started
            ? Icons.gps_fixed_rounded
            : Icons.play_circle_rounded;

    return Container(
      padding: const EdgeInsets.all(14),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(14),
        border: Border.all(color: color.withOpacity(0.2), width: 1.2),
        boxShadow: [
          BoxShadow(
            color: color.withOpacity(0.08),
            blurRadius: 8,
            offset: const Offset(0, 2),
          ),
        ],
      ),
      child: Row(
        children: [
          Container(
            width: 44,
            height: 44,
            decoration:
                BoxDecoration(color: color.withOpacity(0.12), shape: BoxShape.circle),
            child: Icon(icon, color: color, size: 22),
          ),
          const SizedBox(width: 12),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  title,
                  style: TextStyle(
                    fontSize: 14,
                    fontWeight: FontWeight.w700,
                    color: color,
                    letterSpacing: -0.3,
                  ),
                ),
                const SizedBox(height: 3),
                Text(
                  sub,
                  style: const TextStyle(
                    fontSize: 12,
                    color: Color(0xFF64748B),
                    height: 1.3,
                  ),
                  maxLines: 2,
                  overflow: TextOverflow.ellipsis,
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

class _QuickActionData {
  final IconData icon;
  final String label;
  final Color color;
  final String? route;

  const _QuickActionData({
    required this.icon,
    required this.label,
    required this.color,
    required this.route,
  });
}

class _QuickActionTile extends StatelessWidget {
  final _QuickActionData data;
  final VoidCallback? onTap;

  const _QuickActionTile({required this.data, required this.onTap});

  @override
  Widget build(BuildContext context) {
    return Material(
      color: Colors.white,
      borderRadius: BorderRadius.circular(14),
      child: InkWell(
        onTap: onTap,
        borderRadius: BorderRadius.circular(14),
        splashColor: data.color.withOpacity(0.08),
        child: Container(
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(14),
            border: Border.all(color: const Color(0xFFE8EAEE), width: 1),
            boxShadow: [
              BoxShadow(
                color: Colors.black.withOpacity(0.04),
                blurRadius: 6,
                offset: const Offset(0, 2),
              ),
            ],
          ),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Container(
                width: 48,
                height: 48,
                decoration: BoxDecoration(
                  color: data.color.withOpacity(0.12),
                  borderRadius: BorderRadius.circular(13),
                ),
                child: Icon(data.icon, color: data.color, size: 24),
              ),
              const SizedBox(height: 10),
              Text(
                data.label,
                textAlign: TextAlign.center,
                style: const TextStyle(
                  fontSize: 12,
                  fontWeight: FontWeight.w600,
                  color: Color(0xFF1E293B),
                  letterSpacing: -0.2,
                  height: 1.2,
                ),
                maxLines: 2,
                overflow: TextOverflow.ellipsis,
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class _RedStatTile extends StatelessWidget {
  final IconData icon;
  final String label;
  final String value;

  const _RedStatTile({
    required this.icon,
    required this.label,
    required this.value,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(12),
      decoration: BoxDecoration(
        color: Colors.white.withOpacity(0.12),
        borderRadius: BorderRadius.circular(12),
        border: Border.all(color: Colors.white.withOpacity(0.25), width: 1),
        boxShadow: [
          BoxShadow(
            color: Colors.black.withOpacity(0.08),
            blurRadius: 4,
            offset: const Offset(0, 2),
          ),
        ],
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Container(
            padding: const EdgeInsets.all(7),
            decoration: BoxDecoration(
              color: Colors.white.withOpacity(0.18),
              borderRadius: BorderRadius.circular(8),
            ),
            child: Icon(icon, color: Colors.white, size: 17),
          ),
          const SizedBox(height: 8),
          Text(
            value,
            style: const TextStyle(
              fontSize: 15,
              fontWeight: FontWeight.w800,
              color: Colors.white,
              letterSpacing: -0.4,
            ),
          ),
          const SizedBox(height: 3),
          Text(
            label,
            style: TextStyle(
              fontSize: 10,
              color: Colors.white.withOpacity(0.8),
              fontWeight: FontWeight.w500,
              height: 1.2,
            ),
            maxLines: 2,
            overflow: TextOverflow.ellipsis,
          ),
        ],
      ),
    );
  }
}
