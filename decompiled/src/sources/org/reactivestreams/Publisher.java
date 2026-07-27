package org.reactivestreams;

/* loaded from: classes17.dex */
public interface Publisher<T> {
    void subscribe(Subscriber<? super T> subscriber);
}
