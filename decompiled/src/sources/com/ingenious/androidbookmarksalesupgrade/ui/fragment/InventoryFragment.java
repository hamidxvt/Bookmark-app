package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.savedstate.SavedStateRegistryOwner;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter;
import com.ingenious.androidbookmarksalesupgrade.adapter.SegmentsAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentInventoryBinding;
import com.ingenious.androidbookmarksalesupgrade.model.SegmentModel;
import com.ingenious.androidbookmarksalesupgrade.model.response.LowStockInventoryResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import com.ingenious.androidbookmarksalesupgrade.model.response.StockSummaryResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.HelpScreenAct;
import com.ingenious.androidbookmarksalesupgrade.viewModel.InventoryViewModel;
import java.util.ArrayList;
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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONArray;
import org.json.JSONObject;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: InventoryFragment.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u001fH\u0002J\b\u0010%\u001a\u00020\u001fH\u0002J\b\u0010&\u001a\u00020\u001fH\u0002J\b\u0010'\u001a\u00020\u001fH\u0002J\u0010\u0010(\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020\u0012H\u0002J\u0010\u0010*\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020\u0012H\u0002J\n\u0010,\u001a\u0004\u0018\u00010-H\u0002J\u0016\u0010.\u001a\u00020\u001f2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020100H\u0002J\b\u00102\u001a\u00020\u001fH\u0002J\b\u00103\u001a\u00020\u001fH\u0002J\u001e\u00104\u001a\b\u0012\u0004\u0012\u00020\r002\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u0012H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015R\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015R\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015R.\u0010\u0018\u001a\u001c\u0012\u0004\u0012\u00020\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u00068"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/InventoryFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/BaseFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentInventoryBinding;", "<init>", "()V", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/InventoryViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/InventoryViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "adapterLowStock", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "adapterStockSummary", "segmentsAdapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/SegmentsAdapter;", "totalBooks", "", "selectedSegment", "", "Ljava/lang/Integer;", "selectedGrade", "selectedSubject", "bindingInflater", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "getBindingInflater", "()Lkotlin/jvm/functions/Function3;", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setupAdapter", "setupStockAdapter", "setupSegmentAdapter", "fetchBooksBySegment", "showLoading", "isLoading", "showNoData", "show", "getToken", "", "updateSegmentList", "list", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/SegmentModel;", "exportDialog", "fetchInventorySummaryWithFilter", "parseProductsArray", "jsonArray", "Lorg/json/JSONArray;", "isTodayRecommended", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class InventoryFragment extends BaseFragment<FragmentInventoryBinding> {
    private GenericAdapter<Products> adapterLowStock;
    private GenericAdapter<Products> adapterStockSummary;
    private SegmentsAdapter segmentsAdapter;
    private Integer selectedGrade;
    private Integer selectedSegment;
    private Integer selectedSubject;
    private boolean totalBooks;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public InventoryFragment() {
        final InventoryFragment $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$special$$inlined$viewModel$default$1
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
        final Function0 ownerProducer$iv$iv = new Function0<ViewModelStoreOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$special$$inlined$viewModel$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return ((ViewModelOwner) Function0.this.invoke()).getStoreOwner();
            }
        };
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$special$$inlined$viewModel$default$3
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
                ViewModelParameter viewModelParameters$iv = new ViewModelParameter(Reflection.getOrCreateKotlinClass(InventoryViewModel.class), qualifier$iv2, null, parameters$iv2, ownerValue$iv.getStoreOwner(), ownerValue$iv.getStateRegistry());
                return ViewModelResolverKt.pickFactory(scope$iv2, viewModelParameters$iv);
            }
        };
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default$iv, Reflection.getOrCreateKotlinClass(InventoryViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$special$$inlined$viewModel$default$4
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

    /* JADX INFO: Access modifiers changed from: private */
    public final InventoryViewModel getViewModel() {
        return (InventoryViewModel) this.viewModel.getValue();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment
    public Function3<LayoutInflater, ViewGroup, Boolean, FragmentInventoryBinding> getBindingInflater() {
        return InventoryFragment$bindingInflater$1.INSTANCE;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        getBinding().schoolTypeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                InventoryFragment.onViewCreated$lambda$0(InventoryFragment.this, view2);
            }
        });
        getBinding().shopTypeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                InventoryFragment.onViewCreated$lambda$1(InventoryFragment.this, view2);
            }
        });
        getBinding().allBookMove.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                InventoryFragment.onViewCreated$lambda$2(InventoryFragment.this, view2);
            }
        });
        setupAdapter();
        setupStockAdapter();
        setupSegmentAdapter();
        getViewModel().lowStock();
        getViewModel().stockSummary();
        fetchBooksBySegment();
        getViewModel().getLowStockResponse().observe(getViewLifecycleOwner(), new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                InventoryFragment.onViewCreated$lambda$5(InventoryFragment.this, (ApiResponseCallback) obj);
            }
        });
        getViewModel().getStockSummaryResponse().observe(getViewLifecycleOwner(), new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                InventoryFragment.onViewCreated$lambda$8(InventoryFragment.this, (ApiResponseCallback) obj);
            }
        });
        getBinding().setListener(new InventoryFragment$onViewCreated$6(this));
        getBinding().inventorySearchEt.addTextChangedListener(new TextWatcher() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$onViewCreated$7
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                GenericAdapter genericAdapter;
                genericAdapter = InventoryFragment.this.adapterStockSummary;
                if (genericAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapterStockSummary");
                    genericAdapter = null;
                }
                genericAdapter.filterProducts(String.valueOf(s));
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        getBinding().cancelTv.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                InventoryFragment.onViewCreated$lambda$9(InventoryFragment.this, view2);
            }
        });
        getBinding().performanceMenu.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                InventoryFragment.this.exportDialog();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(InventoryFragment this$0, View it) {
        this$0.totalBooks = false;
        this$0.getBinding().customerTypeSchool.setTextColor(this$0.getResources().getColor(R.color.app_color));
        this$0.getBinding().customerTypeBookshop.setTextColor(this$0.getResources().getColor(R.color.gray));
        DrawableCompat.setTint(this$0.getBinding().customerTypeSchool.getCompoundDrawables()[0], ContextCompat.getColor(this$0.requireContext(), R.color.app_color));
        DrawableCompat.setTint(this$0.getBinding().customerTypeBookshop.getCompoundDrawables()[0], ContextCompat.getColor(this$0.requireContext(), R.color.gray));
        this$0.getBinding().schoolTypeLayout.setBackgroundResource(R.drawable.selected_button_background);
        this$0.getBinding().shopTypeLayout.setBackgroundResource(R.drawable.unselected_button_background);
        this$0.getBinding().allBookMove.setText("Recommended for Today");
        this$0.getViewModel().stockSummary();
        this$0.setupStockAdapter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(InventoryFragment this$0, View it) {
        this$0.totalBooks = true;
        this$0.getBinding().customerTypeBookshop.setTextColor(this$0.getResources().getColor(R.color.app_color));
        this$0.getBinding().customerTypeSchool.setTextColor(this$0.getResources().getColor(R.color.gray));
        DrawableCompat.setTint(this$0.getBinding().customerTypeSchool.getCompoundDrawables()[0], ContextCompat.getColor(this$0.requireContext(), R.color.gray));
        DrawableCompat.setTint(this$0.getBinding().customerTypeBookshop.getCompoundDrawables()[0], ContextCompat.getColor(this$0.requireContext(), R.color.app_color));
        this$0.getBinding().shopTypeLayout.setBackgroundResource(R.drawable.selected_button_background);
        this$0.getBinding().schoolTypeLayout.setBackgroundResource(R.drawable.unselected_button_background);
        this$0.getBinding().allBookMove.setText("All Book");
        this$0.getViewModel().stockSummary();
        this$0.setupStockAdapter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(InventoryFragment this$0, View it) {
        if (this$0.totalBooks) {
            Intent intent = new Intent(this$0.requireActivity(), (Class<?>) AllProductsActivity.class);
            this$0.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onViewCreated$lambda$5(InventoryFragment this$0, ApiResponseCallback it) {
        if (it != null && !(it instanceof ApiResponseCallback.Error) && !(it instanceof ApiResponseCallback.Loading)) {
            if (!(it instanceof ApiResponseCallback.Success)) {
                throw new NoWhenBranchMatchedException();
            }
            LowStockInventoryResponse data = (LowStockInventoryResponse) ((ApiResponseCallback.Success) it).getData();
            if (data != null && Intrinsics.areEqual((Object) data.getSuccess(), (Object) true)) {
                GenericAdapter<Products> genericAdapter = this$0.adapterLowStock;
                if (genericAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapterLowStock");
                    genericAdapter = null;
                }
                ArrayList<Products> lowStockProducts = data.getLowStockProducts();
                genericAdapter.addListForInventory(lowStockProducts != null ? lowStockProducts : CollectionsKt.emptyList());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onViewCreated$lambda$8(InventoryFragment this$0, ApiResponseCallback it) {
        if (it != null && !(it instanceof ApiResponseCallback.Error) && !(it instanceof ApiResponseCallback.Loading)) {
            if (!(it instanceof ApiResponseCallback.Success)) {
                throw new NoWhenBranchMatchedException();
            }
            StockSummaryResponse data = (StockSummaryResponse) ((ApiResponseCallback.Success) it).getData();
            if (data != null && Intrinsics.areEqual((Object) data.getSuccess(), (Object) true)) {
                this$0.getBinding().setItem(data);
                GenericAdapter<Products> genericAdapter = null;
                if (this$0.totalBooks) {
                    GenericAdapter<Products> genericAdapter2 = this$0.adapterStockSummary;
                    if (genericAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapterStockSummary");
                    } else {
                        genericAdapter = genericAdapter2;
                    }
                    ArrayList<Products> allBookerProducts = data.getAllBookerProducts();
                    genericAdapter.addListForInventory(allBookerProducts != null ? allBookerProducts : CollectionsKt.emptyList());
                } else {
                    GenericAdapter<Products> genericAdapter3 = this$0.adapterStockSummary;
                    if (genericAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapterStockSummary");
                    } else {
                        genericAdapter = genericAdapter3;
                    }
                    ArrayList<Products> todayRecommendedBooks = data.getTodayRecommendedBooks();
                    genericAdapter.addListForInventory(todayRecommendedBooks != null ? todayRecommendedBooks : CollectionsKt.emptyList());
                }
                Log.i("TAG", "Book: " + data.getAllBookerProducts());
                Log.i("TAG", "Book--: " + data.getTodayRecommendedBooks());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$9(InventoryFragment this$0, View it) {
        Editable text = this$0.getBinding().inventorySearchEt.getText();
        if (text != null) {
            text.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupAdapter() {
        GenericAdapter<Products> genericAdapter = null;
        this.adapterLowStock = new GenericAdapter<>(R.layout.item_stock_list, null, 2, null);
        RecyclerView recyclerView = getBinding().lowStockListRv;
        GenericAdapter<Products> genericAdapter2 = this.adapterLowStock;
        if (genericAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterLowStock");
        } else {
            genericAdapter = genericAdapter2;
        }
        recyclerView.setAdapter(genericAdapter);
        getBinding().lowStockListRv.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupStockAdapter() {
        GenericAdapter<Products> genericAdapter = null;
        this.adapterStockSummary = new GenericAdapter<>(R.layout.item_inventory_products, null, 2, null);
        RecyclerView recyclerView = getBinding().stockSummaryRv;
        GenericAdapter<Products> genericAdapter2 = this.adapterStockSummary;
        if (genericAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterStockSummary");
        } else {
            genericAdapter = genericAdapter2;
        }
        recyclerView.setAdapter(genericAdapter);
        getBinding().stockSummaryRv.setLayoutManager(new GridLayoutManager(requireContext(), 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupSegmentAdapter() {
        this.segmentsAdapter = new SegmentsAdapter(CollectionsKt.emptyList(), new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit unit;
                unit = InventoryFragment.setupSegmentAdapter$lambda$11((SegmentModel) obj);
                return unit;
            }
        });
        getBinding().segmentsRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        RecyclerView recyclerView = getBinding().segmentsRv;
        SegmentsAdapter segmentsAdapter = this.segmentsAdapter;
        if (segmentsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("segmentsAdapter");
            segmentsAdapter = null;
        }
        recyclerView.setAdapter(segmentsAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupSegmentAdapter$lambda$11(SegmentModel item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fetchBooksBySegment() {
        showLoading(true);
        OkHttpClient client = new OkHttpClient();
        Request.Builder addHeader = new Request.Builder().url("https://staging.bookmark.services/api/Inventory/getBooksBySegment").addHeader(HttpHeaders.ACCEPT, "application/json");
        String token = getToken();
        if (token == null) {
            token = "";
        }
        Request request = addHeader.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
        client.newCall(request).enqueue(new InventoryFragment$fetchBooksBySegment$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoading(boolean isLoading) {
        getBinding().progressBar.setVisibility(isLoading ? 0 : 8);
        getBinding().segmentsRv.setVisibility(isLoading ? 8 : 0);
        getBinding().noDataText.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showNoData(boolean show) {
        getBinding().noDataText.setVisibility(show ? 0 : 8);
        getBinding().segmentsRv.setVisibility(show ? 8 : 0);
    }

    private final String getToken() {
        SharedPreferences sharedPref = requireActivity().getSharedPreferences("BookmarkApp", 0);
        return sharedPref.getString("AUTH_TOKEN", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSegmentList(List<SegmentModel> list) {
        SegmentsAdapter segmentsAdapter = this.segmentsAdapter;
        if (segmentsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("segmentsAdapter");
            segmentsAdapter = null;
        }
        segmentsAdapter.updateList(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void exportDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_customer_inventory_more, (ViewGroup) null);
        final AlertDialog dialog = new AlertDialog.Builder(requireContext()).setView(dialogView).create();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        ImageView crossBtn = (ImageView) dialogView.findViewById(R.id.ivClose);
        LinearLayout dateRange = (LinearLayout) dialogView.findViewById(R.id.btnDateRange);
        dateRange.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InventoryFragment.exportDialog$lambda$12(InventoryFragment.this, view);
            }
        });
        crossBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.InventoryFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void exportDialog$lambda$12(InventoryFragment this$0, View it) {
        Intent intent = new Intent(this$0.requireContext(), (Class<?>) HelpScreenAct.class);
        this$0.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fetchInventorySummaryWithFilter() {
        String finalUrl;
        showLoading(true);
        List params = new ArrayList();
        Integer num = this.selectedSegment;
        if (num != null) {
            int it = num.intValue();
            params.add("segment=" + it);
        }
        Integer num2 = this.selectedGrade;
        if (num2 != null) {
            int it2 = num2.intValue();
            params.add("grade=" + it2);
        }
        Integer num3 = this.selectedSubject;
        if (num3 != null) {
            int it3 = num3.intValue();
            params.add("subject=" + it3);
        }
        Log.i("TAG", "fetchInventorySummaryWithFilter: " + this.selectedSegment + "---" + this.selectedGrade + "==" + this.selectedSubject);
        if (params.isEmpty()) {
            finalUrl = "https://staging.bookmark.services/api/Inventory/Summary";
        } else {
            finalUrl = "https://staging.bookmark.services/api/Inventory/Summary?" + CollectionsKt.joinToString$default(params, "&", null, null, 0, null, null, 62, null);
        }
        Log.d("FILTER_API", finalUrl);
        OkHttpClient client = new OkHttpClient();
        Request.Builder addHeader = new Request.Builder().url(finalUrl).addHeader(HttpHeaders.ACCEPT, "application/json");
        String token = getToken();
        if (token == null) {
            token = "";
        }
        Request request = addHeader.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
        client.newCall(request).enqueue(new InventoryFragment$fetchInventorySummaryWithFilter$4(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Products> parseProductsArray(JSONArray jsonArray, boolean isTodayRecommended) {
        List list = new ArrayList();
        int length = jsonArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String optString = obj.optString("product_name");
            String optString2 = isTodayRecommended ? obj.optString(FirebaseAnalytics.Param.QUANTITY) : obj.optString("quantity_assigned");
            list.add(new Products(0, null, null, null, null, optString, null, null, null, obj.optString("image"), obj.optString(FirebaseAnalytics.Param.PRICE), null, optString2, null, null, null, null, null, null, null, "", null, "", null, null, null, null, 128969182, null));
        }
        return list;
    }
}
