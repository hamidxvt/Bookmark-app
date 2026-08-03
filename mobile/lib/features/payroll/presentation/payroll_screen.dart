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

final _pkr = NumberFormat('#,##0', 'en_PK');
String _fmt(num v) => 'Rs ${_pkr.format(v)}';

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
              Icon(Icons.account_balance_wallet_outlined, size: 48, color: AppColors.outline),
              const SizedBox(height: 12),
              const Text('Failed to load payroll'),
              TextButton(onPressed: () => ref.invalidate(payrollProvider), child: const Text('Retry')),
            ],
          ),
        ),
        data: (data) {
          final summary   = data['summary']         as Map<String, dynamic>? ?? {};
          final salary    = data['salaryBreakdown']  as Map<String, dynamic>? ?? {};
          final shifts    = data['dailyShifts']      as List<dynamic>? ?? [];
          final month     = data['month']            as String? ?? '';

          final presentDays     = summary['presentDays']     as int? ?? 0;
          final completedVisits = summary['completedVisits'] as int? ?? 0;
          final adhocVisits     = summary['adhocVisits']     as int? ?? 0;
          final totalShifts     = summary['totalShifts']     as int? ?? 0;

          final basicSalary     = (salary['basicSalary']         as num?)?.toDouble() ?? 0;
          final earnedBasic     = (salary['earnedBasic']          as num?)?.toDouble() ?? 0;
          final runningPay      = (salary['runningPay']           as num?)?.toDouble() ?? 0;
          final adhocBonus      = (salary['adhocBonus']           as num?)?.toDouble() ?? 0;
          final grossSalary     = (salary['grossSalary']          as num?)?.toDouble() ?? 0;
          final secDeposit      = (salary['securityDepositHeld']  as num?)?.toDouble() ?? 0;
          final netSalary       = (salary['netSalary']            as num?)?.toDouble() ?? 0;
          final rewardPoints    = salary['rewardPoints']           as int? ?? 0;
          final rewardValue     = (salary['rewardValue']           as num?)?.toDouble() ?? 0;
          final totalEarned     = (salary['totalEarned']           as num?)?.toDouble() ?? (netSalary + rewardValue);

          return RefreshIndicator(
            onRefresh: () async => ref.invalidate(payrollProvider),
            child: ListView(
              padding: const EdgeInsets.all(AppSpacing.md),
              children: [
                // ── Hero card ─────────────────────────────────────────────
                Container(
                  padding: const EdgeInsets.all(20),
                  decoration: BoxDecoration(
                    gradient: LinearGradient(colors: AppColors.primaryGradient),
                    borderRadius: BorderRadius.circular(AppRadius.xl),
                  ),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(month, style: const TextStyle(color: Colors.white60, fontSize: 13)),
                      const SizedBox(height: 4),
                      Text(_fmt(totalEarned),
                          style: const TextStyle(color: Colors.white, fontSize: 32, fontWeight: FontWeight.w800)),
                      const SizedBox(height: 2),
                      Text('Net take-home this month',
                          style: const TextStyle(color: Colors.white60, fontSize: 12)),
                      const SizedBox(height: 16),
                      Row(children: [
                        _HeroBadge(label: 'Shifts', value: '$totalShifts', icon: Icons.calendar_today_rounded),
                        const SizedBox(width: 12),
                        _HeroBadge(label: 'Present', value: '$presentDays', icon: Icons.check_circle_outline_rounded),
                        const SizedBox(width: 12),
                        _HeroBadge(label: 'Visits', value: '$completedVisits', icon: Icons.route_rounded),
                        if (adhocVisits > 0) ...[
                          const SizedBox(width: 12),
                          _HeroBadge(label: 'Ad-hoc', value: '$adhocVisits', icon: Icons.add_location_alt_rounded, highlight: true),
                        ],
                      ]),
                    ],
                  ),
                ).animate().fadeIn().slideY(begin: -0.08),

                const SizedBox(height: 20),

                // ── Salary Breakdown ──────────────────────────────────────
                _SectionTitle('Salary Breakdown'),
                const SizedBox(height: 8),
                _BreakdownCard(items: [
                  _SalaryRow('Basic Salary (full month)',  _fmt(basicSalary),   color: AppColors.textBody),
                  _SalaryRow('Earned Basic (${presentDays}d present)', _fmt(earnedBasic), color: AppColors.primary),
                  _SalaryRow('Running Pay (${completedVisits} visits)',  _fmt(runningPay),  color: AppColors.success),
                  if (adhocBonus > 0)
                    _SalaryRow('Ad-hoc Bonus ($adhocVisits visits × 50%)', _fmt(adhocBonus), color: Colors.orange),
                  _SalaryDivider(),
                  _SalaryRow('Gross Salary', _fmt(grossSalary), bold: true),
                  _SalaryRow('Security Deposit (held)', '- ${_fmt(secDeposit)}', color: AppColors.error),
                  _SalaryDivider(),
                  _SalaryRow('Net Salary', _fmt(netSalary), bold: true, color: AppColors.primary),
                  if (rewardValue > 0)
                    _SalaryRow('Reward Points ($rewardPoints pts)', '+ ${_fmt(rewardValue)}', color: Colors.amber[700]!),
                  _SalaryDivider(),
                  _SalaryRow('Total Take-home', _fmt(totalEarned), bold: true, large: true, color: AppColors.success),
                ]).animate(delay: 100.ms).fadeIn(),

                const SizedBox(height: 20),

                // ── Daily Attendance ──────────────────────────────────────
                _SectionTitle('Daily Attendance'),
                const SizedBox(height: 8),

                if (shifts.isEmpty)
                  Container(
                    padding: const EdgeInsets.all(24),
                    decoration: BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.circular(AppRadius.lg),
                    ),
                    child: Center(
                      child: Text('No shifts recorded this month',
                          style: TextStyle(color: AppColors.outline)),
                    ),
                  )
                else
                  ...shifts.asMap().entries.map((e) {
                    final shift  = e.value as Map<String, dynamic>;
                    final date   = DateTime.tryParse(shift['date'] as String? ?? '') ?? DateTime.now();
                    final status = shift['status'] as String? ?? 'absent';
                    final startAt = shift['startAt'] != null ? DateTime.tryParse(shift['startAt'] as String) : null;
                    final endAt   = shift['endAt']   != null ? DateTime.tryParse(shift['endAt']   as String) : null;
                    final hours   = shift['hoursWorked'] as String?;
                    return _ShiftRow(date: date, status: status, startAt: startAt, endAt: endAt, hours: hours)
                        .animate(delay: Duration(milliseconds: 40 * e.key)).fadeIn().slideX(begin: 0.08);
                  }),

                const SizedBox(height: 40),
              ],
            ),
          );
        },
      ),
    );
  }
}

