package com.ingenious.androidbookmarksalesupgrade.network.domain;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.model.response.CustomersListResponse;
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
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lretrofit2/Response;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/CustomersListResponse;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.network.domain.DataSource$customersList$2", f = "DataSource.kt", i = {}, l = {161}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes15.dex */
final class DataSource$customersList$2 extends SuspendLambda implements Function1<Continuation<? super Response<CustomersListResponse>>, Object> {
    final /* synthetic */ String $addedBy;
    final /* synthetic */ String $area;
    final /* synthetic */ String $lastVisit;
    final /* synthetic */ String $search;
    final /* synthetic */ String $type;
    int label;
    final /* synthetic */ DataSource this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DataSource$customersList$2(DataSource dataSource, String str, String str2, String str3, String str4, String str5, Continuation<? super DataSource$customersList$2> continuation) {
        super(1, continuation);
        this.this$0 = dataSource;
        this.$type = str;
        this.$search = str2;
        this.$area = str3;
        this.$lastVisit = str4;
        this.$addedBy = str5;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new DataSource$customersList$2(this.this$0, this.$type, this.$search, this.$area, this.$lastVisit, this.$addedBy, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Response<CustomersListResponse>> continuation) {
        return ((DataSource$customersList$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
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
                Object customersList = soService.customersList(this.$type, this.$search, this.$area, this.$lastVisit, this.$addedBy, this);
                if (customersList == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return customersList;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
