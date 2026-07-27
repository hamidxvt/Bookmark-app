package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityRequestDetailBinding;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RequestDetailActivity.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/RequestDetailActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityRequestDetailBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class RequestDetailActivity extends AppCompatActivity {
    private ActivityRequestDetailBinding binding;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        String str;
        super.onCreate(savedInstanceState);
        this.binding = ActivityRequestDetailBinding.inflate(getLayoutInflater());
        ActivityRequestDetailBinding activityRequestDetailBinding = this.binding;
        if (activityRequestDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRequestDetailBinding = null;
        }
        setContentView(activityRequestDetailBinding.getRoot());
        ExtensionKt.belowStatusBarText(this);
        ActivityRequestDetailBinding activityRequestDetailBinding2 = this.binding;
        if (activityRequestDetailBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRequestDetailBinding2 = null;
        }
        activityRequestDetailBinding2.backButton.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.RequestDetailActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RequestDetailActivity.this.finish();
            }
        });
        String requestId = getIntent().getStringExtra("request_id");
        String title = getIntent().getStringExtra("title");
        String category = getIntent().getStringExtra("category");
        String details = getIntent().getStringExtra("details");
        String status = getIntent().getStringExtra(NotificationCompat.CATEGORY_STATUS);
        String createdAt = getIntent().getStringExtra("created_at");
        Iterable photos = getIntent().getStringArrayListExtra("photos");
        Log.i("TAG", "onCreate: " + photos);
        ActivityRequestDetailBinding activityRequestDetailBinding3 = this.binding;
        if (activityRequestDetailBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRequestDetailBinding3 = null;
        }
        activityRequestDetailBinding3.tvRequestId.setText("Request #" + requestId);
        ActivityRequestDetailBinding activityRequestDetailBinding4 = this.binding;
        if (activityRequestDetailBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRequestDetailBinding4 = null;
        }
        activityRequestDetailBinding4.tvTitle.setText(title);
        ActivityRequestDetailBinding activityRequestDetailBinding5 = this.binding;
        if (activityRequestDetailBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRequestDetailBinding5 = null;
        }
        activityRequestDetailBinding5.tvCategory.setText(category);
        ActivityRequestDetailBinding activityRequestDetailBinding6 = this.binding;
        if (activityRequestDetailBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRequestDetailBinding6 = null;
        }
        activityRequestDetailBinding6.tvDetails.setText(details);
        ActivityRequestDetailBinding activityRequestDetailBinding7 = this.binding;
        if (activityRequestDetailBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRequestDetailBinding7 = null;
        }
        activityRequestDetailBinding7.tvCreatedAt.setText(createdAt);
        if (status != null) {
            str = status.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(str, "toLowerCase(...)");
        } else {
            str = null;
        }
        if (Intrinsics.areEqual(str, "resolved")) {
            ActivityRequestDetailBinding activityRequestDetailBinding8 = this.binding;
            if (activityRequestDetailBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityRequestDetailBinding8 = null;
            }
            activityRequestDetailBinding8.tvStatus.setText("• Resolved");
            ActivityRequestDetailBinding activityRequestDetailBinding9 = this.binding;
            if (activityRequestDetailBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityRequestDetailBinding9 = null;
            }
            activityRequestDetailBinding9.tvStatus.setTextColor(getColor(R.color.green));
            ActivityRequestDetailBinding activityRequestDetailBinding10 = this.binding;
            if (activityRequestDetailBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityRequestDetailBinding10 = null;
            }
            activityRequestDetailBinding10.tvStatus.setBackgroundResource(R.drawable.bg_status_resolved);
            ActivityRequestDetailBinding activityRequestDetailBinding11 = this.binding;
            if (activityRequestDetailBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityRequestDetailBinding11 = null;
            }
            TextView $this$onCreate_u24lambda_u241 = activityRequestDetailBinding11.tvReviewMessage;
            $this$onCreate_u24lambda_u241.setText("Your request has been resolved successfully.");
            $this$onCreate_u24lambda_u241.setTextColor(getColor(R.color.green));
            $this$onCreate_u24lambda_u241.setBackgroundResource(R.drawable.request_box);
            $this$onCreate_u24lambda_u241.setVisibility(0);
            Intrinsics.checkNotNull($this$onCreate_u24lambda_u241);
        } else if (Intrinsics.areEqual(str, "pending")) {
            ActivityRequestDetailBinding activityRequestDetailBinding12 = this.binding;
            if (activityRequestDetailBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityRequestDetailBinding12 = null;
            }
            activityRequestDetailBinding12.tvStatus.setText("• Pending");
            ActivityRequestDetailBinding activityRequestDetailBinding13 = this.binding;
            if (activityRequestDetailBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityRequestDetailBinding13 = null;
            }
            activityRequestDetailBinding13.tvStatus.setBackgroundResource(R.drawable.bg_status_pending);
            ActivityRequestDetailBinding activityRequestDetailBinding14 = this.binding;
            if (activityRequestDetailBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityRequestDetailBinding14 = null;
            }
            TextView $this$onCreate_u24lambda_u242 = activityRequestDetailBinding14.tvReviewMessage;
            $this$onCreate_u24lambda_u242.setText("We’re reviewing your request.");
            $this$onCreate_u24lambda_u242.setTextColor(getColor(R.color.pending_color));
            $this$onCreate_u24lambda_u242.setBackgroundResource(R.drawable.request_box);
            $this$onCreate_u24lambda_u242.setVisibility(0);
            Intrinsics.checkNotNull($this$onCreate_u24lambda_u242);
        } else {
            ActivityRequestDetailBinding activityRequestDetailBinding15 = this.binding;
            if (activityRequestDetailBinding15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityRequestDetailBinding15 = null;
            }
            activityRequestDetailBinding15.tvStatus.setText("• Unknown");
            ActivityRequestDetailBinding activityRequestDetailBinding16 = this.binding;
            if (activityRequestDetailBinding16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityRequestDetailBinding16 = null;
            }
            activityRequestDetailBinding16.tvStatus.setBackgroundResource(R.drawable.bg_status_unknown);
            ActivityRequestDetailBinding activityRequestDetailBinding17 = this.binding;
            if (activityRequestDetailBinding17 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityRequestDetailBinding17 = null;
            }
            activityRequestDetailBinding17.tvReviewMessage.setVisibility(8);
        }
        if (photos == null) {
            return;
        }
        Iterable $this$forEach$iv = photos;
        for (Object element$iv : $this$forEach$iv) {
            String url = (String) element$iv;
            ImageView img = new ImageView(this);
            String requestId2 = requestId;
            int size = getResources().getDimensionPixelSize(R.dimen.photo_size);
            String title2 = title;
            int margin = getResources().getDimensionPixelSize(R.dimen.photo_margin);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
            params.setMargins(0, 0, margin, 0);
            img.setLayoutParams(params);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            String category2 = category;
            String details2 = details;
            String status2 = status;
            Glide.with((FragmentActivity) this).load(url).apply((BaseRequestOptions<?>) new RequestOptions().transform(new CenterCrop(), new RoundedCorners(30))).into(img);
            ActivityRequestDetailBinding activityRequestDetailBinding18 = this.binding;
            if (activityRequestDetailBinding18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityRequestDetailBinding18 = null;
            }
            activityRequestDetailBinding18.photoContainer.addView(img);
            requestId = requestId2;
            title = title2;
            category = category2;
            details = details2;
            status = status2;
        }
    }
}
