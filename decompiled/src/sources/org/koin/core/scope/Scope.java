package org.koin.core.scope;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.core.Koin;
import org.koin.core.error.ClosedScopeException;
import org.koin.core.error.MissingPropertyException;
import org.koin.core.error.NoBeanDefFoundException;
import org.koin.core.instance.InstanceContext;
import org.koin.core.instance.InstanceFactory;
import org.koin.core.instance.ScopedInstanceFactory;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.time.MeasureKt;
import org.koin.ext.KClassExtKt;
import org.koin.mp.KoinPlatformTools;

/* compiled from: Scope.kt */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010-\u001a\u00020.H\u0002J\u0006\u0010/\u001a\u00020.J\t\u00100\u001a\u00020\u0003HÆ\u0003J\r\u00101\u001a\u00060\u0005j\u0002`\u0006HÆ\u0003J\t\u00102\u001a\u00020\bHÆ\u0003J\u000e\u00103\u001a\u00020\nHÀ\u0003¢\u0006\u0002\b4J5\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\b\u0002\u0010\u0004\u001a\u00060\u0005j\u0002`\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u001b\u00106\u001a\u00020.2\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u000008H\u0000¢\u0006\u0002\b9JH\u0010:\u001a\u00020.\"\u0006\b\u0000\u0010;\u0018\u00012\u0006\u0010<\u001a\u0002H;2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00032\u0012\b\u0002\u0010>\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030?082\b\b\u0002\u0010@\u001a\u00020\bH\u0086\b¢\u0006\u0002\u0010AJ\u0013\u0010B\u001a\u00020\b2\b\u0010C\u001a\u0004\u0018\u00010\u0001HÖ\u0003JA\u0010D\u001a\u0004\u0018\u0001H;\"\u0004\b\u0000\u0010;2\n\u0010E\u001a\u0006\u0012\u0002\b\u00030?2\b\u0010=\u001a\u0004\u0018\u00010\u00032\u0014\u0010F\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0018\u00010Gj\u0004\u0018\u0001`HH\u0002¢\u0006\u0002\u0010IJA\u0010J\u001a\u0002H;\"\u0004\b\u0000\u0010;2\n\u0010E\u001a\u0006\u0012\u0002\b\u00030?2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010F\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0018\u00010Gj\u0004\u0018\u0001`H¢\u0006\u0002\u0010IJA\u0010J\u001a\u0002H;\"\n\b\u0000\u0010;\u0018\u0001*\u00020\u00012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00032\u0016\b\n\u0010F\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0018\u00010Gj\u0004\u0018\u0001`HH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010KJ\u001b\u0010L\u001a\b\u0012\u0004\u0012\u0002H;08\"\n\b\u0000\u0010;\u0018\u0001*\u00020\u0001H\u0086\bJ\u001e\u0010L\u001a\b\u0012\u0004\u0012\u0002H;08\"\u0004\b\u0000\u0010;2\n\u0010E\u001a\u0006\u0012\u0002\b\u00030?J!\u0010M\u001a\u0004\u0018\u0001H;\"\u0004\b\u0000\u0010;2\n\u0010E\u001a\u0006\u0012\u0002\b\u00030?H\u0002¢\u0006\u0002\u0010NJ\u0006\u0010O\u001a\u00020\nJC\u0010P\u001a\u0004\u0018\u0001H;\"\u0004\b\u0000\u0010;2\n\u0010E\u001a\u0006\u0012\u0002\b\u00030?2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010F\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0018\u00010Gj\u0004\u0018\u0001`H¢\u0006\u0002\u0010IJC\u0010P\u001a\u0004\u0018\u0001H;\"\n\b\u0000\u0010;\u0018\u0001*\u00020\u00012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00032\u0016\b\n\u0010F\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0018\u00010Gj\u0004\u0018\u0001`HH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010KJ\u001d\u0010Q\u001a\u0002H;\"\b\b\u0000\u0010;*\u00020\u00012\u0006\u0010R\u001a\u00020\u0005¢\u0006\u0002\u0010SJ%\u0010Q\u001a\u0002H;\"\b\b\u0000\u0010;*\u00020\u00012\u0006\u0010R\u001a\u00020\u00052\u0006\u0010T\u001a\u0002H;¢\u0006\u0002\u0010UJ\u001f\u0010V\u001a\u0004\u0018\u0001H;\"\b\b\u0000\u0010;*\u00020\u00012\u0006\u0010R\u001a\u00020\u0005¢\u0006\u0002\u0010SJ\u0012\u0010W\u001a\u00020\u00002\n\u0010X\u001a\u00060\u0005j\u0002`\u0006J\u001c\u0010Y\u001a\u0004\u0018\u0001H;\"\n\b\u0000\u0010;\u0018\u0001*\u00020\u0001H\u0087\b¢\u0006\u0002\u0010\u001eJ\t\u0010Z\u001a\u00020[HÖ\u0001JL\u0010\\\u001a\b\u0012\u0004\u0012\u0002H;0]\"\n\b\u0000\u0010;\u0018\u0001*\u00020\u00012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010^\u001a\u00020_2\u0016\b\n\u0010F\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0018\u00010Gj\u0004\u0018\u0001`HH\u0086\bø\u0001\u0000JN\u0010`\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H;0]\"\n\b\u0000\u0010;\u0018\u0001*\u00020\u00012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010^\u001a\u00020_2\u0016\b\n\u0010F\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0018\u00010Gj\u0004\u0018\u0001`HH\u0086\bø\u0001\u0000J\u0006\u0010a\u001a\u00020\bJ\u001f\u0010b\u001a\u00020.2\u0012\u0010c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000d\"\u00020\u0000¢\u0006\u0002\u0010eJ5\u0010f\u001a\u00020.\"\b\b\u0000\u0010;*\u00020\u00012\n\u0010E\u001a\u0006\u0012\u0002\b\u00030?2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00032\u0006\u0010<\u001a\u0002H;¢\u0006\u0002\u0010gJ\u000e\u0010h\u001a\u00020.2\u0006\u0010i\u001a\u00020\u000eJ?\u0010j\u001a\u0002H;\"\u0004\b\u0000\u0010;2\b\u0010=\u001a\u0004\u0018\u00010\u00032\n\u0010E\u001a\u0006\u0012\u0002\b\u00030?2\u0014\u0010k\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0018\u00010Gj\u0004\u0018\u0001`HH\u0002¢\u0006\u0002\u0010lJG\u0010m\u001a\u0002H;\"\u0004\b\u0000\u0010;2\b\u0010=\u001a\u0004\u0018\u00010\u00032\n\u0010E\u001a\u0006\u0012\u0002\b\u00030?2\u0006\u0010n\u001a\u00020o2\u0014\u0010k\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0018\u00010Gj\u0004\u0018\u0001`HH\u0002¢\u0006\u0002\u0010pJ\u001e\u0010q\u001a\u00020r2\b\u0010=\u001a\u0004\u0018\u00010\u00032\n\u0010E\u001a\u0006\u0012\u0002\b\u00030?H\u0002J\b\u0010s\u001a\u00020\u0005H\u0016J\u001f\u0010t\u001a\u00020.2\u0012\u0010c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000d\"\u00020\u0000¢\u0006\u0002\u0010eR\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u00020\n8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\"\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0019\u0010\u001aR&\u0010\u001b\u001a\u0004\u0018\u00010\u00018\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001c\u0010\u0012\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0015\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010#R\u001e\u0010&\u001a\u0012\u0012\u0004\u0012\u00020\u00000\rj\b\u0012\u0004\u0012\u00020\u0000`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010'\u001a\u00020(8F¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006u"}, d2 = {"Lorg/koin/core/scope/Scope;", "", "scopeQualifier", "Lorg/koin/core/qualifier/Qualifier;", Constant.VISIT_ID, "", "Lorg/koin/core/scope/ScopeID;", "isRoot", "", "_koin", "Lorg/koin/core/Koin;", "(Lorg/koin/core/qualifier/Qualifier;Ljava/lang/String;ZLorg/koin/core/Koin;)V", "_callbacks", "Ljava/util/ArrayList;", "Lorg/koin/core/scope/ScopeCallback;", "Lkotlin/collections/ArrayList;", "_closed", "get_koin$annotations", "()V", "get_koin", "()Lorg/koin/core/Koin;", "_parameterStack", "Lkotlin/collections/ArrayDeque;", "Lorg/koin/core/parameter/ParametersHolder;", "get_parameterStack$annotations", "get_parameterStack", "()Lkotlin/collections/ArrayDeque;", "_source", "get_source$annotations", "get_source", "()Ljava/lang/Object;", "set_source", "(Ljava/lang/Object;)V", "closed", "getClosed", "()Z", "getId", "()Ljava/lang/String;", "linkedScopes", "logger", "Lorg/koin/core/logger/Logger;", "getLogger", "()Lorg/koin/core/logger/Logger;", "getScopeQualifier", "()Lorg/koin/core/qualifier/Qualifier;", "clearData", "", "close", "component1", "component2", "component3", "component4", "component4$koin_core", "copy", "create", "links", "", "create$koin_core", "declare", "T", "instance", "qualifier", "secondaryTypes", "Lkotlin/reflect/KClass;", "allowOverride", "(Ljava/lang/Object;Lorg/koin/core/qualifier/Qualifier;Ljava/util/List;Z)V", "equals", "other", "findInOtherScope", "clazz", "parameters", "Lkotlin/Function0;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Lkotlin/reflect/KClass;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", Constant.RetrofitConstants.RETROFIT_METHOD_GET, "(Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getAll", "getFromSource", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "getKoin", "getOrNull", "getProperty", "key", "(Ljava/lang/String;)Ljava/lang/Object;", "defaultValue", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "getPropertyOrNull", "getScope", "scopeID", "getSource", "hashCode", "", "inject", "Lkotlin/Lazy;", "mode", "Lkotlin/LazyThreadSafetyMode;", "injectOrNull", "isNotClosed", "linkTo", "scopes", "", "([Lorg/koin/core/scope/Scope;)V", "refreshScopeInstance", "(Lkotlin/reflect/KClass;Lorg/koin/core/qualifier/Qualifier;Ljava/lang/Object;)V", "registerCallback", "callback", "resolveInstance", "parameterDef", "(Lorg/koin/core/qualifier/Qualifier;Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "resolveValue", "instanceContext", "Lorg/koin/core/instance/InstanceContext;", "(Lorg/koin/core/qualifier/Qualifier;Lkotlin/reflect/KClass;Lorg/koin/core/instance/InstanceContext;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "throwDefinitionNotFound", "", "toString", "unlink", "koin-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final /* data */ class Scope {
    private final ArrayList<ScopeCallback> _callbacks;
    private boolean _closed;
    private final Koin _koin;
    private final ArrayDeque<ParametersHolder> _parameterStack;
    private Object _source;
    private final String id;
    private final boolean isRoot;
    private final ArrayList<Scope> linkedScopes;
    private final Qualifier scopeQualifier;

    public static /* synthetic */ Scope copy$default(Scope scope, Qualifier qualifier, String str, boolean z, Koin koin, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = scope.scopeQualifier;
        }
        if ((i & 2) != 0) {
            str = scope.id;
        }
        if ((i & 4) != 0) {
            z = scope.isRoot;
        }
        if ((i & 8) != 0) {
            koin = scope._koin;
        }
        return scope.copy(qualifier, str, z, koin);
    }

    public static /* synthetic */ void get_koin$annotations() {
    }

    public static /* synthetic */ void get_parameterStack$annotations() {
    }

    public static /* synthetic */ void get_source$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final Qualifier getScopeQualifier() {
        return this.scopeQualifier;
    }

    /* renamed from: component2, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsRoot() {
        return this.isRoot;
    }

    /* renamed from: component4$koin_core, reason: from getter */
    public final Koin get_koin() {
        return this._koin;
    }

    public final Scope copy(Qualifier scopeQualifier, String id, boolean isRoot, Koin _koin) {
        Intrinsics.checkNotNullParameter(scopeQualifier, "scopeQualifier");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(_koin, "_koin");
        return new Scope(scopeQualifier, id, isRoot, _koin);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Scope)) {
            return false;
        }
        Scope scope = (Scope) other;
        return Intrinsics.areEqual(this.scopeQualifier, scope.scopeQualifier) && Intrinsics.areEqual(this.id, scope.id) && this.isRoot == scope.isRoot && Intrinsics.areEqual(this._koin, scope._koin);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((this.scopeQualifier.hashCode() * 31) + this.id.hashCode()) * 31;
        boolean z = this.isRoot;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return ((hashCode + i) * 31) + this._koin.hashCode();
    }

    public Scope(Qualifier scopeQualifier, String id, boolean isRoot, Koin _koin) {
        Intrinsics.checkNotNullParameter(scopeQualifier, "scopeQualifier");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(_koin, "_koin");
        this.scopeQualifier = scopeQualifier;
        this.id = id;
        this.isRoot = isRoot;
        this._koin = _koin;
        this.linkedScopes = new ArrayList<>();
        this._callbacks = new ArrayList<>();
        this._parameterStack = new ArrayDeque<>();
    }

    public /* synthetic */ Scope(Qualifier qualifier, String str, boolean z, Koin koin, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(qualifier, str, (i & 4) != 0 ? false : z, koin);
    }

    public final Qualifier getScopeQualifier() {
        return this.scopeQualifier;
    }

    public final String getId() {
        return this.id;
    }

    public final boolean isRoot() {
        return this.isRoot;
    }

    public final Koin get_koin() {
        return this._koin;
    }

    public final Object get_source() {
        return this._source;
    }

    public final void set_source(Object obj) {
        this._source = obj;
    }

    /* renamed from: getClosed, reason: from getter */
    public final boolean get_closed() {
        return this._closed;
    }

    public final boolean isNotClosed() {
        return !get_closed();
    }

    public final ArrayDeque<ParametersHolder> get_parameterStack() {
        return this._parameterStack;
    }

    public final Logger getLogger() {
        return this._koin.getLogger();
    }

    public final void create$koin_core(List<Scope> links) {
        Intrinsics.checkNotNullParameter(links, "links");
        this.linkedScopes.addAll(links);
    }

    public final void linkTo(Scope... scopes) {
        Intrinsics.checkNotNullParameter(scopes, "scopes");
        if (!this.isRoot) {
            CollectionsKt.addAll(this.linkedScopes, scopes);
            return;
        }
        throw new IllegalStateException("Can't add scope link to a root scope".toString());
    }

    public final void unlink(Scope... scopes) {
        Intrinsics.checkNotNullParameter(scopes, "scopes");
        if (!this.isRoot) {
            CollectionsKt.removeAll(this.linkedScopes, scopes);
            return;
        }
        throw new IllegalStateException("Can't remove scope link to a root scope".toString());
    }

    public static /* synthetic */ Lazy inject$default(Scope scope, Qualifier qualifier, LazyThreadSafetyMode mode, Function0 parameters, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            mode = LazyThreadSafetyMode.SYNCHRONIZED;
        }
        if ((i & 4) != 0) {
            parameters = null;
        }
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.needClassReification();
        return LazyKt.lazy(mode, (Function0) new Scope$inject$1(scope, qualifier, parameters));
    }

    public final /* synthetic */ <T> Lazy<T> inject(Qualifier qualifier, LazyThreadSafetyMode mode, Function0<? extends ParametersHolder> parameters) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.needClassReification();
        return LazyKt.lazy(mode, (Function0) new Scope$inject$1(this, qualifier, parameters));
    }

    public static /* synthetic */ Lazy injectOrNull$default(Scope scope, Qualifier qualifier, LazyThreadSafetyMode mode, Function0 parameters, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            mode = LazyThreadSafetyMode.SYNCHRONIZED;
        }
        if ((i & 4) != 0) {
            parameters = null;
        }
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.needClassReification();
        return LazyKt.lazy(mode, (Function0) new Scope$injectOrNull$1(scope, qualifier, parameters));
    }

    public final /* synthetic */ <T> Lazy<T> injectOrNull(Qualifier qualifier, LazyThreadSafetyMode mode, Function0<? extends ParametersHolder> parameters) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.needClassReification();
        return LazyKt.lazy(mode, (Function0) new Scope$injectOrNull$1(this, qualifier, parameters));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object get$default(Scope scope, Qualifier qualifier, Function0 parameters, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            parameters = null;
        }
        Intrinsics.reifiedOperationMarker(4, "T");
        return scope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, parameters);
    }

    public final /* synthetic */ <T> T get(Qualifier qualifier, Function0<? extends ParametersHolder> parameters) {
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, parameters);
    }

    @Deprecated(message = "No need to use getSource(). You can an use get() directly.", replaceWith = @ReplaceWith(expression = "get()", imports = {}))
    public final /* synthetic */ <T> T getSource() {
        T t = (T) get_source();
        Intrinsics.reifiedOperationMarker(2, "T");
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object getOrNull$default(Scope scope, Qualifier qualifier, Function0 parameters, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            parameters = null;
        }
        Intrinsics.reifiedOperationMarker(4, "T");
        return scope.getOrNull(Reflection.getOrCreateKotlinClass(Object.class), qualifier, parameters);
    }

    public final /* synthetic */ <T> T getOrNull(Qualifier qualifier, Function0<? extends ParametersHolder> parameters) {
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) getOrNull(Reflection.getOrCreateKotlinClass(Object.class), qualifier, parameters);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object getOrNull$default(Scope scope, KClass kClass, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        return scope.getOrNull(kClass, qualifier, function0);
    }

    public final <T> T getOrNull(KClass<?> clazz, Qualifier qualifier, Function0<? extends ParametersHolder> parameters) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        try {
            return (T) get(clazz, qualifier, parameters);
        } catch (ClosedScopeException e) {
            this._koin.getLogger().debug("|- Scope closed - no instance found for " + KClassExtKt.getFullName(clazz) + " on scope " + this);
            return null;
        } catch (NoBeanDefFoundException e2) {
            this._koin.getLogger().debug("|- No instance found for " + KClassExtKt.getFullName(clazz) + " on scope " + this);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object get$default(Scope scope, KClass kClass, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        return scope.get(kClass, qualifier, function0);
    }

    public final <T> T get(final KClass<?> clazz, final Qualifier qualifier, final Function0<? extends ParametersHolder> parameters) {
        String str;
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        if (this._koin.getLogger().isAt(Level.DEBUG)) {
            String str2 = "";
            if (qualifier != null && (str = " with qualifier '" + qualifier + '\'') != null) {
                str2 = str;
            }
            this._koin.getLogger().debug("+- '" + KClassExtKt.getFullName(clazz) + '\'' + str2);
            Pair measureDurationForResult = MeasureKt.measureDurationForResult(new Function0<T>() { // from class: org.koin.core.scope.Scope$get$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final T invoke() {
                    Object resolveInstance;
                    resolveInstance = Scope.this.resolveInstance(qualifier, clazz, parameters);
                    return (T) resolveInstance;
                }
            });
            T t = (T) measureDurationForResult.component1();
            this._koin.getLogger().debug("|- '" + KClassExtKt.getFullName(clazz) + "' in " + ((Number) measureDurationForResult.component2()).doubleValue() + " ms");
            return t;
        }
        return (T) resolveInstance(qualifier, clazz, parameters);
    }

    public static /* synthetic */ void refreshScopeInstance$default(Scope scope, KClass kClass, Qualifier qualifier, Object obj, int i, Object obj2) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        scope.refreshScopeInstance(kClass, qualifier, obj);
    }

    public final <T> void refreshScopeInstance(KClass<?> clazz, Qualifier qualifier, T instance) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(instance, "instance");
        if (this._closed) {
            throw new ClosedScopeException("Scope '" + this.id + "' is closed");
        }
        InstanceFactory definition = this._koin.getInstanceRegistry().resolveDefinition$koin_core(clazz, qualifier, this.scopeQualifier);
        ScopedInstanceFactory $this$refreshScopeInstance_u24lambda_u2d1 = definition instanceof ScopedInstanceFactory ? (ScopedInstanceFactory) definition : null;
        if ($this$refreshScopeInstance_u24lambda_u2d1 != null) {
            get_koin().getLogger().debug("|- '" + KClassExtKt.getFullName(clazz) + "' refresh with " + instance);
            $this$refreshScopeInstance_u24lambda_u2d1.refreshInstance(getId(), instance);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> T resolveInstance(Qualifier qualifier, KClass<?> clazz, Function0<? extends ParametersHolder> parameterDef) {
        if (this._closed) {
            throw new ClosedScopeException("Scope '" + this.id + "' is closed");
        }
        final ParametersHolder invoke = parameterDef == null ? null : parameterDef.invoke();
        if (invoke != null) {
            this._koin.getLogger().log(Level.DEBUG, new Function0<String>() { // from class: org.koin.core.scope.Scope$resolveInstance$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "| put parameters on stack " + ParametersHolder.this + ' ';
                }
            });
            this._parameterStack.addFirst(invoke);
        }
        T t = (T) resolveValue(qualifier, clazz, new InstanceContext(this._koin, this, invoke), parameterDef);
        if (invoke != null) {
            this._koin.getLogger().log(Level.DEBUG, new Function0<String>() { // from class: org.koin.core.scope.Scope$resolveInstance$2
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "| remove parameters from stack";
                }
            });
            this._parameterStack.removeFirstOrNull();
        }
        return t;
    }

    private final <T> T resolveValue(final Qualifier qualifier, final KClass<?> clazz, InstanceContext instanceContext, Function0<? extends ParametersHolder> parameterDef) {
        T t = (T) this._koin.getInstanceRegistry().resolveInstance$koin_core(qualifier, clazz, this.scopeQualifier, instanceContext);
        if (t == null) {
            Scope scope = this;
            scope.get_koin().getLogger().log(Level.DEBUG, new Function0<String>() { // from class: org.koin.core.scope.Scope$resolveValue$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "- lookup? t:'" + KClassExtKt.getFullName(clazz) + "' - q:'" + qualifier + "' look in injected parameters";
                }
            });
            ParametersHolder firstOrNull = scope.get_parameterStack().firstOrNull();
            Object obj = null;
            T t2 = firstOrNull == null ? null : (T) firstOrNull.getOrNull(clazz);
            if (t2 != null) {
                return t2;
            }
            Scope scope2 = this;
            scope2.get_koin().getLogger().log(Level.DEBUG, new Function0<String>() { // from class: org.koin.core.scope.Scope$resolveValue$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "- lookup? t:'" + KClassExtKt.getFullName(clazz) + "' - q:'" + qualifier + "' look at scope source";
                }
            });
            Object obj2 = scope2.get_source();
            if (obj2 != null && clazz.isInstance(obj2)) {
                obj = scope2.get_source();
            }
            if (obj != null) {
                return (T) obj;
            }
            Scope scope3 = this;
            scope3.get_koin().getLogger().log(Level.DEBUG, new Function0<String>() { // from class: org.koin.core.scope.Scope$resolveValue$3$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "- lookup? t:'" + KClassExtKt.getFullName(clazz) + "' - q:'" + qualifier + "' look in other scopes";
                }
            });
            T t3 = (T) scope3.findInOtherScope(clazz, qualifier, parameterDef);
            if (t3 == null) {
                Scope scope4 = this;
                scope4.get_parameterStack().clear();
                scope4.get_koin().getLogger().log(Level.DEBUG, new Function0<String>() { // from class: org.koin.core.scope.Scope$resolveValue$4$1
                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "| clear parameter stack";
                    }
                });
                scope4.throwDefinitionNotFound(qualifier, clazz);
                throw new KotlinNothingValueException();
            }
            return t3;
        }
        return t;
    }

    private final <T> T getFromSource(KClass<?> clazz) {
        if (clazz.isInstance(this._source)) {
            return (T) this._source;
        }
        return null;
    }

    private final <T> T findInOtherScope(KClass<?> clazz, Qualifier qualifier, Function0<? extends ParametersHolder> parameters) {
        T t = null;
        Iterator<Scope> it = this.linkedScopes.iterator();
        while (it.hasNext() && (t = (T) it.next().getOrNull(clazz, qualifier, parameters)) == null) {
        }
        return t;
    }

    private final Void throwDefinitionNotFound(Qualifier qualifier, KClass<?> clazz) {
        String str;
        String qualifierString = "";
        if (qualifier != null && (str = " & qualifier:'" + qualifier + '\'') != null) {
            qualifierString = str;
        }
        throw new NoBeanDefFoundException("|- No definition found for class:'" + KClassExtKt.getFullName(clazz) + '\'' + qualifierString + ". Check your definitions!");
    }

    public static /* synthetic */ void declare$default(Scope scope, Object instance, Qualifier qualifier, List secondaryTypes, boolean allowOverride, int i, Object obj) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            secondaryTypes = CollectionsKt.emptyList();
        }
        if ((i & 8) != 0) {
            allowOverride = true;
        }
        Intrinsics.checkNotNullParameter(secondaryTypes, "secondaryTypes");
        KoinPlatformTools koinPlatformTools = KoinPlatformTools.INSTANCE;
        Intrinsics.needClassReification();
        koinPlatformTools.m2315synchronized(scope, new Scope$declare$1(scope, instance, qualifier, secondaryTypes, allowOverride));
    }

    public final /* synthetic */ <T> void declare(T instance, Qualifier qualifier, List<? extends KClass<?>> secondaryTypes, boolean allowOverride) {
        Intrinsics.checkNotNullParameter(secondaryTypes, "secondaryTypes");
        KoinPlatformTools koinPlatformTools = KoinPlatformTools.INSTANCE;
        Intrinsics.needClassReification();
        koinPlatformTools.m2315synchronized(this, new Scope$declare$1(this, instance, qualifier, secondaryTypes, allowOverride));
    }

    public final Koin getKoin() {
        return this._koin;
    }

    public final Scope getScope(String scopeID) {
        Intrinsics.checkNotNullParameter(scopeID, "scopeID");
        return getKoin().getScope(scopeID);
    }

    public final void registerCallback(ScopeCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this._callbacks.add(callback);
    }

    public final /* synthetic */ <T> List<T> getAll() {
        Intrinsics.reifiedOperationMarker(4, "T");
        return getAll(Reflection.getOrCreateKotlinClass(Object.class));
    }

    public final <T> List<T> getAll(KClass<?> clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        InstanceContext context = new InstanceContext(this._koin, this, null, 4, null);
        List<T> all$koin_core = this._koin.getInstanceRegistry().getAll$koin_core(clazz, context);
        Iterable $this$flatMap$iv = this.linkedScopes;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            Scope scope = (Scope) element$iv$iv;
            Iterable list$iv$iv = scope.getAll(clazz);
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        return CollectionsKt.plus((Collection) all$koin_core, destination$iv$iv);
    }

    public final <T> T getProperty(String key, T defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (T) this._koin.getProperty(key, defaultValue);
    }

    public final <T> T getPropertyOrNull(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return (T) this._koin.getProperty(key);
    }

    public final <T> T getProperty(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        T t = (T) this._koin.getProperty(key);
        if (t != null) {
            return t;
        }
        throw new MissingPropertyException("Property '" + key + "' not found");
    }

    public final void close() {
        KoinPlatformTools.INSTANCE.m2315synchronized(this, new Function0<Unit>() { // from class: org.koin.core.scope.Scope$close$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Scope.this._closed = true;
                Scope.this.clearData();
                Scope.this.get_koin().getScopeRegistry().deleteScope$koin_core(Scope.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearData() {
        this._source = null;
        if (this._koin.getLogger().isAt(Level.DEBUG)) {
            this._koin.getLogger().info("closing scope:'" + this.id + '\'');
        }
        Iterable $this$forEach$iv = this._callbacks;
        for (Object element$iv : $this$forEach$iv) {
            ScopeCallback it = (ScopeCallback) element$iv;
            it.onScopeClose(this);
        }
        this._callbacks.clear();
    }

    public String toString() {
        return "['" + this.id + "']";
    }
}
