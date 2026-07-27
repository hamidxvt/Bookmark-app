package io.reactivex.internal.operators.flowable;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes17.dex */
public final class FlowableFlatMap<T, U> extends AbstractFlowableWithUpstream<T, U> {
    final int bufferSize;
    final boolean delayErrors;
    final Function<? super T, ? extends Publisher<? extends U>> mapper;
    final int maxConcurrency;

    public FlowableFlatMap(Flowable<T> source, Function<? super T, ? extends Publisher<? extends U>> mapper, boolean delayErrors, int maxConcurrency, int bufferSize) {
        super(source);
        this.mapper = mapper;
        this.delayErrors = delayErrors;
        this.maxConcurrency = maxConcurrency;
        this.bufferSize = bufferSize;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super U> s) {
        if (FlowableScalarXMap.tryScalarXMapSubscribe(this.source, s, this.mapper)) {
            return;
        }
        this.source.subscribe((FlowableSubscriber) subscribe(s, this.mapper, this.delayErrors, this.maxConcurrency, this.bufferSize));
    }

    public static <T, U> FlowableSubscriber<T> subscribe(Subscriber<? super U> s, Function<? super T, ? extends Publisher<? extends U>> mapper, boolean delayErrors, int maxConcurrency, int bufferSize) {
        return new MergeSubscriber(s, mapper, delayErrors, maxConcurrency, bufferSize);
    }

    static final class MergeSubscriber<T, U> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -2117620485640801370L;
        final Subscriber<? super U> actual;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        long lastId;
        int lastIndex;
        final Function<? super T, ? extends Publisher<? extends U>> mapper;
        final int maxConcurrency;
        volatile SimplePlainQueue<U> queue;
        int scalarEmitted;
        final int scalarLimit;
        long uniqueId;
        Subscription upstream;
        static final InnerSubscriber<?, ?>[] EMPTY = new InnerSubscriber[0];
        static final InnerSubscriber<?, ?>[] CANCELLED = new InnerSubscriber[0];
        final AtomicThrowable errs = new AtomicThrowable();
        final AtomicReference<InnerSubscriber<?, ?>[]> subscribers = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();

