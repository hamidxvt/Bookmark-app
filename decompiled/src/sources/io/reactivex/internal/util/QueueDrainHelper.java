package io.reactivex.internal.util;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes17.dex */
public final class QueueDrainHelper {
    static final long COMPLETED_MASK = Long.MIN_VALUE;
    static final long REQUESTED_MASK = Long.MAX_VALUE;

    private QueueDrainHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static <T, U> void drainMaxLoop(SimplePlainQueue<T> q, Subscriber<? super U> a, boolean delayError, Disposable dispose, QueueDrain<T, U> qd) {
        int missed = 1;
        while (true) {
            boolean d = qd.done();
            T v = q.poll();
            boolean empty = v == null;
            if (checkTerminated(d, empty, a, delayError, q, qd)) {
                if (dispose != null) {
                    dispose.dispose();
                    return;
                }
                return;
            } else if (!empty) {
                long r = qd.requested();
                if (r != 0) {
                    if (qd.accept(a, v) && r != Long.MAX_VALUE) {
                        qd.produced(1L);
                    }
                } else {
                    q.clear();
                    if (dispose != null) {
                        dispose.dispose();
                    }
                    a.onError(new MissingBackpressureException("Could not emit value due to lack of requests."));
                    return;
                }
            } else {
                missed = qd.leave(-missed);
                if (missed == 0) {
                    return;
                }
            }
        }
    }

    public static <T, U> boolean checkTerminated(boolean d, boolean empty, Subscriber<?> s, boolean delayError, SimpleQueue<?> q, QueueDrain<T, U> qd) {
        if (qd.cancelled()) {
            q.clear();
            return true;
        }
        if (d) {
            if (delayError) {
                if (empty) {
                    Throwable err = qd.error();
                    if (err != null) {
                        s.onError(err);
                    } else {
                        s.onComplete();
                    }
                    return true;
                }
                return false;
            }
            Throwable err2 = qd.error();
            if (err2 != null) {
                q.clear();
                s.onError(err2);
                return true;
            }
            if (empty) {
                s.onComplete();
                return true;
            }
            return false;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0034, code lost:
    
        r0 = r15.leave(-r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0039, code lost:
    
        if (r0 != 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003c, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static <T, U> void drainLoop(SimplePlainQueue<T> q, Observer<? super U> a, boolean delayError, Disposable dispose, ObservableQueueDrain<T, U> qd) {
        int missed = 1;
        while (!checkTerminated(qd.done(), q.isEmpty(), a, delayError, q, dispose, qd)) {
            while (true) {
                boolean d = qd.done();
                T v = q.poll();
                boolean empty = v == null;
                if (checkTerminated(d, empty, a, delayError, q, dispose, qd)) {
                    return;
                }
                if (empty) {
                    break;
                } else {
                    qd.accept(a, v);
                }
            }
        }
    }

    public static <T, U> boolean checkTerminated(boolean d, boolean empty, Observer<?> s, boolean delayError, SimpleQueue<?> q, Disposable disposable, ObservableQueueDrain<T, U> qd) {
        if (qd.cancelled()) {
            q.clear();
            disposable.dispose();
            return true;
        }
        if (d) {
            if (delayError) {
                if (empty) {
                    if (disposable != null) {
                        disposable.dispose();
                    }
                    Throwable err = qd.error();
                    if (err != null) {
                        s.onError(err);
                    } else {
                        s.onComplete();
                    }
                    return true;
                }
                return false;
            }
            Throwable err2 = qd.error();
            if (err2 != null) {
                q.clear();
                if (disposable != null) {
                    disposable.dispose();
                }
                s.onError(err2);
                return true;
            }
            if (empty) {
                if (disposable != null) {
                    disposable.dispose();
                }
                s.onComplete();
                return true;
            }
            return false;
        }
        return false;
    }

    public static <T> SimpleQueue<T> createQueue(int capacityHint) {
        if (capacityHint < 0) {
            return new SpscLinkedArrayQueue(-capacityHint);
        }
        return new SpscArrayQueue(capacityHint);
    }

    public static void request(Subscription s, int prefetch) {
        s.request(prefetch < 0 ? Long.MAX_VALUE : prefetch);
    }

    public static <T> boolean postCompleteRequest(long n, Subscriber<? super T> actual, Queue<T> queue, AtomicLong state, BooleanSupplier isCancelled) {
        long r;
        long u;
        do {
            r = state.get();
            long r0 = Long.MAX_VALUE & r;
            u = (r & Long.MIN_VALUE) | BackpressureHelper.addCap(r0, n);
        } while (!state.compareAndSet(r, u));
        if (r == Long.MIN_VALUE) {
            postCompleteDrain(n | Long.MIN_VALUE, actual, queue, state, isCancelled);
            return true;
        }
        return false;
    }

    static boolean isCancelled(BooleanSupplier cancelled) {
        try {
            return cancelled.getAsBoolean();
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            return true;
        }
    }

    static <T> boolean postCompleteDrain(long j, Subscriber<? super T> subscriber, Queue<T> queue, AtomicLong atomicLong, BooleanSupplier booleanSupplier) {
        long j2 = j & Long.MIN_VALUE;
        while (true) {
            if (j2 != j) {
                if (isCancelled(booleanSupplier)) {
                    return true;
                }
                T poll = queue.poll();
                if (poll == null) {
                    subscriber.onComplete();
                    return true;
                }
                subscriber.onNext(poll);
                j2++;
            } else {
                if (isCancelled(booleanSupplier)) {
                    return true;
                }
                if (queue.isEmpty()) {
                    subscriber.onComplete();
                    return true;
                }
                j = atomicLong.get();
                if (j == j2) {
                    j = atomicLong.addAndGet(-(j2 & Long.MAX_VALUE));
                    if ((Long.MAX_VALUE & j) == 0) {
                        return false;
                    }
                    j2 = j & Long.MIN_VALUE;
                } else {
                    continue;
                }
            }
        }
    }

    public static <T> void postComplete(Subscriber<? super T> actual, Queue<T> queue, AtomicLong state, BooleanSupplier isCancelled) {
        long r;
        long u;
        if (queue.isEmpty()) {
            actual.onComplete();
            return;
        }
        if (postCompleteDrain(state.get(), actual, queue, state, isCancelled)) {
            return;
        }
        do {
            r = state.get();
            if ((r & Long.MIN_VALUE) != 0) {
                return;
            } else {
                u = Long.MIN_VALUE | r;
            }
        } while (!state.compareAndSet(r, u));
        if (r != 0) {
            postCompleteDrain(u, actual, queue, state, isCancelled);
        }
    }
}
