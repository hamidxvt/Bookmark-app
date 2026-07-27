package org.apache.commons.lang3.stream;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;
import org.apache.commons.lang3.function.Failable;
import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableFunction;
import org.apache.commons.lang3.function.FailablePredicate;
import org.apache.commons.lang3.stream.Streams;

/* loaded from: classes17.dex */
public class Streams {

    public static class ArrayCollector<O> implements Collector<O, List<O>, O[]> {
        private static final Set<Collector.Characteristics> characteristics = Collections.emptySet();
        private final Class<O> elementType;

        public static /* synthetic */ ArrayList $r8$lambda$aw4WkQINtNlXlsGxYEbzatsgkDc() {
            return new ArrayList();
        }

        public ArrayCollector(Class<O> elementType) {
            this.elementType = elementType;
        }

        @Override // java.util.stream.Collector
        public BiConsumer<List<O>, O> accumulator() {
            return new org.apache.commons.lang3.Streams$ArrayCollector$$ExternalSyntheticLambda2();
        }

        @Override // java.util.stream.Collector
        public Set<Collector.Characteristics> characteristics() {
            return characteristics;
        }

        @Override // java.util.stream.Collector
        public BinaryOperator<List<O>> combiner() {
            return new BinaryOperator() { // from class: org.apache.commons.lang3.stream.Streams$ArrayCollector$$ExternalSyntheticLambda2
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return Streams.ArrayCollector.lambda$combiner$0((List) obj, (List) obj2);
                }
            };
        }

        static /* synthetic */ List lambda$combiner$0(List left, List right) {
            left.addAll(right);
            return left;
        }

        @Override // java.util.stream.Collector
        public Function<List<O>, O[]> finisher() {
            return new Function() { // from class: org.apache.commons.lang3.stream.Streams$ArrayCollector$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Streams.ArrayCollector.this.m2314x3e66eb69((List) obj);
                }
            };
        }

        /* renamed from: lambda$finisher$1$org-apache-commons-lang3-stream-Streams$ArrayCollector, reason: not valid java name */
        /* synthetic */ Object[] m2314x3e66eb69(List list) {
            return list.toArray((Object[]) Array.newInstance((Class<?>) this.elementType, list.size()));
        }

        @Override // java.util.stream.Collector
        public Supplier<List<O>> supplier() {
            return new Supplier() { // from class: org.apache.commons.lang3.stream.Streams$ArrayCollector$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Streams.ArrayCollector.$r8$lambda$aw4WkQINtNlXlsGxYEbzatsgkDc();
                }
            };
        }
    }

    public static class FailableStream<O> {
        private Stream<O> stream;
        private boolean terminated;

        public FailableStream(Stream<O> stream) {
            this.stream = stream;
        }

        public boolean allMatch(FailablePredicate<O, ?> predicate) {
            assertNotTerminated();
            return stream().allMatch(Failable.asPredicate(predicate));
        }

        public boolean anyMatch(FailablePredicate<O, ?> predicate) {
            assertNotTerminated();
            return stream().anyMatch(Failable.asPredicate(predicate));
        }

        protected void assertNotTerminated() {
            if (this.terminated) {
                throw new IllegalStateException("This stream is already terminated.");
            }
        }

        public <A, R> R collect(Collector<? super O, A, R> collector) {
            makeTerminated();
            return (R) stream().collect(collector);
        }

        public <A, R> R collect(Supplier<R> supplier, BiConsumer<R, ? super O> biConsumer, BiConsumer<R, R> biConsumer2) {
            makeTerminated();
            return (R) stream().collect(supplier, biConsumer, biConsumer2);
        }

        public FailableStream<O> filter(FailablePredicate<O, ?> predicate) {
            assertNotTerminated();
            this.stream = this.stream.filter(Failable.asPredicate(predicate));
            return this;
        }

        public void forEach(FailableConsumer<O, ?> action) {
            makeTerminated();
            stream().forEach(Failable.asConsumer(action));
        }

        protected void makeTerminated() {
            assertNotTerminated();
            this.terminated = true;
        }

        public <R> FailableStream<R> map(FailableFunction<O, R, ?> mapper) {
            assertNotTerminated();
            return new FailableStream<>(this.stream.map(Failable.asFunction(mapper)));
        }

        public O reduce(O identity, BinaryOperator<O> accumulator) {
            makeTerminated();
            return stream().reduce(identity, accumulator);
        }

        public Stream<O> stream() {
            return this.stream;
        }
    }

    public static <O> FailableStream<O> stream(Collection<O> stream) {
        return stream(stream.stream());
    }

    public static <O> FailableStream<O> stream(Stream<O> stream) {
        return new FailableStream<>(stream);
    }

    public static <O> Collector<O, ?, O[]> toArray(Class<O> pElementType) {
        return new ArrayCollector(pElementType);
    }
}
