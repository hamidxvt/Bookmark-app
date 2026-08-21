import 'dart:io';
import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:image_picker/image_picker.dart';

import '../../../core/theme/app_theme.dart';
import '../data/visit_repository.dart';

class MissedVisitScreen extends ConsumerStatefulWidget {
  final int visitId;
  const MissedVisitScreen({super.key, required this.visitId});

  @override
  ConsumerState<MissedVisitScreen> createState() => _MissedVisitScreenState();
}

class _MissedVisitScreenState extends ConsumerState<MissedVisitScreen> {
  final _reasonCtrl = TextEditingController();
  bool _isSubmitting = false;
  String _selectedReason = '';
  final List<XFile> _photos = [];
  final _picker = ImagePicker();

  static const _presetReasons = [
    'Location was closed',
    'Contact person unavailable',
    'Weather conditions',
    'Traffic / Road issue',
    'Emergency situation',
  ];

  @override
  void dispose() {
    _reasonCtrl.dispose();
    super.dispose();
  }

  Future<void> _pickImage(ImageSource source) async {
    final picked = await _picker.pickImage(source: source, imageQuality: 70, maxWidth: 1200);
    if (picked != null) {
      setState(() => _photos.add(picked));
    }
  }

  Future<void> _submit() async {
    final reason = _reasonCtrl.text.trim().isNotEmpty
        ? _reasonCtrl.text.trim()
        : _selectedReason;
    if (reason.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Please provide a reason for missing this visit')),
      );
      return;
    }
    setState(() => _isSubmitting = true);
    try {
      // Encode photos to base64 strings for database storage
      final List<String> photoBase64 = [];
      for (final xFile in _photos) {
        final bytes = await File(xFile.path).readAsBytes();
        photoBase64.add('data:image/jpeg;base64,${base64Encode(bytes)}');
      }

      await ref.read(visitRepositoryProvider).markMissed(
            widget.visitId,
            reason,
            photoBase64: photoBase64.isEmpty ? null : photoBase64,
          );
      await ref.read(visitListProvider.notifier).refresh();
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: const Text('Visit marked as missed'),
            backgroundColor: AppColors.warning,
          ),
        );
        context.pop();
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
        title: const Text('Miss Visit'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios_new_rounded),
          onPressed: () => context.pop(),
        ),
      ),
      body: ListView(
        padding: const EdgeInsets.all(AppSpacing.md),
        children: [
          // Warning card
          Container(
            padding: const EdgeInsets.all(16),
            decoration: BoxDecoration(
              color: AppColors.error.withOpacity(0.06),
              borderRadius: BorderRadius.circular(AppRadius.lg),
              border: Border.all(color: AppColors.error.withOpacity(0.3)),
            ),
            child: Row(
              children: [
                const Icon(Icons.warning_amber_rounded,
                    color: AppColors.error, size: 28),
                const SizedBox(width: 12),
                Expanded(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Missed visits require justification',
                        style: Theme.of(context).textTheme.titleSmall?.copyWith(
                              color: AppColors.error,
                            ),
                      ),
                      const SizedBox(height: 4),
                      Text(
                        'Your reason will be reviewed by your City Head for approval.',
                        style: Theme.of(context).textTheme.bodySmall,
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ).animate().fadeIn(duration: 400.ms),

          const SizedBox(height: 20),
          Text('Select a reason',
              style: Theme.of(context)
                  .textTheme
                  .labelLarge
                  ?.copyWith(color: AppColors.primary)),
          const SizedBox(height: 12),

          ..._presetReasons.map((reason) => Padding(
                padding: const EdgeInsets.only(bottom: 8),
                child: InkWell(
                  borderRadius: BorderRadius.circular(AppRadius.md),
                  onTap: () {
                    setState(() {
                      _selectedReason = reason;
                      _reasonCtrl.clear();
                    });
                  },
                  child: Container(
                    padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 12),
                    decoration: BoxDecoration(
                      color: _selectedReason == reason
                          ? AppColors.error.withOpacity(0.08)
                          : AppColors.surface,
                      borderRadius: BorderRadius.circular(AppRadius.md),
                      border: Border.all(
                        color: _selectedReason == reason
                            ? AppColors.error.withOpacity(0.5)
                            : AppColors.outline,
                      ),
                    ),
                    child: Row(
                      children: [
                        Icon(
                          _selectedReason == reason
                              ? Icons.radio_button_checked_rounded
                              : Icons.radio_button_off_rounded,
                          color: _selectedReason == reason
                              ? AppColors.error
                              : AppColors.outline,
                          size: 20,
                        ),
                        const SizedBox(width: 12),
                        Text(reason,
                            style: Theme.of(context).textTheme.bodyMedium?.copyWith(
                                  color: _selectedReason == reason
                                      ? AppColors.error
                                      : AppColors.onSurface,
                                  fontWeight: _selectedReason == reason
                                      ? FontWeight.w600
                                      : FontWeight.w400,
                                )),
                      ],
                    ),
                  ),
                ),
              ).animate(delay: ((_presetReasons.indexOf(reason)) * 50).ms).fadeIn()),

          const SizedBox(height: 16),
          Text('Or write your own reason',
              style: Theme.of(context)
                  .textTheme
                  .labelLarge
                  ?.copyWith(color: AppColors.primary)),
          const SizedBox(height: 12),

          TextFormField(
            controller: _reasonCtrl,
            maxLines: 3,
            onChanged: (v) {
              if (v.isNotEmpty) setState(() => _selectedReason = '');
            },
            textCapitalization: TextCapitalization.sentences,
            decoration: const InputDecoration(
              hintText: 'Describe what happened...',
              prefixIcon: Padding(
                padding: EdgeInsets.only(bottom: 40),
                child: Icon(Icons.edit_note_rounded),
              ),
              alignLabelWithHint: true,
            ),
          ).animate(delay: 300.ms).slideY(begin: 0.2).fadeIn(),

          const SizedBox(height: 20),

          // Photo evidence section
          Text('Add Evidence (Optional)',
              style: Theme.of(context)
                  .textTheme
                  .labelLarge
                  ?.copyWith(color: AppColors.primary)),
          const SizedBox(height: 8),
          Row(
            children: [
              _PhotoButton(
                icon: Icons.camera_alt_rounded,
                label: 'Camera',
                onTap: () => _pickImage(ImageSource.camera),
              ),
              const SizedBox(width: 10),
              _PhotoButton(
                icon: Icons.photo_library_rounded,
                label: 'Gallery',
                onTap: () => _pickImage(ImageSource.gallery),
              ),
            ],
          ),

          if (_photos.isNotEmpty) ...[
            const SizedBox(height: 12),
            SizedBox(
              height: 90,
              child: ListView.separated(
                scrollDirection: Axis.horizontal,
                itemCount: _photos.length,
                separatorBuilder: (_, __) => const SizedBox(width: 8),
                itemBuilder: (_, i) => Stack(
                  children: [
                    ClipRRect(
                      borderRadius: BorderRadius.circular(10),
                      child: Image.file(
                        File(_photos[i].path),
                        width: 90,
                        height: 90,
                        fit: BoxFit.cover,
                      ),
                    ),
                    Positioned(
                      top: 4,
                      right: 4,
                      child: GestureDetector(
                        onTap: () => setState(() => _photos.removeAt(i)),
                        child: Container(
                          decoration: const BoxDecoration(
                            color: Colors.black54,
                            shape: BoxShape.circle,
                          ),
                          padding: const EdgeInsets.all(3),
                          child: const Icon(Icons.close, color: Colors.white, size: 14),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ],

          const SizedBox(height: 28),

          FilledButton.icon(
            style: FilledButton.styleFrom(backgroundColor: AppColors.error),
            icon: _isSubmitting
                ? const SizedBox(
                    width: 18,
                    height: 18,
                    child: CircularProgressIndicator(strokeWidth: 2, color: Colors.white),
                  )
                : const Icon(Icons.report_rounded),
            label: Text(_isSubmitting ? 'Submitting...' : 'Confirm Missed Visit'),
            onPressed: _isSubmitting ? null : _submit,
          ).animate(delay: 400.ms).slideY(begin: 0.2).fadeIn(),

          const SizedBox(height: AppSpacing.lg),
        ],
      ),
    );
  }
}

class _PhotoButton extends StatelessWidget {
  final IconData icon;
  final String label;
  final VoidCallback onTap;
  const _PhotoButton({required this.icon, required this.label, required this.onTap});

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: InkWell(
        onTap: onTap,
        borderRadius: BorderRadius.circular(AppRadius.md),
        child: Container(
          padding: const EdgeInsets.symmetric(vertical: 12),
          decoration: BoxDecoration(
            color: AppColors.surface,
            borderRadius: BorderRadius.circular(AppRadius.md),
            border: Border.all(color: AppColors.outline),
          ),
          child: Column(
            children: [
              Icon(icon, color: AppColors.primary, size: 24),
              const SizedBox(height: 4),
              Text(label, style: const TextStyle(fontSize: 12, fontWeight: FontWeight.w600)),
            ],
          ),
        ),
      ),
    );
  }
}
