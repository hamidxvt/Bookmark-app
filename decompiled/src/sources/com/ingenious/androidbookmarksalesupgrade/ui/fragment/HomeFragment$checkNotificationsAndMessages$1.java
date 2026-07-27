package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.common.net.HttpHeaders;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: HomeFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$checkNotificationsAndMessages$1", f = "HomeFragment.kt", i = {}, l = {1499, 1504}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
final class HomeFragment$checkNotificationsAndMessages$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $token;
    int label;
    final /* synthetic */ HomeFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HomeFragment$checkNotificationsAndMessages$1(String str, HomeFragment homeFragment, Continuation<? super HomeFragment$checkNotificationsAndMessages$1> continuation) {
        super(2, continuation);
        this.$token = str;
        this.this$0 = homeFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HomeFragment$checkNotificationsAndMessages$1(this.$token, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HomeFragment$checkNotificationsAndMessages$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x011e A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        OkHttpClient client;
        int i;
        OkHttpClient client2;
        int i2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    Ref.BooleanRef hasData = new Ref.BooleanRef();
                    Request notificationRequest = new Request.Builder().url("https://staging.bookmark.services/api/notification/list").addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + this.$token).build();
                    client = this.this$0.getClient();
                    Response notificationResponse = client.newCall(notificationRequest).execute();
                    String str = "{}";
                    boolean z = false;
                    if (notificationResponse.isSuccessful()) {
                        ResponseBody body = notificationResponse.body();
                        String body2 = body != null ? body.string() : null;
                        if (body2 == null) {
                            body2 = "{}";
                        }
                        JSONObject json = new JSONObject(body2);
                        JSONArray optJSONArray = json.optJSONArray(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
                        if (optJSONArray == null) {
                            optJSONArray = new JSONArray();
                        }
                        JSONArray data = optJSONArray;
                        i = data.length();
                    } else {
                        i = 0;
                    }
                    int notificationListLength = i;
                    Request messageRequest = new Request.Builder().url("https://staging.bookmark.services/api/getMessages?page=1").addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + this.$token).build();
                    client2 = this.this$0.getClient();
                    Response messageResponse = client2.newCall(messageRequest).execute();
                    if (messageResponse.isSuccessful()) {
                        ResponseBody body3 = messageResponse.body();
                        String body4 = body3 != null ? body3.string() : null;
                        if (body4 != null) {
                            str = body4;
                        }
                        JSONObject json2 = new JSONObject(str);
                        JSONArray optJSONArray2 = json2.optJSONArray(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
                        if (optJSONArray2 == null) {
                            optJSONArray2 = new JSONArray();
                        }
                        JSONArray data2 = optJSONArray2;
                        i2 = data2.length();
                    } else {
                        i2 = 0;
                    }
                    int messageListLength = i2;
                    if (notificationListLength <= 0 && messageListLength <= 0) {
                        hasData.element = z;
                        this.label = 1;
                        if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1(this.this$0, hasData, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    z = true;
                    hasData.element = z;
                    this.label = 1;
                    if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1(this.this$0, hasData, null), this) == coroutine_suspended) {
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                case 2:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Exception e) {
            this.label = 2;
            if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(e, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }

    /* compiled from: HomeFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$checkNotificationsAndMessages$1$1", f = "HomeFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$checkNotificationsAndMessages$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.BooleanRef $hasData;
        int label;
        final /* synthetic */ HomeFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(HomeFragment homeFragment, Ref.BooleanRef booleanRef, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = homeFragment;
            this.$hasData = booleanRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$hasData, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    this.this$0.updateNotificationDot(this.$hasData.element);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: HomeFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$checkNotificationsAndMessages$1$2", f = "HomeFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.ingenious.androidbookmarksalesupgrade.ui.fragment.HomeFragment$checkNotificationsAndMessages$1$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final /* synthetic */ Exception $e;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Exception exc, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$e = exc;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$e, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    return Boxing.boxInt(Log.e("TAG", "Error fetching notifications/messages: " + this.$e.getMessage()));
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
