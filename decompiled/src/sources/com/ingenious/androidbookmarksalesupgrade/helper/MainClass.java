package com.ingenious.androidbookmarksalesupgrade.helper;

import android.app.Application;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.koin.module.NetworkModuleKt;
import com.ingenious.androidbookmarksalesupgrade.koin.module.RepositoryModuleKt;
import com.ingenious.androidbookmarksalesupgrade.koin.module.ViewModelModuleKt;
import com.ingenious.androidbookmarksalesupgrade.storage.Prefs;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.koin.android.ext.koin.KoinExtKt;
import org.koin.core.KoinApplication;
import org.koin.core.context.DefaultContextExtKt;
import org.koin.core.module.Module;

/* compiled from: MainClass.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/helper/MainClass;", "Landroid/app/Application;", "<init>", "()V", "appModules", "", "Lorg/koin/core/module/Module;", "onCreate", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes9.dex */
public final class MainClass extends Application {
    private final List<Module> appModules = CollectionsKt.listOf((Object[]) new Module[]{NetworkModuleKt.getNetworkModule(), RepositoryModuleKt.getRepositoryModule(), ViewModelModuleKt.getViewModelModule()});

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        DefaultContextExtKt.startKoin((Function1<? super KoinApplication, Unit>) new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.helper.MainClass$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onCreate$lambda$0;
                onCreate$lambda$0 = MainClass.onCreate$lambda$0(MainClass.this, (KoinApplication) obj);
                return onCreate$lambda$0;
            }
        });
        new Prefs.Builder().setContext(this).setMode(0).setPrefsName(getPackageName()).setUseDefaultSharedPreference(true).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$0(MainClass this$0, KoinApplication startKoin) {
        Intrinsics.checkNotNullParameter(startKoin, "$this$startKoin");
        KoinExtKt.androidContext(startKoin, this$0);
        startKoin.modules(this$0.appModules);
        return Unit.INSTANCE;
    }
}
