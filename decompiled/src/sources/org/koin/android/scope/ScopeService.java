package org.koin.android.scope;

import android.app.Service;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.scope.Scope;

/* compiled from: ScopeService.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Lorg/koin/android/scope/ScopeService;", "Landroid/app/Service;", "Lorg/koin/android/scope/AndroidScopeComponent;", "initialiseScope", "", "(Z)V", "scope", "Lorg/koin/core/scope/Scope;", "getScope", "()Lorg/koin/core/scope/Scope;", "scope$delegate", "Lkotlin/Lazy;", "onCreate", "", "onDestroy", "koin-android_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public abstract class ScopeService extends Service implements AndroidScopeComponent {
    private final boolean initialiseScope;

    /* renamed from: scope$delegate, reason: from kotlin metadata */
    private final Lazy scope;

    public ScopeService() {
        this(false, 1, null);
    }

    public /* synthetic */ ScopeService(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z);
    }

    public ScopeService(boolean initialiseScope) {
        this.initialiseScope = initialiseScope;
        this.scope = ServiceExtKt.serviceScope(this);
    }

    @Override // org.koin.android.scope.AndroidScopeComponent
    public Scope getScope() {
        return (Scope) this.scope.getValue();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (this.initialiseScope) {
            getScope().getLogger().debug(Intrinsics.stringPlus("Open Service Scope: ", getScope()));
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        getScope().getLogger().debug(Intrinsics.stringPlus("Close service scope: ", getScope()));
        if (!getScope().get_closed()) {
            getScope().close();
        }
    }
}
