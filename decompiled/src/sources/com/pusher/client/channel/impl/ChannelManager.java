package com.pusher.client.channel.impl;

import com.pusher.client.AuthorizationFailureException;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.ChannelEventListener;
import com.pusher.client.channel.ChannelState;
import com.pusher.client.channel.PresenceChannel;
import com.pusher.client.channel.PrivateChannel;
import com.pusher.client.channel.PrivateChannelEventListener;
import com.pusher.client.channel.PrivateEncryptedChannel;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.pusher.client.connection.impl.InternalConnection;
import com.pusher.client.util.Factory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes17.dex */
public class ChannelManager implements ConnectionEventListener {
    private final Map<String, InternalChannel> channelNameToChannelMap = new ConcurrentHashMap();
    private InternalConnection connection;
    private final Factory factory;

    public ChannelManager(Factory factory) {
        this.factory = factory;
    }

    public Channel getChannel(String channelName) {
        if (channelName.startsWith("private-")) {
            throw new IllegalArgumentException("Please use the getPrivateChannel method");
        }
        if (channelName.startsWith("presence-")) {
            throw new IllegalArgumentException("Please use the getPresenceChannel method");
        }
        return findChannelInChannelMap(channelName);
    }

    public PrivateChannel getPrivateChannel(String channelName) throws IllegalArgumentException {
        if (!channelName.startsWith("private-")) {
            throw new IllegalArgumentException("Private channels must begin with 'private-'");
        }
        return (PrivateChannel) findChannelInChannelMap(channelName);
    }

    public PrivateEncryptedChannel getPrivateEncryptedChannel(String channelName) throws IllegalArgumentException {
        if (!channelName.startsWith("private-encrypted-")) {
            throw new IllegalArgumentException("Encrypted private channels must begin with 'private-encrypted-'");
        }
        return (PrivateEncryptedChannel) findChannelInChannelMap(channelName);
    }

    public PresenceChannel getPresenceChannel(String channelName) throws IllegalArgumentException {
        if (!channelName.startsWith("presence-")) {
            throw new IllegalArgumentException("Presence channels must begin with 'presence-'");
        }
        return (PresenceChannel) findChannelInChannelMap(channelName);
    }

    private InternalChannel findChannelInChannelMap(String channelName) {
        return this.channelNameToChannelMap.get(channelName);
    }

    public void setConnection(InternalConnection connection) {
        if (connection == null) {
            throw new IllegalArgumentException("Cannot construct ChannelManager with a null connection");
        }
        if (this.connection != null) {
            this.connection.unbind(ConnectionState.CONNECTED, this);
        }
        this.connection = connection;
        connection.bind(ConnectionState.CONNECTED, this);
    }

    public void subscribeTo(InternalChannel channel, ChannelEventListener listener, String... eventNames) {
        validateArgumentsAndBindEvents(channel, listener, eventNames);
        this.channelNameToChannelMap.put(channel.getName(), channel);
        sendOrQueueSubscribeMessage(channel);
    }

    public void unsubscribeFrom(String channelName) {
        if (channelName == null) {
            throw new IllegalArgumentException("Cannot unsubscribe from null channel");
        }
        InternalChannel channel = this.channelNameToChannelMap.remove(channelName);
        if (channel != null && this.connection.getState() == ConnectionState.CONNECTED) {
            sendUnsubscribeMessage(channel);
        }
    }

    public void handleEvent(PusherEvent event) {
        InternalChannel channel;
        String channelName = event.getChannelName();
        if (channelName != null && (channel = this.channelNameToChannelMap.get(channelName)) != null) {
            channel.handleEvent(event);
        }
    }

    @Override // com.pusher.client.connection.ConnectionEventListener
    public void onConnectionStateChange(ConnectionStateChange change) {
        if (change.getCurrentState() == ConnectionState.CONNECTED) {
            for (InternalChannel channel : this.channelNameToChannelMap.values()) {
                sendOrQueueSubscribeMessage(channel);
            }
        }
    }

    @Override // com.pusher.client.connection.ConnectionEventListener
    public void onError(String message, String code, Exception e) {
    }

    private void sendOrQueueSubscribeMessage(final InternalChannel channel) {
        this.factory.queueOnEventThread(new Runnable() { // from class: com.pusher.client.channel.impl.ChannelManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ChannelManager.this.m554x3513113(channel);
            }
        });
    }

    /* renamed from: lambda$sendOrQueueSubscribeMessage$0$com-pusher-client-channel-impl-ChannelManager, reason: not valid java name */
    /* synthetic */ void m554x3513113(InternalChannel channel) {
        if (this.connection.getState() == ConnectionState.CONNECTED) {
            try {
                String message = channel.toSubscribeMessage();
                this.connection.sendMessage(message);
                channel.updateState(ChannelState.SUBSCRIBE_SENT);
            } catch (AuthorizationFailureException e) {
                handleAuthenticationFailure(channel, e);
            }
        }
    }

    private void sendUnsubscribeMessage(final InternalChannel channel) {
        this.factory.queueOnEventThread(new Runnable() { // from class: com.pusher.client.channel.impl.ChannelManager$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                ChannelManager.this.m555xb4e21409(channel);
            }
        });
    }

    /* renamed from: lambda$sendUnsubscribeMessage$1$com-pusher-client-channel-impl-ChannelManager, reason: not valid java name */
    /* synthetic */ void m555xb4e21409(InternalChannel channel) {
        this.connection.sendMessage(channel.toUnsubscribeMessage());
        channel.updateState(ChannelState.UNSUBSCRIBED);
    }

    private void handleAuthenticationFailure(final InternalChannel channel, final Exception e) {
        this.channelNameToChannelMap.remove(channel.getName());
        channel.updateState(ChannelState.FAILED);
        if (channel.getEventListener() != null) {
            this.factory.queueOnEventThread(new Runnable() { // from class: com.pusher.client.channel.impl.ChannelManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ChannelManager.lambda$handleAuthenticationFailure$2(InternalChannel.this, e);
                }
            });
        }
    }

    static /* synthetic */ void lambda$handleAuthenticationFailure$2(InternalChannel channel, Exception e) {
        ChannelEventListener eventListener = channel.getEventListener();
        PrivateChannelEventListener privateChannelListener = (PrivateChannelEventListener) eventListener;
        privateChannelListener.onAuthenticationFailure(e.getMessage(), e);
    }

    private void validateArgumentsAndBindEvents(InternalChannel channel, ChannelEventListener listener, String... eventNames) {
        if (channel == null) {
            throw new IllegalArgumentException("Cannot subscribe to a null channel");
        }
        if (this.channelNameToChannelMap.containsKey(channel.getName())) {
            throw new IllegalArgumentException("Already subscribed to a channel with name " + channel.getName());
        }
        for (String eventName : eventNames) {
            channel.bind(eventName, listener);
        }
        channel.setEventListener(listener);
    }
}
