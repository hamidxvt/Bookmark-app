package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.Repeater;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/* loaded from: classes.dex */
public class RepeaterContent implements DrawingContent, PathContent, GreedyContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {
    private ContentGroup contentGroup;
    private final BaseKeyframeAnimation<Float, Float> copies;
    private final boolean hidden;
    private final BaseLayer layer;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<Float, Float> offset;
    private final TransformKeyframeAnimation transform;
    private final Matrix matrix = new Matrix();
    private final Path path = new Path();

    public RepeaterContent(LottieDrawable lottieDrawable, BaseLayer layer, Repeater repeater) {
        this.lottieDrawable = lottieDrawable;
        this.layer = layer;
        this.name = repeater.getName();
        this.hidden = repeater.isHidden();
        this.copies = repeater.getCopies().createAnimation();
        layer.addAnimation(this.copies);
        this.copies.addUpdateListener(this);
        this.offset = repeater.getOffset().createAnimation();
        layer.addAnimation(this.offset);
        this.offset.addUpdateListener(this);
        this.transform = repeater.getTransform().createAnimation();
        this.transform.addAnimationsToLayer(layer);
        this.transform.addListener(this);
    }

    @Override // com.airbnb.lottie.animation.content.GreedyContent
    public void absorbContent(ListIterator<Content> contentsIter) {
        if (this.contentGroup != null) {
            return;
        }
        while (contentsIter.hasPrevious() && contentsIter.previous() != this) {
        }
        List<Content> contents = new ArrayList<>();
        while (contentsIter.hasPrevious()) {
            contents.add(contentsIter.previous());
            contentsIter.remove();
        }
        Collections.reverse(contents);
        this.contentGroup = new ContentGroup(this.lottieDrawable, this.layer, "Repeater", this.hidden, contents, null);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> contentsBefore, List<Content> contentsAfter) {
        this.contentGroup.setContents(contentsBefore, contentsAfter);
    }

    @Override // com.airbnb.lottie.animation.content.PathContent
    public Path getPath() {
        Path contentPath = this.contentGroup.getPath();
        this.path.reset();
        float copies = this.copies.getValue().floatValue();
        float offset = this.offset.getValue().floatValue();
        for (int i = ((int) copies) - 1; i >= 0; i--) {
            this.matrix.set(this.transform.getMatrixForRepeater(i + offset));
            this.path.addPath(contentPath, this.matrix);
        }
        return this.path;
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void draw(Canvas canvas, Matrix parentMatrix, int alpha) {
        float copies = this.copies.getValue().floatValue();
        float offset = this.offset.getValue().floatValue();
        float startOpacity = this.transform.getStartOpacity().getValue().floatValue() / 100.0f;
        float endOpacity = this.transform.getEndOpacity().getValue().floatValue() / 100.0f;
        for (int i = ((int) copies) - 1; i >= 0; i--) {
            this.matrix.set(parentMatrix);
            this.matrix.preConcat(this.transform.getMatrixForRepeater(i + offset));
            float newAlpha = alpha * MiscUtils.lerp(startOpacity, endOpacity, i / copies);
            this.contentGroup.draw(canvas, this.matrix, (int) newAlpha);
        }
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF outBounds, Matrix parentMatrix, boolean applyParents) {
        this.contentGroup.getBounds(outBounds, parentMatrix, applyParents);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath, int depth, List<KeyPath> accumulator, KeyPath currentPartialKeyPath) {
        MiscUtils.resolveKeyPath(keyPath, depth, accumulator, currentPartialKeyPath, this);
        for (int i = 0; i < this.contentGroup.getContents().size(); i++) {
            Content content = this.contentGroup.getContents().get(i);
            if (content instanceof KeyPathElementContent) {
                MiscUtils.resolveKeyPath(keyPath, depth, accumulator, currentPartialKeyPath, (KeyPathElementContent) content);
            }
        }
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        if (this.transform.applyValueCallback(property, callback)) {
            return;
        }
        if (property == LottieProperty.REPEATER_COPIES) {
            this.copies.setValueCallback(callback);
        } else if (property == LottieProperty.REPEATER_OFFSET) {
            this.offset.setValueCallback(callback);
        }
    }
}
