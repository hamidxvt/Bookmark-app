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

  void _clearError() {
    ref.read(authProvider.notifier).clearError();
  }

  @override
  Widget build(BuildContext context) {
    final auth = ref.watch(authProvider);
    final size = MediaQuery.sizeOf(context);

    return Scaffold(
      body: Stack(
        children: [
          // Background gradient header
          Positioned(
            top: 0, left: 0, right: 0,
            height: size.height * 0.42,
            child: const GradientBox(
              colors: AppColors.primaryGradient,
              child: SizedBox.expand(),
            ),
          ),

          // Decorative circles
          Positioned(
            top: -60, right: -60,
            child: Container(
              width: 200, height: 200,
              decoration: BoxDecoration(
                shape: BoxShape.circle,
                color: Colors.white.withOpacity(0.05),
              ),
            ),
          ),
          Positioned(
            top: 40, left: -80,
            child: Container(
              width: 180, height: 180,
              decoration: BoxDecoration(
                shape: BoxShape.circle,
                color: Colors.white.withOpacity(0.07),
              ),
            ),
          ),

          SafeArea(
            child: SingleChildScrollView(
              child: Column(
                children: [
                  // Logo + title section
                  SizedBox(
                    height: size.height * 0.32,
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Container(
                          width: 72,
                          height: 72,
                          decoration: BoxDecoration(
                            color: Colors.white.withOpacity(0.15),
                            borderRadius: BorderRadius.circular(20),
                            border: Border.all(
                              color: Colors.white.withOpacity(0.3),
                              width: 1.5,
                            ),
                          ),
                          child: const Icon(
                            Icons.bookmark_rounded,
                            color: Colors.white,
                            size: 40,
                          ),
                        )
                            .animate()
                            .scale(duration: 600.ms, curve: Curves.elasticOut)
                            .fadeIn(duration: 400.ms),
                        const SizedBox(height: 16),
                        Text(
                          'Bookmark SFA',
                          style: Theme.of(context).textTheme.headlineMedium?.copyWith(
                                color: Colors.white,
                                fontWeight: FontWeight.w800,
                                letterSpacing: -0.5,
                              ),
                        )
                            .animate(delay: 200.ms)
                            .slideY(begin: 0.3, end: 0, duration: 500.ms, curve: Curves.easeOut)
                            .fadeIn(),
                        const SizedBox(height: 6),
                        Text(
                          'Field Force Management',
                          style: Theme.of(context).textTheme.bodyMedium?.copyWith(
                                color: Colors.white.withOpacity(0.75),
                                letterSpacing: 0.5,
                              ),
                        )
                            .animate(delay: 300.ms)
                            .slideY(begin: 0.3, end: 0, duration: 500.ms, curve: Curves.easeOut)
                            .fadeIn(),
                      ],
                    ),
                  ),

                  // Card form
                  Padding(
                    padding: const EdgeInsets.fromLTRB(20, 0, 20, 32),
                    child: Card(
                      elevation: 16,
                      shadowColor: AppColors.primary.withOpacity(0.2),
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(AppRadius.xl),
                      ),
                      child: Padding(
                        padding: const EdgeInsets.all(28),
                        child: Form(
                          key: _formKey,
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text(
                                'Welcome back',
                                style: Theme.of(context).textTheme.titleLarge,
                              ),
                              const SizedBox(height: 4),
                              Text(
                                'Sign in to your account',
                                style: Theme.of(context).textTheme.bodyMedium,
                              ),
                              const SizedBox(height: 28),

                              // Email
                              TextFormField(
                                controller: _emailCtrl,
                                keyboardType: TextInputType.emailAddress,
                                textInputAction: TextInputAction.next,
                                autocorrect: false,
                                decoration: const InputDecoration(
                                  labelText: 'Email',
                                  hintText: 'officer@bookmark.pk',
                                  prefixIcon: Icon(Icons.email_outlined),
                                ),
                                validator: (v) {
                                  if (v == null || v.isEmpty) return 'Email is required';
                                  if (!v.contains('@')) return 'Enter a valid email';
                                  return null;
                                },
                              ),
                              const SizedBox(height: 16),

                              // Password
                              TextFormField(
                                controller: _passCtrl,
                                obscureText: _obscure,
                                textInputAction: TextInputAction.done,
                                onFieldSubmitted: (_) => _submit(),
                                decoration: InputDecoration(
                                  labelText: 'Password',
                                  prefixIcon: const Icon(Icons.lock_outline_rounded),
                                  suffixIcon: IconButton(
                                    icon: Icon(
                                      _obscure
                                          ? Icons.visibility_outlined
                                          : Icons.visibility_off_outlined,
                                    ),
                                    onPressed: () => setState(() => _obscure = !_obscure),
                                  ),
                                ),
                                validator: (v) {
                                  if (v == null || v.isEmpty) return 'Password is required';
                                  if (v.length < 6) return 'Minimum 6 characters';
                                  return null;
                                },
                              ),

                              // Error message with close button
                              if (auth.error != null) ...[
                                const SizedBox(height: 16),
                                Container(
                                  padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 12),
                                  decoration: BoxDecoration(
                                    color: AppColors.error.withOpacity(0.08),
                                    borderRadius: BorderRadius.circular(AppRadius.sm),
                                    border: Border.all(
                                      color: AppColors.error.withOpacity(0.3),
                                    ),
                                  ),
                                  child: Row(
                                    children: [
                                      Icon(Icons.error_outline_rounded,
                                          color: AppColors.error, size: 18),
                                      const SizedBox(width: 10),
                                      Expanded(
                                        child: Text(
                                          auth.error!,
                                          style: Theme.of(context).textTheme.bodySmall?.copyWith(
                                                color: AppColors.error,
                                                fontWeight: FontWeight.w500,
                                              ),
                                        ),
                                      ),
                                      IconButton(
                                        icon: Icon(Icons.close_rounded,
                                            color: AppColors.error, size: 18),
                                        onPressed: _clearError,
                                        padding: EdgeInsets.zero,
                                        constraints: const BoxConstraints(),
                                      ),
                                    ],
                                  ),
                                ).animate().shakeX(duration: 400.ms, hz: 3, amount: 4),
                              ],

                              const SizedBox(height: 28),

                              FilledButton(
                                onPressed: auth.isLoading ? null : _submit,
                                child: auth.isLoading
                                    ? const SizedBox(
                                        width: 22,
                                        height: 22,
                                        child: CircularProgressIndicator(
                                          strokeWidth: 2.5,
                                          color: Colors.white,
                                        ),
                                      )
                                    : const Text('Sign In'),
                              ),
                            ],
                          ),
                        ),
                      ),
                    )
                        .animate(delay: 400.ms)
                        .slideY(begin: 0.2, end: 0, duration: 600.ms, curve: Curves.easeOutCubic)
                        .fadeIn(),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
