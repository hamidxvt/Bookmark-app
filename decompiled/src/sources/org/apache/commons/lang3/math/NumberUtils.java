package org.apache.commons.lang3.math;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.github.mikephil.charting.utils.Utils;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import kotlin.io.encoding.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* loaded from: classes17.dex */
public class NumberUtils {
    public static final Long LONG_ZERO = 0L;
    public static final Long LONG_ONE = 1L;
    public static final Long LONG_MINUS_ONE = -1L;
    public static final Integer INTEGER_ZERO = 0;
    public static final Integer INTEGER_ONE = 1;
    public static final Integer INTEGER_TWO = 2;
    public static final Integer INTEGER_MINUS_ONE = -1;
    public static final Short SHORT_ZERO = 0;
    public static final Short SHORT_ONE = 1;
    public static final Short SHORT_MINUS_ONE = -1;
    public static final Byte BYTE_ZERO = (byte) 0;
    public static final Byte BYTE_ONE = (byte) 1;
    public static final Byte BYTE_MINUS_ONE = (byte) -1;
    public static final Double DOUBLE_ZERO = Double.valueOf(Utils.DOUBLE_EPSILON);
    public static final Double DOUBLE_ONE = Double.valueOf(1.0d);
    public static final Double DOUBLE_MINUS_ONE = Double.valueOf(-1.0d);
    public static final Float FLOAT_ZERO = Float.valueOf(0.0f);
    public static final Float FLOAT_ONE = Float.valueOf(1.0f);
    public static final Float FLOAT_MINUS_ONE = Float.valueOf(-1.0f);
    public static final Long LONG_INT_MAX_VALUE = 2147483647L;
    public static final Long LONG_INT_MIN_VALUE = -2147483648L;

    public static int toInt(String str) {
        return toInt(str, 0);
    }

