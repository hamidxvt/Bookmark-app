import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:intl/intl.dart';

import '../../../core/theme/app_theme.dart';
import '../../../core/services/gps_service.dart';
import '../../auth/presentation/auth_notifier.dart';
import '../../workday/data/workday_status_provider.dart';
import '../../visits/data/visit_repository.dart';

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
    final greeting = now.hour < 12 ? 'Good Morning 👋' : now.hour < 17 ? 'Good Afternoon 👋' : 'Good Evening 👋';
    final name = user?.name ?? 'Officer';
    final initials = name.trim().split(' ').take(2).map((p) => p[0]).join().toUpperCase();

    return Scaffold(
      backgroundColor: AppColors.background,
      body: SafeArea(
        child: CustomScrollView(
          physics: const BouncingScrollPhysics(),
          slivers: [
            SliverToBoxAdapter(
              child: Padding(
                padding: const EdgeInsets.fromLTRB(20, 16, 20, 0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    // ── Header ──────────────────────────────────────────
                    Row(
                      children: [
                        Expanded(
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text(greeting,
                                  style: const TextStyle(
                                    fontSize: 13,
                                    fontWeight: FontWeight.w600,
                                    color: AppColors.textSecondary,
                                  )),
                              const SizedBox(height: 3),
                              Text(name,
                                  style: const TextStyle(
                                    fontSize: 24,
                                    fontWeight: FontWeight.w800,
                                    color: AppColors.onSurface,
                                    letterSpacing: -0.5,
                                  )),
                              const SizedBox(height: 6),
                              Consumer(builder: (_, ref, __) {
                                final designation = user?.displayRole ?? 'Sales Officer';
                                return Container(
                                  padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 4),
                                  decoration: BoxDecoration(
                                    color: AppColors.primary.withOpacity(0.08),
                                    borderRadius: BorderRadius.circular(AppRadius.full),
                                  ),
                                  child: Text(
                                    designation,
                                    style: const TextStyle(
                                      fontSize: 11,
                                      fontWeight: FontWeight.w700,
                                      color: AppColors.primary,
                                      letterSpacing: 0.3,
                                    ),
                                  ),
                                );
                              }),
                            ],
                          ),
                        ),
                        const SizedBox(width: 12),
                        // Notification bell
                        _IconBtn(
                          icon: Icons.notifications_outlined,
                          onTap: () {},
                        ),
                        const SizedBox(width: 8),
                        // Avatar
                        GestureDetector(
                          onTap: () => context.push('/profile'),
                          child: Container(
                            width: 42,
                            height: 42,
                            decoration: BoxDecoration(
                              color: AppColors.navy,
                              borderRadius: BorderRadius.circular(14),
                            ),
                            child: Center(
                              child: Text(initials,
                                  style: const TextStyle(
                                    color: Colors.white,
                                    fontSize: 14,
                                    fontWeight: FontWeight.w800,
                                  )),
                            ),
                          ),
                        ),
                      ],
                    ),

                    const SizedBox(height: 20),

                    // ── Shift Status Card ────────────────────────────────
                    Consumer(builder: (_, ref, __) {
                      final workdayAsync = ref.watch(workdayStatusProvider);
                      return workdayAsync.when(
                        loading: () => const _ShiftCardSkeleton(),
                        error: (_, __) => _ShiftCardError(
                            onRetry: () => ref.invalidate(workdayStatusProvider)),
                        data: (s) => _ShiftCard(
                          started: s.dayStarted,
                          ended: s.dayEnded,
                        ),
                      );
                    }),

                    const SizedBox(height: 16),

                    // ── Mission Hero Card ────────────────────────────────
                    Consumer(builder: (_, ref, __) {
                      final visitsAsync = ref.watch(visitListProvider);
                      final visits = visitsAsync.valueOrNull ?? [];
                      final done = visits.where((v) => v.status == 'COMPLETED').length;
                      final total = visits.length;
                      final pending = visits.where((v) => v.status == 'PENDING').length;
                      final next = visits.where((v) => v.status == 'PENDING').firstOrNull;
                      return _MissionHeroCard(
                        totalVisits: total,
                        doneVisits: done,
                        pendingVisits: pending,
                        nextVisitName: next?.locationName,
                      );
                    }),

                    const SizedBox(height: 16),

                    // ── GPS Status ───────────────────────────────────────
                    Consumer(builder: (_, ref, __) {
                      final s = ref.watch(workdayStatusProvider).valueOrNull;
                      final visitsAsync = ref.watch(visitListProvider);
                      final visits = visitsAsync.valueOrNull ?? [];
                      final done = visits.where((v) => v.status == 'COMPLETED').length;
                      final total = visits.length;
                      return _GpsStatusCard(
                        active: s?.dayStarted ?? false,
                        done: done,
                        total: total,
                      );
                    }),

                    const SizedBox(height: 24),

                    // ── Quick Actions ────────────────────────────────────
                    const Text('Quick Actions',
                        style: TextStyle(
                          fontSize: 15,
                          fontWeight: FontWeight.w800,
                          color: AppColors.onSurface,
                        )),
                    const SizedBox(height: 12),

                    Consumer(builder: (_, ref, __) {
                      final s = ref.watch(workdayStatusProvider).valueOrNull;
                      return _QuickActionsGrid(
                        started: s?.dayStarted ?? false,
                        ended: s?.dayEnded ?? false,
                      );
                    }),

                    const SizedBox(height: 24),

                    // ── Today's Activity Timeline ────────────────────────
                    Consumer(builder: (_, ref, __) {
                      final visitsAsync = ref.watch(visitListProvider);
                      final visits = visitsAsync.valueOrNull ?? [];
                      if (visits.isEmpty) return const SizedBox.shrink();
                      return _TodayTimeline(visits: visits);
                    }),

                    const SizedBox(height: 100),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

// ─────────────────────────────────────────────────────────────────────────────
// Icon Button
// ─────────────────────────────────────────────────────────────────────────────
class _IconBtn extends StatelessWidget {
  final IconData icon;
  final VoidCallback onTap;
  const _IconBtn({required this.icon, required this.onTap});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onTap,
      child: Container(
        width: 42,
        height: 42,
        decoration: BoxDecoration(
          color: AppColors.card,
          borderRadius: BorderRadius.circular(14),
          border: Border.all(color: AppColors.outline),
          boxShadow: [
            BoxShadow(color: Colors.black.withOpacity(0.04), blurRadius: 8, offset: const Offset(0, 2))
          ],
        ),
        child: Icon(icon, size: 20, color: AppColors.onSurface),
      ),
    );
  }
}

// ─────────────────────────────────────────────────────────────────────────────
// Shift Status Card
// ─────────────────────────────────────────────────────────────────────────────
class _ShiftCard extends StatelessWidget {
  final bool started;
  final bool ended;
  const _ShiftCard({required this.started, required this.ended});

  @override
  Widget build(BuildContext context) {
    if (started && !ended) {
      // Active shift card
      return Container(
        padding: const EdgeInsets.all(16),
        decoration: BoxDecoration(
          color: AppColors.successLight,
          borderRadius: BorderRadius.circular(AppRadius.xxl),
          border: Border.all(color: AppColors.success.withOpacity(0.25)),
        ),
        child: Column(
          children: [
            Row(
              children: [
                Expanded(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      const Text('SHIFT STARTED',
                          style: TextStyle(
                            fontSize: 11,
                            fontWeight: FontWeight.w800,
                            color: AppColors.success,
                            letterSpacing: 0.8,
                          )),
                      const SizedBox(height: 2),
                      Text(
                        DateFormat('h:mm a').format(DateTime.now()),
                        style: const TextStyle(
                          fontSize: 22,
                          fontWeight: FontWeight.w800,
                          color: AppColors.onSurface,
                          letterSpacing: -0.5,
                        ),
                      ),
                    ],
                  ),
                ),
                Container(
                  padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 7),
                  decoration: BoxDecoration(
                    color: AppColors.success.withOpacity(0.15),
                    borderRadius: BorderRadius.circular(AppRadius.full),
                  ),
                  child: const Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      Icon(Icons.radio_button_checked, size: 12, color: AppColors.success),
                      SizedBox(width: 5),
                      Text('GPS ACTIVE',
                          style: TextStyle(
                            fontSize: 11,
                            fontWeight: FontWeight.w800,
                            color: AppColors.success,
                            letterSpacing: 0.5,
                          )),
                    ],
                  ),
                ),
              ],
            ),
            const SizedBox(height: 12),
            SizedBox(
              width: double.infinity,
              child: OutlinedButton(
                onPressed: () => context.push('/day-end'),
                style: OutlinedButton.styleFrom(
                  foregroundColor: AppColors.success,
                  side: const BorderSide(color: AppColors.success, width: 1.5),
                  shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(AppRadius.lg)),
                  padding: const EdgeInsets.symmetric(vertical: 14),
                  textStyle: const TextStyle(fontWeight: FontWeight.w800, fontSize: 14),
                ),
                child: const Text('END SHIFT'),
              ),
            ),
          ],
        ),
      ).animate().fadeIn(duration: 400.ms).slideY(begin: 0.05, end: 0);
    }

    if (ended) {
      return Container(
        padding: const EdgeInsets.all(16),
        decoration: BoxDecoration(
          color: AppColors.card,
          borderRadius: BorderRadius.circular(AppRadius.xxl),
          border: Border.all(color: AppColors.outline),
        ),
        child: Row(
          children: [
            Container(
              width: 44,
              height: 44,
              decoration: BoxDecoration(
                color: AppColors.success.withOpacity(0.1),
                borderRadius: BorderRadius.circular(14),
              ),
              child: const Icon(Icons.check_circle_outline_rounded, color: AppColors.success, size: 24),
            ),
            const SizedBox(width: 14),
            const Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text('Shift Completed',
                      style: TextStyle(fontSize: 15, fontWeight: FontWeight.w800, color: AppColors.onSurface)),
                  SizedBox(height: 2),
                  Text('Great work! Rest up for tomorrow.',
                      style: TextStyle(fontSize: 12.5, color: AppColors.textSecondary)),
                ],
              ),
            ),
          ],
        ),
      );
    }

    // Not started
    return Container(
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: AppColors.card,
        borderRadius: BorderRadius.circular(AppRadius.xxl),
        border: Border.all(color: AppColors.outline),
        boxShadow: [
          BoxShadow(color: Colors.black.withOpacity(0.04), blurRadius: 12, offset: const Offset(0, 3))
        ],
      ),
      child: Row(
        children: [
          Container(
            width: 44,
            height: 44,
            decoration: BoxDecoration(
              color: AppColors.background,
              borderRadius: BorderRadius.circular(14),
            ),
            child: const Icon(Icons.access_time_rounded, color: AppColors.textSecondary, size: 22),
          ),
          const SizedBox(width: 14),
          const Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text('SHIFT STATUS',
                    style: TextStyle(fontSize: 11, fontWeight: FontWeight.w800,
                        color: AppColors.textMuted, letterSpacing: 0.6)),
                SizedBox(height: 2),
                Text('Not Started',
                    style: TextStyle(fontSize: 15, fontWeight: FontWeight.w800, color: AppColors.onSurface)),
              ],
            ),
          ),
          GestureDetector(
            onTap: () => context.push('/day-start'),
            child: Container(
              padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 11),
              decoration: BoxDecoration(
                gradient: const LinearGradient(
                  colors: AppColors.primaryGradient,
                  begin: Alignment.topLeft,
                  end: Alignment.bottomRight,
                ),
                borderRadius: BorderRadius.circular(AppRadius.lg),
                boxShadow: [
                  BoxShadow(color: AppColors.primary.withOpacity(0.4), blurRadius: 12, offset: const Offset(0, 4))
                ],
              ),
              child: const Text('START SHIFT',
                  style: TextStyle(color: Colors.white, fontSize: 13, fontWeight: FontWeight.w800)),
            ),
          ),
        ],
      ),
    ).animate().fadeIn(duration: 400.ms).slideY(begin: 0.05, end: 0);
  }
}

