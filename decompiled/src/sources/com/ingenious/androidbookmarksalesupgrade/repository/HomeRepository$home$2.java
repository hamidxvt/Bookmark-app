package com.ingenious.androidbookmarksalesupgrade.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.model.response.HomeResponse;
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

/* compiled from: HomeRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/HomeResponse;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.repository.HomeRepository$home$2", f = "HomeRepository.kt", i = {}, l = {19}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes15.dex */
final class HomeRepository$home$2 extends SuspendLambda implements Function1<Continuation<? super ApiResponseCallback<HomeResponse>>, Object> {
    final /* synthetic */ String $addedBy;
    final /* synthetic */ String $currentDate;
    final /* synthetic */ String $customerType;
    final /* synthetic */ String $distance;
    final /* synthetic */ String $latitude;
    final /* synthetic */ String $longitude;
    final /* synthetic */ String $priority;
    int label;
    final /* synthetic */ HomeRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HomeRepository$home$2(HomeRepository homeRepository, String str, String str2, String str3, String str4, String str5, String str6, String str7, Continuation<? super HomeRepository$home$2> continuation) {
        super(1, continuation);
        this.this$0 = homeRepository;
        this.$currentDate = str;
        this.$latitude = str2;
        this.$longitude = str3;
        this.$priority = str4;
        this.$distance = str5;
        this.$customerType = str6;
        this.$addedBy = str7;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new HomeRepository$home$2(this.this$0, this.$currentDate, this.$latitude, this.$longitude, this.$priority, this.$distance, this.$customerType, this.$addedBy, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super ApiResponseCallback<HomeResponse>> continuation) {
        return ((HomeRepository$home$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
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
                Object home = dataSource.home(this.$currentDate, this.$latitude, this.$longitude, this.$priority, this.$distance, this.$customerType, this.$addedBy, this);
                if (home == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return home;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
