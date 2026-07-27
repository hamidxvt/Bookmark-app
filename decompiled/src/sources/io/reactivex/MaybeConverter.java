package io.reactivex;

/* loaded from: classes17.dex */
public interface MaybeConverter<T, R> {
    R apply(Maybe<T> maybe);
}