class _ShiftCardSkeleton extends StatelessWidget {
  const _ShiftCardSkeleton();

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 80,
      decoration: BoxDecoration(
        color: AppColors.card,
        borderRadius: BorderRadius.circular(AppRadius.xxl),
        border: Border.all(color: AppColors.outline),
      ),
    );
  }
}

class _ShiftCardError extends StatelessWidget {
  final VoidCallback onRetry;
  const _ShiftCardError({required this.onRetry});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 14),
      decoration: BoxDecoration(
        color: AppColors.card,
        borderRadius: BorderRadius.circular(AppRadius.xxl),
        border: Border.all(color: AppColors.missed.withOpacity(0.3)),
      ),
      child: Row(
        children: [
          const Icon(Icons.wifi_off_rounded, color: AppColors.missed, size: 20),
          const SizedBox(width: 12),
          const Expanded(
            child: Text('Could not load shift status',
                style: TextStyle(fontSize: 13, color: AppColors.textSecondary)),
          ),
          GestureDetector(
            onTap: onRetry,
            child: const Text('Retry',
                style: TextStyle(fontSize: 12, fontWeight: FontWeight.w700, color: AppColors.primary)),
          ),
        ],
      ),
    );
  }
}

// ─────────────────────────────────────────────────────────────────────────────
// Mission Hero Card
// ─────────────────────────────────────────────────────────────────────────────
class _MissionHeroCard extends StatelessWidget {
  final int totalVisits;
  final int doneVisits;
  final int pendingVisits;
  final String? nextVisitName;

