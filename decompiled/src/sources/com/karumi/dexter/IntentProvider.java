package com.karumi.dexter;

import android.content.Context;
import android.content.Intent;

/* loaded from: classes17.dex */
class IntentProvider {
    IntentProvider() {
    }

    public Intent get(Context context, Class<?> cls) {
        return new Intent(context, cls);
    }
}
