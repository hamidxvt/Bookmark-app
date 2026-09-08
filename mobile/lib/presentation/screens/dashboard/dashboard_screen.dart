import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';

import '../../providers/auth_provider.dart';

class DashboardScreen extends ConsumerWidget {
  const DashboardScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final auth = ref.watch(authProvider);
    final user = auth.user;
    final scheme = Theme.of(context).colorScheme;

    return Scaffold(
      appBar: AppBar(
        title: const Text('Bookmark SFA'),
        actions: [
          IconButton(
            icon: const Icon(Icons.logout),
            onPressed: () async {
              await ref.read(authProvider.notifier).logout();
              if (context.mounted) context.go('/login');
            },
          )
        ],
      ),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          // Welcome banner
          Card(
            color: scheme.primaryContainer,
            child: Padding(
              padding: const EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text('Welcome back,',
                      style: Theme.of(context).textTheme.bodyMedium),
                  Text(user?.name ?? 'Officer',
                      style: Theme.of(context)
                          .textTheme
                          .titleLarge
                          ?.copyWith(fontWeight: FontWeight.bold)),
                  const SizedBox(height: 4),
                  Text('Leave: ${user?.totalLeaveBalance ?? 28} days remaining',
                      style: Theme.of(context).textTheme.bodySmall),
                ],
              ),
            ),
          ),
          const SizedBox(height: 16),

          // Action grid
          GridView.count(
            crossAxisCount: 2,
            shrinkWrap: true,
            physics: const NeverScrollableScrollPhysics(),
            crossAxisSpacing: 12,
            mainAxisSpacing: 12,
            childAspectRatio: 1.2,
            children: [
              _ActionCard(
                icon: Icons.play_circle_outline,
                label: 'Start Day',
                color: Colors.green,
                onTap: () => context.push('/day-start'),
              ),
              _ActionCard(
                icon: Icons.map_outlined,
                label: "Today's Visits",
                color: scheme.primary,
                onTap: () => context.push('/visits'),
              ),
              _ActionCard(
                icon: Icons.book_outlined,
                label: 'Samples',
                color: Colors.orange,
                onTap: () => context.push('/samples'),
              ),
              _ActionCard(
                icon: Icons.beach_access_outlined,
                label: 'Leaves',
                color: Colors.teal,
                onTap: () => context.push('/leaves'),
              ),
              _ActionCard(
                icon: Icons.stop_circle_outlined,
                label: 'End Day',
                color: Colors.red,
                onTap: () => context.push('/day-end'),
              ),
              _ActionCard(
                icon: Icons.person_outline,
                label: 'Profile',
                color: Colors.purple,
                onTap: () => context.push('/profile'),
              ),
            ],
          ),
        ],
      ),
    );
  }
}

class _ActionCard extends StatelessWidget {
  final IconData icon;
  final String label;
  final Color color;
  final VoidCallback onTap;

  const _ActionCard({
    required this.icon,
    required this.label,
    required this.color,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      clipBehavior: Clip.antiAlias,
      child: InkWell(
        onTap: onTap,
        child: Padding(
          padding: const EdgeInsets.all(16),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Icon(icon, size: 36, color: color),
              const SizedBox(height: 8),
              Text(label,
                  textAlign: TextAlign.center,
                  style: Theme.of(context)
                      .textTheme
                      .bodyMedium
                      ?.copyWith(fontWeight: FontWeight.w600)),
            ],
          ),
        ),
      ),
    );
  }
}
