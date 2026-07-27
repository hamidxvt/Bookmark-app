package io.reactivex.internal.operators.flowable;

import android.R;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes17.dex */
public final class FlowableMergeWithSingle<T> extends AbstractFlowableWithUpstream<T, T> {
    final SingleSource<? extends T> other;

    public FlowableMergeWithSingle(Flowable<T> source, SingleSource<? extends T> other) {
        super(source);
        this.other = other;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> observer) {
        MergeWithObserver<T> parent = new MergeWithObserver<>(observer);
        observer.onSubscribe(parent);
        this.source.subscribe((FlowableSubscriber) parent);
        this.other.subscribe(parent.otherObserver);
    }

    static final class MergeWithObserver<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        static final int OTHER_STATE_CONSUMED_OR_EMPTY = 2;
        static final int OTHER_STATE_HAS_VALUE = 1;
        private static final long serialVersionUID = -4592979584110982903L;
        final Subscriber<? super T> actual;
        volatile boolean cancelled;
        int consumed;
        long emitted;
        volatile boolean mainDone;
        volatile int otherState;
        volatile SimplePlainQueue<T> queue;
        T singleItem;
        final AtomicReference<Subscription> mainSubscription = new AtomicReference<>();
        final OtherObserver<T> otherObserver = new OtherObserver<>(this);
        final AtomicThrowable error = new AtomicThrowable();
        final AtomicLong requested = new AtomicLong();
        final int prefetch = Flowable.bufferSize();
        final int limit = this.prefetch - (this.prefetch >> 2);

        MergeWithObserver(Subscriber<? super T> actual) {
            this.actual = actual;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            SubscriptionHelper.setOnce(this.mainSubscription, s, this.prefetch);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (compareAndSet(0, 1)) {
                long e = this.emitted;
                if (this.requested.get() != e) {
                    SimplePlainQueue<T> q = this.queue;
                    if (q == null || q.isEmpty()) {
                        this.emitted = 1 + e;
                        this.actual.onNext(t);
                        int c = this.consumed + 1;
                        if (c == this.limit) {
                            this.consumed = 0;
                            this.mainSubscription.get().request(c);
                        } else {
                            this.consumed = c;
                        }
                    } else {
                        q.offer(t);
                    }
                } else {
                    SimplePlainQueue<T> q2 = getOrCreateQueue();
                    q2.offer(t);
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                SimplePlainQueue<T> q3 = getOrCreateQueue();
                q3.offer(t);
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable ex) {
            if (this.error.addThrowable(ex)) {
                SubscriptionHelper.cancel(this.mainSubscription);
                drain();
            } else {
                RxJavaPlugins.onError(ex);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.mainDone = true;
            drain();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            BackpressureHelper.add(this.requested, n);
            drain();
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
            SubscriptionHelper.cancel(this.mainSubscription);
            DisposableHelper.dispose(this.otherObserver);
            if (getAndIncrement() == 0) {
                this.queue = null;
                this.singleItem = null;
            }
        }

        void otherSuccess(T value) {
            if (compareAndSet(0, 1)) {
                long e = this.emitted;
                if (this.requested.get() != e) {
                    this.emitted = 1 + e;
                    this.actual.onNext(value);
                    this.otherState = 2;
                } else {
                    this.singleItem = value;
                    this.otherState = 1;
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            } else {
                this.singleItem = value;
                this.otherState = 1;
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        void otherError(Throwable ex) {
            if (this.error.addThrowable(ex)) {
                SubscriptionHelper.cancel(this.mainSubscription);
                drain();
            } else {
                RxJavaPlugins.onError(ex);
            }
        }

        SimplePlainQueue<T> getOrCreateQueue() {
            SimplePlainQueue<T> q = this.queue;
            if (q == null) {
                SimplePlainQueue<T> q2 = new SpscArrayQueue<>(Flowable.bufferSize());
                this.queue = q2;
                return q2;
            }
            return q;
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void drainLoop() {
            long j;
            Subscriber<? super T> subscriber = this.actual;
            int i = 1;
            long j2 = this.emitted;
            int i2 = this.consumed;
            int i3 = this.limit;
            do {
                long j3 = this.requested.get();
                while (j2 != j3) {
                    if (this.cancelled) {
                        this.singleItem = null;
                        this.queue = null;
                        return;
                    }
                    if (this.error.get() != null) {
                        this.singleItem = null;
                        this.queue = null;
                        subscriber.onError(this.error.terminate());
                        return;
                    }
                    int i4 = this.otherState;
                    if (i4 == 1) {
                        T t = this.singleItem;
                        this.singleItem = null;
                        this.otherState = 2;
                        subscriber.onNext(t);
                        j2++;
                    } else {
                        boolean z = this.mainDone;
                        SimplePlainQueue<T> simplePlainQueue = this.queue;
                        R.anim animVar = (Object) (simplePlainQueue != null ? simplePlainQueue.poll() : null);
                        boolean z2 = animVar == null;
                        if (z && z2 && i4 == 2) {
                            this.queue = null;
                            subscriber.onComplete();
                            return;
                        } else {
                            if (z2) {
                                break;
                            }
                            subscriber.onNext(animVar);
                            long j4 = j2 + 1;
                            i2++;
                            if (i2 != i3) {
                                j = j4;
                            } else {
                                i2 = 0;
                                j = j4;
                                this.mainSubscription.get().request(i3);
                            }
                            j2 = j;
                        }
                    }
                }
                if (j2 == j3) {
                    if (this.cancelled) {
                        this.singleItem = null;
                        this.queue = null;
                        return;
                    }
                    if (this.error.get() != null) {
                        this.singleItem = null;
                        this.queue = null;
                        subscriber.onError(this.error.terminate());
                        return;
                    }
                    boolean z3 = this.mainDone;
                    SimplePlainQueue<T> simplePlainQueue2 = this.queue;
                    boolean z4 = simplePlainQueue2 == null || simplePlainQueue2.isEmpty();
                    if (z3 && z4 && this.otherState == 2) {
                        this.queue = null;
                        subscriber.onComplete();
                        return;
                    }
                }
                this.emitted = j2;
                this.consumed = i2;
                i = addAndGet(-i);
            } while (i != 0);
        }

        static final class OtherObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T> {
            private static final long serialVersionUID = -2935427570954647017L;
            final MergeWithObserver<T> parent;

            OtherObserver(MergeWithObserver<T> parent) {
                this.parent = parent;
            }

            @Override // io.reactivex.SingleObserver
            public void onSubscribe(Disposable d) {
                DisposableHelper.setOnce(this, d);
            }

            @Override // io.reactivex.SingleObserver
            public void onSuccess(T t) {
                this.parent.otherSuccess(t);
            }

            @Override // io.reactivex.SingleObserver
            public void onError(Throwable e) {
                this.parent.otherError(e);
            }
        }
    }
}
