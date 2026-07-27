package org.apache.commons.lang3.function;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.commons.lang3.stream.Streams;

/* loaded from: classes17.dex */
public class Failable {
    public static <T, U, E extends Throwable> void accept(final FailableBiConsumer<T, U, E> consumer, final T object1, final U object2) {
        run(new FailableRunnable() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda19
            @Override // org.apache.commons.lang3.function.FailableRunnable
            public final void run() {
                FailableBiConsumer.this.accept(object1, object2);
            }
        });
    }

    public static <T, E extends Throwable> void accept(final FailableConsumer<T, E> consumer, final T object) {
        run(new FailableRunnable() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda10
            @Override // org.apache.commons.lang3.function.FailableRunnable
            public final void run() {
                FailableConsumer.this.accept(object);
            }
        });
    }

    public static <E extends Throwable> void accept(final FailableDoubleConsumer<E> consumer, final double value) {
        run(new FailableRunnable() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda2
            @Override // org.apache.commons.lang3.function.FailableRunnable
            public final void run() {
                FailableDoubleConsumer.this.accept(value);
            }
        });
    }

    public static <E extends Throwable> void accept(final FailableIntConsumer<E> consumer, final int value) {
        run(new FailableRunnable() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda15
            @Override // org.apache.commons.lang3.function.FailableRunnable
            public final void run() {
                FailableIntConsumer.this.accept(value);
            }
        });
    }

    public static <E extends Throwable> void accept(final FailableLongConsumer<E> consumer, final long value) {
        run(new FailableRunnable() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda1
            @Override // org.apache.commons.lang3.function.FailableRunnable
            public final void run() {
                FailableLongConsumer.this.accept(value);
            }
        });
    }

    public static <T, U, R, E extends Throwable> R apply(final FailableBiFunction<T, U, R, E> failableBiFunction, final T t, final U u) {
        return (R) get(new FailableSupplier() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda20
            @Override // org.apache.commons.lang3.function.FailableSupplier
            public final Object get() {
                Object apply;
                apply = FailableBiFunction.this.apply(t, u);
                return apply;
            }
        });
    }

    public static <T, R, E extends Throwable> R apply(final FailableFunction<T, R, E> failableFunction, final T t) {
        return (R) get(new FailableSupplier() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda18
            @Override // org.apache.commons.lang3.function.FailableSupplier
            public final Object get() {
                Object apply;
                apply = FailableFunction.this.apply(t);
                return apply;
            }
        });
    }

    public static <E extends Throwable> double applyAsDouble(final FailableDoubleBinaryOperator<E> function, final double left, final double right) {
        return getAsDouble(new FailableDoubleSupplier() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda11
            @Override // org.apache.commons.lang3.function.FailableDoubleSupplier
            public final double getAsDouble() {
                double applyAsDouble;
                applyAsDouble = FailableDoubleBinaryOperator.this.applyAsDouble(left, right);
                return applyAsDouble;
            }
        });
    }

    public static <T, U> BiConsumer<T, U> asBiConsumer(final FailableBiConsumer<T, U, ?> consumer) {
        return new BiConsumer() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda14
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Failable.accept(FailableBiConsumer.this, obj, obj2);
            }
        };
    }

    public static <T, U, R> BiFunction<T, U, R> asBiFunction(final FailableBiFunction<T, U, R, ?> function) {
        return new BiFunction() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda7
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                Object apply;
                apply = Failable.apply(FailableBiFunction.this, obj, obj2);
                return apply;
            }
        };
    }

    public static <T, U> BiPredicate<T, U> asBiPredicate(final FailableBiPredicate<T, U, ?> predicate) {
        return new BiPredicate() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda0
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                boolean test;
                test = Failable.test(FailableBiPredicate.this, obj, obj2);
                return test;
            }
        };
    }

    public static <V> Callable<V> asCallable(final FailableCallable<V, ?> callable) {
        return new Callable() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda9
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Object call;
                call = Failable.call(FailableCallable.this);
                return call;
            }
        };
    }

    public static <T> Consumer<T> asConsumer(final FailableConsumer<T, ?> consumer) {
        return new Consumer() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda17
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Failable.accept((FailableConsumer<Object, E>) FailableConsumer.this, obj);
            }
        };
    }

    public static <T, R> Function<T, R> asFunction(final FailableFunction<T, R, ?> function) {
        return new Function() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda13
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Object apply;
                apply = Failable.apply(FailableFunction.this, obj);
                return apply;
            }
        };
    }

    public static <T> Predicate<T> asPredicate(final FailablePredicate<T, ?> predicate) {
        return new Predicate() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda16
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean test;
                test = Failable.test(FailablePredicate.this, obj);
                return test;
            }
        };
    }

    public static Runnable asRunnable(final FailableRunnable<?> runnable) {
        return new Runnable() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                Failable.run(FailableRunnable.this);
            }
        };
    }

    public static <T> Supplier<T> asSupplier(final FailableSupplier<T, ?> supplier) {
        return new Supplier() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                Object obj;
                obj = Failable.get(FailableSupplier.this);
                return obj;
            }
        };
    }

    public static <V, E extends Throwable> V call(final FailableCallable<V, E> failableCallable) {
        failableCallable.getClass();
        return (V) get(new FailableSupplier() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda12
            @Override // org.apache.commons.lang3.function.FailableSupplier
            public final Object get() {
                return FailableCallable.this.call();
            }
        });
    }

    public static <T, E extends Throwable> T get(FailableSupplier<T, E> supplier) {
        try {
            return supplier.get();
        } catch (Throwable t) {
            throw rethrow(t);
        }
    }

    public static <E extends Throwable> boolean getAsBoolean(FailableBooleanSupplier<E> supplier) {
        try {
            return supplier.getAsBoolean();
        } catch (Throwable t) {
            throw rethrow(t);
        }
    }

    public static <E extends Throwable> double getAsDouble(FailableDoubleSupplier<E> supplier) {
        try {
            return supplier.getAsDouble();
        } catch (Throwable t) {
            throw rethrow(t);
        }
    }

    public static <E extends Throwable> int getAsInt(FailableIntSupplier<E> supplier) {
        try {
            return supplier.getAsInt();
        } catch (Throwable t) {
            throw rethrow(t);
        }
    }

    public static <E extends Throwable> long getAsLong(FailableLongSupplier<E> supplier) {
        try {
            return supplier.getAsLong();
        } catch (Throwable t) {
            throw rethrow(t);
        }
    }

    public static <E extends Throwable> short getAsShort(FailableShortSupplier<E> supplier) {
        try {
            return supplier.getAsShort();
        } catch (Throwable t) {
            throw rethrow(t);
        }
    }

    public static RuntimeException rethrow(Throwable throwable) {
        Objects.requireNonNull(throwable, "throwable");
        if (throwable instanceof RuntimeException) {
            throw ((RuntimeException) throwable);
        }
        if (throwable instanceof Error) {
            throw ((Error) throwable);
        }
        if (throwable instanceof IOException) {
            throw new UncheckedIOException((IOException) throwable);
        }
        throw new UndeclaredThrowableException(throwable);
    }

    public static <E extends Throwable> void run(FailableRunnable<E> runnable) {
        try {
            runnable.run();
        } catch (Throwable t) {
            throw rethrow(t);
        }
    }

    public static <E> Streams.FailableStream<E> stream(Collection<E> collection) {
        return new Streams.FailableStream<>(collection.stream());
    }

    public static <T> Streams.FailableStream<T> stream(Stream<T> stream) {
        return new Streams.FailableStream<>(stream);
    }

    public static <T, U, E extends Throwable> boolean test(final FailableBiPredicate<T, U, E> predicate, final T object1, final U object2) {
        return getAsBoolean(new FailableBooleanSupplier() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda6
            @Override // org.apache.commons.lang3.function.FailableBooleanSupplier
            public final boolean getAsBoolean() {
                boolean test;
                test = FailableBiPredicate.this.test(object1, object2);
                return test;
            }
        });
    }

    public static <T, E extends Throwable> boolean test(final FailablePredicate<T, E> predicate, final T object) {
        return getAsBoolean(new FailableBooleanSupplier() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda3
            @Override // org.apache.commons.lang3.function.FailableBooleanSupplier
            public final boolean getAsBoolean() {
                boolean test;
                test = FailablePredicate.this.test(object);
                return test;
            }
        });
    }

    @SafeVarargs
    public static void tryWithResources(FailableRunnable<? extends Throwable> action, FailableConsumer<Throwable, ? extends Throwable> errorHandler, FailableRunnable<? extends Throwable>... resources) {
        FailableConsumer<Throwable, ? extends Throwable> actualErrorHandler;
        if (errorHandler == null) {
            actualErrorHandler = new FailableConsumer() { // from class: org.apache.commons.lang3.function.Failable$$ExternalSyntheticLambda4
                @Override // org.apache.commons.lang3.function.FailableConsumer
                public final void accept(Object obj) {
                    Failable.rethrow((Throwable) obj);
                }
            };
        } else {
            actualErrorHandler = errorHandler;
        }
        if (resources != null) {
            for (FailableRunnable<? extends Throwable> failableRunnable : resources) {
                Objects.requireNonNull(failableRunnable, "runnable");
            }
        }
        Throwable th = null;
        try {
            action.run();
        } catch (Throwable t) {
            th = t;
        }
        if (resources != null) {
            for (FailableRunnable<? extends Throwable> failableRunnable2 : resources) {
                try {
                    failableRunnable2.run();
                } catch (Throwable t2) {
                    if (th == null) {
                        th = t2;
                    }
                }
            }
        }
        if (th != null) {
            try {
                actualErrorHandler.accept(th);
            } catch (Throwable t3) {
                throw rethrow(t3);
            }
        }
    }

    @SafeVarargs
    public static void tryWithResources(FailableRunnable<? extends Throwable> action, FailableRunnable<? extends Throwable>... resources) {
        tryWithResources(action, null, resources);
    }

    private Failable() {
    }
}
