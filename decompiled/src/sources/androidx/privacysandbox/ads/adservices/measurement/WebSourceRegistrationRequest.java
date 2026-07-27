package androidx.privacysandbox.ads.adservices.measurement;

import android.adservices.measurement.WebSourceRegistrationRequest;
import android.net.Uri;
import android.view.InputEvent;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WebSourceRegistrationRequest.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001 BK\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\fJ\r\u0010\u0016\u001a\u00020\u0017H\u0001¢\u0006\u0002\b\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Landroidx/privacysandbox/ads/adservices/measurement/WebSourceRegistrationRequest;", "", "webSourceParams", "", "Landroidx/privacysandbox/ads/adservices/measurement/WebSourceParams;", "topOriginUri", "Landroid/net/Uri;", "inputEvent", "Landroid/view/InputEvent;", "appDestination", "webDestination", "verifiedDestination", "(Ljava/util/List;Landroid/net/Uri;Landroid/view/InputEvent;Landroid/net/Uri;Landroid/net/Uri;Landroid/net/Uri;)V", "getAppDestination", "()Landroid/net/Uri;", "getInputEvent", "()Landroid/view/InputEvent;", "getTopOriginUri", "getVerifiedDestination", "getWebDestination", "getWebSourceParams", "()Ljava/util/List;", "convertToAdServices", "Landroid/adservices/measurement/WebSourceRegistrationRequest;", "convertToAdServices$ads_adservices_release", "equals", "", "other", "hashCode", "", "toString", "", "Builder", "ads-adservices_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class WebSourceRegistrationRequest {

    /* renamed from: appDestination, reason: from kotlin metadata and from toString */
    private final Uri AppDestination;

    /* renamed from: inputEvent, reason: from kotlin metadata and from toString */
    private final InputEvent InputEvent;

    /* renamed from: topOriginUri, reason: from kotlin metadata and from toString */
    private final Uri TopOriginUri;

    /* renamed from: verifiedDestination, reason: from kotlin metadata and from toString */
    private final Uri VerifiedDestination;

    /* renamed from: webDestination, reason: from kotlin metadata and from toString */
    private final Uri WebDestination;

    /* renamed from: webSourceParams, reason: from kotlin metadata and from toString */
    private final List<WebSourceParams> WebSourceParams;

    public WebSourceRegistrationRequest(List<WebSourceParams> webSourceParams, Uri topOriginUri, InputEvent inputEvent, Uri appDestination, Uri webDestination, Uri verifiedDestination) {
        Intrinsics.checkNotNullParameter(webSourceParams, "webSourceParams");
        Intrinsics.checkNotNullParameter(topOriginUri, "topOriginUri");
        this.WebSourceParams = webSourceParams;
        this.TopOriginUri = topOriginUri;
        this.InputEvent = inputEvent;
        this.AppDestination = appDestination;
        this.WebDestination = webDestination;
        this.VerifiedDestination = verifiedDestination;
    }

    public /* synthetic */ WebSourceRegistrationRequest(List list, Uri uri, InputEvent inputEvent, Uri uri2, Uri uri3, Uri uri4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, uri, (i & 4) != 0 ? null : inputEvent, (i & 8) != 0 ? null : uri2, (i & 16) != 0 ? null : uri3, (i & 32) != 0 ? null : uri4);
    }

    public final List<WebSourceParams> getWebSourceParams() {
        return this.WebSourceParams;
    }

    public final Uri getTopOriginUri() {
        return this.TopOriginUri;
    }

    public final InputEvent getInputEvent() {
        return this.InputEvent;
    }

    public final Uri getAppDestination() {
        return this.AppDestination;
    }

    public final Uri getWebDestination() {
        return this.WebDestination;
    }

    public final Uri getVerifiedDestination() {
        return this.VerifiedDestination;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof WebSourceRegistrationRequest) {
            return Intrinsics.areEqual(this.WebSourceParams, ((WebSourceRegistrationRequest) other).WebSourceParams) && Intrinsics.areEqual(this.WebDestination, ((WebSourceRegistrationRequest) other).WebDestination) && Intrinsics.areEqual(this.AppDestination, ((WebSourceRegistrationRequest) other).AppDestination) && Intrinsics.areEqual(this.TopOriginUri, ((WebSourceRegistrationRequest) other).TopOriginUri) && Intrinsics.areEqual(this.InputEvent, ((WebSourceRegistrationRequest) other).InputEvent) && Intrinsics.areEqual(this.VerifiedDestination, ((WebSourceRegistrationRequest) other).VerifiedDestination);
        }
        return false;
    }

    public int hashCode() {
        int hash = (this.WebSourceParams.hashCode() * 31) + this.TopOriginUri.hashCode();
        if (this.InputEvent != null) {
            hash = (hash * 31) + this.InputEvent.hashCode();
        }
        if (this.AppDestination != null) {
            hash = (hash * 31) + this.AppDestination.hashCode();
        }
        if (this.WebDestination != null) {
            hash = (hash * 31) + this.WebDestination.hashCode();
        }
        int hash2 = (hash * 31) + this.TopOriginUri.hashCode();
        if (this.InputEvent != null) {
            hash2 = (hash2 * 31) + this.InputEvent.hashCode();
        }
        if (this.VerifiedDestination != null) {
            return (hash2 * 31) + this.VerifiedDestination.hashCode();
        }
        return hash2;
    }

    public String toString() {
        String vals = "WebSourceParams=[" + this.WebSourceParams + "], TopOriginUri=" + this.TopOriginUri + ", InputEvent=" + this.InputEvent + ", AppDestination=" + this.AppDestination + ", WebDestination=" + this.WebDestination + ", VerifiedDestination=" + this.VerifiedDestination;
        return "WebSourceRegistrationRequest { " + vals + " }";
    }

    public final android.adservices.measurement.WebSourceRegistrationRequest convertToAdServices$ads_adservices_release() {
        android.adservices.measurement.WebSourceRegistrationRequest build = new WebSourceRegistrationRequest.Builder(WebSourceParams.INSTANCE.convertWebSourceParams$ads_adservices_release(this.WebSourceParams), this.TopOriginUri).setWebDestination(this.WebDestination).setAppDestination(this.AppDestination).setInputEvent(this.InputEvent).setVerifiedDestination(this.VerifiedDestination).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(\n               …ion)\n            .build()");
        return build;
    }

    /* compiled from: WebSourceRegistrationRequest.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u0011\u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u0012\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u0006R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/privacysandbox/ads/adservices/measurement/WebSourceRegistrationRequest$Builder;", "", "webSourceParams", "", "Landroidx/privacysandbox/ads/adservices/measurement/WebSourceParams;", "topOriginUri", "Landroid/net/Uri;", "(Ljava/util/List;Landroid/net/Uri;)V", "appDestination", "inputEvent", "Landroid/view/InputEvent;", "verifiedDestination", "webDestination", "build", "Landroidx/privacysandbox/ads/adservices/measurement/WebSourceRegistrationRequest;", "setAppDestination", "setInputEvent", "setVerifiedDestination", "setWebDestination", "ads-adservices_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Builder {
        private Uri appDestination;
        private InputEvent inputEvent;
        private final Uri topOriginUri;
        private Uri verifiedDestination;
        private Uri webDestination;
        private final List<WebSourceParams> webSourceParams;

        public Builder(List<WebSourceParams> webSourceParams, Uri topOriginUri) {
            Intrinsics.checkNotNullParameter(webSourceParams, "webSourceParams");
            Intrinsics.checkNotNullParameter(topOriginUri, "topOriginUri");
            this.webSourceParams = webSourceParams;
            this.topOriginUri = topOriginUri;
        }

        public final Builder setInputEvent(InputEvent inputEvent) {
            Intrinsics.checkNotNullParameter(inputEvent, "inputEvent");
            Builder $this$setInputEvent_u24lambda_u240 = this;
            $this$setInputEvent_u24lambda_u240.inputEvent = inputEvent;
            return this;
        }

        public final Builder setAppDestination(Uri appDestination) {
            Builder $this$setAppDestination_u24lambda_u241 = this;
            $this$setAppDestination_u24lambda_u241.appDestination = appDestination;
            return this;
        }

        public final Builder setWebDestination(Uri webDestination) {
            Builder $this$setWebDestination_u24lambda_u242 = this;
            $this$setWebDestination_u24lambda_u242.webDestination = webDestination;
            return this;
        }

        public final Builder setVerifiedDestination(Uri verifiedDestination) {
            Builder $this$setVerifiedDestination_u24lambda_u243 = this;
            $this$setVerifiedDestination_u24lambda_u243.verifiedDestination = verifiedDestination;
            return this;
        }

        public final WebSourceRegistrationRequest build() {
            return new WebSourceRegistrationRequest(this.webSourceParams, this.topOriginUri, this.inputEvent, this.appDestination, this.webDestination, this.verifiedDestination);
        }
    }
}
