package com.pusher.client.crypto.nacl;

import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes17.dex */
public final class TweetNaclFast {
    private static final byte[] sigma = {101, 120, 112, 97, 110, 100, 32, 51, 50, 45, 98, 121, 116, 101, 32, 107};

    public static final class SecretBox {
        public static final int boxzerobytesLength = 16;
        public static final int nonceLength = 24;
        public static final int zerobytesLength = 32;
        private final byte[] key;
        private final AtomicLong nonce;

        public SecretBox(byte[] key) {
            this(key, 68L);
        }

        public SecretBox(byte[] key, long nonce) {
            this.key = key;
            this.nonce = new AtomicLong(nonce);
        }

        public byte[] open(byte[] box, byte[] theNonce) {
            if (box == null) {
                return null;
            }
            return open(box, 0, box.length, theNonce);
        }

        public byte[] open(byte[] box, int boxoff, int boxlen, byte[] theNonce) {
            if (box == null || box.length < boxoff + boxlen || boxlen < 16 || theNonce == null || theNonce.length != 24) {
                return null;
            }
            byte[] c = new byte[boxlen + 16];
            byte[] m = new byte[c.length];
            for (int i = 0; i < boxlen; i++) {
                c[i + 16] = box[i + boxoff];
            }
            int i2 = c.length;
            if (TweetNaclFast.crypto_secretbox_open(m, c, i2, theNonce, this.key) != 0) {
                return null;
            }
            byte[] ret = new byte[m.length - 32];
            for (int i3 = 0; i3 < ret.length; i3++) {
                ret[i3] = m[i3 + 32];
            }
            return ret;
        }
    }

    private static int vn(byte[] x, int xoff, byte[] y, int yoff, int n) {
        int d = 0;
        for (int i = 0; i < n; i++) {
            d |= (x[i + xoff] ^ y[i + yoff]) & 255;
        }
        return (((d - 1) >>> 8) & 1) - 1;
    }

    private static int crypto_verify_16(byte[] x, int xoff, byte[] y, int yoff) {
        return vn(x, xoff, y, yoff, 16);
    }

