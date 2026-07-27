package com.ingenious.androidbookmarksalesupgrade.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.LowStockRefillAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemInventoryLowStockBinding;
import com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LowStockRefillAdapter.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002&'B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001e\u0010\u0014\u001a\u00020\u00152\u0016\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nJ\u0014\u0010\u0017\u001a\u00020\u00152\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u0018J\u0014\u0010\u0019\u001a\u00020\u00152\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u0018J\u0014\u0010\u001a\u001a\u00020\u00152\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u0018J\u0006\u0010\u001b\u001a\u00020\u0015J\u001c\u0010\u001c\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u001c\u0010!\u001a\u00020\u00152\n\u0010\"\u001a\u00060\u0002R\u00020\u00002\u0006\u0010#\u001a\u00020 H\u0016J\b\u0010$\u001a\u00020 H\u0016J\u000e\u0010%\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0082\u000e¢\u0006\u0002\n\u0000RD\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010RD\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006("}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/LowStockRefillAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/LowStockRefillAdapter$LowStockRefillViewHolder;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/ingenious/androidbookmarksalesupgrade/listener/RecyclerViewListener;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/listener/RecyclerViewListener;)V", "productList", "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "Lkotlin/collections/ArrayList;", "value", FirebaseAnalytics.Param.ITEMS, "getItems", "()Ljava/util/ArrayList;", "setItems", "(Ljava/util/ArrayList;)V", "itemsFiltered", "getItemsFiltered", "setItemsFiltered", "addList", "", "products", "addNewList", "", "addNewSearchList", "addSearchedList", "clear", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "onBindViewHolder", "holder", "position", "getItemCount", "updateItem", "LowStockRefillViewHolder", "LastDiffUtilCallback", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class LowStockRefillAdapter extends RecyclerView.Adapter<LowStockRefillViewHolder> {
    private ArrayList<Products> items;
    private ArrayList<Products> itemsFiltered;
    private final RecyclerViewListener listener;
    private ArrayList<Products> productList;

    public LowStockRefillAdapter(RecyclerViewListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
        this.productList = new ArrayList<>();
        this.items = new ArrayList<>();
        this.itemsFiltered = new ArrayList<>();
    }

    public final ArrayList<Products> getItems() {
        return this.items;
    }

    public final void setItems(ArrayList<Products> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.items = value;
        LastDiffUtilCallback diffUtil = new LastDiffUtilCallback(this, this.items, this.itemsFiltered);
        DiffUtil.DiffResult diffUtilResult = DiffUtil.calculateDiff(diffUtil);
        Intrinsics.checkNotNullExpressionValue(diffUtilResult, "calculateDiff(...)");
        this.itemsFiltered.clear();
        this.itemsFiltered.addAll(value);
        diffUtilResult.dispatchUpdatesTo(this);
    }

    public final ArrayList<Products> getItemsFiltered() {
        return this.itemsFiltered;
    }

    public final void setItemsFiltered(ArrayList<Products> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.itemsFiltered = value;
        notifyDataSetChanged();
    }

    public final void addList(ArrayList<Products> products) {
        Intrinsics.checkNotNullParameter(products, "products");
        this.itemsFiltered.clear();
        this.itemsFiltered.addAll(products);
        notifyDataSetChanged();
    }

    public final void addNewList(List<Products> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.itemsFiltered.addAll(items);
        notifyDataSetChanged();
    }

    public final void addNewSearchList(List<Products> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.itemsFiltered.clear();
        this.itemsFiltered.addAll(items);
        notifyDataSetChanged();
    }

    public final void addSearchedList(List<Products> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        setItemsFiltered((ArrayList) items);
        notifyDataSetChanged();
    }

    public final void clear() {
        this.itemsFiltered.clear();
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public LowStockRefillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemInventoryLowStockBinding binding = ItemInventoryLowStockBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "inflate(...)");
        return new LowStockRefillViewHolder(this, binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(LowStockRefillViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Products products = this.itemsFiltered.get(position);
        Intrinsics.checkNotNullExpressionValue(products, "get(...)");
        Products product = products;
        holder.bind(product);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: getItemCount */
    public int getTabCount() {
        return this.itemsFiltered.size();
    }

    /* compiled from: LowStockRefillAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/LowStockRefillAdapter$LowStockRefillViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemInventoryLowStockBinding;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/LowStockRefillAdapter;Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemInventoryLowStockBinding;)V", "bind", "", "item", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class LowStockRefillViewHolder extends RecyclerView.ViewHolder {
        private final ItemInventoryLowStockBinding binding;
        final /* synthetic */ LowStockRefillAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LowStockRefillViewHolder(LowStockRefillAdapter this$0, ItemInventoryLowStockBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = this$0;
            this.binding = binding;
        }

        public final void bind(final Products item) {
            Intrinsics.checkNotNullParameter(item, "item");
            this.binding.setItem(item);
            this.binding.executePendingBindings();
            Log.i("RAF", "bind: " + item.getImage());
            this.binding.productPrice.setText("PKR " + item.getPrice());
            View findViewById = this.binding.getRoot().findViewById(R.id.addToCart);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            ImageView selectIcon = (ImageView) findViewById;
            Boolean isSelected = item.isSelected();
            Intrinsics.checkNotNull(isSelected);
            if (isSelected.booleanValue()) {
                selectIcon.setImageResource(R.drawable.ic_tick_selected);
            } else {
                selectIcon.setImageResource(R.drawable.ic_add_inventory);
            }
            ImageView imageView = this.binding.addToCart;
            final LowStockRefillAdapter lowStockRefillAdapter = this.this$0;
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.LowStockRefillAdapter$LowStockRefillViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LowStockRefillAdapter.LowStockRefillViewHolder.bind$lambda$0(Products.this, lowStockRefillAdapter, this, view);
                }
            });
            Glide.with(this.itemView.getContext()).load(item.getImage()).placeholder(R.drawable.photo).error(R.drawable.photo).into((ImageView) this.itemView.findViewById(R.id.productImage));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$0(Products $item, LowStockRefillAdapter this$0, LowStockRefillViewHolder this$1, View it) {
            Intrinsics.checkNotNull($item.isSelected());
            $item.setSelected(Boolean.valueOf(!r0.booleanValue()));
            this$0.listener.onSelectProduct($item);
            this$0.notifyItemChanged(this$1.getAbsoluteAdapterPosition());
        }
    }

    public final void updateItem(Products products) {
        Intrinsics.checkNotNullParameter(products, "products");
        notifyItemChanged(this.productList.indexOf(products), products);
    }

    /* compiled from: LowStockRefillAdapter.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B7\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\fH\u0016J\b\u0010\u000f\u001a\u00020\fH\u0016J\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016R\u001e\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/LowStockRefillAdapter$LastDiffUtilCallback;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "newList", "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "Lkotlin/collections/ArrayList;", "oldList", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/LowStockRefillAdapter;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "areItemsTheSame", "", "oldItemPosition", "", "newItemPosition", "getOldListSize", "getNewListSize", "areContentsTheSame", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class LastDiffUtilCallback extends DiffUtil.Callback {
        private final ArrayList<Products> newList;
        private final ArrayList<Products> oldList;
        final /* synthetic */ LowStockRefillAdapter this$0;

        public LastDiffUtilCallback(LowStockRefillAdapter this$0, ArrayList<Products> newList, ArrayList<Products> oldList) {
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
