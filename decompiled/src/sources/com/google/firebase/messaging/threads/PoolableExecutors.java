package com.google.firebase.messaging.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes16.dex */
public class PoolableExecutors {
    private static final ExecutorFactory DEFAULT_INSTANCE = new DefaultExecutorFactory();
    private static volatile ExecutorFactory instance = DEFAULT_INSTANCE;

    private PoolableExecutors() {
    }

    public static ExecutorFactory factory() {
        return instance;
    }

    private static class DefaultExecutorFactory implements ExecutorFactory {
        private static final long CORE_THREAD_TIMEOUT_SECS = 60;

        private DefaultExecutorFactory() {
        }

        @Override // com.google.firebase.messaging.threads.ExecutorFactory
        public ExecutorService newThreadPool(ThreadPriority priority) {
            return Executors.unconfigurableExecutorService(Executors.newCachedThreadPool());
        }

        @Override // com.google.firebase.messaging.threads.ExecutorFactory
        public ExecutorService newThreadPool(ThreadFactory threadFactory, ThreadPriority priority) {
            return Executors.unconfigurableExecutorService(Executors.newCachedThreadPool(threadFactory));
        }

        @Override // com.google.firebase.messaging.threads.ExecutorFactory
        public ExecutorService newThreadPool(int maxConcurrency, ThreadPriority priority) {
            return newThreadPool(maxConcurrency, Executors.defaultThreadFactory(), priority);
        }

        @Override // com.google.firebase.messaging.threads.ExecutorFactory
        public ExecutorService newThreadPool(int maxConcurrency, ThreadFactory threadFactory, ThreadPriority priority) {
            ThreadPoolExecutor executor = new ThreadPoolExecutor(maxConcurrency, maxConcurrency, CORE_THREAD_TIMEOUT_SECS, TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory);
            executor.allowCoreThreadTimeOut(true);
            return Executors.unconfigurableExecutorService(executor);
        }

        @Override // com.google.firebase.messaging.threads.ExecutorFactory
        public ExecutorService newSingleThreadExecutor(ThreadPriority priority) {
            return newThreadPool(1, priority);
        }

        @Override // com.google.firebase.messaging.threads.ExecutorFactory
        public ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory, ThreadPriority priority) {
            return newThreadPool(1, threadFactory, priority);
        }

        @Override // com.google.firebase.messaging.threads.ExecutorFactory
        public ScheduledExecutorService newScheduledThreadPool(int maxConcurrency, ThreadPriority priority) {
            return Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(maxConcurrency));
        }

        @Override // com.google.firebase.messaging.threads.ExecutorFactory
        public ScheduledExecutorService newScheduledThreadPool(int maxConcurrency, ThreadFactory threadFactory, ThreadPriority priority) {
            return Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(maxConcurrency, threadFactory));
        }

        @Override // com.google.firebase.messaging.threads.ExecutorFactory
        public void executeOneOff(String moduleName, String name, ThreadPriority priority, Runnable runnable) {
            new Thread(runnable, name).start();
        }

        @Override // com.google.firebase.messaging.threads.ExecutorFactory
        public Future<?> submitOneOff(String moduleName, String name, ThreadPriority priority, Runnable runnable) {
            FutureTask<?> task = new FutureTask<>(runnable, null);
            new Thread(task, name).start();
            return task;
        }
    }

    static void installExecutorFactory(ExecutorFactory instance2) {
        if (instance != DEFAULT_INSTANCE) {
            throw new IllegalStateException("Trying to install an ExecutorFactory twice!");
        }
        instance = instance2;
    }
}
