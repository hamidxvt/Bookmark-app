package com.google.android.gms.auth.api.identity;

import com.google.android.gms.auth.api.identity.Claim;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
/* loaded from: classes16.dex */
final class zbd extends Claim.Builder {
    private String zba;
    private boolean zbb;
    private byte zbc;

    zbd() {
    }

    @Override // com.google.android.gms.auth.api.identity.Claim.Builder
    public final Claim build() {
        if (this.zbc == 1 && this.zba != null) {
            return new Claim(this.zba, this.zbb);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zba == null) {
            sb.append(" name");
        }
        if (this.zbc == 0) {
            sb.append(" essential");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }

    @Override // com.google.android.gms.auth.api.identity.Claim.Builder
    public final Claim.Builder setEssential(boolean z) {
        this.zbb = z;
        this.zbc = (byte) 1;
        return this;
    }

    @Override // com.google.android.gms.auth.api.identity.Claim.Builder
    public final Claim.Builder setName(String str) {
        if (str == null) {
            throw new NullPointerException("Null name");
        }
        this.zba = str;
        return this;
    }
}
