package com.ingenious.androidbookmarksalesupgrade.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemAdoptionQuantityBinding;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooksData;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdoptionQuantityAdapter.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0014B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\b\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\fH\u0016J\u0014\u0010\u0012\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/AdoptionQuantityAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/AdoptionQuantityAdapter$QuantityViewHolder;", FirebaseAnalytics.Param.ITEMS, "", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionBooksData;", "<init>", "(Ljava/util/List;)V", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "onBindViewHolder", "", "holder", "position", "getItemCount", "updateItems", "newItems", "QuantityViewHolder", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class AdoptionQuantityAdapter extends RecyclerView.Adapter<QuantityViewHolder> {
    private List<AdoptionBooksData> items;

    public AdoptionQuantityAdapter(List<AdoptionBooksData> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.items = items;
    }

    /* compiled from: AdoptionQuantityAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/AdoptionQuantityAdapter$QuantityViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemAdoptionQuantityBinding;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/AdoptionQuantityAdapter;Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemAdoptionQuantityBinding;)V", "getBinding", "()Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemAdoptionQuantityBinding;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class QuantityViewHolder extends RecyclerView.ViewHolder {
        private final ItemAdoptionQuantityBinding binding;
        final /* synthetic */ AdoptionQuantityAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public QuantityViewHolder(AdoptionQuantityAdapter this$0, ItemAdoptionQuantityBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = this$0;
            this.binding = binding;
        }

        public final ItemAdoptionQuantityBinding getBinding() {
            return this.binding;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public QuantityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemAdoptionQuantityBinding binding = ItemAdoptionQuantityBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "inflate(...)");
        return new QuantityViewHolder(this, binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(QuantityViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final AdoptionBooksData item = this.items.get(position);
        final ItemAdoptionQuantityBinding b = holder.getBinding();
        b.tvBookTitle.setText(item.getName());
        b.tvBookDetails.setText(item.getGrade() + " • " + item.getSubject());
        TextView textView = b.tvQuantity;
        Integer quantity = item.getQuantity();
        textView.setText(String.valueOf(quantity != null ? quantity.intValue() : 1));
        Glide.with(holder.itemView.getContext()).load(item.getImage()).placeholder(R.drawable.bookmark_logo_white).into(b.ivBookCover);
        b.ivPlus.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.AdoptionQuantityAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdoptionQuantityAdapter.onBindViewHolder$lambda$0(ItemAdoptionQuantityBinding.this, item, view);
            }
        });
        b.ivMinus.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.AdoptionQuantityAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdoptionQuantityAdapter.onBindViewHolder$lambda$1(ItemAdoptionQuantityBinding.this, item, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(ItemAdoptionQuantityBinding $b, AdoptionBooksData $item, View it) {
        int quantity = Integer.parseInt($b.tvQuantity.getText().toString()) + 1;
        $b.tvQuantity.setText(String.valueOf(quantity));
        $item.setQuantity(Integer.valueOf(quantity));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$1(ItemAdoptionQuantityBinding $b, AdoptionBooksData $item, View it) {
        int quantity = Integer.parseInt($b.tvQuantity.getText().toString());
        if (quantity > 1) {
            int quantity2 = quantity - 1;
            $b.tvQuantity.setText(String.valueOf(quantity2));
            $item.setQuantity(Integer.valueOf(quantity2));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: getItemCount */
    public int getTabCount() {
        return this.items.size();
    }

    public final void updateItems(List<AdoptionBooksData> newItems) {
        Intrinsics.checkNotNullParameter(newItems, "newItems");
        this.items = newItems;
        notifyDataSetChanged();
    }
}
