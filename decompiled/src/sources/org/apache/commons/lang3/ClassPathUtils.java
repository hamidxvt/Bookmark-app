package org.apache.commons.lang3;

/* loaded from: classes17.dex */
public class ClassPathUtils {
    public static String toFullyQualifiedName(Class<?> context, String resourceName) {
        Validate.notNull(context, "context", new Object[0]);
        Validate.notNull(resourceName, "resourceName", new Object[0]);
        return toFullyQualifiedName(context.getPackage(), resourceName);
    }

    public static String toFullyQualifiedName(Package context, String resourceName) {
        Validate.notNull(context, "context", new Object[0]);
        Validate.notNull(resourceName, "resourceName", new Object[0]);
        return context.getName() + "." + resourceName;
    }

    public static String toFullyQualifiedPath(Class<?> context, String resourceName) {
        Validate.notNull(context, "context", new Object[0]);
        Validate.notNull(resourceName, "resourceName", new Object[0]);
        return toFullyQualifiedPath(context.getPackage(), resourceName);
    }

    public static String toFullyQualifiedPath(Package context, String resourceName) {
        Validate.notNull(context, "context", new Object[0]);
        Validate.notNull(resourceName, "resourceName", new Object[0]);
        return context.getName().replace(ClassUtils.PACKAGE_SEPARATOR_CHAR, '/') + "/" + resourceName;
    }
}
