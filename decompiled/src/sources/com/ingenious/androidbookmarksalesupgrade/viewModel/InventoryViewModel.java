package com.ingenious.androidbookmarksalesupgrade.viewModel;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils;
import com.ingenious.androidbookmarksalesupgrade.model.response.BooksBySegmentResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.LowStockInventoryResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.StockSummaryResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.repository.AppRepository;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: InventoryViewModel.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0014\u001a\u00020\u000fJ\u0006\u0010\u0019\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\u000b8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\rR\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\b0\u000b8F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/viewModel/InventoryViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "repository", "Lcom/ingenious/androidbookmarksalesupgrade/repository/AppRepository;", "_lowStockResponse", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LowStockInventoryResponse;", "lowStockResponse", "Landroidx/lifecycle/LiveData;", "getLowStockResponse", "()Landroidx/lifecycle/LiveData;", "lowStock", "", "_stockSummaryResponse", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/StockSummaryResponse;", "stockSummaryResponse", "getStockSummaryResponse", "stockSummary", "_booksBySegmentResponse", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/BooksBySegmentResponse;", "booksBySegmentResponse", "getBooksBySegmentResponse", "booksBySegment", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class InventoryViewModel extends ViewModel {
    private final AppRepository repository = InjectUtils.INSTANCE.getAppRepository();
    private final MutableLiveData<ApiResponseCallback<LowStockInventoryResponse>> _lowStockResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<StockSummaryResponse>> _stockSummaryResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<BooksBySegmentResponse>> _booksBySegmentResponse = new MutableLiveData<>();

    public final LiveData<ApiResponseCallback<LowStockInventoryResponse>> getLowStockResponse() {
        return this._lowStockResponse;
    }

    public final void lowStock() {
        this._lowStockResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new InventoryViewModel$lowStock$1(this, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<StockSummaryResponse>> getStockSummaryResponse() {
        return this._stockSummaryResponse;
    }

    public final void stockSummary() {
        this._stockSummaryResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new InventoryViewModel$stockSummary$1(this, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<BooksBySegmentResponse>> getBooksBySegmentResponse() {
        return this._booksBySegmentResponse;
    }

    public final void booksBySegment() {
        this._booksBySegmentResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new InventoryViewModel$booksBySegment$1(this, null), 3, null);
    }
}
