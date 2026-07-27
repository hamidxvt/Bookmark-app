package org.koin.androidx.fragment.koin;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentFactory;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.fragment.android.KoinFragmentFactory;
import org.koin.core.Koin;
import org.koin.core.KoinApplication;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.BeanDefinitionKt;
import org.koin.core.definition.Kind;
import org.koin.core.instance.SingleInstanceFactory;
import org.koin.core.module.Module;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.registry.ScopeRegistry;
import org.koin.core.scope.Scope;
import org.koin.dsl.ModuleKt;

/* compiled from: KoinApplicationExt.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"fragmentFactoryModule", "Lorg/koin/core/module/Module;", "fragmentFactory", "", "Lorg/koin/core/KoinApplication;", "koin-android_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class KoinApplicationExtKt {
    private static final Module fragmentFactoryModule = ModuleKt.module$default(false, new Function1<Module, Unit>() { // from class: org.koin.androidx.fragment.koin.KoinApplicationExtKt$fragmentFactoryModule$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Module module) {
            invoke2(module);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Module module) {
            Intrinsics.checkNotNullParameter(module, "$this$module");
            Function2 definition$iv = new Function2<Scope, ParametersHolder, FragmentFactory>() { // from class: org.koin.androidx.fragment.koin.KoinApplicationExtKt$fragmentFactoryModule$1.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlin.jvm.functions.Function2
                public final FragmentFactory invoke(Scope single, ParametersHolder it) {
                    Intrinsics.checkNotNullParameter(single, "$this$single");
                    Intrinsics.checkNotNullParameter(it, "it");
                    return new KoinFragmentFactory(null, 1, 0 == true ? 1 : 0);
                }
            };
            Kind kind$iv$iv = Kind.Singleton;
            Qualifier scopeQualifier$iv$iv = ScopeRegistry.INSTANCE.getRootScopeQualifier();
            List secondaryTypes$iv$iv = CollectionsKt.emptyList();
            BeanDefinition def$iv = new BeanDefinition(scopeQualifier$iv$iv, Reflection.getOrCreateKotlinClass(FragmentFactory.class), null, definition$iv, kind$iv$iv, secondaryTypes$iv$iv);
            String mapping$iv = BeanDefinitionKt.indexKey(def$iv.getPrimaryType(), null, ScopeRegistry.INSTANCE.getRootScopeQualifier());
            SingleInstanceFactory instanceFactory$iv = new SingleInstanceFactory(def$iv);
            Module.saveMapping$default(module, mapping$iv, instanceFactory$iv, false, 4, null);
            if (module.getCreatedAtStart()) {
                module.getEagerInstances().add(instanceFactory$iv);
            }
            new Pair(module, instanceFactory$iv);
        }
    }, 1, null);

    public static final void fragmentFactory(KoinApplication $this$fragmentFactory) {
        Intrinsics.checkNotNullParameter($this$fragmentFactory, "<this>");
        Koin.loadModules$default($this$fragmentFactory.getKoin(), CollectionsKt.listOf(fragmentFactoryModule), false, 2, null);
    }
}
