package org.koin.dsl;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.core.definition.BeanDefinitionKt;
import org.koin.core.definition.Callbacks;
import org.koin.core.instance.InstanceFactory;
import org.koin.core.module.Module;

/* compiled from: DefinitionBinding.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a5\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0001\"\u0006\b\u0000\u0010\u0004\u0018\u0001*\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0001H\u0086\b\u001a9\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0001*\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u00012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0086\u0004\u001aD\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0001*\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u00012\u0010\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\tH\u0086\u0004¢\u0006\u0002\u0010\n\u001aW\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00030\u0001\"\u0004\b\u0000\u0010\u0004*\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00030\u00012\u001e\u0010\u000b\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u0001H\u0004\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u0002H\u0004`\u000eH\u0086\u0004¨\u0006\u000f"}, d2 = {"bind", "Lkotlin/Pair;", "Lorg/koin/core/module/Module;", "Lorg/koin/core/instance/InstanceFactory;", "T", "clazz", "Lkotlin/reflect/KClass;", "binds", "classes", "", "(Lkotlin/Pair;[Lkotlin/reflect/KClass;)Lkotlin/Pair;", "onClose", "Lkotlin/Function1;", "", "Lorg/koin/core/definition/OnCloseCallback;", "koin-core"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class DefinitionBindingKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final Pair<Module, InstanceFactory<?>> bind(Pair<Module, ? extends InstanceFactory<?>> pair, KClass<?> clazz) {
        Intrinsics.checkNotNullParameter(pair, "<this>");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        ((InstanceFactory) pair.getSecond()).getBeanDefinition().setSecondaryTypes(CollectionsKt.plus((Collection<? extends KClass<?>>) ((InstanceFactory) pair.getSecond()).getBeanDefinition().getSecondaryTypes(), clazz));
        String mapping = BeanDefinitionKt.indexKey(clazz, ((InstanceFactory) pair.getSecond()).getBeanDefinition().getQualifier(), ((InstanceFactory) pair.getSecond()).getBeanDefinition().getScopeQualifier());
        ((Module) pair.getFirst()).saveMapping(mapping, (InstanceFactory) pair.getSecond(), true);
        return pair;
    }

    public static final /* synthetic */ <T> Pair<Module, InstanceFactory<?>> bind(Pair<Module, ? extends InstanceFactory<?>> pair) {
        Intrinsics.checkNotNullParameter(pair, "<this>");
        Intrinsics.reifiedOperationMarker(4, "T");
        return bind(pair, Reflection.getOrCreateKotlinClass(Object.class));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Pair<Module, InstanceFactory<?>> binds(Pair<Module, ? extends InstanceFactory<?>> pair, KClass<?>[] classes) {
        Intrinsics.checkNotNullParameter(pair, "<this>");
        Intrinsics.checkNotNullParameter(classes, "classes");
        ((InstanceFactory) pair.getSecond()).getBeanDefinition().setSecondaryTypes(CollectionsKt.plus((Collection) ((InstanceFactory) pair.getSecond()).getBeanDefinition().getSecondaryTypes(), (Object[]) classes));
        for (KClass<?> kClass : classes) {
            String mapping = BeanDefinitionKt.indexKey(kClass, ((InstanceFactory) pair.getSecond()).getBeanDefinition().getQualifier(), ((InstanceFactory) pair.getSecond()).getBeanDefinition().getScopeQualifier());
            ((Module) pair.getFirst()).saveMapping(mapping, (InstanceFactory) pair.getSecond(), true);
        }
        return pair;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Pair<Module, InstanceFactory<T>> onClose(Pair<Module, ? extends InstanceFactory<T>> pair, Function1<? super T, Unit> onClose) {
        Intrinsics.checkNotNullParameter(pair, "<this>");
        Intrinsics.checkNotNullParameter(onClose, "onClose");
        ((InstanceFactory) pair.getSecond()).getBeanDefinition().setCallbacks(new Callbacks<>(onClose));
        return pair;
    }
}
