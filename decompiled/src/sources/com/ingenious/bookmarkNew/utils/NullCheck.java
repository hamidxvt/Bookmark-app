package com.ingenious.bookmarkNew.utils;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NullCheck.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J[\u0010\u0004\u001a\u0004\u0018\u0001H\u0005\"\b\b\u0000\u0010\u0006*\u00020\u0001\"\b\b\u0001\u0010\u0007*\u00020\u0001\"\b\b\u0002\u0010\u0005*\u00020\u00012\b\u0010\b\u001a\u0004\u0018\u0001H\u00062\b\u0010\t\u001a\u0004\u0018\u0001H\u00072\u001a\u0010\n\u001a\u0016\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00050\u000b¢\u0006\u0002\u0010\fJu\u0010\u0004\u001a\u0004\u0018\u0001H\u0005\"\b\b\u0000\u0010\u0006*\u00020\u0001\"\b\b\u0001\u0010\u0007*\u00020\u0001\"\b\b\u0002\u0010\r*\u00020\u0001\"\b\b\u0003\u0010\u0005*\u00020\u00012\b\u0010\b\u001a\u0004\u0018\u0001H\u00062\b\u0010\t\u001a\u0004\u0018\u0001H\u00072\b\u0010\u000e\u001a\u0004\u0018\u0001H\r2 \u0010\n\u001a\u001c\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\r\u0012\u0006\u0012\u0004\u0018\u0001H\u00050\u000f¢\u0006\u0002\u0010\u0010J\u008f\u0001\u0010\u0004\u001a\u0004\u0018\u0001H\u0005\"\b\b\u0000\u0010\u0006*\u00020\u0001\"\b\b\u0001\u0010\u0007*\u00020\u0001\"\b\b\u0002\u0010\r*\u00020\u0001\"\b\b\u0003\u0010\u0011*\u00020\u0001\"\b\b\u0004\u0010\u0005*\u00020\u00012\b\u0010\b\u001a\u0004\u0018\u0001H\u00062\b\u0010\t\u001a\u0004\u0018\u0001H\u00072\b\u0010\u000e\u001a\u0004\u0018\u0001H\r2\b\u0010\u0012\u001a\u0004\u0018\u0001H\u00112&\u0010\n\u001a\"\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u0011\u0012\u0006\u0012\u0004\u0018\u0001H\u00050\u0013¢\u0006\u0002\u0010\u0014J©\u0001\u0010\u0004\u001a\u0004\u0018\u0001H\u0005\"\b\b\u0000\u0010\u0006*\u00020\u0001\"\b\b\u0001\u0010\u0007*\u00020\u0001\"\b\b\u0002\u0010\r*\u00020\u0001\"\b\b\u0003\u0010\u0011*\u00020\u0001\"\b\b\u0004\u0010\u0015*\u00020\u0001\"\b\b\u0005\u0010\u0005*\u00020\u00012\b\u0010\b\u001a\u0004\u0018\u0001H\u00062\b\u0010\t\u001a\u0004\u0018\u0001H\u00072\b\u0010\u000e\u001a\u0004\u0018\u0001H\r2\b\u0010\u0012\u001a\u0004\u0018\u0001H\u00112\b\u0010\u0016\u001a\u0004\u0018\u0001H\u00152,\u0010\n\u001a(\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u0015\u0012\u0006\u0012\u0004\u0018\u0001H\u00050\u0017¢\u0006\u0002\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/ingenious/bookmarkNew/utils/NullCheck;", "", "<init>", "()V", "safeLet", "R", "T1", "T2", "p1", "p2", "block", "Lkotlin/Function2;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "T3", "p3", "Lkotlin/Function3;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "T4", "p4", "Lkotlin/Function4;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "T5", "p5", "Lkotlin/Function5;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes13.dex */
public final class NullCheck {
    public static final NullCheck INSTANCE = new NullCheck();

    private NullCheck() {
    }

    public final <T1, T2, R> R safeLet(T1 p1, T2 p2, Function2<? super T1, ? super T2, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        if (p1 == null || p2 == null) {
            return null;
        }
        return block.invoke(p1, p2);
    }

    public final <T1, T2, T3, R> R safeLet(T1 p1, T2 p2, T3 p3, Function3<? super T1, ? super T2, ? super T3, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        if (p1 == null || p2 == null || p3 == null) {
            return null;
        }
        return block.invoke(p1, p2, p3);
    }

    public final <T1, T2, T3, T4, R> R safeLet(T1 p1, T2 p2, T3 p3, T4 p4, Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        if (p1 != null && p2 != null && p3 != null && p4 != null) {
            return block.invoke(p1, p2, p3, p4);
        }
        return null;
    }

    public final <T1, T2, T3, T4, T5, R> R safeLet(T1 p1, T2 p2, T3 p3, T4 p4, T5 p5, Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        if (p1 != null && p2 != null && p3 != null && p4 != null && p5 != null) {
            return block.invoke(p1, p2, p3, p4, p5);
        }
        return null;
    }
}
