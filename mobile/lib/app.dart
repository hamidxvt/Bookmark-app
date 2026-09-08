import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';

import 'core/services/fcm_service.dart' show navigatorKey;

import 'core/constants/app_constants.dart';
import 'features/app-update/update_notification_widget.dart';
import 'features/app-update/startup_splash_screen.dart';
import 'core/theme/app_theme.dart';
import 'features/auth/presentation/auth_notifier.dart';
import 'features/auth/presentation/login_screen.dart';
import 'features/auth/presentation/forgot_password_screen.dart';
import 'features/customers/presentation/customer_detail_screen.dart';
import 'features/dashboard/presentation/dashboard_screen.dart';
import 'features/visits/presentation/add_customer_screen.dart';
import 'features/leaves/presentation/leaves_screen.dart';
import 'features/samples/presentation/samples_screen.dart';
import 'features/visits/presentation/visit_list_screen.dart';
import 'features/visits/presentation/complete_visit_screen.dart';
import 'features/visits/presentation/missed_visit_screen.dart';
import 'features/workday/presentation/day_start_screen.dart';
import 'features/workday/presentation/day_end_screen.dart';
import 'features/payroll/presentation/payroll_screen.dart';
import 'features/map/presentation/route_map_screen.dart';
import 'features/profile/presentation/profile_screen.dart';

// Use FCM navigatorKey as the root so push notifications can navigate
final _shellNavigatorKey = GlobalKey<NavigatorState>(debugLabel: 'shell');

final _routerProvider = Provider<GoRouter>((ref) {
  final notifier = _AuthNotifierListenable(ref);
  return GoRouter(
    navigatorKey: navigatorKey,
    refreshListenable: notifier,
    initialLocation: '/login',
    redirect: (context, state) {
      final auth = ref.read(authProvider);
      if (auth.isRestoring) return null;
      final loggedIn = auth.isAuthenticated;
      final onLogin = state.matchedLocation == '/login';
      if (!loggedIn && !onLogin) return '/login';
      if (loggedIn && onLogin) return '/dashboard';
      return null;
    },
    routes: [
      // ── Auth routes (no bottom nav) ────────────────────────────────────
      GoRoute(path: '/login',           builder: (_, __) => const LoginScreen()),
      GoRoute(path: '/forgot-password', builder: (_, __) => const ForgotPasswordScreen()),

      // ── Full-screen routes (no bottom nav) ────────────────────────────
      GoRoute(path: '/day-start', builder: (_, __) => const DayStartScreen()),
      GoRoute(path: '/day-end',   builder: (_, __) => const DayEndScreen()),
      GoRoute(path: '/payroll',   builder: (_, __) => const PayrollScreen()),
      GoRoute(path: '/map',       builder: (_, __) => const RouteMapScreen()),
      GoRoute(path: '/leaves',    builder: (_, __) => const LeavesScreen()),
      GoRoute(
        path: '/visits/:id/complete',
        builder: (_, state) => CompleteVisitScreen(
            visitId: int.parse(state.pathParameters['id']!)),
      ),
      GoRoute(
        path: '/visits/:id/missed',
        builder: (_, state) => MissedVisitScreen(
            visitId: int.parse(state.pathParameters['id']!)),
      ),
      GoRoute(
        path: '/customers/:id',
        builder: (_, state) => CustomerDetailScreen(
            customerId: int.parse(state.pathParameters['id']!)),
      ),
      GoRoute(
        path: '/visits/:id/add-customer',
        builder: (_, state) => AddCustomerScreen(
            visitId: int.parse(state.pathParameters['id']!)),
      ),

      // ── Main shell with bottom nav ─────────────────────────────────────
      ShellRoute(
        navigatorKey: _shellNavigatorKey,
        builder: (context, state, child) => _MainShell(child: child),
        routes: [
          GoRoute(path: '/dashboard', builder: (_, __) => const DashboardScreen()),
          GoRoute(path: '/visits',    builder: (_, __) => const VisitListScreen()),
          GoRoute(path: '/samples',   builder: (_, __) => const SamplesScreen()),
          GoRoute(path: '/profile',   builder: (_, __) => const ProfileScreen()),
        ],
      ),
    ],
  );
});

