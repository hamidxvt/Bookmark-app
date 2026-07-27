package com.ingenious.androidbookmarksalesupgrade.adapter;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.ingenious.androidbookmarksalesupgrade.ui.fragment.ApprovedRefillRequestsFragment;
import com.ingenious.androidbookmarksalesupgrade.ui.fragment.PendingRefillRequestsFragment;
import com.ingenious.androidbookmarksalesupgrade.ui.fragment.ReceivedRefillRequestsFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RefillRequestsViewPagerAdapter.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\f\u001a\u00020\u0005H\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/RefillRequestsViewPagerAdapter;", "Landroidx/viewpager2/adapter/FragmentStateAdapter;", "fm", "Landroidx/fragment/app/FragmentActivity;", "tabCount", "", "<init>", "(Landroidx/fragment/app/FragmentActivity;I)V", "getTabCount", "()I", "setTabCount", "(I)V", "getItemCount", "createFragment", "Landroidx/fragment/app/Fragment;", "position", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class RefillRequestsViewPagerAdapter extends FragmentStateAdapter {
    private int tabCount;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RefillRequestsViewPagerAdapter(FragmentActivity fm, int tabCount) {
        super(fm);
        Intrinsics.checkNotNullParameter(fm, "fm");
        this.tabCount = tabCount;
    }

    public final int getTabCount() {
        return this.tabCount;
    }

    public final void setTabCount(int i) {
        this.tabCount = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: getItemCount, reason: from getter */
    public int getTabCount() {
        return this.tabCount;
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PendingRefillRequestsFragment();
            case 1:
                return new ReceivedRefillRequestsFragment();
            default:
                return new ApprovedRefillRequestsFragment();
        }
    }
}
