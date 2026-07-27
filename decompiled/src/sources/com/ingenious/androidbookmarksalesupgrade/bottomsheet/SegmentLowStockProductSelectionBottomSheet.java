package com.ingenious.androidbookmarksalesupgrade.bottomsheet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.RefillSegmentProductsAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentSegmentLowStockSelectionBottomSheetBinding;
import com.ingenious.androidbookmarksalesupgrade.listener.OnResultListener;
import com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener;
import com.ingenious.androidbookmarksalesupgrade.model.BookModel;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooksData;
import com.ingenious.androidbookmarksalesupgrade.model.response.BooksBySegmentData;
import com.ingenious.androidbookmarksalesupgrade.model.response.GradesSubjectsData;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.RefillRequestsActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SegmentLowStockProductSelectionBottomSheet.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J$\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001bH\u0002J\b\u0010\u001d\u001a\u00020\u001bH\u0002J\u0010\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\"H\u0016R\u0016\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00040\nj\b\u0012\u0004\u0012\u00020\u0004`\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/bottomsheet/SegmentLowStockProductSelectionBottomSheet;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "products", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/BookModel;", "<init>", "(Ljava/util/List;)V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentSegmentLowStockSelectionBottomSheetBinding;", "productList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "totalAmount", "", "adapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/RefillSegmentProductsAdapter;", "segmentName", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setupAdapter", "", "calculateTotal", "showSuccessDialog", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "getTheme", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class SegmentLowStockProductSelectionBottomSheet extends BottomSheetDialogFragment {
    private RefillSegmentProductsAdapter adapter;
    private FragmentSegmentLowStockSelectionBottomSheetBinding binding;
    private ArrayList<BookModel> productList;
    private final List<BookModel> products;
    private String segmentName;
    private double totalAmount;

    /* JADX WARN: Multi-variable type inference failed */
    public SegmentLowStockProductSelectionBottomSheet() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ SegmentLowStockProductSelectionBottomSheet(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }

    public SegmentLowStockProductSelectionBottomSheet(List<BookModel> list) {
        this.products = list;
        this.productList = new ArrayList<>();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.binding = FragmentSegmentLowStockSelectionBottomSheetBinding.inflate(inflater);
        FragmentSegmentLowStockSelectionBottomSheetBinding fragmentSegmentLowStockSelectionBottomSheetBinding = this.binding;
        FragmentSegmentLowStockSelectionBottomSheetBinding fragmentSegmentLowStockSelectionBottomSheetBinding2 = null;
        if (fragmentSegmentLowStockSelectionBottomSheetBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSegmentLowStockSelectionBottomSheetBinding = null;
        }
        fragmentSegmentLowStockSelectionBottomSheetBinding.totalPrice.setText("0.0");
        Bundle it = getArguments();
        if (it != null) {
            ArrayList<BookModel> parcelableArrayList = it.getParcelableArrayList("selectedBooks");
            if (parcelableArrayList == null) {
                parcelableArrayList = new ArrayList<>();
            }
            this.productList = parcelableArrayList;
            this.segmentName = it.getString("segmentName");
            Log.d("TAG", "selected products in bottomsheet: " + this.productList);
        }
        FragmentSegmentLowStockSelectionBottomSheetBinding fragmentSegmentLowStockSelectionBottomSheetBinding3 = this.binding;
        if (fragmentSegmentLowStockSelectionBottomSheetBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSegmentLowStockSelectionBottomSheetBinding3 = null;
        }
        fragmentSegmentLowStockSelectionBottomSheetBinding3.arrowBack.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.SegmentLowStockProductSelectionBottomSheet$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SegmentLowStockProductSelectionBottomSheet.this.dismiss();
            }
        });
        FragmentSegmentLowStockSelectionBottomSheetBinding fragmentSegmentLowStockSelectionBottomSheetBinding4 = this.binding;
        if (fragmentSegmentLowStockSelectionBottomSheetBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSegmentLowStockSelectionBottomSheetBinding4 = null;
        }
        fragmentSegmentLowStockSelectionBottomSheetBinding4.cancelButton.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.SegmentLowStockProductSelectionBottomSheet$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SegmentLowStockProductSelectionBottomSheet.this.dismiss();
            }
        });
        setupAdapter();
        RefillSegmentProductsAdapter refillSegmentProductsAdapter = this.adapter;
        if (refillSegmentProductsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            refillSegmentProductsAdapter = null;
        }
        refillSegmentProductsAdapter.addList(this.productList);
        FragmentSegmentLowStockSelectionBottomSheetBinding fragmentSegmentLowStockSelectionBottomSheetBinding5 = this.binding;
        if (fragmentSegmentLowStockSelectionBottomSheetBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSegmentLowStockSelectionBottomSheetBinding5 = null;
        }
        fragmentSegmentLowStockSelectionBottomSheetBinding5.confirmButton.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.SegmentLowStockProductSelectionBottomSheet$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SegmentLowStockProductSelectionBottomSheet.onCreateView$lambda$3(SegmentLowStockProductSelectionBottomSheet.this, view);
            }
        });
        FragmentSegmentLowStockSelectionBottomSheetBinding fragmentSegmentLowStockSelectionBottomSheetBinding6 = this.binding;
        if (fragmentSegmentLowStockSelectionBottomSheetBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentSegmentLowStockSelectionBottomSheetBinding2 = fragmentSegmentLowStockSelectionBottomSheetBinding6;
        }
        NestedScrollView root = fragmentSegmentLowStockSelectionBottomSheetBinding2.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$3(SegmentLowStockProductSelectionBottomSheet this$0, View it) {
        if (this$0.productList.isEmpty()) {
            Toast.makeText(this$0.requireContext(), "No products selected", 0).show();
            return;
        }
        Log.d("TAG", "Confirm clicked with products: " + this$0.productList);
        this$0.showSuccessDialog();
    }

    private final void setupAdapter() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        RefillSegmentProductsAdapter refillSegmentProductsAdapter = null;
        this.adapter = new RefillSegmentProductsAdapter(requireContext, null, new RecyclerViewListener() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.SegmentLowStockProductSelectionBottomSheet$setupAdapter$1
            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void addQuantityToCart(String quantityNo, String productId, OnResultListener listener) {
                RecyclerViewListener.DefaultImpls.addQuantityToCart(this, quantityNo, productId, listener);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onItemClick(Products products) {
                RecyclerViewListener.DefaultImpls.onItemClick(this, products);
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
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onSegmentTotalUpdated(BookModel products) {
                Intrinsics.checkNotNullParameter(products, "products");
                SegmentLowStockProductSelectionBottomSheet.this.calculateTotal();
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
            public void onTapUpdateQuantity(Products products) {
                Intrinsics.checkNotNullParameter(products, "products");
            }
        }, 2, null);
        FragmentSegmentLowStockSelectionBottomSheetBinding fragmentSegmentLowStockSelectionBottomSheetBinding = this.binding;
        if (fragmentSegmentLowStockSelectionBottomSheetBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSegmentLowStockSelectionBottomSheetBinding = null;
        }
        fragmentSegmentLowStockSelectionBottomSheetBinding.productRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), 1, false));
        FragmentSegmentLowStockSelectionBottomSheetBinding fragmentSegmentLowStockSelectionBottomSheetBinding2 = this.binding;
        if (fragmentSegmentLowStockSelectionBottomSheetBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSegmentLowStockSelectionBottomSheetBinding2 = null;
        }
        RecyclerView recyclerView = fragmentSegmentLowStockSelectionBottomSheetBinding2.productRecyclerView;
        RefillSegmentProductsAdapter refillSegmentProductsAdapter2 = this.adapter;
        if (refillSegmentProductsAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            refillSegmentProductsAdapter = refillSegmentProductsAdapter2;
        }
        recyclerView.setAdapter(refillSegmentProductsAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void calculateTotal() {
        this.totalAmount = Utils.DOUBLE_EPSILON;
        Iterable $this$forEach$iv = this.productList;
        for (Object element$iv : $this$forEach$iv) {
            BookModel product = (BookModel) element$iv;
            int quantity = product.getQuantity();
            String price = product.getPrice();
            double price2 = price != null ? Double.parseDouble(price) : 0.0d;
            this.totalAmount += quantity * price2;
        }
        FragmentSegmentLowStockSelectionBottomSheetBinding fragmentSegmentLowStockSelectionBottomSheetBinding = this.binding;
        if (fragmentSegmentLowStockSelectionBottomSheetBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSegmentLowStockSelectionBottomSheetBinding = null;
        }
        fragmentSegmentLowStockSelectionBottomSheetBinding.totalPrice.setText(String.valueOf(this.totalAmount));
    }

    private final void showSuccessDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_refill_request, (ViewGroup) null);
        final AlertDialog dialog = new AlertDialog.Builder(requireContext()).setView(dialogView).create();
        ImageView closeBtn = (ImageView) dialogView.findViewById(R.id.close_btn);
        TextView trackIdText = (TextView) dialogView.findViewById(R.id.track_id);
        TextView dateText = (TextView) dialogView.findViewById(R.id.date);
        TextView refillRequestBtn = (TextView) dialogView.findViewById(R.id.view_requests_button);
        trackIdText.setText("KHI607");
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy", Locale.getDefault());
        String currentDate = sdf.format(new Date());
        dateText.setText(currentDate);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        closeBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.SegmentLowStockProductSelectionBottomSheet$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        refillRequestBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.SegmentLowStockProductSelectionBottomSheet$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SegmentLowStockProductSelectionBottomSheet.showSuccessDialog$lambda$6(SegmentLowStockProductSelectionBottomSheet.this, dialog, view);
            }
        });
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSuccessDialog$lambda$6(SegmentLowStockProductSelectionBottomSheet this$0, AlertDialog $dialog, View it) {
        Intent intent = new Intent(this$0.requireActivity(), (Class<?>) RefillRequestsActivity.class);
        this$0.startActivity(intent);
        $dialog.dismiss();
        this$0.dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
        RefillSegmentProductsAdapter refillSegmentProductsAdapter = this.adapter;
        if (refillSegmentProductsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            refillSegmentProductsAdapter = null;
        }
        refillSegmentProductsAdapter.addList(new ArrayList<>());
    }

    @Override // androidx.fragment.app.DialogFragment
    public int getTheme() {
        return R.style.Rounded_DialogTheme;
    }
}
