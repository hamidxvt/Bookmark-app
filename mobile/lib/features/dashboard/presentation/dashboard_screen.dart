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
    final now  = DateTime.now();
    final initials = (user?.name.isNotEmpty == true)
        ? user!.name.trim().split(' ').take(2).map((p) => p[0]).join().toUpperCase()
        : 'OF';

    return Scaffold(
      backgroundColor: const Color(0xFFF5F6F8),
      body: CustomScrollView(
        physics: const BouncingScrollPhysics(),
        slivers: [
          // ── Hero AppBar ───────────────────────────────────────────────────
          SliverAppBar(
            expandedHeight: 190,
            pinned: true,
            stretch: true,
            backgroundColor: AppColors.primary,
            foregroundColor: Colors.white,
            elevation: 0,
            flexibleSpace: FlexibleSpaceBar(
              collapseMode: CollapseMode.parallax,
              background: _HeroBanner(initials: initials, user: user, now: now),
            ),
          ),

          SliverPadding(
            padding: const EdgeInsets.fromLTRB(16, 16, 16, 100),
            sliver: SliverList(
              delegate: SliverChildListDelegate([

                // ── Day status card ───────────────────────────────────────────
                Consumer(builder: (_, ref, __) {
                  final ws = ref.watch(workdayStatusProvider);
                  final s  = ws.valueOrNull;
                  return _DayStatusCard(
                    started: s?.dayStarted ?? false,
                    ended: s?.dayEnded ?? false,
                  ).animate().slideX(begin: -0.05, duration: 400.ms, curve: Curves.easeOut).fadeIn();
                }),
                const SizedBox(height: 20),

                // ── Section: Quick Actions ────────────────────────────────────
                _SectionHeader(title: 'Quick Actions', icon: Icons.grid_view_rounded),
                const SizedBox(height: 12),

                Consumer(builder: (ctx, ref, _) {
                  final ws = ref.watch(workdayStatusProvider);
                  final s  = ws.valueOrNull;
                  final started = s?.dayStarted ?? false;
                  final ended   = s?.dayEnded ?? false;

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
                    gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
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
                    ).animate(delay: (60 * i).ms)
                        .scale(begin: const Offset(0.85, 0.85), duration: 350.ms, curve: Curves.easeOut)
                        .fadeIn(),
                  );
                }),
                const SizedBox(height: 20),

                // ── Section: Today's Stats ────────────────────────────────────
                _SectionHeader(title: "Today's Stats", icon: Icons.bar_chart_rounded),
                const SizedBox(height: 12),

                Row(children: [
                  Expanded(child: _StatTile(icon: Icons.school_outlined, label: 'Planned', value: '—', color: AppColors.info)
                      .animate(delay: 50.ms).slideX(begin: -0.1, duration: 350.ms).fadeIn()),
                  const SizedBox(width: 10),
                  Expanded(child: _StatTile(icon: Icons.check_circle_outline_rounded, label: 'Completed', value: '—', color: AppColors.success)
                      .animate(delay: 100.ms).slideX(begin: 0.1, duration: 350.ms).fadeIn()),
                ]),
                const SizedBox(height: 10),
                Row(children: [
                  Expanded(child: _StatTile(icon: Icons.currency_rupee_rounded, label: 'Earned (PKR)', value: '₨0', color: AppColors.warning)
                      .animate(delay: 150.ms).slideX(begin: -0.1, duration: 350.ms).fadeIn()),
                  const SizedBox(width: 10),
                  Expanded(child: _StatTile(icon: Icons.gps_fixed_rounded, label: 'GPS Tracking', value: 'Live', color: AppColors.primary)
                      .animate(delay: 200.ms).slideX(begin: 0.1, duration: 350.ms).fadeIn()),
                ]),

                const SizedBox(height: AppSpacing.lg),
              ]),
            ),
          ),
        ],
      ),
    );
  }
}

// ── Hero banner widget ────────────────────────────────────────────────────────
class _HeroBanner extends StatelessWidget {
  final String initials;
  final dynamic user;
  final DateTime now;

  const _HeroBanner({required this.initials, required this.user, required this.now});

