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
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter;
import com.ingenious.androidbookmarksalesupgrade.bottomsheet.AdoptionDetailsBottomSheet;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitAdoptionBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionsList;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.VisitAdoptionActivity;
import com.ingenious.androidbookmarksalesupgrade.utils.AppToast;
import com.ingenious.androidbookmarksalesupgrade.viewModel.MainViewModel;
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

/* compiled from: VisitAdoptionFragment.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000  2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001 B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u001a\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001e\u001a\u00020\u0018H\u0016J\b\u0010\u001f\u001a\u00020\u0018H\u0002R.\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/VisitAdoptionFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/BaseFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentVisitAdoptionBinding;", "<init>", "()V", "bindingInflater", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "", "getBindingInflater", "()Lkotlin/jvm/functions/Function3;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/MainViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "adapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionsList;", "customerId", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "Landroid/view/View;", "onResume", "setupRecyclerView", "Companion", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class VisitAdoptionFragment extends BaseFragment<FragmentVisitAdoptionBinding> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private GenericAdapter<AdoptionsList> adapter;
    private String customerId;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public VisitAdoptionFragment() {
        final VisitAdoptionFragment $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionFragment$special$$inlined$viewModel$default$1
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
        final Function0 ownerProducer$iv$iv = new Function0<ViewModelStoreOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionFragment$special$$inlined$viewModel$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return ((ViewModelOwner) Function0.this.invoke()).getStoreOwner();
            }
        };
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionFragment$special$$inlined$viewModel$default$3
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default$iv, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionFragment$special$$inlined$viewModel$default$4
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
        this.customerId = "";
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment
    public Function3<LayoutInflater, ViewGroup, Boolean, FragmentVisitAdoptionBinding> getBindingInflater() {
        return VisitAdoptionFragment$bindingInflater$1.INSTANCE;
    }

    private final MainViewModel getViewModel() {
        return (MainViewModel) this.viewModel.getValue();
    }

    /* compiled from: VisitAdoptionFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/VisitAdoptionFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/VisitAdoptionFragment;", "customerId", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VisitAdoptionFragment newInstance(String customerId) {
            Intrinsics.checkNotNullParameter(customerId, "customerId");
            VisitAdoptionFragment fragment = new VisitAdoptionFragment();
            Bundle bundle = new Bundle();
            bundle.putString("customerId", customerId);
            fragment.setArguments(bundle);
            return fragment;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        String str;
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments == null || (str = arguments.getString("customerId")) == null) {
            str = "";
        }
        this.customerId = str;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        getViewModel().getAdoption().observe(getViewLifecycleOwner(), new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VisitAdoptionFragment.onViewCreated$lambda$2(VisitAdoptionFragment.this, (ApiResponseCallback) obj);
            }
        });
        getBinding().btnCreateAdoption.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VisitAdoptionFragment.onViewCreated$lambda$3(VisitAdoptionFragment.this, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onViewCreated$lambda$2(VisitAdoptionFragment this$0, ApiResponseCallback it) {
        if (it != null) {
            if (it instanceof ApiResponseCallback.Error) {
                AppToast appToast = AppToast.INSTANCE;
                String message = ((ApiResponseCallback.Error) it).getMessage();
                if (message == null) {
                    message = "Failed";
                }
                appToast.showToast(message);
                String message2 = ((ApiResponseCallback.Error) it).getMessage();
                Log.d("failed", message2 != null ? message2 : "Failed");
                return;
            }
            if (!(it instanceof ApiResponseCallback.Loading)) {
                if (!(it instanceof ApiResponseCallback.Success)) {
                    throw new NoWhenBranchMatchedException();
                }
                AdoptionListResponse data = (AdoptionListResponse) ((ApiResponseCallback.Success) it).getData();
                if (data != null) {
                    Boolean success = data.getSuccess();
                    Intrinsics.checkNotNull(success);
                    GenericAdapter<AdoptionsList> genericAdapter = null;
                    if (success.booleanValue()) {
                        GenericAdapter<AdoptionsList> genericAdapter2 = this$0.adapter;
                        if (genericAdapter2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            genericAdapter = genericAdapter2;
                        }
                        genericAdapter.addList(data.getData());
                        this$0.getBinding().adoptionLinear.setVisibility(8);
                        this$0.getBinding().btnCreateAdoption.setBackgroundResource(R.drawable.create_adoption_bg);
                        Log.i("TAG", "onViewCreated: " + data.getData());
                        return;
                    }
                    GenericAdapter<AdoptionsList> genericAdapter3 = this$0.adapter;
                    if (genericAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    } else {
                        genericAdapter = genericAdapter3;
                    }
                    genericAdapter.clearList();
                    this$0.getBinding().adoptionLinear.setVisibility(0);
                    this$0.getBinding().btnCreateAdoption.setBackgroundResource(R.drawable.create_adoption_empty_btn_shape);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$3(VisitAdoptionFragment this$0, View it) {
        ActivityExtKt.gotoActivityFromFragment(this$0, VisitAdoptionActivity.class, "customerId", this$0.customerId);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getViewModel().adoption(Integer.parseInt(this.customerId));
    }

    private final void setupRecyclerView() {
        this.adapter = new GenericAdapter<>(R.layout.item_adoption_list, new GenericAdapter.OnItemClickListener<AdoptionsList>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionFragment$setupRecyclerView$1
            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onCall(int visitId) {
                GenericAdapter.OnItemClickListener.DefaultImpls.onCall(this, visitId);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onItemClickTwo(AdoptionsList item) {
                GenericAdapter.OnItemClickListener.DefaultImpls.onItemClickTwo(this, item);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onSelectionChanged(List<? extends AdoptionsList> list) {
                GenericAdapter.OnItemClickListener.DefaultImpls.onSelectionChanged(this, list);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onItemClick(AdoptionsList item) {
                Intrinsics.checkNotNullParameter(item, "item");
                AdoptionDetailsBottomSheet bottomSheet = new AdoptionDetailsBottomSheet();
                Bundle bundle = new Bundle();
                bundle.putString("adoptionId", String.valueOf(item.getId()));
                bottomSheet.setArguments(bundle);
                bottomSheet.show(VisitAdoptionFragment.this.getParentFragmentManager(), "visitAdoptionBottomSheet");
            }
        });
        RecyclerView recyclerView = getBinding().adoptionListRv;
        GenericAdapter<AdoptionsList> genericAdapter = this.adapter;
        if (genericAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            genericAdapter = null;
        }
        recyclerView.setAdapter(genericAdapter);
        getBinding().adoptionListRv.setLayoutManager(new LinearLayoutManager(requireContext(), 1, false));
    }
}
