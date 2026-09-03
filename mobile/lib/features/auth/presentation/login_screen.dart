import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';

import '../../../core/theme/app_theme.dart';
import '../presentation/auth_notifier.dart';

class LoginScreen extends ConsumerStatefulWidget {
  const LoginScreen({super.key});

  @override
  ConsumerState<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends ConsumerState<LoginScreen> {
  late TextEditingController _emailCtrl;
  late TextEditingController _passCtrl;
  bool _obscure = true;

  @override
  void initState() {
    super.initState();
    _emailCtrl = TextEditingController();
    _passCtrl = TextEditingController();
  }

  @override
  void dispose() {
    _emailCtrl.dispose();
    _passCtrl.dispose();
    super.dispose();
  }

  Future<void> _login() async {
    if (_emailCtrl.text.isEmpty || _passCtrl.text.isEmpty) {
      _showError('Please enter email and password');
      return;
    }
    await ref.read(authProvider.notifier).login(
          _emailCtrl.text.trim(),
          _passCtrl.text,
        );
    final auth = ref.read(authProvider);
    if (mounted) {
      if (auth.user != null) {
        context.go('/dashboard');
      } else if (auth.error != null) {
        _showError(auth.error!);
      }
    }
  }

  void _showError(String msg) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text(msg),
        backgroundColor: AppColors.primary,
        behavior: SnackBarBehavior.floating,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
        duration: const Duration(seconds: 3),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    final auth = ref.watch(authProvider);
    final loading = auth.isLoading;

    return Scaffold(
      backgroundColor: Colors.white,
      body: SafeArea(
        child: SingleChildScrollView(
          physics: const BouncingScrollPhysics(),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              // ── Pink wash header ──────────────────────────────────────
              Container(
                width: double.infinity,
                decoration: const BoxDecoration(
                  gradient: LinearGradient(
                    colors: [Color(0xFFFDE8E8), Color(0xFFFFF5F5), Colors.white],
                    begin: Alignment.topCenter,
                    end: Alignment.bottomCenter,
                  ),
                ),
                child: Padding(
                  padding: const EdgeInsets.fromLTRB(24, 24, 24, 0),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      // Brand logo top-left
                      Row(
                        children: [
                          Image.asset(
                            'assets/images/logo.png',
                            width: 44,
                            height: 44,
                            fit: BoxFit.contain,
                            errorBuilder: (_, __, ___) => Container(
                              width: 44,
                              height: 44,
                              decoration: BoxDecoration(
                                color: AppColors.primary,
                                borderRadius: BorderRadius.circular(10),
                              ),
                              child: const Icon(Icons.bookmark_rounded,
                                  color: Colors.white, size: 26),
                            ),
                          ),
                          const SizedBox(width: 10),
                          const Text(
                            'BOOKMARK',
                            style: TextStyle(
                              color: AppColors.primary,
                              fontSize: 15,
                              fontWeight: FontWeight.w900,
                              letterSpacing: 1.5,
                            ),
                          ),
                        ],
                      ).animate().fadeIn(duration: 400.ms),

                      const SizedBox(height: 28),

                      // "Welcome Back!" headline
                      RichText(
                        text: const TextSpan(
                          children: [
                            TextSpan(
                              text: 'Welcome ',
                              style: TextStyle(
                                color: Color(0xFF0F172A),
                                fontSize: 34,
                                fontWeight: FontWeight.w800,
                                height: 1.1,
                              ),
                            ),
                            TextSpan(
                              text: 'Back!',
                              style: TextStyle(
                                color: AppColors.primary,
                                fontSize: 34,
                                fontWeight: FontWeight.w800,
                                height: 1.1,
                              ),
                            ),
                          ],
                        ),
                      )
                          .animate()
                          .fadeIn(delay: 100.ms, duration: 500.ms)
                          .slideY(begin: 0.15, end: 0),

                      const SizedBox(height: 8),

                      const Text(
                        'Sign in to continue your journey',
                        style: TextStyle(
                          color: Color(0xFF6B7280),
                          fontSize: 15,
                          fontWeight: FontWeight.w400,
                        ),
                      ).animate().fadeIn(delay: 200.ms, duration: 400.ms),

                      const SizedBox(height: 16),

                      // 3D field officer illustration
                      Center(
                        child: Image.asset(
                          'assets/images/field_officer_3d.png',
                          height: 210,
                          fit: BoxFit.contain,
                          errorBuilder: (_, __, ___) => SizedBox(
                            height: 210,
                            child: Center(
                              child: Icon(
                                Icons.directions_walk_rounded,
                                size: 120,
                                color: AppColors.primary.withOpacity(0.2),
                              ),
                            ),
                          ),
                        ),
                      ).animate().fadeIn(delay: 250.ms, duration: 600.ms)
                          .scale(begin: const Offset(0.92, 0.92), end: const Offset(1, 1),
                              duration: 500.ms, delay: 250.ms, curve: Curves.easeOut),
                    ],
                  ),
                ),
              ),

              // ── Form section ──────────────────────────────────────────
              Padding(
                padding: const EdgeInsets.fromLTRB(24, 24, 24, 32),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    // Email
                    const Text(
                      'EMAIL ADDRESS',
                      style: TextStyle(
                        fontSize: 11,
                        fontWeight: FontWeight.w700,
                        color: Color(0xFF9CA3AF),
                        letterSpacing: 1.2,
                      ),
                    ).animate().fadeIn(delay: 300.ms),
                    const SizedBox(height: 8),
                    _InputField(
                      controller: _emailCtrl,
                      enabled: !loading,
                      keyboardType: TextInputType.emailAddress,
                      icon: Icons.mail_outline_rounded,
                      hint: 'officer@bookmark.pk',
                    ).animate().fadeIn(delay: 350.ms, duration: 400.ms)
                        .slideY(begin: 0.08, end: 0),

                    const SizedBox(height: 20),

                    // Password
                    const Text(
                      'PASSWORD',
                      style: TextStyle(
                        fontSize: 11,
                        fontWeight: FontWeight.w700,
                        color: Color(0xFF9CA3AF),
                        letterSpacing: 1.2,
                      ),
                    ).animate().fadeIn(delay: 400.ms),
                    const SizedBox(height: 8),
                    _InputField(
                      controller: _passCtrl,
                      enabled: !loading,
                      icon: Icons.lock_outline_rounded,
                      hint: '••••••••',
                      obscure: _obscure,
                      onToggleObscure: () => setState(() => _obscure = !_obscure),
                    ).animate().fadeIn(delay: 450.ms, duration: 400.ms)
                        .slideY(begin: 0.08, end: 0),

                    const SizedBox(height: 12),

                    // Forgot password
                    Align(
                      alignment: Alignment.centerRight,
                      child: GestureDetector(
                        onTap: () => context.go('/forgot-password'),
                        child: const Text(
                          'Forgot Password?',
                          style: TextStyle(
                            fontSize: 13,
                            fontWeight: FontWeight.w700,
                            color: AppColors.primary,
                          ),
                        ),
                      ),
                    ).animate().fadeIn(delay: 480.ms),

                    const SizedBox(height: 28),

                    // Sign In button
                    GestureDetector(
                      onTap: loading ? null : _login,
                      child: AnimatedContainer(
                        duration: const Duration(milliseconds: 200),
                        height: 58,
                        width: double.infinity,
                        decoration: BoxDecoration(
                          color: loading ? const Color(0xFFE5E7EB) : AppColors.primary,
                          borderRadius: BorderRadius.circular(16),
                          boxShadow: loading
                              ? null
                              : [
                                  BoxShadow(
                                    color: AppColors.primary.withOpacity(0.35),
                                    blurRadius: 20,
                                    offset: const Offset(0, 8),
                                  ),
                                ],
                        ),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            if (loading)
                              const SizedBox(
                                width: 22,
                                height: 22,
                                child: CircularProgressIndicator(
                                    strokeWidth: 2.5, color: AppColors.primary),
                              )
                            else ...[
                              const Text(
                                'Sign In',
                                style: TextStyle(
                                  color: Colors.white,
                                  fontSize: 17,
                                  fontWeight: FontWeight.w700,
                                ),
                              ),
                              const SizedBox(width: 10),
                              const Icon(Icons.arrow_forward_rounded,
                                  color: Colors.white, size: 20),
                            ],
                          ],
                        ),
                      ),
                    ).animate().fadeIn(delay: 500.ms, duration: 400.ms)
                        .slideY(begin: 0.08, end: 0),

                    const SizedBox(height: 32),

                    Center(
                      child: Text(
                        '© 2026 Bookmark Publishing',
                        style: TextStyle(
                          fontSize: 11,
                          color: Colors.grey.shade400,
                        ),
                      ),
                    ).animate().fadeIn(delay: 600.ms),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

// ── Input field component ─────────────────────────────────────────────────────
class _InputField extends StatelessWidget {
  final TextEditingController controller;
  final bool enabled;
  final TextInputType? keyboardType;
  final IconData icon;
  final String hint;
  final bool obscure;
  final VoidCallback? onToggleObscure;

  const _InputField({
    required this.controller,
    required this.enabled,
    required this.icon,
    required this.hint,
    this.keyboardType,
    this.obscure = false,
    this.onToggleObscure,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        color: const Color(0xFFF3F4F6),
        borderRadius: BorderRadius.circular(14),
      ),
      child: TextField(
        controller: controller,
        enabled: enabled,
        keyboardType: keyboardType,
        obscureText: obscure,
        style: const TextStyle(
          fontSize: 15,
          fontWeight: FontWeight.w500,
          color: Color(0xFF111827),
        ),
        decoration: InputDecoration(
          hintText: hint,
          hintStyle: const TextStyle(color: Color(0xFF9CA3AF), fontSize: 14),
          prefixIcon: Padding(
            padding: const EdgeInsets.only(left: 16, right: 12),
            child: Icon(icon, size: 19, color: const Color(0xFF6B7280)),
          ),
          prefixIconConstraints: const BoxConstraints(minWidth: 48),
          suffixIcon: onToggleObscure != null
              ? GestureDetector(
                  onTap: onToggleObscure,
                  child: Padding(
                    padding: const EdgeInsets.only(right: 16),
                    child: Icon(
                      obscure ? Icons.visibility_off_outlined : Icons.visibility_outlined,
                      size: 19,
                      color: const Color(0xFF6B7280),
                    ),
                  ),
                )
              : null,
          suffixIconConstraints: const BoxConstraints(minWidth: 48),
          contentPadding: const EdgeInsets.symmetric(horizontal: 16, vertical: 17),
          border: InputBorder.none,
          enabledBorder: InputBorder.none,
          focusedBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(14),
            borderSide: const BorderSide(color: AppColors.primary, width: 1.8),
          ),
        ),
      ),
    );
  }
}
