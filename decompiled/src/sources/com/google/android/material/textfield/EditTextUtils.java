package com.google.android.material.textfield;

import android.widget.EditText;

/* loaded from: classes16.dex */
class EditTextUtils {
    private EditTextUtils() {
    }

    static boolean isEditable(EditText editText) {
        return editText.getInputType() != 0;
    }
}
