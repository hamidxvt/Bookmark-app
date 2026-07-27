package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.util.Log;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.ingenious.androidbookmarksalesupgrade.adapter.OrderAdapter;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AllProductsActivity.kt */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/activity/AllProductsActivity$fetchProductsWithFilter$4", "Lokhttp3/Callback;", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class AllProductsActivity$fetchProductsWithFilter$4 implements Callback {
    final /* synthetic */ AllProductsActivity this$0;

    AllProductsActivity$fetchProductsWithFilter$4(AllProductsActivity $receiver) {
        this.this$0 = $receiver;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException e) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(e, "e");
        AllProductsActivity allProductsActivity = this.this$0;
        final AllProductsActivity allProductsActivity2 = this.this$0;
        allProductsActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$fetchProductsWithFilter$4$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AllProductsActivity$fetchProductsWithFilter$4.onFailure$lambda$0(AllProductsActivity.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFailure$lambda$0(AllProductsActivity this$0) {
        Toast.makeText(this$0, "Failed to load products", 0).show();
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) {
        final List products;
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        ResponseBody body = response.body();
        String body2 = body != null ? body.string() : null;
        response.close();
        if (response.isSuccessful()) {
            String str = body2;
            if (str == null || str.length() == 0) {
                return;
            }
            try {
                JSONObject json = new JSONObject(body2);
                JSONArray productsArray = json.optJSONArray("products");
                if (productsArray == null) {
                    productsArray = new JSONArray();
                }
                products = this.this$0.parseProductsArray(productsArray);
                AllProductsActivity allProductsActivity = this.this$0;
                final AllProductsActivity allProductsActivity2 = this.this$0;
                allProductsActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$fetchProductsWithFilter$4$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        AllProductsActivity$fetchProductsWithFilter$4.onResponse$lambda$1(AllProductsActivity.this, products);
                    }
                });
            } catch (Exception e) {
                Log.e("FILTER_PARSE", String.valueOf(e.getMessage()));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$1(AllProductsActivity this$0, List $products) {
        OrderAdapter orderAdapter;
        orderAdapter = this$0.adapter;
        if (orderAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            orderAdapter = null;
        }
        orderAdapter.setItems($products);
    }
}
