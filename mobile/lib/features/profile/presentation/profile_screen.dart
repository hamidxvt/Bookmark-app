import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:image_picker/image_picker.dart';
import 'dart:io';

import '../../../core/theme/app_theme.dart';
import '../../../core/network/dio_client.dart';
import '../../auth/presentation/auth_notifier.dart';

// ── Model ─────────────────────────────────────────────────────────────────────
class ProfileData {
  final int id;
  final String name;
  final String email;
  final String phone;
  final String city;
  final String jobStatus;
  final String designation;
  final String? profilePhoto;
  final int totalVisits;
  final int completedVisits;
  final int shiftsWorked;
  final double basicSalary;
  final double runningPay;
  final double netSalary;
  final int rewardPoints;

  const ProfileData({
    required this.id,
    required this.name,
    required this.email,
    required this.phone,
    required this.city,
    required this.jobStatus,
    required this.designation,
    this.profilePhoto,
    required this.totalVisits,
    required this.completedVisits,
    required this.shiftsWorked,
    required this.basicSalary,
    required this.runningPay,
    required this.netSalary,
    required this.rewardPoints,
  });

  factory ProfileData.fromJson(Map<String, dynamic> j) {
    final salary = j['salary'] as Map<String, dynamic>? ?? {};
    final stats  = j['stats']  as Map<String, dynamic>? ?? {};
    return ProfileData(
      id:             (j['id'] ?? 0) as int,
      name:           j['name'] ?? '',
      email:          j['email'] ?? '',
      phone:          j['phone'] ?? '',
      city:           j['city'] ?? 'N/A',
      jobStatus:      j['jobStatus'] ?? 'ACTIVE',
      designation:    j['designation'] ?? 'Sales Officer',
      profilePhoto:   j['profilePhoto'],
      totalVisits:    (stats['totalVisits'] ?? 0) as int,
      completedVisits:(stats['completedVisits'] ?? 0) as int,
      shiftsWorked:   (stats['shiftsWorked'] ?? 0) as int,
      basicSalary:    (salary['basicSalary'] ?? 0).toDouble(),
      runningPay:     (salary['runningPay'] ?? 0).toDouble(),
      netSalary:      (salary['netSalary'] ?? 0).toDouble(),
      rewardPoints:   (salary['rewardPoints'] ?? 0) as int,
    );
  }
}

final profileProvider = FutureProvider.autoDispose<ProfileData>((ref) async {
  final dio = ref.watch(dioClientProvider);
  final res = await dio.get('/profile');
  final data = (res.data as Map<String, dynamic>)['data'] as Map<String, dynamic>? ?? res.data as Map<String, dynamic>;
  return ProfileData.fromJson(data);
});

// ── Screen ────────────────────────────────────────────────────────────────────
class ProfileScreen extends ConsumerStatefulWidget {
  const ProfileScreen({super.key});

  @override
  ConsumerState<ProfileScreen> createState() => _ProfileScreenState();
}

class _ProfileScreenState extends ConsumerState<ProfileScreen> {
  bool _uploadingPhoto = false;

