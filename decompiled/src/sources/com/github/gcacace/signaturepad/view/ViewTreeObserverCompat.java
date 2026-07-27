package com.github.gcacace.signaturepad.view;

import android.view.ViewTreeObserver;

/* loaded from: classes16.dex */
public class ViewTreeObserverCompat {
    public static void removeOnGlobalLayoutListener(ViewTreeObserver observer, ViewTreeObserver.OnGlobalLayoutListener victim) {
        observer.removeOnGlobalLayoutListener(victim);
    }
}
