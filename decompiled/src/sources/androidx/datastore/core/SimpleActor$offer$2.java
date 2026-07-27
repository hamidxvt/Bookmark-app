package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: SimpleActor.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.datastore.core.SimpleActor$offer$2", f = "SimpleActor.kt", i = {}, l = {121, 121}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class SimpleActor$offer$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ SimpleActor<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SimpleActor$offer$2(SimpleActor<T> simpleActor, Continuation<? super SimpleActor$offer$2> continuation) {
        super(2, continuation);
        this.this$0 = simpleActor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SimpleActor$offer$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SimpleActor$offer$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x005c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0070 -> B:7:0x0073). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        SimpleActor$offer$2 simpleActor$offer$2;
        AtomicInt atomicInt;
        Function2 function2;
        SimpleActor$offer$2 simpleActor$offer$22;
        Object obj;
        Object $result2;
        CoroutineScope coroutineScope;
        Function2 function22;
        Object receive;
        AtomicInt atomicInt2;
        Object $result3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                simpleActor$offer$2 = this;
                atomicInt = ((SimpleActor) simpleActor$offer$2.this$0).remainingMessages;
                if (!(atomicInt.get() > 0)) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                coroutineScope = ((SimpleActor) simpleActor$offer$2.this$0).scope;
                CoroutineScopeKt.ensureActive(coroutineScope);
                function22 = ((SimpleActor) simpleActor$offer$2.this$0).consumeMessage;
                simpleActor$offer$2.L$0 = function22;
                simpleActor$offer$2.label = 1;
                receive = ((SimpleActor) simpleActor$offer$2.this$0).messageQueue.receive(simpleActor$offer$2);
                if (receive == $result3) {
                    return $result3;
                }
                Object obj2 = $result3;
                $result2 = $result;
                $result = receive;
                function2 = function22;
                simpleActor$offer$22 = simpleActor$offer$2;
                obj = obj2;
                simpleActor$offer$22.L$0 = null;
                simpleActor$offer$22.label = 2;
                if (function2.invoke($result, simpleActor$offer$22) != obj) {
                    return obj;
                }
                $result = $result2;
                $result3 = obj;
                simpleActor$offer$2 = simpleActor$offer$22;
                atomicInt2 = ((SimpleActor) simpleActor$offer$2.this$0).remainingMessages;
                if (atomicInt2.decrementAndGet() == 0) {
                    return Unit.INSTANCE;
                }
                coroutineScope = ((SimpleActor) simpleActor$offer$2.this$0).scope;
                CoroutineScopeKt.ensureActive(coroutineScope);
                function22 = ((SimpleActor) simpleActor$offer$2.this$0).consumeMessage;
                simpleActor$offer$2.L$0 = function22;
                simpleActor$offer$2.label = 1;
                receive = ((SimpleActor) simpleActor$offer$2.this$0).messageQueue.receive(simpleActor$offer$2);
                if (receive == $result3) {
                }
            case 1:
                Function2 function23 = (Function2) this.L$0;
                ResultKt.throwOnFailure($result);
                function2 = function23;
                simpleActor$offer$22 = this;
                obj = $result3;
                $result2 = $result;
                simpleActor$offer$22.L$0 = null;
                simpleActor$offer$22.label = 2;
                if (function2.invoke($result, simpleActor$offer$22) != obj) {
                }
                break;
            case 2:
                simpleActor$offer$2 = this;
                ResultKt.throwOnFailure($result);
                atomicInt2 = ((SimpleActor) simpleActor$offer$2.this$0).remainingMessages;
                if (atomicInt2.decrementAndGet() == 0) {
                }
                coroutineScope = ((SimpleActor) simpleActor$offer$2.this$0).scope;
                CoroutineScopeKt.ensureActive(coroutineScope);
                function22 = ((SimpleActor) simpleActor$offer$2.this$0).consumeMessage;
                simpleActor$offer$2.L$0 = function22;
                simpleActor$offer$2.label = 1;
                receive = ((SimpleActor) simpleActor$offer$2.this$0).messageQueue.receive(simpleActor$offer$2);
                if (receive == $result3) {
                }
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
