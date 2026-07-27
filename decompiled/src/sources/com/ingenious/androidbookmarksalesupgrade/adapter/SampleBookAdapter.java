package com.ingenious.androidbookmarksalesupgrade.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.MonthModel;
import com.ingenious.androidbookmarksalesupgrade.model.SampleBookModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SampleBookAdapter.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u0017\u0018\u0019B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0014\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\u0018\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\b\u0010\u0014\u001a\u00020\u000eH\u0016J\u0018\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000eH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/SampleBookAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "<init>", "()V", FirebaseAnalytics.Param.ITEMS, "", "", "setData", "", "monthList", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/MonthModel;", "getItemViewType", "", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "getItemCount", "onBindViewHolder", "holder", "Companion", "HeaderViewHolder", "ProductViewHolder", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class SampleBookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private final List<Object> items = new ArrayList();

    public final void setData(List<MonthModel> monthList) {
        Intrinsics.checkNotNullParameter(monthList, "monthList");
        this.items.clear();
        List<MonthModel> $this$forEach$iv = monthList;
        for (Object element$iv : $this$forEach$iv) {
            MonthModel month = (MonthModel) element$iv;
            this.items.add(month.getMonth());
            this.items.addAll(month.getProducts());
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return !(this.items.get(position) instanceof String) ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_month_header, parent, false);
            Intrinsics.checkNotNull(view);
            return new HeaderViewHolder(this, view);
        }
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_item_book_card, parent, false);
        Intrinsics.checkNotNull(view2);
        return new ProductViewHolder(this, view2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: getItemCount */
    public int getTabCount() {
        return this.items.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof HeaderViewHolder) {
            Object obj = this.items.get(position);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
            ((HeaderViewHolder) holder).bind((String) obj);
        } else if (holder instanceof ProductViewHolder) {
            Object obj2 = this.items.get(position);
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type com.ingenious.androidbookmarksalesupgrade.model.SampleBookModel");
            ((ProductViewHolder) holder).bind((SampleBookModel) obj2);
        }
    }

    /* compiled from: SampleBookAdapter.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/SampleBookAdapter$HeaderViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/SampleBookAdapter;Landroid/view/View;)V", "tvMonthHeader", "Landroid/widget/TextView;", "bind", "", "month", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class HeaderViewHolder extends RecyclerView.ViewHolder {
        final /* synthetic */ SampleBookAdapter this$0;
        private final TextView tvMonthHeader;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HeaderViewHolder(SampleBookAdapter this$0, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.this$0 = this$0;
            View findViewById = view.findViewById(R.id.tvMonthHeader);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.tvMonthHeader = (TextView) findViewById;
        }

        public final void bind(String month) {
            Intrinsics.checkNotNullParameter(month, "month");
            this.tvMonthHeader.setText(month);
        }
    }

    /* compiled from: SampleBookAdapter.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/SampleBookAdapter$ProductViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/SampleBookAdapter;Landroid/view/View;)V", "imgBook", "Landroid/widget/ImageView;", "tvTitle", "Landroid/widget/TextView;", "tvSubtitle", "tvPrice", "bind", "", "book", "Lcom/ingenious/androidbookmarksalesupgrade/model/SampleBookModel;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class ProductViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgBook;
        final /* synthetic */ SampleBookAdapter this$0;
        private final TextView tvPrice;
        private final TextView tvSubtitle;
        private final TextView tvTitle;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ProductViewHolder(SampleBookAdapter this$0, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.this$0 = this$0;
            View findViewById = view.findViewById(R.id.imgBook);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.imgBook = (ImageView) findViewById;
            View findViewById2 = view.findViewById(R.id.tvTitle);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.tvTitle = (TextView) findViewById2;
            View findViewById3 = view.findViewById(R.id.tvSubtitle);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            this.tvSubtitle = (TextView) findViewById3;
            View findViewById4 = view.findViewById(R.id.tvPrice);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
            this.tvPrice = (TextView) findViewById4;
        }

        public final void bind(SampleBookModel book) {
            Intrinsics.checkNotNullParameter(book, "book");
            this.tvTitle.setText(book.getName());
            this.tvSubtitle.setText(book.getGradeTitle() + " • " + book.getSubjectName());
            this.tvPrice.setText(book.getTotalQuantity());
            Glide.with(this.itemView.getContext()).load(book.getImage()).placeholder(R.drawable.bg_image_rounded).into(this.imgBook);
        }
    }
}
