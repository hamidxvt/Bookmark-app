package com.google.android.libraries.places.internal;

import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class zzhw extends zzhp implements Set {

    @CheckForNull
    private transient zzhs zza;

    zzhw() {
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this || obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException e) {
            } catch (NullPointerException e2) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return zzim.zza(this);
    }

    @Override // com.google.android.libraries.places.internal.zzhp
    public zzhs zzd() {
        zzhs zzhsVar = this.zza;
        if (zzhsVar != null) {
            return zzhsVar;
        }
        zzhs zzh = zzh();
        this.zza = zzh;
        return zzh;
    }

    @Override // com.google.android.libraries.places.internal.zzhp, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: zze */
    public abstract zzip iterator();

    zzhs zzh() {
        return zzhs.zzi(toArray());
    }
}
