import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:intl/intl.dart';

import '../../../core/theme/app_theme.dart';
import '../data/leaves_provider.dart';
import '../data/leaves_repository.dart';

class LeavesScreen extends ConsumerWidget {
  const LeavesScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final balancesAsync = ref.watch(leaveBalancesProvider);
    final historyAsync = ref.watch(leaveHistoryProvider);

    return Scaffold(
      backgroundColor: AppColors.background,
      appBar: AppBar(
        title: const Text('Leave Management'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios_new_rounded),
          onPressed: () => context.go('/dashboard'),
        ),
        actions: [
          FilledButton.tonalIcon(
            icon: const Icon(Icons.add_rounded, size: 18),
            label: const Text('Apply'),
            onPressed: () => _showApplySheet(context, ref),
          ),
          const SizedBox(width: 8),
        ],
      ),
      body: balancesAsync.when(
        loading: () => const Center(child: CircularProgressIndicator()),
        error: (err, st) => Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Icon(Icons.error_outline, size: 48, color: AppColors.error),
              const SizedBox(height: 12),
              Text('Failed to load leave data', style: Theme.of(context).textTheme.titleMedium),
              const SizedBox(height: 8),
              Text(err.toString(), style: Theme.of(context).textTheme.bodySmall, textAlign: TextAlign.center),
            ],
          ),
        ),
        data: (balances) => ListView(
          padding: const EdgeInsets.all(AppSpacing.md),
          children: [
            // Balance cards
            Row(
              children: [
                Expanded(
                  child: _BalanceTile(
                    balance: balances['sick'] ?? LeaveBalance(totalDays: 0, usedDays: 0, type: 'Sick'),
                    color: AppColors.error,
                    icon: Icons.sick_outlined,
                  ).animate(delay: 50.ms).slideX(begin: -0.2).fadeIn(),
                ),
                const SizedBox(width: 12),
                Expanded(
                  child: _BalanceTile(
                    balance: balances['casual'] ?? LeaveBalance(totalDays: 0, usedDays: 0, type: 'Casual'),
                    color: AppColors.info,
                    icon: Icons.beach_access_outlined,
                  ).animate(delay: 100.ms).slideX(begin: 0.2).fadeIn(),
                ),
              ],
            ),
            const SizedBox(height: 12),
            _TotalBalanceBanner(balances: balances)
                .animate(delay: 150.ms)
                .slideY(begin: 0.1)
                .fadeIn(),
            const SizedBox(height: 20),
            Text('Leave History',
                style: Theme.of(context).textTheme.titleMedium),
            const SizedBox(height: 12),
            historyAsync.when(
              loading: () => const Padding(
                padding: EdgeInsets.all(16.0),
                child: CircularProgressIndicator(),
              ),
              error: (err, st) => Padding(
                padding: const EdgeInsets.all(16.0),
                child: Text('Failed to load history: $err'),
              ),
              data: (history) => history.isEmpty
                  ? Padding(
                      padding: const EdgeInsets.all(16.0),
                      child: Text('No leave requests yet',
                          style: Theme.of(context).textTheme.bodySmall),
                    )
                  : Column(
                      children: history
                          .asMap()
                          .entries
                          .map((e) => _LeaveHistoryTile(request: e.value)
                              .animate(delay: (200 + e.key * 50).ms)
                              .slideX(begin: 0.1)
                              .fadeIn())
                          .toList(),
                    ),
            ),
            const SizedBox(height: AppSpacing.lg),
          ],
        ),
      ),
    );
  }

  void _showApplySheet(BuildContext context, WidgetRef ref) {
    showModalBottomSheet(
      context: context,
      isScrollControlled: true,
      backgroundColor: Colors.transparent,
      builder: (_) => _ApplyLeaveSheet(ref: ref),
    );
  }
}

class _BalanceTile extends StatelessWidget {
  final LeaveBalance balance;
  final Color color;
  final IconData icon;

