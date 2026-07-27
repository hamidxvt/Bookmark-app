package androidx.sqlite.db;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SupportSQLiteQueryBuilder.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001d\u0010\b\u001a\u00020\u00002\u0010\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0003\u0018\u00010\u0006¢\u0006\u0002\u0010\u0011J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\n\u001a\u00020\u0000J\u0010\u0010\f\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u0003J\u0010\u0010\r\u001a\u00020\u00002\b\u0010\r\u001a\u0004\u0018\u00010\u0003J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0003J\u0010\u0010\u000f\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003J)\u0010\u0010\u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u00032\u0012\u0010\u0005\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0006¢\u0006\u0002\u0010\u0014J\"\u0010\u0015\u001a\u00020\u0016*\u00060\u0017j\u0002`\u00182\u0006\u0010\u0019\u001a\u00020\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u0002J%\u0010\u001b\u001a\u00020\u0016*\u00060\u0017j\u0002`\u00182\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0006H\u0002¢\u0006\u0002\u0010\u001cR\u001c\u0010\u0005\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007R\u001a\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0003\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteQueryBuilder;", "", "table", "", "(Ljava/lang/String;)V", "bindArgs", "", "[Ljava/lang/Object;", "columns", "[Ljava/lang/String;", "distinct", "", "groupBy", "having", "limit", "orderBy", "selection", "([Ljava/lang/String;)Landroidx/sqlite/db/SupportSQLiteQueryBuilder;", "create", "Landroidx/sqlite/db/SupportSQLiteQuery;", "(Ljava/lang/String;[Ljava/lang/Object;)Landroidx/sqlite/db/SupportSQLiteQueryBuilder;", "appendClause", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", AppMeasurementSdk.ConditionalUserProperty.NAME, "clause", "appendColumns", "(Ljava/lang/StringBuilder;[Ljava/lang/String;)V", "Companion", "sqlite_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class SupportSQLiteQueryBuilder {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Pattern limitPattern = Pattern.compile("\\s*\\d+\\s*(,\\s*\\d+\\s*)?");
    private Object[] bindArgs;
    private String[] columns;
    private boolean distinct;
    private String groupBy;
    private String having;
    private String limit;
    private String orderBy;
    private String selection;
    private final String table;

    public /* synthetic */ SupportSQLiteQueryBuilder(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    @JvmStatic
    public static final SupportSQLiteQueryBuilder builder(String str) {
        return INSTANCE.builder(str);
    }

    private SupportSQLiteQueryBuilder(String table) {
        this.table = table;
    }

    public final SupportSQLiteQueryBuilder distinct() {
        SupportSQLiteQueryBuilder $this$distinct_u24lambda_u240 = this;
        $this$distinct_u24lambda_u240.distinct = true;
        return this;
    }

    public final SupportSQLiteQueryBuilder columns(String[] columns) {
        SupportSQLiteQueryBuilder $this$columns_u24lambda_u241 = this;
        $this$columns_u24lambda_u241.columns = columns;
        return this;
    }

    public final SupportSQLiteQueryBuilder selection(String selection, Object[] bindArgs) {
        SupportSQLiteQueryBuilder $this$selection_u24lambda_u242 = this;
        $this$selection_u24lambda_u242.selection = selection;
        $this$selection_u24lambda_u242.bindArgs = bindArgs;
        return this;
    }

    public final SupportSQLiteQueryBuilder groupBy(String groupBy) {
        SupportSQLiteQueryBuilder $this$groupBy_u24lambda_u243 = this;
        $this$groupBy_u24lambda_u243.groupBy = groupBy;
        return this;
    }

    public final SupportSQLiteQueryBuilder having(String having) {
        SupportSQLiteQueryBuilder $this$having_u24lambda_u244 = this;
        $this$having_u24lambda_u244.having = having;
        return this;
    }

    public final SupportSQLiteQueryBuilder orderBy(String orderBy) {
        SupportSQLiteQueryBuilder $this$orderBy_u24lambda_u245 = this;
        $this$orderBy_u24lambda_u245.orderBy = orderBy;
        return this;
    }

    public final SupportSQLiteQueryBuilder limit(String limit) {
        Intrinsics.checkNotNullParameter(limit, "limit");
        SupportSQLiteQueryBuilder $this$limit_u24lambda_u247 = this;
        boolean patternMatches = limitPattern.matcher(limit).matches();
        boolean z = true;
        if (!(limit.length() == 0) && !patternMatches) {
            z = false;
        }
        if (!z) {
            throw new IllegalArgumentException(("invalid LIMIT clauses:" + limit).toString());
        }
        $this$limit_u24lambda_u247.limit = limit;
        return this;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x004d, code lost:
    
        if ((r5.length == 0) != false) goto L32;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final SupportSQLiteQuery create() {
        boolean z;
        String str = this.groupBy;
        boolean z2 = false;
        if (str == null || str.length() == 0) {
            String str2 = this.having;
            if (!(str2 == null || str2.length() == 0)) {
                z = false;
                if (z) {
                    throw new IllegalArgumentException("HAVING clauses are only permitted when using a groupBy clause".toString());
                }
                StringBuilder $this$create_u24lambda_u249 = new StringBuilder(120);
                $this$create_u24lambda_u249.append("SELECT ");
                if (this.distinct) {
                    $this$create_u24lambda_u249.append("DISTINCT ");
                }
                String[] strArr = this.columns;
                if (strArr != null) {
                }
                z2 = true;
                if (!z2) {
                    String[] strArr2 = this.columns;
                    Intrinsics.checkNotNull(strArr2);
                    appendColumns($this$create_u24lambda_u249, strArr2);
                } else {
                    $this$create_u24lambda_u249.append("* ");
                }
                $this$create_u24lambda_u249.append("FROM ");
                $this$create_u24lambda_u249.append(this.table);
                appendClause($this$create_u24lambda_u249, " WHERE ", this.selection);
                appendClause($this$create_u24lambda_u249, " GROUP BY ", this.groupBy);
                appendClause($this$create_u24lambda_u249, " HAVING ", this.having);
                appendClause($this$create_u24lambda_u249, " ORDER BY ", this.orderBy);
                appendClause($this$create_u24lambda_u249, " LIMIT ", this.limit);
                String query = $this$create_u24lambda_u249.toString();
                Intrinsics.checkNotNullExpressionValue(query, "StringBuilder(capacity).…builderAction).toString()");
                return new SimpleSQLiteQuery(query, this.bindArgs);
            }
        }
        z = true;
        if (z) {
        }
    }

    private final void appendClause(StringBuilder $this$appendClause, String name, String clause) {
        String str = clause;
        if (!(str == null || str.length() == 0)) {
            $this$appendClause.append(name);
            $this$appendClause.append(clause);
        }
    }

    private final void appendColumns(StringBuilder $this$appendColumns, String[] columns) {
        int n = columns.length;
        for (int i = 0; i < n; i++) {
            String column = columns[i];
            if (i > 0) {
                $this$appendColumns.append(", ");
            }
            $this$appendColumns.append(column);
        }
        $this$appendColumns.append(' ');
    }

    /* compiled from: SupportSQLiteQueryBuilder.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteQueryBuilder$Companion;", "", "()V", "limitPattern", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "builder", "Landroidx/sqlite/db/SupportSQLiteQueryBuilder;", "tableName", "", "sqlite_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final SupportSQLiteQueryBuilder builder(String tableName) {
            Intrinsics.checkNotNullParameter(tableName, "tableName");
            return new SupportSQLiteQueryBuilder(tableName, null);
        }
    }
}
