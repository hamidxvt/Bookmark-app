package com.ingenious.androidbookmarksalesupgrade.network;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.Scopes;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.model.MessageListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.request.AddAdoptionRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.AddCustomerRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.AddVisitRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.CreateProductRefillRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.ForgetRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.LocationCheckRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.LoginRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.RegistrationRequest;
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
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/* compiled from: SoService.kt */
@Metadata(d1 = {"\u0000Ð\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\u0007J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH§@¢\u0006\u0002\u0010\fJ\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u000fH§@¢\u0006\u0002\u0010\u0010J\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\u0012\u001a\u00020\u0013H§@¢\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\u00020\u00042\b\b\u0001\u0010\u0016\u001a\u00020\u0017H§@¢\u0006\u0002\u0010\u0018Jh\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00032\n\b\u0003\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0003\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\n\b\u0003\u0010\u001e\u001a\u0004\u0018\u00010\u001c2\n\b\u0003\u0010\u001f\u001a\u0004\u0018\u00010\u001c2\n\b\u0003\u0010 \u001a\u0004\u0018\u00010\u001c2\n\b\u0003\u0010!\u001a\u0004\u0018\u00010\u001c2\n\b\u0003\u0010\"\u001a\u0004\u0018\u00010\u001cH§@¢\u0006\u0002\u0010#J2\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00032\b\b\u0001\u0010&\u001a\u00020'2\b\b\u0001\u0010\u001d\u001a\u00020\u001c2\b\b\u0001\u0010\u001e\u001a\u00020\u001cH§@¢\u0006\u0002\u0010(J\u001e\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00032\b\b\u0001\u0010+\u001a\u00020\u001cH§@¢\u0006\u0002\u0010,J\u001e\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\u00032\b\b\u0001\u0010/\u001a\u00020\u001cH§@¢\u0006\u0002\u0010,J\u001e\u00100\u001a\b\u0012\u0004\u0012\u0002010\u00032\b\b\u0001\u0010/\u001a\u00020\u001cH§@¢\u0006\u0002\u0010,J\u0014\u00102\u001a\b\u0012\u0004\u0012\u0002030\u0003H§@¢\u0006\u0002\u00104J\u001e\u00105\u001a\b\u0012\u0004\u0012\u0002060\u00032\b\b\u0001\u00107\u001a\u000208H§@¢\u0006\u0002\u00109J\u001e\u0010:\u001a\b\u0012\u0004\u0012\u00020;0\u00032\b\b\u0001\u0010<\u001a\u00020=H§@¢\u0006\u0002\u0010>J*\u0010?\u001a\b\u0012\u0004\u0012\u00020@0\u00032\b\b\u0001\u0010A\u001a\u00020B2\n\b\u0003\u0010C\u001a\u0004\u0018\u00010DH§@¢\u0006\u0002\u0010EJh\u0010F\u001a\b\u0012\u0004\u0012\u00020G0\u00032\b\b\u0001\u0010A\u001a\u00020B2\b\b\u0001\u0010H\u001a\u00020B2\b\b\u0001\u0010I\u001a\u00020B2\n\b\u0003\u0010J\u001a\u0004\u0018\u00010D2\u001c\b\u0003\u0010K\u001a\u0016\u0012\u0004\u0012\u00020D\u0018\u00010Lj\n\u0012\u0004\u0012\u00020D\u0018\u0001`M2\n\b\u0001\u0010N\u001a\u0004\u0018\u00010DH§@¢\u0006\u0002\u0010OJX\u0010P\u001a\b\u0012\u0004\u0012\u00020.0\u00032\b\b\u0001\u0010Q\u001a\u00020\u001c2\b\b\u0001\u0010R\u001a\u00020'2\n\b\u0003\u0010/\u001a\u0004\u0018\u00010\u001c2\n\b\u0003\u0010S\u001a\u0004\u0018\u00010\u001c2\n\b\u0003\u0010T\u001a\u0004\u0018\u00010\u001c2\n\b\u0003\u0010U\u001a\u0004\u0018\u00010\u001cH§@¢\u0006\u0002\u0010VJ\u001e\u0010W\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010X\u001a\u00020YH§@¢\u0006\u0002\u0010ZJ\u001e\u0010[\u001a\b\u0012\u0004\u0012\u00020\\0\u00032\b\b\u0001\u0010R\u001a\u00020'H§@¢\u0006\u0002\u0010]J\u001e\u0010^\u001a\b\u0012\u0004\u0012\u00020_0\u00032\b\b\u0001\u0010`\u001a\u00020aH§@¢\u0006\u0002\u0010bJ\u001e\u0010c\u001a\b\u0012\u0004\u0012\u00020d0\u00032\b\b\u0001\u0010Q\u001a\u00020\u001cH§@¢\u0006\u0002\u0010,J\u001e\u0010e\u001a\b\u0012\u0004\u0012\u00020f0\u00032\b\b\u0001\u0010/\u001a\u00020\u001cH§@¢\u0006\u0002\u0010,J\u0014\u0010g\u001a\b\u0012\u0004\u0012\u00020h0\u0003H§@¢\u0006\u0002\u00104J\u0014\u0010i\u001a\b\u0012\u0004\u0012\u00020j0\u0003H§@¢\u0006\u0002\u00104J\u0014\u0010k\u001a\b\u0012\u0004\u0012\u00020l0\u0003H§@¢\u0006\u0002\u00104JP\u0010m\u001a\b\u0012\u0004\u0012\u00020n0\u00032\n\b\u0003\u0010o\u001a\u0004\u0018\u00010\u001c2\n\b\u0003\u0010Q\u001a\u0004\u0018\u00010\u001c2\n\b\u0003\u0010p\u001a\u0004\u0018\u00010\u001c2\n\b\u0003\u0010q\u001a\u0004\u0018\u00010\u001c2\n\b\u0003\u0010\"\u001a\u0004\u0018\u00010\u001cH§@¢\u0006\u0002\u0010rJ\u001e\u0010s\u001a\b\u0012\u0004\u0012\u00020t0\u00032\b\b\u0001\u0010u\u001a\u00020vH§@¢\u0006\u0002\u0010wJ\u001e\u0010x\u001a\b\u0012\u0004\u0012\u00020y0\u00032\b\b\u0001\u0010z\u001a\u00020\u001cH§@¢\u0006\u0002\u0010,J\u0014\u0010{\u001a\b\u0012\u0004\u0012\u00020|0\u0003H§@¢\u0006\u0002\u00104J\u001e\u0010}\u001a\b\u0012\u0004\u0012\u00020~0\u00032\b\b\u0001\u0010\u007f\u001a\u00020\u001cH§@¢\u0006\u0002\u0010,J!\u0010\u0080\u0001\u001a\t\u0012\u0005\u0012\u00030\u0081\u00010\u00032\t\b\u0001\u0010\u0082\u0001\u001a\u00020\u001cH§@¢\u0006\u0002\u0010,J \u0010\u0083\u0001\u001a\t\u0012\u0005\u0012\u00030\u0084\u00010\u00032\b\b\u0001\u0010S\u001a\u00020\u001cH§@¢\u0006\u0002\u0010,J#\u0010\u0085\u0001\u001a\t\u0012\u0005\u0012\u00030\u0086\u00010\u00032\n\b\u0001\u0010\u0085\u0001\u001a\u00030\u0087\u0001H§@¢\u0006\u0003\u0010\u0088\u0001J \u0010\u0089\u0001\u001a\t\u0012\u0005\u0012\u00030\u008a\u00010\u00032\b\b\u0001\u0010/\u001a\u00020'H§@¢\u0006\u0002\u0010]J!\u0010\u008b\u0001\u001a\t\u0012\u0005\u0012\u00030\u008c\u00010\u00032\t\b\u0001\u0010\u008d\u0001\u001a\u00020'H§@¢\u0006\u0002\u0010]¨\u0006\u008e\u0001"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/network/SoService;", "", FirebaseAnalytics.Event.LOGIN, "Lretrofit2/Response;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LoginResponse;", "loginRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/LoginRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "forgetPassword", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;", "forgetRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/ForgetRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/ForgetRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyOtp", "verifyOtpRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/VerifyOtpRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/VerifyOtpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetPassword", "resetPasswordRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/ResetPasswordRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/ResetPasswordRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registration", "registrationRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/RegistrationRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/RegistrationRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "home", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/HomeResponse;", "currentDate", "", "latitude", "longitude", "priority", "distance", "customerType", "addedBy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getJobStatus", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/OnlineStatusResponse;", "online", "", "(ILjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "visitDetails", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitDetailsResponse;", Constant.VISIT_ID, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "productsList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ProductListResponse;", "customerId", "approvedVisits", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ApprovedVisitsResponse;", Scopes.PROFILE, "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ProfileResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addCustomer", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AddCustomerResponse;", "addCustomerRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddCustomerRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddCustomerRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "locationCheck", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LocationCheckResponse;", "locationCheckRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/LocationCheckRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/LocationCheckRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "imageCheck", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ImageCheckResponse;", "visitId", "Lokhttp3/RequestBody;", "image", "Lokhttp3/MultipartBody$Part;", "(Lokhttp3/RequestBody;Lokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "visitCompletion", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitCompletionResponse;", "remarks", "invoiceType", "signature", "images", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "invoice", "(Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/MultipartBody$Part;Ljava/util/ArrayList;Lokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProductList", FirebaseAnalytics.Event.SEARCH, "page", "subjectId", "brandId", "seriesId", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendMessage", "sendMessageRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/SendMessageRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/SendMessageRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMsgList", "Lcom/ingenious/androidbookmarksalesupgrade/model/MessageListResponse;", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addVisit", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AddVisitResponse;", "addVisitRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddVisitRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddVisitRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchCustomer", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/SearchCustomerResponse;", "lastVisitCustomer", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LastVisitCustomerResponse;", "lowStockInventory", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LowStockInventoryResponse;", "stockSummary", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/StockSummaryResponse;", "booksBySegment", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/BooksBySegmentResponse;", "customersList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/CustomersListResponse;", "type", "area", "lastVisit", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createFill", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/CreateProductRefillResponse;", "createProductRefill", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/CreateProductRefillRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/CreateProductRefillRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refillByStatus", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/RefillByStatusResponse;", NotificationCompat.CATEGORY_STATUS, "segmentsList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/SegmentsListResponse;", "gradesList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GradesListResponse;", "segmentId", "subjectsList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/SubjectsListResponse;", "gradeId", "adoptionBooksList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionBooksResponse;", "addAdoption", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AddAdoptionResponse;", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddAdoptionRequest;", "(Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddAdoptionRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "adoption", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionListResponse;", "adoptionDetails", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionDetailsResponse;", "adoptionId", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes13.dex */
public interface SoService {
    @POST(Routes.ADD_ADOPTION)
    Object addAdoption(@Body AddAdoptionRequest addAdoptionRequest, Continuation<? super Response<AddAdoptionResponse>> continuation);

    @POST(Routes.ADD_CUSTOMER)
    Object addCustomer(@Body AddCustomerRequest addCustomerRequest, Continuation<? super Response<AddCustomerResponse>> continuation);

    @POST(Routes.ADD_VISIT)
    Object addVisit(@Body AddVisitRequest addVisitRequest, Continuation<? super Response<AddVisitResponse>> continuation);

    @GET(Routes.ADOPTION_LIST)
    Object adoption(@Query("id") int i, Continuation<? super Response<AdoptionListResponse>> continuation);

    @GET(Routes.ADOPTION_BOOKS_LIST)
    Object adoptionBooksList(@Query(encoded = true, value = "subject_id") String str, Continuation<? super Response<AdoptionBooksResponse>> continuation);

    @GET(Routes.ADOPTION_DETAILS)
    Object adoptionDetails(@Query("id") int i, Continuation<? super Response<AdoptionDetailsResponse>> continuation);

    @GET(Routes.APPROVED_VISITS)
    Object approvedVisits(@Query("customerId") String str, Continuation<? super Response<ApprovedVisitsResponse>> continuation);

    @GET(Routes.BOOKS_BY_SEGMENT)
    Object booksBySegment(Continuation<? super Response<BooksBySegmentResponse>> continuation);

    @POST(Routes.BOOKS_BY_CREATE_REFILL)
    Object createFill(@Body CreateProductRefillRequest createProductRefillRequest, Continuation<? super Response<CreateProductRefillResponse>> continuation);

    @GET(Routes.CUSTOMERS_LIST)
    Object customersList(@Query("type") String str, @Query("search") String str2, @Query("area") String str3, @Query("lastVisit") String str4, @Query("addedBy") String str5, Continuation<? super Response<CustomersListResponse>> continuation);

    @POST(Routes.FORGET)
    Object forgetPassword(@Body ForgetRequest forgetRequest, Continuation<? super Response<GlobalResponse>> continuation);

    @POST(Routes.JOB_STATUS)
    Object getJobStatus(@Query("status") int i, @Query("latitude") String str, @Query("longitude") String str2, Continuation<? super Response<OnlineStatusResponse>> continuation);

    @GET(Routes.LIST_MESSAGE)
    Object getMsgList(@Query("page") int i, Continuation<? super Response<MessageListResponse>> continuation);

    @GET("api/getProductList")
    Object getProductList(@Query("search") String str, @Query("page") int i, @Query("customerId") String str2, @Query("subject_id") String str3, @Query("series_id") String str4, @Query("brand_id") String str5, Continuation<? super Response<ProductListResponse>> continuation);

    @GET(Routes.GRADES_LIST)
    Object gradesList(@Query(encoded = true, value = "segment_id") String str, Continuation<? super Response<GradesListResponse>> continuation);

    @GET(Routes.HOME)
    Object home(@Query("date") String str, @Query("longitude") String str2, @Query("latitude") String str3, @Query("priority") String str4, @Query("distance") String str5, @Query("customer_type") String str6, @Query("added_by") String str7, Continuation<? super Response<HomeResponse>> continuation);

    @POST(Routes.IMAGE_CHECK)
    @Multipart
    Object imageCheck(@Part("visitId") RequestBody requestBody, @Part MultipartBody.Part part, Continuation<? super Response<ImageCheckResponse>> continuation);

    @GET(Routes.LAST_VISIT_CUSTOMER)
    Object lastVisitCustomer(@Query("customer_id") String str, Continuation<? super Response<LastVisitCustomerResponse>> continuation);

    @POST(Routes.LOCATION_CHECK)
    Object locationCheck(@Body LocationCheckRequest locationCheckRequest, Continuation<? super Response<LocationCheckResponse>> continuation);

    @POST(Routes.LOGIN)
    Object login(@Body LoginRequest loginRequest, Continuation<? super Response<LoginResponse>> continuation);

    @GET(Routes.LOW_STOCK_INVENTORY)
    Object lowStockInventory(Continuation<? super Response<LowStockInventoryResponse>> continuation);

    @GET("api/getProductList")
    Object productsList(@Query("customerId") String str, Continuation<? super Response<ProductListResponse>> continuation);

    @GET(Routes.PROFILE)
    Object profile(Continuation<? super Response<ProfileResponse>> continuation);

    @GET(Routes.REFILL_BY_STATUS)
    Object refillByStatus(@Query("status") String str, Continuation<? super Response<RefillByStatusResponse>> continuation);

    @POST(Routes.REGISTRATION)
    Object registration(@Body RegistrationRequest registrationRequest, Continuation<? super LoginResponse> continuation);

    @POST(Routes.RESET_PASSWORD)
    Object resetPassword(@Body ResetPasswordRequest resetPasswordRequest, Continuation<? super Response<GlobalResponse>> continuation);

    @GET(Routes.SEARCH_CUSTOMER)
    Object searchCustomer(@Query("search") String str, Continuation<? super Response<SearchCustomerResponse>> continuation);

    @GET(Routes.SEGMENTS_LIST)
    Object segmentsList(Continuation<? super Response<SegmentsListResponse>> continuation);

    @POST(Routes.SEND_MESSAGE)
    Object sendMessage(@Body SendMessageRequest sendMessageRequest, Continuation<? super Response<GlobalResponse>> continuation);

    @GET(Routes.STOCK_SUMMARY)
    Object stockSummary(Continuation<? super Response<StockSummaryResponse>> continuation);

    @GET(Routes.SUBJECTS_LIST)
    Object subjectsList(@Query(encoded = true, value = "grade_id") String str, Continuation<? super Response<SubjectsListResponse>> continuation);

    @POST(Routes.VERIFY_OTP)
    Object verifyOtp(@Body VerifyOtpRequest verifyOtpRequest, Continuation<? super Response<GlobalResponse>> continuation);

    @POST(Routes.VISIT_COMPLETION)
    @Multipart
    Object visitCompletion(@Part("visitId") RequestBody requestBody, @Part("remarks") RequestBody requestBody2, @Part("invoice_type") RequestBody requestBody3, @Part MultipartBody.Part part, @Part ArrayList<MultipartBody.Part> arrayList, @Part MultipartBody.Part part2, Continuation<? super Response<VisitCompletionResponse>> continuation);

    @GET(Routes.VISIT_DETAILS)
    Object visitDetails(@Query("id") String str, Continuation<? super Response<VisitDetailsResponse>> continuation);

    /* compiled from: SoService.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        public static /* synthetic */ Object home$default(SoService soService, String str, String str2, String str3, String str4, String str5, String str6, String str7, Continuation continuation, int i, Object obj) {
            if (obj == null) {
                return soService.home((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : str7, continuation);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: home");
        }

        public static /* synthetic */ Object imageCheck$default(SoService soService, RequestBody requestBody, MultipartBody.Part part, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: imageCheck");
            }
            if ((i & 2) != 0) {
                part = null;
            }
            return soService.imageCheck(requestBody, part, continuation);
        }

        public static /* synthetic */ Object visitCompletion$default(SoService soService, RequestBody requestBody, RequestBody requestBody2, RequestBody requestBody3, MultipartBody.Part part, ArrayList arrayList, MultipartBody.Part part2, Continuation continuation, int i, Object obj) {
            MultipartBody.Part part3;
            ArrayList arrayList2;
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: visitCompletion");
            }
            if ((i & 8) == 0) {
                part3 = part;
            } else {
                part3 = null;
            }
            if ((i & 16) == 0) {
                arrayList2 = arrayList;
            } else {
                arrayList2 = null;
            }
            return soService.visitCompletion(requestBody, requestBody2, requestBody3, part3, arrayList2, part2, continuation);
        }

        public static /* synthetic */ Object getProductList$default(SoService soService, String str, int i, String str2, String str3, String str4, String str5, Continuation continuation, int i2, Object obj) {
            if (obj == null) {
                return soService.getProductList(str, i, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4, (i2 & 32) != 0 ? null : str5, continuation);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getProductList");
        }

        public static /* synthetic */ Object customersList$default(SoService soService, String str, String str2, String str3, String str4, String str5, Continuation continuation, int i, Object obj) {
            if (obj == null) {
                return soService.customersList((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, continuation);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: customersList");
        }
    }
}
