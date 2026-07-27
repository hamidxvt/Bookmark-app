package org.koin.androidx.viewmodel.dsl;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.core.annotation.KoinReflectAPI;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.BeanDefinitionKt;
import org.koin.core.definition.Kind;
import org.koin.core.instance.FactoryInstanceFactory;
import org.koin.core.instance.InstanceFactory;
import org.koin.core.module.Module;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
import org.koin.dsl.ScopeDSL;

/* compiled from: ScopeSetExt.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a7\u0010\u0000\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00030\u0001\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0005*\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0087\b\u001ae\u0010\u0000\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00030\u0001\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0005*\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2)\b\b\u0010\t\u001a#\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u0002H\u00040\nj\b\u0012\u0004\u0012\u0002H\u0004`\r¢\u0006\u0002\b\u000eH\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000f"}, d2 = {"viewModel", "Lkotlin/Pair;", "Lorg/koin/core/module/Module;", "Lorg/koin/core/instance/InstanceFactory;", "T", "Landroidx/lifecycle/ViewModel;", "Lorg/koin/dsl/ScopeDSL;", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "definition", "Lkotlin/Function2;", "Lorg/koin/core/scope/Scope;", "Lorg/koin/core/parameter/ParametersHolder;", "Lorg/koin/core/definition/Definition;", "Lkotlin/ExtensionFunctionType;", "koin-android_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class ScopeSetExtKt {
    public static /* synthetic */ Pair viewModel$default(ScopeDSL $this$viewModel_u24default, Qualifier qualifier, Function2 definition, int i, Object obj) {
        Qualifier qualifier2;
        if ((i & 1) == 0) {
            qualifier2 = qualifier;
        } else {
            qualifier2 = null;
        }
        Intrinsics.checkNotNullParameter($this$viewModel_u24default, "<this>");
        Intrinsics.checkNotNullParameter(definition, "definition");
        Module this_$iv$iv = $this$viewModel_u24default.getModule();
        Qualifier scopeQualifier$iv$iv = $this$viewModel_u24default.getScopeQualifier();
        Kind kind$iv$iv$iv = Kind.Factory;
        List secondaryTypes$iv$iv$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def$iv$iv = new BeanDefinition(scopeQualifier$iv$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier2, definition, kind$iv$iv$iv, secondaryTypes$iv$iv$iv);
        String mapping$iv$iv = BeanDefinitionKt.indexKey(def$iv$iv.getPrimaryType(), qualifier2, scopeQualifier$iv$iv);
        FactoryInstanceFactory instanceFactory$iv$iv = new FactoryInstanceFactory(def$iv$iv);
        Module.saveMapping$default(this_$iv$iv, mapping$iv$iv, instanceFactory$iv$iv, false, 4, null);
        return new Pair(this_$iv$iv, instanceFactory$iv$iv);
    }

    public static final /* synthetic */ <T extends ViewModel> Pair<Module, InstanceFactory<T>> viewModel(ScopeDSL $this$viewModel, Qualifier qualifier, Function2<? super Scope, ? super ParametersHolder, ? extends T> definition) {
        Intrinsics.checkNotNullParameter($this$viewModel, "<this>");
        Intrinsics.checkNotNullParameter(definition, "definition");
        Module this_$iv$iv = $this$viewModel.getModule();
        Qualifier scopeQualifier$iv$iv = $this$viewModel.getScopeQualifier();
        Kind kind$iv$iv$iv = Kind.Factory;
        List secondaryTypes$iv$iv$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def$iv$iv = new BeanDefinition(scopeQualifier$iv$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier, definition, kind$iv$iv$iv, secondaryTypes$iv$iv$iv);
        String mapping$iv$iv = BeanDefinitionKt.indexKey(def$iv$iv.getPrimaryType(), qualifier, scopeQualifier$iv$iv);
        FactoryInstanceFactory instanceFactory$iv$iv = new FactoryInstanceFactory(def$iv$iv);
        Module.saveMapping$default(this_$iv$iv, mapping$iv$iv, instanceFactory$iv$iv, false, 4, null);
        return new Pair<>(this_$iv$iv, instanceFactory$iv$iv);
    }

    public static /* synthetic */ Pair viewModel$default(ScopeDSL $this$viewModel_u24default, Qualifier qualifier, int i, Object obj) {
        Qualifier qualifier2;
        if ((i & 1) == 0) {
            qualifier2 = qualifier;
        } else {
            qualifier2 = null;
        }
        Intrinsics.checkNotNullParameter($this$viewModel_u24default, "<this>");
        Intrinsics.needClassReification();
        Function2 definition$iv = new ScopeSetExtKt$viewModel$1();
        Module this_$iv$iv = $this$viewModel_u24default.getModule();
        Qualifier scopeQualifier$iv$iv = $this$viewModel_u24default.getScopeQualifier();
        Kind kind$iv$iv$iv = Kind.Factory;
        List secondaryTypes$iv$iv$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def$iv$iv = new BeanDefinition(scopeQualifier$iv$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier2, definition$iv, kind$iv$iv$iv, secondaryTypes$iv$iv$iv);
        String mapping$iv$iv = BeanDefinitionKt.indexKey(def$iv$iv.getPrimaryType(), qualifier2, scopeQualifier$iv$iv);
        FactoryInstanceFactory instanceFactory$iv$iv = new FactoryInstanceFactory(def$iv$iv);
        Module.saveMapping$default(this_$iv$iv, mapping$iv$iv, instanceFactory$iv$iv, false, 4, null);
        return new Pair(this_$iv$iv, instanceFactory$iv$iv);
    }

    @KoinReflectAPI
    public static final /* synthetic */ <T extends ViewModel> Pair<Module, InstanceFactory<T>> viewModel(ScopeDSL $this$viewModel, Qualifier qualifier) {
        Intrinsics.checkNotNullParameter($this$viewModel, "<this>");
        Intrinsics.needClassReification();
        Function2 definition$iv = new ScopeSetExtKt$viewModel$1();
        Module this_$iv$iv = $this$viewModel.getModule();
        Qualifier scopeQualifier$iv$iv = $this$viewModel.getScopeQualifier();
        Kind kind$iv$iv$iv = Kind.Factory;
        List secondaryTypes$iv$iv$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def$iv$iv = new BeanDefinition(scopeQualifier$iv$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier, definition$iv, kind$iv$iv$iv, secondaryTypes$iv$iv$iv);
        String mapping$iv$iv = BeanDefinitionKt.indexKey(def$iv$iv.getPrimaryType(), qualifier, scopeQualifier$iv$iv);
        FactoryInstanceFactory instanceFactory$iv$iv = new FactoryInstanceFactory(def$iv$iv);
        Module.saveMapping$default(this_$iv$iv, mapping$iv$iv, instanceFactory$iv$iv, false, 4, null);
        return new Pair<>(this_$iv$iv, instanceFactory$iv$iv);
    }
}
