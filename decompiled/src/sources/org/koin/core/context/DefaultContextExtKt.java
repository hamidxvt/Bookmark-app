package org.koin.core.context;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.KoinApplication;
import org.koin.core.module.Module;
import org.koin.mp.KoinPlatformTools;

/* compiled from: DefaultContextExt.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0004\u001a\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007\u001a#\u0010\u0006\u001a\u00020\u00072\u001b\u0010\t\u001a\u0017\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\nj\u0002`\u000b¢\u0006\u0002\b\f\u001a\u0006\u0010\r\u001a\u00020\u0001\u001a\u0014\u0010\u000e\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u001a\u000e\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u000f"}, d2 = {"loadKoinModules", "", "modules", "", "Lorg/koin/core/module/Module;", "module", "startKoin", "Lorg/koin/core/KoinApplication;", "koinApplication", "appDeclaration", "Lkotlin/Function1;", "Lorg/koin/dsl/KoinAppDeclaration;", "Lkotlin/ExtensionFunctionType;", "stopKoin", "unloadKoinModules", "koin-core"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class DefaultContextExtKt {
    public static final KoinApplication startKoin(KoinApplication koinApplication) {
        Intrinsics.checkNotNullParameter(koinApplication, "koinApplication");
        return KoinPlatformTools.INSTANCE.defaultContext().startKoin(koinApplication);
    }

    public static final KoinApplication startKoin(Function1<? super KoinApplication, Unit> appDeclaration) {
        Intrinsics.checkNotNullParameter(appDeclaration, "appDeclaration");
        return KoinPlatformTools.INSTANCE.defaultContext().startKoin(appDeclaration);
    }

    public static final void stopKoin() {
        KoinPlatformTools.INSTANCE.defaultContext().stopKoin();
    }

    public static final void loadKoinModules(Module module) {
        Intrinsics.checkNotNullParameter(module, "module");
        KoinPlatformTools.INSTANCE.defaultContext().loadKoinModules(module);
    }

    public static final void loadKoinModules(List<Module> modules) {
        Intrinsics.checkNotNullParameter(modules, "modules");
        KoinPlatformTools.INSTANCE.defaultContext().loadKoinModules(modules);
    }

    public static final void unloadKoinModules(Module module) {
        Intrinsics.checkNotNullParameter(module, "module");
        KoinPlatformTools.INSTANCE.defaultContext().unloadKoinModules(module);
    }

    public static final void unloadKoinModules(List<Module> modules) {
        Intrinsics.checkNotNullParameter(modules, "modules");
        KoinPlatformTools.INSTANCE.defaultContext().unloadKoinModules(modules);
    }
}