  const _MissionHeroCard({
    required this.totalVisits,
    required this.doneVisits,
    required this.pendingVisits,
    this.nextVisitName,
  });

  @override
  Widget build(BuildContext context) {
    final pct = totalVisits > 0 ? (doneVisits / totalVisits * 100).round() : 0;
    return Container(
      decoration: BoxDecoration(
        gradient: const LinearGradient(
          colors: AppColors.primaryGradient,
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
        borderRadius: BorderRadius.circular(AppRadius.xxl),
        boxShadow: [
          BoxShadow(
            color: AppColors.primary.withOpacity(0.45),
            blurRadius: 24,
            offset: const Offset(0, 10),
          ),
        ],
      ),
      child: Stack(
        children: [
          // Dot pattern overlay
          Positioned.fill(
            child: ClipRRect(
              borderRadius: BorderRadius.circular(AppRadius.xxl),
              child: CustomPaint(painter: _DotPatternPainter()),
            ),
          ),
          // Decorative circle
          Positioned(
            right: -40,
            top: -40,
            child: Container(
              width: 160,
              height: 160,
              decoration: BoxDecoration(
                shape: BoxShape.circle,
                color: Colors.white.withOpacity(0.08),
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(20),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  children: [
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text("TODAY'S MISSION",
                              style: TextStyle(
                                fontSize: 11.5,
                                fontWeight: FontWeight.w700,
                                color: Colors.white.withOpacity(0.75),
                                letterSpacing: 1.0,
                              )),
                          const SizedBox(height: 4),
                          Text('$totalVisits ${totalVisits == 1 ? 'Visit' : 'Visits'}',
                              style: const TextStyle(
                                fontSize: 28,
                                fontWeight: FontWeight.w800,
                                color: Colors.white,
                                letterSpacing: -0.5,
                              )),
                        ],
                      ),
                    ),
                    Container(
                      padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 7),
                      decoration: BoxDecoration(
                        color: Colors.white.withOpacity(0.18),
                        borderRadius: BorderRadius.circular(AppRadius.full),
                      ),
                      child: Text('$pct%',
                          style: const TextStyle(
                            fontSize: 13,
                            fontWeight: FontWeight.w800,
                            color: Colors.white,
                          )),
                    ),
                  ],
                ),

                // Route progress dots
                const SizedBox(height: 20),
                _RouteProgressDots(total: totalVisits, done: doneVisits),

                // Progress labels
                const SizedBox(height: 6),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text('$doneVisits done',
                        style: TextStyle(fontSize: 11.5, fontWeight: FontWeight.w700,
                            color: Colors.white.withOpacity(0.8))),
                    Text('$pendingVisits left',
                        style: TextStyle(fontSize: 11.5, fontWeight: FontWeight.w700,
                            color: Colors.white.withOpacity(0.8))),
                  ],
                ),

                // Next visit card
                if (nextVisitName != null) ...[
                  const SizedBox(height: 16),
                  Container(
                    padding: const EdgeInsets.all(14),
                    decoration: BoxDecoration(
                      color: Colors.white.withOpacity(0.14),
                      borderRadius: BorderRadius.circular(AppRadius.lg),
                    ),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text('NEXT VISIT',
                            style: TextStyle(fontSize: 10.5, fontWeight: FontWeight.w700,
                                color: Colors.white.withOpacity(0.7), letterSpacing: 0.8)),
                        const SizedBox(height: 3),
                        Text(nextVisitName!,
                            style: const TextStyle(fontSize: 16, fontWeight: FontWeight.w800, color: Colors.white),
                            maxLines: 1,
                            overflow: TextOverflow.ellipsis),
                      ],
                    ),
                  ),
                ],

                // Start Route button
                const SizedBox(height: 14),
                GestureDetector(
                  onTap: () => context.push('/visits'),
                  child: Container(
                    height: 52,
                    decoration: BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.circular(AppRadius.xl),
                    ),
                    child: const Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Icon(Icons.navigation_rounded, size: 18, color: AppColors.primary),
                        SizedBox(width: 8),
                        Text('View Visits',
                            style: TextStyle(fontSize: 15, fontWeight: FontWeight.w800, color: AppColors.primary)),
                      ],
                    ),
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    ).animate().fadeIn(duration: 500.ms).slideY(begin: 0.06, end: 0);
  }
}

