package com.ingenious.androidbookmarksalesupgrade.utils;

import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.webkit.internal.AssetHelper;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils;
import com.ingenious.androidbookmarksalesupgrade.storage.AppPreferences;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/* compiled from: Utils.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007J\u0006\u0010\f\u001a\u00020\u0005J\u001e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014¨\u0006\u0015"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/utils/Utils;", "", "<init>", "()V", "isValidEmail", "", "email", "", "isOnline", "getSimpleTextBody", "Lokhttp3/RequestBody;", "param", "isLogin", "showDatePickerDialog", "", "textView", "Landroid/widget/TextView;", "validator", "Lcom/google/android/material/datepicker/CalendarConstraints$DateValidator;", "manager", "Landroidx/fragment/app/FragmentManager;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final class Utils {
    public static final Utils INSTANCE = new Utils();

    private Utils() {
    }

    public final boolean isValidEmail(String email) {
        Intrinsics.checkNotNullParameter(email, "email");
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public final boolean isOnline() {
        Object systemService = InjectUtils.INSTANCE.getAppContext().getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager cm = (ConnectivityManager) systemService;
        return cm.getActiveNetworkInfo() != null;
    }

    public final RequestBody getSimpleTextBody(String param) {
        Intrinsics.checkNotNullParameter(param, "param");
        return RequestBody.INSTANCE.create(param, MediaType.INSTANCE.parse(AssetHelper.DEFAULT_MIME_TYPE));
    }

    public final boolean isLogin() {
        if (AppPreferences.INSTANCE.getLoginData() != null) {
            return true;
        }
        return false;
    }

    public final void showDatePickerDialog(final TextView textView, CalendarConstraints.DateValidator validator, FragmentManager manager) {
        Intrinsics.checkNotNullParameter(textView, "textView");
        Intrinsics.checkNotNullParameter(validator, "validator");
        Intrinsics.checkNotNullParameter(manager, "manager");
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder().setValidator(validator);
        Intrinsics.checkNotNullExpressionValue(constraintsBuilder, "setValidator(...)");
        final MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").setSelection(Long.valueOf(MaterialDatePicker.todayInUtcMilliseconds())).setCalendarConstraints(constraintsBuilder.build()).build();
        Intrinsics.checkNotNullExpressionValue(datePicker, "build(...)");
        datePicker.show(manager, "TAG");
        final Function1 function1 = new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.utils.Utils$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit showDatePickerDialog$lambda$2;
                showDatePickerDialog$lambda$2 = Utils.showDatePickerDialog$lambda$2(textView, datePicker, (Long) obj);
                return showDatePickerDialog$lambda$2;
            }
        };
        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.utils.Utils$$ExternalSyntheticLambda1
            @Override // com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
            public final void onPositiveButtonClick(Object obj) {
                Function1.this.invoke(obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showDatePickerDialog$lambda$2(TextView $textView, MaterialDatePicker $datePicker, Long it) {
        $textView.setText($datePicker.getHeaderText());
        return Unit.INSTANCE;
    }
}
