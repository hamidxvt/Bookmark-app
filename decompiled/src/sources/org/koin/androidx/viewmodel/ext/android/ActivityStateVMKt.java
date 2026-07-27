package org.koin.androidx.viewmodel.ext.android;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.scope.ScopeExtKt;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: ActivityStateVM.kt */
@Metadata(d1 = {"\u0000:\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a_\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0012\b\u0002\u0010\u0006\u001a\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000b2\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0018\u00010\u0007j\u0004\u0018\u0001`\u000e¢\u0006\u0002\u0010\u000f\u001aY\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0012\b\n\u0010\u0006\u001a\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t2\u0016\b\n\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0018\u00010\u0007j\u0004\u0018\u0001`\u000eH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a`\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0012\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0012\b\u0002\u0010\u0006\u001a\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000b2\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0018\u00010\u0007j\u0004\u0018\u0001`\u000e\u001aZ\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0012\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0012\b\n\u0010\u0006\u001a\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t2\u0016\b\n\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0018\u00010\u0007j\u0004\u0018\u0001`\u000eH\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0013"}, d2 = {"getStateViewModel", "T", "Landroidx/lifecycle/ViewModel;", "Landroidx/activity/ComponentActivity;", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "state", "Lkotlin/Function0;", "Landroid/os/Bundle;", "Lorg/koin/androidx/viewmodel/scope/BundleDefinition;", "clazz", "Lkotlin/reflect/KClass;", "parameters", "Lorg/koin/core/parameter/ParametersHolder;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Landroidx/activity/ComponentActivity;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function0;)Landroidx/lifecycle/ViewModel;", "(Landroidx/activity/ComponentActivity;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)Landroidx/lifecycle/ViewModel;", "stateViewModel", "Lkotlin/Lazy;", "koin-android_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class ActivityStateVMKt {
    public static /* synthetic */ Lazy stateViewModel$default(ComponentActivity $this$stateViewModel_u24default, Qualifier qualifier, Function0 state, Function0 parameters, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            state = ScopeExtKt.emptyState();
        }
        if ((i & 4) != 0) {
            parameters = null;
        }
        Intrinsics.checkNotNullParameter($this$stateViewModel_u24default, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Scope scope = AndroidKoinScopeExtKt.getKoinScope($this$stateViewModel_u24default);
        Intrinsics.needClassReification();
        Function0 factoryProducer$iv = new ActivityStateVMKt$stateViewModel$1(qualifier, parameters, state, scope, $this$stateViewModel_u24default);
        Intrinsics.reifiedOperationMarker(4, "T");
        return new ViewModelLazy(Reflection.getOrCreateKotlinClass(ViewModel.class), new ActivityStateVMKt$stateViewModel$$inlined$viewModels$2($this$stateViewModel_u24default), factoryProducer$iv);
    }

    public static final /* synthetic */ <T extends ViewModel> Lazy<T> stateViewModel(ComponentActivity $this$stateViewModel, Qualifier qualifier, Function0<Bundle> state, Function0<? extends ParametersHolder> function0) {
        Intrinsics.checkNotNullParameter($this$stateViewModel, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Scope scope = AndroidKoinScopeExtKt.getKoinScope($this$stateViewModel);
        Intrinsics.needClassReification();
        Function0 factoryProducer$iv = new ActivityStateVMKt$stateViewModel$1(qualifier, function0, state, scope, $this$stateViewModel);
        Intrinsics.reifiedOperationMarker(4, "T");
        return new ViewModelLazy(Reflection.getOrCreateKotlinClass(ViewModel.class), new ActivityStateVMKt$stateViewModel$$inlined$viewModels$2($this$stateViewModel), factoryProducer$iv);
    }

    public static /* synthetic */ Lazy stateViewModel$default(ComponentActivity componentActivity, Qualifier qualifier, Function0 function0, KClass kClass, Function0 function02, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = ScopeExtKt.emptyState();
        }
        if ((i & 8) != 0) {
            function02 = null;
        }
        return stateViewModel(componentActivity, qualifier, function0, kClass, function02);
    }

    public static final <T extends ViewModel> Lazy<T> stateViewModel(final ComponentActivity $this$stateViewModel, final Qualifier qualifier, final Function0<Bundle> state, final KClass<T> clazz, final Function0<? extends ParametersHolder> function0) {
        Intrinsics.checkNotNullParameter($this$stateViewModel, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        final Scope scope = AndroidKoinScopeExtKt.getKoinScope($this$stateViewModel);
        return new ViewModelLazy(clazz, new Function0<ViewModelStore>() { // from class: org.koin.androidx.viewmodel.ext.android.ActivityStateVMKt$stateViewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.koin.androidx.viewmodel.ext.android.ActivityStateVMKt$stateViewModel$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                final ComponentActivity componentActivity = $this$stateViewModel;
                Function0 owner = new Function0<ViewModelOwner>() { // from class: org.koin.androidx.viewmodel.ext.android.ActivityStateVMKt$stateViewModel$3$owner$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final ViewModelOwner invoke() {
                        return ViewModelOwner.INSTANCE.from(ComponentActivity.this, ComponentActivity.this);
                    }
                };
                return GetViewModelFactoryKt.getViewModelFactory(owner, clazz, qualifier, function0, state, scope);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ViewModel getStateViewModel$default(ComponentActivity $this$getStateViewModel_u24default, Qualifier qualifier, Function0 state, Function0 parameters, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            state = ScopeExtKt.emptyState();
        }
        if ((i & 4) != 0) {
            parameters = null;
        }
        Intrinsics.checkNotNullParameter($this$getStateViewModel_u24default, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Scope scope$iv = AndroidKoinScopeExtKt.getKoinScope($this$getStateViewModel_u24default);
        Intrinsics.needClassReification();
        Function0 factoryProducer$iv$iv = new ActivityStateVMKt$getStateViewModel$$inlined$stateViewModel$1(qualifier, parameters, state, scope$iv, $this$getStateViewModel_u24default);
        Intrinsics.reifiedOperationMarker(4, "T");
        return (ViewModel) new ViewModelLazy(Reflection.getOrCreateKotlinClass(ViewModel.class), new ActivityStateVMKt$stateViewModel$$inlined$viewModels$2($this$getStateViewModel_u24default), factoryProducer$iv$iv).getValue();
    }

    public static final /* synthetic */ <T extends ViewModel> T getStateViewModel(ComponentActivity $this$getStateViewModel, Qualifier qualifier, Function0<Bundle> state, Function0<? extends ParametersHolder> function0) {
        Intrinsics.checkNotNullParameter($this$getStateViewModel, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Scope scope$iv = AndroidKoinScopeExtKt.getKoinScope($this$getStateViewModel);
        Intrinsics.needClassReification();
        ActivityStateVMKt$getStateViewModel$$inlined$stateViewModel$1 factoryProducer$iv$iv = new ActivityStateVMKt$getStateViewModel$$inlined$stateViewModel$1(qualifier, function0, state, scope$iv, $this$getStateViewModel);
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) new ViewModelLazy(Reflection.getOrCreateKotlinClass(ViewModel.class), new ActivityStateVMKt$stateViewModel$$inlined$viewModels$2($this$getStateViewModel), factoryProducer$iv$iv).getValue();
    }

    public static /* synthetic */ ViewModel getStateViewModel$default(ComponentActivity componentActivity, Qualifier qualifier, Function0 function0, KClass kClass, Function0 function02, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = ScopeExtKt.emptyState();
        }
        if ((i & 8) != 0) {
            function02 = null;
        }
        return getStateViewModel(componentActivity, qualifier, function0, kClass, function02);
    }

    public static final <T extends ViewModel> T getStateViewModel(ComponentActivity $this$getStateViewModel, Qualifier qualifier, Function0<Bundle> state, KClass<T> clazz, Function0<? extends ParametersHolder> function0) {
        Intrinsics.checkNotNullParameter($this$getStateViewModel, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return (T) stateViewModel($this$getStateViewModel, qualifier, state, clazz, function0).getValue();
    }
}
