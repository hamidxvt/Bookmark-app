import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';

import '../../../core/theme/app_theme.dart';
import 'auth_notifier.dart';

class LoginScreen extends ConsumerStatefulWidget {
  const LoginScreen({super.key});

  @override
  ConsumerState<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends ConsumerState<LoginScreen> {
  final _emailCtrl = TextEditingController();
  final _passCtrl = TextEditingController();
  final _formKey = GlobalKey<FormState>();
  bool _obscure = true;

  @override
  void dispose() {
    _emailCtrl.dispose();
    _passCtrl.dispose();
    super.dispose();
  }

  Future<void> _submit() async {
    if (!_formKey.currentState!.validate()) return;
    FocusScope.of(context).unfocus();
    await ref.read(authProvider.notifier).login(
      _emailCtrl.text.trim(),
      _passCtrl.text,
    );
    if (mounted && ref.read(authProvider).isAuthenticated) {
      context.go('/dashboard');
    }
  }

  void _clearError() => ref.read(authProvider.notifier).clearError();

  @override
  Widget build(BuildContext context) {
    final auth = ref.watch(authProvider);
    final size = MediaQuery.sizeOf(context);

    return Scaffold(
      backgroundColor: const Color(0xFFF8F9FA),
      body: Stack(
        children: [
          // Background gradient — top 45%
          Positioned(
            top: 0, left: 0, right: 0,
            height: size.height * 0.46,
            child: Container(
              decoration: const BoxDecoration(
                gradient: LinearGradient(
                  colors: AppColors.primaryGradient,
                  begin: Alignment.topLeft,
                  end: Alignment.bottomRight,
                ),
              ),
            ),
          ),

          // Subtle overlay arcs for depth
          Positioned(
            top: -80, right: -80,
            child: Container(
              width: 240, height: 240,
              decoration: BoxDecoration(
                shape: BoxShape.circle,
                color: Colors.white.withOpacity(0.06),
              ),
            ),
          ),
          Positioned(
            top: 60, left: -100,
            child: Container(
              width: 200, height: 200,
              decoration: BoxDecoration(
                shape: BoxShape.circle,
                color: Colors.white.withOpacity(0.04),
              ),
            ),
          ),

          SafeArea(
            child: SingleChildScrollView(
              physics: const BouncingScrollPhysics(),
              child: Column(
                children: [
                  // ── Brand section ─────────────────────────────────────
                  SizedBox(
                    height: size.height * 0.36,
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        // Logo
                        Container(
                          width: 90,
                          height: 90,
                          decoration: BoxDecoration(
                            color: Colors.white,
                            borderRadius: BorderRadius.circular(26),
                            boxShadow: [
                              BoxShadow(
                                color: Colors.black.withOpacity(0.2),
                                blurRadius: 24,
                                offset: const Offset(0, 8),
                              ),
                            ],
                          ),
                          child: ClipRRect(
                            borderRadius: BorderRadius.circular(24),
                            child: Image.asset(
                              'assets/images/app_icon.png',
                              fit: BoxFit.cover,
                              errorBuilder: (_, __, ___) => const Icon(
                                Icons.bookmark_rounded,
                                color: AppColors.primary,
                                size: 52,
                              ),
                            ),
                          ),
                        )
                            .animate()
                            .scale(duration: 700.ms, curve: Curves.elasticOut)
                            .fadeIn(duration: 400.ms),

                        const SizedBox(height: 20),

                        Text(
                          'Bookmark SFA',
                          style: const TextStyle(
                            color: Colors.white,
                            fontSize: 26,
                            fontWeight: FontWeight.w800,
                            letterSpacing: -0.5,
                          ),
                        )
                            .animate(delay: 150.ms)
                            .slideY(begin: 0.4, end: 0, duration: 500.ms, curve: Curves.easeOut)
                            .fadeIn(),

                        const SizedBox(height: 6),

                        Text(
                          'Field Force Management',
                          style: TextStyle(
                            color: Colors.white.withOpacity(0.7),
                            fontSize: 13,
                            letterSpacing: 1.2,
                            fontWeight: FontWeight.w500,
                          ),
                        )
                            .animate(delay: 250.ms)
                            .slideY(begin: 0.4, end: 0, duration: 500.ms, curve: Curves.easeOut)
                            .fadeIn(),
                      ],
                    ),
                  ),

                  // ── Login Card ─────────────────────────────────────────
                  Padding(
                    padding: const EdgeInsets.fromLTRB(20, 0, 20, 40),
                    child: Container(
                      decoration: BoxDecoration(
                        color: Colors.white,
                        borderRadius: BorderRadius.circular(28),
                        boxShadow: [
                          BoxShadow(
                            color: AppColors.primary.withOpacity(0.10),
                            blurRadius: 32,
                            offset: const Offset(0, 12),
                            spreadRadius: -4,
                          ),
                          BoxShadow(
                            color: Colors.black.withOpacity(0.05),
                            blurRadius: 12,
                            offset: const Offset(0, 4),
                          ),
                        ],
                      ),
                      child: Padding(
                        padding: const EdgeInsets.all(28),
                        child: Form(
                          key: _formKey,
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              // Card header
                              Text(
                                'Welcome back',
                                style: const TextStyle(
                                  fontSize: 22,
                                  fontWeight: FontWeight.w800,
                                  color: Color(0xFF0F172A),
                                  letterSpacing: -0.4,
                                ),
                              ),
                              const SizedBox(height: 4),
                              Text(
                                'Sign in to manage your field operations',
                                style: TextStyle(
                                  fontSize: 13.5,
                                  color: const Color(0xFF64748B),
                                  height: 1.4,
                                ),
                              ),
                              const SizedBox(height: 28),

                              // Email field
                              _FieldLabel('Email address'),
                              const SizedBox(height: 6),
                              TextFormField(
                                controller: _emailCtrl,
                                keyboardType: TextInputType.emailAddress,
                                textInputAction: TextInputAction.next,
                                autocorrect: false,
                                style: const TextStyle(fontSize: 14, fontWeight: FontWeight.w500),
                                decoration: _inputDeco(
                                  hint: 'officer@bookmark.pk',
                                  icon: Icons.email_outlined,
                                ),
                                validator: (v) {
                                  if (v == null || v.isEmpty) return 'Email is required';
                                  if (!v.contains('@')) return 'Enter a valid email';
                                  return null;
                                },
                              ),
                              const SizedBox(height: 18),

                              // Password field
                              _FieldLabel('Password'),
                              const SizedBox(height: 6),
                              TextFormField(
                                controller: _passCtrl,
                                obscureText: _obscure,
                                textInputAction: TextInputAction.done,
                                onFieldSubmitted: (_) => _submit(),
                                style: const TextStyle(fontSize: 14, fontWeight: FontWeight.w500),
                                decoration: _inputDeco(
                                  hint: '••••••••',
                                  icon: Icons.lock_outline_rounded,
                                  suffix: GestureDetector(
                                    onTap: () => setState(() => _obscure = !_obscure),
                                    child: Icon(
                                      _obscure ? Icons.visibility_outlined : Icons.visibility_off_outlined,
                                      size: 20,
                                      color: const Color(0xFF94A3B8),
                                    ),
                                  ),
                                ),
                                validator: (v) {
                                  if (v == null || v.isEmpty) return 'Password is required';
                                  if (v.length < 6) return 'Minimum 6 characters';
                                  return null;
                                },
                              ),

                              // Error banner
                              if (auth.error != null) ...[
                                const SizedBox(height: 14),
                                Container(
                                  padding: const EdgeInsets.symmetric(horizontal: 14, vertical: 12),
                                  decoration: BoxDecoration(
                                    color: AppColors.error.withOpacity(0.07),
                                    borderRadius: BorderRadius.circular(12),
                                    border: Border.all(color: AppColors.error.withOpacity(0.25)),
                                  ),
                                  child: Row(children: [
                                    Icon(Icons.error_outline_rounded, color: AppColors.error, size: 17),
                                    const SizedBox(width: 10),
                                    Expanded(
                                      child: Text(
                                        auth.error!,
                                        style: TextStyle(
                                          fontSize: 12.5,
                                          color: AppColors.error,
                                          fontWeight: FontWeight.w500,
                                        ),
                                      ),
                                    ),
                                    GestureDetector(
                                      onTap: _clearError,
                                      child: Icon(Icons.close_rounded, color: AppColors.error, size: 17),
                                    ),
                                  ]),
                                ).animate().shakeX(duration: 400.ms, hz: 3, amount: 4),
                              ],

                              const SizedBox(height: 10),

                              // Forgot password
                              Align(
                                alignment: Alignment.centerRight,
                                child: TextButton(
                                  onPressed: () => context.go('/forgot-password'),
                                  style: TextButton.styleFrom(
                                    padding: const EdgeInsets.symmetric(horizontal: 4, vertical: 2),
                                    minimumSize: Size.zero,
                                    tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                                  ),
                                  child: const Text(
                                    'Forgot Password?',
                                    style: TextStyle(
                                      fontSize: 12.5,
                                      color: AppColors.primary,
                                      fontWeight: FontWeight.w600,
                                    ),
                                  ),
                                ),
                              ),

                              const SizedBox(height: 22),

                              // Sign in button
                              SizedBox(
                                width: double.infinity,
                                height: 52,
                                child: FilledButton(
                                  onPressed: auth.isLoading ? null : _submit,
                                  style: FilledButton.styleFrom(
                                    backgroundColor: AppColors.primary,
                                    shape: RoundedRectangleBorder(
                                      borderRadius: BorderRadius.circular(14),
                                    ),
                                    elevation: 0,
                                  ),
                                  child: auth.isLoading
                                      ? const SizedBox(
                                          width: 22, height: 22,
                                          child: CircularProgressIndicator(
                                            strokeWidth: 2.5,
                                            color: Colors.white,
                                          ),
                                        )
                                      : const Text(
                                          'Sign In',
                                          style: TextStyle(
                                            fontSize: 15,
                                            fontWeight: FontWeight.w700,
                                            letterSpacing: 0.2,
                                          ),
                                        ),
                                ),
                              ),
                            ],
                          ),
                        ),
                      ),
                    ),
                  )
                      .animate(delay: 350.ms)
                      .slideY(begin: 0.15, end: 0, duration: 600.ms, curve: Curves.easeOutCubic)
                      .fadeIn(),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }

  InputDecoration _inputDeco({
    required String hint,
    required IconData icon,
    Widget? suffix,
  }) {
    return InputDecoration(
      hintText: hint,
      hintStyle: const TextStyle(fontSize: 13.5, color: Color(0xFFCBD5E1)),
      prefixIcon: Icon(icon, size: 19, color: const Color(0xFF94A3B8)),
      suffixIcon: suffix != null
          ? Padding(padding: const EdgeInsets.only(right: 14), child: suffix)
          : null,
      suffixIconConstraints: const BoxConstraints(minWidth: 40, minHeight: 40),
      filled: true,
      fillColor: const Color(0xFFF8FAFC),
      border: OutlineInputBorder(
        borderRadius: BorderRadius.circular(14),
        borderSide: const BorderSide(color: Color(0xFFE2E8F0)),
      ),
      enabledBorder: OutlineInputBorder(
        borderRadius: BorderRadius.circular(14),
        borderSide: const BorderSide(color: Color(0xFFE2E8F0)),
      ),
      focusedBorder: OutlineInputBorder(
        borderRadius: BorderRadius.circular(14),
        borderSide: const BorderSide(color: AppColors.primary, width: 1.5),
      ),
      errorBorder: OutlineInputBorder(
        borderRadius: BorderRadius.circular(14),
        borderSide: BorderSide(color: AppColors.error),
      ),
      focusedErrorBorder: OutlineInputBorder(
        borderRadius: BorderRadius.circular(14),
        borderSide: BorderSide(color: AppColors.error, width: 1.5),
      ),
      contentPadding: const EdgeInsets.symmetric(horizontal: 16, vertical: 14),
    );
  }
}

class _FieldLabel extends StatelessWidget {
  final String text;
  const _FieldLabel(this.text);

  @override
  Widget build(BuildContext context) {
    return Text(
      text,
      style: const TextStyle(
        fontSize: 12.5,
        fontWeight: FontWeight.w600,
        color: Color(0xFF475569),
        letterSpacing: 0.1,
      ),
    );
  }
}
