package androidx.graphics.shapes;

import androidx.collection.FloatList;
import androidx.collection.MutableFloatList;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.graphics.shapes.Feature;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.AbstractList;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: PolygonMeasure.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00182\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002\u0018\u0019B3\b\u0012\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0015J\u0015\u0010\u0016\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0010H\u0096\u0002R\u0018\u0010\b\u001a\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001a"}, d2 = {"Landroidx/graphics/shapes/MeasuredPolygon;", "Lkotlin/collections/AbstractList;", "Landroidx/graphics/shapes/MeasuredPolygon$MeasuredCubic;", "measurer", "Landroidx/graphics/shapes/Measurer;", "features", "", "Landroidx/graphics/shapes/ProgressableFeature;", "cubics", "Landroidx/graphics/shapes/Cubic;", "outlineProgress", "Landroidx/collection/FloatList;", "(Landroidx/graphics/shapes/Measurer;Ljava/util/List;Ljava/util/List;Landroidx/collection/FloatList;)V", "getFeatures", "()Ljava/util/List;", "size", "", "getSize", "()I", "cutAndShift", "cuttingPoint", "", Constant.RetrofitConstants.RETROFIT_METHOD_GET, FirebaseAnalytics.Param.INDEX, "Companion", "MeasuredCubic", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class MeasuredPolygon extends AbstractList<MeasuredCubic> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<MeasuredCubic> cubics;
    private final List<ProgressableFeature> features;
    private final Measurer measurer;

    public /* synthetic */ MeasuredPolygon(Measurer measurer, List list, List list2, FloatList floatList, DefaultConstructorMarker defaultConstructorMarker) {
        this(measurer, list, list2, floatList);
    }

    public /* bridge */ boolean contains(MeasuredCubic element) {
        return super.contains((MeasuredPolygon) element);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object element) {
        if (element instanceof MeasuredCubic) {
            return contains((MeasuredCubic) element);
        }
        return false;
    }

    public /* bridge */ int indexOf(MeasuredCubic element) {
        return super.indexOf((MeasuredPolygon) element);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object element) {
        if (element instanceof MeasuredCubic) {
            return indexOf((MeasuredCubic) element);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(MeasuredCubic element) {
        return super.lastIndexOf((MeasuredPolygon) element);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object element) {
        if (element instanceof MeasuredCubic) {
            return lastIndexOf((MeasuredCubic) element);
        }
        return -1;
    }

    public final List<ProgressableFeature> getFeatures() {
        return this.features;
    }

    private MeasuredPolygon(Measurer measurer, List<ProgressableFeature> list, List<? extends Cubic> list2, FloatList outlineProgress) {
        if (!(outlineProgress.getSize() == list2.size() + 1)) {
            throw new IllegalArgumentException("Outline progress size is expected to be the cubics size + 1".toString());
        }
        if (!(outlineProgress.first() == 0.0f)) {
            throw new IllegalArgumentException("First outline progress value is expected to be zero".toString());
        }
        if (!(outlineProgress.last() == 1.0f)) {
            throw new IllegalArgumentException("Last outline progress value is expected to be one".toString());
        }
        this.measurer = measurer;
        this.features = list;
        List measuredCubics = new ArrayList();
        float startOutlineProgress = 0.0f;
        int size = list2.size();
        for (int index = 0; index < size; index++) {
            if (outlineProgress.get(index + 1) - outlineProgress.get(index) > 1.0E-4f) {
                measuredCubics.add(new MeasuredCubic(this, list2.get(index), startOutlineProgress, outlineProgress.get(index + 1)));
                startOutlineProgress = outlineProgress.get(index + 1);
            }
        }
        int index2 = CollectionsKt.getLastIndex(measuredCubics);
        MeasuredCubic.updateProgressRange$graphics_shapes_release$default((MeasuredCubic) measuredCubics.get(index2), 0.0f, 1.0f, 1, null);
        this.cubics = measuredCubics;
    }

    /* compiled from: PolygonMeasure.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\"\u0010\u0010\u001a\u0016\u0012\b\u0012\u00060\u0000R\u00020\u0012\u0012\b\u0012\u00060\u0000R\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0005J\b\u0010\u0014\u001a\u00020\u0015H\u0016J!\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\f¨\u0006\u0019"}, d2 = {"Landroidx/graphics/shapes/MeasuredPolygon$MeasuredCubic;", "", "cubic", "Landroidx/graphics/shapes/Cubic;", "startOutlineProgress", "", "endOutlineProgress", "(Landroidx/graphics/shapes/MeasuredPolygon;Landroidx/graphics/shapes/Cubic;FF)V", "getCubic", "()Landroidx/graphics/shapes/Cubic;", "<set-?>", "getEndOutlineProgress", "()F", "measuredSize", "getMeasuredSize", "getStartOutlineProgress", "cutAtProgress", "Lkotlin/Pair;", "Landroidx/graphics/shapes/MeasuredPolygon;", "cutOutlineProgress", "toString", "", "updateProgressRange", "", "updateProgressRange$graphics_shapes_release", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class MeasuredCubic {
        private final Cubic cubic;
        private float endOutlineProgress;

        /* renamed from: measuredSize, reason: from kotlin metadata and from toString */
        private final float size;

        /* renamed from: startOutlineProgress, reason: from kotlin metadata and from toString */
        private float outlineProgress;
        final /* synthetic */ MeasuredPolygon this$0;

        public MeasuredCubic(MeasuredPolygon this$0, Cubic cubic, float startOutlineProgress, float endOutlineProgress) {
            Intrinsics.checkNotNullParameter(cubic, "cubic");
            this.this$0 = this$0;
            this.cubic = cubic;
            if (endOutlineProgress >= startOutlineProgress) {
                this.size = this.this$0.measurer.measureCubic(this.cubic);
                this.outlineProgress = startOutlineProgress;
                this.endOutlineProgress = endOutlineProgress;
                return;
            }
            throw new IllegalArgumentException("endOutlineProgress is expected to be equal or greater than startOutlineProgress".toString());
        }

        public final Cubic getCubic() {
            return this.cubic;
        }

        /* renamed from: getMeasuredSize, reason: from getter */
        public final float getSize() {
            return this.size;
        }

        /* renamed from: getStartOutlineProgress, reason: from getter */
        public final float getOutlineProgress() {
            return this.outlineProgress;
        }

        public final float getEndOutlineProgress() {
            return this.endOutlineProgress;
        }

        public static /* synthetic */ void updateProgressRange$graphics_shapes_release$default(MeasuredCubic measuredCubic, float f, float f2, int i, Object obj) {
            if ((i & 1) != 0) {
                f = measuredCubic.outlineProgress;
            }
            if ((i & 2) != 0) {
                f2 = measuredCubic.endOutlineProgress;
            }
            measuredCubic.updateProgressRange$graphics_shapes_release(f, f2);
        }

        public final void updateProgressRange$graphics_shapes_release(float startOutlineProgress, float endOutlineProgress) {
            if (!(endOutlineProgress >= startOutlineProgress)) {
                throw new IllegalArgumentException("endOutlineProgress is expected to be equal or greater than startOutlineProgress".toString());
            }
            this.outlineProgress = startOutlineProgress;
            this.endOutlineProgress = endOutlineProgress;
        }

        public final Pair<MeasuredCubic, MeasuredCubic> cutAtProgress(float cutOutlineProgress) {
            String unused;
            float boundedCutOutlineProgress = RangesKt.coerceIn(cutOutlineProgress, this.outlineProgress, this.endOutlineProgress);
            float outlineProgressSize = this.endOutlineProgress - this.outlineProgress;
            float progressFromStart = boundedCutOutlineProgress - this.outlineProgress;
            float relativeProgress = progressFromStart / outlineProgressSize;
            float t = this.this$0.measurer.findCubicCutPoint(this.cubic, this.size * relativeProgress);
            boolean z = false;
            if (0.0f <= t && t <= 1.0f) {
                z = true;
            }
            if (!z) {
                throw new IllegalArgumentException("Cubic cut point is expected to be between 0 and 1".toString());
            }
            unused = PolygonMeasureKt.LOG_TAG;
            Pair<Cubic, Cubic> split = this.cubic.split(t);
            Cubic c1 = split.component1();
            Cubic c2 = split.component2();
            return TuplesKt.to(new MeasuredCubic(this.this$0, c1, this.outlineProgress, boundedCutOutlineProgress), new MeasuredCubic(this.this$0, c2, boundedCutOutlineProgress, this.endOutlineProgress));
        }

        public String toString() {
            return "MeasuredCubic(outlineProgress=[" + this.outlineProgress + " .. " + this.endOutlineProgress + "], size=" + this.size + ", cubic=" + this.cubic + ')';
        }
    }

    public final MeasuredPolygon cutAndShift(float cuttingPoint) {
        float positiveModulo;
        String unused;
        float f = 1.0f;
        if (!(0.0f <= cuttingPoint && cuttingPoint <= 1.0f)) {
            throw new IllegalArgumentException("Cutting point is expected to be between 0 and 1".toString());
        }
        if (cuttingPoint < 1.0E-4f) {
            return this;
        }
        List $this$indexOfFirst$iv = this.cubics;
        int index$iv = 0;
        Iterator<MeasuredCubic> it = $this$indexOfFirst$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object item$iv = it.next();
                MeasuredCubic it2 = (MeasuredCubic) item$iv;
                if (((cuttingPoint > it2.getEndOutlineProgress() || it2.getOutlineProgress() > cuttingPoint) ? null : 1) != null) {
                    break;
                }
                index$iv++;
            } else {
                index$iv = -1;
                break;
            }
        }
        int targetIndex = index$iv;
        MeasuredCubic target = this.cubics.get(targetIndex);
        Pair<MeasuredCubic, MeasuredCubic> cutAtProgress = target.cutAtProgress(cuttingPoint);
        MeasuredCubic b1 = cutAtProgress.component1();
        MeasuredCubic b2 = cutAtProgress.component2();
        unused = PolygonMeasureKt.LOG_TAG;
        List retCubics = CollectionsKt.mutableListOf(b2.getCubic());
        int size = this.cubics.size();
        for (int i = 1; i < size; i++) {
            retCubics.add(this.cubics.get((i + targetIndex) % this.cubics.size()).getCubic());
        }
        retCubics.add(b1.getCubic());
        MutableFloatList retOutlineProgress = new MutableFloatList(this.cubics.size() + 2);
        int size2 = this.cubics.size() + 2;
        for (int index = 0; index < size2; index++) {
            if (index == 0) {
                positiveModulo = 0.0f;
            } else if (index == this.cubics.size() + 1) {
                positiveModulo = 1.0f;
            } else {
                int cubicIndex = ((targetIndex + index) - 1) % this.cubics.size();
                positiveModulo = Utils.positiveModulo(this.cubics.get(cubicIndex).getEndOutlineProgress() - cuttingPoint, 1.0f);
            }
            retOutlineProgress.add(positiveModulo);
        }
        List $this$cutAndShift_u24lambda_u2410 = CollectionsKt.createListBuilder();
        int i2 = 0;
        int size3 = this.features.size();
        while (i2 < size3) {
            $this$cutAndShift_u24lambda_u2410.add(new ProgressableFeature(Utils.positiveModulo(this.features.get(i2).getProgress() - cuttingPoint, f), this.features.get(i2).getFeature()));
            i2++;
            f = 1.0f;
        }
        List newFeatures = CollectionsKt.build($this$cutAndShift_u24lambda_u2410);
        return new MeasuredPolygon(this.measurer, newFeatures, retCubics, retOutlineProgress);
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    /* renamed from: getSize */
    public int get_size() {
        return this.cubics.size();
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public MeasuredCubic get(int index) {
        return this.cubics.get(index);
    }

    /* compiled from: PolygonMeasure.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"Landroidx/graphics/shapes/MeasuredPolygon$Companion;", "", "()V", "measurePolygon", "Landroidx/graphics/shapes/MeasuredPolygon;", "measurer", "Landroidx/graphics/shapes/Measurer;", "polygon", "Landroidx/graphics/shapes/RoundedPolygon;", "measurePolygon$graphics_shapes_release", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MeasuredPolygon measurePolygon$graphics_shapes_release(Measurer measurer, RoundedPolygon polygon) {
            ArrayList arrayList;
            String unused;
            Intrinsics.checkNotNullParameter(measurer, "measurer");
            Intrinsics.checkNotNullParameter(polygon, "polygon");
            List cubics = new ArrayList();
            List featureToCubic = new ArrayList();
            int size = polygon.getFeatures$graphics_shapes_release().size();
            for (int featureIndex = 0; featureIndex < size; featureIndex++) {
                Feature feature = polygon.getFeatures$graphics_shapes_release().get(featureIndex);
                int size2 = feature.getCubics().size();
                for (int cubicIndex = 0; cubicIndex < size2; cubicIndex++) {
                    if ((feature instanceof Feature.Corner) && cubicIndex == feature.getCubics().size() / 2) {
                        featureToCubic.add(TuplesKt.to(feature, Integer.valueOf(cubics.size())));
                    }
                    cubics.add(feature.getCubics().get(cubicIndex));
                }
            }
            List $this$scan$iv = cubics;
            Float valueOf = Float.valueOf(0.0f);
            int estimatedSize$iv$iv = CollectionsKt.collectionSizeOrDefault($this$scan$iv, 9);
            if (estimatedSize$iv$iv == 0) {
                arrayList = CollectionsKt.listOf(valueOf);
            } else {
                ArrayList result$iv$iv = new ArrayList(estimatedSize$iv$iv + 1);
                result$iv$iv.add(valueOf);
                Float f = valueOf;
                for (Object element$iv$iv : $this$scan$iv) {
                    Cubic cubic = (Cubic) element$iv$iv;
                    float measure = f.floatValue();
                    float it = measurer.measureCubic(cubic);
                    if (it >= 0.0f) {
                        Unit unit = Unit.INSTANCE;
                        f = Float.valueOf(measure + it);
                        result$iv$iv.add(f);
                    } else {
                        throw new IllegalArgumentException("Measured cubic is expected to be greater or equal to zero".toString());
                    }
                }
                arrayList = result$iv$iv;
            }
            List measures = arrayList;
            float totalMeasure = ((Number) CollectionsKt.last(measures)).floatValue();
            MutableFloatList outlineProgress = new MutableFloatList(measures.size());
            int size3 = measures.size();
            for (int i = 0; i < size3; i++) {
                outlineProgress.add(((Number) measures.get(i)).floatValue() / totalMeasure);
            }
            unused = PolygonMeasureKt.LOG_TAG;
            List $this$measurePolygon_u24lambda_u244 = CollectionsKt.createListBuilder();
            int i2 = 0;
            int i3 = 0;
            int size4 = featureToCubic.size();
            while (i3 < size4) {
                int ix = ((Number) ((Pair) featureToCubic.get(i3)).getSecond()).intValue();
                $this$measurePolygon_u24lambda_u244.add(new ProgressableFeature((outlineProgress.get(ix) + outlineProgress.get(ix + 1)) / 2, (Feature) ((Pair) featureToCubic.get(i3)).getFirst()));
                i3++;
                i2 = i2;
            }
            List features = CollectionsKt.build($this$measurePolygon_u24lambda_u244);
            return new MeasuredPolygon(measurer, features, cubics, outlineProgress, null);
        }
    }
}
