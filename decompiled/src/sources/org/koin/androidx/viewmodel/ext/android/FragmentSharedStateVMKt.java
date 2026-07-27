package org.koin.androidx.viewmodel.ext.android;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
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

/* compiled from: FragmentSharedStateVM.kt */
@Metadata(d1 = {"\u0000D\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001as\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0012\b\u0002\u0010\u0006\u001a\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t2\u0012\b\u0002\u0010\n\u001a\f\u0012\u0004\u0012\u00020\u000b0\u0007j\u0002`\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000e2\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0007j\u0004\u0018\u0001`\u0011¢\u0006\u0002\u0010\u0012\u001am\u0010\u0013\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0012\b\n\u0010\u0006\u001a\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t2\u0012\b\n\u0010\n\u001a\f\u0012\u0004\u0012\u00020\u000b0\u0007j\u0002`\f2\u0016\b\n\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0007j\u0004\u0018\u0001`\u0011H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001at\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0016\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0012\b\u0002\u0010\u0006\u001a\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t2\u0012\b\u0002\u0010\n\u001a\f\u0012\u0004\u0012\u00020\u000b0\u0007j\u0002`\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000e2\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0007j\u0004\u0018\u0001`\u0011\u001an\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0016\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0012\b\n\u0010\u0006\u001a\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t2\u0012\b\n\u0010\n\u001a\f\u0012\u0004\u0012\u00020\u000b0\u0007j\u0002`\f2\u0016\b\n\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0007j\u0004\u0018\u0001`\u0011H\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0017"}, d2 = {"getSSharedStateViewModel", "T", "Landroidx/lifecycle/ViewModel;", "Landroidx/fragment/app/Fragment;", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "state", "Lkotlin/Function0;", "Landroid/os/Bundle;", "Lorg/koin/androidx/viewmodel/scope/BundleDefinition;", "owner", "Lorg/koin/androidx/viewmodel/ViewModelOwner;", "Lorg/koin/androidx/viewmodel/ViewModelOwnerDefinition;", "clazz", "Lkotlin/reflect/KClass;", "parameters", "Lorg/koin/core/parameter/ParametersHolder;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Landroidx/fragment/app/Fragment;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function0;)Landroidx/lifecycle/ViewModel;", "getSharedStateViewModel", "(Landroidx/fragment/app/Fragment;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)Landroidx/lifecycle/ViewModel;", "sharedStateViewModel", "Lkotlin/Lazy;", "koin-android_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class FragmentSharedStateVMKt {
    public static /* synthetic */ Lazy sharedStateViewModel$default(final Fragment $this$sharedStateViewModel_u24default, Qualifier qualifier, Function0 state, Function0 owner, Function0 parameters, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            state = ScopeExtKt.emptyState();
        }
        if ((i & 4) != 0) {
            Function0 owner2 = new Function0<ViewModelOwner>() { // from class: org.koin.androidx.viewmodel.ext.android.FragmentSharedStateVMKt$sharedStateViewModel$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final ViewModelOwner invoke() {
                    ViewModelOwner.Companion companion = ViewModelOwner.INSTANCE;
                    FragmentActivity requireActivity = Fragment.this.requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                    return companion.from(requireActivity, Fragment.this);
                }
            };
            owner = owner2;
        }
        if ((i & 8) != 0) {
            parameters = null;
        }
        Intrinsics.checkNotNullParameter($this$sharedStateViewModel_u24default, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Scope scope = AndroidKoinScopeExtKt.getKoinScope($this$sharedStateViewModel_u24default);
        Function0 ownerProducer$iv = new FragmentSharedStateVMKt$sharedStateViewModel$2(owner);
        Intrinsics.needClassReification();
        Function0 factoryProducer$iv = new FragmentSharedStateVMKt$sharedStateViewModel$3(owner, qualifier, parameters, state, scope);
        Intrinsics.reifiedOperationMarker(4, "T");
        return FragmentViewModelLazyKt.createViewModelLazy($this$sharedStateViewModel_u24default, Reflection.getOrCreateKotlinClass(ViewModel.class), new FragmentSharedStateVMKt$sharedStateViewModel$$inlined$viewModels$1(ownerProducer$iv), factoryProducer$iv);
    }

    public static final /* synthetic */ <T extends ViewModel> Lazy<T> sharedStateViewModel(Fragment $this$sharedStateViewModel, Qualifier qualifier, Function0<Bundle> state, Function0<ViewModelOwner> owner, Function0<? extends ParametersHolder> function0) {
        Intrinsics.checkNotNullParameter($this$sharedStateViewModel, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Scope scope = AndroidKoinScopeExtKt.getKoinScope($this$sharedStateViewModel);
        Function0 ownerProducer$iv = new FragmentSharedStateVMKt$sharedStateViewModel$2(owner);
        Intrinsics.needClassReification();
        Function0 factoryProducer$iv = new FragmentSharedStateVMKt$sharedStateViewModel$3(owner, qualifier, function0, state, scope);
        Intrinsics.reifiedOperationMarker(4, "T");
        return FragmentViewModelLazyKt.createViewModelLazy($this$sharedStateViewModel, Reflection.getOrCreateKotlinClass(ViewModel.class), new FragmentSharedStateVMKt$sharedStateViewModel$$inlined$viewModels$1(ownerProducer$iv), factoryProducer$iv);
    }

    public static final <T extends ViewModel> Lazy<T> sharedStateViewModel(final Fragment $this$sharedStateViewModel, final Qualifier qualifier, final Function0<Bundle> state, final Function0<ViewModelOwner> owner, final KClass<T> clazz, final Function0<? extends ParametersHolder> function0) {
        Intrinsics.checkNotNullParameter($this$sharedStateViewModel, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        final Scope scope = AndroidKoinScopeExtKt.getKoinScope($this$sharedStateViewModel);
        return new ViewModelLazy(clazz, new Function0<ViewModelStore>() { // from class: org.koin.androidx.viewmodel.ext.android.FragmentSharedStateVMKt$sharedStateViewModel$5
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.koin.androidx.viewmodel.ext.android.FragmentSharedStateVMKt$sharedStateViewModel$6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return GetViewModelFactoryKt.getViewModelFactory(owner, clazz, qualifier, function0, state, scope);
            }
        });
    }

    public static /* synthetic */ ViewModel getSharedStateViewModel$default(final Fragment $this$getSharedStateViewModel_u24default, Qualifier qualifier, Function0 state, Function0 owner, Function0 parameters, int i, Object obj) {
        Qualifier qualifier2 = (i & 1) != 0 ? null : qualifier;
        Function0 state2 = (i & 2) != 0 ? ScopeExtKt.emptyState() : state;
        Function0 owner2 = (i & 4) != 0 ? new Function0<ViewModelOwner>() { // from class: org.koin.androidx.viewmodel.ext.android.FragmentSharedStateVMKt$getSharedStateViewModel$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelOwner invoke() {
                ViewModelOwner.Companion companion = ViewModelOwner.INSTANCE;
                FragmentActivity requireActivity = Fragment.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                return companion.from(requireActivity, Fragment.this);
            }
        } : owner;
        Function0 parameters2 = (i & 8) != 0 ? null : parameters;
        Intrinsics.checkNotNullParameter($this$getSharedStateViewModel_u24default, "<this>");
        Intrinsics.checkNotNullParameter(state2, "state");
        Intrinsics.checkNotNullParameter(owner2, "owner");
        Scope scope$iv = AndroidKoinScopeExtKt.getKoinScope($this$getSharedStateViewModel_u24default);
        Function0 ownerProducer$iv$iv = new FragmentSharedStateVMKt$sharedStateViewModel$2(owner2);
        Intrinsics.needClassReification();
        Function0 factoryProducer$iv$iv = new FragmentSharedStateVMKt$getSharedStateViewModel$$inlined$sharedStateViewModel$1(owner2, qualifier2, parameters2, state2, scope$iv);
        Intrinsics.reifiedOperationMarker(4, "T");
        return (ViewModel) FragmentViewModelLazyKt.createViewModelLazy($this$getSharedStateViewModel_u24default, Reflection.getOrCreateKotlinClass(ViewModel.class), new FragmentSharedStateVMKt$sharedStateViewModel$$inlined$viewModels$1(ownerProducer$iv$iv), factoryProducer$iv$iv).getValue();
    }

    public static final /* synthetic */ <T extends ViewModel> T getSharedStateViewModel(Fragment $this$getSharedStateViewModel, Qualifier qualifier, Function0<Bundle> state, Function0<ViewModelOwner> owner, Function0<? extends ParametersHolder> function0) {
        Intrinsics.checkNotNullParameter($this$getSharedStateViewModel, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Scope scope$iv = AndroidKoinScopeExtKt.getKoinScope($this$getSharedStateViewModel);
        FragmentSharedStateVMKt$sharedStateViewModel$2 ownerProducer$iv$iv = new FragmentSharedStateVMKt$sharedStateViewModel$2(owner);
        Intrinsics.needClassReification();
        FragmentSharedStateVMKt$getSharedStateViewModel$$inlined$sharedStateViewModel$1 factoryProducer$iv$iv = new FragmentSharedStateVMKt$getSharedStateViewModel$$inlined$sharedStateViewModel$1(owner, qualifier, function0, state, scope$iv);
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) FragmentViewModelLazyKt.createViewModelLazy($this$getSharedStateViewModel, Reflection.getOrCreateKotlinClass(ViewModel.class), new FragmentSharedStateVMKt$sharedStateViewModel$$inlined$viewModels$1(ownerProducer$iv$iv), factoryProducer$iv$iv).getValue();
    }

    public static final <T extends ViewModel> T getSSharedStateViewModel(Fragment $this$getSSharedStateViewModel, Qualifier qualifier, Function0<Bundle> state, Function0<ViewModelOwner> owner, KClass<T> clazz, Function0<? extends ParametersHolder> function0) {
        Intrinsics.checkNotNullParameter($this$getSSharedStateViewModel, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return (T) sharedStateViewModel($this$getSSharedStateViewModel, qualifier, state, owner, clazz, function0).getValue();
    }
}
