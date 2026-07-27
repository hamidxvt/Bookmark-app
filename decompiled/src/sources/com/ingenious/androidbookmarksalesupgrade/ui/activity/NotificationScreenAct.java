package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.common.net.HttpHeaders;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityNotificationScreenBinding;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONObject;

/* compiled from: NotificationScreenAct.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u000fH\u0002J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u000fH\u0014J\b\u0010\u0016\u001a\u00020\u000fH\u0002J\b\u0010\u0017\u001a\u00020\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/NotificationScreenAct;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityNotificationScreenBinding;", "job", "Lkotlinx/coroutines/Job;", "client", "Lokhttp3/OkHttpClient;", "getClient", "()Lokhttp3/OkHttpClient;", "client$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "postNotificationSettings", "getToken", "", "onDestroy", "saveLocalSettings", "loadLocalSettings", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class NotificationScreenAct extends AppCompatActivity {
    private ActivityNotificationScreenBinding binding;

    /* renamed from: client$delegate, reason: from kotlin metadata */
    private final Lazy client = LazyKt.lazy(new Function0() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.NotificationScreenAct$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            OkHttpClient client_delegate$lambda$0;
            client_delegate$lambda$0 = NotificationScreenAct.client_delegate$lambda$0();
            return client_delegate$lambda$0;
        }
    });
    private Job job;

    /* JADX INFO: Access modifiers changed from: private */
    public final OkHttpClient getClient() {
        return (OkHttpClient) this.client.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final OkHttpClient client_delegate$lambda$0() {
        return new OkHttpClient.Builder().connectTimeout(15L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).build();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNotificationScreenBinding activityNotificationScreenBinding = null;
        EdgeToEdge.enable$default(this, null, null, 3, null);
        this.binding = ActivityNotificationScreenBinding.inflate(getLayoutInflater());
        ActivityNotificationScreenBinding activityNotificationScreenBinding2 = this.binding;
        if (activityNotificationScreenBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationScreenBinding2 = null;
        }
        setContentView(activityNotificationScreenBinding2.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new OnApplyWindowInsetsListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.NotificationScreenAct$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat onCreate$lambda$1;
                onCreate$lambda$1 = NotificationScreenAct.onCreate$lambda$1(view, windowInsetsCompat);
                return onCreate$lambda$1;
            }
        });
        loadLocalSettings();
        ActivityNotificationScreenBinding activityNotificationScreenBinding3 = this.binding;
        if (activityNotificationScreenBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationScreenBinding3 = null;
        }
        activityNotificationScreenBinding3.backIcon.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.NotificationScreenAct$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NotificationScreenAct.this.finish();
            }
        });
        final Function2 toggleListener = new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.NotificationScreenAct$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit onCreate$lambda$3;
                onCreate$lambda$3 = NotificationScreenAct.onCreate$lambda$3(NotificationScreenAct.this, obj, ((Boolean) obj2).booleanValue());
                return onCreate$lambda$3;
            }
        };
        ActivityNotificationScreenBinding activityNotificationScreenBinding4 = this.binding;
        if (activityNotificationScreenBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityNotificationScreenBinding = activityNotificationScreenBinding4;
        }
        ActivityNotificationScreenBinding $this$onCreate_u24lambda_u249 = activityNotificationScreenBinding;
        $this$onCreate_u24lambda_u249.switchPush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.NotificationScreenAct$$ExternalSyntheticLambda3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                NotificationScreenAct.onCreate$lambda$9$lambda$4(Function2.this, compoundButton, z);
            }
        });
        $this$onCreate_u24lambda_u249.switchSms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.NotificationScreenAct$$ExternalSyntheticLambda4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                NotificationScreenAct.onCreate$lambda$9$lambda$5(Function2.this, compoundButton, z);
            }
        });
        $this$onCreate_u24lambda_u249.switchLowStock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.NotificationScreenAct$$ExternalSyntheticLambda5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                NotificationScreenAct.onCreate$lambda$9$lambda$6(Function2.this, compoundButton, z);
            }
        });
        $this$onCreate_u24lambda_u249.switchNewProduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.NotificationScreenAct$$ExternalSyntheticLambda6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                NotificationScreenAct.onCreate$lambda$9$lambda$7(Function2.this, compoundButton, z);
            }
        });
        $this$onCreate_u24lambda_u249.switchStockRefill.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.NotificationScreenAct$$ExternalSyntheticLambda7
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                NotificationScreenAct.onCreate$lambda$9$lambda$8(Function2.this, compoundButton, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat onCreate$lambda$1(View v, WindowInsetsCompat insets) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(insets, "insets");
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(systemBars, "getInsets(...)");
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        return insets;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$3(NotificationScreenAct this$0, Object obj, boolean z) {
        Intrinsics.checkNotNullParameter(obj, "<unused var>");
        this$0.postNotificationSettings();
        this$0.saveLocalSettings();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$9$lambda$4(Function2 $tmp0, CompoundButton p0, boolean p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        $tmp0.invoke(p0, Boolean.valueOf(p1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$9$lambda$5(Function2 $tmp0, CompoundButton p0, boolean p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        $tmp0.invoke(p0, Boolean.valueOf(p1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$9$lambda$6(Function2 $tmp0, CompoundButton p0, boolean p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        $tmp0.invoke(p0, Boolean.valueOf(p1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$9$lambda$7(Function2 $tmp0, CompoundButton p0, boolean p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        $tmp0.invoke(p0, Boolean.valueOf(p1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$9$lambda$8(Function2 $tmp0, CompoundButton p0, boolean p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        $tmp0.invoke(p0, Boolean.valueOf(p1));
    }

    private final void postNotificationSettings() {
        Job launch$default;
        String token = getToken();
        String str = token;
        if (str == null || str.length() == 0) {
            Log.e("API_ERROR", "Token is missing");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        ActivityNotificationScreenBinding activityNotificationScreenBinding = this.binding;
        if (activityNotificationScreenBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationScreenBinding = null;
        }
        jSONObject.put("push_notifications", activityNotificationScreenBinding.switchPush.isChecked() ? 1 : 0);
        ActivityNotificationScreenBinding activityNotificationScreenBinding2 = this.binding;
        if (activityNotificationScreenBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationScreenBinding2 = null;
        }
        jSONObject.put("sms_alerts", activityNotificationScreenBinding2.switchSms.isChecked() ? 1 : 0);
        ActivityNotificationScreenBinding activityNotificationScreenBinding3 = this.binding;
        if (activityNotificationScreenBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationScreenBinding3 = null;
        }
        jSONObject.put("low_stock_reminders", activityNotificationScreenBinding3.switchLowStock.isChecked() ? 1 : 0);
        ActivityNotificationScreenBinding activityNotificationScreenBinding4 = this.binding;
        if (activityNotificationScreenBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationScreenBinding4 = null;
        }
        jSONObject.put("new_product_added", activityNotificationScreenBinding4.switchNewProduct.isChecked() ? 1 : 0);
        ActivityNotificationScreenBinding activityNotificationScreenBinding5 = this.binding;
        if (activityNotificationScreenBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationScreenBinding5 = null;
        }
        jSONObject.put("stock_refill_updates", activityNotificationScreenBinding5.switchStockRefill.isChecked() ? 1 : 0);
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType parse = MediaType.INSTANCE.parse("application/json; charset=utf-8");
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
        launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new NotificationScreenAct$postNotificationSettings$1(this, new Request.Builder().url("https://staging.bookmark.services/api/update-notifications").addHeader(HttpHeaders.ACCEPT, "application/json").addHeader("Content-Type", "application/json").addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token).post(companion.create(parse, jSONObject2)).build(), null), 3, null);
        this.job = launch$default;
    }

    private final String getToken() {
        SharedPreferences sharedPref = getSharedPreferences("BookmarkApp", 0);
        String token = sharedPref.getString("AUTH_TOKEN", null);
        Log.i("TOKEN", "Bearer Token: " + token);
        return token;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    private final void saveLocalSettings() {
        SharedPreferences sharedPref = getSharedPreferences("BookmarkApp", 0);
        Intrinsics.checkNotNull(sharedPref);
        SharedPreferences.Editor editor$iv = sharedPref.edit();
        ActivityNotificationScreenBinding activityNotificationScreenBinding = this.binding;
        ActivityNotificationScreenBinding activityNotificationScreenBinding2 = null;
        if (activityNotificationScreenBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationScreenBinding = null;
        }
        SharedPreferences.Editor putBoolean = editor$iv.putBoolean("push_notifications", activityNotificationScreenBinding.switchPush.isChecked());
        ActivityNotificationScreenBinding activityNotificationScreenBinding3 = this.binding;
        if (activityNotificationScreenBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationScreenBinding3 = null;
        }
        SharedPreferences.Editor putBoolean2 = putBoolean.putBoolean("sms_alerts", activityNotificationScreenBinding3.switchSms.isChecked());
        ActivityNotificationScreenBinding activityNotificationScreenBinding4 = this.binding;
        if (activityNotificationScreenBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationScreenBinding4 = null;
        }
        SharedPreferences.Editor putBoolean3 = putBoolean2.putBoolean("low_stock_reminders", activityNotificationScreenBinding4.switchLowStock.isChecked());
        ActivityNotificationScreenBinding activityNotificationScreenBinding5 = this.binding;
        if (activityNotificationScreenBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationScreenBinding5 = null;
        }
        SharedPreferences.Editor putBoolean4 = putBoolean3.putBoolean("new_product_added", activityNotificationScreenBinding5.switchNewProduct.isChecked());
        ActivityNotificationScreenBinding activityNotificationScreenBinding6 = this.binding;
        if (activityNotificationScreenBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityNotificationScreenBinding2 = activityNotificationScreenBinding6;
        }
        putBoolean4.putBoolean("stock_refill_updates", activityNotificationScreenBinding2.switchStockRefill.isChecked());
        editor$iv.apply();
    }

    private final void loadLocalSettings() {
        SharedPreferences sharedPref = getSharedPreferences("BookmarkApp", 0);
        ActivityNotificationScreenBinding activityNotificationScreenBinding = this.binding;
        ActivityNotificationScreenBinding activityNotificationScreenBinding2 = null;
        if (activityNotificationScreenBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationScreenBinding = null;
        }
        activityNotificationScreenBinding.switchPush.setChecked(sharedPref.getBoolean("push_notifications", false));
        ActivityNotificationScreenBinding activityNotificationScreenBinding3 = this.binding;
        if (activityNotificationScreenBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationScreenBinding3 = null;
        }
        activityNotificationScreenBinding3.switchSms.setChecked(sharedPref.getBoolean("sms_alerts", false));
        ActivityNotificationScreenBinding activityNotificationScreenBinding4 = this.binding;
        if (activityNotificationScreenBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationScreenBinding4 = null;
        }
        activityNotificationScreenBinding4.switchLowStock.setChecked(sharedPref.getBoolean("low_stock_reminders", false));
        ActivityNotificationScreenBinding activityNotificationScreenBinding5 = this.binding;
        if (activityNotificationScreenBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityNotificationScreenBinding5 = null;
        }
        activityNotificationScreenBinding5.switchNewProduct.setChecked(sharedPref.getBoolean("new_product_added", false));
        ActivityNotificationScreenBinding activityNotificationScreenBinding6 = this.binding;
        if (activityNotificationScreenBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityNotificationScreenBinding2 = activityNotificationScreenBinding6;
        }
        activityNotificationScreenBinding2.switchStockRefill.setChecked(sharedPref.getBoolean("stock_refill_updates", false));
    }
}
