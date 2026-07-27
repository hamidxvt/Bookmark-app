package com.ingenious.androidbookmarksalesupgrade.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.OrderAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemAllProductsBinding;
import com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: OrderAdapter.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001 B\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0014\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011J\u0016\u0010\u0012\u001a\u00020\u000f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011H\u0002J\u0010\u0010\u0014\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\u001c\u0010\u0017\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u001c\u0010\u001c\u001a\u00020\u000f2\n\u0010\u001d\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001bH\u0016J\b\u0010\u001f\u001a\u00020\u001bH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u000bj\b\u0012\u0004\u0012\u00020\u0005`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u000bj\b\u0012\u0004\u0012\u00020\u0005`\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/OrderAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/OrderAdapter$ViewHolder;", "selectedProductList", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/ingenious/androidbookmarksalesupgrade/listener/RecyclerViewListener;", "<init>", "(Ljava/util/List;Lcom/ingenious/androidbookmarksalesupgrade/listener/RecyclerViewListener;)V", "allItems", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "displayedItems", "setItems", "", FirebaseAnalytics.Param.ITEMS, "", "updateDisplayedList", "newList", "filterProducts", SearchIntents.EXTRA_QUERY, "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "onBindViewHolder", "holder", "position", "getItemCount", "ViewHolder", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class OrderAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final ArrayList<Products> allItems;
    private final ArrayList<Products> displayedItems;
    private final RecyclerViewListener listener;
    private final List<Products> selectedProductList;

    public OrderAdapter(List<Products> selectedProductList, RecyclerViewListener listener) {
        Intrinsics.checkNotNullParameter(selectedProductList, "selectedProductList");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.selectedProductList = selectedProductList;
        this.listener = listener;
        this.allItems = new ArrayList<>();
        this.displayedItems = new ArrayList<>();
    }

    public final void setItems(List<Products> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.allItems.clear();
        this.allItems.addAll(items);
        updateDisplayedList(this.allItems);
    }

    private final void updateDisplayedList(List<Products> newList) {
        this.displayedItems.clear();
        this.displayedItems.addAll(newList);
        notifyDataSetChanged();
    }

    public final void filterProducts(String query) {
        List filteredList;
        String q = query == null ? "" : query;
        Log.i("RAF", "Filtering for query: " + q);
        boolean z = true;
        if (q.length() == 0) {
            filteredList = this.allItems;
        } else {
            Iterable $this$filter$iv = this.allItems;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                Products it = (Products) element$iv$iv;
                String title = it.getTitle();
                boolean match = (title == null || StringsKt.contains(title, q, z) != z) ? false : z;
                Log.i("RAF", "Checking product: " + it.getTitle() + ", match: " + match);
                if (match) {
                    destination$iv$iv.add(element$iv$iv);
                }
                z = true;
            }
            filteredList = (List) destination$iv$iv;
        }
        updateDisplayedList(filteredList);
    }

    /* compiled from: OrderAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/OrderAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemAllProductsBinding;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/OrderAdapter;Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemAllProductsBinding;)V", "bind", "", "item", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemAllProductsBinding binding;
        final /* synthetic */ OrderAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(OrderAdapter this$0, ItemAllProductsBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = this$0;
            this.binding = binding;
        }

        public final void bind(final Products item) {
            Intrinsics.checkNotNullParameter(item, "item");
            this.binding.setItem(item);
            this.binding.executePendingBindings();
            this.binding.addToCart.setImageResource(Intrinsics.areEqual((Object) item.isSelected(), (Object) true) ? R.drawable.ic_tick_selected : R.drawable.ic_add_inventory);
            ImageView imageView = this.binding.addToCart;
            final OrderAdapter orderAdapter = this.this$0;
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.OrderAdapter$ViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OrderAdapter.ViewHolder.bind$lambda$0(Products.this, orderAdapter, this, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$0(Products $item, OrderAdapter this$0, ViewHolder this$1, View it) {
            $item.setSelected(Boolean.valueOf(!($item.isSelected() != null ? r0.booleanValue() : false)));
            if (Intrinsics.areEqual((Object) $item.isSelected(), (Object) true) && !this$0.selectedProductList.contains($item)) {
                this$0.selectedProductList.add($item);
            } else if (Intrinsics.areEqual((Object) $item.isSelected(), (Object) false)) {
                this$0.selectedProductList.remove($item);
            }
            this$0.listener.onSelectProduct($item);
            this$0.notifyItemChanged(this$1.getAdapterPosition());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemAllProductsBinding inflate = ItemAllProductsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(this, inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Products products = this.displayedItems.get(position);
        Intrinsics.checkNotNullExpressionValue(products, "get(...)");
        holder.bind(products);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.displayedItems.size();
    }
}
