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
        backgroundColor: AppColors.missed,
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
      backgroundColor: AppColors.background,
      body: SafeArea(
        child: SingleChildScrollView(
          physics: const BouncingScrollPhysics(),
          child: Column(
            children: [
              // ── Gradient wash at top ─────────────────────────────────────
              Stack(
                alignment: Alignment.topCenter,
                children: [
                  Container(
                    height: 220,
                    decoration: BoxDecoration(
                      gradient: RadialGradient(
                        center: Alignment.topCenter,
                        radius: 1.2,
                        colors: [
                          AppColors.primary.withOpacity(0.14),
                          Colors.transparent,
                        ],
                      ),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.fromLTRB(24, 32, 24, 0),
                    child: Column(
                      children: [
                        // Brand logo + name
                        Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Container(
                              width: 44,
                              height: 44,
                              decoration: BoxDecoration(
                                color: Colors.white,
                                borderRadius: BorderRadius.circular(14),
                                border: Border.all(color: AppColors.outline),
                                boxShadow: [
                                  BoxShadow(
                                    color: Colors.black.withOpacity(0.06),
                                    blurRadius: 10,
                                    offset: const Offset(0, 3),
                                  ),
                                ],
                              ),
                              padding: const EdgeInsets.all(6),
                              child: Image.asset(
                                'assets/images/logo.png',
                                fit: BoxFit.contain,
                                errorBuilder: (_, __, ___) => const Icon(
                                  Icons.bookmark_rounded,
                                  color: AppColors.primary,
                                  size: 24,
                                ),
                              ),
                            ),
                          ],
                        ),
                        const SizedBox(height: 20),
                        const Text('Welcome Back!',
                            style: TextStyle(
                              fontSize: 30,
                              fontWeight: FontWeight.w800,
                              color: AppColors.onSurface,
                              letterSpacing: -0.5,
                            )).animate().fadeIn(duration: 500.ms).slideY(begin: 0.2, end: 0),
                        const SizedBox(height: 6),
                        Text('Sign in to continue your journey',
                            style: TextStyle(fontSize: 14, color: AppColors.textSecondary))
                            .animate().fadeIn(delay: 100.ms, duration: 500.ms),
                      ],
                    ),
                  ),
                ],
              ),

              // ── Form ─────────────────────────────────────────────────────
              Padding(
                padding: const EdgeInsets.fromLTRB(20, 8, 20, 40),
                child: Column(
                  children: [
                    // Email
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        const Text('Email Address',
                            style: TextStyle(fontSize: 12, fontWeight: FontWeight.w700,
                                color: AppColors.onSurface, letterSpacing: 0.3)),
                        const SizedBox(height: 8),
                        _BrandInput(
                          controller: _emailCtrl,
                          enabled: !loading,
                          keyboardType: TextInputType.emailAddress,
                          icon: Icons.mail_outline_rounded,
                          hint: 'officer@bookmark.pk',
                        ),
                      ],
                    ).animate().fadeIn(delay: 150.ms, duration: 400.ms).slideY(begin: 0.1, end: 0),

                    const SizedBox(height: 16),

                    // Password
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        const Text('Password',
                            style: TextStyle(fontSize: 12, fontWeight: FontWeight.w700,
                                color: AppColors.onSurface, letterSpacing: 0.3)),
                        const SizedBox(height: 8),
                        _BrandInput(
                          controller: _passCtrl,
                          enabled: !loading,
                          icon: Icons.lock_outline_rounded,
                          hint: '••••••••',
                          obscure: _obscure,
                          onToggleObscure: () => setState(() => _obscure = !_obscure),
                        ),
                        const SizedBox(height: 8),
                        Align(
                          alignment: Alignment.centerRight,
                          child: GestureDetector(
                            onTap: () => context.go('/forgot-password'),
                            child: const Text('Forgot Password?',
                                style: TextStyle(fontSize: 13, fontWeight: FontWeight.w700,
                                    color: AppColors.primary)),
                          ),
                        ),
                      ],
                    ).animate().fadeIn(delay: 200.ms, duration: 400.ms).slideY(begin: 0.1, end: 0),

                    const SizedBox(height: 24),

                    // Sign In button
                    GestureDetector(
                      onTap: loading ? null : _login,
                      child: AnimatedContainer(
                        duration: 200.ms,
                        height: 56,
                        width: double.infinity,
                        decoration: BoxDecoration(
                          gradient: loading
                              ? null
                              : const LinearGradient(
                                  colors: AppColors.primaryGradient,
                                  begin: Alignment.topLeft,
                                  end: Alignment.bottomRight,
                                ),
                          color: loading ? AppColors.outline : null,
                          borderRadius: BorderRadius.circular(AppRadius.xl),
                          boxShadow: loading
                              ? null
                              : [
                                  BoxShadow(
                                    color: AppColors.primary.withOpacity(0.45),
                                    blurRadius: 20,
                                    offset: const Offset(0, 8),
                                  )
                                ],
                        ),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            if (loading)
                              const SizedBox(
                                width: 20, height: 20,
                                child: CircularProgressIndicator(
                                    strokeWidth: 2, color: Colors.white),
                              )
                            else
                              const Text('Sign In',
                                  style: TextStyle(
                                    color: Colors.white,
                                    fontSize: 16,
                                    fontWeight: FontWeight.w800,
                                  )),
                            if (!loading) ...[
                              const SizedBox(width: 8),
                              const Icon(Icons.arrow_forward_rounded, color: Colors.white, size: 20),
                            ],
                          ],
                        ),
                      ),
                    ).animate().fadeIn(delay: 250.ms, duration: 400.ms).slideY(begin: 0.1, end: 0),

                    const SizedBox(height: 40),

                    Text('© 2026 Bookmark Publishing',
                        style: TextStyle(fontSize: 11, color: AppColors.textMuted))
                        .animate().fadeIn(delay: 300.ms, duration: 400.ms),
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

