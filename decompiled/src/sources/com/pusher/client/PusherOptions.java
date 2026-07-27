package com.pusher.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.util.Properties;

/* loaded from: classes17.dex */
public class PusherOptions {
    private static final long DEFAULT_ACTIVITY_TIMEOUT = 120000;
    private static final long DEFAULT_PONG_TIMEOUT = 30000;
    private static final String LIB_DEV_VERSION = "0.0.0-dev";
    private static final int MAX_RECONNECTION_ATTEMPTS = 6;
    private static final int MAX_RECONNECT_GAP_IN_SECONDS = 30;
    private static final String PUSHER_DOMAIN = "pusher.com";
    private static final String SRC_LIB_DEV_VERSION = "@version@";
    private static final int WSS_PORT = 443;
    private static final String WSS_SCHEME = "wss";
    private static final int WS_PORT = 80;
    private static final String WS_SCHEME = "ws";
    private Authorizer authorizer;
    private ChannelAuthorizer channelAuthorizer;
    private UserAuthenticator userAuthenticator;
    public static final String LIB_VERSION = readVersionFromProperties();
    private static final String URI_SUFFIX = "?client=java-client&protocol=5&version=" + LIB_VERSION;
    private String host = "ws.pusherapp.com";
    private int wsPort = 80;
    private int wssPort = 443;
    private boolean useTLS = true;
    private long activityTimeout = DEFAULT_ACTIVITY_TIMEOUT;
    private long pongTimeout = 30000;
    private Proxy proxy = Proxy.NO_PROXY;
    private int maxReconnectionAttempts = 6;
    private int maxReconnectGapInSeconds = 30;

    @Deprecated
    public boolean isEncrypted() {
        return this.useTLS;
    }

    @Deprecated
    public PusherOptions setEncrypted(boolean encrypted) {
        this.useTLS = encrypted;
        return this;
    }

    public boolean isUseTLS() {
        return this.useTLS;
    }

    public PusherOptions setUseTLS(boolean useTLS) {
        this.useTLS = useTLS;
        return this;
    }

    public UserAuthenticator getUserAuthenticator() {
        return this.userAuthenticator;
    }

    public PusherOptions setUserAuthenticator(UserAuthenticator userAuthenticator) {
        this.userAuthenticator = userAuthenticator;
        return this;
    }

    public ChannelAuthorizer getChannelAuthorizer() {
        return this.channelAuthorizer;
    }

    public PusherOptions setChannelAuthorizer(ChannelAuthorizer channelAuthorizer) {
        this.channelAuthorizer = channelAuthorizer;
        return this;
    }

    @Deprecated
    public Authorizer getAuthorizer() {
        return this.authorizer;
    }

    @Deprecated
    public PusherOptions setAuthorizer(Authorizer authorizer) {
        this.authorizer = authorizer;
        return setChannelAuthorizer(authorizer);
    }

    public PusherOptions setHost(String host) {
        this.host = host;
        return this;
    }

    public PusherOptions setWsPort(int wsPort) {
        this.wsPort = wsPort;
        return this;
    }

    public PusherOptions setWssPort(int wssPort) {
        this.wssPort = wssPort;
        return this;
    }

    public PusherOptions setCluster(String cluster) {
        this.host = "ws-" + cluster + "." + PUSHER_DOMAIN;
        this.wsPort = 80;
        this.wssPort = 443;
        return this;
    }

    public PusherOptions setActivityTimeout(long activityTimeout) {
        if (activityTimeout < 1000) {
            throw new IllegalArgumentException("Activity timeout must be at least 1,000ms (and is recommended to be much higher)");
        }
        this.activityTimeout = activityTimeout;
        return this;
    }

    public long getActivityTimeout() {
        return this.activityTimeout;
    }

    public PusherOptions setPongTimeout(long pongTimeout) {
        if (pongTimeout < 1000) {
            throw new IllegalArgumentException("Pong timeout must be at least 1,000ms (and is recommended to be much higher)");
        }
        this.pongTimeout = pongTimeout;
        return this;
    }

    public PusherOptions setMaxReconnectionAttempts(int maxReconnectionAttempts) {
        this.maxReconnectionAttempts = maxReconnectionAttempts;
        return this;
    }

    public PusherOptions setMaxReconnectGapInSeconds(int maxReconnectGapInSeconds) {
        this.maxReconnectGapInSeconds = maxReconnectGapInSeconds;
        return this;
    }

    public long getPongTimeout() {
        return this.pongTimeout;
    }

    public String buildUrl(String apiKey) {
        return String.format("%s://%s:%s/app/%s%s", this.useTLS ? WSS_SCHEME : WS_SCHEME, this.host, Integer.valueOf(this.useTLS ? this.wssPort : this.wsPort), apiKey, URI_SUFFIX);
    }

    public PusherOptions setProxy(Proxy proxy) {
        if (proxy == null) {
            throw new IllegalArgumentException("proxy must not be null (instead use Proxy.NO_PROXY)");
        }
        this.proxy = proxy;
        return this;
    }

    public Proxy getProxy() {
        return this.proxy;
    }

    public int getMaxReconnectionAttempts() {
        return this.maxReconnectionAttempts;
    }

    public int getMaxReconnectGapInSeconds() {
        return this.maxReconnectGapInSeconds;
    }

    private static String readVersionFromProperties() {
        InputStream inStream = null;
        try {
            try {
                Properties p = new Properties();
                inStream = PusherOptions.class.getResourceAsStream("/pusher.properties");
                p.load(inStream);
                String version = (String) p.get("version");
                if (version.equals(SRC_LIB_DEV_VERSION)) {
                    version = LIB_DEV_VERSION;
                }
                if (version != null) {
                    if (version.length() > 0) {
                        if (inStream != null) {
                            try {
                                inStream.close();
                            } catch (IOException e) {
                            }
                        }
                        return version;
                    }
                }
                if (inStream == null) {
                    return "0.0.0";
                }
                inStream.close();
                return "0.0.0";
            } catch (Exception e2) {
                if (inStream == null) {
                    return "0.0.0";
                }
                inStream.close();
                return "0.0.0";
            } catch (Throwable th) {
                if (inStream != null) {
                    try {
                        inStream.close();
                    } catch (IOException e3) {
                    }
                }
                throw th;
            }
        } catch (IOException e4) {
            return "0.0.0";
        }
    }
}
