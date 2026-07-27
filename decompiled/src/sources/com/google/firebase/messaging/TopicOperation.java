package com.google.firebase.messaging;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import java.util.regex.Pattern;

/* loaded from: classes16.dex */
final class TopicOperation {
    private static final String OLD_TOPIC_PREFIX = "/topics/";
    static final String OPERATION_PAIR_DIVIDER = "!";
    private static final String TOPIC_NAME_PATTERN = "[a-zA-Z0-9-_.~%]{1,900}";
    private static final Pattern TOPIC_NAME_REGEXP = Pattern.compile(TOPIC_NAME_PATTERN);
    private final String operation;
    private final String serializedString;
    private final String topic;

    private TopicOperation(String operation, String topic) {
        this.topic = normalizeTopicOrThrow(topic, operation);
        this.operation = operation;
        this.serializedString = operation + OPERATION_PAIR_DIVIDER + topic;
    }

    private static String normalizeTopicOrThrow(String topic, String methodName) {
        if (topic != null && topic.startsWith(OLD_TOPIC_PREFIX)) {
            Log.w(Constants.TAG, String.format("Format /topics/topic-name is deprecated. Only 'topic-name' should be used in %s.", methodName));
            topic = topic.substring(OLD_TOPIC_PREFIX.length());
        }
        if (topic == null || !TOPIC_NAME_REGEXP.matcher(topic).matches()) {
            throw new IllegalArgumentException(String.format("Invalid topic name: %s does not match the allowed format %s.", topic, TOPIC_NAME_PATTERN));
        }
        return topic;
    }

    public static TopicOperation subscribe(String topic) {
        return new TopicOperation("S", topic);
    }

    public static TopicOperation unsubscribe(String topic) {
        return new TopicOperation("U", topic);
    }

    static TopicOperation from(String entry) {
        if (TextUtils.isEmpty(entry)) {
            return null;
        }
        String[] splits = entry.split(OPERATION_PAIR_DIVIDER, -1);
        if (splits.length != 2) {
            return null;
        }
        return new TopicOperation(splits[0], splits[1]);
    }

    public String getTopic() {
        return this.topic;
    }

    public String getOperation() {
        return this.operation;
    }

    public String serialize() {
        return this.serializedString;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TopicOperation)) {
            return false;
        }
        TopicOperation that = (TopicOperation) obj;
        return this.topic.equals(that.topic) && this.operation.equals(that.operation);
    }

    public int hashCode() {
        return Objects.hashCode(this.operation, this.topic);
    }
}
