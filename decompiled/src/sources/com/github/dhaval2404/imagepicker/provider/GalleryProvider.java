package com.github.dhaval2404.imagepicker.provider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.dhaval2404.imagepicker.ImagePickerActivity;
import com.github.dhaval2404.imagepicker.R;
import com.github.dhaval2404.imagepicker.util.IntentUtils;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GalleryProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J \u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\b\u0010\u0011\u001a\u00020\nH\u0002J\u0006\u0010\u0012\u001a\u00020\nJ\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u0017"}, d2 = {"Lcom/github/dhaval2404/imagepicker/provider/GalleryProvider;", "Lcom/github/dhaval2404/imagepicker/provider/BaseProvider;", "activity", "Lcom/github/dhaval2404/imagepicker/ImagePickerActivity;", "(Lcom/github/dhaval2404/imagepicker/ImagePickerActivity;)V", "mimeTypes", "", "", "[Ljava/lang/String;", "handleResult", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Landroid/content/Intent;", "onActivityResult", "requestCode", "", "resultCode", "startGalleryIntent", "startIntent", "takePersistableUriPermission", "uri", "Landroid/net/Uri;", "Companion", "imagepicker_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class GalleryProvider extends BaseProvider {
    private static final int GALLERY_INTENT_REQ_CODE = 4261;
    private final String[] mimeTypes;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GalleryProvider(ImagePickerActivity activity) {
        super(activity);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intent intent = activity.getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "activity.intent");
        Bundle bundle = intent.getExtras();
        bundle = bundle == null ? new Bundle() : bundle;
        Intrinsics.checkNotNullExpressionValue(bundle, "activity.intent.extras ?: Bundle()");
        String[] stringArray = bundle.getStringArray(ImagePicker.EXTRA_MIME_TYPES);
        this.mimeTypes = stringArray == null ? new String[0] : stringArray;
    }

    public final void startIntent() {
        startGalleryIntent();
    }

    private final void startGalleryIntent() {
        Intent galleryIntent = IntentUtils.getGalleryIntent(getActivity(), this.mimeTypes);
        getActivity().startActivityForResult(galleryIntent, GALLERY_INTENT_REQ_CODE);
    }

    public final void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY_INTENT_REQ_CODE) {
            if (resultCode == -1) {
                handleResult(data);
            } else {
                setResultCancel();
            }
        }
    }

    private final void handleResult(Intent data) {
        Uri uri = data != null ? data.getData() : null;
        if (uri != null) {
            takePersistableUriPermission(uri);
            getActivity().setImage(uri);
        } else {
            setError(R.string.error_failed_pick_gallery_image);
        }
    }

    private final void takePersistableUriPermission(Uri uri) {
        getContentResolver().takePersistableUriPermission(uri, 1);
    }
}
