import 'package:flutter/material.dart';
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
    WidgetsBinding.instance.addPostFrameCallback((_) {
      ref.read(gpsServiceProvider).startTracking();
    });
  }

  @override
  Widget build(BuildContext context) {
    final user = ref.watch(authProvider).user;
    final now = DateTime.now();
    final firstName = (user?.name ?? 'Officer').trim().split(' ').first;
    final initials = (user?.name.isNotEmpty == true)
        ? user!.name.trim().split(' ').take(2).map((p) => p[0]).join().toUpperCase()
        : 'OF';

    return Scaffold(
      backgroundColor: const Color(0xFFF2F4F7),
      body: CustomScrollView(
        physics: const BouncingScrollPhysics(),
        slivers: [
          // ── Red header ─────────────────────────────────────────────────
          SliverToBoxAdapter(
            child: _Header(
              firstName: firstName,
              initials: initials,
              now: now,
            ),
          ),

          // ── Body ───────────────────────────────────────────────────────
          SliverPadding(
            padding: const EdgeInsets.fromLTRB(16, 0, 16, 100),
            sliver: SliverList(
              delegate: SliverChildListDelegate([
                const SizedBox(height: 20),
                // Stats row
                Consumer(builder: (_, ref, __) {
                  final s = ref.watch(workdayStatusProvider).valueOrNull;
                  return _StatsRow(started: s?.dayStarted ?? false, ended: s?.dayEnded ?? false);
                }),
                const SizedBox(height: 16),
                // Day card
                Consumer(builder: (_, ref, __) {
                  final s = ref.watch(workdayStatusProvider).valueOrNull;
                  return _DayCard(started: s?.dayStarted ?? false, ended: s?.dayEnded ?? false);
                }),
                const SizedBox(height: 24),
                // Quick actions label
                const Text(
                  'Quick Actions',
                  style: TextStyle(
                    fontSize: 13,
                    fontWeight: FontWeight.w700,
                    color: Color(0xFF94A3B8),
                    letterSpacing: 0.8,
                  ),
                ),
                const SizedBox(height: 12),
                // Actions grid
                Consumer(builder: (_, ref, __) {
                  final s = ref.watch(workdayStatusProvider).valueOrNull;
                  final started = s?.dayStarted ?? false;
                  final ended = s?.dayEnded ?? false;
                  return _ActionsGrid(started: started, ended: ended);
                }),
              ]),
            ),
          ),
        ],
      ),
    );
  }
}

// ──────────────────────────────────────────────────────────────────────────────
// Header
// ──────────────────────────────────────────────────────────────────────────────
class _Header extends ConsumerWidget {
  final String firstName;
  final String initials;
  final DateTime now;

  const _Header({required this.firstName, required this.initials, required this.now});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final greeting = now.hour < 12 ? 'Good Morning' : now.hour < 17 ? 'Good Afternoon' : 'Good Evening';

