package com.pusher.client.connection.websocket;

import com.google.gson.Gson;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.pusher.client.connection.impl.InternalConnection;
import com.pusher.client.connection.websocket.WebSocketConnection;
import com.pusher.client.util.Factory;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;
import org.java_websocket.handshake.ServerHandshake;

/* loaded from: classes17.dex */
public class WebSocketConnection implements InternalConnection, WebSocketListener {
    private static final String PING_EVENT_SERIALIZED = "{\"event\": \"pusher:ping\"}";
    private final ActivityTimer activityTimer;
    private final Consumer<PusherEvent> eventHandler;
    private final Factory factory;
    private final int maxReconnectionAttempts;
    private final int maxReconnectionGap;
    private final Proxy proxy;
    private String socketId;
    private WebSocketClientWrapper underlyingConnection;
    private final URI webSocketUri;
    private static final Logger log = Logger.getLogger(WebSocketConnection.class.getName());
    private static final Gson GSON = new Gson();
    private final Map<ConnectionState, Set<ConnectionEventListener>> eventListeners = new ConcurrentHashMap();
    private volatile ConnectionState state = ConnectionState.DISCONNECTED;
    private int reconnectAttempts = 0;

    public WebSocketConnection(String url, long activityTimeout, long pongTimeout, int maxReconnectionAttempts, int maxReconnectionGap, Proxy proxy, Consumer<PusherEvent> eventHandler, Factory factory) throws URISyntaxException {
        this.webSocketUri = new URI(url);
        this.activityTimer = new ActivityTimer(activityTimeout, pongTimeout);
        this.maxReconnectionAttempts = maxReconnectionAttempts;
        this.maxReconnectionGap = maxReconnectionGap;
        this.proxy = proxy;
        this.factory = factory;
        this.eventHandler = eventHandler;
        for (ConnectionState state : ConnectionState.values()) {
            this.eventListeners.put(state, Collections.newSetFromMap(new ConcurrentHashMap()));
        }
    }

