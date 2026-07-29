import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';

import '../../../core/theme/app_theme.dart';
import '../../../core/widgets/mock_location_guard.dart';
import '../data/visit_repository.dart';

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

  @override
  void dispose() {
    _contactCtrl.dispose();
    _phoneCtrl.dispose();
    _notesCtrl.dispose();
    super.dispose();
  }

  Future<void> _submit() async {
    if (!_formKey.currentState!.validate()) return;
    // Block if mock GPS is active
    final gpsClean = await MockLocationGuard.check(context, ref);
    if (!gpsClean) return;
    setState(() => _isSubmitting = true);
    try {
      await ref.read(visitRepositoryProvider).completeVisit(widget.visitId, {
        'contactPerson': _contactCtrl.text.trim(),
        'contactPhone': _phoneCtrl.text.trim(),
        'notes': _notesCtrl.text.trim(),
        'visitType': _visitType,
      });
      await ref.read(visitListProvider.notifier).refresh();
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: const Text('Visit completed successfully!'),
            backgroundColor: AppColors.success,
            behavior: SnackBarBehavior.floating,
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
          ),
        );
      }
    } finally {
      if (mounted) setState(() => _isSubmitting = false);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: AppColors.background,
      appBar: AppBar(
        title: const Text('Complete Visit'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios_new_rounded),
          onPressed: () => context.go('/visits'),
        ),
      ),
      body: Form(
        key: _formKey,
        child: ListView(
          padding: const EdgeInsets.all(AppSpacing.md),
          children: [
            // Header card
            Container(
              padding: const EdgeInsets.all(16),
              decoration: BoxDecoration(
                gradient: LinearGradient(
                  colors: [
                    AppColors.secondary.withOpacity(0.1),
                    AppColors.secondary.withOpacity(0.05),
                  ],
                ),
                borderRadius: BorderRadius.circular(AppRadius.lg),
                border: Border.all(color: AppColors.secondary.withOpacity(0.3)),
              ),
              child: Row(
                children: [
                  Icon(Icons.check_circle_outline_rounded,
                      color: AppColors.secondary, size: 28),
                  const SizedBox(width: 12),
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text('Visit #${widget.visitId}',
                            style: Theme.of(context).textTheme.titleSmall?.copyWith(
                                  color: AppColors.secondary,
                                )),
                        Text(
                          'Fill in the details to mark this visit complete',
                          style: Theme.of(context).textTheme.bodySmall,
                        ),
                      ],
                    ),
                  ),
                ],
              ),
            ).animate().fadeIn(duration: 400.ms),

            const SizedBox(height: 20),
            Text('Contact Details',
                style: Theme.of(context)
                    .textTheme
                    .labelLarge
                    ?.copyWith(color: AppColors.primary)),
            const SizedBox(height: 12),

            TextFormField(
              controller: _contactCtrl,
              textCapitalization: TextCapitalization.words,
              decoration: const InputDecoration(
                labelText: 'Contact Person *',
                prefixIcon: Icon(Icons.person_outline_rounded),
              ),
              validator: (v) =>
                  v == null || v.trim().isEmpty ? 'Contact person is required' : null,
            ).animate(delay: 100.ms).slideY(begin: 0.2).fadeIn(),

            const SizedBox(height: 14),
            TextFormField(
              controller: _phoneCtrl,
              keyboardType: TextInputType.phone,
              decoration: const InputDecoration(
                labelText: 'Phone Number *',
                prefixIcon: Icon(Icons.phone_outlined),
              ),
              validator: (v) =>
                  v == null || v.trim().isEmpty ? 'Phone number is required' : null,
            ).animate(delay: 150.ms).slideY(begin: 0.2).fadeIn(),

            const SizedBox(height: 20),
            Text('Visit Details',
                style: Theme.of(context)
                    .textTheme
                    .labelLarge
                    ?.copyWith(color: AppColors.primary)),
            const SizedBox(height: 12),

            // Visit type
            _VisitTypeSelector(
              selected: _visitType,
              onChanged: (v) => setState(() => _visitType = v),
            ).animate(delay: 200.ms).fadeIn(),

            const SizedBox(height: 14),
            TextFormField(
              controller: _notesCtrl,
              maxLines: 4,
              textCapitalization: TextCapitalization.sentences,
              decoration: const InputDecoration(
                labelText: 'Discussion Notes *',
                hintText: 'What was discussed? Any feedback or follow-up needed?',
                prefixIcon: Padding(
                  padding: EdgeInsets.only(bottom: 60),
                  child: Icon(Icons.notes_rounded),
                ),
                alignLabelWithHint: true,
              ),
              validator: (v) =>
                  v == null || v.trim().length < 10 ? 'Please add at least 10 characters' : null,
            ).animate(delay: 250.ms).slideY(begin: 0.2).fadeIn(),

            const SizedBox(height: 28),
            FilledButton.icon(
              icon: _isSubmitting
                  ? const SizedBox(
                      width: 18,
                      height: 18,
                      child:
                          CircularProgressIndicator(strokeWidth: 2, color: Colors.white),
                    )
                  : const Icon(Icons.check_rounded),
              label: Text(_isSubmitting ? 'Submitting...' : 'Mark as Completed'),
              onPressed: _isSubmitting ? null : _submit,
            ).animate(delay: 300.ms).slideY(begin: 0.2).fadeIn(),

            const SizedBox(height: 12),
            OutlinedButton.icon(
              icon: const Icon(Icons.cancel_outlined),
              label: const Text('Mark as Missed'),
              style: OutlinedButton.styleFrom(
                foregroundColor: AppColors.error,
                side: const BorderSide(color: AppColors.error),
              ),
              onPressed: () => context.go('/visits/${widget.visitId}/missed'),
            ).animate(delay: 350.ms).slideY(begin: 0.2).fadeIn(),

            const SizedBox(height: AppSpacing.lg),
          ],
        ),
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
      ('adhoc', 'Ad-hoc', Icons.add_location_alt_rounded),
    ];

    return Wrap(
      spacing: 8,
      runSpacing: 8,
      children: types.map((t) {
        final isSelected = selected == t.$1;
        return ChoiceChip(
          avatar: Icon(t.$3,
              size: 16,
              color: isSelected ? AppColors.onPrimary : AppColors.onBackground),
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
