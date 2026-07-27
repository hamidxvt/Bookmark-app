package org.koin.androidx.scope;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.android.ext.android.ComponentCallbackExtKt;
import org.koin.core.Koin;
import org.koin.core.component.KoinScopeComponentKt;
import org.koin.core.scope.Scope;

/* compiled from: FragmentExt.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0016\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u001a\u0010\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n*\u00020\u0002\u001a\f\u0010\u000b\u001a\u0004\u0018\u00010\u0006*\u00020\u0002\u001a\u001e\u0010\f\u001a\u0002H\r\"\n\b\u0000\u0010\r\u0018\u0001*\u00020\u0001*\u00020\u0002H\u0086\b¢\u0006\u0002\u0010\u0004\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u000e"}, d2 = {"scopeActivity", "Lorg/koin/androidx/scope/ScopeActivity;", "Landroidx/fragment/app/Fragment;", "getScopeActivity", "(Landroidx/fragment/app/Fragment;)Lorg/koin/androidx/scope/ScopeActivity;", "createScope", "Lorg/koin/core/scope/Scope;", "source", "", "fragmentScope", "Lorg/koin/androidx/scope/LifecycleScopeDelegate;", "getScopeOrNull", "requireScopeActivity", "T", "koin-android_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class FragmentExtKt {
    public static final LifecycleScopeDelegate<Fragment> fragmentScope(final Fragment $this$fragmentScope) {
        Intrinsics.checkNotNullParameter($this$fragmentScope, "<this>");
        return new LifecycleScopeDelegate<>($this$fragmentScope, ComponentCallbackExtKt.getKoin($this$fragmentScope), new Function1<Koin, Scope>() { // from class: org.koin.androidx.scope.FragmentExtKt$fragmentScope$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Scope invoke(Koin koin) {
                Intrinsics.checkNotNullParameter(koin, "koin");
                Scope scope = Koin.createScope$default(koin, KoinScopeComponentKt.getScopeId(Fragment.this), KoinScopeComponentKt.getScopeName(Fragment.this), null, 4, null);
                FragmentActivity activity = Fragment.this.getActivity();
                Scope activityScope = activity == null ? null : ComponentActivityExtKt.getScopeOrNull(activity);
                if (activityScope != null) {
                    Scope it = activityScope;
                    scope.linkTo(it);
                }
                return scope;
            }
        });
    }

    public static final Scope createScope(Fragment $this$createScope, Object source) {
        Intrinsics.checkNotNullParameter($this$createScope, "<this>");
        return ComponentCallbackExtKt.getKoin($this$createScope).createScope(KoinScopeComponentKt.getScopeId($this$createScope), KoinScopeComponentKt.getScopeName($this$createScope), source);
    }

    public static /* synthetic */ Scope createScope$default(Fragment fragment, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = null;
        }
        return createScope(fragment, obj);
    }

    public static final Scope getScopeOrNull(Fragment $this$getScopeOrNull) {
        Intrinsics.checkNotNullParameter($this$getScopeOrNull, "<this>");
        return ComponentCallbackExtKt.getKoin($this$getScopeOrNull).getScopeOrNull(KoinScopeComponentKt.getScopeId($this$getScopeOrNull));
    }

    public static final ScopeActivity getScopeActivity(Fragment $this$scopeActivity) {
        Intrinsics.checkNotNullParameter($this$scopeActivity, "<this>");
        FragmentActivity activity = $this$scopeActivity.getActivity();
        if (activity instanceof ScopeActivity) {
            return (ScopeActivity) activity;
        }
        return null;
    }

    public static final /* synthetic */ <T extends ScopeActivity> T requireScopeActivity(Fragment $this$requireScopeActivity) {
        Intrinsics.checkNotNullParameter($this$requireScopeActivity, "<this>");
        FragmentActivity activity = $this$requireScopeActivity.getActivity();
        Intrinsics.reifiedOperationMarker(2, "T");
        T t = (T) activity;
        if (t != null) {
            return t;
        }
        Intrinsics.reifiedOperationMarker(4, "T");
        throw new IllegalStateException(Intrinsics.stringPlus("can't get ScopeActivity for class ", Reflection.getOrCreateKotlinClass(ScopeActivity.class)).toString());
    }
}
