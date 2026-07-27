package org.apache.commons.lang3;

import java.net.URLClassLoader;
import java.util.Arrays;

/* loaded from: classes17.dex */
public class ClassLoaderUtils {
    public static String toString(ClassLoader classLoader) {
        if (classLoader instanceof URLClassLoader) {
            return toString((URLClassLoader) classLoader);
        }
        return classLoader.toString();
    }

    public static String toString(URLClassLoader classLoader) {
        return classLoader + Arrays.toString(classLoader.getURLs());
    }
}
