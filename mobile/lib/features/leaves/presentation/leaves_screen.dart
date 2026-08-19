import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:intl/intl.dart';

import '../../../core/theme/app_theme.dart';
import '../../../core/network/dio_client.dart';

// ── Models ────────────────────────────────────────────────────────────────────
class LeaveBalance {
  final int sickTotal, casualTotal, sickTaken, casualTaken;
  final int sickRemaining, casualRemaining;
  const LeaveBalance({
    required this.sickTotal, required this.casualTotal,
    required this.sickTaken, required this.casualTaken,
    required this.sickRemaining, required this.casualRemaining,
  });
  factory LeaveBalance.fromJson(Map<String, dynamic> j) => LeaveBalance(
    sickTotal: j['sickTotal'] ?? 10,
    casualTotal: j['casualTotal'] ?? 18,
    sickTaken: j['sickTaken'] ?? 0,
    casualTaken: j['casualTaken'] ?? 0,
    sickRemaining: j['sickRemaining'] ?? 10,
    casualRemaining: j['casualRemaining'] ?? 18,
  );
}

class LeaveRecord {
  final int id;
  final String type, reason, status;
  final String? adminNotes;
  final DateTime fromDate, toDate, appliedAt;
  const LeaveRecord({
    required this.id, required this.type, required this.reason,
    required this.status, this.adminNotes,
    required this.fromDate, required this.toDate, required this.appliedAt,
  });
  factory LeaveRecord.fromJson(Map<String, dynamic> j) => LeaveRecord(
    id: j['id'] ?? 0,
    type: j['type'] ?? 'casual',
    reason: j['reason'] ?? '',
    status: j['status'] ?? 'pending',
    adminNotes: j['adminNotes'] as String?,
    fromDate: DateTime.parse(j['fromDate'] as String),
    toDate: DateTime.parse(j['toDate'] as String),
    appliedAt: DateTime.parse(j['appliedAt'] as String),
  );
  int get days =>
      toDate.difference(fromDate).inDays + 1;
}

class LeavesData {
  final LeaveBalance balance;
  final List<LeaveRecord> history;
  const LeavesData({required this.balance, required this.history});
}

// ── Provider ──────────────────────────────────────────────────────────────────
final leavesProvider = FutureProvider.autoDispose<LeavesData>((ref) async {
  final dio = ref.watch(dioClientProvider);
  final res = await dio.get('/leaves');
  final d = res.data['data'] as Map<String, dynamic>;
  return LeavesData(
    balance: LeaveBalance.fromJson(d['balance'] as Map<String, dynamic>),
    history: (d['history'] as List)
        .cast<Map<String, dynamic>>()
        .map(LeaveRecord.fromJson)
        .toList(),
  );
});

