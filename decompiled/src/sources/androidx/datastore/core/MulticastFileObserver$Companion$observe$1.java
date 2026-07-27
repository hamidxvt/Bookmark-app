package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.datastore.core.MulticastFileObserver;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: MulticastFileObserver.android.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.datastore.core.MulticastFileObserver$Companion$observe$1", f = "MulticastFileObserver.android.kt", i = {0, 0}, l = {84, 85}, m = "invokeSuspend", n = {"$this$channelFlow", "disposeListener"}, s = {"L$0", "L$1"})
/* loaded from: classes.dex */
final class MulticastFileObserver$Companion$observe$1 extends SuspendLambda implements Function2<ProducerScope<? super Unit>, Continuation<? super Unit>, Object> {
    final /* synthetic */ File $file;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MulticastFileObserver$Companion$observe$1(File file, Continuation<? super MulticastFileObserver$Companion$observe$1> continuation) {
        super(2, continuation);
        this.$file = file;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MulticastFileObserver$Companion$observe$1 multicastFileObserver$Companion$observe$1 = new MulticastFileObserver$Companion$observe$1(this.$file, continuation);
        multicastFileObserver$Companion$observe$1.L$0 = obj;
        return multicastFileObserver$Companion$observe$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProducerScope<? super Unit> producerScope, Continuation<? super Unit> continuation) {
        return ((MulticastFileObserver$Companion$observe$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x006f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0070  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        MulticastFileObserver$Companion$observe$1 multicastFileObserver$Companion$observe$1;
        final ProducerScope $this$channelFlow;
        final DisposableHandle disposeListener;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                multicastFileObserver$Companion$observe$1 = this;
                $this$channelFlow = (ProducerScope) multicastFileObserver$Companion$observe$1.L$0;
                final File file = multicastFileObserver$Companion$observe$1.$file;
                Function1 flowObserver = new Function1<String, Unit>() { // from class: androidx.datastore.core.MulticastFileObserver$Companion$observe$1$flowObserver$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(String str) {
                        invoke2(str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String fileName) {
                        if (Intrinsics.areEqual(fileName, file.getName())) {
                            ChannelsKt.trySendBlocking($this$channelFlow, Unit.INSTANCE);
                        }
                    }
                };
                MulticastFileObserver.Companion companion = MulticastFileObserver.INSTANCE;
                File parentFile = multicastFileObserver$Companion$observe$1.$file.getParentFile();
                Intrinsics.checkNotNull(parentFile);
                disposeListener = companion.observe(parentFile, flowObserver);
                multicastFileObserver$Companion$observe$1.L$0 = $this$channelFlow;
                multicastFileObserver$Companion$observe$1.L$1 = disposeListener;
                multicastFileObserver$Companion$observe$1.label = 1;
                if ($this$channelFlow.send(Unit.INSTANCE, multicastFileObserver$Companion$observe$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                multicastFileObserver$Companion$observe$1.L$0 = null;
                multicastFileObserver$Companion$observe$1.L$1 = null;
                multicastFileObserver$Companion$observe$1.label = 2;
                if (ProduceKt.awaitClose($this$channelFlow, new Function0<Unit>() { // from class: androidx.datastore.core.MulticastFileObserver$Companion$observe$1.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        DisposableHandle.this.dispose();
                    }
                }, multicastFileObserver$Companion$observe$1) != coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                multicastFileObserver$Companion$observe$1 = this;
                disposeListener = (DisposableHandle) multicastFileObserver$Companion$observe$1.L$1;
                $this$channelFlow = (ProducerScope) multicastFileObserver$Companion$observe$1.L$0;
                ResultKt.throwOnFailure($result);
                multicastFileObserver$Companion$observe$1.L$0 = null;
                multicastFileObserver$Companion$observe$1.L$1 = null;
                multicastFileObserver$Companion$observe$1.label = 2;
                if (ProduceKt.awaitClose($this$channelFlow, new Function0<Unit>() { // from class: androidx.datastore.core.MulticastFileObserver$Companion$observe$1.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        DisposableHandle.this.dispose();
                    }
                }, multicastFileObserver$Companion$observe$1) != coroutine_suspended) {
                }
                break;
            case 2:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
