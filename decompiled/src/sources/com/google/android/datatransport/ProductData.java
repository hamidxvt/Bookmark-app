package com.google.android.datatransport;

/* loaded from: classes16.dex */
public abstract class ProductData {
    public abstract Integer getProductId();

    public static ProductData withProductId(Integer productId) {
        return new AutoValue_ProductData(productId);
    }
}
