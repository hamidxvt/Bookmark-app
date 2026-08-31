import 'dart:convert';
import 'dart:typed_data';

import 'package:flutter/material.dart';
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

// ── Models ────────────────────────────────────────────────────────────────────
class SampleBudget {
  final double total, used, remaining;
  const SampleBudget({required this.total, required this.used, required this.remaining});

  factory SampleBudget.fromJson(Map<String, dynamic> j) => SampleBudget(
        total: (j['total'] as num?)?.toDouble() ?? 300000,
        used: (j['used'] as num?)?.toDouble() ?? 0,
        remaining: (j['remaining'] as num?)?.toDouble() ?? 300000,
      );
}

class SampleRequest {
  final int id;
  final String productName, status;
  final int quantity;
  final double? price, totalCost;
  final String? notes, customerName, adminNotes;
  final DateTime createdAt;
  final Map<String, dynamic>? customer;

  const SampleRequest({
    required this.id,
    required this.productName,
    required this.status,
    required this.quantity,
    this.price,
    this.totalCost,
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
        price: (j['price'] as num?)?.toDouble(),
        totalCost: (j['totalCost'] as num?)?.toDouble(),
        notes: j['notes'] as String?,
        customerName: (j['customer'] as Map?)?['name'] as String? ?? j['customerName'] as String?,
        adminNotes: j['adminNotes'] as String?,
        createdAt: j['createdAt'] != null
            ? DateTime.tryParse(j['createdAt']) ?? DateTime.now()
            : DateTime.now(),
        customer: j['customer'] as Map<String, dynamic>?,
      );
}

class _SampleData {
  final SampleBudget budget;
  final List<SampleRequest> samples;
  const _SampleData({required this.budget, required this.samples});
}

// ── Providers ─────────────────────────────────────────────────────────────────
final sampleDataProvider = FutureProvider.autoDispose<_SampleData>((ref) async {
  final dio = ref.watch(dioClientProvider);
  final res = await dio.get('/samples');
  if (res.statusCode != 200) throw Exception('Server error: ${res.statusCode}');

  final raw = res.data;
  SampleBudget budget = const SampleBudget(total: 300000, used: 0, remaining: 300000);
  List<dynamic> list = [];

  if (raw is Map) {
    final data = raw['data'];
    if (data is Map) {
      if (data['budget'] is Map) {
        budget = SampleBudget.fromJson(data['budget'] as Map<String, dynamic>);
      }
      if (data['samples'] is List) list = data['samples'] as List;
    } else if (data is List) {
      list = data;
    }
    if (raw['success'] == false) throw Exception(raw['error'] ?? 'API error');
  } else if (raw is List) {
    list = raw;
  }

  return _SampleData(
    budget: budget,
    samples: list.cast<Map<String, dynamic>>().map(SampleRequest.fromJson).toList(),
  );
});

final customersSearchProvider = FutureProvider.family.autoDispose<List<Map<String, dynamic>>, String>(
  (ref, query) async {
    final dio = ref.watch(dioClientProvider);
    try {
      // If query is empty, fetch all customers
      final params = query.isEmpty ? {'length': 50} : {'search': query, 'length': 50};
      final res = await dio.get('/customers', params: params);
      final raw = res.data;
      
      List<dynamic> list = [];
      if (raw is Map) {
        final data = raw['data'];
        if (data is Map && data['data'] is List) {
          list = (data['data'] as List);
        } else if (data is List) {
          list = data;
        } else if (raw['success'] == false) {
          throw Exception(raw['error'] ?? 'Failed to load customers');
        }
      } else if (raw is List) {
        list = raw;
      }
      
      return list.cast<Map<String, dynamic>>();
    } catch (e) {
      // Return empty list on error so UI shows "No customers found"
      return [];
    }
  },
);

// ── Screen ────────────────────────────────────────────────────────────────────
class SamplesScreen extends ConsumerStatefulWidget {
  const SamplesScreen({super.key});
  @override
  ConsumerState<SamplesScreen> createState() => _SamplesScreenState();
}

class _SamplesScreenState extends ConsumerState<SamplesScreen> {
  final _fmt = NumberFormat('#,##0', 'en_US');

