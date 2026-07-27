package org.koin.core.registry;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;
import org.koin.core.logger.Level;
import org.koin.mp.KoinPlatformTools;

/* compiled from: PropertyRegistry.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\tJ\u001b\u0010\u000e\u001a\u0004\u0018\u0001H\u000f\"\u0004\b\u0000\u0010\u000f2\u0006\u0010\r\u001a\u00020\t¢\u0006\u0002\u0010\u0010J\u001a\u0010\u0011\u001a\u00020\u000b2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u0013J)\u0010\u0014\u001a\u00020\u000b\"\b\b\u0000\u0010\u000f*\u00020\u00012\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u0015\u001a\u0002H\u000fH\u0000¢\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/koin/core/registry/PropertyRegistry;", "", "_koin", "Lorg/koin/core/Koin;", "(Lorg/koin/core/Koin;)V", "get_koin$koin_core", "()Lorg/koin/core/Koin;", "_values", "", "", "close", "", "deleteProperty", "key", "getProperty", "T", "(Ljava/lang/String;)Ljava/lang/Object;", "saveProperties", "properties", "", "saveProperty", "value", "saveProperty$koin_core", "(Ljava/lang/String;Ljava/lang/Object;)V", "koin-core"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class PropertyRegistry {
    private final Koin _koin;
    private final Map<String, Object> _values;

    public PropertyRegistry(Koin _koin) {
        Intrinsics.checkNotNullParameter(_koin, "_koin");
        this._koin = _koin;
        this._values = KoinPlatformTools.INSTANCE.safeHashMap();
    }

    /* renamed from: get_koin$koin_core, reason: from getter */
    public final Koin get_koin() {
        return this._koin;
    }

    public final void saveProperties(final Map<String, ? extends Object> properties) {
        Intrinsics.checkNotNullParameter(properties, "properties");
        this._koin.getLogger().log(Level.DEBUG, new Function0<String>() { // from class: org.koin.core.registry.PropertyRegistry$saveProperties$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "load " + properties.size() + " properties";
            }
        });
        this._values.putAll(properties);
    }

    public final <T> void saveProperty$koin_core(String key, T value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this._values.put(key, value);
    }

    public final void deleteProperty(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        this._values.remove(key);
    }

    public final <T> T getProperty(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return (T) this._values.get(key);
    }

    public final void close() {
        this._values.clear();
    }
}
