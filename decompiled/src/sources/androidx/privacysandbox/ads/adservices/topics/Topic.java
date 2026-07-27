package androidx.privacysandbox.ads.adservices.topics;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: Topic.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0006H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Landroidx/privacysandbox/ads/adservices/topics/Topic;", "", "taxonomyVersion", "", "modelVersion", "topicId", "", "(JJI)V", "getModelVersion", "()J", "getTaxonomyVersion", "getTopicId", "()I", "equals", "", "other", "hashCode", "toString", "", "ads-adservices_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class Topic {

    /* renamed from: modelVersion, reason: from kotlin metadata and from toString */
    private final long ModelVersion;

    /* renamed from: taxonomyVersion, reason: from kotlin metadata and from toString */
    private final long TaxonomyVersion;

    /* renamed from: topicId, reason: from kotlin metadata and from toString */
    private final int TopicCode;

    public Topic(long taxonomyVersion, long modelVersion, int topicId) {
        this.TaxonomyVersion = taxonomyVersion;
        this.ModelVersion = modelVersion;
        this.TopicCode = topicId;
    }

    public final long getModelVersion() {
        return this.ModelVersion;
    }

    public final long getTaxonomyVersion() {
        return this.TaxonomyVersion;
    }

    /* renamed from: getTopicId, reason: from getter */
    public final int getTopicCode() {
        return this.TopicCode;
    }

    public String toString() {
        String taxonomyVersionString = "TaxonomyVersion=" + this.TaxonomyVersion + ", ModelVersion=" + this.ModelVersion + ", TopicCode=" + this.TopicCode + " }";
        return "Topic { " + taxonomyVersionString;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Topic) {
            return this.TaxonomyVersion == ((Topic) other).TaxonomyVersion && this.ModelVersion == ((Topic) other).ModelVersion && this.TopicCode == ((Topic) other).TopicCode;
        }
        return false;
    }

    public int hashCode() {
        int hash = Long.hashCode(this.TaxonomyVersion);
        return (((hash * 31) + Long.hashCode(this.ModelVersion)) * 31) + Integer.hashCode(this.TopicCode);
    }
}
