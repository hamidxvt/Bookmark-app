package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.common.net.HttpHeaders;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentPerformanceBinding;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.HelpScreenAct;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* compiled from: PerformanceFragment.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0002J2\u0010\u0012\u001a\u00020\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0014H\u0002J$\u0010\u0019\u001a\u00020\u00112\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0014H\u0002J\n\u0010\u001c\u001a\u0004\u0018\u00010\u0018H\u0002J\u0010\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\tH\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J-\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00072\u000e\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180$2\u0006\u0010%\u001a\u00020&H\u0016¢\u0006\u0002\u0010'J\b\u0010(\u001a\u00020\u0011H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/PerformanceFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentPerformanceBinding;", "STORAGE_PERMISSION_CODE", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "fetchPerformanceData", "", "setupLineChart", "completedEntries", "", "Lcom/github/mikephil/charting/data/Entry;", "missedEntries", "dayLabels", "", "setupBarChart", "entries", "Lcom/github/mikephil/charting/data/BarEntry;", "getToken", "captureAndSaveScreenshot", "view", "checkAndRequestPermission", "", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "exportDialog", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class PerformanceFragment extends Fragment {
    private final int STORAGE_PERMISSION_CODE = 1001;
    private FragmentPerformanceBinding binding;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.binding = FragmentPerformanceBinding.inflate(inflater, container, false);
        fetchPerformanceData();
        FragmentPerformanceBinding fragmentPerformanceBinding = this.binding;
        FragmentPerformanceBinding fragmentPerformanceBinding2 = null;
        if (fragmentPerformanceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPerformanceBinding = null;
        }
        fragmentPerformanceBinding.performanceMenu.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.PerformanceFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PerformanceFragment.this.exportDialog();
            }
        });
        FragmentPerformanceBinding fragmentPerformanceBinding3 = this.binding;
        if (fragmentPerformanceBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentPerformanceBinding2 = fragmentPerformanceBinding3;
        }
        NestedScrollView root = fragmentPerformanceBinding2.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    private final void fetchPerformanceData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://staging.bookmark.services/api/performance/dashboard").addHeader(HttpHeaders.ACCEPT, "application/json").addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getToken()).build();
        client.newCall(request).enqueue(new PerformanceFragment$fetchPerformanceData$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupLineChart(List<? extends Entry> completedEntries, List<? extends Entry> missedEntries, List<String> dayLabels) {
        FragmentPerformanceBinding fragmentPerformanceBinding = this.binding;
        if (fragmentPerformanceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPerformanceBinding = null;
        }
        LineChart visitsLineChart = fragmentPerformanceBinding.visitsLineChart;
        Intrinsics.checkNotNullExpressionValue(visitsLineChart, "visitsLineChart");
        List<? extends Entry> $this$map$iv = missedEntries;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Entry it = (Entry) item$iv$iv;
            destination$iv$iv.add(new Entry(it.getX() + 0.5f, it.getY()));
        }
        List shiftedMissed = (List) destination$iv$iv;
        LineDataSet completedDataSet = new LineDataSet(completedEntries, "Completed");
        completedDataSet.setColor(Color.parseColor("#E60000"));
        completedDataSet.setLineWidth(3.0f);
        completedDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        completedDataSet.setDrawFilled(false);
        completedDataSet.setDrawCircles(true);
        completedDataSet.setCircleRadius(5.0f);
        completedDataSet.setCircleColor(Color.parseColor("#E60000"));
        completedDataSet.setValueTextSize(0.0f);
        completedDataSet.setHighlightEnabled(false);
        LineDataSet missedDataSet = new LineDataSet(shiftedMissed, "Missed");
        missedDataSet.setColor(Color.parseColor("#FDC23E"));
        missedDataSet.setLineWidth(3.0f);
        missedDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        missedDataSet.setDrawFilled(false);
        missedDataSet.setDrawCircles(true);
        missedDataSet.setCircleRadius(5.0f);
        missedDataSet.setCircleColor(Color.parseColor("#FDC23E"));
        missedDataSet.setValueTextSize(0.0f);
        missedDataSet.setHighlightEnabled(false);
        LineData lineData = new LineData(completedDataSet, missedDataSet);
        visitsLineChart.setData(lineData);
        XAxis $this$setupLineChart_u24lambda_u244 = visitsLineChart.getXAxis();
        $this$setupLineChart_u24lambda_u244.setValueFormatter(new IndexAxisValueFormatter(dayLabels));
        $this$setupLineChart_u24lambda_u244.setPosition(XAxis.XAxisPosition.BOTTOM);
        $this$setupLineChart_u24lambda_u244.setGranularity(1.0f);
        $this$setupLineChart_u24lambda_u244.setDrawGridLines(false);
        $this$setupLineChart_u24lambda_u244.setTextSize(10.0f);
        YAxis $this$setupLineChart_u24lambda_u245 = visitsLineChart.getAxisLeft();
        $this$setupLineChart_u24lambda_u245.setDrawGridLines(true);
        $this$setupLineChart_u24lambda_u245.setGridColor(Color.parseColor("#F1F1F1"));
        $this$setupLineChart_u24lambda_u245.setSpaceTop(20.0f);
        $this$setupLineChart_u24lambda_u245.setSpaceBottom(20.0f);
        visitsLineChart.getAxisRight().setEnabled(false);
        Legend $this$setupLineChart_u24lambda_u246 = visitsLineChart.getLegend();
        $this$setupLineChart_u24lambda_u246.setEnabled(true);
        $this$setupLineChart_u24lambda_u246.setTextSize(12.0f);
        $this$setupLineChart_u24lambda_u246.setFormSize(12.0f);
        $this$setupLineChart_u24lambda_u246.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        $this$setupLineChart_u24lambda_u246.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        visitsLineChart.getDescription().setEnabled(false);
        visitsLineChart.setDrawBorders(false);
        visitsLineChart.setExtraOffsets(0.0f, 0.0f, 0.0f, 20.0f);
        visitsLineChart.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupBarChart(List<? extends BarEntry> entries, List<String> dayLabels) {
        BarDataSet dataSet = new BarDataSet(entries, "");
        dataSet.setColor(Color.parseColor("#FF0000"));
        dataSet.setValueTextSize(0.0f);
        dataSet.setHighlightEnabled(false);
        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.4f);
        FragmentPerformanceBinding fragmentPerformanceBinding = this.binding;
        FragmentPerformanceBinding fragmentPerformanceBinding2 = null;
        if (fragmentPerformanceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPerformanceBinding = null;
        }
        fragmentPerformanceBinding.customersBarChart.setData(barData);
        FragmentPerformanceBinding fragmentPerformanceBinding3 = this.binding;
        if (fragmentPerformanceBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentPerformanceBinding2 = fragmentPerformanceBinding3;
        }
        BarChart customersBarChart = fragmentPerformanceBinding2.customersBarChart;
        Intrinsics.checkNotNullExpressionValue(customersBarChart, "customersBarChart");
        XAxis $this$setupBarChart_u24lambda_u248 = customersBarChart.getXAxis();
        $this$setupBarChart_u24lambda_u248.setValueFormatter(new IndexAxisValueFormatter(dayLabels));
        $this$setupBarChart_u24lambda_u248.setPosition(XAxis.XAxisPosition.BOTTOM);
        $this$setupBarChart_u24lambda_u248.setGranularity(1.0f);
        $this$setupBarChart_u24lambda_u248.setDrawGridLines(false);
        $this$setupBarChart_u24lambda_u248.setTextSize(10.0f);
        YAxis $this$setupBarChart_u24lambda_u249 = customersBarChart.getAxisLeft();
        $this$setupBarChart_u24lambda_u249.setDrawGridLines(true);
        $this$setupBarChart_u24lambda_u249.setGridColor(Color.parseColor("#EAEAEA"));
        $this$setupBarChart_u24lambda_u249.setAxisMinimum(0.0f);
        customersBarChart.getAxisRight().setEnabled(false);
        customersBarChart.getLegend().setEnabled(false);
        customersBarChart.getDescription().setEnabled(false);
        customersBarChart.setExtraOffsets(0.0f, 10.0f, 0.0f, 20.0f);
        customersBarChart.invalidate();
    }

    private final String getToken() {
        SharedPreferences sharedPref = requireContext().getSharedPreferences("BookmarkApp", 0);
        return sharedPref.getString("AUTH_TOKEN", null);
    }

    private final void captureAndSaveScreenshot(View view) {
        Bitmap bitmap;
        Canvas canvas;
        try {
            bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(bitmap, "createBitmap(...)");
            canvas = new Canvas(bitmap);
        } catch (Exception e) {
            e = e;
        }
        try {
            view.draw(canvas);
            String filename = "performance_dashboard_" + System.currentTimeMillis() + ".png";
            ContentResolver resolver = requireContext().getContentResolver();
            ContentValues $this$captureAndSaveScreenshot_u24lambda_u2410 = new ContentValues();
            $this$captureAndSaveScreenshot_u24lambda_u2410.put("_display_name", filename);
            $this$captureAndSaveScreenshot_u24lambda_u2410.put("mime_type", "image/png");
            if (Build.VERSION.SDK_INT >= 29) {
                $this$captureAndSaveScreenshot_u24lambda_u2410.put("relative_path", Environment.DIRECTORY_PICTURES);
                $this$captureAndSaveScreenshot_u24lambda_u2410.put("is_pending", (Integer) 1);
            }
            Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, $this$captureAndSaveScreenshot_u24lambda_u2410);
            if (imageUri != null) {
                OutputStream outputStream = resolver.openOutputStream(imageUri);
                if (outputStream != null) {
                    OutputStream outputStream2 = outputStream;
                    try {
                        OutputStream it = outputStream2;
                        Boolean.valueOf(bitmap.compress(Bitmap.CompressFormat.PNG, 100, it));
                        CloseableKt.closeFinally(outputStream2, null);
                    } finally {
                    }
                }
                if (Build.VERSION.SDK_INT >= 29) {
                    $this$captureAndSaveScreenshot_u24lambda_u2410.clear();
                    $this$captureAndSaveScreenshot_u24lambda_u2410.put("is_pending", (Integer) 0);
                    resolver.update(imageUri, $this$captureAndSaveScreenshot_u24lambda_u2410, null, null);
                }
                Toast.makeText(requireContext(), "Screenshot saved to gallery", 0).show();
                return;
            }
            Toast.makeText(requireContext(), "Failed to save screenshot", 0).show();
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            Toast.makeText(requireContext(), "Error: " + e.getMessage(), 0).show();
        }
    }

    private final boolean checkAndRequestPermission() {
        if (Build.VERSION.SDK_INT >= 29) {
            return true;
        }
        int granted = ContextCompat.checkSelfPermission(requireContext(), "android.permission.WRITE_EXTERNAL_STORAGE");
        if (granted == 0) {
            return true;
        }
        ActivityCompat.requestPermissions(requireActivity(), new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, this.STORAGE_PERMISSION_CODE);
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == this.STORAGE_PERMISSION_CODE) {
            if (!(grantResults.length == 0) && grantResults[0] == 0) {
                Toast.makeText(requireContext(), "Permission granted", 0).show();
            } else {
                Toast.makeText(requireContext(), "Storage permission denied", 0).show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void exportDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_performance_filter_main, (ViewGroup) null);
        final AlertDialog dialog = new AlertDialog.Builder(requireContext()).setView(dialogView).create();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        ImageView crossBtn = (ImageView) dialogView.findViewById(R.id.ivClose);
        LinearLayout actionType = (LinearLayout) dialogView.findViewById(R.id.btnActionType);
        LinearLayout dateRange = (LinearLayout) dialogView.findViewById(R.id.btnDateRange);
        actionType.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.PerformanceFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PerformanceFragment.exportDialog$lambda$12(PerformanceFragment.this, view);
            }
        });
        dateRange.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.PerformanceFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PerformanceFragment.exportDialog$lambda$13(PerformanceFragment.this, view);
            }
        });
        crossBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.PerformanceFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void exportDialog$lambda$12(PerformanceFragment this$0, View it) {
        if (this$0.checkAndRequestPermission()) {
            FragmentPerformanceBinding fragmentPerformanceBinding = this$0.binding;
            if (fragmentPerformanceBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentPerformanceBinding = null;
            }
            NestedScrollView root = fragmentPerformanceBinding.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
            this$0.captureAndSaveScreenshot(root);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void exportDialog$lambda$13(PerformanceFragment this$0, View it) {
        Intent intent = new Intent(this$0.requireContext(), (Class<?>) HelpScreenAct.class);
        this$0.startActivity(intent);
    }
}
