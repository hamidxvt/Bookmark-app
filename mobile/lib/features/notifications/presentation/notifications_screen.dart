import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import '../../../core/theme/app_theme.dart';

class NotificationsScreen extends StatefulWidget {
  const NotificationsScreen({super.key});

  @override
  State<NotificationsScreen> createState() => _NotificationsScreenState();
}

class _NotificationsScreenState extends State<NotificationsScreen> {
  final List<Map<String, dynamic>> notifications = [
    {
      'title': 'Shift Started',
      'message': 'Your shift has been recorded. Have a productive day!',
      'time': 'Just now',
      'icon': Icons.check_circle,
      'color': AppColors.success,
    },
    {
      'title': 'Visit Completed',
      'message': 'Customer visit has been marked as complete',
      'time': '2 hours ago',
      'icon': Icons.location_on,
      'color': AppColors.primary,
    },
    {
      'title': 'New Assignment',
      'message': 'You have been assigned to 3 new customer visits',
      'time': 'Yesterday',
      'icon': Icons.assignment,
      'color': AppColors.info,
    },
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: AppColors.background,
      appBar: AppBar(
        title: const Text('Notifications'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios_new_rounded),
          onPressed: () => context.pop(),
        ),
        elevation: 0,
      ),
      body: notifications.isEmpty
          ? Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Icon(Icons.notifications_none,
                      size: 64, color: AppColors.onSurface.withOpacity(0.3)),
                  const SizedBox(height: 16),
                  Text(
                    'No Notifications',
                    style: Theme.of(context).textTheme.titleLarge?.copyWith(
                          color: AppColors.onSurface.withOpacity(0.5),
                        ),
                  ),
                ],
              ),
            )
          : ListView.builder(
              padding: const EdgeInsets.all(16),
              itemCount: notifications.length,
              itemBuilder: (context, index) {
                final notif = notifications[index];
                return Padding(
                  padding: const EdgeInsets.only(bottom: 12),
                  child: Container(
                    decoration: BoxDecoration(
                      color: AppColors.surface,
                      borderRadius: BorderRadius.circular(12),
                      border: Border.all(
                        color: AppColors.onSurface.withOpacity(0.1),
                      ),
                    ),
                    child: ListTile(
                      leading: Container(
                        width: 44,
                        height: 44,
                        decoration: BoxDecoration(
                          color: notif['color'].withOpacity(0.15),
                          borderRadius: BorderRadius.circular(12),
                        ),
                        child: Icon(
                          notif['icon'],
                          color: notif['color'],
                        ),
                      ),
                      title: Text(
                        notif['title'],
                        style: const TextStyle(
                          fontWeight: FontWeight.w600,
                          fontSize: 14,
                        ),
                      ),
                      subtitle: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          const SizedBox(height: 4),
                          Text(
                            notif['message'],
                            maxLines: 2,
                            overflow: TextOverflow.ellipsis,
                            style: TextStyle(
                              fontSize: 12,
                              color: AppColors.onSurface.withOpacity(0.7),
                            ),
                          ),
                          const SizedBox(height: 4),
                          Text(
                            notif['time'],
                            style: TextStyle(
                              fontSize: 11,
                              color: AppColors.onSurface.withOpacity(0.5),
                            ),
                          ),
                        ],
                      ),
                      contentPadding: const EdgeInsets.symmetric(
                        horizontal: 16,
                        vertical: 8,
                      ),
                    ),
                  ),
                );
              },
            ),
    );
  }
}
