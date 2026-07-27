package org.koin.core.context;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.koin.core.Koin;
import org.koin.core.KoinApplication;
import org.koin.core.module.Module;

/* compiled from: KoinContext.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0003H&J\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH&J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH&J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH&J%\u0010\u000b\u001a\u00020\f2\u001b\u0010\u000e\u001a\u0017\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00060\u000fj\u0002`\u0010¢\u0006\u0002\b\u0011H&J\b\u0010\u0012\u001a\u00020\u0006H&J\u0016\u0010\u0013\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH&J\u0010\u0010\u0013\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH&¨\u0006\u0014"}, d2 = {"Lorg/koin/core/context/KoinContext;", "", Constant.RetrofitConstants.RETROFIT_METHOD_GET, "Lorg/koin/core/Koin;", "getOrNull", "loadKoinModules", "", "modules", "", "Lorg/koin/core/module/Module;", "module", "startKoin", "Lorg/koin/core/KoinApplication;", "koinApplication", "appDeclaration", "Lkotlin/Function1;", "Lorg/koin/dsl/KoinAppDeclaration;", "Lkotlin/ExtensionFunctionType;", "stopKoin", "unloadKoinModules", "koin-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public interface KoinContext {
    Koin get();

    Koin getOrNull();

    void loadKoinModules(List<Module> modules);

    void loadKoinModules(Module module);

    KoinApplication startKoin(Function1<? super KoinApplication, Unit> appDeclaration);

    KoinApplication startKoin(KoinApplication koinApplication);

    void stopKoin();

    void unloadKoinModules(List<Module> modules);

    void unloadKoinModules(Module module);
}
