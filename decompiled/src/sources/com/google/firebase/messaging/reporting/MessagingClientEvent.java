package com.google.firebase.messaging.reporting;

import com.google.firebase.encoders.proto.ProtoEnum;

/* loaded from: classes16.dex */
public final class MessagingClientEvent {
    private static final MessagingClientEvent DEFAULT_INSTANCE = new Builder().build();
    private final String analytics_label_;
    private final long bulk_id_;
    private final long campaign_id_;
    private final String collapse_key_;
    private final String composer_label_;
    private final Event event_;
    private final String instance_id_;
    private final String message_id_;
    private final MessageType message_type_;
    private final String package_name_;
    private final int priority_;
    private final long project_number_;
    private final SDKPlatform sdk_platform_;
    private final String topic_;
    private final int ttl_;

    MessagingClientEvent(long project_number_, String message_id_, String instance_id_, MessageType message_type_, SDKPlatform sdk_platform_, String package_name_, String collapse_key_, int priority_, int ttl_, String topic_, long bulk_id_, Event event_, String analytics_label_, long campaign_id_, String composer_label_) {
        this.project_number_ = project_number_;
        this.message_id_ = message_id_;
        this.instance_id_ = instance_id_;
        this.message_type_ = message_type_;
        this.sdk_platform_ = sdk_platform_;
        this.package_name_ = package_name_;
        this.collapse_key_ = collapse_key_;
        this.priority_ = priority_;
        this.ttl_ = ttl_;
        this.topic_ = topic_;
        this.bulk_id_ = bulk_id_;
        this.event_ = event_;
        this.analytics_label_ = analytics_label_;
        this.campaign_id_ = campaign_id_;
        this.composer_label_ = composer_label_;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public long getProjectNumber() {
        return this.project_number_;
    }

    public String getMessageId() {
        return this.message_id_;
    }

    public String getInstanceId() {
        return this.instance_id_;
    }

    public MessageType getMessageType() {
        return this.message_type_;
    }

    public SDKPlatform getSdkPlatform() {
        return this.sdk_platform_;
    }

    public String getPackageName() {
        return this.package_name_;
    }

    public String getCollapseKey() {
        return this.collapse_key_;
    }

    public int getPriority() {
        return this.priority_;
    }

    public int getTtl() {
        return this.ttl_;
    }

    public String getTopic() {
        return this.topic_;
    }

    public long getBulkId() {
        return this.bulk_id_;
    }

    public Event getEvent() {
        return this.event_;
    }

    public String getAnalyticsLabel() {
        return this.analytics_label_;
    }

    public long getCampaignId() {
        return this.campaign_id_;
    }

    public String getComposerLabel() {
        return this.composer_label_;
    }

    public static MessagingClientEvent getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder {
        private long project_number_ = 0;
        private String message_id_ = "";
        private String instance_id_ = "";
        private MessageType message_type_ = MessageType.UNKNOWN;
        private SDKPlatform sdk_platform_ = SDKPlatform.UNKNOWN_OS;
        private String package_name_ = "";
        private String collapse_key_ = "";
        private int priority_ = 0;
        private int ttl_ = 0;
        private String topic_ = "";
        private long bulk_id_ = 0;
        private Event event_ = Event.UNKNOWN_EVENT;
        private String analytics_label_ = "";
        private long campaign_id_ = 0;
        private String composer_label_ = "";

        Builder() {
        }

        public MessagingClientEvent build() {
            return new MessagingClientEvent(this.project_number_, this.message_id_, this.instance_id_, this.message_type_, this.sdk_platform_, this.package_name_, this.collapse_key_, this.priority_, this.ttl_, this.topic_, this.bulk_id_, this.event_, this.analytics_label_, this.campaign_id_, this.composer_label_);
        }

        public Builder setProjectNumber(long project_number_) {
            this.project_number_ = project_number_;
            return this;
        }

        public Builder setMessageId(String message_id_) {
            this.message_id_ = message_id_;
            return this;
        }

        public Builder setInstanceId(String instance_id_) {
            this.instance_id_ = instance_id_;
            return this;
        }

        public Builder setMessageType(MessageType message_type_) {
            this.message_type_ = message_type_;
            return this;
        }

        public Builder setSdkPlatform(SDKPlatform sdk_platform_) {
            this.sdk_platform_ = sdk_platform_;
            return this;
        }

        public Builder setPackageName(String package_name_) {
            this.package_name_ = package_name_;
            return this;
        }

        public Builder setCollapseKey(String collapse_key_) {
            this.collapse_key_ = collapse_key_;
            return this;
        }

        public Builder setPriority(int priority_) {
            this.priority_ = priority_;
            return this;
        }

        public Builder setTtl(int ttl_) {
            this.ttl_ = ttl_;
            return this;
        }

        public Builder setTopic(String topic_) {
            this.topic_ = topic_;
            return this;
        }

        public Builder setBulkId(long bulk_id_) {
            this.bulk_id_ = bulk_id_;
            return this;
        }

        public Builder setEvent(Event event_) {
            this.event_ = event_;
            return this;
        }

        public Builder setAnalyticsLabel(String analytics_label_) {
            this.analytics_label_ = analytics_label_;
            return this;
        }

        public Builder setCampaignId(long campaign_id_) {
            this.campaign_id_ = campaign_id_;
            return this;
        }

        public Builder setComposerLabel(String composer_label_) {
            this.composer_label_ = composer_label_;
            return this;
        }
    }

    public enum MessageType implements ProtoEnum {
        UNKNOWN(0),
        DATA_MESSAGE(1),
        TOPIC(2),
        DISPLAY_NOTIFICATION(3);

        private final int number_;

        MessageType(int number_) {
            this.number_ = number_;
        }

        @Override // com.google.firebase.encoders.proto.ProtoEnum
        public int getNumber() {
            return this.number_;
        }
    }

    public enum SDKPlatform implements ProtoEnum {
        UNKNOWN_OS(0),
        ANDROID(1),
        IOS(2),
        WEB(3);

        private final int number_;

        SDKPlatform(int number_) {
            this.number_ = number_;
        }

        @Override // com.google.firebase.encoders.proto.ProtoEnum
        public int getNumber() {
            return this.number_;
        }
    }

    public enum Event implements ProtoEnum {
        UNKNOWN_EVENT(0),
        MESSAGE_DELIVERED(1),
        MESSAGE_OPEN(2);

        private final int number_;

        Event(int number_) {
            this.number_ = number_;
        }

        @Override // com.google.firebase.encoders.proto.ProtoEnum
        public int getNumber() {
            return this.number_;
        }
    }
}
