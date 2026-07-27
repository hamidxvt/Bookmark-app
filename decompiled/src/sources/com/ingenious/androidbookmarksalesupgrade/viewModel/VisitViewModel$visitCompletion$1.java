package com.ingenious.androidbookmarksalesupgrade.viewModel;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import com.ingenious.androidbookmarksalesupgrade.repository.AppRepository;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: VisitViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel$visitCompletion$1", f = "VisitViewModel.kt", i = {}, l = {WebSocketProtocol.PAYLOAD_SHORT}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
final class VisitViewModel$visitCompletion$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ArrayList<MultipartBody.Part> $images;
    final /* synthetic */ MultipartBody.Part $invoice;
    final /* synthetic */ RequestBody $invoiceType;
    final /* synthetic */ RequestBody $remarks;
    final /* synthetic */ MultipartBody.Part $signature;
    final /* synthetic */ RequestBody $visitId;
    Object L$0;
    int label;
    final /* synthetic */ VisitViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VisitViewModel$visitCompletion$1(VisitViewModel visitViewModel, RequestBody requestBody, RequestBody requestBody2, RequestBody requestBody3, MultipartBody.Part part, ArrayList<MultipartBody.Part> arrayList, MultipartBody.Part part2, Continuation<? super VisitViewModel$visitCompletion$1> continuation) {
        super(2, continuation);
        this.this$0 = visitViewModel;
        this.$visitId = requestBody;
        this.$remarks = requestBody2;
        this.$invoiceType = requestBody3;
        this.$signature = part;
        this.$images = arrayList;
        this.$invoice = part2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VisitViewModel$visitCompletion$1(this.this$0, this.$visitId, this.$remarks, this.$invoiceType, this.$signature, this.$images, this.$invoice, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VisitViewModel$visitCompletion$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        MutableLiveData mutableLiveData;
        AppRepository appRepository;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                mutableLiveData = this.this$0._visitCompletionResponse;
                appRepository = this.this$0.repository;
                this.L$0 = mutableLiveData;
                this.label = 1;
                Object visitCompletion = appRepository.getVisitRepository().visitCompletion(this.$visitId, this.$remarks, this.$invoiceType, this.$signature, this.$images, this.$invoice, this);
                if (visitCompletion != coroutine_suspended) {
                    $result = visitCompletion;
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
