package com.google.android.datatransport;

/* loaded from: classes16.dex */
final class AutoValue_Event<T> extends Event<T> {
    private final Integer code;
    private final T payload;
    private final Priority priority;
    private final ProductData productData;

    AutoValue_Event(Integer code, T payload, Priority priority, ProductData productData) {
        this.code = code;
        if (payload == null) {
            throw new NullPointerException("Null payload");
        }
        this.payload = payload;
        if (priority == null) {
            throw new NullPointerException("Null priority");
        }
        this.priority = priority;
        this.productData = productData;
    }

    @Override // com.google.android.datatransport.Event
    public Integer getCode() {
        return this.code;
    }

    @Override // com.google.android.datatransport.Event
    public T getPayload() {
        return this.payload;
    }

    @Override // com.google.android.datatransport.Event
    public Priority getPriority() {
        return this.priority;
    }

    @Override // com.google.android.datatransport.Event
    public ProductData getProductData() {
        return this.productData;
    }

    public String toString() {
        return "Event{code=" + this.code + ", payload=" + this.payload + ", priority=" + this.priority + ", productData=" + this.productData + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        Event<?> that = (Event) o;
        if (this.code != null ? this.code.equals(that.getCode()) : that.getCode() == null) {
            if (this.payload.equals(that.getPayload()) && this.priority.equals(that.getPriority())) {
                if (this.productData == null) {
                    if (that.getProductData() == null) {
                        return true;
                    }
                } else if (this.productData.equals(that.getProductData())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        return ((((((h$ ^ (this.code == null ? 0 : this.code.hashCode())) * 1000003) ^ this.payload.hashCode()) * 1000003) ^ this.priority.hashCode()) * 1000003) ^ (this.productData != null ? this.productData.hashCode() : 0);
    }
}
