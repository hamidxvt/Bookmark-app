import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';

import '../../../core/theme/app_theme.dart';
import '../../../core/network/dio_client.dart';

// ── Models ────────────────────────────────────────────────────────────────────
class SampleRequest {
  final int id;
  final String productName, status;
  final String? institutionName;
  final int quantity;
  final double totalValue;
  final bool isRecovered;
  final int daysSinceCreated;

  const SampleRequest({
    required this.id, required this.productName, required this.status,
    this.institutionName, required this.quantity, required this.totalValue,
    required this.isRecovered, required this.daysSinceCreated,
  });

  factory SampleRequest.fromJson(Map<String, dynamic> j) => SampleRequest(
    id: j['id'] ?? 0,
    productName: j['productName'] ?? 'Sample',
    status: j['status'] ?? 'pending',
    institutionName: j['institutionName'] as String?,
    quantity: (j['quantity'] ?? 1) as int,
    totalValue: (j['totalValue'] ?? 0).toDouble(),
    isRecovered: j['isRecovered'] == true,
    daysSinceCreated: (j['daysSinceCreated'] ?? 0) as int,
  );
}

class SamplesData {
  final List<SampleRequest> requests;
  final double budgetUsed, budgetTotal, budgetRemaining;
  const SamplesData({
    required this.requests, required this.budgetUsed,
    required this.budgetTotal, required this.budgetRemaining,
  });
}

// ── Provider ──────────────────────────────────────────────────────────────────
final samplesProvider = FutureProvider.autoDispose<SamplesData>((ref) async {
  final dio = ref.watch(dioClientProvider);
  final res = await dio.get('/samples');
  final d = res.data['data'] as Map<String, dynamic>;
  return SamplesData(
    requests: (d['requests'] as List)
        .cast<Map<String, dynamic>>()
        .map(SampleRequest.fromJson)
        .toList(),
    budgetUsed: (d['budgetUsed'] ?? 0).toDouble(),
    budgetTotal: (d['budgetTotal'] ?? 300000).toDouble(),
    budgetRemaining: (d['budgetRemaining'] ?? 300000).toDouble(),
  );
});

// ── Screen ────────────────────────────────────────────────────────────────────
class SamplesScreen extends ConsumerWidget {
  const SamplesScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final async = ref.watch(samplesProvider);
    return Scaffold(
      backgroundColor: AppColors.background,
      appBar: AppBar(
        title: const Text('Sample Management'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios_new_rounded),
          onPressed: () => context.go('/dashboard'),
        ),
        actions: [
          IconButton(
            icon: const Icon(Icons.refresh_rounded),
            onPressed: () => ref.invalidate(samplesProvider),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton.extended(
        onPressed: () => _showRequestSheet(context, ref),
        icon: const Icon(Icons.add_box_rounded),
        label: const Text('Request Sample'),
        backgroundColor: AppColors.primary,
        foregroundColor: Colors.white,
      ),
      body: async.when(
        loading: () => const Center(child: CircularProgressIndicator()),
        error: (e, _) => Center(child: Column(mainAxisSize: MainAxisSize.min, children: [
          const Icon(Icons.error_outline_rounded, size: 48, color: Colors.grey),
          const SizedBox(height: 12),
          Text(e.toString(), textAlign: TextAlign.center, style: const TextStyle(color: Colors.grey)),
          const SizedBox(height: 16),
          FilledButton.icon(
            icon: const Icon(Icons.refresh_rounded),
            label: const Text('Retry'),
            onPressed: () => ref.invalidate(samplesProvider),
          ),
        ])),
        data: (data) => ListView(
          padding: const EdgeInsets.fromLTRB(16, 16, 16, 100),
          children: [
            // Budget card
            _BudgetCard(data: data).animate().fadeIn(duration: 300.ms),
            const SizedBox(height: 24),

            // Header
            Row(children: [
              Text('My Sample Requests',
                  style: Theme.of(context).textTheme.labelLarge?.copyWith(color: AppColors.primary)),
              const Spacer(),
              if (data.requests.isNotEmpty)
                Text('${data.requests.length} total',
                    style: Theme.of(context).textTheme.bodySmall),
            ]),
            const SizedBox(height: 12),

            if (data.requests.isEmpty)
              _EmptySamples()
            else
              ...data.requests.asMap().entries.map((e) =>
                _SampleCard(sample: e.value)
                    .animate(delay: (e.key * 50).ms).slideY(begin: 0.1).fadeIn()),
          ],
        ),
      ),
    );
  }

  void _showRequestSheet(BuildContext context, WidgetRef ref) {
    showModalBottomSheet(
      context: context,
      isScrollControlled: true,
      backgroundColor: Colors.transparent,
      builder: (_) => _SampleRequestSheet(
        onSubmitted: () => ref.invalidate(samplesProvider),
      ),
    );
  }
}

// ── Budget card ───────────────────────────────────────────────────────────────
class _BudgetCard extends StatelessWidget {
  final SamplesData data;
  const _BudgetCard({required this.data});

