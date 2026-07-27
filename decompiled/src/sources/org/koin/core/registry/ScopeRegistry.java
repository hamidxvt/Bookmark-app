package org.koin.core.registry;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;
import org.koin.core.error.ScopeAlreadyCreatedException;
import org.koin.core.module.Module;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.qualifier.QualifierKt;
import org.koin.core.qualifier.StringQualifier;
import org.koin.core.scope.Scope;
import org.koin.mp.KoinPlatformTools;

/* compiled from: ScopeRegistry.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u0000 )2\u00020\u0001:\u0001)B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u0019J\b\u0010\u001a\u001a\u00020\u0018H\u0002J(\u0010\u001b\u001a\u00020\r2\n\u0010\u001c\u001a\u00060\u000bj\u0002`\f2\u0006\u0010\u001d\u001a\u00020\u00072\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u0001J\u0015\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\rH\u0000¢\u0006\u0002\b!J\u0019\u0010\u001f\u001a\u00020\u00182\n\u0010\u001c\u001a\u00060\u000bj\u0002`\fH\u0000¢\u0006\u0002\b!J\u0016\u0010\"\u001a\u0004\u0018\u00010\r2\n\u0010\u001c\u001a\u00060\u000bj\u0002`\fH\u0001J\u0010\u0010#\u001a\u00020\u00182\u0006\u0010$\u001a\u00020%H\u0002J\u0014\u0010&\u001a\u00020\u00182\f\u0010'\u001a\b\u0012\u0004\u0012\u00020%0(R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\b\u0012\u00060\u000bj\u0002`\f\u0012\u0004\u0012\u00020\r0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006*"}, d2 = {"Lorg/koin/core/registry/ScopeRegistry;", "", "_koin", "Lorg/koin/core/Koin;", "(Lorg/koin/core/Koin;)V", "_scopeDefinitions", "Ljava/util/HashSet;", "Lorg/koin/core/qualifier/Qualifier;", "Lkotlin/collections/HashSet;", "_scopes", "", "", "Lorg/koin/core/scope/ScopeID;", "Lorg/koin/core/scope/Scope;", "rootScope", "getRootScope$annotations", "()V", "getRootScope", "()Lorg/koin/core/scope/Scope;", "scopeDefinitions", "", "getScopeDefinitions", "()Ljava/util/Set;", "close", "", "close$koin_core", "closeAllScopes", "createScope", "scopeId", "qualifier", "source", "deleteScope", "scope", "deleteScope$koin_core", "getScopeOrNull", "loadModule", "module", "Lorg/koin/core/module/Module;", "loadScopes", "modules", "", "Companion", "koin-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class ScopeRegistry {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String ROOT_SCOPE_ID = "_";
    private static final StringQualifier rootScopeQualifier = QualifierKt._q(ROOT_SCOPE_ID);
    private final Koin _koin;
    private final HashSet<Qualifier> _scopeDefinitions;
    private final Map<String, Scope> _scopes;
    private final Scope rootScope;

    public static /* synthetic */ void getRootScope$annotations() {
    }

    public ScopeRegistry(Koin _koin) {
        Intrinsics.checkNotNullParameter(_koin, "_koin");
        this._koin = _koin;
        this._scopeDefinitions = new HashSet<>();
        this._scopes = KoinPlatformTools.INSTANCE.safeHashMap();
        this.rootScope = new Scope(rootScopeQualifier, ROOT_SCOPE_ID, true, this._koin);
        this._scopeDefinitions.add(this.rootScope.getScopeQualifier());
        this._scopes.put(this.rootScope.getId(), this.rootScope);
    }

    public final Set<Qualifier> getScopeDefinitions() {
        return this._scopeDefinitions;
    }

    public final Scope getRootScope() {
        return this.rootScope;
    }

    public final Scope getScopeOrNull(String scopeId) {
        Intrinsics.checkNotNullParameter(scopeId, "scopeId");
        return this._scopes.get(scopeId);
    }

    public static /* synthetic */ Scope createScope$default(ScopeRegistry scopeRegistry, String str, Qualifier qualifier, Object obj, int i, Object obj2) {
        if ((i & 4) != 0) {
            obj = null;
        }
        return scopeRegistry.createScope(str, qualifier, obj);
    }

    public final Scope createScope(String scopeId, Qualifier qualifier, Object source) {
        Intrinsics.checkNotNullParameter(scopeId, "scopeId");
        Intrinsics.checkNotNullParameter(qualifier, "qualifier");
        if (!this._scopeDefinitions.contains(qualifier)) {
            this._koin.getLogger().info("Warning: Scope '" + qualifier + "' not defined. Creating it");
            this._scopeDefinitions.add(qualifier);
        }
        if (this._scopes.containsKey(scopeId)) {
            throw new ScopeAlreadyCreatedException("Scope with id '" + scopeId + "' is already created");
        }
        Scope scope = new Scope(qualifier, scopeId, false, this._koin, 4, null);
        if (source != null) {
            scope.set_source(source);
        }
        scope.linkTo(this.rootScope);
        this._scopes.put(scopeId, scope);
        return scope;
    }

    public final void deleteScope$koin_core(String scopeId) {
        Intrinsics.checkNotNullParameter(scopeId, "scopeId");
        Scope it = this._scopes.get(scopeId);
        if (it != null) {
            deleteScope$koin_core(it);
        }
    }

    public final void deleteScope$koin_core(Scope scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        this._koin.getInstanceRegistry().dropScopeInstances$koin_core(scope);
        this._scopes.remove(scope.getId());
    }

    public final void close$koin_core() {
        closeAllScopes();
        this._scopes.clear();
        this._scopeDefinitions.clear();
    }

    private final void closeAllScopes() {
        Iterable $this$forEach$iv = this._scopes.values();
        for (Object element$iv : $this$forEach$iv) {
            Scope scope = (Scope) element$iv;
            scope.close();
        }
    }

    public final void loadScopes(List<Module> modules) {
        Intrinsics.checkNotNullParameter(modules, "modules");
        List<Module> $this$forEach$iv = modules;
        for (Object element$iv : $this$forEach$iv) {
            Module it = (Module) element$iv;
            loadModule(it);
        }
    }

    private final void loadModule(Module module) {
        this._scopeDefinitions.addAll(module.getScopes());
    }

    /* compiled from: ScopeRegistry.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u00020\u00068\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/koin/core/registry/ScopeRegistry$Companion;", "", "()V", "ROOT_SCOPE_ID", "", "rootScopeQualifier", "Lorg/koin/core/qualifier/StringQualifier;", "getRootScopeQualifier$annotations", "getRootScopeQualifier", "()Lorg/koin/core/qualifier/StringQualifier;", "koin-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getRootScopeQualifier$annotations() {
        }

        private Companion() {
        }

        public final StringQualifier getRootScopeQualifier() {
            return ScopeRegistry.rootScopeQualifier;
        }
    }
}
