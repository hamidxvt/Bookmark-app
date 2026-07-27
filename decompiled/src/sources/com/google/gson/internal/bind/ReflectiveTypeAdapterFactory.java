package com.google.gson.internal.bind;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.ReflectionAccessFilterHelper;
import com.google.gson.internal.reflect.ReflectionHelper;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes16.dex */
public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    private final List<ReflectionAccessFilter> reflectionFilters;

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor, FieldNamingStrategy fieldNamingPolicy, Excluder excluder, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory, List<ReflectionAccessFilter> reflectionFilters) {
        this.constructorConstructor = constructorConstructor;
        this.fieldNamingPolicy = fieldNamingPolicy;
        this.excluder = excluder;
        this.jsonAdapterFactory = jsonAdapterFactory;
        this.reflectionFilters = reflectionFilters;
    }

    private boolean includeField(Field f, boolean serialize) {
        return (this.excluder.excludeClass(f.getType(), serialize) || this.excluder.excludeField(f, serialize)) ? false : true;
    }

    private List<String> getFieldNames(Field f) {
        SerializedName annotation = (SerializedName) f.getAnnotation(SerializedName.class);
        if (annotation == null) {
            String name = this.fieldNamingPolicy.translateName(f);
            return Collections.singletonList(name);
        }
        String serializedName = annotation.value();
        String[] alternates = annotation.alternate();
        if (alternates.length == 0) {
            return Collections.singletonList(serializedName);
        }
        List<String> fieldNames = new ArrayList<>(alternates.length + 1);
        fieldNames.add(serializedName);
        for (String alternate : alternates) {
            fieldNames.add(alternate);
        }
        return fieldNames;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<? super T> raw = type.getRawType();
        if (!Object.class.isAssignableFrom(raw)) {
            return null;
        }
        ReflectionAccessFilter.FilterResult filterResult = ReflectionAccessFilterHelper.getFilterResult(this.reflectionFilters, raw);
        if (filterResult == ReflectionAccessFilter.FilterResult.BLOCK_ALL) {
            throw new JsonIOException("ReflectionAccessFilter does not permit using reflection for " + raw + ". Register a TypeAdapter for this type or adjust the access filter.");
        }
        boolean blockInaccessible = filterResult == ReflectionAccessFilter.FilterResult.BLOCK_INACCESSIBLE;
        ObjectConstructor<T> constructor = this.constructorConstructor.get(type);
        return new Adapter(constructor, getBoundFields(gson, type, raw, blockInaccessible));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkAccessible(Object object, Field field) {
        if (!ReflectionAccessFilterHelper.canAccess(field, Modifier.isStatic(field.getModifiers()) ? null : object)) {
            throw new JsonIOException("Field '" + field.getDeclaringClass().getName() + "#" + field.getName() + "' is not accessible and ReflectionAccessFilter does not permit making it accessible. Register a TypeAdapter for the declaring type or adjust the access filter.");
        }
    }

    private BoundField createBoundField(final Gson context, final Field field, String name, final TypeToken<?> fieldType, boolean serialize, boolean deserialize, final boolean blockInaccessible) {
        final boolean isPrimitive = Primitives.isPrimitive(fieldType.getRawType());
        JsonAdapter annotation = (JsonAdapter) field.getAnnotation(JsonAdapter.class);
        TypeAdapter<?> mapped = null;
        if (annotation != null) {
            mapped = this.jsonAdapterFactory.getTypeAdapter(this.constructorConstructor, context, fieldType, annotation);
        }
        final boolean jsonAdapterPresent = mapped != null;
        if (mapped == null) {
            mapped = context.getAdapter(fieldType);
        }
        final TypeAdapter<?> mapped2 = mapped;
        return new BoundField(name, serialize, deserialize) { // from class: com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.1
            @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField
            void write(JsonWriter writer, Object value) throws IOException, IllegalAccessException {
                if (this.serialized) {
                    if (blockInaccessible) {
                        ReflectiveTypeAdapterFactory.checkAccessible(value, field);
                    }
                    Object fieldValue = field.get(value);
                    if (fieldValue == value) {
                        return;
                    }
                    writer.name(this.name);
                    TypeAdapter t = jsonAdapterPresent ? mapped2 : new TypeAdapterRuntimeTypeWrapper(context, mapped2, fieldType.getType());
                    t.write(writer, fieldValue);
                }
            }

            @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField
            void read(JsonReader reader, Object value) throws IOException, IllegalAccessException {
                Object fieldValue = mapped2.read2(reader);
                if (fieldValue != null || !isPrimitive) {
                    if (blockInaccessible) {
                        ReflectiveTypeAdapterFactory.checkAccessible(value, field);
                    }
                    field.set(value, fieldValue);
                }
            }
        };
    }

    private Map<String, BoundField> getBoundFields(Gson context, TypeToken<?> type, Class<?> raw, boolean blockInaccessible) {
        boolean blockInaccessible2;
        int i;
        int i2;
        boolean z;
        Class<?> originalRaw;
        ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory = this;
        Map<String, BoundField> result = new LinkedHashMap<>();
        if (raw.isInterface()) {
            return result;
        }
        Type declaredType = type.getType();
        Class<?> originalRaw2 = raw;
        TypeToken<?> type2 = type;
        Class<?> raw2 = raw;
        boolean blockInaccessible3 = blockInaccessible;
        while (raw2 != Object.class) {
            Field[] fields = raw2.getDeclaredFields();
            boolean z2 = false;
            boolean z3 = true;
            if (raw2 != originalRaw2 && fields.length > 0) {
                ReflectionAccessFilter.FilterResult filterResult = ReflectionAccessFilterHelper.getFilterResult(reflectiveTypeAdapterFactory.reflectionFilters, raw2);
                if (filterResult == ReflectionAccessFilter.FilterResult.BLOCK_ALL) {
                    throw new JsonIOException("ReflectionAccessFilter does not permit using reflection for " + raw2 + " (supertype of " + originalRaw2 + "). Register a TypeAdapter for this type or adjust the access filter.");
                }
                boolean blockInaccessible4 = filterResult == ReflectionAccessFilter.FilterResult.BLOCK_INACCESSIBLE;
                blockInaccessible2 = blockInaccessible4;
            } else {
                blockInaccessible2 = blockInaccessible3;
            }
            int length = fields.length;
            int i3 = 0;
            while (i3 < length) {
                Field field = fields[i3];
                boolean serialize = reflectiveTypeAdapterFactory.includeField(field, z3);
                boolean deserialize = reflectiveTypeAdapterFactory.includeField(field, z2);
                if (!serialize && !deserialize) {
                    i = i3;
                    i2 = length;
                    z = z3;
                    originalRaw = originalRaw2;
                } else {
                    if (!blockInaccessible2) {
                        ReflectionHelper.makeAccessible(field);
                    }
                    Type fieldType = C$Gson$Types.resolve(type2.getType(), raw2, field.getGenericType());
                    List<String> fieldNames = reflectiveTypeAdapterFactory.getFieldNames(field);
                    int size = fieldNames.size();
                    BoundField previous = null;
                    int i4 = 0;
                    while (i4 < size) {
                        String name = fieldNames.get(i4);
                        if (i4 != 0) {
                            serialize = false;
                        }
                        boolean serialize2 = serialize;
                        int i5 = i4;
                        Class<?> originalRaw3 = originalRaw2;
                        BoundField previous2 = previous;
                        List<String> fieldNames2 = fieldNames;
                        Field field2 = field;
                        int i6 = i3;
                        int i7 = length;
                        boolean z4 = z3;
                        BoundField boundField = createBoundField(context, field, name, TypeToken.get(fieldType), serialize2, deserialize, blockInaccessible2);
                        BoundField replaced = result.put(name, boundField);
                        previous = previous2 == null ? replaced : previous2;
                        i4 = i5 + 1;
                        serialize = serialize2;
                        i3 = i6;
                        originalRaw2 = originalRaw3;
                        fieldNames = fieldNames2;
                        field = field2;
                        length = i7;
                        z3 = z4;
                    }
                    i = i3;
                    i2 = length;
                    z = z3;
                    originalRaw = originalRaw2;
                    BoundField previous3 = previous;
                    if (previous3 != null) {
                        throw new IllegalArgumentException(declaredType + " declares multiple JSON fields named " + previous3.name);
                    }
                }
                i3 = i + 1;
                z2 = false;
                reflectiveTypeAdapterFactory = this;
                originalRaw2 = originalRaw;
                length = i2;
                z3 = z;
            }
            type2 = TypeToken.get(C$Gson$Types.resolve(type2.getType(), raw2, raw2.getGenericSuperclass()));
            raw2 = type2.getRawType();
            reflectiveTypeAdapterFactory = this;
            blockInaccessible3 = blockInaccessible2;
        }
        return result;
    }

    static abstract class BoundField {
        final boolean deserialized;
        final String name;
        final boolean serialized;

        abstract void read(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException;

        abstract void write(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException;

        protected BoundField(String name, boolean serialized, boolean deserialized) {
            this.name = name;
            this.serialized = serialized;
            this.deserialized = deserialized;
        }
    }

    public static final class Adapter<T> extends TypeAdapter<T> {
        private final Map<String, BoundField> boundFields;
        private final ObjectConstructor<T> constructor;

        Adapter(ObjectConstructor<T> constructor, Map<String, BoundField> boundFields) {
            this.constructor = constructor;
            this.boundFields = boundFields;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public T read2(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            T instance = this.constructor.construct();
            try {
                in.beginObject();
                while (in.hasNext()) {
                    String name = in.nextName();
                    BoundField field = this.boundFields.get(name);
                    if (field != null && field.deserialized) {
                        field.read(in, instance);
                    }
                    in.skipValue();
                }
                in.endObject();
                return instance;
            } catch (IllegalAccessException e) {
                throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
            } catch (IllegalStateException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter out, T value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            try {
                for (BoundField boundField : this.boundFields.values()) {
                    boundField.write(out, value);
                }
                out.endObject();
            } catch (IllegalAccessException e) {
                throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
            }
        }
    }
}
