package com.ingenious.androidbookmarksalesupgrade.utils;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReverseGeoCodeUtils.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/utils/LoadDataCallback;", "T", "", "onDataLoaded", "", "response", "(Ljava/lang/Object;)V", "onDataNotAvailable", "errorCode", "", "reasonMsg", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public interface LoadDataCallback<T> {
    void onDataLoaded(T response);

    void onDataNotAvailable(int errorCode, String reasonMsg);

    /* compiled from: ReverseGeoCodeUtils.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        public static <T> void onDataLoaded(LoadDataCallback<T> loadDataCallback, T t) {
        }

        public static <T> void onDataNotAvailable(LoadDataCallback<T> loadDataCallback, int errorCode, String reasonMsg) {
            Intrinsics.checkNotNullParameter(reasonMsg, "reasonMsg");
        }
    }
}
