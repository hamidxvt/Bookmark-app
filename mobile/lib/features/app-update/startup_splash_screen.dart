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
  String _statusMessage = 'Checking for updates...';
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
    try {
      // Check for updates
      setState(() => _statusMessage = 'Checking for updates...');
      await Future.delayed(const Duration(milliseconds: 800));

      final notifier = ref.read(appUpdateProvider.notifier);
      await notifier.checkForUpdates();

      final state = ref.read(appUpdateProvider);
      if (state.availableVersion != null && state.availableVersion!.isMandatory) {
        // Mandatory update available - show update dialog
        setState(() => _statusMessage = 'Update required. Downloading...');
        await Future.delayed(const Duration(milliseconds: 500));
        notifier.downloadAndInstall();
        // Wait for download to complete
        await Future.delayed(const Duration(seconds: 5));
      } else if (state.availableVersion != null) {
        // Optional update available
        setState(() {
          _statusMessage = 'Optional update available';
          _progress = 0.5;
        });
        await Future.delayed(const Duration(milliseconds: 1000));
      }

      // Complete initialization
      setState(() {
        _statusMessage = 'Ready!';
        _progress = 1.0;
      });

      await Future.delayed(const Duration(milliseconds: 600));
      if (mounted) {
        widget.onComplete();
      }
    } catch (e) {
      debugPrint('[Startup Splash] Error: $e');
      // Continue anyway
      setState(() => _statusMessage = 'Ready!');
      await Future.delayed(const Duration(milliseconds: 800));
      if (mounted) {
        widget.onComplete();
      }
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
