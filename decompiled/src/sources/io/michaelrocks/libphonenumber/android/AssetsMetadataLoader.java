package io.michaelrocks.libphonenumber.android;

import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes17.dex */
public class AssetsMetadataLoader implements MetadataLoader {
    private final AssetManager assetManager;

    public AssetsMetadataLoader(final AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Override // io.michaelrocks.libphonenumber.android.MetadataLoader
    public InputStream loadMetadata(final String metadataFileName) {
        String assetFileName = metadataFileName.substring(1);
        try {
            return this.assetManager.open(assetFileName);
        } catch (IOException e) {
            return null;
        }
    }
}
