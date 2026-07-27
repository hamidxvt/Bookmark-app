package com.ingenious.androidbookmarksalesupgrade.network.domain;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: DataAccessStrategy.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.network.domain.DataAccessStrategyKt", f = "DataAccessStrategy.kt", i = {}, l = {17}, m = "performNetworkCallOperation", n = {}, s = {})
/* loaded from: classes15.dex */
final class DataAccessStrategyKt$performNetworkCallOperation$1<T> extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    DataAccessStrategyKt$performNetworkCallOperation$1(Continuation<? super DataAccessStrategyKt$performNetworkCallOperation$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DataAccessStrategyKt.performNetworkCallOperation(null, this);
    }
}
