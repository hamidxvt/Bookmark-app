package androidx.privacysandbox.ads.adservices.common;

import android.adservices.common.KeyedFrequencyCap;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.privacysandbox.ads.adservices.common.ExperimentalFeatures;
import java.time.Duration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: KeyedFrequencyCap.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\r\u0010\r\u001a\u00020\u000eH\u0001¢\u0006\u0002\b\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0016"}, d2 = {"Landroidx/privacysandbox/ads/adservices/common/KeyedFrequencyCap;", "", "adCounterKey", "", "maxCount", "interval", "Ljava/time/Duration;", "(IILjava/time/Duration;)V", "getAdCounterKey", "()I", "getInterval", "()Ljava/time/Duration;", "getMaxCount", "convertToAdServices", "Landroid/adservices/common/KeyedFrequencyCap;", "convertToAdServices$ads_adservices_release", "equals", "", "other", "hashCode", "toString", "", "ads-adservices_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@ExperimentalFeatures.Ext8OptIn
/* loaded from: classes.dex */
public final class KeyedFrequencyCap {
    private final int adCounterKey;
    private final Duration interval;
    private final int maxCount;

    public KeyedFrequencyCap(int adCounterKey, int maxCount, Duration interval) {
        Intrinsics.checkNotNullParameter(interval, "interval");
        this.adCounterKey = adCounterKey;
        this.maxCount = maxCount;
        this.interval = interval;
    }

    public final int getAdCounterKey() {
        return this.adCounterKey;
    }

    public final Duration getInterval() {
        return this.interval;
    }

    public final int getMaxCount() {
        return this.maxCount;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof KeyedFrequencyCap) {
            return this.adCounterKey == ((KeyedFrequencyCap) other).adCounterKey && this.maxCount == ((KeyedFrequencyCap) other).maxCount && Intrinsics.areEqual(this.interval, ((KeyedFrequencyCap) other).interval);
        }
        return false;
    }

    public int hashCode() {
        int hash = Integer.hashCode(this.adCounterKey);
        return (((hash * 31) + Integer.hashCode(this.maxCount)) * 31) + this.interval.hashCode();
    }

    public String toString() {
        return "KeyedFrequencyCap: adCounterKey=" + this.adCounterKey + ", maxCount=" + this.maxCount + ", interval=" + this.interval;
    }

    public final android.adservices.common.KeyedFrequencyCap convertToAdServices$ads_adservices_release() {
        android.adservices.common.KeyedFrequencyCap build = new KeyedFrequencyCap.Builder(this.adCounterKey, this.maxCount, this.interval).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(adCounterKey, ma…val)\n            .build()");
        return build;
    }
}
