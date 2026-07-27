package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivitySplashBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt;
import com.ingenious.androidbookmarksalesupgrade.storage.AppPreferences;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SplashActivity.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/SplashActivity;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/BaseActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivitySplashBinding;", "handler", "Landroid/os/Handler;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class SplashActivity extends BaseActivity {
    private ActivitySplashBinding binding;
    private Handler handler;

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivitySplashBinding.inflate(getLayoutInflater());
        ActivitySplashBinding activitySplashBinding = this.binding;
        Handler handler = null;
        if (activitySplashBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySplashBinding = null;
        }
        setContentView(activitySplashBinding.getRoot());
        ExtensionKt.belowStatusBarText(this);
        this.handler = new Handler(Looper.getMainLooper());
        Handler handler2 = this.handler;
        if (handler2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("handler");
        } else {
            handler = handler2;
        }
        handler.postDelayed(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.SplashActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SplashActivity.onCreate$lambda$2(SplashActivity.this);
            }
        }, Constant.SPLASH_TIME);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(SplashActivity this$0) {
        if (AppPreferences.INSTANCE.getLoginData() != null) {
            ActivityExtKt.gotoActivityWithNoHistory(this$0, HomeActivity.class);
        } else {
            ActivityExtKt.gotoActivityWithNoHistory(this$0, LoginActivity.class);
        }
    }
}
