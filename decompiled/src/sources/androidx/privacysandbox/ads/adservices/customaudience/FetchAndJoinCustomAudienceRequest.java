package androidx.privacysandbox.ads.adservices.customaudience;

import android.adservices.customaudience.FetchAndJoinCustomAudienceRequest;
import android.net.Uri;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.privacysandbox.ads.adservices.common.AdSelectionSignals;
import androidx.privacysandbox.ads.adservices.common.ExperimentalFeatures;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.time.Instant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FetchAndJoinCustomAudienceRequest.kt */
@ExperimentalFeatures.Ext10OptIn
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B?\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\r\u0010\u0015\u001a\u00020\u0016H\u0001¢\u0006\u0002\b\u0017J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0005H\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001e"}, d2 = {"Landroidx/privacysandbox/ads/adservices/customaudience/FetchAndJoinCustomAudienceRequest;", "", "fetchUri", "Landroid/net/Uri;", AppMeasurementSdk.ConditionalUserProperty.NAME, "", "activationTime", "Ljava/time/Instant;", "expirationTime", "userBiddingSignals", "Landroidx/privacysandbox/ads/adservices/common/AdSelectionSignals;", "(Landroid/net/Uri;Ljava/lang/String;Ljava/time/Instant;Ljava/time/Instant;Landroidx/privacysandbox/ads/adservices/common/AdSelectionSignals;)V", "getActivationTime", "()Ljava/time/Instant;", "getExpirationTime", "getFetchUri", "()Landroid/net/Uri;", "getName", "()Ljava/lang/String;", "getUserBiddingSignals", "()Landroidx/privacysandbox/ads/adservices/common/AdSelectionSignals;", "convertToAdServices", "Landroid/adservices/customaudience/FetchAndJoinCustomAudienceRequest;", "convertToAdServices$ads_adservices_release", "equals", "", "other", "hashCode", "", "toString", "ads-adservices_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class FetchAndJoinCustomAudienceRequest {
    private final Instant activationTime;
    private final Instant expirationTime;
    private final Uri fetchUri;
    private final String name;
    private final AdSelectionSignals userBiddingSignals;

    public FetchAndJoinCustomAudienceRequest(Uri fetchUri, String name, Instant activationTime, Instant expirationTime, AdSelectionSignals userBiddingSignals) {
        Intrinsics.checkNotNullParameter(fetchUri, "fetchUri");
        this.fetchUri = fetchUri;
        this.name = name;
        this.activationTime = activationTime;
        this.expirationTime = expirationTime;
        this.userBiddingSignals = userBiddingSignals;
    }

    public /* synthetic */ FetchAndJoinCustomAudienceRequest(Uri uri, String str, Instant instant, Instant instant2, AdSelectionSignals adSelectionSignals, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(uri, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : instant, (i & 8) != 0 ? null : instant2, (i & 16) != 0 ? null : adSelectionSignals);
    }

    public final Uri getFetchUri() {
        return this.fetchUri;
    }

    public final String getName() {
        return this.name;
    }

    public final Instant getActivationTime() {
        return this.activationTime;
    }

    public final Instant getExpirationTime() {
        return this.expirationTime;
    }

    public final AdSelectionSignals getUserBiddingSignals() {
        return this.userBiddingSignals;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FetchAndJoinCustomAudienceRequest(Uri fetchUri) {
        this(fetchUri, null, null, null, null, 30, null);
        Intrinsics.checkNotNullParameter(fetchUri, "fetchUri");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FetchAndJoinCustomAudienceRequest(Uri fetchUri, String name) {
        this(fetchUri, name, null, null, null, 28, null);
        Intrinsics.checkNotNullParameter(fetchUri, "fetchUri");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FetchAndJoinCustomAudienceRequest(Uri fetchUri, String name, Instant activationTime) {
        this(fetchUri, name, activationTime, null, null, 24, null);
        Intrinsics.checkNotNullParameter(fetchUri, "fetchUri");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FetchAndJoinCustomAudienceRequest(Uri fetchUri, String name, Instant activationTime, Instant expirationTime) {
        this(fetchUri, name, activationTime, expirationTime, null, 16, null);
        Intrinsics.checkNotNullParameter(fetchUri, "fetchUri");
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof FetchAndJoinCustomAudienceRequest) {
            return Intrinsics.areEqual(this.fetchUri, ((FetchAndJoinCustomAudienceRequest) other).fetchUri) && Intrinsics.areEqual(this.name, ((FetchAndJoinCustomAudienceRequest) other).name) && Intrinsics.areEqual(this.activationTime, ((FetchAndJoinCustomAudienceRequest) other).activationTime) && Intrinsics.areEqual(this.expirationTime, ((FetchAndJoinCustomAudienceRequest) other).expirationTime) && Intrinsics.areEqual(this.userBiddingSignals, ((FetchAndJoinCustomAudienceRequest) other).userBiddingSignals);
        }
        return false;
    }

    public int hashCode() {
        int hash = this.fetchUri.hashCode();
        int i = hash * 31;
        String str = this.name;
        int hash2 = i + (str != null ? str.hashCode() : 0);
        int hash3 = hash2 * 31;
        Instant instant = this.activationTime;
        int hash4 = (hash3 + (instant != null ? instant.hashCode() : 0)) * 31;
        Instant instant2 = this.expirationTime;
        int hash5 = (hash4 + (instant2 != null ? instant2.hashCode() : 0)) * 31;
        AdSelectionSignals adSelectionSignals = this.userBiddingSignals;
        return hash5 + (adSelectionSignals != null ? adSelectionSignals.hashCode() : 0);
    }

    public String toString() {
        return "FetchAndJoinCustomAudienceRequest: fetchUri=" + this.fetchUri + ", name=" + this.name + ", activationTime=" + this.activationTime + ", expirationTime=" + this.expirationTime + ", userBiddingSignals=" + this.userBiddingSignals;
    }

    public final android.adservices.customaudience.FetchAndJoinCustomAudienceRequest convertToAdServices$ads_adservices_release() {
        FetchAndJoinCustomAudienceRequest.Builder expirationTime = new FetchAndJoinCustomAudienceRequest.Builder(this.fetchUri).setName(this.name).setActivationTime(this.activationTime).setExpirationTime(this.expirationTime);
        AdSelectionSignals adSelectionSignals = this.userBiddingSignals;
        android.adservices.customaudience.FetchAndJoinCustomAudienceRequest build = expirationTime.setUserBiddingSignals(adSelectionSignals != null ? adSelectionSignals.convertToAdServices$ads_adservices_release() : null).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(fetchUri)\n      …s())\n            .build()");
        return build;
    }
}