  const _BalanceTile({
    required this.balance,
    required this.color,
    required this.icon,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: AppColors.surface,
        borderRadius: BorderRadius.circular(AppRadius.lg),
        border: Border.all(color: color.withOpacity(0.3)),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Icon(icon, color: color, size: 24),
          const SizedBox(height: 10),
          Text(
            '${balance.remainingDays}',
            style: Theme.of(context).textTheme.headlineMedium?.copyWith(
                  color: color,
                  fontWeight: FontWeight.w800,
                ),
          ),
          Text('${balance.type} Leave', style: Theme.of(context).textTheme.labelSmall),
          const SizedBox(height: 8),
          ClipRRect(
            borderRadius: BorderRadius.circular(3),
            child: LinearProgressIndicator(
              value: balance.totalDays > 0 ? balance.usedDays / balance.totalDays : 0,
              backgroundColor: color.withOpacity(0.1),
              color: color,
              minHeight: 4,
            ),
          ),
          const SizedBox(height: 4),
          Text(
            '${balance.usedDays} used of ${balance.totalDays}',
            style: Theme.of(context)
                .textTheme
                .labelSmall
                ?.copyWith(color: AppColors.onBackground),
          ),
        ],
      ),
    );
  }
}

class _TotalBalanceBanner extends StatelessWidget {
  final Map<String, LeaveBalance> balances;

  const _TotalBalanceBanner({required this.balances});

  @override
  Widget build(BuildContext context) {
    final totalRemaining = balances.values.fold<int>(0, (sum, b) => sum + b.remainingDays);
    final totalDays = balances.values.fold<int>(0, (sum, b) => sum + b.totalDays);
    
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 12),
      decoration: BoxDecoration(
        color: AppColors.primary.withOpacity(0.06),
        borderRadius: BorderRadius.circular(AppRadius.md),
        border: Border.all(color: AppColors.primary.withOpacity(0.2)),
      ),
      child: Row(
        children: [
          const Icon(Icons.info_outline_rounded,
              color: AppColors.primary, size: 18),
          const SizedBox(width: 10),
          Expanded(
            child: Text(
              '$totalRemaining days remaining of $totalDays total · Leaves reset on Jan 1',
              style: Theme.of(context).textTheme.bodySmall?.copyWith(
                    color: AppColors.primary,
                    fontWeight: FontWeight.w500,
                  ),
            ),
          ),
        ],
      ),
    );
  }
}

class _LeaveHistoryTile extends StatelessWidget {
  final LeaveRequest request;

  const _LeaveHistoryTile({required this.request});

  @override
  Widget build(BuildContext context) {
    final fmt = DateFormat('dd MMM yyyy');

    final (color, label) = switch (request.status.toLowerCase()) {
      'approved' => (AppColors.success, 'Approved'),
      'rejected' => (AppColors.error, 'Rejected'),
      _ => (AppColors.warning, 'Pending'),
    };

    return Container(
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: AppColors.surface,
        borderRadius: BorderRadius.circular(AppRadius.lg),
        border: Border.all(color: AppColors.outline, width: 0.5),
      ),
      child: Row(
        children: [
          Container(
            padding: const EdgeInsets.all(10),
            decoration: BoxDecoration(
              color: color.withOpacity(0.1),
              borderRadius: BorderRadius.circular(AppRadius.md),
            ),
            child: Icon(Icons.event_available_rounded, color: color, size: 20),
          ),
          const SizedBox(width: 12),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text('${request.type} Leave', style: Theme.of(context).textTheme.titleSmall),
                const SizedBox(height: 2),
                Text(
                  '${fmt.format(request.from)} → ${fmt.format(request.to)} · ${request.days} day${request.days > 1 ? 's' : ''}',
                  style: Theme.of(context).textTheme.bodySmall,
                ),
                const SizedBox(height: 2),
                Text(request.reason, style: Theme.of(context).textTheme.bodySmall),
              ],
            ),
          ),
          StatusBadge(status: request.status.toLowerCase() == 'approved'
              ? 'completed'
              : request.status.toLowerCase() == 'rejected'
                  ? 'missed'
                  : 'planned'),
        ],
      ),
    );
  }
}

class _ApplyLeaveSheet extends StatefulWidget {
  final WidgetRef ref;
  const _ApplyLeaveSheet({required this.ref});

  @override
  State<_ApplyLeaveSheet> createState() => _ApplyLeaveSheetState();
}

class _ApplyLeaveSheetState extends State<_ApplyLeaveSheet> {
  String _leaveType = 'casual';
  DateTime? _from;
  DateTime? _to;
  final _reasonCtrl = TextEditingController();
  bool _loading = false;

  @override
  void dispose() {
    _reasonCtrl.dispose();
    super.dispose();
  }

