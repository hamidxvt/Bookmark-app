import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:flutter_animate/flutter_animate.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';

import '../../../core/theme/app_theme.dart';
import '../../../core/network/dio_client.dart';
import '../../../core/constants/api_constants.dart';
import '../../auth/presentation/auth_notifier.dart';

// ── Data models ───────────────────────────────────────────────────────────────
class ProfileData {
  final String name;
  final String email;
  final String phone;
  final String city;
  final String jobStatus;
  final String designation;
  final int totalVisitsThisMonth;
  final int completedVisits;
  final int missedVisits;
  final double basicSalary;
  final double netSalary;
  final double runningPay;
  final int rewardPoints;
  final int shiftsWorked;

  const ProfileData({
    required this.name,
    required this.email,
    required this.phone,
    required this.city,
    required this.jobStatus,
    required this.designation,
    required this.totalVisitsThisMonth,
    required this.completedVisits,
    required this.missedVisits,
    required this.basicSalary,
    required this.netSalary,
    required this.runningPay,
    required this.rewardPoints,
    required this.shiftsWorked,
  });

  factory ProfileData.fromJson(Map<String, dynamic> j) {
    final payroll = j['payroll'] as Map<String, dynamic>? ?? {};
    return ProfileData(
      name: j['name'] ?? '',
      email: j['email'] ?? '',
      phone: j['phone'] ?? '',
      city: j['city'] ?? 'N/A',
      jobStatus: j['jobStatus'] ?? 'ACTIVE',
      designation: j['designation'] ?? j['role'] ?? 'Sales Officer',
      totalVisitsThisMonth: (j['totalVisitsThisMonth'] ?? 0) as int,
      completedVisits: (j['completedVisits'] ?? 0) as int,
      missedVisits: (j['missedVisits'] ?? 0) as int,
      basicSalary: (payroll['basicSalary'] ?? 0).toDouble(),
      netSalary: (payroll['netSalary'] ?? 0).toDouble(),
      runningPay: (payroll['runningPay'] ?? 0).toDouble(),
      rewardPoints: (payroll['rewardPoints'] ?? 0) as int,
      shiftsWorked: (payroll['shiftsWorked'] ?? 0) as int,
    );
  }
}

final profileProvider = FutureProvider.autoDispose<ProfileData>((ref) async {
  final dio = ref.watch(dioClientProvider);
  final res = await dio.get('/profile');
  return ProfileData.fromJson(res.data as Map<String, dynamic>);
});

// ── Screen ────────────────────────────────────────────────────────────────────
class ProfileScreen extends ConsumerWidget {
  const ProfileScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final profileAsync = ref.watch(profileProvider);
    final auth = ref.watch(authProvider);

