package androidx.databinding;

import androidx.collection.ArrayMap;
import androidx.databinding.ObservableMap;
import java.util.Collection;

/* loaded from: classes.dex */
public class ObservableArrayMap<K, V> extends ArrayMap<K, V> implements ObservableMap<K, V> {
    private transient MapChangeRegistry mListeners;

    @Override // androidx.databinding.ObservableMap
    public void addOnMapChangedCallback(ObservableMap.OnMapChangedCallback<? extends ObservableMap<K, V>, K, V> listener) {
        if (this.mListeners == null) {
            this.mListeners = new MapChangeRegistry();
        }
        this.mListeners.add(listener);
    }

    @Override // androidx.databinding.ObservableMap
    public void removeOnMapChangedCallback(ObservableMap.OnMapChangedCallback<? extends ObservableMap<K, V>, K, V> listener) {
        if (this.mListeners != null) {
            this.mListeners.remove(listener);
        }
    }

    @Override // androidx.collection.SimpleArrayMap, java.util.Map
    public void clear() {
        boolean wasEmpty = isEmpty();
        if (!wasEmpty) {
            super.clear();
            notifyChange(null);
        }
    }

    @Override // androidx.collection.SimpleArrayMap, java.util.Map
    public V put(K k, V v) {
        super.put(k, v);
        notifyChange(k);
        return v;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.collection.ArrayMap
    public boolean removeAll(Collection<?> collection) {
        boolean removed = false;
        for (Object key : collection) {
            int index = indexOfKey(key);
            if (index >= 0) {
                removed = true;
                removeAt(index);
            }
        }
        return removed;
    }

    @Override // androidx.collection.ArrayMap
    public boolean retainAll(Collection<?> collection) {
        boolean removed = false;
        for (int i = getSize() - 1; i >= 0; i--) {
            Object key = keyAt(i);
            if (!collection.contains(key)) {
                removeAt(i);
                removed = true;
            }
        }
        return removed;
    }

    @Override // androidx.collection.SimpleArrayMap
    public V removeAt(int i) {
        K keyAt = keyAt(i);
        V v = (V) super.removeAt(i);
        if (v != null) {
            notifyChange(keyAt);
        }
        return v;
    }

    @Override // androidx.collection.SimpleArrayMap
    public V setValueAt(int i, V v) {
        K keyAt = keyAt(i);
        V v2 = (V) super.setValueAt(i, v);
        notifyChange(keyAt);
        return v2;
    }

    private void notifyChange(Object key) {
        if (this.mListeners != null) {
            this.mListeners.notifyCallbacks(this, 0, key);
        }
    }
}
