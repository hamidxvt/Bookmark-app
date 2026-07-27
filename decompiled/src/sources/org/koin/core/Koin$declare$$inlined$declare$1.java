package org.koin.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.BeanDefinitionKt;
import org.koin.core.definition.Kind;
import org.koin.core.instance.ScopedInstanceFactory;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.registry.InstanceRegistry;
import org.koin.core.scope.Scope;

/* compiled from: Scope.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001H\n¨\u0006\u0003"}, d2 = {"<anonymous>", "", "T", "org/koin/core/scope/Scope$declare$1"}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class Koin$declare$$inlined$declare$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ boolean $allowOverride;
    final /* synthetic */ Object $instance;
    final /* synthetic */ Qualifier $qualifier;
    final /* synthetic */ List $secondaryTypes;
    final /* synthetic */ Scope this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Koin$declare$$inlined$declare$1(Scope scope, Object obj, Qualifier qualifier, List list, boolean z) {
        super(0);
        this.this$0 = scope;
        this.$instance = obj;
        this.$qualifier = qualifier;
        this.$secondaryTypes = list;
        this.$allowOverride = z;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        InstanceRegistry this_$iv = this.this$0.get_koin().getInstanceRegistry();
        final Object instance$iv = this.$instance;
        Qualifier qualifier$iv = this.$qualifier;
        List secondaryTypes$iv = this.$secondaryTypes;
        boolean allowOverride$iv = this.$allowOverride;
        Qualifier scopeQualifier$iv = this.this$0.getScopeQualifier();
        Kind kind$iv$iv = Kind.Scoped;
        Intrinsics.needClassReification();
        Function2 definition$iv$iv = new Function2<Scope, ParametersHolder, T>() { // from class: org.koin.core.Koin$declare$$inlined$declare$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final T invoke(Scope createDefinition, ParametersHolder it) {
                Intrinsics.checkNotNullParameter(createDefinition, "$this$createDefinition");
                Intrinsics.checkNotNullParameter(it, "it");
                return (T) instance$iv;
            }
        };
        Intrinsics.reifiedOperationMarker(4, "T");
        BeanDefinition def$iv = new BeanDefinition(scopeQualifier$iv, Reflection.getOrCreateKotlinClass(Object.class), qualifier$iv, definition$iv$iv, kind$iv$iv, secondaryTypes$iv);
        ScopedInstanceFactory factory$iv = new ScopedInstanceFactory(def$iv);
        String indexKey$iv = BeanDefinitionKt.indexKey(def$iv.getPrimaryType(), def$iv.getQualifier(), def$iv.getScopeQualifier());
        InstanceRegistry.saveMapping$default(this_$iv, allowOverride$iv, indexKey$iv, factory$iv, false, 8, null);
        Iterable $this$forEach$iv$iv = def$iv.getSecondaryTypes();
        for (Object element$iv$iv : $this$forEach$iv$iv) {
            KClass clazz$iv = (KClass) element$iv$iv;
            String index$iv = BeanDefinitionKt.indexKey(clazz$iv, def$iv.getQualifier(), def$iv.getScopeQualifier());
            InstanceRegistry.saveMapping$default(this_$iv, allowOverride$iv, index$iv, factory$iv, false, 8, null);
        }
    }
}
