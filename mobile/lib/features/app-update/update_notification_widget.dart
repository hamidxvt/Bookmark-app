import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../core/theme/app_colors.dart';
import '../../core/theme/app_spacing.dart';
import 'app_update_notifier.dart';

/// Widget that shows an update notification
/// Call this from your main app widget to enable update checking
class UpdateNotificationListener extends ConsumerWidget {
  final Widget child;

  const UpdateNotificationListener({required this.child});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    // Watch for update state changes
    ref.listen(appUpdateProvider, (previous, next) {
      // Show notification if update available and not dismissed
      if (next.availableVersion != null && !next.dismissed) {
        _showUpdateNotification(context, ref, next);
      }
    });

    return child;
  }

  void _showUpdateNotification(
    BuildContext context,
    WidgetRef ref,
    AppUpdateState state,
  ) {
    final isMandatory = state.availableVersion?.isMandatory ?? false;

    if (isMandatory) {
      // Force update: show modal dialog
      showDialog(
        context: context,
        barrierDismissible: false,
        builder: (ctx) => _ForceUpdateDialog(state: state, ref: ref),
      );
    } else {
      // Optional update: show snackbar
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: _UpdateSnackBarContent(state: state),
          backgroundColor: const Color(0xFFF5F5F5),
          elevation: 2,
          behavior: SnackBarBehavior.floating,
          margin: EdgeInsets.all(AppSpacing.md),
          duration: const Duration(seconds: 10),
          action: SnackBarAction(
            label: 'Dismiss',
            textColor: AppColors.primary,
            onPressed: () {
              ref.read(appUpdateProvider.notifier).dismissUpdate();
            },
          ),
        ),
      );
    }
  }
}

/// Snackbar content for optional updates
class _UpdateSnackBarContent extends ConsumerWidget {
  final AppUpdateState state;

  const _UpdateSnackBarContent({required this.state});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Padding(
      padding: EdgeInsets.symmetric(vertical: AppSpacing.sm),
      child: Row(
        children: [
          Expanded(
            child: Column(
              mainAxisSize: MainAxisSize.min,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  'Update Available',
                  style: Theme.of(context).textTheme.titleMedium?.copyWith(
                        fontWeight: FontWeight.bold,
                        color: AppColors.textDark,
                      ),
                ),
                SizedBox(height: AppSpacing.xs),
                Text(
                  'Version ${state.availableVersion?.versionName ?? ''}',
                  style: Theme.of(context).textTheme.bodySmall?.copyWith(
                        color: AppColors.textGrey,
                      ),
                ),
              ],
            ),
          ),
          SizedBox(width: AppSpacing.sm),
          ElevatedButton(
            onPressed: () {
              ScaffoldMessenger.of(context).hideCurrentSnackBar();
              _performUpdate(context, ref);
            },
            style: ElevatedButton.styleFrom(
              backgroundColor: AppColors.primary,
              padding: EdgeInsets.symmetric(
                horizontal: AppSpacing.sm,
                vertical: AppSpacing.xs,
              ),
            ),
            child: const Text('Update', style: TextStyle(color: Colors.white)),
          ),
        ],
      ),
    );
  }

  void _performUpdate(BuildContext context, WidgetRef ref) {
    ref.read(appUpdateProvider.notifier).downloadAndInstall();
  }
}

/// Dialog for mandatory updates (force update)
class _ForceUpdateDialog extends ConsumerWidget {
  final AppUpdateState state;
  final WidgetRef ref;

  const _ForceUpdateDialog({
    required this.state,
    required this.ref,
  });

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    ref.listen(appUpdateProvider, (previous, next) {
      if (next.error != null) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Error: ${next.error}')),
        );
      }
    });

    return AlertDialog(
      title: const Text('Important Update'),
      content: Column(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            'A new version of Bookmark SFA is required.',
            style: Theme.of(context).textTheme.bodyMedium,
          ),
          SizedBox(height: AppSpacing.md),
          Text(
            'Version: ${state.availableVersion?.versionName ?? ''}',
            style: Theme.of(context).textTheme.bodySmall?.copyWith(
                  color: AppColors.textGrey,
                ),
          ),
          if (state.availableVersion?.releaseNotes.isNotEmpty ?? false) ...[
            SizedBox(height: AppSpacing.md),
            Text(
              'What\'s New:',
              style: Theme.of(context).textTheme.labelMedium?.copyWith(
                    fontWeight: FontWeight.bold,
                  ),
            ),
            SizedBox(height: AppSpacing.xs),
            Text(
              state.availableVersion?.releaseNotes ?? '',
              style: Theme.of(context).textTheme.bodySmall,
              maxLines: 4,
              overflow: TextOverflow.ellipsis,
            ),
          ],
          if (state.isDownloading) ...[
            SizedBox(height: AppSpacing.md),
            LinearProgressIndicator(
              value: state.downloadProgress / 100.0,
            ),
            SizedBox(height: AppSpacing.xs),
            Text(
              '${state.downloadProgress}%',
              style: Theme.of(context).textTheme.bodySmall?.copyWith(
                    color: AppColors.textGrey,
                  ),
            ),
          ],
        ],
      ),
      actions: [
        SizedBox(
          width: double.infinity,
          child: ElevatedButton(
            onPressed: state.isDownloading
                ? null
                : () => _performUpdate(context, ref),
            style: ElevatedButton.styleFrom(
              backgroundColor: AppColors.primary,
              disabledBackgroundColor: Colors.grey[300],
            ),
            child: Padding(
              padding: EdgeInsets.symmetric(vertical: AppSpacing.sm),
              child: state.isDownloading
                  ? const SizedBox(
                      height: 20,
                      width: 20,
                      child: CircularProgressIndicator(
                        valueColor: AlwaysStoppedAnimation<Color>(Colors.white),
                        strokeWidth: 2,
                      ),
                    )
                  : const Text(
                      'Update Now',
                      style: TextStyle(color: Colors.white),
                    ),
            ),
          ),
        ),
      ],
    );
  }

  void _performUpdate(BuildContext context, WidgetRef ref) {
    ref.read(appUpdateProvider.notifier).downloadAndInstall();
  }
}
