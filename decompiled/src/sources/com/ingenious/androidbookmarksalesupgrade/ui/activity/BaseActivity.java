package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.tasks.OnSuccessListener;
import com.ingenious.androidbookmarksalesupgrade.databinding.LayoutLoadingBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt;
import com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.network.domain.APIError;
import com.ingenious.androidbookmarksalesupgrade.network.domain.ErrorHandler;
import com.ingenious.androidbookmarksalesupgrade.storage.AppPreferences;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseActivity.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J>\u0010\n\u001a\u00020\u000726\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00070\fJ\u000e\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014J.\u0010\u0016\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00070\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/BaseActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "getCurrentLocation", "onLocationRetrieved", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "latitude", "longitude", "showProgressIndicator", "layoutLoadingBinding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/LayoutLoadingBinding;", "hideProgressIndicator", "genericNetworkErrorHandler", "T", "response", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "passError", "Lkotlin/Function1;", "Lcom/ingenious/androidbookmarksalesupgrade/network/domain/ErrorHandler;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public class BaseActivity extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationClient;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(1);
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient((Activity) this);
    }

    public final void getCurrentLocation(final Function2<? super Double, ? super Double, Unit> onLocationRetrieved) {
        Intrinsics.checkNotNullParameter(onLocationRetrieved, "onLocationRetrieved");
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            return;
        }
        FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationClient;
        if (fusedLocationProviderClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fusedLocationClient");
            fusedLocationProviderClient = null;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.BaseActivity$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                BaseActivity.getCurrentLocation$lambda$2(BaseActivity.this, onLocationRetrieved, (Location) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getCurrentLocation$lambda$2(BaseActivity this$0, Function2 $onLocationRetrieved, Location location) {
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            $onLocationRetrieved.invoke(Double.valueOf(latitude), Double.valueOf(longitude));
            return;
        }
        $onLocationRetrieved.invoke(Double.valueOf(Utils.DOUBLE_EPSILON), Double.valueOf(Utils.DOUBLE_EPSILON));
    }

    public final void showProgressIndicator(LayoutLoadingBinding layoutLoadingBinding) {
        Intrinsics.checkNotNullParameter(layoutLoadingBinding, "layoutLoadingBinding");
        LinearLayout root = layoutLoadingBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ActivityExtKt.visible(root, true);
    }

    public final void hideProgressIndicator(LayoutLoadingBinding layoutLoadingBinding) {
        Intrinsics.checkNotNullParameter(layoutLoadingBinding, "layoutLoadingBinding");
        LinearLayout root = layoutLoadingBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ActivityExtKt.visible(root, false);
    }

    public final <T> void genericNetworkErrorHandler(ApiResponseCallback<T> response, final Function1<? super ErrorHandler, Unit> passError) {
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(passError, "passError");
        APIError.INSTANCE.networkCallFailed(response, new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.BaseActivity$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit genericNetworkErrorHandler$lambda$3;
                genericNetworkErrorHandler$lambda$3 = BaseActivity.genericNetworkErrorHandler$lambda$3(Function1.this, this, (ErrorHandler) obj);
                return genericNetworkErrorHandler$lambda$3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0028, code lost:
    
        if (r0.equals("bad_request") == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0076, code lost:
    
        if (r0.equals("service_unavailable") == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x007f, code lost:
    
        if (r0.equals("unexpected_error") == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0088, code lost:
    
        if (r0.equals(com.ingenious.androidbookmarksalesupgrade.network.domain.APIError.INTERNAL_SERVER_ERROR) == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0091, code lost:
    
        if (r0.equals("network_failed") == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x009a, code lost:
    
        if (r0.equals("server_error") == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x001f, code lost:
    
        if (r0.equals("not_found") == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x009e, code lost:
    
        r1 = r6.getString(r7.getMessageID());
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, "getString(...)");
        com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt.showMaterialAlertDialog$default(r6, r1, null, 2, null);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Unit genericNetworkErrorHandler$lambda$3(Function1 $passError, final BaseActivity this$0, ErrorHandler errorHandler) {
        Intrinsics.checkNotNullParameter(errorHandler, "errorHandler");
        $passError.invoke(errorHandler);
        String errorStatus = errorHandler.getErrorStatus();
        switch (errorStatus.hashCode()) {
            case -2054838772:
                break;
            case -1941829778:
                break;
            case -1489705906:
                break;
            case -1361010534:
                break;
            case -693070394:
                break;
            case 256887771:
                if (errorStatus.equals(APIError.BLOCK_BY_ADMIN_MSG)) {
                    String string = this$0.getString(errorHandler.getMessageID());
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    ActivityExtKt.showMaterialAlertDialog(this$0, string, new DialogListeners() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.BaseActivity$genericNetworkErrorHandler$1$2
                        @Override // com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners
                        public void onNegativeButtonTap(DialogInterface dialog) {
                            DialogListeners.DefaultImpls.onNegativeButtonTap(this, dialog);
                        }

                        @Override // com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners
                        public void onPositionButtonTap(DialogInterface dialog) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            AppPreferences.INSTANCE.setLoginData(null);
                            ActivityExtKt.gotoActivityWithNoHistory(BaseActivity.this, LoginActivity.class);
                        }
                    });
                    break;
                }
                ActivityExtKt.showMaterialAlertDialog$default(this$0, errorHandler.getMessage(), null, 2, null);
                break;
            case 620910836:
                if (errorStatus.equals("unauthorized")) {
                    String string2 = this$0.getString(errorHandler.getMessageID());
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    ActivityExtKt.showMaterialAlertDialog(this$0, string2, new DialogListeners() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.BaseActivity$genericNetworkErrorHandler$1$1
                        @Override // com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners
                        public void onNegativeButtonTap(DialogInterface dialog) {
                            DialogListeners.DefaultImpls.onNegativeButtonTap(this, dialog);
                        }

                        @Override // com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners
                        public void onPositionButtonTap(DialogInterface dialog) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            AppPreferences.INSTANCE.setLoginData(null);
                            ActivityExtKt.gotoActivityWithNoHistory(BaseActivity.this, LoginActivity.class);
                        }
                    });
                    break;
                }
                ActivityExtKt.showMaterialAlertDialog$default(this$0, errorHandler.getMessage(), null, 2, null);
                break;
            case 1207582805:
                break;
            case 1615526678:
                break;
            default:
                ActivityExtKt.showMaterialAlertDialog$default(this$0, errorHandler.getMessage(), null, 2, null);
                break;
        }
        return Unit.INSTANCE;
    }
}
