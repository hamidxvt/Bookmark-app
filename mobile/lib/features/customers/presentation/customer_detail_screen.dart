import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:url_launcher/url_launcher.dart';

import '../../../core/theme/app_theme.dart';
import '../../../core/network/dio_client.dart';

// ── Provider ──────────────────────────────────────────────────────────────────
final customerDetailProvider =
    FutureProvider.autoDispose.family<Map<String, dynamic>, int>((ref, customerId) async {
  final dio = ref.watch(dioClientProvider);
  final res = await dio.get('/customers/$customerId');
  final body = res.data;
  if (body == null) throw Exception('No response from server');
  if (body['success'] == true) {
    final data = body['data'];
    if (data is Map<String, dynamic>) return data;
    if (data is Map) return Map<String, dynamic>.from(data);
    throw Exception('Unexpected data format');
  }
  throw Exception(body['error']?['message'] ?? body['error'] ?? 'Failed to load customer');
});

// ── Screen ────────────────────────────────────────────────────────────────────
class CustomerDetailScreen extends ConsumerStatefulWidget {
  final int customerId;
  const CustomerDetailScreen({super.key, required this.customerId});

  @override
  ConsumerState<CustomerDetailScreen> createState() => _CustomerDetailScreenState();
}

class _CustomerDetailScreenState extends ConsumerState<CustomerDetailScreen> {
  bool _editMode = false;
  bool _submitting = false;

  final _nameCtrl      = TextEditingController();
  final _ownerCtrl     = TextEditingController();
  final _phoneCtrl     = TextEditingController();
  final _emailCtrl     = TextEditingController();
  final _addressCtrl   = TextEditingController();
  final _categoryCtrl  = TextEditingController();
  final _notesCtrl     = TextEditingController();

  bool _prefilled = false;

  void _prefill(Map<String, dynamic> customer) {
    if (_prefilled) return;
    _prefilled = true;
    _nameCtrl.text     = customer['name']       as String? ?? '';
    _ownerCtrl.text    = customer['ownerName']  as String? ?? '';
    _phoneCtrl.text    = customer['ownerPhone'] as String? ?? '';
    _emailCtrl.text    = customer['email']      as String? ?? '';
    _addressCtrl.text  = customer['address']    as String? ?? '';
    _categoryCtrl.text = customer['category']   as String? ?? '';
  }

  @override
  void dispose() {
    _nameCtrl.dispose(); _ownerCtrl.dispose(); _phoneCtrl.dispose();
    _emailCtrl.dispose(); _addressCtrl.dispose(); _categoryCtrl.dispose();
    _notesCtrl.dispose();
    super.dispose();
  }

