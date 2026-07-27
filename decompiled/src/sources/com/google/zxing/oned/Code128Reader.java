package com.google.zxing.oned;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes17.dex */
public final class Code128Reader extends OneDReader {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    static final int[][] CODE_PATTERNS;
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final float MAX_AVG_VARIANCE = 0.25f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;

    static {
        int[] iArr = new int[6];
        // fill-array-data instruction
        iArr[0] = 1;
        iArr[1] = 2;
        iArr[2] = 2;
        iArr[3] = 2;
        iArr[4] = 3;
        iArr[5] = 1;
        CODE_PATTERNS = new int[][]{new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, iArr, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};
    }

    private static int[] findStartPattern(BitArray row) throws NotFoundException {
        int width = row.getSize();
        int rowOffset = row.getNextSet(0);
        int counterPosition = 0;
        int[] counters = new int[6];
        int patternStart = rowOffset;
        boolean isWhite = false;
        for (int i = rowOffset; i < width; i++) {
            if (row.get(i) != isWhite) {
                counters[counterPosition] = counters[counterPosition] + 1;
            } else {
                if (counterPosition == 5) {
                    float bestVariance = MAX_AVG_VARIANCE;
                    int bestMatch = -1;
                    for (int startCode = CODE_START_A; startCode <= 105; startCode++) {
                        float variance = patternMatchVariance(counters, CODE_PATTERNS[startCode], MAX_INDIVIDUAL_VARIANCE);
                        if (variance < bestVariance) {
                            bestVariance = variance;
                            bestMatch = startCode;
                        }
                    }
                    if (bestMatch >= 0 && row.isRange(Math.max(0, patternStart - ((i - patternStart) / 2)), patternStart, false)) {
                        return new int[]{patternStart, i, bestMatch};
                    }
                    patternStart += counters[0] + counters[1];
                    System.arraycopy(counters, 2, counters, 0, counterPosition - 1);
                    counters[counterPosition - 1] = 0;
                    counters[counterPosition] = 0;
                    counterPosition--;
                } else {
                    counterPosition++;
                }
                counters[counterPosition] = 1;
                isWhite = isWhite ? false : true;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int decodeCode(BitArray row, int[] counters, int rowOffset) throws NotFoundException {
        recordPattern(row, rowOffset, counters);
        float bestVariance = MAX_AVG_VARIANCE;
        int bestMatch = -1;
        for (int d = 0; d < CODE_PATTERNS.length; d++) {
            int[] pattern = CODE_PATTERNS[d];
            float variance = patternMatchVariance(counters, pattern, MAX_INDIVIDUAL_VARIANCE);
            if (variance < bestVariance) {
                bestVariance = variance;
                bestMatch = d;
            }
        }
        if (bestMatch >= 0) {
            return bestMatch;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x019d A[PHI: r17
      0x019d: PHI (r17v7 boolean) = (r17v5 boolean), (r17v5 boolean), (r17v5 boolean), (r17v8 boolean), (r17v8 boolean), (r17v8 boolean) binds: [B:77:0x014f, B:83:0x0162, B:82:0x015e, B:51:0x00d8, B:57:0x00ec, B:56:0x00e7] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.google.zxing.oned.OneDReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException, ChecksumException {
        char c;
        char c2;
        boolean z;
        boolean z2 = false;
        boolean z3 = map != null && map.containsKey(DecodeHintType.ASSUME_GS1);
        int[] findStartPattern = findStartPattern(bitArray);
        int i2 = findStartPattern[2];
        ArrayList arrayList = new ArrayList(20);
        arrayList.add(Byte.valueOf((byte) i2));
        switch (i2) {
            case CODE_START_A /* 103 */:
                c = 'e';
                break;
            case 104:
                c = 'd';
                break;
            case 105:
                c = 'c';
                break;
            default:
                throw FormatException.getFormatInstance();
        }
        StringBuilder sb = new StringBuilder(20);
        int i3 = 6;
        int[] iArr = new int[6];
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        boolean z7 = true;
        char c3 = c;
        int i7 = findStartPattern[0];
        int i8 = findStartPattern[1];
        char c4 = c3;
        while (!z5) {
            int decodeCode = decodeCode(bitArray, iArr, i8);
            arrayList.add(Byte.valueOf((byte) decodeCode));
            if (decodeCode != CODE_STOP) {
                z7 = true;
            }
            if (decodeCode != CODE_STOP) {
                i5++;
                i2 += i5 * decodeCode;
            }
            int i9 = i8;
            for (int i10 = 0; i10 < i3; i10++) {
                i9 += iArr[i10];
            }
            switch (decodeCode) {
                case CODE_START_A /* 103 */:
                case 104:
                case 105:
                    throw FormatException.getFormatInstance();
                default:
                    switch (c4) {
                        case CODE_CODE_C /* 99 */:
                            c2 = 'd';
                            if (decodeCode < 100) {
                                if (decodeCode < 10) {
                                    sb.append('0');
                                }
                                sb.append(decodeCode);
                            } else {
                                if (decodeCode != CODE_STOP) {
                                    z7 = false;
                                }
                                switch (decodeCode) {
                                    case 100:
                                        c4 = 'd';
                                        z = false;
                                        break;
                                    case TypedValues.TYPE_TARGET /* 101 */:
                                        z = false;
                                        c4 = 'e';
                                        break;
                                    case 102:
                                        if (z3) {
                                            if (sb.length() == 0) {
                                                sb.append("]C1");
                                            } else {
                                                sb.append((char) 29);
                                            }
                                        }
                                    case CODE_START_A /* 103 */:
                                    case 104:
                                    case 105:
                                    default:
                                        z = false;
                                        break;
                                    case CODE_STOP /* 106 */:
                                        z = false;
                                        z5 = true;
                                        break;
                                }
                            }
                            z = false;
                        case 'd':
                            if (decodeCode < 96) {
                                if (z4 == z2) {
                                    sb.append((char) (decodeCode + 32));
                                } else {
                                    sb.append((char) (decodeCode + 32 + 128));
                                }
                                z = false;
                                z4 = false;
                                c2 = 'd';
                                break;
                            } else {
                                if (decodeCode != CODE_STOP) {
                                    z7 = false;
                                }
                                switch (decodeCode) {
                                    case 96:
                                    case CODE_FNC_2 /* 97 */:
                                        z = false;
                                        c2 = 'd';
                                        break;
                                    case CODE_SHIFT /* 98 */:
                                        z = true;
                                        c2 = 'd';
                                        c4 = 'e';
                                        break;
                                    case CODE_CODE_C /* 99 */:
                                        z = false;
                                        c2 = 'd';
                                        c4 = 'c';
                                        break;
                                    case 100:
                                        if (!z2 && z4) {
                                            z2 = true;
                                            z = false;
                                            z4 = false;
                                            c2 = 'd';
                                            break;
                                        } else if (z2 && z4) {
                                            z2 = false;
                                            z = false;
                                            z4 = false;
                                            c2 = 'd';
                                            break;
                                        } else {
                                            z = false;
                                            z4 = true;
                                            c2 = 'd';
                                            break;
                                        }
                                    case TypedValues.TYPE_TARGET /* 101 */:
                                        z = false;
                                        c2 = 'd';
                                        c4 = 'e';
                                        break;
                                    case 102:
                                        if (z3) {
                                            if (sb.length() == 0) {
                                                sb.append("]C1");
                                            } else {
                                                sb.append((char) 29);
                                            }
                                            z = false;
                                            c2 = 'd';
                                            break;
                                        }
                                        z = false;
                                        c2 = 'd';
                                        break;
                                    case CODE_STOP /* 106 */:
                                        z5 = true;
                                    case CODE_START_A /* 103 */:
                                    case 104:
                                    case 105:
                                    default:
                                        z = false;
                                        c2 = 'd';
                                        break;
                                }
                            }
                        case TypedValues.TYPE_TARGET /* 101 */:
                            if (decodeCode < 64) {
                                if (z4 == z2) {
                                    sb.append((char) (decodeCode + 32));
                                } else {
                                    sb.append((char) (decodeCode + 32 + 128));
                                }
                                z = false;
                                z4 = false;
                                c2 = 'd';
                                break;
                            } else if (decodeCode < 96) {
                                if (z4 == z2) {
                                    sb.append((char) (decodeCode - 64));
                                } else {
                                    sb.append((char) (decodeCode + 64));
                                }
                                z = false;
                                z4 = false;
                                c2 = 'd';
                                break;
                            } else {
                                if (decodeCode != CODE_STOP) {
                                    z7 = false;
                                }
                                switch (decodeCode) {
                                    case 96:
                                    case CODE_FNC_2 /* 97 */:
                                        break;
                                    case CODE_SHIFT /* 98 */:
                                        z = true;
                                        c2 = 'd';
                                        c4 = 'd';
                                        break;
                                    case CODE_CODE_C /* 99 */:
                                        z = false;
                                        c2 = 'd';
                                        c4 = 'c';
                                        break;
                                    case 100:
                                        z = false;
                                        c2 = 'd';
                                        c4 = 'd';
                                        break;
                                    case TypedValues.TYPE_TARGET /* 101 */:
                                        if (!z2 && z4) {
                                            z2 = true;
                                            z = false;
                                            z4 = false;
                                            c2 = 'd';
                                            break;
                                        } else if (z2 && z4) {
                                            z2 = false;
                                            z = false;
                                            z4 = false;
                                            c2 = 'd';
                                            break;
                                        } else {
                                            z = false;
                                            z4 = true;
                                            c2 = 'd';
                                            break;
                                        }
                                    case 102:
                                        if (z3) {
                                            if (sb.length() == 0) {
                                                sb.append("]C1");
                                            } else {
                                                sb.append((char) 29);
                                            }
                                            z = false;
                                            c2 = 'd';
                                            break;
                                        }
                                        z = false;
                                        c2 = 'd';
                                        break;
                                    case CODE_STOP /* 106 */:
                                        z5 = true;
                                    case CODE_START_A /* 103 */:
                                    case 104:
                                    case 105:
                                    default:
                                        z = false;
                                        c2 = 'd';
                                        break;
                                }
                            }
                        default:
                            c2 = 'd';
                            z = false;
                            break;
                    }
                    if (z6) {
                        c4 = c4 == 'e' ? c2 : 'e';
                    }
                    z6 = z;
                    i3 = 6;
                    i7 = i8;
                    i8 = i9;
                    i6 = i4;
                    i4 = decodeCode;
            }
            while (!z5) {
            }
        }
        int i11 = i8 - i7;
        int nextUnset = bitArray.getNextUnset(i8);
        if (!bitArray.isRange(nextUnset, Math.min(bitArray.getSize(), ((nextUnset - i7) / 2) + nextUnset), false)) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i12 = i6;
        if ((i2 - (i5 * i12)) % CODE_START_A != i12) {
            throw ChecksumException.getChecksumInstance();
        }
        int length = sb.length();
        if (length == 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (length > 0 && z7) {
            if (c4 == CODE_CODE_C) {
                sb.delete(length - 2, length);
            } else {
                sb.delete(length - 1, length);
            }
        }
        float f = (findStartPattern[1] + findStartPattern[0]) / 2.0f;
        float f2 = i7 + (i11 / 2.0f);
        int size = arrayList.size();
        byte[] bArr = new byte[size];
        for (int i13 = 0; i13 < size; i13++) {
            bArr[i13] = ((Byte) arrayList.get(i13)).byteValue();
        }
        float f3 = i;
        return new Result(sb.toString(), bArr, new ResultPoint[]{new ResultPoint(f, f3), new ResultPoint(f2, f3)}, BarcodeFormat.CODE_128);
    }
}
