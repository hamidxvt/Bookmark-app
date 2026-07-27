package org.java_websocket.exceptions;

/* loaded from: classes17.dex */
public class IncompleteException extends Exception {
    private static final long serialVersionUID = 7330519489840500997L;
    private final int preferredSize;

    public IncompleteException(int preferredSize) {
        this.preferredSize = preferredSize;
    }

    public int getPreferredSize() {
        return this.preferredSize;
    }
}
