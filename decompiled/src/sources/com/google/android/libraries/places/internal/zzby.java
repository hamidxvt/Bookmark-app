package com.google.android.libraries.places.internal;

import android.graphics.Bitmap;
import android.widget.ImageView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzby {
    private final RequestQueue zza;

    zzby(RequestQueue requestQueue) {
        this.zza = requestQueue;
    }

    static /* synthetic */ void zza(TaskCompletionSource taskCompletionSource, VolleyError volleyError) {
        ApiException zza;
        try {
            if (volleyError.networkResponse != null) {
                switch (volleyError.networkResponse.statusCode) {
                    case 400:
                        zza = new ApiException(new Status(PlacesStatusCodes.INVALID_REQUEST, "The provided parameters are invalid (did you include a max width or height?)."));
                        break;
                    case TypedValues.CycleType.TYPE_ALPHA /* 403 */:
                        zza = new ApiException(new Status(PlacesStatusCodes.REQUEST_DENIED, "The provided API key is invalid."));
                        break;
                }
                taskCompletionSource.trySetException(zza);
            }
            zza = zzbm.zza(volleyError);
            taskCompletionSource.trySetException(zza);
        } catch (Error | RuntimeException e) {
            zzev.zzb(e);
            throw e;
        }
    }

    static /* synthetic */ void zzc(zzcn zzcnVar, TaskCompletionSource taskCompletionSource, Bitmap bitmap) {
        try {
            zzcnVar.zzb(bitmap);
            taskCompletionSource.trySetResult(zzcnVar.zza());
        } catch (Error | RuntimeException e) {
            zzev.zzb(e);
            throw e;
        }
    }

    public final Task zzb(zzca zzcaVar, final zzcn zzcnVar) {
        String zzc = zzcaVar.zzc();
        Map zzd = zzcaVar.zzd();
        CancellationToken zza = zzcaVar.zza();
        final TaskCompletionSource taskCompletionSource = zza != null ? new TaskCompletionSource(zza) : new TaskCompletionSource();
        final byte[] bArr = null;
        final zzbx zzbxVar = new zzbx(this, zzc, new Response.Listener(taskCompletionSource, bArr) { // from class: com.google.android.libraries.places.internal.zzbv
            public final /* synthetic */ TaskCompletionSource zza;

            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                zzby.zzc(zzcn.this, this.zza, (Bitmap) obj);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, new Response.ErrorListener() { // from class: com.google.android.libraries.places.internal.zzbu
            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                zzby.zza(TaskCompletionSource.this, volleyError);
            }
        }, zzd);
        if (zza != null) {
            zza.onCanceledRequested(new OnTokenCanceledListener() { // from class: com.google.android.libraries.places.internal.zzbw
                @Override // com.google.android.gms.tasks.OnTokenCanceledListener
                public final void onCanceled() {
                    ImageRequest.this.cancel();
                }
            });
        }
        this.zza.add(zzbxVar);
        return taskCompletionSource.getTask();
    }
}