// ── Brand Input ───────────────────────────────────────────────────────────────
class _BrandInput extends StatelessWidget {
  final TextEditingController controller;
  final bool enabled;
  final TextInputType? keyboardType;
  final IconData icon;
  final String hint;
  final bool obscure;
  final VoidCallback? onToggleObscure;

  const _BrandInput({
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
    return TextField(
      controller: controller,
      enabled: enabled,
      keyboardType: keyboardType,
      obscureText: obscure,
      style: const TextStyle(fontSize: 14, fontWeight: FontWeight.w600, color: AppColors.onSurface),
      decoration: InputDecoration(
        hintText: hint,
        hintStyle: const TextStyle(color: AppColors.textMuted, fontSize: 14),
        prefixIcon: Padding(
          padding: const EdgeInsets.only(left: 14, right: 10),
          child: Icon(icon, size: 18, color: AppColors.textMuted),
        ),
        prefixIconConstraints: const BoxConstraints(minWidth: 44),
        suffixIcon: onToggleObscure != null
            ? GestureDetector(
                onTap: onToggleObscure,
                child: Padding(
                  padding: const EdgeInsets.only(right: 14),
                  child: Icon(
                    obscure ? Icons.visibility_off_rounded : Icons.visibility_rounded,
                    size: 18,
                    color: AppColors.textMuted,
                  ),
                ),
              )
            : null,
        suffixIconConstraints: const BoxConstraints(minWidth: 44),
        filled: true,
        fillColor: AppColors.card,
        contentPadding: const EdgeInsets.symmetric(horizontal: 16, vertical: 14),
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(AppRadius.xl),
          borderSide: const BorderSide(color: AppColors.outline),
        ),
        enabledBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(AppRadius.xl),
          borderSide: const BorderSide(color: AppColors.outline, width: 1),
        ),
        focusedBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(AppRadius.xl),
          borderSide: const BorderSide(color: AppColors.primary, width: 1.8),
        ),
      ),
    );
  }
}
