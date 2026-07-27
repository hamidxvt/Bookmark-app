package com.pusher.client.util.internal;

import java.util.Arrays;

/* loaded from: classes17.dex */
public class Base64 {
    private static final char[] CHAR_INDEX_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static final int[] charToIndexSparseMappingArray = new int[128];

    static {
        Arrays.fill(charToIndexSparseMappingArray, -1);
        for (int i = 0; i < CHAR_INDEX_TABLE.length; i++) {
            charToIndexSparseMappingArray[CHAR_INDEX_TABLE[i]] = i;
        }
    }

    private static int toInt(char character) {
        int retVal = charToIndexSparseMappingArray[character];
        if (retVal == -1) {
            throw new IllegalArgumentException("invalid char: " + character);
        }
        return retVal;
    }

    public static byte[] decode(String base64String) {
        int paddingSize = base64String.endsWith("==") ? 2 : base64String.endsWith("=") ? 1 : 0;
        byte[] retVal = new byte[((base64String.length() * 3) / 4) - paddingSize];
        int index = 0;
        int i = 0;
        while (i < base64String.length()) {
            int c0 = toInt(base64String.charAt(i));
            int c1 = toInt(base64String.charAt(i + 1));
            int index2 = index + 1;
            retVal[index] = (byte) (((c0 << 2) | (c1 >> 4)) & 255);
            if (index2 >= retVal.length) {
                return retVal;
            }
            int c2 = toInt(base64String.charAt(i + 2));
            int index3 = index2 + 1;
            retVal[index2] = (byte) (((c1 << 4) | (c2 >> 2)) & 255);
            if (index3 >= retVal.length) {
                return retVal;
            }
            int c3 = toInt(base64String.charAt(i + 3));
            retVal[index3] = (byte) (((c2 << 6) | c3) & 255);
            i += 4;
            index = index3 + 1;
        }
        return retVal;
    }
}