    public static int toInt(String str, int defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static long toLong(String str) {
        return toLong(str, 0L);
    }

    public static long toLong(String str, long defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static float toFloat(String str) {
        return toFloat(str, 0.0f);
    }

    public static float toFloat(String str, float defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static double toDouble(String str) {
        return toDouble(str, Utils.DOUBLE_EPSILON);
    }

    public static double toDouble(String str, double defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static double toDouble(BigDecimal value) {
        return toDouble(value, Utils.DOUBLE_EPSILON);
    }

    public static double toDouble(BigDecimal value, double defaultValue) {
        return value == null ? defaultValue : value.doubleValue();
    }

    public static byte toByte(String str) {
        return toByte(str, (byte) 0);
    }

    public static byte toByte(String str, byte defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Byte.parseByte(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static short toShort(String str) {
        return toShort(str, (short) 0);
    }

    public static short toShort(String str, short defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Short.parseShort(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static BigDecimal toScaledBigDecimal(BigDecimal value) {
        return toScaledBigDecimal(value, INTEGER_TWO.intValue(), RoundingMode.HALF_EVEN);
    }

    public static BigDecimal toScaledBigDecimal(BigDecimal value, int scale, RoundingMode roundingMode) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return value.setScale(scale, roundingMode == null ? RoundingMode.HALF_EVEN : roundingMode);
    }

    public static BigDecimal toScaledBigDecimal(Float value) {
        return toScaledBigDecimal(value, INTEGER_TWO.intValue(), RoundingMode.HALF_EVEN);
    }

    public static BigDecimal toScaledBigDecimal(Float value, int scale, RoundingMode roundingMode) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return toScaledBigDecimal(BigDecimal.valueOf(value.floatValue()), scale, roundingMode);
    }

    public static BigDecimal toScaledBigDecimal(Double value) {
        return toScaledBigDecimal(value, INTEGER_TWO.intValue(), RoundingMode.HALF_EVEN);
    }

    public static BigDecimal toScaledBigDecimal(Double value, int scale, RoundingMode roundingMode) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return toScaledBigDecimal(BigDecimal.valueOf(value.doubleValue()), scale, roundingMode);
    }

    public static BigDecimal toScaledBigDecimal(String value) {
        return toScaledBigDecimal(value, INTEGER_TWO.intValue(), RoundingMode.HALF_EVEN);
    }

    public static BigDecimal toScaledBigDecimal(String value, int scale, RoundingMode roundingMode) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return toScaledBigDecimal(createBigDecimal(value), scale, roundingMode);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0195 A[Catch: NumberFormatException -> 0x01a1, TRY_LEAVE, TryCatch #3 {NumberFormatException -> 0x01a1, blocks: (B:80:0x018b, B:82:0x0195), top: B:79:0x018b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Number createNumber(String str) {
        int pfxLen;
        String mant;
        String dec;
        String exp;
        Float f;
        Double d;
        String exp2;
        Double d2;
        if (str == null) {
            return null;
        }
        if (StringUtils.isBlank(str)) {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        boolean allZeros = false;
        String[] hex_prefixes = {"0x", "0X", "-0x", "-0X", "#", "-#"};
        int length = str.length();
        int length2 = hex_prefixes.length;
        int i = 0;
        while (true) {
            if (i >= length2) {
                pfxLen = 0;
                break;
            }
            String pfx = hex_prefixes[i];
            if (str.startsWith(pfx)) {
                int pfxLen2 = 0 + pfx.length();
                pfxLen = pfxLen2;
                break;
            }
            i++;
        }
        if (pfxLen <= 0) {
            char lastChar = str.charAt(length - 1);
            int decPos = str.indexOf(46);
            int expPos = str.indexOf(TypedValues.TYPE_TARGET) + str.indexOf(69) + 1;
            if (decPos > -1) {
                if (expPos <= -1) {
                    dec = str.substring(decPos + 1);
                } else if (expPos >= decPos && expPos <= length) {
                    dec = str.substring(decPos + 1, expPos);
                } else {
                    throw new NumberFormatException(str + " is not a valid number.");
                }
                mant = getMantissa(str, decPos);
            } else {
                if (expPos > -1) {
                    if (expPos > length) {
                        throw new NumberFormatException(str + " is not a valid number.");
                    }
                    mant = getMantissa(str, expPos);
                } else {
                    mant = getMantissa(str);
                }
                dec = null;
            }
            if (!Character.isDigit(lastChar) && lastChar != '.') {
                if (expPos > -1 && expPos < length - 1) {
                    exp2 = str.substring(expPos + 1, length - 1);
                } else {
                    exp2 = null;
                }
                String numeric = str.substring(0, length - 1);
                boolean allZeros2 = isAllZeros(mant) && isAllZeros(exp2);
                switch (lastChar) {
                    case 'D':
                    case 'd':
                        try {
                            d2 = createDouble(str);
                            if (!d2.isInfinite()) {
                                if (d2.doubleValue() != Utils.DOUBLE_EPSILON || allZeros2) {
                                    return d2;
                                }
                            }
                        } catch (NumberFormatException e) {
                        }
                        try {
                            return createBigDecimal(numeric);
                        } catch (NumberFormatException e2) {
                            break;
                        }
                    case 'F':
                    case 'f':
                        try {
                            Float f2 = createFloat(str);
                            if (!f2.isInfinite()) {
                                if (f2.floatValue() != 0.0f || allZeros2) {
                                    return f2;
                                }
                            }
                        } catch (NumberFormatException e3) {
                        }
                        d2 = createDouble(str);
                        if (!d2.isInfinite()) {
                        }
                        return createBigDecimal(numeric);
                    case Base64.mimeLineLength /* 76 */:
                    case AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR /* 108 */:
                        if (dec == null && exp2 == null && ((!numeric.isEmpty() && numeric.charAt(0) == '-' && isDigits(numeric.substring(1))) || isDigits(numeric))) {
                            try {
                                return createLong(numeric);
                            } catch (NumberFormatException e4) {
                                return createBigInteger(numeric);
                            }
                        }
                        throw new NumberFormatException(str + " is not a valid number.");
                    default:
                        throw new NumberFormatException(str + " is not a valid number.");
                }
            } else {
                if (expPos > -1 && expPos < length - 1) {
                    exp = str.substring(expPos + 1);
                } else {
                    exp = null;
                }
                if (dec == null && exp == null) {
                    try {
                        return createInteger(str);
                    } catch (NumberFormatException e5) {
                        try {
                            return createLong(str);
                        } catch (NumberFormatException e6) {
                            return createBigInteger(str);
                        }
                    }
                }
                if (isAllZeros(mant) && isAllZeros(exp)) {
                    allZeros = true;
                }
                try {
                    f = createFloat(str);
                    d = createDouble(str);
                } catch (NumberFormatException e7) {
                }
                if (!f.isInfinite() && ((f.floatValue() != 0.0f || allZeros) && f.toString().equals(d.toString()))) {
                    return f;
                }
                if (!d.isInfinite() && (d.doubleValue() != Utils.DOUBLE_EPSILON || allZeros)) {
                    BigDecimal b = createBigDecimal(str);
                    if (b.compareTo(BigDecimal.valueOf(d.doubleValue())) == 0) {
                        return d;
                    }
                    return b;
                }
                return createBigDecimal(str);
            }
        } else {
            char firstSigDigit = 0;
            for (int i2 = pfxLen; i2 < length; i2++) {
                firstSigDigit = str.charAt(i2);
                if (firstSigDigit != '0') {
                    break;
                }
                pfxLen++;
            }
            int i3 = length - pfxLen;
            if (i3 > 16 || (i3 == 16 && firstSigDigit > '7')) {
                return createBigInteger(str);
            }
            if (i3 > 8 || (i3 == 8 && firstSigDigit > '7')) {
                return createLong(str);
            }
            return createInteger(str);
        }
    }

    private static String getMantissa(String str) {
        return getMantissa(str, str.length());
    }

    private static String getMantissa(String str, int stopPos) {
        char firstChar = str.charAt(0);
        boolean hasSign = firstChar == '-' || firstChar == '+';
        return hasSign ? str.substring(1, stopPos) : str.substring(0, stopPos);
    }

    private static boolean isAllZeros(String str) {
        if (str == null) {
            return true;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) != '0') {
                return false;
            }
        }
        return true ^ str.isEmpty();
    }

    public static Float createFloat(String str) {
        if (str == null) {
            return null;
        }
        return Float.valueOf(str);
    }

    public static Double createDouble(String str) {
        if (str == null) {
            return null;
        }
        return Double.valueOf(str);
    }

    public static Integer createInteger(String str) {
        if (str == null) {
            return null;
        }
        return Integer.decode(str);
    }

    public static Long createLong(String str) {
        if (str == null) {
            return null;
        }
        return Long.decode(str);
    }

    public static BigInteger createBigInteger(String str) {
        if (str == null) {
            return null;
        }
        int pos = 0;
        int radix = 10;
        boolean negate = false;
        if (str.startsWith("-")) {
            negate = true;
            pos = 1;
        }
        if (str.startsWith("0x", pos) || str.startsWith("0X", pos)) {
            radix = 16;
            pos += 2;
        } else if (str.startsWith("#", pos)) {
            radix = 16;
            pos++;
        } else if (str.startsWith("0", pos) && str.length() > pos + 1) {
            radix = 8;
            pos++;
        }
        BigInteger value = new BigInteger(str.substring(pos), radix);
        return negate ? value.negate() : value;
    }

    public static BigDecimal createBigDecimal(String str) {
        if (str == null) {
            return null;
        }
        if (StringUtils.isBlank(str)) {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        return new BigDecimal(str);
    }

    public static long min(long... array) {
        validateArray(array);
        long min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static int min(int... array) {
        validateArray(array);
        int min = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] < min) {
                min = array[j];
            }
        }
        return min;
    }

    public static short min(short... array) {
        validateArray(array);
        short min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static byte min(byte... array) {
        validateArray(array);
        byte min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static double min(double... array) {
        validateArray(array);
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (!Double.isNaN(array[i])) {
                if (array[i] < min) {
                    min = array[i];
                }
            } else {
                return Double.NaN;
            }
        }
        return min;
    }

    public static float min(float... array) {
        validateArray(array);
        float min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (!Float.isNaN(array[i])) {
                if (array[i] < min) {
                    min = array[i];
                }
            } else {
                return Float.NaN;
            }
        }
        return min;
    }

    public static long max(long... array) {
        validateArray(array);
        long max = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] > max) {
                max = array[j];
            }
        }
        return max;
    }