// ── Widgets ───────────────────────────────────────────────────────────────────

class _SectionTitle extends StatelessWidget {
  final String text;
  const _SectionTitle(this.text);
  @override
  Widget build(BuildContext context) =>
      Text(text, style: Theme.of(context).textTheme.titleSmall?.copyWith(fontWeight: FontWeight.w700));
}

class _HeroBadge extends StatelessWidget {
  final String label, value;
  final IconData icon;
  final bool highlight;
  const _HeroBadge({required this.label, required this.value, required this.icon, this.highlight = false});
  @override
  Widget build(BuildContext context) => Expanded(
    child: Container(
      padding: const EdgeInsets.symmetric(vertical: 8, horizontal: 4),
      decoration: BoxDecoration(
        color: highlight ? Colors.amber.withOpacity(0.25) : Colors.white.withOpacity(0.12),
        borderRadius: BorderRadius.circular(10),
      ),
      child: Column(children: [
        Icon(icon, color: highlight ? Colors.amber[300] : Colors.white70, size: 16),
        const SizedBox(height: 3),
        Text(value, style: TextStyle(color: highlight ? Colors.amber[200] : Colors.white, fontSize: 16, fontWeight: FontWeight.w700)),
        Text(label, style: const TextStyle(color: Colors.white54, fontSize: 9)),
      ]),
    ),
  );
}

