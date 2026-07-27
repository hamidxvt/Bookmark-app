package org.koin.androidx.scope;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.koin.core.Koin;
import org.koin.core.component.KoinScopeComponentKt;
import org.koin.core.logger.Logger;
import org.koin.core.scope.Scope;

/* compiled from: LifecycleScopeDelegate.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002B+\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00040\t¢\u0006\u0002\u0010\nJ\b\u0010\b\u001a\u00020\u000eH\u0002J\u001d\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00032\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0096\u0002R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00040\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Lorg/koin/androidx/scope/LifecycleScopeDelegate;", "T", "Lkotlin/properties/ReadOnlyProperty;", "Landroidx/lifecycle/LifecycleOwner;", "Lorg/koin/core/scope/Scope;", "lifecycleOwner", "koin", "Lorg/koin/core/Koin;", "createScope", "Lkotlin/Function1;", "(Landroidx/lifecycle/LifecycleOwner;Lorg/koin/core/Koin;Lkotlin/jvm/functions/Function1;)V", "_scope", "getLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "koin-android_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class LifecycleScopeDelegate<T> implements ReadOnlyProperty<LifecycleOwner, Scope> {
    private Scope _scope;
    private final Function1<Koin, Scope> createScope;
    private final Koin koin;
    private final LifecycleOwner lifecycleOwner;

    /* JADX WARN: Multi-variable type inference failed */
    public LifecycleScopeDelegate(LifecycleOwner lifecycleOwner, Koin koin, Function1<? super Koin, Scope> createScope) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(koin, "koin");
        Intrinsics.checkNotNullParameter(createScope, "createScope");
        this.lifecycleOwner = lifecycleOwner;
        this.koin = koin;
        this.createScope = createScope;
        final Logger logger = this.koin.getLogger();
        logger.debug("setup scope: " + this._scope + " for " + this.lifecycleOwner);
        this.lifecycleOwner.getLifecycle().addObserver(new DefaultLifecycleObserver(this) { // from class: org.koin.androidx.scope.LifecycleScopeDelegate.2
            final /* synthetic */ LifecycleScopeDelegate<T> this$0;

            {
                this.this$0 = this;
            }

            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public void onCreate(LifecycleOwner owner) {
                Intrinsics.checkNotNullParameter(owner, "owner");
                this.this$0.createScope();
            }

            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public void onDestroy(LifecycleOwner owner) {
                Scope scope;
                Intrinsics.checkNotNullParameter(owner, "owner");
                logger.debug("Closing scope: " + ((LifecycleScopeDelegate) this.this$0)._scope + " for " + this.this$0.getLifecycleOwner());
                Scope scope2 = ((LifecycleScopeDelegate) this.this$0)._scope;
                boolean z = false;
                if (scope2 != null && !scope2.get_closed()) {
                    z = true;
                }
                if (z && (scope = ((LifecycleScopeDelegate) this.this$0)._scope) != null) {
                    scope.close();
                }
                ((LifecycleScopeDelegate) this.this$0)._scope = null;
            }
        });
    }

    public /* synthetic */ LifecycleScopeDelegate(final LifecycleOwner lifecycleOwner, Koin koin, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(lifecycleOwner, koin, (i & 4) != 0 ? new Function1<Koin, Scope>() { // from class: org.koin.androidx.scope.LifecycleScopeDelegate.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Scope invoke(Koin koin2) {
                Intrinsics.checkNotNullParameter(koin2, "koin");
                return koin2.createScope(KoinScopeComponentKt.getScopeId(LifecycleOwner.this), KoinScopeComponentKt.getScopeName(LifecycleOwner.this), LifecycleOwner.this);
            }
        } : function1);
    }

    @Override // kotlin.properties.ReadOnlyProperty
    public /* bridge */ /* synthetic */ Scope getValue(LifecycleOwner lifecycleOwner, KProperty property) {
        return getValue2(lifecycleOwner, (KProperty<?>) property);
    }

    public final LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createScope() {
        if (this._scope == null) {
            this.koin.getLogger().debug("Create scope: " + this._scope + " for " + this.lifecycleOwner);
            String scopeId = KoinScopeComponentKt.getScopeId(this.lifecycleOwner);
            Scope scopeOrNull = this.koin.getScopeOrNull(scopeId);
            if (scopeOrNull == null) {
                scopeOrNull = this.createScope.invoke(this.koin);
            }
            this._scope = scopeOrNull;
        }
    }

    /* renamed from: getValue, reason: avoid collision after fix types in other method */
    public Scope getValue2(LifecycleOwner thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(thisRef, "thisRef");
        Intrinsics.checkNotNullParameter(property, "property");
        if (this._scope == null) {
            createScope();
            Scope scope = this._scope;
            if (scope != null) {
                return scope;
            }
            throw new IllegalStateException(Intrinsics.stringPlus("can't get Scope for ", this.lifecycleOwner).toString());
        }
        Scope scope2 = this._scope;
        if (scope2 != null) {
            return scope2;
        }
        throw new IllegalStateException(Intrinsics.stringPlus("can't get Scope for ", this.lifecycleOwner).toString());
    }
}