  @override
  Widget build(BuildContext context) {
    final pct = data.budgetTotal > 0
        ? (data.budgetUsed / data.budgetTotal).clamp(0.0, 1.0)
        : 0.0;
    final isWarning = pct > 0.8;

    return Container(
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        gradient: const LinearGradient(
          colors: [Color(0xFFC8102E), Color(0xFF9B0B22)],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
        borderRadius: BorderRadius.circular(AppRadius.lg),
      ),
      child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
        Row(children: [
          const Icon(Icons.inventory_2_rounded, color: Colors.white70, size: 18),
          const SizedBox(width: 8),
          const Text('Sample Budget', style: TextStyle(color: Colors.white70, fontSize: 13, fontWeight: FontWeight.w600)),
          const Spacer(),
          if (isWarning)
            Container(
              padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 3),
              decoration: BoxDecoration(
                color: Colors.orange.withOpacity(0.2),
                borderRadius: BorderRadius.circular(20),
                border: Border.all(color: Colors.orange.withOpacity(0.5)),
              ),
              child: const Text('⚠ Low Budget',
                  style: TextStyle(color: Colors.orange, fontSize: 10, fontWeight: FontWeight.w700)),
            ),
        ]),
        const SizedBox(height: 10),
        Row(crossAxisAlignment: CrossAxisAlignment.end, children: [
          Text(
            'Rs ${_fmt(data.budgetRemaining)}',
            style: const TextStyle(color: Colors.white, fontSize: 28, fontWeight: FontWeight.w800, height: 1),
          ),
          const SizedBox(width: 4),
          Padding(
            padding: const EdgeInsets.only(bottom: 3),
            child: Text('remaining', style: TextStyle(color: Colors.white.withOpacity(0.7), fontSize: 12)),
          ),
        ]),
        const SizedBox(height: 10),
        ClipRRect(
          borderRadius: BorderRadius.circular(4),
          child: LinearProgressIndicator(
            value: pct,
            backgroundColor: Colors.white.withOpacity(0.2),
            valueColor: AlwaysStoppedAnimation<Color>(
                isWarning ? Colors.orange : Colors.greenAccent),
            minHeight: 6,
          ),
        ),
        const SizedBox(height: 6),
        Row(children: [
          Text('Used: Rs ${_fmt(data.budgetUsed)}',
              style: TextStyle(color: Colors.white.withOpacity(0.7), fontSize: 11)),
          const Spacer(),
          Text('Total: Rs ${_fmt(data.budgetTotal)}',
              style: TextStyle(color: Colors.white.withOpacity(0.7), fontSize: 11)),
        ]),
      ]),
    );
  }

  String _fmt(double v) {
    if (v >= 100000) return '${(v / 1000).toStringAsFixed(0)}k';
    return v.toStringAsFixed(0);
  }
}

// ── Sample card ───────────────────────────────────────────────────────────────
class _SampleCard extends StatelessWidget {
  final SampleRequest sample;
  const _SampleCard({required this.sample});

