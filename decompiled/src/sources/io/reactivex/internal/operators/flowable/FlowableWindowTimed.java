package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.subscribers.QueueDrainSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.processors.UnicastProcessor;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes17.dex */
public final class FlowableWindowTimed<T> extends AbstractFlowableWithUpstream<T, Flowable<T>> {
    final int bufferSize;
    final long maxSize;
    final boolean restartTimerOnMaxSize;
    final Scheduler scheduler;
    final long timeskip;
    final long timespan;
    final TimeUnit unit;

    public FlowableWindowTimed(Flowable<T> source, long timespan, long timeskip, TimeUnit unit, Scheduler scheduler, long maxSize, int bufferSize, boolean restartTimerOnMaxSize) {
        super(source);
        this.timespan = timespan;
        this.timeskip = timeskip;
        this.unit = unit;
        this.scheduler = scheduler;
        this.maxSize = maxSize;
        this.bufferSize = bufferSize;
        this.restartTimerOnMaxSize = restartTimerOnMaxSize;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super Flowable<T>> s) {
        SerializedSubscriber<Flowable<T>> actual = new SerializedSubscriber<>(s);
        if (this.timespan != this.timeskip) {
            this.source.subscribe((FlowableSubscriber) new WindowSkipSubscriber(actual, this.timespan, this.timeskip, this.unit, this.scheduler.createWorker(), this.bufferSize));
        } else if (this.maxSize == Long.MAX_VALUE) {
            this.source.subscribe((FlowableSubscriber) new WindowExactUnboundedSubscriber(actual, this.timespan, this.unit, this.scheduler, this.bufferSize));
        } else {
            this.source.subscribe((FlowableSubscriber) new WindowExactBoundedSubscriber(actual, this.timespan, this.unit, this.scheduler, this.bufferSize, this.maxSize, this.restartTimerOnMaxSize));
        }
    }

    static final class WindowExactUnboundedSubscriber<T> extends QueueDrainSubscriber<T, Object, Flowable<T>> implements FlowableSubscriber<T>, Subscription, Runnable {
        static final Object NEXT = new Object();
        final int bufferSize;
        Subscription s;
        final Scheduler scheduler;
        volatile boolean terminated;
        final SequentialDisposable timer;
        final long timespan;
        final TimeUnit unit;
        UnicastProcessor<T> window;

        WindowExactUnboundedSubscriber(Subscriber<? super Flowable<T>> actual, long timespan, TimeUnit unit, Scheduler scheduler, int bufferSize) {
            super(actual, new MpscLinkedQueue());
            this.timer = new SequentialDisposable();
            this.timespan = timespan;
            this.unit = unit;
            this.scheduler = scheduler;
            this.bufferSize = bufferSize;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.s, s)) {
                this.s = s;
                this.window = UnicastProcessor.create(this.bufferSize);
                Subscriber<? super V> subscriber = this.actual;
                subscriber.onSubscribe(this);
                long r = requested();
                if (r != 0) {
                    subscriber.onNext(this.window);
                    if (r != Long.MAX_VALUE) {
                        produced(1L);
                    }
                    if (!this.cancelled && this.timer.replace(this.scheduler.schedulePeriodicallyDirect(this, this.timespan, this.timespan, this.unit))) {
                        s.request(Long.MAX_VALUE);
                        return;
                    }
                    return;
                }
                this.cancelled = true;
                s.cancel();
                subscriber.onError(new MissingBackpressureException("Could not deliver first window due to lack of requests."));
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.terminated) {
                return;
            }
            if (fastEnter()) {
                this.window.onNext(t);
                if (leave(-1) == 0) {
                    return;
                }
            } else {
                this.queue.offer(NotificationLite.next(t));
                if (!enter()) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.actual.onError(t);
            dispose();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.actual.onComplete();
            dispose();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            requested(n);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
        }

