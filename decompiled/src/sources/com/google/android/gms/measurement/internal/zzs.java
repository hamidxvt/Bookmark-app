package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzs implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        int i = 0;
        boolean z2 = false;
        boolean z3 = false;
        int i2 = 0;
        int i3 = 0;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        long j6 = 0;
        long j7 = 0;
        String str = "";
        String str2 = str;
        String str3 = str2;
        String str4 = str3;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        Boolean bool = null;
        ArrayList<String> arrayList = null;
        String str11 = null;
        String str12 = null;
        int i4 = 100;
        boolean z4 = true;
        boolean z5 = true;
        long j8 = -2147483648L;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str5 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    str6 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    str7 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 5:
                    str8 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 6:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 7:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 8:
                    str9 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 9:
                    z4 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 10:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 11:
                    j8 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 12:
                    str10 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 13:
                case 17:
                case 19:
                case 20:
                case 24:
                case 33:
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
                case 14:
                    j3 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 15:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 16:
                    z5 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 18:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 21:
                    bool = SafeParcelReader.readBooleanObject(parcel, readHeader);
                    break;
                case 22:
                    j4 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 23:
                    arrayList = SafeParcelReader.createStringList(parcel, readHeader);
                    break;
                case 25:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 26:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 27:
                    str11 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 28:
                    z3 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    j5 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 30:
                    i4 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 31:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 32:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 34:
                    j6 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 35:
                    str12 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 36:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 37:
                    j7 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 38:
                    i3 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzr(str5, str6, str7, str8, j, j2, str9, z4, z, j8, str10, j3, i, z5, z2, bool, j4, arrayList, str, str2, str11, z3, j5, i4, str3, i2, j6, str12, str4, j7, i3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzr[i];
    }
}
