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
      GoRoute(path: '/login',     builder: (_, __) => const LoginScreen()),
      GoRoute(path: '/forgot-password', builder: (_, __) => const ForgotPasswordScreen()),
      GoRoute(path: '/dashboard', builder: (_, __) => const DashboardScreen()),
      GoRoute(path: '/day-start', builder: (_, __) => const DayStartScreen()),
      GoRoute(path: '/day-end',   builder: (_, __) => const DayEndScreen()),
      GoRoute(
        path: '/visits',
        builder: (_, __) => const VisitListScreen(),
        routes: [
          GoRoute(
            path: ':id/complete',
            builder: (_, state) => CompleteVisitScreen(
                visitId: int.parse(state.pathParameters['id']!)),
          ),
          GoRoute(
            path: ':id/missed',
            builder: (_, state) => MissedVisitScreen(
                visitId: int.parse(state.pathParameters['id']!)),
          ),
        ],
      ),
      GoRoute(path: '/payroll',  builder: (_, __) => const PayrollScreen()),
      GoRoute(path: '/map',      builder: (_, __) => const RouteMapScreen()),
      GoRoute(path: '/profile',  builder: (_, __) => const ProfileScreen()),
      GoRoute(path: '/leaves',   builder: (_, __) => const LeavesScreen()),
      GoRoute(path: '/samples',  builder: (_, __) => const SamplesScreen()),
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
    ],
  );
});

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
