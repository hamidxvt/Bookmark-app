package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.LogRequest;
import com.google.firebase.encoders.annotations.Encodable;
import java.util.List;

/* loaded from: classes16.dex */
final class AutoValue_LogRequest extends LogRequest {
    private final ClientInfo clientInfo;
    private final List<LogEvent> logEvents;
    private final Integer logSource;
    private final String logSourceName;
    private final QosTier qosTier;
    private final long requestTimeMs;
    private final long requestUptimeMs;

    private AutoValue_LogRequest(long requestTimeMs, long requestUptimeMs, ClientInfo clientInfo, Integer logSource, String logSourceName, List<LogEvent> logEvents, QosTier qosTier) {
        this.requestTimeMs = requestTimeMs;
        this.requestUptimeMs = requestUptimeMs;
        this.clientInfo = clientInfo;
        this.logSource = logSource;
        this.logSourceName = logSourceName;
        this.logEvents = logEvents;
        this.qosTier = qosTier;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public long getRequestTimeMs() {
        return this.requestTimeMs;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public long getRequestUptimeMs() {
        return this.requestUptimeMs;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public ClientInfo getClientInfo() {
        return this.clientInfo;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public Integer getLogSource() {
        return this.logSource;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public String getLogSourceName() {
        return this.logSourceName;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    @Encodable.Field(name = "logEvent")
    public List<LogEvent> getLogEvents() {
        return this.logEvents;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public QosTier getQosTier() {
        return this.qosTier;
    }

    public String toString() {
        return "LogRequest{requestTimeMs=" + this.requestTimeMs + ", requestUptimeMs=" + this.requestUptimeMs + ", clientInfo=" + this.clientInfo + ", logSource=" + this.logSource + ", logSourceName=" + this.logSourceName + ", logEvents=" + this.logEvents + ", qosTier=" + this.qosTier + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LogRequest)) {
            return false;
        }
        LogRequest that = (LogRequest) o;
        if (this.requestTimeMs == that.getRequestTimeMs() && this.requestUptimeMs == that.getRequestUptimeMs() && (this.clientInfo != null ? this.clientInfo.equals(that.getClientInfo()) : that.getClientInfo() == null) && (this.logSource != null ? this.logSource.equals(that.getLogSource()) : that.getLogSource() == null) && (this.logSourceName != null ? this.logSourceName.equals(that.getLogSourceName()) : that.getLogSourceName() == null) && (this.logEvents != null ? this.logEvents.equals(that.getLogEvents()) : that.getLogEvents() == null)) {
            if (this.qosTier == null) {
                if (that.getQosTier() == null) {
                    return true;
                }
            } else if (this.qosTier.equals(that.getQosTier())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        return ((((((((((((h$ ^ ((int) ((this.requestTimeMs >>> 32) ^ this.requestTimeMs))) * 1000003) ^ ((int) ((this.requestUptimeMs >>> 32) ^ this.requestUptimeMs))) * 1000003) ^ (this.clientInfo == null ? 0 : this.clientInfo.hashCode())) * 1000003) ^ (this.logSource == null ? 0 : this.logSource.hashCode())) * 1000003) ^ (this.logSourceName == null ? 0 : this.logSourceName.hashCode())) * 1000003) ^ (this.logEvents == null ? 0 : this.logEvents.hashCode())) * 1000003) ^ (this.qosTier != null ? this.qosTier.hashCode() : 0);
    }

    static final class Builder extends LogRequest.Builder {
        private ClientInfo clientInfo;
        private List<LogEvent> logEvents;
        private Integer logSource;
        private String logSourceName;
        private QosTier qosTier;
        private Long requestTimeMs;
        private Long requestUptimeMs;

        Builder() {
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setRequestTimeMs(long requestTimeMs) {
            this.requestTimeMs = Long.valueOf(requestTimeMs);
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setRequestUptimeMs(long requestUptimeMs) {
            this.requestUptimeMs = Long.valueOf(requestUptimeMs);
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setClientInfo(ClientInfo clientInfo) {
            this.clientInfo = clientInfo;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        LogRequest.Builder setLogSource(Integer logSource) {
            this.logSource = logSource;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        LogRequest.Builder setLogSourceName(String logSourceName) {
            this.logSourceName = logSourceName;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setLogEvents(List<LogEvent> logEvents) {
            this.logEvents = logEvents;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setQosTier(QosTier qosTier) {
            this.qosTier = qosTier;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest build() {
            String missing = this.requestTimeMs == null ? " requestTimeMs" : "";
            if (this.requestUptimeMs == null) {
                missing = missing + " requestUptimeMs";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new AutoValue_LogRequest(this.requestTimeMs.longValue(), this.requestUptimeMs.longValue(), this.clientInfo, this.logSource, this.logSourceName, this.logEvents, this.qosTier);
        }
    }
}
