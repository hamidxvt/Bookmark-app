package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.common.net.HttpHeaders;
import com.google.firebase.messaging.Constants;
import com.ingenious.androidbookmarksalesupgrade.adapter.RequestAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityRequestDashboardBinding;
import com.ingenious.androidbookmarksalesupgrade.model.RequestModel;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* compiled from: RequestDashboard.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\b\u0010\f\u001a\u00020\tH\u0002J\b\u0010\r\u001a\u00020\tH\u0002J\n\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J\u0016\u0010\u0010\u001a\u00020\t2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002J\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0016H\u0002J\"\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/RequestDashboard;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityRequestDashboardBinding;", "requestAdapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/RequestAdapter;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupRecyclerView", "fetchRequestList", "getToken", "", "updateRequestList", "list", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/RequestModel;", "showLoading", "isLoading", "", "showNoData", "show", "onActivityResult", "requestCode", "", "resultCode", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Landroid/content/Intent;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class RequestDashboard extends AppCompatActivity {
    private ActivityRequestDashboardBinding binding;
    private RequestAdapter requestAdapter;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRequestDashboardBinding activityRequestDashboardBinding = null;
        EdgeToEdge.enable$default(this, null, null, 3, null);
        this.binding = ActivityRequestDashboardBinding.inflate(getLayoutInflater());
        ActivityRequestDashboardBinding activityRequestDashboardBinding2 = this.binding;
        if (activityRequestDashboardBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRequestDashboardBinding2 = null;
        }
        setContentView(activityRequestDashboardBinding2.getRoot());
        ExtensionKt.belowStatusBarText(this);
        setupRecyclerView();
        fetchRequestList();
        ActivityRequestDashboardBinding activityRequestDashboardBinding3 = this.binding;
        if (activityRequestDashboardBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRequestDashboardBinding3 = null;
        }
        activityRequestDashboardBinding3.createRequestButton.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.RequestDashboard$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RequestDashboard.onCreate$lambda$0(RequestDashboard.this, view);
            }
        });
        ActivityRequestDashboardBinding activityRequestDashboardBinding4 = this.binding;
        if (activityRequestDashboardBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityRequestDashboardBinding = activityRequestDashboardBinding4;
        }
        activityRequestDashboardBinding.backIcon.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.RequestDashboard$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RequestDashboard.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(RequestDashboard this$0, View it) {
        Intent intent = new Intent(this$0, (Class<?>) CreateRequestActivity.class);
        this$0.startActivityForResult(intent, 1001);
    }

    private final void setupRecyclerView() {
        this.requestAdapter = new RequestAdapter(new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.RequestDashboard$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit unit;
                unit = RequestDashboard.setupRecyclerView$lambda$2(RequestDashboard.this, (RequestModel) obj);
                return unit;
            }
        });
        ActivityRequestDashboardBinding activityRequestDashboardBinding = this.binding;
        RequestAdapter requestAdapter = null;
        if (activityRequestDashboardBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRequestDashboardBinding = null;
        }
        RecyclerView $this$setupRecyclerView_u24lambda_u243 = activityRequestDashboardBinding.recyclerRequests;
        $this$setupRecyclerView_u24lambda_u243.setLayoutManager(new LinearLayoutManager(this));
        RequestAdapter requestAdapter2 = this.requestAdapter;
        if (requestAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestAdapter");
        } else {
            requestAdapter = requestAdapter2;
        }
        $this$setupRecyclerView_u24lambda_u243.setAdapter(requestAdapter);
        $this$setupRecyclerView_u24lambda_u243.setHasFixedSize(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupRecyclerView$lambda$2(RequestDashboard this$0, RequestModel request) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intent intent = new Intent(this$0, (Class<?>) RequestDetailActivity.class);
        intent.putExtra("request_id", request.getRequestId());
        intent.putExtra("title", request.getTitle());
        intent.putExtra("category", request.getCategory());
        intent.putExtra("details", request.getDetails());
        intent.putExtra(NotificationCompat.CATEGORY_STATUS, request.getStatus());
        intent.putExtra("created_at", request.getCreatedAt());
        intent.putStringArrayListExtra("photos", new ArrayList<>(request.getPhoto()));
        this$0.startActivity(intent);
        return Unit.INSTANCE;
    }

    private final void fetchRequestList() {
        showLoading(true);
        OkHttpClient client = new OkHttpClient();
        Request.Builder addHeader = new Request.Builder().url("https://staging.bookmark.services/api/requests/list").addHeader(HttpHeaders.ACCEPT, "application/json");
        String token = getToken();
        if (token == null) {
            token = "";
        }
        Request request = addHeader.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
        client.newCall(request).enqueue(new RequestDashboard$fetchRequestList$1(this));
    }

    private final String getToken() {
        SharedPreferences sharedPref = getSharedPreferences("BookmarkApp", 0);
        return sharedPref.getString("AUTH_TOKEN", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateRequestList(List<RequestModel> list) {
        RequestAdapter requestAdapter = this.requestAdapter;
        ActivityRequestDashboardBinding activityRequestDashboardBinding = null;
        if (requestAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestAdapter");
            requestAdapter = null;
        }
        requestAdapter.submitList(list);
        ActivityRequestDashboardBinding activityRequestDashboardBinding2 = this.binding;
        if (activityRequestDashboardBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityRequestDashboardBinding = activityRequestDashboardBinding2;
        }
        activityRequestDashboardBinding.recyclerRequests.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoading(boolean isLoading) {
        ActivityRequestDashboardBinding activityRequestDashboardBinding = this.binding;
        ActivityRequestDashboardBinding activityRequestDashboardBinding2 = null;
        if (activityRequestDashboardBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRequestDashboardBinding = null;
        }
        activityRequestDashboardBinding.progressBar.setVisibility(isLoading ? 0 : 8);
        ActivityRequestDashboardBinding activityRequestDashboardBinding3 = this.binding;
        if (activityRequestDashboardBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRequestDashboardBinding3 = null;
        }
        activityRequestDashboardBinding3.recyclerRequests.setVisibility(isLoading ? 8 : 0);
        ActivityRequestDashboardBinding activityRequestDashboardBinding4 = this.binding;
        if (activityRequestDashboardBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityRequestDashboardBinding2 = activityRequestDashboardBinding4;
        }
        activityRequestDashboardBinding2.noDataText.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showNoData(boolean show) {
        ActivityRequestDashboardBinding activityRequestDashboardBinding = this.binding;
        ActivityRequestDashboardBinding activityRequestDashboardBinding2 = null;
        if (activityRequestDashboardBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRequestDashboardBinding = null;
        }
        activityRequestDashboardBinding.noDataText.setVisibility(show ? 0 : 8);
        ActivityRequestDashboardBinding activityRequestDashboardBinding3 = this.binding;
        if (activityRequestDashboardBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityRequestDashboardBinding2 = activityRequestDashboardBinding3;
        }
        activityRequestDashboardBinding2.recyclerRequests.setVisibility(show ? 8 : 0);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == -1) {
            fetchRequestList();
        }
    }
}
