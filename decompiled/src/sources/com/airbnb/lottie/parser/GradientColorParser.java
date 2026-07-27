package com.airbnb.lottie.parser;

import android.graphics.Color;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.GammaEvaluator;
import com.airbnb.lottie.utils.MiscUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class GradientColorParser implements ValueParser<GradientColor> {
    private int colorPoints;

    public GradientColorParser(int colorPoints) {
        this.colorPoints = colorPoints;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.airbnb.lottie.parser.ValueParser
    public GradientColor parse(JsonReader reader, float scale) throws IOException {
        List<Float> array = new ArrayList<>();
        boolean isArray = reader.peek() == JsonReader.Token.BEGIN_ARRAY;
        if (isArray) {
            reader.beginArray();
        }
        while (reader.hasNext()) {
            array.add(Float.valueOf((float) reader.nextDouble()));
        }
        if (array.size() == 4 && array.get(0).floatValue() == 1.0f) {
            array.set(0, Float.valueOf(0.0f));
            array.add(Float.valueOf(1.0f));
            array.add(array.get(1));
            array.add(array.get(2));
            array.add(array.get(3));
            this.colorPoints = 2;
        }
        if (isArray) {
            reader.endArray();
        }
        if (this.colorPoints == -1) {
            this.colorPoints = array.size() / 4;
        }
        float[] positions = new float[this.colorPoints];
        int[] colors = new int[this.colorPoints];
        int r = 0;
        int g = 0;
        for (int i = 0; i < this.colorPoints * 4; i++) {
            int colorIndex = i / 4;
            double value = array.get(i).floatValue();
            switch (i % 4) {
                case 0:
                    if (colorIndex > 0 && positions[colorIndex - 1] >= ((float) value)) {
                        positions[colorIndex] = ((float) value) + 0.01f;
                        break;
                    } else {
                        positions[colorIndex] = (float) value;
                        break;
                    }
                case 1:
                    r = (int) (255.0d * value);
                    break;
                case 2:
                    g = (int) (255.0d * value);
                    break;
                case 3:
                    int b = (int) (255.0d * value);
                    colors[colorIndex] = Color.argb(255, r, g, b);
                    break;
            }
        }
        GradientColor gradientColor = new GradientColor(positions, colors);
        return addOpacityStopsToGradientIfNeeded(gradientColor, array);
    }

    private GradientColor addOpacityStopsToGradientIfNeeded(GradientColor gradientColor, List<Float> array) {
        int startIndex = this.colorPoints * 4;
        if (array.size() <= startIndex) {
            return gradientColor;
        }
        float[] colorStopPositions = gradientColor.getPositions();
        int[] colorStopColors = gradientColor.getColors();
        int opacityStops = (array.size() - startIndex) / 2;
        float[] opacityStopPositions = new float[opacityStops];
        float[] opacityStopOpacities = new float[opacityStops];
        int j = 0;
        for (int i = startIndex; i < array.size(); i++) {
            if (i % 2 == 0) {
                opacityStopPositions[j] = array.get(i).floatValue();
            } else {
                opacityStopOpacities[j] = array.get(i).floatValue();
                j++;
            }
        }
        float[] newPositions = mergeUniqueElements(gradientColor.getPositions(), opacityStopPositions);
        int newColorPoints = newPositions.length;
        int[] newColors = new int[newColorPoints];
        for (int i2 = 0; i2 < newColorPoints; i2++) {
            float position = newPositions[i2];
            int colorStopIndex = Arrays.binarySearch(colorStopPositions, position);
            int opacityIndex = Arrays.binarySearch(opacityStopPositions, position);
            if (colorStopIndex >= 0 && opacityIndex <= 0) {
                newColors[i2] = getColorInBetweenOpacityStops(position, colorStopColors[colorStopIndex], opacityStopPositions, opacityStopOpacities);
            } else {
                if (opacityIndex < 0) {
                    opacityIndex = -(opacityIndex + 1);
                }
                newColors[i2] = getColorInBetweenColorStops(position, opacityStopOpacities[opacityIndex], colorStopPositions, colorStopColors);
            }
        }
        return new GradientColor(newPositions, newColors);
    }

    int getColorInBetweenColorStops(float position, float opacity, float[] colorStopPositions, int[] colorStopColors) {
        if (colorStopColors.length < 2 || position == colorStopPositions[0]) {
            return colorStopColors[0];
        }
        for (int i = 1; i < colorStopPositions.length; i++) {
            float colorStopPosition = colorStopPositions[i];
            if (colorStopPosition >= position || i == colorStopPositions.length - 1) {
                if (i != colorStopPositions.length - 1 || position < colorStopPosition) {
                    float distanceBetweenColors = colorStopPositions[i] - colorStopPositions[i - 1];
                    float distanceToLowerColor = position - colorStopPositions[i - 1];
                    float percentage = distanceToLowerColor / distanceBetweenColors;
                    int upperColor = colorStopColors[i];
                    int lowerColor = colorStopColors[i - 1];
                    int intermediateColor = GammaEvaluator.evaluate(percentage, lowerColor, upperColor);
                    int a = (int) (255.0f * opacity);
                    int r = Color.red(intermediateColor);
                    int g = Color.green(intermediateColor);
                    int b = Color.blue(intermediateColor);
                    return Color.argb(a, r, g, b);
                }
                return Color.argb((int) (opacity * 255.0f), Color.red(colorStopColors[i]), Color.green(colorStopColors[i]), Color.blue(colorStopColors[i]));
            }
        }
        throw new IllegalArgumentException("Unreachable code.");
    }

    private int getColorInBetweenOpacityStops(float position, int color, float[] opacityStopPositions, float[] opacityStopOpacities) {
        int a;
        if (opacityStopOpacities.length < 2 || position <= opacityStopPositions[0]) {
            int a2 = (int) (opacityStopOpacities[0] * 255.0f);
            int r = Color.red(color);
            int g = Color.green(color);
            int b = Color.blue(color);
            return Color.argb(a2, r, g, b);
        }
        for (int i = 1; i < opacityStopPositions.length; i++) {
            float opacityStopPosition = opacityStopPositions[i];
            if (opacityStopPosition >= position || i == opacityStopPositions.length - 1) {
                if (opacityStopPosition <= position) {
                    a = (int) (opacityStopOpacities[i] * 255.0f);
                } else {
                    float distanceBetweenOpacities = opacityStopPositions[i] - opacityStopPositions[i - 1];
                    float distanceToLowerOpacity = position - opacityStopPositions[i - 1];
                    float percentage = distanceToLowerOpacity / distanceBetweenOpacities;
                    a = (int) (MiscUtils.lerp(opacityStopOpacities[i - 1], opacityStopOpacities[i], percentage) * 255.0f);
                }
                int r2 = Color.red(color);
                int g2 = Color.green(color);
                int b2 = Color.blue(color);
                return Color.argb(a, r2, g2, b2);
            }
        }
        throw new IllegalArgumentException("Unreachable code.");
    }

    protected static float[] mergeUniqueElements(float[] arrayA, float[] arrayB) {
        if (arrayA.length == 0) {
            return arrayB;
        }
        if (arrayB.length == 0) {
            return arrayA;
        }
        int aIndex = 0;
        int bIndex = 0;
        int numDuplicates = 0;
        float[] mergedNotTruncated = new float[arrayA.length + arrayB.length];
        for (int i = 0; i < mergedNotTruncated.length; i++) {
            float a = aIndex < arrayA.length ? arrayA[aIndex] : Float.NaN;
            float b = bIndex < arrayB.length ? arrayB[bIndex] : Float.NaN;
            if (Float.isNaN(b) || a < b) {
                mergedNotTruncated[i] = a;
                aIndex++;
            } else if (Float.isNaN(a) || b < a) {
                mergedNotTruncated[i] = b;
                bIndex++;
            } else {
                mergedNotTruncated[i] = a;
                aIndex++;
                bIndex++;
                numDuplicates++;
            }
        }
        if (numDuplicates == 0) {
            return mergedNotTruncated;
        }
        return Arrays.copyOf(mergedNotTruncated, mergedNotTruncated.length - numDuplicates);
    }
}
