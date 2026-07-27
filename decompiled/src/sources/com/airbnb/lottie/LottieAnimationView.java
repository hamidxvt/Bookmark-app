package com.airbnb.lottie;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.zip.ZipInputStream;

/* loaded from: classes.dex */
public class LottieAnimationView extends AppCompatImageView {
    private String animationName;
    private int animationResId;
    private boolean autoPlay;
    private boolean cacheComposition;
    private LottieTask<LottieComposition> compositionTask;
    private LottieListener<Throwable> failureListener;
    private int fallbackResource;
    private boolean ignoreUnschedule;
    private final LottieListener<LottieComposition> loadedListener;
    private final LottieDrawable lottieDrawable;
    private final Set<LottieOnCompositionLoadedListener> lottieOnCompositionLoadedListeners;
    private final Set<UserActionTaken> userActionsTaken;
    private final LottieListener<Throwable> wrappedFailureListener;
    private static final String TAG = LottieAnimationView.class.getSimpleName();
    private static final LottieListener<Throwable> DEFAULT_FAILURE_LISTENER = new LottieListener() { // from class: com.airbnb.lottie.LottieAnimationView$$ExternalSyntheticLambda1
        @Override // com.airbnb.lottie.LottieListener
        public final void onResult(Object obj) {
            LottieAnimationView.lambda$static$0((Throwable) obj);
        }
    };

    private enum UserActionTaken {
        SET_ANIMATION,
        SET_PROGRESS,
        SET_REPEAT_MODE,
        SET_REPEAT_COUNT,
        SET_IMAGE_ASSETS,
        PLAY_OPTION
    }

    static /* synthetic */ void lambda$static$0(Throwable throwable) {
        if (Utils.isNetworkException(throwable)) {
            Logger.warning("Unable to load composition.", throwable);
            return;
        }
        throw new IllegalStateException("Unable to parse composition", throwable);
    }

    private static class WeakSuccessListener implements LottieListener<LottieComposition> {
        private final WeakReference<LottieAnimationView> targetReference;

        public WeakSuccessListener(LottieAnimationView target) {
            this.targetReference = new WeakReference<>(target);
        }

        @Override // com.airbnb.lottie.LottieListener
        public void onResult(LottieComposition result) {
            LottieAnimationView targetView = this.targetReference.get();
            if (targetView == null) {
                return;
            }
            targetView.setComposition(result);
        }
    }

    private static class WeakFailureListener implements LottieListener<Throwable> {
        private final WeakReference<LottieAnimationView> targetReference;

        public WeakFailureListener(LottieAnimationView target) {
            this.targetReference = new WeakReference<>(target);
        }

        @Override // com.airbnb.lottie.LottieListener
        public void onResult(Throwable result) {
            LottieAnimationView targetView = this.targetReference.get();
            if (targetView != null) {
                if (targetView.fallbackResource != 0) {
                    targetView.setImageResource(targetView.fallbackResource);
                }
                LottieListener<Throwable> l = targetView.failureListener == null ? LottieAnimationView.DEFAULT_FAILURE_LISTENER : targetView.failureListener;
                l.onResult(result);
            }
        }
    }

    public LottieAnimationView(Context context) {
        super(context);
        this.loadedListener = new WeakSuccessListener(this);
        this.wrappedFailureListener = new WeakFailureListener(this);
        this.fallbackResource = 0;
        this.lottieDrawable = new LottieDrawable();
        this.ignoreUnschedule = false;
        this.autoPlay = false;
        this.cacheComposition = true;
        this.userActionsTaken = new HashSet();
        this.lottieOnCompositionLoadedListeners = new HashSet();
        init(null, R.attr.lottieAnimationViewStyle);
    }

