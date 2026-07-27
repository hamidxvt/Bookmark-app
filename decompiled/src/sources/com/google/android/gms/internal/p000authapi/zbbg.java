package com.google.android.gms.internal.p000authapi;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
/* loaded from: classes16.dex */
final class zbbg extends zbbe {
    private final zbbi zba;

    zbbg(zbbi zbbiVar, int i) {
        super(zbbiVar.size(), i);
        this.zba = zbbiVar;
    }

    @Override // com.google.android.gms.internal.p000authapi.zbbe
    protected final Object zba(int i) {
        return this.zba.get(i);
    }
}
