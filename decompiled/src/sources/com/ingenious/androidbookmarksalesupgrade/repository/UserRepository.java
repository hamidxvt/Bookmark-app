package com.ingenious.androidbookmarksalesupgrade.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.Scopes;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils;
import com.ingenious.androidbookmarksalesupgrade.model.request.ForgetRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.LoginRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.ResetPasswordRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.VerifyOtpRequest;
import com.ingenious.androidbookmarksalesupgrade.model.response.GlobalResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.LoginResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.ProfileResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.network.domain.DataAccessStrategyKt;
import com.ingenious.androidbookmarksalesupgrade.network.domain.DataSource;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* compiled from: UserRepository.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0086@¢\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u0010\u0010J\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@¢\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u00072\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@¢\u0006\u0002\u0010\u0018J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0007H\u0086@¢\u0006\u0002\u0010\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/repository/UserRepository;", "", "<init>", "()V", "remoteDataSource", "Lcom/ingenious/androidbookmarksalesupgrade/network/domain/DataSource;", FirebaseAnalytics.Event.LOGIN, "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LoginResponse;", "loginRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/LoginRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "forgetPassword", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;", "forgetRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/ForgetRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/ForgetRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyOtp", "verifyOtpRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/VerifyOtpRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/VerifyOtpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetPassword", "resetPasswordRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/ResetPasswordRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/ResetPasswordRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", Scopes.PROFILE, "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ProfileResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes15.dex */
public final class UserRepository {
    private DataSource remoteDataSource = InjectUtils.INSTANCE.getDataSource();

    public final Object login(LoginRequest loginRequest, Continuation<? super ApiResponseCallback<LoginResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new UserRepository$login$2(this, loginRequest, null), new UserRepository$login$3(null), continuation);
    }

    public final Object forgetPassword(ForgetRequest forgetRequest, Continuation<? super ApiResponseCallback<GlobalResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new UserRepository$forgetPassword$2(this, forgetRequest, null), continuation);
    }

    public final Object verifyOtp(VerifyOtpRequest verifyOtpRequest, Continuation<? super ApiResponseCallback<GlobalResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new UserRepository$verifyOtp$2(this, verifyOtpRequest, null), continuation);
    }

    public final Object resetPassword(ResetPasswordRequest resetPasswordRequest, Continuation<? super ApiResponseCallback<GlobalResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new UserRepository$resetPassword$2(this, resetPasswordRequest, null), continuation);
    }

    public final Object profile(Continuation<? super ApiResponseCallback<ProfileResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new UserRepository$profile$2(this, null), continuation);
    }
}