        MergeSubscriber(Subscriber<? super U> actual, Function<? super T, ? extends Publisher<? extends U>> mapper, boolean delayErrors, int maxConcurrency, int bufferSize) {
            this.actual = actual;
            this.mapper = mapper;
            this.delayErrors = delayErrors;
            this.maxConcurrency = maxConcurrency;
            this.bufferSize = bufferSize;
            this.scalarLimit = Math.max(1, maxConcurrency >> 1);
            this.subscribers.lazySet(EMPTY);
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.actual.onSubscribe(this);
                if (!this.cancelled) {
                    if (this.maxConcurrency == Integer.MAX_VALUE) {
                        s.request(Long.MAX_VALUE);
                    } else {
                        s.request(this.maxConcurrency);
                    }
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            try {
                Publisher<? extends U> p = (Publisher) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null Publisher");
                if (p instanceof Callable) {
                    try {
                        Object call = ((Callable) p).call();
                        if (call != null) {
                            tryEmitScalar(call);
                            return;
                        }
                        if (this.maxConcurrency == Integer.MAX_VALUE || this.cancelled) {
                            return;
                        }
                        int i = this.scalarEmitted + 1;
                        this.scalarEmitted = i;
                        if (i == this.scalarLimit) {
                            this.scalarEmitted = 0;
                            this.upstream.request(this.scalarLimit);
                            return;
                        }
                        return;
                    } catch (Throwable ex) {
                        Exceptions.throwIfFatal(ex);
                        this.errs.addThrowable(ex);
                        drain();
                        return;
                    }
                }
                long j = this.uniqueId;
                this.uniqueId = 1 + j;
                InnerSubscriber<T, U> inner = new InnerSubscriber<>(this, j);
                if (addInner(inner)) {
                    p.subscribe(inner);
                }
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                this.upstream.cancel();
                onError(e);
            }
        }

        boolean addInner(InnerSubscriber<T, U> inner) {
            InnerSubscriber<?, ?>[] a;
            InnerSubscriber[] innerSubscriberArr;
            do {
                a = this.subscribers.get();
                if (a == CANCELLED) {
                    inner.dispose();
                    return false;
                }
                int n = a.length;
                innerSubscriberArr = new InnerSubscriber[n + 1];
                System.arraycopy(a, 0, innerSubscriberArr, 0, n);
                innerSubscriberArr[n] = inner;
            } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.subscribers, a, innerSubscriberArr));
            return true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        void removeInner(InnerSubscriber<T, U> inner) {
            InnerSubscriber<?, ?>[] innerSubscriberArr;
            InnerSubscriber<?, ?>[] b;
            do {
                innerSubscriberArr = this.subscribers.get();
                int n = innerSubscriberArr.length;
                if (n == 0) {
                    return;
                }
                int j = -1;
                int i = 0;
                while (true) {
                    if (i >= n) {
                        break;
                    }
                    if (innerSubscriberArr[i] != inner) {
                        i++;
                    } else {
                        j = i;
                        break;
                    }
                }
                if (j < 0) {
                    return;
                }
                if (n == 1) {
                    b = EMPTY;
                } else {
                    InnerSubscriber<?, ?>[] b2 = new InnerSubscriber[n - 1];
                    System.arraycopy(innerSubscriberArr, 0, b2, 0, j);
                    System.arraycopy(innerSubscriberArr, j + 1, b2, j, (n - j) - 1);
                    b = b2;
                }
            } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.subscribers, innerSubscriberArr, b));
        }

        SimpleQueue<U> getMainQueue() {
            SimplePlainQueue<U> q = this.queue;
            if (q == null) {
                if (this.maxConcurrency == Integer.MAX_VALUE) {
                    q = new SpscLinkedArrayQueue(this.bufferSize);
                } else {
                    q = new SpscArrayQueue(this.maxConcurrency);
                }
                this.queue = q;
            }
            return q;
        }

        void tryEmitScalar(U value) {
            if (get() == 0 && compareAndSet(0, 1)) {
                long r = this.requested.get();
                SimpleQueue<U> q = this.queue;
                if (r != 0 && (q == null || q.isEmpty())) {
                    this.actual.onNext(value);
                    if (r != Long.MAX_VALUE) {
                        this.requested.decrementAndGet();
                    }
                    if (this.maxConcurrency != Integer.MAX_VALUE && !this.cancelled) {
                        int i = this.scalarEmitted + 1;
                        this.scalarEmitted = i;
                        if (i == this.scalarLimit) {
                            this.scalarEmitted = 0;
                            this.upstream.request(this.scalarLimit);
                        }
                    }
                } else {
                    if (q == null) {
                        q = getMainQueue();
                    }
                    if (!q.offer(value)) {
                        onError(new IllegalStateException("Scalar queue full?!"));
                        return;
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else if (!getMainQueue().offer(value)) {
                onError(new IllegalStateException("Scalar queue full?!"));
                return;
            } else if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        SimpleQueue<U> getInnerQueue(InnerSubscriber<T, U> inner) {
            SimpleQueue<U> q = inner.queue;
            if (q == null) {
                SimpleQueue<U> q2 = new SpscArrayQueue<>(this.bufferSize);
                inner.queue = q2;
                return q2;
            }
            return q;
        }

        void tryEmit(U value, InnerSubscriber<T, U> inner) {
            if (get() == 0 && compareAndSet(0, 1)) {
                long r = this.requested.get();
                SimpleQueue<U> q = inner.queue;
                if (r != 0 && (q == null || q.isEmpty())) {
                    this.actual.onNext(value);
                    if (r != Long.MAX_VALUE) {
                        this.requested.decrementAndGet();
                    }
                    inner.requestMore(1L);
                } else {
                    if (q == null) {
                        q = getInnerQueue(inner);
                    }
                    if (!q.offer(value)) {
                        onError(new MissingBackpressureException("Inner queue full?!"));
                        return;
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                SimpleQueue<U> q2 = inner.queue;
                if (q2 == null) {
                    q2 = new SpscArrayQueue(this.bufferSize);
                    inner.queue = q2;
                }
                if (!q2.offer(value)) {
                    onError(new MissingBackpressureException("Inner queue full?!"));
                    return;
                } else if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
            } else if (this.errs.addThrowable(t)) {
                this.done = true;
                drain();
            } else {
                RxJavaPlugins.onError(t);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            drain();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            SimpleQueue<U> q;
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                disposeAll();
                if (getAndIncrement() == 0 && (q = this.queue) != null) {
                    q.clear();
                }
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:103:0x01f3, code lost:
        
            r32.lastIndex = r5;
            r32.lastId = r11[r5].id;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:146:0x01e7 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:57:0x00e5  */
        /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        void drainLoop() {
            boolean z;
            long j;
            long j2;
            boolean z2;
            int i;
            boolean z3;
            int i2;
            boolean z4;
            boolean z5;
            int i3;
            boolean z6;
            SimplePlainQueue<U> simplePlainQueue;
            boolean z7;
            int i4;
            long j3;
            Subscriber<? super U> subscriber = this.actual;
            int i5 = 1;
            while (!checkTerminate()) {
                SimplePlainQueue<U> simplePlainQueue2 = this.queue;
                long j4 = this.requested.get();
                boolean z8 = j4 == Long.MAX_VALUE;
                long j5 = 0;
                long j6 = 1;
                if (simplePlainQueue2 != null) {
                    while (true) {
                        long j7 = 0;
                        Object obj = null;
                        while (true) {
                            if (j4 == 0) {
                                break;
                            }
                            Object obj2 = (U) simplePlainQueue2.poll();
                            if (checkTerminate()) {
                                return;
                            }
                            if (obj2 == null) {
                                obj = obj2;
                                break;
                            }
                            subscriber.onNext(obj2);
                            j5 += j6;
                            j7 += j6;
                            j4 -= j6;
                            obj = obj2;
                        }
                        if (j7 != 0) {
                            if (z8) {
                                j4 = Long.MAX_VALUE;
                            } else {
                                j4 = this.requested.addAndGet(-j7);
                            }
                        }
                        if (j4 == 0 || obj == null) {
                            break;
                        } else {
                            j6 = 1;
                        }
                    }
                }
                boolean z9 = this.done;
                SimplePlainQueue<U> simplePlainQueue3 = this.queue;
                InnerSubscriber<T, U>[] innerSubscriberArr = (InnerSubscriber[]) this.subscribers.get();
                int length = innerSubscriberArr.length;
                if (z9 && ((simplePlainQueue3 == null || simplePlainQueue3.isEmpty()) && length == 0)) {
                    Throwable terminate = this.errs.terminate();
                    if (terminate != ExceptionHelper.TERMINATED) {
                        if (terminate == null) {
                            subscriber.onComplete();
                            return;
                        } else {
                            subscriber.onError(terminate);
                            return;
                        }
                    }
                    return;
                }
                Throwable th = null;
                if (length != 0) {
                    long j8 = this.lastId;
                    int i6 = this.lastIndex;
                    if (length > i6) {
                        boolean z10 = false;
                        j = j4;
                        z = z10;
                        if (innerSubscriberArr[i6].id == j8) {
                            j2 = j5;
                            i = i6;
                            z2 = z9;
                            z3 = z10;
                            i2 = 0;
                            int i7 = i;
                            z4 = z3;
                            while (true) {
                                if (i2 < length) {
                                    th = z4 ? 1 : 0;
                                    j5 = j2;
                                    break;
                                }
                                if (checkTerminate()) {
                                    return;
                                }
                                InnerSubscriber<T, U> innerSubscriber = innerSubscriberArr[i7];
                                U u = null;
                                while (!checkTerminate()) {
                                    SimpleQueue<U> simpleQueue = innerSubscriber.queue;
                                    if (simpleQueue == null) {
                                        z5 = z8;
                                        i3 = i2;
                                        z6 = z2;
                                        simplePlainQueue = simplePlainQueue3;
                                    } else {
                                        z6 = z2;
                                        simplePlainQueue = simplePlainQueue3;
                                        long j9 = 0;
                                        long j10 = j;
                                        Object obj3 = u;
                                        while (true) {
                                            SimpleQueue<U> simpleQueue2 = simpleQueue;
                                            if (j10 == 0) {
                                                u = (U) obj3;
                                                break;
                                            }
                                            try {
                                                u = simpleQueue2.poll();
                                                if (u == null) {
                                                    break;
                                                }
                                                subscriber.onNext(u);
                                                if (checkTerminate()) {
                                                    return;
                                                }
                                                j10--;
                                                j9++;
                                                obj3 = u;
                                                simpleQueue = simpleQueue2;
                                            } catch (Throwable th2) {
                                                Exceptions.throwIfFatal(th2);
                                                innerSubscriber.dispose();
                                                this.errs.addThrowable(th2);
                                                if (!this.delayErrors) {
                                                    this.upstream.cancel();
                                                }
                                                if (checkTerminate()) {
                                                    return;
                                                }
                                                removeInner(innerSubscriber);
                                                i4 = i2 + 1;
                                                z5 = z8;
                                                z7 = true;
                                                j = j10;
                                            }
                                        }
                                        if (j9 == 0) {
                                            z5 = z8;
                                            i3 = i2;
                                            j = j10;
                                        } else {
                                            if (!z8) {
                                                z5 = z8;
                                                i3 = i2;
                                                j3 = this.requested.addAndGet(-j9);
                                            } else {
                                                z5 = z8;
                                                i3 = i2;
                                                j3 = Long.MAX_VALUE;
                                            }
                                            innerSubscriber.requestMore(j9);
                                            j = j3;
                                        }
                                        if (j != 0 && u != null) {
                                            z8 = z5;
                                            simplePlainQueue3 = simplePlainQueue;
                                            z2 = z6;
                                            i2 = i3;
                                        }
                                    }
                                    boolean z11 = innerSubscriber.done;
                                    SimpleQueue<U> simpleQueue3 = innerSubscriber.queue;
                                    if (!z11) {
                                        z7 = z4;
                                    } else if (simpleQueue3 == null || simpleQueue3.isEmpty()) {
                                        removeInner(innerSubscriber);
                                        if (checkTerminate()) {
                                            return;
                                        }
                                        j2++;
                                        z7 = true;
                                    } else {
                                        z7 = z4;
                                    }
                                    if (j == 0) {
                                        th = z7 ? 1 : 0;
                                        j5 = j2;
                                        break;
                                    }
                                    i7++;
                                    if (i7 != length) {
                                        i4 = i3;
                                    } else {
                                        i7 = 0;
                                        i4 = i3;
                                    }
                                    i2 = i4 + 1;
                                    z8 = z5;
                                    simplePlainQueue3 = simplePlainQueue;
                                    z2 = z6;
                                    z4 = z7;
                                }
                                return;
                            }
                        }
                    } else {
                        z = false;
                        j = j4;
                    }
                    if (length <= i6) {
                        i6 = 0;
                    }
                    int i8 = i6;
                    int i9 = 0;
                    while (true) {
                        if (i9 >= length) {
                            j2 = j5;
                            z2 = z9;
                            break;
                        }
                        j2 = j5;
                        z2 = z9;
                        if (innerSubscriberArr[i8].id == j8) {
                            break;
                        }
                        i8++;
                        if (i8 == length) {
                            i8 = 0;
                        }
                        i9++;
                        z9 = z2;
                        j5 = j2;
                    }
                    this.lastIndex = i8;
                    this.lastId = innerSubscriberArr[i8].id;
                    i = i8;
                    z3 = z;
                    i2 = 0;
                    int i72 = i;
                    z4 = z3;
                    while (true) {
                        if (i2 < length) {
                        }
                        i2 = i4 + 1;
                        z8 = z5;
                        simplePlainQueue3 = simplePlainQueue;
                        z2 = z6;
                        z4 = z7;
                    }
                }
                if (j5 != 0 && !this.cancelled) {
                    this.upstream.request(j5);
                }
                if (th == null && (i5 = addAndGet(-i5)) == 0) {
                    return;
                }
            }
        }

        boolean checkTerminate() {
            if (this.cancelled) {
                clearScalarQueue();
                return true;
            }
            if (!this.delayErrors && this.errs.get() != null) {
                clearScalarQueue();
                Throwable ex = this.errs.terminate();
                if (ex != ExceptionHelper.TERMINATED) {
                    this.actual.onError(ex);
                }
                return true;
            }
            return false;
        }

        void clearScalarQueue() {
            SimpleQueue<U> q = this.queue;
            if (q != null) {
                q.clear();
            }
        }

        void disposeAll() {
            InnerSubscriber<?, ?>[] a = this.subscribers.get();
            if (a != CANCELLED) {
                InnerSubscriber<?, ?>[] a2 = this.subscribers.getAndSet(CANCELLED);
                InnerSubscriber<?, ?>[] a3 = a2;
                if (a3 != CANCELLED) {
                    for (InnerSubscriber<?, ?> inner : a3) {
                        inner.dispose();
                    }
                    Throwable ex = this.errs.terminate();
                    if (ex != null && ex != ExceptionHelper.TERMINATED) {
                        RxJavaPlugins.onError(ex);
                    }
                }
            }
        }

        void innerError(InnerSubscriber<T, U> inner, Throwable t) {
            if (this.errs.addThrowable(t)) {
                inner.done = true;
                if (!this.delayErrors) {
                    this.upstream.cancel();
                    for (InnerSubscriber<?, ?> a : this.subscribers.getAndSet(CANCELLED)) {
                        a.dispose();
                    }
                }
                drain();
                return;
            }
            RxJavaPlugins.onError(t);
        }
    }

    static final class InnerSubscriber<T, U> extends AtomicReference<Subscription> implements FlowableSubscriber<U>, Disposable {
        private static final long serialVersionUID = -4606175640614850599L;
        final int bufferSize;
        volatile boolean done;
        int fusionMode;
        final long id;
        final int limit;
        final MergeSubscriber<T, U> parent;
        long produced;
        volatile SimpleQueue<U> queue;

        InnerSubscriber(MergeSubscriber<T, U> parent, long id) {
            this.id = id;
            this.parent = parent;
            this.bufferSize = parent.bufferSize;
            this.limit = this.bufferSize >> 2;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.setOnce(this, s)) {
                if (s instanceof QueueSubscription) {
                    QueueSubscription<U> qs = (QueueSubscription) s;
                    int m = qs.requestFusion(7);
                    if (m == 1) {
                        this.fusionMode = m;
                        this.queue = qs;
                        this.done = true;
                        this.parent.drain();
                        return;
                    }
                    if (m == 2) {
                        this.fusionMode = m;
                        this.queue = qs;
                    }
                }
                s.request(this.bufferSize);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(U t) {
            if (this.fusionMode != 2) {
                this.parent.tryEmit(t, this);
            } else {
                this.parent.drain();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            lazySet(SubscriptionHelper.CANCELLED);
            this.parent.innerError(this, t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        void requestMore(long n) {
            if (this.fusionMode != 1) {
                long p = this.produced + n;
                if (p >= this.limit) {
                    this.produced = 0L;
                    get().request(p);
                } else {
                    this.produced = p;
                }
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return get() == SubscriptionHelper.CANCELLED;
        }
    }
}
