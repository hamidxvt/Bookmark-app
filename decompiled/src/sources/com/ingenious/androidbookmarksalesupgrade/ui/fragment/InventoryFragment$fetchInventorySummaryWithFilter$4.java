package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.util.Log;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter;
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

/* compiled from: InventoryFragment.kt */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/fragment/InventoryFragment$fetchInventorySummaryWithFilter$4", "Lokhttp3/Callback;", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class InventoryFragment$fetchInventorySummaryWithFilter$4 implements Callback {
    final /* synthetic */ InventoryFragment this$0;

    InventoryFragment$fetchInventorySummaryWithFilter$4(InventoryFragment $receiver) {
        this.this$0 = $receiver;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException e) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(e, "e");
        FragmentActivity requireActivity = this.this$0.requireActivity();
        final InventoryFragment inventoryFragment = this.this$0;
        requireActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$fetchInventorySummaryWithFilter$4$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                InventoryFragment$fetchInventorySummaryWithFilter$4.onFailure$lambda$0(InventoryFragment.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFailure$lambda$0(InventoryFragment this$0) {
        this$0.showLoading(false);
        Toast.makeText(this$0.requireContext(), "Failed to load data", 0).show();
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) {
        boolean z;
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        ResponseBody body = response.body();
        String body2 = body != null ? body.string() : null;
        response.close();
        FragmentActivity requireActivity = this.this$0.requireActivity();
        final InventoryFragment inventoryFragment = this.this$0;
        requireActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$fetchInventorySummaryWithFilter$4$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                InventoryFragment.access$showLoading(InventoryFragment.this, false);
            }
        });
        if (response.isSuccessful()) {
            String str = body2;
            if (!(str == null || str.length() == 0)) {
                try {
                    JSONObject json = new JSONObject(body2);
                    JSONArray todayArray = json.optJSONArray("todays_recommended_books");
                    if (todayArray == null) {
                        todayArray = new JSONArray();
                    }
                    JSONArray allBooksArray = json.optJSONArray("all_booker_products");
                    if (allBooksArray == null) {
                        allBooksArray = new JSONArray();
                    }
                    z = this.this$0.totalBooks;
                    final List finalList = z ? this.this$0.parseProductsArray(allBooksArray, false) : this.this$0.parseProductsArray(todayArray, true);
                    FragmentActivity requireActivity2 = this.this$0.requireActivity();
                    final InventoryFragment inventoryFragment2 = this.this$0;
                    requireActivity2.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$fetchInventorySummaryWithFilter$4$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            InventoryFragment$fetchInventorySummaryWithFilter$4.onResponse$lambda$3(InventoryFragment.this, finalList);
                        }
                    });
                    return;
                } catch (Exception e) {
                    Log.e("FILTER_PARSE", String.valueOf(e.getMessage()));
                    FragmentActivity requireActivity3 = this.this$0.requireActivity();
                    final InventoryFragment inventoryFragment3 = this.this$0;
                    requireActivity3.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$fetchInventorySummaryWithFilter$4$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            InventoryFragment.access$showNoData(InventoryFragment.this, true);
                        }
                    });
                    return;
                }
            }
        }
        FragmentActivity requireActivity4 = this.this$0.requireActivity();
        final InventoryFragment inventoryFragment4 = this.this$0;
        requireActivity4.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$fetchInventorySummaryWithFilter$4$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                InventoryFragment.access$showNoData(InventoryFragment.this, true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$3(InventoryFragment this$0, List $finalList) {
        GenericAdapter genericAdapter;
        GenericAdapter genericAdapter2;
        genericAdapter = this$0.adapterStockSummary;
        GenericAdapter genericAdapter3 = null;
        if (genericAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterStockSummary");
            genericAdapter = null;
        }
        genericAdapter.clearList();
        genericAdapter2 = this$0.adapterStockSummary;
        if (genericAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterStockSummary");
        } else {
            genericAdapter3 = genericAdapter2;
        }
        genericAdapter3.addListForInventory($finalList);
        this$0.showNoData($finalList.isEmpty());
    }
}
