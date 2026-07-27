package com.airbnb.lottie.model;

import com.airbnb.lottie.model.content.ShapeGroup;
import java.util.List;

/* loaded from: classes.dex */
public class FontCharacter {
    private final char character;
    private final String fontFamily;
    private final List<ShapeGroup> shapes;
    private final double size;
    private final String style;
    private final double width;

    public static int hashFor(char character, String fontFamily, String style) {
        int result = (character * 31) + fontFamily.hashCode();
        return (result * 31) + style.hashCode();
    }

    public FontCharacter(List<ShapeGroup> shapes, char character, double size, double width, String style, String fontFamily) {
        this.shapes = shapes;
        this.character = character;
        this.size = size;
        this.width = width;
        this.style = style;
        this.fontFamily = fontFamily;
    }

    public List<ShapeGroup> getShapes() {
        return this.shapes;
    }

    public double getWidth() {
        return this.width;
    }

    public int hashCode() {
        return hashFor(this.character, this.fontFamily, this.style);
    }
}
