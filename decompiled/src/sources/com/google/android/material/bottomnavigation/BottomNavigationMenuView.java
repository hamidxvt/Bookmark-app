package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.material.R;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public class BottomNavigationMenuView extends NavigationBarMenuView {
    private final int activeItemMaxWidth;
    private final int activeItemMinWidth;
    private final int inactiveItemMaxWidth;
    private final int inactiveItemMinWidth;
    private boolean itemHorizontalTranslationEnabled;
    private final List<Integer> tempChildWidths;

    public BottomNavigationMenuView(Context context) {
        super(context);
        this.tempChildWidths = new ArrayList();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2, -2);
        params.gravity = 17;
        setLayoutParams(params);
        Resources res = getResources();
        this.inactiveItemMaxWidth = res.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_max_width);
        this.inactiveItemMinWidth = res.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_min_width);
        this.activeItemMaxWidth = res.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_max_width);
        this.activeItemMinWidth = res.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_min_width);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0106  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxHeight;
        int i;
        int totalWidth;
        int maxHeight2;
        int i2;
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int visibleCount = getCurrentVisibleContentItemCount();
        int totalCount = getChildCount();
        this.tempChildWidths.clear();
        int totalWidth2 = 0;
        int maxHeight3 = 0;
        int parentHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(parentHeight, Integer.MIN_VALUE);
        if (getItemIconGravity() == 0) {
            if (!isShifting(getLabelVisibilityMode(), visibleCount)) {
                totalWidth = 0;
                maxHeight2 = 0;
            } else if (isItemHorizontalTranslationEnabled()) {
                View activeChild = getChildAt(getSelectedItemPosition());
                int activeItemWidth = this.activeItemMinWidth;
                if (activeChild.getVisibility() != 8) {
                    activeChild.measure(View.MeasureSpec.makeMeasureSpec(this.activeItemMaxWidth, Integer.MIN_VALUE), heightSpec);
                    activeItemWidth = Math.max(activeItemWidth, activeChild.getMeasuredWidth());
                }
                int inactiveCount = visibleCount - (activeChild.getVisibility() != 8 ? 1 : 0);
                int activeMaxAvailable = width - (this.inactiveItemMinWidth * inactiveCount);
                int activeWidth = Math.min(activeMaxAvailable, Math.min(activeItemWidth, this.activeItemMaxWidth));
                int inactiveMaxAvailable = (width - activeWidth) / (inactiveCount != 0 ? inactiveCount : 1);
                int inactiveWidth = Math.min(inactiveMaxAvailable, this.inactiveItemMaxWidth);
                int extra = (width - activeWidth) - (inactiveWidth * inactiveCount);
                int i3 = 0;
                while (i3 < totalCount) {
                    int tempChildWidth = 0;
                    int totalWidth3 = totalWidth2;
                    int totalWidth4 = getChildAt(i3).getVisibility();
                    int maxHeight4 = maxHeight3;
                    if (totalWidth4 != 8) {
                        tempChildWidth = i3 == getSelectedItemPosition() ? activeWidth : inactiveWidth;
                        if (extra > 0) {
                            tempChildWidth++;
                            extra--;
                        }
                    }
                    this.tempChildWidths.add(Integer.valueOf(tempChildWidth));
                    i3++;
                    maxHeight3 = maxHeight4;
                    totalWidth2 = totalWidth3;
                }
                totalWidth = totalWidth2;
                maxHeight2 = maxHeight3;
                maxHeight = maxHeight2;
                for (i2 = 0; i2 < totalCount; i2++) {
                    View child = getChildAt(i2);
                    if (child.getVisibility() != 8) {
                        child.measure(View.MeasureSpec.makeMeasureSpec(this.tempChildWidths.get(i2).intValue(), 1073741824), heightSpec);
                        ViewGroup.LayoutParams params = child.getLayoutParams();
                        params.width = child.getMeasuredWidth();
                        totalWidth += child.getMeasuredWidth();
                        maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
                    }
                }
                i = totalWidth;
            } else {
                totalWidth = 0;
                maxHeight2 = 0;
            }
            int maxAvailable = width / (visibleCount != 0 ? visibleCount : 1);
            int childWidth = Math.min(maxAvailable, this.activeItemMaxWidth);
            int extra2 = width - (childWidth * visibleCount);
            for (int i4 = 0; i4 < totalCount; i4++) {
                int tempChildWidth2 = 0;
                if (getChildAt(i4).getVisibility() != 8) {
                    tempChildWidth2 = childWidth;
                    if (extra2 > 0) {
                        tempChildWidth2++;
                        extra2--;
                    }
                }
                this.tempChildWidths.add(Integer.valueOf(tempChildWidth2));
            }
            maxHeight = maxHeight2;
            while (i2 < totalCount) {
            }
            i = totalWidth;
        } else {
            int totalWidth5 = 0;
            int childCount = visibleCount != 0 ? visibleCount : 1;
            int minChildWidth = Math.round((Math.min((childCount + 3) / 10.0f, 0.9f) * width) / childCount);
            int maxChildWidth = Math.round(width / childCount);
            int maxHeight5 = 0;
            for (int i5 = 0; i5 < totalCount; i5++) {
                View child2 = getChildAt(i5);
                if (child2.getVisibility() != 8) {
                    child2.measure(View.MeasureSpec.makeMeasureSpec(maxChildWidth, Integer.MIN_VALUE), heightSpec);
                    if (child2.getMeasuredWidth() < minChildWidth) {
                        child2.measure(View.MeasureSpec.makeMeasureSpec(minChildWidth, 1073741824), heightSpec);
                    }
                    totalWidth5 += child2.getMeasuredWidth();
                    maxHeight5 = Math.max(maxHeight5, child2.getMeasuredHeight());
                }
            }
            maxHeight = maxHeight5;
            i = totalWidth5;
        }
        setMeasuredDimension(i, Math.max(maxHeight, getSuggestedMinimumHeight()));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = getChildCount();
        int width = right - left;
        int height = bottom - top;
        int used = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                if (getLayoutDirection() == 1) {
                    child.layout((width - used) - child.getMeasuredWidth(), 0, width - used, height);
                } else {
                    child.layout(used, 0, child.getMeasuredWidth() + used, height);
                }
                used += child.getMeasuredWidth();
            }
        }
    }

    public void setItemHorizontalTranslationEnabled(boolean itemHorizontalTranslationEnabled) {
        this.itemHorizontalTranslationEnabled = itemHorizontalTranslationEnabled;
    }

    public boolean isItemHorizontalTranslationEnabled() {
        return this.itemHorizontalTranslationEnabled;
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuView
    protected NavigationBarItemView createNavigationBarItemView(Context context) {
        return new BottomNavigationItemView(context);
    }
}
