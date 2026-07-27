package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public enum zzadf {
    DOUBLE(0, 1, zzadv.DOUBLE),
    FLOAT(1, 1, zzadv.FLOAT),
    INT64(2, 1, zzadv.LONG),
    UINT64(3, 1, zzadv.LONG),
    INT32(4, 1, zzadv.INT),
    FIXED64(5, 1, zzadv.LONG),
    FIXED32(6, 1, zzadv.INT),
    BOOL(7, 1, zzadv.BOOLEAN),
    STRING(8, 1, zzadv.STRING),
    MESSAGE(9, 1, zzadv.MESSAGE),
    BYTES(10, 1, zzadv.BYTE_STRING),
    UINT32(11, 1, zzadv.INT),
    ENUM(12, 1, zzadv.ENUM),
    SFIXED32(13, 1, zzadv.INT),
    SFIXED64(14, 1, zzadv.LONG),
    SINT32(15, 1, zzadv.INT),
    SINT64(16, 1, zzadv.LONG),
    GROUP(17, 1, zzadv.MESSAGE),
    DOUBLE_LIST(18, 2, zzadv.DOUBLE),
    FLOAT_LIST(19, 2, zzadv.FLOAT),
    INT64_LIST(20, 2, zzadv.LONG),
    UINT64_LIST(21, 2, zzadv.LONG),
    INT32_LIST(22, 2, zzadv.INT),
    FIXED64_LIST(23, 2, zzadv.LONG),
    FIXED32_LIST(24, 2, zzadv.INT),
    BOOL_LIST(25, 2, zzadv.BOOLEAN),
    STRING_LIST(26, 2, zzadv.STRING),
    MESSAGE_LIST(27, 2, zzadv.MESSAGE),
    BYTES_LIST(28, 2, zzadv.BYTE_STRING),
    UINT32_LIST(29, 2, zzadv.INT),
    ENUM_LIST(30, 2, zzadv.ENUM),
    SFIXED32_LIST(31, 2, zzadv.INT),
    SFIXED64_LIST(32, 2, zzadv.LONG),
    SINT32_LIST(33, 2, zzadv.INT),
    SINT64_LIST(34, 2, zzadv.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzadv.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzadv.FLOAT),
    INT64_LIST_PACKED(37, 3, zzadv.LONG),
    UINT64_LIST_PACKED(38, 3, zzadv.LONG),
    INT32_LIST_PACKED(39, 3, zzadv.INT),
    FIXED64_LIST_PACKED(40, 3, zzadv.LONG),
    FIXED32_LIST_PACKED(41, 3, zzadv.INT),
    BOOL_LIST_PACKED(42, 3, zzadv.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzadv.INT),
    ENUM_LIST_PACKED(44, 3, zzadv.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzadv.INT),
    SFIXED64_LIST_PACKED(46, 3, zzadv.LONG),
    SINT32_LIST_PACKED(47, 3, zzadv.INT),
    SINT64_LIST_PACKED(48, 3, zzadv.LONG),
    GROUP_LIST(49, 2, zzadv.MESSAGE),
    MAP(50, 4, zzadv.VOID);

    private static final zzadf[] zzZ;
    private final zzadv zzab;
    private final int zzac;
    private final Class zzad;

    static {
        zzadf[] values = values();
        zzZ = new zzadf[values.length];
        for (zzadf zzadfVar : values) {
            zzZ[zzadfVar.zzac] = zzadfVar;
        }
    }

    zzadf(int i, int i2, zzadv zzadvVar) {
        this.zzac = i;
        this.zzab = zzadvVar;
        zzadv zzadvVar2 = zzadv.VOID;
        switch (i2 - 1) {
            case 1:
                this.zzad = zzadvVar.zza();
                break;
            case 2:
            default:
                this.zzad = null;
                break;
            case 3:
                this.zzad = zzadvVar.zza();
                break;
        }
        if (i2 == 1) {
            zzadvVar.ordinal();
        }
    }

    public final int zza() {
        return this.zzac;
    }
}