  @override
  Widget build(BuildContext context) {
    final (color, icon, label) = switch (sample.status.toLowerCase()) {
      'resolved' || 'approved' => (AppColors.success, Icons.check_circle_rounded, 'Approved'),
      'rejected'               => (AppColors.error,   Icons.cancel_rounded,       'Rejected'),
      _                        => (AppColors.warning,  Icons.hourglass_empty_rounded, 'Pending'),
    };
    final isOverdue = sample.status.toLowerCase() == 'pending' && sample.daysSinceCreated > 14;

    return Container(
      margin: const EdgeInsets.only(bottom: 10),
      padding: const EdgeInsets.all(14),
      decoration: BoxDecoration(
        color: AppColors.surface,
        borderRadius: BorderRadius.circular(AppRadius.lg),
        border: Border.all(
          color: isOverdue ? AppColors.warning.withOpacity(0.4) : AppColors.outline,
        ),
        boxShadow: [BoxShadow(
          color: Colors.black.withOpacity(0.03),
          blurRadius: 6, offset: const Offset(0, 2),
        )],
      ),
      child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
        Row(children: [
          const Icon(Icons.book_rounded, size: 16, color: AppColors.textMuted),
          const SizedBox(width: 8),
          Expanded(child: Text(
            sample.productName,
            style: Theme.of(context).textTheme.titleSmall,
            maxLines: 1, overflow: TextOverflow.ellipsis,
          )),
          const SizedBox(width: 8),
          Row(children: [
            Icon(icon, size: 14, color: color),
            const SizedBox(width: 4),
            Text(label, style: TextStyle(fontSize: 12, color: color, fontWeight: FontWeight.w600)),
          ]),
        ]),
        const SizedBox(height: 8),
        Row(children: [
          _InfoChip(label: 'Qty: ${sample.quantity}', icon: Icons.numbers_rounded),
          const SizedBox(width: 8),
          _InfoChip(label: 'Rs ${sample.totalValue.toStringAsFixed(0)}', icon: Icons.currency_rupee_rounded),
          const SizedBox(width: 8),
          _InfoChip(label: '${sample.daysSinceCreated}d ago', icon: Icons.schedule_rounded),
        ]),
        if (sample.institutionName != null && sample.institutionName!.isNotEmpty) ...[
          const SizedBox(height: 6),
          Row(children: [
            const Icon(Icons.business_rounded, size: 12, color: AppColors.textMuted),
            const SizedBox(width: 4),
            Expanded(child: Text(sample.institutionName!,
                style: Theme.of(context).textTheme.bodySmall,
                maxLines: 1, overflow: TextOverflow.ellipsis)),
          ]),
        ],
        if (isOverdue)
          Padding(
            padding: const EdgeInsets.only(top: 6),
            child: Container(
              padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
              decoration: BoxDecoration(
                color: AppColors.warning.withOpacity(0.1),
                borderRadius: BorderRadius.circular(6),
                border: Border.all(color: AppColors.warning.withOpacity(0.3)),
              ),
              child: const Text('⚠ Overdue — follow up with admin',
                  style: TextStyle(fontSize: 11, color: AppColors.warning, fontWeight: FontWeight.w600)),
            ),
          ),
      ]),
    );
  }
}

class _InfoChip extends StatelessWidget {
  final String label;
  final IconData icon;
  const _InfoChip({required this.label, required this.icon});

  @override
  Widget build(BuildContext context) => Container(
    padding: const EdgeInsets.symmetric(horizontal: 7, vertical: 3),
    decoration: BoxDecoration(
      color: AppColors.background,
      borderRadius: BorderRadius.circular(6),
      border: Border.all(color: AppColors.outline),
    ),
    child: Row(mainAxisSize: MainAxisSize.min, children: [
      Icon(icon, size: 11, color: AppColors.textMuted),
      const SizedBox(width: 4),
      Text(label, style: const TextStyle(fontSize: 11, color: AppColors.onBackground, fontWeight: FontWeight.w500)),
    ]),
  );
}

