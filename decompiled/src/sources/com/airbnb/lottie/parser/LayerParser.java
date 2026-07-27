package com.airbnb.lottie.parser;

import android.graphics.Color;
import android.graphics.Rect;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.content.LBlendMode;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class LayerParser {
    private static final JsonReader.Options NAMES = JsonReader.Options.of("nm", "ind", "refId", "ty", "parent", "sw", "sh", "sc", "ks", "tt", "masksProperties", "shapes", "t", "ef", "sr", "st", "w", "h", "ip", "op", "tm", "cl", "hd", "ao", "bm");
    private static final JsonReader.Options TEXT_NAMES = JsonReader.Options.of("d", "a");
    private static final JsonReader.Options EFFECTS_NAMES = JsonReader.Options.of("ty", "nm");

    private LayerParser() {
    }

    public static Layer parse(LottieComposition composition) {
        Rect bounds = composition.getBounds();
        return new Layer(Collections.emptyList(), composition, "__container", -1L, Layer.LayerType.PRE_COMP, -1L, null, Collections.emptyList(), new AnimatableTransform(), 0, 0, 0, 0.0f, 0.0f, bounds.width(), bounds.height(), null, null, Collections.emptyList(), Layer.MatteType.NONE, null, false, null, null, LBlendMode.NORMAL);
    }

    public static Layer parse(JsonReader reader, LottieComposition composition) throws IOException {
        AnimatableTransform transform;
        List<Mask> masks;
        List<ContentModel> shapes;
        List<ContentModel> shapes2;
        float outFrame = 0.0f;
        Layer.MatteType matteType = Layer.MatteType.NONE;
        LBlendMode blendMode = LBlendMode.NORMAL;
        AnimatableTransform transform2 = null;
        List<Mask> masks2 = new ArrayList<>();
        List<ContentModel> shapes3 = new ArrayList<>();
        reader.beginObject();
        Layer.LayerType layerType = null;
        String refId = null;
        long layerId = 0;
        int solidWidth = 0;
        int solidHeight = 0;
        int solidColor = 0;
        float preCompWidth = 0.0f;
        float preCompHeight = 0.0f;
        long parentId = -1;
        float timeStretch = 1.0f;
        float startFrame = 0.0f;
        float inFrame = 0.0f;
        String cl = null;
        boolean hidden = false;
        BlurEffect blurEffect = null;
        DropShadowEffect dropShadowEffect = null;
        boolean autoOrient = false;
        Layer.MatteType matteType2 = matteType;
        LBlendMode blendMode2 = blendMode;
        AnimatableTextFrame text = null;
        AnimatableTextProperties textProperties = null;
        AnimatableFloatValue timeRemapping = null;
        String layerName = "UNSET";
        while (reader.hasNext()) {
            switch (reader.selectName(NAMES)) {
                case 0:
                    layerName = reader.nextString();
                    continue;
                case 1:
                    long layerId2 = reader.nextInt();
                    layerId = layerId2;
                    continue;
                case 2:
                    refId = reader.nextString();
                    continue;
                case 3:
                    List<Mask> masks3 = masks2;
                    List<ContentModel> shapes4 = shapes3;
                    int layerTypeInt = reader.nextInt();
                    if (layerTypeInt < Layer.LayerType.UNKNOWN.ordinal()) {
                        layerType = Layer.LayerType.values()[layerTypeInt];
                        masks2 = masks3;
                        shapes3 = shapes4;
                        break;
                    } else {
                        layerType = Layer.LayerType.UNKNOWN;
                        masks2 = masks3;
                        shapes3 = shapes4;
                        continue;
                    }
                case 4:
                    long parentId2 = reader.nextInt();
                    parentId = parentId2;
                    continue;
                case 5:
                    int solidWidth2 = (int) (reader.nextInt() * Utils.dpScale());
                    solidWidth = solidWidth2;
                    continue;
                case 6:
                    int solidHeight2 = (int) (reader.nextInt() * Utils.dpScale());
                    solidHeight = solidHeight2;
                    continue;
                case 7:
                    solidColor = Color.parseColor(reader.nextString());
                    continue;
                case 8:
                    transform2 = AnimatableTransformParser.parse(reader, composition);
                    continue;
                case 9:
                    masks = masks2;
                    shapes = shapes3;
                    int matteTypeIndex = reader.nextInt();
                    if (matteTypeIndex >= Layer.MatteType.values().length) {
                        composition.addWarning("Unsupported matte type: " + matteTypeIndex);
                        break;
                    } else {
                        matteType2 = Layer.MatteType.values()[matteTypeIndex];
                        switch (matteType2) {
                            case LUMA:
                                composition.addWarning("Unsupported matte type: Luma");
                                break;
                            case LUMA_INVERTED:
                                composition.addWarning("Unsupported matte type: Luma Inverted");
                                break;
                        }
                        composition.incrementMatteOrMaskCount(1);
                        masks2 = masks;
                        shapes3 = shapes;
                        break;
                    }
                case 10:
                    shapes = shapes3;
                    reader.beginArray();
                    while (reader.hasNext()) {
                        masks2.add(MaskParser.parse(reader, composition));
                    }
                    masks = masks2;
                    composition.incrementMatteOrMaskCount(masks.size());
                    reader.endArray();
                    break;
                case 11:
                    reader.beginArray();
                    while (reader.hasNext()) {
                        ContentModel shape = ContentModelParser.parse(reader, composition);
                        if (shape == null) {
                            shapes2 = shapes3;
                        } else {
                            shapes2 = shapes3;
                            shapes2.add(shape);
                        }
                        shapes3 = shapes2;
                    }
                    shapes = shapes3;
                    reader.endArray();
                    masks = masks2;
                    break;
                case 12:
                    reader.beginObject();
                    while (reader.hasNext()) {
                        switch (reader.selectName(TEXT_NAMES)) {
                            case 0:
                                text = AnimatableValueParser.parseDocumentData(reader, composition);
                                break;
                            case 1:
                                reader.beginArray();
                                if (reader.hasNext()) {
                                    textProperties = AnimatableTextPropertiesParser.parse(reader, composition);
                                }
                                while (reader.hasNext()) {
                                    reader.skipValue();
                                }
                                reader.endArray();
                                break;
                            default:
                                reader.skipName();
                                reader.skipValue();
                                break;
                        }
                    }
                    reader.endObject();
                    continue;
                case 13:
                    reader.beginArray();
                    List<String> effectNames = new ArrayList<>();
                    while (reader.hasNext()) {
                        reader.beginObject();
                        while (reader.hasNext()) {
                            switch (reader.selectName(EFFECTS_NAMES)) {
                                case 0:
                                    int type = reader.nextInt();
                                    if (type == 29) {
                                        blurEffect = BlurEffectParser.parse(reader, composition);
                                        break;
                                    } else if (type != 25) {
                                        break;
                                    } else {
                                        dropShadowEffect = new DropShadowEffectParser().parse(reader, composition);
                                        break;
                                    }
                                case 1:
                                    String effectName = reader.nextString();
                                    effectNames.add(effectName);
                                    break;
                                default:
                                    reader.skipName();
                                    reader.skipValue();
                                    break;
                            }
                        }
                        reader.endObject();
                    }
                    reader.endArray();
                    composition.addWarning("Lottie doesn't support layer effects. If you are using them for  fills, strokes, trim paths etc. then try adding them directly as contents  in your shape. Found: " + effectNames);
                    continue;
                case 14:
                    float timeStretch2 = (float) reader.nextDouble();
                    timeStretch = timeStretch2;
                    continue;
                case 15:
                    float startFrame2 = (float) reader.nextDouble();
                    startFrame = startFrame2;
                    continue;
                case 16:
                    float preCompWidth2 = (float) (reader.nextDouble() * Utils.dpScale());
                    preCompWidth = preCompWidth2;
                    continue;
                case 17:
                    float preCompHeight2 = (float) (reader.nextDouble() * Utils.dpScale());
                    preCompHeight = preCompHeight2;
                    continue;
                case 18:
                    float inFrame2 = (float) reader.nextDouble();
                    inFrame = inFrame2;
                    continue;
                case 19:
                    float outFrame2 = (float) reader.nextDouble();
                    outFrame = outFrame2;
                    continue;
                case 20:
                    timeRemapping = AnimatableValueParser.parseFloat(reader, composition, false);
                    continue;
                case 21:
                    cl = reader.nextString();
                    continue;
                case 22:
                    hidden = reader.nextBoolean();
                    continue;
                case 23:
                    autoOrient = reader.nextInt() == 1;
                    continue;
                case 24:
                    int blendModeIndex = reader.nextInt();
                    if (blendModeIndex >= LBlendMode.values().length) {
                        composition.addWarning("Unsupported Blend Mode: " + blendModeIndex);
                        blendMode2 = LBlendMode.NORMAL;
                        break;
                    } else {
                        blendMode2 = LBlendMode.values()[blendModeIndex];
                        continue;
                    }
                default:
                    masks = masks2;
                    shapes = shapes3;
                    reader.skipName();
                    reader.skipValue();
                    break;
            }
            masks2 = masks;
            shapes3 = shapes;
        }
        List<Mask> masks4 = masks2;
        List<ContentModel> shapes5 = shapes3;
        reader.endObject();
        List<Keyframe<Float>> inOutKeyframes = new ArrayList<>();
        if (inFrame > 0.0f) {
            Keyframe<Float> preKeyframe = new Keyframe<>(composition, Float.valueOf(0.0f), Float.valueOf(0.0f), null, 0.0f, Float.valueOf(inFrame));
            inOutKeyframes.add(preKeyframe);
        }
        float outFrame3 = outFrame > 0.0f ? outFrame : composition.getEndFrame();
        Keyframe<Float> visibleKeyframe = new Keyframe<>(composition, Float.valueOf(1.0f), Float.valueOf(1.0f), null, inFrame, Float.valueOf(outFrame3));
        inOutKeyframes.add(visibleKeyframe);
        boolean autoOrient2 = autoOrient;
        String cl2 = cl;
        String layerName2 = layerName;
        Keyframe<Float> outKeyframe = new Keyframe<>(composition, Float.valueOf(0.0f), Float.valueOf(0.0f), null, outFrame3, Float.valueOf(Float.MAX_VALUE));
        inOutKeyframes.add(outKeyframe);
        if (layerName2.endsWith(".ai") || "ai".equals(cl2)) {
            composition.addWarning("Convert your Illustrator layers to shape layers.");
        }
        if (!autoOrient2) {
            transform = transform2;
        } else {
            if (transform2 != null) {
                transform = transform2;
            } else {
                transform = new AnimatableTransform();
            }
            transform.setAutoOrient(autoOrient2);
        }
        return new Layer(shapes5, composition, layerName2, layerId, layerType, parentId, refId, masks4, transform, solidWidth, solidHeight, solidColor, timeStretch, startFrame, preCompWidth, preCompHeight, text, textProperties, inOutKeyframes, matteType2, timeRemapping, hidden, blurEffect, dropShadowEffect, blendMode2);
    }
}
