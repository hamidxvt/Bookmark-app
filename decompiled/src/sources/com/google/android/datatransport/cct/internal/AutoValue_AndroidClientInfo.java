package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.AndroidClientInfo;

/* loaded from: classes16.dex */
final class AutoValue_AndroidClientInfo extends AndroidClientInfo {
    private final String applicationBuild;
    private final String country;
    private final String device;
    private final String fingerprint;
    private final String hardware;
    private final String locale;
    private final String manufacturer;
    private final String mccMnc;
    private final String model;
    private final String osBuild;
    private final String product;
    private final Integer sdkVersion;

    private AutoValue_AndroidClientInfo(Integer sdkVersion, String model, String hardware, String device, String product, String osBuild, String manufacturer, String fingerprint, String locale, String country, String mccMnc, String applicationBuild) {
        this.sdkVersion = sdkVersion;
        this.model = model;
        this.hardware = hardware;
        this.device = device;
        this.product = product;
        this.osBuild = osBuild;
        this.manufacturer = manufacturer;
        this.fingerprint = fingerprint;
        this.locale = locale;
        this.country = country;
        this.mccMnc = mccMnc;
        this.applicationBuild = applicationBuild;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public Integer getSdkVersion() {
        return this.sdkVersion;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getModel() {
        return this.model;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getHardware() {
        return this.hardware;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getDevice() {
        return this.device;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getProduct() {
        return this.product;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getOsBuild() {
        return this.osBuild;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getManufacturer() {
        return this.manufacturer;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getFingerprint() {
        return this.fingerprint;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getLocale() {
        return this.locale;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getCountry() {
        return this.country;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getMccMnc() {
        return this.mccMnc;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getApplicationBuild() {
        return this.applicationBuild;
    }

    public String toString() {
        return "AndroidClientInfo{sdkVersion=" + this.sdkVersion + ", model=" + this.model + ", hardware=" + this.hardware + ", device=" + this.device + ", product=" + this.product + ", osBuild=" + this.osBuild + ", manufacturer=" + this.manufacturer + ", fingerprint=" + this.fingerprint + ", locale=" + this.locale + ", country=" + this.country + ", mccMnc=" + this.mccMnc + ", applicationBuild=" + this.applicationBuild + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AndroidClientInfo)) {
            return false;
        }
        AndroidClientInfo that = (AndroidClientInfo) o;
        if (this.sdkVersion != null ? this.sdkVersion.equals(that.getSdkVersion()) : that.getSdkVersion() == null) {
            if (this.model != null ? this.model.equals(that.getModel()) : that.getModel() == null) {
                if (this.hardware != null ? this.hardware.equals(that.getHardware()) : that.getHardware() == null) {
                    if (this.device != null ? this.device.equals(that.getDevice()) : that.getDevice() == null) {
                        if (this.product != null ? this.product.equals(that.getProduct()) : that.getProduct() == null) {
                            if (this.osBuild != null ? this.osBuild.equals(that.getOsBuild()) : that.getOsBuild() == null) {
                                if (this.manufacturer != null ? this.manufacturer.equals(that.getManufacturer()) : that.getManufacturer() == null) {
                                    if (this.fingerprint != null ? this.fingerprint.equals(that.getFingerprint()) : that.getFingerprint() == null) {
                                        if (this.locale != null ? this.locale.equals(that.getLocale()) : that.getLocale() == null) {
                                            if (this.country != null ? this.country.equals(that.getCountry()) : that.getCountry() == null) {
                                                if (this.mccMnc != null ? this.mccMnc.equals(that.getMccMnc()) : that.getMccMnc() == null) {
                                                    if (this.applicationBuild == null) {
                                                        if (that.getApplicationBuild() == null) {
                                                            return true;
                                                        }
                                                    } else if (this.applicationBuild.equals(that.getApplicationBuild())) {
                                                        return true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        return ((((((((((((((((((((((h$ ^ (this.sdkVersion == null ? 0 : this.sdkVersion.hashCode())) * 1000003) ^ (this.model == null ? 0 : this.model.hashCode())) * 1000003) ^ (this.hardware == null ? 0 : this.hardware.hashCode())) * 1000003) ^ (this.device == null ? 0 : this.device.hashCode())) * 1000003) ^ (this.product == null ? 0 : this.product.hashCode())) * 1000003) ^ (this.osBuild == null ? 0 : this.osBuild.hashCode())) * 1000003) ^ (this.manufacturer == null ? 0 : this.manufacturer.hashCode())) * 1000003) ^ (this.fingerprint == null ? 0 : this.fingerprint.hashCode())) * 1000003) ^ (this.locale == null ? 0 : this.locale.hashCode())) * 1000003) ^ (this.country == null ? 0 : this.country.hashCode())) * 1000003) ^ (this.mccMnc == null ? 0 : this.mccMnc.hashCode())) * 1000003) ^ (this.applicationBuild != null ? this.applicationBuild.hashCode() : 0);
    }

    static final class Builder extends AndroidClientInfo.Builder {
        private String applicationBuild;
        private String country;
        private String device;
        private String fingerprint;
        private String hardware;
        private String locale;
        private String manufacturer;
        private String mccMnc;
        private String model;
        private String osBuild;
        private String product;
        private Integer sdkVersion;

        Builder() {
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setSdkVersion(Integer sdkVersion) {
            this.sdkVersion = sdkVersion;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setModel(String model) {
            this.model = model;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setHardware(String hardware) {
            this.hardware = hardware;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setDevice(String device) {
            this.device = device;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setProduct(String product) {
            this.product = product;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setOsBuild(String osBuild) {
            this.osBuild = osBuild;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setFingerprint(String fingerprint) {
            this.fingerprint = fingerprint;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setLocale(String locale) {
            this.locale = locale;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setMccMnc(String mccMnc) {
            this.mccMnc = mccMnc;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setApplicationBuild(String applicationBuild) {
            this.applicationBuild = applicationBuild;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo build() {
            return new AutoValue_AndroidClientInfo(this.sdkVersion, this.model, this.hardware, this.device, this.product, this.osBuild, this.manufacturer, this.fingerprint, this.locale, this.country, this.mccMnc, this.applicationBuild);
        }
    }
}
