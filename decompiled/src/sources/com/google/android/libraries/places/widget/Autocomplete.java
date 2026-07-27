package com.google.android.libraries.places.widget;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.internal.zzev;
import com.google.android.libraries.places.internal.zzfj;
import com.google.android.libraries.places.internal.zzfk;
import com.google.android.libraries.places.internal.zzfl;
import com.google.android.libraries.places.internal.zzfo;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class Autocomplete {

    /* compiled from: com.google.android.libraries.places:places@@2.6.0 */
    public static class IntentBuilder {
        private final zzfk zza;

        public IntentBuilder(zzfl zzflVar) {
            this.zza = zzflVar.zzg();
        }

        public IntentBuilder(AutocompleteActivityMode mode, List<Place.Field> list) {
            this.zza = zzfl.zzm(mode, list, zzfj.INTENT);
        }

        public Intent build(Context context) {
            try {
                Intent intent = new Intent(context, (Class<?>) AutocompleteActivity.class);
                zzfk zzfkVar = this.zza;
                Resources.Theme theme = context.getTheme();
                TypedValue typedValue = new TypedValue();
                if (theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)) {
                    zzfkVar.zzi(typedValue.data);
                }
                TypedValue typedValue2 = new TypedValue();
                if (theme.resolveAttribute(R.attr.colorPrimaryDark, typedValue2, true)) {
                    zzfkVar.zzj(typedValue2.data);
                }
                intent.putExtra("places/AutocompleteOptions", this.zza.zzl());
                return intent;
            } catch (Error | RuntimeException e) {
                zzev.zzb(e);
                throw e;
            }
        }

        public IntentBuilder setCountries(List<String> list) {
            this.zza.zza(list);
            return this;
        }

        public IntentBuilder setCountry(String country) {
            this.zza.zzm(country);
            return this;
        }

        public IntentBuilder setHint(String hint) {
            this.zza.zzb(hint);
            return this;
        }

        public IntentBuilder setInitialQuery(String initialQuery) {
            this.zza.zzc(initialQuery);
            return this;
        }

        public IntentBuilder setLocationBias(LocationBias locationBias) {
            this.zza.zzd(locationBias);
            return this;
        }

        public IntentBuilder setLocationRestriction(LocationRestriction locationRestriction) {
            this.zza.zze(locationRestriction);
            return this;
        }

        public IntentBuilder setTypeFilter(TypeFilter typeFilter) {
            this.zza.zzk(typeFilter);
            return this;
        }

        public final IntentBuilder zza(zzfj zzfjVar) {
            this.zza.zzg(zzfjVar);
            return this;
        }
    }

    private Autocomplete() {
    }

    public static Place getPlaceFromIntent(Intent intent) {
        return zzfo.zzb(intent);
    }

    public static Status getStatusFromIntent(Intent intent) {
        return zzfo.zza(intent);
    }
}