  @override
  Widget build(BuildContext context) {
    final async = ref.watch(sampleDataProvider);
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
            onPressed: () => ref.invalidate(sampleDataProvider),
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
        error: (e, _) => _ErrorView(message: e.toString(), onRetry: () => ref.invalidate(sampleDataProvider)),
        data: (data) => _buildBody(data),
      ),
    );
  }

  Widget _buildBody(_SampleData data) {
    final pending   = data.samples.where((s) => s.status == 'pending').toList();
    final approved  = data.samples.where((s) => s.status == 'approved').toList();
    final delivered = data.samples.where((s) => s.status == 'delivered').toList();
    final rejected  = data.samples.where((s) => s.status == 'rejected').toList();

    return ListView(
      padding: const EdgeInsets.fromLTRB(16, 16, 16, 100),
      children: [
        // ── Budget Dashboard ────────────────────────────────────────────────
        _BudgetCard(budget: data.budget, fmt: _fmt)
            .animate().fadeIn().slideY(begin: -0.04),
        const SizedBox(height: 20),

        if (data.samples.isEmpty)
          Center(
            child: Padding(
              padding: const EdgeInsets.only(top: 48),
              child: Column(mainAxisSize: MainAxisSize.min, children: [
                Icon(Icons.inventory_2_outlined, size: 64, color: Colors.grey.shade300),
                const SizedBox(height: 16),
                Text('No sample requests yet',
                    style: TextStyle(color: Colors.grey.shade500, fontSize: 16)),
                const SizedBox(height: 8),
                Text('Tap + to request a new sample',
                    style: TextStyle(color: Colors.grey.shade400, fontSize: 13)),
              ]),
            ),
          ),

        if (approved.isNotEmpty) ...[
          _SectionHeader('Approved — Ready to Deliver', Icons.check_circle_outline, Colors.green),
          const SizedBox(height: 8),
          ...approved.map((s) => _SampleCard(
            sample: s,
            fmt: _fmt,
            onTap: () => _openDeliveryFlow(context, s),
          ).animate().fadeIn().slideY(begin: 0.05)),
          const SizedBox(height: 24),
        ],
        if (pending.isNotEmpty) ...[
          _SectionHeader('Pending Review', Icons.hourglass_top_rounded, Colors.amber.shade700),
          const SizedBox(height: 8),
          ...pending.map((s) => _SampleCard(sample: s, fmt: _fmt)),
          const SizedBox(height: 24),
        ],
        if (delivered.isNotEmpty) ...[
          _SectionHeader('Delivered', Icons.local_shipping_outlined, AppColors.primary),
          const SizedBox(height: 8),
          ...delivered.map((s) => _SampleCard(sample: s, fmt: _fmt)),
          const SizedBox(height: 24),
        ],
        if (rejected.isNotEmpty) ...[
          _SectionHeader('Rejected', Icons.cancel_outlined, Colors.red.shade400),
          const SizedBox(height: 8),
          ...rejected.map((s) => _SampleCard(sample: s, fmt: _fmt)),
        ],
      ],
    );
  }

  void _showNewRequestSheet(BuildContext context) {
    showModalBottomSheet(
      context: context,
      isScrollControlled: true,
      backgroundColor: Colors.transparent,
      builder: (_) => _NewRequestSheet(
        onSubmitted: () { if (mounted) ref.invalidate(sampleDataProvider); },
      ),
    );
  }

  void _openDeliveryFlow(BuildContext context, SampleRequest sample) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (_) => DeliverSampleScreen(
          sample: sample,
          onDone: () { if (mounted) ref.invalidate(sampleDataProvider); },
        ),
      ),
    );
  }
}

// ── Budget Card ───────────────────────────────────────────────────────────────
class _BudgetCard extends StatelessWidget {
  final SampleBudget budget;
  final NumberFormat fmt;
  const _BudgetCard({required this.budget, required this.fmt});

