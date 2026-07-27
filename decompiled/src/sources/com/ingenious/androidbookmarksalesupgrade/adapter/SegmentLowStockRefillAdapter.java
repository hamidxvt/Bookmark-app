package com.ingenious.androidbookmarksalesupgrade.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.SegmentLowStockRefillAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.SegmentItemInventoryLowStockBinding;
import com.ingenious.androidbookmarksalesupgrade.model.BookModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SegmentLowStockRefillAdapter.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0010#\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001bB!\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004¢\u0006\u0004\b\b\u0010\tJ\u001c\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000eH\u0016J\b\u0010\u0013\u001a\u00020\u000eH\u0016J\u001c\u0010\u0014\u001a\u00020\u00072\n\u0010\u0015\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u000eH\u0016J\u0014\u0010\u0017\u001a\u00020\u00072\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u0019J\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u0019R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/SegmentLowStockRefillAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/SegmentLowStockRefillAdapter$ViewHolder;", "onProductSelected", "Lkotlin/Function2;", "Lcom/ingenious/androidbookmarksalesupgrade/model/BookModel;", "", "", "<init>", "(Lkotlin/jvm/functions/Function2;)V", FirebaseAnalytics.Param.ITEMS, "", "selectedItems", "", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "getItemCount", "onBindViewHolder", "holder", "position", "setNewList", "newList", "", "getSelectedBooks", "ViewHolder", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class SegmentLowStockRefillAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final List<BookModel> items;
    private final Function2<BookModel, Boolean, Unit> onProductSelected;
    private final Set<Integer> selectedItems;

    /* JADX WARN: Multi-variable type inference failed */
    public SegmentLowStockRefillAdapter(Function2<? super BookModel, ? super Boolean, Unit> onProductSelected) {
        Intrinsics.checkNotNullParameter(onProductSelected, "onProductSelected");
        this.onProductSelected = onProductSelected;
        this.items = new ArrayList();
        this.selectedItems = new LinkedHashSet();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        SegmentItemInventoryLowStockBinding binding = SegmentItemInventoryLowStockBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "inflate(...)");
        return new ViewHolder(this, binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.items.get(position), position);
    }

    public final void setNewList(List<BookModel> newList) {
        Intrinsics.checkNotNullParameter(newList, "newList");
        this.items.clear();
        this.items.addAll(newList);
        this.selectedItems.clear();
        notifyDataSetChanged();
    }

    public final List<BookModel> getSelectedBooks() {
        Iterable $this$map$iv = this.selectedItems;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            int index = ((Number) item$iv$iv).intValue();
            destination$iv$iv.add(this.items.get(index));
        }
        return (List) destination$iv$iv;
    }

    /* compiled from: SegmentLowStockRefillAdapter.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/SegmentLowStockRefillAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/SegmentItemInventoryLowStockBinding;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/SegmentLowStockRefillAdapter;Lcom/ingenious/androidbookmarksalesupgrade/databinding/SegmentItemInventoryLowStockBinding;)V", "bind", "", "product", "Lcom/ingenious/androidbookmarksalesupgrade/model/BookModel;", "position", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final SegmentItemInventoryLowStockBinding binding;
        final /* synthetic */ SegmentLowStockRefillAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(SegmentLowStockRefillAdapter this$0, SegmentItemInventoryLowStockBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = this$0;
            this.binding = binding;
        }

        public final void bind(final BookModel product, final int position) {
            Intrinsics.checkNotNullParameter(product, "product");
            final SegmentItemInventoryLowStockBinding $this$bind_u24lambda_u242 = this.binding;
            final SegmentLowStockRefillAdapter segmentLowStockRefillAdapter = this.this$0;
            $this$bind_u24lambda_u242.productName.setText(product.getProductName());
            $this$bind_u24lambda_u242.productPrice.setText("PKR " + product.getPrice());
            $this$bind_u24lambda_u242.stockTv.setText(String.valueOf(product.getQuantity()));
            $this$bind_u24lambda_u242.productImage.setImageResource(R.drawable.photo);
            if (segmentLowStockRefillAdapter.selectedItems.contains(Integer.valueOf(position))) {
                $this$bind_u24lambda_u242.addToCart.setImageResource(R.drawable.ic_tick_selected);
            } else {
                $this$bind_u24lambda_u242.addToCart.setImageResource(R.drawable.ic_add_inventory);
            }
            $this$bind_u24lambda_u242.addToCart.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.SegmentLowStockRefillAdapter$ViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SegmentLowStockRefillAdapter.ViewHolder.bind$lambda$2$lambda$0(SegmentLowStockRefillAdapter.this, position, product, view);
                }
            });
            $this$bind_u24lambda_u242.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.SegmentLowStockRefillAdapter$ViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SegmentLowStockRefillAdapter.ViewHolder.bind$lambda$2$lambda$1(SegmentItemInventoryLowStockBinding.this, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$2$lambda$0(SegmentLowStockRefillAdapter this$0, int $position, BookModel $product, View it) {
            boolean isSelected;
            if (this$0.selectedItems.contains(Integer.valueOf($position))) {
                this$0.selectedItems.remove(Integer.valueOf($position));
                isSelected = false;
            } else {
                this$0.selectedItems.add(Integer.valueOf($position));
                isSelected = true;
            }
            this$0.notifyItemChanged($position);
            this$0.onProductSelected.invoke($product, Boolean.valueOf(isSelected));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$2$lambda$1(SegmentItemInventoryLowStockBinding $this_apply, View it) {
            $this_apply.addToCart.performClick();
        }
    }
}
