package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.CompoundButtonBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.response.HomeResponse;
import io.github.florent37.shapeofview.shapes.CircleView;

/* loaded from: classes13.dex */
public class FragmentHomeBindingImpl extends FragmentHomeBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private final View.OnClickListener mCallback10;
    private final View.OnClickListener mCallback11;
    private final View.OnClickListener mCallback12;
    private final View.OnClickListener mCallback13;
    private final View.OnClickListener mCallback14;
    private final View.OnClickListener mCallback15;
    private final View.OnClickListener mCallback16;
    private final View.OnClickListener mCallback17;
    private final View.OnClickListener mCallback18;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView6;
    private final TextView mboundView7;
    private final TextView mboundView8;
    private final TextView mboundView9;

    static {
        sViewsWithIds.put(R.id.imgCircleProfile, 17);
        sViewsWithIds.put(R.id.inbox, 18);
        sViewsWithIds.put(R.id.redDotInbox, 19);
        sViewsWithIds.put(R.id.redDot, 20);
        sViewsWithIds.put(R.id.pendingVisit, 21);
        sViewsWithIds.put(R.id.circleProgress, 22);
        sViewsWithIds.put(R.id.visitsValue, 23);
        sViewsWithIds.put(R.id.visitsLabel, 24);
        sViewsWithIds.put(R.id.visits_this_week, 25);
        sViewsWithIds.put(R.id.see_stats, 26);
        sViewsWithIds.put(R.id.job_start_linear, 27);
        sViewsWithIds.put(R.id.see_stats_linear, 28);
        sViewsWithIds.put(R.id.statusText, 29);
        sViewsWithIds.put(R.id.date_filter_linearLayout, 30);
        sViewsWithIds.put(R.id.ic_calendar, 31);
        sViewsWithIds.put(R.id.tvDate, 32);
        sViewsWithIds.put(R.id.today_visits_rv, 33);
        sViewsWithIds.put(R.id.past_visits_text, 34);
        sViewsWithIds.put(R.id.past_visits_rv, 35);
    }

    public FragmentHomeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 36, sIncludes, sViewsWithIds));
    }

    private FragmentHomeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageButton) bindings[13], (ImageButton) bindings[12], (CircularProgressIndicator) bindings[22], (LinearLayout) bindings[30], (TextView) bindings[16], (ImageView) bindings[14], (ImageButton) bindings[31], (CircleView) bindings[17], (ImageView) bindings[18], (MaterialCardView) bindings[27], (SwitchMaterial) bindings[5], (ImageView) bindings[2], (ImageView) bindings[15], (RecyclerView) bindings[35], (TextView) bindings[34], (TextView) bindings[21], (View) bindings[20], (View) bindings[19], (ImageView) bindings[1], (TextView) bindings[26], (MaterialCardView) bindings[28], (AppCompatButton) bindings[4], (TextView) bindings[29], (RecyclerView) bindings[33], (TextView) bindings[3], (TextView) bindings[32], (TextView) bindings[24], (TextView) bindings[25], (TextView) bindings[23]);
        this.mDirtyFlags = -1L;
        this.btnNext.setTag(null);
        this.btnPrev.setTag(null);
        this.fabAddVisitCustomer.setTag(null);
        this.filterIconIv.setTag(null);
        this.materialSwitch.setTag(null);
        this.mboundView0 = (ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView10 = (TextView) bindings[10];
        this.mboundView10.setTag(null);
        this.mboundView11 = (TextView) bindings[11];
        this.mboundView11.setTag(null);
        this.mboundView6 = (TextView) bindings[6];
        this.mboundView6.setTag(null);
        this.mboundView7 = (TextView) bindings[7];
        this.mboundView7.setTag(null);
        this.mboundView8 = (TextView) bindings[8];
        this.mboundView8.setTag(null);
        this.mboundView9 = (TextView) bindings[9];
        this.mboundView9.setTag(null);
        this.notificationIcon.setTag(null);
        this.pastFilterIconIv.setTag(null);
        this.roundedImage.setTag(null);
        this.startJobBtn.setTag(null);
        this.totalVisit.setTag(null);
        setRootTag(root);
        this.mCallback15 = new OnClickListener(this, 6);
        this.mCallback11 = new OnClickListener(this, 2);
        this.mCallback16 = new OnClickListener(this, 7);
        this.mCallback12 = new OnClickListener(this, 3);
        this.mCallback17 = new OnClickListener(this, 8);
        this.mCallback13 = new OnClickListener(this, 4);
        this.mCallback14 = new OnClickListener(this, 5);
        this.mCallback10 = new OnClickListener(this, 1);
        this.mCallback18 = new OnClickListener(this, 9);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (2 == variableId) {
            setItem((HomeResponse) variable);
            return true;
        }
        if (3 == variableId) {
            setListener((GenericListeners) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.FragmentHomeBinding
    public void setItem(HomeResponse Item) {
        this.mItem = Item;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.FragmentHomeBinding
    public void setListener(GenericListeners Listener) {
        this.mListener = Listener;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        String stringValueOfItemTotalsVisitsCount;
        String stringValueOfItemShopCompletedCount;
        String stringValueOfItemSchoolTotalCount;
        Drawable itemVisitsLeftTodayInt0TotalVisitAndroidDrawableHomeDownArrowTotalVisitAndroidDrawableHomeDownArrow;
        String stringValueOfItemShopTotalCount;
        String stringValueOfItemVisitsCompletedToday;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        HomeResponse item = this.mItem;
        Integer itemShopCompletedCount = null;
        GenericListeners genericListeners = this.mListener;
        Integer itemShopTotalCount = null;
        boolean itemJobStartedBooleanTrueBooleanFalse = false;
        Integer itemTotalsVisitsCount = null;
        String stringValueOfItemSchoolCompletedCount = null;
        Integer itemVisitsCompletedToday = null;
        Integer itemSchoolTotalCount = null;
        Boolean itemJobStarted = null;
        Integer itemSchoolCompletedCount = null;
        Integer itemVisitsLeftToday = null;
        if ((dirtyFlags & 5) == 0) {
            stringValueOfItemTotalsVisitsCount = null;
            stringValueOfItemShopCompletedCount = null;
            stringValueOfItemSchoolTotalCount = null;
            itemVisitsLeftTodayInt0TotalVisitAndroidDrawableHomeDownArrowTotalVisitAndroidDrawableHomeDownArrow = null;
            stringValueOfItemShopTotalCount = null;
            stringValueOfItemVisitsCompletedToday = null;
        } else {
            if (item != null) {
                itemShopCompletedCount = item.getShopCompletedCount();
                itemShopTotalCount = item.getShopTotalCount();
                itemTotalsVisitsCount = item.getTotalsVisitsCount();
                itemVisitsCompletedToday = item.getVisitsCompletedToday();
                itemSchoolTotalCount = item.getSchoolTotalCount();
                itemJobStarted = item.getJobStarted();
                itemSchoolCompletedCount = item.getSchoolCompletedCount();
                itemVisitsLeftToday = item.getVisitsLeftToday();
            }
            int androidxDatabindingViewDataBindingSafeUnboxItemShopCompletedCount = ViewDataBinding.safeUnbox(itemShopCompletedCount);
            int androidxDatabindingViewDataBindingSafeUnboxItemShopTotalCount = ViewDataBinding.safeUnbox(itemShopTotalCount);
            int androidxDatabindingViewDataBindingSafeUnboxItemTotalsVisitsCount = ViewDataBinding.safeUnbox(itemTotalsVisitsCount);
            int androidxDatabindingViewDataBindingSafeUnboxItemVisitsCompletedToday = ViewDataBinding.safeUnbox(itemVisitsCompletedToday);
            int androidxDatabindingViewDataBindingSafeUnboxItemSchoolTotalCount = ViewDataBinding.safeUnbox(itemSchoolTotalCount);
            boolean androidxDatabindingViewDataBindingSafeUnboxItemJobStarted = ViewDataBinding.safeUnbox(itemJobStarted);
            int androidxDatabindingViewDataBindingSafeUnboxItemSchoolCompletedCount = ViewDataBinding.safeUnbox(itemSchoolCompletedCount);
            int androidxDatabindingViewDataBindingSafeUnboxItemVisitsLeftToday = ViewDataBinding.safeUnbox(itemVisitsLeftToday);
            if ((dirtyFlags & 5) != 0) {
                if (androidxDatabindingViewDataBindingSafeUnboxItemJobStarted) {
                    dirtyFlags |= 16;
                } else {
                    dirtyFlags |= 8;
                }
            }
            String stringValueOfItemShopCompletedCount2 = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemShopCompletedCount);
            String stringValueOfItemShopTotalCount2 = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemShopTotalCount);
            String stringValueOfItemTotalsVisitsCount2 = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemTotalsVisitsCount);
            String stringValueOfItemVisitsCompletedToday2 = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemVisitsCompletedToday);
            String stringValueOfItemSchoolTotalCount2 = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemSchoolTotalCount);
            itemJobStartedBooleanTrueBooleanFalse = androidxDatabindingViewDataBindingSafeUnboxItemJobStarted;
            stringValueOfItemSchoolCompletedCount = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemSchoolCompletedCount);
            boolean itemVisitsLeftTodayInt0 = androidxDatabindingViewDataBindingSafeUnboxItemVisitsLeftToday > 0;
            if ((dirtyFlags & 5) != 0) {
                if (itemVisitsLeftTodayInt0) {
                    dirtyFlags |= 64;
                } else {
                    dirtyFlags |= 32;
                }
            }
            Drawable itemVisitsLeftTodayInt0TotalVisitAndroidDrawableHomeDownArrowTotalVisitAndroidDrawableHomeDownArrow2 = AppCompatResources.getDrawable(this.totalVisit.getContext(), R.drawable.home_down_arrow);
            stringValueOfItemTotalsVisitsCount = stringValueOfItemTotalsVisitsCount2;
            stringValueOfItemShopCompletedCount = stringValueOfItemShopCompletedCount2;
            stringValueOfItemSchoolTotalCount = stringValueOfItemSchoolTotalCount2;
            itemVisitsLeftTodayInt0TotalVisitAndroidDrawableHomeDownArrowTotalVisitAndroidDrawableHomeDownArrow = itemVisitsLeftTodayInt0TotalVisitAndroidDrawableHomeDownArrowTotalVisitAndroidDrawableHomeDownArrow2;
            stringValueOfItemShopTotalCount = stringValueOfItemShopTotalCount2;
            stringValueOfItemVisitsCompletedToday = stringValueOfItemVisitsCompletedToday2;
        }
        if ((dirtyFlags & 4) != 0) {
            this.btnNext.setOnClickListener(this.mCallback15);
            this.btnPrev.setOnClickListener(this.mCallback14);
            this.fabAddVisitCustomer.setOnClickListener(this.mCallback18);
            this.filterIconIv.setOnClickListener(this.mCallback16);
            this.materialSwitch.setOnClickListener(this.mCallback13);
            this.notificationIcon.setOnClickListener(this.mCallback11);
            this.pastFilterIconIv.setOnClickListener(this.mCallback17);
            this.roundedImage.setOnClickListener(this.mCallback10);
            this.startJobBtn.setOnClickListener(this.mCallback12);
        }
        if ((dirtyFlags & 5) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.materialSwitch, itemJobStartedBooleanTrueBooleanFalse);
            TextViewBindingAdapter.setText(this.mboundView10, stringValueOfItemVisitsCompletedToday);
            TextViewBindingAdapter.setText(this.mboundView11, stringValueOfItemTotalsVisitsCount);
            TextViewBindingAdapter.setText(this.mboundView6, stringValueOfItemSchoolCompletedCount);
            TextViewBindingAdapter.setText(this.mboundView7, stringValueOfItemSchoolTotalCount);
            TextViewBindingAdapter.setText(this.mboundView8, stringValueOfItemShopCompletedCount);
            TextViewBindingAdapter.setText(this.mboundView9, stringValueOfItemShopTotalCount);
            TextViewBindingAdapter.setDrawableStart(this.totalVisit, itemVisitsLeftTodayInt0TotalVisitAndroidDrawableHomeDownArrowTotalVisitAndroidDrawableHomeDownArrow);
        }
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean listenerJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                GenericListeners listener = this.mListener;
                listenerJavaLangObjectNull = listener != null;
                if (listenerJavaLangObjectNull) {
                    listener.onTapProfile();
                    break;
                }
                break;
            case 2:
                GenericListeners listener2 = this.mListener;
                listenerJavaLangObjectNull = listener2 != null;
                if (listenerJavaLangObjectNull) {
                    listener2.onNotificationClick();
                    break;
                }
                break;
            case 3:
                GenericListeners listener3 = this.mListener;
                listenerJavaLangObjectNull = listener3 != null;
                if (listenerJavaLangObjectNull) {
                    listener3.onTapSwitch();
                    break;
                }
                break;
            case 4:
                GenericListeners listener4 = this.mListener;
                listenerJavaLangObjectNull = listener4 != null;
                if (listenerJavaLangObjectNull) {
                    listener4.onTapSwitch();
                    break;
                }
                break;
            case 5:
                GenericListeners listener5 = this.mListener;
                listenerJavaLangObjectNull = listener5 != null;
                if (listenerJavaLangObjectNull) {
                    listener5.onTapDatePrevious();
                    break;
                }
                break;
            case 6:
                GenericListeners listener6 = this.mListener;
                listenerJavaLangObjectNull = listener6 != null;
                if (listenerJavaLangObjectNull) {
                    listener6.onTapDateNext();
                    break;
                }
                break;
            case 7:
                GenericListeners listener7 = this.mListener;
                listenerJavaLangObjectNull = listener7 != null;
                if (listenerJavaLangObjectNull) {
                    listener7.onTapFilter();
                    break;
                }
                break;
            case 8:
                GenericListeners listener8 = this.mListener;
                listenerJavaLangObjectNull = listener8 != null;
                if (listenerJavaLangObjectNull) {
                    listener8.onTapFilter();
                    break;
                }
                break;
            case 9:
                GenericListeners listener9 = this.mListener;
                listenerJavaLangObjectNull = listener9 != null;
                if (listenerJavaLangObjectNull) {
                    listener9.onTapAddHome();
                    break;
                }
                break;
        }
    }
}