  @override
  Widget build(BuildContext context) {
    final pctUsed = budget.total > 0 ? (budget.used / budget.total).clamp(0.0, 1.0) : 0.0;
    final barColor = pctUsed > 0.8 ? Colors.red.shade600 : pctUsed > 0.5 ? Colors.amber.shade700 : Colors.green.shade600;

    return Container(
      padding: const EdgeInsets.all(20),
      decoration: BoxDecoration(
        gradient: const LinearGradient(
          colors: [Color(0xFFC8102E), Color(0xFF8B0000)],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
        borderRadius: BorderRadius.circular(AppRadius.lg),
        boxShadow: [BoxShadow(color: AppColors.primary.withOpacity(0.25), blurRadius: 16, offset: const Offset(0, 6))],
      ),
      child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
        Row(children: [
          const Icon(Icons.account_balance_wallet_outlined, color: Colors.white70, size: 18),
          const SizedBox(width: 8),
          const Text('Sample Budget', style: TextStyle(color: Colors.white70, fontSize: 13, fontWeight: FontWeight.w600)),
          const Spacer(),
          Text('PKR ${fmt.format(budget.total.toInt())}',
              style: const TextStyle(color: Colors.white, fontSize: 13, fontWeight: FontWeight.w700)),
        ]),
        const SizedBox(height: 16),
        Row(children: [
          _BudgetStat('Used', fmt.format(budget.used.toInt()), Colors.white),
          const SizedBox(width: 24),
          _BudgetStat('Remaining', fmt.format(budget.remaining.toInt()), Colors.greenAccent.shade100),
        ]),
        const SizedBox(height: 16),
        // Progress bar
        Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(8),
            child: LinearProgressIndicator(
              value: pctUsed,
              backgroundColor: Colors.white24,
              valueColor: AlwaysStoppedAnimation<Color>(barColor),
              minHeight: 8,
            ),
          ),
          const SizedBox(height: 6),
          Text('${(pctUsed * 100).toStringAsFixed(1)}% of budget used',
              style: const TextStyle(color: Colors.white60, fontSize: 11)),
        ]),
      ]),
    );
  }
}

class _BudgetStat extends StatelessWidget {
  final String label, value;
  final Color valueColor;
  const _BudgetStat(this.label, this.value, this.valueColor);

  @override
  Widget build(BuildContext context) => Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
    Text(label, style: const TextStyle(color: Colors.white54, fontSize: 11)),
    const SizedBox(height: 2),
    Text('PKR $value', style: TextStyle(color: valueColor, fontSize: 16, fontWeight: FontWeight.w800)),
  ]);
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
      Padding(
        padding: const EdgeInsets.symmetric(horizontal: 32),
        child: Text(message, textAlign: TextAlign.center, style: TextStyle(color: Colors.grey.shade600)),
      ),
      const SizedBox(height: 16),
      ElevatedButton.icon(
        icon: const Icon(Icons.refresh),
        label: const Text('Retry'),
        onPressed: onRetry,
        style: ElevatedButton.styleFrom(backgroundColor: AppColors.primary, foregroundColor: Colors.white),
      ),
    ]),
  );
}

class _SampleCard extends StatelessWidget {
  final SampleRequest sample;
  final NumberFormat fmt;
  final VoidCallback? onTap;
  const _SampleCard({required this.sample, required this.fmt, this.onTap});

