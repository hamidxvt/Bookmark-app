package com.pusher.client.channel;

import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Map;

/* loaded from: classes17.dex */
public class PusherEvent {
    private JsonObject jsonObject;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public Object getProperty(String key) {
        char c;
        switch (key.hashCode()) {
            case -147132913:
                if (key.equals("user_id")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3076010:
                if (key.equals(Constants.ScionAnalytics.MessageType.DATA_MESSAGE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 96891546:
                if (key.equals(NotificationCompat.CATEGORY_EVENT)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 738950403:
                if (key.equals("channel")) {
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
                return getUserId();
            case 1:
                return getChannelName();
            case 2:
                return this.jsonObject.get(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
            case 3:
                return getEventName();
            default:
                return null;
        }
    }

    public String getUserId() {
        if (this.jsonObject.has("user_id")) {
            return this.jsonObject.get("user_id").getAsString();
        }
        return null;
    }

    public String getChannelName() {
        if (this.jsonObject.has("channel")) {
            return this.jsonObject.get("channel").getAsString();
        }
        return null;
    }

    public String getEventName() {
        if (this.jsonObject.has(NotificationCompat.CATEGORY_EVENT)) {
            return this.jsonObject.get(NotificationCompat.CATEGORY_EVENT).getAsString();
        }
        return null;
    }

    public String getData() {
        JsonElement data = this.jsonObject.get(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        if (data.isJsonPrimitive()) {
            return data.getAsString();
        }
        Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
        return gson.toJson(data);
    }

    public String toString() {
        return toJson();
    }

    public PusherEvent(String event, String channel, String userId, String data) {
        this.jsonObject = new JsonObject();
        this.jsonObject.addProperty(NotificationCompat.CATEGORY_EVENT, event);
        this.jsonObject.addProperty("channel", channel);
        this.jsonObject.addProperty("userId", userId);
        this.jsonObject.addProperty(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, data);
    }

    public PusherEvent(String event, String channel, String userId, Map<String, Object> data) {
        this(event, channel, userId, new Gson().toJson(data));
    }

    public PusherEvent(JsonObject jsonObject) {
        this.jsonObject = new JsonObject();
        this.jsonObject = jsonObject;
    }

    public String toJson() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson((JsonElement) this.jsonObject);
    }

    public static PusherEvent fromJson(String json) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return new PusherEvent((JsonObject) gson.fromJson(json, JsonObject.class));
    }
}
