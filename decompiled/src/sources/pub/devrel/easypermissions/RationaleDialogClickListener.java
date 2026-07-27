package pub.devrel.easypermissions;

import android.app.Activity;
import android.content.DialogInterface;
import androidx.fragment.app.Fragment;
import java.util.Arrays;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.helper.PermissionHelper;

/* loaded from: classes17.dex */
class RationaleDialogClickListener implements DialogInterface.OnClickListener {
    private EasyPermissions.PermissionCallbacks mCallbacks;
    private RationaleDialogConfig mConfig;
    private Object mHost;
    private EasyPermissions.RationaleCallbacks mRationaleCallbacks;

    RationaleDialogClickListener(RationaleDialogFragmentCompat compatDialogFragment, RationaleDialogConfig config, EasyPermissions.PermissionCallbacks callbacks, EasyPermissions.RationaleCallbacks rationaleCallbacks) {
        Object activity;
        if (compatDialogFragment.getParentFragment() != null) {
            activity = compatDialogFragment.getParentFragment();
        } else {
            activity = compatDialogFragment.getActivity();
        }
        this.mHost = activity;
        this.mConfig = config;
        this.mCallbacks = callbacks;
        this.mRationaleCallbacks = rationaleCallbacks;
    }

    RationaleDialogClickListener(RationaleDialogFragment dialogFragment, RationaleDialogConfig config, EasyPermissions.PermissionCallbacks callbacks, EasyPermissions.RationaleCallbacks dialogCallback) {
        this.mHost = dialogFragment.getActivity();
        this.mConfig = config;
        this.mCallbacks = callbacks;
        this.mRationaleCallbacks = dialogCallback;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        int requestCode = this.mConfig.requestCode;
        if (which == -1) {
            String[] permissions = this.mConfig.permissions;
            if (this.mRationaleCallbacks != null) {
                this.mRationaleCallbacks.onRationaleAccepted(requestCode);
            }
            if (this.mHost instanceof Fragment) {
                PermissionHelper.newInstance((Fragment) this.mHost).directRequestPermissions(requestCode, permissions);
                return;
            } else {
                if (this.mHost instanceof Activity) {
                    PermissionHelper.newInstance((Activity) this.mHost).directRequestPermissions(requestCode, permissions);
                    return;
                }
                throw new RuntimeException("Host must be an Activity or Fragment!");
            }
        }
        if (this.mRationaleCallbacks != null) {
            this.mRationaleCallbacks.onRationaleDenied(requestCode);
        }
        notifyPermissionDenied();
    }

    private void notifyPermissionDenied() {
        if (this.mCallbacks != null) {
            this.mCallbacks.onPermissionsDenied(this.mConfig.requestCode, Arrays.asList(this.mConfig.permissions));
        }
    }
}
