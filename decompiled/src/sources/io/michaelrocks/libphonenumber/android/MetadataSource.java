package io.michaelrocks.libphonenumber.android;

import io.michaelrocks.libphonenumber.android.Phonemetadata;

/* loaded from: classes17.dex */
interface MetadataSource {
    Phonemetadata.PhoneMetadata getAlternateFormatsForCountry(int countryCallingCode);

    Phonemetadata.PhoneMetadata getMetadataForNonGeographicalRegion(int countryCallingCode);

    Phonemetadata.PhoneMetadata getMetadataForRegion(String regionCode);

    Phonemetadata.PhoneMetadata getShortNumberMetadataForRegion(String regionCode);
}
