package io.reactivex.internal.subscriptions;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.exceptions.ProtocolViolationException;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;

/* loaded from: classes17.dex */
public enum SubscriptionHelper implements Subscription {
    CANCELLED;

    @Override // org.reactivestreams.Subscription
    public void request(long n) {
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
    }

    public static boolean validate(Subscription current, Subscription next) {
        if (next == null) {
            RxJavaPlugins.onError(new NullPointerException("next is null"));
            return false;
        }
        if (current != null) {
            next.cancel();
            reportSubscriptionSet();
            return false;
        }
        return true;
    }

    public static void reportSubscriptionSet() {
        RxJavaPlugins.onError(new ProtocolViolationException("Subscription already set!"));
    }

    public static boolean validate(long n) {
        if (n <= 0) {
            RxJavaPlugins.onError(new IllegalArgumentException("n > 0 required but it was " + n));
            return false;
        }
        return true;
    }

    public static void reportMoreProduced(long n) {
        RxJavaPlugins.onError(new ProtocolViolationException("More produced than requested: " + n));
    }

    public static boolean isCancelled(Subscription s) {
        return s == CANCELLED;
    }

    public static boolean set(AtomicReference<Subscription> field, Subscription s) {
        Subscription current;
        do {
            current = field.get();
            if (current == CANCELLED) {
                if (s != null) {
                    s.cancel();
                    return false;
                }
                return false;
            }
        } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(field, current, s));
        if (current != null) {
            current.cancel();
            return true;
        }
        return true;
    }

    public static boolean setOnce(AtomicReference<Subscription> field, Subscription s) {
        ObjectHelper.requireNonNull(s, "s is null");
        if (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(field, null, s)) {
            s.cancel();
            if (field.get() != CANCELLED) {
                reportSubscriptionSet();
                return false;
            }
            return false;
        }
        return true;
    }

    public static boolean replace(AtomicReference<Subscription> field, Subscription s) {
        Subscription current;
        do {
            current = field.get();
            if (current == CANCELLED) {
                if (s != null) {
                    s.cancel();
                    return false;
                }
                return false;
            }
        } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(field, current, s));
        return true;
    }

    public static boolean cancel(AtomicReference<Subscription> field) {
        Subscription current = field.get();
        if (current != CANCELLED) {
            Subscription current2 = field.getAndSet(CANCELLED);
            Subscription current3 = current2;
            if (current3 != CANCELLED) {
                if (current3 != null) {
                    current3.cancel();
                    return true;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean deferredSetOnce(AtomicReference<Subscription> field, AtomicLong requested, Subscription s) {
        if (setOnce(field, s)) {
            long r = requested.getAndSet(0L);
            if (r != 0) {
                s.request(r);
                return true;
            }
            return true;
        }
        return false;
    }

    public static void deferredRequest(AtomicReference<Subscription> field, AtomicLong requested, long n) {
        Subscription s = field.get();
        if (s != null) {
            s.request(n);
            return;
        }
        if (validate(n)) {
            BackpressureHelper.add(requested, n);
            Subscription s2 = field.get();
            if (s2 != null) {
                long r = requested.getAndSet(0L);
                if (r != 0) {
                    s2.request(r);
                }
            }
        }
    }

    public static boolean setOnce(AtomicReference<Subscription> field, Subscription s, long request) {
        if (setOnce(field, s)) {
            s.request(request);
            return true;
        }
        return false;
    }
}
