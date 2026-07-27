package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.TextDelegate;
import com.airbnb.lottie.animation.content.ContentGroup;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TextKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes.dex */
public class TextLayer extends BaseLayer {
    private final LongSparseArray<String> codePointCache;
    private BaseKeyframeAnimation<Integer, Integer> colorAnimation;
    private BaseKeyframeAnimation<Integer, Integer> colorCallbackAnimation;
    private final LottieComposition composition;
    private final Map<FontCharacter, List<ContentGroup>> contentsForCharacter;
    private final Paint fillPaint;
    private final LottieDrawable lottieDrawable;
    private final Matrix matrix;
    private final RectF rectF;
    private final StringBuilder stringBuilder;
    private BaseKeyframeAnimation<Integer, Integer> strokeColorAnimation;
    private BaseKeyframeAnimation<Integer, Integer> strokeColorCallbackAnimation;
    private final Paint strokePaint;
    private BaseKeyframeAnimation<Float, Float> strokeWidthAnimation;
    private BaseKeyframeAnimation<Float, Float> strokeWidthCallbackAnimation;
    private final TextKeyframeAnimation textAnimation;
    private BaseKeyframeAnimation<Float, Float> textSizeCallbackAnimation;
    private final List<TextSubLine> textSubLines;
    private BaseKeyframeAnimation<Float, Float> trackingAnimation;
    private BaseKeyframeAnimation<Float, Float> trackingCallbackAnimation;
    private BaseKeyframeAnimation<Typeface, Typeface> typefaceCallbackAnimation;

