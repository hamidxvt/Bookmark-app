package com.ingenious.androidbookmarksalesupgrade.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.model.response.CustomersData;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: GenericAdapter.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0012\u0012\u000e\u0012\f0\u0003R\b\u0012\u0004\u0012\u0002H\u00010\u00000\u0002:\u0003123B#\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\"\u0010\u0015\u001a\f0\u0003R\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0005H\u0016J\"\u0010\u0019\u001a\u00020\u001a2\u0010\u0010\u001b\u001a\f0\u0003R\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001c\u001a\u00020\u0005H\u0016J\b\u0010\u001d\u001a\u00020\u0005H\u0016J\u0010\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0005H\u0016J\u001b\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u0005¢\u0006\u0002\u0010!J\u0013\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00028\u0000¢\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020\u001a2\u0006\u0010 \u001a\u00028\u0000¢\u0006\u0002\u0010\"J\u0014\u0010$\u001a\u00020\u001a2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000%J\u0014\u0010&\u001a\u00020\u001a2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000%J\u0014\u0010'\u001a\u00020\u001a2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000%J\u000e\u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020*J\u000e\u0010+\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020*J\u0014\u0010,\u001a\u00020\u001a2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000%J\u0014\u0010-\u001a\u00020\u001a2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000%J\u0006\u0010.\u001a\u00020\u001aJ\u0014\u0010/\u001a\u00020\u001a2\f\u00100\u001a\b\u0012\u0004\u0012\u00028\u00000%R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000RD\u0010\r\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u000bj\b\u0012\u0004\u0012\u00028\u0000`\f2\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u000bj\b\u0012\u0004\u0012\u00028\u0000`\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011RD\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u000bj\b\u0012\u0004\u0012\u00028\u0000`\f2\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u000bj\b\u0012\u0004\u0012\u00028\u0000`\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011¨\u00064"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter;", "T", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter$MyViewHolder;", "layout", "", "itemClickListener", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter$OnItemClickListener;", "<init>", "(ILcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter$OnItemClickListener;)V", "value", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", FirebaseAnalytics.Param.ITEMS, "getItems", "()Ljava/util/ArrayList;", "setItems", "(Ljava/util/ArrayList;)V", "itemsFiltered", "getItemsFiltered", "setItemsFiltered", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onBindViewHolder", "", "holder", "position", "getItemCount", "getItemViewType", "removeItem", "item", "(Ljava/lang/Object;I)V", "(Ljava/lang/Object;)V", "addItem", "addList", "", "addListForInventory", "addListForCustomer", "filterProducts", SearchIntents.EXTRA_QUERY, "", "filterCustomers", "addNewList", "addSearchedList", "clearList", "setList", "list", "OnItemClickListener", "MyViewHolder", "LastDiffUtilCallback", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class GenericAdapter<T> extends RecyclerView.Adapter<GenericAdapter<T>.MyViewHolder> {
    private final OnItemClickListener<T> itemClickListener;
    private ArrayList<T> items;
    private ArrayList<T> itemsFiltered;
    private final int layout;

    public /* synthetic */ GenericAdapter(int i, OnItemClickListener onItemClickListener, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : onItemClickListener);
    }

    public GenericAdapter(int layout, OnItemClickListener<T> onItemClickListener) {
        this.layout = layout;
        this.itemClickListener = onItemClickListener;
        this.items = new ArrayList<>();
        this.itemsFiltered = new ArrayList<>();
    }

    public final ArrayList<T> getItems() {
        return this.items;
    }

    public final void setItems(ArrayList<T> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.items = value;
        LastDiffUtilCallback diffUtil = new LastDiffUtilCallback(this, this.items, this.itemsFiltered);
        DiffUtil.DiffResult diffUtilResult = DiffUtil.calculateDiff(diffUtil);
        Intrinsics.checkNotNullExpressionValue(diffUtilResult, "calculateDiff(...)");
        this.itemsFiltered.clear();
        this.itemsFiltered.addAll(value);
        diffUtilResult.dispatchUpdatesTo(this);
    }

    public final ArrayList<T> getItemsFiltered() {
        return this.itemsFiltered;
    }

    public final void setItemsFiltered(ArrayList<T> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.itemsFiltered = value;
        notifyDataSetChanged();
    }

    /* compiled from: GenericAdapter.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0006J\u0016\u0010\b\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\tH\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter$OnItemClickListener;", "T", "", "onItemClick", "", "item", "(Ljava/lang/Object;)V", "onItemClickTwo", "onSelectionChanged", "", "onCall", "visitId", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public interface OnItemClickListener<T> {
        void onCall(int visitId);

        void onItemClick(T item);

        void onItemClickTwo(T item);

        void onSelectionChanged(List<? extends T> item);

        /* compiled from: GenericAdapter.kt */
        @Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class DefaultImpls {
            public static <T> void onItemClick(OnItemClickListener<T> onItemClickListener, T t) {
            }

            public static <T> void onItemClickTwo(OnItemClickListener<T> onItemClickListener, T t) {
            }

            public static <T> void onSelectionChanged(OnItemClickListener<T> onItemClickListener, List<? extends T> item) {
                Intrinsics.checkNotNullParameter(item, "item");
            }

            public static <T> void onCall(OnItemClickListener<T> onItemClickListener, int visitId) {
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public GenericAdapter<T>.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        Intrinsics.checkNotNull(binding);
        return new MyViewHolder(this, binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(GenericAdapter<T>.MyViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final T t = this.itemsFiltered.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GenericAdapter.onBindViewHolder$lambda$0(GenericAdapter.this, t, view);
            }
        });
        holder.bind(t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(GenericAdapter this$0, Object $item, View it) {
        OnItemClickListener<T> onItemClickListener = this$0.itemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick($item);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemsFiltered.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return this.layout;
    }

    /* compiled from: GenericAdapter.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter$MyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Landroidx/databinding/ViewDataBinding;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter;Landroidx/databinding/ViewDataBinding;)V", "bind", "", "item", "(Ljava/lang/Object;)V", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class MyViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;
        final /* synthetic */ GenericAdapter<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(GenericAdapter this$0, ViewDataBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = this$0;
            this.binding = binding;
        }

        public final void bind(T item) {
            this.binding.setVariable(2, item);
            this.binding.setVariable(3, ((GenericAdapter) this.this$0).itemClickListener);
            this.binding.executePendingBindings();
        }
    }

    public final void removeItem(T item, int position) {
        this.itemsFiltered.remove(item);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, this.itemsFiltered.size());
    }

    public final void removeItem(T item) {
        this.itemsFiltered.remove(item);
        notifyDataSetChanged();
    }

    public final void addItem(T item) {
        this.itemsFiltered.add(item);
        notifyDataSetChanged();
    }

    public final void addList(List<? extends T> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.itemsFiltered.clear();
        this.itemsFiltered.addAll(items);
        notifyDataSetChanged();
    }

    public final void addListForInventory(List<? extends T> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.items.clear();
        this.items.addAll(items);
        this.itemsFiltered.clear();
        this.itemsFiltered.addAll(items);
        notifyDataSetChanged();
    }

    public final void addListForCustomer(List<? extends T> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.items.clear();
        this.items.addAll(items);
        this.itemsFiltered.clear();
        this.itemsFiltered.addAll(items);
        notifyDataSetChanged();
    }

    public final void filterProducts(String query) {
        ArrayList<T> arrayList;
        boolean z;
        Intrinsics.checkNotNullParameter(query, "query");
        if (this.items.isEmpty()) {
            return;
        }
        if (query.length() == 0) {
            arrayList = new ArrayList<>(this.items);
        } else {
            Iterable $this$filter$iv = this.items;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                if (element$iv$iv instanceof Products) {
                    String productName = ((Products) element$iv$iv).getProductName();
                    z = productName != null && StringsKt.contains((CharSequence) productName, (CharSequence) query, true);
                } else {
                    z = false;
                }
                if (z) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            arrayList = new ArrayList<>((List) destination$iv$iv);
        }
        setItemsFiltered(arrayList);
        notifyDataSetChanged();
    }

    public final void filterCustomers(String query) {
        ArrayList<T> arrayList;
        Object it;
        Intrinsics.checkNotNullParameter(query, "query");
        if (this.items.isEmpty()) {
            return;
        }
        if (query.length() == 0) {
            arrayList = new ArrayList<>(this.items);
        } else {
            Iterable $this$filter$iv = this.items;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                if (element$iv$iv instanceof CustomersData) {
                    String name = ((CustomersData) element$iv$iv).getName();
                    if (!(name != null && StringsKt.contains((CharSequence) name, (CharSequence) query, true))) {
                        String address = ((CustomersData) element$iv$iv).getAddress();
                        if (!(address != null && StringsKt.contains((CharSequence) address, (CharSequence) query, true))) {
                            it = null;
                        }
                    }
                    it = 1;
                } else {
                    it = null;
                }
                if (it != null) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            arrayList = new ArrayList<>((List) destination$iv$iv);
        }
        setItemsFiltered(arrayList);
        notifyDataSetChanged();
    }

    public final void addNewList(List<? extends T> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.itemsFiltered.addAll(items);
        notifyDataSetChanged();
    }

    public final void addSearchedList(List<? extends T> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        setItemsFiltered((ArrayList) items);
        notifyDataSetChanged();
    }

    public final void clearList() {
        this.itemsFiltered.clear();
        notifyDataSetChanged();
    }

    /* compiled from: GenericAdapter.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B7\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0003j\b\u0012\u0004\u0012\u00028\u0000`\u0004\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0003j\b\u0012\u0004\u0012\u00028\u0000`\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016J\b\u0010\u000e\u001a\u00020\u000bH\u0016J\u0018\u0010\u000f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016R\u001e\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0003j\b\u0012\u0004\u0012\u00028\u0000`\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0003j\b\u0012\u0004\u0012\u00028\u0000`\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter$LastDiffUtilCallback;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "newList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "oldList", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "areItemsTheSame", "", "oldItemPosition", "", "newItemPosition", "getOldListSize", "getNewListSize", "areContentsTheSame", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class LastDiffUtilCallback extends DiffUtil.Callback {
        private final ArrayList<T> newList;
        private final ArrayList<T> oldList;
        final /* synthetic */ GenericAdapter<T> this$0;

        public LastDiffUtilCallback(GenericAdapter this$0, ArrayList<T> newList, ArrayList<T> oldList) {
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

    public final void setList(List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.items.clear();
        this.items.addAll(list);
        notifyDataSetChanged();
    }
}
