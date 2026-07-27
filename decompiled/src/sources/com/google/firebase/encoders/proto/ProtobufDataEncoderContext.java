package com.google.firebase.encoders.proto;

import com.github.mikephil.charting.utils.Utils;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes16.dex */
final class ProtobufDataEncoderContext implements ObjectEncoderContext {
    private final ObjectEncoder<Object> fallbackEncoder;
    private final Map<Class<?>, ObjectEncoder<?>> objectEncoders;
    private OutputStream output;
    private final ProtobufValueEncoderContext valueEncoderContext = new ProtobufValueEncoderContext(this);
    private final Map<Class<?>, ValueEncoder<?>> valueEncoders;
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final FieldDescriptor MAP_KEY_DESC = FieldDescriptor.builder("key").withProperty(AtProtobuf.builder().tag(1).build()).build();
    private static final FieldDescriptor MAP_VALUE_DESC = FieldDescriptor.builder("value").withProperty(AtProtobuf.builder().tag(2).build()).build();
    private static final ObjectEncoder<Map.Entry<Object, Object>> DEFAULT_MAP_ENCODER = new ObjectEncoder() { // from class: com.google.firebase.encoders.proto.ProtobufDataEncoderContext$$ExternalSyntheticLambda0
        @Override // com.google.firebase.encoders.Encoder
        public final void encode(Object obj, ObjectEncoderContext objectEncoderContext) {
            ProtobufDataEncoderContext.lambda$static$0((Map.Entry) obj, objectEncoderContext);
        }
    };

    static /* synthetic */ void lambda$static$0(Map.Entry o, ObjectEncoderContext ctx) throws IOException {
        ctx.add(MAP_KEY_DESC, o.getKey());
        ctx.add(MAP_VALUE_DESC, o.getValue());
    }

    ProtobufDataEncoderContext(OutputStream output, Map<Class<?>, ObjectEncoder<?>> objectEncoders, Map<Class<?>, ValueEncoder<?>> valueEncoders, ObjectEncoder<Object> fallbackEncoder) {
        this.output = output;
        this.objectEncoders = objectEncoders;
        this.valueEncoders = valueEncoders;
        this.fallbackEncoder = fallbackEncoder;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String name, Object obj) throws IOException {
        return add(FieldDescriptor.of(name), obj);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String name, double value) throws IOException {
        return add(FieldDescriptor.of(name), value);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String name, int value) throws IOException {
        return add(FieldDescriptor.of(name), value);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String name, long value) throws IOException {
        return add(FieldDescriptor.of(name), value);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String name, boolean value) throws IOException {
        return add(FieldDescriptor.of(name), value);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor field, Object obj) throws IOException {
        return add(field, obj, true);
    }

