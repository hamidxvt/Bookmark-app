package com.ingenious.androidbookmarksalesupgrade.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ActivityLog.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b2\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bó\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u00104\u001a\u00020\u0003HÆ\u0003J\u0010\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u000b\u00106\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u000b\u0010E\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0018HÆ\u0003Jü\u0001\u0010H\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÆ\u0001¢\u0006\u0002\u0010IJ\u0013\u0010J\u001a\u00020K2\b\u0010L\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010M\u001a\u00020\u0003HÖ\u0001J\t\u0010N\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b#\u0010!R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b$\u0010!R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b%\u0010!R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b&\u0010!R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b'\u0010!R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b(\u0010!R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b)\u0010!R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b*\u0010!R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b+\u0010!R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b,\u0010!R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b-\u0010!R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b.\u0010!R\u0015\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b/\u0010\u001eR\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b0\u0010!R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b1\u0010!R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\b\n\u0000\u001a\u0004\b2\u00103¨\u0006O"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/ActivityLog;", "", Constant.VISIT_ID, "", "bookerId", "action", "", "subject", "schoolName", "principalName", "phone", FirebaseAnalytics.Param.LOCATION, "customerType", "createdAt", "visitDate", "visitDuration", "booksDelivered", "orderValue", "notes", "bookImageUrl", "subjectId", "details", "updateAt", "deliveredBooks", "Lcom/ingenious/androidbookmarksalesupgrade/model/DeliveredBooks;", "<init>", "(ILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Lcom/ingenious/androidbookmarksalesupgrade/model/DeliveredBooks;)V", "getId", "()I", "getBookerId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getAction", "()Ljava/lang/String;", "getSubject", "getSchoolName", "getPrincipalName", "getPhone", "getLocation", "getCustomerType", "getCreatedAt", "getVisitDate", "getVisitDuration", "getBooksDelivered", "getOrderValue", "getNotes", "getBookImageUrl", "getSubjectId", "getDetails", "getUpdateAt", "getDeliveredBooks", "()Lcom/ingenious/androidbookmarksalesupgrade/model/DeliveredBooks;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "copy", "(ILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Lcom/ingenious/androidbookmarksalesupgrade/model/DeliveredBooks;)Lcom/ingenious/androidbookmarksalesupgrade/model/ActivityLog;", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes8.dex */
public final /* data */ class ActivityLog {
    private final String action;
    private final String bookImageUrl;
    private final Integer bookerId;
    private final String booksDelivered;
    private final String createdAt;
    private final String customerType;
    private final DeliveredBooks deliveredBooks;
    private final String details;
    private final int id;
    private final String location;
    private final String notes;
    private final String orderValue;
    private final String phone;
    private final String principalName;
    private final String schoolName;
    private final String subject;
    private final Integer subjectId;
    private final String updateAt;
    private final String visitDate;
    private final String visitDuration;

    /* renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component11, reason: from getter */
    public final String getVisitDate() {
        return this.visitDate;
    }

    /* renamed from: component12, reason: from getter */
    public final String getVisitDuration() {
        return this.visitDuration;
    }

    /* renamed from: component13, reason: from getter */
    public final String getBooksDelivered() {
        return this.booksDelivered;
    }

    /* renamed from: component14, reason: from getter */
    public final String getOrderValue() {
        return this.orderValue;
    }

    /* renamed from: component15, reason: from getter */
    public final String getNotes() {
        return this.notes;
    }

    /* renamed from: component16, reason: from getter */
    public final String getBookImageUrl() {
        return this.bookImageUrl;
    }

    /* renamed from: component17, reason: from getter */
    public final Integer getSubjectId() {
        return this.subjectId;
    }

    /* renamed from: component18, reason: from getter */
    public final String getDetails() {
        return this.details;
    }

    /* renamed from: component19, reason: from getter */
    public final String getUpdateAt() {
        return this.updateAt;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getBookerId() {
        return this.bookerId;
    }

    /* renamed from: component20, reason: from getter */
    public final DeliveredBooks getDeliveredBooks() {
        return this.deliveredBooks;
    }

    /* renamed from: component3, reason: from getter */
    public final String getAction() {
        return this.action;
    }

    /* renamed from: component4, reason: from getter */
    public final String getSubject() {
        return this.subject;
    }

    /* renamed from: component5, reason: from getter */
    public final String getSchoolName() {
        return this.schoolName;
    }

    /* renamed from: component6, reason: from getter */
    public final String getPrincipalName() {
        return this.principalName;
    }

    /* renamed from: component7, reason: from getter */
    public final String getPhone() {
        return this.phone;
    }

    /* renamed from: component8, reason: from getter */
    public final String getLocation() {
        return this.location;
    }

    /* renamed from: component9, reason: from getter */
    public final String getCustomerType() {
        return this.customerType;
    }

    public final ActivityLog copy(int id, Integer bookerId, String action, String subject, String schoolName, String principalName, String phone, String location, String customerType, String createdAt, String visitDate, String visitDuration, String booksDelivered, String orderValue, String notes, String bookImageUrl, Integer subjectId, String details, String updateAt, DeliveredBooks deliveredBooks) {
        return new ActivityLog(id, bookerId, action, subject, schoolName, principalName, phone, location, customerType, createdAt, visitDate, visitDuration, booksDelivered, orderValue, notes, bookImageUrl, subjectId, details, updateAt, deliveredBooks);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActivityLog)) {
            return false;
        }
        ActivityLog activityLog = (ActivityLog) other;
        return this.id == activityLog.id && Intrinsics.areEqual(this.bookerId, activityLog.bookerId) && Intrinsics.areEqual(this.action, activityLog.action) && Intrinsics.areEqual(this.subject, activityLog.subject) && Intrinsics.areEqual(this.schoolName, activityLog.schoolName) && Intrinsics.areEqual(this.principalName, activityLog.principalName) && Intrinsics.areEqual(this.phone, activityLog.phone) && Intrinsics.areEqual(this.location, activityLog.location) && Intrinsics.areEqual(this.customerType, activityLog.customerType) && Intrinsics.areEqual(this.createdAt, activityLog.createdAt) && Intrinsics.areEqual(this.visitDate, activityLog.visitDate) && Intrinsics.areEqual(this.visitDuration, activityLog.visitDuration) && Intrinsics.areEqual(this.booksDelivered, activityLog.booksDelivered) && Intrinsics.areEqual(this.orderValue, activityLog.orderValue) && Intrinsics.areEqual(this.notes, activityLog.notes) && Intrinsics.areEqual(this.bookImageUrl, activityLog.bookImageUrl) && Intrinsics.areEqual(this.subjectId, activityLog.subjectId) && Intrinsics.areEqual(this.details, activityLog.details) && Intrinsics.areEqual(this.updateAt, activityLog.updateAt) && Intrinsics.areEqual(this.deliveredBooks, activityLog.deliveredBooks);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((Integer.hashCode(this.id) * 31) + (this.bookerId == null ? 0 : this.bookerId.hashCode())) * 31) + (this.action == null ? 0 : this.action.hashCode())) * 31) + (this.subject == null ? 0 : this.subject.hashCode())) * 31) + (this.schoolName == null ? 0 : this.schoolName.hashCode())) * 31) + (this.principalName == null ? 0 : this.principalName.hashCode())) * 31) + (this.phone == null ? 0 : this.phone.hashCode())) * 31) + (this.location == null ? 0 : this.location.hashCode())) * 31) + (this.customerType == null ? 0 : this.customerType.hashCode())) * 31) + (this.createdAt == null ? 0 : this.createdAt.hashCode())) * 31) + (this.visitDate == null ? 0 : this.visitDate.hashCode())) * 31) + (this.visitDuration == null ? 0 : this.visitDuration.hashCode())) * 31) + (this.booksDelivered == null ? 0 : this.booksDelivered.hashCode())) * 31) + (this.orderValue == null ? 0 : this.orderValue.hashCode())) * 31) + (this.notes == null ? 0 : this.notes.hashCode())) * 31) + (this.bookImageUrl == null ? 0 : this.bookImageUrl.hashCode())) * 31) + (this.subjectId == null ? 0 : this.subjectId.hashCode())) * 31) + (this.details == null ? 0 : this.details.hashCode())) * 31) + (this.updateAt == null ? 0 : this.updateAt.hashCode())) * 31) + (this.deliveredBooks != null ? this.deliveredBooks.hashCode() : 0);
    }

    public String toString() {
        return "ActivityLog(id=" + this.id + ", bookerId=" + this.bookerId + ", action=" + this.action + ", subject=" + this.subject + ", schoolName=" + this.schoolName + ", principalName=" + this.principalName + ", phone=" + this.phone + ", location=" + this.location + ", customerType=" + this.customerType + ", createdAt=" + this.createdAt + ", visitDate=" + this.visitDate + ", visitDuration=" + this.visitDuration + ", booksDelivered=" + this.booksDelivered + ", orderValue=" + this.orderValue + ", notes=" + this.notes + ", bookImageUrl=" + this.bookImageUrl + ", subjectId=" + this.subjectId + ", details=" + this.details + ", updateAt=" + this.updateAt + ", deliveredBooks=" + this.deliveredBooks + ")";
    }

    public ActivityLog(int id, Integer bookerId, String action, String subject, String schoolName, String principalName, String phone, String location, String customerType, String createdAt, String visitDate, String visitDuration, String booksDelivered, String orderValue, String notes, String bookImageUrl, Integer subjectId, String details, String updateAt, DeliveredBooks deliveredBooks) {
        this.id = id;
        this.bookerId = bookerId;
        this.action = action;
        this.subject = subject;
        this.schoolName = schoolName;
        this.principalName = principalName;
        this.phone = phone;
        this.location = location;
        this.customerType = customerType;
        this.createdAt = createdAt;
        this.visitDate = visitDate;
        this.visitDuration = visitDuration;
        this.booksDelivered = booksDelivered;
        this.orderValue = orderValue;
        this.notes = notes;
        this.bookImageUrl = bookImageUrl;
        this.subjectId = subjectId;
        this.details = details;
        this.updateAt = updateAt;
        this.deliveredBooks = deliveredBooks;
    }

    public /* synthetic */ ActivityLog(int i, Integer num, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, Integer num2, String str15, String str16, DeliveredBooks deliveredBooks, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : num, (i2 & 4) != 0 ? null : str, (i2 & 8) != 0 ? null : str2, (i2 & 16) != 0 ? null : str3, (i2 & 32) != 0 ? null : str4, (i2 & 64) != 0 ? null : str5, (i2 & 128) != 0 ? null : str6, (i2 & 256) != 0 ? null : str7, (i2 & 512) != 0 ? null : str8, (i2 & 1024) != 0 ? null : str9, (i2 & 2048) != 0 ? null : str10, (i2 & 4096) != 0 ? null : str11, (i2 & 8192) != 0 ? null : str12, (i2 & 16384) != 0 ? null : str13, (i2 & 32768) != 0 ? null : str14, (i2 & 65536) != 0 ? null : num2, (i2 & 131072) != 0 ? null : str15, (i2 & 262144) != 0 ? null : str16, (i2 & 524288) == 0 ? deliveredBooks : null);
    }

    public final int getId() {
        return this.id;
    }

    public final Integer getBookerId() {
        return this.bookerId;
    }

    public final String getAction() {
        return this.action;
    }

    public final String getSubject() {
        return this.subject;
    }

    public final String getSchoolName() {
        return this.schoolName;
    }

    public final String getPrincipalName() {
        return this.principalName;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final String getLocation() {
        return this.location;
    }

    public final String getCustomerType() {
        return this.customerType;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final String getVisitDate() {
        return this.visitDate;
    }

    public final String getVisitDuration() {
        return this.visitDuration;
    }

    public final String getBooksDelivered() {
        return this.booksDelivered;
    }

    public final String getOrderValue() {
        return this.orderValue;
    }

    public final String getNotes() {
        return this.notes;
    }

    public final String getBookImageUrl() {
        return this.bookImageUrl;
    }

    public final Integer getSubjectId() {
        return this.subjectId;
    }

    public final String getDetails() {
        return this.details;
    }

    public final String getUpdateAt() {
        return this.updateAt;
    }

    public final DeliveredBooks getDeliveredBooks() {
        return this.deliveredBooks;
    }
}
