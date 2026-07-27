package com.ingenious.androidbookmarksalesupgrade.utils;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;

/* compiled from: LocationService.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.utils.LocationService$sendLocationToApi$1", f = "LocationService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes10.dex */
final class LocationService$sendLocationToApi$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $lat;
    final /* synthetic */ double $lng;
    final /* synthetic */ String $token;
    int label;
    final /* synthetic */ LocationService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LocationService$sendLocationToApi$1(String str, LocationService locationService, double d, double d2, Continuation<? super LocationService$sendLocationToApi$1> continuation) {
        super(2, continuation);
        this.$token = str;
        this.this$0 = locationService;
        this.$lat = d;
        this.$lng = d2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LocationService$sendLocationToApi$1(this.$token, this.this$0, this.$lat, this.$lng, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LocationService$sendLocationToApi$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        OkHttpClient okHttpClient;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                try {
                    JSONObject json = new JSONObject();
                    double d = this.$lat;
                    double d2 = this.$lng;
                    json.put("latitude", String.valueOf(d));
                    json.put("longitude", String.valueOf(d2));
                    Log.d("LocationService", "Sending to API: " + json);
                    RequestBody.Companion companion = RequestBody.INSTANCE;
                    String jSONObject = json.toString();
                    Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
                    RequestBody requestBody = companion.create(jSONObject, MediaType.INSTANCE.get("application/json"));
                    Request request = new Request.Builder().url("https://staging.bookmark.services/api/updatelocation").post(requestBody).addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + this.$token).addHeader(HttpHeaders.ACCEPT, "application/json").build();
                    okHttpClient = this.this$0.httpClient;
                    okHttpClient.newCall(request).enqueue(new Callback() { // from class: com.ingenious.androidbookmarksalesupgrade.utils.LocationService$sendLocationToApi$1.1
                        @Override // okhttp3.Callback
                        public void onFailure(Call call, IOException e) {
                            Intrinsics.checkNotNullParameter(call, "call");
                            Intrinsics.checkNotNullParameter(e, "e");
                            Log.e("LocationService", "API call failed: " + e.getMessage());
                        }

                        @Override // okhttp3.Callback
                        public void onResponse(Call call, Response response) {
                            Intrinsics.checkNotNullParameter(call, "call");
                            Intrinsics.checkNotNullParameter(response, "response");
                            ResponseBody body = response.body();
                            String body2 = body != null ? body.string() : null;
                            Log.d("LocationService", "API response: " + response.code() + " - " + body2);
                            response.close();
                            if (!response.isSuccessful()) {
                                Log.e("LocationService", "API error " + response.code() + ": " + body2);
                            }
                        }
                    });
                } catch (Exception e) {
                    Log.e("LocationService", "Error sending location: " + e.getMessage());
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