    private static void core_salsa20(byte[] o, byte[] p, byte[] k, byte[] c) {
        int j0 = (c[0] & 255) | ((c[1] & 255) << 8) | ((c[2] & 255) << 16) | ((c[3] & 255) << 24);
        int j1 = (k[0] & 255) | ((k[1] & 255) << 8) | ((k[2] & 255) << 16) | ((k[3] & 255) << 24);
        int j2 = (k[4] & 255) | ((k[5] & 255) << 8) | ((k[6] & 255) << 16) | ((k[7] & 255) << 24);
        int j3 = ((k[9] & 255) << 8) | (k[8] & 255) | ((k[10] & 255) << 16) | ((k[11] & 255) << 24);
        int j4 = ((k[14] & 255) << 16) | ((k[13] & 255) << 8) | (k[12] & 255) | ((k[15] & 255) << 24);
        int j5 = (c[4] & 255) | ((c[5] & 255) << 8) | ((c[6] & 255) << 16) | ((c[7] & 255) << 24);
        int j6 = ((p[1] & 255) << 8) | (p[0] & 255) | ((p[2] & 255) << 16) | ((p[3] & 255) << 24);
        int j7 = (p[4] & 255) | ((p[5] & 255) << 8) | ((p[6] & 255) << 16) | ((p[7] & 255) << 24);
        int j8 = ((p[10] & 255) << 16) | ((p[9] & 255) << 8) | (p[8] & 255) | ((p[11] & 255) << 24);
        int j9 = ((p[14] & 255) << 16) | (p[12] & 255) | ((p[13] & 255) << 8) | ((p[15] & 255) << 24);
        int j10 = ((c[9] & 255) << 8) | (c[8] & 255) | ((c[10] & 255) << 16) | ((c[11] & 255) << 24);
        int j11 = ((k[18] & 255) << 16) | (k[16] & 255) | ((k[17] & 255) << 8) | ((k[19] & 255) << 24);
        int u = ((k[23] & 255) << 24) | ((k[21] & 255) << 8) | (k[20] & 255) | ((k[22] & 255) << 16);
        int j13 = (k[24] & 255) | ((k[25] & 255) << 8) | ((k[26] & 255) << 16) | ((k[27] & 255) << 24);
        int j14 = ((k[29] & 255) << 8) | (k[28] & 255) | ((k[30] & 255) << 16) | ((k[31] & 255) << 24);
        int j15 = (c[12] & 255) | ((c[13] & 255) << 8) | ((c[14] & 255) << 16) | ((c[15] & 255) << 24);
        int x0 = j0;
        int x1 = j1;
        int x2 = j2;
        int x3 = j3;
        int x4 = j4;
        int x5 = j5;
        int x6 = j6;
        int x7 = j7;
        int x8 = j8;
        int x9 = j9;
        int x10 = j10;
        int x11 = j11;
        int x12 = u;
        int x13 = j13;
        int x14 = j14;
        int x15 = j15;
        int j152 = 0;
        while (true) {
            int j12 = u;
            if (j152 < 20) {
                int u2 = (x0 + x12) | 0;
                int x42 = x4 ^ ((u2 << 7) | (u2 >>> 25));
                int u3 = (x42 + x0) | 0;
                int x82 = x8 ^ ((u3 << 9) | (u3 >>> 23));
                int u4 = (x82 + x42) | 0;
                int x122 = x12 ^ ((u4 << 13) | (u4 >>> 19));
                int u5 = (x122 + x82) | 0;
                int x02 = x0 ^ ((u5 << 18) | (u5 >>> 14));
                int u6 = (x5 + x1) | 0;
                int x92 = x9 ^ ((u6 << 7) | (u6 >>> 25));
                int u7 = (x92 + x5) | 0;
                int x132 = x13 ^ ((u7 << 9) | (u7 >>> 23));
                int u8 = (x132 + x92) | 0;
                int x16 = x1 ^ ((u8 << 13) | (u8 >>> 19));
                int u9 = (x16 + x132) | 0;
                int x52 = x5 ^ ((u9 << 18) | (u9 >>> 14));
                int u10 = (x10 + x6) | 0;
                int x142 = x14 ^ ((u10 << 7) | (u10 >>> 25));
                int u11 = (x142 + x10) | 0;
                int x22 = x2 ^ ((u11 << 9) | (u11 >>> 23));
                int u12 = (x22 + x142) | 0;
                int x62 = x6 ^ ((u12 << 13) | (u12 >>> 19));
                int u13 = (x62 + x22) | 0;
                int x102 = x10 ^ ((u13 << 18) | (u13 >>> 14));
                int u14 = (x15 + x11) | 0;
                int x32 = x3 ^ ((u14 << 7) | (u14 >>> 25));
                int u15 = (x32 + x15) | 0;
                int x72 = x7 ^ ((u15 << 9) | (u15 >>> 23));
                int u16 = (x72 + x32) | 0;
                int x112 = x11 ^ ((u16 << 13) | (u16 >>> 19));
                int u17 = (x112 + x72) | 0;
                int x152 = x15 ^ ((u17 << 18) | (u17 >>> 14));
                int u18 = (x02 + x32) | 0;
                x1 = x16 ^ ((u18 << 7) | (u18 >>> 25));
                int u19 = (x1 + x02) | 0;
                x2 = x22 ^ ((u19 << 9) | (u19 >>> 23));
                int u20 = (x2 + x1) | 0;
                x3 = x32 ^ ((u20 << 13) | (u20 >>> 19));
                int u21 = (x3 + x2) | 0;
                x0 = x02 ^ ((u21 << 18) | (u21 >>> 14));
                int u22 = (x52 + x42) | 0;
                x6 = x62 ^ ((u22 << 7) | (u22 >>> 25));
                int u23 = (x6 + x52) | 0;
                x7 = x72 ^ ((u23 << 9) | (u23 >>> 23));
                int u24 = (x7 + x6) | 0;
                x4 = x42 ^ ((u24 << 13) | (u24 >>> 19));
                int u25 = (x4 + x7) | 0;
                x5 = x52 ^ ((u25 << 18) | (u25 >>> 14));
                int u26 = (x102 + x92) | 0;
                x11 = x112 ^ ((u26 << 7) | (u26 >>> 25));
                int u27 = (x11 + x102) | 0;
                x8 = x82 ^ ((u27 << 9) | (u27 >>> 23));
                int u28 = (x8 + x11) | 0;
                x9 = x92 ^ ((u28 << 13) | (u28 >>> 19));
                int u29 = (x9 + x8) | 0;
                x10 = x102 ^ ((u29 << 18) | (u29 >>> 14));
                int u30 = (x152 + x142) | 0;
                x12 = x122 ^ ((u30 << 7) | (u30 >>> 25));
                int u31 = (x12 + x152) | 0;
                x13 = x132 ^ ((u31 << 9) | (u31 >>> 23));
                int u32 = (x13 + x12) | 0;
                x14 = x142 ^ ((u32 << 13) | (u32 >>> 19));
                int u33 = (x14 + x13) | 0;
                x15 = x152 ^ ((u33 << 18) | (u33 >>> 14));
                j152 += 2;
                u = j12;
            } else {
                int x03 = (x0 + j0) | 0;
                int x04 = x1 + j1;
                int x17 = x04 | 0;
                int x18 = x2 + j2;
                int x23 = x18 | 0;
                int x33 = (x3 + j3) | 0;
                int x43 = (x4 + j4) | 0;
                int x53 = (x5 + j5) | 0;
                int x63 = (x6 + j6) | 0;
                int x73 = (x7 + j7) | 0;
                int x83 = (x8 + j8) | 0;
                int x93 = (x9 + j9) | 0;
                int x103 = (x10 + j10) | 0;
                int x113 = (x11 + j11) | 0;
                int x123 = (x12 + j12) | 0;
                int x133 = (x13 + j13) | 0;
                int x143 = (x14 + j14) | 0;
                int x153 = (x15 + j15) | 0;
                o[0] = (byte) ((x03 >>> 0) & 255);
                o[1] = (byte) ((x03 >>> 8) & 255);
                o[2] = (byte) ((x03 >>> 16) & 255);
                o[3] = (byte) ((x03 >>> 24) & 255);
                o[4] = (byte) ((x17 >>> 0) & 255);
                o[5] = (byte) ((x17 >>> 8) & 255);
                o[6] = (byte) ((x17 >>> 16) & 255);
                o[7] = (byte) ((x17 >>> 24) & 255);
                o[8] = (byte) ((x23 >>> 0) & 255);
                o[9] = (byte) ((x23 >>> 8) & 255);
                o[10] = (byte) ((x23 >>> 16) & 255);
                o[11] = (byte) ((x23 >>> 24) & 255);
                o[12] = (byte) ((x33 >>> 0) & 255);
                o[13] = (byte) ((x33 >>> 8) & 255);
                o[14] = (byte) ((x33 >>> 16) & 255);
                o[15] = (byte) ((x33 >>> 24) & 255);
                o[16] = (byte) ((x43 >>> 0) & 255);
                o[17] = (byte) ((x43 >>> 8) & 255);
                o[18] = (byte) ((x43 >>> 16) & 255);
                o[19] = (byte) ((x43 >>> 24) & 255);
                o[20] = (byte) ((x53 >>> 0) & 255);
                o[21] = (byte) ((x53 >>> 8) & 255);
                o[22] = (byte) ((x53 >>> 16) & 255);
                o[23] = (byte) ((x53 >>> 24) & 255);
                o[24] = (byte) ((x63 >>> 0) & 255);
                o[25] = (byte) ((x63 >>> 8) & 255);
                o[26] = (byte) ((x63 >>> 16) & 255);
                o[27] = (byte) ((x63 >>> 24) & 255);
                o[28] = (byte) ((x73 >>> 0) & 255);
                o[29] = (byte) ((x73 >>> 8) & 255);
                o[30] = (byte) ((x73 >>> 16) & 255);
                o[31] = (byte) ((x73 >>> 24) & 255);
                o[32] = (byte) ((x83 >>> 0) & 255);
                o[33] = (byte) ((x83 >>> 8) & 255);
                o[34] = (byte) ((x83 >>> 16) & 255);
                o[35] = (byte) ((x83 >>> 24) & 255);
                o[36] = (byte) ((x93 >>> 0) & 255);
                o[37] = (byte) ((x93 >>> 8) & 255);
                o[38] = (byte) ((x93 >>> 16) & 255);
                o[39] = (byte) ((x93 >>> 24) & 255);
                o[40] = (byte) ((x103 >>> 0) & 255);
                o[41] = (byte) ((x103 >>> 8) & 255);
                o[42] = (byte) ((x103 >>> 16) & 255);
                o[43] = (byte) ((x103 >>> 24) & 255);
                o[44] = (byte) ((x113 >>> 0) & 255);
                o[45] = (byte) ((x113 >>> 8) & 255);
                o[46] = (byte) ((x113 >>> 16) & 255);
                o[47] = (byte) ((x113 >>> 24) & 255);
                o[48] = (byte) ((x123 >>> 0) & 255);
                o[49] = (byte) ((x123 >>> 8) & 255);
                o[50] = (byte) ((x123 >>> 16) & 255);
                o[51] = (byte) ((x123 >>> 24) & 255);
                o[52] = (byte) ((x133 >>> 0) & 255);
                o[53] = (byte) ((x133 >>> 8) & 255);
                o[54] = (byte) ((x133 >>> 16) & 255);
                o[55] = (byte) ((x133 >>> 24) & 255);
                o[56] = (byte) ((x143 >>> 0) & 255);
                o[57] = (byte) ((x143 >>> 8) & 255);
                o[58] = (byte) ((x143 >>> 16) & 255);
                o[59] = (byte) ((x143 >>> 24) & 255);
                o[60] = (byte) ((x153 >>> 0) & 255);
                o[61] = (byte) ((x153 >>> 8) & 255);
                o[62] = (byte) ((x153 >>> 16) & 255);
                o[63] = (byte) ((x153 >>> 24) & 255);
                return;
            }
        }
    }

