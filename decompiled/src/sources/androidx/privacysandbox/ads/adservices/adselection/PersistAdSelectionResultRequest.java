package androidx.privacysandbox.ads.adservices.adselection;

import android.adservices.adselection.PersistAdSelectionResultRequest;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.privacysandbox.ads.adservices.common.AdTechIdentifier;
import androidx.privacysandbox.ads.adservices.common.ExperimentalFeatures;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PersistAdSelectionResultRequest.kt */
@ExperimentalFeatures.Ext10OptIn
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\r\u0010\u000f\u001a\u00020\u0010H\u0001¢\u0006\u0002\b\u0011J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Landroidx/privacysandbox/ads/adservices/adselection/PersistAdSelectionResultRequest;", "", "adSelectionId", "", "seller", "Landroidx/privacysandbox/ads/adservices/common/AdTechIdentifier;", "adSelectionResult", "", "(JLandroidx/privacysandbox/ads/adservices/common/AdTechIdentifier;[B)V", "getAdSelectionId", "()J", "getAdSelectionResult", "()[B", "getSeller", "()Landroidx/privacysandbox/ads/adservices/common/AdTechIdentifier;", "convertToAdServices", "Landroid/adservices/adselection/PersistAdSelectionResultRequest;", "convertToAdServices$ads_adservices_release", "equals", "", "other", "hashCode", "", "toString", "", "ads-adservices_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class PersistAdSelectionResultRequest {
    private final long adSelectionId;
    private final byte[] adSelectionResult;
    private final AdTechIdentifier seller;

    public PersistAdSelectionResultRequest(long adSelectionId, AdTechIdentifier seller, byte[] adSelectionResult) {
        this.adSelectionId = adSelectionId;
        this.seller = seller;
        this.adSelectionResult = adSelectionResult;
    }

    public /* synthetic */ PersistAdSelectionResultRequest(long j, AdTechIdentifier adTechIdentifier, byte[] bArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, (i & 2) != 0 ? null : adTechIdentifier, (i & 4) != 0 ? null : bArr);
    }

    public final long getAdSelectionId() {
        return this.adSelectionId;
    }

    public final AdTechIdentifier getSeller() {
        return this.seller;
    }

    public final byte[] getAdSelectionResult() {
        return this.adSelectionResult;
    }

    public PersistAdSelectionResultRequest(long adSelectionId) {
        this(adSelectionId, null, null, 6, null);
    }

    public PersistAdSelectionResultRequest(long adSelectionId, AdTechIdentifier seller) {
        this(adSelectionId, seller, null, 4, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof PersistAdSelectionResultRequest) {
            return this.adSelectionId == ((PersistAdSelectionResultRequest) other).adSelectionId && Intrinsics.areEqual(this.seller, ((PersistAdSelectionResultRequest) other).seller) && Arrays.equals(this.adSelectionResult, ((PersistAdSelectionResultRequest) other).adSelectionResult);
        }
        return false;
    }

    public int hashCode() {
        int hash = Long.hashCode(this.adSelectionId);
        int i = hash * 31;
        AdTechIdentifier adTechIdentifier = this.seller;
        int hash2 = i + (adTechIdentifier != null ? adTechIdentifier.hashCode() : 0);
        int hash3 = hash2 * 31;
        byte[] bArr = this.adSelectionResult;
        return hash3 + (bArr != null ? bArr.hashCode() : 0);
    }

    public String toString() {
        return "PersistAdSelectionResultRequest: adSelectionId=" + this.adSelectionId + ", seller=" + this.seller + ", adSelectionResult=" + this.adSelectionResult;
    }

    public final android.adservices.adselection.PersistAdSelectionResultRequest convertToAdServices$ads_adservices_release() {
        PersistAdSelectionResultRequest.Builder adSelectionId = new PersistAdSelectionResultRequest.Builder().setAdSelectionId(this.adSelectionId);
        AdTechIdentifier adTechIdentifier = this.seller;
        android.adservices.adselection.PersistAdSelectionResultRequest build = adSelectionId.setSeller(adTechIdentifier != null ? adTechIdentifier.convertToAdServices$ads_adservices_release() : null).setAdSelectionResult(this.adSelectionResult).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…ult)\n            .build()");
        return build;
    }
}
