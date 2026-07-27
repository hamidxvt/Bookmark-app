import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:image_picker/image_picker.dart';

import '../../../core/network/dio_client.dart';
import '../../../core/constants/api_constants.dart';

class MissedVisitScreen extends ConsumerStatefulWidget {
  final int visitId;
  const MissedVisitScreen({super.key, required this.visitId});

  @override
  ConsumerState<MissedVisitScreen> createState() => _MissedVisitScreenState();
}

class _MissedVisitScreenState extends ConsumerState<MissedVisitScreen> {
  final _reasonCtrl = TextEditingController();
  String? _photoPath;
  bool _loading = false;
  String? _error;

  Future<void> _pickPhoto() async {
    final picker = ImagePicker();
    final image = await picker.pickImage(source: ImageSource.camera, imageQuality: 70);
    if (image != null) setState(() => _photoPath = image.path);
  }

  Future<void> _submit() async {
    if (_reasonCtrl.text.trim().isEmpty) {
      setState(() => _error = 'Please provide a reason.');
      return;
    }
    if (_photoPath == null) {
      setState(() => _error = 'Photo evidence is required.');
      return;
    }
    setState(() { _loading = true; _error = null; });
    try {
      final dio = ref.read(dioClientProvider);
      await dio.post(ApiConstants.visitMissed(widget.visitId), data: {
        'reason': _reasonCtrl.text.trim(),
        'photoUrl': _photoPath, // In production: upload to CDN first
      });
      if (mounted) Navigator.of(context).pop(true);
    } catch (e) {
      setState(() => _error = e.toString());
    } finally {
      if (mounted) setState(() => _loading = false);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Report Missed Visit')),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          const Text(
            'A missed visit requires photo evidence and a written reason. It will be reviewed by your City Head.',
            style: TextStyle(color: Colors.grey),
          ),
          const SizedBox(height: 20),
          TextFormField(
            controller: _reasonCtrl,
            maxLines: 4,
            decoration: const InputDecoration(
              labelText: 'Reason for missing this visit',
              alignLabelWithHint: true,
              border: OutlineInputBorder(),
            ),
          ),
          const SizedBox(height: 16),
          OutlinedButton.icon(
            icon: const Icon(Icons.camera_alt_outlined),
            label: Text(_photoPath == null ? 'Take Photo Evidence' : 'Photo Captured ✓'),
            style: _photoPath != null
                ? OutlinedButton.styleFrom(foregroundColor: Colors.green)
                : null,
            onPressed: _pickPhoto,
          ),
          if (_error != null) ...[
            const SizedBox(height: 12),
            Text(_error!, style: const TextStyle(color: Colors.red)),
          ],
          const SizedBox(height: 24),
          FilledButton(
            onPressed: _loading ? null : _submit,
            style: FilledButton.styleFrom(backgroundColor: Colors.red),
            child: _loading
                ? const CircularProgressIndicator(color: Colors.white)
                : const Text('Submit Missed Visit Report'),
          ),
        ],
      ),
    );
  }
}
