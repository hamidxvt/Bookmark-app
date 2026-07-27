package org.koin.core.instance;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.scope.Scope;

/* compiled from: FactoryInstanceFactory.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\n\u001a\u00020\u0007H\u0016J\u0015\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\rH\u0016¢\u0006\u0002\u0010\u000eJ\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016¨\u0006\u0011"}, d2 = {"Lorg/koin/core/instance/FactoryInstanceFactory;", "T", "Lorg/koin/core/instance/InstanceFactory;", "beanDefinition", "Lorg/koin/core/definition/BeanDefinition;", "(Lorg/koin/core/definition/BeanDefinition;)V", "drop", "", "scope", "Lorg/koin/core/scope/Scope;", "dropAll", Constant.RetrofitConstants.RETROFIT_METHOD_GET, "context", "Lorg/koin/core/instance/InstanceContext;", "(Lorg/koin/core/instance/InstanceContext;)Ljava/lang/Object;", "isCreated", "", "koin-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class FactoryInstanceFactory<T> extends InstanceFactory<T> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FactoryInstanceFactory(BeanDefinition<T> beanDefinition) {
        super(beanDefinition);
        Intrinsics.checkNotNullParameter(beanDefinition, "beanDefinition");
    }

    @Override // org.koin.core.instance.InstanceFactory
    public boolean isCreated(InstanceContext context) {
        return false;
    }

    @Override // org.koin.core.instance.InstanceFactory
    public void drop(Scope scope) {
        Function1<T, Unit> onClose = getBeanDefinition().getCallbacks().getOnClose();
        if (onClose == null) {
            return;
        }
        onClose.invoke(null);
    }

    @Override // org.koin.core.instance.InstanceFactory
    public void dropAll() {
    }

    @Override // org.koin.core.instance.InstanceFactory
    public T get(InstanceContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return create(context);
    }
}
