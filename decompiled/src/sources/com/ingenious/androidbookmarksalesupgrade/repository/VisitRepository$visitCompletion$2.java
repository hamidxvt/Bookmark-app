package com.ingenious.androidbookmarksalesupgrade.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitCompletionResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.network.domain.DataSource;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/* compiled from: VisitRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitCompletionResponse;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.repository.VisitRepository$visitCompletion$2", f = "VisitRepository.kt", i = {}, l = {64}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes15.dex */
final class VisitRepository$visitCompletion$2 extends SuspendLambda implements Function1<Continuation<? super ApiResponseCallback<VisitCompletionResponse>>, Object> {
    final /* synthetic */ ArrayList<MultipartBody.Part> $images;
    final /* synthetic */ MultipartBody.Part $invoice;
    final /* synthetic */ RequestBody $invoiceType;
    final /* synthetic */ RequestBody $remarks;
    final /* synthetic */ MultipartBody.Part $signature;
    final /* synthetic */ RequestBody $visitId;
    int label;
    final /* synthetic */ VisitRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VisitRepository$visitCompletion$2(VisitRepository visitRepository, RequestBody requestBody, RequestBody requestBody2, RequestBody requestBody3, MultipartBody.Part part, ArrayList<MultipartBody.Part> arrayList, MultipartBody.Part part2, Continuation<? super VisitRepository$visitCompletion$2> continuation) {
        super(1, continuation);
        this.this$0 = visitRepository;
        this.$visitId = requestBody;
        this.$remarks = requestBody2;
        this.$invoiceType = requestBody3;
        this.$signature = part;
        this.$images = arrayList;
        this.$invoice = part2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new VisitRepository$visitCompletion$2(this.this$0, this.$visitId, this.$remarks, this.$invoiceType, this.$signature, this.$images, this.$invoice, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super ApiResponseCallback<VisitCompletionResponse>> continuation) {
        return ((VisitRepository$visitCompletion$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
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
                Object visitCompletion = dataSource.visitCompletion(this.$visitId, this.$remarks, this.$invoiceType, this.$signature, this.$images, this.$invoice, this);
                if (visitCompletion == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return visitCompletion;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
