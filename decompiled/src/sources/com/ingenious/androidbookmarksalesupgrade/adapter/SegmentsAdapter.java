package com.ingenious.androidbookmarksalesupgrade.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.ingenious.androidbookmarksalesupgrade.adapter.SegmentsAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.NewItemSegmentBinding;
import com.ingenious.androidbookmarksalesupgrade.model.SegmentModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SegmentsAdapter.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0016B-\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u001c\u0010\u000b\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001c\u0010\u0010\u001a\u00020\b2\n\u0010\u0011\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0016J\u0014\u0010\u0014\u001a\u00020\b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/SegmentsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/SegmentsAdapter$SegmentViewHolder;", "segments", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/SegmentModel;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lkotlin/Function1;", "", "<init>", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "onBindViewHolder", "holder", "position", "getItemCount", "updateList", "newList", "SegmentViewHolder", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class SegmentsAdapter extends RecyclerView.Adapter<SegmentViewHolder> {
    private final Function1<SegmentModel, Unit> listener;
    private List<SegmentModel> segments;

    public /* synthetic */ SegmentsAdapter(List list, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? null : function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SegmentsAdapter(List<SegmentModel> segments, Function1<? super SegmentModel, Unit> function1) {
        Intrinsics.checkNotNullParameter(segments, "segments");
        this.segments = segments;
        this.listener = function1;
    }

    /* compiled from: SegmentsAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/SegmentsAdapter$SegmentViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/NewItemSegmentBinding;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/SegmentsAdapter;Lcom/ingenious/androidbookmarksalesupgrade/databinding/NewItemSegmentBinding;)V", "getBinding", "()Lcom/ingenious/androidbookmarksalesupgrade/databinding/NewItemSegmentBinding;", "bind", "", "item", "Lcom/ingenious/androidbookmarksalesupgrade/model/SegmentModel;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class SegmentViewHolder extends RecyclerView.ViewHolder {
        private final NewItemSegmentBinding binding;
        final /* synthetic */ SegmentsAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SegmentViewHolder(SegmentsAdapter this$0, NewItemSegmentBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = this$0;
            this.binding = binding;
        }

        public final NewItemSegmentBinding getBinding() {
            return this.binding;
        }

        public final void bind(final SegmentModel item) {
            Intrinsics.checkNotNullParameter(item, "item");
            TextView textView = this.binding.segment;
            String segment = item.getSegment();
            if (segment == null) {
                segment = "N/A";
            }
            textView.setText(segment);
            this.binding.booksCountTv.setText(String.valueOf(item.getTotalBooksCount()));
            CardView root = this.binding.getRoot();
            final SegmentsAdapter segmentsAdapter = this.this$0;
            root.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.SegmentsAdapter$SegmentViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SegmentsAdapter.SegmentViewHolder.bind$lambda$0(SegmentsAdapter.this, item, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$0(SegmentsAdapter this$0, SegmentModel $item, View it) {
            Function1 function1 = this$0.listener;
            if (function1 != null) {
                function1.invoke($item);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public SegmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        NewItemSegmentBinding binding = NewItemSegmentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "inflate(...)");
        return new SegmentViewHolder(this, binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(SegmentViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.segments.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.segments.size();
    }

    public final void updateList(List<SegmentModel> newList) {
        Intrinsics.checkNotNullParameter(newList, "newList");
        this.segments = newList;
        notifyDataSetChanged();
    }
}
