package org.koin.androidx.viewmodel.ext.android;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistryOwner;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: GetViewModelFactory.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001ar\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0010\u0010\u0004\u001a\f\u0012\u0004\u0012\u00020\u00060\u0005j\u0002`\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0018\u00010\u0005j\u0004\u0018\u0001`\u000e2\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0005j\u0004\u0018\u0001`\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007\u001al\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0010\u0010\u0004\u001a\f\u0012\u0004\u0012\u00020\u00060\u0005j\u0002`\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0016\b\b\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0018\u00010\u0005j\u0004\u0018\u0001`\u000e2\u0016\b\n\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0005j\u0004\u0018\u0001`\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0087\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0014"}, d2 = {"getViewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "T", "Landroidx/lifecycle/ViewModel;", "owner", "Lkotlin/Function0;", "Lorg/koin/androidx/viewmodel/ViewModelOwner;", "Lorg/koin/androidx/viewmodel/ViewModelOwnerDefinition;", "clazz", "Lkotlin/reflect/KClass;", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "parameters", "Lorg/koin/core/parameter/ParametersHolder;", "Lorg/koin/core/parameter/ParametersDefinition;", "state", "Landroid/os/Bundle;", "Lorg/koin/androidx/viewmodel/scope/BundleDefinition;", "scope", "Lorg/koin/core/scope/Scope;", "koin-android_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class GetViewModelFactoryKt {
    public static /* synthetic */ ViewModelProvider.Factory getViewModelFactory$default(Function0 owner, Qualifier qualifier, Function0 parameters, Function0 state, Scope scope, int i, Object obj) {
        if ((i & 8) != 0) {
            state = null;
        }
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(scope, "scope");
        ViewModelOwner ownerValue = (ViewModelOwner) owner.invoke();
        ViewModelStoreOwner storeOwner = ownerValue.getStoreOwner();
        SavedStateRegistryOwner stateRegistry = ownerValue.getStateRegistry();
        Intrinsics.reifiedOperationMarker(4, "T");
        ViewModelParameter viewModelParameters = new ViewModelParameter(Reflection.getOrCreateKotlinClass(ViewModel.class), qualifier, state, parameters, storeOwner, stateRegistry);
        return ViewModelResolverKt.pickFactory(scope, viewModelParameters);
    }

    public static final /* synthetic */ <T extends ViewModel> ViewModelProvider.Factory getViewModelFactory(Function0<ViewModelOwner> owner, Qualifier qualifier, Function0<? extends ParametersHolder> function0, Function0<Bundle> function02, Scope scope) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(scope, "scope");
        ViewModelOwner ownerValue = owner.invoke();
        ViewModelStoreOwner storeOwner = ownerValue.getStoreOwner();
        SavedStateRegistryOwner stateRegistry = ownerValue.getStateRegistry();
        Intrinsics.reifiedOperationMarker(4, "T");
        ViewModelParameter viewModelParameters = new ViewModelParameter(Reflection.getOrCreateKotlinClass(ViewModel.class), qualifier, function02, function0, storeOwner, stateRegistry);
        return ViewModelResolverKt.pickFactory(scope, viewModelParameters);
    }

    public static /* synthetic */ ViewModelProvider.Factory getViewModelFactory$default(Function0 function0, KClass kClass, Qualifier qualifier, Function0 function02, Function0 function03, Scope scope, int i, Object obj) {
        Function0 function04;
        if ((i & 16) == 0) {
            function04 = function03;
        } else {
            function04 = null;
        }
        return getViewModelFactory(function0, kClass, qualifier, function02, function04, scope);
    }

    public static final <T extends ViewModel> ViewModelProvider.Factory getViewModelFactory(Function0<ViewModelOwner> owner, KClass<T> clazz, Qualifier qualifier, Function0<? extends ParametersHolder> function0, Function0<Bundle> function02, Scope scope) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(scope, "scope");
        ViewModelOwner ownerValue = owner.invoke();
        ViewModelParameter viewModelParameters = new ViewModelParameter(clazz, qualifier, function02, function0, ownerValue.getStoreOwner(), ownerValue.getStateRegistry());
        return ViewModelResolverKt.pickFactory(scope, viewModelParameters);
    }
}
