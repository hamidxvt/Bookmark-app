package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzfz extends com.google.android.gms.internal.measurement.zzbl implements zzgb {
    zzfz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzA(zzr zzrVar) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        zzc(27, zza);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzB(zzr zzrVar, zzoo zzooVar, zzgh zzghVar) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzooVar);
        com.google.android.gms.internal.measurement.zzbn.zzd(zza, zzghVar);
        zzc(29, zza);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzC(zzr zzrVar, zzaf zzafVar) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzafVar);
        zzc(30, zza);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzD(zzr zzrVar, Bundle bundle, zzge zzgeVar) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, bundle);
        com.google.android.gms.internal.measurement.zzbn.zzd(zza, zzgeVar);
        zzc(31, zza);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zze(zzbg zzbgVar, zzr zzrVar) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzbgVar);
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        zzc(1, zza);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzf(zzpl zzplVar, zzr zzrVar) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzplVar);
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        zzc(2, zza);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzg(zzr zzrVar) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        zzc(4, zza);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzh(zzbg zzbgVar, String str, String str2) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzi(zzr zzrVar) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        zzc(6, zza);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List zzj(zzr zzrVar, boolean z) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        zza.writeInt(z ? 1 : 0);
        Parcel zzP = zzP(7, zza);
        ArrayList createTypedArrayList = zzP.createTypedArrayList(zzpl.CREATOR);
        zzP.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final byte[] zzk(zzbg zzbgVar, String str) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzbgVar);
        zza.writeString(str);
        Parcel zzP = zzP(9, zza);
        byte[] createByteArray = zzP.createByteArray();
        zzP.recycle();
        return createByteArray;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzl(long j, String str, String str2, String str3) throws RemoteException {
        Parcel zza = zza();
        zza.writeLong(j);
        zza.writeString(str);
        zza.writeString(str2);
        zza.writeString(str3);
        zzc(10, zza);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final String zzm(zzr zzrVar) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        Parcel zzP = zzP(11, zza);
        String readString = zzP.readString();
        zzP.recycle();
        return readString;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzn(zzah zzahVar, zzr zzrVar) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzahVar);
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        zzc(12, zza);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzo(zzah zzahVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List zzp(String str, String str2, boolean z, zzr zzrVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        int i = com.google.android.gms.internal.measurement.zzbn.zza;
        zza.writeInt(z ? 1 : 0);
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        Parcel zzP = zzP(14, zza);
        ArrayList createTypedArrayList = zzP.createTypedArrayList(zzpl.CREATOR);
        zzP.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List zzq(String str, String str2, String str3, boolean z) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(null);
        zza.writeString(str2);
        zza.writeString(str3);
        int i = com.google.android.gms.internal.measurement.zzbn.zza;
        zza.writeInt(z ? 1 : 0);
        Parcel zzP = zzP(15, zza);
        ArrayList createTypedArrayList = zzP.createTypedArrayList(zzpl.CREATOR);
        zzP.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List zzr(String str, String str2, zzr zzrVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        Parcel zzP = zzP(16, zza);
        ArrayList createTypedArrayList = zzP.createTypedArrayList(zzah.CREATOR);
        zzP.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List zzs(String str, String str2, String str3) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(null);
        zza.writeString(str2);
        zza.writeString(str3);
        Parcel zzP = zzP(17, zza);
        ArrayList createTypedArrayList = zzP.createTypedArrayList(zzah.CREATOR);
        zzP.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzt(zzr zzrVar) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        zzc(18, zza);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzu(Bundle bundle, zzr zzrVar) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, bundle);
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        zzc(19, zza);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzv(zzr zzrVar) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        zzc(20, zza);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final zzao zzw(zzr zzrVar) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        Parcel zzP = zzP(21, zza);
        zzao zzaoVar = (zzao) com.google.android.gms.internal.measurement.zzbn.zzb(zzP, zzao.CREATOR);
        zzP.recycle();
        return zzaoVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List zzx(zzr zzrVar, Bundle bundle) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzy(zzr zzrVar) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        zzc(25, zza);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzz(zzr zzrVar) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.measurement.zzbn.zzc(zza, zzrVar);
        zzc(26, zza);
    }
}
