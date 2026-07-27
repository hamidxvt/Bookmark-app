package com.ingenious.androidbookmarksalesupgrade.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.adapter.GenericAdapter;
import com.ingenious.androidbookmarksalesupgrade.adapter.PastVisitsAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemPastVisitsListBinding;
import com.ingenious.androidbookmarksalesupgrade.model.response.PastVisitsList;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.apache.commons.lang3.StringUtils;

/* compiled from: PastVisitsAdapter.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001aB-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0004\b\n\u0010\u000bJ\u001c\u0010\u000e\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001c\u0010\u0013\u001a\u00020\u00142\n\u0010\u0015\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0012H\u0016J\b\u0010\u0017\u001a\u00020\u0012H\u0016J\u0014\u0010\u0018\u001a\u00020\u00142\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/PastVisitsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/PastVisitsAdapter$ViewHolder;", "context", "Landroid/content/Context;", FirebaseAnalytics.Param.ITEMS, "", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/PastVisitsList;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter$OnItemClickListener;", "<init>", "(Landroid/content/Context;Ljava/util/List;Lcom/ingenious/androidbookmarksalesupgrade/adapter/GenericAdapter$OnItemClickListener;)V", "getContext", "()Landroid/content/Context;", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "onBindViewHolder", "", "holder", "position", "getItemCount", "addList", "newItems", "ViewHolder", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class PastVisitsAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private List<PastVisitsList> items;
    private final GenericAdapter.OnItemClickListener<PastVisitsList> listener;

    public /* synthetic */ PastVisitsAdapter(Context context, List list, GenericAdapter.OnItemClickListener onItemClickListener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? CollectionsKt.emptyList() : list, onItemClickListener);
    }

    public final Context getContext() {
        return this.context;
    }

    public PastVisitsAdapter(Context context, List<PastVisitsList> items, GenericAdapter.OnItemClickListener<PastVisitsList> listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemPastVisitsListBinding binding = ItemPastVisitsListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "inflate(...)");
        return new ViewHolder(this, binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.items.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: getItemCount */
    public int getTabCount() {
        return this.items.size();
    }

    public final void addList(List<PastVisitsList> newItems) {
        Intrinsics.checkNotNullParameter(newItems, "newItems");
        this.items = CollectionsKt.toList(newItems);
        notifyDataSetChanged();
    }

    /* compiled from: PastVisitsAdapter.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/PastVisitsAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemPastVisitsListBinding;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/PastVisitsAdapter;Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemPastVisitsListBinding;)V", "timer", "Landroid/os/CountDownTimer;", "clearTimer", "", "bind", "item", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/PastVisitsList;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemPastVisitsListBinding binding;
        final /* synthetic */ PastVisitsAdapter this$0;
        private CountDownTimer timer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(PastVisitsAdapter this$0, ItemPastVisitsListBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = this$0;
            this.binding = binding;
        }

        public final void clearTimer() {
            CountDownTimer countDownTimer = this.timer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            this.timer = null;
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
        java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
         */
        /* JADX WARN: Type inference failed for: r2v12, types: [java.time.ZonedDateTime] */
        public final void bind(final PastVisitsList item) {
            String str;
            String str2;
            Intrinsics.checkNotNullParameter(item, "item");
            clearTimer();
            this.binding.setItem(item);
            this.binding.setListener(this.this$0.listener);
            String customerType = item.getCustomerType();
            String str3 = null;
            if (customerType != null) {
                str = customerType.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(str, "toLowerCase(...)");
            } else {
                str = null;
            }
            if (Intrinsics.areEqual(str, "schools")) {
                this.binding.customerType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.school_icon, 0, 0, 0);
                this.binding.customerType.setTextColor(ContextCompat.getColor(this.this$0.getContext(), R.color.blue_text));
                this.binding.customerType.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this.this$0.getContext(), R.color.tag_color1)));
            } else if (Intrinsics.areEqual(str, "bookshops")) {
                this.binding.customerType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shop_icon, 0, 0, 0);
                this.binding.customerType.setTextColor(ContextCompat.getColor(this.this$0.getContext(), R.color.orange_text));
                this.binding.customerType.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this.this$0.getContext(), R.color.orange_bookshop)));
            }
            String priority = item.getPriority();
            if (priority != null) {
                str2 = priority.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(str2, "toLowerCase(...)");
            } else {
                str2 = null;
            }
            if (str2 != null) {
                switch (str2.hashCode()) {
                    case -1078030475:
                        if (str2.equals("medium")) {
                            this.binding.priority.setCompoundDrawablesWithIntrinsicBounds(R.drawable.medium_dot, 0, 0, 0);
                            this.binding.priority.setTextColor(ContextCompat.getColor(this.this$0.getContext(), R.color.medium_color));
                            this.binding.priority.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this.this$0.getContext(), R.color.inventory_light_color)));
                            break;
                        }
                        break;
                    case 107348:
                        if (str2.equals("low")) {
                            this.binding.priority.setCompoundDrawablesWithIntrinsicBounds(R.drawable.low_dot, 0, 0, 0);
                            this.binding.priority.setTextColor(ContextCompat.getColor(this.this$0.getContext(), R.color.low_color));
                            this.binding.priority.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this.this$0.getContext(), R.color.tag_color4)));
                            break;
                        }
                        break;
                    case 3202466:
                        if (str2.equals("high")) {
                            this.binding.priority.setCompoundDrawablesWithIntrinsicBounds(R.drawable.high_dot, 0, 0, 0);
                            this.binding.priority.setTextColor(ContextCompat.getColor(this.this$0.getContext(), R.color.app_color));
                            this.binding.priority.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this.this$0.getContext(), R.color.tag_color3)));
                            break;
                        }
                        break;
                }
            }
            String visittype = item.getVisittype();
            if (visittype != null) {
                str3 = visittype.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(str3, "toLowerCase(...)");
            }
            if (Intrinsics.areEqual(str3, "admin")) {
                this.binding.visitType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.admin_icon, 0, 0, 0);
                this.binding.visitType.setTextColor(ContextCompat.getColor(this.this$0.getContext(), R.color.tag_text_color2));
                this.binding.visitType.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this.this$0.getContext(), R.color.tag_color2)));
            } else if (Intrinsics.areEqual(str3, "self")) {
                this.binding.visitType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.self_icon, 0, 0, 0);
                this.binding.visitType.setTextColor(ContextCompat.getColor(this.this$0.getContext(), R.color.tag_text_color1));
                this.binding.visitType.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this.this$0.getContext(), R.color.tag_color1)));
            }
            if (Intrinsics.areEqual(item.getStatus(), "on-going")) {
                this.binding.btnCheckIn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this.this$0.getContext(), R.color.green_text)));
                String visitDate = item.getVisitDate();
                if (!(visitDate == null || visitDate.length() == 0)) {
                    String visitStartTime = item.getVisitStartTime();
                    if (!(visitStartTime == null || visitStartTime.length() == 0)) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime visitStart = LocalDateTime.parse(item.getVisitDate() + StringUtils.SPACE + item.getVisitStartTime(), formatter);
                        final long startMillis = visitStart.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                        this.timer = new CountDownTimer() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.PastVisitsAdapter$ViewHolder$bind$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(Long.MAX_VALUE, 1000L);
                            }

                            @Override // android.os.CountDownTimer
                            public void onTick(long millisUntilFinished) {
                                ItemPastVisitsListBinding itemPastVisitsListBinding;
                                long elapsed = System.currentTimeMillis() - startMillis;
                                Duration duration = Duration.ofMillis(elapsed);
                                long h = duration.toHours();
                                long j = 60;
                                long m = duration.toMinutes() % j;
                                long s = duration.getSeconds() % j;
                                itemPastVisitsListBinding = this.binding;
                                Button button = itemPastVisitsListBinding.btnCheckIn;
                                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                String format = String.format("On-Going %02d:%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(h), Long.valueOf(m), Long.valueOf(s)}, 3));
                                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                                button.setText(format);
                            }

                            @Override // android.os.CountDownTimer
                            public void onFinish() {
                            }
                        };
                        CountDownTimer countDownTimer = this.timer;
                        if (countDownTimer != null) {
                            countDownTimer.onTick(0L);
                        }
                        CountDownTimer countDownTimer2 = this.timer;
                        if (countDownTimer2 != null) {
                            countDownTimer2.start();
                        }
                    }
                }
                this.binding.btnCheckIn.setText("On-Going");
            } else {
                this.binding.btnCheckIn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this.this$0.getContext(), R.color.app_color)));
                this.binding.btnCheckIn.setText("Check-In");
            }
            View root = this.binding.getRoot();
            final PastVisitsAdapter pastVisitsAdapter = this.this$0;
            root.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.PastVisitsAdapter$ViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PastVisitsAdapter.ViewHolder.bind$lambda$0(PastVisitsAdapter.this, item, view);
                }
            });
            Button button = this.binding.btnCheckIn;
            final PastVisitsAdapter pastVisitsAdapter2 = this.this$0;
            button.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.PastVisitsAdapter$ViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PastVisitsAdapter.ViewHolder.bind$lambda$1(PastVisitsAdapter.this, item, view);
                }
            });
            this.binding.executePendingBindings();
            ImageButton imageButton = this.binding.btnCall;
            final PastVisitsAdapter pastVisitsAdapter3 = this.this$0;
            imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.PastVisitsAdapter$ViewHolder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PastVisitsAdapter.ViewHolder.bind$lambda$2(PastVisitsAdapter.this, item, view);
                }
            });
            ImageButton imageButton2 = this.binding.btnNavigate;
            final PastVisitsAdapter pastVisitsAdapter4 = this.this$0;
            imageButton2.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.PastVisitsAdapter$ViewHolder$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PastVisitsAdapter.ViewHolder.bind$lambda$3(PastVisitsList.this, pastVisitsAdapter4, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$0(PastVisitsAdapter this$0, PastVisitsList $item, View it) {
            this$0.listener.onItemClick($item);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$1(PastVisitsAdapter this$0, PastVisitsList $item, View it) {
            this$0.listener.onItemClickTwo($item);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$2(PastVisitsAdapter this$0, PastVisitsList $item, View it) {
            GenericAdapter.OnItemClickListener onItemClickListener = this$0.listener;
            Integer id = $item.getId();
            Intrinsics.checkNotNull(id);
            onItemClickListener.onCall(id.intValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$3(PastVisitsList $item, PastVisitsAdapter this$0, View it) {
            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + Uri.encode($item.getCustomerAddress()));
            Intent mapIntent = new Intent("android.intent.action.VIEW", gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            this$0.getContext().startActivity(mapIntent);
        }
    }
}
