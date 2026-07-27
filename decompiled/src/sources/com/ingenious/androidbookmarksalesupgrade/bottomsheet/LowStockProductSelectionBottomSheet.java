package com.ingenious.androidbookmarksalesupgrade.bottomsheet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.savedstate.SavedStateRegistryOwner;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.RefillProductsAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentLowStockSelectionBottomSheetBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.listener.OnResultListener;
import com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener;
import com.ingenious.androidbookmarksalesupgrade.model.BookModel;
import com.ingenious.androidbookmarksalesupgrade.model.request.CreateProductRefill;
import com.ingenious.androidbookmarksalesupgrade.model.request.CreateProductRefillRequest;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooksData;
import com.ingenious.androidbookmarksalesupgrade.model.response.BooksBySegmentData;
import com.ingenious.androidbookmarksalesupgrade.model.response.CreateProductData;
import com.ingenious.androidbookmarksalesupgrade.model.response.CreateProductRefillResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.GradesSubjectsData;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.RefillRequestsActivity;
import com.ingenious.androidbookmarksalesupgrade.utils.LoggingInterceptor;
import com.ingenious.androidbookmarksalesupgrade.viewModel.MainViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: LowStockProductSelectionBottomSheet.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J$\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020%H\u0002J\b\u0010'\u001a\u00020%H\u0002J\u0010\u0010(\u001a\u00020%2\u0006\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020\rH\u0016R\u0016\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u000fj\b\u0012\u0004\u0012\u00020\u0004`\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019¨\u0006,"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/bottomsheet/LowStockProductSelectionBottomSheet;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "products", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "<init>", "(Ljava/util/List;)V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentLowStockSelectionBottomSheetBinding;", "selectedProducts", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/CreateProductRefill;", "totalPrice", "", "productList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "totalAmount", "", "selectedProduct", "adapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/RefillProductsAdapter;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/MainViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setupAdapter", "", "calculateTotal", "updateProductList", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "getTheme", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class LowStockProductSelectionBottomSheet extends BottomSheetDialogFragment {
    private RefillProductsAdapter adapter;
    private FragmentLowStockSelectionBottomSheetBinding binding;
    private ArrayList<Products> productList;
    private final List<Products> products;
    private Products selectedProduct;
    private List<CreateProductRefill> selectedProducts;
    private double totalAmount;
    private int totalPrice;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public LowStockProductSelectionBottomSheet() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ LowStockProductSelectionBottomSheet(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }

    public LowStockProductSelectionBottomSheet(List<Products> list) {
        this.products = list;
        this.selectedProducts = new ArrayList();
        this.productList = new ArrayList<>();
        final LowStockProductSelectionBottomSheet $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.LowStockProductSelectionBottomSheet$special$$inlined$viewModel$default$1
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
        final Function0 ownerProducer$iv$iv = new Function0<ViewModelStoreOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.LowStockProductSelectionBottomSheet$special$$inlined$viewModel$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return ((ViewModelOwner) Function0.this.invoke()).getStoreOwner();
            }
        };
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.LowStockProductSelectionBottomSheet$special$$inlined$viewModel$default$3
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default$iv, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.LowStockProductSelectionBottomSheet$special$$inlined$viewModel$default$4
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
    public final MainViewModel getViewModel() {
        return (MainViewModel) this.viewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.binding = (FragmentLowStockSelectionBottomSheetBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_low_stock_selection_bottom_sheet, container, false);
        FragmentLowStockSelectionBottomSheetBinding fragmentLowStockSelectionBottomSheetBinding = this.binding;
        FragmentLowStockSelectionBottomSheetBinding fragmentLowStockSelectionBottomSheetBinding2 = null;
        if (fragmentLowStockSelectionBottomSheetBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLowStockSelectionBottomSheetBinding = null;
        }
        fragmentLowStockSelectionBottomSheetBinding.totalPrice.setText("0.0");
        Bundle it = getArguments();
        if (it != null) {
            ArrayList<Products> parcelableArrayList = it.getParcelableArrayList("selectedProduct");
            if (parcelableArrayList == null) {
                parcelableArrayList = new ArrayList<>();
            }
            this.productList = parcelableArrayList;
            Log.d(LoggingInterceptor.TAG, "selected products in bottomsheet: " + this.productList);
        }
        FragmentLowStockSelectionBottomSheetBinding fragmentLowStockSelectionBottomSheetBinding3 = this.binding;
        if (fragmentLowStockSelectionBottomSheetBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLowStockSelectionBottomSheetBinding3 = null;
        }
        fragmentLowStockSelectionBottomSheetBinding3.arrowBack.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.LowStockProductSelectionBottomSheet$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LowStockProductSelectionBottomSheet.this.dismiss();
            }
        });
        if (this.products == null) {
            CollectionsKt.emptyList();
        }
        setupAdapter();
        RefillProductsAdapter refillProductsAdapter = this.adapter;
        if (refillProductsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            refillProductsAdapter = null;
        }
        refillProductsAdapter.addList(this.productList);
        FragmentLowStockSelectionBottomSheetBinding fragmentLowStockSelectionBottomSheetBinding4 = this.binding;
        if (fragmentLowStockSelectionBottomSheetBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLowStockSelectionBottomSheetBinding4 = null;
        }
        fragmentLowStockSelectionBottomSheetBinding4.setListener(new GenericListeners() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.LowStockProductSelectionBottomSheet$onCreateView$3
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
            public void onTapDismiss() {
                LowStockProductSelectionBottomSheet.this.dismiss();
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapRefill() {
                ArrayList arrayList;
                FragmentLowStockSelectionBottomSheetBinding fragmentLowStockSelectionBottomSheetBinding5;
                FragmentLowStockSelectionBottomSheetBinding fragmentLowStockSelectionBottomSheetBinding6;
                MainViewModel viewModel;
                arrayList = LowStockProductSelectionBottomSheet.this.productList;
                ArrayList arrayList2 = arrayList;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
                Iterator it2 = arrayList2.iterator();
                while (true) {
                    fragmentLowStockSelectionBottomSheetBinding5 = null;
                    Integer num = null;
                    if (!it2.hasNext()) {
                        break;
                    }
                    Products products = (Products) it2.next();
                    Integer refillProductsId = products.getRefillProductsId();
                    String quantity = products.getQuantity();
                    if (quantity != null) {
                        num = Integer.valueOf(Integer.parseInt(quantity));
                    }
                    arrayList3.add(new CreateProductRefill(refillProductsId, num, products.getImage()));
                }
                ArrayList arrayList4 = arrayList3;
                Log.d("DEBUG", "Sending refill request: " + arrayList4);
                fragmentLowStockSelectionBottomSheetBinding6 = LowStockProductSelectionBottomSheet.this.binding;
                if (fragmentLowStockSelectionBottomSheetBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentLowStockSelectionBottomSheetBinding5 = fragmentLowStockSelectionBottomSheetBinding6;
                }
                CreateProductRefillRequest createProductRefillRequest = new CreateProductRefillRequest(arrayList4, fragmentLowStockSelectionBottomSheetBinding5.notesEditText.getText().toString());
                viewModel = LowStockProductSelectionBottomSheet.this.getViewModel();
                viewModel.createProductRefillRequest(createProductRefillRequest);
            }
        });
        getViewModel().getCreateProductRefillResponse().observe(getViewLifecycleOwner(), new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.LowStockProductSelectionBottomSheet$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LowStockProductSelectionBottomSheet.onCreateView$lambda$6(LowStockProductSelectionBottomSheet.this, (ApiResponseCallback) obj);
            }
        });
        FragmentLowStockSelectionBottomSheetBinding fragmentLowStockSelectionBottomSheetBinding5 = this.binding;
        if (fragmentLowStockSelectionBottomSheetBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentLowStockSelectionBottomSheetBinding2 = fragmentLowStockSelectionBottomSheetBinding5;
        }
        View root = fragmentLowStockSelectionBottomSheetBinding2.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onCreateView$lambda$6(final LowStockProductSelectionBottomSheet this$0, ApiResponseCallback it) {
        String str;
        String str2;
        if (it != null) {
            FragmentLowStockSelectionBottomSheetBinding fragmentLowStockSelectionBottomSheetBinding = null;
            if (it instanceof ApiResponseCallback.Error) {
                FragmentLowStockSelectionBottomSheetBinding fragmentLowStockSelectionBottomSheetBinding2 = this$0.binding;
                if (fragmentLowStockSelectionBottomSheetBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentLowStockSelectionBottomSheetBinding2 = null;
                }
                fragmentLowStockSelectionBottomSheetBinding2.progressBar.setVisibility(8);
                FragmentLowStockSelectionBottomSheetBinding fragmentLowStockSelectionBottomSheetBinding3 = this$0.binding;
                if (fragmentLowStockSelectionBottomSheetBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentLowStockSelectionBottomSheetBinding = fragmentLowStockSelectionBottomSheetBinding3;
                }
                fragmentLowStockSelectionBottomSheetBinding.confirmButton.setVisibility(0);
                Toast.makeText(this$0.requireContext(), "Error: " + ((ApiResponseCallback.Error) it).getMessage(), 1).show();
                String message = ((ApiResponseCallback.Error) it).getMessage();
                if (message == null) {
                    message = "";
                }
                Log.d("failed", message);
                return;
            }
            if (it instanceof ApiResponseCallback.Loading) {
                FragmentLowStockSelectionBottomSheetBinding fragmentLowStockSelectionBottomSheetBinding4 = this$0.binding;
                if (fragmentLowStockSelectionBottomSheetBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentLowStockSelectionBottomSheetBinding4 = null;
                }
                fragmentLowStockSelectionBottomSheetBinding4.progressBar.setVisibility(0);
                FragmentLowStockSelectionBottomSheetBinding fragmentLowStockSelectionBottomSheetBinding5 = this$0.binding;
                if (fragmentLowStockSelectionBottomSheetBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentLowStockSelectionBottomSheetBinding = fragmentLowStockSelectionBottomSheetBinding5;
                }
                fragmentLowStockSelectionBottomSheetBinding.confirmButton.setVisibility(8);
                return;
            }
            if (!(it instanceof ApiResponseCallback.Success)) {
                throw new NoWhenBranchMatchedException();
            }
            FragmentLowStockSelectionBottomSheetBinding fragmentLowStockSelectionBottomSheetBinding6 = this$0.binding;
            if (fragmentLowStockSelectionBottomSheetBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLowStockSelectionBottomSheetBinding6 = null;
            }
            fragmentLowStockSelectionBottomSheetBinding6.progressBar.setVisibility(8);
            FragmentLowStockSelectionBottomSheetBinding fragmentLowStockSelectionBottomSheetBinding7 = this$0.binding;
            if (fragmentLowStockSelectionBottomSheetBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentLowStockSelectionBottomSheetBinding7 = null;
            }
            fragmentLowStockSelectionBottomSheetBinding7.confirmButton.setVisibility(0);
            CreateProductRefillResponse data = (CreateProductRefillResponse) ((ApiResponseCallback.Success) it).getData();
            if (data != null) {
                Boolean success = data.getSuccess();
                Intrinsics.checkNotNull(success);
                if (success.booleanValue()) {
                    View dialogView = this$0.getLayoutInflater().inflate(R.layout.dialog_refill_request, (ViewGroup) null);
                    final AlertDialog dialog = new AlertDialog.Builder(this$0.requireContext()).setView(dialogView).create();
                    ImageView closeBtn = (ImageView) dialogView.findViewById(R.id.close_btn);
                    TextView trackIdText = (TextView) dialogView.findViewById(R.id.track_id);
                    TextView date = (TextView) dialogView.findViewById(R.id.date);
                    TextView refillRequestBtn = (TextView) dialogView.findViewById(R.id.view_requests_button);
                    CreateProductData data2 = data.getData();
                    if (data2 == null || (str = data2.getRequestid()) == null) {
                        str = "KHI607";
                    }
                    trackIdText.setText(str);
                    CreateProductData data3 = data.getData();
                    if (data3 == null || (str2 = data3.getCreatedAt()) == null) {
                        str2 = "16 Jul, 2025";
                    }
                    date.setText(str2);
                    Window window = dialog.getWindow();
                    if (window != null) {
                        window.setBackgroundDrawable(new ColorDrawable(0));
                    }
                    closeBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.LowStockProductSelectionBottomSheet$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    refillRequestBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.LowStockProductSelectionBottomSheet$$ExternalSyntheticLambda1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            LowStockProductSelectionBottomSheet.onCreateView$lambda$6$lambda$5$lambda$4$lambda$3(LowStockProductSelectionBottomSheet.this, view);
                        }
                    });
                    dialog.show();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$6$lambda$5$lambda$4$lambda$3(LowStockProductSelectionBottomSheet this$0, View it) {
        ActivityExtKt.gotoActivityFromFragment(this$0, RefillRequestsActivity.class);
        FragmentActivity requireActivity = this$0.requireActivity();
        if (requireActivity != null) {
            requireActivity.finish();
        }
    }

    private final void setupAdapter() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        FragmentLowStockSelectionBottomSheetBinding fragmentLowStockSelectionBottomSheetBinding = null;
        this.adapter = new RefillProductsAdapter(requireContext, null, new RecyclerViewListener() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.LowStockProductSelectionBottomSheet$setupAdapter$1
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
                Intrinsics.checkNotNullParameter(products, "products");
                LowStockProductSelectionBottomSheet.this.calculateTotal();
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onTapUpdateQuantity(Products products) {
                Intrinsics.checkNotNullParameter(products, "products");
            }
        }, 2, null);
        FragmentLowStockSelectionBottomSheetBinding fragmentLowStockSelectionBottomSheetBinding2 = this.binding;
        if (fragmentLowStockSelectionBottomSheetBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLowStockSelectionBottomSheetBinding2 = null;
        }
        RecyclerView recyclerView = fragmentLowStockSelectionBottomSheetBinding2.productRecyclerView;
        RefillProductsAdapter refillProductsAdapter = this.adapter;
        if (refillProductsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            refillProductsAdapter = null;
        }
        recyclerView.setAdapter(refillProductsAdapter);
        FragmentLowStockSelectionBottomSheetBinding fragmentLowStockSelectionBottomSheetBinding3 = this.binding;
        if (fragmentLowStockSelectionBottomSheetBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentLowStockSelectionBottomSheetBinding = fragmentLowStockSelectionBottomSheetBinding3;
        }
        fragmentLowStockSelectionBottomSheetBinding.productRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), 1, false));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void calculateTotal() {
        this.totalAmount = Utils.DOUBLE_EPSILON;
        Iterable $this$forEach$iv = this.productList;
        for (Object element$iv : $this$forEach$iv) {
            Products product = (Products) element$iv;
            String quantity = product.getQuantity();
            int quantity2 = quantity != null ? Integer.parseInt(quantity) : 0;
            String price = product.getPrice();
            double price2 = price != null ? Double.parseDouble(price) : 0.0d;
            this.totalAmount += quantity2 * price2;
        }
        FragmentLowStockSelectionBottomSheetBinding fragmentLowStockSelectionBottomSheetBinding = this.binding;
        if (fragmentLowStockSelectionBottomSheetBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLowStockSelectionBottomSheetBinding = null;
        }
        fragmentLowStockSelectionBottomSheetBinding.totalPrice.setText(String.valueOf(this.totalAmount));
    }

    private final void updateProductList() {
        RefillProductsAdapter refillProductsAdapter = this.adapter;
        RefillProductsAdapter refillProductsAdapter2 = null;
        if (refillProductsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            refillProductsAdapter = null;
        }
        refillProductsAdapter.addList(this.productList);
        RefillProductsAdapter refillProductsAdapter3 = this.adapter;
        if (refillProductsAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            refillProductsAdapter2 = refillProductsAdapter3;
        }
        refillProductsAdapter2.notifyDataSetChanged();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
        RefillProductsAdapter refillProductsAdapter = this.adapter;
        if (refillProductsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            refillProductsAdapter = null;
        }
        refillProductsAdapter.addList(new ArrayList<>());
    }

    @Override // androidx.fragment.app.DialogFragment
    public int getTheme() {
        return R.style.Rounded_DialogTheme;
    }
}
