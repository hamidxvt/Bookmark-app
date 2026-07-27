package com.google.android.datatransport;

/* loaded from: classes16.dex */
final class AutoValue_ProductData extends ProductData {
    private final Integer productId;

    AutoValue_ProductData(Integer productId) {
        this.productId = productId;
    }

    @Override // com.google.android.datatransport.ProductData
    public Integer getProductId() {
        return this.productId;
    }

    public String toString() {
        return "ProductData{productId=" + this.productId + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ProductData)) {
            return false;
        }
        ProductData that = (ProductData) o;
        return this.productId == null ? that.getProductId() == null : this.productId.equals(that.getProductId());
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        return h$ ^ (this.productId == null ? 0 : this.productId.hashCode());
    }
}