// ── Screen ────────────────────────────────────────────────────────────────────
class LeavesScreen extends ConsumerWidget {
  const LeavesScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final async = ref.watch(leavesProvider);
    return Scaffold(
      backgroundColor: AppColors.background,
      appBar: AppBar(
        title: const Text('Leave Management'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios_new_rounded),
          onPressed: () => context.go('/dashboard'),
        ),
        actions: [
          IconButton(
            icon: const Icon(Icons.refresh_rounded),
            onPressed: () => ref.invalidate(leavesProvider),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton.extended(
        onPressed: () => _showApplySheet(context, ref),
        icon: const Icon(Icons.add_rounded),
        label: const Text('Apply Leave'),
        backgroundColor: AppColors.primary,
        foregroundColor: Colors.white,
      ),
      body: async.when(
        loading: () => const Center(child: CircularProgressIndicator()),
        error: (e, _) => Center(
          child: Column(mainAxisSize: MainAxisSize.min, children: [
            const Icon(Icons.error_outline_rounded, size: 48, color: Colors.grey),
            const SizedBox(height: 12),
            Text(e.toString(), textAlign: TextAlign.center,
                style: const TextStyle(color: Colors.grey)),
            const SizedBox(height: 16),
            FilledButton.icon(
              icon: const Icon(Icons.refresh_rounded),
              label: const Text('Retry'),
              onPressed: () => ref.invalidate(leavesProvider),
            ),
          ]),
        ),
        data: (data) => ListView(
          padding: const EdgeInsets.fromLTRB(16, 16, 16, 100),
          children: [
            // Balance cards
            _BalanceSection(balance: data.balance)
                .animate().fadeIn(duration: 300.ms),
            const SizedBox(height: 24),

            // History header
            Row(children: [
              Text('Leave History', style: Theme.of(context).textTheme.labelLarge
                  ?.copyWith(color: AppColors.primary)),
              const Spacer(),
              if (data.history.isNotEmpty)
                Text('${data.history.length} requests',
                    style: Theme.of(context).textTheme.bodySmall),
            ]),
            const SizedBox(height: 12),

            if (data.history.isEmpty)
              _EmptyHistory()
            else
              ...data.history.asMap().entries.map((e) =>
                _LeaveCard(record: e.value)
                    .animate(delay: (e.key * 50).ms).slideY(begin: 0.1).fadeIn()),
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
      builder: (_) => _ApplyLeaveSheet(
        onSubmitted: () => ref.invalidate(leavesProvider),
      ),
    );
  }
}

// ── Balance section ───────────────────────────────────────────────────────────
class _BalanceSection extends StatelessWidget {
  final LeaveBalance balance;
  const _BalanceSection({required this.balance});

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text('Leave Balance', style: Theme.of(context).textTheme.labelLarge
            ?.copyWith(color: AppColors.primary)),
        const SizedBox(height: 12),
        Row(children: [
          Expanded(child: _BalanceCard(
            label: 'Sick Leave',
            icon: Icons.medical_services_rounded,
            color: AppColors.error,
            used: balance.sickTaken,
            total: balance.sickTotal,
            remaining: balance.sickRemaining,
          )),
          const SizedBox(width: 12),
          Expanded(child: _BalanceCard(
            label: 'Casual Leave',
            icon: Icons.beach_access_rounded,
            color: AppColors.info,
            used: balance.casualTaken,
            total: balance.casualTotal,
            remaining: balance.casualRemaining,
          )),
        ]),
      ],
    );
  }
}

class _BalanceCard extends StatelessWidget {
  final String label;
  final IconData icon;
  final Color color;
  final int used, total, remaining;
  const _BalanceCard({
    required this.label, required this.icon, required this.color,
    required this.used, required this.total, required this.remaining,
  });

  @override
  Widget build(BuildContext context) {
    final pct = total > 0 ? (used / total).clamp(0.0, 1.0) : 0.0;
    return Container(
      padding: const EdgeInsets.all(14),
      decoration: BoxDecoration(
        color: AppColors.surface,
        borderRadius: BorderRadius.circular(AppRadius.lg),
        border: Border.all(color: AppColors.outline),
        boxShadow: [BoxShadow(
          color: Colors.black.withOpacity(0.04),
          blurRadius: 8, offset: const Offset(0, 2),
        )],
      ),
      child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
        Row(children: [
          Icon(icon, color: color, size: 18),
          const SizedBox(width: 6),
          Expanded(child: Text(label, style: Theme.of(context).textTheme.labelMedium,
              maxLines: 1, overflow: TextOverflow.ellipsis)),
        ]),
        const SizedBox(height: 10),
        Text('$remaining', style: TextStyle(
          color: color, fontSize: 28, fontWeight: FontWeight.w800,
          height: 1,
        )),
        Text('days left', style: Theme.of(context).textTheme.bodySmall),
        const SizedBox(height: 8),
        ClipRRect(
          borderRadius: BorderRadius.circular(4),
          child: LinearProgressIndicator(
            value: pct,
            backgroundColor: color.withOpacity(0.1),
            valueColor: AlwaysStoppedAnimation<Color>(color),
            minHeight: 5,
          ),
        ),
        const SizedBox(height: 4),
        Text('$used / $total used', style: Theme.of(context).textTheme.bodySmall),
      ]),
    );
  }
}

// ── Leave card ────────────────────────────────────────────────────────────────
class _LeaveCard extends StatelessWidget {
  final LeaveRecord record;
  const _LeaveCard({required this.record});

