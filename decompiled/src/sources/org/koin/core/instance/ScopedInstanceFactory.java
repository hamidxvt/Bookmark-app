package org.koin.core.instance;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.scope.Scope;
import org.koin.mp.KoinPlatformTools;

/* compiled from: ScopedInstanceFactory.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u0015\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\rH\u0016¢\u0006\u0002\u0010\u000eJ\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0010H\u0016J\u0015\u0010\u0014\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\rH\u0016¢\u0006\u0002\u0010\u000eJ\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u001a\u0010\u0017\u001a\u00020\u00102\n\u0010\u0018\u001a\u00060\bj\u0002`\t2\u0006\u0010\u0019\u001a\u00020\u001aR2\u0010\u0006\u001a&\u0012\b\u0012\u00060\bj\u0002`\t\u0012\u0004\u0012\u00028\u00000\u0007j\u0012\u0012\b\u0012\u00060\bj\u0002`\t\u0012\u0004\u0012\u00028\u0000`\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/koin/core/instance/ScopedInstanceFactory;", "T", "Lorg/koin/core/instance/InstanceFactory;", "beanDefinition", "Lorg/koin/core/definition/BeanDefinition;", "(Lorg/koin/core/definition/BeanDefinition;)V", "values", "Ljava/util/HashMap;", "", "Lorg/koin/core/scope/ScopeID;", "Lkotlin/collections/HashMap;", "create", "context", "Lorg/koin/core/instance/InstanceContext;", "(Lorg/koin/core/instance/InstanceContext;)Ljava/lang/Object;", "drop", "", "scope", "Lorg/koin/core/scope/Scope;", "dropAll", Constant.RetrofitConstants.RETROFIT_METHOD_GET, "isCreated", "", "refreshInstance", "scopeID", "instance", "", "koin-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class ScopedInstanceFactory<T> extends InstanceFactory<T> {
    private HashMap<String, T> values;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScopedInstanceFactory(BeanDefinition<T> beanDefinition) {
        super(beanDefinition);
        Intrinsics.checkNotNullParameter(beanDefinition, "beanDefinition");
        this.values = new HashMap<>();
    }

    @Override // org.koin.core.instance.InstanceFactory
    public boolean isCreated(InstanceContext context) {
        Scope scope;
        HashMap<String, T> hashMap = this.values;
        String str = null;
        if (context != null && (scope = context.getScope()) != null) {
            str = scope.getId();
        }
        return hashMap.get(str) != null;
    }

    @Override // org.koin.core.instance.InstanceFactory
    public void drop(Scope scope) {
        if (scope != null) {
            Function1<T, Unit> onClose = getBeanDefinition().getCallbacks().getOnClose();
            if (onClose != null) {
                onClose.invoke(this.values.get(scope.getId()));
            }
            this.values.remove(scope.getId());
        }
    }

    @Override // org.koin.core.instance.InstanceFactory
    public T create(InstanceContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.values.get(context.getScope().getId()) == null) {
            return (T) super.create(context);
        }
        T t = this.values.get(context.getScope().getId());
        if (t != null) {
            return t;
        }
        throw new IllegalStateException(("Scoped instance not found for " + context.getScope().getId() + " in " + getBeanDefinition()).toString());
    }

    @Override // org.koin.core.instance.InstanceFactory
    public T get(final InstanceContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!Intrinsics.areEqual(context.getScope().getScopeQualifier(), getBeanDefinition().getScopeQualifier())) {
            throw new IllegalStateException(("Wrong Scope: trying to open instance for " + context.getScope().getId() + " in " + getBeanDefinition()).toString());
        }
        KoinPlatformTools.INSTANCE.m2315synchronized(this, new Function0<Unit>(this) { // from class: org.koin.core.instance.ScopedInstanceFactory$get$1
            final /* synthetic */ ScopedInstanceFactory<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                HashMap hashMap;
                if (!this.this$0.isCreated(context)) {
                    hashMap = ((ScopedInstanceFactory) this.this$0).values;
                    hashMap.put(context.getScope().getId(), this.this$0.create(context));
                }
            }
        });
        T t = this.values.get(context.getScope().getId());
        if (t != null) {
            return t;
        }
        throw new IllegalStateException(("Scoped instance not found for " + context.getScope().getId() + " in " + getBeanDefinition()).toString());
    }

    @Override // org.koin.core.instance.InstanceFactory
    public void dropAll() {
        this.values.clear();
    }

    public final void refreshInstance(String scopeID, Object instance) {
        Intrinsics.checkNotNullParameter(scopeID, "scopeID");
        Intrinsics.checkNotNullParameter(instance, "instance");
        this.values.put(scopeID, instance);
    }
}
