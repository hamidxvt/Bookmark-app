package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import androidx.core.util.Consumer;
import androidx.work.Logger;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo;
import androidx.work.impl.Scheduler;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoKt;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.IdGenerator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* loaded from: classes.dex */
public class SystemJobScheduler implements Scheduler {
    private static final String TAG = Logger.tagWithPrefix("SystemJobScheduler");
    private final Context mContext;
    private final JobScheduler mJobScheduler;
    private final SystemJobInfoConverter mSystemJobInfoConverter;
    private final WorkManagerImpl mWorkManager;

    public SystemJobScheduler(Context context, WorkManagerImpl workManager) {
        this(context, workManager, (JobScheduler) context.getSystemService("jobscheduler"), new SystemJobInfoConverter(context));
    }

    public SystemJobScheduler(Context context, WorkManagerImpl workManager, JobScheduler jobScheduler, SystemJobInfoConverter systemJobInfoConverter) {
        this.mContext = context;
        this.mWorkManager = workManager;
        this.mJobScheduler = jobScheduler;
        this.mSystemJobInfoConverter = systemJobInfoConverter;
    }

    @Override // androidx.work.impl.Scheduler
    public void schedule(WorkSpec... workSpecs) {
        WorkDatabase workDatabase = this.mWorkManager.getWorkDatabase();
        IdGenerator idGenerator = new IdGenerator(workDatabase);
        for (WorkSpec workSpec : workSpecs) {
            workDatabase.beginTransaction();
            try {
                WorkSpec currentDbWorkSpec = workDatabase.workSpecDao().getWorkSpec(workSpec.id);
                if (currentDbWorkSpec == null) {
                    Logger.get().warning(TAG, "Skipping scheduling " + workSpec.id + " because it's no longer in the DB");
                    workDatabase.setTransactionSuccessful();
                    workDatabase.endTransaction();
                } else if (currentDbWorkSpec.state != WorkInfo.State.ENQUEUED) {
                    Logger.get().warning(TAG, "Skipping scheduling " + workSpec.id + " because it is no longer enqueued");
                    workDatabase.setTransactionSuccessful();
                    workDatabase.endTransaction();
                } else {
                    WorkGenerationalId generationalId = WorkSpecKt.generationalId(workSpec);
                    SystemIdInfo info = workDatabase.systemIdInfoDao().getSystemIdInfo(generationalId);
                    int jobId = info != null ? info.systemId : idGenerator.nextJobSchedulerIdWithRange(this.mWorkManager.getConfiguration().getMinJobSchedulerId(), this.mWorkManager.getConfiguration().getMaxJobSchedulerId());
                    if (info == null) {
                        SystemIdInfo newSystemIdInfo = SystemIdInfoKt.systemIdInfo(generationalId, jobId);
                        this.mWorkManager.getWorkDatabase().systemIdInfoDao().insertSystemIdInfo(newSystemIdInfo);
                    }
                    scheduleInternal(workSpec, jobId);
                    workDatabase.setTransactionSuccessful();
                }
            } finally {
                workDatabase.endTransaction();
            }
        }
    }

    public void scheduleInternal(WorkSpec workSpec, int jobId) {
        JobInfo jobInfo = this.mSystemJobInfoConverter.convert(workSpec, jobId);
        Logger.get().debug(TAG, "Scheduling work ID " + workSpec.id + "Job ID " + jobId);
        try {
            int result = this.mJobScheduler.schedule(jobInfo);
            if (result == 0) {
                Logger.get().warning(TAG, "Unable to schedule work ID " + workSpec.id);
                if (workSpec.expedited && workSpec.outOfQuotaPolicy == OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST) {
                    workSpec.expedited = false;
                    Logger.get().debug(TAG, String.format("Scheduling a non-expedited job (work ID %s)", workSpec.id));
                    scheduleInternal(workSpec, jobId);
                }
            }
        } catch (IllegalStateException e) {
            List<JobInfo> jobs = getPendingJobs(this.mContext, this.mJobScheduler);
            int numWorkManagerJobs = jobs != null ? jobs.size() : 0;
            String message = String.format(Locale.getDefault(), "JobScheduler 100 job limit exceeded.  We count %d WorkManager jobs in JobScheduler; we have %d tracked jobs in our DB; our Configuration limit is %d.", Integer.valueOf(numWorkManagerJobs), Integer.valueOf(this.mWorkManager.getWorkDatabase().workSpecDao().getScheduledWork().size()), Integer.valueOf(this.mWorkManager.getConfiguration().getMaxSchedulerLimit()));
            Logger.get().error(TAG, message);
            IllegalStateException schedulingException = new IllegalStateException(message, e);
            Consumer<Throwable> handler = this.mWorkManager.getConfiguration().getSchedulingExceptionHandler();
            if (handler != null) {
                handler.accept(schedulingException);
                return;
            }
            throw schedulingException;
        } catch (Throwable throwable) {
            Logger.get().error(TAG, "Unable to schedule " + workSpec, throwable);
        }
    }

