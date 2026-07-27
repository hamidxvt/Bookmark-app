package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivitySettingScreenBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingScreenAct.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/SettingScreenAct;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivitySettingScreenBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class SettingScreenAct extends AppCompatActivity {
    private ActivitySettingScreenBinding binding;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivitySettingScreenBinding.inflate(getLayoutInflater());
        ActivitySettingScreenBinding activitySettingScreenBinding = this.binding;
        ActivitySettingScreenBinding activitySettingScreenBinding2 = null;
        if (activitySettingScreenBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySettingScreenBinding = null;
        }
        setContentView(activitySettingScreenBinding.getRoot());
        ExtensionKt.belowStatusBarText(this);
        ActivitySettingScreenBinding activitySettingScreenBinding3 = this.binding;
        if (activitySettingScreenBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySettingScreenBinding2 = activitySettingScreenBinding3;
        }
        ActivitySettingScreenBinding $this$onCreate_u24lambda_u245 = activitySettingScreenBinding2;
        $this$onCreate_u24lambda_u245.backIcon.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.SettingScreenAct$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingScreenAct.this.finish();
            }
        });
        $this$onCreate_u24lambda_u245.notificationCard.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.SettingScreenAct$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingScreenAct.onCreate$lambda$5$lambda$1(SettingScreenAct.this, view);
            }
        });
        $this$onCreate_u24lambda_u245.privacyPolicyCard.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.SettingScreenAct$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingScreenAct.onCreate$lambda$5$lambda$2(SettingScreenAct.this, view);
            }
        });
        $this$onCreate_u24lambda_u245.helpCard.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.SettingScreenAct$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingScreenAct.onCreate$lambda$5$lambda$3(SettingScreenAct.this, view);
            }
        });
        $this$onCreate_u24lambda_u245.contactCard.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.SettingScreenAct$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingScreenAct.onCreate$lambda$5$lambda$4(SettingScreenAct.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5$lambda$1(SettingScreenAct this$0, View it) {
        ActivityExtKt.moveNextAct(this$0, NotificationScreenAct.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5$lambda$2(SettingScreenAct this$0, View it) {
        ActivityExtKt.moveNextAct(this$0, PrivacyPolicyAct.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5$lambda$3(SettingScreenAct this$0, View it) {
        ActivityExtKt.moveNextAct(this$0, HelpScreenAct.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5$lambda$4(SettingScreenAct this$0, View it) {
        ActivityExtKt.moveNextAct(this$0, ContactAct.class);
    }
}
