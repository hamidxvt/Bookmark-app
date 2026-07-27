package org.koin.androidx.viewmodel;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistryOwner;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;

/* compiled from: ViewModelParameter.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002Bc\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0004\u0018\u0001`\n\u0012\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\f\u0018\u00010\bj\u0004\u0018\u0001`\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001f\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\f\u0018\u00010\bj\u0004\u0018\u0001`\r¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001f\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0004\u0018\u0001`\n¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Lorg/koin/androidx/viewmodel/ViewModelParameter;", "T", "", "clazz", "Lkotlin/reflect/KClass;", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "state", "Lkotlin/Function0;", "Landroid/os/Bundle;", "Lorg/koin/androidx/viewmodel/scope/BundleDefinition;", "parameters", "Lorg/koin/core/parameter/ParametersHolder;", "Lorg/koin/core/parameter/ParametersDefinition;", "viewModelStoreOwner", "Landroidx/lifecycle/ViewModelStoreOwner;", "registryOwner", "Landroidx/savedstate/SavedStateRegistryOwner;", "(Lkotlin/reflect/KClass;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/lifecycle/ViewModelStoreOwner;Landroidx/savedstate/SavedStateRegistryOwner;)V", "getClazz", "()Lkotlin/reflect/KClass;", "getParameters", "()Lkotlin/jvm/functions/Function0;", "getQualifier", "()Lorg/koin/core/qualifier/Qualifier;", "getRegistryOwner", "()Landroidx/savedstate/SavedStateRegistryOwner;", "getState", "getViewModelStoreOwner", "()Landroidx/lifecycle/ViewModelStoreOwner;", "koin-android_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class ViewModelParameter<T> {
    private final KClass<T> clazz;
    private final Function0<ParametersHolder> parameters;
    private final Qualifier qualifier;
    private final SavedStateRegistryOwner registryOwner;
    private final Function0<Bundle> state;
    private final ViewModelStoreOwner viewModelStoreOwner;

    /* JADX WARN: Multi-variable type inference failed */
    public ViewModelParameter(KClass<T> clazz, Qualifier qualifier, Function0<Bundle> function0, Function0<? extends ParametersHolder> function02, ViewModelStoreOwner viewModelStoreOwner, SavedStateRegistryOwner registryOwner) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(viewModelStoreOwner, "viewModelStoreOwner");
        this.clazz = clazz;
        this.qualifier = qualifier;
        this.state = function0;
        this.parameters = function02;
        this.viewModelStoreOwner = viewModelStoreOwner;
        this.registryOwner = registryOwner;
    }

    public /* synthetic */ ViewModelParameter(KClass kClass, Qualifier qualifier, Function0 function0, Function0 function02, ViewModelStoreOwner viewModelStoreOwner, SavedStateRegistryOwner savedStateRegistryOwner, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(kClass, (i & 2) != 0 ? null : qualifier, (i & 4) != 0 ? null : function0, (i & 8) != 0 ? null : function02, viewModelStoreOwner, (i & 32) != 0 ? null : savedStateRegistryOwner);
    }

    public final KClass<T> getClazz() {
        return this.clazz;
    }

    public final Qualifier getQualifier() {
        return this.qualifier;
    }

    public final Function0<Bundle> getState() {
        return this.state;
    }

    public final Function0<ParametersHolder> getParameters() {
        return this.parameters;
    }

    public final ViewModelStoreOwner getViewModelStoreOwner() {
        return this.viewModelStoreOwner;
    }

    public final SavedStateRegistryOwner getRegistryOwner() {
        return this.registryOwner;
    }
}
