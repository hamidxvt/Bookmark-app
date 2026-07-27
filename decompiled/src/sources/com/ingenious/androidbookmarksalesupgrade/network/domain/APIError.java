package com.ingenious.androidbookmarksalesupgrade.network.domain;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.utils.Utils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Response;

/* compiled from: APIError.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/network/domain/APIError;", "", "<init>", "()V", "Companion", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes15.dex */
public final class APIError {
    private static final int BAD_REQUEST = 400;
    private static final int BLOCK_BY_ADMIN = 403;
    public static final String BLOCK_BY_ADMIN_MSG = "Account_block";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int INTERNAL_SERVER = 500;
    public static final String INTERNAL_SERVER_ERROR = "internal_server_error";
    public static final String NETWORK_CALL_FAILED = "network_failed";
    private static final int NOT_FOUND = 404;
    private static final int NO_NETWORK = 11002;
    public static final String PAGE_NOT_FOUND = "not_found";
    public static final String SERVER_BAD_REQUEST = "bad_request";
    private static final int SERVER_ERROR = 600;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int UNAUTHENTICATED = 401;
    public static final String UNAUTHORIZED = "unauthorized";
    private static final int UNEXPECTED_ERROR = 11001;
    public static final String UNEXPECTED_ERROR_OCCURRED = "unexpected_error";
    public static final String WEB_SERVER_ERROR = "server_error";
    public static final String WEB_SERVICE_UNAVAILABLE = "service_unavailable";

    /* compiled from: APIError.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u0019\"\u0004\b\u0000\u0010\u001a2\u0006\u0010\u0018\u001a\u00020\u001bJ \u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u0019\"\u0004\b\u0000\u0010\u001a2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u001dJ.\u0010\u001e\u001a\u00020\u001f\"\u0004\b\u0000\u0010\u001a2\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001a0\u00192\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u001f0\"J0\u0010$\u001a\u00020\u001f\"\u0004\b\u0000\u0010\u001a2\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001a0\u00192\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u001f0\"H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/network/domain/APIError$Companion;", "", "<init>", "()V", "UNEXPECTED_ERROR", "", "NO_NETWORK", "UNAUTHENTICATED", "NOT_FOUND", "BLOCK_BY_ADMIN", "BAD_REQUEST", "SERVICE_UNAVAILABLE", "INTERNAL_SERVER", "SERVER_ERROR", "UNAUTHORIZED", "", "NETWORK_CALL_FAILED", "PAGE_NOT_FOUND", "BLOCK_BY_ADMIN_MSG", "SERVER_BAD_REQUEST", "WEB_SERVICE_UNAVAILABLE", "WEB_SERVER_ERROR", "INTERNAL_SERVER_ERROR", "UNEXPECTED_ERROR_OCCURRED", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "T", "", "response", "Lretrofit2/Response;", "networkCallFailed", "", "apiResponseCallback", "errorHandler", "Lkotlin/Function1;", "Lcom/ingenious/androidbookmarksalesupgrade/network/domain/ErrorHandler;", "emitError", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T> ApiResponseCallback<T> error(Throwable error) {
            Intrinsics.checkNotNullParameter(error, "error");
            return new ApiResponseCallback.Error(error.toString(), Integer.valueOf(APIError.UNEXPECTED_ERROR), null, 4, null);
        }

        public final <T> ApiResponseCallback<T> error(Response<T> response) {
            Intrinsics.checkNotNullParameter(response, "response");
            int code = response.code();
            String message = response.message();
            return new ApiResponseCallback.Error("Network call has failed for a following reason: " + message, Integer.valueOf(code), null, 4, null);
        }

        public final <T> void networkCallFailed(ApiResponseCallback<T> apiResponseCallback, Function1<? super ErrorHandler, Unit> errorHandler) {
            Intrinsics.checkNotNullParameter(apiResponseCallback, "apiResponseCallback");
            Intrinsics.checkNotNullParameter(errorHandler, "errorHandler");
            emitError(apiResponseCallback, errorHandler);
        }

        private final <T> void emitError(ApiResponseCallback<T> apiResponseCallback, Function1<? super ErrorHandler, Unit> errorHandler) {
            if (!Utils.INSTANCE.isOnline()) {
                apiResponseCallback.setCode(Integer.valueOf(APIError.NO_NETWORK));
            }
            Integer code = apiResponseCallback.getCode();
            if (code != null && code.intValue() == 401) {
                errorHandler.invoke(new ErrorHandler(null, R.string.error_msg_unauthorized, "unauthorized", 1, null));
                return;
            }
            if (code != null && code.intValue() == APIError.NO_NETWORK) {
                errorHandler.invoke(new ErrorHandler(null, R.string.error_msg_no_internet, "network_failed", 1, null));
                return;
            }
            if (code != null && code.intValue() == APIError.NOT_FOUND) {
                errorHandler.invoke(new ErrorHandler(null, R.string.page_not_found, "not_found", 1, null));
                return;
            }
            if (code != null && code.intValue() == APIError.BAD_REQUEST) {
                errorHandler.invoke(new ErrorHandler(null, R.string.bad_request, "bad_request", 1, null));
                return;
            }
            if (code != null && code.intValue() == 503) {
                errorHandler.invoke(new ErrorHandler(null, R.string.service_unavailable, "service_unavailable", 1, null));
                return;
            }
            if (code != null && code.intValue() == 600) {
                errorHandler.invoke(new ErrorHandler(null, R.string.server_error, "server_error", 1, null));
                return;
            }
            if (code != null && code.intValue() == 500) {
                errorHandler.invoke(new ErrorHandler(null, R.string.internal_server_error, APIError.INTERNAL_SERVER_ERROR, 1, null));
            } else if (code != null && code.intValue() == 403) {
                errorHandler.invoke(new ErrorHandler(null, R.string.account_block_by_admin_msg, APIError.BLOCK_BY_ADMIN_MSG, 1, null));
            } else {
                errorHandler.invoke(new ErrorHandler(null, R.string.unexpected_error_occurred, "unexpected_error", 1, null));
            }
        }
    }
}
