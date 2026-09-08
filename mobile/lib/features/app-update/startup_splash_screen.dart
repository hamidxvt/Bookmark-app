import 'dart:math' as math;

import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../core/theme/app_theme.dart';
import 'app_update_notifier.dart';

const List<String> _motivationalQuotes = [
  'Manage. Visit. Grow.',
  'Your field, your success.',
  'Every visit counts.',
  'Built for the road warrior.',
];

class StartupSplashScreen extends ConsumerStatefulWidget {
  final VoidCallback onComplete;
  const StartupSplashScreen({super.key, required this.onComplete});

  @override
  ConsumerState<StartupSplashScreen> createState() => _StartupSplashScreenState();
}

class _StartupSplashScreenState extends ConsumerState<StartupSplashScreen>
    with TickerProviderStateMixin {
  late AnimationController _routeCtrl;   // path draw animation
  late AnimationController _dotCtrl;     // pulsing dot
  String _statusText = '';
  int _activeDot = 1;

  @override
  void initState() {
    super.initState();
    _routeCtrl = AnimationController(vsync: this, duration: const Duration(milliseconds: 1400))
      ..forward();
    _dotCtrl = AnimationController(vsync: this, duration: const Duration(milliseconds: 900))
      ..repeat(reverse: true);
    _runStartup();
  }

  @override
  void dispose() {
    _routeCtrl.dispose();
    _dotCtrl.dispose();
    super.dispose();
  }

  Future<void> _runStartup() async {
    const maxDuration = Duration(seconds: 7);
    await Future.any([_doWork(), Future.delayed(maxDuration)]);
    if (!mounted) return;
    setState(() { _statusText = ''; _activeDot = 2; });
    await Future.delayed(const Duration(milliseconds: 400));
    if (mounted) widget.onComplete();
  }

  Future<void> _doWork() async {
    try {
      if (mounted) setState(() { _statusText = 'Checking for updates…'; _activeDot = 0; });
      final notifier = ref.read(appUpdateProvider.notifier);
      await notifier.checkForUpdates().timeout(const Duration(seconds: 4), onTimeout: () {});
      if (!mounted) return;
      final st = ref.read(appUpdateProvider);
      if (st.availableVersion != null && st.availableVersion!.isMandatory) {
        if (mounted) setState(() { _statusText = 'Downloading update…'; _activeDot = 1; });
        notifier.downloadAndInstall();
        await Future.delayed(const Duration(seconds: 2));
      } else {
        if (mounted) setState(() { _statusText = ''; _activeDot = 1; });
        await Future.delayed(const Duration(milliseconds: 600));
      }
    } catch (e) {
      debugPrint('[Splash] non-fatal: $e');
      if (mounted) setState(() { _statusText = ''; _activeDot = 1; });
    }
  }

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Scaffold(
      body: Container(
        width: double.infinity,
        height: double.infinity,
        decoration: const BoxDecoration(
          gradient: LinearGradient(
            colors: [Color(0xFFD44B60), Color(0xFFC8102E), Color(0xFF7A1020)],
            begin: Alignment.topCenter,
            end: Alignment.bottomCenter,
          ),
        ),
        child: Stack(
          children: [
            // ── Scattered sparkle dots ────────────────────────────────────
            Positioned.fill(child: CustomPaint(painter: _SpecklePainter())),

            // ── Route path drawn from top ─────────────────────────────────
            Positioned(
              top: 0,
              left: 0,
              right: 0,
              height: size.height * 0.52,
              child: AnimatedBuilder(
                animation: _routeCtrl,
                builder: (_, __) => CustomPaint(
                  painter: _RoutePainter(progress: _routeCtrl.value),
                ),
              ),
            ),

            // ── Main content ──────────────────────────────────────────────
            SafeArea(
              child: Column(
                children: [
                  // Top spacer so route has room
                  SizedBox(height: size.height * 0.28),

                  // Logo card (white rounded square)
                  Container(
                    width: 120,
                    height: 120,
                    decoration: BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.circular(30),
                      boxShadow: [
                        BoxShadow(
                          color: Colors.black.withOpacity(0.22),
                          blurRadius: 40,
                          offset: const Offset(0, 14),
                        ),
                      ],
                    ),
                    padding: const EdgeInsets.all(18),
                    child: Image.asset(
                      'assets/images/logo.png',
                      fit: BoxFit.contain,
                      errorBuilder: (_, __, ___) => const Icon(
                        Icons.bookmark_rounded,
                        color: AppColors.primary,
                        size: 56,
                      ),
                    ),
                  )
                      .animate(controller: _routeCtrl)
                      .scale(begin: const Offset(0.6, 0.6), end: const Offset(1, 1),
                          duration: 700.ms, delay: 600.ms, curve: Curves.elasticOut)
                      .fadeIn(duration: 400.ms, delay: 600.ms),

                  const SizedBox(height: 28),

                  // "BOOKMARK"
                  const Text(
                    'BOOKMARK',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 28,
                      fontWeight: FontWeight.w900,
                      letterSpacing: 4,
                    ),
                  )
                      .animate()
                      .fadeIn(delay: 900.ms, duration: 500.ms)
                      .slideY(begin: 0.2, end: 0),

                  const SizedBox(height: 6),

                  // "FIELD FORCE PRO"
                  Text(
                    'FIELD FORCE PRO',
                    style: TextStyle(
                      color: Colors.white.withOpacity(0.8),
                      fontSize: 13,
                      fontWeight: FontWeight.w600,
                      letterSpacing: 3,
                    ),
                  ).animate().fadeIn(delay: 1000.ms, duration: 500.ms),

                  const SizedBox(height: 10),

                  // Tagline
                  Text(
                    'Manage. Visit. Grow.',
                    style: TextStyle(
                      color: Colors.white.withOpacity(0.6),
                      fontSize: 13,
                      fontWeight: FontWeight.w400,
                      letterSpacing: 0.5,
                    ),
                  ).animate().fadeIn(delay: 1100.ms, duration: 500.ms),

                  const Spacer(),

                  // Status text (only when updating)
                  if (_statusText.isNotEmpty)
                    Padding(
                      padding: const EdgeInsets.only(bottom: 12),
                      child: Text(
                        _statusText,
                        style: TextStyle(
                          color: Colors.white.withOpacity(0.7),
                          fontSize: 12,
                          fontWeight: FontWeight.w500,
                        ),
                      ).animate().fadeIn(duration: 300.ms),
                    ),

                  // Pagination dots
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: List.generate(3, (i) {
                      final active = i == _activeDot;
                      return AnimatedContainer(
                        duration: const Duration(milliseconds: 300),
                        margin: const EdgeInsets.symmetric(horizontal: 4),
                        width: active ? 22 : 7,
                        height: 7,
                        decoration: BoxDecoration(
                          color: active ? Colors.white : Colors.white.withOpacity(0.35),
                          borderRadius: BorderRadius.circular(4),
                        ),
                      );
                    }),
                  ).animate().fadeIn(delay: 1200.ms, duration: 400.ms),

                  const SizedBox(height: 40),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}

/// Draws the curved route path + house icon + moving dot
class _RoutePainter extends CustomPainter {
  final double progress;
  const _RoutePainter({required this.progress});

  @override
  void paint(Canvas canvas, Size size) {
    final linePaint = Paint()
      ..color = Colors.white.withOpacity(0.55)
      ..style = PaintingStyle.stroke
      ..strokeWidth = 2.5
      ..strokeCap = StrokeCap.round;

    // Bezier path: from house (top-center) curving down to logo position
    final startX = size.width * 0.55;
    final startY = size.height * 0.12;
    final endX = size.width * 0.5;
    final endY = size.height * 0.88;

    final path = Path()
      ..moveTo(startX, startY)
      ..cubicTo(
        startX + size.width * 0.25, startY + size.height * 0.15,
        startX - size.width * 0.30, startY + size.height * 0.45,
        endX, endY,
      );

    // Extract point along path at progress
    final metrics = path.computeMetrics().first;
    final drawLen = metrics.length * progress.clamp(0.0, 1.0);
    final partialPath = metrics.extractPath(0, drawLen);

    canvas.drawPath(partialPath, linePaint);

    // House icon at start
    _drawHouseIcon(canvas, Offset(startX, startY));

    // Moving dot at tip of path
    if (progress > 0.05) {
      final tang = metrics.getTangentForOffset(drawLen);
      if (tang != null) {
        final dotPos = tang.position;
        final dotPaint = Paint()
          ..color = Colors.white.withOpacity(0.9)
          ..style = PaintingStyle.fill;
        final ringPaint = Paint()
          ..color = Colors.white.withOpacity(0.3)
          ..style = PaintingStyle.stroke
          ..strokeWidth = 2;
        canvas.drawCircle(dotPos, 5, dotPaint);
        canvas.drawCircle(dotPos, 9, ringPaint);
      }
    }
  }

  void _drawHouseIcon(Canvas canvas, Offset center) {
    final p = Paint()
      ..color = Colors.white.withOpacity(0.8)
      ..style = PaintingStyle.stroke
      ..strokeWidth = 1.8
      ..strokeCap = StrokeCap.round
      ..strokeJoin = StrokeJoin.round;

    const s = 11.0;
    final x = center.dx;
    final y = center.dy;

    // Roof
    final roof = Path()
      ..moveTo(x - s, y)
      ..lineTo(x, y - s)
      ..lineTo(x + s, y);
    canvas.drawPath(roof, p);

    // Walls
    canvas.drawRect(Rect.fromLTWH(x - s * 0.7, y, s * 1.4, s), p);

    // Door
    canvas.drawRect(Rect.fromLTWH(x - s * 0.22, y + s * 0.35, s * 0.44, s * 0.65), p);
  }

  @override
  bool shouldRepaint(_RoutePainter old) => old.progress != progress;
}

/// Random subtle sparkle dots scattered across background
class _SpecklePainter extends CustomPainter {
  @override
  void paint(Canvas canvas, Size size) {
    final rng = math.Random(42);
    final paint = Paint()..style = PaintingStyle.fill;
    for (int i = 0; i < 40; i++) {
      final x = rng.nextDouble() * size.width;
      final y = rng.nextDouble() * size.height;
      final r = rng.nextDouble() * 2.5 + 0.5;
      paint.color = Colors.white.withOpacity(rng.nextDouble() * 0.12 + 0.04);
      canvas.drawCircle(Offset(x, y), r, paint);
    }
  }

  @override
  bool shouldRepaint(_) => false;
}
