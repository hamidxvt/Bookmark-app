package com.airbnb.lottie.utils;

import android.view.Choreographer;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;

/* loaded from: classes.dex */
public class LottieValueAnimator extends BaseLottieAnimator implements Choreographer.FrameCallback {
    private LottieComposition composition;
    private float speed = 1.0f;
    private boolean speedReversedForRepeatMode = false;
    private long lastFrameTimeNs = 0;
    private float frameRaw = 0.0f;
    private float frame = 0.0f;
    private int repeatCount = 0;
    private float minFrame = -2.1474836E9f;
    private float maxFrame = 2.1474836E9f;
    protected boolean running = false;
    private boolean useCompositionFrameRate = false;

    @Override // android.animation.ValueAnimator
    public Object getAnimatedValue() {
        return Float.valueOf(getAnimatedValueAbsolute());
    }

    public float getAnimatedValueAbsolute() {
        if (this.composition == null) {
            return 0.0f;
        }
        return (this.frame - this.composition.getStartFrame()) / (this.composition.getEndFrame() - this.composition.getStartFrame());
    }

    @Override // android.animation.ValueAnimator
    public float getAnimatedFraction() {
        if (this.composition == null) {
            return 0.0f;
        }
        if (isReversed()) {
            return (getMaxFrame() - this.frame) / (getMaxFrame() - getMinFrame());
        }
        return (this.frame - getMinFrame()) / (getMaxFrame() - getMinFrame());
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public long getDuration() {
        if (this.composition == null) {
            return 0L;
        }
        return (long) this.composition.getDuration();
    }

    public float getFrame() {
        return this.frame;
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public boolean isRunning() {
        return this.running;
    }

    public void setUseCompositionFrameRate(boolean useCompositionFrameRate) {
        this.useCompositionFrameRate = useCompositionFrameRate;
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long frameTimeNanos) {
        postFrameCallback();
        if (this.composition == null || !isRunning()) {
            return;
        }
        L.beginSection("LottieValueAnimator#doFrame");
        long timeSinceFrame = this.lastFrameTimeNs != 0 ? frameTimeNanos - this.lastFrameTimeNs : 0L;
        float frameDuration = getFrameDurationNs();
        float dFrames = timeSinceFrame / frameDuration;
        float newFrameRaw = this.frameRaw + (isReversed() ? -dFrames : dFrames);
        boolean ended = !MiscUtils.contains(newFrameRaw, getMinFrame(), getMaxFrame());
        float previousFrameRaw = this.frameRaw;
        this.frameRaw = MiscUtils.clamp(newFrameRaw, getMinFrame(), getMaxFrame());
        this.frame = this.useCompositionFrameRate ? (float) Math.floor(this.frameRaw) : this.frameRaw;
        this.lastFrameTimeNs = frameTimeNanos;
        if (!this.useCompositionFrameRate || this.frameRaw != previousFrameRaw) {
            notifyUpdate();
        }
        if (ended) {
            if (getRepeatCount() != -1 && this.repeatCount >= getRepeatCount()) {
                this.frameRaw = this.speed < 0.0f ? getMinFrame() : getMaxFrame();
                this.frame = this.frameRaw;
                removeFrameCallback();
                notifyEnd(isReversed());
            } else {
                notifyRepeat();
                this.repeatCount++;
                if (getRepeatMode() == 2) {
                    this.speedReversedForRepeatMode = !this.speedReversedForRepeatMode;
                    reverseAnimationSpeed();
                } else {
                    this.frameRaw = isReversed() ? getMaxFrame() : getMinFrame();
                    this.frame = this.frameRaw;
                }
                this.lastFrameTimeNs = frameTimeNanos;
            }
        }
        verifyFrame();
        L.endSection("LottieValueAnimator#doFrame");
    }

    private float getFrameDurationNs() {
        if (this.composition == null) {
            return Float.MAX_VALUE;
        }
        return (1.0E9f / this.composition.getFrameRate()) / Math.abs(this.speed);
    }

    public void clearComposition() {
        this.composition = null;
        this.minFrame = -2.1474836E9f;
        this.maxFrame = 2.1474836E9f;
    }

    public void setComposition(LottieComposition composition) {
        boolean keepMinAndMaxFrames = this.composition == null;
        this.composition = composition;
        if (keepMinAndMaxFrames) {
            setMinAndMaxFrames(Math.max(this.minFrame, composition.getStartFrame()), Math.min(this.maxFrame, composition.getEndFrame()));
        } else {
            setMinAndMaxFrames((int) composition.getStartFrame(), (int) composition.getEndFrame());
        }
        float frame = this.frame;
        this.frame = 0.0f;
        this.frameRaw = 0.0f;
        setFrame((int) frame);
        notifyUpdate();
    }

    public void setFrame(float frame) {
        if (this.frameRaw == frame) {
            return;
        }
        this.frameRaw = MiscUtils.clamp(frame, getMinFrame(), getMaxFrame());
        this.frame = this.useCompositionFrameRate ? (float) Math.floor(this.frameRaw) : this.frameRaw;
        this.lastFrameTimeNs = 0L;
        notifyUpdate();
    }

    public void setMinFrame(int minFrame) {
        setMinAndMaxFrames(minFrame, (int) this.maxFrame);
    }

    public void setMaxFrame(float maxFrame) {
        setMinAndMaxFrames(this.minFrame, maxFrame);
    }

    public void setMinAndMaxFrames(float minFrame, float maxFrame) {
        if (minFrame > maxFrame) {
            throw new IllegalArgumentException(String.format("minFrame (%s) must be <= maxFrame (%s)", Float.valueOf(minFrame), Float.valueOf(maxFrame)));
        }
        float compositionMinFrame = this.composition == null ? -3.4028235E38f : this.composition.getStartFrame();
        float compositionMaxFrame = this.composition == null ? Float.MAX_VALUE : this.composition.getEndFrame();
        float newMinFrame = MiscUtils.clamp(minFrame, compositionMinFrame, compositionMaxFrame);
        float newMaxFrame = MiscUtils.clamp(maxFrame, compositionMinFrame, compositionMaxFrame);
        if (newMinFrame != this.minFrame || newMaxFrame != this.maxFrame) {
            this.minFrame = newMinFrame;
            this.maxFrame = newMaxFrame;
            setFrame((int) MiscUtils.clamp(this.frame, newMinFrame, newMaxFrame));
        }
    }

    public void reverseAnimationSpeed() {
        setSpeed(-getSpeed());
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return this.speed;
    }

    @Override // android.animation.ValueAnimator
    public void setRepeatMode(int value) {
        super.setRepeatMode(value);
        if (value != 2 && this.speedReversedForRepeatMode) {
            this.speedReversedForRepeatMode = false;
            reverseAnimationSpeed();
        }
    }

    public void playAnimation() {
        this.running = true;
        notifyStart(isReversed());
        setFrame((int) (isReversed() ? getMaxFrame() : getMinFrame()));
        this.lastFrameTimeNs = 0L;
        this.repeatCount = 0;
        postFrameCallback();
    }

    public void endAnimation() {
        removeFrameCallback();
        notifyEnd(isReversed());
    }

    public void pauseAnimation() {
        removeFrameCallback();
        notifyPause();
    }

    public void resumeAnimation() {
        this.running = true;
        postFrameCallback();
        this.lastFrameTimeNs = 0L;
        if (isReversed() && getFrame() == getMinFrame()) {
            setFrame(getMaxFrame());
        } else if (!isReversed() && getFrame() == getMaxFrame()) {
            setFrame(getMinFrame());
        }
        notifyResume();
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public void cancel() {
        notifyCancel();
        removeFrameCallback();
    }

    private boolean isReversed() {
        return getSpeed() < 0.0f;
    }

    public float getMinFrame() {
        if (this.composition == null) {
            return 0.0f;
        }
        return this.minFrame == -2.1474836E9f ? this.composition.getStartFrame() : this.minFrame;
    }

    public float getMaxFrame() {
        if (this.composition == null) {
            return 0.0f;
        }
        return this.maxFrame == 2.1474836E9f ? this.composition.getEndFrame() : this.maxFrame;
    }

    @Override // com.airbnb.lottie.utils.BaseLottieAnimator
    void notifyCancel() {
        super.notifyCancel();
        notifyEnd(isReversed());
    }

    protected void postFrameCallback() {
        if (isRunning()) {
            removeFrameCallback(false);
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    protected void removeFrameCallback() {
        removeFrameCallback(true);
    }

    protected void removeFrameCallback(boolean stopRunning) {
        Choreographer.getInstance().removeFrameCallback(this);
        if (stopRunning) {
            this.running = false;
        }
    }

    private void verifyFrame() {
        if (this.composition == null) {
            return;
        }
        if (this.frame < this.minFrame || this.frame > this.maxFrame) {
            throw new IllegalStateException(String.format("Frame must be [%f,%f]. It is %f", Float.valueOf(this.minFrame), Float.valueOf(this.maxFrame), Float.valueOf(this.frame)));
        }
    }
}
