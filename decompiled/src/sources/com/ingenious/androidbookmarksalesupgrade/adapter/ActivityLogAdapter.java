package com.ingenious.androidbookmarksalesupgrade.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.reflect.TypeToken;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.databinding.ItemActivityLogBinding;
import com.ingenious.androidbookmarksalesupgrade.model.ActivityLog;
import com.ingenious.androidbookmarksalesupgrade.model.DeliveredBooks;
import com.ingenious.androidbookmarksalesupgrade.model.DeliveredBooksData;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* compiled from: ActivityLogAdapter.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010#\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\b\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001)Bk\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012%\b\u0002\u0010\b\u001a\u001f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\t\u0012%\b\u0002\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u000e\u0018\u00010\t¢\u0006\u0004\b\u0011\u0010\u0012J\u001c\u0010\u001a\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0017H\u0016J\u001c\u0010\u001e\u001a\u00020\u000e2\n\u0010\u001f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010 \u001a\u00020\u0017H\u0016J\u001e\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\"2\b\u0010#\u001a\u0004\u0018\u00010\nH\u0002J\b\u0010$\u001a\u00020\u0017H\u0016J\u0014\u0010%\u001a\u00020\u000e2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0010\u0010'\u001a\u00020\n2\u0006\u0010(\u001a\u00020\nH\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\b\u001a\u001f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u000e\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/ActivityLogAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/ActivityLogAdapter$ActivityViewHolder;", "context", "Landroid/content/Context;", FirebaseAnalytics.Param.ITEMS, "", "Lcom/ingenious/androidbookmarksalesupgrade/model/ActivityLog;", "onCallClick", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "phone", "", "onViewDetailsClick", "item", "<init>", "(Landroid/content/Context;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getContext", "()Landroid/content/Context;", "expandedPositionSet", "", "", "imageAdapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/ProductsAdapter;", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onBindViewHolder", "holder", "position", "parseDetails", "", "input", "getItemCount", "updateList", "newItems", "formatVisitDate", "dateString", "ActivityViewHolder", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes7.dex */
public final class ActivityLogAdapter extends RecyclerView.Adapter<ActivityViewHolder> {
    private final Context context;
    private final Set<Integer> expandedPositionSet;
    private ProductsAdapter imageAdapter;
    private List<ActivityLog> items;
    private final Function1<String, Unit> onCallClick;
    private final Function1<ActivityLog, Unit> onViewDetailsClick;