  @override
  Widget build(BuildContext context) {
    final statusColor = _statusColor(sample.status);
    final isApproved = sample.status == 'approved';
    final hasCost = sample.totalCost != null && sample.totalCost! > 0;

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
        child: Row(crossAxisAlignment: CrossAxisAlignment.start, children: [
          Container(
            width: 44, height: 44,
            decoration: BoxDecoration(color: statusColor.withOpacity(0.1), borderRadius: BorderRadius.circular(12)),
            child: Icon(Icons.inventory_2_rounded, color: statusColor, size: 22),
          ),
          const SizedBox(width: 12),
          Expanded(
            child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
              Text(sample.productName, style: const TextStyle(fontWeight: FontWeight.w700, fontSize: 14)),
              const SizedBox(height: 3),
              Text('Qty: ${sample.quantity}  ·  ${DateFormat('dd MMM yyyy').format(sample.createdAt)}',
                  style: TextStyle(fontSize: 12, color: Colors.grey.shade500)),
              if (hasCost) ...[
                const SizedBox(height: 3),
                Text('Cost: PKR ${fmt.format(sample.totalCost!.toInt())}',
                    style: TextStyle(fontSize: 12, color: Colors.grey.shade600, fontWeight: FontWeight.w600)),
              ],
              if (sample.adminNotes != null && sample.adminNotes!.isNotEmpty) ...[
                const SizedBox(height: 4),
                Text('Admin: ${sample.adminNotes}',
                    style: TextStyle(fontSize: 11, color: Colors.grey.shade400, fontStyle: FontStyle.italic)),
              ],
            ]),
          ),
          const SizedBox(width: 8),
          Column(children: [
            Container(
              padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 4),
              decoration: BoxDecoration(color: statusColor.withOpacity(0.1), borderRadius: BorderRadius.circular(20)),
              child: Text(
                sample.status[0].toUpperCase() + sample.status.substring(1),
                style: TextStyle(fontSize: 11, fontWeight: FontWeight.w700, color: statusColor),
              ),
            ),
            if (isApproved) ...[
              const SizedBox(height: 6),
              Container(
                padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 3),
                decoration: BoxDecoration(color: AppColors.primary, borderRadius: BorderRadius.circular(20)),
                child: const Text('Deliver', style: TextStyle(fontSize: 10, color: Colors.white, fontWeight: FontWeight.w700)),
              ),
            ],
          ]),
        ]),
      ),
    );
  }

  Color _statusColor(String s) => switch (s.toLowerCase()) {
    'approved'  => Colors.green,
    'rejected'  => Colors.red,
    'delivered' => AppColors.primary,
    _           => Colors.amber.shade700,
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
  final _notesCtrl   = TextEditingController();
  final _priceCtrl   = TextEditingController();
  int  _qty    = 1;
  bool _saving = false;
  String? _error;

  @override
  void dispose() {
    _productCtrl.dispose();
    _notesCtrl.dispose();
    _priceCtrl.dispose();
    super.dispose();
  }

  double? get _parsedPrice {
    final t = _priceCtrl.text.trim();
    return t.isEmpty ? null : double.tryParse(t);
  }

  double get _estimatedCost {
    final p = _parsedPrice;
    return p != null ? p * _qty : 0;
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
        'price': _parsedPrice,
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
    final fmt = NumberFormat('#,##0', 'en_US');
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
              icon: const Icon(Icons.remove_circle_outline), color: AppColors.primary,
            ),
            Text('$_qty', style: const TextStyle(fontSize: 18, fontWeight: FontWeight.w700)),
            IconButton(
              onPressed: () => setState(() => _qty++),
              icon: const Icon(Icons.add_circle_outline), color: AppColors.primary,
            ),
          ]),
          const SizedBox(height: 12),

          _Field(label: 'Price per unit (PKR)', child: TextField(
            controller: _priceCtrl,
            keyboardType: const TextInputType.numberWithOptions(decimal: true),
            onChanged: (_) => setState(() {}),
            decoration: _inputDecor('e.g. 500'),
          )),

          if (_parsedPrice != null && _estimatedCost > 0) ...[
            const SizedBox(height: 8),
            Container(
              padding: const EdgeInsets.symmetric(horizontal: 14, vertical: 10),
              decoration: BoxDecoration(
                color: AppColors.primary.withOpacity(0.06),
                borderRadius: BorderRadius.circular(10),
              ),
              child: Row(children: [
                const Icon(Icons.calculate_outlined, size: 16, color: AppColors.primary),
                const SizedBox(width: 8),
                Text('Estimated cost: PKR ${fmt.format(_estimatedCost.toInt())}',
                    style: const TextStyle(fontSize: 13, fontWeight: FontWeight.w700, color: AppColors.primary)),
              ]),
            ),
          ],

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
            width: double.infinity, height: 52,
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
  int _step = 0;
  Map<String, dynamic>? _selectedCustomer;
  final _notesCtrl  = TextEditingController();
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
      final sigData   = await _sigController.toPngBytes();
      final sigBase64 = sigData != null ? base64Encode(sigData) : null;
      final pdfBytes  = await _generatePdf(sigData);
      final tmp = await getTemporaryDirectory();
      final pdfFile = File('${tmp.path}/sample_delivery_${widget.sample.id}.pdf');
      await pdfFile.writeAsBytes(pdfBytes);

      final dio = ref.read(dioClientProvider);
      await dio.patch('/samples/${widget.sample.id}', data: {
        'customerName': _selectedCustomer?['name'],
        'customerId': _selectedCustomer?['id'],
        'signatureBase64': sigBase64,
        'notes': _notesCtrl.text.trim().isEmpty ? null : _notesCtrl.text.trim(),
        'quantity': _qty,
      });

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

    final fmt = NumberFormat('#,##0', 'en_US');
    final costStr = widget.sample.price != null
        ? 'PKR ${fmt.format((widget.sample.price! * _qty).toInt())}'
        : 'N/A';

    doc.addPage(pw.Page(
      pageFormat: PdfPageFormat.a4,
      margin: const pw.EdgeInsets.all(40),
      build: (ctx) => pw.Column(
        crossAxisAlignment: pw.CrossAxisAlignment.start,
        children: [
          pw.Row(mainAxisAlignment: pw.MainAxisAlignment.spaceBetween, children: [
            pw.Text('BOOKMARK', style: pw.TextStyle(fontSize: 30, fontWeight: pw.FontWeight.bold, color: PdfColors.red800)),
            pw.Column(crossAxisAlignment: pw.CrossAxisAlignment.end, children: [
              pw.Text('Sample Delivery Proof', style: pw.TextStyle(fontSize: 13, fontWeight: pw.FontWeight.bold)),
              pw.Text('Ref #${widget.sample.id}', style: const pw.TextStyle(fontSize: 10, color: PdfColors.grey600)),
            ]),
          ]),
          pw.Divider(color: PdfColors.red800, thickness: 2),
          pw.SizedBox(height: 20),
          pw.Container(
            padding: const pw.EdgeInsets.all(14),
            decoration: pw.BoxDecoration(
              color: PdfColors.grey100,
              borderRadius: pw.BorderRadius.circular(6),
            ),
            child: pw.Column(children: [
              _pdfRow('Product', widget.sample.productName),
              _pdfRow('Quantity Delivered', '$_qty unit(s)'),
              _pdfRow('Price per Unit', widget.sample.price != null ? 'PKR ${fmt.format(widget.sample.price!.toInt())}' : 'N/A'),
              _pdfRow('Total Cost', costStr),
              _pdfRow('Customer', _selectedCustomer?['name'] ?? 'N/A'),
              _pdfRow('Delivery Date', DateFormat('dd MMM yyyy, hh:mm a').format(DateTime.now())),
              if (_notesCtrl.text.trim().isNotEmpty) _pdfRow('Notes', _notesCtrl.text.trim()),
            ]),
          ),
          pw.SizedBox(height: 32),
          pw.Text('Customer Signature', style: pw.TextStyle(fontWeight: pw.FontWeight.bold, fontSize: 14)),
          pw.SizedBox(height: 8),
          pw.Container(
            width: 220, height: 110,
            decoration: pw.BoxDecoration(
              border: pw.Border.all(color: PdfColors.grey400),
              borderRadius: pw.BorderRadius.circular(6),
            ),
            child: sigImage != null
                ? pw.Image(sigImage, fit: pw.BoxFit.contain)
                : pw.Center(child: pw.Text('No signature captured', style: const pw.TextStyle(color: PdfColors.grey400))),
          ),
          pw.SizedBox(height: 24),
          pw.Divider(color: PdfColors.grey300),
          pw.SizedBox(height: 6),
          pw.Text('This document is auto-generated by Bookmark SFA Field Force Manager.',
              style: const pw.TextStyle(fontSize: 9, color: PdfColors.grey500)),
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

  Widget _buildCustomerStep() {
    final asyncCustomers = ref.watch(customersSearchProvider(_searchCtrl.text.trim()));
    return Column(children: [
      _StepProgress(step: 0),
      Padding(
        padding: const EdgeInsets.all(16),
        child: TextField(
          controller: _searchCtrl,
          decoration: _inputDecor('Search customers by name…').copyWith(
            prefixIcon: const Icon(Icons.search_rounded),
          ),
        ),
      ),
      Expanded(
        child: asyncCustomers.when(
          loading: () => const Center(child: CircularProgressIndicator()),
          error: (e, _) => Center(child: Text(e.toString())),
          data: (customers) {
            if (customers.isEmpty && _searchCtrl.text.isEmpty) {
              return Center(child: Text('Type to search customers', style: TextStyle(color: Colors.grey.shade500)));
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
                        decoration: BoxDecoration(color: AppColors.primary.withOpacity(0.1), borderRadius: BorderRadius.circular(10)),
                        child: Center(child: Text((c['name'] ?? 'C')[0].toUpperCase(),
                            style: TextStyle(fontWeight: FontWeight.w800, color: AppColors.primary))),
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
            TextField(controller: _notesCtrl, maxLines: 3, decoration: _inputDecor('Any delivery notes or remarks…')),
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
              child: Signature(controller: _sigController, backgroundColor: Colors.white),
            ),
            Positioned(
              top: 12, right: 12,
              child: GestureDetector(
                onTap: () => _sigController.clear(),
                child: Container(
                  padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 6),
                  decoration: BoxDecoration(color: Colors.black.withOpacity(0.1), borderRadius: BorderRadius.circular(20)),
                  child: const Text('Clear', style: TextStyle(fontSize: 12, color: Colors.black54)),
                ),
              ),
            ),
            Positioned(
              bottom: 12, left: 0, right: 0,
              child: Center(child: Text('Sign here', style: TextStyle(fontSize: 12, color: Colors.grey.shade300))),
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

// ── Step Progress ─────────────────────────────────────────────────────────────
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
        final idx  = i ~/ 2;
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

// ── Shared Helpers ────────────────────────────────────────────────────────────
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