class _EmptySamples extends StatelessWidget {
  @override
  Widget build(BuildContext context) => Center(
    child: Padding(
      padding: const EdgeInsets.all(40),
      child: Column(mainAxisSize: MainAxisSize.min, children: [
        Icon(Icons.inventory_2_outlined, size: 56, color: AppColors.outline),
        const SizedBox(height: 12),
        Text('No sample requests', style: Theme.of(context).textTheme.titleSmall),
        const SizedBox(height: 6),
        Text('Tap "Request Sample" to submit a new sample request.',
            textAlign: TextAlign.center,
            style: Theme.of(context).textTheme.bodySmall),
      ]),
    ),
  );
}

// ── Request sample bottom sheet ───────────────────────────────────────────────
class _SampleRequestSheet extends ConsumerStatefulWidget {
  final VoidCallback onSubmitted;
  const _SampleRequestSheet({required this.onSubmitted});

  @override
  ConsumerState<_SampleRequestSheet> createState() => _SampleRequestSheetState();
}

class _SampleRequestSheetState extends ConsumerState<_SampleRequestSheet> {
  final _productCtrl    = TextEditingController();
  final _institutionCtrl = TextEditingController();
  final _notesCtrl      = TextEditingController();
  int _qty = 1;
  bool _submitting = false;
  String _error = '';

  // Product search
  List<Map<String, dynamic>> _products = [];
  Map<String, dynamic>? _selected;
  bool _searching = false;

  @override
  void dispose() {
    _productCtrl.dispose(); _institutionCtrl.dispose(); _notesCtrl.dispose();
    super.dispose();
  }

  Future<void> _search(String q) async {
    if (q.trim().isEmpty) { setState(() => _products = []); return; }
    setState(() => _searching = true);
    try {
      final dio = ref.read(dioClientProvider);
      final res = await dio.get('/products?q=$q');
      final d = res.data as Map<String, dynamic>;
      setState(() => _products = List<Map<String, dynamic>>.from(d['data'] ?? []));
    } catch (_) {
    } finally {
      if (mounted) setState(() => _searching = false);
    }
  }

