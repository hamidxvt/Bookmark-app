package org.apache.commons.lang3;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes17.dex */
public class SerializationUtils {

    static class ClassLoaderAwareObjectInputStream extends ObjectInputStream {
        private static final Map<String, Class<?>> primitiveTypes = new HashMap();
        private final ClassLoader classLoader;

        static {
            primitiveTypes.put("byte", Byte.TYPE);
            primitiveTypes.put("short", Short.TYPE);
            primitiveTypes.put("int", Integer.TYPE);
            primitiveTypes.put("long", Long.TYPE);
            primitiveTypes.put(TypedValues.Custom.S_FLOAT, Float.TYPE);
            primitiveTypes.put("double", Double.TYPE);
            primitiveTypes.put(TypedValues.Custom.S_BOOLEAN, Boolean.TYPE);
            primitiveTypes.put("char", Character.TYPE);
            primitiveTypes.put("void", Void.TYPE);
        }

        ClassLoaderAwareObjectInputStream(InputStream in, ClassLoader classLoader) throws IOException {
            super(in);
            this.classLoader = classLoader;
        }

        @Override // java.io.ObjectInputStream
        protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
            String name = desc.getName();
            try {
                return Class.forName(name, false, this.classLoader);
            } catch (ClassNotFoundException e) {
                try {
                    return Class.forName(name, false, Thread.currentThread().getContextClassLoader());
                } catch (ClassNotFoundException cnfe) {
                    Class<?> cls = primitiveTypes.get(name);
                    if (cls != null) {
                        return cls;
                    }
                    throw cnfe;
                }
            }
        }
    }

    public static <T extends Serializable> T clone(T object) {
        if (object == null) {
            return null;
        }
        byte[] objectData = serialize(object);
        ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
        try {
            ClassLoaderAwareObjectInputStream in = new ClassLoaderAwareObjectInputStream(bais, object.getClass().getClassLoader());
            try {
                T t = (T) in.readObject();
                in.close();
                return t;
            } finally {
            }
        } catch (IOException ex) {
            throw new SerializationException("IOException while reading or closing cloned object data", ex);
        } catch (ClassNotFoundException ex2) {
            throw new SerializationException("ClassNotFoundException while reading cloned object data", ex2);
        }
    }

    public static <T> T deserialize(byte[] bArr) {
        Validate.notNull(bArr, "objectData", new Object[0]);
        return (T) deserialize(new ByteArrayInputStream(bArr));
    }

    public static <T> T deserialize(InputStream inputStream) {
        Validate.notNull(inputStream, "inputStream", new Object[0]);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            try {
                T t = (T) objectInputStream.readObject();
                objectInputStream.close();
                return t;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        objectInputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new SerializationException(e);
        }
    }

    public static <T extends Serializable> T roundtrip(T obj) {
        return (T) deserialize(serialize(obj));
    }

    public static byte[] serialize(Serializable obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        serialize(obj, baos);
        return baos.toByteArray();
    }

    public static void serialize(Serializable obj, OutputStream outputStream) {
        Validate.notNull(outputStream, "outputStream", new Object[0]);
        try {
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            try {
                out.writeObject(obj);
                out.close();
            } finally {
            }
        } catch (IOException ex) {
            throw new SerializationException(ex);
        }
    }
}