  @override
  Widget build(BuildContext context) {
    final (color, icon, label) = switch (record.status.toLowerCase()) {
      'approved' => (AppColors.success, Icons.check_circle_rounded, 'Approved'),
      'rejected' => (AppColors.error,   Icons.cancel_rounded,       'Rejected'),
      _          => (AppColors.warning,  Icons.hourglass_empty_rounded, 'Pending'),
    };
    final typeColor = record.type.toLowerCase() == 'sick' ? AppColors.error : AppColors.info;

    return Container(
      margin: const EdgeInsets.only(bottom: 10),
      padding: const EdgeInsets.all(14),
      decoration: BoxDecoration(
        color: AppColors.surface,
        borderRadius: BorderRadius.circular(AppRadius.lg),
        border: Border.all(color: AppColors.outline),
      ),
      child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
        Row(children: [
          Container(
            padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 3),
            decoration: BoxDecoration(
              color: typeColor.withOpacity(0.1),
              borderRadius: BorderRadius.circular(20),
            ),
            child: Text(record.type.replaceAll('_', ' '),
                style: TextStyle(fontSize: 11, color: typeColor, fontWeight: FontWeight.w700)),
          ),
          const SizedBox(width: 8),
          Text('${record.days} day${record.days != 1 ? 's' : ''}',
              style: Theme.of(context).textTheme.bodySmall),
          const Spacer(),
          Row(children: [
            Icon(icon, size: 14, color: color),
            const SizedBox(width: 4),
            Text(label, style: TextStyle(fontSize: 12, color: color, fontWeight: FontWeight.w600)),
          ]),
        ]),
        const SizedBox(height: 8),
        Row(children: [
          const Icon(Icons.date_range_rounded, size: 14, color: AppColors.textMuted),
          const SizedBox(width: 6),
          Text(
            '${DateFormat('d MMM').format(record.fromDate)} – ${DateFormat('d MMM yyyy').format(record.toDate)}',
            style: Theme.of(context).textTheme.bodySmall?.copyWith(fontWeight: FontWeight.w600),
          ),
        ]),
        if (record.reason.isNotEmpty) ...[
          const SizedBox(height: 6),
          Text(record.reason,
              style: Theme.of(context).textTheme.bodySmall,
              maxLines: 2, overflow: TextOverflow.ellipsis),
        ],
        if (record.adminNotes != null && record.adminNotes!.isNotEmpty) ...[
          const SizedBox(height: 6),
          Container(
            width: double.infinity,
            padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 6),
            decoration: BoxDecoration(
              color: color.withOpacity(0.06),
              borderRadius: BorderRadius.circular(8),
              border: Border.all(color: color.withOpacity(0.2)),
            ),
            child: Text('Admin: ${record.adminNotes}',
                style: TextStyle(fontSize: 11, color: color, fontStyle: FontStyle.italic)),
          ),
        ],
      ]),
    );
  }
}

class _EmptyHistory extends StatelessWidget {
  @override
  Widget build(BuildContext context) => Center(
    child: Padding(
      padding: const EdgeInsets.all(40),
      child: Column(mainAxisSize: MainAxisSize.min, children: [
        Icon(Icons.event_available_rounded, size: 56, color: AppColors.outline),
        const SizedBox(height: 12),
        Text('No leave requests yet',
            style: Theme.of(context).textTheme.titleSmall),
        const SizedBox(height: 6),
        Text('Tap "Apply Leave" to submit a request.',
            textAlign: TextAlign.center,
            style: Theme.of(context).textTheme.bodySmall),
      ]),
    ),
  );
}

// ── Apply leave bottom sheet ──────────────────────────────────────────────────
class _ApplyLeaveSheet extends ConsumerStatefulWidget {
  final VoidCallback onSubmitted;
  const _ApplyLeaveSheet({required this.onSubmitted});

  @override
  ConsumerState<_ApplyLeaveSheet> createState() => _ApplyLeaveSheetState();
}

class _ApplyLeaveSheetState extends ConsumerState<_ApplyLeaveSheet> {
  String _type = 'sick';
  DateTime? _from;
  DateTime? _to;
  final _reasonCtrl = TextEditingController();
  bool _submitting = false;
  String _error = '';

