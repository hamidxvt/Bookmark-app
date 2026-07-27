package org.koin.ext;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: StringExt.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001¨\u0006\u0002"}, d2 = {"clearQuotes", "", "koin-core"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class StringExtKt {
    public static final String clearQuotes(String $this$clearQuotes) {
        Intrinsics.checkNotNullParameter($this$clearQuotes, "<this>");
        if ($this$clearQuotes.length() <= 1 || StringsKt.first($this$clearQuotes) != '\"' || StringsKt.last($this$clearQuotes) != '\"') {
            return $this$clearQuotes;
        }
        String substring = $this$clearQuotes.substring(1, StringsKt.getLastIndex($this$clearQuotes));
        Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }
}
