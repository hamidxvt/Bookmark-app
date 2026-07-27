package com.google.android.datatransport;

/* loaded from: classes16.dex */
public abstract class Event<T> {
    public abstract Integer getCode();

    public abstract T getPayload();

    public abstract Priority getPriority();

    public abstract ProductData getProductData();

    public static <T> Event<T> ofData(int code, T payload, ProductData productData) {
        return new AutoValue_Event(Integer.valueOf(code), payload, Priority.DEFAULT, productData);
    }

    public static <T> Event<T> ofData(int code, T payload) {
        return new AutoValue_Event(Integer.valueOf(code), payload, Priority.DEFAULT, null);
    }

    public static <T> Event<T> ofData(T payload, ProductData productData) {
        return new AutoValue_Event(null, payload, Priority.DEFAULT, productData);
    }

    public static <T> Event<T> ofData(T payload) {
        return new AutoValue_Event(null, payload, Priority.DEFAULT, null);
    }

    public static <T> Event<T> ofTelemetry(int code, T value, ProductData productData) {
        return new AutoValue_Event(Integer.valueOf(code), value, Priority.VERY_LOW, productData);
    }

    public static <T> Event<T> ofTelemetry(int code, T value) {
        return new AutoValue_Event(Integer.valueOf(code), value, Priority.VERY_LOW, null);
    }

    public static <T> Event<T> ofTelemetry(T value, ProductData productData) {
        return new AutoValue_Event(null, value, Priority.VERY_LOW, productData);
    }

    public static <T> Event<T> ofTelemetry(T value) {
        return new AutoValue_Event(null, value, Priority.VERY_LOW, null);
    }

    public static <T> Event<T> ofUrgent(int code, T value, ProductData productData) {
        return new AutoValue_Event(Integer.valueOf(code), value, Priority.HIGHEST, productData);
    }

    public static <T> Event<T> ofUrgent(int code, T value) {
        return new AutoValue_Event(Integer.valueOf(code), value, Priority.HIGHEST, null);
    }

    public static <T> Event<T> ofUrgent(T value, ProductData productData) {
        return new AutoValue_Event(null, value, Priority.HIGHEST, productData);
    }

    public static <T> Event<T> ofUrgent(T value) {
        return new AutoValue_Event(null, value, Priority.HIGHEST, null);
    }
}
