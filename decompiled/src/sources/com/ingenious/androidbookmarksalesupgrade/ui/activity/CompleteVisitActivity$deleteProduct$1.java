package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.util.Log;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.ingenious.androidbookmarksalesupgrade.utils.LoggingInterceptor;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: CompleteVisitActivity.kt */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/activity/CompleteVisitActivity$deleteProduct$1", "Lokhttp3/Callback;", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class CompleteVisitActivity$deleteProduct$1 implements Callback {
    final /* synthetic */ CompleteVisitActivity this$0;

    CompleteVisitActivity$deleteProduct$1(CompleteVisitActivity $receiver) {
        this.this$0 = $receiver;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, final IOException e) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(e, "e");
        Log.e(LoggingInterceptor.TAG, "Failed to delete product: " + e.getMessage());
        CompleteVisitActivity completeVisitActivity = this.this$0;
        final CompleteVisitActivity completeVisitActivity2 = this.this$0;
        completeVisitActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$deleteProduct$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                CompleteVisitActivity$deleteProduct$1.onFailure$lambda$0(CompleteVisitActivity.this, e);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFailure$lambda$0(CompleteVisitActivity this$0, IOException $e) {
        Toast.makeText(this$0, "Failed to delete product: " + $e.getMessage(), 0).show();
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, final Response response) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        ResponseBody body = response.body();
        String body2 = body != null ? body.string() : null;
        Log.d(LoggingInterceptor.TAG, "Delete product response: " + body2);
        CompleteVisitActivity completeVisitActivity = this.this$0;
        final CompleteVisitActivity completeVisitActivity2 = this.this$0;
        completeVisitActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$deleteProduct$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CompleteVisitActivity$deleteProduct$1.onResponse$lambda$1(Response.this, completeVisitActivity2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$1(Response $response, CompleteVisitActivity this$0) {
        if ($response.isSuccessful()) {
            Toast.makeText(this$0, "Product deleted successfully", 0).show();
            this$0.fetchVisitProducts();
        } else {
            Toast.makeText(this$0, "Failed to delete product", 0).show();
        }
    }
}
