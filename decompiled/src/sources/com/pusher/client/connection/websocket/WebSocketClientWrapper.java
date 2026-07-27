package com.pusher.client.connection.websocket;

import java.net.Proxy;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/* loaded from: classes17.dex */
public class WebSocketClientWrapper extends WebSocketClient {
    private static final String WSS_SCHEME = "wss";
    private WebSocketListener webSocketListener;

    public WebSocketClientWrapper(URI uri, Proxy proxy, WebSocketListener webSocketListener) throws SSLException {
        super(uri);
        if (uri.getScheme().equals(WSS_SCHEME)) {
            try {
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, null, null);
                SSLSocketFactory factory = sslContext.getSocketFactory();
                setSocketFactory(factory);
            } catch (KeyManagementException e) {
                throw new SSLException(e);
            } catch (NoSuchAlgorithmException e2) {
                throw new SSLException(e2);
            }
        }
        this.webSocketListener = webSocketListener;
        setProxy(proxy);
    }

    @Override // org.java_websocket.client.WebSocketClient
    public void onOpen(ServerHandshake handshakeData) {
        if (this.webSocketListener != null) {
            this.webSocketListener.onOpen(handshakeData);
        }
    }

    @Override // org.java_websocket.client.WebSocketClient
    public void onMessage(String message) {
        if (this.webSocketListener != null) {
            this.webSocketListener.onMessage(message);
        }
    }

    @Override // org.java_websocket.client.WebSocketClient
    public void onClose(int code, String reason, boolean remote) {
        if (this.webSocketListener != null) {
            this.webSocketListener.onClose(code, reason, remote);
        }
    }

    @Override // org.java_websocket.client.WebSocketClient
    public void onError(Exception ex) {
        if (this.webSocketListener != null) {
            this.webSocketListener.onError(ex);
        }
    }

    public void removeWebSocketListener() {
        this.webSocketListener = null;
    }

    @Override // org.java_websocket.client.WebSocketClient
    protected void onSetSSLParameters(SSLParameters sslParameters) {
        try {
            super.onSetSSLParameters(sslParameters);
        } catch (NoSuchMethodError e) {
        }
    }
}
