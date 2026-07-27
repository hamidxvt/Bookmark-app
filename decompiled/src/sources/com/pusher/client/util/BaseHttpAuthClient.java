package com.pusher.client.util;

import androidx.webkit.ProxyConfig;
import com.google.common.net.HttpHeaders;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes17.dex */
abstract class BaseHttpAuthClient {
    private final URL endPoint;
    protected ConnectionFactory mConnectionFactory;
    private Map<String, String> mHeaders = new HashMap();

    protected abstract RuntimeException authFailureException(IOException iOException);

    protected abstract RuntimeException authFailureException(String str);

    public BaseHttpAuthClient(String endPoint) {
        try {
            this.endPoint = new URL(endPoint);
            this.mConnectionFactory = new UrlEncodedConnectionFactory();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Could not parse channel authorization end point into a valid URL", e);
        }
    }

    public BaseHttpAuthClient(String endPoint, ConnectionFactory connectionFactory) {
        try {
            this.endPoint = new URL(endPoint);
            this.mConnectionFactory = connectionFactory;
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Could not parse channel authorization end point into a valid URL", e);
        }
    }

    public void setHeaders(Map<String, String> headers) {
        this.mHeaders = headers;
    }

    public Boolean isSSL() {
        return Boolean.valueOf(this.endPoint.getProtocol().equals(ProxyConfig.MATCH_HTTPS));
    }

    protected String performAuthRequest() {
        HttpURLConnection connection;
        try {
            String body = this.mConnectionFactory.getBody();
            HashMap<String, String> defaultHeaders = new HashMap<>();
            defaultHeaders.put("Content-Type", this.mConnectionFactory.getContentType());
            defaultHeaders.put("charset", this.mConnectionFactory.getCharset());
            if (isSSL().booleanValue()) {
                connection = (HttpsURLConnection) this.endPoint.openConnection();
            } else {
                connection = (HttpURLConnection) this.endPoint.openConnection();
            }
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            defaultHeaders.putAll(this.mHeaders);
            defaultHeaders.put(HttpHeaders.CONTENT_LENGTH, "" + body.getBytes().length);
            for (String headerName : defaultHeaders.keySet()) {
                String headerValue = defaultHeaders.get(headerName);
                connection.setRequestProperty(headerName, headerValue);
            }
            connection.setUseCaches(false);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(body);
            wr.flush();
            wr.close();
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            while (true) {
                String line = rd.readLine();
                if (line == null) {
                    break;
                }
                response.append(line);
            }
            rd.close();
            int responseHttpStatus = connection.getResponseCode();
            if (responseHttpStatus != 200 && responseHttpStatus != 201) {
                throw authFailureException(response.toString());
            }
            return response.toString();
        } catch (IOException e) {
            throw authFailureException(e);
        }
    }
}
