package org.koin.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.core.component.KoinScopeComponent;
import org.koin.core.component.KoinScopeComponentKt;
import org.koin.core.error.ScopeNotCreatedException;
import org.koin.core.logger.EmptyLogger;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.module.Module;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.qualifier.TypeQualifier;
import org.koin.core.registry.InstanceRegistry;
import org.koin.core.registry.PropertyRegistry;
import org.koin.core.registry.ScopeRegistry;
import org.koin.core.scope.Scope;
import org.koin.core.time.MeasureKt;
import org.koin.mp.KoinPlatformTools;

/* compiled from: Koin.kt */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0018J\u001d\u0010\u001a\u001a\u00020\u001b\"\b\b\u0000\u0010\u001c*\u00020\u001d2\u0006\u0010\u001e\u001a\u0002H\u001c¢\u0006\u0002\u0010\u001fJ#\u0010\u001a\u001a\u00020\u001b\"\n\b\u0000\u0010\u001c\u0018\u0001*\u00020\u00012\f\b\u0002\u0010 \u001a\u00060!j\u0002`\"H\u0086\bJ-\u0010\u001a\u001a\u00020\u001b\"\n\b\u0000\u0010\u001c\u0018\u0001*\u00020\u00012\n\u0010 \u001a\u00060!j\u0002`\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0001H\u0086\bJ&\u0010\u001a\u001a\u00020\u001b2\n\u0010 \u001a\u00060!j\u0002`\"2\u0006\u0010$\u001a\u00020%2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0001JH\u0010&\u001a\u00020\u0018\"\u0006\b\u0000\u0010\u001c\u0018\u00012\u0006\u0010'\u001a\u0002H\u001c2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\u0012\b\u0002\u0010(\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030*0)2\b\b\u0002\u0010+\u001a\u00020,H\u0086\b¢\u0006\u0002\u0010-J\u000e\u0010.\u001a\u00020\u00182\u0006\u0010/\u001a\u00020!J\u0012\u00100\u001a\u00020\u00182\n\u0010 \u001a\u00060!j\u0002`\"JA\u00101\u001a\u0002H\u001c\"\u0004\b\u0000\u0010\u001c2\n\u00102\u001a\u0006\u0012\u0002\b\u00030*2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\u0016\b\u0002\u00103\u001a\u0010\u0012\u0004\u0012\u000205\u0018\u000104j\u0004\u0018\u0001`6¢\u0006\u0002\u00107JA\u00101\u001a\u0002H\u001c\"\n\b\u0000\u0010\u001c\u0018\u0001*\u00020\u00012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\u0016\b\n\u00103\u001a\u0010\u0012\u0004\u0012\u000205\u0018\u000104j\u0004\u0018\u0001`6H\u0086\bø\u0001\u0000¢\u0006\u0002\u00108J\u0017\u00109\u001a\b\u0012\u0004\u0012\u0002H\u001c0)\"\u0006\b\u0000\u0010\u001c\u0018\u0001H\u0086\bJ!\u0010:\u001a\u00020\u001b\"\n\b\u0000\u0010\u001c\u0018\u0001*\u00020\u00012\n\u0010 \u001a\u00060!j\u0002`\"H\u0086\bJ&\u0010:\u001a\u00020\u001b2\n\u0010 \u001a\u00060!j\u0002`\"2\u0006\u0010$\u001a\u00020%2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0001JC\u0010;\u001a\u0004\u0018\u0001H\u001c\"\u0004\b\u0000\u0010\u001c2\n\u00102\u001a\u0006\u0012\u0002\b\u00030*2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\u0016\b\u0002\u00103\u001a\u0010\u0012\u0004\u0012\u000205\u0018\u000104j\u0004\u0018\u0001`6¢\u0006\u0002\u00107JC\u0010;\u001a\u0004\u0018\u0001H\u001c\"\n\b\u0000\u0010\u001c\u0018\u0001*\u00020\u00012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\u0016\b\n\u00103\u001a\u0010\u0012\u0004\u0012\u000205\u0018\u000104j\u0004\u0018\u0001`6H\u0086\bø\u0001\u0000¢\u0006\u0002\u00108J\u001f\u0010<\u001a\u0004\u0018\u0001H\u001c\"\b\b\u0000\u0010\u001c*\u00020\u00012\u0006\u0010/\u001a\u00020!¢\u0006\u0002\u0010=J%\u0010<\u001a\u0002H\u001c\"\b\b\u0000\u0010\u001c*\u00020\u00012\u0006\u0010/\u001a\u00020!2\u0006\u0010>\u001a\u0002H\u001c¢\u0006\u0002\u0010?J\u0012\u0010@\u001a\u00020\u001b2\n\u0010 \u001a\u00060!j\u0002`\"J\u0014\u0010A\u001a\u0004\u0018\u00010\u001b2\n\u0010 \u001a\u00060!j\u0002`\"JL\u0010B\u001a\b\u0012\u0004\u0012\u0002H\u001c0C\"\n\b\u0000\u0010\u001c\u0018\u0001*\u00020\u00012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\b\b\u0002\u0010D\u001a\u00020E2\u0016\b\n\u00103\u001a\u0010\u0012\u0004\u0012\u000205\u0018\u000104j\u0004\u0018\u0001`6H\u0086\bø\u0001\u0000JN\u0010F\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u001c0C\"\n\b\u0000\u0010\u001c\u0018\u0001*\u00020\u00012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\b\b\u0002\u0010D\u001a\u00020E2\u0016\b\n\u00103\u001a\u0010\u0012\u0004\u0012\u000205\u0018\u000104j\u0004\u0018\u0001`6H\u0086\bø\u0001\u0000J\u001e\u0010G\u001a\u00020\u00182\f\u0010H\u001a\b\u0012\u0004\u0012\u00020I0)2\b\b\u0002\u0010+\u001a\u00020,J\u0016\u0010J\u001a\u00020\u00182\u0006\u0010/\u001a\u00020!2\u0006\u0010K\u001a\u00020\u0001J\u0010\u0010L\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\tH\u0007J\u0014\u0010M\u001a\u00020\u00182\f\u0010H\u001a\b\u0012\u0004\u0012\u00020I0)R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0002\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u00020\u00138\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0002\u001a\u0004\b\u0015\u0010\u0016\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006N"}, d2 = {"Lorg/koin/core/Koin;", "", "()V", "instanceRegistry", "Lorg/koin/core/registry/InstanceRegistry;", "getInstanceRegistry$annotations", "getInstanceRegistry", "()Lorg/koin/core/registry/InstanceRegistry;", "<set-?>", "Lorg/koin/core/logger/Logger;", "logger", "getLogger", "()Lorg/koin/core/logger/Logger;", "propertyRegistry", "Lorg/koin/core/registry/PropertyRegistry;", "getPropertyRegistry$annotations", "getPropertyRegistry", "()Lorg/koin/core/registry/PropertyRegistry;", "scopeRegistry", "Lorg/koin/core/registry/ScopeRegistry;", "getScopeRegistry$annotations", "getScopeRegistry", "()Lorg/koin/core/registry/ScopeRegistry;", "close", "", "createEagerInstances", "createScope", "Lorg/koin/core/scope/Scope;", "T", "Lorg/koin/core/component/KoinScopeComponent;", "t", "(Lorg/koin/core/component/KoinScopeComponent;)Lorg/koin/core/scope/Scope;", "scopeId", "", "Lorg/koin/core/scope/ScopeID;", "source", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "declare", "instance", "secondaryTypes", "", "Lkotlin/reflect/KClass;", "allowOverride", "", "(Ljava/lang/Object;Lorg/koin/core/qualifier/Qualifier;Ljava/util/List;Z)V", "deleteProperty", "key", "deleteScope", Constant.RetrofitConstants.RETROFIT_METHOD_GET, "clazz", "parameters", "Lkotlin/Function0;", "Lorg/koin/core/parameter/ParametersHolder;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Lkotlin/reflect/KClass;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "(Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getAll", "getOrCreateScope", "getOrNull", "getProperty", "(Ljava/lang/String;)Ljava/lang/Object;", "defaultValue", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "getScope", "getScopeOrNull", "inject", "Lkotlin/Lazy;", "mode", "Lkotlin/LazyThreadSafetyMode;", "injectOrNull", "loadModules", "modules", "Lorg/koin/core/module/Module;", "setProperty", "value", "setupLogger", "unloadModules", "koin-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class Koin {
    private final ScopeRegistry scopeRegistry = new ScopeRegistry(this);
    private final InstanceRegistry instanceRegistry = new InstanceRegistry(this);
    private final PropertyRegistry propertyRegistry = new PropertyRegistry(this);
    private Logger logger = new EmptyLogger();

    public static /* synthetic */ void getInstanceRegistry$annotations() {
    }

    public static /* synthetic */ void getPropertyRegistry$annotations() {
    }

    public static /* synthetic */ void getScopeRegistry$annotations() {
    }

    public final ScopeRegistry getScopeRegistry() {
        return this.scopeRegistry;
    }

    public final InstanceRegistry getInstanceRegistry() {
        return this.instanceRegistry;
    }

    public final PropertyRegistry getPropertyRegistry() {
        return this.propertyRegistry;
    }

    public final Logger getLogger() {
        return this.logger;
    }

    public final void setupLogger(Logger logger) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.logger = logger;
    }

    public static /* synthetic */ Lazy inject$default(Koin koin, Qualifier qualifier, LazyThreadSafetyMode mode, Function0 parameters, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            mode = KoinPlatformTools.INSTANCE.defaultLazyMode();
        }
        if ((i & 4) != 0) {
            parameters = null;
        }
        Intrinsics.checkNotNullParameter(mode, "mode");
        Scope this_$iv = koin.getScopeRegistry().getRootScope();
        Intrinsics.needClassReification();
        return LazyKt.lazy(mode, (Function0) new Koin$inject$$inlined$inject$1(this_$iv, qualifier, parameters));
    }

    public final /* synthetic */ <T> Lazy<T> inject(Qualifier qualifier, LazyThreadSafetyMode mode, Function0<? extends ParametersHolder> parameters) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Scope this_$iv = getScopeRegistry().getRootScope();
        Intrinsics.needClassReification();
        return LazyKt.lazy(mode, (Function0) new Koin$inject$$inlined$inject$1(this_$iv, qualifier, parameters));
    }

    public static /* synthetic */ Lazy injectOrNull$default(Koin koin, Qualifier qualifier, LazyThreadSafetyMode mode, Function0 parameters, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            mode = KoinPlatformTools.INSTANCE.defaultLazyMode();
        }
        if ((i & 4) != 0) {
            parameters = null;
        }
        Intrinsics.checkNotNullParameter(mode, "mode");
        Scope this_$iv = koin.getScopeRegistry().getRootScope();
        Intrinsics.needClassReification();
        return LazyKt.lazy(mode, (Function0) new Koin$injectOrNull$$inlined$injectOrNull$1(this_$iv, qualifier, parameters));
    }

    public final /* synthetic */ <T> Lazy<T> injectOrNull(Qualifier qualifier, LazyThreadSafetyMode mode, Function0<? extends ParametersHolder> parameters) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Scope this_$iv = getScopeRegistry().getRootScope();
        Intrinsics.needClassReification();
        return LazyKt.lazy(mode, (Function0) new Koin$injectOrNull$$inlined$injectOrNull$1(this_$iv, qualifier, parameters));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object get$default(Koin koin, Qualifier qualifier, Function0 parameters, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            parameters = null;
        }
        Scope rootScope = koin.getScopeRegistry().getRootScope();
        Intrinsics.reifiedOperationMarker(4, "T");
        return rootScope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, parameters);
    }

    public final /* synthetic */ <T> T get(Qualifier qualifier, Function0<? extends ParametersHolder> parameters) {
        Scope rootScope = getScopeRegistry().getRootScope();
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) rootScope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, parameters);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object getOrNull$default(Koin koin, Qualifier qualifier, Function0 parameters, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            parameters = null;
        }
        Scope rootScope = koin.getScopeRegistry().getRootScope();
        Intrinsics.reifiedOperationMarker(4, "T");
        return rootScope.getOrNull(Reflection.getOrCreateKotlinClass(Object.class), qualifier, parameters);
    }

    public final /* synthetic */ <T> T getOrNull(Qualifier qualifier, Function0<? extends ParametersHolder> parameters) {
        Scope rootScope = getScopeRegistry().getRootScope();
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) rootScope.getOrNull(Reflection.getOrCreateKotlinClass(Object.class), qualifier, parameters);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object get$default(Koin koin, KClass kClass, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        return koin.get(kClass, qualifier, function0);
    }

    public final <T> T get(KClass<?> clazz, Qualifier qualifier, Function0<? extends ParametersHolder> parameters) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return (T) this.scopeRegistry.getRootScope().get(clazz, qualifier, parameters);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object getOrNull$default(Koin koin, KClass kClass, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        return koin.getOrNull(kClass, qualifier, function0);
    }

    public final <T> T getOrNull(KClass<?> clazz, Qualifier qualifier, Function0<? extends ParametersHolder> parameters) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return (T) this.scopeRegistry.getRootScope().getOrNull(clazz, qualifier, parameters);
    }

    public static /* synthetic */ void declare$default(Koin koin, Object instance, Qualifier qualifier, List secondaryTypes, boolean allowOverride, int i, Object obj) {
        Qualifier qualifier2 = (i & 2) != 0 ? null : qualifier;
        List secondaryTypes2 = (i & 4) != 0 ? CollectionsKt.emptyList() : secondaryTypes;
        boolean allowOverride2 = (i & 8) != 0 ? true : allowOverride;
        Intrinsics.checkNotNullParameter(secondaryTypes2, "secondaryTypes");
        Intrinsics.reifiedOperationMarker(4, "T");
        List firstType = CollectionsKt.listOf(Reflection.getOrCreateKotlinClass(Object.class));
        Scope this_$iv = koin.getScopeRegistry().getRootScope();
        List secondaryTypes$iv = CollectionsKt.plus((Collection) firstType, (Iterable) secondaryTypes2);
        KoinPlatformTools koinPlatformTools = KoinPlatformTools.INSTANCE;
        Intrinsics.needClassReification();
        koinPlatformTools.m2315synchronized(this_$iv, new Koin$declare$$inlined$declare$1(this_$iv, instance, qualifier2, secondaryTypes$iv, allowOverride2));
    }

    public final /* synthetic */ <T> void declare(T instance, Qualifier qualifier, List<? extends KClass<?>> secondaryTypes, boolean allowOverride) {
        Intrinsics.checkNotNullParameter(secondaryTypes, "secondaryTypes");
        Intrinsics.reifiedOperationMarker(4, "T");
        List firstType = CollectionsKt.listOf(Reflection.getOrCreateKotlinClass(Object.class));
        Scope this_$iv = getScopeRegistry().getRootScope();
        List secondaryTypes$iv = CollectionsKt.plus((Collection) firstType, (Iterable) secondaryTypes);
        KoinPlatformTools koinPlatformTools = KoinPlatformTools.INSTANCE;
        Intrinsics.needClassReification();
        koinPlatformTools.m2315synchronized(this_$iv, new Koin$declare$$inlined$declare$1(this_$iv, instance, qualifier, secondaryTypes$iv, allowOverride));
    }

    public final /* synthetic */ <T> List<T> getAll() {
        Scope this_$iv = getScopeRegistry().getRootScope();
        Intrinsics.reifiedOperationMarker(4, "T");
        return this_$iv.getAll(Reflection.getOrCreateKotlinClass(Object.class));
    }

    public static /* synthetic */ Scope createScope$default(Koin koin, String str, Qualifier qualifier, Object obj, int i, Object obj2) {
        if ((i & 4) != 0) {
            obj = null;
        }
        return koin.createScope(str, qualifier, obj);
    }

    public final Scope createScope(final String scopeId, final Qualifier qualifier, Object source) {
        Intrinsics.checkNotNullParameter(scopeId, "scopeId");
        Intrinsics.checkNotNullParameter(qualifier, "qualifier");
        this.logger.log(Level.DEBUG, new Function0<String>() { // from class: org.koin.core.Koin$createScope$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "|- create scope - id:'" + scopeId + "' q:" + qualifier;
            }
        });
        return this.scopeRegistry.createScope(scopeId, qualifier, source);
    }

    public static /* synthetic */ Scope createScope$default(Koin koin, String scopeId, Object source, int i, Object obj) {
        if ((i & 2) != 0) {
            source = null;
        }
        Intrinsics.checkNotNullParameter(scopeId, "scopeId");
        Intrinsics.reifiedOperationMarker(4, "T");
        TypeQualifier qualifier = new TypeQualifier(Reflection.getOrCreateKotlinClass(Object.class));
        koin.getLogger().log(Level.DEBUG, new Koin$createScope$2(scopeId, qualifier));
        return koin.getScopeRegistry().createScope(scopeId, qualifier, source);
    }

    public final /* synthetic */ <T> Scope createScope(String scopeId, Object source) {
        Intrinsics.checkNotNullParameter(scopeId, "scopeId");
        Intrinsics.reifiedOperationMarker(4, "T");
        TypeQualifier qualifier = new TypeQualifier(Reflection.getOrCreateKotlinClass(Object.class));
        getLogger().log(Level.DEBUG, new Koin$createScope$2(scopeId, qualifier));
        return getScopeRegistry().createScope(scopeId, qualifier, source);
    }

    public static /* synthetic */ Scope createScope$default(Koin koin, String scopeId, int i, Object obj) {
        if ((i & 1) != 0) {
            scopeId = KoinPlatformTools.INSTANCE.generateId();
        }
        Intrinsics.checkNotNullParameter(scopeId, "scopeId");
        Intrinsics.reifiedOperationMarker(4, "T");
        TypeQualifier qualifier = new TypeQualifier(Reflection.getOrCreateKotlinClass(Object.class));
        koin.getLogger().log(Level.DEBUG, new Koin$createScope$3(scopeId, qualifier));
        return koin.getScopeRegistry().createScope(scopeId, qualifier, null);
    }

    public final /* synthetic */ <T> Scope createScope(String scopeId) {
        Intrinsics.checkNotNullParameter(scopeId, "scopeId");
        Intrinsics.reifiedOperationMarker(4, "T");
        TypeQualifier qualifier = new TypeQualifier(Reflection.getOrCreateKotlinClass(Object.class));
        getLogger().log(Level.DEBUG, new Koin$createScope$3(scopeId, qualifier));
        return getScopeRegistry().createScope(scopeId, qualifier, null);
    }

    public final <T extends KoinScopeComponent> Scope createScope(T t) {
        Intrinsics.checkNotNullParameter(t, "t");
        final String scopeId = KoinScopeComponentKt.getScopeId(t);
        final TypeQualifier qualifier = KoinScopeComponentKt.getScopeName(t);
        this.logger.log(Level.DEBUG, new Function0<String>() { // from class: org.koin.core.Koin$createScope$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "|- create scope - id:'" + scopeId + "' q:" + qualifier;
            }
        });
        return this.scopeRegistry.createScope(scopeId, qualifier, null);
    }

    public static /* synthetic */ Scope getOrCreateScope$default(Koin koin, String str, Qualifier qualifier, Object obj, int i, Object obj2) {
        if ((i & 4) != 0) {
            obj = null;
        }
        return koin.getOrCreateScope(str, qualifier, obj);
    }

    public final Scope getOrCreateScope(String scopeId, Qualifier qualifier, Object source) {
        Intrinsics.checkNotNullParameter(scopeId, "scopeId");
        Intrinsics.checkNotNullParameter(qualifier, "qualifier");
        Scope scopeOrNull = this.scopeRegistry.getScopeOrNull(scopeId);
        return scopeOrNull == null ? createScope(scopeId, qualifier, source) : scopeOrNull;
    }

    public final /* synthetic */ <T> Scope getOrCreateScope(String scopeId) {
        Intrinsics.checkNotNullParameter(scopeId, "scopeId");
        Intrinsics.reifiedOperationMarker(4, "T");
        TypeQualifier qualifier = new TypeQualifier(Reflection.getOrCreateKotlinClass(Object.class));
        Scope scopeOrNull = getScopeRegistry().getScopeOrNull(scopeId);
        return scopeOrNull == null ? createScope$default(this, scopeId, qualifier, null, 4, null) : scopeOrNull;
    }

    public final Scope getScope(String scopeId) {
        Intrinsics.checkNotNullParameter(scopeId, "scopeId");
        Scope scopeOrNull = this.scopeRegistry.getScopeOrNull(scopeId);
        if (scopeOrNull != null) {
            return scopeOrNull;
        }
        throw new ScopeNotCreatedException("No scope found for id '" + scopeId + '\'');
    }

    public final Scope getScopeOrNull(String scopeId) {
        Intrinsics.checkNotNullParameter(scopeId, "scopeId");
        return this.scopeRegistry.getScopeOrNull(scopeId);
    }

    public final void deleteScope(String scopeId) {
        Intrinsics.checkNotNullParameter(scopeId, "scopeId");
        this.scopeRegistry.deleteScope$koin_core(scopeId);
    }

    public final <T> T getProperty(String key, T defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        T t = (T) this.propertyRegistry.getProperty(key);
        return t == null ? defaultValue : t;
    }

    public final <T> T getProperty(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return (T) this.propertyRegistry.getProperty(key);
    }

    public final void setProperty(String key, Object value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.propertyRegistry.saveProperty$koin_core(key, value);
    }

    public final void deleteProperty(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.propertyRegistry.deleteProperty(key);
    }

    public final void close() {
        this.scopeRegistry.close$koin_core();
        this.instanceRegistry.close$koin_core();
        this.propertyRegistry.close();
    }

    public static /* synthetic */ void loadModules$default(Koin koin, List list, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        koin.loadModules(list, z);
    }

    public final void loadModules(List<Module> modules, boolean allowOverride) {
        Intrinsics.checkNotNullParameter(modules, "modules");
        this.instanceRegistry.loadModules$koin_core(modules, allowOverride);
        this.scopeRegistry.loadScopes(modules);
    }

    public final void unloadModules(List<Module> modules) {
        Intrinsics.checkNotNullParameter(modules, "modules");
        this.instanceRegistry.unloadModules$koin_core(modules);
    }

    public final void createEagerInstances() {
        this.logger.info("create eager instances ...");
        if (this.logger.isAt(Level.DEBUG)) {
            double duration = MeasureKt.measureDuration(new Function0<Unit>() { // from class: org.koin.core.Koin$createEagerInstances$duration$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Koin.this.getInstanceRegistry().createAllEagerInstances$koin_core();
                }
            });
            this.logger.debug("eager instances created in " + duration + " ms");
        } else {
            this.instanceRegistry.createAllEagerInstances$koin_core();
        }
    }
}
