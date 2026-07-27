package com.github.dhaval2404.imagepicker.provider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.dhaval2404.imagepicker.ImagePickerActivity;
import com.github.dhaval2404.imagepicker.R;
import com.github.dhaval2404.imagepicker.util.FileUtil;
import com.github.dhaval2404.imagepicker.util.IntentUtils;
import com.github.dhaval2404.imagepicker.util.PermissionUtil;
import com.google.firebase.messaging.Constants;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 $2\u00020\u0001:\u0001$B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0002J\u0006\u0010\n\u001a\u00020\tJ\u001b\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002¢\u0006\u0002\u0010\u0010J\b\u0010\u0011\u001a\u00020\tH\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J \u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\b\u0010\u001a\u001a\u00020\tH\u0014J\u000e\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016J\u0012\u0010\u001c\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001eH\u0016J\b\u0010!\u001a\u00020\tH\u0002J\b\u0010\"\u001a\u00020\tH\u0002J\u0006\u0010#\u001a\u00020\tR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/github/dhaval2404/imagepicker/provider/CameraProvider;", "Lcom/github/dhaval2404/imagepicker/provider/BaseProvider;", "activity", "Lcom/github/dhaval2404/imagepicker/ImagePickerActivity;", "(Lcom/github/dhaval2404/imagepicker/ImagePickerActivity;)V", "mCameraFile", "Ljava/io/File;", "mFileDir", "checkPermission", "", "delete", "getRequiredPermission", "", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)[Ljava/lang/String;", "handleResult", "isPermissionGranted", "", "onActivityResult", "requestCode", "", "resultCode", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Landroid/content/Intent;", "onFailure", "onRequestPermissionsResult", "onRestoreInstanceState", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "requestPermission", "startCameraIntent", "startIntent", "Companion", "imagepicker_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class CameraProvider extends BaseProvider {
    private static final int CAMERA_INTENT_REQ_CODE = 4281;
    private static final int PERMISSION_INTENT_REQ_CODE = 4282;
    private static final String STATE_CAMERA_FILE = "state.camera_file";
    private File mCameraFile;
    private final File mFileDir;
    private static final String[] REQUIRED_PERMISSIONS = {"android.permission.CAMERA"};

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraProvider(ImagePickerActivity activity) {
        super(activity);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intent intent = activity.getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "activity.intent");
        Bundle bundle = intent.getExtras();
        bundle = bundle == null ? new Bundle() : bundle;
        Intrinsics.checkNotNullExpressionValue(bundle, "activity.intent.extras ?: Bundle()");
        String fileDir = bundle.getString(ImagePicker.EXTRA_SAVE_DIRECTORY);
        this.mFileDir = getFileDir(fileDir);
    }

    @Override // com.github.dhaval2404.imagepicker.provider.BaseProvider
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putSerializable(STATE_CAMERA_FILE, this.mCameraFile);
    }

    @Override // com.github.dhaval2404.imagepicker.provider.BaseProvider
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        this.mCameraFile = (File) (savedInstanceState != null ? savedInstanceState.getSerializable(STATE_CAMERA_FILE) : null);
    }

    public final void startIntent() {
        if (!IntentUtils.isCameraAppAvailable(this)) {
            setError(R.string.error_camera_app_not_found);
        } else {
            checkPermission();
        }
    }

    private final void checkPermission() {
        if (isPermissionGranted(this)) {
            startCameraIntent();
        } else {
            requestPermission();
        }
    }

    private final void startCameraIntent() {
        File file = FileUtil.getImageFile$default(FileUtil.INSTANCE, this.mFileDir, null, 2, null);
        this.mCameraFile = file;
        if (file != null && file.exists()) {
            Intent cameraIntent = IntentUtils.getCameraIntent(this, file);
            getActivity().startActivityForResult(cameraIntent, CAMERA_INTENT_REQ_CODE);
        } else {
            setError(R.string.error_failed_to_create_camera_image_file);
        }
    }

    private final void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), getRequiredPermission(getActivity()), PERMISSION_INTENT_REQ_CODE);
    }

    private final boolean isPermissionGranted(Context context) {
        for (String str : getRequiredPermission(context)) {
            if (!PermissionUtil.INSTANCE.isPermissionGranted(context, str)) {
                return false;
            }
        }
        return true;
    }

    private final String[] getRequiredPermission(Context context) {
        String[] strArr = REQUIRED_PERMISSIONS;
        Collection destination$iv$iv = new ArrayList();
        for (String str : strArr) {
            if (PermissionUtil.INSTANCE.isPermissionInManifest(context, str)) {
                destination$iv$iv.add(str);
            }
        }
        Collection $this$toTypedArray$iv = (List) destination$iv$iv;
        Object[] array = $this$toTypedArray$iv.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final void onRequestPermissionsResult(int requestCode) {
        if (requestCode == PERMISSION_INTENT_REQ_CODE) {
            if (isPermissionGranted(this)) {
                startIntent();
                return;
            }
            String error = getString(R.string.permission_camera_denied);
            Intrinsics.checkNotNullExpressionValue(error, "getString(R.string.permission_camera_denied)");
            setError(error);
        }
    }

    public final void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_INTENT_REQ_CODE) {
            if (resultCode == -1) {
                handleResult();
            } else {
                setResultCancel();
            }
        }
    }

    private final void handleResult() {
        ImagePickerActivity activity = getActivity();
        Uri fromFile = Uri.fromFile(this.mCameraFile);
        Intrinsics.checkNotNullExpressionValue(fromFile, "Uri.fromFile(mCameraFile)");
        activity.setImage(fromFile);
    }

    @Override // com.github.dhaval2404.imagepicker.provider.BaseProvider
    protected void onFailure() {
        delete();
    }

    public final void delete() {
        File file = this.mCameraFile;
        if (file != null) {
            file.delete();
        }
        this.mCameraFile = null;
    }
}
