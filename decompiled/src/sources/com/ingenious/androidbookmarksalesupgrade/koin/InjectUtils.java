package com.ingenious.androidbookmarksalesupgrade.koin;

import android.app.Application;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.network.SoService;
import com.ingenious.androidbookmarksalesupgrade.network.domain.DataSource;
import com.ingenious.androidbookmarksalesupgrade.repository.AppRepository;
import com.ingenious.androidbookmarksalesupgrade.repository.ChatRepository;
import com.ingenious.androidbookmarksalesupgrade.repository.HomeRepository;
import com.ingenious.androidbookmarksalesupgrade.repository.InventoryRepository;
import com.ingenious.androidbookmarksalesupgrade.repository.ProductRepository;
import com.ingenious.androidbookmarksalesupgrade.repository.UserRepository;
import com.ingenious.androidbookmarksalesupgrade.repository.VisitRepository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Reflection;
import org.koin.core.Koin;
import org.koin.core.component.KoinComponent;
import org.koin.core.component.KoinScopeComponent;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
import org.koin.mp.KoinPlatformTools;

/* compiled from: InjectUtils.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000f\u001a\u00020\u00108FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\t\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0014\u001a\u00020\u00158FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\t\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0019\u001a\u00020\u001a8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\t\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001e\u001a\u00020\u001f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\t\u001a\u0004\b \u0010!R\u001b\u0010#\u001a\u00020$8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\t\u001a\u0004\b%\u0010&R\u001b\u0010(\u001a\u00020)8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\t\u001a\u0004\b*\u0010+R\u001b\u0010-\u001a\u00020.8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b1\u0010\t\u001a\u0004\b/\u00100R\u001b\u00102\u001a\u0002038FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\t\u001a\u0004\b4\u00105¨\u00067"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/koin/InjectUtils;", "Lorg/koin/core/component/KoinComponent;", "<init>", "()V", "appContext", "Landroid/app/Application;", "getAppContext", "()Landroid/app/Application;", "appContext$delegate", "Lkotlin/Lazy;", "getRetrofit", "Lcom/ingenious/androidbookmarksalesupgrade/network/SoService;", "getGetRetrofit", "()Lcom/ingenious/androidbookmarksalesupgrade/network/SoService;", "getRetrofit$delegate", "appRepository", "Lcom/ingenious/androidbookmarksalesupgrade/repository/AppRepository;", "getAppRepository", "()Lcom/ingenious/androidbookmarksalesupgrade/repository/AppRepository;", "appRepository$delegate", "dataSource", "Lcom/ingenious/androidbookmarksalesupgrade/network/domain/DataSource;", "getDataSource", "()Lcom/ingenious/androidbookmarksalesupgrade/network/domain/DataSource;", "dataSource$delegate", "userRepository", "Lcom/ingenious/androidbookmarksalesupgrade/repository/UserRepository;", "getUserRepository", "()Lcom/ingenious/androidbookmarksalesupgrade/repository/UserRepository;", "userRepository$delegate", "homeRepository", "Lcom/ingenious/androidbookmarksalesupgrade/repository/HomeRepository;", "getHomeRepository", "()Lcom/ingenious/androidbookmarksalesupgrade/repository/HomeRepository;", "homeRepository$delegate", "visitRepository", "Lcom/ingenious/androidbookmarksalesupgrade/repository/VisitRepository;", "getVisitRepository", "()Lcom/ingenious/androidbookmarksalesupgrade/repository/VisitRepository;", "visitRepository$delegate", "chatRepository", "Lcom/ingenious/androidbookmarksalesupgrade/repository/ChatRepository;", "getChatRepository", "()Lcom/ingenious/androidbookmarksalesupgrade/repository/ChatRepository;", "chatRepository$delegate", "productRepository", "Lcom/ingenious/androidbookmarksalesupgrade/repository/ProductRepository;", "getProductRepository", "()Lcom/ingenious/androidbookmarksalesupgrade/repository/ProductRepository;", "productRepository$delegate", "inventoryRepository", "Lcom/ingenious/androidbookmarksalesupgrade/repository/InventoryRepository;", "getInventoryRepository", "()Lcom/ingenious/androidbookmarksalesupgrade/repository/InventoryRepository;", "inventoryRepository$delegate", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class InjectUtils implements KoinComponent {
    public static final InjectUtils INSTANCE = new InjectUtils();

    /* renamed from: appContext$delegate, reason: from kotlin metadata */
    private static final Lazy appContext;

    /* renamed from: appRepository$delegate, reason: from kotlin metadata */
    private static final Lazy appRepository;

    /* renamed from: chatRepository$delegate, reason: from kotlin metadata */
    private static final Lazy chatRepository;

    /* renamed from: dataSource$delegate, reason: from kotlin metadata */
    private static final Lazy dataSource;

    /* renamed from: getRetrofit$delegate, reason: from kotlin metadata */
    private static final Lazy getRetrofit;

    /* renamed from: homeRepository$delegate, reason: from kotlin metadata */
    private static final Lazy homeRepository;

    /* renamed from: inventoryRepository$delegate, reason: from kotlin metadata */
    private static final Lazy inventoryRepository;

    /* renamed from: productRepository$delegate, reason: from kotlin metadata */
    private static final Lazy productRepository;

    /* renamed from: userRepository$delegate, reason: from kotlin metadata */
    private static final Lazy userRepository;

    /* renamed from: visitRepository$delegate, reason: from kotlin metadata */
    private static final Lazy visitRepository;

    private InjectUtils() {
    }

    @Override // org.koin.core.component.KoinComponent
    public Koin getKoin() {
        return KoinComponent.DefaultImpls.getKoin(this);
    }

    static {
        final KoinComponent $this$inject_u24default$iv = INSTANCE;
        final Qualifier qualifier$iv = null;
        LazyThreadSafetyMode mode$iv = KoinPlatformTools.INSTANCE.defaultLazyMode();
        final Function0 parameters$iv = null;
        appContext = LazyKt.lazy(mode$iv, (Function0) new Function0<Application>() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils$special$$inlined$inject$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r4v6, types: [android.app.Application, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r6v2, types: [android.app.Application, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function0
            public final Application invoke() {
                KoinComponent $this$get$iv = KoinComponent.this;
                Qualifier qualifier$iv2 = qualifier$iv;
                Function0 parameters$iv2 = parameters$iv;
                if ($this$get$iv instanceof KoinScopeComponent) {
                    Scope this_$iv$iv = ((KoinScopeComponent) $this$get$iv).getScope();
                    return this_$iv$iv.get(Reflection.getOrCreateKotlinClass(Application.class), qualifier$iv2, parameters$iv2);
                }
                Koin this_$iv$iv2 = $this$get$iv.getKoin();
                Scope this_$iv$iv$iv = this_$iv$iv2.getScopeRegistry().getRootScope();
                return this_$iv$iv$iv.get(Reflection.getOrCreateKotlinClass(Application.class), qualifier$iv2, parameters$iv2);
            }
        });
        final KoinComponent $this$inject_u24default$iv2 = INSTANCE;
        final Qualifier qualifier$iv2 = null;
        LazyThreadSafetyMode mode$iv2 = KoinPlatformTools.INSTANCE.defaultLazyMode();
        final Function0 parameters$iv2 = null;
        getRetrofit = LazyKt.lazy(mode$iv2, (Function0) new Function0<SoService>() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils$special$$inlined$inject$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r4v6, types: [com.ingenious.androidbookmarksalesupgrade.network.SoService, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r6v2, types: [com.ingenious.androidbookmarksalesupgrade.network.SoService, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function0
            public final SoService invoke() {
                KoinComponent $this$get$iv = KoinComponent.this;
                Qualifier qualifier$iv3 = qualifier$iv2;
                Function0 parameters$iv3 = parameters$iv2;
                if ($this$get$iv instanceof KoinScopeComponent) {
                    Scope this_$iv$iv = ((KoinScopeComponent) $this$get$iv).getScope();
                    return this_$iv$iv.get(Reflection.getOrCreateKotlinClass(SoService.class), qualifier$iv3, parameters$iv3);
                }
                Koin this_$iv$iv2 = $this$get$iv.getKoin();
                Scope this_$iv$iv$iv = this_$iv$iv2.getScopeRegistry().getRootScope();
                return this_$iv$iv$iv.get(Reflection.getOrCreateKotlinClass(SoService.class), qualifier$iv3, parameters$iv3);
            }
        });
        final KoinComponent $this$inject_u24default$iv3 = INSTANCE;
        final Qualifier qualifier$iv3 = null;
        LazyThreadSafetyMode mode$iv3 = KoinPlatformTools.INSTANCE.defaultLazyMode();
        final Function0 parameters$iv3 = null;
        appRepository = LazyKt.lazy(mode$iv3, (Function0) new Function0<AppRepository>() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils$special$$inlined$inject$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r4v6, types: [com.ingenious.androidbookmarksalesupgrade.repository.AppRepository, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r6v2, types: [com.ingenious.androidbookmarksalesupgrade.repository.AppRepository, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function0
            public final AppRepository invoke() {
                KoinComponent $this$get$iv = KoinComponent.this;
                Qualifier qualifier$iv4 = qualifier$iv3;
                Function0 parameters$iv4 = parameters$iv3;
                if ($this$get$iv instanceof KoinScopeComponent) {
                    Scope this_$iv$iv = ((KoinScopeComponent) $this$get$iv).getScope();
                    return this_$iv$iv.get(Reflection.getOrCreateKotlinClass(AppRepository.class), qualifier$iv4, parameters$iv4);
                }
                Koin this_$iv$iv2 = $this$get$iv.getKoin();
                Scope this_$iv$iv$iv = this_$iv$iv2.getScopeRegistry().getRootScope();
                return this_$iv$iv$iv.get(Reflection.getOrCreateKotlinClass(AppRepository.class), qualifier$iv4, parameters$iv4);
            }
        });
        final KoinComponent $this$inject_u24default$iv4 = INSTANCE;
        final Qualifier qualifier$iv4 = null;
        LazyThreadSafetyMode mode$iv4 = KoinPlatformTools.INSTANCE.defaultLazyMode();
        final Function0 parameters$iv4 = null;
        dataSource = LazyKt.lazy(mode$iv4, (Function0) new Function0<DataSource>() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils$special$$inlined$inject$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r4v6, types: [com.ingenious.androidbookmarksalesupgrade.network.domain.DataSource, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r6v2, types: [com.ingenious.androidbookmarksalesupgrade.network.domain.DataSource, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function0
            public final DataSource invoke() {
                KoinComponent $this$get$iv = KoinComponent.this;
                Qualifier qualifier$iv5 = qualifier$iv4;
                Function0 parameters$iv5 = parameters$iv4;
                if ($this$get$iv instanceof KoinScopeComponent) {
                    Scope this_$iv$iv = ((KoinScopeComponent) $this$get$iv).getScope();
                    return this_$iv$iv.get(Reflection.getOrCreateKotlinClass(DataSource.class), qualifier$iv5, parameters$iv5);
                }
                Koin this_$iv$iv2 = $this$get$iv.getKoin();
                Scope this_$iv$iv$iv = this_$iv$iv2.getScopeRegistry().getRootScope();
                return this_$iv$iv$iv.get(Reflection.getOrCreateKotlinClass(DataSource.class), qualifier$iv5, parameters$iv5);
            }
        });
        final KoinComponent $this$inject_u24default$iv5 = INSTANCE;
        final Qualifier qualifier$iv5 = null;
        LazyThreadSafetyMode mode$iv5 = KoinPlatformTools.INSTANCE.defaultLazyMode();
        final Function0 parameters$iv5 = null;
        userRepository = LazyKt.lazy(mode$iv5, (Function0) new Function0<UserRepository>() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils$special$$inlined$inject$default$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r4v6, types: [com.ingenious.androidbookmarksalesupgrade.repository.UserRepository, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r6v2, types: [com.ingenious.androidbookmarksalesupgrade.repository.UserRepository, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function0
            public final UserRepository invoke() {
                KoinComponent $this$get$iv = KoinComponent.this;
                Qualifier qualifier$iv6 = qualifier$iv5;
                Function0 parameters$iv6 = parameters$iv5;
                if ($this$get$iv instanceof KoinScopeComponent) {
                    Scope this_$iv$iv = ((KoinScopeComponent) $this$get$iv).getScope();
                    return this_$iv$iv.get(Reflection.getOrCreateKotlinClass(UserRepository.class), qualifier$iv6, parameters$iv6);
                }
                Koin this_$iv$iv2 = $this$get$iv.getKoin();
                Scope this_$iv$iv$iv = this_$iv$iv2.getScopeRegistry().getRootScope();
                return this_$iv$iv$iv.get(Reflection.getOrCreateKotlinClass(UserRepository.class), qualifier$iv6, parameters$iv6);
            }
        });
        final KoinComponent $this$inject_u24default$iv6 = INSTANCE;
        final Qualifier qualifier$iv6 = null;
        LazyThreadSafetyMode mode$iv6 = KoinPlatformTools.INSTANCE.defaultLazyMode();
        final Function0 parameters$iv6 = null;
        homeRepository = LazyKt.lazy(mode$iv6, (Function0) new Function0<HomeRepository>() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils$special$$inlined$inject$default$6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r4v6, types: [com.ingenious.androidbookmarksalesupgrade.repository.HomeRepository, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r6v2, types: [com.ingenious.androidbookmarksalesupgrade.repository.HomeRepository, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function0
            public final HomeRepository invoke() {
                KoinComponent $this$get$iv = KoinComponent.this;
                Qualifier qualifier$iv7 = qualifier$iv6;
                Function0 parameters$iv7 = parameters$iv6;
                if ($this$get$iv instanceof KoinScopeComponent) {
                    Scope this_$iv$iv = ((KoinScopeComponent) $this$get$iv).getScope();
                    return this_$iv$iv.get(Reflection.getOrCreateKotlinClass(HomeRepository.class), qualifier$iv7, parameters$iv7);
                }
                Koin this_$iv$iv2 = $this$get$iv.getKoin();
                Scope this_$iv$iv$iv = this_$iv$iv2.getScopeRegistry().getRootScope();
                return this_$iv$iv$iv.get(Reflection.getOrCreateKotlinClass(HomeRepository.class), qualifier$iv7, parameters$iv7);
            }
        });
        final KoinComponent $this$inject_u24default$iv7 = INSTANCE;
        final Qualifier qualifier$iv7 = null;
        LazyThreadSafetyMode mode$iv7 = KoinPlatformTools.INSTANCE.defaultLazyMode();
        final Function0 parameters$iv7 = null;
        visitRepository = LazyKt.lazy(mode$iv7, (Function0) new Function0<VisitRepository>() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils$special$$inlined$inject$default$7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r4v6, types: [com.ingenious.androidbookmarksalesupgrade.repository.VisitRepository, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r6v2, types: [com.ingenious.androidbookmarksalesupgrade.repository.VisitRepository, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function0
            public final VisitRepository invoke() {
                KoinComponent $this$get$iv = KoinComponent.this;
                Qualifier qualifier$iv8 = qualifier$iv7;
                Function0 parameters$iv8 = parameters$iv7;
                if ($this$get$iv instanceof KoinScopeComponent) {
                    Scope this_$iv$iv = ((KoinScopeComponent) $this$get$iv).getScope();
                    return this_$iv$iv.get(Reflection.getOrCreateKotlinClass(VisitRepository.class), qualifier$iv8, parameters$iv8);
                }
                Koin this_$iv$iv2 = $this$get$iv.getKoin();
                Scope this_$iv$iv$iv = this_$iv$iv2.getScopeRegistry().getRootScope();
                return this_$iv$iv$iv.get(Reflection.getOrCreateKotlinClass(VisitRepository.class), qualifier$iv8, parameters$iv8);
            }
        });
        final KoinComponent $this$inject_u24default$iv8 = INSTANCE;
        final Qualifier qualifier$iv8 = null;
        LazyThreadSafetyMode mode$iv8 = KoinPlatformTools.INSTANCE.defaultLazyMode();
        final Function0 parameters$iv8 = null;
        chatRepository = LazyKt.lazy(mode$iv8, (Function0) new Function0<ChatRepository>() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils$special$$inlined$inject$default$8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r4v6, types: [com.ingenious.androidbookmarksalesupgrade.repository.ChatRepository, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r6v2, types: [com.ingenious.androidbookmarksalesupgrade.repository.ChatRepository, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function0
            public final ChatRepository invoke() {
                KoinComponent $this$get$iv = KoinComponent.this;
                Qualifier qualifier$iv9 = qualifier$iv8;
                Function0 parameters$iv9 = parameters$iv8;
                if ($this$get$iv instanceof KoinScopeComponent) {
                    Scope this_$iv$iv = ((KoinScopeComponent) $this$get$iv).getScope();
                    return this_$iv$iv.get(Reflection.getOrCreateKotlinClass(ChatRepository.class), qualifier$iv9, parameters$iv9);
                }
                Koin this_$iv$iv2 = $this$get$iv.getKoin();
                Scope this_$iv$iv$iv = this_$iv$iv2.getScopeRegistry().getRootScope();
                return this_$iv$iv$iv.get(Reflection.getOrCreateKotlinClass(ChatRepository.class), qualifier$iv9, parameters$iv9);
            }
        });
        final KoinComponent $this$inject_u24default$iv9 = INSTANCE;
        final Qualifier qualifier$iv9 = null;
        LazyThreadSafetyMode mode$iv9 = KoinPlatformTools.INSTANCE.defaultLazyMode();
        final Function0 parameters$iv9 = null;
        productRepository = LazyKt.lazy(mode$iv9, (Function0) new Function0<ProductRepository>() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils$special$$inlined$inject$default$9
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r4v6, types: [com.ingenious.androidbookmarksalesupgrade.repository.ProductRepository, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r6v2, types: [com.ingenious.androidbookmarksalesupgrade.repository.ProductRepository, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function0
            public final ProductRepository invoke() {
                KoinComponent $this$get$iv = KoinComponent.this;
                Qualifier qualifier$iv10 = qualifier$iv9;
                Function0 parameters$iv10 = parameters$iv9;
                if ($this$get$iv instanceof KoinScopeComponent) {
                    Scope this_$iv$iv = ((KoinScopeComponent) $this$get$iv).getScope();
                    return this_$iv$iv.get(Reflection.getOrCreateKotlinClass(ProductRepository.class), qualifier$iv10, parameters$iv10);
                }
                Koin this_$iv$iv2 = $this$get$iv.getKoin();
                Scope this_$iv$iv$iv = this_$iv$iv2.getScopeRegistry().getRootScope();
                return this_$iv$iv$iv.get(Reflection.getOrCreateKotlinClass(ProductRepository.class), qualifier$iv10, parameters$iv10);
            }
        });
        final KoinComponent $this$inject_u24default$iv10 = INSTANCE;
        final Qualifier qualifier$iv10 = null;
        LazyThreadSafetyMode mode$iv10 = KoinPlatformTools.INSTANCE.defaultLazyMode();
        final Function0 parameters$iv10 = null;
        inventoryRepository = LazyKt.lazy(mode$iv10, (Function0) new Function0<InventoryRepository>() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils$special$$inlined$inject$default$10
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r4v6, types: [com.ingenious.androidbookmarksalesupgrade.repository.InventoryRepository, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r6v2, types: [com.ingenious.androidbookmarksalesupgrade.repository.InventoryRepository, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function0
            public final InventoryRepository invoke() {
                KoinComponent $this$get$iv = KoinComponent.this;
                Qualifier qualifier$iv11 = qualifier$iv10;
                Function0 parameters$iv11 = parameters$iv10;
                if ($this$get$iv instanceof KoinScopeComponent) {
                    Scope this_$iv$iv = ((KoinScopeComponent) $this$get$iv).getScope();
                    return this_$iv$iv.get(Reflection.getOrCreateKotlinClass(InventoryRepository.class), qualifier$iv11, parameters$iv11);
                }
                Koin this_$iv$iv2 = $this$get$iv.getKoin();
                Scope this_$iv$iv$iv = this_$iv$iv2.getScopeRegistry().getRootScope();
                return this_$iv$iv$iv.get(Reflection.getOrCreateKotlinClass(InventoryRepository.class), qualifier$iv11, parameters$iv11);
            }
        });
    }

    public final Application getAppContext() {
        return (Application) appContext.getValue();
    }

    public final SoService getGetRetrofit() {
        return (SoService) getRetrofit.getValue();
    }

    public final AppRepository getAppRepository() {
        return (AppRepository) appRepository.getValue();
    }

    public final DataSource getDataSource() {
        return (DataSource) dataSource.getValue();
    }

    public final UserRepository getUserRepository() {
        return (UserRepository) userRepository.getValue();
    }

    public final HomeRepository getHomeRepository() {
        return (HomeRepository) homeRepository.getValue();
    }

    public final VisitRepository getVisitRepository() {
        return (VisitRepository) visitRepository.getValue();
    }

    public final ChatRepository getChatRepository() {
        return (ChatRepository) chatRepository.getValue();
    }

    public final ProductRepository getProductRepository() {
        return (ProductRepository) productRepository.getValue();
    }

    public final InventoryRepository getInventoryRepository() {
        return (InventoryRepository) inventoryRepository.getValue();
    }
}
