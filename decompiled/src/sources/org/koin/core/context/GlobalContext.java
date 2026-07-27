package org.koin.core.context;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;
import org.koin.core.KoinApplication;
import org.koin.core.error.KoinAppAlreadyStartedException;
import org.koin.core.module.Module;

/* compiled from: GlobalContext.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u0004\u0018\u00010\u0006J\n\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0016J\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0006H\u0002J\u0010\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0016J%\u0010\u0012\u001a\u00020\u00062\u001b\u0010\u0013\u001a\u0017\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b0\u0014j\u0002`\u0015¢\u0006\u0002\b\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u000bH\u0016J\u0016\u0010\u0018\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J\u0010\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/koin/core/context/GlobalContext;", "Lorg/koin/core/context/KoinContext;", "()V", "_koin", "Lorg/koin/core/Koin;", "_koinApplication", "Lorg/koin/core/KoinApplication;", Constant.RetrofitConstants.RETROFIT_METHOD_GET, "getKoinApplicationOrNull", "getOrNull", "loadKoinModules", "", "modules", "", "Lorg/koin/core/module/Module;", "module", "register", "koinApplication", "startKoin", "appDeclaration", "Lkotlin/Function1;", "Lorg/koin/dsl/KoinAppDeclaration;", "Lkotlin/ExtensionFunctionType;", "stopKoin", "unloadKoinModules", "koin-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class GlobalContext implements KoinContext {
    public static final GlobalContext INSTANCE = new GlobalContext();
    private static Koin _koin;
    private static KoinApplication _koinApplication;

    private GlobalContext() {
    }

    @Override // org.koin.core.context.KoinContext
    public Koin get() {
        Koin koin = _koin;
        if (koin != null) {
            return koin;
        }
        throw new IllegalStateException("KoinApplication has not been started".toString());
    }

    @Override // org.koin.core.context.KoinContext
    public Koin getOrNull() {
        return _koin;
    }

    public final KoinApplication getKoinApplicationOrNull() {
        return _koinApplication;
    }

    private final void register(KoinApplication koinApplication) {
        if (_koin != null) {
            throw new KoinAppAlreadyStartedException("A Koin Application has already been started");
        }
        _koinApplication = koinApplication;
        _koin = koinApplication.getKoin();
    }

    @Override // org.koin.core.context.KoinContext
    public void stopKoin() {
        synchronized (this) {
            Koin koin = _koin;
            if (koin != null) {
                koin.close();
            }
            _koin = null;
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // org.koin.core.context.KoinContext
    public KoinApplication startKoin(KoinApplication koinApplication) {
        Intrinsics.checkNotNullParameter(koinApplication, "koinApplication");
        synchronized (this) {
            INSTANCE.register(koinApplication);
            koinApplication.createEagerInstances();
        }
        return koinApplication;
    }

    @Override // org.koin.core.context.KoinContext
    public KoinApplication startKoin(Function1<? super KoinApplication, Unit> appDeclaration) {
        KoinApplication koinApplication;
        Intrinsics.checkNotNullParameter(appDeclaration, "appDeclaration");
        synchronized (this) {
            koinApplication = KoinApplication.INSTANCE.init();
            INSTANCE.register(koinApplication);
            appDeclaration.invoke(koinApplication);
            koinApplication.createEagerInstances();
        }
        return koinApplication;
    }

    @Override // org.koin.core.context.KoinContext
    public void loadKoinModules(Module module) {
        Intrinsics.checkNotNullParameter(module, "module");
        synchronized (this) {
            Koin.loadModules$default(INSTANCE.get(), CollectionsKt.listOf(module), false, 2, null);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // org.koin.core.context.KoinContext
    public void loadKoinModules(List<Module> modules) {
        Intrinsics.checkNotNullParameter(modules, "modules");
        synchronized (this) {
            Koin.loadModules$default(INSTANCE.get(), modules, false, 2, null);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // org.koin.core.context.KoinContext
    public void unloadKoinModules(Module module) {
        Intrinsics.checkNotNullParameter(module, "module");
        synchronized (this) {
            INSTANCE.get().unloadModules(CollectionsKt.listOf(module));
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // org.koin.core.context.KoinContext
    public void unloadKoinModules(List<Module> modules) {
        Intrinsics.checkNotNullParameter(modules, "modules");
        synchronized (this) {
            INSTANCE.get().unloadModules(modules);
            Unit unit = Unit.INSTANCE;
        }
    }
}
