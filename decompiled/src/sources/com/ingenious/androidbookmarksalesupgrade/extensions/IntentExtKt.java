package com.ingenious.androidbookmarksalesupgrade.extensions;

import android.content.Intent;
import android.os.Parcelable;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IntentExt.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"putExtra", "", "Landroid/content/Intent;", "intentKey", "", "intentValue", "", "app_debug"}, k = 2, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes15.dex */
public final class IntentExtKt {
    public static final void putExtra(Intent $this$putExtra, String intentKey, Object intentValue) {
        Intrinsics.checkNotNullParameter($this$putExtra, "<this>");
        Intrinsics.checkNotNullParameter(intentKey, "intentKey");
        if (!(intentValue instanceof Boolean)) {
            if (!(intentValue instanceof Integer)) {
                if (!(intentValue instanceof Float)) {
                    if (!(intentValue instanceof Double)) {
                        if (!(intentValue instanceof Long)) {
                            if (!(intentValue instanceof String)) {
                                if (!(intentValue instanceof Serializable)) {
                                    if (intentValue instanceof Parcelable) {
                                        $this$putExtra.putExtra(intentKey, (Parcelable) intentValue);
                                        return;
                                    }
                                    return;
                                }
                                $this$putExtra.putExtra(intentKey, (Serializable) intentValue);
                                return;
                            }
                            $this$putExtra.putExtra(intentKey, (String) intentValue);
                            return;
                        }
                        $this$putExtra.putExtra(intentKey, ((Number) intentValue).longValue());
                        return;
                    }
                    $this$putExtra.putExtra(intentKey, ((Number) intentValue).doubleValue());
                    return;
                }
                $this$putExtra.putExtra(intentKey, ((Number) intentValue).floatValue());
                return;
            }
            $this$putExtra.putExtra(intentKey, ((Number) intentValue).intValue());
            return;
        }
        $this$putExtra.putExtra(intentKey, ((Boolean) intentValue).booleanValue());
    }
}
