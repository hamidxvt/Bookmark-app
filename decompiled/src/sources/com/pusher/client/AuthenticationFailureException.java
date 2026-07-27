package com.pusher.client;

/* loaded from: classes17.dex */
public class AuthenticationFailureException extends RuntimeException {
    private static final long serialVersionUID = -7208133561904200801L;

    public AuthenticationFailureException() {
    }

    public AuthenticationFailureException(String msg) {
        super(msg);
    }

    public AuthenticationFailureException(Exception cause) {
        super(cause);
    }

    public AuthenticationFailureException(String msg, Exception cause) {
        super(msg, cause);
    }
}
