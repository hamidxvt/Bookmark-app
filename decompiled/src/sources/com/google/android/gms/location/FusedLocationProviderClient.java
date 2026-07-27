package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.os.SystemClock;
import android.os.WorkSource;
import androidx.work.WorkRequest;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.WorkSourceUtil;
import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes16.dex */
public class FusedLocationProviderClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    public static final String KEY_MOCK_LOCATION = "mockLocation";
    public static final String KEY_VERTICAL_ACCURACY = "verticalAccuracy";

    public FusedLocationProviderClient(Activity activity) {
        super(activity, LocationServices.API, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    private final Task zza(final com.google.android.gms.internal.location.zzbf zzbfVar, final ListenerHolder listenerHolder) {
        final zzaq zzaqVar = new zzaq(this, listenerHolder);
        return doRegisterEventListener(RegistrationMethods.builder().register(new RemoteCall() { // from class: com.google.android.gms.location.zzan
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                FusedLocationProviderClient fusedLocationProviderClient = FusedLocationProviderClient.this;
                zzaw zzawVar = zzaqVar;
                ListenerHolder listenerHolder2 = listenerHolder;
                ((com.google.android.gms.internal.location.zzbe) obj).zzB(zzbfVar, listenerHolder2, new zzau((TaskCompletionSource) obj2, new zzaf(fusedLocationProviderClient, zzawVar, listenerHolder2), null));
            }
        }).unregister(zzaqVar).withHolder(listenerHolder).setMethodKey(2436).build());
    }

    public Task<Void> flushLocations() {
        return doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.location.zzad
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                ((com.google.android.gms.internal.location.zzbe) obj).zzs(new zzav((TaskCompletionSource) obj2));
            }
        }).setMethodKey(2422).build());
    }

    public Task<Location> getCurrentLocation(int priority, final CancellationToken cancellationToken) {
        LocationRequest create = LocationRequest.create();
        create.setPriority(priority);
        create.setInterval(0L);
        create.setFastestInterval(0L);
        create.setExpirationDuration(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS);
        final com.google.android.gms.internal.location.zzbf zzc = com.google.android.gms.internal.location.zzbf.zzc(null, create);
        zzc.zzd(true);
        zzc.zze(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS);
        if (cancellationToken != null) {
            Preconditions.checkArgument(true ^ cancellationToken.isCancellationRequested(), "cancellationToken may not be already canceled");
        }
        Task doRead = doRead(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.location.zzaa
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                FusedLocationProviderClient fusedLocationProviderClient = FusedLocationProviderClient.this;
                com.google.android.gms.internal.location.zzbf zzbfVar = zzc;
                CancellationToken cancellationToken2 = cancellationToken;
                com.google.android.gms.internal.location.zzbe zzbeVar = (com.google.android.gms.internal.location.zzbe) obj;
                TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
                CurrentLocationRequest.Builder builder = new CurrentLocationRequest.Builder();
                builder.setPriority(zzbfVar.zzb().getPriority());
                builder.setDurationMillis(zzbfVar.zzb().getExpirationTime() != Long.MAX_VALUE ? zzbfVar.zzb().getExpirationTime() - SystemClock.elapsedRealtime() : Long.MAX_VALUE);
                builder.setMaxUpdateAgeMillis(zzbfVar.zza());
                builder.zza(zzbfVar.zzg());
                List<ClientIdentity> zzf = zzbfVar.zzf();
                WorkSource workSource = new WorkSource();
                for (ClientIdentity clientIdentity : zzf) {
                    WorkSourceUtil.add(workSource, clientIdentity.uid, clientIdentity.packageName);
                }
                builder.zzb(workSource);
                zzbeVar.zzt(builder.build(), cancellationToken2, new zzap(fusedLocationProviderClient, taskCompletionSource));
            }
        }).setMethodKey(2415).build());
        if (cancellationToken == null) {
            return doRead;
        }
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationToken);
        doRead.continueWith(new Continuation() { // from class: com.google.android.gms.location.zzag
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                TaskCompletionSource taskCompletionSource2 = TaskCompletionSource.this;
                String str = FusedLocationProviderClient.KEY_MOCK_LOCATION;
                if (task.isSuccessful()) {
                    taskCompletionSource2.trySetResult((Location) task.getResult());
                    return null;
                }
                taskCompletionSource2.trySetException((Exception) Preconditions.checkNotNull(task.getException()));
                return null;
            }
        });
        return taskCompletionSource.getTask();
    }

    public Task<Location> getLastLocation() {
        return doRead(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.location.zzak
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                ((com.google.android.gms.internal.location.zzbe) obj).zzu(new LastLocationRequest.Builder().build(), new zzat(FusedLocationProviderClient.this, (TaskCompletionSource) obj2));
            }
        }).setMethodKey(2414).build());
    }

    public Task<LocationAvailability> getLocationAvailability() {
        return doRead(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.location.zzae
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                String str = FusedLocationProviderClient.KEY_MOCK_LOCATION;
                ((TaskCompletionSource) obj2).setResult(((com.google.android.gms.internal.location.zzbe) obj).zzp());
            }
        }).setMethodKey(2416).build());
    }

    public Task<Void> removeLocationUpdates(final PendingIntent callbackIntent) {
        return doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.location.zzz
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                ((com.google.android.gms.internal.location.zzbe) obj).zzA(callbackIntent, new zzav((TaskCompletionSource) obj2));
            }
        }).setMethodKey(2418).build());
    }

    public Task<Void> requestLocationUpdates(LocationRequest request, final PendingIntent pendingIntent) {
        final com.google.android.gms.internal.location.zzbf zzc = com.google.android.gms.internal.location.zzbf.zzc(null, request);
        return doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.location.zzac
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                ((com.google.android.gms.internal.location.zzbe) obj).zzD(com.google.android.gms.internal.location.zzbf.this, pendingIntent, new zzav((TaskCompletionSource) obj2));
            }
        }).setMethodKey(2417).build());
    }

    public Task<Void> setMockLocation(final Location location) {
        Preconditions.checkArgument(location != null);
        return doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.location.zzal
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                com.google.android.gms.internal.location.zzbe zzbeVar = (com.google.android.gms.internal.location.zzbe) obj;
                zzbeVar.zzE(location, new zzas(FusedLocationProviderClient.this, (TaskCompletionSource) obj2));
            }
        }).setMethodKey(2421).build());
    }

    public Task<Void> setMockMode(final boolean mockMode) {
        return doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.location.zzab
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                com.google.android.gms.internal.location.zzbe zzbeVar = (com.google.android.gms.internal.location.zzbe) obj;
                zzbeVar.zzF(mockMode, new zzas(FusedLocationProviderClient.this, (TaskCompletionSource) obj2));
            }
        }).setMethodKey(2420).build());
    }

    public FusedLocationProviderClient(Context context) {
        super(context, LocationServices.API, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<Location> getLastLocation(final LastLocationRequest request) {
        return doRead(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.location.zzao
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                com.google.android.gms.internal.location.zzbe zzbeVar = (com.google.android.gms.internal.location.zzbe) obj;
                zzbeVar.zzu(request, new zzat(FusedLocationProviderClient.this, (TaskCompletionSource) obj2));
            }
        }).setMethodKey(2414).setFeatures(zzy.zzf).build());
    }

    public Task<Void> removeLocationUpdates(LocationCallback callback) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(callback, LocationCallback.class.getSimpleName()), 2418).continueWith(new Executor() { // from class: com.google.android.gms.location.zzaj
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                runnable.run();
            }
        }, new Continuation() { // from class: com.google.android.gms.location.zzai
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                String str = FusedLocationProviderClient.KEY_MOCK_LOCATION;
                return null;
            }
        });
    }

    public Task<Void> requestLocationUpdates(LocationRequest request, LocationCallback callback, Looper looper) {
        com.google.android.gms.internal.location.zzbf zzc = com.google.android.gms.internal.location.zzbf.zzc(null, request);
        if (looper != null || (looper = Looper.myLooper()) != null) {
            return zza(zzc, ListenerHolders.createListenerHolder(callback, looper, LocationCallback.class.getSimpleName()));
        }
        throw new IllegalStateException();
    }

    public Task<Void> requestLocationUpdates(LocationRequest request, Executor executor, LocationCallback callback) {
        return zza(com.google.android.gms.internal.location.zzbf.zzc(null, request), ListenerHolders.createListenerHolder(callback, executor, LocationCallback.class.getSimpleName()));
    }

    public Task<Location> getCurrentLocation(final CurrentLocationRequest request, final CancellationToken cancellationToken) {
        if (cancellationToken != null) {
            Preconditions.checkArgument(!cancellationToken.isCancellationRequested(), "cancellationToken may not be already canceled");
        }
        Task doRead = doRead(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.location.zzam
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                com.google.android.gms.internal.location.zzbe zzbeVar = (com.google.android.gms.internal.location.zzbe) obj;
                zzbeVar.zzt(request, cancellationToken, new zzat(FusedLocationProviderClient.this, (TaskCompletionSource) obj2));
            }
        }).setFeatures(zzy.zze).setMethodKey(2415).build());
        if (cancellationToken == null) {
            return doRead;
        }
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationToken);
        doRead.continueWith(new Continuation() { // from class: com.google.android.gms.location.zzah
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                TaskCompletionSource taskCompletionSource2 = TaskCompletionSource.this;
                String str = FusedLocationProviderClient.KEY_MOCK_LOCATION;
                if (task.isSuccessful()) {
                    taskCompletionSource2.trySetResult((Location) task.getResult());
                    return null;
                }
                taskCompletionSource2.trySetException((Exception) Preconditions.checkNotNull(task.getException()));
                return null;
            }
        });
        return taskCompletionSource.getTask();
    }
}
