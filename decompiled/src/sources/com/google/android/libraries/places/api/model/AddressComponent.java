package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.libraries.places.internal.zzha;
import com.google.android.libraries.places.internal.zzhs;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class AddressComponent implements Parcelable {

    /* compiled from: com.google.android.libraries.places:places@@2.6.0 */
    public static abstract class Builder {
        public AddressComponent build() {
            AddressComponent zzc = zzc();
            zzha.zzi(!zzc.getName().isEmpty(), "Name must not be empty.");
            List<String> types = zzc.getTypes();
            Iterator<String> it = types.iterator();
            while (it.hasNext()) {
                zzha.zzi(!TextUtils.isEmpty(it.next()), "Types must not contain null or empty values.");
            }
            zzb(zzhs.zzk(types));
            return zzc();
        }

        public abstract String getShortName();

        public abstract Builder setShortName(String str);

        abstract Builder zzb(List list);

        abstract AddressComponent zzc();
    }

    public static Builder builder(String name, List<String> list) {
        zza zzaVar = new zza();
        zzaVar.zza(name);
        zzaVar.zzb(list);
        return zzaVar;
    }

    public abstract String getName();

    public abstract String getShortName();

    public abstract List<String> getTypes();
}
