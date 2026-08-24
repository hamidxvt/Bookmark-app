import 'dart:convert';
import 'dart:typed_data';
import 'dart:ui' as ui;

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:intl/intl.dart';
import 'package:printing/printing.dart';
import 'package:pdf/pdf.dart';
import 'package:pdf/widgets.dart' as pw;
import 'package:share_plus/share_plus.dart';
import 'package:signature/signature.dart';
import 'package:path_provider/path_provider.dart';
import 'dart:io';

import '../../../core/theme/app_theme.dart';
import '../../../core/network/dio_client.dart';

// ── Model ─────────────────────────────────────────────────────────────────────
class SampleRequest {
  final int id;
  final String productName, status;
  final int quantity;
  final String? notes, customerName, adminNotes;
  final DateTime createdAt;
  final Map<String, dynamic>? customer;

  const SampleRequest({
    required this.id,
    required this.productName,
    required this.status,
    required this.quantity,
    this.notes,
    this.customerName,
    this.adminNotes,
    required this.createdAt,
    this.customer,
  });

  factory SampleRequest.fromJson(Map<String, dynamic> j) => SampleRequest(
        id: j['id'] ?? 0,
        productName: j['productName'] ?? 'Sample',
        status: j['status'] ?? 'pending',
        quantity: (j['quantity'] ?? 1) as int,
        notes: j['notes'] as String?,
        customerName: j['customerName'] as String?,
        adminNotes: j['adminNotes'] as String?,
        createdAt: j['createdAt'] != null
            ? DateTime.tryParse(j['createdAt']) ?? DateTime.now()
            : DateTime.now(),
        customer: j['customer'] as Map<String, dynamic>?,
      );
}

// ── Providers ─────────────────────────────────────────────────────────────────
final samplesListProvider = FutureProvider.autoDispose<List<SampleRequest>>((ref) async {
  final dio = ref.watch(dioClientProvider);
  final res = await dio.get('/samples');
  final raw = res.data;
  final list = raw['data'] as List? ?? [];
  return list.cast<Map<String, dynamic>>().map(SampleRequest.fromJson).toList();
});

final customersSearchProvider = FutureProvider.family.autoDispose<List<Map<String, dynamic>>, String>(
  (ref, query) async {
    final dio = ref.watch(dioClientProvider);
    final res = await dio.get('/customers', params: {'search': query, 'length': 30});
    final d = res.data['data'];
    if (d is Map && d['data'] is List) {
      return (d['data'] as List).cast<Map<String, dynamic>>();
    }
    if (d is List) return d.cast<Map<String, dynamic>>();
    return [];
  },
);

// ── Screen ────────────────────────────────────────────────────────────────────
class SamplesScreen extends ConsumerStatefulWidget {
  const SamplesScreen({super.key});

  @override
  ConsumerState<SamplesScreen> createState() => _SamplesScreenState();
}

