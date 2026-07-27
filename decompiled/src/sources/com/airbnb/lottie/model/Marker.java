package com.airbnb.lottie.model;

/* loaded from: classes.dex */
public class Marker {
    private static final String CARRIAGE_RETURN = "\r";
    public final float durationFrames;
    private final String name;
    public final float startFrame;

    public Marker(String name, float startFrame, float durationFrames) {
        this.name = name;
        this.durationFrames = durationFrames;
        this.startFrame = startFrame;
    }

    public String getName() {
        return this.name;
    }

    public float getStartFrame() {
        return this.startFrame;
    }

    public float getDurationFrames() {
        return this.durationFrames;
    }

    public boolean matchesName(String name) {
        if (this.name.equalsIgnoreCase(name)) {
            return true;
        }
        return this.name.endsWith("\r") && this.name.substring(0, this.name.length() - 1).equalsIgnoreCase(name);
    }
}
