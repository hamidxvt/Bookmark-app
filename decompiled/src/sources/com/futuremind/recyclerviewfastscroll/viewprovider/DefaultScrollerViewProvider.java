package com.futuremind.recyclerviewfastscroll.viewprovider;

import android.graphics.drawable.InsetDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.futuremind.recyclerviewfastscroll.Utils;
import com.futuremind.recyclerviewfastscroll.viewprovider.VisibilityAnimationManager;
import com.hbb20.R;

/* loaded from: classes16.dex */
public class DefaultScrollerViewProvider extends ScrollerViewProvider {
    protected View bubble;
    protected View handle;

    @Override // com.futuremind.recyclerviewfastscroll.viewprovider.ScrollerViewProvider
    public View provideHandleView(ViewGroup container) {
        this.handle = new View(getContext());
        int verticalInset = getScroller().isVertical() ? 0 : getContext().getResources().getDimensionPixelSize(R.dimen.fastscroll__handle_inset);
        int horizontalInset = getScroller().isVertical() ? getContext().getResources().getDimensionPixelSize(R.dimen.fastscroll__handle_inset) : 0;
        InsetDrawable handleBg = new InsetDrawable(ContextCompat.getDrawable(getContext(), R.drawable.fastscroll__default_handle), horizontalInset, verticalInset, horizontalInset, verticalInset);
        Utils.setBackground(this.handle, handleBg);
        int handleWidth = getContext().getResources().getDimensionPixelSize(getScroller().isVertical() ? R.dimen.fastscroll__handle_clickable_width : R.dimen.fastscroll__handle_height);
        int handleHeight = getContext().getResources().getDimensionPixelSize(getScroller().isVertical() ? R.dimen.fastscroll__handle_height : R.dimen.fastscroll__handle_clickable_width);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(handleWidth, handleHeight);
        this.handle.setLayoutParams(params);
        return this.handle;
    }

    @Override // com.futuremind.recyclerviewfastscroll.viewprovider.ScrollerViewProvider
    public View provideBubbleView(ViewGroup container) {
        this.bubble = LayoutInflater.from(getContext()).inflate(R.layout.fastscroll__default_bubble, container, false);
        return this.bubble;
    }

    @Override // com.futuremind.recyclerviewfastscroll.viewprovider.ScrollerViewProvider
    public TextView provideBubbleTextView() {
        return (TextView) this.bubble;
    }

    @Override // com.futuremind.recyclerviewfastscroll.viewprovider.ScrollerViewProvider
    public int getBubbleOffset() {
        float width;
        int width2;
        if (getScroller().isVertical()) {
            width = this.handle.getHeight() / 2.0f;
            width2 = this.bubble.getHeight();
        } else {
            width = this.handle.getWidth() / 2.0f;
            width2 = this.bubble.getWidth();
        }
        return (int) (width - width2);
    }

    @Override // com.futuremind.recyclerviewfastscroll.viewprovider.ScrollerViewProvider
    protected ViewBehavior provideHandleBehavior() {
        return null;
    }

    @Override // com.futuremind.recyclerviewfastscroll.viewprovider.ScrollerViewProvider
    protected ViewBehavior provideBubbleBehavior() {
        return new DefaultBubbleBehavior(new VisibilityAnimationManager.Builder(this.bubble).withPivotX(1.0f).withPivotY(1.0f).build());
    }
}
