package com.ingenious.androidbookmarksalesupgrade.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FaqModel.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/FaqModel;", "", "question", "", "answer", "isExpanded", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getQuestion", "()Ljava/lang/String;", "getAnswer", "()Z", "setExpanded", "(Z)V", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes8.dex */
public final /* data */ class FaqModel {
    private final String answer;
    private boolean isExpanded;
    private final String question;

    public static /* synthetic */ FaqModel copy$default(FaqModel faqModel, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = faqModel.question;
        }
        if ((i & 2) != 0) {
            str2 = faqModel.answer;
        }
        if ((i & 4) != 0) {
            z = faqModel.isExpanded;
        }
        return faqModel.copy(str, str2, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getQuestion() {
        return this.question;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAnswer() {
        return this.answer;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsExpanded() {
        return this.isExpanded;
    }

    public final FaqModel copy(String question, String answer, boolean isExpanded) {
        Intrinsics.checkNotNullParameter(question, "question");
        Intrinsics.checkNotNullParameter(answer, "answer");
        return new FaqModel(question, answer, isExpanded);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FaqModel)) {
            return false;
        }
        FaqModel faqModel = (FaqModel) other;
        return Intrinsics.areEqual(this.question, faqModel.question) && Intrinsics.areEqual(this.answer, faqModel.answer) && this.isExpanded == faqModel.isExpanded;
    }

    public int hashCode() {
        return (((this.question.hashCode() * 31) + this.answer.hashCode()) * 31) + Boolean.hashCode(this.isExpanded);
    }

    public String toString() {
        return "FaqModel(question=" + this.question + ", answer=" + this.answer + ", isExpanded=" + this.isExpanded + ")";
    }

    public FaqModel(String question, String answer, boolean isExpanded) {
        Intrinsics.checkNotNullParameter(question, "question");
        Intrinsics.checkNotNullParameter(answer, "answer");
        this.question = question;
        this.answer = answer;
        this.isExpanded = isExpanded;
    }

    public /* synthetic */ FaqModel(String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? false : z);
    }

    public final String getQuestion() {
        return this.question;
    }

    public final String getAnswer() {
        return this.answer;
    }

    public final boolean isExpanded() {
        return this.isExpanded;
    }

    public final void setExpanded(boolean z) {
        this.isExpanded = z;
    }
}