class _RouteProgressDots extends StatelessWidget {
  final int total;
  final int done;
  const _RouteProgressDots({required this.total, required this.done});

  @override
  Widget build(BuildContext context) {
    final count = total.clamp(2, 8);
    return Row(
      children: List.generate(count, (i) {
        final isDone = i < done;
        final isCurrent = i == done;
        return Expanded(
          child: Row(
            children: [
              Container(
                width: isDone || isCurrent ? 13 : 11,
                height: isDone || isCurrent ? 13 : 11,
                decoration: BoxDecoration(
                  shape: BoxShape.circle,
                  color: isDone || isCurrent ? Colors.white : Colors.white.withOpacity(0.35),
                  boxShadow: isCurrent
                      ? [BoxShadow(color: Colors.white.withOpacity(0.4), blurRadius: 6, spreadRadius: 2)]
                      : null,
                ),
              ),
              if (i < count - 1)
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
    );
  }
}

// Dot pattern painter for hero card
class _DotPatternPainter extends CustomPainter {
  @override
  void paint(Canvas canvas, Size size) {
    final paint = Paint()
      ..color = Colors.white.withOpacity(0.07)
      ..style = PaintingStyle.fill;
    const spacing = 18.0;
    for (double x = 0; x < size.width; x += spacing) {
      for (double y = 0; y < size.height; y += spacing) {
        canvas.drawCircle(Offset(x, y), 1, paint);
      }
    }
  }

  @override
  bool shouldRepaint(_) => false;
}

// ─────────────────────────────────────────────────────────────────────────────
// GPS Status Card
// ─────────────────────────────────────────────────────────────────────────────
class _GpsStatusCard extends StatelessWidget {
  final bool active;
  final int done;
  final int total;
  const _GpsStatusCard({required this.active, required this.done, required this.total});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => context.push('/visits'),
      child: Container(
        padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 14),
        decoration: BoxDecoration(
          color: AppColors.card,
          borderRadius: BorderRadius.circular(AppRadius.xl),
          border: Border.all(color: AppColors.outline),
          boxShadow: [
            BoxShadow(color: Colors.black.withOpacity(0.04), blurRadius: 10, offset: const Offset(0, 3))
          ],
        ),
        child: Row(
          children: [
            Stack(
              clipBehavior: Clip.none,
              children: [
                Container(
                  width: 40,
                  height: 40,
                  decoration: BoxDecoration(
                    color: active
                        ? AppColors.success.withOpacity(0.1)
                        : AppColors.background,
                    borderRadius: BorderRadius.circular(AppRadius.full),
                  ),
                  child: Icon(Icons.radio_outlined,
                      size: 20,
                      color: active ? AppColors.success : AppColors.textMuted),
                ),
                if (active)
                  Positioned(
                    right: -1,
                    top: -1,
                    child: Container(
                      width: 10,
                      height: 10,
                      decoration: BoxDecoration(
                        color: AppColors.success,
                        shape: BoxShape.circle,
                        border: Border.all(color: Colors.white, width: 2),
                      ),
                    ),
                  ),
              ],
            ),
            const SizedBox(width: 14),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(active ? 'GPS Tracking Active' : 'GPS Tracking Off',
                      style: const TextStyle(
                        fontSize: 13.5,
                        fontWeight: FontWeight.w800,
                        color: AppColors.onSurface,
                      )),
                  const SizedBox(height: 2),
                  Text('$done of $total visits completed today',
                      style: const TextStyle(
                        fontSize: 12,
                        fontWeight: FontWeight.w600,
                        color: AppColors.textSecondary,
                      )),
                ],
              ),
            ),
            Container(
              padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 5),
              decoration: BoxDecoration(
                color: active ? AppColors.success.withOpacity(0.1) : AppColors.background,
                borderRadius: BorderRadius.circular(AppRadius.full),
              ),
              child: Text(active ? 'Live' : 'Off',
                  style: TextStyle(
                    fontSize: 11.5,
                    fontWeight: FontWeight.w700,
                    color: active ? AppColors.success : AppColors.textMuted,
                  )),
            ),
          ],
        ),
      ),
    );
  }
}

