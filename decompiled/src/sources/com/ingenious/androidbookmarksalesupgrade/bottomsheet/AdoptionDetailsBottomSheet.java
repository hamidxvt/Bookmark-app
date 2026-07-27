package com.ingenious.androidbookmarksalesupgrade.bottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
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
import com.ingenious.androidbookmarksalesupgrade.databinding.BottomSheetAdoptionDetailsFragmentBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.DialogExtKt;
import com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooks;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionDetailsData;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionDetailsResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.viewModel.MainViewModel;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
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

/* compiled from: AdoptionDetailsBottomSheet.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u001fH\u0002J\b\u0010!\u001a\u00020\"H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006#"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/bottomsheet/AdoptionDetailsBottomSheet;", "Landroidx/fragment/app/DialogFragment;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/BottomSheetAdoptionDetailsFragmentBinding;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/MainViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "quantityAdapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionBooks;", "imageAdapter", "adoptionId", "", "getAdoptionId", "()Ljava/lang/String;", "setAdoptionId", "(Ljava/lang/String;)V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setupQuantityAdapter", "", "setupImageAdapter", "getTheme", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class AdoptionDetailsBottomSheet extends DialogFragment {
    private String adoptionId;
    private BottomSheetAdoptionDetailsFragmentBinding binding;
    private GenericAdapter<AdoptionBooks> imageAdapter;
    private GenericAdapter<AdoptionBooks> quantityAdapter;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public AdoptionDetailsBottomSheet() {
        final AdoptionDetailsBottomSheet $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.AdoptionDetailsBottomSheet$special$$inlined$viewModel$default$1
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
        final Function0 ownerProducer$iv$iv = new Function0<ViewModelStoreOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.AdoptionDetailsBottomSheet$special$$inlined$viewModel$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return ((ViewModelOwner) Function0.this.invoke()).getStoreOwner();
            }
        };
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.AdoptionDetailsBottomSheet$special$$inlined$viewModel$default$3
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default$iv, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.AdoptionDetailsBottomSheet$special$$inlined$viewModel$default$4
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
        this.adoptionId = "";
    }

    private final MainViewModel getViewModel() {
        return (MainViewModel) this.viewModel.getValue();
    }

    public final String getAdoptionId() {
        return this.adoptionId;
    }

    public final void setAdoptionId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.adoptionId = str;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.binding = (BottomSheetAdoptionDetailsFragmentBinding) DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_adoption_details_fragment, container, false);
        Bundle it = getArguments();
        if (it != null) {
            this.adoptionId = String.valueOf(it.getString("adoptionId"));
        }
        BottomSheetAdoptionDetailsFragmentBinding bottomSheetAdoptionDetailsFragmentBinding = this.binding;
        BottomSheetAdoptionDetailsFragmentBinding bottomSheetAdoptionDetailsFragmentBinding2 = null;
        if (bottomSheetAdoptionDetailsFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            bottomSheetAdoptionDetailsFragmentBinding = null;
        }
        bottomSheetAdoptionDetailsFragmentBinding.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.AdoptionDetailsBottomSheet$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdoptionDetailsBottomSheet.this.dismiss();
            }
        });
        getViewModel().adoptionDetails(Integer.parseInt(this.adoptionId));
        setupQuantityAdapter();
        setupImageAdapter();
        getViewModel().getAdoptionDetails().observe(getViewLifecycleOwner(), new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.AdoptionDetailsBottomSheet$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AdoptionDetailsBottomSheet.onCreateView$lambda$4(AdoptionDetailsBottomSheet.this, (ApiResponseCallback) obj);
            }
        });
        BottomSheetAdoptionDetailsFragmentBinding bottomSheetAdoptionDetailsFragmentBinding3 = this.binding;
        if (bottomSheetAdoptionDetailsFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            bottomSheetAdoptionDetailsFragmentBinding2 = bottomSheetAdoptionDetailsFragmentBinding3;
        }
        return bottomSheetAdoptionDetailsFragmentBinding2.getRoot();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onCreateView$lambda$4(AdoptionDetailsBottomSheet this$0, ApiResponseCallback it) {
        ArrayList<AdoptionBooks> books;
        ArrayList<AdoptionBooks> books2;
        if (it != null) {
            GenericAdapter<AdoptionBooks> genericAdapter = null;
            if (it instanceof ApiResponseCallback.Error) {
                String message = ((ApiResponseCallback.Error) it).getMessage();
                Intrinsics.checkNotNull(message);
                DialogExtKt.showMaterialDialog$default(this$0, message, (DialogListeners) null, 2, (Object) null);
                return;
            }
            if (!(it instanceof ApiResponseCallback.Loading)) {
                if (!(it instanceof ApiResponseCallback.Success)) {
                    throw new NoWhenBranchMatchedException();
                }
                AdoptionDetailsResponse data = (AdoptionDetailsResponse) ((ApiResponseCallback.Success) it).getData();
                if (data != null) {
                    Boolean success = data.getSuccess();
                    Intrinsics.checkNotNull(success);
                    if (success.booleanValue()) {
                        BottomSheetAdoptionDetailsFragmentBinding bottomSheetAdoptionDetailsFragmentBinding = this$0.binding;
                        if (bottomSheetAdoptionDetailsFragmentBinding == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            bottomSheetAdoptionDetailsFragmentBinding = null;
                        }
                        bottomSheetAdoptionDetailsFragmentBinding.setItem(data.getData());
                        GenericAdapter<AdoptionBooks> genericAdapter2 = this$0.quantityAdapter;
                        if (genericAdapter2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("quantityAdapter");
                            genericAdapter2 = null;
                        }
                        AdoptionDetailsData data2 = data.getData();
                        genericAdapter2.addList((data2 == null || (books2 = data2.getBooks()) == null) ? CollectionsKt.emptyList() : books2);
                        GenericAdapter<AdoptionBooks> genericAdapter3 = this$0.imageAdapter;
                        if (genericAdapter3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("imageAdapter");
                        } else {
                            genericAdapter = genericAdapter3;
                        }
                        AdoptionDetailsData data3 = data.getData();
                        genericAdapter.addList((data3 == null || (books = data3.getBooks()) == null) ? CollectionsKt.emptyList() : books);
                        return;
                    }
                    String message2 = data.getMessage();
                    Intrinsics.checkNotNull(message2);
                    DialogExtKt.showMaterialDialog$default(this$0, message2, (DialogListeners) null, 2, (Object) null);
                }
            }
        }
    }

    private final void setupQuantityAdapter() {
        BottomSheetAdoptionDetailsFragmentBinding bottomSheetAdoptionDetailsFragmentBinding = null;
        this.quantityAdapter = new GenericAdapter<>(R.layout.item_adoption_quantities, null, 2, null);
        BottomSheetAdoptionDetailsFragmentBinding bottomSheetAdoptionDetailsFragmentBinding2 = this.binding;
        if (bottomSheetAdoptionDetailsFragmentBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            bottomSheetAdoptionDetailsFragmentBinding2 = null;
        }
        RecyclerView recyclerView = bottomSheetAdoptionDetailsFragmentBinding2.quantityRv;
        GenericAdapter<AdoptionBooks> genericAdapter = this.quantityAdapter;
        if (genericAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("quantityAdapter");
            genericAdapter = null;
        }
        recyclerView.setAdapter(genericAdapter);
        BottomSheetAdoptionDetailsFragmentBinding bottomSheetAdoptionDetailsFragmentBinding3 = this.binding;
        if (bottomSheetAdoptionDetailsFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            bottomSheetAdoptionDetailsFragmentBinding = bottomSheetAdoptionDetailsFragmentBinding3;
        }
        bottomSheetAdoptionDetailsFragmentBinding.quantityRv.setLayoutManager(new LinearLayoutManager(requireContext(), 1, false));
    }

    private final void setupImageAdapter() {
        BottomSheetAdoptionDetailsFragmentBinding bottomSheetAdoptionDetailsFragmentBinding = null;
        this.imageAdapter = new GenericAdapter<>(R.layout.item_adoption_images, null, 2, null);
        BottomSheetAdoptionDetailsFragmentBinding bottomSheetAdoptionDetailsFragmentBinding2 = this.binding;
        if (bottomSheetAdoptionDetailsFragmentBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            bottomSheetAdoptionDetailsFragmentBinding2 = null;
        }
        RecyclerView recyclerView = bottomSheetAdoptionDetailsFragmentBinding2.rvBookImages;
        GenericAdapter<AdoptionBooks> genericAdapter = this.imageAdapter;
        if (genericAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imageAdapter");
            genericAdapter = null;
        }
        recyclerView.setAdapter(genericAdapter);
        BottomSheetAdoptionDetailsFragmentBinding bottomSheetAdoptionDetailsFragmentBinding3 = this.binding;
        if (bottomSheetAdoptionDetailsFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            bottomSheetAdoptionDetailsFragmentBinding = bottomSheetAdoptionDetailsFragmentBinding3;
        }
        bottomSheetAdoptionDetailsFragmentBinding.rvBookImages.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
    }

    @Override // androidx.fragment.app.DialogFragment
    public int getTheme() {
        return R.style.RoundedDialogTheme;
    }
}
