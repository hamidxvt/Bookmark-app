package com.ingenious.androidbookmarksalesupgrade.network.domain;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: DataAccessStrategy.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.network.domain.DataAccessStrategyKt", f = "DataAccessStrategy.kt", i = {0, 1}, l = {ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS, 31}, m = "performNetworkCallOperation", n = {"saveCallResult", "response"}, s = {"L$0", "L$0"})
/* loaded from: classes15.dex */
final class DataAccessStrategyKt$performNetworkCallOperation$3<T> extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    DataAccessStrategyKt$performNetworkCallOperation$3(Continuation<? super DataAccessStrategyKt$performNetworkCallOperation$3> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DataAccessStrategyKt.performNetworkCallOperation(null, null, this);
    }
}
