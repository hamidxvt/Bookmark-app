package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.Constants;
import com.ingenious.androidbookmarksalesupgrade.model.RequestModel;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
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

/* compiled from: RequestDashboard.kt */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/activity/RequestDashboard$fetchRequestList$1", "Lokhttp3/Callback;", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class RequestDashboard$fetchRequestList$1 implements Callback {
    final /* synthetic */ RequestDashboard this$0;

    RequestDashboard$fetchRequestList$1(RequestDashboard $receiver) {
        this.this$0 = $receiver;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException e) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(e, "e");
        RequestDashboard requestDashboard = this.this$0;
        final RequestDashboard requestDashboard2 = this.this$0;
        requestDashboard.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.RequestDashboard$fetchRequestList$1$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                RequestDashboard$fetchRequestList$1.onFailure$lambda$0(RequestDashboard.this);
            }
        });
        Log.e("API_ERROR", "Request failed: " + e.getMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFailure$lambda$0(RequestDashboard this$0) {
        this$0.showLoading(false);
        this$0.showNoData(true);
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) {
        JSONObject root;
        JSONArray dataArray;
        JSONObject root2;
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        ResponseBody body = response.body();
        String body2 = body != null ? body.string() : null;
        if (response.isSuccessful()) {
            String str = body2;
            if (!(str == null || str.length() == 0)) {
                try {
                    try {
                        root = new JSONObject(body2);
                        dataArray = root.optJSONArray(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
                    } catch (Exception e) {
                        RequestDashboard requestDashboard = this.this$0;
                        final RequestDashboard requestDashboard2 = this.this$0;
                        requestDashboard.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.RequestDashboard$fetchRequestList$1$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                RequestDashboard$fetchRequestList$1.onResponse$lambda$3(RequestDashboard.this);
                            }
                        });
                        Log.e("JSON_ERROR", "Parse error: " + e.getMessage());
                    }
                    if (dataArray == null) {
                        return;
                    }
                    final List requestList = new ArrayList();
                    int i = 0;
                    int length = dataArray.length();
                    while (i < length) {
                        JSONObject obj = dataArray.getJSONObject(i);
                        int id = obj.optInt(Constant.VISIT_ID);
                        String title = obj.optString("title", "Untitled Request");
                        String category = obj.optString("category", "General");
                        String details = obj.optString("details", "");
                        JSONArray photoArray = obj.optJSONArray("photo");
                        List photos = new ArrayList();
                        if (photoArray != null) {
                            root2 = root;
                            int length2 = photoArray.length();
                            int j = 0;
                            while (j < length2) {
                                int i2 = length2;
                                String optString = photoArray.optString(j);
                                Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
                                photos.add(optString);
                                j++;
                                length2 = i2;
                            }
                        } else {
                            root2 = root;
                        }
                        String status = obj.optString(NotificationCompat.CATEGORY_STATUS, "pending");
                        String requestId = obj.optString("request-id", "N/A");
                        String createdAt = obj.optString("created_at", "-");
                        Intrinsics.checkNotNull(title);
                        Intrinsics.checkNotNull(category);
                        Intrinsics.checkNotNull(details);
                        Intrinsics.checkNotNull(status);
                        Intrinsics.checkNotNull(requestId);
                        Intrinsics.checkNotNull(createdAt);
                        requestList.add(new RequestModel(id, title, category, details, photos, status, requestId, createdAt));
                        i++;
                        root = root2;
                    }
                    RequestDashboard requestDashboard3 = this.this$0;
                    final RequestDashboard requestDashboard4 = this.this$0;
                    requestDashboard3.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.RequestDashboard$fetchRequestList$1$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            RequestDashboard$fetchRequestList$1.onResponse$lambda$2(RequestDashboard.this, requestList);
                        }
                    });
                    return;
                } finally {
                    response.close();
                }
            }
        }
        RequestDashboard requestDashboard5 = this.this$0;
        final RequestDashboard requestDashboard6 = this.this$0;
        requestDashboard5.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.RequestDashboard$fetchRequestList$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RequestDashboard$fetchRequestList$1.onResponse$lambda$1(RequestDashboard.this);
            }
        });
        Log.e("API_ERROR", "Invalid response: " + response.code() + StringUtils.LF + body2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$1(RequestDashboard this$0) {
        this$0.showLoading(false);
        this$0.showNoData(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$2(RequestDashboard this$0, List $requestList) {
        this$0.showLoading(false);
        if ($requestList.isEmpty()) {
            this$0.showNoData(true);
        } else {
            this$0.showNoData(false);
            this$0.updateRequestList($requestList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$3(RequestDashboard this$0) {
        this$0.showLoading(false);
        this$0.showNoData(true);
    }
}
