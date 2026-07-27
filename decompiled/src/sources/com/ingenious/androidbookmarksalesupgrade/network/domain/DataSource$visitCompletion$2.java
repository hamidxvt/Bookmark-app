package com.ingenious.androidbookmarksalesupgrade.network.domain;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitCompletionResponse;
import com.ingenious.androidbookmarksalesupgrade.network.SoService;
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
import retrofit2.Response;

/* compiled from: DataSource.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lretrofit2/Response;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitCompletionResponse;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.network.domain.DataSource$visitCompletion$2", f = "DataSource.kt", i = {}, l = {99}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes15.dex */
final class DataSource$visitCompletion$2 extends SuspendLambda implements Function1<Continuation<? super Response<VisitCompletionResponse>>, Object> {
    final /* synthetic */ ArrayList<MultipartBody.Part> $images;
    final /* synthetic */ MultipartBody.Part $invoice;
    final /* synthetic */ RequestBody $invoiceType;
    final /* synthetic */ RequestBody $remarks;
    final /* synthetic */ MultipartBody.Part $signature;
    final /* synthetic */ RequestBody $visitId;
    int label;
    final /* synthetic */ DataSource this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DataSource$visitCompletion$2(DataSource dataSource, RequestBody requestBody, RequestBody requestBody2, RequestBody requestBody3, MultipartBody.Part part, ArrayList<MultipartBody.Part> arrayList, MultipartBody.Part part2, Continuation<? super DataSource$visitCompletion$2> continuation) {
        super(1, continuation);
        this.this$0 = dataSource;
        this.$visitId = requestBody;
        this.$remarks = requestBody2;
        this.$invoiceType = requestBody3;
        this.$signature = part;
        this.$images = arrayList;
        this.$invoice = part2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new DataSource$visitCompletion$2(this.this$0, this.$visitId, this.$remarks, this.$invoiceType, this.$signature, this.$images, this.$invoice, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Response<VisitCompletionResponse>> continuation) {
        return ((DataSource$visitCompletion$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        SoService soService;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                soService = this.this$0.apiService;
                this.label = 1;
                Object visitCompletion = soService.visitCompletion(this.$visitId, this.$remarks, this.$invoiceType, this.$signature, this.$images, this.$invoice, this);
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