    return Scaffold(
      backgroundColor: const Color(0xFFFFF5F5),
      body: CustomScrollView(
        slivers: [
          // ── Hero ──────────────────────────────────────────────────────
          SliverAppBar(
            expandedHeight: 220,
            pinned: true,
            backgroundColor: const Color(0xFFC8102E),
            leading: IconButton(
              icon: const Icon(Icons.arrow_back_ios_new_rounded, color: Colors.white),
              onPressed: () => context.go('/dashboard'),
            ),
            flexibleSpace: FlexibleSpaceBar(
              background: profileAsync.when(
                data: (p) => _ProfileHero(profile: p),
                loading: () => _ProfileHeroLoading(),
                error: (_, __) => _ProfileHeroLoading(),
              ),
            ),
          ),

          // ── Content ───────────────────────────────────────────────────
          SliverPadding(
            padding: const EdgeInsets.fromLTRB(16, 20, 16, 40),
            sliver: SliverList(
              delegate: SliverChildListDelegate(
                profileAsync.when(
                  loading: () => [const Center(child: Padding(
                    padding: EdgeInsets.all(40),
                    child: CircularProgressIndicator(color: AppColors.primary),
                  ))],
                  error: (e, _) => [
                    Center(child: Column(children: [
                      const SizedBox(height: 40),
                      const Icon(Icons.error_outline_rounded, color: Colors.grey, size: 48),
                      const SizedBox(height: 12),
                      Text(e.toString(), style: const TextStyle(color: Colors.grey)),
                    ])),
                  ],
                  data: (p) => [
                    // ── Visit Stats ────────────────────────────────────
                    _SectionLabel('Visit Statistics'),
                    const SizedBox(height: 10),
                    Row(children: [
                      Expanded(child: _StatTile(
                        label: 'Total', value: '${p.totalVisitsThisMonth}',
                        icon: Icons.route_rounded, color: AppColors.info,
                      ).animate(delay: 50.ms).slideX(begin: -0.1).fadeIn()),
                      const SizedBox(width: 10),
                      Expanded(child: _StatTile(
                        label: 'Completed', value: '${p.completedVisits}',
                        icon: Icons.check_circle_rounded, color: AppColors.success,
                      ).animate(delay: 100.ms).slideX(begin: 0.1).fadeIn()),
                    ]),
                    const SizedBox(height: 10),
                    Row(children: [
                      Expanded(child: _StatTile(
                        label: 'Missed', value: '${p.missedVisits}',
                        icon: Icons.cancel_rounded, color: AppColors.error,
                      ).animate(delay: 150.ms).slideX(begin: -0.1).fadeIn()),
                      const SizedBox(width: 10),
                      Expanded(child: _StatTile(
                        label: 'Shifts', value: '${p.shiftsWorked}',
                        icon: Icons.calendar_today_rounded, color: AppColors.secondary,
                      ).animate(delay: 200.ms).slideX(begin: 0.1).fadeIn()),
                    ]),
                    const SizedBox(height: 20),

                    // ── Salary Breakdown ───────────────────────────────
                    _SectionLabel('Salary Breakdown'),
                    const SizedBox(height: 10),
                    _SalaryCard(profile: p).animate(delay: 100.ms).slideY(begin: 0.1).fadeIn(),
                    const SizedBox(height: 20),

                    // ── Reward Points ──────────────────────────────────
                    if (p.rewardPoints > 0) ...[
                      _RewardCard(points: p.rewardPoints).animate(delay: 150.ms).slideY(begin: 0.1).fadeIn(),
                      const SizedBox(height: 20),
                    ],

                    // ── Logout ─────────────────────────────────────────
                    SizedBox(
                      width: double.infinity,
                      child: OutlinedButton.icon(
                        onPressed: () {
                          ref.read(authProvider.notifier).logout();
                          context.go('/login');
                        },
                        icon: const Icon(Icons.logout_rounded, color: Colors.red),
                        label: const Text('Sign Out', style: TextStyle(color: Colors.red)),
                        style: OutlinedButton.styleFrom(
                          side: const BorderSide(color: Colors.redAccent, width: 0.8),
                          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
                          padding: const EdgeInsets.symmetric(vertical: 14),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}

// ── Hero header ───────────────────────────────────────────────────────────────
class _ProfileHero extends StatelessWidget {
  final ProfileData profile;
  const _ProfileHero({required this.profile});

  @override
  Widget build(BuildContext context) {
    final initials = profile.name.trim().split(' ').take(2).map((p) => p[0]).join().toUpperCase();
    return Container(
      decoration: const BoxDecoration(
        gradient: LinearGradient(
          colors: [Color(0xFFC8102E), Color(0xFF9B0B22)],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
      ),
      child: SafeArea(
        child: Padding(
          padding: const EdgeInsets.fromLTRB(20, 20, 20, 16),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.end,
            children: [
              Container(
                width: 72,
                height: 72,
                decoration: BoxDecoration(
                  shape: BoxShape.circle,
                  color: Colors.white.withOpacity(0.15),
                  border: Border.all(color: Colors.white.withOpacity(0.4), width: 2.5),
                ),
                child: Center(
                  child: Text(initials,
                      style: const TextStyle(color: Colors.white, fontSize: 26, fontWeight: FontWeight.w800)),
                ),
              ),
              const SizedBox(height: 12),
              Text(profile.name,
                  style: const TextStyle(color: Colors.white, fontSize: 18, fontWeight: FontWeight.w700)),
              const SizedBox(height: 4),
              Text(profile.designation,
                  style: const TextStyle(color: Colors.white70, fontSize: 13, fontWeight: FontWeight.w500)),
              const SizedBox(height: 6),
              Row(mainAxisAlignment: MainAxisAlignment.center, children: [
                const Icon(Icons.email_outlined, color: Colors.white60, size: 13),
                const SizedBox(width: 4),
                Text(profile.email,
                    style: const TextStyle(color: Colors.white70, fontSize: 12)),
                const SizedBox(width: 12),
                const Icon(Icons.location_on_rounded, color: Colors.white60, size: 13),
                const SizedBox(width: 4),
                Text(profile.city,
                    style: const TextStyle(color: Colors.white70, fontSize: 12)),
              ]),
              const SizedBox(height: 6),
              Container(
                padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 3),
                decoration: BoxDecoration(
                  color: Colors.white.withOpacity(0.2),
                  borderRadius: BorderRadius.circular(20),
                ),
                child: Text(profile.jobStatus,
                    style: const TextStyle(color: Colors.white, fontSize: 11, fontWeight: FontWeight.w600)),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class _ProfileHeroLoading extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: const BoxDecoration(
        gradient: LinearGradient(
          colors: AppColors.primaryGradient,
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
      ),
    );
  }
}

// ── Salary card ───────────────────────────────────────────────────────────────
class _SalaryCard extends StatelessWidget {
  final ProfileData profile;
  const _SalaryCard({required this.profile});

  @override
  Widget build(BuildContext context) {
    final fmt = (double v) => '₨${v.toStringAsFixed(0).replaceAllMapped(
          RegExp(r'(\d{1,3})(?=(\d{3})+(?!\d))'),
          (m) => '${m[1]},',
        )}';

    return Container(
      padding: const EdgeInsets.all(18),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(16),
        border: Border.all(color: const Color(0xFFE2E8F0), width: 0.8),
        boxShadow: [
          BoxShadow(
            color: AppColors.primary.withOpacity(0.06),
            blurRadius: 12,
            offset: const Offset(0, 4),
          ),
        ],
      ),
      child: Column(children: [
        _SalaryRow(label: 'Basic Salary', value: fmt(profile.basicSalary), isHighlight: false),
        const Divider(height: 20, color: Color(0xFFF1F5F9)),
        _SalaryRow(label: 'Running Pay', value: fmt(profile.runningPay), isHighlight: false),
        const SizedBox(height: 8),
        _SalaryRow(label: 'Net Payable', value: fmt(profile.netSalary), isHighlight: true),
      ]),
    );
  }
}

class _SalaryRow extends StatelessWidget {
  final String label;
  final String value;
  final bool isHighlight;

  const _SalaryRow({required this.label, required this.value, required this.isHighlight});

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Text(label,
            style: TextStyle(
              fontSize: 13.5,
              color: isHighlight ? AppColors.primary : const Color(0xFF64748B),
              fontWeight: isHighlight ? FontWeight.w700 : FontWeight.w500,
            )),
        Text(value,
            style: TextStyle(
              fontSize: isHighlight ? 17 : 13.5,
              fontWeight: isHighlight ? FontWeight.w800 : FontWeight.w600,
              color: isHighlight ? AppColors.primary : const Color(0xFF1E293B),
            )),
      ],
    );
  }
}

// ── Reward card ───────────────────────────────────────────────────────────────
class _RewardCard extends StatelessWidget {
  final int points;
  const _RewardCard({required this.points});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        gradient: LinearGradient(
          colors: [
            const Color(0xFFFBBF24).withOpacity(0.15),
            const Color(0xFFF59E0B).withOpacity(0.05),
          ],
        ),
        borderRadius: BorderRadius.circular(16),
        border: Border.all(color: const Color(0xFFFBBF24).withOpacity(0.4)),
      ),
      child: Row(children: [
        Container(
          padding: const EdgeInsets.all(10),
          decoration: BoxDecoration(
            color: const Color(0xFFFBBF24).withOpacity(0.2),
            shape: BoxShape.circle,
          ),
          child: const Icon(Icons.emoji_events_rounded, color: Color(0xFFF59E0B), size: 24),
        ),
        const SizedBox(width: 14),
        Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
          const Text('Reward Points',
              style: TextStyle(fontSize: 13, color: Color(0xFF92400E), fontWeight: FontWeight.w600)),
          Text('$points pts earned',
              style: const TextStyle(fontSize: 20, fontWeight: FontWeight.w800, color: Color(0xFFD97706))),
        ]),
      ]),
    );
  }
}

// ── Helpers ───────────────────────────────────────────────────────────────────
class _SectionLabel extends StatelessWidget {
  final String text;
  const _SectionLabel(this.text);

  @override
  Widget build(BuildContext context) {
    return Text(text,
        style: const TextStyle(
          fontSize: 15,
          fontWeight: FontWeight.w700,
          color: Color(0xFF1E293B),
          letterSpacing: -0.2,
        ));
  }
}

class _StatTile extends StatelessWidget {
  final String label;
  final String value;
  final IconData icon;
  final Color color;

  const _StatTile({
    required this.label,
    required this.value,
    required this.icon,
    required this.color,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(14),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(14),
        border: Border.all(color: const Color(0xFFE2E8F0), width: 0.8),
        boxShadow: [
          BoxShadow(
            color: color.withOpacity(0.06),
            blurRadius: 8,
            offset: const Offset(0, 2),
          ),
        ],
      ),
      child: Row(children: [
        Container(
          padding: const EdgeInsets.all(8),
          decoration: BoxDecoration(
            color: color.withOpacity(0.1),
            borderRadius: BorderRadius.circular(8),
          ),
          child: Icon(icon, color: color, size: 18),
        ),
        const SizedBox(width: 10),
        Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
          Text(value,
              style: TextStyle(
                fontSize: 20,
                fontWeight: FontWeight.w800,
                color: color,
                letterSpacing: -0.5,
              )),
          Text(label,
              style: const TextStyle(fontSize: 10.5, color: Color(0xFF94A3B8), fontWeight: FontWeight.w500)),
        ]),
      ]),
    );
  }
}
