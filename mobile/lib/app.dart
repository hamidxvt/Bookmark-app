import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';

import 'core/constants/app_constants.dart';
import 'presentation/providers/auth_provider.dart';
import 'presentation/screens/auth/login_screen.dart';
import 'presentation/screens/dashboard/dashboard_screen.dart';
import 'presentation/screens/workday/day_start_screen.dart';
import 'presentation/screens/workday/day_end_screen.dart';
import 'presentation/screens/visits/visit_list_screen.dart';
import 'presentation/screens/visits/complete_visit_screen.dart';
import 'presentation/screens/visits/missed_visit_screen.dart';
import 'presentation/screens/samples/samples_screen.dart';
import 'presentation/screens/leaves/leaves_screen.dart';
import 'presentation/screens/splash/splash_screen.dart';

class BookmarkSFAApp extends ConsumerWidget {
  const BookmarkSFAApp({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final router = _buildRouter(ref);
    return MaterialApp.router(
      title: AppConstants.appName,
      debugShowCheckedModeBanner: false,
      theme: _buildTheme(),
      routerConfig: router,
    );
  }

  GoRouter _buildRouter(WidgetRef ref) {
    return GoRouter(
      initialLocation: '/splash',
      redirect: (context, state) {
        final isLoggedIn = ref.read(authProvider).isAuthenticated;
        final isOnAuth = state.matchedLocation == '/login';
        final isOnSplash = state.matchedLocation == '/splash';
        if (isOnSplash) return null;
        if (!isLoggedIn && !isOnAuth) return '/login';
        if (isLoggedIn && isOnAuth) return '/dashboard';
        return null;
      },
      routes: [
        GoRoute(path: '/splash', builder: (_, __) => const SplashScreen()),
        GoRoute(path: '/login', builder: (_, __) => const LoginScreen()),
        GoRoute(path: '/dashboard', builder: (_, __) => const DashboardScreen()),
        GoRoute(path: '/day-start', builder: (_, __) => const DayStartScreen()),
        GoRoute(path: '/day-end', builder: (_, __) => const DayEndScreen()),
        GoRoute(path: '/visits', builder: (_, __) => const VisitListScreen()),
        GoRoute(
          path: '/visits/:id/complete',
          builder: (_, state) => CompleteVisitScreen(visitId: int.parse(state.pathParameters['id']!)),
        ),
        GoRoute(
          path: '/visits/:id/missed',
          builder: (_, state) => MissedVisitScreen(visitId: int.parse(state.pathParameters['id']!)),
        ),
        GoRoute(path: '/samples', builder: (_, __) => const SamplesScreen()),
        GoRoute(path: '/leaves', builder: (_, __) => const LeavesScreen()),
      ],
    );
  }

  ThemeData _buildTheme() {
    return ThemeData(
      colorScheme: ColorScheme.fromSeed(
        seedColor: const Color(0xFF1B4F72),
        brightness: Brightness.light,
      ),
      useMaterial3: true,
      appBarTheme: const AppBarTheme(centerTitle: true, elevation: 0),
    );
  }
}
