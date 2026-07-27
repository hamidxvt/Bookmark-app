package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import com.ingenious.androidbookmarksalesupgrade.R;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: VisitAdoptionQuantityFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionQuantityFragment$onViewCreated$3$1", f = "VisitAdoptionQuantityFragment.kt", i = {}, l = {64}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
final class VisitAdoptionQuantityFragment$onViewCreated$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ VisitAdoptionQuantityFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VisitAdoptionQuantityFragment$onViewCreated$3$1(VisitAdoptionQuantityFragment visitAdoptionQuantityFragment, Continuation<? super VisitAdoptionQuantityFragment$onViewCreated$3$1> continuation) {
        super(2, continuation);
        this.this$0 = visitAdoptionQuantityFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VisitAdoptionQuantityFragment$onViewCreated$3$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VisitAdoptionQuantityFragment$onViewCreated$3$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                if (DelayKt.delay(2000L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.hideLoadingAlert();
        VisitAdoptionQuantityFragment visitAdoptionQuantityFragment = this.this$0;
        int i = R.layout.successful_adoption;
        final VisitAdoptionQuantityFragment visitAdoptionQuantityFragment2 = this.this$0;
        visitAdoptionQuantityFragment.showLoadingAlert(i, new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionQuantityFragment$onViewCreated$3$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit invokeSuspend$lambda$1;
                invokeSuspend$lambda$1 = VisitAdoptionQuantityFragment$onViewCreated$3$1.invokeSuspend$lambda$1(VisitAdoptionQuantityFragment.this, (View) obj);
                return invokeSuspend$lambda$1;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(final VisitAdoptionQuantityFragment this$0, View view) {
        AppCompatButton btnDone = (AppCompatButton) view.findViewById(R.id.btnCheckAdoptions);
        btnDone.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoptionQuantityFragment$onViewCreated$3$1$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VisitAdoptionQuantityFragment$onViewCreated$3$1.invokeSuspend$lambda$1$lambda$0(VisitAdoptionQuantityFragment.this, view2);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$1$lambda$0(VisitAdoptionQuantityFragment this$0, View it) {
        this$0.hideLoadingAlert();
        FragmentActivity activity = this$0.getActivity();
        if (activity != null) {
            activity.finish();
        }
    }
}