class _SamplesScreenState extends ConsumerState<SamplesScreen> {
  @override
  Widget build(BuildContext context) {
    final async = ref.watch(samplesListProvider);
    return Scaffold(
      backgroundColor: AppColors.background,
      appBar: AppBar(
        title: const Text('Sample Management'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios_new_rounded),
          onPressed: () => context.pop(),
        ),
        actions: [
          IconButton(
            icon: const Icon(Icons.refresh_rounded),
            onPressed: () => ref.invalidate(samplesListProvider),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton.extended(
        onPressed: () => _showNewRequestSheet(context),
        icon: const Icon(Icons.add_rounded),
        label: const Text('Request Sample'),
        backgroundColor: AppColors.primary,
        foregroundColor: Colors.white,
      ),
      body: async.when(
        loading: () => const Center(child: CircularProgressIndicator()),
        error: (e, _) => _ErrorView(message: e.toString(), onRetry: () => ref.invalidate(samplesListProvider)),
        data: (samples) {
          if (samples.isEmpty) {
            return Center(
              child: Column(mainAxisSize: MainAxisSize.min, children: [
                Icon(Icons.inventory_2_outlined, size: 64, color: Colors.grey.shade300),
                const SizedBox(height: 16),
                Text('No sample requests yet', style: TextStyle(color: Colors.grey.shade500, fontSize: 16)),
                const SizedBox(height: 8),
                Text('Tap + to request a new sample', style: TextStyle(color: Colors.grey.shade400, fontSize: 13)),
              ]),
            );
          }

          // Group: pending admin review, approved (can deliver), others
          final pending = samples.where((s) => s.status == 'pending').toList();
          final approved = samples.where((s) => s.status == 'approved').toList();
          final delivered = samples.where((s) => s.status == 'delivered').toList();
          final rejected = samples.where((s) => s.status == 'rejected').toList();

          return ListView(
            padding: const EdgeInsets.fromLTRB(16, 16, 16, 100),
            children: [
              if (approved.isNotEmpty) ...[
                _SectionHeader('Approved — Ready to Deliver', Icons.check_circle_outline, Colors.green),
                const SizedBox(height: 8),
                ...approved.map((s) => _SampleCard(
                  sample: s,
                  onTap: () => _openDeliveryFlow(context, s),
                ).animate().fadeIn().slideY(begin: 0.05)),
                const SizedBox(height: 24),
              ],
              if (pending.isNotEmpty) ...[
                _SectionHeader('Pending Review', Icons.hourglass_top_rounded, Colors.amber.shade700),
                const SizedBox(height: 8),
                ...pending.map((s) => _SampleCard(sample: s)),
                const SizedBox(height: 24),
              ],
              if (delivered.isNotEmpty) ...[
                _SectionHeader('Delivered', Icons.local_shipping_outlined, AppColors.primary),
                const SizedBox(height: 8),
                ...delivered.map((s) => _SampleCard(sample: s)),
                const SizedBox(height: 24),
              ],
              if (rejected.isNotEmpty) ...[
                _SectionHeader('Rejected', Icons.cancel_outlined, Colors.red.shade400),
                const SizedBox(height: 8),
                ...rejected.map((s) => _SampleCard(sample: s)),
              ],
            ],
          );
        },
      ),
    );
  }

  void _showNewRequestSheet(BuildContext context) {
    showModalBottomSheet(
      context: context,
      isScrollControlled: true,
      backgroundColor: Colors.transparent,
      builder: (_) => _NewRequestSheet(
        onSubmitted: () {
          if (mounted) ref.invalidate(samplesListProvider);
        },
      ),
    );
  }

  void _openDeliveryFlow(BuildContext context, SampleRequest sample) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (_) => DeliverSampleScreen(
          sample: sample,
          onDone: () {
            if (mounted) ref.invalidate(samplesListProvider);
          },
        ),
      ),
    );
  }
}

// ── UI Helpers ────────────────────────────────────────────────────────────────
class _SectionHeader extends StatelessWidget {
  final String title;
  final IconData icon;
  final Color color;
  const _SectionHeader(this.title, this.icon, this.color);

  @override
  Widget build(BuildContext context) => Row(children: [
    Icon(icon, size: 16, color: color),
    const SizedBox(width: 6),
    Text(title, style: TextStyle(fontSize: 13, fontWeight: FontWeight.w700, color: color)),
  ]);
}

class _ErrorView extends StatelessWidget {
  final String message;
  final VoidCallback onRetry;
  const _ErrorView({required this.message, required this.onRetry});

  @override
  Widget build(BuildContext context) => Center(
    child: Column(mainAxisSize: MainAxisSize.min, children: [
      const Icon(Icons.error_outline, size: 48, color: Colors.grey),
      const SizedBox(height: 12),
      Text(message, textAlign: TextAlign.center),
      const SizedBox(height: 16),
      ElevatedButton.icon(
        icon: const Icon(Icons.refresh),
        label: const Text('Retry'),
        onPressed: onRetry,
      ),
    ]),
  );
}

class _SampleCard extends StatelessWidget {
  final SampleRequest sample;
  final VoidCallback? onTap;
  const _SampleCard({required this.sample, this.onTap});

