package com.ingenious.androidbookmarksalesupgrade.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemNotificationBinding;
import com.ingenious.androidbookmarksalesupgrade.model.NotificationData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationAdapter.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0017B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\b\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\fH\u0016J\u0014\u0010\u0012\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/NotificationAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/NotificationAdapter$NotificationViewHolder;", "notificationList", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/NotificationData;", "<init>", "(Ljava/util/List;)V", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "onBindViewHolder", "", "holder", "position", "getItemCount", "updateList", "newList", "getTimeAgo", "", "dateString", "NotificationViewHolder", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class NotificationAdapter extends RecyclerView.Adapter<NotificationViewHolder> {
    private List<NotificationData> notificationList;

    public NotificationAdapter(List<NotificationData> notificationList) {
        Intrinsics.checkNotNullParameter(notificationList, "notificationList");
        this.notificationList = notificationList;
    }

    /* compiled from: NotificationAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/NotificationAdapter$NotificationViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemNotificationBinding;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/NotificationAdapter;Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemNotificationBinding;)V", "getBinding", "()Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemNotificationBinding;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class NotificationViewHolder extends RecyclerView.ViewHolder {
        private final ItemNotificationBinding binding;
        final /* synthetic */ NotificationAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NotificationViewHolder(NotificationAdapter this$0, ItemNotificationBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = this$0;
            this.binding = binding;
        }

        public final ItemNotificationBinding getBinding() {
            return this.binding;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemNotificationBinding binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "inflate(...)");
        return new NotificationViewHolder(this, binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        NotificationData item = this.notificationList.get(position);
        holder.getBinding().tvTitle.setText(item.getTitle());
        holder.getBinding().tvMessage.setText(item.getMessage());
        holder.getBinding().tvDate.setText(getTimeAgo(item.getCreated_at()));
        Log.i("TAG", "onBindViewHolder: " + item.getCreated_at());
        Log.i("TAG", "onBindViewHolder: " + getTimeAgo(item.getCreated_at()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.notificationList.size();
    }

    public final void updateList(List<NotificationData> newList) {
        Intrinsics.checkNotNullParameter(newList, "newList");
        this.notificationList = newList;
        notifyDataSetChanged();
    }

    public final String getTimeAgo(String dateString) {
        Date past;
        String str = "";
        Intrinsics.checkNotNullParameter(dateString, "dateString");
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault());
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            past = format.parse(dateString);
        } catch (Exception e) {
        }
        if (past == null) {
            return "";
        }
        Date now = new Date();
        long diffMillis = now.getTime() - past.getTime();
        long hours = TimeUnit.MILLISECONDS.toHours(diffMillis);
        long days = TimeUnit.MILLISECONDS.toDays(diffMillis);
        if (hours < 24) {
            str = hours + " hr";
        } else {
            str = days == 1 ? "1 day" : days + " days";
        }
        return str;
    }
}
