package com.pusher.client.connection;

import java.util.logging.Logger;

/* loaded from: classes17.dex */
public class ConnectionStateChange {
    private static final Logger log = Logger.getLogger(ConnectionStateChange.class.getName());
    private final ConnectionState currentState;
    private final ConnectionState previousState;

    public ConnectionStateChange(ConnectionState previousState, ConnectionState currentState) {
        if (previousState == currentState) {
            log.fine("Attempted to create an connection state update where both previous and current state are: " + currentState);
        }
        this.previousState = previousState;
        this.currentState = currentState;
    }

    public ConnectionState getPreviousState() {
        return this.previousState;
    }

    public ConnectionState getCurrentState() {
        return this.currentState;
    }

    public int hashCode() {
        return this.previousState.hashCode() + this.currentState.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ConnectionStateChange)) {
            return false;
        }
        ConnectionStateChange other = (ConnectionStateChange) obj;
        return this.currentState == other.currentState && this.previousState == other.previousState;
    }
}