        public void dispose() {
            DisposableHelper.dispose(this.timer);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.cancelled) {
                this.terminated = true;
                dispose();
            }
            this.queue.offer(NEXT);
            if (enter()) {
                drainLoop();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0024, code lost:
        
            r2.onError(r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:?, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x0028, code lost:
        
            r2.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x002b, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:8:0x0018, code lost:
        
            r12.window = null;
            r0.clear();
            dispose();
            r7 = r12.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
        
            if (r7 == null) goto L11;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v0, types: [io.reactivex.processors.UnicastProcessor<T>] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        void drainLoop() {
            SimpleQueue simpleQueue = this.queue;
            Subscriber<? super V> subscriber = this.actual;
            UnicastProcessor<T> unicastProcessor = this.window;
            int i = 1;
            while (true) {
                boolean z = this.terminated;
                boolean z2 = this.done;
                Object poll = simpleQueue.poll();
                if (!z2 || (poll != null && poll != NEXT)) {
                    if (poll != null) {
                        if (poll == NEXT) {
                            unicastProcessor.onComplete();
                            if (!z) {
                                unicastProcessor = (UnicastProcessor<T>) UnicastProcessor.create(this.bufferSize);
                                this.window = unicastProcessor;
                                long requested = requested();
                                if (requested != 0) {
                                    subscriber.onNext(unicastProcessor);
                                    if (requested != Long.MAX_VALUE) {
                                        produced(1L);
                                    }
                                } else {
                                    this.window = null;
                                    this.queue.clear();
                                    this.s.cancel();
                                    dispose();
                                    subscriber.onError(new MissingBackpressureException("Could not deliver first window due to lack of requests."));
                                    return;
                                }
                            } else {
                                this.s.cancel();
                            }
                        } else {
                            unicastProcessor.onNext(NotificationLite.getValue(poll));
                        }
                    } else {
                        i = leave(-i);
                        if (i == 0) {
                            return;
                        }
                    }
                }
            }
        }
    }

    static final class WindowExactBoundedSubscriber<T> extends QueueDrainSubscriber<T, Object, Flowable<T>> implements Subscription {
        final int bufferSize;
        long count;
        final long maxSize;
        long producerIndex;
        final boolean restartTimerOnMaxSize;
        Subscription s;
        final Scheduler scheduler;
        volatile boolean terminated;
        final SequentialDisposable timer;
        final long timespan;
        final TimeUnit unit;
        UnicastProcessor<T> window;
        final Scheduler.Worker worker;

        WindowExactBoundedSubscriber(Subscriber<? super Flowable<T>> actual, long timespan, TimeUnit unit, Scheduler scheduler, int bufferSize, long maxSize, boolean restartTimerOnMaxSize) {
            super(actual, new MpscLinkedQueue());
            this.timer = new SequentialDisposable();
            this.timespan = timespan;
            this.unit = unit;
            this.scheduler = scheduler;
            this.bufferSize = bufferSize;
            this.maxSize = maxSize;
            this.restartTimerOnMaxSize = restartTimerOnMaxSize;
            if (restartTimerOnMaxSize) {
                this.worker = scheduler.createWorker();
            } else {
                this.worker = null;
            }
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.s, s)) {
                this.s = s;
                Subscriber<? super V> subscriber = this.actual;
                subscriber.onSubscribe(this);
                if (this.cancelled) {
                    return;
                }
                UnicastProcessor<T> w = UnicastProcessor.create(this.bufferSize);
                this.window = w;
                long r = requested();
                if (r == 0) {
                    this.cancelled = true;
                    s.cancel();
                    subscriber.onError(new MissingBackpressureException("Could not deliver initial window due to lack of requests."));
                    return;
                }
                subscriber.onNext(w);
                if (r != Long.MAX_VALUE) {
                    produced(1L);
                }
                ConsumerIndexHolder consumerIndexHolder = new ConsumerIndexHolder(this.producerIndex, this);
                Disposable d = this.restartTimerOnMaxSize ? this.worker.schedulePeriodically(consumerIndexHolder, this.timespan, this.timespan, this.unit) : this.scheduler.schedulePeriodicallyDirect(consumerIndexHolder, this.timespan, this.timespan, this.unit);
                if (this.timer.replace(d)) {
                    s.request(Long.MAX_VALUE);
                }
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.terminated) {
                return;
            }
            if (!fastEnter()) {
                this.queue.offer(NotificationLite.next(t));
                if (!enter()) {
                    return;
                }
            } else {
                UnicastProcessor<T> w = this.window;
                w.onNext(t);
                long c = this.count + 1;
                if (c >= this.maxSize) {
                    this.producerIndex++;
                    this.count = 0L;
                    w.onComplete();
                    long r = requested();
                    if (r == 0) {
                        this.window = null;
                        this.s.cancel();
                        this.actual.onError(new MissingBackpressureException("Could not deliver window due to lack of requests"));
                        dispose();
                        return;
                    }
                    UnicastProcessor<T> w2 = UnicastProcessor.create(this.bufferSize);
                    this.window = w2;
                    this.actual.onNext(w2);
                    if (r != Long.MAX_VALUE) {
                        produced(1L);
                    }
                    if (this.restartTimerOnMaxSize) {
                        Disposable tm = this.timer.get();
                        tm.dispose();
                        Disposable task = this.worker.schedulePeriodically(new ConsumerIndexHolder(this.producerIndex, this), this.timespan, this.timespan, this.unit);
                        this.timer.replace(task);
                    }
                } else {
                    this.count = c;
                }
                if (leave(-1) == 0) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.actual.onError(t);
            dispose();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.actual.onComplete();
            dispose();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            requested(n);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
        }

        public void dispose() {
            DisposableHelper.dispose(this.timer);
            Scheduler.Worker w = this.worker;
            if (w != null) {
                w.dispose();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v0, types: [org.reactivestreams.Subscriber<? super V>] */
        /* JADX WARN: Type inference failed for: r2v1, types: [org.reactivestreams.Subscriber] */
        /* JADX WARN: Type inference failed for: r2v2 */
        /* JADX WARN: Type inference failed for: r2v3 */
        /* JADX WARN: Type inference failed for: r3v0, types: [io.reactivex.processors.UnicastProcessor<T>] */
        /* JADX WARN: Type inference failed for: r3v1, types: [io.reactivex.processors.UnicastProcessor] */
        /* JADX WARN: Type inference failed for: r3v10, types: [io.reactivex.processors.UnicastProcessor] */
        /* JADX WARN: Type inference failed for: r3v16 */
        /* JADX WARN: Type inference failed for: r3v17 */
        /* JADX WARN: Type inference failed for: r3v2 */
        /* JADX WARN: Type inference failed for: r3v3, types: [io.reactivex.processors.UnicastProcessor, io.reactivex.processors.UnicastProcessor<T>, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v4 */
        void drainLoop() {
            SimpleQueue simpleQueue;
            Object obj;
            int i;
            UnicastProcessor<T> unicastProcessor;
            SimpleQueue simpleQueue2 = this.queue;
            ?? r2 = this.actual;
            UnicastProcessor<T> unicastProcessor2 = this.window;
            int i2 = 1;
            while (!this.terminated) {
                boolean z = this.done;
                Object poll = simpleQueue2.poll();
                boolean z2 = poll == null;
                boolean z3 = poll instanceof ConsumerIndexHolder;
                if (z && (z2 || z3)) {
                    this.window = null;
                    simpleQueue2.clear();
                    Throwable th = this.error;
                    if (th != null) {
                        ((UnicastProcessor) unicastProcessor2).onError(th);
                    } else {
                        ((UnicastProcessor) unicastProcessor2).onComplete();
                    }
                    dispose();
                    return;
                }
                if (z2) {
                    i2 = leave(-i2);
                    if (i2 == 0) {
                        return;
                    }
                } else if (z3) {
                    ConsumerIndexHolder consumerIndexHolder = (ConsumerIndexHolder) poll;
                    if (this.restartTimerOnMaxSize || this.producerIndex == consumerIndexHolder.index) {
                        ((UnicastProcessor) unicastProcessor2).onComplete();
                        this.count = 0L;
                        unicastProcessor2 = (UnicastProcessor<T>) UnicastProcessor.create(this.bufferSize);
                        this.window = unicastProcessor2;
                        long requested = requested();
                        if (requested == 0) {
                            this.window = null;
                            this.queue.clear();
                            this.s.cancel();
                            r2.onError(new MissingBackpressureException("Could not deliver first window due to lack of requests."));
                            dispose();
                            return;
                        }
                        r2.onNext(unicastProcessor2);
                        if (requested != Long.MAX_VALUE) {
                            produced(1L);
                        }
                    }
                } else {
                    ((UnicastProcessor) unicastProcessor2).onNext(NotificationLite.getValue(poll));
                    long j = this.count + 1;
                    if (j >= this.maxSize) {
                        this.producerIndex++;
                        this.count = 0L;
                        ((UnicastProcessor) unicastProcessor2).onComplete();
                        long requested2 = requested();
                        if (requested2 == 0) {
                            this.window = null;
                            this.s.cancel();
                            this.actual.onError(new MissingBackpressureException("Could not deliver window due to lack of requests"));
                            dispose();
                            return;
                        }
                        UnicastProcessor<T> create = UnicastProcessor.create(this.bufferSize);
                        this.window = create;
                        this.actual.onNext(create);
                        if (requested2 != Long.MAX_VALUE) {
                            produced(1L);
                        }
                        if (!this.restartTimerOnMaxSize) {
                            simpleQueue = simpleQueue2;
                            obj = r2;
                            unicastProcessor = create;
                            i = i2;
                        } else {
                            this.timer.get().dispose();
                            simpleQueue = simpleQueue2;
                            obj = r2;
                            unicastProcessor = create;
                            i = i2;
                            this.timer.replace(this.worker.schedulePeriodically(new ConsumerIndexHolder(this.producerIndex, this), this.timespan, this.timespan, this.unit));
                        }
                        unicastProcessor2 = unicastProcessor;
                    } else {
                        simpleQueue = simpleQueue2;
                        obj = r2;
                        i = i2;
                        this.count = j;
                        unicastProcessor2 = unicastProcessor2;
                    }
                    simpleQueue2 = simpleQueue;
                    r2 = obj;
                    i2 = i;
                }
            }
            this.s.cancel();
            simpleQueue2.clear();
            dispose();
        }

        static final class ConsumerIndexHolder implements Runnable {
            final long index;
            final WindowExactBoundedSubscriber<?> parent;

            ConsumerIndexHolder(long index, WindowExactBoundedSubscriber<?> parent) {
                this.index = index;
                this.parent = parent;
            }

            @Override // java.lang.Runnable
            public void run() {
                WindowExactBoundedSubscriber<?> p = this.parent;
                if (!((WindowExactBoundedSubscriber) p).cancelled) {
                    ((WindowExactBoundedSubscriber) p).queue.offer(this);
                } else {
                    p.terminated = true;
                    p.dispose();
                }
                if (p.enter()) {
                    p.drainLoop();
                }
            }
        }
    }

    static final class WindowSkipSubscriber<T> extends QueueDrainSubscriber<T, Object, Flowable<T>> implements Subscription, Runnable {
        final int bufferSize;
        Subscription s;
        volatile boolean terminated;
        final long timeskip;
        final long timespan;
        final TimeUnit unit;
        final List<UnicastProcessor<T>> windows;
        final Scheduler.Worker worker;

        WindowSkipSubscriber(Subscriber<? super Flowable<T>> actual, long timespan, long timeskip, TimeUnit unit, Scheduler.Worker worker, int bufferSize) {
            super(actual, new MpscLinkedQueue());
            this.timespan = timespan;
            this.timeskip = timeskip;
            this.unit = unit;
            this.worker = worker;
            this.bufferSize = bufferSize;
            this.windows = new LinkedList();
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.s, s)) {
                this.s = s;
                this.actual.onSubscribe(this);
                if (this.cancelled) {
                    return;
                }
                long r = requested();
                if (r != 0) {
                    UnicastProcessor<T> w = UnicastProcessor.create(this.bufferSize);
                    this.windows.add(w);
                    this.actual.onNext(w);
                    if (r != Long.MAX_VALUE) {
                        produced(1L);
                    }
                    this.worker.schedule(new Completion(w), this.timespan, this.unit);
                    this.worker.schedulePeriodically(this, this.timeskip, this.timeskip, this.unit);
                    s.request(Long.MAX_VALUE);
                    return;
                }
                s.cancel();
                this.actual.onError(new MissingBackpressureException("Could not emit the first window due to lack of requests"));
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (fastEnter()) {
                for (UnicastProcessor<T> w : this.windows) {
                    w.onNext(t);
                }
                if (leave(-1) == 0) {
                    return;
                }
            } else {
                this.queue.offer(t);
                if (!enter()) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.actual.onError(t);
            dispose();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.actual.onComplete();
            dispose();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            requested(n);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
        }

        public void dispose() {
            this.worker.dispose();
        }

        void complete(UnicastProcessor<T> w) {
            this.queue.offer(new SubjectWork(w, false));
            if (enter()) {
                drainLoop();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void drainLoop() {
            SimpleQueue simpleQueue;
            int i;
            SimpleQueue simpleQueue2 = this.queue;
            Subscriber<? super V> subscriber = this.actual;
            List<UnicastProcessor<T>> list = this.windows;
            int i2 = 1;
            while (!this.terminated) {
                boolean z = this.done;
                Object poll = simpleQueue2.poll();
                boolean z2 = poll == null;
                boolean z3 = poll instanceof SubjectWork;
                if (z && (z2 || z3)) {
                    simpleQueue2.clear();
                    Throwable th = this.error;
                    if (th != null) {
                        Iterator<UnicastProcessor<T>> it = list.iterator();
                        while (it.hasNext()) {
                            it.next().onError(th);
                        }
                    } else {
                        Iterator<UnicastProcessor<T>> it2 = list.iterator();
                        while (it2.hasNext()) {
                            it2.next().onComplete();
                        }
                    }
                    list.clear();
                    dispose();
                    return;
                }
                if (z2) {
                    i2 = leave(-i2);
                    if (i2 == 0) {
                        return;
                    }
                } else {
                    if (z3) {
                        SubjectWork subjectWork = (SubjectWork) poll;
                        if (subjectWork.open) {
                            if (this.cancelled) {
                                simpleQueue = simpleQueue2;
                                i = i2;
                            } else {
                                long requested = requested();
                                if (requested != 0) {
                                    UnicastProcessor<T> create = UnicastProcessor.create(this.bufferSize);
                                    list.add(create);
                                    subscriber.onNext(create);
                                    if (requested != Long.MAX_VALUE) {
                                        produced(1L);
                                    }
                                    i = i2;
                                    simpleQueue = simpleQueue2;
                                    this.worker.schedule(new Completion(create), this.timespan, this.unit);
                                } else {
                                    simpleQueue = simpleQueue2;
                                    i = i2;
                                    subscriber.onError(new MissingBackpressureException("Can't emit window due to lack of requests"));
                                }
                            }
                        } else {
                            simpleQueue = simpleQueue2;
                            i = i2;
                            list.remove(subjectWork.w);
                            subjectWork.w.onComplete();
                            if (list.isEmpty() && this.cancelled) {
                                this.terminated = true;
                            }
                        }
                    } else {
                        simpleQueue = simpleQueue2;
                        i = i2;
                        Iterator<UnicastProcessor<T>> it3 = list.iterator();
                        while (it3.hasNext()) {
                            it3.next().onNext(poll);
                        }
                    }
                    i2 = i;
                    simpleQueue2 = simpleQueue;
                }
            }
            this.s.cancel();
            dispose();
            simpleQueue2.clear();
            list.clear();
        }

        @Override // java.lang.Runnable
        public void run() {
            UnicastProcessor<T> w = UnicastProcessor.create(this.bufferSize);
            SubjectWork<T> sw = new SubjectWork<>(w, true);
            if (!this.cancelled) {
                this.queue.offer(sw);
            }
            if (enter()) {
                drainLoop();
            }
        }

        static final class SubjectWork<T> {
            final boolean open;
            final UnicastProcessor<T> w;

            SubjectWork(UnicastProcessor<T> w, boolean open) {
                this.w = w;
                this.open = open;
            }
        }

        final class Completion implements Runnable {
            private final UnicastProcessor<T> processor;

            Completion(UnicastProcessor<T> processor) {
                this.processor = processor;
            }

            @Override // java.lang.Runnable
            public void run() {
                WindowSkipSubscriber.this.complete(this.processor);
            }
        }
    }
}
