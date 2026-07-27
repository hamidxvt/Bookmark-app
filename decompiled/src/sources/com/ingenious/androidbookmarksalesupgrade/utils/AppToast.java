package com.ingenious.androidbookmarksalesupgrade.utils;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppToast.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\f\u001a\u00020\u00072\b\b\u0001\u0010\n\u001a\u00020\u000bJ\u0006\u0010\r\u001a\u00020\u0007J\u001a\u0010\u000e\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0010\u001a\u00020\u000bH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/utils/AppToast;", "", "<init>", "()V", "mToast", "Landroid/widget/Toast;", "showToast", "", "toastMessage", "", "resId", "", "showLongToast", "showInternetErrorToast", "createToast", TypedValues.Custom.S_STRING, "toastDuration", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final class AppToast {
    public static final AppToast INSTANCE = new AppToast();
    private static Toast mToast;

    private AppToast() {
    }

    public final void showToast(String toastMessage) {
        Intrinsics.checkNotNullParameter(toastMessage, "toastMessage");
        createToast(toastMessage, 0);
    }

    public final void showToast(int resId) {
        createToast(InjectUtils.INSTANCE.getAppContext().getString(resId), 0);
    }

    public final void showLongToast(String toastMessage) {
        Intrinsics.checkNotNullParameter(toastMessage, "toastMessage");
        createToast(toastMessage, 1);
    }

    public final void showLongToast(int resId) {
        createToast(InjectUtils.INSTANCE.getAppContext().getString(resId), 1);
    }

    public final void showInternetErrorToast() {
        createToast(InjectUtils.INSTANCE.getAppContext().getString(R.string.error_msg_no_internet), 1);
    }

    private final void createToast(String string, int toastDuration) {
        Toast toast = mToast;
        if (toast != null) {
            toast.cancel();
        }
        mToast = Toast.makeText(InjectUtils.INSTANCE.getAppContext(), string, toastDuration);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.utils.AppToast$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AppToast.createToast$lambda$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createToast$lambda$0() {
        try {
            Toast toast = mToast;
            if (toast != null) {
                toast.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
