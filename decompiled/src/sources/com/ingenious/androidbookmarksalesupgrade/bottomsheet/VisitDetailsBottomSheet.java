package com.ingenious.androidbookmarksalesupgrade.bottomsheet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.VisitImagesAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.BottomSheetVisitDetailsBinding;
import com.ingenious.androidbookmarksalesupgrade.model.response.ApprovedVisitsLists;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
import org.json.JSONArray;

/* compiled from: VisitDetailsBottomSheet.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J$\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u001a\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00102\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u0017\u001a\u00020\fH\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u000e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u0019R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/bottomsheet/VisitDetailsBottomSheet;", "Landroidx/fragment/app/DialogFragment;", "<init>", "()V", "_binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/BottomSheetVisitDetailsBinding;", "binding", "getBinding", "()Lcom/ingenious/androidbookmarksalesupgrade/databinding/BottomSheetVisitDetailsBinding;", "visitData", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ApprovedVisitsLists;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "onDestroyView", "getTheme", "", "openPdfExternally", "pdfUrl", "", "generateInvoiceFromItem", "itemId", "Companion", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class VisitDetailsBottomSheet extends DialogFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private BottomSheetVisitDetailsBinding _binding;
    private ApprovedVisitsLists visitData;

    private final BottomSheetVisitDetailsBinding getBinding() {
        BottomSheetVisitDetailsBinding bottomSheetVisitDetailsBinding = this._binding;
        Intrinsics.checkNotNull(bottomSheetVisitDetailsBinding);
        return bottomSheetVisitDetailsBinding;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.visitData = arguments != null ? (ApprovedVisitsLists) arguments.getParcelable("visitData") : null;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = BottomSheetVisitDetailsBinding.inflate(inflater, container, false);
        ScrollView root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String str;
        ArrayList emptyList;
        Products products;
        String num;
        String num2;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        ApprovedVisitsLists data = this.visitData;
        if (data != null) {
            BottomSheetVisitDetailsBinding $this$onViewCreated_u24lambda_u245_u24lambda_u244 = getBinding();
            TextView textView = $this$onViewCreated_u24lambda_u245_u24lambda_u244.tvVisitDate;
            String visitDate = data.getVisitDate();
            if (visitDate == null) {
                visitDate = "--";
            }
            textView.setText(visitDate);
            TextView textView2 = $this$onViewCreated_u24lambda_u245_u24lambda_u244.tvVisitDuration;
            Integer visitDuration = data.getVisitDuration();
            textView2.setText((visitDuration == null || (num2 = visitDuration.toString()) == null) ? "N/A" : num2);
            TextView textView3 = $this$onViewCreated_u24lambda_u245_u24lambda_u244.tvOrderValue;
            Integer visitTotal = data.getVisitTotal();
            textView3.setText((visitTotal == null || (num = visitTotal.toString()) == null) ? "N/A" : num);
            TextView textView4 = $this$onViewCreated_u24lambda_u245_u24lambda_u244.tvBookTitle;
            List<Products> products2 = data.getProducts();
            if (products2 == null || (products = (Products) CollectionsKt.firstOrNull((List) products2)) == null || (str = products.getProductName()) == null) {
                str = "No book title";
            }
            textView4.setText(str);
            TextView textView5 = $this$onViewCreated_u24lambda_u245_u24lambda_u244.tvVisitNotes;
            String remark = data.getRemark();
            if (remark == null) {
                remark = "No remarks available";
            }
            textView5.setText(remark);
            final String invoicePdfUrl = data.getInvoiceImageUrls();
            $this$onViewCreated_u24lambda_u245_u24lambda_u244.invoicePdf.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.VisitDetailsBottomSheet$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    VisitDetailsBottomSheet.onViewCreated$lambda$5$lambda$4$lambda$0(invoicePdfUrl, this, view2);
                }
            });
            TextView textView6 = $this$onViewCreated_u24lambda_u245_u24lambda_u244.tvInvoiceNo;
            Integer id = data.getId();
            Intrinsics.checkNotNull(id);
            textView6.setText(generateInvoiceFromItem(id.intValue()));
            try {
                String imageUrls = data.getImageUrls();
                if (imageUrls == null) {
                    imageUrls = HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
                }
                JSONArray jsonArray = new JSONArray(imageUrls);
                int length = jsonArray.length();
                ArrayList arrayList = new ArrayList(length);
                for (int i = 0; i < length; i++) {
                    int i2 = i;
                    arrayList.add(jsonArray.getString(i2));
                }
                emptyList = arrayList;
            } catch (Exception e) {
                emptyList = CollectionsKt.emptyList();
            }
            List imageList = emptyList;
            Glide.with($this$onViewCreated_u24lambda_u245_u24lambda_u244.imgBook.getContext()).load((String) CollectionsKt.firstOrNull(imageList)).placeholder(R.drawable.logo).error(R.drawable.logo).apply((BaseRequestOptions<?>) new RequestOptions().transform(new RoundedCorners(15))).into($this$onViewCreated_u24lambda_u245_u24lambda_u244.imgBook);
            if (!imageList.isEmpty()) {
                RecyclerView $this$onViewCreated_u24lambda_u245_u24lambda_u244_u24lambda_u242 = $this$onViewCreated_u24lambda_u245_u24lambda_u244.recyclerVisitImages;
                $this$onViewCreated_u24lambda_u245_u24lambda_u244_u24lambda_u242.setAdapter(new VisitImagesAdapter(imageList));
                $this$onViewCreated_u24lambda_u245_u24lambda_u244_u24lambda_u242.setLayoutManager(new LinearLayoutManager($this$onViewCreated_u24lambda_u245_u24lambda_u244_u24lambda_u242.getContext(), 0, false));
                $this$onViewCreated_u24lambda_u245_u24lambda_u244_u24lambda_u242.setVisibility(0);
                Intrinsics.checkNotNull($this$onViewCreated_u24lambda_u245_u24lambda_u244_u24lambda_u242);
            } else {
                $this$onViewCreated_u24lambda_u245_u24lambda_u244.recyclerVisitImages.setVisibility(8);
            }
            $this$onViewCreated_u24lambda_u245_u24lambda_u244.cancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.bottomsheet.VisitDetailsBottomSheet$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    VisitDetailsBottomSheet.this.dismiss();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$5$lambda$4$lambda$0(String $invoicePdfUrl, VisitDetailsBottomSheet this$0, View it) {
        String str = $invoicePdfUrl;
        if (!(str == null || str.length() == 0)) {
            this$0.openPdfExternally($invoicePdfUrl);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }

    /* compiled from: VisitDetailsBottomSheet.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/bottomsheet/VisitDetailsBottomSheet$Companion;", "", "<init>", "()V", "newInstance", "Lcom/ingenious/androidbookmarksalesupgrade/bottomsheet/VisitDetailsBottomSheet;", "visitData", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ApprovedVisitsLists;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VisitDetailsBottomSheet newInstance(ApprovedVisitsLists visitData) {
            Intrinsics.checkNotNullParameter(visitData, "visitData");
            VisitDetailsBottomSheet fragment = new VisitDetailsBottomSheet();
            Bundle args = new Bundle();
            args.putParcelable("visitData", visitData);
            fragment.setArguments(args);
            return fragment;
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public int getTheme() {
        return R.style.RoundedDialogTheme;
    }

    private final void openPdfExternally(String pdfUrl) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(pdfUrl), "application/pdf");
            intent.setFlags(1073741824);
            startActivity(intent);
        } catch (Exception e) {
            Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(pdfUrl));
            startActivity(browserIntent);
        }
    }

    public final String generateInvoiceFromItem(int itemId) {
        int year = Calendar.getInstance().get(1);
        int suffix = (itemId % 9000) + 1000;
        return "#INV-" + year + "-" + suffix;
    }
}
