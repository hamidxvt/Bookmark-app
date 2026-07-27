package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzpu;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
final class zzad extends zzos {
    private String zza;
    private Set zzb;
    private Map zzc;
    private Long zzd;
    private Long zze;

    zzad(zzpg zzpgVar) {
        super(zzpgVar);
    }

    private final zzy zzc(Integer num) {
        if (this.zzc.containsKey(num)) {
            return (zzy) this.zzc.get(num);
        }
        zzy zzyVar = new zzy(this, this.zza, null);
        this.zzc.put(num, zzyVar);
        return zzyVar;
    }

    private final boolean zzd(int i, int i2) {
        zzy zzyVar = (zzy) this.zzc.get(Integer.valueOf(i));
        if (zzyVar == null) {
            return false;
        }
        return zzyVar.zzc().get(i2);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(20:0|1|(2:2|(2:4|(2:6|7)(1:569))(2:570|571))|8|(3:10|11|12)|16|(6:19|20|21|23|24|(14:(7:26|27|28|29|(1:31)(3:543|(1:545)(1:547)|546)|32|(1:35)(1:34))|(1:37)|38|39|40|41|42|43|(3:45|(1:47)|48)(4:503|(6:504|505|506|507|508|(1:511)(1:510))|(1:513)|514)|49|(1:51)(6:321|(8:323|324|325|326|327|328|329|(1:(3:331|(1:333)|334))(1:485))(1:502)|388|(9:391|(3:395|(4:398|(5:400|401|(1:403)(1:407)|404|405)(1:408)|406|396)|409)|410|(2:412|(1:414)(4:464|(4:467|(2:472|473)(3:475|476|477)|474|465)|479|480))(1:481)|(4:416|(6:419|(2:421|(3:423|424|425))(1:428)|426|427|425|417)|429|430)(1:463)|431|(3:440|(8:443|(1:445)|446|(1:448)|449|(3:451|452|453)(1:455)|454|441)|456)|457|389)|482|483)|52|(1:54)(4:207|(4:210|(3:216|217|(8:222|223|(15:225|226|227|228|229|230|231|232|233|234|235|236|237|(3:(9:239|240|241|242|243|(3:245|246|247)(1:295)|248|249|(1:252)(1:251))|(1:254)|255)(2:299|300)|256)(1:318)|257|(4:260|(3:278|279|280)(4:262|263|(2:264|(2:266|(1:268)(2:269|270))(1:277))|(3:272|273|274)(1:276))|275|258)|281|282|283)(3:219|220|221))(3:212|213|214)|215|208)|319|320)|(6:56|(3:58|(6:61|(16:63|64|65|66|67|68|69|70|71|72|73|74|75|76|(3:(9:78|79|80|81|82|83|(1:85)|86|87)|(1:91)|92)(2:158|159)|93)(1:175)|94|(2:95|(2:97|(3:136|137|138)(8:99|(2:100|(4:102|(3:104|(1:106)(1:132)|107)(1:133)|108|(1:1)(2:112|(1:114)(2:115|116)))(2:134|135))|126|(1:128)(1:130)|129|123|124|121))(0))|139|59)|176)|177|(10:180|181|182|183|184|185|187|(4:193|194|195|196)(3:189|190|191)|192|178)|203|204)(2:205|206))(1:551))|568|39|40|41|42|43|(0)(0)|49|(0)(0)|52|(0)(0)|(0)(0)|(9:(0)|(1:290)|(1:148)|(1:491)|(1:558)|(0)|(0)|(0)|(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0a07, code lost:
    
        if (r8 != false) goto L525;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0a17, code lost:
    
        r0 = r30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x08e5, code lost:
    
        if (r9 != null) goto L378;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x08bb, code lost:
    
        r9.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x08b9, code lost:
    
        if (r9 != null) goto L378;
     */
    /* JADX WARN: Code restructure failed: missing block: B:288:0x0725, code lost:
    
        if (r5 == null) goto L312;
     */
    /* JADX WARN: Code restructure failed: missing block: B:289:0x06ef, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:301:0x06ed, code lost:
    
        if (r5 != null) goto L290;
     */
    /* JADX WARN: Code restructure failed: missing block: B:337:0x02d5, code lost:
    
        if (r5 != null) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:338:0x02d7, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:339:0x030d, code lost:
    
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r1);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r13);
        r1 = new androidx.collection.ArrayMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:340:0x031c, code lost:
    
        if (r13.isEmpty() == false) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:341:0x0320, code lost:
    
        r3 = r13.keySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:343:0x032c, code lost:
    
        if (r3.hasNext() == false) goto L566;
     */
    /* JADX WARN: Code restructure failed: missing block: B:344:0x032e, code lost:
    
        r4 = ((java.lang.Integer) r3.next()).intValue();
        r5 = java.lang.Integer.valueOf(r4);
        r6 = (com.google.android.gms.internal.measurement.zzii) r13.get(r5);
        r8 = (java.util.List) r0.get(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:345:0x0348, code lost:
    
        if (r8 == null) goto L564;
     */
    /* JADX WARN: Code restructure failed: missing block: B:347:0x034e, code lost:
    
        if (r8.isEmpty() == false) goto L569;
     */
    /* JADX WARN: Code restructure failed: missing block: B:349:0x041c, code lost:
    
        r1.put(r5, r6);
        r0 = r0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:353:0x0356, code lost:
    
        r5 = r29.zzg;
        r19 = r0;
        r0 = r5.zzp().zzq(r6.zzc(), r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:354:0x036a, code lost:
    
        if (r0.isEmpty() != false) goto L565;
     */
    /* JADX WARN: Code restructure failed: missing block: B:356:0x0411, code lost:
    
        r0 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:359:0x036c, code lost:
    
        r7 = (com.google.android.gms.internal.measurement.zzih) r6.zzcl();
        r7.zzd();
        r7.zzc(r0);
        r0 = r5.zzp().zzq(r6.zza(), r8);
        r7.zzb();
        r7.zza(r0);
        r0 = new java.util.ArrayList();
        r5 = r6.zze().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:361:0x039b, code lost:
    
        if (r5.hasNext() == false) goto L575;
     */
    /* JADX WARN: Code restructure failed: missing block: B:362:0x039d, code lost:
    
        r22 = r3;
        r3 = (com.google.android.gms.internal.measurement.zzhq) r5.next();
        r23 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:363:0x03b5, code lost:
    
        if (r8.contains(java.lang.Integer.valueOf(r3.zzb())) != false) goto L574;
     */
    /* JADX WARN: Code restructure failed: missing block: B:365:0x03bf, code lost:
    
        r3 = r22;
        r5 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:369:0x03b7, code lost:
    
        r0.add(r3);
        r3 = r22;
        r5 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:372:0x03c4, code lost:
    
        r22 = r3;
        r7.zzf();
        r7.zze(r0);
        r0 = new java.util.ArrayList();
        r3 = r6.zzg().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:374:0x03dd, code lost:
    
        if (r3.hasNext() == false) goto L581;
     */
    /* JADX WARN: Code restructure failed: missing block: B:375:0x03df, code lost:
    
        r5 = (com.google.android.gms.internal.measurement.zzik) r3.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:376:0x03f1, code lost:
    
        if (r8.contains(java.lang.Integer.valueOf(r5.zzb())) != false) goto L583;
     */
    /* JADX WARN: Code restructure failed: missing block: B:378:0x03f3, code lost:
    
        r0.add(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:383:0x03f7, code lost:
    
        r7.zzh();
        r7.zzg(r0);
        r1.put(java.lang.Integer.valueOf(r4), (com.google.android.gms.internal.measurement.zzii) r7.zzbc());
        r0 = r19;
        r3 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:387:0x0427, code lost:
    
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:486:0x02e0, code lost:
    
        if (r5 != null) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:490:0x030a, code lost:
    
        if (r5 == null) goto L136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:536:0x0231, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:537:0x0232, code lost:
    
        r20 = "audience_id";
     */
    /* JADX WARN: Code restructure failed: missing block: B:539:0x0239, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:540:0x023a, code lost:
    
        r20 = "audience_id";
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:541:0x0235, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:542:0x0236, code lost:
    
        r5 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:552:0x0158, code lost:
    
        if (r5 != null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:553:0x015a, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:557:0x017c, code lost:
    
        if (r5 == null) goto L61;
     */
    /* JADX WARN: Removed duplicated region for block: B:152:0x08f0  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0ad4  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x05ec  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x072f  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01c1 A[Catch: all -> 0x022d, SQLiteException -> 0x0231, TRY_LEAVE, TryCatch #9 {all -> 0x022d, blocks: (B:43:0x01bb, B:45:0x01c1, B:503:0x01cf, B:504:0x01d4, B:506:0x01df, B:507:0x01ef, B:508:0x0217, B:530:0x01fc, B:533:0x0210), top: B:42:0x01bb }] */
    /* JADX WARN: Removed duplicated region for block: B:494:0x042c  */
    /* JADX WARN: Removed duplicated region for block: B:503:0x01cf A[Catch: all -> 0x022d, SQLiteException -> 0x0231, TRY_ENTER, TryCatch #9 {all -> 0x022d, blocks: (B:43:0x01bb, B:45:0x01c1, B:503:0x01cf, B:504:0x01d4, B:506:0x01df, B:507:0x01ef, B:508:0x0217, B:530:0x01fc, B:533:0x0210), top: B:42:0x01bb }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:526:0x0ade  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x05e8  */
    /* JADX WARN: Removed duplicated region for block: B:561:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x07ea  */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final List zzb(String str, List list, List list2, Long l, Long l2, boolean z) {
        int i;
        int i2;
        boolean z2;
        Map map;
        Cursor cursor;
        String str2;
        Cursor cursor2;
        Map map2;
        String str3;
        Map map3;
        String str4;
        String str5;
        String str6;
        Map map4;
        String str7;
        List<com.google.android.gms.internal.measurement.zzff> list3;
        String str8;
        Cursor cursor3;
        String str9;
        zzz zzzVar;
        Iterator it;
        zzbc zzbcVar;
        String str10;
        Cursor cursor4;
        List list4;
        Map map5;
        com.google.android.gms.internal.measurement.zzfn zzfnVar;
        zzic zzicVar;
        Cursor cursor5;
        Cursor cursor6;
        ArrayMap arrayMap;
        Cursor cursor7;
        List list5;
        String str11 = "current_results";
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(list2);
        this.zza = str;
        this.zzb = new HashSet();
        this.zzc = new ArrayMap();
        this.zzd = l;
        this.zze = l2;
        Iterator it2 = list.iterator();
        while (true) {
            i = 0;
            i2 = 1;
            if (!it2.hasNext()) {
                z2 = false;
                break;
            }
            if ("_s".equals(((com.google.android.gms.internal.measurement.zzhs) it2.next()).zzd())) {
                z2 = true;
                break;
            }
        }
        zzpu.zza();
        zzic zzicVar2 = this.zzu;
        boolean zzp = zzicVar2.zzc().zzp(this.zza, zzfy.zzaF);
        zzpu.zza();
        boolean zzp2 = zzicVar2.zzc().zzp(this.zza, zzfy.zzaE);
        if (z2) {
            zzav zzj = this.zzg.zzj();
            String str12 = this.zza;
            zzj.zzaw();
            zzj.zzg();
            Preconditions.checkNotEmpty(str12);
            ContentValues contentValues = new ContentValues();
            contentValues.put("current_session_count", (Integer) 0);
            try {
                zzj.zze().update("events", contentValues, "app_id = ?", new String[]{str12});
            } catch (SQLiteException e) {
                zzj.zzu.zzaV().zzb().zzc("Error resetting session-scoped event counts. appId", zzgu.zzl(str12), e);
            }
        }
        Map emptyMap = Collections.emptyMap();
        String str13 = "Failed to merge filter. appId";
        String str14 = "audience_id";
        try {
            if (zzp2 && zzp) {
                zzav zzj2 = this.zzg.zzj();
                String str15 = this.zza;
                Preconditions.checkNotEmpty(str15);
                ArrayMap arrayMap2 = new ArrayMap();
                try {
                    cursor7 = zzj2.zze().query("event_filters", new String[]{"audience_id", Constants.ScionAnalytics.MessageType.DATA_MESSAGE}, "app_id=?", new String[]{str15}, null, null, null);
                } catch (SQLiteException e2) {
                    e = e2;
                    cursor7 = null;
                } catch (Throwable th) {
                    th = th;
                    cursor7 = null;
                }
                try {
                } catch (SQLiteException e3) {
                    e = e3;
                    try {
                        zzj2.zzu.zzaV().zzb().zzc("Database error querying filters. appId", zzgu.zzl(str15), e);
                        emptyMap = Collections.emptyMap();
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor7 != null) {
                            cursor7.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (cursor7 != null) {
                    }
                    throw th;
                }
                if (cursor7.moveToFirst()) {
                    while (true) {
                        try {
                            com.google.android.gms.internal.measurement.zzff zzffVar = (com.google.android.gms.internal.measurement.zzff) ((com.google.android.gms.internal.measurement.zzfe) zzpk.zzw(com.google.android.gms.internal.measurement.zzff.zzn(), cursor7.getBlob(i2))).zzbc();
                            if (zzffVar.zzg()) {
                                Integer valueOf = Integer.valueOf(cursor7.getInt(i));
                                List list6 = (List) arrayMap2.get(valueOf);
                                if (list6 == null) {
                                    list5 = new ArrayList();
                                    arrayMap2.put(valueOf, list5);
                                } else {
                                    list5 = list6;
                                }
                                list5.add(zzffVar);
                            }
                        } catch (IOException e4) {
                            zzj2.zzu.zzaV().zzb().zzc("Failed to merge filter. appId", zzgu.zzl(str15), e4);
                        }
                        if (!cursor7.moveToNext()) {
                            break;
                        }
                        i = 0;
                        i2 = 1;
                    }
                    if (cursor7 != null) {
                        cursor7.close();
                    }
                    map = arrayMap2;
                    zzav zzj3 = this.zzg.zzj();
                    String str16 = this.zza;
                    zzj3.zzaw();
                    zzj3.zzg();
                    Preconditions.checkNotEmpty(str16);
                    cursor2 = zzj3.zze().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str16}, null, null, null);
                    if (cursor2.moveToFirst()) {
                        Map emptyMap2 = Collections.emptyMap();
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        map2 = emptyMap2;
                        str2 = "audience_id";
                    } else {
                        ArrayMap arrayMap3 = new ArrayMap();
                        while (true) {
                            int i3 = cursor2.getInt(0);
                            try {
                                arrayMap3.put(Integer.valueOf(i3), (com.google.android.gms.internal.measurement.zzii) ((com.google.android.gms.internal.measurement.zzih) zzpk.zzw(com.google.android.gms.internal.measurement.zzii.zzi(), cursor2.getBlob(1))).zzbc());
                                arrayMap = arrayMap3;
                                str2 = str14;
                            } catch (IOException e5) {
                                arrayMap = arrayMap3;
                                str2 = str14;
                                try {
                                    zzj3.zzu.zzaV().zzb().zzd("Failed to merge filter results. appId, audienceId, error", zzgu.zzl(str16), Integer.valueOf(i3), e5);
                                } catch (SQLiteException e6) {
                                    e = e6;
                                    try {
                                        zzj3.zzu.zzaV().zzb().zzc("Database error querying filter results. appId", zzgu.zzl(str16), e);
                                        Map emptyMap3 = Collections.emptyMap();
                                        if (cursor2 != null) {
                                            cursor2.close();
                                        }
                                        map2 = emptyMap3;
                                        if (map2.isEmpty()) {
                                        }
                                        if (list.isEmpty()) {
                                        }
                                        if (z) {
                                        }
                                    } catch (Throwable th4) {
                                        th = th4;
                                        cursor = cursor2;
                                        if (cursor != null) {
                                        }
                                        throw th;
                                    }
                                }
                            }
                            if (!cursor2.moveToNext()) {
                                break;
                            }
                            arrayMap3 = arrayMap;
                            str14 = str2;
                        }
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        map2 = arrayMap;
                    }
                    if (map2.isEmpty()) {
                        str5 = "Database error querying filters. appId";
                        str4 = "Failed to merge filter. appId";
                        str6 = str2;
                    } else {
                        HashSet hashSet = new HashSet(map2.keySet());
                        if (z2) {
                            String str17 = this.zza;
                            zzav zzj4 = this.zzg.zzj();
                            String str18 = this.zza;
                            zzj4.zzaw();
                            zzj4.zzg();
                            Preconditions.checkNotEmpty(str18);
                            Map arrayMap4 = new ArrayMap();
                            str3 = "Database error querying filters. appId";
                            try {
                                cursor3 = zzj4.zze().rawQuery("select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;", new String[]{str18, str18});
                                try {
                                    if (cursor3.moveToFirst()) {
                                        do {
                                            Integer valueOf2 = Integer.valueOf(cursor3.getInt(0));
                                            List list7 = (List) arrayMap4.get(valueOf2);
                                            if (list7 == null) {
                                                list7 = new ArrayList();
                                                arrayMap4.put(valueOf2, list7);
                                            }
                                            list7.add(Integer.valueOf(cursor3.getInt(1)));
                                        } while (cursor3.moveToNext());
                                    } else {
                                        arrayMap4 = Collections.emptyMap();
                                    }
                                } catch (SQLiteException e7) {
                                    e = e7;
                                    try {
                                        zzj4.zzu.zzaV().zzb().zzc("Database error querying scoped filters. appId", zzgu.zzl(str18), e);
                                        arrayMap4 = Collections.emptyMap();
                                    } catch (Throwable th5) {
                                        th = th5;
                                        if (cursor3 != null) {
                                            cursor3.close();
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                    if (cursor3 != null) {
                                    }
                                    throw th;
                                }
                            } catch (SQLiteException e8) {
                                e = e8;
                                cursor3 = null;
                            } catch (Throwable th7) {
                                th = th7;
                                cursor3 = null;
                            }
                        } else {
                            str3 = "Database error querying filters. appId";
                            map3 = map2;
                        }
                        Iterator it3 = hashSet.iterator();
                        while (it3.hasNext()) {
                            int intValue = ((Integer) it3.next()).intValue();
                            com.google.android.gms.internal.measurement.zzii zziiVar = (com.google.android.gms.internal.measurement.zzii) map3.get(Integer.valueOf(intValue));
                            BitSet bitSet = new BitSet();
                            BitSet bitSet2 = new BitSet();
                            ArrayMap arrayMap5 = new ArrayMap();
                            if (zziiVar != null && zziiVar.zzf() != 0) {
                                for (com.google.android.gms.internal.measurement.zzhq zzhqVar : zziiVar.zze()) {
                                    if (zzhqVar.zza()) {
                                        arrayMap5.put(Integer.valueOf(zzhqVar.zzb()), zzhqVar.zzc() ? Long.valueOf(zzhqVar.zzd()) : null);
                                    }
                                }
                            }
                            ArrayMap arrayMap6 = new ArrayMap();
                            if (zziiVar == null) {
                                map4 = map3;
                            } else if (zziiVar.zzh() == 0) {
                                map4 = map3;
                            } else {
                                for (com.google.android.gms.internal.measurement.zzik zzikVar : zziiVar.zzg()) {
                                    if (zzikVar.zza() && zzikVar.zzd() > 0) {
                                        arrayMap6.put(Integer.valueOf(zzikVar.zzb()), Long.valueOf(zzikVar.zze(zzikVar.zzd() - 1)));
                                        map3 = map3;
                                    }
                                }
                                map4 = map3;
                            }
                            if (zziiVar != null) {
                                int i4 = 0;
                                while (i4 < zziiVar.zzb() * 64) {
                                    if (zzpk.zzn(zziiVar.zza(), i4)) {
                                        str8 = str13;
                                        this.zzu.zzaV().zzk().zzc("Filter already evaluated. audience ID, filter ID", Integer.valueOf(intValue), Integer.valueOf(i4));
                                        bitSet2.set(i4);
                                        if (zzpk.zzn(zziiVar.zzc(), i4)) {
                                            bitSet.set(i4);
                                            i4++;
                                            str13 = str8;
                                        }
                                    } else {
                                        str8 = str13;
                                    }
                                    arrayMap5.remove(Integer.valueOf(i4));
                                    i4++;
                                    str13 = str8;
                                }
                                str7 = str13;
                            } else {
                                str7 = str13;
                            }
                            Integer valueOf3 = Integer.valueOf(intValue);
                            com.google.android.gms.internal.measurement.zzii zziiVar2 = (com.google.android.gms.internal.measurement.zzii) map2.get(valueOf3);
                            if (zzp2 && zzp && (list3 = (List) map.get(valueOf3)) != null && this.zze != null && this.zzd != null) {
                                for (com.google.android.gms.internal.measurement.zzff zzffVar2 : list3) {
                                    int zzb = zzffVar2.zzb();
                                    long longValue = this.zze.longValue() / 1000;
                                    if (zzffVar2.zzj()) {
                                        longValue = this.zzd.longValue() / 1000;
                                    }
                                    Integer valueOf4 = Integer.valueOf(zzb);
                                    if (arrayMap5.containsKey(valueOf4)) {
                                        arrayMap5.put(valueOf4, Long.valueOf(longValue));
                                    }
                                    if (arrayMap6.containsKey(valueOf4)) {
                                        arrayMap6.put(valueOf4, Long.valueOf(longValue));
                                    }
                                }
                            }
                            this.zzc.put(Integer.valueOf(intValue), new zzy(this, this.zza, zziiVar2, bitSet, bitSet2, arrayMap5, arrayMap6, null));
                            str13 = str7;
                            map = map;
                            map3 = map4;
                            map2 = map2;
                        }
                        str4 = str13;
                        str5 = str3;
                        str6 = str2;
                    }
                    if (list.isEmpty()) {
                        str9 = "current_results";
                    } else {
                        zzz zzzVar2 = new zzz(this, null);
                        ArrayMap arrayMap7 = new ArrayMap();
                        Iterator it4 = list.iterator();
                        while (it4.hasNext()) {
                            com.google.android.gms.internal.measurement.zzhs zzhsVar = (com.google.android.gms.internal.measurement.zzhs) it4.next();
                            com.google.android.gms.internal.measurement.zzhs zza = zzzVar2.zza(this.zza, zzhsVar);
                            if (zza != null) {
                                zzpg zzpgVar = this.zzg;
                                zzbc zzaf = zzpgVar.zzj().zzaf(this.zza, zzhsVar, zza.zzd());
                                zzpgVar.zzj().zzh(zzaf);
                                if (!z) {
                                    long j = zzaf.zzc;
                                    String zzd = zza.zzd();
                                    Map map6 = (Map) arrayMap7.get(zzd);
                                    if (map6 == null) {
                                        zzav zzj5 = zzpgVar.zzj();
                                        String str19 = this.zza;
                                        zzj5.zzaw();
                                        zzj5.zzg();
                                        Preconditions.checkNotEmpty(str19);
                                        Preconditions.checkNotEmpty(zzd);
                                        zzzVar = zzzVar2;
                                        ArrayMap arrayMap8 = new ArrayMap();
                                        SQLiteDatabase zze = zzj5.zze();
                                        it = it4;
                                        try {
                                            try {
                                                String[] strArr = new String[2];
                                                String str20 = str6;
                                                try {
                                                    strArr[0] = str20;
                                                    strArr[1] = Constants.ScionAnalytics.MessageType.DATA_MESSAGE;
                                                    str6 = str20;
                                                    str10 = str11;
                                                } catch (SQLiteException e9) {
                                                    e = e9;
                                                    str6 = str20;
                                                    zzbcVar = zzaf;
                                                    str10 = str11;
                                                    cursor4 = null;
                                                    try {
                                                        zzj5.zzu.zzaV().zzb().zzc(str5, zzgu.zzl(str19), e);
                                                        map6 = Collections.emptyMap();
                                                    } catch (Throwable th8) {
                                                        th = th8;
                                                        if (cursor4 != null) {
                                                            cursor4.close();
                                                        }
                                                        throw th;
                                                    }
                                                }
                                                try {
                                                    cursor4 = zze.query("event_filters", strArr, "app_id=? AND event_name=?", new String[]{str19, zzd}, null, null, null);
                                                    try {
                                                        try {
                                                            if (cursor4.moveToFirst()) {
                                                                while (true) {
                                                                    try {
                                                                        com.google.android.gms.internal.measurement.zzff zzffVar3 = (com.google.android.gms.internal.measurement.zzff) ((com.google.android.gms.internal.measurement.zzfe) zzpk.zzw(com.google.android.gms.internal.measurement.zzff.zzn(), cursor4.getBlob(1))).zzbc();
                                                                        Integer valueOf5 = Integer.valueOf(cursor4.getInt(0));
                                                                        List list8 = (List) arrayMap8.get(valueOf5);
                                                                        if (list8 == null) {
                                                                            zzbcVar = zzaf;
                                                                            try {
                                                                                list4 = new ArrayList();
                                                                                arrayMap8.put(valueOf5, list4);
                                                                            } catch (SQLiteException e10) {
                                                                                e = e10;
                                                                                zzj5.zzu.zzaV().zzb().zzc(str5, zzgu.zzl(str19), e);
                                                                                map6 = Collections.emptyMap();
                                                                            }
                                                                        } else {
                                                                            zzbcVar = zzaf;
                                                                            list4 = list8;
                                                                        }
                                                                        list4.add(zzffVar3);
                                                                    } catch (IOException e11) {
                                                                        zzbcVar = zzaf;
                                                                        zzj5.zzu.zzaV().zzb().zzc(str4, zzgu.zzl(str19), e11);
                                                                    }
                                                                    if (!cursor4.moveToNext()) {
                                                                        break;
                                                                    }
                                                                    zzaf = zzbcVar;
                                                                }
                                                                if (cursor4 != null) {
                                                                    cursor4.close();
                                                                }
                                                                map6 = arrayMap8;
                                                            } else {
                                                                zzbcVar = zzaf;
                                                                map6 = Collections.emptyMap();
                                                            }
                                                        } catch (Throwable th9) {
                                                            th = th9;
                                                            if (cursor4 != null) {
                                                            }
                                                            throw th;
                                                        }
                                                    } catch (SQLiteException e12) {
                                                        e = e12;
                                                        zzbcVar = zzaf;
                                                    }
                                                } catch (SQLiteException e13) {
                                                    e = e13;
                                                    zzbcVar = zzaf;
                                                    cursor4 = null;
                                                    zzj5.zzu.zzaV().zzb().zzc(str5, zzgu.zzl(str19), e);
                                                    map6 = Collections.emptyMap();
                                                }
                                            } catch (Throwable th10) {
                                                th = th10;
                                                cursor4 = null;
                                            }
                                        } catch (SQLiteException e14) {
                                            e = e14;
                                        }
                                        arrayMap7.put(zzd, map6);
                                    } else {
                                        zzzVar = zzzVar2;
                                        it = it4;
                                        zzbcVar = zzaf;
                                        str10 = str11;
                                    }
                                    Iterator it5 = map6.keySet().iterator();
                                    while (it5.hasNext()) {
                                        int intValue2 = ((Integer) it5.next()).intValue();
                                        Set set = this.zzb;
                                        Integer valueOf6 = Integer.valueOf(intValue2);
                                        if (set.contains(valueOf6)) {
                                            this.zzu.zzaV().zzk().zzb("Skipping failed audience ID", valueOf6);
                                        } else {
                                            Iterator it6 = ((List) map6.get(valueOf6)).iterator();
                                            boolean z3 = true;
                                            while (true) {
                                                if (!it6.hasNext()) {
                                                    break;
                                                }
                                                com.google.android.gms.internal.measurement.zzff zzffVar4 = (com.google.android.gms.internal.measurement.zzff) it6.next();
                                                zzaa zzaaVar = new zzaa(this, this.zza, intValue2, zzffVar4);
                                                z3 = zzaaVar.zzd(this.zzd, this.zze, zza, j, zzbcVar, zzd(intValue2, zzffVar4.zzb()));
                                                if (!z3) {
                                                    this.zzb.add(Integer.valueOf(intValue2));
                                                    break;
                                                }
                                                zzc(Integer.valueOf(intValue2)).zza(zzaaVar);
                                            }
                                            if (!z3) {
                                                this.zzb.add(Integer.valueOf(intValue2));
                                            }
                                        }
                                    }
                                    zzzVar2 = zzzVar;
                                    it4 = it;
                                    str11 = str10;
                                }
                            }
                        }
                        str9 = str11;
                    }
                    if (z) {
                        return new ArrayList();
                    }
                    if (!list2.isEmpty()) {
                        ArrayMap arrayMap9 = new ArrayMap();
                        Iterator it7 = list2.iterator();
                        while (it7.hasNext()) {
                            com.google.android.gms.internal.measurement.zziu zziuVar = (com.google.android.gms.internal.measurement.zziu) it7.next();
                            String zzc = zziuVar.zzc();
                            Map map7 = (Map) arrayMap9.get(zzc);
                            if (map7 == null) {
                                zzav zzj6 = this.zzg.zzj();
                                String str21 = this.zza;
                                zzj6.zzaw();
                                zzj6.zzg();
                                Preconditions.checkNotEmpty(str21);
                                Preconditions.checkNotEmpty(zzc);
                                ArrayMap arrayMap10 = new ArrayMap();
                                SQLiteDatabase zze2 = zzj6.zze();
                                try {
                                    try {
                                        String[] strArr2 = new String[2];
                                        try {
                                            strArr2[0] = str6;
                                            strArr2[1] = Constants.ScionAnalytics.MessageType.DATA_MESSAGE;
                                            String[] strArr3 = new String[2];
                                            strArr3[0] = str21;
                                            strArr3[1] = zzc;
                                            cursor6 = zze2.query("property_filters", strArr2, "app_id=? AND property_name=?", strArr3, null, null, null);
                                            try {
                                                try {
                                                    if (cursor6.moveToFirst()) {
                                                        do {
                                                            try {
                                                                com.google.android.gms.internal.measurement.zzfn zzfnVar2 = (com.google.android.gms.internal.measurement.zzfn) ((com.google.android.gms.internal.measurement.zzfm) zzpk.zzw(com.google.android.gms.internal.measurement.zzfn.zzi(), cursor6.getBlob(1))).zzbc();
                                                                try {
                                                                    Integer valueOf7 = Integer.valueOf(cursor6.getInt(0));
                                                                    List list9 = (List) arrayMap10.get(valueOf7);
                                                                    if (list9 == null) {
                                                                        list9 = new ArrayList();
                                                                        arrayMap10.put(valueOf7, list9);
                                                                    }
                                                                    list9.add(zzfnVar2);
                                                                } catch (SQLiteException e15) {
                                                                    e = e15;
                                                                    try {
                                                                        zzj6.zzu.zzaV().zzb().zzc(str5, zzgu.zzl(str21), e);
                                                                        map7 = Collections.emptyMap();
                                                                    } catch (Throwable th11) {
                                                                        th = th11;
                                                                        cursor5 = cursor6;
                                                                        if (cursor5 != null) {
                                                                            cursor5.close();
                                                                        }
                                                                        throw th;
                                                                    }
                                                                }
                                                            } catch (IOException e16) {
                                                                zzj6.zzu.zzaV().zzb().zzc("Failed to merge filter", zzgu.zzl(str21), e16);
                                                            }
                                                        } while (cursor6.moveToNext());
                                                        if (cursor6 != null) {
                                                            cursor6.close();
                                                        }
                                                        map7 = arrayMap10;
                                                    } else {
                                                        map7 = Collections.emptyMap();
                                                    }
                                                } catch (Throwable th12) {
                                                    th = th12;
                                                    cursor5 = cursor6;
                                                    if (cursor5 != null) {
                                                    }
                                                    throw th;
                                                }
                                            } catch (SQLiteException e17) {
                                                e = e17;
                                            }
                                        } catch (SQLiteException e18) {
                                            e = e18;
                                            cursor6 = null;
                                            zzj6.zzu.zzaV().zzb().zzc(str5, zzgu.zzl(str21), e);
                                            map7 = Collections.emptyMap();
                                        }
                                    } catch (Throwable th13) {
                                        th = th13;
                                        cursor5 = null;
                                    }
                                } catch (SQLiteException e19) {
                                    e = e19;
                                }
                                arrayMap9.put(zzc, map7);
                            }
                            Iterator it8 = map7.keySet().iterator();
                            while (true) {
                                if (it8.hasNext()) {
                                    int intValue3 = ((Integer) it8.next()).intValue();
                                    Set set2 = this.zzb;
                                    Integer valueOf8 = Integer.valueOf(intValue3);
                                    if (set2.contains(valueOf8)) {
                                        this.zzu.zzaV().zzk().zzb("Skipping failed audience ID", valueOf8);
                                        break;
                                    }
                                    Iterator it9 = ((List) map7.get(valueOf8)).iterator();
                                    boolean z4 = true;
                                    while (true) {
                                        if (!it9.hasNext()) {
                                            map5 = map7;
                                            break;
                                        }
                                        zzfnVar = (com.google.android.gms.internal.measurement.zzfn) it9.next();
                                        zzicVar = this.zzu;
                                        if (Log.isLoggable(zzicVar.zzaV().zzn(), 2)) {
                                            map5 = map7;
                                            zzicVar.zzaV().zzk().zzd("Evaluating filter. audience, filter, property", Integer.valueOf(intValue3), zzfnVar.zza() ? Integer.valueOf(zzfnVar.zzb()) : null, zzicVar.zzl().zzc(zzfnVar.zzc()));
                                            zzicVar.zzaV().zzk().zzb("Filter definition", this.zzg.zzp().zzk(zzfnVar));
                                        } else {
                                            map5 = map7;
                                        }
                                        if (!zzfnVar.zza() || zzfnVar.zzb() > 256) {
                                            break;
                                        }
                                        zzac zzacVar = new zzac(this, this.zza, intValue3, zzfnVar);
                                        z4 = zzacVar.zzd(this.zzd, this.zze, zziuVar, zzd(intValue3, zzfnVar.zzb()));
                                        if (!z4) {
                                            this.zzb.add(Integer.valueOf(intValue3));
                                            break;
                                        }
                                        zzc(Integer.valueOf(intValue3)).zza(zzacVar);
                                        map7 = map5;
                                    }
                                    zzicVar.zzaV().zze().zzc("Invalid property filter ID. appId, id", zzgu.zzl(this.zza), String.valueOf(zzfnVar.zza() ? Integer.valueOf(zzfnVar.zzb()) : null));
                                    this.zzb.add(Integer.valueOf(intValue3));
                                    map7 = map5;
                                }
                            }
                        }
                    }
                    ArrayList arrayList = new ArrayList();
                    Set keySet = this.zzc.keySet();
                    keySet.removeAll(this.zzb);
                    Iterator it10 = keySet.iterator();
                    while (it10.hasNext()) {
                        int intValue4 = ((Integer) it10.next()).intValue();
                        Map map8 = this.zzc;
                        Integer valueOf9 = Integer.valueOf(intValue4);
                        zzy zzyVar = (zzy) map8.get(valueOf9);
                        Preconditions.checkNotNull(zzyVar);
                        com.google.android.gms.internal.measurement.zzhg zzb2 = zzyVar.zzb(intValue4);
                        arrayList.add(zzb2);
                        zzav zzj7 = this.zzg.zzj();
                        String str22 = this.zza;
                        com.google.android.gms.internal.measurement.zzii zzc2 = zzb2.zzc();
                        zzj7.zzaw();
                        zzj7.zzg();
                        Preconditions.checkNotEmpty(str22);
                        Preconditions.checkNotNull(zzc2);
                        byte[] zzcc = zzc2.zzcc();
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("app_id", str22);
                        String str23 = str6;
                        contentValues2.put(str23, valueOf9);
                        String str24 = str9;
                        contentValues2.put(str24, zzcc);
                        try {
                        } catch (SQLiteException e20) {
                            e = e20;
                        }
                        try {
                            if (zzj7.zze().insertWithOnConflict("audience_filter_values", null, contentValues2, 5) == -1) {
                                zzj7.zzu.zzaV().zzb().zzb("Failed to insert filter results (got -1). appId", zzgu.zzl(str22));
                                str9 = str24;
                                str6 = str23;
                            } else {
                                str9 = str24;
                                str6 = str23;
                            }
                        } catch (SQLiteException e21) {
                            e = e21;
                            zzj7.zzu.zzaV().zzb().zzc("Error storing filter results. appId", zzgu.zzl(str22), e);
                            str9 = str24;
                            str6 = str23;
                        }
                    }
                    return arrayList;
                }
                emptyMap = Collections.emptyMap();
            }
            if (cursor2.moveToFirst()) {
            }
            if (map2.isEmpty()) {
            }
            if (list.isEmpty()) {
            }
            if (z) {
            }
        } catch (Throwable th14) {
            th = th14;
            cursor = cursor2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        map = emptyMap;
        zzav zzj32 = this.zzg.zzj();
        String str162 = this.zza;
        zzj32.zzaw();
        zzj32.zzg();
        Preconditions.checkNotEmpty(str162);
        cursor2 = zzj32.zze().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str162}, null, null, null);
    }

    @Override // com.google.android.gms.measurement.internal.zzos
    protected final boolean zzbb() {
        return false;
    }
}
