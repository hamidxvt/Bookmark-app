package com.ingenious.androidbookmarksalesupgrade.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.model.response.SegmentsListResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.network.domain.DataSource;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* compiled from: VisitRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/SegmentsListResponse;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.repository.VisitRepository$segmentsList$2", f = "VisitRepository.kt", i = {}, l = {118}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes15.dex */
final class VisitRepository$segmentsList$2 extends SuspendLambda implements Function1<Continuation<? super ApiResponseCallback<SegmentsListResponse>>, Object> {
    int label;
    final /* synthetic */ VisitRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VisitRepository$segmentsList$2(VisitRepository visitRepository, Continuation<? super VisitRepository$segmentsList$2> continuation) {
        super(1, continuation);
        this.this$0 = visitRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new VisitRepository$segmentsList$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super ApiResponseCallback<SegmentsListResponse>> continuation) {
        return ((VisitRepository$segmentsList$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        DataSource dataSource;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                dataSource = this.this$0.remoteDataSource;
                this.label = 1;
                Object segmentsList = dataSource.segmentsList(this);
                if (segmentsList == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return segmentsList;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
