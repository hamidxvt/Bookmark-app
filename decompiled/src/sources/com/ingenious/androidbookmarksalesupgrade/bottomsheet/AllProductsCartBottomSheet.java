package com.ingenious.androidbookmarksalesupgrade.bottomsheet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.common.net.HttpHeaders;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.AllProductsSelectedCartAdapter2;
import com.ingenious.androidbookmarksalesupgrade.databinding.BottomSheetAllProductsCartFragmentBinding;
import com.ingenious.androidbookmarksalesupgrade.listener.OnResultListener;
import com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener;
import com.ingenious.androidbookmarksalesupgrade.model.BookModel;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooksData;
import com.ingenious.androidbookmarksalesupgrade.model.response.BooksBySegmentData;
import com.ingenious.androidbookmarksalesupgrade.model.response.GradesSubjectsData;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import com.ingenious.androidbookmarksalesupgrade.utils.AppToast;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* compiled from: AllProductsCartBottomSheet.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\"\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0019H\u0002J\n\u0010\u001a\u001a\u0004\u0018\u00010\u0017H\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/bottomsheet/AllProductsCartBottomSheet;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/BottomSheetAllProductsCartFragmentBinding;", "adapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/AllProductsSelectedCartAdapter2;", "productList", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setupAdapter", "", "addProductsToVisit", "visitId", "", "products", "", "getToken", "getTheme", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class AllProductsCartBottomSheet extends BottomSheetDialogFragment {
    private AllProductsSelectedCartAdapter2 adapter;
    private BottomSheetAllProductsCartFragmentBinding binding;
    private List<Products> productList = new ArrayList();

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.binding = (BottomSheetAllProductsCartFragmentBinding) DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_all_products_cart_fragment, container, false);
        Bundle it = getArguments();
        if (it != null) {
            ArrayList parcelableArrayList = it.getParcelableArrayList("selectedProducts");
            if (parcelableArrayList == null) {
                parcelableArrayList = new ArrayList();
            }
            this.productList = parcelableArrayList;
            Log.d("TAG", "Selected products in bottomsheet: " + this.productList);
        }
        BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding = this.binding;
        BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding2 = null;
        if (bottomSheetAllProductsCartFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            bottomSheetAllProductsCartFragmentBinding = null;
        }
        bottomSheetAllProductsCartFragmentBinding.crossBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.AllProductsCartBottomSheet$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsCartBottomSheet.this.dismiss();
            }
        });
        setupAdapter();
        BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding3 = this.binding;
        if (bottomSheetAllProductsCartFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            bottomSheetAllProductsCartFragmentBinding3 = null;
        }
        bottomSheetAllProductsCartFragmentBinding3.addToVisitBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.AllProductsCartBottomSheet$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllProductsCartBottomSheet.onCreateView$lambda$2(AllProductsCartBottomSheet.this, view);
            }
        });
        BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding4 = this.binding;
        if (bottomSheetAllProductsCartFragmentBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            bottomSheetAllProductsCartFragmentBinding2 = bottomSheetAllProductsCartFragmentBinding4;
        }
        View root = bottomSheetAllProductsCartFragmentBinding2.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$2(AllProductsCartBottomSheet this$0, View it) {
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        String visitId = ExtensionKt.getVisitId(requireContext);
        if (visitId.length() == 0) {
            AppToast.INSTANCE.showToast("Visit ID is missing");
            return;
        }
        BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding = this$0.binding;
        BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding2 = null;
        if (bottomSheetAllProductsCartFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            bottomSheetAllProductsCartFragmentBinding = null;
        }
        bottomSheetAllProductsCartFragmentBinding.addToVisitBtn.setVisibility(8);
        BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding3 = this$0.binding;
        if (bottomSheetAllProductsCartFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            bottomSheetAllProductsCartFragmentBinding2 = bottomSheetAllProductsCartFragmentBinding3;
        }
        bottomSheetAllProductsCartFragmentBinding2.addingProductsBar.setVisibility(0);
        this$0.addProductsToVisit(visitId, this$0.productList);
    }

    private final void setupAdapter() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        this.adapter = new AllProductsSelectedCartAdapter2(requireContext, this.productList, new RecyclerViewListener() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.AllProductsCartBottomSheet$setupAdapter$1
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
            public void onTapDelete(Products products) {
                List list;
                AllProductsSelectedCartAdapter2 allProductsSelectedCartAdapter2;
                Intrinsics.checkNotNullParameter(products, "products");
                list = AllProductsCartBottomSheet.this.productList;
                list.remove(products);
                allProductsSelectedCartAdapter2 = AllProductsCartBottomSheet.this.adapter;
                if (allProductsSelectedCartAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    allProductsSelectedCartAdapter2 = null;
                }
                allProductsSelectedCartAdapter2.removeItem(products);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onTapUpdateQuantity(Products products) {
                Intrinsics.checkNotNullParameter(products, "products");
            }
        });
        BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding = this.binding;
        AllProductsSelectedCartAdapter2 allProductsSelectedCartAdapter2 = null;
        if (bottomSheetAllProductsCartFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            bottomSheetAllProductsCartFragmentBinding = null;
        }
        bottomSheetAllProductsCartFragmentBinding.selectedProductsRv.setLayoutManager(new LinearLayoutManager(requireContext(), 1, false));
        BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding2 = this.binding;
        if (bottomSheetAllProductsCartFragmentBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            bottomSheetAllProductsCartFragmentBinding2 = null;
        }
        RecyclerView recyclerView = bottomSheetAllProductsCartFragmentBinding2.selectedProductsRv;
        AllProductsSelectedCartAdapter2 allProductsSelectedCartAdapter22 = this.adapter;
        if (allProductsSelectedCartAdapter22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            allProductsSelectedCartAdapter2 = allProductsSelectedCartAdapter22;
        }
        recyclerView.setAdapter(allProductsSelectedCartAdapter2);
    }

    private final void addProductsToVisit(String visitId, List<Products> products) {
        String quantity;
        String str = visitId;
        int i = 1;
        int i2 = 0;
        BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding = null;
        if (str == null || str.length() == 0) {
            AppToast.INSTANCE.showToast("Visit ID is missing");
            BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding2 = this.binding;
            if (bottomSheetAllProductsCartFragmentBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                bottomSheetAllProductsCartFragmentBinding2 = null;
            }
            bottomSheetAllProductsCartFragmentBinding2.addToVisitBtn.setVisibility(0);
            BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding3 = this.binding;
            if (bottomSheetAllProductsCartFragmentBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                bottomSheetAllProductsCartFragmentBinding = bottomSheetAllProductsCartFragmentBinding3;
            }
            bottomSheetAllProductsCartFragmentBinding.addingProductsBar.setVisibility(8);
            return;
        }
        List<Products> list = products;
        if (list == null || list.isEmpty()) {
            AppToast.INSTANCE.showToast("No products selected");
            BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding4 = this.binding;
            if (bottomSheetAllProductsCartFragmentBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                bottomSheetAllProductsCartFragmentBinding4 = null;
            }
            bottomSheetAllProductsCartFragmentBinding4.addToVisitBtn.setVisibility(0);
            BottomSheetAllProductsCartFragmentBinding bottomSheetAllProductsCartFragmentBinding5 = this.binding;
            if (bottomSheetAllProductsCartFragmentBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                bottomSheetAllProductsCartFragmentBinding = bottomSheetAllProductsCartFragmentBinding5;
            }
            bottomSheetAllProductsCartFragmentBinding.addingProductsBar.setVisibility(8);
            return;
        }
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBodyBuilder = new FormBody.Builder(null, 1, null).add("visitId", visitId);
        List<Products> $this$forEachIndexed$iv = products;
        int index$iv = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv2 = index$iv + 1;
            if (index$iv < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Products product = (Products) item$iv;
            Integer id = product.getId();
            int productId = id != null ? id.intValue() : i2;
            String quantity2 = product.getQuantity();
            if (((quantity2 == null || quantity2.length() == 0) ? i : i2) != 0 || Intrinsics.areEqual(product.getQuantity(), "0")) {
                quantity = "5";
            } else {
                quantity = product.getQuantity();
            }
            formBodyBuilder.add("products[" + index$iv + "][product_id]", String.valueOf(productId));
            formBodyBuilder.add("products[" + index$iv + "][quantity]", String.valueOf(quantity));
            Log.d("API_FORM", "products[" + index$iv + "][product_id]=" + productId);
            Log.d("API_FORM", "products[" + index$iv + "][quantity]=" + quantity);
            index$iv = index$iv2;
            i = 1;
            i2 = 0;
        }
        Request.Builder addHeader = new Request.Builder().url("https://staging.bookmark.services/api/visit/add-product").addHeader(HttpHeaders.ACCEPT, "application/json");
        String token = getToken();
        if (token == null) {
            token = "";
        }
        Request request = addHeader.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token).post(formBodyBuilder.build()).build();
        client.newCall(request).enqueue(new AllProductsCartBottomSheet$addProductsToVisit$2(this));
    }

    private final String getToken() {
        SharedPreferences sharedPref = requireActivity().getSharedPreferences("BookmarkApp", 0);
        return sharedPref.getString("AUTH_TOKEN", null);
    }

    @Override // androidx.fragment.app.DialogFragment
    public int getTheme() {
        return R.style.TransparentBottomSheetTheme;
    }
}
