package com.hbb20;

import android.content.Context;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import io.michaelrocks.libphonenumber.android.AsYouTypeFormatter;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;
import org.slf4j.Marker;

/* loaded from: classes17.dex */
public class InternationalPhoneTextWatcher implements TextWatcher {
    private static final String TAG = "Int'l Phone TextWatcher";
    private String countryNameCode;
    private int countryPhoneCode;
    private boolean internationalOnly;
    Editable lastFormatted;
    private AsYouTypeFormatter mFormatter;
    private boolean mSelfChange;
    private boolean mStopFormatting;
    private boolean needUpdateForCountryChange;
    PhoneNumberUtil phoneNumberUtil;

    public InternationalPhoneTextWatcher(Context context, String countryNameCode, int countryPhoneCode) {
        this(context, countryNameCode, countryPhoneCode, true);
    }

    public InternationalPhoneTextWatcher(Context context, String countryNameCode, int countryPhoneCode, boolean internationalOnly) {
        this.mSelfChange = false;
        this.lastFormatted = null;
        this.needUpdateForCountryChange = false;
        if (countryNameCode == null || countryNameCode.length() == 0) {
            throw new IllegalArgumentException();
        }
        this.phoneNumberUtil = PhoneNumberUtil.createInstance(context);
        updateCountry(countryNameCode, countryPhoneCode);
        this.internationalOnly = internationalOnly;
    }

    public void updateCountry(String countryNameCode, int countryPhoneCode) {
        this.countryNameCode = countryNameCode;
        this.countryPhoneCode = countryPhoneCode;
        this.mFormatter = this.phoneNumberUtil.getAsYouTypeFormatter(countryNameCode);
        this.mFormatter.clear();
        if (this.lastFormatted != null) {
            this.needUpdateForCountryChange = true;
            String onlyDigits = PhoneNumberUtil.normalizeDigitsOnly(this.lastFormatted);
            this.lastFormatted.replace(0, this.lastFormatted.length(), onlyDigits, 0, onlyDigits.length());
            this.needUpdateForCountryChange = false;
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (!this.mSelfChange && !this.mStopFormatting && count > 0 && hasSeparator(s, start, count) && !this.needUpdateForCountryChange) {
            stopFormatting();
        }
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!this.mSelfChange && !this.mStopFormatting && count > 0 && hasSeparator(s, start, count)) {
            stopFormatting();
        }
    }

    @Override // android.text.TextWatcher
    public synchronized void afterTextChanged(Editable s) {
        int finalCursorPosition;
        if (this.mStopFormatting) {
            this.mStopFormatting = s.length() != 0;
            return;
        }
        if (this.mSelfChange) {
            return;
        }
        int selectionEnd = Selection.getSelectionEnd(s);
        boolean isCursorAtEnd = selectionEnd == s.length();
        String formatted = reformat(s);
        int finalCursorPosition2 = 0;
        if (formatted.equals(s.toString())) {
            finalCursorPosition2 = selectionEnd;
        } else if (isCursorAtEnd) {
            finalCursorPosition2 = formatted.length();
        } else {
            int digitsBeforeCursor = 0;
            for (int i = 0; i < s.length() && i < selectionEnd; i++) {
                if (PhoneNumberUtils.isNonSeparator(s.charAt(i))) {
                    digitsBeforeCursor++;
                }
            }
            int i2 = 0;
            int digitPassed = 0;
            while (true) {
                if (i2 >= formatted.length()) {
                    break;
                }
                if (digitPassed == digitsBeforeCursor) {
                    finalCursorPosition2 = i2;
                    break;
                } else {
                    if (PhoneNumberUtils.isNonSeparator(formatted.charAt(i2))) {
                        digitPassed++;
                    }
                    i2++;
                }
            }
        }
        if (isCursorAtEnd) {
            finalCursorPosition = finalCursorPosition2;
        } else {
            while (finalCursorPosition2 - 1 > 0 && !PhoneNumberUtils.isNonSeparator(formatted.charAt(finalCursorPosition2 - 1))) {
                finalCursorPosition2--;
            }
            finalCursorPosition = finalCursorPosition2;
        }
        if (formatted != null) {
            try {
                this.mSelfChange = true;
                s.replace(0, s.length(), formatted, 0, formatted.length());
                this.mSelfChange = false;
                this.lastFormatted = s;
                Selection.setSelection(s, finalCursorPosition);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String reformat(CharSequence s) {
        String internationalFormatted = "";
        this.mFormatter.clear();
        char lastNonSeparator = 0;
        String countryCallingCode = Marker.ANY_NON_NULL_MARKER + this.countryPhoneCode;
        if (this.internationalOnly || (s.length() > 0 && s.charAt(0) != '0')) {
            s = countryCallingCode + ((Object) s);
        }
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (PhoneNumberUtils.isNonSeparator(c)) {
                if (lastNonSeparator != 0) {
                    internationalFormatted = this.mFormatter.inputDigit(lastNonSeparator);
                }
                lastNonSeparator = c;
            }
        }
        if (lastNonSeparator != 0) {
            internationalFormatted = this.mFormatter.inputDigit(lastNonSeparator);
        }
        String internationalFormatted2 = internationalFormatted.trim();
        if (this.internationalOnly || s.length() == 0 || s.charAt(0) != '0') {
            if (internationalFormatted2.length() > countryCallingCode.length()) {
                if (internationalFormatted2.charAt(countryCallingCode.length()) == ' ') {
                    internationalFormatted2 = internationalFormatted2.substring(countryCallingCode.length() + 1);
                } else {
                    internationalFormatted2 = internationalFormatted2.substring(countryCallingCode.length());
                }
            } else {
                internationalFormatted2 = "";
            }
        }
        return TextUtils.isEmpty(internationalFormatted2) ? "" : internationalFormatted2;
    }

    private void stopFormatting() {
        this.mStopFormatting = true;
        this.mFormatter.clear();
    }

    private boolean hasSeparator(final CharSequence s, final int start, final int count) {
        for (int i = start; i < start + count; i++) {
            char c = s.charAt(i);
            if (!PhoneNumberUtils.isNonSeparator(c)) {
                return true;
            }
        }
        return false;
    }
}
