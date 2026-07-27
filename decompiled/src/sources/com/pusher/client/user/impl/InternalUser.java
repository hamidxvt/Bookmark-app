package com.pusher.client.user.impl;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import com.pusher.client.AuthenticationFailureException;
import com.pusher.client.UserAuthenticator;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.channel.impl.ChannelManager;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.pusher.client.connection.impl.InternalConnection;
import com.pusher.client.user.User;
import com.pusher.client.user.impl.message.AuthenticationResponse;
import com.pusher.client.user.impl.message.SigninMessage;
import com.pusher.client.util.Factory;
import java.util.Map;
import java.util.logging.Logger;

/* loaded from: classes17.dex */
public class InternalUser implements User {
    private static final Gson GSON = new Gson();
    private static final Logger log = Logger.getLogger(User.class.getName());
    private final ChannelManager channelManager;
    private final InternalConnection connection;
    private final ServerToUserChannel serverToUserChannel;
    private boolean signinRequested = false;
    private final UserAuthenticator userAuthenticator;
    private String userId;

    private static class ConnectionStateChangeHandler implements ConnectionEventListener {
        private final InternalUser user;

        public ConnectionStateChangeHandler(InternalUser user) {
            this.user = user;
        }

        @Override // com.pusher.client.connection.ConnectionEventListener
        public void onConnectionStateChange(ConnectionStateChange change) {
            switch (change.getCurrentState()) {
                case CONNECTED:
                    this.user.attemptSignin();
                    break;
                case CONNECTING:
                case DISCONNECTED:
                    this.user.disconnect();
                    break;
            }
        }

        @Override // com.pusher.client.connection.ConnectionEventListener
        public void onError(String message, String code, Exception e) {
            InternalUser.log.warning(message);
        }
    }

    public InternalUser(InternalConnection connection, UserAuthenticator userAuthenticator, Factory factory) {
        this.connection = connection;
        this.userAuthenticator = userAuthenticator;
        this.channelManager = factory.getChannelManager();
        this.serverToUserChannel = new ServerToUserChannel(this, factory);
        connection.bind(ConnectionState.ALL, new ConnectionStateChangeHandler(this));
    }

    public void signin() throws AuthenticationFailureException {
        if (this.signinRequested || this.userId != null) {
            return;
        }
        this.signinRequested = true;
        attemptSignin();
    }

    public void handleEvent(PusherEvent event) {
        if (event.getEventName().equals("pusher:signin_success")) {
            onSigninSuccess(event);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void attemptSignin() throws AuthenticationFailureException {
        if (!this.signinRequested || this.userId != null || this.connection.getState() != ConnectionState.CONNECTED) {
            return;
        }
        AuthenticationResponse authenticationResponse = getAuthenticationResponse();
        this.connection.sendMessage(authenticationResponseToSigninMessage(authenticationResponse));
    }

    private static String authenticationResponseToSigninMessage(AuthenticationResponse authenticationResponse) {
        return GSON.toJson(new SigninMessage(authenticationResponse.getAuth(), authenticationResponse.getUserData()));
    }

    private AuthenticationResponse getAuthenticationResponse() throws AuthenticationFailureException {
        String response = this.userAuthenticator.authenticate(this.connection.getSocketId());
        try {
            AuthenticationResponse authenticationResponse = (AuthenticationResponse) GSON.fromJson(response, AuthenticationResponse.class);
            if (authenticationResponse.getAuth() == null || authenticationResponse.getUserData() == null) {
                throw new AuthenticationFailureException("Didn't receive all the fields expected from the UserAuthenticator. Expected auth and user_data");
            }
            return authenticationResponse;
        } catch (JsonSyntaxException e) {
            throw new AuthenticationFailureException("Unable to parse response from AuthenticationResponse");
        }
    }

    private void onSigninSuccess(PusherEvent event) {
        try {
            String userData = (String) ((Map) GSON.fromJson(event.getData(), Map.class)).get("user_data");
            this.userId = (String) ((Map) GSON.fromJson(userData, Map.class)).get(Constant.VISIT_ID);
            if (this.userId == null) {
                log.severe("User data doesn't contain an id");
            } else {
                this.channelManager.subscribeTo(this.serverToUserChannel, null, new String[0]);
            }
        } catch (Exception e) {
            log.severe("Failed parsing user data after signin");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disconnect() {
        if (this.serverToUserChannel.isSubscribed()) {
            this.channelManager.unsubscribeFrom(this.serverToUserChannel.getName());
        }
        this.userId = null;
    }

    @Override // com.pusher.client.user.User
    public String userId() {
        return this.userId;
    }

    @Override // com.pusher.client.user.User
    public void bind(String eventName, SubscriptionEventListener listener) {
        this.serverToUserChannel.bind(eventName, listener);
    }

    @Override // com.pusher.client.user.User
    public void bindGlobal(SubscriptionEventListener listener) {
        this.serverToUserChannel.bindGlobal(listener);
    }

    @Override // com.pusher.client.user.User
    public void unbind(String eventName, SubscriptionEventListener listener) {
        this.serverToUserChannel.unbind(eventName, listener);
    }

    @Override // com.pusher.client.user.User
    public void unbindGlobal(SubscriptionEventListener listener) {
        this.serverToUserChannel.unbindGlobal(listener);
    }
}
