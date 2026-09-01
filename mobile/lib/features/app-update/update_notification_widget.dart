import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../core/theme/app_theme.dart';
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
                        color: AppColors.onSurface,
                      ),
                ),
                SizedBox(height: AppSpacing.xs),
                Text(
                  'Version ${state.availableVersion?.versionName ?? ''}',
                  style: Theme.of(context).textTheme.bodySmall?.copyWith(
                        color: AppColors.textMuted,
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
          SnackBar(
            content: Text('Error: ${next.error}'),
            backgroundColor: AppColors.error,
          ),
        );
      }
    });

    final isProcessing = state.isDownloading;

    return AlertDialog(
      title: const Text('Important Update'),
      content: SingleChildScrollView(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              'A new version of Bookmark SFA is required.',
              style: Theme.of(context).textTheme.bodyMedium,
            ),
            SizedBox(height: AppSpacing.md),
            Container(
              padding: EdgeInsets.all(AppSpacing.md),
              decoration: BoxDecoration(
                color: AppColors.primary.withOpacity(0.1),
                borderRadius: BorderRadius.circular(8),
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    children: [
                      Icon(Icons.info_rounded, color: AppColors.primary, size: 20),
                      SizedBox(width: AppSpacing.sm),
                      Expanded(
                        child: Text(
                          'Version ${state.availableVersion?.versionName ?? ''}',
                          style: Theme.of(context).textTheme.bodySmall?.copyWith(
                                fontWeight: FontWeight.bold,
                              ),
                        ),
                      ),
                    ],
                  ),
                ],
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
            if (isProcessing) ...[
              SizedBox(height: AppSpacing.lg),
              Container(
                padding: EdgeInsets.all(AppSpacing.md),
                decoration: BoxDecoration(
                  color: Colors.blue.withOpacity(0.05),
                  borderRadius: BorderRadius.circular(8),
                  border: Border.all(color: Colors.blue.withOpacity(0.2)),
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Text(
                          'Downloading update...',
                          style: Theme.of(context).textTheme.bodySmall?.copyWith(
                                fontWeight: FontWeight.w600,
                              ),
                        ),
                        Text(
                          '${state.downloadProgress}%',
                          style: Theme.of(context).textTheme.bodySmall?.copyWith(
                                fontWeight: FontWeight.bold,
                                color: AppColors.primary,
                              ),
                        ),
                      ],
                    ),
                    SizedBox(height: AppSpacing.md),
                    ClipRRect(
                      borderRadius: BorderRadius.circular(4),
                      child: LinearProgressIndicator(
                        value: state.downloadProgress / 100.0,
                        minHeight: 8,
                        backgroundColor: Colors.grey[300],
                        valueColor: AlwaysStoppedAnimation<Color>(AppColors.primary),
                      ),
                    ),
                    SizedBox(height: AppSpacing.sm),
                    Text(
                      'Please keep the app open while updating...',
                      style: Theme.of(context).textTheme.bodySmall?.copyWith(
                            color: AppColors.textMuted,
                            fontStyle: FontStyle.italic,
                          ),
                    ),
                  ],
                ),
              ),
            ],
          ],
        ),
      ),
      actions: [
        SizedBox(
          width: double.infinity,
          child: ElevatedButton(
            onPressed: isProcessing ? null : () => _performUpdate(context, ref),
            style: ElevatedButton.styleFrom(
              backgroundColor: AppColors.primary,
              disabledBackgroundColor: Colors.grey[300],
              padding: EdgeInsets.symmetric(vertical: AppSpacing.md),
            ),
            child: isProcessing
                ? Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      SizedBox(
                        height: 20,
                        width: 20,
                        child: CircularProgressIndicator(
                          valueColor: AlwaysStoppedAnimation<Color>(
                            Colors.grey[700]!,
                          ),
                          strokeWidth: 2,
                        ),
                      ),
                      SizedBox(width: AppSpacing.sm),
                      const Text(
                        'Installing...',
                        style: TextStyle(color: Colors.white, fontSize: 16),
                      ),
                    ],
                  )
                : Text(
                    'Update Now',
                    style: Theme.of(context).textTheme.labelLarge?.copyWith(
                          color: Colors.white,
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
