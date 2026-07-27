package org.koin.core.registry;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.scope.Scope;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: InstanceRegistry.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "T", "Lorg/koin/core/scope/Scope;", "it", "Lorg/koin/core/parameter/ParametersHolder;"}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class InstanceRegistry$declareInstance$def$1<T> extends Lambda implements Function2<Scope, ParametersHolder, T> {
    final /* synthetic */ T $instance;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InstanceRegistry$declareInstance$def$1(T t) {
        super(2);
        this.$instance = t;
    }

    @Override // kotlin.jvm.functions.Function2
    public final T invoke(Scope createDefinition, ParametersHolder it) {
        Intrinsics.checkNotNullParameter(createDefinition, "$this$createDefinition");
        Intrinsics.checkNotNullParameter(it, "it");
        return this.$instance;
    }
}
