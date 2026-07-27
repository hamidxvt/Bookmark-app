package com.google.android.gms.internal.auth;

import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import kotlin.text.Typography;
import org.apache.commons.lang3.StringUtils;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
/* loaded from: classes16.dex */
final class zzfz {
    private static final char[] zza = new char[80];

    static {
        Arrays.fill(zza, ' ');
    }

    static String zza(zzfx zzfxVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzfxVar, sb, 0);
        return sb.toString();
    }

    static void zzb(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zzb(sb, i, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                zzb(sb, i, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        zzc(i, sb);
        if (!str.isEmpty()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(Character.toLowerCase(str.charAt(0)));
            for (int i2 = 1; i2 < str.length(); i2++) {
                char charAt = str.charAt(i2);
                if (Character.isUpperCase(charAt)) {
                    sb2.append("_");
                }
                sb2.append(Character.toLowerCase(charAt));
            }
            str = sb2.toString();
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(zzgx.zza(new zzec(((String) obj).getBytes(zzfa.zzb))));
            sb.append(Typography.quote);
            return;
        }
        if (obj instanceof zzef) {
            sb.append(": \"");
            sb.append(zzgx.zza((zzef) obj));
            sb.append(Typography.quote);
            return;
        }
        if (obj instanceof zzev) {
            sb.append(" {");
            zzd((zzev) obj, sb, i + 2);
            sb.append(StringUtils.LF);
            zzc(i, sb);
            sb.append("}");
            return;
        }
        if (!(obj instanceof Map.Entry)) {
            sb.append(": ");
            sb.append(obj);
            return;
        }
        sb.append(" {");
        Map.Entry entry = (Map.Entry) obj;
        int i3 = i + 2;
        zzb(sb, i3, "key", entry.getKey());
        zzb(sb, i3, "value", entry.getValue());
        sb.append(StringUtils.LF);
        zzc(i, sb);
        sb.append("}");
    }

    private static void zzc(int i, StringBuilder sb) {
        while (i > 0) {
            int i2 = 80;
            if (i <= 80) {
                i2 = i;
            }
            sb.append(zza, 0, i2);
            i -= i2;
        }
    }

    private static void zzd(zzfx zzfxVar, StringBuilder sb, int i) {
        int i2;
        boolean equals;
        Method method;
        Method method2;
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        TreeMap treeMap = new TreeMap();
        Method[] declaredMethods = zzfxVar.getClass().getDeclaredMethods();
        int length = declaredMethods.length;
        int i3 = 0;
        while (true) {
            i2 = 3;
            if (i3 >= length) {
                break;
            }
            Method method3 = declaredMethods[i3];
            if (!Modifier.isStatic(method3.getModifiers()) && method3.getName().length() >= 3) {
                if (method3.getName().startsWith("set")) {
                    hashSet.add(method3.getName());
                } else if (Modifier.isPublic(method3.getModifiers()) && method3.getParameterTypes().length == 0) {
                    if (method3.getName().startsWith("has")) {
                        hashMap.put(method3.getName(), method3);
                    } else if (method3.getName().startsWith(Constant.RetrofitConstants.RETROFIT_METHOD_GET)) {
                        treeMap.put(method3.getName(), method3);
                    }
                }
            }
            i3++;
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            String substring = ((String) entry.getKey()).substring(i2);
            if (substring.endsWith("List") && !substring.endsWith("OrBuilderList") && !substring.equals("List") && (method2 = (Method) entry.getValue()) != null && method2.getReturnType().equals(List.class)) {
                zzb(sb, i, substring.substring(0, substring.length() - 4), zzev.zzg(method2, zzfxVar, new Object[0]));
                i2 = 3;
            } else if (substring.endsWith("Map") && !substring.equals("Map") && (method = (Method) entry.getValue()) != null && method.getReturnType().equals(Map.class) && !method.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method.getModifiers())) {
                zzb(sb, i, substring.substring(0, substring.length() - 3), zzev.zzg(method, zzfxVar, new Object[0]));
                i2 = 3;
            } else if (!hashSet.contains("set".concat(String.valueOf(substring)))) {
                i2 = 3;
            } else if (substring.endsWith("Bytes") && treeMap.containsKey(Constant.RetrofitConstants.RETROFIT_METHOD_GET.concat(String.valueOf(substring.substring(0, substring.length() - 5))))) {
                i2 = 3;
            } else {
                Method method4 = (Method) entry.getValue();
                Method method5 = (Method) hashMap.get("has".concat(String.valueOf(substring)));
                if (method4 != null) {
                    Object zzg = zzev.zzg(method4, zzfxVar, new Object[0]);
                    if (method5 == null) {
                        if (zzg instanceof Boolean) {
                            if (((Boolean) zzg).booleanValue()) {
                                zzb(sb, i, substring, zzg);
                                i2 = 3;
                            } else {
                                i2 = 3;
                            }
                        } else if (zzg instanceof Integer) {
                            if (((Integer) zzg).intValue() != 0) {
                                zzb(sb, i, substring, zzg);
                                i2 = 3;
                            } else {
                                i2 = 3;
                            }
                        } else if (zzg instanceof Float) {
                            if (Float.floatToRawIntBits(((Float) zzg).floatValue()) != 0) {
                                zzb(sb, i, substring, zzg);
                                i2 = 3;
                            } else {
                                i2 = 3;
                            }
                        } else if (!(zzg instanceof Double)) {
                            if (zzg instanceof String) {
                                equals = zzg.equals("");
                            } else if (zzg instanceof zzef) {
                                equals = zzg.equals(zzef.zzb);
                            } else if (!(zzg instanceof zzfx)) {
                                if ((zzg instanceof Enum) && ((Enum) zzg).ordinal() == 0) {
                                    i2 = 3;
                                }
                                zzb(sb, i, substring, zzg);
                                i2 = 3;
                            } else if (zzg != ((zzfx) zzg).zze()) {
                                zzb(sb, i, substring, zzg);
                                i2 = 3;
                            } else {
                                i2 = 3;
                            }
                            if (equals) {
                                i2 = 3;
                            } else {
                                zzb(sb, i, substring, zzg);
                                i2 = 3;
                            }
                        } else if (Double.doubleToRawLongBits(((Double) zzg).doubleValue()) != 0) {
                            zzb(sb, i, substring, zzg);
                            i2 = 3;
                        } else {
                            i2 = 3;
                        }
                    } else if (((Boolean) zzev.zzg(method5, zzfxVar, new Object[0])).booleanValue()) {
                        zzb(sb, i, substring, zzg);
                        i2 = 3;
                    } else {
                        i2 = 3;
                    }
                } else {
                    i2 = 3;
                }
            }
        }
        if (zzfxVar instanceof zzeu) {
            zzeq zzeqVar = ((zzeu) zzfxVar).zzb;
            throw null;
        }
        zzha zzhaVar = ((zzev) zzfxVar).zzc;
        if (zzhaVar != null) {
            zzhaVar.zzg(sb, i);
        }
    }
}
