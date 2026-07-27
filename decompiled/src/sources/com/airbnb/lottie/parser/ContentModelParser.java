package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import java.io.IOException;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes.dex */
class ContentModelParser {
    private static final JsonReader.Options NAMES = JsonReader.Options.of("ty", "d");

    private ContentModelParser() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    static ContentModel parse(JsonReader reader, LottieComposition composition) throws IOException {
        char c;
        String type = null;
        reader.beginObject();
        int d = 2;
        while (true) {
            if (reader.hasNext()) {
                switch (reader.selectName(NAMES)) {
                    case 0:
                        type = reader.nextString();
                        break;
                    case 1:
                        d = reader.nextInt();
                        continue;
                    default:
                        reader.skipName();
                        reader.skipValue();
                        continue;
                }
            }
        }
        if (type == null) {
            return null;
        }
        ContentModel model = null;
        switch (type.hashCode()) {
            case 3239:
                if (type.equals("el")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 3270:
                if (type.equals("fl")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 3295:
                if (type.equals("gf")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 3307:
                if (type.equals("gr")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3308:
                if (type.equals("gs")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 3488:
                if (type.equals("mm")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 3633:
                if (type.equals("rc")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 3634:
                if (type.equals("rd")) {
                    c = CharUtils.CR;
                    break;
                }
                c = 65535;
                break;
            case 3646:
                if (type.equals("rp")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 3669:
                if (type.equals("sh")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 3679:
                if (type.equals("sr")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 3681:
                if (type.equals("st")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3705:
                if (type.equals("tm")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 3710:
                if (type.equals("tr")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                model = ShapeGroupParser.parse(reader, composition);
                break;
            case 1:
                model = ShapeStrokeParser.parse(reader, composition);
                break;
            case 2:
                model = GradientStrokeParser.parse(reader, composition);
                break;
            case 3:
                model = ShapeFillParser.parse(reader, composition);
                break;
            case 4:
                model = GradientFillParser.parse(reader, composition);
                break;
            case 5:
                model = AnimatableTransformParser.parse(reader, composition);
                break;
            case 6:
                model = ShapePathParser.parse(reader, composition);
                break;
            case 7:
                model = CircleShapeParser.parse(reader, composition, d);
                break;
            case '\b':
                model = RectangleShapeParser.parse(reader, composition);
                break;
            case '\t':
                model = ShapeTrimPathParser.parse(reader, composition);
                break;
            case '\n':
                model = PolystarShapeParser.parse(reader, composition, d);
                break;
            case 11:
                model = MergePathsParser.parse(reader);
                composition.addWarning("Animation contains merge paths. Merge paths are only supported on KitKat+ and must be manually enabled by calling enableMergePathsForKitKatAndAbove().");
                break;
            case '\f':
                model = RepeaterParser.parse(reader, composition);
                break;
            case '\r':
                model = RoundedCornersParser.parse(reader, composition);
                break;
            default:
                Logger.warning("Unknown shape type " + type);
                break;
        }
        while (reader.hasNext()) {
            reader.skipValue();
        }
        reader.endObject();
        return model;
    }
}
