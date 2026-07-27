package com.ingenious.androidbookmarksalesupgrade.listener;

import android.content.DialogInterface;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: DialogListeners.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0007"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/listener/DialogListeners;", "", "onPositionButtonTap", "", "dialog", "Landroid/content/DialogInterface;", "onNegativeButtonTap", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes12.dex */
public interface DialogListeners {
    void onNegativeButtonTap(DialogInterface dialog);

    void onPositionButtonTap(DialogInterface dialog);

    /* compiled from: DialogListeners.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        public static void onPositionButtonTap(DialogListeners $this, DialogInterface dialog) {
        }

        public static void onNegativeButtonTap(DialogListeners $this, DialogInterface dialog) {
        }
    }
}
