package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.FaqAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityQuickScreenBinding;
import com.ingenious.androidbookmarksalesupgrade.model.FaqModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QuickScreen.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/QuickScreen;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityQuickScreenBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class QuickScreen extends AppCompatActivity {
    private ActivityQuickScreenBinding binding;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityQuickScreenBinding activityQuickScreenBinding = null;
        EdgeToEdge.enable$default(this, null, null, 3, null);
        this.binding = ActivityQuickScreenBinding.inflate(getLayoutInflater());
        ActivityQuickScreenBinding activityQuickScreenBinding2 = this.binding;
        if (activityQuickScreenBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQuickScreenBinding2 = null;
        }
        setContentView(activityQuickScreenBinding2.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new OnApplyWindowInsetsListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.QuickScreen$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat onCreate$lambda$0;
                onCreate$lambda$0 = QuickScreen.onCreate$lambda$0(view, windowInsetsCompat);
                return onCreate$lambda$0;
            }
        });
        ActivityQuickScreenBinding activityQuickScreenBinding3 = this.binding;
        if (activityQuickScreenBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityQuickScreenBinding = activityQuickScreenBinding3;
        }
        ActivityQuickScreenBinding $this$onCreate_u24lambda_u242 = activityQuickScreenBinding;
        $this$onCreate_u24lambda_u242.backIcon.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.QuickScreen$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QuickScreen.this.finish();
            }
        });
        List faqList = CollectionsKt.listOf((Object[]) new FaqModel[]{new FaqModel("How do I create an adoption?", "To create an adoption, go to the Customers tab.\nSelect the school, tap on the adoption.\nThere you’ll see Create Adoption button and then complete the process and submit it.\nOnce submitted, the adoption will appear in your list \nand can be tracked anytime.", false, 4, null), new FaqModel("How do I request a stock refill?", "Go to Stock → Refill section...", false, 4, null), new FaqModel("Where can I see pending refill requests?", "You can see pending refills in the Refill tab...", false, 4, null), new FaqModel("What should I do if the invoice is incorrect?", "Contact admin support with invoice number...", false, 4, null)});
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FaqAdapter(faqList));
        $this$onCreate_u24lambda_u242.faqContainer.addView(recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat onCreate$lambda$0(View v, WindowInsetsCompat insets) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(insets, "insets");
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(systemBars, "getInsets(...)");
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        return insets;
    }
}