  @override
  Widget build(BuildContext context) {
    final statusColor = _statusColor(sample.status);
    final isApproved = sample.status == 'approved';

    return GestureDetector(
      onTap: onTap,
      child: Container(
        margin: const EdgeInsets.only(bottom: 10),
        padding: const EdgeInsets.all(16),
        decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.circular(AppRadius.md),
          border: isApproved
              ? Border.all(color: Colors.green.shade300, width: 1.5)
              : Border.all(color: Colors.grey.shade100),
          boxShadow: [BoxShadow(color: Colors.black.withOpacity(0.04), blurRadius: 8, offset: const Offset(0, 2))],
        ),
        child: Row(children: [
          Container(
            width: 44, height: 44,
            decoration: BoxDecoration(
              color: statusColor.withOpacity(0.1),
              borderRadius: BorderRadius.circular(12),
            ),
            child: Icon(Icons.inventory_2_rounded, color: statusColor, size: 22),
          ),
          const SizedBox(width: 12),
          Expanded(
            child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
              Text(sample.productName,
                  style: const TextStyle(fontWeight: FontWeight.w700, fontSize: 14)),
              const SizedBox(height: 3),
              Text('Qty: ${sample.quantity}  ·  ${DateFormat('dd MMM yyyy').format(sample.createdAt)}',
                  style: TextStyle(fontSize: 12, color: Colors.grey.shade500)),
              if (sample.adminNotes != null && sample.adminNotes!.isNotEmpty) ...[
                const SizedBox(height: 4),
                Text('Note: ${sample.adminNotes}',
                    style: TextStyle(fontSize: 11, color: Colors.grey.shade400, fontStyle: FontStyle.italic)),
              ],
            ]),
          ),
          const SizedBox(width: 8),
          Column(children: [
            Container(
              padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 4),
              decoration: BoxDecoration(
                color: statusColor.withOpacity(0.1),
                borderRadius: BorderRadius.circular(20),
              ),
              child: Text(
                sample.status[0].toUpperCase() + sample.status.substring(1),
                style: TextStyle(fontSize: 11, fontWeight: FontWeight.w700, color: statusColor),
              ),
            ),
            if (isApproved) ...[
              const SizedBox(height: 6),
              Container(
                padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 3),
                decoration: BoxDecoration(
                  color: AppColors.primary,
                  borderRadius: BorderRadius.circular(20),
                ),
                child: const Text('Deliver', style: TextStyle(fontSize: 10, color: Colors.white, fontWeight: FontWeight.w700)),
              ),
            ],
          ]),
        ]),
      ),
    );
  }

  Color _statusColor(String s) => switch (s.toLowerCase()) {
    'approved' => Colors.green,
    'rejected' => Colors.red,
    'delivered' => AppColors.primary,
    _ => Colors.amber.shade700,
  };
}

// ── New Request Sheet ─────────────────────────────────────────────────────────
class _NewRequestSheet extends ConsumerStatefulWidget {
  final VoidCallback onSubmitted;
  const _NewRequestSheet({required this.onSubmitted});

  @override
  ConsumerState<_NewRequestSheet> createState() => _NewRequestSheetState();
}

class _NewRequestSheetState extends ConsumerState<_NewRequestSheet> {
  final _productCtrl = TextEditingController();
  final _notesCtrl = TextEditingController();
  int _qty = 1;
  bool _saving = false;
  String? _error;

  @override
  void dispose() {
    _productCtrl.dispose();
    _notesCtrl.dispose();
    super.dispose();
  }

