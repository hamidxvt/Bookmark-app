package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.ingenious.androidbookmarksalesupgrade.model.BookModel;
import com.ingenious.androidbookmarksalesupgrade.model.SegmentModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: InventoryFragment.kt */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/fragment/InventoryFragment$fetchBooksBySegment$1", "Lokhttp3/Callback;", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class InventoryFragment$fetchBooksBySegment$1 implements Callback {
    final /* synthetic */ InventoryFragment this$0;

    InventoryFragment$fetchBooksBySegment$1(InventoryFragment $receiver) {
        this.this$0 = $receiver;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException e) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(e, "e");
        FragmentActivity requireActivity = this.this$0.requireActivity();
        final InventoryFragment inventoryFragment = this.this$0;
        requireActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$fetchBooksBySegment$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                InventoryFragment$fetchBooksBySegment$1.onFailure$lambda$0(InventoryFragment.this);
            }
        });
        Log.e("API_ERROR", "Request failed: " + e.getMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFailure$lambda$0(InventoryFragment this$0) {
        this$0.showLoading(false);
        this$0.showNoData(true);
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        ResponseBody body = response.body();
        String body2 = body != null ? body.string() : null;
        if (response.isSuccessful()) {
            String str = body2;
            try {
                if (!(str == null || str.length() == 0)) {
                    try {
                        JSONObject jsonObject = new JSONObject(body2);
                        JSONArray dataArray = jsonObject.optJSONArray(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
                        if (dataArray == null) {
                            dataArray = new JSONArray();
                        }
                        final List segmentList = new ArrayList();
                        int i = 0;
                        int length = dataArray.length();
                        while (i < length) {
                            JSONObject obj = dataArray.getJSONObject(i);
                            String segment = obj.optString("segment");
                            int totalBooksCount = obj.optInt("total_books_count");
                            JSONArray booksArray = obj.optJSONArray("books");
                            if (booksArray == null) {
                                booksArray = new JSONArray();
                            }
                            List bookList = new ArrayList();
                            int j = 0;
                            int length2 = booksArray.length();
                            while (j < length2) {
                                JSONObject bookObj = booksArray.getJSONObject(j);
                                JSONObject jsonObject2 = jsonObject;
                                String productName = bookObj.optString("product_name");
                                int quantity = bookObj.optInt(FirebaseAnalytics.Param.QUANTITY);
                                JSONArray dataArray2 = dataArray;
                                String price = bookObj.optString(FirebaseAnalytics.Param.PRICE);
                                Intrinsics.checkNotNull(productName);
                                Intrinsics.checkNotNull(price);
                                bookList.add(new BookModel(productName, quantity, price));
                                j++;
                                jsonObject = jsonObject2;
                                dataArray = dataArray2;
                            }
                            Intrinsics.checkNotNull(segment);
                            segmentList.add(new SegmentModel(segment, totalBooksCount, bookList));
                            i++;
                            jsonObject = jsonObject;
                            dataArray = dataArray;
                        }
                        FragmentActivity requireActivity = this.this$0.requireActivity();
                        final InventoryFragment inventoryFragment = this.this$0;
                        requireActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$fetchBooksBySegment$1$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                InventoryFragment$fetchBooksBySegment$1.onResponse$lambda$2(InventoryFragment.this, segmentList);
                            }
                        });
                        Log.i("RAF", "Final segment list: " + segmentList);
                    } catch (Exception e) {
                        FragmentActivity requireActivity2 = this.this$0.requireActivity();
                        if (requireActivity2 != null) {
                            final InventoryFragment inventoryFragment2 = this.this$0;
                            requireActivity2.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$fetchBooksBySegment$1$$ExternalSyntheticLambda3
                                @Override // java.lang.Runnable
                                public final void run() {
                                    InventoryFragment$fetchBooksBySegment$1.onResponse$lambda$3(InventoryFragment.this);
                                }
                            });
                        }
                        Log.e("JSON_ERROR", "Parse error: " + e.getMessage());
                    }
                    return;
                }
            } finally {
                response.close();
            }
        }
        FragmentActivity requireActivity3 = this.this$0.requireActivity();
        final InventoryFragment inventoryFragment3 = this.this$0;
        requireActivity3.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$fetchBooksBySegment$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                InventoryFragment$fetchBooksBySegment$1.onResponse$lambda$1(InventoryFragment.this);
            }
        });
        Log.e("API_ERROR", "Invalid response: " + response.code() + StringUtils.LF + body2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$1(InventoryFragment this$0) {
        this$0.showLoading(false);
        this$0.showNoData(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$2(InventoryFragment this$0, List $segmentList) {
        this$0.showLoading(false);
        if ($segmentList.isEmpty()) {
            this$0.showNoData(true);
        } else {
            this$0.showNoData(false);
            this$0.updateSegmentList($segmentList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$3(InventoryFragment this$0) {
        this$0.showLoading(false);
        this$0.showNoData(true);
    }
}
