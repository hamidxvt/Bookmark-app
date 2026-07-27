package com.github.dhaval2404.imagepicker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.github.dhaval2404.imagepicker.constant.ImageProvider;
import com.github.dhaval2404.imagepicker.provider.CameraProvider;
import com.github.dhaval2404.imagepicker.provider.CompressionProvider;
import com.github.dhaval2404.imagepicker.provider.CropProvider;
import com.github.dhaval2404.imagepicker.provider.GalleryProvider;
import com.github.dhaval2404.imagepicker.util.FileUriUtils;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImagePickerActivity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J\"\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\fH\u0016J\u0012\u0010\u0016\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J+\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0016¢\u0006\u0002\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u000eH\u0016J\u000e\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020\f2\u0006\u0010!\u001a\u00020\"J\u000e\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020\u001aJ\u000e\u0010&\u001a\u00020\f2\u0006\u0010!\u001a\u00020\"J\u0010\u0010'\u001a\u00020\f2\u0006\u0010!\u001a\u00020\"H\u0002J\u0006\u0010(\u001a\u00020\fR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/github/dhaval2404/imagepicker/ImagePickerActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "mCameraProvider", "Lcom/github/dhaval2404/imagepicker/provider/CameraProvider;", "mCompressionProvider", "Lcom/github/dhaval2404/imagepicker/provider/CompressionProvider;", "mCropProvider", "Lcom/github/dhaval2404/imagepicker/provider/CropProvider;", "mGalleryProvider", "Lcom/github/dhaval2404/imagepicker/provider/GalleryProvider;", "loadBundle", "", "savedInstanceState", "Landroid/os/Bundle;", "onActivityResult", "requestCode", "", "resultCode", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Landroid/content/Intent;", "onBackPressed", "onCreate", "onRequestPermissionsResult", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onSaveInstanceState", "outState", "setCompressedImage", "uri", "Landroid/net/Uri;", "setCropImage", "setError", "message", "setImage", "setResult", "setResultCancel", "Companion", "imagepicker_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class ImagePickerActivity extends AppCompatActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "image_picker";
    private CameraProvider mCameraProvider;
    private CompressionProvider mCompressionProvider;
    private CropProvider mCropProvider;
    private GalleryProvider mGalleryProvider;

    /* compiled from: ImagePickerActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/github/dhaval2404/imagepicker/ImagePickerActivity$Companion;", "", "()V", "TAG", "", "getCancelledIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "getCancelledIntent$imagepicker_release", "imagepicker_release"}, k = 1, mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final Intent getCancelledIntent$imagepicker_release(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent();
            String message = context.getString(R.string.error_task_cancelled);
            Intrinsics.checkNotNullExpressionValue(message, "context.getString(R.string.error_task_cancelled)");
            intent.putExtra(ImagePicker.EXTRA_ERROR, message);
            return intent;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadBundle(savedInstanceState);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        CameraProvider cameraProvider = this.mCameraProvider;
        if (cameraProvider != null) {
            cameraProvider.onSaveInstanceState(outState);
        }
        CropProvider cropProvider = this.mCropProvider;
        if (cropProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCropProvider");
        }
        cropProvider.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    private final void loadBundle(Bundle savedInstanceState) {
        GalleryProvider galleryProvider;
        CameraProvider cameraProvider;
        this.mCropProvider = new CropProvider(this);
        CropProvider cropProvider = this.mCropProvider;
        if (cropProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCropProvider");
        }
        cropProvider.onRestoreInstanceState(savedInstanceState);
        this.mCompressionProvider = new CompressionProvider(this);
        Intent intent = getIntent();
        ImageProvider provider = (ImageProvider) (intent != null ? intent.getSerializableExtra(ImagePicker.EXTRA_IMAGE_PROVIDER) : null);
        if (provider != null) {
            switch (provider) {
                case GALLERY:
                    this.mGalleryProvider = new GalleryProvider(this);
                    if (savedInstanceState == null && (galleryProvider = this.mGalleryProvider) != null) {
                        galleryProvider.startIntent();
                        Unit unit = Unit.INSTANCE;
                        break;
                    }
                    break;
                case CAMERA:
                    this.mCameraProvider = new CameraProvider(this);
                    CameraProvider cameraProvider2 = this.mCameraProvider;
                    if (cameraProvider2 != null) {
                        cameraProvider2.onRestoreInstanceState(savedInstanceState);
                    }
                    if (savedInstanceState == null && (cameraProvider = this.mCameraProvider) != null) {
                        cameraProvider.startIntent();
                        Unit unit2 = Unit.INSTANCE;
                        break;
                    }
                    break;
            }
        }
        Log.e(TAG, "Image provider can not be null");
        String string = getString(R.string.error_task_cancelled);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.error_task_cancelled)");
        setError(string);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        CameraProvider cameraProvider = this.mCameraProvider;
        if (cameraProvider != null) {
            cameraProvider.onRequestPermissionsResult(requestCode);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CameraProvider cameraProvider = this.mCameraProvider;
        if (cameraProvider != null) {
            cameraProvider.onActivityResult(requestCode, resultCode, data);
        }
        GalleryProvider galleryProvider = this.mGalleryProvider;
        if (galleryProvider != null) {
            galleryProvider.onActivityResult(requestCode, resultCode, data);
        }
        CropProvider cropProvider = this.mCropProvider;
        if (cropProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCropProvider");
        }
        cropProvider.onActivityResult(requestCode, resultCode, data);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        setResultCancel();
    }

    public final void setImage(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        CropProvider cropProvider = this.mCropProvider;
        if (cropProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCropProvider");
        }
        if (!cropProvider.getMCrop()) {
            CompressionProvider compressionProvider = this.mCompressionProvider;
            if (compressionProvider == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCompressionProvider");
            }
            if (!compressionProvider.isCompressionRequired(uri)) {
                setResult(uri);
                return;
            }
            CompressionProvider compressionProvider2 = this.mCompressionProvider;
            if (compressionProvider2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCompressionProvider");
            }
            compressionProvider2.compress(uri);
            return;
        }
        CropProvider cropProvider2 = this.mCropProvider;
        if (cropProvider2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCropProvider");
        }
        cropProvider2.startIntent(uri);
    }

    public final void setCropImage(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        CameraProvider cameraProvider = this.mCameraProvider;
        if (cameraProvider != null) {
            cameraProvider.delete();
        }
        CompressionProvider compressionProvider = this.mCompressionProvider;
        if (compressionProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCompressionProvider");
        }
        if (compressionProvider.isCompressionRequired(uri)) {
            CompressionProvider compressionProvider2 = this.mCompressionProvider;
            if (compressionProvider2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCompressionProvider");
            }
            compressionProvider2.compress(uri);
            return;
        }
        setResult(uri);
    }

    public final void setCompressedImage(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        CameraProvider cameraProvider = this.mCameraProvider;
        if (cameraProvider != null) {
            cameraProvider.delete();
        }
        CropProvider cropProvider = this.mCropProvider;
        if (cropProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCropProvider");
        }
        cropProvider.delete();
        setResult(uri);
    }

    private final void setResult(Uri uri) {
        Intent intent = new Intent();
        intent.setData(uri);
        intent.putExtra(ImagePicker.EXTRA_FILE_PATH, FileUriUtils.INSTANCE.getRealPath(this, uri));
        setResult(-1, intent);
        finish();
    }

    public final void setResultCancel() {
        setResult(0, INSTANCE.getCancelledIntent$imagepicker_release(this));
        finish();
    }

    public final void setError(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intent intent = new Intent();
        intent.putExtra(ImagePicker.EXTRA_ERROR, message);
        setResult(64, intent);
        finish();
    }
}