  Future<void> _pickDate(bool isFrom) async {
    final d = await showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      firstDate: DateTime.now(),
      lastDate: DateTime.now().add(const Duration(days: 90)),
    );
    if (d != null) {
      setState(() {
        if (isFrom) _from = d;
        else _to = d;
      });
    }
  }

  Future<void> _submit() async {
    if (_from == null || _to == null) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Please select both dates')),
      );
      return;
    }
    if (_reasonCtrl.text.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Please enter a reason')),
      );
      return;
    }

    setState(() => _loading = true);
    try {
      await widget.ref.read(submitLeaveProvider(
        (
          type: _leaveType,
          from: _from!,
          to: _to!,
          reason: _reasonCtrl.text,
        ),
      ).future);
      
      if (mounted) {
        Navigator.pop(context);
        ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(content: Text('Leave request submitted')),
        );
      }
    } catch (e) {
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Error: $e'), backgroundColor: AppColors.error),
        );
      }
    } finally {
      if (mounted) setState(() => _loading = false);
    }
  }

  @override
  Widget build(BuildContext context) {
    final fmt = DateFormat('dd MMM yyyy');
    return Container(
      decoration: const BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.vertical(top: Radius.circular(24)),
      ),
      padding: EdgeInsets.fromLTRB(
          20, 20, 20, MediaQuery.viewInsetsOf(context).bottom + 20),
      child: Column(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Center(
            child: Container(
              width: 36, height: 4,
              decoration: BoxDecoration(
                color: AppColors.outline,
                borderRadius: BorderRadius.circular(2),
              ),
            ),
          ),
          const SizedBox(height: 20),
          Text('Apply for Leave',
              style: Theme.of(context).textTheme.titleLarge),
          const SizedBox(height: 20),
          // Leave type
          Row(
            children: [
              Expanded(
                child: _TypeButton(
                  label: 'Casual Leave',
                  isSelected: _leaveType == 'casual',
                  onTap: () => setState(() => _leaveType = 'casual'),
                ),
              ),
              const SizedBox(width: 10),
              Expanded(
                child: _TypeButton(
                  label: 'Sick Leave',
                  isSelected: _leaveType == 'sick',
                  onTap: () => setState(() => _leaveType = 'sick'),
                ),
              ),
            ],
          ),
          const SizedBox(height: 16),
          // Date pickers
          Row(
            children: [
              Expanded(
                child: OutlinedButton.icon(
                  icon: const Icon(Icons.calendar_month_outlined, size: 18),
                  label: Text(_from != null ? fmt.format(_from!) : 'From Date'),
                  onPressed: _loading ? null : () => _pickDate(true),
                ),
              ),
              const SizedBox(width: 10),
              Expanded(
                child: OutlinedButton.icon(
                  icon: const Icon(Icons.calendar_month_outlined, size: 18),
                  label: Text(_to != null ? fmt.format(_to!) : 'To Date'),
                  onPressed: _loading ? null : () => _pickDate(false),
                ),
              ),
            ],
          ),
          const SizedBox(height: 14),
          TextFormField(
            controller: _reasonCtrl,
            maxLines: 2,
            enabled: !_loading,
            textCapitalization: TextCapitalization.sentences,
            decoration: const InputDecoration(
              labelText: 'Reason',
              prefixIcon: Icon(Icons.edit_note_rounded),
            ),
          ),
          const SizedBox(height: 20),
          FilledButton(
            onPressed: _loading ? null : _submit,
            child: _loading
                ? const SizedBox(
                    height: 20,
                    width: 20,
                    child: CircularProgressIndicator(strokeWidth: 2),
                  )
                : const Text('Submit Leave Request'),
          ),
        ],
      ),
    );
  }
}

class _TypeButton extends StatelessWidget {
  final String label;
  final bool isSelected;
  final VoidCallback onTap;
  const _TypeButton(
      {required this.label, required this.isSelected, required this.onTap});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onTap,
      child: Container(
        padding: const EdgeInsets.symmetric(vertical: 12),
        decoration: BoxDecoration(
          color: isSelected ? AppColors.primary : AppColors.surface,
          borderRadius: BorderRadius.circular(AppRadius.md),
          border: Border.all(
            color: isSelected ? AppColors.primary : AppColors.outline,
          ),
        ),
        child: Center(
          child: Text(
            label,
            style: TextStyle(
              color: isSelected ? Colors.white : AppColors.onSurface,
              fontWeight: FontWeight.w600,
              fontSize: 13,
            ),
          ),
        ),
      ),
    );
  }
}