  String _greeting(int h) {
    if (h < 12) return 'Good Morning';
    if (h < 17) return 'Good Afternoon';
    return 'Good Evening';
  }

  @override
  Widget build(BuildContext context) {
    return Consumer(builder: (_, ref, __) {
      return Stack(
        fit: StackFit.expand,
        children: [
          // Brand gradient
          Container(
            decoration: const BoxDecoration(
              gradient: LinearGradient(
                colors: AppColors.primaryGradient,
                begin: Alignment.topLeft,
                end: Alignment.bottomRight,
              ),
            ),
          ),
          // Subtle texture circles
          Positioned(top: -50, right: -50,
              child: _Circle(180, Colors.white.withOpacity(0.05))),
          Positioned(bottom: -30, left: -30,
              child: _Circle(130, Colors.white.withOpacity(0.04))),

          // Content
          SafeArea(
            child: Padding(
              padding: const EdgeInsets.fromLTRB(20, 12, 20, 16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(children: [
                    // Avatar
                    Container(
                      width: 44,
                      height: 44,
                      decoration: BoxDecoration(
                        shape: BoxShape.circle,
                        color: Colors.white.withOpacity(0.18),
                        border: Border.all(color: Colors.white.withOpacity(0.35), width: 1.5),
                      ),
                      child: Center(
                        child: Text(initials,
                            style: const TextStyle(color: Colors.white, fontWeight: FontWeight.w800, fontSize: 15)),
                      ),
                    ),
                    const SizedBox(width: 12),
                    Expanded(
                      child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
                        Text(
                          _greeting(now.hour),
                          style: TextStyle(color: Colors.white.withOpacity(0.7), fontSize: 11.5, letterSpacing: 0.3),
                        ),
                        Text(
                          user?.name.isNotEmpty == true ? user!.name : 'Field Officer',
                          style: const TextStyle(color: Colors.white, fontSize: 17, fontWeight: FontWeight.w700, letterSpacing: -0.3),
                          maxLines: 1,
                          overflow: TextOverflow.ellipsis,
                        ),
                      ]),
                    ),
                    // Profile button
                    _HeaderBtn(
                      icon: Icons.person_outline_rounded,
                      onTap: () => context.go('/profile'),
                    ),
                    const SizedBox(width: 6),
                    _HeaderBtn(
                      icon: Icons.logout_rounded,
                      onTap: () {
                        ref.read(authProvider.notifier).logout();
                        context.go('/login');
                      },
                    ),
                  ]),
                  const Spacer(),
                  // Logo + date row
                  Row(children: [
                    // Inline bookmark logo
                    ClipRRect(
                      borderRadius: BorderRadius.circular(8),
                      child: Image.asset(
                        'assets/images/logo.png',
                        width: 28,
                        height: 28,
                        fit: BoxFit.contain,
                        errorBuilder: (_, __, ___) => const SizedBox.shrink(),
                      ),
                    ),
                    const SizedBox(width: 8),
                    Text(
                      DateFormat('EEEE, d MMM yyyy').format(now),
                      style: TextStyle(
                        color: Colors.white.withOpacity(0.65),
                        fontSize: 12,
                        letterSpacing: 0.2,
                      ),
                    ),
                  ]),
                ],
              ),
            ),
          ),
        ],
      );
    });
  }
}

class _Circle extends StatelessWidget {
  final double size;
  final Color color;
  const _Circle(this.size, this.color);

  @override
  Widget build(BuildContext context) => Container(
        width: size,
        height: size,
        decoration: BoxDecoration(shape: BoxShape.circle, color: color),
      );
}

class _HeaderBtn extends StatelessWidget {
  final IconData icon;
  final VoidCallback onTap;
  const _HeaderBtn({required this.icon, required this.onTap});

  @override
  Widget build(BuildContext context) => GestureDetector(
        onTap: onTap,
        child: Container(
          padding: const EdgeInsets.all(8),
          decoration: BoxDecoration(
            color: Colors.white.withOpacity(0.12),
            borderRadius: BorderRadius.circular(10),
            border: Border.all(color: Colors.white.withOpacity(0.2)),
          ),
          child: Icon(icon, color: Colors.white, size: 19),
        ),
      );
}

