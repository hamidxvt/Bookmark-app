package org.apache.commons.lang3.function;

import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableSupplier<R, E extends Throwable> {
    R get() throws Throwable;
}
