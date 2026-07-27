package com.github.dhaval2404.imagepicker.provider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.dhaval2404.imagepicker.ImagePickerActivity;
import com.github.dhaval2404.imagepicker.R;
import com.github.dhaval2404.imagepicker.util.FileUtil;
import com.google.firebase.messaging.Constants;
import com.yalantis.ucrop.UCrop;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CropProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 $2\u00020\u0001:\u0001$B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\u0011J\u0012\u0010\u0015\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u000bH\u0002J\u0006\u0010\u0017\u001a\u00020\u0006J \u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cJ\b\u0010\u001d\u001a\u00020\u0011H\u0014J\u0012\u0010\u001e\u001a\u00020\u00112\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0010\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020 H\u0016J\u000e\u0010#\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/github/dhaval2404/imagepicker/provider/CropProvider;", "Lcom/github/dhaval2404/imagepicker/provider/BaseProvider;", "activity", "Lcom/github/dhaval2404/imagepicker/ImagePickerActivity;", "(Lcom/github/dhaval2404/imagepicker/ImagePickerActivity;)V", "mCrop", "", "mCropAspectX", "", "mCropAspectY", "mCropImageFile", "Ljava/io/File;", "mFileDir", "mMaxHeight", "", "mMaxWidth", "cropImage", "", "uri", "Landroid/net/Uri;", "delete", "handleResult", "file", "isCropEnabled", "onActivityResult", "requestCode", "resultCode", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Landroid/content/Intent;", "onFailure", "onRestoreInstanceState", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "startIntent", "Companion", "imagepicker_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class CropProvider extends BaseProvider {
    private static final String STATE_CROP_FILE = "state.crop_file";
    private final boolean mCrop;
    private final float mCropAspectX;
    private final float mCropAspectY;
    private File mCropImageFile;
    private final File mFileDir;
    private final int mMaxHeight;
    private final int mMaxWidth;
    private static final String TAG = CropProvider.class.getSimpleName();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CropProvider(ImagePickerActivity activity) {
        super(activity);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intent intent = activity.getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "activity.intent");
        Bundle bundle = intent.getExtras();
        bundle = bundle == null ? new Bundle() : bundle;
        Intrinsics.checkNotNullExpressionValue(bundle, "activity.intent.extras ?: Bundle()");
        this.mMaxWidth = bundle.getInt(ImagePicker.EXTRA_MAX_WIDTH, 0);
        this.mMaxHeight = bundle.getInt(ImagePicker.EXTRA_MAX_HEIGHT, 0);
        this.mCrop = bundle.getBoolean(ImagePicker.EXTRA_CROP, false);
        this.mCropAspectX = bundle.getFloat(ImagePicker.EXTRA_CROP_X, 0.0f);
        this.mCropAspectY = bundle.getFloat(ImagePicker.EXTRA_CROP_Y, 0.0f);
        String fileDir = bundle.getString(ImagePicker.EXTRA_SAVE_DIRECTORY);
        this.mFileDir = getFileDir(fileDir);
    }

    @Override // com.github.dhaval2404.imagepicker.provider.BaseProvider
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putSerializable(STATE_CROP_FILE, this.mCropImageFile);
    }

    @Override // com.github.dhaval2404.imagepicker.provider.BaseProvider
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        this.mCropImageFile = (File) (savedInstanceState != null ? savedInstanceState.getSerializable(STATE_CROP_FILE) : null);
    }

    /* renamed from: isCropEnabled, reason: from getter */
    public final boolean getMCrop() {
        return this.mCrop;
    }

    public final void startIntent(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        cropImage(uri);
    }

    private final void cropImage(Uri uri) throws IOException {
        String extension = FileUtil.INSTANCE.getImageExtension(uri);
        this.mCropImageFile = FileUtil.INSTANCE.getImageFile(this.mFileDir, extension);
        if (this.mCropImageFile != null) {
            File file = this.mCropImageFile;
            Intrinsics.checkNotNull(file);
            if (file.exists()) {
                UCrop.Options options = new UCrop.Options();
                options.setCompressionFormat(FileUtil.INSTANCE.getCompressFormat(extension));
                UCrop uCrop = UCrop.of(uri, Uri.fromFile(this.mCropImageFile)).withOptions(options);
                float f = 0;
                if (this.mCropAspectX > f && this.mCropAspectY > f) {
                    uCrop.withAspectRatio(this.mCropAspectX, this.mCropAspectY);
                }
                if (this.mMaxWidth > 0 && this.mMaxHeight > 0) {
                    uCrop.withMaxResultSize(this.mMaxWidth, this.mMaxHeight);
                }
                try {
                    uCrop.start(getActivity(), 69);
                    return;
                } catch (ActivityNotFoundException ex) {
                    setError("uCrop not specified in manifest file.Add UCropActivity in Manifest<activity\n    android:name=\"com.yalantis.ucrop.UCropActivity\"\n    android:screenOrientation=\"portrait\"\n    android:theme=\"@style/Theme.AppCompat.Light.NoActionBar\"/>");
                    ex.printStackTrace();
                    return;
                }
            }
        }
        Log.e(TAG, "Failed to create crop image file");
        setError(R.string.error_failed_to_crop_image);
    }

    public final void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 69) {
            if (resultCode == -1) {
                handleResult(this.mCropImageFile);
            } else {
                setResultCancel();
            }
        }
    }

    private final void handleResult(File file) {
        if (file != null) {
            ImagePickerActivity activity = getActivity();
            Uri fromFile = Uri.fromFile(file);
            Intrinsics.checkNotNullExpressionValue(fromFile, "Uri.fromFile(file)");
            activity.setCropImage(fromFile);
            return;
        }
        setError(R.string.error_failed_to_crop_image);
    }

    @Override // com.github.dhaval2404.imagepicker.provider.BaseProvider
    protected void onFailure() {
        delete();
    }

    public final void delete() {
        File file = this.mCropImageFile;
        if (file != null) {
            file.delete();
        }
        this.mCropImageFile = null;
    }
}
