import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:intl/intl.dart';

import '../../../core/theme/app_theme.dart';
import '../../../core/network/dio_client.dart';
import '../../../core/constants/api_constants.dart';

final payrollProvider = FutureProvider<Map<String, dynamic>>((ref) async {
  final dio = ref.read(dioClientProvider);
  final res = await dio.get(ApiConstants.myPayroll);
  return res.data['data'] as Map<String, dynamic>;
});

class PayrollScreen extends ConsumerWidget {
  const PayrollScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final payrollAsync = ref.watch(payrollProvider);

    return Scaffold(
      backgroundColor: AppColors.background,
      appBar: AppBar(
        title: const Text('My Earnings'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios_new_rounded),
          onPressed: () => context.go('/dashboard'),
        ),
        actions: [
          IconButton(
            icon: const Icon(Icons.refresh_rounded),
            onPressed: () => ref.invalidate(payrollProvider),
          ),
        ],
      ),
      body: payrollAsync.when(
        loading: () => const Center(child: CircularProgressIndicator()),
        error: (e, _) => Center(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              const Icon(Icons.account_balance_wallet_outlined, size: 48, color: AppColors.textMuted),
              const SizedBox(height: 12),
              Text('Failed to load payroll', style: Theme.of(context).textTheme.bodyMedium),
              const SizedBox(height: 8),
              TextButton(onPressed: () => ref.invalidate(payrollProvider), child: const Text('Retry')),
            ],
          ),
        ),
        data: (data) {
          final summary = data['summary'] as Map<String, dynamic>;
          final shifts = data['dailyShifts'] as List<dynamic>? ?? [];
          final month = data['month'] as String? ?? '';

          final totalShifts = summary['totalShifts'] as int? ?? 0;
          final presentDays = summary['presentDays'] as int? ?? 0;
          final completedVisits = summary['completedVisits'] as int? ?? 0;
          final ratePerVisit = (summary['ratePerVisit'] as num?)?.toDouble() ?? 500;
          final totalEarned = (summary['totalEarned'] as num?)?.toDouble() ?? 0;

          return RefreshIndicator(
            onRefresh: () async => ref.invalidate(payrollProvider),
            child: ListView(
              padding: const EdgeInsets.all(AppSpacing.md),
              children: [
                // Month header
                Container(
                  padding: const EdgeInsets.all(20),
                  decoration: BoxDecoration(
                    gradient: LinearGradient(colors: AppColors.primaryGradient),
                    borderRadius: BorderRadius.circular(AppRadius.xl),
                  ),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(month, style: const TextStyle(color: Colors.white70, fontSize: 13)),
                      const SizedBox(height: 4),
                      Text(
                        'Rs ${NumberFormat('#,##0').format(totalEarned)}',
                        style: const TextStyle(color: Colors.white, fontSize: 32, fontWeight: FontWeight.w800),
                      ),
                      const SizedBox(height: 4),
                      Text(
                        '$completedVisits visits × Rs ${NumberFormat('#,##0').format(ratePerVisit)}',
                        style: const TextStyle(color: Colors.white60, fontSize: 12),
                      ),
                    ],
                  ),
                ).animate().fadeIn().slideY(begin: -0.1),

                const SizedBox(height: AppSpacing.md),

                // KPI row
                Row(
                  children: [
                    _KpiCard(label: 'Shifts', value: '$totalShifts', icon: Icons.calendar_today_rounded, color: AppColors.primary),
                    const SizedBox(width: 12),
                    _KpiCard(label: 'Present', value: '$presentDays', icon: Icons.check_circle_outline_rounded, color: AppColors.success),
                    const SizedBox(width: 12),
                    _KpiCard(label: 'Visits', value: '$completedVisits', icon: Icons.route_rounded, color: AppColors.secondary),
                  ],
                ).animate(delay: 100.ms).fadeIn(),

                const SizedBox(height: AppSpacing.md),

                // Daily shifts
                Text('Daily Attendance', style: Theme.of(context).textTheme.titleMedium),
                const SizedBox(height: AppSpacing.sm),

                ...shifts.asMap().entries.map((entry) {
                  final shift = entry.value as Map<String, dynamic>;
                  final date = DateTime.tryParse(shift['date'] as String? ?? '') ?? DateTime.now();
                  final status = shift['status'] as String? ?? 'absent';
                  final startAt = shift['startAt'] != null ? DateTime.tryParse(shift['startAt'] as String) : null;
                  final endAt = shift['endAt'] != null ? DateTime.tryParse(shift['endAt'] as String) : null;
                  final hours = shift['hoursWorked'] as String?;

                  return _ShiftRow(
                    date: date,
                    status: status,
                    startAt: startAt,
                    endAt: endAt,
                    hours: hours,
                  ).animate(delay: Duration(milliseconds: 50 * entry.key)).fadeIn().slideX(begin: 0.1);
                }),

                if (shifts.isEmpty)
                  Container(
                    padding: const EdgeInsets.all(24),
                    decoration: BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.circular(AppRadius.lg),
                    ),
                    child: const Center(
                      child: Text('No shifts recorded this month', style: TextStyle(color: AppColors.textMuted)),
                    ),
                  ),

                const SizedBox(height: AppSpacing.lg),
              ],
            ),
          );
        },
      ),
    );
  }
}