  Future<void> _submit() async {
    if (_selected == null && _productCtrl.text.trim().isEmpty) {
      setState(() => _error = 'Please select or enter a product');
      return;
    }
    setState(() { _submitting = true; _error = ''; });
    try {
      final dio = ref.read(dioClientProvider);
      final res = await dio.post('/samples', data: {
        if (_selected != null) 'productId': _selected!['id'],
        'productName': _selected?['name'] ?? _productCtrl.text.trim(),
        'quantity': _qty,
        if (_institutionCtrl.text.isNotEmpty) 'institutionName': _institutionCtrl.text.trim(),
        if (_notesCtrl.text.isNotEmpty) 'notes': _notesCtrl.text.trim(),
      });
      if (res.data['success'] == true) {
        if (mounted) context.pop();
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
    return DraggableScrollableSheet(
      initialChildSize: 0.85,
      minChildSize: 0.5,
      maxChildSize: 0.95,
      builder: (_, scrollCtrl) => Container(
        margin: EdgeInsets.only(bottom: bottom),
        decoration: const BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.vertical(top: Radius.circular(24)),
        ),
        child: ListView(
          controller: scrollCtrl,
          padding: const EdgeInsets.fromLTRB(20, 16, 20, 20),
          children: [
            Center(child: Container(
              width: 40, height: 4,
              decoration: BoxDecoration(color: AppColors.outline, borderRadius: BorderRadius.circular(2)),
            )),
            const SizedBox(height: 16),
            Text('Request Sample Books',
                style: Theme.of(context).textTheme.titleLarge?.copyWith(fontWeight: FontWeight.bold)),
            const SizedBox(height: 6),
            Text('Submit a request for sample books to distribute to institutions.',
                style: Theme.of(context).textTheme.bodySmall),
            const SizedBox(height: 20),

            // Product search
            Text('Product / Book', style: Theme.of(context).textTheme.labelMedium?.copyWith(color: AppColors.textMuted)),
            const SizedBox(height: 8),
            if (_selected == null) ...[
              TextField(
                controller: _productCtrl,
                autofocus: true,
                onChanged: _search,
                decoration: InputDecoration(
                  hintText: 'Search by book name…',
                  prefixIcon: _searching
                      ? const Padding(padding: EdgeInsets.all(12),
                          child: SizedBox(width: 18, height: 18,
                              child: CircularProgressIndicator(strokeWidth: 2)))
                      : const Icon(Icons.search_rounded),
                  filled: true, fillColor: AppColors.background,
                  border: OutlineInputBorder(borderRadius: BorderRadius.circular(12)),
                ),
              ),
              if (_products.isNotEmpty) ...[
                const SizedBox(height: 6),
                Container(
                  decoration: BoxDecoration(
                    border: Border.all(color: AppColors.outline),
                    borderRadius: BorderRadius.circular(12),
                  ),
                  child: Column(
                    children: _products.take(5).map((p) => ListTile(
                      dense: true,
                      leading: const Icon(Icons.book_rounded, size: 18, color: AppColors.textMuted),
                      title: Text(p['name'] ?? '', style: const TextStyle(fontSize: 13)),
                      subtitle: Text('Rs ${(p['retailPrice'] ?? p['price'] ?? 0).toString()}',
                          style: const TextStyle(fontSize: 11)),
                      onTap: () => setState(() {
                        _selected = p;
                        _products = [];
                        _productCtrl.clear();
                      }),
                    )).toList(),
                  ),
                ),
              ],
            ] else
              ListTile(
                contentPadding: const EdgeInsets.symmetric(horizontal: 12, vertical: 4),
                tileColor: AppColors.primary.withOpacity(0.05),
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(12),
                  side: BorderSide(color: AppColors.primary.withOpacity(0.3)),
                ),
                leading: const Icon(Icons.book_rounded, color: AppColors.primary),
                title: Text(_selected!['name'] ?? '', style: const TextStyle(fontWeight: FontWeight.w600)),
                subtitle: Text('Rs ${_selected!['retailPrice'] ?? _selected!['price'] ?? 0}'),
                trailing: IconButton(
                  icon: const Icon(Icons.close_rounded, size: 18),
                  onPressed: () => setState(() => _selected = null),
                ),
              ),

            const SizedBox(height: 16),

            // Quantity
            Text('Quantity', style: Theme.of(context).textTheme.labelMedium?.copyWith(color: AppColors.textMuted)),
            const SizedBox(height: 8),
            Row(children: [
              IconButton(
                onPressed: _qty > 1 ? () => setState(() => _qty--) : null,
                icon: const Icon(Icons.remove_circle_rounded),
                color: AppColors.primary,
              ),
              Expanded(child: Center(child: Text('$_qty',
                  style: const TextStyle(fontSize: 22, fontWeight: FontWeight.w800)))),
              IconButton(
                onPressed: () => setState(() => _qty++),
                icon: const Icon(Icons.add_circle_rounded),
                color: AppColors.primary,
              ),
            ]),
            const SizedBox(height: 16),

            // Institution
            Text('Institution / School (optional)',
                style: Theme.of(context).textTheme.labelMedium?.copyWith(color: AppColors.textMuted)),
            const SizedBox(height: 8),
            TextField(
              controller: _institutionCtrl,
              textCapitalization: TextCapitalization.words,
              decoration: InputDecoration(
                hintText: 'Where will these be sent?',
                prefixIcon: const Icon(Icons.school_rounded),
                filled: true, fillColor: AppColors.background,
                border: OutlineInputBorder(borderRadius: BorderRadius.circular(12)),
              ),
            ),
            const SizedBox(height: 16),

            // Notes
            Text('Notes (optional)',
                style: Theme.of(context).textTheme.labelMedium?.copyWith(color: AppColors.textMuted)),
            const SizedBox(height: 8),
            TextField(
              controller: _notesCtrl,
              maxLines: 2,
              textCapitalization: TextCapitalization.sentences,
              decoration: InputDecoration(
                hintText: 'Any additional notes…',
                filled: true, fillColor: AppColors.background,
                border: OutlineInputBorder(borderRadius: BorderRadius.circular(12)),
              ),
            ),

            if (_error.isNotEmpty) ...[
              const SizedBox(height: 10),
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
                label: Text(_submitting ? 'Submitting…' : 'Submit Sample Request'),
                onPressed: _submitting ? null : _submit,
              ),
            ),
          ],
        ),
      ),
    );
  }
}
