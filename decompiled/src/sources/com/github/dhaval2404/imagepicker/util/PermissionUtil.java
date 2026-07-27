package com.github.dhaval2404.imagepicker.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PermissionUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nJ\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\tJ\u0016\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\t¨\u0006\r"}, d2 = {"Lcom/github/dhaval2404/imagepicker/util/PermissionUtil;", "", "()V", "isPermissionGranted", "", "context", "Landroid/content/Context;", "permissions", "", "", "(Landroid/content/Context;[Ljava/lang/String;)Z", "permission", "isPermissionInManifest", "imagepicker_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class PermissionUtil {
    public static final PermissionUtil INSTANCE = new PermissionUtil();

    private PermissionUtil() {
    }

    public final boolean isPermissionGranted(Context context, String permission) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(permission, "permission");
        int selfPermission = ContextCompat.checkSelfPermission(context, permission);
        return selfPermission == 0;
    }

    public final boolean isPermissionGranted(Context context, String[] permissions) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Collection destination$iv$iv = new ArrayList();
        for (String str : permissions) {
            if (INSTANCE.isPermissionGranted(context, str)) {
                destination$iv$iv.add(str);
            }
        }
        return ((List) destination$iv$iv).size() == permissions.length;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002d A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean isPermissionInManifest(Context context, String permission) {
        boolean z;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(permission, "permission");
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
        String[] permissions = packageInfo.requestedPermissions;
        if (permissions != null) {
            if (!(permissions.length == 0)) {
                z = false;
                if (!z) {
                    return false;
                }
                for (String perm : permissions) {
                    if (Intrinsics.areEqual(perm, permission)) {
                        return true;
                    }
                }
                return false;
            }
        }
        z = true;
        if (!z) {
        }
    }
}
