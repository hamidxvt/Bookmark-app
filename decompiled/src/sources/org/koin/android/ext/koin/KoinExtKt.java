package org.koin.android.ext.koin;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.android.logger.AndroidLogger;
import org.koin.core.Koin;
import org.koin.core.KoinApplication;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.BeanDefinitionKt;
import org.koin.core.definition.Kind;
import org.koin.core.instance.SingleInstanceFactory;
import org.koin.core.logger.Level;
import org.koin.core.module.Module;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.registry.PropertyRegistryExtKt;
import org.koin.core.registry.ScopeRegistry;
import org.koin.core.scope.Scope;
import org.koin.dsl.DefinitionBindingKt;
import org.koin.dsl.ModuleKt;

/* compiled from: KoinExt.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0000\u001a\u00020\u0002\u001a\u0014\u0010\u0003\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u001a\u0014\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"androidContext", "Lorg/koin/core/KoinApplication;", "Landroid/content/Context;", "androidFileProperties", "koinPropertyFile", "", "androidLogger", FirebaseAnalytics.Param.LEVEL, "Lorg/koin/core/logger/Level;", "koin-android_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class KoinExtKt {
    public static /* synthetic */ KoinApplication androidLogger$default(KoinApplication koinApplication, Level level, int i, Object obj) {
        if ((i & 1) != 0) {
            level = Level.INFO;
        }
        return androidLogger(koinApplication, level);
    }

    public static final KoinApplication androidLogger(KoinApplication $this$androidLogger, Level level) {
        Intrinsics.checkNotNullParameter($this$androidLogger, "<this>");
        Intrinsics.checkNotNullParameter(level, "level");
        $this$androidLogger.getKoin().setupLogger(new AndroidLogger(level));
        return $this$androidLogger;
    }

    public static final KoinApplication androidContext(KoinApplication $this$androidContext, final Context androidContext) {
        Intrinsics.checkNotNullParameter($this$androidContext, "<this>");
        Intrinsics.checkNotNullParameter(androidContext, "androidContext");
        if ($this$androidContext.getKoin().getLogger().isAt(Level.INFO)) {
            $this$androidContext.getKoin().getLogger().info("[init] declare Android Context");
        }
        if (androidContext instanceof Application) {
            Koin.loadModules$default($this$androidContext.getKoin(), CollectionsKt.listOf(ModuleKt.module$default(false, new Function1<Module, Unit>() { // from class: org.koin.android.ext.koin.KoinExtKt$androidContext$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Module module) {
                    invoke2(module);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Module module) {
                    Intrinsics.checkNotNullParameter(module, "$this$module");
                    final Context context = androidContext;
                    Function2 definition$iv = new Function2<Scope, ParametersHolder, Context>() { // from class: org.koin.android.ext.koin.KoinExtKt$androidContext$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Context invoke(Scope single, ParametersHolder it) {
                            Intrinsics.checkNotNullParameter(single, "$this$single");
                            Intrinsics.checkNotNullParameter(it, "it");
                            return context;
                        }
                    };
                    Kind kind$iv$iv = Kind.Singleton;
                    Qualifier scopeQualifier$iv$iv = ScopeRegistry.INSTANCE.getRootScopeQualifier();
                    List secondaryTypes$iv$iv = CollectionsKt.emptyList();
                    BeanDefinition def$iv = new BeanDefinition(scopeQualifier$iv$iv, Reflection.getOrCreateKotlinClass(Context.class), null, definition$iv, kind$iv$iv, secondaryTypes$iv$iv);
                    String mapping$iv = BeanDefinitionKt.indexKey(def$iv.getPrimaryType(), null, ScopeRegistry.INSTANCE.getRootScopeQualifier());
                    SingleInstanceFactory instanceFactory$iv = new SingleInstanceFactory(def$iv);
                    Module.saveMapping$default(module, mapping$iv, instanceFactory$iv, false, 4, null);
                    if (module.getCreatedAtStart()) {
                        module.getEagerInstances().add(instanceFactory$iv);
                    }
                    DefinitionBindingKt.bind(new Pair(module, instanceFactory$iv), Reflection.getOrCreateKotlinClass(Application.class));
                }
            }, 1, null)), false, 2, null);
        } else {
            Koin.loadModules$default($this$androidContext.getKoin(), CollectionsKt.listOf(ModuleKt.module$default(false, new Function1<Module, Unit>() { // from class: org.koin.android.ext.koin.KoinExtKt$androidContext$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Module module) {
                    invoke2(module);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Module module) {
                    Intrinsics.checkNotNullParameter(module, "$this$module");
                    final Context context = androidContext;
                    Function2 definition$iv = new Function2<Scope, ParametersHolder, Context>() { // from class: org.koin.android.ext.koin.KoinExtKt$androidContext$2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Context invoke(Scope single, ParametersHolder it) {
                            Intrinsics.checkNotNullParameter(single, "$this$single");
                            Intrinsics.checkNotNullParameter(it, "it");
                            return context;
                        }
                    };
                    Kind kind$iv$iv = Kind.Singleton;
                    Qualifier scopeQualifier$iv$iv = ScopeRegistry.INSTANCE.getRootScopeQualifier();
                    List secondaryTypes$iv$iv = CollectionsKt.emptyList();
                    BeanDefinition def$iv = new BeanDefinition(scopeQualifier$iv$iv, Reflection.getOrCreateKotlinClass(Context.class), null, definition$iv, kind$iv$iv, secondaryTypes$iv$iv);
                    String mapping$iv = BeanDefinitionKt.indexKey(def$iv.getPrimaryType(), null, ScopeRegistry.INSTANCE.getRootScopeQualifier());
                    SingleInstanceFactory instanceFactory$iv = new SingleInstanceFactory(def$iv);
                    Module.saveMapping$default(module, mapping$iv, instanceFactory$iv, false, 4, null);
                    if (module.getCreatedAtStart()) {
                        module.getEagerInstances().add(instanceFactory$iv);
                    }
                    new Pair(module, instanceFactory$iv);
                }
            }, 1, null)), false, 2, null);
        }
        return $this$androidContext;
    }

    public static /* synthetic */ KoinApplication androidFileProperties$default(KoinApplication koinApplication, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "koin.properties";
        }
        return androidFileProperties(koinApplication, str);
    }

    public static final KoinApplication androidFileProperties(KoinApplication $this$androidFileProperties, String koinPropertyFile) {
        String[] list;
        Intrinsics.checkNotNullParameter($this$androidFileProperties, "<this>");
        Intrinsics.checkNotNullParameter(koinPropertyFile, "koinPropertyFile");
        Properties koinProperties = new Properties();
        Koin this_$iv = $this$androidFileProperties.getKoin();
        Scope this_$iv$iv = this_$iv.getScopeRegistry().getRootScope();
        Context androidContext = (Context) this_$iv$iv.get(Reflection.getOrCreateKotlinClass(Context.class), null, null);
        try {
            AssetManager assets = androidContext.getAssets();
            boolean z = false;
            if (assets != null && (list = assets.list("")) != null) {
                z = ArraysKt.contains(list, koinPropertyFile);
            }
            boolean hasFile = z;
            if (hasFile) {
                try {
                    InputStream open = androidContext.getAssets().open(koinPropertyFile);
                    try {
                        InputStream it = open;
                        koinProperties.load(it);
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(open, null);
                        PropertyRegistryExtKt.saveProperties($this$androidFileProperties.getKoin().getPropertyRegistry(), koinProperties);
                        Unit nb = Unit.INSTANCE;
                        if ($this$androidFileProperties.getKoin().getLogger().isAt(Level.INFO)) {
                            $this$androidFileProperties.getKoin().getLogger().info("[Android-Properties] loaded " + nb + " properties from assets/" + koinPropertyFile);
                        }
                    } finally {
                    }
                } catch (Exception e) {
                    $this$androidFileProperties.getKoin().getLogger().error(Intrinsics.stringPlus("[Android-Properties] error for binding properties : ", e));
                }
            } else if ($this$androidFileProperties.getKoin().getLogger().isAt(Level.INFO)) {
                $this$androidFileProperties.getKoin().getLogger().info("[Android-Properties] no assets/" + koinPropertyFile + " file to load");
            }
        } catch (Exception e2) {
            $this$androidFileProperties.getKoin().getLogger().error("[Android-Properties] error while loading properties from assets/" + koinPropertyFile + " : " + e2);
        }
        return $this$androidFileProperties;
    }
}
