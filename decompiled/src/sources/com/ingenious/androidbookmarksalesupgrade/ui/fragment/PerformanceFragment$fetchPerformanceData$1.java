package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.google.firebase.messaging.Constants;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentPerformanceBinding;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: PerformanceFragment.kt */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/fragment/PerformanceFragment$fetchPerformanceData$1", "Lokhttp3/Callback;", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class PerformanceFragment$fetchPerformanceData$1 implements Callback {
    final /* synthetic */ PerformanceFragment this$0;

    PerformanceFragment$fetchPerformanceData$1(PerformanceFragment $receiver) {
        this.this$0 = $receiver;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException e) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(e, "e");
        Log.e("API_ERROR", "Request failed: " + e.getMessage());
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) {
        String str;
        String str2 = "completed";
        String str3 = "missed";
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        ResponseBody body = response.body();
        String body2 = body != null ? body.string() : null;
        if (response.isSuccessful()) {
            String str4 = body2;
            if (!(str4 == null || str4.length() == 0)) {
                try {
                    JSONObject root = new JSONObject(body2);
                    JSONObject data = root.getJSONObject(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
                    Log.i("TAG", "Performance: " + data);
                    JSONObject visitsSummary = data.getJSONObject("visitsSummary");
                    final int totalVisits = visitsSummary.getInt("total");
                    final int missed = visitsSummary.getInt("missed");
                    final double avgDaily = visitsSummary.getDouble("avgDaily");
                    JSONObject targets = data.getJSONObject("targets");
                    JSONObject weekly = targets.getJSONObject("weeklyVisit");
                    JSONObject yearly = targets.getJSONObject("yearlySample");
                    final int weeklyCompleted = weekly.getInt("completed");
                    final int weeklyTarget = weekly.getInt(TypedValues.AttributesType.S_TARGET);
                    final int yearlyUsed = yearly.getInt("used");
                    final int yearlyTarget = yearly.getInt(TypedValues.AttributesType.S_TARGET);
                    final int weeklyProgress = weeklyTarget > 0 ? (weeklyCompleted * 100) / weeklyTarget : 0;
                    final int yearlyProgress = yearlyTarget > 0 ? (yearlyUsed * 100) / yearlyTarget : 0;
                    JSONArray visitsOverview = data.getJSONArray("visitsOverview");
                    ArrayList completedEntries = new ArrayList();
                    ArrayList missedEntries = new ArrayList();
                    ArrayList dayLabels = new ArrayList();
                    Map dayMap = MapsKt.mapOf(TuplesKt.to("Monday", "M"), TuplesKt.to("Mon", "M"), TuplesKt.to("Tuesday", "T"), TuplesKt.to("Tue", "T"), TuplesKt.to("Wednesday", "W"), TuplesKt.to("Wed", "W"), TuplesKt.to("Thursday", "T"), TuplesKt.to("Thu", "T"), TuplesKt.to("Friday", "F"), TuplesKt.to("Fri", "F"), TuplesKt.to("Saturday", "S"), TuplesKt.to("Sat", "S"), TuplesKt.to("Sunday", "S"), TuplesKt.to("Sun", "S"));
                    int i = 0;
                    int length = visitsOverview.length();
                    while (true) {
                        str = "day";
                        if (i >= length) {
                            break;
                        }
                        JSONObject item = visitsOverview.getJSONObject(i);
                        String day = item.getString("day");
                        int completed = item.getInt(str2);
                        int missed2 = item.getInt(str3);
                        int i2 = length;
                        String str5 = str2;
                        String str6 = str3;
                        ArrayList completedEntries2 = completedEntries;
                        completedEntries2.add(new Entry(i, completed));
                        ArrayList missedEntries2 = missedEntries;
                        missedEntries2.add(new Entry(i, missed2));
                        String str7 = (String) dayMap.get(day);
                        if (str7 == null) {
                            str7 = day;
                        }
                        ArrayList dayLabels2 = dayLabels;
                        dayLabels2.add(str7);
                        i++;
                        completedEntries = completedEntries2;
                        missedEntries = missedEntries2;
                        dayLabels = dayLabels2;
                        length = i2;
                        str2 = str5;
                        str3 = str6;
                    }
                    final ArrayList completedEntries3 = completedEntries;
                    final ArrayList missedEntries3 = missedEntries;
                    final ArrayList dayLabels3 = dayLabels;
                    JSONObject newCustomers = data.getJSONObject("newCustomers");
                    JSONArray customerChart = newCustomers.getJSONArray("chart");
                    final int totalCustomer = newCustomers.getInt("total");
                    final int avg = newCustomers.getInt("avg");
                    final ArrayList barEntries = new ArrayList();
                    final ArrayList customerDayLabels = new ArrayList();
                    int i3 = 0;
                    int length2 = customerChart.length();
                    while (i3 < length2) {
                        JSONObject obj = customerChart.getJSONObject(i3);
                        int i4 = length2;
                        String day2 = obj.getString(str);
                        JSONArray customerChart2 = customerChart;
                        int customers = obj.getInt("customers");
                        String str8 = str;
                        JSONArray visitsOverview2 = visitsOverview;
                        barEntries.add(new BarEntry(i3, customers));
                        String str9 = (String) dayMap.get(day2);
                        customerDayLabels.add(str9 == null ? day2 : str9);
                        i3++;
                        length2 = i4;
                        str = str8;
                        customerChart = customerChart2;
                        visitsOverview = visitsOverview2;
                    }
                    FragmentActivity requireActivity = this.this$0.requireActivity();
                    final PerformanceFragment performanceFragment = this.this$0;
                    requireActivity.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.PerformanceFragment$fetchPerformanceData$1$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            PerformanceFragment$fetchPerformanceData$1.onResponse$lambda$0(PerformanceFragment.this, totalVisits, missed, avgDaily, weeklyProgress, weeklyCompleted, weeklyTarget, yearlyProgress, yearlyUsed, yearlyTarget, completedEntries3, missedEntries3, dayLabels3, barEntries, customerDayLabels, totalCustomer, avg);
                        }
                    });
                    return;
                } catch (Exception e) {
                    Log.e("JSON_ERROR", "Parse error: " + e.getMessage());
                    Log.e("JSON_ERROR", "Response: " + body2);
                    return;
                }
            }
        }
        Log.e("API_ERROR", "Invalid response: " + response.code() + StringUtils.LF + body2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResponse$lambda$0(PerformanceFragment this$0, int $totalVisits, int $missed, double $avgDaily, int $weeklyProgress, int $weeklyCompleted, int $weeklyTarget, int $yearlyProgress, int $yearlyUsed, int $yearlyTarget, ArrayList $completedEntries, ArrayList $missedEntries, ArrayList $dayLabels, ArrayList $barEntries, ArrayList $customerDayLabels, int $totalCustomer, int $avg) {
        FragmentPerformanceBinding fragmentPerformanceBinding;
        FragmentPerformanceBinding fragmentPerformanceBinding2;
        FragmentPerformanceBinding fragmentPerformanceBinding3;
        FragmentPerformanceBinding fragmentPerformanceBinding4;
        FragmentPerformanceBinding fragmentPerformanceBinding5;
        FragmentPerformanceBinding fragmentPerformanceBinding6;
        FragmentPerformanceBinding fragmentPerformanceBinding7;
        FragmentPerformanceBinding fragmentPerformanceBinding8;
        FragmentPerformanceBinding fragmentPerformanceBinding9;
        FragmentPerformanceBinding fragmentPerformanceBinding10;
        FragmentPerformanceBinding fragmentPerformanceBinding11;
        fragmentPerformanceBinding = this$0.binding;
        FragmentPerformanceBinding fragmentPerformanceBinding12 = null;
        if (fragmentPerformanceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPerformanceBinding = null;
        }
        fragmentPerformanceBinding.totalVisits.setText(String.valueOf($totalVisits));
        fragmentPerformanceBinding2 = this$0.binding;
        if (fragmentPerformanceBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPerformanceBinding2 = null;
        }
        fragmentPerformanceBinding2.missedVisits.setText(String.valueOf($missed));
        fragmentPerformanceBinding3 = this$0.binding;
        if (fragmentPerformanceBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPerformanceBinding3 = null;
        }
        fragmentPerformanceBinding3.avgDaily.setText(String.valueOf($avgDaily));
        fragmentPerformanceBinding4 = this$0.binding;
        if (fragmentPerformanceBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPerformanceBinding4 = null;
        }
        fragmentPerformanceBinding4.weeklyProgress.setProgress($weeklyProgress);
        fragmentPerformanceBinding5 = this$0.binding;
        if (fragmentPerformanceBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPerformanceBinding5 = null;
        }
        fragmentPerformanceBinding5.weeklyPercentText.setText($weeklyProgress + "%");
        fragmentPerformanceBinding6 = this$0.binding;
        if (fragmentPerformanceBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPerformanceBinding6 = null;
        }
        fragmentPerformanceBinding6.weeklyTargetDetail.setText($weeklyCompleted + " / " + $weeklyTarget + " visits completed");
        fragmentPerformanceBinding7 = this$0.binding;
        if (fragmentPerformanceBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPerformanceBinding7 = null;
        }
        fragmentPerformanceBinding7.yearlyProgress.setProgress($yearlyProgress);
        fragmentPerformanceBinding8 = this$0.binding;
        if (fragmentPerformanceBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPerformanceBinding8 = null;
        }
        fragmentPerformanceBinding8.yearlyPercentText.setText($yearlyProgress + "%");
        fragmentPerformanceBinding9 = this$0.binding;
        if (fragmentPerformanceBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPerformanceBinding9 = null;
        }
        fragmentPerformanceBinding9.yearlyTargetDetail.setText($yearlyUsed + " / " + $yearlyTarget + " samples used");
        this$0.setupLineChart($completedEntries, $missedEntries, $dayLabels);
        this$0.setupBarChart($barEntries, $customerDayLabels);
        fragmentPerformanceBinding10 = this$0.binding;
        if (fragmentPerformanceBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPerformanceBinding10 = null;
        }
        fragmentPerformanceBinding10.totalCustomer.setText(String.valueOf($totalCustomer));
        fragmentPerformanceBinding11 = this$0.binding;
        if (fragmentPerformanceBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentPerformanceBinding12 = fragmentPerformanceBinding11;
        }
        fragmentPerformanceBinding12.plusCustomer.setText(String.valueOf($avg));
    }
}
