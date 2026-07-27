package com.hbb20;

/* compiled from: CCPTalkBackTextProvider.java */
/* loaded from: classes17.dex */
class InternalTalkBackTextProvider implements CCPTalkBackTextProvider {
    InternalTalkBackTextProvider() {
    }

    @Override // com.hbb20.CCPTalkBackTextProvider
    public String getTalkBackTextForCountry(CCPCountry country) {
        if (country == null) {
            return null;
        }
        return country.name + " phone code is +" + country.phoneCode;
    }
}
