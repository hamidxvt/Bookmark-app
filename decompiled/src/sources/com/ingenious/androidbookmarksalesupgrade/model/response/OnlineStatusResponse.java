package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OnlineStatusResponse.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\rJ&\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/OnlineStatusResponse;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;", "startTime", "", "jobStarted", "", "<init>", "(Ljava/lang/String;Ljava/lang/Boolean;)V", "getStartTime", "()Ljava/lang/String;", "setStartTime", "(Ljava/lang/String;)V", "getJobStarted", "()Ljava/lang/Boolean;", "setJobStarted", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Boolean;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/OnlineStatusResponse;", "equals", "other", "", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class OnlineStatusResponse extends GlobalResponse {

    @SerializedName("jobStarted")
    private Boolean jobStarted;

    @SerializedName("startTime")
    private String startTime;

    /* JADX WARN: Multi-variable type inference failed */
    public OnlineStatusResponse() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ OnlineStatusResponse copy$default(OnlineStatusResponse onlineStatusResponse, String str, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = onlineStatusResponse.startTime;
        }
        if ((i & 2) != 0) {
            bool = onlineStatusResponse.jobStarted;
        }
        return onlineStatusResponse.copy(str, bool);
    }

    /* renamed from: component1, reason: from getter */
    public final String getStartTime() {
        return this.startTime;
    }

    /* renamed from: component2, reason: from getter */
    public final Boolean getJobStarted() {
        return this.jobStarted;
    }

    public final OnlineStatusResponse copy(String startTime, Boolean jobStarted) {
        return new OnlineStatusResponse(startTime, jobStarted);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OnlineStatusResponse)) {
            return false;
        }
        OnlineStatusResponse onlineStatusResponse = (OnlineStatusResponse) other;
        return Intrinsics.areEqual(this.startTime, onlineStatusResponse.startTime) && Intrinsics.areEqual(this.jobStarted, onlineStatusResponse.jobStarted);
    }

    public int hashCode() {
        return ((this.startTime == null ? 0 : this.startTime.hashCode()) * 31) + (this.jobStarted != null ? this.jobStarted.hashCode() : 0);
    }

    public String toString() {
        return "OnlineStatusResponse(startTime=" + this.startTime + ", jobStarted=" + this.jobStarted + ")";
    }

    public /* synthetic */ OnlineStatusResponse(String str, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : bool);
    }

    public final String getStartTime() {
        return this.startTime;
    }

    public final void setStartTime(String str) {
        this.startTime = str;
    }

    public final Boolean getJobStarted() {
        return this.jobStarted;
    }

    public final void setJobStarted(Boolean bool) {
        this.jobStarted = bool;
    }

    public OnlineStatusResponse(String startTime, Boolean jobStarted) {
        super(null, null, null, 7, null);
        this.startTime = startTime;
        this.jobStarted = jobStarted;
    }
}
