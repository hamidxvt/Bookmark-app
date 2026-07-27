package com.ingenious.androidbookmarksalesupgrade.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import com.ingenious.androidbookmarksalesupgrade.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Extension.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\n\u0010\u0005\u001a\u00020\u0004*\u00020\u0002\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0007\u001a\f\u0010\b\u001a\u00020\u0001*\u00020\u0007H\u0002¨\u0006\t"}, d2 = {"setVisitId", "", "Landroid/content/Context;", "visitId", "", "getVisitId", "belowStatusBarText", "Landroid/app/Activity;", "hideSystemBars", "app_debug"}, k = 2, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final class ExtensionKt {
    public static final void setVisitId(Context $this$setVisitId, String visitId) {
        Intrinsics.checkNotNullParameter($this$setVisitId, "<this>");
        Intrinsics.checkNotNullParameter(visitId, "visitId");
        SharedPreferences sharedPref = $this$setVisitId.getSharedPreferences("BookmarkApp", 0);
        sharedPref.edit().putString("VISIT_ID", visitId).apply();
    }

    public static final String getVisitId(Context $this$getVisitId) {
        Intrinsics.checkNotNullParameter($this$getVisitId, "<this>");
        SharedPreferences sharedPref = $this$getVisitId.getSharedPreferences("BookmarkApp", 0);
        String string = sharedPref.getString("VISIT_ID", "");
        return string == null ? "" : string;
    }

    public static final void belowStatusBarText(Activity $this$belowStatusBarText) {
        Intrinsics.checkNotNullParameter($this$belowStatusBarText, "<this>");
        WindowCompat.setDecorFitsSystemWindows($this$belowStatusBarText.getWindow(), false);
        View rootView = $this$belowStatusBarText.findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(rootView, new OnApplyWindowInsetsListener() { // from class: com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat belowStatusBarText$lambda$0;
                belowStatusBarText$lambda$0 = ExtensionKt.belowStatusBarText$lambda$0(view, windowInsetsCompat);
                return belowStatusBarText$lambda$0;
            }
        });
        hideSystemBars($this$belowStatusBarText);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat belowStatusBarText$lambda$0(View v, WindowInsetsCompat insets) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(insets, "insets");
        int statusBarHeight = insets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.statusBars()).top;
        v.setPadding(0, statusBarHeight, 0, 0);
        return insets;
    }

    private static final void hideSystemBars(Activity $this$hideSystemBars) {
        WindowInsetsControllerCompat controller = WindowCompat.getInsetsController($this$hideSystemBars.getWindow(), $this$hideSystemBars.getWindow().getDecorView());
        Intrinsics.checkNotNullExpressionValue(controller, "getInsetsController(...)");
        controller.setSystemBarsBehavior(2);
        controller.hide(WindowInsetsCompat.Type.systemBars());
    }
}
