package com.ingenious.androidbookmarksalesupgrade.model.request;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ForgetRequest.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0011"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/request/ForgetRequest;", "", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "setEmail", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes11.dex */
public final /* data */ class ForgetRequest {

    @SerializedName("email")
    private String email;

    /* JADX WARN: Multi-variable type inference failed */
    public ForgetRequest() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ ForgetRequest copy$default(ForgetRequest forgetRequest, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = forgetRequest.email;
        }
        return forgetRequest.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    public final ForgetRequest copy(String email) {
        Intrinsics.checkNotNullParameter(email, "email");
        return new ForgetRequest(email);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ForgetRequest) && Intrinsics.areEqual(this.email, ((ForgetRequest) other).email);
    }

    public int hashCode() {
        return this.email.hashCode();
    }

    public String toString() {
        return "ForgetRequest(email=" + this.email + ")";
    }

    public ForgetRequest(String email) {
        Intrinsics.checkNotNullParameter(email, "email");
        this.email = email;
    }

    public /* synthetic */ ForgetRequest(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str);
    }

    public final String getEmail() {
        return this.email;
    }

    public final void setEmail(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.email = str;
    }
}
