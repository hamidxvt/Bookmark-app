package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.content.ShapeStroke;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
class ShapeStrokeParser {
    private static final JsonReader.Options NAMES = JsonReader.Options.of("nm", "c", "w", "o", "lc", "lj", "ml", "hd", "d");
    private static final JsonReader.Options DASH_PATTERN_NAMES = JsonReader.Options.of("n", "v");

    private ShapeStrokeParser() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0066, code lost:
    
        if (r11.equals("o") != false) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static ShapeStroke parse(JsonReader reader, LottieComposition composition) throws IOException {
        String name = null;
        AnimatableColorValue color = null;
        AnimatableFloatValue width = null;
        AnimatableIntegerValue opacity = null;
        ShapeStroke.LineCapType capType = null;
        ShapeStroke.LineJoinType joinType = null;
        AnimatableFloatValue offset = null;
        float miterLimit = 0.0f;
        boolean hidden = false;
        List<AnimatableFloatValue> lineDashPattern = new ArrayList<>();
        while (reader.hasNext()) {
            switch (reader.selectName(NAMES)) {
                case 0:
                    name = reader.nextString();
                    break;
                case 1:
                    color = AnimatableValueParser.parseColor(reader, composition);
                    break;
                case 2:
                    width = AnimatableValueParser.parseFloat(reader, composition);
                    break;
                case 3:
                    opacity = AnimatableValueParser.parseInteger(reader, composition);
                    break;
                case 4:
                    capType = ShapeStroke.LineCapType.values()[reader.nextInt() - 1];
                    break;
                case 5:
                    joinType = ShapeStroke.LineJoinType.values()[reader.nextInt() - 1];
                    break;
                case 6:
                    miterLimit = (float) reader.nextDouble();
                    break;
                case 7:
                    hidden = reader.nextBoolean();
                    break;
                case 8:
                    reader.beginArray();
                    while (true) {
                        char c = 0;
                        if (reader.hasNext()) {
                            String n = null;
                            AnimatableFloatValue val = null;
                            reader.beginObject();
                            while (reader.hasNext()) {
                                switch (reader.selectName(DASH_PATTERN_NAMES)) {
                                    case 0:
                                        n = reader.nextString();
                                        break;
                                    case 1:
                                        val = AnimatableValueParser.parseFloat(reader, composition);
                                        break;
                                    default:
                                        reader.skipName();
                                        reader.skipValue();
                                        break;
                                }
                            }
                            reader.endObject();
                            switch (n.hashCode()) {
                                case 100:
                                    if (n.equals("d")) {
                                        c = 1;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 103:
                                    if (n.equals("g")) {
                                        c = 2;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 111:
                                    break;
                                default:
                                    c = 65535;
                                    break;
                            }
                            switch (c) {
                                case 0:
                                    offset = val;
                                    break;
                                case 1:
                                case 2:
                                    composition.setHasDashPattern(true);
                                    lineDashPattern.add(val);
                                    break;
                            }
                        } else {
                            reader.endArray();
                            if (lineDashPattern.size() != 1) {
                                break;
                            } else {
                                lineDashPattern.add(lineDashPattern.get(0));
                                break;
                            }
                        }
                    }
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        return new ShapeStroke(name, offset, lineDashPattern, color, opacity == null ? new AnimatableIntegerValue(Collections.singletonList(new Keyframe(100))) : opacity, width, capType == null ? ShapeStroke.LineCapType.BUTT : capType, joinType == null ? ShapeStroke.LineJoinType.MITER : joinType, miterLimit, hidden);
    }
}
