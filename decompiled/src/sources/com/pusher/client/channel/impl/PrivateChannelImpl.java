package com.pusher.client.channel.impl;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.pusher.client.AuthorizationFailureException;
import com.pusher.client.ChannelAuthorizer;
import com.pusher.client.channel.ChannelState;
import com.pusher.client.channel.PrivateChannel;
import com.pusher.client.channel.PrivateChannelEventListener;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.channel.impl.message.AuthResponse;
import com.pusher.client.channel.impl.message.SubscribeMessage;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.impl.InternalConnection;
import com.pusher.client.util.Factory;

/* loaded from: classes17.dex */
public class PrivateChannelImpl extends ChannelImpl implements PrivateChannel {
    private static final String CLIENT_EVENT_PREFIX = "client-";
    private static final Gson GSON = new Gson();
    private final ChannelAuthorizer channelAuthorizer;
    protected String channelData;
    private final InternalConnection connection;

    public PrivateChannelImpl(InternalConnection connection, String channelName, ChannelAuthorizer channelAuthorizer, Factory factory) {
        super(channelName, factory);
        this.connection = connection;
        this.channelAuthorizer = channelAuthorizer;
    }

    @Override // com.pusher.client.channel.PrivateChannel
    public void trigger(String eventName, String data) {
        if (eventName == null || !eventName.startsWith(CLIENT_EVENT_PREFIX)) {
            throw new IllegalArgumentException("Cannot trigger event " + eventName + ": client events must start with \"client-\"");
        }
        if (this.state != ChannelState.SUBSCRIBED) {
            throw new IllegalStateException("Cannot trigger event " + eventName + " because channel " + this.name + " is in " + this.state.toString() + " state");
        }
        if (this.connection.getState() != ConnectionState.CONNECTED) {
            throw new IllegalStateException("Cannot trigger event " + eventName + " because connection is in " + this.connection.getState().toString() + " state");
        }
        String json = new PusherEvent(eventName, this.name, (String) null, data).toJson();
        this.connection.sendMessage(json);
    }

    @Override // com.pusher.client.channel.impl.BaseChannel, com.pusher.client.channel.Channel
    public void bind(String eventName, SubscriptionEventListener listener) {
        if (!(listener instanceof PrivateChannelEventListener)) {
            throw new IllegalArgumentException("Only instances of PrivateChannelEventListener can be bound to a private channel");
        }
        super.bind(eventName, listener);
    }

    private String authorize() {
        try {
            AuthResponse authResponse = (AuthResponse) GSON.fromJson(getAuthorizationResponse(), AuthResponse.class);
            this.channelData = authResponse.getChannelData();
            if (authResponse.getAuth() == null) {
                throw new AuthorizationFailureException("Didn't receive all the fields expected from the ChannelAuthorizer, expected an auth and shared_secret.");
            }
            return authResponse.getAuth();
        } catch (JsonSyntaxException e) {
            throw new AuthorizationFailureException("Unable to parse response from ChannelAuthorizer");
        }
    }

    @Override // com.pusher.client.channel.impl.BaseChannel, com.pusher.client.channel.impl.InternalChannel
    public String toSubscribeMessage() {
        return GSON.toJson(new SubscribeMessage(this.name, authorize(), this.channelData));
    }

    @Override // com.pusher.client.channel.impl.ChannelImpl
    protected String[] getDisallowedNameExpressions() {
        return new String[]{"^(?!private-).*", "^private-encrypted-.*"};
    }

    private String getAuthorizationResponse() {
        String socketId = this.connection.getSocketId();
        return this.channelAuthorizer.authorize(getName(), socketId);
    }

    @Override // com.pusher.client.channel.impl.ChannelImpl, com.pusher.client.channel.impl.BaseChannel
    public String toString() {
        return String.format("[Private Channel: name=%s]", this.name);
    }
}
