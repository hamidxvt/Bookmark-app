package com.ingenious.androidbookmarksalesupgrade.extensions;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ActivityExt.kt */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\f\u0010\u0005\u001a\u00020\u0004*\u0004\u0018\u00010\u0006\u001a'\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\b0\f¢\u0006\u0002\u0010\r\u001a\"\u0010\u000e\u001a\u00020\u0001\"\b\b\u0000\u0010\b*\u00020\n*\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\b0\f\u001a\"\u0010\u0010\u001a\u00020\u0001\"\b\b\u0000\u0010\b*\u00020\n*\u00020\u00112\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\b0\f\u001a*\u0010\u0010\u001a\u00020\u0001*\u00020\u00112\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u0012\u001a\u00020\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u001a\n\u0010\u0015\u001a\u00020\u0001*\u00020\n\u001a\n\u0010\u0016\u001a\u00020\u0004*\u00020\n\u001a>\u0010\u0010\u001a\u00020\u0001*\u00020\u00112\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u0012\u001a\u00020\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0017\u001a\u00020\u00062\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0014\u001a\u0016\u0010\u0019\u001a\u00020\u0001*\u00020\u00112\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\f\u001a\u0016\u0010\u001a\u001a\u00020\u0001*\u00020\n2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\f\u001a*\u0010\u000e\u001a\u00020\u0001*\u00020\n2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u0012\u001a\u00020\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u001a0\u0010\u001b\u001a\u00020\u0001\"\b\b\u0000\u0010\b*\u00020\n*\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\b0\f2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d\u001a>\u0010\u000e\u001a\u00020\u0001*\u00020\n2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u0017\u001a\u00020\u00062\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001f\u001a\u00020\u00062\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0014\u001aR\u0010\u000e\u001a\u00020\u0001*\u00020\n2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u0017\u001a\u00020\u00062\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001f\u001a\u00020\u00062\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00142\u0006\u0010!\u001a\u00020\u00062\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0014\u001af\u0010\u000e\u001a\u00020\u0001*\u00020\n2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u0017\u001a\u00020\u00062\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001f\u001a\u00020\u00062\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00142\u0006\u0010!\u001a\u00020\u00062\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00142\u0006\u0010#\u001a\u00020\u00062\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0014\u001a\u0014\u0010%\u001a\u00020\u0001*\u00020\n2\b\b\u0001\u0010&\u001a\u00020'\u001a\u0014\u0010(\u001a\u00020\u0001*\u00020\n2\b\b\u0001\u0010&\u001a\u00020'\u001a\u0014\u0010%\u001a\u00020\u0001*\u00020\u00112\b\b\u0001\u0010&\u001a\u00020'\u001a\n\u0010)\u001a\u00020\u0001*\u00020\n\u001a\u0014\u0010*\u001a\u00020\u0001*\u00020\n2\b\b\u0001\u0010&\u001a\u00020'\u001a\u001e\u0010+\u001a\u00020\u0001*\u00020\n2\u0006\u0010,\u001a\u00020\u00062\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.\u001a0\u0010/\u001a\b\u0012\u0004\u0012\u0002H\b00\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b002\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u00020\u000402\u001a\u0016\u00103\u001a\u00020\u0001*\u00020\n2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\f¨\u00064"}, d2 = {"visible", "", "Landroid/view/View;", "isVisible", "", "isValid", "", "obtainViewModel", "T", "Landroidx/lifecycle/ViewModel;", "Landroidx/appcompat/app/AppCompatActivity;", "viewModelClass", "Ljava/lang/Class;", "(Landroidx/appcompat/app/AppCompatActivity;Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "gotoActivity", "targetActivityClass", "gotoActivityFromFragment", "Landroidx/fragment/app/Fragment;", "intentKey", "intentValue", "", "openSettingForGPS", "isGpsEnable", "intentKey1", "intentValue1", "gotoActivityWithNoHistoryFromFragment", "gotoActivityWithNoHistory", "gotoActivityForResult", "resultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "intentKey2", "intentValue2", "intentKey3", "intentValue3", "intentKey4", "intentValue4", "changeStatusBarColor", TypedValues.Custom.S_COLOR, "", "changeNavigationBarColor", "setTransparentStatusBar", "setStatusBarWithBlackIcon", "showMaterialAlertDialog", "message", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/ingenious/androidbookmarksalesupgrade/listener/DialogListeners;", "customFilterList", "", "filterFunction", "Lkotlin/Function1;", "moveNextAct", "app_debug"}, k = 2, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes15.dex */
public final class ActivityExtKt {
    public static final void visible(View $this$visible, boolean isVisible) {
        Intrinsics.checkNotNullParameter($this$visible, "<this>");
        $this$visible.setVisibility(isVisible ? 0 : 8);
    }

