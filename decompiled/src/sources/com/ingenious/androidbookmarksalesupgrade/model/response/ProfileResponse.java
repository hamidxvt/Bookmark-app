package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProfileResponse.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\n\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0013"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/ProfileResponse;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ProfileData;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/model/response/ProfileData;)V", "getData", "()Lcom/ingenious/androidbookmarksalesupgrade/model/response/ProfileData;", "setData", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class ProfileResponse extends GlobalResponse {

    @SerializedName(Constants.ScionAnalytics.MessageType.DATA_MESSAGE)
    private ProfileData data;

    /* JADX WARN: Multi-variable type inference failed */
    public ProfileResponse() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ ProfileResponse copy$default(ProfileResponse profileResponse, ProfileData profileData, int i, Object obj) {
        if ((i & 1) != 0) {
            profileData = profileResponse.data;
        }
        return profileResponse.copy(profileData);
    }

    /* renamed from: component1, reason: from getter */
    public final ProfileData getData() {
        return this.data;
    }

    public final ProfileResponse copy(ProfileData data) {
        return new ProfileResponse(data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ProfileResponse) && Intrinsics.areEqual(this.data, ((ProfileResponse) other).data);
    }

    public int hashCode() {
        if (this.data == null) {
            return 0;
        }
        return this.data.hashCode();
    }

    public String toString() {
        return "ProfileResponse(data=" + this.data + ")";
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ ProfileResponse(ProfileData profileData, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(r0);
        ProfileData profileData2;
        if ((i & 1) == 0) {
            profileData2 = profileData;
        } else {
            profileData2 = new ProfileData(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 2097151, null);
        }
    }

    public final ProfileData getData() {
        return this.data;
    }

    public final void setData(ProfileData profileData) {
        this.data = profileData;
    }

    public ProfileResponse(ProfileData data) {
        super(null, null, null, 7, null);
        this.data = data;
    }
}