    public LottieAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.loadedListener = new WeakSuccessListener(this);
        this.wrappedFailureListener = new WeakFailureListener(this);
        this.fallbackResource = 0;
        this.lottieDrawable = new LottieDrawable();
        this.ignoreUnschedule = false;
        this.autoPlay = false;
        this.cacheComposition = true;
        this.userActionsTaken = new HashSet();
        this.lottieOnCompositionLoadedListeners = new HashSet();
        init(attrs, R.attr.lottieAnimationViewStyle);
    }

    public LottieAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.loadedListener = new WeakSuccessListener(this);
        this.wrappedFailureListener = new WeakFailureListener(this);
        this.fallbackResource = 0;
        this.lottieDrawable = new LottieDrawable();
        this.ignoreUnschedule = false;
        this.autoPlay = false;
        this.cacheComposition = true;
        this.userActionsTaken = new HashSet();
        this.lottieOnCompositionLoadedListeners = new HashSet();
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        String url;
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.LottieAnimationView, defStyleAttr, 0);
        this.cacheComposition = ta.getBoolean(R.styleable.LottieAnimationView_lottie_cacheComposition, true);
        boolean hasRawRes = ta.hasValue(R.styleable.LottieAnimationView_lottie_rawRes);
        boolean hasFileName = ta.hasValue(R.styleable.LottieAnimationView_lottie_fileName);
        boolean hasUrl = ta.hasValue(R.styleable.LottieAnimationView_lottie_url);
        if (hasRawRes && hasFileName) {
            throw new IllegalArgumentException("lottie_rawRes and lottie_fileName cannot be used at the same time. Please use only one at once.");
        }
        if (hasRawRes) {
            int rawResId = ta.getResourceId(R.styleable.LottieAnimationView_lottie_rawRes, 0);
            if (rawResId != 0) {
                setAnimation(rawResId);
            }
        } else if (hasFileName) {
            String fileName = ta.getString(R.styleable.LottieAnimationView_lottie_fileName);
            if (fileName != null) {
                setAnimation(fileName);
            }
        } else if (hasUrl && (url = ta.getString(R.styleable.LottieAnimationView_lottie_url)) != null) {
            setAnimationFromUrl(url);
        }
        setFallbackResource(ta.getResourceId(R.styleable.LottieAnimationView_lottie_fallbackRes, 0));
        if (ta.getBoolean(R.styleable.LottieAnimationView_lottie_autoPlay, false)) {
            this.autoPlay = true;
        }
        if (ta.getBoolean(R.styleable.LottieAnimationView_lottie_loop, false)) {
            this.lottieDrawable.setRepeatCount(-1);
        }
        if (ta.hasValue(R.styleable.LottieAnimationView_lottie_repeatMode)) {
            setRepeatMode(ta.getInt(R.styleable.LottieAnimationView_lottie_repeatMode, 1));
        }
        if (ta.hasValue(R.styleable.LottieAnimationView_lottie_repeatCount)) {
            setRepeatCount(ta.getInt(R.styleable.LottieAnimationView_lottie_repeatCount, -1));
        }
        if (ta.hasValue(R.styleable.LottieAnimationView_lottie_speed)) {
            setSpeed(ta.getFloat(R.styleable.LottieAnimationView_lottie_speed, 1.0f));
        }
        if (ta.hasValue(R.styleable.LottieAnimationView_lottie_clipToCompositionBounds)) {
            setClipToCompositionBounds(ta.getBoolean(R.styleable.LottieAnimationView_lottie_clipToCompositionBounds, true));
        }
        if (ta.hasValue(R.styleable.LottieAnimationView_lottie_clipTextToBoundingBox)) {
            setClipTextToBoundingBox(ta.getBoolean(R.styleable.LottieAnimationView_lottie_clipTextToBoundingBox, false));
        }
        if (ta.hasValue(R.styleable.LottieAnimationView_lottie_defaultFontFileExtension)) {
            setDefaultFontFileExtension(ta.getString(R.styleable.LottieAnimationView_lottie_defaultFontFileExtension));
        }
        setImageAssetsFolder(ta.getString(R.styleable.LottieAnimationView_lottie_imageAssetsFolder));
        boolean hasProgress = ta.hasValue(R.styleable.LottieAnimationView_lottie_progress);
        setProgressInternal(ta.getFloat(R.styleable.LottieAnimationView_lottie_progress, 0.0f), hasProgress);
        enableMergePathsForKitKatAndAbove(ta.getBoolean(R.styleable.LottieAnimationView_lottie_enableMergePathsForKitKatAndAbove, false));
        if (ta.hasValue(R.styleable.LottieAnimationView_lottie_colorFilter)) {
            int colorRes = ta.getResourceId(R.styleable.LottieAnimationView_lottie_colorFilter, -1);
            ColorStateList csl = AppCompatResources.getColorStateList(getContext(), colorRes);
            SimpleColorFilter filter = new SimpleColorFilter(csl.getDefaultColor());
            KeyPath keyPath = new KeyPath("**");
            LottieValueCallback<ColorFilter> callback = new LottieValueCallback<>(filter);
            addValueCallback(keyPath, (KeyPath) LottieProperty.COLOR_FILTER, (LottieValueCallback<KeyPath>) callback);
        }
        if (ta.hasValue(R.styleable.LottieAnimationView_lottie_renderMode)) {
            int renderModeOrdinal = ta.getInt(R.styleable.LottieAnimationView_lottie_renderMode, RenderMode.AUTOMATIC.ordinal());
            if (renderModeOrdinal >= RenderMode.values().length) {
                renderModeOrdinal = RenderMode.AUTOMATIC.ordinal();
            }
            setRenderMode(RenderMode.values()[renderModeOrdinal]);
        }
        if (ta.hasValue(R.styleable.LottieAnimationView_lottie_asyncUpdates)) {
            int asyncUpdatesOrdinal = ta.getInt(R.styleable.LottieAnimationView_lottie_asyncUpdates, AsyncUpdates.AUTOMATIC.ordinal());
            if (asyncUpdatesOrdinal >= RenderMode.values().length) {
                asyncUpdatesOrdinal = AsyncUpdates.AUTOMATIC.ordinal();
            }
            setAsyncUpdates(AsyncUpdates.values()[asyncUpdatesOrdinal]);
        }
        setIgnoreDisabledSystemAnimations(ta.getBoolean(R.styleable.LottieAnimationView_lottie_ignoreDisabledSystemAnimations, false));
        if (ta.hasValue(R.styleable.LottieAnimationView_lottie_useCompositionFrameRate)) {
            setUseCompositionFrameRate(ta.getBoolean(R.styleable.LottieAnimationView_lottie_useCompositionFrameRate, false));
        }
        ta.recycle();
        this.lottieDrawable.setSystemAnimationsAreEnabled(Boolean.valueOf(Utils.getAnimationScale(getContext()) != 0.0f));
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int resId) {
        this.animationResId = 0;
        this.animationName = null;
        cancelLoaderTask();
        super.setImageResource(resId);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        this.animationResId = 0;
        this.animationName = null;
        cancelLoaderTask();
        super.setImageDrawable(drawable);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bm) {
        this.animationResId = 0;
        this.animationName = null;
        cancelLoaderTask();
        super.setImageBitmap(bm);
    }

    @Override // android.view.View
    public void unscheduleDrawable(Drawable who) {
        if (!this.ignoreUnschedule && who == this.lottieDrawable && this.lottieDrawable.isAnimating()) {
            pauseAnimation();
        } else if (!this.ignoreUnschedule && (who instanceof LottieDrawable) && ((LottieDrawable) who).isAnimating()) {
            ((LottieDrawable) who).pauseAnimation();
        }
        super.unscheduleDrawable(who);
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        Drawable d = getDrawable();
        if ((d instanceof LottieDrawable) && ((LottieDrawable) d).getRenderMode() == RenderMode.SOFTWARE) {
            this.lottieDrawable.invalidateSelf();
        }
    }

    @Override // android.widget.ImageView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable dr) {
        if (getDrawable() == this.lottieDrawable) {
            super.invalidateDrawable(this.lottieDrawable);
        } else {
            super.invalidateDrawable(dr);
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.animationName = this.animationName;
        ss.animationResId = this.animationResId;
        ss.progress = this.lottieDrawable.getProgress();
        ss.isAnimating = this.lottieDrawable.isAnimatingOrWillAnimateOnVisible();
        ss.imageAssetsFolder = this.lottieDrawable.getImageAssetsFolder();
        ss.repeatMode = this.lottieDrawable.getRepeatMode();
        ss.repeatCount = this.lottieDrawable.getRepeatCount();
        return ss;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        this.animationName = ss.animationName;
        if (!this.userActionsTaken.contains(UserActionTaken.SET_ANIMATION) && !TextUtils.isEmpty(this.animationName)) {
            setAnimation(this.animationName);
        }
        this.animationResId = ss.animationResId;
        if (!this.userActionsTaken.contains(UserActionTaken.SET_ANIMATION) && this.animationResId != 0) {
            setAnimation(this.animationResId);
        }
        if (!this.userActionsTaken.contains(UserActionTaken.SET_PROGRESS)) {
            setProgressInternal(ss.progress, false);
        }
        if (!this.userActionsTaken.contains(UserActionTaken.PLAY_OPTION) && ss.isAnimating) {
            playAnimation();
        }
        if (!this.userActionsTaken.contains(UserActionTaken.SET_IMAGE_ASSETS)) {
            setImageAssetsFolder(ss.imageAssetsFolder);
        }
        if (!this.userActionsTaken.contains(UserActionTaken.SET_REPEAT_MODE)) {
            setRepeatMode(ss.repeatMode);
        }
        if (!this.userActionsTaken.contains(UserActionTaken.SET_REPEAT_COUNT)) {
            setRepeatCount(ss.repeatCount);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode() && this.autoPlay) {
            this.lottieDrawable.playAnimation();
        }
    }

    public void setIgnoreDisabledSystemAnimations(boolean ignore) {
        this.lottieDrawable.setIgnoreDisabledSystemAnimations(ignore);
    }

    public void setUseCompositionFrameRate(boolean useCompositionFrameRate) {
        this.lottieDrawable.setUseCompositionFrameRate(useCompositionFrameRate);
    }

    public void enableMergePathsForKitKatAndAbove(boolean enable) {
        this.lottieDrawable.enableMergePathsForKitKatAndAbove(enable);
    }

    public boolean isMergePathsEnabledForKitKatAndAbove() {
        return this.lottieDrawable.isMergePathsEnabledForKitKatAndAbove();
    }

    public void setClipToCompositionBounds(boolean clipToCompositionBounds) {
        this.lottieDrawable.setClipToCompositionBounds(clipToCompositionBounds);
    }

    public boolean getClipToCompositionBounds() {
        return this.lottieDrawable.getClipToCompositionBounds();
    }

    public void setCacheComposition(boolean cacheComposition) {
        this.cacheComposition = cacheComposition;
    }

    public void setOutlineMasksAndMattes(boolean outline) {
        this.lottieDrawable.setOutlineMasksAndMattes(outline);
    }

    public void setAnimation(int rawRes) {
        this.animationResId = rawRes;
        this.animationName = null;
        setCompositionTask(fromRawRes(rawRes));
    }

    private LottieTask<LottieComposition> fromRawRes(final int rawRes) {
        if (isInEditMode()) {
            return new LottieTask<>(new Callable() { // from class: com.airbnb.lottie.LottieAnimationView$$ExternalSyntheticLambda2
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return LottieAnimationView.this.m190lambda$fromRawRes$1$comairbnblottieLottieAnimationView(rawRes);
                }
            }, true);
        }
        return this.cacheComposition ? LottieCompositionFactory.fromRawRes(getContext(), rawRes) : LottieCompositionFactory.fromRawRes(getContext(), rawRes, null);
    }

    /* renamed from: lambda$fromRawRes$1$com-airbnb-lottie-LottieAnimationView, reason: not valid java name */
    /* synthetic */ LottieResult m190lambda$fromRawRes$1$comairbnblottieLottieAnimationView(int rawRes) throws Exception {
        return this.cacheComposition ? LottieCompositionFactory.fromRawResSync(getContext(), rawRes) : LottieCompositionFactory.fromRawResSync(getContext(), rawRes, null);
    }

    public void setAnimation(String assetName) {
        this.animationName = assetName;
        this.animationResId = 0;
        setCompositionTask(fromAssets(assetName));
    }

    private LottieTask<LottieComposition> fromAssets(final String assetName) {
        if (isInEditMode()) {
            return new LottieTask<>(new Callable() { // from class: com.airbnb.lottie.LottieAnimationView$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return LottieAnimationView.this.m189lambda$fromAssets$2$comairbnblottieLottieAnimationView(assetName);
                }
            }, true);
        }
        return this.cacheComposition ? LottieCompositionFactory.fromAsset(getContext(), assetName) : LottieCompositionFactory.fromAsset(getContext(), assetName, null);
    }

    /* renamed from: lambda$fromAssets$2$com-airbnb-lottie-LottieAnimationView, reason: not valid java name */
    /* synthetic */ LottieResult m189lambda$fromAssets$2$comairbnblottieLottieAnimationView(String assetName) throws Exception {
        return this.cacheComposition ? LottieCompositionFactory.fromAssetSync(getContext(), assetName) : LottieCompositionFactory.fromAssetSync(getContext(), assetName, null);
    }

    @Deprecated
    public void setAnimationFromJson(String jsonString) {
        setAnimationFromJson(jsonString, null);
    }

    public void setAnimationFromJson(String jsonString, String cacheKey) {
        setAnimation(new ByteArrayInputStream(jsonString.getBytes()), cacheKey);
    }

    public void setAnimation(InputStream stream, String cacheKey) {
        setCompositionTask(LottieCompositionFactory.fromJsonInputStream(stream, cacheKey));
    }

    public void setAnimation(ZipInputStream stream, String cacheKey) {
        setCompositionTask(LottieCompositionFactory.fromZipStream(stream, cacheKey));
    }

    public void setAnimationFromUrl(String url) {
        LottieTask<LottieComposition> task = this.cacheComposition ? LottieCompositionFactory.fromUrl(getContext(), url) : LottieCompositionFactory.fromUrl(getContext(), url, null);
        setCompositionTask(task);
    }

    public void setAnimationFromUrl(String url, String cacheKey) {
        LottieTask<LottieComposition> task = LottieCompositionFactory.fromUrl(getContext(), url, cacheKey);
        setCompositionTask(task);
    }

    public void setFailureListener(LottieListener<Throwable> failureListener) {
        this.failureListener = failureListener;
    }

    public void setFallbackResource(int fallbackResource) {
        this.fallbackResource = fallbackResource;
    }

    private void setCompositionTask(LottieTask<LottieComposition> compositionTask) {
        LottieResult<LottieComposition> result = compositionTask.getResult();
        LottieDrawable lottieDrawable = this.lottieDrawable;
        if (result != null && lottieDrawable == getDrawable() && lottieDrawable.getComposition() == result.getValue()) {
            return;
        }
        this.userActionsTaken.add(UserActionTaken.SET_ANIMATION);
        clearComposition();
        cancelLoaderTask();
        this.compositionTask = compositionTask.addListener(this.loadedListener).addFailureListener(this.wrappedFailureListener);
    }

    private void cancelLoaderTask() {
        if (this.compositionTask != null) {
            this.compositionTask.removeListener(this.loadedListener);
            this.compositionTask.removeFailureListener(this.wrappedFailureListener);
        }
    }

    public void setComposition(LottieComposition composition) {
        if (L.DBG) {
            Log.v(TAG, "Set Composition \n" + composition);
        }
        this.lottieDrawable.setCallback(this);
        this.ignoreUnschedule = true;
        boolean isNewComposition = this.lottieDrawable.setComposition(composition);
        if (this.autoPlay) {
            this.lottieDrawable.playAnimation();
        }
        this.ignoreUnschedule = false;
        if (getDrawable() == this.lottieDrawable && !isNewComposition) {
            return;
        }
        if (!isNewComposition) {
            setLottieDrawable();
        }
        onVisibilityChanged(this, getVisibility());
        requestLayout();
        for (LottieOnCompositionLoadedListener lottieOnCompositionLoadedListener : this.lottieOnCompositionLoadedListeners) {
            lottieOnCompositionLoadedListener.onCompositionLoaded(composition);
        }
    }

    public LottieComposition getComposition() {
        if (getDrawable() == this.lottieDrawable) {
            return this.lottieDrawable.getComposition();
        }
        return null;
    }

    public boolean hasMasks() {
        return this.lottieDrawable.hasMasks();
    }

    public boolean hasMatte() {
        return this.lottieDrawable.hasMatte();
    }

    public void playAnimation() {
        this.userActionsTaken.add(UserActionTaken.PLAY_OPTION);
        this.lottieDrawable.playAnimation();
    }

    public void resumeAnimation() {
        this.userActionsTaken.add(UserActionTaken.PLAY_OPTION);
        this.lottieDrawable.resumeAnimation();
    }

    public void setMinFrame(int startFrame) {
        this.lottieDrawable.setMinFrame(startFrame);
    }

    public float getMinFrame() {
        return this.lottieDrawable.getMinFrame();
    }

    public void setMinProgress(float startProgress) {
        this.lottieDrawable.setMinProgress(startProgress);
    }

    public void setMaxFrame(int endFrame) {
        this.lottieDrawable.setMaxFrame(endFrame);
    }

    public float getMaxFrame() {
        return this.lottieDrawable.getMaxFrame();
    }

    public void setMaxProgress(float endProgress) {
        this.lottieDrawable.setMaxProgress(endProgress);
    }

    public void setMinFrame(String markerName) {
        this.lottieDrawable.setMinFrame(markerName);
    }

    public void setMaxFrame(String markerName) {
        this.lottieDrawable.setMaxFrame(markerName);
    }

    public void setMinAndMaxFrame(String markerName) {
        this.lottieDrawable.setMinAndMaxFrame(markerName);
    }

    public void setMinAndMaxFrame(String startMarkerName, String endMarkerName, boolean playEndMarkerStartFrame) {
        this.lottieDrawable.setMinAndMaxFrame(startMarkerName, endMarkerName, playEndMarkerStartFrame);
    }

    public void setMinAndMaxFrame(int minFrame, int maxFrame) {
        this.lottieDrawable.setMinAndMaxFrame(minFrame, maxFrame);
    }

    public void setMinAndMaxProgress(float minProgress, float maxProgress) {
        this.lottieDrawable.setMinAndMaxProgress(minProgress, maxProgress);
    }

    public void reverseAnimationSpeed() {
        this.lottieDrawable.reverseAnimationSpeed();
    }

    public void setSpeed(float speed) {
        this.lottieDrawable.setSpeed(speed);
    }

    public float getSpeed() {
        return this.lottieDrawable.getSpeed();
    }

    public void addAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener updateListener) {
        this.lottieDrawable.addAnimatorUpdateListener(updateListener);
    }

    public void removeUpdateListener(ValueAnimator.AnimatorUpdateListener updateListener) {
        this.lottieDrawable.removeAnimatorUpdateListener(updateListener);
    }

    public void removeAllUpdateListeners() {
        this.lottieDrawable.removeAllUpdateListeners();
    }

    public void addAnimatorListener(Animator.AnimatorListener listener) {
        this.lottieDrawable.addAnimatorListener(listener);
    }

    public void removeAnimatorListener(Animator.AnimatorListener listener) {
        this.lottieDrawable.removeAnimatorListener(listener);
    }

    public void removeAllAnimatorListeners() {
        this.lottieDrawable.removeAllAnimatorListeners();
    }

    public void addAnimatorPauseListener(Animator.AnimatorPauseListener listener) {
        this.lottieDrawable.addAnimatorPauseListener(listener);
    }

    public void removeAnimatorPauseListener(Animator.AnimatorPauseListener listener) {
        this.lottieDrawable.removeAnimatorPauseListener(listener);
    }

    @Deprecated
    public void loop(boolean loop) {
        this.lottieDrawable.setRepeatCount(loop ? -1 : 0);
    }

    public void setRepeatMode(int mode) {
        this.userActionsTaken.add(UserActionTaken.SET_REPEAT_MODE);
        this.lottieDrawable.setRepeatMode(mode);
    }

    public int getRepeatMode() {
        return this.lottieDrawable.getRepeatMode();
    }

    public void setRepeatCount(int count) {
        this.userActionsTaken.add(UserActionTaken.SET_REPEAT_COUNT);
        this.lottieDrawable.setRepeatCount(count);
    }

    public int getRepeatCount() {
        return this.lottieDrawable.getRepeatCount();
    }

    public boolean isAnimating() {
        return this.lottieDrawable.isAnimating();
    }

    public void setImageAssetsFolder(String imageAssetsFolder) {
        this.lottieDrawable.setImagesAssetsFolder(imageAssetsFolder);
    }

    public String getImageAssetsFolder() {
        return this.lottieDrawable.getImageAssetsFolder();
    }

    public void setMaintainOriginalImageBounds(boolean maintainOriginalImageBounds) {
        this.lottieDrawable.setMaintainOriginalImageBounds(maintainOriginalImageBounds);
    }

    public boolean getMaintainOriginalImageBounds() {
        return this.lottieDrawable.getMaintainOriginalImageBounds();
    }

    public Bitmap updateBitmap(String id, Bitmap bitmap) {
        return this.lottieDrawable.updateBitmap(id, bitmap);
    }

    public void setImageAssetDelegate(ImageAssetDelegate assetDelegate) {
        this.lottieDrawable.setImageAssetDelegate(assetDelegate);
    }

    public void setDefaultFontFileExtension(String extension) {
        this.lottieDrawable.setDefaultFontFileExtension(extension);
    }

    public void setFontAssetDelegate(FontAssetDelegate assetDelegate) {
        this.lottieDrawable.setFontAssetDelegate(assetDelegate);
    }

    public void setFontMap(Map<String, Typeface> fontMap) {
        this.lottieDrawable.setFontMap(fontMap);
    }

    public void setTextDelegate(TextDelegate textDelegate) {
        this.lottieDrawable.setTextDelegate(textDelegate);
    }

    public List<KeyPath> resolveKeyPath(KeyPath keyPath) {
        return this.lottieDrawable.resolveKeyPath(keyPath);
    }

    public <T> void clearValueCallback(KeyPath keyPath, T property) {
        this.lottieDrawable.addValueCallback(keyPath, (KeyPath) property, (LottieValueCallback<KeyPath>) null);
    }

    public <T> void addValueCallback(KeyPath keyPath, T property, LottieValueCallback<T> callback) {
        this.lottieDrawable.addValueCallback(keyPath, (KeyPath) property, (LottieValueCallback<KeyPath>) callback);
    }

    public <T> void addValueCallback(KeyPath keyPath, T property, final SimpleLottieValueCallback<T> callback) {
        this.lottieDrawable.addValueCallback(keyPath, (KeyPath) property, (LottieValueCallback<KeyPath>) new LottieValueCallback<T>() { // from class: com.airbnb.lottie.LottieAnimationView.1
            @Override // com.airbnb.lottie.value.LottieValueCallback
            public T getValue(LottieFrameInfo<T> lottieFrameInfo) {
                return (T) callback.getValue(lottieFrameInfo);
            }
        });
    }

    public void cancelAnimation() {
        this.autoPlay = false;
        this.userActionsTaken.add(UserActionTaken.PLAY_OPTION);
        this.lottieDrawable.cancelAnimation();
    }

    public void pauseAnimation() {
        this.autoPlay = false;
        this.lottieDrawable.pauseAnimation();
    }

    public void setFrame(int frame) {
        this.lottieDrawable.setFrame(frame);
    }

    public int getFrame() {
        return this.lottieDrawable.getFrame();
    }

    public void setProgress(float progress) {
        setProgressInternal(progress, true);
    }

    private void setProgressInternal(float progress, boolean fromUser) {
        if (fromUser) {
            this.userActionsTaken.add(UserActionTaken.SET_PROGRESS);
        }
        this.lottieDrawable.setProgress(progress);
    }

    public float getProgress() {
        return this.lottieDrawable.getProgress();
    }

    public long getDuration() {
        LottieComposition composition = getComposition();
        if (composition != null) {
            return (long) composition.getDuration();
        }
        return 0L;
    }

    public void setPerformanceTrackingEnabled(boolean enabled) {
        this.lottieDrawable.setPerformanceTrackingEnabled(enabled);
    }

    public PerformanceTracker getPerformanceTracker() {
        return this.lottieDrawable.getPerformanceTracker();
    }

    private void clearComposition() {
        this.lottieDrawable.clearComposition();
    }

    public void setSafeMode(boolean safeMode) {
        this.lottieDrawable.setSafeMode(safeMode);
    }

    public void setRenderMode(RenderMode renderMode) {
        this.lottieDrawable.setRenderMode(renderMode);
    }

    public RenderMode getRenderMode() {
        return this.lottieDrawable.getRenderMode();
    }

    public AsyncUpdates getAsyncUpdates() {
        return this.lottieDrawable.getAsyncUpdates();
    }

    public boolean getAsyncUpdatesEnabled() {
        return this.lottieDrawable.getAsyncUpdatesEnabled();
    }

    public void setAsyncUpdates(AsyncUpdates asyncUpdates) {
        this.lottieDrawable.setAsyncUpdates(asyncUpdates);
    }

    public void setApplyingOpacityToLayersEnabled(boolean isApplyingOpacityToLayersEnabled) {
        this.lottieDrawable.setApplyingOpacityToLayersEnabled(isApplyingOpacityToLayersEnabled);
    }

    public boolean getClipTextToBoundingBox() {
        return this.lottieDrawable.getClipTextToBoundingBox();
    }

    public void setClipTextToBoundingBox(boolean clipTextToBoundingBox) {
        this.lottieDrawable.setClipTextToBoundingBox(clipTextToBoundingBox);
    }

    @Deprecated
    public void disableExtraScaleModeInFitXY() {
        this.lottieDrawable.disableExtraScaleModeInFitXY();
    }

    public boolean addLottieOnCompositionLoadedListener(LottieOnCompositionLoadedListener lottieOnCompositionLoadedListener) {
        LottieComposition composition = getComposition();
        if (composition != null) {
            lottieOnCompositionLoadedListener.onCompositionLoaded(composition);
        }
        return this.lottieOnCompositionLoadedListeners.add(lottieOnCompositionLoadedListener);
    }

    public boolean removeLottieOnCompositionLoadedListener(LottieOnCompositionLoadedListener lottieOnCompositionLoadedListener) {
        return this.lottieOnCompositionLoadedListeners.remove(lottieOnCompositionLoadedListener);
    }

    public void removeAllLottieOnCompositionLoadedListener() {
        this.lottieOnCompositionLoadedListeners.clear();
    }

    private void setLottieDrawable() {
        boolean wasAnimating = isAnimating();
        setImageDrawable(null);
        setImageDrawable(this.lottieDrawable);
        if (wasAnimating) {
            this.lottieDrawable.resumeAnimation();
        }
    }

    private static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.airbnb.lottie.LottieAnimationView.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        String animationName;
        int animationResId;
        String imageAssetsFolder;
        boolean isAnimating;
        float progress;
        int repeatCount;
        int repeatMode;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.animationName = in.readString();
            this.progress = in.readFloat();
            this.isAnimating = in.readInt() == 1;
            this.imageAssetsFolder = in.readString();
            this.repeatMode = in.readInt();
            this.repeatCount = in.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.animationName);
            parcel.writeFloat(this.progress);
            parcel.writeInt(this.isAnimating ? 1 : 0);
            parcel.writeString(this.imageAssetsFolder);
            parcel.writeInt(this.repeatMode);
            parcel.writeInt(this.repeatCount);
        }
    }
}
