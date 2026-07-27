package eightbitlab.com.blurview;

/* loaded from: classes17.dex */
public class SizeScaler {
    private static final int ROUNDING_VALUE = 64;
    private final float scaleFactor;

    public SizeScaler(float scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    Size scale(int width, int height) {
        int nonRoundedScaledWidth = downscaleSize(width);
        int scaledWidth = roundSize(nonRoundedScaledWidth);
        float roundingScaleFactor = width / scaledWidth;
        int scaledHeight = (int) Math.ceil(height / roundingScaleFactor);
        return new Size(scaledWidth, scaledHeight, roundingScaleFactor);
    }

    boolean isZeroSized(int measuredWidth, int measuredHeight) {
        return downscaleSize((float) measuredHeight) == 0 || downscaleSize((float) measuredWidth) == 0;
    }

    private int roundSize(int value) {
        if (value % 64 == 0) {
            return value;
        }
        return (value - (value % 64)) + 64;
    }

    private int downscaleSize(float value) {
        return (int) Math.ceil(value / this.scaleFactor);
    }

    static class Size {
        final int height;
        final float scaleFactor;
        final int width;

        Size(int width, int height, float scaleFactor) {
            this.width = width;
            this.height = height;
            this.scaleFactor = scaleFactor;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Size size = (Size) o;
            if (this.width == size.width && this.height == size.height && Float.compare(size.scaleFactor, this.scaleFactor) == 0) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int result = this.width;
            return (((result * 31) + this.height) * 31) + (this.scaleFactor != 0.0f ? Float.floatToIntBits(this.scaleFactor) : 0);
        }

        public String toString() {
            return "Size{width=" + this.width + ", height=" + this.height + ", scaleFactor=" + this.scaleFactor + '}';
        }
    }
}
