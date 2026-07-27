package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public abstract class zzga extends com.google.android.gms.internal.measurement.zzbm implements zzgb {
    public zzga() {
        super("com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override // com.google.android.gms.internal.measurement.zzbm
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzgh zzghVar = null;
        zzge zzgeVar = null;
        switch (i) {
            case 1:
                zzbg zzbgVar = (zzbg) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzbg.CREATOR);
                zzr zzrVar = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                zze(zzbgVar, zzrVar);
                parcel2.writeNoException();
                return true;
            case 2:
                zzpl zzplVar = (zzpl) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzpl.CREATOR);
                zzr zzrVar2 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                zzf(zzplVar, zzrVar2);
                parcel2.writeNoException();
                return true;
            case 3:
            case 8:
            case 22:
            case 23:
            case 28:
            default:
                return false;
            case 4:
                zzr zzrVar3 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                zzg(zzrVar3);
                parcel2.writeNoException();
                return true;
            case 5:
                zzbg zzbgVar2 = (zzbg) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzbg.CREATOR);
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                zzh(zzbgVar2, readString, readString2);
                parcel2.writeNoException();
                return true;
            case 6:
                zzr zzrVar4 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                zzi(zzrVar4);
                parcel2.writeNoException();
                return true;
            case 7:
                zzr zzrVar5 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                boolean zza = com.google.android.gms.internal.measurement.zzbn.zza(parcel);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                List zzj = zzj(zzrVar5, zza);
                parcel2.writeNoException();
                parcel2.writeTypedList(zzj);
                return true;
            case 9:
                zzbg zzbgVar3 = (zzbg) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzbg.CREATOR);
                String readString3 = parcel.readString();
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                byte[] zzk = zzk(zzbgVar3, readString3);
                parcel2.writeNoException();
                parcel2.writeByteArray(zzk);
                return true;
            case 10:
                long readLong = parcel.readLong();
                String readString4 = parcel.readString();
                String readString5 = parcel.readString();
                String readString6 = parcel.readString();
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                zzl(readLong, readString4, readString5, readString6);
                parcel2.writeNoException();
                return true;
            case 11:
                zzr zzrVar6 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                String zzm = zzm(zzrVar6);
                parcel2.writeNoException();
                parcel2.writeString(zzm);
                return true;
            case 12:
                zzah zzahVar = (zzah) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzah.CREATOR);
                zzr zzrVar7 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                zzn(zzahVar, zzrVar7);
                parcel2.writeNoException();
                return true;
            case 13:
                zzah zzahVar2 = (zzah) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzah.CREATOR);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                zzo(zzahVar2);
                parcel2.writeNoException();
                return true;
            case 14:
                String readString7 = parcel.readString();
                String readString8 = parcel.readString();
                boolean zza2 = com.google.android.gms.internal.measurement.zzbn.zza(parcel);
                zzr zzrVar8 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                List zzp = zzp(readString7, readString8, zza2, zzrVar8);
                parcel2.writeNoException();
                parcel2.writeTypedList(zzp);
                return true;
            case 15:
                String readString9 = parcel.readString();
                String readString10 = parcel.readString();
                String readString11 = parcel.readString();
                boolean zza3 = com.google.android.gms.internal.measurement.zzbn.zza(parcel);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                List zzq = zzq(readString9, readString10, readString11, zza3);
                parcel2.writeNoException();
                parcel2.writeTypedList(zzq);
                return true;
            case 16:
                String readString12 = parcel.readString();
                String readString13 = parcel.readString();
                zzr zzrVar9 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                List zzr = zzr(readString12, readString13, zzrVar9);
                parcel2.writeNoException();
                parcel2.writeTypedList(zzr);
                return true;
            case 17:
                String readString14 = parcel.readString();
                String readString15 = parcel.readString();
                String readString16 = parcel.readString();
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                List zzs = zzs(readString14, readString15, readString16);
                parcel2.writeNoException();
                parcel2.writeTypedList(zzs);
                return true;
            case 18:
                zzr zzrVar10 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                zzt(zzrVar10);
                parcel2.writeNoException();
                return true;
            case 19:
                Bundle bundle = (Bundle) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, Bundle.CREATOR);
                zzr zzrVar11 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                zzu(bundle, zzrVar11);
                parcel2.writeNoException();
                return true;
            case 20:
                zzr zzrVar12 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                zzv(zzrVar12);
                parcel2.writeNoException();
                return true;
            case 21:
                zzr zzrVar13 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                zzao zzw = zzw(zzrVar13);
                parcel2.writeNoException();
                if (zzw == null) {
                    parcel2.writeInt(0);
                } else {
                    parcel2.writeInt(1);
                    zzw.writeToParcel(parcel2, 1);
                }
                return true;
            case 24:
                zzr zzrVar14 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                Bundle bundle2 = (Bundle) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, Bundle.CREATOR);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                List zzx = zzx(zzrVar14, bundle2);
                parcel2.writeNoException();
                parcel2.writeTypedList(zzx);
                return true;
            case 25:
                zzr zzrVar15 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                zzy(zzrVar15);
                parcel2.writeNoException();
                return true;
            case 26:
                zzr zzrVar16 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                zzz(zzrVar16);
                parcel2.writeNoException();
                return true;
            case 27:
                zzr zzrVar17 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                zzA(zzrVar17);
                parcel2.writeNoException();
                return true;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                zzr zzrVar18 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                zzoo zzooVar = (zzoo) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzoo.CREATOR);
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.measurement.internal.IUploadBatchesCallback");
                    zzghVar = queryLocalInterface instanceof zzgh ? (zzgh) queryLocalInterface : new zzgf(readStrongBinder);
                }
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                zzB(zzrVar18, zzooVar, zzghVar);
                parcel2.writeNoException();
                return true;
            case 30:
                zzr zzrVar19 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                zzaf zzafVar = (zzaf) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzaf.CREATOR);
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                zzC(zzrVar19, zzafVar);
                parcel2.writeNoException();
                return true;
            case 31:
                zzr zzrVar20 = (zzr) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, zzr.CREATOR);
                Bundle bundle3 = (Bundle) com.google.android.gms.internal.measurement.zzbn.zzb(parcel, Bundle.CREATOR);
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.measurement.internal.ITriggerUrisCallback");
                    zzgeVar = queryLocalInterface2 instanceof zzge ? (zzge) queryLocalInterface2 : new zzgc(readStrongBinder2);
                }
                com.google.android.gms.internal.measurement.zzbn.zzf(parcel);
                zzD(zzrVar20, bundle3, zzgeVar);
                parcel2.writeNoException();
                return true;
        }
    }
}
