package org.koin.core.module;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.BeanDefinitionKt;
import org.koin.core.definition.Kind;
import org.koin.core.instance.FactoryInstanceFactory;
import org.koin.core.instance.InstanceFactory;
import org.koin.core.instance.SingleInstanceFactory;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.qualifier.TypeQualifier;
import org.koin.core.registry.ScopeRegistry;
import org.koin.core.scope.Scope;
import org.koin.dsl.ScopeDSL;

/* compiled from: Module.kt */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J]\u0010\u001f\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H!0\u00150 \"\u0006\b\u0000\u0010!\u0018\u00012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u001c2)\b\b\u0010#\u001a#\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u0002H!0$j\b\u0012\u0004\u0012\u0002H!`'¢\u0006\u0002\b(H\u0086\bø\u0001\u0000Je\u0010\u001f\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H!0\u00150 \"\u0006\b\u0000\u0010!\u0018\u00012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u001c2)\b\b\u0010#\u001a#\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u0002H!0$j\b\u0012\u0004\u0012\u0002H!`'¢\u0006\u0002\b(2\u0006\u0010)\u001a\u00020\u001cH\u0081\bø\u0001\u0000J\u001d\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00000+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00000+H\u0086\u0002J\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00000+2\u0006\u0010-\u001a\u00020\u0000H\u0086\u0002J*\u0010.\u001a\u00020/2\n\u00100\u001a\u00060\u0013j\u0002`\u00142\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030\u00152\b\b\u0002\u00101\u001a\u00020\u0003H\u0001J'\u00102\u001a\u00020/2\u0006\u0010\"\u001a\u00020\u001c2\u0017\u00103\u001a\u0013\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u00020/04¢\u0006\u0002\b(J-\u00102\u001a\u00020/\"\u0006\b\u0000\u0010!\u0018\u00012\u0017\u00103\u001a\u0013\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u00020/04¢\u0006\u0002\b(H\u0086\bø\u0001\u0000Jg\u00106\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H!0\u00150 \"\u0006\b\u0000\u0010!\u0018\u00012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010\u0002\u001a\u00020\u00032)\b\b\u0010#\u001a#\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u0002H!0$j\b\u0012\u0004\u0012\u0002H!`'¢\u0006\u0002\b(H\u0086\bø\u0001\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006RT\u0010\u000b\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\bj\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t`\n2\u001e\u0010\u0007\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\bj\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t`\n@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0006RH\u0010\u0011\u001a.\u0012\b\u0012\u00060\u0013j\u0002`\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0012j\u0016\u0012\b\u0012\u00060\u0013j\u0002`\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0015`\u00168\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR,\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u001c0\bj\b\u0012\u0004\u0012\u00020\u001c`\n8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0018\u001a\u0004\b\u001e\u0010\r\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00067"}, d2 = {"Lorg/koin/core/module/Module;", "", "createdAtStart", "", "(Z)V", "getCreatedAtStart", "()Z", "<set-?>", "Ljava/util/HashSet;", "Lorg/koin/core/instance/SingleInstanceFactory;", "Lkotlin/collections/HashSet;", "eagerInstances", "getEagerInstances", "()Ljava/util/HashSet;", "setEagerInstances$koin_core", "(Ljava/util/HashSet;)V", "isLoaded", "mappings", "Ljava/util/HashMap;", "", "Lorg/koin/core/definition/IndexKey;", "Lorg/koin/core/instance/InstanceFactory;", "Lkotlin/collections/HashMap;", "getMappings$annotations", "()V", "getMappings", "()Ljava/util/HashMap;", "scopes", "Lorg/koin/core/qualifier/Qualifier;", "getScopes$annotations", "getScopes", "factory", "Lkotlin/Pair;", "T", "qualifier", "definition", "Lkotlin/Function2;", "Lorg/koin/core/scope/Scope;", "Lorg/koin/core/parameter/ParametersHolder;", "Lorg/koin/core/definition/Definition;", "Lkotlin/ExtensionFunctionType;", "scopeQualifier", "plus", "", "modules", "module", "saveMapping", "", "mapping", "allowOverride", "scope", "scopeSet", "Lkotlin/Function1;", "Lorg/koin/dsl/ScopeDSL;", "single", "koin-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class Module {
    private final boolean createdAtStart;
    private HashSet<SingleInstanceFactory<?>> eagerInstances;
    private final HashMap<String, InstanceFactory<?>> mappings;
    private final HashSet<Qualifier> scopes;

    public Module() {
        this(false, 1, null);
    }

    public static /* synthetic */ void getMappings$annotations() {
    }

    public static /* synthetic */ void getScopes$annotations() {
    }

    public Module(boolean createdAtStart) {
        this.createdAtStart = createdAtStart;
        this.eagerInstances = new HashSet<>();
        this.mappings = new HashMap<>();
        this.scopes = new HashSet<>();
    }

    public /* synthetic */ Module(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public final boolean getCreatedAtStart() {
        return this.createdAtStart;
    }

    public final HashSet<SingleInstanceFactory<?>> getEagerInstances() {
        return this.eagerInstances;
    }

    public final void setEagerInstances$koin_core(HashSet<SingleInstanceFactory<?>> hashSet) {
        Intrinsics.checkNotNullParameter(hashSet, "<set-?>");
        this.eagerInstances = hashSet;
    }

    public final HashMap<String, InstanceFactory<?>> getMappings() {
        return this.mappings;
    }

    public final boolean isLoaded() {
        return this.mappings.size() > 0;
    }

    public final HashSet<Qualifier> getScopes() {
        return this.scopes;
    }

    public final void scope(Qualifier qualifier, Function1<? super ScopeDSL, Unit> scopeSet) {
        Intrinsics.checkNotNullParameter(qualifier, "qualifier");
        Intrinsics.checkNotNullParameter(scopeSet, "scopeSet");
        scopeSet.invoke(new ScopeDSL(qualifier, this));
        this.scopes.add(qualifier);
    }

    public final /* synthetic */ <T> void scope(Function1<? super ScopeDSL, Unit> scopeSet) {
        Intrinsics.checkNotNullParameter(scopeSet, "scopeSet");
        Intrinsics.reifiedOperationMarker(4, "T");
        TypeQualifier qualifier = new TypeQualifier(Reflection.getOrCreateKotlinClass(Object.class));
        scopeSet.invoke(new ScopeDSL(qualifier, this));
        getScopes().add(qualifier);
    }

    public static /* synthetic */ Pair single$default(Module module, Qualifier qualifier, boolean createdAtStart, Function2 definition, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            createdAtStart = false;
        }
        Intrinsics.checkNotNullParameter(definition, "definition");
        Kind kind$iv = Kind.Singleton;
        Qualifier scopeQualifier$iv = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        List secondaryTypes$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def = new BeanDefinition(scopeQualifier$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier, definition, kind$iv, secondaryTypes$iv);
        String mapping = BeanDefinitionKt.indexKey(def.getPrimaryType(), qualifier, ScopeRegistry.INSTANCE.getRootScopeQualifier());
        SingleInstanceFactory instanceFactory = new SingleInstanceFactory(def);
        saveMapping$default(module, mapping, instanceFactory, false, 4, null);
        if (createdAtStart || module.getCreatedAtStart()) {
            module.getEagerInstances().add(instanceFactory);
        }
        return new Pair(module, instanceFactory);
    }

    public final /* synthetic */ <T> Pair<Module, InstanceFactory<T>> single(Qualifier qualifier, boolean createdAtStart, Function2<? super Scope, ? super ParametersHolder, ? extends T> definition) {
        Intrinsics.checkNotNullParameter(definition, "definition");
        Kind kind$iv = Kind.Singleton;
        Qualifier scopeQualifier$iv = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        List secondaryTypes$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def = new BeanDefinition(scopeQualifier$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier, definition, kind$iv, secondaryTypes$iv);
        String mapping = BeanDefinitionKt.indexKey(def.getPrimaryType(), qualifier, ScopeRegistry.INSTANCE.getRootScopeQualifier());
        SingleInstanceFactory instanceFactory = new SingleInstanceFactory(def);
        saveMapping$default(this, mapping, instanceFactory, false, 4, null);
        if (createdAtStart || getCreatedAtStart()) {
            getEagerInstances().add(instanceFactory);
        }
        return new Pair<>(this, instanceFactory);
    }

    public static /* synthetic */ void saveMapping$default(Module module, String str, InstanceFactory instanceFactory, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        module.saveMapping(str, instanceFactory, z);
    }

    public final void saveMapping(String mapping, InstanceFactory<?> factory, boolean allowOverride) {
        Intrinsics.checkNotNullParameter(mapping, "mapping");
        Intrinsics.checkNotNullParameter(factory, "factory");
        if (!allowOverride && this.mappings.containsKey(mapping)) {
            ModuleKt.overrideError(factory, mapping);
        }
        this.mappings.put(mapping, factory);
    }

    public static /* synthetic */ Pair factory$default(Module module, Qualifier qualifier, Function2 definition, int i, Object obj) {
        Qualifier qualifier2;
        if ((i & 1) == 0) {
            qualifier2 = qualifier;
        } else {
            qualifier2 = null;
        }
        Intrinsics.checkNotNullParameter(definition, "definition");
        Qualifier scopeQualifier$iv = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        Kind kind$iv$iv = Kind.Factory;
        List secondaryTypes$iv$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def$iv = new BeanDefinition(scopeQualifier$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier2, definition, kind$iv$iv, secondaryTypes$iv$iv);
        String mapping$iv = BeanDefinitionKt.indexKey(def$iv.getPrimaryType(), qualifier2, scopeQualifier$iv);
        FactoryInstanceFactory instanceFactory$iv = new FactoryInstanceFactory(def$iv);
        saveMapping$default(module, mapping$iv, instanceFactory$iv, false, 4, null);
        return new Pair(module, instanceFactory$iv);
    }

    public final /* synthetic */ <T> Pair<Module, InstanceFactory<T>> factory(Qualifier qualifier, Function2<? super Scope, ? super ParametersHolder, ? extends T> definition) {
        Intrinsics.checkNotNullParameter(definition, "definition");
        Qualifier scopeQualifier$iv = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        Kind kind$iv$iv = Kind.Factory;
        List secondaryTypes$iv$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def$iv = new BeanDefinition(scopeQualifier$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier, definition, kind$iv$iv, secondaryTypes$iv$iv);
        String mapping$iv = BeanDefinitionKt.indexKey(def$iv.getPrimaryType(), qualifier, scopeQualifier$iv);
        FactoryInstanceFactory instanceFactory$iv = new FactoryInstanceFactory(def$iv);
        saveMapping$default(this, mapping$iv, instanceFactory$iv, false, 4, null);
        return new Pair<>(this, instanceFactory$iv);
    }

    public static /* synthetic */ Pair factory$default(Module module, Qualifier qualifier, Function2 definition, Qualifier scopeQualifier, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        Intrinsics.checkNotNullParameter(definition, "definition");
        Intrinsics.checkNotNullParameter(scopeQualifier, "scopeQualifier");
        Kind kind$iv = Kind.Factory;
        List secondaryTypes$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def = new BeanDefinition(scopeQualifier, Reflection.getOrCreateKotlinClass(Object.class), qualifier, definition, kind$iv, secondaryTypes$iv);
        String mapping = BeanDefinitionKt.indexKey(def.getPrimaryType(), qualifier, scopeQualifier);
        FactoryInstanceFactory instanceFactory = new FactoryInstanceFactory(def);
        saveMapping$default(module, mapping, instanceFactory, false, 4, null);
        return new Pair(module, instanceFactory);
    }

    public final /* synthetic */ <T> Pair<Module, InstanceFactory<T>> factory(Qualifier qualifier, Function2<? super Scope, ? super ParametersHolder, ? extends T> definition, Qualifier scopeQualifier) {
        Intrinsics.checkNotNullParameter(definition, "definition");
        Intrinsics.checkNotNullParameter(scopeQualifier, "scopeQualifier");
        Kind kind$iv = Kind.Factory;
        List secondaryTypes$iv = CollectionsKt.emptyList();
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def = new BeanDefinition(scopeQualifier, Reflection.getOrCreateKotlinClass(Object.class), qualifier, definition, kind$iv, secondaryTypes$iv);
        String mapping = BeanDefinitionKt.indexKey(def.getPrimaryType(), qualifier, scopeQualifier);
        FactoryInstanceFactory instanceFactory = new FactoryInstanceFactory(def);
        saveMapping$default(this, mapping, instanceFactory, false, 4, null);
        return new Pair<>(this, instanceFactory);
    }

    public final List<Module> plus(Module module) {
        Intrinsics.checkNotNullParameter(module, "module");
        return CollectionsKt.listOf((Object[]) new Module[]{this, module});
    }

    public final List<Module> plus(List<Module> modules) {
        Intrinsics.checkNotNullParameter(modules, "modules");
        return CollectionsKt.plus((Collection) CollectionsKt.listOf(this), (Iterable) modules);
    }
}
