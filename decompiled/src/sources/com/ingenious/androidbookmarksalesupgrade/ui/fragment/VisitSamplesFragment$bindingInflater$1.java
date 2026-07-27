package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitSamplesBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VisitSamplesFragment.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
/* synthetic */ class VisitSamplesFragment$bindingInflater$1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, FragmentVisitSamplesBinding> {
    public static final VisitSamplesFragment$bindingInflater$1 INSTANCE = new VisitSamplesFragment$bindingInflater$1();

    VisitSamplesFragment$bindingInflater$1() {
        super(3, FragmentVisitSamplesBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentVisitSamplesBinding;", 0);
    }

    public final FragmentVisitSamplesBinding invoke(LayoutInflater p0, ViewGroup p1, boolean p2) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return FragmentVisitSamplesBinding.inflate(p0, p1, p2);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ FragmentVisitSamplesBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
        return invoke(layoutInflater, viewGroup, bool.booleanValue());
    }
}