  Future<void> _submit() async {
    if (_productCtrl.text.trim().isEmpty) {
      setState(() => _error = 'Product name is required');
      return;
    }
    setState(() { _saving = true; _error = null; });
    try {
      final dio = ref.read(dioClientProvider);
      await dio.post('/samples', data: {
        'productName': _productCtrl.text.trim(),
        'quantity': _qty,
        'notes': _notesCtrl.text.trim().isEmpty ? null : _notesCtrl.text.trim(),
      });
      if (mounted) {
        widget.onSubmitted();
        Navigator.pop(context);
      }
    } catch (e) {
      setState(() => _error = e.toString());
    } finally {
      if (mounted) setState(() => _saving = false);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(bottom: MediaQuery.of(context).viewInsets.bottom),
      child: Container(
        decoration: const BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.vertical(top: Radius.circular(24)),
        ),
        padding: const EdgeInsets.all(24),
        child: Column(mainAxisSize: MainAxisSize.min, crossAxisAlignment: CrossAxisAlignment.start, children: [
          Row(children: [
            const Text('New Sample Request', style: TextStyle(fontSize: 18, fontWeight: FontWeight.w800)),
            const Spacer(),
            IconButton(icon: const Icon(Icons.close), onPressed: () => Navigator.pop(context)),
          ]),
          const SizedBox(height: 20),

          _Field(label: 'Product / Sample Name', child: TextField(
            controller: _productCtrl,
            decoration: _inputDecor('e.g. Science Textbook Grade 8'),
          )),
          const SizedBox(height: 16),

          Row(children: [
            const Text('Quantity', style: TextStyle(fontWeight: FontWeight.w600, fontSize: 13)),
            const Spacer(),
            IconButton(
              onPressed: () { if (_qty > 1) setState(() => _qty--); },
              icon: const Icon(Icons.remove_circle_outline),
              color: AppColors.primary,
            ),
            Text('$_qty', style: const TextStyle(fontSize: 18, fontWeight: FontWeight.w700)),
            IconButton(
              onPressed: () => setState(() => _qty++),
              icon: const Icon(Icons.add_circle_outline),
              color: AppColors.primary,
            ),
          ]),
          const SizedBox(height: 12),

          _Field(label: 'Notes (optional)', child: TextField(
            controller: _notesCtrl,
            maxLines: 2,
            decoration: _inputDecor('Any additional details…'),
          )),

          if (_error != null) ...[
            const SizedBox(height: 12),
            Text(_error!, style: const TextStyle(color: Colors.red, fontSize: 13)),
          ],

          const SizedBox(height: 24),
          SizedBox(
            width: double.infinity,
            height: 52,
            child: ElevatedButton(
              onPressed: _saving ? null : _submit,
              style: ElevatedButton.styleFrom(
                backgroundColor: AppColors.primary,
                foregroundColor: Colors.white,
                shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(14)),
              ),
              child: _saving
                  ? const SizedBox(width: 20, height: 20, child: CircularProgressIndicator(strokeWidth: 2, color: Colors.white))
                  : const Text('Submit Request', style: TextStyle(fontWeight: FontWeight.w700, fontSize: 16)),
            ),
          ),
          const SizedBox(height: 8),
        ]),
      ),
    );
  }
}

// ── Delivery Flow Screen ───────────────────────────────────────────────────────
class DeliverSampleScreen extends ConsumerStatefulWidget {
  final SampleRequest sample;
  final VoidCallback onDone;
  const DeliverSampleScreen({super.key, required this.sample, required this.onDone});

  @override
  ConsumerState<DeliverSampleScreen> createState() => _DeliverSampleScreenState();
}

class _DeliverSampleScreenState extends ConsumerState<DeliverSampleScreen> {
  // Step 0: select customer / 1: details / 2: signature / 3: done
  int _step = 0;
  Map<String, dynamic>? _selectedCustomer;
  final _notesCtrl = TextEditingController();
  final _searchCtrl = TextEditingController();
  int _qty = 1;
  final _sigController = SignatureController(
    penStrokeWidth: 3,
    penColor: Colors.black,
    exportBackgroundColor: Colors.white,
  );
  bool _saving = false;
  String? _error;

  @override
  void initState() {
    super.initState();
    _qty = widget.sample.quantity;
    _searchCtrl.addListener(() => setState(() {}));
  }

  @override
  void dispose() {
    _notesCtrl.dispose();
    _searchCtrl.dispose();
    _sigController.dispose();
    super.dispose();
  }

