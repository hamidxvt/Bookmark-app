package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzfb extends zzfk {
    private AutocompleteActivityMode zza;
    private zzhs zzb;
    private zzfj zzc;
    private String zzd;
    private String zze;
    private LocationBias zzf;
    private LocationRestriction zzg;
    private zzhs zzh;
    private TypeFilter zzi;
    private int zzj;
    private int zzk;
    private byte zzl;

    zzfb() {
    }

    /* synthetic */ zzfb(zzfl zzflVar, zzfa zzfaVar) {
        this.zza = zzflVar.zzh();
        this.zzb = zzflVar.zzj();
        this.zzc = zzflVar.zzf();
        this.zzd = zzflVar.zzl();
        this.zze = zzflVar.zzk();
        this.zzf = zzflVar.zzc();
        this.zzg = zzflVar.zzd();
        this.zzh = zzflVar.zzi();
        this.zzi = zzflVar.zze();
        this.zzj = zzflVar.zza();
        this.zzk = zzflVar.zzb();
        this.zzl = (byte) 3;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zza(List list) {
        this.zzh = zzhs.zzk(list);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zzb(String str) {
        this.zze = str;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zzc(String str) {
        this.zzd = str;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zzd(LocationBias locationBias) {
        this.zzf = locationBias;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zze(LocationRestriction locationRestriction) {
        this.zzg = locationRestriction;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zzf(AutocompleteActivityMode autocompleteActivityMode) {
        if (autocompleteActivityMode == null) {
            throw new NullPointerException("Null mode");
        }
        this.zza = autocompleteActivityMode;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zzg(zzfj zzfjVar) {
        if (zzfjVar == null) {
            throw new NullPointerException("Null origin");
        }
        this.zzc = zzfjVar;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zzh(List list) {
        this.zzb = zzhs.zzk(list);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zzi(int i) {
        this.zzj = i;
        this.zzl = (byte) (this.zzl | 1);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zzj(int i) {
        this.zzk = i;
        this.zzl = (byte) (this.zzl | 2);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zzk(TypeFilter typeFilter) {
        this.zzi = typeFilter;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfl zzl() {
        AutocompleteActivityMode autocompleteActivityMode;
        zzhs zzhsVar;
        zzfj zzfjVar;
        zzhs zzhsVar2;
        if (this.zzl == 3 && (autocompleteActivityMode = this.zza) != null && (zzhsVar = this.zzb) != null && (zzfjVar = this.zzc) != null && (zzhsVar2 = this.zzh) != null) {
            return new zzfe(autocompleteActivityMode, zzhsVar, zzfjVar, this.zzd, this.zze, this.zzf, this.zzg, zzhsVar2, this.zzi, this.zzj, this.zzk);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" mode");
        }
        if (this.zzb == null) {
            sb.append(" placeFields");
        }
        if (this.zzc == null) {
            sb.append(" origin");
        }
        if (this.zzh == null) {
            sb.append(" countries");
        }
        if ((this.zzl & 1) == 0) {
            sb.append(" primaryColor");
        }
        if ((this.zzl & 2) == 0) {
            sb.append(" primaryColorDark");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
