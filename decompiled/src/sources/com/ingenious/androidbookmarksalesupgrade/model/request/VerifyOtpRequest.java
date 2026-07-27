package com.ingenious.androidbookmarksalesupgrade.model.request;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VerifyOtpRequest.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0011"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/request/VerifyOtpRequest;", "", "otp", "", "<init>", "(Ljava/lang/String;)V", "getOtp", "()Ljava/lang/String;", "setOtp", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes11.dex */
public final /* data */ class VerifyOtpRequest {

    @SerializedName("otp")
    private String otp;

    /* JADX WARN: Multi-variable type inference failed */
    public VerifyOtpRequest() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ VerifyOtpRequest copy$default(VerifyOtpRequest verifyOtpRequest, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = verifyOtpRequest.otp;
        }
        return verifyOtpRequest.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getOtp() {
        return this.otp;
    }

    public final VerifyOtpRequest copy(String otp) {
        Intrinsics.checkNotNullParameter(otp, "otp");
        return new VerifyOtpRequest(otp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof VerifyOtpRequest) && Intrinsics.areEqual(this.otp, ((VerifyOtpRequest) other).otp);
    }

    public int hashCode() {
        return this.otp.hashCode();
    }

    public String toString() {
        return "VerifyOtpRequest(otp=" + this.otp + ")";
    }

    public VerifyOtpRequest(String otp) {
        Intrinsics.checkNotNullParameter(otp, "otp");
        this.otp = otp;
    }

    public /* synthetic */ VerifyOtpRequest(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str);
    }

    public final String getOtp() {
        return this.otp;
    }

    public final void setOtp(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.otp = str;
    }
}