  Future<void> _pickAndUploadPhoto() async {
    final picker = ImagePicker();
    final source = await showModalBottomSheet<ImageSource>(
      context: context,
      backgroundColor: Colors.white,
      shape: const RoundedRectangleBorder(
        borderRadius: BorderRadius.vertical(top: Radius.circular(20)),
      ),
      builder: (_) => SafeArea(
        child: Column(mainAxisSize: MainAxisSize.min, children: [
          Container(
            width: 40, height: 4,
            margin: const EdgeInsets.only(top: 12, bottom: 16),
            decoration: BoxDecoration(color: Colors.grey[300], borderRadius: BorderRadius.circular(2)),
          ),
          const Text('Set Profile Photo', style: TextStyle(fontSize: 16, fontWeight: FontWeight.w700)),
          const SizedBox(height: 16),
          ListTile(
            leading: Container(padding: const EdgeInsets.all(8),
              decoration: BoxDecoration(color: AppColors.primary.withOpacity(0.1), borderRadius: BorderRadius.circular(10)),
              child: const Icon(Icons.camera_alt_rounded, color: AppColors.primary)),
            title: const Text('Take Photo', style: TextStyle(fontWeight: FontWeight.w600)),
            onTap: () => Navigator.pop(context, ImageSource.camera),
          ),
          ListTile(
            leading: Container(padding: const EdgeInsets.all(8),
              decoration: BoxDecoration(color: AppColors.primary.withOpacity(0.1), borderRadius: BorderRadius.circular(10)),
              child: const Icon(Icons.photo_library_rounded, color: AppColors.primary)),
            title: const Text('Choose from Gallery', style: TextStyle(fontWeight: FontWeight.w600)),
            onTap: () => Navigator.pop(context, ImageSource.gallery),
          ),
          const SizedBox(height: 16),
        ]),
      ),
    );
    if (source == null) return;

    final xfile = await picker.pickImage(source: source, imageQuality: 70, maxWidth: 512);
    if (xfile == null) return;

    setState(() => _uploadingPhoto = true);
    try {
      final bytes  = await File(xfile.path).readAsBytes();
      final base64 = 'data:image/jpeg;base64,${base64Encode(bytes)}';
      final dio    = ref.read(dioClientProvider);
      await dio.patch('/profile', data: {'profilePhoto': base64});
      ref.invalidate(profileProvider);
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(const SnackBar(
          content: Text('Profile photo updated!'),
          backgroundColor: AppColors.success,
          behavior: SnackBarBehavior.floating,
        ));
      }
    } catch (_) {
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(const SnackBar(
          content: Text('Failed to upload photo'),
          backgroundColor: AppColors.error,
          behavior: SnackBarBehavior.floating,
        ));
      }
    } finally {
      if (mounted) setState(() => _uploadingPhoto = false);
    }
  }

  @override
  Widget build(BuildContext context) {
    final profileAsync = ref.watch(profileProvider);
    final auth = ref.watch(authProvider);

    return Scaffold(
      backgroundColor: const Color(0xFFF5F6F8),
      appBar: AppBar(
        backgroundColor: AppColors.primary,
        foregroundColor: Colors.white,
        elevation: 0,
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios_new_rounded, size: 18),
          onPressed: () => context.pop(),
        ),
        title: const Text('My Profile',
            style: TextStyle(fontSize: 17, fontWeight: FontWeight.w700)),
        actions: [
          IconButton(
            icon: const Icon(Icons.logout_rounded, size: 20),
            onPressed: () {
              ref.read(authProvider.notifier).logout();
              context.go('/login');
            },
          ),
        ],
      ),
      body: profileAsync.when(
        loading: () => const Center(child: CircularProgressIndicator(color: AppColors.primary)),
        error: (e, _) => Center(
          child: Column(mainAxisSize: MainAxisSize.min, children: [
            const Icon(Icons.error_outline_rounded, color: AppColors.error, size: 48),
            const SizedBox(height: 12),
            Text('Failed to load profile', style: const TextStyle(color: AppColors.error)),
            const SizedBox(height: 12),
            ElevatedButton.icon(
              onPressed: () => ref.invalidate(profileProvider),
              icon: const Icon(Icons.refresh_rounded),
              label: const Text('Retry'),
              style: ElevatedButton.styleFrom(backgroundColor: AppColors.primary, foregroundColor: Colors.white),
            ),
          ]),
        ),
        data: (p) => SingleChildScrollView(
          physics: const BouncingScrollPhysics(),
          child: Column(children: [
            // ── Red header with avatar ─────────────────────────────────────
            Container(
              width: double.infinity,
              decoration: const BoxDecoration(
                gradient: LinearGradient(
                  colors: AppColors.primaryGradient,
                  begin: Alignment.topLeft,
                  end: Alignment.bottomRight,
                ),
              ),
              child: Column(children: [
                const SizedBox(height: 28),

                // Avatar with edit button
                Stack(
                  children: [
                    GestureDetector(
                      onTap: _pickAndUploadPhoto,
                      child: Container(
                        width: 90, height: 90,
                        decoration: BoxDecoration(
                          shape: BoxShape.circle,
                          border: Border.all(color: Colors.white, width: 3),
                          boxShadow: [BoxShadow(color: Colors.black.withOpacity(0.2), blurRadius: 12, offset: const Offset(0, 4))],
                        ),
                        child: ClipOval(
                          child: _buildAvatar(p),
                        ),
                      ),
                    ),
                    Positioned(
                      bottom: 0, right: 0,
                      child: GestureDetector(
                        onTap: _uploadingPhoto ? null : _pickAndUploadPhoto,
                        child: Container(
                          width: 28, height: 28,
                          decoration: BoxDecoration(
                            color: Colors.white,
                            shape: BoxShape.circle,
                            boxShadow: [BoxShadow(color: Colors.black.withOpacity(0.15), blurRadius: 6)],
                          ),
                          child: _uploadingPhoto
                              ? const Padding(
                                  padding: EdgeInsets.all(6),
                                  child: CircularProgressIndicator(strokeWidth: 2, color: AppColors.primary))
                              : const Icon(Icons.camera_alt_rounded, size: 16, color: AppColors.primary),
                        ),
                      ),
                    ),
                  ],
                ),

                const SizedBox(height: 14),
                Text(p.name,
                    style: const TextStyle(fontSize: 20, fontWeight: FontWeight.w800, color: Colors.white, letterSpacing: -0.3)),
                const SizedBox(height: 4),
                Text(p.designation,
                    style: TextStyle(fontSize: 13, color: Colors.white.withOpacity(0.75))),
                const SizedBox(height: 8),

                // Status chip
                Container(
                  padding: const EdgeInsets.symmetric(horizontal: 14, vertical: 5),
                  decoration: BoxDecoration(
                    color: Colors.white.withOpacity(0.18),
                    borderRadius: BorderRadius.circular(20),
                    border: Border.all(color: Colors.white.withOpacity(0.3)),
                  ),
                  child: Row(mainAxisSize: MainAxisSize.min, children: [
                    Container(width: 7, height: 7, decoration: const BoxDecoration(color: AppColors.success, shape: BoxShape.circle)),
                    const SizedBox(width: 6),
                    Text(p.jobStatus, style: const TextStyle(color: Colors.white, fontSize: 12, fontWeight: FontWeight.w600)),
                  ]),
                ),
                const SizedBox(height: 24),

                // Stats row
                Container(
                  margin: const EdgeInsets.fromLTRB(20, 0, 20, 0),
                  padding: const EdgeInsets.symmetric(vertical: 16),
                  decoration: BoxDecoration(
                    color: Colors.white.withOpacity(0.12),
                    borderRadius: BorderRadius.circular(16),
                    border: Border.all(color: Colors.white.withOpacity(0.2)),
                  ),
                  child: Row(children: [
                    _HeroStat(value: '${p.completedVisits}', label: 'Completed'),
                    _Divider(),
                    _HeroStat(value: '${p.totalVisits}', label: 'This Month'),
                    _Divider(),
                    _HeroStat(value: '${p.shiftsWorked}', label: 'Days Worked'),
                  ]),
                ),
                const SizedBox(height: 24),
              ]),
            ),

            // ── Content ────────────────────────────────────────────────────
            Padding(
              padding: const EdgeInsets.all(16),
              child: Column(children: [

                // Contact info
                _Section(title: 'Contact Info', icon: Icons.person_outline_rounded, children: [
                  _InfoRow(icon: Icons.alternate_email_rounded, label: 'Email',  value: p.email),
                  _InfoRow(icon: Icons.phone_rounded,           label: 'Phone',  value: p.phone),
                  _InfoRow(icon: Icons.location_city_rounded,   label: 'City',   value: p.city),
                ]),
                const SizedBox(height: 12),

                // Salary
                _Section(title: 'This Month Earnings', icon: Icons.account_balance_wallet_rounded, children: [
                  _InfoRow(icon: Icons.currency_rupee_rounded, label: 'Basic Salary',  value: '₨${_fmt(p.basicSalary)}'),
                  _InfoRow(icon: Icons.trending_up_rounded,    label: 'Visit Earnings', value: '₨${_fmt(p.runningPay)}'),
                  _InfoRow(icon: Icons.payments_rounded,       label: 'Net Salary',    value: '₨${_fmt(p.netSalary)}',  highlight: true),
                  _InfoRow(icon: Icons.star_rounded,           label: 'Reward Points', value: '${p.rewardPoints} pts'),
                ]),
                const SizedBox(height: 28),
              ]),
            ),
          ]).animate().fadeIn(duration: 400.ms),
        ),
      ),
    );
  }

  Widget _buildAvatar(ProfileData p) {
    if (p.profilePhoto != null && p.profilePhoto!.isNotEmpty) {
      final photo = p.profilePhoto!;
      if (photo.startsWith('data:image')) {
        final bytes = base64Decode(photo.split(',').last);
        return Image.memory(bytes, fit: BoxFit.cover, width: 90, height: 90,
            errorBuilder: (_, __, ___) => _initialsAvatar(p.name));
      }
      return Image.network(photo, fit: BoxFit.cover, width: 90, height: 90,
          errorBuilder: (_, __, ___) => _initialsAvatar(p.name));
    }
    return _initialsAvatar(p.name);
  }

  Widget _initialsAvatar(String name) {
    final initials = name.trim().split(' ').take(2).map((w) => w.isEmpty ? '' : w[0]).join().toUpperCase();
    return Container(
      width: 90, height: 90,
      color: Colors.white.withOpacity(0.2),
      child: Center(
        child: Text(initials, style: const TextStyle(fontSize: 30, fontWeight: FontWeight.w800, color: Colors.white)),
      ),
    );
  }

  String _fmt(double v) => v.toStringAsFixed(0).replaceAllMapped(
      RegExp(r'(\d{1,3})(?=(\d{3})+(?!\d))'), (m) => '${m[1]},');
}

