package com.ingenious.androidbookmarksalesupgrade.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.ActivityFilterAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemActivityListFilterBinding;
import com.ingenious.androidbookmarksalesupgrade.listener.OnFilterItemClickListener;
import com.ingenious.androidbookmarksalesupgrade.model.ActivityFilerTxt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ActivityFilterAdapter.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0018B\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u001c\u0010\f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u001c\u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u000bH\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/ActivityFilterAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/ActivityFilterAdapter$ActivityFilterHolder;", "list", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/ActivityFilerTxt;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/ingenious/androidbookmarksalesupgrade/listener/OnFilterItemClickListener;", "<init>", "(Ljava/util/List;Lcom/ingenious/androidbookmarksalesupgrade/listener/OnFilterItemClickListener;)V", "selectedPosition", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onBindViewHolder", "", "holder", "position", "getItemCount", "onAttachedToRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "ActivityFilterHolder", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class ActivityFilterAdapter extends RecyclerView.Adapter<ActivityFilterHolder> {
    private List<ActivityFilerTxt> list;
    private final OnFilterItemClickListener listener;
    private int selectedPosition;

    public ActivityFilterAdapter(List<ActivityFilerTxt> list, OnFilterItemClickListener listener) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.list = list;
        this.listener = listener;
    }

    /* compiled from: ActivityFilterAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/ActivityFilterAdapter$ActivityFilterHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemActivityListFilterBinding;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/ActivityFilterAdapter;Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemActivityListFilterBinding;)V", "getBinding", "()Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemActivityListFilterBinding;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class ActivityFilterHolder extends RecyclerView.ViewHolder {
        private final ItemActivityListFilterBinding binding;
        final /* synthetic */ ActivityFilterAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActivityFilterHolder(ActivityFilterAdapter this$0, ItemActivityListFilterBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = this$0;
            this.binding = binding;
            TextView textView = this.binding.name;
            final ActivityFilterAdapter activityFilterAdapter = this.this$0;
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityFilterAdapter$ActivityFilterHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityFilterAdapter.ActivityFilterHolder._init_$lambda$0(ActivityFilterAdapter.this, this, view);
                }
            });
        }

        public final ItemActivityListFilterBinding getBinding() {
            return this.binding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void _init_$lambda$0(ActivityFilterAdapter this$0, ActivityFilterHolder this$1, View it) {
            int oldPosition = this$0.selectedPosition;
            this$0.selectedPosition = this$1.getAdapterPosition();
            this$0.notifyItemChanged(oldPosition);
            this$0.notifyItemChanged(this$0.selectedPosition);
            this$0.listener.onFilterItemClick(this$0.selectedPosition, (ActivityFilerTxt) this$0.list.get(this$0.selectedPosition));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ActivityFilterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemActivityListFilterBinding binding = ItemActivityListFilterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "inflate(...)");
        return new ActivityFilterHolder(this, binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ActivityFilterHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ActivityFilerTxt item = this.list.get(position);
        holder.getBinding().name.setText(item.getTitle());
        if (position == this.selectedPosition) {
            holder.getBinding().name.setBackgroundResource(R.drawable.selected_bg);
            holder.getBinding().name.setTextColor(SupportMenu.CATEGORY_MASK);
        } else {
            holder.getBinding().name.setBackgroundResource(R.drawable.edittext_background);
            holder.getBinding().name.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        if (!this.list.isEmpty() && this.selectedPosition != -1) {
            this.listener.onFilterItemClick(this.selectedPosition, this.list.get(this.selectedPosition));
        }
    }
}
