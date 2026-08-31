import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:intl/intl.dart';
import 'package:geolocator/geolocator.dart';

import '../../../core/theme/app_theme.dart';
import '../../../core/network/dio_client.dart';
import '../../../core/constants/api_constants.dart';
import '../../../core/utils/quotes.dart';
import '../data/workday_status_provider.dart';
import '../../visits/data/visit_repository.dart';

class DayEndScreen extends ConsumerStatefulWidget {
  const DayEndScreen({super.key});

  @override
  ConsumerState<DayEndScreen> createState() => _DayEndScreenState();
}

class _DayEndScreenState extends ConsumerState<DayEndScreen> {
  bool _isEnding = false;
  final String _quote = getDayEndQuote();

  Future<Position?> _getLocation() async {
    try {
      bool serviceEnabled = await Geolocator.isLocationServiceEnabled();
      if (!serviceEnabled) return null;
      LocationPermission permission = await Geolocator.checkPermission();
      if (permission == LocationPermission.denied) {
        permission = await Geolocator.requestPermission();
        if (permission == LocationPermission.denied) return null;
      }
      return await Geolocator.getCurrentPosition(
        desiredAccuracy: LocationAccuracy.high,
        timeLimit: const Duration(seconds: 10),
      );
    } catch (_) {
      return null;
    }
  }

