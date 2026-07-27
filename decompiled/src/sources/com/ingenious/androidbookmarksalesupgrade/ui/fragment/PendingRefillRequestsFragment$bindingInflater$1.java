package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentPendingRefillRequestsBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PendingRefillRequestsFragment.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
/* synthetic */ class PendingRefillRequestsFragment$bindingInflater$1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, FragmentPendingRefillRequestsBinding> {
    public static final PendingRefillRequestsFragment$bindingInflater$1 INSTANCE = new PendingRefillRequestsFragment$bindingInflater$1();

    PendingRefillRequestsFragment$bindingInflater$1() {
        super(3, FragmentPendingRefillRequestsBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentPendingRefillRequestsBinding;", 0);
    }

    public final FragmentPendingRefillRequestsBinding invoke(LayoutInflater p0, ViewGroup p1, boolean p2) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return FragmentPendingRefillRequestsBinding.inflate(p0, p1, p2);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ FragmentPendingRefillRequestsBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
        return invoke(layoutInflater, viewGroup, bool.booleanValue());
    }
}
