package androidx.privacysandbox.ads.adservices.appsetid;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.os.OutcomeReceiverKt;
import androidx.privacysandbox.ads.adservices.adid.AdIdManagerImplCommon$$ExternalSyntheticLambda0;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: AppSetIdManagerImplCommon.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u0010\t\u001a\u00020\u0006H\u0097@¢\u0006\u0002\u0010\nJ\u000e\u0010\u000b\u001a\u00020\bH\u0082@¢\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/privacysandbox/ads/adservices/appsetid/AppSetIdManagerImplCommon;", "Landroidx/privacysandbox/ads/adservices/appsetid/AppSetIdManager;", "mAppSetIdManager", "Landroid/adservices/appsetid/AppSetIdManager;", "(Landroid/adservices/appsetid/AppSetIdManager;)V", "convertResponse", "Landroidx/privacysandbox/ads/adservices/appsetid/AppSetId;", "response", "Landroid/adservices/appsetid/AppSetId;", "getAppSetId", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAppSetIdAsyncInternal", "ads-adservices_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public class AppSetIdManagerImplCommon extends AppSetIdManager {
    private final android.adservices.appsetid.AppSetIdManager mAppSetIdManager;

    @Override // androidx.privacysandbox.ads.adservices.appsetid.AppSetIdManager
    public Object getAppSetId(Continuation<? super AppSetId> continuation) {
        return getAppSetId$suspendImpl(this, continuation);
    }

    public AppSetIdManagerImplCommon(android.adservices.appsetid.AppSetIdManager mAppSetIdManager) {
        Intrinsics.checkNotNullParameter(mAppSetIdManager, "mAppSetIdManager");
        this.mAppSetIdManager = mAppSetIdManager;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static /* synthetic */ Object getAppSetId$suspendImpl(AppSetIdManagerImplCommon $this, Continuation<? super AppSetId> continuation) {
        AppSetIdManagerImplCommon$getAppSetId$1 appSetIdManagerImplCommon$getAppSetId$1;
        Object appSetIdAsyncInternal;
        if (continuation instanceof AppSetIdManagerImplCommon$getAppSetId$1) {
            appSetIdManagerImplCommon$getAppSetId$1 = (AppSetIdManagerImplCommon$getAppSetId$1) continuation;
            if ((appSetIdManagerImplCommon$getAppSetId$1.label & Integer.MIN_VALUE) != 0) {
                appSetIdManagerImplCommon$getAppSetId$1.label -= Integer.MIN_VALUE;
                Object $result = appSetIdManagerImplCommon$getAppSetId$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (appSetIdManagerImplCommon$getAppSetId$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        appSetIdManagerImplCommon$getAppSetId$1.L$0 = $this;
                        appSetIdManagerImplCommon$getAppSetId$1.label = 1;
                        appSetIdAsyncInternal = $this.getAppSetIdAsyncInternal(appSetIdManagerImplCommon$getAppSetId$1);
                        if (appSetIdAsyncInternal == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        $this = (AppSetIdManagerImplCommon) appSetIdManagerImplCommon$getAppSetId$1.L$0;
                        ResultKt.throwOnFailure($result);
                        appSetIdAsyncInternal = $result;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return $this.convertResponse((android.adservices.appsetid.AppSetId) appSetIdAsyncInternal);
            }
        }
        appSetIdManagerImplCommon$getAppSetId$1 = new AppSetIdManagerImplCommon$getAppSetId$1($this, continuation);
        Object $result2 = appSetIdManagerImplCommon$getAppSetId$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (appSetIdManagerImplCommon$getAppSetId$1.label) {
        }
        return $this.convertResponse((android.adservices.appsetid.AppSetId) appSetIdAsyncInternal);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getAppSetIdAsyncInternal(Continuation<? super android.adservices.appsetid.AppSetId> continuation) {
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        CancellableContinuationImpl continuation2 = cancellable$iv;
        this.mAppSetIdManager.getAppSetId(new AdIdManagerImplCommon$$ExternalSyntheticLambda0(), OutcomeReceiverKt.asOutcomeReceiver(continuation2));
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    private final AppSetId convertResponse(android.adservices.appsetid.AppSetId response) {
        if (response.getScope() == 1) {
            String id = response.getId();
            Intrinsics.checkNotNullExpressionValue(id, "response.id");
            return new AppSetId(id, 1);
        }
        String id2 = response.getId();
        Intrinsics.checkNotNullExpressionValue(id2, "response.id");
        return new AppSetId(id2, 2);
    }
}
