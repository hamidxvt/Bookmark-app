package com.ingenious.androidbookmarksalesupgrade.viewModel;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils;
import com.ingenious.androidbookmarksalesupgrade.model.request.AddAdoptionRequest;
import com.ingenious.androidbookmarksalesupgrade.model.request.CreateProductRefillRequest;
import com.ingenious.androidbookmarksalesupgrade.model.response.AddAdoptionResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooksResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionDetailsResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.CreateProductRefillResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.GradesListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.HomeResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.OnlineStatusResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.RefillByStatusResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.SegmentsListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.SubjectsListResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.repository.AppRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: MainViewModel.kt */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JZ\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0011J\u001e\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011J\u000e\u0010#\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020$J\u000e\u0010)\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u0011J\u0006\u0010,\u001a\u00020\u000fJ\u000e\u00100\u001a\u00020\u000f2\u0006\u00102\u001a\u00020\u0011J\u000e\u00105\u001a\u00020\u000f2\u0006\u00107\u001a\u00020\u0011J\u000e\u0010:\u001a\u00020\u000f2\u0006\u0010<\u001a\u00020\u0011J\u000e\u0010?\u001a\u00020\u000f2\u0006\u0010?\u001a\u00020AJ\u000e\u0010D\u001a\u00020\u000f2\u0006\u0010F\u001a\u00020GJ\u000e\u0010J\u001a\u00020\u000f2\u0006\u0010L\u001a\u00020GR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\b0\u000b8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\rR\u001a\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\b0\u000b8F¢\u0006\u0006\u001a\u0004\b\"\u0010\rR\u001a\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\b0\u000b8F¢\u0006\u0006\u001a\u0004\b(\u0010\rR\u001a\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\b0\u000b8F¢\u0006\u0006\u001a\u0004\b-\u0010\rR\u001a\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0\b0\u000b8F¢\u0006\u0006\u001a\u0004\b1\u0010\rR\u001a\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002040\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002040\b0\u000b8F¢\u0006\u0006\u001a\u0004\b6\u0010\rR\u001a\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002090\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002090\b0\u000b8F¢\u0006\u0006\u001a\u0004\b;\u0010\rR\u001a\u0010=\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020>0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020>0\b0\u000b8F¢\u0006\u0006\u001a\u0004\b@\u0010\rR\u001a\u0010B\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020C0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010D\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020C0\b0\u000b8F¢\u0006\u0006\u001a\u0004\bE\u0010\rR\u001a\u0010H\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010J\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0\b0\u000b8F¢\u0006\u0006\u001a\u0004\bK\u0010\r¨\u0006M"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/viewModel/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "repository", "Lcom/ingenious/androidbookmarksalesupgrade/repository/AppRepository;", "_homeResponse", "Landroidx/lifecycle/MutableLiveData;", "Lcom/ingenious/androidbookmarksalesupgrade/network/ApiResponseCallback;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/HomeResponse;", "homeResponse", "Landroidx/lifecycle/LiveData;", "getHomeResponse", "()Landroidx/lifecycle/LiveData;", "homeRequest", "", "currentDate", "", "latitude", "longitude", "priority", "distance", "customerType", "addedBy", "_jobStatusResponse", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/OnlineStatusResponse;", "jobStatusResponse", "getJobStatusResponse", "jobStatusRequest", NotificationCompat.CATEGORY_STATUS, "", "_createProductRefill", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/CreateProductRefillResponse;", "createProductRefillResponse", "getCreateProductRefillResponse", "createProductRefillRequest", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/CreateProductRefillRequest;", "_refillByStatus", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/RefillByStatusResponse;", "refillResponse", "getRefillResponse", "refillByStatusRequest", "_segmentsList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/SegmentsListResponse;", "segmentsList", "getSegmentsList", "_gradesList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GradesListResponse;", "gradesList", "getGradesList", "segmentId", "_subjectsList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/SubjectsListResponse;", "subjectsList", "getSubjectsList", "gradesId", "_adoptionBooksList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionBooksResponse;", "adoptionBooksList", "getAdoptionBooksList", "subjectId", "_addAdoption", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AddAdoptionResponse;", "addAdoption", "getAddAdoption", "Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddAdoptionRequest;", "_adoption", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionListResponse;", "adoption", "getAdoption", "customerId", "", "_adoptionDetails", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionDetailsResponse;", "adoptionDetails", "getAdoptionDetails", "adoptionId", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class MainViewModel extends ViewModel {
    private final AppRepository repository = InjectUtils.INSTANCE.getAppRepository();
    private final MutableLiveData<ApiResponseCallback<HomeResponse>> _homeResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<OnlineStatusResponse>> _jobStatusResponse = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<CreateProductRefillResponse>> _createProductRefill = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<RefillByStatusResponse>> _refillByStatus = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<SegmentsListResponse>> _segmentsList = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<GradesListResponse>> _gradesList = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<SubjectsListResponse>> _subjectsList = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<AdoptionBooksResponse>> _adoptionBooksList = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<AddAdoptionResponse>> _addAdoption = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<AdoptionListResponse>> _adoption = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseCallback<AdoptionDetailsResponse>> _adoptionDetails = new MutableLiveData<>();

    public final LiveData<ApiResponseCallback<HomeResponse>> getHomeResponse() {
        return this._homeResponse;
    }

    public static /* synthetic */ void homeRequest$default(MainViewModel mainViewModel, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
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
        if ((i & 32) != 0) {
            str6 = null;
        }
        if ((i & 64) != 0) {
            str7 = null;
        }
        mainViewModel.homeRequest(str, str2, str3, str4, str5, str6, str7);
    }

    public final void homeRequest(String currentDate, String latitude, String longitude, String priority, String distance, String customerType, String addedBy) {
        this._homeResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$homeRequest$1(this, currentDate, latitude, longitude, priority, distance, customerType, addedBy, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<OnlineStatusResponse>> getJobStatusResponse() {
        return this._jobStatusResponse;
    }

    public final void jobStatusRequest(boolean status, String latitude, String longitude) {
        Intrinsics.checkNotNullParameter(latitude, "latitude");
        Intrinsics.checkNotNullParameter(longitude, "longitude");
        this._jobStatusResponse.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$jobStatusRequest$1(this, status, latitude, longitude, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<CreateProductRefillResponse>> getCreateProductRefillResponse() {
        return this._createProductRefill;
    }

    public final void createProductRefillRequest(CreateProductRefillRequest createProductRefillRequest) {
        Intrinsics.checkNotNullParameter(createProductRefillRequest, "createProductRefillRequest");
        this._createProductRefill.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$createProductRefillRequest$1(this, createProductRefillRequest, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<RefillByStatusResponse>> getRefillResponse() {
        return this._refillByStatus;
    }

    public final void refillByStatusRequest(String status) {
        Intrinsics.checkNotNullParameter(status, "status");
        this._refillByStatus.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$refillByStatusRequest$1(this, status, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<SegmentsListResponse>> getSegmentsList() {
        return this._segmentsList;
    }

    public final void segmentsList() {
        this._segmentsList.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$segmentsList$1(this, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<GradesListResponse>> getGradesList() {
        return this._gradesList;
    }

    public final void gradesList(String segmentId) {
        Intrinsics.checkNotNullParameter(segmentId, "segmentId");
        this._gradesList.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$gradesList$1(this, segmentId, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<SubjectsListResponse>> getSubjectsList() {
        return this._subjectsList;
    }

    public final void subjectsList(String gradesId) {
        Intrinsics.checkNotNullParameter(gradesId, "gradesId");
        this._subjectsList.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$subjectsList$1(this, gradesId, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<AdoptionBooksResponse>> getAdoptionBooksList() {
        return this._adoptionBooksList;
    }

    public final void adoptionBooksList(String subjectId) {
        Intrinsics.checkNotNullParameter(subjectId, "subjectId");
        this._adoptionBooksList.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$adoptionBooksList$1(this, subjectId, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<AddAdoptionResponse>> getAddAdoption() {
        return this._addAdoption;
    }

    public final void addAdoption(AddAdoptionRequest addAdoption) {
        Intrinsics.checkNotNullParameter(addAdoption, "addAdoption");
        this._addAdoption.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$addAdoption$1(this, addAdoption, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<AdoptionListResponse>> getAdoption() {
        return this._adoption;
    }

    public final void adoption(int customerId) {
        this._adoption.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$adoption$1(this, customerId, null), 3, null);
    }

    public final LiveData<ApiResponseCallback<AdoptionDetailsResponse>> getAdoptionDetails() {
        return this._adoptionDetails;
    }

    public final void adoptionDetails(int adoptionId) {
        this._adoptionDetails.setValue(new ApiResponseCallback.Loading());
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$adoptionDetails$1(this, adoptionId, null), 3, null);
    }
}
