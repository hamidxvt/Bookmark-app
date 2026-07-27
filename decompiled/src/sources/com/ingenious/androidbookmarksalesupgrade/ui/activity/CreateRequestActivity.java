package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.R;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.button.MaterialButton;
import com.google.common.net.HttpHeaders;
import com.google.firebase.messaging.Constants;
import com.ingenious.androidbookmarksalesupgrade.adapter.ImageAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityCreateRequestBinding;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/* compiled from: CreateRequestActivity.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u000fH\u0002J\b\u0010\u0013\u001a\u00020\u000fH\u0002J\"\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u000fH\u0002J\u0018\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0007H\u0002J\n\u0010\u001d\u001a\u0004\u0018\u00010\u0007H\u0002J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001f\u001a\u00020\tH\u0002J\b\u0010 \u001a\u00020\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/CreateRequestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityCreateRequestBinding;", "selectedCategory", "", "selectedImageUri", "Landroid/net/Uri;", "PICK_IMAGE", "", "selectedImages", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupCategoryButtons", "pickImage", "onActivityResult", "requestCode", "resultCode", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Landroid/content/Intent;", "validateAndSubmit", "submitRequest", "title", "details", "getToken", "getRealPathFromURI", "uri", "showSelectedImages", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class CreateRequestActivity extends AppCompatActivity {
    private ActivityCreateRequestBinding binding;
    private String selectedCategory;
    private Uri selectedImageUri;
    private final int PICK_IMAGE = TypedValues.TYPE_TARGET;
    private final List<Uri> selectedImages = new ArrayList();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityCreateRequestBinding.inflate(getLayoutInflater());
        ActivityCreateRequestBinding activityCreateRequestBinding = this.binding;
        ActivityCreateRequestBinding activityCreateRequestBinding2 = null;
        if (activityCreateRequestBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCreateRequestBinding = null;
        }
        setContentView(activityCreateRequestBinding.getRoot());
        ExtensionKt.belowStatusBarText(this);
        setupCategoryButtons();
        ActivityCreateRequestBinding activityCreateRequestBinding3 = this.binding;
        if (activityCreateRequestBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCreateRequestBinding3 = null;
        }
        activityCreateRequestBinding3.backButton.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CreateRequestActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CreateRequestActivity.this.finish();
            }
        });
        ActivityCreateRequestBinding activityCreateRequestBinding4 = this.binding;
        if (activityCreateRequestBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCreateRequestBinding4 = null;
        }
        activityCreateRequestBinding4.uploadContainer.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CreateRequestActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CreateRequestActivity.this.pickImage();
            }
        });
        ActivityCreateRequestBinding activityCreateRequestBinding5 = this.binding;
        if (activityCreateRequestBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCreateRequestBinding2 = activityCreateRequestBinding5;
        }
        activityCreateRequestBinding2.btnSubmit.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CreateRequestActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CreateRequestActivity.this.validateAndSubmit();
            }
        });
    }

    private final void setupCategoryButtons() {
        MaterialButton[] materialButtonArr = new MaterialButton[5];
        ActivityCreateRequestBinding activityCreateRequestBinding = this.binding;
        ActivityCreateRequestBinding activityCreateRequestBinding2 = null;
        if (activityCreateRequestBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCreateRequestBinding = null;
        }
        materialButtonArr[0] = activityCreateRequestBinding.btnAdoption;
        ActivityCreateRequestBinding activityCreateRequestBinding3 = this.binding;
        if (activityCreateRequestBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCreateRequestBinding3 = null;
        }
        materialButtonArr[1] = activityCreateRequestBinding3.btnInventory;
        ActivityCreateRequestBinding activityCreateRequestBinding4 = this.binding;
        if (activityCreateRequestBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCreateRequestBinding4 = null;
        }
        materialButtonArr[2] = activityCreateRequestBinding4.btnInvoice;
        ActivityCreateRequestBinding activityCreateRequestBinding5 = this.binding;
        if (activityCreateRequestBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCreateRequestBinding5 = null;
        }
        materialButtonArr[3] = activityCreateRequestBinding5.btnAppIssue;
        ActivityCreateRequestBinding activityCreateRequestBinding6 = this.binding;
        if (activityCreateRequestBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCreateRequestBinding2 = activityCreateRequestBinding6;
        }
        materialButtonArr[4] = activityCreateRequestBinding2.btnOther;
        final List buttons = CollectionsKt.listOf((Object[]) materialButtonArr);
        List $this$forEach$iv = buttons;
        for (Object element$iv : $this$forEach$iv) {
            final MaterialButton button = (MaterialButton) element$iv;
            button.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.CreateRequestActivity$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CreateRequestActivity.setupCategoryButtons$lambda$5$lambda$4(buttons, button, this, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCategoryButtons$lambda$5$lambda$4(List $buttons, MaterialButton $button, CreateRequestActivity this$0, View it) {
        List $this$forEach$iv = $buttons;
        for (Object element$iv : $this$forEach$iv) {
            MaterialButton it2 = (MaterialButton) element$iv;
            it2.setStrokeColorResource(R.color.darker_gray);
            it2.setTextColor(this$0.getResources().getColor(R.color.black));
            it2.setBackgroundColor(this$0.getResources().getColor(R.color.white));
        }
        $button.setStrokeColorResource(com.ingenious.androidbookmarksalesupgrade.R.color.app_color);
        $button.setTextColor(this$0.getResources().getColor(com.ingenious.androidbookmarksalesupgrade.R.color.app_color));
        $button.setBackgroundColor(this$0.getResources().getColor(R.color.white));
        this$0.selectedCategory = $button.getText().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void pickImage() {
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        startActivityForResult(intent, this.PICK_IMAGE);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.PICK_IMAGE && resultCode == -1) {
            this.selectedImages.clear();
            if ((data != null ? data.getClipData() : null) != null) {
                ClipData clipData = data.getClipData();
                Intrinsics.checkNotNull(clipData);
                int count = clipData.getItemCount();
                if (count > 3) {
                    Toast.makeText(this, "You can select maximum 3 images", 0).show();
                    return;
                }
                for (int i = 0; i < count; i++) {
                    ClipData clipData2 = data.getClipData();
                    Intrinsics.checkNotNull(clipData2);
                    Uri imageUri = clipData2.getItemAt(i).getUri();
                    getContentResolver().takePersistableUriPermission(imageUri, 1);
                    List<Uri> list = this.selectedImages;
                    Intrinsics.checkNotNull(imageUri);
                    list.add(imageUri);
                }
            } else if ((data != null ? data.getData() : null) != null) {
                List<Uri> list2 = this.selectedImages;
                Uri data2 = data.getData();
                Intrinsics.checkNotNull(data2);
                list2.add(data2);
            }
            showSelectedImages();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void validateAndSubmit() {
        ActivityCreateRequestBinding activityCreateRequestBinding = this.binding;
        ActivityCreateRequestBinding activityCreateRequestBinding2 = null;
        if (activityCreateRequestBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCreateRequestBinding = null;
        }
        String title = StringsKt.trim((CharSequence) activityCreateRequestBinding.inputTitle.getText().toString()).toString();
        ActivityCreateRequestBinding activityCreateRequestBinding3 = this.binding;
        if (activityCreateRequestBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCreateRequestBinding2 = activityCreateRequestBinding3;
        }
        String details = StringsKt.trim((CharSequence) activityCreateRequestBinding2.inputDetails.getText().toString()).toString();
        String str = this.selectedCategory;
        if (str == null || str.length() == 0) {
            Toast.makeText(this, "Please select a category", 0).show();
            return;
        }
        if (title.length() == 0) {
            Toast.makeText(this, "Enter a title", 0).show();
            return;
        }
        if (details.length() == 0) {
            Toast.makeText(this, "Enter details", 0).show();
        } else {
            submitRequest(title, details);
        }
    }

    private final void submitRequest(String title, String details) {
        String str;
        ActivityCreateRequestBinding activityCreateRequestBinding = this.binding;
        if (activityCreateRequestBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCreateRequestBinding = null;
        }
        activityCreateRequestBinding.progressBar.setVisibility(0);
        ActivityCreateRequestBinding activityCreateRequestBinding2 = this.binding;
        if (activityCreateRequestBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCreateRequestBinding2 = null;
        }
        activityCreateRequestBinding2.btnSubmit.setEnabled(false);
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder addFormDataPart = new MultipartBody.Builder(null, 1, null).setType(MultipartBody.FORM).addFormDataPart("title", title).addFormDataPart("details", details);
        String str2 = this.selectedCategory;
        String str3 = "";
        if (str2 == null) {
            str2 = "";
        }
        MultipartBody.Builder $this$submitRequest_u24lambda_u247 = addFormDataPart.addFormDataPart("category", str2);
        Iterable $this$forEachIndexed$iv = this.selectedImages;
        int index$iv = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv2 = index$iv + 1;
            if (index$iv < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Uri uri = (Uri) item$iv;
            InputStream inputStream = getContentResolver().openInputStream(uri);
            if (inputStream != null) {
                byte[] bytes = ByteStreamsKt.readBytes(inputStream);
                RequestBody requestBody = RequestBody.Companion.create$default(RequestBody.INSTANCE, bytes, MediaType.INSTANCE.parse("image/*"), 0, 0, 6, (Object) null);
                str = str3;
                $this$submitRequest_u24lambda_u247.addFormDataPart("photo[]", "image_" + index$iv + ".jpg", requestBody);
            } else {
                str = str3;
            }
            index$iv = index$iv2;
            str3 = str;
        }
        String str4 = str3;
        MultipartBody multipartBody = $this$submitRequest_u24lambda_u247.build();
        Request.Builder url = new Request.Builder().url("https://staging.bookmark.services/api/requests/create");
        String token = getToken();
        Request request = url.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + (token == null ? str4 : token)).post(multipartBody).build();
        client.newCall(request).enqueue(new CreateRequestActivity$submitRequest$1(this));
    }

    private final String getToken() {
        SharedPreferences sharedPref = getSharedPreferences("BookmarkApp", 0);
        return sharedPref.getString("AUTH_TOKEN", null);
    }

    private final String getRealPathFromURI(Uri uri) {
        String[] projection = {"_data"};
        Cursor query = getContentResolver().query(uri, projection, null, null, null);
        if (query == null) {
            return null;
        }
        Cursor cursor = query;
        try {
            Cursor cursor2 = cursor;
            int columnIndex = cursor2.getColumnIndexOrThrow("_data");
            cursor2.moveToFirst();
            String string = cursor2.getString(columnIndex);
            CloseableKt.closeFinally(cursor, null);
            return string;
        } finally {
        }
    }

    private final void showSelectedImages() {
        ActivityCreateRequestBinding activityCreateRequestBinding = this.binding;
        if (activityCreateRequestBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCreateRequestBinding = null;
        }
        activityCreateRequestBinding.imageRecycler.setAdapter(new ImageAdapter(this.selectedImages));
    }
}
