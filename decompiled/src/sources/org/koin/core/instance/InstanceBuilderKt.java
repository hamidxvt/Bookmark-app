package org.koin.core.instance;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.core.annotation.KoinReflectAPI;
import org.koin.core.error.NoBeanDefFoundException;
import org.koin.core.logger.Level;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.parameter.ParametersHolderKt;
import org.koin.core.scope.Scope;
import org.koin.core.time.MeasureKt;
import org.koin.ext.KClassExtKt;

/* compiled from: InstanceBuilder.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a+\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0005H\u0002¢\u0006\u0002\u0010\u0006\u001a/\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010\f\u001a1\u0010\r\u001a\u0002H\u000e\"\b\b\u0000\u0010\u000e*\u00020\u0001*\u00020\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00102\u0006\u0010\u0011\u001a\u00020\u000bH\u0007¢\u0006\u0002\u0010\u0012\u001a(\u0010\r\u001a\u0002H\u000e\"\n\b\u0000\u0010\u000e\u0018\u0001*\u00020\u0001*\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\u000bH\u0087\b¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"createInstance", "", "args", "", "constructor", "Ljava/lang/reflect/Constructor;", "([Ljava/lang/Object;Ljava/lang/reflect/Constructor;)Ljava/lang/Object;", "getArguments", "scope", "Lorg/koin/core/scope/Scope;", "parameters", "Lorg/koin/core/parameter/ParametersHolder;", "(Ljava/lang/reflect/Constructor;Lorg/koin/core/scope/Scope;Lorg/koin/core/parameter/ParametersHolder;)[Ljava/lang/Object;", "newInstance", "T", "kClass", "Lkotlin/reflect/KClass;", "params", "(Lorg/koin/core/scope/Scope;Lkotlin/reflect/KClass;Lorg/koin/core/parameter/ParametersHolder;)Ljava/lang/Object;", "defParams", "(Lorg/koin/core/scope/Scope;Lorg/koin/core/parameter/ParametersHolder;)Ljava/lang/Object;", "koin-core"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class InstanceBuilderKt {
    public static /* synthetic */ Object newInstance$default(Scope $this$newInstance_u24default, ParametersHolder defParams, int i, Object obj) {
        if ((i & 1) != 0) {
            defParams = ParametersHolderKt.emptyParametersHolder();
        }
        Intrinsics.checkNotNullParameter($this$newInstance_u24default, "<this>");
        Intrinsics.checkNotNullParameter(defParams, "defParams");
        Intrinsics.reifiedOperationMarker(4, "T");
        return newInstance($this$newInstance_u24default, Reflection.getOrCreateKotlinClass(Object.class), defParams);
    }

    @KoinReflectAPI
    public static final /* synthetic */ <T> T newInstance(Scope scope, ParametersHolder defParams) {
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(defParams, "defParams");
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) newInstance(scope, Reflection.getOrCreateKotlinClass(Object.class), defParams);
    }

    @KoinReflectAPI
    public static final <T> T newInstance(final Scope scope, KClass<T> kClass, final ParametersHolder params) {
        Object[] arguments;
        Object createInstance;
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(params, "params");
        if (scope.getLogger().getLevel() == Level.DEBUG) {
            scope.getLogger().debug(Intrinsics.stringPlus("|- creating new instance - ", KClassExtKt.getFullName(kClass)));
        }
        Constructor<?>[] constructors = JvmClassMappingKt.getJavaClass((KClass) kClass).getConstructors();
        Intrinsics.checkNotNullExpressionValue(constructors, "kClass.java.constructors");
        final Constructor constructor = (Constructor) ArraysKt.firstOrNull(constructors);
        if (constructor == null) {
            throw new IllegalStateException(("No constructor found for class '" + KClassExtKt.getFullName(kClass) + '\'').toString());
        }
        if (scope.getLogger().getLevel() == Level.DEBUG) {
            Pair measureDurationForResult = MeasureKt.measureDurationForResult(new Function0<Object[]>() { // from class: org.koin.core.instance.InstanceBuilderKt$newInstance$args$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object[] invoke() {
                    Object[] arguments2;
                    arguments2 = InstanceBuilderKt.getArguments(constructor, scope, params);
                    return arguments2;
                }
            });
            arguments = (Object[]) measureDurationForResult.component1();
            scope.getLogger().debug("|- got arguments in " + ((Number) measureDurationForResult.component2()).doubleValue() + " ms");
        } else {
            arguments = getArguments(constructor, scope, params);
        }
        final Object[] objArr = arguments;
        if (scope.getLogger().getLevel() == Level.DEBUG) {
            Pair measureDurationForResult2 = MeasureKt.measureDurationForResult(new Function0<Object>() { // from class: org.koin.core.instance.InstanceBuilderKt$newInstance$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Object createInstance2;
                    createInstance2 = InstanceBuilderKt.createInstance(objArr, constructor);
                    return createInstance2;
                }
            });
            createInstance = measureDurationForResult2.component1();
            scope.getLogger().debug("|- created instance in " + ((Number) measureDurationForResult2.component2()).doubleValue() + " ms");
        } else {
            createInstance = createInstance(objArr, constructor);
        }
        return (T) createInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object createInstance(Object[] args, Constructor<? extends Object> constructor) {
        Object newInstance;
        if (args.length == 0) {
            newInstance = constructor.newInstance(new Object[0]);
        } else {
            newInstance = constructor.newInstance(Arrays.copyOf(args, args.length));
        }
        Intrinsics.checkNotNullExpressionValue(newInstance, "if (args.isEmpty()) {\n  ….newInstance(*args)\n    }");
        return newInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object[] getArguments(Constructor<?> constructor, Scope scope, final ParametersHolder parameters) {
        int length = constructor.getParameterTypes().length;
        int i = 0;
        if (length != 0) {
            Object[] result = new Object[length];
            for (int i2 = 0; i2 < length; i2++) {
                result[i2] = Unit.INSTANCE;
            }
            if (length > 0) {
                do {
                    int i3 = i;
                    i++;
                    Class p = constructor.getParameterTypes()[i3];
                    Intrinsics.checkNotNullExpressionValue(p, "p");
                    KClass parameterClass = JvmClassMappingKt.getKotlinClass(p);
                    Object orNull = scope.getOrNull(parameterClass, null, new Function0<ParametersHolder>() { // from class: org.koin.core.instance.InstanceBuilderKt$getArguments$1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final ParametersHolder invoke() {
                            return ParametersHolder.this;
                        }
                    });
                    if (orNull == null && (orNull = parameters.getOrNull(parameterClass)) == null) {
                        throw new NoBeanDefFoundException("No definition found for class '" + parameterClass + '\'');
                    }
                    result[i3] = orNull;
                } while (i < length);
            }
            return result;
        }
        return new Object[0];
    }
}
