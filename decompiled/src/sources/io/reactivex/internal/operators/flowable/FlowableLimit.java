package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes17.dex */
public final class FlowableLimit<T> extends AbstractFlowableWithUpstream<T, T> {
    final long n;

    public FlowableLimit(Flowable<T> source, long n) {
        super(source);
        this.n = n;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe((FlowableSubscriber) new LimitSubscriber(s, this.n));
    }

    static final class LimitSubscriber<T> extends AtomicLong implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = 2288246011222124525L;
        final Subscriber<? super T> actual;
        long remaining;
        Subscription upstream;

        LimitSubscriber(Subscriber<? super T> actual, long remaining) {
            this.actual = actual;
            this.remaining = remaining;
            lazySet(remaining);
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                if (this.remaining == 0) {
                    s.cancel();
                    EmptySubscription.complete(this.actual);
                } else {
                    this.upstream = s;
                    this.actual.onSubscribe(this);
                }
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            long r = this.remaining;
            if (r > 0) {
                long r2 = r - 1;
                this.remaining = r2;
                this.actual.onNext(t);
                if (r2 == 0) {
                    this.upstream.cancel();
                    this.actual.onComplete();
                }
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            if (this.remaining > 0) {
                this.remaining = 0L;
                this.actual.onError(t);
            } else {
                RxJavaPlugins.onError(t);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.remaining > 0) {
                this.remaining = 0L;
                this.actual.onComplete();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            long r;
            long toRequest;
            long u;
            if (SubscriptionHelper.validate(n)) {
                do {
                    r = get();
                    if (r != 0) {
                        if (r <= n) {
                            toRequest = r;
                        } else {
                            toRequest = n;
                        }
                        u = r - toRequest;
                    } else {
                        return;
                    }
                } while (!compareAndSet(r, u));
                this.upstream.request(toRequest);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.upstream.cancel();
        }
    }
}
