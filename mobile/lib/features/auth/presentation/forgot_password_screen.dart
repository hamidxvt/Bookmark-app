import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:dio/dio.dart';

import '../../../core/theme/app_theme.dart';
import '../../../core/network/dio_client.dart';

enum _Step { email, otp, done }

class ForgotPasswordScreen extends ConsumerStatefulWidget {
  const ForgotPasswordScreen({super.key});

  @override
  ConsumerState<ForgotPasswordScreen> createState() => _ForgotPasswordScreenState();
}

class _ForgotPasswordScreenState extends ConsumerState<ForgotPasswordScreen> {
  _Step _step = _Step.email;
  bool _loading = false;
  String? _error;
  String _userEmail = '';
  String? _devOtp; // only shown when SMTP not configured

  final _emailCtrl = TextEditingController();
  final _otpCtrl = TextEditingController();
  final _passCtrl = TextEditingController();
  final _confirmCtrl = TextEditingController();
  bool _obscure = true;
  bool _obscureConfirm = true;

  @override
  void dispose() {
    _emailCtrl.dispose();
    _otpCtrl.dispose();
    _passCtrl.dispose();
    _confirmCtrl.dispose();
    super.dispose();
  }

  Future<void> _sendOtp() async {
    final email = _emailCtrl.text.trim();
    if (email.isEmpty || !email.contains('@')) {
      setState(() => _error = 'Enter a valid email address');
      return;
    }
    setState(() { _loading = true; _error = null; });
    try {
      final dio = ref.read(dioClientProvider);
      final res = await dio.post('/forgot-password', data: {'email': email});
      final body = res.data as Map<String, dynamic>;
      _userEmail = email;
      _devOtp = body['devOtp'] as String?;
      setState(() { _step = _Step.otp; _loading = false; });
    } on DioException catch (e) {
      setState(() {
        _error = (e.response?.data as Map?)?['error'] as String? ?? 'Failed to send OTP';
        _loading = false;
      });
    } catch (_) {
      setState(() { _error = 'Something went wrong'; _loading = false; });
    }
  }

