import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:image_picker/image_picker.dart';
import 'dart:convert';
import 'dart:io';

import '../../../core/theme/app_theme.dart';
import '../../../core/network/dio_client.dart';

class AddCustomerScreen extends ConsumerStatefulWidget {
  final int visitId;

  const AddCustomerScreen({super.key, required this.visitId});

  @override
  ConsumerState<AddCustomerScreen> createState() => _AddCustomerScreenState();
}

class _AddCustomerScreenState extends ConsumerState<AddCustomerScreen> {
  late final TextEditingController _nameCtrl;
  late final TextEditingController _ownerCtrl;
  late final TextEditingController _phoneCtrl;
  late final TextEditingController _emailCtrl;
  late final TextEditingController _addressCtrl;
  late final TextEditingController _categoryCtrl;

  File? _photo;
  bool _loading = false;

  @override
  void initState() {
    super.initState();
    _nameCtrl = TextEditingController();
    _ownerCtrl = TextEditingController();
    _phoneCtrl = TextEditingController();
    _emailCtrl = TextEditingController();
    _addressCtrl = TextEditingController();
    _categoryCtrl = TextEditingController();
  }

  @override
  void dispose() {
    _nameCtrl.dispose();
    _ownerCtrl.dispose();
    _phoneCtrl.dispose();
    _emailCtrl.dispose();
    _addressCtrl.dispose();
    _categoryCtrl.dispose();
    super.dispose();
  }

  Future<void> _pickPhoto() async {
    final picker = ImagePicker();
    final image = await picker.pickImage(source: ImageSource.camera);
    if (image != null) {
      setState(() => _photo = File(image.path));
    }
  }

  Future<void> _submitCustomer() async {
    if (_nameCtrl.text.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Please enter customer name'), backgroundColor: AppColors.error),
      );
      return;
    }

    setState(() => _loading = true);

    try {
      final dio = ref.read(dioClientProvider);
      
      // Convert photo to base64 if present
      String? photoBase64;
      if (_photo != null) {
        final bytes = await _photo!.readAsBytes();
        photoBase64 = 'data:image/jpeg;base64,${base64Encode(bytes)}';
      }

      final res = await dio.post(
        '/customers',
        data: {
          'name': _nameCtrl.text.trim(),
          'ownerName': _ownerCtrl.text.trim(),
          'ownerPhone': _phoneCtrl.text.trim(),
          'email': _emailCtrl.text.trim(),
          'address': _addressCtrl.text.trim(),
          'category': _categoryCtrl.text.trim(),
          'photo': photoBase64,
          'latitude': 0.0,
          'longitude': 0.0,
        },
      );

      if (res.data['success'] == true) {
        final customerId = res.data['data']['id'];

        // Link customer to visit (start visit with new customer)
        await dio.post(
          '/visits',
          data: {
            'customerId': customerId,
            'visitDate': DateTime.now().toIso8601String(),
          },
        ).catchError((_) => null);

        if (mounted) {
          ScaffoldMessenger.of(context).showSnackBar(
            const SnackBar(
              content: Row(children: [
                Icon(Icons.check_circle_rounded, color: Colors.white, size: 18),
                SizedBox(width: 8),
                Expanded(child: Text('Customer added & visit started!')),
              ]),
              backgroundColor: AppColors.success,
              duration: Duration(seconds: 2),
            ),
          );
          context.pop(customerId);
        }
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
    return Scaffold(
      backgroundColor: AppColors.background,
      appBar: AppBar(
        title: const Text('Add New Customer'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios_new_rounded),
          onPressed: () => context.pop(),
        ),
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // Photo section
            GestureDetector(
              onTap: _loading ? null : _pickPhoto,
              child: Container(
                width: double.infinity,
                height: 150,
                decoration: BoxDecoration(
                  color: Colors.white,
                  border: Border.all(color: AppColors.primary.withOpacity(0.3)),
                  borderRadius: BorderRadius.circular(12),
                ),
                child: _photo != null
                    ? ClipRRect(
                        borderRadius: BorderRadius.circular(12),
                        child: Image.file(_photo!, fit: BoxFit.cover),
                      )
                    : Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Icon(Icons.camera_alt_outlined, size: 40, color: AppColors.primary),
                          const SizedBox(height: 8),
                          const Text('Tap to take photo', style: TextStyle(color: Color(0xFF94A3B8))),
                        ],
                      ),
              ),
            ),
            const SizedBox(height: 20),

            // Form fields
            _buildField('Shop/School Name', _nameCtrl, Icons.storefront_rounded),
            _buildField('Owner Name', _ownerCtrl, Icons.person_outline_rounded),
            _buildField('Phone', _phoneCtrl, Icons.phone_outlined, keyboardType: TextInputType.phone),
            _buildField('Email', _emailCtrl, Icons.email_outlined, keyboardType: TextInputType.emailAddress),
            _buildField('Address', _addressCtrl, Icons.location_on_outlined, maxLines: 2),
            _buildField('Category', _categoryCtrl, Icons.label_outline),

            const SizedBox(height: 24),

            // Submit button
            SizedBox(
              width: double.infinity,
              height: 48,
              child: ElevatedButton.icon(
                onPressed: _loading ? null : _submitCustomer,
                icon: _loading
                    ? const SizedBox(
                        width: 20,
                        height: 20,
                        child: CircularProgressIndicator(strokeWidth: 2, valueColor: AlwaysStoppedAnimation(Colors.white)),
                      )
                    : const Icon(Icons.check_circle_rounded),
                label: Text(_loading ? 'Adding...' : 'Add & Start Visit'),
                style: ElevatedButton.styleFrom(
                  backgroundColor: AppColors.primary,
                  foregroundColor: Colors.white,
                  shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildField(
    String label,
    TextEditingController controller,
    IconData icon, {
    TextInputType keyboardType = TextInputType.text,
    int maxLines = 1,
  }) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(label, style: const TextStyle(fontSize: 12, fontWeight: FontWeight.w600)),
          const SizedBox(height: 6),
          TextField(
            controller: controller,
            keyboardType: keyboardType,
            maxLines: maxLines,
            enabled: !_loading,
            decoration: InputDecoration(
              prefixIcon: Icon(icon, size: 18, color: AppColors.primary),
              border: OutlineInputBorder(borderRadius: BorderRadius.circular(10)),
              enabledBorder: OutlineInputBorder(
                borderRadius: BorderRadius.circular(10),
                borderSide: const BorderSide(color: Color(0xFFE8EAEE)),
              ),
              focusedBorder: OutlineInputBorder(
                borderRadius: BorderRadius.circular(10),
                borderSide: const BorderSide(color: AppColors.primary, width: 1.5),
              ),
              filled: true,
              fillColor: Colors.white,
              contentPadding: const EdgeInsets.symmetric(vertical: 12, horizontal: 12),
            ),
          ),
        ],
      ),
    );
  }
}
