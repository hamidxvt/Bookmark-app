package com.google.common.cache;

@ElementTypesAreNonnullByDefault
/* loaded from: classes16.dex */
public interface RemovalListener<K, V> {
    void onRemoval(RemovalNotification<K, V> removalNotification);
}
