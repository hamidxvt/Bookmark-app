package com.ingenious.androidbookmarksalesupgrade.network.domain;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.model.request.ForgetRequest;
import com.ingenious.androidbookmarksalesupgrade.model.response.GlobalResponse;
import com.ingenious.androidbookmarksalesupgrade.network.SoService;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import retrofit2.Response;

/* compiled from: DataSource.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lretrofit2/Response;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.network.domain.DataSource$forgetPassword$2", f = "DataSource.kt", i = {}, l = {35}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes15.dex */
final class DataSource$forgetPassword$2 extends SuspendLambda implements Function1<Continuation<? super Response<GlobalResponse>>, Object> {
    final /* synthetic */ ForgetRequest $forgetRequest;
    int label;
    final /* synthetic */ DataSource this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DataSource$forgetPassword$2(DataSource dataSource, ForgetRequest forgetRequest, Continuation<? super DataSource$forgetPassword$2> continuation) {
        super(1, continuation);
        this.this$0 = dataSource;
        this.$forgetRequest = forgetRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new DataSource$forgetPassword$2(this.this$0, this.$forgetRequest, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Response<GlobalResponse>> continuation) {
        return ((DataSource$forgetPassword$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
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
                Object forgetPassword = soService.forgetPassword(this.$forgetRequest, this);
                if (forgetPassword == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return forgetPassword;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
