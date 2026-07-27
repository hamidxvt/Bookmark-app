package com.google.android.libraries.places.internal;

import android.os.Parcelable;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class zzfl implements Parcelable {
    public static zzfk zzm(AutocompleteActivityMode autocompleteActivityMode, List list, zzfj zzfjVar) {
        zzfb zzfbVar = new zzfb();
        zzfbVar.zza(new ArrayList());
        zzfbVar.zzf(autocompleteActivityMode);
        zzfbVar.zzh(list);
        zzfbVar.zzg(zzfjVar);
        zzfbVar.zzi(0);
        zzfbVar.zzj(0);
        return zzfbVar;
    }

    public abstract int zza();

    public abstract int zzb();

    public abstract LocationBias zzc();

    public abstract LocationRestriction zzd();

    public abstract TypeFilter zze();

    public abstract zzfj zzf();

    public abstract zzfk zzg();

    public abstract AutocompleteActivityMode zzh();

    public abstract zzhs zzi();

    public abstract zzhs zzj();

    public abstract String zzk();

    public abstract String zzl();
}
