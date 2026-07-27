package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.savedstate.SavedStateRegistryOwner;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.AllProductsSelectedCartAdapter;
import com.ingenious.androidbookmarksalesupgrade.adapter.AttachmentAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityCompleteVisitBinding;
import com.ingenious.androidbookmarksalesupgrade.databinding.DialogInvoiceShareBinding;
import com.ingenious.androidbookmarksalesupgrade.extensions.DialogExtKt;
import com.ingenious.androidbookmarksalesupgrade.extensions.FileExtKt;
import com.ingenious.androidbookmarksalesupgrade.extensions.PermissionsExtKt;
import com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.Invoice;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import com.ingenious.androidbookmarksalesupgrade.model.response.ProfileData;
import com.ingenious.androidbookmarksalesupgrade.model.response.ProfileResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.VisitCompletionResponse;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.utils.AppToast;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import com.ingenious.androidbookmarksalesupgrade.utils.LoggingInterceptor;
import com.ingenious.androidbookmarksalesupgrade.utils.Utils;
import com.ingenious.androidbookmarksalesupgrade.viewModel.UserViewModel;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: CompleteVisitActivity.kt */
@Metadata(d1 = {"\u0000Â\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010H\u001a\u00020I2\b\u0010J\u001a\u0004\u0018\u00010KH\u0014J\u0018\u0010L\u001a\u00020I2\u0006\u0010M\u001a\u00020\u00152\u0006\u0010N\u001a\u000205H\u0003J\u0018\u0010O\u001a\u0012\u0012\u0004\u0012\u00020 0\u000fj\b\u0012\u0004\u0012\u00020 `\u0011H\u0002J\n\u0010P\u001a\u0004\u0018\u00010 H\u0002J\b\u0010Q\u001a\u00020IH\u0002J\b\u0010R\u001a\u00020IH\u0002J\b\u0010S\u001a\u00020=H\u0002J\b\u0010U\u001a\u00020=H\u0002J\u0010\u0010V\u001a\u00020I2\u0006\u0010W\u001a\u00020\u0010H\u0002J\b\u0010X\u001a\u00020IH\u0002J\b\u0010Y\u001a\u00020IH\u0002J\u0016\u0010Z\u001a\u00020I2\f\u0010[\u001a\b\u0012\u0004\u0012\u00020\u00100\\H\u0002J\u0010\u0010]\u001a\u00020I2\u0006\u0010^\u001a\u00020_H\u0002J\b\u0010`\u001a\u00020IH\u0002J\n\u0010a\u001a\u0004\u0018\u00010\u0015H\u0002J\u0016\u0010h\u001a\u00020I2\f\u0010i\u001a\b\u0012\u0004\u0012\u00020j0\\H\u0002J\u001a\u0010k\u001a\u00020I2\u0006\u0010l\u001a\u00020'2\b\b\u0002\u0010m\u001a\u00020\u0015H\u0002J\u0018\u0010n\u001a\u00020I2\u0006\u0010l\u001a\u00020'2\u0006\u0010m\u001a\u00020\u0015H\u0002J\b\u0010s\u001a\u00020IH\u0002J\u0010\u0010t\u001a\u00020\u00152\u0006\u0010u\u001a\u00020vH\u0002J\u000e\u0010w\u001a\b\u0012\u0004\u0012\u00020\u00150\\H\u0002J\u0016\u0010x\u001a\u00020y2\f\u0010z\u001a\b\u0012\u0004\u0012\u00020j0\\H\u0002J\u001c\u0010{\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u0002050|2\u0006\u0010}\u001a\u00020~H\u0002J\u0010\u0010\u007f\u001a\u00020 2\u0006\u0010N\u001a\u000205H\u0002J\t\u0010\u0080\u0001\u001a\u000202H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u0012\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0010\u0010%\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u00020'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001b\u0010,\u001a\u00020-8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b0\u0010\u000b\u001a\u0004\b.\u0010/R\u000e\u00101\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u00106\u001a\u0004\u0018\u00010'8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b9\u0010\u000b\u001a\u0004\b7\u00108R\u0016\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010;R\u0014\u0010<\u001a\b\u0012\u0004\u0012\u00020=0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010>\u001a\b\u0012\u0004\u0012\u00020=0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010?\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u001c\"\u0004\bA\u0010\u001eR\u001a\u0010B\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u001c\"\u0004\bD\u0010\u001eR\u001a\u0010E\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u001c\"\u0004\bG\u0010\u001eR\u0010\u0010T\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010b\u001a\u0004\u0018\u00010cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010e\"\u0004\bf\u0010gR\u0011\u0010o\u001a\u00020p¢\u0006\b\n\u0000\u001a\u0004\bq\u0010r¨\u0006\u0081\u0001"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/CompleteVisitActivity;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/BaseActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityCompleteVisitBinding;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "attachmentAdapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/AttachmentAdapter;", "pictureUriList", "Ljava/util/ArrayList;", "Landroid/net/Uri;", "Lkotlin/collections/ArrayList;", "readWritePermReqLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "getReadWritePermReqLauncher", "()Landroidx/activity/result/ActivityResultLauncher;", "setReadWritePermReqLauncher", "(Landroidx/activity/result/ActivityResultLauncher;)V", "photoPath", "getPhotoPath", "()Ljava/lang/String;", "setPhotoPath", "(Ljava/lang/String;)V", "imageBodySignature", "Lokhttp3/MultipartBody$Part;", "getImageBodySignature", "()Lokhttp3/MultipartBody$Part;", "setImageBodySignature", "(Lokhttp3/MultipartBody$Part;)V", "invoicePdfPart", "IMAGE_PICKER_REQUEST_CODE", "", "getIMAGE_PICKER_REQUEST_CODE", "()I", "setIMAGE_PICKER_REQUEST_CODE", "(I)V", "userViewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/UserViewModel;", "getUserViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/UserViewModel;", "userViewModel$delegate", "isInvoiceGenerated", "", "generatedInvoiceId", "generatedInvoiceFile", "Ljava/io/File;", "visitId", "getVisitId", "()Ljava/lang/Integer;", "visitId$delegate", "readWritePermissionsLists", "[Ljava/lang/String;", "addProductsLauncher", "Landroid/content/Intent;", "filePickerLauncher", "visitStartTime", "getVisitStartTime", "setVisitStartTime", "customerName", "getCustomerName", "setCustomerName", "visitDate", "getVisitDate", "setVisitDate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "showInvoiceDialog", "invoiceId", "pdfFile", "processImages", "processSignature", "submitVisitData", "pickImageFiles", "createGalleryIntent", "cameraImageUri", "createCameraIntent", "handleURI", "uri", "setAttachmentAdapter", "openImagePicker", "handleSelectedImages", "selectedUris", "", "showKeyboard", "view", "Landroid/view/View;", "fetchVisitProducts", "getToken", "adapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/AllProductsSelectedCartAdapter;", "getAdapter", "()Lcom/ingenious/androidbookmarksalesupgrade/adapter/AllProductsSelectedCartAdapter;", "setAdapter", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/AllProductsSelectedCartAdapter;)V", "showVisitProducts", "productsList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "deleteProduct", "productId", FirebaseAnalytics.Param.QUANTITY, "updateProductQuantity", "itemTouchHelperCallback", "Landroidx/recyclerview/widget/ItemTouchHelper$SimpleCallback;", "getItemTouchHelperCallback", "()Landroidx/recyclerview/widget/ItemTouchHelper$SimpleCallback;", "setupInvoiceTypeList", "saveSignatureToFile", "bitmap", "Landroid/graphics/Bitmap;", "getImagePaths", "calculateTotalAmount", "", "products", "generateInvoicePdf", "Lkotlin/Pair;", "invoice", "Lcom/ingenious/androidbookmarksalesupgrade/model/Invoice;", "createInvoicePdfPart", "ensureInvoiceGenerated", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class CompleteVisitActivity extends BaseActivity {
    private int IMAGE_PICKER_REQUEST_CODE;
    private AllProductsSelectedCartAdapter adapter;
    private final ActivityResultLauncher<Intent> addProductsLauncher;
    private AttachmentAdapter attachmentAdapter;
    private ActivityCompleteVisitBinding binding;
    private Uri cameraImageUri;
    private String customerName;
    private final ActivityResultLauncher<Intent> filePickerLauncher;
    private File generatedInvoiceFile;
    private String generatedInvoiceId;
    private MultipartBody.Part imageBodySignature;
    private MultipartBody.Part invoicePdfPart;
    private boolean isInvoiceGenerated;
    private final ItemTouchHelper.SimpleCallback itemTouchHelperCallback;
    private String photoPath;
    private ArrayList<Uri> pictureUriList;
    private ActivityResultLauncher<String[]> readWritePermReqLauncher;
    private final String[] readWritePermissionsLists;

    /* renamed from: userViewModel$delegate, reason: from kotlin metadata */
    private final Lazy userViewModel;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private String visitDate;

    /* renamed from: visitId$delegate, reason: from kotlin metadata */
    private final Lazy visitId;
    private String visitStartTime;

    public CompleteVisitActivity() {
        final CompleteVisitActivity $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$special$$inlined$viewModel$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelOwner invoke() {
                ViewModelOwner.Companion companion = ViewModelOwner.INSTANCE;
                ComponentActivity componentActivity = ComponentActivity.this;
                ComponentActivity componentActivity2 = ComponentActivity.this;
                return companion.from(componentActivity, componentActivity2 instanceof SavedStateRegistryOwner ? componentActivity2 : null);
            }
        };
        final Function0 parameters$iv = null;
        final Scope scope$iv = AndroidKoinScopeExtKt.getKoinScope($this$viewModel_u24default$iv);
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$special$$inlined$viewModel$default$2
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
        this.viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(VisitViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$special$$inlined$viewModel$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, factoryProducer$iv$iv);
        this.pictureUriList = new ArrayList<>();
        this.photoPath = "";
        this.IMAGE_PICKER_REQUEST_CODE = 1001;
        final CompleteVisitActivity $this$viewModel_u24default$iv2 = this;
        final Qualifier qualifier$iv2 = null;
        final Function0 owner$iv2 = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$special$$inlined$viewModel$default$4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelOwner invoke() {
                ViewModelOwner.Companion companion = ViewModelOwner.INSTANCE;
                ComponentActivity componentActivity = ComponentActivity.this;
                ComponentActivity componentActivity2 = ComponentActivity.this;
                return companion.from(componentActivity, componentActivity2 instanceof SavedStateRegistryOwner ? componentActivity2 : null);
            }
        };
        final Function0 parameters$iv2 = null;
        final Scope scope$iv2 = AndroidKoinScopeExtKt.getKoinScope($this$viewModel_u24default$iv2);
        Function0 factoryProducer$iv$iv2 = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$special$$inlined$viewModel$default$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                Function0 owner$iv3 = Function0.this;
                Qualifier qualifier$iv3 = qualifier$iv2;
                Function0 parameters$iv3 = parameters$iv2;
                Scope scope$iv3 = scope$iv2;
                ViewModelOwner ownerValue$iv = (ViewModelOwner) owner$iv3.invoke();
                ViewModelParameter viewModelParameters$iv = new ViewModelParameter(Reflection.getOrCreateKotlinClass(UserViewModel.class), qualifier$iv3, null, parameters$iv3, ownerValue$iv.getStoreOwner(), ownerValue$iv.getStateRegistry());
                return ViewModelResolverKt.pickFactory(scope$iv3, viewModelParameters$iv);
            }
        };
        this.userViewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(UserViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$special$$inlined$viewModel$default$6
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, factoryProducer$iv$iv2);
        this.visitId = LazyKt.lazy(new Function0() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                int visitId_delegate$lambda$0;
                visitId_delegate$lambda$0 = CompleteVisitActivity.visitId_delegate$lambda$0(CompleteVisitActivity.this);
                return Integer.valueOf(visitId_delegate$lambda$0);
            }
        });
        this.readWritePermissionsLists = Build.VERSION.SDK_INT >= 33 ? new String[]{"android.permission.READ_MEDIA_IMAGES"} : new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        this.addProductsLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$$ExternalSyntheticLambda3
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                CompleteVisitActivity.addProductsLauncher$lambda$1(CompleteVisitActivity.this, (ActivityResult) obj);
            }
        });
        this.filePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$$ExternalSyntheticLambda4
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                CompleteVisitActivity.filePickerLauncher$lambda$2(CompleteVisitActivity.this, (ActivityResult) obj);
            }
        });
        this.visitStartTime = "";
        this.customerName = "";
        this.visitDate = "";
        this.itemTouchHelperCallback = new CompleteVisitActivity$itemTouchHelperCallback$1(this);
    }

    private final VisitViewModel getViewModel() {
        return (VisitViewModel) this.viewModel.getValue();
    }

    public final ActivityResultLauncher<String[]> getReadWritePermReqLauncher() {
        return this.readWritePermReqLauncher;
    }

    public final void setReadWritePermReqLauncher(ActivityResultLauncher<String[]> activityResultLauncher) {
        this.readWritePermReqLauncher = activityResultLauncher;
    }

    public final String getPhotoPath() {
        return this.photoPath;
    }

    public final void setPhotoPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.photoPath = str;
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

    private final UserViewModel getUserViewModel() {
        return (UserViewModel) this.userViewModel.getValue();
    }

    private final Integer getVisitId() {
        return (Integer) this.visitId.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int visitId_delegate$lambda$0(CompleteVisitActivity this$0) {
        return this$0.getIntent().getIntExtra("visitId", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addProductsLauncher$lambda$1(CompleteVisitActivity this$0, ActivityResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ActivityCompleteVisitBinding activityCompleteVisitBinding = this$0.binding;
        ActivityCompleteVisitBinding activityCompleteVisitBinding2 = null;
        if (activityCompleteVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding = null;
        }
        activityCompleteVisitBinding.selectedProducts.setText("0");
        ActivityCompleteVisitBinding activityCompleteVisitBinding3 = this$0.binding;
        if (activityCompleteVisitBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding3 = null;
        }
        activityCompleteVisitBinding3.pbarFetchingBooks.setVisibility(0);
        ActivityCompleteVisitBinding activityCompleteVisitBinding4 = this$0.binding;
        if (activityCompleteVisitBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCompleteVisitBinding2 = activityCompleteVisitBinding4;
        }
        activityCompleteVisitBinding2.recyclerView.setVisibility(8);
        this$0.fetchVisitProducts();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void filePickerLauncher$lambda$2(CompleteVisitActivity this$0, ActivityResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.getResultCode() == -1) {
            this$0.pictureUriList.clear();
            Intent data = result.getData();
            ClipData clipData = data != null ? data.getClipData() : null;
            if (clipData != null) {
                int itemCount = clipData.getItemCount();
                for (int i = 0; i < itemCount; i++) {
                    Uri uri = clipData.getItemAt(i).getUri();
                    Intrinsics.checkNotNull(uri);
                    this$0.handleURI(uri);
                }
                return;
            }
            Intent data2 = result.getData();
            if ((data2 != null ? data2.getData() : null) != null) {
                Intent data3 = result.getData();
                Intrinsics.checkNotNull(data3);
                Uri data4 = data3.getData();
                Intrinsics.checkNotNull(data4);
                this$0.handleURI(data4);
                return;
            }
            if (this$0.cameraImageUri != null) {
                Uri uri2 = this$0.cameraImageUri;
                Intrinsics.checkNotNull(uri2);
                this$0.handleURI(uri2);
            }
        }
    }

    public final String getVisitStartTime() {
        return this.visitStartTime;
    }

    public final void setVisitStartTime(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.visitStartTime = str;
    }

    public final String getCustomerName() {
        return this.customerName;
    }

    public final void setCustomerName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.customerName = str;
    }

    public final String getVisitDate() {
        return this.visitDate;
    }

    public final void setVisitDate(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.visitDate = str;
    }

    /* JADX WARN: Type inference failed for: r6v5, types: [java.time.ZonedDateTime] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$onCreate$1] */
    @Override // com.ingenious.androidbookmarksalesupgrade.ui.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityCompleteVisitBinding.inflate(getLayoutInflater());
        ActivityCompleteVisitBinding activityCompleteVisitBinding = this.binding;
        if (activityCompleteVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding = null;
        }
        setContentView(activityCompleteVisitBinding.getRoot());
        ActivityCompleteVisitBinding activityCompleteVisitBinding2 = this.binding;
        if (activityCompleteVisitBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding2 = null;
        }
        activityCompleteVisitBinding2.linearCameraOpen.setVisibility(0);
        try {
            this.visitStartTime = String.valueOf(getIntent().getStringExtra("visitstarttime"));
            this.customerName = String.valueOf(getIntent().getStringExtra("customer_name"));
            this.visitDate = String.valueOf(getIntent().getStringExtra("visitDate"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime visitStart = LocalDateTime.parse(this.visitDate + StringUtils.SPACE + this.visitStartTime, formatter);
            final long startMillis = visitStart.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            new CountDownTimer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$onCreate$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(Long.MAX_VALUE, 1000L);
                }

                @Override // android.os.CountDownTimer
                public void onTick(long millisUntilFinished) {
                    ActivityCompleteVisitBinding activityCompleteVisitBinding3;
                    long now = System.currentTimeMillis();
                    long elapsedMillis = now - startMillis;
                    Duration duration = Duration.ofMillis(elapsedMillis);
                    long hours = duration.toHours();
                    long j = 60;
                    long minutes = duration.toMinutes() % j;
                    long seconds = duration.getSeconds() % j;
                    activityCompleteVisitBinding3 = this.binding;
                    if (activityCompleteVisitBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityCompleteVisitBinding3 = null;
                    }
                    TextView textView = activityCompleteVisitBinding3.schoolName;
                    String customerName = this.getCustomerName();
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String format = String.format("%02d:%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds)}, 3));
                    Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                    textView.setText(customerName + " • " + format);
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                }
            }.start();
        } catch (Exception e) {
            Log.i(LoggingInterceptor.TAG, "onCreate: " + e.getMessage());
        }
        ExtensionKt.belowStatusBarText(this);
        this.readWritePermReqLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$$ExternalSyntheticLambda10
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                CompleteVisitActivity.onCreate$lambda$4(CompleteVisitActivity.this, (Map) obj);
            }
        });
        Log.d(LoggingInterceptor.TAG, "Received visitId: " + getVisitId());
        getViewModel().getVisitCompletionResponse().observe(this, new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$$ExternalSyntheticLambda11
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CompleteVisitActivity.onCreate$lambda$7(CompleteVisitActivity.this, (ApiResponseCallback) obj);
            }
        });
        ActivityCompleteVisitBinding activityCompleteVisitBinding3 = this.binding;
        if (activityCompleteVisitBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding3 = null;
        }
        activityCompleteVisitBinding3.title.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CompleteVisitActivity.onCreate$lambda$8(CompleteVisitActivity.this, view);
            }
        });
        ActivityCompleteVisitBinding activityCompleteVisitBinding4 = this.binding;
        if (activityCompleteVisitBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding4 = null;
        }
        activityCompleteVisitBinding4.clearBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CompleteVisitActivity.onCreate$lambda$9(CompleteVisitActivity.this, view);
            }
        });
        ActivityCompleteVisitBinding activityCompleteVisitBinding5 = this.binding;
        if (activityCompleteVisitBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding5 = null;
        }
        activityCompleteVisitBinding5.btnGenerateInvoice.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CompleteVisitActivity.onCreate$lambda$10(CompleteVisitActivity.this, view);
            }
        });
        fetchVisitProducts();
        ActivityCompleteVisitBinding activityCompleteVisitBinding6 = this.binding;
        if (activityCompleteVisitBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding6 = null;
        }
        activityCompleteVisitBinding6.setListener(new GenericListeners() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$onCreate$7
            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onNotificationClick() {
                GenericListeners.DefaultImpls.onNotificationClick(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onSettingClick() {
                GenericListeners.DefaultImpls.onSettingClick(this);
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
                CompleteVisitActivity completeVisitActivity = CompleteVisitActivity.this;
                strArr = CompleteVisitActivity.this.readWritePermissionsLists;
                if (PermissionsExtKt.hasPermissions(completeVisitActivity, strArr)) {
                    CompleteVisitActivity.this.pickImageFiles();
                    return;
                }
                ActivityResultLauncher<String[]> readWritePermReqLauncher = CompleteVisitActivity.this.getReadWritePermReqLauncher();
                if (readWritePermReqLauncher != null) {
                    strArr2 = CompleteVisitActivity.this.readWritePermissionsLists;
                    readWritePermReqLauncher.launch(strArr2);
                }
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapCompleteVisit() {
                CompleteVisitActivity.this.submitVisitData();
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapAddBooks() {
                ActivityResultLauncher activityResultLauncher;
                Intent intent = new Intent(CompleteVisitActivity.this, (Class<?>) AllProductsActivity.class);
                activityResultLauncher = CompleteVisitActivity.this.addProductsLauncher;
                activityResultLauncher.launch(intent);
            }
        });
        setAttachmentAdapter();
        getUserViewModel().profile();
        getUserViewModel().getProfileResponse().observe(this, new Observer() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$$ExternalSyntheticLambda15
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CompleteVisitActivity.onCreate$lambda$14(CompleteVisitActivity.this, (ApiResponseCallback) obj);
            }
        });
        setupInvoiceTypeList();
        ActivityCompleteVisitBinding activityCompleteVisitBinding7 = this.binding;
        if (activityCompleteVisitBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding7 = null;
        }
        activityCompleteVisitBinding7.invoiceType.setText((CharSequence) "Cash", false);
        ActivityCompleteVisitBinding activityCompleteVisitBinding8 = this.binding;
        if (activityCompleteVisitBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding8 = null;
        }
        activityCompleteVisitBinding8.invoiceType.setKeyListener(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$4(CompleteVisitActivity completeVisitActivity, Map permissions) {
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
            completeVisitActivity.pickImageFiles();
            return;
        }
        AppToast appToast = AppToast.INSTANCE;
        String string = completeVisitActivity.getString(R.string.please_grant_permissions_from_app_settings);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        appToast.showToast(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onCreate$lambda$7(final CompleteVisitActivity this$0, ApiResponseCallback it) {
        if (it != null) {
            ActivityCompleteVisitBinding activityCompleteVisitBinding = null;
            if (it instanceof ApiResponseCallback.Error) {
                String message = ((ApiResponseCallback.Error) it).getMessage();
                Intrinsics.checkNotNull(message);
                DialogExtKt.showMaterialDialog$default(this$0, message, (DialogListeners) null, 2, (Object) null);
                ActivityCompleteVisitBinding activityCompleteVisitBinding2 = this$0.binding;
                if (activityCompleteVisitBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityCompleteVisitBinding2 = null;
                }
                activityCompleteVisitBinding2.progressBar.setVisibility(8);
                ActivityCompleteVisitBinding activityCompleteVisitBinding3 = this$0.binding;
                if (activityCompleteVisitBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityCompleteVisitBinding = activityCompleteVisitBinding3;
                }
                activityCompleteVisitBinding.btnVisitComplete.setVisibility(0);
                return;
            }
            if (it instanceof ApiResponseCallback.Loading) {
                ActivityCompleteVisitBinding activityCompleteVisitBinding4 = this$0.binding;
                if (activityCompleteVisitBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityCompleteVisitBinding4 = null;
                }
                activityCompleteVisitBinding4.progressBar.setVisibility(0);
                ActivityCompleteVisitBinding activityCompleteVisitBinding5 = this$0.binding;
                if (activityCompleteVisitBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityCompleteVisitBinding = activityCompleteVisitBinding5;
                }
                activityCompleteVisitBinding.btnVisitComplete.setVisibility(8);
                return;
            }
            if (!(it instanceof ApiResponseCallback.Success)) {
                throw new NoWhenBranchMatchedException();
            }
            VisitCompletionResponse data = (VisitCompletionResponse) ((ApiResponseCallback.Success) it).getData();
            if (data != null) {
                Boolean success = data.getSuccess();
                Intrinsics.checkNotNull(success);
                if (success.booleanValue()) {
                    String message2 = data.getMessage();
                    Intrinsics.checkNotNull(message2);
                    DialogExtKt.showMaterialDialog(this$0, message2, new DialogListeners() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$onCreate$3$1$1$1
                        @Override // com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners
                        public void onNegativeButtonTap(DialogInterface dialog) {
                            DialogListeners.DefaultImpls.onNegativeButtonTap(this, dialog);
                        }

                        @Override // com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners
                        public void onPositionButtonTap(DialogInterface dialog) {
                            CompleteVisitActivity.this.finish();
                        }
                    });
                    ActivityCompleteVisitBinding activityCompleteVisitBinding6 = this$0.binding;
                    if (activityCompleteVisitBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityCompleteVisitBinding6 = null;
                    }
                    activityCompleteVisitBinding6.progressBar.setVisibility(8);
                    ActivityCompleteVisitBinding activityCompleteVisitBinding7 = this$0.binding;
                    if (activityCompleteVisitBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityCompleteVisitBinding = activityCompleteVisitBinding7;
                    }
                    activityCompleteVisitBinding.btnVisitComplete.setVisibility(0);
                    return;
                }
                CompleteVisitActivity completeVisitActivity = this$0;
                String message3 = data.getMessage();
                if (message3 == null) {
                    message3 = "Failed: The selected visit id is invalid.";
                }
                DialogExtKt.showMaterialDialog(completeVisitActivity, message3, new DialogListeners() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$onCreate$3$1$1$2
                    @Override // com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners
                    public void onNegativeButtonTap(DialogInterface dialog) {
                        DialogListeners.DefaultImpls.onNegativeButtonTap(this, dialog);
                    }

                    @Override // com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners
                    public void onPositionButtonTap(DialogInterface dialog) {
                        CompleteVisitActivity.this.finish();
                    }
                });
                ActivityCompleteVisitBinding activityCompleteVisitBinding8 = this$0.binding;
                if (activityCompleteVisitBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityCompleteVisitBinding8 = null;
                }
                activityCompleteVisitBinding8.progressBar.setVisibility(8);
                ActivityCompleteVisitBinding activityCompleteVisitBinding9 = this$0.binding;
                if (activityCompleteVisitBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityCompleteVisitBinding = activityCompleteVisitBinding9;
                }
                activityCompleteVisitBinding.btnVisitComplete.setVisibility(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$8(CompleteVisitActivity this$0, View it) {
        ActivityCompleteVisitBinding activityCompleteVisitBinding = this$0.binding;
        ActivityCompleteVisitBinding activityCompleteVisitBinding2 = null;
        if (activityCompleteVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding = null;
        }
        activityCompleteVisitBinding.title.requestFocus();
        ActivityCompleteVisitBinding activityCompleteVisitBinding3 = this$0.binding;
        if (activityCompleteVisitBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCompleteVisitBinding2 = activityCompleteVisitBinding3;
        }
        EditText title = activityCompleteVisitBinding2.title;
        Intrinsics.checkNotNullExpressionValue(title, "title");
        this$0.showKeyboard(title);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$9(CompleteVisitActivity this$0, View it) {
        ActivityCompleteVisitBinding activityCompleteVisitBinding = this$0.binding;
        if (activityCompleteVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding = null;
        }
        activityCompleteVisitBinding.signaturePad.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$10(CompleteVisitActivity this$0, View it) {
        if (this$0.ensureInvoiceGenerated()) {
            String str = this$0.generatedInvoiceId;
            Intrinsics.checkNotNull(str);
            File file = this$0.generatedInvoiceFile;
            Intrinsics.checkNotNull(file);
            this$0.showInvoiceDialog(str, file);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onCreate$lambda$14(final CompleteVisitActivity this$0, ApiResponseCallback it) {
        if (it != null && !(it instanceof ApiResponseCallback.Error) && !(it instanceof ApiResponseCallback.Loading)) {
            if (!(it instanceof ApiResponseCallback.Success)) {
                throw new NoWhenBranchMatchedException();
            }
            final ProfileResponse data = (ProfileResponse) ((ApiResponseCallback.Success) it).getData();
            if (data != null) {
                this$0.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        CompleteVisitActivity.onCreate$lambda$14$lambda$13$lambda$12$lambda$11(CompleteVisitActivity.this, data);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$14$lambda$13$lambda$12$lambda$11(CompleteVisitActivity this$0, ProfileResponse $data) {
        ActivityCompleteVisitBinding activityCompleteVisitBinding = this$0.binding;
        ActivityCompleteVisitBinding activityCompleteVisitBinding2 = null;
        if (activityCompleteVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding = null;
        }
        TextView textView = activityCompleteVisitBinding.agentName;
        ProfileData data = $data.getData();
        textView.setText(data != null ? data.getName() : null);
        ActivityCompleteVisitBinding activityCompleteVisitBinding3 = this$0.binding;
        if (activityCompleteVisitBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding3 = null;
        }
        TextView textView2 = activityCompleteVisitBinding3.agentId;
        ProfileData data2 = $data.getData();
        textView2.setText(String.valueOf(data2 != null ? data2.getId() : null));
        String currentDateTime = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        ActivityCompleteVisitBinding activityCompleteVisitBinding4 = this$0.binding;
        if (activityCompleteVisitBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCompleteVisitBinding2 = activityCompleteVisitBinding4;
        }
        activityCompleteVisitBinding2.completeVisitTime.setText(currentDateTime);
    }

    private final void showInvoiceDialog(final String invoiceId, final File pdfFile) {
        DialogInvoiceShareBinding dialogBinding = DialogInvoiceShareBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(dialogBinding, "inflate(...)");
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(dialogBinding.getRoot()).create();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        dialog.show();
        dialogBinding.date.setText(new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date()));
        dialogBinding.time.setText(new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date()));
        dialogBinding.invoiceNumber.setText(invoiceId);
        dialogBinding.closeBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialogBinding.shareWhatsapp.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CompleteVisitActivity.showInvoiceDialog$sharePdf(CompleteVisitActivity.this, pdfFile, "com.whatsapp");
            }
        });
        dialogBinding.shareEmail.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CompleteVisitActivity.showInvoiceDialog$lambda$20(CompleteVisitActivity.this, pdfFile, invoiceId, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showInvoiceDialog$sharePdf(CompleteVisitActivity this$0, File $pdfFile, String packageName) {
        Uri uri = FileProvider.getUriForFile(this$0, this$0.getApplicationContext().getPackageName() + ".provider", $pdfFile);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("application/pdf");
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.addFlags(1);
        if (packageName != null) {
            intent.setPackage(packageName);
        }
        try {
            this$0.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this$0, "App not found", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showInvoiceDialog$lambda$20(CompleteVisitActivity this$0, File $pdfFile, String $invoiceId, View it) {
        Uri uri = FileProvider.getUriForFile(this$0, this$0.getApplicationContext().getPackageName() + ".provider", $pdfFile);
        Intent emailIntent = new Intent("android.intent.action.SEND");
        emailIntent.setType("application/pdf");
        emailIntent.putExtra("android.intent.extra.SUBJECT", "Invoice " + $invoiceId);
        emailIntent.putExtra("android.intent.extra.TEXT", "Please find attached your invoice.");
        emailIntent.putExtra("android.intent.extra.STREAM", uri);
        emailIntent.addFlags(1);
        this$0.startActivity(Intent.createChooser(emailIntent, "Send Invoice"));
    }

    private final ArrayList<MultipartBody.Part> processImages() {
        Iterable $this$forEach$iv;
        CompleteVisitActivity completeVisitActivity = this;
        ArrayList images = new ArrayList();
        Iterable $this$forEach$iv2 = completeVisitActivity.pictureUriList;
        for (Object element$iv : $this$forEach$iv2) {
            Uri uri = (Uri) element$iv;
            File file = FileExtKt.getFileFromUri(completeVisitActivity, uri);
            if (file == null) {
                $this$forEach$iv = $this$forEach$iv2;
            } else {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
                File compressedFile = new File(getCacheDir(), file.getName());
                byte[] byteArray = baos.toByteArray();
                Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
                FilesKt.writeBytes(compressedFile, byteArray);
                RequestBody imageBody = RequestBody.INSTANCE.create(compressedFile, MediaType.INSTANCE.parse("image/jpeg"));
                $this$forEach$iv = $this$forEach$iv2;
                MultipartBody.Part imagePart = MultipartBody.Part.INSTANCE.createFormData("images[]", compressedFile.getName(), imageBody);
                images.add(imagePart);
            }
            completeVisitActivity = this;
            $this$forEach$iv2 = $this$forEach$iv;
        }
        return images;
    }

    private final MultipartBody.Part processSignature() {
        ActivityCompleteVisitBinding activityCompleteVisitBinding = this.binding;
        if (activityCompleteVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding = null;
        }
        Bitmap signatureBitmap = activityCompleteVisitBinding.signaturePad.getSignatureBitmap();
        Intrinsics.checkNotNull(signatureBitmap);
        saveSignatureToFile(signatureBitmap);
        if (signatureBitmap.getWidth() == 0 || signatureBitmap.getHeight() == 0) {
            AppToast.INSTANCE.showToast("Please provide a signature before submitting.");
            return null;
        }
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        signatureBitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArray);
        byte[] imageData = byteArray.toByteArray();
        File signatureFile = new File(getCacheDir(), "signature.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(signatureFile);
        try {
            FileOutputStream it = fileOutputStream;
            it.write(imageData);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(fileOutputStream, null);
            RequestBody requestBody = RequestBody.INSTANCE.create(signatureFile, MediaType.INSTANCE.parse("image/jpeg"));
            return MultipartBody.Part.INSTANCE.createFormData("signature", signatureFile.getName(), requestBody);
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void submitVisitData() {
        Integer currentVisitId = getVisitId();
        ActivityCompleteVisitBinding activityCompleteVisitBinding = this.binding;
        ActivityCompleteVisitBinding activityCompleteVisitBinding2 = null;
        if (activityCompleteVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding = null;
        }
        activityCompleteVisitBinding.title.getText().toString();
        ActivityCompleteVisitBinding activityCompleteVisitBinding3 = this.binding;
        if (activityCompleteVisitBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCompleteVisitBinding2 = activityCompleteVisitBinding3;
        }
        String invoiceType = activityCompleteVisitBinding2.invoiceType.getText().toString();
        if (currentVisitId == null || currentVisitId.intValue() == 0) {
            AppToast.INSTANCE.showToast("Visit ID is missing. Please try again.");
        } else {
            Log.i("TAG", "submitVisitData: " + invoiceType);
            getViewModel().visitCompletion(Utils.INSTANCE.getSimpleTextBody(currentVisitId.toString()), Utils.INSTANCE.getSimpleTextBody("Dumy Notes from Developer Side"), Utils.INSTANCE.getSimpleTextBody(invoiceType), null, null, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void pickImageFiles() {
        Intent cameraIntent = createCameraIntent();
        this.filePickerLauncher.launch(cameraIntent);
    }

    private final Intent createGalleryIntent() {
        Intent $this$createGalleryIntent_u24lambda_u2424 = new Intent("android.intent.action.OPEN_DOCUMENT");
        $this$createGalleryIntent_u24lambda_u2424.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        $this$createGalleryIntent_u24lambda_u2424.addCategory("android.intent.category.OPENABLE");
        $this$createGalleryIntent_u24lambda_u2424.setType("image/*");
        $this$createGalleryIntent_u24lambda_u2424.setFlags(65);
        return $this$createGalleryIntent_u24lambda_u2424;
    }

    private final Intent createCameraIntent() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        ContentValues values = new ContentValues();
        values.put("title", "New Picture");
        values.put("description", "From Camera");
        this.cameraImageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        intent.putExtra("output", this.cameraImageUri);
        return intent;
    }

    private final void handleURI(Uri uri) {
        if (this.pictureUriList.size() >= 3) {
            AppToast.INSTANCE.showToast("You can select only 3 images");
            return;
        }
        DocumentFile documentFile = DocumentFile.fromSingleUri(this, uri);
        if (documentFile != null && documentFile.isFile()) {
            File imagePath = FileExtKt.getFileFromUri(this, uri);
            if (imagePath != null) {
                if (!this.pictureUriList.contains(uri)) {
                    this.pictureUriList.add(uri);
                }
                AttachmentAdapter attachmentAdapter = this.attachmentAdapter;
                if (attachmentAdapter != null) {
                    attachmentAdapter.notifyDataSetChanged();
                }
                this.pictureUriList.isEmpty();
                return;
            }
            return;
        }
        AppToast.INSTANCE.showToast("Please select a valid Image file");
    }

    private final void setAttachmentAdapter() {
        this.attachmentAdapter = new AttachmentAdapter(this, this.pictureUriList, new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit attachmentAdapter$lambda$29;
                attachmentAdapter$lambda$29 = CompleteVisitActivity.setAttachmentAdapter$lambda$29(CompleteVisitActivity.this, ((Integer) obj).intValue());
                return attachmentAdapter$lambda$29;
            }
        });
        ActivityCompleteVisitBinding activityCompleteVisitBinding = this.binding;
        ActivityCompleteVisitBinding activityCompleteVisitBinding2 = null;
        if (activityCompleteVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding = null;
        }
        activityCompleteVisitBinding.attachmentRv.setAdapter(this.attachmentAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(0);
        ActivityCompleteVisitBinding activityCompleteVisitBinding3 = this.binding;
        if (activityCompleteVisitBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCompleteVisitBinding2 = activityCompleteVisitBinding3;
        }
        activityCompleteVisitBinding2.attachmentRv.setLayoutManager(manager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setAttachmentAdapter$lambda$29(final CompleteVisitActivity this$0, final int position) {
        new MaterialAlertDialogBuilder(this$0).setMessage(R.string.do_you_want_to_delete_this_item).setCancelable(false).setPositiveButton((CharSequence) this$0.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                CompleteVisitActivity.setAttachmentAdapter$lambda$29$lambda$27(CompleteVisitActivity.this, position, dialogInterface, i);
            }
        }).setNegativeButton((CharSequence) this$0.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$$ExternalSyntheticLambda6
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setAttachmentAdapter$lambda$29$lambda$27(CompleteVisitActivity this$0, int $position, DialogInterface dialog, int which) {
        this$0.pictureUriList.remove($position);
        AttachmentAdapter $this$setAttachmentAdapter_u24lambda_u2429_u24lambda_u2427_u24lambda_u2426 = this$0.attachmentAdapter;
        if ($this$setAttachmentAdapter_u24lambda_u2429_u24lambda_u2427_u24lambda_u2426 != null) {
            $this$setAttachmentAdapter_u24lambda_u2429_u24lambda_u2427_u24lambda_u2426.notifyItemRemoved($position);
            $this$setAttachmentAdapter_u24lambda_u2429_u24lambda_u2427_u24lambda_u2426.notifyItemRangeChanged($position, this$0.pictureUriList.size() - $position);
        }
        dialog.dismiss();
    }

    private final void openImagePicker() {
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        startActivityForResult(intent, this.IMAGE_PICKER_REQUEST_CODE);
    }

    private final void handleSelectedImages(List<? extends Uri> selectedUris) {
        this.pictureUriList.clear();
        this.pictureUriList.addAll(selectedUris);
        AttachmentAdapter attachmentAdapter = this.attachmentAdapter;
        if (attachmentAdapter != null) {
            attachmentAdapter.notifyDataSetChanged();
        }
    }

    private final void showKeyboard(View view) {
        Object systemService = getSystemService("input_method");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        InputMethodManager imm = (InputMethodManager) systemService;
        imm.showSoftInput(view, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fetchVisitProducts() {
        String visitId = ExtensionKt.getVisitId(this);
        if (visitId.length() == 0) {
            Log.e(LoggingInterceptor.TAG, "visitId is null or empty");
            return;
        }
        OkHttpClient client = new OkHttpClient();
        String url = "https://staging.bookmark.services/api/visit/get-product?visit_id=" + visitId;
        Log.d(LoggingInterceptor.TAG, "Fetching products from: " + url);
        Request.Builder addHeader = new Request.Builder().url(url).addHeader(HttpHeaders.ACCEPT, "application/json");
        String token = getToken();
        if (token == null) {
            token = "";
        }
        Request request = addHeader.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
        client.newCall(request).enqueue(new CompleteVisitActivity$fetchVisitProducts$1(this));
    }

    private final String getToken() {
        SharedPreferences sharedPref = getSharedPreferences("BookmarkApp", 0);
        return sharedPref.getString("AUTH_TOKEN", null);
    }

    public final AllProductsSelectedCartAdapter getAdapter() {
        return this.adapter;
    }

    public final void setAdapter(AllProductsSelectedCartAdapter allProductsSelectedCartAdapter) {
        this.adapter = allProductsSelectedCartAdapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showVisitProducts(List<Products> productsList) {
        ActivityCompleteVisitBinding activityCompleteVisitBinding = this.binding;
        ActivityCompleteVisitBinding activityCompleteVisitBinding2 = null;
        if (activityCompleteVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding = null;
        }
        activityCompleteVisitBinding.selectedProducts.setText(String.valueOf(productsList.size()));
        ActivityCompleteVisitBinding activityCompleteVisitBinding3 = this.binding;
        if (activityCompleteVisitBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding3 = null;
        }
        activityCompleteVisitBinding3.pbarFetchingBooks.setVisibility(8);
        ActivityCompleteVisitBinding activityCompleteVisitBinding4 = this.binding;
        if (activityCompleteVisitBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding4 = null;
        }
        activityCompleteVisitBinding4.recyclerView.setVisibility(0);
        this.adapter = new AllProductsSelectedCartAdapter(this, CollectionsKt.toMutableList((Collection) productsList), new CompleteVisitActivity$showVisitProducts$1(this));
        ActivityCompleteVisitBinding activityCompleteVisitBinding5 = this.binding;
        if (activityCompleteVisitBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding5 = null;
        }
        activityCompleteVisitBinding5.recyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        ActivityCompleteVisitBinding activityCompleteVisitBinding6 = this.binding;
        if (activityCompleteVisitBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding6 = null;
        }
        activityCompleteVisitBinding6.recyclerView.setAdapter(this.adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(this.itemTouchHelperCallback);
        ActivityCompleteVisitBinding activityCompleteVisitBinding7 = this.binding;
        if (activityCompleteVisitBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCompleteVisitBinding2 = activityCompleteVisitBinding7;
        }
        itemTouchHelper.attachToRecyclerView(activityCompleteVisitBinding2.recyclerView);
    }

    static /* synthetic */ void deleteProduct$default(CompleteVisitActivity completeVisitActivity, int i, String str, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = "5";
        }
        completeVisitActivity.deleteProduct(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteProduct(int productId, String quantity) {
        OkHttpClient client = new OkHttpClient();
        Log.d(LoggingInterceptor.TAG, "Deleting product from: https://staging.bookmark.services/api/visit/delete-product");
        FormBody.Builder formBodyBuilder = new FormBody.Builder(null, 1, null);
        formBodyBuilder.add("visitId", ExtensionKt.getVisitId(this));
        formBodyBuilder.add("products[0][product_id]", String.valueOf(productId));
        formBodyBuilder.add("products[0][quantity]", quantity);
        Request.Builder addHeader = new Request.Builder().url("https://staging.bookmark.services/api/visit/delete-product").post(formBodyBuilder.build()).addHeader(HttpHeaders.ACCEPT, "application/json");
        String token = getToken();
        if (token == null) {
            token = "";
        }
        Request request = addHeader.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
        client.newCall(request).enqueue(new CompleteVisitActivity$deleteProduct$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateProductQuantity(int productId, String quantity) {
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder(null, 1, null).add("visitId", ExtensionKt.getVisitId(this)).add("products[0][product_id]", String.valueOf(productId)).add("products[0][quantity]", quantity).build();
        Request.Builder addHeader = new Request.Builder().url("https://staging.bookmark.services/api/visit/update-product").post(formBody).addHeader(HttpHeaders.ACCEPT, "application/json");
        String token = getToken();
        if (token == null) {
            token = "";
        }
        Request request = addHeader.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
        client.newCall(request).enqueue(new Callback() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$updateProductQuantity$1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                Log.e("UpdateProduct", "Failed: " + e.getMessage());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                ResponseBody body = response.body();
                Log.d("UpdateProduct", "Response: " + (body != null ? body.string() : null));
            }
        });
    }

    public final ItemTouchHelper.SimpleCallback getItemTouchHelperCallback() {
        return this.itemTouchHelperCallback;
    }

    private final void setupInvoiceTypeList() {
        List invoiceTypes = CollectionsKt.listOf((Object[]) new String[]{"Cash", "Credit"});
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, invoiceTypes);
        ActivityCompleteVisitBinding activityCompleteVisitBinding = this.binding;
        ActivityCompleteVisitBinding activityCompleteVisitBinding2 = null;
        if (activityCompleteVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding = null;
        }
        activityCompleteVisitBinding.invoiceType.setAdapter(adapter);
        ActivityCompleteVisitBinding activityCompleteVisitBinding3 = this.binding;
        if (activityCompleteVisitBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCompleteVisitBinding2 = activityCompleteVisitBinding3;
        }
        activityCompleteVisitBinding2.invoiceType.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CompleteVisitActivity$$ExternalSyntheticLambda16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CompleteVisitActivity.setupInvoiceTypeList$lambda$31(CompleteVisitActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupInvoiceTypeList$lambda$31(CompleteVisitActivity this$0, View it) {
        ActivityCompleteVisitBinding activityCompleteVisitBinding = this$0.binding;
        if (activityCompleteVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding = null;
        }
        activityCompleteVisitBinding.invoiceType.showDropDown();
    }

    private final String saveSignatureToFile(Bitmap bitmap) {
        File file = new File(getCacheDir(), "signature_" + System.currentTimeMillis() + ".jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            FileOutputStream it = fileOutputStream;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, it);
            CloseableKt.closeFinally(fileOutputStream, null);
            String absolutePath = file.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
            return absolutePath;
        } finally {
        }
    }

    private final List<String> getImagePaths() {
        Iterable $this$mapNotNull$iv = this.pictureUriList;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            Uri uri = (Uri) element$iv$iv$iv;
            File fileFromUri = FileExtKt.getFileFromUri(this, uri);
            String absolutePath = fileFromUri != null ? fileFromUri.getAbsolutePath() : null;
            if (absolutePath != null) {
                destination$iv$iv.add(absolutePath);
            }
        }
        return (List) destination$iv$iv;
    }

    private final double calculateTotalAmount(List<Products> products) {
        Integer intOrNull;
        Double doubleOrNull;
        double total = com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON;
        List<Products> $this$forEach$iv = products;
        for (Object element$iv : $this$forEach$iv) {
            Products product = (Products) element$iv;
            String price = product.getPrice();
            double price2 = (price == null || (doubleOrNull = StringsKt.toDoubleOrNull(price)) == null) ? com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON : doubleOrNull.doubleValue();
            String quantity = product.getQuantity();
            int qty = (quantity == null || (intOrNull = StringsKt.toIntOrNull(quantity)) == null) ? 0 : intOrNull.intValue();
            total += qty * price2;
        }
        return total;
    }

    private final Pair<String, File> generateInvoicePdf(Invoice invoice) {
        Double doubleOrNull;
        Integer intOrNull;
        String invoiceId = "INV_" + System.currentTimeMillis();
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();
        Paint titlePaint = new Paint();
        titlePaint.setTextSize(18.0f);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        canvas.drawText("INVOICE", 240.0f, 40, titlePaint);
        int y = 40 + 30;
        paint.setTextSize(12.0f);
        float f = 20.0f;
        canvas.drawText("Customer Name: " + invoice.getEmployeeName(), 20.0f, y, paint);
        int y2 = y + 20;
        canvas.drawText("Customer ID: " + invoice.getEmployeeId(), 20.0f, y2, paint);
        int y3 = y2 + 20;
        canvas.drawText("Invoice Type: " + invoice.getInvoiceType(), 20.0f, y3, paint);
        int y4 = y3 + 20;
        canvas.drawText("Date: " + new SimpleDateFormat("dd MMM yyyy hh:mm a").format(new Date(invoice.getTime())), 20.0f, y4, paint);
        int y5 = y4 + 30;
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("Product", 20.0f, y5, paint);
        float f2 = 300.0f;
        canvas.drawText("Qty", 300.0f, y5, paint);
        canvas.drawText("Price", 350.0f, y5, paint);
        canvas.drawText("Total", 430.0f, y5, paint);
        int y6 = y5 + 15;
        paint.setTypeface(Typeface.DEFAULT);
        Iterable $this$forEach$iv = invoice.getProducts();
        for (Object element$iv : $this$forEach$iv) {
            Products it = (Products) element$iv;
            String quantity = it.getQuantity();
            int qty = (quantity == null || (intOrNull = StringsKt.toIntOrNull(quantity)) == null) ? 0 : intOrNull.intValue();
            String price = it.getPrice();
            double price2 = (price == null || (doubleOrNull = StringsKt.toDoubleOrNull(price)) == null) ? com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON : doubleOrNull.doubleValue();
            String title = it.getTitle();
            if (title == null) {
                title = "";
            }
            canvas.drawText(title, f, y6, paint);
            canvas.drawText(String.valueOf(qty), f2, y6, paint);
            canvas.drawText(String.valueOf(price2), 350.0f, y6, paint);
            canvas.drawText(String.valueOf(qty * price2), 430.0f, y6, paint);
            y6 += 20;
            f = 20.0f;
            f2 = 300.0f;
        }
        int y7 = y6 + 20;
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("Total Amount: " + invoice.getTotalAmount(), 350.0f, y7, paint);
        int y8 = y7 + 30;
        paint.setTypeface(Typeface.DEFAULT);
        canvas.drawText("Signature:", 20.0f, y8, paint);
        int y9 = y8 + 10;
        Bitmap it2 = BitmapFactory.decodeFile(invoice.getSignaturePath());
        if (it2 != null) {
            Bitmap scaled = Bitmap.createScaledBitmap(it2, 150, 80, true);
            Intrinsics.checkNotNullExpressionValue(scaled, "createScaledBitmap(...)");
            canvas.drawBitmap(scaled, 20.0f, y9, (Paint) null);
        }
        pdfDocument.finishPage(page);
        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Invoices");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, invoiceId + ".pdf");
        pdfDocument.writeTo(new FileOutputStream(file));
        pdfDocument.close();
        return new Pair<>(invoiceId, file);
    }

    private final MultipartBody.Part createInvoicePdfPart(File pdfFile) {
        RequestBody requestBody = RequestBody.INSTANCE.create(pdfFile, MediaType.INSTANCE.parse("application/pdf"));
        return MultipartBody.Part.INSTANCE.createFormData("invoice", pdfFile.getName(), requestBody);
    }

    private final boolean ensureInvoiceGenerated() {
        List products;
        if (this.isInvoiceGenerated) {
            Log.d("INVOICE", "Invoice already generated: " + this.generatedInvoiceId);
            return true;
        }
        ActivityCompleteVisitBinding activityCompleteVisitBinding = this.binding;
        ActivityCompleteVisitBinding activityCompleteVisitBinding2 = null;
        if (activityCompleteVisitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding = null;
        }
        Bitmap signatureBitmap = activityCompleteVisitBinding.signaturePad.getSignatureBitmap();
        AllProductsSelectedCartAdapter allProductsSelectedCartAdapter = this.adapter;
        if (allProductsSelectedCartAdapter == null || (products = allProductsSelectedCartAdapter.getAllItems()) == null) {
            products = CollectionsKt.emptyList();
        }
        double totalAmount = calculateTotalAmount(products);
        ActivityCompleteVisitBinding activityCompleteVisitBinding3 = this.binding;
        if (activityCompleteVisitBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding3 = null;
        }
        String obj = activityCompleteVisitBinding3.title.getText().toString();
        List<String> imagePaths = getImagePaths();
        Intrinsics.checkNotNull(signatureBitmap);
        String saveSignatureToFile = saveSignatureToFile(signatureBitmap);
        ActivityCompleteVisitBinding activityCompleteVisitBinding4 = this.binding;
        if (activityCompleteVisitBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding4 = null;
        }
        String obj2 = activityCompleteVisitBinding4.invoiceType.getText().toString();
        ActivityCompleteVisitBinding activityCompleteVisitBinding5 = this.binding;
        if (activityCompleteVisitBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCompleteVisitBinding5 = null;
        }
        String obj3 = activityCompleteVisitBinding5.agentId.getText().toString();
        ActivityCompleteVisitBinding activityCompleteVisitBinding6 = this.binding;
        if (activityCompleteVisitBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCompleteVisitBinding2 = activityCompleteVisitBinding6;
        }
        Invoice invoice = new Invoice(products, obj, imagePaths, saveSignatureToFile, obj2, obj3, activityCompleteVisitBinding2.agentName.getText().toString(), System.currentTimeMillis(), totalAmount);
        Pair<String, File> generateInvoicePdf = generateInvoicePdf(invoice);
        String invoiceId = generateInvoicePdf.component1();
        File pdfFile = generateInvoicePdf.component2();
        this.generatedInvoiceId = invoiceId;
        this.generatedInvoiceFile = pdfFile;
        this.invoicePdfPart = createInvoicePdfPart(pdfFile);
        this.isInvoiceGenerated = true;
        Log.d("INVOICE", "Invoice generated successfully: " + invoiceId);
        return true;
    }
}
