package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import androidx.work.Logger;
import androidx.work.impl.background.systemalarm.SystemAlarmDispatcher;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTrackerImpl;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
class ConstraintsCommandHandler {
    private static final String TAG = Logger.tagWithPrefix("ConstraintsCmdHandler");
    private final Context mContext;
    private final SystemAlarmDispatcher mDispatcher;
    private final int mStartId;
    private final WorkConstraintsTrackerImpl mWorkConstraintsTracker;

    ConstraintsCommandHandler(Context context, int startId, SystemAlarmDispatcher dispatcher) {
        this.mContext = context;
        this.mStartId = startId;
        this.mDispatcher = dispatcher;
        Trackers trackers = this.mDispatcher.getWorkManager().getTrackers();
        this.mWorkConstraintsTracker = new WorkConstraintsTrackerImpl(trackers, (WorkConstraintsCallback) null);
    }

    void handleConstraintsChanged() {
        List<WorkSpec> candidates = this.mDispatcher.getWorkManager().getWorkDatabase().workSpecDao().getScheduledWork();
        ConstraintProxy.updateAll(this.mContext, candidates);
        this.mWorkConstraintsTracker.replace(candidates);
        List<WorkSpec> eligibleWorkSpecs = new ArrayList<>(candidates.size());
        long now = System.currentTimeMillis();
        for (WorkSpec workSpec : candidates) {
            String workSpecId = workSpec.id;
            long triggerAt = workSpec.calculateNextRunTime();
            if (now >= triggerAt && (!workSpec.hasConstraints() || this.mWorkConstraintsTracker.areAllConstraintsMet(workSpecId))) {
                eligibleWorkSpecs.add(workSpec);
            }
        }
        for (WorkSpec workSpec2 : eligibleWorkSpecs) {
            String workSpecId2 = workSpec2.id;
            Intent intent = CommandHandler.createDelayMetIntent(this.mContext, WorkSpecKt.generationalId(workSpec2));
            Logger.get().debug(TAG, "Creating a delay_met command for workSpec with id (" + workSpecId2 + ")");
            this.mDispatcher.getTaskExecutor().getMainThreadExecutor().execute(new SystemAlarmDispatcher.AddRunnable(this.mDispatcher, intent, this.mStartId));
        }
        this.mWorkConstraintsTracker.reset();
    }
}
