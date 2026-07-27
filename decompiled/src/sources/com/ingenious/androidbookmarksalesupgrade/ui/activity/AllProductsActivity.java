package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import androidx.activity.ComponentActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.common.net.HttpHeaders;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.OrderAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityAllProductsBinding;
import com.ingenious.androidbookmarksalesupgrade.databinding.LayoutLoadingBinding;
import com.ingenious.androidbookmarksalesupgrade.listener.OnResultListener;
import com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener;
import com.ingenious.androidbookmarksalesupgrade.model.BookModel;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooksData;
import com.ingenious.androidbookmarksalesupgrade.model.response.BooksBySegmentData;
import com.ingenious.androidbookmarksalesupgrade.model.response.GradesSubjectsData;
import com.ingenious.androidbookmarksalesupgrade.model.response.ProductListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.network.domain.ErrorHandler;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AllProductsActivity.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0014J\b\u0010*\u001a\u00020'H\u0002J\u0006\u0010+\u001a\u00020'J\b\u0010,\u001a\u00020'H\u0002J\u0016\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00120\u001a2\u0006\u0010.\u001a\u00020/H\u0002J\n\u00100\u001a\u0004\u0018\u00010\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00120\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\"\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010#R\u0012\u0010$\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010#R\u0012\u0010%\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010#¨\u00061"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/AllProductsActivity;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/BaseActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityAllProductsBinding;", "pageNo", "", "subjectId", "", "seriesId", "brandsId", "visibleItemCount", "totalItemCount", "adapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/OrderAdapter;", "selectedProductsList", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "productList", "", "searchText", "manager", "Landroidx/recyclerview/widget/GridLayoutManager;", "isLastPage", "", "isScrolling", "visitId", "selectedSegment", "Ljava/lang/Integer;", "selectedGrade", "selectedSubject", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setAdapter", "finishAllAct", "fetchProductsWithFilter", "parseProductsArray", "jsonArray", "Lorg/json/JSONArray;", "getToken", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class AllProductsActivity extends BaseActivity {
    private OrderAdapter adapter;
    private ActivityAllProductsBinding binding;
    private boolean isLastPage;
    private boolean isScrolling;
    private GridLayoutManager manager;
    private List<Products> productList;
    private String searchText;
    private Integer selectedGrade;
    private Integer selectedSegment;
    private Integer selectedSubject;
    private int totalItemCount;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private int visibleItemCount;
    private String visitId;
    private int pageNo = 1;
    private String subjectId = "";
    private String seriesId = "";
    private String brandsId = "";
    private List<Products> selectedProductsList = new ArrayList();

    public AllProductsActivity() {
        final AllProductsActivity $this$viewModels_u24default$iv = this;
        final Function0 extrasProducer$iv = null;
        Function0 factoryPromise$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return ComponentActivity.this.getDefaultViewModelProviderFactory();
            }
        };
        this.viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(VisitViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return ComponentActivity.this.getViewModelStore();
            }
        }, factoryPromise$iv, new Function0<CreationExtras>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$special$$inlined$viewModels$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function0 = Function0.this;
                return (function0 == null || (creationExtras = (CreationExtras) function0.invoke()) == null) ? $this$viewModels_u24default$iv.getDefaultViewModelCreationExtras() : creationExtras;
            }
        });
        this.searchText = "";
        this.visitId = "";
    }

    private final VisitViewModel getViewModel() {
        return (VisitViewModel) this.viewModel.getValue();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityAllProductsBinding.inflate(getLayoutInflater());
        ActivityAllProductsBinding activityAllProductsBinding = this.binding;
        ActivityAllProductsBinding activityAllProductsBinding2 = null;
        if (activityAllProductsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAllProductsBinding = null;
        }
        setContentView(activityAllProductsBinding.getRoot());
        ExtensionKt.belowStatusBarText(this);
        setAdapter();
        getViewModel().productListRequest("", this.pageNo, "", this.subjectId, this.brandsId, this.seriesId);
        getViewModel().getProductListResponse().observe(this, new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AllProductsActivity.onCreate$lambda$3(AllProductsActivity.this, (ApiResponseCallback) obj);
            }
        });
        ActivityAllProductsBinding activityAllProductsBinding3 = this.binding;
        if (activityAllProductsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAllProductsBinding3 = null;
        }
        activityAllProductsBinding3.setListener(new AllProductsActivity$onCreate$2(this));
        ActivityAllProductsBinding activityAllProductsBinding4 = this.binding;
        if (activityAllProductsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAllProductsBinding4 = null;
        }
        activityAllProductsBinding4.ivBack.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity.this.finish();
            }
        });
        ActivityAllProductsBinding activityAllProductsBinding5 = this.binding;
        if (activityAllProductsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAllProductsBinding5 = null;
        }
        activityAllProductsBinding5.inventorySearchEt.addTextChangedListener(new TextWatcher() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$onCreate$4
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query;
                OrderAdapter orderAdapter;
                if (s == null || (query = s.toString()) == null) {
                    query = "";
                }
                Log.i("AllProductsActivity", "filterProducts: " + query);
                orderAdapter = AllProductsActivity.this.adapter;
                if (orderAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    orderAdapter = null;
                }
                orderAdapter.filterProducts(query);
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        ActivityAllProductsBinding activityAllProductsBinding6 = this.binding;
        if (activityAllProductsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAllProductsBinding2 = activityAllProductsBinding6;
        }
        activityAllProductsBinding2.searchIcon.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsActivity.onCreate$lambda$5(AllProductsActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onCreate$lambda$3(AllProductsActivity allProductsActivity, ApiResponseCallback apiResponseCallback) {
        if (apiResponseCallback != null) {
            ActivityAllProductsBinding activityAllProductsBinding = null;
            ActivityAllProductsBinding activityAllProductsBinding2 = null;
            List<Products> list = null;
            if (apiResponseCallback instanceof ApiResponseCallback.Loading) {
                ActivityAllProductsBinding activityAllProductsBinding3 = allProductsActivity.binding;
                if (activityAllProductsBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityAllProductsBinding2 = activityAllProductsBinding3;
                }
                LayoutLoadingBinding layoutProgressIndicator = activityAllProductsBinding2.layoutProgressIndicator;
                Intrinsics.checkNotNullExpressionValue(layoutProgressIndicator, "layoutProgressIndicator");
                allProductsActivity.showProgressIndicator(layoutProgressIndicator);
                return;
            }
            if (apiResponseCallback instanceof ApiResponseCallback.Success) {
                ActivityAllProductsBinding activityAllProductsBinding4 = allProductsActivity.binding;
                if (activityAllProductsBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAllProductsBinding4 = null;
                }
                LayoutLoadingBinding layoutProgressIndicator2 = activityAllProductsBinding4.layoutProgressIndicator;
                Intrinsics.checkNotNullExpressionValue(layoutProgressIndicator2, "layoutProgressIndicator");
                allProductsActivity.hideProgressIndicator(layoutProgressIndicator2);
                ProductListResponse productListResponse = (ProductListResponse) ((ApiResponseCallback.Success) apiResponseCallback).getData();
                if (productListResponse != null) {
                    Boolean success = productListResponse.getSuccess();
                    Intrinsics.checkNotNull(success);
                    if (success.booleanValue()) {
                        allProductsActivity.productList = productListResponse.getProducts();
                        OrderAdapter orderAdapter = allProductsActivity.adapter;
                        if (orderAdapter == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                            orderAdapter = null;
                        }
                        List<Products> list2 = allProductsActivity.productList;
                        if (list2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("productList");
                        } else {
                            list = list2;
                        }
                        orderAdapter.setItems(list);
                        return;
                    }
                    return;
                }
                return;
            }
            if (!(apiResponseCallback instanceof ApiResponseCallback.Error)) {
                throw new NoWhenBranchMatchedException();
            }
            ActivityAllProductsBinding activityAllProductsBinding5 = allProductsActivity.binding;
            if (activityAllProductsBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityAllProductsBinding = activityAllProductsBinding5;
            }
            LayoutLoadingBinding layoutProgressIndicator3 = activityAllProductsBinding.layoutProgressIndicator;
            Intrinsics.checkNotNullExpressionValue(layoutProgressIndicator3, "layoutProgressIndicator");
            allProductsActivity.hideProgressIndicator(layoutProgressIndicator3);
            allProductsActivity.genericNetworkErrorHandler(apiResponseCallback, new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit onCreate$lambda$3$lambda$2$lambda$1;
                    onCreate$lambda$3$lambda$2$lambda$1 = AllProductsActivity.onCreate$lambda$3$lambda$2$lambda$1((ErrorHandler) obj);
                    return onCreate$lambda$3$lambda$2$lambda$1;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$3$lambda$2$lambda$1(ErrorHandler it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5(AllProductsActivity this$0, View it) {
        ActivityAllProductsBinding activityAllProductsBinding = this$0.binding;
        ActivityAllProductsBinding activityAllProductsBinding2 = null;
        if (activityAllProductsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAllProductsBinding = null;
        }
        if (activityAllProductsBinding.inventorySearchEt.getVisibility() == 8) {
            ActivityAllProductsBinding activityAllProductsBinding3 = this$0.binding;
            if (activityAllProductsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAllProductsBinding3 = null;
            }
            activityAllProductsBinding3.inventorySearchEt.setVisibility(0);
            ActivityAllProductsBinding activityAllProductsBinding4 = this$0.binding;
            if (activityAllProductsBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAllProductsBinding4 = null;
            }
            activityAllProductsBinding4.allBookText.setVisibility(8);
            ActivityAllProductsBinding activityAllProductsBinding5 = this$0.binding;
            if (activityAllProductsBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAllProductsBinding5 = null;
            }
            activityAllProductsBinding5.searchIcon.setImageResource(R.drawable.cross_btn);
            ActivityAllProductsBinding activityAllProductsBinding6 = this$0.binding;
            if (activityAllProductsBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityAllProductsBinding2 = activityAllProductsBinding6;
            }
            activityAllProductsBinding2.inventorySearchEt.requestFocus();
            return;
        }
        ActivityAllProductsBinding activityAllProductsBinding7 = this$0.binding;
        if (activityAllProductsBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAllProductsBinding7 = null;
        }
        activityAllProductsBinding7.inventorySearchEt.setVisibility(8);
        ActivityAllProductsBinding activityAllProductsBinding8 = this$0.binding;
        if (activityAllProductsBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAllProductsBinding8 = null;
        }
        activityAllProductsBinding8.allBookText.setVisibility(0);
        ActivityAllProductsBinding activityAllProductsBinding9 = this$0.binding;
        if (activityAllProductsBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAllProductsBinding9 = null;
        }
        activityAllProductsBinding9.searchIcon.setImageResource(R.drawable.ic_search);
        ActivityAllProductsBinding activityAllProductsBinding10 = this$0.binding;
        if (activityAllProductsBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAllProductsBinding2 = activityAllProductsBinding10;
        }
        Editable text = activityAllProductsBinding2.inventorySearchEt.getText();
        if (text != null) {
            text.clear();
        }
    }

    private final void setAdapter() {
        this.adapter = new OrderAdapter(this.selectedProductsList, new RecyclerViewListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AllProductsActivity$setAdapter$1
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
            public void onSelectGradesSubjects(GradesSubjectsData gradesSubjects) {
                RecyclerViewListener.DefaultImpls.onSelectGradesSubjects(this, gradesSubjects);
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
            public void onSelectProduct(Products products) {
                List list;
                ActivityAllProductsBinding activityAllProductsBinding;
                Intrinsics.checkNotNullParameter(products, "products");
                list = AllProductsActivity.this.selectedProductsList;
                String productsCount = String.valueOf(list.size());
                activityAllProductsBinding = AllProductsActivity.this.binding;
                if (activityAllProductsBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAllProductsBinding = null;
                }
                activityAllProductsBinding.productCount.setText(productsCount);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onTapUpdateQuantity(Products products) {
                Intrinsics.checkNotNullParameter(products, "products");
            }
        });
        ActivityAllProductsBinding activityAllProductsBinding = this.binding;
        GridLayoutManager gridLayoutManager = null;
        if (activityAllProductsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAllProductsBinding = null;
        }
        RecyclerView recyclerView = activityAllProductsBinding.productsRv;
        OrderAdapter orderAdapter = this.adapter;
        if (orderAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            orderAdapter = null;
        }
        recyclerView.setAdapter(orderAdapter);
        Integer INTEGER_TWO = NumberUtils.INTEGER_TWO;
        Intrinsics.checkNotNullExpressionValue(INTEGER_TWO, "INTEGER_TWO");
        this.manager = new GridLayoutManager((Context) this, INTEGER_TWO.intValue(), 1, false);
        ActivityAllProductsBinding activityAllProductsBinding2 = this.binding;
        if (activityAllProductsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAllProductsBinding2 = null;
        }
        RecyclerView recyclerView2 = activityAllProductsBinding2.productsRv;
        GridLayoutManager gridLayoutManager2 = this.manager;
        if (gridLayoutManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manager");
        } else {
            gridLayoutManager = gridLayoutManager2;
        }
        recyclerView2.setLayoutManager(gridLayoutManager);
    }

    public final void finishAllAct() {
        Intent resultIntent = new Intent();
        setResult(-1, resultIntent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fetchProductsWithFilter() {
        List params = new ArrayList();
        params.add("search=");
        params.add("page=" + this.pageNo);
        params.add("customerId=");
        Integer num = this.selectedSubject;
        if (num != null) {
            int it = num.intValue();
            params.add("subject_id=" + it);
        }
        Integer num2 = this.selectedSegment;
        if (num2 != null) {
            int it2 = num2.intValue();
            params.add("series_id=" + it2);
        }
        Integer num3 = this.selectedGrade;
        if (num3 != null) {
            int it3 = num3.intValue();
            params.add("grade_id=" + it3);
        }
        String finalUrl = "https://staging.bookmark.services/api/getProductList?" + CollectionsKt.joinToString$default(params, "&", null, null, 0, null, null, 62, null);
        Log.d("FILTER_API", finalUrl);
        Request.Builder addHeader = new Request.Builder().url(finalUrl).addHeader(HttpHeaders.ACCEPT, "application/json");
        String token = getToken();
        if (token == null) {
            token = "";
        }
        Request request = addHeader.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
        new OkHttpClient().newCall(request).enqueue(new AllProductsActivity$fetchProductsWithFilter$4(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Products> parseProductsArray(JSONArray jsonArray) {
        List list = new ArrayList();
        int length = jsonArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            int optInt = obj.optInt(Constant.VISIT_ID);
            String optString = obj.optString("title");
            String valueOf = String.valueOf(obj.optInt("stockAvailable"));
            String optString2 = obj.optString("companyPriceForDisplay");
            String optString3 = obj.optString("image");
            String optString4 = obj.optString("grade");
            list.add(new Products(Integer.valueOf(optInt), null, null, null, null, optString, null, null, null, optString3, optString2, null, valueOf, null, null, null, null, null, null, null, obj.optString("subject"), null, optString4, null, null, null, null, 128969182, null));
        }
        return list;
    }

    private final String getToken() {
        SharedPreferences sharedPref = getSharedPreferences("BookmarkApp", 0);
        return sharedPref.getString("AUTH_TOKEN", null);
    }
}
