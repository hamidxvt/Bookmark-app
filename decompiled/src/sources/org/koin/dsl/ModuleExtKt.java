package org.koin.dsl;

import androidx.constraintlayout.widget.ConstraintLayout;
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
import org.koin.core.instance.SingleInstanceFactory;
import org.koin.core.module.Module;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.registry.ScopeRegistry;

/* compiled from: ModuleExt.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a7\u0010\u0000\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00030\u0001\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0005*\u00020\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0087\b\u001aA\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00030\u0001\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0005*\u00020\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b¨\u0006\u000b"}, d2 = {"factory", "Lkotlin/Pair;", "Lorg/koin/core/module/Module;", "Lorg/koin/core/instance/InstanceFactory;", "T", "", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "single", "createOnStart", "", "koin-core"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class ModuleExtKt {
    public static /* synthetic */ Pair single$default(Module $this$single_u24default, Qualifier qualifier, boolean createOnStart, int i, Object obj) {
        Qualifier qualifier2;
        boolean createOnStart2;
        if ((i & 1) == 0) {
            qualifier2 = qualifier;
        } else {
            qualifier2 = null;
        }
        if ((i & 2) == 0) {
            createOnStart2 = createOnStart;
        } else {
            createOnStart2 = false;
        }
        Intrinsics.checkNotNullParameter($this$single_u24default, "<this>");
        Intrinsics.needClassReification();
        Function2 definition$iv = new ModuleExtKt$single$1();
        Kind kind$iv$iv = Kind.Singleton;
        Qualifier scopeQualifier$iv$iv = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        List secondaryTypes$iv$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def$iv = new BeanDefinition(scopeQualifier$iv$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier2, definition$iv, kind$iv$iv, secondaryTypes$iv$iv);
        String mapping$iv = BeanDefinitionKt.indexKey(def$iv.getPrimaryType(), qualifier2, ScopeRegistry.INSTANCE.getRootScopeQualifier());
        SingleInstanceFactory instanceFactory$iv = new SingleInstanceFactory(def$iv);
        Module.saveMapping$default($this$single_u24default, mapping$iv, instanceFactory$iv, false, 4, null);
        if (createOnStart2 || $this$single_u24default.getCreatedAtStart()) {
            $this$single_u24default.getEagerInstances().add(instanceFactory$iv);
        }
        return new Pair($this$single_u24default, instanceFactory$iv);
    }

    @KoinReflectAPI
    public static final /* synthetic */ <T> Pair<Module, InstanceFactory<T>> single(Module $this$single, Qualifier qualifier, boolean createOnStart) {
        Intrinsics.checkNotNullParameter($this$single, "<this>");
        Intrinsics.needClassReification();
        Function2 definition$iv = new ModuleExtKt$single$1();
        Kind kind$iv$iv = Kind.Singleton;
        Qualifier scopeQualifier$iv$iv = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        List secondaryTypes$iv$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def$iv = new BeanDefinition(scopeQualifier$iv$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier, definition$iv, kind$iv$iv, secondaryTypes$iv$iv);
        String mapping$iv = BeanDefinitionKt.indexKey(def$iv.getPrimaryType(), qualifier, ScopeRegistry.INSTANCE.getRootScopeQualifier());
        SingleInstanceFactory instanceFactory$iv = new SingleInstanceFactory(def$iv);
        Module.saveMapping$default($this$single, mapping$iv, instanceFactory$iv, false, 4, null);
        if (createOnStart || $this$single.getCreatedAtStart()) {
            $this$single.getEagerInstances().add(instanceFactory$iv);
        }
        return new Pair<>($this$single, instanceFactory$iv);
    }

    public static /* synthetic */ Pair factory$default(Module $this$factory_u24default, Qualifier qualifier, int i, Object obj) {
        Qualifier qualifier2;
        if ((i & 1) == 0) {
            qualifier2 = qualifier;
        } else {
            qualifier2 = null;
        }
        Intrinsics.checkNotNullParameter($this$factory_u24default, "<this>");
        Intrinsics.needClassReification();
        Function2 definition$iv = new ModuleExtKt$factory$1();
        Qualifier scopeQualifier$iv$iv = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        Kind kind$iv$iv$iv = Kind.Factory;
        List secondaryTypes$iv$iv$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def$iv$iv = new BeanDefinition(scopeQualifier$iv$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier2, definition$iv, kind$iv$iv$iv, secondaryTypes$iv$iv$iv);
        String mapping$iv$iv = BeanDefinitionKt.indexKey(def$iv$iv.getPrimaryType(), qualifier2, scopeQualifier$iv$iv);
        FactoryInstanceFactory instanceFactory$iv$iv = new FactoryInstanceFactory(def$iv$iv);
        Module.saveMapping$default($this$factory_u24default, mapping$iv$iv, instanceFactory$iv$iv, false, 4, null);
        return new Pair($this$factory_u24default, instanceFactory$iv$iv);
    }

    @KoinReflectAPI
    public static final /* synthetic */ <T> Pair<Module, InstanceFactory<T>> factory(Module $this$factory, Qualifier qualifier) {
        Intrinsics.checkNotNullParameter($this$factory, "<this>");
        Intrinsics.needClassReification();
        Function2 definition$iv = new ModuleExtKt$factory$1();
        Qualifier scopeQualifier$iv$iv = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        Kind kind$iv$iv$iv = Kind.Factory;
        List secondaryTypes$iv$iv$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def$iv$iv = new BeanDefinition(scopeQualifier$iv$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier, definition$iv, kind$iv$iv$iv, secondaryTypes$iv$iv$iv);
        String mapping$iv$iv = BeanDefinitionKt.indexKey(def$iv$iv.getPrimaryType(), qualifier, scopeQualifier$iv$iv);
        FactoryInstanceFactory instanceFactory$iv$iv = new FactoryInstanceFactory(def$iv$iv);
        Module.saveMapping$default($this$factory, mapping$iv$iv, instanceFactory$iv$iv, false, 4, null);
        return new Pair<>($this$factory, instanceFactory$iv$iv);
    }
}
