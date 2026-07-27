package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.savedstate.SavedStateRegistryOwner;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.AdoptionQuantityAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitAdoptionQuantityBinding;
import com.ingenious.androidbookmarksalesupgrade.model.request.AddAdoptionRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.AdoptionProductRequest;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooksData;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.utils.FullScreenDialogFragment;
import com.ingenious.androidbookmarksalesupgrade.viewModel.MainViewModel;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitAdoptionViewModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: VisitAdoptionQuantityFragment.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010#\u001a\u00020\u001eH\u0002J\b\u0010$\u001a\u00020\u001eH\u0002J&\u0010'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020)2\u0016\b\u0002\u0010*\u001a\u0010\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u001e\u0018\u00010+J\u0006\u0010,\u001a\u00020\u001eR\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR.\u0010\t\u001a\u001c\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/VisitAdoptionQuantityFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/BaseFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentVisitAdoptionQuantityBinding;", "customerId", "", "<init>", "(Ljava/lang/String;)V", "getCustomerId", "()Ljava/lang/String;", "bindingInflater", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "", "getBindingInflater", "()Lkotlin/jvm/functions/Function3;", "sharedViewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitAdoptionViewModel;", "getSharedViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitAdoptionViewModel;", "sharedViewModel$delegate", "Lkotlin/Lazy;", "mainViewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/MainViewModel;", "getMainViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/MainViewModel;", "mainViewModel$delegate", "adapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/AdoptionQuantityAdapter;", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "createAdoption", "setupRecyclerView", "loadingDialogFragment", "Lcom/ingenious/androidbookmarksalesupgrade/utils/FullScreenDialogFragment;", "showLoadingAlert", "layout", "", "setupViews", "Lkotlin/Function1;", "hideLoadingAlert", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class VisitAdoptionQuantityFragment extends BaseFragment<FragmentVisitAdoptionQuantityBinding> {
    private AdoptionQuantityAdapter adapter;
    private final String customerId;
    private FullScreenDialogFragment loadingDialogFragment;

    /* renamed from: mainViewModel$delegate, reason: from kotlin metadata */
    private final Lazy mainViewModel;

    /* renamed from: sharedViewModel$delegate, reason: from kotlin metadata */
    private final Lazy sharedViewModel;

    public VisitAdoptionQuantityFragment(String customerId) {
        this.customerId = customerId;
        final VisitAdoptionQuantityFragment $this$activityViewModels_u24default$iv = this;
        final Function0 extrasProducer$iv = null;
        this.sharedViewModel = FragmentViewModelLazyKt.createViewModelLazy($this$activityViewModels_u24default$iv, Reflection.getOrCreateKotlinClass(VisitAdoptionViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionQuantityFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return Fragment.this.requireActivity().getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionQuantityFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function0 = Function0.this;
                return (function0 == null || (creationExtras = (CreationExtras) function0.invoke()) == null) ? $this$activityViewModels_u24default$iv.requireActivity().getDefaultViewModelCreationExtras() : creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionQuantityFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return Fragment.this.requireActivity().getDefaultViewModelProviderFactory();
            }
        });
        final VisitAdoptionQuantityFragment $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionQuantityFragment$special$$inlined$viewModel$default$1
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
        final Function0 ownerProducer$iv$iv = new Function0<ViewModelStoreOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionQuantityFragment$special$$inlined$viewModel$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return ((ViewModelOwner) Function0.this.invoke()).getStoreOwner();
            }
        };
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionQuantityFragment$special$$inlined$viewModel$default$3
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
        this.mainViewModel = FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default$iv, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionQuantityFragment$special$$inlined$viewModel$default$4
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

    public final String getCustomerId() {
        return this.customerId;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment
    public Function3<LayoutInflater, ViewGroup, Boolean, FragmentVisitAdoptionQuantityBinding> getBindingInflater() {
        return VisitAdoptionQuantityFragment$bindingInflater$1.INSTANCE;
    }

    private final VisitAdoptionViewModel getSharedViewModel() {
        return (VisitAdoptionViewModel) this.sharedViewModel.getValue();
    }

    private final MainViewModel getMainViewModel() {
        return (MainViewModel) this.mainViewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        getSharedViewModel().getSelectedBooksList().observe(getViewLifecycleOwner(), new VisitAdoptionQuantityFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionQuantityFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onViewCreated$lambda$1;
                onViewCreated$lambda$1 = VisitAdoptionQuantityFragment.onViewCreated$lambda$1(VisitAdoptionQuantityFragment.this, (List) obj);
                return onViewCreated$lambda$1;
            }
        }));
        getBinding().btnCreateAdoption.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionQuantityFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VisitAdoptionQuantityFragment.onViewCreated$lambda$2(VisitAdoptionQuantityFragment.this, view2);
            }
        });
        getMainViewModel().getAddAdoption().observe(getViewLifecycleOwner(), new VisitAdoptionQuantityFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionQuantityFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onViewCreated$lambda$3;
                onViewCreated$lambda$3 = VisitAdoptionQuantityFragment.onViewCreated$lambda$3(VisitAdoptionQuantityFragment.this, (ApiResponseCallback) obj);
                return onViewCreated$lambda$3;
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onViewCreated$lambda$1(VisitAdoptionQuantityFragment this$0, List bookList) {
        if (bookList != null) {
            AdoptionQuantityAdapter adoptionQuantityAdapter = this$0.adapter;
            if (adoptionQuantityAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                adoptionQuantityAdapter = null;
            }
            adoptionQuantityAdapter.updateItems(bookList);
            this$0.getBinding().totalResults.setText(String.valueOf(bookList.size()));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(VisitAdoptionQuantityFragment this$0, View it) {
        showLoadingAlert$default(this$0, R.layout.loading_adoption, null, 2, null);
        this$0.createAdoption();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onViewCreated$lambda$3(VisitAdoptionQuantityFragment this$0, ApiResponseCallback response) {
        this$0.getBinding().progressBar.setVisibility(8);
        this$0.getBinding().btnCreateAdoption.setVisibility(0);
        if (response instanceof ApiResponseCallback.Success) {
            this$0.hideLoadingAlert();
            Toast.makeText(this$0.requireContext(), "Adoption created successfully", 0).show();
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), null, null, new VisitAdoptionQuantityFragment$onViewCreated$3$1(this$0, null), 3, null);
        } else if (response instanceof ApiResponseCallback.Error) {
            this$0.hideLoadingAlert();
            Toast.makeText(this$0.requireContext(), "Failed to create adoption: " + ((ApiResponseCallback.Error) response).getMessage(), 0).show();
        } else {
            if (!(response instanceof ApiResponseCallback.Loading)) {
                throw new NoWhenBranchMatchedException();
            }
            showLoadingAlert$default(this$0, R.layout.loading_adoption, null, 2, null);
            this$0.getBinding().btnCreateAdoption.setVisibility(8);
            this$0.getBinding().progressBar.setVisibility(0);
        }
        return Unit.INSTANCE;
    }

    private final void createAdoption() {
        Iterable iterable = (List) getSharedViewModel().getSelectedBooksList().getValue();
        List list = null;
        if (iterable != null) {
            Iterable $this$map$iv = iterable;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                AdoptionBooksData book = (AdoptionBooksData) item$iv$iv;
                List<Integer> value = getSharedViewModel().getSelectedSegmentsData().getValue();
                Integer num = value != null ? value.get(0) : null;
                List<Integer> value2 = getSharedViewModel().getSelectedGradesData().getValue();
                Integer num2 = value2 != null ? value2.get(0) : null;
                List<Integer> value3 = getSharedViewModel().getSelectedSubjectsData().getValue();
                Integer num3 = value3 != null ? value3.get(0) : null;
                Integer id = book.getId();
                Integer quantity = book.getQuantity();
                destination$iv$iv.add(new AdoptionProductRequest(num, num2, num3, id, quantity != null ? quantity.intValue() : 1));
            }
            list = (List) destination$iv$iv;
        }
        List productRequests = list;
        String value4 = getSharedViewModel().getName().getValue();
        if (value4 == null) {
            value4 = "No Name";
        }
        String adoptionName = value4;
        String notes = getSharedViewModel().getNotes().getValue();
        List list2 = productRequests;
        if (list2 == null || list2.isEmpty()) {
            Toast.makeText(requireContext(), "Please select at least one book", 0).show();
        } else {
            AddAdoptionRequest request = new AddAdoptionRequest(adoptionName, null, this.customerId, notes, "pending", productRequests);
            getMainViewModel().addAdoption(request);
        }
    }

    private final void setupRecyclerView() {
        this.adapter = new AdoptionQuantityAdapter(CollectionsKt.emptyList());
        RecyclerView recyclerView = getBinding().segmentsRv;
        AdoptionQuantityAdapter adoptionQuantityAdapter = this.adapter;
        if (adoptionQuantityAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            adoptionQuantityAdapter = null;
        }
        recyclerView.setAdapter(adoptionQuantityAdapter);
        getBinding().segmentsRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        DividerItemDecoration divider = new DividerItemDecoration(requireContext(), 1);
        getBinding().segmentsRv.addItemDecoration(divider);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void showLoadingAlert$default(VisitAdoptionQuantityFragment visitAdoptionQuantityFragment, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            function1 = null;
        }
        visitAdoptionQuantityFragment.showLoadingAlert(i, function1);
    }

    public final void showLoadingAlert(int layout, Function1<? super View, Unit> setupViews) {
        this.loadingDialogFragment = new FullScreenDialogFragment(layout, setupViews);
        FullScreenDialogFragment fullScreenDialogFragment = this.loadingDialogFragment;
        if (fullScreenDialogFragment != null) {
            fullScreenDialogFragment.show(getParentFragmentManager(), "loading_dialog");
        }
    }

    public final void hideLoadingAlert() {
        FullScreenDialogFragment fullScreenDialogFragment = this.loadingDialogFragment;
        if (fullScreenDialogFragment != null) {
            fullScreenDialogFragment.dismiss();
        }
        this.loadingDialogFragment = null;
    }
}
