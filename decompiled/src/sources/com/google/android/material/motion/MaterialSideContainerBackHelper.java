package com.google.android.material.motion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.util.Property;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.BackEventCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;

/* loaded from: classes16.dex */
public class MaterialSideContainerBackHelper extends MaterialBackAnimationHelper<View> {
    private final float maxScaleXDistanceGrow;
    private final float maxScaleXDistanceShrink;
    private final float maxScaleYDistance;

    public MaterialSideContainerBackHelper(View view) {
        super(view);
        Resources resources = view.getResources();
        this.maxScaleXDistanceShrink = resources.getDimension(R.dimen.m3_back_progress_side_container_max_scale_x_distance_shrink);
        this.maxScaleXDistanceGrow = resources.getDimension(R.dimen.m3_back_progress_side_container_max_scale_x_distance_grow);
        this.maxScaleYDistance = resources.getDimension(R.dimen.m3_back_progress_side_container_max_scale_y_distance);
    }

    public void startBackProgress(BackEventCompat backEvent) {
        super.onStartBackProgress(backEvent);
    }

    public void updateBackProgress(BackEventCompat backEvent, int gravity) {
        if (super.onUpdateBackProgress(backEvent) == null) {
            return;
        }
        boolean leftSwipeEdge = backEvent.getSwipeEdge() == 0;
        updateBackProgress(backEvent.getProgress(), leftSwipeEdge, gravity);
    }

    public void updateBackProgress(float progress, boolean leftSwipeEdge, int gravity) {
        boolean leftGravity;
        float f;
        float childScaleX;
        float f2;
        float progress2 = interpolateProgress(progress);
        boolean leftGravity2 = checkAbsoluteGravity(gravity, 3);
        boolean swipeEdgeMatchesGravity = leftSwipeEdge == leftGravity2;
        int width = this.view.getWidth();
        int height = this.view.getHeight();
        if (width > 0.0f && height > 0.0f) {
            float maxScaleXDeltaShrink = this.maxScaleXDistanceShrink / width;
            float maxScaleXDeltaGrow = this.maxScaleXDistanceGrow / width;
            float maxScaleYDelta = this.maxScaleYDistance / height;
            this.view.setPivotX(leftGravity2 ? 0.0f : width);
            float endScaleXDelta = swipeEdgeMatchesGravity ? maxScaleXDeltaGrow : -maxScaleXDeltaShrink;
            float scaleXDelta = AnimationUtils.lerp(0.0f, endScaleXDelta, progress2);
            float scaleX = scaleXDelta + 1.0f;
            float scaleYDelta = AnimationUtils.lerp(0.0f, maxScaleYDelta, progress2);
            float scaleY = 1.0f - scaleYDelta;
            if (!Float.isNaN(scaleX) && !Float.isNaN(scaleY)) {
                this.view.setScaleX(scaleX);
                this.view.setScaleY(scaleY);
                if (this.view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) this.view;
                    int i = 0;
                    while (true) {
                        float progress3 = progress2;
                        if (i < viewGroup.getChildCount()) {
                            View childView = viewGroup.getChildAt(i);
                            if (leftGravity2) {
                                leftGravity = leftGravity2;
                                f = (width - childView.getRight()) + childView.getWidth();
                            } else {
                                leftGravity = leftGravity2;
                                f = -childView.getLeft();
                            }
                            childView.setPivotX(f);
                            childView.setPivotY(-childView.getTop());
                            float childScaleX2 = swipeEdgeMatchesGravity ? 1.0f - scaleXDelta : 1.0f;
                            if (scaleY != 0.0f) {
                                childScaleX = childScaleX2;
                                f2 = (scaleX / scaleY) * childScaleX;
                            } else {
                                childScaleX = childScaleX2;
                                f2 = 1.0f;
                            }
                            float childScaleY = f2;
                            if (!Float.isNaN(childScaleX) && !Float.isNaN(childScaleY)) {
                                childView.setScaleX(childScaleX);
                                childView.setScaleY(childScaleY);
                            }
                            i++;
                            progress2 = progress3;
                            leftGravity2 = leftGravity;
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }

    public void finishBackProgress(BackEventCompat backEvent, final int gravity, Animator.AnimatorListener animatorListener, ValueAnimator.AnimatorUpdateListener finishAnimatorUpdateListener) {
        final boolean leftSwipeEdge = backEvent.getSwipeEdge() == 0;
        boolean leftGravity = checkAbsoluteGravity(gravity, 3);
        float scaledWidth = (this.view.getWidth() * this.view.getScaleX()) + getEdgeMargin(leftGravity);
        ObjectAnimator finishAnimator = ObjectAnimator.ofFloat(this.view, (Property<V, Float>) View.TRANSLATION_X, leftGravity ? -scaledWidth : scaledWidth);
        if (finishAnimatorUpdateListener != null) {
            finishAnimator.addUpdateListener(finishAnimatorUpdateListener);
        }
        finishAnimator.setInterpolator(new FastOutSlowInInterpolator());
        finishAnimator.setDuration(AnimationUtils.lerp(this.hideDurationMax, this.hideDurationMin, backEvent.getProgress()));
        finishAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.motion.MaterialSideContainerBackHelper.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                MaterialSideContainerBackHelper.this.view.setTranslationX(0.0f);
                MaterialSideContainerBackHelper.this.updateBackProgress(0.0f, leftSwipeEdge, gravity);
            }
        });
        if (animatorListener != null) {
            finishAnimator.addListener(animatorListener);
        }
        finishAnimator.start();
    }

    public void cancelBackProgress() {
        if (super.onCancelBackProgress() == null) {
            return;
        }
        AnimatorSet cancelAnimatorSet = new AnimatorSet();
        cancelAnimatorSet.playTogether(ObjectAnimator.ofFloat(this.view, (Property<V, Float>) View.SCALE_X, 1.0f), ObjectAnimator.ofFloat(this.view, (Property<V, Float>) View.SCALE_Y, 1.0f));
        if (this.view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) this.view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childView = viewGroup.getChildAt(i);
                cancelAnimatorSet.playTogether(ObjectAnimator.ofFloat(childView, (Property<View, Float>) View.SCALE_Y, 1.0f));
            }
        }
        cancelAnimatorSet.setDuration(this.cancelDuration);
        cancelAnimatorSet.start();
    }

    private boolean checkAbsoluteGravity(int gravity, int checkFor) {
        int absoluteGravity = Gravity.getAbsoluteGravity(gravity, this.view.getLayoutDirection());
        return (absoluteGravity & checkFor) == checkFor;
    }

    private int getEdgeMargin(boolean leftGravity) {
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            return leftGravity ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
        }
        return 0;
    }
}