    TextLayer(LottieDrawable lottieDrawable, Layer layerModel) {
        super(lottieDrawable, layerModel);
        this.stringBuilder = new StringBuilder(2);
        this.rectF = new RectF();
        this.matrix = new Matrix();
        int i = 1;
        this.fillPaint = new Paint(i) { // from class: com.airbnb.lottie.model.layer.TextLayer.1
            {
                setStyle(Paint.Style.FILL);
            }
        };
        this.strokePaint = new Paint(i) { // from class: com.airbnb.lottie.model.layer.TextLayer.2
            {
                setStyle(Paint.Style.STROKE);
            }
        };
        this.contentsForCharacter = new HashMap();
        this.codePointCache = new LongSparseArray<>();
        this.textSubLines = new ArrayList();
        this.lottieDrawable = lottieDrawable;
        this.composition = layerModel.getComposition();
        this.textAnimation = layerModel.getText().createAnimation();
        this.textAnimation.addUpdateListener(this);
        addAnimation(this.textAnimation);
        AnimatableTextProperties textProperties = layerModel.getTextProperties();
        if (textProperties != null && textProperties.color != null) {
            this.colorAnimation = textProperties.color.createAnimation();
            this.colorAnimation.addUpdateListener(this);
            addAnimation(this.colorAnimation);
        }
        if (textProperties != null && textProperties.stroke != null) {
            this.strokeColorAnimation = textProperties.stroke.createAnimation();
            this.strokeColorAnimation.addUpdateListener(this);
            addAnimation(this.strokeColorAnimation);
        }
        if (textProperties != null && textProperties.strokeWidth != null) {
            this.strokeWidthAnimation = textProperties.strokeWidth.createAnimation();
            this.strokeWidthAnimation.addUpdateListener(this);
            addAnimation(this.strokeWidthAnimation);
        }
        if (textProperties != null && textProperties.tracking != null) {
            this.trackingAnimation = textProperties.tracking.createAnimation();
            this.trackingAnimation.addUpdateListener(this);
            addAnimation(this.trackingAnimation);
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF outBounds, Matrix parentMatrix, boolean applyParents) {
        super.getBounds(outBounds, parentMatrix, applyParents);
        outBounds.set(0.0f, 0.0f, this.composition.getBounds().width(), this.composition.getBounds().height());
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    void drawLayer(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        DocumentData documentData = this.textAnimation.getValue();
        Font font = this.composition.getFonts().get(documentData.fontName);
        if (font == null) {
            return;
        }
        canvas.save();
        canvas.concat(parentMatrix);
        configurePaint(documentData, parentAlpha);
        if (this.lottieDrawable.useTextGlyphs()) {
            drawTextWithGlyphs(documentData, parentMatrix, font, canvas);
        } else {
            drawTextWithFont(documentData, font, canvas);
        }
        canvas.restore();
    }

    private void configurePaint(DocumentData documentData, int parentAlpha) {
        if (this.colorCallbackAnimation != null) {
            this.fillPaint.setColor(this.colorCallbackAnimation.getValue().intValue());
        } else if (this.colorAnimation != null) {
            this.fillPaint.setColor(this.colorAnimation.getValue().intValue());
        } else {
            this.fillPaint.setColor(documentData.color);
        }
        if (this.strokeColorCallbackAnimation != null) {
            this.strokePaint.setColor(this.strokeColorCallbackAnimation.getValue().intValue());
        } else if (this.strokeColorAnimation != null) {
            this.strokePaint.setColor(this.strokeColorAnimation.getValue().intValue());
        } else {
            this.strokePaint.setColor(documentData.strokeColor);
        }
        int opacity = this.transform.getOpacity() == null ? 100 : this.transform.getOpacity().getValue().intValue();
        int alpha = (((opacity * 255) / 100) * parentAlpha) / 255;
        this.fillPaint.setAlpha(alpha);
        this.strokePaint.setAlpha(alpha);
        if (this.strokeWidthCallbackAnimation != null) {
            this.strokePaint.setStrokeWidth(this.strokeWidthCallbackAnimation.getValue().floatValue());
        } else if (this.strokeWidthAnimation != null) {
            this.strokePaint.setStrokeWidth(this.strokeWidthAnimation.getValue().floatValue());
        } else {
            this.strokePaint.setStrokeWidth(documentData.strokeWidth * Utils.dpScale());
        }
    }

    private void drawTextWithGlyphs(DocumentData documentData, Matrix parentMatrix, Font font, Canvas canvas) {
        float textSize;
        float tracking;
        int lineIndex;
        int textLineCount;
        List<String> textLines;
        String text;
        if (this.textSizeCallbackAnimation != null) {
            textSize = this.textSizeCallbackAnimation.getValue().floatValue();
        } else {
            float textSize2 = documentData.size;
            textSize = textSize2;
        }
        float fontScale = textSize / 100.0f;
        float parentScale = Utils.getScale(parentMatrix);
        String text2 = documentData.text;
        List<String> textLines2 = getTextLines(text2);
        int textLineCount2 = textLines2.size();
        float tracking2 = documentData.tracking / 10.0f;
        if (this.trackingCallbackAnimation != null) {
            tracking = tracking2 + this.trackingCallbackAnimation.getValue().floatValue();
        } else if (this.trackingAnimation == null) {
            tracking = tracking2;
        } else {
            tracking = tracking2 + this.trackingAnimation.getValue().floatValue();
        }
        int lineIndex2 = -1;
        int i = 0;
        while (i < textLineCount2) {
            String textLine = textLines2.get(i);
            float boxWidth = documentData.boxSize == null ? 0.0f : documentData.boxSize.x;
            int i2 = i;
            List<TextSubLine> lines = splitGlyphTextIntoLines(textLine, boxWidth, font, fontScale, tracking, true);
            int j = 0;
            while (j < lines.size()) {
                TextSubLine line = lines.get(j);
                int lineIndex3 = lineIndex2 + 1;
                canvas.save();
                if (!offsetCanvas(canvas, documentData, lineIndex3, line.width)) {
                    lineIndex = lineIndex3;
                    textLineCount = textLineCount2;
                    textLines = textLines2;
                    text = text2;
                } else {
                    lineIndex = lineIndex3;
                    textLineCount = textLineCount2;
                    textLines = textLines2;
                    text = text2;
                    drawGlyphTextLine(line.text, documentData, font, canvas, parentScale, fontScale, tracking);
                }
                canvas.restore();
                j++;
                lineIndex2 = lineIndex;
                textLineCount2 = textLineCount;
                textLines2 = textLines;
                text2 = text;
            }
            i = i2 + 1;
        }
    }

    private void drawGlyphTextLine(String text, DocumentData documentData, Font font, Canvas canvas, float parentScale, float fontScale, float tracking) {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int characterHash = FontCharacter.hashFor(c, font.getFamily(), font.getStyle());
            FontCharacter character = this.composition.getCharacters().get(characterHash);
            if (character != null) {
                drawCharacterAsGlyph(character, fontScale, documentData, canvas);
                float tx = (((float) character.getWidth()) * fontScale * Utils.dpScale()) + tracking;
                canvas.translate(tx, 0.0f);
            }
        }
    }

    private void drawTextWithFont(DocumentData documentData, Font font, Canvas canvas) {
        String text;
        float textSize;
        Typeface typeface = getTypeface(font);
        if (typeface == null) {
            return;
        }
        String text2 = documentData.text;
        TextDelegate textDelegate = this.lottieDrawable.getTextDelegate();
        if (textDelegate == null) {
            text = text2;
        } else {
            text = textDelegate.getTextInternal(getName(), text2);
        }
        this.fillPaint.setTypeface(typeface);
        if (this.textSizeCallbackAnimation != null) {
            textSize = this.textSizeCallbackAnimation.getValue().floatValue();
        } else {
            float textSize2 = documentData.size;
            textSize = textSize2;
        }
        this.fillPaint.setTextSize(Utils.dpScale() * textSize);
        this.strokePaint.setTypeface(this.fillPaint.getTypeface());
        this.strokePaint.setTextSize(this.fillPaint.getTextSize());
        float tracking = documentData.tracking / 10.0f;
        if (this.trackingCallbackAnimation != null) {
            tracking += this.trackingCallbackAnimation.getValue().floatValue();
        } else if (this.trackingAnimation != null) {
            tracking += this.trackingAnimation.getValue().floatValue();
        }
        float tracking2 = ((Utils.dpScale() * tracking) * textSize) / 100.0f;
        List<String> textLines = getTextLines(text);
        int textLineCount = textLines.size();
        int lineIndex = -1;
        int lineIndex2 = 0;
        while (lineIndex2 < textLineCount) {
            String textLine = textLines.get(lineIndex2);
            float boxWidth = documentData.boxSize == null ? 0.0f : documentData.boxSize.x;
            int i = lineIndex2;
            int textLineCount2 = textLineCount;
            List<String> textLines2 = textLines;
            List<TextSubLine> lines = splitGlyphTextIntoLines(textLine, boxWidth, font, 0.0f, tracking2, false);
            int j = 0;
            while (j < lines.size()) {
                TextSubLine line = lines.get(j);
                int lineIndex3 = lineIndex + 1;
                canvas.save();
                if (offsetCanvas(canvas, documentData, lineIndex3, line.width)) {
                    drawFontTextLine(line.text, documentData, canvas, tracking2);
                }
                canvas.restore();
                j++;
                lineIndex = lineIndex3;
            }
            lineIndex2 = i + 1;
            textLineCount = textLineCount2;
            textLines = textLines2;
        }
    }

    private boolean offsetCanvas(Canvas canvas, DocumentData documentData, int lineIndex, float lineWidth) {
        PointF position = documentData.boxPosition;
        PointF size = documentData.boxSize;
        float dpScale = Utils.dpScale();
        float lineStartY = position == null ? 0.0f : (documentData.lineHeight * dpScale) + position.y;
        float lineOffset = (lineIndex * documentData.lineHeight * dpScale) + lineStartY;
        if (this.lottieDrawable.getClipTextToBoundingBox() && size != null && position != null && lineOffset >= position.y + size.y + documentData.size) {
            return false;
        }
        float lineStart = position == null ? 0.0f : position.x;
        float boxWidth = size != null ? size.x : 0.0f;
        switch (documentData.justification) {
            case LEFT_ALIGN:
                canvas.translate(lineStart, lineOffset);
                return true;
            case RIGHT_ALIGN:
                canvas.translate((lineStart + boxWidth) - lineWidth, lineOffset);
                return true;
            case CENTER:
                canvas.translate(((boxWidth / 2.0f) + lineStart) - (lineWidth / 2.0f), lineOffset);
                return true;
            default:
                return true;
        }
    }

    private Typeface getTypeface(Font font) {
        Typeface callbackTypeface;
        if (this.typefaceCallbackAnimation != null && (callbackTypeface = this.typefaceCallbackAnimation.getValue()) != null) {
            return callbackTypeface;
        }
        Typeface drawableTypeface = this.lottieDrawable.getTypeface(font);
        if (drawableTypeface != null) {
            return drawableTypeface;
        }
        return font.getTypeface();
    }

    private List<String> getTextLines(String text) {
        String formattedText = text.replaceAll("\r\n", StringUtils.CR).replaceAll("\u0003", StringUtils.CR).replaceAll(StringUtils.LF, StringUtils.CR);
        String[] textLinesArray = formattedText.split(StringUtils.CR);
        return Arrays.asList(textLinesArray);
    }

    private void drawFontTextLine(String text, DocumentData documentData, Canvas canvas, float tracking) {
        int i = 0;
        while (i < text.length()) {
            String charString = codePointToString(text, i);
            i += charString.length();
            drawCharacterFromFont(charString, documentData, canvas);
            float charWidth = this.fillPaint.measureText(charString);
            float tx = charWidth + tracking;
            canvas.translate(tx, 0.0f);
        }
    }

    private List<TextSubLine> splitGlyphTextIntoLines(String textLine, float boxWidth, Font font, float fontScale, float tracking, boolean usingGlyphs) {
        float currentCharWidth;
        int currentLineStartIndex = 0;
        float currentLineWidth = 0.0f;
        int currentWordStartIndex = 0;
        int currentWordStartIndex2 = 0;
        float currentWordWidth = 0.0f;
        boolean nextCharacterStartsWord = false;
        float spaceWidth = 0.0f;
        for (int i = 0; i < textLine.length(); i++) {
            char c = textLine.charAt(i);
            if (usingGlyphs) {
                int characterHash = FontCharacter.hashFor(c, font.getFamily(), font.getStyle());
                FontCharacter character = this.composition.getCharacters().get(characterHash);
                if (character != null) {
                    currentCharWidth = (((float) character.getWidth()) * fontScale * Utils.dpScale()) + tracking;
                }
            } else {
                currentCharWidth = this.fillPaint.measureText(textLine.substring(i, i + 1)) + tracking;
            }
            if (c == ' ') {
                spaceWidth = currentCharWidth;
                nextCharacterStartsWord = true;
            } else if (nextCharacterStartsWord) {
                nextCharacterStartsWord = false;
                currentWordStartIndex2 = i;
                currentWordWidth = currentCharWidth;
            } else {
                currentWordWidth += currentCharWidth;
            }
            currentLineWidth += currentCharWidth;
            if (boxWidth > 0.0f && currentLineWidth >= boxWidth && c != ' ') {
                int lineCount = currentLineStartIndex + 1;
                TextSubLine subLine = ensureEnoughSubLines(lineCount);
                if (currentWordStartIndex2 == currentWordStartIndex) {
                    String substr = textLine.substring(currentWordStartIndex, i);
                    String trimmed = substr.trim();
                    float trimmedSpace = (trimmed.length() - substr.length()) * spaceWidth;
                    subLine.set(trimmed, (currentLineWidth - currentCharWidth) - trimmedSpace);
                    int currentLineStartIndex2 = i;
                    currentLineWidth = currentCharWidth;
                    currentWordStartIndex = currentLineStartIndex2;
                    float currentWordWidth2 = currentCharWidth;
                    currentWordWidth = currentWordWidth2;
                    currentWordStartIndex2 = currentWordStartIndex;
                    currentLineStartIndex = lineCount;
                } else {
                    String substr2 = textLine.substring(currentWordStartIndex, currentWordStartIndex2 - 1);
                    String trimmed2 = substr2.trim();
                    float trimmedSpace2 = (substr2.length() - trimmed2.length()) * spaceWidth;
                    subLine.set(trimmed2, ((currentLineWidth - currentWordWidth) - trimmedSpace2) - spaceWidth);
                    currentWordStartIndex = currentWordStartIndex2;
                    currentLineWidth = currentWordWidth;
                    currentLineStartIndex = lineCount;
                }
            }
        }
        if (currentLineWidth > 0.0f) {
            currentLineStartIndex++;
            TextSubLine line = ensureEnoughSubLines(currentLineStartIndex);
            line.set(textLine.substring(currentWordStartIndex), currentLineWidth);
        }
        return this.textSubLines.subList(0, currentLineStartIndex);
    }

    private TextSubLine ensureEnoughSubLines(int numLines) {
        for (int i = this.textSubLines.size(); i < numLines; i++) {
            this.textSubLines.add(new TextSubLine());
        }
        return this.textSubLines.get(numLines - 1);
    }

    private void drawCharacterAsGlyph(FontCharacter character, float fontScale, DocumentData documentData, Canvas canvas) {
        List<ContentGroup> contentGroups = getContentsForCharacter(character);
        for (int j = 0; j < contentGroups.size(); j++) {
            Path path = contentGroups.get(j).getPath();
            path.computeBounds(this.rectF, false);
            this.matrix.reset();
            this.matrix.preTranslate(0.0f, (-documentData.baselineShift) * Utils.dpScale());
            this.matrix.preScale(fontScale, fontScale);
            path.transform(this.matrix);
            if (documentData.strokeOverFill) {
                drawGlyph(path, this.fillPaint, canvas);
                drawGlyph(path, this.strokePaint, canvas);
            } else {
                drawGlyph(path, this.strokePaint, canvas);
                drawGlyph(path, this.fillPaint, canvas);
            }
        }
    }

    private void drawGlyph(Path path, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawPath(path, paint);
    }

    private void drawCharacterFromFont(String character, DocumentData documentData, Canvas canvas) {
        if (documentData.strokeOverFill) {
            drawCharacter(character, this.fillPaint, canvas);
            drawCharacter(character, this.strokePaint, canvas);
        } else {
            drawCharacter(character, this.strokePaint, canvas);
            drawCharacter(character, this.fillPaint, canvas);
        }
    }

    private void drawCharacter(String character, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawText(character, 0, character.length(), 0.0f, 0.0f, paint);
    }

    private List<ContentGroup> getContentsForCharacter(FontCharacter character) {
        if (this.contentsForCharacter.containsKey(character)) {
            return this.contentsForCharacter.get(character);
        }
        List<ShapeGroup> shapes = character.getShapes();
        int size = shapes.size();
        List<ContentGroup> contents = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            ShapeGroup sg = shapes.get(i);
            contents.add(new ContentGroup(this.lottieDrawable, this, sg, this.composition));
        }
        this.contentsForCharacter.put(character, contents);
        return contents;
    }

