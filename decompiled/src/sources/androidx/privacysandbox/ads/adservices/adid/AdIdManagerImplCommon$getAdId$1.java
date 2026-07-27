package androidx.privacysandbox.ads.adservices.adid;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: AdIdManagerImplCommon.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.privacysandbox.ads.adservices.adid.AdIdManagerImplCommon", f = "AdIdManagerImplCommon.kt", i = {}, l = {40}, m = "getAdId$suspendImpl", n = {}, s = {})
/* loaded from: classes.dex */
final class AdIdManagerImplCommon$getAdId$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AdIdManagerImplCommon this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AdIdManagerImplCommon$getAdId$1(AdIdManagerImplCommon adIdManagerImplCommon, Continuation<? super AdIdManagerImplCommon$getAdId$1> continuation) {
        super(continuation);
        this.this$0 = adIdManagerImplCommon;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return AdIdManagerImplCommon.getAdId$suspendImpl(this.this$0, this);
    }
}
