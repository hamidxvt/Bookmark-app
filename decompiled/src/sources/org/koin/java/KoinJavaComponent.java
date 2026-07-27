package org.koin.java;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.mp.KoinPlatformTools;

/* compiled from: KoinJavaComponent.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JC\u0010\u0003\u001a\u0002H\u0004\"\u0004\b\u0000\u0010\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nj\u0004\u0018\u0001`\fH\u0007¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0007JE\u0010\u0010\u001a\u0004\u0018\u0001H\u0004\"\u0004\b\u0000\u0010\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nj\u0004\u0018\u0001`\fH\u0007¢\u0006\u0002\u0010\rJD\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0012\"\u0004\b\u0000\u0010\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nj\u0004\u0018\u0001`\fH\u0007JF\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00040\u0012\"\u0004\b\u0000\u0010\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nj\u0004\u0018\u0001`\fH\u0007¨\u0006\u0014"}, d2 = {"Lorg/koin/java/KoinJavaComponent;", "", "()V", Constant.RetrofitConstants.RETROFIT_METHOD_GET, "T", "clazz", "Ljava/lang/Class;", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "parameters", "Lkotlin/Function0;", "Lorg/koin/core/parameter/ParametersHolder;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Ljava/lang/Class;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getKoin", "Lorg/koin/core/Koin;", "getOrNull", "inject", "Lkotlin/Lazy;", "injectOrNull", "koin-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class KoinJavaComponent {
    public static final KoinJavaComponent INSTANCE = new KoinJavaComponent();

    @JvmStatic
    public static final <T> T get(Class<?> clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return (T) get$default(clazz, null, null, 6, null);
    }

    @JvmStatic
    public static final <T> T get(Class<?> clazz, Qualifier qualifier) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return (T) get$default(clazz, qualifier, null, 4, null);
    }

    @JvmStatic
    public static final <T> T getOrNull(Class<?> clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return (T) getOrNull$default(clazz, null, null, 6, null);
    }

    @JvmStatic
    public static final <T> T getOrNull(Class<?> clazz, Qualifier qualifier) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return (T) getOrNull$default(clazz, qualifier, null, 4, null);
    }

    @JvmStatic
    public static final <T> Lazy<T> inject(Class<?> clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return inject$default(clazz, null, null, 6, null);
    }

    @JvmStatic
    public static final <T> Lazy<T> inject(Class<?> clazz, Qualifier qualifier) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return inject$default(clazz, qualifier, null, 4, null);
    }

    @JvmStatic
    public static final <T> Lazy<T> injectOrNull(Class<?> clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return injectOrNull$default(clazz, null, null, 6, null);
    }

    @JvmStatic
    public static final <T> Lazy<T> injectOrNull(Class<?> clazz, Qualifier qualifier) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return injectOrNull$default(clazz, qualifier, null, 4, null);
    }

    private KoinJavaComponent() {
    }

    public static /* synthetic */ Lazy inject$default(Class cls, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        return inject(cls, qualifier, function0);
    }

    @JvmStatic
    public static final <T> Lazy<T> inject(final Class<?> clazz, final Qualifier qualifier, final Function0<? extends ParametersHolder> parameters) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<T>() { // from class: org.koin.java.KoinJavaComponent$inject$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final T invoke() {
                return (T) KoinJavaComponent.get(clazz, qualifier, parameters);
            }
        });
    }

    public static /* synthetic */ Lazy injectOrNull$default(Class cls, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        return injectOrNull(cls, qualifier, function0);
    }

    @JvmStatic
    public static final <T> Lazy<T> injectOrNull(final Class<?> clazz, final Qualifier qualifier, final Function0<? extends ParametersHolder> parameters) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return LazyKt.lazy(new Function0<T>() { // from class: org.koin.java.KoinJavaComponent$injectOrNull$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final T invoke() {
                return (T) KoinJavaComponent.getOrNull(clazz, qualifier, parameters);
            }
        });
    }

    public static /* synthetic */ Object get$default(Class cls, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        return get(cls, qualifier, function0);
    }

    @JvmStatic
    public static final <T> T get(Class<?> clazz, Qualifier qualifier, Function0<? extends ParametersHolder> parameters) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return (T) getKoin().get(JvmClassMappingKt.getKotlinClass(clazz), qualifier, parameters);
    }

    public static /* synthetic */ Object getOrNull$default(Class cls, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        return getOrNull(cls, qualifier, function0);
    }

    @JvmStatic
    public static final <T> T getOrNull(Class<?> clazz, Qualifier qualifier, Function0<? extends ParametersHolder> parameters) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return (T) getKoin().getOrNull(JvmClassMappingKt.getKotlinClass(clazz), qualifier, parameters);
    }

    @JvmStatic
    public static final Koin getKoin() {
        return KoinPlatformTools.INSTANCE.defaultContext().get();
    }
}