  @override
  void dispose() { _reasonCtrl.dispose(); super.dispose(); }

  int get _days => (_from != null && _to != null)
      ? _to!.difference(_from!).inDays + 1
      : 0;

  Future<void> _pickDate(bool isFrom) async {
    final now = DateTime.now();
    final initial = isFrom
        ? (_from ?? now)
        : (_to ?? (_from ?? now).add(const Duration(days: 1)));
    final picked = await showDatePicker(
      context: context,
      initialDate: initial,
      firstDate: now.subtract(const Duration(days: 30)),
      lastDate: now.add(const Duration(days: 90)),
      builder: (ctx, child) => Theme(
        data: Theme.of(ctx).copyWith(
          colorScheme: Theme.of(ctx).colorScheme.copyWith(primary: AppColors.primary),
        ),
        child: child!,
      ),
    );
    if (picked != null) {
      setState(() {
        if (isFrom) {
          _from = picked;
          if (_to != null && _to!.isBefore(picked)) _to = null;
        } else {
          _to = picked;
        }
      });
    }
  }

  Future<void> _submit() async {
    if (_from == null || _to == null) {
      setState(() => _error = 'Please select date range');
      return;
    }
    if (_reasonCtrl.text.trim().isEmpty) {
      setState(() => _error = 'Please provide a reason');
      return;
    }
    setState(() { _submitting = true; _error = ''; });
    try {
      final dio = ref.read(dioClientProvider);
      final res = await dio.post('/leaves', data: {
        'leaveType': _type,
        'fromDate': _from!.toIso8601String().substring(0, 10),
        'toDate': _to!.toIso8601String().substring(0, 10),
        'reason': _reasonCtrl.text.trim(),
      });
      if (res.data['success'] == true) {
        if (mounted) Navigator.pop(context);
        widget.onSubmitted();
      } else {
        setState(() => _error = res.data['error'] ?? 'Failed');
      }
    } catch (e) {
      setState(() => _error = e.toString());
    } finally {
      if (mounted) setState(() => _submitting = false);
    }
  }

