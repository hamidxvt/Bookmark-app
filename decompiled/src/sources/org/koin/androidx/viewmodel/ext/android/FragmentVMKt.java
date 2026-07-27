package org.koin.androidx.viewmodel.ext.android;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModel;
import androidx.savedstate.SavedStateRegistryOwner;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: FragmentVM.kt */
@Metadata(d1 = {"\u00004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001aY\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0012\b\n\u0010\u0006\u001a\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t2\u0016\b\n\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0007j\u0004\u0018\u0001`\fH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\r\u001aZ\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000f\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0012\b\n\u0010\u0006\u001a\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t2\u0016\b\n\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0007j\u0004\u0018\u0001`\fH\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0010"}, d2 = {"getViewModel", "T", "Landroidx/lifecycle/ViewModel;", "Landroidx/fragment/app/Fragment;", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "owner", "Lkotlin/Function0;", "Lorg/koin/androidx/viewmodel/ViewModelOwner;", "Lorg/koin/androidx/viewmodel/ViewModelOwnerDefinition;", "parameters", "Lorg/koin/core/parameter/ParametersHolder;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Landroidx/fragment/app/Fragment;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)Landroidx/lifecycle/ViewModel;", "viewModel", "Lkotlin/Lazy;", "koin-android_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class FragmentVMKt {
    public static /* synthetic */ Lazy viewModel$default(final Fragment $this$viewModel_u24default, Qualifier qualifier, Function0 owner, Function0 parameters, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            Function0 owner2 = new Function0<ViewModelOwner>() { // from class: org.koin.androidx.viewmodel.ext.android.FragmentVMKt$viewModel$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final ViewModelOwner invoke() {
                    ViewModelOwner.Companion companion = ViewModelOwner.INSTANCE;
                    Fragment fragment = Fragment.this;
                    Fragment fragment2 = Fragment.this;
                    return companion.from(fragment, fragment2 instanceof SavedStateRegistryOwner ? fragment2 : null);
                }
            };
            owner = owner2;
        }
        if ((i & 4) != 0) {
            parameters = null;
        }
        Intrinsics.checkNotNullParameter($this$viewModel_u24default, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Scope scope = AndroidKoinScopeExtKt.getKoinScope($this$viewModel_u24default);
        Function0 ownerProducer$iv = new FragmentVMKt$viewModel$2(owner);
        Intrinsics.needClassReification();
        Function0 factoryProducer$iv = new FragmentVMKt$viewModel$3(owner, qualifier, parameters, scope);
        Intrinsics.reifiedOperationMarker(4, "T");
        return FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default, Reflection.getOrCreateKotlinClass(ViewModel.class), new FragmentVMKt$viewModel$$inlined$viewModels$1(ownerProducer$iv), factoryProducer$iv);
    }

    public static final /* synthetic */ <T extends ViewModel> Lazy<T> viewModel(Fragment $this$viewModel, Qualifier qualifier, Function0<ViewModelOwner> owner, Function0<? extends ParametersHolder> function0) {
        Intrinsics.checkNotNullParameter($this$viewModel, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Scope scope = AndroidKoinScopeExtKt.getKoinScope($this$viewModel);
        Function0 ownerProducer$iv = new FragmentVMKt$viewModel$2(owner);
        Intrinsics.needClassReification();
        Function0 factoryProducer$iv = new FragmentVMKt$viewModel$3(owner, qualifier, function0, scope);
        Intrinsics.reifiedOperationMarker(4, "T");
        return FragmentViewModelLazyKt.createViewModelLazy($this$viewModel, Reflection.getOrCreateKotlinClass(ViewModel.class), new FragmentVMKt$viewModel$$inlined$viewModels$1(ownerProducer$iv), factoryProducer$iv);
    }

    public static /* synthetic */ ViewModel getViewModel$default(final Fragment $this$getViewModel_u24default, Qualifier qualifier, Function0 owner, Function0 parameters, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            Function0 owner2 = new Function0<ViewModelOwner>() { // from class: org.koin.androidx.viewmodel.ext.android.FragmentVMKt$getViewModel$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final ViewModelOwner invoke() {
                    ViewModelOwner.Companion companion = ViewModelOwner.INSTANCE;
                    Fragment fragment = Fragment.this;
                    Fragment fragment2 = Fragment.this;
                    return companion.from(fragment, fragment2 instanceof SavedStateRegistryOwner ? fragment2 : null);
                }
            };
            owner = owner2;
        }
        if ((i & 4) != 0) {
            parameters = null;
        }
        Intrinsics.checkNotNullParameter($this$getViewModel_u24default, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Scope scope$iv = AndroidKoinScopeExtKt.getKoinScope($this$getViewModel_u24default);
        Function0 ownerProducer$iv$iv = new FragmentVMKt$viewModel$2(owner);
        Intrinsics.needClassReification();
        Function0 factoryProducer$iv$iv = new FragmentVMKt$getViewModel$$inlined$viewModel$1(owner, qualifier, parameters, scope$iv);
        Intrinsics.reifiedOperationMarker(4, "T");
        return (ViewModel) FragmentViewModelLazyKt.createViewModelLazy($this$getViewModel_u24default, Reflection.getOrCreateKotlinClass(ViewModel.class), new FragmentVMKt$viewModel$$inlined$viewModels$1(ownerProducer$iv$iv), factoryProducer$iv$iv).getValue();
    }

    public static final /* synthetic */ <T extends ViewModel> T getViewModel(Fragment $this$getViewModel, Qualifier qualifier, Function0<ViewModelOwner> owner, Function0<? extends ParametersHolder> function0) {
        Intrinsics.checkNotNullParameter($this$getViewModel, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Scope scope$iv = AndroidKoinScopeExtKt.getKoinScope($this$getViewModel);
        FragmentVMKt$viewModel$2 ownerProducer$iv$iv = new FragmentVMKt$viewModel$2(owner);
        Intrinsics.needClassReification();
        FragmentVMKt$getViewModel$$inlined$viewModel$1 factoryProducer$iv$iv = new FragmentVMKt$getViewModel$$inlined$viewModel$1(owner, qualifier, function0, scope$iv);
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) FragmentViewModelLazyKt.createViewModelLazy($this$getViewModel, Reflection.getOrCreateKotlinClass(ViewModel.class), new FragmentVMKt$viewModel$$inlined$viewModels$1(ownerProducer$iv$iv), factoryProducer$iv$iv).getValue();
    }
}
