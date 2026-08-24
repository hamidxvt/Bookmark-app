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
  final _passCtrl  = TextEditingController();
  final _formKey   = GlobalKey<FormState>();
  bool _obscure    = true;

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

  @override
  Widget build(BuildContext context) {
    final auth = ref.watch(authProvider);

    return Scaffold(
      backgroundColor: Colors.white,
      body: SafeArea(
        child: SingleChildScrollView(
          physics: const BouncingScrollPhysics(),
          child: ConstrainedBox(
            constraints: BoxConstraints(
              minHeight: MediaQuery.of(context).size.height -
                  MediaQuery.of(context).padding.top -
                  MediaQuery.of(context).padding.bottom,
            ),
            child: IntrinsicHeight(
              child: Column(
                children: [
                  // ── Top brand hero ─────────────────────────────────────────
                  Container(
                    width: double.infinity,
                    padding: const EdgeInsets.symmetric(vertical: 48),
                    decoration: const BoxDecoration(
                      color: Colors.white,
                    ),
                    child: Column(
                      children: [
                        // Real Bookmark logo
                        Image.asset(
                          'assets/images/logo.png',
                          width: 120,
                          height: 120,
                          fit: BoxFit.contain,
                          errorBuilder: (_, __, ___) => Container(
                            width: 120,
                            height: 120,
                            decoration: BoxDecoration(
                              color: AppColors.primary,
                              borderRadius: BorderRadius.circular(24),
                            ),
                            child: const Icon(
                              Icons.bookmark_rounded,
                              color: Colors.white,
                              size: 60,
                            ),
                          ),
                        )
                            .animate()
                            .scale(
                              duration: 700.ms,
                              curve: Curves.elasticOut,
                              begin: const Offset(0.6, 0.6),
                            )
                            .fadeIn(duration: 400.ms),

                        const SizedBox(height: 16),

                        Text(
                          'BOOKMARK',
                          style: TextStyle(
                            fontSize: 22,
                            fontWeight: FontWeight.w900,
                            color: AppColors.primary,
                            letterSpacing: 4,
                          ),
                        )
                            .animate(delay: 200.ms)
                            .slideY(begin: 0.5, end: 0, duration: 500.ms, curve: Curves.easeOut)
                            .fadeIn(),

                        const SizedBox(height: 4),

                        Text(
                          'Field Force Manager',
                          style: TextStyle(
                            fontSize: 13,
                            color: const Color(0xFF64748B),
                            letterSpacing: 1.5,
                            fontWeight: FontWeight.w500,
                          ),
                        )
                            .animate(delay: 300.ms)
                            .slideY(begin: 0.5, end: 0, duration: 500.ms, curve: Curves.easeOut)
                            .fadeIn(),
                      ],
                    ),
                  ),

                  // ── Divider with red accent ─────────────────────────────────
                  Container(
                    height: 3,
                    decoration: const BoxDecoration(
                      gradient: LinearGradient(
                        colors: [Colors.transparent, AppColors.primary, Colors.transparent],
                      ),
                    ),
                  ),

                  // ── Login form card ─────────────────────────────────────────
                  Expanded(
                    child: Container(
                      color: const Color(0xFFF5F6F8),
                      child: Padding(
                        padding: const EdgeInsets.fromLTRB(20, 28, 20, 20),
                        child: Column(
                          children: [
                            Container(
                              decoration: BoxDecoration(
                                color: Colors.white,
                                borderRadius: BorderRadius.circular(20),
                                boxShadow: [
                                  BoxShadow(
                                    color: Colors.black.withOpacity(0.06),
                                    blurRadius: 20,
                                    offset: const Offset(0, 6),
                                  ),
                                ],
                              ),
                              child: Padding(
                                padding: const EdgeInsets.all(24),
                                child: Form(
                                  key: _formKey,
                                  child: Column(
                                    crossAxisAlignment: CrossAxisAlignment.start,
                                    children: [
                                      const Text(
                                        'Sign In',
                                        style: TextStyle(
                                          fontSize: 20,
                                          fontWeight: FontWeight.w800,
                                          color: Color(0xFF0F172A),
                                          letterSpacing: -0.3,
                                        ),
                                      ),
                                      const SizedBox(height: 4),
                                      const Text(
                                        'Access your field operations dashboard',
                                        style: TextStyle(
                                          fontSize: 13,
                                          color: Color(0xFF64748B),
                                        ),
                                      ),
                                      const SizedBox(height: 24),

                                      // Email
                                      _buildLabel('Email Address'),
                                      const SizedBox(height: 6),
                                      TextFormField(
                                        controller: _emailCtrl,
                                        keyboardType: TextInputType.emailAddress,
                                        textInputAction: TextInputAction.next,
                                        autocorrect: false,
                                        decoration: _fieldDeco(
                                          hint: 'officer@bookmark.pk',
                                          icon: Icons.alternate_email_rounded,
                                        ),
                                        validator: (v) {
                                          if (v == null || v.isEmpty) return 'Email is required';
                                          if (!v.contains('@')) return 'Enter a valid email';
                                          return null;
                                        },
                                      ),
                                      const SizedBox(height: 16),

                                      // Password
                                      _buildLabel('Password'),
                                      const SizedBox(height: 6),
                                      TextFormField(
                                        controller: _passCtrl,
                                        obscureText: _obscure,
                                        textInputAction: TextInputAction.done,
                                        onFieldSubmitted: (_) => _submit(),
                                        decoration: _fieldDeco(
                                          hint: '••••••••',
                                          icon: Icons.lock_outline_rounded,
                                          suffix: GestureDetector(
                                            onTap: () => setState(() => _obscure = !_obscure),
                                            child: Icon(
                                              _obscure
                                                  ? Icons.visibility_off_outlined
                                                  : Icons.visibility_outlined,
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

                                      // Error
                                      if (auth.error != null) ...[
                                        const SizedBox(height: 14),
                                        Container(
                                          padding: const EdgeInsets.all(12),
                                          decoration: BoxDecoration(
                                            color: const Color(0xFFFEF2F2),
                                            borderRadius: BorderRadius.circular(10),
                                            border: Border.all(
                                              color: AppColors.primary.withOpacity(0.3),
                                            ),
                                          ),
                                          child: Row(children: [
                                            const Icon(
                                              Icons.error_outline_rounded,
                                              color: AppColors.primary,
                                              size: 16,
                                            ),
                                            const SizedBox(width: 8),
                                            Expanded(
                                              child: Text(
                                                auth.error!,
                                                style: const TextStyle(
                                                  fontSize: 12.5,
                                                  color: AppColors.primary,
                                                  fontWeight: FontWeight.w500,
                                                ),
                                              ),
                                            ),
                                            GestureDetector(
                                              onTap: () => ref.read(authProvider.notifier).clearError(),
                                              child: const Icon(
                                                Icons.close_rounded,
                                                color: AppColors.primary,
                                                size: 16,
                                              ),
                                            ),
                                          ]),
                                        ).animate().shakeX(duration: 400.ms, hz: 3, amount: 4),
                                      ],

                                      const SizedBox(height: 8),

                                      Align(
                                        alignment: Alignment.centerRight,
                                        child: TextButton(
                                          onPressed: () => context.go('/forgot-password'),
                                          style: TextButton.styleFrom(
                                            padding: const EdgeInsets.symmetric(horizontal: 0, vertical: 4),
                                            minimumSize: Size.zero,
                                            tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                                            foregroundColor: AppColors.primary,
                                          ),
                                          child: const Text(
                                            'Forgot Password?',
                                            style: TextStyle(
                                              fontSize: 12.5,
                                              fontWeight: FontWeight.w600,
                                            ),
                                          ),
                                        ),
                                      ),

                                      const SizedBox(height: 20),

                                      // Sign in button
                                      SizedBox(
                                        width: double.infinity,
                                        height: 50,
                                        child: ElevatedButton(
                                          onPressed: auth.isLoading ? null : _submit,
                                          style: ElevatedButton.styleFrom(
                                            backgroundColor: AppColors.primary,
                                            foregroundColor: Colors.white,
                                            disabledBackgroundColor:
                                                AppColors.primary.withOpacity(0.5),
                                            shape: RoundedRectangleBorder(
                                              borderRadius: BorderRadius.circular(12),
                                            ),
                                            elevation: 0,
                                          ),
                                          child: auth.isLoading
                                              ? const SizedBox(
                                                  width: 22,
                                                  height: 22,
                                                  child: CircularProgressIndicator(
                                                    strokeWidth: 2.5,
                                                    color: Colors.white,
                                                  ),
                                                )
                                              : const Row(
                                                  mainAxisAlignment: MainAxisAlignment.center,
                                                  children: [
                                                    Icon(Icons.login_rounded, size: 18),
                                                    SizedBox(width: 8),
                                                    Text(
                                                      'Sign In',
                                                      style: TextStyle(
                                                        fontSize: 15,
                                                        fontWeight: FontWeight.w700,
                                                        letterSpacing: 0.3,
                                                      ),
                                                    ),
                                                  ],
                                                ),
                                        ),
                                      ),
                                    ],
                                  ),
                                ),
                              ),
                            ).animate(delay: 350.ms)
                                .slideY(begin: 0.12, end: 0, duration: 500.ms, curve: Curves.easeOut)
                                .fadeIn(),

                            const Spacer(),

                            // Footer
                            Padding(
                              padding: const EdgeInsets.only(top: 20, bottom: 8),
                              child: Text(
                                '© ${DateTime.now().year} Bookmark Publishing',
                                style: const TextStyle(
                                  fontSize: 11,
                                  color: Color(0xFFCBD5E1),
                                  letterSpacing: 0.3,
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }

  Widget _buildLabel(String text) {
    return Text(
      text,
      style: const TextStyle(
        fontSize: 12.5,
        fontWeight: FontWeight.w600,
        color: Color(0xFF475569),
        letterSpacing: 0.2,
      ),
    );
  }

  InputDecoration _fieldDeco({
    required String hint,
    required IconData icon,
    Widget? suffix,
  }) {
    return InputDecoration(
      hintText: hint,
      hintStyle: const TextStyle(fontSize: 13.5, color: Color(0xFFCBD5E1)),
      prefixIcon: Icon(icon, size: 19, color: const Color(0xFFCBD5E1)),
      suffixIcon: suffix != null
          ? Padding(padding: const EdgeInsets.only(right: 14), child: suffix)
          : null,
      suffixIconConstraints: const BoxConstraints(minWidth: 40, minHeight: 40),
      filled: true,
      fillColor: const Color(0xFFF8FAFC),
      border: OutlineInputBorder(
        borderRadius: BorderRadius.circular(12),
        borderSide: const BorderSide(color: Color(0xFFE2E8F0)),
      ),
      enabledBorder: OutlineInputBorder(
        borderRadius: BorderRadius.circular(12),
        borderSide: const BorderSide(color: Color(0xFFE2E8F0)),
      ),
      focusedBorder: OutlineInputBorder(
        borderRadius: BorderRadius.circular(12),
        borderSide: const BorderSide(color: AppColors.primary, width: 1.5),
      ),
      errorBorder: OutlineInputBorder(
        borderRadius: BorderRadius.circular(12),
        borderSide: const BorderSide(color: AppColors.primary),
      ),
      focusedErrorBorder: OutlineInputBorder(
        borderRadius: BorderRadius.circular(12),
        borderSide: const BorderSide(color: AppColors.primary, width: 1.5),
      ),
      contentPadding: const EdgeInsets.symmetric(horizontal: 16, vertical: 14),
    );
  }
}
