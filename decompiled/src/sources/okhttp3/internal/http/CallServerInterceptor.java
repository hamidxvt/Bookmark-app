package okhttp3.internal.http;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.net.ProtocolException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.http2.ConnectionShutdownException;
import okio.BufferedSink;
import okio.Okio;

/* compiled from: CallServerInterceptor.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lokhttp3/internal/http/CallServerInterceptor;", "Lokhttp3/Interceptor;", "forWebSocket", "", "(Z)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "shouldIgnoreAndWaitForRealResponse", "code", "", "okhttp"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    public CallServerInterceptor(boolean forWebSocket) {
        this.forWebSocket = forWebSocket;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x01f8  */
    @Override // okhttp3.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Response intercept(Interceptor.Chain chain) throws IOException {
        IOException sendRequestException;
        Intrinsics.checkNotNullParameter(chain, "chain");
        RealInterceptorChain realChain = (RealInterceptorChain) chain;
        Exchange exchange = realChain.getExchange();
        Intrinsics.checkNotNull(exchange);
        Request request = realChain.getRequest();
        RequestBody requestBody = request.body();
        long sentRequestMillis = System.currentTimeMillis();
        boolean invokeStartEvent = true;
        Response.Builder responseBuilder = null;
        IOException sendRequestException2 = null;
        try {
            exchange.writeRequestHeaders(request);
            if (!HttpMethod.permitsRequestBody(request.method()) || requestBody == null) {
                exchange.noRequestBody();
            } else {
                if (StringsKt.equals("100-continue", request.header(com.google.common.net.HttpHeaders.EXPECT), true)) {
                    exchange.flushRequest();
                    responseBuilder = exchange.readResponseHeaders(true);
                    exchange.responseHeadersStart();
                    invokeStartEvent = false;
                }
                if (responseBuilder != null) {
                    exchange.noRequestBody();
                    if (!exchange.getConnection().isMultiplexed$okhttp()) {
                        exchange.noNewExchangesOnConnection();
                    }
                } else if (requestBody.isDuplex()) {
                    exchange.flushRequest();
                    requestBody.writeTo(Okio.buffer(exchange.createRequestBody(request, true)));
                } else {
                    BufferedSink bufferedRequestBody = Okio.buffer(exchange.createRequestBody(request, false));
                    requestBody.writeTo(bufferedRequestBody);
                    bufferedRequestBody.close();
                }
            }
            if (requestBody == null || !requestBody.isDuplex()) {
                exchange.finishRequest();
            }
        } catch (IOException e) {
            if (e instanceof ConnectionShutdownException) {
                throw e;
            }
            if (!exchange.getHasFailure()) {
                throw e;
            }
            sendRequestException2 = e;
        }
        if (responseBuilder == null) {
            try {
                Response.Builder readResponseHeaders = exchange.readResponseHeaders(false);
                Intrinsics.checkNotNull(readResponseHeaders);
                responseBuilder = readResponseHeaders;
                if (invokeStartEvent) {
                    exchange.responseHeadersStart();
                    invokeStartEvent = false;
                }
            } catch (IOException e2) {
                e = e2;
                sendRequestException = sendRequestException2;
                if (sendRequestException != null) {
                }
            }
        }
        try {
            Response.Builder responseBuilder2 = responseBuilder;
            sendRequestException = sendRequestException2;
            try {
                Response response = responseBuilder.request(request).handshake(exchange.getConnection().getHandshake()).sentRequestAtMillis(sentRequestMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
                int code = response.code();
                if (shouldIgnoreAndWaitForRealResponse(code)) {
                    Response.Builder responseBuilder3 = exchange.readResponseHeaders(false);
                    Intrinsics.checkNotNull(responseBuilder3);
                    if (invokeStartEvent) {
                        try {
                            exchange.responseHeadersStart();
                        } catch (IOException e3) {
                            e = e3;
                            if (sendRequestException != null) {
                            }
                        }
                    }
                    try {
                    } catch (IOException e4) {
                        e = e4;
                    }
                    try {
                        response = responseBuilder3.request(request).handshake(exchange.getConnection().getHandshake()).sentRequestAtMillis(sentRequestMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
                        code = response.code();
                        responseBuilder2 = responseBuilder3;
                    } catch (IOException e5) {
                        e = e5;
                        if (sendRequestException != null) {
                        }
                    }
                }
                try {
                    exchange.responseHeadersEnd(response);
                    Response response2 = (this.forWebSocket && code == 101) ? response.newBuilder().body(Util.EMPTY_RESPONSE).build() : response.newBuilder().body(exchange.openResponseBody(response)).build();
                    if (StringsKt.equals("close", response2.request().header(com.google.common.net.HttpHeaders.CONNECTION), true) || StringsKt.equals("close", Response.header$default(response2, com.google.common.net.HttpHeaders.CONNECTION, null, 2, null), true)) {
                        exchange.noNewExchangesOnConnection();
                    }
                    if (code == 204 || code == 205) {
                        ResponseBody body = response2.body();
                        if ((body != null ? body.getContentLength() : -1L) > 0) {
                            StringBuilder append = new StringBuilder().append("HTTP ").append(code).append(" had non-zero Content-Length: ");
                            ResponseBody body2 = response2.body();
                            throw new ProtocolException(append.append(body2 != null ? Long.valueOf(body2.getContentLength()) : null).toString());
                        }
                    }
                    return response2;
                } catch (IOException e6) {
                    e = e6;
                    if (sendRequestException != null) {
                        throw e;
                    }
                    ExceptionsKt.addSuppressed(sendRequestException, e);
                    throw sendRequestException;
                }
            } catch (IOException e7) {
                e = e7;
            }
        } catch (IOException e8) {
            e = e8;
            sendRequestException = sendRequestException2;
        }
    }

    private final boolean shouldIgnoreAndWaitForRealResponse(int code) {
        if (code == 100) {
            return true;
        }
        return 102 <= code && code < 200;
    }
}
