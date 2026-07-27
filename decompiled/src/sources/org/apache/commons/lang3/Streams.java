package org.apache.commons.lang3;

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
import org.apache.commons.lang3.Functions;
import org.apache.commons.lang3.Streams;

@Deprecated
/* loaded from: classes17.dex */
public class Streams {

    @Deprecated
    public static class FailableStream<O> {
        private Stream<O> stream;
        private boolean terminated;

        public FailableStream(Stream<O> stream) {
            this.stream = stream;
        }

        protected void assertNotTerminated() {
            if (this.terminated) {
                throw new IllegalStateException("This stream is already terminated.");
            }
        }

        protected void makeTerminated() {
            assertNotTerminated();
            this.terminated = true;
        }

        public FailableStream<O> filter(Functions.FailablePredicate<O, ?> predicate) {
            assertNotTerminated();
            this.stream = this.stream.filter(Functions.asPredicate(predicate));
            return this;
        }

        public void forEach(Functions.FailableConsumer<O, ?> action) {
            makeTerminated();
            stream().forEach(Functions.asConsumer(action));
        }

        public <A, R> R collect(Collector<? super O, A, R> collector) {
            makeTerminated();
            return (R) stream().collect(collector);
        }

        public <A, R> R collect(Supplier<R> supplier, BiConsumer<R, ? super O> biConsumer, BiConsumer<R, R> biConsumer2) {
            makeTerminated();
            return (R) stream().collect(supplier, biConsumer, biConsumer2);
        }

        public O reduce(O identity, BinaryOperator<O> accumulator) {
            makeTerminated();
            return stream().reduce(identity, accumulator);
        }

        public <R> FailableStream<R> map(Functions.FailableFunction<O, R, ?> mapper) {
            assertNotTerminated();
            return new FailableStream<>(this.stream.map(Functions.asFunction(mapper)));
        }

        public Stream<O> stream() {
            return this.stream;
        }

        public boolean allMatch(Functions.FailablePredicate<O, ?> predicate) {
            assertNotTerminated();
            return stream().allMatch(Functions.asPredicate(predicate));
        }

        public boolean anyMatch(Functions.FailablePredicate<O, ?> predicate) {
            assertNotTerminated();
            return stream().anyMatch(Functions.asPredicate(predicate));
        }
    }

    public static <O> FailableStream<O> stream(Stream<O> stream) {
        return new FailableStream<>(stream);
    }

    public static <O> FailableStream<O> stream(Collection<O> stream) {
        return stream(stream.stream());
    }

    @Deprecated
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
        public Supplier<List<O>> supplier() {
            return new Supplier() { // from class: org.apache.commons.lang3.Streams$ArrayCollector$$ExternalSyntheticLambda3
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Streams.ArrayCollector.$r8$lambda$aw4WkQINtNlXlsGxYEbzatsgkDc();
                }
            };
        }

        @Override // java.util.stream.Collector
        public BiConsumer<List<O>, O> accumulator() {
            return new Streams$ArrayCollector$$ExternalSyntheticLambda2();
        }

        @Override // java.util.stream.Collector
        public BinaryOperator<List<O>> combiner() {
            return new BinaryOperator() { // from class: org.apache.commons.lang3.Streams$ArrayCollector$$ExternalSyntheticLambda1
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
            return new Function() { // from class: org.apache.commons.lang3.Streams$ArrayCollector$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Streams.ArrayCollector.this.m2304xfe0fe9ee((List) obj);
                }
            };
        }

        /* renamed from: lambda$finisher$1$org-apache-commons-lang3-Streams$ArrayCollector, reason: not valid java name */
        /* synthetic */ Object[] m2304xfe0fe9ee(List list) {
            return list.toArray((Object[]) Array.newInstance((Class<?>) this.elementType, list.size()));
        }

        @Override // java.util.stream.Collector
        public Set<Collector.Characteristics> characteristics() {
            return characteristics;
        }
    }

    public static <O> Collector<O, ?, O[]> toArray(Class<O> pElementType) {
        return new ArrayCollector(pElementType);
    }
}
