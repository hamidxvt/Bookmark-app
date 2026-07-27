package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: RecentActivityFragment.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.ui.fragment.RecentActivityFragment", f = "RecentActivityFragment.kt", i = {0, 0, 0, 0, 0, 0}, l = {472}, m = "parseActivityList", n = {"this", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "list", "obj", "detailsJson", "i"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0"})
/* loaded from: classes5.dex */
final class RecentActivityFragment$parseActivityList$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RecentActivityFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RecentActivityFragment$parseActivityList$1(RecentActivityFragment recentActivityFragment, Continuation<? super RecentActivityFragment$parseActivityList$1> continuation) {
        super(continuation);
        this.this$0 = recentActivityFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object parseActivityList;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        parseActivityList = this.this$0.parseActivityList(null, this);
        return parseActivityList;
    }
}
