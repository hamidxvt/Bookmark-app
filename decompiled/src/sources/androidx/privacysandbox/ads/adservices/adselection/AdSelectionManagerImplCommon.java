package androidx.privacysandbox.ads.adservices.adselection;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.os.OutcomeReceiverKt;
import androidx.privacysandbox.ads.adservices.adid.AdIdManagerImplCommon$$ExternalSyntheticLambda0;
import androidx.privacysandbox.ads.adservices.internal.AdServicesInfo;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: AdSelectionManagerImplCommon.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001:\u0002)*B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0097@¢\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0097@¢\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0097@¢\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0097@¢\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001cH\u0097@¢\u0006\u0002\u0010\u001dJ\u0016\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001fH\u0097@¢\u0006\u0002\u0010 J\u0016\u0010!\u001a\u00020\"2\u0006\u0010\u001b\u001a\u00020#H\u0083@¢\u0006\u0002\u0010$J\u0016\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020'H\u0097@¢\u0006\u0002\u0010(R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006+"}, d2 = {"Landroidx/privacysandbox/ads/adservices/adselection/AdSelectionManagerImplCommon;", "Landroidx/privacysandbox/ads/adservices/adselection/AdSelectionManager;", "mAdSelectionManager", "Landroid/adservices/adselection/AdSelectionManager;", "(Landroid/adservices/adselection/AdSelectionManager;)V", "getMAdSelectionManager", "()Landroid/adservices/adselection/AdSelectionManager;", "getAdSelectionData", "Landroidx/privacysandbox/ads/adservices/adselection/GetAdSelectionDataOutcome;", "getAdSelectionDataRequest", "Landroidx/privacysandbox/ads/adservices/adselection/GetAdSelectionDataRequest;", "(Landroidx/privacysandbox/ads/adservices/adselection/GetAdSelectionDataRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "persistAdSelectionResult", "Landroidx/privacysandbox/ads/adservices/adselection/AdSelectionOutcome;", "persistAdSelectionResultRequest", "Landroidx/privacysandbox/ads/adservices/adselection/PersistAdSelectionResultRequest;", "(Landroidx/privacysandbox/ads/adservices/adselection/PersistAdSelectionResultRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reportEvent", "", "reportEventRequest", "Landroidx/privacysandbox/ads/adservices/adselection/ReportEventRequest;", "(Landroidx/privacysandbox/ads/adservices/adselection/ReportEventRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reportImpression", "reportImpressionRequest", "Landroidx/privacysandbox/ads/adservices/adselection/ReportImpressionRequest;", "(Landroidx/privacysandbox/ads/adservices/adselection/ReportImpressionRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectAds", "adSelectionConfig", "Landroidx/privacysandbox/ads/adservices/adselection/AdSelectionConfig;", "(Landroidx/privacysandbox/ads/adservices/adselection/AdSelectionConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "adSelectionFromOutcomesConfig", "Landroidx/privacysandbox/ads/adservices/adselection/AdSelectionFromOutcomesConfig;", "(Landroidx/privacysandbox/ads/adservices/adselection/AdSelectionFromOutcomesConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectAdsInternal", "Landroid/adservices/adselection/AdSelectionOutcome;", "Landroid/adservices/adselection/AdSelectionConfig;", "(Landroid/adservices/adselection/AdSelectionConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAdCounterHistogram", "updateAdCounterHistogramRequest", "Landroidx/privacysandbox/ads/adservices/adselection/UpdateAdCounterHistogramRequest;", "(Landroidx/privacysandbox/ads/adservices/adselection/UpdateAdCounterHistogramRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Ext10Impl", "Ext8Impl", "ads-adservices_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public class AdSelectionManagerImplCommon extends AdSelectionManager {
    private final android.adservices.adselection.AdSelectionManager mAdSelectionManager;

    @Override // androidx.privacysandbox.ads.adservices.adselection.AdSelectionManager
    public Object getAdSelectionData(GetAdSelectionDataRequest getAdSelectionDataRequest, Continuation<? super GetAdSelectionDataOutcome> continuation) {
        return getAdSelectionData$suspendImpl(this, getAdSelectionDataRequest, continuation);
    }

    @Override // androidx.privacysandbox.ads.adservices.adselection.AdSelectionManager
    public Object persistAdSelectionResult(PersistAdSelectionResultRequest persistAdSelectionResultRequest, Continuation<? super AdSelectionOutcome> continuation) {
        return persistAdSelectionResult$suspendImpl(this, persistAdSelectionResultRequest, continuation);
    }

    @Override // androidx.privacysandbox.ads.adservices.adselection.AdSelectionManager
    public Object reportEvent(ReportEventRequest reportEventRequest, Continuation<? super Unit> continuation) {
        return reportEvent$suspendImpl(this, reportEventRequest, continuation);
    }

    @Override // androidx.privacysandbox.ads.adservices.adselection.AdSelectionManager
    public Object reportImpression(ReportImpressionRequest reportImpressionRequest, Continuation<? super Unit> continuation) {
        return reportImpression$suspendImpl(this, reportImpressionRequest, continuation);
    }

    @Override // androidx.privacysandbox.ads.adservices.adselection.AdSelectionManager
    public Object selectAds(AdSelectionConfig adSelectionConfig, Continuation<? super AdSelectionOutcome> continuation) {
        return selectAds$suspendImpl(this, adSelectionConfig, continuation);
    }

    @Override // androidx.privacysandbox.ads.adservices.adselection.AdSelectionManager
    public Object selectAds(AdSelectionFromOutcomesConfig adSelectionFromOutcomesConfig, Continuation<? super AdSelectionOutcome> continuation) {
        return selectAds$suspendImpl(this, adSelectionFromOutcomesConfig, continuation);
    }

    @Override // androidx.privacysandbox.ads.adservices.adselection.AdSelectionManager
    public Object updateAdCounterHistogram(UpdateAdCounterHistogramRequest updateAdCounterHistogramRequest, Continuation<? super Unit> continuation) {
        return updateAdCounterHistogram$suspendImpl(this, updateAdCounterHistogramRequest, continuation);
    }

    protected final android.adservices.adselection.AdSelectionManager getMAdSelectionManager() {
        return this.mAdSelectionManager;
    }

    public AdSelectionManagerImplCommon(android.adservices.adselection.AdSelectionManager mAdSelectionManager) {
        Intrinsics.checkNotNullParameter(mAdSelectionManager, "mAdSelectionManager");
        this.mAdSelectionManager = mAdSelectionManager;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static /* synthetic */ Object selectAds$suspendImpl(AdSelectionManagerImplCommon $this, AdSelectionConfig adSelectionConfig, Continuation<? super AdSelectionOutcome> continuation) {
        AdSelectionManagerImplCommon$selectAds$1 adSelectionManagerImplCommon$selectAds$1;
        Object selectAdsInternal;
        if (continuation instanceof AdSelectionManagerImplCommon$selectAds$1) {
            adSelectionManagerImplCommon$selectAds$1 = (AdSelectionManagerImplCommon$selectAds$1) continuation;
            if ((adSelectionManagerImplCommon$selectAds$1.label & Integer.MIN_VALUE) != 0) {
                adSelectionManagerImplCommon$selectAds$1.label -= Integer.MIN_VALUE;
                Object $result = adSelectionManagerImplCommon$selectAds$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (adSelectionManagerImplCommon$selectAds$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        android.adservices.adselection.AdSelectionConfig convertToAdServices$ads_adservices_release = adSelectionConfig.convertToAdServices$ads_adservices_release();
                        adSelectionManagerImplCommon$selectAds$1.label = 1;
                        selectAdsInternal = $this.selectAdsInternal(convertToAdServices$ads_adservices_release, adSelectionManagerImplCommon$selectAds$1);
                        if (selectAdsInternal == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        selectAdsInternal = $result;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return new AdSelectionOutcome((android.adservices.adselection.AdSelectionOutcome) selectAdsInternal);
            }
        }
        adSelectionManagerImplCommon$selectAds$1 = new AdSelectionManagerImplCommon$selectAds$1($this, continuation);
        Object $result2 = adSelectionManagerImplCommon$selectAds$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (adSelectionManagerImplCommon$selectAds$1.label) {
        }
        return new AdSelectionOutcome((android.adservices.adselection.AdSelectionOutcome) selectAdsInternal);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object selectAdsInternal(android.adservices.adselection.AdSelectionConfig adSelectionConfig, Continuation<? super android.adservices.adselection.AdSelectionOutcome> continuation) {
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        CancellableContinuationImpl cont = cancellable$iv;
        getMAdSelectionManager().selectAds(adSelectionConfig, new AdIdManagerImplCommon$$ExternalSyntheticLambda0(), OutcomeReceiverKt.asOutcomeReceiver(cont));
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    static /* synthetic */ Object selectAds$suspendImpl(AdSelectionManagerImplCommon $this, AdSelectionFromOutcomesConfig adSelectionFromOutcomesConfig, Continuation<? super AdSelectionOutcome> continuation) {
        if (AdServicesInfo.INSTANCE.adServicesVersion() >= 10 || AdServicesInfo.INSTANCE.extServicesVersionS() >= 10) {
            return Ext10Impl.INSTANCE.selectAds($this.mAdSelectionManager, adSelectionFromOutcomesConfig, continuation);
        }
        throw new UnsupportedOperationException("API is not available. Min version is API 31 ext 10");
    }

    static /* synthetic */ Object reportImpression$suspendImpl(AdSelectionManagerImplCommon $this, ReportImpressionRequest reportImpressionRequest, Continuation<? super Unit> continuation) {
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        CancellableContinuationImpl continuation2 = cancellable$iv;
        $this.getMAdSelectionManager().reportImpression(reportImpressionRequest.convertToAdServices$ads_adservices_release(), new AdIdManagerImplCommon$$ExternalSyntheticLambda0(), OutcomeReceiverKt.asOutcomeReceiver(continuation2));
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }

    static /* synthetic */ Object reportEvent$suspendImpl(AdSelectionManagerImplCommon $this, ReportEventRequest reportEventRequest, Continuation<? super Unit> continuation) {
        if (AdServicesInfo.INSTANCE.adServicesVersion() >= 8 || AdServicesInfo.INSTANCE.extServicesVersionS() >= 9) {
            Object reportEvent = Ext8Impl.INSTANCE.reportEvent($this.mAdSelectionManager, reportEventRequest, continuation);
            return reportEvent == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? reportEvent : Unit.INSTANCE;
        }
        throw new UnsupportedOperationException("API is unsupported. Min version is API 33 ext 8 or API 31/32 ext 9");
    }

    static /* synthetic */ Object updateAdCounterHistogram$suspendImpl(AdSelectionManagerImplCommon $this, UpdateAdCounterHistogramRequest updateAdCounterHistogramRequest, Continuation<? super Unit> continuation) {
        if (AdServicesInfo.INSTANCE.adServicesVersion() >= 8 || AdServicesInfo.INSTANCE.extServicesVersionS() >= 9) {
            Object updateAdCounterHistogram = Ext8Impl.INSTANCE.updateAdCounterHistogram($this.mAdSelectionManager, updateAdCounterHistogramRequest, continuation);
            return updateAdCounterHistogram == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? updateAdCounterHistogram : Unit.INSTANCE;
        }
        throw new UnsupportedOperationException("API is unsupported. Min version is API 33 ext 8 or API 31/32 ext 9");
    }

    static /* synthetic */ Object getAdSelectionData$suspendImpl(AdSelectionManagerImplCommon $this, GetAdSelectionDataRequest getAdSelectionDataRequest, Continuation<? super GetAdSelectionDataOutcome> continuation) {
        if (AdServicesInfo.INSTANCE.adServicesVersion() >= 10 || AdServicesInfo.INSTANCE.extServicesVersionS() >= 10) {
            return Ext10Impl.INSTANCE.getAdSelectionData($this.mAdSelectionManager, getAdSelectionDataRequest, continuation);
        }
        throw new UnsupportedOperationException("API is not available. Min version is API 31 ext 10");
    }

    static /* synthetic */ Object persistAdSelectionResult$suspendImpl(AdSelectionManagerImplCommon $this, PersistAdSelectionResultRequest persistAdSelectionResultRequest, Continuation<? super AdSelectionOutcome> continuation) {
        if (AdServicesInfo.INSTANCE.adServicesVersion() >= 10 || AdServicesInfo.INSTANCE.extServicesVersionS() >= 10) {
            return Ext10Impl.INSTANCE.persistAdSelectionResult($this.mAdSelectionManager, persistAdSelectionResultRequest, continuation);
        }
        throw new UnsupportedOperationException("API is not available. Min version is API 31 ext 10");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AdSelectionManagerImplCommon.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/privacysandbox/ads/adservices/adselection/AdSelectionManagerImplCommon$Ext10Impl;", "", "()V", "Companion", "ads-adservices_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class Ext10Impl {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        private Ext10Impl() {
        }

        /* compiled from: AdSelectionManagerImplCommon.kt */
        @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0087@¢\u0006\u0002\u0010\tJ\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0087@¢\u0006\u0002\u0010\u000eJ\u001e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0087@¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/privacysandbox/ads/adservices/adselection/AdSelectionManagerImplCommon$Ext10Impl$Companion;", "", "()V", "getAdSelectionData", "Landroidx/privacysandbox/ads/adservices/adselection/GetAdSelectionDataOutcome;", "adSelectionManager", "Landroid/adservices/adselection/AdSelectionManager;", "getAdSelectionDataRequest", "Landroidx/privacysandbox/ads/adservices/adselection/GetAdSelectionDataRequest;", "(Landroid/adservices/adselection/AdSelectionManager;Landroidx/privacysandbox/ads/adservices/adselection/GetAdSelectionDataRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "persistAdSelectionResult", "Landroidx/privacysandbox/ads/adservices/adselection/AdSelectionOutcome;", "persistAdSelectionResultRequest", "Landroidx/privacysandbox/ads/adservices/adselection/PersistAdSelectionResultRequest;", "(Landroid/adservices/adselection/AdSelectionManager;Landroidx/privacysandbox/ads/adservices/adselection/PersistAdSelectionResultRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectAds", "adSelectionFromOutcomesConfig", "Landroidx/privacysandbox/ads/adservices/adselection/AdSelectionFromOutcomesConfig;", "(Landroid/adservices/adselection/AdSelectionManager;Landroidx/privacysandbox/ads/adservices/adselection/AdSelectionFromOutcomesConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ads-adservices_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
            /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
            /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object getAdSelectionData(android.adservices.adselection.AdSelectionManager adSelectionManager, GetAdSelectionDataRequest getAdSelectionDataRequest, Continuation<? super GetAdSelectionDataOutcome> continuation) {
                AdSelectionManagerImplCommon$Ext10Impl$Companion$getAdSelectionData$1 adSelectionManagerImplCommon$Ext10Impl$Companion$getAdSelectionData$1;
                Object obj;
                if (continuation instanceof AdSelectionManagerImplCommon$Ext10Impl$Companion$getAdSelectionData$1) {
                    adSelectionManagerImplCommon$Ext10Impl$Companion$getAdSelectionData$1 = (AdSelectionManagerImplCommon$Ext10Impl$Companion$getAdSelectionData$1) continuation;
                    if ((adSelectionManagerImplCommon$Ext10Impl$Companion$getAdSelectionData$1.label & Integer.MIN_VALUE) != 0) {
                        adSelectionManagerImplCommon$Ext10Impl$Companion$getAdSelectionData$1.label -= Integer.MIN_VALUE;
                        Object $result = adSelectionManagerImplCommon$Ext10Impl$Companion$getAdSelectionData$1.result;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (adSelectionManagerImplCommon$Ext10Impl$Companion$getAdSelectionData$1.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                adSelectionManagerImplCommon$Ext10Impl$Companion$getAdSelectionData$1.L$0 = adSelectionManager;
                                adSelectionManagerImplCommon$Ext10Impl$Companion$getAdSelectionData$1.L$1 = getAdSelectionDataRequest;
                                adSelectionManagerImplCommon$Ext10Impl$Companion$getAdSelectionData$1.label = 1;
                                Continuation uCont$iv = adSelectionManagerImplCommon$Ext10Impl$Companion$getAdSelectionData$1;
                                CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(uCont$iv), 1);
                                cancellable$iv.initCancellability();
                                CancellableContinuationImpl continuation2 = cancellable$iv;
                                adSelectionManager.getAdSelectionData(getAdSelectionDataRequest.convertToAdServices$ads_adservices_release(), new AdIdManagerImplCommon$$ExternalSyntheticLambda0(), OutcomeReceiverKt.asOutcomeReceiver(continuation2));
                                Object result = cancellable$iv.getResult();
                                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                    DebugProbesKt.probeCoroutineSuspended(adSelectionManagerImplCommon$Ext10Impl$Companion$getAdSelectionData$1);
                                }
                                if (result == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                obj = result;
                                break;
                            case 1:
                                ResultKt.throwOnFailure($result);
                                obj = $result;
                                break;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        return new GetAdSelectionDataOutcome((android.adservices.adselection.GetAdSelectionDataOutcome) obj);
                    }
                }
                adSelectionManagerImplCommon$Ext10Impl$Companion$getAdSelectionData$1 = new AdSelectionManagerImplCommon$Ext10Impl$Companion$getAdSelectionData$1(this, continuation);
                Object $result2 = adSelectionManagerImplCommon$Ext10Impl$Companion$getAdSelectionData$1.result;
                Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (adSelectionManagerImplCommon$Ext10Impl$Companion$getAdSelectionData$1.label) {
                }
                return new GetAdSelectionDataOutcome((android.adservices.adselection.GetAdSelectionDataOutcome) obj);
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
            /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
            /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object persistAdSelectionResult(android.adservices.adselection.AdSelectionManager adSelectionManager, PersistAdSelectionResultRequest persistAdSelectionResultRequest, Continuation<? super AdSelectionOutcome> continuation) {
                AdSelectionManagerImplCommon$Ext10Impl$Companion$persistAdSelectionResult$1 adSelectionManagerImplCommon$Ext10Impl$Companion$persistAdSelectionResult$1;
                Object obj;
                if (continuation instanceof AdSelectionManagerImplCommon$Ext10Impl$Companion$persistAdSelectionResult$1) {
                    adSelectionManagerImplCommon$Ext10Impl$Companion$persistAdSelectionResult$1 = (AdSelectionManagerImplCommon$Ext10Impl$Companion$persistAdSelectionResult$1) continuation;
                    if ((adSelectionManagerImplCommon$Ext10Impl$Companion$persistAdSelectionResult$1.label & Integer.MIN_VALUE) != 0) {
                        adSelectionManagerImplCommon$Ext10Impl$Companion$persistAdSelectionResult$1.label -= Integer.MIN_VALUE;
                        Object $result = adSelectionManagerImplCommon$Ext10Impl$Companion$persistAdSelectionResult$1.result;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (adSelectionManagerImplCommon$Ext10Impl$Companion$persistAdSelectionResult$1.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                adSelectionManagerImplCommon$Ext10Impl$Companion$persistAdSelectionResult$1.L$0 = adSelectionManager;
                                adSelectionManagerImplCommon$Ext10Impl$Companion$persistAdSelectionResult$1.L$1 = persistAdSelectionResultRequest;
                                adSelectionManagerImplCommon$Ext10Impl$Companion$persistAdSelectionResult$1.label = 1;
                                Continuation uCont$iv = adSelectionManagerImplCommon$Ext10Impl$Companion$persistAdSelectionResult$1;
                                CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(uCont$iv), 1);
                                cancellable$iv.initCancellability();
                                CancellableContinuationImpl continuation2 = cancellable$iv;
                                adSelectionManager.persistAdSelectionResult(persistAdSelectionResultRequest.convertToAdServices$ads_adservices_release(), new AdIdManagerImplCommon$$ExternalSyntheticLambda0(), OutcomeReceiverKt.asOutcomeReceiver(continuation2));
                                Object result = cancellable$iv.getResult();
                                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                    DebugProbesKt.probeCoroutineSuspended(adSelectionManagerImplCommon$Ext10Impl$Companion$persistAdSelectionResult$1);
                                }
                                if (result == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                obj = result;
                                break;
                            case 1:
                                ResultKt.throwOnFailure($result);
                                obj = $result;
                                break;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        return new AdSelectionOutcome((android.adservices.adselection.AdSelectionOutcome) obj);
                    }
                }
                adSelectionManagerImplCommon$Ext10Impl$Companion$persistAdSelectionResult$1 = new AdSelectionManagerImplCommon$Ext10Impl$Companion$persistAdSelectionResult$1(this, continuation);
                Object $result2 = adSelectionManagerImplCommon$Ext10Impl$Companion$persistAdSelectionResult$1.result;
                Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (adSelectionManagerImplCommon$Ext10Impl$Companion$persistAdSelectionResult$1.label) {
                }
                return new AdSelectionOutcome((android.adservices.adselection.AdSelectionOutcome) obj);
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
            /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
            /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object selectAds(android.adservices.adselection.AdSelectionManager adSelectionManager, AdSelectionFromOutcomesConfig adSelectionFromOutcomesConfig, Continuation<? super AdSelectionOutcome> continuation) {
                AdSelectionManagerImplCommon$Ext10Impl$Companion$selectAds$1 adSelectionManagerImplCommon$Ext10Impl$Companion$selectAds$1;
                Object obj;
                if (continuation instanceof AdSelectionManagerImplCommon$Ext10Impl$Companion$selectAds$1) {
                    adSelectionManagerImplCommon$Ext10Impl$Companion$selectAds$1 = (AdSelectionManagerImplCommon$Ext10Impl$Companion$selectAds$1) continuation;
                    if ((adSelectionManagerImplCommon$Ext10Impl$Companion$selectAds$1.label & Integer.MIN_VALUE) != 0) {
                        adSelectionManagerImplCommon$Ext10Impl$Companion$selectAds$1.label -= Integer.MIN_VALUE;
                        Object $result = adSelectionManagerImplCommon$Ext10Impl$Companion$selectAds$1.result;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (adSelectionManagerImplCommon$Ext10Impl$Companion$selectAds$1.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                adSelectionManagerImplCommon$Ext10Impl$Companion$selectAds$1.L$0 = adSelectionManager;
                                adSelectionManagerImplCommon$Ext10Impl$Companion$selectAds$1.L$1 = adSelectionFromOutcomesConfig;
                                adSelectionManagerImplCommon$Ext10Impl$Companion$selectAds$1.label = 1;
                                Continuation uCont$iv = adSelectionManagerImplCommon$Ext10Impl$Companion$selectAds$1;
                                CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(uCont$iv), 1);
                                cancellable$iv.initCancellability();
                                CancellableContinuationImpl continuation2 = cancellable$iv;
                                adSelectionManager.selectAds(adSelectionFromOutcomesConfig.convertToAdServices$ads_adservices_release(), new AdIdManagerImplCommon$$ExternalSyntheticLambda0(), OutcomeReceiverKt.asOutcomeReceiver(continuation2));
                                Object result = cancellable$iv.getResult();
                                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                    DebugProbesKt.probeCoroutineSuspended(adSelectionManagerImplCommon$Ext10Impl$Companion$selectAds$1);
                                }
                                if (result == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                obj = result;
                                break;
                            case 1:
                                ResultKt.throwOnFailure($result);
                                obj = $result;
                                break;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        return new AdSelectionOutcome((android.adservices.adselection.AdSelectionOutcome) obj);
                    }
                }
                adSelectionManagerImplCommon$Ext10Impl$Companion$selectAds$1 = new AdSelectionManagerImplCommon$Ext10Impl$Companion$selectAds$1(this, continuation);
                Object $result2 = adSelectionManagerImplCommon$Ext10Impl$Companion$selectAds$1.result;
                Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (adSelectionManagerImplCommon$Ext10Impl$Companion$selectAds$1.label) {
                }
                return new AdSelectionOutcome((android.adservices.adselection.AdSelectionOutcome) obj);
            }
        }
    }

    /* compiled from: AdSelectionManagerImplCommon.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/privacysandbox/ads/adservices/adselection/AdSelectionManagerImplCommon$Ext8Impl;", "", "()V", "Companion", "ads-adservices_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class Ext8Impl {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        private Ext8Impl() {
        }

        /* compiled from: AdSelectionManagerImplCommon.kt */
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0087@¢\u0006\u0002\u0010\tJ\u001e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0087@¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/privacysandbox/ads/adservices/adselection/AdSelectionManagerImplCommon$Ext8Impl$Companion;", "", "()V", "reportEvent", "", "adSelectionManager", "Landroid/adservices/adselection/AdSelectionManager;", "reportEventRequest", "Landroidx/privacysandbox/ads/adservices/adselection/ReportEventRequest;", "(Landroid/adservices/adselection/AdSelectionManager;Landroidx/privacysandbox/ads/adservices/adselection/ReportEventRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAdCounterHistogram", "updateAdCounterHistogramRequest", "Landroidx/privacysandbox/ads/adservices/adselection/UpdateAdCounterHistogramRequest;", "(Landroid/adservices/adselection/AdSelectionManager;Landroidx/privacysandbox/ads/adservices/adselection/UpdateAdCounterHistogramRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ads-adservices_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final Object updateAdCounterHistogram(android.adservices.adselection.AdSelectionManager adSelectionManager, UpdateAdCounterHistogramRequest updateAdCounterHistogramRequest, Continuation<? super Unit> continuation) {
                CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
                cancellable$iv.initCancellability();
                CancellableContinuationImpl cont = cancellable$iv;
                adSelectionManager.updateAdCounterHistogram(updateAdCounterHistogramRequest.convertToAdServices$ads_adservices_release(), new AdIdManagerImplCommon$$ExternalSyntheticLambda0(), OutcomeReceiverKt.asOutcomeReceiver(cont));
                Object result = cancellable$iv.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(continuation);
                }
                return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
            }

            public final Object reportEvent(android.adservices.adselection.AdSelectionManager adSelectionManager, ReportEventRequest reportEventRequest, Continuation<? super Unit> continuation) {
                CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
                cancellable$iv.initCancellability();
                CancellableContinuationImpl continuation2 = cancellable$iv;
                adSelectionManager.reportEvent(reportEventRequest.convertToAdServices$ads_adservices_release(), new AdIdManagerImplCommon$$ExternalSyntheticLambda0(), OutcomeReceiverKt.asOutcomeReceiver(continuation2));
                Object result = cancellable$iv.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(continuation);
                }
                return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
            }
        }
    }
}
