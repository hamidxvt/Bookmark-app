package androidx.databinding;

import androidx.databinding.Observable;

/* loaded from: classes.dex */
abstract class BaseObservableField extends BaseObservable {
    public BaseObservableField() {
    }

    public BaseObservableField(Observable... dependencies) {
        if (dependencies != null && dependencies.length != 0) {
            DependencyCallback callback = new DependencyCallback();
            for (Observable observable : dependencies) {
                observable.addOnPropertyChangedCallback(callback);
            }
        }
    }

    class DependencyCallback extends Observable.OnPropertyChangedCallback {
        DependencyCallback() {
        }

        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(Observable sender, int propertyId) {
            BaseObservableField.this.notifyChange();
        }
    }
}
