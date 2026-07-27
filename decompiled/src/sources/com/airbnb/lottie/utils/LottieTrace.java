package com.airbnb.lottie.utils;

import androidx.core.os.TraceCompat;

/* loaded from: classes.dex */
public class LottieTrace {
    private static final int MAX_DEPTH = 5;
    private final String[] sections = new String[5];
    private final long[] startTimeNs = new long[5];
    private int traceDepth = 0;
    private int depthPastMaxDepth = 0;

    public void beginSection(String section) {
        if (this.traceDepth == 5) {
            this.depthPastMaxDepth++;
            return;
        }
        this.sections[this.traceDepth] = section;
        this.startTimeNs[this.traceDepth] = System.nanoTime();
        TraceCompat.beginSection(section);
        this.traceDepth++;
    }

    public float endSection(String section) {
        if (this.depthPastMaxDepth > 0) {
            this.depthPastMaxDepth--;
            return 0.0f;
        }
        this.traceDepth--;
        if (this.traceDepth == -1) {
            throw new IllegalStateException("Can't end trace section. There are none.");
        }
        if (!section.equals(this.sections[this.traceDepth])) {
            throw new IllegalStateException("Unbalanced trace call " + section + ". Expected " + this.sections[this.traceDepth] + ".");
        }
        TraceCompat.endSection();
        return (System.nanoTime() - this.startTimeNs[this.traceDepth]) / 1000000.0f;
    }
}
