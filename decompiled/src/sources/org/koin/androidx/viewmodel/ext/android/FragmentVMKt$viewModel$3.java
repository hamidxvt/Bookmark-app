package org.koin.androidx.viewmodel.ext.android;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistryOwner;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: FragmentVM.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Landroidx/lifecycle/ViewModelProvider$Factory;", "T", "Landroidx/lifecycle/ViewModel;"}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class FragmentVMKt$viewModel$3 extends Lambda implements Function0<ViewModelProvider.Factory> {
    final /* synthetic */ Function0<ViewModelOwner> $owner;
    final /* synthetic */ Function0<ParametersHolder> $parameters;
    final /* synthetic */ Qualifier $qualifier;
    final /* synthetic */ Scope $scope;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FragmentVMKt$viewModel$3(Function0<ViewModelOwner> function0, Qualifier qualifier, Function0<? extends ParametersHolder> function02, Scope scope) {
        super(0);
        this.$owner = function0;
        this.$qualifier = qualifier;
        this.$parameters = function02;
        this.$scope = scope;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final ViewModelProvider.Factory invoke() {
        Function0 owner$iv = this.$owner;
        Qualifier qualifier$iv = this.$qualifier;
        Function0 parameters$iv = this.$parameters;
        Scope scope$iv = this.$scope;
        ViewModelOwner ownerValue$iv = owner$iv.invoke();
        ViewModelStoreOwner storeOwner = ownerValue$iv.getStoreOwner();
        SavedStateRegistryOwner stateRegistry = ownerValue$iv.getStateRegistry();
        Intrinsics.reifiedOperationMarker(4, "T");
        ViewModelParameter viewModelParameters$iv = new ViewModelParameter(Reflection.getOrCreateKotlinClass(ViewModel.class), qualifier$iv, null, parameters$iv, storeOwner, stateRegistry);
        return ViewModelResolverKt.pickFactory(scope$iv, viewModelParameters$iv);
    }
}
