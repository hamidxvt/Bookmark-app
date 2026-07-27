package org.koin.androidx.scope;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LifecycleScopeDelegate.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"isActive", "", "Landroidx/lifecycle/LifecycleOwner;", "koin-android_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class LifecycleScopeDelegateKt {
    public static final boolean isActive(LifecycleOwner $this$isActive) {
        Intrinsics.checkNotNullParameter($this$isActive, "<this>");
        Lifecycle.State ownerState = $this$isActive.getLifecycle().getCurrentState();
        Intrinsics.checkNotNullExpressionValue(ownerState, "lifecycle.currentState");
        return ownerState.isAtLeast(Lifecycle.State.CREATED);
    }
}
