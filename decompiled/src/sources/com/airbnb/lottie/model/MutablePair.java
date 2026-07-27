package com.airbnb.lottie.model;

import androidx.core.util.Pair;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes.dex */
public class MutablePair<T> {
    T first;
    T second;

    public void set(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<?, ?> p = (Pair) o;
        return objectsEqual(p.first, this.first) && objectsEqual(p.second, this.second);
    }

    private static boolean objectsEqual(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public int hashCode() {
        return (this.first == null ? 0 : this.first.hashCode()) ^ (this.second != null ? this.second.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + this.first + StringUtils.SPACE + this.second + "}";
    }
}
