package com.ingenious.androidbookmarksalesupgrade.viewModel;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import com.ingenious.androidbookmarksalesupgrade.model.request.ResetPasswordRequest;
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

/* compiled from: UserViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.viewModel.UserViewModel$resetPasswordRequest$1", f = "UserViewModel.kt", i = {}, l = {72}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
final class UserViewModel$resetPasswordRequest$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ResetPasswordRequest $resetPasswordRequest;
    Object L$0;
    int label;
    final /* synthetic */ UserViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UserViewModel$resetPasswordRequest$1(UserViewModel userViewModel, ResetPasswordRequest resetPasswordRequest, Continuation<? super UserViewModel$resetPasswordRequest$1> continuation) {
        super(2, continuation);
        this.this$0 = userViewModel;
        this.$resetPasswordRequest = resetPasswordRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UserViewModel$resetPasswordRequest$1(this.this$0, this.$resetPasswordRequest, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UserViewModel$resetPasswordRequest$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        MutableLiveData mutableLiveData;
        AppRepository appRepository;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                mutableLiveData = this.this$0._resetPasswordResponse;
                appRepository = this.this$0.repository;
                this.L$0 = mutableLiveData;
                this.label = 1;
                Object resetPassword = appRepository.getUserRepository().resetPassword(this.$resetPasswordRequest, this);
                if (resetPassword != coroutine_suspended) {
                    $result = resetPassword;
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
