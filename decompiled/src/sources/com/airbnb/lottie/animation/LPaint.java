package com.airbnb.lottie.animation;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.os.LocaleList;
import com.airbnb.lottie.utils.MiscUtils;

/* loaded from: classes.dex */
public class LPaint extends Paint {
    public LPaint() {
    }

    public LPaint(int flags) {
        super(flags);
    }

    public LPaint(PorterDuff.Mode porterDuffMode) {
        setXfermode(new PorterDuffXfermode(porterDuffMode));
    }

    public LPaint(int flags, PorterDuff.Mode porterDuffMode) {
        super(flags);
        setXfermode(new PorterDuffXfermode(porterDuffMode));
    }

    @Override // android.graphics.Paint
    public void setTextLocales(LocaleList locales) {
    }

    @Override // android.graphics.Paint
    public void setAlpha(int alpha) {
        if (Build.VERSION.SDK_INT < 30) {
            int color = getColor();
            setColor((MiscUtils.clamp(alpha, 0, 255) << 24) | (16777215 & color));
        } else {
            super.setAlpha(MiscUtils.clamp(alpha, 0, 255));
        }
    }
}
