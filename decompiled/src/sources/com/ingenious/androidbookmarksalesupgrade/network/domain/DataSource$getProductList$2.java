package com.ingenious.androidbookmarksalesupgrade.network.domain;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.model.response.ProductListResponse;
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
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lretrofit2/Response;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ProductListResponse;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.network.domain.DataSource$getProductList$2", f = "DataSource.kt", i = {}, l = {122}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes15.dex */
final class DataSource$getProductList$2 extends SuspendLambda implements Function1<Continuation<? super Response<ProductListResponse>>, Object> {
    final /* synthetic */ String $brandId;
    final /* synthetic */ String $customerId;
    final /* synthetic */ int $page;
    final /* synthetic */ String $search;
    final /* synthetic */ String $seriesId;
    final /* synthetic */ String $subjectId;
    int label;
    final /* synthetic */ DataSource this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DataSource$getProductList$2(DataSource dataSource, String str, int i, String str2, String str3, String str4, String str5, Continuation<? super DataSource$getProductList$2> continuation) {
        super(1, continuation);
        this.this$0 = dataSource;
        this.$search = str;
        this.$page = i;
        this.$customerId = str2;
        this.$subjectId = str3;
        this.$brandId = str4;
        this.$seriesId = str5;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new DataSource$getProductList$2(this.this$0, this.$search, this.$page, this.$customerId, this.$subjectId, this.$brandId, this.$seriesId, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Response<ProductListResponse>> continuation) {
        return ((DataSource$getProductList$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
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
                Object productList = soService.getProductList(this.$search, this.$page, this.$customerId, this.$subjectId, this.$brandId, this.$seriesId, this);
                if (productList == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return productList;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
