package com.google.common.base;

@ElementTypesAreNonnullByDefault
/* loaded from: classes16.dex */
interface PatternCompiler {
    CommonPattern compile(String str);

    boolean isPcreLike();
}
