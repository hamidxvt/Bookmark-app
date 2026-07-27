package com.ingenious.androidbookmarksalesupgrade.network.domain;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.Scopes;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils;
import com.ingenious.androidbookmarksalesupgrade.model.MessageListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.request.AddAdoptionRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.AddCustomerRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.AddVisitRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.CreateProductRefillRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.ForgetRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.LocationCheckRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.LoginRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.ResetPasswordRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.SendMessageRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.VerifyOtpRequest;
import com.ingenious.androidbookmarksalesupgrade.model.response.AddAdoptionResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.AddCustomerResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.AddVisitResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooksResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionDetailsResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.ApprovedVisitsResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.BooksBySegmentResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.CreateProductRefillResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.CustomersListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.GlobalResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.GradesListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.HomeResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.ImageCheckResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.LastVisitCustomerResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.LocationCheckResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.LoginResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.LowStockInventoryResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.OnlineStatusResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.ProductListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.ProfileResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.RefillByStatusResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.SearchCustomerResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.SegmentsListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.StockSummaryResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.SubjectsListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitCompletionResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitDetailsResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.network.SoService;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/* compiled from: DataSource.kt */
@Metadata(d1 = {"\u0000Ô\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0086@¢\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u0010\u0010J\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@¢\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u00072\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@¢\u0006\u0002\u0010\u0018Jh\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00072\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u001cH\u0086@¢\u0006\u0002\u0010#J,\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00072\u0006\u0010&\u001a\u00020'2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001cH\u0086@¢\u0006\u0002\u0010(J\u001c\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00072\u0006\u0010+\u001a\u00020\u001cH\u0086@¢\u0006\u0002\u0010,J\u001c\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\u00072\u0006\u0010/\u001a\u00020\u001cH\u0086@¢\u0006\u0002\u0010,J\u001c\u00100\u001a\b\u0012\u0004\u0012\u0002010\u00072\u0006\u0010/\u001a\u00020\u001cH\u0086@¢\u0006\u0002\u0010,J\u0014\u00102\u001a\b\u0012\u0004\u0012\u0002030\u0007H\u0086@¢\u0006\u0002\u00104J\u001c\u00105\u001a\b\u0012\u0004\u0012\u0002060\u00072\u0006\u00107\u001a\u000208H\u0086@¢\u0006\u0002\u00109J\u001c\u0010:\u001a\b\u0012\u0004\u0012\u00020;0\u00072\u0006\u0010<\u001a\u00020=H\u0086@¢\u0006\u0002\u0010>J(\u0010?\u001a\b\u0012\u0004\u0012\u00020@0\u00072\u0006\u0010A\u001a\u00020B2\n\b\u0002\u0010C\u001a\u0004\u0018\u00010DH\u0086@¢\u0006\u0002\u0010EJb\u0010F\u001a\b\u0012\u0004\u0012\u00020G0\u00072\u0006\u0010A\u001a\u00020B2\u0006\u0010H\u001a\u00020B2\u0006\u0010I\u001a\u00020B2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010D2\u001c\b\u0002\u0010K\u001a\u0016\u0012\u0004\u0012\u00020D\u0018\u00010Lj\n\u0012\u0004\u0012\u00020D\u0018\u0001`M2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010DH\u0086@¢\u0006\u0002\u0010OJ\u001c\u0010P\u001a\b\u0012\u0004\u0012\u00020\r0\u00072\u0006\u0010Q\u001a\u00020RH\u0086@¢\u0006\u0002\u0010SJ\u001c\u0010T\u001a\b\u0012\u0004\u0012\u00020U0\u00072\u0006\u0010V\u001a\u00020WH\u0086@¢\u0006\u0002\u0010XJ\u001c\u0010Y\u001a\b\u0012\u0004\u0012\u00020Z0\u00072\u0006\u0010[\u001a\u00020\\H\u0086@¢\u0006\u0002\u0010]JT\u0010^\u001a\b\u0012\u0004\u0012\u00020.0\u00072\u0006\u0010_\u001a\u00020\u001c2\u0006\u0010V\u001a\u00020W2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010`\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010a\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010b\u001a\u0004\u0018\u00010\u001cH\u0086@¢\u0006\u0002\u0010cJ\u001c\u0010d\u001a\b\u0012\u0004\u0012\u00020e0\u00072\u0006\u0010_\u001a\u00020\u001cH\u0086@¢\u0006\u0002\u0010,J\u001c\u0010f\u001a\b\u0012\u0004\u0012\u00020g0\u00072\u0006\u0010/\u001a\u00020\u001cH\u0086@¢\u0006\u0002\u0010,J\u0014\u0010h\u001a\b\u0012\u0004\u0012\u00020i0\u0007H\u0086@¢\u0006\u0002\u00104J\u0014\u0010j\u001a\b\u0012\u0004\u0012\u00020k0\u0007H\u0086@¢\u0006\u0002\u00104J\u0014\u0010l\u001a\b\u0012\u0004\u0012\u00020m0\u0007H\u0086@¢\u0006\u0002\u00104JP\u0010n\u001a\b\u0012\u0004\u0012\u00020o0\u00072\n\b\u0002\u0010p\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010_\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010q\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010r\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u001cH\u0086@¢\u0006\u0002\u0010sJ\u001c\u0010t\u001a\b\u0012\u0004\u0012\u00020u0\u00072\u0006\u0010v\u001a\u00020wH\u0086@¢\u0006\u0002\u0010xJ\u001c\u0010y\u001a\b\u0012\u0004\u0012\u00020z0\u00072\u0006\u0010&\u001a\u00020\u001cH\u0086@¢\u0006\u0002\u0010,J\u0014\u0010{\u001a\b\u0012\u0004\u0012\u00020|0\u0007H\u0086@¢\u0006\u0002\u00104J\u001c\u0010}\u001a\b\u0012\u0004\u0012\u00020~0\u00072\u0006\u0010\u007f\u001a\u00020\u001cH\u0086@¢\u0006\u0002\u0010,J\u001f\u0010\u0080\u0001\u001a\t\u0012\u0005\u0012\u00030\u0081\u00010\u00072\u0007\u0010\u0082\u0001\u001a\u00020\u001cH\u0086@¢\u0006\u0002\u0010,J\u001e\u0010\u0083\u0001\u001a\t\u0012\u0005\u0012\u00030\u0084\u00010\u00072\u0006\u0010`\u001a\u00020\u001cH\u0086@¢\u0006\u0002\u0010,J!\u0010\u0085\u0001\u001a\t\u0012\u0005\u0012\u00030\u0086\u00010\u00072\b\u0010\u0085\u0001\u001a\u00030\u0087\u0001H\u0086@¢\u0006\u0003\u0010\u0088\u0001J\u001e\u0010\u0089\u0001\u001a\t\u0012\u0005\u0012\u00030\u008a\u00010\u00072\u0006\u0010/\u001a\u00020WH\u0086@¢\u0006\u0002\u0010XJ\u001f\u0010\u008b\u0001\u001a\t\u0012\u0005\u0012\u00030\u008c\u00010\u00072\u0007\u0010\u008d\u0001\u001a\u00020WH\u0086@¢\u0006\u0002\u0010XR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u008e\u0001"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/network/domain/DataSource;", "Lcom/ingenious/androidbookmarksalesupgrade/network/domain/BaseDataSource;", "<init>", "()V", "apiService", "Lcom/ingenious/androidbookmarksalesupgrade/network/SoService;", FirebaseAnalytics.Event.LOGIN, "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LoginResponse;", "loginRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/LoginRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "forgetPassword", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;", "forgetRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/ForgetRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/ForgetRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyOtp", "verifyOtpRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/VerifyOtpRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/VerifyOtpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetPassword", "resetPasswordRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/ResetPasswordRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/ResetPasswordRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "home", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/HomeResponse;", "currentDate", "", "latitude", "longitude", "priority", "distance", "customerType", "addedBy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getJobStatus", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/OnlineStatusResponse;", NotificationCompat.CATEGORY_STATUS, "", "(ZLjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "visitDetails", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitDetailsResponse;", Constant.VISIT_ID, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "productsList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ProductListResponse;", "customerId", "approvedVisits", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ApprovedVisitsResponse;", Scopes.PROFILE, "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ProfileResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addCustomer", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AddCustomerResponse;", "addCustomerRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddCustomerRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddCustomerRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "locationCheck", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LocationCheckResponse;", "locationCheckRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/LocationCheckRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/LocationCheckRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "imageCheck", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ImageCheckResponse;", "visitId", "Lokhttp3/RequestBody;", "image", "Lokhttp3/MultipartBody$Part;", "(Lokhttp3/RequestBody;Lokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "visitCompletion", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitCompletionResponse;", "remarks", "invoiceType", "signature", "images", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "invoice", "(Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/MultipartBody$Part;Ljava/util/ArrayList;Lokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendMessage", "sendMessageRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/SendMessageRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/SendMessageRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "listMessage", "Lcom/ingenious/androidbookmarksalesupgrade/model/MessageListResponse;", "page", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addVisit", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AddVisitResponse;", "addVisitRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddVisitRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddVisitRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProductList", FirebaseAnalytics.Event.SEARCH, "subjectId", "brandId", "seriesId", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchCustomer", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/SearchCustomerResponse;", "lastVisitCustomer", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LastVisitCustomerResponse;", "lowStockInventory", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LowStockInventoryResponse;", "stockSummary", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/StockSummaryResponse;", "booksBySegment", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/BooksBySegmentResponse;", "customersList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/CustomersListResponse;", "type", "area", "lastVisit", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createFill", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/CreateProductRefillResponse;", "createProductRefillRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/CreateProductRefillRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/CreateProductRefillRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refillByStatus", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/RefillByStatusResponse;", "segmentsList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/SegmentsListResponse;", "gradesList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GradesListResponse;", "segmentId", "subjectsList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/SubjectsListResponse;", "gradeId", "adoptionBooksList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionBooksResponse;", "addAdoption", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AddAdoptionResponse;", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddAdoptionRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddAdoptionRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "adoption", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionListResponse;", "adoptionDetails", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionDetailsResponse;", "adoptionId", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes15.dex */
public final class DataSource extends BaseDataSource {
    private SoService apiService = InjectUtils.INSTANCE.getGetRetrofit();

    public final Object login(LoginRequest loginRequest, Continuation<? super ApiResponseCallback<LoginResponse>> continuation) {
        return callApi(new DataSource$login$2(this, loginRequest, null), continuation);
    }

    public final Object forgetPassword(ForgetRequest forgetRequest, Continuation<? super ApiResponseCallback<GlobalResponse>> continuation) {
        return callApi(new DataSource$forgetPassword$2(this, forgetRequest, null), continuation);
    }

    public final Object verifyOtp(VerifyOtpRequest verifyOtpRequest, Continuation<? super ApiResponseCallback<GlobalResponse>> continuation) {
        return callApi(new DataSource$verifyOtp$2(this, verifyOtpRequest, null), continuation);
    }

    public final Object resetPassword(ResetPasswordRequest resetPasswordRequest, Continuation<? super ApiResponseCallback<GlobalResponse>> continuation) {
        return callApi(new DataSource$resetPassword$2(this, resetPasswordRequest, null), continuation);
    }

    public final Object home(String currentDate, String latitude, String longitude, String priority, String distance, String customerType, String addedBy, Continuation<? super ApiResponseCallback<HomeResponse>> continuation) {
        return callApi(new DataSource$home$2(this, currentDate, latitude, longitude, priority, distance, customerType, addedBy, null), continuation);
    }

    public final Object getJobStatus(boolean status, String latitude, String longitude, Continuation<? super ApiResponseCallback<OnlineStatusResponse>> continuation) {
        return callApi(new DataSource$getJobStatus$2(this, status, latitude, longitude, null), continuation);
    }

    public final Object visitDetails(String id, Continuation<? super ApiResponseCallback<VisitDetailsResponse>> continuation) {
        return callApi(new DataSource$visitDetails$2(this, id, null), continuation);
    }

    public final Object productsList(String customerId, Continuation<? super ApiResponseCallback<ProductListResponse>> continuation) {
        return callApi(new DataSource$productsList$2(this, customerId, null), continuation);
    }

    public final Object approvedVisits(String customerId, Continuation<? super ApiResponseCallback<ApprovedVisitsResponse>> continuation) {
        return callApi(new DataSource$approvedVisits$2(this, customerId, null), continuation);
    }

    public final Object profile(Continuation<? super ApiResponseCallback<ProfileResponse>> continuation) {
        return callApi(new DataSource$profile$2(this, null), continuation);
    }

    public final Object addCustomer(AddCustomerRequest addCustomerRequest, Continuation<? super ApiResponseCallback<AddCustomerResponse>> continuation) {
        return callApi(new DataSource$addCustomer$2(this, addCustomerRequest, null), continuation);
    }

    public final Object locationCheck(LocationCheckRequest locationCheckRequest, Continuation<? super ApiResponseCallback<LocationCheckResponse>> continuation) {
        return callApi(new DataSource$locationCheck$2(this, locationCheckRequest, null), continuation);
    }

    public static /* synthetic */ Object imageCheck$default(DataSource dataSource, RequestBody requestBody, MultipartBody.Part part, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            part = null;
        }
        return dataSource.imageCheck(requestBody, part, continuation);
    }

    public final Object imageCheck(RequestBody visitId, MultipartBody.Part image, Continuation<? super ApiResponseCallback<ImageCheckResponse>> continuation) {
        return callApi(new DataSource$imageCheck$2(this, visitId, image, null), continuation);
    }

    public final Object visitCompletion(RequestBody visitId, RequestBody remarks, RequestBody invoiceType, MultipartBody.Part signature, ArrayList<MultipartBody.Part> arrayList, MultipartBody.Part invoice, Continuation<? super ApiResponseCallback<VisitCompletionResponse>> continuation) {
        return callApi(new DataSource$visitCompletion$2(this, visitId, remarks, invoiceType, signature, arrayList, invoice, null), continuation);
    }

    public final Object sendMessage(SendMessageRequest sendMessageRequest, Continuation<? super ApiResponseCallback<GlobalResponse>> continuation) {
        return callApi(new DataSource$sendMessage$2(this, sendMessageRequest, null), continuation);
    }

    public final Object listMessage(int page, Continuation<? super ApiResponseCallback<MessageListResponse>> continuation) {
        return callApi(new DataSource$listMessage$2(this, page, null), continuation);
    }

    public final Object addVisit(AddVisitRequest addVisitRequest, Continuation<? super ApiResponseCallback<AddVisitResponse>> continuation) {
        return callApi(new DataSource$addVisit$2(this, addVisitRequest, null), continuation);
    }

    public final Object getProductList(String search, int page, String customerId, String subjectId, String brandId, String seriesId, Continuation<? super ApiResponseCallback<ProductListResponse>> continuation) {
        return callApi(new DataSource$getProductList$2(this, search, page, customerId, subjectId, brandId, seriesId, null), continuation);
    }

    public final Object searchCustomer(String search, Continuation<? super ApiResponseCallback<SearchCustomerResponse>> continuation) {
        return callApi(new DataSource$searchCustomer$2(this, search, null), continuation);
    }

    public final Object lastVisitCustomer(String customerId, Continuation<? super ApiResponseCallback<LastVisitCustomerResponse>> continuation) {
        return callApi(new DataSource$lastVisitCustomer$2(this, customerId, null), continuation);
    }

    public final Object lowStockInventory(Continuation<? super ApiResponseCallback<LowStockInventoryResponse>> continuation) {
        return callApi(new DataSource$lowStockInventory$2(this, null), continuation);
    }

    public final Object stockSummary(Continuation<? super ApiResponseCallback<StockSummaryResponse>> continuation) {
        return callApi(new DataSource$stockSummary$2(this, null), continuation);
    }

    public final Object booksBySegment(Continuation<? super ApiResponseCallback<BooksBySegmentResponse>> continuation) {
        return callApi(new DataSource$booksBySegment$2(this, null), continuation);
    }

    public final Object customersList(String type, String search, String area, String lastVisit, String addedBy, Continuation<? super ApiResponseCallback<CustomersListResponse>> continuation) {
        return callApi(new DataSource$customersList$2(this, type, search, area, lastVisit, addedBy, null), continuation);
    }

    public final Object createFill(CreateProductRefillRequest createProductRefillRequest, Continuation<? super ApiResponseCallback<CreateProductRefillResponse>> continuation) {
        return callApi(new DataSource$createFill$2(this, createProductRefillRequest, null), continuation);
    }

    public final Object refillByStatus(String status, Continuation<? super ApiResponseCallback<RefillByStatusResponse>> continuation) {
        return callApi(new DataSource$refillByStatus$2(this, status, null), continuation);
    }

    public final Object segmentsList(Continuation<? super ApiResponseCallback<SegmentsListResponse>> continuation) {
        return callApi(new DataSource$segmentsList$2(this, null), continuation);
    }

    public final Object gradesList(String segmentId, Continuation<? super ApiResponseCallback<GradesListResponse>> continuation) {
        return callApi(new DataSource$gradesList$2(this, segmentId, null), continuation);
    }

    public final Object subjectsList(String gradeId, Continuation<? super ApiResponseCallback<SubjectsListResponse>> continuation) {
        return callApi(new DataSource$subjectsList$2(this, gradeId, null), continuation);
    }

    public final Object adoptionBooksList(String subjectId, Continuation<? super ApiResponseCallback<AdoptionBooksResponse>> continuation) {
        return callApi(new DataSource$adoptionBooksList$2(this, subjectId, null), continuation);
    }

    public final Object addAdoption(AddAdoptionRequest addAdoption, Continuation<? super ApiResponseCallback<AddAdoptionResponse>> continuation) {
        return callApi(new DataSource$addAdoption$2(this, addAdoption, null), continuation);
    }

    public final Object adoption(int customerId, Continuation<? super ApiResponseCallback<AdoptionListResponse>> continuation) {
        return callApi(new DataSource$adoption$2(this, customerId, null), continuation);
    }

    public final Object adoptionDetails(int adoptionId, Continuation<? super ApiResponseCallback<AdoptionDetailsResponse>> continuation) {
        return callApi(new DataSource$adoptionDetails$2(this, adoptionId, null), continuation);
    }
}