  Future<void> _submit() async {
    if (_sigController.isEmpty) {
      setState(() => _error = 'Please get the customer signature before submitting.');
      return;
    }
    setState(() { _saving = true; _error = null; });
    try {
      // Export signature as PNG bytes
      final sigData = await _sigController.toPngBytes();
      final sigBase64 = sigData != null ? base64Encode(sigData) : null;

      // Generate PDF
      final pdfBytes = await _generatePdf(sigData);
      final tmp = await getTemporaryDirectory();
      final pdfFile = File('${tmp.path}/sample_delivery_${widget.sample.id}.pdf');
      await pdfFile.writeAsBytes(pdfBytes);

      // Upload delivery proof
      final dio = ref.read(dioClientProvider);
      await dio.patch('/samples/${widget.sample.id}', data: {
        'customerName': _selectedCustomer?['name'],
        'customerId': _selectedCustomer?['id'],
        'signatureBase64': sigBase64,
        'notes': _notesCtrl.text.trim().isEmpty ? null : _notesCtrl.text.trim(),
        'quantity': _qty,
      });

      // Share PDF
      await Share.shareXFiles(
        [XFile(pdfFile.path, mimeType: 'application/pdf')],
        text: 'Sample Delivery Proof – ${widget.sample.productName}',
      );

      widget.onDone();
      if (mounted) setState(() => _step = 3);
    } catch (e) {
      setState(() => _error = e.toString());
    } finally {
      if (mounted) setState(() => _saving = false);
    }
  }

  Future<Uint8List> _generatePdf(Uint8List? sigBytes) async {
    final doc = pw.Document();
    pw.MemoryImage? sigImage;
    if (sigBytes != null) sigImage = pw.MemoryImage(sigBytes);

    doc.addPage(pw.Page(
      pageFormat: PdfPageFormat.a4,
      build: (ctx) => pw.Column(
        crossAxisAlignment: pw.CrossAxisAlignment.start,
        children: [
          pw.Row(mainAxisAlignment: pw.MainAxisAlignment.spaceBetween, children: [
            pw.Text('BOOKMARK', style: pw.TextStyle(fontSize: 28, fontWeight: pw.FontWeight.bold, color: PdfColors.red800)),
            pw.Text('Sample Delivery Proof', style: pw.TextStyle(fontSize: 12, color: PdfColors.grey600)),
          ]),
          pw.Divider(color: PdfColors.red800, thickness: 2),
          pw.SizedBox(height: 16),
          _pdfRow('Product', widget.sample.productName),
          _pdfRow('Quantity Delivered', '${_qty} unit(s)'),
          _pdfRow('Customer', _selectedCustomer?['name'] ?? 'N/A'),
          _pdfRow('Delivery Date', DateFormat('dd MMM yyyy, hh:mm a').format(DateTime.now())),
          if (_notesCtrl.text.trim().isNotEmpty) _pdfRow('Notes', _notesCtrl.text.trim()),
          pw.SizedBox(height: 32),
          pw.Text('Customer Signature', style: pw.TextStyle(fontWeight: pw.FontWeight.bold, fontSize: 14)),
          pw.SizedBox(height: 8),
          if (sigImage != null)
            pw.Container(
              width: 200, height: 100,
              decoration: pw.BoxDecoration(border: pw.Border.all(color: PdfColors.grey300)),
              child: pw.Image(sigImage, fit: pw.BoxFit.contain),
            )
          else
            pw.Container(width: 200, height: 60, color: PdfColors.grey100,
              child: pw.Center(child: pw.Text('No signature captured'))),
          pw.SizedBox(height: 32),
          pw.Text('This document is auto-generated by Bookmark SFA.',
              style: pw.TextStyle(fontSize: 9, color: PdfColors.grey500)),
        ],
      ),
    ));
    return doc.save();
  }

