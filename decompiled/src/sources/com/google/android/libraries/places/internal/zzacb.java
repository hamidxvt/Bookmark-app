package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzacb;
import com.google.android.libraries.places.internal.zzacc;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class zzacb<MessageType extends zzacc<MessageType, BuilderType>, BuilderType extends zzacb<MessageType, BuilderType>> implements zzaeq {
    @Override // 
    /* renamed from: zzo, reason: merged with bridge method [inline-methods] */
    public abstract zzacb clone();

    protected abstract zzacb zzp(zzacc zzaccVar);

    @Override // com.google.android.libraries.places.internal.zzaeq
    public final /* bridge */ /* synthetic */ zzaeq zzq(zzaer zzaerVar) {
        if (zzw().getClass().isInstance(zzaerVar)) {
            return zzp((zzacc) zzaerVar);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
