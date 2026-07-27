package org.reactivestreams;

/* loaded from: classes17.dex */
public interface Subscription {
    void cancel();

    void request(long j);
}