  Future<void> _submitUpdateRequest() async {
    setState(() => _submitting = true);
    try {
      final dio = ref.read(dioClientProvider);
      final res = await dio.post(
        '/customers/${widget.customerId}/update-request',
        data: {
          if (_nameCtrl.text.isNotEmpty)     'name':      _nameCtrl.text.trim(),
          if (_ownerCtrl.text.isNotEmpty)    'ownerName': _ownerCtrl.text.trim(),
          if (_phoneCtrl.text.isNotEmpty)    'ownerPhone': _phoneCtrl.text.trim(),
          if (_emailCtrl.text.isNotEmpty)    'email':     _emailCtrl.text.trim(),
          if (_addressCtrl.text.isNotEmpty)  'address':   _addressCtrl.text.trim(),
          if (_categoryCtrl.text.isNotEmpty) 'category':  _categoryCtrl.text.trim(),
          if (_notesCtrl.text.isNotEmpty)    'notes':     _notesCtrl.text.trim(),
        },
      );
      if (res.data['success'] == true) {
        if (mounted) {
          setState(() => _editMode = false);
          ScaffoldMessenger.of(context).showSnackBar(
            SnackBar(
              content: const Row(children: [
                Icon(Icons.check_circle_rounded, color: Colors.white, size: 18),
                SizedBox(width: 8),
                Expanded(child: Text('Update request submitted for admin approval!')),
              ]),
              backgroundColor: AppColors.success,
              behavior: SnackBarBehavior.floating,
              duration: const Duration(seconds: 4),
            ),
          );
        }
      } else {
        throw Exception(res.data['error'] ?? 'Failed');
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
      if (mounted) setState(() => _submitting = false);
    }
  }

  @override
  Widget build(BuildContext context) {
    final asyncData = ref.watch(customerDetailProvider(widget.customerId));

    return PopScope(
      canPop: false,
      onPopInvoked: (didPop) {
        if (!didPop) {
          if (_editMode) {
            setState(() { _editMode = false; _prefilled = false; });
          } else {
            context.pop();
          }
        }
      },
      child: Scaffold(
        backgroundColor: AppColors.background,
        appBar: AppBar(
          title: const Text('Customer Details'),
          leading: IconButton(
            icon: const Icon(Icons.arrow_back_ios_new_rounded),
            onPressed: () {
              if (_editMode) {
                setState(() { _editMode = false; _prefilled = false; });
              } else {
                context.pop();
              }
            },
            tooltip: _editMode ? 'Cancel editing' : 'Back',
          ),
          actions: [
            if (!_editMode)
              IconButton(
                icon: const Icon(Icons.edit_rounded),
                tooltip: 'Suggest changes',
                onPressed: () => setState(() => _editMode = true),
              ),
          ],
        ),
      body: asyncData.when(
        loading: () => const Center(child: CircularProgressIndicator()),
        error: (e, _) => Center(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              const Icon(Icons.error_outline_rounded, size: 48, color: Colors.grey),
              const SizedBox(height: 12),
              Text(e.toString(), style: const TextStyle(color: Colors.grey)),
              const SizedBox(height: 16),
              FilledButton.icon(
                icon: const Icon(Icons.refresh_rounded),
                label: const Text('Retry'),
                onPressed: () => ref.invalidate(customerDetailProvider(widget.customerId)),
              ),
            ],
          ),
        ),
        data: (customer) {
          _prefill(customer);
          return _editMode
              ? _EditForm(
                  nameCtrl: _nameCtrl,
                  ownerCtrl: _ownerCtrl,
                  phoneCtrl: _phoneCtrl,
                  emailCtrl: _emailCtrl,
                  addressCtrl: _addressCtrl,
                  categoryCtrl: _categoryCtrl,
                  notesCtrl: _notesCtrl,
                  submitting: _submitting,
                  onSubmit: _submitUpdateRequest,
                )
              : _DetailView(customer: customer);
        },
      ),
      ),
    );
  }
}

// ── Read-only detail view ─────────────────────────────────────────────────────
class _DetailView extends StatelessWidget {
  final Map<String, dynamic> customer;
  const _DetailView({required this.customer});

  @override
  Widget build(BuildContext context) {
    final orders = (customer['orders'] as List? ?? [])
        .cast<Map<String, dynamic>>();
    final visits = (customer['visits'] as List? ?? [])
        .cast<Map<String, dynamic>>();
    final phone = customer['ownerPhone'] as String? ?? '';
    final lat = customer['latitude'];
    final lng = customer['longitude'];
    final hasGps = lat != null && lng != null;

    return ListView(
      padding: const EdgeInsets.all(AppSpacing.md),
      children: [
        // Info card
        _InfoCard(customer: customer).animate().fadeIn(duration: 300.ms),
        const SizedBox(height: 16),

        // Action buttons
        Row(
          children: [
            if (phone.isNotEmpty)
              Expanded(
                child: OutlinedButton.icon(
                  icon: const Icon(Icons.call_rounded, size: 16),
                  label: const Text('Call'),
                  onPressed: () => launchUrl(Uri.parse('tel:$phone')),
                  style: OutlinedButton.styleFrom(
                    foregroundColor: AppColors.success,
                    side: BorderSide(color: AppColors.success.withOpacity(0.5)),
                    minimumSize: const Size(0, 42),
                  ),
                ),
              ),
            if (phone.isNotEmpty && hasGps) const SizedBox(width: 10),
            if (hasGps)
              Expanded(
                child: OutlinedButton.icon(
                  icon: const Icon(Icons.directions_rounded, size: 16),
                  label: const Text('Navigate'),
                  onPressed: () => launchUrl(
                    Uri.parse('google.navigation:q=$lat,$lng&mode=d'),
                    mode: LaunchMode.externalApplication,
                  ),
                  style: OutlinedButton.styleFrom(
                    foregroundColor: AppColors.info,
                    side: BorderSide(color: AppColors.info.withOpacity(0.5)),
                    minimumSize: const Size(0, 42),
                  ),
                ),
              ),
          ],
        ).animate(delay: 100.ms).fadeIn(),

        if (orders.isNotEmpty) ...[
          const SizedBox(height: 20),
          _SectionHeader(label: 'Order History', count: orders.length),
          const SizedBox(height: 8),
          ...orders.asMap().entries.map((e) => _OrderCard(order: e.value)
              .animate(delay: (e.key * 60).ms).slideY(begin: 0.1).fadeIn()),
        ],

        if (visits.isNotEmpty) ...[
          const SizedBox(height: 20),
          _SectionHeader(label: 'Recent Visits', count: visits.length),
          const SizedBox(height: 8),
          ...visits.asMap().entries.map((e) => _VisitHistoryCard(visit: e.value)
              .animate(delay: (e.key * 60).ms).slideY(begin: 0.1).fadeIn()),
        ],

        const SizedBox(height: 80),
      ],
    );
  }
}

