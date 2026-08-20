import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:intl/intl.dart';
import 'package:url_launcher/url_launcher.dart';

import '../../../core/theme/app_theme.dart';
import '../../../core/widgets/mock_location_guard.dart';
import '../data/visit_repository.dart';

// Provider to load single visit details
final visitDetailProvider = FutureProvider.family<Map<String, dynamic>?, int>(
  (ref, visitId) async {
    final repo = ref.read(visitRepositoryProvider);
    return repo.getVisitDetail(visitId);
  },
);

class CompleteVisitScreen extends ConsumerStatefulWidget {
  final int visitId;
  const CompleteVisitScreen({super.key, required this.visitId});

  @override
  ConsumerState<CompleteVisitScreen> createState() => _CompleteVisitScreenState();
}

class _CompleteVisitScreenState extends ConsumerState<CompleteVisitScreen> {
  final _formKey = GlobalKey<FormState>();
  final _contactCtrl = TextEditingController();
  final _phoneCtrl = TextEditingController();
  final _notesCtrl = TextEditingController();
  String _visitType = 'regular';
  bool _isSubmitting = false;
  bool _prefilled = false;
  DateTime? _followUpDate;

  @override
  void dispose() {
    _contactCtrl.dispose();
    _phoneCtrl.dispose();
    _notesCtrl.dispose();
    super.dispose();
  }

  void _prefillFromVisit(Map<String, dynamic>? detail) {
    if (_prefilled || detail == null) return;
    _prefilled = true;
    final customer = detail['customer'] as Map<String, dynamic>? ?? {};
    _contactCtrl.text = detail['contact'] as String? ??
        customer['ownerName'] as String? ?? '';
    _phoneCtrl.text = detail['phone'] as String? ??
        customer['ownerPhone'] as String? ?? '';
    _notesCtrl.text = detail['notes'] as String? ?? '';
    if (detail['visitType'] != null) {
      setState(() => _visitType = detail['visitType'] as String);
    }
  }

