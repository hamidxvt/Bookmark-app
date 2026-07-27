package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: DataMigrationInitializer.kt */
@Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0003\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u0001H\u008a@"}, d2 = {"<anonymous>", "T", "startingData"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.datastore.core.DataMigrationInitializer$Companion$runMigrations$2", f = "DataMigrationInitializer.kt", i = {0, 0}, l = {44, 46}, m = "invokeSuspend", n = {"migration", Constants.ScionAnalytics.MessageType.DATA_MESSAGE}, s = {"L$2", "L$3"})
/* loaded from: classes.dex */
final class DataMigrationInitializer$Companion$runMigrations$2<T> extends SuspendLambda implements Function2<T, Continuation<? super T>, Object> {
    final /* synthetic */ List<Function1<Continuation<? super Unit>, Object>> $cleanUps;
    final /* synthetic */ List<DataMigration<T>> $migrations;
    /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    DataMigrationInitializer$Companion$runMigrations$2(List<? extends DataMigration<T>> list, List<Function1<Continuation<? super Unit>, Object>> list2, Continuation<? super DataMigrationInitializer$Companion$runMigrations$2> continuation) {
        super(2, continuation);
        this.$migrations = list;
        this.$cleanUps = list2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DataMigrationInitializer$Companion$runMigrations$2 dataMigrationInitializer$Companion$runMigrations$2 = new DataMigrationInitializer$Companion$runMigrations$2(this.$migrations, this.$cleanUps, continuation);
        dataMigrationInitializer$Companion$runMigrations$2.L$0 = obj;
        return dataMigrationInitializer$Companion$runMigrations$2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((DataMigrationInitializer$Companion$runMigrations$2<T>) obj, (Continuation<? super DataMigrationInitializer$Companion$runMigrations$2<T>>) obj2);
    }

    public final Object invoke(T t, Continuation<? super T> continuation) {
        return ((DataMigrationInitializer$Companion$runMigrations$2) create(t, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00ac A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x009d -> B:7:0x009e). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x00a4 -> B:8:0x00a7). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        DataMigrationInitializer$Companion$runMigrations$2<T> dataMigrationInitializer$Companion$runMigrations$2;
        Object obj2;
        List<Function1<Continuation<? super Unit>, Object>> list;
        Iterator<T> it;
        Object obj3;
        List<Function1<Continuation<? super Unit>, Object>> list2;
        DataMigration dataMigration;
        DataMigrationInitializer$Companion$runMigrations$2<T> dataMigrationInitializer$Companion$runMigrations$22;
        Object obj4;
        Object obj5;
        Iterator<T> it2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                dataMigrationInitializer$Companion$runMigrations$2 = this;
                obj2 = dataMigrationInitializer$Companion$runMigrations$2.L$0;
                List<DataMigration<T>> list3 = dataMigrationInitializer$Companion$runMigrations$2.$migrations;
                list = dataMigrationInitializer$Companion$runMigrations$2.$cleanUps;
                it = list3.iterator();
                if (!it.hasNext()) {
                    DataMigration dataMigration2 = (DataMigration) it.next();
                    dataMigrationInitializer$Companion$runMigrations$2.L$0 = list;
                    dataMigrationInitializer$Companion$runMigrations$2.L$1 = it;
                    dataMigrationInitializer$Companion$runMigrations$2.L$2 = dataMigration2;
                    dataMigrationInitializer$Companion$runMigrations$2.L$3 = obj2;
                    dataMigrationInitializer$Companion$runMigrations$2.label = 1;
                    Object shouldMigrate = dataMigration2.shouldMigrate(obj2, dataMigrationInitializer$Companion$runMigrations$2);
                    if (shouldMigrate == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Object obj6 = coroutine_suspended;
                    obj5 = obj;
                    obj = shouldMigrate;
                    list2 = list;
                    obj3 = obj2;
                    dataMigrationInitializer$Companion$runMigrations$22 = dataMigrationInitializer$Companion$runMigrations$2;
                    obj4 = obj6;
                    dataMigration = dataMigration2;
                    if (!((Boolean) obj).booleanValue()) {
                        list2.add(new DataMigrationInitializer$Companion$runMigrations$2$1$1(dataMigration, null));
                        dataMigrationInitializer$Companion$runMigrations$22.L$0 = list2;
                        dataMigrationInitializer$Companion$runMigrations$22.L$1 = it;
                        dataMigrationInitializer$Companion$runMigrations$22.L$2 = null;
                        dataMigrationInitializer$Companion$runMigrations$22.L$3 = null;
                        dataMigrationInitializer$Companion$runMigrations$22.label = 2;
                        obj = dataMigration.migrate(obj3, dataMigrationInitializer$Companion$runMigrations$22);
                        if (obj == obj4) {
                            return obj4;
                        }
                        it2 = it;
                        it = it2;
                        obj3 = obj;
                        obj = obj5;
                        coroutine_suspended = obj4;
                        dataMigrationInitializer$Companion$runMigrations$2 = dataMigrationInitializer$Companion$runMigrations$22;
                        obj2 = obj3;
                        list = list2;
                        if (!it.hasNext()) {
                            return obj2;
                        }
                    } else {
                        obj = obj5;
                        coroutine_suspended = obj4;
                        dataMigrationInitializer$Companion$runMigrations$2 = dataMigrationInitializer$Companion$runMigrations$22;
                        obj2 = obj3;
                        list = list2;
                        if (!it.hasNext()) {
                        }
                    }
                }
            case 1:
                obj3 = this.L$3;
                DataMigration dataMigration3 = (DataMigration) this.L$2;
                it = (Iterator) this.L$1;
                List<Function1<Continuation<? super Unit>, Object>> list4 = (List) this.L$0;
                ResultKt.throwOnFailure(obj);
                list2 = list4;
                dataMigration = dataMigration3;
                dataMigrationInitializer$Companion$runMigrations$22 = this;
                obj4 = coroutine_suspended;
                obj5 = obj;
                if (!((Boolean) obj).booleanValue()) {
                }
                break;
            case 2:
                it2 = (Iterator) this.L$1;
                List<Function1<Continuation<? super Unit>, Object>> list5 = (List) this.L$0;
                ResultKt.throwOnFailure(obj);
                list2 = list5;
                dataMigrationInitializer$Companion$runMigrations$22 = this;
                obj4 = coroutine_suspended;
                obj5 = obj;
                it = it2;
                obj3 = obj;
                obj = obj5;
                coroutine_suspended = obj4;
                dataMigrationInitializer$Companion$runMigrations$2 = dataMigrationInitializer$Companion$runMigrations$22;
                obj2 = obj3;
                list = list2;
                if (!it.hasNext()) {
                }
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
