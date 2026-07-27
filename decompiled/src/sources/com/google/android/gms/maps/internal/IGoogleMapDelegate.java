package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.FeatureLayerOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapColorScheme;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

/* compiled from: com.google.android.gms:play-services-maps@@19.2.0 */
/* loaded from: classes16.dex */
public interface IGoogleMapDelegate extends IInterface {
    com.google.android.gms.internal.maps.zzn addCircle(CircleOptions circleOptions) throws RemoteException;

    com.google.android.gms.internal.maps.zzx addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException;

    com.google.android.gms.internal.maps.zzaj addMarker(MarkerOptions markerOptions) throws RemoteException;

    void addOnMapCapabilitiesChangedListener(zzal zzalVar) throws RemoteException;

    com.google.android.gms.internal.maps.zzao addPolygon(PolygonOptions polygonOptions) throws RemoteException;

    com.google.android.gms.internal.maps.zzar addPolyline(PolylineOptions polylineOptions) throws RemoteException;

    com.google.android.gms.internal.maps.zzaw addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException;

    void animateCamera(IObjectWrapper iObjectWrapper) throws RemoteException;

    void animateCameraWithCallback(IObjectWrapper iObjectWrapper, zzd zzdVar) throws RemoteException;

    void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i, zzd zzdVar) throws RemoteException;

    void clear() throws RemoteException;

    CameraPosition getCameraPosition() throws RemoteException;

    com.google.android.gms.internal.maps.zzu getFeatureLayer(FeatureLayerOptions featureLayerOptions) throws RemoteException;

    com.google.android.gms.internal.maps.zzaa getFocusedBuilding() throws RemoteException;

    void getMapAsync(zzat zzatVar) throws RemoteException;

    com.google.android.gms.internal.maps.zzag getMapCapabilities() throws RemoteException;

    @MapColorScheme
    int getMapColorScheme() throws RemoteException;

    int getMapType() throws RemoteException;

    float getMaxZoomLevel() throws RemoteException;

    float getMinZoomLevel() throws RemoteException;

    Location getMyLocation() throws RemoteException;

    IProjectionDelegate getProjection() throws RemoteException;

    IUiSettingsDelegate getUiSettings() throws RemoteException;

    boolean isBuildingsEnabled() throws RemoteException;

    boolean isIndoorEnabled() throws RemoteException;

    boolean isMyLocationEnabled() throws RemoteException;

    boolean isTrafficEnabled() throws RemoteException;

    void moveCamera(IObjectWrapper iObjectWrapper) throws RemoteException;

    void onCreate(Bundle bundle) throws RemoteException;

    void onDestroy() throws RemoteException;

    void onEnterAmbient(Bundle bundle) throws RemoteException;

    void onExitAmbient() throws RemoteException;

    void onLowMemory() throws RemoteException;

    void onPause() throws RemoteException;

    void onResume() throws RemoteException;

    void onSaveInstanceState(Bundle bundle) throws RemoteException;

    void onStart() throws RemoteException;

    void onStop() throws RemoteException;

    void removeOnMapCapabilitiesChangedListener(zzal zzalVar) throws RemoteException;

    void resetMinMaxZoomPreference() throws RemoteException;

    void setBuildingsEnabled(boolean z) throws RemoteException;

    void setContentDescription(String str) throws RemoteException;

    boolean setIndoorEnabled(boolean z) throws RemoteException;

    void setInfoWindowAdapter(zzi zziVar) throws RemoteException;

    void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) throws RemoteException;

    void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) throws RemoteException;

    void setMapColorScheme(@MapColorScheme int i) throws RemoteException;

    boolean setMapStyle(MapStyleOptions mapStyleOptions) throws RemoteException;

    void setMapType(int i) throws RemoteException;

    void setMaxZoomPreference(float f) throws RemoteException;

    void setMinZoomPreference(float f) throws RemoteException;

    void setMyLocationEnabled(boolean z) throws RemoteException;

    void setOnCameraChangeListener(zzn zznVar) throws RemoteException;

    void setOnCameraIdleListener(zzp zzpVar) throws RemoteException;

    void setOnCameraMoveCanceledListener(zzr zzrVar) throws RemoteException;

    void setOnCameraMoveListener(zzt zztVar) throws RemoteException;

    void setOnCameraMoveStartedListener(zzv zzvVar) throws RemoteException;

    void setOnCircleClickListener(zzx zzxVar) throws RemoteException;

    void setOnGroundOverlayClickListener(zzz zzzVar) throws RemoteException;

    void setOnIndoorStateChangeListener(zzab zzabVar) throws RemoteException;

    void setOnInfoWindowClickListener(zzad zzadVar) throws RemoteException;

    void setOnInfoWindowCloseListener(zzaf zzafVar) throws RemoteException;

    void setOnInfoWindowLongClickListener(zzah zzahVar) throws RemoteException;

    void setOnMapClickListener(zzan zzanVar) throws RemoteException;

    void setOnMapLoadedCallback(zzap zzapVar) throws RemoteException;

    void setOnMapLongClickListener(zzar zzarVar) throws RemoteException;

    void setOnMarkerClickListener(zzav zzavVar) throws RemoteException;

    void setOnMarkerDragListener(zzax zzaxVar) throws RemoteException;

    void setOnMyLocationButtonClickListener(zzaz zzazVar) throws RemoteException;

    void setOnMyLocationChangeListener(zzbb zzbbVar) throws RemoteException;

    void setOnMyLocationClickListener(zzbd zzbdVar) throws RemoteException;

    void setOnPoiClickListener(zzbf zzbfVar) throws RemoteException;

    void setOnPolygonClickListener(zzbh zzbhVar) throws RemoteException;

    void setOnPolylineClickListener(zzbj zzbjVar) throws RemoteException;

    void setPadding(int i, int i2, int i3, int i4) throws RemoteException;

    void setTrafficEnabled(boolean z) throws RemoteException;

    void setWatermarkEnabled(boolean z) throws RemoteException;

    void snapshot(zzbw zzbwVar, IObjectWrapper iObjectWrapper) throws RemoteException;

    void snapshotForTest(zzbw zzbwVar) throws RemoteException;

    void stopAnimation() throws RemoteException;

    boolean useViewLifecycleWhenInFragment() throws RemoteException;
}
