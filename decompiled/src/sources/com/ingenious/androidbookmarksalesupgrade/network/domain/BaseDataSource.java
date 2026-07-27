package com.ingenious.androidbookmarksalesupgrade.network.domain;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.utils.Utils;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import retrofit2.Response;

/* compiled from: BaseDataSource.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J>\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u00062\"\u0010\u0007\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bH\u0084@¢\u0006\u0002\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/network/domain/BaseDataSource;", "", "<init>", "()V", "callApi", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "T", NotificationCompat.CATEGORY_CALL, "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "Lretrofit2/Response;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes15.dex */
public abstract class BaseDataSource {
    private static final int BAD_REQUEST = 400;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int NOT_FOUND = 404;
    private static final int NO_NETWORK = 11002;
    private static final int SERVER_ERROR = 600;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int UNAUTHENTICATED = 401;
    private static final int UNEXPECTED_ERROR = 11001;

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final <T> Object callApi(Function1<? super Continuation<? super Response<T>>, ? extends Object> function1, Continuation<? super ApiResponseCallback<T>> continuation) {
        BaseDataSource$callApi$1 baseDataSource$callApi$1;
        Ref.ObjectRef objectRef;
        Object invoke;
        Object m572exceptionOrNullimpl;
        Response<T> response;
        T body;
        if (continuation instanceof BaseDataSource$callApi$1) {
            baseDataSource$callApi$1 = (BaseDataSource$callApi$1) continuation;
            if ((baseDataSource$callApi$1.label & Integer.MIN_VALUE) != 0) {
                baseDataSource$callApi$1.label -= Integer.MIN_VALUE;
                Object obj = baseDataSource$callApi$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (baseDataSource$callApi$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                        objectRef2.element = (T) new Exception("Network call has failed!");
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            BaseDataSource baseDataSource = this;
                            baseDataSource$callApi$1.L$0 = objectRef2;
                            baseDataSource$callApi$1.label = 1;
                            invoke = function1.invoke(baseDataSource$callApi$1);
                            if (invoke == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            response = (Response) invoke;
                            if (!response.isSuccessful() && (body = response.body()) != null) {
                                return new ApiResponseCallback.Success(body);
                            }
                            return APIError.INSTANCE.error(response);
                        } catch (Throwable th) {
                            th = th;
                            objectRef = objectRef2;
                            Result.Companion companion2 = Result.INSTANCE;
                            m572exceptionOrNullimpl = Result.m572exceptionOrNullimpl(Result.m569constructorimpl(ResultKt.createFailure(th)));
                            if (m572exceptionOrNullimpl != null) {
                            }
                            return APIError.INSTANCE.error((Throwable) objectRef.element);
                        }
                    case 1:
                        objectRef = (Ref.ObjectRef) baseDataSource$callApi$1.L$0;
                        try {
                            ResultKt.throwOnFailure(obj);
                            invoke = obj;
                            response = (Response) invoke;
                            if (!response.isSuccessful()) {
                                break;
                            }
                            return APIError.INSTANCE.error(response);
                        } catch (Throwable th2) {
                            th = th2;
                            Result.Companion companion22 = Result.INSTANCE;
                            m572exceptionOrNullimpl = Result.m572exceptionOrNullimpl(Result.m569constructorimpl(ResultKt.createFailure(th)));
                            if (m572exceptionOrNullimpl != null) {
                                Intrinsics.checkNotNull(m572exceptionOrNullimpl, "null cannot be cast to non-null type java.lang.Exception");
                                objectRef.element = (T) ((Exception) m572exceptionOrNullimpl);
                            }
                            return APIError.INSTANCE.error((Throwable) objectRef.element);
                        }
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        baseDataSource$callApi$1 = new BaseDataSource$callApi$1(this, continuation);
        Object obj2 = baseDataSource$callApi$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (baseDataSource$callApi$1.label) {
        }
    }

    /* compiled from: BaseDataSource.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\r0\u0012J0\u0010\u0014\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\r0\u0012H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/network/domain/BaseDataSource$Companion;", "", "<init>", "()V", "UNEXPECTED_ERROR", "", "NO_NETWORK", "UNAUTHENTICATED", "NOT_FOUND", "BAD_REQUEST", "SERVICE_UNAVAILABLE", "SERVER_ERROR", "networkCallFailed", "", "T", "apiResponseCallback", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "errorHandler", "Lkotlin/Function1;", "Lcom/ingenious/androidbookmarksalesupgrade/network/domain/ErrorHandler;", "emitError", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T> void networkCallFailed(ApiResponseCallback<T> apiResponseCallback, Function1<? super ErrorHandler, Unit> errorHandler) {
            Intrinsics.checkNotNullParameter(apiResponseCallback, "apiResponseCallback");
            Intrinsics.checkNotNullParameter(errorHandler, "errorHandler");
            emitError(apiResponseCallback, errorHandler);
        }

        private final <T> void emitError(ApiResponseCallback<T> apiResponseCallback, Function1<? super ErrorHandler, Unit> errorHandler) {
            if (!Utils.INSTANCE.isOnline()) {
                apiResponseCallback.setCode(Integer.valueOf(BaseDataSource.NO_NETWORK));
            }
            Integer code = apiResponseCallback.getCode();
            if (code != null && code.intValue() == 401) {
                errorHandler.invoke(new ErrorHandler(null, R.string.error_msg_unauthorized, "unauthorized", 1, null));
                return;
            }
            if (code != null && code.intValue() == BaseDataSource.NO_NETWORK) {
                errorHandler.invoke(new ErrorHandler(null, R.string.error_msg_no_internet, "network_failed", 1, null));
                return;
            }
            if (code != null && code.intValue() == BaseDataSource.NOT_FOUND) {
                errorHandler.invoke(new ErrorHandler(null, R.string.page_not_found, "not_found", 1, null));
                return;
            }
            if (code != null && code.intValue() == BaseDataSource.BAD_REQUEST) {
                errorHandler.invoke(new ErrorHandler(null, R.string.bad_request, "bad_request", 1, null));
                return;
            }
            if (code != null && code.intValue() == 503) {
                errorHandler.invoke(new ErrorHandler(null, R.string.service_unavailable, "service_unavailable", 1, null));
            } else if (code != null && code.intValue() == 600) {
                errorHandler.invoke(new ErrorHandler(null, R.string.server_error, "server_error", 1, null));
            } else {
                errorHandler.invoke(new ErrorHandler(null, R.string.unexpected_error_occurred, "unexpected_error", 1, null));
            }
        }
    }
}
