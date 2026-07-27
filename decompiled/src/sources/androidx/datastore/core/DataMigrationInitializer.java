package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: DataMigrationInitializer.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u0004*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0004B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/datastore/core/DataMigrationInitializer;", "T", "", "()V", "Companion", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class DataMigrationInitializer<T> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: DataMigrationInitializer.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JV\u0010\u0003\u001a3\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002H\u00060\u0005¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004\"\u0004\b\u0001\u0010\u00062\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u000e0\r¢\u0006\u0002\u0010\u000fJ6\u0010\u0010\u001a\u00020\u000b\"\u0004\b\u0001\u0010\u00062\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u000e0\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005H\u0082@¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/datastore/core/DataMigrationInitializer$Companion;", "", "()V", "getInitializer", "Lkotlin/Function2;", "Landroidx/datastore/core/InitializerApi;", "T", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "api", "Lkotlin/coroutines/Continuation;", "", "migrations", "", "Landroidx/datastore/core/DataMigration;", "(Ljava/util/List;)Lkotlin/jvm/functions/Function2;", "runMigrations", "(Ljava/util/List;Landroidx/datastore/core/InitializerApi;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T> Function2<InitializerApi<T>, Continuation<? super Unit>, Object> getInitializer(List<? extends DataMigration<T>> migrations) {
            Intrinsics.checkNotNullParameter(migrations, "migrations");
            return new DataMigrationInitializer$Companion$getInitializer$1(migrations, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0079  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0099  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x009c  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00af  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00b2  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x003d  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x0045  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0099 -> B:14:0x0073). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x009c -> B:14:0x0073). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final <T> Object runMigrations(List<? extends DataMigration<T>> list, InitializerApi<T> initializerApi, Continuation<? super Unit> continuation) {
            DataMigrationInitializer$Companion$runMigrations$1 dataMigrationInitializer$Companion$runMigrations$1;
            DataMigrationInitializer$Companion$runMigrations$1 dataMigrationInitializer$Companion$runMigrations$12;
            List list2;
            Iterator<T> it;
            Ref.ObjectRef objectRef;
            Object obj;
            Throwable th;
            if (continuation instanceof DataMigrationInitializer$Companion$runMigrations$1) {
                dataMigrationInitializer$Companion$runMigrations$1 = (DataMigrationInitializer$Companion$runMigrations$1) continuation;
                if ((dataMigrationInitializer$Companion$runMigrations$1.label & Integer.MIN_VALUE) != 0) {
                    dataMigrationInitializer$Companion$runMigrations$1.label -= Integer.MIN_VALUE;
                    dataMigrationInitializer$Companion$runMigrations$12 = dataMigrationInitializer$Companion$runMigrations$1;
                    Object obj2 = dataMigrationInitializer$Companion$runMigrations$12.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (dataMigrationInitializer$Companion$runMigrations$12.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj2);
                            ArrayList arrayList = new ArrayList();
                            Function2<? super T, ? super Continuation<? super T>, ? extends Object> dataMigrationInitializer$Companion$runMigrations$2 = new DataMigrationInitializer$Companion$runMigrations$2<>(list, arrayList, null);
                            dataMigrationInitializer$Companion$runMigrations$12.L$0 = arrayList;
                            dataMigrationInitializer$Companion$runMigrations$12.label = 1;
                            if (initializerApi.updateData(dataMigrationInitializer$Companion$runMigrations$2, dataMigrationInitializer$Companion$runMigrations$12) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            list2 = arrayList;
                            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                            it = list2.iterator();
                            objectRef = objectRef2;
                            while (it.hasNext()) {
                                Function1 function1 = (Function1) it.next();
                                try {
                                } catch (Throwable th2) {
                                    obj = (T) th2;
                                    if (objectRef.element != null) {
                                        objectRef.element = (T) obj;
                                    } else {
                                        T t = objectRef.element;
                                        Intrinsics.checkNotNull(t);
                                        ExceptionsKt.addSuppressed((Throwable) t, (Throwable) obj);
                                    }
                                    while (it.hasNext()) {
                                    }
                                    th = (Throwable) objectRef.element;
                                    if (th == null) {
                                    }
                                }
                                dataMigrationInitializer$Companion$runMigrations$12.L$0 = objectRef;
                                dataMigrationInitializer$Companion$runMigrations$12.L$1 = it;
                                dataMigrationInitializer$Companion$runMigrations$12.label = 2;
                                if (function1.invoke(dataMigrationInitializer$Companion$runMigrations$12) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                            th = (Throwable) objectRef.element;
                            if (th == null) {
                                return Unit.INSTANCE;
                            }
                            throw th;
                        case 1:
                            list2 = (List) dataMigrationInitializer$Companion$runMigrations$12.L$0;
                            ResultKt.throwOnFailure(obj2);
                            Ref.ObjectRef objectRef22 = new Ref.ObjectRef();
                            it = list2.iterator();
                            objectRef = objectRef22;
                            while (it.hasNext()) {
                            }
                            th = (Throwable) objectRef.element;
                            if (th == null) {
                            }
                            break;
                        case 2:
                            it = (Iterator) dataMigrationInitializer$Companion$runMigrations$12.L$1;
                            objectRef = (Ref.ObjectRef) dataMigrationInitializer$Companion$runMigrations$12.L$0;
                            try {
                                ResultKt.throwOnFailure(obj2);
                            } catch (Throwable 
                            /*  JADX ERROR: Method code generation error
                                java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.getCodeVar()" because "ssaVar" is null
                                	at jadx.core.codegen.RegionGen.makeCatchBlock(RegionGen.java:369)
                                	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:332)
                                	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                	at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:267)
                                	at jadx.core.dex.regions.SwitchRegion.generate(SwitchRegion.java:84)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                */
                            /*
                                this = this;
                                boolean r0 = r10 instanceof androidx.datastore.core.DataMigrationInitializer$Companion$runMigrations$1
                                if (r0 == 0) goto L14
                                r0 = r10
                                androidx.datastore.core.DataMigrationInitializer$Companion$runMigrations$1 r0 = (androidx.datastore.core.DataMigrationInitializer$Companion$runMigrations$1) r0
                                int r1 = r0.label
                                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                r1 = r1 & r2
                                if (r1 == 0) goto L14
                                int r10 = r0.label
                                int r10 = r10 - r2
                                r0.label = r10
                                goto L19
                            L14:
                                androidx.datastore.core.DataMigrationInitializer$Companion$runMigrations$1 r0 = new androidx.datastore.core.DataMigrationInitializer$Companion$runMigrations$1
                                r0.<init>(r7, r10)
                            L19:
                                r10 = r0
                                java.lang.Object r0 = r10.result
                                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                                int r2 = r10.label
                                switch(r2) {
                                    case 0: goto L45;
                                    case 1: goto L3d;
                                    case 2: goto L2d;
                                    default: goto L25;
                                }
                            L25:
                                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                                java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                                r8.<init>(r9)
                                throw r8
                            L2d:
                                r8 = 0
                                r9 = 0
                                java.lang.Object r2 = r10.L$1
                                java.util.Iterator r2 = (java.util.Iterator) r2
                                java.lang.Object r3 = r10.L$0
                                kotlin.jvm.internal.Ref$ObjectRef r3 = (kotlin.jvm.internal.Ref.ObjectRef) r3
                                kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> L3b
                                goto L90
                            L3b:
                                r4 = move-exception
                                goto L95
                            L3d:
                                java.lang.Object r8 = r10.L$0
                                java.util.List r8 = (java.util.List) r8
                                kotlin.ResultKt.throwOnFailure(r0)
                                goto L64
                            L45:
                                kotlin.ResultKt.throwOnFailure(r0)
                                java.util.ArrayList r2 = new java.util.ArrayList
                                r2.<init>()
                                java.util.List r2 = (java.util.List) r2
                                androidx.datastore.core.DataMigrationInitializer$Companion$runMigrations$2 r3 = new androidx.datastore.core.DataMigrationInitializer$Companion$runMigrations$2
                                r4 = 0
                                r3.<init>(r8, r2, r4)
                                kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
                                r10.L$0 = r2
                                r4 = 1
                                r10.label = r4
                                java.lang.Object r8 = r9.updateData(r3, r10)
                                if (r8 != r1) goto L63
                                return r1
                            L63:
                                r8 = r2
                            L64:
                                kotlin.jvm.internal.Ref$ObjectRef r9 = new kotlin.jvm.internal.Ref$ObjectRef
                                r9.<init>()
                                java.lang.Iterable r8 = (java.lang.Iterable) r8
                                r2 = 0
                                java.util.Iterator r3 = r8.iterator()
                                r8 = r2
                                r2 = r3
                                r3 = r9
                            L73:
                                boolean r9 = r2.hasNext()
                                if (r9 == 0) goto La8
                                java.lang.Object r9 = r2.next()
                                kotlin.jvm.functions.Function1 r9 = (kotlin.jvm.functions.Function1) r9
                                r4 = 0
                                r10.L$0 = r3     // Catch: java.lang.Throwable -> L91
                                r10.L$1 = r2     // Catch: java.lang.Throwable -> L91
                                r5 = 2
                                r10.label = r5     // Catch: java.lang.Throwable -> L91
                                java.lang.Object r5 = r9.invoke(r10)     // Catch: java.lang.Throwable -> L91
                                if (r5 != r1) goto L8f
                                return r1
                            L8f:
                                r9 = r4
                            L90:
                                goto La6
                            L91:
                                r9 = move-exception
                                r6 = r4
                                r4 = r9
                                r9 = r6
                            L95:
                                T r5 = r3.element
                                if (r5 != 0) goto L9c
                                r3.element = r4
                                goto La6
                            L9c:
                                T r5 = r3.element
                                kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                                java.lang.Throwable r5 = (java.lang.Throwable) r5
                                kotlin.ExceptionsKt.addSuppressed(r5, r4)
                            La6:
                                goto L73
                            La8:
                                T r8 = r3.element
                                java.lang.Throwable r8 = (java.lang.Throwable) r8
                                if (r8 != 0) goto Lb2
                                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                                return r8
                            Lb2:
                                r9 = 0
                                throw r8
                            */
                            throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataMigrationInitializer.Companion.runMigrations(java.util.List, androidx.datastore.core.InitializerApi, kotlin.coroutines.Continuation):java.lang.Object");
                        }
                    }
                }
