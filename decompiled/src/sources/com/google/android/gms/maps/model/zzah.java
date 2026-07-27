package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzaz;

/* compiled from: com.google.android.gms:play-services-maps@@19.2.0 */
/* loaded from: classes16.dex */
final class zzah implements TileProvider {
    final /* synthetic */ TileOverlayOptions zza;
    private final zzaz zzb;

    zzah(TileOverlayOptions tileOverlayOptions) {
        zzaz zzazVar;
        this.zza = tileOverlayOptions;
        zzazVar = this.zza.zza;
        this.zzb = zzazVar;
    }

    @Override // com.google.android.gms.maps.model.TileProvider
    public final Tile getTile(int i, int i2, int i3) {
        try {
            return this.zzb.zzb(i, i2, i3);
        } catch (RemoteException e) {
            return null;
        }
    }
}
