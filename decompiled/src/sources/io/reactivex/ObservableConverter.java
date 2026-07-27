package io.reactivex;

/* loaded from: classes17.dex */
public interface ObservableConverter<T, R> {
    R apply(Observable<T> observable);
}