  Future<void> _endDay() async {
    final confirmed = await _showConfirmDialog();
    if (!confirmed) return;
    setState(() => _isEnding = true);
    try {
      final position = await _getLocation();
      final dio = ref.read(dioClientProvider);
      await dio.post(ApiConstants.dayEnd, data: {
        'action': 'end',
        'lat': position?.latitude ?? 24.8607,
        'lng': position?.longitude ?? 67.0011,
      });
      if (mounted) {
        ref.invalidate(workdayStatusProvider);
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: const Text('Day ended. Great work today!'),
            backgroundColor: AppColors.success,
            behavior: SnackBarBehavior.floating,
          ),
        );
        context.go('/dashboard');
      }
    } catch (e) {
      if (mounted) {
        final msg = e is ApiException ? e.message : 'Could not end day. Try again.';
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: Text(msg),
            backgroundColor: AppColors.error,
            behavior: SnackBarBehavior.floating,
          ),
        );
      }
    } finally {
      if (mounted) setState(() => _isEnding = false);
    }
  }

  Future<bool> _showConfirmDialog() async {
    return await showDialog<bool>(
          context: context,
          builder: (ctx) => AlertDialog(
            title: const Text('End Your Day?'),
            content: const Text(
              'This will lock your attendance record for today. '
              'Make sure all visits are completed or marked.',
            ),
            actions: [
              TextButton(
                onPressed: () => context.pop(false),
                child: const Text('Not Yet'),
              ),
              FilledButton(
                onPressed: () => context.pop(true),
                child: const Text('End Day'),
              ),
            ],
          ),
        ) ??
        false;
  }

  @override
  Widget build(BuildContext context) {
    final now = DateTime.now();

    return Scaffold(
      backgroundColor: AppColors.background,
      appBar: AppBar(
        title: const Text('End Your Day'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios_new_rounded),
          onPressed: () => context.go('/dashboard'),
        ),
      ),
      body: Padding(
        padding: const EdgeInsets.all(AppSpacing.md),
        child: Column(
          children: [
            // Summary hero
            GradientBox(
              colors: [const Color(0xFF1E293B), const Color(0xFF334155)],
              borderRadius: BorderRadius.circular(AppRadius.xl),
              child: Padding(
                padding: const EdgeInsets.all(28),
                child: Column(
                  children: [
                    const Icon(Icons.nightlight_round_outlined,
                        color: Colors.white70, size: 56)
                        .animate()
                        .scale(duration: 600.ms, curve: Curves.elasticOut),
                    const SizedBox(height: 16),
                    const Text(
                      'Wrapping Up',
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 22,
                        fontWeight: FontWeight.w700,
                      ),
                    ),
                    const SizedBox(height: 6),
                    Text(
                      DateFormat('hh:mm a').format(now),
                      style: const TextStyle(color: Colors.white60, fontSize: 14),
                    ),
                  ],
                ),
              ),
            ).animate().fadeIn(duration: 600.ms),

            const SizedBox(height: 16),

            // Evening motivational quote
            Container(
              padding: const EdgeInsets.all(16),
              decoration: BoxDecoration(
                color: const Color(0xFF1E293B).withOpacity(0.06),
                borderRadius: BorderRadius.circular(AppRadius.lg),
                border: Border.all(color: const Color(0xFF1E293B).withOpacity(0.15)),
              ),
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const Icon(Icons.format_quote_rounded,
                      color: Color(0xFF334155), size: 20),
                  const SizedBox(width: 10),
                  Expanded(
                    child: Text(
                      _quote,
                      style: Theme.of(context).textTheme.bodySmall?.copyWith(
                            color: const Color(0xFF334155),
                            fontStyle: FontStyle.italic,
                            height: 1.5,
                          ),
                    ),
                  ),
                ],
              ),
            ).animate(delay: 200.ms).fadeIn(duration: 500.ms),

            const SizedBox(height: 24),

            // Day summary stats — real data
            Consumer(builder: (_, ref, __) {
              final visits = ref.watch(visitListProvider).valueOrNull ?? [];
              final completed = visits.where((v) => v.status == 'COMPLETED').length;
              final missed = visits.where(
                  (v) => v.status == 'CANCELLED' || v.status == 'MISSED').length;
              return Row(
                children: [
                  Expanded(
                    child: _SummaryCard(
                      icon: Icons.check_circle_outline_rounded,
                      label: 'Completed',
                      value: '$completed',
                      color: AppColors.success,
                    ).animate(delay: 100.ms).slideX(begin: -0.2).fadeIn(),
                  ),
                  const SizedBox(width: 12),
                  Expanded(
                    child: _SummaryCard(
                      icon: Icons.cancel_outlined,
                      label: 'Missed',
                      value: '$missed',
                      color: AppColors.error,
                    ).animate(delay: 200.ms).slideX(begin: 0.2).fadeIn(),
                  ),
                ],
              );
            }),

            const SizedBox(height: AppSpacing.lg),

            const Spacer(),

            FilledButton.icon(
              icon: _isEnding
                  ? const SizedBox(
                      width: 20,
                      height: 20,
                      child: CircularProgressIndicator(
                          strokeWidth: 2, color: Colors.white),
                    )
                  : const Icon(Icons.stop_circle_outlined, size: 22),
              label: Text(
                _isEnding ? 'Ending...' : 'End My Day',
                style: const TextStyle(fontSize: 16),
              ),
              style: FilledButton.styleFrom(
                backgroundColor: AppColors.error,
                padding: const EdgeInsets.symmetric(vertical: 16),
              ),
              onPressed: _isEnding ? null : _endDay,
            ).animate(delay: 400.ms).slideY(begin: 0.2).fadeIn(),

            const SizedBox(height: AppSpacing.md),
          ],
        ),
      ),
    );
  }
}

class _SummaryCard extends StatelessWidget {
  final IconData icon;
  final String label;
  final String value;
  final Color color;
  const _SummaryCard(
      {required this.icon,
      required this.label,
      required this.value,
      required this.color});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: AppColors.surface,
        borderRadius: BorderRadius.circular(AppRadius.lg),
        border: Border.all(color: color.withOpacity(0.3)),
      ),
      child: Row(
        children: [
          Icon(icon, color: color, size: 28),
          const SizedBox(width: 12),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(value,
                  style: Theme.of(context)
                      .textTheme
                      .headlineSmall
                      ?.copyWith(color: color, fontWeight: FontWeight.w800)),
              Text(label, style: Theme.of(context).textTheme.labelSmall),
            ],
          ),
        ],
      ),
    );
  }
}
