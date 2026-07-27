package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.ActivityFilterAdapter;
import com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentRecentActivityBinding;
import com.ingenious.androidbookmarksalesupgrade.listener.OnFilterItemClickListener;
import com.ingenious.androidbookmarksalesupgrade.model.ActivityFilerTxt;
import com.ingenious.androidbookmarksalesupgrade.model.ActivityLog;
import com.ingenious.androidbookmarksalesupgrade.model.DeliveredBooks;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.HelpScreenAct;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: RecentActivityFragment.kt */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J&\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010+\u001a\u00020\"H\u0002J\u0018\u0010,\u001a\u00020\"2\u0006\u0010-\u001a\u00020\u00182\u0006\u0010.\u001a\u00020/H\u0016J\b\u00100\u001a\u00020\"H\u0002J.\u00101\u001a\u00020\"2\u0006\u00102\u001a\u00020\f2\b\b\u0002\u00103\u001a\u00020\f2\b\b\u0002\u00104\u001a\u00020\f2\b\b\u0002\u00105\u001a\u00020\fH\u0002J\u001c\u00106\u001a\b\u0012\u0004\u0012\u000208072\u0006\u00109\u001a\u00020:H\u0082@¢\u0006\u0002\u0010;J\n\u0010<\u001a\u0004\u0018\u00010\fH\u0002J\u0010\u0010=\u001a\u00020\"2\u0006\u0010>\u001a\u00020\fH\u0002J\u0010\u0010?\u001a\u00020\"2\u0006\u0010@\u001a\u00020AH\u0002J\u0016\u0010B\u001a\u00020\"2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020807H\u0002J\b\u0010D\u001a\u00020\"H\u0016J \u0010E\u001a\u00020\"2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020G2\u0006\u0010I\u001a\u00020GH\u0002J(\u0010J\u001a\u00020\"2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020G2\u0006\u0010I\u001a\u00020G2\u0006\u0010K\u001a\u00020GH\u0002J\u0018\u0010L\u001a\u00020\"2\u0006\u00102\u001a\u00020\f2\u0006\u0010M\u001a\u00020\fH\u0002J\u0010\u0010N\u001a\u00020\"2\u0006\u0010O\u001a\u00020&H\u0002J\b\u0010P\u001a\u00020AH\u0002J-\u0010Q\u001a\u00020\"2\u0006\u0010R\u001a\u00020\u00182\u000e\u0010S\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0T2\u0006\u0010U\u001a\u00020VH\u0016¢\u0006\u0002\u0010WJ\b\u0010X\u001a\u00020\"H\u0002J\u0018\u0010Y\u001a\u0004\u0018\u00010Z2\u0006\u0010[\u001a\u00020\u0018H\u0082@¢\u0006\u0002\u0010\\R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001a\u0010\u0014\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001cR\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006]"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/RecentActivityFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/ingenious/androidbookmarksalesupgrade/listener/OnFilterItemClickListener;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentRecentActivityBinding;", "adapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/ActivityFilterAdapter;", "activityAdapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/ActivityLogAdapter;", "storeActionType", "", "getStoreActionType", "()Ljava/lang/String;", "setStoreActionType", "(Ljava/lang/String;)V", "storeDateRange", "getStoreDateRange", "setStoreDateRange", "storeByArea", "getStoreByArea", "setStoreByArea", "STORAGE_PERMISSION_CODE", "", "client", "Lokhttp3/OkHttpClient;", "getClient", "()Lokhttp3/OkHttpClient;", "client$delegate", "Lkotlin/Lazy;", "job", "Lkotlinx/coroutines/Job;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "setupRecyclerView", "onFilterItemClick", "position", "item", "Lcom/ingenious/androidbookmarksalesupgrade/model/ActivityFilerTxt;", "mainFilterDialog", "fetchActivityLog", "token", "date", "action", "subject", "parseActivityList", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/ActivityLog;", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lorg/json/JSONArray;", "(Lorg/json/JSONArray;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getToken", "showEmptyState", "message", "showLoading", "isLoading", "", "showActivityLog", "list", "onDestroy", "activeBg", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, "Landroid/widget/TextView;", "deActive", "deActive2", "activeBg2", "deActive3", "searchActivityLog", SearchIntents.EXTRA_QUERY, "captureAndSaveScreenshot", "view", "checkAndRequestPermission", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "exportDialog", "fetchVisitProducts", "Lcom/ingenious/androidbookmarksalesupgrade/model/DeliveredBooks;", "visitId", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class RecentActivityFragment extends Fragment implements OnFilterItemClickListener {
    private ActivityLogAdapter activityAdapter;
    private ActivityFilterAdapter adapter;
    private FragmentRecentActivityBinding binding;
    private Job job;
    private String storeActionType = "New Customer";
    private String storeDateRange = "";
    private String storeByArea = "";
    private final int STORAGE_PERMISSION_CODE = 1001;

    /* renamed from: client$delegate, reason: from kotlin metadata */
    private final Lazy client = LazyKt.lazy(new Function0() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            OkHttpClient client_delegate$lambda$0;
            client_delegate$lambda$0 = RecentActivityFragment.client_delegate$lambda$0();
            return client_delegate$lambda$0;
        }
    });

    public final String getStoreActionType() {
        return this.storeActionType;
    }

    public final void setStoreActionType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.storeActionType = str;
    }

    public final String getStoreDateRange() {
        return this.storeDateRange;
    }

    public final void setStoreDateRange(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.storeDateRange = str;
    }

    public final String getStoreByArea() {
        return this.storeByArea;
    }

    public final void setStoreByArea(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.storeByArea = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OkHttpClient getClient() {
        return (OkHttpClient) this.client.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final OkHttpClient client_delegate$lambda$0() {
        return new OkHttpClient.Builder().connectTimeout(15L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).build();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.binding = FragmentRecentActivityBinding.inflate(getLayoutInflater(), container, false);
        final FragmentRecentActivityBinding $this$onCreateView_u24lambda_u244 = this.binding;
        FragmentRecentActivityBinding fragmentRecentActivityBinding = null;
        if ($this$onCreateView_u24lambda_u244 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            $this$onCreateView_u24lambda_u244 = null;
        }
        $this$onCreateView_u24lambda_u244.mainFiler.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecentActivityFragment.this.mainFilterDialog();
            }
        });
        setupRecyclerView();
        $this$onCreateView_u24lambda_u244.filterSearch.addTextChangedListener(new TextWatcher() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$onCreateView$1$2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String token;
                String query = StringsKt.trim((CharSequence) String.valueOf(s)).toString();
                token = RecentActivityFragment.this.getToken();
                if (token != null) {
                    if (query.length() > 0) {
                        RecentActivityFragment.this.searchActivityLog(token, query);
                        return;
                    }
                }
                if (query.length() == 0) {
                    RecentActivityFragment.this.showEmptyState("Search something...");
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        $this$onCreateView_u24lambda_u244.cancelTv.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecentActivityFragment.onCreateView$lambda$4$lambda$2(FragmentRecentActivityBinding.this, view);
            }
        });
        FragmentRecentActivityBinding fragmentRecentActivityBinding2 = this.binding;
        if (fragmentRecentActivityBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentRecentActivityBinding2 = null;
        }
        fragmentRecentActivityBinding2.performanceMenu.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecentActivityFragment.this.exportDialog();
            }
        });
        FragmentRecentActivityBinding fragmentRecentActivityBinding3 = this.binding;
        if (fragmentRecentActivityBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentRecentActivityBinding = fragmentRecentActivityBinding3;
        }
        return fragmentRecentActivityBinding.getRoot();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$4$lambda$2(FragmentRecentActivityBinding $this_apply, View it) {
        $this_apply.filterSearch.setText((CharSequence) null);
    }

    private final void setupRecyclerView() {
        ArrayList list = new ArrayList();
        list.add(new ActivityFilerTxt("today"));
        list.add(new ActivityFilerTxt("yesterday"));
        list.add(new ActivityFilerTxt("school"));
        list.add(new ActivityFilerTxt("Visits"));
        this.adapter = new ActivityFilterAdapter(list, this);
        FragmentRecentActivityBinding fragmentRecentActivityBinding = this.binding;
        ActivityLogAdapter activityLogAdapter = null;
        if (fragmentRecentActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentRecentActivityBinding = null;
        }
        fragmentRecentActivityBinding.activityListRv.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        FragmentRecentActivityBinding fragmentRecentActivityBinding2 = this.binding;
        if (fragmentRecentActivityBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentRecentActivityBinding2 = null;
        }
        RecyclerView recyclerView = fragmentRecentActivityBinding2.activityListRv;
        ActivityFilterAdapter activityFilterAdapter = this.adapter;
        if (activityFilterAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            activityFilterAdapter = null;
        }
        recyclerView.setAdapter(activityFilterAdapter);
        FragmentRecentActivityBinding fragmentRecentActivityBinding3 = this.binding;
        if (fragmentRecentActivityBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentRecentActivityBinding3 = null;
        }
        fragmentRecentActivityBinding3.activityResultRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        this.activityAdapter = new ActivityLogAdapter(requireContext, CollectionsKt.emptyList(), null, null, 12, null);
        FragmentRecentActivityBinding fragmentRecentActivityBinding4 = this.binding;
        if (fragmentRecentActivityBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentRecentActivityBinding4 = null;
        }
        RecyclerView recyclerView2 = fragmentRecentActivityBinding4.activityResultRv;
        ActivityLogAdapter activityLogAdapter2 = this.activityAdapter;
        if (activityLogAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityAdapter");
        } else {
            activityLogAdapter = activityLogAdapter2;
        }
        recyclerView2.setAdapter(activityLogAdapter);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.OnFilterItemClickListener
    public void onFilterItemClick(int position, ActivityFilerTxt item) {
        Intrinsics.checkNotNullParameter(item, "item");
        String token = getToken();
        if (token != null) {
            if (Intrinsics.areEqual(item.getTitle(), "today") || Intrinsics.areEqual(item.getTitle(), "yesterday")) {
                fetchActivityLog$default(this, token, item.getTitle(), null, null, 12, null);
            } else if (Intrinsics.areEqual(item.getTitle(), "school")) {
                fetchActivityLog$default(this, token, null, "New Customer", null, 10, null);
            } else if (Intrinsics.areEqual(item.getTitle(), "Visits")) {
                fetchActivityLog$default(this, token, null, "Visited", null, 10, null);
            }
            Log.i("TAG", "onFilterItemClick: " + item.getTitle());
            return;
        }
        showEmptyState("Session expired. Please log in again.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void mainFilterDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_recent_activty_filter_main, (ViewGroup) null);
        final AlertDialog dialog = new AlertDialog.Builder(requireContext()).setView(dialogView).create();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        Button cancel = (Button) dialogView.findViewById(R.id.btnClearAll);
        ImageView crossBtn = (ImageView) dialogView.findViewById(R.id.ivClose);
        LinearLayout actionType = (LinearLayout) dialogView.findViewById(R.id.btnActionType);
        LinearLayout dateRange = (LinearLayout) dialogView.findViewById(R.id.btnDateRange);
        LinearLayout byArea = (LinearLayout) dialogView.findViewById(R.id.btnArea);
        Button done = (Button) dialogView.findViewById(R.id.btnDone);
        actionType.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecentActivityFragment.mainFilterDialog$lambda$12(RecentActivityFragment.this, view);
            }
        });
        dateRange.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecentActivityFragment.mainFilterDialog$lambda$19(RecentActivityFragment.this, view);
            }
        });
        byArea.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecentActivityFragment.mainFilterDialog$lambda$27(RecentActivityFragment.this, view);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        crossBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        done.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecentActivityFragment.mainFilterDialog$lambda$30(RecentActivityFragment.this, dialog, view);
            }
        });
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mainFilterDialog$lambda$12(final RecentActivityFragment this$0, View it) {
        View priorityDialogView = this$0.getLayoutInflater().inflate(R.layout.dialog_action_type_filter, (ViewGroup) null);
        final AlertDialog priorityDialog = new AlertDialog.Builder(this$0.requireContext()).setView(priorityDialogView).create();
        Window window = priorityDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        ImageView priorityCrossBtn = (ImageView) priorityDialogView.findViewById(R.id.priority_cross_iv);
        Button priorityCancel = (Button) priorityDialogView.findViewById(R.id.priority_cancel_btn);
        Button priorityDone = (Button) priorityDialogView.findViewById(R.id.priority_done_btn);
        final TextView all = (TextView) priorityDialogView.findViewById(R.id.all);
        final TextView newCustomer = (TextView) priorityDialogView.findViewById(R.id.newCustomer);
        final TextView visited = (TextView) priorityDialogView.findViewById(R.id.completeVisit);
        final TextView inventory = (TextView) priorityDialogView.findViewById(R.id.inventory);
        if (all != null) {
            all.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda11
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecentActivityFragment.mainFilterDialog$lambda$12$lambda$5(RecentActivityFragment.this, all, newCustomer, visited, inventory, view);
                }
            });
        }
        if (newCustomer != null) {
            newCustomer.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda22
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecentActivityFragment.mainFilterDialog$lambda$12$lambda$6(RecentActivityFragment.this, newCustomer, all, visited, inventory, view);
                }
            });
        }
        if (visited != null) {
            visited.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda26
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecentActivityFragment.mainFilterDialog$lambda$12$lambda$7(RecentActivityFragment.this, visited, newCustomer, all, inventory, view);
                }
            });
        }
        if (inventory != null) {
            inventory.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda27
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecentActivityFragment.mainFilterDialog$lambda$12$lambda$8(RecentActivityFragment.this, inventory, newCustomer, visited, all, view);
                }
            });
        }
        if (priorityCrossBtn != null) {
            priorityCrossBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda28
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    priorityDialog.dismiss();
                }
            });
        }
        if (priorityCancel != null) {
            priorityCancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda29
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    priorityDialog.dismiss();
                }
            });
        }
        if (priorityDone != null) {
            priorityDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda30
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    priorityDialog.dismiss();
                }
            });
        }
        priorityDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mainFilterDialog$lambda$12$lambda$5(RecentActivityFragment this$0, TextView $all, TextView $newCustomer, TextView $visited, TextView $inventory, View it) {
        this$0.storeActionType = "all";
        Intrinsics.checkNotNull($newCustomer);
        Intrinsics.checkNotNull($visited);
        Intrinsics.checkNotNull($inventory);
        this$0.activeBg2($all, $newCustomer, $visited, $inventory);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mainFilterDialog$lambda$12$lambda$6(RecentActivityFragment this$0, TextView $newCustomer, TextView $all, TextView $visited, TextView $inventory, View it) {
        this$0.storeActionType = "New Customer";
        Intrinsics.checkNotNull($all);
        Intrinsics.checkNotNull($visited);
        Intrinsics.checkNotNull($inventory);
        this$0.activeBg2($newCustomer, $all, $visited, $inventory);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mainFilterDialog$lambda$12$lambda$7(RecentActivityFragment this$0, TextView $visited, TextView $newCustomer, TextView $all, TextView $inventory, View it) {
        this$0.storeActionType = "visited";
        Intrinsics.checkNotNull($newCustomer);
        Intrinsics.checkNotNull($all);
        Intrinsics.checkNotNull($inventory);
        this$0.activeBg2($visited, $newCustomer, $all, $inventory);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mainFilterDialog$lambda$12$lambda$8(RecentActivityFragment this$0, TextView $inventory, TextView $newCustomer, TextView $visited, TextView $all, View it) {
        this$0.storeActionType = "inventory";
        Intrinsics.checkNotNull($newCustomer);
        Intrinsics.checkNotNull($visited);
        Intrinsics.checkNotNull($all);
        this$0.activeBg2($inventory, $newCustomer, $visited, $all);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mainFilterDialog$lambda$19(final RecentActivityFragment this$0, View it) {
        View priorityDialogView = this$0.getLayoutInflater().inflate(R.layout.dialog_date_range_filter, (ViewGroup) null);
        final AlertDialog priorityDialog = new AlertDialog.Builder(this$0.requireContext()).setView(priorityDialogView).create();
        Window window = priorityDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        ImageView priorityCrossBtn = (ImageView) priorityDialogView.findViewById(R.id.priority_cross_iv);
        Button priorityCancel = (Button) priorityDialogView.findViewById(R.id.priority_cancel_btn);
        Button priorityDone = (Button) priorityDialogView.findViewById(R.id.priority_done_btn);
        final TextView today = (TextView) priorityDialogView.findViewById(R.id.today);
        final TextView yesterday = (TextView) priorityDialogView.findViewById(R.id.yesterday);
        final TextView last7Day = (TextView) priorityDialogView.findViewById(R.id.last7Days);
        if (today != null) {
            today.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda31
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecentActivityFragment.mainFilterDialog$lambda$19$lambda$13(RecentActivityFragment.this, today, yesterday, last7Day, view);
                }
            });
        }
        if (yesterday != null) {
            yesterday.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda32
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecentActivityFragment.mainFilterDialog$lambda$19$lambda$14(RecentActivityFragment.this, yesterday, today, last7Day, view);
                }
            });
        }
        if (last7Day != null) {
            last7Day.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecentActivityFragment.mainFilterDialog$lambda$19$lambda$15(RecentActivityFragment.this, last7Day, yesterday, today, view);
                }
            });
        }
        if (priorityCrossBtn != null) {
            priorityCrossBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    priorityDialog.dismiss();
                }
            });
        }
        if (priorityCancel != null) {
            priorityCancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    priorityDialog.dismiss();
                }
            });
        }
        if (priorityDone != null) {
            priorityDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    priorityDialog.dismiss();
                }
            });
        }
        priorityDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mainFilterDialog$lambda$19$lambda$13(RecentActivityFragment this$0, TextView $today, TextView $yesterday, TextView $last7Day, View it) {
        this$0.storeDateRange = "today";
        Intrinsics.checkNotNull($yesterday);
        Intrinsics.checkNotNull($last7Day);
        this$0.activeBg($today, $yesterday, $last7Day);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mainFilterDialog$lambda$19$lambda$14(RecentActivityFragment this$0, TextView $yesterday, TextView $today, TextView $last7Day, View it) {
        this$0.storeDateRange = "yesterday";
        Intrinsics.checkNotNull($today);
        Intrinsics.checkNotNull($last7Day);
        this$0.activeBg($yesterday, $today, $last7Day);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mainFilterDialog$lambda$19$lambda$15(RecentActivityFragment this$0, TextView $last7Day, TextView $yesterday, TextView $today, View it) {
        this$0.storeDateRange = "last7Days";
        Intrinsics.checkNotNull($yesterday);
        Intrinsics.checkNotNull($today);
        this$0.activeBg($last7Day, $yesterday, $today);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mainFilterDialog$lambda$27(final RecentActivityFragment this$0, View it) {
        View priorityDialogView = this$0.getLayoutInflater().inflate(R.layout.dialog_by_area_filter, (ViewGroup) null);
        final AlertDialog priorityDialog = new AlertDialog.Builder(this$0.requireContext()).setView(priorityDialogView).create();
        Window window = priorityDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        ImageView priorityCrossBtn = (ImageView) priorityDialogView.findViewById(R.id.priority_cross_iv);
        Button priorityCancel = (Button) priorityDialogView.findViewById(R.id.priority_cancel_btn);
        Button priorityDone = (Button) priorityDialogView.findViewById(R.id.priority_done_btn);
        final TextView all = (TextView) priorityDialogView.findViewById(R.id.all);
        final TextView gulshan = (TextView) priorityDialogView.findViewById(R.id.gulshan);
        final TextView karimabad = (TextView) priorityDialogView.findViewById(R.id.karimabad);
        final TextView dha = (TextView) priorityDialogView.findViewById(R.id.dha);
        if (all != null) {
            all.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda18
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecentActivityFragment.mainFilterDialog$lambda$27$lambda$20(RecentActivityFragment.this, all, gulshan, karimabad, dha, view);
                }
            });
        }
        if (gulshan != null) {
            gulshan.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda19
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecentActivityFragment.mainFilterDialog$lambda$27$lambda$21(RecentActivityFragment.this, gulshan, all, karimabad, dha, view);
                }
            });
        }
        if (karimabad != null) {
            karimabad.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda20
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecentActivityFragment.mainFilterDialog$lambda$27$lambda$22(RecentActivityFragment.this, karimabad, all, gulshan, dha, view);
                }
            });
        }
        if (dha != null) {
            dha.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda21
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecentActivityFragment.mainFilterDialog$lambda$27$lambda$23(RecentActivityFragment.this, dha, all, gulshan, karimabad, view);
                }
            });
        }
        if (priorityCrossBtn != null) {
            priorityCrossBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda23
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    priorityDialog.dismiss();
                }
            });
        }
        if (priorityCancel != null) {
            priorityCancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda24
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    priorityDialog.dismiss();
                }
            });
        }
        if (priorityDone != null) {
            priorityDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda25
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    priorityDialog.dismiss();
                }
            });
        }
        priorityDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mainFilterDialog$lambda$27$lambda$20(RecentActivityFragment this$0, TextView $all, TextView $gulshan, TextView $karimabad, TextView $dha, View it) {
        this$0.storeByArea = "all";
        Intrinsics.checkNotNull($gulshan);
        Intrinsics.checkNotNull($karimabad);
        Intrinsics.checkNotNull($dha);
        this$0.activeBg2($all, $gulshan, $karimabad, $dha);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mainFilterDialog$lambda$27$lambda$21(RecentActivityFragment this$0, TextView $gulshan, TextView $all, TextView $karimabad, TextView $dha, View it) {
        this$0.storeByArea = "gulshan";
        Intrinsics.checkNotNull($all);
        Intrinsics.checkNotNull($karimabad);
        Intrinsics.checkNotNull($dha);
        this$0.activeBg2($gulshan, $all, $karimabad, $dha);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mainFilterDialog$lambda$27$lambda$22(RecentActivityFragment this$0, TextView $karimabad, TextView $all, TextView $gulshan, TextView $dha, View it) {
        this$0.storeByArea = "karimabad";
        Intrinsics.checkNotNull($all);
        Intrinsics.checkNotNull($gulshan);
        Intrinsics.checkNotNull($dha);
        this$0.activeBg2($karimabad, $all, $gulshan, $dha);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mainFilterDialog$lambda$27$lambda$23(RecentActivityFragment this$0, TextView $dha, TextView $all, TextView $gulshan, TextView $karimabad, View it) {
        this$0.storeByArea = "dha";
        Intrinsics.checkNotNull($all);
        Intrinsics.checkNotNull($gulshan);
        Intrinsics.checkNotNull($karimabad);
        this$0.activeBg2($dha, $all, $gulshan, $karimabad);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mainFilterDialog$lambda$30(RecentActivityFragment this$0, AlertDialog $dialog, View it) {
        String token = this$0.getToken();
        if (token != null) {
            fetchActivityLog$default(this$0, token, this$0.storeDateRange, this$0.storeActionType, null, 8, null);
        } else {
            this$0.showEmptyState("Session expired. Please log in again.");
        }
        $dialog.dismiss();
    }

    static /* synthetic */ void fetchActivityLog$default(RecentActivityFragment recentActivityFragment, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        if ((i & 4) != 0) {
            str3 = "";
        }
        if ((i & 8) != 0) {
            str4 = "";
        }
        recentActivityFragment.fetchActivityLog(str, str2, str3, str4);
    }

    private final void fetchActivityLog(String token, String date, String action, String subject) {
        Job launch$default;
        showLoading(true);
        launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new RecentActivityFragment$fetchActivityLog$1(date, action, subject, token, this, null), 3, null);
        this.job = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0164 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00b5 -> B:13:0x00dc). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00d4 -> B:12:0x00d8). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object parseActivityList(JSONArray data, Continuation<? super List<ActivityLog>> continuation) {
        RecentActivityFragment$parseActivityList$1 recentActivityFragment$parseActivityList$1;
        RecentActivityFragment recentActivityFragment;
        JSONArray data2;
        int i;
        int length;
        List list;
        JSONObject obj;
        JSONObject detailsJson;
        DeliveredBooks products;
        Object $result;
        Continuation $completion = continuation;
        if ($completion instanceof RecentActivityFragment$parseActivityList$1) {
            recentActivityFragment$parseActivityList$1 = (RecentActivityFragment$parseActivityList$1) $completion;
            if ((recentActivityFragment$parseActivityList$1.label & Integer.MIN_VALUE) != 0) {
                recentActivityFragment$parseActivityList$1.label -= Integer.MIN_VALUE;
                Object $result2 = recentActivityFragment$parseActivityList$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (recentActivityFragment$parseActivityList$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result2);
                        List list2 = new ArrayList();
                        recentActivityFragment = this;
                        data2 = data;
                        i = 0;
                        length = data.length();
                        list = list2;
                        if (i >= length) {
                            obj = data2.getJSONObject(i);
                            String detailsString = obj.optString("details", "{}");
                            detailsJson = new JSONObject(detailsString);
                            Log.i("TAG", "parseActivityList: " + detailsJson);
                            products = null;
                            Continuation $completion2 = $completion;
                            if (StringsKt.equals(obj.optString("action"), "visited", true) || StringsKt.equals(obj.optString("action"), "new visit", true)) {
                                int optInt = obj.optInt("subject_id");
                                recentActivityFragment$parseActivityList$1.L$0 = recentActivityFragment;
                                recentActivityFragment$parseActivityList$1.L$1 = data2;
                                recentActivityFragment$parseActivityList$1.L$2 = list;
                                recentActivityFragment$parseActivityList$1.L$3 = obj;
                                recentActivityFragment$parseActivityList$1.L$4 = detailsJson;
                                recentActivityFragment$parseActivityList$1.I$0 = i;
                                recentActivityFragment$parseActivityList$1.I$1 = length;
                                recentActivityFragment$parseActivityList$1.label = 1;
                                Object fetchVisitProducts = recentActivityFragment.fetchVisitProducts(optInt, recentActivityFragment$parseActivityList$1);
                                if (fetchVisitProducts == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $result = $result2;
                                $result2 = fetchVisitProducts;
                                $completion = $completion2;
                                products = (DeliveredBooks) $result2;
                                $result2 = $result;
                                list.add(new ActivityLog(obj.optInt(Constant.VISIT_ID), Boxing.boxInt(obj.optInt("booker_id")), obj.optString("action"), obj.optString("subject"), obj.optString("Customername"), detailsJson.optString("principalName"), detailsJson.optString("phone"), detailsJson.optString(FirebaseAnalytics.Param.LOCATION), detailsJson.optString("customerType"), obj.optString("created_at"), detailsJson.optString("visitDate"), detailsJson.optString("visitDuration"), detailsJson.optString("booksDelivered"), detailsJson.optString("orderValue"), detailsJson.optString("notes"), detailsJson.optString("bookImageUrl"), Boxing.boxInt(detailsJson.optInt("subject_id")), detailsJson.toString(), obj.optString("updated_at"), products));
                                i++;
                                if (i >= length) {
                                    return list;
                                }
                            } else {
                                $completion = $completion2;
                                list.add(new ActivityLog(obj.optInt(Constant.VISIT_ID), Boxing.boxInt(obj.optInt("booker_id")), obj.optString("action"), obj.optString("subject"), obj.optString("Customername"), detailsJson.optString("principalName"), detailsJson.optString("phone"), detailsJson.optString(FirebaseAnalytics.Param.LOCATION), detailsJson.optString("customerType"), obj.optString("created_at"), detailsJson.optString("visitDate"), detailsJson.optString("visitDuration"), detailsJson.optString("booksDelivered"), detailsJson.optString("orderValue"), detailsJson.optString("notes"), detailsJson.optString("bookImageUrl"), Boxing.boxInt(detailsJson.optInt("subject_id")), detailsJson.toString(), obj.optString("updated_at"), products));
                                i++;
                                if (i >= length) {
                                }
                            }
                        }
                        break;
                    case 1:
                        length = recentActivityFragment$parseActivityList$1.I$1;
                        i = recentActivityFragment$parseActivityList$1.I$0;
                        detailsJson = (JSONObject) recentActivityFragment$parseActivityList$1.L$4;
                        obj = (JSONObject) recentActivityFragment$parseActivityList$1.L$3;
                        list = (List) recentActivityFragment$parseActivityList$1.L$2;
                        data2 = (JSONArray) recentActivityFragment$parseActivityList$1.L$1;
                        recentActivityFragment = (RecentActivityFragment) recentActivityFragment$parseActivityList$1.L$0;
                        ResultKt.throwOnFailure($result2);
                        $result = $result2;
                        products = (DeliveredBooks) $result2;
                        $result2 = $result;
                        list.add(new ActivityLog(obj.optInt(Constant.VISIT_ID), Boxing.boxInt(obj.optInt("booker_id")), obj.optString("action"), obj.optString("subject"), obj.optString("Customername"), detailsJson.optString("principalName"), detailsJson.optString("phone"), detailsJson.optString(FirebaseAnalytics.Param.LOCATION), detailsJson.optString("customerType"), obj.optString("created_at"), detailsJson.optString("visitDate"), detailsJson.optString("visitDuration"), detailsJson.optString("booksDelivered"), detailsJson.optString("orderValue"), detailsJson.optString("notes"), detailsJson.optString("bookImageUrl"), Boxing.boxInt(detailsJson.optInt("subject_id")), detailsJson.toString(), obj.optString("updated_at"), products));
                        i++;
                        if (i >= length) {
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        recentActivityFragment$parseActivityList$1 = new RecentActivityFragment$parseActivityList$1(this, $completion);
        Object $result22 = recentActivityFragment$parseActivityList$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (recentActivityFragment$parseActivityList$1.label) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getToken() {
        SharedPreferences sharedPref = requireContext().getSharedPreferences("BookmarkApp", 0);
        Log.i("TAG", "getToken: " + sharedPref.getString("AUTH_TOKEN", null));
        return sharedPref.getString("AUTH_TOKEN", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showEmptyState(String message) {
        FragmentRecentActivityBinding fragmentRecentActivityBinding = this.binding;
        FragmentRecentActivityBinding fragmentRecentActivityBinding2 = null;
        if (fragmentRecentActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentRecentActivityBinding = null;
        }
        fragmentRecentActivityBinding.activityResultRv.setVisibility(8);
        FragmentRecentActivityBinding fragmentRecentActivityBinding3 = this.binding;
        if (fragmentRecentActivityBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentRecentActivityBinding3 = null;
        }
        fragmentRecentActivityBinding3.tvNoData.setText(message);
        FragmentRecentActivityBinding fragmentRecentActivityBinding4 = this.binding;
        if (fragmentRecentActivityBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentRecentActivityBinding2 = fragmentRecentActivityBinding4;
        }
        fragmentRecentActivityBinding2.tvNoData.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoading(boolean isLoading) {
        FragmentRecentActivityBinding fragmentRecentActivityBinding = this.binding;
        if (fragmentRecentActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentRecentActivityBinding = null;
        }
        fragmentRecentActivityBinding.progressBar.setVisibility(isLoading ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showActivityLog(List<ActivityLog> list) {
        FragmentRecentActivityBinding fragmentRecentActivityBinding = this.binding;
        ActivityLogAdapter activityLogAdapter = null;
        if (fragmentRecentActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentRecentActivityBinding = null;
        }
        fragmentRecentActivityBinding.tvNoData.setVisibility(8);
        FragmentRecentActivityBinding fragmentRecentActivityBinding2 = this.binding;
        if (fragmentRecentActivityBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentRecentActivityBinding2 = null;
        }
        fragmentRecentActivityBinding2.activityResultRv.setVisibility(0);
        FragmentRecentActivityBinding fragmentRecentActivityBinding3 = this.binding;
        if (fragmentRecentActivityBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentRecentActivityBinding3 = null;
        }
        fragmentRecentActivityBinding3.activityListRv.setVisibility(0);
        ActivityLogAdapter activityLogAdapter2 = this.activityAdapter;
        if (activityLogAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityAdapter");
        } else {
            activityLogAdapter = activityLogAdapter2;
        }
        activityLogAdapter.updateList(list);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    private final void activeBg(TextView active, TextView deActive, TextView deActive2) {
        active.setBackgroundResource(R.drawable.selected_bg);
        active.setTextColor(requireActivity().getColor(R.color.app_color));
        deActive.setBackgroundResource(R.drawable.priority_selector);
        deActive2.setBackgroundResource(R.drawable.priority_selector);
        deActive.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        deActive2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
    }

    private final void activeBg2(TextView active, TextView deActive, TextView deActive2, TextView deActive3) {
        active.setBackgroundResource(R.drawable.selected_bg);
        active.setTextColor(requireActivity().getColor(R.color.app_color));
        deActive.setBackgroundResource(R.drawable.priority_selector);
        deActive2.setBackgroundResource(R.drawable.priority_selector);
        deActive3.setBackgroundResource(R.drawable.priority_selector);
        deActive3.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        deActive.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        deActive2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void searchActivityLog(String token, String query) {
        Job launch$default;
        showLoading(true);
        launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new RecentActivityFragment$searchActivityLog$1(query, token, this, null), 3, null);
        this.job = launch$default;
    }

    private final void captureAndSaveScreenshot(View view) {
        Bitmap bitmap;
        Canvas canvas;
        try {
            bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(bitmap, "createBitmap(...)");
            canvas = new Canvas(bitmap);
        } catch (Exception e) {
            e = e;
        }
        try {
            view.draw(canvas);
            String filename = "performance_dashboard_" + System.currentTimeMillis() + ".png";
            ContentResolver resolver = requireContext().getContentResolver();
            ContentValues $this$captureAndSaveScreenshot_u24lambda_u2431 = new ContentValues();
            $this$captureAndSaveScreenshot_u24lambda_u2431.put("_display_name", filename);
            $this$captureAndSaveScreenshot_u24lambda_u2431.put("mime_type", "image/png");
            if (Build.VERSION.SDK_INT >= 29) {
                $this$captureAndSaveScreenshot_u24lambda_u2431.put("relative_path", Environment.DIRECTORY_PICTURES);
                $this$captureAndSaveScreenshot_u24lambda_u2431.put("is_pending", (Integer) 1);
            }
            Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, $this$captureAndSaveScreenshot_u24lambda_u2431);
            if (imageUri != null) {
                OutputStream outputStream = resolver.openOutputStream(imageUri);
                if (outputStream != null) {
                    OutputStream outputStream2 = outputStream;
                    try {
                        OutputStream it = outputStream2;
                        Boolean.valueOf(bitmap.compress(Bitmap.CompressFormat.PNG, 100, it));
                        CloseableKt.closeFinally(outputStream2, null);
                    } finally {
                    }
                }
                if (Build.VERSION.SDK_INT >= 29) {
                    $this$captureAndSaveScreenshot_u24lambda_u2431.clear();
                    $this$captureAndSaveScreenshot_u24lambda_u2431.put("is_pending", (Integer) 0);
                    resolver.update(imageUri, $this$captureAndSaveScreenshot_u24lambda_u2431, null, null);
                }
                Toast.makeText(requireContext(), "Screenshot saved to gallery", 0).show();
                return;
            }
            Toast.makeText(requireContext(), "Failed to save screenshot", 0).show();
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            Toast.makeText(requireContext(), "Error: " + e.getMessage(), 0).show();
        }
    }

    private final boolean checkAndRequestPermission() {
        if (Build.VERSION.SDK_INT >= 29) {
            return true;
        }
        int granted = ContextCompat.checkSelfPermission(requireContext(), "android.permission.WRITE_EXTERNAL_STORAGE");
        if (granted == 0) {
            return true;
        }
        ActivityCompat.requestPermissions(requireActivity(), new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, this.STORAGE_PERMISSION_CODE);
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == this.STORAGE_PERMISSION_CODE) {
            if (!(grantResults.length == 0) && grantResults[0] == 0) {
                Toast.makeText(requireContext(), "Permission granted", 0).show();
            } else {
                Toast.makeText(requireContext(), "Storage permission denied", 0).show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void exportDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_performance_filter_main, (ViewGroup) null);
        final AlertDialog dialog = new AlertDialog.Builder(requireContext()).setView(dialogView).create();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        ImageView crossBtn = (ImageView) dialogView.findViewById(R.id.ivClose);
        LinearLayout actionType = (LinearLayout) dialogView.findViewById(R.id.btnActionType);
        LinearLayout dateRange = (LinearLayout) dialogView.findViewById(R.id.btnDateRange);
        actionType.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecentActivityFragment.exportDialog$lambda$33(RecentActivityFragment.this, view);
            }
        });
        dateRange.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecentActivityFragment.exportDialog$lambda$34(RecentActivityFragment.this, view);
            }
        });
        crossBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void exportDialog$lambda$33(RecentActivityFragment this$0, View it) {
        if (this$0.checkAndRequestPermission()) {
            FragmentRecentActivityBinding fragmentRecentActivityBinding = this$0.binding;
            if (fragmentRecentActivityBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentRecentActivityBinding = null;
            }
            ConstraintLayout root = fragmentRecentActivityBinding.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
            this$0.captureAndSaveScreenshot(root);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void exportDialog$lambda$34(RecentActivityFragment this$0, View it) {
        Intent intent = new Intent(this$0.requireContext(), (Class<?>) HelpScreenAct.class);
        this$0.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object fetchVisitProducts(int visitId, Continuation<? super DeliveredBooks> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new RecentActivityFragment$fetchVisitProducts$2(visitId, this, null), continuation);
    }
}
