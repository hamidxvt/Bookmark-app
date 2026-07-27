package org.koin.android.ext.koin;

import android.app.Application;
import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.android.error.MissingAndroidContextException;
import org.koin.core.scope.Scope;

/* compiled from: ModuleExt.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\u001a\n\u0010\u0005\u001a\u00020\u0006*\u00020\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"ERROR_MSG", "", "androidApplication", "Landroid/app/Application;", "Lorg/koin/core/scope/Scope;", "androidContext", "Landroid/content/Context;", "koin-android_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class ModuleExtKt {
    public static final String ERROR_MSG = "Please use androidContext() function in your KoinApplication configuration.";

    public static final Context androidContext(Scope $this$androidContext) {
        Intrinsics.checkNotNullParameter($this$androidContext, "<this>");
        try {
            return (Context) $this$androidContext.get(Reflection.getOrCreateKotlinClass(Context.class), null, null);
        } catch (Exception e) {
            throw new MissingAndroidContextException("Can't resolve Context instance. Please use androidContext() function in your KoinApplication configuration.");
        }
    }

    public static final Application androidApplication(Scope $this$androidApplication) {
        Intrinsics.checkNotNullParameter($this$androidApplication, "<this>");
        try {
            return (Application) $this$androidApplication.get(Reflection.getOrCreateKotlinClass(Application.class), null, null);
        } catch (Exception e) {
            throw new MissingAndroidContextException("Can't resolve Application instance. Please use androidContext() function in your KoinApplication configuration.");
        }
    }
}
