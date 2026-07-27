package com.ingenious.androidbookmarksalesupgrade.bottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import com.google.firebase.messaging.Constants;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.databinding.BottomSheetCustomerDetailsBinding;
import com.ingenious.androidbookmarksalesupgrade.model.response.CustomersData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomerDetailBottomSheet.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/bottomsheet/CustomerDetailBottomSheet;", "Landroidx/fragment/app/DialogFragment;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/BottomSheetCustomerDetailsBinding;", "customerData", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/CustomersData;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "getTheme", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class CustomerDetailBottomSheet extends DialogFragment {
    private BottomSheetCustomerDetailsBinding binding;
    private CustomersData customerData;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.binding = BottomSheetCustomerDetailsBinding.inflate(inflater, container, false);
        BottomSheetCustomerDetailsBinding bottomSheetCustomerDetailsBinding = this.binding;
        BottomSheetCustomerDetailsBinding bottomSheetCustomerDetailsBinding2 = null;
        if (bottomSheetCustomerDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            bottomSheetCustomerDetailsBinding = null;
        }
        bottomSheetCustomerDetailsBinding.crossBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.CustomerDetailBottomSheet$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomerDetailBottomSheet.this.dismiss();
            }
        });
        Bundle arguments = getArguments();
        this.customerData = arguments != null ? (CustomersData) arguments.getParcelable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE) : null;
        CustomersData data = this.customerData;
        if (data != null) {
            BottomSheetCustomerDetailsBinding bottomSheetCustomerDetailsBinding3 = this.binding;
            if (bottomSheetCustomerDetailsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                bottomSheetCustomerDetailsBinding3 = null;
            }
            bottomSheetCustomerDetailsBinding3.self.setText(data.getAddby());
            BottomSheetCustomerDetailsBinding bottomSheetCustomerDetailsBinding4 = this.binding;
            if (bottomSheetCustomerDetailsBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                bottomSheetCustomerDetailsBinding4 = null;
            }
            bottomSheetCustomerDetailsBinding4.test.setText(data.getName());
            BottomSheetCustomerDetailsBinding bottomSheetCustomerDetailsBinding5 = this.binding;
            if (bottomSheetCustomerDetailsBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                bottomSheetCustomerDetailsBinding5 = null;
            }
            bottomSheetCustomerDetailsBinding5.detail.setText(data.getAddress());
            BottomSheetCustomerDetailsBinding bottomSheetCustomerDetailsBinding6 = this.binding;
            if (bottomSheetCustomerDetailsBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                bottomSheetCustomerDetailsBinding6 = null;
            }
            bottomSheetCustomerDetailsBinding6.time.setText(data.getDaysAgo());
        }
        BottomSheetCustomerDetailsBinding bottomSheetCustomerDetailsBinding7 = this.binding;
        if (bottomSheetCustomerDetailsBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            bottomSheetCustomerDetailsBinding2 = bottomSheetCustomerDetailsBinding7;
        }
        View root = bottomSheetCustomerDetailsBinding2.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.DialogFragment
    public int getTheme() {
        return R.style.RoundedDialogTheme;
    }
}
