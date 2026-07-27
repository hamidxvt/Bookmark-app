package com.ingenious.androidbookmarksalesupgrade.koin.module;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.network.domain.DataSource;
import com.ingenious.androidbookmarksalesupgrade.repository.AppRepository;
import com.ingenious.androidbookmarksalesupgrade.repository.ChatRepository;
import com.ingenious.androidbookmarksalesupgrade.repository.HomeRepository;
import com.ingenious.androidbookmarksalesupgrade.repository.InventoryRepository;
import com.ingenious.androidbookmarksalesupgrade.repository.ProductRepository;
import com.ingenious.androidbookmarksalesupgrade.repository.UserRepository;
import com.ingenious.androidbookmarksalesupgrade.repository.VisitRepository;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
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

/* compiled from: RepositoryModule.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"RepositoryModule", "Lorg/koin/core/module/Module;", "getRepositoryModule", "()Lorg/koin/core/module/Module;", "app_debug"}, k = 2, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes14.dex */
public final class RepositoryModuleKt {
    private static final Module RepositoryModule = ModuleKt.module$default(false, new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.RepositoryModuleKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Unit RepositoryModule$lambda$8;
            RepositoryModule$lambda$8 = RepositoryModuleKt.RepositoryModule$lambda$8((Module) obj);
            return RepositoryModule$lambda$8;
        }
    }, 1, null);

    public static final Module getRepositoryModule() {
        return RepositoryModule;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepositoryModule$lambda$8(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        Function2 definition$iv = new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.RepositoryModuleKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                DataSource RepositoryModule$lambda$8$lambda$0;
                RepositoryModule$lambda$8$lambda$0 = RepositoryModuleKt.RepositoryModule$lambda$8$lambda$0((Scope) obj, (ParametersHolder) obj2);
                return RepositoryModule$lambda$8$lambda$0;
            }
        };
        Kind kind$iv$iv = Kind.Singleton;
        Qualifier scopeQualifier$iv$iv = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        List secondaryTypes$iv$iv = CollectionsKt.emptyList();
        BeanDefinition def$iv = new BeanDefinition(scopeQualifier$iv$iv, Reflection.getOrCreateKotlinClass(DataSource.class), null, definition$iv, kind$iv$iv, secondaryTypes$iv$iv);
        String mapping$iv = BeanDefinitionKt.indexKey(def$iv.getPrimaryType(), null, ScopeRegistry.INSTANCE.getRootScopeQualifier());
        SingleInstanceFactory instanceFactory$iv = new SingleInstanceFactory(def$iv);
        Module.saveMapping$default(module, mapping$iv, instanceFactory$iv, false, 4, null);
        if (module.getCreatedAtStart()) {
            module.getEagerInstances().add(instanceFactory$iv);
        }
        new Pair(module, instanceFactory$iv);
        Function2 definition$iv2 = new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.RepositoryModuleKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                AppRepository RepositoryModule$lambda$8$lambda$1;
                RepositoryModule$lambda$8$lambda$1 = RepositoryModuleKt.RepositoryModule$lambda$8$lambda$1((Scope) obj, (ParametersHolder) obj2);
                return RepositoryModule$lambda$8$lambda$1;
            }
        };
        Kind kind$iv$iv2 = Kind.Singleton;
        Qualifier scopeQualifier$iv$iv2 = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        List secondaryTypes$iv$iv2 = CollectionsKt.emptyList();
        BeanDefinition def$iv2 = new BeanDefinition(scopeQualifier$iv$iv2, Reflection.getOrCreateKotlinClass(AppRepository.class), null, definition$iv2, kind$iv$iv2, secondaryTypes$iv$iv2);
        String mapping$iv2 = BeanDefinitionKt.indexKey(def$iv2.getPrimaryType(), null, ScopeRegistry.INSTANCE.getRootScopeQualifier());
        SingleInstanceFactory instanceFactory$iv2 = new SingleInstanceFactory(def$iv2);
        Module.saveMapping$default(module, mapping$iv2, instanceFactory$iv2, false, 4, null);
        if (module.getCreatedAtStart()) {
            module.getEagerInstances().add(instanceFactory$iv2);
        }
        new Pair(module, instanceFactory$iv2);
        Function2 definition$iv3 = new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.RepositoryModuleKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                UserRepository RepositoryModule$lambda$8$lambda$2;
                RepositoryModule$lambda$8$lambda$2 = RepositoryModuleKt.RepositoryModule$lambda$8$lambda$2((Scope) obj, (ParametersHolder) obj2);
                return RepositoryModule$lambda$8$lambda$2;
            }
        };
        Kind kind$iv$iv3 = Kind.Singleton;
        Qualifier scopeQualifier$iv$iv3 = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        List secondaryTypes$iv$iv3 = CollectionsKt.emptyList();
        BeanDefinition def$iv3 = new BeanDefinition(scopeQualifier$iv$iv3, Reflection.getOrCreateKotlinClass(UserRepository.class), null, definition$iv3, kind$iv$iv3, secondaryTypes$iv$iv3);
        String mapping$iv3 = BeanDefinitionKt.indexKey(def$iv3.getPrimaryType(), null, ScopeRegistry.INSTANCE.getRootScopeQualifier());
        SingleInstanceFactory instanceFactory$iv3 = new SingleInstanceFactory(def$iv3);
        Module.saveMapping$default(module, mapping$iv3, instanceFactory$iv3, false, 4, null);
        if (module.getCreatedAtStart()) {
            module.getEagerInstances().add(instanceFactory$iv3);
        }
        new Pair(module, instanceFactory$iv3);
        Function2 definition$iv4 = new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.RepositoryModuleKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                HomeRepository RepositoryModule$lambda$8$lambda$3;
                RepositoryModule$lambda$8$lambda$3 = RepositoryModuleKt.RepositoryModule$lambda$8$lambda$3((Scope) obj, (ParametersHolder) obj2);
                return RepositoryModule$lambda$8$lambda$3;
            }
        };
        Kind kind$iv$iv4 = Kind.Singleton;
        Qualifier scopeQualifier$iv$iv4 = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        List secondaryTypes$iv$iv4 = CollectionsKt.emptyList();
        BeanDefinition def$iv4 = new BeanDefinition(scopeQualifier$iv$iv4, Reflection.getOrCreateKotlinClass(HomeRepository.class), null, definition$iv4, kind$iv$iv4, secondaryTypes$iv$iv4);
        String mapping$iv4 = BeanDefinitionKt.indexKey(def$iv4.getPrimaryType(), null, ScopeRegistry.INSTANCE.getRootScopeQualifier());
        SingleInstanceFactory instanceFactory$iv4 = new SingleInstanceFactory(def$iv4);
        Module.saveMapping$default(module, mapping$iv4, instanceFactory$iv4, false, 4, null);
        if (module.getCreatedAtStart()) {
            module.getEagerInstances().add(instanceFactory$iv4);
        }
        new Pair(module, instanceFactory$iv4);
        Function2 definition$iv5 = new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.RepositoryModuleKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                VisitRepository RepositoryModule$lambda$8$lambda$4;
                RepositoryModule$lambda$8$lambda$4 = RepositoryModuleKt.RepositoryModule$lambda$8$lambda$4((Scope) obj, (ParametersHolder) obj2);
                return RepositoryModule$lambda$8$lambda$4;
            }
        };
        Kind kind$iv$iv5 = Kind.Singleton;
        Qualifier scopeQualifier$iv$iv5 = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        List secondaryTypes$iv$iv5 = CollectionsKt.emptyList();
        BeanDefinition def$iv5 = new BeanDefinition(scopeQualifier$iv$iv5, Reflection.getOrCreateKotlinClass(VisitRepository.class), null, definition$iv5, kind$iv$iv5, secondaryTypes$iv$iv5);
        String mapping$iv5 = BeanDefinitionKt.indexKey(def$iv5.getPrimaryType(), null, ScopeRegistry.INSTANCE.getRootScopeQualifier());
        SingleInstanceFactory instanceFactory$iv5 = new SingleInstanceFactory(def$iv5);
        Module.saveMapping$default(module, mapping$iv5, instanceFactory$iv5, false, 4, null);
        if (module.getCreatedAtStart()) {
            module.getEagerInstances().add(instanceFactory$iv5);
        }
        new Pair(module, instanceFactory$iv5);
        Function2 definition$iv6 = new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.RepositoryModuleKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ChatRepository RepositoryModule$lambda$8$lambda$5;
                RepositoryModule$lambda$8$lambda$5 = RepositoryModuleKt.RepositoryModule$lambda$8$lambda$5((Scope) obj, (ParametersHolder) obj2);
                return RepositoryModule$lambda$8$lambda$5;
            }
        };
        Kind kind$iv$iv6 = Kind.Singleton;
        Qualifier scopeQualifier$iv$iv6 = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        List secondaryTypes$iv$iv6 = CollectionsKt.emptyList();
        BeanDefinition def$iv6 = new BeanDefinition(scopeQualifier$iv$iv6, Reflection.getOrCreateKotlinClass(ChatRepository.class), null, definition$iv6, kind$iv$iv6, secondaryTypes$iv$iv6);
        String mapping$iv6 = BeanDefinitionKt.indexKey(def$iv6.getPrimaryType(), null, ScopeRegistry.INSTANCE.getRootScopeQualifier());
        SingleInstanceFactory instanceFactory$iv6 = new SingleInstanceFactory(def$iv6);
        Module.saveMapping$default(module, mapping$iv6, instanceFactory$iv6, false, 4, null);
        if (module.getCreatedAtStart()) {
            module.getEagerInstances().add(instanceFactory$iv6);
        }
        new Pair(module, instanceFactory$iv6);
        Function2 definition$iv7 = new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.RepositoryModuleKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ProductRepository RepositoryModule$lambda$8$lambda$6;
                RepositoryModule$lambda$8$lambda$6 = RepositoryModuleKt.RepositoryModule$lambda$8$lambda$6((Scope) obj, (ParametersHolder) obj2);
                return RepositoryModule$lambda$8$lambda$6;
            }
        };
        Kind kind$iv$iv7 = Kind.Singleton;
        Qualifier scopeQualifier$iv$iv7 = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        List secondaryTypes$iv$iv7 = CollectionsKt.emptyList();
        BeanDefinition def$iv7 = new BeanDefinition(scopeQualifier$iv$iv7, Reflection.getOrCreateKotlinClass(ProductRepository.class), null, definition$iv7, kind$iv$iv7, secondaryTypes$iv$iv7);
        String mapping$iv7 = BeanDefinitionKt.indexKey(def$iv7.getPrimaryType(), null, ScopeRegistry.INSTANCE.getRootScopeQualifier());
        SingleInstanceFactory instanceFactory$iv7 = new SingleInstanceFactory(def$iv7);
        Module.saveMapping$default(module, mapping$iv7, instanceFactory$iv7, false, 4, null);
        if (module.getCreatedAtStart()) {
            module.getEagerInstances().add(instanceFactory$iv7);
        }
        new Pair(module, instanceFactory$iv7);
        Function2 definition$iv8 = new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.RepositoryModuleKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                InventoryRepository RepositoryModule$lambda$8$lambda$7;
                RepositoryModule$lambda$8$lambda$7 = RepositoryModuleKt.RepositoryModule$lambda$8$lambda$7((Scope) obj, (ParametersHolder) obj2);
                return RepositoryModule$lambda$8$lambda$7;
            }
        };
        Kind kind$iv$iv8 = Kind.Singleton;
        Qualifier scopeQualifier$iv$iv8 = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        List secondaryTypes$iv$iv8 = CollectionsKt.emptyList();
        BeanDefinition def$iv8 = new BeanDefinition(scopeQualifier$iv$iv8, Reflection.getOrCreateKotlinClass(InventoryRepository.class), null, definition$iv8, kind$iv$iv8, secondaryTypes$iv$iv8);
        String mapping$iv8 = BeanDefinitionKt.indexKey(def$iv8.getPrimaryType(), null, ScopeRegistry.INSTANCE.getRootScopeQualifier());
        SingleInstanceFactory instanceFactory$iv8 = new SingleInstanceFactory(def$iv8);
        Module.saveMapping$default(module, mapping$iv8, instanceFactory$iv8, false, 4, null);
        if (module.getCreatedAtStart()) {
            module.getEagerInstances().add(instanceFactory$iv8);
        }
        new Pair(module, instanceFactory$iv8);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DataSource RepositoryModule$lambda$8$lambda$0(Scope single, ParametersHolder it) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(it, "it");
        return new DataSource();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AppRepository RepositoryModule$lambda$8$lambda$1(Scope single, ParametersHolder it) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(it, "it");
        return new AppRepository();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final UserRepository RepositoryModule$lambda$8$lambda$2(Scope single, ParametersHolder it) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(it, "it");
        return new UserRepository();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HomeRepository RepositoryModule$lambda$8$lambda$3(Scope single, ParametersHolder it) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(it, "it");
        return new HomeRepository();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final VisitRepository RepositoryModule$lambda$8$lambda$4(Scope single, ParametersHolder it) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(it, "it");
        return new VisitRepository();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ChatRepository RepositoryModule$lambda$8$lambda$5(Scope single, ParametersHolder it) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(it, "it");
        return new ChatRepository();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ProductRepository RepositoryModule$lambda$8$lambda$6(Scope single, ParametersHolder it) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(it, "it");
        return new ProductRepository();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final InventoryRepository RepositoryModule$lambda$8$lambda$7(Scope single, ParametersHolder it) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(it, "it");
        return new InventoryRepository();
    }
}
