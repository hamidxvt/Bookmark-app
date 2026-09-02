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
import '../../app-update/app_update_notifier.dart';

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

    return Scaffold(
      backgroundColor: AppColors.background,
      body: SafeArea(
        child: profileAsync.when(
          loading: () => const Center(child: CircularProgressIndicator(color: AppColors.primary)),
          error: (e, _) => Center(
            child: Column(mainAxisSize: MainAxisSize.min, children: [
              Container(
                width: 64, height: 64,
                decoration: BoxDecoration(color: AppColors.missed.withOpacity(0.1), shape: BoxShape.circle),
                child: const Icon(Icons.error_outline_rounded, color: AppColors.missed, size: 32),
              ),
              const SizedBox(height: 16),
              const Text('Failed to load profile',
                  style: TextStyle(fontSize: 15, fontWeight: FontWeight.w700, color: AppColors.onSurface)),
              const SizedBox(height: 20),
              GestureDetector(
                onTap: () => ref.invalidate(profileProvider),
                child: Container(
                  padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 12),
                  decoration: BoxDecoration(color: AppColors.primary, borderRadius: BorderRadius.circular(AppRadius.lg)),
                  child: const Text('Retry', style: TextStyle(color: Colors.white, fontWeight: FontWeight.w800)),
                ),
              ),
            ]),
          ),
          data: (p) => SingleChildScrollView(
            physics: const BouncingScrollPhysics(),
            padding: const EdgeInsets.fromLTRB(20, 16, 20, 100),
            child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [

              // ── Header row ───────────────────────────────────────────────
              const Row(children: [
                Expanded(
                  child: Text('My Profile',
                      style: TextStyle(fontSize: 22, fontWeight: FontWeight.w800, color: AppColors.onSurface)),
                ),
              ]),

              const SizedBox(height: 20),

              // ── User Card ─────────────────────────────────────────────────
              Container(
                padding: const EdgeInsets.all(16),
                decoration: BoxDecoration(
                  color: AppColors.card,
                  borderRadius: BorderRadius.circular(AppRadius.xxl),
                  border: Border.all(color: AppColors.outline),
                  boxShadow: [
                    BoxShadow(color: Colors.black.withOpacity(0.04), blurRadius: 10, offset: const Offset(0, 3))
                  ],
                ),
                child: Row(children: [
                  GestureDetector(
                    onTap: _pickAndUploadPhoto,
                    child: Stack(
                      clipBehavior: Clip.none,
                      children: [
                        Container(
                          width: 56, height: 56,
                          decoration: BoxDecoration(
                            color: AppColors.navy,
                            borderRadius: BorderRadius.circular(20),
                          ),
                          child: ClipRRect(
                            borderRadius: BorderRadius.circular(20),
                            child: _buildAvatar(p),
                          ),
                        ),
                        Positioned(
                          bottom: -2, right: -2,
                          child: Container(
                            width: 22, height: 22,
                            decoration: BoxDecoration(
                              color: Colors.white,
                              shape: BoxShape.circle,
                              border: Border.all(color: AppColors.outline, width: 1.5),
                            ),
                            child: _uploadingPhoto
                                ? const Padding(padding: EdgeInsets.all(5),
                                    child: CircularProgressIndicator(strokeWidth: 1.5, color: AppColors.primary))
                                : const Icon(Icons.camera_alt_rounded, size: 13, color: AppColors.primary),
                          ),
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(width: 14),
                  Expanded(
                    child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
                      Text(p.name,
                          style: const TextStyle(fontSize: 16.5, fontWeight: FontWeight.w800, color: AppColors.onSurface),
                          maxLines: 1, overflow: TextOverflow.ellipsis),
                      const SizedBox(height: 2),
                      Text('${p.designation} · EMP-${p.id}',
                          style: const TextStyle(fontSize: 12.5, fontWeight: FontWeight.w600, color: AppColors.textSecondary)),
                      const SizedBox(height: 6),
                      Container(
                        padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 3),
                        decoration: BoxDecoration(
                          color: AppColors.primary.withOpacity(0.1),
                          borderRadius: BorderRadius.circular(AppRadius.full),
                        ),
                        child: Text(p.city,
                            style: const TextStyle(fontSize: 11, fontWeight: FontWeight.w700,
                                color: AppColors.primary, letterSpacing: 0.3)),
                      ),
                    ]),
                  ),
                ]),
              ),

              const SizedBox(height: 14),

              // ── Stats Grid ────────────────────────────────────────────────
              Row(children: [
                Expanded(child: _StatCard(value: '${p.completedVisits}', label: 'Visits')),
                const SizedBox(width: 10),
                Expanded(child: _StatCard(value: '${p.shiftsWorked}', label: 'Days')),
                const SizedBox(width: 10),
                Expanded(child: _StatCard(
                  value: p.totalVisits > 0
                      ? '${(p.completedVisits / p.totalVisits * 100).round()}%'
                      : '—',
                  label: 'Target',
                )),
              ]),

              const SizedBox(height: 20),

              // ── Contact Info Card ─────────────────────────────────────────
              _MenuCard(children: [
                _MenuRow(icon: Icons.alternate_email_rounded, label: 'Email', value: p.email),
                _MenuRow(icon: Icons.phone_rounded, label: 'Phone', value: p.phone),
                _MenuRow(icon: Icons.location_city_rounded, label: 'City', value: p.city),
              ]),

              const SizedBox(height: 12),

              // ── Earnings Card ─────────────────────────────────────────────
              _MenuCard(children: [
                _MenuRow(icon: Icons.account_balance_wallet_outlined, label: 'Earnings', value: '',
                    onTap: () => context.push('/payroll')),
                _MenuRow(icon: Icons.calendar_month_outlined, label: 'Attendance & Leave', value: '',
                    onTap: () => context.push('/leaves')),
                _MenuRow(icon: Icons.science_outlined, label: 'Sample Requests', value: '',
                    onTap: () => context.push('/samples')),
              ]),

              const SizedBox(height: 12),

              // ── App Update Row ────────────────────────────────────────────
              Consumer(builder: (ctx, ref, _) {
                final updateState = ref.watch(appUpdateProvider);
                return _MenuCard(children: [
                  _MenuRow(
                    icon: Icons.system_update_rounded,
                    label: 'App Update',
                    value: updateState.availableVersion != null
                        ? 'v${updateState.availableVersion!.versionName} available'
                        : 'Up to date',
                    valueColor: updateState.availableVersion != null ? AppColors.primary : null,
                    trailing: updateState.isDownloading
                        ? SizedBox(width: 16, height: 16,
                            child: CircularProgressIndicator(
                                strokeWidth: 2, value: updateState.downloadProgress / 100.0,
                                color: AppColors.primary))
                        : null,
                    onTap: () {
                      if (updateState.availableVersion != null && !updateState.isDownloading) {
                        ref.read(appUpdateProvider.notifier).downloadAndInstall();
                      } else {
                        ref.read(appUpdateProvider.notifier).checkForUpdates();
                      }
                    },
                  ),
                ]);
              }),

              const SizedBox(height: 20),

              // ── Sign Out ──────────────────────────────────────────────────
              GestureDetector(
                onTap: () {
                  ref.read(authProvider.notifier).logout();
                  context.go('/login');
                },
                child: Container(
                  height: 56,
                  decoration: BoxDecoration(
                    color: AppColors.card,
                    borderRadius: BorderRadius.circular(AppRadius.xl),
                    border: Border.all(color: AppColors.outline),
                  ),
                  child: const Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Icon(Icons.logout_rounded, size: 18, color: AppColors.primary),
                      SizedBox(width: 8),
                      Text('Sign Out',
                          style: TextStyle(fontSize: 15, fontWeight: FontWeight.w800, color: AppColors.primary)),
                    ],
                  ),
                ),
              ),
            ]).animate().fadeIn(duration: 400.ms),
          ),
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

