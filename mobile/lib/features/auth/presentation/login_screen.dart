import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';

import '../../../core/theme/app_theme.dart';
import '../../../core/services/api_service.dart';
import '../presentation/auth_notifier.dart';

class LoginScreen extends ConsumerStatefulWidget {
  const LoginScreen({super.key});

  @override
  ConsumerState<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends ConsumerState<LoginScreen> {
  late TextEditingController emailCtrl;
  late TextEditingController passCtrl;
  bool obscure = true;
  bool loading = false;
  String? error;

  @override
  void initState() {
    super.initState();
    emailCtrl = TextEditingController();
    passCtrl = TextEditingController();
  }

  @override
  void dispose() {
    emailCtrl.dispose();
    passCtrl.dispose();
    super.dispose();
  }

  Future<void> _login() async {
    if (emailCtrl.text.isEmpty || passCtrl.text.isEmpty) {
      setState(() => error = 'Please enter email and password');
      return;
    }

    setState(() {
      loading = true;
      error = null;
    });

    try {
      final api = ref.read(apiServiceProvider);
      final res = await api.post('/api/mobile/auth/login', {
        'email': emailCtrl.text.trim(),
        'password': passCtrl.text,
      });

      if (res['success'] == true && res['data'] != null) {
        if (mounted) {
          ref.read(authProvider.notifier).login(
                token: res['data']['token'],
                user: res['data']['user'],
              );
          context.go('/dashboard');
        }
      } else {
        setState(() => error = res['error']?['message'] ?? 'Login failed');
      }
    } catch (e) {
      setState(() => error = 'Network error. Try again.');
    } finally {
      if (mounted) setState(() => loading = false);
    }
  }

  @override
  Widget build(BuildContext context) {
    final h = MediaQuery.of(context).size.height;

    return Scaffold(
      backgroundColor: const Color(0xFFF5F6F8),
      body: SafeArea(
        child: SingleChildScrollView(
          physics: const BouncingScrollPhysics(),
          child: Padding(
            padding: const EdgeInsets.symmetric(horizontal: 20),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                SizedBox(height: h * 0.04),

                // ── Logo (compact) ─────────────────────────────────────
                Image.asset(
                  'assets/images/logo.png',
                  width: 48,
                  height: 48,
                  fit: BoxFit.contain,
                  errorBuilder: (_, __, ___) =>
                      const SizedBox(width: 48, height: 48),
                ),
                const SizedBox(height: 16),

                // ── Branding ────────────────────────────────────────────
                const Text(
                  'BOOKMARK',
                  style: TextStyle(
                    fontSize: 28,
                    fontWeight: FontWeight.w900,
                    color: AppColors.primary,
                    letterSpacing: 1.2,
                  ),
                ),
                const SizedBox(height: 4),
                Text(
                  'Field Force Manager',
                  style: TextStyle(
                    fontSize: 13,
                    color: Colors.black.withOpacity(0.5),
                    fontWeight: FontWeight.w500,
                  ),
                ),

                SizedBox(height: h * 0.05),

                // ── Sign In Card ────────────────────────────────────────
                Container(
                  padding: const EdgeInsets.all(24),
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.circular(18),
                    boxShadow: [
                      BoxShadow(
                        color: Colors.black.withOpacity(0.05),
                        blurRadius: 12,
                        offset: const Offset(0, 4),
                      ),
                    ],
                  ),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      // Header
                      const Text(
                        'Sign In',
                        style: TextStyle(
                          fontSize: 20,
                          fontWeight: FontWeight.w800,
                          color: Color(0xFF1E293B),
                        ),
                      ),
                      const SizedBox(height: 6),
                      Text(
                        'Access your field operations dashboard',
                        style: TextStyle(
                          fontSize: 12.5,
                          color: Colors.black.withOpacity(0.5),
                        ),
                      ),
                      const SizedBox(height: 20),

                      // Error message
                      if (error != null)
                        Container(
                          padding: const EdgeInsets.all(12),
                          decoration: BoxDecoration(
                            color: AppColors.error.withOpacity(0.08),
                            borderRadius: BorderRadius.circular(10),
                            border:
                                Border.all(color: AppColors.error.withOpacity(0.3)),
                          ),
                          child: Row(
                            children: [
                              Icon(Icons.error_outline_rounded,
                                  size: 18, color: AppColors.error),
                              const SizedBox(width: 10),
                              Expanded(
                                child: Text(
                                  error!,
                                  style: TextStyle(
                                    fontSize: 11.5,
                                    color: AppColors.error,
                                    fontWeight: FontWeight.w500,
                                  ),
                                ),
                              ),
                            ],
                          ),
                        ),
                      if (error != null) const SizedBox(height: 16),

                      // Email field
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          const Text(
                            'Email Address',
                            style: TextStyle(
                              fontSize: 12,
                              fontWeight: FontWeight.w600,
                              color: Color(0xFF1E293B),
                            ),
                          ),
                          const SizedBox(height: 8),
                          TextField(
                            controller: emailCtrl,
                            enabled: !loading,
                            keyboardType: TextInputType.emailAddress,
                            decoration: InputDecoration(
                              hintText: 'officer@bookmark.pk',
                              prefixIcon: Icon(Icons.email_outlined,
                                  size: 18, color: Colors.grey.shade400),
                              border: OutlineInputBorder(
                                borderRadius: BorderRadius.circular(12),
                                borderSide: BorderSide(
                                    color: Colors.grey.shade200, width: 1.2),
                              ),
                              enabledBorder: OutlineInputBorder(
                                borderRadius: BorderRadius.circular(12),
                                borderSide: BorderSide(
                                    color: Colors.grey.shade200, width: 1.2),
                              ),
                              focusedBorder: OutlineInputBorder(
                                borderRadius: BorderRadius.circular(12),
                                borderSide: const BorderSide(
                                    color: AppColors.primary, width: 1.5),
                              ),
                              filled: true,
                              fillColor: Colors.grey.shade50,
                              contentPadding: const EdgeInsets.symmetric(
                                  vertical: 12, horizontal: 14),
                              hintStyle: TextStyle(
                                  color: Colors.grey.shade400,
                                  fontSize: 13.5),
                            ),
                          ),
                        ],
                      ),
                      const SizedBox(height: 16),

                      // Password field
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          const Text(
                            'Password',
                            style: TextStyle(
                              fontSize: 12,
                              fontWeight: FontWeight.w600,
                              color: Color(0xFF1E293B),
                            ),
                          ),
                          const SizedBox(height: 8),
                          TextField(
                            controller: passCtrl,
                            enabled: !loading,
                            obscureText: obscure,
                            decoration: InputDecoration(
                              hintText: 'Enter your password',
                              prefixIcon: Icon(Icons.lock_outline_rounded,
                                  size: 18, color: Colors.grey.shade400),
                              suffixIcon: GestureDetector(
                                onTap: () =>
                                    setState(() => obscure = !obscure),
                                child: Icon(
                                  obscure
                                      ? Icons.visibility_off_rounded
                                      : Icons.visibility_rounded,
                                  size: 18,
                                  color: Colors.grey.shade400,
                                ),
                              ),
                              border: OutlineInputBorder(
                                borderRadius: BorderRadius.circular(12),
                                borderSide: BorderSide(
                                    color: Colors.grey.shade200, width: 1.2),
                              ),
                              enabledBorder: OutlineInputBorder(
                                borderRadius: BorderRadius.circular(12),
                                borderSide: BorderSide(
                                    color: Colors.grey.shade200, width: 1.2),
                              ),
                              focusedBorder: OutlineInputBorder(
                                borderRadius: BorderRadius.circular(12),
                                borderSide: const BorderSide(
                                    color: AppColors.primary, width: 1.5),
                              ),
                              filled: true,
                              fillColor: Colors.grey.shade50,
                              contentPadding: const EdgeInsets.symmetric(
                                  vertical: 12, horizontal: 14),
                              hintStyle: TextStyle(
                                  color: Colors.grey.shade400,
                                  fontSize: 13.5),
                            ),
                          ),
                        ],
                      ),
                      const SizedBox(height: 12),

                      // Forgot password
                      Align(
                        alignment: Alignment.centerRight,
                        child: GestureDetector(
                          onTap: () => context.go('/forgot-password'),
                          child: const Text(
                            'Forgot Password?',
                            style: TextStyle(
                              fontSize: 12,
                              color: AppColors.primary,
                              fontWeight: FontWeight.w600,
                            ),
                          ),
                        ),
                      ),
                      const SizedBox(height: 20),

                      // Sign In button
                      SizedBox(
                        width: double.infinity,
                        height: 48,
                        child: ElevatedButton.icon(
                          onPressed: loading ? null : _login,
                          icon: loading
                              ? const SizedBox(
                                  width: 18,
                                  height: 18,
                                  child: CircularProgressIndicator(
                                    strokeWidth: 2,
                                    valueColor: AlwaysStoppedAnimation<Color>(
                                        Colors.white),
                                  ),
                                )
                              : const Icon(Icons.login_rounded),
                          label: Text(loading ? 'Signing in...' : 'Sign In'),
                          style: ElevatedButton.styleFrom(
                            backgroundColor: AppColors.primary,
                            foregroundColor: Colors.white,
                            elevation: 0,
                            shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(12),
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),

                SizedBox(height: h * 0.04),

                // Footer
                Text(
                  '© 2026 Bookmark Publishing',
                  style: TextStyle(
                    fontSize: 11,
                    color: Colors.black.withOpacity(0.35),
                    fontWeight: FontWeight.w400,
                  ),
                ),

                SizedBox(height: h * 0.02),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
