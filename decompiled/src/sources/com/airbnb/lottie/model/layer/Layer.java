package com.airbnb.lottie.model.layer;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.content.LBlendMode;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.parser.DropShadowEffect;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes.dex */
public class Layer {
    private final LBlendMode blendMode;
    private final BlurEffect blurEffect;
    private final LottieComposition composition;
    private final DropShadowEffect dropShadowEffect;
    private final boolean hidden;
    private final List<Keyframe<Float>> inOutKeyframes;
    private final long layerId;
    private final String layerName;
    private final LayerType layerType;
    private final List<Mask> masks;
    private final MatteType matteType;
    private final long parentId;
    private final float preCompHeight;
    private final float preCompWidth;
    private final String refId;
    private final List<ContentModel> shapes;
    private final int solidColor;
    private final int solidHeight;
    private final int solidWidth;
    private final float startFrame;
    private final AnimatableTextFrame text;
    private final AnimatableTextProperties textProperties;
    private final AnimatableFloatValue timeRemapping;
    private final float timeStretch;
    private final AnimatableTransform transform;

    public enum LayerType {
        PRE_COMP,
        SOLID,
        IMAGE,
        NULL,
        SHAPE,
        TEXT,
        UNKNOWN
    }

    public enum MatteType {
        NONE,
        ADD,
        INVERT,
        LUMA,
        LUMA_INVERTED,
        UNKNOWN
    }

    public Layer(List<ContentModel> shapes, LottieComposition composition, String layerName, long layerId, LayerType layerType, long parentId, String refId, List<Mask> masks, AnimatableTransform transform, int solidWidth, int solidHeight, int solidColor, float timeStretch, float startFrame, float preCompWidth, float preCompHeight, AnimatableTextFrame text, AnimatableTextProperties textProperties, List<Keyframe<Float>> inOutKeyframes, MatteType matteType, AnimatableFloatValue timeRemapping, boolean hidden, BlurEffect blurEffect, DropShadowEffect dropShadowEffect, LBlendMode blendMode) {
        this.shapes = shapes;
        this.composition = composition;
        this.layerName = layerName;
        this.layerId = layerId;
        this.layerType = layerType;
        this.parentId = parentId;
        this.refId = refId;
        this.masks = masks;
        this.transform = transform;
        this.solidWidth = solidWidth;
        this.solidHeight = solidHeight;
        this.solidColor = solidColor;
        this.timeStretch = timeStretch;
        this.startFrame = startFrame;
        this.preCompWidth = preCompWidth;
        this.preCompHeight = preCompHeight;
        this.text = text;
        this.textProperties = textProperties;
        this.inOutKeyframes = inOutKeyframes;
        this.matteType = matteType;
        this.timeRemapping = timeRemapping;
        this.hidden = hidden;
        this.blurEffect = blurEffect;
        this.dropShadowEffect = dropShadowEffect;
        this.blendMode = blendMode;
    }

    LottieComposition getComposition() {
        return this.composition;
    }

    float getTimeStretch() {
        return this.timeStretch;
    }

    float getStartProgress() {
        return this.startFrame / this.composition.getDurationFrames();
    }

    List<Keyframe<Float>> getInOutKeyframes() {
        return this.inOutKeyframes;
    }

    public long getId() {
        return this.layerId;
    }

    public String getName() {
        return this.layerName;
    }

    public String getRefId() {
        return this.refId;
    }

    float getPreCompWidth() {
        return this.preCompWidth;
    }

    float getPreCompHeight() {
        return this.preCompHeight;
    }

    List<Mask> getMasks() {
        return this.masks;
    }

    public LayerType getLayerType() {
        return this.layerType;
    }

    MatteType getMatteType() {
        return this.matteType;
    }

    long getParentId() {
        return this.parentId;
    }

    List<ContentModel> getShapes() {
        return this.shapes;
    }

    AnimatableTransform getTransform() {
        return this.transform;
    }

    int getSolidColor() {
        return this.solidColor;
    }

    int getSolidHeight() {
        return this.solidHeight;
    }

    int getSolidWidth() {
        return this.solidWidth;
    }

    AnimatableTextFrame getText() {
        return this.text;
    }

    AnimatableTextProperties getTextProperties() {
        return this.textProperties;
    }

    AnimatableFloatValue getTimeRemapping() {
        return this.timeRemapping;
    }

    public String toString() {
        return toString("");
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public LBlendMode getBlendMode() {
        return this.blendMode;
    }

    public BlurEffect getBlurEffect() {
        return this.blurEffect;
    }

    public DropShadowEffect getDropShadowEffect() {
        return this.dropShadowEffect;
    }

    public String toString(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append(getName()).append(StringUtils.LF);
        Layer parent = this.composition.layerModelForId(getParentId());
        if (parent != null) {
            sb.append("\t\tParents: ").append(parent.getName());
            Layer parent2 = this.composition.layerModelForId(parent.getParentId());
            while (parent2 != null) {
                sb.append("->").append(parent2.getName());
                parent2 = this.composition.layerModelForId(parent2.getParentId());
            }
            sb.append(prefix).append(StringUtils.LF);
        }
        if (!getMasks().isEmpty()) {
            sb.append(prefix).append("\tMasks: ").append(getMasks().size()).append(StringUtils.LF);
        }
        if (getSolidWidth() != 0 && getSolidHeight() != 0) {
            sb.append(prefix).append("\tBackground: ").append(String.format(Locale.US, "%dx%d %X\n", Integer.valueOf(getSolidWidth()), Integer.valueOf(getSolidHeight()), Integer.valueOf(getSolidColor())));
        }
        if (!this.shapes.isEmpty()) {
            sb.append(prefix).append("\tShapes:\n");
            for (Object shape : this.shapes) {
                sb.append(prefix).append("\t\t").append(shape).append(StringUtils.LF);
            }
        }
        return sb.toString();
    }
}
