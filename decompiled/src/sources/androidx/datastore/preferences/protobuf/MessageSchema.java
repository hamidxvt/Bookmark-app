package androidx.datastore.preferences.protobuf;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import androidx.datastore.preferences.protobuf.ArrayDecoders;
import androidx.datastore.preferences.protobuf.ByteString;
import androidx.datastore.preferences.protobuf.Internal;
import androidx.datastore.preferences.protobuf.MapEntryLite;
import androidx.datastore.preferences.protobuf.WireFormat;
import androidx.datastore.preferences.protobuf.Writer;
import com.github.mikephil.charting.utils.Utils;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import sun.misc.Unsafe;

@CheckReturnValue
/* loaded from: classes.dex */
final class MessageSchema<T> implements Schema<T> {
    private static final int CHECK_INITIALIZED_BIT = 1024;
    private static final int ENFORCE_UTF8_MASK = 536870912;
    private static final int FIELD_TYPE_MASK = 267386880;
    private static final int HAS_HAS_BIT = 4096;
    private static final int INTS_PER_FIELD = 3;
    private static final int LEGACY_ENUM_IS_CLOSED_BIT = 2048;
    private static final int LEGACY_ENUM_IS_CLOSED_MASK = Integer.MIN_VALUE;
    private static final int NO_PRESENCE_SENTINEL = 1048575;
    private static final int OFFSET_BITS = 20;
    private static final int OFFSET_MASK = 1048575;
    static final int ONEOF_TYPE_OFFSET = 51;
    private static final int REQUIRED_BIT = 256;
    private static final int REQUIRED_MASK = 268435456;
    private static final int UTF8_CHECK_BIT = 512;
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final int repeatedFieldOffsetStart;
    private final ProtoSyntax syntax;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;
    private final boolean useCachedSizeField;
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();

    private MessageSchema(int[] buffer, Object[] objects, int minFieldNumber, int maxFieldNumber, MessageLite defaultInstance, ProtoSyntax syntax, boolean useCachedSizeField, int[] intArray, int checkInitialized, int mapFieldPositions, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        this.buffer = buffer;
        this.objects = objects;
        this.minFieldNumber = minFieldNumber;
        this.maxFieldNumber = maxFieldNumber;
        this.lite = defaultInstance instanceof GeneratedMessageLite;
        this.syntax = syntax;
        this.hasExtensions = extensionSchema != null && extensionSchema.hasExtensions(defaultInstance);
        this.useCachedSizeField = useCachedSizeField;
        this.intArray = intArray;
        this.checkInitializedCount = checkInitialized;
        this.repeatedFieldOffsetStart = mapFieldPositions;
        this.newInstanceSchema = newInstanceSchema;
        this.listFieldSchema = listFieldSchema;
        this.unknownFieldSchema = unknownFieldSchema;
        this.extensionSchema = extensionSchema;
        this.defaultInstance = defaultInstance;
        this.mapFieldSchema = mapFieldSchema;
    }

