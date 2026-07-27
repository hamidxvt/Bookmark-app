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
public abstract class ItemMessageSentBinding extends ViewDataBinding {

    @Bindable
    protected Messages mItem;
    public final TextView messageTime;
    public final TextView tvMessage;

    public abstract void setItem(Messages messages);

    protected ItemMessageSentBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView messageTime, TextView tvMessage) {
        super(_bindingComponent, _root, _localFieldCount);
        this.messageTime = messageTime;
        this.tvMessage = tvMessage;
    }

    public Messages getItem() {
        return this.mItem;
    }

    public static ItemMessageSentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMessageSentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemMessageSentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_message_sent, root, attachToRoot, component);
    }

    public static ItemMessageSentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMessageSentBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemMessageSentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_message_sent, null, false, component);
    }

    public static ItemMessageSentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMessageSentBinding bind(View view, Object component) {
        return (ItemMessageSentBinding) bind(component, view, R.layout.item_message_sent);
    }
}
