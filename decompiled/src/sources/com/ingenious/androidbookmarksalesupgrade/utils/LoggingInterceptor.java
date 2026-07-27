package com.ingenious.androidbookmarksalesupgrade.utils;

import android.util.Log;
import com.ingenious.androidbookmarksalesupgrade.BuildConfig;
import java.io.IOException;
import java.util.Locale;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/* loaded from: classes10.dex */
public class LoggingInterceptor implements Interceptor {
    public static final String TAG = "API_LOG";

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (!BuildConfig.DEBUG) {
            return chain.proceed(request);
        }
        long startTime = System.nanoTime();
        logRequest(request);
        Response response = chain.proceed(request);
        long endTime = System.nanoTime();
        return logResponse(response, endTime - startTime);
    }

    private void logRequest(Request request) {
        Log.v(TAG, "------------- REQUEST -------------");
        Log.v(TAG, "URL      : " + request.url());
        Log.v(TAG, "Method   : " + request.method());
        Log.v(TAG, "Headers  : " + request.headers());
        if (request.body() != null) {
            MediaType contentType = request.body().getContentType();
            if (contentType != null && contentType.type().equalsIgnoreCase("multipart")) {
                Log.v(TAG, "Body     : Multipart request (file/image not logged)");
                return;
            } else {
                Log.v(TAG, "Body     : " + bodyToString(request));
                return;
            }
        }
        Log.v(TAG, "Body     : No Body");
    }

    private Response logResponse(Response response, long durationNs) throws IOException {
        ResponseBody responseBody = response.body();
        String bodyString = "";
        if (responseBody != null) {
            bodyString = responseBody.string();
        }
        Log.v(TAG, "------------- RESPONSE ------------");
        Log.v(TAG, "URL      : " + response.request().url());
        Log.v(TAG, String.format(Locale.US, "Time     : %.1f ms", Double.valueOf(durationNs / 1000000.0d)));
        Log.v(TAG, "Code     : " + response.code());
        Log.v(TAG, "Headers  : " + response.headers());
        Log.v(TAG, "Body     : " + bodyString);
        return response.newBuilder().body(ResponseBody.create(responseBody != null ? responseBody.get$contentType() : null, bodyString)).build();
    }

    private String bodyToString(Request request) {
        try {
            Request copy = request.newBuilder().build();
            Buffer buffer = new Buffer();
            if (copy.body() != null) {
                copy.body().writeTo(buffer);
                return buffer.readUtf8();
            }
            return "No Body";
        } catch (Exception e) {
            return "Unable to read body";
        }
    }
}