    private static void core_hsalsa20(byte[] o, byte[] p, byte[] k, byte[] c) {
        int u = (c[0] & 255) | ((c[1] & 255) << 8) | ((c[2] & 255) << 16) | ((c[3] & 255) << 24);
        int j1 = (k[0] & 255) | ((k[1] & 255) << 8) | ((k[2] & 255) << 16) | ((k[3] & 255) << 24);
        int j2 = (k[4] & 255) | ((k[5] & 255) << 8) | ((k[6] & 255) << 16) | ((k[7] & 255) << 24);
        int j3 = ((k[9] & 255) << 8) | (k[8] & 255) | ((k[10] & 255) << 16) | ((k[11] & 255) << 24);
        int j4 = ((k[14] & 255) << 16) | ((k[13] & 255) << 8) | (k[12] & 255) | ((k[15] & 255) << 24);
        int j5 = (c[4] & 255) | ((c[5] & 255) << 8) | ((c[6] & 255) << 16) | ((c[7] & 255) << 24);
        int j6 = ((p[1] & 255) << 8) | (p[0] & 255) | ((p[2] & 255) << 16) | ((p[3] & 255) << 24);
        int j7 = (p[4] & 255) | ((p[5] & 255) << 8) | ((p[6] & 255) << 16) | ((p[7] & 255) << 24);
        int j8 = ((p[10] & 255) << 16) | ((p[9] & 255) << 8) | (p[8] & 255) | ((p[11] & 255) << 24);
        int j9 = ((p[14] & 255) << 16) | (p[12] & 255) | ((p[13] & 255) << 8) | ((p[15] & 255) << 24);
        int j10 = ((c[9] & 255) << 8) | (c[8] & 255) | ((c[10] & 255) << 16) | ((c[11] & 255) << 24);
        int j11 = ((k[18] & 255) << 16) | (k[16] & 255) | ((k[17] & 255) << 8) | ((k[19] & 255) << 24);
        int j12 = ((k[23] & 255) << 24) | ((k[21] & 255) << 8) | (k[20] & 255) | ((k[22] & 255) << 16);
        int j13 = (k[24] & 255) | ((k[25] & 255) << 8) | ((k[26] & 255) << 16) | ((k[27] & 255) << 24);
        int j14 = ((k[29] & 255) << 8) | (k[28] & 255) | ((k[30] & 255) << 16) | ((k[31] & 255) << 24);
        int j142 = c[12];
        int i = (j142 & 255) | ((c[13] & 255) << 8);
        int j132 = c[14];
        int j15 = i | ((j132 & 255) << 16) | ((c[15] & 255) << 24);
        int x0 = u;
        int x1 = j1;
        int x2 = j2;
        int x3 = j3;
        int x4 = j4;
        int x5 = j5;
        int x6 = j6;
        int x7 = j7;
        int x8 = j8;
        int x9 = j9;
        int x10 = j10;
        int x11 = j11;
        int x12 = j12;
        int x13 = j13;
        int x14 = j14;
        int x15 = j15;
        int j62 = 0;
        while (true) {
            int j0 = u;
            if (j62 < 20) {
                int u2 = (x0 + x12) | 0;
                int x42 = x4 ^ ((u2 << 7) | (u2 >>> 25));
                int u3 = (x42 + x0) | 0;
                int x82 = x8 ^ ((u3 << 9) | (u3 >>> 23));
                int u4 = (x82 + x42) | 0;
                int x122 = x12 ^ ((u4 << 13) | (u4 >>> 19));
                int u5 = (x122 + x82) | 0;
                int x02 = x0 ^ ((u5 << 18) | (u5 >>> 14));
                int u6 = (x5 + x1) | 0;
                int x92 = x9 ^ ((u6 << 7) | (u6 >>> 25));
                int u7 = (x92 + x5) | 0;
                int x132 = x13 ^ ((u7 << 9) | (u7 >>> 23));
                int u8 = (x132 + x92) | 0;
                int x16 = x1 ^ ((u8 << 13) | (u8 >>> 19));
                int u9 = (x16 + x132) | 0;
                int x52 = x5 ^ ((u9 << 18) | (u9 >>> 14));
                int u10 = (x10 + x6) | 0;
                int x142 = x14 ^ ((u10 << 7) | (u10 >>> 25));
                int u11 = (x142 + x10) | 0;
                int x22 = x2 ^ ((u11 << 9) | (u11 >>> 23));
                int u12 = (x22 + x142) | 0;
                int x62 = x6 ^ ((u12 << 13) | (u12 >>> 19));
                int u13 = (x62 + x22) | 0;
                int x102 = x10 ^ ((u13 << 18) | (u13 >>> 14));
                int u14 = (x15 + x11) | 0;
                int x32 = x3 ^ ((u14 << 7) | (u14 >>> 25));
                int u15 = (x32 + x15) | 0;
                int x72 = x7 ^ ((u15 << 9) | (u15 >>> 23));
                int u16 = (x72 + x32) | 0;
                int x112 = x11 ^ ((u16 << 13) | (u16 >>> 19));
                int u17 = (x112 + x72) | 0;
                int x152 = x15 ^ ((u17 << 18) | (u17 >>> 14));
                int u18 = (x02 + x32) | 0;
                x1 = x16 ^ ((u18 << 7) | (u18 >>> 25));
                int u19 = (x1 + x02) | 0;
                x2 = x22 ^ ((u19 << 9) | (u19 >>> 23));
                int u20 = (x2 + x1) | 0;
                x3 = x32 ^ ((u20 << 13) | (u20 >>> 19));
                int u21 = (x3 + x2) | 0;
                x0 = x02 ^ ((u21 << 18) | (u21 >>> 14));
                int u22 = (x52 + x42) | 0;
                x6 = x62 ^ ((u22 << 7) | (u22 >>> 25));
                int u23 = (x6 + x52) | 0;
                x7 = x72 ^ ((u23 << 9) | (u23 >>> 23));
                int u24 = (x7 + x6) | 0;
                x4 = x42 ^ ((u24 << 13) | (u24 >>> 19));
                int u25 = (x4 + x7) | 0;
                x5 = x52 ^ ((u25 << 18) | (u25 >>> 14));
                int u26 = (x102 + x92) | 0;
                x11 = x112 ^ ((u26 << 7) | (u26 >>> 25));
                int u27 = (x11 + x102) | 0;
                x8 = x82 ^ ((u27 << 9) | (u27 >>> 23));
                int u28 = (x8 + x11) | 0;
                x9 = x92 ^ ((u28 << 13) | (u28 >>> 19));
                int u29 = (x9 + x8) | 0;
                x10 = x102 ^ ((u29 << 18) | (u29 >>> 14));
                int u30 = (x152 + x142) | 0;
                x12 = x122 ^ ((u30 << 7) | (u30 >>> 25));
                int u31 = (x12 + x152) | 0;
                x13 = x132 ^ ((u31 << 9) | (u31 >>> 23));
                int u32 = (x13 + x12) | 0;
                x14 = x142 ^ ((u32 << 13) | (u32 >>> 19));
                int u33 = (x14 + x13) | 0;
                x15 = x152 ^ ((u33 << 18) | (u33 >>> 14));
                j62 += 2;
                u = j0;
            } else {
                int i2 = x0 >>> 0;
                o[0] = (byte) (i2 & 255);
                o[1] = (byte) ((x0 >>> 8) & 255);
                o[2] = (byte) ((x0 >>> 16) & 255);
                o[3] = (byte) ((x0 >>> 24) & 255);
                o[4] = (byte) ((x5 >>> 0) & 255);
                o[5] = (byte) ((x5 >>> 8) & 255);
                o[6] = (byte) ((x5 >>> 16) & 255);
                o[7] = (byte) ((x5 >>> 24) & 255);
                o[8] = (byte) ((x10 >>> 0) & 255);
                o[9] = (byte) ((x10 >>> 8) & 255);
                o[10] = (byte) ((x10 >>> 16) & 255);
                o[11] = (byte) ((x10 >>> 24) & 255);
                o[12] = (byte) ((x15 >>> 0) & 255);
                o[13] = (byte) ((x15 >>> 8) & 255);
                o[14] = (byte) ((x15 >>> 16) & 255);
                o[15] = (byte) ((x15 >>> 24) & 255);
                o[16] = (byte) ((x6 >>> 0) & 255);
                o[17] = (byte) ((x6 >>> 8) & 255);
                o[18] = (byte) ((x6 >>> 16) & 255);
                o[19] = (byte) ((x6 >>> 24) & 255);
                o[20] = (byte) ((x7 >>> 0) & 255);
                o[21] = (byte) ((x7 >>> 8) & 255);
                o[22] = (byte) ((x7 >>> 16) & 255);
                o[23] = (byte) ((x7 >>> 24) & 255);
                o[24] = (byte) ((x8 >>> 0) & 255);
                o[25] = (byte) ((x8 >>> 8) & 255);
                o[26] = (byte) ((x8 >>> 16) & 255);
                o[27] = (byte) ((x8 >>> 24) & 255);
                o[28] = (byte) ((x9 >>> 0) & 255);
                o[29] = (byte) ((x9 >>> 8) & 255);
                o[30] = (byte) ((x9 >>> 16) & 255);
                o[31] = (byte) ((x9 >>> 24) & 255);
                return;
            }
        }
    }

