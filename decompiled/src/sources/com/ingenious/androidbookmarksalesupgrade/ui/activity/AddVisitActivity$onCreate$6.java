package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddVisitActivity.kt */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016J*\u0010\n\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0012\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\rH\u0016¨\u0006\u000e"}, d2 = {"com/ingenious/androidbookmarksalesupgrade/ui/activity/AddVisitActivity$onCreate$6", "Landroid/text/TextWatcher;", "beforeTextChanged", "", "s", "", "start", "", "count", "after", "onTextChanged", "before", "afterTextChanged", "Landroid/text/Editable;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class AddVisitActivity$onCreate$6 implements TextWatcher {
    final /* synthetic */ AddVisitActivity this$0;

    AddVisitActivity$onCreate$6(AddVisitActivity $receiver) {
        this.this$0 = $receiver;
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Runnable it;
        Handler handler;
        Runnable runnable;
        Handler handler2;
        final String query = String.valueOf(s);
        it = this.this$0.runnable;
        if (it != null) {
            handler2 = this.this$0.handler;
            handler2.removeCallbacks(it);
        }
        AddVisitActivity addVisitActivity = this.this$0;
        final AddVisitActivity addVisitActivity2 = this.this$0;
        addVisitActivity.runnable = new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.AddVisitActivity$onCreate$6$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AddVisitActivity$onCreate$6.onTextChanged$lambda$1(query, addVisitActivity2);
            }
        };
        handler = this.this$0.handler;
        runnable = this.this$0.runnable;
        Intrinsics.checkNotNull(runnable);
        handler.postDelayed(runnable, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTextChanged$lambda$1(String $query, AddVisitActivity this$0) {
        VisitViewModel viewModel;
        if ($query.length() > 0) {
            viewModel = this$0.getViewModel();
            viewModel.searchCustomer($query);
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable s) {
    }
}
