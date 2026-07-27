package com.ingenious.androidbookmarksalesupgrade.network.domain;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: DataAccessStrategy.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a>\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\"\u0010\u0003\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\u0086@¢\u0006\u0002\u0010\u0007\u001ab\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\"\u0010\u0003\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00042\"\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\tH\u0086@¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"performNetworkCallOperation", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "T", "networkCall", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveCallResult", "Lkotlin/Function2;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"}, k = 2, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes15.dex */
public final class DataAccessStrategyKt {
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object performNetworkCallOperation(Function1<? super Continuation<? super ApiResponseCallback<T>>, ? extends Object> function1, Continuation<? super ApiResponseCallback<T>> continuation) {
        DataAccessStrategyKt$performNetworkCallOperation$1 dataAccessStrategyKt$performNetworkCallOperation$1;
        Object invoke;
        Object response;
        if (continuation instanceof DataAccessStrategyKt$performNetworkCallOperation$1) {
            dataAccessStrategyKt$performNetworkCallOperation$1 = (DataAccessStrategyKt$performNetworkCallOperation$1) continuation;
            if ((dataAccessStrategyKt$performNetworkCallOperation$1.label & Integer.MIN_VALUE) != 0) {
                dataAccessStrategyKt$performNetworkCallOperation$1.label -= Integer.MIN_VALUE;
                Object $result = dataAccessStrategyKt$performNetworkCallOperation$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (dataAccessStrategyKt$performNetworkCallOperation$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        dataAccessStrategyKt$performNetworkCallOperation$1.label = 1;
                        invoke = function1.invoke(dataAccessStrategyKt$performNetworkCallOperation$1);
                        if (invoke == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        invoke = $result;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ApiResponseCallback responseStatus = (ApiResponseCallback) invoke;
                response = responseStatus.getData();
                if (response == null) {
                    return new ApiResponseCallback.Success(response);
                }
                return new ApiResponseCallback.Error(responseStatus.getMessage(), responseStatus.getCode(), null, 4, null);
            }
        }
        dataAccessStrategyKt$performNetworkCallOperation$1 = new DataAccessStrategyKt$performNetworkCallOperation$1(continuation);
        Object $result2 = dataAccessStrategyKt$performNetworkCallOperation$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (dataAccessStrategyKt$performNetworkCallOperation$1.label) {
        }
        ApiResponseCallback responseStatus2 = (ApiResponseCallback) invoke;
        response = responseStatus2.getData();
        if (response == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object performNetworkCallOperation(Function1<? super Continuation<? super ApiResponseCallback<T>>, ? extends Object> function1, Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super ApiResponseCallback<T>> continuation) {
        DataAccessStrategyKt$performNetworkCallOperation$3 dataAccessStrategyKt$performNetworkCallOperation$3;
        Object invoke;
        Object response;
        Object response2;
        if (continuation instanceof DataAccessStrategyKt$performNetworkCallOperation$3) {
            dataAccessStrategyKt$performNetworkCallOperation$3 = (DataAccessStrategyKt$performNetworkCallOperation$3) continuation;
            if ((dataAccessStrategyKt$performNetworkCallOperation$3.label & Integer.MIN_VALUE) != 0) {
                dataAccessStrategyKt$performNetworkCallOperation$3.label -= Integer.MIN_VALUE;
                Object $result = dataAccessStrategyKt$performNetworkCallOperation$3.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (dataAccessStrategyKt$performNetworkCallOperation$3.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        dataAccessStrategyKt$performNetworkCallOperation$3.L$0 = function2;
                        dataAccessStrategyKt$performNetworkCallOperation$3.label = 1;
                        invoke = function1.invoke(dataAccessStrategyKt$performNetworkCallOperation$3);
                        if (invoke == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        ApiResponseCallback responseStatus = (ApiResponseCallback) invoke;
                        response = responseStatus.getData();
                        if (response == null) {
                            dataAccessStrategyKt$performNetworkCallOperation$3.L$0 = response;
                            dataAccessStrategyKt$performNetworkCallOperation$3.label = 2;
                            if (function2.invoke(response, dataAccessStrategyKt$performNetworkCallOperation$3) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            response2 = response;
                            return new ApiResponseCallback.Success(response2);
                        }
                        return new ApiResponseCallback.Error(responseStatus.getMessage(), responseStatus.getCode(), null, 4, null);
                    case 1:
                        Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function22 = (Function2) dataAccessStrategyKt$performNetworkCallOperation$3.L$0;
                        ResultKt.throwOnFailure($result);
                        function2 = function22;
                        invoke = $result;
                        ApiResponseCallback responseStatus2 = (ApiResponseCallback) invoke;
                        response = responseStatus2.getData();
                        if (response == null) {
                        }
                        break;
                    case 2:
                        response2 = dataAccessStrategyKt$performNetworkCallOperation$3.L$0;
                        ResultKt.throwOnFailure($result);
                        return new ApiResponseCallback.Success(response2);
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        dataAccessStrategyKt$performNetworkCallOperation$3 = new DataAccessStrategyKt$performNetworkCallOperation$3(continuation);
        Object $result2 = dataAccessStrategyKt$performNetworkCallOperation$3.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (dataAccessStrategyKt$performNetworkCallOperation$3.label) {
        }
    }
}
