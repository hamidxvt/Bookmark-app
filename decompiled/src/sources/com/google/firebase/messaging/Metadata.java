package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.firebase.FirebaseApp;
import java.util.List;

/* loaded from: classes16.dex */
class Metadata {
    private static final String ACTION_C2DM_REGISTER = "com.google.android.c2dm.intent.REGISTER";
    private static final String ACTION_IID_TOKEN_REQUEST = "com.google.iid.TOKEN_REQUEST";
    static final int GMSCORE_NOT_FOUND = 0;
    private static final String GMSCORE_SEND_PERMISSION = "com.google.android.c2dm.permission.SEND";
    static final String GMS_PACKAGE = "com.google.android.gms";
    static final int IID_VIA_RECEIVER = 2;
    static final int IID_VIA_SERVICE = 1;
    private String appVersionCode;
    private String appVersionName;
    private final Context context;
    private int gmsVersionCode;
    private int iidImplementation = 0;

    Metadata(Context context) {
        this.context = context;
    }

    boolean isGmscorePresent() {
        return getIidImplementation() != 0;
    }

    synchronized int getIidImplementation() {
        if (this.iidImplementation != 0) {
            return this.iidImplementation;
        }
        PackageManager pm = this.context.getPackageManager();
        int permissionState = pm.checkPermission(GMSCORE_SEND_PERMISSION, "com.google.android.gms");
        if (permissionState == -1) {
            Log.e(Constants.TAG, "Google Play services missing or without correct permission.");
            return 0;
        }
        if (!PlatformVersion.isAtLeastO()) {
            Intent intent = new Intent(ACTION_C2DM_REGISTER);
            intent.setPackage("com.google.android.gms");
            List<ResolveInfo> infos = pm.queryIntentServices(intent, 0);
            if (infos != null && infos.size() > 0) {
                this.iidImplementation = 1;
                return this.iidImplementation;
            }
        }
        Intent intent2 = new Intent(ACTION_IID_TOKEN_REQUEST);
        intent2.setPackage("com.google.android.gms");
        List<ResolveInfo> infos2 = pm.queryBroadcastReceivers(intent2, 0);
        if (infos2 != null && infos2.size() > 0) {
            this.iidImplementation = 2;
            return this.iidImplementation;
        }
        Log.w(Constants.TAG, "Failed to resolve IID implementation package, falling back");
        if (PlatformVersion.isAtLeastO()) {
            this.iidImplementation = 2;
        } else {
            this.iidImplementation = 1;
        }
        return this.iidImplementation;
    }

    static String getDefaultSenderId(FirebaseApp app) {
        String senderId = app.getOptions().getGcmSenderId();
        if (senderId != null) {
            return senderId;
        }
        String appId = app.getOptions().getApplicationId();
        if (!appId.startsWith("1:")) {
            return appId;
        }
        String[] parts = appId.split(":");
        if (parts.length < 2) {
            return null;
        }
        String projectId = parts[1];
        if (projectId.isEmpty()) {
            return null;
        }
        return projectId;
    }

    synchronized String getAppVersionCode() {
        if (this.appVersionCode == null) {
            populateAppVersionInfo();
        }
        return this.appVersionCode;
    }

    synchronized String getAppVersionName() {
        if (this.appVersionName == null) {
            populateAppVersionInfo();
        }
        return this.appVersionName;
    }

    synchronized int getGmsVersionCode() {
        PackageInfo info;
        if (this.gmsVersionCode == 0 && (info = getPackageInfo("com.google.android.gms")) != null) {
            this.gmsVersionCode = info.versionCode;
        }
        return this.gmsVersionCode;
    }

    private synchronized void populateAppVersionInfo() {
        PackageInfo info = getPackageInfo(this.context.getPackageName());
        if (info != null) {
            this.appVersionCode = Integer.toString(info.versionCode);
            this.appVersionName = info.versionName;
        }
    }

    private PackageInfo getPackageInfo(String packageName) {
        try {
            return this.context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(Constants.TAG, "Failed to find package " + e);
            return null;
        }
    }
}
