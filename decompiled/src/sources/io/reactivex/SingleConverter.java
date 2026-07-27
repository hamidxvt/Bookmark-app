package io.reactivex;

/* loaded from: classes17.dex */
public interface SingleConverter<T, R> {
    R apply(Single<T> single);
}