// ─────────────────────────────────────────────────────────────────────────────
// Quick Actions Grid (2x2 key actions)
// ─────────────────────────────────────────────────────────────────────────────
class _QuickActionsGrid extends StatelessWidget {
  final bool started;
  final bool ended;
  const _QuickActionsGrid({required this.started, required this.ended});

  @override
  Widget build(BuildContext context) {
    final actions = [
      _QAction('My Visits', Icons.checklist_rtl_rounded,
          'Today\'s schedule', '/visits', AppColors.primary),
      _QAction('Customers', Icons.people_outline_rounded,
          'Schools & contacts', '/customers', const Color(0xFF6366F1)),
      _QAction('Samples', Icons.science_outlined,
          'Track requests', '/samples', const Color(0xFF059669)),
      _QAction('Route Map', Icons.map_outlined,
          'Navigate visits', '/map', const Color(0xFF8B5CF6)),
    ];

    return GridView.builder(
      shrinkWrap: true,
      physics: const NeverScrollableScrollPhysics(),
      gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: 2,
        crossAxisSpacing: 12,
        mainAxisSpacing: 12,
        childAspectRatio: 1.55,
      ),
      itemCount: actions.length,
      itemBuilder: (ctx, i) => _QActionTile(action: actions[i])
          .animate(delay: (i * 80).ms)
          .fadeIn(duration: 400.ms)
          .slideY(begin: 0.1, end: 0),
    );
  }
}

