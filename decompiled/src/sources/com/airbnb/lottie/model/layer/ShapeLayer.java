package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.ContentGroup;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.parser.DropShadowEffect;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class ShapeLayer extends BaseLayer {
    private final CompositionLayer compositionLayer;
    private final ContentGroup contentGroup;

    ShapeLayer(LottieDrawable lottieDrawable, Layer layerModel, CompositionLayer compositionLayer, LottieComposition composition) {
        super(lottieDrawable, layerModel);
        this.compositionLayer = compositionLayer;
        ShapeGroup shapeGroup = new ShapeGroup("__container", layerModel.getShapes(), false);
        this.contentGroup = new ContentGroup(lottieDrawable, this, shapeGroup, composition);
        this.contentGroup.setContents(Collections.emptyList(), Collections.emptyList());
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    void drawLayer(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        this.contentGroup.draw(canvas, parentMatrix, parentAlpha);
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF outBounds, Matrix parentMatrix, boolean applyParents) {
        super.getBounds(outBounds, parentMatrix, applyParents);
        this.contentGroup.getBounds(outBounds, this.boundsMatrix, applyParents);
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public BlurEffect getBlurEffect() {
        BlurEffect layerBlur = super.getBlurEffect();
        if (layerBlur != null) {
            return layerBlur;
        }
        return this.compositionLayer.getBlurEffect();
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public DropShadowEffect getDropShadowEffect() {
        DropShadowEffect layerDropShadow = super.getDropShadowEffect();
        if (layerDropShadow != null) {
            return layerDropShadow;
        }
        return this.compositionLayer.getDropShadowEffect();
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    protected void resolveChildKeyPath(KeyPath keyPath, int depth, List<KeyPath> accumulator, KeyPath currentPartialKeyPath) {
        this.contentGroup.resolveKeyPath(keyPath, depth, accumulator, currentPartialKeyPath);
    }
}
