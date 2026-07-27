package org.koin.core.registry;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Properties;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.koin.core.Koin;
import org.koin.core.error.NoPropertyFileFoundException;
import org.koin.core.logger.Level;

/* compiled from: PropertyRegistryExt.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0006\u001a\u0012\u0010\u0007\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\b\u001a\u00020\u0003\u001a\u0012\u0010\t\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\n\u001a\u00020\u0001¨\u0006\u000b"}, d2 = {"readDataFromFile", "Ljava/util/Properties;", FirebaseAnalytics.Param.CONTENT, "", "loadEnvironmentProperties", "", "Lorg/koin/core/registry/PropertyRegistry;", "loadPropertiesFromFile", "fileName", "saveProperties", "properties", "koin-core"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class PropertyRegistryExtKt {
    public static final void saveProperties(PropertyRegistry $this$saveProperties, final Properties properties) {
        Intrinsics.checkNotNullParameter($this$saveProperties, "<this>");
        Intrinsics.checkNotNullParameter(properties, "properties");
        $this$saveProperties.get_koin().getLogger().log(Level.DEBUG, new Function0<String>() { // from class: org.koin.core.registry.PropertyRegistryExtKt$saveProperties$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "load " + properties.size() + " properties";
            }
        });
        Map propertiesMapValues = MapsKt.toMap(properties);
        for (Map.Entry element$iv : propertiesMapValues.entrySet()) {
            String k = (String) element$iv.getKey();
            String v = (String) element$iv.getValue();
            $this$saveProperties.saveProperty$koin_core(k, v);
        }
    }

    public static final void loadPropertiesFromFile(PropertyRegistry $this$loadPropertiesFromFile, final String fileName) {
        String content;
        Intrinsics.checkNotNullParameter($this$loadPropertiesFromFile, "<this>");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        $this$loadPropertiesFromFile.get_koin().getLogger().log(Level.DEBUG, new Function0<String>() { // from class: org.koin.core.registry.PropertyRegistryExtKt$loadPropertiesFromFile$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return Intrinsics.stringPlus("load properties from ", fileName);
            }
        });
        URL resource = Koin.class.getResource(fileName);
        if (resource == null) {
            content = null;
        } else {
            content = new String(TextStreamsKt.readBytes(resource), Charsets.UTF_8);
        }
        if (content != null) {
            $this$loadPropertiesFromFile.get_koin().getLogger().log(Level.INFO, new Function0<String>() { // from class: org.koin.core.registry.PropertyRegistryExtKt$loadPropertiesFromFile$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "loaded properties from file:'" + fileName + '\'';
                }
            });
            Properties properties = readDataFromFile(content);
            saveProperties($this$loadPropertiesFromFile, properties);
            return;
        }
        throw new NoPropertyFileFoundException("No properties found for file '" + fileName + '\'');
    }

    private static final Properties readDataFromFile(String content) {
        Properties properties = new Properties();
        Charset charset = Charsets.UTF_8;
        if (content == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = content.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        properties.load(new ByteArrayInputStream(bytes));
        return properties;
    }

    public static final void loadEnvironmentProperties(PropertyRegistry $this$loadEnvironmentProperties) {
        Intrinsics.checkNotNullParameter($this$loadEnvironmentProperties, "<this>");
        $this$loadEnvironmentProperties.get_koin().getLogger().log(Level.DEBUG, new Function0<String>() { // from class: org.koin.core.registry.PropertyRegistryExtKt$loadEnvironmentProperties$1
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "load properties from environment";
            }
        });
        Properties sysProperties = System.getProperties();
        Intrinsics.checkNotNullExpressionValue(sysProperties, "sysProperties");
        saveProperties($this$loadEnvironmentProperties, sysProperties);
        Map<String, String> map = System.getenv();
        Intrinsics.checkNotNullExpressionValue(map, "getenv()");
        Properties sysEnvProperties = new Properties();
        sysEnvProperties.putAll(map);
        saveProperties($this$loadEnvironmentProperties, sysEnvProperties);
    }
}
