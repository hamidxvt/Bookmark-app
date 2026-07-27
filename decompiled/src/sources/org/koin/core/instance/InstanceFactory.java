package org.koin.core.instance;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.error.InstanceCreationException;
import org.koin.core.logger.Level;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.parameter.ParametersHolderKt;
import org.koin.core.scope.Scope;
import org.koin.mp.KoinPlatformTools;

/* compiled from: InstanceFactory.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b&\u0018\u0000 \u0014*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0014B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u0015\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000bJ\u0014\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&J\b\u0010\u0010\u001a\u00020\rH&J\u0015\u0010\u0011\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0002\u0010\u000bJ\u0014\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH&R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0015"}, d2 = {"Lorg/koin/core/instance/InstanceFactory;", "T", "", "beanDefinition", "Lorg/koin/core/definition/BeanDefinition;", "(Lorg/koin/core/definition/BeanDefinition;)V", "getBeanDefinition", "()Lorg/koin/core/definition/BeanDefinition;", "create", "context", "Lorg/koin/core/instance/InstanceContext;", "(Lorg/koin/core/instance/InstanceContext;)Ljava/lang/Object;", "drop", "", "scope", "Lorg/koin/core/scope/Scope;", "dropAll", Constant.RetrofitConstants.RETROFIT_METHOD_GET, "isCreated", "", "Companion", "koin-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public abstract class InstanceFactory<T> {
    public static final String ERROR_SEPARATOR = "\n\t";
    private final BeanDefinition<T> beanDefinition;

    public abstract void drop(Scope scope);

    public abstract void dropAll();

    public abstract T get(InstanceContext context);

    public abstract boolean isCreated(InstanceContext context);

    public InstanceFactory(BeanDefinition<T> beanDefinition) {
        Intrinsics.checkNotNullParameter(beanDefinition, "beanDefinition");
        this.beanDefinition = beanDefinition;
    }

    public final BeanDefinition<T> getBeanDefinition() {
        return this.beanDefinition;
    }

    public T create(InstanceContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Koin koin = context.getKoin();
        if (koin.getLogger().isAt(Level.DEBUG)) {
            koin.getLogger().debug(Intrinsics.stringPlus("| create instance for ", this.beanDefinition));
        }
        try {
            ParametersHolder parameters = context.getParameters();
            if (parameters == null) {
                parameters = ParametersHolderKt.emptyParametersHolder();
            }
            return this.beanDefinition.getDefinition().invoke(context.getScope(), parameters);
        } catch (Exception e) {
            String stack = KoinPlatformTools.INSTANCE.getStackTrace(e);
            koin.getLogger().error("Instance creation error : could not create instance for " + this.beanDefinition + ": " + stack);
            throw new InstanceCreationException(Intrinsics.stringPlus("Could not create instance for ", this.beanDefinition), e);
        }
    }

    public static /* synthetic */ boolean isCreated$default(InstanceFactory instanceFactory, InstanceContext instanceContext, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: isCreated");
        }
        if ((i & 1) != 0) {
            instanceContext = null;
        }
        return instanceFactory.isCreated(instanceContext);
    }

    public static /* synthetic */ void drop$default(InstanceFactory instanceFactory, Scope scope, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drop");
        }
        if ((i & 1) != 0) {
            scope = null;
        }
        instanceFactory.drop(scope);
    }
}
