package com.ingenious.androidbookmarksalesupgrade.bottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.savedstate.SavedStateRegistryOwner;
import com.google.firebase.messaging.Constants;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter;
import com.ingenious.androidbookmarksalesupgrade.adapter.ProductsAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.BottomSheetRefillRequestProductDetailsBinding;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import com.ingenious.androidbookmarksalesupgrade.model.response.RefillByStatusData;
import com.ingenious.androidbookmarksalesupgrade.viewModel.MainViewModel;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: RefillRequestProductDetailsBottomSheet.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/bottomsheet/RefillRequestProductDetailsBottomSheet;", "Landroidx/fragment/app/DialogFragment;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/BottomSheetRefillRequestProductDetailsBinding;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/MainViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "adapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "refillData", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/RefillByStatusData;", "imageAdapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/ProductsAdapter;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setupAdapter", "", "imageAdapterFun", "getTheme", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class RefillRequestProductDetailsBottomSheet extends DialogFragment {
    private GenericAdapter<Products> adapter;
    private BottomSheetRefillRequestProductDetailsBinding binding;
    private ProductsAdapter imageAdapter;
    private RefillByStatusData refillData;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public RefillRequestProductDetailsBottomSheet() {
        final RefillRequestProductDetailsBottomSheet $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.RefillRequestProductDetailsBottomSheet$special$$inlined$viewModel$default$1
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
        final Function0 ownerProducer$iv$iv = new Function0<ViewModelStoreOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.RefillRequestProductDetailsBottomSheet$special$$inlined$viewModel$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return ((ViewModelOwner) Function0.this.invoke()).getStoreOwner();
            }
        };
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.RefillRequestProductDetailsBottomSheet$special$$inlined$viewModel$default$3
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default$iv, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.RefillRequestProductDetailsBottomSheet$special$$inlined$viewModel$default$4
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

    private final MainViewModel getViewModel() {
        return (MainViewModel) this.viewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayList<Products> products;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.binding = BottomSheetRefillRequestProductDetailsBinding.inflate(inflater, container, false);
        BottomSheetRefillRequestProductDetailsBinding bottomSheetRefillRequestProductDetailsBinding = this.binding;
        BottomSheetRefillRequestProductDetailsBinding bottomSheetRefillRequestProductDetailsBinding2 = null;
        if (bottomSheetRefillRequestProductDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            bottomSheetRefillRequestProductDetailsBinding = null;
        }
        bottomSheetRefillRequestProductDetailsBinding.crossBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.RefillRequestProductDetailsBottomSheet$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RefillRequestProductDetailsBottomSheet.this.dismiss();
            }
        });
        Bundle arguments = getArguments();
        this.refillData = arguments != null ? (RefillByStatusData) arguments.getParcelable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE) : null;
        RefillByStatusData data = this.refillData;
        if (data != null) {
            BottomSheetRefillRequestProductDetailsBinding bottomSheetRefillRequestProductDetailsBinding3 = this.binding;
            if (bottomSheetRefillRequestProductDetailsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                bottomSheetRefillRequestProductDetailsBinding3 = null;
            }
            bottomSheetRefillRequestProductDetailsBinding3.requestedId.setText(data.getRequestid());
            BottomSheetRefillRequestProductDetailsBinding bottomSheetRefillRequestProductDetailsBinding4 = this.binding;
            if (bottomSheetRefillRequestProductDetailsBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                bottomSheetRefillRequestProductDetailsBinding4 = null;
            }
            bottomSheetRefillRequestProductDetailsBinding4.dateTv.setText(data.getCreatedAt());
            BottomSheetRefillRequestProductDetailsBinding bottomSheetRefillRequestProductDetailsBinding5 = this.binding;
            if (bottomSheetRefillRequestProductDetailsBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                bottomSheetRefillRequestProductDetailsBinding5 = null;
            }
            bottomSheetRefillRequestProductDetailsBinding5.totalTv.setText("PKR " + data.getTotalAmount());
            BottomSheetRefillRequestProductDetailsBinding bottomSheetRefillRequestProductDetailsBinding6 = this.binding;
            if (bottomSheetRefillRequestProductDetailsBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                bottomSheetRefillRequestProductDetailsBinding6 = null;
            }
            bottomSheetRefillRequestProductDetailsBinding6.notesTv.setText(data.getNotes());
        }
        setupAdapter();
        imageAdapterFun();
        GenericAdapter<Products> genericAdapter = this.adapter;
        if (genericAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            genericAdapter = null;
        }
        RefillByStatusData refillByStatusData = this.refillData;
        genericAdapter.addList((refillByStatusData == null || (products = refillByStatusData.getProducts()) == null) ? CollectionsKt.emptyList() : products);
        BottomSheetRefillRequestProductDetailsBinding bottomSheetRefillRequestProductDetailsBinding7 = this.binding;
        if (bottomSheetRefillRequestProductDetailsBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            bottomSheetRefillRequestProductDetailsBinding2 = bottomSheetRefillRequestProductDetailsBinding7;
        }
        View root = bottomSheetRefillRequestProductDetailsBinding2.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    private final void setupAdapter() {
        BottomSheetRefillRequestProductDetailsBinding bottomSheetRefillRequestProductDetailsBinding = null;
        this.adapter = new GenericAdapter<>(R.layout.item_books_list, null, 2, null);
        BottomSheetRefillRequestProductDetailsBinding bottomSheetRefillRequestProductDetailsBinding2 = this.binding;
        if (bottomSheetRefillRequestProductDetailsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            bottomSheetRefillRequestProductDetailsBinding2 = null;
        }
        RecyclerView recyclerView = bottomSheetRefillRequestProductDetailsBinding2.booksListRv;
        GenericAdapter<Products> genericAdapter = this.adapter;
        if (genericAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            genericAdapter = null;
        }
        recyclerView.setAdapter(genericAdapter);
        BottomSheetRefillRequestProductDetailsBinding bottomSheetRefillRequestProductDetailsBinding3 = this.binding;
        if (bottomSheetRefillRequestProductDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            bottomSheetRefillRequestProductDetailsBinding = bottomSheetRefillRequestProductDetailsBinding3;
        }
        bottomSheetRefillRequestProductDetailsBinding.booksListRv.setLayoutManager(new LinearLayoutManager(requireContext(), 1, false));
    }

    private final void imageAdapterFun() {
        ArrayList<Products> products;
        RefillByStatusData refillByStatusData = this.refillData;
        ArrayList<Products> list = (refillByStatusData == null || (products = refillByStatusData.getProducts()) == null) ? CollectionsKt.emptyList() : products;
        this.imageAdapter = new ProductsAdapter(list);
        BottomSheetRefillRequestProductDetailsBinding bottomSheetRefillRequestProductDetailsBinding = this.binding;
        ProductsAdapter productsAdapter = null;
        if (bottomSheetRefillRequestProductDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            bottomSheetRefillRequestProductDetailsBinding = null;
        }
        bottomSheetRefillRequestProductDetailsBinding.imageRv.setLayoutManager(new GridLayoutManager(requireContext(), 4, 1, false));
        BottomSheetRefillRequestProductDetailsBinding bottomSheetRefillRequestProductDetailsBinding2 = this.binding;
        if (bottomSheetRefillRequestProductDetailsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            bottomSheetRefillRequestProductDetailsBinding2 = null;
        }
        RecyclerView recyclerView = bottomSheetRefillRequestProductDetailsBinding2.imageRv;
        ProductsAdapter productsAdapter2 = this.imageAdapter;
        if (productsAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imageAdapter");
        } else {
            productsAdapter = productsAdapter2;
        }
        recyclerView.setAdapter(productsAdapter);
    }

    @Override // androidx.fragment.app.DialogFragment
    public int getTheme() {
        return R.style.RoundedDialogTheme;
    }
}
