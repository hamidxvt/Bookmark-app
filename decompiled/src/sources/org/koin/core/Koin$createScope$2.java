package org.koin.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.koin.core.qualifier.TypeQualifier;

/* compiled from: Koin.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "T", ""}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class Koin$createScope$2 extends Lambda implements Function0<String> {
    final /* synthetic */ TypeQualifier $qualifier;
    final /* synthetic */ String $scopeId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Koin$createScope$2(String str, TypeQualifier typeQualifier) {
        super(0);
        this.$scopeId = str;
        this.$qualifier = typeQualifier;
    }

    @Override // kotlin.jvm.functions.Function0
    public final String invoke() {
        return "|- create scope - id:'" + this.$scopeId + "' q:" + this.$qualifier;
    }
}
