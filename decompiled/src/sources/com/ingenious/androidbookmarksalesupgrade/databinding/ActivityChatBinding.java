package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.MessageListResponse;

/* loaded from: classes13.dex */
public abstract class ActivityChatBinding extends ViewDataBinding {
    public final MaterialCardView call;
    public final ImageView cancel;
    public final EditText etMsg;
    public final ImageView imgMsgSend;
    public final LinearLayout layoutFooter;
    public final LinearLayout layoutHeader;

    @Bindable
    protected String mHeaderName;

    @Bindable
    protected MessageListResponse mItem;

    @Bindable
    protected GenericListeners mListener;
    public final RelativeLayout main;
    public final ImageView mic;
    public final RecyclerView recyclerView;
    public final TextView tvName;

    public abstract void setHeaderName(String str);

    public abstract void setItem(MessageListResponse messageListResponse);

    public abstract void setListener(GenericListeners genericListeners);

    protected ActivityChatBinding(Object _bindingComponent, View _root, int _localFieldCount, MaterialCardView call, ImageView cancel, EditText etMsg, ImageView imgMsgSend, LinearLayout layoutFooter, LinearLayout layoutHeader, RelativeLayout main, ImageView mic, RecyclerView recyclerView, TextView tvName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.call = call;
        this.cancel = cancel;
        this.etMsg = etMsg;
        this.imgMsgSend = imgMsgSend;
        this.layoutFooter = layoutFooter;
        this.layoutHeader = layoutHeader;
        this.main = main;
        this.mic = mic;
        this.recyclerView = recyclerView;
        this.tvName = tvName;
    }

    public String getHeaderName() {
        return this.mHeaderName;
    }

    public MessageListResponse getItem() {
        return this.mItem;
    }

    public GenericListeners getListener() {
        return this.mListener;
    }

    public static ActivityChatBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChatBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityChatBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_chat, root, attachToRoot, component);
    }

    public static ActivityChatBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChatBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityChatBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_chat, null, false, component);
    }

    public static ActivityChatBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChatBinding bind(View view, Object component) {
        return (ActivityChatBinding) bind(component, view, R.layout.activity_chat);
    }
}
