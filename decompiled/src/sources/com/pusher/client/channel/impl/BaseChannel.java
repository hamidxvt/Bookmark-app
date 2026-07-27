package com.pusher.client.channel.impl;

import com.google.gson.Gson;
import com.pusher.client.channel.ChannelEventListener;
import com.pusher.client.channel.ChannelState;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.channel.impl.message.SubscribeMessage;
import com.pusher.client.channel.impl.message.SubscriptionCountData;
import com.pusher.client.channel.impl.message.UnsubscribeMessage;
import com.pusher.client.util.Factory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes17.dex */
public abstract class BaseChannel implements InternalChannel {
    private static final String INTERNAL_EVENT_PREFIX = "pusher_internal:";
    protected static final String PUBLIC_SUBSCRIPTION_COUNT_EVENT = "pusher:subscription_count";
    protected static final String SUBSCRIPTION_COUNT_EVENT = "pusher_internal:subscription_count";
    protected static final String SUBSCRIPTION_SUCCESS_EVENT = "pusher_internal:subscription_succeeded";
    private ChannelEventListener eventListener;
    private final Factory factory;
    private Integer subscriptionCount;
    protected final Gson GSON = new Gson();
    private final Set<SubscriptionEventListener> globalListeners = new HashSet();
    private final Map<String, Set<SubscriptionEventListener>> eventNameToListenerMap = new HashMap();
    protected volatile ChannelState state = ChannelState.INITIAL;
    private final Object lock = new Object();

    @Override // com.pusher.client.channel.Channel
    public abstract String getName();

    public BaseChannel(Factory factory) {
        this.factory = factory;
    }

    @Override // com.pusher.client.channel.impl.InternalChannel
    public Integer getCount() {
        return this.subscriptionCount;
    }

    @Override // com.pusher.client.channel.Channel
    public void bind(String eventName, SubscriptionEventListener listener) {
        validateArguments(eventName, listener);
        synchronized (this.lock) {
            Set<SubscriptionEventListener> listeners = this.eventNameToListenerMap.get(eventName);
            if (listeners == null) {
                listeners = new HashSet();
                this.eventNameToListenerMap.put(eventName, listeners);
            }
            listeners.add(listener);
        }
    }

    @Override // com.pusher.client.channel.Channel
    public void bindGlobal(SubscriptionEventListener listener) {
        validateArguments("", listener);
        synchronized (this.lock) {
            this.globalListeners.add(listener);
        }
    }

    @Override // com.pusher.client.channel.Channel
    public void unbind(String eventName, SubscriptionEventListener listener) {
        validateArguments(eventName, listener);
        synchronized (this.lock) {
            Set<SubscriptionEventListener> listeners = this.eventNameToListenerMap.get(eventName);
            if (listeners != null) {
                listeners.remove(listener);
                if (listeners.isEmpty()) {
                    this.eventNameToListenerMap.remove(eventName);
                }
            }
        }
    }

    @Override // com.pusher.client.channel.Channel
    public void unbindGlobal(SubscriptionEventListener listener) {
        validateArguments("", listener);
        synchronized (this.lock) {
            if (this.globalListeners != null) {
                this.globalListeners.remove(listener);
            }
        }
    }

    @Override // com.pusher.client.channel.Channel
    public boolean isSubscribed() {
        return this.state == ChannelState.SUBSCRIBED;
    }

    @Override // com.pusher.client.channel.impl.InternalChannel
    public String toSubscribeMessage() {
        return this.GSON.toJson(new SubscribeMessage(getName()));
    }

    @Override // com.pusher.client.channel.impl.InternalChannel
    public String toUnsubscribeMessage() {
        return this.GSON.toJson(new UnsubscribeMessage(getName()));
    }

    public void emit(final PusherEvent pusherEvent) {
        Set<SubscriptionEventListener> listeners = getInterestedListeners(pusherEvent.getEventName());
        if (listeners != null) {
            for (final SubscriptionEventListener listener : listeners) {
                this.factory.queueOnEventThread(new Runnable() { // from class: com.pusher.client.channel.impl.BaseChannel$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        SubscriptionEventListener.this.onEvent(pusherEvent);
                    }
                });
            }
        }
    }

    @Override // com.pusher.client.channel.impl.InternalChannel
    public void handleEvent(PusherEvent event) {
        if (event.getEventName().equals(SUBSCRIPTION_SUCCESS_EVENT)) {
            updateState(ChannelState.SUBSCRIBED);
        } else if (event.getEventName().equals(SUBSCRIPTION_COUNT_EVENT)) {
            handleSubscriptionCountEvent(event);
        } else {
            emit(event);
        }
    }

    @Override // com.pusher.client.channel.impl.InternalChannel
    public void updateState(ChannelState state) {
        this.state = state;
        if (state == ChannelState.SUBSCRIBED && this.eventListener != null) {
            this.factory.queueOnEventThread(new Runnable() { // from class: com.pusher.client.channel.impl.BaseChannel$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    BaseChannel.this.m553lambda$updateState$1$compusherclientchannelimplBaseChannel();
                }
            });
        }
    }

    /* renamed from: lambda$updateState$1$com-pusher-client-channel-impl-BaseChannel, reason: not valid java name */
    /* synthetic */ void m553lambda$updateState$1$compusherclientchannelimplBaseChannel() {
        this.eventListener.onSubscriptionSucceeded(getName());
    }

    @Override // com.pusher.client.channel.impl.InternalChannel
    public void setEventListener(ChannelEventListener listener) {
        this.eventListener = listener;
    }

    @Override // com.pusher.client.channel.impl.InternalChannel
    public ChannelEventListener getEventListener() {
        return this.eventListener;
    }

    @Override // java.lang.Comparable
    public int compareTo(InternalChannel other) {
        return getName().compareTo(other.getName());
    }

    public String toString() {
        return String.format("[Channel: name=%s]", getName());
    }

    private void validateArguments(String eventName, SubscriptionEventListener listener) {
        if (eventName == null) {
            throw new IllegalArgumentException("Cannot bind or unbind to channel " + getName() + " with a null event name");
        }
        if (listener == null) {
            throw new IllegalArgumentException("Cannot bind or unbind to channel " + getName() + " with a null listener");
        }
        if (eventName.startsWith(INTERNAL_EVENT_PREFIX)) {
            throw new IllegalArgumentException("Cannot bind or unbind channel " + getName() + " with an internal event name such as " + eventName);
        }
    }

    private void handleSubscriptionCountEvent(PusherEvent event) {
        SubscriptionCountData subscriptionCountMessage = (SubscriptionCountData) this.GSON.fromJson(event.getData(), SubscriptionCountData.class);
        this.subscriptionCount = Integer.valueOf(subscriptionCountMessage.getCount());
        PusherEvent publicEvent = new PusherEvent(PUBLIC_SUBSCRIPTION_COUNT_EVENT, event.getChannelName(), event.getUserId(), event.getData());
        emit(publicEvent);
    }

    protected Set<SubscriptionEventListener> getInterestedListeners(String event) {
        synchronized (this.lock) {
            Set<SubscriptionEventListener> listeners = new HashSet<>();
            Set<SubscriptionEventListener> sharedListeners = this.eventNameToListenerMap.get(event);
            if (sharedListeners != null) {
                listeners.addAll(sharedListeners);
            }
            if (!this.globalListeners.isEmpty()) {
                listeners.addAll(this.globalListeners);
            }
            if (listeners.isEmpty()) {
                return null;
            }
            return listeners;
        }
    }
}
