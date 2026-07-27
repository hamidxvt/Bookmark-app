package com.github.dhaval2404.imagepicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.dhaval2404.imagepicker.constant.ImageProvider;
import com.github.dhaval2404.imagepicker.listener.DismissListener;
import com.github.dhaval2404.imagepicker.listener.ResultListener;
import com.github.dhaval2404.imagepicker.util.DialogHelper;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.messaging.Constants;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.RealWebSocket;

/* compiled from: ImagePicker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00042\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/github/dhaval2404/imagepicker/ImagePicker;", "", "()V", "Builder", "Companion", "imagepicker_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public class ImagePicker {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String EXTRA_CAMERA_DEVICE = "extra.camera_device";
    public static final String EXTRA_CROP = "extra.crop";
    public static final String EXTRA_CROP_X = "extra.crop_x";
    public static final String EXTRA_CROP_Y = "extra.crop_y";
    public static final String EXTRA_ERROR = "extra.error";
    public static final String EXTRA_FILE_PATH = "extra.file_path";
    public static final String EXTRA_IMAGE_MAX_SIZE = "extra.image_max_size";
    public static final String EXTRA_IMAGE_PROVIDER = "extra.image_provider";
    public static final String EXTRA_MAX_HEIGHT = "extra.max_height";
    public static final String EXTRA_MAX_WIDTH = "extra.max_width";
    public static final String EXTRA_MIME_TYPES = "extra.mime_types";
    public static final String EXTRA_SAVE_DIRECTORY = "extra.save_directory";
    public static final int REQUEST_CODE = 2404;
    public static final int RESULT_ERROR = 64;

    @JvmStatic
    public static final String getError(Intent intent) {
        return INSTANCE.getError(intent);
    }

    @JvmStatic
    public static final Builder with(Activity activity) {
        return INSTANCE.with(activity);
    }

    @JvmStatic
    public static final Builder with(Fragment fragment) {
        return INSTANCE.with(fragment);
    }

    /* compiled from: ImagePicker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/github/dhaval2404/imagepicker/ImagePicker$Companion;", "", "()V", "EXTRA_CAMERA_DEVICE", "", "EXTRA_CROP", "EXTRA_CROP_X", "EXTRA_CROP_Y", "EXTRA_ERROR", "EXTRA_FILE_PATH", "EXTRA_IMAGE_MAX_SIZE", "EXTRA_IMAGE_PROVIDER", "EXTRA_MAX_HEIGHT", "EXTRA_MAX_WIDTH", "EXTRA_MIME_TYPES", "EXTRA_SAVE_DIRECTORY", "REQUEST_CODE", "", "RESULT_ERROR", "getError", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Landroid/content/Intent;", "with", "Lcom/github/dhaval2404/imagepicker/ImagePicker$Builder;", "activity", "Landroid/app/Activity;", "fragment", "Landroidx/fragment/app/Fragment;", "imagepicker_release"}, k = 1, mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @JvmStatic
        public final Builder with(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            return new Builder(activity);
        }

        @JvmStatic
        public final Builder with(Fragment fragment) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            return new Builder(fragment);
        }

        @JvmStatic
        public final String getError(Intent data) {
            String error = data != null ? data.getStringExtra(ImagePicker.EXTRA_ERROR) : null;
            if (error != null) {
                return error;
            }
            return "Unknown Error!";
        }
    }

    /* compiled from: ImagePicker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\u001e\u001a\u00020\u0000J\u000e\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0015J\b\u0010 \u001a\u00020!H\u0002J\u001a\u0010 \u001a\u00020\u00132\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00130\u0012J\u0006\u0010\b\u001a\u00020\u0000J\u0016\u0010\b\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000bJ\u0006\u0010%\u001a\u00020\u0000J\u0019\u0010&\u001a\u00020\u00002\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a¢\u0006\u0002\u0010'J\u0006\u0010(\u001a\u00020\u0000J\b\u0010)\u001a\u00020*H\u0002J\u0016\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u0015J\u000e\u0010.\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010/\u001a\u000200J\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u00101\u001a\u00020\u001bJ\u0014\u00102\u001a\u00020\u00002\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u001304J\u000e\u00102\u001a\u00020\u00002\u0006\u00103\u001a\u00020\u000eJ\u001a\u00105\u001a\u00020\u00002\u0012\u00106\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00130\u0012J\u0010\u00107\u001a\u00020\u00132\u0006\u00108\u001a\u00020\u0015H\u0002J\u0006\u00109\u001a\u00020\u0013J\u000e\u00109\u001a\u00020\u00132\u0006\u00108\u001a\u00020\u0015J\u0010\u0010:\u001a\u00020\u00132\u0006\u00108\u001a\u00020\u0015H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001cR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lcom/github/dhaval2404/imagepicker/ImagePicker$Builder;", "", "fragment", "Landroidx/fragment/app/Fragment;", "(Landroidx/fragment/app/Fragment;)V", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "crop", "", "cropX", "", "cropY", "dismissListener", "Lcom/github/dhaval2404/imagepicker/listener/DismissListener;", "imageProvider", "Lcom/github/dhaval2404/imagepicker/constant/ImageProvider;", "imageProviderInterceptor", "Lkotlin/Function1;", "", "maxHeight", "", "maxSize", "", "maxWidth", "mimeTypes", "", "", "[Ljava/lang/String;", "saveDir", "cameraOnly", "compress", "createIntent", "Landroid/content/Intent;", "onResult", "x", "y", "cropSquare", "galleryMimeTypes", "([Ljava/lang/String;)Lcom/github/dhaval2404/imagepicker/ImagePicker$Builder;", "galleryOnly", "getBundle", "Landroid/os/Bundle;", "maxResultSize", "width", "height", "provider", "file", "Ljava/io/File;", "path", "setDismissListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lkotlin/Function0;", "setImageProviderInterceptor", "interceptor", "showImageProviderDialog", "reqCode", "start", "startActivity", "imagepicker_release"}, k = 1, mv = {1, 4, 0})
    public static final class Builder {
        private final Activity activity;
        private boolean crop;
        private float cropX;
        private float cropY;
        private DismissListener dismissListener;
        private Fragment fragment;
        private ImageProvider imageProvider;
        private Function1<? super ImageProvider, Unit> imageProviderInterceptor;
        private int maxHeight;
        private long maxSize;
        private int maxWidth;
        private String[] mimeTypes;
        private String saveDir;

        public Builder(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            this.activity = activity;
            this.imageProvider = ImageProvider.BOTH;
            this.mimeTypes = new String[0];
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder(Fragment fragment) {
            this(r0);
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            FragmentActivity requireActivity = fragment.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "fragment.requireActivity()");
            this.fragment = fragment;
        }

        public final Builder provider(ImageProvider imageProvider) {
            Intrinsics.checkNotNullParameter(imageProvider, "imageProvider");
            this.imageProvider = imageProvider;
            return this;
        }

        public final Builder cameraOnly() {
            this.imageProvider = ImageProvider.CAMERA;
            return this;
        }

        public final Builder galleryOnly() {
            this.imageProvider = ImageProvider.GALLERY;
            return this;
        }

        public final Builder galleryMimeTypes(String[] mimeTypes) {
            Intrinsics.checkNotNullParameter(mimeTypes, "mimeTypes");
            this.mimeTypes = mimeTypes;
            return this;
        }

        public final Builder crop(float x, float y) {
            this.cropX = x;
            this.cropY = y;
            return crop();
        }

        public final Builder crop() {
            this.crop = true;
            return this;
        }

        public final Builder cropSquare() {
            return crop(1.0f, 1.0f);
        }

        public final Builder maxResultSize(int width, int height) {
            this.maxWidth = width;
            this.maxHeight = height;
            return this;
        }

        public final Builder compress(int maxSize) {
            this.maxSize = maxSize * RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE;
            return this;
        }

        public final Builder saveDir(String path) {
            Intrinsics.checkNotNullParameter(path, "path");
            this.saveDir = path;
            return this;
        }

        public final Builder saveDir(File file) {
            Intrinsics.checkNotNullParameter(file, "file");
            this.saveDir = file.getAbsolutePath();
            return this;
        }

        public final Builder setImageProviderInterceptor(Function1<? super ImageProvider, Unit> interceptor) {
            Intrinsics.checkNotNullParameter(interceptor, "interceptor");
            this.imageProviderInterceptor = interceptor;
            return this;
        }

        public final Builder setDismissListener(DismissListener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            this.dismissListener = listener;
            return this;
        }

        public final Builder setDismissListener(final Function0<Unit> listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            this.dismissListener = new DismissListener() { // from class: com.github.dhaval2404.imagepicker.ImagePicker$Builder$setDismissListener$1
                @Override // com.github.dhaval2404.imagepicker.listener.DismissListener
                public void onDismiss() {
                    Function0.this.invoke();
                }
            };
            return this;
        }

        public final void start() {
            start(ImagePicker.REQUEST_CODE);
        }

        public final void start(int reqCode) {
            if (this.imageProvider == ImageProvider.BOTH) {
                showImageProviderDialog(reqCode);
            } else {
                startActivity(reqCode);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Intent createIntent() {
            Intent intent = new Intent(this.activity, (Class<?>) ImagePickerActivity.class);
            intent.putExtras(getBundle());
            return intent;
        }

        public final void createIntent(final Function1<? super Intent, Unit> onResult) {
            Intrinsics.checkNotNullParameter(onResult, "onResult");
            if (this.imageProvider == ImageProvider.BOTH) {
                DialogHelper.INSTANCE.showChooseAppDialog(this.activity, new ResultListener<ImageProvider>() { // from class: com.github.dhaval2404.imagepicker.ImagePicker$Builder$createIntent$1
                    @Override // com.github.dhaval2404.imagepicker.listener.ResultListener
                    public void onResult(ImageProvider t) {
                        Function1 function1;
                        Intent createIntent;
                        ImageProvider imageProvider;
                        if (t != null) {
                            ImagePicker.Builder.this.imageProvider = t;
                            function1 = ImagePicker.Builder.this.imageProviderInterceptor;
                            if (function1 != null) {
                                imageProvider = ImagePicker.Builder.this.imageProvider;
                            }
                            Function1 function12 = onResult;
                            createIntent = ImagePicker.Builder.this.createIntent();
                            function12.invoke(createIntent);
                        }
                    }
                }, this.dismissListener);
            } else {
                onResult.invoke(createIntent());
            }
        }

        private final void showImageProviderDialog(final int reqCode) {
            DialogHelper.INSTANCE.showChooseAppDialog(this.activity, new ResultListener<ImageProvider>() { // from class: com.github.dhaval2404.imagepicker.ImagePicker$Builder$showImageProviderDialog$1
                @Override // com.github.dhaval2404.imagepicker.listener.ResultListener
                public void onResult(ImageProvider t) {
                    Function1 function1;
                    ImageProvider imageProvider;
                    if (t != null) {
                        ImagePicker.Builder.this.imageProvider = t;
                        function1 = ImagePicker.Builder.this.imageProviderInterceptor;
                        if (function1 != null) {
                            imageProvider = ImagePicker.Builder.this.imageProvider;
                        }
                        ImagePicker.Builder.this.startActivity(reqCode);
                    }
                }
            }, this.dismissListener);
        }

        private final Bundle getBundle() {
            Bundle $this$apply = new Bundle();
            $this$apply.putSerializable(ImagePicker.EXTRA_IMAGE_PROVIDER, this.imageProvider);
            $this$apply.putStringArray(ImagePicker.EXTRA_MIME_TYPES, this.mimeTypes);
            $this$apply.putBoolean(ImagePicker.EXTRA_CROP, this.crop);
            $this$apply.putFloat(ImagePicker.EXTRA_CROP_X, this.cropX);
            $this$apply.putFloat(ImagePicker.EXTRA_CROP_Y, this.cropY);
            $this$apply.putInt(ImagePicker.EXTRA_MAX_WIDTH, this.maxWidth);
            $this$apply.putInt(ImagePicker.EXTRA_MAX_HEIGHT, this.maxHeight);
            $this$apply.putLong(ImagePicker.EXTRA_IMAGE_MAX_SIZE, this.maxSize);
            $this$apply.putString(ImagePicker.EXTRA_SAVE_DIRECTORY, this.saveDir);
            return $this$apply;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void startActivity(int reqCode) {
            Intent intent = new Intent(this.activity, (Class<?>) ImagePickerActivity.class);
            intent.putExtras(getBundle());
            if (this.fragment != null) {
                Fragment fragment = this.fragment;
                if (fragment != null) {
                    fragment.startActivityForResult(intent, reqCode);
                    return;
                }
                return;
            }
            this.activity.startActivityForResult(intent, reqCode);
        }
    }
}
