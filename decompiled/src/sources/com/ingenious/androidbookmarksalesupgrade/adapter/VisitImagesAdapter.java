package com.ingenious.androidbookmarksalesupgrade.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.common.internal.ImagesContract;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemVisitImageBinding;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VisitImagesAdapter.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0012B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\b\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\fH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/VisitImagesAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/VisitImagesAdapter$ImageViewHolder;", "images", "", "", "<init>", "(Ljava/util/List;)V", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "onBindViewHolder", "", "holder", "position", "getItemCount", "ImageViewHolder", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class VisitImagesAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private final List<String> images;

    public VisitImagesAdapter(List<String> images) {
        Intrinsics.checkNotNullParameter(images, "images");
        this.images = images;
    }

    /* compiled from: VisitImagesAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/VisitImagesAdapter$ImageViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemVisitImageBinding;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/VisitImagesAdapter;Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemVisitImageBinding;)V", "getBinding", "()Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemVisitImageBinding;", "bind", "", ImagesContract.URL, "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class ImageViewHolder extends RecyclerView.ViewHolder {
        private final ItemVisitImageBinding binding;
        final /* synthetic */ VisitImagesAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ImageViewHolder(VisitImagesAdapter this$0, ItemVisitImageBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = this$0;
            this.binding = binding;
        }

        public final ItemVisitImageBinding getBinding() {
            return this.binding;
        }

        public final void bind(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            Glide.with(this.binding.imgVisit.getContext()).load(url).placeholder(R.drawable.logo).error(R.drawable.logo).apply((BaseRequestOptions<?>) new RequestOptions().transform(new RoundedCorners(15))).into(this.binding.imgVisit);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemVisitImageBinding binding = ItemVisitImageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "inflate(...)");
        return new ImageViewHolder(this, binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.images.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: getItemCount */
    public int getTabCount() {
        return this.images.size();
    }
}
