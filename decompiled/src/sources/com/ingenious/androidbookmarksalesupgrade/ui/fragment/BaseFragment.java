package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewbinding.ViewBinding;
import com.ingenious.androidbookmarksalesupgrade.databinding.LayoutLoadingBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt;
import com.ingenious.androidbookmarksalesupgrade.extensions.DialogExtKt;
import com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.network.domain.APIError;
import com.ingenious.androidbookmarksalesupgrade.network.domain.BaseDataSource;
import com.ingenious.androidbookmarksalesupgrade.network.domain.ErrorHandler;
import com.ingenious.androidbookmarksalesupgrade.storage.AppPreferences;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.HomeActivity;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.LoginActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseFragment.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J&\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ5\u0010\u001e\u001a\u00020\u001a\"\u0004\b\u0001\u0010\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001f0!2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u001a0#H\u0000¢\u0006\u0002\b%R\u001c\u0010\u0006\u001a\u00028\u0000X\u0086.¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR,\u0010\f\u001a\u001c\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00028\u00000\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006&"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/BaseFragment;", "VB", "Landroidx/viewbinding/ViewBinding;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "binding", "getBinding", "()Landroidx/viewbinding/ViewBinding;", "setBinding", "(Landroidx/viewbinding/ViewBinding;)V", "Landroidx/viewbinding/ViewBinding;", "bindingInflater", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "", "getBindingInflater", "()Lkotlin/jvm/functions/Function3;", "onCreateView", "Landroid/view/View;", "inflater", "container", "savedInstanceState", "Landroid/os/Bundle;", "showProgressIndicator", "", "layoutLoadingBinding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/LayoutLoadingBinding;", "hideProgressIndicator", "genericNetworkErrorHandler", "T", "resource", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "passError", "Lkotlin/Function1;", "Lcom/ingenious/androidbookmarksalesupgrade/network/domain/ErrorHandler;", "genericNetworkErrorHandler$app_debug", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {
    public VB binding;

    public abstract Function3<LayoutInflater, ViewGroup, Boolean, VB> getBindingInflater();

    public final VB getBinding() {
        VB vb = this.binding;
        if (vb != null) {
            return vb;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(VB vb) {
        Intrinsics.checkNotNullParameter(vb, "<set-?>");
        this.binding = vb;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        setBinding(getBindingInflater().invoke(inflater, container, false));
        return getBinding().getRoot();
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

    public final <T> void genericNetworkErrorHandler$app_debug(ApiResponseCallback<T> resource, final Function1<? super ErrorHandler, Unit> passError) {
        Intrinsics.checkNotNullParameter(resource, "resource");
        Intrinsics.checkNotNullParameter(passError, "passError");
        BaseDataSource.INSTANCE.networkCallFailed(resource, new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit genericNetworkErrorHandler$lambda$0;
                genericNetworkErrorHandler$lambda$0 = BaseFragment.genericNetworkErrorHandler$lambda$0(Function1.this, this, (ErrorHandler) obj);
                return genericNetworkErrorHandler$lambda$0;
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
        com.ingenious.androidbookmarksalesupgrade.extensions.DialogExtKt.showMaterialAlertDialog$default(r6, r1, null, 2, null);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Unit genericNetworkErrorHandler$lambda$0(Function1 $passError, final BaseFragment this$0, ErrorHandler errorHandler) {
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
                    DialogExtKt.showMaterialAlertDialog(this$0, string, new DialogListeners(this$0) { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment$genericNetworkErrorHandler$1$2
                        final /* synthetic */ BaseFragment<VB> this$0;

                        {
                            this.this$0 = this$0;
                        }

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
                            ActivityExtKt.gotoActivityWithNoHistoryFromFragment(this.this$0, LoginActivity.class);
                        }
                    });
                    break;
                }
                DialogExtKt.showMaterialAlertDialog$default(this$0, errorHandler.getMessage(), null, 2, null);
                break;
            case 620910836:
                if (errorStatus.equals("unauthorized")) {
                    String string2 = this$0.getString(errorHandler.getMessageID());
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    DialogExtKt.showMaterialAlertDialog(this$0, string2, new DialogListeners(this$0) { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment$genericNetworkErrorHandler$1$1
                        final /* synthetic */ BaseFragment<VB> this$0;

                        {
                            this.this$0 = this$0;
                        }

                        @Override // com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners
                        public void onPositionButtonTap(DialogInterface dialog) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            AppPreferences.INSTANCE.setLoginData(null);
                            FragmentActivity requireActivity = this.this$0.requireActivity();
                            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.ingenious.androidbookmarksalesupgrade.ui.activity.HomeActivity");
                            ActivityExtKt.gotoActivityWithNoHistory((HomeActivity) requireActivity, LoginActivity.class);
                        }

                        @Override // com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners
                        public void onNegativeButtonTap(DialogInterface dialog) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                        }
                    });
                    break;
                }
                DialogExtKt.showMaterialAlertDialog$default(this$0, errorHandler.getMessage(), null, 2, null);
                break;
            case 1207582805:
                break;
            case 1615526678:
                break;
            default:
                DialogExtKt.showMaterialAlertDialog$default(this$0, errorHandler.getMessage(), null, 2, null);
                break;
        }
        return Unit.INSTANCE;
    }
}
