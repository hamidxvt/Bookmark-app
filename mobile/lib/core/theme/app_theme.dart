import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

/// Bookmark SFA — Design System
/// Primary: Bookmark Red #C8102E  Accent: Dark Red #9B0B22  Background: White
abstract class AppColors {
  static const primary = Color(0xFFC8102E);
  static const primaryContainer = Color(0xFF9B0B22);
  static const secondary = Color(0xFFE63946);
  static const secondaryContainer = Color(0xFFFF6B7A);
  static const accent = Color(0xFFC8102E);
  static const surface = Color(0xFFFFFFFF);
  static const background = Color(0xFFFFF5F5);
  static const error = Color(0xFF9B0B22);
  static const onPrimary = Color(0xFFFFFFFF);
  static const onSecondary = Color(0xFFFFFFFF);
  static const onSurface = Color(0xFF1A0A0A);
  static const onBackground = Color(0xFF3D1515);
  static const outline = Color(0xFFEECCCC);
  static const success = Color(0xFF16A34A);
  static const warning = Color(0xFFD97706);
  static const info = Color(0xFF0EA5E9);
  static const textMuted = Color(0xFF9B6B6B);

  // Status badge colors
  static const planned = Color(0xFF0EA5E9);
  static const inProgress = Color(0xFFD97706);
  static const completed = Color(0xFF16A34A);
  static const missed = Color(0xFFC8102E);

  // Gradient pairs
  static const List<Color> primaryGradient = [Color(0xFFC8102E), Color(0xFF9B0B22)];
  static const List<Color> accentGradient = [Color(0xFFE63946), Color(0xFFC8102E)];
  static const List<Color> cardGradient = [Color(0xFFFFFFFF), Color(0xFFFFF5F5)];
}

abstract class AppSpacing {
  static const double xs = 4;
  static const double sm = 8;
  static const double md = 16;
  static const double lg = 24;
  static const double xl = 32;
  static const double xxl = 48;
}

abstract class AppRadius {
  static const double sm = 8;
  static const double md = 12;
  static const double lg = 16;
  static const double xl = 24;
  static const double full = 999;
}

class AppTheme {
  AppTheme._();

  // Web-optimized responsive breakpoints
  static const double mobileMax = 600;
  static const double tabletMax = 1200;
  static bool isMobile(double width) => width <= mobileMax;
  static bool isTablet(double width) => width > mobileMax && width <= tabletMax;
  static bool isDesktop(double width) => width > tabletMax;

