package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
final class zzcl {
    private String description;
    private Integer distanceMeters;
    private zzb[] matchedSubstrings;
    private String placeId;
    private zza structuredFormatting;
    private String[] types;

    /* compiled from: com.google.android.libraries.places:places@@2.6.0 */
    class zza {
        private String mainText;
        private zzb[] mainTextMatchedSubstrings;
        private String secondaryText;
        private zzb[] secondaryTextMatchedSubstrings;

        zza() {
        }

        final zzhs zza() {
            zzb[] zzbVarArr = this.mainTextMatchedSubstrings;
            if (zzbVarArr != null) {
                return zzhs.zzl(zzbVarArr);
            }
            return null;
        }

        final zzhs zzb() {
            zzb[] zzbVarArr = this.secondaryTextMatchedSubstrings;
            if (zzbVarArr != null) {
                return zzhs.zzl(zzbVarArr);
            }
            return null;
        }

        final String zzc() {
            return this.mainText;
        }

        final String zzd() {
            return this.secondaryText;
        }
    }

    /* compiled from: com.google.android.libraries.places:places@@2.6.0 */
    class zzb {
        Integer length;
        Integer offset;

        zzb() {
        }
    }

    zzcl() {
    }

    final zza zza() {
        return this.structuredFormatting;
    }

    final zzhs zzb() {
        zzb[] zzbVarArr = this.matchedSubstrings;
        if (zzbVarArr != null) {
            return zzhs.zzl(zzbVarArr);
        }
        return null;
    }

    final zzhs zzc() {
        String[] strArr = this.types;
        if (strArr != null) {
            return zzhs.zzl(strArr);
        }
        return null;
    }

    final Integer zzd() {
        return this.distanceMeters;
    }

    final String zze() {
        return this.description;
    }

    final String zzf() {
        return this.placeId;
    }
}