class _QAction {
  final String label;
  final IconData icon;
  final String desc;
  final String route;
  final Color color;
  const _QAction(this.label, this.icon, this.desc, this.route, this.color);
}

class _QActionTile extends StatelessWidget {
  final _QAction action;
  const _QActionTile({required this.action});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => context.push(action.route),
      child: Container(
        padding: const EdgeInsets.all(14),
        decoration: BoxDecoration(
          color: AppColors.card,
          borderRadius: BorderRadius.circular(AppRadius.xl),
          border: Border.all(color: AppColors.outline),
          boxShadow: [
            BoxShadow(color: Colors.black.withOpacity(0.04), blurRadius: 8, offset: const Offset(0, 2))
          ],
        ),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Container(
              width: 40,
              height: 40,
              decoration: BoxDecoration(
                color: action.color.withOpacity(0.1),
                borderRadius: BorderRadius.circular(13),
              ),
              child: Icon(action.icon, color: action.color, size: 20),
            ),
            const SizedBox(height: 8),
            Text(action.label,
                style: const TextStyle(
                  fontSize: 13.5,
                  fontWeight: FontWeight.w800,
                  color: AppColors.onSurface,
                )),
            const SizedBox(height: 2),
            Text(action.desc,
                style: const TextStyle(
                  fontSize: 11.5,
                  fontWeight: FontWeight.w500,
                  color: AppColors.textSecondary,
                ),
                maxLines: 1,
                overflow: TextOverflow.ellipsis),
          ],
        ),
      ),
    );
  }
}

