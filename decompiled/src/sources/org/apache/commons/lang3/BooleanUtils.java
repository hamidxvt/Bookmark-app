package org.apache.commons.lang3;

import org.apache.commons.lang3.math.NumberUtils;

/* loaded from: classes17.dex */
public class BooleanUtils {
    public static final String FALSE = "false";
    public static final String NO = "no";
    public static final String OFF = "off";
    public static final String ON = "on";
    public static final String TRUE = "true";
    public static final String YES = "yes";

    public static boolean and(boolean... array) {
        ObjectUtils.requireNonEmpty(array, "array");
        for (boolean element : array) {
            if (!element) {
                return false;
            }
        }
        return true;
    }

    public static Boolean and(Boolean... array) {
        ObjectUtils.requireNonEmpty(array, "array");
        try {
            boolean[] primitive = ArrayUtils.toPrimitive(array);
            return and(primitive) ? Boolean.TRUE : Boolean.FALSE;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("The array must not contain any null elements");
        }
    }

    public static Boolean[] booleanValues() {
        return new Boolean[]{Boolean.FALSE, Boolean.TRUE};
    }

    public static int compare(boolean x, boolean y) {
        if (x == y) {
            return 0;
        }
        return x ? 1 : -1;
    }

    public static boolean isFalse(Boolean bool) {
        return Boolean.FALSE.equals(bool);
    }

    public static boolean isNotFalse(Boolean bool) {
        return !isFalse(bool);
    }

    public static boolean isNotTrue(Boolean bool) {
        return !isTrue(bool);
    }

    public static boolean isTrue(Boolean bool) {
        return Boolean.TRUE.equals(bool);
    }

    public static Boolean negate(Boolean bool) {
        if (bool == null) {
            return null;
        }
        return bool.booleanValue() ? Boolean.FALSE : Boolean.TRUE;
    }

    public static boolean or(boolean... array) {
        ObjectUtils.requireNonEmpty(array, "array");
        for (boolean element : array) {
            if (element) {
                return true;
            }
        }
        return false;
    }

    public static Boolean or(Boolean... array) {
        ObjectUtils.requireNonEmpty(array, "array");
        try {
            boolean[] primitive = ArrayUtils.toPrimitive(array);
            return or(primitive) ? Boolean.TRUE : Boolean.FALSE;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("The array must not contain any null elements");
        }
    }

    public static boolean[] primitiveValues() {
        return new boolean[]{false, true};
    }

    public static boolean toBoolean(Boolean bool) {
        return bool != null && bool.booleanValue();
    }

    public static boolean toBoolean(int value) {
        return value != 0;
    }

    public static boolean toBoolean(int value, int trueValue, int falseValue) {
        if (value == trueValue) {
            return true;
        }
        if (value == falseValue) {
            return false;
        }
        throw new IllegalArgumentException("The Integer did not match either specified value");
    }

    public static boolean toBoolean(Integer value, Integer trueValue, Integer falseValue) {
        if (value == null) {
            if (trueValue == null) {
                return true;
            }
            if (falseValue == null) {
                return false;
            }
        } else {
            if (value.equals(trueValue)) {
                return true;
            }
            if (value.equals(falseValue)) {
                return false;
            }
        }
        throw new IllegalArgumentException("The Integer did not match either specified value");
    }

    public static boolean toBoolean(String str) {
        return toBooleanObject(str) == Boolean.TRUE;
    }

    public static boolean toBoolean(String str, String trueString, String falseString) {
        if (str == trueString) {
            return true;
        }
        if (str == falseString) {
            return false;
        }
        if (str != null) {
            if (str.equals(trueString)) {
                return true;
            }
            if (str.equals(falseString)) {
                return false;
            }
        }
        throw new IllegalArgumentException("The String did not match either specified value");
    }

    public static boolean toBooleanDefaultIfNull(Boolean bool, boolean valueIfNull) {
        if (bool == null) {
            return valueIfNull;
        }
        return bool.booleanValue();
    }