    public static int crypto_core_salsa20(byte[] out, byte[] in, byte[] k, byte[] c) {
        core_salsa20(out, in, k, c);
        return 0;
    }

    public static int crypto_core_hsalsa20(byte[] out, byte[] in, byte[] k, byte[] c) {
        core_hsalsa20(out, in, k, c);
        return 0;
    }

    private static int crypto_stream_salsa20_xor(byte[] c, int cpos, byte[] m, int mpos, long b, byte[] n, byte[] k) {
        byte[] z = new byte[16];
        byte[] x = new byte[64];
        for (int i = 0; i < 16; i++) {
            z[i] = 0;
        }
        for (int i2 = 0; i2 < 8; i2++) {
            z[i2] = n[i2];
        }
        int mpos2 = mpos;
        long b2 = b;
        int i3 = cpos;
        while (b2 >= 64) {
            crypto_core_salsa20(x, z, k, sigma);
            for (int i4 = 0; i4 < 64; i4++) {
                c[i3 + i4] = (byte) ((m[mpos2 + i4] ^ x[i4]) & 255);
            }
            int u = 1;
            for (int i5 = 8; i5 < 16; i5++) {
                int u2 = ((z[i5] & 255) + u) | 0;
                z[i5] = (byte) (u2 & 255);
                u = u2 >>> 8;
            }
            b2 -= 64;
            i3 += 64;
            mpos2 += 64;
        }
        if (b2 > 0) {
            crypto_core_salsa20(x, z, k, sigma);
            for (int i6 = 0; i6 < b2; i6++) {
                c[i3 + i6] = (byte) ((m[mpos2 + i6] ^ x[i6]) & 255);
            }
        }
        return 0;
    }

