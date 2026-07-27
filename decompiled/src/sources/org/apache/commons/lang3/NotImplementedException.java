package org.apache.commons.lang3;

/* loaded from: classes17.dex */
public class NotImplementedException extends UnsupportedOperationException {
    private static final long serialVersionUID = 20131021;
    private final String code;

    public NotImplementedException() {
        this.code = null;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NotImplementedException(String message) {
        this(message, (String) null);
    }

    public NotImplementedException(Throwable cause) {
        this(cause, (String) null);
    }

    public NotImplementedException(String message, Throwable cause) {
        this(message, cause, null);
    }

    public NotImplementedException(String message, String code) {
        super(message);
        this.code = code;
    }

    public NotImplementedException(Throwable cause, String code) {
        super(cause);
        this.code = code;
    }

    public NotImplementedException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
