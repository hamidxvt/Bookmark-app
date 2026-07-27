package com.ingenious.androidbookmarksalesupgrade.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils;
import com.ingenious.androidbookmarksalesupgrade.model.response.HomeResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.OnlineStatusResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.network.domain.DataAccessStrategyKt;
import com.ingenious.androidbookmarksalesupgrade.network.domain.DataSource;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* compiled from: HomeRepository.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003Jh\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\nH\u0086@¢\u0006\u0002\u0010\u0011J,\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00072\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0086@¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/repository/HomeRepository;", "", "<init>", "()V", "remoteDataSource", "Lcom/ingenious/androidbookmarksalesupgrade/network/domain/DataSource;", "home", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/HomeResponse;", "currentDate", "", "latitude", "longitude", "priority", "distance", "customerType", "addedBy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "jobStatus", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/OnlineStatusResponse;", NotificationCompat.CATEGORY_STATUS, "", "(ZLjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes15.dex */
public final class HomeRepository {
    private DataSource remoteDataSource = InjectUtils.INSTANCE.getDataSource();

    public final Object home(String currentDate, String latitude, String longitude, String priority, String distance, String customerType, String addedBy, Continuation<? super ApiResponseCallback<HomeResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new HomeRepository$home$2(this, currentDate, latitude, longitude, priority, distance, customerType, addedBy, null), continuation);
    }

    public final Object jobStatus(boolean status, String latitude, String longitude, Continuation<? super ApiResponseCallback<OnlineStatusResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new HomeRepository$jobStatus$2(this, status, latitude, longitude, null), continuation);
    }
}
