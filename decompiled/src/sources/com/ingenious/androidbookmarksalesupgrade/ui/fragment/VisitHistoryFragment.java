package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.messaging.Constants;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter;
import com.ingenious.androidbookmarksalesupgrade.bottomsheet.VisitDetailsBottomSheet;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitHistoryBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.DialogExtKt;
import com.ingenious.androidbookmarksalesupgrade.model.response.ApprovedVisitsLists;
import com.ingenious.androidbookmarksalesupgrade.model.response.ApprovedVisitsResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: VisitHistoryFragment.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001eB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0018H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR.\u0010\u000b\u001a\u001c\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/VisitHistoryFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/BaseFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentVisitHistoryBinding;", "<init>", "()V", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "bindingInflater", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "", "getBindingInflater", "()Lkotlin/jvm/functions/Function3;", "customerId", "", "approvedVisitsListAdapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ApprovedVisitsLists;", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setupAdapter", "Companion", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class VisitHistoryFragment extends BaseFragment<FragmentVisitHistoryBinding> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private GenericAdapter<ApprovedVisitsLists> approvedVisitsListAdapter;
    private String customerId;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public VisitHistoryFragment() {
        final VisitHistoryFragment $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitHistoryFragment$special$$inlined$viewModel$default$1
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
        final Function0 ownerProducer$iv$iv = new Function0<ViewModelStoreOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitHistoryFragment$special$$inlined$viewModel$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return ((ViewModelOwner) Function0.this.invoke()).getStoreOwner();
            }
        };
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitHistoryFragment$special$$inlined$viewModel$default$3
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
                ViewModelParameter viewModelParameters$iv = new ViewModelParameter(Reflection.getOrCreateKotlinClass(VisitViewModel.class), qualifier$iv2, null, parameters$iv2, ownerValue$iv.getStoreOwner(), ownerValue$iv.getStateRegistry());
                return ViewModelResolverKt.pickFactory(scope$iv2, viewModelParameters$iv);
            }
        };
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default$iv, Reflection.getOrCreateKotlinClass(VisitViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitHistoryFragment$special$$inlined$viewModel$default$4
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

    private final VisitViewModel getViewModel() {
        return (VisitViewModel) this.viewModel.getValue();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment
    public Function3<LayoutInflater, ViewGroup, Boolean, FragmentVisitHistoryBinding> getBindingInflater() {
        return VisitHistoryFragment$bindingInflater$1.INSTANCE;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        this.customerId = arguments != null ? arguments.getString("customerId") : null;
        setupAdapter();
        getViewModel().approvedVisits(String.valueOf(this.customerId));
        getViewModel().getApprovedVisitsResponse().observe(getViewLifecycleOwner(), new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitHistoryFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VisitHistoryFragment.onViewCreated$lambda$2(VisitHistoryFragment.this, (ApiResponseCallback) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onViewCreated$lambda$2(VisitHistoryFragment this$0, ApiResponseCallback it) {
        if (it != null) {
            GenericAdapter<ApprovedVisitsLists> genericAdapter = null;
            if (it instanceof ApiResponseCallback.Error) {
                Log.e(Constants.IPC_BUNDLE_KEY_SEND_ERROR, "Error: " + ((ApiResponseCallback.Error) it).getMessage());
                String message = ((ApiResponseCallback.Error) it).getMessage();
                Intrinsics.checkNotNull(message);
                DialogExtKt.showMaterialAlertDialog$default(this$0, message, null, 2, null);
                return;
            }
            if (!(it instanceof ApiResponseCallback.Loading)) {
                if (!(it instanceof ApiResponseCallback.Success)) {
                    throw new NoWhenBranchMatchedException();
                }
                ApprovedVisitsResponse data = (ApprovedVisitsResponse) ((ApiResponseCallback.Success) it).getData();
                if (data != null && Intrinsics.areEqual((Object) data.getSuccess(), (Object) true)) {
                    if (!data.getApprovedVisits().isEmpty()) {
                        GenericAdapter<ApprovedVisitsLists> genericAdapter2 = this$0.approvedVisitsListAdapter;
                        if (genericAdapter2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("approvedVisitsListAdapter");
                        } else {
                            genericAdapter = genericAdapter2;
                        }
                        genericAdapter.addList(data.getApprovedVisits());
                        this$0.getBinding().approvedVisitsRv.setVisibility(0);
                        this$0.getBinding().adoptionLinear.setVisibility(8);
                        return;
                    }
                    this$0.getBinding().approvedVisitsRv.setVisibility(8);
                    this$0.getBinding().adoptionLinear.setVisibility(0);
                }
            }
        }
    }

    private final void setupAdapter() {
        this.approvedVisitsListAdapter = new GenericAdapter<>(R.layout.item_approved_visits_list, new GenericAdapter.OnItemClickListener<ApprovedVisitsLists>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitHistoryFragment$setupAdapter$1
            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onCall(int visitId) {
                GenericAdapter.OnItemClickListener.DefaultImpls.onCall(this, visitId);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onItemClickTwo(ApprovedVisitsLists item) {
                GenericAdapter.OnItemClickListener.DefaultImpls.onItemClickTwo(this, item);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onSelectionChanged(List<? extends ApprovedVisitsLists> list) {
                GenericAdapter.OnItemClickListener.DefaultImpls.onSelectionChanged(this, list);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onItemClick(ApprovedVisitsLists item) {
                Intrinsics.checkNotNullParameter(item, "item");
                VisitDetailsBottomSheet bottomSheet = VisitDetailsBottomSheet.Companion.newInstance(item);
                bottomSheet.show(VisitHistoryFragment.this.getParentFragmentManager(), "visitDetailsBottomSheet");
                Log.i("TAG", "onItemClick: " + item);
            }
        });
        RecyclerView $this$setupAdapter_u24lambda_u243 = getBinding().approvedVisitsRv;
        GenericAdapter<ApprovedVisitsLists> genericAdapter = this.approvedVisitsListAdapter;
        if (genericAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("approvedVisitsListAdapter");
            genericAdapter = null;
        }
        $this$setupAdapter_u24lambda_u243.setAdapter(genericAdapter);
        $this$setupAdapter_u24lambda_u243.setLayoutManager(new LinearLayoutManager(requireContext(), 1, false));
    }

    /* compiled from: VisitHistoryFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/VisitHistoryFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/VisitHistoryFragment;", "customerId", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VisitHistoryFragment newInstance(String customerId) {
            Intrinsics.checkNotNullParameter(customerId, "customerId");
            VisitHistoryFragment fragment = new VisitHistoryFragment();
            Bundle args = new Bundle();
            args.putString("customerId", customerId);
            fragment.setArguments(args);
            return fragment;
        }
    }
}