// ── Main Shell with bottom navigation ────────────────────────────────────────
class _MainShell extends StatelessWidget {
  final Widget child;
  const _MainShell({required this.child});

  static const _tabs = ['/dashboard', '/visits', '/samples', '/profile'];
  static const _icons = [
    Icons.home_outlined,
    Icons.checklist_rtl_outlined,
    Icons.science_outlined,
    Icons.person_outline_rounded,
  ];
  static const _activeIcons = [
    Icons.home_rounded,
    Icons.checklist_rtl_rounded,
    Icons.science_rounded,
    Icons.person_rounded,
  ];
  static const _labels = ['Home', 'Visits', 'Samples', 'Profile'];

  int _currentIndex(String location) {
    for (int i = _tabs.length - 1; i >= 0; i--) {
      if (location.startsWith(_tabs[i])) return i;
    }
    return 0;
  }

  @override
  Widget build(BuildContext context) {
    final location = GoRouterState.of(context).matchedLocation;
    final index = _currentIndex(location);

    return Scaffold(
      backgroundColor: AppColors.background,
      body: child,
      bottomNavigationBar: Container(
        decoration: BoxDecoration(
          color: AppColors.card,
          border: const Border(top: BorderSide(color: AppColors.outline, width: 0.8)),
          boxShadow: [
            BoxShadow(
              color: Colors.black.withOpacity(0.06),
              blurRadius: 16,
              offset: const Offset(0, -4),
            ),
          ],
        ),
        child: SafeArea(
          top: false,
          child: SizedBox(
            height: 62,
            child: Row(
              children: List.generate(_tabs.length, (i) {
                final isActive = index == i;
                return Expanded(
                  child: GestureDetector(
                    onTap: () => context.go(_tabs[i]),
                    behavior: HitTestBehavior.opaque,
                    child: AnimatedContainer(
                      duration: const Duration(milliseconds: 200),
                      padding: const EdgeInsets.symmetric(vertical: 6),
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Icon(
                            isActive ? _activeIcons[i] : _icons[i],
                            size: 22,
                            color: isActive ? AppColors.primary : AppColors.textMuted,
                          ),
                          const SizedBox(height: 3),
                          Text(
                            _labels[i],
                            style: TextStyle(
                              fontSize: 10.5,
                              fontWeight: isActive ? FontWeight.w700 : FontWeight.w600,
                              color: isActive ? AppColors.primary : AppColors.textMuted,
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                );
              }),
            ),
          ),
        ),
      ),
    );
  }
}

class _AuthNotifierListenable extends ChangeNotifier {
  _AuthNotifierListenable(ProviderRef ref) {
    ref.listen(authProvider, (_, __) => notifyListeners());
  }
}

class BookmarkSFAApp extends ConsumerStatefulWidget {
  const BookmarkSFAApp({super.key});

  @override
  ConsumerState<BookmarkSFAApp> createState() => _BookmarkSFAAppState();
}

class _BookmarkSFAAppState extends ConsumerState<BookmarkSFAApp> {
  bool _splashComplete = false;

  @override
  Widget build(BuildContext context) {
    final authState = ref.watch(authProvider);
    final router = ref.watch(_routerProvider);

    // Show splash screen (quotes + update check) before main app
    if (!_splashComplete) {
      return MaterialApp(
        debugShowCheckedModeBanner: false,
        theme: AppTheme.light,
        home: StartupSplashScreen(
          onComplete: () {
            if (mounted) setState(() => _splashComplete = true);
          },
        ),
      );
    }

    if (authState.isRestoring) {
      return MaterialApp(
        debugShowCheckedModeBanner: false,
        theme: AppTheme.light,
        home: const Scaffold(
          backgroundColor: Color(0xFFC8102E),
          body: Center(
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                Icon(Icons.bookmark_rounded, color: Colors.white, size: 52),
                SizedBox(height: 24),
                CircularProgressIndicator(color: Colors.white70, strokeWidth: 2),
              ],
            ),
          ),
        ),
      );
    }

    return UpdateNotificationListener(
      child: MaterialApp.router(
        title: AppConstants.appName,
        debugShowCheckedModeBanner: false,
        theme: AppTheme.light,
        routerConfig: router,
      ),
    );
  }
}
