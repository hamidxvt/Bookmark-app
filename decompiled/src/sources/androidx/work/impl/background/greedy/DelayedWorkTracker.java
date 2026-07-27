package androidx.work.impl.background.greedy;

import androidx.work.Logger;
import androidx.work.RunnableScheduler;
import androidx.work.impl.model.WorkSpec;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class DelayedWorkTracker {
    static final String TAG = Logger.tagWithPrefix("DelayedWorkTracker");
    final GreedyScheduler mGreedyScheduler;
    private final RunnableScheduler mRunnableScheduler;
    private final Map<String, Runnable> mRunnables = new HashMap();

    public DelayedWorkTracker(GreedyScheduler scheduler, RunnableScheduler runnableScheduler) {
        this.mGreedyScheduler = scheduler;
        this.mRunnableScheduler = runnableScheduler;
    }

    public void schedule(final WorkSpec workSpec) {
        Runnable existing = this.mRunnables.remove(workSpec.id);
        if (existing != null) {
            this.mRunnableScheduler.cancel(existing);
        }
        Runnable runnable = new Runnable() { // from class: androidx.work.impl.background.greedy.DelayedWorkTracker.1
            @Override // java.lang.Runnable
            public void run() {
                Logger.get().debug(DelayedWorkTracker.TAG, "Scheduling work " + workSpec.id);
                DelayedWorkTracker.this.mGreedyScheduler.schedule(workSpec);
            }
        };
        this.mRunnables.put(workSpec.id, runnable);
        long now = System.currentTimeMillis();
        long delay = workSpec.calculateNextRunTime() - now;
        this.mRunnableScheduler.scheduleWithDelay(delay, runnable);
    }

    public void unschedule(String workSpecId) {
        Runnable runnable = this.mRunnables.remove(workSpecId);
        if (runnable != null) {
            this.mRunnableScheduler.cancel(runnable);
        }
    }
}
