import 'dart:math' as math;

import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../core/theme/app_theme.dart';
import 'app_update_notifier.dart';

const List<String> _motivationalQuotes = [
  'Success is the sum of small efforts\nrepeated day in and day out.',
  'The only way to do great work\nis to love what you do.',
  'Don\'t watch the clock — do what it does.\nKeep going.',
  'Believe you can and\nyou\'re halfway there.',
  'Excellence is not a skill,\nit\'s an attitude.',
  'Push yourself, because no one else\nis going to do it for you.',
  'Great things never come\nfrom comfort zones.',
  'The future depends\non what you do today.',
];

class StartupSplashScreen extends ConsumerStatefulWidget {
  final VoidCallback onComplete;
  const StartupSplashScreen({super.key, required this.onComplete});

  @override
  ConsumerState<StartupSplashScreen> createState() => _StartupSplashScreenState();
}

class _StartupSplashScreenState extends ConsumerState<StartupSplashScreen>
    with SingleTickerProviderStateMixin {
  late AnimationController _pulseCtrl;
  late String _quote;
  String _statusText = 'Initializing…';
  double _progress = 0.0;

  @override
  void initState() {
    super.initState();
    _quote = _motivationalQuotes[math.Random().nextInt(_motivationalQuotes.length)];
    _pulseCtrl = AnimationController(
      vsync: this,
      duration: const Duration(milliseconds: 1600),
    )..repeat(reverse: true);
    _runStartup();
  }

  @override
  void dispose() {
    _pulseCtrl.dispose();
    super.dispose();
  }

  Future<void> _runStartup() async {
    const maxDuration = Duration(seconds: 7);
    final deadline = Future.delayed(maxDuration);
    final work = _doWork();
    await Future.any([work, deadline]);
    if (!mounted) return;
    _setStatus('All set!', 1.0);
    await Future.delayed(const Duration(milliseconds: 500));
    if (mounted) widget.onComplete();
  }

  Future<void> _doWork() async {
    try {
      _setStatus('Checking for updates…', 0.25);
      final notifier = ref.read(appUpdateProvider.notifier);
      await notifier.checkForUpdates().timeout(
        const Duration(seconds: 4),
        onTimeout: () {},
      );
      if (!mounted) return;

      final st = ref.read(appUpdateProvider);
      if (st.availableVersion != null && st.availableVersion!.isMandatory) {
        _setStatus('Downloading update…', 0.55);
        notifier.downloadAndInstall();
        await Future.delayed(const Duration(seconds: 2));
      } else if (st.availableVersion != null) {
        _setStatus('Update available!', 0.75);
        await Future.delayed(const Duration(milliseconds: 800));
      } else {
        _setStatus('Loading your workspace…', 0.90);
        await Future.delayed(const Duration(milliseconds: 600));
      }
    } catch (e) {
      debugPrint('[Splash] Non-fatal: $e');
      if (mounted) _setStatus('Starting app…', 0.85);
      await Future.delayed(const Duration(milliseconds: 400));
    }
  }

  void _setStatus(String msg, double pct) {
    if (!mounted) return;
    setState(() {
      _statusText = msg;
      _progress = pct;
    });
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
            colors: [Color(0xFFE8304A), Color(0xFFC8102E), Color(0xFF7A0B1E)],
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
          ),
        ),
        child: Stack(
          children: [
            // ── Animated dot grid background ───────────────────────────
            Positioned.fill(
              child: CustomPaint(painter: _DotGridPainter()),
            ),

            // ── Glowing orb (top-center) ───────────────────────────────
            Positioned(
              top: -size.height * 0.15,
              left: size.width * 0.5 - 200,
              child: AnimatedBuilder(
                animation: _pulseCtrl,
                builder: (_, __) => Container(
                  width: 400,
                  height: 400,
                  decoration: BoxDecoration(
                    shape: BoxShape.circle,
                    gradient: RadialGradient(
                      colors: [
                        Colors.white.withOpacity(0.18 + _pulseCtrl.value * 0.08),
                        Colors.transparent,
                      ],
                    ),
                  ),
                ),
              ),
            ),

            // ── Content ────────────────────────────────────────────────
            SafeArea(
              child: Padding(
                padding: const EdgeInsets.symmetric(horizontal: 32),
                child: Column(
                  children: [
                    const Spacer(flex: 2),

                    // Logo
                    Container(
                      width: 88,
                      height: 88,
                      decoration: BoxDecoration(
                        color: Colors.white,
                        borderRadius: BorderRadius.circular(28),
                        boxShadow: [
                          BoxShadow(
                            color: Colors.black.withOpacity(0.25),
                            blurRadius: 32,
                            offset: const Offset(0, 12),
                          ),
                        ],
                      ),
                      padding: const EdgeInsets.all(14),
                      child: Image.asset(
                        'assets/images/logo.png',
                        fit: BoxFit.contain,
                        errorBuilder: (_, __, ___) => const Icon(
                          Icons.bookmark_rounded,
                          color: AppColors.primary,
                          size: 44,
                        ),
                      ),
                    )
                        .animate()
                        .scale(begin: const Offset(0.7, 0.7), end: const Offset(1, 1), duration: 700.ms, curve: Curves.elasticOut)
                        .fadeIn(duration: 500.ms),

                    const SizedBox(height: 24),

                    // App name
                    const Text(
                      'BOOKMARK',
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 32,
                        fontWeight: FontWeight.w900,
                        letterSpacing: 2.5,
                      ),
                    )
                        .animate()
                        .fadeIn(delay: 300.ms, duration: 600.ms)
                        .slideY(begin: 0.2, end: 0),

                    const SizedBox(height: 6),

                    Text(
                      'Field Force Pro',
                      style: TextStyle(
                        color: Colors.white.withOpacity(0.7),
                        fontSize: 15,
                        fontWeight: FontWeight.w500,
                        letterSpacing: 1.0,
                      ),
                    )
                        .animate()
                        .fadeIn(delay: 400.ms, duration: 600.ms),

                    const Spacer(flex: 2),

                    // Quote card
                    Container(
                      padding: const EdgeInsets.all(24),
                      decoration: BoxDecoration(
                        color: Colors.white.withOpacity(0.1),
                        borderRadius: BorderRadius.circular(24),
                        border: Border.all(color: Colors.white.withOpacity(0.15)),
                      ),
                      child: Column(
                        children: [
                          Icon(
                            Icons.format_quote_rounded,
                            color: Colors.white.withOpacity(0.5),
                            size: 28,
                          ),
                          const SizedBox(height: 12),
                          Text(
                            _quote,
                            textAlign: TextAlign.center,
                            style: TextStyle(
                              color: Colors.white.withOpacity(0.9),
                              fontSize: 15.5,
                              fontWeight: FontWeight.w500,
                              fontStyle: FontStyle.italic,
                              height: 1.65,
                            ),
                          ),
                        ],
                      ),
                    )
                        .animate()
                        .fadeIn(delay: 600.ms, duration: 700.ms)
                        .slideY(begin: 0.15, end: 0),

                    const Spacer(flex: 3),

                    // Progress section
                    Column(
                      children: [
                        // GPS route illustration (decorative dots)
                        Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: List.generate(5, (i) {
                            final filled = (_progress * 5).ceil() > i;
                            return AnimatedContainer(
                              duration: const Duration(milliseconds: 300),
                              margin: const EdgeInsets.symmetric(horizontal: 4),
                              width: filled ? 24 : 8,
                              height: 8,
                              decoration: BoxDecoration(
                                color: filled ? Colors.white : Colors.white.withOpacity(0.3),
                                borderRadius: BorderRadius.circular(4),
                              ),
                            );
                          }),
                        ).animate().fadeIn(delay: 800.ms),

                        const SizedBox(height: 16),

                        // Progress bar
                        ClipRRect(
                          borderRadius: BorderRadius.circular(6),
                          child: LinearProgressIndicator(
                            value: _progress > 0 ? _progress : null,
                            minHeight: 5,
                            backgroundColor: Colors.white.withOpacity(0.2),
                            valueColor: const AlwaysStoppedAnimation<Color>(Colors.white),
                          ),
                        ).animate().fadeIn(delay: 900.ms),

                        const SizedBox(height: 14),

                        // Status text
                        Text(
                          _statusText,
                          style: TextStyle(
                            color: Colors.white.withOpacity(0.8),
                            fontSize: 13,
                            fontWeight: FontWeight.w600,
                          ),
                        ).animate().fadeIn(delay: 900.ms),

                        const SizedBox(height: 8),
                      ],
                    ),

                    const SizedBox(height: 24),

                    // Footer
                    Text(
                      '© 2026 Bookmark Publishing',
                      style: TextStyle(
                        color: Colors.white.withOpacity(0.4),
                        fontSize: 11,
                      ),
                    ),

                    const SizedBox(height: 16),
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

class _DotGridPainter extends CustomPainter {
  @override
  void paint(Canvas canvas, Size size) {
    final paint = Paint()
      ..color = Colors.white.withOpacity(0.06)
      ..style = PaintingStyle.fill;
    const spacing = 22.0;
    for (double x = 0; x < size.width + spacing; x += spacing) {
      for (double y = 0; y < size.height + spacing; y += spacing) {
        canvas.drawCircle(Offset(x, y), 1.2, paint);
      }
    }
  }

  @override
  bool shouldRepaint(_) => false;
}
