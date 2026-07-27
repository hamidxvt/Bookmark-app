package com.ingenious.androidbookmarksalesupgrade.viewModel;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils;
import com.ingenious.androidbookmarksalesupgrade.model.request.AddCustomerRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.AddVisitRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.LocationCheckRequest;
import com.ingenious.androidbookmarksalesupgrade.model.response.AddCustomerResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.AddVisitResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.ApprovedVisitsResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.CustomersListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.ImageCheckResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.LastVisitCustomerResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.LocationCheckResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.ProductListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.SearchCustomerResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitCompletionResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitDetailsResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.repository.AppRepository;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/* compiled from: VisitViewModel.kt */
@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0011J\u000e\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0011J\u000e\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020#J\u000e\u0010(\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020*J\u001a\u0010/\u001a\u00020\u000f2\u0006\u00100\u001a\u0002012\n\b\u0002\u00102\u001a\u0004\u0018\u000103JT\u00108\u001a\u00020\u000f2\u0006\u00100\u001a\u0002012\u0006\u00109\u001a\u0002012\u0006\u0010:\u001a\u0002012\n\b\u0002\u0010;\u001a\u0004\u0018\u0001032\u001c\b\u0002\u0010<\u001a\u0016\u0012\u0004\u0012\u000203\u0018\u00010=j\n\u0012\u0004\u0012\u000203\u0018\u0001`>2\n\b\u0002\u0010?\u001a\u0004\u0018\u000103J@\u0010C\u001a\u00020\u000f2\b\b\u0002\u0010D\u001a\u00020\u00112\u0006\u0010E\u001a\u00020F2\b\b\u0002\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010G\u001a\u00020\u00112\b\b\u0002\u0010H\u001a\u00020\u00112\b\b\u0002\u0010I\u001a\u00020\u0011J\u000e\u0010N\u001a\u00020\u000f2\u0006\u0010D\u001a\u00020\u0011J\u000e\u0010S\u001a\u00020\u000f2\u0006\u0010T\u001a\u00020UJ\u000e\u0010Z\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0011JB\u0010_\u001a\u00020\u000f2\n\b\u0002\u0010`\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010a\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010b\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010c\u001a\u0004\u0018\u00010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\b0\u000b8F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\rR\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\b0\u000b8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\rR\u001a\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\b0\u000b8F¢\u0006\u0006\u001a\u0004\b \u0010\rR\u001a\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b0\u000b8F¢\u0006\u0006\u001a\u0004\b'\u0010\rR\u001a\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0\b0\u000b8F¢\u0006\u0006\u001a\u0004\b.\u0010\rR\u001a\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002050\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002050\b0\u000b8F¢\u0006\u0006\u001a\u0004\b7\u0010\rR\u001a\u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010A\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\b0\u000b8F¢\u0006\u0006\u001a\u0004\bB\u0010\rR\u001a\u0010J\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020K0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010L\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020K0\b0\u000b8F¢\u0006\u0006\u001a\u0004\bM\u0010\rR\u001a\u0010O\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020P0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010Q\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020P0\b0\u000b8F¢\u0006\u0006\u001a\u0004\bR\u0010\rR\u001a\u0010V\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020W0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010X\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020W0\b0\u000b8F¢\u0006\u0006\u001a\u0004\bY\u0010\rR\u001a\u0010[\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\\0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010]\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\\0\b0\u000b8F¢\u0006\u0006\u001a\u0004\b^\u0010\r¨\u0006d"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "repository", "Lcom/ingenious/androidbookmarksalesupgrade/repository/AppRepository;", "_visitResponse", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitDetailsResponse;", "visitResponse", "Landroidx/lifecycle/LiveData;", "getVisitResponse", "()Landroidx/lifecycle/LiveData;", "visitDetails", "", Constant.VISIT_ID, "", "_productsListResponse", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ProductListResponse;", "productsListResponse", "getProductsListResponse", "productsList", "customerId", "_approvedVisitsResponse", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ApprovedVisitsResponse;", "approvedVisitsResponse", "getApprovedVisitsResponse", "approvedVisits", "_addCustomerResponse", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AddCustomerResponse;", "addCustomerResponse", "getAddCustomerResponse", "addCustomer", "addCustomerRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddCustomerRequest;", "_locationCheckResponse", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LocationCheckResponse;", "locationCheckResponse", "getLocationCheckResponse", "locationCheck", "locationCheckRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/LocationCheckRequest;", "_imageCheckResponse", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ImageCheckResponse;", "imageCheckResponse", "getImageCheckResponse", "imageCheck", "visitId", "Lokhttp3/RequestBody;", "image", "Lokhttp3/MultipartBody$Part;", "_visitCompletionResponse", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/VisitCompletionResponse;", "visitCompletionResponse", "getVisitCompletionResponse", "visitCompletion", "remarks", "invoiceType", "signature", "images", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "invoice", "_productListResponse", "productListResponse", "getProductListResponse", "productListRequest", FirebaseAnalytics.Event.SEARCH, "page", "", "subjectId", "brandId", "seriesId", "_searchCustomerResponse", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/SearchCustomerResponse;", "searchCustomerResponse", "getSearchCustomerResponse", "searchCustomer", "_addVisitResponse", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AddVisitResponse;", "addVisitResponse", "getAddVisitResponse", "addVisit", "addVisitRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddVisitRequest;", "_lastVisitCustomerResponse", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/LastVisitCustomerResponse;", "lastVisitCustomerResponse", "getLastVisitCustomerResponse", "lastVisitCustomer", "_customersListResponse", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/CustomersListResponse;", "customersListResponse", "getCustomersListResponse", "customersList", "type", "area", "lastVisit", "addedBy", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class VisitViewModel extends ViewModel {
    private final AppRepository repository = InjectUtils.INSTANCE.getAppRepository();
    private final MutableLiveData<ApiResponseCallback<VisitDetailsResponse>> _visitResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<ProductListResponse>> _productsListResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<ApprovedVisitsResponse>> _approvedVisitsResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<AddCustomerResponse>> _addCustomerResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<LocationCheckResponse>> _locationCheckResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<ImageCheckResponse>> _imageCheckResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<VisitCompletionResponse>> _visitCompletionResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<ProductListResponse>> _productListResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<SearchCustomerResponse>> _searchCustomerResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<AddVisitResponse>> _addVisitResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<LastVisitCustomerResponse>> _lastVisitCustomerResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<CustomersListResponse>> _customersListResponse = new MutableLiveData<>();

    public final LiveData<ApiResponseCallback<VisitDetailsResponse>> getVisitResponse() {
        return this._visitResponse;
    }

    public final void visitDetails(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this._visitResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new VisitViewModel$visitDetails$1(this, id, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<ProductListResponse>> getProductsListResponse() {
        return this._productsListResponse;
    }

    public final void productsList(String customerId) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        this._productsListResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new VisitViewModel$productsList$1(this, customerId, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<ApprovedVisitsResponse>> getApprovedVisitsResponse() {
        return this._approvedVisitsResponse;
    }

    public final void approvedVisits(String customerId) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        this._approvedVisitsResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new VisitViewModel$approvedVisits$1(this, customerId, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<AddCustomerResponse>> getAddCustomerResponse() {
        return this._addCustomerResponse;
    }

    public final void addCustomer(AddCustomerRequest addCustomerRequest) {
        Intrinsics.checkNotNullParameter(addCustomerRequest, "addCustomerRequest");
        this._addCustomerResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new VisitViewModel$addCustomer$1(this, addCustomerRequest, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<LocationCheckResponse>> getLocationCheckResponse() {
        return this._locationCheckResponse;
    }

    public final void locationCheck(LocationCheckRequest locationCheckRequest) {
        Intrinsics.checkNotNullParameter(locationCheckRequest, "locationCheckRequest");
        this._locationCheckResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new VisitViewModel$locationCheck$1(this, locationCheckRequest, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<ImageCheckResponse>> getImageCheckResponse() {
        return this._imageCheckResponse;
    }

    public static /* synthetic */ void imageCheck$default(VisitViewModel visitViewModel, RequestBody requestBody, MultipartBody.Part part, int i, Object obj) {
        if ((i & 2) != 0) {
            part = null;
        }
        visitViewModel.imageCheck(requestBody, part);
    }

    public final void imageCheck(RequestBody visitId, MultipartBody.Part image) {
        Intrinsics.checkNotNullParameter(visitId, "visitId");
        this._imageCheckResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new VisitViewModel$imageCheck$1(this, visitId, image, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<VisitCompletionResponse>> getVisitCompletionResponse() {
        return this._visitCompletionResponse;
    }

    public final void visitCompletion(RequestBody visitId, RequestBody remarks, RequestBody invoiceType, MultipartBody.Part signature, ArrayList<MultipartBody.Part> images, MultipartBody.Part invoice) {
        Intrinsics.checkNotNullParameter(visitId, "visitId");
        Intrinsics.checkNotNullParameter(remarks, "remarks");
        Intrinsics.checkNotNullParameter(invoiceType, "invoiceType");
        this._visitCompletionResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new VisitViewModel$visitCompletion$1(this, visitId, remarks, invoiceType, signature, images, invoice, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<ProductListResponse>> getProductListResponse() {
        return this._productListResponse;
    }

    public final void productListRequest(String search, int page, String customerId, String subjectId, String brandId, String seriesId) {
        Intrinsics.checkNotNullParameter(search, "search");
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(subjectId, "subjectId");
        Intrinsics.checkNotNullParameter(brandId, "brandId");
        Intrinsics.checkNotNullParameter(seriesId, "seriesId");
        this._productListResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new VisitViewModel$productListRequest$1(this, search, page, customerId, subjectId, brandId, seriesId, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<SearchCustomerResponse>> getSearchCustomerResponse() {
        return this._searchCustomerResponse;
    }

    public final void searchCustomer(String search) {
        Intrinsics.checkNotNullParameter(search, "search");
        this._searchCustomerResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new VisitViewModel$searchCustomer$1(this, search, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<AddVisitResponse>> getAddVisitResponse() {
        return this._addVisitResponse;
    }

    public final void addVisit(AddVisitRequest addVisitRequest) {
        Intrinsics.checkNotNullParameter(addVisitRequest, "addVisitRequest");
        this._addVisitResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new VisitViewModel$addVisit$1(this, addVisitRequest, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<LastVisitCustomerResponse>> getLastVisitCustomerResponse() {
        return this._lastVisitCustomerResponse;
    }

    public final void lastVisitCustomer(String customerId) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        this._lastVisitCustomerResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new VisitViewModel$lastVisitCustomer$1(this, customerId, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<CustomersListResponse>> getCustomersListResponse() {
        return this._customersListResponse;
    }

    public static /* synthetic */ void customersList$default(VisitViewModel visitViewModel, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            str3 = null;
        }
        if ((i & 8) != 0) {
            str4 = null;
        }
        if ((i & 16) != 0) {
            str5 = null;
        }
        visitViewModel.customersList(str, str2, str3, str4, str5);
    }

    public final void customersList(String type, String search, String area, String lastVisit, String addedBy) {
        this._customersListResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new VisitViewModel$customersList$1(this, type, search, area, lastVisit, addedBy, null), 3, null);
    }
}