    return Container(
      decoration: const BoxDecoration(
        gradient: LinearGradient(
          colors: [Color(0xFFC8102E), Color(0xFF8B0000)],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
        borderRadius: BorderRadius.vertical(bottom: Radius.circular(28)),
      ),
      child: SafeArea(
        bottom: false,
        child: Padding(
          padding: const EdgeInsets.fromLTRB(20, 16, 20, 28),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              // Top bar
              Row(
                children: [
                  // Logo
                  Container(
                    width: 36,
                    height: 36,
                    decoration: BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.circular(10),
                    ),
                    padding: const EdgeInsets.all(4),
                    child: Image.asset(
                      'assets/images/logo.png',
                      fit: BoxFit.contain,
                      errorBuilder: (_, __, ___) => const Icon(
                        Icons.bookmark_rounded,
                        color: Color(0xFFC8102E),
                        size: 20,
                      ),
                    ),
                  ),
                  const Spacer(),
                  // Profile button
                  _HeaderBtn(
                    icon: Icons.person_outline_rounded,
                    onTap: () => context.go('/profile'),
                  ),
                  const SizedBox(width: 8),
                  // Logout
                  _HeaderBtn(
                    icon: Icons.logout_rounded,
                    onTap: () {
                      ref.read(authProvider.notifier).logout();
                      context.go('/login');
                    },
                  ),
                ],
              ),
              const SizedBox(height: 20),
              // Greeting
              Text(
                greeting,
                style: TextStyle(
                  color: Colors.white.withOpacity(0.72),
                  fontSize: 14,
                  fontWeight: FontWeight.w500,
                ),
              ),
              const SizedBox(height: 4),
              Text(
                firstName,
                style: const TextStyle(
                  color: Colors.white,
                  fontSize: 28,
                  fontWeight: FontWeight.w800,
                  letterSpacing: -0.5,
                ),
              ),
              const SizedBox(height: 12),
              // Date row
              Row(
                children: [
                  Icon(Icons.calendar_today_rounded,
                      size: 13, color: Colors.white.withOpacity(0.6)),
                  const SizedBox(width: 6),
                  Text(
                    DateFormat('EEEE, d MMMM yyyy').format(now),
                    style: TextStyle(
                      color: Colors.white.withOpacity(0.65),
                      fontSize: 12.5,
                      fontWeight: FontWeight.w500,
                    ),
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class _HeaderBtn extends StatelessWidget {
  final IconData icon;
  final VoidCallback onTap;
  const _HeaderBtn({required this.icon, required this.onTap});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onTap,
      child: Container(
        width: 38,
        height: 38,
        decoration: BoxDecoration(
          color: Colors.white.withOpacity(0.15),
          borderRadius: BorderRadius.circular(10),
          border: Border.all(color: Colors.white.withOpacity(0.2)),
        ),
        child: Icon(icon, color: Colors.white, size: 18),
      ),
    );
  }
}

// ──────────────────────────────────────────────────────────────────────────────
// Stats Row (4 tiles in red card)
// ──────────────────────────────────────────────────────────────────────────────
class _StatsRow extends StatelessWidget {
  final bool started;
  final bool ended;
  const _StatsRow({required this.started, required this.ended});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 4, vertical: 14),
      decoration: BoxDecoration(
        color: const Color(0xFFC8102E),
        borderRadius: BorderRadius.circular(20),
        boxShadow: [
          BoxShadow(
            color: const Color(0xFFC8102E).withOpacity(0.3),
            blurRadius: 16,
            offset: const Offset(0, 6),
          ),
        ],
      ),
      child: Row(
        children: [
          _StatCell(label: 'Planned', value: '—', icon: Icons.route_rounded),
          _divider(),
          _StatCell(label: 'Done', value: '—', icon: Icons.check_circle_outline_rounded),
          _divider(),
          _StatCell(label: 'Earned', value: 'Rs. 0', icon: Icons.attach_money_rounded),
          _divider(),
          _StatCell(
            label: 'GPS',
            value: started ? 'Live' : 'Off',
            icon: Icons.gps_fixed_rounded,
            highlight: started,
          ),
        ],
      ),
    );
  }

  Widget _divider() => Container(
        width: 1,
        height: 36,
        color: Colors.white.withOpacity(0.2),
      );
}

class _StatCell extends StatelessWidget {
  final String label;
  final String value;
  final IconData icon;
  final bool highlight;
  const _StatCell({required this.label, required this.value, required this.icon, this.highlight = false});

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: Column(
        children: [
          Icon(icon, color: Colors.white.withOpacity(0.8), size: 18),
          const SizedBox(height: 6),
          Text(
            value,
            style: TextStyle(
              color: highlight ? const Color(0xFF86EFAC) : Colors.white,
              fontSize: 13,
              fontWeight: FontWeight.w800,
              letterSpacing: -0.3,
            ),
          ),
          const SizedBox(height: 2),
          Text(
            label,
            style: TextStyle(
              color: Colors.white.withOpacity(0.6),
              fontSize: 10,
              fontWeight: FontWeight.w500,
            ),
          ),
        ],
      ),
    );
  }
}

// ──────────────────────────────────────────────────────────────────────────────
// Day status card
// ──────────────────────────────────────────────────────────────────────────────
class _DayCard extends StatelessWidget {
  final bool started;
  final bool ended;
  const _DayCard({required this.started, required this.ended});

