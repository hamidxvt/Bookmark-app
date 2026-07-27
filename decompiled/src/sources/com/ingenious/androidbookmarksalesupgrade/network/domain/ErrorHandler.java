package com.ingenious.androidbookmarksalesupgrade.network.domain;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: APIError.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0017"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/network/domain/ErrorHandler;", "", "message", "", "messageID", "", "errorStatus", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getMessageID", "()I", "getErrorStatus", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes15.dex */
public final /* data */ class ErrorHandler {
    private final String errorStatus;
    private final String message;
    private final int messageID;

    public static /* synthetic */ ErrorHandler copy$default(ErrorHandler errorHandler, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = errorHandler.message;
        }
        if ((i2 & 2) != 0) {
            i = errorHandler.messageID;
        }
        if ((i2 & 4) != 0) {
            str2 = errorHandler.errorStatus;
        }
        return errorHandler.copy(str, i, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* renamed from: component2, reason: from getter */
    public final int getMessageID() {
        return this.messageID;
    }

    /* renamed from: component3, reason: from getter */
    public final String getErrorStatus() {
        return this.errorStatus;
    }

    public final ErrorHandler copy(String message, int messageID, String errorStatus) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(errorStatus, "errorStatus");
        return new ErrorHandler(message, messageID, errorStatus);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ErrorHandler)) {
            return false;
        }
        ErrorHandler errorHandler = (ErrorHandler) other;
        return Intrinsics.areEqual(this.message, errorHandler.message) && this.messageID == errorHandler.messageID && Intrinsics.areEqual(this.errorStatus, errorHandler.errorStatus);
    }

    public int hashCode() {
        return (((this.message.hashCode() * 31) + Integer.hashCode(this.messageID)) * 31) + this.errorStatus.hashCode();
    }

    public String toString() {
        return "ErrorHandler(message=" + this.message + ", messageID=" + this.messageID + ", errorStatus=" + this.errorStatus + ")";
    }

    public ErrorHandler(String message, int messageID, String errorStatus) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(errorStatus, "errorStatus");
        this.message = message;
        this.messageID = messageID;
        this.errorStatus = errorStatus;
    }

    public /* synthetic */ ErrorHandler(String str, int i, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? 0 : i, str2);
    }

    public final String getMessage() {
        return this.message;
    }

    public final int getMessageID() {
        return this.messageID;
    }

    public final String getErrorStatus() {
        return this.errorStatus;
    }
}