    public static Boolean toBooleanObject(int value) {
        return value == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    public static Boolean toBooleanObject(int value, int trueValue, int falseValue, int nullValue) {
        if (value == trueValue) {
            return Boolean.TRUE;
        }
        if (value == falseValue) {
            return Boolean.FALSE;
        }
        if (value == nullValue) {
            return null;
        }
        throw new IllegalArgumentException("The Integer did not match any specified value");
    }

    public static Boolean toBooleanObject(Integer value) {
        if (value == null) {
            return null;
        }
        return value.intValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    public static Boolean toBooleanObject(Integer value, Integer trueValue, Integer falseValue, Integer nullValue) {
        if (value == null) {
            if (trueValue == null) {
                return Boolean.TRUE;
            }
            if (falseValue == null) {
                return Boolean.FALSE;
            }
            if (nullValue == null) {
                return null;
            }
        } else {
            if (value.equals(trueValue)) {
                return Boolean.TRUE;
            }
            if (value.equals(falseValue)) {
                return Boolean.FALSE;
            }
            if (value.equals(nullValue)) {
                return null;
            }
        }
        throw new IllegalArgumentException("The Integer did not match any specified value");
    }

    /* JADX WARN: Code restructure failed: missing block: B:92:0x00d8, code lost:
    
        if (r2 == 'N') goto L83;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Boolean toBooleanObject(String str) {
        char c;
        char c2;
        if (str != TRUE) {
            if (str == null) {
                return null;
            }
            switch (str.length()) {
                case 1:
                    char ch0 = str.charAt(0);
                    if (ch0 == 'y' || ch0 == 'Y' || ch0 == 't' || ch0 == 'T' || ch0 == '1') {
                        return Boolean.TRUE;
                    }
                    if (ch0 == 'n' || ch0 == 'N' || ch0 == 'f' || ch0 == 'F' || ch0 == '0') {
                        return Boolean.FALSE;
                    }
                    return null;
                case 2:
                    char ch02 = str.charAt(0);
                    char ch1 = str.charAt(1);
                    if (ch02 == 'o' || ch02 == 'O') {
                        c = 'n';
                        if (ch1 != 'n') {
                            c2 = 'N';
                            break;
                        }
                        return Boolean.TRUE;
                    }
                    c = 'n';
                    c2 = 'N';
                    if (ch02 != c && ch02 != c2) {
                        return null;
                    }
                    if (ch1 == 'o' || ch1 == 'O') {
                        return Boolean.FALSE;
                    }
                    return null;
                case 3:
                    char ch03 = str.charAt(0);
                    char ch12 = str.charAt(1);
                    char ch2 = str.charAt(2);
                    if ((ch03 == 'y' || ch03 == 'Y') && ((ch12 == 'e' || ch12 == 'E') && (ch2 == 's' || ch2 == 'S'))) {
                        return Boolean.TRUE;
                    }
                    if (ch03 != 'o' && ch03 != 'O') {
                        return null;
                    }
                    if (ch12 != 'f' && ch12 != 'F') {
                        return null;
                    }
                    if (ch2 == 'f' || ch2 == 'F') {
                        return Boolean.FALSE;
                    }
                    return null;
                case 4:
                    char ch04 = str.charAt(0);
                    char ch13 = str.charAt(1);
                    char ch22 = str.charAt(2);
                    char ch3 = str.charAt(3);
                    if (ch04 != 't' && ch04 != 'T') {
                        return null;
                    }
                    if (ch13 != 'r' && ch13 != 'R') {
                        return null;
                    }
                    if (ch22 != 'u' && ch22 != 'U') {
                        return null;
                    }
                    if (ch3 == 'e' || ch3 == 'E') {
                        return Boolean.TRUE;
                    }
                    return null;
                case 5:
                    char ch05 = str.charAt(0);
                    char ch14 = str.charAt(1);
                    char ch23 = str.charAt(2);
                    char ch32 = str.charAt(3);
                    char ch4 = str.charAt(4);
                    if (ch05 != 'f' && ch05 != 'F') {
                        return null;
                    }
                    if (ch14 != 'a' && ch14 != 'A') {
                        return null;
                    }
                    if (ch23 != 'l' && ch23 != 'L') {
                        return null;
                    }
                    if (ch32 != 's' && ch32 != 'S') {
                        return null;
                    }
                    if (ch4 == 'e' || ch4 == 'E') {
                        return Boolean.FALSE;
                    }
                    return null;
                default:
                    return null;
            }
        }
        return Boolean.TRUE;
    }

    public static Boolean toBooleanObject(String str, String trueString, String falseString, String nullString) {
        if (str == null) {
            if (trueString == null) {
                return Boolean.TRUE;
            }
            if (falseString == null) {
                return Boolean.FALSE;
            }
            if (nullString == null) {
                return null;
            }
        } else {
            if (str.equals(trueString)) {
                return Boolean.TRUE;
            }
            if (str.equals(falseString)) {
                return Boolean.FALSE;
            }
            if (str.equals(nullString)) {
                return null;
            }
        }
        throw new IllegalArgumentException("The String did not match any specified value");
    }

    public static int toInteger(boolean z) {
        return z ? 1 : 0;
    }

    public static int toInteger(boolean bool, int trueValue, int falseValue) {
        return bool ? trueValue : falseValue;
    }

    public static int toInteger(Boolean bool, int trueValue, int falseValue, int nullValue) {
        if (bool == null) {
            return nullValue;
        }
        return bool.booleanValue() ? trueValue : falseValue;
    }

    public static Integer toIntegerObject(boolean bool) {
        return bool ? NumberUtils.INTEGER_ONE : NumberUtils.INTEGER_ZERO;
    }

    public static Integer toIntegerObject(boolean bool, Integer trueValue, Integer falseValue) {
        return bool ? trueValue : falseValue;
    }

    public static Integer toIntegerObject(Boolean bool) {
        if (bool == null) {
            return null;
        }
        return bool.booleanValue() ? NumberUtils.INTEGER_ONE : NumberUtils.INTEGER_ZERO;
    }

    public static Integer toIntegerObject(Boolean bool, Integer trueValue, Integer falseValue, Integer nullValue) {
        if (bool == null) {
            return nullValue;
        }
        return bool.booleanValue() ? trueValue : falseValue;
    }

    public static String toString(boolean bool, String trueString, String falseString) {
        return bool ? trueString : falseString;
    }

    public static String toString(Boolean bool, String trueString, String falseString, String nullString) {
        if (bool == null) {
            return nullString;
        }
        return bool.booleanValue() ? trueString : falseString;
    }

    public static String toStringOnOff(boolean bool) {
        return toString(bool, "on", "off");
    }

    public static String toStringOnOff(Boolean bool) {
        return toString(bool, "on", "off", null);
    }

    public static String toStringTrueFalse(boolean bool) {
        return toString(bool, TRUE, FALSE);
    }

    public static String toStringTrueFalse(Boolean bool) {
        return toString(bool, TRUE, FALSE, null);
    }

    public static String toStringYesNo(boolean bool) {
        return toString(bool, YES, NO);
    }

    public static String toStringYesNo(Boolean bool) {
        return toString(bool, YES, NO, null);
    }

    public static boolean xor(boolean... array) {
        ObjectUtils.requireNonEmpty(array, "array");
        boolean result = false;
        for (boolean element : array) {
            result ^= element;
        }
        return result;
    }

    public static Boolean xor(Boolean... array) {
        ObjectUtils.requireNonEmpty(array, "array");
        try {
            boolean[] primitive = ArrayUtils.toPrimitive(array);
            return xor(primitive) ? Boolean.TRUE : Boolean.FALSE;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("The array must not contain any null elements");
        }
    }
}
