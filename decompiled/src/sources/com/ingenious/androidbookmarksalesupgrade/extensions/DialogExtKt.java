package com.ingenious.androidbookmarksalesupgrade.extensions;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DialogExt.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001a \u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\t"}, d2 = {"showMaterialDialog", "", "Landroidx/fragment/app/Fragment;", "message", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/ingenious/androidbookmarksalesupgrade/listener/DialogListeners;", "showMaterialAlertDialog", "Landroid/app/Activity;", "app_debug"}, k = 2, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes15.dex */
public final class DialogExtKt {
    public static /* synthetic */ void showMaterialDialog$default(Fragment fragment, String str, DialogListeners dialogListeners, int i, Object obj) {
        if ((i & 2) != 0) {
            dialogListeners = null;
        }
        showMaterialDialog(fragment, str, dialogListeners);
    }

    public static final void showMaterialDialog(Fragment $this$showMaterialDialog, String message, final DialogListeners listener) {
        Intrinsics.checkNotNullParameter($this$showMaterialDialog, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        new MaterialAlertDialogBuilder($this$showMaterialDialog.requireContext()).setMessage((CharSequence) message).setCancelable(false).setPositiveButton((CharSequence) $this$showMaterialDialog.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.extensions.DialogExtKt$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogExtKt.showMaterialDialog$lambda$0(DialogListeners.this, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMaterialDialog$lambda$0(DialogListeners $listener, DialogInterface dialog, int which) {
        if ($listener != null) {
            $listener.onPositionButtonTap(dialog);
        }
    }

    public static /* synthetic */ void showMaterialAlertDialog$default(Fragment fragment, String str, DialogListeners dialogListeners, int i, Object obj) {
        if ((i & 2) != 0) {
            dialogListeners = null;
        }
        showMaterialAlertDialog(fragment, str, dialogListeners);
    }

    public static final void showMaterialAlertDialog(Fragment $this$showMaterialAlertDialog, String message, final DialogListeners listener) {
        Intrinsics.checkNotNullParameter($this$showMaterialAlertDialog, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        AlertDialog dialog = new MaterialAlertDialogBuilder($this$showMaterialAlertDialog.requireContext()).setMessage((CharSequence) message).setCancelable(false).setPositiveButton((CharSequence) $this$showMaterialAlertDialog.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.extensions.DialogExtKt$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogExtKt.showMaterialAlertDialog$lambda$1(DialogListeners.this, dialogInterface, i);
            }
        }).setNegativeButton((CharSequence) $this$showMaterialAlertDialog.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.extensions.DialogExtKt$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogExtKt.showMaterialAlertDialog$lambda$2(DialogListeners.this, dialogInterface, i);
            }
        }).create();
        Intrinsics.checkNotNullExpressionValue(dialog, "create(...)");
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMaterialAlertDialog$lambda$1(DialogListeners $listener, DialogInterface dialog, int which) {
        if ($listener != null) {
            $listener.onPositionButtonTap(dialog);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMaterialAlertDialog$lambda$2(DialogListeners $listener, DialogInterface dialog, int which) {
        if ($listener != null) {
            $listener.onNegativeButtonTap(dialog);
        }
    }

    public static /* synthetic */ void showMaterialDialog$default(Activity activity, String str, DialogListeners dialogListeners, int i, Object obj) {
        if ((i & 2) != 0) {
            dialogListeners = null;
        }
        showMaterialDialog(activity, str, dialogListeners);
    }

    public static final void showMaterialDialog(Activity $this$showMaterialDialog, String message, final DialogListeners listener) {
        Intrinsics.checkNotNullParameter($this$showMaterialDialog, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        View view = $this$showMaterialDialog.getLayoutInflater().inflate(R.layout.dialog_message, (ViewGroup) null);
        TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);
        AlertDialog dialog = new MaterialAlertDialogBuilder($this$showMaterialDialog).setView(view).setPositiveButton((CharSequence) "OK", new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.extensions.DialogExtKt$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogExtKt.showMaterialDialog$lambda$3(DialogListeners.this, dialogInterface, i);
            }
        }).show();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(R.drawable.dialog_background_all);
        }
        Button okButton = dialog.getButton(-1);
        okButton.setTextColor(ContextCompat.getColor($this$showMaterialDialog, android.R.color.white));
        okButton.setTextSize(20.0f);
        okButton.setTypeface(okButton.getTypeface(), 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMaterialDialog$lambda$3(DialogListeners $listener, DialogInterface dialogInterface, int i) {
        if ($listener != null) {
            $listener.onPositionButtonTap(dialogInterface);
        }
    }
}
