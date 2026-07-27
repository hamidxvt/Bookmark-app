package com.pusher.client;

import com.pusher.client.channel.Channel;
import com.pusher.client.channel.ChannelEventListener;
import com.pusher.client.channel.PresenceChannel;
import com.pusher.client.channel.PresenceChannelEventListener;
import com.pusher.client.channel.PrivateChannel;
import com.pusher.client.channel.PrivateChannelEventListener;
import com.pusher.client.channel.PrivateEncryptedChannel;
import com.pusher.client.channel.PrivateEncryptedChannelEventListener;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.impl.ChannelManager;
import com.pusher.client.channel.impl.InternalChannel;
import com.pusher.client.channel.impl.PresenceChannelImpl;
import com.pusher.client.channel.impl.PrivateChannelImpl;
import com.pusher.client.channel.impl.PrivateEncryptedChannelImpl;
import com.pusher.client.connection.Connection;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.impl.InternalConnection;
import com.pusher.client.user.User;
import com.pusher.client.user.impl.InternalUser;
import com.pusher.client.util.Factory;
import java.util.function.Consumer;

/* loaded from: classes17.dex */
public class Pusher implements Client {
    private final ChannelManager channelManager;
    private final InternalConnection connection;
    private final Factory factory;
    private final PusherOptions pusherOptions;
    private final InternalUser user;

    public Pusher(String apiKey) {
        this(apiKey, new PusherOptions());
    }

    public Pusher(String apiKey, PusherOptions pusherOptions) {
        this(apiKey, pusherOptions, new Factory());
    }

    Pusher(String apiKey, PusherOptions pusherOptions, Factory factory) {
        if (apiKey == null || apiKey.length() == 0) {
            throw new IllegalArgumentException("API Key cannot be null or empty");
        }
        if (pusherOptions == null) {
            throw new IllegalArgumentException("PusherOptions cannot be null");
        }
        this.pusherOptions = pusherOptions;
        this.factory = factory;
        this.connection = factory.getConnection(apiKey, this.pusherOptions, new Consumer() { // from class: com.pusher.client.Pusher$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Pusher.this.handleEvent((PusherEvent) obj);
            }
        });
        this.channelManager = factory.getChannelManager();
        this.user = factory.newUser(this.connection, pusherOptions.getUserAuthenticator());
        this.channelManager.setConnection(this.connection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleEvent(PusherEvent event) {
        this.user.handleEvent(event);
        this.channelManager.handleEvent(event);
    }

    @Override // com.pusher.client.Client
    public Connection getConnection() {
        return this.connection;
    }

    @Override // com.pusher.client.Client
    public void connect() {
        connect(null, new ConnectionState[0]);
    }

    @Override // com.pusher.client.Client
    public void connect(ConnectionEventListener eventListener, ConnectionState... connectionStates) {
        if (eventListener != null) {
            if (connectionStates.length == 0) {
                connectionStates = new ConnectionState[]{ConnectionState.ALL};
            }
            for (ConnectionState state : connectionStates) {
                this.connection.bind(state, eventListener);
            }
        } else if (connectionStates.length > 0) {
            throw new IllegalArgumentException("Cannot bind to connection states with a null connection event listener");
        }
        this.connection.connect();
    }

    @Override // com.pusher.client.Client
    public void disconnect() {
        if (this.connection.getState() != ConnectionState.DISCONNECTING && this.connection.getState() != ConnectionState.DISCONNECTED) {
            this.connection.disconnect();
        }
    }

    public User user() {
        return this.user;
    }

    public void signin() {
        throwExceptionIfNoUserAuthenticatorHasBeenSet();
        this.user.signin();
    }

    @Override // com.pusher.client.Client
    public Channel subscribe(String channelName) {
        return subscribe(channelName, null, new String[0]);
    }

    @Override // com.pusher.client.Client
    public Channel subscribe(String channelName, ChannelEventListener listener, String... eventNames) {
        InternalChannel channel = this.factory.newPublicChannel(channelName);
        this.channelManager.subscribeTo(channel, listener, eventNames);
        return channel;
    }

    @Override // com.pusher.client.Client
    public PrivateChannel subscribePrivate(String channelName) {
        return subscribePrivate(channelName, null, new String[0]);
    }

    @Override // com.pusher.client.Client
    public PrivateChannel subscribePrivate(String channelName, PrivateChannelEventListener listener, String... eventNames) {
        throwExceptionIfNoChannelAuthorizerHasBeenSet();
        PrivateChannelImpl channel = this.factory.newPrivateChannel(this.connection, channelName, this.pusherOptions.getChannelAuthorizer());
        this.channelManager.subscribeTo(channel, listener, eventNames);
        return channel;
    }

    public PrivateEncryptedChannel subscribePrivateEncrypted(String channelName, PrivateEncryptedChannelEventListener listener, String... eventNames) {
        throwExceptionIfNoChannelAuthorizerHasBeenSet();
        PrivateEncryptedChannelImpl channel = this.factory.newPrivateEncryptedChannel(this.connection, channelName, this.pusherOptions.getChannelAuthorizer());
        this.channelManager.subscribeTo(channel, listener, eventNames);
        return channel;
    }

    @Override // com.pusher.client.Client
    public PresenceChannel subscribePresence(String channelName) {
        return subscribePresence(channelName, null, new String[0]);
    }

    @Override // com.pusher.client.Client
    public PresenceChannel subscribePresence(String channelName, PresenceChannelEventListener listener, String... eventNames) {
        throwExceptionIfNoChannelAuthorizerHasBeenSet();
        PresenceChannelImpl channel = this.factory.newPresenceChannel(this.connection, channelName, this.pusherOptions.getChannelAuthorizer());
        this.channelManager.subscribeTo(channel, listener, eventNames);
        return channel;
    }

    @Override // com.pusher.client.Client
    public void unsubscribe(String channelName) {
        this.channelManager.unsubscribeFrom(channelName);
    }

    private void throwExceptionIfNoChannelAuthorizerHasBeenSet() {
        if (this.pusherOptions.getChannelAuthorizer() == null) {
            throw new IllegalStateException("Cannot subscribe to a private or presence channel because no ChannelAuthorizer has been set. Call PusherOptions.setChannelAuthorizer() before connecting to Pusher");
        }
    }

    private void throwExceptionIfNoUserAuthenticatorHasBeenSet() {
        if (this.pusherOptions.getUserAuthenticator() == null) {
            throw new IllegalStateException("Cannot sign in because no UserAuthenticator has been set. Call PusherOptions.setUserAuthenticator() before connecting to Pusher");
        }
    }

    @Override // com.pusher.client.Client
    public Channel getChannel(String channelName) {
        return this.channelManager.getChannel(channelName);
    }

    @Override // com.pusher.client.Client
    public PrivateChannel getPrivateChannel(String channelName) {
        return this.channelManager.getPrivateChannel(channelName);
    }

    public PrivateEncryptedChannel getPrivateEncryptedChannel(String channelName) {
        return this.channelManager.getPrivateEncryptedChannel(channelName);
    }

    @Override // com.pusher.client.Client
    public PresenceChannel getPresenceChannel(String channelName) {
        return this.channelManager.getPresenceChannel(channelName);
    }
}
