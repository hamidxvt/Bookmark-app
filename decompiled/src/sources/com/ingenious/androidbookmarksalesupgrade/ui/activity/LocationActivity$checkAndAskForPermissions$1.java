package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.content.DialogInterface;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import kotlin.Metadata;

/* compiled from: LocationActivity.kt */
@Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u001c\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/activity/LocationActivity$checkAndAskForPermissions$1", "Lcom/karumi/dexter/listener/single/PermissionListener;", "onPermissionGranted", "", "p0", "Lcom/karumi/dexter/listener/PermissionGrantedResponse;", "onPermissionRationaleShouldBeShown", "Lcom/karumi/dexter/listener/PermissionRequest;", "p1", "Lcom/karumi/dexter/PermissionToken;", "onPermissionDenied", "response", "Lcom/karumi/dexter/listener/PermissionDeniedResponse;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class LocationActivity$checkAndAskForPermissions$1 implements PermissionListener {
    final /* synthetic */ LocationActivity this$0;

    LocationActivity$checkAndAskForPermissions$1(LocationActivity $receiver) {
        this.this$0 = $receiver;
    }

    @Override // com.karumi.dexter.listener.single.PermissionListener
    public void onPermissionGranted(PermissionGrantedResponse p0) {
        this.this$0.getLastLocation();
    }

    @Override // com.karumi.dexter.listener.single.PermissionListener
    public void onPermissionRationaleShouldBeShown(PermissionRequest p0, PermissionToken p1) {
        if (p1 != null) {
            p1.continuePermissionRequest();
        }
    }

    @Override // com.karumi.dexter.listener.single.PermissionListener
    public void onPermissionDenied(PermissionDeniedResponse response) {
        if (response != null) {
            final LocationActivity locationActivity = this.this$0;
            if (response.isPermanentlyDenied()) {
                new MaterialAlertDialogBuilder(locationActivity).setMessage((CharSequence) "Requires location permission to fetch your location").setCancelable(false).setPositiveButton((CharSequence) "Ok", new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LocationActivity$checkAndAskForPermissions$1$$ExternalSyntheticLambda0
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        LocationActivity$checkAndAskForPermissions$1.onPermissionDenied$lambda$1$lambda$0(LocationActivity.this, dialogInterface, i);
                    }
                }).show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onPermissionDenied$lambda$1$lambda$0(LocationActivity this$0, DialogInterface dialog, int which) {
        dialog.dismiss();
        ActivityExtKt.openSettingForGPS(this$0);
    }
}
