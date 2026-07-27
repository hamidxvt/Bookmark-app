package com.pusher.client.channel.impl;

import com.google.gson.JsonSyntaxException;
import com.pusher.client.AuthorizationFailureException;
import com.pusher.client.ChannelAuthorizer;
import com.pusher.client.channel.ChannelState;
import com.pusher.client.channel.PrivateEncryptedChannel;
import com.pusher.client.channel.PrivateEncryptedChannelEventListener;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.channel.impl.message.AuthResponse;
import com.pusher.client.channel.impl.message.EncryptedReceivedData;
import com.pusher.client.channel.impl.message.SubscribeMessage;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.pusher.client.connection.impl.InternalConnection;
import com.pusher.client.crypto.nacl.AuthenticityException;
import com.pusher.client.crypto.nacl.SecretBoxOpener;
import com.pusher.client.crypto.nacl.SecretBoxOpenerFactory;
import com.pusher.client.util.Factory;
import com.pusher.client.util.internal.Base64;
import java.util.Set;

/* loaded from: classes17.dex */
public class PrivateEncryptedChannelImpl extends ChannelImpl implements PrivateEncryptedChannel {
    private final ChannelAuthorizer channelAuthorizer;
    private final InternalConnection connection;
    private final ConnectionEventListener disposeSecretBoxOpenerOnDisconnectedListener;
    private SecretBoxOpener secretBoxOpener;
    private final SecretBoxOpenerFactory secretBoxOpenerFactory;

    public PrivateEncryptedChannelImpl(InternalConnection connection, String channelName, ChannelAuthorizer channelAuthorizer, Factory factory, SecretBoxOpenerFactory secretBoxOpenerFactory) {
        super(channelName, factory);
        this.disposeSecretBoxOpenerOnDisconnectedListener = new ConnectionEventListener() { // from class: com.pusher.client.channel.impl.PrivateEncryptedChannelImpl.1
            @Override // com.pusher.client.connection.ConnectionEventListener
            public void onConnectionStateChange(ConnectionStateChange change) {
                PrivateEncryptedChannelImpl.this.disposeSecretBoxOpener();
            }

            @Override // com.pusher.client.connection.ConnectionEventListener
            public void onError(String message, String code, Exception e) {
            }
        };
        this.connection = connection;
        this.channelAuthorizer = channelAuthorizer;
        this.secretBoxOpenerFactory = secretBoxOpenerFactory;
    }

    @Override // com.pusher.client.channel.impl.BaseChannel, com.pusher.client.channel.Channel
    public void bind(String eventName, SubscriptionEventListener listener) {
        if (!(listener instanceof PrivateEncryptedChannelEventListener)) {
            throw new IllegalArgumentException("Only instances of PrivateEncryptedChannelEventListener can be bound to a private encrypted channel");
        }
        super.bind(eventName, listener);
    }

    @Override // com.pusher.client.channel.impl.BaseChannel, com.pusher.client.channel.impl.InternalChannel
    public String toSubscribeMessage() {
        return this.GSON.toJson(new SubscribeMessage(this.name, authenticate(), null));
    }

    private String authenticate() {
        try {
            AuthResponse authResponse = (AuthResponse) this.GSON.fromJson(getAuthorizationResponse(), AuthResponse.class);
            if (authResponse.getAuth() == null || authResponse.getSharedSecret() == null) {
                throw new AuthorizationFailureException("Didn't receive all the fields expected from the ChannelAuthorizer, expected an auth and shared_secret.");
            }
            createSecretBoxOpener(Base64.decode(authResponse.getSharedSecret()));
            return authResponse.getAuth();
        } catch (JsonSyntaxException e) {
            throw new AuthorizationFailureException("Unable to parse response from Authorizer");
        }
    }

    private void createSecretBoxOpener(byte[] key) {
        this.secretBoxOpener = this.secretBoxOpenerFactory.create(key);
        setListenerToDisposeSecretBoxOpenerOnDisconnected();
    }

    private void setListenerToDisposeSecretBoxOpenerOnDisconnected() {
        this.connection.bind(ConnectionState.DISCONNECTED, this.disposeSecretBoxOpenerOnDisconnectedListener);
    }

    @Override // com.pusher.client.channel.impl.BaseChannel, com.pusher.client.channel.impl.InternalChannel
    public void updateState(ChannelState state) {
        super.updateState(state);
        if (state == ChannelState.UNSUBSCRIBED) {
            disposeSecretBoxOpener();
        }
    }

    @Override // com.pusher.client.channel.impl.BaseChannel, com.pusher.client.channel.impl.InternalChannel
    public void handleEvent(PusherEvent event) {
        try {
            super.handleEvent(decryptMessage(event));
        } catch (AuthenticityException e) {
            disposeSecretBoxOpener();
            authenticate();
            try {
                super.handleEvent(decryptMessage(event));
            } catch (AuthenticityException e2) {
                notifyListenersOfDecryptFailure(event.getEventName(), "Failed to decrypt message.");
            }
        }
    }

    private void notifyListenersOfDecryptFailure(String event, String reason) {
        Set<SubscriptionEventListener> listeners = getInterestedListeners(event);
        if (listeners != null) {
            for (SubscriptionEventListener listener : listeners) {
                ((PrivateEncryptedChannelEventListener) listener).onDecryptionFailure(event, reason);
            }
        }
    }

    private PusherEvent decryptMessage(PusherEvent event) {
        String decryptedData = "{}";
        if (!event.getData().equals("{}")) {
            EncryptedReceivedData encryptedReceivedData = (EncryptedReceivedData) this.GSON.fromJson(event.getData(), EncryptedReceivedData.class);
            decryptedData = this.secretBoxOpener.open(encryptedReceivedData.getCiphertext(), encryptedReceivedData.getNonce());
        }
        return new PusherEvent(event.getEventName(), event.getChannelName(), event.getUserId(), decryptedData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disposeSecretBoxOpener() {
        if (this.secretBoxOpener != null) {
            this.secretBoxOpener.clearKey();
            this.secretBoxOpener = null;
            removeListenerToDisposeSecretBoxOpenerOnDisconnected();
        }
    }

    private void removeListenerToDisposeSecretBoxOpenerOnDisconnected() {
        this.connection.unbind(ConnectionState.DISCONNECTED, this.disposeSecretBoxOpenerOnDisconnectedListener);
    }

    private String getAuthorizationResponse() {
        String socketId = this.connection.getSocketId();
        return this.channelAuthorizer.authorize(getName(), socketId);
    }

    @Override // com.pusher.client.channel.impl.ChannelImpl
    protected String[] getDisallowedNameExpressions() {
        return new String[]{"^(?!private-encrypted-).*"};
    }

    @Override // com.pusher.client.channel.impl.ChannelImpl, com.pusher.client.channel.impl.BaseChannel
    public String toString() {
        return String.format("[Private Encrypted Channel: name=%s]", this.name);
    }
}
