package androidx.work.impl;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.work.Configuration;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Operation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;
import androidx.work.impl.utils.EnqueueRunnable;
import androidx.work.impl.utils.EnqueueUtilsKt;
import androidx.work.impl.utils.futures.SettableFuture;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkerUpdater.kt */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aD\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002\u001a\u001c\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007\u001a\u0014\u0010\u0016\u001a\u00020\u0017*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000fH\u0002\u001a\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00010\u001a*\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0000¨\u0006\u001b"}, d2 = {"updateWorkImpl", "Landroidx/work/WorkManager$UpdateResult;", "processor", "Landroidx/work/impl/Processor;", "workDatabase", "Landroidx/work/impl/WorkDatabase;", "configuration", "Landroidx/work/Configuration;", "schedulers", "", "Landroidx/work/impl/Scheduler;", "newWorkSpec", "Landroidx/work/impl/model/WorkSpec;", "tags", "", "", "enqueueUniquelyNamedPeriodic", "Landroidx/work/Operation;", "Landroidx/work/impl/WorkManagerImpl;", AppMeasurementSdk.ConditionalUserProperty.NAME, "workRequest", "Landroidx/work/WorkRequest;", "failWorkTypeChanged", "", "Landroidx/work/impl/OperationImpl;", "message", "Lcom/google/common/util/concurrent/ListenableFuture;", "work-runtime_release"}, k = 2, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class WorkerUpdater {
    private static final WorkManager.UpdateResult updateWorkImpl(Processor processor, final WorkDatabase workDatabase, Configuration configuration, final List<? extends Scheduler> list, final WorkSpec newWorkSpec, final Set<String> set) {
        final String workSpecId = newWorkSpec.id;
        final WorkSpec oldWorkSpec = workDatabase.workSpecDao().getWorkSpec(workSpecId);
        if (oldWorkSpec == null) {
            throw new IllegalArgumentException("Worker with " + workSpecId + " doesn't exist");
        }
        if (oldWorkSpec.state.isFinished()) {
            return WorkManager.UpdateResult.NOT_APPLIED;
        }
        if (oldWorkSpec.isPeriodic() ^ newWorkSpec.isPeriodic()) {
            Function1 type = new Function1<WorkSpec, String>() { // from class: androidx.work.impl.WorkerUpdater$updateWorkImpl$type$1
                @Override // kotlin.jvm.functions.Function1
                public final String invoke(WorkSpec spec) {
                    Intrinsics.checkNotNullParameter(spec, "spec");
                    return spec.isPeriodic() ? "Periodic" : "OneTime";
                }
            };
            throw new UnsupportedOperationException("Can't update " + type.invoke(oldWorkSpec) + " Worker to " + type.invoke(newWorkSpec) + " Worker. Update operation must preserve worker's type.");
        }
        final boolean isEnqueued = processor.isEnqueued(workSpecId);
        if (!isEnqueued) {
            List<? extends Scheduler> $this$forEach$iv = list;
            for (Object element$iv : $this$forEach$iv) {
                Scheduler scheduler = (Scheduler) element$iv;
                scheduler.cancel(workSpecId);
            }
        }
        workDatabase.runInTransaction(new Runnable() { // from class: androidx.work.impl.WorkerUpdater$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                WorkerUpdater.updateWorkImpl$lambda$1(WorkDatabase.this, newWorkSpec, oldWorkSpec, list, workSpecId, set, isEnqueued);
            }
        });
        if (!isEnqueued) {
            Schedulers.schedule(configuration, workDatabase, list);
        }
        return isEnqueued ? WorkManager.UpdateResult.APPLIED_FOR_NEXT_RUN : WorkManager.UpdateResult.APPLIED_IMMEDIATELY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateWorkImpl$lambda$1(WorkDatabase workDatabase, WorkSpec newWorkSpec, WorkSpec oldWorkSpec, List schedulers, String workSpecId, Set tags, boolean $isEnqueued) {
        WorkSpec updatedSpec;
        Intrinsics.checkNotNullParameter(workDatabase, "$workDatabase");
        Intrinsics.checkNotNullParameter(newWorkSpec, "$newWorkSpec");
        Intrinsics.checkNotNullParameter(oldWorkSpec, "$oldWorkSpec");
        Intrinsics.checkNotNullParameter(schedulers, "$schedulers");
        Intrinsics.checkNotNullParameter(workSpecId, "$workSpecId");
        Intrinsics.checkNotNullParameter(tags, "$tags");
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        WorkTagDao workTagDao = workDatabase.workTagDao();
        updatedSpec = newWorkSpec.copy((r45 & 1) != 0 ? newWorkSpec.id : null, (r45 & 2) != 0 ? newWorkSpec.state : oldWorkSpec.state, (r45 & 4) != 0 ? newWorkSpec.workerClassName : null, (r45 & 8) != 0 ? newWorkSpec.inputMergerClassName : null, (r45 & 16) != 0 ? newWorkSpec.input : null, (r45 & 32) != 0 ? newWorkSpec.output : null, (r45 & 64) != 0 ? newWorkSpec.initialDelay : 0L, (r45 & 128) != 0 ? newWorkSpec.intervalDuration : 0L, (r45 & 256) != 0 ? newWorkSpec.flexDuration : 0L, (r45 & 512) != 0 ? newWorkSpec.constraints : null, (r45 & 1024) != 0 ? newWorkSpec.runAttemptCount : oldWorkSpec.runAttemptCount, (r45 & 2048) != 0 ? newWorkSpec.backoffPolicy : null, (r45 & 4096) != 0 ? newWorkSpec.backoffDelayDuration : 0L, (r45 & 8192) != 0 ? newWorkSpec.lastEnqueueTime : oldWorkSpec.lastEnqueueTime, (r45 & 16384) != 0 ? newWorkSpec.minimumRetentionDuration : 0L, (r45 & 32768) != 0 ? newWorkSpec.scheduleRequestedAt : 0L, (r45 & 65536) != 0 ? newWorkSpec.expedited : false, (131072 & r45) != 0 ? newWorkSpec.outOfQuotaPolicy : null, (r45 & 262144) != 0 ? newWorkSpec.periodCount : 0, (r45 & 524288) != 0 ? newWorkSpec.generation : oldWorkSpec.getGeneration() + 1);
        workSpecDao.updateWorkSpec(EnqueueUtilsKt.wrapInConstraintTrackingWorkerIfNeeded(schedulers, updatedSpec));
        workTagDao.deleteByWorkSpecId(workSpecId);
        workTagDao.insertTags(workSpecId, tags);
        if ($isEnqueued) {
            return;
        }
        workSpecDao.markWorkSpecScheduled(workSpecId, -1L);
        workDatabase.workProgressDao().delete(workSpecId);
    }

    public static final ListenableFuture<WorkManager.UpdateResult> updateWorkImpl(final WorkManagerImpl $this$updateWorkImpl, final WorkRequest workRequest) {
        Intrinsics.checkNotNullParameter($this$updateWorkImpl, "<this>");
        Intrinsics.checkNotNullParameter(workRequest, "workRequest");
        final SettableFuture future = SettableFuture.create();
        $this$updateWorkImpl.getWorkTaskExecutor().getSerialTaskExecutor().execute(new Runnable() { // from class: androidx.work.impl.WorkerUpdater$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                WorkerUpdater.updateWorkImpl$lambda$2(SettableFuture.this, $this$updateWorkImpl, workRequest);
            }
        });
        Intrinsics.checkNotNullExpressionValue(future, "future");
        return future;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateWorkImpl$lambda$2(SettableFuture $future, WorkManagerImpl this_updateWorkImpl, WorkRequest workRequest) {
        Intrinsics.checkNotNullParameter(this_updateWorkImpl, "$this_updateWorkImpl");
        Intrinsics.checkNotNullParameter(workRequest, "$workRequest");
        if ($future.isCancelled()) {
            return;
        }
        try {
            Processor processor = this_updateWorkImpl.getProcessor();
            Intrinsics.checkNotNullExpressionValue(processor, "processor");
            WorkDatabase workDatabase = this_updateWorkImpl.getWorkDatabase();
            Intrinsics.checkNotNullExpressionValue(workDatabase, "workDatabase");
            Configuration configuration = this_updateWorkImpl.getConfiguration();
            Intrinsics.checkNotNullExpressionValue(configuration, "configuration");
            List<Scheduler> schedulers = this_updateWorkImpl.getSchedulers();
            Intrinsics.checkNotNullExpressionValue(schedulers, "schedulers");
            WorkManager.UpdateResult result = updateWorkImpl(processor, workDatabase, configuration, schedulers, workRequest.getWorkSpec(), workRequest.getTags());
            $future.set(result);
        } catch (Throwable e) {
            $future.setException(e);
        }
    }

    public static final Operation enqueueUniquelyNamedPeriodic(final WorkManagerImpl $this$enqueueUniquelyNamedPeriodic, final String name, final WorkRequest workRequest) {
        Intrinsics.checkNotNullParameter($this$enqueueUniquelyNamedPeriodic, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(workRequest, "workRequest");
        final OperationImpl operation = new OperationImpl();
        final Function0 enqueueNew = new Function0<Unit>() { // from class: androidx.work.impl.WorkerUpdater$enqueueUniquelyNamedPeriodic$enqueueNew$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                List requests = CollectionsKt.listOf(WorkRequest.this);
                WorkContinuationImpl continuation = new WorkContinuationImpl($this$enqueueUniquelyNamedPeriodic, name, ExistingWorkPolicy.KEEP, requests);
                new EnqueueRunnable(continuation, operation).run();
            }
        };
        $this$enqueueUniquelyNamedPeriodic.getWorkTaskExecutor().getSerialTaskExecutor().execute(new Runnable() { // from class: androidx.work.impl.WorkerUpdater$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                WorkerUpdater.enqueueUniquelyNamedPeriodic$lambda$3(WorkManagerImpl.this, name, operation, enqueueNew, workRequest);
            }
        });
        return operation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void enqueueUniquelyNamedPeriodic$lambda$3(WorkManagerImpl this_enqueueUniquelyNamedPeriodic, String name, OperationImpl operation, Function0 enqueueNew, WorkRequest workRequest) {
        WorkSpec newWorkSpec;
        Intrinsics.checkNotNullParameter(this_enqueueUniquelyNamedPeriodic, "$this_enqueueUniquelyNamedPeriodic");
        Intrinsics.checkNotNullParameter(name, "$name");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        Intrinsics.checkNotNullParameter(enqueueNew, "$enqueueNew");
        Intrinsics.checkNotNullParameter(workRequest, "$workRequest");
        WorkSpecDao workSpecDao = this_enqueueUniquelyNamedPeriodic.getWorkDatabase().workSpecDao();
        List idAndStates = workSpecDao.getWorkSpecIdAndStatesForName(name);
        if (idAndStates.size() > 1) {
            failWorkTypeChanged(operation, "Can't apply UPDATE policy to the chains of work.");
            return;
        }
        WorkSpec.IdAndState current = (WorkSpec.IdAndState) CollectionsKt.firstOrNull(idAndStates);
        if (current == null) {
            enqueueNew.invoke();
            return;
        }
        WorkSpec spec = workSpecDao.getWorkSpec(current.id);
        if (spec == null) {
            operation.markState(new Operation.State.FAILURE(new IllegalStateException("WorkSpec with " + current.id + ", that matches a name \"" + name + "\", wasn't found")));
            return;
        }
        if (!spec.isPeriodic()) {
            failWorkTypeChanged(operation, "Can't update OneTimeWorker to Periodic Worker. Update operation must preserve worker's type.");
            return;
        }
        if (current.state == WorkInfo.State.CANCELLED) {
            workSpecDao.delete(current.id);
            enqueueNew.invoke();
            return;
        }
        newWorkSpec = r10.copy((r45 & 1) != 0 ? r10.id : current.id, (r45 & 2) != 0 ? r10.state : null, (r45 & 4) != 0 ? r10.workerClassName : null, (r45 & 8) != 0 ? r10.inputMergerClassName : null, (r45 & 16) != 0 ? r10.input : null, (r45 & 32) != 0 ? r10.output : null, (r45 & 64) != 0 ? r10.initialDelay : 0L, (r45 & 128) != 0 ? r10.intervalDuration : 0L, (r45 & 256) != 0 ? r10.flexDuration : 0L, (r45 & 512) != 0 ? r10.constraints : null, (r45 & 1024) != 0 ? r10.runAttemptCount : 0, (r45 & 2048) != 0 ? r10.backoffPolicy : null, (r45 & 4096) != 0 ? r10.backoffDelayDuration : 0L, (r45 & 8192) != 0 ? r10.lastEnqueueTime : 0L, (r45 & 16384) != 0 ? r10.minimumRetentionDuration : 0L, (r45 & 32768) != 0 ? r10.scheduleRequestedAt : 0L, (r45 & 65536) != 0 ? r10.expedited : false, (131072 & r45) != 0 ? r10.outOfQuotaPolicy : null, (r45 & 262144) != 0 ? r10.periodCount : 0, (r45 & 524288) != 0 ? workRequest.getWorkSpec().generation : 0);
        try {
            Processor processor = this_enqueueUniquelyNamedPeriodic.getProcessor();
            Intrinsics.checkNotNullExpressionValue(processor, "processor");
            WorkDatabase workDatabase = this_enqueueUniquelyNamedPeriodic.getWorkDatabase();
            Intrinsics.checkNotNullExpressionValue(workDatabase, "workDatabase");
            Configuration configuration = this_enqueueUniquelyNamedPeriodic.getConfiguration();
            Intrinsics.checkNotNullExpressionValue(configuration, "configuration");
            List<Scheduler> schedulers = this_enqueueUniquelyNamedPeriodic.getSchedulers();
            Intrinsics.checkNotNullExpressionValue(schedulers, "schedulers");
            updateWorkImpl(processor, workDatabase, configuration, schedulers, newWorkSpec, workRequest.getTags());
            operation.markState(Operation.SUCCESS);
        } catch (Throwable e) {
            operation.markState(new Operation.State.FAILURE(e));
        }
    }

    private static final void failWorkTypeChanged(OperationImpl $this$failWorkTypeChanged, String message) {
        $this$failWorkTypeChanged.markState(new Operation.State.FAILURE(new UnsupportedOperationException(message)));
    }
}
