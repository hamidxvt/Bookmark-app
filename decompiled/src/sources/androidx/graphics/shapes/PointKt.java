package androidx.graphics.shapes;

import androidx.collection.FloatFloatPair;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Point.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a6\u0010\b\u001a\u00060\u0002j\u0002`\u00032\n\u0010\t\u001a\u00060\u0002j\u0002`\u00032\n\u0010\n\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u000b\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a&\u0010\u000e\u001a\u00020\u000f*\u00060\u0002j\u0002`\u00032\n\u0010\u0010\u001a\u00060\u0002j\u0002`\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a2\u0010\u0013\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\b\b\u0002\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a'\u0010\u0016\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\u0006\u0010\u0017\u001a\u00020\u0001H\u0080\u0002ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a&\u0010\u001a\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00032\n\u0010\u0010\u001a\u00060\u0002j\u0002`\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a*\u0010\u001a\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00032\u0006\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a\u001e\u0010!\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001a\u001a\u0010$\u001a\u00020\u0001*\u00060\u0002j\u0002`\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b%\u0010\u0005\u001a\u001a\u0010&\u001a\u00020\u0001*\u00060\u0002j\u0002`\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b'\u0010\u0005\u001a+\u0010(\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\n\u0010\u0010\u001a\u00060\u0002j\u0002`\u0003H\u0080\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a+\u0010+\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\n\u0010\u0010\u001a\u00060\u0002j\u0002`\u0003H\u0080\u0002ø\u0001\u0000¢\u0006\u0004\b,\u0010*\u001a'\u0010-\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\u0006\u0010\u0017\u001a\u00020\u0001H\u0080\u0002ø\u0001\u0000¢\u0006\u0004\b.\u0010\u0019\u001a'\u0010/\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\u0006\u0010\u0017\u001a\u00020\u0001H\u0080\u0002ø\u0001\u0000¢\u0006\u0004\b0\u0010\u0019\u001a&\u00101\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\u0006\u00102\u001a\u000203H\u0000ø\u0001\u0000¢\u0006\u0004\b4\u00105\u001a\u001f\u00106\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u0003H\u0080\u0002ø\u0001\u0000¢\u0006\u0004\b7\u0010#\"\u001c\u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u001c\u0010\u0006\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005*\f\b\u0000\u00108\"\u00020\u00022\u00020\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00069"}, d2 = {"x", "", "Landroidx/collection/FloatFloatPair;", "Landroidx/graphics/shapes/Point;", "getX-DnnuFBc", "(J)F", "y", "getY-DnnuFBc", "interpolate", "start", "stop", "fraction", "interpolate-dLqxh1s", "(JJF)J", "clockwise", "", "other", "clockwise-ybeJwSQ", "(JJ)Z", "copy", "copy-5P9i7ZU", "(JFF)J", "div", "operand", "div-so9K2fw", "(JF)J", "dotProduct", "dotProduct-ybeJwSQ", "(JJ)F", "otherX", "otherY", "dotProduct-5P9i7ZU", "(JFF)F", "getDirection", "getDirection-DnnuFBc", "(J)J", "getDistance", "getDistance-DnnuFBc", "getDistanceSquared", "getDistanceSquared-DnnuFBc", "minus", "minus-ybeJwSQ", "(JJ)J", "plus", "plus-ybeJwSQ", "rem", "rem-so9K2fw", "times", "times-so9K2fw", "transformed", "f", "Landroidx/graphics/shapes/PointTransformer;", "transformed-so9K2fw", "(JLandroidx/graphics/shapes/PointTransformer;)J", "unaryMinus", "unaryMinus-DnnuFBc", "Point", "graphics-shapes_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class PointKt {
    /* renamed from: getX-DnnuFBc, reason: not valid java name */
    public static final float m116getXDnnuFBc(long $this$x) {
        int bits$iv$iv = (int) ($this$x >> 32);
        return Float.intBitsToFloat(bits$iv$iv);
    }

    /* renamed from: getY-DnnuFBc, reason: not valid java name */
    public static final float m117getYDnnuFBc(long $this$y) {
        int bits$iv$iv = (int) (4294967295L & $this$y);
        return Float.intBitsToFloat(bits$iv$iv);
    }

    /* renamed from: copy-5P9i7ZU, reason: not valid java name */
    public static final long m108copy5P9i7ZU(long $this$copy_u2d5P9i7ZU, float x, float y) {
        return FloatFloatPair.m12constructorimpl(x, y);
    }

    /* renamed from: copy-5P9i7ZU$default, reason: not valid java name */
    public static /* synthetic */ long m109copy5P9i7ZU$default(long j, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            int bits$iv$iv = (int) (j >> 32);
            f = Float.intBitsToFloat(bits$iv$iv);
        }
        if ((i & 2) != 0) {
            int bits$iv$iv2 = (int) (4294967295L & j);
            f2 = Float.intBitsToFloat(bits$iv$iv2);
        }
        return m108copy5P9i7ZU(j, f, f2);
    }

    /* renamed from: getDistance-DnnuFBc, reason: not valid java name */
    public static final float m114getDistanceDnnuFBc(long $this$getDistance_u2dDnnuFBc) {
        return (float) Math.sqrt((m116getXDnnuFBc($this$getDistance_u2dDnnuFBc) * m116getXDnnuFBc($this$getDistance_u2dDnnuFBc)) + (m117getYDnnuFBc($this$getDistance_u2dDnnuFBc) * m117getYDnnuFBc($this$getDistance_u2dDnnuFBc)));
    }

    /* renamed from: getDistanceSquared-DnnuFBc, reason: not valid java name */
    public static final float m115getDistanceSquaredDnnuFBc(long $this$getDistanceSquared_u2dDnnuFBc) {
        return (m116getXDnnuFBc($this$getDistanceSquared_u2dDnnuFBc) * m116getXDnnuFBc($this$getDistanceSquared_u2dDnnuFBc)) + (m117getYDnnuFBc($this$getDistanceSquared_u2dDnnuFBc) * m117getYDnnuFBc($this$getDistanceSquared_u2dDnnuFBc));
    }

    /* renamed from: dotProduct-ybeJwSQ, reason: not valid java name */
    public static final float m112dotProductybeJwSQ(long $this$dotProduct_u2dybeJwSQ, long other) {
        return (m116getXDnnuFBc($this$dotProduct_u2dybeJwSQ) * m116getXDnnuFBc(other)) + (m117getYDnnuFBc($this$dotProduct_u2dybeJwSQ) * m117getYDnnuFBc(other));
    }

    /* renamed from: dotProduct-5P9i7ZU, reason: not valid java name */
    public static final float m111dotProduct5P9i7ZU(long $this$dotProduct_u2d5P9i7ZU, float otherX, float otherY) {
        return (m116getXDnnuFBc($this$dotProduct_u2d5P9i7ZU) * otherX) + (m117getYDnnuFBc($this$dotProduct_u2d5P9i7ZU) * otherY);
    }

    /* renamed from: clockwise-ybeJwSQ, reason: not valid java name */
    public static final boolean m107clockwiseybeJwSQ(long $this$clockwise_u2dybeJwSQ, long other) {
        return (m116getXDnnuFBc($this$clockwise_u2dybeJwSQ) * m117getYDnnuFBc(other)) - (m117getYDnnuFBc($this$clockwise_u2dybeJwSQ) * m116getXDnnuFBc(other)) > 0.0f;
    }

    /* renamed from: getDirection-DnnuFBc, reason: not valid java name */
    public static final long m113getDirectionDnnuFBc(long $this$getDirection_u2dDnnuFBc) {
        float d = m114getDistanceDnnuFBc($this$getDirection_u2dDnnuFBc);
        if (!(d > 0.0f)) {
            throw new IllegalArgumentException("Can't get the direction of a 0-length vector".toString());
        }
        long $this$getDirection_DnnuFBc_u24lambda_u241 = m110divso9K2fw($this$getDirection_u2dDnnuFBc, d);
        return $this$getDirection_DnnuFBc_u24lambda_u241;
    }

    /* renamed from: unaryMinus-DnnuFBc, reason: not valid java name */
    public static final long m124unaryMinusDnnuFBc(long $this$unaryMinus_u2dDnnuFBc) {
        return FloatFloatPair.m12constructorimpl(-m116getXDnnuFBc($this$unaryMinus_u2dDnnuFBc), -m117getYDnnuFBc($this$unaryMinus_u2dDnnuFBc));
    }

    /* renamed from: minus-ybeJwSQ, reason: not valid java name */
    public static final long m119minusybeJwSQ(long $this$minus_u2dybeJwSQ, long other) {
        return FloatFloatPair.m12constructorimpl(m116getXDnnuFBc($this$minus_u2dybeJwSQ) - m116getXDnnuFBc(other), m117getYDnnuFBc($this$minus_u2dybeJwSQ) - m117getYDnnuFBc(other));
    }

    /* renamed from: plus-ybeJwSQ, reason: not valid java name */
    public static final long m120plusybeJwSQ(long $this$plus_u2dybeJwSQ, long other) {
        return FloatFloatPair.m12constructorimpl(m116getXDnnuFBc($this$plus_u2dybeJwSQ) + m116getXDnnuFBc(other), m117getYDnnuFBc($this$plus_u2dybeJwSQ) + m117getYDnnuFBc(other));
    }

    /* renamed from: times-so9K2fw, reason: not valid java name */
    public static final long m122timesso9K2fw(long $this$times_u2dso9K2fw, float operand) {
        return FloatFloatPair.m12constructorimpl(m116getXDnnuFBc($this$times_u2dso9K2fw) * operand, m117getYDnnuFBc($this$times_u2dso9K2fw) * operand);
    }

    /* renamed from: div-so9K2fw, reason: not valid java name */
    public static final long m110divso9K2fw(long $this$div_u2dso9K2fw, float operand) {
        return FloatFloatPair.m12constructorimpl(m116getXDnnuFBc($this$div_u2dso9K2fw) / operand, m117getYDnnuFBc($this$div_u2dso9K2fw) / operand);
    }

    /* renamed from: rem-so9K2fw, reason: not valid java name */
    public static final long m121remso9K2fw(long $this$rem_u2dso9K2fw, float operand) {
        return FloatFloatPair.m12constructorimpl(m116getXDnnuFBc($this$rem_u2dso9K2fw) % operand, m117getYDnnuFBc($this$rem_u2dso9K2fw) % operand);
    }

    /* renamed from: interpolate-dLqxh1s, reason: not valid java name */
    public static final long m118interpolatedLqxh1s(long start, long stop, float fraction) {
        return FloatFloatPair.m12constructorimpl(Utils.interpolate(m116getXDnnuFBc(start), m116getXDnnuFBc(stop), fraction), Utils.interpolate(m117getYDnnuFBc(start), m117getYDnnuFBc(stop), fraction));
    }

    /* renamed from: transformed-so9K2fw, reason: not valid java name */
    public static final long m123transformedso9K2fw(long $this$transformed_u2dso9K2fw, PointTransformer f) {
        Intrinsics.checkNotNullParameter(f, "f");
        long result = f.mo125transformXgqJiTY(m116getXDnnuFBc($this$transformed_u2dso9K2fw), m117getYDnnuFBc($this$transformed_u2dso9K2fw));
        int bits$iv$iv = (int) (result >> 32);
        int bits$iv$iv2 = (int) (4294967295L & result);
        return FloatFloatPair.m12constructorimpl(Float.intBitsToFloat(bits$iv$iv), Float.intBitsToFloat(bits$iv$iv2));
    }
}
