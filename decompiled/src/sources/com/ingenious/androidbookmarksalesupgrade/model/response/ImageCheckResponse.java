package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageCheckResponse.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J&\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/ImageCheckResponse;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;", "imageUrl", "", "currentTime", "", "<init>", "(Ljava/lang/Boolean;Ljava/lang/String;)V", "getImageUrl", "()Ljava/lang/Boolean;", "setImageUrl", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getCurrentTime", "()Ljava/lang/String;", "setCurrentTime", "(Ljava/lang/String;)V", "component1", "component2", "copy", "(Ljava/lang/Boolean;Ljava/lang/String;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/ImageCheckResponse;", "equals", "other", "", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class ImageCheckResponse extends GlobalResponse {

    @SerializedName("currentTime")
    private String currentTime;

    @SerializedName("imageUrl")
    private Boolean imageUrl;

    /* JADX WARN: Multi-variable type inference failed */
    public ImageCheckResponse() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ ImageCheckResponse copy$default(ImageCheckResponse imageCheckResponse, Boolean bool, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = imageCheckResponse.imageUrl;
        }
        if ((i & 2) != 0) {
            str = imageCheckResponse.currentTime;
        }
        return imageCheckResponse.copy(bool, str);
    }

    /* renamed from: component1, reason: from getter */
    public final Boolean getImageUrl() {
        return this.imageUrl;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCurrentTime() {
        return this.currentTime;
    }

    public final ImageCheckResponse copy(Boolean imageUrl, String currentTime) {
        return new ImageCheckResponse(imageUrl, currentTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImageCheckResponse)) {
            return false;
        }
        ImageCheckResponse imageCheckResponse = (ImageCheckResponse) other;
        return Intrinsics.areEqual(this.imageUrl, imageCheckResponse.imageUrl) && Intrinsics.areEqual(this.currentTime, imageCheckResponse.currentTime);
    }

    public int hashCode() {
        return ((this.imageUrl == null ? 0 : this.imageUrl.hashCode()) * 31) + (this.currentTime != null ? this.currentTime.hashCode() : 0);
    }

    public String toString() {
        return "ImageCheckResponse(imageUrl=" + this.imageUrl + ", currentTime=" + this.currentTime + ")";
    }

    public /* synthetic */ ImageCheckResponse(Boolean bool, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : bool, (i & 2) != 0 ? null : str);
    }

    public final Boolean getImageUrl() {
        return this.imageUrl;
    }

    public final void setImageUrl(Boolean bool) {
        this.imageUrl = bool;
    }

    public final String getCurrentTime() {
        return this.currentTime;
    }

    public final void setCurrentTime(String str) {
        this.currentTime = str;
    }

    public ImageCheckResponse(Boolean imageUrl, String currentTime) {
        super(null, null, null, 7, null);
        this.imageUrl = imageUrl;
        this.currentTime = currentTime;
    }
}
