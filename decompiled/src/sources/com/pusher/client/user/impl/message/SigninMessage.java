package com.pusher.client.user.impl.message;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes17.dex */
public class SigninMessage {
    private final String event = "pusher:signin";
    private final Map<String, String> data = new HashMap();

    public SigninMessage(String auth, String userData) {
        this.data.put("auth", auth);
        this.data.put("user_data", userData);
    }
}
