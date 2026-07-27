package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.util.Log;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityCompleteVisitBinding;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import com.ingenious.androidbookmarksalesupgrade.utils.LoggingInterceptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: CompleteVisitActivity.kt */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/activity/CompleteVisitActivity$fetchVisitProducts$1", "Lokhttp3/Callback;", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class CompleteVisitActivity$fetchVisitProducts$1 implements Callback {
    final /* synthetic */ CompleteVisitActivity this$0;

    CompleteVisitActivity$fetchVisitProducts$1(CompleteVisitActivity $receiver) {
        this.this$0 = $receiver;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, final IOException e) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(e, "e");
        Log.e(LoggingInterceptor.TAG, "Failed to load products: " + e.getMessage());
        CompleteVisitActivity completeVisitActivity = this.this$0;
        final CompleteVisitActivity completeVisitActivity2 = this.this$0;
        completeVisitActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$fetchVisitProducts$1$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                CompleteVisitActivity$fetchVisitProducts$1.onFailure$lambda$0(CompleteVisitActivity.this, e);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFailure$lambda$0(CompleteVisitActivity this$0, IOException $e) {
        Toast.makeText(this$0, "Failed to load products: " + $e.getMessage(), 0).show();
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) {
        String str = "product_image";
        String str2 = "0";
        String str3 = "";
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        ResponseBody body = response.body();
        String body2 = body != null ? body.string() : null;
        Log.d(LoggingInterceptor.TAG, "Response body: " + body2);
        if (response.isSuccessful()) {
            String str4 = body2;
            if (!(str4 == null || str4.length() == 0)) {
                try {
                    JSONObject root = new JSONObject(body2);
                    JSONObject dataObject = root.optJSONObject(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
                    if (dataObject == null) {
                        try {
                            dataObject = new JSONObject();
                        } catch (Exception e) {
                            e = e;
                            Log.e(LoggingInterceptor.TAG, "Error parsing products: " + e.getMessage());
                            CompleteVisitActivity completeVisitActivity = this.this$0;
                            final CompleteVisitActivity completeVisitActivity2 = this.this$0;
                            completeVisitActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$fetchVisitProducts$1$$ExternalSyntheticLambda3
                                @Override // java.lang.Runnable
                                public final void run() {
                                    CompleteVisitActivity$fetchVisitProducts$1.onResponse$lambda$4(CompleteVisitActivity.this);
                                }
                            });
                            return;
                        }
                    }
                    JSONArray productsArray = dataObject.optJSONArray("products");
                    if (productsArray == null) {
                        productsArray = new JSONArray();
                    }
                    final List productsList = new ArrayList();
                    int i = 0;
                    int length = productsArray.length();
                    while (i < length) {
                        JSONObject obj = productsArray.getJSONObject(i);
                        String body3 = body2;
                        try {
                            int optInt = obj.optInt("product_id", 0);
                            String optString = obj.optString("title", str3);
                            String optString2 = obj.optString(FirebaseAnalytics.Param.QUANTITY, str2);
                            Products product = new Products(Integer.valueOf(optInt), null, null, null, null, optString, null, null, null, obj.optString(str, str3), obj.optString(FirebaseAnalytics.Param.PRICE, str2), null, optString2, null, null, null, null, null, null, null, obj.optString("subject", str3), null, obj.optString("grade", str3), null, null, null, null, 128969182, null);
                            productsList.add(product);
                            Log.i("TAG", "onResponse: " + obj.optString(str, str3));
                            i++;
                            body2 = body3;
                            str2 = str2;
                            str = str;
                            str3 = str3;
                        } catch (Exception e2) {
                            e = e2;
                            Log.e(LoggingInterceptor.TAG, "Error parsing products: " + e.getMessage());
                            CompleteVisitActivity completeVisitActivity3 = this.this$0;
                            final CompleteVisitActivity completeVisitActivity22 = this.this$0;
                            completeVisitActivity3.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$fetchVisitProducts$1$$ExternalSyntheticLambda3
                                @Override // java.lang.Runnable
                                public final void run() {
                                    CompleteVisitActivity$fetchVisitProducts$1.onResponse$lambda$4(CompleteVisitActivity.this);
                                }
                            });
                            return;
                        }
                    }
                    Log.d(LoggingInterceptor.TAG, "Parsed products: " + productsList);
                    CompleteVisitActivity completeVisitActivity4 = this.this$0;
                    final CompleteVisitActivity completeVisitActivity5 = this.this$0;
                    completeVisitActivity4.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$fetchVisitProducts$1$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            CompleteVisitActivity.access$showVisitProducts(CompleteVisitActivity.this, productsList);
                        }
                    });
                    return;
                } catch (Exception e3) {
                    e = e3;
                }
            }
        }
        this.this$0.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$fetchVisitProducts$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CompleteVisitActivity$fetchVisitProducts$1.onResponse$lambda$1();
            }
        });
        CompleteVisitActivity completeVisitActivity6 = this.this$0;
        final CompleteVisitActivity completeVisitActivity7 = this.this$0;
        completeVisitActivity6.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$fetchVisitProducts$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                CompleteVisitActivity$fetchVisitProducts$1.onResponse$lambda$2(CompleteVisitActivity.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$1() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$2(CompleteVisitActivity this$0) {
        ActivityCompleteVisitBinding activityCompleteVisitBinding;
        activityCompleteVisitBinding = this$0.binding;
        if (activityCompleteVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding = null;
        }
        activityCompleteVisitBinding.pbarFetchingBooks.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$4(CompleteVisitActivity this$0) {
        ActivityCompleteVisitBinding activityCompleteVisitBinding;
        Toast.makeText(this$0, "Error parsing products", 0).show();
        activityCompleteVisitBinding = this$0.binding;
        if (activityCompleteVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding = null;
        }
        activityCompleteVisitBinding.pbarFetchingBooks.setVisibility(8);
    }
}
