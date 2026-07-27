package com.google.android.libraries.places.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class zzhs extends zzhp implements List, RandomAccess {
    private static final zziq zza = new zzhq(zzig.zza, 0);

    zzhs() {
    }

    static zzhs zzi(Object[] objArr) {
        return zzj(objArr, objArr.length);
    }

    static zzhs zzj(Object[] objArr, int i) {
        return i == 0 ? zzig.zza : new zzig(objArr, i);
    }

    public static zzhs zzk(Collection collection) {
        if (!(collection instanceof zzhp)) {
            Object[] array = collection.toArray();
            int length = array.length;
            zzic.zza(array, length);
            return zzj(array, length);
        }
        zzhs zzd = ((zzhp) collection).zzd();
        if (!zzd.zzf()) {
            return zzd;
        }
        Object[] array2 = zzd.toArray();
        return zzj(array2, array2.length);
    }

    public static zzhs zzl(Object[] objArr) {
        if (objArr.length == 0) {
            return zzig.zza;
        }
        Object[] objArr2 = (Object[]) objArr.clone();
        int length = objArr2.length;
        zzic.zza(objArr2, length);
        return zzj(objArr2, length);
    }

    public static zzhs zzm() {
        return zzig.zza;
    }

    public static zzhs zzn(Object obj) {
        Object[] objArr = {obj};
        zzic.zza(objArr, 1);
        return zzj(objArr, 1);
    }

    public static zzhs zzo(Object obj, Object obj2) {
        Object[] objArr = {obj, obj2};
        zzic.zza(objArr, 2);
        return zzj(objArr, 2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static zzhs zzp(Comparator comparator, Iterable iterable) {
        Object[] array = iterable.toArray();
        int length = array.length;
        zzic.zza(array, length);
        Arrays.sort(array, comparator);
        return zzj(array, length);
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.libraries.places.internal.zzhp, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List list = (List) obj;
        int size = size();
        if (size != list.size()) {
            return false;
        }
        if (list instanceof RandomAccess) {
            for (int i = 0; i < size; i++) {
                if (!zzgw.zza(get(i), list.get(i))) {
                    return false;
                }
            }
            return true;
        }
        Iterator it = iterator();
        Iterator it2 = list.iterator();
        while (it.hasNext()) {
            if (it2.hasNext() && zzgw.zza(it.next(), it2.next())) {
            }
            return false;
        }
        return !it2.hasNext();
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    @Override // java.util.List
    public final int indexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (obj.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.google.android.libraries.places.internal.zzhp, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final int lastIndexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final /* synthetic */ ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    @Deprecated
    public final Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.libraries.places.internal.zzhp
    int zza(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i2] = get(i2);
        }
        return size;
    }

    @Override // com.google.android.libraries.places.internal.zzhp
    @Deprecated
    public final zzhs zzd() {
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzhp
    /* renamed from: zze */
    public final zzip iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    /* renamed from: zzh, reason: merged with bridge method [inline-methods] */
    public zzhs subList(int i, int i2) {
        zzha.zzg(i, i2, size());
        int i3 = i2 - i;
        return i3 == size() ? this : i3 == 0 ? zzig.zza : new zzhr(this, i, i3);
    }

    @Override // java.util.List
    /* renamed from: zzq, reason: merged with bridge method [inline-methods] */
    public final zziq listIterator(int i) {
        zzha.zzb(i, size(), FirebaseAnalytics.Param.INDEX);
        return isEmpty() ? zza : new zzhq(this, i);
    }
}