    public /* synthetic */ ActivityLogAdapter(Context context, List list, Function1 function1, Function1 function12, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, list, (i & 4) != 0 ? null : function1, (i & 8) != 0 ? null : function12);
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ActivityLogAdapter(Context context, List<ActivityLog> items, Function1<? super String, Unit> function1, Function1<? super ActivityLog, Unit> function12) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(items, "items");
        this.context = context;
        this.items = items;
        this.onCallClick = function1;
        this.onViewDetailsClick = function12;
        this.expandedPositionSet = new LinkedHashSet();
    }

    /* compiled from: ActivityLogAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/adapter/ActivityLogAdapter$ActivityViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemActivityLogBinding;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/adapter/ActivityLogAdapter;Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemActivityLogBinding;)V", "getBinding", "()Lcom/ingenious/androidbookmarksalesupgrade/databinding/ItemActivityLogBinding;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class ActivityViewHolder extends RecyclerView.ViewHolder {
        private final ItemActivityLogBinding binding;
        final /* synthetic */ ActivityLogAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActivityViewHolder(ActivityLogAdapter this$0, ItemActivityLogBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = this$0;
            this.binding = binding;
        }

        public final ItemActivityLogBinding getBinding() {
            return this.binding;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemActivityLogBinding binding = ItemActivityLogBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "inflate(...)");
        return new ActivityViewHolder(this, binding);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x00aa, code lost:
    
        if (r5.equals("visited") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x012f, code lost:
    
        r4.tvBadge.setBackgroundResource(com.ingenious.androidbookmarksalesupgrade.R.drawable.bg_badge_green);
        r4.tvBadge.setTextColor(android.graphics.Color.parseColor("#04B45C"));
        r4.tvBadge.setText("Visit Completed");
        r5 = androidx.core.content.ContextCompat.getDrawable(r24.context, com.ingenious.androidbookmarksalesupgrade.R.drawable.ic_tick);
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0153, code lost:
    
        if (r5 == null) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0155, code lost:
    
        r5.setTint(androidx.core.content.ContextCompat.getColor(r24.context, com.ingenious.androidbookmarksalesupgrade.R.color.green_text));
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x0160, code lost:
    
        r4.tvBadge.setCompoundDrawablesWithIntrinsicBounds(r5, (android.graphics.drawable.Drawable) null, (android.graphics.drawable.Drawable) null, (android.graphics.drawable.Drawable) null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x012c, code lost:
    
        if (r5.equals("verified") == false) goto L49;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:104:0x05e2  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x055b  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0550  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0545  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0313  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x04a7  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0543  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x054e  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0558  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0566  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0586  */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onBindViewHolder(ActivityViewHolder holder, final int position) {
        String str;
        String createdAt;
        String orderValue;
        Map fullMap;
        boolean z;
        String inventoryProducts;
        Iterator it;
        List products;
        DeliveredBooksData data;
        DeliveredBooksData data2;
        boolean z2;
        boolean z3;
        boolean z4;
        Intrinsics.checkNotNullParameter(holder, "holder");
        final ActivityLog item = this.items.get(position);
        ItemActivityLogBinding b = holder.getBinding();
        b.tvBadge.setText(item.getAction());
        Log.i("TAG", "onBindViewHolder: " + item.getAction());
        String action = item.getAction();
        if (action != null) {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            str = action.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(str, "toLowerCase(...)");
        } else {
            str = null;
        }
        if (str != null) {
            switch (str.hashCode()) {
                case -2020599460:
                    if (str.equals("inventory")) {
                        b.tvBadge.setBackgroundResource(R.drawable.bg_badge_blue);
                        b.tvBadge.setTextColor(Color.parseColor("#E3B306"));
                        b.booksDelivered.setVisibility(8);
                        Drawable drawable = ContextCompat.getDrawable(this.context, R.drawable.recent_new_inventory);
                        if (drawable != null) {
                            drawable.setTint(ContextCompat.getColor(this.context, R.color.inventory_color));
                        }
                        b.tvBadge.setCompoundDrawablesWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
                        break;
                    }
                    break;
                case -1994383672:
                    break;
                case -1990013253:
                    if (str.equals("Missed")) {
                        b.tvBadge.setBackgroundResource(R.drawable.missed_bg);
                        b.tvBadge.setTextColor(Color.parseColor("#E30613"));
                        b.booksDelivered.setVisibility(8);
                        Drawable drawable2 = ContextCompat.getDrawable(this.context, R.drawable.miss_icon);
                        if (drawable2 != null) {
                            drawable2.setTint(ContextCompat.getColor(this.context, R.color.missed_color));
                        }
                        b.tvBadge.setCompoundDrawablesWithIntrinsicBounds(drawable2, (Drawable) null, (Drawable) null, (Drawable) null);
                        break;
                    }
                    break;
                case -79789429:
                    if (str.equals("New Visit")) {
                        b.tvBadge.setBackgroundResource(R.drawable.bg_badge_green);
                        b.tvBadge.setTextColor(ContextCompat.getColor(this.context, R.color.green_text));
                        Drawable drawable3 = ContextCompat.getDrawable(this.context, R.drawable.ic_tick);
                        if (drawable3 != null) {
                            drawable3.setTint(ContextCompat.getColor(this.context, R.color.green_text));
                        }
                        b.tvBadge.setCompoundDrawablesWithIntrinsicBounds(drawable3, (Drawable) null, (Drawable) null, (Drawable) null);
                        break;
                    }
                    break;
                case 466760490:
                    break;
                case 1197640286:
                    if (str.equals("new customer")) {
                        b.tvBadge.setBackgroundResource(R.drawable.bg_badge_red);
                        b.tvBadge.setTextColor(Color.parseColor("#E30613"));
                        b.booksDelivered.setVisibility(8);
                        Drawable drawable4 = ContextCompat.getDrawable(this.context, R.drawable.recent_new_customer);
                        if (drawable4 != null) {
                            drawable4.setTint(ContextCompat.getColor(this.context, R.color.app_color));
                        }
                        b.tvBadge.setCompoundDrawablesWithIntrinsicBounds(drawable4, (Drawable) null, (Drawable) null, (Drawable) null);
                        break;
                    }
                    break;
            }
            TextView textView = b.tvCreatedAt;
            createdAt = item.getCreatedAt();
            if (createdAt == null) {
                createdAt = "";
            }
            textView.setText(formatVisitDate(createdAt));
            b.tvSubject.setText(item.getSchoolName());
            b.tvSchoolInfo.setText(String.valueOf(item.getLocation()));
            Log.i("TAG", "onBindViewHolder: " + item);
            TextView textView2 = b.tvOrderValue;
            orderValue = item.getOrderValue();
            if (orderValue == null) {
                orderValue = "-";
            }
            textView2.setText(orderValue);
            fullMap = parseDetails(item.getDetails());
            if (fullMap.containsKey("Remarks")) {
                String str2 = fullMap.get("Remarks");
                if (str2 != null) {
                    if (str2.length() > 0) {
                        z4 = true;
                        if (z4) {
                            b.tvNotes.setText(fullMap.get("Remarks"));
                            b.visitNoteContainer.setVisibility(0);
                            if (fullMap.containsKey("VisitDuration")) {
                                String str3 = fullMap.get("VisitDuration");
                                if (str3 != null) {
                                    if (str3.length() > 0) {
                                        z3 = true;
                                        if (z3) {
                                            b.tvVisitDuration.setText(((Object) fullMap.get("VisitDuration")) + " mints");
                                            b.duration.setVisibility(0);
                                            if (fullMap.containsKey("visit_date")) {
                                                String str4 = fullMap.get("visit_date");
                                                if (str4 != null) {
                                                    if (str4.length() > 0) {
                                                        z2 = true;
                                                        if (z2) {
                                                            TextView textView3 = b.tvVisitDate;
                                                            String str5 = fullMap.get("visit_date");
                                                            textView3.setText(formatVisitDate(str5 != null ? str5 : ""));
                                                            Log.i("TAG", "onBindViewHolder: " + item.getCreatedAt());
                                                            inventoryProducts = !fullMap.containsKey("Products") ? fullMap.get("Products") : null;
                                                            Map partialMap = MapsKt.toMutableMap(fullMap);
                                                            partialMap.remove("Remarks");
                                                            partialMap.remove("VisitDuration");
                                                            partialMap.remove("visit_date");
                                                            partialMap.remove("Visitdate");
                                                            partialMap.remove("schoolName");
                                                            partialMap.remove(FirebaseAnalytics.Param.LOCATION);
                                                            partialMap.remove("Products");
                                                            int count = 0;
                                                            LinearLayout leftContainer = b.leftContainer;
                                                            Intrinsics.checkNotNullExpressionValue(leftContainer, "leftContainer");
                                                            LinearLayout rightContainer = b.rightContainer;
                                                            Intrinsics.checkNotNullExpressionValue(rightContainer, "rightContainer");
                                                            leftContainer.removeAllViews();
                                                            rightContainer.removeAllViews();
                                                            it = partialMap.entrySet().iterator();
                                                            while (it.hasNext()) {
                                                                Map.Entry entry = (Map.Entry) it.next();
                                                                String key = (String) entry.getKey();
                                                                String value = (String) entry.getValue();
                                                                Map fullMap2 = fullMap;
                                                                Iterator it2 = it;
                                                                Map titleMap = MapsKt.mapOf(TuplesKt.to("customerType", "Customer Type"), TuplesKt.to("principalName", "Principal Name"), TuplesKt.to("phone", "Phone"));
                                                                TextView $this$onBindViewHolder_u24lambda_u240 = new TextView(this.context);
                                                                String str6 = (String) titleMap.get(key);
                                                                $this$onBindViewHolder_u24lambda_u240.setText(str6 != null ? str6 : key);
                                                                $this$onBindViewHolder_u24lambda_u240.setTextSize(14.0f);
                                                                $this$onBindViewHolder_u24lambda_u240.setTextColor(Color.parseColor("#777777"));
                                                                $this$onBindViewHolder_u24lambda_u240.setSingleLine(true);
                                                                Map partialMap2 = partialMap;
                                                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
                                                                params.setMargins(0, 16, 0, 4);
                                                                $this$onBindViewHolder_u24lambda_u240.setLayoutParams(params);
                                                                Log.i("TAG", "onBindViewHolder: " + ((Object) $this$onBindViewHolder_u24lambda_u240.getText()));
                                                                TextView $this$onBindViewHolder_u24lambda_u241 = new TextView(this.context);
                                                                $this$onBindViewHolder_u24lambda_u241.setText(value);
                                                                $this$onBindViewHolder_u24lambda_u241.setTextSize(16.0f);
                                                                $this$onBindViewHolder_u24lambda_u241.setTypeface(null, 0);
                                                                $this$onBindViewHolder_u24lambda_u241.setTextColor(ContextCompat.getColor($this$onBindViewHolder_u24lambda_u241.getContext(), R.color.black));
                                                                if (count % 2 == 0) {
                                                                    leftContainer.addView($this$onBindViewHolder_u24lambda_u240);
                                                                    leftContainer.addView($this$onBindViewHolder_u24lambda_u241);
                                                                } else {
                                                                    rightContainer.addView($this$onBindViewHolder_u24lambda_u240);
                                                                    rightContainer.addView($this$onBindViewHolder_u24lambda_u241);
                                                                }
                                                                count++;
                                                                fullMap = fullMap2;
                                                                it = it2;
                                                                partialMap = partialMap2;
                                                            }
                                                            if (inventoryProducts != null) {
                                                                String it3 = inventoryProducts;
                                                                int i = 0;
                                                                b.productsContainer.setVisibility(0);
                                                                Gson gson = new Gson();
                                                                Type type = new TypeToken<List<? extends String>>() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$onBindViewHolder$1$type$1
                                                                }.getType();
                                                                Object fromJson = gson.fromJson(it3, type);
                                                                Intrinsics.checkNotNullExpressionValue(fromJson, "fromJson(...)");
                                                                List<String> inventoryProductsArray = (List) fromJson;
                                                                b.productsList.removeAllViews();
                                                                for (String i2 : inventoryProductsArray) {
                                                                    TextView $this$onBindViewHolder_u24lambda_u243_u24lambda_u242 = new TextView(this.context);
                                                                    String it4 = it3;
                                                                    $this$onBindViewHolder_u24lambda_u243_u24lambda_u242.setText(i2);
                                                                    $this$onBindViewHolder_u24lambda_u243_u24lambda_u242.setTextSize(15.0f);
                                                                    $this$onBindViewHolder_u24lambda_u243_u24lambda_u242.setTypeface(null, 0);
                                                                    $this$onBindViewHolder_u24lambda_u243_u24lambda_u242.setTextColor(ContextCompat.getColor($this$onBindViewHolder_u24lambda_u243_u24lambda_u242.getContext(), R.color.black));
                                                                    LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(-2, -2);
                                                                    params2.setMargins(0, 4, 0, 4);
                                                                    $this$onBindViewHolder_u24lambda_u243_u24lambda_u242.setLayoutParams(params2);
                                                                    b.productsList.addView($this$onBindViewHolder_u24lambda_u243_u24lambda_u242);
                                                                    i = i;
                                                                    it3 = it4;
                                                                    gson = gson;
                                                                }
                                                            }
                                                            final boolean expanded = this.expandedPositionSet.contains(Integer.valueOf(position));
                                                            b.expandableSection.setVisibility(!expanded ? 0 : 8);
                                                            b.divider.setVisibility(!expanded ? 0 : 4);
                                                            b.ivArrow.setImageResource(!expanded ? R.drawable.activity_up_arrow : R.drawable.activity_down_arrow);
                                                            DeliveredBooks deliveredBooks = item.getDeliveredBooks();
                                                            products = (deliveredBooks != null || (data2 = deliveredBooks.getData()) == null) ? null : data2.getProducts();
                                                            GridLayoutManager alyuotmanager = new GridLayoutManager(this.context, 4, 1, false);
                                                            LinearLayoutManager alyuotmanager2 = new LinearLayoutManager(this.context, 1, false);
                                                            if (products != null || products.isEmpty()) {
                                                                b.booksDelivered.setVisibility(8);
                                                                b.rvProducts.setVisibility(8);
                                                                b.rvProductName.setVisibility(8);
                                                            } else {
                                                                b.booksDelivered.setVisibility(0);
                                                                b.rvProducts.setVisibility(0);
                                                                b.rvProducts.setLayoutManager(alyuotmanager);
                                                                b.rvProducts.setAdapter(new ProductsAdapter(products));
                                                                b.rvProductName.setVisibility(0);
                                                                b.rvProductName.setLayoutManager(alyuotmanager2);
                                                                b.rvProductName.setAdapter(new ProductNameAdapter(products));
                                                            }
                                                            TextView textView4 = b.tvOrderValue;
                                                            DeliveredBooks deliveredBooks2 = item.getDeliveredBooks();
                                                            textView4.setText("PKR " + ((deliveredBooks2 != null || (data = deliveredBooks2.getData()) == null) ? null : data.getGrand_total()));
                                                            b.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$$ExternalSyntheticLambda0
                                                                @Override // android.view.View.OnClickListener
                                                                public final void onClick(View view) {
                                                                    ActivityLogAdapter.onBindViewHolder$lambda$4(expanded, this, position, view);
                                                                }
                                                            });
                                                            b.btnViewDetails.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$$ExternalSyntheticLambda1
                                                                @Override // android.view.View.OnClickListener
                                                                public final void onClick(View view) {
                                                                    ActivityLogAdapter.onBindViewHolder$lambda$5(ActivityLogAdapter.this, item, view);
                                                                }
                                                            });
                                                            b.ivCall.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$$ExternalSyntheticLambda2
                                                                @Override // android.view.View.OnClickListener
                                                                public final void onClick(View view) {
                                                                    ActivityLogAdapter.onBindViewHolder$lambda$6(ActivityLogAdapter.this, item, view);
                                                                }
                                                            });
                                                        }
                                                    }
                                                }
                                                z2 = false;
                                                if (z2) {
                                                }
                                            }
                                            if (fullMap.containsKey("Visitdate")) {
                                                String str7 = fullMap.get("Visitdate");
                                                if (str7 != null) {
                                                    if (str7.length() > 0) {
                                                        z = true;
                                                        if (z) {
                                                            TextView textView5 = b.tvVisitDate;
                                                            String str8 = fullMap.get("Visitdate");
                                                            textView5.setText(formatVisitDate(str8 != null ? str8 : ""));
                                                            Log.i("TAG", "onBindViewHolder: " + item.getCreatedAt());
                                                            if (!fullMap.containsKey("Products")) {
                                                            }
                                                            Map partialMap3 = MapsKt.toMutableMap(fullMap);
                                                            partialMap3.remove("Remarks");
                                                            partialMap3.remove("VisitDuration");
                                                            partialMap3.remove("visit_date");
                                                            partialMap3.remove("Visitdate");
                                                            partialMap3.remove("schoolName");
                                                            partialMap3.remove(FirebaseAnalytics.Param.LOCATION);
                                                            partialMap3.remove("Products");
                                                            int count2 = 0;
                                                            LinearLayout leftContainer2 = b.leftContainer;
                                                            Intrinsics.checkNotNullExpressionValue(leftContainer2, "leftContainer");
                                                            LinearLayout rightContainer2 = b.rightContainer;
                                                            Intrinsics.checkNotNullExpressionValue(rightContainer2, "rightContainer");
                                                            leftContainer2.removeAllViews();
                                                            rightContainer2.removeAllViews();
                                                            it = partialMap3.entrySet().iterator();
                                                            while (it.hasNext()) {
                                                            }
                                                            if (inventoryProducts != null) {
                                                            }
                                                            final boolean expanded2 = this.expandedPositionSet.contains(Integer.valueOf(position));
                                                            b.expandableSection.setVisibility(!expanded2 ? 0 : 8);
                                                            b.divider.setVisibility(!expanded2 ? 0 : 4);
                                                            b.ivArrow.setImageResource(!expanded2 ? R.drawable.activity_up_arrow : R.drawable.activity_down_arrow);
                                                            DeliveredBooks deliveredBooks3 = item.getDeliveredBooks();
                                                            products = (deliveredBooks3 != null || (data2 = deliveredBooks3.getData()) == null) ? null : data2.getProducts();
                                                            GridLayoutManager alyuotmanager3 = new GridLayoutManager(this.context, 4, 1, false);
                                                            LinearLayoutManager alyuotmanager22 = new LinearLayoutManager(this.context, 1, false);
                                                            if (products != null) {
                                                            }
                                                            b.booksDelivered.setVisibility(8);
                                                            b.rvProducts.setVisibility(8);
                                                            b.rvProductName.setVisibility(8);
                                                            TextView textView42 = b.tvOrderValue;
                                                            DeliveredBooks deliveredBooks22 = item.getDeliveredBooks();
                                                            textView42.setText("PKR " + ((deliveredBooks22 != null || (data = deliveredBooks22.getData()) == null) ? null : data.getGrand_total()));
                                                            b.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$$ExternalSyntheticLambda0
                                                                @Override // android.view.View.OnClickListener
                                                                public final void onClick(View view) {
                                                                    ActivityLogAdapter.onBindViewHolder$lambda$4(expanded2, this, position, view);
                                                                }
                                                            });
                                                            b.btnViewDetails.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$$ExternalSyntheticLambda1
                                                                @Override // android.view.View.OnClickListener
                                                                public final void onClick(View view) {
                                                                    ActivityLogAdapter.onBindViewHolder$lambda$5(ActivityLogAdapter.this, item, view);
                                                                }
                                                            });
                                                            b.ivCall.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$$ExternalSyntheticLambda2
                                                                @Override // android.view.View.OnClickListener
                                                                public final void onClick(View view) {
                                                                    ActivityLogAdapter.onBindViewHolder$lambda$6(ActivityLogAdapter.this, item, view);
                                                                }
                                                            });
                                                        }
                                                    }
                                                }
                                                z = false;
                                                if (z) {
                                                }
                                            }
                                            TextView textView6 = b.tvVisitDate;
                                            String createdAt2 = item.getCreatedAt();
                                            textView6.setText(formatVisitDate(createdAt2 != null ? createdAt2 : ""));
                                            Log.i("TAG", "onBindViewHolder: " + item.getCreatedAt());
                                            if (!fullMap.containsKey("Products")) {
                                            }
                                            Map partialMap32 = MapsKt.toMutableMap(fullMap);
                                            partialMap32.remove("Remarks");
                                            partialMap32.remove("VisitDuration");
                                            partialMap32.remove("visit_date");
                                            partialMap32.remove("Visitdate");
                                            partialMap32.remove("schoolName");
                                            partialMap32.remove(FirebaseAnalytics.Param.LOCATION);
                                            partialMap32.remove("Products");
                                            int count22 = 0;
                                            LinearLayout leftContainer22 = b.leftContainer;
                                            Intrinsics.checkNotNullExpressionValue(leftContainer22, "leftContainer");
                                            LinearLayout rightContainer22 = b.rightContainer;
                                            Intrinsics.checkNotNullExpressionValue(rightContainer22, "rightContainer");
                                            leftContainer22.removeAllViews();
                                            rightContainer22.removeAllViews();
                                            it = partialMap32.entrySet().iterator();
                                            while (it.hasNext()) {
                                            }
                                            if (inventoryProducts != null) {
                                            }
                                            final boolean expanded22 = this.expandedPositionSet.contains(Integer.valueOf(position));
                                            b.expandableSection.setVisibility(!expanded22 ? 0 : 8);
                                            b.divider.setVisibility(!expanded22 ? 0 : 4);
                                            b.ivArrow.setImageResource(!expanded22 ? R.drawable.activity_up_arrow : R.drawable.activity_down_arrow);
                                            DeliveredBooks deliveredBooks32 = item.getDeliveredBooks();
                                            products = (deliveredBooks32 != null || (data2 = deliveredBooks32.getData()) == null) ? null : data2.getProducts();
                                            GridLayoutManager alyuotmanager32 = new GridLayoutManager(this.context, 4, 1, false);
                                            LinearLayoutManager alyuotmanager222 = new LinearLayoutManager(this.context, 1, false);
                                            if (products != null) {
                                            }
                                            b.booksDelivered.setVisibility(8);
                                            b.rvProducts.setVisibility(8);
                                            b.rvProductName.setVisibility(8);
                                            TextView textView422 = b.tvOrderValue;
                                            DeliveredBooks deliveredBooks222 = item.getDeliveredBooks();
                                            textView422.setText("PKR " + ((deliveredBooks222 != null || (data = deliveredBooks222.getData()) == null) ? null : data.getGrand_total()));
                                            b.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$$ExternalSyntheticLambda0
                                                @Override // android.view.View.OnClickListener
                                                public final void onClick(View view) {
                                                    ActivityLogAdapter.onBindViewHolder$lambda$4(expanded22, this, position, view);
                                                }
                                            });
                                            b.btnViewDetails.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$$ExternalSyntheticLambda1
                                                @Override // android.view.View.OnClickListener
                                                public final void onClick(View view) {
                                                    ActivityLogAdapter.onBindViewHolder$lambda$5(ActivityLogAdapter.this, item, view);
                                                }
                                            });
                                            b.ivCall.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$$ExternalSyntheticLambda2
                                                @Override // android.view.View.OnClickListener
                                                public final void onClick(View view) {
                                                    ActivityLogAdapter.onBindViewHolder$lambda$6(ActivityLogAdapter.this, item, view);
                                                }
                                            });
                                        }
                                    }
                                }
                                z3 = false;
                                if (z3) {
                                }
                            }
                            b.duration.setVisibility(8);
                            if (fullMap.containsKey("visit_date")) {
                            }
                            if (fullMap.containsKey("Visitdate")) {
                            }
                            TextView textView62 = b.tvVisitDate;
                            String createdAt22 = item.getCreatedAt();
                            textView62.setText(formatVisitDate(createdAt22 != null ? createdAt22 : ""));
                            Log.i("TAG", "onBindViewHolder: " + item.getCreatedAt());
                            if (!fullMap.containsKey("Products")) {
                            }
                            Map partialMap322 = MapsKt.toMutableMap(fullMap);
                            partialMap322.remove("Remarks");
                            partialMap322.remove("VisitDuration");
                            partialMap322.remove("visit_date");
                            partialMap322.remove("Visitdate");
                            partialMap322.remove("schoolName");
                            partialMap322.remove(FirebaseAnalytics.Param.LOCATION);
                            partialMap322.remove("Products");
                            int count222 = 0;
                            LinearLayout leftContainer222 = b.leftContainer;
                            Intrinsics.checkNotNullExpressionValue(leftContainer222, "leftContainer");
                            LinearLayout rightContainer222 = b.rightContainer;
                            Intrinsics.checkNotNullExpressionValue(rightContainer222, "rightContainer");
                            leftContainer222.removeAllViews();
                            rightContainer222.removeAllViews();
                            it = partialMap322.entrySet().iterator();
                            while (it.hasNext()) {
                            }
                            if (inventoryProducts != null) {
                            }
                            final boolean expanded222 = this.expandedPositionSet.contains(Integer.valueOf(position));
                            b.expandableSection.setVisibility(!expanded222 ? 0 : 8);
                            b.divider.setVisibility(!expanded222 ? 0 : 4);
                            b.ivArrow.setImageResource(!expanded222 ? R.drawable.activity_up_arrow : R.drawable.activity_down_arrow);
                            DeliveredBooks deliveredBooks322 = item.getDeliveredBooks();
                            products = (deliveredBooks322 != null || (data2 = deliveredBooks322.getData()) == null) ? null : data2.getProducts();
                            GridLayoutManager alyuotmanager322 = new GridLayoutManager(this.context, 4, 1, false);
                            LinearLayoutManager alyuotmanager2222 = new LinearLayoutManager(this.context, 1, false);
                            if (products != null) {
                            }
                            b.booksDelivered.setVisibility(8);
                            b.rvProducts.setVisibility(8);
                            b.rvProductName.setVisibility(8);
                            TextView textView4222 = b.tvOrderValue;
                            DeliveredBooks deliveredBooks2222 = item.getDeliveredBooks();
                            textView4222.setText("PKR " + ((deliveredBooks2222 != null || (data = deliveredBooks2222.getData()) == null) ? null : data.getGrand_total()));
                            b.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$$ExternalSyntheticLambda0
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view) {
                                    ActivityLogAdapter.onBindViewHolder$lambda$4(expanded222, this, position, view);
                                }
                            });
                            b.btnViewDetails.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$$ExternalSyntheticLambda1
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view) {
                                    ActivityLogAdapter.onBindViewHolder$lambda$5(ActivityLogAdapter.this, item, view);
                                }
                            });
                            b.ivCall.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$$ExternalSyntheticLambda2
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view) {
                                    ActivityLogAdapter.onBindViewHolder$lambda$6(ActivityLogAdapter.this, item, view);
                                }
                            });
                        }
                    }
                }
                z4 = false;
                if (z4) {
                }
            }
            b.visitNoteContainer.setVisibility(8);
            if (fullMap.containsKey("VisitDuration")) {
            }
            b.duration.setVisibility(8);
            if (fullMap.containsKey("visit_date")) {
            }
            if (fullMap.containsKey("Visitdate")) {
            }
            TextView textView622 = b.tvVisitDate;
            String createdAt222 = item.getCreatedAt();
            textView622.setText(formatVisitDate(createdAt222 != null ? createdAt222 : ""));
            Log.i("TAG", "onBindViewHolder: " + item.getCreatedAt());
            if (!fullMap.containsKey("Products")) {
            }
            Map partialMap3222 = MapsKt.toMutableMap(fullMap);
            partialMap3222.remove("Remarks");
            partialMap3222.remove("VisitDuration");
            partialMap3222.remove("visit_date");
            partialMap3222.remove("Visitdate");
            partialMap3222.remove("schoolName");
            partialMap3222.remove(FirebaseAnalytics.Param.LOCATION);
            partialMap3222.remove("Products");
            int count2222 = 0;
            LinearLayout leftContainer2222 = b.leftContainer;
            Intrinsics.checkNotNullExpressionValue(leftContainer2222, "leftContainer");
            LinearLayout rightContainer2222 = b.rightContainer;
            Intrinsics.checkNotNullExpressionValue(rightContainer2222, "rightContainer");
            leftContainer2222.removeAllViews();
            rightContainer2222.removeAllViews();
            it = partialMap3222.entrySet().iterator();
            while (it.hasNext()) {
            }
            if (inventoryProducts != null) {
            }
            final boolean expanded2222 = this.expandedPositionSet.contains(Integer.valueOf(position));
            b.expandableSection.setVisibility(!expanded2222 ? 0 : 8);
            b.divider.setVisibility(!expanded2222 ? 0 : 4);
            b.ivArrow.setImageResource(!expanded2222 ? R.drawable.activity_up_arrow : R.drawable.activity_down_arrow);
            DeliveredBooks deliveredBooks3222 = item.getDeliveredBooks();
            products = (deliveredBooks3222 != null || (data2 = deliveredBooks3222.getData()) == null) ? null : data2.getProducts();
            GridLayoutManager alyuotmanager3222 = new GridLayoutManager(this.context, 4, 1, false);
            LinearLayoutManager alyuotmanager22222 = new LinearLayoutManager(this.context, 1, false);
            if (products != null) {
            }
            b.booksDelivered.setVisibility(8);
            b.rvProducts.setVisibility(8);
            b.rvProductName.setVisibility(8);
            TextView textView42222 = b.tvOrderValue;
            DeliveredBooks deliveredBooks22222 = item.getDeliveredBooks();
            textView42222.setText("PKR " + ((deliveredBooks22222 != null || (data = deliveredBooks22222.getData()) == null) ? null : data.getGrand_total()));
            b.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityLogAdapter.onBindViewHolder$lambda$4(expanded2222, this, position, view);
                }
            });
            b.btnViewDetails.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityLogAdapter.onBindViewHolder$lambda$5(ActivityLogAdapter.this, item, view);
                }
            });
            b.ivCall.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityLogAdapter.onBindViewHolder$lambda$6(ActivityLogAdapter.this, item, view);
                }
            });
        }
        b.tvBadge.setBackgroundResource(R.drawable.bg_badge_green);
        b.tvBadge.setTextColor(ContextCompat.getColor(this.context, R.color.green_text));
        b.booksDelivered.setVisibility(8);
        Drawable drawable5 = ContextCompat.getDrawable(this.context, R.drawable.ic_tick);
        if (drawable5 != null) {
            drawable5.setTint(ContextCompat.getColor(this.context, R.color.green_text));
        }
        b.tvBadge.setCompoundDrawablesWithIntrinsicBounds(drawable5, (Drawable) null, (Drawable) null, (Drawable) null);
        TextView textView7 = b.tvCreatedAt;
        createdAt = item.getCreatedAt();
        if (createdAt == null) {
        }
        textView7.setText(formatVisitDate(createdAt));
        b.tvSubject.setText(item.getSchoolName());
        b.tvSchoolInfo.setText(String.valueOf(item.getLocation()));
        Log.i("TAG", "onBindViewHolder: " + item);
        TextView textView22 = b.tvOrderValue;
        orderValue = item.getOrderValue();
        if (orderValue == null) {
        }
        textView22.setText(orderValue);
        fullMap = parseDetails(item.getDetails());
        if (fullMap.containsKey("Remarks")) {
        }
        b.visitNoteContainer.setVisibility(8);
        if (fullMap.containsKey("VisitDuration")) {
        }
        b.duration.setVisibility(8);
        if (fullMap.containsKey("visit_date")) {
        }
        if (fullMap.containsKey("Visitdate")) {
        }
        TextView textView6222 = b.tvVisitDate;
        String createdAt2222 = item.getCreatedAt();
        textView6222.setText(formatVisitDate(createdAt2222 != null ? createdAt2222 : ""));
        Log.i("TAG", "onBindViewHolder: " + item.getCreatedAt());
        if (!fullMap.containsKey("Products")) {
        }
        Map partialMap32222 = MapsKt.toMutableMap(fullMap);
        partialMap32222.remove("Remarks");
        partialMap32222.remove("VisitDuration");
        partialMap32222.remove("visit_date");
        partialMap32222.remove("Visitdate");
        partialMap32222.remove("schoolName");
        partialMap32222.remove(FirebaseAnalytics.Param.LOCATION);
        partialMap32222.remove("Products");
        int count22222 = 0;
        LinearLayout leftContainer22222 = b.leftContainer;
        Intrinsics.checkNotNullExpressionValue(leftContainer22222, "leftContainer");
        LinearLayout rightContainer22222 = b.rightContainer;
        Intrinsics.checkNotNullExpressionValue(rightContainer22222, "rightContainer");
        leftContainer22222.removeAllViews();
        rightContainer22222.removeAllViews();
        it = partialMap32222.entrySet().iterator();
        while (it.hasNext()) {
        }
        if (inventoryProducts != null) {
        }
        final boolean expanded22222 = this.expandedPositionSet.contains(Integer.valueOf(position));
        b.expandableSection.setVisibility(!expanded22222 ? 0 : 8);
        b.divider.setVisibility(!expanded22222 ? 0 : 4);
        b.ivArrow.setImageResource(!expanded22222 ? R.drawable.activity_up_arrow : R.drawable.activity_down_arrow);
        DeliveredBooks deliveredBooks32222 = item.getDeliveredBooks();
        products = (deliveredBooks32222 != null || (data2 = deliveredBooks32222.getData()) == null) ? null : data2.getProducts();
        GridLayoutManager alyuotmanager32222 = new GridLayoutManager(this.context, 4, 1, false);
        LinearLayoutManager alyuotmanager222222 = new LinearLayoutManager(this.context, 1, false);
        if (products != null) {
        }
        b.booksDelivered.setVisibility(8);
        b.rvProducts.setVisibility(8);
        b.rvProductName.setVisibility(8);
        TextView textView422222 = b.tvOrderValue;
        DeliveredBooks deliveredBooks222222 = item.getDeliveredBooks();
        textView422222.setText("PKR " + ((deliveredBooks222222 != null || (data = deliveredBooks222222.getData()) == null) ? null : data.getGrand_total()));
        b.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityLogAdapter.onBindViewHolder$lambda$4(expanded22222, this, position, view);
            }
        });
        b.btnViewDetails.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityLogAdapter.onBindViewHolder$lambda$5(ActivityLogAdapter.this, item, view);
            }
        });
        b.ivCall.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.adapter.ActivityLogAdapter$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityLogAdapter.onBindViewHolder$lambda$6(ActivityLogAdapter.this, item, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$4(boolean $expanded, ActivityLogAdapter this$0, int $position, View it) {
        if ($expanded) {
            this$0.expandedPositionSet.remove(Integer.valueOf($position));
        } else {
            this$0.expandedPositionSet.add(Integer.valueOf($position));
        }
        this$0.notifyItemChanged($position);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$5(ActivityLogAdapter this$0, ActivityLog $item, View it) {
        Function1<ActivityLog, Unit> function1 = this$0.onViewDetailsClick;
        if (function1 != null) {
            function1.invoke($item);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$6(ActivityLogAdapter this$0, ActivityLog $item, View it) {
        Function1<String, Unit> function1 = this$0.onCallClick;
        if (function1 != null) {
            String phone = $item.getPhone();
            if (phone == null) {
                phone = "";
            }
            function1.invoke(phone);
        }
    }

    private final Map<String, String> parseDetails(String input) {
        JSONObject json = new JSONObject(input);
        Map map = new LinkedHashMap();
        Iterator $this$forEach$iv = json.keys();
        Intrinsics.checkNotNullExpressionValue($this$forEach$iv, "keys(...)");
        while ($this$forEach$iv.hasNext()) {
            Object element$iv = $this$forEach$iv.next();
            String key = (String) element$iv;
            String optString = json.optString(key, "");
            Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
            String value = StringsKt.trim((CharSequence) optString).toString();
            if (value.length() > 0) {
                map.put(key, value);
            }
        }
        return map;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: getItemCount */
    public int getTabCount() {
        return this.items.size();
    }

    public final void updateList(List<ActivityLog> newItems) {
        Intrinsics.checkNotNullParameter(newItems, "newItems");
        this.items = newItems;
        this.expandedPositionSet.clear();
        notifyDataSetChanged();
    }

    private final String formatVisitDate(String dateString) {
        try {
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = input.parse(dateString);
            SimpleDateFormat output = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
            Intrinsics.checkNotNull(date);
            String format = output.format(date);
            Intrinsics.checkNotNull(format);
            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return dateString;
        }
    }
}
