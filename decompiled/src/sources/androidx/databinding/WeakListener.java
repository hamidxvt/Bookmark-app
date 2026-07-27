package androidx.databinding;

import androidx.lifecycle.LifecycleOwner;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
class WeakListener<T> extends WeakReference<ViewDataBinding> {
    protected final int mLocalFieldId;
    private final ObservableReference<T> mObservable;
    private T mTarget;

    public WeakListener(ViewDataBinding binder, int localFieldId, ObservableReference<T> observable, ReferenceQueue<ViewDataBinding> referenceQueue) {
        super(binder, referenceQueue);
        this.mLocalFieldId = localFieldId;
        this.mObservable = observable;
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.mObservable.setLifecycleOwner(lifecycleOwner);
    }

    public void setTarget(T object) {
        unregister();
        this.mTarget = object;
        if (this.mTarget != null) {
            this.mObservable.addListener(this.mTarget);
        }
    }

    public boolean unregister() {
        boolean unregistered = false;
        if (this.mTarget != null) {
            this.mObservable.removeListener(this.mTarget);
            unregistered = true;
        }
        this.mTarget = null;
        return unregistered;
    }

    public T getTarget() {
        return this.mTarget;
    }

    protected ViewDataBinding getBinder() {
        ViewDataBinding binder = (ViewDataBinding) get();
        if (binder == null) {
            unregister();
        }
        return binder;
    }
}