  @override
  Widget build(BuildContext context) {
    final Color accent = ended
        ? const Color(0xFF16A34A)
        : started
            ? const Color(0xFFD97706)
            : const Color(0xFF64748B);

    final String title = ended
        ? 'Day Complete'
        : started
            ? 'Day In Progress'
            : 'Day Not Started';

    final String sub = ended
        ? 'Great work! Rest up for tomorrow.'
        : started
            ? 'GPS active — go visit your customers'
            : 'Tap "Start Day" to begin your shift';

    final IconData ico = ended
        ? Icons.check_circle_rounded
        : started
            ? Icons.gps_fixed_rounded
            : Icons.play_circle_rounded;

    return Container(
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(16),
        boxShadow: [
          BoxShadow(
            color: Colors.black.withOpacity(0.05),
            blurRadius: 10,
            offset: const Offset(0, 3),
          ),
        ],
      ),
      child: Row(
        children: [
          Container(
            width: 48,
            height: 48,
            decoration: BoxDecoration(
              color: accent.withOpacity(0.1),
              borderRadius: BorderRadius.circular(14),
            ),
            child: Icon(ico, color: accent, size: 24),
          ),
          const SizedBox(width: 14),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(title,
                    style: TextStyle(
                        fontSize: 14,
                        fontWeight: FontWeight.w700,
                        color: accent,
                        letterSpacing: -0.3)),
                const SizedBox(height: 3),
                Text(sub,
                    style: const TextStyle(
                        fontSize: 12, color: Color(0xFF64748B), height: 1.4)),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

// ──────────────────────────────────────────────────────────────────────────────
// Quick Actions Grid
// ──────────────────────────────────────────────────────────────────────────────
class _ActionsGrid extends StatelessWidget {
  final bool started;
  final bool ended;
  const _ActionsGrid({required this.started, required this.ended});

  @override
  Widget build(BuildContext context) {
    final actions = <_Action>[
      if (!started)
        _Action('Start Day', Icons.play_circle_rounded, const Color(0xFF16A34A), '/day-start')
      else if (!ended)
        _Action('End Day', Icons.stop_circle_rounded, const Color(0xFFC8102E), '/day-end')
      else
        _Action('Day Done', Icons.check_circle_rounded, const Color(0xFF16A34A), null),
      _Action('My Visits', Icons.checklist_rtl_rounded, const Color(0xFFC8102E), '/visits'),
      _Action('Route Map', Icons.map_rounded, const Color(0xFF7C3AED), '/map'),
      _Action('Earnings', Icons.account_balance_wallet_rounded, const Color(0xFFD97706), '/payroll'),
      _Action('My Leaves', Icons.calendar_month_rounded, const Color(0xFF0891B2), '/leaves'),
      _Action('Samples', Icons.science_rounded, const Color(0xFF059669), '/samples'),
    ];

    return GridView.builder(
      shrinkWrap: true,
      physics: const NeverScrollableScrollPhysics(),
      gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: 3,
        crossAxisSpacing: 10,
        mainAxisSpacing: 10,
        childAspectRatio: 0.92,
      ),
      itemCount: actions.length,
      itemBuilder: (ctx, i) => _ActionTile(action: actions[i]),
    );
  }
}

class _Action {
  final String label;
  final IconData icon;
  final Color color;
  final String? route;
  const _Action(this.label, this.icon, this.color, this.route);
}

class _ActionTile extends StatelessWidget {
  final _Action action;
  const _ActionTile({required this.action});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: action.route != null ? () => context.go(action.route!) : null,
      child: Container(
        decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.circular(16),
          boxShadow: [
            BoxShadow(
              color: Colors.black.withOpacity(0.04),
              blurRadius: 8,
              offset: const Offset(0, 2),
            ),
          ],
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Container(
              width: 50,
              height: 50,
              decoration: BoxDecoration(
                color: action.color.withOpacity(0.1),
                borderRadius: BorderRadius.circular(15),
              ),
              child: Icon(action.icon, color: action.color, size: 26),
            ),
            const SizedBox(height: 10),
            Text(
              action.label,
              textAlign: TextAlign.center,
              style: const TextStyle(
                fontSize: 11.5,
                fontWeight: FontWeight.w600,
                color: Color(0xFF1E293B),
                height: 1.25,
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
