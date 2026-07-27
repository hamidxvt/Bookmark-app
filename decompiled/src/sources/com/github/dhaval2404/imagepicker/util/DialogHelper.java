package com.github.dhaval2404.imagepicker.util;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import com.github.dhaval2404.imagepicker.R;
import com.github.dhaval2404.imagepicker.constant.ImageProvider;
import com.github.dhaval2404.imagepicker.listener.DismissListener;
import com.github.dhaval2404.imagepicker.listener.ResultListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DialogHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b¨\u0006\f"}, d2 = {"Lcom/github/dhaval2404/imagepicker/util/DialogHelper;", "", "()V", "showChooseAppDialog", "", "context", "Landroid/content/Context;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/github/dhaval2404/imagepicker/listener/ResultListener;", "Lcom/github/dhaval2404/imagepicker/constant/ImageProvider;", "dismissListener", "Lcom/github/dhaval2404/imagepicker/listener/DismissListener;", "imagepicker_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class DialogHelper {
    public static final DialogHelper INSTANCE = new DialogHelper();

    private DialogHelper() {
    }

    public final void showChooseAppDialog(Context context, final ResultListener<ImageProvider> listener, final DismissListener dismissListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View customView = layoutInflater.inflate(R.layout.dialog_choose_app, (ViewGroup) null);
        final AlertDialog dialog = new AlertDialog.Builder(context).setTitle(R.string.title_choose_image_provider).setView(customView).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.github.dhaval2404.imagepicker.util.DialogHelper$showChooseAppDialog$dialog$1
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface it) {
                ResultListener.this.onResult(null);
            }
        }).setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() { // from class: com.github.dhaval2404.imagepicker.util.DialogHelper$showChooseAppDialog$dialog$2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface $noName_0, int $noName_1) {
                ResultListener.this.onResult(null);
            }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.github.dhaval2404.imagepicker.util.DialogHelper$showChooseAppDialog$dialog$3
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface it) {
                DismissListener dismissListener2 = DismissListener.this;
                if (dismissListener2 != null) {
                    dismissListener2.onDismiss();
                }
            }
        }).show();
        customView.findViewById(R.id.lytCameraPick).setOnClickListener(new View.OnClickListener() { // from class: com.github.dhaval2404.imagepicker.util.DialogHelper$showChooseAppDialog$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                ResultListener.this.onResult(ImageProvider.CAMERA);
                dialog.dismiss();
            }
        });
        customView.findViewById(R.id.lytGalleryPick).setOnClickListener(new View.OnClickListener() { // from class: com.github.dhaval2404.imagepicker.util.DialogHelper$showChooseAppDialog$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                ResultListener.this.onResult(ImageProvider.GALLERY);
                dialog.dismiss();
            }
        });
    }
}