// ─────────────────────────────────────────────────────────────────────────────
// Today's Activity Timeline
// ─────────────────────────────────────────────────────────────────────────────
class _TodayTimeline extends StatelessWidget {
  final List<dynamic> visits;
  const _TodayTimeline({required this.visits});

  @override
  Widget build(BuildContext context) {
    final recentVisits = visits.take(5).toList();
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            const Text("Today's Activity",
                style: TextStyle(
                  fontSize: 15,
                  fontWeight: FontWeight.w800,
                  color: AppColors.onSurface,
                )),
            GestureDetector(
              onTap: () => context.push('/visits'),
              child: const Row(
                children: [
                  Text('View all',
                      style: TextStyle(fontSize: 12.5, fontWeight: FontWeight.w700, color: AppColors.primary)),
                  Icon(Icons.chevron_right_rounded, size: 16, color: AppColors.primary),
                ],
              ),
            ),
          ],
        ),
        const SizedBox(height: 14),
        Container(
          padding: const EdgeInsets.all(16),
          decoration: BoxDecoration(
            color: AppColors.card,
            borderRadius: BorderRadius.circular(AppRadius.xl),
            border: Border.all(color: AppColors.outline),
          ),
          child: Column(
            children: recentVisits.asMap().entries.map((entry) {
              final i = entry.key;
              final v = entry.value;
              final isLast = i == recentVisits.length - 1;
              final status = v.status as String;
              final color = _statusColor(status);
              final icon = _statusIcon(status);

              return Row(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Column(
                    children: [
                      Container(
                        width: 22,
                        height: 22,
                        decoration: BoxDecoration(
                          color: color.withOpacity(0.15),
                          shape: BoxShape.circle,
                          border: Border.all(color: Colors.white, width: 2),
                        ),
                        child: Icon(icon, size: 12, color: color),
                      ),
                      if (!isLast)
                        Container(width: 2, height: 36, color: AppColors.outline),
                    ],
                  ),
                  const SizedBox(width: 12),
                  Expanded(
                    child: Padding(
                      padding: const EdgeInsets.only(bottom: 8),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(v.locationName as String,
                              style: TextStyle(
                                fontSize: 14,
                                fontWeight: FontWeight.w800,
                                color: status == 'in_progress' ? AppColors.primary : AppColors.onSurface,
                              ),
                              maxLines: 1,
                              overflow: TextOverflow.ellipsis),
                          Text(_statusLabel(status),
                              style: TextStyle(
                                fontSize: 11.5,
                                fontWeight: FontWeight.w600,
                                color: color,
                              )),
                        ],
                      ),
                    ),
                  ),
                ],
              );
            }).toList(),
          ),
        ),
      ],
    );
  }

  Color _statusColor(String s) => switch (s) {
    'COMPLETED' || 'completed' => AppColors.success,
    'IN_PROGRESS' || 'in_progress' => AppColors.warning,
    'MISSED' || 'missed' => AppColors.missed,
    _ => AppColors.info,
  };

  IconData _statusIcon(String s) => switch (s) {
    'COMPLETED' || 'completed' => Icons.check_rounded,
    'IN_PROGRESS' || 'in_progress' => Icons.radio_button_checked,
    'MISSED' || 'missed' => Icons.close_rounded,
    _ => Icons.radio_button_unchecked,
  };

  String _statusLabel(String s) => switch (s) {
    'COMPLETED' || 'completed' => 'Visit completed',
    'IN_PROGRESS' || 'in_progress' => 'Currently in progress',
    'MISSED' || 'missed' => 'Visit missed',
    _ => 'Planned',
  };
}
