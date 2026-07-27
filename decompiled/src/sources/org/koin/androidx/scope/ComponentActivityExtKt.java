package org.koin.androidx.scope;

import android.app.Activity;
import androidx.activity.ComponentActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.koin.android.ext.android.ComponentCallbackExtKt;
import org.koin.core.component.KoinScopeComponentKt;
import org.koin.core.scope.Scope;

/* compiled from: ComponentActivityExt.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0010\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\u00020\u0002\u001a\u0016\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u001a\f\u0010\n\u001a\u0004\u0018\u00010\u0007*\u00020\u0002¨\u0006\u000b"}, d2 = {"activityRetainedScope", "Lorg/koin/androidx/scope/LifecycleViewModelScopeDelegate;", "Landroidx/activity/ComponentActivity;", "activityScope", "Lorg/koin/androidx/scope/LifecycleScopeDelegate;", "Landroid/app/Activity;", "createScope", "Lorg/koin/core/scope/Scope;", "source", "", "getScopeOrNull", "koin-android_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class ComponentActivityExtKt {
    public static final LifecycleScopeDelegate<Activity> activityScope(ComponentActivity $this$activityScope) {
        Intrinsics.checkNotNullParameter($this$activityScope, "<this>");
        return new LifecycleScopeDelegate<>($this$activityScope, ComponentCallbackExtKt.getKoin($this$activityScope), null, 4, null);
    }

    public static final LifecycleViewModelScopeDelegate activityRetainedScope(ComponentActivity $this$activityRetainedScope) {
        Intrinsics.checkNotNullParameter($this$activityRetainedScope, "<this>");
        return new LifecycleViewModelScopeDelegate($this$activityRetainedScope, ComponentCallbackExtKt.getKoin($this$activityRetainedScope), null, 4, null);
    }

    public static final Scope createScope(ComponentActivity $this$createScope, Object source) {
        Intrinsics.checkNotNullParameter($this$createScope, "<this>");
        return ComponentCallbackExtKt.getKoin($this$createScope).createScope(KoinScopeComponentKt.getScopeId($this$createScope), KoinScopeComponentKt.getScopeName($this$createScope), source);
    }

    public static /* synthetic */ Scope createScope$default(ComponentActivity componentActivity, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = null;
        }
        return createScope(componentActivity, obj);
    }

    public static final Scope getScopeOrNull(ComponentActivity $this$getScopeOrNull) {
        Intrinsics.checkNotNullParameter($this$getScopeOrNull, "<this>");
        return ComponentCallbackExtKt.getKoin($this$getScopeOrNull).getScopeOrNull(KoinScopeComponentKt.getScopeId($this$getScopeOrNull));
    }
}
