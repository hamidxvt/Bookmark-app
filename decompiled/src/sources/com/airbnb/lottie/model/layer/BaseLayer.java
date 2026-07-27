package com.airbnb.lottie.model.layer;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import androidx.core.view.ViewCompat;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.DrawingContent;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.MaskKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.KeyPathElement;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.LBlendMode;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.DropShadowEffect;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseLayer implements DrawingContent, BaseKeyframeAnimation.AnimationListener, KeyPathElement {
    private static final int CLIP_SAVE_FLAG = 2;
    private static final int CLIP_TO_LAYER_SAVE_FLAG = 16;
    private static final int MATRIX_SAVE_FLAG = 1;
    private static final int SAVE_FLAGS = 19;
    BlurMaskFilter blurMaskFilter;
    private final String drawTraceName;
    private FloatKeyframeAnimation inOutAnimation;
    final Layer layerModel;
    final LottieDrawable lottieDrawable;
    private MaskKeyframeAnimation mask;
    private BaseLayer matteLayer;
    private boolean outlineMasksAndMattes;
    private Paint outlineMasksAndMattesPaint;
    private BaseLayer parentLayer;
    private List<BaseLayer> parentLayers;
    final TransformKeyframeAnimation transform;
    private final Path path = new Path();
    private final Matrix matrix = new Matrix();
    private final Matrix canvasMatrix = new Matrix();
    private final Paint contentPaint = new LPaint(1);
    private final Paint dstInPaint = new LPaint(1, PorterDuff.Mode.DST_IN);
    private final Paint dstOutPaint = new LPaint(1, PorterDuff.Mode.DST_OUT);
    private final Paint mattePaint = new LPaint(1);
    private final Paint clearPaint = new LPaint(PorterDuff.Mode.CLEAR);
    private final RectF rect = new RectF();
    private final RectF canvasBounds = new RectF();
    private final RectF maskBoundsRect = new RectF();
    private final RectF matteBoundsRect = new RectF();
    private final RectF tempMaskBoundsRect = new RectF();
    final Matrix boundsMatrix = new Matrix();
    private final List<BaseKeyframeAnimation<?, ?>> animations = new ArrayList();
    private boolean visible = true;
    float blurMaskFilterRadius = 0.0f;

    abstract void drawLayer(Canvas canvas, Matrix matrix, int i);

    static BaseLayer forModel(CompositionLayer compositionLayer, Layer layerModel, LottieDrawable drawable, LottieComposition composition) {
        switch (layerModel.getLayerType()) {
            case SHAPE:
                return new ShapeLayer(drawable, layerModel, compositionLayer, composition);
            case PRE_COMP:
                return new CompositionLayer(drawable, layerModel, composition.getPrecomps(layerModel.getRefId()), composition);
            case SOLID:
                return new SolidLayer(drawable, layerModel);
            case IMAGE:
                return new ImageLayer(drawable, layerModel);
            case NULL:
                return new NullLayer(drawable, layerModel);
            case TEXT:
                return new TextLayer(drawable, layerModel);
            default:
                Logger.warning("Unknown layer type " + layerModel.getLayerType());
                return null;
        }
    }

    BaseLayer(LottieDrawable lottieDrawable, Layer layerModel) {
        this.lottieDrawable = lottieDrawable;
        this.layerModel = layerModel;
        this.drawTraceName = layerModel.getName() + "#draw";
        if (layerModel.getMatteType() == Layer.MatteType.INVERT) {
            this.mattePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        } else {
            this.mattePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        }
        this.transform = layerModel.getTransform().createAnimation();
        this.transform.addListener(this);
        if (layerModel.getMasks() != null && !layerModel.getMasks().isEmpty()) {
            this.mask = new MaskKeyframeAnimation(layerModel.getMasks());
            Iterator<BaseKeyframeAnimation<ShapeData, Path>> it = this.mask.getMaskAnimations().iterator();
            while (it.hasNext()) {
                it.next().addUpdateListener(this);
            }
            for (BaseKeyframeAnimation<Integer, Integer> animation : this.mask.getOpacityAnimations()) {
                addAnimation(animation);
                animation.addUpdateListener(this);
            }
        }
        setupInOutAnimations();
    }

    void setOutlineMasksAndMattes(boolean outline) {
        if (outline && this.outlineMasksAndMattesPaint == null) {
            this.outlineMasksAndMattesPaint = new LPaint();
        }
        this.outlineMasksAndMattes = outline;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        invalidateSelf();
    }

    Layer getLayerModel() {
        return this.layerModel;
    }

    void setMatteLayer(BaseLayer matteLayer) {
        this.matteLayer = matteLayer;
    }

    boolean hasMatteOnThisLayer() {
        return this.matteLayer != null;
    }

    void setParentLayer(BaseLayer parentLayer) {
        this.parentLayer = parentLayer;
    }

    private void setupInOutAnimations() {
        if (!this.layerModel.getInOutKeyframes().isEmpty()) {
            this.inOutAnimation = new FloatKeyframeAnimation(this.layerModel.getInOutKeyframes());
            this.inOutAnimation.setIsDiscrete();
            this.inOutAnimation.addUpdateListener(new BaseKeyframeAnimation.AnimationListener() { // from class: com.airbnb.lottie.model.layer.BaseLayer$$ExternalSyntheticLambda0
                @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
                public final void onValueChanged() {
                    BaseLayer.this.m210xf49bd987();
                }
            });
            setVisible(this.inOutAnimation.getValue().floatValue() == 1.0f);
            addAnimation(this.inOutAnimation);
            return;
        }
        setVisible(true);
    }

    /* renamed from: lambda$setupInOutAnimations$0$com-airbnb-lottie-model-layer-BaseLayer, reason: not valid java name */
    /* synthetic */ void m210xf49bd987() {
        setVisible(this.inOutAnimation.getFloatValue() == 1.0f);
    }

    private void invalidateSelf() {
        this.lottieDrawable.invalidateSelf();
    }

    public void addAnimation(BaseKeyframeAnimation<?, ?> newAnimation) {
        if (newAnimation == null) {
            return;
        }
        this.animations.add(newAnimation);
    }

    public void removeAnimation(BaseKeyframeAnimation<?, ?> animation) {
        this.animations.remove(animation);
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF outBounds, Matrix parentMatrix, boolean applyParents) {
        this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
        buildParentLayerListIfNeeded();
        this.boundsMatrix.set(parentMatrix);
        if (applyParents) {
            if (this.parentLayers != null) {
                for (int i = this.parentLayers.size() - 1; i >= 0; i--) {
                    this.boundsMatrix.preConcat(this.parentLayers.get(i).transform.getMatrix());
                }
            } else if (this.parentLayer != null) {
                this.boundsMatrix.preConcat(this.parentLayer.transform.getMatrix());
            }
        }
        this.boundsMatrix.preConcat(this.transform.getMatrix());
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void draw(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        Integer opacityValue;
        L.beginSection(this.drawTraceName);
        if (!this.visible || this.layerModel.isHidden()) {
            L.endSection(this.drawTraceName);
            return;
        }
        buildParentLayerListIfNeeded();
        L.beginSection("Layer#parentMatrix");
        this.matrix.reset();
        this.matrix.set(parentMatrix);
        for (int i = this.parentLayers.size() - 1; i >= 0; i--) {
            this.matrix.preConcat(this.parentLayers.get(i).transform.getMatrix());
        }
        L.endSection("Layer#parentMatrix");
        int opacity = 100;
        BaseKeyframeAnimation<?, Integer> opacityAnimation = this.transform.getOpacity();
        if (opacityAnimation != null && (opacityValue = opacityAnimation.getValue()) != null) {
            opacity = opacityValue.intValue();
        }
        int alpha = (int) ((((parentAlpha / 255.0f) * opacity) / 100.0f) * 255.0f);
        if (!hasMatteOnThisLayer() && !hasMasksOnThisLayer()) {
            this.matrix.preConcat(this.transform.getMatrix());
            L.beginSection("Layer#drawLayer");
            drawLayer(canvas, this.matrix, alpha);
            L.endSection("Layer#drawLayer");
            recordRenderTime(L.endSection(this.drawTraceName));
            return;
        }
        L.beginSection("Layer#computeBounds");
        getBounds(this.rect, this.matrix, false);
        intersectBoundsWithMatte(this.rect, parentMatrix);
        this.matrix.preConcat(this.transform.getMatrix());
        intersectBoundsWithMask(this.rect, this.matrix);
        this.canvasBounds.set(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight());
        canvas.getMatrix(this.canvasMatrix);
        if (!this.canvasMatrix.isIdentity()) {
            this.canvasMatrix.invert(this.canvasMatrix);
            this.canvasMatrix.mapRect(this.canvasBounds);
        }
        if (!this.rect.intersect(this.canvasBounds)) {
            this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
        L.endSection("Layer#computeBounds");
        if (this.rect.width() >= 1.0f && this.rect.height() >= 1.0f) {
            L.beginSection("Layer#saveLayer");
            this.contentPaint.setAlpha(255);
            Utils.saveLayerCompat(canvas, this.rect, this.contentPaint);
            L.endSection("Layer#saveLayer");
            clearCanvas(canvas);
            L.beginSection("Layer#drawLayer");
            drawLayer(canvas, this.matrix, alpha);
            L.endSection("Layer#drawLayer");
            if (hasMasksOnThisLayer()) {
                applyMasks(canvas, this.matrix);
            }
            if (hasMatteOnThisLayer()) {
                L.beginSection("Layer#drawMatte");
                L.beginSection("Layer#saveLayer");
                Utils.saveLayerCompat(canvas, this.rect, this.mattePaint, 19);
                L.endSection("Layer#saveLayer");
                clearCanvas(canvas);
                this.matteLayer.draw(canvas, parentMatrix, alpha);
                L.beginSection("Layer#restoreLayer");
                canvas.restore();
                L.endSection("Layer#restoreLayer");
                L.endSection("Layer#drawMatte");
            }
            L.beginSection("Layer#restoreLayer");
            canvas.restore();
            L.endSection("Layer#restoreLayer");
        }
        if (this.outlineMasksAndMattes && this.outlineMasksAndMattesPaint != null) {
            this.outlineMasksAndMattesPaint.setStyle(Paint.Style.STROKE);
            this.outlineMasksAndMattesPaint.setColor(-251901);
            this.outlineMasksAndMattesPaint.setStrokeWidth(4.0f);
            canvas.drawRect(this.rect, this.outlineMasksAndMattesPaint);
            this.outlineMasksAndMattesPaint.setStyle(Paint.Style.FILL);
            this.outlineMasksAndMattesPaint.setColor(1357638635);
            canvas.drawRect(this.rect, this.outlineMasksAndMattesPaint);
        }
        recordRenderTime(L.endSection(this.drawTraceName));
    }

    private void recordRenderTime(float ms) {
        this.lottieDrawable.getComposition().getPerformanceTracker().recordRenderTime(this.layerModel.getName(), ms);
    }

    private void clearCanvas(Canvas canvas) {
        L.beginSection("Layer#clearLayer");
        canvas.drawRect(this.rect.left - 1.0f, this.rect.top - 1.0f, this.rect.right + 1.0f, this.rect.bottom + 1.0f, this.clearPaint);
        L.endSection("Layer#clearLayer");
    }

    private void intersectBoundsWithMask(RectF rect, Matrix matrix) {
        this.maskBoundsRect.set(0.0f, 0.0f, 0.0f, 0.0f);
        if (!hasMasksOnThisLayer()) {
            return;
        }
        int size = this.mask.getMasks().size();
        for (int i = 0; i < size; i++) {
            Mask mask = this.mask.getMasks().get(i);
            BaseKeyframeAnimation<?, Path> maskAnimation = this.mask.getMaskAnimations().get(i);
            Path maskPath = maskAnimation.getValue();
            if (maskPath != null) {
                this.path.set(maskPath);
                this.path.transform(matrix);
                switch (mask.getMaskMode()) {
                    case MASK_MODE_NONE:
                        break;
                    case MASK_MODE_SUBTRACT:
                        break;
                    case MASK_MODE_INTERSECT:
                    case MASK_MODE_ADD:
                        if (mask.isInverted()) {
                            break;
                        }
                    default:
                        this.path.computeBounds(this.tempMaskBoundsRect, false);
                        if (i == 0) {
                            this.maskBoundsRect.set(this.tempMaskBoundsRect);
                            break;
                        } else {
                            this.maskBoundsRect.set(Math.min(this.maskBoundsRect.left, this.tempMaskBoundsRect.left), Math.min(this.maskBoundsRect.top, this.tempMaskBoundsRect.top), Math.max(this.maskBoundsRect.right, this.tempMaskBoundsRect.right), Math.max(this.maskBoundsRect.bottom, this.tempMaskBoundsRect.bottom));
                            break;
                        }
                }
                return;
            }
        }
        boolean intersects = rect.intersect(this.maskBoundsRect);
        if (!intersects) {
            rect.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    private void intersectBoundsWithMatte(RectF rect, Matrix matrix) {
        if (!hasMatteOnThisLayer() || this.layerModel.getMatteType() == Layer.MatteType.INVERT) {
            return;
        }
        this.matteBoundsRect.set(0.0f, 0.0f, 0.0f, 0.0f);
        this.matteLayer.getBounds(this.matteBoundsRect, matrix, true);
        boolean intersects = rect.intersect(this.matteBoundsRect);
        if (!intersects) {
            rect.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    private void applyMasks(Canvas canvas, Matrix matrix) {
        L.beginSection("Layer#saveLayer");
        Utils.saveLayerCompat(canvas, this.rect, this.dstInPaint, 19);
        if (Build.VERSION.SDK_INT < 28) {
            clearCanvas(canvas);
        }
        L.endSection("Layer#saveLayer");
        for (int i = 0; i < this.mask.getMasks().size(); i++) {
            Mask mask = this.mask.getMasks().get(i);
            BaseKeyframeAnimation<ShapeData, Path> maskAnimation = this.mask.getMaskAnimations().get(i);
            BaseKeyframeAnimation<Integer, Integer> opacityAnimation = this.mask.getOpacityAnimations().get(i);
            switch (mask.getMaskMode()) {
                case MASK_MODE_NONE:
                    if (areAllMasksNone()) {
                        this.contentPaint.setAlpha(255);
                        canvas.drawRect(this.rect, this.contentPaint);
                        break;
                    } else {
                        break;
                    }
                case MASK_MODE_SUBTRACT:
                    if (i == 0) {
                        this.contentPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
                        this.contentPaint.setAlpha(255);
                        canvas.drawRect(this.rect, this.contentPaint);
                    }
                    if (mask.isInverted()) {
                        applyInvertedSubtractMask(canvas, matrix, maskAnimation, opacityAnimation);
                        break;
                    } else {
                        applySubtractMask(canvas, matrix, maskAnimation);
                        break;
                    }
                case MASK_MODE_INTERSECT:
                    if (mask.isInverted()) {
                        applyInvertedIntersectMask(canvas, matrix, maskAnimation, opacityAnimation);
                        break;
                    } else {
                        applyIntersectMask(canvas, matrix, maskAnimation, opacityAnimation);
                        break;
                    }
                case MASK_MODE_ADD:
                    if (mask.isInverted()) {
                        applyInvertedAddMask(canvas, matrix, maskAnimation, opacityAnimation);
                        break;
                    } else {
                        applyAddMask(canvas, matrix, maskAnimation, opacityAnimation);
                        break;
                    }
            }
        }
        L.beginSection("Layer#restoreLayer");
        canvas.restore();
        L.endSection("Layer#restoreLayer");
    }

    private boolean areAllMasksNone() {
        if (this.mask.getMaskAnimations().isEmpty()) {
            return false;
        }
        for (int i = 0; i < this.mask.getMasks().size(); i++) {
            if (this.mask.getMasks().get(i).getMaskMode() != Mask.MaskMode.MASK_MODE_NONE) {
                return false;
            }
        }
        return true;
    }

    private void applyAddMask(Canvas canvas, Matrix matrix, BaseKeyframeAnimation<ShapeData, Path> maskAnimation, BaseKeyframeAnimation<Integer, Integer> opacityAnimation) {
        Path maskPath = maskAnimation.getValue();
        this.path.set(maskPath);
        this.path.transform(matrix);
        this.contentPaint.setAlpha((int) (opacityAnimation.getValue().intValue() * 2.55f));
        canvas.drawPath(this.path, this.contentPaint);
    }

    private void applyInvertedAddMask(Canvas canvas, Matrix matrix, BaseKeyframeAnimation<ShapeData, Path> maskAnimation, BaseKeyframeAnimation<Integer, Integer> opacityAnimation) {
        Utils.saveLayerCompat(canvas, this.rect, this.contentPaint);
        canvas.drawRect(this.rect, this.contentPaint);
        Path maskPath = maskAnimation.getValue();
        this.path.set(maskPath);
        this.path.transform(matrix);
        this.contentPaint.setAlpha((int) (opacityAnimation.getValue().intValue() * 2.55f));
        canvas.drawPath(this.path, this.dstOutPaint);
        canvas.restore();
    }

    private void applySubtractMask(Canvas canvas, Matrix matrix, BaseKeyframeAnimation<ShapeData, Path> maskAnimation) {
        Path maskPath = maskAnimation.getValue();
        this.path.set(maskPath);
        this.path.transform(matrix);
        canvas.drawPath(this.path, this.dstOutPaint);
    }

    private void applyInvertedSubtractMask(Canvas canvas, Matrix matrix, BaseKeyframeAnimation<ShapeData, Path> maskAnimation, BaseKeyframeAnimation<Integer, Integer> opacityAnimation) {
        Utils.saveLayerCompat(canvas, this.rect, this.dstOutPaint);
        canvas.drawRect(this.rect, this.contentPaint);
        this.dstOutPaint.setAlpha((int) (opacityAnimation.getValue().intValue() * 2.55f));
        Path maskPath = maskAnimation.getValue();
        this.path.set(maskPath);
        this.path.transform(matrix);
        canvas.drawPath(this.path, this.dstOutPaint);
        canvas.restore();
    }

    private void applyIntersectMask(Canvas canvas, Matrix matrix, BaseKeyframeAnimation<ShapeData, Path> maskAnimation, BaseKeyframeAnimation<Integer, Integer> opacityAnimation) {
        Utils.saveLayerCompat(canvas, this.rect, this.dstInPaint);
        Path maskPath = maskAnimation.getValue();
        this.path.set(maskPath);
        this.path.transform(matrix);
        this.contentPaint.setAlpha((int) (opacityAnimation.getValue().intValue() * 2.55f));
        canvas.drawPath(this.path, this.contentPaint);
        canvas.restore();
    }

    private void applyInvertedIntersectMask(Canvas canvas, Matrix matrix, BaseKeyframeAnimation<ShapeData, Path> maskAnimation, BaseKeyframeAnimation<Integer, Integer> opacityAnimation) {
        Utils.saveLayerCompat(canvas, this.rect, this.dstInPaint);
        canvas.drawRect(this.rect, this.contentPaint);
        this.dstOutPaint.setAlpha((int) (opacityAnimation.getValue().intValue() * 2.55f));
        Path maskPath = maskAnimation.getValue();
        this.path.set(maskPath);
        this.path.transform(matrix);
        canvas.drawPath(this.path, this.dstOutPaint);
        canvas.restore();
    }

    boolean hasMasksOnThisLayer() {
        return (this.mask == null || this.mask.getMaskAnimations().isEmpty()) ? false : true;
    }

    private void setVisible(boolean visible) {
        if (visible != this.visible) {
            this.visible = visible;
            invalidateSelf();
        }
    }

    void setProgress(float progress) {
        L.beginSection("BaseLayer#setProgress");
        L.beginSection("BaseLayer#setProgress.transform");
        this.transform.setProgress(progress);
        L.endSection("BaseLayer#setProgress.transform");
        if (this.mask != null) {
            L.beginSection("BaseLayer#setProgress.mask");
            for (int i = 0; i < this.mask.getMaskAnimations().size(); i++) {
                this.mask.getMaskAnimations().get(i).setProgress(progress);
            }
            L.endSection("BaseLayer#setProgress.mask");
        }
        if (this.inOutAnimation != null) {
            L.beginSection("BaseLayer#setProgress.inout");
            this.inOutAnimation.setProgress(progress);
            L.endSection("BaseLayer#setProgress.inout");
        }
        if (this.matteLayer != null) {
            L.beginSection("BaseLayer#setProgress.matte");
            this.matteLayer.setProgress(progress);
            L.endSection("BaseLayer#setProgress.matte");
        }
        L.beginSection("BaseLayer#setProgress.animations." + this.animations.size());
        for (int i2 = 0; i2 < this.animations.size(); i2++) {
            this.animations.get(i2).setProgress(progress);
        }
        L.endSection("BaseLayer#setProgress.animations." + this.animations.size());
        L.endSection("BaseLayer#setProgress");
    }

    private void buildParentLayerListIfNeeded() {
        if (this.parentLayers != null) {
            return;
        }
        if (this.parentLayer == null) {
            this.parentLayers = Collections.emptyList();
            return;
        }
        this.parentLayers = new ArrayList();
        for (BaseLayer layer = this.parentLayer; layer != null; layer = layer.parentLayer) {
            this.parentLayers.add(layer);
        }
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.layerModel.getName();
    }

    public BlurEffect getBlurEffect() {
        return this.layerModel.getBlurEffect();
    }

    public LBlendMode getBlendMode() {
        return this.layerModel.getBlendMode();
    }

    public BlurMaskFilter getBlurMaskFilter(float radius) {
        if (this.blurMaskFilterRadius == radius) {
            return this.blurMaskFilter;
        }
        this.blurMaskFilter = new BlurMaskFilter(radius / 2.0f, BlurMaskFilter.Blur.NORMAL);
        this.blurMaskFilterRadius = radius;
        return this.blurMaskFilter;
    }

    public DropShadowEffect getDropShadowEffect() {
        return this.layerModel.getDropShadowEffect();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> contentsBefore, List<Content> contentsAfter) {
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath, int depth, List<KeyPath> accumulator, KeyPath currentPartialKeyPath) {
        if (this.matteLayer != null) {
            KeyPath matteCurrentPartialKeyPath = currentPartialKeyPath.addKey(this.matteLayer.getName());
            if (keyPath.fullyResolvesTo(this.matteLayer.getName(), depth)) {
                accumulator.add(matteCurrentPartialKeyPath.resolve(this.matteLayer));
            }
            if (keyPath.propagateToChildren(getName(), depth)) {
                int newDepth = keyPath.incrementDepthBy(this.matteLayer.getName(), depth) + depth;
                this.matteLayer.resolveChildKeyPath(keyPath, newDepth, accumulator, matteCurrentPartialKeyPath);
            }
        }
        if (!keyPath.matches(getName(), depth)) {
            return;
        }
        if (!"__container".equals(getName())) {
            currentPartialKeyPath = currentPartialKeyPath.addKey(getName());
            if (keyPath.fullyResolvesTo(getName(), depth)) {
                accumulator.add(currentPartialKeyPath.resolve(this));
            }
        }
        if (keyPath.propagateToChildren(getName(), depth)) {
            int newDepth2 = keyPath.incrementDepthBy(getName(), depth) + depth;
            resolveChildKeyPath(keyPath, newDepth2, accumulator, currentPartialKeyPath);
        }
    }

    void resolveChildKeyPath(KeyPath keyPath, int depth, List<KeyPath> accumulator, KeyPath currentPartialKeyPath) {
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        this.transform.applyValueCallback(property, callback);
    }
}
