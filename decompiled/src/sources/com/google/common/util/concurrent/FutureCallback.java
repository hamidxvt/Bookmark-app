package com.google.common.util.concurrent;

@ElementTypesAreNonnullByDefault
/* loaded from: classes16.dex */
public interface FutureCallback<V> {
    void onFailure(Throwable th);

    void onSuccess(@ParametricNullness V v);
}
