package com.google.common.util.concurrent;

@ElementTypesAreNonnullByDefault
/* loaded from: classes16.dex */
public interface AsyncCallable<V> {
    ListenableFuture<V> call() throws Exception;
}