    ObjectEncoderContext add(FieldDescriptor field, Object obj, boolean skipDefault) throws IOException {
        if (obj == null) {
            return this;
        }
        if (obj instanceof CharSequence) {
            CharSequence seq = (CharSequence) obj;
            if (skipDefault && seq.length() == 0) {
                return this;
            }
            int tag = getTag(field);
            writeVarInt32((tag << 3) | 2);
            byte[] bytes = seq.toString().getBytes(UTF_8);
            writeVarInt32(bytes.length);
            this.output.write(bytes);
            return this;
        }
        if (obj instanceof Collection) {
            Collection<Object> collection = (Collection) obj;
            for (Object value : collection) {
                add(field, value, false);
            }
            return this;
        }
        if (obj instanceof Map) {
            Map<Object, Object> map = (Map) obj;
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                doEncode((ObjectEncoder<FieldDescriptor>) DEFAULT_MAP_ENCODER, field, (FieldDescriptor) entry, false);
            }
            return this;
        }
        if (obj instanceof Double) {
            return add(field, ((Double) obj).doubleValue(), skipDefault);
        }
        if (obj instanceof Float) {
            return add(field, ((Float) obj).floatValue(), skipDefault);
        }
        if (obj instanceof Number) {
            return add(field, ((Number) obj).longValue(), skipDefault);
        }
        if (obj instanceof Boolean) {
            return add(field, ((Boolean) obj).booleanValue(), skipDefault);
        }
        if (obj instanceof byte[]) {
            byte[] bytes2 = (byte[]) obj;
            if (skipDefault && bytes2.length == 0) {
                return this;
            }
            int tag2 = getTag(field);
            writeVarInt32((tag2 << 3) | 2);
            writeVarInt32(bytes2.length);
            this.output.write(bytes2);
            return this;
        }
        ObjectEncoder<Object> objectEncoder = this.objectEncoders.get(obj.getClass());
        if (objectEncoder != null) {
            return doEncode((ObjectEncoder<FieldDescriptor>) objectEncoder, field, (FieldDescriptor) obj, skipDefault);
        }
        ValueEncoder<Object> valueEncoder = this.valueEncoders.get(obj.getClass());
        if (valueEncoder != null) {
            return doEncode((ValueEncoder<FieldDescriptor>) valueEncoder, field, (FieldDescriptor) obj, skipDefault);
        }
        if (obj instanceof ProtoEnum) {
            return add(field, ((ProtoEnum) obj).getNumber());
        }
        if (obj instanceof Enum) {
            return add(field, ((Enum) obj).ordinal());
        }
        return doEncode((ObjectEncoder<FieldDescriptor>) this.fallbackEncoder, field, (FieldDescriptor) obj, skipDefault);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor field, double value) throws IOException {
        return add(field, value, true);
    }

    ObjectEncoderContext add(FieldDescriptor field, double value, boolean skipDefault) throws IOException {
        if (skipDefault && value == Utils.DOUBLE_EPSILON) {
            return this;
        }
        int tag = getTag(field);
        writeVarInt32((tag << 3) | 1);
        this.output.write(allocateBuffer(8).putDouble(value).array());
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor field, float value) throws IOException {
        return add(field, value, true);
    }

    ObjectEncoderContext add(FieldDescriptor field, float value, boolean skipDefault) throws IOException {
        if (skipDefault && value == 0.0f) {
            return this;
        }
        int tag = getTag(field);
        writeVarInt32((tag << 3) | 5);
        this.output.write(allocateBuffer(4).putFloat(value).array());
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ProtobufDataEncoderContext add(FieldDescriptor field, int value) throws IOException {
        return add(field, value, true);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0056, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    ProtobufDataEncoderContext add(FieldDescriptor field, int value, boolean skipDefault) throws IOException {
        if (skipDefault && value == 0) {
            return this;
        }
        Protobuf protobuf = getProtobuf(field);
        switch (protobuf.intEncoding()) {
            case DEFAULT:
                writeVarInt32(protobuf.tag() << 3);
                writeVarInt32(value);
                break;
            case SIGNED:
                writeVarInt32(protobuf.tag() << 3);
                writeVarInt32((value << 1) ^ (value >> 31));
                break;
            case FIXED:
                writeVarInt32((protobuf.tag() << 3) | 5);
                this.output.write(allocateBuffer(4).putInt(value).array());
                break;
        }
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ProtobufDataEncoderContext add(FieldDescriptor field, long value) throws IOException {
        return add(field, value, true);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x005d, code lost:
    
        return r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    ProtobufDataEncoderContext add(FieldDescriptor field, long value, boolean skipDefault) throws IOException {
        if (skipDefault && value == 0) {
            return this;
        }
        Protobuf protobuf = getProtobuf(field);
        switch (protobuf.intEncoding()) {
            case DEFAULT:
                writeVarInt32(protobuf.tag() << 3);
                writeVarInt64(value);
                break;
            case SIGNED:
                writeVarInt32(protobuf.tag() << 3);
                writeVarInt64((value << 1) ^ (value >> 63));
                break;
            case FIXED:
                writeVarInt32((protobuf.tag() << 3) | 1);
                this.output.write(allocateBuffer(8).putLong(value).array());
                break;
        }
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ProtobufDataEncoderContext add(FieldDescriptor field, boolean value) throws IOException {
        return add(field, value, true);
    }

    ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, boolean z, boolean z2) throws IOException {
        return add(fieldDescriptor, z ? 1 : 0, z2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext inline(Object value) throws IOException {
        return encode(value);
    }

    ProtobufDataEncoderContext encode(Object value) throws IOException {
        if (value == null) {
            return this;
        }
        ObjectEncoder<Object> objectEncoder = this.objectEncoders.get(value.getClass());
        if (objectEncoder != null) {
            objectEncoder.encode(value, this);
            return this;
        }
        throw new EncodingException("No encoder for " + value.getClass());
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext nested(String name) throws IOException {
        return nested(FieldDescriptor.of(name));
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext nested(FieldDescriptor field) throws IOException {
        throw new EncodingException("nested() is not implemented for protobuf encoding.");
    }

    private <T> ProtobufDataEncoderContext doEncode(ObjectEncoder<T> encoder, FieldDescriptor field, T obj, boolean skipDefault) throws IOException {
        long size = determineSize(encoder, obj);
        if (skipDefault && size == 0) {
            return this;
        }
        int tag = getTag(field);
        writeVarInt32((tag << 3) | 2);
        writeVarInt64(size);
        encoder.encode(obj, this);
        return this;
    }

    private <T> long determineSize(ObjectEncoder<T> encoder, T obj) throws IOException {
        LengthCountingOutputStream out = new LengthCountingOutputStream();
        try {
            OutputStream originalStream = this.output;
            this.output = out;
            try {
                encoder.encode(obj, this);
                this.output = originalStream;
                long length = out.getLength();
                out.close();
                return length;
            } catch (Throwable th) {
                this.output = originalStream;
                throw th;
            }
        } catch (Throwable th2) {
            try {
                out.close();
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
            throw th2;
        }
    }

    private <T> ProtobufDataEncoderContext doEncode(ValueEncoder<T> encoder, FieldDescriptor field, T obj, boolean skipDefault) throws IOException {
        this.valueEncoderContext.resetContext(field, skipDefault);
        encoder.encode(obj, this.valueEncoderContext);
        return this;
    }

    private static ByteBuffer allocateBuffer(int length) {
        return ByteBuffer.allocate(length).order(ByteOrder.LITTLE_ENDIAN);
    }

    private static int getTag(FieldDescriptor field) {
        Protobuf protobuf = (Protobuf) field.getProperty(Protobuf.class);
        if (protobuf == null) {
            throw new EncodingException("Field has no @Protobuf config");
        }
        return protobuf.tag();
    }

    private static Protobuf getProtobuf(FieldDescriptor field) {
        Protobuf protobuf = (Protobuf) field.getProperty(Protobuf.class);
        if (protobuf == null) {
            throw new EncodingException("Field has no @Protobuf config");
        }
        return protobuf;
    }

    private void writeVarInt32(int value) throws IOException {
        while ((value & (-128)) != 0) {
            this.output.write((value & 127) | 128);
            value >>>= 7;
        }
        this.output.write(value & 127);
    }

    private void writeVarInt64(long value) throws IOException {
        while (((-128) & value) != 0) {
            this.output.write((((int) value) & 127) | 128);
            value >>>= 7;
        }
        this.output.write(((int) value) & 127);
    }
}
