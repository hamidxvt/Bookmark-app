package com.ingenious.androidbookmarksalesupgrade.network.domain;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: BaseDataSource.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.network.domain.BaseDataSource", f = "BaseDataSource.kt", i = {0}, l = {18}, m = "callApi", n = {"exception"}, s = {"L$0"})
/* loaded from: classes15.dex */
final class BaseDataSource$callApi$1<T> extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BaseDataSource this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BaseDataSource$callApi$1(BaseDataSource baseDataSource, Continuation<? super BaseDataSource$callApi$1> continuation) {
        super(continuation);
        this.this$0 = baseDataSource;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.callApi(null, this);
    }
}