class _InfoCard extends StatelessWidget {
  final Map<String, dynamic> customer;
  const _InfoCard({required this.customer});

  @override
  Widget build(BuildContext context) {
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
                child: Icon(
                  _typeIcon(customer['customerType'] as String? ?? ''),
                  color: Colors.white,
                  size: 20,
                ),
              ),
              const SizedBox(width: 12),
              Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      customer['name'] as String? ?? 'Customer',
                      style: const TextStyle(color: Colors.white, fontWeight: FontWeight.bold, fontSize: 16),
                    ),
                    if ((customer['category'] as String?) != null)
                      Text(
                        customer['category'] as String,
                        style: TextStyle(color: Colors.white.withOpacity(0.75), fontSize: 12),
                      ),
                  ],
                ),
              ),
              Container(
                padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 4),
                decoration: BoxDecoration(
                  color: Colors.white.withOpacity(0.2),
                  borderRadius: BorderRadius.circular(20),
                ),
                child: Text(
                  (customer['customerType'] as String? ?? 'OTHER').replaceAll('_', ' '),
                  style: const TextStyle(color: Colors.white, fontSize: 11, fontWeight: FontWeight.w600),
                ),
              ),
            ],
          ),
          const SizedBox(height: 12),
          _Divider(),
          const SizedBox(height: 12),
          _InfoRow(icon: Icons.person_outline_rounded, label: customer['ownerName'] as String? ?? '—'),
          if ((customer['ownerPhone'] as String?) != null && (customer['ownerPhone'] as String).isNotEmpty)
            _InfoRow(icon: Icons.phone_outlined, label: customer['ownerPhone'] as String),
          if ((customer['email'] as String?) != null && (customer['email'] as String).isNotEmpty)
            _InfoRow(icon: Icons.email_outlined, label: customer['email'] as String),
          if ((customer['address'] as String?) != null && (customer['address'] as String).isNotEmpty)
            _InfoRow(icon: Icons.location_on_outlined, label: customer['address'] as String),
          if ((customer['city'] as Map?)?.containsKey('name') == true)
            _InfoRow(icon: Icons.apartment_rounded, label: 'City: ${(customer['city'] as Map)['name']}'),
        ],
      ),
    );
  }

  IconData _typeIcon(String type) {
    return switch (type.toUpperCase()) {
      'SCHOOL' => Icons.school_rounded,
      'COLLEGE' => Icons.account_balance_rounded,
      'RETAILER' => Icons.store_rounded,
      'SELF' => Icons.person_rounded,
      _ => Icons.business_rounded,
    };
  }
}

class _Divider extends StatelessWidget {
  @override
  Widget build(BuildContext context) => Container(height: 1, color: Colors.white.withOpacity(0.2));
}

class _InfoRow extends StatelessWidget {
  final IconData icon;
  final String label;
  const _InfoRow({required this.icon, required this.label});

  @override
  Widget build(BuildContext context) => Padding(
        padding: const EdgeInsets.only(bottom: 6),
        child: Row(
          children: [
            Icon(icon, size: 14, color: Colors.white.withOpacity(0.7)),
            const SizedBox(width: 8),
            Expanded(
              child: Text(label,
                  style: TextStyle(color: Colors.white.withOpacity(0.9), fontSize: 13),
                  maxLines: 2,
                  overflow: TextOverflow.ellipsis),
            ),
          ],
        ),
      );
}

class _SectionHeader extends StatelessWidget {
  final String label;
  final int count;
  const _SectionHeader({required this.label, required this.count});

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Text(label, style: Theme.of(context).textTheme.labelLarge?.copyWith(color: AppColors.primary)),
        const Spacer(),
        Container(
          padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 2),
          decoration: BoxDecoration(
            color: AppColors.primary.withOpacity(0.1),
            borderRadius: BorderRadius.circular(20),
          ),
          child: Text('$count', style: TextStyle(color: AppColors.primary, fontSize: 11, fontWeight: FontWeight.w700)),
        ),
      ],
    );
  }
}

