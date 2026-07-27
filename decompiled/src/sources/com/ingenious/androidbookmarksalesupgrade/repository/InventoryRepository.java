package com.ingenious.androidbookmarksalesupgrade.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils;
import com.ingenious.androidbookmarksalesupgrade.model.response.BooksBySegmentResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.LowStockInventoryResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.StockSummaryResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.network.domain.DataAccessStrategyKt;
import com.ingenious.androidbookmarksalesupgrade.network.domain.DataSource;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* compiled from: InventoryRepository.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0086@¢\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0007H\u0086@¢\u0006\u0002\u0010\tJ\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0007H\u0086@¢\u0006\u0002\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/repository/InventoryRepository;", "", "<init>", "()V", "remoteDataSource", "Lcom/ingenious/androidbookmarksalesupgrade/network/domain/DataSource;", "lowStockInventory", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LowStockInventoryResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stockSummary", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/StockSummaryResponse;", "booksBySegment", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/BooksBySegmentResponse;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes15.dex */
public final class InventoryRepository {
    private DataSource remoteDataSource = InjectUtils.INSTANCE.getDataSource();

    public final Object lowStockInventory(Continuation<? super ApiResponseCallback<LowStockInventoryResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new InventoryRepository$lowStockInventory$2(this, null), continuation);
    }

    public final Object stockSummary(Continuation<? super ApiResponseCallback<StockSummaryResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new InventoryRepository$stockSummary$2(this, null), continuation);
    }

    public final Object booksBySegment(Continuation<? super ApiResponseCallback<BooksBySegmentResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new InventoryRepository$booksBySegment$2(this, null), continuation);
    }
}
