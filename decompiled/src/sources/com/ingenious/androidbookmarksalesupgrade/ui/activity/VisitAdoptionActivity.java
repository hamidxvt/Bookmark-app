package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityVisitAdoptionBinding;
import com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment;
import com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoption1Fragment;
import com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoption2Fragment;
import com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionBooksFragment;
import com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionGradesFragment;
import com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionQuantityFragment;
import com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionSubjectsFragment;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: VisitAdoptionActivity.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0006\u0010\u0013\u001a\u00020\u0010J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/VisitAdoptionActivity;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/BaseActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityVisitAdoptionBinding;", "step", "", "total", "progress", "Lcom/google/android/material/progressindicator/LinearProgressIndicator;", "stepLabel", "Landroid/widget/TextView;", "customerId", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "next", "showStep", TypedValues.AttributesType.S_TARGET, "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class VisitAdoptionActivity extends BaseActivity {
    private ActivityVisitAdoptionBinding binding;
    private String customerId;
    private LinearProgressIndicator progress;
    private TextView stepLabel;
    private int step = 1;
    private final int total = 6;

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        String str;
        super.onCreate(savedInstanceState);
        this.binding = ActivityVisitAdoptionBinding.inflate(getLayoutInflater());
        ActivityVisitAdoptionBinding activityVisitAdoptionBinding = this.binding;
        ActivityVisitAdoptionBinding activityVisitAdoptionBinding2 = null;
        if (activityVisitAdoptionBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVisitAdoptionBinding = null;
        }
        setContentView(activityVisitAdoptionBinding.getRoot());
        ExtensionKt.belowStatusBarText(this);
        if (getIntent().hasExtra("customerId")) {
            str = getIntent().getStringExtra("customerId");
        } else {
            str = null;
        }
        this.customerId = str;
        ActivityVisitAdoptionBinding activityVisitAdoptionBinding3 = this.binding;
        if (activityVisitAdoptionBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVisitAdoptionBinding3 = null;
        }
        this.progress = activityVisitAdoptionBinding3.stepProgress;
        ActivityVisitAdoptionBinding activityVisitAdoptionBinding4 = this.binding;
        if (activityVisitAdoptionBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVisitAdoptionBinding4 = null;
        }
        this.stepLabel = activityVisitAdoptionBinding4.stepLabel;
        showStep(1);
        ActivityVisitAdoptionBinding activityVisitAdoptionBinding5 = this.binding;
        if (activityVisitAdoptionBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityVisitAdoptionBinding2 = activityVisitAdoptionBinding5;
        }
        activityVisitAdoptionBinding2.btnBack.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.VisitAdoptionActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VisitAdoptionActivity.onCreate$lambda$0(VisitAdoptionActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(VisitAdoptionActivity this$0, View it) {
        if (this$0.step > 1) {
            this$0.showStep(this$0.step - 1);
        } else {
            this$0.finish();
        }
    }

    public final void next() {
        showStep(this.step + 1);
    }

    private final void showStep(int target) {
        BaseFragment fragment;
        if (target > 6) {
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.slide_in_left).replace(R.id.fragmentContainer, new VisitAdoptionQuantityFragment(this.customerId)).commitNow();
            return;
        }
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
                fragment = new VisitAdoption1Fragment();
                break;
            case 2:
                fragment = new VisitAdoption2Fragment();
                break;
            case 3:
                fragment = new VisitAdoptionGradesFragment();
                break;
            case 4:
                fragment = new VisitAdoptionSubjectsFragment();
                break;
            case 5:
                fragment = new VisitAdoptionBooksFragment();
                break;
            default:
                fragment = new VisitAdoptionQuantityFragment(this.customerId);
                break;
        }
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.slide_in_left).replace(R.id.fragmentContainer, fragment).commitNow();
    }
}
