package com.ingenious.androidbookmarksalesupgrade.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemMessageReceivedBinding;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemMessageSentBinding;
import com.ingenious.androidbookmarksalesupgrade.model.response.Messages;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ChatAdapter.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\u0018\u0000 #2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003#$%B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013H\u0016J\u0010\u0010\u0016\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\b\u0010\u0019\u001a\u00020\u0013H\u0016J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0007J\u0014\u0010\u001d\u001a\u00020\u001b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u001fJ\u0014\u0010 \u001a\u00020\u001b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u001fJ\u0018\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0013H\u0016R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u000e¢\u0006\u0002\n\u0000RD\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b2\u0016\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006&"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/ChatAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "<init>", "()V", "messageList", "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Messages;", "Lkotlin/collections/ArrayList;", "value", "itemsFiltered", "getItemsFiltered", "()Ljava/util/ArrayList;", "setItemsFiltered", "(Ljava/util/ArrayList;)V", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "getItemViewType", "position", "getItemPosition", Constant.VISIT_ID, "", "getItemCount", "addNewMessage", "", "message", "addNewMessageList", "messageListNew", "", "updateData", "onBindViewHolder", "holder", "Companion", "ReceiveViewHolder", "SentViewHolder", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int CHAT_IN = 1;
    private static final int CHAT_OUT = 2;
    private ArrayList<Messages> messageList = new ArrayList<>();
    private ArrayList<Messages> itemsFiltered = new ArrayList<>();

    public final ArrayList<Messages> getItemsFiltered() {
        return this.itemsFiltered;
    }

    public final void setItemsFiltered(ArrayList<Messages> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.itemsFiltered = value;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 1:
                ItemMessageReceivedBinding binding = ItemMessageReceivedBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                Intrinsics.checkNotNullExpressionValue(binding, "inflate(...)");
                RecyclerView.ViewHolder holder2 = new ReceiveViewHolder(this, binding);
                holder = holder2;
                break;
            case 2:
                ItemMessageSentBinding binding2 = ItemMessageSentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                Intrinsics.checkNotNullExpressionValue(binding2, "inflate(...)");
                RecyclerView.ViewHolder holder3 = new SentViewHolder(this, binding2);
                holder = holder3;
                break;
        }
        Intrinsics.checkNotNull(holder);
        return holder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        boolean isMe = StringsKt.equals$default(this.messageList.get(position).getFrom(), "booker", false, 2, null);
        return isMe ? 2 : 1;
    }

    public final int getItemPosition(String id) {
        int size = this.messageList.size();
        for (int position = 0; position < size; position++) {
            if (StringsKt.equals$default(this.messageList.get(position).getFrom(), id, false, 2, null)) {
                return position;
            }
        }
        return 0;
    }

    /* compiled from: ChatAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/ChatAdapter$ReceiveViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemMessageReceivedBinding;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/ChatAdapter;Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemMessageReceivedBinding;)V", "getBinding", "()Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemMessageReceivedBinding;", "bind", "", "item", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Messages;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class ReceiveViewHolder extends RecyclerView.ViewHolder {
        private final ItemMessageReceivedBinding binding;
        final /* synthetic */ ChatAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ReceiveViewHolder(ChatAdapter this$0, ItemMessageReceivedBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = this$0;
            this.binding = binding;
        }

        public final ItemMessageReceivedBinding getBinding() {
            return this.binding;
        }

        public final void bind(Messages item) {
            Intrinsics.checkNotNullParameter(item, "item");
            this.binding.setItem(item);
            this.binding.executePendingBindings();
        }
    }

    /* compiled from: ChatAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/ChatAdapter$SentViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemMessageSentBinding;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/ChatAdapter;Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemMessageSentBinding;)V", "getBinding", "()Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemMessageSentBinding;", "bind", "", "item", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Messages;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class SentViewHolder extends RecyclerView.ViewHolder {
        private final ItemMessageSentBinding binding;
        final /* synthetic */ ChatAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SentViewHolder(ChatAdapter this$0, ItemMessageSentBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = this$0;
            this.binding = binding;
        }

        public final ItemMessageSentBinding getBinding() {
            return this.binding;
        }

        public final void bind(Messages item) {
            Intrinsics.checkNotNullParameter(item, "item");
            this.binding.setItem(item);
            this.binding.executePendingBindings();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.messageList.size();
    }

    public final void addNewMessage(Messages message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.messageList.add(message);
        notifyDataSetChanged();
    }

    public final void addNewMessageList(List<Messages> messageListNew) {
        Intrinsics.checkNotNullParameter(messageListNew, "messageListNew");
        this.messageList.addAll(0, messageListNew);
        notifyDataSetChanged();
    }

    public final void updateData(List<Messages> messageListNew) {
        Intrinsics.checkNotNullParameter(messageListNew, "messageListNew");
        this.messageList = (ArrayList) messageListNew;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        switch (holder.getItemViewType()) {
            case 1:
                Messages messages = this.messageList.get(position);
                Intrinsics.checkNotNullExpressionValue(messages, "get(...)");
                ((ReceiveViewHolder) holder).bind(messages);
                break;
            case 2:
                Messages messages2 = this.messageList.get(position);
                Intrinsics.checkNotNullExpressionValue(messages2, "get(...)");
                ((SentViewHolder) holder).bind(messages2);
                break;
        }
    }
}