class _KpiCard extends StatelessWidget {
  final String label;
  final String value;
  final IconData icon;
  final Color color;

  const _KpiCard({required this.label, required this.value, required this.icon, required this.color});

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: Container(
        padding: const EdgeInsets.all(12),
        decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.circular(AppRadius.md),
          border: Border.all(color: AppColors.outline),
        ),
        child: Column(
          children: [
            Icon(icon, color: color, size: 22),
            const SizedBox(height: 6),
            Text(value, style: TextStyle(fontSize: 20, fontWeight: FontWeight.w700, color: color)),
            Text(label, style: const TextStyle(fontSize: 11, color: AppColors.textMuted)),
          ],
        ),
      ),
    );
  }
}

class _ShiftRow extends StatelessWidget {
  final DateTime date;
  final String status;
  final DateTime? startAt;
  final DateTime? endAt;
  final String? hours;

  const _ShiftRow({required this.date, required this.status, this.startAt, this.endAt, this.hours});

  @override
  Widget build(BuildContext context) {
    final isPresent = status == 'present';
    final fmt = DateFormat('h:mm a');

    return Container(
      margin: const EdgeInsets.only(bottom: 8),
      padding: const EdgeInsets.all(12),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(AppRadius.md),
        border: Border.all(color: AppColors.outline.withOpacity(0.5)),
      ),
      child: Row(
        children: [
          Container(
            width: 40, height: 40,
            decoration: BoxDecoration(
              color: isPresent ? AppColors.success.withOpacity(0.1) : AppColors.error.withOpacity(0.08),
              borderRadius: BorderRadius.circular(10),
            ),
            child: Center(
              child: Icon(
                isPresent ? Icons.check_rounded : Icons.close_rounded,
                color: isPresent ? AppColors.success : AppColors.error,
                size: 20,
              ),
            ),
          ),
          const SizedBox(width: 12),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  DateFormat('EEE, dd MMM').format(date),
                  style: const TextStyle(fontSize: 13, fontWeight: FontWeight.w600),
                ),
                if (isPresent && startAt != null)
                  Text(
                    '${fmt.format(startAt!)} — ${endAt != null ? fmt.format(endAt!) : "ongoing"}${hours != null ? " · ${hours}h" : ""}',
                    style: const TextStyle(fontSize: 11, color: AppColors.textMuted),
                  )
                else
                  Text(
                    status == 'cannot_work' ? 'Cannot work' : 'Absent',
                    style: TextStyle(fontSize: 11, color: status == 'cannot_work' ? AppColors.warning : AppColors.error),
                  ),
              ],
            ),
          ),
          if (isPresent)
            Container(
              padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
              decoration: BoxDecoration(
                color: AppColors.success.withOpacity(0.1),
                borderRadius: BorderRadius.circular(6),
              ),
              child: Text('Present', style: TextStyle(fontSize: 10, color: AppColors.success, fontWeight: FontWeight.w600)),
            ),
        ],
      ),
    );
  }
}
