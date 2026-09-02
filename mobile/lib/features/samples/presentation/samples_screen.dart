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
  final String? pdfUrl;

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
    this.pdfUrl,
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
        pdfUrl: j['pdfUrl'] as String?,
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

// Products search provider
final productsSearchProvider = FutureProvider.family.autoDispose<List<Map<String, dynamic>>, String>(
  (ref, query) async {
    final dio = ref.watch(dioClientProvider);
    try {
      final res = await dio.get('/products', params: {'q': query, 'limit': '30'});
      final raw = res.data;
      if (raw is Map && raw['success'] == true && raw['data'] is List) {
        return (raw['data'] as List).cast<Map<String, dynamic>>();
      }
      return [];
    } catch (_) {
      return [];
    }
  },
);

final customersSearchProvider = FutureProvider.family.autoDispose<List<Map<String, dynamic>>, String>(
  (ref, query) async {
    final dio = ref.watch(dioClientProvider);
    try {
      // Send search query if not empty, otherwise fetch all
      final params = {'search': query, 'length': 50};
      final res = await dio.get('/customers', params: params);
      final raw = res.data;
      
      // Handle response shape: { success: true, data: [...] }
      if (raw is Map && raw['success'] == true && raw['data'] is List) {
        return (raw['data'] as List).cast<Map<String, dynamic>>();
      }
      
      // Fallback for flat array or nested data
      if (raw is Map && raw['data'] is Map && raw['data']['data'] is List) {
        return (raw['data']['data'] as List).cast<Map<String, dynamic>>();
      }
      
      if (raw is List) {
        return raw.cast<Map<String, dynamic>>();
      }
      
      throw Exception('Invalid response format');
    } catch (e) {
      print('[customersSearchProvider] Error: $e');
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
      body: SafeArea(
        child: Column(
          children: [
            // ── Header ─────────────────────────────────────────────────
            Padding(
              padding: const EdgeInsets.fromLTRB(16, 12, 16, 8),
              child: Row(
                children: [
                  const Expanded(
                    child: Text('Samples',
                        style: TextStyle(fontSize: 22, fontWeight: FontWeight.w800, color: AppColors.onSurface)),
                  ),
                  GestureDetector(
                    onTap: () => ref.invalidate(sampleDataProvider),
                    child: Container(
                      width: 40, height: 40,
                      decoration: BoxDecoration(
                        color: AppColors.card,
                        borderRadius: BorderRadius.circular(14),
                        border: Border.all(color: AppColors.outline),
                      ),
                      child: const Icon(Icons.refresh_rounded, size: 18, color: AppColors.primary),
                    ),
                  ),
                ],
              ),
            ),

            Expanded(
              child: async.when(
                loading: () => const Center(child: CircularProgressIndicator(color: AppColors.primary)),
                error: (e, _) => _ErrorView(message: e.toString(), onRetry: () => ref.invalidate(sampleDataProvider)),
                data: (data) => _buildBody(data),
              ),
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton.extended(
        onPressed: () => _showNewRequestSheet(context),
        icon: const Icon(Icons.add_rounded),
        label: const Text('Request Sample', style: TextStyle(fontWeight: FontWeight.w800)),
        backgroundColor: AppColors.primary,
        foregroundColor: Colors.white,
        elevation: 4,
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
        const SizedBox(height: 14),

        // ── Status Stats Grid ────────────────────────────────────────────────
        Row(children: [
          _StatPill(icon: Icons.check_rounded, label: 'Approved',
              value: approved.length, color: AppColors.primary),
          const SizedBox(width: 8),
          _StatPill(icon: Icons.hourglass_top_rounded, label: 'Pending',
              value: pending.length, color: AppColors.warning),
          const SizedBox(width: 8),
          _StatPill(icon: Icons.local_shipping_outlined, label: 'Delivered',
              value: delivered.length, color: AppColors.success),
          const SizedBox(width: 8),
          _StatPill(icon: Icons.close_rounded, label: 'Rejected',
              value: rejected.length, color: AppColors.textMuted),
        ]).animate().fadeIn(delay: 100.ms),
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

    return Container(
      padding: const EdgeInsets.all(20),
      decoration: BoxDecoration(
        color: AppColors.card,
        borderRadius: BorderRadius.circular(AppRadius.xxl),
        border: Border.all(color: AppColors.outline),
        boxShadow: [BoxShadow(color: Colors.black.withOpacity(0.04), blurRadius: 12, offset: const Offset(0, 3))],
      ),
      child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
        // Title row
        Row(children: [
          Container(
            width: 36, height: 36,
            decoration: BoxDecoration(
              color: AppColors.primary.withOpacity(0.1),
              borderRadius: BorderRadius.circular(12),
            ),
            child: const Icon(Icons.account_balance_wallet_outlined, color: AppColors.primary, size: 18),
          ),
          const SizedBox(width: 10),
          const Expanded(
            child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
              Text('Sample Budget',
                  style: TextStyle(fontSize: 13, fontWeight: FontWeight.w800, color: AppColors.onSurface)),
              Text('Financial year 2026',
                  style: TextStyle(fontSize: 11.5, fontWeight: FontWeight.w500, color: AppColors.textSecondary)),
            ]),
          ),
          Container(
            padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 4),
            decoration: BoxDecoration(
              color: AppColors.background,
              borderRadius: BorderRadius.circular(AppRadius.full),
            ),
            child: Text('${(pctUsed * 100).toStringAsFixed(1)}% Used',
                style: const TextStyle(fontSize: 11, fontWeight: FontWeight.w700, color: AppColors.onSurface)),
          ),
        ]),

        const SizedBox(height: 16),

        // Remaining amount
        Text('PKR ${fmt.format(budget.remaining.toInt())}',
            style: const TextStyle(fontSize: 30, fontWeight: FontWeight.w800, color: AppColors.onSurface, letterSpacing: -0.5)),
        Text('Remaining of PKR ${fmt.format(budget.total.toInt())} total',
            style: const TextStyle(fontSize: 12, fontWeight: FontWeight.w500, color: AppColors.textSecondary)),

        const SizedBox(height: 14),

        // Progress bar
        ClipRRect(
          borderRadius: BorderRadius.circular(AppRadius.full),
          child: LinearProgressIndicator(
            value: pctUsed,
            backgroundColor: AppColors.outline,
            valueColor: AlwaysStoppedAnimation<Color>(
                pctUsed > 0.8 ? AppColors.missed : AppColors.primary),
            minHeight: 10,
          ),
        ),

        const SizedBox(height: 14),

        // Used / Available
        Row(children: [
          Expanded(child: _BudgetStat('Used', fmt.format(budget.used.toInt()), false)),
          const SizedBox(width: 10),
          Expanded(child: _BudgetStat('Available', fmt.format(budget.remaining.toInt()), true)),
        ]),
      ]),
    );
  }
}

class _BudgetStat extends StatelessWidget {
  final String label, value;
  final bool highlight;
  const _BudgetStat(this.label, this.value, this.highlight);

  @override
  Widget build(BuildContext context) => Container(
    padding: const EdgeInsets.symmetric(horizontal: 14, vertical: 10),
    decoration: BoxDecoration(
      color: highlight ? AppColors.successLight : AppColors.background,
      borderRadius: BorderRadius.circular(AppRadius.lg),
    ),
    child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
      Text(label,
          style: TextStyle(fontSize: 10.5, fontWeight: FontWeight.w700, letterSpacing: 0.5,
              color: highlight ? AppColors.success : AppColors.textMuted)),
      const SizedBox(height: 3),
      Text('PKR $value',
          style: const TextStyle(fontSize: 15, fontWeight: FontWeight.w800, color: AppColors.onSurface)),
    ]),
  );
}

