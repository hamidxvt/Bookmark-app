package com.ingenious.androidbookmarksalesupgrade.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.AdoptionBooksListAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemAdoptionBooksBinding;
import com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooksData;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdoptionBooksListAdapter.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002\u001e\u001fB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001c\u0010\u0014\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u001c\u0010\u0019\u001a\u00020\u001a2\n\u0010\u001b\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u0018H\u0016J\b\u0010\u001d\u001a\u00020\u0018H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0082\u0004¢\u0006\u0002\n\u0000RD\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010RD\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006 "}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/AdoptionBooksListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/AdoptionBooksListAdapter$MyViewHolder;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/ingenious/androidbookmarksalesupgrade/listener/RecyclerViewListener;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/listener/RecyclerViewListener;)V", "selectedProductList", "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionBooksData;", "Lkotlin/collections/ArrayList;", "value", FirebaseAnalytics.Param.ITEMS, "getItems", "()Ljava/util/ArrayList;", "setItems", "(Ljava/util/ArrayList;)V", "itemsFiltered", "getItemsFiltered", "setItemsFiltered", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "onBindViewHolder", "", "holder", "position", "getItemCount", "MyViewHolder", "LastDiffUtilCallback", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class AdoptionBooksListAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private ArrayList<AdoptionBooksData> items;
    private ArrayList<AdoptionBooksData> itemsFiltered;
    private final RecyclerViewListener listener;
    private final ArrayList<AdoptionBooksData> selectedProductList;

    public AdoptionBooksListAdapter(RecyclerViewListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
        this.selectedProductList = new ArrayList<>();
        this.items = new ArrayList<>();
        this.itemsFiltered = new ArrayList<>();
    }

    public final ArrayList<AdoptionBooksData> getItems() {
        return this.items;
    }

    public final void setItems(ArrayList<AdoptionBooksData> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.items = value;
        LastDiffUtilCallback diffUtil = new LastDiffUtilCallback(this, this.items, this.itemsFiltered);
        DiffUtil.DiffResult diffUtilResult = DiffUtil.calculateDiff(diffUtil);
        Intrinsics.checkNotNullExpressionValue(diffUtilResult, "calculateDiff(...)");
        this.itemsFiltered.clear();
        this.itemsFiltered.addAll(value);
        diffUtilResult.dispatchUpdatesTo(this);
    }

    public final ArrayList<AdoptionBooksData> getItemsFiltered() {
        return this.itemsFiltered;
    }

    public final void setItemsFiltered(ArrayList<AdoptionBooksData> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.itemsFiltered = value;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemAdoptionBooksBinding binding = ItemAdoptionBooksBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "inflate(...)");
        return new MyViewHolder(this, binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        AdoptionBooksData adoptionBooksData = this.itemsFiltered.get(position);
        Intrinsics.checkNotNullExpressionValue(adoptionBooksData, "get(...)");
        AdoptionBooksData product = adoptionBooksData;
        holder.bind(product);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemsFiltered.size();
    }

    /* compiled from: AdoptionBooksListAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/AdoptionBooksListAdapter$MyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemAdoptionBooksBinding;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/AdoptionBooksListAdapter;Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemAdoptionBooksBinding;)V", "bind", "", "item", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionBooksData;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemAdoptionBooksBinding binding;
        final /* synthetic */ AdoptionBooksListAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(AdoptionBooksListAdapter this$0, ItemAdoptionBooksBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = this$0;
            this.binding = binding;
        }

        public final void bind(final AdoptionBooksData item) {
            Intrinsics.checkNotNullParameter(item, "item");
            this.binding.setItem(item);
            this.binding.executePendingBindings();
            ImageView ivSelection = this.binding.ivSelection;
            Intrinsics.checkNotNullExpressionValue(ivSelection, "ivSelection");
            ivSelection.setVisibility(0);
            Boolean isSelected = item.isSelected();
            Intrinsics.checkNotNull(isSelected);
            if (isSelected.booleanValue()) {
                ivSelection.setImageResource(R.drawable.ic_selection_tick);
                this.binding.linearSegments.setBackgroundResource(R.drawable.bg_image_rounded_red_stroke);
            } else {
                ivSelection.setImageResource(R.drawable.ic_unselected_item);
                this.binding.linearSegments.setBackgroundResource(R.drawable.bg_image_rounded_fill);
            }
            LinearLayout linearLayout = this.binding.linearSegments;
            final AdoptionBooksListAdapter adoptionBooksListAdapter = this.this$0;
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.AdoptionBooksListAdapter$MyViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AdoptionBooksListAdapter.MyViewHolder.bind$lambda$0(AdoptionBooksData.this, adoptionBooksListAdapter, this, view);
                }
            });
            View root = this.binding.getRoot();
            final AdoptionBooksListAdapter adoptionBooksListAdapter2 = this.this$0;
            root.post(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.AdoptionBooksListAdapter$MyViewHolder$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    AdoptionBooksListAdapter.MyViewHolder.bind$lambda$1(AdoptionBooksListAdapter.this, this);
                }
            });
            this.this$0.listener.onSelectBooksList(item);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$0(AdoptionBooksData $item, AdoptionBooksListAdapter this$0, MyViewHolder this$1, View it) {
            Intrinsics.checkNotNull($item.isSelected());
            $item.setSelected(Boolean.valueOf(!r0.booleanValue()));
            this$0.listener.onSelectBooksList($item);
            this$0.notifyItemChanged(this$1.getBindingAdapterPosition());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$1(AdoptionBooksListAdapter this$0, MyViewHolder this$1) {
            this$0.notifyItemChanged(this$1.getBindingAdapterPosition());
        }
    }

    /* compiled from: AdoptionBooksListAdapter.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B7\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\fH\u0016J\b\u0010\u000f\u001a\u00020\fH\u0016J\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016R\u001e\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/AdoptionBooksListAdapter$LastDiffUtilCallback;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "newList", "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionBooksData;", "Lkotlin/collections/ArrayList;", "oldList", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/AdoptionBooksListAdapter;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "areItemsTheSame", "", "oldItemPosition", "", "newItemPosition", "getOldListSize", "getNewListSize", "areContentsTheSame", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class LastDiffUtilCallback extends DiffUtil.Callback {
        private final ArrayList<AdoptionBooksData> newList;
        private final ArrayList<AdoptionBooksData> oldList;
        final /* synthetic */ AdoptionBooksListAdapter this$0;

        public LastDiffUtilCallback(AdoptionBooksListAdapter this$0, ArrayList<AdoptionBooksData> newList, ArrayList<AdoptionBooksData> oldList) {
            Intrinsics.checkNotNullParameter(newList, "newList");
            Intrinsics.checkNotNullParameter(oldList, "oldList");
            this.this$0 = this$0;
            this.newList = newList;
            this.oldList = oldList;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return Intrinsics.areEqual(this.newList.get(newItemPosition), this.oldList.get(oldItemPosition));
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getOldListSize() {
            return this.oldList.size();
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getNewListSize() {
            return this.newList.size();
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return Intrinsics.areEqual(this.newList.get(newItemPosition), this.oldList.get(oldItemPosition));
        }
    }
}