  static ThemeData get light {
    final base = ThemeData(
      useMaterial3: true,
      colorScheme: const ColorScheme(
        brightness: Brightness.light,
        primary: AppColors.primary,
        onPrimary: AppColors.onPrimary,
        primaryContainer: Color(0xFFFFDADD),
        onPrimaryContainer: Color(0xFF40000A),
        secondary: AppColors.secondary,
        onSecondary: AppColors.onSecondary,
        secondaryContainer: Color(0xFFFFCDD2),
        onSecondaryContainer: Color(0xFF5C0011),
        tertiary: Color(0xFF7C3AED),
        onTertiary: AppColors.onPrimary,
        tertiaryContainer: Color(0xFFEDE9FE),
        onTertiaryContainer: Color(0xFF2E1065),
        error: AppColors.error,
        onError: AppColors.onPrimary,
        errorContainer: Color(0xFFFFDADD),
        onErrorContainer: Color(0xFF40000A),
        surface: AppColors.surface,
        onSurface: AppColors.onSurface,
        surfaceContainerHighest: Color(0xFFFFEEEE),
        outline: AppColors.outline,
        outlineVariant: Color(0xFFFFDDDD),
        shadow: Color(0x1A200000),
        scrim: Color(0x80C8102E),
        inverseSurface: Color(0xFF3D1515),
        onInverseSurface: Color(0xFFFFF5F5),
        inversePrimary: Color(0xFFFF8A9B),
      ),
    );

    final textTheme = GoogleFonts.interTextTheme(base.textTheme).copyWith(
      displayLarge: GoogleFonts.inter(
          fontSize: 57, fontWeight: FontWeight.w400, color: AppColors.onSurface, letterSpacing: -0.25),
      displayMedium: GoogleFonts.inter(
          fontSize: 45, fontWeight: FontWeight.w400, color: AppColors.onSurface),
      displaySmall: GoogleFonts.inter(
          fontSize: 36, fontWeight: FontWeight.w400, color: AppColors.onSurface),
      headlineLarge: GoogleFonts.inter(
          fontSize: 32, fontWeight: FontWeight.w700, color: AppColors.onSurface),
      headlineMedium: GoogleFonts.inter(
          fontSize: 28, fontWeight: FontWeight.w600, color: AppColors.onSurface),
      headlineSmall: GoogleFonts.inter(
          fontSize: 24, fontWeight: FontWeight.w600, color: AppColors.onSurface),
      titleLarge: GoogleFonts.inter(
          fontSize: 22, fontWeight: FontWeight.w600, color: AppColors.onSurface),
      titleMedium: GoogleFonts.inter(
          fontSize: 16, fontWeight: FontWeight.w600, color: AppColors.onSurface, letterSpacing: 0.15),
      titleSmall: GoogleFonts.inter(
          fontSize: 14, fontWeight: FontWeight.w500, color: AppColors.onSurface, letterSpacing: 0.1),
      bodyLarge: GoogleFonts.inter(
          fontSize: 16, fontWeight: FontWeight.w400, color: AppColors.onSurface, letterSpacing: 0.5),
      bodyMedium: GoogleFonts.inter(
          fontSize: 14, fontWeight: FontWeight.w400, color: AppColors.onBackground, letterSpacing: 0.25),
      bodySmall: GoogleFonts.inter(
          fontSize: 12, fontWeight: FontWeight.w400, color: AppColors.onBackground, letterSpacing: 0.4),
      labelLarge: GoogleFonts.inter(
          fontSize: 14, fontWeight: FontWeight.w600, color: AppColors.onSurface, letterSpacing: 0.1),
      labelMedium: GoogleFonts.inter(
          fontSize: 12, fontWeight: FontWeight.w500, color: AppColors.onSurface, letterSpacing: 0.5),
      labelSmall: GoogleFonts.inter(
          fontSize: 11, fontWeight: FontWeight.w500, color: AppColors.onBackground, letterSpacing: 0.5),
    );

    return base.copyWith(
      textTheme: textTheme,
      scaffoldBackgroundColor: AppColors.background,
      appBarTheme: AppBarTheme(
        centerTitle: false,
        elevation: 0,
        scrolledUnderElevation: 1,
        backgroundColor: AppColors.surface,
        foregroundColor: AppColors.onSurface,
        titleTextStyle: textTheme.titleLarge,
        shadowColor: AppColors.outline.withOpacity(0.5),
      ),
      cardTheme: const CardTheme(
        elevation: 0,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.all(Radius.circular(AppRadius.lg)),
          side: BorderSide(color: AppColors.outline, width: 0.5),
        ),
        color: AppColors.surface,
        margin: EdgeInsets.zero,
      ),
      inputDecorationTheme: InputDecorationTheme(
        filled: true,
        fillColor: AppColors.surface,
        contentPadding: const EdgeInsets.symmetric(horizontal: 16, vertical: 14),
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(AppRadius.md),
          borderSide: const BorderSide(color: AppColors.outline),
        ),
        enabledBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(AppRadius.md),
          borderSide: const BorderSide(color: AppColors.outline, width: 1),
        ),
        focusedBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(AppRadius.md),
          borderSide: const BorderSide(color: AppColors.primary, width: 2),
        ),
        errorBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(AppRadius.md),
          borderSide: const BorderSide(color: AppColors.error, width: 1),
        ),
        focusedErrorBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(AppRadius.md),
          borderSide: const BorderSide(color: AppColors.error, width: 2),
        ),
        labelStyle: textTheme.bodyMedium?.copyWith(color: AppColors.onBackground),
        hintStyle: textTheme.bodyMedium?.copyWith(color: AppColors.outline),
        prefixIconColor: AppColors.onBackground,
        suffixIconColor: AppColors.onBackground,
      ),
      filledButtonTheme: FilledButtonThemeData(
        style: FilledButton.styleFrom(
          backgroundColor: AppColors.primary,
          foregroundColor: AppColors.onPrimary,
          textStyle: textTheme.labelLarge,
          padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 14),
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(AppRadius.md)),
          minimumSize: const Size(double.infinity, 50),
        ),
      ),
      outlinedButtonTheme: OutlinedButtonThemeData(
        style: OutlinedButton.styleFrom(
          foregroundColor: AppColors.primary,
          textStyle: textTheme.labelLarge,
          padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 14),
          side: const BorderSide(color: AppColors.primary),
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(AppRadius.md)),
          minimumSize: const Size(double.infinity, 50),
        ),
      ),
      textButtonTheme: TextButtonThemeData(
        style: TextButton.styleFrom(
          foregroundColor: AppColors.primary,
          textStyle: textTheme.labelLarge,
          padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 10),
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(AppRadius.sm)),
        ),
      ),
      chipTheme: ChipThemeData(
        backgroundColor: AppColors.background,
        selectedColor: AppColors.primaryContainer.withOpacity(0.2),
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(AppRadius.full)),
        padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 6),
        labelStyle: textTheme.labelMedium,
        side: const BorderSide(color: AppColors.outline, width: 0.5),
      ),
      bottomNavigationBarTheme: BottomNavigationBarThemeData(
        elevation: 0,
        backgroundColor: AppColors.surface,
        selectedItemColor: AppColors.primary,
        unselectedItemColor: AppColors.outline,
        selectedLabelStyle: textTheme.labelSmall?.copyWith(fontWeight: FontWeight.w600),
        unselectedLabelStyle: textTheme.labelSmall,
        showSelectedLabels: true,
        showUnselectedLabels: true,
        type: BottomNavigationBarType.fixed,
      ),
      dividerTheme: const DividerThemeData(
        color: AppColors.outline,
        thickness: 0.5,
        space: 0,
      ),
      snackBarTheme: SnackBarThemeData(
        behavior: SnackBarBehavior.floating,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(AppRadius.sm)),
        backgroundColor: AppColors.onSurface,
        contentTextStyle: textTheme.bodyMedium?.copyWith(color: AppColors.surface),
      ),
      progressIndicatorTheme: const ProgressIndicatorThemeData(
        color: AppColors.secondary,
        linearTrackColor: Color(0xFFE2E8F0),
      ),
    );
  }
}

