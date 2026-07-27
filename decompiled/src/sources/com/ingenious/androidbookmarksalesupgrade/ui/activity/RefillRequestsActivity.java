package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ingenious.androidbookmarksalesupgrade.adapter.RefillRequestsViewPagerAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityRefillRequestsBinding;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RefillRequestsActivity.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\b\u0010\u000b\u001a\u00020\bH\u0002J\u0012\u0010\f\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u0010\u0010\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/RefillRequestsActivity;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/BaseActivity;", "Lcom/google/android/material/tabs/TabLayout$OnTabSelectedListener;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityRefillRequestsBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViewPagerAdapter", "onTabSelected", "tab", "Lcom/google/android/material/tabs/TabLayout$Tab;", "onTabUnselected", "onTabReselected", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class RefillRequestsActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    private ActivityRefillRequestsBinding binding;

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityRefillRequestsBinding.inflate(getLayoutInflater());
        ActivityRefillRequestsBinding activityRefillRequestsBinding = this.binding;
        ActivityRefillRequestsBinding activityRefillRequestsBinding2 = null;
        if (activityRefillRequestsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRefillRequestsBinding = null;
        }
        setContentView(activityRefillRequestsBinding.getRoot());
        ExtensionKt.belowStatusBarText(this);
        setupViewPagerAdapter();
        ActivityRefillRequestsBinding activityRefillRequestsBinding3 = this.binding;
        if (activityRefillRequestsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityRefillRequestsBinding2 = activityRefillRequestsBinding3;
        }
        activityRefillRequestsBinding2.back.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.RefillRequestsActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RefillRequestsActivity.onCreate$lambda$0(RefillRequestsActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(RefillRequestsActivity this$0, View it) {
        this$0.finish();
        Log.i("TAG", "onTapBack: ");
    }

    private final void setupViewPagerAdapter() {
        RefillRequestsViewPagerAdapter viewPagerAdapter = new RefillRequestsViewPagerAdapter(this, 3);
        ActivityRefillRequestsBinding activityRefillRequestsBinding = this.binding;
        ActivityRefillRequestsBinding activityRefillRequestsBinding2 = null;
        if (activityRefillRequestsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRefillRequestsBinding = null;
        }
        activityRefillRequestsBinding.viewPager.setAdapter(viewPagerAdapter);
        ActivityRefillRequestsBinding activityRefillRequestsBinding3 = this.binding;
        if (activityRefillRequestsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRefillRequestsBinding3 = null;
        }
        TabLayout tabLayout = activityRefillRequestsBinding3.tabLayout;
        ActivityRefillRequestsBinding activityRefillRequestsBinding4 = this.binding;
        if (activityRefillRequestsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityRefillRequestsBinding2 = activityRefillRequestsBinding4;
        }
        new TabLayoutMediator(tabLayout, activityRefillRequestsBinding2.viewPager, new TabLayoutMediator.TabConfigurationStrategy() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.RefillRequestsActivity$$ExternalSyntheticLambda1
            @Override // com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
            public final void onConfigureTab(TabLayout.Tab tab, int i) {
                RefillRequestsActivity.setupViewPagerAdapter$lambda$1(tab, i);
            }
        }).attach();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupViewPagerAdapter$lambda$1(TabLayout.Tab tab, int position) {
        String str;
        Intrinsics.checkNotNullParameter(tab, "tab");
        switch (position) {
            case 0:
                str = "Pending";
                break;
            case 1:
                str = "Received";
                break;
            default:
                str = "Approved";
                break;
        }
        tab.setText(str);
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabSelected(TabLayout.Tab tab) {
        ActivityRefillRequestsBinding activityRefillRequestsBinding = this.binding;
        if (activityRefillRequestsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRefillRequestsBinding = null;
        }
        ViewPager2 viewPager2 = activityRefillRequestsBinding.viewPager;
        Integer valueOf = tab != null ? Integer.valueOf(tab.getPosition()) : null;
        Intrinsics.checkNotNull(valueOf);
        viewPager2.setCurrentItem(valueOf.intValue());
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabReselected(TabLayout.Tab tab) {
        ActivityRefillRequestsBinding activityRefillRequestsBinding = this.binding;
        if (activityRefillRequestsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRefillRequestsBinding = null;
        }
        activityRefillRequestsBinding.viewPager.offsetLeftAndRight(1);
    }
}
