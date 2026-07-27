package org.koin.androidx.fragment.android;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.core.scope.Scope;

/* compiled from: ActivityExt.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a;\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00012\b\b\u0001\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0086\b\u001a\u0016\u0010\n\u001a\u00020\u000b*\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¨\u0006\u000f"}, d2 = {"replace", "Landroidx/fragment/app/FragmentTransaction;", "F", "Landroidx/fragment/app/Fragment;", "containerViewId", "", "args", "Landroid/os/Bundle;", "tag", "", "setupKoinFragmentFactory", "", "Landroidx/fragment/app/FragmentActivity;", "scope", "Lorg/koin/core/scope/Scope;", "koin-android_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class ActivityExtKt {
    public static /* synthetic */ void setupKoinFragmentFactory$default(FragmentActivity fragmentActivity, Scope scope, int i, Object obj) {
        if ((i & 1) != 0) {
            scope = null;
        }
        setupKoinFragmentFactory(fragmentActivity, scope);
    }

    public static final void setupKoinFragmentFactory(FragmentActivity $this$setupKoinFragmentFactory, Scope scope) {
        Intrinsics.checkNotNullParameter($this$setupKoinFragmentFactory, "<this>");
        if (scope == null) {
            FragmentManager supportFragmentManager = $this$setupKoinFragmentFactory.getSupportFragmentManager();
            FragmentActivity $this$get_u24default$iv = $this$setupKoinFragmentFactory;
            Scope this_$iv$iv = AndroidKoinScopeExtKt.getKoinScope($this$get_u24default$iv);
            supportFragmentManager.setFragmentFactory((FragmentFactory) this_$iv$iv.get(Reflection.getOrCreateKotlinClass(FragmentFactory.class), null, null));
            return;
        }
        $this$setupKoinFragmentFactory.getSupportFragmentManager().setFragmentFactory(new KoinFragmentFactory(scope));
    }

    public static /* synthetic */ FragmentTransaction replace$default(FragmentTransaction $this$replace_u24default, int containerViewId, Bundle args, String tag, int i, Object obj) {
        if ((i & 2) != 0) {
            args = null;
        }
        if ((i & 4) != 0) {
            tag = null;
        }
        Intrinsics.checkNotNullParameter($this$replace_u24default, "<this>");
        Intrinsics.reifiedOperationMarker(4, "F");
        FragmentTransaction replace = $this$replace_u24default.replace(containerViewId, Fragment.class, args, tag);
        Intrinsics.checkNotNullExpressionValue(replace, "replace(containerViewId, F::class.java, args, tag)");
        return replace;
    }

    public static final /* synthetic */ <F extends Fragment> FragmentTransaction replace(FragmentTransaction $this$replace, int containerViewId, Bundle args, String tag) {
        Intrinsics.checkNotNullParameter($this$replace, "<this>");
        Intrinsics.reifiedOperationMarker(4, "F");
        FragmentTransaction replace = $this$replace.replace(containerViewId, Fragment.class, args, tag);
        Intrinsics.checkNotNullExpressionValue(replace, "replace(containerViewId, F::class.java, args, tag)");
        return replace;
    }
}