    public static int max(int... array) {
        validateArray(array);
        int max = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] > max) {
                max = array[j];
            }
        }
        return max;
    }

    public static short max(short... array) {
        validateArray(array);
        short max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static byte max(byte... array) {
        validateArray(array);
        byte max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static double max(double... array) {
        validateArray(array);
        double max = array[0];
        for (int j = 1; j < array.length; j++) {
            if (!Double.isNaN(array[j])) {
                if (array[j] > max) {
                    max = array[j];
                }
            } else {
                return Double.NaN;
            }
        }
        return max;
    }

    public static float max(float... array) {
        validateArray(array);
        float max = array[0];
        for (int j = 1; j < array.length; j++) {
            if (!Float.isNaN(array[j])) {
                if (array[j] > max) {
                    max = array[j];
                }
            } else {
                return Float.NaN;
            }
        }
        return max;
    }

    private static void validateArray(Object array) {
        Validate.notNull(array, "array", new Object[0]);
        Validate.isTrue(Array.getLength(array) != 0, "Array cannot be empty.", new Object[0]);
    }

    public static long min(long a, long b, long c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            return c;
        }
        return a;
    }

    public static int min(int a, int b, int c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            return c;
        }
        return a;
    }

    public static short min(short a, short b, short c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            return c;
        }
        return a;
    }

    public static byte min(byte a, byte b, byte c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            return c;
        }
        return a;
    }

    public static double min(double a, double b, double c) {
        return Math.min(Math.min(a, b), c);
    }

    public static float min(float a, float b, float c) {
        return Math.min(Math.min(a, b), c);
    }

    public static long max(long a, long b, long c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            return c;
        }
        return a;
    }

    public static int max(int a, int b, int c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            return c;
        }
        return a;
    }

    public static short max(short a, short b, short c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            return c;
        }
        return a;
    }

    public static byte max(byte a, byte b, byte c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            return c;
        }
        return a;
    }

    public static double max(double a, double b, double c) {
        return Math.max(Math.max(a, b), c);
    }

    public static float max(float a, float b, float c) {
        return Math.max(Math.max(a, b), c);
    }

    public static boolean isDigits(String str) {
        return StringUtils.isNumeric(str);
    }

    @Deprecated
    public static boolean isNumber(String str) {
        return isCreatable(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:133:0x012e, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x014e, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean isCreatable(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        char[] chars = str.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        boolean z = true;
        int start = (chars[0] == '-' || chars[0] == '+') ? 1 : 0;
        char c = '9';
        char c2 = '0';
        if (sz > start + 1 && chars[start] == '0') {
            if (!StringUtils.contains(str, 46)) {
                if (chars[start + 1] == 'x' || chars[start + 1] == 'X') {
                    int i = start + 2;
                    if (i == sz) {
                        return false;
                    }
                    while (i < chars.length) {
                        if ((chars[i] < '0' || chars[i] > c) && ((chars[i] < 'a' || chars[i] > 'f') && (chars[i] < 'A' || chars[i] > 'F'))) {
                            return false;
                        }
                        i++;
                        c = '9';
                    }
                    return true;
                }
                if (Character.isDigit(chars[start + 1])) {
                    for (int i2 = start + 1; i2 < chars.length; i2++) {
                        if (chars[i2] < '0' || chars[i2] > '7') {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        int sz2 = sz - 1;
        int i3 = start;
        while (true) {
            if (i3 >= sz2 && (i3 >= sz2 + 1 || !allowSigns || foundDigit)) {
                break;
            }
            if (chars[i3] >= c2 && chars[i3] <= '9') {
                foundDigit = true;
                allowSigns = false;
                i3++;
                c2 = '0';
                z = true;
            }
            if (chars[i3] == '.') {
                if (hasDecPoint || hasExp) {
                    break;
                }
                hasDecPoint = true;
            } else {
                if (chars[i3] != 'e' && chars[i3] != 'E') {
                    if (chars[i3] != '+' && chars[i3] != '-') {
                        return false;
                    }
                    allowSigns = false;
                    foundDigit = false;
                }
                if (hasExp || !foundDigit) {
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            }
            i3++;
            c2 = '0';
            z = true;
        }
        if (i3 < chars.length) {
            if (chars[i3] >= c2 && chars[i3] <= '9') {
                return z;
            }
            if (chars[i3] == 'e' || chars[i3] == 'E') {
                return false;
            }
            if (chars[i3] == '.') {
                if (hasDecPoint || hasExp) {
                    return false;
                }
                return foundDigit;
            }
            if (!allowSigns && (chars[i3] == 'd' || chars[i3] == 'D' || chars[i3] == 'f' || chars[i3] == 'F')) {
                return foundDigit;
            }
            if ((chars[i3] == 'l' || chars[i3] == 'L') && foundDigit && !hasExp && !hasDecPoint) {
                return z;
            }
            return false;
        }
        if (allowSigns || !foundDigit) {
            return false;
        }
        return z;
    }

    public static boolean isParsable(String str) {
        if (StringUtils.isEmpty(str) || str.charAt(str.length() - 1) == '.') {
            return false;
        }
        if (str.charAt(0) == '-') {
            if (str.length() == 1) {
                return false;
            }
            return withDecimalsParsing(str, 1);
        }
        return withDecimalsParsing(str, 0);
    }

    private static boolean withDecimalsParsing(String str, int beginIdx) {
        int decimalPoints = 0;
        for (int i = beginIdx; i < str.length(); i++) {
            boolean isDecimalPoint = str.charAt(i) == '.';
            if (isDecimalPoint) {
                decimalPoints++;
            }
            if (decimalPoints > 1) {
                return false;
            }
            if (!isDecimalPoint && !Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static int compare(int x, int y) {
        if (x == y) {
            return 0;
        }
        return x < y ? -1 : 1;
    }

    public static int compare(long x, long y) {
        if (x == y) {
            return 0;
        }
        return x < y ? -1 : 1;
    }

    public static int compare(short x, short y) {
        if (x == y) {
            return 0;
        }
        return x < y ? -1 : 1;
    }

    public static int compare(byte x, byte y) {
        return x - y;
    }
}
