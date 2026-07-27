package io.michaelrocks.libphonenumber.android.internal;

import io.michaelrocks.libphonenumber.android.Phonemetadata;

/* loaded from: classes17.dex */
public interface MatcherApi {
    boolean matchNationalNumber(CharSequence number, Phonemetadata.PhoneNumberDesc numberDesc, boolean allowPrefixMatch);
}
