package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.app.AlertDialog;
import android.content.Intent;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.savedstate.SavedStateRegistryOwner;
import com.google.firebase.messaging.Constants;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter;
import com.ingenious.androidbookmarksalesupgrade.bottomsheet.CustomerDetailBottomSheet;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentCustomerBinding;
import com.ingenious.androidbookmarksalesupgrade.model.response.Customers;
import com.ingenious.androidbookmarksalesupgrade.model.response.CustomersData;
import com.ingenious.androidbookmarksalesupgrade.model.response.CustomersListData;
import com.ingenious.androidbookmarksalesupgrade.model.response.CustomersListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.ProfileData;
import com.ingenious.androidbookmarksalesupgrade.model.response.ProfileResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.HelpScreenAct;
import com.ingenious.androidbookmarksalesupgrade.viewModel.UserViewModel;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
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

/* compiled from: CustomerFragment.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\b\u0010(\u001a\u00020#H\u0002J\b\u0010)\u001a\u00020#H\u0002J\u0010\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020\u0014H\u0002J\b\u0010,\u001a\u00020#H\u0002R.\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u0011\u001a\u0004\b\u001f\u0010 ¨\u0006-"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/CustomerFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/BaseFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentCustomerBinding;", "<init>", "()V", "bindingInflater", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "", "getBindingInflater", "()Lkotlin/jvm/functions/Function3;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "adapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/CustomersData;", "customerListFilterAdapter", "selectedPriority", "", "selectedDistance", "selectedCustomerType", "selectedAddedBy", "selectedArea", "selectedLastVisit", "userViewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/UserViewModel;", "getUserViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/UserViewModel;", "userViewModel$delegate", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setupAdapter", "setupCustomerListAdapter", "openCustomerBottomSheet", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "exportDialog", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class CustomerFragment extends BaseFragment<FragmentCustomerBinding> {
    private GenericAdapter<CustomersData> adapter;
    private GenericAdapter<CustomersData> customerListFilterAdapter;
    private String selectedAddedBy;
    private String selectedArea;
    private String selectedCustomerType;
    private String selectedDistance;
    private String selectedLastVisit;
    private String selectedPriority;

    /* renamed from: userViewModel$delegate, reason: from kotlin metadata */
    private final Lazy userViewModel;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public CustomerFragment() {
        final CustomerFragment $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$special$$inlined$viewModel$default$1
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
        final Function0 ownerProducer$iv$iv = new Function0<ViewModelStoreOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$special$$inlined$viewModel$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return ((ViewModelOwner) Function0.this.invoke()).getStoreOwner();
            }
        };
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$special$$inlined$viewModel$default$3
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default$iv, Reflection.getOrCreateKotlinClass(VisitViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$special$$inlined$viewModel$default$4
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
        final CustomerFragment $this$viewModel_u24default$iv2 = this;
        final Qualifier qualifier$iv2 = null;
        final Function0 owner$iv2 = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$special$$inlined$viewModel$default$5
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
        final Function0 parameters$iv2 = null;
        final Scope scope$iv2 = AndroidKoinScopeExtKt.getKoinScope($this$viewModel_u24default$iv2);
        final Function0 ownerProducer$iv$iv2 = new Function0<ViewModelStoreOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$special$$inlined$viewModel$default$6
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return ((ViewModelOwner) Function0.this.invoke()).getStoreOwner();
            }
        };
        Function0 factoryProducer$iv$iv2 = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$special$$inlined$viewModel$default$7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                Function0 owner$iv3 = Function0.this;
                Qualifier qualifier$iv3 = qualifier$iv2;
                Function0 parameters$iv3 = parameters$iv2;
                Scope scope$iv3 = scope$iv2;
                ViewModelOwner ownerValue$iv = (ViewModelOwner) owner$iv3.invoke();
                ViewModelParameter viewModelParameters$iv = new ViewModelParameter(Reflection.getOrCreateKotlinClass(UserViewModel.class), qualifier$iv3, null, parameters$iv3, ownerValue$iv.getStoreOwner(), ownerValue$iv.getStateRegistry());
                return ViewModelResolverKt.pickFactory(scope$iv3, viewModelParameters$iv);
            }
        };
        this.userViewModel = FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default$iv2, Reflection.getOrCreateKotlinClass(UserViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$special$$inlined$viewModel$default$8
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
        }, factoryProducer$iv$iv2);
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment
    public Function3<LayoutInflater, ViewGroup, Boolean, FragmentCustomerBinding> getBindingInflater() {
        return CustomerFragment$bindingInflater$1.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VisitViewModel getViewModel() {
        return (VisitViewModel) this.viewModel.getValue();
    }

    private final UserViewModel getUserViewModel() {
        return (UserViewModel) this.userViewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        getUserViewModel().profile();
        getUserViewModel().getProfileResponse().observe(getViewLifecycleOwner(), new CustomerFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onViewCreated$lambda$0;
                onViewCreated$lambda$0 = CustomerFragment.onViewCreated$lambda$0((ApiResponseCallback) obj);
                return onViewCreated$lambda$0;
            }
        }));
        VisitViewModel.customersList$default(getViewModel(), "all", null, null, null, null, 30, null);
        setupAdapter();
        setupCustomerListAdapter();
        getViewModel().getCustomersListResponse().observe(getViewLifecycleOwner(), new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CustomerFragment.onViewCreated$lambda$6(CustomerFragment.this, (ApiResponseCallback) obj);
            }
        });
        getBinding().allTypeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CustomerFragment.onViewCreated$lambda$7(CustomerFragment.this, view2);
            }
        });
        getBinding().schoolTypeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CustomerFragment.onViewCreated$lambda$8(CustomerFragment.this, view2);
            }
        });
        getBinding().shopTypeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CustomerFragment.onViewCreated$lambda$9(CustomerFragment.this, view2);
            }
        });
        getBinding().setListener(new CustomerFragment$onViewCreated$6(this));
        getBinding().inventorySearchEt.addTextChangedListener(new TextWatcher() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$onViewCreated$7
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                GenericAdapter genericAdapter;
                genericAdapter = CustomerFragment.this.adapter;
                if (genericAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    genericAdapter = null;
                }
                genericAdapter.filterCustomers(String.valueOf(s));
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        getBinding().cancelTv.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CustomerFragment.onViewCreated$lambda$10(CustomerFragment.this, view2);
            }
        });
        getBinding().performanceMenu.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CustomerFragment.this.exportDialog();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit onViewCreated$lambda$0(ApiResponseCallback response) {
        if (response instanceof ApiResponseCallback.Error) {
            Log.e("CustomerFragment", "Profile API Error: " + ((ApiResponseCallback.Error) response).getMessage());
        } else if (response instanceof ApiResponseCallback.Loading) {
            Log.d("CustomerFragment", "Profile API Loading...");
        } else {
            if (!(response instanceof ApiResponseCallback.Success)) {
                throw new NoWhenBranchMatchedException();
            }
            ProfileResponse profileResponse = (ProfileResponse) ((ApiResponseCallback.Success) response).getData();
            ProfileData profileData = profileResponse != null ? profileResponse.getData() : null;
            Log.d("CustomerFragment", "Profile API Success: " + profileData);
            Log.d("CustomerFragment", "Profile Name: " + (profileData != null ? profileData.getName() : null));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onViewCreated$lambda$6(CustomerFragment customerFragment, ApiResponseCallback apiResponseCallback) {
        CustomersListResponse customersListResponse;
        CustomersListData data;
        Customers customers;
        ArrayList<CustomersData> data2;
        GenericAdapter<CustomersData> genericAdapter;
        ProfileResponse profileResponse;
        ProfileData data3;
        if (apiResponseCallback == null || !(apiResponseCallback instanceof ApiResponseCallback.Success) || (customersListResponse = (CustomersListResponse) ((ApiResponseCallback.Success) apiResponseCallback).getData()) == null || (data = customersListResponse.getData()) == null || (customers = data.getCustomers()) == null || (data2 = customers.getData()) == null) {
            return;
        }
        Iterator<T> it = data2.iterator();
        while (true) {
            genericAdapter = null;
            r8 = null;
            r8 = null;
            r8 = null;
            String str = null;
            if (!it.hasNext()) {
                break;
            }
            CustomersData customersData = (CustomersData) it.next();
            ApiResponseCallback<ProfileResponse> value = customerFragment.getUserViewModel().getProfileResponse().getValue();
            if (value != null) {
                if (!Boolean.valueOf(value instanceof ApiResponseCallback.Success).booleanValue()) {
                    value = null;
                }
                if (value != null && (profileResponse = (ProfileResponse) ((ApiResponseCallback.Success) value).getData()) != null && (data3 = profileResponse.getData()) != null) {
                    str = data3.getName();
                }
            }
            customersData.setProfileName(str);
        }
        Log.i("CUSTOMER", "onViewCreated: " + ((ApiResponseCallback.Success) apiResponseCallback).getData());
        GenericAdapter<CustomersData> genericAdapter2 = customerFragment.adapter;
        if (genericAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            genericAdapter2 = null;
        }
        genericAdapter2.addListForCustomer(data2);
        GenericAdapter<CustomersData> genericAdapter3 = customerFragment.customerListFilterAdapter;
        if (genericAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customerListFilterAdapter");
        } else {
            genericAdapter = genericAdapter3;
        }
        genericAdapter.addListForCustomer(data2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$7(CustomerFragment this$0, View it) {
        VisitViewModel.customersList$default(this$0.getViewModel(), "all", null, null, null, null, 30, null);
        this$0.getBinding().customerTypeAll.setTextColor(this$0.getResources().getColor(R.color.app_color));
        this$0.getBinding().customerTypeBookshop.setTextColor(this$0.getResources().getColor(com.denzcoskun.imageslider.R.color.grey_font));
        this$0.getBinding().customerTypeSchool.setTextColor(this$0.getResources().getColor(com.denzcoskun.imageslider.R.color.grey_font));
        DrawableCompat.setTint(this$0.getBinding().customerTypeAll.getCompoundDrawables()[0], ContextCompat.getColor(this$0.requireContext(), R.color.app_color));
        DrawableCompat.setTint(this$0.getBinding().customerTypeBookshop.getCompoundDrawables()[0], ContextCompat.getColor(this$0.requireContext(), com.denzcoskun.imageslider.R.color.grey_font));
        DrawableCompat.setTint(this$0.getBinding().customerTypeSchool.getCompoundDrawables()[0], ContextCompat.getColor(this$0.requireContext(), com.denzcoskun.imageslider.R.color.grey_font));
        this$0.getBinding().allTypeLayout.setBackgroundResource(R.drawable.selected_button_background);
        this$0.getBinding().shopTypeLayout.setBackgroundResource(R.drawable.unselected_button_background);
        this$0.getBinding().schoolTypeLayout.setBackgroundResource(R.drawable.unselected_button_background);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$8(CustomerFragment this$0, View it) {
        VisitViewModel.customersList$default(this$0.getViewModel(), "school", null, null, null, null, 30, null);
        this$0.getBinding().customerTypeSchool.setTextColor(this$0.getResources().getColor(R.color.app_color));
        this$0.getBinding().customerTypeBookshop.setTextColor(this$0.getResources().getColor(com.denzcoskun.imageslider.R.color.grey_font));
        this$0.getBinding().customerTypeAll.setTextColor(this$0.getResources().getColor(com.denzcoskun.imageslider.R.color.grey_font));
        DrawableCompat.setTint(this$0.getBinding().customerTypeSchool.getCompoundDrawables()[0], ContextCompat.getColor(this$0.requireContext(), R.color.app_color));
        DrawableCompat.setTint(this$0.getBinding().customerTypeBookshop.getCompoundDrawables()[0], ContextCompat.getColor(this$0.requireContext(), com.denzcoskun.imageslider.R.color.grey_font));
        DrawableCompat.setTint(this$0.getBinding().customerTypeAll.getCompoundDrawables()[0], ContextCompat.getColor(this$0.requireContext(), com.denzcoskun.imageslider.R.color.grey_font));
        this$0.getBinding().schoolTypeLayout.setBackgroundResource(R.drawable.selected_button_background);
        this$0.getBinding().shopTypeLayout.setBackgroundResource(R.drawable.unselected_button_background);
        this$0.getBinding().allTypeLayout.setBackgroundResource(R.drawable.unselected_button_background);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$9(CustomerFragment this$0, View it) {
        VisitViewModel.customersList$default(this$0.getViewModel(), "bookshop", null, null, null, null, 30, null);
        this$0.getBinding().customerTypeBookshop.setTextColor(this$0.getResources().getColor(R.color.app_color));
        this$0.getBinding().customerTypeSchool.setTextColor(this$0.getResources().getColor(com.denzcoskun.imageslider.R.color.grey_font));
        this$0.getBinding().customerTypeAll.setTextColor(this$0.getResources().getColor(com.denzcoskun.imageslider.R.color.grey_font));
        DrawableCompat.setTint(this$0.getBinding().customerTypeSchool.getCompoundDrawables()[0], ContextCompat.getColor(this$0.requireContext(), com.denzcoskun.imageslider.R.color.grey_font));
        DrawableCompat.setTint(this$0.getBinding().customerTypeAll.getCompoundDrawables()[0], ContextCompat.getColor(this$0.requireContext(), com.denzcoskun.imageslider.R.color.grey_font));
        DrawableCompat.setTint(this$0.getBinding().customerTypeBookshop.getCompoundDrawables()[0], ContextCompat.getColor(this$0.requireContext(), R.color.app_color));
        this$0.getBinding().shopTypeLayout.setBackgroundResource(R.drawable.selected_button_background);
        this$0.getBinding().schoolTypeLayout.setBackgroundResource(R.drawable.unselected_button_background);
        this$0.getBinding().allTypeLayout.setBackgroundResource(R.drawable.unselected_button_background);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$10(CustomerFragment this$0, View it) {
        Editable text = this$0.getBinding().inventorySearchEt.getText();
        if (text != null) {
            text.clear();
        }
    }

    private final void setupAdapter() {
        this.adapter = new GenericAdapter<>(R.layout.item_customers_list, new GenericAdapter.OnItemClickListener<CustomersData>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$setupAdapter$1
            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onCall(int visitId) {
                GenericAdapter.OnItemClickListener.DefaultImpls.onCall(this, visitId);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onItemClickTwo(CustomersData item) {
                GenericAdapter.OnItemClickListener.DefaultImpls.onItemClickTwo(this, item);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onSelectionChanged(List<? extends CustomersData> list) {
                GenericAdapter.OnItemClickListener.DefaultImpls.onSelectionChanged(this, list);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onItemClick(CustomersData item) {
                Intrinsics.checkNotNullParameter(item, "item");
                CustomerFragment.this.openCustomerBottomSheet(item);
            }
        });
        RecyclerView recyclerView = getBinding().customerListRv;
        GenericAdapter<CustomersData> genericAdapter = this.adapter;
        if (genericAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            genericAdapter = null;
        }
        recyclerView.setAdapter(genericAdapter);
        getBinding().customerListRv.setLayoutManager(new LinearLayoutManager(requireContext(), 1, false));
    }

    private final void setupCustomerListAdapter() {
        this.customerListFilterAdapter = new GenericAdapter<>(R.layout.item_customer_list_filter, new GenericAdapter.OnItemClickListener<CustomersData>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$setupCustomerListAdapter$1
            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onCall(int visitId) {
                GenericAdapter.OnItemClickListener.DefaultImpls.onCall(this, visitId);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onItemClickTwo(CustomersData item) {
                GenericAdapter.OnItemClickListener.DefaultImpls.onItemClickTwo(this, item);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onSelectionChanged(List<? extends CustomersData> list) {
                GenericAdapter.OnItemClickListener.DefaultImpls.onSelectionChanged(this, list);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter.OnItemClickListener
            public void onItemClick(CustomersData item) {
                Intrinsics.checkNotNullParameter(item, "item");
                CustomerFragment.this.openCustomerBottomSheet(item);
            }
        });
        RecyclerView recyclerView = getBinding().customersListFilterRv;
        GenericAdapter<CustomersData> genericAdapter = this.customerListFilterAdapter;
        if (genericAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customerListFilterAdapter");
            genericAdapter = null;
        }
        recyclerView.setAdapter(genericAdapter);
        getBinding().customersListFilterRv.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openCustomerBottomSheet(CustomersData data) {
        CustomerDetailBottomSheet bottomSheet = new CustomerDetailBottomSheet();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, data);
        bottomSheet.setArguments(bundle);
        bottomSheet.show(getParentFragmentManager(), bottomSheet.getTag());
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
        dateRange.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomerFragment.exportDialog$lambda$12(CustomerFragment.this, view);
            }
        });
        crossBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CustomerFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void exportDialog$lambda$12(CustomerFragment this$0, View it) {
        Intent intent = new Intent(this$0.requireContext(), (Class<?>) HelpScreenAct.class);
        this$0.startActivity(intent);
    }
}
