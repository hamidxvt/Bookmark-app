package com.pusher.client.channel.impl;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.pusher.client.AuthorizationFailureException;
import com.pusher.client.ChannelAuthorizer;
import com.pusher.client.channel.ChannelEventListener;
import com.pusher.client.channel.PresenceChannel;
import com.pusher.client.channel.PresenceChannelEventListener;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.channel.User;
import com.pusher.client.channel.impl.message.ChannelData;
import com.pusher.client.channel.impl.message.PresenceMemberData;
import com.pusher.client.channel.impl.message.PresenceSubscriptionData;
import com.pusher.client.connection.impl.InternalConnection;
import com.pusher.client.util.Factory;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes17.dex */
public class PresenceChannelImpl extends PrivateChannelImpl implements PresenceChannel {
    private static final Gson GSON = new Gson();
    private static final String MEMBER_ADDED_EVENT = "pusher_internal:member_added";
    private static final String MEMBER_REMOVED_EVENT = "pusher_internal:member_removed";
    private final Map<String, User> idToUserMap;
    private String myUserID;

    public PresenceChannelImpl(InternalConnection connection, String channelName, ChannelAuthorizer channelAuthorizer, Factory factory) {
        super(connection, channelName, channelAuthorizer, factory);
        this.idToUserMap = Collections.synchronizedMap(new LinkedHashMap());
    }

    @Override // com.pusher.client.channel.PresenceChannel
    public Set<User> getUsers() {
        return new LinkedHashSet(this.idToUserMap.values());
    }

    @Override // com.pusher.client.channel.PresenceChannel
    public User getMe() {
        return this.idToUserMap.get(this.myUserID);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.pusher.client.channel.impl.BaseChannel, com.pusher.client.channel.impl.InternalChannel
    public void handleEvent(PusherEvent event) {
        char c;
        super.handleEvent(event);
        String eventName = event.getEventName();
        switch (eventName.hashCode()) {
            case -1034553308:
                if (eventName.equals("pusher_internal:subscription_succeeded")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -146725088:
                if (eventName.equals(MEMBER_REMOVED_EVENT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 489136064:
                if (eventName.equals(MEMBER_ADDED_EVENT)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                handleSubscriptionSuccessfulMessage(event);
                break;
            case 1:
                handleMemberAddedEvent(event);
                break;
            case 2:
                handleMemberRemovedEvent(event);
                break;
        }
    }

    @Override // com.pusher.client.channel.impl.PrivateChannelImpl, com.pusher.client.channel.impl.BaseChannel, com.pusher.client.channel.impl.InternalChannel
    public String toSubscribeMessage() {
        String msg = super.toSubscribeMessage();
        this.myUserID = extractUserIdFromChannelData(this.channelData);
        return msg;
    }

    @Override // com.pusher.client.channel.impl.PrivateChannelImpl, com.pusher.client.channel.impl.BaseChannel, com.pusher.client.channel.Channel
    public void bind(String eventName, SubscriptionEventListener listener) {
        if (!(listener instanceof PresenceChannelEventListener)) {
            throw new IllegalArgumentException("Only instances of PresenceChannelEventListener can be bound to a presence channel");
        }
        super.bind(eventName, listener);
    }

    @Override // com.pusher.client.channel.impl.PrivateChannelImpl, com.pusher.client.channel.impl.ChannelImpl
    protected String[] getDisallowedNameExpressions() {
        return new String[]{"^(?!presence-).*"};
    }

    @Override // com.pusher.client.channel.impl.PrivateChannelImpl, com.pusher.client.channel.impl.ChannelImpl, com.pusher.client.channel.impl.BaseChannel
    public String toString() {
        return String.format("[Presence Channel: name=%s]", this.name);
    }

    private void handleSubscriptionSuccessfulMessage(PusherEvent event) {
        ChannelEventListener listener = getEventListener();
        PresenceSubscriptionData presenceSubscriptionData = (PresenceSubscriptionData) GSON.fromJson(event.getData(), PresenceSubscriptionData.class);
        if (presenceSubscriptionData.presence == null) {
            if (listener != null) {
                listener.onError("Subscription failed: Presence data not found", null);
                return;
            }
            return;
        }
        List<String> ids = presenceSubscriptionData.getIds();
        Map<String, Object> hash = presenceSubscriptionData.getHash();
        if (ids != null && !ids.isEmpty()) {
            for (String id : ids) {
                String userData = hash.get(id) != null ? GSON.toJson(hash.get(id)) : null;
                User user = new User(id, userData);
                this.idToUserMap.put(id, user);
            }
        }
        if (listener != null) {
            PresenceChannelEventListener presenceListener = (PresenceChannelEventListener) listener;
            presenceListener.onUsersInformationReceived(getName(), getUsers());
        }
    }

    private void handleMemberAddedEvent(PusherEvent event) {
        PresenceMemberData memberData = (PresenceMemberData) GSON.fromJson(event.getData(), PresenceMemberData.class);
        String id = memberData.getId();
        String userData = memberData.getInfo() != null ? GSON.toJson(memberData.getInfo()) : null;
        User user = new User(id, userData);
        this.idToUserMap.put(id, user);
        ChannelEventListener listener = getEventListener();
        if (listener != null) {
            PresenceChannelEventListener presenceListener = (PresenceChannelEventListener) listener;
            presenceListener.userSubscribed(getName(), user);
        }
    }

    private void handleMemberRemovedEvent(PusherEvent event) {
        PresenceMemberData memberData = (PresenceMemberData) GSON.fromJson(event.getData(), PresenceMemberData.class);
        User user = this.idToUserMap.remove(memberData.getId());
        ChannelEventListener listener = getEventListener();
        if (listener != null) {
            PresenceChannelEventListener presenceListener = (PresenceChannelEventListener) listener;
            presenceListener.userUnsubscribed(getName(), user);
        }
    }

    public String extractUserIdFromChannelData(String channelDataString) {
        try {
            ChannelData data = (ChannelData) GSON.fromJson(channelDataString, ChannelData.class);
            if (data.getUserId() == null) {
                throw new AuthorizationFailureException("Invalid response from ChannelAuthorizer: no user_id key in channel_data object: " + channelDataString);
            }
            return data.getUserId();
        } catch (JsonSyntaxException e) {
            throw new AuthorizationFailureException("Invalid response from ChannelAuthorizer: unable to parse channel_data object: " + channelDataString, e);
        } catch (NullPointerException e2) {
            throw new AuthorizationFailureException("Invalid response from ChannelAuthorizer: no user_id key in channel_data object: " + channelDataString);
        }
    }
}