  Future<void> _resetPassword() async {
    final otp = _otpCtrl.text.trim();
    final pass = _passCtrl.text;
    final confirm = _confirmCtrl.text;

    if (otp.length != 6) {
      setState(() => _error = 'Enter the 6-digit OTP');
      return;
    }
    if (pass.length < 6) {
      setState(() => _error = 'Password must be at least 6 characters');
      return;
    }
    if (pass != confirm) {
      setState(() => _error = 'Passwords do not match');
      return;
    }

    setState(() { _loading = true; _error = null; });
    try {
      final dio = ref.read(dioClientProvider);
      await dio.post('/reset-password', data: {
        'email': _userEmail,
        'otp': otp,
        'newPassword': pass,
      });
      setState(() { _step = _Step.done; _loading = false; });
    } on DioException catch (e) {
      setState(() {
        _error = (e.response?.data as Map?)?['error'] as String? ?? 'Failed to reset password';
        _loading = false;
      });
    } catch (_) {
      setState(() { _error = 'Something went wrong'; _loading = false; });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: AppColors.background,
      appBar: AppBar(
        title: const Text('Forgot Password'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios_new_rounded),
          onPressed: () => context.go('/login'),
        ),
        backgroundColor: AppColors.primary,
        foregroundColor: Colors.white,
        elevation: 0,
      ),
      body: SafeArea(
        child: SingleChildScrollView(
          padding: const EdgeInsets.all(24),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              // ── Step indicator ──────────────────────────────
              _StepIndicator(step: _step),
              const SizedBox(height: 32),

              // ── Content per step ────────────────────────────
              if (_step == _Step.email) _buildEmailStep(),
              if (_step == _Step.otp) _buildOtpStep(),
              if (_step == _Step.done) _buildDoneStep(),

              // ── Error ───────────────────────────────────────
              if (_error != null) ...[
                const SizedBox(height: 16),
                Container(
                  padding: const EdgeInsets.symmetric(horizontal: 14, vertical: 12),
                  decoration: BoxDecoration(
                    color: AppColors.error.withOpacity(0.08),
                    borderRadius: BorderRadius.circular(AppRadius.md),
                    border: Border.all(color: AppColors.error.withOpacity(0.3)),
                  ),
                  child: Row(
                    children: [
                      const Icon(Icons.error_outline_rounded, color: AppColors.error, size: 18),
                      const SizedBox(width: 10),
                      Expanded(
                        child: Text(_error!,
                            style: const TextStyle(color: AppColors.error, fontSize: 13)),
                      ),
                    ],
                  ),
                ).animate().shakeX(duration: 400.ms, hz: 3),
              ],
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildEmailStep() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text('Reset your password',
            style: Theme.of(context).textTheme.headlineSmall?.copyWith(fontWeight: FontWeight.w800)),
        const SizedBox(height: 8),
        const Text(
          'Enter your registered email address. We will send you a 6-digit OTP to reset your password.',
          style: TextStyle(color: Color(0xFF64748B), fontSize: 14),
        ),
        const SizedBox(height: 28),
        TextFormField(
          controller: _emailCtrl,
          keyboardType: TextInputType.emailAddress,
          autocorrect: false,
          textInputAction: TextInputAction.done,
          onFieldSubmitted: (_) => _loading ? null : _sendOtp(),
          decoration: const InputDecoration(
            labelText: 'Email Address',
            prefixIcon: Icon(Icons.email_outlined),
            hintText: 'officer@bookmark.pk',
          ),
        ),
        const SizedBox(height: 24),
        FilledButton(
          onPressed: _loading ? null : _sendOtp,
          child: _loading
              ? const SizedBox(
                  width: 22, height: 22,
                  child: CircularProgressIndicator(strokeWidth: 2.5, color: Colors.white))
              : const Text('Send OTP'),
        ),
      ],
    ).animate().fadeIn(duration: 400.ms);
  }

  Widget _buildOtpStep() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text('Enter OTP & New Password',
            style: Theme.of(context).textTheme.headlineSmall?.copyWith(fontWeight: FontWeight.w800)),
        const SizedBox(height: 8),
        Text(
          'An OTP was sent to $_userEmail. Enter it below along with your new password.',
          style: const TextStyle(color: Color(0xFF64748B), fontSize: 14),
        ),

        // Dev OTP banner (only when SMTP not configured)
        if (_devOtp != null) ...[
          const SizedBox(height: 12),
          Container(
            padding: const EdgeInsets.all(14),
            decoration: BoxDecoration(
              color: AppColors.warning.withOpacity(0.1),
              borderRadius: BorderRadius.circular(AppRadius.md),
              border: Border.all(color: AppColors.warning.withOpacity(0.4)),
            ),
            child: Row(
              children: [
                const Icon(Icons.info_outline_rounded, color: AppColors.warning, size: 18),
                const SizedBox(width: 10),
                Expanded(
                  child: Text(
                    'Dev mode — your OTP is: $_devOtp\n(Configure SMTP to send via email)',
                    style: const TextStyle(color: AppColors.warning, fontSize: 12, fontWeight: FontWeight.w600),
                  ),
                ),
              ],
            ),
          ),
        ],

