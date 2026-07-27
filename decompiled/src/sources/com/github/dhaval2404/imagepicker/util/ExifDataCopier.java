package com.github.dhaval2404.imagepicker.util;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExifDataCopier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J \u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0002¨\u0006\u000e"}, d2 = {"Lcom/github/dhaval2404/imagepicker/util/ExifDataCopier;", "", "()V", "copyExif", "", "filePathOri", "Ljava/io/File;", "filePathDest", "setIfNotNull", "oldExif", "Landroidx/exifinterface/media/ExifInterface;", "newExif", "property", "", "imagepicker_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class ExifDataCopier {
    public static final ExifDataCopier INSTANCE = new ExifDataCopier();

    private ExifDataCopier() {
    }

    public final void copyExif(File filePathOri, File filePathDest) {
        Intrinsics.checkNotNullParameter(filePathOri, "filePathOri");
        Intrinsics.checkNotNullParameter(filePathDest, "filePathDest");
        try {
            ExifInterface oldExif = new ExifInterface(filePathOri);
            ExifInterface newExif = new ExifInterface(filePathDest);
            List<String> attributes = CollectionsKt.listOf((Object[]) new String[]{"FNumber", "ExposureTime", "ISOSpeedRatings", "GPSAltitude", "GPSAltitudeRef", "FocalLength", "GPSDateStamp", "WhiteBalance", "GPSProcessingMethod", "GPSTimeStamp", "DateTime", "Flash", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "Make", "Model", "Orientation"});
            for (String attribute : attributes) {
                setIfNotNull(oldExif, newExif, attribute);
            }
            newExif.saveAttributes();
        } catch (Exception ex) {
            Log.e("ExifDataCopier", "Error preserving Exif data on selected image: " + ex);
        }
    }

    private final void setIfNotNull(ExifInterface oldExif, ExifInterface newExif, String property) {
        if (oldExif.getAttribute(property) != null) {
            newExif.setAttribute(property, oldExif.getAttribute(property));
        }
    }
}
