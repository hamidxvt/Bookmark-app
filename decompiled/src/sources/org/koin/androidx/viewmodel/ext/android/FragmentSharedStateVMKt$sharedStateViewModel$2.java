package org.koin.androidx.viewmodel.ext.android;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.koin.androidx.viewmodel.ViewModelOwner;

/* compiled from: FragmentSharedStateVM.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Landroidx/lifecycle/ViewModelStoreOwner;", "T", "Landroidx/lifecycle/ViewModel;"}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class FragmentSharedStateVMKt$sharedStateViewModel$2 extends Lambda implements Function0<ViewModelStoreOwner> {
    final /* synthetic */ Function0<ViewModelOwner> $owner;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentSharedStateVMKt$sharedStateViewModel$2(Function0<ViewModelOwner> function0) {
        super(0);
        this.$owner = function0;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final ViewModelStoreOwner invoke() {
        return this.$owner.invoke().getStoreOwner();
    }
}
