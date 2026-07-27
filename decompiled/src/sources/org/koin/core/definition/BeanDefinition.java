package org.koin.core.definition;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.registry.ScopeRegistry;
import org.koin.core.scope.Scope;
import org.koin.ext.KClassExtKt;

/* compiled from: BeanDefinition.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002Bj\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012'\u0010\b\u001a#\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u00000\tj\b\u0012\u0004\u0012\u00028\u0000`\f¢\u0006\u0002\b\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0012\b\u0002\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0011¢\u0006\u0002\u0010\u0012J\t\u0010&\u001a\u00020\u0004HÆ\u0003J\r\u0010'\u001a\u0006\u0012\u0002\b\u00030\u0006HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0004HÆ\u0003J*\u0010)\u001a#\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u00000\tj\b\u0012\u0004\u0012\u00028\u0000`\f¢\u0006\u0002\b\rHÆ\u0003J\t\u0010*\u001a\u00020\u000fHÆ\u0003J\u0013\u0010+\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0011HÆ\u0003J|\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\f\b\u0002\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042)\b\u0002\u0010\b\u001a#\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u00000\tj\b\u0012\u0004\u0012\u00028\u0000`\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0012\b\u0002\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0011HÆ\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\u0012\u00100\u001a\u00020.2\n\u00101\u001a\u0006\u0012\u0002\b\u00030\u0006J\b\u00102\u001a\u000203H\u0016J$\u00104\u001a\u00020.2\n\u00101\u001a\u0006\u0012\u0002\b\u00030\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u00105\u001a\u00020\u0004J\b\u00106\u001a\u000207H\u0016R \u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R2\u0010\b\u001a#\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u00000\tj\b\u0012\u0004\u0012\u00028\u0000`\f¢\u0006\u0002\b\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0015\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 R$\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u00068"}, d2 = {"Lorg/koin/core/definition/BeanDefinition;", "T", "", "scopeQualifier", "Lorg/koin/core/qualifier/Qualifier;", "primaryType", "Lkotlin/reflect/KClass;", "qualifier", "definition", "Lkotlin/Function2;", "Lorg/koin/core/scope/Scope;", "Lorg/koin/core/parameter/ParametersHolder;", "Lorg/koin/core/definition/Definition;", "Lkotlin/ExtensionFunctionType;", "kind", "Lorg/koin/core/definition/Kind;", "secondaryTypes", "", "(Lorg/koin/core/qualifier/Qualifier;Lkotlin/reflect/KClass;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function2;Lorg/koin/core/definition/Kind;Ljava/util/List;)V", "callbacks", "Lorg/koin/core/definition/Callbacks;", "getCallbacks", "()Lorg/koin/core/definition/Callbacks;", "setCallbacks", "(Lorg/koin/core/definition/Callbacks;)V", "getDefinition", "()Lkotlin/jvm/functions/Function2;", "getKind", "()Lorg/koin/core/definition/Kind;", "getPrimaryType", "()Lkotlin/reflect/KClass;", "getQualifier", "()Lorg/koin/core/qualifier/Qualifier;", "getScopeQualifier", "getSecondaryTypes", "()Ljava/util/List;", "setSecondaryTypes", "(Ljava/util/List;)V", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hasType", "clazz", "hashCode", "", "is", "scopeDefinition", "toString", "", "koin-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final /* data */ class BeanDefinition<T> {
    private Callbacks<T> callbacks;
    private final Function2<Scope, ParametersHolder, T> definition;
    private final Kind kind;
    private final KClass<?> primaryType;
    private final Qualifier qualifier;
    private final Qualifier scopeQualifier;
    private List<? extends KClass<?>> secondaryTypes;

    public static /* synthetic */ BeanDefinition copy$default(BeanDefinition beanDefinition, Qualifier qualifier, KClass kClass, Qualifier qualifier2, Function2 function2, Kind kind, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = beanDefinition.scopeQualifier;
        }
        if ((i & 2) != 0) {
            kClass = beanDefinition.primaryType;
        }
        KClass kClass2 = kClass;
        if ((i & 4) != 0) {
            qualifier2 = beanDefinition.qualifier;
        }
        Qualifier qualifier3 = qualifier2;
        if ((i & 8) != 0) {
            function2 = beanDefinition.definition;
        }
        Function2 function22 = function2;
        if ((i & 16) != 0) {
            kind = beanDefinition.kind;
        }
        Kind kind2 = kind;
        if ((i & 32) != 0) {
            list = beanDefinition.secondaryTypes;
        }
        return beanDefinition.copy(qualifier, kClass2, qualifier3, function22, kind2, list);
    }

    /* renamed from: component1, reason: from getter */
    public final Qualifier getScopeQualifier() {
        return this.scopeQualifier;
    }

    public final KClass<?> component2() {
        return this.primaryType;
    }

    /* renamed from: component3, reason: from getter */
    public final Qualifier getQualifier() {
        return this.qualifier;
    }

    public final Function2<Scope, ParametersHolder, T> component4() {
        return this.definition;
    }

    /* renamed from: component5, reason: from getter */
    public final Kind getKind() {
        return this.kind;
    }

    public final List<KClass<?>> component6() {
        return this.secondaryTypes;
    }

    public final BeanDefinition<T> copy(Qualifier scopeQualifier, KClass<?> primaryType, Qualifier qualifier, Function2<? super Scope, ? super ParametersHolder, ? extends T> definition, Kind kind, List<? extends KClass<?>> secondaryTypes) {
        Intrinsics.checkNotNullParameter(scopeQualifier, "scopeQualifier");
        Intrinsics.checkNotNullParameter(primaryType, "primaryType");
        Intrinsics.checkNotNullParameter(definition, "definition");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(secondaryTypes, "secondaryTypes");
        return new BeanDefinition<>(scopeQualifier, primaryType, qualifier, definition, kind, secondaryTypes);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BeanDefinition(Qualifier scopeQualifier, KClass<?> primaryType, Qualifier qualifier, Function2<? super Scope, ? super ParametersHolder, ? extends T> definition, Kind kind, List<? extends KClass<?>> secondaryTypes) {
        Intrinsics.checkNotNullParameter(scopeQualifier, "scopeQualifier");
        Intrinsics.checkNotNullParameter(primaryType, "primaryType");
        Intrinsics.checkNotNullParameter(definition, "definition");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(secondaryTypes, "secondaryTypes");
        this.scopeQualifier = scopeQualifier;
        this.primaryType = primaryType;
        this.qualifier = qualifier;
        this.definition = definition;
        this.kind = kind;
        this.secondaryTypes = secondaryTypes;
        this.callbacks = new Callbacks<>(null, 1, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ BeanDefinition(Qualifier qualifier, KClass kClass, Qualifier qualifier2, Function2 function2, Kind kind, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(qualifier, kClass, r3, function2, kind, r6);
        Qualifier qualifier3;
        List list2;
        if ((i & 4) == 0) {
            qualifier3 = qualifier2;
        } else {
            qualifier3 = null;
        }
        if ((i & 32) == 0) {
            list2 = list;
        } else {
            list2 = CollectionsKt.emptyList();
        }
    }

    public final Qualifier getScopeQualifier() {
        return this.scopeQualifier;
    }

    public final KClass<?> getPrimaryType() {
        return this.primaryType;
    }

    public final Qualifier getQualifier() {
        return this.qualifier;
    }

    public final Function2<Scope, ParametersHolder, T> getDefinition() {
        return this.definition;
    }

    public final Kind getKind() {
        return this.kind;
    }

    public final List<KClass<?>> getSecondaryTypes() {
        return this.secondaryTypes;
    }

    public final void setSecondaryTypes(List<? extends KClass<?>> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.secondaryTypes = list;
    }

    public final Callbacks<T> getCallbacks() {
        return this.callbacks;
    }

    public final void setCallbacks(Callbacks<T> callbacks) {
        Intrinsics.checkNotNullParameter(callbacks, "<set-?>");
        this.callbacks = callbacks;
    }

    public String toString() {
        String defName;
        String defKind = this.kind.toString();
        String defType = '\'' + KClassExtKt.getFullName(this.primaryType) + '\'';
        String typesAsString = "";
        if (this.qualifier == null || (defName = Intrinsics.stringPlus(",qualifier:", getQualifier())) == null) {
            defName = "";
        }
        Qualifier it = this.scopeQualifier;
        String defScope = Intrinsics.areEqual(it, ScopeRegistry.INSTANCE.getRootScopeQualifier()) ? "" : Intrinsics.stringPlus(",scope:", getScopeQualifier());
        if (!this.secondaryTypes.isEmpty()) {
            String typesAsString2 = CollectionsKt.joinToString$default(this.secondaryTypes, ",", null, null, 0, null, new Function1<KClass<?>, CharSequence>() { // from class: org.koin.core.definition.BeanDefinition$toString$defOtherTypes$typesAsString$1
                @Override // kotlin.jvm.functions.Function1
                public final CharSequence invoke(KClass<?> it2) {
                    Intrinsics.checkNotNullParameter(it2, "it");
                    return KClassExtKt.getFullName(it2);
                }
            }, 30, null);
            typesAsString = Intrinsics.stringPlus(",binds:", typesAsString2);
        }
        return '[' + defKind + ':' + defType + defName + defScope + typesAsString + ']';
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.koin.core.definition.BeanDefinition<*>");
        }
        return Intrinsics.areEqual(this.primaryType, ((BeanDefinition) other).primaryType) && Intrinsics.areEqual(this.qualifier, ((BeanDefinition) other).qualifier) && Intrinsics.areEqual(this.scopeQualifier, ((BeanDefinition) other).scopeQualifier);
    }

    public final boolean hasType(KClass<?> clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return Intrinsics.areEqual(this.primaryType, clazz) || this.secondaryTypes.contains(clazz);
    }

    public final boolean is(KClass<?> clazz, Qualifier qualifier, Qualifier scopeDefinition) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(scopeDefinition, "scopeDefinition");
        return hasType(clazz) && Intrinsics.areEqual(this.qualifier, qualifier) && Intrinsics.areEqual(this.scopeQualifier, scopeDefinition);
    }

    public int hashCode() {
        Qualifier qualifier = this.qualifier;
        int result = qualifier == null ? 0 : qualifier.hashCode();
        return (((result * 31) + this.primaryType.hashCode()) * 31) + this.scopeQualifier.hashCode();
    }
}
