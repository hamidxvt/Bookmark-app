package com.ingenious.androidbookmarksalesupgrade.koin.module;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.viewModel.InventoryViewModel;
import com.ingenious.androidbookmarksalesupgrade.viewModel.MainViewModel;
import com.ingenious.androidbookmarksalesupgrade.viewModel.UserViewModel;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
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
import org.koin.core.instance.FactoryInstanceFactory;
import org.koin.core.module.Module;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.registry.ScopeRegistry;
import org.koin.core.scope.Scope;
import org.koin.dsl.ModuleKt;

/* compiled from: ViewModelModule.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"ViewModelModule", "Lorg/koin/core/module/Module;", "getViewModelModule", "()Lorg/koin/core/module/Module;", "app_debug"}, k = 2, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes14.dex */
public final class ViewModelModuleKt {
    private static final Module ViewModelModule = ModuleKt.module$default(false, new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.ViewModelModuleKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Unit ViewModelModule$lambda$4;
            ViewModelModule$lambda$4 = ViewModelModuleKt.ViewModelModule$lambda$4((Module) obj);
            return ViewModelModule$lambda$4;
        }
    }, 1, null);

    public static final Module getViewModelModule() {
        return ViewModelModule;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ViewModelModule$lambda$4(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        Function2 definition$iv = new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.ViewModelModuleKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                UserViewModel ViewModelModule$lambda$4$lambda$0;
                ViewModelModule$lambda$4$lambda$0 = ViewModelModuleKt.ViewModelModule$lambda$4$lambda$0((Scope) obj, (ParametersHolder) obj2);
                return ViewModelModule$lambda$4$lambda$0;
            }
        };
        Qualifier scopeQualifier$iv$iv$iv = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        Kind kind$iv$iv$iv$iv = Kind.Factory;
        List secondaryTypes$iv$iv$iv$iv = CollectionsKt.emptyList();
        BeanDefinition def$iv$iv$iv = new BeanDefinition(scopeQualifier$iv$iv$iv, Reflection.getOrCreateKotlinClass(UserViewModel.class), null, definition$iv, kind$iv$iv$iv$iv, secondaryTypes$iv$iv$iv$iv);
        String mapping$iv$iv$iv = BeanDefinitionKt.indexKey(def$iv$iv$iv.getPrimaryType(), null, scopeQualifier$iv$iv$iv);
        FactoryInstanceFactory instanceFactory$iv$iv$iv = new FactoryInstanceFactory(def$iv$iv$iv);
        Module.saveMapping$default(module, mapping$iv$iv$iv, instanceFactory$iv$iv$iv, false, 4, null);
        new Pair(module, instanceFactory$iv$iv$iv);
        Function2 definition$iv2 = new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.ViewModelModuleKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                MainViewModel ViewModelModule$lambda$4$lambda$1;
                ViewModelModule$lambda$4$lambda$1 = ViewModelModuleKt.ViewModelModule$lambda$4$lambda$1((Scope) obj, (ParametersHolder) obj2);
                return ViewModelModule$lambda$4$lambda$1;
            }
        };
        Qualifier scopeQualifier$iv$iv$iv2 = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        Kind kind$iv$iv$iv$iv2 = Kind.Factory;
        List secondaryTypes$iv$iv$iv$iv2 = CollectionsKt.emptyList();
        BeanDefinition def$iv$iv$iv2 = new BeanDefinition(scopeQualifier$iv$iv$iv2, Reflection.getOrCreateKotlinClass(MainViewModel.class), null, definition$iv2, kind$iv$iv$iv$iv2, secondaryTypes$iv$iv$iv$iv2);
        String mapping$iv$iv$iv2 = BeanDefinitionKt.indexKey(def$iv$iv$iv2.getPrimaryType(), null, scopeQualifier$iv$iv$iv2);
        FactoryInstanceFactory instanceFactory$iv$iv$iv2 = new FactoryInstanceFactory(def$iv$iv$iv2);
        Module.saveMapping$default(module, mapping$iv$iv$iv2, instanceFactory$iv$iv$iv2, false, 4, null);
        new Pair(module, instanceFactory$iv$iv$iv2);
        Function2 definition$iv3 = new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.ViewModelModuleKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                VisitViewModel ViewModelModule$lambda$4$lambda$2;
                ViewModelModule$lambda$4$lambda$2 = ViewModelModuleKt.ViewModelModule$lambda$4$lambda$2((Scope) obj, (ParametersHolder) obj2);
                return ViewModelModule$lambda$4$lambda$2;
            }
        };
        Qualifier scopeQualifier$iv$iv$iv3 = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        Kind kind$iv$iv$iv$iv3 = Kind.Factory;
        List secondaryTypes$iv$iv$iv$iv3 = CollectionsKt.emptyList();
        BeanDefinition def$iv$iv$iv3 = new BeanDefinition(scopeQualifier$iv$iv$iv3, Reflection.getOrCreateKotlinClass(VisitViewModel.class), null, definition$iv3, kind$iv$iv$iv$iv3, secondaryTypes$iv$iv$iv$iv3);
        String mapping$iv$iv$iv3 = BeanDefinitionKt.indexKey(def$iv$iv$iv3.getPrimaryType(), null, scopeQualifier$iv$iv$iv3);
        FactoryInstanceFactory instanceFactory$iv$iv$iv3 = new FactoryInstanceFactory(def$iv$iv$iv3);
        Module.saveMapping$default(module, mapping$iv$iv$iv3, instanceFactory$iv$iv$iv3, false, 4, null);
        new Pair(module, instanceFactory$iv$iv$iv3);
        Function2 definition$iv4 = new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.ViewModelModuleKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                InventoryViewModel ViewModelModule$lambda$4$lambda$3;
                ViewModelModule$lambda$4$lambda$3 = ViewModelModuleKt.ViewModelModule$lambda$4$lambda$3((Scope) obj, (ParametersHolder) obj2);
                return ViewModelModule$lambda$4$lambda$3;
            }
        };
        Qualifier scopeQualifier$iv$iv$iv4 = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        Kind kind$iv$iv$iv$iv4 = Kind.Factory;
        List secondaryTypes$iv$iv$iv$iv4 = CollectionsKt.emptyList();
        BeanDefinition def$iv$iv$iv4 = new BeanDefinition(scopeQualifier$iv$iv$iv4, Reflection.getOrCreateKotlinClass(InventoryViewModel.class), null, definition$iv4, kind$iv$iv$iv$iv4, secondaryTypes$iv$iv$iv$iv4);
        String mapping$iv$iv$iv4 = BeanDefinitionKt.indexKey(def$iv$iv$iv4.getPrimaryType(), null, scopeQualifier$iv$iv$iv4);
        FactoryInstanceFactory instanceFactory$iv$iv$iv4 = new FactoryInstanceFactory(def$iv$iv$iv4);
        Module.saveMapping$default(module, mapping$iv$iv$iv4, instanceFactory$iv$iv$iv4, false, 4, null);
        new Pair(module, instanceFactory$iv$iv$iv4);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final UserViewModel ViewModelModule$lambda$4$lambda$0(Scope viewModel, ParametersHolder it) {
        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
        Intrinsics.checkNotNullParameter(it, "it");
        return new UserViewModel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MainViewModel ViewModelModule$lambda$4$lambda$1(Scope viewModel, ParametersHolder it) {
        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
        Intrinsics.checkNotNullParameter(it, "it");
        return new MainViewModel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final VisitViewModel ViewModelModule$lambda$4$lambda$2(Scope viewModel, ParametersHolder it) {
        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
        Intrinsics.checkNotNullParameter(it, "it");
        return new VisitViewModel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final InventoryViewModel ViewModelModule$lambda$4$lambda$3(Scope viewModel, ParametersHolder it) {
        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
        Intrinsics.checkNotNullParameter(it, "it");
        return new InventoryViewModel();
    }
}
