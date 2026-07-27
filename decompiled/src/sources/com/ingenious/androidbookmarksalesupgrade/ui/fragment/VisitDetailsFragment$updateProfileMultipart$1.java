package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.app.AlertDialog;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;

/* compiled from: VisitDetailsFragment.kt */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/fragment/VisitDetailsFragment$updateProfileMultipart$1", "Lokhttp3/Callback;", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class VisitDetailsFragment$updateProfileMultipart$1 implements Callback {
    final /* synthetic */ AlertDialog $dialog;
    final /* synthetic */ ProgressBar $progressBar;
    final /* synthetic */ VisitDetailsFragment this$0;

    VisitDetailsFragment$updateProfileMultipart$1(VisitDetailsFragment $receiver, ProgressBar $progressBar, AlertDialog $dialog) {
        this.this$0 = $receiver;
        this.$progressBar = $progressBar;
        this.$dialog = $dialog;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException e) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(e, "e");
        FragmentActivity requireActivity = this.this$0.requireActivity();
        final VisitDetailsFragment visitDetailsFragment = this.this$0;
        final ProgressBar progressBar = this.$progressBar;
        final AlertDialog alertDialog = this.$dialog;
        requireActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitDetailsFragment$updateProfileMultipart$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                VisitDetailsFragment$updateProfileMultipart$1.onFailure$lambda$0(VisitDetailsFragment.this, progressBar, alertDialog);
            }
        });
        Log.e("API_ERROR", String.valueOf(e.getMessage()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFailure$lambda$0(VisitDetailsFragment this$0, ProgressBar $progressBar, AlertDialog $dialog) {
        this$0.showLoading(false, $progressBar);
        $dialog.dismiss();
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, final Response response) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        ResponseBody body = response.body();
        final String body2 = body != null ? body.string() : null;
        FragmentActivity requireActivity = this.this$0.requireActivity();
        final VisitDetailsFragment visitDetailsFragment = this.this$0;
        final ProgressBar progressBar = this.$progressBar;
        final AlertDialog alertDialog = this.$dialog;
        requireActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitDetailsFragment$updateProfileMultipart$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                VisitDetailsFragment$updateProfileMultipart$1.onResponse$lambda$1(VisitDetailsFragment.this, progressBar, alertDialog, response, body2);
            }
        });
        response.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$1(VisitDetailsFragment this$0, ProgressBar $progressBar, AlertDialog $dialog, Response $response, String $body) {
        this$0.showLoading(false, $progressBar);
        $dialog.dismiss();
        Toast.makeText(this$0.requireActivity(), "Request Sent Successfully", 0).show();
        if ($response.isSuccessful()) {
            String str = $body;
            if (!(str == null || str.length() == 0)) {
                Log.d("SUCCESS", $body);
                return;
            }
        }
        Log.e("API_ERROR", "Invalid Response: " + $response.code() + StringUtils.LF + $body);
    }
}
