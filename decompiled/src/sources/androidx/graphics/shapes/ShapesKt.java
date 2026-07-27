package androidx.graphics.shapes;

import androidx.collection.FloatFloatPair;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.graphics.shapes.RoundedPolygon;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: Shapes.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\u001aH\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0002\u001a0\u0010\f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0002\u001a4\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\b\b\u0003\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0005H\u0007\u001a>\u0010\u0012\u001a\u00020\u000f*\u00020\u00102\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0005H\u0007\u001a\u0084\u0001\u0010\u0014\u001a\u00020\u000f*\u00020\u00102\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u001a2\b\b\u0003\u0010\b\u001a\u00020\u00052\b\b\u0003\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0005H\u0007\u001aN\u0010\u001b\u001a\u00020\u000f*\u00020\u00102\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00172\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u001a2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u001ad\u0010\u001c\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u001a2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0005H\u0007¨\u0006\u001d"}, d2 = {"pillStarVerticesFromNumVerts", "", "numVerticesPerRadius", "", "width", "", "height", "innerRadius", "vertexSpacing", "startLocation", "centerX", "centerY", "starVerticesFromNumVerts", "radius", "circle", "Landroidx/graphics/shapes/RoundedPolygon;", "Landroidx/graphics/shapes/RoundedPolygon$Companion;", "numVertices", "pill", "smoothing", "pillStar", "innerRadiusRatio", "rounding", "Landroidx/graphics/shapes/CornerRounding;", "innerRounding", "perVertexRounding", "", "rectangle", "star", "graphics-shapes_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ShapesKt {
    public static final RoundedPolygon circle(RoundedPolygon.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return circle$default(companion, 0, 0.0f, 0.0f, 0.0f, 15, null);
    }

    public static final RoundedPolygon circle(RoundedPolygon.Companion companion, int i) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return circle$default(companion, i, 0.0f, 0.0f, 0.0f, 14, null);
    }

    public static final RoundedPolygon circle(RoundedPolygon.Companion companion, int i, float f) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return circle$default(companion, i, f, 0.0f, 0.0f, 12, null);
    }

    public static final RoundedPolygon circle(RoundedPolygon.Companion companion, int i, float f, float f2) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return circle$default(companion, i, f, f2, 0.0f, 8, null);
    }

    public static final RoundedPolygon pill(RoundedPolygon.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return pill$default(companion, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 31, null);
    }

    public static final RoundedPolygon pill(RoundedPolygon.Companion companion, float f) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return pill$default(companion, f, 0.0f, 0.0f, 0.0f, 0.0f, 30, null);
    }

    public static final RoundedPolygon pill(RoundedPolygon.Companion companion, float f, float f2) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return pill$default(companion, f, f2, 0.0f, 0.0f, 0.0f, 28, null);
    }

    public static final RoundedPolygon pill(RoundedPolygon.Companion companion, float f, float f2, float f3) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return pill$default(companion, f, f2, f3, 0.0f, 0.0f, 24, null);
    }

    public static final RoundedPolygon pill(RoundedPolygon.Companion companion, float f, float f2, float f3, float f4) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return pill$default(companion, f, f2, f3, f4, 0.0f, 16, null);
    }

    public static final RoundedPolygon pillStar(RoundedPolygon.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return pillStar$default(companion, 0.0f, 0.0f, 0, 0.0f, null, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 2047, null);
    }

    public static final RoundedPolygon pillStar(RoundedPolygon.Companion companion, float f) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return pillStar$default(companion, f, 0.0f, 0, 0.0f, null, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 2046, null);
    }

    public static final RoundedPolygon pillStar(RoundedPolygon.Companion companion, float f, float f2) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return pillStar$default(companion, f, f2, 0, 0.0f, null, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 2044, null);
    }

    public static final RoundedPolygon pillStar(RoundedPolygon.Companion companion, float f, float f2, int i) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return pillStar$default(companion, f, f2, i, 0.0f, null, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 2040, null);
    }

    public static final RoundedPolygon pillStar(RoundedPolygon.Companion companion, float f, float f2, int i, float f3) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return pillStar$default(companion, f, f2, i, f3, null, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 2032, null);
    }

    public static final RoundedPolygon pillStar(RoundedPolygon.Companion companion, float f, float f2, int i, float f3, CornerRounding rounding) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(rounding, "rounding");
        return pillStar$default(companion, f, f2, i, f3, rounding, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 2016, null);
    }

    public static final RoundedPolygon pillStar(RoundedPolygon.Companion companion, float f, float f2, int i, float f3, CornerRounding rounding, CornerRounding cornerRounding) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(rounding, "rounding");
        return pillStar$default(companion, f, f2, i, f3, rounding, cornerRounding, null, 0.0f, 0.0f, 0.0f, 0.0f, 1984, null);
    }

    public static final RoundedPolygon pillStar(RoundedPolygon.Companion companion, float f, float f2, int i, float f3, CornerRounding rounding, CornerRounding cornerRounding, List<CornerRounding> list) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(rounding, "rounding");
        return pillStar$default(companion, f, f2, i, f3, rounding, cornerRounding, list, 0.0f, 0.0f, 0.0f, 0.0f, 1920, null);
    }

    public static final RoundedPolygon pillStar(RoundedPolygon.Companion companion, float f, float f2, int i, float f3, CornerRounding rounding, CornerRounding cornerRounding, List<CornerRounding> list, float f4) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(rounding, "rounding");
        return pillStar$default(companion, f, f2, i, f3, rounding, cornerRounding, list, f4, 0.0f, 0.0f, 0.0f, 1792, null);
    }

    public static final RoundedPolygon pillStar(RoundedPolygon.Companion companion, float f, float f2, int i, float f3, CornerRounding rounding, CornerRounding cornerRounding, List<CornerRounding> list, float f4, float f5) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(rounding, "rounding");
        return pillStar$default(companion, f, f2, i, f3, rounding, cornerRounding, list, f4, f5, 0.0f, 0.0f, 1536, null);
    }

    public static final RoundedPolygon pillStar(RoundedPolygon.Companion companion, float f, float f2, int i, float f3, CornerRounding rounding, CornerRounding cornerRounding, List<CornerRounding> list, float f4, float f5, float f6) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(rounding, "rounding");
        return pillStar$default(companion, f, f2, i, f3, rounding, cornerRounding, list, f4, f5, f6, 0.0f, 1024, null);
    }

    public static final RoundedPolygon star(RoundedPolygon.Companion companion, int i) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return star$default(companion, i, 0.0f, 0.0f, null, null, null, 0.0f, 0.0f, 254, null);
    }

    public static final RoundedPolygon star(RoundedPolygon.Companion companion, int i, float f) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return star$default(companion, i, f, 0.0f, null, null, null, 0.0f, 0.0f, 252, null);
    }

    public static final RoundedPolygon star(RoundedPolygon.Companion companion, int i, float f, float f2) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return star$default(companion, i, f, f2, null, null, null, 0.0f, 0.0f, 248, null);
    }

    public static final RoundedPolygon star(RoundedPolygon.Companion companion, int i, float f, float f2, CornerRounding rounding) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(rounding, "rounding");
        return star$default(companion, i, f, f2, rounding, null, null, 0.0f, 0.0f, 240, null);
    }

    public static final RoundedPolygon star(RoundedPolygon.Companion companion, int i, float f, float f2, CornerRounding rounding, CornerRounding cornerRounding) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(rounding, "rounding");
        return star$default(companion, i, f, f2, rounding, cornerRounding, null, 0.0f, 0.0f, 224, null);
    }

    public static final RoundedPolygon star(RoundedPolygon.Companion companion, int i, float f, float f2, CornerRounding rounding, CornerRounding cornerRounding, List<CornerRounding> list) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(rounding, "rounding");
        return star$default(companion, i, f, f2, rounding, cornerRounding, list, 0.0f, 0.0f, 192, null);
    }

    public static final RoundedPolygon star(RoundedPolygon.Companion companion, int i, float f, float f2, CornerRounding rounding, CornerRounding cornerRounding, List<CornerRounding> list, float f3) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(rounding, "rounding");
        return star$default(companion, i, f, f2, rounding, cornerRounding, list, f3, 0.0f, 128, null);
    }

    public static /* synthetic */ RoundedPolygon circle$default(RoundedPolygon.Companion companion, int i, float f, float f2, float f3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8;
        }
        if ((i2 & 2) != 0) {
            f = 1.0f;
        }
        if ((i2 & 4) != 0) {
            f2 = 0.0f;
        }
        if ((i2 & 8) != 0) {
            f3 = 0.0f;
        }
        return circle(companion, i, f, f2, f3);
    }

    public static final RoundedPolygon circle(RoundedPolygon.Companion $this$circle, int numVertices, float radius, float centerX, float centerY) {
        Intrinsics.checkNotNullParameter($this$circle, "<this>");
        if (numVertices < 3) {
            throw new IllegalArgumentException("Circle must have at least three vertices");
        }
        float theta = Utils.getFloatPi() / numVertices;
        float polygonRadius = radius / ((float) Math.cos(theta));
        return RoundedPolygonKt.RoundedPolygon$default(numVertices, polygonRadius, centerX, centerY, new CornerRounding(radius, 0.0f, 2, null), null, 32, null);
    }

    public static /* synthetic */ RoundedPolygon rectangle$default(RoundedPolygon.Companion companion, float f, float f2, CornerRounding cornerRounding, List list, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 2.0f;
        }
        if ((i & 2) != 0) {
            f2 = 2.0f;
        }
        if ((i & 4) != 0) {
            cornerRounding = CornerRounding.Unrounded;
        }
        if ((i & 8) != 0) {
            list = null;
        }
        if ((i & 16) != 0) {
            f3 = 0.0f;
        }
        if ((i & 32) != 0) {
            f4 = 0.0f;
        }
        return rectangle(companion, f, f2, cornerRounding, list, f3, f4);
    }

    public static final RoundedPolygon rectangle(RoundedPolygon.Companion $this$rectangle, float width, float height, CornerRounding rounding, List<CornerRounding> list, float centerX, float centerY) {
        Intrinsics.checkNotNullParameter($this$rectangle, "<this>");
        Intrinsics.checkNotNullParameter(rounding, "rounding");
        float f = 2;
        float left = centerX - (width / f);
        float top = centerY - (height / f);
        float right = (width / f) + centerX;
        float bottom = (height / f) + centerY;
        return RoundedPolygonKt.RoundedPolygon(new float[]{right, bottom, left, bottom, left, top, right, top}, rounding, list, centerX, centerY);
    }

    public static /* synthetic */ RoundedPolygon star$default(RoundedPolygon.Companion companion, int i, float f, float f2, CornerRounding cornerRounding, CornerRounding cornerRounding2, List list, float f3, float f4, int i2, Object obj) {
        return star(companion, i, (i2 & 2) != 0 ? 1.0f : f, (i2 & 4) != 0 ? 0.5f : f2, (i2 & 8) != 0 ? CornerRounding.Unrounded : cornerRounding, (i2 & 16) != 0 ? null : cornerRounding2, (i2 & 32) == 0 ? list : null, (i2 & 64) != 0 ? 0.0f : f3, (i2 & 128) == 0 ? f4 : 0.0f);
    }

    public static final RoundedPolygon star(RoundedPolygon.Companion $this$star, int numVerticesPerRadius, float radius, float innerRadius, CornerRounding rounding, CornerRounding innerRounding, List<CornerRounding> list, float centerX, float centerY) {
        List pvRounding;
        Intrinsics.checkNotNullParameter($this$star, "<this>");
        Intrinsics.checkNotNullParameter(rounding, "rounding");
        if (radius <= 0.0f || innerRadius <= 0.0f) {
            throw new IllegalArgumentException("Star radii must both be greater than 0");
        }
        if (innerRadius >= radius) {
            throw new IllegalArgumentException("innerRadius must be less than radius");
        }
        List pvRounding2 = list;
        if (pvRounding2 == null && innerRounding != null) {
            Iterable $this$flatMap$iv = RangesKt.until(0, numVerticesPerRadius);
            Collection destination$iv$iv = new ArrayList();
            Iterator<Integer> it = $this$flatMap$iv.iterator();
            while (it.hasNext()) {
                ((IntIterator) it).nextInt();
                List pvRounding3 = pvRounding2;
                Iterable list$iv$iv = CollectionsKt.listOf((Object[]) new CornerRounding[]{rounding, innerRounding});
                CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
                pvRounding2 = pvRounding3;
            }
            pvRounding = (List) destination$iv$iv;
        } else {
            pvRounding = pvRounding2;
        }
        return RoundedPolygonKt.RoundedPolygon(starVerticesFromNumVerts(numVerticesPerRadius, radius, innerRadius, centerX, centerY), rounding, (List<CornerRounding>) pvRounding, centerX, centerY);
    }

    public static /* synthetic */ RoundedPolygon pill$default(RoundedPolygon.Companion companion, float f, float f2, float f3, float f4, float f5, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 2.0f;
        }
        return pill(companion, f, (i & 2) != 0 ? 1.0f : f2, (i & 4) != 0 ? 0.0f : f3, (i & 8) != 0 ? 0.0f : f4, (i & 16) == 0 ? f5 : 0.0f);
    }

    public static final RoundedPolygon pill(RoundedPolygon.Companion $this$pill, float width, float height, float smoothing, float centerX, float centerY) {
        Intrinsics.checkNotNullParameter($this$pill, "<this>");
        if (!(width > 0.0f && height > 0.0f)) {
            throw new IllegalArgumentException("Pill shapes must have positive width and height");
        }
        float f = 2;
        float wHalf = width / f;
        float hHalf = height / f;
        return RoundedPolygonKt.RoundedPolygon$default(new float[]{wHalf + centerX, hHalf + centerY, (-wHalf) + centerX, hHalf + centerY, (-wHalf) + centerX, (-hHalf) + centerY, wHalf + centerX, (-hHalf) + centerY}, new CornerRounding(Math.min(wHalf, hHalf), smoothing), null, centerX, centerY, 4, null);
    }

    public static /* synthetic */ RoundedPolygon pillStar$default(RoundedPolygon.Companion companion, float f, float f2, int i, float f3, CornerRounding cornerRounding, CornerRounding cornerRounding2, List list, float f4, float f5, float f6, float f7, int i2, Object obj) {
        return pillStar(companion, (i2 & 1) != 0 ? 2.0f : f, (i2 & 2) != 0 ? 1.0f : f2, (i2 & 4) != 0 ? 8 : i, (i2 & 8) != 0 ? 0.5f : f3, (i2 & 16) != 0 ? CornerRounding.Unrounded : cornerRounding, (i2 & 32) != 0 ? null : cornerRounding2, (i2 & 64) == 0 ? list : null, (i2 & 128) == 0 ? f4 : 0.5f, (i2 & 256) != 0 ? 0.0f : f5, (i2 & 512) != 0 ? 0.0f : f6, (i2 & 1024) == 0 ? f7 : 0.0f);
    }

    public static final RoundedPolygon pillStar(RoundedPolygon.Companion $this$pillStar, float width, float height, int numVerticesPerRadius, float innerRadiusRatio, CornerRounding rounding, CornerRounding innerRounding, List<CornerRounding> list, float vertexSpacing, float startLocation, float centerX, float centerY) {
        List pvRounding;
        Intrinsics.checkNotNullParameter($this$pillStar, "<this>");
        Intrinsics.checkNotNullParameter(rounding, "rounding");
        if (!(width > 0.0f && height > 0.0f)) {
            throw new IllegalArgumentException("Pill shapes must have positive width and height");
        }
        if (!(innerRadiusRatio > 0.0f && innerRadiusRatio <= 1.0f)) {
            throw new IllegalArgumentException("innerRadius must be between 0 and 1");
        }
        if (list == null && innerRounding != null) {
            Iterable $this$flatMap$iv = RangesKt.until(0, numVerticesPerRadius);
            Collection destination$iv$iv = new ArrayList();
            Iterator<Integer> it = $this$flatMap$iv.iterator();
            while (it.hasNext()) {
                ((IntIterator) it).nextInt();
                Iterable list$iv$iv = CollectionsKt.listOf((Object[]) new CornerRounding[]{rounding, innerRounding});
                CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
            }
            List pvRounding2 = (List) destination$iv$iv;
            pvRounding = pvRounding2;
        } else {
            pvRounding = list;
        }
        return RoundedPolygonKt.RoundedPolygon(pillStarVerticesFromNumVerts(numVerticesPerRadius, width, height, innerRadiusRatio, vertexSpacing, startLocation, centerX, centerY), rounding, (List<CornerRounding>) pvRounding, centerX, centerY);
    }

    private static final float[] pillStarVerticesFromNumVerts(int numVerticesPerRadius, float width, float height, float innerRadius, float vertexSpacing, float startLocation, float centerX, float centerY) {
        float endcapRadius;
        float[] sections;
        long rectBL;
        long rectTR;
        float hSegLen;
        long rectBR;
        long vertex;
        float f = innerRadius;
        float endcapRadius2 = Math.min(width, height);
        float vSegLen = RangesKt.coerceAtLeast(height - width, 0.0f);
        float hSegLen2 = RangesKt.coerceAtLeast(width - height, 0.0f);
        float f2 = 2;
        float vSegHalf = vSegLen / f2;
        float hSegHalf = hSegLen2 / f2;
        float circlePerimeter = Utils.getTwoPi() * endcapRadius2 * Utils.interpolate(f, 1.0f, vertexSpacing);
        float perimeter = (f2 * hSegLen2) + (f2 * vSegLen) + circlePerimeter;
        float[] sections2 = new float[11];
        sections2[0] = 0.0f;
        sections2[1] = vSegLen / f2;
        float f3 = 4;
        sections2[2] = sections2[1] + (circlePerimeter / f3);
        sections2[3] = sections2[2] + hSegLen2;
        sections2[4] = sections2[3] + (circlePerimeter / f3);
        sections2[5] = sections2[4] + vSegLen;
        sections2[6] = sections2[5] + (circlePerimeter / f3);
        sections2[7] = sections2[6] + hSegLen2;
        sections2[8] = sections2[7] + (circlePerimeter / f3);
        sections2[9] = sections2[8] + (vSegLen / f2);
        sections2[10] = perimeter;
        float tPerVertex = perimeter / (numVerticesPerRadius * 2);
        float secEnd = sections2[1];
        float t = startLocation * perimeter;
        float[] result = new float[numVerticesPerRadius * 4];
        int currSecIndex = 0;
        long rectBR2 = FloatFloatPair.m12constructorimpl(hSegHalf, vSegHalf);
        boolean inner = false;
        long rectBR3 = rectBR2;
        long rectBL2 = FloatFloatPair.m12constructorimpl(-hSegHalf, vSegHalf);
        long rectBL3 = rectBL2;
        long rectTL = FloatFloatPair.m12constructorimpl(-hSegHalf, -vSegHalf);
        long rectTR2 = FloatFloatPair.m12constructorimpl(hSegHalf, -vSegHalf);
        int i = numVerticesPerRadius * 2;
        float secStart = 0.0f;
        int i2 = 0;
        int i3 = 0;
        float t2 = t;
        float secEnd2 = secEnd;
        while (i2 < i) {
            float boundedT = t2 % perimeter;
            if (boundedT < secStart) {
                currSecIndex = 0;
            }
            while (true) {
                int i4 = i;
                if (boundedT >= sections2[(currSecIndex + 1) % sections2.length]) {
                    currSecIndex = (currSecIndex + 1) % sections2.length;
                    secStart = sections2[currSecIndex];
                    secEnd2 = sections2[(currSecIndex + 1) % sections2.length];
                    perimeter = perimeter;
                    i = i4;
                } else {
                    float perimeter2 = perimeter;
                    float tInSection = boundedT - secStart;
                    float tProportion = tInSection / (secEnd2 - secStart);
                    float currRadius = inner ? endcapRadius2 * f : endcapRadius2;
                    switch (currSecIndex) {
                        case 0:
                            endcapRadius = endcapRadius2;
                            sections = sections2;
                            rectBL = rectBL3;
                            rectTR = rectTR2;
                            hSegLen = hSegLen2;
                            rectBR = rectBR3;
                            vertex = FloatFloatPair.m12constructorimpl(currRadius, tProportion * vSegHalf);
                            break;
                        case 1:
                            endcapRadius = endcapRadius2;
                            sections = sections2;
                            rectBL = rectBL3;
                            rectTR = rectTR2;
                            hSegLen = hSegLen2;
                            rectBR = rectBR3;
                            vertex = PointKt.m120plusybeJwSQ(Utils.m136radialToCartesianL6JJ3z0$default(currRadius, (Utils.getFloatPi() * tProportion) / f2, 0L, 4, null), rectBR);
                            break;
                        case 2:
                            endcapRadius = endcapRadius2;
                            sections = sections2;
                            rectBL = rectBL3;
                            rectTR = rectTR2;
                            vertex = FloatFloatPair.m12constructorimpl(hSegHalf - (tProportion * hSegLen2), currRadius);
                            hSegLen = hSegLen2;
                            rectBR = rectBR3;
                            break;
                        case 3:
                            endcapRadius = endcapRadius2;
                            rectTR = rectTR2;
                            sections = sections2;
                            rectBL = rectBL3;
                            vertex = PointKt.m120plusybeJwSQ(Utils.m136radialToCartesianL6JJ3z0$default(currRadius, (Utils.getFloatPi() / f2) + ((Utils.getFloatPi() * tProportion) / f2), 0L, 4, null), rectBL);
                            hSegLen = hSegLen2;
                            rectBR = rectBR3;
                            break;
                        case 4:
                            endcapRadius = endcapRadius2;
                            rectTR = rectTR2;
                            vertex = FloatFloatPair.m12constructorimpl(-currRadius, vSegHalf - (tProportion * vSegLen));
                            sections = sections2;
                            rectBL = rectBL3;
                            hSegLen = hSegLen2;
                            rectBR = rectBR3;
                            break;
                        case 5:
                            endcapRadius = endcapRadius2;
                            rectTR = rectTR2;
                            vertex = PointKt.m120plusybeJwSQ(Utils.m136radialToCartesianL6JJ3z0$default(currRadius, Utils.getFloatPi() + ((Utils.getFloatPi() * tProportion) / f2), 0L, 4, null), rectTL);
                            sections = sections2;
                            rectBL = rectBL3;
                            hSegLen = hSegLen2;
                            rectBR = rectBR3;
                            break;
                        case 6:
                            endcapRadius = endcapRadius2;
                            rectTR = rectTR2;
                            vertex = FloatFloatPair.m12constructorimpl((-hSegHalf) + (tProportion * hSegLen2), -currRadius);
                            sections = sections2;
                            rectBL = rectBL3;
                            hSegLen = hSegLen2;
                            rectBR = rectBR3;
                            break;
                        case 7:
                            endcapRadius = endcapRadius2;
                            long m120plusybeJwSQ = PointKt.m120plusybeJwSQ(Utils.m136radialToCartesianL6JJ3z0$default(currRadius, (Utils.getFloatPi() * 1.5f) + ((Utils.getFloatPi() * tProportion) / f2), 0L, 4, null), rectTR2);
                            long j = rectBL3;
                            hSegLen = hSegLen2;
                            rectBR = rectBR3;
                            sections = sections2;
                            rectBL = j;
                            rectTR = rectTR2;
                            vertex = m120plusybeJwSQ;
                            break;
                        default:
                            endcapRadius = endcapRadius2;
                            sections = sections2;
                            rectBL = rectBL3;
                            rectTR = rectTR2;
                            hSegLen = hSegLen2;
                            rectBR = rectBR3;
                            vertex = FloatFloatPair.m12constructorimpl(currRadius, (-vSegHalf) + (tProportion * vSegHalf));
                            break;
                    }
                    int arrayIndex = i3 + 1;
                    result[i3] = PointKt.m116getXDnnuFBc(vertex) + centerX;
                    i3 = arrayIndex + 1;
                    result[arrayIndex] = PointKt.m117getYDnnuFBc(vertex) + centerY;
                    t2 += tPerVertex;
                    inner = !inner;
                    i2++;
                    rectBR3 = rectBR;
                    hSegLen2 = hSegLen;
                    perimeter = perimeter2;
                    i = i4;
                    endcapRadius2 = endcapRadius;
                    rectTR2 = rectTR;
                    rectBL3 = rectBL;
                    sections2 = sections;
                    f = innerRadius;
                }
            }
        }
        return result;
    }

    private static final float[] starVerticesFromNumVerts(int numVerticesPerRadius, float radius, float innerRadius, float centerX, float centerY) {
        float[] result = new float[numVerticesPerRadius * 4];
        int arrayIndex = 0;
        for (int i = 0; i < numVerticesPerRadius; i++) {
            long vertex = Utils.m136radialToCartesianL6JJ3z0$default(radius, (Utils.getFloatPi() / numVerticesPerRadius) * 2 * i, 0L, 4, null);
            int arrayIndex2 = arrayIndex + 1;
            result[arrayIndex] = PointKt.m116getXDnnuFBc(vertex) + centerX;
            int arrayIndex3 = arrayIndex2 + 1;
            result[arrayIndex2] = PointKt.m117getYDnnuFBc(vertex) + centerY;
            long vertex2 = Utils.m136radialToCartesianL6JJ3z0$default(innerRadius, (Utils.getFloatPi() / numVerticesPerRadius) * ((i * 2) + 1), 0L, 4, null);
            int arrayIndex4 = arrayIndex3 + 1;
            result[arrayIndex3] = PointKt.m116getXDnnuFBc(vertex2) + centerX;
            arrayIndex = arrayIndex4 + 1;
            result[arrayIndex4] = PointKt.m117getYDnnuFBc(vertex2) + centerY;
        }
        return result;
    }
}
