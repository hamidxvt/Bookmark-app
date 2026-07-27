package com.futuremind.recyclerviewfastscroll;

import android.graphics.drawable.Drawable;
import android.view.View;

/* loaded from: classes16.dex */
public class Utils {
    public static float getViewRawY(View view) {
        int[] location = {0, (int) view.getY()};
        ((View) view.getParent()).getLocationInWindow(location);
        return location[1];
    }

    public static float getViewRawX(View view) {
        int[] location = {(int) view.getX(), 0};
        ((View) view.getParent()).getLocationInWindow(location);
        return location[0];
    }

    public static float getValueInRange(float min, float max, float value) {
        float minimum = Math.max(min, value);
        return Math.min(minimum, max);
    }

    public static void setBackground(View view, Drawable drawable) {
        view.setBackground(drawable);
    }
}
