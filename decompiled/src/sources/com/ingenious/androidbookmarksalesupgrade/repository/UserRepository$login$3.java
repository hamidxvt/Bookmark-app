package com.ingenious.androidbookmarksalesupgrade.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.model.response.LoginResponse;
import com.ingenious.androidbookmarksalesupgrade.storage.AppPreferences;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UserRepository.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LoginResponse;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.repository.UserRepository$login$3", f = "UserRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes15.dex */
final class UserRepository$login$3 extends SuspendLambda implements Function2<LoginResponse, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;

    UserRepository$login$3(Continuation<? super UserRepository$login$3> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        UserRepository$login$3 userRepository$login$3 = new UserRepository$login$3(continuation);
        userRepository$login$3.L$0 = obj;
        return userRepository$login$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(LoginResponse loginResponse, Continuation<? super Unit> continuation) {
        return ((UserRepository$login$3) create(loginResponse, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                LoginResponse it = (LoginResponse) this.L$0;
                Boolean success = it.getSuccess();
                Intrinsics.checkNotNull(success);
                if (success.booleanValue()) {
                    AppPreferences.INSTANCE.setLoginData(it);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
