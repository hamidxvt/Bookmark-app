package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@23.0.0 */
/* loaded from: classes16.dex */
final class zzlw {
    private static final zzlw zzd = new zzlw(true);
    final zzoe zza = new zzoa();
    private boolean zzb;
    private boolean zzc;

    private zzlw() {
    }

    public static zzlw zza() {
        return zzd;
    }

    static void zzf(zzlm zzlmVar, zzot zzotVar, int i, Object obj) throws IOException {
        if (zzotVar == zzot.GROUP) {
            zznm zznmVar = (zznm) obj;
            zzmp.zzd(zznmVar);
            zzlmVar.zza(i, 3);
            zznmVar.zzcB(zzlmVar);
            zzlmVar.zza(i, 4);
            return;
        }
        zzlmVar.zza(i, zzotVar.zzb());
        zzou zzouVar = zzou.INT;
        switch (zzotVar) {
            case DOUBLE:
                zzlmVar.zzu(Double.doubleToRawLongBits(((Double) obj).doubleValue()));
                break;
            case FLOAT:
                zzlmVar.zzs(Float.floatToRawIntBits(((Float) obj).floatValue()));
                break;
            case INT64:
                zzlmVar.zzt(((Long) obj).longValue());
                break;
            case UINT64:
                zzlmVar.zzt(((Long) obj).longValue());
                break;
            case INT32:
                zzlmVar.zzq(((Integer) obj).intValue());
                break;
            case FIXED64:
                zzlmVar.zzu(((Long) obj).longValue());
                break;
            case FIXED32:
                zzlmVar.zzs(((Integer) obj).intValue());
                break;
            case BOOL:
                zzlmVar.zzp(((Boolean) obj).booleanValue() ? (byte) 1 : (byte) 0);
                break;
            case STRING:
                if (!(obj instanceof zzlh)) {
                    zzlmVar.zzx((String) obj);
                    break;
                } else {
                    zzlmVar.zzj((zzlh) obj);
                    break;
                }
            case GROUP:
                ((zznm) obj).zzcB(zzlmVar);
                break;
            case MESSAGE:
                zzlmVar.zzo((zznm) obj);
                break;
            case BYTES:
                if (!(obj instanceof zzlh)) {
                    byte[] bArr = (byte[]) obj;
                    zzlmVar.zzk(bArr, 0, bArr.length);
                    break;
                } else {
                    zzlmVar.zzj((zzlh) obj);
                    break;
                }
            case UINT32:
                zzlmVar.zzr(((Integer) obj).intValue());
                break;
            case ENUM:
                if (!(obj instanceof zzmj)) {
                    zzlmVar.zzq(((Integer) obj).intValue());
                    break;
                } else {
                    zzlmVar.zzq(((zzmj) obj).zza());
                    break;
                }
            case SFIXED32:
                zzlmVar.zzs(((Integer) obj).intValue());
                break;
            case SFIXED64:
                zzlmVar.zzu(((Long) obj).longValue());
                break;
            case SINT32:
                int intValue = ((Integer) obj).intValue();
                zzlmVar.zzr((intValue >> 31) ^ (intValue + intValue));
                break;
            case SINT64:
                long longValue = ((Long) obj).longValue();
                zzlmVar.zzt((longValue >> 63) ^ (longValue + longValue));
                break;
        }
    }

    static int zzh(zzot zzotVar, int i, Object obj) {
        int zzz = zzlm.zzz(i << 3);
        if (zzotVar == zzot.GROUP) {
            zzmp.zzd((zznm) obj);
            zzz += zzz;
        }
        return zzz + zzi(zzotVar, obj);
    }

