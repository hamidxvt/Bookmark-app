package com.ingenious.androidbookmarksalesupgrade.viewModel;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import kotlin.Metadata;

/* compiled from: CheckInViewModel.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/viewModel/CheckInViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "photoPath", "Landroidx/lifecycle/MutableLiveData;", "", "getPhotoPath", "()Landroidx/lifecycle/MutableLiveData;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class CheckInViewModel extends ViewModel {
    private final MutableLiveData<String> photoPath = new MutableLiveData<>();

    public final MutableLiveData<String> getPhotoPath() {
        return this.photoPath;
    }
}
