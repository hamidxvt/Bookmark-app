package com.airbnb.lottie.manager;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.airbnb.lottie.FontAssetDelegate;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.MutablePair;
import com.airbnb.lottie.utils.Logger;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class FontAssetManager {
    private final AssetManager assetManager;
    private FontAssetDelegate delegate;
    private final MutablePair<String> tempPair = new MutablePair<>();
    private final Map<MutablePair<String>, Typeface> fontMap = new HashMap();
    private final Map<String, Typeface> fontFamilies = new HashMap();
    private String defaultFontFileExtension = ".ttf";

    public FontAssetManager(Drawable.Callback callback, FontAssetDelegate delegate) {
        this.delegate = delegate;
        if (!(callback instanceof View)) {
            Logger.warning("LottieDrawable must be inside of a view for images to work.");
            this.assetManager = null;
        } else {
            this.assetManager = ((View) callback).getContext().getAssets();
        }
    }

    public void setDelegate(FontAssetDelegate assetDelegate) {
        this.delegate = assetDelegate;
    }

    public void setDefaultFontFileExtension(String defaultFontFileExtension) {
        this.defaultFontFileExtension = defaultFontFileExtension;
    }

    public Typeface getTypeface(Font font) {
        this.tempPair.set(font.getFamily(), font.getStyle());
        Typeface typeface = this.fontMap.get(this.tempPair);
        if (typeface != null) {
            return typeface;
        }
        Typeface typefaceWithDefaultStyle = getFontFamily(font);
        Typeface typeface2 = typefaceForStyle(typefaceWithDefaultStyle, font.getStyle());
        this.fontMap.put(this.tempPair, typeface2);
        return typeface2;
    }

    private Typeface getFontFamily(Font font) {
        String fontFamily = font.getFamily();
        Typeface defaultTypeface = this.fontFamilies.get(fontFamily);
        if (defaultTypeface != null) {
            return defaultTypeface;
        }
        Typeface typeface = null;
        String fontStyle = font.getStyle();
        String fontName = font.getName();
        if (this.delegate != null && (typeface = this.delegate.fetchFont(fontFamily, fontStyle, fontName)) == null) {
            typeface = this.delegate.fetchFont(fontFamily);
        }
        if (this.delegate != null && typeface == null) {
            String path = this.delegate.getFontPath(fontFamily, fontStyle, fontName);
            if (path == null) {
                path = this.delegate.getFontPath(fontFamily);
            }
            if (path != null) {
                typeface = Typeface.createFromAsset(this.assetManager, path);
            }
        }
        if (font.getTypeface() != null) {
            return font.getTypeface();
        }
        if (typeface == null) {
            typeface = Typeface.createFromAsset(this.assetManager, "fonts/" + fontFamily + this.defaultFontFileExtension);
        }
        this.fontFamilies.put(fontFamily, typeface);
        return typeface;
    }

    private Typeface typefaceForStyle(Typeface typeface, String style) {
        int styleInt = 0;
        boolean containsItalic = style.contains("Italic");
        boolean containsBold = style.contains("Bold");
        if (containsItalic && containsBold) {
            styleInt = 3;
        } else if (containsItalic) {
            styleInt = 2;
        } else if (containsBold) {
            styleInt = 1;
        }
        if (typeface.getStyle() == styleInt) {
            return typeface;
        }
        return Typeface.create(typeface, styleInt);
    }
}
