package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.content.DialogInterface;
import android.graphics.Canvas;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.AllProductsSelectedCartAdapter;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CompleteVisitActivity.kt */
@Metadata(d1 = {"\u0000;\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J@\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0003H\u0016¨\u0006\u0015"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/activity/CompleteVisitActivity$itemTouchHelperCallback$1", "Landroidx/recyclerview/widget/ItemTouchHelper$SimpleCallback;", "onMove", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", TypedValues.AttributesType.S_TARGET, "onSwiped", "", "direction", "", "onChildDraw", "c", "Landroid/graphics/Canvas;", "dX", "", "dY", "actionState", "isCurrentlyActive", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class CompleteVisitActivity$itemTouchHelperCallback$1 extends ItemTouchHelper.SimpleCallback {
    final /* synthetic */ CompleteVisitActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CompleteVisitActivity$itemTouchHelperCallback$1(CompleteVisitActivity $receiver) {
        super(0, 4);
        this.this$0 = $receiver;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(target, "target");
        return false;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        final int position = viewHolder.getAdapterPosition();
        AllProductsSelectedCartAdapter adapter = this.this$0.getAdapter();
        Intrinsics.checkNotNull(adapter);
        final Products product = adapter.getItem(position);
        MaterialAlertDialogBuilder title = new MaterialAlertDialogBuilder(this.this$0).setTitle((CharSequence) "Delete Product");
        String productName = product.getProductName();
        final CompleteVisitActivity completeVisitActivity = this.this$0;
        final CompleteVisitActivity completeVisitActivity2 = this.this$0;
        title.setMessage((CharSequence) ("Are you sure you want to remove " + productName + "?")).setPositiveButton((CharSequence) "Yes", new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$itemTouchHelperCallback$1$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                CompleteVisitActivity$itemTouchHelperCallback$1.onSwiped$lambda$0(CompleteVisitActivity.this, product, dialogInterface, i);
            }
        }).setNegativeButton((CharSequence) "Cancel", new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$itemTouchHelperCallback$1$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                CompleteVisitActivity$itemTouchHelperCallback$1.onSwiped$lambda$1(CompleteVisitActivity.this, position, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onSwiped$lambda$0(CompleteVisitActivity this$0, Products $product, DialogInterface dialog, int i) {
        Integer id = $product.getId();
        int intValue = id != null ? id.intValue() : 0;
        String quantity = $product.getQuantity();
        if (quantity == null) {
            quantity = "1";
        }
        this$0.deleteProduct(intValue, quantity);
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onSwiped$lambda$1(CompleteVisitActivity this$0, int $position, DialogInterface dialog, int i) {
        dialog.dismiss();
        AllProductsSelectedCartAdapter adapter = this$0.getAdapter();
        Intrinsics.checkNotNull(adapter);
        adapter.notifyItemChanged($position);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        View itemView = viewHolder.itemView;
        Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
        View deleteLayout = itemView.findViewById(R.id.delete_product_iv);
        if (dX < 0.0f) {
            deleteLayout.setVisibility(0);
            deleteLayout.setTranslationX(dX);
        } else {
            deleteLayout.setVisibility(8);
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