// ── New UI Widgets ─────────────────────────────────────────────────────────────

class _StatCard extends StatelessWidget {
  final String value;
  final String label;
  const _StatCard({required this.value, required this.label});

  @override
  Widget build(BuildContext context) => Container(
    padding: const EdgeInsets.symmetric(vertical: 14),
    decoration: BoxDecoration(
      color: AppColors.card,
      borderRadius: BorderRadius.circular(AppRadius.xl),
      border: Border.all(color: AppColors.outline),
      boxShadow: [BoxShadow(color: Colors.black.withOpacity(0.04), blurRadius: 8, offset: const Offset(0, 2))],
    ),
    child: Column(children: [
      Text(value, style: const TextStyle(fontSize: 18, fontWeight: FontWeight.w800, color: AppColors.onSurface, letterSpacing: -0.5)),
      const SizedBox(height: 3),
      Text(label, style: const TextStyle(fontSize: 11.5, fontWeight: FontWeight.w600, color: AppColors.textSecondary)),
    ]),
  );
}

class _MenuCard extends StatelessWidget {
  final List<Widget> children;
  const _MenuCard({required this.children});

  @override
  Widget build(BuildContext context) => Container(
    decoration: BoxDecoration(
      color: AppColors.card,
      borderRadius: BorderRadius.circular(AppRadius.xl),
      border: Border.all(color: AppColors.outline),
      boxShadow: [BoxShadow(color: Colors.black.withOpacity(0.04), blurRadius: 8, offset: const Offset(0, 2))],
    ),
    child: Column(
      children: children.asMap().entries.map((e) {
        final isLast = e.key == children.length - 1;
        return Column(
          children: [
            e.value,
            if (!isLast) const Divider(height: 1, color: AppColors.outline),
          ],
        );
      }).toList(),
    ),
  );
}

class _MenuRow extends StatelessWidget {
  final IconData icon;
  final String label;
  final String value;
  final Color? valueColor;
  final Widget? trailing;
  final VoidCallback? onTap;
  const _MenuRow({
    required this.icon,
    required this.label,
    this.value = '',
    this.valueColor,
    this.trailing,
    this.onTap,
  });

  @override
  Widget build(BuildContext context) => GestureDetector(
    onTap: onTap,
    child: Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 14),
      child: Row(children: [
        Container(
          width: 40, height: 40,
          decoration: BoxDecoration(
            color: AppColors.background,
            borderRadius: BorderRadius.circular(13),
          ),
          child: Icon(icon, size: 18, color: AppColors.onSurface),
        ),
        const SizedBox(width: 12),
        Expanded(
          child: Text(label,
              style: const TextStyle(fontSize: 14, fontWeight: FontWeight.w800, color: AppColors.onSurface)),
        ),
        if (value.isNotEmpty)
          Text(value,
              style: TextStyle(fontSize: 12, fontWeight: FontWeight.w600,
                  color: valueColor ?? AppColors.textSecondary)),
        if (trailing != null) ...[const SizedBox(width: 8), trailing!],
        if (onTap != null) ...[
          const SizedBox(width: 6),
          const Icon(Icons.chevron_right_rounded, size: 18, color: AppColors.textMuted),
        ],
      ]),
    ),
  );
}
