package com.ingenious.androidbookmarksalesupgrade.bindingAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.extensions.ActivityExtKt;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppBindingAdapters.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0007J\u001a\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\tH\u0007J\u001a\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\tH\u0007J\u001a\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\tH\u0007¨\u0006\u0016"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/bindingAdapter/AppBindingAdapters;", "", "<init>", "()V", "setImageUsingGlide", "", "imageView", "Landroid/widget/ImageView;", "imageUrl", "", "setTextIfNotEmpty", "textView", "Landroid/widget/TextView;", "text", "setStatusUI", "view", "Landroid/view/View;", NotificationCompat.CATEGORY_STATUS, "setRelativeTime", "dateString", "setCustomerStyle", "type", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class AppBindingAdapters {
    public static final AppBindingAdapters INSTANCE = new AppBindingAdapters();

    private AppBindingAdapters() {
    }

    @BindingAdapter({"setImageUsingGlide"})
    @JvmStatic
    public static final void setImageUsingGlide(ImageView imageView, String imageUrl) {
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        String str = imageUrl;
        if (!(str == null || str.length() == 0)) {
            Glide.with(imageView.getContext()).load(imageUrl).placeholder(R.drawable.photo).into(imageView);
        }
    }

    @BindingAdapter({"setTextIfNotEmpty"})
    @JvmStatic
    public static final void setTextIfNotEmpty(TextView textView, String text) {
        Intrinsics.checkNotNullParameter(textView, "textView");
        Intrinsics.checkNotNullParameter(text, "text");
        if (ActivityExtKt.isValid(text)) {
            textView.setText(text);
        } else {
            ActivityExtKt.visible(textView, false);
        }
    }

    @BindingAdapter({"statusUI"})
    @JvmStatic
    public static final void setStatusUI(View view, String status) {
        String str;
        Intrinsics.checkNotNullParameter(view, "view");
        Context context = view.getContext();
        int green = ContextCompat.getColor(context, R.color.approved_color);
        int orange = ContextCompat.getColor(context, R.color.pending_color);
        Drawable greenBg = ContextCompat.getDrawable(context, R.drawable.edittext_background_green);
        Drawable orangeBg = ContextCompat.getDrawable(context, R.drawable.edittext_background_orange);
        if (status != null) {
            str = status.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(str, "toLowerCase(...)");
        } else {
            str = null;
        }
        if (Intrinsics.areEqual(str, "approved")) {
            if (view instanceof LinearLayout) {
                ((LinearLayout) view).setBackground(greenBg);
            }
            if (view instanceof ImageView) {
                ((ImageView) view).setColorFilter(green);
            }
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(green);
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(str, "pending")) {
            if (view instanceof LinearLayout) {
                ((LinearLayout) view).setBackground(orangeBg);
            }
            if (view instanceof ImageView) {
                ((ImageView) view).setColorFilter(orange);
            }
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(orange);
            }
        }
    }

    @BindingAdapter({"relativeTime"})
    @JvmStatic
    public static final void setRelativeTime(TextView textView, String dateString) {
        String text;
        Intrinsics.checkNotNullParameter(textView, "textView");
        String str = dateString;
        if (str == null || str.length() == 0) {
            return;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault());
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date parse = format.parse(dateString);
            if (parse == null) {
                return;
            }
            long time = parse.getTime();
            long now = System.currentTimeMillis();
            long diff = now - time;
            long seconds = diff / 1000;
            long j = 60;
            long minutes = seconds / j;
            long hours = minutes / j;
            long days = hours / 24;
            if (seconds < 60) {
                text = "Just now";
            } else if (minutes < 60) {
                text = minutes + " minutes ago";
            } else if (hours < 24) {
                text = hours + " hours ago";
            } else if (days < 7) {
                text = days + " days ago";
            } else if (days < 30) {
                text = (days / 7) + " weeks ago";
            } else {
                text = days < 365 ? (days / 30) + " months ago" : (days / 365) + " years ago";
            }
            textView.setText(text);
        } catch (Exception e) {
            textView.setText("");
        }
    }

    @BindingAdapter({"setCustomerStyle"})
    @JvmStatic
    public static final void setCustomerStyle(TextView view, String type) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (Intrinsics.areEqual(type, "Schools")) {
            view.setBackgroundTintList(ContextCompat.getColorStateList(view.getContext(), R.color.tag_color1));
            view.setTextColor(ContextCompat.getColor(view.getContext(), R.color.blue_text));
            view.setCompoundDrawablesWithIntrinsicBounds(R.drawable.school_icon, 0, 0, 0);
        } else if (Intrinsics.areEqual(type, "Bookshops")) {
            view.setBackgroundTintList(ContextCompat.getColorStateList(view.getContext(), R.color.orange_bookshop));
            view.setTextColor(ContextCompat.getColor(view.getContext(), R.color.orange_text));
            view.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shop_icon, 0, 0, 0);
        } else {
            view.setBackgroundTintList(ContextCompat.getColorStateList(view.getContext(), R.color.tag_color1));
            view.setTextColor(ContextCompat.getColor(view.getContext(), R.color.blue_text));
            view.setCompoundDrawablesWithIntrinsicBounds(R.drawable.school_icon, 0, 0, 0);
        }
    }
}
