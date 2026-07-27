package com.pusher.client.channel.impl;

import com.pusher.client.channel.Channel;
import com.pusher.client.channel.ChannelEventListener;
import com.pusher.client.channel.ChannelState;
import com.pusher.client.channel.PusherEvent;

/* loaded from: classes17.dex */
public interface InternalChannel extends Channel, Comparable<InternalChannel> {
    Integer getCount();

    ChannelEventListener getEventListener();

    void handleEvent(PusherEvent pusherEvent);

    void setEventListener(ChannelEventListener channelEventListener);

    String toSubscribeMessage();

    String toUnsubscribeMessage();

    void updateState(ChannelState channelState);
}
