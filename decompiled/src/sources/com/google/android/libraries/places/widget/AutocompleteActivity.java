package com.google.android.libraries.places.widget;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.R;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
import com.google.android.libraries.places.internal.zzev;
import com.google.android.libraries.places.internal.zzfl;
import com.google.android.libraries.places.internal.zzha;
import com.google.android.libraries.places.widget.internal.ui.AutocompleteImplFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public class AutocompleteActivity extends AppCompatActivity implements PlaceSelectionListener {
    public static final int RESULT_ERROR = 2;
    static boolean zza = true;
    private int zzb;
    private int zzc;
    private boolean zzd;

    public AutocompleteActivity() {
        super(R.layout.places_autocomplete_activity);
        this.zzd = false;
    }

    private final void zzc(int i, Place place, Status status) {
        try {
            Intent intent = new Intent();
            if (place != null) {
                intent.putExtra("places/selected_place", place);
            }
            intent.putExtra("places/status", status);
            setResult(i, intent);
            finish();
        } catch (Error | RuntimeException e) {
            zzev.zzb(e);
            throw e;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        try {
            zzha.zzi(Places.isInitialized(), "Places must be initialized.");
            boolean z = true;
            if (zza) {
                zzha.zzi(getCallingActivity() != null, "Cannot find caller. startActivityForResult should be used.");
            }
            zzfl zzflVar = (zzfl) getIntent().getParcelableExtra("places/AutocompleteOptions");
            if (zzflVar == null) {
                throw null;
            }
            AutocompleteActivityMode autocompleteActivityMode = AutocompleteActivityMode.FULLSCREEN;
            switch (zzflVar.zzh()) {
                case FULLSCREEN:
                    this.zzb = R.layout.places_autocomplete_impl_fragment_fullscreen;
                    this.zzc = R.style.PlacesAutocompleteFullscreen;
                    break;
                case OVERLAY:
                    this.zzb = R.layout.places_autocomplete_impl_fragment_overlay;
                    this.zzc = R.style.PlacesAutocompleteOverlay;
                    break;
            }
            getSupportFragmentManager().setFragmentFactory(new com.google.android.libraries.places.widget.internal.ui.zzh(this.zzb, this, zzflVar));
            setTheme(this.zzc);
            super.onCreate(savedInstanceState);
            final AutocompleteImplFragment autocompleteImplFragment = (AutocompleteImplFragment) getSupportFragmentManager().findFragmentById(R.id.places_autocomplete_content);
            if (autocompleteImplFragment == null) {
                z = false;
            }
            zzha.zzh(z);
            autocompleteImplFragment.zzh(this);
            final View findViewById = findViewById(android.R.id.content);
            findViewById.setOnTouchListener(new View.OnTouchListener() { // from class: com.google.android.libraries.places.widget.zzb
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return AutocompleteActivity.this.zzb(autocompleteImplFragment, findViewById, view, motionEvent);
                }
            });
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.libraries.places.widget.zza
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AutocompleteActivity.this.zza(view);
                }
            });
            if (zzflVar.zzj().isEmpty()) {
                zzc(2, null, new Status(PlacesStatusCodes.INVALID_REQUEST, "Place Fields must not be empty."));
            }
        } catch (Error | RuntimeException e) {
            zzev.zzb(e);
            throw e;
        }
    }

    @Override // com.google.android.libraries.places.widget.listener.PlaceSelectionListener
    public void onError(Status status) {
        zzc(true != status.isCanceled() ? 2 : 0, null, status);
    }

    @Override // com.google.android.libraries.places.widget.listener.PlaceSelectionListener
    public void onPlaceSelected(Place place) {
        zzc(-1, place, Status.RESULT_SUCCESS);
    }

    final /* synthetic */ void zza(View view) {
        if (this.zzd) {
            zzc(0, null, new Status(16));
        }
    }

    final /* synthetic */ boolean zzb(AutocompleteImplFragment autocompleteImplFragment, View view, View view2, MotionEvent motionEvent) {
        this.zzd = false;
        if (autocompleteImplFragment.getView() == null || motionEvent.getY() <= r1.getBottom()) {
            return false;
        }
        this.zzd = true;
        view.performClick();
        return true;
    }
}
