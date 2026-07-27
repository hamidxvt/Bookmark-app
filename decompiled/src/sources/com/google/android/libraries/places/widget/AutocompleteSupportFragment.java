package com.google.android.libraries.places.widget;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.google.android.libraries.places.R;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.internal.zzev;
import com.google.android.libraries.places.internal.zzfj;
import com.google.android.libraries.places.internal.zzfk;
import com.google.android.libraries.places.internal.zzfl;
import com.google.android.libraries.places.internal.zzhs;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public class AutocompleteSupportFragment extends Fragment {
    private final MutableLiveData zza;
    private final MutableLiveData zzb;
    private zzfk zzc;
    private PlaceSelectionListener zzd;

    public AutocompleteSupportFragment() {
        super(R.layout.places_autocomplete_fragment);
        this.zza = new MutableLiveData();
        this.zzb = new MutableLiveData();
        this.zzc = zzfl.zzm(AutocompleteActivityMode.OVERLAY, zzhs.zzm(), zzfj.FRAGMENT);
    }

    public static AutocompleteSupportFragment newInstance() {
        return new AutocompleteSupportFragment();
    }

    private final void zze() {
        Intent build = new Autocomplete.IntentBuilder(this.zzc.zzl()).build(requireContext());
        if (requireView().isEnabled()) {
            requireView().setEnabled(false);
            startActivityForResult(build, 30421);
        }
    }

    private final void zzf(View view) {
        view.setVisibility(true != TextUtils.isEmpty((CharSequence) this.zza.getValue()) ? 0 : 8);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 30421) {
            try {
                PlaceSelectionListener placeSelectionListener = this.zzd;
                if (placeSelectionListener == null) {
                    if (Log.isLoggable("Places", 5)) {
                        Log.w("Places", "No PlaceSelectionListener is set. No result will be delivered.");
                    }
                } else if (data == null) {
                    if (Log.isLoggable("Places", 6)) {
                        Log.e("Places", "Intent data was null.");
                    }
                } else {
                    if (resultCode != -1) {
                        placeSelectionListener.onError(Autocomplete.getStatusFromIntent(data));
                        return;
                    }
                    Place placeFromIntent = Autocomplete.getPlaceFromIntent(data);
                    placeSelectionListener.onPlaceSelected(placeFromIntent);
                    setText(placeFromIntent.getName());
                }
            } catch (Error | RuntimeException e) {
                zzev.zzb(e);
                throw e;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            try {
                zzfl zzflVar = (zzfl) savedInstanceState.getParcelable("options");
                if (zzflVar == null) {
                    return;
                }
                if (this.zza.getValue() == 0) {
                    this.zza.postValue(zzflVar.zzl());
                }
                if (this.zzb.getValue() == 0) {
                    this.zzb.postValue(zzflVar.zzk());
                }
                this.zzc = zzflVar.zzg();
            } catch (Error | RuntimeException e) {
                zzev.zzb(e);
                throw e;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        requireView().setEnabled(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable("options", this.zzc.zzl());
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        final View findViewById = view.findViewById(R.id.places_autocomplete_search_button);
        final View findViewById2 = view.findViewById(R.id.places_autocomplete_clear_button);
        final EditText editText = (EditText) view.findViewById(R.id.places_autocomplete_search_input);
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.libraries.places.widget.zze
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AutocompleteSupportFragment.this.zza(view2);
            }
        });
        editText.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.libraries.places.widget.zzf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AutocompleteSupportFragment.this.zzb(view2);
            }
        });
        findViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.libraries.places.widget.zzg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AutocompleteSupportFragment.this.setText(null);
            }
        });
        zzf(findViewById2);
        this.zza.observe(getViewLifecycleOwner(), new Observer() { // from class: com.google.android.libraries.places.widget.zzi
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AutocompleteSupportFragment.this.zzc(editText, findViewById2, (CharSequence) obj);
            }
        });
        this.zzb.observe(getViewLifecycleOwner(), new Observer() { // from class: com.google.android.libraries.places.widget.zzh
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AutocompleteSupportFragment.zzd(editText, findViewById, (CharSequence) obj);
            }
        });
    }

    public AutocompleteSupportFragment setActivityMode(AutocompleteActivityMode mode) {
        this.zzc.zzf(mode);
        return this;
    }

    public AutocompleteSupportFragment setCountries(List<String> list) {
        this.zzc.zza(list);
        return this;
    }

    public AutocompleteSupportFragment setCountry(String country) {
        this.zzc.zzm(country);
        return this;
    }

    public AutocompleteSupportFragment setHint(CharSequence hint) {
        try {
            if (hint == null) {
                String string = getString(R.string.places_autocomplete_search_hint);
                this.zzc.zzb(string);
                this.zzb.postValue(string);
            } else {
                this.zzc.zzb(hint.toString());
                this.zzb.postValue(hint);
            }
            return this;
        } catch (Error | RuntimeException e) {
            zzev.zzb(e);
            throw e;
        }
    }

    public AutocompleteSupportFragment setLocationBias(LocationBias locationBias) {
        this.zzc.zzd(locationBias);
        return this;
    }

    public AutocompleteSupportFragment setLocationRestriction(LocationRestriction locationRestriction) {
        this.zzc.zze(locationRestriction);
        return this;
    }

    public AutocompleteSupportFragment setOnPlaceSelectedListener(PlaceSelectionListener placeSelectionListener) {
        this.zzd = placeSelectionListener;
        return this;
    }

    public AutocompleteSupportFragment setPlaceFields(List<Place.Field> list) {
        this.zzc.zzh(list);
        return this;
    }

    public AutocompleteSupportFragment setText(CharSequence text) {
        try {
            this.zzc.zzc(TextUtils.isEmpty(text) ? null : text.toString());
            this.zza.postValue(text);
            return this;
        } catch (Error | RuntimeException e) {
            zzev.zzb(e);
            throw e;
        }
    }

    public AutocompleteSupportFragment setTypeFilter(TypeFilter typeFilter) {
        this.zzc.zzk(typeFilter);
        return this;
    }

    final /* synthetic */ void zza(View view) {
        zze();
    }

    final /* synthetic */ void zzb(View view) {
        zze();
    }

    public AutocompleteSupportFragment setCountries(String... countries) {
        this.zzc.zza(zzhs.zzl(countries));
        return this;
    }

    static /* synthetic */ void zzd(EditText editText, View view, CharSequence charSequence) {
        try {
            editText.setHint(charSequence);
            view.setContentDescription(charSequence);
        } catch (Error | RuntimeException e) {
            zzev.zzb(e);
            throw e;
        }
    }

    final /* synthetic */ void zzc(EditText editText, View view, CharSequence charSequence) {
        try {
            editText.setText(charSequence);
            zzf(view);
        } catch (Error | RuntimeException e) {
            zzev.zzb(e);
            throw e;
        }
    }
}
