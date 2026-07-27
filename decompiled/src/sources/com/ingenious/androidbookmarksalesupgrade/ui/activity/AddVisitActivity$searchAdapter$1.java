package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityAddVisitBinding;
import com.ingenious.androidbookmarksalesupgrade.model.response.LastVisitCustomerResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.SearchData;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddVisitActivity.kt */
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0002H\u0016¨\u0006\u0006"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/activity/AddVisitActivity$searchAdapter$1", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter$OnItemClickListener;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/SearchData;", "onItemClick", "", "item", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class AddVisitActivity$searchAdapter$1 implements GenericAdapter.OnItemClickListener<SearchData> {
    final /* synthetic */ AddVisitActivity this$0;

    AddVisitActivity$searchAdapter$1(AddVisitActivity $receiver) {
        this.this$0 = $receiver;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
    public void onCall(int visitId) {
        GenericAdapter.OnItemClickListener.DefaultImpls.onCall(this, visitId);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
    public void onItemClickTwo(SearchData item) {
        GenericAdapter.OnItemClickListener.DefaultImpls.onItemClickTwo(this, item);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
    public void onSelectionChanged(List<? extends SearchData> list) {
        GenericAdapter.OnItemClickListener.DefaultImpls.onSelectionChanged(this, list);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
    public void onItemClick(SearchData item) {
        ActivityAddVisitBinding activityAddVisitBinding;
        VisitViewModel viewModel;
        VisitViewModel viewModel2;
        Intrinsics.checkNotNullParameter(item, "item");
        activityAddVisitBinding = this.this$0.binding;
        if (activityAddVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddVisitBinding = null;
        }
        activityAddVisitBinding.customerNameEt.setText(String.valueOf(item.getBusinessName()));
        this.this$0.setCustomerId(String.valueOf(item.getId()));
        viewModel = this.this$0.getViewModel();
        viewModel.lastVisitCustomer(String.valueOf(item.getId()));
        viewModel2 = this.this$0.getViewModel();
        LiveData<ApiResponseCallback<LastVisitCustomerResponse>> lastVisitCustomerResponse = viewModel2.getLastVisitCustomerResponse();
        AddVisitActivity addVisitActivity = this.this$0;
        final AddVisitActivity addVisitActivity2 = this.this$0;
        lastVisitCustomerResponse.observe(addVisitActivity, new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity$searchAdapter$1$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddVisitActivity$searchAdapter$1.onItemClick$lambda$2(AddVisitActivity.this, (ApiResponseCallback) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onItemClick$lambda$2(AddVisitActivity this$0, ApiResponseCallback it) {
        ActivityAddVisitBinding activityAddVisitBinding;
        if (it != null && !(it instanceof ApiResponseCallback.Error) && !(it instanceof ApiResponseCallback.Loading)) {
            if (!(it instanceof ApiResponseCallback.Success)) {
                throw new NoWhenBranchMatchedException();
            }
            LastVisitCustomerResponse data = (LastVisitCustomerResponse) ((ApiResponseCallback.Success) it).getData();
            if (data != null) {
                Boolean success = data.getSuccess();
                Intrinsics.checkNotNull(success);
                if (success.booleanValue()) {
                    activityAddVisitBinding = this$0.binding;
                    if (activityAddVisitBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddVisitBinding = null;
                    }
                    activityAddVisitBinding.setItem(data.getData());
                }
            }
        }
    }
}
