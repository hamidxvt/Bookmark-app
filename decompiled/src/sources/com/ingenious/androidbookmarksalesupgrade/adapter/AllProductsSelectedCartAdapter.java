package com.ingenious.androidbookmarksalesupgrade.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.AllProductsSelectedCartAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemAllProductsCartBinding;
import com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AllProductsSelectedCartAdapter.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002 !B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010J\u0014\u0010\u0011\u001a\u00020\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010J\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0007J\u001c\u0010\u0015\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u001c\u0010\u001a\u001a\u00020\u000e2\n\u0010\u001b\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u0019H\u0016J\b\u0010\u001d\u001a\u00020\u0019H\u0016J\u000e\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0019J\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/AllProductsSelectedCartAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/AllProductsSelectedCartAdapter$MyViewHolder;", "context", "Landroid/content/Context;", "productsList", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/ingenious/androidbookmarksalesupgrade/listener/RecyclerViewListener;", "<init>", "(Landroid/content/Context;Ljava/util/List;Lcom/ingenious/androidbookmarksalesupgrade/listener/RecyclerViewListener;)V", "itemsFiltered", "addList", "", "products", "", "updateList", "newProducts", "removeItem", "product", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "onBindViewHolder", "holder", "position", "getItemCount", "getItem", "getAllItems", "MyViewHolder", "LastDiffUtilCallback", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class AllProductsSelectedCartAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private final Context context;
    private final List<Products> itemsFiltered;
    private final RecyclerViewListener listener;
    private final List<Products> productsList;

    public AllProductsSelectedCartAdapter(Context context, List<Products> productsList, RecyclerViewListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(productsList, "productsList");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.context = context;
        this.productsList = productsList;
        this.listener = listener;
        this.itemsFiltered = new ArrayList();
        this.itemsFiltered.addAll(this.productsList);
    }

    public final void addList(List<Products> products) {
        Intrinsics.checkNotNullParameter(products, "products");
        this.itemsFiltered.clear();
        this.itemsFiltered.addAll(products);
        notifyDataSetChanged();
    }

    public final void updateList(List<Products> newProducts) {
        Intrinsics.checkNotNullParameter(newProducts, "newProducts");
        this.itemsFiltered.clear();
        this.itemsFiltered.addAll(newProducts);
        notifyDataSetChanged();
    }

    public final void removeItem(Products product) {
        Intrinsics.checkNotNullParameter(product, "product");
        List $this$indexOfFirst$iv = this.itemsFiltered;
        int index$iv = 0;
        Iterator<Products> it = $this$indexOfFirst$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object item$iv = it.next();
                Products it2 = (Products) item$iv;
                if (Intrinsics.areEqual(it2.getId(), product.getId())) {
                    break;
                } else {
                    index$iv++;
                }
            } else {
                index$iv = -1;
                break;
            }
        }
        int index = index$iv;
        if (index != -1) {
            this.itemsFiltered.remove(index);
            notifyItemRemoved(index);
            if (this.itemsFiltered.isEmpty()) {
                notifyDataSetChanged();
            }
            Log.d("CartAdapter", "Removed: " + product.getTitle() + ", New size=" + this.productsList.size());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemAllProductsCartBinding binding = ItemAllProductsCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "inflate(...)");
        return new MyViewHolder(this, binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Products product = this.itemsFiltered.get(position);
        holder.bind(product);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: getItemCount */
    public int getTabCount() {
        return this.itemsFiltered.size();
    }

    /* compiled from: AllProductsSelectedCartAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/AllProductsSelectedCartAdapter$MyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemAllProductsCartBinding;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/AllProductsSelectedCartAdapter;Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemAllProductsCartBinding;)V", "bind", "", "item", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemAllProductsCartBinding binding;
        final /* synthetic */ AllProductsSelectedCartAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(AllProductsSelectedCartAdapter this$0, ItemAllProductsCartBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = this$0;
            this.binding = binding;
        }

        public final void bind(final Products item) {
            Intrinsics.checkNotNullParameter(item, "item");
            TextView textView = this.binding.productName;
            String productName = item.getProductName();
            textView.setText(productName != null ? productName : "");
            TextView textView2 = this.binding.productPrice;
            String price = item.getPrice();
            if (price == null && (price = item.getCompanyPrice()) == null) {
                price = "0";
            }
            textView2.setText("PKR " + price);
            TextView textView3 = this.binding.grade;
            String grade = item.getGrade();
            textView3.setText(grade != null ? grade : "");
            TextView textView4 = this.binding.subject;
            String subject = item.getSubject();
            textView4.setText(subject != null ? subject : "");
            TextView textView5 = this.binding.tvQuantity;
            String quantity = item.getQuantity();
            if (quantity == null) {
                quantity = "1";
            }
            textView5.setText(quantity);
            Glide.with(this.this$0.context).load(item.getImage()).placeholder(R.drawable.photo).error(R.drawable.photo).centerCrop().into(this.binding.productImage);
            LinearLayout linearLayout = this.binding.deleteProductIv;
            final AllProductsSelectedCartAdapter allProductsSelectedCartAdapter = this.this$0;
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.AllProductsSelectedCartAdapter$MyViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AllProductsSelectedCartAdapter.MyViewHolder.bind$lambda$0(AllProductsSelectedCartAdapter.this, item, view);
                }
            });
            ImageView imageView = this.binding.btnPlus;
            final AllProductsSelectedCartAdapter allProductsSelectedCartAdapter2 = this.this$0;
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.AllProductsSelectedCartAdapter$MyViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AllProductsSelectedCartAdapter.MyViewHolder.bind$lambda$1(Products.this, this, allProductsSelectedCartAdapter2, view);
                }
            });
            ImageView imageView2 = this.binding.btnMinus;
            final AllProductsSelectedCartAdapter allProductsSelectedCartAdapter3 = this.this$0;
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.AllProductsSelectedCartAdapter$MyViewHolder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AllProductsSelectedCartAdapter.MyViewHolder.bind$lambda$2(Products.this, this, allProductsSelectedCartAdapter3, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$0(AllProductsSelectedCartAdapter this$0, Products $item, View it) {
            this$0.listener.onTapDelete($item);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$1(Products $item, MyViewHolder this$0, AllProductsSelectedCartAdapter this$1, View it) {
            Integer intOrNull;
            String quantity = $item.getQuantity();
            int currentQty = (quantity == null || (intOrNull = StringsKt.toIntOrNull(quantity)) == null) ? 0 : intOrNull.intValue();
            int newQty = currentQty + 1;
            $item.setQuantity(String.valueOf(newQty));
            this$0.binding.tvQuantity.setText($item.getQuantity());
            this$1.listener.onTapUpdateQuantity($item);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$2(Products $item, MyViewHolder this$0, AllProductsSelectedCartAdapter this$1, View it) {
            Integer intOrNull;
            String quantity = $item.getQuantity();
            int currentQty = (quantity == null || (intOrNull = StringsKt.toIntOrNull(quantity)) == null) ? 1 : intOrNull.intValue();
            if (currentQty > 1) {
                int newQty = currentQty - 1;
                $item.setQuantity(String.valueOf(newQty));
                this$0.binding.tvQuantity.setText($item.getQuantity());
                this$1.listener.onTapUpdateQuantity($item);
            }
        }
    }

    /* compiled from: AllProductsSelectedCartAdapter.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B7\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\fH\u0016J\b\u0010\u000f\u001a\u00020\fH\u0016J\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016R\u001e\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/AllProductsSelectedCartAdapter$LastDiffUtilCallback;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "newList", "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "Lkotlin/collections/ArrayList;", "oldList", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/AllProductsSelectedCartAdapter;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "areItemsTheSame", "", "oldItemPosition", "", "newItemPosition", "getOldListSize", "getNewListSize", "areContentsTheSame", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class LastDiffUtilCallback extends DiffUtil.Callback {
        private final ArrayList<Products> newList;
        private final ArrayList<Products> oldList;
        final /* synthetic */ AllProductsSelectedCartAdapter this$0;

        public LastDiffUtilCallback(AllProductsSelectedCartAdapter this$0, ArrayList<Products> newList, ArrayList<Products> oldList) {
            Intrinsics.checkNotNullParameter(newList, "newList");
            Intrinsics.checkNotNullParameter(oldList, "oldList");
            this.this$0 = this$0;
            this.newList = newList;
            this.oldList = oldList;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return Intrinsics.areEqual(this.newList.get(newItemPosition).getId(), this.oldList.get(oldItemPosition).getId());
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

    public final Products getItem(int position) {
        return this.itemsFiltered.get(position);
    }

    public final List<Products> getAllItems() {
        return CollectionsKt.toList(this.itemsFiltered);
    }
}
