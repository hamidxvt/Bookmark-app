package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ingenious.androidbookmarksalesupgrade.adapter.SampleBookAdapter;
import com.ingenious.androidbookmarksalesupgrade.model.MonthModel;
import com.ingenious.androidbookmarksalesupgrade.model.SampleBookModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: VisitSamplesFragment.kt */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/fragment/VisitSamplesFragment$fetchSampleBooks$1", "Lokhttp3/Callback;", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class VisitSamplesFragment$fetchSampleBooks$1 implements Callback {
    final /* synthetic */ VisitSamplesFragment this$0;

    VisitSamplesFragment$fetchSampleBooks$1(VisitSamplesFragment $receiver) {
        this.this$0 = $receiver;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException e) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(e, "e");
        Log.e("API_ERROR", "Failed: " + e.getMessage());
        FragmentActivity requireActivity = this.this$0.requireActivity();
        final VisitSamplesFragment visitSamplesFragment = this.this$0;
        requireActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitSamplesFragment$fetchSampleBooks$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                VisitSamplesFragment$fetchSampleBooks$1.onFailure$lambda$0(VisitSamplesFragment.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFailure$lambda$0(VisitSamplesFragment this$0) {
        this$0.getBinding().sampleListRv.setVisibility(8);
        this$0.getBinding().adoptionLinear.setVisibility(0);
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) {
        String body;
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        ResponseBody body2 = response.body();
        if (body2 == null || (body = body2.string()) == null) {
            body = "";
        }
        Log.d("API_RESPONSE", body);
        if (response.isSuccessful()) {
            if (body.length() > 0) {
                try {
                    JSONObject jsonObject = new JSONObject(body);
                    JSONArray sampleArray = jsonObject.optJSONArray("sample");
                    if (sampleArray == null) {
                        try {
                            sampleArray = new JSONArray();
                        } catch (Exception e) {
                            e = e;
                            String body3 = e.getMessage();
                            Log.e("PARSE_ERROR", String.valueOf(body3));
                            FragmentActivity requireActivity = this.this$0.requireActivity();
                            final VisitSamplesFragment visitSamplesFragment = this.this$0;
                            requireActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitSamplesFragment$fetchSampleBooks$1$$ExternalSyntheticLambda2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    VisitSamplesFragment$fetchSampleBooks$1.onResponse$lambda$2(VisitSamplesFragment.this);
                                }
                            });
                            return;
                        }
                    }
                    final List monthList = new ArrayList();
                    int i = 0;
                    int length = sampleArray.length();
                    while (i < length) {
                        JSONObject monthObj = sampleArray.getJSONObject(i);
                        String month = monthObj.optString("month");
                        JSONArray productsArray = monthObj.optJSONArray("products");
                        if (productsArray == null) {
                            productsArray = new JSONArray();
                        }
                        List products = new ArrayList();
                        int j = 0;
                        int length2 = productsArray.length();
                        while (j < length2) {
                            JSONObject p = productsArray.getJSONObject(j);
                            String body4 = body;
                            try {
                                String optString = p.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                                Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
                                JSONObject jsonObject2 = jsonObject;
                                String optString2 = p.optString("image");
                                Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
                                JSONArray sampleArray2 = sampleArray;
                                String optString3 = p.optString("grade_title");
                                Intrinsics.checkNotNullExpressionValue(optString3, "optString(...)");
                                int i2 = length;
                                String optString4 = p.optString("subject_name");
                                Intrinsics.checkNotNullExpressionValue(optString4, "optString(...)");
                                JSONObject monthObj2 = monthObj;
                                String optString5 = p.optString("total_quantity");
                                Intrinsics.checkNotNullExpressionValue(optString5, "optString(...)");
                                products.add(new SampleBookModel(optString, optString2, optString3, optString4, optString5));
                                j++;
                                body = body4;
                                jsonObject = jsonObject2;
                                sampleArray = sampleArray2;
                                length = i2;
                                monthObj = monthObj2;
                            } catch (Exception e2) {
                                e = e2;
                                String body32 = e.getMessage();
                                Log.e("PARSE_ERROR", String.valueOf(body32));
                                FragmentActivity requireActivity2 = this.this$0.requireActivity();
                                final VisitSamplesFragment visitSamplesFragment2 = this.this$0;
                                requireActivity2.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitSamplesFragment$fetchSampleBooks$1$$ExternalSyntheticLambda2
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        VisitSamplesFragment$fetchSampleBooks$1.onResponse$lambda$2(VisitSamplesFragment.this);
                                    }
                                });
                                return;
                            }
                        }
                        Intrinsics.checkNotNull(month);
                        monthList.add(new MonthModel(month, products));
                        i++;
                        body = body;
                        jsonObject = jsonObject;
                        sampleArray = sampleArray;
                        length = length;
                    }
                    FragmentActivity requireActivity3 = this.this$0.requireActivity();
                    final VisitSamplesFragment visitSamplesFragment3 = this.this$0;
                    requireActivity3.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitSamplesFragment$fetchSampleBooks$1$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            VisitSamplesFragment$fetchSampleBooks$1.onResponse$lambda$1(monthList, visitSamplesFragment3);
                        }
                    });
                    return;
                } catch (Exception e3) {
                    e = e3;
                }
            }
        }
        FragmentActivity requireActivity4 = this.this$0.requireActivity();
        final VisitSamplesFragment visitSamplesFragment4 = this.this$0;
        requireActivity4.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitSamplesFragment$fetchSampleBooks$1$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                VisitSamplesFragment$fetchSampleBooks$1.onResponse$lambda$3(VisitSamplesFragment.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$1(List $monthList, VisitSamplesFragment this$0) {
        SampleBookAdapter sampleBookAdapter;
        if (!$monthList.isEmpty()) {
            this$0.getBinding().sampleListRv.setVisibility(0);
            this$0.getBinding().adoptionLinear.setVisibility(8);
            sampleBookAdapter = this$0.bookAdapter;
            if (sampleBookAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bookAdapter");
                sampleBookAdapter = null;
            }
            sampleBookAdapter.setData($monthList);
            return;
        }
        this$0.getBinding().sampleListRv.setVisibility(8);
        this$0.getBinding().adoptionLinear.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$2(VisitSamplesFragment this$0) {
        this$0.getBinding().sampleListRv.setVisibility(8);
        this$0.getBinding().adoptionLinear.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$3(VisitSamplesFragment this$0) {
        this$0.getBinding().sampleListRv.setVisibility(8);
        this$0.getBinding().adoptionLinear.setVisibility(0);
    }
}
