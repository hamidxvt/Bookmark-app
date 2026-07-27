package org.koin.androidx.scope;

import androidx.activity.ComponentActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.koin.core.Koin;
import org.koin.core.component.KoinScopeComponentKt;
import org.koin.core.logger.Logger;
import org.koin.core.scope.Scope;

/* compiled from: LifecycleViewModelScopeDelegate.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B+\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00030\b¢\u0006\u0002\u0010\tJ\u001d\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00022\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0096\u0002R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00030\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00060\u000ej\u0002`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/koin/androidx/scope/LifecycleViewModelScopeDelegate;", "Lkotlin/properties/ReadOnlyProperty;", "Landroidx/lifecycle/LifecycleOwner;", "Lorg/koin/core/scope/Scope;", "lifecycleOwner", "koin", "Lorg/koin/core/Koin;", "createScope", "Lkotlin/Function1;", "(Landroidx/lifecycle/LifecycleOwner;Lorg/koin/core/Koin;Lkotlin/jvm/functions/Function1;)V", "_scope", "getLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "scopeId", "", "Lorg/koin/core/qualifier/QualifierValue;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "koin-android_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class LifecycleViewModelScopeDelegate implements ReadOnlyProperty<LifecycleOwner, Scope> {
    private Scope _scope;
    private final Function1<Koin, Scope> createScope;
    private final Koin koin;
    private final LifecycleOwner lifecycleOwner;
    private final String scopeId;

    /* JADX WARN: Multi-variable type inference failed */
    public LifecycleViewModelScopeDelegate(LifecycleOwner lifecycleOwner, Koin koin, Function1<? super Koin, Scope> createScope) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(koin, "koin");
        Intrinsics.checkNotNullParameter(createScope, "createScope");
        this.lifecycleOwner = lifecycleOwner;
        this.koin = koin;
        this.createScope = createScope;
        this.scopeId = KoinScopeComponentKt.getScopeName(this.lifecycleOwner).getValue();
        if (!(this.lifecycleOwner instanceof ComponentActivity)) {
            throw new AssertionError(getLifecycleOwner() + " should be a ComponentActivity");
        }
        final Logger logger = this.koin.getLogger();
        logger.debug("setup scope: " + this._scope + " for " + this.lifecycleOwner);
        Scope scopeOrNull = this.koin.getScopeOrNull(this.scopeId);
        this._scope = scopeOrNull == null ? this.createScope.invoke(this.koin) : scopeOrNull;
        logger.debug("got scope: " + this._scope + " for " + this.lifecycleOwner);
        this.lifecycleOwner.getLifecycle().addObserver(new DefaultLifecycleObserver() { // from class: org.koin.androidx.scope.LifecycleViewModelScopeDelegate.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public void onCreate(LifecycleOwner owner) {
                Intrinsics.checkNotNullParameter(owner, "owner");
                Logger.this.debug("Attach ViewModel scope: " + this._scope + " to " + this.getLifecycleOwner());
                final ComponentActivity $this$viewModels_u24default$iv = (ComponentActivity) this.getLifecycleOwner();
                Function0 factoryPromise$iv = new Function0<ViewModelProvider.Factory>() { // from class: org.koin.androidx.scope.LifecycleViewModelScopeDelegate$3$onCreate$$inlined$viewModels$default$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final ViewModelProvider.Factory invoke() {
                        ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
                        Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                        return defaultViewModelProviderFactory;
                    }
                };
                ScopeHandlerViewModel scopeViewModel = (ScopeHandlerViewModel) new ViewModelLazy(Reflection.getOrCreateKotlinClass(ScopeHandlerViewModel.class), new Function0<ViewModelStore>() { // from class: org.koin.androidx.scope.LifecycleViewModelScopeDelegate$3$onCreate$$inlined$viewModels$default$2
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final ViewModelStore invoke() {
                        ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
                        Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                        return viewModelStore;
                    }
                }, factoryPromise$iv).getValue();
                if (scopeViewModel.getScope() == null) {
                    scopeViewModel.setScope(this._scope);
                }
            }
        });
    }

    public /* synthetic */ LifecycleViewModelScopeDelegate(final LifecycleOwner lifecycleOwner, Koin koin, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(lifecycleOwner, koin, (i & 4) != 0 ? new Function1<Koin, Scope>() { // from class: org.koin.androidx.scope.LifecycleViewModelScopeDelegate.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Scope invoke(Koin koin2) {
                Intrinsics.checkNotNullParameter(koin2, "koin");
                return Koin.createScope$default(koin2, KoinScopeComponentKt.getScopeName(LifecycleOwner.this).getValue(), KoinScopeComponentKt.getScopeName(LifecycleOwner.this), null, 4, null);
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

    /* renamed from: getValue, reason: avoid collision after fix types in other method */
    public Scope getValue2(LifecycleOwner thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(thisRef, "thisRef");
        Intrinsics.checkNotNullParameter(property, "property");
        if (this._scope != null) {
            Scope scope = this._scope;
            Intrinsics.checkNotNull(scope);
            return scope;
        }
        if (!LifecycleScopeDelegateKt.isActive(thisRef)) {
            throw new IllegalStateException(("can't get Scope for " + this.lifecycleOwner + " - LifecycleOwner is not Active").toString());
        }
        Scope scopeOrNull = this.koin.getScopeOrNull(KoinScopeComponentKt.getScopeName(this.lifecycleOwner).getValue());
        if (scopeOrNull == null) {
            scopeOrNull = this.createScope.invoke(this.koin);
        }
        this._scope = scopeOrNull;
        this.koin.getLogger().debug("got scope: " + this._scope + " for " + this.lifecycleOwner);
        Scope scope2 = this._scope;
        Intrinsics.checkNotNull(scope2);
        return scope2;
    }
}
