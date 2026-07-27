package com.github.dhaval2404.imagepicker.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;
import com.github.dhaval2404.imagepicker.R;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IntentUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u001b\u0010\t\u001a\u00020\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002¢\u0006\u0002\u0010\rJ#\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0007¢\u0006\u0002\u0010\u000fJ\u001b\u0010\u0010\u001a\u00020\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002¢\u0006\u0002\u0010\rJ\u0018\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u001f\u0010\u0016\u001a\u00020\u0004*\u00020\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002¢\u0006\u0002\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/github/dhaval2404/imagepicker/util/IntentUtils;", "", "()V", "getCameraIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "file", "Ljava/io/File;", "getGalleryDocumentIntent", "mimeTypes", "", "", "([Ljava/lang/String;)Landroid/content/Intent;", "getGalleryIntent", "(Landroid/content/Context;[Ljava/lang/String;)Landroid/content/Intent;", "getLegacyGalleryPickIntent", "getUriViewIntent", "uri", "Landroid/net/Uri;", "isCameraAppAvailable", "", "applyImageTypes", "(Landroid/content/Intent;[Ljava/lang/String;)Landroid/content/Intent;", "imagepicker_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class IntentUtils {
    public static final IntentUtils INSTANCE = new IntentUtils();

    private IntentUtils() {
    }

    @JvmStatic
    public static final Intent getGalleryIntent(Context context, String[] mimeTypes) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mimeTypes, "mimeTypes");
        Intent intent = INSTANCE.getGalleryDocumentIntent(mimeTypes);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            return intent;
        }
        return INSTANCE.getLegacyGalleryPickIntent(mimeTypes);
    }

    private final Intent getGalleryDocumentIntent(String[] mimeTypes) {
        Intent intent = applyImageTypes(new Intent("android.intent.action.OPEN_DOCUMENT"), mimeTypes);
        intent.addCategory("android.intent.category.OPENABLE");
        intent.addFlags(64);
        intent.addFlags(1);
        intent.addFlags(2);
        return intent;
    }

    private final Intent getLegacyGalleryPickIntent(String[] mimeTypes) {
        return applyImageTypes(new Intent("android.intent.action.PICK"), mimeTypes);
    }

    private final Intent applyImageTypes(Intent $this$applyImageTypes, String[] mimeTypes) {
        $this$applyImageTypes.setType("image/*");
        if (!(mimeTypes.length == 0)) {
            $this$applyImageTypes.putExtra("android.intent.extra.MIME_TYPES", mimeTypes);
        }
        return $this$applyImageTypes;
    }

    @JvmStatic
    public static final Intent getCameraIntent(Context context, File file) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(file, "file");
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        String authority = context.getPackageName() + context.getString(R.string.image_picker_provider_authority_suffix);
        Uri photoURI = FileProvider.getUriForFile(context, authority, file);
        intent.putExtra("output", photoURI);
        return intent;
    }

    @JvmStatic
    public static final boolean isCameraAppAvailable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        return intent.resolveActivity(context.getPackageManager()) != null;
    }

    @JvmStatic
    public static final Intent getUriViewIntent(Context context, Uri uri) {
        Uri uriForFile;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intent intent = new Intent("android.intent.action.VIEW");
        String authority = context.getPackageName() + context.getString(R.string.image_picker_provider_authority_suffix);
        DocumentFile file = DocumentFile.fromSingleUri(context, uri);
        if (file != null && file.canRead()) {
            uriForFile = uri;
        } else {
            String filePath = FileUriUtils.INSTANCE.getRealPath(context, uri);
            Intrinsics.checkNotNull(filePath);
            uriForFile = FileProvider.getUriForFile(context, authority, new File(filePath));
        }
        Uri dataUri = uriForFile;
        intent.setDataAndType(dataUri, "image/*");
        intent.addFlags(1);
        return intent;
    }
}
