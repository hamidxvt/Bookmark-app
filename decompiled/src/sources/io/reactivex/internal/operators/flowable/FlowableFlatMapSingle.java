package io.reactivex.internal.operators.flowable;

import android.R;
import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
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
public final class FlowableFlatMapSingle<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final boolean delayErrors;
    final Function<? super T, ? extends SingleSource<? extends R>> mapper;
    final int maxConcurrency;

    public FlowableFlatMapSingle(Flowable<T> source, Function<? super T, ? extends SingleSource<? extends R>> mapper, boolean delayError, int maxConcurrency) {
        super(source);
        this.mapper = mapper;
        this.delayErrors = delayError;
        this.maxConcurrency = maxConcurrency;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super R> s) {
        this.source.subscribe((FlowableSubscriber) new FlatMapSingleSubscriber(s, this.mapper, this.delayErrors, this.maxConcurrency));
    }

    static final class FlatMapSingleSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = 8600231336733376951L;
        final Subscriber<? super R> actual;
        volatile boolean cancelled;
        final boolean delayErrors;
        final Function<? super T, ? extends SingleSource<? extends R>> mapper;
        final int maxConcurrency;
        Subscription s;
        final AtomicLong requested = new AtomicLong();
        final CompositeDisposable set = new CompositeDisposable();
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicInteger active = new AtomicInteger(1);
        final AtomicReference<SpscLinkedArrayQueue<R>> queue = new AtomicReference<>();

        FlatMapSingleSubscriber(Subscriber<? super R> actual, Function<? super T, ? extends SingleSource<? extends R>> mapper, boolean delayErrors, int maxConcurrency) {
            this.actual = actual;
            this.mapper = mapper;
            this.delayErrors = delayErrors;
            this.maxConcurrency = maxConcurrency;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.s, s)) {
                this.s = s;
                this.actual.onSubscribe(this);
                int m = this.maxConcurrency;
                if (m == Integer.MAX_VALUE) {
                    s.request(Long.MAX_VALUE);
                } else {
                    s.request(this.maxConcurrency);
                }
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            try {
                SingleSource<? extends R> ms = (SingleSource) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null SingleSource");
                this.active.getAndIncrement();
                FlatMapSingleSubscriber<T, R>.InnerObserver inner = new InnerObserver();
                if (!this.cancelled && this.set.add(inner)) {
                    ms.subscribe(inner);
                }
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.s.cancel();
                onError(ex);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            this.active.decrementAndGet();
            if (this.errors.addThrowable(t)) {
                if (!this.delayErrors) {
                    this.set.dispose();
                }
                drain();
                return;
            }
            RxJavaPlugins.onError(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.active.decrementAndGet();
            drain();
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
            this.s.cancel();
            this.set.dispose();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        void innerSuccess(FlatMapSingleSubscriber<T, R>.InnerObserver inner, R value) {
            this.set.delete(inner);
            if (get() == 0) {
                if (compareAndSet(0, 1)) {
                    boolean d = this.active.decrementAndGet() == 0;
                    if (this.requested.get() != 0) {
                        this.actual.onNext(value);
                        SpscLinkedArrayQueue<R> q = this.queue.get();
                        if (d && (q == null || q.isEmpty())) {
                            Throwable ex = this.errors.terminate();
                            if (ex != null) {
                                this.actual.onError(ex);
                                return;
                            } else {
                                this.actual.onComplete();
                                return;
                            }
                        }
                        BackpressureHelper.produced(this.requested, 1L);
                        if (this.maxConcurrency != Integer.MAX_VALUE) {
                            this.s.request(1L);
                        }
                    } else {
                        SpscLinkedArrayQueue<R> q2 = getOrCreateQueue();
                        synchronized (q2) {
                            q2.offer(value);
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                    drainLoop();
                }
            }
            SpscLinkedArrayQueue<R> q3 = getOrCreateQueue();
            synchronized (q3) {
                q3.offer(value);
            }
            this.active.decrementAndGet();
            if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        SpscLinkedArrayQueue<R> getOrCreateQueue() {
            SpscLinkedArrayQueue<R> current;
            do {
                SpscLinkedArrayQueue<R> current2 = this.queue.get();
                if (current2 != null) {
                    return current2;
                }
                current = new SpscLinkedArrayQueue<>(Flowable.bufferSize());
            } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.queue, null, current));
            return current;
        }

        void innerError(FlatMapSingleSubscriber<T, R>.InnerObserver inner, Throwable e) {
            this.set.delete(inner);
            if (this.errors.addThrowable(e)) {
                if (!this.delayErrors) {
                    this.s.cancel();
                    this.set.dispose();
                } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                    this.s.request(1L);
                }
                this.active.decrementAndGet();
                drain();
                return;
            }
            RxJavaPlugins.onError(e);
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void clear() {
            SpscLinkedArrayQueue<R> q = this.queue.get();
            if (q != null) {
                q.clear();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:36:0x0074, code lost:
        
            if (r6 != r4) goto L66;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0078, code lost:
        
            if (r14.cancelled == false) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0080, code lost:
        
            if (r14.delayErrors != false) goto L49;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x008a, code lost:
        
            if (r14.errors.get() == null) goto L49;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x008c, code lost:
        
            r8 = r14.errors.terminate();
            clear();
            r1.onError(r8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x0098, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x009d, code lost:
        
            if (r2.get() != 0) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x009f, code lost:
        
            r8 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00a2, code lost:
        
            r11 = r3.get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x00a8, code lost:
        
            if (r11 == null) goto L59;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00ae, code lost:
        
            if (r11.isEmpty() == false) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00b1, code lost:
        
            r9 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00b2, code lost:
        
            if (r8 == false) goto L66;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00b4, code lost:
        
            if (r9 == false) goto L66;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x00b6, code lost:
        
            r10 = r14.errors.terminate();
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00bc, code lost:
        
            if (r10 == null) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x00be, code lost:
        
            r1.onError(r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:?, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x00c2, code lost:
        
            r1.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x00c5, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x00a1, code lost:
        
            r8 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x007a, code lost:
        
            clear();
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x007d, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x00ca, code lost:
        
            if (r6 == 0) goto L71;
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x00cc, code lost:
        
            io.reactivex.internal.util.BackpressureHelper.produced(r14.requested, r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x00d6, code lost:
        
            if (r14.maxConcurrency == Integer.MAX_VALUE) goto L71;
         */
        /* JADX WARN: Code restructure failed: missing block: B:71:0x00d8, code lost:
        
            r14.s.request(r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:72:0x00dd, code lost:
        
            r0 = addAndGet(-r0);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        void drainLoop() {
            int i = 1;
            Subscriber<? super R> subscriber = this.actual;
            AtomicInteger atomicInteger = this.active;
            AtomicReference<SpscLinkedArrayQueue<R>> atomicReference = this.queue;
            do {
                long j = this.requested.get();
                long j2 = 0;
                while (true) {
                    boolean z = true;
                    if (j2 == j) {
                        break;
                    }
                    if (this.cancelled) {
                        clear();
                        return;
                    }
                    if (!this.delayErrors && this.errors.get() != null) {
                        Throwable terminate = this.errors.terminate();
                        clear();
                        subscriber.onError(terminate);
                        return;
                    }
                    boolean z2 = atomicInteger.get() == 0;
                    SpscLinkedArrayQueue<R> spscLinkedArrayQueue = atomicReference.get();
                    R.animator poll = spscLinkedArrayQueue != null ? spscLinkedArrayQueue.poll() : null;
                    boolean z3 = poll == null;
                    if (z2 && z3) {
                        Throwable terminate2 = this.errors.terminate();
                        if (terminate2 != null) {
                            subscriber.onError(terminate2);
                            return;
                        } else {
                            subscriber.onComplete();
                            return;
                        }
                    }
                    if (z3) {
                        break;
                    }
                    subscriber.onNext(poll);
                    j2++;
                }
            } while (i != 0);
        }

        final class InnerObserver extends AtomicReference<Disposable> implements SingleObserver<R>, Disposable {
            private static final long serialVersionUID = -502562646270949838L;

            InnerObserver() {
            }

            @Override // io.reactivex.SingleObserver
            public void onSubscribe(Disposable d) {
                DisposableHelper.setOnce(this, d);
            }

            @Override // io.reactivex.SingleObserver
            public void onSuccess(R value) {
                FlatMapSingleSubscriber.this.innerSuccess(this, value);
            }

            @Override // io.reactivex.SingleObserver
            public void onError(Throwable e) {
                FlatMapSingleSubscriber.this.innerError(this, e);
            }

            @Override // io.reactivex.disposables.Disposable
            public boolean isDisposed() {
                return DisposableHelper.isDisposed(get());
            }

            @Override // io.reactivex.disposables.Disposable
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