// ── Section header ────────────────────────────────────────────────────────────
class _SectionHeader extends StatelessWidget {
  final String title;
  final IconData icon;
  const _SectionHeader({required this.title, required this.icon});

  @override
  Widget build(BuildContext context) => Row(children: [
        Icon(icon, size: 16, color: AppColors.primary),
        const SizedBox(width: 6),
        Text(
          title,
          style: const TextStyle(
            fontSize: 14,
            fontWeight: FontWeight.w700,
            color: Color(0xFF1E293B),
            letterSpacing: -0.2,
          ),
        ),
      ]);
}

// ── Day status card ───────────────────────────────────────────────────────────
class _DayStatusCard extends StatelessWidget {
  final bool started;
  final bool ended;
  const _DayStatusCard({required this.started, required this.ended});

  @override
  Widget build(BuildContext context) {
    final Color color = ended
        ? AppColors.success
        : started
            ? AppColors.warning
            : const Color(0xFF64748B);
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
        border: Border.all(color: color.withOpacity(0.25)),
        boxShadow: [
          BoxShadow(color: color.withOpacity(0.08), blurRadius: 12, offset: const Offset(0, 3)),
        ],
      ),
      child: Row(children: [
        Container(
          width: 42,
          height: 42,
          decoration: BoxDecoration(
            color: color.withOpacity(0.12),
            shape: BoxShape.circle,
          ),
          child: Icon(icon, color: color, size: 21),
        ),
        const SizedBox(width: 12),
        Expanded(child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
          Text(title,
              style: TextStyle(fontSize: 13.5, fontWeight: FontWeight.w700, color: color)),
          const SizedBox(height: 2),
          Text(sub,
              style: const TextStyle(fontSize: 11.5, color: Color(0xFF64748B))),
        ])),
        // Pulse indicator when in progress
        if (started && !ended)
          Container(
            width: 8,
            height: 8,
            decoration: const BoxDecoration(
              color: AppColors.success,
              shape: BoxShape.circle,
            ),
          ).animate(onPlay: (c) => c.repeat())
              .scaleXY(begin: 1, end: 1.5, duration: 800.ms)
              .then()
              .scaleXY(begin: 1.5, end: 1, duration: 800.ms),
      ]),
    );
  }
}

// ── Quick action tile ─────────────────────────────────────────────────────────
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
        splashColor: data.color.withOpacity(0.1),
        highlightColor: data.color.withOpacity(0.05),
        child: Container(
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(14),
            border: Border.all(color: const Color(0xFFEEF0F2)),
          ),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Container(
                width: 46,
                height: 46,
                decoration: BoxDecoration(
                  color: data.color.withOpacity(0.1),
                  borderRadius: BorderRadius.circular(13),
                ),
                child: Icon(data.icon, color: data.color, size: 23),
              ),
              const SizedBox(height: 9),
              Text(
                data.label,
                textAlign: TextAlign.center,
                style: const TextStyle(
                  fontSize: 11.5,
                  fontWeight: FontWeight.w600,
                  color: Color(0xFF1E293B),
                  letterSpacing: -0.1,
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

// ── Stats tile ────────────────────────────────────────────────────────────────
class _StatTile extends StatelessWidget {
  final IconData icon;
  final String label;
  final String value;
  final Color color;
  const _StatTile({required this.icon, required this.label, required this.value, required this.color});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(14),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(14),
        border: Border.all(color: const Color(0xFFEEF0F2)),
      ),
      child: Row(children: [
        Container(
          padding: const EdgeInsets.all(8),
          decoration: BoxDecoration(
            color: color.withOpacity(0.1),
            borderRadius: BorderRadius.circular(9),
          ),
          child: Icon(icon, color: color, size: 17),
        ),
        const SizedBox(width: 10),
        Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
          Text(value,
              style: TextStyle(fontSize: 17, fontWeight: FontWeight.w800, color: color, letterSpacing: -0.5)),
          Text(label,
              style: const TextStyle(fontSize: 10.5, color: Color(0xFF94A3B8), fontWeight: FontWeight.w500)),
        ]),
      ]),
    );
  }
}
