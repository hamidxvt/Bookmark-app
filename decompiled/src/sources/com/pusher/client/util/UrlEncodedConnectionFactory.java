package com.pusher.client.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes17.dex */
public class UrlEncodedConnectionFactory extends ConnectionFactory {
    private Map<String, String> mQueryStringParameters;

    public UrlEncodedConnectionFactory() {
        this.mQueryStringParameters = new HashMap();
    }

    public UrlEncodedConnectionFactory(Map<String, String> queryStringParameters) {
        this.mQueryStringParameters = new HashMap();
        this.mQueryStringParameters = queryStringParameters;
    }

    @Override // com.pusher.client.util.ConnectionFactory
    public String getCharset() {
        return "UTF-8";
    }

    @Override // com.pusher.client.util.ConnectionFactory
    public String getContentType() {
        return "application/x-www-form-urlencoded";
    }

    @Override // com.pusher.client.util.ConnectionFactory
    public String getBody() {
        StringBuilder urlParameters = new StringBuilder();
        try {
            urlParameters.append("socket_id=").append(URLEncoder.encode(getSocketId(), getCharset()));
            if (getChannelName() != null) {
                urlParameters.append("&channel_name=").append(URLEncoder.encode(getChannelName(), getCharset()));
            }
            for (String parameterName : this.mQueryStringParameters.keySet()) {
                urlParameters.append("&").append(parameterName).append("=");
                urlParameters.append(URLEncoder.encode(this.mQueryStringParameters.get(parameterName), getCharset()));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return urlParameters.toString();
    }
}
