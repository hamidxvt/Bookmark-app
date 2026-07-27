package com.ingenious.androidbookmarksalesupgrade.koin.module;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.common.net.HttpHeaders;
import com.ingenious.androidbookmarksalesupgrade.BuildConfig;
import com.ingenious.androidbookmarksalesupgrade.model.response.LoginResponse;
import com.ingenious.androidbookmarksalesupgrade.network.RemoteConstant;
import com.ingenious.androidbookmarksalesupgrade.network.SoService;
import com.ingenious.androidbookmarksalesupgrade.storage.AppPreferences;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* compiled from: NetworkModule.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t\u001a\u0006\u0010\n\u001a\u00020\t\u001a\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005\u001a\u0006\u0010\u000e\u001a\u00020\u0007\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u000f"}, d2 = {"NetworkModule", "Lorg/koin/core/module/Module;", "getNetworkModule", "()Lorg/koin/core/module/Module;", "getRetrofitInstance", "Lretrofit2/Retrofit;", "gsonConverterFactory", "Lretrofit2/converter/gson/GsonConverterFactory;", "client", "Lokhttp3/OkHttpClient;", "provideOkHttpClient", "provideAPIClient", "Lcom/ingenious/androidbookmarksalesupgrade/network/SoService;", "retrofit", "getGsonConverterFactory", "app_debug"}, k = 2, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes14.dex */
public final class NetworkModuleKt {
    private static final Module NetworkModule = ModuleKt.module$default(false, new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.NetworkModuleKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Unit NetworkModule$lambda$4;
            NetworkModule$lambda$4 = NetworkModuleKt.NetworkModule$lambda$4((Module) obj);
            return NetworkModule$lambda$4;
        }
    }, 1, null);

    public static final Module getNetworkModule() {
        return NetworkModule;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit NetworkModule$lambda$4(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        Function2 definition$iv = new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.NetworkModuleKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Retrofit NetworkModule$lambda$4$lambda$0;
                NetworkModule$lambda$4$lambda$0 = NetworkModuleKt.NetworkModule$lambda$4$lambda$0((Scope) obj, (ParametersHolder) obj2);
                return NetworkModule$lambda$4$lambda$0;
            }
        };
        Kind kind$iv$iv = Kind.Singleton;
        Qualifier scopeQualifier$iv$iv = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        List secondaryTypes$iv$iv = CollectionsKt.emptyList();
        BeanDefinition def$iv = new BeanDefinition(scopeQualifier$iv$iv, Reflection.getOrCreateKotlinClass(Retrofit.class), null, definition$iv, kind$iv$iv, secondaryTypes$iv$iv);
        String mapping$iv = BeanDefinitionKt.indexKey(def$iv.getPrimaryType(), null, ScopeRegistry.INSTANCE.getRootScopeQualifier());
        SingleInstanceFactory instanceFactory$iv = new SingleInstanceFactory(def$iv);
        Module.saveMapping$default(module, mapping$iv, instanceFactory$iv, false, 4, null);
        if (module.getCreatedAtStart()) {
            module.getEagerInstances().add(instanceFactory$iv);
        }
        new Pair(module, instanceFactory$iv);
        Function2 definition$iv2 = new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.NetworkModuleKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                OkHttpClient NetworkModule$lambda$4$lambda$1;
                NetworkModule$lambda$4$lambda$1 = NetworkModuleKt.NetworkModule$lambda$4$lambda$1((Scope) obj, (ParametersHolder) obj2);
                return NetworkModule$lambda$4$lambda$1;
            }
        };
        Kind kind$iv$iv2 = Kind.Singleton;
        Qualifier scopeQualifier$iv$iv2 = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        List secondaryTypes$iv$iv2 = CollectionsKt.emptyList();
        BeanDefinition def$iv2 = new BeanDefinition(scopeQualifier$iv$iv2, Reflection.getOrCreateKotlinClass(OkHttpClient.class), null, definition$iv2, kind$iv$iv2, secondaryTypes$iv$iv2);
        String mapping$iv2 = BeanDefinitionKt.indexKey(def$iv2.getPrimaryType(), null, ScopeRegistry.INSTANCE.getRootScopeQualifier());
        SingleInstanceFactory instanceFactory$iv2 = new SingleInstanceFactory(def$iv2);
        Module.saveMapping$default(module, mapping$iv2, instanceFactory$iv2, false, 4, null);
        if (module.getCreatedAtStart()) {
            module.getEagerInstances().add(instanceFactory$iv2);
        }
        new Pair(module, instanceFactory$iv2);
        Function2 definition$iv3 = new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.NetworkModuleKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                SoService NetworkModule$lambda$4$lambda$2;
                NetworkModule$lambda$4$lambda$2 = NetworkModuleKt.NetworkModule$lambda$4$lambda$2((Scope) obj, (ParametersHolder) obj2);
                return NetworkModule$lambda$4$lambda$2;
            }
        };
        Kind kind$iv$iv3 = Kind.Singleton;
        Qualifier scopeQualifier$iv$iv3 = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        List secondaryTypes$iv$iv3 = CollectionsKt.emptyList();
        BeanDefinition def$iv3 = new BeanDefinition(scopeQualifier$iv$iv3, Reflection.getOrCreateKotlinClass(SoService.class), null, definition$iv3, kind$iv$iv3, secondaryTypes$iv$iv3);
        String mapping$iv3 = BeanDefinitionKt.indexKey(def$iv3.getPrimaryType(), null, ScopeRegistry.INSTANCE.getRootScopeQualifier());
        SingleInstanceFactory instanceFactory$iv3 = new SingleInstanceFactory(def$iv3);
        Module.saveMapping$default(module, mapping$iv3, instanceFactory$iv3, false, 4, null);
        if (module.getCreatedAtStart()) {
            module.getEagerInstances().add(instanceFactory$iv3);
        }
        new Pair(module, instanceFactory$iv3);
        Function2 definition$iv4 = new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.NetworkModuleKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                GsonConverterFactory NetworkModule$lambda$4$lambda$3;
                NetworkModule$lambda$4$lambda$3 = NetworkModuleKt.NetworkModule$lambda$4$lambda$3((Scope) obj, (ParametersHolder) obj2);
                return NetworkModule$lambda$4$lambda$3;
            }
        };
        Kind kind$iv$iv4 = Kind.Singleton;
        Qualifier scopeQualifier$iv$iv4 = ScopeRegistry.INSTANCE.getRootScopeQualifier();
        List secondaryTypes$iv$iv4 = CollectionsKt.emptyList();
        BeanDefinition def$iv4 = new BeanDefinition(scopeQualifier$iv$iv4, Reflection.getOrCreateKotlinClass(GsonConverterFactory.class), null, definition$iv4, kind$iv$iv4, secondaryTypes$iv$iv4);
        String mapping$iv4 = BeanDefinitionKt.indexKey(def$iv4.getPrimaryType(), null, ScopeRegistry.INSTANCE.getRootScopeQualifier());
        SingleInstanceFactory instanceFactory$iv4 = new SingleInstanceFactory(def$iv4);
        Module.saveMapping$default(module, mapping$iv4, instanceFactory$iv4, false, 4, null);
        if (module.getCreatedAtStart()) {
            module.getEagerInstances().add(instanceFactory$iv4);
        }
        new Pair(module, instanceFactory$iv4);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Retrofit NetworkModule$lambda$4$lambda$0(Scope single, ParametersHolder it) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(it, "it");
        return getRetrofitInstance((GsonConverterFactory) single.get(Reflection.getOrCreateKotlinClass(GsonConverterFactory.class), null, null), (OkHttpClient) single.get(Reflection.getOrCreateKotlinClass(OkHttpClient.class), null, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final OkHttpClient NetworkModule$lambda$4$lambda$1(Scope single, ParametersHolder it) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(it, "it");
        return provideOkHttpClient();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SoService NetworkModule$lambda$4$lambda$2(Scope single, ParametersHolder it) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(it, "it");
        return provideAPIClient((Retrofit) single.get(Reflection.getOrCreateKotlinClass(Retrofit.class), null, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final GsonConverterFactory NetworkModule$lambda$4$lambda$3(Scope single, ParametersHolder it) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(it, "it");
        return getGsonConverterFactory();
    }

    public static final Retrofit getRetrofitInstance(GsonConverterFactory gsonConverterFactory, OkHttpClient client) {
        Intrinsics.checkNotNullParameter(gsonConverterFactory, "gsonConverterFactory");
        Intrinsics.checkNotNullParameter(client, "client");
        Retrofit build = new Retrofit.Builder().baseUrl(RemoteConstant.BASE_URL).client(client).addConverterFactory(gsonConverterFactory).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    public static final OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new Interceptor() { // from class: com.ingenious.androidbookmarksalesupgrade.koin.module.NetworkModuleKt$provideOkHttpClient$$inlined$-addInterceptor$1
            @Override // okhttp3.Interceptor
            public final Response intercept(Interceptor.Chain chain) {
                Intrinsics.checkNotNullParameter(chain, "chain");
                Request.Builder newBuilder = chain.request().newBuilder();
                LoginResponse loginData = AppPreferences.INSTANCE.getLoginData();
                Request newRequest = newBuilder.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + (loginData != null ? loginData.getToken() : null)).build();
                return chain.proceed(newRequest);
            }
        });
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(null, 1, null);
            logging.level(HttpLoggingInterceptor.Level.BASIC);
            client.addInterceptor(logging);
        }
        return client.build();
    }

    public static final SoService provideAPIClient(Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object create = retrofit.create(SoService.class);
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        return (SoService) create;
    }

    public static final GsonConverterFactory getGsonConverterFactory() {
        GsonConverterFactory create = GsonConverterFactory.create();
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        return create;
    }
}
