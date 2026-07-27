package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.activity.ComponentActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.savedstate.SavedStateRegistryOwner;
import com.ingenious.androidbookmarksalesupgrade.adapter.LowStockRefillAdapter;
import com.ingenious.androidbookmarksalesupgrade.adapter.SegmentLowStockRefillAdapter;
import com.ingenious.androidbookmarksalesupgrade.bottomsheet.LowStockProductSelectionBottomSheet;
import com.ingenious.androidbookmarksalesupgrade.bottomsheet.SegmentLowStockProductSelectionBottomSheet;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityLowStockBinding;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.listener.OnResultListener;
import com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener;
import com.ingenious.androidbookmarksalesupgrade.model.BookModel;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooksData;
import com.ingenious.androidbookmarksalesupgrade.model.response.BooksBySegmentData;
import com.ingenious.androidbookmarksalesupgrade.model.response.GradesSubjectsData;
import com.ingenious.androidbookmarksalesupgrade.model.response.LowStockInventoryResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import com.ingenious.androidbookmarksalesupgrade.utils.LoggingInterceptor;
import com.ingenious.androidbookmarksalesupgrade.viewModel.InventoryViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.apache.commons.lang3.math.NumberUtils;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: LowStockActivity.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u0019H\u0002J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u0019H\u0002J\b\u0010 \u001a\u00020\u0019H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\"\u0010\u0014\u001a\u0016\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015j\n\u0012\u0004\u0012\u00020\u0016\u0018\u0001`\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/LowStockActivity;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/BaseActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityLowStockBinding;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/InventoryViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/InventoryViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "selectedProduct", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "selectedProductList", "", "adapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/LowStockRefillAdapter;", "segmentAdapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/SegmentLowStockRefillAdapter;", "segmentBooks", "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/BookModel;", "Lkotlin/collections/ArrayList;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setAdapter", "getSelectedProduct", "", "updateProductList", "segmentSetAdapter", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class LowStockActivity extends BaseActivity {
    private LowStockRefillAdapter adapter;
    private ActivityLowStockBinding binding;
    private SegmentLowStockRefillAdapter segmentAdapter;
    private ArrayList<BookModel> segmentBooks;
    private Products selectedProduct;
    private final List<Products> selectedProductList;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public LowStockActivity() {
        final LowStockActivity $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LowStockActivity$special$$inlined$viewModel$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelOwner invoke() {
                ViewModelOwner.Companion companion = ViewModelOwner.INSTANCE;
                ComponentActivity componentActivity = ComponentActivity.this;
                ComponentActivity componentActivity2 = ComponentActivity.this;
                return companion.from(componentActivity, componentActivity2 instanceof SavedStateRegistryOwner ? componentActivity2 : null);
            }
        };
        final Function0 parameters$iv = null;
        final Scope scope$iv = AndroidKoinScopeExtKt.getKoinScope($this$viewModel_u24default$iv);
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LowStockActivity$special$$inlined$viewModel$default$2
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
        this.viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(InventoryViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LowStockActivity$special$$inlined$viewModel$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, factoryProducer$iv$iv);
        this.selectedProductList = new ArrayList();
    }

    private final InventoryViewModel getViewModel() {
        return (InventoryViewModel) this.viewModel.getValue();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityLowStockBinding.inflate(getLayoutInflater());
        ActivityLowStockBinding activityLowStockBinding = this.binding;
        ActivityLowStockBinding activityLowStockBinding2 = null;
        if (activityLowStockBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLowStockBinding = null;
        }
        setContentView(activityLowStockBinding.getRoot());
        ExtensionKt.belowStatusBarText(this);
        String segmentName = getIntent().getStringExtra("SEGMENT_NAME");
        this.segmentBooks = getIntent().getParcelableArrayListExtra("SEGMENT_BOOKS");
        ArrayList<BookModel> arrayList = this.segmentBooks;
        if (!(arrayList == null || arrayList.isEmpty())) {
            Log.d("LowStockActivity", "Segment name: " + segmentName);
            segmentSetAdapter();
        } else {
            setAdapter();
        }
        ActivityLowStockBinding activityLowStockBinding3 = this.binding;
        if (activityLowStockBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLowStockBinding3 = null;
        }
        activityLowStockBinding3.backArrow.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LowStockActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LowStockActivity.this.finish();
            }
        });
        getViewModel().lowStock();
        getViewModel().getLowStockResponse().observe(this, new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LowStockActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LowStockActivity.onCreate$lambda$3(LowStockActivity.this, (ApiResponseCallback) obj);
            }
        });
        ActivityLowStockBinding activityLowStockBinding4 = this.binding;
        if (activityLowStockBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityLowStockBinding2 = activityLowStockBinding4;
        }
        activityLowStockBinding2.setListener(new GenericListeners() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LowStockActivity$onCreate$3
            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onNotificationClick() {
                GenericListeners.DefaultImpls.onNotificationClick(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onSettingClick() {
                GenericListeners.DefaultImpls.onSettingClick(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapAddBooks() {
                GenericListeners.DefaultImpls.onTapAddBooks(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapAddCustomer() {
                GenericListeners.DefaultImpls.onTapAddCustomer(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapAddHome() {
                GenericListeners.DefaultImpls.onTapAddHome(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapAddVisit() {
                GenericListeners.DefaultImpls.onTapAddVisit(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapBack() {
                GenericListeners.DefaultImpls.onTapBack(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapCaptureImage() {
                GenericListeners.DefaultImpls.onTapCaptureImage(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapCheckIn() {
                GenericListeners.DefaultImpls.onTapCheckIn(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapCompleteVisit() {
                GenericListeners.DefaultImpls.onTapCompleteVisit(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapDate() {
                GenericListeners.DefaultImpls.onTapDate(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapDateNext() {
                GenericListeners.DefaultImpls.onTapDateNext(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapDatePrevious() {
                GenericListeners.DefaultImpls.onTapDatePrevious(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapDismiss() {
                GenericListeners.DefaultImpls.onTapDismiss(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapDone() {
                GenericListeners.DefaultImpls.onTapDone(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapFilter() {
                GenericListeners.DefaultImpls.onTapFilter(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapForgetPassword() {
                GenericListeners.DefaultImpls.onTapForgetPassword(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapLocation() {
                GenericListeners.DefaultImpls.onTapLocation(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapLocationFab() {
                GenericListeners.DefaultImpls.onTapLocationFab(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapLogin() {
                GenericListeners.DefaultImpls.onTapLogin(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapLogout() {
                GenericListeners.DefaultImpls.onTapLogout(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapLowStock() {
                GenericListeners.DefaultImpls.onTapLowStock(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapNewAccount() {
                GenericListeners.DefaultImpls.onTapNewAccount(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapOTP() {
                GenericListeners.DefaultImpls.onTapOTP(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapProfile() {
                GenericListeners.DefaultImpls.onTapProfile(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapRefillRequests() {
                GenericListeners.DefaultImpls.onTapRefillRequests(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapResetPassword() {
                GenericListeners.DefaultImpls.onTapResetPassword(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapSendMessage() {
                GenericListeners.DefaultImpls.onTapSendMessage(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapSettings() {
                GenericListeners.DefaultImpls.onTapSettings(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapSwitch() {
                GenericListeners.DefaultImpls.onTapSwitch(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapViewSelection() {
                GenericListeners.DefaultImpls.onTapViewSelection(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapRefill() {
                ArrayList arrayList2;
                List selectedProductsList;
                SegmentLowStockRefillAdapter segmentLowStockRefillAdapter;
                arrayList2 = LowStockActivity.this.segmentBooks;
                ArrayList arrayList3 = arrayList2;
                if (!(arrayList3 == null || arrayList3.isEmpty())) {
                    segmentLowStockRefillAdapter = LowStockActivity.this.segmentAdapter;
                    if (segmentLowStockRefillAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("segmentAdapter");
                        segmentLowStockRefillAdapter = null;
                    }
                    List selectedBooks = segmentLowStockRefillAdapter.getSelectedBooks();
                    Log.d("TAG", "Selected segment books: " + selectedBooks.size());
                    if (selectedBooks.isEmpty()) {
                        Log.d("TAG", "No segment book selected");
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("selectedBooks", new ArrayList<>(selectedBooks));
                    SegmentLowStockProductSelectionBottomSheet bottomSheet = new SegmentLowStockProductSelectionBottomSheet(new ArrayList(selectedBooks));
                    bottomSheet.setArguments(bundle);
                    bottomSheet.show(LowStockActivity.this.getSupportFragmentManager(), "lowStockBottomSheet");
                    return;
                }
                selectedProductsList = LowStockActivity.this.getSelectedProduct();
                Log.d("TAG", "Selected products in onTapRefill: " + selectedProductsList.size());
                if (selectedProductsList.isEmpty()) {
                    Log.d("TAG", "No product selected");
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putParcelableArrayList("selectedProduct", new ArrayList<>(selectedProductsList));
                LowStockProductSelectionBottomSheet bottomSheet2 = new LowStockProductSelectionBottomSheet(selectedProductsList);
                bottomSheet2.setArguments(bundle2);
                bottomSheet2.show(LowStockActivity.this.getSupportFragmentManager(), "lowStockBottomSheet");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onCreate$lambda$3(LowStockActivity this$0, ApiResponseCallback it) {
        if (it != null && !(it instanceof ApiResponseCallback.Error) && !(it instanceof ApiResponseCallback.Loading)) {
            if (!(it instanceof ApiResponseCallback.Success)) {
                throw new NoWhenBranchMatchedException();
            }
            LowStockInventoryResponse data = (LowStockInventoryResponse) ((ApiResponseCallback.Success) it).getData();
            if (data != null) {
                Boolean success = data.getSuccess();
                Intrinsics.checkNotNull(success);
                if (success.booleanValue() && this$0.adapter != null) {
                    LowStockRefillAdapter lowStockRefillAdapter = this$0.adapter;
                    if (lowStockRefillAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        lowStockRefillAdapter = null;
                    }
                    ArrayList<Products> lowStockProducts = data.getLowStockProducts();
                    lowStockRefillAdapter.addNewList(lowStockProducts != null ? lowStockProducts : CollectionsKt.emptyList());
                    Log.d("TAG", "Products: " + data.getLowStockProducts());
                }
            }
        }
    }

    private final void setAdapter() {
        this.adapter = new LowStockRefillAdapter(new RecyclerViewListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LowStockActivity$setAdapter$1
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
            public void onTotalUpdated(Products products) {
                RecyclerViewListener.DefaultImpls.onTotalUpdated(this, products);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onSelectProduct(Products products) {
                List list;
                List list2;
                List list3;
                List list4;
                Intrinsics.checkNotNullParameter(products, "products");
                if (Intrinsics.areEqual((Object) products.isSelected(), (Object) true)) {
                    list3 = LowStockActivity.this.selectedProductList;
                    if (!list3.contains(products)) {
                        list4 = LowStockActivity.this.selectedProductList;
                        list4.add(products);
                    }
                } else {
                    list = LowStockActivity.this.selectedProductList;
                    list.remove(products);
                }
                list2 = LowStockActivity.this.selectedProductList;
                Log.d(LoggingInterceptor.TAG, "Selected productss: " + list2.size());
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onTapSelectReturnProduct(Products products) {
                Intrinsics.checkNotNullParameter(products, "products");
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onTapAddPrice(Products products) {
                Intrinsics.checkNotNullParameter(products, "products");
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onTapUpdateQuantity(Products products) {
                Intrinsics.checkNotNullParameter(products, "products");
            }
        });
        ActivityLowStockBinding activityLowStockBinding = this.binding;
        ActivityLowStockBinding activityLowStockBinding2 = null;
        if (activityLowStockBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLowStockBinding = null;
        }
        RecyclerView recyclerView = activityLowStockBinding.lowStockRv;
        LowStockRefillAdapter lowStockRefillAdapter = this.adapter;
        if (lowStockRefillAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            lowStockRefillAdapter = null;
        }
        recyclerView.setAdapter(lowStockRefillAdapter);
        ActivityLowStockBinding activityLowStockBinding3 = this.binding;
        if (activityLowStockBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityLowStockBinding2 = activityLowStockBinding3;
        }
        RecyclerView recyclerView2 = activityLowStockBinding2.lowStockRv;
        Integer INTEGER_TWO = NumberUtils.INTEGER_TWO;
        Intrinsics.checkNotNullExpressionValue(INTEGER_TWO, "INTEGER_TWO");
        recyclerView2.setLayoutManager(new GridLayoutManager((Context) this, INTEGER_TWO.intValue(), 1, false));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Products> getSelectedProduct() {
        List<Products> list = this.selectedProductList;
        return list == null ? CollectionsKt.emptyList() : list;
    }

    private final void updateProductList() {
        LowStockRefillAdapter lowStockRefillAdapter = this.adapter;
        if (lowStockRefillAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            lowStockRefillAdapter = null;
        }
        lowStockRefillAdapter.notifyDataSetChanged();
    }

    private final void segmentSetAdapter() {
        this.segmentAdapter = new SegmentLowStockRefillAdapter(new Function2() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.LowStockActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit segmentSetAdapter$lambda$4;
                segmentSetAdapter$lambda$4 = LowStockActivity.segmentSetAdapter$lambda$4((BookModel) obj, ((Boolean) obj2).booleanValue());
                return segmentSetAdapter$lambda$4;
            }
        });
        ActivityLowStockBinding activityLowStockBinding = this.binding;
        SegmentLowStockRefillAdapter segmentLowStockRefillAdapter = null;
        if (activityLowStockBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLowStockBinding = null;
        }
        RecyclerView recyclerView = activityLowStockBinding.lowStockRv;
        SegmentLowStockRefillAdapter segmentLowStockRefillAdapter2 = this.segmentAdapter;
        if (segmentLowStockRefillAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("segmentAdapter");
            segmentLowStockRefillAdapter2 = null;
        }
        recyclerView.setAdapter(segmentLowStockRefillAdapter2);
        ActivityLowStockBinding activityLowStockBinding2 = this.binding;
        if (activityLowStockBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLowStockBinding2 = null;
        }
        activityLowStockBinding2.lowStockRv.setLayoutManager(new GridLayoutManager(this, 2));
        ArrayList it = this.segmentBooks;
        if (it != null) {
            SegmentLowStockRefillAdapter segmentLowStockRefillAdapter3 = this.segmentAdapter;
            if (segmentLowStockRefillAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("segmentAdapter");
            } else {
                segmentLowStockRefillAdapter = segmentLowStockRefillAdapter3;
            }
            segmentLowStockRefillAdapter.setNewList(it);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit segmentSetAdapter$lambda$4(BookModel book, boolean isSelected) {
        Intrinsics.checkNotNullParameter(book, "book");
        if (isSelected) {
            System.out.println((Object) ("Selected: " + book.getProductName()));
        } else {
            System.out.println((Object) ("Unselected: " + book.getProductName()));
        }
        return Unit.INSTANCE;
    }
}
