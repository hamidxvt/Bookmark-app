package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityCreateRequestBinding;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/* compiled from: CreateRequestActivity.kt */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/activity/CreateRequestActivity$submitRequest$1", "Lokhttp3/Callback;", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class CreateRequestActivity$submitRequest$1 implements Callback {
    final /* synthetic */ CreateRequestActivity this$0;

    CreateRequestActivity$submitRequest$1(CreateRequestActivity $receiver) {
        this.this$0 = $receiver;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException e) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(e, "e");
        CreateRequestActivity createRequestActivity = this.this$0;
        final CreateRequestActivity createRequestActivity2 = this.this$0;
        createRequestActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CreateRequestActivity$submitRequest$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CreateRequestActivity$submitRequest$1.onFailure$lambda$0(CreateRequestActivity.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFailure$lambda$0(CreateRequestActivity this$0) {
        ActivityCreateRequestBinding activityCreateRequestBinding;
        ActivityCreateRequestBinding activityCreateRequestBinding2;
        activityCreateRequestBinding = this$0.binding;
        ActivityCreateRequestBinding activityCreateRequestBinding3 = null;
        if (activityCreateRequestBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCreateRequestBinding = null;
        }
        activityCreateRequestBinding.progressBar.setVisibility(8);
        activityCreateRequestBinding2 = this$0.binding;
        if (activityCreateRequestBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCreateRequestBinding3 = activityCreateRequestBinding2;
        }
        activityCreateRequestBinding3.btnSubmit.setEnabled(true);
        Toast.makeText(this$0, "Network error", 0).show();
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, final Response response) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        CreateRequestActivity createRequestActivity = this.this$0;
        final CreateRequestActivity createRequestActivity2 = this.this$0;
        createRequestActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CreateRequestActivity$submitRequest$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                CreateRequestActivity$submitRequest$1.onResponse$lambda$1(CreateRequestActivity.this, response);
            }
        });
        response.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$1(CreateRequestActivity this$0, Response $response) {
        ActivityCreateRequestBinding activityCreateRequestBinding;
        ActivityCreateRequestBinding activityCreateRequestBinding2;
        activityCreateRequestBinding = this$0.binding;
        ActivityCreateRequestBinding activityCreateRequestBinding3 = null;
        if (activityCreateRequestBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCreateRequestBinding = null;
        }
        activityCreateRequestBinding.progressBar.setVisibility(8);
        activityCreateRequestBinding2 = this$0.binding;
        if (activityCreateRequestBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCreateRequestBinding3 = activityCreateRequestBinding2;
        }
        activityCreateRequestBinding3.btnSubmit.setEnabled(true);
        if ($response.isSuccessful()) {
            Toast.makeText(this$0, "Request submitted successfully", 0).show();
            this$0.setResult(-1);
            this$0.finish();
        } else {
            Toast.makeText(this$0, "Failed: " + $response.code(), 0).show();
        }
    }
}