    public static final boolean isValid(String $this$isValid) {
        if ($this$isValid != null) {
            return ($this$isValid.length() > 0) && !StringsKt.equals($this$isValid, "null", true);
        }
        return false;
    }

    public static final <T extends ViewModel> T obtainViewModel(AppCompatActivity appCompatActivity, Class<T> viewModelClass) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        Intrinsics.checkNotNullParameter(viewModelClass, "viewModelClass");
        return (T) new ViewModelProvider.NewInstanceFactory().create(viewModelClass);
    }

    public static final <T extends AppCompatActivity> void gotoActivity(AppCompatActivity $this$gotoActivity, Class<T> targetActivityClass) {
        Intrinsics.checkNotNullParameter($this$gotoActivity, "<this>");
        Intrinsics.checkNotNullParameter(targetActivityClass, "targetActivityClass");
        Intent intent = new Intent((Context) $this$gotoActivity, (Class<?>) targetActivityClass);
        $this$gotoActivity.startActivity(intent);
    }

    public static final <T extends AppCompatActivity> void gotoActivityFromFragment(Fragment $this$gotoActivityFromFragment, Class<T> targetActivityClass) {
        Intrinsics.checkNotNullParameter($this$gotoActivityFromFragment, "<this>");
        Intrinsics.checkNotNullParameter(targetActivityClass, "targetActivityClass");
        Intent intent = new Intent((Context) $this$gotoActivityFromFragment.requireActivity(), (Class<?>) targetActivityClass);
        $this$gotoActivityFromFragment.startActivity(intent);
    }

    public static /* synthetic */ void gotoActivityFromFragment$default(Fragment fragment, Class cls, String str, Object obj, int i, Object obj2) {
        if ((i & 4) != 0) {
            obj = null;
        }
        gotoActivityFromFragment(fragment, cls, str, obj);
    }

    public static final void gotoActivityFromFragment(Fragment $this$gotoActivityFromFragment, Class<?> targetActivityClass, String intentKey, Object intentValue) {
        Intrinsics.checkNotNullParameter($this$gotoActivityFromFragment, "<this>");
        Intrinsics.checkNotNullParameter(targetActivityClass, "targetActivityClass");
        Intrinsics.checkNotNullParameter(intentKey, "intentKey");
        Intent i = new Intent($this$gotoActivityFromFragment.requireActivity(), targetActivityClass);
        IntentExtKt.putExtra(i, intentKey, intentValue);
        $this$gotoActivityFromFragment.startActivity(i);
    }

    public static final void openSettingForGPS(AppCompatActivity $this$openSettingForGPS) {
        Intrinsics.checkNotNullParameter($this$openSettingForGPS, "<this>");
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        Uri uri = Uri.fromParts("package", $this$openSettingForGPS.getPackageName(), null);
        Intrinsics.checkNotNullExpressionValue(uri, "fromParts(...)");
        intent.setData(uri);
        $this$openSettingForGPS.startActivityForResult(intent, TypedValues.TYPE_TARGET);
    }

    public static final boolean isGpsEnable(AppCompatActivity $this$isGpsEnable) {
        Intrinsics.checkNotNullParameter($this$isGpsEnable, "<this>");
        Object systemService = $this$isGpsEnable.getSystemService(FirebaseAnalytics.Param.LOCATION);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        LocationManager locationManager = (LocationManager) systemService;
        return locationManager.isProviderEnabled("gps");
    }

    public static /* synthetic */ void gotoActivityFromFragment$default(Fragment fragment, Class cls, String str, Object obj, String str2, Object obj2, int i, Object obj3) {
        Object obj4;
        Object obj5;
        if ((i & 4) == 0) {
            obj4 = obj;
        } else {
            obj4 = null;
        }
        if ((i & 16) == 0) {
            obj5 = obj2;
        } else {
            obj5 = null;
        }
        gotoActivityFromFragment(fragment, cls, str, obj4, str2, obj5);
    }

    public static final void gotoActivityFromFragment(Fragment $this$gotoActivityFromFragment, Class<?> targetActivityClass, String intentKey, Object intentValue, String intentKey1, Object intentValue1) {
        Intrinsics.checkNotNullParameter($this$gotoActivityFromFragment, "<this>");
        Intrinsics.checkNotNullParameter(targetActivityClass, "targetActivityClass");
        Intrinsics.checkNotNullParameter(intentKey, "intentKey");
        Intrinsics.checkNotNullParameter(intentKey1, "intentKey1");
        Intent i = new Intent($this$gotoActivityFromFragment.requireActivity(), targetActivityClass);
        IntentExtKt.putExtra(i, intentKey, intentValue);
        IntentExtKt.putExtra(i, intentKey1, intentValue1);
        $this$gotoActivityFromFragment.startActivity(i);
    }

    public static final void gotoActivityWithNoHistoryFromFragment(Fragment $this$gotoActivityWithNoHistoryFromFragment, Class<?> targetActivityClass) {
        Intrinsics.checkNotNullParameter($this$gotoActivityWithNoHistoryFromFragment, "<this>");
        Intrinsics.checkNotNullParameter(targetActivityClass, "targetActivityClass");
        Intent i = new Intent($this$gotoActivityWithNoHistoryFromFragment.requireActivity(), targetActivityClass);
        i.setFlags(335577088);
        $this$gotoActivityWithNoHistoryFromFragment.startActivity(i);
    }

    public static final void gotoActivityWithNoHistory(AppCompatActivity $this$gotoActivityWithNoHistory, Class<?> targetActivityClass) {
        Intrinsics.checkNotNullParameter($this$gotoActivityWithNoHistory, "<this>");
        Intrinsics.checkNotNullParameter(targetActivityClass, "targetActivityClass");
        Intent i = new Intent($this$gotoActivityWithNoHistory, targetActivityClass);
        i.setFlags(335577088);
        $this$gotoActivityWithNoHistory.startActivity(i);
    }

    public static /* synthetic */ void gotoActivity$default(AppCompatActivity appCompatActivity, Class cls, String str, Object obj, int i, Object obj2) {
        if ((i & 4) != 0) {
            obj = null;
        }
        gotoActivity(appCompatActivity, cls, str, obj);
    }

    public static final void gotoActivity(AppCompatActivity $this$gotoActivity, Class<?> targetActivityClass, String intentKey, Object intentValue) {
        Intrinsics.checkNotNullParameter($this$gotoActivity, "<this>");
        Intrinsics.checkNotNullParameter(targetActivityClass, "targetActivityClass");
        Intrinsics.checkNotNullParameter(intentKey, "intentKey");
        Intent i = new Intent($this$gotoActivity, targetActivityClass);
        IntentExtKt.putExtra(i, intentKey, intentValue);
        $this$gotoActivity.startActivity(i);
    }

    public static final <T extends AppCompatActivity> void gotoActivityForResult(AppCompatActivity $this$gotoActivityForResult, Class<T> targetActivityClass, ActivityResultLauncher<Intent> resultLauncher) {
        Intrinsics.checkNotNullParameter($this$gotoActivityForResult, "<this>");
        Intrinsics.checkNotNullParameter(targetActivityClass, "targetActivityClass");
        Intrinsics.checkNotNullParameter(resultLauncher, "resultLauncher");
        Intent intent = new Intent((Context) $this$gotoActivityForResult, (Class<?>) targetActivityClass);
        resultLauncher.launch(intent);
    }

    public static /* synthetic */ void gotoActivity$default(AppCompatActivity appCompatActivity, Class cls, String str, Object obj, String str2, Object obj2, int i, Object obj3) {
        Object obj4;
        Object obj5;
        if ((i & 4) == 0) {
            obj4 = obj;
        } else {
            obj4 = null;
        }
        if ((i & 16) == 0) {
            obj5 = obj2;
        } else {
            obj5 = null;
        }
        gotoActivity(appCompatActivity, cls, str, obj4, str2, obj5);
    }

    public static final void gotoActivity(AppCompatActivity $this$gotoActivity, Class<?> targetActivityClass, String intentKey1, Object intentValue1, String intentKey2, Object intentValue2) {
        Intrinsics.checkNotNullParameter($this$gotoActivity, "<this>");
        Intrinsics.checkNotNullParameter(targetActivityClass, "targetActivityClass");
        Intrinsics.checkNotNullParameter(intentKey1, "intentKey1");
        Intrinsics.checkNotNullParameter(intentKey2, "intentKey2");
        Intent i = new Intent($this$gotoActivity, targetActivityClass);
        IntentExtKt.putExtra(i, intentKey1, intentValue1);
        IntentExtKt.putExtra(i, intentKey2, intentValue2);
        $this$gotoActivity.startActivity(i);
    }

    public static final void gotoActivity(AppCompatActivity $this$gotoActivity, Class<?> targetActivityClass, String intentKey1, Object intentValue1, String intentKey2, Object intentValue2, String intentKey3, Object intentValue3) {
        Intrinsics.checkNotNullParameter($this$gotoActivity, "<this>");
        Intrinsics.checkNotNullParameter(targetActivityClass, "targetActivityClass");
        Intrinsics.checkNotNullParameter(intentKey1, "intentKey1");
        Intrinsics.checkNotNullParameter(intentKey2, "intentKey2");
        Intrinsics.checkNotNullParameter(intentKey3, "intentKey3");
        Intent i = new Intent($this$gotoActivity, targetActivityClass);
        IntentExtKt.putExtra(i, intentKey1, intentValue1);
        IntentExtKt.putExtra(i, intentKey2, intentValue2);
        IntentExtKt.putExtra(i, intentKey3, intentValue3);
        $this$gotoActivity.startActivity(i);
    }

    public static final void gotoActivity(AppCompatActivity $this$gotoActivity, Class<?> targetActivityClass, String intentKey1, Object intentValue1, String intentKey2, Object intentValue2, String intentKey3, Object intentValue3, String intentKey4, Object intentValue4) {
        Intrinsics.checkNotNullParameter($this$gotoActivity, "<this>");
        Intrinsics.checkNotNullParameter(targetActivityClass, "targetActivityClass");
        Intrinsics.checkNotNullParameter(intentKey1, "intentKey1");
        Intrinsics.checkNotNullParameter(intentKey2, "intentKey2");
        Intrinsics.checkNotNullParameter(intentKey3, "intentKey3");
        Intrinsics.checkNotNullParameter(intentKey4, "intentKey4");
        Intent i = new Intent($this$gotoActivity, targetActivityClass);
        IntentExtKt.putExtra(i, intentKey1, intentValue1);
        IntentExtKt.putExtra(i, intentKey2, intentValue2);
        IntentExtKt.putExtra(i, intentKey3, intentValue3);
        IntentExtKt.putExtra(i, intentKey4, intentValue4);
        $this$gotoActivity.startActivity(i);
    }

    public static final void changeStatusBarColor(AppCompatActivity $this$changeStatusBarColor, int color) {
        Intrinsics.checkNotNullParameter($this$changeStatusBarColor, "<this>");
        Window window = $this$changeStatusBarColor.getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        window.setStatusBarColor(ContextCompat.getColor($this$changeStatusBarColor, color));
    }

    public static final void changeNavigationBarColor(AppCompatActivity $this$changeNavigationBarColor, int color) {
        Intrinsics.checkNotNullParameter($this$changeNavigationBarColor, "<this>");
        Window window = $this$changeNavigationBarColor.getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        window.setNavigationBarColor(ContextCompat.getColor($this$changeNavigationBarColor, color));
    }

    public static final void changeStatusBarColor(Fragment $this$changeStatusBarColor, int color) {
        Intrinsics.checkNotNullParameter($this$changeStatusBarColor, "<this>");
        Window window = $this$changeStatusBarColor.requireActivity().getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        window.setStatusBarColor(ContextCompat.getColor($this$changeStatusBarColor.requireContext(), color));
    }

    public static final void setTransparentStatusBar(AppCompatActivity $this$setTransparentStatusBar) {
        Intrinsics.checkNotNullParameter($this$setTransparentStatusBar, "<this>");
        $this$setTransparentStatusBar.getWindow().addFlags(Integer.MIN_VALUE);
        $this$setTransparentStatusBar.getWindow().clearFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        $this$setTransparentStatusBar.getWindow().getDecorView().setSystemUiVisibility(1024);
        $this$setTransparentStatusBar.getWindow().setStatusBarColor(0);
    }

    public static final void setStatusBarWithBlackIcon(AppCompatActivity $this$setStatusBarWithBlackIcon, int color) {
        Intrinsics.checkNotNullParameter($this$setStatusBarWithBlackIcon, "<this>");
        $this$setStatusBarWithBlackIcon.getWindow().addFlags(Integer.MIN_VALUE);
        $this$setStatusBarWithBlackIcon.getWindow().clearFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        $this$setStatusBarWithBlackIcon.getWindow().getDecorView().setSystemUiVisibility(1024);
        $this$setStatusBarWithBlackIcon.getWindow().getDecorView().setSystemUiVisibility(8192);
        $this$setStatusBarWithBlackIcon.getWindow().setStatusBarColor(ContextCompat.getColor($this$setStatusBarWithBlackIcon, color));
    }

    public static /* synthetic */ void showMaterialAlertDialog$default(AppCompatActivity appCompatActivity, String str, DialogListeners dialogListeners, int i, Object obj) {
        if ((i & 2) != 0) {
            dialogListeners = null;
        }
        showMaterialAlertDialog(appCompatActivity, str, dialogListeners);
    }

    public static final void showMaterialAlertDialog(AppCompatActivity $this$showMaterialAlertDialog, String message, final DialogListeners listener) {
        Intrinsics.checkNotNullParameter($this$showMaterialAlertDialog, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        View view = $this$showMaterialAlertDialog.getLayoutInflater().inflate(R.layout.dialog_message, (ViewGroup) null);
        TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);
        AlertDialog dialog = new MaterialAlertDialogBuilder($this$showMaterialAlertDialog).setView(view).setCancelable(false).setPositiveButton((CharSequence) $this$showMaterialAlertDialog.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ActivityExtKt.showMaterialAlertDialog$lambda$0(DialogListeners.this, dialogInterface, i);
            }
        }).setNegativeButton((CharSequence) $this$showMaterialAlertDialog.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ActivityExtKt.showMaterialAlertDialog$lambda$1(DialogListeners.this, dialogInterface, i);
            }
        }).show();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(R.drawable.dialog_background_all);
        }
        Button positiveButton = dialog.getButton(-1);
        Button negativeButton = dialog.getButton(-2);
        positiveButton.setTextColor(ContextCompat.getColor($this$showMaterialAlertDialog, R.color.white));
        negativeButton.setTextColor(ContextCompat.getColor($this$showMaterialAlertDialog, R.color.white));
        positiveButton.setTextSize(20.0f);
        negativeButton.setTextSize(20.0f);
        positiveButton.setTypeface(positiveButton.getTypeface(), 1);
        negativeButton.setTypeface(negativeButton.getTypeface(), 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMaterialAlertDialog$lambda$0(DialogListeners $listener, DialogInterface dialogInterface, int i) {
        if ($listener != null) {
            $listener.onPositionButtonTap(dialogInterface);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMaterialAlertDialog$lambda$1(DialogListeners $listener, DialogInterface dialogInterface, int i) {
        if ($listener != null) {
            $listener.onNegativeButtonTap(dialogInterface);
        }
    }

    public static final <T> List<T> customFilterList(List<? extends T> list, Function1<? super T, Boolean> filterFunction) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(filterFunction, "filterFunction");
        List resultList = new ArrayList();
        for (Object item : list) {
            if (filterFunction.invoke(item).booleanValue()) {
                resultList.add(item);
            }
        }
        return resultList;
    }

    public static final void moveNextAct(AppCompatActivity $this$moveNextAct, Class<?> targetActivityClass) {
        Intrinsics.checkNotNullParameter($this$moveNextAct, "<this>");
        Intrinsics.checkNotNullParameter(targetActivityClass, "targetActivityClass");
        Intent i = new Intent($this$moveNextAct, targetActivityClass);
        $this$moveNextAct.startActivity(i);
    }
}