  pw.Widget _pdfRow(String label, String value) => pw.Padding(
    padding: const pw.EdgeInsets.symmetric(vertical: 5),
    child: pw.Row(crossAxisAlignment: pw.CrossAxisAlignment.start, children: [
      pw.SizedBox(width: 160, child: pw.Text(label, style: pw.TextStyle(fontWeight: pw.FontWeight.bold, fontSize: 11))),
      pw.Expanded(child: pw.Text(value, style: const pw.TextStyle(fontSize: 11))),
    ]),
  );

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: AppColors.background,
      appBar: AppBar(
        title: Text('Deliver: ${widget.sample.productName}'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios_new_rounded),
          onPressed: () {
            if (_step == 0) Navigator.pop(context);
            else setState(() => _step--);
          },
        ),
      ),
      body: [
        _buildCustomerStep(),
        _buildDetailsStep(),
        _buildSignatureStep(),
        _buildDoneStep(),
      ][_step],
    );
  }

  // ── Step 0: Select Customer ────────────────────────────────────────────────
  Widget _buildCustomerStep() {
    final asyncCustomers = ref.watch(customersSearchProvider(_searchCtrl.text.trim()));

    return Column(children: [
      // Progress
      _StepProgress(step: 0),
      Padding(
        padding: const EdgeInsets.all(16),
        child: Column(children: [
          TextField(
            controller: _searchCtrl,
            decoration: _inputDecor('Search customers by name…').copyWith(
              prefixIcon: const Icon(Icons.search_rounded),
            ),
          ),
        ]),
      ),
      Expanded(
        child: asyncCustomers.when(
          loading: () => const Center(child: CircularProgressIndicator()),
          error: (e, _) => Center(child: Text(e.toString())),
          data: (customers) {
            if (customers.isEmpty && _searchCtrl.text.isEmpty) {
              return Center(
                child: Text('Type to search customers', style: TextStyle(color: Colors.grey.shade500)),
              );
            }
            if (customers.isEmpty) {
              return Center(child: Text('No customers found', style: TextStyle(color: Colors.grey.shade500)));
            }
            return ListView.builder(
              padding: const EdgeInsets.symmetric(horizontal: 16),
              itemCount: customers.length,
              itemBuilder: (_, i) {
                final c = customers[i];
                final isSelected = _selectedCustomer?['id'] == c['id'];
                return GestureDetector(
                  onTap: () => setState(() { _selectedCustomer = c; }),
                  child: Container(
                    margin: const EdgeInsets.only(bottom: 8),
                    padding: const EdgeInsets.all(14),
                    decoration: BoxDecoration(
                      color: isSelected ? AppColors.primary.withOpacity(0.1) : Colors.white,
                      borderRadius: BorderRadius.circular(AppRadius.md),
                      border: Border.all(color: isSelected ? AppColors.primary : Colors.grey.shade100),
                    ),
                    child: Row(children: [
                      Container(
                        width: 36, height: 36,
                        decoration: BoxDecoration(
                          color: AppColors.primary.withOpacity(0.1),
                          borderRadius: BorderRadius.circular(10),
                        ),
                        child: Center(
                          child: Text((c['name'] ?? 'C')[0].toUpperCase(),
                              style: TextStyle(fontWeight: FontWeight.w800, color: AppColors.primary)),
                        ),
                      ),
                      const SizedBox(width: 12),
                      Expanded(
                        child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
                          Text(c['name'] ?? '', style: const TextStyle(fontWeight: FontWeight.w600, fontSize: 14)),
                          if (c['city'] != null)
                            Text(c['city']['name'] ?? '', style: TextStyle(fontSize: 12, color: Colors.grey.shade500)),
                        ]),
                      ),
                      if (isSelected) Icon(Icons.check_circle_rounded, color: AppColors.primary),
                    ]),
                  ),
                );
              },
            );
          },
        ),
      ),
      Padding(
        padding: const EdgeInsets.all(16),
        child: SizedBox(
          width: double.infinity, height: 52,
          child: ElevatedButton(
            onPressed: _selectedCustomer == null ? null : () => setState(() => _step = 1),
            style: ElevatedButton.styleFrom(
              backgroundColor: AppColors.primary,
              foregroundColor: Colors.white,
              disabledBackgroundColor: Colors.grey.shade200,
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(14)),
            ),
            child: Text(
              _selectedCustomer == null ? 'Select a Customer to Continue' : 'Continue →',
              style: const TextStyle(fontWeight: FontWeight.w700, fontSize: 16),
            ),
          ),
        ),
      ),
    ]);
  }

  // ── Step 1: Notes + Quantity ───────────────────────────────────────────────
  Widget _buildDetailsStep() {
    return SingleChildScrollView(
      padding: const EdgeInsets.all(16),
      child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
        _StepProgress(step: 1),
        const SizedBox(height: 20),

        Container(
          padding: const EdgeInsets.all(16),
          decoration: BoxDecoration(
            color: Colors.white,
            borderRadius: BorderRadius.circular(AppRadius.md),
            border: Border.all(color: Colors.grey.shade100),
          ),
          child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
            const Text('Delivery Details', style: TextStyle(fontWeight: FontWeight.w800, fontSize: 16)),
            const SizedBox(height: 16),
            Row(children: [
              const Text('Quantity', style: TextStyle(fontWeight: FontWeight.w600)),
              const Spacer(),
              IconButton(onPressed: () { if (_qty > 1) setState(() => _qty--); }, icon: const Icon(Icons.remove_circle_outline), color: AppColors.primary),
              Text('$_qty', style: const TextStyle(fontSize: 20, fontWeight: FontWeight.w800)),
              IconButton(onPressed: () => setState(() => _qty++), icon: const Icon(Icons.add_circle_outline), color: AppColors.primary),
            ]),
            const Divider(height: 24),
            const Text('Notes', style: TextStyle(fontWeight: FontWeight.w600, fontSize: 13)),
            const SizedBox(height: 8),
            TextField(
              controller: _notesCtrl,
              maxLines: 3,
              decoration: _inputDecor('Any delivery notes or remarks…'),
            ),
          ]),
        ),

        const SizedBox(height: 24),
        SizedBox(
          width: double.infinity, height: 52,
          child: ElevatedButton(
            onPressed: () => setState(() => _step = 2),
            style: ElevatedButton.styleFrom(
              backgroundColor: AppColors.primary, foregroundColor: Colors.white,
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(14)),
            ),
            child: const Text('Continue to Signature →', style: TextStyle(fontWeight: FontWeight.w700, fontSize: 16)),
          ),
        ),
      ]),
    );
  }

  // ── Step 2: Signature ──────────────────────────────────────────────────────
  Widget _buildSignatureStep() {
    return Column(children: [
      _StepProgress(step: 2),
      const SizedBox(height: 16),
      Padding(
        padding: const EdgeInsets.symmetric(horizontal: 16),
        child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
          const Text('Customer Signature', style: TextStyle(fontWeight: FontWeight.w800, fontSize: 18)),
          const SizedBox(height: 4),
          Text('${_selectedCustomer?['name'] ?? 'Customer'} — please sign below',
              style: TextStyle(fontSize: 13, color: Colors.grey.shade500)),
        ]),
      ),
      const SizedBox(height: 12),
      Expanded(
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: Stack(children: [
            ClipRRect(
              borderRadius: BorderRadius.circular(AppRadius.lg),
              child: Signature(
                controller: _sigController,
                backgroundColor: Colors.white,
              ),
            ),
            Positioned(
              top: 12, right: 12,
              child: GestureDetector(
                onTap: () => _sigController.clear(),
                child: Container(
                  padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 6),
                  decoration: BoxDecoration(
                    color: Colors.black.withOpacity(0.1),
                    borderRadius: BorderRadius.circular(20),
                  ),
                  child: const Text('Clear', style: TextStyle(fontSize: 12, color: Colors.black54)),
                ),
              ),
            ),
            Positioned(
              bottom: 12, left: 0, right: 0,
              child: Center(
                child: Text('Sign here', style: TextStyle(fontSize: 12, color: Colors.grey.shade300)),
              ),
            ),
          ]),
        ),
      ),
      if (_error != null)
        Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
          child: Text(_error!, style: const TextStyle(color: Colors.red, fontSize: 13)),
        ),
      Padding(
        padding: const EdgeInsets.all(16),
        child: SizedBox(
          width: double.infinity, height: 52,
          child: ElevatedButton.icon(
            onPressed: _saving ? null : _submit,
            icon: _saving
                ? const SizedBox(width: 18, height: 18, child: CircularProgressIndicator(strokeWidth: 2, color: Colors.white))
                : const Icon(Icons.picture_as_pdf_rounded),
            label: Text(_saving ? 'Generating PDF…' : 'Submit & Share PDF',
                style: const TextStyle(fontWeight: FontWeight.w700, fontSize: 16)),
            style: ElevatedButton.styleFrom(
              backgroundColor: AppColors.primary, foregroundColor: Colors.white,
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(14)),
            ),
          ),
        ),
      ),
    ]);
  }

  // ── Step 3: Done ───────────────────────────────────────────────────────────
  Widget _buildDoneStep() {
    return Center(
      child: Padding(
        padding: const EdgeInsets.all(32),
        child: Column(mainAxisSize: MainAxisSize.min, children: [
          Container(
            width: 80, height: 80,
            decoration: BoxDecoration(color: Colors.green.withOpacity(0.1), shape: BoxShape.circle),
            child: const Icon(Icons.check_circle_rounded, size: 48, color: Colors.green),
          ),
          const SizedBox(height: 24),
          const Text('Delivery Completed!', style: TextStyle(fontSize: 22, fontWeight: FontWeight.w800)),
          const SizedBox(height: 8),
          Text('PDF has been generated and shared. The admin has been notified.',
              textAlign: TextAlign.center, style: TextStyle(fontSize: 14, color: Colors.grey.shade500)),
          const SizedBox(height: 32),
          SizedBox(
            width: double.infinity, height: 52,
            child: ElevatedButton(
              onPressed: () => Navigator.pop(context),
              style: ElevatedButton.styleFrom(
                backgroundColor: AppColors.primary, foregroundColor: Colors.white,
                shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(14)),
              ),
              child: const Text('Back to Samples', style: TextStyle(fontWeight: FontWeight.w700, fontSize: 16)),
            ),
          ),
        ]),
      ),
    );
  }
}

