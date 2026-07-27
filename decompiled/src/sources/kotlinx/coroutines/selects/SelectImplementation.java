package kotlinx.coroutines.selects;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancelHandler;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectBuilder;

/* compiled from: Select.kt */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0011\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004:\u0001IB\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u001b\u001a\u00028\u0000H\u0091@¢\u0006\u0002\u0010\u001cJ\u000e\u0010\u001d\u001a\u00028\u0000H\u0082@¢\u0006\u0002\u0010\u001cJ0\u0010\u001e\u001a\u00020\u001f*\u00020 2\u001c\u0010!\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000#\u0012\u0006\u0012\u0004\u0018\u00010\r0\"H\u0096\u0002¢\u0006\u0002\u0010$JB\u0010\u001e\u001a\u00020\u001f\"\u0004\b\u0001\u0010%*\b\u0012\u0004\u0012\u0002H%0&2\"\u0010!\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H%\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000#\u0012\u0006\u0012\u0004\u0018\u00010\r0'H\u0096\u0002¢\u0006\u0002\u0010(JV\u0010\u001e\u001a\u00020\u001f\"\u0004\b\u0001\u0010)\"\u0004\b\u0002\u0010%*\u000e\u0012\u0004\u0012\u0002H)\u0012\u0004\u0012\u0002H%0*2\u0006\u0010+\u001a\u0002H)2\"\u0010!\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H%\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000#\u0012\u0006\u0012\u0004\u0018\u00010\r0'H\u0096\u0002¢\u0006\u0002\u0010,J \u0010-\u001a\u00020\u001f*\f0\u0016R\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010.\u001a\u00020\u000fH\u0001J\u0010\u0010/\u001a\u00020\u001f2\u0006\u00100\u001a\u00020\rH\u0002J\u0010\u00101\u001a\u00020\u001f2\u0006\u00102\u001a\u000203H\u0016J\u001c\u00104\u001a\u00020\u001f2\n\u00105\u001a\u0006\u0012\u0002\b\u0003062\u0006\u00107\u001a\u00020\u0019H\u0016J\u0012\u00108\u001a\u00020\u001f2\b\u0010\u001a\u001a\u0004\u0018\u00010\rH\u0016J\u000e\u00109\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010\u001cJ\u0010\u0010:\u001a\u00020\u001f2\u0006\u00100\u001a\u00020\rH\u0002J\u001a\u0010;\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\r2\b\u0010<\u001a\u0004\u0018\u00010\rH\u0016J\u0018\u0010=\u001a\u00020>2\u0006\u00100\u001a\u00020\r2\b\u0010<\u001a\u0004\u0018\u00010\rJ\u001a\u0010?\u001a\u00020\u00192\u0006\u00100\u001a\u00020\r2\b\u0010\u001a\u001a\u0004\u0018\u00010\rH\u0002J\u001c\u0010@\u001a\u000e\u0018\u00010\u0016R\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00100\u001a\u00020\rH\u0002J\u000e\u0010A\u001a\u00028\u0000H\u0082@¢\u0006\u0002\u0010\u001cJ*\u0010B\u001a\u00028\u00002\u0010\u0010C\u001a\f0\u0016R\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010\u001a\u001a\u0004\u0018\u00010\rH\u0082@¢\u0006\u0002\u0010DJ\u001a\u0010E\u001a\u00020\u001f2\u0010\u0010F\u001a\f0\u0016R\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0002J\u0012\u0010\u001e\u001a\u00020\u001f2\b\u0010G\u001a\u0004\u0018\u00010HH\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004R\u0014\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R \u0010\u0014\u001a\u0014\u0012\u000e\u0012\f0\u0016R\b\u0012\u0004\u0012\u00028\u00000\u0000\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lkotlinx/coroutines/selects/SelectImplementation;", "R", "Lkotlinx/coroutines/CancelHandler;", "Lkotlinx/coroutines/selects/SelectBuilder;", "Lkotlinx/coroutines/selects/SelectInstanceInternal;", "context", "Lkotlin/coroutines/CoroutineContext;", "<init>", "(Lkotlin/coroutines/CoroutineContext;)V", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "state", "Lkotlinx/atomicfu/AtomicRef;", "", "inRegistrationPhase", "", "getInRegistrationPhase", "()Z", "isSelected", "isCancelled", "clauses", "", "Lkotlinx/coroutines/selects/SelectImplementation$ClauseData;", "disposableHandleOrSegment", "indexInSegment", "", "internalResult", "doSelect", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doSelectSuspend", "invoke", "", "Lkotlinx/coroutines/selects/SelectClause0;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/selects/SelectClause0;Lkotlin/jvm/functions/Function1;)V", "Q", "Lkotlinx/coroutines/selects/SelectClause1;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/selects/SelectClause1;Lkotlin/jvm/functions/Function2;)V", "P", "Lkotlinx/coroutines/selects/SelectClause2;", "param", "(Lkotlinx/coroutines/selects/SelectClause2;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "register", "reregister", "checkClauseObject", "clauseObject", "disposeOnCompletion", "disposableHandle", "Lkotlinx/coroutines/DisposableHandle;", "invokeOnCancellation", "segment", "Lkotlinx/coroutines/internal/Segment;", FirebaseAnalytics.Param.INDEX, "selectInRegistrationPhase", "waitUntilSelected", "reregisterClause", "trySelect", "result", "trySelectDetailed", "Lkotlinx/coroutines/selects/TrySelectDetailedResult;", "trySelectInternal", "findClause", "complete", "processResultAndInvokeBlockRecoveringException", "clause", "(Lkotlinx/coroutines/selects/SelectImplementation$ClauseData;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cleanup", "selectedClause", "cause", "", "ClauseData", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public class SelectImplementation<R> implements CancelHandler, SelectBuilder<R>, SelectInstanceInternal<R> {
    private static final /* synthetic */ AtomicReferenceFieldUpdater state$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(SelectImplementation.class, Object.class, "state$volatile");
    private List<SelectImplementation<R>.ClauseData> clauses;
    private final CoroutineContext context;
    private Object disposableHandleOrSegment;
    private int indexInSegment;
    private Object internalResult;
    private volatile /* synthetic */ Object state$volatile;

    private final /* synthetic */ Object getState$volatile() {
        return this.state$volatile;
    }

    private final /* synthetic */ void loop$atomicfu(Object obj, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, Function1<Object, Unit> function1) {
        while (true) {
            function1.invoke(atomicReferenceFieldUpdater.get(obj));
        }
    }

    private final /* synthetic */ void setState$volatile(Object obj) {
        this.state$volatile = obj;
    }

    private final /* synthetic */ void update$atomicfu(Object obj, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, Function1<Object, ? extends Object> function1) {
        Object obj2;
        do {
            obj2 = atomicReferenceFieldUpdater.get(obj);
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, obj, obj2, function1.invoke(obj2)));
    }

    public Object doSelect(Continuation<? super R> continuation) {
        return doSelect$suspendImpl(this, continuation);
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(SelectClause2<? super P, ? extends Q> selectClause2, Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        SelectBuilder.DefaultImpls.invoke(this, selectClause2, function2);
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    @Deprecated(level = DeprecationLevel.ERROR, message = "Replaced with the same extension function", replaceWith = @ReplaceWith(expression = "onTimeout", imports = {"kotlinx.coroutines.selects.onTimeout"}))
    public void onTimeout(long timeMillis, Function1<? super Continuation<? super R>, ? extends Object> function1) {
        SelectBuilder.DefaultImpls.onTimeout(this, timeMillis, function1);
    }

    public SelectImplementation(CoroutineContext context) {
        Symbol symbol;
        Symbol symbol2;
        this.context = context;
        symbol = SelectKt.STATE_REG;
        this.state$volatile = symbol;
        this.clauses = new ArrayList(2);
        this.indexInSegment = -1;
        symbol2 = SelectKt.NO_RESULT;
        this.internalResult = symbol2;
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public CoroutineContext getContext() {
        return this.context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getInRegistrationPhase() {
        Symbol symbol;
        Object it = state$volatile$FU.get(this);
        symbol = SelectKt.STATE_REG;
        return it == symbol || (it instanceof List);
    }

    private final boolean isSelected() {
        return state$volatile$FU.get(this) instanceof ClauseData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isCancelled() {
        Symbol symbol;
        Object obj = state$volatile$FU.get(this);
        symbol = SelectKt.STATE_CANCELLED;
        return obj == symbol;
    }

    static /* synthetic */ <R> Object doSelect$suspendImpl(SelectImplementation<R> selectImplementation, Continuation<? super R> continuation) {
        return selectImplementation.isSelected() ? selectImplementation.complete(continuation) : selectImplementation.doSelectSuspend(continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0055 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object doSelectSuspend(Continuation<? super R> continuation) {
        SelectImplementation$doSelectSuspend$1 selectImplementation$doSelectSuspend$1;
        Object coroutine_suspended;
        SelectImplementation selectImplementation;
        Object complete;
        if (continuation instanceof SelectImplementation$doSelectSuspend$1) {
            selectImplementation$doSelectSuspend$1 = (SelectImplementation$doSelectSuspend$1) continuation;
            if ((selectImplementation$doSelectSuspend$1.label & Integer.MIN_VALUE) != 0) {
                selectImplementation$doSelectSuspend$1.label -= Integer.MIN_VALUE;
                Object $result = selectImplementation$doSelectSuspend$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (selectImplementation$doSelectSuspend$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        selectImplementation = this;
                        selectImplementation$doSelectSuspend$1.L$0 = selectImplementation;
                        selectImplementation$doSelectSuspend$1.label = 1;
                        if (selectImplementation.waitUntilSelected(selectImplementation$doSelectSuspend$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        selectImplementation = (SelectImplementation) selectImplementation$doSelectSuspend$1.L$0;
                        ResultKt.throwOnFailure($result);
                        break;
                    case 2:
                        ResultKt.throwOnFailure($result);
                        return $result;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                selectImplementation$doSelectSuspend$1.L$0 = null;
                selectImplementation$doSelectSuspend$1.label = 2;
                complete = selectImplementation.complete(selectImplementation$doSelectSuspend$1);
                if (complete != coroutine_suspended) {
                    return coroutine_suspended;
                }
                return complete;
            }
        }
        selectImplementation$doSelectSuspend$1 = new SelectImplementation$doSelectSuspend$1(this, continuation);
        Object $result2 = selectImplementation$doSelectSuspend$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (selectImplementation$doSelectSuspend$1.label) {
        }
        selectImplementation$doSelectSuspend$1.L$0 = null;
        selectImplementation$doSelectSuspend$1.label = 2;
        complete = selectImplementation.complete(selectImplementation$doSelectSuspend$1);
        if (complete != coroutine_suspended) {
        }
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void invoke(SelectClause0 $this$invoke, Function1<? super Continuation<? super R>, ? extends Object> function1) {
        register$default(this, new ClauseData($this$invoke.getClauseObject(), $this$invoke.getRegFunc(), $this$invoke.getProcessResFunc(), SelectKt.getPARAM_CLAUSE_0(), function1, $this$invoke.getOnCancellationConstructor()), false, 1, null);
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <Q> void invoke(SelectClause1<? extends Q> selectClause1, Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        register$default(this, new ClauseData(selectClause1.getClauseObject(), selectClause1.getRegFunc(), selectClause1.getProcessResFunc(), null, function2, selectClause1.getOnCancellationConstructor()), false, 1, null);
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(SelectClause2<? super P, ? extends Q> selectClause2, P p, Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        register$default(this, new ClauseData(selectClause2.getClauseObject(), selectClause2.getRegFunc(), selectClause2.getProcessResFunc(), p, function2, selectClause2.getOnCancellationConstructor()), false, 1, null);
    }

    public static /* synthetic */ void register$default(SelectImplementation selectImplementation, ClauseData clauseData, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: register");
        }
        if ((i & 1) != 0) {
            z = false;
        }
        selectImplementation.register(clauseData, z);
    }

    public final void register(SelectImplementation<R>.ClauseData clauseData, boolean reregister) {
        Symbol symbol;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            Object obj = state$volatile$FU.get(this);
            symbol = SelectKt.STATE_CANCELLED;
            if (!(obj != symbol)) {
                throw new AssertionError();
            }
        }
        Object it = state$volatile$FU.get(this);
        if (it instanceof ClauseData) {
            return;
        }
        if (!reregister) {
            checkClauseObject(clauseData.clauseObject);
        }
        if (clauseData.tryRegisterAsWaiter(this)) {
            if (!reregister) {
                List<SelectImplementation<R>.ClauseData> list = this.clauses;
                Intrinsics.checkNotNull(list);
                list.add(clauseData);
            }
            clauseData.disposableHandleOrSegment = this.disposableHandleOrSegment;
            clauseData.indexInSegment = this.indexInSegment;
            this.disposableHandleOrSegment = null;
            this.indexInSegment = -1;
            return;
        }
        state$volatile$FU.set(this, clauseData);
    }

    private final void checkClauseObject(Object clauseObject) {
        Iterable clauses = this.clauses;
        Intrinsics.checkNotNull(clauses);
        Iterable $this$none$iv = clauses;
        boolean z = true;
        if (!($this$none$iv instanceof Collection) || !((Collection) $this$none$iv).isEmpty()) {
            Iterator it = $this$none$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object element$iv = it.next();
                ClauseData it2 = (ClauseData) element$iv;
                ClauseData it3 = it2.clauseObject == clauseObject ? 1 : null;
                if (it3 != null) {
                    z = false;
                    break;
                }
            }
        }
        if (!z) {
            throw new IllegalStateException(("Cannot use select clauses on the same object: " + clauseObject).toString());
        }
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public void disposeOnCompletion(DisposableHandle disposableHandle) {
        this.disposableHandleOrSegment = disposableHandle;
    }

    @Override // kotlinx.coroutines.Waiter
    public void invokeOnCancellation(Segment<?> segment, int index) {
        this.disposableHandleOrSegment = segment;
        this.indexInSegment = index;
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public void selectInRegistrationPhase(Object internalResult) {
        this.internalResult = internalResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0084, code lost:
    
        r2 = r4.getResult();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x008d, code lost:
    
        if (r2 != kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x008f, code lost:
    
        kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r18);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0096, code lost:
    
        if (r2 != kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0098, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x009c, code lost:
    
        return kotlin.Unit.INSTANCE;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object waitUntilSelected(Continuation<? super Unit> continuation) {
        Symbol symbol;
        Symbol symbol2;
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        CancellableContinuationImpl cont = cancellable$iv;
        AtomicReferenceFieldUpdater handler$atomicfu$iv = state$volatile$FU;
        while (true) {
            Object curState = handler$atomicfu$iv.get(this);
            symbol = SelectKt.STATE_REG;
            if (curState == symbol) {
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(state$volatile$FU, this, curState, cont)) {
                    CancellableContinuationKt.invokeOnCancellation(cont, this);
                    break;
                }
            } else if (curState instanceof List) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = state$volatile$FU;
                symbol2 = SelectKt.STATE_REG;
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, curState, symbol2)) {
                    Iterable $this$forEach$iv = (Iterable) curState;
                    for (Object element$iv : $this$forEach$iv) {
                        reregisterClause(element$iv);
                    }
                }
            } else if (curState instanceof ClauseData) {
                cont.resume((CancellableContinuationImpl) Unit.INSTANCE, (Function3<? super Throwable, ? super CancellableContinuationImpl, ? super CoroutineContext, Unit>) ((ClauseData) curState).createOnCancellationAction(this, this.internalResult));
            } else {
                throw new IllegalStateException(("unexpected state: " + curState).toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reregisterClause(Object clauseObject) {
        ClauseData clause = findClause(clauseObject);
        Intrinsics.checkNotNull(clause);
        clause.disposableHandleOrSegment = null;
        clause.indexInSegment = -1;
        register(clause, true);
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public boolean trySelect(Object clauseObject, Object result) {
        return trySelectInternal(clauseObject, result) == 0;
    }

    public final TrySelectDetailedResult trySelectDetailed(Object clauseObject, Object result) {
        TrySelectDetailedResult TrySelectDetailedResult;
        TrySelectDetailedResult = SelectKt.TrySelectDetailedResult(trySelectInternal(clauseObject, result));
        return TrySelectDetailedResult;
    }

    private final int trySelectInternal(Object clauseObject, Object internalResult) {
        boolean tryResume;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        while (true) {
            Object curState = state$volatile$FU.get(this);
            if (!(curState instanceof CancellableContinuation)) {
                symbol2 = SelectKt.STATE_COMPLETED;
                if (!Intrinsics.areEqual(curState, symbol2) && !(curState instanceof ClauseData)) {
                    symbol3 = SelectKt.STATE_CANCELLED;
                    if (Intrinsics.areEqual(curState, symbol3)) {
                        return 2;
                    }
                    symbol4 = SelectKt.STATE_REG;
                    if (Intrinsics.areEqual(curState, symbol4)) {
                        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(state$volatile$FU, this, curState, CollectionsKt.listOf(clauseObject))) {
                            return 1;
                        }
                    } else {
                        if (!(curState instanceof List)) {
                            throw new IllegalStateException(("Unexpected state: " + curState).toString());
                        }
                        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(state$volatile$FU, this, curState, CollectionsKt.plus((Collection<? extends Object>) curState, clauseObject))) {
                            return 1;
                        }
                    }
                } else {
                    return 3;
                }
            } else {
                ClauseData clause = findClause(clauseObject);
                if (clause == null) {
                    continue;
                } else {
                    Function3 onCancellation = clause.createOnCancellationAction(this, internalResult);
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(state$volatile$FU, this, curState, clause)) {
                        CancellableContinuation cont = (CancellableContinuation) curState;
                        this.internalResult = internalResult;
                        tryResume = SelectKt.tryResume(cont, onCancellation);
                        if (tryResume) {
                            return 0;
                        }
                        symbol = SelectKt.NO_RESULT;
                        this.internalResult = symbol;
                        return 2;
                    }
                }
            }
        }
    }

    private final SelectImplementation<R>.ClauseData findClause(Object clauseObject) {
        List clauses = this.clauses;
        Object obj = null;
        if (clauses == null) {
            return null;
        }
        Iterator<T> it = clauses.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            ClauseData it2 = (ClauseData) next;
            if (it2.clauseObject == clauseObject) {
                obj = next;
                break;
            }
        }
        SelectImplementation<R>.ClauseData clauseData = (ClauseData) obj;
        if (clauseData != null) {
            return clauseData;
        }
        throw new IllegalStateException(("Clause with object " + clauseObject + " is not found").toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object complete(Continuation<? super R> continuation) {
        if (DebugKt.getASSERTIONS_ENABLED() && !isSelected()) {
            throw new AssertionError();
        }
        Object obj = state$volatile$FU.get(this);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectImplementation.ClauseData<R of kotlinx.coroutines.selects.SelectImplementation>");
        ClauseData selectedClause = (ClauseData) obj;
        Object internalResult = this.internalResult;
        cleanup(selectedClause);
        if (!DebugKt.getRECOVER_STACK_TRACES()) {
            Object blockArgument = selectedClause.processResult(internalResult);
            return selectedClause.invokeBlock(blockArgument, continuation);
        }
        Object blockArgument2 = processResultAndInvokeBlockRecoveringException(selectedClause, internalResult, continuation);
        return blockArgument2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002c A[Catch: all -> 0x0031, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0031, blocks: (B:12:0x002c, B:18:0x0037), top: B:7:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object processResultAndInvokeBlockRecoveringException(SelectImplementation<R>.ClauseData clauseData, Object internalResult, Continuation<? super R> continuation) {
        SelectImplementation$processResultAndInvokeBlockRecoveringException$1 selectImplementation$processResultAndInvokeBlockRecoveringException$1;
        Object invokeBlock;
        try {
            if (continuation instanceof SelectImplementation$processResultAndInvokeBlockRecoveringException$1) {
                selectImplementation$processResultAndInvokeBlockRecoveringException$1 = (SelectImplementation$processResultAndInvokeBlockRecoveringException$1) continuation;
                if ((selectImplementation$processResultAndInvokeBlockRecoveringException$1.label & Integer.MIN_VALUE) != 0) {
                    selectImplementation$processResultAndInvokeBlockRecoveringException$1.label -= Integer.MIN_VALUE;
                    Object $result = selectImplementation$processResultAndInvokeBlockRecoveringException$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (selectImplementation$processResultAndInvokeBlockRecoveringException$1.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            Object blockArgument = clauseData.processResult(internalResult);
                            selectImplementation$processResultAndInvokeBlockRecoveringException$1.label = 1;
                            invokeBlock = clauseData.invokeBlock(blockArgument, selectImplementation$processResultAndInvokeBlockRecoveringException$1);
                            if (invokeBlock == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            break;
                        case 1:
                            ResultKt.throwOnFailure($result);
                            invokeBlock = $result;
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    return invokeBlock;
                }
            }
            switch (selectImplementation$processResultAndInvokeBlockRecoveringException$1.label) {
            }
            return invokeBlock;
        } catch (Throwable e) {
            if (!DebugKt.getRECOVER_STACK_TRACES()) {
                throw e;
            }
            Continuation it$iv = selectImplementation$processResultAndInvokeBlockRecoveringException$1;
            if (it$iv instanceof CoroutineStackFrame) {
                throw StackTraceRecoveryKt.recoverFromStackFrame(e, (CoroutineStackFrame) it$iv);
            }
            throw e;
        }
        selectImplementation$processResultAndInvokeBlockRecoveringException$1 = new SelectImplementation$processResultAndInvokeBlockRecoveringException$1(this, continuation);
        Object $result2 = selectImplementation$processResultAndInvokeBlockRecoveringException$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }

    private final void cleanup(SelectImplementation<R>.ClauseData selectedClause) {
        Symbol symbol;
        Symbol symbol2;
        if (DebugKt.getASSERTIONS_ENABLED() && !Intrinsics.areEqual(state$volatile$FU.get(this), selectedClause)) {
            throw new AssertionError();
        }
        Iterable clauses = this.clauses;
        if (clauses == null) {
            return;
        }
        Iterable $this$forEach$iv = clauses;
        for (Object element$iv : $this$forEach$iv) {
            ClauseData clause = (ClauseData) element$iv;
            if (clause != selectedClause) {
                clause.dispose();
            }
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = state$volatile$FU;
        symbol = SelectKt.STATE_COMPLETED;
        atomicReferenceFieldUpdater.set(this, symbol);
        symbol2 = SelectKt.NO_RESULT;
        this.internalResult = symbol2;
        this.clauses = null;
    }

    @Override // kotlinx.coroutines.CancelHandler
    public void invoke(Throwable cause) {
        Object cur;
        Symbol symbol;
        Object cur2;
        Symbol symbol2;
        AtomicReferenceFieldUpdater handler$atomicfu$iv = state$volatile$FU;
        do {
            cur = handler$atomicfu$iv.get(this);
            symbol = SelectKt.STATE_COMPLETED;
            if (cur == symbol) {
                return;
            } else {
                cur2 = SelectKt.STATE_CANCELLED;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(handler$atomicfu$iv, this, cur, cur2));
        Iterable clauses = this.clauses;
        if (clauses == null) {
            return;
        }
        Iterable $this$forEach$iv = clauses;
        for (Object element$iv : $this$forEach$iv) {
            ClauseData it = (ClauseData) element$iv;
            it.dispose();
        }
        symbol2 = SelectKt.NO_RESULT;
        this.internalResult = symbol2;
        this.clauses = null;
    }

    /* compiled from: Select.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\b\b\u0080\u0004\u0018\u00002\u00020\u0001BÆ\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012U\u0010\u0003\u001aQ\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0002\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\b¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0005j\u0002`\u0004\u0012U\u0010\f\u001aQ\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0002\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005j\u0002`\r\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u000f\u001a\u00020\u0001\u0012u\u0010\u0010\u001aq\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\b¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0012\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\u0005\u0018\u00010\u0005j\u0004\u0018\u0001`\u0011¢\u0006\u0004\b\u0015\u0010\u0016J\u0014\u0010\u001b\u001a\u00020\u001c2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u00012\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001J\u0018\u0010 \u001a\u00028\u00002\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u0086@¢\u0006\u0002\u0010\"J\u0006\u0010#\u001a\u00020\u000bJ8\u0010$\u001a\u001e\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00052\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001R\u0010\u0010\u0002\u001a\u00020\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R_\u0010\u0003\u001aQ\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0002\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\b¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0005j\u0002`\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R_\u0010\f\u001aQ\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0002\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005j\u0002`\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0081\u0001\u0010\u0010\u001aq\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\b¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0012\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\u0005\u0018\u00010\u0005j\u0004\u0018\u0001`\u00118\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u00018\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0019\u001a\u00020\u001a8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lkotlinx/coroutines/selects/SelectImplementation$ClauseData;", "", "clauseObject", "regFunc", "Lkotlinx/coroutines/selects/RegistrationFunction;", "Lkotlin/Function3;", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "Lkotlinx/coroutines/selects/SelectInstance;", "select", "param", "", "processResFunc", "Lkotlinx/coroutines/selects/ProcessResultFunction;", "clauseResult", "block", "onCancellationConstructor", "Lkotlinx/coroutines/selects/OnCancellationConstructor;", "internalResult", "", "Lkotlin/coroutines/CoroutineContext;", "<init>", "(Lkotlinx/coroutines/selects/SelectImplementation;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)V", "Lkotlin/jvm/functions/Function3;", "disposableHandleOrSegment", "indexInSegment", "", "tryRegisterAsWaiter", "", "Lkotlinx/coroutines/selects/SelectImplementation;", "processResult", "result", "invokeBlock", "argument", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispose", "createOnCancellationAction", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class ClauseData {
        private final Object block;
        public final Object clauseObject;
        public Object disposableHandleOrSegment;
        public int indexInSegment = -1;
        public final Function3<SelectInstance<?>, Object, Object, Function3<Throwable, Object, CoroutineContext, Unit>> onCancellationConstructor;
        private final Object param;
        private final Function3<Object, Object, Object, Object> processResFunc;
        private final Function3<Object, SelectInstance<?>, Object, Unit> regFunc;

        /* JADX WARN: Multi-variable type inference failed */
        public ClauseData(Object clauseObject, Function3<Object, ? super SelectInstance<?>, Object, Unit> function3, Function3<Object, Object, Object, ? extends Object> function32, Object param, Object block, Function3<? super SelectInstance<?>, Object, Object, ? extends Function3<? super Throwable, Object, ? super CoroutineContext, Unit>> function33) {
            this.clauseObject = clauseObject;
            this.regFunc = function3;
            this.processResFunc = function32;
            this.param = param;
            this.block = block;
            this.onCancellationConstructor = function33;
        }

        public final boolean tryRegisterAsWaiter(SelectImplementation<R> select) {
            Symbol symbol;
            Symbol symbol2;
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (((select.getInRegistrationPhase() || select.isCancelled()) ? 1 : 0) == 0) {
                    throw new AssertionError();
                }
            }
            if (DebugKt.getASSERTIONS_ENABLED()) {
                Object obj = ((SelectImplementation) select).internalResult;
                symbol2 = SelectKt.NO_RESULT;
                if ((obj == symbol2 ? 1 : 0) == 0) {
                    throw new AssertionError();
                }
            }
            this.regFunc.invoke(this.clauseObject, select, this.param);
            Object obj2 = ((SelectImplementation) select).internalResult;
            symbol = SelectKt.NO_RESULT;
            return obj2 == symbol;
        }

        public final Object processResult(Object result) {
            return this.processResFunc.invoke(this.clauseObject, this.param, result);
        }

        public final Object invokeBlock(Object argument, Continuation<? super R> continuation) {
            Object block = this.block;
            if (this.param == SelectKt.getPARAM_CLAUSE_0()) {
                Intrinsics.checkNotNull(block, "null cannot be cast to non-null type kotlin.coroutines.SuspendFunction0<R of kotlinx.coroutines.selects.SelectImplementation>");
                return ((Function1) block).invoke(continuation);
            }
            Intrinsics.checkNotNull(block, "null cannot be cast to non-null type kotlin.coroutines.SuspendFunction1<kotlin.Any?, R of kotlinx.coroutines.selects.SelectImplementation>");
            return ((Function2) block).invoke(argument, continuation);
        }

        public final void dispose() {
            Object $this$dispose_u24lambda_u242 = this.disposableHandleOrSegment;
            SelectImplementation<R> selectImplementation = SelectImplementation.this;
            if ($this$dispose_u24lambda_u242 instanceof Segment) {
                ((Segment) $this$dispose_u24lambda_u242).onCancellation(this.indexInSegment, null, selectImplementation.getContext());
                return;
            }
            DisposableHandle disposableHandle = $this$dispose_u24lambda_u242 instanceof DisposableHandle ? (DisposableHandle) $this$dispose_u24lambda_u242 : null;
            if (disposableHandle != null) {
                disposableHandle.dispose();
            }
        }

        public final Function3<Throwable, Object, CoroutineContext, Unit> createOnCancellationAction(SelectInstance<?> select, Object internalResult) {
            Function3<SelectInstance<?>, Object, Object, Function3<Throwable, Object, CoroutineContext, Unit>> function3 = this.onCancellationConstructor;
            if (function3 != null) {
                return function3.invoke(select, this.param, internalResult);
            }
            return null;
        }
    }
}
