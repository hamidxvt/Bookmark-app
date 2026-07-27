package com.pusher.client.channel;

import com.google.gson.Gson;

/* loaded from: classes17.dex */
public class User {
    private static final Gson GSON = new Gson();
    private final String id;
    private final String jsonData;

    public User(String id, String jsonData) {
        this.id = id;
        this.jsonData = jsonData;
    }

    public String getId() {
        return this.id;
    }

    public String getInfo() {
        return this.jsonData;
    }

    public <V> V getInfo(Class<V> cls) {
        return (V) GSON.fromJson(this.jsonData, (Class) cls);
    }

    public String toString() {
        return String.format("[User id=%s, data=%s]", this.id, this.jsonData);
    }

    public int hashCode() {
        return this.id.hashCode() + (this.jsonData != null ? this.jsonData.hashCode() : 0);
    }

    public boolean equals(Object other) {
        if (!(other instanceof User)) {
            return false;
        }
        User otherUser = (User) other;
        return getId().equals(otherUser.getId()) && getInfo().equals(otherUser.getInfo());
    }
}
