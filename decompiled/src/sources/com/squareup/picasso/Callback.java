package com.squareup.picasso;

/* loaded from: classes17.dex */
public interface Callback {
    void onError(Exception exc);

    void onSuccess();

    public static class EmptyCallback implements Callback {
        @Override // com.squareup.picasso.Callback
        public void onSuccess() {
        }

        @Override // com.squareup.picasso.Callback
        public void onError(Exception e) {
        }
    }
}
