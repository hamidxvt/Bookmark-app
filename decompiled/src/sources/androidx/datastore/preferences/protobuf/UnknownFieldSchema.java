package androidx.datastore.preferences.protobuf;

import java.io.IOException;

@CheckReturnValue
/* loaded from: classes.dex */
abstract class UnknownFieldSchema<T, B> {
    static final int DEFAULT_RECURSION_LIMIT = 100;
    private static volatile int recursionLimit = 100;

    abstract void addFixed32(B fields, int number, int value);

    abstract void addFixed64(B fields, int number, long value);

    abstract void addGroup(B fields, int number, T subFieldSet);

    abstract void addLengthDelimited(B fields, int number, ByteString value);

    abstract void addVarint(B fields, int number, long value);

    abstract B getBuilderFromMessage(Object message);

    abstract T getFromMessage(Object message);

    abstract int getSerializedSize(T unknowns);

    abstract int getSerializedSizeAsMessageSet(T message);

    abstract void makeImmutable(Object message);

    abstract T merge(T destination, T source);

    abstract B newBuilder();

    abstract void setBuilderToMessage(Object message, B builder);

    abstract void setToMessage(Object message, T fields);

    abstract boolean shouldDiscardUnknownFields(Reader reader);

    abstract T toImmutable(B fields);

    abstract void writeAsMessageSetTo(T unknownFields, Writer writer) throws IOException;

    abstract void writeTo(T unknownFields, Writer writer) throws IOException;

    UnknownFieldSchema() {
    }

    final boolean mergeOneFieldFrom(B unknownFields, Reader reader, int currentDepth) throws IOException {
        int tag = reader.getTag();
        int fieldNumber = WireFormat.getTagFieldNumber(tag);
        switch (WireFormat.getTagWireType(tag)) {
            case 0:
                addVarint(unknownFields, fieldNumber, reader.readInt64());
                return true;
            case 1:
                addFixed64(unknownFields, fieldNumber, reader.readFixed64());
                return true;
            case 2:
                addLengthDelimited(unknownFields, fieldNumber, reader.readBytes());
                return true;
            case 3:
                B subFields = newBuilder();
                int endGroupTag = WireFormat.makeTag(fieldNumber, 4);
                int currentDepth2 = currentDepth + 1;
                if (currentDepth2 >= recursionLimit) {
                    throw InvalidProtocolBufferException.recursionLimitExceeded();
                }
                mergeFrom(subFields, reader, currentDepth2);
                int i = currentDepth2 - 1;
                if (endGroupTag != reader.getTag()) {
                    throw InvalidProtocolBufferException.invalidEndTag();
                }
                addGroup(unknownFields, fieldNumber, toImmutable(subFields));
                return true;
            case 4:
                return false;
            case 5:
                addFixed32(unknownFields, fieldNumber, reader.readFixed32());
                return true;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    private final void mergeFrom(B unknownFields, Reader reader, int currentDepth) throws IOException {
        while (reader.getFieldNumber() != Integer.MAX_VALUE && mergeOneFieldFrom(unknownFields, reader, currentDepth)) {
        }
    }

    public void setRecursionLimit(int limit) {
        recursionLimit = limit;
    }
}
