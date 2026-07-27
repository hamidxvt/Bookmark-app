package com.google.android.libraries.places.internal;

import sun.misc.Unsafe;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzaga extends zzagc {
    zzaga(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.libraries.places.internal.zzagc
    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(zzk(obj, j));
    }

    @Override // com.google.android.libraries.places.internal.zzagc
    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(zzj(obj, j));
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.google.android.libraries.places.internal.zzagd.zzi(java.lang.Object, long, boolean):void
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.ArrayIndexOutOfBoundsException: arraycopy: length -1 is negative
        	at java.base/java.lang.System.arraycopy(Native Method)
        	at java.base/java.util.ArrayList.shiftTailOverGap(ArrayList.java:828)
        	at java.base/java.util.ArrayList.removeIf(ArrayList.java:1774)
        	at java.base/java.util.ArrayList.removeIf(ArrayList.java:1743)
        	at jadx.core.dex.instructions.args.SSAVar.removeUse(SSAVar.java:141)
        	at jadx.core.dex.instructions.args.SSAVar.use(SSAVar.java:134)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:493)
        	at jadx.core.dex.instructions.mods.TernaryInsn.rebindArgs(TernaryInsn.java:92)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1122)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1131)
        	at jadx.core.dex.visitors.InlineMethods.inlineMethod(InlineMethods.java:118)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:77)
        	... 1 more
        */
    @Override // com.google.android.libraries.places.internal.zzagc
    public final void zzc(java.lang.Object r2, long r3, boolean r5) {
        /*
            r1 = this;
            boolean r0 = com.google.android.libraries.places.internal.zzagd.zzb
            if (r0 == 0) goto L8
            com.google.android.libraries.places.internal.zzagd.zzi(r2, r3, r5)
            return
        L8:
            com.google.android.libraries.places.internal.zzagd.zzj(r2, r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzaga.zzc(java.lang.Object, long, boolean):void");
    }

    @Override // com.google.android.libraries.places.internal.zzagc
    public final void zzd(Object obj, long j, byte b) {
        if (zzagd.zzb) {
            zzagd.zzD(obj, j, b);
        } else {
            zzagd.zzE(obj, j, b);
        }
    }

    @Override // com.google.android.libraries.places.internal.zzagc
    public final void zze(Object obj, long j, double d) {
        zzo(obj, j, Double.doubleToLongBits(d));
    }

    @Override // com.google.android.libraries.places.internal.zzagc
    public final void zzf(Object obj, long j, float f) {
        zzn(obj, j, Float.floatToIntBits(f));
    }

    @Override // com.google.android.libraries.places.internal.zzagc
    public final boolean zzg(Object obj, long j) {
        return zzagd.zzb ? zzagd.zzt(obj, j) : zzagd.zzu(obj, j);
    }
}