// ── Step Progress Indicator ───────────────────────────────────────────────────
class _StepProgress extends StatelessWidget {
  final int step;
  const _StepProgress({required this.step});

  @override
  Widget build(BuildContext context) {
    final labels = ['Customer', 'Details', 'Signature'];
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 12),
      child: Row(children: List.generate(labels.length * 2 - 1, (i) {
        if (i % 2 == 1) {
          return Expanded(child: Container(height: 2, color: i ~/ 2 < step ? AppColors.primary : Colors.grey.shade200));
        }
        final idx = i ~/ 2;
        final done = idx < step;
        final active = idx == step;
        return Column(mainAxisSize: MainAxisSize.min, children: [
          Container(
            width: 28, height: 28,
            decoration: BoxDecoration(
              color: done || active ? AppColors.primary : Colors.grey.shade200,
              shape: BoxShape.circle,
            ),
            child: Center(
              child: done
                  ? const Icon(Icons.check, size: 14, color: Colors.white)
                  : Text('${idx + 1}', style: TextStyle(fontSize: 12, fontWeight: FontWeight.w700,
                      color: active ? Colors.white : Colors.grey)),
            ),
          ),
          const SizedBox(height: 4),
          Text(labels[idx], style: TextStyle(fontSize: 10, fontWeight: active ? FontWeight.w700 : FontWeight.w500,
              color: active ? AppColors.primary : Colors.grey.shade500)),
        ]);
      })),
    );
  }
}

// ── Shared Helpers ─────────────────────────────────────────────────────────────
class _Field extends StatelessWidget {
  final String label;
  final Widget child;
  const _Field({required this.label, required this.child});

  @override
  Widget build(BuildContext context) => Column(
    crossAxisAlignment: CrossAxisAlignment.start,
    children: [
      Text(label, style: const TextStyle(fontWeight: FontWeight.w600, fontSize: 13)),
      const SizedBox(height: 6),
      child,
    ],
  );
}

InputDecoration _inputDecor(String hint) => InputDecoration(
  hintText: hint,
  hintStyle: const TextStyle(fontSize: 13, color: Color(0xFFB0B0B0)),
  filled: true, fillColor: const Color(0xFFF5F6F8),
  contentPadding: const EdgeInsets.symmetric(horizontal: 14, vertical: 12),
  border: OutlineInputBorder(borderRadius: BorderRadius.circular(12), borderSide: BorderSide.none),
  focusedBorder: OutlineInputBorder(
    borderRadius: BorderRadius.circular(12),
    borderSide: const BorderSide(color: Color(0xFFC8102E), width: 1.5),
  ),
);