    private String codePointToString(String text, int startIndex) {
        int firstCodePoint = text.codePointAt(startIndex);
        int firstCodePointLength = Character.charCount(firstCodePoint);
        int key = firstCodePoint;
        int index = startIndex + firstCodePointLength;
        while (index < text.length()) {
            int nextCodePoint = text.codePointAt(index);
            if (!isModifier(nextCodePoint)) {
                break;
            }
            int nextCodePointLength = Character.charCount(nextCodePoint);
            index += nextCodePointLength;
            key = (key * 31) + nextCodePoint;
        }
        if (this.codePointCache.containsKey(key)) {
            return this.codePointCache.get(key);
        }
        this.stringBuilder.setLength(0);
        int i = startIndex;
        while (i < index) {
            int codePoint = text.codePointAt(i);
            this.stringBuilder.appendCodePoint(codePoint);
            i += Character.charCount(codePoint);
        }
        String str = this.stringBuilder.toString();
        this.codePointCache.put(key, str);
        return str;
    }

    private boolean isModifier(int codePoint) {
        return Character.getType(codePoint) == 16 || Character.getType(codePoint) == 27 || Character.getType(codePoint) == 6 || Character.getType(codePoint) == 28 || Character.getType(codePoint) == 8 || Character.getType(codePoint) == 19;
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        super.addValueCallback(property, callback);
        if (property == LottieProperty.COLOR) {
            if (this.colorCallbackAnimation != null) {
                removeAnimation(this.colorCallbackAnimation);
            }
            if (callback == null) {
                this.colorCallbackAnimation = null;
                return;
            }
            this.colorCallbackAnimation = new ValueCallbackKeyframeAnimation(callback);
            this.colorCallbackAnimation.addUpdateListener(this);
            addAnimation(this.colorCallbackAnimation);
            return;
        }
        if (property == LottieProperty.STROKE_COLOR) {
            if (this.strokeColorCallbackAnimation != null) {
                removeAnimation(this.strokeColorCallbackAnimation);
            }
            if (callback == null) {
                this.strokeColorCallbackAnimation = null;
                return;
            }
            this.strokeColorCallbackAnimation = new ValueCallbackKeyframeAnimation(callback);
            this.strokeColorCallbackAnimation.addUpdateListener(this);
            addAnimation(this.strokeColorCallbackAnimation);
            return;
        }
        if (property == LottieProperty.STROKE_WIDTH) {
            if (this.strokeWidthCallbackAnimation != null) {
                removeAnimation(this.strokeWidthCallbackAnimation);
            }
            if (callback == null) {
                this.strokeWidthCallbackAnimation = null;
                return;
            }
            this.strokeWidthCallbackAnimation = new ValueCallbackKeyframeAnimation(callback);
            this.strokeWidthCallbackAnimation.addUpdateListener(this);
            addAnimation(this.strokeWidthCallbackAnimation);
            return;
        }
        if (property == LottieProperty.TEXT_TRACKING) {
            if (this.trackingCallbackAnimation != null) {
                removeAnimation(this.trackingCallbackAnimation);
            }
            if (callback == null) {
                this.trackingCallbackAnimation = null;
                return;
            }
            this.trackingCallbackAnimation = new ValueCallbackKeyframeAnimation(callback);
            this.trackingCallbackAnimation.addUpdateListener(this);
            addAnimation(this.trackingCallbackAnimation);
            return;
        }
        if (property == LottieProperty.TEXT_SIZE) {
            if (this.textSizeCallbackAnimation != null) {
                removeAnimation(this.textSizeCallbackAnimation);
            }
            if (callback == null) {
                this.textSizeCallbackAnimation = null;
                return;
            }
            this.textSizeCallbackAnimation = new ValueCallbackKeyframeAnimation(callback);
            this.textSizeCallbackAnimation.addUpdateListener(this);
            addAnimation(this.textSizeCallbackAnimation);
            return;
        }
        if (property == LottieProperty.TYPEFACE) {
            if (this.typefaceCallbackAnimation != null) {
                removeAnimation(this.typefaceCallbackAnimation);
            }
            if (callback == null) {
                this.typefaceCallbackAnimation = null;
                return;
            }
            this.typefaceCallbackAnimation = new ValueCallbackKeyframeAnimation(callback);
            this.typefaceCallbackAnimation.addUpdateListener(this);
            addAnimation(this.typefaceCallbackAnimation);
            return;
        }
        if (property == LottieProperty.TEXT) {
            this.textAnimation.setStringValueCallback(callback);
        }
    }

    private static class TextSubLine {
        private String text;
        private float width;

        private TextSubLine() {
            this.text = "";
            this.width = 0.0f;
        }

        void set(String text, float width) {
            this.text = text;
            this.width = width;
        }
    }
}
