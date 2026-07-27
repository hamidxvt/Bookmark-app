package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.savedstate.SavedStateRegistryOwner;
import com.ingenious.androidbookmarksalesupgrade.adapter.GradesSubjectsAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitAdoptionGradesBinding;
import com.ingenious.androidbookmarksalesupgrade.listener.OnResultListener;
import com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener;
import com.ingenious.androidbookmarksalesupgrade.model.BookModel;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooksData;
import com.ingenious.androidbookmarksalesupgrade.model.response.BooksBySegmentData;
import com.ingenious.androidbookmarksalesupgrade.model.response.GradesListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.GradesSubjectsData;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.VisitAdoptionActivity;
import com.ingenious.androidbookmarksalesupgrade.utils.LoggingInterceptor;
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
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: VisitAdoptionGradesFragment.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020\u001dH\u0002R.\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/VisitAdoptionGradesFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/BaseFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentVisitAdoptionGradesBinding;", "<init>", "()V", "bindingInflater", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "", "getBindingInflater", "()Lkotlin/jvm/functions/Function3;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/MainViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "sharedViewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitAdoptionViewModel;", "getSharedViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitAdoptionViewModel;", "sharedViewModel$delegate", "adapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/GradesSubjectsAdapter;", "selectedProductList", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GradesSubjectsData;", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setupRecyclerView", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class VisitAdoptionGradesFragment extends BaseFragment<FragmentVisitAdoptionGradesBinding> {
    private GradesSubjectsAdapter adapter;
    private final List<GradesSubjectsData> selectedProductList;

    /* renamed from: sharedViewModel$delegate, reason: from kotlin metadata */
    private final Lazy sharedViewModel;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public VisitAdoptionGradesFragment() {
        final VisitAdoptionGradesFragment $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionGradesFragment$special$$inlined$viewModel$default$1
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
        final Function0 ownerProducer$iv$iv = new Function0<ViewModelStoreOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionGradesFragment$special$$inlined$viewModel$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return ((ViewModelOwner) Function0.this.invoke()).getStoreOwner();
            }
        };
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionGradesFragment$special$$inlined$viewModel$default$3
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default$iv, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionGradesFragment$special$$inlined$viewModel$default$4
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
        final VisitAdoptionGradesFragment $this$activityViewModels_u24default$iv = this;
        final Function0 extrasProducer$iv = null;
        this.sharedViewModel = FragmentViewModelLazyKt.createViewModelLazy($this$activityViewModels_u24default$iv, Reflection.getOrCreateKotlinClass(VisitAdoptionViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionGradesFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return Fragment.this.requireActivity().getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionGradesFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionGradesFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return Fragment.this.requireActivity().getDefaultViewModelProviderFactory();
            }
        });
        this.selectedProductList = new ArrayList();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment
    public Function3<LayoutInflater, ViewGroup, Boolean, FragmentVisitAdoptionGradesBinding> getBindingInflater() {
        return VisitAdoptionGradesFragment$bindingInflater$1.INSTANCE;
    }

    private final MainViewModel getViewModel() {
        return (MainViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VisitAdoptionViewModel getSharedViewModel() {
        return (VisitAdoptionViewModel) this.sharedViewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        getSharedViewModel().getSelectedSegmentsData().observe(getViewLifecycleOwner(), new VisitAdoptionGradesFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionGradesFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onViewCreated$lambda$1;
                onViewCreated$lambda$1 = VisitAdoptionGradesFragment.onViewCreated$lambda$1(VisitAdoptionGradesFragment.this, (List) obj);
                return onViewCreated$lambda$1;
            }
        }));
        setupRecyclerView();
        getViewModel().getGradesList().observe(getViewLifecycleOwner(), new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionGradesFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VisitAdoptionGradesFragment.onViewCreated$lambda$4(VisitAdoptionGradesFragment.this, (ApiResponseCallback) obj);
            }
        });
        getBinding().btnContinue.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionGradesFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VisitAdoptionGradesFragment.onViewCreated$lambda$5(VisitAdoptionGradesFragment.this, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onViewCreated$lambda$1(VisitAdoptionGradesFragment this$0, List selectedIds) {
        if (selectedIds != null) {
            String idsAsString = CollectionsKt.joinToString$default(selectedIds, ",", null, null, 0, null, null, 62, null);
            this$0.getViewModel().gradesList(idsAsString);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onViewCreated$lambda$4(VisitAdoptionGradesFragment this$0, ApiResponseCallback it) {
        if (it != null && !(it instanceof ApiResponseCallback.Error) && !(it instanceof ApiResponseCallback.Loading)) {
            if (!(it instanceof ApiResponseCallback.Success)) {
                throw new NoWhenBranchMatchedException();
            }
            GradesListResponse data = (GradesListResponse) ((ApiResponseCallback.Success) it).getData();
            if (data != null) {
                Boolean success = data.getSuccess();
                Intrinsics.checkNotNull(success);
                if (success.booleanValue()) {
                    GradesSubjectsAdapter gradesSubjectsAdapter = this$0.adapter;
                    if (gradesSubjectsAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        gradesSubjectsAdapter = null;
                    }
                    gradesSubjectsAdapter.setItems(data.getData());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$5(VisitAdoptionGradesFragment this$0, View it) {
        if (this$0.selectedProductList.isEmpty()) {
            Toast.makeText(this$0.requireContext(), "Please select at least one grade or subject", 0).show();
            return;
        }
        FragmentActivity activity = this$0.getActivity();
        VisitAdoptionActivity visitAdoptionActivity = activity instanceof VisitAdoptionActivity ? (VisitAdoptionActivity) activity : null;
        if (visitAdoptionActivity != null) {
            visitAdoptionActivity.next();
        }
    }

    private final void setupRecyclerView() {
        this.adapter = new GradesSubjectsAdapter(new RecyclerViewListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionGradesFragment$setupRecyclerView$1
            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void addQuantityToCart(String quantityNo, String productId, OnResultListener listener) {
                RecyclerViewListener.DefaultImpls.addQuantityToCart(this, quantityNo, productId, listener);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onItemClick(Products products) {
                RecyclerViewListener.DefaultImpls.onItemClick(this, products);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onSegmentTotalUpdated(BookModel products) {
                RecyclerViewListener.DefaultImpls.onSegmentTotalUpdated(this, products);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onSelectBooksList(AdoptionBooksData books) {
                RecyclerViewListener.DefaultImpls.onSelectBooksList(this, books);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onSelectProduct(Products products) {
                RecyclerViewListener.DefaultImpls.onSelectProduct(this, products);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onSelectSegment(BooksBySegmentData segments) {
                RecyclerViewListener.DefaultImpls.onSelectSegment(this, segments);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onTapAddPrice(Products products) {
                RecyclerViewListener.DefaultImpls.onTapAddPrice(this, products);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onTapAddToCart(Products products) {
                RecyclerViewListener.DefaultImpls.onTapAddToCart(this, products);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onTapDelete(Products products) {
                RecyclerViewListener.DefaultImpls.onTapDelete(this, products);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onTapSelectQuantity(Products products) {
                RecyclerViewListener.DefaultImpls.onTapSelectQuantity(this, products);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onTapSelectReturnProduct(Products products) {
                RecyclerViewListener.DefaultImpls.onTapSelectReturnProduct(this, products);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onTotalUpdated(Products products) {
                RecyclerViewListener.DefaultImpls.onTotalUpdated(this, products);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onSelectGradesSubjects(GradesSubjectsData gradesSubjects) {
                List list;
                List list2;
                Iterable it;
                VisitAdoptionViewModel sharedViewModel;
                VisitAdoptionViewModel sharedViewModel2;
                String name;
                List list3;
                List list4;
                List list5;
                Intrinsics.checkNotNullParameter(gradesSubjects, "gradesSubjects");
                Boolean isSelected = gradesSubjects.isSelected();
                Intrinsics.checkNotNull(isSelected);
                if (isSelected.booleanValue()) {
                    list3 = VisitAdoptionGradesFragment.this.selectedProductList;
                    if (!list3.contains(gradesSubjects)) {
                        list4 = VisitAdoptionGradesFragment.this.selectedProductList;
                        list4.add(gradesSubjects);
                        list5 = VisitAdoptionGradesFragment.this.selectedProductList;
                        Log.d(LoggingInterceptor.TAG, "Selected products: " + list5);
                    }
                } else {
                    list = VisitAdoptionGradesFragment.this.selectedProductList;
                    list.remove(gradesSubjects);
                    list2 = VisitAdoptionGradesFragment.this.selectedProductList;
                    Log.d(LoggingInterceptor.TAG, "Selected products: " + list2);
                }
                it = VisitAdoptionGradesFragment.this.selectedProductList;
                VisitAdoptionGradesFragment visitAdoptionGradesFragment = VisitAdoptionGradesFragment.this;
                Iterable $this$map$iv = it;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    GradesSubjectsData segment = (GradesSubjectsData) item$iv$iv;
                    Integer id = segment.getId();
                    Intrinsics.checkNotNull(id);
                    destination$iv$iv.add(Integer.valueOf(id.intValue()));
                }
                List idList = (List) destination$iv$iv;
                Iterable $this$map$iv2 = it;
                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                for (Object item$iv$iv2 : $this$map$iv2) {
                    GradesSubjectsData grade = (GradesSubjectsData) item$iv$iv2;
                    if (grade.getTitle() != null) {
                        name = grade.getTitle();
                    } else {
                        name = grade.getName();
                        if (name == null) {
                            name = "";
                        }
                    }
                    Intrinsics.checkNotNull(name, "null cannot be cast to non-null type kotlin.String");
                    destination$iv$iv2.add(name);
                }
                List nameList = (List) destination$iv$iv2;
                sharedViewModel = visitAdoptionGradesFragment.getSharedViewModel();
                sharedViewModel.getSelectedGradesData().setValue(idList);
                sharedViewModel2 = visitAdoptionGradesFragment.getSharedViewModel();
                sharedViewModel2.getSelectedGradeNames().setValue(nameList);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onTapUpdateQuantity(Products products) {
                Intrinsics.checkNotNullParameter(products, "products");
            }
        });
        RecyclerView recyclerView = getBinding().segmentsRv;
        GradesSubjectsAdapter gradesSubjectsAdapter = this.adapter;
        if (gradesSubjectsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            gradesSubjectsAdapter = null;
        }
        recyclerView.setAdapter(gradesSubjectsAdapter);
        getBinding().segmentsRv.setLayoutManager(new LinearLayoutManager(requireContext(), 1, false));
    }
}
