package org.koin.androidx.viewmodel.ext.android;

import androidx.activity.ComponentActivity;
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
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: ActivityStateVM.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n¨\u0006\u0004"}, d2 = {"<anonymous>", "Landroidx/lifecycle/ViewModelProvider$Factory;", "T", "Landroidx/lifecycle/ViewModel;", "org/koin/androidx/viewmodel/ext/android/ActivityStateVMKt$stateViewModel$1"}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class ActivityStateVMKt$getStateViewModel$$inlined$stateViewModel$1 extends Lambda implements Function0<ViewModelProvider.Factory> {
    final /* synthetic */ Function0 $parameters;
    final /* synthetic */ Qualifier $qualifier;
    final /* synthetic */ Scope $scope;
    final /* synthetic */ Function0 $state;
    final /* synthetic */ ComponentActivity $this_stateViewModel;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityStateVMKt$getStateViewModel$$inlined$stateViewModel$1(Qualifier qualifier, Function0 function0, Function0 function02, Scope scope, ComponentActivity componentActivity) {
        super(0);
        this.$qualifier = qualifier;
        this.$parameters = function0;
        this.$state = function02;
        this.$scope = scope;
        this.$this_stateViewModel = componentActivity;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final ViewModelProvider.Factory invoke() {
        final ComponentActivity componentActivity = this.$this_stateViewModel;
        Function0 owner = new Function0<ViewModelOwner>() { // from class: org.koin.androidx.viewmodel.ext.android.ActivityStateVMKt$getStateViewModel$$inlined$stateViewModel$1.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelOwner invoke() {
                return ViewModelOwner.INSTANCE.from(ComponentActivity.this, ComponentActivity.this);
            }
        };
        Qualifier qualifier$iv = this.$qualifier;
        Function0 parameters$iv = this.$parameters;
        Function0 state$iv = this.$state;
        Scope scope$iv = this.$scope;
        ViewModelOwner ownerValue$iv = owner.invoke();
        ViewModelStoreOwner storeOwner = ownerValue$iv.getStoreOwner();
        SavedStateRegistryOwner stateRegistry = ownerValue$iv.getStateRegistry();
        Intrinsics.reifiedOperationMarker(4, "T");
        ViewModelParameter viewModelParameters$iv = new ViewModelParameter(Reflection.getOrCreateKotlinClass(ViewModel.class), qualifier$iv, state$iv, parameters$iv, storeOwner, stateRegistry);
        return ViewModelResolverKt.pickFactory(scope$iv, viewModelParameters$iv);
    }
}
