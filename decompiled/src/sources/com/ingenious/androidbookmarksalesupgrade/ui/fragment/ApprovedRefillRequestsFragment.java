package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.savedstate.SavedStateRegistryOwner;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentApprovedRefillRequestsBinding;
import com.ingenious.androidbookmarksalesupgrade.model.response.RefillByStatusData;
import com.ingenious.androidbookmarksalesupgrade.model.response.RefillByStatusResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.viewModel.MainViewModel;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: ApprovedRefillRequestsFragment.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0016H\u0002R.\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/ApprovedRefillRequestsFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/BaseFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentApprovedRefillRequestsBinding;", "<init>", "()V", "bindingInflater", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "", "getBindingInflater", "()Lkotlin/jvm/functions/Function3;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/MainViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "adapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/RefillByStatusData;", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setupRecyclerView", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class ApprovedRefillRequestsFragment extends BaseFragment<FragmentApprovedRefillRequestsBinding> {
    private GenericAdapter<RefillByStatusData> adapter;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public ApprovedRefillRequestsFragment() {
        final ApprovedRefillRequestsFragment $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.ApprovedRefillRequestsFragment$special$$inlined$viewModel$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelOwner invoke() {
                ViewModelOwner.Companion companion = ViewModelOwner.INSTANCE;
                Fragment fragment = Fragment.this;
                Fragment fragment2 = Fragment.this;
                return companion.from(fragment, fragment2 instanceof SavedStateRegistryOwner ? fragment2 : null);
            }
        };
        final Function0 parameters$iv = null;
        final Scope scope$iv = AndroidKoinScopeExtKt.getKoinScope($this$viewModel_u24default$iv);
        final Function0 ownerProducer$iv$iv = new Function0<ViewModelStoreOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.ApprovedRefillRequestsFragment$special$$inlined$viewModel$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return ((ViewModelOwner) Function0.this.invoke()).getStoreOwner();
            }
        };
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.ApprovedRefillRequestsFragment$special$$inlined$viewModel$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                Function0 owner$iv2 = Function0.this;
                Qualifier qualifier$iv2 = qualifier$iv;
                Function0 parameters$iv2 = parameters$iv;
                Scope scope$iv2 = scope$iv;
                ViewModelOwner ownerValue$iv = (ViewModelOwner) owner$iv2.invoke();
                ViewModelParameter viewModelParameters$iv = new ViewModelParameter(Reflection.getOrCreateKotlinClass(MainViewModel.class), qualifier$iv2, null, parameters$iv2, ownerValue$iv.getStoreOwner(), ownerValue$iv.getStateRegistry());
                return ViewModelResolverKt.pickFactory(scope$iv2, viewModelParameters$iv);
            }
        };
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default$iv, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.ApprovedRefillRequestsFragment$special$$inlined$viewModel$default$4
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) Function0.this.invoke()).getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "ownerProducer().viewModelStore");
                return viewModelStore;
            }
        }, factoryProducer$iv$iv);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment
    public Function3<LayoutInflater, ViewGroup, Boolean, FragmentApprovedRefillRequestsBinding> getBindingInflater() {
        return ApprovedRefillRequestsFragment$bindingInflater$1.INSTANCE;
    }

    private final MainViewModel getViewModel() {
        return (MainViewModel) this.viewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        getViewModel().refillByStatusRequest("approved");
        setupRecyclerView();
        getViewModel().getRefillResponse().observe(getViewLifecycleOwner(), new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.ApprovedRefillRequestsFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ApprovedRefillRequestsFragment.onViewCreated$lambda$2(ApprovedRefillRequestsFragment.this, (ApiResponseCallback) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onViewCreated$lambda$2(ApprovedRefillRequestsFragment this$0, ApiResponseCallback it) {
        if (it != null && !(it instanceof ApiResponseCallback.Error) && !(it instanceof ApiResponseCallback.Loading)) {
            if (!(it instanceof ApiResponseCallback.Success)) {
                throw new NoWhenBranchMatchedException();
            }
            RefillByStatusResponse data = (RefillByStatusResponse) ((ApiResponseCallback.Success) it).getData();
            if (data != null) {
                Boolean success = data.getSuccess();
                Intrinsics.checkNotNull(success);
                if (success.booleanValue()) {
                    GenericAdapter<RefillByStatusData> genericAdapter = this$0.adapter;
                    if (genericAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        genericAdapter = null;
                    }
                    genericAdapter.addList(data.getData());
                }
            }
        }
    }

    private final void setupRecyclerView() {
        GenericAdapter<RefillByStatusData> genericAdapter = null;
        this.adapter = new GenericAdapter<>(R.layout.item_request_list, null, 2, null);
        RecyclerView recyclerView = getBinding().refillApprovedRequestRv;
        GenericAdapter<RefillByStatusData> genericAdapter2 = this.adapter;
        if (genericAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            genericAdapter = genericAdapter2;
        }
        recyclerView.setAdapter(genericAdapter);
        getBinding().refillApprovedRequestRv.setLayoutManager(new LinearLayoutManager(requireContext(), 1, false));
    }
}
