package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.savedstate.SavedStateRegistryOwner;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.AttachmentAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentCompleteVisitBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.FileExtKt;
import com.ingenious.androidbookmarksalesupgrade.extensions.PermissionsExtKt;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitCompletionResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import okhttp3.MultipartBody;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: CompleteVisitFragment.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u0014H\u0002J\u001a\u00107\u001a\u0002052\u0006\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\b\u0010<\u001a\u000205H\u0002J\b\u0010=\u001a\u000205H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR.\u0010\t\u001a\u001c\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R(\u0010(\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040*\u0018\u00010)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0016\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00040*X\u0082\u0004¢\u0006\u0004\n\u0002\u00100R\u001c\u00101\u001a\u0010\u0012\f\u0012\n 3*\u0004\u0018\u000102020)X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/CompleteVisitFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/BaseFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentCompleteVisitBinding;", "visitId", "", "<init>", "(Ljava/lang/String;)V", "getVisitId", "()Ljava/lang/String;", "bindingInflater", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "", "getBindingInflater", "()Lkotlin/jvm/functions/Function3;", "attachmentAdapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/AttachmentAdapter;", "pictureUriList", "Ljava/util/ArrayList;", "Landroid/net/Uri;", "Lkotlin/collections/ArrayList;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "imageBodySignature", "Lokhttp3/MultipartBody$Part;", "getImageBodySignature", "()Lokhttp3/MultipartBody$Part;", "setImageBodySignature", "(Lokhttp3/MultipartBody$Part;)V", "IMAGE_PICKER_REQUEST_CODE", "", "getIMAGE_PICKER_REQUEST_CODE", "()I", "setIMAGE_PICKER_REQUEST_CODE", "(I)V", "readWritePermReqLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "getReadWritePermReqLauncher", "()Landroidx/activity/result/ActivityResultLauncher;", "setReadWritePermReqLauncher", "(Landroidx/activity/result/ActivityResultLauncher;)V", "readWritePermissionsLists", "[Ljava/lang/String;", "filePickerLauncher", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "handleURI", "", "uri", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setAttachmentAdapter", "pickImageFiles", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class CompleteVisitFragment extends BaseFragment<FragmentCompleteVisitBinding> {
    private int IMAGE_PICKER_REQUEST_CODE;
    private AttachmentAdapter attachmentAdapter;
    private final ActivityResultLauncher<Intent> filePickerLauncher;
    private MultipartBody.Part imageBodySignature;
    private ArrayList<Uri> pictureUriList;
    private ActivityResultLauncher<String[]> readWritePermReqLauncher;
    private final String[] readWritePermissionsLists;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private final String visitId;

    public CompleteVisitFragment(String visitId) {
        Intrinsics.checkNotNullParameter(visitId, "visitId");
        this.visitId = visitId;
        this.pictureUriList = new ArrayList<>();
        final CompleteVisitFragment $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CompleteVisitFragment$special$$inlined$viewModel$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelOwner invoke() {
                ViewModelOwner.Companion companion = ViewModelOwner.INSTANCE;
                Fragment fragment = Fragment.this;
                Fragment fragment2 = Fragment.this;
                return companion.from(fragment, fragment2 instanceof SavedStateRegistryOwner ? fragment2 : null);
            }
        };
        final Function0 parameters$iv = null;
        final Scope scope$iv = AndroidKoinScopeExtKt.getKoinScope($this$viewModel_u24default$iv);
        final Function0 ownerProducer$iv$iv = new Function0<ViewModelStoreOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CompleteVisitFragment$special$$inlined$viewModel$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return ((ViewModelOwner) Function0.this.invoke()).getStoreOwner();
            }
        };
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CompleteVisitFragment$special$$inlined$viewModel$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                Function0 owner$iv2 = Function0.this;
                Qualifier qualifier$iv2 = qualifier$iv;
                Function0 parameters$iv2 = parameters$iv;
                Scope scope$iv2 = scope$iv;
                ViewModelOwner ownerValue$iv = (ViewModelOwner) owner$iv2.invoke();
                ViewModelParameter viewModelParameters$iv = new ViewModelParameter(Reflection.getOrCreateKotlinClass(VisitViewModel.class), qualifier$iv2, null, parameters$iv2, ownerValue$iv.getStoreOwner(), ownerValue$iv.getStateRegistry());
                return ViewModelResolverKt.pickFactory(scope$iv2, viewModelParameters$iv);
            }
        };
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy($this$viewModel_u24default$iv, Reflection.getOrCreateKotlinClass(VisitViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CompleteVisitFragment$special$$inlined$viewModel$default$4
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) Function0.this.invoke()).getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "ownerProducer().viewModelStore");
                return viewModelStore;
            }
        }, factoryProducer$iv$iv);
        this.IMAGE_PICKER_REQUEST_CODE = 1001;
        this.readWritePermissionsLists = Build.VERSION.SDK_INT >= 33 ? new String[]{"android.permission.READ_MEDIA_IMAGES"} : new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CompleteVisitFragment$$ExternalSyntheticLambda3
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                CompleteVisitFragment.filePickerLauncher$lambda$1(CompleteVisitFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.filePickerLauncher = registerForActivityResult;
    }

    public final String getVisitId() {
        return this.visitId;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment
    public Function3<LayoutInflater, ViewGroup, Boolean, FragmentCompleteVisitBinding> getBindingInflater() {
        return CompleteVisitFragment$bindingInflater$1.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VisitViewModel getViewModel() {
        return (VisitViewModel) this.viewModel.getValue();
    }

    public final MultipartBody.Part getImageBodySignature() {
        return this.imageBodySignature;
    }

    public final void setImageBodySignature(MultipartBody.Part part) {
        this.imageBodySignature = part;
    }

    public final int getIMAGE_PICKER_REQUEST_CODE() {
        return this.IMAGE_PICKER_REQUEST_CODE;
    }

    public final void setIMAGE_PICKER_REQUEST_CODE(int i) {
        this.IMAGE_PICKER_REQUEST_CODE = i;
    }

    public final ActivityResultLauncher<String[]> getReadWritePermReqLauncher() {
        return this.readWritePermReqLauncher;
    }

    public final void setReadWritePermReqLauncher(ActivityResultLauncher<String[]> activityResultLauncher) {
        this.readWritePermReqLauncher = activityResultLauncher;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void filePickerLauncher$lambda$1(CompleteVisitFragment this$0, ActivityResult result) {
        Uri uri;
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.getResultCode() == -1) {
            this$0.pictureUriList.clear();
            Intent data = result.getData();
            ClipData clipData = data != null ? data.getClipData() : null;
            if (clipData != null) {
                int itemCount = clipData.getItemCount();
                for (int i = 0; i < itemCount; i++) {
                    Uri uri2 = clipData.getItemAt(i).getUri();
                    Intrinsics.checkNotNull(uri2);
                    this$0.handleURI(uri2);
                }
                return;
            }
            Intent data2 = result.getData();
            if (data2 != null && (uri = data2.getData()) != null) {
                this$0.handleURI(uri);
            }
        }
    }

    private final void handleURI(Uri uri) {
        DocumentFile documentFile = DocumentFile.fromSingleUri(requireContext(), uri);
        if (documentFile != null && documentFile.isFile()) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
            File imagePath = FileExtKt.getFileFromUri(requireContext, uri);
            if (imagePath != null) {
                this.pictureUriList.add(uri);
                AttachmentAdapter attachmentAdapter = this.attachmentAdapter;
                if (attachmentAdapter != null) {
                    attachmentAdapter.notifyDataSetChanged();
                    return;
                }
                return;
            }
            return;
        }
        Toast.makeText(requireContext(), "Please select a valid Image file", 0).show();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        this.readWritePermReqLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CompleteVisitFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                CompleteVisitFragment.onViewCreated$lambda$3(CompleteVisitFragment.this, (Map) obj);
            }
        });
        getBinding().title.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CompleteVisitFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CompleteVisitFragment.onViewCreated$lambda$4(CompleteVisitFragment.this, view2);
            }
        });
        setAttachmentAdapter();
        getBinding().setListener(new GenericListeners() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CompleteVisitFragment$onViewCreated$3
            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onNotificationClick() {
                GenericListeners.DefaultImpls.onNotificationClick(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onSettingClick() {
                GenericListeners.DefaultImpls.onSettingClick(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapAddBooks() {
                GenericListeners.DefaultImpls.onTapAddBooks(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapAddCustomer() {
                GenericListeners.DefaultImpls.onTapAddCustomer(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapAddHome() {
                GenericListeners.DefaultImpls.onTapAddHome(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapAddVisit() {
                GenericListeners.DefaultImpls.onTapAddVisit(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapBack() {
                GenericListeners.DefaultImpls.onTapBack(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapCheckIn() {
                GenericListeners.DefaultImpls.onTapCheckIn(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapDate() {
                GenericListeners.DefaultImpls.onTapDate(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapDateNext() {
                GenericListeners.DefaultImpls.onTapDateNext(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapDatePrevious() {
                GenericListeners.DefaultImpls.onTapDatePrevious(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapDismiss() {
                GenericListeners.DefaultImpls.onTapDismiss(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapDone() {
                GenericListeners.DefaultImpls.onTapDone(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapFilter() {
                GenericListeners.DefaultImpls.onTapFilter(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapForgetPassword() {
                GenericListeners.DefaultImpls.onTapForgetPassword(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapLocation() {
                GenericListeners.DefaultImpls.onTapLocation(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapLocationFab() {
                GenericListeners.DefaultImpls.onTapLocationFab(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapLogin() {
                GenericListeners.DefaultImpls.onTapLogin(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapLogout() {
                GenericListeners.DefaultImpls.onTapLogout(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapLowStock() {
                GenericListeners.DefaultImpls.onTapLowStock(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapNewAccount() {
                GenericListeners.DefaultImpls.onTapNewAccount(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapOTP() {
                GenericListeners.DefaultImpls.onTapOTP(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapProfile() {
                GenericListeners.DefaultImpls.onTapProfile(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapRefill() {
                GenericListeners.DefaultImpls.onTapRefill(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapRefillRequests() {
                GenericListeners.DefaultImpls.onTapRefillRequests(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapResetPassword() {
                GenericListeners.DefaultImpls.onTapResetPassword(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapSendMessage() {
                GenericListeners.DefaultImpls.onTapSendMessage(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapSettings() {
                GenericListeners.DefaultImpls.onTapSettings(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapSwitch() {
                GenericListeners.DefaultImpls.onTapSwitch(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapViewSelection() {
                GenericListeners.DefaultImpls.onTapViewSelection(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapCaptureImage() {
                String[] strArr;
                String[] strArr2;
                Context requireContext = CompleteVisitFragment.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
                strArr = CompleteVisitFragment.this.readWritePermissionsLists;
                if (PermissionsExtKt.hasPermissions(requireContext, strArr)) {
                    CompleteVisitFragment.this.pickImageFiles();
                    return;
                }
                ActivityResultLauncher<String[]> readWritePermReqLauncher = CompleteVisitFragment.this.getReadWritePermReqLauncher();
                if (readWritePermReqLauncher != null) {
                    strArr2 = CompleteVisitFragment.this.readWritePermissionsLists;
                    readWritePermReqLauncher.launch(strArr2);
                }
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapCompleteVisit() {
                LifecycleOwner viewLifecycleOwner = CompleteVisitFragment.this.getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1(CompleteVisitFragment.this, null), 3, null);
            }
        });
        getViewModel().getVisitCompletionResponse().observe(getViewLifecycleOwner(), new CompleteVisitFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CompleteVisitFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onViewCreated$lambda$5;
                onViewCreated$lambda$5 = CompleteVisitFragment.onViewCreated$lambda$5(CompleteVisitFragment.this, (ApiResponseCallback) obj);
                return onViewCreated$lambda$5;
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$3(CompleteVisitFragment completeVisitFragment, Map permissions) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Set entrySet = permissions.entrySet();
        char c = 1;
        c = 1;
        if (!(entrySet instanceof Collection) || !entrySet.isEmpty()) {
            Iterator it = entrySet.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (!((Boolean) ((Map.Entry) it.next()).getValue()).booleanValue()) {
                    c = 0;
                    break;
                }
            }
        }
        if (c != null) {
            completeVisitFragment.pickImageFiles();
        } else {
            Toast.makeText(completeVisitFragment.requireContext(), completeVisitFragment.getString(R.string.please_grant_permissions_from_app_settings), 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$4(CompleteVisitFragment this$0, View it) {
        this$0.getBinding().title.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit onViewCreated$lambda$5(CompleteVisitFragment this$0, ApiResponseCallback response) {
        String str;
        this$0.getBinding().progressBar.setVisibility(8);
        if (response instanceof ApiResponseCallback.Success) {
            VisitCompletionResponse visitCompletionResponse = (VisitCompletionResponse) ((ApiResponseCallback.Success) response).getData();
            if (visitCompletionResponse != null ? Intrinsics.areEqual((Object) visitCompletionResponse.getSuccess(), (Object) true) : false) {
                Toast.makeText(this$0.requireContext(), ((VisitCompletionResponse) ((ApiResponseCallback.Success) response).getData()).getMessage(), 0).show();
                this$0.requireActivity().onBackPressed();
            } else {
                this$0.getBinding().btnVisitComplete.setVisibility(0);
                Context requireContext = this$0.requireContext();
                VisitCompletionResponse visitCompletionResponse2 = (VisitCompletionResponse) ((ApiResponseCallback.Success) response).getData();
                if (visitCompletionResponse2 == null || (str = visitCompletionResponse2.getMessage()) == null) {
                    str = "An error occurred";
                }
                Toast.makeText(requireContext, str, 0).show();
                T data = ((ApiResponseCallback.Success) response).getData();
                Intrinsics.checkNotNull(data);
                Log.i("TAG", "onViewCreated: " + ((VisitCompletionResponse) data).getMessage());
            }
        } else if (response instanceof ApiResponseCallback.Error) {
            this$0.getBinding().btnVisitComplete.setVisibility(0);
            Toast.makeText(this$0.requireContext(), ((ApiResponseCallback.Error) response).getMessage(), 0).show();
            T data2 = ((ApiResponseCallback.Error) response).getData();
            Intrinsics.checkNotNull(data2);
            Log.i("TAG", "onViewCreated--: " + ((VisitCompletionResponse) data2).getMessage());
        } else {
            if (!(response instanceof ApiResponseCallback.Loading)) {
                throw new NoWhenBranchMatchedException();
            }
            this$0.getBinding().btnVisitComplete.setVisibility(8);
            this$0.getBinding().progressBar.setVisibility(0);
        }
        return Unit.INSTANCE;
    }

    private final void setAttachmentAdapter() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        this.attachmentAdapter = new AttachmentAdapter(requireContext, this.pictureUriList, new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CompleteVisitFragment$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit attachmentAdapter$lambda$9;
                attachmentAdapter$lambda$9 = CompleteVisitFragment.setAttachmentAdapter$lambda$9(CompleteVisitFragment.this, ((Integer) obj).intValue());
                return attachmentAdapter$lambda$9;
            }
        });
        getBinding().attachmentRv.setAdapter(this.attachmentAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(requireContext());
        manager.setOrientation(0);
        getBinding().attachmentRv.setLayoutManager(manager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setAttachmentAdapter$lambda$9(final CompleteVisitFragment this$0, final int position) {
        new MaterialAlertDialogBuilder(this$0.requireContext()).setMessage(R.string.do_you_want_to_delete_this_item).setCancelable(false).setPositiveButton((CharSequence) this$0.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CompleteVisitFragment$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                CompleteVisitFragment.setAttachmentAdapter$lambda$9$lambda$7(CompleteVisitFragment.this, position, dialogInterface, i);
            }
        }).setNegativeButton((CharSequence) this$0.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CompleteVisitFragment$$ExternalSyntheticLambda6
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setAttachmentAdapter$lambda$9$lambda$7(CompleteVisitFragment this$0, int $position, DialogInterface dialog, int which) {
        this$0.pictureUriList.remove($position);
        AttachmentAdapter $this$setAttachmentAdapter_u24lambda_u249_u24lambda_u247_u24lambda_u246 = this$0.attachmentAdapter;
        if ($this$setAttachmentAdapter_u24lambda_u249_u24lambda_u247_u24lambda_u246 != null) {
            $this$setAttachmentAdapter_u24lambda_u249_u24lambda_u247_u24lambda_u246.notifyItemRemoved($position);
            $this$setAttachmentAdapter_u24lambda_u249_u24lambda_u247_u24lambda_u246.notifyItemRangeChanged($position, this$0.pictureUriList.size() - $position);
        }
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void pickImageFiles() {
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        this.filePickerLauncher.launch(intent);
    }
}
