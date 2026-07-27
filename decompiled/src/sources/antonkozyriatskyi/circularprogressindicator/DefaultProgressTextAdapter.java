package antonkozyriatskyi.circularprogressindicator;

import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;

/* loaded from: classes.dex */
public final class DefaultProgressTextAdapter implements CircularProgressIndicator.ProgressTextAdapter {
    @Override // antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator.ProgressTextAdapter
    public String formatText(double currentProgress) {
        return String.valueOf((int) currentProgress);
    }
}
