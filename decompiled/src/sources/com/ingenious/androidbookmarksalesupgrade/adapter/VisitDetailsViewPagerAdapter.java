package com.ingenious.androidbookmarksalesupgrade.adapter;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionFragment;
import com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitDetailsFragment;
import com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitHistoryFragment;
import com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitSamplesFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VisitDetailsViewPagerAdapter.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0005H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/VisitDetailsViewPagerAdapter;", "Landroidx/viewpager2/adapter/FragmentStateAdapter;", "fm", "Landroidx/fragment/app/FragmentActivity;", "tabCount", "", "visitId", "", "customerId", "<init>", "(Landroidx/fragment/app/FragmentActivity;ILjava/lang/String;Ljava/lang/String;)V", "getTabCount", "()I", "setTabCount", "(I)V", "getItemCount", "createFragment", "Landroidx/fragment/app/Fragment;", "position", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class VisitDetailsViewPagerAdapter extends FragmentStateAdapter {
    private final String customerId;
    private int tabCount;
    private final String visitId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VisitDetailsViewPagerAdapter(FragmentActivity fm, int tabCount, String visitId, String customerId) {
        super(fm);
        Intrinsics.checkNotNullParameter(fm, "fm");
        Intrinsics.checkNotNullParameter(visitId, "visitId");
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        this.tabCount = tabCount;
        this.visitId = visitId;
        this.customerId = customerId;
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
                return VisitDetailsFragment.INSTANCE.newInstance(this.visitId);
            case 1:
                return VisitSamplesFragment.INSTANCE.newInstance(this.customerId);
            case 2:
                return VisitHistoryFragment.INSTANCE.newInstance(this.customerId);
            default:
                return VisitAdoptionFragment.INSTANCE.newInstance(this.customerId);
        }
    }
}