    public static int crypto_stream_salsa20(byte[] c, int cpos, long b, byte[] n, byte[] k) {
        byte[] z = new byte[16];
        byte[] x = new byte[64];
        for (int i = 0; i < 16; i++) {
            z[i] = 0;
        }
        for (int i2 = 0; i2 < 8; i2++) {
            z[i2] = n[i2];
        }
        long b2 = b;
        int i3 = cpos;
        while (b2 >= 64) {
            crypto_core_salsa20(x, z, k, sigma);
            for (int i4 = 0; i4 < 64; i4++) {
                c[i3 + i4] = x[i4];
            }
            int u = 1;
            for (int i5 = 8; i5 < 16; i5++) {
                int u2 = ((z[i5] & 255) + u) | 0;
                z[i5] = (byte) (u2 & 255);
                u = u2 >>> 8;
            }
            b2 -= 64;
            i3 += 64;
        }
        if (b2 > 0) {
            crypto_core_salsa20(x, z, k, sigma);
            for (int i6 = 0; i6 < b2; i6++) {
                c[i3 + i6] = x[i6];
            }
        }
        return 0;
    }

    public static int crypto_stream(byte[] c, int cpos, long d, byte[] n, byte[] k) {
        byte[] s = new byte[32];
        crypto_core_hsalsa20(s, n, k, sigma);
        byte[] sn = new byte[8];
        for (int i = 0; i < 8; i++) {
            sn[i] = n[i + 16];
        }
        return crypto_stream_salsa20(c, cpos, d, sn, s);
    }

    public static int crypto_stream_xor(byte[] c, int cpos, byte[] m, int mpos, long d, byte[] n, byte[] k) {
        byte[] s = new byte[32];
        crypto_core_hsalsa20(s, n, k, sigma);
        byte[] sn = new byte[8];
        for (int i = 0; i < 8; i++) {
            sn[i] = n[i + 16];
        }
        return crypto_stream_salsa20_xor(c, cpos, m, mpos, d, sn, s);
    }

    public static final class poly1305 {
        private final byte[] buffer = new byte[16];
        private final int[] r = new int[10];
        private final int[] h = new int[10];
        private final int[] pad = new int[8];
        private int leftover = 0;
        private int fin = 0;

