package kotlinx.coroutines.channels;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.primitives.Longs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KFunction;
import kotlin.text.StringsKt;
import kotlin.time.DurationKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectClause1Impl;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectClause2Impl;
import kotlinx.coroutines.selects.SelectImplementation;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.TrySelectDetailedResult;

/* compiled from: BufferedChannel.kt */
@Metadata(d1 = {"\u0000Ö\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b1\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0004ï\u0001ð\u0001B3\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\"\b\u0002\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0006¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00028\u0000H\u0096@¢\u0006\u0002\u0010#J\u0016\u0010$\u001a\u00020\b2\u0006\u0010\"\u001a\u00028\u0000H\u0082@¢\u0006\u0002\u0010#J4\u0010%\u001a\u00020\b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010\"\u001a\u00028\u00002\u0006\u0010(\u001a\u00020\u0011H\u0082@¢\u0006\u0002\u0010)J\"\u0010*\u001a\u00020\b*\u00020+2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u0004H\u0002J#\u0010,\u001a\u00020\b2\u0006\u0010\"\u001a\u00028\u00002\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\b0.H\u0002¢\u0006\u0002\u0010/J\u001d\u00100\u001a\b\u0012\u0004\u0012\u00020\b012\u0006\u0010\"\u001a\u00028\u0000H\u0016¢\u0006\u0004\b2\u00103J\u0018\u00104\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00028\u0000H\u0090@¢\u0006\u0004\b5\u0010#Jê\u0001\u00106\u001a\u0002H7\"\u0004\b\u0001\u001072\u0006\u0010\"\u001a\u00028\u00002\b\u00108\u001a\u0004\u0018\u0001092\f\u0010:\u001a\b\u0012\u0004\u0012\u0002H70;2<\u0010<\u001a8\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u001e¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(@\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(A\u0012\u0004\u0012\u0002H70=2\f\u0010B\u001a\b\u0012\u0004\u0012\u0002H70;2h\b\u0002\u0010C\u001ab\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u001e¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(@\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(A\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b((\u0012\u0004\u0012\u0002H70DH\u0082\b¢\u0006\u0002\u0010EJ\u001d\u0010F\u001a\b\u0012\u0004\u0012\u00020\b012\u0006\u0010\"\u001a\u00028\u0000H\u0004¢\u0006\u0004\bG\u00103JX\u0010H\u001a\u00020\b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010\"\u001a\u00028\u00002\u0006\u0010(\u001a\u00020\u00112\u0006\u00108\u001a\u00020+2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\b0;2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\b0;H\u0082\b¢\u0006\u0002\u0010IJE\u0010J\u001a\u00020\u00042\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010\"\u001a\u00028\u00002\u0006\u0010(\u001a\u00020\u00112\b\u00108\u001a\u0004\u0018\u0001092\u0006\u0010K\u001a\u00020\u001aH\u0002¢\u0006\u0002\u0010LJE\u0010M\u001a\u00020\u00042\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010\"\u001a\u00028\u00002\u0006\u0010(\u001a\u00020\u00112\b\u00108\u001a\u0004\u0018\u0001092\u0006\u0010K\u001a\u00020\u001aH\u0002¢\u0006\u0002\u0010LJ\u0010\u0010N\u001a\u00020\u001a2\u0006\u0010O\u001a\u00020\u0011H\u0003J\u0010\u0010P\u001a\u00020\u001a2\u0006\u0010Q\u001a\u00020\u0011H\u0002J\r\u0010N\u001a\u00020\u001aH\u0010¢\u0006\u0002\bRJ\u0019\u0010S\u001a\u00020\u001a*\u0002092\u0006\u0010\"\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010TJ\b\u0010U\u001a\u00020\bH\u0014J\b\u0010V\u001a\u00020\bH\u0014J\u000e\u0010W\u001a\u00028\u0000H\u0096@¢\u0006\u0002\u0010XJ;\u0010Y\u001a\u00118\u0000¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(\"2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020\u0011H\u0082@¢\u0006\u0002\u0010[J\"\u0010\\\u001a\u00020\b*\u00020+2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u0004H\u0002J\u0016\u0010]\u001a\u00020\b2\f\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00000.H\u0002J\u0016\u0010^\u001a\b\u0012\u0004\u0012\u00028\u000001H\u0096@¢\u0006\u0004\b_\u0010XJ4\u0010`\u001a\b\u0012\u0004\u0012\u00028\u0000012\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020\u0011H\u0082@¢\u0006\u0004\ba\u0010[J\u001c\u0010b\u001a\u00020\b2\u0012\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u0000010.H\u0002J\u0015\u0010c\u001a\b\u0012\u0004\u0012\u00028\u000001H\u0016¢\u0006\u0004\bd\u0010eJ\u0010\u0010f\u001a\u00020\b2\u0006\u0010g\u001a\u00020\u0011H\u0004J÷\u0001\u0010h\u001a\u0002H7\"\u0004\b\u0001\u001072\b\u00108\u001a\u0004\u0018\u0001092!\u0010i\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H70\u00072Q\u0010<\u001aM\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u001e¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(@\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(A\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(Z\u0012\u0004\u0012\u0002H70j2\f\u0010B\u001a\b\u0012\u0004\u0012\u0002H70;2S\b\u0002\u0010C\u001aM\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u001e¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(@\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(A\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(Z\u0012\u0004\u0012\u0002H70jH\u0082\b¢\u0006\u0002\u0010kJ`\u0010l\u001a\u00020\b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020\u00112\u0006\u00108\u001a\u00020+2!\u0010i\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\b0\u00072\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\b0;H\u0082\bJ2\u0010m\u001a\u0004\u0018\u0001092\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020\u00112\b\u00108\u001a\u0004\u0018\u000109H\u0002J2\u0010n\u001a\u0004\u0018\u0001092\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020\u00112\b\u00108\u001a\u0004\u0018\u000109H\u0002J\"\u0010o\u001a\u00020\u001a*\u0002092\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u0004H\u0002J\b\u0010p\u001a\u00020\bH\u0002J&\u0010q\u001a\u00020\u001a2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010r\u001a\u00020\u0011H\u0002J&\u0010s\u001a\u00020\u001a2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010r\u001a\u00020\u0011H\u0002J\u0012\u0010t\u001a\u00020\b2\b\b\u0002\u0010u\u001a\u00020\u0011H\u0002J\u0015\u0010v\u001a\u00020\b2\u0006\u0010w\u001a\u00020\u0011H\u0000¢\u0006\u0002\bxJ \u0010\u007f\u001a\u00020\b2\f\u0010\u0080\u0001\u001a\u0007\u0012\u0002\b\u00030\u0081\u00012\b\u0010\"\u001a\u0004\u0018\u000109H\u0014J%\u0010\u0082\u0001\u001a\u00020\b2\u0006\u0010\"\u001a\u00028\u00002\f\u0010\u0080\u0001\u001a\u0007\u0012\u0002\b\u00030\u0081\u0001H\u0002¢\u0006\u0003\u0010\u0083\u0001J!\u0010\u0084\u0001\u001a\u0004\u0018\u0001092\t\u0010\u0085\u0001\u001a\u0004\u0018\u0001092\t\u0010\u0086\u0001\u001a\u0004\u0018\u000109H\u0002J\"\u0010\u0092\u0001\u001a\u00020\b2\f\u0010\u0080\u0001\u001a\u0007\u0012\u0002\b\u00030\u0081\u00012\t\u0010\u0085\u0001\u001a\u0004\u0018\u000109H\u0002J\u0017\u0010\u0093\u0001\u001a\u00020\b2\f\u0010\u0080\u0001\u001a\u0007\u0012\u0002\b\u00030\u0081\u0001H\u0002J!\u0010\u0094\u0001\u001a\u0004\u0018\u0001092\t\u0010\u0085\u0001\u001a\u0004\u0018\u0001092\t\u0010\u0086\u0001\u001a\u0004\u0018\u000109H\u0002J!\u0010\u0095\u0001\u001a\u0004\u0018\u0001092\t\u0010\u0085\u0001\u001a\u0004\u0018\u0001092\t\u0010\u0086\u0001\u001a\u0004\u0018\u000109H\u0002J!\u0010\u0096\u0001\u001a\u0004\u0018\u0001092\t\u0010\u0085\u0001\u001a\u0004\u0018\u0001092\t\u0010\u0086\u0001\u001a\u0004\u0018\u000109H\u0002J\u0011\u0010\u009f\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000 \u0001H\u0096\u0002J\t\u0010ª\u0001\u001a\u00020\bH\u0014J\u0015\u0010«\u0001\u001a\u00020\u001a2\n\u0010¬\u0001\u001a\u0005\u0018\u00010\u009b\u0001H\u0016J\u0013\u0010\u00ad\u0001\u001a\u00020\u001a2\n\u0010¬\u0001\u001a\u0005\u0018\u00010\u009b\u0001J\u0007\u0010\u00ad\u0001\u001a\u00020\bJ \u0010\u00ad\u0001\u001a\u00020\b2\u0011\u0010¬\u0001\u001a\f\u0018\u00010¯\u0001j\u0005\u0018\u0001`®\u0001¢\u0006\u0003\u0010°\u0001J\u001b\u0010±\u0001\u001a\u00020\u001a2\n\u0010¬\u0001\u001a\u0005\u0018\u00010\u009b\u0001H\u0010¢\u0006\u0003\b²\u0001J\u001e\u0010³\u0001\u001a\u00020\u001a2\n\u0010¬\u0001\u001a\u0005\u0018\u00010\u009b\u00012\u0007\u0010\u00ad\u0001\u001a\u00020\u001aH\u0014J\t\u0010´\u0001\u001a\u00020\bH\u0002J1\u0010µ\u0001\u001a\u00020\b2&\u0010¶\u0001\u001a!\u0012\u0017\u0012\u0015\u0018\u00010\u009b\u0001¢\u0006\r\b>\u0012\t\b?\u0012\u0005\b\b(¬\u0001\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\t\u0010·\u0001\u001a\u00020\bH\u0002J\t\u0010¸\u0001\u001a\u00020\bH\u0002J\t\u0010¹\u0001\u001a\u00020\bH\u0002J\t\u0010º\u0001\u001a\u00020\bH\u0002J\u0018\u0010¼\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0007\u0010½\u0001\u001a\u00020\u0011H\u0002J\u0012\u0010¾\u0001\u001a\u00020\b2\u0007\u0010½\u0001\u001a\u00020\u0011H\u0002J\u000f\u0010¿\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0002J\u0018\u0010À\u0001\u001a\u00020\u00112\r\u0010Á\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0002J\u0018\u0010Â\u0001\u001a\u00020\b2\r\u0010Á\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0002J \u0010Ã\u0001\u001a\u00020\b2\r\u0010Á\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\r\u0010Ä\u0001\u001a\u00020\b*\u00020+H\u0002J\r\u0010Å\u0001\u001a\u00020\b*\u00020+H\u0002J\u0016\u0010Æ\u0001\u001a\u00020\b*\u00020+2\u0007\u0010Ç\u0001\u001a\u00020\u001aH\u0002J\u001b\u0010Ï\u0001\u001a\u00020\u001a2\u0007\u0010Ð\u0001\u001a\u00020\u00112\u0007\u0010Ì\u0001\u001a\u00020\u001aH\u0002J\u000f\u0010Ó\u0001\u001a\u00020\u001aH\u0000¢\u0006\u0003\bÔ\u0001J'\u0010Õ\u0001\u001a\u00020\u001a2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010w\u001a\u00020\u0011H\u0002J)\u0010Ö\u0001\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001e2\u0007\u0010×\u0001\u001a\u00020\u00112\r\u0010Ø\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0002J)\u0010Ù\u0001\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001e2\u0007\u0010×\u0001\u001a\u00020\u00112\r\u0010Ø\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0002J2\u0010Ú\u0001\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001e2\u0007\u0010×\u0001\u001a\u00020\u00112\r\u0010Ø\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0007\u0010Û\u0001\u001a\u00020\u0011H\u0002J!\u0010Ü\u0001\u001a\u00020\b2\u0007\u0010×\u0001\u001a\u00020\u00112\r\u0010Ø\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0002J\u0012\u0010Ý\u0001\u001a\u00020\b2\u0007\u0010Þ\u0001\u001a\u00020\u0011H\u0002J\u0012\u0010ß\u0001\u001a\u00020\b2\u0007\u0010Þ\u0001\u001a\u00020\u0011H\u0002J\n\u0010à\u0001\u001a\u00030á\u0001H\u0016J\u0010\u0010â\u0001\u001a\u00030á\u0001H\u0000¢\u0006\u0003\bã\u0001J\u0007\u0010ä\u0001\u001a\u00020\bJJ\u0010å\u0001\u001a#\u0012\u0005\u0012\u00030\u009b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000001\u0012\u0005\u0012\u00030\u009c\u0001\u0012\u0004\u0012\u00020\b0æ\u0001*\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00028\u0000`\u0006H\u0002¢\u0006\u0003\u0010ç\u0001J4\u0010è\u0001\u001a\u00020\b2\b\u0010¬\u0001\u001a\u00030\u009b\u00012\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u0000012\b\u0010é\u0001\u001a\u00030\u009c\u0001H\u0002¢\u0006\u0006\bê\u0001\u0010ë\u0001JM\u0010ì\u0001\u001a\u001e\u0012\u0005\u0012\u00030\u009b\u0001\u0012\u0006\u0012\u0004\u0018\u000109\u0012\u0005\u0012\u00030\u009c\u0001\u0012\u0004\u0012\u00020\b0j*\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00028\u0000`\u00062\u0006\u0010\"\u001a\u00028\u0000H\u0002¢\u0006\u0003\u0010í\u0001JD\u0010ì\u0001\u001a\u001d\u0012\u0005\u0012\u00030\u009b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u0005\u0012\u00030\u009c\u0001\u0012\u0004\u0012\u00020\b0æ\u0001*\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00028\u0000`\u0006H\u0002¢\u0006\u0003\u0010ç\u0001J+\u0010î\u0001\u001a\u00020\b2\b\u0010¬\u0001\u001a\u00030\u009b\u00012\u0006\u0010\"\u001a\u00028\u00002\b\u0010é\u0001\u001a\u00030\u009c\u0001H\u0002¢\u0006\u0003\u0010ë\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u00068\u0000X\u0081\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\t\u0010\f\u001a\u00020\rX\u0082\u0004R\t\u0010\u000e\u001a\u00020\rX\u0082\u0004R\t\u0010\u000f\u001a\u00020\rX\u0082\u0004R\u0014\u0010\u0010\u001a\u00020\u00118@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00118@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0014\u0010\u0016\u001a\u00020\u00118BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R\t\u0010\u0018\u001a\u00020\rX\u0082\u0004R\u0014\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001bR\u0015\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001e0\u001dX\u0082\u0004R\u0015\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001e0\u001dX\u0082\u0004R\u0015\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001e0\u001dX\u0082\u0004R,\u0010y\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00000z8VX\u0096\u0004¢\u0006\f\u0012\u0004\b{\u0010|\u001a\u0004\b}\u0010~R%\u0010\u0087\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000\u0088\u00018VX\u0096\u0004¢\u0006\u000f\u0012\u0005\b\u0089\u0001\u0010|\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001R+\u0010\u008c\u0001\u001a\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u0000010\u0088\u00018VX\u0096\u0004¢\u0006\u000f\u0012\u0005\b\u008d\u0001\u0010|\u001a\u0006\b\u008e\u0001\u0010\u008b\u0001R'\u0010\u008f\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0088\u00018VX\u0096\u0004¢\u0006\u000f\u0012\u0005\b\u0090\u0001\u0010|\u001a\u0006\b\u0091\u0001\u0010\u008b\u0001R\u008f\u0001\u0010\u0097\u0001\u001ax\u0012\u0019\u0012\u0017\u0012\u0002\b\u00030\u0081\u0001¢\u0006\r\b>\u0012\t\b?\u0012\u0005\b\b(\u0080\u0001\u0012\u0016\u0012\u0014\u0018\u000109¢\u0006\r\b>\u0012\t\b?\u0012\u0005\b\b(\u0099\u0001\u0012\u0016\u0012\u0014\u0018\u000109¢\u0006\r\b>\u0012\t\b?\u0012\u0005\b\b(\u009a\u0001\u0012 \u0012\u001e\u0012\u0005\u0012\u00030\u009b\u0001\u0012\u0006\u0012\u0004\u0018\u000109\u0012\u0005\u0012\u00030\u009c\u0001\u0012\u0004\u0012\u00020\b0j\u0018\u00010jj\u0005\u0018\u0001`\u0098\u0001X\u0082\u0004¢\u0006\f\n\u0003\u0010\u009e\u0001\u0012\u0005\b\u009d\u0001\u0010|R\u0012\u0010¡\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001090\u001dX\u0082\u0004R\u001a\u0010¢\u0001\u001a\u0005\u0018\u00010\u009b\u00018DX\u0084\u0004¢\u0006\b\u001a\u0006\b£\u0001\u0010¤\u0001R\u0018\u0010¥\u0001\u001a\u00030\u009b\u00018DX\u0084\u0004¢\u0006\b\u001a\u0006\b¦\u0001\u0010¤\u0001R\u0018\u0010§\u0001\u001a\u00030\u009b\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b¨\u0001\u0010¤\u0001R\u0012\u0010©\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001090\u001dX\u0082\u0004R\u0016\u0010»\u0001\u001a\u00020\u001a8TX\u0094\u0004¢\u0006\u0007\u001a\u0005\b»\u0001\u0010\u001bR\u001d\u0010È\u0001\u001a\u00020\u001a8VX\u0097\u0004¢\u0006\u000e\u0012\u0005\bÉ\u0001\u0010|\u001a\u0005\bÈ\u0001\u0010\u001bR\u001b\u0010Ê\u0001\u001a\u00020\u001a*\u00020\u00118BX\u0082\u0004¢\u0006\b\u001a\u0006\bÊ\u0001\u0010Ë\u0001R\u001d\u0010Ì\u0001\u001a\u00020\u001a8VX\u0097\u0004¢\u0006\u000e\u0012\u0005\bÍ\u0001\u0010|\u001a\u0005\bÌ\u0001\u0010\u001bR\u001b\u0010Î\u0001\u001a\u00020\u001a*\u00020\u00118BX\u0082\u0004¢\u0006\b\u001a\u0006\bÎ\u0001\u0010Ë\u0001R\u001d\u0010Ñ\u0001\u001a\u00020\u001a8VX\u0097\u0004¢\u0006\u000e\u0012\u0005\bÒ\u0001\u0010|\u001a\u0005\bÑ\u0001\u0010\u001b¨\u0006ñ\u0001"}, d2 = {"Lkotlinx/coroutines/channels/BufferedChannel;", "E", "Lkotlinx/coroutines/channels/Channel;", "capacity", "", "onUndeliveredElement", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "Lkotlin/Function1;", "", "<init>", "(ILkotlin/jvm/functions/Function1;)V", "Lkotlin/jvm/functions/Function1;", "sendersAndCloseStatus", "Lkotlinx/atomicfu/AtomicLong;", "receivers", "bufferEnd", "sendersCounter", "", "getSendersCounter$kotlinx_coroutines_core", "()J", "receiversCounter", "getReceiversCounter$kotlinx_coroutines_core", "bufferEndCounter", "getBufferEndCounter", "completedExpandBuffersAndPauseFlag", "isRendezvousOrUnlimited", "", "()Z", "sendSegment", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/channels/ChannelSegment;", "receiveSegment", "bufferEndSegment", "send", "element", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onClosedSend", "sendOnNoWaiterSuspend", "segment", FirebaseAnalytics.Param.INDEX, "s", "(Lkotlinx/coroutines/channels/ChannelSegment;ILjava/lang/Object;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "prepareSenderForSuspension", "Lkotlinx/coroutines/Waiter;", "onClosedSendOnNoWaiterSuspend", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)V", "trySend", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "sendBroadcast", "sendBroadcast$kotlinx_coroutines_core", "sendImpl", "R", "waiter", "", "onRendezvousOrBuffered", "Lkotlin/Function0;", "onSuspend", "Lkotlin/Function2;", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "segm", "i", "onClosed", "onNoWaiterSuspend", "Lkotlin/Function4;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "trySendDropOldest", "trySendDropOldest-JP2dKIU", "sendImplOnNoWaiter", "(Lkotlinx/coroutines/channels/ChannelSegment;ILjava/lang/Object;JLkotlinx/coroutines/Waiter;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "updateCellSend", "closed", "(Lkotlinx/coroutines/channels/ChannelSegment;ILjava/lang/Object;JLjava/lang/Object;Z)I", "updateCellSendSlow", "shouldSendSuspend", "curSendersAndCloseStatus", "bufferOrRendezvousSend", "curSenders", "shouldSendSuspend$kotlinx_coroutines_core", "tryResumeReceiver", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "onReceiveEnqueued", "onReceiveDequeued", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveOnNoWaiterSuspend", "r", "(Lkotlinx/coroutines/channels/ChannelSegment;IJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "prepareReceiverForSuspension", "onClosedReceiveOnNoWaiterSuspend", "receiveCatching", "receiveCatching-JP2dKIU", "receiveCatchingOnNoWaiterSuspend", "receiveCatchingOnNoWaiterSuspend-GKJJFZk", "onClosedReceiveCatchingOnNoWaiterSuspend", "tryReceive", "tryReceive-PtdJZtk", "()Ljava/lang/Object;", "dropFirstElementUntilTheSpecifiedCellIsInTheBuffer", "globalCellIndex", "receiveImpl", "onElementRetrieved", "Lkotlin/Function3;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "receiveImplOnNoWaiter", "updateCellReceive", "updateCellReceiveSlow", "tryResumeSender", "expandBuffer", "updateCellExpandBuffer", "b", "updateCellExpandBufferSlow", "incCompletedExpandBufferAttempts", "nAttempts", "waitExpandBufferCompletion", "globalIndex", "waitExpandBufferCompletion$kotlinx_coroutines_core", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend$annotations", "()V", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "registerSelectForSend", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "onClosedSelectOnSend", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)V", "processResultSelectSend", "ignoredParam", "selectResult", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive$annotations", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveCatching", "getOnReceiveCatching$annotations", "getOnReceiveCatching", "onReceiveOrNull", "getOnReceiveOrNull$annotations", "getOnReceiveOrNull", "registerSelectForReceive", "onClosedSelectOnReceive", "processResultSelectReceive", "processResultSelectReceiveOrNull", "processResultSelectReceiveCatching", "onUndeliveredElementReceiveCancellationConstructor", "Lkotlinx/coroutines/selects/OnCancellationConstructor;", "param", "internalResult", "", "Lkotlin/coroutines/CoroutineContext;", "getOnUndeliveredElementReceiveCancellationConstructor$annotations", "Lkotlin/jvm/functions/Function3;", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "_closeCause", "closeCause", "getCloseCause", "()Ljava/lang/Throwable;", "sendException", "getSendException", "receiveException", "getReceiveException", "closeHandler", "onClosedIdempotent", "close", "cause", "cancel", "Lkotlinx/coroutines/CancellationException;", "Ljava/util/concurrent/CancellationException;", "(Ljava/util/concurrent/CancellationException;)V", "cancelImpl", "cancelImpl$kotlinx_coroutines_core", "closeOrCancelImpl", "invokeCloseHandler", "invokeOnClose", "handler", "markClosed", "markCancelled", "markCancellationStarted", "completeCloseOrCancel", "isConflatedDropOldest", "completeClose", "sendersCur", "completeCancel", "closeLinkedList", "markAllEmptyCellsAsClosed", "lastSegment", "removeUnprocessedElements", "cancelSuspendedReceiveRequests", "resumeReceiverOnClosedChannel", "resumeSenderOnCancelledChannel", "resumeWaiterOnClosedChannel", "receiver", "isClosedForSend", "isClosedForSend$annotations", "isClosedForSend0", "(J)Z", "isClosedForReceive", "isClosedForReceive$annotations", "isClosedForReceive0", "isClosed", "sendersAndCloseStatusCur", "isEmpty", "isEmpty$annotations", "hasElements", "hasElements$kotlinx_coroutines_core", "isCellNonEmpty", "findSegmentSend", Constant.VISIT_ID, "startFrom", "findSegmentReceive", "findSegmentBufferEnd", "currentBufferEndCounter", "moveSegmentBufferEndToSpecifiedOrLast", "updateSendersCounterIfLower", "value", "updateReceiversCounterIfLower", "toString", "", "toStringDebug", "toStringDebug$kotlinx_coroutines_core", "checkSegmentStructureInvariants", "bindCancellationFunResult", "Lkotlin/reflect/KFunction3;", "(Lkotlin/jvm/functions/Function1;)Lkotlin/reflect/KFunction;", "onCancellationChannelResultImplDoNotCall", "context", "onCancellationChannelResultImplDoNotCall-5_sEAP8", "(Ljava/lang/Throwable;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;)V", "bindCancellationFun", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;)Lkotlin/jvm/functions/Function3;", "onCancellationImplDoNotCall", "SendBroadcast", "BufferedChannelIterator", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public class BufferedChannel<E> implements Channel<E> {
    private volatile /* synthetic */ Object _closeCause$volatile;
    private volatile /* synthetic */ long bufferEnd$volatile;
    private volatile /* synthetic */ Object bufferEndSegment$volatile;
    private final int capacity;
    private volatile /* synthetic */ Object closeHandler$volatile;
    private volatile /* synthetic */ long completedExpandBuffersAndPauseFlag$volatile;
    public final Function1<E, Unit> onUndeliveredElement;
    private final Function3<SelectInstance<?>, Object, Object, Function3<Throwable, Object, CoroutineContext, Unit>> onUndeliveredElementReceiveCancellationConstructor;
    private volatile /* synthetic */ Object receiveSegment$volatile;
    private volatile /* synthetic */ long receivers$volatile;
    private volatile /* synthetic */ Object sendSegment$volatile;
    private volatile /* synthetic */ long sendersAndCloseStatus$volatile;
    private static final /* synthetic */ AtomicLongFieldUpdater sendersAndCloseStatus$volatile$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "sendersAndCloseStatus$volatile");
    private static final /* synthetic */ AtomicLongFieldUpdater receivers$volatile$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "receivers$volatile");
    private static final /* synthetic */ AtomicLongFieldUpdater bufferEnd$volatile$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "bufferEnd$volatile");
    private static final /* synthetic */ AtomicLongFieldUpdater completedExpandBuffersAndPauseFlag$volatile$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "completedExpandBuffersAndPauseFlag$volatile");
    private static final /* synthetic */ AtomicReferenceFieldUpdater sendSegment$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "sendSegment$volatile");
    private static final /* synthetic */ AtomicReferenceFieldUpdater receiveSegment$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "receiveSegment$volatile");
    private static final /* synthetic */ AtomicReferenceFieldUpdater bufferEndSegment$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "bufferEndSegment$volatile");
    private static final /* synthetic */ AtomicReferenceFieldUpdater _closeCause$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "_closeCause$volatile");
    private static final /* synthetic */ AtomicReferenceFieldUpdater closeHandler$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "closeHandler$volatile");

    private final /* synthetic */ Object getAndUpdate$atomicfu(Object obj, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, Function1<Object, ? extends Object> function1) {
        Object obj2;
        do {
            obj2 = atomicReferenceFieldUpdater.get(obj);
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, obj, obj2, function1.invoke(obj2)));
        return obj2;
    }

    private final /* synthetic */ long getBufferEnd$volatile() {
        return this.bufferEnd$volatile;
    }

    private final /* synthetic */ Object getBufferEndSegment$volatile() {
        return this.bufferEndSegment$volatile;
    }

    private final /* synthetic */ Object getCloseHandler$volatile() {
        return this.closeHandler$volatile;
    }

    private final /* synthetic */ long getCompletedExpandBuffersAndPauseFlag$volatile() {
        return this.completedExpandBuffersAndPauseFlag$volatile;
    }

    public static /* synthetic */ void getOnReceive$annotations() {
    }

    public static /* synthetic */ void getOnReceiveCatching$annotations() {
    }

    public static /* synthetic */ void getOnReceiveOrNull$annotations() {
    }

    public static /* synthetic */ void getOnSend$annotations() {
    }

    private static /* synthetic */ void getOnUndeliveredElementReceiveCancellationConstructor$annotations() {
    }

    private final /* synthetic */ Object getReceiveSegment$volatile() {
        return this.receiveSegment$volatile;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ AtomicReferenceFieldUpdater getReceiveSegment$volatile$FU() {
        return receiveSegment$volatile$FU;
    }

    private final /* synthetic */ long getReceivers$volatile() {
        return this.receivers$volatile;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ AtomicLongFieldUpdater getReceivers$volatile$FU() {
        return receivers$volatile$FU;
    }

    private final /* synthetic */ Object getSendSegment$volatile() {
        return this.sendSegment$volatile;
    }

    private final /* synthetic */ long getSendersAndCloseStatus$volatile() {
        return this.sendersAndCloseStatus$volatile;
    }

    private final /* synthetic */ Object get_closeCause$volatile() {
        return this._closeCause$volatile;
    }

    public static /* synthetic */ void isClosedForReceive$annotations() {
    }

    public static /* synthetic */ void isClosedForSend$annotations() {
    }

    public static /* synthetic */ void isEmpty$annotations() {
    }

    private final /* synthetic */ void loop$atomicfu(Object obj, AtomicLongFieldUpdater atomicLongFieldUpdater, Function1<? super Long, Unit> function1) {
        while (true) {
            function1.invoke(Long.valueOf(atomicLongFieldUpdater.get(obj)));
        }
    }

    private final /* synthetic */ void loop$atomicfu(Object obj, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, Function1<Object, Unit> function1) {
        while (true) {
            function1.invoke(atomicReferenceFieldUpdater.get(obj));
        }
    }

    private final /* synthetic */ void setBufferEnd$volatile(long j) {
        this.bufferEnd$volatile = j;
    }

    private final /* synthetic */ void setBufferEndSegment$volatile(Object obj) {
        this.bufferEndSegment$volatile = obj;
    }

    private final /* synthetic */ void setCloseHandler$volatile(Object obj) {
        this.closeHandler$volatile = obj;
    }

    private final /* synthetic */ void setCompletedExpandBuffersAndPauseFlag$volatile(long j) {
        this.completedExpandBuffersAndPauseFlag$volatile = j;
    }

    private final /* synthetic */ void setReceiveSegment$volatile(Object obj) {
        this.receiveSegment$volatile = obj;
    }

    private final /* synthetic */ void setReceivers$volatile(long j) {
        this.receivers$volatile = j;
    }

    private final /* synthetic */ void setSendSegment$volatile(Object obj) {
        this.sendSegment$volatile = obj;
    }

    private final /* synthetic */ void setSendersAndCloseStatus$volatile(long j) {
        this.sendersAndCloseStatus$volatile = j;
    }

    private final /* synthetic */ void set_closeCause$volatile(Object obj) {
        this._closeCause$volatile = obj;
    }

    private final /* synthetic */ void update$atomicfu(Object obj, AtomicLongFieldUpdater atomicLongFieldUpdater, Function1<? super Long, Long> function1) {
        long j;
        do {
            j = atomicLongFieldUpdater.get(obj);
        } while (!atomicLongFieldUpdater.compareAndSet(obj, j, function1.invoke(Long.valueOf(j)).longValue()));
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public Object receive(Continuation<? super E> continuation) {
        return receive$suspendImpl(this, continuation);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: receiveCatching-JP2dKIU, reason: not valid java name */
    public Object mo2071receiveCatchingJP2dKIU(Continuation<? super ChannelResult<? extends E>> continuation) {
        return m2069receiveCatchingJP2dKIU$suspendImpl(this, continuation);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public Object send(E e, Continuation<? super Unit> continuation) {
        return send$suspendImpl(this, e, continuation);
    }

    public Object sendBroadcast$kotlinx_coroutines_core(E e, Continuation<? super Boolean> continuation) {
        return sendBroadcast$suspendImpl(this, e, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BufferedChannel(int capacity, Function1<? super E, Unit> function1) {
        long initialBufferEnd;
        ChannelSegment channelSegment;
        Symbol symbol;
        this.capacity = capacity;
        this.onUndeliveredElement = function1;
        if (this.capacity >= 0) {
            initialBufferEnd = BufferedChannelKt.initialBufferEnd(this.capacity);
            this.bufferEnd$volatile = initialBufferEnd;
            this.completedExpandBuffersAndPauseFlag$volatile = getBufferEndCounter();
            ChannelSegment firstSegment = new ChannelSegment(0L, null, this, 3);
            this.sendSegment$volatile = firstSegment;
            this.receiveSegment$volatile = firstSegment;
            if (isRendezvousOrUnlimited()) {
                channelSegment = BufferedChannelKt.NULL_SEGMENT;
                Intrinsics.checkNotNull(channelSegment, "null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelSegment<E of kotlinx.coroutines.channels.BufferedChannel>");
            } else {
                channelSegment = firstSegment;
            }
            this.bufferEndSegment$volatile = channelSegment;
            this.onUndeliveredElementReceiveCancellationConstructor = this.onUndeliveredElement != null ? new Function3() { // from class: kotlinx.coroutines.channels.BufferedChannel$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Function3 onUndeliveredElementReceiveCancellationConstructor$lambda$57$lambda$56;
                    onUndeliveredElementReceiveCancellationConstructor$lambda$57$lambda$56 = BufferedChannel.onUndeliveredElementReceiveCancellationConstructor$lambda$57$lambda$56(BufferedChannel.this, (SelectInstance) obj, obj2, obj3);
                    return onUndeliveredElementReceiveCancellationConstructor$lambda$57$lambda$56;
                }
            } : null;
            symbol = BufferedChannelKt.NO_CLOSE_CAUSE;
            this._closeCause$volatile = symbol;
            return;
        }
        throw new IllegalArgumentException(("Invalid channel capacity: " + this.capacity + ", should be >=0").toString());
    }

    public /* synthetic */ BufferedChannel(int i, Function1 function1, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : function1);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    public boolean offer(E e) {
        return Channel.DefaultImpls.offer(this, e);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}))
    public E poll() {
        return (E) Channel.DefaultImpls.poll(this);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
    public Object receiveOrNull(Continuation<? super E> continuation) {
        return Channel.DefaultImpls.receiveOrNull(this, continuation);
    }

    public final long getSendersCounter$kotlinx_coroutines_core() {
        long $this$sendersCounter$iv = sendersAndCloseStatus$volatile$FU.get(this);
        return $this$sendersCounter$iv & 1152921504606846975L;
    }

    public final long getReceiversCounter$kotlinx_coroutines_core() {
        return receivers$volatile$FU.get(this);
    }

    private final long getBufferEndCounter() {
        return bufferEnd$volatile$FU.get(this);
    }

    private final boolean isRendezvousOrUnlimited() {
        long it = getBufferEndCounter();
        return it == 0 || it == Long.MAX_VALUE;
    }

    static /* synthetic */ <E> Object send$suspendImpl(BufferedChannel<E> bufferedChannel, E e, Continuation<? super Unit> continuation) {
        ChannelSegment segment$iv;
        ChannelSegment segment$iv2 = (ChannelSegment) sendSegment$volatile$FU.get(bufferedChannel);
        while (true) {
            long sendersAndCloseStatusCur$iv = sendersAndCloseStatus$volatile$FU.getAndIncrement(bufferedChannel);
            long $this$sendersCounter$iv$iv = sendersAndCloseStatusCur$iv & 1152921504606846975L;
            boolean closed$iv = bufferedChannel.isClosedForSend0(sendersAndCloseStatusCur$iv);
            long id$iv = $this$sendersCounter$iv$iv / BufferedChannelKt.SEGMENT_SIZE;
            int i$iv = (int) ($this$sendersCounter$iv$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment$iv2.id != id$iv) {
                ChannelSegment segment$iv3 = bufferedChannel.findSegmentSend(id$iv, segment$iv2);
                if (segment$iv3 != null) {
                    segment$iv = segment$iv3;
                } else if (closed$iv) {
                    Object onClosedSend = bufferedChannel.onClosedSend(e, continuation);
                    if (onClosedSend == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return onClosedSend;
                    }
                }
            } else {
                segment$iv = segment$iv2;
            }
            switch (bufferedChannel.updateCellSend(segment$iv, i$iv, e, $this$sendersCounter$iv$iv, null, closed$iv)) {
                case 0:
                    segment$iv.cleanPrev();
                    break;
                case 1:
                    break;
                case 2:
                    if (closed$iv) {
                        segment$iv.onSlotCleaned();
                        Object onClosedSend2 = bufferedChannel.onClosedSend(e, continuation);
                        if (onClosedSend2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                            return onClosedSend2;
                        }
                    } else if (DebugKt.getASSERTIONS_ENABLED()) {
                        throw new AssertionError();
                    }
                    break;
                case 3:
                    ChannelSegment segm = segment$iv;
                    Object sendOnNoWaiterSuspend = bufferedChannel.sendOnNoWaiterSuspend(segm, i$iv, e, $this$sendersCounter$iv$iv, continuation);
                    if (sendOnNoWaiterSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return sendOnNoWaiterSuspend;
                    }
                    break;
                case 4:
                    if ($this$sendersCounter$iv$iv < bufferedChannel.getReceiversCounter$kotlinx_coroutines_core()) {
                        segment$iv.cleanPrev();
                    }
                    Object onClosedSend3 = bufferedChannel.onClosedSend(e, continuation);
                    if (onClosedSend3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return onClosedSend3;
                    }
                    break;
                case 5:
                    segment$iv.cleanPrev();
                    segment$iv2 = segment$iv;
                default:
                    segment$iv2 = segment$iv;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object onClosedSend(E e, Continuation<? super Unit> continuation) {
        Throwable th;
        UndeliveredElementException it;
        UndeliveredElementException undeliveredElementException;
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        CancellableContinuationImpl continuation2 = cancellable$iv;
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        if (function1 != null && (it = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, e, null, 2, null)) != null) {
            ExceptionsKt.addSuppressed(it, getSendException());
            CancellableContinuationImpl $this$resumeWithStackTrace$iv = continuation2;
            Result.Companion companion = Result.INSTANCE;
            if (DebugKt.getRECOVER_STACK_TRACES() && ($this$resumeWithStackTrace$iv instanceof CoroutineStackFrame)) {
                undeliveredElementException = StackTraceRecoveryKt.recoverFromStackFrame(it, $this$resumeWithStackTrace$iv);
            } else {
                undeliveredElementException = it;
            }
            $this$resumeWithStackTrace$iv.resumeWith(Result.m569constructorimpl(ResultKt.createFailure(undeliveredElementException)));
        } else {
            CancellableContinuationImpl $this$resumeWithStackTrace$iv2 = continuation2;
            Throwable exception$iv = getSendException();
            Result.Companion companion2 = Result.INSTANCE;
            if (DebugKt.getRECOVER_STACK_TRACES() && ($this$resumeWithStackTrace$iv2 instanceof CoroutineStackFrame)) {
                th = StackTraceRecoveryKt.recoverFromStackFrame(exception$iv, $this$resumeWithStackTrace$iv2);
            } else {
                th = exception$iv;
            }
            $this$resumeWithStackTrace$iv2.resumeWith(Result.m569constructorimpl(ResultKt.createFailure(th)));
        }
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:21:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x01cc A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object sendOnNoWaiterSuspend(ChannelSegment<E> channelSegment, int index, E e, long s, Continuation<? super Unit> continuation) {
        ChannelSegment segment$iv$iv;
        ChannelSegment segment$iv$iv2;
        Object result;
        Continuation uCont$iv = continuation;
        CancellableContinuationImpl cancellable$iv = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(uCont$iv));
        try {
            try {
                try {
                    try {
                        switch (updateCellSend(channelSegment, index, e, s, cancellable$iv, false)) {
                            case 0:
                                channelSegment.cleanPrev();
                                Result.Companion companion = Result.INSTANCE;
                                cancellable$iv.resumeWith(Result.m569constructorimpl(Unit.INSTANCE));
                                result = cancellable$iv.getResult();
                                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                    DebugProbesKt.probeCoroutineSuspended(continuation);
                                }
                                return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
                            case 1:
                                Result.Companion companion2 = Result.INSTANCE;
                                cancellable$iv.resumeWith(Result.m569constructorimpl(Unit.INSTANCE));
                                result = cancellable$iv.getResult();
                                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                }
                                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                }
                                break;
                            case 2:
                                prepareSenderForSuspension(cancellable$iv, channelSegment, index);
                                result = cancellable$iv.getResult();
                                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                }
                                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                }
                                break;
                            case 3:
                            default:
                                throw new IllegalStateException("unexpected".toString());
                            case 4:
                                if (s < getReceiversCounter$kotlinx_coroutines_core()) {
                                    channelSegment.cleanPrev();
                                }
                                onClosedSendOnNoWaiterSuspend(e, cancellable$iv);
                                result = cancellable$iv.getResult();
                                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                }
                                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                }
                                break;
                            case 5:
                                try {
                                    channelSegment.cleanPrev();
                                    ChannelSegment segment$iv$iv3 = (ChannelSegment) sendSegment$volatile$FU.get(this);
                                    while (true) {
                                        long $this$sendersCounter$iv$iv$iv = sendersAndCloseStatus$volatile$FU.getAndIncrement(this);
                                        long $this$sendersCounter$iv$iv$iv2 = $this$sendersCounter$iv$iv$iv & 1152921504606846975L;
                                        boolean closed$iv$iv = isClosedForSend0($this$sendersCounter$iv$iv$iv);
                                        long id$iv$iv = $this$sendersCounter$iv$iv$iv2 / BufferedChannelKt.SEGMENT_SIZE;
                                        int i$iv$iv = (int) ($this$sendersCounter$iv$iv$iv2 % BufferedChannelKt.SEGMENT_SIZE);
                                        if (segment$iv$iv3.id != id$iv$iv) {
                                            try {
                                                ChannelSegment segment$iv$iv4 = findSegmentSend(id$iv$iv, segment$iv$iv3);
                                                if (segment$iv$iv4 != null) {
                                                    segment$iv$iv = segment$iv$iv4;
                                                } else if (closed$iv$iv) {
                                                    onClosedSendOnNoWaiterSuspend(e, cancellable$iv);
                                                }
                                            } catch (Throwable th) {
                                                e$iv = th;
                                                cancellable$iv.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                                                throw e$iv;
                                            }
                                        } else {
                                            segment$iv$iv = segment$iv$iv3;
                                        }
                                        ChannelSegment segment$iv$iv5 = segment$iv$iv;
                                        Continuation uCont$iv2 = uCont$iv;
                                        switch (updateCellSend(segment$iv$iv, i$iv$iv, e, $this$sendersCounter$iv$iv$iv2, cancellable$iv, closed$iv$iv)) {
                                            case 0:
                                                segment$iv$iv5.cleanPrev();
                                                Result.Companion companion3 = Result.INSTANCE;
                                                cancellable$iv.resumeWith(Result.m569constructorimpl(Unit.INSTANCE));
                                                break;
                                            case 1:
                                                Result.Companion companion4 = Result.INSTANCE;
                                                cancellable$iv.resumeWith(Result.m569constructorimpl(Unit.INSTANCE));
                                                break;
                                            case 2:
                                                if (!closed$iv$iv) {
                                                    CancellableContinuationImpl cancellableContinuationImpl = cancellable$iv instanceof Waiter ? cancellable$iv : null;
                                                    if (cancellableContinuationImpl != null) {
                                                        prepareSenderForSuspension(cancellableContinuationImpl, segment$iv$iv5, i$iv$iv);
                                                    }
                                                    break;
                                                } else {
                                                    segment$iv$iv5.onSlotCleaned();
                                                    onClosedSendOnNoWaiterSuspend(e, cancellable$iv);
                                                    break;
                                                }
                                            case 3:
                                                throw new IllegalStateException("unexpected".toString());
                                            case 4:
                                                if ($this$sendersCounter$iv$iv$iv2 < getReceiversCounter$kotlinx_coroutines_core()) {
                                                    segment$iv$iv5.cleanPrev();
                                                }
                                                onClosedSendOnNoWaiterSuspend(e, cancellable$iv);
                                                break;
                                            case 5:
                                                segment$iv$iv5.cleanPrev();
                                                segment$iv$iv2 = segment$iv$iv5;
                                                segment$iv$iv3 = segment$iv$iv2;
                                                uCont$iv = uCont$iv2;
                                            default:
                                                segment$iv$iv2 = segment$iv$iv5;
                                                segment$iv$iv3 = segment$iv$iv2;
                                                uCont$iv = uCont$iv2;
                                        }
                                    }
                                    result = cancellable$iv.getResult();
                                    if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                    }
                                    if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                    }
                                } catch (Throwable th2) {
                                    e$iv = th2;
                                }
                                break;
                        }
                    } catch (Throwable th3) {
                        e$iv = th3;
                    }
                } catch (Throwable th4) {
                    e$iv = th4;
                }
            } catch (Throwable th5) {
                e$iv = th5;
            }
        } catch (Throwable th6) {
            e$iv = th6;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void prepareSenderForSuspension(Waiter $this$prepareSenderForSuspension, ChannelSegment<E> channelSegment, int index) {
        $this$prepareSenderForSuspension.invokeOnCancellation(channelSegment, BufferedChannelKt.SEGMENT_SIZE + index);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClosedSendOnNoWaiterSuspend(E element, CancellableContinuation<? super Unit> cont) {
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        if (function1 != null) {
            OnUndeliveredElementKt.callUndeliveredElement(function1, element, cont.getContext());
        }
        CancellableContinuation<? super Unit> cancellableContinuation = cont;
        Throwable exception$iv = getSendException();
        if (DebugKt.getRECOVER_STACK_TRACES() && (cont instanceof CoroutineStackFrame)) {
            exception$iv = StackTraceRecoveryKt.recoverFromStackFrame(exception$iv, (CoroutineStackFrame) cont);
        }
        Result.Companion companion = Result.INSTANCE;
        cancellableContinuation.resumeWith(Result.m569constructorimpl(ResultKt.createFailure(exception$iv)));
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU */
    public Object mo2064trySendJP2dKIU(E element) {
        Object waiter$iv;
        ChannelSegment segment$iv;
        ChannelSegment segment$iv2;
        if (shouldSendSuspend(sendersAndCloseStatus$volatile$FU.get(this))) {
            return ChannelResult.INSTANCE.m2093failurePtdJZtk();
        }
        waiter$iv = BufferedChannelKt.INTERRUPTED_SEND;
        int $i$f$sendImpl = 0;
        ChannelSegment segment$iv3 = (ChannelSegment) sendSegment$volatile$FU.get(this);
        while (true) {
            long sendersAndCloseStatusCur$iv = sendersAndCloseStatus$volatile$FU.getAndIncrement(this);
            long $this$sendersCounter$iv$iv = sendersAndCloseStatusCur$iv & 1152921504606846975L;
            boolean closed$iv = isClosedForSend0(sendersAndCloseStatusCur$iv);
            long id$iv = $this$sendersCounter$iv$iv / BufferedChannelKt.SEGMENT_SIZE;
            int i$iv = (int) ($this$sendersCounter$iv$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment$iv3.id != id$iv) {
                ChannelSegment segment$iv4 = findSegmentSend(id$iv, segment$iv3);
                if (segment$iv4 != null) {
                    segment$iv = segment$iv4;
                } else if (closed$iv) {
                    return ChannelResult.INSTANCE.m2092closedJP2dKIU(getSendException());
                }
            } else {
                segment$iv = segment$iv3;
            }
            ChannelSegment segment$iv5 = segment$iv;
            int $i$f$sendImpl2 = $i$f$sendImpl;
            switch (updateCellSend(segment$iv, i$iv, element, $this$sendersCounter$iv$iv, waiter$iv, closed$iv)) {
                case 0:
                    segment$iv5.cleanPrev();
                    return ChannelResult.INSTANCE.m2094successJP2dKIU(Unit.INSTANCE);
                case 1:
                    return ChannelResult.INSTANCE.m2094successJP2dKIU(Unit.INSTANCE);
                case 2:
                    if (closed$iv) {
                        segment$iv5.onSlotCleaned();
                        return ChannelResult.INSTANCE.m2092closedJP2dKIU(getSendException());
                    }
                    Waiter waiter = waiter$iv instanceof Waiter ? (Waiter) waiter$iv : null;
                    if (waiter != null) {
                        segment$iv2 = segment$iv5;
                        prepareSenderForSuspension(waiter, segment$iv2, i$iv);
                    } else {
                        segment$iv2 = segment$iv5;
                    }
                    ChannelSegment segm = segment$iv2;
                    segm.onSlotCleaned();
                    return ChannelResult.INSTANCE.m2093failurePtdJZtk();
                case 3:
                    throw new IllegalStateException("unexpected".toString());
                case 4:
                    if ($this$sendersCounter$iv$iv < getReceiversCounter$kotlinx_coroutines_core()) {
                        segment$iv5.cleanPrev();
                    }
                    return ChannelResult.INSTANCE.m2092closedJP2dKIU(getSendException());
                case 5:
                    segment$iv5.cleanPrev();
                default:
                    segment$iv3 = segment$iv5;
                    $i$f$sendImpl = $i$f$sendImpl2;
            }
        }
    }

    static /* synthetic */ <E> Object sendBroadcast$suspendImpl(BufferedChannel<E> bufferedChannel, E e, Continuation<? super Boolean> continuation) {
        CancellableContinuationImpl cancellable$iv;
        ChannelSegment segment$iv;
        int $i$f$suspendCancellableCoroutine = 0;
        CancellableContinuationImpl cancellable$iv2 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv2.initCancellability();
        CancellableContinuationImpl cont = cancellable$iv2;
        if (!(bufferedChannel.onUndeliveredElement == null)) {
            throw new IllegalStateException("the `onUndeliveredElement` feature is unsupported for `sendBroadcast(e)`".toString());
        }
        Object waiter$iv = new SendBroadcast(cont);
        ChannelSegment segment$iv2 = (ChannelSegment) sendSegment$volatile$FU.get(bufferedChannel);
        while (true) {
            long sendersAndCloseStatusCur$iv = sendersAndCloseStatus$volatile$FU.getAndIncrement(bufferedChannel);
            long $this$sendersCounter$iv$iv = sendersAndCloseStatusCur$iv & 1152921504606846975L;
            boolean closed$iv = bufferedChannel.isClosedForSend0(sendersAndCloseStatusCur$iv);
            long id$iv = $this$sendersCounter$iv$iv / BufferedChannelKt.SEGMENT_SIZE;
            cancellable$iv = cancellable$iv2;
            int i$iv = (int) ($this$sendersCounter$iv$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment$iv2.id != id$iv) {
                ChannelSegment segment$iv3 = bufferedChannel.findSegmentSend(id$iv, segment$iv2);
                if (segment$iv3 != null) {
                    segment$iv = segment$iv3;
                } else if (closed$iv) {
                    Result.Companion companion = Result.INSTANCE;
                    cont.resumeWith(Result.m569constructorimpl(Boxing.boxBoolean(false)));
                } else {
                    cancellable$iv2 = cancellable$iv;
                }
            } else {
                segment$iv = segment$iv2;
            }
            int $i$f$suspendCancellableCoroutine2 = $i$f$suspendCancellableCoroutine;
            Object waiter$iv2 = waiter$iv;
            switch (bufferedChannel.updateCellSend(segment$iv, i$iv, e, $this$sendersCounter$iv$iv, waiter$iv, closed$iv)) {
                case 0:
                    segment$iv.cleanPrev();
                    Result.Companion companion2 = Result.INSTANCE;
                    cont.resumeWith(Result.m569constructorimpl(Boxing.boxBoolean(true)));
                    break;
                case 1:
                    Result.Companion companion3 = Result.INSTANCE;
                    cont.resumeWith(Result.m569constructorimpl(Boxing.boxBoolean(true)));
                    break;
                case 2:
                    if (closed$iv) {
                        segment$iv.onSlotCleaned();
                        Result.Companion companion4 = Result.INSTANCE;
                        cont.resumeWith(Result.m569constructorimpl(Boxing.boxBoolean(false)));
                        break;
                    } else {
                        SendBroadcast sendBroadcast = waiter$iv2 instanceof Waiter ? (Waiter) waiter$iv2 : null;
                        if (sendBroadcast != null) {
                            bufferedChannel.prepareSenderForSuspension(sendBroadcast, segment$iv, i$iv);
                        }
                        break;
                    }
                case 3:
                    throw new IllegalStateException("unexpected".toString());
                case 4:
                    if ($this$sendersCounter$iv$iv < bufferedChannel.getReceiversCounter$kotlinx_coroutines_core()) {
                        segment$iv.cleanPrev();
                    }
                    Result.Companion companion5 = Result.INSTANCE;
                    cont.resumeWith(Result.m569constructorimpl(Boxing.boxBoolean(false)));
                    break;
                case 5:
                    segment$iv.cleanPrev();
                default:
                    waiter$iv = waiter$iv2;
                    segment$iv2 = segment$iv;
                    cancellable$iv2 = cancellable$iv;
                    $i$f$suspendCancellableCoroutine = $i$f$suspendCancellableCoroutine2;
            }
        }
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* compiled from: BufferedChannel.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\r\u001a\u00020\u000eH\u0096\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/channels/BufferedChannel$SendBroadcast;", "Lkotlinx/coroutines/Waiter;", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "<init>", "(Lkotlinx/coroutines/CancellableContinuation;)V", "getCont", "()Lkotlinx/coroutines/CancellableContinuation;", "invokeOnCancellation", "", "segment", "Lkotlinx/coroutines/internal/Segment;", FirebaseAnalytics.Param.INDEX, "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class SendBroadcast implements Waiter {
        private final /* synthetic */ CancellableContinuationImpl<Boolean> $$delegate_0;
        private final CancellableContinuation<Boolean> cont;

        @Override // kotlinx.coroutines.Waiter
        public void invokeOnCancellation(Segment<?> segment, int index) {
            this.$$delegate_0.invokeOnCancellation(segment, index);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public SendBroadcast(CancellableContinuation<? super Boolean> cancellableContinuation) {
            Intrinsics.checkNotNull(cancellableContinuation, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuationImpl<kotlin.Boolean>");
            this.$$delegate_0 = (CancellableContinuationImpl) cancellableContinuation;
            this.cont = cancellableContinuation;
        }

        public final CancellableContinuation<Boolean> getCont() {
            return this.cont;
        }
    }

    static /* synthetic */ Object sendImpl$default(BufferedChannel $this, Object element, Object waiter, Function0 onRendezvousOrBuffered, Function2 onSuspend, Function0 onClosed, Function4 onNoWaiterSuspend, int i, Object obj) {
        Function4 onNoWaiterSuspend2;
        ChannelSegment segment;
        int i2;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendImpl");
        }
        if ((i & 32) == 0) {
            onNoWaiterSuspend2 = onNoWaiterSuspend;
        } else {
            onNoWaiterSuspend2 = new Function4() { // from class: kotlinx.coroutines.channels.BufferedChannel$sendImpl$1
                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3, Object p4) {
                    return invoke((ChannelSegment<int>) p1, ((Number) p2).intValue(), (int) p3, ((Number) p4).longValue());
                }

                public final Void invoke(ChannelSegment<E> channelSegment, int i3, E e, long j) {
                    throw new IllegalStateException("unexpected".toString());
                }
            };
        }
        ChannelSegment segment2 = (ChannelSegment) sendSegment$volatile$FU.get($this);
        while (true) {
            long sendersAndCloseStatusCur = sendersAndCloseStatus$volatile$FU.getAndIncrement($this);
            long $this$sendersCounter$iv = sendersAndCloseStatusCur & 1152921504606846975L;
            boolean closed = $this.isClosedForSend0(sendersAndCloseStatusCur);
            long id = $this$sendersCounter$iv / BufferedChannelKt.SEGMENT_SIZE;
            int i3 = (int) ($this$sendersCounter$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment2.id != id) {
                ChannelSegment segment3 = $this.findSegmentSend(id, segment2);
                if (segment3 != null) {
                    segment = segment3;
                } else if (closed) {
                    return onClosed.invoke();
                }
            } else {
                segment = segment2;
            }
            ChannelSegment segment4 = segment;
            switch ($this.updateCellSend(segment, i3, element, $this$sendersCounter$iv, waiter, closed)) {
                case 0:
                    segment4.cleanPrev();
                    return onRendezvousOrBuffered.invoke();
                case 1:
                    return onRendezvousOrBuffered.invoke();
                case 2:
                    if (closed) {
                        segment4.onSlotCleaned();
                        return onClosed.invoke();
                    }
                    Waiter waiter2 = waiter instanceof Waiter ? (Waiter) waiter : null;
                    if (waiter2 != null) {
                        i2 = i3;
                        $this.prepareSenderForSuspension(waiter2, segment4, i2);
                    } else {
                        i2 = i3;
                    }
                    return onSuspend.invoke(segment4, Integer.valueOf(i2));
                case 3:
                    return onNoWaiterSuspend2.invoke(segment4, Integer.valueOf(i3), element, Long.valueOf($this$sendersCounter$iv));
                case 4:
                    if ($this$sendersCounter$iv < $this.getReceiversCounter$kotlinx_coroutines_core()) {
                        segment4.cleanPrev();
                    }
                    return onClosed.invoke();
                case 5:
                    segment4.cleanPrev();
                    break;
            }
            segment2 = segment4;
        }
    }

    private final <R> R sendImpl(E element, Object waiter, Function0<? extends R> onRendezvousOrBuffered, Function2<? super ChannelSegment<E>, ? super Integer, ? extends R> onSuspend, Function0<? extends R> onClosed, Function4<? super ChannelSegment<E>, ? super Integer, ? super E, ? super Long, ? extends R> onNoWaiterSuspend) {
        ChannelSegment segment;
        int i;
        ChannelSegment segment2 = (ChannelSegment) sendSegment$volatile$FU.get(this);
        while (true) {
            long sendersAndCloseStatusCur = sendersAndCloseStatus$volatile$FU.getAndIncrement(this);
            long $this$sendersCounter$iv = sendersAndCloseStatusCur & 1152921504606846975L;
            boolean closed = isClosedForSend0(sendersAndCloseStatusCur);
            long id = $this$sendersCounter$iv / BufferedChannelKt.SEGMENT_SIZE;
            int i2 = (int) ($this$sendersCounter$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment2.id != id) {
                ChannelSegment segment3 = findSegmentSend(id, segment2);
                if (segment3 != null) {
                    segment = segment3;
                } else if (closed) {
                    return onClosed.invoke();
                }
            } else {
                segment = segment2;
            }
            ChannelSegment segment4 = segment;
            switch (updateCellSend(segment, i2, element, $this$sendersCounter$iv, waiter, closed)) {
                case 0:
                    segment4.cleanPrev();
                    return onRendezvousOrBuffered.invoke();
                case 1:
                    return onRendezvousOrBuffered.invoke();
                case 2:
                    if (closed) {
                        segment4.onSlotCleaned();
                        return onClosed.invoke();
                    }
                    Waiter waiter2 = waiter instanceof Waiter ? (Waiter) waiter : null;
                    if (waiter2 != null) {
                        i = i2;
                        prepareSenderForSuspension(waiter2, segment4, i);
                    } else {
                        i = i2;
                    }
                    return onSuspend.invoke(segment4, Integer.valueOf(i));
                case 3:
                    return onNoWaiterSuspend.invoke(segment4, Integer.valueOf(i2), element, Long.valueOf($this$sendersCounter$iv));
                case 4:
                    if ($this$sendersCounter$iv < getReceiversCounter$kotlinx_coroutines_core()) {
                        segment4.cleanPrev();
                    }
                    return onClosed.invoke();
                case 5:
                    segment4.cleanPrev();
                    break;
            }
            segment2 = segment4;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0088, code lost:
    
        return kotlinx.coroutines.channels.ChannelResult.INSTANCE.m2092closedJP2dKIU(getSendException());
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00d2, code lost:
    
        return kotlinx.coroutines.channels.ChannelResult.INSTANCE.m2094successJP2dKIU(kotlin.Unit.INSTANCE);
     */
    /* renamed from: trySendDropOldest-JP2dKIU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final Object m2073trySendDropOldestJP2dKIU(E element) {
        ChannelSegment segment$iv;
        ChannelSegment segment$iv2;
        Object waiter$iv = BufferedChannelKt.BUFFERED;
        int $i$f$sendImpl = 0;
        ChannelSegment segment$iv3 = (ChannelSegment) sendSegment$volatile$FU.get(this);
        while (true) {
            long sendersAndCloseStatusCur$iv = sendersAndCloseStatus$volatile$FU.getAndIncrement(this);
            long $this$sendersCounter$iv$iv = sendersAndCloseStatusCur$iv & 1152921504606846975L;
            boolean closed$iv = isClosedForSend0(sendersAndCloseStatusCur$iv);
            long id$iv = $this$sendersCounter$iv$iv / BufferedChannelKt.SEGMENT_SIZE;
            int i$iv = (int) ($this$sendersCounter$iv$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment$iv3.id != id$iv) {
                ChannelSegment segment$iv4 = findSegmentSend(id$iv, segment$iv3);
                if (segment$iv4 != null) {
                    segment$iv = segment$iv4;
                } else if (closed$iv) {
                    return ChannelResult.INSTANCE.m2092closedJP2dKIU(getSendException());
                }
            } else {
                segment$iv = segment$iv3;
            }
            ChannelSegment segment$iv5 = segment$iv;
            int $i$f$sendImpl2 = $i$f$sendImpl;
            switch (updateCellSend(segment$iv, i$iv, element, $this$sendersCounter$iv$iv, waiter$iv, closed$iv)) {
                case 0:
                    segment$iv5.cleanPrev();
                    break;
                case 1:
                    break;
                case 2:
                    if (closed$iv) {
                        segment$iv5.onSlotCleaned();
                        break;
                    } else {
                        Waiter waiter = waiter$iv instanceof Waiter ? (Waiter) waiter$iv : null;
                        if (waiter != null) {
                            segment$iv2 = segment$iv5;
                            prepareSenderForSuspension(waiter, segment$iv2, i$iv);
                        } else {
                            segment$iv2 = segment$iv5;
                        }
                        ChannelSegment segm = segment$iv2;
                        dropFirstElementUntilTheSpecifiedCellIsInTheBuffer((segm.id * BufferedChannelKt.SEGMENT_SIZE) + i$iv);
                        return ChannelResult.INSTANCE.m2094successJP2dKIU(Unit.INSTANCE);
                    }
                case 3:
                    throw new IllegalStateException("unexpected".toString());
                case 4:
                    if ($this$sendersCounter$iv$iv < getReceiversCounter$kotlinx_coroutines_core()) {
                        segment$iv5.cleanPrev();
                    }
                    break;
                case 5:
                    segment$iv5.cleanPrev();
                default:
                    segment$iv3 = segment$iv5;
                    $i$f$sendImpl = $i$f$sendImpl2;
            }
        }
    }

    private final void sendImplOnNoWaiter(ChannelSegment<E> segment, int index, E element, long s, Waiter waiter, Function0<Unit> onRendezvousOrBuffered, Function0<Unit> onClosed) {
        ChannelSegment segment$iv;
        Unit unit;
        int $i$f$sendImplOnNoWaiter = 0;
        switch (updateCellSend(segment, index, element, s, waiter, false)) {
            case 0:
                segment.cleanPrev();
                onRendezvousOrBuffered.invoke();
                return;
            case 1:
                onRendezvousOrBuffered.invoke();
                return;
            case 2:
                prepareSenderForSuspension(waiter, segment, index);
                return;
            case 3:
            default:
                throw new IllegalStateException("unexpected".toString());
            case 4:
                if (s < getReceiversCounter$kotlinx_coroutines_core()) {
                    segment.cleanPrev();
                }
                onClosed.invoke();
                return;
            case 5:
                segment.cleanPrev();
                ChannelSegment segment$iv2 = (ChannelSegment) sendSegment$volatile$FU.get(this);
                while (true) {
                    long sendersAndCloseStatusCur$iv = sendersAndCloseStatus$volatile$FU.getAndIncrement(this);
                    long $this$sendersCounter$iv$iv = sendersAndCloseStatusCur$iv & 1152921504606846975L;
                    boolean closed$iv = isClosedForSend0(sendersAndCloseStatusCur$iv);
                    long id$iv = $this$sendersCounter$iv$iv / BufferedChannelKt.SEGMENT_SIZE;
                    int i$iv = (int) ($this$sendersCounter$iv$iv % BufferedChannelKt.SEGMENT_SIZE);
                    if (segment$iv2.id != id$iv) {
                        ChannelSegment segment$iv3 = findSegmentSend(id$iv, segment$iv2);
                        if (segment$iv3 != null) {
                            segment$iv = segment$iv3;
                        } else if (closed$iv) {
                            unit = onClosed.invoke();
                        }
                    } else {
                        segment$iv = segment$iv2;
                    }
                    ChannelSegment segment$iv4 = segment$iv;
                    int $i$f$sendImplOnNoWaiter2 = $i$f$sendImplOnNoWaiter;
                    switch (updateCellSend(segment$iv, i$iv, element, $this$sendersCounter$iv$iv, waiter, closed$iv)) {
                        case 0:
                            segment$iv4.cleanPrev();
                            unit = onRendezvousOrBuffered.invoke();
                            break;
                        case 1:
                            unit = onRendezvousOrBuffered.invoke();
                            break;
                        case 2:
                            if (!closed$iv) {
                                Waiter waiter2 = waiter instanceof Waiter ? waiter : null;
                                if (waiter2 != null) {
                                    prepareSenderForSuspension(waiter2, segment$iv4, i$iv);
                                }
                                unit = Unit.INSTANCE;
                                break;
                            } else {
                                segment$iv4.onSlotCleaned();
                                unit = onClosed.invoke();
                                break;
                            }
                        case 3:
                            throw new IllegalStateException("unexpected".toString());
                        case 4:
                            if ($this$sendersCounter$iv$iv < getReceiversCounter$kotlinx_coroutines_core()) {
                                segment$iv4.cleanPrev();
                            }
                            unit = onClosed.invoke();
                            break;
                        case 5:
                            segment$iv4.cleanPrev();
                        default:
                            segment$iv2 = segment$iv4;
                            $i$f$sendImplOnNoWaiter = $i$f$sendImplOnNoWaiter2;
                    }
                }
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int updateCellSend(ChannelSegment<E> segment, int index, E element, long s, Object waiter, boolean closed) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        segment.storeElement$kotlinx_coroutines_core(index, element);
        if (closed) {
            return updateCellSendSlow(segment, index, element, s, waiter, closed);
        }
        Object state = segment.getState$kotlinx_coroutines_core(index);
        if (state == null) {
            if (bufferOrRendezvousSend(s)) {
                if (segment.casState$kotlinx_coroutines_core(index, null, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else {
                if (waiter == null) {
                    return 3;
                }
                if (segment.casState$kotlinx_coroutines_core(index, null, waiter)) {
                    return 2;
                }
            }
        } else if (state instanceof Waiter) {
            segment.cleanElement$kotlinx_coroutines_core(index);
            if (tryResumeReceiver(state, element)) {
                symbol3 = BufferedChannelKt.DONE_RCV;
                segment.setState$kotlinx_coroutines_core(index, symbol3);
                onReceiveDequeued();
                return 0;
            }
            symbol = BufferedChannelKt.INTERRUPTED_RCV;
            Object andSetState$kotlinx_coroutines_core = segment.getAndSetState$kotlinx_coroutines_core(index, symbol);
            symbol2 = BufferedChannelKt.INTERRUPTED_RCV;
            if (andSetState$kotlinx_coroutines_core != symbol2) {
                segment.onCancelledRequest(index, true);
            }
            return 5;
        }
        return updateCellSendSlow(segment, index, element, s, waiter, closed);
    }

    private final int updateCellSendSlow(ChannelSegment<E> segment, int index, E element, long s, Object waiter, boolean closed) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Symbol symbol7;
        while (true) {
            Object state = segment.getState$kotlinx_coroutines_core(index);
            if (state != null) {
                symbol2 = BufferedChannelKt.IN_BUFFER;
                if (state != symbol2) {
                    symbol3 = BufferedChannelKt.INTERRUPTED_RCV;
                    if (state != symbol3) {
                        symbol4 = BufferedChannelKt.POISONED;
                        if (state == symbol4) {
                            segment.cleanElement$kotlinx_coroutines_core(index);
                            return 5;
                        }
                        if (state == BufferedChannelKt.getCHANNEL_CLOSED()) {
                            segment.cleanElement$kotlinx_coroutines_core(index);
                            completeCloseOrCancel();
                            return 4;
                        }
                        if (DebugKt.getASSERTIONS_ENABLED()) {
                            if ((((state instanceof Waiter) || (state instanceof WaiterEB)) ? 1 : 0) == 0) {
                                throw new AssertionError();
                            }
                        }
                        segment.cleanElement$kotlinx_coroutines_core(index);
                        Object receiver = state instanceof WaiterEB ? ((WaiterEB) state).waiter : state;
                        if (tryResumeReceiver(receiver, element)) {
                            symbol7 = BufferedChannelKt.DONE_RCV;
                            segment.setState$kotlinx_coroutines_core(index, symbol7);
                            onReceiveDequeued();
                            return 0;
                        }
                        symbol5 = BufferedChannelKt.INTERRUPTED_RCV;
                        Object andSetState$kotlinx_coroutines_core = segment.getAndSetState$kotlinx_coroutines_core(index, symbol5);
                        symbol6 = BufferedChannelKt.INTERRUPTED_RCV;
                        if (andSetState$kotlinx_coroutines_core != symbol6) {
                            segment.onCancelledRequest(index, true);
                        }
                        return 5;
                    }
                    segment.cleanElement$kotlinx_coroutines_core(index);
                    return 5;
                }
                if (segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else if (bufferOrRendezvousSend(s) && !closed) {
                if (segment.casState$kotlinx_coroutines_core(index, null, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else if (closed) {
                symbol = BufferedChannelKt.INTERRUPTED_SEND;
                if (segment.casState$kotlinx_coroutines_core(index, null, symbol)) {
                    segment.onCancelledRequest(index, false);
                    return 4;
                }
            } else {
                if (waiter == null) {
                    return 3;
                }
                if (segment.casState$kotlinx_coroutines_core(index, null, waiter)) {
                    return 2;
                }
            }
        }
    }

    private final boolean shouldSendSuspend(long curSendersAndCloseStatus) {
        if (isClosedForSend0(curSendersAndCloseStatus)) {
            return false;
        }
        long $this$sendersCounter$iv = curSendersAndCloseStatus & 1152921504606846975L;
        return !bufferOrRendezvousSend($this$sendersCounter$iv);
    }

    private final boolean bufferOrRendezvousSend(long curSenders) {
        return curSenders < getBufferEndCounter() || curSenders < getReceiversCounter$kotlinx_coroutines_core() + ((long) this.capacity);
    }

    public boolean shouldSendSuspend$kotlinx_coroutines_core() {
        return shouldSendSuspend(sendersAndCloseStatus$volatile$FU.get(this));
    }

    private final boolean tryResumeReceiver(Object $this$tryResumeReceiver, E e) {
        boolean tryResume0;
        boolean tryResume02;
        if ($this$tryResumeReceiver instanceof SelectInstance) {
            return ((SelectInstance) $this$tryResumeReceiver).trySelect(this, e);
        }
        if ($this$tryResumeReceiver instanceof ReceiveCatching) {
            Intrinsics.checkNotNull($this$tryResumeReceiver, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveCatching<E of kotlinx.coroutines.channels.BufferedChannel>");
            CancellableContinuationImpl<ChannelResult<? extends E>> cancellableContinuationImpl = ((ReceiveCatching) $this$tryResumeReceiver).cont;
            ChannelResult m2079boximpl = ChannelResult.m2079boximpl(ChannelResult.INSTANCE.m2094successJP2dKIU(e));
            Function1<E, Unit> function1 = this.onUndeliveredElement;
            tryResume02 = BufferedChannelKt.tryResume0(cancellableContinuationImpl, m2079boximpl, (Function3) (function1 != null ? bindCancellationFunResult(function1) : null));
            return tryResume02;
        }
        if ($this$tryResumeReceiver instanceof BufferedChannelIterator) {
            Intrinsics.checkNotNull($this$tryResumeReceiver, "null cannot be cast to non-null type kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator<E of kotlinx.coroutines.channels.BufferedChannel>");
            return ((BufferedChannelIterator) $this$tryResumeReceiver).tryResumeHasNext(e);
        }
        if ($this$tryResumeReceiver instanceof CancellableContinuation) {
            Intrinsics.checkNotNull($this$tryResumeReceiver, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<E of kotlinx.coroutines.channels.BufferedChannel>");
            CancellableContinuation cancellableContinuation = (CancellableContinuation) $this$tryResumeReceiver;
            Function1<E, Unit> function12 = this.onUndeliveredElement;
            tryResume0 = BufferedChannelKt.tryResume0(cancellableContinuation, e, (Function3) (function12 != null ? bindCancellationFun(function12) : null));
            return tryResume0;
        }
        throw new IllegalStateException(("Unexpected receiver type: " + $this$tryResumeReceiver).toString());
    }

    protected void onReceiveEnqueued() {
    }

    protected void onReceiveDequeued() {
    }

    static /* synthetic */ <E> Object receive$suspendImpl(BufferedChannel<E> bufferedChannel, Continuation<? super E> continuation) {
        ChannelSegment segment$iv;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        ChannelSegment segment$iv2 = (ChannelSegment) getReceiveSegment$volatile$FU().get(bufferedChannel);
        while (!bufferedChannel.isClosedForReceive()) {
            long r$iv = getReceivers$volatile$FU().getAndIncrement(bufferedChannel);
            long id$iv = r$iv / BufferedChannelKt.SEGMENT_SIZE;
            int i$iv = (int) (r$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment$iv2.id != id$iv) {
                ChannelSegment segment$iv3 = bufferedChannel.findSegmentReceive(id$iv, segment$iv2);
                if (segment$iv3 == null) {
                    continue;
                } else {
                    segment$iv = segment$iv3;
                }
            } else {
                segment$iv = segment$iv2;
            }
            Object updCellResult$iv = bufferedChannel.updateCellReceive(segment$iv, i$iv, r$iv, null);
            symbol = BufferedChannelKt.SUSPEND;
            if (updCellResult$iv != symbol) {
                symbol2 = BufferedChannelKt.FAILED;
                if (updCellResult$iv != symbol2) {
                    symbol3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                    if (updCellResult$iv == symbol3) {
                        ChannelSegment segm = segment$iv;
                        return bufferedChannel.receiveOnNoWaiterSuspend(segm, i$iv, r$iv, continuation);
                    }
                    segment$iv.cleanPrev();
                    return updCellResult$iv;
                }
                if (r$iv < bufferedChannel.getSendersCounter$kotlinx_coroutines_core()) {
                    segment$iv.cleanPrev();
                }
                segment$iv2 = segment$iv;
            } else {
                throw new IllegalStateException("unexpected".toString());
            }
        }
        throw StackTraceRecoveryKt.recoverStackTrace(bufferedChannel.getReceiveException());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object receiveOnNoWaiterSuspend(ChannelSegment<E> channelSegment, int i, long j, Continuation<? super E> continuation) {
        Symbol symbol;
        Symbol symbol2;
        ChannelSegment channelSegment2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        int i2 = 0;
        Continuation<? super E> continuation2 = continuation;
        int i3 = 0;
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation2));
        try {
            Object updateCellReceive = updateCellReceive(channelSegment, i, j, orCreateCancellableContinuation);
            symbol = BufferedChannelKt.SUSPEND;
            try {
                if (updateCellReceive != symbol) {
                    symbol2 = BufferedChannelKt.FAILED;
                    try {
                        if (updateCellReceive != symbol2) {
                            channelSegment.cleanPrev();
                            Function1<E, Unit> function1 = this.onUndeliveredElement;
                            orCreateCancellableContinuation.resume((CancellableContinuationImpl) updateCellReceive, (Function3<? super Throwable, ? super CancellableContinuationImpl, ? super CoroutineContext, Unit>) (function1 != null ? bindCancellationFun(function1) : null));
                        } else {
                            if (j < getSendersCounter$kotlinx_coroutines_core()) {
                                channelSegment.cleanPrev();
                            }
                            ChannelSegment channelSegment3 = (ChannelSegment) getReceiveSegment$volatile$FU().get(this);
                            while (!isClosedForReceive()) {
                                long andIncrement = getReceivers$volatile$FU().getAndIncrement(this);
                                long j2 = andIncrement / BufferedChannelKt.SEGMENT_SIZE;
                                int i4 = i2;
                                Continuation<? super E> continuation3 = continuation2;
                                try {
                                    int i5 = (int) (andIncrement % BufferedChannelKt.SEGMENT_SIZE);
                                    int i6 = i3;
                                    if (channelSegment3.id != j2) {
                                        channelSegment2 = findSegmentReceive(j2, channelSegment3);
                                        if (channelSegment2 == null) {
                                            i2 = i4;
                                            continuation2 = continuation3;
                                            i3 = i6;
                                        }
                                    } else {
                                        channelSegment2 = channelSegment3;
                                    }
                                    Object updateCellReceive2 = updateCellReceive(channelSegment2, i5, andIncrement, orCreateCancellableContinuation);
                                    symbol3 = BufferedChannelKt.SUSPEND;
                                    if (updateCellReceive2 != symbol3) {
                                        symbol4 = BufferedChannelKt.FAILED;
                                        if (updateCellReceive2 == symbol4) {
                                            if (andIncrement < getSendersCounter$kotlinx_coroutines_core()) {
                                                channelSegment2.cleanPrev();
                                            }
                                            channelSegment3 = channelSegment2;
                                            i2 = i4;
                                            continuation2 = continuation3;
                                            i3 = i6;
                                        } else {
                                            symbol5 = BufferedChannelKt.SUSPEND_NO_WAITER;
                                            if (updateCellReceive2 == symbol5) {
                                                throw new IllegalStateException("unexpected".toString());
                                            }
                                            channelSegment2.cleanPrev();
                                            Function1<E, Unit> function12 = this.onUndeliveredElement;
                                            orCreateCancellableContinuation.resume((CancellableContinuationImpl) updateCellReceive2, (Function3<? super Throwable, ? super CancellableContinuationImpl, ? super CoroutineContext, Unit>) (function12 != null ? bindCancellationFun(function12) : null));
                                        }
                                    } else {
                                        CancellableContinuationImpl cancellableContinuationImpl = orCreateCancellableContinuation instanceof Waiter ? orCreateCancellableContinuation : null;
                                        if (cancellableContinuationImpl != null) {
                                            prepareReceiverForSuspension(cancellableContinuationImpl, channelSegment2, i5);
                                        }
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    orCreateCancellableContinuation.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                                    throw th;
                                }
                            }
                            onClosedReceiveOnNoWaiterSuspend(orCreateCancellableContinuation);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } else {
                    try {
                        prepareReceiverForSuspension(orCreateCancellableContinuation, channelSegment, i);
                    } catch (Throwable th3) {
                        th = th3;
                        orCreateCancellableContinuation.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                        throw th;
                    }
                }
                Object result = orCreateCancellableContinuation.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(continuation);
                }
                return result;
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void prepareReceiverForSuspension(Waiter $this$prepareReceiverForSuspension, ChannelSegment<E> channelSegment, int index) {
        onReceiveEnqueued();
        $this$prepareReceiverForSuspension.invokeOnCancellation(channelSegment, index);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClosedReceiveOnNoWaiterSuspend(CancellableContinuation<? super E> cont) {
        Result.Companion companion = Result.INSTANCE;
        cont.resumeWith(Result.m569constructorimpl(ResultKt.createFailure(getReceiveException())));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /* renamed from: receiveCatching-JP2dKIU$suspendImpl, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static /* synthetic */ <E> Object m2069receiveCatchingJP2dKIU$suspendImpl(BufferedChannel<E> bufferedChannel, Continuation<? super ChannelResult<? extends E>> continuation) {
        BufferedChannel$receiveCatching$1 bufferedChannel$receiveCatching$1;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Object m2070receiveCatchingOnNoWaiterSuspendGKJJFZk;
        int $i$f$receiveImpl;
        if (continuation instanceof BufferedChannel$receiveCatching$1) {
            bufferedChannel$receiveCatching$1 = (BufferedChannel$receiveCatching$1) continuation;
            if ((bufferedChannel$receiveCatching$1.label & Integer.MIN_VALUE) != 0) {
                bufferedChannel$receiveCatching$1.label -= Integer.MIN_VALUE;
                Object $result = bufferedChannel$receiveCatching$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (bufferedChannel$receiveCatching$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        ChannelSegment segment$iv = (ChannelSegment) getReceiveSegment$volatile$FU().get(bufferedChannel);
                        while (!bufferedChannel.isClosedForReceive()) {
                            long r$iv = getReceivers$volatile$FU().getAndIncrement(bufferedChannel);
                            long id$iv = r$iv / BufferedChannelKt.SEGMENT_SIZE;
                            int i$iv = (int) (r$iv % BufferedChannelKt.SEGMENT_SIZE);
                            if (segment$iv.id != id$iv) {
                                ChannelSegment findSegmentReceive = bufferedChannel.findSegmentReceive(id$iv, segment$iv);
                                if (findSegmentReceive == null) {
                                    continue;
                                } else {
                                    segment$iv = findSegmentReceive;
                                }
                            }
                            Object updCellResult$iv = bufferedChannel.updateCellReceive(segment$iv, i$iv, r$iv, null);
                            symbol = BufferedChannelKt.SUSPEND;
                            if (updCellResult$iv != symbol) {
                                symbol2 = BufferedChannelKt.FAILED;
                                if (updCellResult$iv != symbol2) {
                                    symbol3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                                    if (updCellResult$iv == symbol3) {
                                        ChannelSegment segm = segment$iv;
                                        bufferedChannel$receiveCatching$1.label = 1;
                                        m2070receiveCatchingOnNoWaiterSuspendGKJJFZk = bufferedChannel.m2070receiveCatchingOnNoWaiterSuspendGKJJFZk(segm, i$iv, r$iv, bufferedChannel$receiveCatching$1);
                                        if (m2070receiveCatchingOnNoWaiterSuspendGKJJFZk == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        $i$f$receiveImpl = 0;
                                        break;
                                    } else {
                                        segment$iv.cleanPrev();
                                        return ChannelResult.INSTANCE.m2094successJP2dKIU(updCellResult$iv);
                                    }
                                } else if (r$iv < bufferedChannel.getSendersCounter$kotlinx_coroutines_core()) {
                                    segment$iv.cleanPrev();
                                }
                            } else {
                                throw new IllegalStateException("unexpected".toString());
                            }
                        }
                        return ChannelResult.INSTANCE.m2092closedJP2dKIU(bufferedChannel.getCloseCause());
                    case 1:
                        $i$f$receiveImpl = 0;
                        ResultKt.throwOnFailure($result);
                        m2070receiveCatchingOnNoWaiterSuspendGKJJFZk = ((ChannelResult) $result).getHolder();
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return m2070receiveCatchingOnNoWaiterSuspendGKJJFZk;
            }
        }
        bufferedChannel$receiveCatching$1 = new BufferedChannel$receiveCatching$1(bufferedChannel, continuation);
        Object $result2 = bufferedChannel$receiveCatching$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (bufferedChannel$receiveCatching$1.label) {
        }
        return m2070receiveCatchingOnNoWaiterSuspendGKJJFZk;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /* renamed from: receiveCatchingOnNoWaiterSuspend-GKJJFZk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m2070receiveCatchingOnNoWaiterSuspendGKJJFZk(ChannelSegment<E> channelSegment, int i, long j, Continuation<? super ChannelResult<? extends E>> continuation) {
        BufferedChannel$receiveCatchingOnNoWaiterSuspend$1 bufferedChannel$receiveCatchingOnNoWaiterSuspend$1;
        BufferedChannel$receiveCatchingOnNoWaiterSuspend$1 bufferedChannel$receiveCatchingOnNoWaiterSuspend$12;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Object result;
        if (continuation instanceof BufferedChannel$receiveCatchingOnNoWaiterSuspend$1) {
            bufferedChannel$receiveCatchingOnNoWaiterSuspend$1 = (BufferedChannel$receiveCatchingOnNoWaiterSuspend$1) continuation;
            if ((bufferedChannel$receiveCatchingOnNoWaiterSuspend$1.label & Integer.MIN_VALUE) != 0) {
                bufferedChannel$receiveCatchingOnNoWaiterSuspend$1.label -= Integer.MIN_VALUE;
                bufferedChannel$receiveCatchingOnNoWaiterSuspend$12 = bufferedChannel$receiveCatchingOnNoWaiterSuspend$1;
                Object obj = bufferedChannel$receiveCatchingOnNoWaiterSuspend$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (bufferedChannel$receiveCatchingOnNoWaiterSuspend$12.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        bufferedChannel$receiveCatchingOnNoWaiterSuspend$12.L$0 = this;
                        bufferedChannel$receiveCatchingOnNoWaiterSuspend$12.L$1 = channelSegment;
                        bufferedChannel$receiveCatchingOnNoWaiterSuspend$12.I$0 = i;
                        bufferedChannel$receiveCatchingOnNoWaiterSuspend$12.J$0 = j;
                        bufferedChannel$receiveCatchingOnNoWaiterSuspend$12.label = 1;
                        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(bufferedChannel$receiveCatchingOnNoWaiterSuspend$12));
                        try {
                            Intrinsics.checkNotNull(orCreateCancellableContinuation, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuationImpl<kotlinx.coroutines.channels.ChannelResult<E of kotlinx.coroutines.channels.BufferedChannel.receiveCatchingOnNoWaiterSuspend_GKJJFZk$lambda$38>>");
                            ReceiveCatching receiveCatching = new ReceiveCatching(orCreateCancellableContinuation);
                            Object updateCellReceive = updateCellReceive(channelSegment, i, j, receiveCatching);
                            symbol = BufferedChannelKt.SUSPEND;
                            if (updateCellReceive != symbol) {
                                symbol2 = BufferedChannelKt.FAILED;
                                if (updateCellReceive == symbol2) {
                                    if (j < getSendersCounter$kotlinx_coroutines_core()) {
                                        channelSegment.cleanPrev();
                                    }
                                    ChannelSegment channelSegment2 = (ChannelSegment) getReceiveSegment$volatile$FU().get(this);
                                    while (!isClosedForReceive()) {
                                        long andIncrement = getReceivers$volatile$FU().getAndIncrement(this);
                                        long j2 = andIncrement / BufferedChannelKt.SEGMENT_SIZE;
                                        int i2 = (int) (andIncrement % BufferedChannelKt.SEGMENT_SIZE);
                                        if (channelSegment2.id != j2) {
                                            ChannelSegment findSegmentReceive = findSegmentReceive(j2, channelSegment2);
                                            if (findSegmentReceive != null) {
                                                channelSegment2 = findSegmentReceive;
                                            }
                                        }
                                        Object updateCellReceive2 = updateCellReceive(channelSegment2, i2, andIncrement, receiveCatching);
                                        symbol3 = BufferedChannelKt.SUSPEND;
                                        if (updateCellReceive2 != symbol3) {
                                            symbol4 = BufferedChannelKt.FAILED;
                                            if (updateCellReceive2 != symbol4) {
                                                symbol5 = BufferedChannelKt.SUSPEND_NO_WAITER;
                                                if (updateCellReceive2 == symbol5) {
                                                    throw new IllegalStateException("unexpected".toString());
                                                }
                                                channelSegment2.cleanPrev();
                                                ChannelResult m2079boximpl = ChannelResult.m2079boximpl(ChannelResult.INSTANCE.m2094successJP2dKIU(updateCellReceive2));
                                                Function1<E, Unit> function1 = this.onUndeliveredElement;
                                                orCreateCancellableContinuation.resume((CancellableContinuationImpl) m2079boximpl, (Function3<? super Throwable, ? super CancellableContinuationImpl, ? super CoroutineContext, Unit>) (function1 != null ? bindCancellationFunResult(function1) : null));
                                            } else if (andIncrement < getSendersCounter$kotlinx_coroutines_core()) {
                                                channelSegment2.cleanPrev();
                                            }
                                        } else {
                                            ReceiveCatching receiveCatching2 = receiveCatching instanceof Waiter ? receiveCatching : null;
                                            if (receiveCatching2 != null) {
                                                prepareReceiverForSuspension(receiveCatching2, channelSegment2, i2);
                                            }
                                        }
                                    }
                                    onClosedReceiveCatchingOnNoWaiterSuspend(orCreateCancellableContinuation);
                                } else {
                                    channelSegment.cleanPrev();
                                    ChannelResult m2079boximpl2 = ChannelResult.m2079boximpl(ChannelResult.INSTANCE.m2094successJP2dKIU(updateCellReceive));
                                    Function1<E, Unit> function12 = this.onUndeliveredElement;
                                    orCreateCancellableContinuation.resume((CancellableContinuationImpl) m2079boximpl2, (Function3<? super Throwable, ? super CancellableContinuationImpl, ? super CoroutineContext, Unit>) (function12 != null ? bindCancellationFunResult(function12) : null));
                                }
                            } else {
                                prepareReceiverForSuspension(receiveCatching, channelSegment, i);
                            }
                            result = orCreateCancellableContinuation.getResult();
                            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                DebugProbesKt.probeCoroutineSuspended(bufferedChannel$receiveCatchingOnNoWaiterSuspend$12);
                            }
                            if (result != coroutine_suspended) {
                                break;
                            } else {
                                return coroutine_suspended;
                            }
                        } catch (Throwable th) {
                            orCreateCancellableContinuation.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                            throw th;
                        }
                    case 1:
                        long j3 = bufferedChannel$receiveCatchingOnNoWaiterSuspend$12.J$0;
                        int i3 = bufferedChannel$receiveCatchingOnNoWaiterSuspend$12.I$0;
                        ResultKt.throwOnFailure(obj);
                        result = obj;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return ((ChannelResult) result).getHolder();
            }
        }
        bufferedChannel$receiveCatchingOnNoWaiterSuspend$1 = new BufferedChannel$receiveCatchingOnNoWaiterSuspend$1(this, continuation);
        bufferedChannel$receiveCatchingOnNoWaiterSuspend$12 = bufferedChannel$receiveCatchingOnNoWaiterSuspend$1;
        Object obj2 = bufferedChannel$receiveCatchingOnNoWaiterSuspend$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (bufferedChannel$receiveCatchingOnNoWaiterSuspend$12.label) {
        }
        return ((ChannelResult) result).getHolder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClosedReceiveCatchingOnNoWaiterSuspend(CancellableContinuation<? super ChannelResult<? extends E>> cont) {
        Result.Companion companion = Result.INSTANCE;
        cont.resumeWith(Result.m569constructorimpl(ChannelResult.m2079boximpl(ChannelResult.INSTANCE.m2092closedJP2dKIU(getCloseCause()))));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: tryReceive-PtdJZtk, reason: not valid java name */
    public Object mo2072tryReceivePtdJZtk() {
        Object waiter$iv;
        ChannelSegment segment$iv;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        long r = receivers$volatile$FU.get(this);
        long sendersAndCloseStatusCur = sendersAndCloseStatus$volatile$FU.get(this);
        if (isClosedForReceive0(sendersAndCloseStatusCur)) {
            return ChannelResult.INSTANCE.m2092closedJP2dKIU(getCloseCause());
        }
        long $this$sendersCounter$iv = sendersAndCloseStatusCur & 1152921504606846975L;
        if (r >= $this$sendersCounter$iv) {
            return ChannelResult.INSTANCE.m2093failurePtdJZtk();
        }
        waiter$iv = BufferedChannelKt.INTERRUPTED_RCV;
        ChannelSegment segment$iv2 = (ChannelSegment) getReceiveSegment$volatile$FU().get(this);
        while (!isClosedForReceive()) {
            long r$iv = getReceivers$volatile$FU().getAndIncrement(this);
            long id$iv = r$iv / BufferedChannelKt.SEGMENT_SIZE;
            int i$iv = (int) (r$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment$iv2.id != id$iv) {
                ChannelSegment segment$iv3 = findSegmentReceive(id$iv, segment$iv2);
                if (segment$iv3 == null) {
                    continue;
                } else {
                    segment$iv = segment$iv3;
                }
            } else {
                segment$iv = segment$iv2;
            }
            long r2 = r;
            ChannelSegment segment$iv4 = segment$iv;
            Object updCellResult$iv = updateCellReceive(segment$iv, i$iv, r$iv, waiter$iv);
            symbol = BufferedChannelKt.SUSPEND;
            if (updCellResult$iv != symbol) {
                symbol2 = BufferedChannelKt.FAILED;
                if (updCellResult$iv != symbol2) {
                    symbol3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                    if (updCellResult$iv == symbol3) {
                        throw new IllegalStateException("unexpected".toString());
                    }
                    segment$iv4.cleanPrev();
                    return ChannelResult.INSTANCE.m2094successJP2dKIU(updCellResult$iv);
                }
                if (r$iv < getSendersCounter$kotlinx_coroutines_core()) {
                    segment$iv4.cleanPrev();
                }
                segment$iv2 = segment$iv4;
                r = r2;
            } else {
                Waiter waiter = waiter$iv instanceof Waiter ? (Waiter) waiter$iv : null;
                if (waiter != null) {
                    prepareReceiverForSuspension(waiter, segment$iv4, i$iv);
                }
                waitExpandBufferCompletion$kotlinx_coroutines_core(r$iv);
                segment$iv4.onSlotCleaned();
                return ChannelResult.INSTANCE.m2093failurePtdJZtk();
            }
        }
        return ChannelResult.INSTANCE.m2092closedJP2dKIU(getCloseCause());
    }

    protected final void dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(long globalCellIndex) {
        Symbol symbol;
        UndeliveredElementException it;
        if (DebugKt.getASSERTIONS_ENABLED() && !isConflatedDropOldest()) {
            throw new AssertionError();
        }
        ChannelSegment segment = (ChannelSegment) receiveSegment$volatile$FU.get(this);
        while (true) {
            long r = receivers$volatile$FU.get(this);
            if (globalCellIndex < Math.max(this.capacity + r, getBufferEndCounter())) {
                return;
            }
            if (receivers$volatile$FU.compareAndSet(this, r, r + 1)) {
                long id = r / BufferedChannelKt.SEGMENT_SIZE;
                int i = (int) (r % BufferedChannelKt.SEGMENT_SIZE);
                if (segment.id != id) {
                    ChannelSegment findSegmentReceive = findSegmentReceive(id, segment);
                    if (findSegmentReceive == null) {
                        continue;
                    } else {
                        segment = findSegmentReceive;
                    }
                }
                Object updCellResult = updateCellReceive(segment, i, r, null);
                symbol = BufferedChannelKt.FAILED;
                if (updCellResult != symbol) {
                    segment.cleanPrev();
                    Function1<E, Unit> function1 = this.onUndeliveredElement;
                    if (function1 != null && (it = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, updCellResult, null, 2, null)) != null) {
                        throw it;
                    }
                } else if (r < getSendersCounter$kotlinx_coroutines_core()) {
                    segment.cleanPrev();
                }
            }
        }
    }

    static /* synthetic */ Object receiveImpl$default(BufferedChannel $this, Object waiter, Function1 onElementRetrieved, Function3 onSuspend, Function0 onClosed, Function3 onNoWaiterSuspend, int i, Object obj) {
        Function3 onNoWaiterSuspend2;
        ChannelSegment segment;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: receiveImpl");
        }
        if ((i & 16) == 0) {
            onNoWaiterSuspend2 = onNoWaiterSuspend;
        } else {
            onNoWaiterSuspend2 = new Function3() { // from class: kotlinx.coroutines.channels.BufferedChannel$receiveImpl$1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3) {
                    return invoke((ChannelSegment) p1, ((Number) p2).intValue(), ((Number) p3).longValue());
                }

                public final Void invoke(ChannelSegment<E> channelSegment, int i2, long j) {
                    throw new IllegalStateException("unexpected".toString());
                }
            };
        }
        ChannelSegment segment2 = (ChannelSegment) getReceiveSegment$volatile$FU().get($this);
        while (!$this.isClosedForReceive()) {
            long r = getReceivers$volatile$FU().getAndIncrement($this);
            long id = r / BufferedChannelKt.SEGMENT_SIZE;
            int i2 = (int) (r % BufferedChannelKt.SEGMENT_SIZE);
            if (segment2.id != id) {
                ChannelSegment segment3 = $this.findSegmentReceive(id, segment2);
                if (segment3 == null) {
                    continue;
                } else {
                    segment = segment3;
                }
            } else {
                segment = segment2;
            }
            Object updCellResult = $this.updateCellReceive(segment, i2, r, waiter);
            symbol = BufferedChannelKt.SUSPEND;
            if (updCellResult != symbol) {
                symbol2 = BufferedChannelKt.FAILED;
                if (updCellResult != symbol2) {
                    symbol3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                    if (updCellResult == symbol3) {
                        return onNoWaiterSuspend2.invoke(segment, Integer.valueOf(i2), Long.valueOf(r));
                    }
                    segment.cleanPrev();
                    return onElementRetrieved.invoke(updCellResult);
                }
                if (r < $this.getSendersCounter$kotlinx_coroutines_core()) {
                    segment.cleanPrev();
                }
                segment2 = segment;
            } else {
                Waiter waiter2 = waiter instanceof Waiter ? (Waiter) waiter : null;
                if (waiter2 != null) {
                    $this.prepareReceiverForSuspension(waiter2, segment, i2);
                }
                return onSuspend.invoke(segment, Integer.valueOf(i2), Long.valueOf(r));
            }
        }
        return onClosed.invoke();
    }

    private final <R> R receiveImpl(Object waiter, Function1<? super E, ? extends R> onElementRetrieved, Function3<? super ChannelSegment<E>, ? super Integer, ? super Long, ? extends R> onSuspend, Function0<? extends R> onClosed, Function3<? super ChannelSegment<E>, ? super Integer, ? super Long, ? extends R> onNoWaiterSuspend) {
        ChannelSegment segment;
        Object obj;
        Object obj2;
        Object obj3;
        ChannelSegment segment2 = (ChannelSegment) getReceiveSegment$volatile$FU().get(this);
        while (!isClosedForReceive()) {
            long r = getReceivers$volatile$FU().getAndIncrement(this);
            long id = r / BufferedChannelKt.SEGMENT_SIZE;
            int i = (int) (r % BufferedChannelKt.SEGMENT_SIZE);
            if (segment2.id != id) {
                ChannelSegment segment3 = findSegmentReceive(id, segment2);
                if (segment3 == null) {
                    continue;
                } else {
                    segment = segment3;
                }
            } else {
                segment = segment2;
            }
            Object updCellResult = updateCellReceive(segment, i, r, waiter);
            obj = BufferedChannelKt.SUSPEND;
            if (updCellResult != obj) {
                obj2 = BufferedChannelKt.FAILED;
                if (updCellResult != obj2) {
                    obj3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                    if (updCellResult == obj3) {
                        return onNoWaiterSuspend.invoke(segment, Integer.valueOf(i), Long.valueOf(r));
                    }
                    segment.cleanPrev();
                    return onElementRetrieved.invoke(updCellResult);
                }
                if (r < getSendersCounter$kotlinx_coroutines_core()) {
                    segment.cleanPrev();
                }
                segment2 = segment;
            } else {
                Waiter waiter2 = waiter instanceof Waiter ? (Waiter) waiter : null;
                if (waiter2 != null) {
                    prepareReceiverForSuspension(waiter2, segment, i);
                }
                return onSuspend.invoke(segment, Integer.valueOf(i), Long.valueOf(r));
            }
        }
        return onClosed.invoke();
    }

    private final void receiveImplOnNoWaiter(ChannelSegment<E> segment, int index, long r, Waiter waiter, Function1<? super E, Unit> onElementRetrieved, Function0<Unit> onClosed) {
        Object obj;
        Object obj2;
        ChannelSegment segment$iv;
        Object obj3;
        Object obj4;
        Object obj5;
        int $i$f$receiveImplOnNoWaiter = 0;
        Object updCellResult = updateCellReceive(segment, index, r, waiter);
        obj = BufferedChannelKt.SUSPEND;
        if (updCellResult != obj) {
            obj2 = BufferedChannelKt.FAILED;
            if (updCellResult != obj2) {
                segment.cleanPrev();
                onElementRetrieved.invoke(updCellResult);
                return;
            }
            if (r < getSendersCounter$kotlinx_coroutines_core()) {
                segment.cleanPrev();
            }
            ChannelSegment segment$iv2 = (ChannelSegment) getReceiveSegment$volatile$FU().get(this);
            while (!isClosedForReceive()) {
                long r$iv = getReceivers$volatile$FU().getAndIncrement(this);
                long id$iv = r$iv / BufferedChannelKt.SEGMENT_SIZE;
                int i$iv = (int) (r$iv % BufferedChannelKt.SEGMENT_SIZE);
                if (segment$iv2.id != id$iv) {
                    ChannelSegment segment$iv3 = findSegmentReceive(id$iv, segment$iv2);
                    if (segment$iv3 == null) {
                        continue;
                    } else {
                        segment$iv = segment$iv3;
                    }
                } else {
                    segment$iv = segment$iv2;
                }
                int $i$f$receiveImplOnNoWaiter2 = $i$f$receiveImplOnNoWaiter;
                ChannelSegment segment$iv4 = segment$iv;
                Object updCellResult$iv = updateCellReceive(segment$iv, i$iv, r$iv, waiter);
                obj3 = BufferedChannelKt.SUSPEND;
                if (updCellResult$iv != obj3) {
                    obj4 = BufferedChannelKt.FAILED;
                    if (updCellResult$iv != obj4) {
                        obj5 = BufferedChannelKt.SUSPEND_NO_WAITER;
                        if (updCellResult$iv == obj5) {
                            throw new IllegalStateException("unexpected".toString());
                        }
                        segment$iv4.cleanPrev();
                        onElementRetrieved.invoke(updCellResult$iv);
                        return;
                    }
                    if (r$iv < getSendersCounter$kotlinx_coroutines_core()) {
                        segment$iv4.cleanPrev();
                    }
                    segment$iv2 = segment$iv4;
                    $i$f$receiveImplOnNoWaiter = $i$f$receiveImplOnNoWaiter2;
                } else {
                    Waiter waiter2 = waiter instanceof Waiter ? waiter : null;
                    if (waiter2 != null) {
                        prepareReceiverForSuspension(waiter2, segment$iv4, i$iv);
                    }
                    Unit unit = Unit.INSTANCE;
                    return;
                }
            }
            onClosed.invoke();
            return;
        }
        prepareReceiverForSuspension(waiter, segment, index);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object updateCellReceive(ChannelSegment<E> segment, int index, long r, Object waiter) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Object state = segment.getState$kotlinx_coroutines_core(index);
        if (state == null) {
            long $this$sendersCounter$iv = sendersAndCloseStatus$volatile$FU.get(this);
            long senders = $this$sendersCounter$iv & 1152921504606846975L;
            if (r >= senders) {
                if (waiter == null) {
                    symbol3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                    return symbol3;
                }
                if (segment.casState$kotlinx_coroutines_core(index, state, waiter)) {
                    expandBuffer();
                    symbol2 = BufferedChannelKt.SUSPEND;
                    return symbol2;
                }
            }
        } else if (state == BufferedChannelKt.BUFFERED) {
            symbol = BufferedChannelKt.DONE_RCV;
            if (segment.casState$kotlinx_coroutines_core(index, state, symbol)) {
                expandBuffer();
                return segment.retrieveElement$kotlinx_coroutines_core(index);
            }
        }
        return updateCellReceiveSlow(segment, index, r, waiter);
    }

    private final Object updateCellReceiveSlow(ChannelSegment<E> segment, int index, long r, Object waiter) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Symbol symbol7;
        Symbol symbol8;
        Symbol symbol9;
        Symbol symbol10;
        Symbol symbol11;
        Symbol symbol12;
        Symbol symbol13;
        Symbol symbol14;
        Symbol symbol15;
        Symbol symbol16;
        while (true) {
            Object state = segment.getState$kotlinx_coroutines_core(index);
            if (state != null) {
                symbol5 = BufferedChannelKt.IN_BUFFER;
                if (state != symbol5) {
                    if (state == BufferedChannelKt.BUFFERED) {
                        symbol6 = BufferedChannelKt.DONE_RCV;
                        if (segment.casState$kotlinx_coroutines_core(index, state, symbol6)) {
                            expandBuffer();
                            return segment.retrieveElement$kotlinx_coroutines_core(index);
                        }
                    } else {
                        symbol7 = BufferedChannelKt.INTERRUPTED_SEND;
                        if (state == symbol7) {
                            symbol8 = BufferedChannelKt.FAILED;
                            return symbol8;
                        }
                        symbol9 = BufferedChannelKt.POISONED;
                        if (state == symbol9) {
                            symbol10 = BufferedChannelKt.FAILED;
                            return symbol10;
                        }
                        if (state != BufferedChannelKt.getCHANNEL_CLOSED()) {
                            symbol12 = BufferedChannelKt.RESUMING_BY_EB;
                            if (state != symbol12) {
                                symbol13 = BufferedChannelKt.RESUMING_BY_RCV;
                                if (segment.casState$kotlinx_coroutines_core(index, state, symbol13)) {
                                    boolean helpExpandBuffer = state instanceof WaiterEB;
                                    Object sender = state instanceof WaiterEB ? ((WaiterEB) state).waiter : state;
                                    if (tryResumeSender(sender, segment, index)) {
                                        symbol16 = BufferedChannelKt.DONE_RCV;
                                        segment.setState$kotlinx_coroutines_core(index, symbol16);
                                        expandBuffer();
                                        return segment.retrieveElement$kotlinx_coroutines_core(index);
                                    }
                                    symbol14 = BufferedChannelKt.INTERRUPTED_SEND;
                                    segment.setState$kotlinx_coroutines_core(index, symbol14);
                                    segment.onCancelledRequest(index, false);
                                    if (helpExpandBuffer) {
                                        expandBuffer();
                                    }
                                    symbol15 = BufferedChannelKt.FAILED;
                                    return symbol15;
                                }
                            } else {
                                continue;
                            }
                        } else {
                            expandBuffer();
                            symbol11 = BufferedChannelKt.FAILED;
                            return symbol11;
                        }
                    }
                }
            }
            long $this$sendersCounter$iv = sendersAndCloseStatus$volatile$FU.get(this);
            long senders = $this$sendersCounter$iv & 1152921504606846975L;
            if (r < senders) {
                symbol = BufferedChannelKt.POISONED;
                if (segment.casState$kotlinx_coroutines_core(index, state, symbol)) {
                    expandBuffer();
                    symbol2 = BufferedChannelKt.FAILED;
                    return symbol2;
                }
            } else {
                if (waiter == null) {
                    symbol3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                    return symbol3;
                }
                if (segment.casState$kotlinx_coroutines_core(index, state, waiter)) {
                    expandBuffer();
                    symbol4 = BufferedChannelKt.SUSPEND;
                    return symbol4;
                }
            }
        }
    }

    private final boolean tryResumeSender(Object $this$tryResumeSender, ChannelSegment<E> channelSegment, int index) {
        if ($this$tryResumeSender instanceof CancellableContinuation) {
            Intrinsics.checkNotNull($this$tryResumeSender, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            return BufferedChannelKt.tryResume0$default((CancellableContinuation) $this$tryResumeSender, Unit.INSTANCE, null, 2, null);
        }
        if ($this$tryResumeSender instanceof SelectInstance) {
            Intrinsics.checkNotNull($this$tryResumeSender, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectImplementation<*>");
            TrySelectDetailedResult trySelectResult = ((SelectImplementation) $this$tryResumeSender).trySelectDetailed(this, Unit.INSTANCE);
            if (trySelectResult == TrySelectDetailedResult.REREGISTER) {
                channelSegment.cleanElement$kotlinx_coroutines_core(index);
            }
            return trySelectResult == TrySelectDetailedResult.SUCCESSFUL;
        }
        if ($this$tryResumeSender instanceof SendBroadcast) {
            return BufferedChannelKt.tryResume0$default(((SendBroadcast) $this$tryResumeSender).getCont(), true, null, 2, null);
        }
        throw new IllegalStateException(("Unexpected waiter: " + $this$tryResumeSender).toString());
    }

    private final void expandBuffer() {
        long id;
        if (isRendezvousOrUnlimited()) {
            return;
        }
        ChannelSegment segment = (ChannelSegment) bufferEndSegment$volatile$FU.get(this);
        while (true) {
            long b = bufferEnd$volatile$FU.getAndIncrement(this);
            long id2 = b / BufferedChannelKt.SEGMENT_SIZE;
            long s = getSendersCounter$kotlinx_coroutines_core();
            if (s <= b) {
                if (segment.id < id2 && segment.getNext() != 0) {
                    moveSegmentBufferEndToSpecifiedOrLast(id2, segment);
                }
                incCompletedExpandBufferAttempts$default(this, 0L, 1, null);
                return;
            }
            if (segment.id != id2) {
                id = 0;
                ChannelSegment findSegmentBufferEnd = findSegmentBufferEnd(id2, segment, b);
                if (findSegmentBufferEnd == null) {
                    continue;
                } else {
                    segment = findSegmentBufferEnd;
                }
            } else {
                id = 0;
            }
            int i = (int) (b % BufferedChannelKt.SEGMENT_SIZE);
            if (updateCellExpandBuffer(segment, i, b)) {
                incCompletedExpandBufferAttempts$default(this, id, 1, null);
                return;
            }
            incCompletedExpandBufferAttempts$default(this, id, 1, null);
        }
    }

    private final boolean updateCellExpandBuffer(ChannelSegment<E> segment, int index, long b) {
        Symbol symbol;
        Symbol symbol2;
        Object state = segment.getState$kotlinx_coroutines_core(index);
        if ((state instanceof Waiter) && b >= receivers$volatile$FU.get(this)) {
            symbol = BufferedChannelKt.RESUMING_BY_EB;
            if (segment.casState$kotlinx_coroutines_core(index, state, symbol)) {
                if (!tryResumeSender(state, segment, index)) {
                    symbol2 = BufferedChannelKt.INTERRUPTED_SEND;
                    segment.setState$kotlinx_coroutines_core(index, symbol2);
                    segment.onCancelledRequest(index, false);
                    return false;
                }
                segment.setState$kotlinx_coroutines_core(index, BufferedChannelKt.BUFFERED);
                return true;
            }
        }
        return updateCellExpandBufferSlow(segment, index, b);
    }

    private final boolean updateCellExpandBufferSlow(ChannelSegment<E> segment, int index, long b) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Symbol symbol7;
        Symbol symbol8;
        while (true) {
            Object state = segment.getState$kotlinx_coroutines_core(index);
            if (!(state instanceof Waiter)) {
                symbol3 = BufferedChannelKt.INTERRUPTED_SEND;
                if (state != symbol3) {
                    if (state == null) {
                        symbol4 = BufferedChannelKt.IN_BUFFER;
                        if (segment.casState$kotlinx_coroutines_core(index, state, symbol4)) {
                            return true;
                        }
                    } else if (state != BufferedChannelKt.BUFFERED) {
                        symbol5 = BufferedChannelKt.POISONED;
                        if (state == symbol5) {
                            break;
                        }
                        symbol6 = BufferedChannelKt.DONE_RCV;
                        if (state == symbol6) {
                            break;
                        }
                        symbol7 = BufferedChannelKt.INTERRUPTED_RCV;
                        if (state == symbol7 || state == BufferedChannelKt.getCHANNEL_CLOSED()) {
                            return true;
                        }
                        symbol8 = BufferedChannelKt.RESUMING_BY_RCV;
                        if (state != symbol8) {
                            throw new IllegalStateException(("Unexpected cell state: " + state).toString());
                        }
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            } else if (b >= receivers$volatile$FU.get(this)) {
                symbol = BufferedChannelKt.RESUMING_BY_EB;
                if (segment.casState$kotlinx_coroutines_core(index, state, symbol)) {
                    if (!tryResumeSender(state, segment, index)) {
                        symbol2 = BufferedChannelKt.INTERRUPTED_SEND;
                        segment.setState$kotlinx_coroutines_core(index, symbol2);
                        segment.onCancelledRequest(index, false);
                        return false;
                    }
                    segment.setState$kotlinx_coroutines_core(index, BufferedChannelKt.BUFFERED);
                    return true;
                }
            } else if (segment.casState$kotlinx_coroutines_core(index, state, new WaiterEB((Waiter) state))) {
                return true;
            }
        }
    }

    static /* synthetic */ void incCompletedExpandBufferAttempts$default(BufferedChannel bufferedChannel, long j, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: incCompletedExpandBufferAttempts");
        }
        if ((i & 1) != 0) {
            j = 1;
        }
        bufferedChannel.incCompletedExpandBufferAttempts(j);
    }

    private final void incCompletedExpandBufferAttempts(long nAttempts) {
        long $this$ebPauseExpandBuffers$iv;
        long it = completedExpandBuffersAndPauseFlag$volatile$FU.addAndGet(this, nAttempts);
        if ((it & Longs.MAX_POWER_OF_TWO) != 0) {
            do {
                $this$ebPauseExpandBuffers$iv = completedExpandBuffersAndPauseFlag$volatile$FU.get(this);
            } while (($this$ebPauseExpandBuffers$iv & Longs.MAX_POWER_OF_TWO) != 0);
        }
    }

    public final void waitExpandBufferCompletion$kotlinx_coroutines_core(long globalIndex) {
        int i;
        long constructEBCompletedAndPauseFlag;
        long ebCompleted;
        long constructEBCompletedAndPauseFlag2;
        long constructEBCompletedAndPauseFlag3;
        if (isRendezvousOrUnlimited()) {
            return;
        }
        while (getBufferEndCounter() <= globalIndex) {
        }
        i = BufferedChannelKt.EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS;
        boolean z = false;
        int i2 = 0;
        while (true) {
            long j = DurationKt.MAX_MILLIS;
            if (i2 >= i) {
                AtomicLongFieldUpdater handler$atomicfu$iv = completedExpandBuffersAndPauseFlag$volatile$FU;
                while (true) {
                    long it = handler$atomicfu$iv.get(this);
                    long $this$ebCompletedCounter$iv = it & j;
                    constructEBCompletedAndPauseFlag = BufferedChannelKt.constructEBCompletedAndPauseFlag($this$ebCompletedCounter$iv, true);
                    if (handler$atomicfu$iv.compareAndSet(this, it, constructEBCompletedAndPauseFlag)) {
                        break;
                    }
                    z = false;
                    j = DurationKt.MAX_MILLIS;
                }
                while (true) {
                    long b = getBufferEndCounter();
                    long ebCompletedAndBit = completedExpandBuffersAndPauseFlag$volatile$FU.get(this);
                    long $this$ebCompletedCounter$iv2 = ebCompletedAndBit & j;
                    ebCompleted = $this$ebCompletedCounter$iv2;
                    boolean pauseExpandBuffers = (Longs.MAX_POWER_OF_TWO & ebCompletedAndBit) != 0 ? true : z;
                    if (b == ebCompleted && b == getBufferEndCounter()) {
                        break;
                    }
                    if (!pauseExpandBuffers) {
                        AtomicLongFieldUpdater atomicLongFieldUpdater = completedExpandBuffersAndPauseFlag$volatile$FU;
                        constructEBCompletedAndPauseFlag2 = BufferedChannelKt.constructEBCompletedAndPauseFlag(ebCompleted, true);
                        atomicLongFieldUpdater.compareAndSet(this, ebCompletedAndBit, constructEBCompletedAndPauseFlag2);
                        z = false;
                        j = DurationKt.MAX_MILLIS;
                    } else {
                        z = false;
                        j = DurationKt.MAX_MILLIS;
                    }
                }
                AtomicLongFieldUpdater handler$atomicfu$iv2 = completedExpandBuffersAndPauseFlag$volatile$FU;
                while (true) {
                    long it2 = handler$atomicfu$iv2.get(this);
                    constructEBCompletedAndPauseFlag3 = BufferedChannelKt.constructEBCompletedAndPauseFlag(it2 & j, z);
                    long ebCompleted2 = ebCompleted;
                    if (!handler$atomicfu$iv2.compareAndSet(this, it2, constructEBCompletedAndPauseFlag3)) {
                        ebCompleted = ebCompleted2;
                        z = false;
                        j = DurationKt.MAX_MILLIS;
                    } else {
                        return;
                    }
                }
            } else {
                long b2 = getBufferEndCounter();
                long $this$ebCompletedCounter$iv3 = completedExpandBuffersAndPauseFlag$volatile$FU.get(this);
                if (b2 == (DurationKt.MAX_MILLIS & $this$ebCompletedCounter$iv3) && b2 == getBufferEndCounter()) {
                    return;
                } else {
                    i2++;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public SelectClause2<E, BufferedChannel<E>> getOnSend() {
        BufferedChannel$onSend$1 bufferedChannel$onSend$1 = BufferedChannel$onSend$1.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onSend$1, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"select\")] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \"param\")] kotlin.Any?, kotlin.Unit>");
        Function3 function3 = (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onSend$1, 3);
        BufferedChannel$onSend$2 bufferedChannel$onSend$2 = BufferedChannel$onSend$2.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onSend$2, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"param\")] kotlin.Any?, @[ParameterName(name = \"clauseResult\")] kotlin.Any?, kotlin.Any?>");
        return new SelectClause2Impl(this, function3, (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onSend$2, 3), null, 8, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void registerSelectForSend(SelectInstance<?> select, Object element) {
        ChannelSegment segment$iv;
        int $i$f$sendImpl = 0;
        ChannelSegment segment$iv2 = (ChannelSegment) sendSegment$volatile$FU.get(this);
        while (true) {
            long sendersAndCloseStatusCur$iv = sendersAndCloseStatus$volatile$FU.getAndIncrement(this);
            long $this$sendersCounter$iv$iv = sendersAndCloseStatusCur$iv & 1152921504606846975L;
            boolean closed$iv = isClosedForSend0(sendersAndCloseStatusCur$iv);
            long id$iv = $this$sendersCounter$iv$iv / BufferedChannelKt.SEGMENT_SIZE;
            int i$iv = (int) ($this$sendersCounter$iv$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment$iv2.id != id$iv) {
                ChannelSegment segment$iv3 = findSegmentSend(id$iv, segment$iv2);
                if (segment$iv3 != null) {
                    segment$iv = segment$iv3;
                } else if (closed$iv) {
                    onClosedSelectOnSend(element, select);
                    return;
                }
            } else {
                segment$iv = segment$iv2;
            }
            ChannelSegment segment$iv4 = segment$iv;
            int $i$f$sendImpl2 = $i$f$sendImpl;
            switch (updateCellSend(segment$iv, i$iv, element, $this$sendersCounter$iv$iv, select, closed$iv)) {
                case 0:
                    segment$iv4.cleanPrev();
                    select.selectInRegistrationPhase(Unit.INSTANCE);
                    return;
                case 1:
                    select.selectInRegistrationPhase(Unit.INSTANCE);
                    return;
                case 2:
                    if (closed$iv) {
                        segment$iv4.onSlotCleaned();
                        onClosedSelectOnSend(element, select);
                        return;
                    } else {
                        Waiter waiter = select instanceof Waiter ? (Waiter) select : null;
                        if (waiter != null) {
                            prepareSenderForSuspension(waiter, segment$iv4, i$iv);
                        }
                        return;
                    }
                case 3:
                    throw new IllegalStateException("unexpected".toString());
                case 4:
                    if ($this$sendersCounter$iv$iv < getReceiversCounter$kotlinx_coroutines_core()) {
                        segment$iv4.cleanPrev();
                    }
                    onClosedSelectOnSend(element, select);
                    return;
                case 5:
                    segment$iv4.cleanPrev();
                default:
                    segment$iv2 = segment$iv4;
                    $i$f$sendImpl = $i$f$sendImpl2;
            }
        }
    }

    private final void onClosedSelectOnSend(E element, SelectInstance<?> select) {
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        if (function1 != null) {
            OnUndeliveredElementKt.callUndeliveredElement(function1, element, select.getContext());
        }
        select.selectInRegistrationPhase(BufferedChannelKt.getCHANNEL_CLOSED());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object processResultSelectSend(Object ignoredParam, Object selectResult) {
        if (selectResult == BufferedChannelKt.getCHANNEL_CLOSED()) {
            throw getSendException();
        }
        return this;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1<E> getOnReceive() {
        BufferedChannel$onReceive$1 bufferedChannel$onReceive$1 = BufferedChannel$onReceive$1.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onReceive$1, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"select\")] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \"param\")] kotlin.Any?, kotlin.Unit>");
        Function3 function3 = (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onReceive$1, 3);
        BufferedChannel$onReceive$2 bufferedChannel$onReceive$2 = BufferedChannel$onReceive$2.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onReceive$2, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"param\")] kotlin.Any?, @[ParameterName(name = \"clauseResult\")] kotlin.Any?, kotlin.Any?>");
        return new SelectClause1Impl(this, function3, (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onReceive$2, 3), this.onUndeliveredElementReceiveCancellationConstructor);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1<ChannelResult<E>> getOnReceiveCatching() {
        BufferedChannel$onReceiveCatching$1 bufferedChannel$onReceiveCatching$1 = BufferedChannel$onReceiveCatching$1.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onReceiveCatching$1, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"select\")] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \"param\")] kotlin.Any?, kotlin.Unit>");
        Function3 function3 = (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onReceiveCatching$1, 3);
        BufferedChannel$onReceiveCatching$2 bufferedChannel$onReceiveCatching$2 = BufferedChannel$onReceiveCatching$2.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onReceiveCatching$2, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"param\")] kotlin.Any?, @[ParameterName(name = \"clauseResult\")] kotlin.Any?, kotlin.Any?>");
        return new SelectClause1Impl(this, function3, (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onReceiveCatching$2, 3), this.onUndeliveredElementReceiveCancellationConstructor);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1<E> getOnReceiveOrNull() {
        BufferedChannel$onReceiveOrNull$1 bufferedChannel$onReceiveOrNull$1 = BufferedChannel$onReceiveOrNull$1.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onReceiveOrNull$1, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"select\")] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \"param\")] kotlin.Any?, kotlin.Unit>");
        Function3 function3 = (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onReceiveOrNull$1, 3);
        BufferedChannel$onReceiveOrNull$2 bufferedChannel$onReceiveOrNull$2 = BufferedChannel$onReceiveOrNull$2.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onReceiveOrNull$2, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"param\")] kotlin.Any?, @[ParameterName(name = \"clauseResult\")] kotlin.Any?, kotlin.Any?>");
        return new SelectClause1Impl(this, function3, (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onReceiveOrNull$2, 3), this.onUndeliveredElementReceiveCancellationConstructor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void registerSelectForReceive(SelectInstance<?> select, Object ignoredParam) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        ChannelSegment segment$iv = (ChannelSegment) getReceiveSegment$volatile$FU().get(this);
        while (!isClosedForReceive()) {
            long r$iv = getReceivers$volatile$FU().getAndIncrement(this);
            long id$iv = r$iv / BufferedChannelKt.SEGMENT_SIZE;
            int i$iv = (int) (r$iv % BufferedChannelKt.SEGMENT_SIZE);
            if (segment$iv.id != id$iv) {
                ChannelSegment findSegmentReceive = findSegmentReceive(id$iv, segment$iv);
                if (findSegmentReceive == null) {
                    continue;
                } else {
                    segment$iv = findSegmentReceive;
                }
            }
            Object updCellResult$iv = updateCellReceive(segment$iv, i$iv, r$iv, select);
            symbol = BufferedChannelKt.SUSPEND;
            if (updCellResult$iv != symbol) {
                symbol2 = BufferedChannelKt.FAILED;
                if (updCellResult$iv != symbol2) {
                    symbol3 = BufferedChannelKt.SUSPEND_NO_WAITER;
                    if (updCellResult$iv == symbol3) {
                        throw new IllegalStateException("unexpected".toString());
                    }
                    segment$iv.cleanPrev();
                    select.selectInRegistrationPhase(updCellResult$iv);
                    return;
                }
                if (r$iv < getSendersCounter$kotlinx_coroutines_core()) {
                    segment$iv.cleanPrev();
                }
            } else {
                Waiter waiter = select instanceof Waiter ? (Waiter) select : null;
                if (waiter != null) {
                    prepareReceiverForSuspension(waiter, segment$iv, i$iv);
                }
                return;
            }
        }
        onClosedSelectOnReceive(select);
    }

    private final void onClosedSelectOnReceive(SelectInstance<?> select) {
        select.selectInRegistrationPhase(BufferedChannelKt.getCHANNEL_CLOSED());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object processResultSelectReceive(Object ignoredParam, Object selectResult) {
        if (selectResult == BufferedChannelKt.getCHANNEL_CLOSED()) {
            throw getReceiveException();
        }
        return selectResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object processResultSelectReceiveOrNull(Object ignoredParam, Object selectResult) {
        if (selectResult == BufferedChannelKt.getCHANNEL_CLOSED()) {
            if (getCloseCause() == null) {
                return null;
            }
            throw getReceiveException();
        }
        return selectResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object processResultSelectReceiveCatching(Object ignoredParam, Object selectResult) {
        return ChannelResult.m2079boximpl(selectResult == BufferedChannelKt.getCHANNEL_CLOSED() ? ChannelResult.INSTANCE.m2092closedJP2dKIU(getCloseCause()) : ChannelResult.INSTANCE.m2094successJP2dKIU(selectResult));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function3 onUndeliveredElementReceiveCancellationConstructor$lambda$57$lambda$56(final BufferedChannel this$0, final SelectInstance select, Object obj, final Object element) {
        return new Function3() { // from class: kotlinx.coroutines.channels.BufferedChannel$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj2, Object obj3, Object obj4) {
                Unit onUndeliveredElementReceiveCancellationConstructor$lambda$57$lambda$56$lambda$55;
                onUndeliveredElementReceiveCancellationConstructor$lambda$57$lambda$56$lambda$55 = BufferedChannel.onUndeliveredElementReceiveCancellationConstructor$lambda$57$lambda$56$lambda$55(element, this$0, select, (Throwable) obj2, obj3, (CoroutineContext) obj4);
                return onUndeliveredElementReceiveCancellationConstructor$lambda$57$lambda$56$lambda$55;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onUndeliveredElementReceiveCancellationConstructor$lambda$57$lambda$56$lambda$55(Object $element, BufferedChannel this$0, SelectInstance $select, Throwable th, Object obj, CoroutineContext coroutineContext) {
        if ($element != BufferedChannelKt.getCHANNEL_CLOSED()) {
            OnUndeliveredElementKt.callUndeliveredElement(this$0.onUndeliveredElement, $element, $select.getContext());
        }
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public ChannelIterator<E> iterator() {
        return new BufferedChannelIterator();
    }

    /* compiled from: BufferedChannel.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\n\u001a\u00020\tH\u0096B¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\tH\u0002J,\u0010\r\u001a\u00020\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@¢\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\u00020\u00162\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0018\u001a\u00020\u0016H\u0002J\u000e\u0010\u0019\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00028\u0000¢\u0006\u0002\u0010\u001dJ\u0006\u0010\u001e\u001a\u00020\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lkotlinx/coroutines/channels/BufferedChannel$BufferedChannelIterator;", "Lkotlinx/coroutines/channels/ChannelIterator;", "Lkotlinx/coroutines/Waiter;", "<init>", "(Lkotlinx/coroutines/channels/BufferedChannel;)V", "receiveResult", "", "continuation", "Lkotlinx/coroutines/CancellableContinuationImpl;", "", "hasNext", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onClosedHasNext", "hasNextOnNoWaiterSuspend", "segment", "Lkotlinx/coroutines/channels/ChannelSegment;", FirebaseAnalytics.Param.INDEX, "", "r", "", "(Lkotlinx/coroutines/channels/ChannelSegment;IJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invokeOnCancellation", "", "Lkotlinx/coroutines/internal/Segment;", "onClosedHasNextNoWaiterSuspend", "next", "()Ljava/lang/Object;", "tryResumeHasNext", "element", "(Ljava/lang/Object;)Z", "tryResumeHasNextOnClosedChannel", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private final class BufferedChannelIterator implements ChannelIterator<E>, Waiter {
        private CancellableContinuationImpl<? super Boolean> continuation;
        private Object receiveResult;

        public BufferedChannelIterator() {
            Symbol symbol;
            symbol = BufferedChannelKt.NO_RECEIVE_RESULT;
            this.receiveResult = symbol;
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.3.0, binary compatibility with versions <= 1.2.x")
        public /* synthetic */ Object next(Continuation $completion) {
            return ChannelIterator.DefaultImpls.next(this, $completion);
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public Object hasNext(Continuation<? super Boolean> continuation) {
            Symbol symbol;
            ChannelSegment segment$iv;
            Symbol symbol2;
            Symbol symbol3;
            Symbol symbol4;
            Object obj = this.receiveResult;
            symbol = BufferedChannelKt.NO_RECEIVE_RESULT;
            boolean z = true;
            if (obj == symbol || this.receiveResult == BufferedChannelKt.getCHANNEL_CLOSED()) {
                BufferedChannel this_$iv = BufferedChannel.this;
                ChannelSegment segment$iv2 = (ChannelSegment) BufferedChannel.getReceiveSegment$volatile$FU().get(this_$iv);
                while (true) {
                    if (this_$iv.isClosedForReceive()) {
                        z = onClosedHasNext();
                        break;
                    }
                    long r$iv = BufferedChannel.getReceivers$volatile$FU().getAndIncrement(this_$iv);
                    long id$iv = r$iv / BufferedChannelKt.SEGMENT_SIZE;
                    int i$iv = (int) (r$iv % BufferedChannelKt.SEGMENT_SIZE);
                    if (segment$iv2.id != id$iv) {
                        ChannelSegment segment$iv3 = this_$iv.findSegmentReceive(id$iv, segment$iv2);
                        if (segment$iv3 == null) {
                            continue;
                        } else {
                            segment$iv = segment$iv3;
                        }
                    } else {
                        segment$iv = segment$iv2;
                    }
                    Object updCellResult$iv = this_$iv.updateCellReceive(segment$iv, i$iv, r$iv, null);
                    symbol2 = BufferedChannelKt.SUSPEND;
                    if (updCellResult$iv != symbol2) {
                        symbol3 = BufferedChannelKt.FAILED;
                        if (updCellResult$iv != symbol3) {
                            symbol4 = BufferedChannelKt.SUSPEND_NO_WAITER;
                            if (updCellResult$iv != symbol4) {
                                segment$iv.cleanPrev();
                                this.receiveResult = updCellResult$iv;
                            } else {
                                ChannelSegment segm = segment$iv;
                                return hasNextOnNoWaiterSuspend(segm, i$iv, r$iv, continuation);
                            }
                        } else {
                            if (r$iv < this_$iv.getSendersCounter$kotlinx_coroutines_core()) {
                                segment$iv.cleanPrev();
                            }
                            segment$iv2 = segment$iv;
                        }
                    } else {
                        throw new IllegalStateException("unreachable".toString());
                    }
                }
            }
            return Boxing.boxBoolean(z);
        }

        private final boolean onClosedHasNext() {
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable cause = BufferedChannel.this.getCloseCause();
            if (cause == null) {
                return false;
            }
            throw StackTraceRecoveryKt.recoverStackTrace(cause);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Object hasNextOnNoWaiterSuspend(ChannelSegment<E> channelSegment, int index, long r, Continuation<? super Boolean> continuation) {
            Symbol symbol;
            Symbol symbol2;
            ChannelSegment segment$iv$iv;
            Symbol symbol3;
            Symbol symbol4;
            Symbol symbol5;
            BufferedChannel this_$iv = BufferedChannel.this;
            int i$iv$iv = 0;
            Continuation uCont$iv = continuation;
            int i = 0;
            CancellableContinuationImpl cancellable$iv = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(uCont$iv));
            int i2 = 0;
            try {
                this.continuation = cancellable$iv;
                Object updCellResult$iv = this_$iv.updateCellReceive(channelSegment, index, r, this);
                symbol = BufferedChannelKt.SUSPEND;
                try {
                    if (updCellResult$iv != symbol) {
                        symbol2 = BufferedChannelKt.FAILED;
                        try {
                            if (updCellResult$iv != symbol2) {
                                channelSegment.cleanPrev();
                                this.receiveResult = updCellResult$iv;
                                this.continuation = null;
                                Boolean boxBoolean = Boxing.boxBoolean(true);
                                Function1<E, Unit> function1 = this_$iv.onUndeliveredElement;
                                cancellable$iv.resume((CancellableContinuationImpl) boxBoolean, (Function3<? super Throwable, ? super CancellableContinuationImpl, ? super CoroutineContext, Unit>) (function1 != null ? this_$iv.bindCancellationFun(function1, updCellResult$iv) : null));
                            } else {
                                if (r < this_$iv.getSendersCounter$kotlinx_coroutines_core()) {
                                    channelSegment.cleanPrev();
                                }
                                ChannelSegment segment$iv$iv2 = (ChannelSegment) BufferedChannel.getReceiveSegment$volatile$FU().get(this_$iv);
                                while (!this_$iv.isClosedForReceive()) {
                                    long r$iv$iv = BufferedChannel.getReceivers$volatile$FU().getAndIncrement(this_$iv);
                                    long id$iv$iv = r$iv$iv / BufferedChannelKt.SEGMENT_SIZE;
                                    int $i$f$suspendCancellableCoroutineReusable = i$iv$iv;
                                    Continuation uCont$iv2 = uCont$iv;
                                    try {
                                        int i$iv$iv2 = (int) (r$iv$iv % BufferedChannelKt.SEGMENT_SIZE);
                                        int i3 = i;
                                        if (segment$iv$iv2.id != id$iv$iv) {
                                            segment$iv$iv = this_$iv.findSegmentReceive(id$iv$iv, segment$iv$iv2);
                                            if (segment$iv$iv == null) {
                                                i$iv$iv = $i$f$suspendCancellableCoroutineReusable;
                                                uCont$iv = uCont$iv2;
                                                i = i3;
                                            }
                                        } else {
                                            segment$iv$iv = segment$iv$iv2;
                                        }
                                        int i4 = i2;
                                        Object updCellResult$iv2 = updCellResult$iv;
                                        Object updCellResult$iv$iv = this_$iv.updateCellReceive(segment$iv$iv, i$iv$iv2, r$iv$iv, this);
                                        symbol3 = BufferedChannelKt.SUSPEND;
                                        if (updCellResult$iv$iv != symbol3) {
                                            symbol4 = BufferedChannelKt.FAILED;
                                            if (updCellResult$iv$iv == symbol4) {
                                                if (r$iv$iv < this_$iv.getSendersCounter$kotlinx_coroutines_core()) {
                                                    segment$iv$iv.cleanPrev();
                                                }
                                                segment$iv$iv2 = segment$iv$iv;
                                                i2 = i4;
                                                i$iv$iv = $i$f$suspendCancellableCoroutineReusable;
                                                uCont$iv = uCont$iv2;
                                                i = i3;
                                                updCellResult$iv = updCellResult$iv2;
                                            } else {
                                                symbol5 = BufferedChannelKt.SUSPEND_NO_WAITER;
                                                if (updCellResult$iv$iv == symbol5) {
                                                    throw new IllegalStateException("unexpected".toString());
                                                }
                                                segment$iv$iv.cleanPrev();
                                                this.receiveResult = updCellResult$iv$iv;
                                                this.continuation = null;
                                                Boolean boxBoolean2 = Boxing.boxBoolean(true);
                                                Function1<E, Unit> function12 = this_$iv.onUndeliveredElement;
                                                cancellable$iv.resume((CancellableContinuationImpl) boxBoolean2, (Function3<? super Throwable, ? super CancellableContinuationImpl, ? super CoroutineContext, Unit>) (function12 != null ? this_$iv.bindCancellationFun(function12, updCellResult$iv$iv) : null));
                                            }
                                        } else {
                                            BufferedChannelIterator bufferedChannelIterator = this instanceof Waiter ? this : null;
                                            if (bufferedChannelIterator != null) {
                                                this_$iv.prepareReceiverForSuspension(bufferedChannelIterator, segment$iv$iv, i$iv$iv2);
                                            }
                                        }
                                    } catch (Throwable th) {
                                        e$iv = th;
                                        cancellable$iv.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                                        throw e$iv;
                                    }
                                }
                                onClosedHasNextNoWaiterSuspend();
                            }
                        } catch (Throwable th2) {
                            e$iv = th2;
                        }
                    } else {
                        try {
                            this_$iv.prepareReceiverForSuspension(this, channelSegment, index);
                        } catch (Throwable th3) {
                            e$iv = th3;
                            cancellable$iv.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                            throw e$iv;
                        }
                    }
                    Object result = cancellable$iv.getResult();
                    if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        DebugProbesKt.probeCoroutineSuspended(continuation);
                    }
                    return result;
                } catch (Throwable th4) {
                    e$iv = th4;
                }
            } catch (Throwable th5) {
                e$iv = th5;
            }
        }

        @Override // kotlinx.coroutines.Waiter
        public void invokeOnCancellation(Segment<?> segment, int index) {
            CancellableContinuationImpl<? super Boolean> cancellableContinuationImpl = this.continuation;
            if (cancellableContinuationImpl != null) {
                cancellableContinuationImpl.invokeOnCancellation(segment, index);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void onClosedHasNextNoWaiterSuspend() {
            Throwable th;
            CancellableContinuationImpl cont = this.continuation;
            Intrinsics.checkNotNull(cont);
            this.continuation = null;
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable cause = BufferedChannel.this.getCloseCause();
            if (cause == null) {
                Result.Companion companion = Result.INSTANCE;
                cont.resumeWith(Result.m569constructorimpl(false));
                return;
            }
            CancellableContinuationImpl cancellableContinuationImpl = cont;
            if (DebugKt.getRECOVER_STACK_TRACES() && (cont instanceof CoroutineStackFrame)) {
                th = StackTraceRecoveryKt.recoverFromStackFrame(cause, cont);
            } else {
                th = cause;
            }
            Result.Companion companion2 = Result.INSTANCE;
            cancellableContinuationImpl.resumeWith(Result.m569constructorimpl(ResultKt.createFailure(th)));
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public E next() {
            Symbol symbol;
            Symbol symbol2;
            E e = (E) this.receiveResult;
            symbol = BufferedChannelKt.NO_RECEIVE_RESULT;
            if (!(e != symbol)) {
                throw new IllegalStateException("`hasNext()` has not been invoked".toString());
            }
            symbol2 = BufferedChannelKt.NO_RECEIVE_RESULT;
            this.receiveResult = symbol2;
            if (e == BufferedChannelKt.getCHANNEL_CLOSED()) {
                throw StackTraceRecoveryKt.recoverStackTrace(BufferedChannel.this.getReceiveException());
            }
            return e;
        }

        public final boolean tryResumeHasNext(E element) {
            boolean tryResume0;
            CancellableContinuationImpl cont = this.continuation;
            Intrinsics.checkNotNull(cont);
            Function3 function3 = null;
            this.continuation = null;
            this.receiveResult = element;
            CancellableContinuationImpl cancellableContinuationImpl = cont;
            Function1<E, Unit> function1 = BufferedChannel.this.onUndeliveredElement;
            if (function1 != null) {
                function3 = BufferedChannel.this.bindCancellationFun(function1, element);
            }
            tryResume0 = BufferedChannelKt.tryResume0(cancellableContinuationImpl, true, function3);
            return tryResume0;
        }

        public final void tryResumeHasNextOnClosedChannel() {
            Throwable th;
            CancellableContinuationImpl cont = this.continuation;
            Intrinsics.checkNotNull(cont);
            this.continuation = null;
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable cause = BufferedChannel.this.getCloseCause();
            if (cause == null) {
                Result.Companion companion = Result.INSTANCE;
                cont.resumeWith(Result.m569constructorimpl(false));
                return;
            }
            CancellableContinuationImpl cancellableContinuationImpl = cont;
            if (DebugKt.getRECOVER_STACK_TRACES() && (cont instanceof CoroutineStackFrame)) {
                th = StackTraceRecoveryKt.recoverFromStackFrame(cause, cont);
            } else {
                th = cause;
            }
            Result.Companion companion2 = Result.INSTANCE;
            cancellableContinuationImpl.resumeWith(Result.m569constructorimpl(ResultKt.createFailure(th)));
        }
    }

    protected final Throwable getCloseCause() {
        return (Throwable) _closeCause$volatile$FU.get(this);
    }

    protected final Throwable getSendException() {
        Throwable closeCause = getCloseCause();
        return closeCause == null ? new ClosedSendChannelException(ChannelsKt.DEFAULT_CLOSE_MESSAGE) : closeCause;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Throwable getReceiveException() {
        Throwable closeCause = getCloseCause();
        return closeCause == null ? new ClosedReceiveChannelException(ChannelsKt.DEFAULT_CLOSE_MESSAGE) : closeCause;
    }

    protected void onClosedIdempotent() {
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean close(Throwable cause) {
        return closeOrCancelImpl(cause, false);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final boolean cancel(Throwable cause) {
        return cancelImpl$kotlinx_coroutines_core(cause);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel() {
        cancelImpl$kotlinx_coroutines_core(null);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel(CancellationException cause) {
        cancelImpl$kotlinx_coroutines_core(cause);
    }

    public boolean cancelImpl$kotlinx_coroutines_core(Throwable cause) {
        return closeOrCancelImpl(cause == null ? new CancellationException("Channel was cancelled") : cause, true);
    }

    protected boolean closeOrCancelImpl(Throwable cause, boolean cancel) {
        Symbol symbol;
        if (cancel) {
            markCancellationStarted();
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _closeCause$volatile$FU;
        symbol = BufferedChannelKt.NO_CLOSE_CAUSE;
        boolean closedByThisOperation = AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, symbol, cause);
        if (cancel) {
            markCancelled();
        } else {
            markClosed();
        }
        completeCloseOrCancel();
        onClosedIdempotent();
        if (closedByThisOperation) {
            invokeCloseHandler();
        }
        return closedByThisOperation;
    }

    private final void invokeCloseHandler() {
        Object it;
        AtomicReferenceFieldUpdater handler$atomicfu$iv = closeHandler$volatile$FU;
        do {
            it = handler$atomicfu$iv.get(this);
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(handler$atomicfu$iv, this, it, it == null ? BufferedChannelKt.CLOSE_HANDLER_CLOSED : BufferedChannelKt.CLOSE_HANDLER_INVOKED));
        if (it != null) {
            ((Function1) it).invoke(getCloseCause());
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(Function1<? super Throwable, Unit> handler) {
        Symbol symbol;
        Symbol symbol2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Symbol symbol3;
        Symbol symbol4;
        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(closeHandler$volatile$FU, this, null, handler)) {
            AtomicReferenceFieldUpdater handler$atomicfu$iv = closeHandler$volatile$FU;
            do {
                Object cur = handler$atomicfu$iv.get(this);
                symbol = BufferedChannelKt.CLOSE_HANDLER_CLOSED;
                if (cur != symbol) {
                    symbol2 = BufferedChannelKt.CLOSE_HANDLER_INVOKED;
                    if (cur != symbol2) {
                        throw new IllegalStateException(("Another handler is already registered: " + cur).toString());
                    }
                    throw new IllegalStateException("Another handler was already registered and successfully invoked".toString());
                }
                atomicReferenceFieldUpdater = closeHandler$volatile$FU;
                symbol3 = BufferedChannelKt.CLOSE_HANDLER_CLOSED;
                symbol4 = BufferedChannelKt.CLOSE_HANDLER_INVOKED;
            } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, symbol3, symbol4));
            handler.invoke(getCloseCause());
        }
    }

    private final void markClosed() {
        long cur;
        long constructSendersAndCloseStatus;
        AtomicLongFieldUpdater handler$atomicfu$iv = sendersAndCloseStatus$volatile$FU;
        do {
            cur = handler$atomicfu$iv.get(this);
            int $i$f$getSendersCloseStatus = (int) (cur >> 60);
            switch ($i$f$getSendersCloseStatus) {
                case 0:
                    constructSendersAndCloseStatus = BufferedChannelKt.constructSendersAndCloseStatus(1152921504606846975L & cur, 2);
                    break;
                case 1:
                    constructSendersAndCloseStatus = BufferedChannelKt.constructSendersAndCloseStatus(1152921504606846975L & cur, 3);
                    break;
                default:
                    return;
            }
        } while (!handler$atomicfu$iv.compareAndSet(this, cur, constructSendersAndCloseStatus));
    }

    private final void markCancelled() {
        long cur;
        long constructSendersAndCloseStatus;
        AtomicLongFieldUpdater handler$atomicfu$iv = sendersAndCloseStatus$volatile$FU;
        do {
            cur = handler$atomicfu$iv.get(this);
            long $this$sendersCounter$iv = cur & 1152921504606846975L;
            constructSendersAndCloseStatus = BufferedChannelKt.constructSendersAndCloseStatus($this$sendersCounter$iv, 3);
        } while (!handler$atomicfu$iv.compareAndSet(this, cur, constructSendersAndCloseStatus));
    }

    private final void markCancellationStarted() {
        long cur;
        long constructSendersAndCloseStatus;
        AtomicLongFieldUpdater handler$atomicfu$iv = sendersAndCloseStatus$volatile$FU;
        do {
            cur = handler$atomicfu$iv.get(this);
            int $i$f$getSendersCloseStatus = (int) (cur >> 60);
            if ($i$f$getSendersCloseStatus == 0) {
                long $this$sendersCounter$iv = cur & 1152921504606846975L;
                constructSendersAndCloseStatus = BufferedChannelKt.constructSendersAndCloseStatus($this$sendersCounter$iv, 1);
            } else {
                return;
            }
        } while (!handler$atomicfu$iv.compareAndSet(this, cur, constructSendersAndCloseStatus));
    }

    private final void completeCloseOrCancel() {
        isClosedForSend();
    }

    protected boolean isConflatedDropOldest() {
        return false;
    }

    private final ChannelSegment<E> completeClose(long sendersCur) {
        ChannelSegment lastSegment = closeLinkedList();
        if (isConflatedDropOldest()) {
            long lastBufferedCellGlobalIndex = markAllEmptyCellsAsClosed(lastSegment);
            if (lastBufferedCellGlobalIndex != -1) {
                dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(lastBufferedCellGlobalIndex);
            }
        }
        cancelSuspendedReceiveRequests(lastSegment, sendersCur);
        return lastSegment;
    }

    private final void completeCancel(long sendersCur) {
        ChannelSegment lastSegment = completeClose(sendersCur);
        removeUnprocessedElements(lastSegment);
    }

    private final ChannelSegment<E> closeLinkedList() {
        Object lastSegment = bufferEndSegment$volatile$FU.get(this);
        ChannelSegment it = (ChannelSegment) sendSegment$volatile$FU.get(this);
        if (it.id > ((ChannelSegment) lastSegment).id) {
            lastSegment = it;
        }
        ChannelSegment it2 = (ChannelSegment) receiveSegment$volatile$FU.get(this);
        if (it2.id > ((ChannelSegment) lastSegment).id) {
            lastSegment = it2;
        }
        return (ChannelSegment) ConcurrentLinkedListKt.close((ConcurrentLinkedListNode) lastSegment);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0042, code lost:
    
        r1 = (kotlinx.coroutines.channels.ChannelSegment) r0.getPrev();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0048, code lost:
    
        if (r1 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x004a, code lost:
    
        return -1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final long markAllEmptyCellsAsClosed(ChannelSegment<E> lastSegment) {
        ChannelSegment channelSegment;
        Symbol symbol;
        ChannelSegment segment = lastSegment;
        while (true) {
            int index = BufferedChannelKt.SEGMENT_SIZE;
            while (true) {
                index--;
                if (-1 >= index) {
                    break;
                }
                long globalIndex = (segment.id * BufferedChannelKt.SEGMENT_SIZE) + index;
                if (globalIndex < getReceiversCounter$kotlinx_coroutines_core()) {
                    return -1L;
                }
                while (true) {
                    Object state = segment.getState$kotlinx_coroutines_core(index);
                    if (state != null) {
                        symbol = BufferedChannelKt.IN_BUFFER;
                        if (state != symbol) {
                            if (state == BufferedChannelKt.BUFFERED) {
                                return globalIndex;
                            }
                        }
                    }
                    if (segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                        segment.onSlotCleaned();
                        break;
                    }
                }
            }
            segment = channelSegment;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:85:0x00ba, code lost:
    
        r5 = (kotlinx.coroutines.channels.ChannelSegment) r4.getPrev();
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x00c0, code lost:
    
        if (r5 != null) goto L65;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void removeUnprocessedElements(ChannelSegment<E> lastSegment) {
        ChannelSegment channelSegment;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Function1 onUndeliveredElement = this.onUndeliveredElement;
        UndeliveredElementException undeliveredElementException = null;
        Object suspendedSenders = InlineList.m2121constructorimpl$default(null, 1, null);
        ChannelSegment segment = lastSegment;
        loop0: while (true) {
            int index = BufferedChannelKt.SEGMENT_SIZE - 1;
            while (true) {
                if (-1 >= index) {
                    break;
                }
                long globalIndex = (segment.id * BufferedChannelKt.SEGMENT_SIZE) + index;
                while (true) {
                    Object state = segment.getState$kotlinx_coroutines_core(index);
                    symbol = BufferedChannelKt.DONE_RCV;
                    if (state == symbol) {
                        break loop0;
                    }
                    if (state != BufferedChannelKt.BUFFERED) {
                        symbol2 = BufferedChannelKt.IN_BUFFER;
                        if (state == symbol2 || state == null) {
                            if (segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                                segment.onSlotCleaned();
                                break;
                            }
                        } else if (!(state instanceof Waiter) && !(state instanceof WaiterEB)) {
                            symbol3 = BufferedChannelKt.RESUMING_BY_EB;
                            if (state == symbol3) {
                                break loop0;
                            }
                            symbol4 = BufferedChannelKt.RESUMING_BY_RCV;
                            if (state == symbol4) {
                                break loop0;
                            }
                            symbol5 = BufferedChannelKt.RESUMING_BY_EB;
                            if (state != symbol5) {
                                break;
                            }
                        } else {
                            if (globalIndex < getReceiversCounter$kotlinx_coroutines_core()) {
                                break loop0;
                            }
                            Waiter sender = state instanceof WaiterEB ? ((WaiterEB) state).waiter : (Waiter) state;
                            if (segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                                if (onUndeliveredElement != null) {
                                    Object element = segment.getElement$kotlinx_coroutines_core(index);
                                    undeliveredElementException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(onUndeliveredElement, element, undeliveredElementException);
                                }
                                suspendedSenders = InlineList.m2126plusFjFbRPM(suspendedSenders, sender);
                                segment.cleanElement$kotlinx_coroutines_core(index);
                                segment.onSlotCleaned();
                            }
                        }
                    } else {
                        if (globalIndex < getReceiversCounter$kotlinx_coroutines_core()) {
                            break loop0;
                        }
                        if (segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                            if (onUndeliveredElement != null) {
                                Object element2 = segment.getElement$kotlinx_coroutines_core(index);
                                undeliveredElementException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(onUndeliveredElement, element2, undeliveredElementException);
                            }
                            segment.cleanElement$kotlinx_coroutines_core(index);
                            segment.onSlotCleaned();
                        }
                    }
                }
                index--;
            }
            segment = channelSegment;
        }
        if (suspendedSenders != null) {
            if (!(suspendedSenders instanceof ArrayList)) {
                Waiter it = (Waiter) suspendedSenders;
                resumeSenderOnCancelledChannel(it);
            } else {
                Intrinsics.checkNotNull(suspendedSenders, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>");
                ArrayList list$iv = (ArrayList) suspendedSenders;
                for (int i$iv = list$iv.size() - 1; -1 < i$iv; i$iv--) {
                    Waiter it2 = (Waiter) list$iv.get(i$iv);
                    resumeSenderOnCancelledChannel(it2);
                }
            }
        }
        if (undeliveredElementException != null) {
            UndeliveredElementException it3 = undeliveredElementException;
            throw it3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void cancelSuspendedReceiveRequests(ChannelSegment<E> lastSegment, long sendersCounter) {
        Symbol symbol;
        Object suspendedReceivers = InlineList.m2121constructorimpl$default(null, 1, null);
        loop0: for (ChannelSegment segment = lastSegment; segment != null; segment = (ChannelSegment) segment.getPrev()) {
            for (int index = BufferedChannelKt.SEGMENT_SIZE - 1; -1 < index; index--) {
                if ((segment.id * BufferedChannelKt.SEGMENT_SIZE) + index < sendersCounter) {
                    break loop0;
                }
                while (true) {
                    Object state = segment.getState$kotlinx_coroutines_core(index);
                    if (state != null) {
                        symbol = BufferedChannelKt.IN_BUFFER;
                        if (state != symbol) {
                            if (state instanceof WaiterEB) {
                                if (segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                                    suspendedReceivers = InlineList.m2126plusFjFbRPM(suspendedReceivers, ((WaiterEB) state).waiter);
                                    segment.onCancelledRequest(index, true);
                                    break;
                                }
                            } else {
                                if (!(state instanceof Waiter)) {
                                    break;
                                }
                                if (segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                                    suspendedReceivers = InlineList.m2126plusFjFbRPM(suspendedReceivers, state);
                                    segment.onCancelledRequest(index, true);
                                    break;
                                }
                            }
                        }
                    }
                    if (segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                        segment.onSlotCleaned();
                        break;
                    }
                }
            }
        }
        if (suspendedReceivers == null) {
            return;
        }
        if (!(suspendedReceivers instanceof ArrayList)) {
            Waiter it = (Waiter) suspendedReceivers;
            resumeReceiverOnClosedChannel(it);
            return;
        }
        Intrinsics.checkNotNull(suspendedReceivers, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>");
        ArrayList list$iv = (ArrayList) suspendedReceivers;
        for (int i$iv = list$iv.size() - 1; -1 < i$iv; i$iv--) {
            Waiter it2 = (Waiter) list$iv.get(i$iv);
            resumeReceiverOnClosedChannel(it2);
        }
    }

    private final void resumeReceiverOnClosedChannel(Waiter $this$resumeReceiverOnClosedChannel) {
        resumeWaiterOnClosedChannel($this$resumeReceiverOnClosedChannel, true);
    }

    private final void resumeSenderOnCancelledChannel(Waiter $this$resumeSenderOnCancelledChannel) {
        resumeWaiterOnClosedChannel($this$resumeSenderOnCancelledChannel, false);
    }

    private final void resumeWaiterOnClosedChannel(Waiter $this$resumeWaiterOnClosedChannel, boolean receiver) {
        if (!($this$resumeWaiterOnClosedChannel instanceof SendBroadcast)) {
            if (!($this$resumeWaiterOnClosedChannel instanceof CancellableContinuation)) {
                if (!($this$resumeWaiterOnClosedChannel instanceof ReceiveCatching)) {
                    if (!($this$resumeWaiterOnClosedChannel instanceof BufferedChannelIterator)) {
                        if (!($this$resumeWaiterOnClosedChannel instanceof SelectInstance)) {
                            throw new IllegalStateException(("Unexpected waiter: " + $this$resumeWaiterOnClosedChannel).toString());
                        }
                        ((SelectInstance) $this$resumeWaiterOnClosedChannel).trySelect(this, BufferedChannelKt.getCHANNEL_CLOSED());
                        return;
                    }
                    ((BufferedChannelIterator) $this$resumeWaiterOnClosedChannel).tryResumeHasNextOnClosedChannel();
                    return;
                }
                CancellableContinuationImpl<ChannelResult<? extends E>> cancellableContinuationImpl = ((ReceiveCatching) $this$resumeWaiterOnClosedChannel).cont;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuationImpl.resumeWith(Result.m569constructorimpl(ChannelResult.m2079boximpl(ChannelResult.INSTANCE.m2092closedJP2dKIU(getCloseCause()))));
                return;
            }
            Continuation continuation = (Continuation) $this$resumeWaiterOnClosedChannel;
            Result.Companion companion2 = Result.INSTANCE;
            continuation.resumeWith(Result.m569constructorimpl(ResultKt.createFailure(receiver ? getReceiveException() : getSendException())));
            return;
        }
        CancellableContinuation<Boolean> cont = ((SendBroadcast) $this$resumeWaiterOnClosedChannel).getCont();
        Result.Companion companion3 = Result.INSTANCE;
        cont.resumeWith(Result.m569constructorimpl(false));
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return isClosedForSend0(sendersAndCloseStatus$volatile$FU.get(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isClosedForSend0(long $this$isClosedForSend0) {
        return isClosed($this$isClosedForSend0, false);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        return isClosedForReceive0(sendersAndCloseStatus$volatile$FU.get(this));
    }

    private final boolean isClosedForReceive0(long $this$isClosedForReceive0) {
        return isClosed($this$isClosedForReceive0, true);
    }

    private final boolean isClosed(long sendersAndCloseStatusCur, boolean isClosedForReceive) {
        long $this$sendersCloseStatus$iv = (int) (sendersAndCloseStatusCur >> 60);
        switch ($this$sendersCloseStatus$iv) {
            case 0:
                return false;
            case 1:
                return false;
            case 2:
                completeClose(sendersAndCloseStatusCur & 1152921504606846975L);
                return (isClosedForReceive && hasElements$kotlinx_coroutines_core()) ? false : true;
            case 3:
                completeCancel(sendersAndCloseStatusCur & 1152921504606846975L);
                return true;
            default:
                throw new IllegalStateException(("unexpected close status: " + ((int) (sendersAndCloseStatusCur >> 60))).toString());
        }
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        if (isClosedForReceive() || hasElements$kotlinx_coroutines_core()) {
            return false;
        }
        return !isClosedForReceive();
    }

    public final boolean hasElements$kotlinx_coroutines_core() {
        while (true) {
            ChannelSegment segment = (ChannelSegment) receiveSegment$volatile$FU.get(this);
            long r = getReceiversCounter$kotlinx_coroutines_core();
            long s = getSendersCounter$kotlinx_coroutines_core();
            if (s <= r) {
                return false;
            }
            long id = r / BufferedChannelKt.SEGMENT_SIZE;
            if (segment.id != id) {
                ChannelSegment findSegmentReceive = findSegmentReceive(id, segment);
                if (findSegmentReceive != null) {
                    segment = findSegmentReceive;
                } else if (((ChannelSegment) receiveSegment$volatile$FU.get(this)).id < id) {
                    return false;
                }
            }
            segment.cleanPrev();
            int i = (int) (r % BufferedChannelKt.SEGMENT_SIZE);
            if (isCellNonEmpty(segment, i, r)) {
                return true;
            }
            receivers$volatile$FU.compareAndSet(this, r, r + 1);
        }
    }

    private final boolean isCellNonEmpty(ChannelSegment<E> segment, int index, long globalIndex) {
        Object state;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Symbol symbol7;
        do {
            state = segment.getState$kotlinx_coroutines_core(index);
            if (state != null) {
                symbol2 = BufferedChannelKt.IN_BUFFER;
                if (state != symbol2) {
                    if (state == BufferedChannelKt.BUFFERED) {
                        return true;
                    }
                    symbol3 = BufferedChannelKt.INTERRUPTED_SEND;
                    if (state != symbol3 && state != BufferedChannelKt.getCHANNEL_CLOSED()) {
                        symbol4 = BufferedChannelKt.DONE_RCV;
                        if (state != symbol4) {
                            symbol5 = BufferedChannelKt.POISONED;
                            if (state != symbol5) {
                                symbol6 = BufferedChannelKt.RESUMING_BY_EB;
                                if (state == symbol6) {
                                    return true;
                                }
                                symbol7 = BufferedChannelKt.RESUMING_BY_RCV;
                                return state != symbol7 && globalIndex == getReceiversCounter$kotlinx_coroutines_core();
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
            }
            symbol = BufferedChannelKt.POISONED;
        } while (!segment.casState$kotlinx_coroutines_core(index, state, symbol));
        expandBuffer();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ChannelSegment<E> findSegmentSend(long id, ChannelSegment<E> startFrom) {
        Object s$iv;
        Segment to$iv$iv;
        AtomicReferenceFieldUpdater handler$atomicfu$iv = sendSegment$volatile$FU;
        Function2 createNewSegment$iv = (Function2) BufferedChannelKt.createSegmentFunction();
        do {
            s$iv = ConcurrentLinkedListKt.findSegmentInternal(startFrom, id, createNewSegment$iv);
            if (SegmentOrClosed.m2138isClosedimpl(s$iv)) {
                break;
            }
            Segment to$iv$iv2 = SegmentOrClosed.m2136getSegmentimpl(s$iv);
            while (true) {
                Segment cur$iv$iv = (Segment) handler$atomicfu$iv.get(this);
                if (cur$iv$iv.id >= to$iv$iv2.id) {
                    to$iv$iv = 1;
                    break;
                }
                if (!to$iv$iv2.tryIncPointers$kotlinx_coroutines_core()) {
                    to$iv$iv = null;
                    break;
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(handler$atomicfu$iv, this, cur$iv$iv, to$iv$iv2)) {
                    if (cur$iv$iv.decPointers$kotlinx_coroutines_core()) {
                        cur$iv$iv.remove();
                    }
                    to$iv$iv = 1;
                } else if (to$iv$iv2.decPointers$kotlinx_coroutines_core()) {
                    to$iv$iv2.remove();
                }
            }
        } while (to$iv$iv == null);
        if (SegmentOrClosed.m2138isClosedimpl(s$iv)) {
            completeCloseOrCancel();
            if (startFrom.id * BufferedChannelKt.SEGMENT_SIZE >= getReceiversCounter$kotlinx_coroutines_core()) {
                return null;
            }
            startFrom.cleanPrev();
            return null;
        }
        ChannelSegment segment = (ChannelSegment) SegmentOrClosed.m2136getSegmentimpl(s$iv);
        if (segment.id > id) {
            updateSendersCounterIfLower(segment.id * BufferedChannelKt.SEGMENT_SIZE);
            if (segment.id * BufferedChannelKt.SEGMENT_SIZE >= getReceiversCounter$kotlinx_coroutines_core()) {
                return null;
            }
            segment.cleanPrev();
            return null;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(segment.id == id)) {
                throw new AssertionError();
            }
        }
        return segment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ChannelSegment<E> findSegmentReceive(long id, ChannelSegment<E> startFrom) {
        Object s$iv;
        Segment to$iv$iv;
        AtomicReferenceFieldUpdater handler$atomicfu$iv = receiveSegment$volatile$FU;
        Function2 createNewSegment$iv = (Function2) BufferedChannelKt.createSegmentFunction();
        do {
            s$iv = ConcurrentLinkedListKt.findSegmentInternal(startFrom, id, createNewSegment$iv);
            if (SegmentOrClosed.m2138isClosedimpl(s$iv)) {
                break;
            }
            Segment to$iv$iv2 = SegmentOrClosed.m2136getSegmentimpl(s$iv);
            while (true) {
                Segment cur$iv$iv = (Segment) handler$atomicfu$iv.get(this);
                if (cur$iv$iv.id >= to$iv$iv2.id) {
                    to$iv$iv = 1;
                    break;
                }
                if (!to$iv$iv2.tryIncPointers$kotlinx_coroutines_core()) {
                    to$iv$iv = null;
                    break;
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(handler$atomicfu$iv, this, cur$iv$iv, to$iv$iv2)) {
                    if (cur$iv$iv.decPointers$kotlinx_coroutines_core()) {
                        cur$iv$iv.remove();
                    }
                    to$iv$iv = 1;
                } else if (to$iv$iv2.decPointers$kotlinx_coroutines_core()) {
                    to$iv$iv2.remove();
                }
            }
        } while (to$iv$iv == null);
        if (SegmentOrClosed.m2138isClosedimpl(s$iv)) {
            completeCloseOrCancel();
            if (startFrom.id * BufferedChannelKt.SEGMENT_SIZE < getSendersCounter$kotlinx_coroutines_core()) {
                startFrom.cleanPrev();
            }
            return null;
        }
        ChannelSegment segment = (ChannelSegment) SegmentOrClosed.m2136getSegmentimpl(s$iv);
        if (!isRendezvousOrUnlimited() && id <= getBufferEndCounter() / BufferedChannelKt.SEGMENT_SIZE) {
            AtomicReferenceFieldUpdater handler$atomicfu$iv2 = bufferEndSegment$volatile$FU;
            while (true) {
                Segment cur$iv = (Segment) handler$atomicfu$iv2.get(this);
                if (cur$iv.id >= segment.id || !segment.tryIncPointers$kotlinx_coroutines_core()) {
                    break;
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(handler$atomicfu$iv2, this, cur$iv, segment)) {
                    if (cur$iv.decPointers$kotlinx_coroutines_core()) {
                        cur$iv.remove();
                    }
                } else if (segment.decPointers$kotlinx_coroutines_core()) {
                    segment.remove();
                }
            }
        }
        if (segment.id > id) {
            updateReceiversCounterIfLower(segment.id * BufferedChannelKt.SEGMENT_SIZE);
            if (segment.id * BufferedChannelKt.SEGMENT_SIZE < getSendersCounter$kotlinx_coroutines_core()) {
                segment.cleanPrev();
            }
            return null;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(segment.id == id)) {
                throw new AssertionError();
            }
        }
        return segment;
    }

    private final ChannelSegment<E> findSegmentBufferEnd(long id, ChannelSegment<E> startFrom, long currentBufferEndCounter) {
        Object s$iv;
        Segment to$iv$iv;
        AtomicReferenceFieldUpdater handler$atomicfu$iv = bufferEndSegment$volatile$FU;
        Function2 createNewSegment$iv = (Function2) BufferedChannelKt.createSegmentFunction();
        do {
            s$iv = ConcurrentLinkedListKt.findSegmentInternal(startFrom, id, createNewSegment$iv);
            if (SegmentOrClosed.m2138isClosedimpl(s$iv)) {
                break;
            }
            Segment to$iv$iv2 = SegmentOrClosed.m2136getSegmentimpl(s$iv);
            while (true) {
                Segment cur$iv$iv = (Segment) handler$atomicfu$iv.get(this);
                if (cur$iv$iv.id >= to$iv$iv2.id) {
                    to$iv$iv = 1;
                    break;
                }
                if (!to$iv$iv2.tryIncPointers$kotlinx_coroutines_core()) {
                    to$iv$iv = null;
                    break;
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(handler$atomicfu$iv, this, cur$iv$iv, to$iv$iv2)) {
                    if (cur$iv$iv.decPointers$kotlinx_coroutines_core()) {
                        cur$iv$iv.remove();
                    }
                    to$iv$iv = 1;
                } else if (to$iv$iv2.decPointers$kotlinx_coroutines_core()) {
                    to$iv$iv2.remove();
                }
            }
        } while (to$iv$iv == null);
        if (SegmentOrClosed.m2138isClosedimpl(s$iv)) {
            completeCloseOrCancel();
            moveSegmentBufferEndToSpecifiedOrLast(id, startFrom);
            incCompletedExpandBufferAttempts$default(this, 0L, 1, null);
            return null;
        }
        ChannelSegment segment = (ChannelSegment) SegmentOrClosed.m2136getSegmentimpl(s$iv);
        if (segment.id > id) {
            if (bufferEnd$volatile$FU.compareAndSet(this, currentBufferEndCounter + 1, segment.id * BufferedChannelKt.SEGMENT_SIZE)) {
                incCompletedExpandBufferAttempts((segment.id * BufferedChannelKt.SEGMENT_SIZE) - currentBufferEndCounter);
                return null;
            }
            incCompletedExpandBufferAttempts$default(this, 0L, 1, null);
            return null;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(segment.id == id)) {
                throw new AssertionError();
            }
        }
        return segment;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void moveSegmentBufferEndToSpecifiedOrLast(long id, ChannelSegment<E> startFrom) {
        boolean z;
        ChannelSegment channelSegment;
        ChannelSegment channelSegment2;
        ChannelSegment segment = startFrom;
        while (segment.id < id && (channelSegment2 = (ChannelSegment) segment.getNext()) != null) {
            segment = channelSegment2;
        }
        while (true) {
            if (!segment.isRemoved() || (channelSegment = (ChannelSegment) segment.getNext()) == null) {
                AtomicReferenceFieldUpdater handler$atomicfu$iv = bufferEndSegment$volatile$FU;
                while (true) {
                    Segment cur$iv = (Segment) handler$atomicfu$iv.get(this);
                    z = true;
                    if (cur$iv.id >= segment.id) {
                        break;
                    }
                    if (!segment.tryIncPointers$kotlinx_coroutines_core()) {
                        z = false;
                        break;
                    } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(handler$atomicfu$iv, this, cur$iv, segment)) {
                        if (cur$iv.decPointers$kotlinx_coroutines_core()) {
                            cur$iv.remove();
                        }
                    } else if (segment.decPointers$kotlinx_coroutines_core()) {
                        segment.remove();
                    }
                }
                if (z) {
                    return;
                }
            } else {
                segment = channelSegment;
            }
        }
    }

    private final void updateSendersCounterIfLower(long value) {
        long cur;
        long update;
        AtomicLongFieldUpdater handler$atomicfu$iv = sendersAndCloseStatus$volatile$FU;
        do {
            cur = handler$atomicfu$iv.get(this);
            long $this$sendersCounter$iv = cur & 1152921504606846975L;
            if ($this$sendersCounter$iv >= value) {
                return;
            } else {
                update = BufferedChannelKt.constructSendersAndCloseStatus($this$sendersCounter$iv, (int) (cur >> 60));
            }
        } while (!sendersAndCloseStatus$volatile$FU.compareAndSet(this, cur, update));
    }

    private final void updateReceiversCounterIfLower(long value) {
        long cur;
        AtomicLongFieldUpdater handler$atomicfu$iv = receivers$volatile$FU;
        do {
            cur = handler$atomicfu$iv.get(this);
            if (cur >= value) {
                return;
            }
        } while (!receivers$volatile$FU.compareAndSet(this, cur, value));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String toString() {
        Symbol symbol;
        String str;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Symbol symbol7;
        ChannelSegment channelSegment;
        StringBuilder sb = new StringBuilder();
        long $this$sendersCloseStatus$iv = sendersAndCloseStatus$volatile$FU.get(this);
        switch ((int) ($this$sendersCloseStatus$iv >> 60)) {
            case 2:
                sb.append("closed,");
                break;
            case 3:
                sb.append("cancelled,");
                break;
        }
        sb.append("capacity=" + this.capacity + ',');
        sb.append("data=[");
        Iterable $this$filter$iv = CollectionsKt.listOf((Object[]) new ChannelSegment[]{receiveSegment$volatile$FU.get(this), sendSegment$volatile$FU.get(this), bufferEndSegment$volatile$FU.get(this)});
        ArrayList arrayList = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            ChannelSegment it = (ChannelSegment) element$iv$iv;
            channelSegment = BufferedChannelKt.NULL_SEGMENT;
            ChannelSegment it2 = it != channelSegment ? 1 : null;
            if (it2 != null) {
                arrayList.add(element$iv$iv);
            }
        }
        ArrayList $this$minBy$iv = arrayList;
        Iterator iterator$iv = $this$minBy$iv.iterator();
        if (!iterator$iv.hasNext()) {
            throw new NoSuchElementException();
        }
        Object minElem$iv = iterator$iv.next();
        if (iterator$iv.hasNext()) {
            ChannelSegment it3 = (ChannelSegment) minElem$iv;
            long minValue$iv = it3.id;
            do {
                Object e$iv = iterator$iv.next();
                ChannelSegment it4 = (ChannelSegment) e$iv;
                long v$iv = it4.id;
                if (minValue$iv > v$iv) {
                    minElem$iv = e$iv;
                    minValue$iv = v$iv;
                }
            } while (iterator$iv.hasNext());
        }
        ChannelSegment firstSegment = (ChannelSegment) minElem$iv;
        long r = getReceiversCounter$kotlinx_coroutines_core();
        long s = getSendersCounter$kotlinx_coroutines_core();
        ChannelSegment segment = firstSegment;
        while (true) {
            int i = 0;
            int i2 = BufferedChannelKt.SEGMENT_SIZE;
            while (true) {
                if (i < i2) {
                    ChannelSegment firstSegment2 = firstSegment;
                    long globalCellIndex = (segment.id * BufferedChannelKt.SEGMENT_SIZE) + i;
                    if (globalCellIndex < s || globalCellIndex < r) {
                        Object cellState = segment.getState$kotlinx_coroutines_core(i);
                        Object element = segment.getElement$kotlinx_coroutines_core(i);
                        if (cellState instanceof CancellableContinuation) {
                            str = (globalCellIndex >= r || globalCellIndex < s) ? (globalCellIndex >= s || globalCellIndex < r) ? "cont" : "send" : "receive";
                        } else if (cellState instanceof SelectInstance) {
                            str = (globalCellIndex >= r || globalCellIndex < s) ? (globalCellIndex >= s || globalCellIndex < r) ? "select" : "onSend" : "onReceive";
                        } else if (cellState instanceof ReceiveCatching) {
                            str = "receiveCatching";
                        } else if (cellState instanceof SendBroadcast) {
                            str = "sendBroadcast";
                        } else if (cellState instanceof WaiterEB) {
                            str = "EB(" + cellState + ')';
                        } else {
                            symbol = BufferedChannelKt.RESUMING_BY_RCV;
                            if (!Intrinsics.areEqual(cellState, symbol)) {
                                symbol2 = BufferedChannelKt.RESUMING_BY_EB;
                                if (!Intrinsics.areEqual(cellState, symbol2)) {
                                    if (cellState != null) {
                                        symbol3 = BufferedChannelKt.IN_BUFFER;
                                        if (!Intrinsics.areEqual(cellState, symbol3)) {
                                            symbol4 = BufferedChannelKt.DONE_RCV;
                                            if (!Intrinsics.areEqual(cellState, symbol4)) {
                                                symbol5 = BufferedChannelKt.POISONED;
                                                if (!Intrinsics.areEqual(cellState, symbol5)) {
                                                    symbol6 = BufferedChannelKt.INTERRUPTED_RCV;
                                                    if (!Intrinsics.areEqual(cellState, symbol6)) {
                                                        symbol7 = BufferedChannelKt.INTERRUPTED_SEND;
                                                        if (!Intrinsics.areEqual(cellState, symbol7) && !Intrinsics.areEqual(cellState, BufferedChannelKt.getCHANNEL_CLOSED())) {
                                                            str = cellState.toString();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    i++;
                                    firstSegment = firstSegment2;
                                }
                            }
                            str = "resuming_sender";
                        }
                        String cellStateString = str;
                        if (element != null) {
                            sb.append('(' + cellStateString + ',' + element + "),");
                        } else {
                            sb.append(cellStateString + ',');
                        }
                        i++;
                        firstSegment = firstSegment2;
                    }
                } else {
                    ChannelSegment firstSegment3 = firstSegment;
                    ChannelSegment channelSegment2 = (ChannelSegment) segment.getNext();
                    if (channelSegment2 != null) {
                        segment = channelSegment2;
                        firstSegment = firstSegment3;
                    }
                }
            }
        }
        if (StringsKt.last(sb) == ',') {
            Intrinsics.checkNotNullExpressionValue(sb.deleteCharAt(sb.length() - 1), "deleteCharAt(...)");
        }
        sb.append("]");
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String toStringDebug$kotlinx_coroutines_core() {
        ChannelSegment channelSegment;
        StringBuilder sb = new StringBuilder();
        StringBuilder append = new StringBuilder().append("S=").append(getSendersCounter$kotlinx_coroutines_core()).append(",R=").append(getReceiversCounter$kotlinx_coroutines_core()).append(",B=").append(getBufferEndCounter()).append(",B'=").append(completedExpandBuffersAndPauseFlag$volatile$FU.get(this)).append(",C=");
        long $this$sendersCloseStatus$iv = sendersAndCloseStatus$volatile$FU.get(this);
        sb.append(append.append((int) ($this$sendersCloseStatus$iv >> 60)).append(',').toString());
        long $this$sendersCloseStatus$iv2 = sendersAndCloseStatus$volatile$FU.get(this);
        int $i$f$getSendersCloseStatus = (int) ($this$sendersCloseStatus$iv2 >> 60);
        switch ($i$f$getSendersCloseStatus) {
            case 1:
                sb.append("CANCELLATION_STARTED,");
                break;
            case 2:
                sb.append("CLOSED,");
                break;
            case 3:
                sb.append("CANCELLED,");
                break;
        }
        sb.append("SEND_SEGM=" + DebugStringsKt.getHexAddress(sendSegment$volatile$FU.get(this)) + ",RCV_SEGM=" + DebugStringsKt.getHexAddress(receiveSegment$volatile$FU.get(this)));
        if (!isRendezvousOrUnlimited()) {
            sb.append(",EB_SEGM=" + DebugStringsKt.getHexAddress(bufferEndSegment$volatile$FU.get(this)));
        }
        sb.append("  ");
        Iterable $this$filter$iv = CollectionsKt.listOf((Object[]) new ChannelSegment[]{receiveSegment$volatile$FU.get(this), sendSegment$volatile$FU.get(this), bufferEndSegment$volatile$FU.get(this)});
        ArrayList arrayList = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            ChannelSegment it = (ChannelSegment) element$iv$iv;
            channelSegment = BufferedChannelKt.NULL_SEGMENT;
            ChannelSegment it2 = it != channelSegment ? 1 : null;
            if (it2 != null) {
                arrayList.add(element$iv$iv);
            }
        }
        ArrayList $this$minBy$iv = arrayList;
        Iterator iterator$iv = $this$minBy$iv.iterator();
        if (!iterator$iv.hasNext()) {
            throw new NoSuchElementException();
        }
        Object minElem$iv = iterator$iv.next();
        if (iterator$iv.hasNext()) {
            ChannelSegment it3 = (ChannelSegment) minElem$iv;
            long minValue$iv = it3.id;
            do {
                Object e$iv = iterator$iv.next();
                ChannelSegment it4 = (ChannelSegment) e$iv;
                long v$iv = it4.id;
                if (minValue$iv > v$iv) {
                    minElem$iv = e$iv;
                    minValue$iv = v$iv;
                }
            } while (iterator$iv.hasNext());
        }
        ChannelSegment firstSegment = (ChannelSegment) minElem$iv;
        ChannelSegment channelSegment2 = firstSegment;
        while (true) {
            StringBuilder append2 = new StringBuilder().append(DebugStringsKt.getHexAddress(channelSegment2)).append("=[").append(channelSegment2.isRemoved() ? "*" : "").append(channelSegment2.id).append(",prev=");
            ChannelSegment channelSegment3 = (ChannelSegment) channelSegment2.getPrev();
            sb.append(append2.append(channelSegment3 != null ? DebugStringsKt.getHexAddress(channelSegment3) : null).append(',').toString());
            int i = BufferedChannelKt.SEGMENT_SIZE;
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = i2;
                Object cellState = channelSegment2.getState$kotlinx_coroutines_core(i3);
                Object element = channelSegment2.getElement$kotlinx_coroutines_core(i3);
                String cellStateString = cellState instanceof CancellableContinuation ? "cont" : cellState instanceof SelectInstance ? "select" : cellState instanceof ReceiveCatching ? "receiveCatching" : cellState instanceof SendBroadcast ? "send(broadcast)" : cellState instanceof WaiterEB ? "EB(" + cellState + ')' : String.valueOf(cellState);
                sb.append('[' + i3 + "]=(" + cellStateString + ',' + element + "),");
            }
            StringBuilder append3 = new StringBuilder().append("next=");
            ChannelSegment channelSegment4 = (ChannelSegment) channelSegment2.getNext();
            sb.append(append3.append(channelSegment4 != null ? DebugStringsKt.getHexAddress(channelSegment4) : null).append("]  ").toString());
            ChannelSegment channelSegment5 = (ChannelSegment) channelSegment2.getNext();
            if (channelSegment5 == null) {
                return sb.toString();
            }
            channelSegment2 = channelSegment5;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:108:0x021e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0126  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void checkSegmentStructureInvariants() {
        boolean z;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        ChannelSegment channelSegment;
        ChannelSegment channelSegment2;
        if (isRendezvousOrUnlimited()) {
            Object obj = bufferEndSegment$volatile$FU.get(this);
            channelSegment2 = BufferedChannelKt.NULL_SEGMENT;
            if (!(obj == channelSegment2)) {
                throw new IllegalStateException(("bufferEndSegment must be NULL_SEGMENT for rendezvous and unlimited channels; they do not manipulate it.\nChannel state: " + this).toString());
            }
        } else if (!(((ChannelSegment) receiveSegment$volatile$FU.get(this)).id <= ((ChannelSegment) bufferEndSegment$volatile$FU.get(this)).id)) {
            throw new IllegalStateException(("bufferEndSegment should not have lower id than receiveSegment.\nChannel state: " + this).toString());
        }
        Iterable $this$filter$iv = CollectionsKt.listOf((Object[]) new ChannelSegment[]{receiveSegment$volatile$FU.get(this), sendSegment$volatile$FU.get(this), bufferEndSegment$volatile$FU.get(this)});
        ArrayList arrayList = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            ChannelSegment it = (ChannelSegment) element$iv$iv;
            channelSegment = BufferedChannelKt.NULL_SEGMENT;
            ChannelSegment it2 = it != channelSegment ? 1 : null;
            if (it2 != null) {
                arrayList.add(element$iv$iv);
            }
        }
        ArrayList destination$iv$iv = arrayList;
        ArrayList $this$minBy$iv = destination$iv$iv;
        Iterator iterator$iv = $this$minBy$iv.iterator();
        if (!iterator$iv.hasNext()) {
            throw new NoSuchElementException();
        }
        Object minElem$iv = iterator$iv.next();
        if (iterator$iv.hasNext()) {
            ChannelSegment it3 = (ChannelSegment) minElem$iv;
            long minValue$iv = it3.id;
            do {
                Object e$iv = iterator$iv.next();
                ChannelSegment it4 = (ChannelSegment) e$iv;
                long v$iv = it4.id;
                if (minValue$iv > v$iv) {
                    minElem$iv = e$iv;
                    minValue$iv = v$iv;
                }
            } while (iterator$iv.hasNext());
        }
        ChannelSegment firstSegment = (ChannelSegment) minElem$iv;
        if (!(firstSegment.getPrev() == 0)) {
            throw new IllegalStateException(("All processed segments should be unreachable from the data structure, but the `prev` link of the leftmost segment is non-null.\nChannel state: " + this).toString());
        }
        ChannelSegment segment = firstSegment;
        while (segment.getNext() != 0) {
            S next = segment.getNext();
            Intrinsics.checkNotNull(next);
            if (((ChannelSegment) next).getPrev() != 0) {
                S next2 = segment.getNext();
                Intrinsics.checkNotNull(next2);
                if (((ChannelSegment) next2).getPrev() != segment) {
                    z = false;
                    if (z) {
                        throw new IllegalStateException(("The `segment.next.prev === segment` invariant is violated.\nChannel state: " + this).toString());
                    }
                    int interruptedOrClosedCells = 0;
                    int i = BufferedChannelKt.SEGMENT_SIZE;
                    for (int i2 = 0; i2 < i; i2++) {
                        Object state = segment.getState$kotlinx_coroutines_core(i2);
                        if (!Intrinsics.areEqual(state, BufferedChannelKt.BUFFERED) && !(state instanceof Waiter)) {
                            symbol = BufferedChannelKt.INTERRUPTED_RCV;
                            if (!Intrinsics.areEqual(state, symbol)) {
                                symbol2 = BufferedChannelKt.INTERRUPTED_SEND;
                                if (!Intrinsics.areEqual(state, symbol2) && !Intrinsics.areEqual(state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                                    symbol3 = BufferedChannelKt.POISONED;
                                    if (!Intrinsics.areEqual(state, symbol3)) {
                                        symbol4 = BufferedChannelKt.DONE_RCV;
                                        if (!Intrinsics.areEqual(state, symbol4)) {
                                            throw new IllegalStateException(("Unexpected segment cell state: " + state + ".\nChannel state: " + this).toString());
                                        }
                                    }
                                    if (!(segment.getElement$kotlinx_coroutines_core(i2) == null)) {
                                        throw new IllegalStateException("Check failed.".toString());
                                    }
                                }
                            }
                            if (!(segment.getElement$kotlinx_coroutines_core(i2) == null)) {
                                throw new IllegalStateException("Check failed.".toString());
                            }
                            interruptedOrClosedCells++;
                        }
                    }
                    int i3 = BufferedChannelKt.SEGMENT_SIZE;
                    if (interruptedOrClosedCells == i3) {
                        if (!(segment == receiveSegment$volatile$FU.get(this) || segment == sendSegment$volatile$FU.get(this) || segment == bufferEndSegment$volatile$FU.get(this))) {
                            throw new IllegalStateException(("Logically removed segment is reachable.\nChannel state: " + this).toString());
                        }
                    }
                    S next3 = segment.getNext();
                    Intrinsics.checkNotNull(next3);
                    segment = (ChannelSegment) next3;
                }
            }
            z = true;
            if (z) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final KFunction<Unit> bindCancellationFunResult(Function1<? super E, Unit> function1) {
        return new BufferedChannel$bindCancellationFunResult$1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCancellationChannelResultImplDoNotCall-5_sEAP8, reason: not valid java name */
    public final void m2068onCancellationChannelResultImplDoNotCall5_sEAP8(Throwable cause, Object element, CoroutineContext context) {
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        Intrinsics.checkNotNull(function1);
        Object m2084getOrNullimpl = ChannelResult.m2084getOrNullimpl(element);
        Intrinsics.checkNotNull(m2084getOrNullimpl);
        OnUndeliveredElementKt.callUndeliveredElement(function1, m2084getOrNullimpl, context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Function3<Throwable, Object, CoroutineContext, Unit> bindCancellationFun(final Function1<? super E, Unit> function1, final E e) {
        return new Function3() { // from class: kotlinx.coroutines.channels.BufferedChannel$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                Unit bindCancellationFun$lambda$89;
                bindCancellationFun$lambda$89 = BufferedChannel.bindCancellationFun$lambda$89(Function1.this, e, (Throwable) obj, obj2, (CoroutineContext) obj3);
                return bindCancellationFun$lambda$89;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindCancellationFun$lambda$89(Function1 $this_bindCancellationFun, Object $element, Throwable th, Object obj, CoroutineContext context) {
        OnUndeliveredElementKt.callUndeliveredElement($this_bindCancellationFun, $element, context);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final KFunction<Unit> bindCancellationFun(Function1<? super E, Unit> function1) {
        return new BufferedChannel$bindCancellationFun$2(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCancellationImplDoNotCall(Throwable cause, E element, CoroutineContext context) {
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        Intrinsics.checkNotNull(function1);
        OnUndeliveredElementKt.callUndeliveredElement(function1, element, context);
    }
}
