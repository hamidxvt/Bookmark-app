package com.github.gcacace.signaturepad.utils;

/* loaded from: classes16.dex */
public class TimedPoint {
    public long timestamp;
    public float x;
    public float y;

    public TimedPoint set(float x, float y) {
        this.x = x;
        this.y = y;
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public float velocityFrom(TimedPoint start) {
        long diff = this.timestamp - start.timestamp;
        if (diff <= 0) {
            diff = 1;
        }
        float velocity = distanceTo(start) / diff;
        if (Float.isInfinite(velocity) || Float.isNaN(velocity)) {
            return 0.0f;
        }
        return velocity;
    }

    public float distanceTo(TimedPoint point) {
        return (float) Math.sqrt(Math.pow(point.x - this.x, 2.0d) + Math.pow(point.y - this.y, 2.0d));
    }
}
