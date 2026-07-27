package androidx.work.impl;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import androidx.core.content.ContextCompat;
import androidx.work.Configuration;
import androidx.work.ForegroundInfo;
import androidx.work.Logger;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkerWrapper;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.foreground.SystemForegroundDispatcher;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.WakeLocks;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes.dex */
public class Processor implements ExecutionListener, ForegroundProcessor {
    private static final String FOREGROUND_WAKELOCK_TAG = "ProcessorForegroundLck";
    private static final String TAG = Logger.tagWithPrefix("Processor");
    private Context mAppContext;
    private Configuration mConfiguration;
    private List<Scheduler> mSchedulers;
    private WorkDatabase mWorkDatabase;
    private TaskExecutor mWorkTaskExecutor;
    private Map<String, WorkerWrapper> mEnqueuedWorkMap = new HashMap();
    private Map<String, WorkerWrapper> mForegroundWorkMap = new HashMap();
    private Set<String> mCancelledIds = new HashSet();
    private final List<ExecutionListener> mOuterListeners = new ArrayList();
    private PowerManager.WakeLock mForegroundLock = null;
    private final Object mLock = new Object();
    private Map<String, Set<StartStopToken>> mWorkRuns = new HashMap();

    public Processor(Context appContext, Configuration configuration, TaskExecutor workTaskExecutor, WorkDatabase workDatabase, List<Scheduler> schedulers) {
        this.mAppContext = appContext;
        this.mConfiguration = configuration;
        this.mWorkTaskExecutor = workTaskExecutor;
        this.mWorkDatabase = workDatabase;
        this.mSchedulers = schedulers;
    }

    public boolean startWork(StartStopToken id) {
        return startWork(id, null);
    }

