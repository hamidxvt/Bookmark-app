package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.content.RoundedCorners;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* loaded from: classes.dex */
public class RoundedCornersParser {
    private static final JsonReader.Options NAMES = JsonReader.Options.of("nm", "r", "hd");

    private RoundedCornersParser() {
    }

    static RoundedCorners parse(JsonReader reader, LottieComposition composition) throws IOException {
        String name = null;
        AnimatableValue<Float, Float> cornerRadius = null;
        boolean hidden = false;
        while (reader.hasNext()) {
            switch (reader.selectName(NAMES)) {
                case 0:
                    name = reader.nextString();
                    break;
                case 1:
                    cornerRadius = AnimatableValueParser.parseFloat(reader, composition, true);
                    break;
                case 2:
                    hidden = reader.nextBoolean();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        if (hidden) {
            return null;
        }
        return new RoundedCorners(name, cornerRadius);
    }
}