  Future<void> _submit() async {
    if (!_formKey.currentState!.validate()) return;
    final gpsClean = await MockLocationGuard.check(context, ref);
    if (!gpsClean) return;
    setState(() => _isSubmitting = true);
    try {
      await ref.read(visitRepositoryProvider).completeVisit(widget.visitId, {
        'contactPerson': _contactCtrl.text.trim(),
        'contactPhone': _phoneCtrl.text.trim(),
        'notes': _notesCtrl.text.trim(),
        'visitType': _visitType,
        if (_followUpDate != null)
          'followUpDate': _followUpDate!.toIso8601String().substring(0, 10),
      });
      await ref.read(visitListProvider.notifier).refresh();
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: const Row(children: [
              Icon(Icons.check_circle_rounded, color: Colors.white, size: 18),
              SizedBox(width: 8),
              Text('Visit completed!'),
            ]),
            backgroundColor: AppColors.success,
            behavior: SnackBarBehavior.floating,
            shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
          ),
        );
        context.go('/visits');
      }
    } catch (e) {
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: Text('Failed: $e'),
            backgroundColor: AppColors.error,
            behavior: SnackBarBehavior.floating,
          ),
        );
      }
    } finally {
      if (mounted) setState(() => _isSubmitting = false);
    }
  }

  @override
  Widget build(BuildContext context) {
    final detailAsync = ref.watch(visitDetailProvider(widget.visitId));

    return Scaffold(
      backgroundColor: AppColors.background,
      appBar: AppBar(
        title: const Text('Complete Visit'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios_new_rounded),
          onPressed: () => context.go('/visits'),
        ),
      ),
      body: detailAsync.when(
        loading: () => const Center(child: CircularProgressIndicator()),
        error: (_, __) => _buildForm(null),
        data: (detail) {
          _prefillFromVisit(detail);
          return _buildForm(detail);
        },
      ),
    );
  }

  Widget _buildForm(Map<String, dynamic>? detail) {
    final customer = detail?['customer'] as Map<String, dynamic>? ?? {};
    final customerName = detail?['customerName'] as String? ?? customer['name'] as String? ?? 'Customer';
    final address = detail?['address'] as String? ?? customer['address'] as String? ?? '';
    final lat = detail?['latitude'];
    final lng = detail?['longitude'];
    final hasGps = lat != null && lng != null && lat != 0 && lng != 0;

    return Form(
      key: _formKey,
      child: ListView(
        padding: const EdgeInsets.all(AppSpacing.md),
        children: [

          // ── Customer Info Card (tappable to open full detail) ──
          Material(
            color: Colors.transparent,
            child: InkWell(
              onTap: customer['id'] != null
                  ? () => context.go('/customers/${customer['id']}')
                  : null,
              borderRadius: BorderRadius.circular(AppRadius.lg),
              splashColor: Colors.white24,
              child: Container(
              padding: const EdgeInsets.all(16),
              decoration: BoxDecoration(
                gradient: LinearGradient(
                  begin: Alignment.topLeft,
                  end: Alignment.bottomRight,
                  colors: [AppColors.primary, AppColors.primary.withOpacity(0.8)],
                ),
                borderRadius: BorderRadius.circular(AppRadius.lg),
              ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  children: [
                    Container(
                      padding: const EdgeInsets.all(8),
                      decoration: BoxDecoration(
                        color: Colors.white.withOpacity(0.2),
                        borderRadius: BorderRadius.circular(8),
                      ),
                      child: const Icon(Icons.store_rounded, color: Colors.white, size: 20),
                    ),
                    const SizedBox(width: 12),
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            customerName,
                            style: const TextStyle(
                              color: Colors.white,
                              fontWeight: FontWeight.bold,
                              fontSize: 16,
                            ),
                          ),
                          if (address.isNotEmpty)
                            Text(
                              address,
                              style: TextStyle(
                                color: Colors.white.withOpacity(0.8),
                                fontSize: 12,
                              ),
                              maxLines: 2,
                              overflow: TextOverflow.ellipsis,
                            ),
                        ],
                      ),
                    ),
                  ],
                ),
                if (customer['ownerPhone'] != null && (customer['ownerPhone'] as String).isNotEmpty) ...[
                  const SizedBox(height: 12),
                  Row(
                    children: [
                      Icon(Icons.phone_rounded, color: Colors.white.withOpacity(0.7), size: 14),
                      const SizedBox(width: 6),
                      Text(
                        customer['ownerPhone'] as String,
                        style: TextStyle(color: Colors.white.withOpacity(0.9), fontSize: 13),
                      ),
                      const Spacer(),
                      GestureDetector(
                        onTap: () => launchUrl(Uri.parse('tel:${customer['ownerPhone']}')),
                        child: Container(
                          padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 4),
                          decoration: BoxDecoration(
                            color: Colors.white.withOpacity(0.2),
                            borderRadius: BorderRadius.circular(20),
                          ),
                          child: const Text('Call', style: TextStyle(color: Colors.white, fontSize: 12, fontWeight: FontWeight.w600)),
                        ),
                      ),
                    ],
                  ),
                ],
                if (hasGps) ...[
                  const SizedBox(height: 8),
                  GestureDetector(
                    onTap: () => launchUrl(Uri.parse('https://maps.google.com/?q=$lat,$lng')),
                    child: Row(
                      children: [
                        Icon(Icons.directions_rounded, color: Colors.white.withOpacity(0.7), size: 14),
                        const SizedBox(width: 6),
                        Text(
                          'Navigate to customer',
                          style: TextStyle(color: Colors.white.withOpacity(0.9), fontSize: 12, decoration: TextDecoration.underline, decorationColor: Colors.white.withOpacity(0.7)),
                        ),
                      ],
                    ),
                  ),
                ],
                // "View full profile" hint
                const SizedBox(height: 10),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Icon(Icons.touch_app_rounded, size: 12, color: Colors.white.withOpacity(0.5)),
                    const SizedBox(width: 4),
                    Text('Tap to view / edit customer info',
                        style: TextStyle(color: Colors.white.withOpacity(0.5), fontSize: 11)),
                  ],
                ),
              ],
            ),
          ),
          ),
          ).animate().fadeIn(duration: 300.ms),

          const SizedBox(height: 20),

          // ── Visit Type ────────────────────────────────────────
          Text('Visit Type', style: Theme.of(context).textTheme.labelLarge?.copyWith(color: AppColors.primary)),
          const SizedBox(height: 10),
          _VisitTypeSelector(
            selected: _visitType,
            onChanged: (v) => setState(() => _visitType = v),
          ).animate(delay: 80.ms).fadeIn(),

          const SizedBox(height: 20),

          // ── Contact Details ───────────────────────────────────
          Text('Contact Details', style: Theme.of(context).textTheme.labelLarge?.copyWith(color: AppColors.primary)),
          const SizedBox(height: 12),

          TextFormField(
            controller: _contactCtrl,
            textCapitalization: TextCapitalization.words,
            decoration: const InputDecoration(
              labelText: 'Contact Person *',
              prefixIcon: Icon(Icons.person_outline_rounded),
            ),
            validator: (v) => v == null || v.trim().isEmpty ? 'Required' : null,
          ).animate(delay: 100.ms).slideY(begin: 0.2).fadeIn(),

          const SizedBox(height: 14),

          TextFormField(
            controller: _phoneCtrl,
            keyboardType: TextInputType.phone,
            decoration: const InputDecoration(
              labelText: 'Phone Number *',
              prefixIcon: Icon(Icons.phone_outlined),
            ),
            validator: (v) => v == null || v.trim().isEmpty ? 'Required' : null,
          ).animate(delay: 140.ms).slideY(begin: 0.2).fadeIn(),

          const SizedBox(height: 20),

          // ── Notes ─────────────────────────────────────────────
          Text('Visit Notes', style: Theme.of(context).textTheme.labelLarge?.copyWith(color: AppColors.primary)),
          const SizedBox(height: 12),

          TextFormField(
            controller: _notesCtrl,
            maxLines: 4,
            textCapitalization: TextCapitalization.sentences,
            decoration: const InputDecoration(
              labelText: 'Discussion Notes *',
              hintText: 'What was discussed? Any orders, feedback, or follow-up needed?',
              prefixIcon: Padding(
                padding: EdgeInsets.only(bottom: 60),
                child: Icon(Icons.notes_rounded),
              ),
              alignLabelWithHint: true,
            ),
            validator: (v) => v == null || v.trim().length < 5 ? 'Add at least 5 characters' : null,
          ).animate(delay: 200.ms).slideY(begin: 0.2).fadeIn(),

          const SizedBox(height: 20),

          // ── Follow-up Date ────────────────────────────────────
          Text('Follow-up Date (Optional)',
              style: Theme.of(context).textTheme.labelLarge?.copyWith(color: AppColors.primary)),
          const SizedBox(height: 10),
          InkWell(
            onTap: () async {
              final picked = await showDatePicker(
                context: context,
                initialDate: _followUpDate ?? DateTime.now().add(const Duration(days: 7)),
                firstDate: DateTime.now().add(const Duration(days: 1)),
                lastDate: DateTime.now().add(const Duration(days: 90)),
                helpText: 'Select Follow-up Date',
                builder: (ctx, child) => Theme(
                  data: Theme.of(ctx).copyWith(
                    colorScheme: Theme.of(ctx).colorScheme.copyWith(primary: AppColors.primary),
                  ),
                  child: child!,
                ),
              );
              if (picked != null) setState(() => _followUpDate = picked);
            },
            borderRadius: BorderRadius.circular(AppRadius.md),
            child: Container(
              padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 14),
              decoration: BoxDecoration(
                color: _followUpDate != null
                    ? AppColors.primary.withOpacity(0.06)
                    : AppColors.surface,
                borderRadius: BorderRadius.circular(AppRadius.md),
                border: Border.all(
                  color: _followUpDate != null ? AppColors.primary.withOpacity(0.4) : AppColors.outline,
                ),
              ),
              child: Row(
                children: [
                  Icon(Icons.calendar_today_rounded,
                      size: 18,
                      color: _followUpDate != null ? AppColors.primary : AppColors.textMuted),
                  const SizedBox(width: 10),
                  Expanded(
                    child: Text(
                      _followUpDate != null
                          ? 'Follow-up: ${DateFormat('MMM d, yyyy').format(_followUpDate!)}'
                          : 'Tap to set follow-up reminder date',
                      style: TextStyle(
                        color: _followUpDate != null ? AppColors.primary : AppColors.textMuted,
                        fontSize: 14,
                        fontWeight: _followUpDate != null ? FontWeight.w600 : FontWeight.w400,
                      ),
                    ),
                  ),
                  if (_followUpDate != null)
                    GestureDetector(
                      onTap: () => setState(() => _followUpDate = null),
                      child: Icon(Icons.close_rounded, size: 18, color: AppColors.textMuted),
                    ),
                ],
              ),
            ),
          ).animate(delay: 220.ms).fadeIn(),
          if (_followUpDate != null)
            Padding(
              padding: const EdgeInsets.only(top: 6, left: 4),
              child: Text(
                'A visit will be auto-scheduled on this date as a reminder.',
                style: Theme.of(context).textTheme.bodySmall?.copyWith(color: AppColors.success),
              ),
            ),

          const SizedBox(height: 28),

          // ── Actions ───────────────────────────────────────────
          FilledButton.icon(
            icon: _isSubmitting
                ? const SizedBox(width: 18, height: 18, child: CircularProgressIndicator(strokeWidth: 2, color: Colors.white))
                : const Icon(Icons.check_circle_rounded),
            label: Text(_isSubmitting ? 'Submitting...' : 'Mark as Completed'),
            onPressed: _isSubmitting ? null : _submit,
            style: FilledButton.styleFrom(
              backgroundColor: AppColors.success,
              minimumSize: const Size.fromHeight(52),
            ),
          ).animate(delay: 260.ms).slideY(begin: 0.2).fadeIn(),

          const SizedBox(height: 12),

          OutlinedButton.icon(
            icon: const Icon(Icons.cancel_outlined),
            label: const Text('Mark as Missed'),
            style: OutlinedButton.styleFrom(
              foregroundColor: AppColors.error,
              side: const BorderSide(color: AppColors.error),
              minimumSize: const Size.fromHeight(48),
            ),
            onPressed: () => context.go('/visits/${widget.visitId}/missed'),
          ).animate(delay: 300.ms).slideY(begin: 0.2).fadeIn(),

          const SizedBox(height: AppSpacing.lg),
        ],
      ),
    );
  }
}

class _VisitTypeSelector extends StatelessWidget {
  final String selected;
  final ValueChanged<String> onChanged;

  const _VisitTypeSelector({required this.selected, required this.onChanged});

  @override
  Widget build(BuildContext context) {
    const types = [
      ('regular', 'Regular', Icons.route_rounded),
      ('follow_up', 'Follow-up', Icons.replay_rounded),
      ('priority', 'Priority', Icons.star_rounded),
      ('demo', 'Demo', Icons.present_to_all_rounded),
    ];

    return Wrap(
      spacing: 8,
      runSpacing: 8,
      children: types.map((t) {
        final isSelected = selected == t.$1;
        return ChoiceChip(
          avatar: Icon(t.$3, size: 16, color: isSelected ? AppColors.onPrimary : AppColors.onBackground),
          label: Text(t.$2),
          selected: isSelected,
          selectedColor: AppColors.primary,
          labelStyle: TextStyle(
            color: isSelected ? AppColors.onPrimary : AppColors.onSurface,
            fontWeight: FontWeight.w600,
            fontSize: 13,
          ),
          onSelected: (_) => onChanged(t.$1),
        );
      }).toList(),
    );
  }
}
