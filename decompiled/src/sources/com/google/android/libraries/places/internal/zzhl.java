package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzhl extends zzhn {
    private static final zzhl zzb = new zzhl();

    private zzhl() {
        super("");
    }

    @Override // com.google.android.libraries.places.internal.zzhn, java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return zza((zzhn) obj);
    }

    @Override // com.google.android.libraries.places.internal.zzhn
    public final int hashCode() {
        return System.identityHashCode(this);
    }

    public final String toString() {
        return "-∞";
    }

    @Override // com.google.android.libraries.places.internal.zzhn
    public final int zza(zzhn zzhnVar) {
        return zzhnVar == this ? 0 : -1;
    }

    @Override // com.google.android.libraries.places.internal.zzhn
    final void zzc(StringBuilder sb) {
        sb.append("(-∞");
    }

    @Override // com.google.android.libraries.places.internal.zzhn
    final void zzd(StringBuilder sb) {
        throw new AssertionError();
    }

    @Override // com.google.android.libraries.places.internal.zzhn
    final boolean zze(Comparable comparable) {
        return true;
    }
}
