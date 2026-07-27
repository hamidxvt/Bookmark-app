package io.reactivex.internal.operators.flowable;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.fuseable.HasUpstreamPublisher;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes17.dex */
public final class FlowablePublish<T> extends ConnectableFlowable<T> implements HasUpstreamPublisher<T> {
    static final long CANCELLED = Long.MIN_VALUE;
    final int bufferSize;
    final AtomicReference<PublishSubscriber<T>> current;
    final Publisher<T> onSubscribe;
    final Flowable<T> source;

    public static <T> ConnectableFlowable<T> create(Flowable<T> source, int bufferSize) {
        AtomicReference<PublishSubscriber<T>> curr = new AtomicReference<>();
        Publisher<T> onSubscribe = new FlowablePublisher<>(curr, bufferSize);
        return RxJavaPlugins.onAssembly((ConnectableFlowable) new FlowablePublish(onSubscribe, source, curr, bufferSize));
    }

    private FlowablePublish(Publisher<T> onSubscribe, Flowable<T> source, AtomicReference<PublishSubscriber<T>> current, int bufferSize) {
        this.onSubscribe = onSubscribe;
        this.source = source;
        this.current = current;
        this.bufferSize = bufferSize;
    }

    @Override // io.reactivex.internal.fuseable.HasUpstreamPublisher
    public Publisher<T> source() {
        return this.source;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> s) {
        this.onSubscribe.subscribe(s);
    }

