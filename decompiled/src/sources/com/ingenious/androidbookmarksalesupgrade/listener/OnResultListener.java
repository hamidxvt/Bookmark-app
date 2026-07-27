package com.ingenious.androidbookmarksalesupgrade.listener;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.model.response.GlobalResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecyclerViewListener.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/listener/OnResultListener;", "", "onCartAddResult", "", "response", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes12.dex */
public interface OnResultListener {
    void onCartAddResult(GlobalResponse response);

    /* compiled from: RecyclerViewListener.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        public static void onCartAddResult(OnResultListener $this, GlobalResponse response) {
            Intrinsics.checkNotNullParameter(response, "response");
        }
    }
}