        public poly1305(byte[] key) {
            int t0 = (key[0] & 255) | ((key[1] & 255) << 8);
            this.r[0] = t0 & 8191;
            int t1 = (key[2] & 255) | ((key[3] & 255) << 8);
            this.r[1] = ((t0 >>> 13) | (t1 << 3)) & 8191;
            int t2 = (key[4] & 255) | ((key[5] & 255) << 8);
            this.r[2] = ((t1 >>> 10) | (t2 << 6)) & 7939;
            int t3 = ((key[7] & 255) << 8) | (key[6] & 255);
            this.r[3] = ((t2 >>> 7) | (t3 << 9)) & 8191;
            int t4 = (key[8] & 255) | ((key[9] & 255) << 8);
            this.r[4] = ((t3 >>> 4) | (t4 << 12)) & 255;
            this.r[5] = (t4 >>> 1) & 8190;
            int t5 = (key[10] & 255) | ((key[11] & 255) << 8);
            this.r[6] = ((t4 >>> 14) | (t5 << 2)) & 8191;
            int t6 = (key[12] & 255) | ((key[13] & 255) << 8);
            this.r[7] = ((t5 >>> 11) | (t6 << 5)) & 8065;
            int t7 = (key[14] & 255) | ((key[15] & 255) << 8);
            this.r[8] = ((t6 >>> 8) | (t7 << 8)) & 8191;
            this.r[9] = (t7 >>> 5) & 127;
            this.pad[0] = (key[16] & 255) | ((key[17] & 255) << 8);
            this.pad[1] = (key[18] & 255) | ((key[19] & 255) << 8);
            this.pad[2] = (key[20] & 255) | ((key[21] & 255) << 8);
            this.pad[3] = (key[22] & 255) | ((key[23] & 255) << 8);
            this.pad[4] = (key[24] & 255) | ((key[25] & 255) << 8);
            this.pad[5] = (key[26] & 255) | ((key[27] & 255) << 8);
            this.pad[6] = (key[28] & 255) | ((key[29] & 255) << 8);
            this.pad[7] = ((key[31] & 255) << 8) | (key[30] & 255);
        }

