package com.ingenious.androidbookmarksalesupgrade.viewModel;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import com.ingenious.androidbookmarksalesupgrade.repository.AppRepository;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MainViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.viewModel.MainViewModel$homeRequest$1", f = "MainViewModel.kt", i = {}, l = {ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
final class MainViewModel$homeRequest$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $addedBy;
    final /* synthetic */ String $currentDate;
    final /* synthetic */ String $customerType;
    final /* synthetic */ String $distance;
    final /* synthetic */ String $latitude;
    final /* synthetic */ String $longitude;
    final /* synthetic */ String $priority;
    Object L$0;
    int label;
    final /* synthetic */ MainViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainViewModel$homeRequest$1(MainViewModel mainViewModel, String str, String str2, String str3, String str4, String str5, String str6, String str7, Continuation<? super MainViewModel$homeRequest$1> continuation) {
        super(2, continuation);
        this.this$0 = mainViewModel;
        this.$currentDate = str;
        this.$latitude = str2;
        this.$longitude = str3;
        this.$priority = str4;
        this.$distance = str5;
        this.$customerType = str6;
        this.$addedBy = str7;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainViewModel$homeRequest$1(this.this$0, this.$currentDate, this.$latitude, this.$longitude, this.$priority, this.$distance, this.$customerType, this.$addedBy, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainViewModel$homeRequest$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        MutableLiveData mutableLiveData;
        AppRepository appRepository;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                mutableLiveData = this.this$0._homeResponse;
                appRepository = this.this$0.repository;
                this.L$0 = mutableLiveData;
                this.label = 1;
                Object home = appRepository.getHomeRepository().home(this.$currentDate, this.$latitude, this.$longitude, this.$priority, this.$distance, this.$customerType, this.$addedBy, this);
                if (home != coroutine_suspended) {
                    $result = home;
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                MutableLiveData mutableLiveData2 = (MutableLiveData) this.L$0;
                ResultKt.throwOnFailure($result);
                mutableLiveData = mutableLiveData2;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        mutableLiveData.setValue($result);
        return Unit.INSTANCE;
    }
}
