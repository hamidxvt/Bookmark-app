package androidx.privacysandbox.ads.adservices.adselection;

import android.adservices.adselection.UpdateAdCounterHistogramRequest;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.privacysandbox.ads.adservices.common.AdTechIdentifier;
import androidx.privacysandbox.ads.adservices.common.ExperimentalFeatures;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdateAdCounterHistogramRequest.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\r\u0010\u000f\u001a\u00020\u0010H\u0001¢\u0006\u0002\b\u0011J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0005H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Landroidx/privacysandbox/ads/adservices/adselection/UpdateAdCounterHistogramRequest;", "", "adSelectionId", "", "adEventType", "", "callerAdTech", "Landroidx/privacysandbox/ads/adservices/common/AdTechIdentifier;", "(JILandroidx/privacysandbox/ads/adservices/common/AdTechIdentifier;)V", "getAdEventType", "()I", "getAdSelectionId", "()J", "getCallerAdTech", "()Landroidx/privacysandbox/ads/adservices/common/AdTechIdentifier;", "convertToAdServices", "Landroid/adservices/adselection/UpdateAdCounterHistogramRequest;", "convertToAdServices$ads_adservices_release", "equals", "", "other", "hashCode", "toString", "", "ads-adservices_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@ExperimentalFeatures.Ext8OptIn
/* loaded from: classes.dex */
public final class UpdateAdCounterHistogramRequest {
    private final int adEventType;
    private final long adSelectionId;
    private final AdTechIdentifier callerAdTech;

    public UpdateAdCounterHistogramRequest(long adSelectionId, int adEventType, AdTechIdentifier callerAdTech) {
        Intrinsics.checkNotNullParameter(callerAdTech, "callerAdTech");
        this.adSelectionId = adSelectionId;
        this.adEventType = adEventType;
        this.callerAdTech = callerAdTech;
        if (!(this.adEventType != 0)) {
            throw new IllegalArgumentException("Win event types cannot be manually updated.".toString());
        }
        if (this.adEventType == 1 || this.adEventType == 2 || this.adEventType == 3) {
        } else {
            throw new IllegalArgumentException("Ad event type must be one of AD_EVENT_TYPE_IMPRESSION, AD_EVENT_TYPE_VIEW, or AD_EVENT_TYPE_CLICK".toString());
        }
    }

    public final long getAdSelectionId() {
        return this.adSelectionId;
    }

    public final int getAdEventType() {
        return this.adEventType;
    }

    public final AdTechIdentifier getCallerAdTech() {
        return this.callerAdTech;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof UpdateAdCounterHistogramRequest) {
            return this.adSelectionId == ((UpdateAdCounterHistogramRequest) other).adSelectionId && this.adEventType == ((UpdateAdCounterHistogramRequest) other).adEventType && Intrinsics.areEqual(this.callerAdTech, ((UpdateAdCounterHistogramRequest) other).callerAdTech);
        }
        return false;
    }

    public int hashCode() {
        int hash = Long.hashCode(this.adSelectionId);
        return (((hash * 31) + Integer.hashCode(this.adEventType)) * 31) + this.callerAdTech.hashCode();
    }

    public String toString() {
        String adEventTypeStr;
        switch (this.adEventType) {
            case 0:
                adEventTypeStr = "AD_EVENT_TYPE_WIN";
                break;
            case 1:
                adEventTypeStr = "AD_EVENT_TYPE_IMPRESSION";
                break;
            case 2:
                adEventTypeStr = "AD_EVENT_TYPE_VIEW";
                break;
            case 3:
                adEventTypeStr = "AD_EVENT_TYPE_CLICK";
                break;
            default:
                adEventTypeStr = "Invalid ad event type";
                break;
        }
        return "UpdateAdCounterHistogramRequest: adSelectionId=" + this.adSelectionId + ", adEventType=" + adEventTypeStr + ", callerAdTech=" + this.callerAdTech;
    }

    public final android.adservices.adselection.UpdateAdCounterHistogramRequest convertToAdServices$ads_adservices_release() {
        android.adservices.adselection.UpdateAdCounterHistogramRequest build = new UpdateAdCounterHistogramRequest.Builder(this.adSelectionId, this.adEventType, this.callerAdTech.convertToAdServices$ads_adservices_release()).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(\n               …   )\n            .build()");
        return build;
    }
}
