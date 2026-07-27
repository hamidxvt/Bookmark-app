package org.koin.androidx.fragment.android;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.koin.core.Koin;
import org.koin.core.component.KoinComponent;
import org.koin.core.scope.Scope;

/* compiled from: KoinFragmentFactory.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/koin/androidx/fragment/android/KoinFragmentFactory;", "Landroidx/fragment/app/FragmentFactory;", "Lorg/koin/core/component/KoinComponent;", "scope", "Lorg/koin/core/scope/Scope;", "(Lorg/koin/core/scope/Scope;)V", "instantiate", "Landroidx/fragment/app/Fragment;", "classLoader", "Ljava/lang/ClassLoader;", "className", "", "koin-android_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class KoinFragmentFactory extends FragmentFactory implements KoinComponent {
    private final Scope scope;

    /* JADX WARN: Multi-variable type inference failed */
    public KoinFragmentFactory() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public KoinFragmentFactory(Scope scope) {
        this.scope = scope;
    }

    public /* synthetic */ KoinFragmentFactory(Scope scope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : scope);
    }

    @Override // org.koin.core.component.KoinComponent
    public Koin getKoin() {
        return KoinComponent.DefaultImpls.getKoin(this);
    }

    @Override // androidx.fragment.app.FragmentFactory
    public Fragment instantiate(ClassLoader classLoader, String className) {
        Fragment instance;
        Intrinsics.checkNotNullParameter(classLoader, "classLoader");
        Intrinsics.checkNotNullParameter(className, "className");
        Class<?> cls = Class.forName(className);
        Intrinsics.checkNotNullExpressionValue(cls, "forName(className)");
        KClass clazz = JvmClassMappingKt.getKotlinClass(cls);
        if (this.scope != null) {
            instance = (Fragment) Scope.getOrNull$default(this.scope, clazz, null, null, 6, null);
        } else {
            instance = (Fragment) Koin.getOrNull$default(getKoin(), clazz, null, null, 6, null);
        }
        if (instance != null) {
            return instance;
        }
        Fragment instantiate = super.instantiate(classLoader, className);
        Intrinsics.checkNotNullExpressionValue(instantiate, "super.instantiate(classLoader, className)");
        return instantiate;
    }
}
