package androidx.work.impl.foreground;

import androidx.work.ForegroundInfo;

/* loaded from: classes.dex */
public interface ForegroundProcessor {
    boolean isEnqueuedInForeground(String workSpecId);

    void startForeground(String workSpecId, ForegroundInfo foregroundInfo);

    void stopForeground(String workSpecId);
}
