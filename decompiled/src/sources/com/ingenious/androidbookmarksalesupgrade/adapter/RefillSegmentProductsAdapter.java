package com.ingenious.androidbookmarksalesupgrade.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.RefillSegmentProductsAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.SegmentItemLowStockProductsBinding;
import com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener;
import com.ingenious.androidbookmarksalesupgrade.model.BookModel;
import com.ingenious.androidbookmarksalesupgrade.utils.LoggingInterceptor;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RefillSegmentProductsAdapter.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002)*B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u001e\u0010\u001a\u001a\u00020\u001b2\u0016\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0010j\b\u0012\u0004\u0012\u00020\u0007`\u0011J\u0016\u0010\u001d\u001a\u00020\u001b2\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006J\u0014\u0010\u001e\u001a\u00020\u001b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0014\u0010\u001f\u001a\u00020\u001b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0006\u0010 \u001a\u00020\u001bJ\u001c\u0010!\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\rH\u0016J\u001c\u0010%\u001a\u00020\u001b2\n\u0010&\u001a\u00060\u0002R\u00020\u00002\u0006\u0010'\u001a\u00020\rH\u0016J\b\u0010(\u001a\u00020\rH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000RD\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0010j\b\u0012\u0004\u0012\u00020\u0007`\u00112\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0010j\b\u0012\u0004\u0012\u00020\u0007`\u0011@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016RD\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0010j\b\u0012\u0004\u0012\u00020\u0007`\u00112\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0010j\b\u0012\u0004\u0012\u00020\u0007`\u0011@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016¨\u0006+"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/RefillSegmentProductsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/RefillSegmentProductsAdapter$RefillProductsViewHolder;", "context", "Landroid/content/Context;", "productsList", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/BookModel;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/ingenious/androidbookmarksalesupgrade/listener/RecyclerViewListener;", "<init>", "(Landroid/content/Context;Ljava/util/List;Lcom/ingenious/androidbookmarksalesupgrade/listener/RecyclerViewListener;)V", "itemQuantity", "", "selectedPosition", "value", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", FirebaseAnalytics.Param.ITEMS, "getItems", "()Ljava/util/ArrayList;", "setItems", "(Ljava/util/ArrayList;)V", "itemsFiltered", "getItemsFiltered", "setItemsFiltered", "addList", "", "products", "addNewList", "addNewSearchList", "addSearchedList", "clear", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onBindViewHolder", "holder", "position", "getItemCount", "RefillProductsViewHolder", "LastDiffUtilCallback", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class RefillSegmentProductsAdapter extends RecyclerView.Adapter<RefillProductsViewHolder> {
    private final Context context;
    private int itemQuantity;
    private ArrayList<BookModel> items;
    private ArrayList<BookModel> itemsFiltered;
    private final RecyclerViewListener listener;
    private final List<BookModel> productsList;
    private int selectedPosition;

    public /* synthetic */ RefillSegmentProductsAdapter(Context context, List list, RecyclerViewListener recyclerViewListener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? CollectionsKt.emptyList() : list, recyclerViewListener);
    }

    public RefillSegmentProductsAdapter(Context context, List<BookModel> list, RecyclerViewListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.context = context;
        this.productsList = list;
        this.listener = listener;
        if (this.productsList == null) {
            Log.e(LoggingInterceptor.TAG, "Products list is null!");
        }
        this.selectedPosition = -1;
        this.items = new ArrayList<>();
        this.itemsFiltered = new ArrayList<>(this.productsList);
    }

    /* compiled from: RefillSegmentProductsAdapter.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/RefillSegmentProductsAdapter$RefillProductsViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/SegmentItemLowStockProductsBinding;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/RefillSegmentProductsAdapter;Lcom/ingenious/androidbookmarksalesupgrade/databinding/SegmentItemLowStockProductsBinding;)V", "bind", "", "item", "Lcom/ingenious/androidbookmarksalesupgrade/model/BookModel;", "position", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class RefillProductsViewHolder extends RecyclerView.ViewHolder {
        private final SegmentItemLowStockProductsBinding binding;
        final /* synthetic */ RefillSegmentProductsAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RefillProductsViewHolder(RefillSegmentProductsAdapter this$0, SegmentItemLowStockProductsBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = this$0;
            this.binding = binding;
        }

        public final void bind(final BookModel item, int position) {
            Intrinsics.checkNotNullParameter(item, "item");
            item.setQuantity(0);
            this.binding.productName.setText(item.getProductName());
            this.binding.productQuantity.setText(String.valueOf(item.getQuantity()));
            TextView textView = this.binding.productPrice;
            String price = item.getPrice();
            if (price == null) {
                price = "0.00";
            }
            textView.setText(price);
            this.binding.firstQuantity.setBackground(ContextCompat.getDrawable(this.binding.getRoot().getContext(), R.drawable.edittext_background));
            TextView textView2 = this.binding.firstQuantity;
            final RefillSegmentProductsAdapter refillSegmentProductsAdapter = this.this$0;
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.RefillSegmentProductsAdapter$RefillProductsViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RefillSegmentProductsAdapter.RefillProductsViewHolder.bind$lambda$0(BookModel.this, this, refillSegmentProductsAdapter, view);
                }
            });
            TextView textView3 = this.binding.secondQuantity;
            final RefillSegmentProductsAdapter refillSegmentProductsAdapter2 = this.this$0;
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.RefillSegmentProductsAdapter$RefillProductsViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RefillSegmentProductsAdapter.RefillProductsViewHolder.bind$lambda$1(RefillSegmentProductsAdapter.RefillProductsViewHolder.this, item, refillSegmentProductsAdapter2, view);
                }
            });
            TextView textView4 = this.binding.thirdQuantity;
            final RefillSegmentProductsAdapter refillSegmentProductsAdapter3 = this.this$0;
            textView4.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.RefillSegmentProductsAdapter$RefillProductsViewHolder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RefillSegmentProductsAdapter.RefillProductsViewHolder.bind$lambda$2(BookModel.this, this, refillSegmentProductsAdapter3, view);
                }
            });
            ImageView imageView = this.binding.addIv;
            final RefillSegmentProductsAdapter refillSegmentProductsAdapter4 = this.this$0;
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.RefillSegmentProductsAdapter$RefillProductsViewHolder$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RefillSegmentProductsAdapter.RefillProductsViewHolder.bind$lambda$3(BookModel.this, this, refillSegmentProductsAdapter4, view);
                }
            });
            ImageView imageView2 = this.binding.subtractIv;
            final RefillSegmentProductsAdapter refillSegmentProductsAdapter5 = this.this$0;
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.RefillSegmentProductsAdapter$RefillProductsViewHolder$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RefillSegmentProductsAdapter.RefillProductsViewHolder.bind$lambda$4(BookModel.this, this, refillSegmentProductsAdapter5, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$0(BookModel $item, RefillProductsViewHolder this$0, RefillSegmentProductsAdapter this$1, View it) {
            $item.setQuantity(50);
            this$0.binding.productQuantity.setText(String.valueOf($item.getQuantity()));
            this$0.binding.firstQuantity.setBackground(ContextCompat.getDrawable(this$0.binding.getRoot().getContext(), R.drawable.edittext_background));
            this$0.binding.secondQuantity.setBackground(ContextCompat.getDrawable(this$0.binding.getRoot().getContext(), R.drawable.edittext_background));
            this$0.binding.secondQuantity.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this$0.binding.thirdQuantity.setBackground(ContextCompat.getDrawable(this$0.binding.getRoot().getContext(), R.drawable.edittext_background));
            this$0.binding.thirdQuantity.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this$1.listener.onSegmentTotalUpdated($item);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$1(RefillProductsViewHolder this$0, BookModel $item, RefillSegmentProductsAdapter this$1, View it) {
            this$0.binding.firstQuantity.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this$0.binding.firstQuantity.setBackground(ContextCompat.getDrawable(this$0.binding.getRoot().getContext(), R.drawable.edittext_background));
            this$0.binding.secondQuantity.setBackground(ContextCompat.getDrawable(this$0.binding.getRoot().getContext(), R.drawable.edittext_background));
            this$0.binding.thirdQuantity.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            $item.setQuantity(100);
            this$0.binding.thirdQuantity.setBackground(ContextCompat.getDrawable(this$0.binding.getRoot().getContext(), R.drawable.edittext_background));
            this$0.binding.productQuantity.setText(String.valueOf($item.getQuantity()));
            this$1.listener.onSegmentTotalUpdated($item);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$2(BookModel $item, RefillProductsViewHolder this$0, RefillSegmentProductsAdapter this$1, View it) {
            $item.setQuantity(200);
            this$0.binding.productQuantity.setText(String.valueOf($item.getQuantity()));
            this$0.binding.firstQuantity.setBackground(ContextCompat.getDrawable(this$0.binding.getRoot().getContext(), R.drawable.edittext_background));
            this$0.binding.firstQuantity.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this$0.binding.secondQuantity.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this$0.binding.secondQuantity.setBackground(ContextCompat.getDrawable(this$0.binding.getRoot().getContext(), R.drawable.edittext_background));
            this$0.binding.thirdQuantity.setBackground(ContextCompat.getDrawable(this$0.binding.getRoot().getContext(), R.drawable.edittext_background));
            this$1.listener.onSegmentTotalUpdated($item);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$3(BookModel $item, RefillProductsViewHolder this$0, RefillSegmentProductsAdapter this$1, View it) {
            int currentQuantity = $item.getQuantity();
            int updatedQuantity = currentQuantity + 1;
            $item.setQuantity(updatedQuantity);
            this$0.binding.productQuantity.setText(String.valueOf($item.getQuantity()));
            this$1.listener.onSegmentTotalUpdated($item);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$4(BookModel $item, RefillProductsViewHolder this$0, RefillSegmentProductsAdapter this$1, View it) {
            int currentQuantity = $item.getQuantity();
            if (currentQuantity > 0) {
                int updatedQuantity = currentQuantity - 1;
                $item.setQuantity(updatedQuantity);
                this$0.binding.productQuantity.setText(String.valueOf($item.getQuantity()));
                this$1.listener.onSegmentTotalUpdated($item);
                return;
            }
            Toast.makeText(this$1.context, "Quantity should be greater than 0", 1).show();
        }
    }

    public final ArrayList<BookModel> getItems() {
        return this.items;
    }

    public final void setItems(ArrayList<BookModel> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.items = value;
        LastDiffUtilCallback diffUtil = new LastDiffUtilCallback(this, this.items, this.itemsFiltered);
        DiffUtil.DiffResult diffUtilResult = DiffUtil.calculateDiff(diffUtil);
        Intrinsics.checkNotNullExpressionValue(diffUtilResult, "calculateDiff(...)");
        this.itemsFiltered.clear();
        this.itemsFiltered.addAll(value);
        diffUtilResult.dispatchUpdatesTo(this);
    }

    public final ArrayList<BookModel> getItemsFiltered() {
        return this.itemsFiltered;
    }

    public final void setItemsFiltered(ArrayList<BookModel> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.itemsFiltered = value;
        notifyDataSetChanged();
    }

    public final void addList(ArrayList<BookModel> products) {
        Intrinsics.checkNotNullParameter(products, "products");
        this.itemsFiltered.clear();
        this.itemsFiltered.addAll(products);
        notifyDataSetChanged();
    }

    public final void addNewList(List<BookModel> items) {
        this.itemsFiltered.addAll(items != null ? items : CollectionsKt.emptyList());
        notifyDataSetChanged();
    }

    public final void addNewSearchList(List<BookModel> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.itemsFiltered.clear();
        this.itemsFiltered.addAll(items);
        notifyDataSetChanged();
    }

    public final void addSearchedList(List<BookModel> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        setItemsFiltered((ArrayList) items);
        notifyDataSetChanged();
    }

    public final void clear() {
        this.itemsFiltered.clear();
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RefillProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        SegmentItemLowStockProductsBinding binding = SegmentItemLowStockProductsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "inflate(...)");
        return new RefillProductsViewHolder(this, binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RefillProductsViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        BookModel bookModel = this.itemsFiltered.get(position);
        Intrinsics.checkNotNullExpressionValue(bookModel, "get(...)");
        BookModel product = bookModel;
        holder.bind(product, position);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemsFiltered.size();
    }

    /* compiled from: RefillSegmentProductsAdapter.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B7\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\fH\u0016J\b\u0010\u000f\u001a\u00020\fH\u0016J\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016R\u001e\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/RefillSegmentProductsAdapter$LastDiffUtilCallback;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "newList", "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/BookModel;", "Lkotlin/collections/ArrayList;", "oldList", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/RefillSegmentProductsAdapter;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "areItemsTheSame", "", "oldItemPosition", "", "newItemPosition", "getOldListSize", "getNewListSize", "areContentsTheSame", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class LastDiffUtilCallback extends DiffUtil.Callback {
        private final ArrayList<BookModel> newList;
        private final ArrayList<BookModel> oldList;
        final /* synthetic */ RefillSegmentProductsAdapter this$0;

        public LastDiffUtilCallback(RefillSegmentProductsAdapter this$0, ArrayList<BookModel> newList, ArrayList<BookModel> oldList) {
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
