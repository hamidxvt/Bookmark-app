package com.ingenious.androidbookmarksalesupgrade.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.FaqModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FaqAdapter.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0012B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\b\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\fH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/FaqAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/FaqAdapter$FaqViewHolder;", "list", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/FaqModel;", "<init>", "(Ljava/util/List;)V", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "onBindViewHolder", "", "holder", "position", "getItemCount", "FaqViewHolder", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class FaqAdapter extends RecyclerView.Adapter<FaqViewHolder> {
    private final List<FaqModel> list;

    public FaqAdapter(List<FaqModel> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.list = list;
    }

    /* compiled from: FaqAdapter.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u001b\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u001b\u0010\f\u001a\n \b*\u0004\u0018\u00010\u00070\u0007¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\nR\u001b\u0010\u000e\u001a\n \b*\u0004\u0018\u00010\u000f0\u000f¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0013\u001a\n \b*\u0004\u0018\u00010\u00030\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/FaqAdapter$FaqViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/FaqAdapter;Landroid/view/View;)V", "tvQuestion", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "getTvQuestion", "()Landroid/widget/TextView;", "Landroid/widget/TextView;", "tvAnswer", "getTvAnswer", "ivToggle", "Landroid/widget/ImageView;", "getIvToggle", "()Landroid/widget/ImageView;", "Landroid/widget/ImageView;", "layoutQuestion", "getLayoutQuestion", "()Landroid/view/View;", "Landroid/view/View;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class FaqViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivToggle;
        private final View layoutQuestion;
        final /* synthetic */ FaqAdapter this$0;
        private final TextView tvAnswer;
        private final TextView tvQuestion;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FaqViewHolder(FaqAdapter this$0, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = this$0;
            this.tvQuestion = (TextView) itemView.findViewById(R.id.tvQuestion);
            this.tvAnswer = (TextView) itemView.findViewById(R.id.tvAnswer);
            this.ivToggle = (ImageView) itemView.findViewById(R.id.ivToggle);
            this.layoutQuestion = itemView.findViewById(R.id.layoutQuestion);
        }

        public final TextView getTvQuestion() {
            return this.tvQuestion;
        }

        public final TextView getTvAnswer() {
            return this.tvAnswer;
        }

        public final ImageView getIvToggle() {
            return this.ivToggle;
        }

        public final View getLayoutQuestion() {
            return this.layoutQuestion;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public FaqViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faq, parent, false);
        Intrinsics.checkNotNull(view);
        return new FaqViewHolder(this, view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(FaqViewHolder holder, final int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final FaqModel item = this.list.get(position);
        holder.getTvQuestion().setText(item.getQuestion());
        holder.getTvAnswer().setText(item.getAnswer());
        holder.getTvAnswer().setVisibility(item.isExpanded() ? 0 : 8);
        holder.getIvToggle().setImageResource(item.isExpanded() ? R.drawable.quick_up : R.drawable.quick_down);
        holder.getLayoutQuestion().setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.FaqAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FaqAdapter.onBindViewHolder$lambda$0(FaqModel.this, this, position, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(FaqModel $item, FaqAdapter this$0, int $position, View it) {
        $item.setExpanded(!$item.isExpanded());
        this$0.notifyItemChanged($position);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }
}
