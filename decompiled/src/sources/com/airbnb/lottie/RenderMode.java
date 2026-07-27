package com.airbnb.lottie;

/* loaded from: classes.dex */
public enum RenderMode {
    AUTOMATIC,
    HARDWARE,
    SOFTWARE;

    public boolean useSoftwareRendering(int sdkInt, boolean hasDashPattern, int numMasksAndMattes) {
        switch (this) {
            case HARDWARE:
                break;
            case SOFTWARE:
                break;
            default:
                if ((!hasDashPattern || sdkInt >= 28) && numMasksAndMattes <= 4 && sdkInt > 25) {
                }
                break;
        }
        return true;
    }
}
