package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
/* loaded from: classes16.dex */
public abstract class zza extends com.google.android.gms.internal.auth.zzb implements zzb {
    public zza() {
        super("com.google.android.gms.auth.account.IWorkAccountCallback");
    }

    @Override // com.google.android.gms.internal.auth.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                Account account = (Account) com.google.android.gms.internal.auth.zzc.zza(parcel, Account.CREATOR);
                com.google.android.gms.internal.auth.zzc.zzb(parcel);
                zzb(account);
                return true;
            case 2:
                int i3 = com.google.android.gms.internal.auth.zzc.zza;
                int readInt = parcel.readInt();
                com.google.android.gms.internal.auth.zzc.zzb(parcel);
                zzc(readInt != 0);
                return true;
            default:
                return false;
        }
    }
}