// ── Widgets ───────────────────────────────────────────────────────────────────
class _HeroStat extends StatelessWidget {
  final String value;
  final String label;
  const _HeroStat({required this.value, required this.label});

  @override
  Widget build(BuildContext context) => Expanded(
    child: Column(children: [
      Text(value, style: const TextStyle(fontSize: 22, fontWeight: FontWeight.w800, color: Colors.white, letterSpacing: -0.5)),
      const SizedBox(height: 2),
      Text(label, style: TextStyle(fontSize: 11, color: Colors.white.withOpacity(0.7))),
    ]),
  );
}

class _Divider extends StatelessWidget {
  @override
  Widget build(BuildContext context) => Container(
    width: 1, height: 36,
    color: Colors.white.withOpacity(0.25),
  );
}

class _Section extends StatelessWidget {
  final String title;
  final IconData icon;
  final List<Widget> children;
  const _Section({required this.title, required this.icon, required this.children});

  @override
  Widget build(BuildContext context) => Container(
    decoration: BoxDecoration(
      color: Colors.white,
      borderRadius: BorderRadius.circular(16),
      border: Border.all(color: const Color(0xFFEEF0F2)),
    ),
    child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
      Padding(
        padding: const EdgeInsets.fromLTRB(16, 14, 16, 10),
        child: Row(children: [
          Icon(icon, size: 16, color: AppColors.primary),
          const SizedBox(width: 7),
          Text(title, style: const TextStyle(fontSize: 13, fontWeight: FontWeight.w700, color: Color(0xFF1E293B))),
        ]),
      ),
      const Divider(height: 1, color: Color(0xFFF1F3F5)),
      ...children,
    ]),
  );
}

class _InfoRow extends StatelessWidget {
  final IconData icon;
  final String label;
  final String value;
  final bool highlight;
  const _InfoRow({required this.icon, required this.label, required this.value, this.highlight = false});

  @override
  Widget build(BuildContext context) => Padding(
    padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 12),
    child: Row(children: [
      Container(
        padding: const EdgeInsets.all(7),
        decoration: BoxDecoration(
          color: highlight ? AppColors.primary.withOpacity(0.1) : const Color(0xFFF5F6F8),
          borderRadius: BorderRadius.circular(8),
        ),
        child: Icon(icon, size: 16, color: highlight ? AppColors.primary : const Color(0xFF64748B)),
      ),
      const SizedBox(width: 12),
      Text(label, style: const TextStyle(fontSize: 13, color: Color(0xFF64748B))),
      const Spacer(),
      Text(value, style: TextStyle(
        fontSize: 13,
        fontWeight: highlight ? FontWeight.w800 : FontWeight.w600,
        color: highlight ? AppColors.primary : const Color(0xFF1E293B),
      )),
    ]),
  );
}
