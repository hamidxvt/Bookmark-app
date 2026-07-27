package org.koin.core.qualifier;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StringQualifier.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\b\u001a\u00060\u0003j\u0002`\u0004HÆ\u0003J\u0017\u0010\t\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\b\u0010\u0010\u001a\u00020\u0003H\u0016R\u0018\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/koin/core/qualifier/StringQualifier;", "Lorg/koin/core/qualifier/Qualifier;", "value", "", "Lorg/koin/core/qualifier/QualifierValue;", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "koin-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final /* data */ class StringQualifier implements Qualifier {
    private final String value;

    public static /* synthetic */ StringQualifier copy$default(StringQualifier stringQualifier, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = stringQualifier.getValue();
        }
        return stringQualifier.copy(str);
    }

    public final String component1() {
        return getValue();
    }

    public final StringQualifier copy(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new StringQualifier(value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof StringQualifier) && Intrinsics.areEqual(getValue(), ((StringQualifier) other).getValue());
    }

    public int hashCode() {
        return getValue().hashCode();
    }

    public StringQualifier(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
    }

    @Override // org.koin.core.qualifier.Qualifier
    public String getValue() {
        return this.value;
    }

    public String toString() {
        return getValue();
    }
}
