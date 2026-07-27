package com.airbnb.lottie;

import android.os.Handler;
import android.os.Looper;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.LottieThreadFactory;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/* loaded from: classes.dex */
public class LottieTask<T> {
    public static Executor EXECUTOR = Executors.newCachedThreadPool(new LottieThreadFactory());
    private final Set<LottieListener<Throwable>> failureListeners;
    private final Handler handler;
    private volatile LottieResult<T> result;
    private final Set<LottieListener<T>> successListeners;

    public LottieTask(Callable<LottieResult<T>> runnable) {
        this(runnable, false);
    }

    public LottieTask(T result) {
        this.successListeners = new LinkedHashSet(1);
        this.failureListeners = new LinkedHashSet(1);
        this.handler = new Handler(Looper.getMainLooper());
        this.result = null;
        setResult(new LottieResult<>(result));
    }

    LottieTask(Callable<LottieResult<T>> runnable, boolean runNow) {
        this.successListeners = new LinkedHashSet(1);
        this.failureListeners = new LinkedHashSet(1);
        this.handler = new Handler(Looper.getMainLooper());
        this.result = null;
        if (runNow) {
            try {
                setResult(runnable.call());
                return;
            } catch (Throwable e) {
                setResult(new LottieResult<>(e));
                return;
            }
        }
        EXECUTOR.execute(new LottieFutureTask(this, runnable));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResult(LottieResult<T> result) {
        if (this.result != null) {
            throw new IllegalStateException("A task may only be set once.");
        }
        this.result = result;
        notifyListeners();
    }

    public synchronized LottieTask<T> addListener(LottieListener<T> listener) {
        LottieResult<T> result = this.result;
        if (result != null && result.getValue() != null) {
            listener.onResult(result.getValue());
        }
        this.successListeners.add(listener);
        return this;
    }

    public synchronized LottieTask<T> removeListener(LottieListener<T> listener) {
        this.successListeners.remove(listener);
        return this;
    }

    public synchronized LottieTask<T> addFailureListener(LottieListener<Throwable> listener) {
        LottieResult<T> result = this.result;
        if (result != null && result.getException() != null) {
            listener.onResult(result.getException());
        }
        this.failureListeners.add(listener);
        return this;
    }

    public synchronized LottieTask<T> removeFailureListener(LottieListener<Throwable> listener) {
        this.failureListeners.remove(listener);
        return this;
    }

    public LottieResult<T> getResult() {
        return this.result;
    }

    private void notifyListeners() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            notifyListenersInternal();
        } else {
            this.handler.post(new Runnable() { // from class: com.airbnb.lottie.LottieTask$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LottieTask.this.notifyListenersInternal();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyListenersInternal() {
        LottieResult<T> result = this.result;
        if (result == null) {
            return;
        }
        if (result.getValue() != null) {
            notifySuccessListeners(result.getValue());
        } else {
            notifyFailureListeners(result.getException());
        }
    }

    private synchronized void notifySuccessListeners(T value) {
        List<LottieListener<T>> listenersCopy = new ArrayList<>(this.successListeners);
        for (LottieListener<T> l : listenersCopy) {
            l.onResult(value);
        }
    }

    private synchronized void notifyFailureListeners(Throwable e) {
        List<LottieListener<Throwable>> listenersCopy = new ArrayList<>(this.failureListeners);
        if (listenersCopy.isEmpty()) {
            Logger.warning("Lottie encountered an error but no failure listener was added:", e);
            return;
        }
        for (LottieListener<Throwable> l : listenersCopy) {
            l.onResult(e);
        }
    }

    private static class LottieFutureTask<T> extends FutureTask<LottieResult<T>> {
        private LottieTask<T> lottieTask;

        LottieFutureTask(LottieTask<T> task, Callable<LottieResult<T>> callable) {
            super(callable);
            this.lottieTask = task;
        }

        @Override // java.util.concurrent.FutureTask
        protected void done() {
            try {
                if (!isCancelled()) {
                    try {
                        this.lottieTask.setResult(get());
                    } catch (InterruptedException | ExecutionException e) {
                        this.lottieTask.setResult(new LottieResult((Throwable) e));
                    }
                }
            } finally {
                this.lottieTask = null;
            }
        }
    }
}
