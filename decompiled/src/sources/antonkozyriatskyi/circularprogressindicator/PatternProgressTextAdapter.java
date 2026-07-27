package antonkozyriatskyi.circularprogressindicator;

import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;

/* loaded from: classes.dex */
public final class PatternProgressTextAdapter implements CircularProgressIndicator.ProgressTextAdapter {
    private String pattern;

    public PatternProgressTextAdapter(String pattern) {
        this.pattern = pattern;
    }

    @Override // antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator.ProgressTextAdapter
    public String formatText(double currentProgress) {
        return String.format(this.pattern, Double.valueOf(currentProgress));
    }
}