class _OrderCard extends StatelessWidget {
  final Map<String, dynamic> order;
  const _OrderCard({required this.order});

  @override
  Widget build(BuildContext context) {
    final items = (order['items'] as List? ?? []).cast<Map<String, dynamic>>();
    final total = (order['totalAmount'] ?? 0).toDouble();
    final date = order['createdAt'] as String? ?? '';

    return Container(
      margin: const EdgeInsets.only(bottom: 8),
      padding: const EdgeInsets.all(12),
      decoration: BoxDecoration(
        color: AppColors.surface,
        borderRadius: BorderRadius.circular(AppRadius.md),
        border: Border.all(color: AppColors.outline),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            children: [
              const Icon(Icons.receipt_long_rounded, size: 16, color: AppColors.textMuted),
              const SizedBox(width: 6),
              Text('Order #${order['id']}',
                  style: const TextStyle(fontWeight: FontWeight.w600, fontSize: 13)),
              const Spacer(),
              Text(
                'Rs ${total.toStringAsFixed(0)}',
                style: const TextStyle(color: AppColors.primary, fontWeight: FontWeight.w700, fontSize: 13),
              ),
            ],
          ),
          if (date.isNotEmpty) ...[
            const SizedBox(height: 4),
            Text(
              DateTime.tryParse(date)?.toLocal().toString().substring(0, 10) ?? date,
              style: const TextStyle(fontSize: 11, color: AppColors.textMuted),
            ),
          ],
          if (items.isNotEmpty) ...[
            const SizedBox(height: 8),
            ...items.take(3).map((item) {
              final product = item['product'] as Map<String, dynamic>? ?? {};
              return Padding(
                padding: const EdgeInsets.only(bottom: 3),
                child: Row(
                  children: [
                    const Icon(Icons.fiber_manual_record_rounded, size: 6, color: AppColors.textMuted),
                    const SizedBox(width: 6),
                    Expanded(
                      child: Text(product['title'] as String? ?? 'Product',
                          style: const TextStyle(fontSize: 12), maxLines: 1, overflow: TextOverflow.ellipsis),
                    ),
                    Text('×${item['quantity']}',
                        style: const TextStyle(fontSize: 12, color: AppColors.textMuted)),
                  ],
                ),
              );
            }),
            if (items.length > 3)
              Text('+${items.length - 3} more items',
                  style: const TextStyle(fontSize: 11, color: AppColors.textMuted)),
          ],
        ],
      ),
    );
  }
}

class _VisitHistoryCard extends StatelessWidget {
  final Map<String, dynamic> visit;
  const _VisitHistoryCard({required this.visit});

  @override
  Widget build(BuildContext context) {
    final date = visit['visitDate'] as String? ?? '';
    final type = visit['visitType'] as String? ?? 'regular';
    final notes = visit['notes'] as String? ?? '';

    return Container(
      margin: const EdgeInsets.only(bottom: 8),
      padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 10),
      decoration: BoxDecoration(
        color: AppColors.surface,
        borderRadius: BorderRadius.circular(AppRadius.md),
        border: Border.all(color: AppColors.success.withOpacity(0.3)),
      ),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Container(
            width: 8,
            height: 8,
            margin: const EdgeInsets.only(top: 4),
            decoration: const BoxDecoration(color: AppColors.success, shape: BoxShape.circle),
          ),
          const SizedBox(width: 10),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(children: [
                  Text(
                    DateTime.tryParse(date)?.toLocal().toString().substring(0, 10) ?? date,
                    style: const TextStyle(fontSize: 12, fontWeight: FontWeight.w600),
                  ),
                  const Spacer(),
                  Container(
                    padding: const EdgeInsets.symmetric(horizontal: 6, vertical: 2),
                    decoration: BoxDecoration(
                      color: AppColors.success.withOpacity(0.1),
                      borderRadius: BorderRadius.circular(4),
                    ),
                    child: Text(type.replaceAll('_', ' '),
                        style: const TextStyle(fontSize: 10, color: AppColors.success, fontWeight: FontWeight.w600)),
                  ),
                ]),
                if (notes.isNotEmpty) ...[
                  const SizedBox(height: 3),
                  Text(notes, style: const TextStyle(fontSize: 12, color: AppColors.textMuted),
                      maxLines: 2, overflow: TextOverflow.ellipsis),
                ],
              ],
            ),
          ),
        ],
      ),
    );
  }
}

