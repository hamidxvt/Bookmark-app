package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityCheckInBinding;
import com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment;
import com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn1Fragment;
import com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn2Fragment;
import com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: CheckInActivity.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\u0012\u0010\u0019\u001a\u00020\u00162\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\rJ\u001c\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u000b2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\rH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/CheckInActivity;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/BaseActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityCheckInBinding;", "progress", "Lcom/google/android/material/progressindicator/LinearProgressIndicator;", "stepLabel", "Landroid/widget/TextView;", "visitId", "", AppMeasurementSdk.ConditionalUserProperty.NAME, "", "reason", "priority", "customerType", "customerAddress", "visitType", "step", "total", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "next", "currentTime", "showStep", TypedValues.AttributesType.S_TARGET, "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class CheckInActivity extends BaseActivity {
    private ActivityCheckInBinding binding;
    private LinearProgressIndicator progress;
    private TextView stepLabel;
    private int visitId;
    private String name = "";
    private String reason = "";
    private String priority = "";
    private String customerType = "";
    private String customerAddress = "";
    private String visitType = "";
    private int step = 1;
    private final int total = 3;

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityCheckInBinding.inflate(getLayoutInflater());
        ActivityCheckInBinding activityCheckInBinding = this.binding;
        ActivityCheckInBinding activityCheckInBinding2 = null;
        if (activityCheckInBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCheckInBinding = null;
        }
        setContentView(activityCheckInBinding.getRoot());
        ExtensionKt.belowStatusBarText(this);
        this.visitId = getIntent().getIntExtra("visitId", 0);
        this.name = String.valueOf(getIntent().getStringExtra(AppMeasurementSdk.ConditionalUserProperty.NAME));
        this.reason = String.valueOf(getIntent().getStringExtra("reason"));
        this.customerAddress = String.valueOf(getIntent().getStringExtra(FirebaseAnalytics.Param.LOCATION));
        this.priority = String.valueOf(getIntent().getStringExtra("priority"));
        this.customerType = String.valueOf(getIntent().getStringExtra("customerType"));
        this.visitType = String.valueOf(getIntent().getStringExtra("visitType"));
        this.progress = (LinearProgressIndicator) findViewById(R.id.stepProgress);
        this.stepLabel = (TextView) findViewById(R.id.stepLabel);
        showStep$default(this, 1, null, 2, null);
        ActivityCheckInBinding activityCheckInBinding3 = this.binding;
        if (activityCheckInBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCheckInBinding2 = activityCheckInBinding3;
        }
        activityCheckInBinding2.btnBack.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CheckInActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CheckInActivity.onCreate$lambda$0(CheckInActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(CheckInActivity this$0, View it) {
        if (this$0.step > 1) {
            showStep$default(this$0, this$0.step - 1, null, 2, null);
        } else {
            this$0.finish();
        }
    }

    public static /* synthetic */ void next$default(CheckInActivity checkInActivity, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        checkInActivity.next(str);
    }

    public final void next(String currentTime) {
        showStep(this.step + 1, currentTime);
    }

    static /* synthetic */ void showStep$default(CheckInActivity checkInActivity, int i, String str, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        checkInActivity.showStep(i, str);
    }

    private final void showStep(int target, String currentTime) {
        BaseFragment fragment;
        this.step = RangesKt.coerceIn(target, 1, this.total);
        int pct = (this.step * 100) / this.total;
        LinearProgressIndicator linearProgressIndicator = this.progress;
        TextView textView = null;
        if (linearProgressIndicator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progress");
            linearProgressIndicator = null;
        }
        linearProgressIndicator.setProgressCompat(pct, true);
        TextView textView2 = this.stepLabel;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stepLabel");
        } else {
            textView = textView2;
        }
        textView.setText(this.step + "/" + this.total);
        switch (this.step) {
            case 1:
                fragment = new CheckIn1Fragment(String.valueOf(this.visitId), this.name, this.reason, this.customerAddress, this.priority, this.customerType, this.visitType);
                break;
            case 2:
                fragment = new CheckIn2Fragment(String.valueOf(this.visitId));
                break;
            default:
                CheckIn3Fragment $this$showStep_u24lambda_u242 = new CheckIn3Fragment(String.valueOf(this.visitId));
                Bundle $this$showStep_u24lambda_u242_u24lambda_u241 = new Bundle();
                $this$showStep_u24lambda_u242_u24lambda_u241.putString("currentTime", currentTime);
                $this$showStep_u24lambda_u242.setArguments($this$showStep_u24lambda_u242_u24lambda_u241);
                fragment = $this$showStep_u24lambda_u242;
                break;
        }
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.slide_in_left).replace(R.id.fragmentContainer, fragment).commitNow();
    }
}
