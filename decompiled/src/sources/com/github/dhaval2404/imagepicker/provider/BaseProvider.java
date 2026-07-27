package com.github.dhaval2404.imagepicker.provider;

import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Environment;
import com.github.dhaval2404.imagepicker.ImagePickerActivity;
import com.google.firebase.messaging.Constants;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\b\u0010\u000b\u001a\u00020\fH\u0014J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0004J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\nH\u0004J\b\u0010\u0016\u001a\u00020\fH\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0017"}, d2 = {"Lcom/github/dhaval2404/imagepicker/provider/BaseProvider;", "Landroid/content/ContextWrapper;", "activity", "Lcom/github/dhaval2404/imagepicker/ImagePickerActivity;", "(Lcom/github/dhaval2404/imagepicker/ImagePickerActivity;)V", "getActivity", "()Lcom/github/dhaval2404/imagepicker/ImagePickerActivity;", "getFileDir", "Ljava/io/File;", "path", "", "onFailure", "", "onRestoreInstanceState", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "setError", "errorRes", "", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "setResultCancel", "imagepicker_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public abstract class BaseProvider extends ContextWrapper {
    private final ImagePickerActivity activity;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseProvider(ImagePickerActivity activity) {
        super(activity);
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
    }

    protected final ImagePickerActivity getActivity() {
        return this.activity;
    }

    public final File getFileDir(String path) {
        if (path != null) {
            return new File(path);
        }
        File externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_DCIM);
        if (externalFilesDir == null) {
            externalFilesDir = this.activity.getFilesDir();
        }
        Intrinsics.checkNotNullExpressionValue(externalFilesDir, "getExternalFilesDir(Envi…CIM) ?: activity.filesDir");
        return externalFilesDir;
    }

    protected final void setError(String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        onFailure();
        this.activity.setError(error);
    }

    protected final void setError(int errorRes) {
        String string = getString(errorRes);
        Intrinsics.checkNotNullExpressionValue(string, "getString(errorRes)");
        setError(string);
    }

    protected final void setResultCancel() {
        onFailure();
        this.activity.setResultCancel();
    }

    protected void onFailure() {
    }

    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
    }
}
