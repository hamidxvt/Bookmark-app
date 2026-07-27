package com.ingenious.androidbookmarksalesupgrade.bottomsheet;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.ingenious.androidbookmarksalesupgrade.databinding.BottomSheetAllProductsCartFragmentBinding;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity;
import com.ingenious.androidbookmarksalesupgrade.utils.AppToast;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: AllProductsCartBottomSheet.kt */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/bottomsheet/AllProductsCartBottomSheet$addProductsToVisit$2", "Lokhttp3/Callback;", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class AllProductsCartBottomSheet$addProductsToVisit$2 implements Callback {
    final /* synthetic */ AllProductsCartBottomSheet this$0;

    AllProductsCartBottomSheet$addProductsToVisit$2(AllProductsCartBottomSheet $receiver) {
        this.this$0 = $receiver;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, final IOException e) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(e, "e");
        FragmentActivity requireActivity = this.this$0.requireActivity();
        final AllProductsCartBottomSheet allProductsCartBottomSheet = this.this$0;
        requireActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.AllProductsCartBottomSheet$addProductsToVisit$2$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AllProductsCartBottomSheet$addProductsToVisit$2.onFailure$lambda$0(e, allProductsCartBottomSheet);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFailure$lambda$0(IOException $e, AllProductsCartBottomSheet this$0) {
        BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding;
        BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding2;
        AppToast.INSTANCE.showToast("Failed to add products: " + $e.getMessage());
        bottomSheetAllProductsCartFragmentBinding = this$0.binding;
        BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding3 = null;
        if (bottomSheetAllProductsCartFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            bottomSheetAllProductsCartFragmentBinding = null;
        }
        bottomSheetAllProductsCartFragmentBinding.addToVisitBtn.setVisibility(0);
        bottomSheetAllProductsCartFragmentBinding2 = this$0.binding;
        if (bottomSheetAllProductsCartFragmentBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            bottomSheetAllProductsCartFragmentBinding3 = bottomSheetAllProductsCartFragmentBinding2;
        }
        bottomSheetAllProductsCartFragmentBinding3.addingProductsBar.setVisibility(8);
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, final Response response) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        ResponseBody body = response.body();
        final String body2 = body != null ? body.string() : null;
        Log.d("API_RESPONSE", "Response code: " + response.code() + " | body: " + body2);
        FragmentActivity requireActivity = this.this$0.requireActivity();
        final AllProductsCartBottomSheet allProductsCartBottomSheet = this.this$0;
        requireActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.AllProductsCartBottomSheet$addProductsToVisit$2$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AllProductsCartBottomSheet$addProductsToVisit$2.onResponse$lambda$1(Response.this, allProductsCartBottomSheet, body2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$1(Response $response, AllProductsCartBottomSheet this$0, String $body) {
        BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding;
        BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding2;
        BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding3;
        BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding4;
        BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding5 = null;
        if (!$response.isSuccessful()) {
            bottomSheetAllProductsCartFragmentBinding = this$0.binding;
            if (bottomSheetAllProductsCartFragmentBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                bottomSheetAllProductsCartFragmentBinding = null;
            }
            bottomSheetAllProductsCartFragmentBinding.addToVisitBtn.setVisibility(0);
            bottomSheetAllProductsCartFragmentBinding2 = this$0.binding;
            if (bottomSheetAllProductsCartFragmentBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                bottomSheetAllProductsCartFragmentBinding5 = bottomSheetAllProductsCartFragmentBinding2;
            }
            bottomSheetAllProductsCartFragmentBinding5.addingProductsBar.setVisibility(8);
            AppToast.INSTANCE.showToast("Error: " + $response.code() + " - " + $body);
            return;
        }
        AppToast.INSTANCE.showToast("Products added successfully");
        this$0.dismiss();
        bottomSheetAllProductsCartFragmentBinding3 = this$0.binding;
        if (bottomSheetAllProductsCartFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            bottomSheetAllProductsCartFragmentBinding3 = null;
        }
        bottomSheetAllProductsCartFragmentBinding3.addToVisitBtn.setVisibility(0);
        bottomSheetAllProductsCartFragmentBinding4 = this$0.binding;
        if (bottomSheetAllProductsCartFragmentBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            bottomSheetAllProductsCartFragmentBinding5 = bottomSheetAllProductsCartFragmentBinding4;
        }
        bottomSheetAllProductsCartFragmentBinding5.addingProductsBar.setVisibility(8);
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity");
        ((AllProductsActivity) requireActivity).finishAllAct();
    }
}
