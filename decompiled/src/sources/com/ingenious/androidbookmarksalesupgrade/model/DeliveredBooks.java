package com.ingenious.androidbookmarksalesupgrade.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ActivityLog.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/DeliveredBooks;", "", FirebaseAnalytics.Param.SUCCESS, "", "message", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lcom/ingenious/androidbookmarksalesupgrade/model/DeliveredBooksData;", "<init>", "(ZLjava/lang/String;Lcom/ingenious/androidbookmarksalesupgrade/model/DeliveredBooksData;)V", "getSuccess", "()Z", "getMessage", "()Ljava/lang/String;", "getData", "()Lcom/ingenious/androidbookmarksalesupgrade/model/DeliveredBooksData;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes8.dex */
public final /* data */ class DeliveredBooks {
    private final DeliveredBooksData data;
    private final String message;
    private final boolean success;

    public DeliveredBooks() {
        this(false, null, null, 7, null);
    }

    public static /* synthetic */ DeliveredBooks copy$default(DeliveredBooks deliveredBooks, boolean z, String str, DeliveredBooksData deliveredBooksData, int i, Object obj) {
        if ((i & 1) != 0) {
            z = deliveredBooks.success;
        }
        if ((i & 2) != 0) {
            str = deliveredBooks.message;
        }
        if ((i & 4) != 0) {
            deliveredBooksData = deliveredBooks.data;
        }
        return deliveredBooks.copy(z, str, deliveredBooksData);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getSuccess() {
        return this.success;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* renamed from: component3, reason: from getter */
    public final DeliveredBooksData getData() {
        return this.data;
    }

    public final DeliveredBooks copy(boolean success, String message, DeliveredBooksData data) {
        Intrinsics.checkNotNullParameter(message, "message");
        return new DeliveredBooks(success, message, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeliveredBooks)) {
            return false;
        }
        DeliveredBooks deliveredBooks = (DeliveredBooks) other;
        return this.success == deliveredBooks.success && Intrinsics.areEqual(this.message, deliveredBooks.message) && Intrinsics.areEqual(this.data, deliveredBooks.data);
    }

    public int hashCode() {
        return (((Boolean.hashCode(this.success) * 31) + this.message.hashCode()) * 31) + (this.data == null ? 0 : this.data.hashCode());
    }

    public String toString() {
        return "DeliveredBooks(success=" + this.success + ", message=" + this.message + ", data=" + this.data + ")";
    }

    public DeliveredBooks(boolean success, String message, DeliveredBooksData data) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public /* synthetic */ DeliveredBooks(boolean z, String str, DeliveredBooksData deliveredBooksData, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? "" : str, (i & 4) != 0 ? null : deliveredBooksData);
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public final String getMessage() {
        return this.message;
    }

    public final DeliveredBooksData getData() {
        return this.data;
    }
}
