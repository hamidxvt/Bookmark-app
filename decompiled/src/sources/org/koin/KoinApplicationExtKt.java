package org.koin;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.KoinApplication;
import org.koin.core.registry.PropertyRegistryExtKt;

/* compiled from: KoinApplicationExt.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"environmentProperties", "Lorg/koin/core/KoinApplication;", "fileProperties", "fileName", "", "koin-core"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class KoinApplicationExtKt {
    public static /* synthetic */ KoinApplication fileProperties$default(KoinApplication koinApplication, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "/koin.properties";
        }
        return fileProperties(koinApplication, str);
    }

    public static final KoinApplication fileProperties(KoinApplication $this$fileProperties, String fileName) {
        Intrinsics.checkNotNullParameter($this$fileProperties, "<this>");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        PropertyRegistryExtKt.loadPropertiesFromFile($this$fileProperties.getKoin().getPropertyRegistry(), fileName);
        return $this$fileProperties;
    }

    public static final KoinApplication environmentProperties(KoinApplication $this$environmentProperties) {
        Intrinsics.checkNotNullParameter($this$environmentProperties, "<this>");
        PropertyRegistryExtKt.loadEnvironmentProperties($this$environmentProperties.getKoin().getPropertyRegistry());
        return $this$environmentProperties;
    }
}
