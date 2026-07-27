import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../core/network/dio_client.dart';
import '../../../core/constants/api_constants.dart';

class CompleteVisitScreen extends ConsumerStatefulWidget {
  final int visitId;
  const CompleteVisitScreen({super.key, required this.visitId});

  @override
  ConsumerState<CompleteVisitScreen> createState() => _CompleteVisitScreenState();
}

class _CompleteVisitScreenState extends ConsumerState<CompleteVisitScreen> {
  final _formKey = GlobalKey<FormState>();
  final _contactCtrl = TextEditingController();
  final _designationCtrl = TextEditingController();
  final _phoneCtrl = TextEditingController();
  final _notesCtrl = TextEditingController();
  String _visitType = 'sales_call';
  int _samples = 0;
  bool _loading = false;
  String? _error;

  static const _visitTypes = ['sales_call', 'follow_up', 'introduction', 'collection'];

  @override
  void dispose() {
    _contactCtrl.dispose();
    _designationCtrl.dispose();
    _phoneCtrl.dispose();
    _notesCtrl.dispose();
    super.dispose();
  }

  Future<void> _submit() async {
    if (!_formKey.currentState!.validate()) return;
    setState(() { _loading = true; _error = null; });
    try {
      final dio = ref.read(dioClientProvider);
      await dio.post(ApiConstants.visitComplete(widget.visitId), data: {
        'contactPerson': _contactCtrl.text.trim(),
        'designation': _designationCtrl.text.trim(),
        'phone': _phoneCtrl.text.trim(),
        'notes': _notesCtrl.text.trim(),
        'visitType': _visitType,
        'sampleDistributed': _samples,
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
      appBar: AppBar(title: const Text('Complete Visit')),
      body: Form(
        key: _formKey,
        child: ListView(
          padding: const EdgeInsets.all(16),
          children: [
            _buildField(_contactCtrl, 'Contact Person', Icons.person_outline),
            const SizedBox(height: 12),
            _buildField(_designationCtrl, 'Designation', Icons.badge_outlined),
            const SizedBox(height: 12),
            _buildField(_phoneCtrl, 'Phone Number', Icons.phone_outlined,
                type: TextInputType.phone),
            const SizedBox(height: 12),
            DropdownButtonFormField<String>(
              value: _visitType,
              decoration: const InputDecoration(labelText: 'Visit Type', border: OutlineInputBorder()),
              items: _visitTypes
                  .map((t) => DropdownMenuItem(value: t, child: Text(t.replaceAll('_', ' '))))
                  .toList(),
              onChanged: (v) => setState(() => _visitType = v!),
            ),
            const SizedBox(height: 12),
            Row(
              children: [
                const Text('Samples Distributed:'),
                const SizedBox(width: 12),
                IconButton(
                    icon: const Icon(Icons.remove_circle_outline),
                    onPressed: _samples > 0 ? () => setState(() => _samples--) : null),
                Text('$_samples',
                    style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
                IconButton(
                    icon: const Icon(Icons.add_circle_outline),
                    onPressed: () => setState(() => _samples++)),
              ],
            ),
            const SizedBox(height: 12),
            TextFormField(
              controller: _notesCtrl,
              maxLines: 4,
              decoration: const InputDecoration(
                  labelText: 'Discussion Notes / Feedback',
                  alignLabelWithHint: true,
                  border: OutlineInputBorder()),
              validator: (v) => v == null || v.trim().isEmpty ? 'Notes are required' : null,
            ),
            if (_error != null) ...[
              const SizedBox(height: 12),
              Text(_error!, style: const TextStyle(color: Colors.red)),
            ],
            const SizedBox(height: 24),
            FilledButton(
              onPressed: _loading ? null : _submit,
              child: _loading
                  ? const CircularProgressIndicator(color: Colors.white)
                  : const Text('Mark as Completed'),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildField(TextEditingController ctrl, String label, IconData icon,
      {TextInputType type = TextInputType.text}) {
    return TextFormField(
      controller: ctrl,
      keyboardType: type,
      decoration: InputDecoration(
          labelText: label, prefixIcon: Icon(icon), border: const OutlineInputBorder()),
      validator: (v) => v == null || v.trim().isEmpty ? '$label is required' : null,
    );
  }
}
