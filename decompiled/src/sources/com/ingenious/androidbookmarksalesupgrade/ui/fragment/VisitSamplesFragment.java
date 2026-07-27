package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.common.net.HttpHeaders;
import com.ingenious.androidbookmarksalesupgrade.adapter.SampleBookAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitSamplesBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* compiled from: VisitSamplesFragment.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0011H\u0002J\b\u0010\u0017\u001a\u00020\u0011H\u0002J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R.\u0010\t\u001a\u001c\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/VisitSamplesFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/BaseFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentVisitSamplesBinding;", "<init>", "()V", "customerId", "", "bookAdapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/SampleBookAdapter;", "bindingInflater", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "", "getBindingInflater", "()Lkotlin/jvm/functions/Function3;", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setupAdapter", "fetchSampleBooks", "getToken", "Companion", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class VisitSamplesFragment extends BaseFragment<FragmentVisitSamplesBinding> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private SampleBookAdapter bookAdapter;
    private String customerId;

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment
    public Function3<LayoutInflater, ViewGroup, Boolean, FragmentVisitSamplesBinding> getBindingInflater() {
        return VisitSamplesFragment$bindingInflater$1.INSTANCE;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        Bundle arguments = getArguments();
        this.customerId = arguments != null ? arguments.getString("customerId") : null;
        setupAdapter();
    }

    private final void setupAdapter() {
        this.bookAdapter = new SampleBookAdapter();
        getBinding().sampleListRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        RecyclerView recyclerView = getBinding().sampleListRv;
        SampleBookAdapter sampleBookAdapter = this.bookAdapter;
        if (sampleBookAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bookAdapter");
            sampleBookAdapter = null;
        }
        recyclerView.setAdapter(sampleBookAdapter);
        fetchSampleBooks();
    }

    /* compiled from: VisitSamplesFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/VisitSamplesFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/VisitSamplesFragment;", "customerId", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VisitSamplesFragment newInstance(String customerId) {
            Intrinsics.checkNotNullParameter(customerId, "customerId");
            VisitSamplesFragment fragment = new VisitSamplesFragment();
            Bundle args = new Bundle();
            args.putString("customerId", customerId);
            fragment.setArguments(args);
            return fragment;
        }
    }

    private final void fetchSampleBooks() {
        OkHttpClient client = new OkHttpClient();
        String url = "https://staging.bookmark.services/api/customer/sample?customer_id=" + this.customerId;
        Request.Builder addHeader = new Request.Builder().url(url).addHeader(HttpHeaders.ACCEPT, "application/json");
        String token = getToken();
        if (token == null) {
            token = "";
        }
        Request request = addHeader.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
        client.newCall(request).enqueue(new VisitSamplesFragment$fetchSampleBooks$1(this));
    }

    private final String getToken() {
        SharedPreferences sharedPref = requireActivity().getSharedPreferences("BookmarkApp", 0);
        return sharedPref.getString("AUTH_TOKEN", null);
    }
}
