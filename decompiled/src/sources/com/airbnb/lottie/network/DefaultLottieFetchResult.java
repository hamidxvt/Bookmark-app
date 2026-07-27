package com.airbnb.lottie.network;

import com.airbnb.lottie.utils.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes.dex */
public class DefaultLottieFetchResult implements LottieFetchResult {
    private final HttpURLConnection connection;

    public DefaultLottieFetchResult(HttpURLConnection connection) {
        this.connection = connection;
    }

    @Override // com.airbnb.lottie.network.LottieFetchResult
    public boolean isSuccessful() {
        try {
            return this.connection.getResponseCode() / 100 == 2;
        } catch (IOException e) {
            return false;
        }
    }

    @Override // com.airbnb.lottie.network.LottieFetchResult
    public InputStream bodyByteStream() throws IOException {
        return this.connection.getInputStream();
    }

    @Override // com.airbnb.lottie.network.LottieFetchResult
    public String contentType() {
        return this.connection.getContentType();
    }

    @Override // com.airbnb.lottie.network.LottieFetchResult
    public String error() {
        try {
            if (isSuccessful()) {
                return null;
            }
            return "Unable to fetch " + this.connection.getURL() + ". Failed with " + this.connection.getResponseCode() + StringUtils.LF + getErrorFromConnection(this.connection);
        } catch (IOException e) {
            Logger.warning("get error failed ", e);
            return e.getMessage();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.connection.disconnect();
    }

    private String getErrorFromConnection(HttpURLConnection connection) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        StringBuilder error = new StringBuilder();
        while (true) {
            try {
                String line = r.readLine();
                if (line != null) {
                    error.append(line).append('\n');
                } else {
                    try {
                        break;
                    } catch (Exception e) {
                    }
                }
            } finally {
                try {
                    r.close();
                } catch (Exception e2) {
                }
            }
        }
        return error.toString();
    }
}
