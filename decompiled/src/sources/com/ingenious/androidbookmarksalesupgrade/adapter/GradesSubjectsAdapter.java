package com.ingenious.androidbookmarksalesupgrade.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.GradesSubjectsAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemGradesSubjectsBinding;
import com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener;
import com.ingenious.androidbookmarksalesupgrade.model.response.GradesSubjectsData;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GradesSubjectsAdapter.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002\u001f B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001c\u0010\u0015\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u001c\u0010\u001a\u001a\u00020\u001b2\n\u0010\u001c\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u0019H\u0016J\b\u0010\u001e\u001a\u00020\u0019H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0082\u000e¢\u0006\u0002\n\u0000RD\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011RD\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011¨\u0006!"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/GradesSubjectsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/GradesSubjectsAdapter$MyViewHolder;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/ingenious/androidbookmarksalesupgrade/listener/RecyclerViewListener;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/listener/RecyclerViewListener;)V", "selectedProductList", "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GradesSubjectsData;", "Lkotlin/collections/ArrayList;", "productList", "value", FirebaseAnalytics.Param.ITEMS, "getItems", "()Ljava/util/ArrayList;", "setItems", "(Ljava/util/ArrayList;)V", "itemsFiltered", "getItemsFiltered", "setItemsFiltered", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "onBindViewHolder", "", "holder", "position", "getItemCount", "MyViewHolder", "LastDiffUtilCallback", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class GradesSubjectsAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private ArrayList<GradesSubjectsData> items;
    private ArrayList<GradesSubjectsData> itemsFiltered;
    private final RecyclerViewListener listener;
    private ArrayList<GradesSubjectsData> productList;
    private final ArrayList<GradesSubjectsData> selectedProductList;

    public GradesSubjectsAdapter(RecyclerViewListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
        this.selectedProductList = new ArrayList<>();
        this.productList = new ArrayList<>();
        this.items = new ArrayList<>();
        this.itemsFiltered = new ArrayList<>();
    }

    public final ArrayList<GradesSubjectsData> getItems() {
        return this.items;
    }

    public final void setItems(ArrayList<GradesSubjectsData> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.items = value;
        LastDiffUtilCallback diffUtil = new LastDiffUtilCallback(this, this.items, this.itemsFiltered);
        DiffUtil.DiffResult diffUtilResult = DiffUtil.calculateDiff(diffUtil);
        Intrinsics.checkNotNullExpressionValue(diffUtilResult, "calculateDiff(...)");
        this.itemsFiltered.clear();
        this.itemsFiltered.addAll(value);
        diffUtilResult.dispatchUpdatesTo(this);
    }

    public final ArrayList<GradesSubjectsData> getItemsFiltered() {
        return this.itemsFiltered;
    }

    public final void setItemsFiltered(ArrayList<GradesSubjectsData> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.itemsFiltered = value;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemGradesSubjectsBinding binding = ItemGradesSubjectsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "inflate(...)");
        return new MyViewHolder(this, binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        GradesSubjectsData gradesSubjectsData = this.itemsFiltered.get(position);
        Intrinsics.checkNotNullExpressionValue(gradesSubjectsData, "get(...)");
        GradesSubjectsData product = gradesSubjectsData;
        holder.bind(product);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemsFiltered.size();
    }

    /* compiled from: GradesSubjectsAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/GradesSubjectsAdapter$MyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemGradesSubjectsBinding;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/GradesSubjectsAdapter;Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemGradesSubjectsBinding;)V", "bind", "", "item", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GradesSubjectsData;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemGradesSubjectsBinding binding;
        final /* synthetic */ GradesSubjectsAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(GradesSubjectsAdapter this$0, ItemGradesSubjectsBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = this$0;
            this.binding = binding;
        }

        public final void bind(final GradesSubjectsData item) {
            Intrinsics.checkNotNullParameter(item, "item");
            this.binding.setItem(item);
            this.binding.executePendingBindings();
            ImageView icTickToggleIv = this.binding.icTickToggleIv;
            Intrinsics.checkNotNullExpressionValue(icTickToggleIv, "icTickToggleIv");
            icTickToggleIv.setVisibility(0);
            Boolean isSelected = item.isSelected();
            Intrinsics.checkNotNull(isSelected);
            if (isSelected.booleanValue()) {
                icTickToggleIv.setImageResource(R.drawable.ic_selection_tick);
                this.binding.card.setStrokeColor(ContextCompat.getColor(new MyViewHolder(this.this$0, this.binding).itemView.getContext(), R.color.app_color));
                this.binding.card.setStrokeWidth(1);
            } else {
                icTickToggleIv.setImageResource(R.drawable.ic_unselected_item);
                this.binding.card.setStrokeColor(ContextCompat.getColor(new MyViewHolder(this.this$0, this.binding).itemView.getContext(), R.color.white));
            }
            LinearLayout linearLayout = this.binding.linearSegments;
            final GradesSubjectsAdapter gradesSubjectsAdapter = this.this$0;
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.GradesSubjectsAdapter$MyViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GradesSubjectsAdapter.MyViewHolder.bind$lambda$0(GradesSubjectsData.this, gradesSubjectsAdapter, view);
                }
            });
            View root = this.binding.getRoot();
            final GradesSubjectsAdapter gradesSubjectsAdapter2 = this.this$0;
            root.post(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.GradesSubjectsAdapter$MyViewHolder$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    GradesSubjectsAdapter.MyViewHolder.bind$lambda$1(GradesSubjectsAdapter.this, this);
                }
            });
            this.this$0.listener.onSelectGradesSubjects(item);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$0(GradesSubjectsData $item, GradesSubjectsAdapter this$0, View it) {
            Intrinsics.checkNotNull($item.isSelected());
            $item.setSelected(Boolean.valueOf(!r0.booleanValue()));
            Boolean isSelected = $item.isSelected();
            Intrinsics.checkNotNull(isSelected);
            if (isSelected.booleanValue()) {
                if (!this$0.selectedProductList.contains($item)) {
                    this$0.selectedProductList.add($item);
                    return;
                }
                return;
            }
            this$0.selectedProductList.remove($item);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$1(GradesSubjectsAdapter this$0, MyViewHolder this$1) {
            this$0.notifyItemChanged(this$1.getBindingAdapterPosition());
        }
    }

    /* compiled from: GradesSubjectsAdapter.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B7\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\fH\u0016J\b\u0010\u000f\u001a\u00020\fH\u0016J\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016R\u001e\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/GradesSubjectsAdapter$LastDiffUtilCallback;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "newList", "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GradesSubjectsData;", "Lkotlin/collections/ArrayList;", "oldList", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/GradesSubjectsAdapter;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "areItemsTheSame", "", "oldItemPosition", "", "newItemPosition", "getOldListSize", "getNewListSize", "areContentsTheSame", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class LastDiffUtilCallback extends DiffUtil.Callback {
        private final ArrayList<GradesSubjectsData> newList;
        private final ArrayList<GradesSubjectsData> oldList;
        final /* synthetic */ GradesSubjectsAdapter this$0;

        public LastDiffUtilCallback(GradesSubjectsAdapter this$0, ArrayList<GradesSubjectsData> newList, ArrayList<GradesSubjectsData> oldList) {
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
