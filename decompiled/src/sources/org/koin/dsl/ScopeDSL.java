package org.koin.dsl;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.BeanDefinitionKt;
import org.koin.core.definition.Kind;
import org.koin.core.instance.FactoryInstanceFactory;
import org.koin.core.instance.InstanceFactory;
import org.koin.core.instance.ScopedInstanceFactory;
import org.koin.core.module.Module;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: ScopeDSL.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J]\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\r0\f\"\u0006\b\u0000\u0010\u000e\u0018\u00012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032)\b\b\u0010\u0010\u001a#\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u0002H\u000e0\u0011j\b\u0012\u0004\u0012\u0002H\u000e`\u0014¢\u0006\u0002\b\u0015H\u0086\bø\u0001\u0000J]\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\r0\f\"\u0006\b\u0000\u0010\u000e\u0018\u00012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032)\b\b\u0010\u0010\u001a#\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u0002H\u000e0\u0011j\b\u0012\u0004\u0012\u0002H\u000e`\u0014¢\u0006\u0002\b\u0015H\u0086\bø\u0001\u0000J]\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\r0\f\"\u0006\b\u0000\u0010\u000e\u0018\u00012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032)\b\b\u0010\u0010\u001a#\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u0002H\u000e0\u0011j\b\u0012\u0004\u0012\u0002H\u000e`\u0014¢\u0006\u0002\b\u0015H\u0087\bø\u0001\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0018"}, d2 = {"Lorg/koin/dsl/ScopeDSL;", "", "scopeQualifier", "Lorg/koin/core/qualifier/Qualifier;", "module", "Lorg/koin/core/module/Module;", "(Lorg/koin/core/qualifier/Qualifier;Lorg/koin/core/module/Module;)V", "getModule", "()Lorg/koin/core/module/Module;", "getScopeQualifier", "()Lorg/koin/core/qualifier/Qualifier;", "factory", "Lkotlin/Pair;", "Lorg/koin/core/instance/InstanceFactory;", "T", "qualifier", "definition", "Lkotlin/Function2;", "Lorg/koin/core/scope/Scope;", "Lorg/koin/core/parameter/ParametersHolder;", "Lorg/koin/core/definition/Definition;", "Lkotlin/ExtensionFunctionType;", "scoped", "single", "koin-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class ScopeDSL {
    private final Module module;
    private final Qualifier scopeQualifier;

    public ScopeDSL(Qualifier scopeQualifier, Module module) {
        Intrinsics.checkNotNullParameter(scopeQualifier, "scopeQualifier");
        Intrinsics.checkNotNullParameter(module, "module");
        this.scopeQualifier = scopeQualifier;
        this.module = module;
    }

    public final Module getModule() {
        return this.module;
    }

    public final Qualifier getScopeQualifier() {
        return this.scopeQualifier;
    }

    public static /* synthetic */ Pair single$default(ScopeDSL scopeDSL, Qualifier qualifier, Function2 definition, int i, Object obj) {
        if ((i & 1) != 0) {
        }
        Intrinsics.checkNotNullParameter(definition, "definition");
        throw new IllegalStateException("Scoped definition is deprecated and has been replaced with Single scope definitions".toString());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Can't use Single in a scope. Use Scoped instead")
    public final /* synthetic */ <T> Pair<Module, InstanceFactory<T>> single(Qualifier qualifier, Function2<? super Scope, ? super ParametersHolder, ? extends T> definition) {
        Intrinsics.checkNotNullParameter(definition, "definition");
        throw new IllegalStateException("Scoped definition is deprecated and has been replaced with Single scope definitions".toString());
    }

    public static /* synthetic */ Pair scoped$default(ScopeDSL scopeDSL, Qualifier qualifier, Function2 definition, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        Intrinsics.checkNotNullParameter(definition, "definition");
        Kind kind$iv = Kind.Scoped;
        Qualifier scopeQualifier$iv = scopeDSL.getScopeQualifier();
        List secondaryTypes$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def = new BeanDefinition(scopeQualifier$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier, definition, kind$iv, secondaryTypes$iv);
        String mapping = BeanDefinitionKt.indexKey(def.getPrimaryType(), qualifier, scopeDSL.getScopeQualifier());
        ScopedInstanceFactory instanceFactory = new ScopedInstanceFactory(def);
        Module.saveMapping$default(scopeDSL.getModule(), mapping, instanceFactory, false, 4, null);
        return new Pair(scopeDSL.getModule(), instanceFactory);
    }

    public final /* synthetic */ <T> Pair<Module, InstanceFactory<T>> scoped(Qualifier qualifier, Function2<? super Scope, ? super ParametersHolder, ? extends T> definition) {
        Intrinsics.checkNotNullParameter(definition, "definition");
        Kind kind$iv = Kind.Scoped;
        Qualifier scopeQualifier$iv = getScopeQualifier();
        List secondaryTypes$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def = new BeanDefinition(scopeQualifier$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier, definition, kind$iv, secondaryTypes$iv);
        String mapping = BeanDefinitionKt.indexKey(def.getPrimaryType(), qualifier, getScopeQualifier());
        ScopedInstanceFactory instanceFactory = new ScopedInstanceFactory(def);
        Module.saveMapping$default(getModule(), mapping, instanceFactory, false, 4, null);
        return new Pair<>(getModule(), instanceFactory);
    }

    public static /* synthetic */ Pair factory$default(ScopeDSL scopeDSL, Qualifier qualifier, Function2 definition, int i, Object obj) {
        Qualifier qualifier2;
        if ((i & 1) == 0) {
            qualifier2 = qualifier;
        } else {
            qualifier2 = null;
        }
        Intrinsics.checkNotNullParameter(definition, "definition");
        Module this_$iv = scopeDSL.getModule();
        Qualifier scopeQualifier$iv = scopeDSL.getScopeQualifier();
        Kind kind$iv$iv = Kind.Factory;
        List secondaryTypes$iv$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def$iv = new BeanDefinition(scopeQualifier$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier2, definition, kind$iv$iv, secondaryTypes$iv$iv);
        String mapping$iv = BeanDefinitionKt.indexKey(def$iv.getPrimaryType(), qualifier2, scopeQualifier$iv);
        FactoryInstanceFactory instanceFactory$iv = new FactoryInstanceFactory(def$iv);
        Module.saveMapping$default(this_$iv, mapping$iv, instanceFactory$iv, false, 4, null);
        return new Pair(this_$iv, instanceFactory$iv);
    }

    public final /* synthetic */ <T> Pair<Module, InstanceFactory<T>> factory(Qualifier qualifier, Function2<? super Scope, ? super ParametersHolder, ? extends T> definition) {
        Intrinsics.checkNotNullParameter(definition, "definition");
        Module this_$iv = getModule();
        Qualifier scopeQualifier$iv = getScopeQualifier();
        Kind kind$iv$iv = Kind.Factory;
        List secondaryTypes$iv$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def$iv = new BeanDefinition(scopeQualifier$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier, definition, kind$iv$iv, secondaryTypes$iv$iv);
        String mapping$iv = BeanDefinitionKt.indexKey(def$iv.getPrimaryType(), qualifier, scopeQualifier$iv);
        FactoryInstanceFactory instanceFactory$iv = new FactoryInstanceFactory(def$iv);
        Module.saveMapping$default(this_$iv, mapping$iv, instanceFactory$iv, false, 4, null);
        return new Pair<>(this_$iv, instanceFactory$iv);
    }
}
