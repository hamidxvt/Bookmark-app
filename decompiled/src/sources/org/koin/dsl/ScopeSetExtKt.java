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
import org.koin.core.instance.ScopedInstanceFactory;
import org.koin.core.module.Module;
import org.koin.core.qualifier.Qualifier;

/* compiled from: ScopeSetExt.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a7\u0010\u0000\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00030\u0001\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0005*\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0087\b\u001a7\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00030\u0001\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0005*\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0087\b¨\u0006\n"}, d2 = {"factory", "Lkotlin/Pair;", "Lorg/koin/core/module/Module;", "Lorg/koin/core/instance/InstanceFactory;", "R", "", "Lorg/koin/dsl/ScopeDSL;", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "scoped", "koin-core"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class ScopeSetExtKt {
    public static /* synthetic */ Pair scoped$default(ScopeDSL $this$scoped_u24default, Qualifier qualifier, int i, Object obj) {
        Qualifier qualifier2;
        if ((i & 1) == 0) {
            qualifier2 = qualifier;
        } else {
            qualifier2 = null;
        }
        Intrinsics.checkNotNullParameter($this$scoped_u24default, "<this>");
        Intrinsics.needClassReification();
        Function2 definition$iv = new ScopeSetExtKt$scoped$1();
        Kind kind$iv$iv = Kind.Scoped;
        Qualifier scopeQualifier$iv$iv = $this$scoped_u24default.getScopeQualifier();
        List secondaryTypes$iv$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "R");
        BeanDefinition def$iv = new BeanDefinition(scopeQualifier$iv$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier2, definition$iv, kind$iv$iv, secondaryTypes$iv$iv);
        String mapping$iv = BeanDefinitionKt.indexKey(def$iv.getPrimaryType(), qualifier2, $this$scoped_u24default.getScopeQualifier());
        ScopedInstanceFactory instanceFactory$iv = new ScopedInstanceFactory(def$iv);
        Module.saveMapping$default($this$scoped_u24default.getModule(), mapping$iv, instanceFactory$iv, false, 4, null);
        return new Pair($this$scoped_u24default.getModule(), instanceFactory$iv);
    }

    @KoinReflectAPI
    public static final /* synthetic */ <R> Pair<Module, InstanceFactory<R>> scoped(ScopeDSL $this$scoped, Qualifier qualifier) {
        Intrinsics.checkNotNullParameter($this$scoped, "<this>");
        Intrinsics.needClassReification();
        Function2 definition$iv = new ScopeSetExtKt$scoped$1();
        Kind kind$iv$iv = Kind.Scoped;
        Qualifier scopeQualifier$iv$iv = $this$scoped.getScopeQualifier();
        List secondaryTypes$iv$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "R");
        BeanDefinition def$iv = new BeanDefinition(scopeQualifier$iv$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier, definition$iv, kind$iv$iv, secondaryTypes$iv$iv);
        String mapping$iv = BeanDefinitionKt.indexKey(def$iv.getPrimaryType(), qualifier, $this$scoped.getScopeQualifier());
        ScopedInstanceFactory instanceFactory$iv = new ScopedInstanceFactory(def$iv);
        Module.saveMapping$default($this$scoped.getModule(), mapping$iv, instanceFactory$iv, false, 4, null);
        return new Pair<>($this$scoped.getModule(), instanceFactory$iv);
    }

    public static /* synthetic */ Pair factory$default(ScopeDSL $this$factory_u24default, Qualifier qualifier, int i, Object obj) {
        Qualifier qualifier2;
        if ((i & 1) == 0) {
            qualifier2 = qualifier;
        } else {
            qualifier2 = null;
        }
        Intrinsics.checkNotNullParameter($this$factory_u24default, "<this>");
        Intrinsics.needClassReification();
        Function2 definition$iv = new ScopeSetExtKt$factory$1();
        Module this_$iv$iv = $this$factory_u24default.getModule();
        Qualifier scopeQualifier$iv$iv = $this$factory_u24default.getScopeQualifier();
        Kind kind$iv$iv$iv = Kind.Factory;
        List secondaryTypes$iv$iv$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "R");
        BeanDefinition def$iv$iv = new BeanDefinition(scopeQualifier$iv$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier2, definition$iv, kind$iv$iv$iv, secondaryTypes$iv$iv$iv);
        String mapping$iv$iv = BeanDefinitionKt.indexKey(def$iv$iv.getPrimaryType(), qualifier2, scopeQualifier$iv$iv);
        FactoryInstanceFactory instanceFactory$iv$iv = new FactoryInstanceFactory(def$iv$iv);
        Module.saveMapping$default(this_$iv$iv, mapping$iv$iv, instanceFactory$iv$iv, false, 4, null);
        return new Pair(this_$iv$iv, instanceFactory$iv$iv);
    }

    @KoinReflectAPI
    public static final /* synthetic */ <R> Pair<Module, InstanceFactory<R>> factory(ScopeDSL $this$factory, Qualifier qualifier) {
        Intrinsics.checkNotNullParameter($this$factory, "<this>");
        Intrinsics.needClassReification();
        Function2 definition$iv = new ScopeSetExtKt$factory$1();
        Module this_$iv$iv = $this$factory.getModule();
        Qualifier scopeQualifier$iv$iv = $this$factory.getScopeQualifier();
        Kind kind$iv$iv$iv = Kind.Factory;
        List secondaryTypes$iv$iv$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "R");
        BeanDefinition def$iv$iv = new BeanDefinition(scopeQualifier$iv$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier, definition$iv, kind$iv$iv$iv, secondaryTypes$iv$iv$iv);
        String mapping$iv$iv = BeanDefinitionKt.indexKey(def$iv$iv.getPrimaryType(), qualifier, scopeQualifier$iv$iv);
        FactoryInstanceFactory instanceFactory$iv$iv = new FactoryInstanceFactory(def$iv$iv);
        Module.saveMapping$default(this_$iv$iv, mapping$iv$iv, instanceFactory$iv$iv, false, 4, null);
        return new Pair<>(this_$iv$iv, instanceFactory$iv$iv);
    }
}