    static <T> MessageSchema<T> newSchema(Class<T> messageClass, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        if (messageInfo instanceof RawMessageInfo) {
            return newSchemaForRawMessageInfo((RawMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
        }
        return newSchemaForMessageInfo((StructuralMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x03a0  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x043a  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0423  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x039a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static <T> MessageSchema<T> newSchemaForRawMessageInfo(RawMessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int objectsPosition;
        int minFieldNumber;
        int maxFieldNumber;
        int numEntries;
        int mapFieldCount;
        int checkInitialized;
        int[] intArray;
        int hasBitsCount;
        int i;
        int next;
        int i2;
        int next2;
        int i3;
        int next3;
        int i4;
        int next4;
        int i5;
        int next5;
        int i6;
        int next6;
        int i7;
        int next7;
        int i8;
        int next8;
        int length;
        int fieldCount;
        int next9;
        boolean hasHasBit;
        String info;
        int objectsPosition2;
        int unusedFlags;
        int presenceFieldOffset;
        int hasBitsIndex;
        int fieldOffset;
        int presenceMaskShift;
        int next10;
        java.lang.reflect.Field hasBitsField;
        int i9;
        int next11;
        Object o;
        java.lang.reflect.Field oneofField;
        Object o2;
        java.lang.reflect.Field oneofCaseField;
        int i10;
        int next12;
        int i11;
        int next13;
        int i12;
        int next14;
        int i13;
        int next15;
        int i14;
        int next16;
        String info2 = messageInfo.getStringInfo();
        int length2 = info2.length();
        int i15 = 0 + 1;
        int next17 = info2.charAt(0);
        int i16 = 55296;
        if (next17 >= 55296) {
            int result = next17 & 8191;
            int shift = 13;
            while (true) {
                i14 = i15 + 1;
                next16 = info2.charAt(i15);
                if (next16 < 55296) {
                    break;
                }
                result |= (next16 & 8191) << shift;
                shift += 13;
                i15 = i14;
            }
            next17 = result | (next16 << shift);
            i15 = i14;
        }
        int unusedFlags2 = next17;
        int i17 = i15 + 1;
        int next18 = info2.charAt(i15);
        if (next18 >= 55296) {
            int result2 = next18 & 8191;
            int shift2 = 13;
            while (true) {
                i13 = i17 + 1;
                next15 = info2.charAt(i17);
                if (next15 < 55296) {
                    break;
                }
                result2 |= (next15 & 8191) << shift2;
                shift2 += 13;
                i17 = i13;
            }
            next18 = result2 | (next15 << shift2);
            i17 = i13;
        }
        int fieldCount2 = next18;
        if (fieldCount2 == 0) {
            hasBitsCount = 0;
            int[] intArray2 = EMPTY_INT_ARRAY;
            objectsPosition = 0;
            minFieldNumber = 0;
            maxFieldNumber = 0;
            numEntries = 0;
            mapFieldCount = 0;
            checkInitialized = 0;
            intArray = intArray2;
        } else {
            int i18 = i17 + 1;
            int next19 = info2.charAt(i17);
            if (next19 >= 55296) {
                int result3 = next19 & 8191;
                int shift3 = 13;
                while (true) {
                    i8 = i18 + 1;
                    next8 = info2.charAt(i18);
                    if (next8 < 55296) {
                        break;
                    }
                    result3 |= (next8 & 8191) << shift3;
                    shift3 += 13;
                    i18 = i8;
                }
                next19 = result3 | (next8 << shift3);
                i18 = i8;
            }
            int result4 = next19;
            int i19 = i18 + 1;
            int next20 = info2.charAt(i18);
            if (next20 >= 55296) {
                int result5 = next20 & 8191;
                int shift4 = 13;
                while (true) {
                    i7 = i19 + 1;
                    next7 = info2.charAt(i19);
                    if (next7 < 55296) {
                        break;
                    }
                    result5 |= (next7 & 8191) << shift4;
                    shift4 += 13;
                    i19 = i7;
                }
                next20 = result5 | (next7 << shift4);
                i19 = i7;
            }
            int result6 = next20;
            int i20 = i19 + 1;
            int next21 = info2.charAt(i19);
            if (next21 >= 55296) {
                int result7 = next21 & 8191;
                int shift5 = 13;
                while (true) {
                    i6 = i20 + 1;
                    next6 = info2.charAt(i20);
                    if (next6 < 55296) {
                        break;
                    }
                    result7 |= (next6 & 8191) << shift5;
                    shift5 += 13;
                    i20 = i6;
                }
                next21 = result7 | (next6 << shift5);
                i20 = i6;
            }
            int result8 = next21;
            int i21 = i20 + 1;
            int next22 = info2.charAt(i20);
            if (next22 >= 55296) {
                int result9 = next22 & 8191;
                int shift6 = 13;
                while (true) {
                    i5 = i21 + 1;
                    next5 = info2.charAt(i21);
                    if (next5 < 55296) {
                        break;
                    }
                    result9 |= (next5 & 8191) << shift6;
                    shift6 += 13;
                    i21 = i5;
                }
                next22 = result9 | (next5 << shift6);
                i21 = i5;
            }
            int result10 = next22;
            int i22 = i21 + 1;
            int next23 = info2.charAt(i21);
            if (next23 >= 55296) {
                int result11 = next23 & 8191;
                int shift7 = 13;
                while (true) {
                    i4 = i22 + 1;
                    next4 = info2.charAt(i22);
                    if (next4 < 55296) {
                        break;
                    }
                    result11 |= (next4 & 8191) << shift7;
                    shift7 += 13;
                    i22 = i4;
                }
                next23 = result11 | (next4 << shift7);
                i22 = i4;
            }
            int result12 = next23;
            int i23 = i22 + 1;
            int next24 = info2.charAt(i22);
            if (next24 >= 55296) {
                int result13 = next24 & 8191;
                int shift8 = 13;
                while (true) {
                    i3 = i23 + 1;
                    next3 = info2.charAt(i23);
                    if (next3 < 55296) {
                        break;
                    }
                    result13 |= (next3 & 8191) << shift8;
                    shift8 += 13;
                    i23 = i3;
                }
                next24 = result13 | (next3 << shift8);
                i23 = i3;
            }
            int result14 = next24;
            int i24 = i23 + 1;
            int next25 = info2.charAt(i23);
            if (next25 >= 55296) {
                int result15 = next25 & 8191;
                int shift9 = 13;
                while (true) {
                    i2 = i24 + 1;
                    next2 = info2.charAt(i24);
                    if (next2 < 55296) {
                        break;
                    }
                    result15 |= (next2 & 8191) << shift9;
                    shift9 += 13;
                    i24 = i2;
                }
                next25 = result15 | (next2 << shift9);
                i24 = i2;
            }
            int result16 = next25;
            int i25 = i24 + 1;
            int next26 = info2.charAt(i24);
            if (next26 >= 55296) {
                int result17 = next26 & 8191;
                int shift10 = 13;
                while (true) {
                    i = i25 + 1;
                    next = info2.charAt(i25);
                    if (next < 55296) {
                        break;
                    }
                    result17 |= (next & 8191) << shift10;
                    shift10 += 13;
                    i25 = i;
                }
                next26 = result17 | (next << shift10);
                i25 = i;
            }
            int result18 = next26;
            int[] intArray3 = new int[result18 + result14 + result16];
            objectsPosition = (result4 * 2) + result6;
            minFieldNumber = result8;
            maxFieldNumber = result10;
            numEntries = result12;
            mapFieldCount = result14;
            checkInitialized = result18;
            intArray = intArray3;
            hasBitsCount = result4;
            i17 = i25;
        }
        Unsafe unsafe = UNSAFE;
        Object[] messageInfoObjects = messageInfo.getObjects();
        Class<?> messageClass = messageInfo.getDefaultInstance().getClass();
        int[] buffer = new int[numEntries * 3];
        Object[] objects = new Object[numEntries * 2];
        int mapFieldIndex = checkInitialized;
        int repeatedFieldIndex = checkInitialized + mapFieldCount;
        int checkInitializedPosition = 0;
        int mapFieldIndex2 = mapFieldIndex;
        int repeatedFieldIndex2 = repeatedFieldIndex;
        int objectsPosition3 = objectsPosition;
        int bufferIndex = 0;
        while (i17 < length2) {
            int i26 = i17 + 1;
            int next27 = info2.charAt(i17);
            if (next27 >= i16) {
                int result19 = next27 & 8191;
                int shift11 = 13;
                while (true) {
                    i12 = i26 + 1;
                    next14 = info2.charAt(i26);
                    if (next14 < i16) {
                        break;
                    }
                    result19 |= (next14 & 8191) << shift11;
                    shift11 += 13;
                    i26 = i12;
                }
                next27 = result19 | (next14 << shift11);
                i26 = i12;
            }
            int result20 = next27;
            int i27 = i26 + 1;
            int next28 = info2.charAt(i26);
            if (next28 >= i16) {
                int result21 = next28 & 8191;
                int shift12 = 13;
                while (true) {
                    i11 = i27 + 1;
                    next13 = info2.charAt(i27);
                    if (next13 < i16) {
                        break;
                    }
                    result21 |= (next13 & 8191) << shift12;
                    shift12 += 13;
                    i27 = i11;
                }
                next28 = result21 | (next13 << shift12);
                i27 = i11;
            }
            int result22 = next28;
            int fieldType = result22 & 255;
            if ((result22 & 1024) != 0) {
                intArray[checkInitializedPosition] = bufferIndex;
                checkInitializedPosition++;
            }
            int i28 = 0;
            if (fieldType >= 51) {
                int i29 = i27 + 1;
                int next29 = info2.charAt(i27);
                if (next29 < 55296) {
                    length = length2;
                } else {
                    int result23 = next29 & 8191;
                    int shift13 = 13;
                    while (true) {
                        i10 = i29 + 1;
                        next12 = info2.charAt(i29);
                        length = length2;
                        if (next12 < 55296) {
                            break;
                        }
                        result23 |= (next12 & 8191) << shift13;
                        shift13 += 13;
                        i29 = i10;
                        length2 = length;
                    }
                    next29 = result23 | (next12 << shift13);
                    i29 = i10;
                }
                int length3 = next29;
                int oneofFieldType = fieldType - 51;
                int next30 = next29;
                if (oneofFieldType == 9) {
                    fieldCount = fieldCount2;
                } else if (oneofFieldType == 17) {
                    fieldCount = fieldCount2;
                } else {
                    if (oneofFieldType != 12) {
                        fieldCount = fieldCount2;
                    } else {
                        fieldCount = fieldCount2;
                        if (messageInfo.getSyntax().equals(ProtoSyntax.PROTO2) || (result22 & 2048) != 0) {
                            objects[((bufferIndex / 3) * 2) + 1] = messageInfoObjects[objectsPosition3];
                            objectsPosition3++;
                        }
                    }
                    int index = length3 * 2;
                    o = messageInfoObjects[index];
                    if (!(o instanceof java.lang.reflect.Field)) {
                        oneofField = (java.lang.reflect.Field) o;
                    } else {
                        oneofField = reflectField(messageClass, (String) o);
                        messageInfoObjects[index] = oneofField;
                    }
                    int i30 = i29;
                    int fieldOffset2 = (int) unsafe.objectFieldOffset(oneofField);
                    int index2 = index + 1;
                    o2 = messageInfoObjects[index2];
                    if (!(o2 instanceof java.lang.reflect.Field)) {
                        oneofCaseField = (java.lang.reflect.Field) o2;
                    } else {
                        oneofCaseField = reflectField(messageClass, (String) o2);
                        messageInfoObjects[index2] = oneofCaseField;
                    }
                    presenceFieldOffset = (int) unsafe.objectFieldOffset(oneofCaseField);
                    presenceMaskShift = 0;
                    info = info2;
                    unusedFlags = unusedFlags2;
                    i27 = i30;
                    next9 = next30;
                    fieldOffset = fieldOffset2;
                }
                objects[((bufferIndex / 3) * 2) + 1] = messageInfoObjects[objectsPosition3];
                objectsPosition3++;
                int index3 = length3 * 2;
                o = messageInfoObjects[index3];
                if (!(o instanceof java.lang.reflect.Field)) {
                }
                int i302 = i29;
                int fieldOffset22 = (int) unsafe.objectFieldOffset(oneofField);
                int index22 = index3 + 1;
                o2 = messageInfoObjects[index22];
                if (!(o2 instanceof java.lang.reflect.Field)) {
                }
                presenceFieldOffset = (int) unsafe.objectFieldOffset(oneofCaseField);
                presenceMaskShift = 0;
                info = info2;
                unusedFlags = unusedFlags2;
                i27 = i302;
                next9 = next30;
                fieldOffset = fieldOffset22;
            } else {
                length = length2;
                fieldCount = fieldCount2;
                int objectsPosition4 = objectsPosition3 + 1;
                java.lang.reflect.Field field = reflectField(messageClass, (String) messageInfoObjects[objectsPosition3]);
                if (fieldType == 9) {
                    next9 = next28;
                } else if (fieldType == 17) {
                    next9 = next28;
                } else {
                    if (fieldType == 27) {
                        next9 = next28;
                    } else if (fieldType == 49) {
                        next9 = next28;
                    } else {
                        if (fieldType == 12 || fieldType == 30 || fieldType == 44) {
                            next9 = next28;
                            if (messageInfo.getSyntax() == ProtoSyntax.PROTO2 || (result22 & 2048) != 0) {
                                objects[((bufferIndex / 3) * 2) + 1] = messageInfoObjects[objectsPosition4];
                                objectsPosition4++;
                            }
                        } else if (fieldType != 50) {
                            next9 = next28;
                        } else {
                            int mapFieldIndex3 = mapFieldIndex2 + 1;
                            intArray[mapFieldIndex2] = bufferIndex;
                            int objectsPosition5 = objectsPosition4 + 1;
                            objects[(bufferIndex / 3) * 2] = messageInfoObjects[objectsPosition4];
                            if ((result22 & 2048) == 0) {
                                next9 = next28;
                                mapFieldIndex2 = mapFieldIndex3;
                                objectsPosition4 = objectsPosition5;
                            } else {
                                objects[((bufferIndex / 3) * 2) + 1] = messageInfoObjects[objectsPosition5];
                                mapFieldIndex2 = mapFieldIndex3;
                                objectsPosition4 = objectsPosition5 + 1;
                                next9 = next28;
                            }
                        }
                        int objectsPosition6 = objectsPosition4;
                        int fieldOffset3 = (int) unsafe.objectFieldOffset(field);
                        hasHasBit = (result22 & 4096) != 0;
                        if (!hasHasBit) {
                            info = info2;
                            objectsPosition2 = objectsPosition6;
                            unusedFlags = unusedFlags2;
                        } else if (fieldType > 17) {
                            info = info2;
                            objectsPosition2 = objectsPosition6;
                            unusedFlags = unusedFlags2;
                        } else {
                            int i31 = i27 + 1;
                            int next31 = info2.charAt(i27);
                            if (next31 < 55296) {
                                info = info2;
                                next10 = next31;
                                i27 = i31;
                            } else {
                                int next32 = next31 & 8191;
                                int next33 = i31;
                                int i32 = 13;
                                while (true) {
                                    i9 = next33 + 1;
                                    next11 = info2.charAt(next33);
                                    info = info2;
                                    if (next11 < 55296) {
                                        break;
                                    }
                                    next32 |= (next11 & 8191) << i32;
                                    i32 += 13;
                                    next33 = i9;
                                    info2 = info;
                                }
                                next10 = (next11 << i32) | next32;
                                i27 = i9;
                            }
                            int hasBitsIndex2 = next10;
                            int index4 = (hasBitsCount * 2) + (hasBitsIndex2 / 32);
                            int next34 = next10;
                            Object o3 = messageInfoObjects[index4];
                            if (o3 instanceof java.lang.reflect.Field) {
                                hasBitsField = (java.lang.reflect.Field) o3;
                            } else {
                                hasBitsField = reflectField(messageClass, (String) o3);
                                messageInfoObjects[index4] = hasBitsField;
                            }
                            objectsPosition2 = objectsPosition6;
                            unusedFlags = unusedFlags2;
                            int presenceFieldOffset2 = (int) unsafe.objectFieldOffset(hasBitsField);
                            hasBitsIndex = hasBitsIndex2 % 32;
                            presenceFieldOffset = presenceFieldOffset2;
                            next9 = next34;
                            if (fieldType < 18 && fieldType <= 49) {
                                intArray[repeatedFieldIndex2] = fieldOffset3;
                                repeatedFieldIndex2++;
                                objectsPosition3 = objectsPosition2;
                                fieldOffset = fieldOffset3;
                                presenceMaskShift = hasBitsIndex;
                            } else {
                                fieldOffset = fieldOffset3;
                                objectsPosition3 = objectsPosition2;
                                presenceMaskShift = hasBitsIndex;
                            }
                        }
                        presenceFieldOffset = 1048575;
                        hasBitsIndex = 0;
                        if (fieldType < 18) {
                        }
                        fieldOffset = fieldOffset3;
                        objectsPosition3 = objectsPosition2;
                        presenceMaskShift = hasBitsIndex;
                    }
                    objects[((bufferIndex / 3) * 2) + 1] = messageInfoObjects[objectsPosition4];
                    objectsPosition4++;
                    int objectsPosition62 = objectsPosition4;
                    int fieldOffset32 = (int) unsafe.objectFieldOffset(field);
                    hasHasBit = (result22 & 4096) != 0;
                    if (!hasHasBit) {
                    }
                    presenceFieldOffset = 1048575;
                    hasBitsIndex = 0;
                    if (fieldType < 18) {
                    }
                    fieldOffset = fieldOffset32;
                    objectsPosition3 = objectsPosition2;
                    presenceMaskShift = hasBitsIndex;
                }
                objects[((bufferIndex / 3) * 2) + 1] = field.getType();
                int objectsPosition622 = objectsPosition4;
                int fieldOffset322 = (int) unsafe.objectFieldOffset(field);
                hasHasBit = (result22 & 4096) != 0;
                if (!hasHasBit) {
                }
                presenceFieldOffset = 1048575;
                hasBitsIndex = 0;
                if (fieldType < 18) {
                }
                fieldOffset = fieldOffset322;
                objectsPosition3 = objectsPosition2;
                presenceMaskShift = hasBitsIndex;
            }
            int presenceMaskShift2 = bufferIndex + 1;
            buffer[bufferIndex] = result20;
            int bufferIndex2 = presenceMaskShift2 + 1;
            int fieldNumber = result22 & 256;
            int i33 = ((result22 & 512) != 0 ? ENFORCE_UTF8_MASK : 0) | (fieldNumber != 0 ? REQUIRED_MASK : 0);
            if ((result22 & 2048) != 0) {
                i28 = Integer.MIN_VALUE;
            }
            buffer[presenceMaskShift2] = i33 | i28 | (fieldType << 20) | fieldOffset;
            bufferIndex = bufferIndex2 + 1;
            buffer[bufferIndex2] = (presenceMaskShift << 20) | presenceFieldOffset;
            i17 = i27;
            length2 = length;
            fieldCount2 = fieldCount;
            info2 = info;
            unusedFlags2 = unusedFlags;
            i16 = 55296;
        }
        return new MessageSchema<>(buffer, objects, minFieldNumber, maxFieldNumber, messageInfo.getDefaultInstance(), messageInfo.getSyntax(), false, intArray, checkInitialized, checkInitialized + mapFieldCount, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    private static java.lang.reflect.Field reflectField(Class<?> messageClass, String fieldName) {
        try {
            return messageClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            java.lang.reflect.Field[] fields = messageClass.getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                if (fieldName.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + fieldName + " for " + messageClass.getName() + " not found. Known fields are " + Arrays.toString(fields));
        }
    }

    /* JADX WARN: Incorrect condition in loop: B:31:0x007b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static <T> MessageSchema<T> newSchemaForMessageInfo(StructuralMessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int minFieldNumber;
        int maxFieldNumber;
        int[] checkInitialized;
        int[] mapFieldPositions;
        int[] repeatedFieldOffsets;
        int fieldIndex;
        FieldInfo[] fis = messageInfo.getFields();
        if (fis.length == 0) {
            minFieldNumber = 0;
            maxFieldNumber = 0;
        } else {
            minFieldNumber = fis[0].getFieldNumber();
            maxFieldNumber = fis[fis.length - 1].getFieldNumber();
        }
        int numEntries = fis.length;
        int[] buffer = new int[numEntries * 3];
        Object[] objects = new Object[numEntries * 2];
        int mapFieldCount = 0;
        int repeatedFieldCount = 0;
        for (FieldInfo fi : fis) {
            if (fi.getType() == FieldType.MAP) {
                mapFieldCount++;
            } else if (fi.getType().id() >= 18 && fi.getType().id() <= 49) {
                repeatedFieldCount++;
            }
        }
        int[] mapFieldPositions2 = mapFieldCount > 0 ? new int[mapFieldCount] : null;
        int[] repeatedFieldOffsets2 = repeatedFieldCount > 0 ? new int[repeatedFieldCount] : null;
        int[] checkInitialized2 = messageInfo.getCheckInitialized();
        if (checkInitialized2 != null) {
            checkInitialized = checkInitialized2;
        } else {
            checkInitialized = EMPTY_INT_ARRAY;
        }
        int mapFieldCount2 = 0;
        int repeatedFieldCount2 = 0;
        int mapFieldCount3 = 0;
        int checkInitializedIndex = 0;
        int checkInitializedIndex2 = 0;
        while (checkInitializedIndex2 < repeatedFieldCount) {
            FieldInfo fi2 = fis[checkInitializedIndex2];
            int fieldNumber = fi2.getFieldNumber();
            storeFieldData(fi2, buffer, mapFieldCount3, objects);
            if (checkInitializedIndex < checkInitialized.length && checkInitialized[checkInitializedIndex] == fieldNumber) {
                checkInitialized[checkInitializedIndex] = mapFieldCount3;
                checkInitializedIndex++;
            }
            if (fi2.getType() == FieldType.MAP) {
                mapFieldPositions2[mapFieldCount2] = mapFieldCount3;
                mapFieldCount2++;
                fieldIndex = checkInitializedIndex2;
            } else if (fi2.getType().id() < 18 || fi2.getType().id() > 49) {
                fieldIndex = checkInitializedIndex2;
            } else {
                fieldIndex = checkInitializedIndex2;
                repeatedFieldOffsets2[repeatedFieldCount2] = (int) UnsafeUtil.objectFieldOffset(fi2.getField());
                repeatedFieldCount2++;
            }
            checkInitializedIndex2 = fieldIndex + 1;
            mapFieldCount3 += 3;
        }
        if (mapFieldPositions2 != null) {
            mapFieldPositions = mapFieldPositions2;
        } else {
            mapFieldPositions = EMPTY_INT_ARRAY;
        }
        if (repeatedFieldOffsets2 != null) {
            repeatedFieldOffsets = repeatedFieldOffsets2;
        } else {
            repeatedFieldOffsets = EMPTY_INT_ARRAY;
        }
        int[] combined = new int[checkInitialized.length + mapFieldPositions.length + repeatedFieldOffsets.length];
        System.arraycopy(checkInitialized, 0, combined, 0, checkInitialized.length);
        System.arraycopy(mapFieldPositions, 0, combined, checkInitialized.length, mapFieldPositions.length);
        System.arraycopy(repeatedFieldOffsets, 0, combined, checkInitialized.length + mapFieldPositions.length, repeatedFieldOffsets.length);
        return new MessageSchema<>(buffer, objects, minFieldNumber, maxFieldNumber, messageInfo.getDefaultInstance(), messageInfo.getSyntax(), true, combined, checkInitialized.length, checkInitialized.length + mapFieldPositions.length, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    private static void storeFieldData(FieldInfo fi, int[] buffer, int bufferIndex, Object[] objects) {
        int fieldOffset;
        int typeId;
        int typeId2;
        int presenceFieldOffset;
        int presenceFieldOffset2;
        OneofInfo oneof = fi.getOneof();
        if (oneof != null) {
            typeId = fi.getType().id() + 51;
            fieldOffset = (int) UnsafeUtil.objectFieldOffset(oneof.getValueField());
            typeId2 = (int) UnsafeUtil.objectFieldOffset(oneof.getCaseField());
            presenceFieldOffset = 0;
        } else {
            FieldType type = fi.getType();
            fieldOffset = (int) UnsafeUtil.objectFieldOffset(fi.getField());
            int typeId3 = type.id();
            if (!type.isList() && !type.isMap()) {
                java.lang.reflect.Field presenceField = fi.getPresenceField();
                if (presenceField == null) {
                    presenceFieldOffset2 = 1048575;
                } else {
                    presenceFieldOffset2 = (int) UnsafeUtil.objectFieldOffset(presenceField);
                }
                presenceFieldOffset = Integer.numberOfTrailingZeros(fi.getPresenceMask());
                typeId = typeId3;
                typeId2 = presenceFieldOffset2;
            } else if (fi.getCachedSizeField() == null) {
                typeId = typeId3;
                typeId2 = 0;
                presenceFieldOffset = 0;
            } else {
                int presenceFieldOffset3 = (int) UnsafeUtil.objectFieldOffset(fi.getCachedSizeField());
                typeId = typeId3;
                typeId2 = presenceFieldOffset3;
                presenceFieldOffset = 0;
            }
        }
        buffer[bufferIndex] = fi.getFieldNumber();
        buffer[bufferIndex + 1] = (fi.isEnforceUtf8() ? ENFORCE_UTF8_MASK : 0) | (fi.isRequired() ? REQUIRED_MASK : 0) | (typeId << 20) | fieldOffset;
        buffer[bufferIndex + 2] = (presenceFieldOffset << 20) | typeId2;
        Object messageFieldClass = fi.getMessageFieldClass();
        if (fi.getMapDefaultEntry() != null) {
            objects[(bufferIndex / 3) * 2] = fi.getMapDefaultEntry();
            if (messageFieldClass != null) {
                objects[((bufferIndex / 3) * 2) + 1] = messageFieldClass;
                return;
            } else {
                if (fi.getEnumVerifier() != null) {
                    objects[((bufferIndex / 3) * 2) + 1] = fi.getEnumVerifier();
                    return;
                }
                return;
            }
        }
        if (messageFieldClass != null) {
            objects[((bufferIndex / 3) * 2) + 1] = messageFieldClass;
        } else if (fi.getEnumVerifier() != null) {
            objects[((bufferIndex / 3) * 2) + 1] = fi.getEnumVerifier();
        }
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public T newInstance() {
        return (T) this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public boolean equals(T message, T other) {
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            if (!equals(message, other, pos)) {
                return false;
            }
        }
        Object messageUnknown = this.unknownFieldSchema.getFromMessage(message);
        Object otherUnknown = this.unknownFieldSchema.getFromMessage(other);
        if (!messageUnknown.equals(otherUnknown)) {
            return false;
        }
        if (this.hasExtensions) {
            FieldSet<?> messageExtensions = this.extensionSchema.getExtensions(message);
            FieldSet<?> otherExtensions = this.extensionSchema.getExtensions(other);
            return messageExtensions.equals(otherExtensions);
        }
        return true;
    }

    private boolean equals(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffset);
        switch (type(typeAndOffset)) {
            case 0:
                if (arePresentForEquals(message, other, pos) && Double.doubleToLongBits(UnsafeUtil.getDouble(message, offset)) == Double.doubleToLongBits(UnsafeUtil.getDouble(other, offset))) {
                    break;
                }
                break;
            case 1:
                if (arePresentForEquals(message, other, pos) && Float.floatToIntBits(UnsafeUtil.getFloat(message, offset)) == Float.floatToIntBits(UnsafeUtil.getFloat(other, offset))) {
                    break;
                }
                break;
            case 2:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 3:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 4:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 5:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 6:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 7:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getBoolean(message, offset) == UnsafeUtil.getBoolean(other, offset)) {
                    break;
                }
                break;
            case 8:
                if (arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
            case 9:
                if (arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
            case 10:
                if (arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
            case 11:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 12:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 13:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 14:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 15:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 16:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 17:
                if (arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
            case 51:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
            case 56:
            case 57:
            case 58:
            case 59:
            case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
            case 62:
            case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
            case 64:
            case 65:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
            case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
            case 68:
                if (isOneofCaseEqual(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
        }
        return true;
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public int hashCode(T message) {
        int hashCode = 0;
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            int typeAndOffset = typeAndOffsetAt(pos);
            int entryNumber = numberAt(pos);
            long offset = offset(typeAndOffset);
            switch (type(typeAndOffset)) {
                case 0:
                    hashCode = (hashCode * 53) + Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble(message, offset)));
                    break;
                case 1:
                    hashCode = (hashCode * 53) + Float.floatToIntBits(UnsafeUtil.getFloat(message, offset));
                    break;
                case 2:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 3:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 4:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 5:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 6:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 7:
                    hashCode = (hashCode * 53) + Internal.hashBoolean(UnsafeUtil.getBoolean(message, offset));
                    break;
                case 8:
                    int protoHash = hashCode * 53;
                    int hashCode2 = protoHash + ((String) UnsafeUtil.getObject(message, offset)).hashCode();
                    hashCode = hashCode2;
                    break;
                case 9:
                    int protoHash2 = 37;
                    Object submessage = UnsafeUtil.getObject(message, offset);
                    if (submessage != null) {
                        protoHash2 = submessage.hashCode();
                    }
                    hashCode = (hashCode * 53) + protoHash2;
                    break;
                case 10:
                    hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    break;
                case 11:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 12:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 13:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 14:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 15:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 16:
                    int protoHash3 = hashCode * 53;
                    int hashCode3 = protoHash3 + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    hashCode = hashCode3;
                    break;
                case 17:
                    int protoHash4 = 37;
                    Object submessage2 = UnsafeUtil.getObject(message, offset);
                    if (submessage2 != null) {
                        protoHash4 = submessage2.hashCode();
                    }
                    hashCode = (hashCode * 53) + protoHash4;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case 49:
                    hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    break;
                case 50:
                    hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    break;
                case 51:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(message, offset)));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Float.floatToIntBits(oneofFloatAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashBoolean(oneofBooleanAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + ((String) UnsafeUtil.getObject(message, offset)).hashCode();
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    } else {
                        break;
                    }
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                        break;
                    } else {
                        break;
                    }
            }
        }
        int pos2 = hashCode * 53;
        int hashCode4 = pos2 + this.unknownFieldSchema.getFromMessage(message).hashCode();
        if (this.hasExtensions) {
            return (hashCode4 * 53) + this.extensionSchema.getExtensions(message).hashCode();
        }
        return hashCode4;
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void mergeFrom(T message, T other) {
        checkMutable(message);
        if (other == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.buffer.length; i += 3) {
            mergeSingleField(message, other, i);
        }
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, message, other);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, message, other);
        }
    }

    private void mergeSingleField(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffset);
        int number = numberAt(pos);
        switch (type(typeAndOffset)) {
            case 0:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putDouble(message, offset, UnsafeUtil.getDouble(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 1:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putFloat(message, offset, UnsafeUtil.getFloat(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 2:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 3:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 4:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 5:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 6:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 7:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putBoolean(message, offset, UnsafeUtil.getBoolean(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 8:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 9:
                mergeMessage(message, other, pos);
                break;
            case 10:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 11:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 12:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 13:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 14:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 15:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 16:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 17:
                mergeMessage(message, other, pos);
                break;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
            case 49:
                this.listFieldSchema.mergeListsAt(message, other, offset);
                break;
            case 50:
                SchemaUtil.mergeMap(this.mapFieldSchema, message, other, offset);
                break;
            case 51:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
            case 56:
            case 57:
            case 58:
            case 59:
                if (isOneofPresent(other, number, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setOneofPresent(message, number, pos);
                    break;
                }
                break;
            case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                mergeOneofMessage(message, other, pos);
                break;
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
            case 62:
            case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
            case 64:
            case 65:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
            case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                if (isOneofPresent(other, number, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setOneofPresent(message, number, pos);
                    break;
                }
                break;
            case 68:
                mergeOneofMessage(message, other, pos);
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeMessage(T targetParent, T sourceParent, int pos) {
        if (!isFieldPresent(sourceParent, pos)) {
            return;
        }
        int typeAndOffset = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffset);
        Object source = UNSAFE.getObject(sourceParent, offset);
        if (source == null) {
            throw new IllegalStateException("Source subfield " + numberAt(pos) + " is present but null: " + sourceParent);
        }
        Schema messageFieldSchema = getMessageFieldSchema(pos);
        if (!isFieldPresent(targetParent, pos)) {
            if (!isMutable(source)) {
                UNSAFE.putObject(targetParent, offset, source);
            } else {
                Object copyOfSource = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(copyOfSource, source);
                UNSAFE.putObject(targetParent, offset, copyOfSource);
            }
            setFieldPresent(targetParent, pos);
            return;
        }
        Object target = UNSAFE.getObject(targetParent, offset);
        if (!isMutable(target)) {
            Object newInstance = messageFieldSchema.newInstance();
            messageFieldSchema.mergeFrom(newInstance, target);
            UNSAFE.putObject(targetParent, offset, newInstance);
            target = newInstance;
        }
        messageFieldSchema.mergeFrom(target, source);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeOneofMessage(T targetParent, T sourceParent, int pos) {
        int number = numberAt(pos);
        if (!isOneofPresent(sourceParent, number, pos)) {
            return;
        }
        long offset = offset(typeAndOffsetAt(pos));
        Object source = UNSAFE.getObject(sourceParent, offset);
        if (source == null) {
            throw new IllegalStateException("Source subfield " + numberAt(pos) + " is present but null: " + sourceParent);
        }
        Schema messageFieldSchema = getMessageFieldSchema(pos);
        if (!isOneofPresent(targetParent, number, pos)) {
            if (!isMutable(source)) {
                UNSAFE.putObject(targetParent, offset, source);
            } else {
                Object copyOfSource = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(copyOfSource, source);
                UNSAFE.putObject(targetParent, offset, copyOfSource);
            }
            setOneofPresent(targetParent, number, pos);
            return;
        }
        Object target = UNSAFE.getObject(targetParent, offset);
        if (!isMutable(target)) {
            Object newInstance = messageFieldSchema.newInstance();
            messageFieldSchema.mergeFrom(newInstance, target);
            UNSAFE.putObject(targetParent, offset, newInstance);
            target = newInstance;
        }
        messageFieldSchema.mergeFrom(target, source);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public int getSerializedSize(T message) {
        int presenceMask;
        int currentPresenceFieldOffset;
        int currentPresenceField;
        int i;
        int i2;
        Unsafe unsafe = UNSAFE;
        int currentPresenceFieldOffset2 = 1048575;
        int currentPresenceField2 = 0;
        int size = 0;
        int i3 = 0;
        while (i3 < this.buffer.length) {
            int typeAndOffset = typeAndOffsetAt(i3);
            int fieldType = type(typeAndOffset);
            int number = numberAt(i3);
            int presenceMaskAndOffset = this.buffer[i3 + 2];
            int presenceOrCachedSizeFieldOffset = presenceMaskAndOffset & 1048575;
            if (fieldType > 17) {
                presenceMask = 0;
                currentPresenceFieldOffset = currentPresenceFieldOffset2;
                currentPresenceField = currentPresenceField2;
            } else {
                if (presenceOrCachedSizeFieldOffset != currentPresenceFieldOffset2) {
                    currentPresenceFieldOffset2 = presenceOrCachedSizeFieldOffset;
                    if (currentPresenceFieldOffset2 == 1048575) {
                        i2 = 0;
                    } else {
                        i2 = unsafe.getInt(message, currentPresenceFieldOffset2);
                    }
                    currentPresenceField2 = i2;
                }
                int presenceMask2 = 1 << (presenceMaskAndOffset >>> 20);
                presenceMask = presenceMask2;
                currentPresenceFieldOffset = currentPresenceFieldOffset2;
                currentPresenceField = currentPresenceField2;
            }
            long offset = offset(typeAndOffset);
            if (fieldType >= FieldType.DOUBLE_LIST_PACKED.id() && fieldType <= FieldType.SINT64_LIST_PACKED.id()) {
                i = presenceOrCachedSizeFieldOffset;
            } else {
                i = 0;
            }
            int cachedSizeOffset = i;
            switch (fieldType) {
                case 0:
                    if (!isFieldPresent(message, i3, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeDoubleSize(number, Utils.DOUBLE_EPSILON);
                        break;
                    }
                case 1:
                    if (!isFieldPresent(message, i3, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeFloatSize(number, 0.0f);
                        break;
                    }
                case 2:
                    if (!isFieldPresent(message, i3, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeInt64Size(number, unsafe.getLong(message, offset));
                        break;
                    }
                case 3:
                    if (!isFieldPresent(message, i3, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeUInt64Size(number, unsafe.getLong(message, offset));
                        break;
                    }
                case 4:
                    if (!isFieldPresent(message, i3, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeInt32Size(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 5:
                    if (!isFieldPresent(message, i3, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeFixed64Size(number, 0L);
                        break;
                    }
                case 6:
                    if (!isFieldPresent(message, i3, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeFixed32Size(number, 0);
                        break;
                    }
                case 7:
                    if (!isFieldPresent(message, i3, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeBoolSize(number, true);
                        break;
                    }
                case 8:
                    if (!isFieldPresent(message, i3, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                        break;
                    } else {
                        Object value = unsafe.getObject(message, offset);
                        if (value instanceof ByteString) {
                            size += CodedOutputStream.computeBytesSize(number, (ByteString) value);
                            break;
                        } else {
                            size += CodedOutputStream.computeStringSize(number, (String) value);
                            break;
                        }
                    }
                case 9:
                    if (!isFieldPresent(message, i3, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                        break;
                    } else {
                        size += SchemaUtil.computeSizeMessage(number, unsafe.getObject(message, offset), getMessageFieldSchema(i3));
                        break;
                    }
                case 10:
                    if (!isFieldPresent(message, i3, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeBytesSize(number, (ByteString) unsafe.getObject(message, offset));
                        break;
                    }
                case 11:
                    if (!isFieldPresent(message, i3, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeUInt32Size(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 12:
                    if (!isFieldPresent(message, i3, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeEnumSize(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 13:
                    if (!isFieldPresent(message, i3, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSFixed32Size(number, 0);
                        break;
                    }
                case 14:
                    if (!isFieldPresent(message, i3, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSFixed64Size(number, 0L);
                        break;
                    }
                case 15:
                    if (!isFieldPresent(message, i3, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSInt32Size(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 16:
                    if (!isFieldPresent(message, i3, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSInt64Size(number, unsafe.getLong(message, offset));
                        break;
                    }
                case 17:
                    if (!isFieldPresent(message, i3, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeGroupSize(number, (MessageLite) unsafe.getObject(message, offset), getMessageFieldSchema(i3));
                        break;
                    }
                case 18:
                    size += SchemaUtil.computeSizeFixed64List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 19:
                    size += SchemaUtil.computeSizeFixed32List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 20:
                    size += SchemaUtil.computeSizeInt64List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 21:
                    size += SchemaUtil.computeSizeUInt64List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 22:
                    size += SchemaUtil.computeSizeInt32List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 23:
                    size += SchemaUtil.computeSizeFixed64List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 24:
                    size += SchemaUtil.computeSizeFixed32List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 25:
                    size += SchemaUtil.computeSizeBoolList(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 26:
                    size += SchemaUtil.computeSizeStringList(number, (List) unsafe.getObject(message, offset));
                    break;
                case 27:
                    size += SchemaUtil.computeSizeMessageList(number, (List) unsafe.getObject(message, offset), getMessageFieldSchema(i3));
                    break;
                case 28:
                    size += SchemaUtil.computeSizeByteStringList(number, (List) unsafe.getObject(message, offset));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    size += SchemaUtil.computeSizeUInt32List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 30:
                    size += SchemaUtil.computeSizeEnumList(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 31:
                    size += SchemaUtil.computeSizeFixed32List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 32:
                    size += SchemaUtil.computeSizeFixed64List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 33:
                    size += SchemaUtil.computeSizeSInt32List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 34:
                    size += SchemaUtil.computeSizeSInt64List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 35:
                    int fieldSize = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    int fieldSize2 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize2);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize2) + fieldSize2;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    int fieldSize3 = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize3 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize3);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize3) + fieldSize3;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    int fieldSize4 = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize4 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize4);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize4) + fieldSize4;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    int fieldSize5 = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize5 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize5);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize5) + fieldSize5;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    int fieldSize6 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize6 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize6);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize6) + fieldSize6;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    int fieldSize7 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize7 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize7);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize7) + fieldSize7;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    int fieldSize8 = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize8 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize8);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize8) + fieldSize8;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    int fieldSize9 = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize9 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize9);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize9) + fieldSize9;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    int fieldSize10 = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize10 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize10);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize10) + fieldSize10;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    int fieldSize11 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize11 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize11);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize11) + fieldSize11;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    int fieldSize12 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize12 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize12);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize12) + fieldSize12;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    int fieldSize13 = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize13 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize13);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize13) + fieldSize13;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    int fieldSize14 = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize14 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize14);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize14) + fieldSize14;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    size += SchemaUtil.computeSizeGroupList(number, (List) unsafe.getObject(message, offset), getMessageFieldSchema(i3));
                    break;
                case 50:
                    size += this.mapFieldSchema.getSerializedSize(number, unsafe.getObject(message, offset), getMapFieldDefaultEntry(i3));
                    break;
                case 51:
                    if (!isOneofPresent(message, number, i3)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeDoubleSize(number, Utils.DOUBLE_EPSILON);
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (!isOneofPresent(message, number, i3)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeFloatSize(number, 0.0f);
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (!isOneofPresent(message, number, i3)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeInt64Size(number, oneofLongAt(message, offset));
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (!isOneofPresent(message, number, i3)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeUInt64Size(number, oneofLongAt(message, offset));
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                    if (!isOneofPresent(message, number, i3)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeInt32Size(number, oneofIntAt(message, offset));
                        break;
                    }
                case 56:
                    if (!isOneofPresent(message, number, i3)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeFixed64Size(number, 0L);
                        break;
                    }
                case 57:
                    if (!isOneofPresent(message, number, i3)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeFixed32Size(number, 0);
                        break;
                    }
                case 58:
                    if (!isOneofPresent(message, number, i3)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeBoolSize(number, true);
                        break;
                    }
                case 59:
                    if (!isOneofPresent(message, number, i3)) {
                        break;
                    } else {
                        Object value2 = unsafe.getObject(message, offset);
                        if (value2 instanceof ByteString) {
                            size += CodedOutputStream.computeBytesSize(number, (ByteString) value2);
                            break;
                        } else {
                            size += CodedOutputStream.computeStringSize(number, (String) value2);
                            break;
                        }
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (!isOneofPresent(message, number, i3)) {
                        break;
                    } else {
                        size += SchemaUtil.computeSizeMessage(number, unsafe.getObject(message, offset), getMessageFieldSchema(i3));
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (!isOneofPresent(message, number, i3)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeBytesSize(number, (ByteString) unsafe.getObject(message, offset));
                        break;
                    }
                case 62:
                    if (!isOneofPresent(message, number, i3)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeUInt32Size(number, oneofIntAt(message, offset));
                        break;
                    }
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (!isOneofPresent(message, number, i3)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeEnumSize(number, oneofIntAt(message, offset));
                        break;
                    }
                case 64:
                    if (!isOneofPresent(message, number, i3)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSFixed32Size(number, 0);
                        break;
                    }
                case 65:
                    if (!isOneofPresent(message, number, i3)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSFixed64Size(number, 0L);
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (!isOneofPresent(message, number, i3)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSInt32Size(number, oneofIntAt(message, offset));
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (!isOneofPresent(message, number, i3)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSInt64Size(number, oneofLongAt(message, offset));
                        break;
                    }
                case 68:
                    if (!isOneofPresent(message, number, i3)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeGroupSize(number, (MessageLite) unsafe.getObject(message, offset), getMessageFieldSchema(i3));
                        break;
                    }
            }
            i3 += 3;
            currentPresenceFieldOffset2 = currentPresenceFieldOffset;
            currentPresenceField2 = currentPresenceField;
        }
        int size2 = size + getUnknownFieldsSerializedSize(this.unknownFieldSchema, message);
        if (this.hasExtensions) {
            return size2 + this.extensionSchema.getExtensions(message).getSerializedSize();
        }
        return size2;
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> schema, T message) {
        UT unknowns = schema.getFromMessage(message);
        return schema.getSerializedSize(unknowns);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void writeTo(T message, Writer writer) throws IOException {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(message, writer);
        } else {
            writeFieldsInAscendingOrder(message, writer);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:304:0x080d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeFieldsInAscendingOrder(T message, Writer writer) throws IOException {
        Iterator<? extends Map.Entry<?, ?>> extensionIterator;
        int bufferLength;
        int pos;
        int currentPresenceField;
        int presenceMask;
        Map.Entry nextExtension;
        int currentPresenceFieldOffset;
        Map.Entry nextExtension2;
        Iterator<? extends Map.Entry<?, ?>> extensionIterator2;
        int bufferLength2;
        Map.Entry nextExtension3 = null;
        if (this.hasExtensions) {
            FieldSet<?> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                Iterator<? extends Map.Entry<?, ?>> extensionIterator3 = extensions.iterator();
                Map.Entry nextExtension4 = extensionIterator3.next();
                nextExtension3 = nextExtension4;
                extensionIterator = extensionIterator3;
                int currentPresenceFieldOffset2 = 1048575;
                int currentPresenceField2 = 0;
                bufferLength = this.buffer.length;
                Unsafe unsafe = UNSAFE;
                pos = 0;
                while (pos < bufferLength) {
                    int typeAndOffset = typeAndOffsetAt(pos);
                    int number = numberAt(pos);
                    int fieldType = type(typeAndOffset);
                    if (fieldType > 17) {
                        currentPresenceField = currentPresenceField2;
                        presenceMask = 0;
                        nextExtension = nextExtension3;
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    } else {
                        int presenceMaskAndOffset = this.buffer[pos + 2];
                        Map.Entry nextExtension5 = nextExtension3;
                        int presenceFieldOffset = presenceMaskAndOffset & 1048575;
                        if (presenceFieldOffset != currentPresenceFieldOffset2) {
                            currentPresenceFieldOffset2 = presenceFieldOffset;
                            currentPresenceField2 = currentPresenceFieldOffset2 == 1048575 ? 0 : unsafe.getInt(message, presenceFieldOffset);
                        }
                        int presenceMask2 = 1 << (presenceMaskAndOffset >>> 20);
                        currentPresenceField = currentPresenceField2;
                        presenceMask = presenceMask2;
                        nextExtension = nextExtension5;
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    }
                    while (nextExtension != null && this.extensionSchema.extensionNumber(nextExtension) <= number) {
                        this.extensionSchema.serializeExtension(writer, nextExtension);
                        nextExtension = extensionIterator.hasNext() ? extensionIterator.next() : null;
                    }
                    long offset = offset(typeAndOffset);
                    switch (fieldType) {
                        case 0:
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            if (!isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                                break;
                            } else {
                                writer.writeDouble(number, doubleAt(message, offset));
                                break;
                            }
                        case 1:
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            if (!isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                                break;
                            } else {
                                writer.writeFloat(number, floatAt(message, offset));
                                break;
                            }
                        case 2:
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            if (!isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                                break;
                            } else {
                                writer.writeInt64(number, unsafe.getLong(message, offset));
                                break;
                            }
                        case 3:
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            if (!isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                                break;
                            } else {
                                writer.writeUInt64(number, unsafe.getLong(message, offset));
                                break;
                            }
                        case 4:
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            if (!isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                                break;
                            } else {
                                writer.writeInt32(number, unsafe.getInt(message, offset));
                                break;
                            }
                        case 5:
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            if (!isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                                break;
                            } else {
                                writer.writeFixed64(number, unsafe.getLong(message, offset));
                                break;
                            }
                        case 6:
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            if (!isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                                break;
                            } else {
                                writer.writeFixed32(number, unsafe.getInt(message, offset));
                                break;
                            }
                        case 7:
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            if (!isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                                break;
                            } else {
                                writer.writeBool(number, booleanAt(message, offset));
                                break;
                            }
                        case 8:
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            if (!isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                                break;
                            } else {
                                writeString(number, unsafe.getObject(message, offset), writer);
                                break;
                            }
                        case 9:
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            if (!isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                                break;
                            } else {
                                Object value = unsafe.getObject(message, offset);
                                writer.writeMessage(number, value, getMessageFieldSchema(pos));
                                break;
                            }
                        case 10:
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            if (!isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                                break;
                            } else {
                                writer.writeBytes(number, (ByteString) unsafe.getObject(message, offset));
                                break;
                            }
                        case 11:
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            if (!isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                                break;
                            } else {
                                writer.writeUInt32(number, unsafe.getInt(message, offset));
                                break;
                            }
                        case 12:
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            if (!isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                                break;
                            } else {
                                writer.writeEnum(number, unsafe.getInt(message, offset));
                                break;
                            }
                        case 13:
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            if (!isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                                break;
                            } else {
                                writer.writeSFixed32(number, unsafe.getInt(message, offset));
                                break;
                            }
                        case 14:
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            if (!isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                                break;
                            } else {
                                writer.writeSFixed64(number, unsafe.getLong(message, offset));
                                break;
                            }
                        case 15:
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            if (!isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                                break;
                            } else {
                                writer.writeSInt32(number, unsafe.getInt(message, offset));
                                break;
                            }
                        case 16:
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            if (!isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                                break;
                            } else {
                                writer.writeSInt64(number, unsafe.getLong(message, offset));
                                break;
                            }
                        case 17:
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            nextExtension2 = nextExtension;
                            if (!isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                                break;
                            } else {
                                writer.writeGroup(number, unsafe.getObject(message, offset), getMessageFieldSchema(pos));
                                break;
                            }
                        case 18:
                            SchemaUtil.writeDoubleList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, false);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 19:
                            SchemaUtil.writeFloatList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, false);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 20:
                            SchemaUtil.writeInt64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, false);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 21:
                            SchemaUtil.writeUInt64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, false);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 22:
                            SchemaUtil.writeInt32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, false);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 23:
                            SchemaUtil.writeFixed64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, false);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 24:
                            SchemaUtil.writeFixed32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, false);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 25:
                            SchemaUtil.writeBoolList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, false);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 26:
                            SchemaUtil.writeStringList(numberAt(pos), (List) unsafe.getObject(message, offset), writer);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 27:
                            SchemaUtil.writeMessageList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, getMessageFieldSchema(pos));
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 28:
                            SchemaUtil.writeBytesList(numberAt(pos), (List) unsafe.getObject(message, offset), writer);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                            SchemaUtil.writeUInt32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, false);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 30:
                            SchemaUtil.writeEnumList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, false);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 31:
                            SchemaUtil.writeSFixed32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, false);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 32:
                            SchemaUtil.writeSFixed64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, false);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 33:
                            SchemaUtil.writeSInt32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, false);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 34:
                            SchemaUtil.writeSInt64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, false);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 35:
                            SchemaUtil.writeDoubleList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 36:
                            SchemaUtil.writeFloatList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 37:
                            SchemaUtil.writeInt64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 38:
                            SchemaUtil.writeUInt64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 39:
                            SchemaUtil.writeInt32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 40:
                            SchemaUtil.writeFixed64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 41:
                            SchemaUtil.writeFixed32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 42:
                            SchemaUtil.writeBoolList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 43:
                            SchemaUtil.writeUInt32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 44:
                            SchemaUtil.writeEnumList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 45:
                            SchemaUtil.writeSFixed32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 46:
                            SchemaUtil.writeSFixed64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 47:
                            SchemaUtil.writeSInt32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                            SchemaUtil.writeSInt64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 49:
                            SchemaUtil.writeGroupList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, getMessageFieldSchema(pos));
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 50:
                            writeMapHelper(writer, number, unsafe.getObject(message, offset), pos);
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                        case 51:
                            if (!isOneofPresent(message, number, pos)) {
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            } else {
                                writer.writeDouble(number, oneofDoubleAt(message, offset));
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            }
                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                            if (!isOneofPresent(message, number, pos)) {
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            } else {
                                writer.writeFloat(number, oneofFloatAt(message, offset));
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            }
                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                            if (!isOneofPresent(message, number, pos)) {
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            } else {
                                writer.writeInt64(number, oneofLongAt(message, offset));
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            }
                        case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                            if (!isOneofPresent(message, number, pos)) {
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            } else {
                                writer.writeUInt64(number, oneofLongAt(message, offset));
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            }
                        case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                            if (!isOneofPresent(message, number, pos)) {
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            } else {
                                writer.writeInt32(number, oneofIntAt(message, offset));
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            }
                        case 56:
                            if (!isOneofPresent(message, number, pos)) {
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            } else {
                                writer.writeFixed64(number, oneofLongAt(message, offset));
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            }
                        case 57:
                            if (!isOneofPresent(message, number, pos)) {
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            } else {
                                writer.writeFixed32(number, oneofIntAt(message, offset));
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            }
                        case 58:
                            if (!isOneofPresent(message, number, pos)) {
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            } else {
                                writer.writeBool(number, oneofBooleanAt(message, offset));
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            }
                        case 59:
                            if (isOneofPresent(message, number, pos)) {
                                writeString(number, unsafe.getObject(message, offset), writer);
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            } else {
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            }
                        case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                            if (!isOneofPresent(message, number, pos)) {
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            } else {
                                Object value2 = unsafe.getObject(message, offset);
                                writer.writeMessage(number, value2, getMessageFieldSchema(pos));
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            }
                        case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                            if (!isOneofPresent(message, number, pos)) {
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            } else {
                                writer.writeBytes(number, (ByteString) unsafe.getObject(message, offset));
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            }
                        case 62:
                            if (!isOneofPresent(message, number, pos)) {
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            } else {
                                writer.writeUInt32(number, oneofIntAt(message, offset));
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            }
                        case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                            if (!isOneofPresent(message, number, pos)) {
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            } else {
                                writer.writeEnum(number, oneofIntAt(message, offset));
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            }
                        case 64:
                            if (!isOneofPresent(message, number, pos)) {
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            } else {
                                writer.writeSFixed32(number, oneofIntAt(message, offset));
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            }
                        case 65:
                            if (!isOneofPresent(message, number, pos)) {
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            } else {
                                writer.writeSFixed64(number, oneofLongAt(message, offset));
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            }
                        case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                            if (!isOneofPresent(message, number, pos)) {
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            } else {
                                writer.writeSInt32(number, oneofIntAt(message, offset));
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            }
                        case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                            if (!isOneofPresent(message, number, pos)) {
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            } else {
                                writer.writeSInt64(number, oneofLongAt(message, offset));
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            }
                        case 68:
                            if (!isOneofPresent(message, number, pos)) {
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            } else {
                                writer.writeGroup(number, unsafe.getObject(message, offset), getMessageFieldSchema(pos));
                                nextExtension2 = nextExtension;
                                extensionIterator2 = extensionIterator;
                                bufferLength2 = bufferLength;
                                break;
                            }
                        default:
                            nextExtension2 = nextExtension;
                            extensionIterator2 = extensionIterator;
                            bufferLength2 = bufferLength;
                            break;
                    }
                    pos += 3;
                    nextExtension3 = nextExtension2;
                    extensionIterator = extensionIterator2;
                    bufferLength = bufferLength2;
                    currentPresenceFieldOffset2 = currentPresenceFieldOffset;
                    currentPresenceField2 = currentPresenceField;
                }
                Iterator<? extends Map.Entry<?, ?>> extensionIterator4 = extensionIterator;
                while (nextExtension3 != null) {
                    this.extensionSchema.serializeExtension(writer, nextExtension3);
                    nextExtension3 = extensionIterator4.hasNext() ? extensionIterator4.next() : null;
                }
                writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
            }
        }
        extensionIterator = null;
        int currentPresenceFieldOffset22 = 1048575;
        int currentPresenceField22 = 0;
        bufferLength = this.buffer.length;
        Unsafe unsafe2 = UNSAFE;
        pos = 0;
        while (pos < bufferLength) {
        }
        Iterator<? extends Map.Entry<?, ?>> extensionIterator42 = extensionIterator;
        while (nextExtension3 != null) {
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
    }

    private void writeFieldsInDescendingOrder(T message, Writer writer) throws IOException {
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
        Iterator<? extends Map.Entry<?, ?>> extensionIterator = null;
        Map.Entry nextExtension = null;
        if (this.hasExtensions) {
            FieldSet<?> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                extensionIterator = extensions.descendingIterator();
                nextExtension = extensionIterator.next();
            }
        }
        int pos = this.buffer.length;
        while (true) {
            pos -= 3;
            if (pos >= 0) {
                int typeAndOffset = typeAndOffsetAt(pos);
                int number = numberAt(pos);
                while (nextExtension != null && this.extensionSchema.extensionNumber(nextExtension) > number) {
                    this.extensionSchema.serializeExtension(writer, nextExtension);
                    nextExtension = extensionIterator.hasNext() ? extensionIterator.next() : null;
                }
                switch (type(typeAndOffset)) {
                    case 0:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeDouble(number, doubleAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 1:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeFloat(number, floatAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 2:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeInt64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 3:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeUInt64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 4:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeInt32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 5:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeFixed64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 6:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeFixed32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 7:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeBool(number, booleanAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 8:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                            break;
                        }
                    case 9:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            Object value = UnsafeUtil.getObject(message, offset(typeAndOffset));
                            writer.writeMessage(number, value, getMessageFieldSchema(pos));
                            break;
                        }
                    case 10:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeBytes(number, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffset)));
                            break;
                        }
                    case 11:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeUInt32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 12:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeEnum(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 13:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeSFixed32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 14:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeSFixed64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 15:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeSInt32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 16:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeSInt64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 17:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeGroup(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                            break;
                        }
                    case 18:
                        SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 19:
                        SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 20:
                        SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 21:
                        SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 22:
                        SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 23:
                        SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 24:
                        SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 25:
                        SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 26:
                        SchemaUtil.writeStringList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                        break;
                    case 27:
                        SchemaUtil.writeMessageList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                        break;
                    case 28:
                        SchemaUtil.writeBytesList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                        SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 30:
                        SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 31:
                        SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 32:
                        SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 33:
                        SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 34:
                        SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 35:
                        SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 36:
                        SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 37:
                        SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 38:
                        SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 39:
                        SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 40:
                        SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 41:
                        SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 42:
                        SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 43:
                        SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 44:
                        SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 45:
                        SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 46:
                        SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 47:
                        SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                        SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 49:
                        SchemaUtil.writeGroupList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                        break;
                    case 50:
                        writeMapHelper(writer, number, UnsafeUtil.getObject(message, offset(typeAndOffset)), pos);
                        break;
                    case 51:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeDouble(number, oneofDoubleAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeFloat(number, oneofFloatAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeUInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 56:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 57:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 58:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeBool(number, oneofBooleanAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 59:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                            break;
                        }
                    case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            Object value2 = UnsafeUtil.getObject(message, offset(typeAndOffset));
                            writer.writeMessage(number, value2, getMessageFieldSchema(pos));
                            break;
                        }
                    case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeBytes(number, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffset)));
                            break;
                        }
                    case 62:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeUInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeEnum(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 64:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeSFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 65:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeSFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeSInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeSInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 68:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeGroup(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                            break;
                        }
                }
            } else {
                while (nextExtension != null) {
                    this.extensionSchema.serializeExtension(writer, nextExtension);
                    nextExtension = extensionIterator.hasNext() ? extensionIterator.next() : null;
                }
                return;
            }
        }
    }

    private <K, V> void writeMapHelper(Writer writer, int number, Object mapField, int pos) throws IOException {
        if (mapField != null) {
            writer.writeMap(number, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(pos)), this.mapFieldSchema.forMapData(mapField));
        }
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> schema, T message, Writer writer) throws IOException {
        schema.writeTo(schema.getFromMessage(message), writer);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void mergeFrom(T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        checkMutable(message);
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, message, reader, extensionRegistry);
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    private <UT, UB, ET extends androidx.datastore.preferences.protobuf.FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(androidx.datastore.preferences.protobuf.UnknownFieldSchema<UT, UB> r22, androidx.datastore.preferences.protobuf.ExtensionSchema<ET> r23, T r24, androidx.datastore.preferences.protobuf.Reader r25, androidx.datastore.preferences.protobuf.ExtensionRegistryLite r26) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2214
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.MessageSchema.mergeFromHelper(androidx.datastore.preferences.protobuf.UnknownFieldSchema, androidx.datastore.preferences.protobuf.ExtensionSchema, java.lang.Object, androidx.datastore.preferences.protobuf.Reader, androidx.datastore.preferences.protobuf.ExtensionRegistryLite):void");
    }

    static UnknownFieldSetLite getMutableUnknownFields(Object message) {
        UnknownFieldSetLite unknownFields = ((GeneratedMessageLite) message).unknownFields;
        if (unknownFields == UnknownFieldSetLite.getDefaultInstance()) {
            UnknownFieldSetLite unknownFields2 = UnknownFieldSetLite.newInstance();
            ((GeneratedMessageLite) message).unknownFields = unknownFields2;
            return unknownFields2;
        }
        return unknownFields;
    }

    /* renamed from: androidx.datastore.preferences.protobuf.MessageSchema$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType = new int[WireFormat.FieldType.values().length];

        static {
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    private int decodeMapEntryValue(byte[] data, int position, int limit, WireFormat.FieldType fieldType, Class<?> messageType, ArrayDecoders.Registers registers) throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                int position2 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Boolean.valueOf(registers.long1 != 0);
                return position2;
            case 2:
                return ArrayDecoders.decodeBytes(data, position, registers);
            case 3:
                registers.object1 = Double.valueOf(ArrayDecoders.decodeDouble(data, position));
                return position + 8;
            case 4:
            case 5:
                registers.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(data, position));
                return position + 4;
            case 6:
            case 7:
                registers.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(data, position));
                return position + 8;
            case 8:
                registers.object1 = Float.valueOf(ArrayDecoders.decodeFloat(data, position));
                return position + 4;
            case 9:
            case 10:
            case 11:
                int position3 = ArrayDecoders.decodeVarint32(data, position, registers);
                registers.object1 = Integer.valueOf(registers.int1);
                return position3;
            case 12:
            case 13:
                int position4 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Long.valueOf(registers.long1);
                return position4;
            case 14:
                return ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor((Class) messageType), data, position, limit, registers);
            case 15:
                int position5 = ArrayDecoders.decodeVarint32(data, position, registers);
                registers.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1));
                return position5;
            case 16:
                int position6 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1));
                return position6;
            case 17:
                return ArrayDecoders.decodeStringRequireUtf8(data, position, registers);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <K, V> int decodeMapEntry(byte[] data, int position, int limit, MapEntryLite.Metadata<K, V> metadata, Map<K, V> target, ArrayDecoders.Registers registers) throws IOException {
        int tag;
        int position2;
        int position3;
        int length;
        int position4 = ArrayDecoders.decodeVarint32(data, position, registers);
        int wireType = registers.int1;
        if (wireType < 0 || wireType > limit - position4) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int end = position4 + wireType;
        K key = metadata.defaultKey;
        Object obj = key;
        Object obj2 = metadata.defaultValue;
        while (position4 < end) {
            int position5 = position4 + 1;
            int tag2 = data[position4];
            if (tag2 >= 0) {
                tag = tag2;
                position2 = position5;
            } else {
                int position6 = ArrayDecoders.decodeVarint32(tag2, data, position5, registers);
                tag = registers.int1;
                position2 = position6;
            }
            int fieldNumber = tag >>> 3;
            int wireType2 = tag & 7;
            switch (fieldNumber) {
                case 1:
                    position3 = position2;
                    length = wireType;
                    if (wireType2 != metadata.keyType.getWireType()) {
                        position4 = ArrayDecoders.skipField(tag, data, position3, limit, registers);
                        wireType = length;
                        break;
                    } else {
                        position4 = decodeMapEntryValue(data, position3, limit, metadata.keyType, null, registers);
                        obj = registers.object1;
                        wireType = length;
                        break;
                    }
                case 2:
                    if (wireType2 != metadata.valueType.getWireType()) {
                        position3 = position2;
                        length = wireType;
                        position4 = ArrayDecoders.skipField(tag, data, position3, limit, registers);
                        wireType = length;
                        break;
                    } else {
                        int length2 = wireType;
                        position4 = decodeMapEntryValue(data, position2, limit, metadata.valueType, metadata.defaultValue.getClass(), registers);
                        obj2 = registers.object1;
                        wireType = length2;
                        break;
                    }
                default:
                    position3 = position2;
                    length = wireType;
                    position4 = ArrayDecoders.skipField(tag, data, position3, limit, registers);
                    wireType = length;
                    break;
            }
        }
        if (position4 != end) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        target.put(obj, obj2);
        return end;
    }

    private int parseRepeatedField(T message, byte[] data, int position, int limit, int tag, int number, int wireType, int bufferPosition, long typeAndOffset, int fieldType, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
        Internal.ProtobufList<?> list;
        int position2;
        int position3;
        Internal.ProtobufList<?> list2 = (Internal.ProtobufList) UNSAFE.getObject(message, fieldOffset);
        if (list2.isModifiable()) {
            list = list2;
        } else {
            int size = list2.size();
            Internal.ProtobufList<?> list3 = list2.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
            UNSAFE.putObject(message, fieldOffset, list3);
            list = list3;
        }
        switch (fieldType) {
            case 18:
            case 35:
                position2 = position;
                Internal.ProtobufList<?> list4 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedDoubleList(data, position2, list4, registers);
                }
                if (wireType == 1) {
                    return ArrayDecoders.decodeDoubleList(tag, data, position, limit, list4, registers);
                }
                break;
            case 19:
            case 36:
                position2 = position;
                Internal.ProtobufList<?> list5 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFloatList(data, position2, list5, registers);
                }
                if (wireType == 5) {
                    return ArrayDecoders.decodeFloatList(tag, data, position, limit, list5, registers);
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                position2 = position;
                Internal.ProtobufList<?> list6 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedVarint64List(data, position2, list6, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeVarint64List(tag, data, position, limit, list6, registers);
                }
                break;
            case 22:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
            case 39:
            case 43:
                position2 = position;
                Internal.ProtobufList<?> list7 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedVarint32List(data, position2, list7, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeVarint32List(tag, data, position, limit, list7, registers);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                position2 = position;
                Internal.ProtobufList<?> list8 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFixed64List(data, position2, list8, registers);
                }
                if (wireType == 1) {
                    return ArrayDecoders.decodeFixed64List(tag, data, position, limit, list8, registers);
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                position2 = position;
                Internal.ProtobufList<?> list9 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFixed32List(data, position2, list9, registers);
                }
                if (wireType == 5) {
                    return ArrayDecoders.decodeFixed32List(tag, data, position, limit, list9, registers);
                }
                break;
            case 25:
            case 42:
                position2 = position;
                Internal.ProtobufList<?> list10 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedBoolList(data, position2, list10, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeBoolList(tag, data, position, limit, list10, registers);
                }
                break;
            case 26:
                Internal.ProtobufList<?> list11 = list;
                if (wireType != 2) {
                    position2 = position;
                    break;
                } else {
                    return (typeAndOffset & 536870912) == 0 ? ArrayDecoders.decodeStringList(tag, data, position, limit, list11, registers) : ArrayDecoders.decodeStringListRequireUtf8(tag, data, position, limit, list11, registers);
                }
            case 27:
                Internal.ProtobufList<?> list12 = list;
                if (wireType != 2) {
                    position2 = position;
                    break;
                } else {
                    return ArrayDecoders.decodeMessageList(getMessageFieldSchema(bufferPosition), tag, data, position, limit, list12, registers);
                }
            case 28:
                Internal.ProtobufList<?> list13 = list;
                if (wireType != 2) {
                    position2 = position;
                    break;
                } else {
                    return ArrayDecoders.decodeBytesList(tag, data, position, limit, list13, registers);
                }
            case 30:
            case 44:
                Internal.ProtobufList<?> list14 = list;
                if (wireType == 2) {
                    position3 = ArrayDecoders.decodePackedVarint32List(data, position, list14, registers);
                } else if (wireType != 0) {
                    position2 = position;
                    break;
                } else {
                    position3 = ArrayDecoders.decodeVarint32List(tag, data, position, limit, list14, registers);
                }
                SchemaUtil.filterUnknownEnumList((Object) message, number, (List<Integer>) list14, getEnumFieldVerifier(bufferPosition), (Object) null, (UnknownFieldSchema<UT, Object>) this.unknownFieldSchema);
                return position3;
            case 33:
            case 47:
                Internal.ProtobufList<?> list15 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedSInt32List(data, position, list15, registers);
                }
                if (wireType != 0) {
                    position2 = position;
                    break;
                } else {
                    return ArrayDecoders.decodeSInt32List(tag, data, position, limit, list15, registers);
                }
            case 34:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                Internal.ProtobufList<?> list16 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedSInt64List(data, position, list16, registers);
                }
                if (wireType != 0) {
                    position2 = position;
                    break;
                } else {
                    return ArrayDecoders.decodeSInt64List(tag, data, position, limit, list16, registers);
                }
            case 49:
                if (wireType != 3) {
                    position2 = position;
                    break;
                } else {
                    return ArrayDecoders.decodeGroupList(getMessageFieldSchema(bufferPosition), tag, data, position, limit, list, registers);
                }
            default:
                position2 = position;
                break;
        }
        return position2;
    }

    private <K, V> int parseMapField(T message, byte[] data, int position, int limit, int bufferPosition, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
        Object mapField;
        Unsafe unsafe = UNSAFE;
        Object mapDefaultEntry = getMapFieldDefaultEntry(bufferPosition);
        Object mapField2 = unsafe.getObject(message, fieldOffset);
        if (!this.mapFieldSchema.isImmutable(mapField2)) {
            mapField = mapField2;
        } else {
            Object mapField3 = this.mapFieldSchema.newMapField(mapDefaultEntry);
            this.mapFieldSchema.mergeFrom(mapField3, mapField2);
            unsafe.putObject(message, fieldOffset, mapField3);
            mapField = mapField3;
        }
        return decodeMapEntry(data, position, limit, this.mapFieldSchema.forMapMetadata(mapDefaultEntry), this.mapFieldSchema.forMutableMapData(mapField), registers);
    }

    private int parseOneofField(T message, byte[] data, int position, int limit, int tag, int number, int wireType, int typeAndOffset, int fieldType, long fieldOffset, int bufferPosition, ArrayDecoders.Registers registers) throws IOException {
        long oneofCaseOffset;
        long oneofCaseOffset2;
        Unsafe unsafe = UNSAFE;
        long oneofCaseOffset3 = this.buffer[bufferPosition + 2] & 1048575;
        switch (fieldType) {
            case 51:
                if (wireType == 1) {
                    unsafe.putObject(message, fieldOffset, Double.valueOf(ArrayDecoders.decodeDouble(data, position)));
                    int position2 = position + 8;
                    unsafe.putInt(message, oneofCaseOffset3, number);
                    return position2;
                }
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                if (wireType == 5) {
                    unsafe.putObject(message, fieldOffset, Float.valueOf(ArrayDecoders.decodeFloat(data, position)));
                    int position3 = position + 4;
                    unsafe.putInt(message, oneofCaseOffset3, number);
                    return position3;
                }
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                if (wireType == 0) {
                    int position4 = ArrayDecoders.decodeVarint64(data, position, registers);
                    unsafe.putObject(message, fieldOffset, Long.valueOf(registers.long1));
                    unsafe.putInt(message, oneofCaseOffset3, number);
                    return position4;
                }
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
            case 62:
                if (wireType == 0) {
                    int position5 = ArrayDecoders.decodeVarint32(data, position, registers);
                    unsafe.putObject(message, fieldOffset, Integer.valueOf(registers.int1));
                    unsafe.putInt(message, oneofCaseOffset3, number);
                    return position5;
                }
                break;
            case 56:
            case 65:
                if (wireType == 1) {
                    unsafe.putObject(message, fieldOffset, Long.valueOf(ArrayDecoders.decodeFixed64(data, position)));
                    int position6 = position + 8;
                    unsafe.putInt(message, oneofCaseOffset3, number);
                    return position6;
                }
                break;
            case 57:
            case 64:
                if (wireType == 5) {
                    unsafe.putObject(message, fieldOffset, Integer.valueOf(ArrayDecoders.decodeFixed32(data, position)));
                    int position7 = position + 4;
                    unsafe.putInt(message, oneofCaseOffset3, number);
                    return position7;
                }
                break;
            case 58:
                if (wireType == 0) {
                    int position8 = ArrayDecoders.decodeVarint64(data, position, registers);
                    unsafe.putObject(message, fieldOffset, Boolean.valueOf(registers.long1 != 0));
                    unsafe.putInt(message, oneofCaseOffset3, number);
                    return position8;
                }
                break;
            case 59:
                if (wireType != 2) {
                    break;
                } else {
                    int position9 = ArrayDecoders.decodeVarint32(data, position, registers);
                    int length = registers.int1;
                    if (length == 0) {
                        unsafe.putObject(message, fieldOffset, "");
                    } else {
                        if ((typeAndOffset & ENFORCE_UTF8_MASK) != 0 && !Utf8.isValidUtf8(data, position9, position9 + length)) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        String value = new String(data, position9, length, Internal.UTF_8);
                        unsafe.putObject(message, fieldOffset, value);
                        position9 += length;
                    }
                    unsafe.putInt(message, oneofCaseOffset3, number);
                    return position9;
                }
                break;
            case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                if (wireType != 2) {
                    break;
                } else {
                    Object current = mutableOneofMessageFieldForMerge(message, number, bufferPosition);
                    int position10 = ArrayDecoders.mergeMessageField(current, getMessageFieldSchema(bufferPosition), data, position, limit, registers);
                    storeOneofMessageField(message, number, bufferPosition, current);
                    return position10;
                }
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                if (wireType != 2) {
                    break;
                } else {
                    int position11 = ArrayDecoders.decodeBytes(data, position, registers);
                    unsafe.putObject(message, fieldOffset, registers.object1);
                    unsafe.putInt(message, oneofCaseOffset3, number);
                    return position11;
                }
            case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                if (wireType != 0) {
                    break;
                } else {
                    int position12 = ArrayDecoders.decodeVarint32(data, position, registers);
                    int enumValue = registers.int1;
                    Internal.EnumVerifier enumVerifier = getEnumFieldVerifier(bufferPosition);
                    if (enumVerifier == null) {
                        oneofCaseOffset = oneofCaseOffset3;
                    } else {
                        if (!enumVerifier.isInRange(enumValue)) {
                            getMutableUnknownFields(message).storeField(tag, Long.valueOf(enumValue));
                            oneofCaseOffset2 = oneofCaseOffset3;
                            return position12;
                        }
                        oneofCaseOffset = oneofCaseOffset3;
                    }
                    unsafe.putObject(message, fieldOffset, Integer.valueOf(enumValue));
                    oneofCaseOffset2 = oneofCaseOffset;
                    unsafe.putInt(message, oneofCaseOffset2, number);
                    return position12;
                }
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                if (wireType != 0) {
                    break;
                } else {
                    int position13 = ArrayDecoders.decodeVarint32(data, position, registers);
                    unsafe.putObject(message, fieldOffset, Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1)));
                    unsafe.putInt(message, oneofCaseOffset3, number);
                    return position13;
                }
            case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                if (wireType != 0) {
                    break;
                } else {
                    int position14 = ArrayDecoders.decodeVarint64(data, position, registers);
                    unsafe.putObject(message, fieldOffset, Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1)));
                    unsafe.putInt(message, oneofCaseOffset3, number);
                    return position14;
                }
            case 68:
                if (wireType != 3) {
                    break;
                } else {
                    Object current2 = mutableOneofMessageFieldForMerge(message, number, bufferPosition);
                    int endTag = (tag & (-8)) | 4;
                    int position15 = ArrayDecoders.mergeGroupField(current2, getMessageFieldSchema(bufferPosition), data, position, limit, endTag, registers);
                    storeOneofMessageField(message, number, bufferPosition, current2);
                    return position15;
                }
        }
        return position;
    }

    private Schema getMessageFieldSchema(int pos) {
        int index = (pos / 3) * 2;
        Schema schema = (Schema) this.objects[index];
        if (schema != null) {
            return schema;
        }
        Schema schema2 = Protobuf.getInstance().schemaFor((Class) this.objects[index + 1]);
        this.objects[index] = schema2;
        return schema2;
    }

    private Object getMapFieldDefaultEntry(int pos) {
        return this.objects[(pos / 3) * 2];
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int pos) {
        return (Internal.EnumVerifier) this.objects[((pos / 3) * 2) + 1];
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:115:0x0092. Please report as an issue. */
    int parseMessage(T message, byte[] data, int position, int limit, int endDelimited, ArrayDecoders.Registers registers) throws IOException {
        Unsafe unsafe;
        int i;
        MessageSchema<T> messageSchema;
        int position2;
        int tag;
        T t;
        int tag2;
        int position3;
        int pos;
        int tag3;
        int pos2;
        int typeAndOffset;
        ArrayDecoders.Registers registers2;
        int tag4;
        int currentPresenceFieldOffset;
        int pos3;
        int tag5;
        int i2;
        int position4;
        MessageSchema<T> messageSchema2 = this;
        T t2 = message;
        byte[] bArr = data;
        int pos4 = limit;
        int position5 = endDelimited;
        ArrayDecoders.Registers registers3 = registers;
        checkMutable(message);
        Unsafe unsafe2 = UNSAFE;
        int tag6 = 0;
        int oldNumber = -1;
        int pos5 = 0;
        int currentPresenceFieldOffset2 = 1048575;
        int currentPresenceField = 0;
        int position6 = position;
        while (true) {
            if (position6 < pos4) {
                int position7 = position6 + 1;
                int tag7 = bArr[position6];
                if (tag7 >= 0) {
                    tag2 = tag7;
                    position3 = position7;
                } else {
                    int position8 = ArrayDecoders.decodeVarint32(tag7, bArr, position7, registers3);
                    int tag8 = registers3.int1;
                    tag2 = tag8;
                    position3 = position8;
                }
                int position9 = tag2 >>> 3;
                int wireType = tag2 & 7;
                if (position9 > oldNumber) {
                    pos = messageSchema2.positionForFieldNumber(position9, pos5 / 3);
                } else {
                    int pos6 = messageSchema2.positionForFieldNumber(position9);
                    pos = pos6;
                }
                if (pos == -1) {
                    tag3 = tag2;
                    pos2 = 0;
                    position2 = position3;
                    typeAndOffset = currentPresenceField;
                    unsafe = unsafe2;
                } else {
                    int typeAndOffset2 = messageSchema2.buffer[pos + 1];
                    int fieldType = type(typeAndOffset2);
                    long fieldOffset = offset(typeAndOffset2);
                    int tag9 = tag2;
                    if (fieldType <= 17) {
                        int presenceMaskAndOffset = messageSchema2.buffer[pos + 2];
                        int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
                        int presenceFieldOffset = presenceMaskAndOffset & 1048575;
                        if (presenceFieldOffset != currentPresenceFieldOffset2) {
                            if (currentPresenceFieldOffset2 != 1048575) {
                                unsafe2.putInt(t2, currentPresenceFieldOffset2, currentPresenceField);
                            }
                            currentPresenceFieldOffset2 = presenceFieldOffset;
                            if (presenceFieldOffset == 1048575) {
                                i2 = 0;
                            } else {
                                i2 = unsafe2.getInt(t2, presenceFieldOffset);
                            }
                            currentPresenceField = i2;
                        }
                        switch (fieldType) {
                            case 0:
                                position2 = position3;
                                pos3 = pos;
                                tag5 = tag9;
                                tag4 = currentPresenceFieldOffset2;
                                currentPresenceFieldOffset = wireType;
                                if (currentPresenceFieldOffset == 1) {
                                    UnsafeUtil.putDouble(t2, fieldOffset, ArrayDecoders.decodeDouble(data, position2));
                                    position6 = position2 + 8;
                                    currentPresenceField |= presenceMask;
                                    pos4 = limit;
                                    position5 = endDelimited;
                                    bArr = data;
                                    oldNumber = position9;
                                    currentPresenceFieldOffset2 = tag4;
                                    pos5 = pos3;
                                    tag6 = tag5;
                                } else {
                                    typeAndOffset = currentPresenceField;
                                    unsafe = unsafe2;
                                    currentPresenceFieldOffset2 = tag4;
                                    pos2 = pos3;
                                    tag3 = tag5;
                                    break;
                                }
                            case 1:
                                position2 = position3;
                                pos3 = pos;
                                tag5 = tag9;
                                tag4 = currentPresenceFieldOffset2;
                                currentPresenceFieldOffset = wireType;
                                if (currentPresenceFieldOffset == 5) {
                                    UnsafeUtil.putFloat(t2, fieldOffset, ArrayDecoders.decodeFloat(data, position2));
                                    position6 = position2 + 4;
                                    currentPresenceField |= presenceMask;
                                    pos4 = limit;
                                    position5 = endDelimited;
                                    bArr = data;
                                    oldNumber = position9;
                                    currentPresenceFieldOffset2 = tag4;
                                    pos5 = pos3;
                                    tag6 = tag5;
                                } else {
                                    typeAndOffset = currentPresenceField;
                                    unsafe = unsafe2;
                                    currentPresenceFieldOffset2 = tag4;
                                    pos2 = pos3;
                                    tag3 = tag5;
                                    break;
                                }
                            case 2:
                            case 3:
                                int pos7 = pos;
                                position2 = position3;
                                tag5 = tag9;
                                tag4 = currentPresenceFieldOffset2;
                                currentPresenceFieldOffset = wireType;
                                if (currentPresenceFieldOffset != 0) {
                                    pos3 = pos7;
                                    typeAndOffset = currentPresenceField;
                                    unsafe = unsafe2;
                                    currentPresenceFieldOffset2 = tag4;
                                    pos2 = pos3;
                                    tag3 = tag5;
                                    break;
                                } else {
                                    int position10 = ArrayDecoders.decodeVarint64(data, position2, registers3);
                                    unsafe2.putLong(message, fieldOffset, registers3.long1);
                                    currentPresenceField |= presenceMask;
                                    pos4 = limit;
                                    bArr = data;
                                    position6 = position10;
                                    oldNumber = position9;
                                    currentPresenceFieldOffset2 = tag4;
                                    pos5 = pos7;
                                    tag6 = tag5;
                                    position5 = endDelimited;
                                }
                            case 4:
                            case 11:
                                int pos8 = pos;
                                position2 = position3;
                                tag5 = tag9;
                                tag4 = currentPresenceFieldOffset2;
                                currentPresenceFieldOffset = wireType;
                                if (currentPresenceFieldOffset == 0) {
                                    position6 = ArrayDecoders.decodeVarint32(data, position2, registers3);
                                    unsafe2.putInt(t2, fieldOffset, registers3.int1);
                                    currentPresenceField |= presenceMask;
                                    pos4 = limit;
                                    position5 = endDelimited;
                                    pos5 = pos8;
                                    oldNumber = position9;
                                    currentPresenceFieldOffset2 = tag4;
                                    tag6 = tag5;
                                    bArr = data;
                                } else {
                                    pos3 = pos8;
                                    typeAndOffset = currentPresenceField;
                                    unsafe = unsafe2;
                                    currentPresenceFieldOffset2 = tag4;
                                    pos2 = pos3;
                                    tag3 = tag5;
                                    break;
                                }
                            case 5:
                            case 14:
                                int pos9 = pos;
                                position2 = position3;
                                int i3 = currentPresenceFieldOffset2;
                                currentPresenceFieldOffset = wireType;
                                tag5 = tag9;
                                tag4 = i3;
                                if (currentPresenceFieldOffset == 1) {
                                    unsafe2.putLong(message, fieldOffset, ArrayDecoders.decodeFixed64(data, position2));
                                    position6 = position2 + 8;
                                    currentPresenceField |= presenceMask;
                                    pos4 = limit;
                                    position5 = endDelimited;
                                    pos5 = pos9;
                                    oldNumber = position9;
                                    currentPresenceFieldOffset2 = tag4;
                                    tag6 = tag5;
                                    bArr = data;
                                } else {
                                    pos3 = pos9;
                                    typeAndOffset = currentPresenceField;
                                    unsafe = unsafe2;
                                    currentPresenceFieldOffset2 = tag4;
                                    pos2 = pos3;
                                    tag3 = tag5;
                                    break;
                                }
                            case 6:
                            case 13:
                                position2 = position3;
                                int pos10 = pos;
                                int i4 = currentPresenceFieldOffset2;
                                currentPresenceFieldOffset = wireType;
                                tag5 = tag9;
                                tag4 = i4;
                                if (currentPresenceFieldOffset == 5) {
                                    unsafe2.putInt(t2, fieldOffset, ArrayDecoders.decodeFixed32(data, position2));
                                    int position11 = position2 + 4;
                                    currentPresenceField |= presenceMask;
                                    pos4 = limit;
                                    position5 = endDelimited;
                                    position6 = position11;
                                    pos5 = pos10;
                                    oldNumber = position9;
                                    currentPresenceFieldOffset2 = tag4;
                                    tag6 = tag5;
                                    bArr = data;
                                } else {
                                    pos3 = pos10;
                                    typeAndOffset = currentPresenceField;
                                    unsafe = unsafe2;
                                    currentPresenceFieldOffset2 = tag4;
                                    pos2 = pos3;
                                    tag3 = tag5;
                                    break;
                                }
                            case 7:
                                int pos11 = pos;
                                position2 = position3;
                                int i5 = currentPresenceFieldOffset2;
                                currentPresenceFieldOffset = wireType;
                                tag5 = tag9;
                                tag4 = i5;
                                if (currentPresenceFieldOffset != 0) {
                                    pos3 = pos11;
                                    typeAndOffset = currentPresenceField;
                                    unsafe = unsafe2;
                                    currentPresenceFieldOffset2 = tag4;
                                    pos2 = pos3;
                                    tag3 = tag5;
                                    break;
                                } else {
                                    int position12 = ArrayDecoders.decodeVarint64(data, position2, registers3);
                                    UnsafeUtil.putBoolean(t2, fieldOffset, registers3.long1 != 0);
                                    currentPresenceField |= presenceMask;
                                    pos4 = limit;
                                    position5 = endDelimited;
                                    position6 = position12;
                                    pos5 = pos11;
                                    oldNumber = position9;
                                    currentPresenceFieldOffset2 = tag4;
                                    tag6 = tag5;
                                    bArr = data;
                                }
                            case 8:
                                int pos12 = pos;
                                position2 = position3;
                                Unsafe unsafe3 = unsafe2;
                                tag5 = tag9;
                                tag4 = currentPresenceFieldOffset2;
                                currentPresenceFieldOffset = wireType;
                                if (currentPresenceFieldOffset != 2) {
                                    unsafe2 = unsafe3;
                                    pos3 = pos12;
                                    typeAndOffset = currentPresenceField;
                                    unsafe = unsafe2;
                                    currentPresenceFieldOffset2 = tag4;
                                    pos2 = pos3;
                                    tag3 = tag5;
                                    break;
                                } else {
                                    if (isEnforceUtf8(typeAndOffset2)) {
                                        position6 = ArrayDecoders.decodeStringRequireUtf8(data, position2, registers3);
                                    } else {
                                        position6 = ArrayDecoders.decodeString(data, position2, registers3);
                                    }
                                    unsafe2 = unsafe3;
                                    unsafe2.putObject(t2, fieldOffset, registers3.object1);
                                    currentPresenceField |= presenceMask;
                                    pos4 = limit;
                                    position5 = endDelimited;
                                    pos5 = pos12;
                                    oldNumber = position9;
                                    currentPresenceFieldOffset2 = tag4;
                                    tag6 = tag5;
                                    bArr = data;
                                }
                            case 9:
                                int pos13 = pos;
                                position2 = position3;
                                tag4 = currentPresenceFieldOffset2;
                                currentPresenceFieldOffset = wireType;
                                if (currentPresenceFieldOffset != 2) {
                                    tag5 = tag9;
                                    pos3 = pos13;
                                    typeAndOffset = currentPresenceField;
                                    unsafe = unsafe2;
                                    currentPresenceFieldOffset2 = tag4;
                                    pos2 = pos3;
                                    tag3 = tag5;
                                    break;
                                } else {
                                    Object current = messageSchema2.mutableMessageFieldForMerge(t2, pos13);
                                    position6 = ArrayDecoders.mergeMessageField(current, messageSchema2.getMessageFieldSchema(pos13), data, position2, limit, registers);
                                    messageSchema2.storeMessageField(t2, pos13, current);
                                    currentPresenceField |= presenceMask;
                                    pos4 = limit;
                                    position5 = endDelimited;
                                    pos5 = pos13;
                                    oldNumber = position9;
                                    currentPresenceFieldOffset2 = tag4;
                                    unsafe2 = unsafe2;
                                    tag6 = tag9;
                                    bArr = data;
                                }
                            case 10:
                                int pos14 = pos;
                                position2 = position3;
                                tag4 = currentPresenceFieldOffset2;
                                currentPresenceFieldOffset = wireType;
                                if (currentPresenceFieldOffset != 2) {
                                    tag5 = tag9;
                                    pos3 = pos14;
                                    typeAndOffset = currentPresenceField;
                                    unsafe = unsafe2;
                                    currentPresenceFieldOffset2 = tag4;
                                    pos2 = pos3;
                                    tag3 = tag5;
                                    break;
                                } else {
                                    position6 = ArrayDecoders.decodeBytes(data, position2, registers3);
                                    unsafe2.putObject(t2, fieldOffset, registers3.object1);
                                    currentPresenceField |= presenceMask;
                                    pos4 = limit;
                                    position5 = endDelimited;
                                    tag6 = tag9;
                                    bArr = data;
                                    oldNumber = position9;
                                    currentPresenceFieldOffset2 = tag4;
                                    pos5 = pos14;
                                }
                            case 12:
                                int pos15 = pos;
                                position2 = position3;
                                tag4 = currentPresenceFieldOffset2;
                                currentPresenceFieldOffset = wireType;
                                if (currentPresenceFieldOffset != 0) {
                                    tag5 = tag9;
                                    pos3 = pos15;
                                    typeAndOffset = currentPresenceField;
                                    unsafe = unsafe2;
                                    currentPresenceFieldOffset2 = tag4;
                                    pos2 = pos3;
                                    tag3 = tag5;
                                    break;
                                } else {
                                    position6 = ArrayDecoders.decodeVarint32(data, position2, registers3);
                                    int enumValue = registers3.int1;
                                    Internal.EnumVerifier enumVerifier = messageSchema2.getEnumFieldVerifier(pos15);
                                    if (!isLegacyEnumIsClosed(typeAndOffset2) || enumVerifier == null || enumVerifier.isInRange(enumValue)) {
                                        unsafe2.putInt(t2, fieldOffset, enumValue);
                                        currentPresenceField |= presenceMask;
                                        pos4 = limit;
                                        position5 = endDelimited;
                                        tag6 = tag9;
                                        bArr = data;
                                        oldNumber = position9;
                                        currentPresenceFieldOffset2 = tag4;
                                        pos5 = pos15;
                                    } else {
                                        getMutableUnknownFields(message).storeField(tag9, Long.valueOf(enumValue));
                                        pos4 = limit;
                                        position5 = endDelimited;
                                        tag6 = tag9;
                                        bArr = data;
                                        oldNumber = position9;
                                        currentPresenceFieldOffset2 = tag4;
                                        pos5 = pos15;
                                    }
                                }
                                break;
                            case 15:
                                int pos16 = pos;
                                position2 = position3;
                                tag4 = currentPresenceFieldOffset2;
                                currentPresenceFieldOffset = wireType;
                                if (currentPresenceFieldOffset != 0) {
                                    tag5 = tag9;
                                    pos3 = pos16;
                                    typeAndOffset = currentPresenceField;
                                    unsafe = unsafe2;
                                    currentPresenceFieldOffset2 = tag4;
                                    pos2 = pos3;
                                    tag3 = tag5;
                                    break;
                                } else {
                                    position6 = ArrayDecoders.decodeVarint32(data, position2, registers3);
                                    unsafe2.putInt(t2, fieldOffset, CodedInputStream.decodeZigZag32(registers3.int1));
                                    currentPresenceField |= presenceMask;
                                    pos4 = limit;
                                    position5 = endDelimited;
                                    bArr = data;
                                    oldNumber = position9;
                                    currentPresenceFieldOffset2 = tag4;
                                    tag6 = tag9;
                                    pos5 = pos16;
                                }
                            case 16:
                                int pos17 = pos;
                                position2 = position3;
                                tag4 = currentPresenceFieldOffset2;
                                currentPresenceFieldOffset = wireType;
                                if (currentPresenceFieldOffset == 0) {
                                    int position13 = ArrayDecoders.decodeVarint64(data, position2, registers3);
                                    unsafe2.putLong(message, fieldOffset, CodedInputStream.decodeZigZag64(registers3.long1));
                                    currentPresenceField |= presenceMask;
                                    pos4 = limit;
                                    bArr = data;
                                    position6 = position13;
                                    oldNumber = position9;
                                    currentPresenceFieldOffset2 = tag4;
                                    tag6 = tag9;
                                    pos5 = pos17;
                                    position5 = endDelimited;
                                } else {
                                    tag5 = tag9;
                                    pos3 = pos17;
                                    typeAndOffset = currentPresenceField;
                                    unsafe = unsafe2;
                                    currentPresenceFieldOffset2 = tag4;
                                    pos2 = pos3;
                                    tag3 = tag5;
                                    break;
                                }
                            case 17:
                                if (wireType == 3) {
                                    Object current2 = messageSchema2.mutableMessageFieldForMerge(t2, pos);
                                    int endTag = pos;
                                    position6 = ArrayDecoders.mergeGroupField(current2, messageSchema2.getMessageFieldSchema(pos), data, position3, limit, (position9 << 3) | 4, registers);
                                    messageSchema2.storeMessageField(t2, endTag, current2);
                                    currentPresenceField |= presenceMask;
                                    bArr = data;
                                    position5 = endDelimited;
                                    pos5 = endTag;
                                    oldNumber = position9;
                                    currentPresenceFieldOffset2 = currentPresenceFieldOffset2;
                                    tag6 = tag9;
                                    pos4 = limit;
                                } else {
                                    position2 = position3;
                                    tag4 = currentPresenceFieldOffset2;
                                    currentPresenceFieldOffset = wireType;
                                    pos3 = pos;
                                    tag5 = tag9;
                                    typeAndOffset = currentPresenceField;
                                    unsafe = unsafe2;
                                    currentPresenceFieldOffset2 = tag4;
                                    pos2 = pos3;
                                    tag3 = tag5;
                                    break;
                                }
                            default:
                                position2 = position3;
                                pos3 = pos;
                                tag5 = tag9;
                                tag4 = currentPresenceFieldOffset2;
                                currentPresenceFieldOffset = wireType;
                                typeAndOffset = currentPresenceField;
                                unsafe = unsafe2;
                                currentPresenceFieldOffset2 = tag4;
                                pos2 = pos3;
                                tag3 = tag5;
                                break;
                        }
                    } else {
                        int pos18 = pos;
                        int position14 = position3;
                        int tag10 = currentPresenceFieldOffset2;
                        if (fieldType == 27) {
                            if (wireType == 2) {
                                Internal.ProtobufList<?> list = (Internal.ProtobufList) unsafe2.getObject(t2, fieldOffset);
                                if (!list.isModifiable()) {
                                    int size = list.size();
                                    list = list.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                                    unsafe2.putObject(t2, fieldOffset, list);
                                }
                                position6 = ArrayDecoders.decodeMessageList(messageSchema2.getMessageFieldSchema(pos18), tag9, data, position14, limit, list, registers);
                                bArr = data;
                                pos4 = limit;
                                position5 = endDelimited;
                                registers3 = registers;
                                oldNumber = position9;
                                currentPresenceFieldOffset2 = tag10;
                                pos5 = pos18;
                                tag6 = tag9;
                            } else {
                                pos2 = pos18;
                                typeAndOffset = currentPresenceField;
                                unsafe = unsafe2;
                                position4 = position14;
                                tag3 = tag9;
                                currentPresenceFieldOffset2 = tag10;
                                position2 = position4;
                            }
                        } else {
                            pos2 = pos18;
                            if (fieldType > 49) {
                                unsafe = unsafe2;
                                position4 = position14;
                                tag3 = tag9;
                                typeAndOffset = currentPresenceField;
                                if (fieldType == 50) {
                                    if (wireType == 2) {
                                        position6 = parseMapField(message, data, position4, limit, pos2, fieldOffset, registers);
                                        if (position6 != position4) {
                                            messageSchema2 = this;
                                            t2 = message;
                                            bArr = data;
                                            pos4 = limit;
                                            position5 = endDelimited;
                                            registers3 = registers;
                                            oldNumber = position9;
                                            currentPresenceFieldOffset2 = tag10;
                                            currentPresenceField = typeAndOffset;
                                            pos5 = pos2;
                                            tag6 = tag3;
                                            unsafe2 = unsafe;
                                        } else {
                                            position2 = position6;
                                            currentPresenceFieldOffset2 = tag10;
                                        }
                                    } else {
                                        currentPresenceFieldOffset2 = tag10;
                                        position2 = position4;
                                    }
                                } else {
                                    position6 = parseOneofField(message, data, position4, limit, tag3, position9, wireType, typeAndOffset2, fieldType, fieldOffset, pos2, registers);
                                    if (position6 == position4) {
                                        position2 = position6;
                                        currentPresenceFieldOffset2 = tag10;
                                    } else {
                                        messageSchema2 = this;
                                        t2 = message;
                                        bArr = data;
                                        pos4 = limit;
                                        position5 = endDelimited;
                                        registers3 = registers;
                                        oldNumber = position9;
                                        currentPresenceFieldOffset2 = tag10;
                                        currentPresenceField = typeAndOffset;
                                        pos5 = pos2;
                                        tag6 = tag3;
                                        unsafe2 = unsafe;
                                    }
                                }
                            } else {
                                typeAndOffset = currentPresenceField;
                                unsafe = unsafe2;
                                tag3 = tag9;
                                position6 = parseRepeatedField(message, data, position14, limit, tag9, position9, wireType, pos2, typeAndOffset2, fieldType, fieldOffset, registers);
                                if (position6 != position14) {
                                    messageSchema2 = this;
                                    t2 = message;
                                    bArr = data;
                                    pos4 = limit;
                                    position5 = endDelimited;
                                    registers3 = registers;
                                    oldNumber = position9;
                                    currentPresenceFieldOffset2 = tag10;
                                    currentPresenceField = typeAndOffset;
                                    pos5 = pos2;
                                    tag6 = tag3;
                                    unsafe2 = unsafe;
                                } else {
                                    position2 = position6;
                                    currentPresenceFieldOffset2 = tag10;
                                }
                            }
                        }
                    }
                }
                i = endDelimited;
                tag = tag3;
                if (tag == i && i != 0) {
                    messageSchema = this;
                    currentPresenceField = typeAndOffset;
                } else {
                    if (!this.hasExtensions) {
                        registers2 = registers;
                    } else {
                        registers2 = registers;
                        if (registers2.extensionRegistry != ExtensionRegistryLite.getEmptyRegistry()) {
                            position6 = ArrayDecoders.decodeExtensionOrUnknownField(tag, data, position2, limit, message, this.defaultInstance, this.unknownFieldSchema, registers);
                            t2 = message;
                            position5 = i;
                            tag6 = tag;
                            messageSchema2 = this;
                            registers3 = registers2;
                            oldNumber = position9;
                            currentPresenceField = typeAndOffset;
                            pos5 = pos2;
                            unsafe2 = unsafe;
                            bArr = data;
                            pos4 = limit;
                        }
                    }
                    position6 = ArrayDecoders.decodeUnknownField(tag, data, position2, limit, getMutableUnknownFields(message), registers);
                    t2 = message;
                    position5 = i;
                    tag6 = tag;
                    messageSchema2 = this;
                    registers3 = registers2;
                    oldNumber = position9;
                    currentPresenceField = typeAndOffset;
                    pos5 = pos2;
                    unsafe2 = unsafe;
                    bArr = data;
                    pos4 = limit;
                }
            } else {
                unsafe = unsafe2;
                i = position5;
                messageSchema = messageSchema2;
                position2 = position6;
                tag = tag6;
            }
        }
        if (currentPresenceFieldOffset2 == 1048575) {
            t = message;
        } else {
            t = message;
            unsafe.putInt(t, currentPresenceFieldOffset2, currentPresenceField);
        }
        UnknownFieldSetLite unknownFields = null;
        for (int i6 = messageSchema.checkInitializedCount; i6 < messageSchema.repeatedFieldOffsetStart; i6++) {
            unknownFields = (UnknownFieldSetLite) filterMapUnknownEnumValues(message, messageSchema.intArray[i6], unknownFields, messageSchema.unknownFieldSchema, message);
        }
        if (unknownFields != null) {
            messageSchema.unknownFieldSchema.setBuilderToMessage(t, unknownFields);
        }
        if (i == 0) {
            if (position2 != limit) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        } else if (position2 > limit || tag != i) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return position2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableMessageFieldForMerge(T message, int pos) {
        Schema messageFieldSchema = getMessageFieldSchema(pos);
        long offset = offset(typeAndOffsetAt(pos));
        if (!isFieldPresent(message, pos)) {
            return messageFieldSchema.newInstance();
        }
        Object current = UNSAFE.getObject(message, offset);
        if (isMutable(current)) {
            return current;
        }
        Object newMessage = messageFieldSchema.newInstance();
        if (current != null) {
            messageFieldSchema.mergeFrom(newMessage, current);
        }
        return newMessage;
    }

    private void storeMessageField(T message, int pos, Object field) {
        UNSAFE.putObject(message, offset(typeAndOffsetAt(pos)), field);
        setFieldPresent(message, pos);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableOneofMessageFieldForMerge(T message, int fieldNumber, int pos) {
        Schema messageFieldSchema = getMessageFieldSchema(pos);
        if (!isOneofPresent(message, fieldNumber, pos)) {
            return messageFieldSchema.newInstance();
        }
        Object current = UNSAFE.getObject(message, offset(typeAndOffsetAt(pos)));
        if (isMutable(current)) {
            return current;
        }
        Object newMessage = messageFieldSchema.newInstance();
        if (current != null) {
            messageFieldSchema.mergeFrom(newMessage, current);
        }
        return newMessage;
    }

    private void storeOneofMessageField(T message, int fieldNumber, int pos, Object field) {
        UNSAFE.putObject(message, offset(typeAndOffsetAt(pos)), field);
        setOneofPresent(message, fieldNumber, pos);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void mergeFrom(T message, byte[] data, int position, int limit, ArrayDecoders.Registers registers) throws IOException {
        parseMessage(message, data, position, limit, 0, registers);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.datastore.preferences.protobuf.Schema
    public void makeImmutable(T message) {
        if (!isMutable(message)) {
            return;
        }
        if (message instanceof GeneratedMessageLite) {
            GeneratedMessageLite<?, ?> generatedMessage = (GeneratedMessageLite) message;
            generatedMessage.clearMemoizedSerializedSize();
            generatedMessage.clearMemoizedHashCode();
            generatedMessage.markImmutable();
        }
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            int typeAndOffset = typeAndOffsetAt(pos);
            long offset = offset(typeAndOffset);
            switch (type(typeAndOffset)) {
                case 9:
                case 17:
                    if (isFieldPresent(message, pos)) {
                        getMessageFieldSchema(pos).makeImmutable(UNSAFE.getObject(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case 49:
                    this.listFieldSchema.makeImmutableListAt(message, offset);
                    break;
                case 50:
                    Object mapField = UNSAFE.getObject(message, offset);
                    if (mapField != null) {
                        UNSAFE.putObject(message, offset, this.mapFieldSchema.toImmutable(mapField));
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                case 68:
                    if (isOneofPresent(message, numberAt(pos), pos)) {
                        getMessageFieldSchema(pos).makeImmutable(UNSAFE.getObject(message, offset));
                        break;
                    } else {
                        break;
                    }
            }
        }
        this.unknownFieldSchema.makeImmutable(message);
        if (this.hasExtensions) {
            this.extensionSchema.makeImmutable(message);
        }
    }

    private final <K, V> void mergeMap(Object message, int pos, Object mapDefaultEntry, ExtensionRegistryLite extensionRegistry, Reader reader) throws IOException {
        long offset = offset(typeAndOffsetAt(pos));
        Object mapField = UnsafeUtil.getObject(message, offset);
        if (mapField == null) {
            mapField = this.mapFieldSchema.newMapField(mapDefaultEntry);
            UnsafeUtil.putObject(message, offset, mapField);
        } else if (this.mapFieldSchema.isImmutable(mapField)) {
            mapField = this.mapFieldSchema.newMapField(mapDefaultEntry);
            this.mapFieldSchema.mergeFrom(mapField, mapField);
            UnsafeUtil.putObject(message, offset, mapField);
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(mapField), this.mapFieldSchema.forMapMetadata(mapDefaultEntry), extensionRegistry);
    }

    private <UT, UB> UB filterMapUnknownEnumValues(Object obj, int i, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema, Object obj2) {
        int numberAt = numberAt(i);
        Object object = UnsafeUtil.getObject(obj, offset(typeAndOffsetAt(i)));
        if (object == null) {
            return ub;
        }
        Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(i);
        if (enumFieldVerifier == null) {
            return ub;
        }
        return (UB) filterUnknownEnumMap(i, numberAt, this.mapFieldSchema.forMutableMapData(object), enumFieldVerifier, ub, unknownFieldSchema, obj2);
    }

    private <K, V, UT, UB> UB filterUnknownEnumMap(int i, int i2, Map<K, V> map, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema, Object obj) {
        MapEntryLite.Metadata<?, ?> forMapMetadata = this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!enumVerifier.isInRange(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = unknownFieldSchema.getBuilderFromMessage(obj);
                }
                ByteString.CodedBuilder newCodedBuilder = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(forMapMetadata, next.getKey(), next.getValue()));
                try {
                    MapEntryLite.writeTo(newCodedBuilder.getCodedOutput(), forMapMetadata, next.getKey(), next.getValue());
                    unknownFieldSchema.addLengthDelimited(ub, i2, newCodedBuilder.build());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public final boolean isInitialized(T message) {
        int currentPresenceFieldOffset;
        int currentPresenceField;
        int currentPresenceFieldOffset2 = 1048575;
        int currentPresenceField2 = 0;
        int i = 0;
        while (i < this.checkInitializedCount) {
            int pos = this.intArray[i];
            int number = numberAt(pos);
            int typeAndOffset = typeAndOffsetAt(pos);
            int presenceMaskAndOffset = this.buffer[pos + 2];
            int presenceFieldOffset = presenceMaskAndOffset & 1048575;
            int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
            if (presenceFieldOffset == currentPresenceFieldOffset2) {
                currentPresenceFieldOffset = currentPresenceFieldOffset2;
                currentPresenceField = currentPresenceField2;
            } else if (presenceFieldOffset == 1048575) {
                currentPresenceFieldOffset = presenceFieldOffset;
                currentPresenceField = currentPresenceField2;
            } else {
                int currentPresenceField3 = UNSAFE.getInt(message, presenceFieldOffset);
                currentPresenceFieldOffset = presenceFieldOffset;
                currentPresenceField = currentPresenceField3;
            }
            if (isRequired(typeAndOffset) && !isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                return false;
            }
            switch (type(typeAndOffset)) {
                case 9:
                case 17:
                    if (isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask) && !isInitialized(message, typeAndOffset, getMessageFieldSchema(pos))) {
                        return false;
                    }
                    break;
                case 27:
                case 49:
                    if (!isListInitialized(message, typeAndOffset, pos)) {
                        return false;
                    }
                    break;
                case 50:
                    if (!isMapInitialized(message, typeAndOffset, pos)) {
                        return false;
                    }
                    break;
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                case 68:
                    if (isOneofPresent(message, number, pos) && !isInitialized(message, typeAndOffset, getMessageFieldSchema(pos))) {
                        return false;
                    }
                    break;
            }
            i++;
            currentPresenceFieldOffset2 = currentPresenceFieldOffset;
            currentPresenceField2 = currentPresenceField;
        }
        return !this.hasExtensions || this.extensionSchema.getExtensions(message).isInitialized();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean isInitialized(Object message, int typeAndOffset, Schema schema) {
        Object nested = UnsafeUtil.getObject(message, offset(typeAndOffset));
        return schema.isInitialized(nested);
    }

    private <N> boolean isListInitialized(Object message, int typeAndOffset, int pos) {
        List<N> list = (List) UnsafeUtil.getObject(message, offset(typeAndOffset));
        if (list.isEmpty()) {
            return true;
        }
        Schema schema = getMessageFieldSchema(pos);
        for (int i = 0; i < list.size(); i++) {
            N nested = list.get(i);
            if (!schema.isInitialized(nested)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [androidx.datastore.preferences.protobuf.Schema] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    private boolean isMapInitialized(T t, int i, int i2) {
        Map<?, ?> forMapData = this.mapFieldSchema.forMapData(UnsafeUtil.getObject(t, offset(i)));
        if (forMapData.isEmpty()) {
            return true;
        }
        if (this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)).valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        ?? r4 = 0;
        for (Object obj : forMapData.values()) {
            r4 = r4;
            if (r4 == 0) {
                r4 = Protobuf.getInstance().schemaFor((Class) obj.getClass());
            }
            if (!r4.isInitialized(obj)) {
                return false;
            }
        }
        return true;
    }

    private void writeString(int fieldNumber, Object value, Writer writer) throws IOException {
        if (value instanceof String) {
            writer.writeString(fieldNumber, (String) value);
        } else {
            writer.writeBytes(fieldNumber, (ByteString) value);
        }
    }

    private void readString(Object message, int typeAndOffset, Reader reader) throws IOException {
        if (isEnforceUtf8(typeAndOffset)) {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readStringRequireUtf8());
        } else if (this.lite) {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readString());
        } else {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readBytes());
        }
    }

    private void readStringList(Object message, int typeAndOffset, Reader reader) throws IOException {
        if (isEnforceUtf8(typeAndOffset)) {
            reader.readStringListRequireUtf8(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
        } else {
            reader.readStringList(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
        }
    }

    private <E> void readMessageList(Object message, int typeAndOffset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        long offset = offset(typeAndOffset);
        reader.readMessageList(this.listFieldSchema.mutableListAt(message, offset), schema, extensionRegistry);
    }

    private <E> void readGroupList(Object message, long offset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        reader.readGroupList(this.listFieldSchema.mutableListAt(message, offset), schema, extensionRegistry);
    }

    private int numberAt(int pos) {
        return this.buffer[pos];
    }

    private int typeAndOffsetAt(int pos) {
        return this.buffer[pos + 1];
    }

    private int presenceMaskAndOffsetAt(int pos) {
        return this.buffer[pos + 2];
    }

    private static int type(int value) {
        return (FIELD_TYPE_MASK & value) >>> 20;
    }

    private static boolean isRequired(int value) {
        return (REQUIRED_MASK & value) != 0;
    }

    private static boolean isEnforceUtf8(int value) {
        return (ENFORCE_UTF8_MASK & value) != 0;
    }

    private static boolean isLegacyEnumIsClosed(int value) {
        return (Integer.MIN_VALUE & value) != 0;
    }

    private static long offset(int value) {
        return 1048575 & value;
    }

    private static boolean isMutable(Object message) {
        if (message == null) {
            return false;
        }
        if (message instanceof GeneratedMessageLite) {
            return ((GeneratedMessageLite) message).isMutable();
        }
        return true;
    }

    private static void checkMutable(Object message) {
        if (!isMutable(message)) {
            throw new IllegalArgumentException("Mutating immutable message: " + message);
        }
    }

    private static <T> double doubleAt(T message, long offset) {
        return UnsafeUtil.getDouble(message, offset);
    }

    private static <T> float floatAt(T message, long offset) {
        return UnsafeUtil.getFloat(message, offset);
    }

    private static <T> int intAt(T message, long offset) {
        return UnsafeUtil.getInt(message, offset);
    }

    private static <T> long longAt(T message, long offset) {
        return UnsafeUtil.getLong(message, offset);
    }

    private static <T> boolean booleanAt(T message, long offset) {
        return UnsafeUtil.getBoolean(message, offset);
    }

    private static <T> double oneofDoubleAt(T message, long offset) {
        return ((Double) UnsafeUtil.getObject(message, offset)).doubleValue();
    }

    private static <T> float oneofFloatAt(T message, long offset) {
        return ((Float) UnsafeUtil.getObject(message, offset)).floatValue();
    }

    private static <T> int oneofIntAt(T message, long offset) {
        return ((Integer) UnsafeUtil.getObject(message, offset)).intValue();
    }

    private static <T> long oneofLongAt(T message, long offset) {
        return ((Long) UnsafeUtil.getObject(message, offset)).longValue();
    }

    private static <T> boolean oneofBooleanAt(T message, long offset) {
        return ((Boolean) UnsafeUtil.getObject(message, offset)).booleanValue();
    }

    private boolean arePresentForEquals(T message, T other, int pos) {
        return isFieldPresent(message, pos) == isFieldPresent(other, pos);
    }

    private boolean isFieldPresent(T message, int pos, int presenceFieldOffset, int presenceField, int presenceMask) {
        if (presenceFieldOffset == 1048575) {
            return isFieldPresent(message, pos);
        }
        return (presenceField & presenceMask) != 0;
    }

    private boolean isFieldPresent(T message, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        long presenceFieldOffset = presenceMaskAndOffset & 1048575;
        if (presenceFieldOffset == 1048575) {
            int typeAndOffset = typeAndOffsetAt(pos);
            long offset = offset(typeAndOffset);
            switch (type(typeAndOffset)) {
                case 0:
                    return Double.doubleToRawLongBits(UnsafeUtil.getDouble(message, offset)) != 0;
                case 1:
                    return Float.floatToRawIntBits(UnsafeUtil.getFloat(message, offset)) != 0;
                case 2:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 3:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 4:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 5:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 6:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 7:
                    return UnsafeUtil.getBoolean(message, offset);
                case 8:
                    Object value = UnsafeUtil.getObject(message, offset);
                    if (value instanceof String) {
                        return !((String) value).isEmpty();
                    }
                    if (value instanceof ByteString) {
                        return !ByteString.EMPTY.equals(value);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return UnsafeUtil.getObject(message, offset) != null;
                case 10:
                    return !ByteString.EMPTY.equals(UnsafeUtil.getObject(message, offset));
                case 11:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 12:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 13:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 14:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 15:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 16:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 17:
                    return UnsafeUtil.getObject(message, offset) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
        return (UnsafeUtil.getInt(message, (long) (1048575 & presenceMaskAndOffset)) & presenceMask) != 0;
    }

    private void setFieldPresent(T message, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        long presenceFieldOffset = 1048575 & presenceMaskAndOffset;
        if (presenceFieldOffset == 1048575) {
            return;
        }
        int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
        UnsafeUtil.putInt(message, presenceFieldOffset, UnsafeUtil.getInt(message, presenceFieldOffset) | presenceMask);
    }

    private boolean isOneofPresent(T message, int fieldNumber, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        return UnsafeUtil.getInt(message, (long) (1048575 & presenceMaskAndOffset)) == fieldNumber;
    }

    private boolean isOneofCaseEqual(T message, T other, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        return UnsafeUtil.getInt(message, (long) (presenceMaskAndOffset & 1048575)) == UnsafeUtil.getInt(other, (long) (1048575 & presenceMaskAndOffset));
    }

    private void setOneofPresent(T message, int fieldNumber, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        UnsafeUtil.putInt(message, 1048575 & presenceMaskAndOffset, fieldNumber);
    }

    private int positionForFieldNumber(final int number) {
        if (number >= this.minFieldNumber && number <= this.maxFieldNumber) {
            return slowPositionForFieldNumber(number, 0);
        }
        return -1;
    }

    private int positionForFieldNumber(final int number, final int min) {
        if (number >= this.minFieldNumber && number <= this.maxFieldNumber) {
            return slowPositionForFieldNumber(number, min);
        }
        return -1;
    }

    private int slowPositionForFieldNumber(final int number, int min) {
        int max = (this.buffer.length / 3) - 1;
        while (min <= max) {
            int mid = (max + min) >>> 1;
            int pos = mid * 3;
            int midFieldNumber = numberAt(pos);
            if (number == midFieldNumber) {
                return pos;
            }
            if (number < midFieldNumber) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }

    int getSchemaSize() {
        return this.buffer.length * 3;
    }
}
