package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* loaded from: classes.dex */
public class DropShadowEffectParser {
    private static final JsonReader.Options DROP_SHADOW_EFFECT_NAMES = JsonReader.Options.of("ef");
    private static final JsonReader.Options INNER_EFFECT_NAMES = JsonReader.Options.of("nm", "v");
    private AnimatableColorValue color;
    private AnimatableFloatValue direction;
    private AnimatableFloatValue distance;
    private AnimatableFloatValue opacity;
    private AnimatableFloatValue radius;

    DropShadowEffect parse(JsonReader reader, LottieComposition composition) throws IOException {
        while (reader.hasNext()) {
            switch (reader.selectName(DROP_SHADOW_EFFECT_NAMES)) {
                case 0:
                    reader.beginArray();
                    while (reader.hasNext()) {
                        maybeParseInnerEffect(reader, composition);
                    }
                    reader.endArray();
                    break;
                default:
                    reader.skipName();
                    reader.skipValue();
                    break;
            }
        }
        if (this.color != null && this.opacity != null && this.direction != null && this.distance != null && this.radius != null) {
            return new DropShadowEffect(this.color, this.opacity, this.direction, this.distance, this.radius);
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void maybeParseInnerEffect(JsonReader reader, LottieComposition composition) throws IOException {
        char c;
        String currentEffectName = "";
        reader.beginObject();
        while (reader.hasNext()) {
            switch (reader.selectName(INNER_EFFECT_NAMES)) {
                case 0:
                    currentEffectName = reader.nextString();
                    break;
                case 1:
                    switch (currentEffectName.hashCode()) {
                        case 353103893:
                            if (currentEffectName.equals("Distance")) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case 397447147:
                            if (currentEffectName.equals("Opacity")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1041377119:
                            if (currentEffectName.equals("Direction")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1379387491:
                            if (currentEffectName.equals("Shadow Color")) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1383710113:
                            if (currentEffectName.equals("Softness")) {
                                c = 4;
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
                            this.color = AnimatableValueParser.parseColor(reader, composition);
                            break;
                        case 1:
                            this.opacity = AnimatableValueParser.parseFloat(reader, composition, false);
                            break;
                        case 2:
                            this.direction = AnimatableValueParser.parseFloat(reader, composition, false);
                            break;
                        case 3:
                            this.distance = AnimatableValueParser.parseFloat(reader, composition);
                            break;
                        case 4:
                            this.radius = AnimatableValueParser.parseFloat(reader, composition);
                            break;
                        default:
                            reader.skipValue();
                            break;
                    }
                default:
                    reader.skipName();
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
    }
}
