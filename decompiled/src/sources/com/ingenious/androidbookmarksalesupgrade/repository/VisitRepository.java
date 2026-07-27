package com.ingenious.androidbookmarksalesupgrade.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils;
import com.ingenious.androidbookmarksalesupgrade.model.request.AddAdoptionRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.AddCustomerRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.AddVisitRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.CreateProductRefillRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.LocationCheckRequest;
import com.ingenious.androidbookmarksalesupgrade.model.response.AddAdoptionResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.AddCustomerResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.AddVisitResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooksResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionDetailsResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.ApprovedVisitsResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.CreateProductRefillResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.CustomersListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.GradesListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.ImageCheckResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.LastVisitCustomerResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.LocationCheckResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.ProductListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.RefillByStatusResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.SearchCustomerResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.SegmentsListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.SubjectsListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitCompletionResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitDetailsResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.network.domain.DataAccessStrategyKt;
import com.ingenious.androidbookmarksalesupgrade.network.domain.DataSource;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/* compiled from: VisitRepository.kt */
@Metadata(d1 = {"\u0000ð\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0086@¢\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00072\u0006\u0010\u000e\u001a\u00020\nH\u0086@¢\u0006\u0002\u0010\u000bJ\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00072\u0006\u0010\u000e\u001a\u00020\nH\u0086@¢\u0006\u0002\u0010\u000bJ\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00072\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@¢\u0006\u0002\u0010\u0015J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00072\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@¢\u0006\u0002\u0010\u001aJ(\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00072\u0006\u0010\u001d\u001a\u00020\u001e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 H\u0086@¢\u0006\u0002\u0010!Jb\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u00072\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u001e2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010 2\u001c\b\u0002\u0010'\u001a\u0016\u0012\u0004\u0012\u00020 \u0018\u00010(j\n\u0012\u0004\u0012\u00020 \u0018\u0001`)2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010 H\u0086@¢\u0006\u0002\u0010+J\u001c\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u00072\u0006\u0010.\u001a\u00020\nH\u0086@¢\u0006\u0002\u0010\u000bJ\u001c\u0010/\u001a\b\u0012\u0004\u0012\u0002000\u00072\u0006\u00101\u001a\u000202H\u0086@¢\u0006\u0002\u00103J\u001c\u00104\u001a\b\u0012\u0004\u0012\u0002050\u00072\u0006\u0010\u000e\u001a\u00020\nH\u0086@¢\u0006\u0002\u0010\u000bJP\u00106\u001a\b\u0012\u0004\u0012\u0002070\u00072\n\b\u0002\u00108\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\n2\n\b\u0002\u00109\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\nH\u0086@¢\u0006\u0002\u0010<J\u001c\u0010=\u001a\b\u0012\u0004\u0012\u00020>0\u00072\u0006\u0010?\u001a\u00020@H\u0086@¢\u0006\u0002\u0010AJ\u001c\u0010B\u001a\b\u0012\u0004\u0012\u00020C0\u00072\u0006\u0010D\u001a\u00020\nH\u0086@¢\u0006\u0002\u0010\u000bJ\u0014\u0010E\u001a\b\u0012\u0004\u0012\u00020F0\u0007H\u0086@¢\u0006\u0002\u0010GJ\u001c\u0010H\u001a\b\u0012\u0004\u0012\u00020I0\u00072\u0006\u0010J\u001a\u00020\nH\u0086@¢\u0006\u0002\u0010\u000bJ\u001c\u0010K\u001a\b\u0012\u0004\u0012\u00020L0\u00072\u0006\u0010M\u001a\u00020\nH\u0086@¢\u0006\u0002\u0010\u000bJ\u001c\u0010N\u001a\b\u0012\u0004\u0012\u00020O0\u00072\u0006\u0010P\u001a\u00020\nH\u0086@¢\u0006\u0002\u0010\u000bJ\u001c\u0010Q\u001a\b\u0012\u0004\u0012\u00020R0\u00072\u0006\u0010Q\u001a\u00020SH\u0086@¢\u0006\u0002\u0010TJ\u001c\u0010U\u001a\b\u0012\u0004\u0012\u00020V0\u00072\u0006\u0010\u000e\u001a\u00020WH\u0086@¢\u0006\u0002\u0010XJ\u001c\u0010Y\u001a\b\u0012\u0004\u0012\u00020Z0\u00072\u0006\u0010[\u001a\u00020WH\u0086@¢\u0006\u0002\u0010XR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\\"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/repository/VisitRepository;", "", "<init>", "()V", "remoteDataSource", "Lcom/ingenious/androidbookmarksalesupgrade/network/domain/DataSource;", "visitDetails", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitDetailsResponse;", Constant.VISIT_ID, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "productsList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ProductListResponse;", "customerId", "approvedVisits", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ApprovedVisitsResponse;", "addCustomer", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AddCustomerResponse;", "addCustomerRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddCustomerRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddCustomerRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "locationCheck", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LocationCheckResponse;", "locationCheckRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/LocationCheckRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/LocationCheckRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "imageCheck", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ImageCheckResponse;", "visitId", "Lokhttp3/RequestBody;", "image", "Lokhttp3/MultipartBody$Part;", "(Lokhttp3/RequestBody;Lokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "visitCompletion", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitCompletionResponse;", "remarks", "invoiceType", "signature", "images", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "invoice", "(Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/MultipartBody$Part;Ljava/util/ArrayList;Lokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchCustomer", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/SearchCustomerResponse;", FirebaseAnalytics.Event.SEARCH, "addVisit", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AddVisitResponse;", "addVisitRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddVisitRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddVisitRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lastVisitCustomer", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LastVisitCustomerResponse;", "customersList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/CustomersListResponse;", "type", "area", "lastVisit", "addedBy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createFill", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/CreateProductRefillResponse;", "createProductRefillRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/CreateProductRefillRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/CreateProductRefillRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refillByStatus", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/RefillByStatusResponse;", NotificationCompat.CATEGORY_STATUS, "segmentsList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/SegmentsListResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "gradesList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GradesListResponse;", "segmentId", "subjectsList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/SubjectsListResponse;", "gradeId", "adoptionBooksList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionBooksResponse;", "subjectId", "addAdoption", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AddAdoptionResponse;", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddAdoptionRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddAdoptionRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "adoption", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionListResponse;", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "adoptionDetails", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionDetailsResponse;", "adoptionId", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes15.dex */
public final class VisitRepository {
    private DataSource remoteDataSource = InjectUtils.INSTANCE.getDataSource();

    public final Object visitDetails(String id, Continuation<? super ApiResponseCallback<VisitDetailsResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$visitDetails$2(this, id, null), continuation);
    }

    public final Object productsList(String customerId, Continuation<? super ApiResponseCallback<ProductListResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$productsList$2(this, customerId, null), continuation);
    }

    public final Object approvedVisits(String customerId, Continuation<? super ApiResponseCallback<ApprovedVisitsResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$approvedVisits$2(this, customerId, null), continuation);
    }

    public final Object addCustomer(AddCustomerRequest addCustomerRequest, Continuation<? super ApiResponseCallback<AddCustomerResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$addCustomer$2(this, addCustomerRequest, null), continuation);
    }

    public final Object locationCheck(LocationCheckRequest locationCheckRequest, Continuation<? super ApiResponseCallback<LocationCheckResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$locationCheck$2(this, locationCheckRequest, null), continuation);
    }

    public static /* synthetic */ Object imageCheck$default(VisitRepository visitRepository, RequestBody requestBody, MultipartBody.Part part, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            part = null;
        }
        return visitRepository.imageCheck(requestBody, part, continuation);
    }

    public final Object imageCheck(RequestBody visitId, MultipartBody.Part image, Continuation<? super ApiResponseCallback<ImageCheckResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$imageCheck$2(this, visitId, image, null), continuation);
    }

    public final Object visitCompletion(RequestBody visitId, RequestBody remarks, RequestBody invoiceType, MultipartBody.Part signature, ArrayList<MultipartBody.Part> arrayList, MultipartBody.Part invoice, Continuation<? super ApiResponseCallback<VisitCompletionResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$visitCompletion$2(this, visitId, remarks, invoiceType, signature, arrayList, invoice, null), continuation);
    }

    public final Object searchCustomer(String search, Continuation<? super ApiResponseCallback<SearchCustomerResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$searchCustomer$2(this, search, null), continuation);
    }

    public final Object addVisit(AddVisitRequest addVisitRequest, Continuation<? super ApiResponseCallback<AddVisitResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$addVisit$2(this, addVisitRequest, null), continuation);
    }

    public final Object lastVisitCustomer(String customerId, Continuation<? super ApiResponseCallback<LastVisitCustomerResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$lastVisitCustomer$2(this, customerId, null), continuation);
    }

    public final Object customersList(String type, String search, String area, String lastVisit, String addedBy, Continuation<? super ApiResponseCallback<CustomersListResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$customersList$2(this, type, search, area, lastVisit, addedBy, null), continuation);
    }

    public final Object createFill(CreateProductRefillRequest createProductRefillRequest, Continuation<? super ApiResponseCallback<CreateProductRefillResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$createFill$2(this, createProductRefillRequest, null), continuation);
    }

    public final Object refillByStatus(String status, Continuation<? super ApiResponseCallback<RefillByStatusResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$refillByStatus$2(this, status, null), continuation);
    }

    public final Object segmentsList(Continuation<? super ApiResponseCallback<SegmentsListResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$segmentsList$2(this, null), continuation);
    }

    public final Object gradesList(String segmentId, Continuation<? super ApiResponseCallback<GradesListResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$gradesList$2(this, segmentId, null), continuation);
    }

    public final Object subjectsList(String gradeId, Continuation<? super ApiResponseCallback<SubjectsListResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$subjectsList$2(this, gradeId, null), continuation);
    }

    public final Object adoptionBooksList(String subjectId, Continuation<? super ApiResponseCallback<AdoptionBooksResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$adoptionBooksList$2(this, subjectId, null), continuation);
    }

    public final Object addAdoption(AddAdoptionRequest addAdoption, Continuation<? super ApiResponseCallback<AddAdoptionResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$addAdoption$2(this, addAdoption, null), continuation);
    }

    public final Object adoption(int customerId, Continuation<? super ApiResponseCallback<AdoptionListResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$adoption$2(this, customerId, null), continuation);
    }

    public final Object adoptionDetails(int adoptionId, Continuation<? super ApiResponseCallback<AdoptionDetailsResponse>> continuation) {
        return DataAccessStrategyKt.performNetworkCallOperation(new VisitRepository$adoptionDetails$2(this, adoptionId, null), continuation);
    }
}
