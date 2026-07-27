package androidx.work.impl.utils;

import android.content.Context;
import android.text.TextUtils;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.Operation;
import androidx.work.WorkInfo;
import androidx.work.WorkRequest;
import androidx.work.impl.OperationImpl;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkContinuationImpl;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.model.Dependency;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkName;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public class EnqueueRunnable implements Runnable {
    private static final String TAG = Logger.tagWithPrefix("EnqueueRunnable");
    private final OperationImpl mOperation;
    private final WorkContinuationImpl mWorkContinuation;

    public EnqueueRunnable(WorkContinuationImpl workContinuation) {
        this(workContinuation, new OperationImpl());
    }

    public EnqueueRunnable(WorkContinuationImpl workContinuation, OperationImpl result) {
        this.mWorkContinuation = workContinuation;
        this.mOperation = result;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.mWorkContinuation.hasCycles()) {
                throw new IllegalStateException("WorkContinuation has cycles (" + this.mWorkContinuation + ")");
            }
            boolean needsScheduling = addToDatabase();
            if (needsScheduling) {
                Context context = this.mWorkContinuation.getWorkManagerImpl().getApplicationContext();
                PackageManagerHelper.setComponentEnabled(context, RescheduleReceiver.class, true);
                scheduleWorkInBackground();
            }
            this.mOperation.markState(Operation.SUCCESS);
        } catch (Throwable exception) {
            this.mOperation.markState(new Operation.State.FAILURE(exception));
        }
    }

    public Operation getOperation() {
        return this.mOperation;
    }

    public boolean addToDatabase() {
        WorkManagerImpl workManagerImpl = this.mWorkContinuation.getWorkManagerImpl();
        WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
        workDatabase.beginTransaction();
        try {
            boolean needsScheduling = processContinuation(this.mWorkContinuation);
            workDatabase.setTransactionSuccessful();
            return needsScheduling;
        } finally {
            workDatabase.endTransaction();
        }
    }

    public void scheduleWorkInBackground() {
        WorkManagerImpl workManager = this.mWorkContinuation.getWorkManagerImpl();
        Schedulers.schedule(workManager.getConfiguration(), workManager.getWorkDatabase(), workManager.getSchedulers());
    }

    private static boolean processContinuation(WorkContinuationImpl workContinuation) {
        boolean needsScheduling = false;
        List<WorkContinuationImpl> parents = workContinuation.getParents();
        if (parents != null) {
            for (WorkContinuationImpl parent : parents) {
                if (!parent.isEnqueued()) {
                    needsScheduling |= processContinuation(parent);
                } else {
                    Logger.get().warning(TAG, "Already enqueued work ids (" + TextUtils.join(", ", parent.getIds()) + ")");
                }
            }
        }
        return needsScheduling | enqueueContinuation(workContinuation);
    }

    private static boolean enqueueContinuation(WorkContinuationImpl workContinuation) {
        Set<String> prerequisiteIds = WorkContinuationImpl.prerequisitesFor(workContinuation);
        boolean needsScheduling = enqueueWorkWithPrerequisites(workContinuation.getWorkManagerImpl(), workContinuation.getWork(), (String[]) prerequisiteIds.toArray(new String[0]), workContinuation.getName(), workContinuation.getExistingWorkPolicy());
        workContinuation.markEnqueued();
        return needsScheduling;
    }

    /* JADX WARN: Removed duplicated region for block: B:82:0x01ba  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean enqueueWorkWithPrerequisites(WorkManagerImpl workManagerImpl, List<? extends WorkRequest> workList, String[] prerequisiteIds, String name, ExistingWorkPolicy existingWorkPolicy) {
        boolean needsScheduling;
        boolean hasPrerequisite;
        Iterator<? extends WorkRequest> it;
        String[] prerequisiteIds2;
        Iterator<? extends WorkRequest> it2;
        long currentTimeMillis;
        DependencyDao dependencyDao;
        boolean z;
        String[] prerequisiteIds3 = prerequisiteIds;
        boolean needsScheduling2 = false;
        long currentTimeMillis2 = System.currentTimeMillis();
        WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
        boolean hasPrerequisite2 = prerequisiteIds3 != null && prerequisiteIds3.length > 0;
        boolean hasCompletedAllPrerequisites = true;
        boolean hasFailedPrerequisites = false;
        boolean hasCancelledPrerequisites = false;
        if (!hasPrerequisite2) {
            needsScheduling = false;
        } else {
            int length = prerequisiteIds3.length;
            int i = 0;
            while (i < length) {
                String id = prerequisiteIds3[i];
                WorkSpec prerequisiteWorkSpec = workDatabase.workSpecDao().getWorkSpec(id);
                if (prerequisiteWorkSpec == null) {
                    Logger.get().error(TAG, "Prerequisite " + id + " doesn't exist; not enqueuing");
                    return false;
                }
                boolean needsScheduling3 = needsScheduling2;
                WorkInfo.State prerequisiteState = prerequisiteWorkSpec.state;
                hasCompletedAllPrerequisites &= prerequisiteState == WorkInfo.State.SUCCEEDED;
                if (prerequisiteState == WorkInfo.State.FAILED) {
                    hasFailedPrerequisites = true;
                } else if (prerequisiteState == WorkInfo.State.CANCELLED) {
                    hasCancelledPrerequisites = true;
                }
                i++;
                needsScheduling2 = needsScheduling3;
            }
            needsScheduling = needsScheduling2;
        }
        boolean needsScheduling4 = TextUtils.isEmpty(name);
        boolean isNamed = !needsScheduling4;
        boolean shouldApplyExistingWorkPolicy = isNamed && !hasPrerequisite2;
        if (shouldApplyExistingWorkPolicy) {
            List<WorkSpec.IdAndState> existingWorkSpecIdAndStates = workDatabase.workSpecDao().getWorkSpecIdAndStatesForName(name);
            if (!existingWorkSpecIdAndStates.isEmpty()) {
                if (existingWorkPolicy != ExistingWorkPolicy.APPEND && existingWorkPolicy != ExistingWorkPolicy.APPEND_OR_REPLACE) {
                    if (existingWorkPolicy != ExistingWorkPolicy.KEEP) {
                        z = false;
                    } else {
                        Iterator<WorkSpec.IdAndState> it3 = existingWorkSpecIdAndStates.iterator();
                        while (it3.hasNext()) {
                            WorkSpec.IdAndState idAndState = it3.next();
                            Iterator<WorkSpec.IdAndState> it4 = it3;
                            if (idAndState.state == WorkInfo.State.ENQUEUED || idAndState.state == WorkInfo.State.RUNNING) {
                                return false;
                            }
                            it3 = it4;
                        }
                        z = false;
                    }
                    CancelWorkRunnable.forName(name, workManagerImpl, z).run();
                    boolean needsScheduling5 = true;
                    WorkSpecDao workSpecDao = workDatabase.workSpecDao();
                    Iterator<WorkSpec.IdAndState> it5 = existingWorkSpecIdAndStates.iterator();
                    while (it5.hasNext()) {
                        workSpecDao.delete(it5.next().id);
                        needsScheduling5 = needsScheduling5;
                        shouldApplyExistingWorkPolicy = shouldApplyExistingWorkPolicy;
                    }
                    needsScheduling = needsScheduling5;
                    it = workList.iterator();
                    while (it.hasNext()) {
                        WorkRequest work = it.next();
                        WorkSpec workSpec = work.getWorkSpec();
                        if (hasPrerequisite2 && !hasCompletedAllPrerequisites) {
                            if (hasFailedPrerequisites) {
                                workSpec.state = WorkInfo.State.FAILED;
                            } else if (hasCancelledPrerequisites) {
                                workSpec.state = WorkInfo.State.CANCELLED;
                            } else {
                                workSpec.state = WorkInfo.State.BLOCKED;
                            }
                        } else {
                            workSpec.lastEnqueueTime = currentTimeMillis2;
                        }
                        if (workSpec.state == WorkInfo.State.ENQUEUED) {
                            needsScheduling = true;
                        }
                        workDatabase.workSpecDao().insertWorkSpec(EnqueueUtilsKt.wrapInConstraintTrackingWorkerIfNeeded(workManagerImpl.getSchedulers(), workSpec));
                        if (!hasPrerequisite2) {
                            prerequisiteIds2 = prerequisiteIds3;
                            it2 = it;
                            currentTimeMillis = currentTimeMillis2;
                        } else {
                            int length2 = prerequisiteIds3.length;
                            int i2 = 0;
                            while (i2 < length2) {
                                Iterator<? extends WorkRequest> it6 = it;
                                String prerequisiteId = prerequisiteIds3[i2];
                                String[] prerequisiteIds4 = prerequisiteIds3;
                                Dependency dep = new Dependency(work.getStringId(), prerequisiteId);
                                workDatabase.dependencyDao().insertDependency(dep);
                                i2++;
                                it = it6;
                                prerequisiteIds3 = prerequisiteIds4;
                                currentTimeMillis2 = currentTimeMillis2;
                            }
                            prerequisiteIds2 = prerequisiteIds3;
                            it2 = it;
                            currentTimeMillis = currentTimeMillis2;
                        }
                        workDatabase.workTagDao().insertTags(work.getStringId(), work.getTags());
                        if (isNamed) {
                            workDatabase.workNameDao().insert(new WorkName(name, work.getStringId()));
                        }
                        it = it2;
                        prerequisiteIds3 = prerequisiteIds2;
                        currentTimeMillis2 = currentTimeMillis;
                    }
                    return needsScheduling;
                }
                DependencyDao dependencyDao2 = workDatabase.dependencyDao();
                List<String> newPrerequisiteIds = new ArrayList<>();
                for (WorkSpec.IdAndState idAndState2 : existingWorkSpecIdAndStates) {
                    boolean hasPrerequisite3 = hasPrerequisite2;
                    if (dependencyDao2.hasDependents(idAndState2.id)) {
                        dependencyDao = dependencyDao2;
                    } else {
                        dependencyDao = dependencyDao2;
                        boolean hasCompletedAllPrerequisites2 = (idAndState2.state == WorkInfo.State.SUCCEEDED) & hasCompletedAllPrerequisites;
                        if (idAndState2.state == WorkInfo.State.FAILED) {
                            hasFailedPrerequisites = true;
                        } else if (idAndState2.state == WorkInfo.State.CANCELLED) {
                            hasCancelledPrerequisites = true;
                        }
                        newPrerequisiteIds.add(idAndState2.id);
                        hasCompletedAllPrerequisites = hasCompletedAllPrerequisites2;
                    }
                    hasPrerequisite2 = hasPrerequisite3;
                    dependencyDao2 = dependencyDao;
                }
                if (existingWorkPolicy == ExistingWorkPolicy.APPEND_OR_REPLACE && (hasCancelledPrerequisites || hasFailedPrerequisites)) {
                    WorkSpecDao workSpecDao2 = workDatabase.workSpecDao();
                    List<WorkSpec.IdAndState> idAndStates = workSpecDao2.getWorkSpecIdAndStatesForName(name);
                    Iterator<WorkSpec.IdAndState> it7 = idAndStates.iterator();
                    while (it7.hasNext()) {
                        workSpecDao2.delete(it7.next().id);
                        idAndStates = idAndStates;
                    }
                    newPrerequisiteIds = Collections.emptyList();
                    hasCancelledPrerequisites = false;
                    hasFailedPrerequisites = false;
                }
                prerequisiteIds3 = (String[]) newPrerequisiteIds.toArray(prerequisiteIds3);
                hasPrerequisite2 = prerequisiteIds3.length > 0;
                it = workList.iterator();
                while (it.hasNext()) {
                }
                return needsScheduling;
            }
            hasPrerequisite = hasPrerequisite2;
        } else {
            hasPrerequisite = hasPrerequisite2;
        }
        hasPrerequisite2 = hasPrerequisite;
        it = workList.iterator();
        while (it.hasNext()) {
        }
        return needsScheduling;
    }
}
