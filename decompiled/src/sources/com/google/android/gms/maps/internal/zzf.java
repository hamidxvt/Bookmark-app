package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;

/* compiled from: com.google.android.gms:play-services-maps@@19.2.0 */
/* loaded from: classes16.dex */
public interface zzf extends IInterface {
    int zzd() throws RemoteException;

    ICameraUpdateFactoryDelegate zze() throws RemoteException;

    IMapFragmentDelegate zzf(IObjectWrapper iObjectWrapper) throws RemoteException;

    IMapViewDelegate zzg(IObjectWrapper iObjectWrapper, GoogleMapOptions googleMapOptions) throws RemoteException;

    IStreetViewPanoramaFragmentDelegate zzh(IObjectWrapper iObjectWrapper) throws RemoteException;

    IStreetViewPanoramaViewDelegate zzi(IObjectWrapper iObjectWrapper, StreetViewPanoramaOptions streetViewPanoramaOptions) throws RemoteException;

    com.google.android.gms.internal.maps.zzk zzj() throws RemoteException;

    void zzk(IObjectWrapper iObjectWrapper, String str) throws RemoteException;

    void zzl(IObjectWrapper iObjectWrapper, int i) throws RemoteException;

    void zzm(IObjectWrapper iObjectWrapper, int i) throws RemoteException;

    void zzn(IObjectWrapper iObjectWrapper) throws RemoteException;
}
