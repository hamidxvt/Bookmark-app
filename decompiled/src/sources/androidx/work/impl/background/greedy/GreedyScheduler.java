package androidx.work.impl.background.greedy;

import android.content.Context;
import android.text.TextUtils;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.WorkInfo;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Scheduler;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.StartStopTokens;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.constraints.WorkConstraintsTrackerImpl;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.ProcessUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public class GreedyScheduler implements Scheduler, WorkConstraintsCallback, ExecutionListener {
    private static final String TAG = Logger.tagWithPrefix("GreedyScheduler");
    private final Context mContext;
    private DelayedWorkTracker mDelayedWorkTracker;
    Boolean mInDefaultProcess;
    private boolean mRegisteredExecutionListener;
    private final WorkConstraintsTracker mWorkConstraintsTracker;
    private final WorkManagerImpl mWorkManagerImpl;
    private final Set<WorkSpec> mConstrainedWorkSpecs = new HashSet();
    private final StartStopTokens mStartStopTokens = new StartStopTokens();
    private final Object mLock = new Object();

    public GreedyScheduler(Context context, Configuration configuration, Trackers trackers, WorkManagerImpl workManagerImpl) {
        this.mContext = context;
        this.mWorkManagerImpl = workManagerImpl;
        this.mWorkConstraintsTracker = new WorkConstraintsTrackerImpl(trackers, this);
        this.mDelayedWorkTracker = new DelayedWorkTracker(this, configuration.getRunnableScheduler());
    }

    public GreedyScheduler(Context context, WorkManagerImpl workManagerImpl, WorkConstraintsTracker workConstraintsTracker) {
        this.mContext = context;
        this.mWorkManagerImpl = workManagerImpl;
        this.mWorkConstraintsTracker = workConstraintsTracker;
    }

    public void setDelayedWorkTracker(DelayedWorkTracker delayedWorkTracker) {
        this.mDelayedWorkTracker = delayedWorkTracker;
    }

    @Override // androidx.work.impl.Scheduler
    public boolean hasLimitedSchedulingSlots() {
        return false;
    }

    @Override // androidx.work.impl.Scheduler
    public void schedule(WorkSpec... workSpecs) {
        if (this.mInDefaultProcess == null) {
            checkDefaultProcess();
        }
        if (!this.mInDefaultProcess.booleanValue()) {
            Logger.get().info(TAG, "Ignoring schedule request in a secondary process");
            return;
        }
        registerExecutionListenerIfNeeded();
        Set<WorkSpec> constrainedWorkSpecs = new HashSet<>();
        Set<String> constrainedWorkSpecIds = new HashSet<>();
        for (WorkSpec workSpec : workSpecs) {
            WorkGenerationalId id = WorkSpecKt.generationalId(workSpec);
            if (!this.mStartStopTokens.contains(id)) {
                long nextRunTime = workSpec.calculateNextRunTime();
                long now = System.currentTimeMillis();
                if (workSpec.state == WorkInfo.State.ENQUEUED) {
                    if (now < nextRunTime) {
                        if (this.mDelayedWorkTracker != null) {
                            this.mDelayedWorkTracker.schedule(workSpec);
                        }
                    } else if (workSpec.hasConstraints()) {
                        if (workSpec.constraints.getRequiresDeviceIdle()) {
                            Logger.get().debug(TAG, "Ignoring " + workSpec + ". Requires device idle.");
                        } else if (workSpec.constraints.hasContentUriTriggers()) {
                            Logger.get().debug(TAG, "Ignoring " + workSpec + ". Requires ContentUri triggers.");
                        } else {
                            constrainedWorkSpecs.add(workSpec);
                            constrainedWorkSpecIds.add(workSpec.id);
                        }
                    } else if (!this.mStartStopTokens.contains(WorkSpecKt.generationalId(workSpec))) {
                        Logger.get().debug(TAG, "Starting work for " + workSpec.id);
                        this.mWorkManagerImpl.startWork(this.mStartStopTokens.tokenFor(workSpec));
                    }
                }
            }
        }
        synchronized (this.mLock) {
            if (!constrainedWorkSpecs.isEmpty()) {
                String formattedIds = TextUtils.join(",", constrainedWorkSpecIds);
                Logger.get().debug(TAG, "Starting tracking for " + formattedIds);
                this.mConstrainedWorkSpecs.addAll(constrainedWorkSpecs);
                this.mWorkConstraintsTracker.replace(this.mConstrainedWorkSpecs);
            }
        }
    }

    private void checkDefaultProcess() {
        Configuration configuration = this.mWorkManagerImpl.getConfiguration();
        this.mInDefaultProcess = Boolean.valueOf(ProcessUtils.isDefaultProcess(this.mContext, configuration));
    }

    @Override // androidx.work.impl.Scheduler
    public void cancel(String workSpecId) {
        if (this.mInDefaultProcess == null) {
            checkDefaultProcess();
        }
        if (!this.mInDefaultProcess.booleanValue()) {
            Logger.get().info(TAG, "Ignoring schedule request in non-main process");
            return;
        }
        registerExecutionListenerIfNeeded();
        Logger.get().debug(TAG, "Cancelling work ID " + workSpecId);
        if (this.mDelayedWorkTracker != null) {
            this.mDelayedWorkTracker.unschedule(workSpecId);
        }
        for (StartStopToken id : this.mStartStopTokens.remove(workSpecId)) {
            this.mWorkManagerImpl.stopWork(id);
        }
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsMet(List<WorkSpec> workSpecs) {
        for (WorkSpec workSpec : workSpecs) {
            WorkGenerationalId id = WorkSpecKt.generationalId(workSpec);
            if (!this.mStartStopTokens.contains(id)) {
                Logger.get().debug(TAG, "Constraints met: Scheduling work ID " + id);
                this.mWorkManagerImpl.startWork(this.mStartStopTokens.tokenFor(id));
            }
        }
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsNotMet(List<WorkSpec> workSpecs) {
        for (WorkSpec workSpec : workSpecs) {
            WorkGenerationalId id = WorkSpecKt.generationalId(workSpec);
            Logger.get().debug(TAG, "Constraints not met: Cancelling work ID " + id);
            StartStopToken runId = this.mStartStopTokens.remove(id);
            if (runId != null) {
                this.mWorkManagerImpl.stopWork(runId);
            }
        }
    }

    @Override // androidx.work.impl.ExecutionListener
    /* renamed from: onExecuted */
    public void m180lambda$runOnExecuted$1$androidxworkimplProcessor(WorkGenerationalId id, boolean needsReschedule) {
        this.mStartStopTokens.remove(id);
        removeConstraintTrackingFor(id);
    }

    private void removeConstraintTrackingFor(WorkGenerationalId id) {
        synchronized (this.mLock) {
            Iterator<WorkSpec> it = this.mConstrainedWorkSpecs.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WorkSpec constrainedWorkSpec = it.next();
                if (WorkSpecKt.generationalId(constrainedWorkSpec).equals(id)) {
                    Logger.get().debug(TAG, "Stopping tracking for " + id);
                    this.mConstrainedWorkSpecs.remove(constrainedWorkSpec);
                    this.mWorkConstraintsTracker.replace(this.mConstrainedWorkSpecs);
                    break;
                }
            }
        }
    }

    private void registerExecutionListenerIfNeeded() {
        if (!this.mRegisteredExecutionListener) {
            this.mWorkManagerImpl.getProcessor().addExecutionListener(this);
            this.mRegisteredExecutionListener = true;
        }
    }
}
