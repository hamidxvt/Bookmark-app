package kotlinx.coroutines.channels;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: Deprecated.kt */
@Metadata(d1 = {"\u0000¶\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\"\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u001aC\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001d\u0010\u0004\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0004\u0012\u0002H\u00010\u0005¢\u0006\u0002\b\u0007H\u0087\b¢\u0006\u0002\u0010\b\u001a2\u0010\t\u001a\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\n0\u0005H\u0087H¢\u0006\u0002\u0010\f\u001aJ\u0010\r\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000e2\u001a\u0010\u0013\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00060\u0014\"\u0006\u0012\u0002\b\u00030\u0006H\u0001¢\u0006\u0002\u0010\u0015\u001a&\u0010\u0016\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0018H\u0087@¢\u0006\u0002\u0010\u0019\u001a(\u0010\u001a\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0018H\u0087@¢\u0006\u0002\u0010\u0019\u001a\u001e\u0010\u001b\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087@¢\u0006\u0002\u0010\u001c\u001a \u0010\u001d\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087@¢\u0006\u0002\u0010\u001c\u001a&\u0010\u001e\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00062\u0006\u0010\u001f\u001a\u0002H\u0002H\u0087@¢\u0006\u0002\u0010 \u001a\u001e\u0010!\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087@¢\u0006\u0002\u0010\u001c\u001a&\u0010\"\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00062\u0006\u0010\u001f\u001a\u0002H\u0002H\u0087@¢\u0006\u0002\u0010 \u001a \u0010#\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087@¢\u0006\u0002\u0010\u001c\u001a\u001e\u0010$\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087@¢\u0006\u0002\u0010\u001c\u001a \u0010%\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087@¢\u0006\u0002\u0010\u001c\u001a0\u0010&\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00062\u0006\u0010'\u001a\u00020\u00182\b\b\u0002\u0010(\u001a\u00020)H\u0007\u001aQ\u0010*\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00062\b\b\u0002\u0010(\u001a\u00020)2\"\u0010+\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0-\u0012\u0006\u0012\u0004\u0018\u00010/0,H\u0007¢\u0006\u0002\u00100\u001aQ\u00101\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00062\b\b\u0002\u0010(\u001a\u00020)2\"\u0010+\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0-\u0012\u0006\u0012\u0004\u0018\u00010/0,H\u0001¢\u0006\u0002\u00100\u001af\u00102\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00062\b\b\u0002\u0010(\u001a\u00020)27\u0010+\u001a3\b\u0001\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0-\u0012\u0006\u0012\u0004\u0018\u00010/03H\u0007¢\u0006\u0002\u00104\u001aQ\u00105\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00062\b\b\u0002\u0010(\u001a\u00020)2\"\u0010+\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0-\u0012\u0006\u0012\u0004\u0018\u00010/0,H\u0007¢\u0006\u0002\u00100\u001a$\u00106\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\b\b\u0000\u0010\u0002*\u00020/*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0006H\u0001\u001a>\u00107\u001a\u0002H8\"\b\b\u0000\u0010\u0002*\u00020/\"\u0010\b\u0001\u00108*\n\u0012\u0006\b\u0000\u0012\u0002H\u000209*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u00062\u0006\u0010:\u001a\u0002H8H\u0087@¢\u0006\u0002\u0010;\u001a<\u00107\u001a\u0002H8\"\b\b\u0000\u0010\u0002*\u00020/\"\u000e\b\u0001\u00108*\b\u0012\u0004\u0012\u0002H\u00020<*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u00062\u0006\u0010:\u001a\u0002H8H\u0087@¢\u0006\u0002\u0010=\u001a0\u0010>\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00062\u0006\u0010'\u001a\u00020\u00182\b\b\u0002\u0010(\u001a\u00020)H\u0007\u001aQ\u0010?\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00062\b\b\u0002\u0010(\u001a\u00020)2\"\u0010+\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0-\u0012\u0006\u0012\u0004\u0018\u00010/0,H\u0007¢\u0006\u0002\u00100\u001a6\u0010@\u001a\u0002H8\"\u0004\b\u0000\u0010\u0002\"\u000e\b\u0001\u00108*\b\u0012\u0004\u0012\u0002H\u00020<*\b\u0012\u0004\u0012\u0002H\u00020\u00062\u0006\u0010:\u001a\u0002H8H\u0081@¢\u0006\u0002\u0010=\u001a8\u0010A\u001a\u0002H8\"\u0004\b\u0000\u0010\u0002\"\u0010\b\u0001\u00108*\n\u0012\u0006\b\u0000\u0012\u0002H\u000209*\b\u0012\u0004\u0012\u0002H\u00020\u00062\u0006\u0010:\u001a\u0002H8H\u0081@¢\u0006\u0002\u0010;\u001a<\u0010B\u001a\u000e\u0012\u0004\u0012\u0002HD\u0012\u0004\u0012\u0002HE0C\"\u0004\b\u0000\u0010D\"\u0004\b\u0001\u0010E*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002HD\u0012\u0004\u0012\u0002HE0F0\u0006H\u0087@¢\u0006\u0002\u0010\u001c\u001aR\u0010B\u001a\u0002HG\"\u0004\b\u0000\u0010D\"\u0004\b\u0001\u0010E\"\u0018\b\u0002\u0010G*\u0012\u0012\u0006\b\u0000\u0012\u0002HD\u0012\u0006\b\u0000\u0012\u0002HE0H*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002HD\u0012\u0004\u0012\u0002HE0F0\u00062\u0006\u0010:\u001a\u0002HGH\u0081@¢\u0006\u0002\u0010I\u001a$\u0010J\u001a\b\u0012\u0004\u0012\u0002H\u00020K\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087@¢\u0006\u0002\u0010\u001c\u001a$\u0010L\u001a\b\u0012\u0004\u0012\u0002H\u00020M\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087@¢\u0006\u0002\u0010\u001c\u001a]\u0010N\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0006\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00062\b\b\u0002\u0010(\u001a\u00020)2(\u0010O\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00060-\u0012\u0006\u0012\u0004\u0018\u00010/0,H\u0007¢\u0006\u0002\u00100\u001aW\u0010P\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0006\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00062\b\b\u0002\u0010(\u001a\u00020)2\"\u0010O\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010-\u0012\u0006\u0012\u0004\u0018\u00010/0,H\u0001¢\u0006\u0002\u00100\u001al\u0010Q\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0006\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00062\b\b\u0002\u0010(\u001a\u00020)27\u0010O\u001a3\b\u0001\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010-\u0012\u0006\u0012\u0004\u0018\u00010/03H\u0001¢\u0006\u0002\u00104\u001ar\u0010R\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0006\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0001*\u00020/*\b\u0012\u0004\u0012\u0002H\u00020\u00062\b\b\u0002\u0010(\u001a\u00020)29\u0010O\u001a5\b\u0001\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u0002H\u0002\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00010-\u0012\u0006\u0012\u0004\u0018\u00010/03H\u0007¢\u0006\u0002\u00104\u001a]\u0010S\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0006\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0001*\u00020/*\b\u0012\u0004\u0012\u0002H\u00020\u00062\b\b\u0002\u0010(\u001a\u00020)2$\u0010O\u001a \b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00010-\u0012\u0006\u0012\u0004\u0018\u00010/0,H\u0007¢\u0006\u0002\u00100\u001a.\u0010T\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020U0\u0006\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00062\b\b\u0002\u0010(\u001a\u00020)H\u0007\u001a\u001e\u0010V\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0007\u001aW\u0010W\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010D*\b\u0012\u0004\u0012\u0002H\u00020\u00062\b\b\u0002\u0010(\u001a\u00020)2\"\u0010X\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002HD0-\u0012\u0006\u0012\u0004\u0018\u00010/0,H\u0001¢\u0006\u0002\u00100\u001a$\u0010Y\u001a\b\u0012\u0004\u0012\u0002H\u00020Z\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0081@¢\u0006\u0002\u0010\u001c\u001a\u001e\u0010[\u001a\u00020.\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087@¢\u0006\u0002\u0010\u001c\u001a\u001e\u0010\\\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087@¢\u0006\u0002\u0010\u001c\u001a<\u0010]\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00062\u001a\u0010^\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020`j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`_H\u0087@¢\u0006\u0002\u0010a\u001a<\u0010b\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00062\u001a\u0010^\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020`j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`_H\u0087@¢\u0006\u0002\u0010a\u001a\u001e\u0010c\u001a\u00020.\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087@¢\u0006\u0002\u0010\u001c\u001a$\u0010d\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\b\b\u0000\u0010\u0002*\u00020/*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0006H\u0007\u001a?\u0010e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010F0\u0006\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00062\f\u0010f\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0006H\u0087\u0004\u001az\u0010e\u001a\b\u0012\u0004\u0012\u0002HE0\u0006\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010E*\b\u0012\u0004\u0012\u0002H\u00020\u00062\f\u0010f\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00062\b\b\u0002\u0010(\u001a\u00020)26\u0010O\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(g\u0012\u0013\u0012\u0011H\u0001¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(h\u0012\u0004\u0012\u0002HE0,H\u0001\u001a6\u0010i\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000e*\u0006\u0012\u0002\b\u00030\u0006H\u0001¢\u0006\u0002\u0010j¨\u0006k"}, d2 = {"consume", "R", "E", "Lkotlinx/coroutines/channels/BroadcastChannel;", "block", "Lkotlin/Function1;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/channels/BroadcastChannel;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "consumeEach", "", "action", "(Lkotlinx/coroutines/channels/BroadcastChannel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "consumesAll", "Lkotlinx/coroutines/CompletionHandler;", "", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "cause", "channels", "", "([Lkotlinx/coroutines/channels/ReceiveChannel;)Lkotlin/jvm/functions/Function1;", "elementAt", FirebaseAnalytics.Param.INDEX, "", "(Lkotlinx/coroutines/channels/ReceiveChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "elementAtOrNull", "first", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "firstOrNull", "indexOf", "element", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "last", "lastIndexOf", "lastOrNull", "single", "singleOrNull", "drop", "n", "context", "Lkotlin/coroutines/CoroutineContext;", "dropWhile", "predicate", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "filter", "filterIndexed", "Lkotlin/Function3;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/channels/ReceiveChannel;", "filterNot", "filterNotNull", "filterNotNullTo", "C", "", FirebaseAnalytics.Param.DESTINATION, "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/SendChannel;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "take", "takeWhile", "toChannel", "toCollection", "toMap", "", "K", "V", "Lkotlin/Pair;", "M", "", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toMutableList", "", "toSet", "", "flatMap", "transform", "map", "mapIndexed", "mapIndexedNotNull", "mapNotNull", "withIndex", "Lkotlin/collections/IndexedValue;", "distinct", "distinctBy", "selector", "toMutableSet", "", "any", "count", "maxWith", "comparator", "Lkotlin/Comparator;", "Ljava/util/Comparator;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Comparator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "minWith", "none", "requireNoNulls", "zip", "other", "a", "b", "consumes", "(Lkotlinx/coroutines/channels/ReceiveChannel;)Lkotlin/jvm/functions/Function1;", "kotlinx-coroutines-core"}, k = 5, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE, xs = "kotlinx/coroutines/channels/ChannelsKt")
/* loaded from: classes17.dex */
final /* synthetic */ class ChannelsKt__DeprecatedKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported")
    public static final <E, R> R consume(BroadcastChannel<E> broadcastChannel, Function1<? super ReceiveChannel<? extends E>, ? extends R> function1) {
        ReceiveChannel channel = broadcastChannel.openSubscription();
        try {
            return function1.invoke(channel);
        } finally {
            InlineMarker.finallyStart(1);
            ReceiveChannel.DefaultImpls.cancel$default(channel, (CancellationException) null, 1, (Object) null);
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007f A[Catch: all -> 0x009d, TryCatch #2 {all -> 0x009d, blocks: (B:16:0x0077, B:18:0x007f, B:24:0x008d), top: B:15:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x008d A[Catch: all -> 0x009d, TRY_LEAVE, TryCatch #2 {all -> 0x009d, blocks: (B:16:0x0077, B:18:0x007f, B:24:0x008d), top: B:15:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x006f -> B:15:0x0077). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.ERROR, message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <E> Object consumeEach(BroadcastChannel<E> broadcastChannel, Function1<? super E, Unit> function1, Continuation<? super Unit> continuation) {
        ChannelsKt__DeprecatedKt$consumeEach$1 channelsKt__DeprecatedKt$consumeEach$1;
        ReceiveChannel channel$iv;
        Object $result;
        Function1 action;
        ReceiveChannel channel$iv2;
        ChannelIterator channelIterator;
        int i;
        Object obj;
        if (continuation instanceof ChannelsKt__DeprecatedKt$consumeEach$1) {
            channelsKt__DeprecatedKt$consumeEach$1 = (ChannelsKt__DeprecatedKt$consumeEach$1) continuation;
            if ((channelsKt__DeprecatedKt$consumeEach$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$consumeEach$1.label -= Integer.MIN_VALUE;
                Object element = channelsKt__DeprecatedKt$consumeEach$1.result;
                Object $result2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__DeprecatedKt$consumeEach$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(element);
                        channel$iv = broadcastChannel.openSubscription();
                        try {
                            int $i$f$consumeEach = 0;
                            Function1 action2 = function1;
                            ChannelIterator it = channel$iv.iterator();
                            channelsKt__DeprecatedKt$consumeEach$1.L$0 = action2;
                            channelsKt__DeprecatedKt$consumeEach$1.L$1 = channel$iv;
                            channelsKt__DeprecatedKt$consumeEach$1.L$2 = it;
                            channelsKt__DeprecatedKt$consumeEach$1.label = 1;
                            Object hasNext = it.hasNext(channelsKt__DeprecatedKt$consumeEach$1);
                            if (hasNext != $result2) {
                                return $result2;
                            }
                            Object obj2 = $result2;
                            $result = element;
                            element = hasNext;
                            action = action2;
                            channel$iv2 = channel$iv;
                            channelIterator = it;
                            i = $i$f$consumeEach;
                            obj = obj2;
                            try {
                                if (((Boolean) element).booleanValue()) {
                                    Unit unit = Unit.INSTANCE;
                                    InlineMarker.finallyStart(1);
                                    ReceiveChannel.DefaultImpls.cancel$default(channel$iv2, (CancellationException) null, 1, (Object) null);
                                    InlineMarker.finallyEnd(1);
                                    return Unit.INSTANCE;
                                }
                                action.invoke(channelIterator.next());
                                element = $result;
                                $result2 = obj;
                                $i$f$consumeEach = i;
                                it = channelIterator;
                                channel$iv = channel$iv2;
                                action2 = action;
                                channelsKt__DeprecatedKt$consumeEach$1.L$0 = action2;
                                channelsKt__DeprecatedKt$consumeEach$1.L$1 = channel$iv;
                                channelsKt__DeprecatedKt$consumeEach$1.L$2 = it;
                                channelsKt__DeprecatedKt$consumeEach$1.label = 1;
                                Object hasNext2 = it.hasNext(channelsKt__DeprecatedKt$consumeEach$1);
                                if (hasNext2 != $result2) {
                                }
                            } catch (Throwable th) {
                                channel$iv = channel$iv2;
                                th = th;
                                InlineMarker.finallyStart(1);
                                ReceiveChannel.DefaultImpls.cancel$default(channel$iv, (CancellationException) null, 1, (Object) null);
                                InlineMarker.finallyEnd(1);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            InlineMarker.finallyStart(1);
                            ReceiveChannel.DefaultImpls.cancel$default(channel$iv, (CancellationException) null, 1, (Object) null);
                            InlineMarker.finallyEnd(1);
                            throw th;
                        }
                    case 1:
                        ChannelIterator channelIterator2 = (ChannelIterator) channelsKt__DeprecatedKt$consumeEach$1.L$2;
                        channel$iv = (ReceiveChannel) channelsKt__DeprecatedKt$consumeEach$1.L$1;
                        Function1 action3 = (Function1) channelsKt__DeprecatedKt$consumeEach$1.L$0;
                        try {
                            ResultKt.throwOnFailure(element);
                            action = action3;
                            channel$iv2 = channel$iv;
                            channelIterator = channelIterator2;
                            i = 0;
                            obj = $result2;
                            $result = element;
                            if (((Boolean) element).booleanValue()) {
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            InlineMarker.finallyStart(1);
                            ReceiveChannel.DefaultImpls.cancel$default(channel$iv, (CancellationException) null, 1, (Object) null);
                            InlineMarker.finallyEnd(1);
                            throw th;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$consumeEach$1 = new ChannelsKt__DeprecatedKt$consumeEach$1(continuation);
        Object element2 = channelsKt__DeprecatedKt$consumeEach$1.result;
        Object $result22 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__DeprecatedKt$consumeEach$1.label) {
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported")
    private static final <E> Object consumeEach$$forInline(BroadcastChannel<E> broadcastChannel, Function1<? super E, Unit> function1, Continuation<? super Unit> continuation) {
        ReceiveChannel channel$iv = broadcastChannel.openSubscription();
        try {
            ReceiveChannel $this$consumeEach_u24lambda_u240 = channel$iv;
            ChannelIterator<E> it = $this$consumeEach_u24lambda_u240.iterator();
            while (true) {
                InlineMarker.mark(3);
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(null);
                InlineMarker.mark(1);
                if (!((Boolean) hasNext).booleanValue()) {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ReceiveChannel.DefaultImpls.cancel$default(channel$iv, (CancellationException) null, 1, (Object) null);
                    InlineMarker.finallyEnd(1);
                    return Unit.INSTANCE;
                }
                Object element = it.next();
                function1.invoke(element);
            }
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            ReceiveChannel.DefaultImpls.cancel$default(channel$iv, (CancellationException) null, 1, (Object) null);
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    public static final Function1<Throwable, Unit> consumesAll(final ReceiveChannel<?>... receiveChannelArr) {
        return new Function1() { // from class: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit consumesAll$lambda$2$ChannelsKt__DeprecatedKt;
                consumesAll$lambda$2$ChannelsKt__DeprecatedKt = ChannelsKt__DeprecatedKt.consumesAll$lambda$2$ChannelsKt__DeprecatedKt(receiveChannelArr, (Throwable) obj);
                return consumesAll$lambda$2$ChannelsKt__DeprecatedKt;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit consumesAll$lambda$2$ChannelsKt__DeprecatedKt(ReceiveChannel[] $channels, Throwable cause) {
        Throwable exception = null;
        for (ReceiveChannel channel : $channels) {
            try {
                ChannelsKt.cancelConsumed(channel, cause);
            } catch (Throwable e) {
                if (exception == null) {
                    exception = e;
                } else {
                    ExceptionsKt.addSuppressed(exception, e);
                }
            }
        }
        if (exception != null) {
            Throwable it = exception;
            throw it;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008a A[Catch: all -> 0x00c2, TRY_LEAVE, TryCatch #1 {all -> 0x00c2, blocks: (B:16:0x0082, B:18:0x008a), top: B:15:0x0082 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0078 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0079 -> B:15:0x0082). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object elementAt(ReceiveChannel $this$consume$iv, int index, Continuation $completion) {
        ChannelsKt__DeprecatedKt$elementAt$1 channelsKt__DeprecatedKt$elementAt$1;
        ReceiveChannel $this$consume$iv2;
        Object $result;
        Throwable th;
        ReceiveChannel $this$consume$iv3;
        ChannelIterator channelIterator;
        int index2;
        int index3;
        Object obj;
        if ($completion instanceof ChannelsKt__DeprecatedKt$elementAt$1) {
            channelsKt__DeprecatedKt$elementAt$1 = (ChannelsKt__DeprecatedKt$elementAt$1) $completion;
            if ((channelsKt__DeprecatedKt$elementAt$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$elementAt$1.label -= Integer.MIN_VALUE;
                Object $result2 = channelsKt__DeprecatedKt$elementAt$1.result;
                Object $result3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__DeprecatedKt$elementAt$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result2);
                        try {
                            if (index < 0) {
                                throw new IndexOutOfBoundsException("ReceiveChannel doesn't contain element at index " + index + ClassUtils.PACKAGE_SEPARATOR_CHAR);
                            }
                            ChannelIterator it = $this$consume$iv.iterator();
                            ReceiveChannel $this$consume$iv4 = $this$consume$iv;
                            int $i$f$consume = 0;
                            Throwable cause$iv = null;
                            int index4 = index;
                            try {
                                channelsKt__DeprecatedKt$elementAt$1.L$0 = $this$consume$iv4;
                                channelsKt__DeprecatedKt$elementAt$1.L$1 = it;
                                channelsKt__DeprecatedKt$elementAt$1.I$0 = index4;
                                channelsKt__DeprecatedKt$elementAt$1.I$1 = $i$f$consume;
                                channelsKt__DeprecatedKt$elementAt$1.label = 1;
                                Object hasNext = it.hasNext(channelsKt__DeprecatedKt$elementAt$1);
                                if (hasNext != $result3) {
                                    return $result3;
                                }
                                Object obj2 = $result3;
                                $result = $result2;
                                $result2 = hasNext;
                                th = cause$iv;
                                $this$consume$iv3 = $this$consume$iv4;
                                channelIterator = it;
                                index2 = index4;
                                index3 = $i$f$consume;
                                obj = obj2;
                                try {
                                    if (((Boolean) $result2).booleanValue()) {
                                        ReceiveChannel $this$consume$iv5 = $this$consume$iv3;
                                        try {
                                            throw new IndexOutOfBoundsException("ReceiveChannel doesn't contain element at index " + index2 + ClassUtils.PACKAGE_SEPARATOR_CHAR);
                                        } catch (Throwable th2) {
                                            e$iv = th2;
                                            $this$consume$iv2 = $this$consume$iv5;
                                            Throwable cause$iv2 = e$iv;
                                            try {
                                                throw e$iv;
                                            } catch (Throwable e$iv) {
                                                ChannelsKt.cancelConsumed($this$consume$iv2, cause$iv2);
                                                throw e$iv;
                                            }
                                        }
                                    }
                                    Object next = channelIterator.next();
                                    int count = index3 + 1;
                                    if (index2 != index3) {
                                        ReceiveChannel $this$consume$iv6 = $this$consume$iv3;
                                        cause$iv = th;
                                        index4 = index2;
                                        it = channelIterator;
                                        $this$consume$iv4 = $this$consume$iv6;
                                        $result2 = $result;
                                        $result3 = obj;
                                        $i$f$consume = count;
                                        channelsKt__DeprecatedKt$elementAt$1.L$0 = $this$consume$iv4;
                                        channelsKt__DeprecatedKt$elementAt$1.L$1 = it;
                                        channelsKt__DeprecatedKt$elementAt$1.I$0 = index4;
                                        channelsKt__DeprecatedKt$elementAt$1.I$1 = $i$f$consume;
                                        channelsKt__DeprecatedKt$elementAt$1.label = 1;
                                        Object hasNext2 = it.hasNext(channelsKt__DeprecatedKt$elementAt$1);
                                        if (hasNext2 != $result3) {
                                        }
                                    } else {
                                        ChannelsKt.cancelConsumed($this$consume$iv3, th);
                                        return next;
                                    }
                                } catch (Throwable th3) {
                                    e$iv = th3;
                                    $this$consume$iv2 = $this$consume$iv3;
                                }
                            } catch (Throwable th4) {
                                e$iv = th4;
                                $this$consume$iv2 = $this$consume$iv4;
                                Throwable cause$iv22 = e$iv;
                                throw e$iv;
                            }
                        } catch (Throwable th5) {
                            e$iv = th5;
                            $this$consume$iv2 = $this$consume$iv;
                            Throwable cause$iv222 = e$iv;
                            throw e$iv;
                        }
                    case 1:
                        int count2 = channelsKt__DeprecatedKt$elementAt$1.I$1;
                        int index5 = channelsKt__DeprecatedKt$elementAt$1.I$0;
                        ChannelIterator channelIterator2 = (ChannelIterator) channelsKt__DeprecatedKt$elementAt$1.L$1;
                        ReceiveChannel receiveChannel = (ReceiveChannel) channelsKt__DeprecatedKt$elementAt$1.L$0;
                        try {
                            ResultKt.throwOnFailure($result2);
                            th = null;
                            $this$consume$iv3 = receiveChannel;
                            channelIterator = channelIterator2;
                            index2 = index5;
                            index3 = count2;
                            obj = $result3;
                            $result = $result2;
                            if (((Boolean) $result2).booleanValue()) {
                            }
                        } catch (Throwable th6) {
                            e$iv = th6;
                            $this$consume$iv2 = receiveChannel;
                            Throwable cause$iv2222 = e$iv;
                            throw e$iv;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$elementAt$1 = new ChannelsKt__DeprecatedKt$elementAt$1($completion);
        Object $result22 = channelsKt__DeprecatedKt$elementAt$1.result;
        Object $result32 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__DeprecatedKt$elementAt$1.label) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0089 A[Catch: all -> 0x00a3, TRY_LEAVE, TryCatch #0 {all -> 0x00a3, blocks: (B:16:0x0081, B:18:0x0089), top: B:15:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0078 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0079 -> B:15:0x0081). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object elementAtOrNull(ReceiveChannel $this$consume$iv, int index, Continuation $completion) {
        ChannelsKt__DeprecatedKt$elementAtOrNull$1 channelsKt__DeprecatedKt$elementAtOrNull$1;
        ReceiveChannel $this$consume$iv2;
        Throwable e$iv;
        ReceiveChannel $this$consume$iv3;
        Object $result;
        Throwable th;
        ChannelIterator channelIterator;
        int index2;
        int index3;
        Object obj;
        if ($completion instanceof ChannelsKt__DeprecatedKt$elementAtOrNull$1) {
            channelsKt__DeprecatedKt$elementAtOrNull$1 = (ChannelsKt__DeprecatedKt$elementAtOrNull$1) $completion;
            if ((channelsKt__DeprecatedKt$elementAtOrNull$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$elementAtOrNull$1.label -= Integer.MIN_VALUE;
                Object $result2 = channelsKt__DeprecatedKt$elementAtOrNull$1.result;
                Object $result3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__DeprecatedKt$elementAtOrNull$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result2);
                        if (index < 0) {
                            ChannelsKt.cancelConsumed($this$consume$iv, null);
                            return null;
                        }
                        Throwable cause$iv = null;
                        try {
                            int index4 = index;
                            int $i$f$consume = 0;
                            $this$consume$iv3 = $this$consume$iv;
                            ChannelIterator it = $this$consume$iv.iterator();
                            try {
                                channelsKt__DeprecatedKt$elementAtOrNull$1.L$0 = $this$consume$iv3;
                                channelsKt__DeprecatedKt$elementAtOrNull$1.L$1 = it;
                                channelsKt__DeprecatedKt$elementAtOrNull$1.I$0 = index4;
                                channelsKt__DeprecatedKt$elementAtOrNull$1.I$1 = $i$f$consume;
                                channelsKt__DeprecatedKt$elementAtOrNull$1.label = 1;
                                Object hasNext = it.hasNext(channelsKt__DeprecatedKt$elementAtOrNull$1);
                                if (hasNext != $result3) {
                                    return $result3;
                                }
                                Object obj2 = $result3;
                                $result = $result2;
                                $result2 = hasNext;
                                th = cause$iv;
                                channelIterator = it;
                                index2 = index4;
                                index3 = $i$f$consume;
                                obj = obj2;
                                try {
                                    if (((Boolean) $result2).booleanValue()) {
                                        ChannelsKt.cancelConsumed($this$consume$iv3, th);
                                        return null;
                                    }
                                    Object next = channelIterator.next();
                                    int count = index3 + 1;
                                    if (index2 == index3) {
                                        ChannelsKt.cancelConsumed($this$consume$iv3, th);
                                        return next;
                                    }
                                    index4 = index2;
                                    it = channelIterator;
                                    cause$iv = th;
                                    $result2 = $result;
                                    $result3 = obj;
                                    $i$f$consume = count;
                                    channelsKt__DeprecatedKt$elementAtOrNull$1.L$0 = $this$consume$iv3;
                                    channelsKt__DeprecatedKt$elementAtOrNull$1.L$1 = it;
                                    channelsKt__DeprecatedKt$elementAtOrNull$1.I$0 = index4;
                                    channelsKt__DeprecatedKt$elementAtOrNull$1.I$1 = $i$f$consume;
                                    channelsKt__DeprecatedKt$elementAtOrNull$1.label = 1;
                                    Object hasNext2 = it.hasNext(channelsKt__DeprecatedKt$elementAtOrNull$1);
                                    if (hasNext2 != $result3) {
                                    }
                                } catch (Throwable th2) {
                                    e$iv = th2;
                                    $this$consume$iv2 = $this$consume$iv3;
                                    Throwable cause$iv2 = e$iv;
                                    try {
                                        throw e$iv;
                                    } catch (Throwable e$iv2) {
                                        ChannelsKt.cancelConsumed($this$consume$iv2, cause$iv2);
                                        throw e$iv2;
                                    }
                                }
                            } catch (Throwable th3) {
                                e$iv = th3;
                                $this$consume$iv2 = $this$consume$iv3;
                                Throwable cause$iv22 = e$iv;
                                throw e$iv;
                            }
                        } catch (Throwable th4) {
                            $this$consume$iv2 = $this$consume$iv;
                            e$iv = th4;
                            Throwable cause$iv222 = e$iv;
                            throw e$iv;
                        }
                    case 1:
                        int count2 = channelsKt__DeprecatedKt$elementAtOrNull$1.I$1;
                        int index5 = channelsKt__DeprecatedKt$elementAtOrNull$1.I$0;
                        ChannelIterator channelIterator2 = (ChannelIterator) channelsKt__DeprecatedKt$elementAtOrNull$1.L$1;
                        ReceiveChannel receiveChannel = (ReceiveChannel) channelsKt__DeprecatedKt$elementAtOrNull$1.L$0;
                        try {
                            ResultKt.throwOnFailure($result2);
                            th = null;
                            $this$consume$iv3 = receiveChannel;
                            channelIterator = channelIterator2;
                            index2 = index5;
                            index3 = count2;
                            obj = $result3;
                            $result = $result2;
                            if (((Boolean) $result2).booleanValue()) {
                            }
                        } catch (Throwable th5) {
                            e$iv = th5;
                            $this$consume$iv2 = receiveChannel;
                            Throwable cause$iv2222 = e$iv;
                            throw e$iv;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$elementAtOrNull$1 = new ChannelsKt__DeprecatedKt$elementAtOrNull$1($completion);
        Object $result22 = channelsKt__DeprecatedKt$elementAtOrNull$1.result;
        Object $result32 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__DeprecatedKt$elementAtOrNull$1.label) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0065 A[Catch: all -> 0x003c, TRY_LEAVE, TryCatch #2 {all -> 0x003c, blocks: (B:13:0x0037, B:15:0x005d, B:17:0x0065, B:20:0x006d, B:21:0x0074), top: B:12:0x0037 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006d A[Catch: all -> 0x003c, TRY_ENTER, TryCatch #2 {all -> 0x003c, blocks: (B:13:0x0037, B:15:0x005d, B:17:0x0065, B:20:0x006d, B:21:0x0074), top: B:12:0x0037 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object first(ReceiveChannel $this$first, Continuation $completion) {
        ChannelsKt__DeprecatedKt$first$1 channelsKt__DeprecatedKt$first$1;
        ReceiveChannel $this$consume$iv;
        Throwable cause$iv;
        ReceiveChannel $this$consume$iv2;
        ChannelIterator iterator;
        Object hasNext;
        if ($completion instanceof ChannelsKt__DeprecatedKt$first$1) {
            channelsKt__DeprecatedKt$first$1 = (ChannelsKt__DeprecatedKt$first$1) $completion;
            if ((channelsKt__DeprecatedKt$first$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$first$1.label -= Integer.MIN_VALUE;
                Object $result = channelsKt__DeprecatedKt$first$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__DeprecatedKt$first$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        $this$consume$iv = $this$first;
                        cause$iv = null;
                        try {
                            iterator = $this$consume$iv.iterator();
                            channelsKt__DeprecatedKt$first$1.L$0 = $this$consume$iv;
                            channelsKt__DeprecatedKt$first$1.L$1 = iterator;
                            channelsKt__DeprecatedKt$first$1.label = 1;
                            hasNext = iterator.hasNext(channelsKt__DeprecatedKt$first$1);
                            if (hasNext == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            if (((Boolean) hasNext).booleanValue()) {
                                throw new NoSuchElementException("ReceiveChannel is empty.");
                            }
                            Object next = iterator.next();
                            ChannelsKt.cancelConsumed($this$consume$iv, cause$iv);
                            return next;
                        } catch (Throwable th) {
                            e$iv = th;
                            $this$consume$iv2 = $this$consume$iv;
                            Throwable cause$iv2 = e$iv;
                            try {
                                throw e$iv;
                            } catch (Throwable e$iv) {
                                ChannelsKt.cancelConsumed($this$consume$iv2, cause$iv2);
                                throw e$iv;
                            }
                        }
                    case 1:
                        iterator = (ChannelIterator) channelsKt__DeprecatedKt$first$1.L$1;
                        $this$consume$iv = (ReceiveChannel) channelsKt__DeprecatedKt$first$1.L$0;
                        cause$iv = null;
                        try {
                            ResultKt.throwOnFailure($result);
                            hasNext = $result;
                            if (((Boolean) hasNext).booleanValue()) {
                            }
                        } catch (Throwable th2) {
                            e$iv = th2;
                            $this$consume$iv2 = $this$consume$iv;
                            Throwable cause$iv22 = e$iv;
                            throw e$iv;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$first$1 = new ChannelsKt__DeprecatedKt$first$1($completion);
        Object $result2 = channelsKt__DeprecatedKt$first$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__DeprecatedKt$first$1.label) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006b A[Catch: all -> 0x0070, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0070, blocks: (B:16:0x005e, B:20:0x006b), top: B:15:0x005e }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object firstOrNull(ReceiveChannel $this$firstOrNull, Continuation $completion) {
        ChannelsKt__DeprecatedKt$firstOrNull$1 channelsKt__DeprecatedKt$firstOrNull$1;
        ReceiveChannel $this$consume$iv;
        Throwable cause$iv;
        ReceiveChannel $this$consume$iv2;
        ChannelIterator iterator;
        Object hasNext;
        if ($completion instanceof ChannelsKt__DeprecatedKt$firstOrNull$1) {
            channelsKt__DeprecatedKt$firstOrNull$1 = (ChannelsKt__DeprecatedKt$firstOrNull$1) $completion;
            if ((channelsKt__DeprecatedKt$firstOrNull$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$firstOrNull$1.label -= Integer.MIN_VALUE;
                Object $result = channelsKt__DeprecatedKt$firstOrNull$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__DeprecatedKt$firstOrNull$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        $this$consume$iv = $this$firstOrNull;
                        cause$iv = null;
                        try {
                            iterator = $this$consume$iv.iterator();
                            channelsKt__DeprecatedKt$firstOrNull$1.L$0 = $this$consume$iv;
                            channelsKt__DeprecatedKt$firstOrNull$1.L$1 = iterator;
                            channelsKt__DeprecatedKt$firstOrNull$1.label = 1;
                            hasNext = iterator.hasNext(channelsKt__DeprecatedKt$firstOrNull$1);
                            if (hasNext == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            try {
                                Object next = ((Boolean) hasNext).booleanValue() ? iterator.next() : null;
                                ChannelsKt.cancelConsumed($this$consume$iv, cause$iv);
                                return next;
                            } catch (Throwable th) {
                                e$iv = th;
                                $this$consume$iv2 = $this$consume$iv;
                                Throwable cause$iv2 = e$iv;
                                try {
                                    throw e$iv;
                                } catch (Throwable e$iv) {
                                    ChannelsKt.cancelConsumed($this$consume$iv2, cause$iv2);
                                    throw e$iv;
                                }
                            }
                        } catch (Throwable th2) {
                            e$iv = th2;
                            $this$consume$iv2 = $this$consume$iv;
                            Throwable cause$iv22 = e$iv;
                            throw e$iv;
                        }
                    case 1:
                        ChannelIterator iterator2 = (ChannelIterator) channelsKt__DeprecatedKt$firstOrNull$1.L$1;
                        $this$consume$iv = (ReceiveChannel) channelsKt__DeprecatedKt$firstOrNull$1.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                            hasNext = $result;
                            iterator = iterator2;
                            cause$iv = null;
                            if (((Boolean) hasNext).booleanValue()) {
                            }
                            ChannelsKt.cancelConsumed($this$consume$iv, cause$iv);
                            return next;
                        } catch (Throwable th3) {
                            e$iv = th3;
                            $this$consume$iv2 = $this$consume$iv;
                            Throwable cause$iv222 = e$iv;
                            throw e$iv;
                        }
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$firstOrNull$1 = new ChannelsKt__DeprecatedKt$firstOrNull$1($completion);
        Object $result2 = channelsKt__DeprecatedKt$firstOrNull$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__DeprecatedKt$firstOrNull$1.label) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x009d A[Catch: all -> 0x00ec, TryCatch #5 {all -> 0x00ec, blocks: (B:16:0x0095, B:18:0x009d, B:20:0x00a9), top: B:15:0x0095 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0087 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0088 -> B:15:0x0095). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object indexOf(ReceiveChannel $this$consume$iv$iv, Object element, Continuation $completion) {
        ChannelsKt__DeprecatedKt$indexOf$1 channelsKt__DeprecatedKt$indexOf$1;
        ChannelsKt__DeprecatedKt$indexOf$1 channelsKt__DeprecatedKt$indexOf$12;
        ReceiveChannel $this$consume$iv$iv2;
        Object element2;
        Object element3;
        ReceiveChannel $this$consume$iv$iv3;
        Object $result;
        ChannelIterator it;
        Ref.IntRef index;
        Ref.IntRef index2;
        Throwable cause$iv$iv;
        Object hasNext;
        Object $result2;
        Throwable th;
        Object element4;
        Ref.IntRef index3;
        ReceiveChannel receiveChannel;
        ChannelIterator channelIterator;
        Ref.IntRef intRef;
        Object obj;
        Object obj2;
        if ($completion instanceof ChannelsKt__DeprecatedKt$indexOf$1) {
            channelsKt__DeprecatedKt$indexOf$1 = (ChannelsKt__DeprecatedKt$indexOf$1) $completion;
            if ((channelsKt__DeprecatedKt$indexOf$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$indexOf$1.label -= Integer.MIN_VALUE;
                channelsKt__DeprecatedKt$indexOf$12 = channelsKt__DeprecatedKt$indexOf$1;
                Object $result3 = channelsKt__DeprecatedKt$indexOf$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__DeprecatedKt$indexOf$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result3);
                        Ref.IntRef index4 = new Ref.IntRef();
                        try {
                            element2 = element;
                            element3 = null;
                            $this$consume$iv$iv3 = $this$consume$iv$iv;
                            $result = null;
                            it = $this$consume$iv$iv.iterator();
                            index = index4;
                            index2 = null;
                            cause$iv$iv = null;
                            try {
                                channelsKt__DeprecatedKt$indexOf$12.L$0 = element2;
                                channelsKt__DeprecatedKt$indexOf$12.L$1 = index;
                                channelsKt__DeprecatedKt$indexOf$12.L$2 = $this$consume$iv$iv3;
                                channelsKt__DeprecatedKt$indexOf$12.L$3 = it;
                                channelsKt__DeprecatedKt$indexOf$12.label = 1;
                                hasNext = it.hasNext(channelsKt__DeprecatedKt$indexOf$12);
                            } catch (Throwable th2) {
                                e$iv$iv = th2;
                                $this$consume$iv$iv2 = $this$consume$iv$iv3;
                            }
                        } catch (Throwable th3) {
                            e$iv$iv = th3;
                            $this$consume$iv$iv2 = $this$consume$iv$iv;
                        }
                        if (hasNext != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Object obj3 = $result;
                        $result2 = $result3;
                        $result3 = hasNext;
                        th = cause$iv$iv;
                        element4 = element2;
                        index3 = index;
                        receiveChannel = $this$consume$iv$iv3;
                        channelIterator = it;
                        intRef = index2;
                        obj = element3;
                        obj2 = obj3;
                        try {
                            if (((Boolean) $result3).booleanValue()) {
                                $this$consume$iv$iv2 = receiveChannel;
                                Throwable cause$iv$iv2 = th;
                                try {
                                    Unit unit = Unit.INSTANCE;
                                    ChannelsKt.cancelConsumed($this$consume$iv$iv2, cause$iv$iv2);
                                    return Boxing.boxInt(-1);
                                } catch (Throwable th4) {
                                    e$iv$iv = th4;
                                }
                            } else {
                                Object e$iv = channelIterator.next();
                                if (Intrinsics.areEqual(element4, e$iv)) {
                                    Integer boxInt = Boxing.boxInt(index3.element);
                                    ChannelsKt.cancelConsumed(receiveChannel, th);
                                    return boxInt;
                                }
                                ReceiveChannel $this$consume$iv$iv4 = receiveChannel;
                                Throwable cause$iv$iv3 = th;
                                try {
                                    index3.element++;
                                    ChannelIterator channelIterator2 = channelIterator;
                                    $this$consume$iv$iv3 = $this$consume$iv$iv4;
                                    $result3 = $result2;
                                    $result = obj2;
                                    element3 = obj;
                                    index2 = intRef;
                                    it = channelIterator2;
                                    Object obj4 = element4;
                                    cause$iv$iv = cause$iv$iv3;
                                    index = index3;
                                    element2 = obj4;
                                    channelsKt__DeprecatedKt$indexOf$12.L$0 = element2;
                                    channelsKt__DeprecatedKt$indexOf$12.L$1 = index;
                                    channelsKt__DeprecatedKt$indexOf$12.L$2 = $this$consume$iv$iv3;
                                    channelsKt__DeprecatedKt$indexOf$12.L$3 = it;
                                    channelsKt__DeprecatedKt$indexOf$12.label = 1;
                                    hasNext = it.hasNext(channelsKt__DeprecatedKt$indexOf$12);
                                    if (hasNext != coroutine_suspended) {
                                    }
                                } catch (Throwable th5) {
                                    e$iv$iv = th5;
                                    $this$consume$iv$iv2 = $this$consume$iv$iv4;
                                }
                            }
                        } catch (Throwable th6) {
                            e$iv$iv = th6;
                            $this$consume$iv$iv2 = receiveChannel;
                        }
                        Throwable cause$iv$iv4 = e$iv$iv;
                        try {
                            throw e$iv$iv;
                        } catch (Throwable e$iv$iv) {
                            ChannelsKt.cancelConsumed($this$consume$iv$iv2, cause$iv$iv4);
                            throw e$iv$iv;
                        }
                    case 1:
                        ChannelIterator channelIterator3 = (ChannelIterator) channelsKt__DeprecatedKt$indexOf$12.L$3;
                        ReceiveChannel receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$indexOf$12.L$2;
                        Ref.IntRef index5 = (Ref.IntRef) channelsKt__DeprecatedKt$indexOf$12.L$1;
                        Object element5 = channelsKt__DeprecatedKt$indexOf$12.L$0;
                        try {
                            ResultKt.throwOnFailure($result3);
                            th = null;
                            element4 = element5;
                            index3 = index5;
                            receiveChannel = receiveChannel2;
                            channelIterator = channelIterator3;
                            intRef = null;
                            obj = null;
                            obj2 = null;
                            $result2 = $result3;
                            if (((Boolean) $result3).booleanValue()) {
                            }
                        } catch (Throwable th7) {
                            e$iv$iv = th7;
                            $this$consume$iv$iv2 = receiveChannel2;
                        }
                        Throwable cause$iv$iv42 = e$iv$iv;
                        throw e$iv$iv;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$indexOf$1 = new ChannelsKt__DeprecatedKt$indexOf$1($completion);
        channelsKt__DeprecatedKt$indexOf$12 = channelsKt__DeprecatedKt$indexOf$1;
        Object $result32 = channelsKt__DeprecatedKt$indexOf$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__DeprecatedKt$indexOf$12.label) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00b5 A[Catch: all -> 0x00c9, TRY_LEAVE, TryCatch #3 {all -> 0x00c9, blocks: (B:16:0x00ad, B:18:0x00b5), top: B:15:0x00ad }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0089 A[Catch: all -> 0x00dc, TRY_LEAVE, TryCatch #4 {all -> 0x00dc, blocks: (B:48:0x0081, B:50:0x0089, B:52:0x00d4, B:53:0x00db), top: B:47:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00d4 A[Catch: all -> 0x00dc, TRY_ENTER, TryCatch #4 {all -> 0x00dc, blocks: (B:48:0x0081, B:50:0x0089, B:52:0x00d4, B:53:0x00db), top: B:47:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x00a4 -> B:15:0x00ad). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object last(ReceiveChannel $this$last, Continuation $completion) {
        ChannelsKt__DeprecatedKt$last$1 channelsKt__DeprecatedKt$last$1;
        ReceiveChannel $this$consume$iv;
        int i;
        Object hasNext;
        Throwable cause$iv;
        ChannelIterator iterator;
        Object $result;
        Throwable th;
        ReceiveChannel receiveChannel;
        ChannelIterator iterator2;
        Object obj;
        int i2;
        Object obj2;
        if ($completion instanceof ChannelsKt__DeprecatedKt$last$1) {
            channelsKt__DeprecatedKt$last$1 = (ChannelsKt__DeprecatedKt$last$1) $completion;
            if ((channelsKt__DeprecatedKt$last$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$last$1.label -= Integer.MIN_VALUE;
                Object last = channelsKt__DeprecatedKt$last$1.result;
                Object $result2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__DeprecatedKt$last$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(last);
                        $this$consume$iv = $this$last;
                        i = 0;
                        try {
                            ChannelIterator iterator3 = $this$consume$iv.iterator();
                            channelsKt__DeprecatedKt$last$1.L$0 = $this$consume$iv;
                            channelsKt__DeprecatedKt$last$1.L$1 = iterator3;
                            channelsKt__DeprecatedKt$last$1.label = 1;
                            hasNext = iterator3.hasNext(channelsKt__DeprecatedKt$last$1);
                            if (hasNext == $result2) {
                                return $result2;
                            }
                            cause$iv = null;
                            iterator = iterator3;
                            try {
                                if (((Boolean) hasNext).booleanValue()) {
                                    throw new NoSuchElementException("ReceiveChannel is empty.");
                                }
                                int i3 = i;
                                ReceiveChannel $this$consume$iv2 = $this$consume$iv;
                                int i4 = i3;
                                Throwable th2 = cause$iv;
                                ChannelIterator iterator4 = iterator;
                                Object last2 = iterator.next();
                                Throwable cause$iv2 = th2;
                                try {
                                    channelsKt__DeprecatedKt$last$1.L$0 = $this$consume$iv2;
                                    channelsKt__DeprecatedKt$last$1.L$1 = iterator4;
                                    channelsKt__DeprecatedKt$last$1.L$2 = last2;
                                    channelsKt__DeprecatedKt$last$1.label = 2;
                                    Object hasNext2 = iterator4.hasNext(channelsKt__DeprecatedKt$last$1);
                                    if (hasNext2 != $result2) {
                                        return $result2;
                                    }
                                    Object obj3 = $result2;
                                    $result = last;
                                    last = hasNext2;
                                    th = cause$iv2;
                                    receiveChannel = $this$consume$iv2;
                                    iterator2 = iterator4;
                                    obj = last2;
                                    i2 = i4;
                                    obj2 = obj3;
                                    try {
                                        if (((Boolean) last).booleanValue()) {
                                            ChannelsKt.cancelConsumed(receiveChannel, th);
                                            return obj;
                                        }
                                        ReceiveChannel $this$consume$iv3 = receiveChannel;
                                        cause$iv2 = th;
                                        int i5 = i2;
                                        last2 = iterator2.next();
                                        last = $result;
                                        $result2 = obj2;
                                        i4 = i5;
                                        ChannelIterator channelIterator = iterator2;
                                        $this$consume$iv2 = $this$consume$iv3;
                                        iterator4 = channelIterator;
                                        channelsKt__DeprecatedKt$last$1.L$0 = $this$consume$iv2;
                                        channelsKt__DeprecatedKt$last$1.L$1 = iterator4;
                                        channelsKt__DeprecatedKt$last$1.L$2 = last2;
                                        channelsKt__DeprecatedKt$last$1.label = 2;
                                        Object hasNext22 = iterator4.hasNext(channelsKt__DeprecatedKt$last$1);
                                        if (hasNext22 != $result2) {
                                        }
                                    } catch (Throwable th3) {
                                        $this$consume$iv = receiveChannel;
                                        e$iv = th3;
                                        Throwable cause$iv3 = e$iv;
                                        try {
                                            throw e$iv;
                                        } catch (Throwable e$iv) {
                                            ChannelsKt.cancelConsumed($this$consume$iv, cause$iv3);
                                            throw e$iv;
                                        }
                                    }
                                } catch (Throwable th4) {
                                    e$iv = th4;
                                    $this$consume$iv = $this$consume$iv2;
                                    Throwable cause$iv32 = e$iv;
                                    throw e$iv;
                                }
                            } catch (Throwable th5) {
                                e$iv = th5;
                                Throwable cause$iv322 = e$iv;
                                throw e$iv;
                            }
                        } catch (Throwable th6) {
                            e$iv = th6;
                            Throwable cause$iv3222 = e$iv;
                            throw e$iv;
                        }
                    case 1:
                        iterator = (ChannelIterator) channelsKt__DeprecatedKt$last$1.L$1;
                        cause$iv = null;
                        ReceiveChannel $this$consume$iv4 = (ReceiveChannel) channelsKt__DeprecatedKt$last$1.L$0;
                        try {
                            ResultKt.throwOnFailure(last);
                            hasNext = last;
                            i = 0;
                            $this$consume$iv = $this$consume$iv4;
                            if (((Boolean) hasNext).booleanValue()) {
                            }
                        } catch (Throwable th7) {
                            e$iv = th7;
                            $this$consume$iv = $this$consume$iv4;
                            Throwable cause$iv32222 = e$iv;
                            throw e$iv;
                        }
                        break;
                    case 2:
                        Object last3 = channelsKt__DeprecatedKt$last$1.L$2;
                        ChannelIterator iterator5 = (ChannelIterator) channelsKt__DeprecatedKt$last$1.L$1;
                        ReceiveChannel receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$last$1.L$0;
                        try {
                            ResultKt.throwOnFailure(last);
                            th = null;
                            receiveChannel = receiveChannel2;
                            iterator2 = iterator5;
                            obj = last3;
                            i2 = 0;
                            obj2 = $result2;
                            $result = last;
                            if (((Boolean) last).booleanValue()) {
                            }
                        } catch (Throwable th8) {
                            e$iv = th8;
                            $this$consume$iv = receiveChannel2;
                            Throwable cause$iv322222 = e$iv;
                            throw e$iv;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$last$1 = new ChannelsKt__DeprecatedKt$last$1($completion);
        Object last4 = channelsKt__DeprecatedKt$last$1.result;
        Object $result22 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__DeprecatedKt$last$1.label) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00a8 A[Catch: all -> 0x00d8, TryCatch #3 {all -> 0x00d8, blocks: (B:16:0x00a0, B:18:0x00a8, B:20:0x00b3, B:21:0x00b7, B:36:0x00c8), top: B:15:0x00a0 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0091 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c8 A[Catch: all -> 0x00d8, TRY_LEAVE, TryCatch #3 {all -> 0x00d8, blocks: (B:16:0x00a0, B:18:0x00a8, B:20:0x00b3, B:21:0x00b7, B:36:0x00c8), top: B:15:0x00a0 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0092 -> B:15:0x00a0). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object lastIndexOf(ReceiveChannel $this$consumeEach$iv, Object element, Continuation $completion) {
        ChannelsKt__DeprecatedKt$lastIndexOf$1 channelsKt__DeprecatedKt$lastIndexOf$1;
        ChannelsKt__DeprecatedKt$lastIndexOf$1 channelsKt__DeprecatedKt$lastIndexOf$12;
        ReceiveChannel $this$consume$iv$iv;
        Ref.IntRef index;
        Ref.IntRef lastIndex;
        Object element2;
        Object element3;
        int $i$f$consume;
        Throwable cause$iv$iv;
        Ref.IntRef index2;
        ChannelIterator it;
        Object $result;
        Object element4;
        Ref.IntRef lastIndex2;
        Ref.IntRef lastIndex3;
        ReceiveChannel $this$consume$iv$iv2;
        Throwable cause$iv$iv2;
        ChannelIterator channelIterator;
        Ref.IntRef intRef;
        Object obj;
        int $i$f$consume2;
        if ($completion instanceof ChannelsKt__DeprecatedKt$lastIndexOf$1) {
            channelsKt__DeprecatedKt$lastIndexOf$1 = (ChannelsKt__DeprecatedKt$lastIndexOf$1) $completion;
            if ((channelsKt__DeprecatedKt$lastIndexOf$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$lastIndexOf$1.label -= Integer.MIN_VALUE;
                channelsKt__DeprecatedKt$lastIndexOf$12 = channelsKt__DeprecatedKt$lastIndexOf$1;
                Object $result2 = channelsKt__DeprecatedKt$lastIndexOf$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__DeprecatedKt$lastIndexOf$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result2);
                        Ref.IntRef lastIndex4 = new Ref.IntRef();
                        lastIndex4.element = -1;
                        Ref.IntRef index3 = new Ref.IntRef();
                        $this$consume$iv$iv = $this$consumeEach$iv;
                        try {
                            index = lastIndex4;
                            lastIndex = null;
                            element2 = element;
                            element3 = null;
                            $i$f$consume = 0;
                            cause$iv$iv = null;
                            index2 = index3;
                            it = $this$consume$iv$iv.iterator();
                        } catch (Throwable th) {
                            e$iv$iv = th;
                            Throwable cause$iv$iv3 = e$iv$iv;
                            try {
                                throw e$iv$iv;
                            } catch (Throwable e$iv$iv) {
                                ChannelsKt.cancelConsumed($this$consume$iv$iv, cause$iv$iv3);
                                throw e$iv$iv;
                            }
                        }
                        try {
                            channelsKt__DeprecatedKt$lastIndexOf$12.L$0 = element2;
                            channelsKt__DeprecatedKt$lastIndexOf$12.L$1 = index;
                            channelsKt__DeprecatedKt$lastIndexOf$12.L$2 = index2;
                            channelsKt__DeprecatedKt$lastIndexOf$12.L$3 = $this$consume$iv$iv;
                            channelsKt__DeprecatedKt$lastIndexOf$12.L$4 = it;
                            channelsKt__DeprecatedKt$lastIndexOf$12.label = 1;
                            Object hasNext = it.hasNext(channelsKt__DeprecatedKt$lastIndexOf$12);
                            if (hasNext != coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            int i = $i$f$consume;
                            $result = $result2;
                            $result2 = hasNext;
                            element4 = element2;
                            lastIndex2 = index;
                            lastIndex3 = index2;
                            $this$consume$iv$iv2 = $this$consume$iv$iv;
                            cause$iv$iv2 = cause$iv$iv;
                            channelIterator = it;
                            intRef = lastIndex;
                            obj = element3;
                            $i$f$consume2 = i;
                            try {
                                if (!((Boolean) $result2).booleanValue()) {
                                    Object it2 = channelIterator.next();
                                    if (Intrinsics.areEqual(element4, it2)) {
                                        lastIndex2.element = lastIndex3.element;
                                    }
                                    lastIndex3.element++;
                                    $result2 = $result;
                                    $i$f$consume = $i$f$consume2;
                                    element3 = obj;
                                    lastIndex = intRef;
                                    it = channelIterator;
                                    cause$iv$iv = cause$iv$iv2;
                                    $this$consume$iv$iv = $this$consume$iv$iv2;
                                    index2 = lastIndex3;
                                    index = lastIndex2;
                                    element2 = element4;
                                    channelsKt__DeprecatedKt$lastIndexOf$12.L$0 = element2;
                                    channelsKt__DeprecatedKt$lastIndexOf$12.L$1 = index;
                                    channelsKt__DeprecatedKt$lastIndexOf$12.L$2 = index2;
                                    channelsKt__DeprecatedKt$lastIndexOf$12.L$3 = $this$consume$iv$iv;
                                    channelsKt__DeprecatedKt$lastIndexOf$12.L$4 = it;
                                    channelsKt__DeprecatedKt$lastIndexOf$12.label = 1;
                                    Object hasNext2 = it.hasNext(channelsKt__DeprecatedKt$lastIndexOf$12);
                                    if (hasNext2 != coroutine_suspended) {
                                    }
                                } else {
                                    Unit unit = Unit.INSTANCE;
                                    ChannelsKt.cancelConsumed($this$consume$iv$iv2, cause$iv$iv2);
                                    return Boxing.boxInt(lastIndex2.element);
                                }
                            } catch (Throwable th2) {
                                e$iv$iv = th2;
                                $this$consume$iv$iv = $this$consume$iv$iv2;
                                Throwable cause$iv$iv32 = e$iv$iv;
                                throw e$iv$iv;
                            }
                        } catch (Throwable th3) {
                            e$iv$iv = th3;
                            Throwable cause$iv$iv322 = e$iv$iv;
                            throw e$iv$iv;
                        }
                    case 1:
                        ChannelIterator channelIterator2 = (ChannelIterator) channelsKt__DeprecatedKt$lastIndexOf$12.L$4;
                        $this$consume$iv$iv = (ReceiveChannel) channelsKt__DeprecatedKt$lastIndexOf$12.L$3;
                        Ref.IntRef index4 = (Ref.IntRef) channelsKt__DeprecatedKt$lastIndexOf$12.L$2;
                        Ref.IntRef lastIndex5 = (Ref.IntRef) channelsKt__DeprecatedKt$lastIndexOf$12.L$1;
                        Object element5 = channelsKt__DeprecatedKt$lastIndexOf$12.L$0;
                        try {
                            ResultKt.throwOnFailure($result2);
                            element4 = element5;
                            lastIndex2 = lastIndex5;
                            lastIndex3 = index4;
                            $this$consume$iv$iv2 = $this$consume$iv$iv;
                            cause$iv$iv2 = null;
                            channelIterator = channelIterator2;
                            intRef = null;
                            obj = null;
                            $i$f$consume2 = 0;
                            $result = $result2;
                            if (!((Boolean) $result2).booleanValue()) {
                            }
                        } catch (Throwable th4) {
                            e$iv$iv = th4;
                            Throwable cause$iv$iv3222 = e$iv$iv;
                            throw e$iv$iv;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$lastIndexOf$1 = new ChannelsKt__DeprecatedKt$lastIndexOf$1($completion);
        channelsKt__DeprecatedKt$lastIndexOf$12 = channelsKt__DeprecatedKt$lastIndexOf$1;
        Object $result22 = channelsKt__DeprecatedKt$lastIndexOf$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__DeprecatedKt$lastIndexOf$12.label) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00b4 A[Catch: all -> 0x00c8, TRY_LEAVE, TryCatch #4 {all -> 0x00c8, blocks: (B:16:0x00ac, B:18:0x00b4), top: B:15:0x00ac }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x00a3 -> B:15:0x00ac). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object lastOrNull(ReceiveChannel $this$consume$iv, Continuation $completion) {
        ChannelsKt__DeprecatedKt$lastOrNull$1 channelsKt__DeprecatedKt$lastOrNull$1;
        int $i$f$consume;
        Throwable cause$iv;
        ReceiveChannel $this$consume$iv2;
        Throwable e$iv;
        ChannelIterator iterator;
        Object hasNext;
        ReceiveChannel receiveChannel;
        Object $result;
        ReceiveChannel $this$consume$iv3;
        ChannelIterator iterator2;
        Object obj;
        Throwable th;
        int $i$f$consume2;
        Object obj2;
        if ($completion instanceof ChannelsKt__DeprecatedKt$lastOrNull$1) {
            channelsKt__DeprecatedKt$lastOrNull$1 = (ChannelsKt__DeprecatedKt$lastOrNull$1) $completion;
            if ((channelsKt__DeprecatedKt$lastOrNull$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$lastOrNull$1.label -= Integer.MIN_VALUE;
                Object last = channelsKt__DeprecatedKt$lastOrNull$1.result;
                Object $result2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__DeprecatedKt$lastOrNull$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(last);
                        $i$f$consume = 0;
                        cause$iv = null;
                        try {
                            iterator = $this$consume$iv.iterator();
                            channelsKt__DeprecatedKt$lastOrNull$1.L$0 = $this$consume$iv;
                            channelsKt__DeprecatedKt$lastOrNull$1.L$1 = iterator;
                            channelsKt__DeprecatedKt$lastOrNull$1.label = 1;
                            hasNext = iterator.hasNext(channelsKt__DeprecatedKt$lastOrNull$1);
                            if (hasNext == $result2) {
                                return $result2;
                            }
                            receiveChannel = $this$consume$iv;
                            try {
                                if (((Boolean) hasNext).booleanValue()) {
                                    ChannelsKt.cancelConsumed(receiveChannel, cause$iv);
                                    return null;
                                }
                                ReceiveChannel $this$consume$iv4 = receiveChannel;
                                try {
                                    ReceiveChannel $this$consume$iv5 = $this$consume$iv4;
                                    Throwable cause$iv2 = cause$iv;
                                    Object last2 = iterator.next();
                                    try {
                                        channelsKt__DeprecatedKt$lastOrNull$1.L$0 = $this$consume$iv5;
                                        channelsKt__DeprecatedKt$lastOrNull$1.L$1 = iterator;
                                        channelsKt__DeprecatedKt$lastOrNull$1.L$2 = last2;
                                        channelsKt__DeprecatedKt$lastOrNull$1.label = 2;
                                        Object hasNext2 = iterator.hasNext(channelsKt__DeprecatedKt$lastOrNull$1);
                                        if (hasNext2 != $result2) {
                                            return $result2;
                                        }
                                        Object obj3 = $result2;
                                        $result = last;
                                        last = hasNext2;
                                        $this$consume$iv3 = $this$consume$iv5;
                                        iterator2 = iterator;
                                        obj = last2;
                                        th = cause$iv2;
                                        $i$f$consume2 = $i$f$consume;
                                        obj2 = obj3;
                                        try {
                                            if (((Boolean) last).booleanValue()) {
                                                ChannelsKt.cancelConsumed($this$consume$iv3, th);
                                                return obj;
                                            }
                                            Throwable th2 = th;
                                            last2 = iterator2.next();
                                            last = $result;
                                            $result2 = obj2;
                                            $i$f$consume = $i$f$consume2;
                                            cause$iv2 = th2;
                                            ChannelIterator channelIterator = iterator2;
                                            $this$consume$iv5 = $this$consume$iv3;
                                            iterator = channelIterator;
                                            channelsKt__DeprecatedKt$lastOrNull$1.L$0 = $this$consume$iv5;
                                            channelsKt__DeprecatedKt$lastOrNull$1.L$1 = iterator;
                                            channelsKt__DeprecatedKt$lastOrNull$1.L$2 = last2;
                                            channelsKt__DeprecatedKt$lastOrNull$1.label = 2;
                                            Object hasNext22 = iterator.hasNext(channelsKt__DeprecatedKt$lastOrNull$1);
                                            if (hasNext22 != $result2) {
                                            }
                                        } catch (Throwable th3) {
                                            e$iv = th3;
                                            $this$consume$iv2 = $this$consume$iv3;
                                            Throwable cause$iv3 = e$iv;
                                            try {
                                                throw e$iv;
                                            } catch (Throwable e$iv2) {
                                                ChannelsKt.cancelConsumed($this$consume$iv2, cause$iv3);
                                                throw e$iv2;
                                            }
                                        }
                                    } catch (Throwable th4) {
                                        e$iv = th4;
                                        $this$consume$iv2 = $this$consume$iv5;
                                        Throwable cause$iv32 = e$iv;
                                        throw e$iv;
                                    }
                                } catch (Throwable th5) {
                                    e$iv = th5;
                                    $this$consume$iv2 = $this$consume$iv4;
                                    Throwable cause$iv322 = e$iv;
                                    throw e$iv;
                                }
                            } catch (Throwable th6) {
                                e$iv = th6;
                                $this$consume$iv2 = receiveChannel;
                            }
                        } catch (Throwable th7) {
                            $this$consume$iv2 = $this$consume$iv;
                            e$iv = th7;
                            Throwable cause$iv3222 = e$iv;
                            throw e$iv;
                        }
                    case 1:
                        $i$f$consume = 0;
                        ChannelIterator iterator3 = (ChannelIterator) channelsKt__DeprecatedKt$lastOrNull$1.L$1;
                        ReceiveChannel receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$lastOrNull$1.L$0;
                        try {
                            ResultKt.throwOnFailure(last);
                            hasNext = last;
                            receiveChannel = receiveChannel2;
                            iterator = iterator3;
                            cause$iv = null;
                            if (((Boolean) hasNext).booleanValue()) {
                            }
                        } catch (Throwable th8) {
                            e$iv = th8;
                            $this$consume$iv2 = receiveChannel2;
                            Throwable cause$iv32222 = e$iv;
                            throw e$iv;
                        }
                        break;
                    case 2:
                        Object last3 = channelsKt__DeprecatedKt$lastOrNull$1.L$2;
                        ChannelIterator iterator4 = (ChannelIterator) channelsKt__DeprecatedKt$lastOrNull$1.L$1;
                        ReceiveChannel receiveChannel3 = (ReceiveChannel) channelsKt__DeprecatedKt$lastOrNull$1.L$0;
                        try {
                            ResultKt.throwOnFailure(last);
                            $this$consume$iv3 = receiveChannel3;
                            iterator2 = iterator4;
                            obj = last3;
                            th = null;
                            $i$f$consume2 = 0;
                            obj2 = $result2;
                            $result = last;
                            if (((Boolean) last).booleanValue()) {
                            }
                        } catch (Throwable th9) {
                            e$iv = th9;
                            $this$consume$iv2 = receiveChannel3;
                            Throwable cause$iv322222 = e$iv;
                            throw e$iv;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$lastOrNull$1 = new ChannelsKt__DeprecatedKt$lastOrNull$1($completion);
        Object last4 = channelsKt__DeprecatedKt$lastOrNull$1.result;
        Object $result22 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__DeprecatedKt$lastOrNull$1.label) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a0 A[Catch: all -> 0x003b, TRY_ENTER, TryCatch #3 {all -> 0x003b, blocks: (B:13:0x0035, B:15:0x0093, B:19:0x00a0, B:20:0x00a7), top: B:12:0x0035 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007e A[Catch: all -> 0x0050, TRY_LEAVE, TryCatch #1 {all -> 0x0050, blocks: (B:33:0x004b, B:35:0x0076, B:37:0x007e, B:41:0x00a8, B:42:0x00af), top: B:32:0x004b }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a8 A[Catch: all -> 0x0050, TRY_ENTER, TryCatch #1 {all -> 0x0050, blocks: (B:33:0x004b, B:35:0x0076, B:37:0x007e, B:41:0x00a8, B:42:0x00af), top: B:32:0x004b }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object single(ReceiveChannel $this$single, Continuation $completion) {
        ChannelsKt__DeprecatedKt$single$1 channelsKt__DeprecatedKt$single$1;
        ReceiveChannel $this$consume$iv;
        Object hasNext;
        ReceiveChannel $this$consume$iv2;
        ReceiveChannel $this$consume$iv3;
        Throwable cause$iv;
        ChannelIterator iterator;
        Object hasNext2;
        ReceiveChannel receiveChannel;
        Object obj;
        if ($completion instanceof ChannelsKt__DeprecatedKt$single$1) {
            channelsKt__DeprecatedKt$single$1 = (ChannelsKt__DeprecatedKt$single$1) $completion;
            if ((channelsKt__DeprecatedKt$single$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$single$1.label -= Integer.MIN_VALUE;
                Object $result = channelsKt__DeprecatedKt$single$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__DeprecatedKt$single$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        $this$consume$iv = $this$single;
                        try {
                            ChannelIterator iterator2 = $this$consume$iv.iterator();
                            channelsKt__DeprecatedKt$single$1.L$0 = $this$consume$iv;
                            channelsKt__DeprecatedKt$single$1.L$1 = iterator2;
                            channelsKt__DeprecatedKt$single$1.label = 1;
                            hasNext = iterator2.hasNext(channelsKt__DeprecatedKt$single$1);
                            if (hasNext == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $this$consume$iv2 = $this$consume$iv;
                            $this$consume$iv3 = null;
                            cause$iv = null;
                            iterator = iterator2;
                            if (((Boolean) hasNext).booleanValue()) {
                                throw new NoSuchElementException("ReceiveChannel is empty.");
                            }
                            Object single = iterator.next();
                            channelsKt__DeprecatedKt$single$1.L$0 = $this$consume$iv2;
                            channelsKt__DeprecatedKt$single$1.L$1 = single;
                            channelsKt__DeprecatedKt$single$1.label = 2;
                            hasNext2 = iterator.hasNext(channelsKt__DeprecatedKt$single$1);
                            if (hasNext2 == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            receiveChannel = $this$consume$iv2;
                            obj = single;
                            if (!((Boolean) hasNext2).booleanValue()) {
                                throw new IllegalArgumentException("ReceiveChannel has more than one element.");
                            }
                            ChannelsKt.cancelConsumed(receiveChannel, cause$iv);
                            return obj;
                        } catch (Throwable th) {
                            e$iv = th;
                            Throwable cause$iv2 = e$iv;
                            try {
                                throw e$iv;
                            } catch (Throwable e$iv) {
                                ChannelsKt.cancelConsumed($this$consume$iv, cause$iv2);
                                throw e$iv;
                            }
                        }
                    case 1:
                        $this$consume$iv3 = null;
                        iterator = (ChannelIterator) channelsKt__DeprecatedKt$single$1.L$1;
                        cause$iv = null;
                        $this$consume$iv2 = (ReceiveChannel) channelsKt__DeprecatedKt$single$1.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                            hasNext = $result;
                            if (((Boolean) hasNext).booleanValue()) {
                            }
                        } catch (Throwable th2) {
                            e$iv = th2;
                            $this$consume$iv = $this$consume$iv2;
                            Throwable cause$iv22 = e$iv;
                            throw e$iv;
                        }
                        break;
                    case 2:
                        obj = channelsKt__DeprecatedKt$single$1.L$1;
                        receiveChannel = (ReceiveChannel) channelsKt__DeprecatedKt$single$1.L$0;
                        cause$iv = null;
                        try {
                            ResultKt.throwOnFailure($result);
                            hasNext2 = $result;
                            if (!((Boolean) hasNext2).booleanValue()) {
                            }
                        } catch (Throwable th3) {
                            e$iv = th3;
                            $this$consume$iv = receiveChannel;
                            Throwable cause$iv222 = e$iv;
                            throw e$iv;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$single$1 = new ChannelsKt__DeprecatedKt$single$1($completion);
        Object $result2 = channelsKt__DeprecatedKt$single$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__DeprecatedKt$single$1.label) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0089 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object singleOrNull(ReceiveChannel $this$singleOrNull, Continuation $completion) {
        ChannelsKt__DeprecatedKt$singleOrNull$1 channelsKt__DeprecatedKt$singleOrNull$1;
        ReceiveChannel $this$consume$iv;
        Throwable cause$iv;
        Throwable e$iv;
        ChannelIterator iterator;
        Object hasNext;
        ReceiveChannel $this$consume$iv2;
        ReceiveChannel $this$consume$iv3;
        Object hasNext2;
        Throwable th;
        ReceiveChannel receiveChannel;
        Object obj;
        if ($completion instanceof ChannelsKt__DeprecatedKt$singleOrNull$1) {
            channelsKt__DeprecatedKt$singleOrNull$1 = (ChannelsKt__DeprecatedKt$singleOrNull$1) $completion;
            if ((channelsKt__DeprecatedKt$singleOrNull$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$singleOrNull$1.label -= Integer.MIN_VALUE;
                Object $result = channelsKt__DeprecatedKt$singleOrNull$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__DeprecatedKt$singleOrNull$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        $this$consume$iv = $this$singleOrNull;
                        cause$iv = null;
                        try {
                            iterator = $this$consume$iv.iterator();
                            channelsKt__DeprecatedKt$singleOrNull$1.L$0 = $this$consume$iv;
                            channelsKt__DeprecatedKt$singleOrNull$1.L$1 = iterator;
                            channelsKt__DeprecatedKt$singleOrNull$1.label = 1;
                            hasNext = iterator.hasNext(channelsKt__DeprecatedKt$singleOrNull$1);
                            if (hasNext == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $this$consume$iv2 = null;
                            $this$consume$iv3 = $this$consume$iv;
                            try {
                                if (((Boolean) hasNext).booleanValue()) {
                                    ChannelsKt.cancelConsumed($this$consume$iv3, cause$iv);
                                    return null;
                                }
                                try {
                                    Object single = iterator.next();
                                    channelsKt__DeprecatedKt$singleOrNull$1.L$0 = $this$consume$iv3;
                                    channelsKt__DeprecatedKt$singleOrNull$1.L$1 = single;
                                    channelsKt__DeprecatedKt$singleOrNull$1.label = 2;
                                    hasNext2 = iterator.hasNext(channelsKt__DeprecatedKt$singleOrNull$1);
                                    if (hasNext2 == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    th = cause$iv;
                                    receiveChannel = $this$consume$iv3;
                                    obj = single;
                                    try {
                                        if (((Boolean) hasNext2).booleanValue()) {
                                            ChannelsKt.cancelConsumed(receiveChannel, th);
                                            return obj;
                                        }
                                        ChannelsKt.cancelConsumed(receiveChannel, th);
                                        return null;
                                    } catch (Throwable th2) {
                                        e$iv = th2;
                                        $this$consume$iv = receiveChannel;
                                        Throwable cause$iv2 = e$iv;
                                        try {
                                            throw e$iv;
                                        } catch (Throwable e$iv2) {
                                            ChannelsKt.cancelConsumed($this$consume$iv, cause$iv2);
                                            throw e$iv2;
                                        }
                                    }
                                } catch (Throwable th3) {
                                    e$iv = th3;
                                    $this$consume$iv = $this$consume$iv3;
                                    Throwable cause$iv22 = e$iv;
                                    throw e$iv;
                                }
                            } catch (Throwable th4) {
                                e$iv = th4;
                                $this$consume$iv = $this$consume$iv3;
                            }
                        } catch (Throwable th5) {
                            e$iv = th5;
                            Throwable cause$iv222 = e$iv;
                            throw e$iv;
                        }
                    case 1:
                        ChannelIterator iterator2 = (ChannelIterator) channelsKt__DeprecatedKt$singleOrNull$1.L$1;
                        ReceiveChannel receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$singleOrNull$1.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                            hasNext = $result;
                            $this$consume$iv3 = receiveChannel2;
                            iterator = iterator2;
                            cause$iv = null;
                            $this$consume$iv2 = null;
                            if (((Boolean) hasNext).booleanValue()) {
                            }
                        } catch (Throwable th6) {
                            $this$consume$iv = receiveChannel2;
                            e$iv = th6;
                            Throwable cause$iv2222 = e$iv;
                            throw e$iv;
                        }
                        break;
                    case 2:
                        obj = channelsKt__DeprecatedKt$singleOrNull$1.L$1;
                        receiveChannel = (ReceiveChannel) channelsKt__DeprecatedKt$singleOrNull$1.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                            hasNext2 = $result;
                            th = null;
                            if (((Boolean) hasNext2).booleanValue()) {
                            }
                        } catch (Throwable th7) {
                            e$iv = th7;
                            $this$consume$iv = receiveChannel;
                            Throwable cause$iv22222 = e$iv;
                            throw e$iv;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$singleOrNull$1 = new ChannelsKt__DeprecatedKt$singleOrNull$1($completion);
        Object $result2 = channelsKt__DeprecatedKt$singleOrNull$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__DeprecatedKt$singleOrNull$1.label) {
        }
    }

    public static /* synthetic */ ReceiveChannel drop$default(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return drop(receiveChannel, i, coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel drop(ReceiveChannel $this$drop, int n, CoroutineContext context) {
        ReceiveChannel produce;
        produce = ProduceKt.produce(GlobalScope.INSTANCE, (r12 & 1) != 0 ? EmptyCoroutineContext.INSTANCE : context, (r12 & 2) != 0 ? 0 : 0, (r12 & 4) != 0 ? CoroutineStart.DEFAULT : null, (r12 & 8) != 0 ? null : ChannelsKt.consumes($this$drop), new ChannelsKt__DeprecatedKt$drop$1(n, $this$drop, null));
        return produce;
    }

    public static /* synthetic */ ReceiveChannel dropWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return dropWhile(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel dropWhile(ReceiveChannel $this$dropWhile, CoroutineContext context, Function2 predicate) {
        ReceiveChannel produce;
        produce = ProduceKt.produce(GlobalScope.INSTANCE, (r12 & 1) != 0 ? EmptyCoroutineContext.INSTANCE : context, (r12 & 2) != 0 ? 0 : 0, (r12 & 4) != 0 ? CoroutineStart.DEFAULT : null, (r12 & 8) != 0 ? null : ChannelsKt.consumes($this$dropWhile), new ChannelsKt__DeprecatedKt$dropWhile$1($this$dropWhile, predicate, null));
        return produce;
    }

    public static /* synthetic */ ReceiveChannel filter$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filter(receiveChannel, coroutineContext, function2);
    }

    public static final <E> ReceiveChannel<E> filter(ReceiveChannel<? extends E> receiveChannel, CoroutineContext context, Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        ReceiveChannel<E> produce;
        produce = ProduceKt.produce(GlobalScope.INSTANCE, (r12 & 1) != 0 ? EmptyCoroutineContext.INSTANCE : context, (r12 & 2) != 0 ? 0 : 0, (r12 & 4) != 0 ? CoroutineStart.DEFAULT : null, (r12 & 8) != 0 ? null : ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$filter$1(receiveChannel, function2, null));
        return produce;
    }

    public static /* synthetic */ ReceiveChannel filterIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return filterIndexed(receiveChannel, coroutineContext, function3);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel filterIndexed(ReceiveChannel $this$filterIndexed, CoroutineContext context, Function3 predicate) {
        ReceiveChannel produce;
        produce = ProduceKt.produce(GlobalScope.INSTANCE, (r12 & 1) != 0 ? EmptyCoroutineContext.INSTANCE : context, (r12 & 2) != 0 ? 0 : 0, (r12 & 4) != 0 ? CoroutineStart.DEFAULT : null, (r12 & 8) != 0 ? null : ChannelsKt.consumes($this$filterIndexed), new ChannelsKt__DeprecatedKt$filterIndexed$1($this$filterIndexed, predicate, null));
        return produce;
    }

    public static /* synthetic */ ReceiveChannel filterNot$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return filterNot(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel filterNot(ReceiveChannel $this$filterNot, CoroutineContext context, Function2 predicate) {
        return ChannelsKt.filter($this$filterNot, context, new ChannelsKt__DeprecatedKt$filterNot$1(predicate, null));
    }

    public static final <E> ReceiveChannel<E> filterNotNull(ReceiveChannel<? extends E> receiveChannel) {
        ReceiveChannel<E> filter$default = filter$default(receiveChannel, null, new ChannelsKt__DeprecatedKt$filterNotNull$1(null), 1, null);
        Intrinsics.checkNotNull(filter$default, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveChannel<E of kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNull>");
        return filter$default;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0080 A[Catch: all -> 0x009d, TryCatch #3 {all -> 0x009d, blocks: (B:16:0x0078, B:18:0x0080, B:20:0x0087, B:27:0x0093), top: B:15:0x0078 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0093 A[Catch: all -> 0x009d, TRY_LEAVE, TryCatch #3 {all -> 0x009d, blocks: (B:16:0x0078, B:18:0x0080, B:20:0x0087, B:27:0x0093), top: B:15:0x0078 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x006f -> B:15:0x0078). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object filterNotNullTo(ReceiveChannel $this$consumeEach$iv, Collection destination, Continuation $completion) {
        ChannelsKt__DeprecatedKt$filterNotNullTo$1 channelsKt__DeprecatedKt$filterNotNullTo$1;
        ReceiveChannel $this$consume$iv$iv;
        Throwable cause$iv$iv;
        Object $result;
        Collection destination2;
        ReceiveChannel $this$consume$iv$iv2;
        Throwable cause$iv$iv2;
        ChannelIterator channelIterator;
        int i;
        Object obj;
        if ($completion instanceof ChannelsKt__DeprecatedKt$filterNotNullTo$1) {
            channelsKt__DeprecatedKt$filterNotNullTo$1 = (ChannelsKt__DeprecatedKt$filterNotNullTo$1) $completion;
            if ((channelsKt__DeprecatedKt$filterNotNullTo$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$filterNotNullTo$1.label -= Integer.MIN_VALUE;
                Object $result2 = channelsKt__DeprecatedKt$filterNotNullTo$1.result;
                Object $result3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__DeprecatedKt$filterNotNullTo$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result2);
                        $this$consume$iv$iv = $this$consumeEach$iv;
                        Throwable cause$iv$iv3 = null;
                        try {
                            ChannelIterator it = $this$consume$iv$iv.iterator();
                            int $i$f$consumeEach = 0;
                            Collection destination3 = destination;
                            channelsKt__DeprecatedKt$filterNotNullTo$1.L$0 = destination3;
                            channelsKt__DeprecatedKt$filterNotNullTo$1.L$1 = $this$consume$iv$iv;
                            channelsKt__DeprecatedKt$filterNotNullTo$1.L$2 = it;
                            channelsKt__DeprecatedKt$filterNotNullTo$1.label = 1;
                            Object hasNext = it.hasNext(channelsKt__DeprecatedKt$filterNotNullTo$1);
                            if (hasNext != $result3) {
                                return $result3;
                            }
                            Object obj2 = $result3;
                            $result = $result2;
                            $result2 = hasNext;
                            destination2 = destination3;
                            $this$consume$iv$iv2 = $this$consume$iv$iv;
                            cause$iv$iv2 = cause$iv$iv3;
                            channelIterator = it;
                            i = $i$f$consumeEach;
                            obj = obj2;
                            try {
                                if (((Boolean) $result2).booleanValue()) {
                                    Object it2 = channelIterator.next();
                                    if (it2 != null) {
                                        destination2.add(it2);
                                    }
                                    $result2 = $result;
                                    $result3 = obj;
                                    $i$f$consumeEach = i;
                                    it = channelIterator;
                                    cause$iv$iv3 = cause$iv$iv;
                                    $this$consume$iv$iv = $this$consume$iv$iv;
                                    destination3 = destination2;
                                    channelsKt__DeprecatedKt$filterNotNullTo$1.L$0 = destination3;
                                    channelsKt__DeprecatedKt$filterNotNullTo$1.L$1 = $this$consume$iv$iv;
                                    channelsKt__DeprecatedKt$filterNotNullTo$1.L$2 = it;
                                    channelsKt__DeprecatedKt$filterNotNullTo$1.label = 1;
                                    Object hasNext2 = it.hasNext(channelsKt__DeprecatedKt$filterNotNullTo$1);
                                    if (hasNext2 != $result3) {
                                    }
                                } else {
                                    Unit unit = Unit.INSTANCE;
                                    return destination2;
                                }
                            } catch (Throwable th) {
                                $this$consume$iv$iv = $this$consume$iv$iv;
                                e$iv$iv = th;
                                cause$iv$iv = e$iv$iv;
                                try {
                                    throw e$iv$iv;
                                } finally {
                                    ChannelsKt.cancelConsumed($this$consume$iv$iv, cause$iv$iv);
                                }
                            }
                        } catch (Throwable th2) {
                            e$iv$iv = th2;
                            cause$iv$iv = e$iv$iv;
                            throw e$iv$iv;
                        }
                    case 1:
                        ChannelIterator channelIterator2 = (ChannelIterator) channelsKt__DeprecatedKt$filterNotNullTo$1.L$2;
                        $this$consume$iv$iv = (ReceiveChannel) channelsKt__DeprecatedKt$filterNotNullTo$1.L$1;
                        Collection destination4 = (Collection) channelsKt__DeprecatedKt$filterNotNullTo$1.L$0;
                        try {
                            ResultKt.throwOnFailure($result2);
                            destination2 = destination4;
                            $this$consume$iv$iv2 = $this$consume$iv$iv;
                            cause$iv$iv2 = null;
                            channelIterator = channelIterator2;
                            i = 0;
                            obj = $result3;
                            $result = $result2;
                            if (((Boolean) $result2).booleanValue()) {
                            }
                        } catch (Throwable th3) {
                            e$iv$iv = th3;
                            cause$iv$iv = e$iv$iv;
                            throw e$iv$iv;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$filterNotNullTo$1 = new ChannelsKt__DeprecatedKt$filterNotNullTo$1($completion);
        Object $result22 = channelsKt__DeprecatedKt$filterNotNullTo$1.result;
        Object $result32 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__DeprecatedKt$filterNotNullTo$1.label) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0083 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0093 A[Catch: all -> 0x00c9, TryCatch #1 {all -> 0x00c9, blocks: (B:22:0x008b, B:24:0x0093, B:26:0x009a, B:31:0x00bf), top: B:21:0x008b }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00bf A[Catch: all -> 0x00c9, TRY_LEAVE, TryCatch #1 {all -> 0x00c9, blocks: (B:22:0x008b, B:24:0x0093, B:26:0x009a, B:31:0x00bf), top: B:21:0x008b }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x00aa -> B:14:0x00af). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00b9 -> B:15:0x00be). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object filterNotNullTo(ReceiveChannel $this$consumeEach$iv, SendChannel destination, Continuation $completion) {
        ChannelsKt__DeprecatedKt$filterNotNullTo$3 channelsKt__DeprecatedKt$filterNotNullTo$3;
        ReceiveChannel $this$consume$iv$iv;
        Throwable cause$iv$iv;
        int i;
        ChannelIterator it;
        Continuation $completion2;
        Object obj;
        Object $result;
        ChannelsKt__DeprecatedKt$filterNotNullTo$3 channelsKt__DeprecatedKt$filterNotNullTo$32;
        Continuation $continuation;
        Continuation continuation;
        ChannelIterator channelIterator;
        SendChannel destination2;
        int i2;
        ChannelIterator channelIterator2;
        Object hasNext;
        try {
            if ($completion instanceof ChannelsKt__DeprecatedKt$filterNotNullTo$3) {
                channelsKt__DeprecatedKt$filterNotNullTo$3 = (ChannelsKt__DeprecatedKt$filterNotNullTo$3) $completion;
                if ((channelsKt__DeprecatedKt$filterNotNullTo$3.label & Integer.MIN_VALUE) != 0) {
                    channelsKt__DeprecatedKt$filterNotNullTo$3.label -= Integer.MIN_VALUE;
                    Object $result2 = channelsKt__DeprecatedKt$filterNotNullTo$3.result;
                    Object $result3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (channelsKt__DeprecatedKt$filterNotNullTo$3.label) {
                        case 0:
                            ResultKt.throwOnFailure($result2);
                            $this$consume$iv$iv = $this$consumeEach$iv;
                            cause$iv$iv = null;
                            i = 0;
                            try {
                                it = $this$consume$iv$iv.iterator();
                                $completion2 = null;
                                obj = $result3;
                                $result = $result2;
                                channelsKt__DeprecatedKt$filterNotNullTo$32 = channelsKt__DeprecatedKt$filterNotNullTo$3;
                                $continuation = $completion;
                            } catch (Throwable th) {
                                e$iv$iv = th;
                                Throwable cause$iv$iv2 = e$iv$iv;
                                try {
                                    throw e$iv$iv;
                                } finally {
                                    ChannelsKt.cancelConsumed($this$consume$iv$iv, cause$iv$iv2);
                                }
                            }
                            try {
                                channelsKt__DeprecatedKt$filterNotNullTo$32.L$0 = destination;
                                channelsKt__DeprecatedKt$filterNotNullTo$32.L$1 = $this$consume$iv$iv;
                                channelsKt__DeprecatedKt$filterNotNullTo$32.L$2 = it;
                                channelsKt__DeprecatedKt$filterNotNullTo$32.label = 1;
                                hasNext = it.hasNext(channelsKt__DeprecatedKt$filterNotNullTo$32);
                                if (hasNext != obj) {
                                    return obj;
                                }
                                ChannelIterator channelIterator3 = it;
                                destination2 = destination;
                                continuation = $completion2;
                                $completion = $continuation;
                                channelsKt__DeprecatedKt$filterNotNullTo$3 = channelsKt__DeprecatedKt$filterNotNullTo$32;
                                $result2 = hasNext;
                                channelIterator = channelIterator3;
                                try {
                                    if (!((Boolean) $result2).booleanValue()) {
                                        Unit unit = Unit.INSTANCE;
                                        return destination2;
                                    }
                                    Object it2 = channelIterator.next();
                                    if (it2 != null) {
                                        channelsKt__DeprecatedKt$filterNotNullTo$3.L$0 = destination2;
                                        channelsKt__DeprecatedKt$filterNotNullTo$3.L$1 = $this$consume$iv$iv;
                                        channelsKt__DeprecatedKt$filterNotNullTo$3.L$2 = channelIterator;
                                        channelsKt__DeprecatedKt$filterNotNullTo$3.label = 2;
                                        if (destination2.send(it2, channelsKt__DeprecatedKt$filterNotNullTo$3) == obj) {
                                            return obj;
                                        }
                                        $result2 = $result;
                                        $result3 = obj;
                                        i2 = i;
                                        channelIterator2 = channelIterator;
                                        Continuation continuation2 = $completion;
                                        $completion2 = continuation;
                                        destination = destination2;
                                        it = channelIterator2;
                                        i = i2;
                                        obj = $result3;
                                        $result = $result2;
                                        channelsKt__DeprecatedKt$filterNotNullTo$32 = channelsKt__DeprecatedKt$filterNotNullTo$3;
                                        $continuation = continuation2;
                                        channelsKt__DeprecatedKt$filterNotNullTo$32.L$0 = destination;
                                        channelsKt__DeprecatedKt$filterNotNullTo$32.L$1 = $this$consume$iv$iv;
                                        channelsKt__DeprecatedKt$filterNotNullTo$32.L$2 = it;
                                        channelsKt__DeprecatedKt$filterNotNullTo$32.label = 1;
                                        hasNext = it.hasNext(channelsKt__DeprecatedKt$filterNotNullTo$32);
                                        if (hasNext != obj) {
                                        }
                                    } else {
                                        channelsKt__DeprecatedKt$filterNotNullTo$32 = channelsKt__DeprecatedKt$filterNotNullTo$3;
                                        $continuation = $completion;
                                        $completion2 = continuation;
                                        destination = destination2;
                                        it = channelIterator;
                                        channelsKt__DeprecatedKt$filterNotNullTo$32.L$0 = destination;
                                        channelsKt__DeprecatedKt$filterNotNullTo$32.L$1 = $this$consume$iv$iv;
                                        channelsKt__DeprecatedKt$filterNotNullTo$32.L$2 = it;
                                        channelsKt__DeprecatedKt$filterNotNullTo$32.label = 1;
                                        hasNext = it.hasNext(channelsKt__DeprecatedKt$filterNotNullTo$32);
                                        if (hasNext != obj) {
                                        }
                                    }
                                } catch (Throwable th2) {
                                    e$iv$iv = th2;
                                    Throwable cause$iv$iv22 = e$iv$iv;
                                    throw e$iv$iv;
                                }
                            } catch (Throwable th3) {
                                e$iv$iv = th3;
                                Throwable cause$iv$iv222 = e$iv$iv;
                                throw e$iv$iv;
                            }
                        case 1:
                            continuation = null;
                            channelIterator = (ChannelIterator) channelsKt__DeprecatedKt$filterNotNullTo$3.L$2;
                            cause$iv$iv = null;
                            ReceiveChannel $this$consume$iv$iv2 = (ReceiveChannel) channelsKt__DeprecatedKt$filterNotNullTo$3.L$1;
                            $this$consume$iv$iv = $this$consume$iv$iv2;
                            SendChannel destination3 = (SendChannel) channelsKt__DeprecatedKt$filterNotNullTo$3.L$0;
                            ResultKt.throwOnFailure($result2);
                            destination2 = destination3;
                            i = 0;
                            obj = $result3;
                            $result = $result2;
                            if (!((Boolean) $result2).booleanValue()) {
                            }
                            break;
                        case 2:
                            continuation = null;
                            i2 = 0;
                            channelIterator2 = (ChannelIterator) channelsKt__DeprecatedKt$filterNotNullTo$3.L$2;
                            cause$iv$iv = null;
                            $this$consume$iv$iv = (ReceiveChannel) channelsKt__DeprecatedKt$filterNotNullTo$3.L$1;
                            destination2 = (SendChannel) channelsKt__DeprecatedKt$filterNotNullTo$3.L$0;
                            ResultKt.throwOnFailure($result2);
                            Continuation continuation22 = $completion;
                            $completion2 = continuation;
                            destination = destination2;
                            it = channelIterator2;
                            i = i2;
                            obj = $result3;
                            $result = $result2;
                            channelsKt__DeprecatedKt$filterNotNullTo$32 = channelsKt__DeprecatedKt$filterNotNullTo$3;
                            $continuation = continuation22;
                            channelsKt__DeprecatedKt$filterNotNullTo$32.L$0 = destination;
                            channelsKt__DeprecatedKt$filterNotNullTo$32.L$1 = $this$consume$iv$iv;
                            channelsKt__DeprecatedKt$filterNotNullTo$32.L$2 = it;
                            channelsKt__DeprecatedKt$filterNotNullTo$32.label = 1;
                            hasNext = it.hasNext(channelsKt__DeprecatedKt$filterNotNullTo$32);
                            if (hasNext != obj) {
                            }
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }
            switch (channelsKt__DeprecatedKt$filterNotNullTo$3.label) {
            }
        } catch (Throwable th4) {
            e$iv$iv = th4;
        }
        channelsKt__DeprecatedKt$filterNotNullTo$3 = new ChannelsKt__DeprecatedKt$filterNotNullTo$3($completion);
        Object $result22 = channelsKt__DeprecatedKt$filterNotNullTo$3.result;
        Object $result32 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }

    public static /* synthetic */ ReceiveChannel take$default(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return take(receiveChannel, i, coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel take(ReceiveChannel $this$take, int n, CoroutineContext context) {
        ReceiveChannel produce;
        produce = ProduceKt.produce(GlobalScope.INSTANCE, (r12 & 1) != 0 ? EmptyCoroutineContext.INSTANCE : context, (r12 & 2) != 0 ? 0 : 0, (r12 & 4) != 0 ? CoroutineStart.DEFAULT : null, (r12 & 8) != 0 ? null : ChannelsKt.consumes($this$take), new ChannelsKt__DeprecatedKt$take$1(n, $this$take, null));
        return produce;
    }

    public static /* synthetic */ ReceiveChannel takeWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return takeWhile(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel takeWhile(ReceiveChannel $this$takeWhile, CoroutineContext context, Function2 predicate) {
        ReceiveChannel produce;
        produce = ProduceKt.produce(GlobalScope.INSTANCE, (r12 & 1) != 0 ? EmptyCoroutineContext.INSTANCE : context, (r12 & 2) != 0 ? 0 : 0, (r12 & 4) != 0 ? CoroutineStart.DEFAULT : null, (r12 & 8) != 0 ? null : ChannelsKt.consumes($this$takeWhile), new ChannelsKt__DeprecatedKt$takeWhile$1($this$takeWhile, predicate, null));
        return produce;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x008c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x009c A[Catch: all -> 0x00c3, TryCatch #1 {all -> 0x00c3, blocks: (B:22:0x0094, B:24:0x009c, B:28:0x00b9), top: B:21:0x0094 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b9 A[Catch: all -> 0x00c3, TRY_LEAVE, TryCatch #1 {all -> 0x00c3, blocks: (B:22:0x0094, B:24:0x009c, B:28:0x00b9), top: B:21:0x0094 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [java.lang.Object, kotlinx.coroutines.channels.SendChannel] */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v8, types: [kotlinx.coroutines.channels.SendChannel] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00b1 -> B:15:0x00b7). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <E, C extends SendChannel<? super E>> Object toChannel(ReceiveChannel<? extends E> receiveChannel, C c, Continuation<? super C> continuation) {
        ChannelsKt__DeprecatedKt$toChannel$1 channelsKt__DeprecatedKt$toChannel$1;
        ReceiveChannel $this$consume$iv$iv;
        Throwable cause$iv$iv;
        ChannelIterator<? extends E> it;
        Continuation $completion;
        Object obj;
        Object $result;
        ChannelsKt__DeprecatedKt$toChannel$1 channelsKt__DeprecatedKt$toChannel$12;
        Continuation<? super C> continuation2;
        Continuation continuation3;
        ChannelIterator<? extends E> channelIterator;
        ?? r8;
        Object hasNext;
        try {
            if (continuation instanceof ChannelsKt__DeprecatedKt$toChannel$1) {
                channelsKt__DeprecatedKt$toChannel$1 = (ChannelsKt__DeprecatedKt$toChannel$1) continuation;
                if ((channelsKt__DeprecatedKt$toChannel$1.label & Integer.MIN_VALUE) != 0) {
                    channelsKt__DeprecatedKt$toChannel$1.label -= Integer.MIN_VALUE;
                    Object $result2 = channelsKt__DeprecatedKt$toChannel$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (channelsKt__DeprecatedKt$toChannel$1.label) {
                        case 0:
                            ResultKt.throwOnFailure($result2);
                            $this$consume$iv$iv = receiveChannel;
                            cause$iv$iv = null;
                            try {
                                it = $this$consume$iv$iv.iterator();
                                $completion = null;
                                obj = coroutine_suspended;
                                $result = $result2;
                                channelsKt__DeprecatedKt$toChannel$12 = channelsKt__DeprecatedKt$toChannel$1;
                                continuation2 = continuation;
                            } catch (Throwable th) {
                                e$iv$iv = th;
                                Throwable cause$iv$iv2 = e$iv$iv;
                                try {
                                    throw e$iv$iv;
                                } finally {
                                    ChannelsKt.cancelConsumed($this$consume$iv$iv, cause$iv$iv2);
                                }
                            }
                            try {
                                channelsKt__DeprecatedKt$toChannel$12.L$0 = c;
                                channelsKt__DeprecatedKt$toChannel$12.L$1 = $this$consume$iv$iv;
                                channelsKt__DeprecatedKt$toChannel$12.L$2 = it;
                                channelsKt__DeprecatedKt$toChannel$12.label = 1;
                                hasNext = it.hasNext(channelsKt__DeprecatedKt$toChannel$12);
                                if (hasNext == obj) {
                                    return obj;
                                }
                                ChannelIterator<? extends E> channelIterator2 = it;
                                r8 = c;
                                continuation3 = $completion;
                                continuation = continuation2;
                                channelsKt__DeprecatedKt$toChannel$1 = channelsKt__DeprecatedKt$toChannel$12;
                                $result2 = hasNext;
                                channelIterator = channelIterator2;
                                try {
                                    if (((Boolean) $result2).booleanValue()) {
                                        Unit unit = Unit.INSTANCE;
                                        return r8;
                                    }
                                    E next = channelIterator.next();
                                    channelsKt__DeprecatedKt$toChannel$1.L$0 = r8;
                                    channelsKt__DeprecatedKt$toChannel$1.L$1 = $this$consume$iv$iv;
                                    channelsKt__DeprecatedKt$toChannel$1.L$2 = channelIterator;
                                    channelsKt__DeprecatedKt$toChannel$1.label = 2;
                                    if (r8.send(next, channelsKt__DeprecatedKt$toChannel$1) == obj) {
                                        return obj;
                                    }
                                    channelsKt__DeprecatedKt$toChannel$12 = channelsKt__DeprecatedKt$toChannel$1;
                                    continuation2 = continuation;
                                    $completion = continuation3;
                                    c = r8;
                                    it = channelIterator;
                                    channelsKt__DeprecatedKt$toChannel$12.L$0 = c;
                                    channelsKt__DeprecatedKt$toChannel$12.L$1 = $this$consume$iv$iv;
                                    channelsKt__DeprecatedKt$toChannel$12.L$2 = it;
                                    channelsKt__DeprecatedKt$toChannel$12.label = 1;
                                    hasNext = it.hasNext(channelsKt__DeprecatedKt$toChannel$12);
                                    if (hasNext == obj) {
                                    }
                                } catch (Throwable th2) {
                                    e$iv$iv = th2;
                                    Throwable cause$iv$iv22 = e$iv$iv;
                                    throw e$iv$iv;
                                }
                            } catch (Throwable th3) {
                                e$iv$iv = th3;
                                Throwable cause$iv$iv222 = e$iv$iv;
                                throw e$iv$iv;
                            }
                        case 1:
                            continuation3 = null;
                            channelIterator = (ChannelIterator) channelsKt__DeprecatedKt$toChannel$1.L$2;
                            cause$iv$iv = null;
                            ReceiveChannel $this$consume$iv$iv2 = (ReceiveChannel) channelsKt__DeprecatedKt$toChannel$1.L$1;
                            $this$consume$iv$iv = $this$consume$iv$iv2;
                            SendChannel destination = (SendChannel) channelsKt__DeprecatedKt$toChannel$1.L$0;
                            ResultKt.throwOnFailure($result2);
                            r8 = destination;
                            obj = coroutine_suspended;
                            $result = $result2;
                            if (((Boolean) $result2).booleanValue()) {
                            }
                            break;
                        case 2:
                            ChannelIterator<? extends E> channelIterator3 = (ChannelIterator) channelsKt__DeprecatedKt$toChannel$1.L$2;
                            cause$iv$iv = null;
                            $this$consume$iv$iv = (ReceiveChannel) channelsKt__DeprecatedKt$toChannel$1.L$1;
                            ?? r82 = (SendChannel) channelsKt__DeprecatedKt$toChannel$1.L$0;
                            ResultKt.throwOnFailure($result2);
                            $completion = null;
                            c = r82;
                            it = channelIterator3;
                            obj = coroutine_suspended;
                            $result = $result2;
                            channelsKt__DeprecatedKt$toChannel$12 = channelsKt__DeprecatedKt$toChannel$1;
                            continuation2 = continuation;
                            channelsKt__DeprecatedKt$toChannel$12.L$0 = c;
                            channelsKt__DeprecatedKt$toChannel$12.L$1 = $this$consume$iv$iv;
                            channelsKt__DeprecatedKt$toChannel$12.L$2 = it;
                            channelsKt__DeprecatedKt$toChannel$12.label = 1;
                            hasNext = it.hasNext(channelsKt__DeprecatedKt$toChannel$12);
                            if (hasNext == obj) {
                            }
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }
            switch (channelsKt__DeprecatedKt$toChannel$1.label) {
            }
        } catch (Throwable th4) {
            e$iv$iv = th4;
        }
        channelsKt__DeprecatedKt$toChannel$1 = new ChannelsKt__DeprecatedKt$toChannel$1(continuation);
        Object $result22 = channelsKt__DeprecatedKt$toChannel$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0080 A[Catch: all -> 0x009c, TryCatch #3 {all -> 0x009c, blocks: (B:16:0x0078, B:18:0x0080, B:24:0x0092), top: B:15:0x0078 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0092 A[Catch: all -> 0x009c, TRY_LEAVE, TryCatch #3 {all -> 0x009c, blocks: (B:16:0x0078, B:18:0x0080, B:24:0x0092), top: B:15:0x0078 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x006f -> B:15:0x0078). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <E, C extends Collection<? super E>> Object toCollection(ReceiveChannel<? extends E> receiveChannel, C c, Continuation<? super C> continuation) {
        ChannelsKt__DeprecatedKt$toCollection$1 channelsKt__DeprecatedKt$toCollection$1;
        ReceiveChannel $this$consume$iv$iv;
        Throwable cause$iv$iv;
        Object $result;
        Collection collection;
        ReceiveChannel $this$consume$iv$iv2;
        Throwable cause$iv$iv2;
        ChannelIterator channelIterator;
        int i;
        Object obj;
        if (continuation instanceof ChannelsKt__DeprecatedKt$toCollection$1) {
            channelsKt__DeprecatedKt$toCollection$1 = (ChannelsKt__DeprecatedKt$toCollection$1) continuation;
            if ((channelsKt__DeprecatedKt$toCollection$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$toCollection$1.label -= Integer.MIN_VALUE;
                Object e$iv = channelsKt__DeprecatedKt$toCollection$1.result;
                Object $result2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__DeprecatedKt$toCollection$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(e$iv);
                        $this$consume$iv$iv = receiveChannel;
                        Throwable cause$iv$iv3 = null;
                        try {
                            ChannelIterator it = $this$consume$iv$iv.iterator();
                            int $i$f$consumeEach = 0;
                            Collection destination = c;
                            channelsKt__DeprecatedKt$toCollection$1.L$0 = destination;
                            channelsKt__DeprecatedKt$toCollection$1.L$1 = $this$consume$iv$iv;
                            channelsKt__DeprecatedKt$toCollection$1.L$2 = it;
                            channelsKt__DeprecatedKt$toCollection$1.label = 1;
                            Object hasNext = it.hasNext(channelsKt__DeprecatedKt$toCollection$1);
                            if (hasNext != $result2) {
                                return $result2;
                            }
                            Object obj2 = $result2;
                            $result = e$iv;
                            e$iv = hasNext;
                            collection = destination;
                            $this$consume$iv$iv2 = $this$consume$iv$iv;
                            cause$iv$iv2 = cause$iv$iv3;
                            channelIterator = it;
                            i = $i$f$consumeEach;
                            obj = obj2;
                            try {
                                if (!((Boolean) e$iv).booleanValue()) {
                                    collection.add(channelIterator.next());
                                    e$iv = $result;
                                    $result2 = obj;
                                    $i$f$consumeEach = i;
                                    it = channelIterator;
                                    cause$iv$iv3 = cause$iv$iv;
                                    $this$consume$iv$iv = $this$consume$iv$iv;
                                    destination = collection;
                                    channelsKt__DeprecatedKt$toCollection$1.L$0 = destination;
                                    channelsKt__DeprecatedKt$toCollection$1.L$1 = $this$consume$iv$iv;
                                    channelsKt__DeprecatedKt$toCollection$1.L$2 = it;
                                    channelsKt__DeprecatedKt$toCollection$1.label = 1;
                                    Object hasNext2 = it.hasNext(channelsKt__DeprecatedKt$toCollection$1);
                                    if (hasNext2 != $result2) {
                                    }
                                } else {
                                    Unit unit = Unit.INSTANCE;
                                    return collection;
                                }
                            } catch (Throwable th) {
                                $this$consume$iv$iv = $this$consume$iv$iv;
                                e$iv$iv = th;
                                cause$iv$iv = e$iv$iv;
                                try {
                                    throw e$iv$iv;
                                } finally {
                                    ChannelsKt.cancelConsumed($this$consume$iv$iv, cause$iv$iv);
                                }
                            }
                        } catch (Throwable th2) {
                            e$iv$iv = th2;
                            cause$iv$iv = e$iv$iv;
                            throw e$iv$iv;
                        }
                    case 1:
                        ChannelIterator channelIterator2 = (ChannelIterator) channelsKt__DeprecatedKt$toCollection$1.L$2;
                        $this$consume$iv$iv = (ReceiveChannel) channelsKt__DeprecatedKt$toCollection$1.L$1;
                        Collection destination2 = (Collection) channelsKt__DeprecatedKt$toCollection$1.L$0;
                        try {
                            ResultKt.throwOnFailure(e$iv);
                            collection = destination2;
                            $this$consume$iv$iv2 = $this$consume$iv$iv;
                            cause$iv$iv2 = null;
                            channelIterator = channelIterator2;
                            i = 0;
                            obj = $result2;
                            $result = e$iv;
                            if (!((Boolean) e$iv).booleanValue()) {
                            }
                        } catch (Throwable th3) {
                            e$iv$iv = th3;
                            cause$iv$iv = e$iv$iv;
                            throw e$iv$iv;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$toCollection$1 = new ChannelsKt__DeprecatedKt$toCollection$1(continuation);
        Object e$iv2 = channelsKt__DeprecatedKt$toCollection$1.result;
        Object $result22 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__DeprecatedKt$toCollection$1.label) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008d A[Catch: all -> 0x00b4, TryCatch #1 {all -> 0x00b4, blocks: (B:16:0x0085, B:18:0x008d, B:33:0x00aa), top: B:15:0x0085 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0078 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00aa A[Catch: all -> 0x00b4, TRY_LEAVE, TryCatch #1 {all -> 0x00b4, blocks: (B:16:0x0085, B:18:0x008d, B:33:0x00aa), top: B:15:0x0085 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0079 -> B:15:0x0085). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <K, V, M extends Map<? super K, ? super V>> Object toMap(ReceiveChannel<? extends Pair<? extends K, ? extends V>> receiveChannel, M m, Continuation<? super M> continuation) {
        ChannelsKt__DeprecatedKt$toMap$2 channelsKt__DeprecatedKt$toMap$2;
        ChannelsKt__DeprecatedKt$toMap$2 channelsKt__DeprecatedKt$toMap$22;
        ReceiveChannel $this$consume$iv$iv;
        Throwable cause$iv$iv;
        Object $result;
        Map map;
        ReceiveChannel $this$consume$iv$iv2;
        Throwable cause$iv$iv2;
        ChannelIterator channelIterator;
        int i;
        Map map2;
        int $i$f$consume;
        if (continuation instanceof ChannelsKt__DeprecatedKt$toMap$2) {
            channelsKt__DeprecatedKt$toMap$2 = (ChannelsKt__DeprecatedKt$toMap$2) continuation;
            if ((channelsKt__DeprecatedKt$toMap$2.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$toMap$2.label -= Integer.MIN_VALUE;
                channelsKt__DeprecatedKt$toMap$22 = channelsKt__DeprecatedKt$toMap$2;
                Object e$iv = channelsKt__DeprecatedKt$toMap$22.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__DeprecatedKt$toMap$22.label) {
                    case 0:
                        ResultKt.throwOnFailure(e$iv);
                        $this$consume$iv$iv = receiveChannel;
                        Throwable cause$iv$iv3 = null;
                        try {
                            ChannelIterator it = $this$consume$iv$iv.iterator();
                            Map destination = null;
                            int $i$f$consume2 = 0;
                            int $i$f$consume3 = 0;
                            Map destination2 = m;
                            try {
                                channelsKt__DeprecatedKt$toMap$22.L$0 = destination2;
                                channelsKt__DeprecatedKt$toMap$22.L$1 = $this$consume$iv$iv;
                                channelsKt__DeprecatedKt$toMap$22.L$2 = it;
                                channelsKt__DeprecatedKt$toMap$22.label = 1;
                                Object hasNext = it.hasNext(channelsKt__DeprecatedKt$toMap$22);
                                if (hasNext != coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                int i2 = $i$f$consume2;
                                $result = e$iv;
                                e$iv = hasNext;
                                map = destination2;
                                $this$consume$iv$iv2 = $this$consume$iv$iv;
                                cause$iv$iv2 = cause$iv$iv3;
                                channelIterator = it;
                                i = $i$f$consume3;
                                map2 = destination;
                                $i$f$consume = i2;
                                try {
                                    if (!((Boolean) e$iv).booleanValue()) {
                                        Pair it2 = (Pair) channelIterator.next();
                                        map.put(it2.getFirst(), it2.getSecond());
                                        e$iv = $result;
                                        $i$f$consume2 = $i$f$consume;
                                        destination = map2;
                                        $i$f$consume3 = i;
                                        it = channelIterator;
                                        cause$iv$iv3 = cause$iv$iv;
                                        $this$consume$iv$iv = $this$consume$iv$iv;
                                        destination2 = map;
                                        channelsKt__DeprecatedKt$toMap$22.L$0 = destination2;
                                        channelsKt__DeprecatedKt$toMap$22.L$1 = $this$consume$iv$iv;
                                        channelsKt__DeprecatedKt$toMap$22.L$2 = it;
                                        channelsKt__DeprecatedKt$toMap$22.label = 1;
                                        Object hasNext2 = it.hasNext(channelsKt__DeprecatedKt$toMap$22);
                                        if (hasNext2 != coroutine_suspended) {
                                        }
                                    } else {
                                        Unit unit = Unit.INSTANCE;
                                        return map;
                                    }
                                } catch (Throwable th) {
                                    e$iv$iv = th;
                                    $this$consume$iv$iv = $this$consume$iv$iv;
                                    cause$iv$iv = e$iv$iv;
                                    try {
                                        throw e$iv$iv;
                                    } finally {
                                        ChannelsKt.cancelConsumed($this$consume$iv$iv, cause$iv$iv);
                                    }
                                }
                            } catch (Throwable th2) {
                                e$iv$iv = th2;
                                cause$iv$iv = e$iv$iv;
                                throw e$iv$iv;
                            }
                        } catch (Throwable th3) {
                            e$iv$iv = th3;
                            cause$iv$iv = e$iv$iv;
                            throw e$iv$iv;
                        }
                    case 1:
                        ChannelIterator channelIterator2 = (ChannelIterator) channelsKt__DeprecatedKt$toMap$22.L$2;
                        $this$consume$iv$iv = (ReceiveChannel) channelsKt__DeprecatedKt$toMap$22.L$1;
                        Map destination3 = (Map) channelsKt__DeprecatedKt$toMap$22.L$0;
                        try {
                            ResultKt.throwOnFailure(e$iv);
                            map = destination3;
                            $this$consume$iv$iv2 = $this$consume$iv$iv;
                            cause$iv$iv2 = null;
                            channelIterator = channelIterator2;
                            i = 0;
                            map2 = null;
                            $i$f$consume = 0;
                            $result = e$iv;
                            if (!((Boolean) e$iv).booleanValue()) {
                            }
                        } catch (Throwable th4) {
                            e$iv$iv = th4;
                            cause$iv$iv = e$iv$iv;
                            throw e$iv$iv;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$toMap$2 = new ChannelsKt__DeprecatedKt$toMap$2(continuation);
        channelsKt__DeprecatedKt$toMap$22 = channelsKt__DeprecatedKt$toMap$2;
        Object e$iv2 = channelsKt__DeprecatedKt$toMap$22.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__DeprecatedKt$toMap$22.label) {
        }
    }

    public static /* synthetic */ ReceiveChannel flatMap$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return flatMap(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel flatMap(ReceiveChannel $this$flatMap, CoroutineContext context, Function2 transform) {
        ReceiveChannel produce;
        produce = ProduceKt.produce(GlobalScope.INSTANCE, (r12 & 1) != 0 ? EmptyCoroutineContext.INSTANCE : context, (r12 & 2) != 0 ? 0 : 0, (r12 & 4) != 0 ? CoroutineStart.DEFAULT : null, (r12 & 8) != 0 ? null : ChannelsKt.consumes($this$flatMap), new ChannelsKt__DeprecatedKt$flatMap$1($this$flatMap, transform, null));
        return produce;
    }

    public static /* synthetic */ ReceiveChannel map$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.map(receiveChannel, coroutineContext, function2);
    }

    public static final <E, R> ReceiveChannel<R> map(ReceiveChannel<? extends E> receiveChannel, CoroutineContext context, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        ReceiveChannel<R> produce;
        produce = ProduceKt.produce(GlobalScope.INSTANCE, (r12 & 1) != 0 ? EmptyCoroutineContext.INSTANCE : context, (r12 & 2) != 0 ? 0 : 0, (r12 & 4) != 0 ? CoroutineStart.DEFAULT : null, (r12 & 8) != 0 ? null : ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$map$1(receiveChannel, function2, null));
        return produce;
    }

    public static /* synthetic */ ReceiveChannel mapIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.mapIndexed(receiveChannel, coroutineContext, function3);
    }

    public static final <E, R> ReceiveChannel<R> mapIndexed(ReceiveChannel<? extends E> receiveChannel, CoroutineContext context, Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3) {
        ReceiveChannel<R> produce;
        produce = ProduceKt.produce(GlobalScope.INSTANCE, (r12 & 1) != 0 ? EmptyCoroutineContext.INSTANCE : context, (r12 & 2) != 0 ? 0 : 0, (r12 & 4) != 0 ? CoroutineStart.DEFAULT : null, (r12 & 8) != 0 ? null : ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$mapIndexed$1(receiveChannel, function3, null));
        return produce;
    }

    public static /* synthetic */ ReceiveChannel mapIndexedNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return mapIndexedNotNull(receiveChannel, coroutineContext, function3);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel mapIndexedNotNull(ReceiveChannel $this$mapIndexedNotNull, CoroutineContext context, Function3 transform) {
        return ChannelsKt.filterNotNull(ChannelsKt.mapIndexed($this$mapIndexedNotNull, context, transform));
    }

    public static /* synthetic */ ReceiveChannel mapNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return mapNotNull(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel mapNotNull(ReceiveChannel $this$mapNotNull, CoroutineContext context, Function2 transform) {
        return ChannelsKt.filterNotNull(ChannelsKt.map($this$mapNotNull, context, transform));
    }

    public static /* synthetic */ ReceiveChannel withIndex$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return withIndex(receiveChannel, coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final /* synthetic */ ReceiveChannel withIndex(ReceiveChannel $this$withIndex, CoroutineContext context) {
        ReceiveChannel produce;
        produce = ProduceKt.produce(GlobalScope.INSTANCE, (r12 & 1) != 0 ? EmptyCoroutineContext.INSTANCE : context, (r12 & 2) != 0 ? 0 : 0, (r12 & 4) != 0 ? CoroutineStart.DEFAULT : null, (r12 & 8) != 0 ? null : ChannelsKt.consumes($this$withIndex), new ChannelsKt__DeprecatedKt$withIndex$1($this$withIndex, null));
        return produce;
    }

    public static /* synthetic */ ReceiveChannel distinctBy$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.distinctBy(receiveChannel, coroutineContext, function2);
    }

    public static final <E, K> ReceiveChannel<E> distinctBy(ReceiveChannel<? extends E> receiveChannel, CoroutineContext context, Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2) {
        ReceiveChannel<E> produce;
        produce = ProduceKt.produce(GlobalScope.INSTANCE, (r12 & 1) != 0 ? EmptyCoroutineContext.INSTANCE : context, (r12 & 2) != 0 ? 0 : 0, (r12 & 4) != 0 ? CoroutineStart.DEFAULT : null, (r12 & 8) != 0 ? null : ChannelsKt.consumes(receiveChannel), new ChannelsKt__DeprecatedKt$distinctBy$1(receiveChannel, function2, null));
        return produce;
    }

    public static final <E> Object toMutableSet(ReceiveChannel<? extends E> receiveChannel, Continuation<? super Set<E>> continuation) {
        return ChannelsKt.toCollection(receiveChannel, new LinkedHashSet(), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object any(ReceiveChannel $this$any, Continuation $completion) {
        ChannelsKt__DeprecatedKt$any$1 channelsKt__DeprecatedKt$any$1;
        ReceiveChannel $this$consume$iv;
        Throwable cause$iv;
        Object hasNext;
        if ($completion instanceof ChannelsKt__DeprecatedKt$any$1) {
            channelsKt__DeprecatedKt$any$1 = (ChannelsKt__DeprecatedKt$any$1) $completion;
            if ((channelsKt__DeprecatedKt$any$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$any$1.label -= Integer.MIN_VALUE;
                Object $result = channelsKt__DeprecatedKt$any$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__DeprecatedKt$any$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        $this$consume$iv = $this$any;
                        cause$iv = null;
                        try {
                            ChannelIterator it = $this$consume$iv.iterator();
                            channelsKt__DeprecatedKt$any$1.L$0 = $this$consume$iv;
                            channelsKt__DeprecatedKt$any$1.label = 1;
                            hasNext = it.hasNext(channelsKt__DeprecatedKt$any$1);
                            if (hasNext == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            return hasNext;
                        } catch (Throwable th) {
                            e$iv = th;
                            Throwable cause$iv2 = e$iv;
                            try {
                                throw e$iv;
                            } finally {
                                ChannelsKt.cancelConsumed($this$consume$iv, cause$iv2);
                            }
                        }
                    case 1:
                        $this$consume$iv = (ReceiveChannel) channelsKt__DeprecatedKt$any$1.L$0;
                        cause$iv = null;
                        try {
                            ResultKt.throwOnFailure($result);
                            hasNext = $result;
                            return hasNext;
                        } catch (Throwable th2) {
                            e$iv = th2;
                            Throwable cause$iv22 = e$iv;
                            throw e$iv;
                        }
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$any$1 = new ChannelsKt__DeprecatedKt$any$1($completion);
        Object $result2 = channelsKt__DeprecatedKt$any$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__DeprecatedKt$any$1.label) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0087 A[Catch: all -> 0x00aa, TryCatch #3 {all -> 0x00aa, blocks: (B:16:0x007f, B:18:0x0087, B:24:0x009a), top: B:15:0x007f }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0074 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x009a A[Catch: all -> 0x00aa, TRY_LEAVE, TryCatch #3 {all -> 0x00aa, blocks: (B:16:0x007f, B:18:0x0087, B:24:0x009a), top: B:15:0x007f }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0075 -> B:15:0x007f). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object count(ReceiveChannel $this$consumeEach$iv, Continuation $completion) {
        ChannelsKt__DeprecatedKt$count$1 channelsKt__DeprecatedKt$count$1;
        ReceiveChannel $this$consume$iv$iv;
        Object $result;
        Ref.IntRef count;
        ReceiveChannel $this$consume$iv$iv2;
        Throwable cause$iv$iv;
        ChannelIterator channelIterator;
        int i;
        Ref.IntRef intRef;
        Object obj;
        if ($completion instanceof ChannelsKt__DeprecatedKt$count$1) {
            channelsKt__DeprecatedKt$count$1 = (ChannelsKt__DeprecatedKt$count$1) $completion;
            if ((channelsKt__DeprecatedKt$count$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$count$1.label -= Integer.MIN_VALUE;
                Object e$iv = channelsKt__DeprecatedKt$count$1.result;
                Object $result2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__DeprecatedKt$count$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(e$iv);
                        Ref.IntRef count2 = new Ref.IntRef();
                        $this$consume$iv$iv = $this$consumeEach$iv;
                        Throwable cause$iv$iv2 = null;
                        try {
                            ChannelIterator it = $this$consume$iv$iv.iterator();
                            Ref.IntRef count3 = null;
                            int $i$f$consume = 0;
                            Ref.IntRef count4 = count2;
                            channelsKt__DeprecatedKt$count$1.L$0 = count4;
                            channelsKt__DeprecatedKt$count$1.L$1 = $this$consume$iv$iv;
                            channelsKt__DeprecatedKt$count$1.L$2 = it;
                            channelsKt__DeprecatedKt$count$1.label = 1;
                            Object hasNext = it.hasNext(channelsKt__DeprecatedKt$count$1);
                            if (hasNext != $result2) {
                                return $result2;
                            }
                            Object obj2 = $result2;
                            $result = e$iv;
                            e$iv = hasNext;
                            count = count4;
                            $this$consume$iv$iv2 = $this$consume$iv$iv;
                            cause$iv$iv = cause$iv$iv2;
                            channelIterator = it;
                            i = $i$f$consume;
                            intRef = count3;
                            obj = obj2;
                            try {
                                if (!((Boolean) e$iv).booleanValue()) {
                                    channelIterator.next();
                                    count.element++;
                                    e$iv = $result;
                                    $result2 = obj;
                                    count3 = intRef;
                                    $i$f$consume = i;
                                    it = channelIterator;
                                    cause$iv$iv2 = cause$iv$iv;
                                    $this$consume$iv$iv = $this$consume$iv$iv2;
                                    count4 = count;
                                    channelsKt__DeprecatedKt$count$1.L$0 = count4;
                                    channelsKt__DeprecatedKt$count$1.L$1 = $this$consume$iv$iv;
                                    channelsKt__DeprecatedKt$count$1.L$2 = it;
                                    channelsKt__DeprecatedKt$count$1.label = 1;
                                    Object hasNext2 = it.hasNext(channelsKt__DeprecatedKt$count$1);
                                    if (hasNext2 != $result2) {
                                    }
                                } else {
                                    Unit unit = Unit.INSTANCE;
                                    ChannelsKt.cancelConsumed($this$consume$iv$iv2, cause$iv$iv);
                                    int $i$f$consumeEach = count.element;
                                    return Boxing.boxInt($i$f$consumeEach);
                                }
                            } catch (Throwable th) {
                                $this$consume$iv$iv = $this$consume$iv$iv2;
                                e$iv$iv = th;
                                Throwable cause$iv$iv3 = e$iv$iv;
                                try {
                                    throw e$iv$iv;
                                } catch (Throwable e$iv$iv) {
                                    ChannelsKt.cancelConsumed($this$consume$iv$iv, cause$iv$iv3);
                                    throw e$iv$iv;
                                }
                            }
                        } catch (Throwable th2) {
                            e$iv$iv = th2;
                            Throwable cause$iv$iv32 = e$iv$iv;
                            throw e$iv$iv;
                        }
                    case 1:
                        ChannelIterator channelIterator2 = (ChannelIterator) channelsKt__DeprecatedKt$count$1.L$2;
                        $this$consume$iv$iv = (ReceiveChannel) channelsKt__DeprecatedKt$count$1.L$1;
                        Ref.IntRef count5 = (Ref.IntRef) channelsKt__DeprecatedKt$count$1.L$0;
                        try {
                            ResultKt.throwOnFailure(e$iv);
                            count = count5;
                            $this$consume$iv$iv2 = $this$consume$iv$iv;
                            cause$iv$iv = null;
                            channelIterator = channelIterator2;
                            i = 0;
                            intRef = null;
                            obj = $result2;
                            $result = e$iv;
                            if (!((Boolean) e$iv).booleanValue()) {
                            }
                        } catch (Throwable th3) {
                            e$iv$iv = th3;
                            Throwable cause$iv$iv322 = e$iv$iv;
                            throw e$iv$iv;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$count$1 = new ChannelsKt__DeprecatedKt$count$1($completion);
        Object e$iv2 = channelsKt__DeprecatedKt$count$1.result;
        Object $result22 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__DeprecatedKt$count$1.label) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00c5 A[Catch: all -> 0x00e9, TRY_LEAVE, TryCatch #5 {all -> 0x00e9, blocks: (B:16:0x00bd, B:18:0x00c5), top: B:15:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00b4 -> B:15:0x00bd). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object maxWith(ReceiveChannel $this$consume$iv, Comparator comparator, Continuation $completion) {
        ChannelsKt__DeprecatedKt$maxWith$1 channelsKt__DeprecatedKt$maxWith$1;
        Throwable cause$iv;
        ReceiveChannel $this$consume$iv2;
        Throwable e$iv;
        ChannelIterator iterator;
        Object hasNext;
        ReceiveChannel receiveChannel;
        Comparator comparator2;
        Object $result;
        Comparator comparator3;
        ReceiveChannel $this$consume$iv3;
        ChannelIterator iterator2;
        Throwable th;
        Object max;
        Object max2;
        if ($completion instanceof ChannelsKt__DeprecatedKt$maxWith$1) {
            channelsKt__DeprecatedKt$maxWith$1 = (ChannelsKt__DeprecatedKt$maxWith$1) $completion;
            if ((channelsKt__DeprecatedKt$maxWith$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$maxWith$1.label -= Integer.MIN_VALUE;
                Object max3 = channelsKt__DeprecatedKt$maxWith$1.result;
                Object $result2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__DeprecatedKt$maxWith$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(max3);
                        cause$iv = null;
                        try {
                            iterator = $this$consume$iv.iterator();
                            channelsKt__DeprecatedKt$maxWith$1.L$0 = comparator;
                            channelsKt__DeprecatedKt$maxWith$1.L$1 = $this$consume$iv;
                            channelsKt__DeprecatedKt$maxWith$1.L$2 = iterator;
                            channelsKt__DeprecatedKt$maxWith$1.label = 1;
                            hasNext = iterator.hasNext(channelsKt__DeprecatedKt$maxWith$1);
                            if (hasNext == $result2) {
                                return $result2;
                            }
                            receiveChannel = $this$consume$iv;
                            comparator2 = comparator;
                            try {
                                if (((Boolean) hasNext).booleanValue()) {
                                    ChannelsKt.cancelConsumed(receiveChannel, cause$iv);
                                    return null;
                                }
                                ReceiveChannel $this$consume$iv4 = receiveChannel;
                                try {
                                    Throwable cause$iv2 = cause$iv;
                                    Object max4 = iterator.next();
                                    ReceiveChannel $this$consume$iv5 = $this$consume$iv4;
                                    try {
                                        channelsKt__DeprecatedKt$maxWith$1.L$0 = comparator2;
                                        channelsKt__DeprecatedKt$maxWith$1.L$1 = $this$consume$iv5;
                                        channelsKt__DeprecatedKt$maxWith$1.L$2 = iterator;
                                        channelsKt__DeprecatedKt$maxWith$1.L$3 = max4;
                                        channelsKt__DeprecatedKt$maxWith$1.label = 2;
                                        Object hasNext2 = iterator.hasNext(channelsKt__DeprecatedKt$maxWith$1);
                                        if (hasNext2 != $result2) {
                                            return $result2;
                                        }
                                        Object obj = $result2;
                                        $result = max3;
                                        max3 = hasNext2;
                                        comparator3 = comparator2;
                                        $this$consume$iv3 = $this$consume$iv5;
                                        iterator2 = iterator;
                                        th = cause$iv2;
                                        max = max4;
                                        max2 = obj;
                                        try {
                                            if (((Boolean) max3).booleanValue()) {
                                                ChannelsKt.cancelConsumed($this$consume$iv3, th);
                                                return max;
                                            }
                                            Object e = iterator2.next();
                                            if (comparator3.compare(max, e) < 0) {
                                                ReceiveChannel $this$consume$iv6 = $this$consume$iv3;
                                                comparator2 = comparator3;
                                                Object obj2 = max2;
                                                max4 = e;
                                                max3 = $result;
                                                $result2 = obj2;
                                                ChannelIterator channelIterator = iterator2;
                                                $this$consume$iv5 = $this$consume$iv6;
                                                cause$iv2 = th;
                                                iterator = channelIterator;
                                            } else {
                                                ReceiveChannel $this$consume$iv7 = $this$consume$iv3;
                                                comparator2 = comparator3;
                                                ChannelIterator channelIterator2 = iterator2;
                                                $this$consume$iv5 = $this$consume$iv7;
                                                max3 = $result;
                                                $result2 = max2;
                                                max4 = max;
                                                cause$iv2 = th;
                                                iterator = channelIterator2;
                                            }
                                            channelsKt__DeprecatedKt$maxWith$1.L$0 = comparator2;
                                            channelsKt__DeprecatedKt$maxWith$1.L$1 = $this$consume$iv5;
                                            channelsKt__DeprecatedKt$maxWith$1.L$2 = iterator;
                                            channelsKt__DeprecatedKt$maxWith$1.L$3 = max4;
                                            channelsKt__DeprecatedKt$maxWith$1.label = 2;
                                            Object hasNext22 = iterator.hasNext(channelsKt__DeprecatedKt$maxWith$1);
                                            if (hasNext22 != $result2) {
                                            }
                                        } catch (Throwable th2) {
                                            e$iv = th2;
                                            $this$consume$iv2 = $this$consume$iv3;
                                            Throwable cause$iv3 = e$iv;
                                            try {
                                                throw e$iv;
                                            } catch (Throwable e$iv2) {
                                                ChannelsKt.cancelConsumed($this$consume$iv2, cause$iv3);
                                                throw e$iv2;
                                            }
                                        }
                                    } catch (Throwable th3) {
                                        e$iv = th3;
                                        $this$consume$iv2 = $this$consume$iv5;
                                        Throwable cause$iv32 = e$iv;
                                        throw e$iv;
                                    }
                                } catch (Throwable th4) {
                                    e$iv = th4;
                                    $this$consume$iv2 = $this$consume$iv4;
                                    Throwable cause$iv322 = e$iv;
                                    throw e$iv;
                                }
                            } catch (Throwable th5) {
                                e$iv = th5;
                                $this$consume$iv2 = receiveChannel;
                            }
                        } catch (Throwable th6) {
                            $this$consume$iv2 = $this$consume$iv;
                            e$iv = th6;
                            Throwable cause$iv3222 = e$iv;
                            throw e$iv;
                        }
                    case 1:
                        ChannelIterator iterator3 = (ChannelIterator) channelsKt__DeprecatedKt$maxWith$1.L$2;
                        ReceiveChannel receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$maxWith$1.L$1;
                        Comparator comparator4 = (Comparator) channelsKt__DeprecatedKt$maxWith$1.L$0;
                        try {
                            ResultKt.throwOnFailure(max3);
                            hasNext = max3;
                            comparator2 = comparator4;
                            receiveChannel = receiveChannel2;
                            iterator = iterator3;
                            cause$iv = null;
                            if (((Boolean) hasNext).booleanValue()) {
                            }
                        } catch (Throwable th7) {
                            e$iv = th7;
                            $this$consume$iv2 = receiveChannel2;
                            Throwable cause$iv32222 = e$iv;
                            throw e$iv;
                        }
                        break;
                    case 2:
                        Object max5 = channelsKt__DeprecatedKt$maxWith$1.L$3;
                        ChannelIterator iterator4 = (ChannelIterator) channelsKt__DeprecatedKt$maxWith$1.L$2;
                        ReceiveChannel receiveChannel3 = (ReceiveChannel) channelsKt__DeprecatedKt$maxWith$1.L$1;
                        Comparator comparator5 = (Comparator) channelsKt__DeprecatedKt$maxWith$1.L$0;
                        try {
                            ResultKt.throwOnFailure(max3);
                            comparator3 = comparator5;
                            $this$consume$iv3 = receiveChannel3;
                            iterator2 = iterator4;
                            th = null;
                            max = max5;
                            max2 = $result2;
                            $result = max3;
                            if (((Boolean) max3).booleanValue()) {
                            }
                        } catch (Throwable th8) {
                            e$iv = th8;
                            $this$consume$iv2 = receiveChannel3;
                            Throwable cause$iv322222 = e$iv;
                            throw e$iv;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$maxWith$1 = new ChannelsKt__DeprecatedKt$maxWith$1($completion);
        Object max32 = channelsKt__DeprecatedKt$maxWith$1.result;
        Object $result22 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__DeprecatedKt$maxWith$1.label) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00c5 A[Catch: all -> 0x00e9, TRY_LEAVE, TryCatch #5 {all -> 0x00e9, blocks: (B:16:0x00bd, B:18:0x00c5), top: B:15:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00b4 -> B:15:0x00bd). Please report as a decompilation issue!!! */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object minWith(ReceiveChannel $this$consume$iv, Comparator comparator, Continuation $completion) {
        ChannelsKt__DeprecatedKt$minWith$1 channelsKt__DeprecatedKt$minWith$1;
        Throwable cause$iv;
        ReceiveChannel $this$consume$iv2;
        Throwable e$iv;
        ChannelIterator iterator;
        Object hasNext;
        ReceiveChannel receiveChannel;
        Comparator comparator2;
        Object $result;
        Comparator comparator3;
        ReceiveChannel $this$consume$iv3;
        ChannelIterator iterator2;
        Throwable th;
        Object min;
        Object min2;
        if ($completion instanceof ChannelsKt__DeprecatedKt$minWith$1) {
            channelsKt__DeprecatedKt$minWith$1 = (ChannelsKt__DeprecatedKt$minWith$1) $completion;
            if ((channelsKt__DeprecatedKt$minWith$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$minWith$1.label -= Integer.MIN_VALUE;
                Object min3 = channelsKt__DeprecatedKt$minWith$1.result;
                Object $result2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__DeprecatedKt$minWith$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(min3);
                        cause$iv = null;
                        try {
                            iterator = $this$consume$iv.iterator();
                            channelsKt__DeprecatedKt$minWith$1.L$0 = comparator;
                            channelsKt__DeprecatedKt$minWith$1.L$1 = $this$consume$iv;
                            channelsKt__DeprecatedKt$minWith$1.L$2 = iterator;
                            channelsKt__DeprecatedKt$minWith$1.label = 1;
                            hasNext = iterator.hasNext(channelsKt__DeprecatedKt$minWith$1);
                            if (hasNext == $result2) {
                                return $result2;
                            }
                            receiveChannel = $this$consume$iv;
                            comparator2 = comparator;
                            try {
                                if (((Boolean) hasNext).booleanValue()) {
                                    ChannelsKt.cancelConsumed(receiveChannel, cause$iv);
                                    return null;
                                }
                                ReceiveChannel $this$consume$iv4 = receiveChannel;
                                try {
                                    Throwable cause$iv2 = cause$iv;
                                    Object min4 = iterator.next();
                                    ReceiveChannel $this$consume$iv5 = $this$consume$iv4;
                                    try {
                                        channelsKt__DeprecatedKt$minWith$1.L$0 = comparator2;
                                        channelsKt__DeprecatedKt$minWith$1.L$1 = $this$consume$iv5;
                                        channelsKt__DeprecatedKt$minWith$1.L$2 = iterator;
                                        channelsKt__DeprecatedKt$minWith$1.L$3 = min4;
                                        channelsKt__DeprecatedKt$minWith$1.label = 2;
                                        Object hasNext2 = iterator.hasNext(channelsKt__DeprecatedKt$minWith$1);
                                        if (hasNext2 != $result2) {
                                            return $result2;
                                        }
                                        Object obj = $result2;
                                        $result = min3;
                                        min3 = hasNext2;
                                        comparator3 = comparator2;
                                        $this$consume$iv3 = $this$consume$iv5;
                                        iterator2 = iterator;
                                        th = cause$iv2;
                                        min = min4;
                                        min2 = obj;
                                        try {
                                            if (((Boolean) min3).booleanValue()) {
                                                ChannelsKt.cancelConsumed($this$consume$iv3, th);
                                                return min;
                                            }
                                            Object e = iterator2.next();
                                            if (comparator3.compare(min, e) > 0) {
                                                ReceiveChannel $this$consume$iv6 = $this$consume$iv3;
                                                comparator2 = comparator3;
                                                Object obj2 = min2;
                                                min4 = e;
                                                min3 = $result;
                                                $result2 = obj2;
                                                ChannelIterator channelIterator = iterator2;
                                                $this$consume$iv5 = $this$consume$iv6;
                                                cause$iv2 = th;
                                                iterator = channelIterator;
                                            } else {
                                                ReceiveChannel $this$consume$iv7 = $this$consume$iv3;
                                                comparator2 = comparator3;
                                                ChannelIterator channelIterator2 = iterator2;
                                                $this$consume$iv5 = $this$consume$iv7;
                                                min3 = $result;
                                                $result2 = min2;
                                                min4 = min;
                                                cause$iv2 = th;
                                                iterator = channelIterator2;
                                            }
                                            channelsKt__DeprecatedKt$minWith$1.L$0 = comparator2;
                                            channelsKt__DeprecatedKt$minWith$1.L$1 = $this$consume$iv5;
                                            channelsKt__DeprecatedKt$minWith$1.L$2 = iterator;
                                            channelsKt__DeprecatedKt$minWith$1.L$3 = min4;
                                            channelsKt__DeprecatedKt$minWith$1.label = 2;
                                            Object hasNext22 = iterator.hasNext(channelsKt__DeprecatedKt$minWith$1);
                                            if (hasNext22 != $result2) {
                                            }
                                        } catch (Throwable th2) {
                                            e$iv = th2;
                                            $this$consume$iv2 = $this$consume$iv3;
                                            Throwable cause$iv3 = e$iv;
                                            try {
                                                throw e$iv;
                                            } catch (Throwable e$iv2) {
                                                ChannelsKt.cancelConsumed($this$consume$iv2, cause$iv3);
                                                throw e$iv2;
                                            }
                                        }
                                    } catch (Throwable th3) {
                                        e$iv = th3;
                                        $this$consume$iv2 = $this$consume$iv5;
                                        Throwable cause$iv32 = e$iv;
                                        throw e$iv;
                                    }
                                } catch (Throwable th4) {
                                    e$iv = th4;
                                    $this$consume$iv2 = $this$consume$iv4;
                                    Throwable cause$iv322 = e$iv;
                                    throw e$iv;
                                }
                            } catch (Throwable th5) {
                                e$iv = th5;
                                $this$consume$iv2 = receiveChannel;
                            }
                        } catch (Throwable th6) {
                            $this$consume$iv2 = $this$consume$iv;
                            e$iv = th6;
                            Throwable cause$iv3222 = e$iv;
                            throw e$iv;
                        }
                    case 1:
                        ChannelIterator iterator3 = (ChannelIterator) channelsKt__DeprecatedKt$minWith$1.L$2;
                        ReceiveChannel receiveChannel2 = (ReceiveChannel) channelsKt__DeprecatedKt$minWith$1.L$1;
                        Comparator comparator4 = (Comparator) channelsKt__DeprecatedKt$minWith$1.L$0;
                        try {
                            ResultKt.throwOnFailure(min3);
                            hasNext = min3;
                            comparator2 = comparator4;
                            receiveChannel = receiveChannel2;
                            iterator = iterator3;
                            cause$iv = null;
                            if (((Boolean) hasNext).booleanValue()) {
                            }
                        } catch (Throwable th7) {
                            e$iv = th7;
                            $this$consume$iv2 = receiveChannel2;
                            Throwable cause$iv32222 = e$iv;
                            throw e$iv;
                        }
                        break;
                    case 2:
                        Object min5 = channelsKt__DeprecatedKt$minWith$1.L$3;
                        ChannelIterator iterator4 = (ChannelIterator) channelsKt__DeprecatedKt$minWith$1.L$2;
                        ReceiveChannel receiveChannel3 = (ReceiveChannel) channelsKt__DeprecatedKt$minWith$1.L$1;
                        Comparator comparator5 = (Comparator) channelsKt__DeprecatedKt$minWith$1.L$0;
                        try {
                            ResultKt.throwOnFailure(min3);
                            comparator3 = comparator5;
                            $this$consume$iv3 = receiveChannel3;
                            iterator2 = iterator4;
                            th = null;
                            min = min5;
                            min2 = $result2;
                            $result = min3;
                            if (((Boolean) min3).booleanValue()) {
                            }
                        } catch (Throwable th8) {
                            e$iv = th8;
                            $this$consume$iv2 = receiveChannel3;
                            Throwable cause$iv322222 = e$iv;
                            throw e$iv;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$minWith$1 = new ChannelsKt__DeprecatedKt$minWith$1($completion);
        Object min32 = channelsKt__DeprecatedKt$minWith$1.result;
        Object $result22 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__DeprecatedKt$minWith$1.label) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object none(ReceiveChannel $this$none, Continuation $completion) {
        ChannelsKt__DeprecatedKt$none$1 channelsKt__DeprecatedKt$none$1;
        ReceiveChannel $this$consume$iv;
        Throwable cause$iv;
        Object hasNext;
        if ($completion instanceof ChannelsKt__DeprecatedKt$none$1) {
            channelsKt__DeprecatedKt$none$1 = (ChannelsKt__DeprecatedKt$none$1) $completion;
            if ((channelsKt__DeprecatedKt$none$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__DeprecatedKt$none$1.label -= Integer.MIN_VALUE;
                Object $result = channelsKt__DeprecatedKt$none$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                boolean z = true;
                switch (channelsKt__DeprecatedKt$none$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        $this$consume$iv = $this$none;
                        cause$iv = null;
                        try {
                            ChannelIterator it = $this$consume$iv.iterator();
                            channelsKt__DeprecatedKt$none$1.L$0 = $this$consume$iv;
                            channelsKt__DeprecatedKt$none$1.label = 1;
                            hasNext = it.hasNext(channelsKt__DeprecatedKt$none$1);
                            if (hasNext == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            if (!((Boolean) hasNext).booleanValue()) {
                                z = false;
                            }
                            Boolean boxBoolean = Boxing.boxBoolean(z);
                            ChannelsKt.cancelConsumed($this$consume$iv, cause$iv);
                            return boxBoolean;
                        } catch (Throwable th) {
                            e$iv = th;
                            Throwable cause$iv2 = e$iv;
                            try {
                                throw e$iv;
                            } catch (Throwable e$iv) {
                                ChannelsKt.cancelConsumed($this$consume$iv, cause$iv2);
                                throw e$iv;
                            }
                        }
                    case 1:
                        $this$consume$iv = (ReceiveChannel) channelsKt__DeprecatedKt$none$1.L$0;
                        cause$iv = null;
                        try {
                            ResultKt.throwOnFailure($result);
                            hasNext = $result;
                            if (!((Boolean) hasNext).booleanValue()) {
                            }
                            Boolean boxBoolean2 = Boxing.boxBoolean(z);
                            ChannelsKt.cancelConsumed($this$consume$iv, cause$iv);
                            return boxBoolean2;
                        } catch (Throwable th2) {
                            e$iv = th2;
                            Throwable cause$iv22 = e$iv;
                            throw e$iv;
                        }
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__DeprecatedKt$none$1 = new ChannelsKt__DeprecatedKt$none$1($completion);
        Object $result2 = channelsKt__DeprecatedKt$none$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        boolean z2 = true;
        switch (channelsKt__DeprecatedKt$none$1.label) {
        }
    }

    public static /* synthetic */ ReceiveChannel zip$default(ReceiveChannel receiveChannel, ReceiveChannel receiveChannel2, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.zip(receiveChannel, receiveChannel2, coroutineContext, function2);
    }

    public static final <E, R, V> ReceiveChannel<V> zip(ReceiveChannel<? extends E> receiveChannel, ReceiveChannel<? extends R> receiveChannel2, CoroutineContext context, Function2<? super E, ? super R, ? extends V> function2) {
        ReceiveChannel<V> produce;
        produce = ProduceKt.produce(GlobalScope.INSTANCE, (r12 & 1) != 0 ? EmptyCoroutineContext.INSTANCE : context, (r12 & 2) != 0 ? 0 : 0, (r12 & 4) != 0 ? CoroutineStart.DEFAULT : null, (r12 & 8) != 0 ? null : ChannelsKt.consumesAll(receiveChannel, receiveChannel2), new ChannelsKt__DeprecatedKt$zip$2(receiveChannel2, receiveChannel, function2, null));
        return produce;
    }

    public static final Function1<Throwable, Unit> consumes(final ReceiveChannel<?> receiveChannel) {
        return new Function1() { // from class: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit consumes$lambda$24$ChannelsKt__DeprecatedKt;
                consumes$lambda$24$ChannelsKt__DeprecatedKt = ChannelsKt__DeprecatedKt.consumes$lambda$24$ChannelsKt__DeprecatedKt(ReceiveChannel.this, (Throwable) obj);
                return consumes$lambda$24$ChannelsKt__DeprecatedKt;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit consumes$lambda$24$ChannelsKt__DeprecatedKt(ReceiveChannel $this_consumes, Throwable cause) {
        ChannelsKt.cancelConsumed($this_consumes, cause);
        return Unit.INSTANCE;
    }
}
