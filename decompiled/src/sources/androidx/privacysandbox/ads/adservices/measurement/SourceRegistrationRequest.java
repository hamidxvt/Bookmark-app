package androidx.privacysandbox.ads.adservices.measurement;

import android.net.Uri;
import android.view.InputEvent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.privacysandbox.ads.adservices.common.ExperimentalFeatures;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SourceRegistrationRequest.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0013B\u001f\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Landroidx/privacysandbox/ads/adservices/measurement/SourceRegistrationRequest;", "", "registrationUris", "", "Landroid/net/Uri;", "inputEvent", "Landroid/view/InputEvent;", "(Ljava/util/List;Landroid/view/InputEvent;)V", "getInputEvent", "()Landroid/view/InputEvent;", "getRegistrationUris", "()Ljava/util/List;", "equals", "", "other", "hashCode", "", "toString", "", "Builder", "ads-adservices_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@ExperimentalFeatures.RegisterSourceOptIn
/* loaded from: classes.dex */
public final class SourceRegistrationRequest {

    /* renamed from: inputEvent, reason: from kotlin metadata and from toString */
    private final InputEvent InputEvent;

    /* renamed from: registrationUris, reason: from kotlin metadata and from toString */
    private final List<Uri> RegistrationUris;

    /* JADX WARN: Multi-variable type inference failed */
    public SourceRegistrationRequest(List<? extends Uri> registrationUris, InputEvent inputEvent) {
        Intrinsics.checkNotNullParameter(registrationUris, "registrationUris");
        this.RegistrationUris = registrationUris;
        this.InputEvent = inputEvent;
    }

    public /* synthetic */ SourceRegistrationRequest(List list, InputEvent inputEvent, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? null : inputEvent);
    }

    public final InputEvent getInputEvent() {
        return this.InputEvent;
    }

    public final List<Uri> getRegistrationUris() {
        return this.RegistrationUris;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof SourceRegistrationRequest) {
            return Intrinsics.areEqual(this.RegistrationUris, ((SourceRegistrationRequest) other).RegistrationUris) && Intrinsics.areEqual(this.InputEvent, ((SourceRegistrationRequest) other).InputEvent);
        }
        return false;
    }

    public int hashCode() {
        int hash = this.RegistrationUris.hashCode();
        if (this.InputEvent != null) {
            return (hash * 31) + this.InputEvent.hashCode();
        }
        return hash;
    }

    public String toString() {
        String vals = "RegistrationUris=[" + this.RegistrationUris + "], InputEvent=" + this.InputEvent;
        return "AppSourcesRegistrationRequest { " + vals + " }";
    }

    /* compiled from: SourceRegistrationRequest.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/privacysandbox/ads/adservices/measurement/SourceRegistrationRequest$Builder;", "", "registrationUris", "", "Landroid/net/Uri;", "(Ljava/util/List;)V", "inputEvent", "Landroid/view/InputEvent;", "build", "Landroidx/privacysandbox/ads/adservices/measurement/SourceRegistrationRequest;", "setInputEvent", "ads-adservices_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Builder {
        private InputEvent inputEvent;
        private final List<Uri> registrationUris;

        /* JADX WARN: Multi-variable type inference failed */
        public Builder(List<? extends Uri> registrationUris) {
            Intrinsics.checkNotNullParameter(registrationUris, "registrationUris");
            this.registrationUris = registrationUris;
        }

        public final Builder setInputEvent(InputEvent inputEvent) {
            Intrinsics.checkNotNullParameter(inputEvent, "inputEvent");
            Builder $this$setInputEvent_u24lambda_u240 = this;
            $this$setInputEvent_u24lambda_u240.inputEvent = inputEvent;
            return this;
        }

        public final SourceRegistrationRequest build() {
            return new SourceRegistrationRequest(this.registrationUris, this.inputEvent);
        }
    }
}