    @Override // com.pusher.client.connection.Connection
    public void connect() {
        this.factory.queueOnEventThread(new Runnable() { // from class: com.pusher.client.connection.websocket.WebSocketConnection$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                WebSocketConnection.this.m557x94364e83();
            }
        });
    }

    /* renamed from: lambda$connect$0$com-pusher-client-connection-websocket-WebSocketConnection, reason: not valid java name */
    /* synthetic */ void m557x94364e83() {
        if (canConnect()) {
            tryConnecting();
        }
    }

    private void tryConnecting() {
        try {
            this.underlyingConnection = this.factory.newWebSocketClientWrapper(this.webSocketUri, this.proxy, this);
            updateState(ConnectionState.CONNECTING);
            this.underlyingConnection.connect();
        } catch (SSLException e) {
            sendErrorToAllListeners("Error connecting over SSL", null, e);
        }
    }

    @Override // com.pusher.client.connection.impl.InternalConnection
    public void disconnect() {
        this.factory.queueOnEventThread(new Runnable() { // from class: com.pusher.client.connection.websocket.WebSocketConnection$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                WebSocketConnection.this.m558x3ff1b4d6();
            }
        });
    }

    /* renamed from: lambda$disconnect$1$com-pusher-client-connection-websocket-WebSocketConnection, reason: not valid java name */
    /* synthetic */ void m558x3ff1b4d6() {
        if (canDisconnect()) {
            updateState(ConnectionState.DISCONNECTING);
            this.underlyingConnection.close();
        }
    }

    @Override // com.pusher.client.connection.Connection
    public void bind(ConnectionState state, ConnectionEventListener eventListener) {
        this.eventListeners.get(state).add(eventListener);
    }

    @Override // com.pusher.client.connection.Connection
    public boolean unbind(ConnectionState state, ConnectionEventListener eventListener) {
        return this.eventListeners.get(state).remove(eventListener);
    }

    @Override // com.pusher.client.connection.Connection
    public ConnectionState getState() {
        return this.state;
    }

    @Override // com.pusher.client.connection.impl.InternalConnection
    public void sendMessage(final String message) {
        this.factory.queueOnEventThread(new Runnable() { // from class: com.pusher.client.connection.websocket.WebSocketConnection$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                WebSocketConnection.this.m561xdb96bc6c(message);
            }
        });
    }

    /* renamed from: lambda$sendMessage$2$com-pusher-client-connection-websocket-WebSocketConnection, reason: not valid java name */
    /* synthetic */ void m561xdb96bc6c(String message) {
        try {
            if (this.state != ConnectionState.CONNECTED) {
                sendErrorToAllListeners("Cannot send a message while in " + this.state + " state", null, null);
            } else {
                this.underlyingConnection.send(message);
            }
        } catch (Exception e) {
            sendErrorToAllListeners("An exception occurred while sending message [" + message + "]", null, e);
        }
    }

    @Override // com.pusher.client.connection.Connection
    public String getSocketId() {
        return this.socketId;
    }

    private void updateState(ConnectionState newState) {
        log.fine("State transition requested, current [" + this.state + "], new [" + newState + "]");
        final ConnectionStateChange change = new ConnectionStateChange(this.state, newState);
        this.state = newState;
        Set<ConnectionEventListener> interestedListeners = new HashSet<>();
        interestedListeners.addAll(this.eventListeners.get(ConnectionState.ALL));
        interestedListeners.addAll(this.eventListeners.get(newState));
        for (final ConnectionEventListener listener : interestedListeners) {
            this.factory.queueOnEventThread(new Runnable() { // from class: com.pusher.client.connection.websocket.WebSocketConnection$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    ConnectionEventListener.this.onConnectionStateChange(change);
                }
            });
        }
    }

    private void handleEvent(PusherEvent event) {
        if (event.getEventName().equals("pusher:connection_established")) {
            handleConnectihandleEvent(event);
        } else if (event.getEventName().equals("pusher:error")) {
            handleError(event);
        }
        this.eventHandler.accept(event);
    }

    private void handleConnectihandleEvent(PusherEvent event) {
        Map dataMap = (Map) GSON.fromJson(event.getData(), Map.class);
        this.socketId = (String) dataMap.get("socket_id");
        if (this.state != ConnectionState.CONNECTED) {
            updateState(ConnectionState.CONNECTED);
        }
        this.reconnectAttempts = 0;
    }

    private void handleError(PusherEvent event) {
        Map dataMap = (Map) GSON.fromJson(event.getData(), Map.class);
        String message = (String) dataMap.get("message");
        Object codeObject = dataMap.get("code");
        String code = null;
        if (codeObject != null) {
            code = String.valueOf(Math.round(((Double) codeObject).doubleValue()));
        }
        sendErrorToAllListeners(message, code, null);
    }

    private void sendErrorToAllListeners(final String message, final String code, final Exception e) {
        Set<ConnectionEventListener> allListeners = new HashSet<>();
        for (Set<ConnectionEventListener> listenersForState : this.eventListeners.values()) {
            allListeners.addAll(listenersForState);
        }
        for (final ConnectionEventListener listener : allListeners) {
            this.factory.queueOnEventThread(new Runnable() { // from class: com.pusher.client.connection.websocket.WebSocketConnection$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ConnectionEventListener.this.onError(message, code, e);
                }
            });
        }
    }

    @Override // com.pusher.client.connection.websocket.WebSocketListener
    public void onOpen(ServerHandshake handshakeData) {
    }

    @Override // com.pusher.client.connection.websocket.WebSocketListener
    public void onMessage(final String message) {
        this.activityTimer.activity();
        this.factory.queueOnEventThread(new Runnable() { // from class: com.pusher.client.connection.websocket.WebSocketConnection$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                WebSocketConnection.this.m560xa93e0ca0(message);
            }
        });
    }

    /* renamed from: lambda$onMessage$5$com-pusher-client-connection-websocket-WebSocketConnection, reason: not valid java name */
    /* synthetic */ void m560xa93e0ca0(String message) {
        handleEvent(PusherEvent.fromJson(message));
    }

    @Override // com.pusher.client.connection.websocket.WebSocketListener
    public void onClose(int code, String reason, boolean remote) {
        if (this.state == ConnectionState.DISCONNECTED || this.state == ConnectionState.RECONNECTING) {
            log.warning("Received close from underlying socket when already disconnected.Close code [" + code + "], Reason [" + reason + "], Remote [" + remote + "]");
            return;
        }
        if (!shouldReconnect(code)) {
            updateState(ConnectionState.DISCONNECTING);
        }
        if (this.state == ConnectionState.CONNECTED || this.state == ConnectionState.CONNECTING) {
            if (this.reconnectAttempts < this.maxReconnectionAttempts) {
                tryReconnecting();
                return;
            } else {
                updateState(ConnectionState.DISCONNECTING);
                cancelTimeoutsAndTransitionToDisconnected();
                return;
            }
        }
        if (this.state == ConnectionState.DISCONNECTING) {
            cancelTimeoutsAndTransitionToDisconnected();
        }
    }

    private void tryReconnecting() {
        this.reconnectAttempts++;
        updateState(ConnectionState.RECONNECTING);
        long reconnectInterval = Math.min(this.maxReconnectionGap, this.reconnectAttempts * this.reconnectAttempts);
        this.factory.getTimers().schedule(new Runnable() { // from class: com.pusher.client.connection.websocket.WebSocketConnection$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                WebSocketConnection.this.m562x74b7a5a1();
            }
        }, reconnectInterval, TimeUnit.SECONDS);
    }

    /* renamed from: lambda$tryReconnecting$6$com-pusher-client-connection-websocket-WebSocketConnection, reason: not valid java name */
    /* synthetic */ void m562x74b7a5a1() {
        if (this.state == ConnectionState.RECONNECTING) {
            this.underlyingConnection.removeWebSocketListener();
            tryConnecting();
        }
    }

    private boolean shouldReconnect(int code) {
        return code < 4000 || code >= 4100;
    }

    private void cancelTimeoutsAndTransitionToDisconnected() {
        this.activityTimer.cancelTimeouts();
        this.factory.queueOnEventThread(new Runnable() { // from class: com.pusher.client.connection.websocket.WebSocketConnection$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                WebSocketConnection.this.m556xe533cb0();
            }
        });
        this.reconnectAttempts = 0;
    }

    /* renamed from: lambda$cancelTimeoutsAndTransitionToDisconnected$7$com-pusher-client-connection-websocket-WebSocketConnection, reason: not valid java name */
    /* synthetic */ void m556xe533cb0() {
        if (this.state == ConnectionState.DISCONNECTING) {
            updateState(ConnectionState.DISCONNECTED);
            this.factory.shutdownThreads();
        }
    }

    @Override // com.pusher.client.connection.websocket.WebSocketListener
    public void onError(final Exception ex) {
        this.factory.queueOnEventThread(new Runnable() { // from class: com.pusher.client.connection.websocket.WebSocketConnection$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                WebSocketConnection.this.m559x20469b5c(ex);
            }
        });
    }

    /* renamed from: lambda$onError$8$com-pusher-client-connection-websocket-WebSocketConnection, reason: not valid java name */
    /* synthetic */ void m559x20469b5c(Exception ex) {
        sendErrorToAllListeners("An exception was thrown by the websocket", null, ex);
    }

    private boolean canConnect() {
        return this.state == ConnectionState.DISCONNECTING || this.state == ConnectionState.DISCONNECTED;
    }

    private boolean canDisconnect() {
        return (this.state == ConnectionState.DISCONNECTING || this.state == ConnectionState.DISCONNECTED) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    class ActivityTimer {
        private final long activityTimeout;
        private Future<?> pingTimer;
        private final long pongTimeout;
        private Future<?> pongTimer;

        ActivityTimer(long activityTimeout, long pongTimeout) {
            this.activityTimeout = activityTimeout;
            this.pongTimeout = pongTimeout;
        }

        synchronized void activity() {
            if (this.pongTimer != null) {
                this.pongTimer.cancel(true);
            }
            if (this.pingTimer != null) {
                this.pingTimer.cancel(false);
            }
            this.pingTimer = WebSocketConnection.this.factory.getTimers().schedule(new Runnable() { // from class: com.pusher.client.connection.websocket.WebSocketConnection$ActivityTimer$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WebSocketConnection.ActivityTimer.this.m563x20c3b156();
                }
            }, this.activityTimeout, TimeUnit.MILLISECONDS);
        }

        /* renamed from: lambda$activity$0$com-pusher-client-connection-websocket-WebSocketConnection$ActivityTimer, reason: not valid java name */
        /* synthetic */ void m563x20c3b156() {
            WebSocketConnection.log.fine("Sending ping");
            WebSocketConnection.this.sendMessage(WebSocketConnection.PING_EVENT_SERIALIZED);
            schedulePongCheck();
        }

        synchronized void cancelTimeouts() {
            if (this.pingTimer != null) {
                this.pingTimer.cancel(false);
            }
            if (this.pongTimer != null) {
                this.pongTimer.cancel(false);
            }
        }

        private synchronized void schedulePongCheck() {
            if (this.pongTimer != null) {
                this.pongTimer.cancel(false);
            }
            this.pongTimer = WebSocketConnection.this.factory.getTimers().schedule(new Runnable() { // from class: com.pusher.client.connection.websocket.WebSocketConnection$ActivityTimer$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    WebSocketConnection.ActivityTimer.this.m564x3c2f3c45();
                }
            }, this.pongTimeout, TimeUnit.MILLISECONDS);
        }

        /* renamed from: lambda$schedulePongCheck$1$com-pusher-client-connection-websocket-WebSocketConnection$ActivityTimer, reason: not valid java name */
        /* synthetic */ void m564x3c2f3c45() {
            WebSocketConnection.log.fine("Timed out awaiting pong from server - disconnecting");
            WebSocketConnection.this.underlyingConnection.removeWebSocketListener();
            WebSocketConnection.this.underlyingConnection.close();
            WebSocketConnection.this.onClose(-1, "Pong timeout", false);
        }
    }
}