    static int zzi(zzot zzotVar, Object obj) {
        zzot zzotVar2 = zzot.DOUBLE;
        zzou zzouVar = zzou.INT;
        switch (zzotVar) {
            case DOUBLE:
                ((Double) obj).doubleValue();
                int i = zzlm.zzb;
                return 8;
            case FLOAT:
                ((Float) obj).floatValue();
                int i2 = zzlm.zzb;
                return 4;
            case INT64:
                return zzlm.zzA(((Long) obj).longValue());
            case UINT64:
                return zzlm.zzA(((Long) obj).longValue());
            case INT32:
                return zzlm.zzA(((Integer) obj).intValue());
            case FIXED64:
                ((Long) obj).longValue();
                int i3 = zzlm.zzb;
                return 8;
            case FIXED32:
                ((Integer) obj).intValue();
                int i4 = zzlm.zzb;
                return 4;
            case BOOL:
                ((Boolean) obj).booleanValue();
                int i5 = zzlm.zzb;
                return 1;
            case STRING:
                if (!(obj instanceof zzlh)) {
                    return zzlm.zzB((String) obj);
                }
                int i6 = zzlm.zzb;
                int zzc = ((zzlh) obj).zzc();
                return zzlm.zzz(zzc) + zzc;
            case GROUP:
                return ((zznm) obj).zzcn();
            case MESSAGE:
                if (!(obj instanceof zzmv)) {
                    return zzlm.zzC((zznm) obj);
                }
                int i7 = zzlm.zzb;
                int zzb = ((zzmv) obj).zzb();
                return zzlm.zzz(zzb) + zzb;
            case BYTES:
                if (obj instanceof zzlh) {
                    int i8 = zzlm.zzb;
                    int zzc2 = ((zzlh) obj).zzc();
                    return zzlm.zzz(zzc2) + zzc2;
                }
                int i9 = zzlm.zzb;
                int length = ((byte[]) obj).length;
                return zzlm.zzz(length) + length;
            case UINT32:
                return zzlm.zzz(((Integer) obj).intValue());
            case ENUM:
                return obj instanceof zzmj ? zzlm.zzA(((zzmj) obj).zza()) : zzlm.zzA(((Integer) obj).intValue());
            case SFIXED32:
                ((Integer) obj).intValue();
                int i10 = zzlm.zzb;
                return 4;
            case SFIXED64:
                ((Long) obj).longValue();
                int i11 = zzlm.zzb;
                return 8;
            case SINT32:
                int intValue = ((Integer) obj).intValue();
                return zzlm.zzz((intValue >> 31) ^ (intValue + intValue));
            case SINT64:
                long longValue = ((Long) obj).longValue();
                return zzlm.zzA((longValue >> 63) ^ (longValue + longValue));
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zzj(zzlv zzlvVar, Object obj) {
        zzot zzb = zzlvVar.zzb();
        int zza = zzlvVar.zza();
        if (!zzlvVar.zzd()) {
            return zzh(zzb, zza, obj);
        }
        List list = (List) obj;
        int size = list.size();
        int i = 0;
        if (!zzlvVar.zze()) {
            int i2 = 0;
            while (i < size) {
                i2 += zzh(zzb, zza, list.get(i));
                i++;
            }
            return i2;
        }
        if (list.isEmpty()) {
            return 0;
        }
        int i3 = 0;
        while (i < size) {
            i3 += zzi(zzb, list.get(i));
            i++;
        }
        return zzlm.zzz(zza << 3) + i3 + zzlm.zzz(i3);
    }

    private static boolean zzk(Map.Entry entry) {
        zzlv zzlvVar = (zzlv) entry.getKey();
        if (zzlvVar.zzc() != zzou.MESSAGE) {
            return true;
        }
        if (!zzlvVar.zzd()) {
            return zzl(entry.getValue());
        }
        List list = (List) entry.getValue();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!zzl(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzl(Object obj) {
        if (obj instanceof zznn) {
            return ((zznn) obj).zzcD();
        }
        if (obj instanceof zzmv) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    private static final int zzm(Map.Entry entry) {
        zzlv zzlvVar = (zzlv) entry.getKey();
        Object value = entry.getValue();
        if (zzlvVar.zzc() != zzou.MESSAGE || zzlvVar.zzd() || zzlvVar.zze()) {
            return zzj(zzlvVar, value);
        }
        if (!(value instanceof zzmv)) {
            int zza = ((zzlv) entry.getKey()).zza();
            int zzz = zzlm.zzz(8);
            return zzz + zzz + zzlm.zzz(16) + zzlm.zzz(zza) + zzlm.zzz(24) + zzlm.zzC((zznm) value);
        }
        int zza2 = ((zzlv) entry.getKey()).zza();
        int zzz2 = zzlm.zzz(8);
        int zzz3 = zzlm.zzz(16) + zzlm.zzz(zza2);
        int zzz4 = zzlm.zzz(24);
        int zzb = ((zzmv) value).zzb();
        return zzz2 + zzz2 + zzz3 + zzz4 + zzlm.zzz(zzb) + zzb;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final void zzn(zzlv zzlvVar, Object obj) {
        boolean z;
        zzlvVar.zzb();
        byte[] bArr = zzmp.zzb;
        if (obj == null) {
            throw null;
        }
        zzot zzotVar = zzot.DOUBLE;
        zzou zzouVar = zzou.INT;
        switch (r0.zza()) {
            case INT:
                z = obj instanceof Integer;
                if (z) {
                    return;
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzlvVar.zza()), zzlvVar.zzb().zza(), obj.getClass().getName()));
            case LONG:
                z = obj instanceof Long;
                if (z) {
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzlvVar.zza()), zzlvVar.zzb().zza(), obj.getClass().getName()));
            case FLOAT:
                z = obj instanceof Float;
                if (z) {
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzlvVar.zza()), zzlvVar.zzb().zza(), obj.getClass().getName()));
            case DOUBLE:
                z = obj instanceof Double;
                if (z) {
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzlvVar.zza()), zzlvVar.zzb().zza(), obj.getClass().getName()));
            case BOOLEAN:
                z = obj instanceof Boolean;
                if (z) {
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzlvVar.zza()), zzlvVar.zzb().zza(), obj.getClass().getName()));
            case STRING:
                z = obj instanceof String;
                if (z) {
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzlvVar.zza()), zzlvVar.zzb().zza(), obj.getClass().getName()));
            case BYTE_STRING:
                if ((obj instanceof zzlh) || (obj instanceof byte[])) {
                    return;
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzlvVar.zza()), zzlvVar.zzb().zza(), obj.getClass().getName()));
            case ENUM:
                if ((obj instanceof Integer) || (obj instanceof zzmj)) {
                    return;
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzlvVar.zza()), zzlvVar.zzb().zza(), obj.getClass().getName()));
            case MESSAGE:
                if ((obj instanceof zznm) || (obj instanceof zzmv)) {
                    return;
                }
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzlvVar.zza()), zzlvVar.zzb().zza(), obj.getClass().getName()));
            default:
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzlvVar.zza()), zzlvVar.zzb().zza(), obj.getClass().getName()));
        }
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzlw zzlwVar = new zzlw();
        zzoe zzoeVar = this.zza;
        int zzc = zzoeVar.zzc();
        for (int i = 0; i < zzc; i++) {
            Map.Entry zzd2 = zzoeVar.zzd(i);
            zzlwVar.zzd((zzlv) ((zzob) zzd2).zza(), zzd2.getValue());
        }
        for (Map.Entry entry : zzoeVar.zze()) {
            zzlwVar.zzd((zzlv) entry.getKey(), entry.getValue());
        }
        zzlwVar.zzc = this.zzc;
        return zzlwVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzlw) {
            return this.zza.equals(((zzlw) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final void zzb() {
        if (this.zzb) {
            return;
        }
        zzoe zzoeVar = this.zza;
        int zzc = zzoeVar.zzc();
        for (int i = 0; i < zzc; i++) {
            Object value = zzoeVar.zzd(i).getValue();
            if (value instanceof zzmf) {
                ((zzmf) value).zzcj();
            }
        }
        Iterator it = zzoeVar.zze().iterator();
        while (it.hasNext()) {
            Object value2 = ((Map.Entry) it.next()).getValue();
            if (value2 instanceof zzmf) {
                ((zzmf) value2).zzcj();
            }
        }
        zzoeVar.zza();
        this.zzb = true;
    }

    public final Iterator zzc() {
        zzoe zzoeVar = this.zza;
        return zzoeVar.isEmpty() ? Collections.emptyIterator() : this.zzc ? new zzmu(zzoeVar.entrySet().iterator()) : zzoeVar.entrySet().iterator();
    }

    public final void zzd(zzlv zzlvVar, Object obj) {
        if (!zzlvVar.zzd()) {
            zzn(zzlvVar, obj);
        } else {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            List list = (List) obj;
            int size = list.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                zzn(zzlvVar, obj2);
                arrayList.add(obj2);
            }
            obj = arrayList;
        }
        if (obj instanceof zzmv) {
            this.zzc = true;
        }
        this.zza.put(zzlvVar, obj);
    }

    public final boolean zze() {
        zzoe zzoeVar = this.zza;
        int zzc = zzoeVar.zzc();
        for (int i = 0; i < zzc; i++) {
            if (!zzk(zzoeVar.zzd(i))) {
                return false;
            }
        }
        Iterator it = zzoeVar.zze().iterator();
        while (it.hasNext()) {
            if (!zzk((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    public final int zzg() {
        zzoe zzoeVar = this.zza;
        int zzc = zzoeVar.zzc();
        int i = 0;
        for (int i2 = 0; i2 < zzc; i2++) {
            i += zzm(zzoeVar.zzd(i2));
        }
        Iterator it = zzoeVar.zze().iterator();
        while (it.hasNext()) {
            i += zzm((Map.Entry) it.next());
        }
        return i;
    }

    private zzlw(boolean z) {
        zzb();
        zzb();
    }
}
