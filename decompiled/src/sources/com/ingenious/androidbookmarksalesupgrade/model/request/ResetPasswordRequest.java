package com.ingenious.androidbookmarksalesupgrade.model.request;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ResetPasswordRequest.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001e\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000b¨\u0006\u001a"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/request/ResetPasswordRequest;", "", "email", "", "password", "passwordConfirmation", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "setEmail", "(Ljava/lang/String;)V", "getPassword", "setPassword", "getPasswordConfirmation", "setPasswordConfirmation", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes11.dex */
public final /* data */ class ResetPasswordRequest {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("password_confirmation")
    private String passwordConfirmation;

    public ResetPasswordRequest() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ ResetPasswordRequest copy$default(ResetPasswordRequest resetPasswordRequest, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = resetPasswordRequest.email;
        }
        if ((i & 2) != 0) {
            str2 = resetPasswordRequest.password;
        }
        if ((i & 4) != 0) {
            str3 = resetPasswordRequest.passwordConfirmation;
        }
        return resetPasswordRequest.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPassword() {
        return this.password;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPasswordConfirmation() {
        return this.passwordConfirmation;
    }

    public final ResetPasswordRequest copy(String email, String password, String passwordConfirmation) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(passwordConfirmation, "passwordConfirmation");
        return new ResetPasswordRequest(email, password, passwordConfirmation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResetPasswordRequest)) {
            return false;
        }
        ResetPasswordRequest resetPasswordRequest = (ResetPasswordRequest) other;
        return Intrinsics.areEqual(this.email, resetPasswordRequest.email) && Intrinsics.areEqual(this.password, resetPasswordRequest.password) && Intrinsics.areEqual(this.passwordConfirmation, resetPasswordRequest.passwordConfirmation);
    }

    public int hashCode() {
        return (((this.email.hashCode() * 31) + this.password.hashCode()) * 31) + this.passwordConfirmation.hashCode();
    }

    public String toString() {
        return "ResetPasswordRequest(email=" + this.email + ", password=" + this.password + ", passwordConfirmation=" + this.passwordConfirmation + ")";
    }

    public ResetPasswordRequest(String email, String password, String passwordConfirmation) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(passwordConfirmation, "passwordConfirmation");
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    public /* synthetic */ ResetPasswordRequest(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3);
    }

    public final String getEmail() {
        return this.email;
    }

    public final void setEmail(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.email = str;
    }

    public final String getPassword() {
        return this.password;
    }

    public final void setPassword(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.password = str;
    }

    public final String getPasswordConfirmation() {
        return this.passwordConfirmation;
    }

    public final void setPasswordConfirmation(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.passwordConfirmation = str;
    }
}