        public poly1305 blocks(byte[] m, int mpos, int bytes) {
            int hibit = this.fin != 0 ? 0 : 2048;
            int h0 = this.h[0];
            int h1 = this.h[1];
            int h2 = this.h[2];
            int h3 = this.h[3];
            int h4 = this.h[4];
            int h5 = this.h[5];
            int h6 = this.h[6];
            int h7 = this.h[7];
            int h8 = this.h[8];
            int h9 = this.h[9];
            int r0 = this.r[0];
            int d2 = this.r[1];
            int r2 = this.r[2];
            int r3 = this.r[3];
            int h02 = h0;
            int d0 = this.r[4];
            int h12 = h1;
            int d9 = this.r[5];
            int h22 = h2;
            int r6 = this.r[6];
            int h32 = h3;
            int r7 = this.r[7];
            int h42 = h4;
            int r8 = this.r[8];
            int h92 = h9;
            int r9 = this.r[9];
            int h62 = h6;
            int h72 = h7;
            int h52 = h5;
            int h82 = h8;
            int d8 = mpos;
            int d3 = bytes;
            while (d3 >= 16) {
                int i = m[d8 + 0] & 255;
                int bytes2 = d3;
                int bytes3 = m[d8 + 1];
                int t0 = i | ((bytes3 & 255) << 8);
                int h03 = h02 + (t0 & 8191);
                int r1 = d2;
                int t1 = ((m[d8 + 3] & 255) << 8) | (m[d8 + 2] & 255);
                int h13 = h12 + (((t0 >>> 13) | (t1 << 3)) & 8191);
                int t2 = ((m[d8 + 5] & 255) << 8) | (m[d8 + 4] & 255);
                int h23 = h22 + (((t1 >>> 10) | (t2 << 6)) & 8191);
                int t3 = ((m[d8 + 7] & 255) << 8) | (m[d8 + 6] & 255);
                int h33 = h32 + (((t2 >>> 7) | (t3 << 9)) & 8191);
                int t4 = ((m[d8 + 9] & 255) << 8) | (m[d8 + 8] & 255);
                int h43 = h42 + (((t3 >>> 4) | (t4 << 12)) & 8191);
                int h53 = h52 + ((t4 >>> 1) & 8191);
                int t5 = ((m[d8 + 11] & 255) << 8) | (m[d8 + 10] & 255);
                int h63 = h62 + (((t4 >>> 14) | (t5 << 2)) & 8191);
                int t6 = ((m[d8 + 13] & 255) << 8) | (m[d8 + 12] & 255);
                int h73 = h72 + (((t5 >>> 11) | (t6 << 5)) & 8191);
                int t7 = ((m[d8 + 15] & 255) << 8) | (m[d8 + 14] & 255);
                int h83 = h82 + (((t6 >>> 8) | (t7 << 8)) & 8191);
                int h93 = h92 + ((t7 >>> 5) | hibit);
                int d02 = 0 + (h03 * r0);
                int t62 = d02 + (r9 * 5 * h13) + (r8 * 5 * h23) + (r7 * 5 * h33) + (r6 * 5 * h43);
                int c = t62 >>> 13;
                int d03 = t62 & 8191;
                int d04 = d03 + (d9 * 5 * h53) + (d0 * 5 * h63) + (r3 * 5 * h73) + (r2 * 5 * h83) + (r1 * 5 * h93);
                int d1 = c + (d04 >>> 13) + (h03 * r1);
                int hibit2 = hibit;
                int hibit3 = d1 + (h13 * r0) + (r9 * 5 * h23) + (r8 * 5 * h33) + (r7 * 5 * h43);
                int c2 = hibit3 >>> 13;
                int d12 = hibit3 & 8191;
                int d13 = d12 + (r6 * 5 * h53) + (d9 * 5 * h63) + (d0 * 5 * h73) + (r3 * 5 * h83) + (r2 * 5 * h93);
                int d22 = c2 + (d13 >>> 13) + (h03 * r2);
                int t72 = d22 + (h13 * r1) + (h23 * r0) + (r9 * 5 * h33) + (r8 * 5 * h43);
                int c3 = t72 >>> 13;
                int d23 = t72 & 8191;
                int d24 = d23 + (r7 * 5 * h53) + (r6 * 5 * h63) + (d9 * 5 * h73) + (d0 * 5 * h83) + (r3 * 5 * h93);
                int d32 = c3 + (d24 >>> 13) + (h03 * r3);
                int c4 = d32 + (h13 * r2) + (h23 * r1) + (h33 * r0) + (r9 * 5 * h43);
                int d33 = c4 >>> 13;
                int d34 = c4 & 8191;
                int d35 = d34 + (r8 * 5 * h53) + (r7 * 5 * h63) + (r6 * 5 * h73) + (d9 * 5 * h83) + (d0 * 5 * h93);
                int c5 = d33 + (d35 >>> 13);
                int d4 = c5 + (h03 * d0);
                int mpos2 = d8;
                int mpos3 = d4 + (h13 * r3) + (h23 * r2) + (h33 * r1) + (h43 * r0);
                int c6 = mpos3 >>> 13;
                int d42 = mpos3 & 8191;
                int d43 = d42 + (r9 * 5 * h53) + (r8 * 5 * h63) + (r7 * 5 * h73) + (r6 * 5 * h83) + (d9 * 5 * h93);
                int c7 = c6 + (d43 >>> 13);
                int d44 = d43 & 8191;
                int d5 = c7 + (h03 * d9);
                int d45 = d5 + (h13 * d0) + (h23 * r3) + (h33 * r2) + (h43 * r1);
                int c8 = d45 >>> 13;
                int d52 = d45 & 8191;
                int d53 = d52 + (h53 * r0) + (r9 * 5 * h63) + (r8 * 5 * h73) + (r7 * 5 * h83) + (r6 * 5 * h93);
                int c9 = c8 + (d53 >>> 13);
                int d54 = d53 & 8191;
                int d6 = c9 + (h03 * r6);
                int d55 = d6 + (h13 * d9) + (h23 * d0) + (h33 * r3) + (h43 * r2);
                int c10 = d55 >>> 13;
                int d62 = d55 & 8191;
                int d63 = d62 + (h53 * r1) + (h63 * r0) + (r9 * 5 * h73) + (r8 * 5 * h83) + (r7 * 5 * h93);
                int c11 = c10 + (d63 >>> 13);
                int d64 = d63 & 8191;
                int d7 = c11 + (h03 * r7);
                int d65 = d7 + (h13 * r6) + (h23 * d9) + (h33 * d0) + (h43 * r3);
                int c12 = d65 >>> 13;
                int d72 = d65 & 8191;
                int d73 = d72 + (h53 * r2) + (h63 * r1) + (h73 * r0) + (r9 * 5 * h83) + (r8 * 5 * h93);
                int c13 = c12 + (d73 >>> 13);
                int d74 = d73 & 8191;
                int d82 = c13 + (h03 * r8);
                int d75 = d82 + (h13 * r7) + (h23 * r6) + (h33 * d9) + (h43 * d0);
                int c14 = d75 >>> 13;
                int d83 = d75 & 8191;
                int d84 = d83 + (h53 * r3) + (h63 * r2) + (h73 * r1) + (h83 * r0) + (r9 * 5 * h93);
                int d92 = c14 + (d84 >>> 13) + (h03 * r9);
                int r5 = d9;
                int r52 = d92 + (h13 * r8) + (h23 * r7) + (h33 * r6) + (h43 * d9);
                int c15 = r52 >>> 13;
                int d93 = r52 & 8191;
                int d94 = d93 + (h53 * d0) + (h63 * r3) + (h73 * r2) + (h83 * r1) + (h93 * r0);
                int c16 = c15 + (d94 >>> 13);
                int d05 = ((((c16 << 2) + c16) | 0) + (d04 & 8191)) | 0;
                int c17 = d0;
                int r4 = d05 & 8191;
                int c18 = d05 >>> 13;
                h02 = r4;
                h12 = (d13 & 8191) + c18;
                h22 = d24 & 8191;
                h32 = d35 & 8191;
                h42 = d44;
                h52 = d54;
                h62 = d64;
                h72 = d74;
                h82 = d84 & 8191;
                h92 = d94 & 8191;
                d0 = c17;
                d2 = r1;
                d8 = mpos2 + 16;
                hibit = hibit2;
                d3 = bytes2 - 16;
                d9 = r5;
            }
            this.h[0] = h02;
            this.h[1] = h12;
            this.h[2] = h22;
            this.h[3] = h32;
            this.h[4] = h42;
            this.h[5] = h52;
            this.h[6] = h62;
            this.h[7] = h72;
            this.h[8] = h82;
            this.h[9] = h92;
            return this;
        }

