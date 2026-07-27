package org.koin.androidx.scope;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.koin.android.scope.AndroidScopeComponent;
import org.koin.core.scope.Scope;

/* compiled from: ScopeActivity.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\u0012\b\b\u0003\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lorg/koin/androidx/scope/ScopeActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lorg/koin/android/scope/AndroidScopeComponent;", "contentLayoutId", "", "initialiseScope", "", "(IZ)V", "scope", "Lorg/koin/core/scope/Scope;", "getScope", "()Lorg/koin/core/scope/Scope;", "scope$delegate", "Lorg/koin/androidx/scope/LifecycleScopeDelegate;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "koin-android_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public abstract class ScopeActivity extends AppCompatActivity implements AndroidScopeComponent {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(ScopeActivity.class, "scope", "getScope()Lorg/koin/core/scope/Scope;", 0))};
    private final boolean initialiseScope;

    /* renamed from: scope$delegate, reason: from kotlin metadata */
    private final LifecycleScopeDelegate scope;

    /* JADX WARN: Multi-variable type inference failed */
    public ScopeActivity() {
        this(0, 0 == true ? 1 : 0, 3, null);
    }

    public /* synthetic */ ScopeActivity(int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? true : z);
    }

    public ScopeActivity(int contentLayoutId, boolean initialiseScope) {
        super(contentLayoutId);
        this.initialiseScope = initialiseScope;
        this.scope = ComponentActivityExtKt.activityScope(this);
    }

    @Override // org.koin.android.scope.AndroidScopeComponent
    public Scope getScope() {
        return this.scope.getValue2((LifecycleOwner) this, $$delegatedProperties[0]);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.initialiseScope) {
            getScope().getLogger().debug(Intrinsics.stringPlus("Open Activity Scope: ", getScope()));
        }
    }
}
