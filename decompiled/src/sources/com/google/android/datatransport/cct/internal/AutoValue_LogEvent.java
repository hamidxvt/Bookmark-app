package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.LogEvent;
import java.util.Arrays;

/* loaded from: classes16.dex */
final class AutoValue_LogEvent extends LogEvent {
    private final Integer eventCode;
    private final long eventTimeMs;
    private final long eventUptimeMs;
    private final NetworkConnectionInfo networkConnectionInfo;
    private final byte[] sourceExtension;
    private final String sourceExtensionJsonProto3;
    private final long timezoneOffsetSeconds;

    private AutoValue_LogEvent(long eventTimeMs, Integer eventCode, long eventUptimeMs, byte[] sourceExtension, String sourceExtensionJsonProto3, long timezoneOffsetSeconds, NetworkConnectionInfo networkConnectionInfo) {
        this.eventTimeMs = eventTimeMs;
        this.eventCode = eventCode;
        this.eventUptimeMs = eventUptimeMs;
        this.sourceExtension = sourceExtension;
        this.sourceExtensionJsonProto3 = sourceExtensionJsonProto3;
        this.timezoneOffsetSeconds = timezoneOffsetSeconds;
        this.networkConnectionInfo = networkConnectionInfo;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public long getEventTimeMs() {
        return this.eventTimeMs;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public Integer getEventCode() {
        return this.eventCode;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public long getEventUptimeMs() {
        return this.eventUptimeMs;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public byte[] getSourceExtension() {
        return this.sourceExtension;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public String getSourceExtensionJsonProto3() {
        return this.sourceExtensionJsonProto3;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public long getTimezoneOffsetSeconds() {
        return this.timezoneOffsetSeconds;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public NetworkConnectionInfo getNetworkConnectionInfo() {
        return this.networkConnectionInfo;
    }

    public String toString() {
        return "LogEvent{eventTimeMs=" + this.eventTimeMs + ", eventCode=" + this.eventCode + ", eventUptimeMs=" + this.eventUptimeMs + ", sourceExtension=" + Arrays.toString(this.sourceExtension) + ", sourceExtensionJsonProto3=" + this.sourceExtensionJsonProto3 + ", timezoneOffsetSeconds=" + this.timezoneOffsetSeconds + ", networkConnectionInfo=" + this.networkConnectionInfo + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LogEvent)) {
            return false;
        }
        LogEvent that = (LogEvent) o;
        if (this.eventTimeMs == that.getEventTimeMs() && (this.eventCode != null ? this.eventCode.equals(that.getEventCode()) : that.getEventCode() == null) && this.eventUptimeMs == that.getEventUptimeMs()) {
            if (Arrays.equals(this.sourceExtension, that instanceof AutoValue_LogEvent ? ((AutoValue_LogEvent) that).sourceExtension : that.getSourceExtension()) && (this.sourceExtensionJsonProto3 != null ? this.sourceExtensionJsonProto3.equals(that.getSourceExtensionJsonProto3()) : that.getSourceExtensionJsonProto3() == null) && this.timezoneOffsetSeconds == that.getTimezoneOffsetSeconds()) {
                if (this.networkConnectionInfo == null) {
                    if (that.getNetworkConnectionInfo() == null) {
                        return true;
                    }
                } else if (this.networkConnectionInfo.equals(that.getNetworkConnectionInfo())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        return ((((((((((((h$ ^ ((int) ((this.eventTimeMs >>> 32) ^ this.eventTimeMs))) * 1000003) ^ (this.eventCode == null ? 0 : this.eventCode.hashCode())) * 1000003) ^ ((int) ((this.eventUptimeMs >>> 32) ^ this.eventUptimeMs))) * 1000003) ^ Arrays.hashCode(this.sourceExtension)) * 1000003) ^ (this.sourceExtensionJsonProto3 == null ? 0 : this.sourceExtensionJsonProto3.hashCode())) * 1000003) ^ ((int) ((this.timezoneOffsetSeconds >>> 32) ^ this.timezoneOffsetSeconds))) * 1000003) ^ (this.networkConnectionInfo != null ? this.networkConnectionInfo.hashCode() : 0);
    }

    static final class Builder extends LogEvent.Builder {
        private Integer eventCode;
        private Long eventTimeMs;
        private Long eventUptimeMs;
        private NetworkConnectionInfo networkConnectionInfo;
        private byte[] sourceExtension;
        private String sourceExtensionJsonProto3;
        private Long timezoneOffsetSeconds;

        Builder() {
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setEventTimeMs(long eventTimeMs) {
            this.eventTimeMs = Long.valueOf(eventTimeMs);
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setEventCode(Integer eventCode) {
            this.eventCode = eventCode;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setEventUptimeMs(long eventUptimeMs) {
            this.eventUptimeMs = Long.valueOf(eventUptimeMs);
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        LogEvent.Builder setSourceExtension(byte[] sourceExtension) {
            this.sourceExtension = sourceExtension;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        LogEvent.Builder setSourceExtensionJsonProto3(String sourceExtensionJsonProto3) {
            this.sourceExtensionJsonProto3 = sourceExtensionJsonProto3;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setTimezoneOffsetSeconds(long timezoneOffsetSeconds) {
            this.timezoneOffsetSeconds = Long.valueOf(timezoneOffsetSeconds);
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setNetworkConnectionInfo(NetworkConnectionInfo networkConnectionInfo) {
            this.networkConnectionInfo = networkConnectionInfo;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent build() {
            String missing = this.eventTimeMs == null ? " eventTimeMs" : "";
            if (this.eventUptimeMs == null) {
                missing = missing + " eventUptimeMs";
            }
            if (this.timezoneOffsetSeconds == null) {
                missing = missing + " timezoneOffsetSeconds";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new AutoValue_LogEvent(this.eventTimeMs.longValue(), this.eventCode, this.eventUptimeMs.longValue(), this.sourceExtension, this.sourceExtensionJsonProto3, this.timezoneOffsetSeconds.longValue(), this.networkConnectionInfo);
        }
    }
}
