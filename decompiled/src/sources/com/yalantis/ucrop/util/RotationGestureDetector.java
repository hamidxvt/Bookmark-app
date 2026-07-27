package com.yalantis.ucrop.util;

import android.view.MotionEvent;

/* loaded from: classes17.dex */
public class RotationGestureDetector {
    private static final int INVALID_POINTER_INDEX = -1;
    private float fX;
    private float fY;
    private float mAngle;
    private boolean mIsFirstTouch;
    private OnRotationGestureListener mListener;
    private int mPointerIndex1 = -1;
    private int mPointerIndex2 = -1;
    private float sX;
    private float sY;

    public interface OnRotationGestureListener {
        boolean onRotation(RotationGestureDetector rotationGestureDetector);
    }

    public RotationGestureDetector(OnRotationGestureListener listener) {
        this.mListener = listener;
    }

    public float getAngle() {
        return this.mAngle;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00a7, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case 0:
                this.sX = event.getX();
                this.sY = event.getY();
                this.mPointerIndex1 = event.findPointerIndex(event.getPointerId(0));
                this.mAngle = 0.0f;
                this.mIsFirstTouch = true;
                break;
            case 1:
                this.mPointerIndex1 = -1;
                break;
            case 2:
                if (this.mPointerIndex1 != -1 && this.mPointerIndex2 != -1 && event.getPointerCount() > this.mPointerIndex2) {
                    float nsX = event.getX(this.mPointerIndex1);
                    float nsY = event.getY(this.mPointerIndex1);
                    float nfX = event.getX(this.mPointerIndex2);
                    float nfY = event.getY(this.mPointerIndex2);
                    if (!this.mIsFirstTouch) {
                        calculateAngleBetweenLines(this.fX, this.fY, this.sX, this.sY, nfX, nfY, nsX, nsY);
                    } else {
                        this.mAngle = 0.0f;
                        this.mIsFirstTouch = false;
                    }
                    if (this.mListener != null) {
                        this.mListener.onRotation(this);
                    }
                    this.fX = nfX;
                    this.fY = nfY;
                    this.sX = nsX;
                    this.sY = nsY;
                    break;
                }
                break;
            case 5:
                this.fX = event.getX();
                this.fY = event.getY();
                this.mPointerIndex2 = event.findPointerIndex(event.getPointerId(event.getActionIndex()));
                this.mAngle = 0.0f;
                this.mIsFirstTouch = true;
                break;
            case 6:
                this.mPointerIndex2 = -1;
                break;
        }
    }

    private float calculateAngleBetweenLines(float fx1, float fy1, float fx2, float fy2, float sx1, float sy1, float sx2, float sy2) {
        return calculateAngleDelta((float) Math.toDegrees((float) Math.atan2(fy1 - fy2, fx1 - fx2)), (float) Math.toDegrees((float) Math.atan2(sy1 - sy2, sx1 - sx2)));
    }

    private float calculateAngleDelta(float angleFrom, float angleTo) {
        this.mAngle = (angleTo % 360.0f) - (angleFrom % 360.0f);
        if (this.mAngle < -180.0f) {
            this.mAngle += 360.0f;
        } else if (this.mAngle > 180.0f) {
            this.mAngle -= 360.0f;
        }
        return this.mAngle;
    }

    public static class SimpleOnRotationGestureListener implements OnRotationGestureListener {
        @Override // com.yalantis.ucrop.util.RotationGestureDetector.OnRotationGestureListener
        public boolean onRotation(RotationGestureDetector rotationDetector) {
            return false;
        }
    }
}
