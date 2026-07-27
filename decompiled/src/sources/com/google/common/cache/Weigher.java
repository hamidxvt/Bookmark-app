package com.google.common.cache;

@ElementTypesAreNonnullByDefault
/* loaded from: classes16.dex */
public interface Weigher<K, V> {
    int weigh(K k, V v);
}
