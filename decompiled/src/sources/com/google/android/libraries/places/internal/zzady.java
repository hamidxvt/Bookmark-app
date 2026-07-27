package com.google.android.libraries.places.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzady extends zzacd implements RandomAccess, zzadz {
    public static final zzadz zza;
    private static final zzady zzb = new zzady(10);
    private final List zzc;

    static {
        zzb.zzb();
        zza = zzb;
    }

    public zzady() {
        this(10);
    }

    private static String zzi(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof zzacp ? ((zzacp) obj).zzm(zzads.zzb) : zzads.zzh((byte[]) obj);
    }

    @Override // com.google.android.libraries.places.internal.zzacd, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zza();
        this.zzc.add(i, (String) obj);
        this.modCount++;
    }

    @Override // com.google.android.libraries.places.internal.zzacd, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        zza();
        if (collection instanceof zzadz) {
            collection = ((zzadz) collection).zzh();
        }
        boolean addAll = this.zzc.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    @Override // com.google.android.libraries.places.internal.zzacd, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zza();
        this.zzc.clear();
        this.modCount++;
    }

    @Override // com.google.android.libraries.places.internal.zzacd, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zza();
        Object remove = this.zzc.remove(i);
        this.modCount++;
        return zzi(remove);
    }

    @Override // com.google.android.libraries.places.internal.zzacd, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zza();
        return zzi(this.zzc.set(i, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc.size();
    }

    @Override // com.google.android.libraries.places.internal.zzadz
    public final zzadz zzd() {
        return zzc() ? new zzafy(this) : this;
    }

    @Override // com.google.android.libraries.places.internal.zzadz
    public final Object zze(int i) {
        return this.zzc.get(i);
    }

    @Override // com.google.android.libraries.places.internal.zzadr
    public final /* bridge */ /* synthetic */ zzadr zzf(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzc);
        return new zzady(arrayList);
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: zzg, reason: merged with bridge method [inline-methods] */
    public final String get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzacp) {
            zzacp zzacpVar = (zzacp) obj;
            String zzm = zzacpVar.zzm(zzads.zzb);
            if (zzacpVar.zzi()) {
                this.zzc.set(i, zzm);
            }
            return zzm;
        }
        byte[] bArr = (byte[]) obj;
        String zzh = zzads.zzh(bArr);
        if (zzads.zzi(bArr)) {
            this.zzc.set(i, zzh);
        }
        return zzh;
    }

    @Override // com.google.android.libraries.places.internal.zzadz
    public final List zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    public zzady(int i) {
        this.zzc = new ArrayList(i);
    }

    private zzady(ArrayList arrayList) {
        this.zzc = arrayList;
    }

    @Override // com.google.android.libraries.places.internal.zzacd, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }
}
