package com.ingenious.androidbookmarksalesupgrade.viewModel;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.google.android.gms.common.Scopes;
import com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils;
import com.ingenious.androidbookmarksalesupgrade.model.MessageListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.request.ForgetRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.LoginRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.ResetPasswordRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.SendMessageRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.VerifyOtpRequest;
import com.ingenious.androidbookmarksalesupgrade.model.response.GlobalResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.LoginResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.ProfileResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.repository.AppRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: UserViewModel.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u0010J\u000e\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010%\u001a\u00020\u000fJ\u000e\u0010)\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020*J\u000e\u0010/\u001a\u00020\u000f2\u0006\u00100\u001a\u000201R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\b0\u000b8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\rR\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\b0\u000b8F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\rR\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\b0\u000b8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\rR\u001a\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\b0\u000b8F¢\u0006\u0006\u001a\u0004\b$\u0010\rR\u001a\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\b0\u000b8F¢\u0006\u0006\u001a\u0004\b(\u0010\rR\u001a\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0\b0\u000b8F¢\u0006\u0006\u001a\u0004\b.\u0010\r¨\u00062"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/viewModel/UserViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "repository", "Lcom/ingenious/androidbookmarksalesupgrade/repository/AppRepository;", "_loginResponse", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LoginResponse;", "loginResponse", "Landroidx/lifecycle/LiveData;", "getLoginResponse", "()Landroidx/lifecycle/LiveData;", "loginRequest", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/LoginRequest;", "_forgetResponse", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;", "forgetResponse", "getForgetResponse", "forgetRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/ForgetRequest;", "_verifyOtpResponse", "verifyOtpResponse", "getVerifyOtpResponse", "verifyOtpRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/VerifyOtpRequest;", "_resetPasswordResponse", "resetPasswordResponse", "getResetPasswordResponse", "resetPasswordRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/ResetPasswordRequest;", "_profileResponse", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ProfileResponse;", "profileResponse", "getProfileResponse", Scopes.PROFILE, "_sendMessageResponse", "sendMessageResponse", "getSendMessageResponse", "sendMessageRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/SendMessageRequest;", "_listMessageResponse", "Lcom/ingenious/androidbookmarksalesupgrade/model/MessageListResponse;", "listMessageResponse", "getListMessageResponse", "listMessageRequest", "page", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class UserViewModel extends ViewModel {
    private final AppRepository repository = InjectUtils.INSTANCE.getAppRepository();
    private final MutableLiveData<ApiResponseCallback<LoginResponse>> _loginResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<GlobalResponse>> _forgetResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<GlobalResponse>> _verifyOtpResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<GlobalResponse>> _resetPasswordResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<ProfileResponse>> _profileResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<GlobalResponse>> _sendMessageResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<MessageListResponse>> _listMessageResponse = new MutableLiveData<>();

    public final LiveData<ApiResponseCallback<LoginResponse>> getLoginResponse() {
        return this._loginResponse;
    }

    public final void loginRequest(LoginRequest loginRequest) {
        Intrinsics.checkNotNullParameter(loginRequest, "loginRequest");
        this._loginResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new UserViewModel$loginRequest$1(this, loginRequest, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<GlobalResponse>> getForgetResponse() {
        return this._forgetResponse;
    }

    public final void forgetRequest(ForgetRequest forgetRequest) {
        Intrinsics.checkNotNullParameter(forgetRequest, "forgetRequest");
        this._forgetResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new UserViewModel$forgetRequest$1(this, forgetRequest, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<GlobalResponse>> getVerifyOtpResponse() {
        return this._verifyOtpResponse;
    }

    public final void verifyOtpRequest(VerifyOtpRequest verifyOtpRequest) {
        Intrinsics.checkNotNullParameter(verifyOtpRequest, "verifyOtpRequest");
        this._verifyOtpResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new UserViewModel$verifyOtpRequest$1(this, verifyOtpRequest, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<GlobalResponse>> getResetPasswordResponse() {
        return this._resetPasswordResponse;
    }

    public final void resetPasswordRequest(ResetPasswordRequest resetPasswordRequest) {
        Intrinsics.checkNotNullParameter(resetPasswordRequest, "resetPasswordRequest");
        this._resetPasswordResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new UserViewModel$resetPasswordRequest$1(this, resetPasswordRequest, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<ProfileResponse>> getProfileResponse() {
        return this._profileResponse;
    }

    public final void profile() {
        this._profileResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new UserViewModel$profile$1(this, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<GlobalResponse>> getSendMessageResponse() {
        return this._sendMessageResponse;
    }

    public final void sendMessageRequest(SendMessageRequest sendMessageRequest) {
        Intrinsics.checkNotNullParameter(sendMessageRequest, "sendMessageRequest");
        this._sendMessageResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new UserViewModel$sendMessageRequest$1(this, sendMessageRequest, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<MessageListResponse>> getListMessageResponse() {
        return this._listMessageResponse;
    }

    public final void listMessageRequest(int page) {
        this._listMessageResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new UserViewModel$listMessageRequest$1(this, page, null), 3, null);
    }
}