    @Override // io.reactivex.flowables.ConnectableFlowable
    public void connect(Consumer<? super Disposable> connection) {
        PublishSubscriber<T> ps;
        while (true) {
            ps = this.current.get();
            if (ps != null && !ps.isDisposed()) {
                break;
            }
            PublishSubscriber<T> u = new PublishSubscriber<>(this.current, this.bufferSize);
            if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.current, ps, u)) {
                ps = u;
                break;
            }
        }
        boolean z = false;
        if (!ps.shouldConnect.get() && ps.shouldConnect.compareAndSet(false, true)) {
            z = true;
        }
        boolean doConnect = z;
        try {
            connection.accept(ps);
            if (doConnect) {
                this.source.subscribe((FlowableSubscriber) ps);
            }
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            throw ExceptionHelper.wrapOrThrow(ex);
        }
    }

    static final class PublishSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Disposable {
        static final InnerSubscriber[] EMPTY = new InnerSubscriber[0];
        static final InnerSubscriber[] TERMINATED = new InnerSubscriber[0];
        private static final long serialVersionUID = -202316842419149694L;
        final int bufferSize;
        final AtomicReference<PublishSubscriber<T>> current;
        volatile SimpleQueue<T> queue;
        int sourceMode;
        volatile Object terminalEvent;
        final AtomicReference<Subscription> s = new AtomicReference<>();
        final AtomicReference<InnerSubscriber<T>[]> subscribers = new AtomicReference<>(EMPTY);
        final AtomicBoolean shouldConnect = new AtomicBoolean();

        PublishSubscriber(AtomicReference<PublishSubscriber<T>> current, int bufferSize) {
            this.current = current;
            this.bufferSize = bufferSize;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (this.subscribers.get() != TERMINATED) {
                InnerSubscriber[] ps = this.subscribers.getAndSet(TERMINATED);
                if (ps != TERMINATED) {
                    LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.current, this, null);
                    SubscriptionHelper.cancel(this.s);
                }
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.subscribers.get() == TERMINATED;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.setOnce(this.s, s)) {
                if (s instanceof QueueSubscription) {
                    QueueSubscription<T> qs = (QueueSubscription) s;
                    int m = qs.requestFusion(3);
                    if (m == 1) {
                        this.sourceMode = m;
                        this.queue = qs;
                        this.terminalEvent = NotificationLite.complete();
                        dispatch();
                        return;
                    }
                    if (m == 2) {
                        this.sourceMode = m;
                        this.queue = qs;
                        s.request(this.bufferSize);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.bufferSize);
                s.request(this.bufferSize);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.sourceMode == 0 && !this.queue.offer(t)) {
                onError(new MissingBackpressureException("Prefetch queue is full?!"));
            } else {
                dispatch();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable e) {
            if (this.terminalEvent == null) {
                this.terminalEvent = NotificationLite.error(e);
                dispatch();
            } else {
                RxJavaPlugins.onError(e);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.terminalEvent == null) {
                this.terminalEvent = NotificationLite.complete();
                dispatch();
            }
        }

        boolean add(InnerSubscriber<T> producer) {
            InnerSubscriber<T>[] c;
            InnerSubscriber<T>[] u;
            do {
                c = this.subscribers.get();
                if (c == TERMINATED) {
                    return false;
                }
                int len = c.length;
                u = new InnerSubscriber[len + 1];
                System.arraycopy(c, 0, u, 0, len);
                u[len] = producer;
            } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.subscribers, c, u));
            return true;
        }

        void remove(InnerSubscriber<T> producer) {
            InnerSubscriber<T>[] c;
            InnerSubscriber<T>[] u;
            do {
                c = this.subscribers.get();
                int len = c.length;
                if (len != 0) {
                    int j = -1;
                    int i = 0;
                    while (true) {
                        if (i >= len) {
                            break;
                        }
                        if (!c[i].equals(producer)) {
                            i++;
                        } else {
                            j = i;
                            break;
                        }
                    }
                    if (j < 0) {
                        return;
                    }
                    if (len == 1) {
                        u = EMPTY;
                    } else {
                        InnerSubscriber<T>[] u2 = new InnerSubscriber[len - 1];
                        System.arraycopy(c, 0, u2, 0, j);
                        System.arraycopy(c, j + 1, u2, j, (len - j) - 1);
                        u = u2;
                    }
                } else {
                    return;
                }
            } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.subscribers, c, u));
        }

        boolean checkTerminated(Object term, boolean empty) {
            int i = 0;
            if (term != null) {
                if (NotificationLite.isComplete(term)) {
                    if (empty) {
                        LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.current, this, null);
                        InnerSubscriber<T>[] andSet = this.subscribers.getAndSet(TERMINATED);
                        int length = andSet.length;
                        while (i < length) {
                            andSet[i].child.onComplete();
                            i++;
                        }
                        return true;
                    }
                } else {
                    Throwable t = NotificationLite.getError(term);
                    LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.current, this, null);
                    InnerSubscriber[] a = this.subscribers.getAndSet(TERMINATED);
                    if (a.length != 0) {
                        int length2 = a.length;
                        while (i < length2) {
                            InnerSubscriber<?> ip = a[i];
                            ip.child.onError(t);
                            i++;
                        }
                    } else {
                        RxJavaPlugins.onError(t);
                    }
                    return true;
                }
            }
            return false;
        }

        /* JADX WARN: Code restructure failed: missing block: B:64:0x0151, code lost:
        
            if (r8 <= 0) goto L79;
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x0156, code lost:
        
            if (r27.sourceMode == 1) goto L79;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x0158, code lost:
        
            r27.s.get().request(r8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x0168, code lost:
        
            if (r10 == 0) goto L102;
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x016a, code lost:
        
            if (r19 != false) goto L103;
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x0014, code lost:
        
            continue;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        void dispatch() {
            Object term;
            Object term2;
            InnerSubscriber<T>[] freshArray;
            int cancelled;
            Object term3;
            boolean empty;
            if (getAndIncrement() != 0) {
                return;
            }
            AtomicReference<InnerSubscriber<T>[]> subscribers = this.subscribers;
            InnerSubscriber<T>[] ps = subscribers.get();
            int missed = 1;
            while (true) {
                Object term4 = this.terminalEvent;
                SimpleQueue<T> q = this.queue;
                boolean empty2 = q == null || q.isEmpty();
                if (checkTerminated(term4, empty2)) {
                    return;
                }
                if (!empty2) {
                    int len = ps.length;
                    long maxRequested = Long.MAX_VALUE;
                    int cancelled2 = 0;
                    int length = ps.length;
                    int i = 0;
                    while (i < length) {
                        InnerSubscriber<T> ip = ps[i];
                        long r = ip.get();
                        if (r != Long.MIN_VALUE) {
                            empty = empty2;
                            maxRequested = Math.min(maxRequested, r - ip.emitted);
                        } else {
                            empty = empty2;
                            cancelled2++;
                        }
                        i++;
                        empty2 = empty;
                    }
                    boolean empty3 = empty2;
                    if (len == cancelled2) {
                        Object term5 = this.terminalEvent;
                        try {
                            term = q.poll();
                        } catch (Throwable ex) {
                            Exceptions.throwIfFatal(ex);
                            this.s.get().cancel();
                            term5 = NotificationLite.error(ex);
                            this.terminalEvent = term5;
                            term = null;
                        }
                        if (checkTerminated(term5, term == null)) {
                            return;
                        }
                        if (this.sourceMode != 1) {
                            this.s.get().request(1L);
                        }
                    } else {
                        int d = 0;
                        while (true) {
                            if (d >= maxRequested) {
                                break;
                            }
                            Object term6 = this.terminalEvent;
                            try {
                                term2 = q.poll();
                            } catch (Throwable ex2) {
                                Exceptions.throwIfFatal(ex2);
                                this.s.get().cancel();
                                term6 = NotificationLite.error(ex2);
                                this.terminalEvent = term6;
                                term2 = null;
                            }
                            boolean empty4 = term2 == null;
                            if (checkTerminated(term6, empty4)) {
                                return;
                            }
                            if (empty4) {
                                empty3 = empty4;
                                break;
                            }
                            Object value = NotificationLite.getValue(term2);
                            boolean subscribersChanged = false;
                            int length2 = ps.length;
                            int i2 = 0;
                            while (i2 < length2) {
                                SimpleQueue<T> q2 = q;
                                InnerSubscriber<T> ip2 = ps[i2];
                                long ipr = ip2.get();
                                if (ipr != Long.MIN_VALUE) {
                                    if (ipr == Long.MAX_VALUE) {
                                        cancelled = cancelled2;
                                        term3 = term6;
                                    } else {
                                        cancelled = cancelled2;
                                        term3 = term6;
                                        ip2.emitted++;
                                    }
                                    ip2.child.onNext(value);
                                } else {
                                    cancelled = cancelled2;
                                    term3 = term6;
                                    subscribersChanged = true;
                                }
                                i2++;
                                cancelled2 = cancelled;
                                q = q2;
                                term6 = term3;
                            }
                            SimpleQueue<T> q3 = q;
                            int cancelled3 = cancelled2;
                            d++;
                            freshArray = subscribers.get();
                            if (subscribersChanged || freshArray != ps) {
                                break;
                            }
                            cancelled2 = cancelled3;
                            q = q3;
                            empty3 = empty4;
                        }
                        ps = freshArray;
                    }
                }
                missed = addAndGet(-missed);
                if (missed != 0) {
                    InnerSubscriber<T>[] ps2 = subscribers.get();
                    ps = ps2;
                } else {
                    return;
                }
            }
        }
    }

    static final class InnerSubscriber<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = -4453897557930727610L;
        final Subscriber<? super T> child;
        long emitted;
        volatile PublishSubscriber<T> parent;

        InnerSubscriber(Subscriber<? super T> child) {
            this.child = child;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.addCancel(this, n);
                PublishSubscriber<T> p = this.parent;
                if (p != null) {
                    p.dispatch();
                }
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            PublishSubscriber<T> p;
            long r = get();
            if (r != Long.MIN_VALUE) {
                long r2 = getAndSet(Long.MIN_VALUE);
                if (r2 != Long.MIN_VALUE && (p = this.parent) != null) {
                    p.remove(this);
                    p.dispatch();
                }
            }
        }
    }

    static final class FlowablePublisher<T> implements Publisher<T> {
        private final int bufferSize;
        private final AtomicReference<PublishSubscriber<T>> curr;

        FlowablePublisher(AtomicReference<PublishSubscriber<T>> curr, int bufferSize) {
            this.curr = curr;
            this.bufferSize = bufferSize;
        }

        @Override // org.reactivestreams.Publisher
        public void subscribe(Subscriber<? super T> child) {
            PublishSubscriber<T> r;
            InnerSubscriber<T> inner = new InnerSubscriber<>(child);
            child.onSubscribe(inner);
            while (true) {
                r = this.curr.get();
                if (r == null || r.isDisposed()) {
                    PublishSubscriber<T> u = new PublishSubscriber<>(this.curr, this.bufferSize);
                    if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.curr, r, u)) {
                        r = u;
                    } else {
                        continue;
                    }
                }
                if (r.add(inner)) {
                    break;
                }
            }
            if (inner.get() == Long.MIN_VALUE) {
                r.remove(inner);
            } else {
                inner.parent = r;
            }
            r.dispatch();
        }
    }
}
