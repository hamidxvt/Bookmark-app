package com.ingenious.androidbookmarksalesupgrade.storage;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ingenious.androidbookmarksalesupgrade.model.LocationModel;
import com.ingenious.androidbookmarksalesupgrade.model.response.LoginResponse;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppPreferences.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R(\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR(\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/storage/AppPreferences;", "", "<init>", "()V", "loginData", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LoginResponse;", "getLoginData", "()Lcom/ingenious/androidbookmarksalesupgrade/model/response/LoginResponse;", "setLoginData", "(Lcom/ingenious/androidbookmarksalesupgrade/model/response/LoginResponse;)V", "userLocation", "Lcom/ingenious/androidbookmarksalesupgrade/model/LocationModel;", "getUserLocation", "()Lcom/ingenious/androidbookmarksalesupgrade/model/LocationModel;", "setUserLocation", "(Lcom/ingenious/androidbookmarksalesupgrade/model/LocationModel;)V", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes6.dex */
public final class AppPreferences {
    public static final AppPreferences INSTANCE = new AppPreferences();

    private AppPreferences() {
    }

    public final LoginResponse getLoginData() {
        String data = Prefs.getString(Constant.PreferenceKeys.LOGIN_RESPONSE, "");
        Intrinsics.checkNotNull(data);
        if (data.length() > 0) {
            return (LoginResponse) new Gson().fromJson(data, new TypeToken<LoginResponse>() { // from class: com.ingenious.androidbookmarksalesupgrade.storage.AppPreferences$loginData$1
            }.getType());
        }
        return null;
    }

    public final void setLoginData(LoginResponse loginData) {
        Prefs.putString(Constant.PreferenceKeys.LOGIN_RESPONSE, new Gson().toJson(loginData));
    }

    public final LocationModel getUserLocation() {
        String data = Prefs.getString("android.permission-group.LOCATION", "");
        Intrinsics.checkNotNull(data);
        if (data.length() > 0) {
            return (LocationModel) new Gson().fromJson(data, new TypeToken<LocationModel>() { // from class: com.ingenious.androidbookmarksalesupgrade.storage.AppPreferences$userLocation$1
            }.getType());
        }
        return null;
    }

    public final void setUserLocation(LocationModel userLocation) {
        Prefs.putString("android.permission-group.LOCATION", new Gson().toJson(userLocation));
    }
}
