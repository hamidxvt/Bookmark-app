package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import com.github.gcacace.signaturepad.BuildConfig;
import java.io.IOException;

/* loaded from: classes.dex */
class MaskParser {
    private MaskParser() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0091, code lost:
    
        if (r5.equals("a") != false) goto L42;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static Mask parse(JsonReader reader, LottieComposition composition) throws IOException {
        boolean z;
        Mask.MaskMode maskMode = null;
        AnimatableShapeValue maskPath = null;
        AnimatableIntegerValue opacity = null;
        boolean inverted = false;
        reader.beginObject();
        while (reader.hasNext()) {
            String mode = reader.nextName();
            boolean z2 = false;
            switch (mode.hashCode()) {
                case 111:
                    if (mode.equals("o")) {
                        z = 2;
                        break;
                    }
                    z = -1;
                    break;
                case 3588:
                    if (mode.equals("pt")) {
                        z = true;
                        break;
                    }
                    z = -1;
                    break;
                case 104433:
                    if (mode.equals("inv")) {
                        z = 3;
                        break;
                    }
                    z = -1;
                    break;
                case 3357091:
                    if (mode.equals("mode")) {
                        z = false;
                        break;
                    }
                    z = -1;
                    break;
                default:
                    z = -1;
                    break;
            }
            switch (z) {
                case false:
                    String nextString = reader.nextString();
                    switch (nextString.hashCode()) {
                        case 97:
                            break;
                        case 105:
                            if (nextString.equals("i")) {
                                z2 = 3;
                                break;
                            }
                            z2 = -1;
                            break;
                        case BuildConfig.VERSION_CODE /* 110 */:
                            if (nextString.equals("n")) {
                                z2 = 2;
                                break;
                            }
                            z2 = -1;
                            break;
                        case 115:
                            if (nextString.equals("s")) {
                                z2 = true;
                                break;
                            }
                            z2 = -1;
                            break;
                        default:
                            z2 = -1;
                            break;
                    }
                    switch (z2) {
                        case false:
                            maskMode = Mask.MaskMode.MASK_MODE_ADD;
                            break;
                        case true:
                            maskMode = Mask.MaskMode.MASK_MODE_SUBTRACT;
                            break;
                        case true:
                            maskMode = Mask.MaskMode.MASK_MODE_NONE;
                            break;
                        case true:
                            composition.addWarning("Animation contains intersect masks. They are not supported but will be treated like add masks.");
                            maskMode = Mask.MaskMode.MASK_MODE_INTERSECT;
                            break;
                        default:
                            Logger.warning("Unknown mask mode " + mode + ". Defaulting to Add.");
                            maskMode = Mask.MaskMode.MASK_MODE_ADD;
                            break;
                    }
                case true:
                    maskPath = AnimatableValueParser.parseShapeData(reader, composition);
                    break;
                case true:
                    opacity = AnimatableValueParser.parseInteger(reader, composition);
                    break;
                case true:
                    inverted = reader.nextBoolean();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Mask(maskMode, maskPath, opacity, inverted);
    }
}
