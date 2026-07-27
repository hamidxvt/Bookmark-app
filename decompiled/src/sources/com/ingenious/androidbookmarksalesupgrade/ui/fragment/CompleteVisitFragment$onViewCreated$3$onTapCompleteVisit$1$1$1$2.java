package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.util.Log;
import android.widget.Toast;
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

/* compiled from: CompleteVisitFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.ui.fragment.CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1$1$1$2", f = "CompleteVisitFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
final class CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1$1$1$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Exception $e;
    int label;
    final /* synthetic */ CompleteVisitFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1$1$1$2(Exception exc, CompleteVisitFragment completeVisitFragment, Continuation<? super CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1$1$1$2> continuation) {
        super(2, continuation);
        this.$e = exc;
        this.this$0 = completeVisitFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1$1$1$2(this.$e, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1$1$1$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                Log.d("CompleteVisitFragment", "Failed to process image: " + this.$e.getMessage());
                Toast.makeText(this.this$0.requireContext(), "Failed to process image", 0).show();
                this.this$0.getBinding().progressBar.setVisibility(8);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