    public boolean startWork(StartStopToken startStopToken, WorkerParameters.RuntimeExtras runtimeExtras) {
        WorkGenerationalId id = startStopToken.getId();
        final String workSpecId = id.getWorkSpecId();
        final ArrayList<String> tags = new ArrayList<>();
        WorkSpec workSpec = (WorkSpec) this.mWorkDatabase.runInTransaction(new Callable() { // from class: androidx.work.impl.Processor$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Processor.this.m181lambda$startWork$0$androidxworkimplProcessor(tags, workSpecId);
            }
        });
        if (workSpec == null) {
            Logger.get().warning(TAG, "Didn't find WorkSpec for id " + id);
            runOnExecuted(id, false);
            return false;
        }
        synchronized (this.mLock) {
            try {
                try {
                    if (isEnqueued(workSpecId)) {
                        Set<StartStopToken> tokens = this.mWorkRuns.get(workSpecId);
                        StartStopToken previousRun = tokens.iterator().next();
                        int previousRunGeneration = previousRun.getId().getGeneration();
                        if (previousRunGeneration == id.getGeneration()) {
                            tokens.add(startStopToken);
                            Logger.get().debug(TAG, "Work " + id + " is already enqueued for processing");
                        } else {
                            runOnExecuted(id, false);
                        }
                        return false;
                    }
                    if (workSpec.getGeneration() != id.getGeneration()) {
                        runOnExecuted(id, false);
                        return false;
                    }
                    WorkerWrapper workWrapper = new WorkerWrapper.Builder(this.mAppContext, this.mConfiguration, this.mWorkTaskExecutor, this, this.mWorkDatabase, workSpec, tags).withSchedulers(this.mSchedulers).withRuntimeExtras(runtimeExtras).build();
                    ListenableFuture<Boolean> future = workWrapper.getFuture();
                    future.addListener(new FutureListener(this, startStopToken.getId(), future), this.mWorkTaskExecutor.getMainThreadExecutor());
                    this.mEnqueuedWorkMap.put(workSpecId, workWrapper);
                    HashSet<StartStopToken> set = new HashSet<>();
                    set.add(startStopToken);
                    this.mWorkRuns.put(workSpecId, set);
                    this.mWorkTaskExecutor.getSerialTaskExecutor().execute(workWrapper);
                    Logger.get().debug(TAG, getClass().getSimpleName() + ": processing " + id);
                    return true;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    /* renamed from: lambda$startWork$0$androidx-work-impl-Processor, reason: not valid java name */
    /* synthetic */ WorkSpec m181lambda$startWork$0$androidxworkimplProcessor(ArrayList tags, String workSpecId) throws Exception {
        tags.addAll(this.mWorkDatabase.workTagDao().getTagsForWorkSpecId(workSpecId));
        return this.mWorkDatabase.workSpecDao().getWorkSpec(workSpecId);
    }

    @Override // androidx.work.impl.foreground.ForegroundProcessor
    public void startForeground(String workSpecId, ForegroundInfo foregroundInfo) {
        synchronized (this.mLock) {
            Logger.get().info(TAG, "Moving WorkSpec (" + workSpecId + ") to the foreground");
            WorkerWrapper wrapper = this.mEnqueuedWorkMap.remove(workSpecId);
            if (wrapper != null) {
                if (this.mForegroundLock == null) {
                    this.mForegroundLock = WakeLocks.newWakeLock(this.mAppContext, FOREGROUND_WAKELOCK_TAG);
                    this.mForegroundLock.acquire();
                }
                this.mForegroundWorkMap.put(workSpecId, wrapper);
                Intent intent = SystemForegroundDispatcher.createStartForegroundIntent(this.mAppContext, wrapper.getWorkGenerationalId(), foregroundInfo);
                ContextCompat.startForegroundService(this.mAppContext, intent);
            }
        }
    }

    public boolean stopForegroundWork(StartStopToken token) {
        WorkerWrapper wrapper;
        String id = token.getId().getWorkSpecId();
        synchronized (this.mLock) {
            Logger.get().debug(TAG, "Processor stopping foreground work " + id);
            wrapper = this.mForegroundWorkMap.remove(id);
            if (wrapper != null) {
                this.mWorkRuns.remove(id);
            }
        }
        return interrupt(id, wrapper);
    }

    public boolean stopWork(StartStopToken runId) {
        String id = runId.getId().getWorkSpecId();
        synchronized (this.mLock) {
            WorkerWrapper wrapper = this.mEnqueuedWorkMap.remove(id);
            if (wrapper == null) {
                Logger.get().debug(TAG, "WorkerWrapper could not be found for " + id);
                return false;
            }
            Set<StartStopToken> runs = this.mWorkRuns.get(id);
            if (runs != null && runs.contains(runId)) {
                Logger.get().debug(TAG, "Processor stopping background work " + id);
                this.mWorkRuns.remove(id);
                return interrupt(id, wrapper);
            }
            return false;
        }
    }

    public boolean stopAndCancelWork(String id) {
        WorkerWrapper wrapper;
        boolean isForegroundWork;
        synchronized (this.mLock) {
            Logger.get().debug(TAG, "Processor cancelling " + id);
            this.mCancelledIds.add(id);
            wrapper = this.mForegroundWorkMap.remove(id);
            isForegroundWork = wrapper != null;
            if (wrapper == null) {
                wrapper = this.mEnqueuedWorkMap.remove(id);
            }
            if (wrapper != null) {
                this.mWorkRuns.remove(id);
            }
        }
        boolean interrupted = interrupt(id, wrapper);
        if (isForegroundWork) {
            stopForegroundService();
        }
        return interrupted;
    }

    @Override // androidx.work.impl.foreground.ForegroundProcessor
    public void stopForeground(String workSpecId) {
        synchronized (this.mLock) {
            this.mForegroundWorkMap.remove(workSpecId);
            stopForegroundService();
        }
    }

    public boolean isCancelled(String id) {
        boolean contains;
        synchronized (this.mLock) {
            contains = this.mCancelledIds.contains(id);
        }
        return contains;
    }

    public boolean hasWork() {
        boolean z;
        synchronized (this.mLock) {
            z = (this.mEnqueuedWorkMap.isEmpty() && this.mForegroundWorkMap.isEmpty()) ? false : true;
        }
        return z;
    }

    public boolean isEnqueued(String workSpecId) {
        boolean z;
        synchronized (this.mLock) {
            z = this.mEnqueuedWorkMap.containsKey(workSpecId) || this.mForegroundWorkMap.containsKey(workSpecId);
        }
        return z;
    }

    @Override // androidx.work.impl.foreground.ForegroundProcessor
    public boolean isEnqueuedInForeground(String workSpecId) {
        boolean containsKey;
        synchronized (this.mLock) {
            containsKey = this.mForegroundWorkMap.containsKey(workSpecId);
        }
        return containsKey;
    }

    public void addExecutionListener(ExecutionListener executionListener) {
        synchronized (this.mLock) {
            this.mOuterListeners.add(executionListener);
        }
    }

    public void removeExecutionListener(ExecutionListener executionListener) {
        synchronized (this.mLock) {
            this.mOuterListeners.remove(executionListener);
        }
    }

    @Override // androidx.work.impl.ExecutionListener
    /* renamed from: onExecuted, reason: merged with bridge method [inline-methods] */
    public void m180lambda$runOnExecuted$1$androidxworkimplProcessor(final WorkGenerationalId id, boolean needsReschedule) {
        synchronized (this.mLock) {
            WorkerWrapper workerWrapper = this.mEnqueuedWorkMap.get(id.getWorkSpecId());
            if (workerWrapper != null && id.equals(workerWrapper.getWorkGenerationalId())) {
                this.mEnqueuedWorkMap.remove(id.getWorkSpecId());
            }
            Logger.get().debug(TAG, getClass().getSimpleName() + StringUtils.SPACE + id.getWorkSpecId() + " executed; reschedule = " + needsReschedule);
            for (ExecutionListener executionListener : this.mOuterListeners) {
                executionListener.m180lambda$runOnExecuted$1$androidxworkimplProcessor(id, needsReschedule);
            }
        }
    }

    public WorkSpec getRunningWorkSpec(String workSpecId) {
        synchronized (this.mLock) {
            WorkerWrapper workerWrapper = this.mForegroundWorkMap.get(workSpecId);
            if (workerWrapper == null) {
                workerWrapper = this.mEnqueuedWorkMap.get(workSpecId);
            }
            if (workerWrapper == null) {
                return null;
            }
            return workerWrapper.getWorkSpec();
        }
    }

    private void runOnExecuted(final WorkGenerationalId id, final boolean needsReschedule) {
        this.mWorkTaskExecutor.getMainThreadExecutor().execute(new Runnable() { // from class: androidx.work.impl.Processor$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Processor.this.m180lambda$runOnExecuted$1$androidxworkimplProcessor(id, needsReschedule);
            }
        });
    }

    private void stopForegroundService() {
        synchronized (this.mLock) {
            boolean hasForegroundWork = !this.mForegroundWorkMap.isEmpty();
            if (!hasForegroundWork) {
                Intent intent = SystemForegroundDispatcher.createStopForegroundIntent(this.mAppContext);
                try {
                    this.mAppContext.startService(intent);
                } catch (Throwable throwable) {
                    Logger.get().error(TAG, "Unable to stop foreground service", throwable);
                }
                if (this.mForegroundLock != null) {
                    this.mForegroundLock.release();
                    this.mForegroundLock = null;
                }
            }
        }
    }

    private static boolean interrupt(String id, WorkerWrapper wrapper) {
        if (wrapper != null) {
            wrapper.interrupt();
            Logger.get().debug(TAG, "WorkerWrapper interrupted for " + id);
            return true;
        }
        Logger.get().debug(TAG, "WorkerWrapper could not be found for " + id);
        return false;
    }

    private static class FutureListener implements Runnable {
        private ExecutionListener mExecutionListener;
        private ListenableFuture<Boolean> mFuture;
        private final WorkGenerationalId mWorkGenerationalId;

        FutureListener(ExecutionListener executionListener, WorkGenerationalId workGenerationalId, ListenableFuture<Boolean> future) {
            this.mExecutionListener = executionListener;
            this.mWorkGenerationalId = workGenerationalId;
            this.mFuture = future;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean needsReschedule;
            try {
                needsReschedule = this.mFuture.get().booleanValue();
            } catch (InterruptedException | ExecutionException e) {
                needsReschedule = true;
            }
            this.mExecutionListener.m180lambda$runOnExecuted$1$androidxworkimplProcessor(this.mWorkGenerationalId, needsReschedule);
        }
    }
}
