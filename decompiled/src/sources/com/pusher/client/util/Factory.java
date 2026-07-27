package com.pusher.client.util;

import com.pusher.client.ChannelAuthorizer;
import com.pusher.client.PusherOptions;
import com.pusher.client.UserAuthenticator;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.impl.ChannelImpl;
import com.pusher.client.channel.impl.ChannelManager;
import com.pusher.client.channel.impl.PresenceChannelImpl;
import com.pusher.client.channel.impl.PrivateChannelImpl;
import com.pusher.client.channel.impl.PrivateEncryptedChannelImpl;
import com.pusher.client.connection.impl.InternalConnection;
import com.pusher.client.connection.websocket.WebSocketClientWrapper;
import com.pusher.client.connection.websocket.WebSocketConnection;
import com.pusher.client.connection.websocket.WebSocketListener;
import com.pusher.client.crypto.nacl.SecretBoxOpenerFactory;
import com.pusher.client.user.impl.InternalUser;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.function.Consumer;
import javax.net.ssl.SSLException;

/* loaded from: classes17.dex */
public class Factory {
    private static final Object eventLock = new Object();
    private ChannelManager channelManager;
    private InternalConnection connection;
    private ExecutorService eventQueue;
    private ScheduledExecutorService timers;

    public synchronized InternalConnection getConnection(String apiKey, PusherOptions options, Consumer<PusherEvent> eventHandler) {
        if (this.connection == null) {
            try {
                this.connection = new WebSocketConnection(options.buildUrl(apiKey), options.getActivityTimeout(), options.getPongTimeout(), options.getMaxReconnectionAttempts(), options.getMaxReconnectGapInSeconds(), options.getProxy(), eventHandler, this);
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Failed to initialise connection", e);
            }
        }
        return this.connection;
    }

    public WebSocketClientWrapper newWebSocketClientWrapper(URI uri, Proxy proxy, WebSocketListener webSocketListener) throws SSLException {
        return new WebSocketClientWrapper(uri, proxy, webSocketListener);
    }

    public synchronized ScheduledExecutorService getTimers() {
        if (this.timers == null) {
            this.timers = Executors.newSingleThreadScheduledExecutor(new DaemonThreadFactory("timers"));
        }
        return this.timers;
    }

    public ChannelImpl newPublicChannel(String channelName) {
        return new ChannelImpl(channelName, this);
    }

    public PrivateChannelImpl newPrivateChannel(InternalConnection connection, String channelName, ChannelAuthorizer channelAuthorizer) {
        return new PrivateChannelImpl(connection, channelName, channelAuthorizer, this);
    }

    public PrivateEncryptedChannelImpl newPrivateEncryptedChannel(InternalConnection connection, String channelName, ChannelAuthorizer channelAuthorizer) {
        return new PrivateEncryptedChannelImpl(connection, channelName, channelAuthorizer, this, new SecretBoxOpenerFactory());
    }

    public PresenceChannelImpl newPresenceChannel(InternalConnection connection, String channelName, ChannelAuthorizer channelAuthorizer) {
        return new PresenceChannelImpl(connection, channelName, channelAuthorizer, this);
    }

    public InternalUser newUser(InternalConnection connection, UserAuthenticator userAuthenticator) {
        return new InternalUser(connection, userAuthenticator, this);
    }

    public synchronized ChannelManager getChannelManager() {
        if (this.channelManager == null) {
            this.channelManager = new ChannelManager(this);
        }
        return this.channelManager;
    }

    public synchronized void queueOnEventThread(final Runnable r) {
        if (this.eventQueue == null) {
            this.eventQueue = Executors.newSingleThreadExecutor(new DaemonThreadFactory("eventQueue"));
        }
        this.eventQueue.execute(new Runnable() { // from class: com.pusher.client.util.Factory$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Factory.lambda$queueOnEventThread$0(r);
            }
        });
    }

    static /* synthetic */ void lambda$queueOnEventThread$0(Runnable r) {
        synchronized (eventLock) {
            r.run();
        }
    }

    public synchronized void shutdownThreads() {
        if (this.eventQueue != null) {
            this.eventQueue.shutdown();
            this.eventQueue = null;
        }
        if (this.timers != null) {
            this.timers.shutdown();
            this.timers = null;
        }
    }

    private static class DaemonThreadFactory implements ThreadFactory {
        private final String name;

        public DaemonThreadFactory(String name) {
            this.name = name;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.setName("pusher-java-client " + this.name);
            return t;
        }
    }
}
