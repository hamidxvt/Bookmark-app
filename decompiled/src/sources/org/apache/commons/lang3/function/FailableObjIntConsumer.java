package org.apache.commons.lang3.function;

import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableObjIntConsumer<T, E extends Throwable> {
    public static final FailableObjIntConsumer NOP = new FailableObjIntConsumer() { // from class: org.apache.commons.lang3.function.FailableObjIntConsumer$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableObjIntConsumer
        public final void accept(Object obj, int i) {
            FailableObjIntConsumer.lambda$static$0(obj, i);
        }
    };

    void accept(T t, int i) throws Throwable;

    static /* synthetic */ void lambda$static$0(Object t, int u) throws Throwable {
    }

    static <T, E extends Throwable> FailableObjIntConsumer<T, E> nop() {
        return NOP;
    }
}
