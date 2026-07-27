package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.content.DialogInterface;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.ingenious.androidbookmarksalesupgrade.listener.OnResultListener;
import com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener;
import com.ingenious.androidbookmarksalesupgrade.model.BookModel;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooksData;
import com.ingenious.androidbookmarksalesupgrade.model.response.BooksBySegmentData;
import com.ingenious.androidbookmarksalesupgrade.model.response.GradesSubjectsData;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CompleteVisitActivity.kt */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/activity/CompleteVisitActivity$showVisitProducts$1", "Lcom/ingenious/androidbookmarksalesupgrade/listener/RecyclerViewListener;", "onTapDelete", "", "products", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "onTapUpdateQuantity", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class CompleteVisitActivity$showVisitProducts$1 implements RecyclerViewListener {
    final /* synthetic */ CompleteVisitActivity this$0;

    CompleteVisitActivity$showVisitProducts$1(CompleteVisitActivity $receiver) {
        this.this$0 = $receiver;
    }

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
    public void onTapDelete(final Products products) {
        Intrinsics.checkNotNullParameter(products, "products");
        MaterialAlertDialogBuilder title = new MaterialAlertDialogBuilder(this.this$0).setTitle((CharSequence) "Delete Product");
        String title2 = products.getTitle();
        final CompleteVisitActivity completeVisitActivity = this.this$0;
        title.setMessage((CharSequence) ("Are you sure you want to remove " + title2 + "?")).setPositiveButton((CharSequence) "Yes", new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$showVisitProducts$1$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                CompleteVisitActivity$showVisitProducts$1.onTapDelete$lambda$0(CompleteVisitActivity.this, products, dialogInterface, i);
            }
        }).setNegativeButton((CharSequence) "Cancel", new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$showVisitProducts$1$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTapDelete$lambda$0(CompleteVisitActivity this$0, Products $products, DialogInterface dialog, int i) {
        Integer id = $products.getId();
        int intValue = id != null ? id.intValue() : 0;
        String quantity = $products.getQuantity();
        if (quantity == null) {
            quantity = "5";
        }
        this$0.deleteProduct(intValue, quantity);
        dialog.dismiss();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.listener.RecyclerViewListener
    public void onTapUpdateQuantity(Products products) {
        Intrinsics.checkNotNullParameter(products, "products");
        CompleteVisitActivity completeVisitActivity = this.this$0;
        Integer id = products.getId();
        int intValue = id != null ? id.intValue() : 0;
        String quantity = products.getQuantity();
        if (quantity == null) {
            quantity = "5";
        }
        completeVisitActivity.updateProductQuantity(intValue, quantity);
    }
}
