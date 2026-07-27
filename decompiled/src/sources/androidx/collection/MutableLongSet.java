package androidx.collection;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LongSet.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0015\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0001J\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\r\u001a\u00020\u000eH\u0002J\u0006\u0010\u000f\u001a\u00020\u000eJ\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0003H\u0002J\b\u0010\u0013\u001a\u00020\u000eH\u0002J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0003H\u0002J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0011\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u0001H\u0086\u0002J\u0011\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\tH\u0086\u0002J\u0011\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0086\u0002J\u0011\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u0001H\u0086\u0002J\u0011\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\tH\u0086\u0002J\u0011\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0086\u0002J\u000e\u0010\u0019\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0001J\u000e\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\u001b\u001a\u00020\u000eH\u0002J\u0010\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u0003H\u0002J\u0010\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u0003H\u0002J\b\u0010 \u001a\u00020\u0003H\u0007J\u0019\u0010!\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\tH\u0082\bR\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/collection/MutableLongSet;", "Landroidx/collection/LongSet;", "initialCapacity", "", "(I)V", "growthLimit", "add", "", "element", "", "addAll", "elements", "", "adjustStorage", "", "clear", "findAbsoluteInsertIndex", "findFirstAvailableSlot", "hash1", "initializeGrowth", "initializeMetadata", "capacity", "initializeStorage", "minusAssign", "plusAssign", "remove", "removeAll", "removeDeletedMarkers", "removeElementAt", FirebaseAnalytics.Param.INDEX, "resizeStorage", "newCapacity", "trim", "writeMetadata", "value", "collection"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class MutableLongSet extends LongSet {
    private int growthLimit;

    public MutableLongSet() {
        this(0, 1, null);
    }

    public /* synthetic */ MutableLongSet(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 6 : i);
    }

    public MutableLongSet(int initialCapacity) {
        super(null);
        if (!(initialCapacity >= 0)) {
            throw new IllegalArgumentException("Capacity must be a positive value.".toString());
        }
        initializeStorage(ScatterMapKt.unloadedCapacity(initialCapacity));
    }

    private final void initializeStorage(int initialCapacity) {
        int newCapacity;
        if (initialCapacity > 0) {
            newCapacity = Math.max(7, ScatterMapKt.normalizeCapacity(initialCapacity));
        } else {
            newCapacity = 0;
        }
        this._capacity = newCapacity;
        initializeMetadata(newCapacity);
        this.elements = new long[newCapacity];
    }

    private final void initializeMetadata(int capacity) {
        long[] jArr;
        if (capacity == 0) {
            jArr = ScatterMapKt.EmptyGroup;
        } else {
            int size = ((((capacity + 1) + 7) + 7) & (-8)) >> 3;
            long[] $this$initializeMetadata_u24lambda_u241 = new long[size];
            ArraysKt.fill$default($this$initializeMetadata_u24lambda_u241, -9187201950435737472L, 0, 0, 6, (Object) null);
            jArr = $this$initializeMetadata_u24lambda_u241;
        }
        this.metadata = jArr;
        long[] data$iv = this.metadata;
        int i$iv = capacity >> 3;
        int b$iv = (capacity & 7) << 3;
        data$iv[i$iv] = (data$iv[i$iv] & (~(255 << b$iv))) | (255 << b$iv);
        initializeGrowth();
    }

    private final void initializeGrowth() {
        this.growthLimit = ScatterMapKt.loadedCapacity(get_capacity()) - this._size;
    }

    public final boolean add(long element) {
        int oldSize = this._size;
        int index = findAbsoluteInsertIndex(element);
        this.elements[index] = element;
        return this._size != oldSize;
    }

    public final void plusAssign(long element) {
        int index = findAbsoluteInsertIndex(element);
        this.elements[index] = element;
    }

    public final boolean addAll(long[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int oldSize = this._size;
        plusAssign(elements);
        return oldSize != this._size;
    }

    public final void plusAssign(long[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        for (long element$iv : elements) {
            plusAssign(element$iv);
        }
    }

    public final boolean addAll(LongSet elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int oldSize = this._size;
        plusAssign(elements);
        return oldSize != this._size;
    }

    public final void plusAssign(LongSet elements) {
        LongSet this_$iv;
        LongSet this_$iv2;
        int j$iv$iv;
        Intrinsics.checkNotNullParameter(elements, "elements");
        LongSet this_$iv3 = elements;
        long[] k$iv = this_$iv3.elements;
        long[] m$iv$iv = this_$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                this_$iv = this_$iv3;
            } else {
                int i = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv2 = 0;
                while (j$iv$iv2 < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        this_$iv2 = this_$iv3;
                        j$iv$iv = j$iv$iv2;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv2;
                        j$iv$iv = j$iv$iv2;
                        long element = k$iv[index$iv$iv];
                        this_$iv2 = this_$iv3;
                        plusAssign(element);
                    }
                    i = 8;
                    slot$iv$iv >>= 8;
                    j$iv$iv2 = j$iv$iv + 1;
                    this_$iv3 = this_$iv2;
                }
                this_$iv = this_$iv3;
                if (bitCount$iv$iv != i) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            this_$iv3 = this_$iv;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x008d, code lost:
    
        r10 = (((~r2) << 6) & r2) & (-9187201950435737472L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x009c, code lost:
    
        if (r10 == 0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x009f, code lost:
    
        r10 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean remove(long element) {
        int index$iv;
        MutableLongSet this_$iv = this;
        int $i$f$findElementIndex$collection = 0;
        int hash$iv$iv = Long.hashCode(element) * ScatterMapKt.MurmurHashC1;
        int hash$iv = hash$iv$iv ^ (hash$iv$iv << 16);
        int $i$f$h2 = hash$iv & 127;
        int probeMask$iv = this_$iv._capacity;
        int $i$f$h1 = hash$iv >>> 7;
        int probeOffset$iv = $i$f$h1 & probeMask$iv;
        int probeIndex$iv = 0;
        loop0: while (true) {
            long[] metadata$iv$iv = this_$iv.metadata;
            int i$iv$iv = probeOffset$iv >> 3;
            int b$iv$iv = (probeOffset$iv & 7) << 3;
            int $i$f$findElementIndex$collection2 = $i$f$findElementIndex$collection;
            int hash$iv2 = hash$iv;
            long g$iv = (((-b$iv$iv) >> 63) & (metadata$iv$iv[i$iv$iv + 1] << (64 - b$iv$iv))) | (metadata$iv$iv[i$iv$iv] >>> b$iv$iv);
            long x$iv$iv = ($i$f$h2 * ScatterMapKt.BitmaskLsb) ^ g$iv;
            long j = x$iv$iv - ScatterMapKt.BitmaskLsb;
            long $this$match$iv$iv = ~x$iv$iv;
            long m$iv = $this$match$iv$iv & j & (-9187201950435737472L);
            while (true) {
                long $this$hasNext$iv$iv = m$iv;
                if (!($this$hasNext$iv$iv != 0)) {
                    break;
                }
                long $this$get$iv$iv = m$iv;
                int $i$f$lowestBitSet = Long.numberOfTrailingZeros($this$get$iv$iv) >> 3;
                index$iv = ($i$f$lowestBitSet + probeOffset$iv) & probeMask$iv;
                if (this_$iv.elements[index$iv] == element) {
                    break loop0;
                }
                long $this$next$iv$iv = m$iv;
                m$iv = $this$next$iv$iv & ($this$next$iv$iv - 1);
            }
            probeIndex$iv += 8;
            probeOffset$iv = (probeOffset$iv + probeIndex$iv) & probeMask$iv;
            $i$f$findElementIndex$collection = $i$f$findElementIndex$collection2;
            hash$iv = hash$iv2;
        }
        int index = index$iv;
        boolean exists = index >= 0;
        if (exists) {
            removeElementAt(index);
        }
        return exists;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0087, code lost:
    
        r10 = (((~r2) << 6) & r2) & (-9187201950435737472L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0096, code lost:
    
        if (r10 == 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0099, code lost:
    
        r10 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void minusAssign(long element) {
        int index$iv;
        MutableLongSet this_$iv = this;
        int $i$f$findElementIndex$collection = 0;
        int hash$iv$iv = Long.hashCode(element) * ScatterMapKt.MurmurHashC1;
        int hash$iv = hash$iv$iv ^ (hash$iv$iv << 16);
        int $i$f$h2 = hash$iv & 127;
        int probeMask$iv = this_$iv._capacity;
        int $i$f$h1 = hash$iv >>> 7;
        int probeOffset$iv = $i$f$h1 & probeMask$iv;
        int probeIndex$iv = 0;
        loop0: while (true) {
            long[] metadata$iv$iv = this_$iv.metadata;
            int i$iv$iv = probeOffset$iv >> 3;
            int b$iv$iv = (probeOffset$iv & 7) << 3;
            int $i$f$findElementIndex$collection2 = $i$f$findElementIndex$collection;
            int hash$iv2 = hash$iv;
            long g$iv = (((-b$iv$iv) >> 63) & (metadata$iv$iv[i$iv$iv + 1] << (64 - b$iv$iv))) | (metadata$iv$iv[i$iv$iv] >>> b$iv$iv);
            long x$iv$iv = ($i$f$h2 * ScatterMapKt.BitmaskLsb) ^ g$iv;
            long j = x$iv$iv - ScatterMapKt.BitmaskLsb;
            long $this$match$iv$iv = ~x$iv$iv;
            long m$iv = $this$match$iv$iv & j & (-9187201950435737472L);
            while (true) {
                long $this$hasNext$iv$iv = m$iv;
                if (!($this$hasNext$iv$iv != 0)) {
                    break;
                }
                long $this$get$iv$iv = m$iv;
                int $i$f$lowestBitSet = Long.numberOfTrailingZeros($this$get$iv$iv) >> 3;
                index$iv = ($i$f$lowestBitSet + probeOffset$iv) & probeMask$iv;
                if (this_$iv.elements[index$iv] == element) {
                    break loop0;
                }
                long $this$next$iv$iv = m$iv;
                m$iv = $this$next$iv$iv & ($this$next$iv$iv - 1);
            }
            probeIndex$iv += 8;
            probeOffset$iv = (probeOffset$iv + probeIndex$iv) & probeMask$iv;
            $i$f$findElementIndex$collection = $i$f$findElementIndex$collection2;
            hash$iv = hash$iv2;
        }
        int index = index$iv;
        if (index >= 0) {
            removeElementAt(index);
        }
    }

    public final boolean removeAll(long[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int oldSize = this._size;
        minusAssign(elements);
        return oldSize != this._size;
    }

    public final void minusAssign(long[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        for (long element$iv : elements) {
            minusAssign(element$iv);
        }
    }

    public final boolean removeAll(LongSet elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int oldSize = this._size;
        minusAssign(elements);
        return oldSize != this._size;
    }

    public final void minusAssign(LongSet elements) {
        LongSet this_$iv;
        LongSet this_$iv2;
        int j$iv$iv;
        Intrinsics.checkNotNullParameter(elements, "elements");
        LongSet this_$iv3 = elements;
        long[] k$iv = this_$iv3.elements;
        long[] m$iv$iv = this_$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                this_$iv = this_$iv3;
            } else {
                int i = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv2 = 0;
                while (j$iv$iv2 < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        this_$iv2 = this_$iv3;
                        j$iv$iv = j$iv$iv2;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv2;
                        j$iv$iv = j$iv$iv2;
                        long element = k$iv[index$iv$iv];
                        this_$iv2 = this_$iv3;
                        minusAssign(element);
                    }
                    i = 8;
                    slot$iv$iv >>= 8;
                    j$iv$iv2 = j$iv$iv + 1;
                    this_$iv3 = this_$iv2;
                }
                this_$iv = this_$iv3;
                if (bitCount$iv$iv != i) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            this_$iv3 = this_$iv;
        }
    }

    private final void removeElementAt(int index) {
        this._size--;
        long[] m$iv = this.metadata;
        int i$iv$iv = index >> 3;
        int b$iv$iv = (index & 7) << 3;
        m$iv[i$iv$iv] = (m$iv[i$iv$iv] & (~(255 << b$iv$iv))) | (254 << b$iv$iv);
        int $i$f$writeRawMetadata = this._capacity;
        int cloneIndex$iv = ((index - 7) & $i$f$writeRawMetadata) + ($i$f$writeRawMetadata & 7);
        int i$iv$iv2 = cloneIndex$iv >> 3;
        int b$iv$iv2 = (cloneIndex$iv & 7) << 3;
        m$iv[i$iv$iv2] = ((~(255 << b$iv$iv2)) & m$iv[i$iv$iv2]) | (254 << b$iv$iv2);
    }

    public final void clear() {
        this._size = 0;
        if (this.metadata != ScatterMapKt.EmptyGroup) {
            ArraysKt.fill$default(this.metadata, -9187201950435737472L, 0, 0, 6, (Object) null);
            long[] data$iv = this.metadata;
            int offset$iv = this._capacity;
            int i$iv = offset$iv >> 3;
            int b$iv = (offset$iv & 7) << 3;
            data$iv[i$iv] = (data$iv[i$iv] & (~(255 << b$iv))) | (255 << b$iv);
        }
        initializeGrowth();
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x008c, code lost:
    
        r11 = (((~r7) << 6) & r7) & (-9187201950435737472L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x009d, code lost:
    
        if (r11 == 0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00a0, code lost:
    
        r7 = r0.findFirstAvailableSlot(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00a8, code lost:
    
        if (r0.growthLimit != 0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00bd, code lost:
    
        if (((r0.metadata[r7 >> 3] >> ((r7 & 7) << 3)) & 255) != 254) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00bf, code lost:
    
        r8 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00c4, code lost:
    
        if (r8 != null) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00c6, code lost:
    
        adjustStorage();
        r7 = r0.findFirstAvailableSlot(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00c2, code lost:
    
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00cd, code lost:
    
        r0._size++;
        r8 = r0.growthLimit;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00e9, code lost:
    
        if (((r0.metadata[r7 >> 3] >> ((r7 & 7) << 3)) & 255) != 128) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00eb, code lost:
    
        r19 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00ed, code lost:
    
        r0.growthLimit = r8 - r19;
        r11 = r3;
        r14 = r23.metadata;
        r16 = r7 >> 3;
        r17 = (r7 & 7) << 3;
        r14[r16] = (r14[r16] & (~(255 << r17))) | (r11 << r17);
        r0 = r23._capacity;
        r1 = ((r7 - 7) & r0) + (r0 & 7);
        r16 = r1 >> 3;
        r17 = (r1 & 7) << 3;
        r14[r16] = (r14[r16] & (~(255 << r17))) | (r11 << r17);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x012c, code lost:
    
        return r7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int findAbsoluteInsertIndex(long element) {
        MutableLongSet mutableLongSet = this;
        int hash$iv = Long.hashCode(element) * ScatterMapKt.MurmurHashC1;
        int $i$f$hash = hash$iv ^ (hash$iv << 16);
        int $i$f$h1 = $i$f$hash >>> 7;
        int $i$f$h2 = $i$f$hash & 127;
        int probeMask = mutableLongSet._capacity;
        int probeOffset = $i$f$h1 & probeMask;
        int probeIndex = 0;
        while (true) {
            long[] metadata$iv = mutableLongSet.metadata;
            int i$iv = probeOffset >> 3;
            int b$iv = (probeOffset & 7) << 3;
            long g = (((-b$iv) >> 63) & (metadata$iv[i$iv + 1] << (64 - b$iv))) | (metadata$iv[i$iv] >>> b$iv);
            long x$iv = ($i$f$h2 * ScatterMapKt.BitmaskLsb) ^ g;
            long j = x$iv - ScatterMapKt.BitmaskLsb;
            long $this$match$iv = ~x$iv;
            long m = $this$match$iv & j & (-9187201950435737472L);
            while (true) {
                long $this$hasNext$iv = m;
                int i = 0;
                if (!($this$hasNext$iv != 0)) {
                    break;
                }
                long $this$get$iv = m;
                int index = (probeOffset + (Long.numberOfTrailingZeros($this$get$iv) >> 3)) & probeMask;
                if (mutableLongSet.elements[index] == element) {
                    return index;
                }
                long $this$next$iv = m;
                m = $this$next$iv & ($this$next$iv - 1);
            }
            probeIndex += 8;
            probeOffset = (probeOffset + probeIndex) & probeMask;
            mutableLongSet = this;
        }
    }

    private final int findFirstAvailableSlot(int hash1) {
        int probeMask = this._capacity;
        int probeOffset = hash1 & probeMask;
        int probeIndex = 0;
        while (true) {
            long[] metadata$iv = this.metadata;
            int i$iv = probeOffset >> 3;
            int b$iv = (probeOffset & 7) << 3;
            long g = (metadata$iv[i$iv] >>> b$iv) | ((metadata$iv[i$iv + 1] << (64 - b$iv)) & ((-b$iv) >> 63));
            long $this$maskEmptyOrDeleted$iv = ((~g) << 7) & g & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv != 0) {
                return ((Long.numberOfTrailingZeros($this$maskEmptyOrDeleted$iv) >> 3) + probeOffset) & probeMask;
            }
            probeIndex += 8;
            probeOffset = (probeOffset + probeIndex) & probeMask;
        }
    }

    public final int trim() {
        int previousCapacity = this._capacity;
        int newCapacity = ScatterMapKt.normalizeCapacity(ScatterMapKt.unloadedCapacity(this._size));
        if (newCapacity < previousCapacity) {
            resizeStorage(newCapacity);
            return previousCapacity - this._capacity;
        }
        return 0;
    }

    private final void adjustStorage() {
        if (this._capacity > 8 && Long.compareUnsigned(ULong.m743constructorimpl(ULong.m743constructorimpl(this._size) * 32), ULong.m743constructorimpl(ULong.m743constructorimpl(this._capacity) * 25)) <= 0) {
            removeDeletedMarkers();
        } else {
            resizeStorage(ScatterMapKt.nextCapacity(this._capacity));
        }
    }

    private final void resizeStorage(int newCapacity) {
        long[] previousMetadata;
        MutableLongSet mutableLongSet = this;
        long[] previousMetadata2 = mutableLongSet.metadata;
        long[] previousElements = mutableLongSet.elements;
        int previousCapacity = mutableLongSet._capacity;
        initializeStorage(newCapacity);
        long[] newElements = mutableLongSet.elements;
        int i = 0;
        while (i < previousCapacity) {
            if (!(((previousMetadata2[i >> 3] >> ((i & 7) << 3)) & 255) < 128)) {
                previousMetadata = previousMetadata2;
            } else {
                long previousElement = previousElements[i];
                int hash$iv = Long.hashCode(previousElement) * ScatterMapKt.MurmurHashC1;
                int $i$f$hash = hash$iv ^ (hash$iv << 16);
                int $i$f$h1 = $i$f$hash >>> 7;
                int index = mutableLongSet.findFirstAvailableSlot($i$f$h1);
                int $i$f$h2 = $i$f$hash & 127;
                long value$iv = $i$f$h2;
                long[] m$iv = this.metadata;
                int i$iv$iv = index >> 3;
                int b$iv$iv = (index & 7) << 3;
                previousMetadata = previousMetadata2;
                m$iv[i$iv$iv] = (m$iv[i$iv$iv] & (~(255 << b$iv$iv))) | (value$iv << b$iv$iv);
                int c$iv = this._capacity;
                int cloneIndex$iv = ((index - 7) & c$iv) + (c$iv & 7);
                int i$iv$iv2 = cloneIndex$iv >> 3;
                int b$iv$iv2 = (cloneIndex$iv & 7) << 3;
                m$iv[i$iv$iv2] = (m$iv[i$iv$iv2] & (~(255 << b$iv$iv2))) | (value$iv << b$iv$iv2);
                newElements[index] = previousElement;
            }
            i++;
            mutableLongSet = this;
            previousMetadata2 = previousMetadata;
        }
    }

    private final void removeDeletedMarkers() {
        long[] m;
        int capacity;
        long[] m2 = this.metadata;
        int capacity2 = this._capacity;
        int removedDeletes = 0;
        int i = 0;
        while (i < capacity2) {
            long slot = (m2[i >> 3] >> ((i & 7) << 3)) & 255;
            if (slot != 254) {
                m = m2;
                capacity = capacity2;
            } else {
                long[] m$iv = this.metadata;
                int i$iv$iv = i >> 3;
                int b$iv$iv = (i & 7) << 3;
                m = m2;
                capacity = capacity2;
                m$iv[i$iv$iv] = (m$iv[i$iv$iv] & (~(255 << b$iv$iv))) | (128 << b$iv$iv);
                int c$iv = this._capacity;
                int cloneIndex$iv = ((i - 7) & c$iv) + (c$iv & 7);
                int i$iv$iv2 = cloneIndex$iv >> 3;
                int b$iv$iv2 = (cloneIndex$iv & 7) << 3;
                m$iv[i$iv$iv2] = (m$iv[i$iv$iv2] & (~(255 << b$iv$iv2))) | (128 << b$iv$iv2);
                removedDeletes++;
            }
            i++;
            m2 = m;
            capacity2 = capacity;
        }
        this.growthLimit += removedDeletes;
    }

    private final void writeMetadata(int index, long value) {
        long[] m = this.metadata;
        int i$iv = index >> 3;
        int b$iv = (index & 7) << 3;
        m[i$iv] = (m[i$iv] & (~(255 << b$iv))) | (value << b$iv);
        int $i$f$writeRawMetadata = this._capacity;
        int cloneIndex = ((index - 7) & $i$f$writeRawMetadata) + ($i$f$writeRawMetadata & 7);
        int i$iv2 = cloneIndex >> 3;
        int b$iv2 = (cloneIndex & 7) << 3;
        m[i$iv2] = ((~(255 << b$iv2)) & m[i$iv2]) | (value << b$iv2);
    }
}
