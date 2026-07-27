package com.ingenious.androidbookmarksalesupgrade.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.util.CollectionUtils;
import com.ingenious.androidbookmarksalesupgrade.model.LocationModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: ReverseGeoCodeUtils.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/utils/ReverseGeoCodeUtils;", "", "<init>", "()V", "TAG", "", "execute", "", "context", "Landroid/content/Context;", "latitude", "longitude", "callback", "Lcom/ingenious/androidbookmarksalesupgrade/utils/LoadDataCallback;", "Lcom/ingenious/androidbookmarksalesupgrade/model/LocationModel;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final class ReverseGeoCodeUtils {
    public static final ReverseGeoCodeUtils INSTANCE = new ReverseGeoCodeUtils();
    private static final String TAG;

    private ReverseGeoCodeUtils() {
    }

    static {
        String simpleName = ReverseGeoCodeUtils.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        TAG = simpleName;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [T, com.ingenious.androidbookmarksalesupgrade.model.LocationModel] */
    public final void execute(final Context context, final String latitude, final String longitude, final LoadDataCallback<LocationModel> callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(latitude, "latitude");
        Intrinsics.checkNotNullParameter(longitude, "longitude");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final Ref.ObjectRef address = new Ref.ObjectRef();
        address.element = "";
        final Ref.ObjectRef cityName = new Ref.ObjectRef();
        cityName.element = "";
        final Ref.ObjectRef areaName = new Ref.ObjectRef();
        areaName.element = "";
        final Ref.ObjectRef countryCode = new Ref.ObjectRef();
        countryCode.element = "";
        final Ref.ObjectRef locationModel = new Ref.ObjectRef();
        locationModel.element = new LocationModel();
        Observable.fromCallable(new Callable() { // from class: com.ingenious.androidbookmarksalesupgrade.utils.ReverseGeoCodeUtils$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                LocationModel execute$lambda$4;
                execute$lambda$4 = ReverseGeoCodeUtils.execute$lambda$4(context, latitude, longitude, address, locationModel, cityName, areaName, countryCode);
                return execute$lambda$4;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<LocationModel>() { // from class: com.ingenious.androidbookmarksalesupgrade.utils.ReverseGeoCodeUtils$execute$2
            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // io.reactivex.Observer
            public void onNext(LocationModel model) {
                Intrinsics.checkNotNullParameter(model, "model");
                callback.onDataLoaded(model);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                LoadDataCallback<LocationModel> loadDataCallback = callback;
                String message = e.getMessage();
                Intrinsics.checkNotNull(message);
                loadDataCallback.onDataNotAvailable(1, message);
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v12, types: [T, com.ingenious.androidbookmarksalesupgrade.model.LocationModel] */
    /* JADX WARN: Type inference failed for: r2v13, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v14, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v15, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v16, types: [T, java.lang.String] */
    public static final LocationModel execute$lambda$4(Context $context, String $latitude, String $longitude, Ref.ObjectRef $address, Ref.ObjectRef $locationModel, Ref.ObjectRef $cityName, Ref.ObjectRef $areaName, Ref.ObjectRef $countryCode) {
        try {
            Geocoder geocoder = new Geocoder($context, Locale.ENGLISH);
            List addresses = geocoder.getFromLocation(Double.parseDouble($latitude), Double.parseDouble($longitude), 1);
            Intrinsics.checkNotNull(addresses);
            if (!CollectionUtils.isEmpty(addresses)) {
                Address fetchedAddress = addresses.get(0);
                if (fetchedAddress.getMaxAddressLineIndex() > -1) {
                    $address.element = fetchedAddress.getAddressLine(0);
                    ?? locality = fetchedAddress.getLocality();
                    if (locality != 0) {
                        $cityName.element = locality;
                    }
                    ?? subLocality = fetchedAddress.getSubLocality();
                    if (subLocality != 0) {
                        $areaName.element = subLocality;
                    }
                    ?? countryCode = fetchedAddress.getCountryCode();
                    if (countryCode != 0) {
                        $countryCode.element = countryCode;
                    }
                }
                ?? locationModel = new LocationModel();
                locationModel.setLocationAddress((String) $address.element);
                locationModel.setLocationCityName((String) $cityName.element);
                locationModel.setLocationAreaName((String) $areaName.element);
                locationModel.setLocationCountryCode((String) $countryCode.element);
                $locationModel.element = locationModel;
            }
            return (LocationModel) $locationModel.element;
        } catch (Exception e) {
            Log.e(TAG, "Geocoding getFromLocation Failed");
            e.printStackTrace();
            return (LocationModel) $locationModel.element;
        }
    }
}
