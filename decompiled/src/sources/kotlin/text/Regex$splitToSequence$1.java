package kotlin.text;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* compiled from: Regex.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "kotlin.text.Regex$splitToSequence$1", f = "Regex.kt", i = {1, 1, 1}, l = {275, 283, 287}, m = "invokeSuspend", n = {"$this$sequence", "matcher", "splitCount"}, s = {"L$0", "L$1", "I$0"})
/* loaded from: classes17.dex */
final class Regex$splitToSequence$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super String>, Continuation<? super Unit>, Object> {
    final /* synthetic */ CharSequence $input;
    final /* synthetic */ int $limit;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ Regex this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Regex$splitToSequence$1(Regex regex, CharSequence charSequence, int i, Continuation<? super Regex$splitToSequence$1> continuation) {
        super(2, continuation);
        this.this$0 = regex;
        this.$input = charSequence;
        this.$limit = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Regex$splitToSequence$1 regex$splitToSequence$1 = new Regex$splitToSequence$1(this.this$0, this.$input, this.$limit, continuation);
        regex$splitToSequence$1.L$0 = obj;
        return regex$splitToSequence$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super String> sequenceScope, Continuation<? super Unit> continuation) {
        return ((Regex$splitToSequence$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0071 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a4 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x006f -> B:10:0x0072). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        Pattern pattern;
        Matcher matcher;
        Regex$splitToSequence$1 regex$splitToSequence$1;
        SequenceScope $this$sequence;
        int splitCount;
        int nextStart;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                SequenceScope $this$sequence2 = (SequenceScope) this.L$0;
                pattern = this.this$0.nativePattern;
                matcher = pattern.matcher(this.$input);
                if (this.$limit == 1 || !matcher.find()) {
                    this.label = 1;
                    if ($this$sequence2.yield(this.$input.toString(), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                regex$splitToSequence$1 = this;
                $this$sequence = $this$sequence2;
                splitCount = 0;
                nextStart = 0;
                regex$splitToSequence$1.L$0 = $this$sequence;
                regex$splitToSequence$1.L$1 = matcher;
                regex$splitToSequence$1.I$0 = splitCount;
                regex$splitToSequence$1.label = 2;
                if ($this$sequence.yield(regex$splitToSequence$1.$input.subSequence(nextStart, matcher.start()).toString(), regex$splitToSequence$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                nextStart = matcher.end();
                splitCount++;
                if (splitCount != regex$splitToSequence$1.$limit - 1 || !matcher.find()) {
                    regex$splitToSequence$1.L$0 = null;
                    regex$splitToSequence$1.L$1 = null;
                    regex$splitToSequence$1.label = 3;
                    if ($this$sequence.yield(regex$splitToSequence$1.$input.subSequence(nextStart, regex$splitToSequence$1.$input.length()).toString(), regex$splitToSequence$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                regex$splitToSequence$1.L$0 = $this$sequence;
                regex$splitToSequence$1.L$1 = matcher;
                regex$splitToSequence$1.I$0 = splitCount;
                regex$splitToSequence$1.label = 2;
                if ($this$sequence.yield(regex$splitToSequence$1.$input.subSequence(nextStart, matcher.start()).toString(), regex$splitToSequence$1) == coroutine_suspended) {
                }
                nextStart = matcher.end();
                splitCount++;
                if (splitCount != regex$splitToSequence$1.$limit - 1) {
                }
                regex$splitToSequence$1.L$0 = null;
                regex$splitToSequence$1.L$1 = null;
                regex$splitToSequence$1.label = 3;
                if ($this$sequence.yield(regex$splitToSequence$1.$input.subSequence(nextStart, regex$splitToSequence$1.$input.length()).toString(), regex$splitToSequence$1) == coroutine_suspended) {
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            case 2:
                splitCount = this.I$0;
                matcher = (Matcher) this.L$1;
                $this$sequence = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure($result);
                regex$splitToSequence$1 = this;
                nextStart = matcher.end();
                splitCount++;
                if (splitCount != regex$splitToSequence$1.$limit - 1) {
                }
                regex$splitToSequence$1.L$0 = null;
                regex$splitToSequence$1.L$1 = null;
                regex$splitToSequence$1.label = 3;
                if ($this$sequence.yield(regex$splitToSequence$1.$input.subSequence(nextStart, regex$splitToSequence$1.$input.length()).toString(), regex$splitToSequence$1) == coroutine_suspended) {
                }
                return Unit.INSTANCE;
            case 3:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