/// Reusable gradient container used for headers and hero sections.
class GradientBox extends StatelessWidget {
  final Widget child;
  final List<Color> colors;
  final BorderRadius? borderRadius;

  const GradientBox({
    super.key,
    required this.child,
    this.colors = AppColors.primaryGradient,
    this.borderRadius,
  });

  @override
  Widget build(BuildContext context) {
    return DecoratedBox(
      decoration: BoxDecoration(
        gradient: LinearGradient(
          colors: colors,
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
        borderRadius: borderRadius,
      ),
      child: child,
    );
  }
}

/// Status badge chip — planned / in_progress / completed / missed
class StatusBadge extends StatelessWidget {
  final String status;
  const StatusBadge({super.key, required this.status});

  @override
  Widget build(BuildContext context) {
    final (color, label) = switch (status) {
      'completed' => (AppColors.completed, 'Completed'),
      'in_progress' => (AppColors.inProgress, 'In Progress'),
      'missed' => (AppColors.missed, 'Missed'),
      _ => (AppColors.planned, 'Planned'),
    };

    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 4),
      decoration: BoxDecoration(
        color: color.withOpacity(0.12),
        borderRadius: BorderRadius.circular(AppRadius.full),
        border: Border.all(color: color.withOpacity(0.4)),
      ),
      child: Text(
        label,
        style: TextStyle(
          color: color,
          fontSize: 11,
          fontWeight: FontWeight.w600,
          letterSpacing: 0.3,
        ),
      ),
    );
  }
}
