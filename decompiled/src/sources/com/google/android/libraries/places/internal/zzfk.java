package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class zzfk {
    public abstract zzfk zza(List list);

    public abstract zzfk zzb(String str);

    public abstract zzfk zzc(String str);

    public abstract zzfk zzd(LocationBias locationBias);

    public abstract zzfk zze(LocationRestriction locationRestriction);

    public abstract zzfk zzf(AutocompleteActivityMode autocompleteActivityMode);

    public abstract zzfk zzg(zzfj zzfjVar);

    public abstract zzfk zzh(List list);

    public abstract zzfk zzi(int i);

    public abstract zzfk zzj(int i);

    public abstract zzfk zzk(TypeFilter typeFilter);

    public abstract zzfl zzl();

    public final zzfk zzm(String str) {
        return zza(str == null ? zzhs.zzm() : zzhs.zzn(str));
    }
}
