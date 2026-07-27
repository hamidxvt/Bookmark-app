package com.ingenious.androidbookmarksalesupgrade.utils;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: Constant.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001:\u0006\u000e\u000f\u0010\u0011\u0012\u0013B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/utils/Constant;", "", "<init>", "()V", "VERSION", "", "SPLASH_TIME", "", "VISIT_ID", "MAP_ZOOM_LEVEL_16", "", Constant.ADDRESS, Constant.LATITUDE, Constant.LONGITUDE, "PreferenceKeys", "ErrorMessage", "RetrofitConstants", "IntentKeys", "PusherConstant", "BroadCastActions", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final class Constant {
    public static final String ADDRESS = "ADDRESS";
    public static final Constant INSTANCE = new Constant();
    public static final String LATITUDE = "LATITUDE";
    public static final String LONGITUDE = "LONGITUDE";
    public static final float MAP_ZOOM_LEVEL_16 = 16.0f;
    public static final long SPLASH_TIME = 3000;
    public static final String VERSION = "Version: 1.0";
    public static final String VISIT_ID = "id";

    private Constant() {
    }

    /* compiled from: Constant.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/utils/Constant$PreferenceKeys;", "", "<init>", "()V", PreferenceKeys.LOGIN_RESPONSE, "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class PreferenceKeys {
        public static final PreferenceKeys INSTANCE = new PreferenceKeys();
        public static final String LOGIN_RESPONSE = "LOGIN_RESPONSE";

        private PreferenceKeys() {
        }
    }

    /* compiled from: Constant.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/utils/Constant$ErrorMessage;", "", "<init>", "()V", "REQUIRED", "", "no_data_found", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class ErrorMessage {
        public static final ErrorMessage INSTANCE = new ErrorMessage();
        public static final String REQUIRED = "Required";
        public static final String no_data_found = "No Data Found";

        private ErrorMessage() {
        }
    }

    /* compiled from: Constant.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/utils/Constant$RetrofitConstants;", "", "<init>", "()V", "RETROFIT_METHOD_POST", "", "RETROFIT_METHOD_GET", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class RetrofitConstants {
        public static final RetrofitConstants INSTANCE = new RetrofitConstants();
        public static final String RETROFIT_METHOD_GET = "get";
        public static final String RETROFIT_METHOD_POST = "post";

        private RetrofitConstants() {
        }
    }

    /* compiled from: Constant.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/utils/Constant$IntentKeys;", "", "<init>", "()V", IntentKeys.LOCATION_ADDRESS, "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class IntentKeys {
        public static final IntentKeys INSTANCE = new IntentKeys();
        public static final String LOCATION_ADDRESS = "LOCATION_ADDRESS";

        private IntentKeys() {
        }
    }

    /* compiled from: Constant.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/utils/Constant$PusherConstant;", "", "<init>", "()V", "PUSHER_APP_ID", "", "PUSHER_KEY", "PUSHER_SECRET", "PUSHER_CLUSTER", "PUSHER_EVENT_NEW_JOB", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class PusherConstant {
        public static final PusherConstant INSTANCE = new PusherConstant();
        public static final String PUSHER_APP_ID = "1542638";
        public static final String PUSHER_CLUSTER = "us2";
        public static final String PUSHER_EVENT_NEW_JOB = "newMessage";
        public static final String PUSHER_KEY = "b2494e299320fa43c4ec";
        public static final String PUSHER_SECRET = "5da38bd85b1b28eb1c34";

        private PusherConstant() {
        }
    }

    /* compiled from: Constant.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/utils/Constant$BroadCastActions;", "", "<init>", "()V", "ON_GPS_ENABLED_CHANGE", "", "getON_GPS_ENABLED_CHANGE", "()Ljava/lang/String;", "ON_LOCATION_CHANGED", "getON_LOCATION_CHANGED", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class BroadCastActions {
        public static final BroadCastActions INSTANCE = new BroadCastActions();
        private static final String ON_GPS_ENABLED_CHANGE = "android.location.GPS_ENABLED_CHANGE";
        private static final String ON_LOCATION_CHANGED = "android.location.PROVIDERS_CHANGED";

        private BroadCastActions() {
        }

        public final String getON_GPS_ENABLED_CHANGE() {
            return ON_GPS_ENABLED_CHANGE;
        }

        public final String getON_LOCATION_CHANGED() {
            return ON_LOCATION_CHANGED;
        }
    }
}
