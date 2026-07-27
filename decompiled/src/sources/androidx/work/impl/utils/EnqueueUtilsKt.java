package androidx.work.impl.utils;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.impl.Scheduler;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import androidx.work.impl.workers.ConstraintTrackingWorkerKt;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EnqueueUtils.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0002\u001a\u001e\u0010\n\u001a\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¨\u0006\u000b"}, d2 = {"tryDelegateConstrainedWorkSpec", "Landroidx/work/impl/model/WorkSpec;", "workSpec", "usesScheduler", "", "schedulers", "", "Landroidx/work/impl/Scheduler;", "className", "", "wrapInConstraintTrackingWorkerIfNeeded", "work-runtime_release"}, k = 2, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class EnqueueUtilsKt {
    public static final WorkSpec tryDelegateConstrainedWorkSpec(WorkSpec workSpec) {
        WorkSpec copy;
        Intrinsics.checkNotNullParameter(workSpec, "workSpec");
        Constraints constraints = workSpec.constraints;
        String workerClassName = workSpec.workerClassName;
        boolean isConstraintTrackingWorker = Intrinsics.areEqual(workerClassName, ConstraintTrackingWorker.class.getName());
        if (!isConstraintTrackingWorker && (constraints.getRequiresBatteryNotLow() || constraints.getRequiresStorageNotLow())) {
            Data newInputData = new Data.Builder().putAll(workSpec.input).putString(ConstraintTrackingWorkerKt.ARGUMENT_CLASS_NAME, workerClassName).build();
            Intrinsics.checkNotNullExpressionValue(newInputData, "Builder().putAll(workSpe…ame)\n            .build()");
            String name = ConstraintTrackingWorker.class.getName();
            Intrinsics.checkNotNullExpressionValue(name, "name");
            copy = workSpec.copy((r45 & 1) != 0 ? workSpec.id : null, (r45 & 2) != 0 ? workSpec.state : null, (r45 & 4) != 0 ? workSpec.workerClassName : name, (r45 & 8) != 0 ? workSpec.inputMergerClassName : null, (r45 & 16) != 0 ? workSpec.input : newInputData, (r45 & 32) != 0 ? workSpec.output : null, (r45 & 64) != 0 ? workSpec.initialDelay : 0L, (r45 & 128) != 0 ? workSpec.intervalDuration : 0L, (r45 & 256) != 0 ? workSpec.flexDuration : 0L, (r45 & 512) != 0 ? workSpec.constraints : null, (r45 & 1024) != 0 ? workSpec.runAttemptCount : 0, (r45 & 2048) != 0 ? workSpec.backoffPolicy : null, (r45 & 4096) != 0 ? workSpec.backoffDelayDuration : 0L, (r45 & 8192) != 0 ? workSpec.lastEnqueueTime : 0L, (r45 & 16384) != 0 ? workSpec.minimumRetentionDuration : 0L, (r45 & 32768) != 0 ? workSpec.scheduleRequestedAt : 0L, (r45 & 65536) != 0 ? workSpec.expedited : false, (131072 & r45) != 0 ? workSpec.outOfQuotaPolicy : null, (r45 & 262144) != 0 ? workSpec.periodCount : 0, (r45 & 524288) != 0 ? workSpec.generation : 0);
            return copy;
        }
        return workSpec;
    }

    public static final WorkSpec wrapInConstraintTrackingWorkerIfNeeded(List<? extends Scheduler> schedulers, WorkSpec workSpec) {
        Intrinsics.checkNotNullParameter(schedulers, "schedulers");
        Intrinsics.checkNotNullParameter(workSpec, "workSpec");
        return workSpec;
    }

    private static final boolean usesScheduler(List<? extends Scheduler> list, String className) {
        try {
            Class klass = Class.forName(className);
            List<? extends Scheduler> $this$any$iv = list;
            if (($this$any$iv instanceof Collection) && $this$any$iv.isEmpty()) {
                return false;
            }
            for (Object element$iv : $this$any$iv) {
                Scheduler scheduler = (Scheduler) element$iv;
                if (klass.isAssignableFrom(scheduler.getClass())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