// ── Edit form ─────────────────────────────────────────────────────────────────
class _EditForm extends StatelessWidget {
  final TextEditingController nameCtrl, ownerCtrl, phoneCtrl, emailCtrl, addressCtrl, categoryCtrl, notesCtrl;
  final bool submitting;
  final VoidCallback onSubmit;

  const _EditForm({
    required this.nameCtrl,
    required this.ownerCtrl,
    required this.phoneCtrl,
    required this.emailCtrl,
    required this.addressCtrl,
    required this.categoryCtrl,
    required this.notesCtrl,
    required this.submitting,
    required this.onSubmit,
  });

  @override
  Widget build(BuildContext context) {
    return ListView(
      padding: const EdgeInsets.all(AppSpacing.md),
      children: [
        // Info banner
        Container(
          padding: const EdgeInsets.all(12),
          decoration: BoxDecoration(
            color: AppColors.info.withOpacity(0.08),
            borderRadius: BorderRadius.circular(AppRadius.md),
            border: Border.all(color: AppColors.info.withOpacity(0.3)),
          ),
          child: Row(
            children: [
              const Icon(Icons.info_outline_rounded, color: AppColors.info, size: 20),
              const SizedBox(width: 10),
              Expanded(
                child: Text(
                  'Your changes will be sent to the admin for review. They will be applied only after approval.',
                  style: Theme.of(context).textTheme.bodySmall?.copyWith(color: AppColors.info),
                ),
              ),
            ],
          ),
        ).animate().fadeIn(duration: 300.ms),
        const SizedBox(height: 20),

        ...[
          ('Customer Name', nameCtrl, Icons.store_rounded, TextInputType.text, false),
          ('Owner Name', ownerCtrl, Icons.person_outline_rounded, TextInputType.name, false),
          ('Phone Number', phoneCtrl, Icons.phone_outlined, TextInputType.phone, false),
          ('Email', emailCtrl, Icons.email_outlined, TextInputType.emailAddress, false),
          ('Category', categoryCtrl, Icons.label_outline_rounded, TextInputType.text, false),
          ('Address', addressCtrl, Icons.location_on_outlined, TextInputType.multiline, true),
        ].asMap().entries.map((e) {
          final i = e.key;
          final (label, ctrl, icon, kbType, multiline) = e.value;
          return Padding(
            padding: const EdgeInsets.only(bottom: 14),
            child: TextFormField(
              controller: ctrl,
              keyboardType: kbType,
              maxLines: multiline ? 3 : 1,
              textCapitalization: TextCapitalization.sentences,
              decoration: InputDecoration(
                labelText: label,
                prefixIcon: Icon(icon),
                alignLabelWithHint: multiline,
                prefixIconConstraints: multiline
                    ? const BoxConstraints(minWidth: 48, minHeight: 48)
                    : null,
              ),
            ).animate(delay: (i * 50).ms).slideY(begin: 0.15).fadeIn(),
          );
        }),

        Padding(
          padding: const EdgeInsets.only(bottom: 14),
          child: TextFormField(
            controller: notesCtrl,
            maxLines: 3,
            textCapitalization: TextCapitalization.sentences,
            decoration: const InputDecoration(
              labelText: 'Notes for Admin (optional)',
              prefixIcon: Padding(
                padding: EdgeInsets.only(bottom: 40),
                child: Icon(Icons.notes_rounded),
              ),
              alignLabelWithHint: true,
              hintText: 'Explain why these changes are needed...',
            ),
          ).animate(delay: 300.ms).slideY(begin: 0.15).fadeIn(),
        ),

        const SizedBox(height: 12),

        FilledButton.icon(
          icon: submitting
              ? const SizedBox(width: 18, height: 18, child: CircularProgressIndicator(strokeWidth: 2, color: Colors.white))
              : const Icon(Icons.send_rounded),
          label: Text(submitting ? 'Submitting…' : 'Submit for Admin Approval'),
          onPressed: submitting ? null : onSubmit,
          style: FilledButton.styleFrom(
            minimumSize: const Size.fromHeight(52),
          ),
        ).animate(delay: 350.ms).slideY(begin: 0.2).fadeIn(),

        const SizedBox(height: 40),
      ],
    );
  }
}
