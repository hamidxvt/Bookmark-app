package com.google.thirdparty.publicsuffix;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Queues;
import java.util.Deque;

/* loaded from: classes16.dex */
final class TrieParser {
    private static final Joiner PREFIX_JOINER = Joiner.on("");

    TrieParser() {
    }

    static ImmutableMap<String, PublicSuffixType> parseTrie(CharSequence encoded) {
        ImmutableMap.Builder<String, PublicSuffixType> builder = ImmutableMap.builder();
        int encodedLen = encoded.length();
        int idx = 0;
        while (idx < encodedLen) {
            idx += doParseTrieToBuilder(Queues.newArrayDeque(), encoded, idx, builder);
        }
        return builder.buildOrThrow();
    }

    private static int doParseTrieToBuilder(Deque<CharSequence> stack, CharSequence encoded, int start, ImmutableMap.Builder<String, PublicSuffixType> builder) {
        int encodedLen = encoded.length();
        int idx = start;
        char c = 0;
        while (idx < encodedLen && (c = encoded.charAt(idx)) != '&' && c != '?' && c != '!' && c != ':' && c != ',') {
            idx++;
        }
        stack.push(reverse(encoded.subSequence(start, idx)));
        if (c == '!' || c == '?' || c == ':' || c == ',') {
            String domain = PREFIX_JOINER.join(stack);
            if (domain.length() > 0) {
                builder.put(domain, PublicSuffixType.fromCode(c));
            }
        }
        int idx2 = idx + 1;
        if (c != '?' && c != ',') {
            while (idx2 < encodedLen) {
                idx2 += doParseTrieToBuilder(stack, encoded, idx2, builder);
                if (encoded.charAt(idx2) == '?' || encoded.charAt(idx2) == ',') {
                    idx2++;
                    break;
                }
            }
        }
        stack.pop();
        return idx2 - start;
    }

    private static CharSequence reverse(CharSequence s) {
        return new StringBuilder(s).reverse();
    }
}