// ignore: must_be_immutable
class _BreakdownCard extends StatelessWidget {
  final List<Widget> items;
  const _BreakdownCard({required this.items});
  @override
  Widget build(BuildContext context) => Container(
    decoration: BoxDecoration(
      color: Colors.white,
      borderRadius: BorderRadius.circular(AppRadius.lg),
      border: Border.all(color: AppColors.outline.withOpacity(0.5)),
    ),
    child: Column(children: items),
  );
}

class _SalaryRow extends StatelessWidget {
  final String label, value;
  final Color? color;
  final bool bold, large;
  const _SalaryRow(this.label, this.value, {this.color, this.bold = false, this.large = false});
  @override
  Widget build(BuildContext context) => Padding(
    padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 10),
    child: Row(
      children: [
        Expanded(child: Text(label,
            style: TextStyle(fontSize: large ? 13 : 12, fontWeight: bold ? FontWeight.w700 : FontWeight.w400, color: AppColors.textBody))),
        Text(value,
            style: TextStyle(fontSize: large ? 15 : 13, fontWeight: bold ? FontWeight.w800 : FontWeight.w600,
                color: color ?? AppColors.textBody)),
      ],
    ),
  );
}

class _SalaryDivider extends StatelessWidget {
  const _SalaryDivider();
  @override
  Widget build(BuildContext context) => Divider(height: 1, thickness: 1, color: AppColors.outline.withOpacity(0.4), indent: 16, endIndent: 16);
}

class _ShiftRow extends StatelessWidget {
  final DateTime date;
  final String status;
  final DateTime? startAt, endAt;
  final String? hours;
  const _ShiftRow({required this.date, required this.status, this.startAt, this.endAt, this.hours});

  @override
  Widget build(BuildContext context) {
    final isPresent     = status == 'present';
    final isCannotWork  = status == 'cannot_work';
    final fmt           = DateFormat('h:mm a');
    final color = isPresent ? AppColors.success : isCannotWork ? AppColors.warning : AppColors.error;

    return Container(
      margin: const EdgeInsets.only(bottom: 8),
      padding: const EdgeInsets.all(12),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(AppRadius.md),
        border: Border.all(color: AppColors.outline.withOpacity(0.5)),
      ),
      child: Row(children: [
        Container(
          width: 40, height: 40,
          decoration: BoxDecoration(color: color.withOpacity(0.1), borderRadius: BorderRadius.circular(10)),
          child: Center(child: Icon(
            isPresent ? Icons.check_rounded : isCannotWork ? Icons.report_outlined : Icons.close_rounded,
            color: color, size: 20,
          )),
        ),
        const SizedBox(width: 12),
        Expanded(child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
          Text(DateFormat('EEE, dd MMM').format(date),
              style: const TextStyle(fontSize: 13, fontWeight: FontWeight.w600)),
          if (isPresent && startAt != null)
            Text(
              '${fmt.format(startAt!)} — ${endAt != null ? fmt.format(endAt!) : "ongoing"}'
              '${hours != null ? " · ${hours}h" : ""}',
              style: TextStyle(fontSize: 11, color: AppColors.textMuted),
            )
          else
            Text(isCannotWork ? 'Could not work' : 'Absent',
                style: TextStyle(fontSize: 11, color: color)),
        ])),
        Container(
          padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 3),
          decoration: BoxDecoration(color: color.withOpacity(0.08), borderRadius: BorderRadius.circular(6)),
          child: Text(
            isPresent ? 'Present' : isCannotWork ? 'Excused' : 'Absent',
            style: TextStyle(fontSize: 10, color: color, fontWeight: FontWeight.w600),
          ),
        ),
      ]),
    );
  }
}
