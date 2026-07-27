package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.ingenious.androidbookmarksalesupgrade.model.DeliveredBooks;
import com.ingenious.androidbookmarksalesupgrade.model.DeliveredBooksData;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import com.ingenious.androidbookmarksalesupgrade.utils.LoggingInterceptor;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: RecentActivityFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/ingenious/androidbookmarksalesupgrade/model/DeliveredBooks;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$fetchVisitProducts$2", f = "RecentActivityFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
final class RecentActivityFragment$fetchVisitProducts$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super DeliveredBooks>, Object> {
    final /* synthetic */ int $visitId;
    int label;
    final /* synthetic */ RecentActivityFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RecentActivityFragment$fetchVisitProducts$2(int i, RecentActivityFragment recentActivityFragment, Continuation<? super RecentActivityFragment$fetchVisitProducts$2> continuation) {
        super(2, continuation);
        this.$visitId = i;
        this.this$0 = recentActivityFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RecentActivityFragment$fetchVisitProducts$2(this.$visitId, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super DeliveredBooks> continuation) {
        return ((RecentActivityFragment$fetchVisitProducts$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ce A[Catch: Exception -> 0x01be, TryCatch #0 {Exception -> 0x01be, blocks: (B:8:0x001d, B:12:0x006b, B:14:0x0096, B:15:0x009c, B:17:0x00ba, B:19:0x00c0, B:25:0x00ce, B:27:0x00e9, B:30:0x00f0, B:33:0x00fd, B:35:0x0105, B:36:0x010a, B:38:0x0118, B:40:0x018e, B:43:0x01b5), top: B:7:0x001d }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String token;
        boolean z;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                try {
                    OkHttpClient client = new OkHttpClient();
                    String url = "https://staging.bookmark.services/api/visit/get-product?visit_id=" + this.$visitId;
                    Log.d(LoggingInterceptor.TAG, "Fetching products from: " + url);
                    Request.Builder addHeader = new Request.Builder().url(url).addHeader(HttpHeaders.ACCEPT, "application/json");
                    token = this.this$0.getToken();
                    if (token == null) {
                        token = "";
                    }
                    Request request = addHeader.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
                    Response response = client.newCall(request).execute();
                    ResponseBody body = response.body();
                    String body2 = body != null ? body.string() : null;
                    Log.d("TAG", "Response body: " + body2);
                    if (response.isSuccessful()) {
                        String str = body2;
                        int i = 0;
                        if (str != null && str.length() != 0) {
                            z = false;
                            if (z) {
                                JSONObject root = new JSONObject(body2);
                                boolean success = root.optBoolean(FirebaseAnalytics.Param.SUCCESS);
                                String message = root.optString("message");
                                JSONObject dataObject = root.optJSONObject(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
                                if (dataObject == null) {
                                    boolean z2 = success;
                                    Intrinsics.checkNotNull(message);
                                    return new DeliveredBooks(z2, message, null, 4, null);
                                }
                                JSONArray productsArray = dataObject.optJSONArray("products");
                                if (productsArray == null) {
                                    productsArray = new JSONArray();
                                }
                                List productsList = new ArrayList();
                                int i2 = 0;
                                int length = productsArray.length();
                                while (i2 < length) {
                                    JSONObject obj2 = productsArray.getJSONObject(i2);
                                    int optInt = obj2.optInt("product_id", i);
                                    String optString = obj2.optString("product_name", "");
                                    String optString2 = obj2.optString(FirebaseAnalytics.Param.QUANTITY, "0");
                                    Products product = new Products(null, null, Boxing.boxInt(optInt), null, null, null, null, null, null, obj2.optString("product_image", ""), obj2.optString(FirebaseAnalytics.Param.PRICE, "0"), null, optString2, null, null, null, null, null, null, null, obj2.optString("subject", ""), optString, obj2.optString("grade", ""), null, null, null, null, 126872059, null);
                                    productsList.add(product);
                                    i2++;
                                    i = 0;
                                }
                                DeliveredBooksData deliveredBooksData = new DeliveredBooksData(dataObject.optString("visit_id"), Boxing.boxInt(dataObject.optInt("booker_id")), productsList, Boxing.boxInt(dataObject.optInt("grand_total")));
                                boolean z3 = success;
                                Intrinsics.checkNotNull(message);
                                return new DeliveredBooks(z3, message, deliveredBooksData);
                            }
                        }
                        z = true;
                        if (z) {
                        }
                    }
                    return null;
                } catch (Exception e) {
                    Log.e(LoggingInterceptor.TAG, "Error parsing products: " + e.getMessage());
                    return null;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
