package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.model.response.Messages;

/* loaded from: classes13.dex */
public abstract class ItemMessageReceivedBinding extends ViewDataBinding {

    @Bindable
    protected Messages mItem;
    public final TextView messageTime;
    public final TextView tvMessage;

    public abstract void setItem(Messages messages);

    protected ItemMessageReceivedBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView messageTime, TextView tvMessage) {
        super(_bindingComponent, _root, _localFieldCount);
        this.messageTime = messageTime;
        this.tvMessage = tvMessage;
    }

    public Messages getItem() {
        return this.mItem;
    }

    public static ItemMessageReceivedBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMessageReceivedBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemMessageReceivedBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_message_received, root, attachToRoot, component);
    }

    public static ItemMessageReceivedBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMessageReceivedBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemMessageReceivedBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_message_received, null, false, component);
    }

    public static ItemMessageReceivedBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMessageReceivedBinding bind(View view, Object component) {
        return (ItemMessageReceivedBinding) bind(component, view, R.layout.item_message_received);
    }
}