        const SizedBox(height: 24),
        TextFormField(
          controller: _otpCtrl,
          keyboardType: TextInputType.number,
          maxLength: 6,
          textAlign: TextAlign.center,
          style: const TextStyle(fontSize: 24, fontWeight: FontWeight.w800, letterSpacing: 10),
          decoration: const InputDecoration(
            labelText: '6-Digit OTP',
            counterText: '',
            hintText: '------',
          ),
        ),
        const SizedBox(height: 16),
        TextFormField(
          controller: _passCtrl,
          obscureText: _obscure,
          textInputAction: TextInputAction.next,
          decoration: InputDecoration(
            labelText: 'New Password',
            prefixIcon: const Icon(Icons.lock_outline_rounded),
            suffixIcon: IconButton(
              icon: Icon(_obscure ? Icons.visibility_outlined : Icons.visibility_off_outlined),
              onPressed: () => setState(() => _obscure = !_obscure),
            ),
          ),
        ),
        const SizedBox(height: 14),
        TextFormField(
          controller: _confirmCtrl,
          obscureText: _obscureConfirm,
          textInputAction: TextInputAction.done,
          onFieldSubmitted: (_) => _loading ? null : _resetPassword(),
          decoration: InputDecoration(
            labelText: 'Confirm New Password',
            prefixIcon: const Icon(Icons.lock_reset_rounded),
            suffixIcon: IconButton(
              icon: Icon(_obscureConfirm ? Icons.visibility_outlined : Icons.visibility_off_outlined),
              onPressed: () => setState(() => _obscureConfirm = !_obscureConfirm),
            ),
          ),
        ),
        const SizedBox(height: 24),
        FilledButton(
          onPressed: _loading ? null : _resetPassword,
          child: _loading
              ? const SizedBox(
                  width: 22, height: 22,
                  child: CircularProgressIndicator(strokeWidth: 2.5, color: Colors.white))
              : const Text('Reset Password'),
        ),
        const SizedBox(height: 12),
        TextButton(
          onPressed: _loading ? null : () => setState(() { _step = _Step.email; _error = null; }),
          child: const Text('← Back to Email'),
        ),
      ],
    ).animate().fadeIn(duration: 400.ms);
  }

  Widget _buildDoneStep() {
    return Column(
      children: [
        const SizedBox(height: 32),
        const Icon(Icons.check_circle_rounded, color: AppColors.success, size: 72),
        const SizedBox(height: 20),
        Text('Password Reset!',
            style: Theme.of(context).textTheme.headlineSmall?.copyWith(fontWeight: FontWeight.w800)),
        const SizedBox(height: 10),
        const Text(
          'Your password has been updated. You can now log in with your new password.',
          textAlign: TextAlign.center,
          style: TextStyle(color: Color(0xFF64748B), fontSize: 14),
        ),
        const SizedBox(height: 32),
        FilledButton.icon(
          icon: const Icon(Icons.login_rounded),
          label: const Text('Go to Login'),
          onPressed: () => context.go('/login'),
        ),
      ],
    ).animate().scale(duration: 500.ms, curve: Curves.elasticOut).fadeIn();
  }
}

// ── Step indicator ──────────────────────────────────────────────────────────
class _StepIndicator extends StatelessWidget {
  final _Step step;
  const _StepIndicator({required this.step});

  @override
  Widget build(BuildContext context) {
    final steps = ['Email', 'Reset', 'Done'];
    final current = step.index;
    return Row(
      children: List.generate(steps.length * 2 - 1, (i) {
        if (i.isOdd) {
          return Expanded(
            child: Container(
              height: 2,
              color: (i ~/ 2) < current ? AppColors.primary : AppColors.outline,
            ),
          );
        }
        final idx = i ~/ 2;
        final done = idx < current;
        final active = idx == current;
        return AnimatedContainer(
          duration: const Duration(milliseconds: 300),
          width: 28, height: 28,
          decoration: BoxDecoration(
            shape: BoxShape.circle,
            color: done || active ? AppColors.primary : AppColors.outline,
          ),
          child: Center(
            child: done
                ? const Icon(Icons.check, color: Colors.white, size: 14)
                : Text('${idx + 1}',
                    style: TextStyle(
                      color: active ? Colors.white : Colors.white70,
                      fontSize: 12, fontWeight: FontWeight.w700,
                    )),
          ),
        );
      }),
    );
  }
}
