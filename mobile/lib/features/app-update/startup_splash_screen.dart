import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../core/theme/app_theme.dart';
import 'app_update_notifier.dart';

/// Motivational quotes displayed during startup
const List<String> _motivationalQuotes = [
  'Success is the sum of small efforts repeated day in and day out.',
  'The only way to do great work is to love what you do.',
  'Don\'t watch the clock; do what it does. Keep going.',
  'Believe you can and you\'re halfway there.',
  'Excellence is not a skill, it\'s an attitude.',
  'Your work is going to fill a large part of your life.',
  'The future depends on what you do today.',
  'Push yourself, because no one else is going to do it for you.',
  'Sometimes we\'re tested not to show our weaknesses, but to discover our strengths.',
  'Great things never come from comfort zones.',
];

/// Splash/Loading screen shown during app startup and auto-update checks
class StartupSplashScreen extends ConsumerStatefulWidget {
  final VoidCallback onComplete;

  const StartupSplashScreen({required this.onComplete});

  @override
  ConsumerState<StartupSplashScreen> createState() => _StartupSplashScreenState();
}

class _StartupSplashScreenState extends ConsumerState<StartupSplashScreen> {
  late String _currentQuote;
  String _statusMessage = 'Initializing...';
  double _progress = 0.0;

  @override
  void initState() {
    super.initState();
    _currentQuote = _motivationalQuotes[
        DateTime.now().microsecond % _motivationalQuotes.length
    ];
    _checkUpdateAndInitialize();
  }

  Future<void> _checkUpdateAndInitialize() async {
    // Always finish within this deadline — never block app startup
    const maxWait = Duration(seconds: 6);
    final deadline = Future.delayed(maxWait);

    final work = _doUpdateCheck();

    // Race: whichever finishes first wins
    await Future.any([work, deadline]);

    if (mounted) {
      setState(() { _statusMessage = 'Ready!'; _progress = 1.0; });
      await Future.delayed(const Duration(milliseconds: 400));
      if (mounted) widget.onComplete();
    }
  }

  Future<void> _doUpdateCheck() async {
    try {
      if (!mounted) return;
      setState(() { _statusMessage = 'Checking for updates...'; _progress = 0.2; });

      final notifier = ref.read(appUpdateProvider.notifier);

      // Timeout the network check at 4s so slow devices don't hang
      await notifier.checkForUpdates().timeout(
        const Duration(seconds: 4),
        onTimeout: () {},
      );

      if (!mounted) return;
      final updateState = ref.read(appUpdateProvider);

      if (updateState.availableVersion != null && updateState.availableVersion!.isMandatory) {
        setState(() { _statusMessage = 'Mandatory update found — downloading...'; _progress = 0.5; });
        // Fire-and-forget: don't block splash on download
        notifier.downloadAndInstall();
        await Future.delayed(const Duration(seconds: 2));
      } else if (updateState.availableVersion != null) {
        setState(() { _statusMessage = 'Update available!'; _progress = 0.7; });
        await Future.delayed(const Duration(milliseconds: 800));
      } else {
        setState(() { _statusMessage = 'All good, loading app...'; _progress = 0.9; });
        await Future.delayed(const Duration(milliseconds: 500));
      }
    } catch (e) {
      // Any crash: log and continue — never block startup
      debugPrint('[Splash] Update check error (non-fatal): $e');
      if (mounted) setState(() => _statusMessage = 'Starting app...');
    }
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: AppColors.primary,
      body: Container(
        width: double.infinity,
        height: double.infinity,
        decoration: const BoxDecoration(
          gradient: LinearGradient(
            colors: AppColors.primaryGradient,
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
          ),
        ),
        child: SingleChildScrollView(
          child: SizedBox(
            height: MediaQuery.of(context).size.height,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                // Top - Logo/Title
                Padding(
                  padding: EdgeInsets.only(top: 80),
                  child: Column(
                    children: [
                      Icon(
                        Icons.bookmark_rounded,
                        size: 64,
                        color: Colors.white,
                      ),
                      SizedBox(height: AppSpacing.md),
                      Text(
                        'Bookmark SFA',
                        textAlign: TextAlign.center,
                        style: Theme.of(context).textTheme.displaySmall?.copyWith(
                              color: Colors.white,
                              fontWeight: FontWeight.bold,
                            ),
                      ),
                      SizedBox(height: 4),
                      Text(
                        'Field Force Management',
                        textAlign: TextAlign.center,
                        style: Theme.of(context).textTheme.bodyMedium?.copyWith(
                              color: Colors.white.withOpacity(0.8),
                            ),
                      ),
                    ],
                  ),
                ),

                // Middle - Quote
                Padding(
                  padding: EdgeInsets.symmetric(
                    horizontal: AppSpacing.lg,
                    vertical: AppSpacing.xl,
                  ),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Icon(
                        Icons.format_quote_rounded,
                        size: 32,
                        color: Colors.white.withOpacity(0.6),
                      ),
                      SizedBox(height: AppSpacing.md),
                      Text(
                        _currentQuote,
                        textAlign: TextAlign.center,
                        style: Theme.of(context).textTheme.titleMedium?.copyWith(
                              color: Colors.white,
                              fontStyle: FontStyle.italic,
                              height: 1.6,
                            ),
                      ),
                    ],
                  ),
                ),

                // Bottom - Progress
                Padding(
                  padding: EdgeInsets.only(
                    left: AppSpacing.lg,
                    right: AppSpacing.lg,
                    bottom: 60,
                  ),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.end,
                    children: [
                      // Progress bar
                      ClipRRect(
                        borderRadius: BorderRadius.circular(4),
                        child: LinearProgressIndicator(
                          value: _progress > 0 ? _progress : null,
                          minHeight: 6,
                          backgroundColor: Colors.white.withOpacity(0.2),
                          valueColor: AlwaysStoppedAnimation<Color>(
                            Colors.white.withOpacity(0.9),
                          ),
                        ),
                      ),
                      SizedBox(height: AppSpacing.md),

                      // Status text
                      Text(
                        _statusMessage,
                        textAlign: TextAlign.center,
                        style: Theme.of(context).textTheme.bodySmall?.copyWith(
                              color: Colors.white.withOpacity(0.85),
                              fontWeight: FontWeight.w500,
                            ),
                      ),

                      // Spinner
                      SizedBox(height: AppSpacing.md),
                      SizedBox(
                        height: 24,
                        width: 24,
                        child: CircularProgressIndicator(
                          strokeWidth: 2,
                          valueColor: AlwaysStoppedAnimation<Color>(
                            Colors.white.withOpacity(0.9),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
