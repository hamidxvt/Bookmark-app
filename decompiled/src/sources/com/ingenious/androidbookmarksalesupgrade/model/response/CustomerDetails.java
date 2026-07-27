package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomerDetails.kt */
@Metadata(d1 = {"\u0000#\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0003\b\u008d\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B«\u0006\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010E\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\bG\u0010HJ\u0011\u0010Ï\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010JJ\u0011\u0010Ð\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010JJ\u0011\u0010Ñ\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010JJ\f\u0010Ò\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010Ó\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010Ô\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010Õ\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010Ö\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010×\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010Ø\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010Ù\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010Ú\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010Û\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010Ü\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010Ý\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010Þ\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010ß\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010à\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010á\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010â\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010ã\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010ä\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010å\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010æ\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010ç\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010è\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010é\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010ê\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010ë\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010ì\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010í\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010î\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010ï\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010ð\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0011\u0010ñ\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010JJ\u0011\u0010ò\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010JJ\u0011\u0010ó\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010JJ\u0011\u0010ô\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010JJ\u0011\u0010õ\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010JJ\f\u0010ö\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010÷\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010ø\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010ù\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010ú\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010û\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010ü\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010ý\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010þ\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0011\u0010ÿ\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010JJ\u0011\u0010\u0080\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010JJ\f\u0010\u0081\u0002\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010\u0082\u0002\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010\u0083\u0002\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010\u0084\u0002\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010\u0085\u0002\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010\u0086\u0002\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010\u0087\u0002\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010\u0088\u0002\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010\u0089\u0002\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010\u008a\u0002\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010\u008b\u0002\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010\u008c\u0002\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010\u008d\u0002\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010\u008e\u0002\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010\u008f\u0002\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0011\u0010\u0090\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010JJ\f\u0010\u0091\u0002\u001a\u0004\u0018\u00010\u0007HÆ\u0003J´\u0006\u0010\u0092\u0002\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010E\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0003\u0010\u0093\u0002J\u0016\u0010\u0094\u0002\u001a\u00030\u0095\u00022\t\u0010\u0096\u0002\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\n\u0010\u0097\u0002\u001a\u00020\u0003HÖ\u0001J\n\u0010\u0098\u0002\u001a\u00020\u0007HÖ\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010M\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\"\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010M\u001a\u0004\bN\u0010J\"\u0004\bO\u0010LR\"\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010M\u001a\u0004\bP\u0010J\"\u0004\bQ\u0010LR \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010S\"\u0004\bT\u0010UR \u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010S\"\u0004\bW\u0010UR \u0010\t\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010S\"\u0004\bY\u0010UR \u0010\n\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010S\"\u0004\b[\u0010UR \u0010\u000b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010S\"\u0004\b]\u0010UR \u0010\f\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010S\"\u0004\b_\u0010UR \u0010\r\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010S\"\u0004\ba\u0010UR \u0010\u000e\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010S\"\u0004\bc\u0010UR \u0010\u000f\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010S\"\u0004\be\u0010UR \u0010\u0010\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010S\"\u0004\bg\u0010UR \u0010\u0011\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010S\"\u0004\bi\u0010UR \u0010\u0012\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010S\"\u0004\bk\u0010UR \u0010\u0013\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010S\"\u0004\bm\u0010UR \u0010\u0014\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010S\"\u0004\bo\u0010UR \u0010\u0015\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010S\"\u0004\bq\u0010UR \u0010\u0016\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010S\"\u0004\bs\u0010UR \u0010\u0017\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bt\u0010S\"\u0004\bu\u0010UR \u0010\u0018\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u0010S\"\u0004\bw\u0010UR \u0010\u0019\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010S\"\u0004\by\u0010UR \u0010\u001a\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bz\u0010S\"\u0004\b{\u0010UR \u0010\u001b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b|\u0010S\"\u0004\b}\u0010UR \u0010\u001c\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b~\u0010S\"\u0004\b\u007f\u0010UR\"\u0010\u001d\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0080\u0001\u0010S\"\u0005\b\u0081\u0001\u0010UR\"\u0010\u001e\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010S\"\u0005\b\u0083\u0001\u0010UR\"\u0010\u001f\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0084\u0001\u0010S\"\u0005\b\u0085\u0001\u0010UR\"\u0010 \u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0086\u0001\u0010S\"\u0005\b\u0087\u0001\u0010UR\"\u0010!\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0088\u0001\u0010S\"\u0005\b\u0089\u0001\u0010UR\"\u0010\"\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008a\u0001\u0010S\"\u0005\b\u008b\u0001\u0010UR\"\u0010#\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008c\u0001\u0010S\"\u0005\b\u008d\u0001\u0010UR\"\u0010$\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008e\u0001\u0010S\"\u0005\b\u008f\u0001\u0010UR\"\u0010%\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0090\u0001\u0010S\"\u0005\b\u0091\u0001\u0010UR#\u0010&\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0011\n\u0002\u0010M\u001a\u0004\b&\u0010J\"\u0005\b\u0092\u0001\u0010LR#\u0010'\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0011\n\u0002\u0010M\u001a\u0004\b'\u0010J\"\u0005\b\u0093\u0001\u0010LR#\u0010(\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0011\n\u0002\u0010M\u001a\u0004\b(\u0010J\"\u0005\b\u0094\u0001\u0010LR#\u0010)\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0011\n\u0002\u0010M\u001a\u0004\b)\u0010J\"\u0005\b\u0095\u0001\u0010LR#\u0010*\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0011\n\u0002\u0010M\u001a\u0004\b*\u0010J\"\u0005\b\u0096\u0001\u0010LR\"\u0010+\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0097\u0001\u0010S\"\u0005\b\u0098\u0001\u0010UR\"\u0010,\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0099\u0001\u0010S\"\u0005\b\u009a\u0001\u0010UR\"\u0010-\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009b\u0001\u0010S\"\u0005\b\u009c\u0001\u0010UR\"\u0010.\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009d\u0001\u0010S\"\u0005\b\u009e\u0001\u0010UR\"\u0010/\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009f\u0001\u0010S\"\u0005\b \u0001\u0010UR\"\u00100\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¡\u0001\u0010S\"\u0005\b¢\u0001\u0010UR\"\u00101\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b£\u0001\u0010S\"\u0005\b¤\u0001\u0010UR\"\u00102\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¥\u0001\u0010S\"\u0005\b¦\u0001\u0010UR\"\u00103\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b§\u0001\u0010S\"\u0005\b¨\u0001\u0010UR$\u00104\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0002\u0010M\u001a\u0005\b©\u0001\u0010J\"\u0005\bª\u0001\u0010LR$\u00105\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0002\u0010M\u001a\u0005\b«\u0001\u0010J\"\u0005\b¬\u0001\u0010LR\"\u00106\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u00ad\u0001\u0010S\"\u0005\b®\u0001\u0010UR\"\u00107\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¯\u0001\u0010S\"\u0005\b°\u0001\u0010UR\"\u00108\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b±\u0001\u0010S\"\u0005\b²\u0001\u0010UR\"\u00109\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b³\u0001\u0010S\"\u0005\b´\u0001\u0010UR\"\u0010:\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bµ\u0001\u0010S\"\u0005\b¶\u0001\u0010UR\"\u0010;\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b·\u0001\u0010S\"\u0005\b¸\u0001\u0010UR\"\u0010<\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¹\u0001\u0010S\"\u0005\bº\u0001\u0010UR\"\u0010=\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b»\u0001\u0010S\"\u0005\b¼\u0001\u0010UR\"\u0010>\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b½\u0001\u0010S\"\u0005\b¾\u0001\u0010UR\"\u0010?\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¿\u0001\u0010S\"\u0005\bÀ\u0001\u0010UR\"\u0010@\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÁ\u0001\u0010S\"\u0005\bÂ\u0001\u0010UR\"\u0010A\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÃ\u0001\u0010S\"\u0005\bÄ\u0001\u0010UR\"\u0010B\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÅ\u0001\u0010S\"\u0005\bÆ\u0001\u0010UR\"\u0010C\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÇ\u0001\u0010S\"\u0005\bÈ\u0001\u0010UR\"\u0010D\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÉ\u0001\u0010S\"\u0005\bÊ\u0001\u0010UR$\u0010E\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0002\u0010M\u001a\u0005\bË\u0001\u0010J\"\u0005\bÌ\u0001\u0010LR\"\u0010F\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÍ\u0001\u0010S\"\u0005\bÎ\u0001\u0010U¨\u0006\u0099\u0002"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/CustomerDetails;", "", Constant.VISIT_ID, "", "customerId", "bookerId", AppMeasurementSdk.ConditionalUserProperty.NAME, "", "ownerName", "assignBy", "ownerPhone", "contact", "businessName", "verificationCode", "shopAddressLatitude", "shopAddressLongitude", "shopAddress", "address", "ntnPhone", "password", "strnPhone", "messIncharge", "inchargePhone", "accountantName", "accountantPhone", "wareHousePhone", "wareHouseAddress", "wareHouseLatitude", "wareHouseLongitude", "banner", "logo", "email", "emailVerifiedAt", "deliveryLocationLatitude", "deliveryLocationLongitude", "deliveryLocation", "branchName", "adminApproved", "isGstRequired", "isBranches", "isPaymentTerm", "isVisitDay", "isOrderDetail", "cnic", "creditLimitDays", "creditLimitAmount", "maxOrderQuantity", "minOrderQuantity", "paymentCondition", "area", "creditCondition", "days", "newUpdates", "newInvoices", "rememberToken", "deletedAt", "createdAt", "updatedAt", "sessionStart", "reviewMonth", "type", "category", "examinationBoard", "offeredProgramme", "totalstudents", "workingPriority", "country", "city", "website", FirebaseAnalytics.Param.DISCOUNT, "customerType", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getCustomerId", "setCustomerId", "getBookerId", "setBookerId", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getOwnerName", "setOwnerName", "getAssignBy", "setAssignBy", "getOwnerPhone", "setOwnerPhone", "getContact", "setContact", "getBusinessName", "setBusinessName", "getVerificationCode", "setVerificationCode", "getShopAddressLatitude", "setShopAddressLatitude", "getShopAddressLongitude", "setShopAddressLongitude", "getShopAddress", "setShopAddress", "getAddress", "setAddress", "getNtnPhone", "setNtnPhone", "getPassword", "setPassword", "getStrnPhone", "setStrnPhone", "getMessIncharge", "setMessIncharge", "getInchargePhone", "setInchargePhone", "getAccountantName", "setAccountantName", "getAccountantPhone", "setAccountantPhone", "getWareHousePhone", "setWareHousePhone", "getWareHouseAddress", "setWareHouseAddress", "getWareHouseLatitude", "setWareHouseLatitude", "getWareHouseLongitude", "setWareHouseLongitude", "getBanner", "setBanner", "getLogo", "setLogo", "getEmail", "setEmail", "getEmailVerifiedAt", "setEmailVerifiedAt", "getDeliveryLocationLatitude", "setDeliveryLocationLatitude", "getDeliveryLocationLongitude", "setDeliveryLocationLongitude", "getDeliveryLocation", "setDeliveryLocation", "getBranchName", "setBranchName", "getAdminApproved", "setAdminApproved", "setGstRequired", "setBranches", "setPaymentTerm", "setVisitDay", "setOrderDetail", "getCnic", "setCnic", "getCreditLimitDays", "setCreditLimitDays", "getCreditLimitAmount", "setCreditLimitAmount", "getMaxOrderQuantity", "setMaxOrderQuantity", "getMinOrderQuantity", "setMinOrderQuantity", "getPaymentCondition", "setPaymentCondition", "getArea", "setArea", "getCreditCondition", "setCreditCondition", "getDays", "setDays", "getNewUpdates", "setNewUpdates", "getNewInvoices", "setNewInvoices", "getRememberToken", "setRememberToken", "getDeletedAt", "setDeletedAt", "getCreatedAt", "setCreatedAt", "getUpdatedAt", "setUpdatedAt", "getSessionStart", "setSessionStart", "getReviewMonth", "setReviewMonth", "getType", "setType", "getCategory", "setCategory", "getExaminationBoard", "setExaminationBoard", "getOfferedProgramme", "setOfferedProgramme", "getTotalstudents", "setTotalstudents", "getWorkingPriority", "setWorkingPriority", "getCountry", "setCountry", "getCity", "setCity", "getWebsite", "setWebsite", "getDiscount", "setDiscount", "getCustomerType", "setCustomerType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component47", "component48", "component49", "component50", "component51", "component52", "component53", "component54", "component55", "component56", "component57", "component58", "component59", "component60", "component61", "component62", "component63", "component64", "component65", "component66", "component67", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/CustomerDetails;", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class CustomerDetails {

    @SerializedName("accountant_name")
    private String accountantName;

    @SerializedName("accountant_phone")
    private String accountantPhone;

    @SerializedName("address")
    private String address;

    @SerializedName("admin_approved")
    private String adminApproved;

    @SerializedName("area")
    private String area;

    @SerializedName("assignby")
    private String assignBy;

    @SerializedName("banner")
    private String banner;

    @SerializedName("booker_id")
    private Integer bookerId;

    @SerializedName("branch_name")
    private String branchName;

    @SerializedName("business_name")
    private String businessName;

    @SerializedName("category")
    private String category;

    @SerializedName("city")
    private String city;

    @SerializedName("cnic")
    private String cnic;

    @SerializedName("contact")
    private String contact;

    @SerializedName("country")
    private String country;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("credit_condition")
    private String creditCondition;

    @SerializedName("credit_limit_amount")
    private String creditLimitAmount;

    @SerializedName("credit_limit_days")
    private String creditLimitDays;

    @SerializedName("customerid")
    private Integer customerId;

    @SerializedName("customerType")
    private String customerType;

    @SerializedName("days")
    private String days;

    @SerializedName("deleted_at")
    private String deletedAt;

    @SerializedName("delivery_location")
    private String deliveryLocation;

    @SerializedName("delivery_location_latitude")
    private String deliveryLocationLatitude;

    @SerializedName("delivery_location_longitude")
    private String deliveryLocationLongitude;

    @SerializedName(FirebaseAnalytics.Param.DISCOUNT)
    private Integer discount;

    @SerializedName("email")
    private String email;

    @SerializedName("email_verified_at")
    private String emailVerifiedAt;

    @SerializedName("examination_board")
    private String examinationBoard;

    @SerializedName(Constant.VISIT_ID)
    private Integer id;

    @SerializedName("incharge_phone")
    private String inchargePhone;

    @SerializedName("is_branches")
    private Integer isBranches;

    @SerializedName("is_gst_required")
    private Integer isGstRequired;

    @SerializedName("is_order_detail")
    private Integer isOrderDetail;

    @SerializedName("is_payment_term")
    private Integer isPaymentTerm;

    @SerializedName("is_visit_day")
    private Integer isVisitDay;

    @SerializedName("logo")
    private String logo;

    @SerializedName("max_order_quantity")
    private String maxOrderQuantity;

    @SerializedName("mess_incharge")
    private String messIncharge;

    @SerializedName("min_order_quantity")
    private String minOrderQuantity;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    private String name;

    @SerializedName("new_invoices")
    private Integer newInvoices;

    @SerializedName("new_updates")
    private Integer newUpdates;

    @SerializedName("ntn_phone")
    private String ntnPhone;

    @SerializedName("offered_programme")
    private String offeredProgramme;

    @SerializedName("owner_name")
    private String ownerName;

    @SerializedName("owner_phone")
    private String ownerPhone;

    @SerializedName("password")
    private String password;

    @SerializedName("payment_condition")
    private String paymentCondition;

    @SerializedName("remember_token")
    private String rememberToken;

    @SerializedName("review_month")
    private String reviewMonth;

    @SerializedName("session_start")
    private String sessionStart;

    @SerializedName("shop_address")
    private String shopAddress;

    @SerializedName("shop_address_latitude")
    private String shopAddressLatitude;

    @SerializedName("shop_address_longitude")
    private String shopAddressLongitude;

    @SerializedName("strn_phone")
    private String strnPhone;

    @SerializedName("totalstudents")
    private String totalstudents;

    @SerializedName("type")
    private String type;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("verification_code")
    private String verificationCode;

    @SerializedName("wareHouse_address")
    private String wareHouseAddress;

    @SerializedName("wareHouse_latitude")
    private String wareHouseLatitude;

    @SerializedName("wareHouse_longitude")
    private String wareHouseLongitude;

    @SerializedName("wareHouse_phone")
    private String wareHousePhone;

    @SerializedName("website")
    private String website;

    @SerializedName("working_priority")
    private String workingPriority;

    public CustomerDetails() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, -1, -1, 7, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getVerificationCode() {
        return this.verificationCode;
    }

    /* renamed from: component11, reason: from getter */
    public final String getShopAddressLatitude() {
        return this.shopAddressLatitude;
    }

    /* renamed from: component12, reason: from getter */
    public final String getShopAddressLongitude() {
        return this.shopAddressLongitude;
    }

    /* renamed from: component13, reason: from getter */
    public final String getShopAddress() {
        return this.shopAddress;
    }

    /* renamed from: component14, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* renamed from: component15, reason: from getter */
    public final String getNtnPhone() {
        return this.ntnPhone;
    }

    /* renamed from: component16, reason: from getter */
    public final String getPassword() {
        return this.password;
    }

    /* renamed from: component17, reason: from getter */
    public final String getStrnPhone() {
        return this.strnPhone;
    }

    /* renamed from: component18, reason: from getter */
    public final String getMessIncharge() {
        return this.messIncharge;
    }

    /* renamed from: component19, reason: from getter */
    public final String getInchargePhone() {
        return this.inchargePhone;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getCustomerId() {
        return this.customerId;
    }

    /* renamed from: component20, reason: from getter */
    public final String getAccountantName() {
        return this.accountantName;
    }

    /* renamed from: component21, reason: from getter */
    public final String getAccountantPhone() {
        return this.accountantPhone;
    }

    /* renamed from: component22, reason: from getter */
    public final String getWareHousePhone() {
        return this.wareHousePhone;
    }

    /* renamed from: component23, reason: from getter */
    public final String getWareHouseAddress() {
        return this.wareHouseAddress;
    }

    /* renamed from: component24, reason: from getter */
    public final String getWareHouseLatitude() {
        return this.wareHouseLatitude;
    }

    /* renamed from: component25, reason: from getter */
    public final String getWareHouseLongitude() {
        return this.wareHouseLongitude;
    }

    /* renamed from: component26, reason: from getter */
    public final String getBanner() {
        return this.banner;
    }

    /* renamed from: component27, reason: from getter */
    public final String getLogo() {
        return this.logo;
    }

    /* renamed from: component28, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    /* renamed from: component29, reason: from getter */
    public final String getEmailVerifiedAt() {
        return this.emailVerifiedAt;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getBookerId() {
        return this.bookerId;
    }

    /* renamed from: component30, reason: from getter */
    public final String getDeliveryLocationLatitude() {
        return this.deliveryLocationLatitude;
    }

    /* renamed from: component31, reason: from getter */
    public final String getDeliveryLocationLongitude() {
        return this.deliveryLocationLongitude;
    }

    /* renamed from: component32, reason: from getter */
    public final String getDeliveryLocation() {
        return this.deliveryLocation;
    }

    /* renamed from: component33, reason: from getter */
    public final String getBranchName() {
        return this.branchName;
    }

    /* renamed from: component34, reason: from getter */
    public final String getAdminApproved() {
        return this.adminApproved;
    }

    /* renamed from: component35, reason: from getter */
    public final Integer getIsGstRequired() {
        return this.isGstRequired;
    }

    /* renamed from: component36, reason: from getter */
    public final Integer getIsBranches() {
        return this.isBranches;
    }

    /* renamed from: component37, reason: from getter */
    public final Integer getIsPaymentTerm() {
        return this.isPaymentTerm;
    }

    /* renamed from: component38, reason: from getter */
    public final Integer getIsVisitDay() {
        return this.isVisitDay;
    }

    /* renamed from: component39, reason: from getter */
    public final Integer getIsOrderDetail() {
        return this.isOrderDetail;
    }

    /* renamed from: component4, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component40, reason: from getter */
    public final String getCnic() {
        return this.cnic;
    }

    /* renamed from: component41, reason: from getter */
    public final String getCreditLimitDays() {
        return this.creditLimitDays;
    }

    /* renamed from: component42, reason: from getter */
    public final String getCreditLimitAmount() {
        return this.creditLimitAmount;
    }

    /* renamed from: component43, reason: from getter */
    public final String getMaxOrderQuantity() {
        return this.maxOrderQuantity;
    }

    /* renamed from: component44, reason: from getter */
    public final String getMinOrderQuantity() {
        return this.minOrderQuantity;
    }

    /* renamed from: component45, reason: from getter */
    public final String getPaymentCondition() {
        return this.paymentCondition;
    }

    /* renamed from: component46, reason: from getter */
    public final String getArea() {
        return this.area;
    }

    /* renamed from: component47, reason: from getter */
    public final String getCreditCondition() {
        return this.creditCondition;
    }

    /* renamed from: component48, reason: from getter */
    public final String getDays() {
        return this.days;
    }

    /* renamed from: component49, reason: from getter */
    public final Integer getNewUpdates() {
        return this.newUpdates;
    }

    /* renamed from: component5, reason: from getter */
    public final String getOwnerName() {
        return this.ownerName;
    }

    /* renamed from: component50, reason: from getter */
    public final Integer getNewInvoices() {
        return this.newInvoices;
    }

    /* renamed from: component51, reason: from getter */
    public final String getRememberToken() {
        return this.rememberToken;
    }

    /* renamed from: component52, reason: from getter */
    public final String getDeletedAt() {
        return this.deletedAt;
    }

    /* renamed from: component53, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component54, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* renamed from: component55, reason: from getter */
    public final String getSessionStart() {
        return this.sessionStart;
    }

    /* renamed from: component56, reason: from getter */
    public final String getReviewMonth() {
        return this.reviewMonth;
    }

    /* renamed from: component57, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component58, reason: from getter */
    public final String getCategory() {
        return this.category;
    }

    /* renamed from: component59, reason: from getter */
    public final String getExaminationBoard() {
        return this.examinationBoard;
    }

    /* renamed from: component6, reason: from getter */
    public final String getAssignBy() {
        return this.assignBy;
    }

    /* renamed from: component60, reason: from getter */
    public final String getOfferedProgramme() {
        return this.offeredProgramme;
    }

    /* renamed from: component61, reason: from getter */
    public final String getTotalstudents() {
        return this.totalstudents;
    }

    /* renamed from: component62, reason: from getter */
    public final String getWorkingPriority() {
        return this.workingPriority;
    }

    /* renamed from: component63, reason: from getter */
    public final String getCountry() {
        return this.country;
    }

    /* renamed from: component64, reason: from getter */
    public final String getCity() {
        return this.city;
    }

    /* renamed from: component65, reason: from getter */
    public final String getWebsite() {
        return this.website;
    }

    /* renamed from: component66, reason: from getter */
    public final Integer getDiscount() {
        return this.discount;
    }

    /* renamed from: component67, reason: from getter */
    public final String getCustomerType() {
        return this.customerType;
    }

    /* renamed from: component7, reason: from getter */
    public final String getOwnerPhone() {
        return this.ownerPhone;
    }

    /* renamed from: component8, reason: from getter */
    public final String getContact() {
        return this.contact;
    }

    /* renamed from: component9, reason: from getter */
    public final String getBusinessName() {
        return this.businessName;
    }

    public final CustomerDetails copy(Integer id, Integer customerId, Integer bookerId, String name, String ownerName, String assignBy, String ownerPhone, String contact, String businessName, String verificationCode, String shopAddressLatitude, String shopAddressLongitude, String shopAddress, String address, String ntnPhone, String password, String strnPhone, String messIncharge, String inchargePhone, String accountantName, String accountantPhone, String wareHousePhone, String wareHouseAddress, String wareHouseLatitude, String wareHouseLongitude, String banner, String logo, String email, String emailVerifiedAt, String deliveryLocationLatitude, String deliveryLocationLongitude, String deliveryLocation, String branchName, String adminApproved, Integer isGstRequired, Integer isBranches, Integer isPaymentTerm, Integer isVisitDay, Integer isOrderDetail, String cnic, String creditLimitDays, String creditLimitAmount, String maxOrderQuantity, String minOrderQuantity, String paymentCondition, String area, String creditCondition, String days, Integer newUpdates, Integer newInvoices, String rememberToken, String deletedAt, String createdAt, String updatedAt, String sessionStart, String reviewMonth, String type, String category, String examinationBoard, String offeredProgramme, String totalstudents, String workingPriority, String country, String city, String website, Integer discount, String customerType) {
        return new CustomerDetails(id, customerId, bookerId, name, ownerName, assignBy, ownerPhone, contact, businessName, verificationCode, shopAddressLatitude, shopAddressLongitude, shopAddress, address, ntnPhone, password, strnPhone, messIncharge, inchargePhone, accountantName, accountantPhone, wareHousePhone, wareHouseAddress, wareHouseLatitude, wareHouseLongitude, banner, logo, email, emailVerifiedAt, deliveryLocationLatitude, deliveryLocationLongitude, deliveryLocation, branchName, adminApproved, isGstRequired, isBranches, isPaymentTerm, isVisitDay, isOrderDetail, cnic, creditLimitDays, creditLimitAmount, maxOrderQuantity, minOrderQuantity, paymentCondition, area, creditCondition, days, newUpdates, newInvoices, rememberToken, deletedAt, createdAt, updatedAt, sessionStart, reviewMonth, type, category, examinationBoard, offeredProgramme, totalstudents, workingPriority, country, city, website, discount, customerType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomerDetails)) {
            return false;
        }
        CustomerDetails customerDetails = (CustomerDetails) other;
        return Intrinsics.areEqual(this.id, customerDetails.id) && Intrinsics.areEqual(this.customerId, customerDetails.customerId) && Intrinsics.areEqual(this.bookerId, customerDetails.bookerId) && Intrinsics.areEqual(this.name, customerDetails.name) && Intrinsics.areEqual(this.ownerName, customerDetails.ownerName) && Intrinsics.areEqual(this.assignBy, customerDetails.assignBy) && Intrinsics.areEqual(this.ownerPhone, customerDetails.ownerPhone) && Intrinsics.areEqual(this.contact, customerDetails.contact) && Intrinsics.areEqual(this.businessName, customerDetails.businessName) && Intrinsics.areEqual(this.verificationCode, customerDetails.verificationCode) && Intrinsics.areEqual(this.shopAddressLatitude, customerDetails.shopAddressLatitude) && Intrinsics.areEqual(this.shopAddressLongitude, customerDetails.shopAddressLongitude) && Intrinsics.areEqual(this.shopAddress, customerDetails.shopAddress) && Intrinsics.areEqual(this.address, customerDetails.address) && Intrinsics.areEqual(this.ntnPhone, customerDetails.ntnPhone) && Intrinsics.areEqual(this.password, customerDetails.password) && Intrinsics.areEqual(this.strnPhone, customerDetails.strnPhone) && Intrinsics.areEqual(this.messIncharge, customerDetails.messIncharge) && Intrinsics.areEqual(this.inchargePhone, customerDetails.inchargePhone) && Intrinsics.areEqual(this.accountantName, customerDetails.accountantName) && Intrinsics.areEqual(this.accountantPhone, customerDetails.accountantPhone) && Intrinsics.areEqual(this.wareHousePhone, customerDetails.wareHousePhone) && Intrinsics.areEqual(this.wareHouseAddress, customerDetails.wareHouseAddress) && Intrinsics.areEqual(this.wareHouseLatitude, customerDetails.wareHouseLatitude) && Intrinsics.areEqual(this.wareHouseLongitude, customerDetails.wareHouseLongitude) && Intrinsics.areEqual(this.banner, customerDetails.banner) && Intrinsics.areEqual(this.logo, customerDetails.logo) && Intrinsics.areEqual(this.email, customerDetails.email) && Intrinsics.areEqual(this.emailVerifiedAt, customerDetails.emailVerifiedAt) && Intrinsics.areEqual(this.deliveryLocationLatitude, customerDetails.deliveryLocationLatitude) && Intrinsics.areEqual(this.deliveryLocationLongitude, customerDetails.deliveryLocationLongitude) && Intrinsics.areEqual(this.deliveryLocation, customerDetails.deliveryLocation) && Intrinsics.areEqual(this.branchName, customerDetails.branchName) && Intrinsics.areEqual(this.adminApproved, customerDetails.adminApproved) && Intrinsics.areEqual(this.isGstRequired, customerDetails.isGstRequired) && Intrinsics.areEqual(this.isBranches, customerDetails.isBranches) && Intrinsics.areEqual(this.isPaymentTerm, customerDetails.isPaymentTerm) && Intrinsics.areEqual(this.isVisitDay, customerDetails.isVisitDay) && Intrinsics.areEqual(this.isOrderDetail, customerDetails.isOrderDetail) && Intrinsics.areEqual(this.cnic, customerDetails.cnic) && Intrinsics.areEqual(this.creditLimitDays, customerDetails.creditLimitDays) && Intrinsics.areEqual(this.creditLimitAmount, customerDetails.creditLimitAmount) && Intrinsics.areEqual(this.maxOrderQuantity, customerDetails.maxOrderQuantity) && Intrinsics.areEqual(this.minOrderQuantity, customerDetails.minOrderQuantity) && Intrinsics.areEqual(this.paymentCondition, customerDetails.paymentCondition) && Intrinsics.areEqual(this.area, customerDetails.area) && Intrinsics.areEqual(this.creditCondition, customerDetails.creditCondition) && Intrinsics.areEqual(this.days, customerDetails.days) && Intrinsics.areEqual(this.newUpdates, customerDetails.newUpdates) && Intrinsics.areEqual(this.newInvoices, customerDetails.newInvoices) && Intrinsics.areEqual(this.rememberToken, customerDetails.rememberToken) && Intrinsics.areEqual(this.deletedAt, customerDetails.deletedAt) && Intrinsics.areEqual(this.createdAt, customerDetails.createdAt) && Intrinsics.areEqual(this.updatedAt, customerDetails.updatedAt) && Intrinsics.areEqual(this.sessionStart, customerDetails.sessionStart) && Intrinsics.areEqual(this.reviewMonth, customerDetails.reviewMonth) && Intrinsics.areEqual(this.type, customerDetails.type) && Intrinsics.areEqual(this.category, customerDetails.category) && Intrinsics.areEqual(this.examinationBoard, customerDetails.examinationBoard) && Intrinsics.areEqual(this.offeredProgramme, customerDetails.offeredProgramme) && Intrinsics.areEqual(this.totalstudents, customerDetails.totalstudents) && Intrinsics.areEqual(this.workingPriority, customerDetails.workingPriority) && Intrinsics.areEqual(this.country, customerDetails.country) && Intrinsics.areEqual(this.city, customerDetails.city) && Intrinsics.areEqual(this.website, customerDetails.website) && Intrinsics.areEqual(this.discount, customerDetails.discount) && Intrinsics.areEqual(this.customerType, customerDetails.customerType);
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.customerId == null ? 0 : this.customerId.hashCode())) * 31) + (this.bookerId == null ? 0 : this.bookerId.hashCode())) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.ownerName == null ? 0 : this.ownerName.hashCode())) * 31) + (this.assignBy == null ? 0 : this.assignBy.hashCode())) * 31) + (this.ownerPhone == null ? 0 : this.ownerPhone.hashCode())) * 31) + (this.contact == null ? 0 : this.contact.hashCode())) * 31) + (this.businessName == null ? 0 : this.businessName.hashCode())) * 31) + (this.verificationCode == null ? 0 : this.verificationCode.hashCode())) * 31) + (this.shopAddressLatitude == null ? 0 : this.shopAddressLatitude.hashCode())) * 31) + (this.shopAddressLongitude == null ? 0 : this.shopAddressLongitude.hashCode())) * 31) + (this.shopAddress == null ? 0 : this.shopAddress.hashCode())) * 31) + (this.address == null ? 0 : this.address.hashCode())) * 31) + (this.ntnPhone == null ? 0 : this.ntnPhone.hashCode())) * 31) + (this.password == null ? 0 : this.password.hashCode())) * 31) + (this.strnPhone == null ? 0 : this.strnPhone.hashCode())) * 31) + (this.messIncharge == null ? 0 : this.messIncharge.hashCode())) * 31) + (this.inchargePhone == null ? 0 : this.inchargePhone.hashCode())) * 31) + (this.accountantName == null ? 0 : this.accountantName.hashCode())) * 31) + (this.accountantPhone == null ? 0 : this.accountantPhone.hashCode())) * 31) + (this.wareHousePhone == null ? 0 : this.wareHousePhone.hashCode())) * 31) + (this.wareHouseAddress == null ? 0 : this.wareHouseAddress.hashCode())) * 31) + (this.wareHouseLatitude == null ? 0 : this.wareHouseLatitude.hashCode())) * 31) + (this.wareHouseLongitude == null ? 0 : this.wareHouseLongitude.hashCode())) * 31) + (this.banner == null ? 0 : this.banner.hashCode())) * 31) + (this.logo == null ? 0 : this.logo.hashCode())) * 31) + (this.email == null ? 0 : this.email.hashCode())) * 31) + (this.emailVerifiedAt == null ? 0 : this.emailVerifiedAt.hashCode())) * 31) + (this.deliveryLocationLatitude == null ? 0 : this.deliveryLocationLatitude.hashCode())) * 31) + (this.deliveryLocationLongitude == null ? 0 : this.deliveryLocationLongitude.hashCode())) * 31) + (this.deliveryLocation == null ? 0 : this.deliveryLocation.hashCode())) * 31) + (this.branchName == null ? 0 : this.branchName.hashCode())) * 31) + (this.adminApproved == null ? 0 : this.adminApproved.hashCode())) * 31) + (this.isGstRequired == null ? 0 : this.isGstRequired.hashCode())) * 31) + (this.isBranches == null ? 0 : this.isBranches.hashCode())) * 31) + (this.isPaymentTerm == null ? 0 : this.isPaymentTerm.hashCode())) * 31) + (this.isVisitDay == null ? 0 : this.isVisitDay.hashCode())) * 31) + (this.isOrderDetail == null ? 0 : this.isOrderDetail.hashCode())) * 31) + (this.cnic == null ? 0 : this.cnic.hashCode())) * 31) + (this.creditLimitDays == null ? 0 : this.creditLimitDays.hashCode())) * 31) + (this.creditLimitAmount == null ? 0 : this.creditLimitAmount.hashCode())) * 31) + (this.maxOrderQuantity == null ? 0 : this.maxOrderQuantity.hashCode())) * 31) + (this.minOrderQuantity == null ? 0 : this.minOrderQuantity.hashCode())) * 31) + (this.paymentCondition == null ? 0 : this.paymentCondition.hashCode())) * 31) + (this.area == null ? 0 : this.area.hashCode())) * 31) + (this.creditCondition == null ? 0 : this.creditCondition.hashCode())) * 31) + (this.days == null ? 0 : this.days.hashCode())) * 31) + (this.newUpdates == null ? 0 : this.newUpdates.hashCode())) * 31) + (this.newInvoices == null ? 0 : this.newInvoices.hashCode())) * 31) + (this.rememberToken == null ? 0 : this.rememberToken.hashCode())) * 31) + (this.deletedAt == null ? 0 : this.deletedAt.hashCode())) * 31) + (this.createdAt == null ? 0 : this.createdAt.hashCode())) * 31) + (this.updatedAt == null ? 0 : this.updatedAt.hashCode())) * 31) + (this.sessionStart == null ? 0 : this.sessionStart.hashCode())) * 31) + (this.reviewMonth == null ? 0 : this.reviewMonth.hashCode())) * 31) + (this.type == null ? 0 : this.type.hashCode())) * 31) + (this.category == null ? 0 : this.category.hashCode())) * 31) + (this.examinationBoard == null ? 0 : this.examinationBoard.hashCode())) * 31) + (this.offeredProgramme == null ? 0 : this.offeredProgramme.hashCode())) * 31) + (this.totalstudents == null ? 0 : this.totalstudents.hashCode())) * 31) + (this.workingPriority == null ? 0 : this.workingPriority.hashCode())) * 31) + (this.country == null ? 0 : this.country.hashCode())) * 31) + (this.city == null ? 0 : this.city.hashCode())) * 31) + (this.website == null ? 0 : this.website.hashCode())) * 31) + (this.discount == null ? 0 : this.discount.hashCode())) * 31) + (this.customerType != null ? this.customerType.hashCode() : 0);
    }

    public String toString() {
        return "CustomerDetails(id=" + this.id + ", customerId=" + this.customerId + ", bookerId=" + this.bookerId + ", name=" + this.name + ", ownerName=" + this.ownerName + ", assignBy=" + this.assignBy + ", ownerPhone=" + this.ownerPhone + ", contact=" + this.contact + ", businessName=" + this.businessName + ", verificationCode=" + this.verificationCode + ", shopAddressLatitude=" + this.shopAddressLatitude + ", shopAddressLongitude=" + this.shopAddressLongitude + ", shopAddress=" + this.shopAddress + ", address=" + this.address + ", ntnPhone=" + this.ntnPhone + ", password=" + this.password + ", strnPhone=" + this.strnPhone + ", messIncharge=" + this.messIncharge + ", inchargePhone=" + this.inchargePhone + ", accountantName=" + this.accountantName + ", accountantPhone=" + this.accountantPhone + ", wareHousePhone=" + this.wareHousePhone + ", wareHouseAddress=" + this.wareHouseAddress + ", wareHouseLatitude=" + this.wareHouseLatitude + ", wareHouseLongitude=" + this.wareHouseLongitude + ", banner=" + this.banner + ", logo=" + this.logo + ", email=" + this.email + ", emailVerifiedAt=" + this.emailVerifiedAt + ", deliveryLocationLatitude=" + this.deliveryLocationLatitude + ", deliveryLocationLongitude=" + this.deliveryLocationLongitude + ", deliveryLocation=" + this.deliveryLocation + ", branchName=" + this.branchName + ", adminApproved=" + this.adminApproved + ", isGstRequired=" + this.isGstRequired + ", isBranches=" + this.isBranches + ", isPaymentTerm=" + this.isPaymentTerm + ", isVisitDay=" + this.isVisitDay + ", isOrderDetail=" + this.isOrderDetail + ", cnic=" + this.cnic + ", creditLimitDays=" + this.creditLimitDays + ", creditLimitAmount=" + this.creditLimitAmount + ", maxOrderQuantity=" + this.maxOrderQuantity + ", minOrderQuantity=" + this.minOrderQuantity + ", paymentCondition=" + this.paymentCondition + ", area=" + this.area + ", creditCondition=" + this.creditCondition + ", days=" + this.days + ", newUpdates=" + this.newUpdates + ", newInvoices=" + this.newInvoices + ", rememberToken=" + this.rememberToken + ", deletedAt=" + this.deletedAt + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", sessionStart=" + this.sessionStart + ", reviewMonth=" + this.reviewMonth + ", type=" + this.type + ", category=" + this.category + ", examinationBoard=" + this.examinationBoard + ", offeredProgramme=" + this.offeredProgramme + ", totalstudents=" + this.totalstudents + ", workingPriority=" + this.workingPriority + ", country=" + this.country + ", city=" + this.city + ", website=" + this.website + ", discount=" + this.discount + ", customerType=" + this.customerType + ")";
    }

    public CustomerDetails(Integer id, Integer customerId, Integer bookerId, String name, String ownerName, String assignBy, String ownerPhone, String contact, String businessName, String verificationCode, String shopAddressLatitude, String shopAddressLongitude, String shopAddress, String address, String ntnPhone, String password, String strnPhone, String messIncharge, String inchargePhone, String accountantName, String accountantPhone, String wareHousePhone, String wareHouseAddress, String wareHouseLatitude, String wareHouseLongitude, String banner, String logo, String email, String emailVerifiedAt, String deliveryLocationLatitude, String deliveryLocationLongitude, String deliveryLocation, String branchName, String adminApproved, Integer isGstRequired, Integer isBranches, Integer isPaymentTerm, Integer isVisitDay, Integer isOrderDetail, String cnic, String creditLimitDays, String creditLimitAmount, String maxOrderQuantity, String minOrderQuantity, String paymentCondition, String area, String creditCondition, String days, Integer newUpdates, Integer newInvoices, String rememberToken, String deletedAt, String createdAt, String updatedAt, String sessionStart, String reviewMonth, String type, String category, String examinationBoard, String offeredProgramme, String totalstudents, String workingPriority, String country, String city, String website, Integer discount, String customerType) {
        this.id = id;
        this.customerId = customerId;
        this.bookerId = bookerId;
        this.name = name;
        this.ownerName = ownerName;
        this.assignBy = assignBy;
        this.ownerPhone = ownerPhone;
        this.contact = contact;
        this.businessName = businessName;
        this.verificationCode = verificationCode;
        this.shopAddressLatitude = shopAddressLatitude;
        this.shopAddressLongitude = shopAddressLongitude;
        this.shopAddress = shopAddress;
        this.address = address;
        this.ntnPhone = ntnPhone;
        this.password = password;
        this.strnPhone = strnPhone;
        this.messIncharge = messIncharge;
        this.inchargePhone = inchargePhone;
        this.accountantName = accountantName;
        this.accountantPhone = accountantPhone;
        this.wareHousePhone = wareHousePhone;
        this.wareHouseAddress = wareHouseAddress;
        this.wareHouseLatitude = wareHouseLatitude;
        this.wareHouseLongitude = wareHouseLongitude;
        this.banner = banner;
        this.logo = logo;
        this.email = email;
        this.emailVerifiedAt = emailVerifiedAt;
        this.deliveryLocationLatitude = deliveryLocationLatitude;
        this.deliveryLocationLongitude = deliveryLocationLongitude;
        this.deliveryLocation = deliveryLocation;
        this.branchName = branchName;
        this.adminApproved = adminApproved;
        this.isGstRequired = isGstRequired;
        this.isBranches = isBranches;
        this.isPaymentTerm = isPaymentTerm;
        this.isVisitDay = isVisitDay;
        this.isOrderDetail = isOrderDetail;
        this.cnic = cnic;
        this.creditLimitDays = creditLimitDays;
        this.creditLimitAmount = creditLimitAmount;
        this.maxOrderQuantity = maxOrderQuantity;
        this.minOrderQuantity = minOrderQuantity;
        this.paymentCondition = paymentCondition;
        this.area = area;
        this.creditCondition = creditCondition;
        this.days = days;
        this.newUpdates = newUpdates;
        this.newInvoices = newInvoices;
        this.rememberToken = rememberToken;
        this.deletedAt = deletedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sessionStart = sessionStart;
        this.reviewMonth = reviewMonth;
        this.type = type;
        this.category = category;
        this.examinationBoard = examinationBoard;
        this.offeredProgramme = offeredProgramme;
        this.totalstudents = totalstudents;
        this.workingPriority = workingPriority;
        this.country = country;
        this.city = city;
        this.website = website;
        this.discount = discount;
        this.customerType = customerType;
    }

    public /* synthetic */ CustomerDetails(Integer num, Integer num2, Integer num3, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8, String str32, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, Integer num9, Integer num10, String str41, String str42, String str43, String str44, String str45, String str46, String str47, String str48, String str49, String str50, String str51, String str52, String str53, String str54, String str55, Integer num11, String str56, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : num3, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? null : str4, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? null : str6, (i & 512) != 0 ? null : str7, (i & 1024) != 0 ? null : str8, (i & 2048) != 0 ? null : str9, (i & 4096) != 0 ? null : str10, (i & 8192) != 0 ? null : str11, (i & 16384) != 0 ? null : str12, (i & 32768) != 0 ? null : str13, (i & 65536) != 0 ? null : str14, (i & 131072) != 0 ? null : str15, (i & 262144) != 0 ? null : str16, (i & 524288) != 0 ? null : str17, (i & 1048576) != 0 ? null : str18, (i & 2097152) != 0 ? null : str19, (i & 4194304) != 0 ? null : str20, (i & 8388608) != 0 ? null : str21, (i & 16777216) != 0 ? null : str22, (i & 33554432) != 0 ? null : str23, (i & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? null : str24, (i & 134217728) != 0 ? null : str25, (i & 268435456) != 0 ? null : str26, (i & 536870912) != 0 ? null : str27, (i & 1073741824) != 0 ? null : str28, (i & Integer.MIN_VALUE) != 0 ? null : str29, (i2 & 1) != 0 ? null : str30, (i2 & 2) != 0 ? null : str31, (i2 & 4) != 0 ? null : num4, (i2 & 8) != 0 ? null : num5, (i2 & 16) != 0 ? null : num6, (i2 & 32) != 0 ? null : num7, (i2 & 64) != 0 ? null : num8, (i2 & 128) != 0 ? null : str32, (i2 & 256) != 0 ? null : str33, (i2 & 512) != 0 ? null : str34, (i2 & 1024) != 0 ? null : str35, (i2 & 2048) != 0 ? null : str36, (i2 & 4096) != 0 ? null : str37, (i2 & 8192) != 0 ? null : str38, (i2 & 16384) != 0 ? null : str39, (i2 & 32768) != 0 ? null : str40, (i2 & 65536) != 0 ? null : num9, (i2 & 131072) != 0 ? null : num10, (i2 & 262144) != 0 ? null : str41, (i2 & 524288) != 0 ? null : str42, (i2 & 1048576) != 0 ? null : str43, (i2 & 2097152) != 0 ? null : str44, (i2 & 4194304) != 0 ? null : str45, (i2 & 8388608) != 0 ? null : str46, (i2 & 16777216) != 0 ? null : str47, (i2 & 33554432) != 0 ? null : str48, (i2 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? null : str49, (i2 & 134217728) != 0 ? null : str50, (i2 & 268435456) != 0 ? null : str51, (i2 & 536870912) != 0 ? null : str52, (i2 & 1073741824) != 0 ? null : str53, (i2 & Integer.MIN_VALUE) != 0 ? null : str54, (i3 & 1) != 0 ? null : str55, (i3 & 2) != 0 ? null : num11, (i3 & 4) != 0 ? null : str56);
    }

    public final Integer getId() {
        return this.id;
    }

    public final void setId(Integer num) {
        this.id = num;
    }

    public final Integer getCustomerId() {
        return this.customerId;
    }

    public final void setCustomerId(Integer num) {
        this.customerId = num;
    }

    public final Integer getBookerId() {
        return this.bookerId;
    }

    public final void setBookerId(Integer num) {
        this.bookerId = num;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String getOwnerName() {
        return this.ownerName;
    }

    public final void setOwnerName(String str) {
        this.ownerName = str;
    }

    public final String getAssignBy() {
        return this.assignBy;
    }

    public final void setAssignBy(String str) {
        this.assignBy = str;
    }

    public final String getOwnerPhone() {
        return this.ownerPhone;
    }

    public final void setOwnerPhone(String str) {
        this.ownerPhone = str;
    }

    public final String getContact() {
        return this.contact;
    }

    public final void setContact(String str) {
        this.contact = str;
    }

    public final String getBusinessName() {
        return this.businessName;
    }

    public final void setBusinessName(String str) {
        this.businessName = str;
    }

    public final String getVerificationCode() {
        return this.verificationCode;
    }

    public final void setVerificationCode(String str) {
        this.verificationCode = str;
    }

    public final String getShopAddressLatitude() {
        return this.shopAddressLatitude;
    }

    public final void setShopAddressLatitude(String str) {
        this.shopAddressLatitude = str;
    }

    public final String getShopAddressLongitude() {
        return this.shopAddressLongitude;
    }

    public final void setShopAddressLongitude(String str) {
        this.shopAddressLongitude = str;
    }

    public final String getShopAddress() {
        return this.shopAddress;
    }

    public final void setShopAddress(String str) {
        this.shopAddress = str;
    }

    public final String getAddress() {
        return this.address;
    }

    public final void setAddress(String str) {
        this.address = str;
    }

    public final String getNtnPhone() {
        return this.ntnPhone;
    }

    public final void setNtnPhone(String str) {
        this.ntnPhone = str;
    }

    public final String getPassword() {
        return this.password;
    }

    public final void setPassword(String str) {
        this.password = str;
    }

    public final String getStrnPhone() {
        return this.strnPhone;
    }

    public final void setStrnPhone(String str) {
        this.strnPhone = str;
    }

    public final String getMessIncharge() {
        return this.messIncharge;
    }

    public final void setMessIncharge(String str) {
        this.messIncharge = str;
    }

    public final String getInchargePhone() {
        return this.inchargePhone;
    }

    public final void setInchargePhone(String str) {
        this.inchargePhone = str;
    }

    public final String getAccountantName() {
        return this.accountantName;
    }

    public final void setAccountantName(String str) {
        this.accountantName = str;
    }

    public final String getAccountantPhone() {
        return this.accountantPhone;
    }

    public final void setAccountantPhone(String str) {
        this.accountantPhone = str;
    }

    public final String getWareHousePhone() {
        return this.wareHousePhone;
    }

    public final void setWareHousePhone(String str) {
        this.wareHousePhone = str;
    }

    public final String getWareHouseAddress() {
        return this.wareHouseAddress;
    }

    public final void setWareHouseAddress(String str) {
        this.wareHouseAddress = str;
    }

    public final String getWareHouseLatitude() {
        return this.wareHouseLatitude;
    }

    public final void setWareHouseLatitude(String str) {
        this.wareHouseLatitude = str;
    }

    public final String getWareHouseLongitude() {
        return this.wareHouseLongitude;
    }

    public final void setWareHouseLongitude(String str) {
        this.wareHouseLongitude = str;
    }

    public final String getBanner() {
        return this.banner;
    }

    public final void setBanner(String str) {
        this.banner = str;
    }

    public final String getLogo() {
        return this.logo;
    }

    public final void setLogo(String str) {
        this.logo = str;
    }

    public final String getEmail() {
        return this.email;
    }

    public final void setEmail(String str) {
        this.email = str;
    }

    public final String getEmailVerifiedAt() {
        return this.emailVerifiedAt;
    }

    public final void setEmailVerifiedAt(String str) {
        this.emailVerifiedAt = str;
    }

    public final String getDeliveryLocationLatitude() {
        return this.deliveryLocationLatitude;
    }

    public final void setDeliveryLocationLatitude(String str) {
        this.deliveryLocationLatitude = str;
    }

    public final String getDeliveryLocationLongitude() {
        return this.deliveryLocationLongitude;
    }

    public final void setDeliveryLocationLongitude(String str) {
        this.deliveryLocationLongitude = str;
    }

    public final String getDeliveryLocation() {
        return this.deliveryLocation;
    }

    public final void setDeliveryLocation(String str) {
        this.deliveryLocation = str;
    }

    public final String getBranchName() {
        return this.branchName;
    }

    public final void setBranchName(String str) {
        this.branchName = str;
    }

    public final String getAdminApproved() {
        return this.adminApproved;
    }

    public final void setAdminApproved(String str) {
        this.adminApproved = str;
    }

    public final Integer isGstRequired() {
        return this.isGstRequired;
    }

    public final void setGstRequired(Integer num) {
        this.isGstRequired = num;
    }

    public final Integer isBranches() {
        return this.isBranches;
    }

    public final void setBranches(Integer num) {
        this.isBranches = num;
    }

    public final Integer isPaymentTerm() {
        return this.isPaymentTerm;
    }

    public final void setPaymentTerm(Integer num) {
        this.isPaymentTerm = num;
    }

    public final Integer isVisitDay() {
        return this.isVisitDay;
    }

    public final void setVisitDay(Integer num) {
        this.isVisitDay = num;
    }

    public final Integer isOrderDetail() {
        return this.isOrderDetail;
    }

    public final void setOrderDetail(Integer num) {
        this.isOrderDetail = num;
    }

    public final String getCnic() {
        return this.cnic;
    }

    public final void setCnic(String str) {
        this.cnic = str;
    }

    public final String getCreditLimitDays() {
        return this.creditLimitDays;
    }

    public final void setCreditLimitDays(String str) {
        this.creditLimitDays = str;
    }

    public final String getCreditLimitAmount() {
        return this.creditLimitAmount;
    }

    public final void setCreditLimitAmount(String str) {
        this.creditLimitAmount = str;
    }

    public final String getMaxOrderQuantity() {
        return this.maxOrderQuantity;
    }

    public final void setMaxOrderQuantity(String str) {
        this.maxOrderQuantity = str;
    }

    public final String getMinOrderQuantity() {
        return this.minOrderQuantity;
    }

    public final void setMinOrderQuantity(String str) {
        this.minOrderQuantity = str;
    }

    public final String getPaymentCondition() {
        return this.paymentCondition;
    }

    public final void setPaymentCondition(String str) {
        this.paymentCondition = str;
    }

    public final String getArea() {
        return this.area;
    }

    public final void setArea(String str) {
        this.area = str;
    }

    public final String getCreditCondition() {
        return this.creditCondition;
    }

    public final void setCreditCondition(String str) {
        this.creditCondition = str;
    }

    public final String getDays() {
        return this.days;
    }

    public final void setDays(String str) {
        this.days = str;
    }

    public final Integer getNewUpdates() {
        return this.newUpdates;
    }

    public final void setNewUpdates(Integer num) {
        this.newUpdates = num;
    }

    public final Integer getNewInvoices() {
        return this.newInvoices;
    }

    public final void setNewInvoices(Integer num) {
        this.newInvoices = num;
    }

    public final String getRememberToken() {
        return this.rememberToken;
    }

    public final void setRememberToken(String str) {
        this.rememberToken = str;
    }

    public final String getDeletedAt() {
        return this.deletedAt;
    }

    public final void setDeletedAt(String str) {
        this.deletedAt = str;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final void setUpdatedAt(String str) {
        this.updatedAt = str;
    }

    public final String getSessionStart() {
        return this.sessionStart;
    }

    public final void setSessionStart(String str) {
        this.sessionStart = str;
    }

    public final String getReviewMonth() {
        return this.reviewMonth;
    }

    public final void setReviewMonth(String str) {
        this.reviewMonth = str;
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        this.type = str;
    }

    public final String getCategory() {
        return this.category;
    }

    public final void setCategory(String str) {
        this.category = str;
    }

    public final String getExaminationBoard() {
        return this.examinationBoard;
    }

    public final void setExaminationBoard(String str) {
        this.examinationBoard = str;
    }

    public final String getOfferedProgramme() {
        return this.offeredProgramme;
    }

    public final void setOfferedProgramme(String str) {
        this.offeredProgramme = str;
    }

    public final String getTotalstudents() {
        return this.totalstudents;
    }

    public final void setTotalstudents(String str) {
        this.totalstudents = str;
    }

    public final String getWorkingPriority() {
        return this.workingPriority;
    }

    public final void setWorkingPriority(String str) {
        this.workingPriority = str;
    }

    public final String getCountry() {
        return this.country;
    }

    public final void setCountry(String str) {
        this.country = str;
    }

    public final String getCity() {
        return this.city;
    }

    public final void setCity(String str) {
        this.city = str;
    }

    public final String getWebsite() {
        return this.website;
    }

    public final void setWebsite(String str) {
        this.website = str;
    }

    public final Integer getDiscount() {
        return this.discount;
    }

    public final void setDiscount(Integer num) {
        this.discount = num;
    }

    public final String getCustomerType() {
        return this.customerType;
    }

    public final void setCustomerType(String str) {
        this.customerType = str;
    }
}
