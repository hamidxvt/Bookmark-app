package com.github.gcacace.signaturepad.utils;

import com.github.gcacace.signaturepad.views.SignaturePad;

/* loaded from: classes16.dex */
public final class SignaturePadBindingAdapter {

    public interface OnClearListener {
        void onClear();
    }

    public interface OnSignedListener {
        void onSigned();
    }

    public interface OnStartSigningListener {
        void onStartSigning();
    }

    public static void setOnSignedListener(SignaturePad view, OnStartSigningListener onStartSigningListener) {
        setOnSignedListener(view, onStartSigningListener, null, null);
    }

    public static void setOnSignedListener(SignaturePad view, OnSignedListener onSignedListener) {
        setOnSignedListener(view, null, onSignedListener, null);
    }

    public static void setOnSignedListener(SignaturePad view, OnClearListener onClearListener) {
        setOnSignedListener(view, null, null, onClearListener);
    }

    public static void setOnSignedListener(SignaturePad view, final OnStartSigningListener onStartSigningListener, final OnSignedListener onSignedListener, final OnClearListener onClearListener) {
        view.setOnSignedListener(new SignaturePad.OnSignedListener() { // from class: com.github.gcacace.signaturepad.utils.SignaturePadBindingAdapter.1
            @Override // com.github.gcacace.signaturepad.views.SignaturePad.OnSignedListener
            public void onStartSigning() {
                if (OnStartSigningListener.this != null) {
                    OnStartSigningListener.this.onStartSigning();
                }
            }

            @Override // com.github.gcacace.signaturepad.views.SignaturePad.OnSignedListener
            public void onSigned() {
                if (onSignedListener != null) {
                    onSignedListener.onSigned();
                }
            }

            @Override // com.github.gcacace.signaturepad.views.SignaturePad.OnSignedListener
            public void onClear() {
                if (onClearListener != null) {
                    onClearListener.onClear();
                }
            }
        });
    }
}