        public poly1305 finish(byte[] mac, int macpos) {
            int[] g = new int[10];
            if (this.leftover != 0) {
                int i = this.leftover;
                this.buffer[i] = 1;
                for (int i2 = i + 1; i2 < 16; i2++) {
                    this.buffer[i2] = 0;
                }
                this.fin = 1;
                blocks(this.buffer, 0, 16);
            }
            int c = this.h[1] >>> 13;
            int[] iArr = this.h;
            iArr[1] = iArr[1] & 8191;
            for (int i3 = 2; i3 < 10; i3++) {
                int[] iArr2 = this.h;
                iArr2[i3] = iArr2[i3] + c;
                c = this.h[i3] >>> 13;
                int[] iArr3 = this.h;
                iArr3[i3] = iArr3[i3] & 8191;
            }
            int[] iArr4 = this.h;
            iArr4[0] = iArr4[0] + (c * 5);
            int c2 = this.h[0] >>> 13;
            int[] iArr5 = this.h;
            iArr5[0] = iArr5[0] & 8191;
            int[] iArr6 = this.h;
            iArr6[1] = iArr6[1] + c2;
            int c3 = this.h[1] >>> 13;
            int[] iArr7 = this.h;
            iArr7[1] = iArr7[1] & 8191;
            int[] iArr8 = this.h;
            iArr8[2] = iArr8[2] + c3;
            g[0] = this.h[0] + 5;
            int c4 = g[0] >>> 13;
            g[0] = g[0] & 8191;
            for (int i4 = 1; i4 < 10; i4++) {
                g[i4] = this.h[i4] + c4;
                c4 = g[i4] >>> 13;
                g[i4] = g[i4] & 8191;
            }
            g[9] = g[9] - 8192;
            g[9] = g[9] & 65535;
            int mask = ((c4 ^ 1) - 1) & 65535;
            for (int i5 = 0; i5 < 10; i5++) {
                g[i5] = g[i5] & mask;
            }
            int mask2 = ~mask;
            for (int i6 = 0; i6 < 10; i6++) {
                this.h[i6] = (this.h[i6] & mask2) | g[i6];
            }
            this.h[0] = (this.h[0] | (this.h[1] << 13)) & 65535;
            this.h[1] = ((this.h[2] << 10) | (this.h[1] >>> 3)) & 65535;
            this.h[2] = ((this.h[2] >>> 6) | (this.h[3] << 7)) & 65535;
            this.h[3] = ((this.h[3] >>> 9) | (this.h[4] << 4)) & 65535;
            this.h[4] = ((this.h[4] >>> 12) | (this.h[5] << 1) | (this.h[6] << 14)) & 65535;
            this.h[5] = ((this.h[6] >>> 2) | (this.h[7] << 11)) & 65535;
            this.h[6] = ((this.h[7] >>> 5) | (this.h[8] << 8)) & 65535;
            this.h[7] = ((this.h[9] << 5) | (this.h[8] >>> 8)) & 65535;
            int f = this.h[0] + this.pad[0];
            this.h[0] = f & 65535;
            for (int i7 = 1; i7 < 8; i7++) {
                f = (((this.h[i7] + this.pad[i7]) | 0) + (f >>> 16)) | 0;
                this.h[i7] = f & 65535;
            }
            mac[macpos + 0] = (byte) ((this.h[0] >>> 0) & 255);
            mac[macpos + 1] = (byte) ((this.h[0] >>> 8) & 255);
            mac[macpos + 2] = (byte) ((this.h[1] >>> 0) & 255);
            mac[macpos + 3] = (byte) ((this.h[1] >>> 8) & 255);
            mac[macpos + 4] = (byte) ((this.h[2] >>> 0) & 255);
            mac[macpos + 5] = (byte) ((this.h[2] >>> 8) & 255);
            mac[macpos + 6] = (byte) ((this.h[3] >>> 0) & 255);
            mac[macpos + 7] = (byte) ((this.h[3] >>> 8) & 255);
            mac[macpos + 8] = (byte) ((this.h[4] >>> 0) & 255);
            mac[macpos + 9] = (byte) ((this.h[4] >>> 8) & 255);
            mac[macpos + 10] = (byte) ((this.h[5] >>> 0) & 255);
            mac[macpos + 11] = (byte) ((this.h[5] >>> 8) & 255);
            mac[macpos + 12] = (byte) ((this.h[6] >>> 0) & 255);
            mac[macpos + 13] = (byte) ((this.h[6] >>> 8) & 255);
            mac[macpos + 14] = (byte) ((this.h[7] >>> 0) & 255);
            mac[macpos + 15] = (byte) ((this.h[7] >>> 8) & 255);
            return this;
        }

        public poly1305 update(byte[] m, int mpos, int bytes) {
            if (this.leftover != 0) {
                int want = 16 - this.leftover;
                if (want > bytes) {
                    want = bytes;
                }
                for (int i = 0; i < want; i++) {
                    this.buffer[this.leftover + i] = m[mpos + i];
                }
                bytes -= want;
                mpos += want;
                this.leftover += want;
                if (this.leftover < 16) {
                    return this;
                }
                blocks(this.buffer, 0, 16);
                this.leftover = 0;
            }
            if (bytes >= 16) {
                int want2 = bytes - (bytes % 16);
                blocks(m, mpos, want2);
                mpos += want2;
                bytes -= want2;
            }
            if (bytes != 0) {
                for (int i2 = 0; i2 < bytes; i2++) {
                    this.buffer[this.leftover + i2] = m[mpos + i2];
                }
                this.leftover += bytes;
            }
            return this;
        }
    }

    private static int crypto_onetimeauth(byte[] out, int outpos, byte[] m, int mpos, int n, byte[] k) {
        poly1305 s = new poly1305(k);
        s.update(m, mpos, n);
        s.finish(out, outpos);
        return 0;
    }

    private static int crypto_onetimeauth_verify(byte[] h, int hoff, byte[] m, int moff, int n, byte[] k) {
        byte[] x = new byte[16];
        crypto_onetimeauth(x, 0, m, moff, n, k);
        return crypto_verify_16(h, hoff, x, 0);
    }

    public static int crypto_secretbox_open(byte[] m, byte[] c, int d, byte[] n, byte[] k) {
        byte[] x = new byte[32];
        if (d < 32) {
            return -1;
        }
        crypto_stream(x, 0, 32L, n, k);
        if (crypto_onetimeauth_verify(c, 16, c, 32, d - 32, x) != 0) {
            return -1;
        }
        crypto_stream_xor(m, 0, c, 0, d, n, k);
        return 0;
    }
}