    @Override // androidx.work.impl.Scheduler
    public void cancel(String workSpecId) {
        List<Integer> jobIds = getPendingJobIds(this.mContext, this.mJobScheduler, workSpecId);
        if (jobIds != null && !jobIds.isEmpty()) {
            Iterator<Integer> it = jobIds.iterator();
            while (it.hasNext()) {
                int jobId = it.next().intValue();
                cancelJobById(this.mJobScheduler, jobId);
            }
            this.mWorkManager.getWorkDatabase().systemIdInfoDao().removeSystemIdInfo(workSpecId);
        }
    }

    @Override // androidx.work.impl.Scheduler
    public boolean hasLimitedSchedulingSlots() {
        return true;
    }

    private static void cancelJobById(JobScheduler jobScheduler, int id) {
        try {
            jobScheduler.cancel(id);
        } catch (Throwable throwable) {
            Logger.get().error(TAG, String.format(Locale.getDefault(), "Exception while trying to cancel job (%d)", Integer.valueOf(id)), throwable);
        }
    }

    public static void cancelAll(Context context) {
        List<JobInfo> jobs;
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler != null && (jobs = getPendingJobs(context, jobScheduler)) != null && !jobs.isEmpty()) {
            for (JobInfo jobInfo : jobs) {
                cancelJobById(jobScheduler, jobInfo.getId());
            }
        }
    }

    public static boolean reconcileJobs(Context context, WorkManagerImpl workManager) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        List<JobInfo> jobs = getPendingJobs(context, jobScheduler);
        List<String> workManagerWorkSpecs = workManager.getWorkDatabase().systemIdInfoDao().getWorkSpecIds();
        int jobSize = jobs != null ? jobs.size() : 0;
        Set<String> jobSchedulerWorkSpecs = new HashSet<>(jobSize);
        if (jobs != null && !jobs.isEmpty()) {
            for (JobInfo jobInfo : jobs) {
                WorkGenerationalId id = getWorkGenerationalIdFromJobInfo(jobInfo);
                if (id != null) {
                    jobSchedulerWorkSpecs.add(id.getWorkSpecId());
                } else {
                    cancelJobById(jobScheduler, jobInfo.getId());
                }
            }
        }
        boolean needsReconciling = false;
        Iterator<String> it = workManagerWorkSpecs.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String workSpecId = it.next();
            if (!jobSchedulerWorkSpecs.contains(workSpecId)) {
                Logger.get().debug(TAG, "Reconciling jobs");
                needsReconciling = true;
                break;
            }
        }
        if (needsReconciling) {
            WorkDatabase workDatabase = workManager.getWorkDatabase();
            workDatabase.beginTransaction();
            try {
                WorkSpecDao workSpecDao = workDatabase.workSpecDao();
                for (String workSpecId2 : workManagerWorkSpecs) {
                    workSpecDao.markWorkSpecScheduled(workSpecId2, -1L);
                }
                workDatabase.setTransactionSuccessful();
            } finally {
                workDatabase.endTransaction();
            }
        }
        return needsReconciling;
    }

    private static List<JobInfo> getPendingJobs(Context context, JobScheduler jobScheduler) {
        List<JobInfo> pendingJobs = null;
        try {
            pendingJobs = jobScheduler.getAllPendingJobs();
        } catch (Throwable exception) {
            Logger.get().error(TAG, "getAllPendingJobs() is not reliable on this device.", exception);
        }
        if (pendingJobs == null) {
            return null;
        }
        List<JobInfo> filtered = new ArrayList<>(pendingJobs.size());
        ComponentName jobServiceComponent = new ComponentName(context, (Class<?>) SystemJobService.class);
        for (JobInfo jobInfo : pendingJobs) {
            if (jobServiceComponent.equals(jobInfo.getService())) {
                filtered.add(jobInfo);
            }
        }
        return filtered;
    }

    private static List<Integer> getPendingJobIds(Context context, JobScheduler jobScheduler, String workSpecId) {
        List<JobInfo> jobs = getPendingJobs(context, jobScheduler);
        if (jobs == null) {
            return null;
        }
        List<Integer> jobIds = new ArrayList<>(2);
        for (JobInfo jobInfo : jobs) {
            WorkGenerationalId id = getWorkGenerationalIdFromJobInfo(jobInfo);
            if (id != null && workSpecId.equals(id.getWorkSpecId())) {
                jobIds.add(Integer.valueOf(jobInfo.getId()));
            }
        }
        return jobIds;
    }

    private static WorkGenerationalId getWorkGenerationalIdFromJobInfo(JobInfo jobInfo) {
        PersistableBundle extras = jobInfo.getExtras();
        if (extras != null) {
            try {
                if (extras.containsKey("EXTRA_WORK_SPEC_ID")) {
                    int generation = extras.getInt("EXTRA_WORK_SPEC_GENERATION", 0);
                    return new WorkGenerationalId(extras.getString("EXTRA_WORK_SPEC_ID"), generation);
                }
                return null;
            } catch (NullPointerException e) {
                return null;
            }
        }
        return null;
    }
}
