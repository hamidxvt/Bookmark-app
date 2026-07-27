package org.koin.core.registry;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.core.Koin;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.BeanDefinitionKt;
import org.koin.core.definition.Kind;
import org.koin.core.instance.InstanceContext;
import org.koin.core.instance.InstanceFactory;
import org.koin.core.instance.ScopedInstanceFactory;
import org.koin.core.instance.SingleInstanceFactory;
import org.koin.core.logger.Level;
import org.koin.core.module.Module;
import org.koin.core.module.ModuleKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
import org.koin.mp.KoinPlatformTools;

/* compiled from: InstanceRegistry.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u0014\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0016J\r\u0010\u0017\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0018J(\u0010\u0019\u001a\u00020\u00152\u001e\u0010\f\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\rj\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e`\u000fH\u0002JP\u0010\u001a\u001a\u00020\u0015\"\u0006\b\u0000\u0010\u001b\u0018\u00012\u0006\u0010\u001c\u001a\u0002H\u001b2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0012\b\u0002\u0010\u001f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030!0 2\b\b\u0002\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001eH\u0081\b¢\u0006\u0002\u0010%J\u0015\u0010&\u001a\u00020\u00152\u0006\u0010'\u001a\u00020(H\u0000¢\u0006\u0002\b)J-\u0010*\u001a\b\u0012\u0004\u0012\u0002H\u001b0 \"\u0004\b\u0000\u0010\u001b2\n\u0010+\u001a\u0006\u0012\u0002\b\u00030!2\u0006\u0010,\u001a\u00020-H\u0000¢\u0006\u0002\b.J\u0018\u0010/\u001a\u00020\u00152\u0006\u00100\u001a\u0002012\u0006\u0010\"\u001a\u00020#H\u0002J#\u00102\u001a\u00020\u00152\f\u00103\u001a\b\u0012\u0004\u0012\u0002010 2\u0006\u0010\"\u001a\u00020#H\u0000¢\u0006\u0002\b4J1\u00105\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t2\n\u0010+\u001a\u0006\u0012\u0002\b\u00030!2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010$\u001a\u00020\u001eH\u0000¢\u0006\u0002\b6J=\u00107\u001a\u0004\u0018\u0001H\u001b\"\u0004\b\u0000\u0010\u001b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\n\u0010+\u001a\u0006\u0012\u0002\b\u00030!2\u0006\u0010$\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020-H\u0000¢\u0006\u0004\b8\u00109J2\u0010:\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020#2\n\u0010;\u001a\u00060\u0007j\u0002`\b2\n\u0010<\u001a\u0006\u0012\u0002\b\u00030\t2\b\b\u0002\u0010=\u001a\u00020#H\u0007J\u0006\u0010>\u001a\u00020?J\u0010\u0010@\u001a\u00020\u00152\u0006\u00100\u001a\u000201H\u0002J\u001b\u0010A\u001a\u00020\u00152\f\u00103\u001a\b\u0012\u0004\u0012\u0002010 H\u0000¢\u0006\u0002\bBR\"\u0010\u0005\u001a\u0016\u0012\b\u0012\u00060\u0007j\u0002`\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR&\u0010\f\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\rj\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R%\u0010\u0010\u001a\u0016\u0012\b\u0012\u00060\u0007j\u0002`\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006C"}, d2 = {"Lorg/koin/core/registry/InstanceRegistry;", "", "_koin", "Lorg/koin/core/Koin;", "(Lorg/koin/core/Koin;)V", "_instances", "", "", "Lorg/koin/core/definition/IndexKey;", "Lorg/koin/core/instance/InstanceFactory;", "get_koin", "()Lorg/koin/core/Koin;", "eagerInstances", "Ljava/util/HashSet;", "Lorg/koin/core/instance/SingleInstanceFactory;", "Lkotlin/collections/HashSet;", "instances", "", "getInstances", "()Ljava/util/Map;", "close", "", "close$koin_core", "createAllEagerInstances", "createAllEagerInstances$koin_core", "createEagerInstances", "declareInstance", "T", "instance", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "secondaryTypes", "", "Lkotlin/reflect/KClass;", "allowOverride", "", "scopeQualifier", "(Ljava/lang/Object;Lorg/koin/core/qualifier/Qualifier;Ljava/util/List;ZLorg/koin/core/qualifier/Qualifier;)V", "dropScopeInstances", "scope", "Lorg/koin/core/scope/Scope;", "dropScopeInstances$koin_core", "getAll", "clazz", "instanceContext", "Lorg/koin/core/instance/InstanceContext;", "getAll$koin_core", "loadModule", "module", "Lorg/koin/core/module/Module;", "loadModules", "modules", "loadModules$koin_core", "resolveDefinition", "resolveDefinition$koin_core", "resolveInstance", "resolveInstance$koin_core", "(Lorg/koin/core/qualifier/Qualifier;Lkotlin/reflect/KClass;Lorg/koin/core/qualifier/Qualifier;Lorg/koin/core/instance/InstanceContext;)Ljava/lang/Object;", "saveMapping", "mapping", "factory", "logWarning", "size", "", "unloadModule", "unloadModules", "unloadModules$koin_core", "koin-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class InstanceRegistry {
    private final Map<String, InstanceFactory<?>> _instances;
    private final Koin _koin;
    private final HashSet<SingleInstanceFactory<?>> eagerInstances;

    public InstanceRegistry(Koin _koin) {
        Intrinsics.checkNotNullParameter(_koin, "_koin");
        this._koin = _koin;
        this._instances = KoinPlatformTools.INSTANCE.safeHashMap();
        this.eagerInstances = new HashSet<>();
    }

    public final Koin get_koin() {
        return this._koin;
    }

    public final Map<String, InstanceFactory<?>> getInstances() {
        return this._instances;
    }

    public final void loadModules$koin_core(List<Module> modules, boolean allowOverride) {
        Intrinsics.checkNotNullParameter(modules, "modules");
        List<Module> $this$forEach$iv = modules;
        for (Object element$iv : $this$forEach$iv) {
            Module module = (Module) element$iv;
            loadModule(module, allowOverride);
            this.eagerInstances.addAll(module.getEagerInstances());
        }
    }

    public final void createAllEagerInstances$koin_core() {
        createEagerInstances(this.eagerInstances);
        this.eagerInstances.clear();
    }

    private final void loadModule(Module module, boolean allowOverride) {
        Map $this$forEach$iv = module.getMappings();
        for (Map.Entry element$iv : $this$forEach$iv.entrySet()) {
            String mapping = element$iv.getKey();
            InstanceFactory<?> factory = element$iv.getValue();
            saveMapping$default(this, allowOverride, mapping, factory, false, 8, null);
        }
    }

    public static /* synthetic */ void saveMapping$default(InstanceRegistry instanceRegistry, boolean z, String str, InstanceFactory instanceFactory, boolean z2, int i, Object obj) {
        if ((i & 8) != 0) {
            z2 = true;
        }
        instanceRegistry.saveMapping(z, str, instanceFactory, z2);
    }

    public final void saveMapping(boolean allowOverride, String mapping, InstanceFactory<?> factory, boolean logWarning) {
        Intrinsics.checkNotNullParameter(mapping, "mapping");
        Intrinsics.checkNotNullParameter(factory, "factory");
        if (this._instances.containsKey(mapping)) {
            if (!allowOverride) {
                ModuleKt.overrideError(factory, mapping);
            } else if (logWarning) {
                this._koin.getLogger().info("Override Mapping '" + mapping + "' with " + factory.getBeanDefinition());
            }
        }
        if (this._koin.getLogger().isAt(Level.DEBUG) && logWarning) {
            this._koin.getLogger().debug("add mapping '" + mapping + "' for " + factory.getBeanDefinition());
        }
        this._instances.put(mapping, factory);
    }

    private final void createEagerInstances(HashSet<SingleInstanceFactory<?>> eagerInstances) {
        if (!eagerInstances.isEmpty()) {
            if (this._koin.getLogger().isAt(Level.DEBUG)) {
                this._koin.getLogger().debug("Creating eager instances ...");
            }
            InstanceContext defaultContext = new InstanceContext(this._koin, this._koin.getScopeRegistry().getRootScope(), null, 4, null);
            HashSet<SingleInstanceFactory<?>> $this$forEach$iv = eagerInstances;
            for (Object element$iv : $this$forEach$iv) {
                SingleInstanceFactory factory = (SingleInstanceFactory) element$iv;
                factory.get(defaultContext);
            }
        }
    }

    public final InstanceFactory<?> resolveDefinition$koin_core(KClass<?> clazz, Qualifier qualifier, Qualifier scopeQualifier) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(scopeQualifier, "scopeQualifier");
        String indexKey = BeanDefinitionKt.indexKey(clazz, qualifier, scopeQualifier);
        return this._instances.get(indexKey);
    }

    public final <T> T resolveInstance$koin_core(Qualifier qualifier, KClass<?> clazz, Qualifier scopeQualifier, InstanceContext instanceContext) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(scopeQualifier, "scopeQualifier");
        Intrinsics.checkNotNullParameter(instanceContext, "instanceContext");
        InstanceFactory<?> resolveDefinition$koin_core = resolveDefinition$koin_core(clazz, qualifier, scopeQualifier);
        if (resolveDefinition$koin_core == null) {
            return null;
        }
        return (T) resolveDefinition$koin_core.get(instanceContext);
    }

    public static /* synthetic */ void declareInstance$default(InstanceRegistry instanceRegistry, Object instance, Qualifier qualifier, List secondaryTypes, boolean allowOverride, Qualifier scopeQualifier, int i, Object obj) {
        Qualifier qualifier2 = (i & 2) != 0 ? null : qualifier;
        List secondaryTypes2 = (i & 4) != 0 ? CollectionsKt.emptyList() : secondaryTypes;
        boolean allowOverride2 = (i & 8) != 0 ? true : allowOverride;
        Intrinsics.checkNotNullParameter(secondaryTypes2, "secondaryTypes");
        Intrinsics.checkNotNullParameter(scopeQualifier, "scopeQualifier");
        Kind kind$iv = Kind.Scoped;
        Intrinsics.needClassReification();
        Function2 definition$iv = new InstanceRegistry$declareInstance$def$1(instance);
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def = new BeanDefinition(scopeQualifier, Reflection.getOrCreateKotlinClass(Object.class), qualifier2, definition$iv, kind$iv, secondaryTypes2);
        ScopedInstanceFactory factory = new ScopedInstanceFactory(def);
        String indexKey = BeanDefinitionKt.indexKey(def.getPrimaryType(), def.getQualifier(), def.getScopeQualifier());
        saveMapping$default(instanceRegistry, allowOverride2, indexKey, factory, false, 8, null);
        Iterable $this$forEach$iv = def.getSecondaryTypes();
        for (Object element$iv : $this$forEach$iv) {
            KClass clazz = (KClass) element$iv;
            String index = BeanDefinitionKt.indexKey(clazz, def.getQualifier(), def.getScopeQualifier());
            saveMapping$default(instanceRegistry, allowOverride2, index, factory, false, 8, null);
        }
    }

    public final /* synthetic */ <T> void declareInstance(T instance, Qualifier qualifier, List<? extends KClass<?>> secondaryTypes, boolean allowOverride, Qualifier scopeQualifier) {
        Intrinsics.checkNotNullParameter(secondaryTypes, "secondaryTypes");
        Intrinsics.checkNotNullParameter(scopeQualifier, "scopeQualifier");
        Kind kind$iv = Kind.Scoped;
        Intrinsics.needClassReification();
        Function2 definition$iv = new InstanceRegistry$declareInstance$def$1(instance);
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def = new BeanDefinition(scopeQualifier, Reflection.getOrCreateKotlinClass(Object.class), qualifier, definition$iv, kind$iv, secondaryTypes);
        ScopedInstanceFactory factory = new ScopedInstanceFactory(def);
        String indexKey = BeanDefinitionKt.indexKey(def.getPrimaryType(), def.getQualifier(), def.getScopeQualifier());
        saveMapping$default(this, allowOverride, indexKey, factory, false, 8, null);
        Iterable $this$forEach$iv = def.getSecondaryTypes();
        for (Object element$iv : $this$forEach$iv) {
            KClass clazz = (KClass) element$iv;
            String index = BeanDefinitionKt.indexKey(clazz, def.getQualifier(), def.getScopeQualifier());
            saveMapping$default(this, allowOverride, index, factory, false, 8, null);
        }
    }

    public final void dropScopeInstances$koin_core(Scope scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Iterable $this$filterIsInstance$iv = this._instances.values();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof ScopedInstanceFactory) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$forEach$iv = (List) destination$iv$iv;
        for (Object element$iv : $this$forEach$iv) {
            ScopedInstanceFactory factory = (ScopedInstanceFactory) element$iv;
            factory.drop(scope);
        }
    }

    public final void close$koin_core() {
        Map $this$forEach$iv = this._instances;
        for (Map.Entry element$iv : $this$forEach$iv.entrySet()) {
            element$iv.getKey();
            InstanceFactory<?> factory = element$iv.getValue();
            factory.dropAll();
        }
        Map $this$forEach$iv2 = this._instances;
        $this$forEach$iv2.clear();
    }

    public final <T> List<T> getAll$koin_core(KClass<?> clazz, InstanceContext instanceContext) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(instanceContext, "instanceContext");
        Iterable $this$filter$iv = this._instances.values();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            if (Intrinsics.areEqual(((InstanceFactory) element$iv$iv).getBeanDefinition().getScopeQualifier(), instanceContext.getScope().getScopeQualifier())) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$filter$iv2 = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            InstanceFactory factory = (InstanceFactory) element$iv$iv2;
            if (Intrinsics.areEqual(factory.getBeanDefinition().getPrimaryType(), clazz) || factory.getBeanDefinition().getSecondaryTypes().contains(clazz)) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        Iterable $this$map$iv = CollectionsKt.distinct((List) destination$iv$iv2);
        Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            InstanceFactory it = (InstanceFactory) item$iv$iv;
            destination$iv$iv3.add(it.get(instanceContext));
        }
        return (List) destination$iv$iv3;
    }

    public final void unloadModules$koin_core(List<Module> modules) {
        Intrinsics.checkNotNullParameter(modules, "modules");
        List<Module> $this$forEach$iv = modules;
        for (Object element$iv : $this$forEach$iv) {
            Module it = (Module) element$iv;
            unloadModule(it);
        }
    }

    private final void unloadModule(Module module) {
        Iterable keySet = module.getMappings().keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "module.mappings.keys");
        Iterable $this$forEach$iv = keySet;
        for (Object element$iv : $this$forEach$iv) {
            String mapping = (String) element$iv;
            if (this._instances.containsKey(mapping)) {
                InstanceFactory<?> instanceFactory = this._instances.get(mapping);
                if (instanceFactory != null) {
                    instanceFactory.dropAll();
                }
                this._instances.remove(mapping);
            }
        }
    }

    public final int size() {
        return this._instances.size();
    }
}
