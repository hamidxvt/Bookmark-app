package com.google.android.gms.internal.p000authapi;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
/* loaded from: classes16.dex */
final class zbbh extends zbbi {
    final transient int zba;
    final transient int zbb;
    final /* synthetic */ zbbi zbc;

    zbbh(zbbi zbbiVar, int i, int i2) {
        Objects.requireNonNull(zbbiVar);
        this.zbc = zbbiVar;
        this.zba = i;
        this.zbb = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zbbc.zba(i, this.zbb, FirebaseAnalytics.Param.INDEX);
        return this.zbc.get(i + this.zba);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zbb;
    }

    @Override // com.google.android.gms.internal.p000authapi.zbbi, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    @Override // com.google.android.gms.internal.p000authapi.zbbf
    final Object[] zbb() {
        return this.zbc.zbb();
    }

    @Override // com.google.android.gms.internal.p000authapi.zbbf
    final int zbc() {
        return this.zbc.zbc() + this.zba;
    }

    @Override // com.google.android.gms.internal.p000authapi.zbbf
    final int zbd() {
        return this.zbc.zbc() + this.zba + this.zbb;
    }

    @Override // com.google.android.gms.internal.p000authapi.zbbf
    final boolean zbf() {
        return true;
    }

    @Override // com.google.android.gms.internal.p000authapi.zbbi
    /* renamed from: zbh */
    public final zbbi subList(int i, int i2) {
        zbbc.zbc(i, i2, this.zbb);
        int i3 = this.zba;
        return this.zbc.subList(i + i3, i2 + i3);
    }
}