class _StatPill extends StatelessWidget {
  final IconData icon;
  final String label;
  final int value;
  final Color color;
  const _StatPill({required this.icon, required this.label, required this.value, required this.color});

  @override
  Widget build(BuildContext context) => Expanded(
    child: Container(
      padding: const EdgeInsets.symmetric(vertical: 12),
      decoration: BoxDecoration(
        color: AppColors.card,
        borderRadius: BorderRadius.circular(AppRadius.lg),
        border: Border.all(color: AppColors.outline),
        boxShadow: [BoxShadow(color: Colors.black.withOpacity(0.03), blurRadius: 6)],
      ),
      child: Column(children: [
        Container(
          width: 32, height: 32,
          decoration: BoxDecoration(color: color.withOpacity(0.1), borderRadius: BorderRadius.circular(11)),
          child: Icon(icon, size: 15, color: color),
        ),
        const SizedBox(height: 6),
        Text('$value', style: const TextStyle(fontSize: 17, fontWeight: FontWeight.w800, color: AppColors.onSurface, letterSpacing: -0.3)),
        const SizedBox(height: 2),
        Text(label, style: const TextStyle(fontSize: 10, fontWeight: FontWeight.w600, color: AppColors.textSecondary)),
      ]),
    ),
  );
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

Future<void> _viewPdf(String pdfBase64) async {
  try {
    final bytes = base64Decode(pdfBase64.contains(',')
        ? pdfBase64.split(',').last
        : pdfBase64);
    await Printing.layoutPdf(onLayout: (_) async => bytes);
  } catch (e) {
    debugPrint('[PDF view error] $e');
  }
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
          color: AppColors.card,
          borderRadius: BorderRadius.circular(AppRadius.xl),
          border: isApproved
              ? Border.all(color: AppColors.success.withOpacity(0.4), width: 1.5)
              : Border.all(color: AppColors.outline),
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
            if (sample.status == 'delivered' && sample.pdfUrl != null) ...[
              const SizedBox(height: 6),
              GestureDetector(
                onTap: () => _viewPdf(sample.pdfUrl!),
                child: Container(
                  padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 3),
                  decoration: BoxDecoration(
                    color: Colors.blue.shade50,
                    borderRadius: BorderRadius.circular(20),
                    border: Border.all(color: Colors.blue.shade200),
                  ),
                  child: const Text('PDF', style: TextStyle(fontSize: 10, color: Colors.blue, fontWeight: FontWeight.w700)),
                ),
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

// ── New Request Sheet (multi-product) ─────────────────────────────────────────
class _SelectedProduct {
  final int id;
  final String name;
  final double price;
  int qty;
  _SelectedProduct({required this.id, required this.name, required this.price, this.qty = 1});
}

class _NewRequestSheet extends ConsumerStatefulWidget {
  final VoidCallback onSubmitted;
  const _NewRequestSheet({required this.onSubmitted});
  @override
  ConsumerState<_NewRequestSheet> createState() => _NewRequestSheetState();
}

class _NewRequestSheetState extends ConsumerState<_NewRequestSheet> {
  final _searchCtrl = TextEditingController();
  final _notesCtrl  = TextEditingController();
  final List<_SelectedProduct> _selected = [];
  bool _saving = false;
  String? _error;

  @override
  void initState() {
    super.initState();
    _searchCtrl.addListener(() => setState(() {}));
  }

  @override
  void dispose() {
    _searchCtrl.dispose();
    _notesCtrl.dispose();
    super.dispose();
  }

  void _toggleProduct(Map<String, dynamic> product) {
    final id = product['id'] as int;
    final existing = _selected.indexWhere((p) => p.id == id);
    if (existing >= 0) {
      setState(() => _selected.removeAt(existing));
    } else {
      setState(() => _selected.add(_SelectedProduct(
        id: id,
        name: product['name'] as String? ?? 'Product',
        price: (product['price'] as num?)?.toDouble() ?? 0,
      )));
    }
  }

  double get _totalCost => _selected.fold(0, (sum, p) => sum + p.price * p.qty);

  Future<void> _submit() async {
    if (_selected.isEmpty) {
      setState(() => _error = 'Select at least one product');
      return;
    }
    setState(() { _saving = true; _error = null; });
    try {
      final dio = ref.read(dioClientProvider);
      final notes = _notesCtrl.text.trim().isEmpty ? null : _notesCtrl.text.trim();

      // Build a combined product name and itemsJson for one request
      final productNames = _selected.map((p) => '${p.name} (×${p.qty})').join(', ');
      final itemsJson = _selected.map((p) => {'name': p.name, 'qty': p.qty, 'price': p.price}).toList();

      await dio.post('/samples', data: {
        'productName': productNames,
        'quantity': _selected.fold<int>(0, (sum, p) => sum + p.qty),
        'notes': notes,
        'items': itemsJson,
        'price': _selected.isNotEmpty && _selected.first.price > 0
            ? _selected.fold<double>(0, (sum, p) => sum + p.price * p.qty) /
              _selected.fold<int>(0, (sum, p) => sum + p.qty)
            : null,
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
    final productsAsync = ref.watch(productsSearchProvider(_searchCtrl.text.trim()));

    return DraggableScrollableSheet(
      initialChildSize: 0.9,
      minChildSize: 0.5,
      maxChildSize: 0.95,
      expand: false,
      builder: (_, scrollCtrl) => Container(
        decoration: const BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.vertical(top: Radius.circular(24)),
        ),
        child: Column(children: [
          // Handle
          Container(
            margin: const EdgeInsets.symmetric(vertical: 12),
            width: 40, height: 4,
            decoration: BoxDecoration(color: Colors.grey.shade300, borderRadius: BorderRadius.circular(2)),
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 20),
            child: Row(children: [
              const Text('Request Samples', style: TextStyle(fontSize: 18, fontWeight: FontWeight.w800)),
              const Spacer(),
              IconButton(icon: const Icon(Icons.close), onPressed: () => Navigator.pop(context)),
            ]),
          ),

          // Search bar
          Padding(
            padding: const EdgeInsets.fromLTRB(20, 8, 20, 8),
            child: TextField(
              controller: _searchCtrl,
              decoration: _inputDecor('Search products…').copyWith(
                prefixIcon: const Icon(Icons.search_rounded),
              ),
            ),
          ),

          // Selected summary chip row
          if (_selected.isNotEmpty)
            Container(
              height: 38,
              margin: const EdgeInsets.only(bottom: 8),
              child: ListView.separated(
                padding: const EdgeInsets.symmetric(horizontal: 20),
                scrollDirection: Axis.horizontal,
                itemCount: _selected.length,
                separatorBuilder: (_, __) => const SizedBox(width: 6),
                itemBuilder: (_, i) {
                  final p = _selected[i];
                  return Chip(
                    label: Text('${p.name} ×${p.qty}', style: const TextStyle(fontSize: 11)),
                    backgroundColor: AppColors.primary.withOpacity(0.1),
                    side: BorderSide(color: AppColors.primary.withOpacity(0.3)),
                    deleteIcon: const Icon(Icons.close, size: 14),
                    onDeleted: () => setState(() => _selected.removeAt(i)),
                    materialTapTargetSize: MaterialTapTargetSize.shrinkWrap,
                    visualDensity: VisualDensity.compact,
                  );
                },
              ),
            ),

          // Product list
          Expanded(
            child: productsAsync.when(
              loading: () => const Center(child: CircularProgressIndicator()),
              error: (e, _) => Center(child: Text(e.toString())),
              data: (products) {
                if (products.isEmpty && _searchCtrl.text.isEmpty) {
                  return Center(child: Text('Type to search products', style: TextStyle(color: Colors.grey.shade500)));
                }
                if (products.isEmpty) {
                  return Center(child: Text('No products found', style: TextStyle(color: Colors.grey.shade500)));
                }
                return ListView.separated(
                  controller: scrollCtrl,
                  padding: const EdgeInsets.symmetric(horizontal: 20),
                  itemCount: products.length,
                  separatorBuilder: (_, __) => const SizedBox(height: 6),
                  itemBuilder: (_, i) {
                    final p = products[i];
                    final pid = p['id'] as int;
                    final selectedIdx = _selected.indexWhere((s) => s.id == pid);
                    final isSelected = selectedIdx >= 0;

                    return GestureDetector(
                      onTap: () => _toggleProduct(p),
                      child: Container(
                        padding: const EdgeInsets.all(12),
                        decoration: BoxDecoration(
                          color: isSelected ? AppColors.primary.withOpacity(0.08) : Colors.white,
                          borderRadius: BorderRadius.circular(12),
                          border: Border.all(color: isSelected ? AppColors.primary : Colors.grey.shade100),
                        ),
                        child: Row(children: [
                          Container(
                            width: 36, height: 36,
                            decoration: BoxDecoration(
                              color: isSelected ? AppColors.primary : Colors.grey.shade100,
                              borderRadius: BorderRadius.circular(8),
                            ),
                            child: Icon(
                              isSelected ? Icons.check_rounded : Icons.inventory_2_outlined,
                              color: isSelected ? Colors.white : Colors.grey.shade500,
                              size: 18,
                            ),
                          ),
                          const SizedBox(width: 12),
                          Expanded(
                            child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
                              Text(p['name'] ?? '', style: const TextStyle(fontWeight: FontWeight.w600, fontSize: 13)),
                              Text(
                                '${p['grade'] != null && (p['grade'] as String).isNotEmpty ? 'Grade ${p['grade']} · ' : ''}${p['brand'] ?? ''}',
                                style: TextStyle(fontSize: 11, color: Colors.grey.shade500),
                              ),
                            ]),
                          ),
                          if ((p['price'] as num?)?.toDouble() != null && (p['price'] as num) > 0)
                            Text(
                              'PKR ${fmt.format((p['price'] as num).toInt())}',
                              style: const TextStyle(fontSize: 12, fontWeight: FontWeight.w700, color: AppColors.primary),
                            ),
                          // Quantity adjuster when selected
                          if (isSelected) ...[
                            const SizedBox(width: 8),
                            GestureDetector(
                              onTap: () { if (_selected[selectedIdx].qty > 1) setState(() => _selected[selectedIdx].qty--); },
                              child: const Icon(Icons.remove_circle_outline, size: 20, color: AppColors.primary),
                            ),
                            Padding(
                              padding: const EdgeInsets.symmetric(horizontal: 4),
                              child: Text('${_selected[selectedIdx].qty}', style: const TextStyle(fontWeight: FontWeight.w700, fontSize: 15)),
                            ),
                            GestureDetector(
                              onTap: () => setState(() => _selected[selectedIdx].qty++),
                              child: const Icon(Icons.add_circle_outline, size: 20, color: AppColors.primary),
                            ),
                          ],
                        ]),
                      ),
                    );
                  },
                );
              },
            ),
          ),

          // Footer
          Padding(
            padding: EdgeInsets.fromLTRB(20, 8, 20, MediaQuery.of(context).viewInsets.bottom + 20),
            child: Column(mainAxisSize: MainAxisSize.min, crossAxisAlignment: CrossAxisAlignment.start, children: [
              _Field(label: 'Notes (optional)', child: TextField(
                controller: _notesCtrl,
                maxLines: 2,
                decoration: _inputDecor('Any additional details…'),
              )),

              if (_selected.isNotEmpty && _totalCost > 0) ...[
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
                    Text(
                      '${_selected.length} product${_selected.length > 1 ? 's' : ''} · Est. PKR ${fmt.format(_totalCost.toInt())}',
                      style: const TextStyle(fontSize: 13, fontWeight: FontWeight.w700, color: AppColors.primary),
                    ),
                  ]),
                ),
              ],

              if (_error != null) ...[
                const SizedBox(height: 8),
                Text(_error!, style: const TextStyle(color: Colors.red, fontSize: 13)),
              ],

              const SizedBox(height: 16),
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
                      : Text(
                          _selected.isEmpty ? 'Select Products to Submit' : 'Submit ${_selected.length} Request${_selected.length > 1 ? 's' : ''}',
                          style: const TextStyle(fontWeight: FontWeight.w700, fontSize: 15),
                        ),
                ),
              ),
            ]),
          ),
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

      final pdfBase64Str = 'data:application/pdf;base64,${base64Encode(pdfBytes)}';

      final dio = ref.read(dioClientProvider);
      await dio.patch('/samples/${widget.sample.id}', data: {
        'customerName': _selectedCustomer?['name'],
        'customerId': _selectedCustomer?['id'],
        'signatureBase64': sigBase64,
        'pdfBase64': pdfBase64Str,
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
    final doc   = pw.Document();
    pw.MemoryImage? sigImage;
    if (sigBytes != null) sigImage = pw.MemoryImage(sigBytes);

    final fmt      = NumberFormat('#,##0', 'en_US');
    final now      = DateTime.now();
    final dateStr  = DateFormat('dd-MM-yyyy').format(now);
    final timeStr  = DateFormat('hh:mm a').format(now);
    final unitPrice = widget.sample.price ?? 0.0;
    final totalAmt  = unitPrice * _qty;
    final custName  = _selectedCustomer?['name'] as String? ?? 'N/A';
    final custAddr  = (_selectedCustomer?['address'] as String?) ?? '';
    final custPhone = (_selectedCustomer?['phone'] as String?) ?? '';
    final refNo     = widget.sample.id.toString().padLeft(10, '0');

    // Amount in words (simple)
    String amountInWords(double amt) {
      if (amt <= 0) return 'ZERO ONLY';
      final n = amt.toInt();
      return '${fmt.format(n)} RUPEES ONLY';
    }

    // Border style
    const border = pw.TableBorder(
      top:    pw.BorderSide(width: 1),
      bottom: pw.BorderSide(width: 1),
      left:   pw.BorderSide(width: 1),
      right:  pw.BorderSide(width: 1),
      horizontalInside: pw.BorderSide(width: 0.5, color: PdfColors.grey400),
      verticalInside:   pw.BorderSide(width: 1),
    );

    pw.Widget cell(String text, {bool bold = false, pw.TextAlign align = pw.TextAlign.left, double size = 8}) =>
        pw.Padding(
          padding: const pw.EdgeInsets.all(4),
          child: pw.Text(text, textAlign: align,
              style: pw.TextStyle(fontSize: size, fontWeight: bold ? pw.FontWeight.bold : pw.FontWeight.normal)),
        );

    doc.addPage(pw.Page(
      pageFormat: PdfPageFormat.a4,
      margin: const pw.EdgeInsets.all(24),
      build: (ctx) => pw.Column(
        crossAxisAlignment: pw.CrossAxisAlignment.stretch,
        children: [
          // ── TOP HEADER BOX ─────────────────────────────────────────────
          pw.Container(
            decoration: pw.BoxDecoration(border: pw.Border.all(width: 1.5)),
            child: pw.Row(crossAxisAlignment: pw.CrossAxisAlignment.start, children: [
              // Logo block
              pw.Container(
                width: 110, height: 100,
                padding: const pw.EdgeInsets.all(8),
                decoration: const pw.BoxDecoration(
                  border: pw.Border(right: pw.BorderSide(width: 1.5)),
                ),
                child: pw.Column(mainAxisAlignment: pw.MainAxisAlignment.center, children: [
                  // Red bookmark logo box
                  pw.Container(
                    width: 60, height: 60,
                    color: PdfColors.red800,
                    child: pw.Center(
                      child: pw.Text('BOOKMARK',
                          textAlign: pw.TextAlign.center,
                          style: pw.TextStyle(fontSize: 8, fontWeight: pw.FontWeight.bold, color: PdfColors.white)),
                    ),
                  ),
                  pw.SizedBox(height: 4),
                  pw.Text('BOOKMARK', style: pw.TextStyle(fontSize: 10, fontWeight: pw.FontWeight.bold)),
                ]),
              ),
              // Left info block
              pw.Expanded(
                child: pw.Container(
                  padding: const pw.EdgeInsets.all(8),
                  decoration: const pw.BoxDecoration(
                    border: pw.Border(right: pw.BorderSide(width: 1)),
                  ),
                  child: pw.Column(crossAxisAlignment: pw.CrossAxisAlignment.start, children: [
                    _pdfInfoRow('INVOICE TYPE:', 'SAMPLE DELIVERY'),
                    _pdfInfoRow('INVOICE No.:', refNo),
                    _pdfInfoRow('INVOICE DATE:', dateStr),
                    _pdfInfoRow('INVOICE LOCATION:', custAddr.isNotEmpty ? custAddr.split(',').first : 'FIELD'),
                    _pdfInfoRow('HELP LINE:', '+92 3363 008 008'),
                    pw.SizedBox(height: 4),
                    pw.Text('For Complaints & Queries:', style: pw.TextStyle(fontSize: 7, fontWeight: pw.FontWeight.bold)),
                    pw.Text('customer.care@bookmark.com.pk', style: const pw.TextStyle(fontSize: 7)),
                  ]),
                ),
              ),
              // Right info block
              pw.Expanded(
                child: pw.Padding(
                  padding: const pw.EdgeInsets.all(8),
                  child: pw.Column(crossAxisAlignment: pw.CrossAxisAlignment.start, children: [
                    _pdfInfoRow("CUSTOMER'S NAME:", custName, bold: true),
                    pw.SizedBox(height: 4),
                    _pdfInfoRow('ADDRESS:', custAddr),
                    _pdfInfoRow('TELEPHONE No.:', custPhone),
                    _pdfInfoRow('CONTACT PERSON:', custName),
                    _pdfInfoRow('DELIVERY DATE:', '$dateStr  $timeStr'),
                  ]),
                ),
              ),
            ]),
          ),

          // ── PRODUCT TABLE ───────────────────────────────────────────────
          pw.SizedBox(height: 8),
          pw.Table(
            border: border,
            columnWidths: const {
              0: pw.FixedColumnWidth(28),
              1: pw.FixedColumnWidth(100),
              2: pw.FlexColumnWidth(),
              3: pw.FixedColumnWidth(36),
              4: pw.FixedColumnWidth(60),
              5: pw.FixedColumnWidth(70),
            },
            children: [
              // Header row
              pw.TableRow(
                decoration: const pw.BoxDecoration(color: PdfColors.grey200),
                children: [
                  cell('S.No.', bold: true, align: pw.TextAlign.center),
                  cell('Product Code', bold: true),
                  cell("Product's Name", bold: true),
                  cell('Qty.', bold: true, align: pw.TextAlign.center),
                  cell('Retail Price', bold: true, align: pw.TextAlign.right),
                  cell('Total Amount', bold: true, align: pw.TextAlign.right),
                ],
              ),
              // Product row
              pw.TableRow(children: [
                cell('1', align: pw.TextAlign.center),
                cell('SMP-${widget.sample.id}'),
                cell(widget.sample.productName.toUpperCase()),
                cell('$_qty', align: pw.TextAlign.center),
                cell(unitPrice > 0 ? fmt.format(unitPrice.toInt()) : '—', align: pw.TextAlign.right),
                cell(unitPrice > 0 ? fmt.format(totalAmt.toInt()) : '—', align: pw.TextAlign.right),
              ]),
              // Empty filler rows
              for (int i = 0; i < 4; i++)
                pw.TableRow(children: List.generate(6, (_) => pw.SizedBox(height: 18))),
            ],
          ),

          // ── BOTTOM SECTION ──────────────────────────────────────────────
          pw.SizedBox(height: 8),
          pw.Row(crossAxisAlignment: pw.CrossAxisAlignment.start, children: [
            // Left: amount in words + delivery info + signature
            pw.Expanded(
              flex: 3,
              child: pw.Column(crossAxisAlignment: pw.CrossAxisAlignment.start, children: [
                pw.Text('AMOUNT IN WORDS:', style: pw.TextStyle(fontSize: 7.5, fontWeight: pw.FontWeight.bold)),
                pw.Text('(${amountInWords(totalAmt)})', style: const pw.TextStyle(fontSize: 7.5)),
                pw.SizedBox(height: 12),
                _pdfInfoRow('DELIVERED BY:', _notesCtrl.text.trim().isEmpty ? 'FIELD OFFICER' : _notesCtrl.text.trim()),
                _pdfInfoRow('REMARKS:', _notesCtrl.text.trim()),
                pw.SizedBox(height: 20),
                // Customer signature box
                pw.Text('CUSTOMER SIGNATURE:', style: pw.TextStyle(fontSize: 7.5, fontWeight: pw.FontWeight.bold)),
                pw.SizedBox(height: 6),
                pw.Container(
                  width: 200, height: 80,
                  decoration: pw.BoxDecoration(border: pw.Border.all(color: PdfColors.grey400)),
                  child: sigImage != null
                      ? pw.Image(sigImage, fit: pw.BoxFit.contain)
                      : pw.Center(child: pw.Text('Customer Signature', style: const pw.TextStyle(fontSize: 8, color: PdfColors.grey400))),
                ),
              ]),
            ),
            pw.SizedBox(width: 16),
            // Right: totals
            pw.Expanded(
              flex: 2,
              child: pw.Table(
                border: pw.TableBorder(
                  bottom: const pw.BorderSide(width: 1),
                  horizontalInside: const pw.BorderSide(width: 0.5, color: PdfColors.grey300),
                ),
                columnWidths: const {0: pw.FlexColumnWidth(), 1: pw.FixedColumnWidth(80)},
                children: [
                  _totalsRow('GROSS AMOUNT:', fmt.format(totalAmt.toInt())),
                  _totalsRow('LESS: DISCOUNT:', '—'),
                  _totalsRow('DISCOUNTED AMOUNT:', fmt.format(totalAmt.toInt()), bold: true),
                  _totalsRow('ADD: DELIVERY CHARGES:', '—'),
                  _totalsRow('TOTAL AMOUNT:', fmt.format(totalAmt.toInt()), bold: true, highlight: true),
                ],
              ),
            ),
          ]),

          pw.SizedBox(height: 16),
          pw.Divider(color: PdfColors.grey300),
          pw.Text('This document is auto-generated by Bookmark SFA Field Force Manager. Ref #$refNo',
              style: const pw.TextStyle(fontSize: 7, color: PdfColors.grey500)),
        ],
      ),
    ));
    return doc.save();
  }

  pw.Widget _pdfInfoRow(String label, String value, {bool bold = false}) => pw.Padding(
    padding: const pw.EdgeInsets.symmetric(vertical: 1.5),
    child: pw.Row(crossAxisAlignment: pw.CrossAxisAlignment.start, children: [
      pw.Text(label, style: pw.TextStyle(fontSize: 7.5, fontWeight: pw.FontWeight.bold)),
      pw.SizedBox(width: 4),
      pw.Expanded(child: pw.Text(value, style: pw.TextStyle(fontSize: 7.5, fontWeight: bold ? pw.FontWeight.bold : pw.FontWeight.normal))),
    ]),
  );

  pw.TableRow _totalsRow(String label, String value, {bool bold = false, bool highlight = false}) =>
    pw.TableRow(
      decoration: highlight ? const pw.BoxDecoration(color: PdfColors.grey200) : null,
      children: [
        pw.Padding(padding: const pw.EdgeInsets.all(3),
            child: pw.Text(label, style: pw.TextStyle(fontSize: 7.5, fontWeight: bold ? pw.FontWeight.bold : pw.FontWeight.normal))),
        pw.Padding(padding: const pw.EdgeInsets.all(3),
            child: pw.Text(value, textAlign: pw.TextAlign.right, style: pw.TextStyle(fontSize: 7.5, fontWeight: bold ? pw.FontWeight.bold : pw.FontWeight.normal))),
      ],
    );

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
