package io.reactivex;

/* loaded from: classes17.dex */
public interface FlowableConverter<T, R> {
    R apply(Flowable<T> flowable);
}
