package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.messaging.Constants;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.NotificationAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityNotificationDisplayBinding;
import com.ingenious.androidbookmarksalesupgrade.model.NotificationData;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NotificationDisplayAct.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0016\u0010\u001d\u001a\u00020\u00112\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0002J\u0010\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u0017H\u0002J\u0010\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#H\u0002J\n\u0010$\u001a\u0004\u0018\u00010\u0017H\u0002J\b\u0010%\u001a\u00020\u0011H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/NotificationDisplayAct;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityNotificationDisplayBinding;", "client", "Lokhttp3/OkHttpClient;", "getClient", "()Lokhttp3/OkHttpClient;", "client$delegate", "Lkotlin/Lazy;", "job", "Lkotlinx/coroutines/Job;", "adapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/NotificationAdapter;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupRecyclerView", "fetchNotifications", "token", "", "parseNotificationList", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/NotificationData;", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lorg/json/JSONArray;", "showNotificationData", "list", "showEmptyState", "message", "showLoading", "isLoading", "", "getToken", "onDestroy", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class NotificationDisplayAct extends AppCompatActivity {
    private NotificationAdapter adapter;
    private ActivityNotificationDisplayBinding binding;

    /* renamed from: client$delegate, reason: from kotlin metadata */
    private final Lazy client = LazyKt.lazy(new Function0() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.NotificationDisplayAct$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            OkHttpClient client_delegate$lambda$0;
            client_delegate$lambda$0 = NotificationDisplayAct.client_delegate$lambda$0();
            return client_delegate$lambda$0;
        }
    });
    private Job job;

    /* JADX INFO: Access modifiers changed from: private */
    public final OkHttpClient getClient() {
        return (OkHttpClient) this.client.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final OkHttpClient client_delegate$lambda$0() {
        return new OkHttpClient.Builder().connectTimeout(15L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).build();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNotificationDisplayBinding activityNotificationDisplayBinding = null;
        EdgeToEdge.enable$default(this, null, null, 3, null);
        this.binding = ActivityNotificationDisplayBinding.inflate(getLayoutInflater());
        ActivityNotificationDisplayBinding activityNotificationDisplayBinding2 = this.binding;
        if (activityNotificationDisplayBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationDisplayBinding2 = null;
        }
        setContentView(activityNotificationDisplayBinding2.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new OnApplyWindowInsetsListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.NotificationDisplayAct$$ExternalSyntheticLambda1
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat onCreate$lambda$1;
                onCreate$lambda$1 = NotificationDisplayAct.onCreate$lambda$1(view, windowInsetsCompat);
                return onCreate$lambda$1;
            }
        });
        setupRecyclerView();
        String token = getToken();
        if (token != null) {
            fetchNotifications(token);
        } else {
            showEmptyState("Session expired. Please log in again.");
        }
        ActivityNotificationDisplayBinding activityNotificationDisplayBinding3 = this.binding;
        if (activityNotificationDisplayBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityNotificationDisplayBinding = activityNotificationDisplayBinding3;
        }
        activityNotificationDisplayBinding.backIcon.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.NotificationDisplayAct$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NotificationDisplayAct.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat onCreate$lambda$1(View v, WindowInsetsCompat insets) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(insets, "insets");
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(systemBars, "getInsets(...)");
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        return insets;
    }

    private final void setupRecyclerView() {
        this.adapter = new NotificationAdapter(CollectionsKt.emptyList());
        ActivityNotificationDisplayBinding activityNotificationDisplayBinding = this.binding;
        NotificationAdapter notificationAdapter = null;
        if (activityNotificationDisplayBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationDisplayBinding = null;
        }
        activityNotificationDisplayBinding.notificationList.setLayoutManager(new LinearLayoutManager(this));
        ActivityNotificationDisplayBinding activityNotificationDisplayBinding2 = this.binding;
        if (activityNotificationDisplayBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationDisplayBinding2 = null;
        }
        RecyclerView recyclerView = activityNotificationDisplayBinding2.notificationList;
        NotificationAdapter notificationAdapter2 = this.adapter;
        if (notificationAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            notificationAdapter = notificationAdapter2;
        }
        recyclerView.setAdapter(notificationAdapter);
    }

    private final void fetchNotifications(String token) {
        Job launch$default;
        showLoading(true);
        launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new NotificationDisplayAct$fetchNotifications$1(token, this, null), 3, null);
        this.job = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<NotificationData> parseNotificationList(JSONArray data) {
        List list = new ArrayList();
        int length = data.length();
        for (int i = 0; i < length; i++) {
            JSONObject obj = data.getJSONObject(i);
            int optInt = obj.optInt(Constant.VISIT_ID);
            String optString = obj.optString("title");
            Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
            String optString2 = obj.optString("message");
            Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
            String optString3 = obj.optString("image", null);
            String optString4 = obj.optString("created_at");
            Intrinsics.checkNotNullExpressionValue(optString4, "optString(...)");
            list.add(new NotificationData(optInt, optString, optString2, optString3, optString4));
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showNotificationData(List<NotificationData> list) {
        ActivityNotificationDisplayBinding activityNotificationDisplayBinding = this.binding;
        NotificationAdapter notificationAdapter = null;
        if (activityNotificationDisplayBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationDisplayBinding = null;
        }
        activityNotificationDisplayBinding.tvNoData.setVisibility(8);
        ActivityNotificationDisplayBinding activityNotificationDisplayBinding2 = this.binding;
        if (activityNotificationDisplayBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationDisplayBinding2 = null;
        }
        activityNotificationDisplayBinding2.notificationList.setVisibility(0);
        NotificationAdapter notificationAdapter2 = this.adapter;
        if (notificationAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            notificationAdapter = notificationAdapter2;
        }
        notificationAdapter.updateList(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showEmptyState(String message) {
        ActivityNotificationDisplayBinding activityNotificationDisplayBinding = this.binding;
        ActivityNotificationDisplayBinding activityNotificationDisplayBinding2 = null;
        if (activityNotificationDisplayBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationDisplayBinding = null;
        }
        activityNotificationDisplayBinding.notificationList.setVisibility(8);
        ActivityNotificationDisplayBinding activityNotificationDisplayBinding3 = this.binding;
        if (activityNotificationDisplayBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationDisplayBinding3 = null;
        }
        activityNotificationDisplayBinding3.tvNoData.setText(message);
        ActivityNotificationDisplayBinding activityNotificationDisplayBinding4 = this.binding;
        if (activityNotificationDisplayBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityNotificationDisplayBinding2 = activityNotificationDisplayBinding4;
        }
        activityNotificationDisplayBinding2.tvNoData.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoading(boolean isLoading) {
        ActivityNotificationDisplayBinding activityNotificationDisplayBinding = this.binding;
        if (activityNotificationDisplayBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationDisplayBinding = null;
        }
        activityNotificationDisplayBinding.progressBar.setVisibility(isLoading ? 0 : 8);
    }

    private final String getToken() {
        SharedPreferences sharedPref = getSharedPreferences("BookmarkApp", 0);
        Log.i("TAG", "getToken: " + sharedPref.getString("AUTH_TOKEN", null));
        return sharedPref.getString("AUTH_TOKEN", null);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }
}
