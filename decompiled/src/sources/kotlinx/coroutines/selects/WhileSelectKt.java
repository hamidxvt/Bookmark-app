package kotlinx.coroutines.selects;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;

/* compiled from: WhileSelect.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a/\u0010\u0000\u001a\u00020\u00012\u001f\b\u0004\u0010\u0002\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0006H\u0087H¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"whileSelect", "", "builder", "Lkotlin/Function1;", "Lkotlinx/coroutines/selects/SelectBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class WhileSelectKt {
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x005b -> B:12:0x0060). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object whileSelect(Function1<? super SelectBuilder<? super Boolean>, Unit> function1, Continuation<? super Unit> continuation) {
        WhileSelectKt$whileSelect$1 whileSelectKt$whileSelect$1;
        Function1 builder;
        Object $result;
        Object obj;
        if (continuation instanceof WhileSelectKt$whileSelect$1) {
            whileSelectKt$whileSelect$1 = (WhileSelectKt$whileSelect$1) continuation;
            if ((whileSelectKt$whileSelect$1.label & Integer.MIN_VALUE) != 0) {
                whileSelectKt$whileSelect$1.label -= Integer.MIN_VALUE;
                Object $result2 = whileSelectKt$whileSelect$1.result;
                Object $result3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (whileSelectKt$whileSelect$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result2);
                        builder = function1;
                        SelectImplementation $this$select_u24lambda_u240$iv = new SelectImplementation(whileSelectKt$whileSelect$1.getContext());
                        builder.invoke($this$select_u24lambda_u240$iv);
                        whileSelectKt$whileSelect$1.L$0 = builder;
                        whileSelectKt$whileSelect$1.label = 1;
                        Object doSelect = $this$select_u24lambda_u240$iv.doSelect(whileSelectKt$whileSelect$1);
                        if (doSelect != $result3) {
                            return $result3;
                        }
                        Object obj2 = $result3;
                        $result = $result2;
                        $result2 = doSelect;
                        obj = obj2;
                        if (((Boolean) $result2).booleanValue()) {
                            return Unit.INSTANCE;
                        }
                        $result2 = $result;
                        $result3 = obj;
                        SelectImplementation $this$select_u24lambda_u240$iv2 = new SelectImplementation(whileSelectKt$whileSelect$1.getContext());
                        builder.invoke($this$select_u24lambda_u240$iv2);
                        whileSelectKt$whileSelect$1.L$0 = builder;
                        whileSelectKt$whileSelect$1.label = 1;
                        Object doSelect2 = $this$select_u24lambda_u240$iv2.doSelect(whileSelectKt$whileSelect$1);
                        if (doSelect2 != $result3) {
                        }
                    case 1:
                        builder = (Function1) whileSelectKt$whileSelect$1.L$0;
                        ResultKt.throwOnFailure($result2);
                        obj = $result3;
                        $result = $result2;
                        if (((Boolean) $result2).booleanValue()) {
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        whileSelectKt$whileSelect$1 = new WhileSelectKt$whileSelect$1(continuation);
        Object $result22 = whileSelectKt$whileSelect$1.result;
        Object $result32 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (whileSelectKt$whileSelect$1.label) {
        }
    }

    private static final Object whileSelect$$forInline(Function1<? super SelectBuilder<? super Boolean>, Unit> function1, Continuation<? super Unit> continuation) {
        InlineMarker.mark(3);
        Continuation continuation2 = null;
        continuation2.getContext();
        throw null;
    }
}
