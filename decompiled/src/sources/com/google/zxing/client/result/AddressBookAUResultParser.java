package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes17.dex */
public final class AddressBookAUResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    public AddressBookParsedResult parse(Result result) {
        String rawText = getMassagedText(result);
        if (!rawText.contains("MEMORY") || !rawText.contains("\r\n")) {
            return null;
        }
        String name = matchSinglePrefixedField("NAME1:", rawText, CharUtils.CR, true);
        String pronunciation = matchSinglePrefixedField("NAME2:", rawText, CharUtils.CR, true);
        String[] phoneNumbers = matchMultipleValuePrefix("TEL", 3, rawText, true);
        String[] emails = matchMultipleValuePrefix("MAIL", 3, rawText, true);
        String note = matchSinglePrefixedField("MEMORY:", rawText, CharUtils.CR, false);
        String address = matchSinglePrefixedField("ADD:", rawText, CharUtils.CR, true);
        String[] addresses = address != null ? new String[]{address} : null;
        return new AddressBookParsedResult(maybeWrap(name), null, pronunciation, phoneNumbers, null, emails, null, null, note, addresses, null, null, null, null, null, null);
    }

    private static String[] matchMultipleValuePrefix(String prefix, int max, String rawText, boolean trim) {
        List<String> values = null;
        for (int i = 1; i <= max; i++) {
            String value = matchSinglePrefixedField(prefix + i + ':', rawText, CharUtils.CR, trim);
            if (value == null) {
                break;
            }
            if (values == null) {
                values = new ArrayList<>(max);
            }
            values.add(value);
        }
        if (values == null) {
            return null;
        }
        return (String[]) values.toArray(new String[values.size()]);
    }
}
