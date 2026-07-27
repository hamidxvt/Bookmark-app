package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: RecentActivityFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$searchActivityLog$1", f = "RecentActivityFragment.kt", i = {}, l = {579, TypedValues.MotionType.TYPE_ANIMATE_CIRCLEANGLE_TO}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
final class RecentActivityFragment$searchActivityLog$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $query;
    final /* synthetic */ String $token;
    int label;
    final /* synthetic */ RecentActivityFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RecentActivityFragment$searchActivityLog$1(String str, String str2, RecentActivityFragment recentActivityFragment, Continuation<? super RecentActivityFragment$searchActivityLog$1> continuation) {
        super(2, continuation);
        this.$query = str;
        this.$token = str2;
        this.this$0 = recentActivityFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RecentActivityFragment$searchActivityLog$1(this.$query, this.$token, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RecentActivityFragment$searchActivityLog$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.coroutines.Continuation] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        OkHttpClient client;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r2 = 0;
        r2 = 0;
        try {
        } catch (IOException e) {
            this.label = 2;
            if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(e, this.this$0, r2), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                Request build = new Request.Builder().url("https://staging.bookmark.services/api/Activity/SearchActivitylog?search=" + this.$query).addHeader(HttpHeaders.ACCEPT, "application/json").addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + this.$token).build();
                client = this.this$0.getClient();
                Response execute = client.newCall(build).execute();
                this.label = 1;
                Object withContext = BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1(this.this$0, execute, null), this);
                r2 = withContext;
                if (withContext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            case 2:
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* compiled from: RecentActivityFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$searchActivityLog$1$1", f = "RecentActivityFragment.kt", i = {}, l = {591}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$searchActivityLog$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Response $response;
        int label;
        final /* synthetic */ RecentActivityFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(RecentActivityFragment recentActivityFragment, Response response, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = recentActivityFragment;
            this.$response = response;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$response, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object parseActivityList;
            Object $result2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.this$0.showLoading(false);
                    if (!this.$response.isSuccessful()) {
                        Log.e("API_ERROR", "Code: " + this.$response.code());
                        this.this$0.showEmptyState("Server error: " + this.$response.code());
                    } else {
                        ResponseBody body = this.$response.body();
                        String body2 = body != null ? body.string() : null;
                        Log.d("API_SEARCH_RESPONSE", String.valueOf(body2));
                        if (body2 == null) {
                            body2 = "{}";
                        }
                        JSONObject json = new JSONObject(body2);
                        boolean success = json.optBoolean(FirebaseAnalytics.Param.SUCCESS, false);
                        String message = json.optString("message", "");
                        JSONArray data = json.optJSONArray(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
                        if (data == null) {
                            data = new JSONArray();
                        }
                        if (success && data.length() > 0) {
                            this.label = 1;
                            parseActivityList = this.this$0.parseActivityList(data, this);
                            if (parseActivityList == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $result2 = $result;
                            $result = parseActivityList;
                            List list = (List) $result;
                            this.this$0.showActivityLog(list);
                            this.this$0.showLoading(false);
                        } else {
                            RecentActivityFragment recentActivityFragment = this.this$0;
                            String str = message;
                            if (str.length() == 0) {
                                str = "No activity found.";
                            }
                            Intrinsics.checkNotNullExpressionValue(str, "ifEmpty(...)");
                            recentActivityFragment.showEmptyState(str);
                            this.this$0.showLoading(false);
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    ResultKt.throwOnFailure($result);
                    $result2 = $result;
                    List list2 = (List) $result;
                    this.this$0.showActivityLog(list2);
                    this.this$0.showLoading(false);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: RecentActivityFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$searchActivityLog$1$2", f = "RecentActivityFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$searchActivityLog$1$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ IOException $e;
        int label;
        final /* synthetic */ RecentActivityFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(IOException iOException, RecentActivityFragment recentActivityFragment, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$e = iOException;
            this.this$0 = recentActivityFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$e, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    Log.e("API_ERROR", "Network Error: " + this.$e.getMessage());
                    this.this$0.showEmptyState("Network error. Please try again.");
                    this.this$0.showLoading(false);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
