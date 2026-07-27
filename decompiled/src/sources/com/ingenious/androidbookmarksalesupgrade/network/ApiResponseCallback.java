package com.ingenious.androidbookmarksalesupgrade.network;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApiResponseCallback.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0004\u0019\u001a\u001b\u001cB9\b\u0004\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bR\u0015\u0010\u0003\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017\u0082\u0001\u0003\u001d\u001e\u001f¨\u0006 "}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "T", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "code", "", "message", "", FirebaseAnalytics.Param.SUCCESS, "", "<init>", "(Ljava/lang/Object;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getCode", "()Ljava/lang/Integer;", "setCode", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getMessage", "()Ljava/lang/String;", "getSuccess", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "Loading", "Success", "Error", "ApiResponse", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback$Error;", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback$Loading;", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback$Success;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes13.dex */
public abstract class ApiResponseCallback<T> {
    private Integer code;
    private final T data;
    private final String message;
    private final Boolean success;

    public /* synthetic */ ApiResponseCallback(Object obj, Integer num, String str, Boolean bool, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, num, str, bool);
    }

    private ApiResponseCallback(T t, Integer code, String message, Boolean success) {
        this.data = t;
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public /* synthetic */ ApiResponseCallback(Object obj, Integer num, String str, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : obj, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : str, (i & 8) != 0 ? false : bool, null);
    }

    public final T getData() {
        return this.data;
    }

    public final Integer getCode() {
        return this.code;
    }

    public final void setCode(Integer num) {
        this.code = num;
    }

    public final String getMessage() {
        return this.message;
    }

    public final Boolean getSuccess() {
        return this.success;
    }

    /* compiled from: ApiResponseCallback.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback$Loading;", "T", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "<init>", "()V", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Loading<T> extends ApiResponseCallback<T> {
        public Loading() {
            super(null, null, null, null, 15, null);
        }
    }

    /* compiled from: ApiResponseCallback.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback$Success;", "T", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "<init>", "(Ljava/lang/Object;)V", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Success<T> extends ApiResponseCallback<T> {
        public Success(T t) {
            super(t, null, null, null, 14, null);
        }
    }

    /* compiled from: ApiResponseCallback.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B'\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00018\u0001¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback$Error;", "T", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "message", "", "code", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Object;)V", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Error<T> extends ApiResponseCallback<T> {
        public Error(String message, Integer code, T t) {
            super(t, code, message, null, 8, null);
        }

        public /* synthetic */ Error(String str, Integer num, Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, num, (i & 4) != 0 ? null : obj);
        }
    }

    /* compiled from: ApiResponseCallback.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00018\u0001¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0011\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00018\u0001HÆ\u0003¢\u0006\u0002\u0010\u000fJ4\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00018\u0001HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0007\u001a\u0004\u0018\u00018\u0001¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback$ApiResponse;", "T", "", FirebaseAnalytics.Param.SUCCESS, "", "message", "", "visitDetails", "<init>", "(ZLjava/lang/String;Ljava/lang/Object;)V", "getSuccess", "()Z", "getMessage", "()Ljava/lang/String;", "getVisitDetails", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "component3", "copy", "(ZLjava/lang/String;Ljava/lang/Object;)Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback$ApiResponse;", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class ApiResponse<T> {
        private final String message;
        private final boolean success;
        private final T visitDetails;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiResponse copy$default(ApiResponse apiResponse, boolean z, String str, Object obj, int i, Object obj2) {
            if ((i & 1) != 0) {
                z = apiResponse.success;
            }
            if ((i & 2) != 0) {
                str = apiResponse.message;
            }
            if ((i & 4) != 0) {
                obj = apiResponse.visitDetails;
            }
            return apiResponse.copy(z, str, obj);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getSuccess() {
            return this.success;
        }

        /* renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        public final T component3() {
            return this.visitDetails;
        }

        public final ApiResponse<T> copy(boolean success, String message, T visitDetails) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new ApiResponse<>(success, message, visitDetails);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ApiResponse)) {
                return false;
            }
            ApiResponse apiResponse = (ApiResponse) other;
            return this.success == apiResponse.success && Intrinsics.areEqual(this.message, apiResponse.message) && Intrinsics.areEqual(this.visitDetails, apiResponse.visitDetails);
        }

        public int hashCode() {
            return (((Boolean.hashCode(this.success) * 31) + this.message.hashCode()) * 31) + (this.visitDetails == null ? 0 : this.visitDetails.hashCode());
        }

        public String toString() {
            return "ApiResponse(success=" + this.success + ", message=" + this.message + ", visitDetails=" + this.visitDetails + ")";
        }

        public ApiResponse(boolean success, String message, T t) {
            Intrinsics.checkNotNullParameter(message, "message");
            this.success = success;
            this.message = message;
            this.visitDetails = t;
        }

        public final boolean getSuccess() {
            return this.success;
        }

        public final String getMessage() {
            return this.message;
        }

        public final T getVisitDetails() {
            return this.visitDetails;
        }
    }
}