  @override
  Widget build(BuildContext context) {
    final bottom = MediaQuery.of(context).viewInsets.bottom;
    return Container(
      margin: EdgeInsets.only(bottom: bottom),
      decoration: const BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.vertical(top: Radius.circular(24)),
      ),
      child: SafeArea(
        child: Padding(
          padding: const EdgeInsets.fromLTRB(20, 16, 20, 20),
          child: Column(
            mainAxisSize: MainAxisSize.min,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              // Handle
              Center(child: Container(
                width: 40, height: 4,
                decoration: BoxDecoration(color: AppColors.outline, borderRadius: BorderRadius.circular(2)),
              )),
              const SizedBox(height: 16),
              Text('Apply for Leave',
                  style: Theme.of(context).textTheme.titleLarge?.copyWith(fontWeight: FontWeight.bold)),
              const SizedBox(height: 20),

              // Type selector
              Text('Leave Type', style: Theme.of(context).textTheme.labelMedium?.copyWith(color: AppColors.textMuted)),
              const SizedBox(height: 8),
              Row(children: [
                _TypeChip(label: 'Sick', value: 'sick', selected: _type, onTap: (v) => setState(() => _type = v),
                    icon: Icons.medical_services_rounded, color: AppColors.error),
                const SizedBox(width: 10),
                _TypeChip(label: 'Casual', value: 'casual', selected: _type, onTap: (v) => setState(() => _type = v),
                    icon: Icons.beach_access_rounded, color: AppColors.info),
              ]),
              const SizedBox(height: 16),

              // Date range
              Text('Date Range', style: Theme.of(context).textTheme.labelMedium?.copyWith(color: AppColors.textMuted)),
              const SizedBox(height: 8),
              Row(children: [
                Expanded(child: _DatePicker(
                  label: 'From', date: _from, onTap: () => _pickDate(true),
                )),
                const Padding(padding: EdgeInsets.symmetric(horizontal: 10),
                    child: Text('→', style: TextStyle(color: AppColors.textMuted))),
                Expanded(child: _DatePicker(
                  label: 'To', date: _to, onTap: () => _pickDate(false),
                )),
              ]),
              if (_days > 0)
                Padding(
                  padding: const EdgeInsets.only(top: 6),
                  child: Text('$_days day${_days != 1 ? 's' : ''}',
                      style: TextStyle(color: AppColors.primary, fontSize: 12, fontWeight: FontWeight.w600)),
                ),
              const SizedBox(height: 16),

              // Reason
              Text('Reason', style: Theme.of(context).textTheme.labelMedium?.copyWith(color: AppColors.textMuted)),
              const SizedBox(height: 8),
              TextField(
                controller: _reasonCtrl,
                maxLines: 3,
                textCapitalization: TextCapitalization.sentences,
                decoration: InputDecoration(
                  hintText: 'Explain why you need leave...',
                  filled: true,
                  fillColor: AppColors.background,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(12),
                    borderSide: const BorderSide(color: AppColors.outline),
                  ),
                  enabledBorder: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(12),
                    borderSide: const BorderSide(color: AppColors.outline),
                  ),
                  focusedBorder: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(12),
                    borderSide: BorderSide(color: AppColors.primary, width: 2),
                  ),
                ),
              ),
              if (_error.isNotEmpty) ...[
                const SizedBox(height: 8),
                Text(_error, style: const TextStyle(color: AppColors.error, fontSize: 12)),
              ],
              const SizedBox(height: 20),

              SizedBox(
                width: double.infinity,
                child: FilledButton.icon(
                  icon: _submitting
                      ? const SizedBox(width: 18, height: 18,
                          child: CircularProgressIndicator(strokeWidth: 2, color: Colors.white))
                      : const Icon(Icons.send_rounded),
                  label: Text(_submitting ? 'Submitting…' : 'Submit Leave Request'),
                  onPressed: _submitting ? null : _submit,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class _TypeChip extends StatelessWidget {
  final String label, value, selected;
  final IconData icon;
  final Color color;
  final ValueChanged<String> onTap;
  const _TypeChip({required this.label, required this.value, required this.selected,
    required this.onTap, required this.icon, required this.color});

  @override
  Widget build(BuildContext context) {
    final isSelected = value == selected;
    return Expanded(
      child: GestureDetector(
        onTap: () => onTap(value),
        child: AnimatedContainer(
          duration: const Duration(milliseconds: 150),
          padding: const EdgeInsets.symmetric(vertical: 10),
          decoration: BoxDecoration(
            color: isSelected ? color.withOpacity(0.1) : AppColors.surface,
            borderRadius: BorderRadius.circular(12),
            border: Border.all(color: isSelected ? color : AppColors.outline, width: isSelected ? 2 : 1),
          ),
          child: Row(mainAxisAlignment: MainAxisAlignment.center, children: [
            Icon(icon, size: 16, color: isSelected ? color : AppColors.textMuted),
            const SizedBox(width: 6),
            Text(label, style: TextStyle(
              fontWeight: FontWeight.w600, fontSize: 13,
              color: isSelected ? color : AppColors.onSurface,
            )),
          ]),
        ),
      ),
    );
  }
}

class _DatePicker extends StatelessWidget {
  final String label;
  final DateTime? date;
  final VoidCallback onTap;
  const _DatePicker({required this.label, this.date, required this.onTap});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onTap,
      child: Container(
        padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 12),
        decoration: BoxDecoration(
          color: date != null ? AppColors.primary.withOpacity(0.05) : AppColors.background,
          borderRadius: BorderRadius.circular(12),
          border: Border.all(
            color: date != null ? AppColors.primary.withOpacity(0.4) : AppColors.outline,
          ),
        ),
        child: Row(children: [
          Icon(Icons.calendar_today_rounded, size: 15,
              color: date != null ? AppColors.primary : AppColors.textMuted),
          const SizedBox(width: 6),
          Expanded(child: Text(
            date != null ? DateFormat('d MMM yy').format(date!) : label,
            style: TextStyle(
              fontSize: 13,
              color: date != null ? AppColors.primary : AppColors.textMuted,
              fontWeight: date != null ? FontWeight.w600 : FontWeight.w400,
            ),
            overflow: TextOverflow.ellipsis,
          )),
        ]),
      ),
    );
  }
}
