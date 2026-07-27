package com.hbb20;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.michaelrocks.libphonenumber.android.NumberParseException;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;
import io.michaelrocks.libphonenumber.android.Phonenumber;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Marker;

/* loaded from: classes17.dex */
public class CountryCodePicker extends RelativeLayout {
    static final int DEFAULT_UNSET = -99;
    String CCP_PREF_FILE;
    TextWatcher areaCodeCountryDetectorTextWatcher;
    int arrowColor;
    boolean autoDetectCountryEnabled;
    boolean autoDetectLanguageEnabled;
    int borderFlagColor;
    boolean ccpClickable;
    boolean ccpDialogInitialScrollToSelection;
    boolean ccpDialogShowFlag;
    boolean ccpDialogShowNameCode;
    boolean ccpDialogShowPhoneCode;
    boolean ccpDialogShowTitle;
    int ccpPadding;
    int ccpTextgGravity;
    boolean ccpUseDummyEmojiForPreview;
    boolean ccpUseEmoji;
    CountryCodePicker codePicker;
    int contentColor;
    Context context;
    boolean countryChangedDueToAreaCode;
    View.OnClickListener countryCodeHolderClickListener;
    boolean countryDetectionBasedOnAreaAllowed;
    String countryPreference;
    private CCPCountryGroup currentCountryGroup;
    TextGravity currentTextGravity;
    private View.OnClickListener customClickListener;
    Language customDefaultLanguage;
    private CustomDialogTextProvider customDialogTextProvider;
    List<CCPCountry> customMasterCountriesList;
    String customMasterCountriesParam;
    CCPCountry defaultCCPCountry;
    int defaultCountryCode;
    String defaultCountryNameCode;
    boolean detectCountryWithAreaCode;
    private int dialogBackgroundColor;
    private int dialogBackgroundResId;
    private float dialogCornerRadius;
    private DialogEventsListener dialogEventsListener;
    boolean dialogKeyboardAutoPopup;
    private int dialogSearchEditTextTintColor;
    private int dialogTextColor;
    Typeface dialogTypeFace;
    int dialogTypeFaceStyle;
    EditText editText_registeredCarrierNumber;
    String excludedCountriesParam;
    private FailureListener failureListener;
    int fastScrollerBubbleColor;
    private int fastScrollerBubbleTextAppearance;
    private int fastScrollerHandleColor;
    InternationalPhoneTextWatcher formattingTextWatcher;
    boolean hintExampleNumberEnabled;
    PhoneNumberType hintExampleNumberType;
    RelativeLayout holder;
    View holderView;
    ImageView imageViewArrow;
    ImageView imageViewFlag;
    boolean internationalFormattingOnly;
    Language languageToApply;
    String lastCheckedAreaCode;
    int lastCursorPosition;
    LinearLayout linearFlagBorder;
    LinearLayout linearFlagHolder;
    LayoutInflater mInflater;
    boolean numberAutoFormattingEnabled;
    private OnCountryChangeListener onCountryChangeListener;
    String originalHint;
    private PhoneNumberValidityChangeListener phoneNumberValidityChangeListener;
    PhoneNumberUtil phoneUtil;
    List<CCPCountry> preferredCountries;
    RelativeLayout relativeClickConsumer;
    boolean rememberLastSelection;
    boolean reportedValidity;
    boolean searchAllowed;
    AutoDetectionPref selectedAutoDetectionPref;
    CCPCountry selectedCCPCountry;
    String selectionMemoryTag;
    boolean showArrow;
    boolean showCloseIcon;
    boolean showFastScroller;
    boolean showFlag;
    boolean showFullName;
    boolean showNameCode;
    boolean showPhoneCode;
    private CCPTalkBackTextProvider talkBackTextProvider;
    TextView textView_selectedCountry;
    TextWatcher validityTextWatcher;
    String xmlWidth;
    static String TAG = "CCP";
    static String BUNDLE_SELECTED_CODE = "selectedCode";
    static int LIB_DEFAULT_COUNTRY_CODE = 91;
    private static int TEXT_GRAVITY_LEFT = -1;
    private static int TEXT_GRAVITY_RIGHT = 1;
    private static int TEXT_GRAVITY_CENTER = 0;
    private static String ANDROID_NAME_SPACE = "http://schemas.android.com/apk/res/android";

    public interface CustomDialogTextProvider {
        String getCCPDialogNoResultACK(Language language, String defaultNoResultACK);

        String getCCPDialogSearchHintText(Language language, String defaultSearchHintText);

        String getCCPDialogTitle(Language language, String defaultTitle);
    }

    public interface DialogEventsListener {
        void onCcpDialogCancel(DialogInterface dialogInterface);

        void onCcpDialogDismiss(DialogInterface dialogInterface);

        void onCcpDialogOpen(Dialog dialog);
    }

    public interface FailureListener {
        void onCountryAutoDetectionFailed();
    }

    public interface OnCountryChangeListener {
        void onCountrySelected();
    }

    public enum PhoneNumberType {
        MOBILE,
        FIXED_LINE,
        FIXED_LINE_OR_MOBILE,
        TOLL_FREE,
        PREMIUM_RATE,
        SHARED_COST,
        VOIP,
        PERSONAL_NUMBER,
        PAGER,
        UAN,
        VOICEMAIL,
        UNKNOWN
    }

    public interface PhoneNumberValidityChangeListener {
        void onValidityChanged(boolean isValidNumber);
    }

    public CountryCodePicker(Context context) {
        super(context);
        this.talkBackTextProvider = new InternalTalkBackTextProvider();
        this.CCP_PREF_FILE = "CCP_PREF_FILE";
        this.originalHint = "";
        this.selectedAutoDetectionPref = AutoDetectionPref.SIM_NETWORK_LOCALE;
        this.showNameCode = true;
        this.showPhoneCode = true;
        this.ccpDialogShowPhoneCode = true;
        this.showFlag = true;
        this.showFullName = false;
        this.showFastScroller = true;
        this.ccpDialogShowTitle = true;
        this.ccpDialogShowFlag = true;
        this.searchAllowed = true;
        this.showArrow = true;
        this.showCloseIcon = false;
        this.rememberLastSelection = false;
        this.detectCountryWithAreaCode = true;
        this.ccpDialogShowNameCode = true;
        this.ccpDialogInitialScrollToSelection = false;
        this.ccpUseEmoji = false;
        this.ccpUseDummyEmojiForPreview = false;
        this.internationalFormattingOnly = true;
        this.hintExampleNumberType = PhoneNumberType.MOBILE;
        this.selectionMemoryTag = "ccp_last_selection";
        this.contentColor = DEFAULT_UNSET;
        this.arrowColor = DEFAULT_UNSET;
        this.ccpTextgGravity = TEXT_GRAVITY_CENTER;
        this.fastScrollerBubbleColor = 0;
        this.customDefaultLanguage = Language.ENGLISH;
        this.languageToApply = Language.ENGLISH;
        this.dialogKeyboardAutoPopup = true;
        this.ccpClickable = true;
        this.autoDetectLanguageEnabled = false;
        this.autoDetectCountryEnabled = false;
        this.numberAutoFormattingEnabled = true;
        this.hintExampleNumberEnabled = false;
        this.xmlWidth = "notSet";
        this.lastCheckedAreaCode = null;
        this.lastCursorPosition = 0;
        this.countryChangedDueToAreaCode = false;
        this.fastScrollerHandleColor = 0;
        this.fastScrollerBubbleTextAppearance = 0;
        this.countryCodeHolderClickListener = new View.OnClickListener() { // from class: com.hbb20.CountryCodePicker.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (CountryCodePicker.this.customClickListener != null) {
                    CountryCodePicker.this.customClickListener.onClick(v);
                } else if (CountryCodePicker.this.isCcpClickable()) {
                    if (CountryCodePicker.this.ccpDialogInitialScrollToSelection) {
                        CountryCodePicker.this.launchCountrySelectionDialog(CountryCodePicker.this.getSelectedCountryNameCode());
                    } else {
                        CountryCodePicker.this.launchCountrySelectionDialog();
                    }
                }
            }
        };
        this.context = context;
        init(null);
    }

    public CountryCodePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.talkBackTextProvider = new InternalTalkBackTextProvider();
        this.CCP_PREF_FILE = "CCP_PREF_FILE";
        this.originalHint = "";
        this.selectedAutoDetectionPref = AutoDetectionPref.SIM_NETWORK_LOCALE;
        this.showNameCode = true;
        this.showPhoneCode = true;
        this.ccpDialogShowPhoneCode = true;
        this.showFlag = true;
        this.showFullName = false;
        this.showFastScroller = true;
        this.ccpDialogShowTitle = true;
        this.ccpDialogShowFlag = true;
        this.searchAllowed = true;
        this.showArrow = true;
        this.showCloseIcon = false;
        this.rememberLastSelection = false;
        this.detectCountryWithAreaCode = true;
        this.ccpDialogShowNameCode = true;
        this.ccpDialogInitialScrollToSelection = false;
        this.ccpUseEmoji = false;
        this.ccpUseDummyEmojiForPreview = false;
        this.internationalFormattingOnly = true;
        this.hintExampleNumberType = PhoneNumberType.MOBILE;
        this.selectionMemoryTag = "ccp_last_selection";
        this.contentColor = DEFAULT_UNSET;
        this.arrowColor = DEFAULT_UNSET;
        this.ccpTextgGravity = TEXT_GRAVITY_CENTER;
        this.fastScrollerBubbleColor = 0;
        this.customDefaultLanguage = Language.ENGLISH;
        this.languageToApply = Language.ENGLISH;
        this.dialogKeyboardAutoPopup = true;
        this.ccpClickable = true;
        this.autoDetectLanguageEnabled = false;
        this.autoDetectCountryEnabled = false;
        this.numberAutoFormattingEnabled = true;
        this.hintExampleNumberEnabled = false;
        this.xmlWidth = "notSet";
        this.lastCheckedAreaCode = null;
        this.lastCursorPosition = 0;
        this.countryChangedDueToAreaCode = false;
        this.fastScrollerHandleColor = 0;
        this.fastScrollerBubbleTextAppearance = 0;
        this.countryCodeHolderClickListener = new View.OnClickListener() { // from class: com.hbb20.CountryCodePicker.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (CountryCodePicker.this.customClickListener != null) {
                    CountryCodePicker.this.customClickListener.onClick(v);
                } else if (CountryCodePicker.this.isCcpClickable()) {
                    if (CountryCodePicker.this.ccpDialogInitialScrollToSelection) {
                        CountryCodePicker.this.launchCountrySelectionDialog(CountryCodePicker.this.getSelectedCountryNameCode());
                    } else {
                        CountryCodePicker.this.launchCountrySelectionDialog();
                    }
                }
            }
        };
        this.context = context;
        init(attrs);
    }

    public CountryCodePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.talkBackTextProvider = new InternalTalkBackTextProvider();
        this.CCP_PREF_FILE = "CCP_PREF_FILE";
        this.originalHint = "";
        this.selectedAutoDetectionPref = AutoDetectionPref.SIM_NETWORK_LOCALE;
        this.showNameCode = true;
        this.showPhoneCode = true;
        this.ccpDialogShowPhoneCode = true;
        this.showFlag = true;
        this.showFullName = false;
        this.showFastScroller = true;
        this.ccpDialogShowTitle = true;
        this.ccpDialogShowFlag = true;
        this.searchAllowed = true;
        this.showArrow = true;
        this.showCloseIcon = false;
        this.rememberLastSelection = false;
        this.detectCountryWithAreaCode = true;
        this.ccpDialogShowNameCode = true;
        this.ccpDialogInitialScrollToSelection = false;
        this.ccpUseEmoji = false;
        this.ccpUseDummyEmojiForPreview = false;
        this.internationalFormattingOnly = true;
        this.hintExampleNumberType = PhoneNumberType.MOBILE;
        this.selectionMemoryTag = "ccp_last_selection";
        this.contentColor = DEFAULT_UNSET;
        this.arrowColor = DEFAULT_UNSET;
        this.ccpTextgGravity = TEXT_GRAVITY_CENTER;
        this.fastScrollerBubbleColor = 0;
        this.customDefaultLanguage = Language.ENGLISH;
        this.languageToApply = Language.ENGLISH;
        this.dialogKeyboardAutoPopup = true;
        this.ccpClickable = true;
        this.autoDetectLanguageEnabled = false;
        this.autoDetectCountryEnabled = false;
        this.numberAutoFormattingEnabled = true;
        this.hintExampleNumberEnabled = false;
        this.xmlWidth = "notSet";
        this.lastCheckedAreaCode = null;
        this.lastCursorPosition = 0;
        this.countryChangedDueToAreaCode = false;
        this.fastScrollerHandleColor = 0;
        this.fastScrollerBubbleTextAppearance = 0;
        this.countryCodeHolderClickListener = new View.OnClickListener() { // from class: com.hbb20.CountryCodePicker.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (CountryCodePicker.this.customClickListener != null) {
                    CountryCodePicker.this.customClickListener.onClick(v);
                } else if (CountryCodePicker.this.isCcpClickable()) {
                    if (CountryCodePicker.this.ccpDialogInitialScrollToSelection) {
                        CountryCodePicker.this.launchCountrySelectionDialog(CountryCodePicker.this.getSelectedCountryNameCode());
                    } else {
                        CountryCodePicker.this.launchCountrySelectionDialog();
                    }
                }
            }
        };
        this.context = context;
        init(attrs);
    }

    private boolean isNumberAutoFormattingEnabled() {
        return this.numberAutoFormattingEnabled;
    }

    public void setNumberAutoFormattingEnabled(boolean numberAutoFormattingEnabled) {
        this.numberAutoFormattingEnabled = numberAutoFormattingEnabled;
        if (this.editText_registeredCarrierNumber != null) {
            updateFormattingTextWatcher();
        }
    }

    private boolean isInternationalFormattingOnlyEnabled() {
        return this.internationalFormattingOnly;
    }

    public void setInternationalFormattingOnly(boolean internationalFormattingOnly) {
        this.internationalFormattingOnly = internationalFormattingOnly;
        if (this.editText_registeredCarrierNumber != null) {
            updateFormattingTextWatcher();
        }
    }

    private void init(AttributeSet attrs) {
        this.mInflater = LayoutInflater.from(this.context);
        if (attrs != null) {
            this.xmlWidth = attrs.getAttributeValue(ANDROID_NAME_SPACE, "layout_width");
        }
        removeAllViewsInLayout();
        if (attrs == null || this.xmlWidth == null || (!this.xmlWidth.equals("-1") && !this.xmlWidth.equals("-1") && !this.xmlWidth.equals("fill_parent") && !this.xmlWidth.equals("match_parent"))) {
            this.holderView = this.mInflater.inflate(R.layout.layout_code_picker, (ViewGroup) this, true);
        } else {
            this.holderView = this.mInflater.inflate(R.layout.layout_full_width_code_picker, (ViewGroup) this, true);
        }
        this.textView_selectedCountry = (TextView) this.holderView.findViewById(R.id.textView_selectedCountry);
        this.holder = (RelativeLayout) this.holderView.findViewById(R.id.countryCodeHolder);
        this.imageViewArrow = (ImageView) this.holderView.findViewById(R.id.imageView_arrow);
        this.imageViewFlag = (ImageView) this.holderView.findViewById(R.id.image_flag);
        this.linearFlagHolder = (LinearLayout) this.holderView.findViewById(R.id.linear_flag_holder);
        this.linearFlagBorder = (LinearLayout) this.holderView.findViewById(R.id.linear_flag_border);
        this.relativeClickConsumer = (RelativeLayout) this.holderView.findViewById(R.id.rlClickConsumer);
        this.codePicker = this;
        if (attrs != null) {
            applyCustomProperty(attrs);
        }
        this.relativeClickConsumer.setOnClickListener(this.countryCodeHolderClickListener);
    }

    private void applyCustomProperty(AttributeSet attrs) {
        int contentColor;
        int borderFlagColor;
        TypedArray a = this.context.getTheme().obtainStyledAttributes(attrs, R.styleable.CountryCodePicker, 0, 0);
        try {
            try {
                this.showNameCode = a.getBoolean(R.styleable.CountryCodePicker_ccp_showNameCode, true);
                this.numberAutoFormattingEnabled = a.getBoolean(R.styleable.CountryCodePicker_ccp_autoFormatNumber, true);
                this.showPhoneCode = a.getBoolean(R.styleable.CountryCodePicker_ccp_showPhoneCode, true);
                this.ccpDialogShowPhoneCode = a.getBoolean(R.styleable.CountryCodePicker_ccpDialog_showPhoneCode, this.showPhoneCode);
                this.ccpDialogShowNameCode = a.getBoolean(R.styleable.CountryCodePicker_ccpDialog_showNameCode, true);
                this.ccpDialogShowTitle = a.getBoolean(R.styleable.CountryCodePicker_ccpDialog_showTitle, true);
                this.ccpUseEmoji = a.getBoolean(R.styleable.CountryCodePicker_ccp_useFlagEmoji, false);
                this.ccpUseDummyEmojiForPreview = a.getBoolean(R.styleable.CountryCodePicker_ccp_useDummyEmojiForPreview, false);
                this.ccpDialogShowFlag = a.getBoolean(R.styleable.CountryCodePicker_ccpDialog_showFlag, true);
                this.ccpDialogInitialScrollToSelection = a.getBoolean(R.styleable.CountryCodePicker_ccpDialog_initialScrollToSelection, false);
                this.showFullName = a.getBoolean(R.styleable.CountryCodePicker_ccp_showFullName, false);
                this.showFastScroller = a.getBoolean(R.styleable.CountryCodePicker_ccpDialog_showFastScroller, true);
                this.fastScrollerBubbleColor = a.getColor(R.styleable.CountryCodePicker_ccpDialog_fastScroller_bubbleColor, 0);
                this.fastScrollerHandleColor = a.getColor(R.styleable.CountryCodePicker_ccpDialog_fastScroller_handleColor, 0);
                this.fastScrollerBubbleTextAppearance = a.getResourceId(R.styleable.CountryCodePicker_ccpDialog_fastScroller_bubbleTextAppearance, 0);
                this.autoDetectLanguageEnabled = a.getBoolean(R.styleable.CountryCodePicker_ccp_autoDetectLanguage, false);
                this.detectCountryWithAreaCode = a.getBoolean(R.styleable.CountryCodePicker_ccp_areaCodeDetectedCountry, true);
                this.rememberLastSelection = a.getBoolean(R.styleable.CountryCodePicker_ccp_rememberLastSelection, false);
                this.hintExampleNumberEnabled = a.getBoolean(R.styleable.CountryCodePicker_ccp_hintExampleNumber, false);
                this.internationalFormattingOnly = a.getBoolean(R.styleable.CountryCodePicker_ccp_internationalFormattingOnly, true);
                this.ccpPadding = (int) a.getDimension(R.styleable.CountryCodePicker_ccp_padding, this.context.getResources().getDimension(R.dimen.ccp_padding));
                this.relativeClickConsumer.setPadding(this.ccpPadding, this.ccpPadding, this.ccpPadding, this.ccpPadding);
                int hintNumberTypeIndex = a.getInt(R.styleable.CountryCodePicker_ccp_hintExampleNumberType, 0);
                this.hintExampleNumberType = PhoneNumberType.values()[hintNumberTypeIndex];
                this.selectionMemoryTag = a.getString(R.styleable.CountryCodePicker_ccp_selectionMemoryTag);
                if (this.selectionMemoryTag == null) {
                    this.selectionMemoryTag = "CCP_last_selection";
                }
                int autoDetectionPrefValue = a.getInt(R.styleable.CountryCodePicker_ccp_countryAutoDetectionPref, 123);
                this.selectedAutoDetectionPref = AutoDetectionPref.getPrefForValue(String.valueOf(autoDetectionPrefValue));
                this.autoDetectCountryEnabled = a.getBoolean(R.styleable.CountryCodePicker_ccp_autoDetectCountry, false);
                this.showArrow = a.getBoolean(R.styleable.CountryCodePicker_ccp_showArrow, true);
                refreshArrowViewVisibility();
                this.showCloseIcon = a.getBoolean(R.styleable.CountryCodePicker_ccpDialog_showCloseIcon, false);
                showFlag(a.getBoolean(R.styleable.CountryCodePicker_ccp_showFlag, true));
                setDialogKeyboardAutoPopup(a.getBoolean(R.styleable.CountryCodePicker_ccpDialog_keyboardAutoPopup, true));
                int attrLanguage = a.getInt(R.styleable.CountryCodePicker_ccp_defaultLanguage, Language.ENGLISH.ordinal());
                this.customDefaultLanguage = getLanguageEnum(attrLanguage);
                updateLanguageToApply();
                this.customMasterCountriesParam = a.getString(R.styleable.CountryCodePicker_ccp_customMasterCountries);
                this.excludedCountriesParam = a.getString(R.styleable.CountryCodePicker_ccp_excludedCountries);
                if (!isInEditMode()) {
                    refreshCustomMasterList();
                }
                this.countryPreference = a.getString(R.styleable.CountryCodePicker_ccp_countryPreference);
                if (!isInEditMode()) {
                    refreshPreferredCountries();
                }
                if (a.hasValue(R.styleable.CountryCodePicker_ccp_textGravity)) {
                    this.ccpTextgGravity = a.getInt(R.styleable.CountryCodePicker_ccp_textGravity, TEXT_GRAVITY_CENTER);
                }
                applyTextGravity(this.ccpTextgGravity);
                this.defaultCountryNameCode = a.getString(R.styleable.CountryCodePicker_ccp_defaultNameCode);
                boolean setUsingNameCode = false;
                if (this.defaultCountryNameCode != null && this.defaultCountryNameCode.length() != 0) {
                    if (!isInEditMode()) {
                        if (CCPCountry.getCountryForNameCodeFromLibraryMasterList(getContext(), getLanguageToApply(), this.defaultCountryNameCode) != null) {
                            setUsingNameCode = true;
                            setDefaultCountry(CCPCountry.getCountryForNameCodeFromLibraryMasterList(getContext(), getLanguageToApply(), this.defaultCountryNameCode));
                            setSelectedCountry(this.defaultCCPCountry);
                        }
                    } else if (CCPCountry.getCountryForNameCodeFromEnglishList(this.defaultCountryNameCode) != null) {
                        setUsingNameCode = true;
                        setDefaultCountry(CCPCountry.getCountryForNameCodeFromEnglishList(this.defaultCountryNameCode));
                        setSelectedCountry(this.defaultCCPCountry);
                    }
                    if (!setUsingNameCode) {
                        setDefaultCountry(CCPCountry.getCountryForNameCodeFromEnglishList("IN"));
                        setSelectedCountry(this.defaultCCPCountry);
                        setUsingNameCode = true;
                    }
                }
                int defaultCountryCode = a.getInteger(R.styleable.CountryCodePicker_ccp_defaultPhoneCode, -1);
                if (!setUsingNameCode && defaultCountryCode != -1) {
                    if (isInEditMode()) {
                        CCPCountry defaultCountry = CCPCountry.getCountryForCodeFromEnglishList(defaultCountryCode + "");
                        if (defaultCountry == null) {
                            defaultCountry = CCPCountry.getCountryForCodeFromEnglishList(LIB_DEFAULT_COUNTRY_CODE + "");
                        }
                        setDefaultCountry(defaultCountry);
                        setSelectedCountry(defaultCountry);
                    } else {
                        if (defaultCountryCode != -1 && CCPCountry.getCountryForCode(getContext(), getLanguageToApply(), this.preferredCountries, defaultCountryCode) == null) {
                            defaultCountryCode = LIB_DEFAULT_COUNTRY_CODE;
                        }
                        setDefaultCountryUsingPhoneCode(defaultCountryCode);
                        setSelectedCountry(this.defaultCCPCountry);
                    }
                }
                if (getDefaultCountry() == null) {
                    setDefaultCountry(CCPCountry.getCountryForNameCodeFromEnglishList("IN"));
                    if (getSelectedCountry() == null) {
                        setSelectedCountry(this.defaultCCPCountry);
                    }
                }
                if (isAutoDetectCountryEnabled() && !isInEditMode()) {
                    setAutoDetectedCountry(true);
                }
                if (this.rememberLastSelection && !isInEditMode()) {
                    loadLastSelectedCountryInCCP();
                }
                int arrowColor = a.getColor(R.styleable.CountryCodePicker_ccp_arrowColor, DEFAULT_UNSET);
                setArrowColor(arrowColor);
                if (isInEditMode()) {
                    contentColor = a.getColor(R.styleable.CountryCodePicker_ccp_contentColor, DEFAULT_UNSET);
                } else {
                    int contentColor2 = R.styleable.CountryCodePicker_ccp_contentColor;
                    contentColor = a.getColor(contentColor2, this.context.getResources().getColor(R.color.defaultContentColor));
                }
                if (contentColor != DEFAULT_UNSET) {
                    setContentColor(contentColor);
                }
                if (isInEditMode()) {
                    borderFlagColor = a.getColor(R.styleable.CountryCodePicker_ccp_flagBorderColor, 0);
                } else {
                    int borderFlagColor2 = R.styleable.CountryCodePicker_ccp_flagBorderColor;
                    borderFlagColor = a.getColor(borderFlagColor2, this.context.getResources().getColor(R.color.defaultBorderFlagColor));
                }
                if (borderFlagColor != 0) {
                    setFlagBorderColor(borderFlagColor);
                }
                setDialogBackgroundColor(a.getColor(R.styleable.CountryCodePicker_ccpDialog_backgroundColor, 0));
                setDialogBackground(a.getResourceId(R.styleable.CountryCodePicker_ccpDialog_background, 0));
                setDialogTextColor(a.getColor(R.styleable.CountryCodePicker_ccpDialog_textColor, 0));
                setDialogSearchEditTextTintColor(a.getColor(R.styleable.CountryCodePicker_ccpDialog_searchEditTextTint, 0));
                setDialogCornerRaius(a.getDimension(R.styleable.CountryCodePicker_ccpDialog_cornerRadius, 0.0f));
                int textSize = a.getDimensionPixelSize(R.styleable.CountryCodePicker_ccp_textSize, 0);
                if (textSize > 0) {
                    this.textView_selectedCountry.setTextSize(0, textSize);
                    setFlagSize(textSize);
                    setArrowSize(textSize);
                }
                int arrowSize = a.getDimensionPixelSize(R.styleable.CountryCodePicker_ccp_arrowSize, 0);
                if (arrowSize > 0) {
                    setArrowSize(arrowSize);
                }
                this.searchAllowed = a.getBoolean(R.styleable.CountryCodePicker_ccpDialog_allowSearch, true);
                setCcpClickable(a.getBoolean(R.styleable.CountryCodePicker_ccp_clickable, true));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            a.recycle();
        }
    }

    private void refreshArrowViewVisibility() {
        if (this.showArrow) {
            this.imageViewArrow.setVisibility(0);
        } else {
            this.imageViewArrow.setVisibility(8);
        }
    }

    private void loadLastSelectedCountryInCCP() {
        SharedPreferences sharedPref = this.context.getSharedPreferences(this.CCP_PREF_FILE, 0);
        String lastSelectedCountryNameCode = sharedPref.getString(this.selectionMemoryTag, null);
        if (lastSelectedCountryNameCode != null) {
            setCountryForNameCode(lastSelectedCountryNameCode);
        }
    }

    void storeSelectedCountryNameCode(String selectedCountryNameCode) {
        SharedPreferences sharedPref = this.context.getSharedPreferences(this.CCP_PREF_FILE, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(this.selectionMemoryTag, selectedCountryNameCode);
        editor.apply();
    }

    boolean isCcpDialogShowPhoneCode() {
        return this.ccpDialogShowPhoneCode;
    }

    public void setCcpDialogShowPhoneCode(boolean ccpDialogShowPhoneCode) {
        this.ccpDialogShowPhoneCode = ccpDialogShowPhoneCode;
    }

    public boolean getCcpDialogShowNameCode() {
        return this.ccpDialogShowNameCode;
    }

    public void setCcpDialogShowNameCode(boolean ccpDialogShowNameCode) {
        this.ccpDialogShowNameCode = ccpDialogShowNameCode;
    }

    public boolean getCcpDialogShowTitle() {
        return this.ccpDialogShowTitle;
    }

    public void setCcpDialogShowTitle(boolean ccpDialogShowTitle) {
        this.ccpDialogShowTitle = ccpDialogShowTitle;
    }

    public boolean getCcpDialogShowFlag() {
        return this.ccpDialogShowFlag;
    }

    public void setCcpDialogShowFlag(boolean ccpDialogShowFlag) {
        this.ccpDialogShowFlag = ccpDialogShowFlag;
    }

    boolean isShowPhoneCode() {
        return this.showPhoneCode;
    }

    public void setShowPhoneCode(boolean showPhoneCode) {
        this.showPhoneCode = showPhoneCode;
        setSelectedCountry(this.selectedCCPCountry);
    }

    protected DialogEventsListener getDialogEventsListener() {
        return this.dialogEventsListener;
    }

    public void setDialogEventsListener(DialogEventsListener dialogEventsListener) {
        this.dialogEventsListener = dialogEventsListener;
    }

    int getFastScrollerBubbleTextAppearance() {
        return this.fastScrollerBubbleTextAppearance;
    }

    public void setFastScrollerBubbleTextAppearance(int fastScrollerBubbleTextAppearance) {
        this.fastScrollerBubbleTextAppearance = fastScrollerBubbleTextAppearance;
    }

    int getFastScrollerHandleColor() {
        return this.fastScrollerHandleColor;
    }

    public void setFastScrollerHandleColor(int fastScrollerHandleColor) {
        this.fastScrollerHandleColor = fastScrollerHandleColor;
    }

    int getFastScrollerBubbleColor() {
        return this.fastScrollerBubbleColor;
    }

    public void setFastScrollerBubbleColor(int fastScrollerBubbleColor) {
        this.fastScrollerBubbleColor = fastScrollerBubbleColor;
    }

    TextGravity getCurrentTextGravity() {
        return this.currentTextGravity;
    }

    public void setCurrentTextGravity(TextGravity textGravity) {
        this.currentTextGravity = textGravity;
        applyTextGravity(textGravity.enumIndex);
    }

    private void applyTextGravity(int enumIndex) {
        if (enumIndex == TextGravity.LEFT.enumIndex) {
            this.textView_selectedCountry.setGravity(3);
        } else if (enumIndex == TextGravity.CENTER.enumIndex) {
            this.textView_selectedCountry.setGravity(17);
        } else {
            this.textView_selectedCountry.setGravity(5);
        }
    }

    private void updateLanguageToApply() {
        if (isInEditMode()) {
            if (this.customDefaultLanguage != null) {
                this.languageToApply = this.customDefaultLanguage;
                return;
            } else {
                this.languageToApply = Language.ENGLISH;
                return;
            }
        }
        if (isAutoDetectLanguageEnabled()) {
            Language localeBasedLanguage = getCCPLanguageFromLocale();
            if (localeBasedLanguage == null) {
                if (getCustomDefaultLanguage() != null) {
                    this.languageToApply = getCustomDefaultLanguage();
                    return;
                } else {
                    this.languageToApply = Language.ENGLISH;
                    return;
                }
            }
            this.languageToApply = localeBasedLanguage;
            return;
        }
        if (getCustomDefaultLanguage() != null) {
            this.languageToApply = this.customDefaultLanguage;
        } else {
            this.languageToApply = Language.ENGLISH;
        }
    }

    private Language getCCPLanguageFromLocale() {
        Locale currentLocale = this.context.getResources().getConfiguration().locale;
        for (Language language : Language.values()) {
            if (language.getCode().equalsIgnoreCase(currentLocale.getLanguage())) {
                if (language.getCountry() == null || language.getCountry().equalsIgnoreCase(currentLocale.getCountry())) {
                    return language;
                }
                if (language.getScript() == null || language.getScript().equalsIgnoreCase(currentLocale.getScript())) {
                    return language;
                }
            }
        }
        return null;
    }

    private CCPCountry getDefaultCountry() {
        return this.defaultCCPCountry;
    }

    private void setDefaultCountry(CCPCountry defaultCCPCountry) {
        this.defaultCCPCountry = defaultCCPCountry;
    }

    public TextView getTextView_selectedCountry() {
        return this.textView_selectedCountry;
    }

    public void setTextView_selectedCountry(TextView textView_selectedCountry) {
        this.textView_selectedCountry = textView_selectedCountry;
    }

    public ImageView getImageViewFlag() {
        return this.imageViewFlag;
    }

    public void setImageViewFlag(ImageView imageViewFlag) {
        this.imageViewFlag = imageViewFlag;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CCPCountry getSelectedCountry() {
        if (this.selectedCCPCountry == null) {
            setSelectedCountry(getDefaultCountry());
        }
        return this.selectedCCPCountry;
    }

    void setSelectedCountry(CCPCountry selectedCCPCountry) {
        if (this.talkBackTextProvider != null && this.talkBackTextProvider.getTalkBackTextForCountry(selectedCCPCountry) != null) {
            this.textView_selectedCountry.setContentDescription(this.talkBackTextProvider.getTalkBackTextForCountry(selectedCCPCountry));
        }
        this.countryDetectionBasedOnAreaAllowed = false;
        this.lastCheckedAreaCode = "";
        if (selectedCCPCountry == null && (selectedCCPCountry = CCPCountry.getCountryForCode(getContext(), getLanguageToApply(), this.preferredCountries, this.defaultCountryCode)) == null) {
            return;
        }
        this.selectedCCPCountry = selectedCCPCountry;
        String displayText = "";
        if (this.showFlag && this.ccpUseEmoji) {
            displayText = isInEditMode() ? this.ccpUseDummyEmojiForPreview ? "🏁\u200b " : "" + CCPCountry.getFlagEmoji(selectedCCPCountry) + "\u200b " : "" + CCPCountry.getFlagEmoji(selectedCCPCountry) + "  ";
        }
        if (this.showFullName) {
            displayText = displayText + selectedCCPCountry.getName();
        }
        if (this.showNameCode) {
            if (this.showFullName) {
                displayText = displayText + " (" + selectedCCPCountry.getNameCode().toUpperCase() + ")";
            } else {
                displayText = displayText + StringUtils.SPACE + selectedCCPCountry.getNameCode().toUpperCase();
            }
        }
        if (this.showPhoneCode) {
            if (displayText.length() > 0) {
                displayText = displayText + "  ";
            }
            displayText = displayText + Marker.ANY_NON_NULL_MARKER + selectedCCPCountry.getPhoneCode();
        }
        this.textView_selectedCountry.setText(displayText);
        if (!this.showFlag && displayText.length() == 0) {
            this.textView_selectedCountry.setText(displayText + Marker.ANY_NON_NULL_MARKER + selectedCCPCountry.getPhoneCode());
        }
        this.imageViewFlag.setImageResource(selectedCCPCountry.getFlagID());
        if (this.onCountryChangeListener != null) {
            this.onCountryChangeListener.onCountrySelected();
        }
        updateFormattingTextWatcher();
        updateHint();
        if (this.editText_registeredCarrierNumber != null && this.phoneNumberValidityChangeListener != null) {
            this.reportedValidity = isValidFullNumber();
            this.phoneNumberValidityChangeListener.onValidityChanged(this.reportedValidity);
        }
        this.countryDetectionBasedOnAreaAllowed = true;
        if (this.countryChangedDueToAreaCode) {
            try {
                this.editText_registeredCarrierNumber.setSelection(this.lastCursorPosition);
                this.countryChangedDueToAreaCode = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        updateCountryGroup();
    }

    private void updateCountryGroup() {
        this.currentCountryGroup = CCPCountryGroup.getCountryGroupForPhoneCode(getSelectedCountryCodeAsInt());
    }

    private void updateHint() {
        if (this.editText_registeredCarrierNumber != null && this.hintExampleNumberEnabled) {
            String formattedNumber = "";
            Phonenumber.PhoneNumber exampleNumber = getPhoneUtil().getExampleNumberForType(getSelectedCountryNameCode(), getSelectedHintNumberType());
            if (exampleNumber != null) {
                String formattedNumber2 = exampleNumber.getNationalNumber() + "";
                formattedNumber = PhoneNumberUtils.formatNumber(getSelectedCountryCodeWithPlus() + formattedNumber2, getSelectedCountryNameCode());
                if (formattedNumber != null) {
                    formattedNumber = formattedNumber.substring(getSelectedCountryCodeWithPlus().length()).trim();
                }
            }
            if (formattedNumber == null) {
                formattedNumber = this.originalHint;
            }
            this.editText_registeredCarrierNumber.setHint(formattedNumber);
        }
    }

    private PhoneNumberUtil.PhoneNumberType getSelectedHintNumberType() {
        switch (this.hintExampleNumberType) {
            case MOBILE:
                return PhoneNumberUtil.PhoneNumberType.MOBILE;
            case FIXED_LINE:
                return PhoneNumberUtil.PhoneNumberType.FIXED_LINE;
            case FIXED_LINE_OR_MOBILE:
                return PhoneNumberUtil.PhoneNumberType.FIXED_LINE_OR_MOBILE;
            case TOLL_FREE:
                return PhoneNumberUtil.PhoneNumberType.TOLL_FREE;
            case PREMIUM_RATE:
                return PhoneNumberUtil.PhoneNumberType.PREMIUM_RATE;
            case SHARED_COST:
                return PhoneNumberUtil.PhoneNumberType.SHARED_COST;
            case VOIP:
                return PhoneNumberUtil.PhoneNumberType.VOIP;
            case PERSONAL_NUMBER:
                return PhoneNumberUtil.PhoneNumberType.PERSONAL_NUMBER;
            case PAGER:
                return PhoneNumberUtil.PhoneNumberType.PAGER;
            case UAN:
                return PhoneNumberUtil.PhoneNumberType.UAN;
            case VOICEMAIL:
                return PhoneNumberUtil.PhoneNumberType.VOICEMAIL;
            case UNKNOWN:
                return PhoneNumberUtil.PhoneNumberType.UNKNOWN;
            default:
                return PhoneNumberUtil.PhoneNumberType.MOBILE;
        }
    }

    public Language getLanguageToApply() {
        if (this.languageToApply == null) {
            updateLanguageToApply();
        }
        return this.languageToApply;
    }

    void setLanguageToApply(Language languageToApply) {
        this.languageToApply = languageToApply;
    }

    private void updateFormattingTextWatcher() {
        if (this.editText_registeredCarrierNumber != null && this.selectedCCPCountry != null) {
            String enteredValue = getEditText_registeredCarrierNumber().getText().toString();
            String digitsValue = PhoneNumberUtil.normalizeDigitsOnly(enteredValue);
            if (this.formattingTextWatcher != null) {
                this.editText_registeredCarrierNumber.removeTextChangedListener(this.formattingTextWatcher);
            }
            if (this.areaCodeCountryDetectorTextWatcher != null) {
                this.editText_registeredCarrierNumber.removeTextChangedListener(this.areaCodeCountryDetectorTextWatcher);
            }
            if (this.numberAutoFormattingEnabled) {
                this.formattingTextWatcher = new InternationalPhoneTextWatcher(this.context, getSelectedCountryNameCode(), getSelectedCountryCodeAsInt(), this.internationalFormattingOnly);
                this.editText_registeredCarrierNumber.addTextChangedListener(this.formattingTextWatcher);
            }
            if (this.detectCountryWithAreaCode) {
                this.areaCodeCountryDetectorTextWatcher = getCountryDetectorTextWatcher();
                this.editText_registeredCarrierNumber.addTextChangedListener(this.areaCodeCountryDetectorTextWatcher);
            }
            this.editText_registeredCarrierNumber.setText("");
            this.editText_registeredCarrierNumber.setText(digitsValue);
            this.editText_registeredCarrierNumber.setSelection(this.editText_registeredCarrierNumber.getText().length());
            return;
        }
        if (this.editText_registeredCarrierNumber == null) {
            Log.v(TAG, "updateFormattingTextWatcher: EditText not registered " + this.selectionMemoryTag);
        } else {
            Log.v(TAG, "updateFormattingTextWatcher: selected country is null " + this.selectionMemoryTag);
        }
    }

    private TextWatcher getCountryDetectorTextWatcher() {
        if (this.editText_registeredCarrierNumber != null && this.areaCodeCountryDetectorTextWatcher == null) {
            this.areaCodeCountryDetectorTextWatcher = new TextWatcher() { // from class: com.hbb20.CountryCodePicker.2
                String lastCheckedNumber = null;

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    CCPCountry selectedCountry = CountryCodePicker.this.getSelectedCountry();
                    if (selectedCountry != null) {
                        if ((this.lastCheckedNumber == null || !this.lastCheckedNumber.equals(s.toString())) && CountryCodePicker.this.countryDetectionBasedOnAreaAllowed) {
                            if (CountryCodePicker.this.currentCountryGroup != null) {
                                String enteredValue = CountryCodePicker.this.getEditText_registeredCarrierNumber().getText().toString();
                                if (enteredValue.length() >= CountryCodePicker.this.currentCountryGroup.areaCodeLength) {
                                    String digitsValue = PhoneNumberUtil.normalizeDigitsOnly(enteredValue);
                                    if (digitsValue.length() >= CountryCodePicker.this.currentCountryGroup.areaCodeLength) {
                                        String currentAreaCode = digitsValue.substring(0, CountryCodePicker.this.currentCountryGroup.areaCodeLength);
                                        if (!currentAreaCode.equals(CountryCodePicker.this.lastCheckedAreaCode)) {
                                            CCPCountry detectedCountry = CountryCodePicker.this.currentCountryGroup.getCountryForAreaCode(CountryCodePicker.this.context, CountryCodePicker.this.getLanguageToApply(), currentAreaCode);
                                            if (!detectedCountry.equals(selectedCountry)) {
                                                CountryCodePicker.this.countryChangedDueToAreaCode = true;
                                                CountryCodePicker.this.lastCursorPosition = Selection.getSelectionEnd(s);
                                                CountryCodePicker.this.setSelectedCountry(detectedCountry);
                                            }
                                            CountryCodePicker.this.lastCheckedAreaCode = currentAreaCode;
                                        }
                                    }
                                }
                            }
                            this.lastCheckedNumber = s.toString();
                        }
                    }
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable s) {
                }
            };
        }
        return this.areaCodeCountryDetectorTextWatcher;
    }

    Language getCustomDefaultLanguage() {
        return this.customDefaultLanguage;
    }

    private void setCustomDefaultLanguage(Language customDefaultLanguage) {
        CCPCountry country;
        this.customDefaultLanguage = customDefaultLanguage;
        updateLanguageToApply();
        if (this.selectedCCPCountry != null && (country = CCPCountry.getCountryForNameCodeFromLibraryMasterList(this.context, getLanguageToApply(), this.selectedCCPCountry.getNameCode())) != null) {
            setSelectedCountry(country);
        }
    }

    private View getHolderView() {
        return this.holderView;
    }

    private void setHolderView(View holderView) {
        this.holderView = holderView;
    }

    public RelativeLayout getHolder() {
        return this.holder;
    }

    private void setHolder(RelativeLayout holder) {
        this.holder = holder;
    }

    boolean isAutoDetectLanguageEnabled() {
        return this.autoDetectLanguageEnabled;
    }

    boolean isAutoDetectCountryEnabled() {
        return this.autoDetectCountryEnabled;
    }

    boolean isDialogKeyboardAutoPopup() {
        return this.dialogKeyboardAutoPopup;
    }

    public void setDialogKeyboardAutoPopup(boolean dialogKeyboardAutoPopup) {
        this.dialogKeyboardAutoPopup = dialogKeyboardAutoPopup;
    }

    boolean isShowFastScroller() {
        return this.showFastScroller;
    }

    public void setShowFastScroller(boolean showFastScroller) {
        this.showFastScroller = showFastScroller;
    }

    protected boolean isShowCloseIcon() {
        return this.showCloseIcon;
    }

    public void showCloseIcon(boolean showCloseIcon) {
        this.showCloseIcon = showCloseIcon;
    }

    EditText getEditText_registeredCarrierNumber() {
        return this.editText_registeredCarrierNumber;
    }

    void setEditText_registeredCarrierNumber(EditText editText_registeredCarrierNumber) {
        this.editText_registeredCarrierNumber = editText_registeredCarrierNumber;
        if (this.editText_registeredCarrierNumber.getHint() != null) {
            this.originalHint = this.editText_registeredCarrierNumber.getHint().toString();
        }
        updateValidityTextWatcher();
        updateFormattingTextWatcher();
        updateHint();
    }

    private void updateValidityTextWatcher() {
        try {
            this.editText_registeredCarrierNumber.removeTextChangedListener(this.validityTextWatcher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.reportedValidity = isValidFullNumber();
        if (this.phoneNumberValidityChangeListener != null) {
            this.phoneNumberValidityChangeListener.onValidityChanged(this.reportedValidity);
        }
        this.validityTextWatcher = new TextWatcher() { // from class: com.hbb20.CountryCodePicker.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                boolean currentValidity;
                if (CountryCodePicker.this.phoneNumberValidityChangeListener != null && (currentValidity = CountryCodePicker.this.isValidFullNumber()) != CountryCodePicker.this.reportedValidity) {
                    CountryCodePicker.this.reportedValidity = currentValidity;
                    CountryCodePicker.this.phoneNumberValidityChangeListener.onValidityChanged(CountryCodePicker.this.reportedValidity);
                }
            }
        };
        this.editText_registeredCarrierNumber.addTextChangedListener(this.validityTextWatcher);
    }

    private LayoutInflater getmInflater() {
        return this.mInflater;
    }

    private View.OnClickListener getCountryCodeHolderClickListener() {
        return this.countryCodeHolderClickListener;
    }

    int getDialogBackgroundColor() {
        return this.dialogBackgroundColor;
    }

    public void setDialogBackgroundColor(int dialogBackgroundColor) {
        this.dialogBackgroundColor = dialogBackgroundColor;
    }

    int getDialogBackgroundResId() {
        return this.dialogBackgroundResId;
    }

    public void setDialogBackground(int dialogBackgroundResId) {
        this.dialogBackgroundResId = dialogBackgroundResId;
    }

    int getDialogSearchEditTextTintColor() {
        return this.dialogSearchEditTextTintColor;
    }

    public void setDialogSearchEditTextTintColor(int dialogSearchEditTextTintColor) {
        this.dialogSearchEditTextTintColor = dialogSearchEditTextTintColor;
    }

    public float getDialogCornerRadius() {
        return this.dialogCornerRadius;
    }

    public void setDialogCornerRaius(float dialogCornerRadius) {
        this.dialogCornerRadius = dialogCornerRadius;
    }

    int getDialogTextColor() {
        return this.dialogTextColor;
    }

    public void setDialogTextColor(int dialogTextColor) {
        this.dialogTextColor = dialogTextColor;
    }

    int getDialogTypeFaceStyle() {
        return this.dialogTypeFaceStyle;
    }

    Typeface getDialogTypeFace() {
        return this.dialogTypeFace;
    }

    public void setDialogTypeFace(Typeface typeFace) {
        try {
            this.dialogTypeFace = typeFace;
            this.dialogTypeFaceStyle = DEFAULT_UNSET;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void refreshPreferredCountries() {
        if (this.countryPreference == null || this.countryPreference.length() == 0) {
            this.preferredCountries = null;
        } else {
            List<CCPCountry> localCCPCountryList = new ArrayList<>();
            for (String nameCode : this.countryPreference.split(",")) {
                CCPCountry ccpCountry = CCPCountry.getCountryForNameCodeFromCustomMasterList(getContext(), this.customMasterCountriesList, getLanguageToApply(), nameCode);
                if (ccpCountry != null && !isAlreadyInList(ccpCountry, localCCPCountryList)) {
                    localCCPCountryList.add(ccpCountry);
                }
            }
            if (localCCPCountryList.size() == 0) {
                this.preferredCountries = null;
            } else {
                this.preferredCountries = localCCPCountryList;
            }
        }
        if (this.preferredCountries != null) {
            for (CCPCountry CCPCountry : this.preferredCountries) {
                CCPCountry.log();
            }
        }
    }

    void refreshCustomMasterList() {
        if (this.customMasterCountriesParam == null || this.customMasterCountriesParam.length() == 0) {
            if (this.excludedCountriesParam != null && this.excludedCountriesParam.length() != 0) {
                this.excludedCountriesParam = this.excludedCountriesParam.toLowerCase();
                List<CCPCountry> libraryMasterList = CCPCountry.getLibraryMasterCountryList(this.context, getLanguageToApply());
                List<CCPCountry> localCCPCountryList = new ArrayList<>();
                for (CCPCountry ccpCountry : libraryMasterList) {
                    if (!this.excludedCountriesParam.contains(ccpCountry.getNameCode().toLowerCase())) {
                        localCCPCountryList.add(ccpCountry);
                    }
                }
                if (localCCPCountryList.size() > 0) {
                    this.customMasterCountriesList = localCCPCountryList;
                } else {
                    this.customMasterCountriesList = null;
                }
            } else {
                this.customMasterCountriesList = null;
            }
        } else {
            List<CCPCountry> localCCPCountryList2 = new ArrayList<>();
            for (String nameCode : this.customMasterCountriesParam.split(",")) {
                CCPCountry ccpCountry2 = CCPCountry.getCountryForNameCodeFromLibraryMasterList(getContext(), getLanguageToApply(), nameCode);
                if (ccpCountry2 != null && !isAlreadyInList(ccpCountry2, localCCPCountryList2)) {
                    localCCPCountryList2.add(ccpCountry2);
                }
            }
            if (localCCPCountryList2.size() == 0) {
                this.customMasterCountriesList = null;
            } else {
                this.customMasterCountriesList = localCCPCountryList2;
            }
        }
        if (this.customMasterCountriesList != null) {
            Iterator<CCPCountry> it = this.customMasterCountriesList.iterator();
            while (it.hasNext()) {
                it.next().log();
            }
        }
    }

    List<CCPCountry> getCustomMasterCountriesList() {
        return this.customMasterCountriesList;
    }

    void setCustomMasterCountriesList(List<CCPCountry> customMasterCountriesList) {
        this.customMasterCountriesList = customMasterCountriesList;
    }

    String getCustomMasterCountriesParam() {
        return this.customMasterCountriesParam;
    }

    public void setCustomMasterCountries(String customMasterCountriesParam) {
        this.customMasterCountriesParam = customMasterCountriesParam;
    }

    public void setExcludedCountries(String excludedCountries) {
        this.excludedCountriesParam = excludedCountries;
        refreshCustomMasterList();
    }

    boolean isCcpClickable() {
        return this.ccpClickable;
    }

    public void setCcpClickable(boolean ccpClickable) {
        this.ccpClickable = ccpClickable;
        if (!ccpClickable) {
            this.relativeClickConsumer.setOnClickListener(null);
            this.relativeClickConsumer.setClickable(false);
            this.relativeClickConsumer.setEnabled(false);
        } else {
            this.relativeClickConsumer.setOnClickListener(this.countryCodeHolderClickListener);
            this.relativeClickConsumer.setClickable(true);
            this.relativeClickConsumer.setEnabled(true);
        }
    }

    private boolean isAlreadyInList(CCPCountry CCPCountry, List<CCPCountry> CCPCountryList) {
        if (CCPCountry != null && CCPCountryList != null) {
            for (CCPCountry iterationCCPCountry : CCPCountryList) {
                if (iterationCCPCountry.getNameCode().equalsIgnoreCase(CCPCountry.getNameCode())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private String detectCarrierNumber(String fullNumber, CCPCountry CCPCountry) {
        if (CCPCountry == null || fullNumber == null || fullNumber.isEmpty()) {
            return fullNumber;
        }
        int indexOfCode = fullNumber.indexOf(CCPCountry.getPhoneCode());
        if (indexOfCode == -1) {
            return fullNumber;
        }
        String carrierNumber = CCPCountry.getPhoneCode();
        return fullNumber.substring(carrierNumber.length() + indexOfCode);
    }

    private Language getLanguageEnum(int index) {
        if (index < Language.values().length) {
            return Language.values()[index];
        }
        return Language.ENGLISH;
    }

    String getDialogTitle() {
        String defaultTitle = CCPCountry.getDialogTitle(this.context, getLanguageToApply());
        if (this.customDialogTextProvider != null) {
            return this.customDialogTextProvider.getCCPDialogTitle(getLanguageToApply(), defaultTitle);
        }
        return defaultTitle;
    }

    String getSearchHintText() {
        String defaultHint = CCPCountry.getSearchHintMessage(this.context, getLanguageToApply());
        if (this.customDialogTextProvider != null) {
            return this.customDialogTextProvider.getCCPDialogSearchHintText(getLanguageToApply(), defaultHint);
        }
        return defaultHint;
    }

    String getNoResultACK() {
        String defaultNoResultACK = CCPCountry.getNoResultFoundAckMessage(this.context, getLanguageToApply());
        if (this.customDialogTextProvider != null) {
            return this.customDialogTextProvider.getCCPDialogNoResultACK(getLanguageToApply(), defaultNoResultACK);
        }
        return defaultNoResultACK;
    }

    @Deprecated
    public void setDefaultCountryUsingPhoneCode(int defaultCountryCode) {
        CCPCountry defaultCCPCountry = CCPCountry.getCountryForCode(getContext(), getLanguageToApply(), this.preferredCountries, defaultCountryCode);
        if (defaultCCPCountry != null) {
            this.defaultCountryCode = defaultCountryCode;
            setDefaultCountry(defaultCCPCountry);
        }
    }

    public void setDefaultCountryUsingNameCode(String defaultCountryNameCode) {
        CCPCountry defaultCCPCountry = CCPCountry.getCountryForNameCodeFromLibraryMasterList(getContext(), getLanguageToApply(), defaultCountryNameCode);
        if (defaultCCPCountry != null) {
            this.defaultCountryNameCode = defaultCCPCountry.getNameCode();
            setDefaultCountry(defaultCCPCountry);
        }
    }

    public String getDefaultCountryCode() {
        return this.defaultCCPCountry.phoneCode;
    }

    public int getDefaultCountryCodeAsInt() {
        try {
            int code = Integer.parseInt(getDefaultCountryCode());
            return code;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getDefaultCountryCodeWithPlus() {
        return Marker.ANY_NON_NULL_MARKER + getDefaultCountryCode();
    }

    public String getDefaultCountryName() {
        CCPCountry dc = getDefaultCountry();
        return dc == null ? "" : dc.name;
    }

    public String getDefaultCountryNameCode() {
        CCPCountry dc = getDefaultCountry();
        return dc == null ? "" : dc.nameCode.toUpperCase();
    }

    public void resetToDefaultCountry() {
        this.defaultCCPCountry = CCPCountry.getCountryForNameCodeFromLibraryMasterList(getContext(), getLanguageToApply(), getDefaultCountryNameCode());
        setSelectedCountry(this.defaultCCPCountry);
    }

    public String getSelectedCountryCode() {
        return getSelectedCountry().phoneCode;
    }

    public String getSelectedCountryCodeWithPlus() {
        return Marker.ANY_NON_NULL_MARKER + getSelectedCountryCode();
    }

    public int getSelectedCountryCodeAsInt() {
        try {
            int code = Integer.parseInt(getSelectedCountryCode());
            return code;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getSelectedCountryName() {
        return getSelectedCountry().name;
    }

    public String getSelectedCountryEnglishName() {
        return getSelectedCountry().getEnglishName();
    }

    public String getSelectedCountryNameCode() {
        return getSelectedCountry().nameCode.toUpperCase();
    }

    public int getSelectedCountryFlagResourceId() {
        return getSelectedCountry().flagResID;
    }

    public void setCountryForPhoneCode(int countryCode) {
        CCPCountry ccpCountry = CCPCountry.getCountryForCode(getContext(), getLanguageToApply(), this.preferredCountries, countryCode);
        if (ccpCountry == null) {
            if (this.defaultCCPCountry == null) {
                this.defaultCCPCountry = CCPCountry.getCountryForCode(getContext(), getLanguageToApply(), this.preferredCountries, this.defaultCountryCode);
            }
            setSelectedCountry(this.defaultCCPCountry);
            return;
        }
        setSelectedCountry(ccpCountry);
    }

    public void setCountryForNameCode(String countryNameCode) {
        CCPCountry country = CCPCountry.getCountryForNameCodeFromLibraryMasterList(getContext(), getLanguageToApply(), countryNameCode);
        if (country == null) {
            if (this.defaultCCPCountry == null) {
                this.defaultCCPCountry = CCPCountry.getCountryForCode(getContext(), getLanguageToApply(), this.preferredCountries, this.defaultCountryCode);
            }
            setSelectedCountry(this.defaultCCPCountry);
            return;
        }
        setSelectedCountry(country);
    }

    public void registerCarrierNumberEditText(EditText editTextCarrierNumber) {
        setEditText_registeredCarrierNumber(editTextCarrierNumber);
    }

    public void deregisterCarrierNumberEditText() {
        if (this.editText_registeredCarrierNumber != null) {
            try {
                this.editText_registeredCarrierNumber.removeTextChangedListener(this.validityTextWatcher);
            } catch (Exception e) {
            }
            try {
                this.editText_registeredCarrierNumber.removeTextChangedListener(this.formattingTextWatcher);
            } catch (Exception e2) {
            }
            this.editText_registeredCarrierNumber.setHint("");
            this.editText_registeredCarrierNumber = null;
        }
    }

    private Phonenumber.PhoneNumber getEnteredPhoneNumber() throws NumberParseException {
        String carrierNumber = "";
        if (this.editText_registeredCarrierNumber != null) {
            carrierNumber = PhoneNumberUtil.normalizeDigitsOnly(this.editText_registeredCarrierNumber.getText().toString());
        }
        return getPhoneUtil().parse(carrierNumber, getSelectedCountryNameCode());
    }

    public String getFullNumber() {
        try {
            Phonenumber.PhoneNumber phoneNumber = getEnteredPhoneNumber();
            return getPhoneUtil().format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164).substring(1);
        } catch (NumberParseException e) {
            Log.e(TAG, "getFullNumber: Could not parse number");
            return getSelectedCountryCode() + PhoneNumberUtil.normalizeDigitsOnly(this.editText_registeredCarrierNumber.getText().toString());
        }
    }

    public void setFullNumber(String fullNumber) {
        CCPCountry country = CCPCountry.getCountryForNumber(getContext(), getLanguageToApply(), this.preferredCountries, fullNumber);
        if (country == null) {
            country = getDefaultCountry();
        }
        setSelectedCountry(country);
        String carrierNumber = detectCarrierNumber(fullNumber, country);
        if (getEditText_registeredCarrierNumber() != null) {
            getEditText_registeredCarrierNumber().setText(carrierNumber);
            updateFormattingTextWatcher();
        } else {
            Log.w(TAG, "EditText for carrier number is not registered. Register it using registerCarrierNumberEditText() before getFullNumber() or setFullNumber().");
        }
    }

    public String getFormattedFullNumber() {
        try {
            Phonenumber.PhoneNumber phoneNumber = getEnteredPhoneNumber();
            return Marker.ANY_NON_NULL_MARKER + getPhoneUtil().format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL).substring(1);
        } catch (NumberParseException e) {
            Log.e(TAG, "getFullNumber: Could not parse number");
            return getFullNumberWithPlus();
        }
    }

    public String getFullNumberWithPlus() {
        return Marker.ANY_NON_NULL_MARKER + getFullNumber();
    }

    public int getContentColor() {
        return this.contentColor;
    }

    public void setContentColor(int contentColor) {
        this.contentColor = contentColor;
        this.textView_selectedCountry.setTextColor(this.contentColor);
        if (this.arrowColor == DEFAULT_UNSET) {
            this.imageViewArrow.setColorFilter(this.contentColor, PorterDuff.Mode.SRC_IN);
        }
    }

    public void setArrowColor(int arrowColor) {
        this.arrowColor = arrowColor;
        if (this.arrowColor == DEFAULT_UNSET) {
            if (this.contentColor != DEFAULT_UNSET) {
                this.imageViewArrow.setColorFilter(this.contentColor, PorterDuff.Mode.SRC_IN);
                return;
            }
            return;
        }
        this.imageViewArrow.setColorFilter(this.arrowColor, PorterDuff.Mode.SRC_IN);
    }

    public void setFlagBorderColor(int borderFlagColor) {
        this.borderFlagColor = borderFlagColor;
        this.linearFlagBorder.setBackgroundColor(this.borderFlagColor);
    }

    public void setTextSize(int textSize) {
        if (textSize > 0) {
            this.textView_selectedCountry.setTextSize(0, textSize);
            setArrowSize(textSize);
            setFlagSize(textSize);
        }
    }

    public void setArrowSize(int arrowSize) {
        if (arrowSize > 0) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) this.imageViewArrow.getLayoutParams();
            params.width = arrowSize;
            params.height = arrowSize;
            this.imageViewArrow.setLayoutParams(params);
        }
    }

    public void showNameCode(boolean showNameCode) {
        this.showNameCode = showNameCode;
        setSelectedCountry(this.selectedCCPCountry);
    }

    public void showArrow(boolean showArrow) {
        this.showArrow = showArrow;
        refreshArrowViewVisibility();
    }

    public void setCountryPreference(String countryPreference) {
        this.countryPreference = countryPreference;
    }

    public void changeDefaultLanguage(Language language) {
        setCustomDefaultLanguage(language);
    }

    public void setTypeFace(Typeface typeFace) {
        try {
            this.textView_selectedCountry.setTypeface(typeFace);
            setDialogTypeFace(typeFace);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDialogTypeFace(Typeface typeFace, int style) {
        try {
            this.dialogTypeFace = typeFace;
            if (this.dialogTypeFace == null) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTypeFace(Typeface typeFace, int style) {
        try {
            this.textView_selectedCountry.setTypeface(typeFace, style);
            setDialogTypeFace(typeFace, style);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnCountryChangeListener(OnCountryChangeListener onCountryChangeListener) {
        this.onCountryChangeListener = onCountryChangeListener;
    }

    public void setFlagSize(int flagSize) {
        this.imageViewFlag.getLayoutParams().height = flagSize;
        this.imageViewFlag.requestLayout();
    }

    public void showFlag(boolean showFlag) {
        this.showFlag = showFlag;
        refreshFlagVisibility();
        if (!isInEditMode()) {
            setSelectedCountry(this.selectedCCPCountry);
        }
    }

    private void refreshFlagVisibility() {
        if (this.showFlag) {
            if (this.ccpUseEmoji) {
                this.linearFlagHolder.setVisibility(8);
                return;
            } else {
                this.linearFlagHolder.setVisibility(0);
                return;
            }
        }
        this.linearFlagHolder.setVisibility(8);
    }

    public void useFlagEmoji(boolean useFlagEmoji) {
        this.ccpUseEmoji = useFlagEmoji;
        refreshFlagVisibility();
        setSelectedCountry(this.selectedCCPCountry);
    }

    public void showFullName(boolean showFullName) {
        this.showFullName = showFullName;
        setSelectedCountry(this.selectedCCPCountry);
    }

    public boolean isSearchAllowed() {
        return this.searchAllowed;
    }

    public void setSearchAllowed(boolean searchAllowed) {
        this.searchAllowed = searchAllowed;
    }

    public void setPhoneNumberValidityChangeListener(PhoneNumberValidityChangeListener phoneNumberValidityChangeListener) {
        this.phoneNumberValidityChangeListener = phoneNumberValidityChangeListener;
        if (this.editText_registeredCarrierNumber != null && phoneNumberValidityChangeListener != null) {
            this.reportedValidity = isValidFullNumber();
            phoneNumberValidityChangeListener.onValidityChanged(this.reportedValidity);
        }
    }

    public void setAutoDetectionFailureListener(FailureListener failureListener) {
        this.failureListener = failureListener;
    }

    public void setCustomDialogTextProvider(CustomDialogTextProvider customDialogTextProvider) {
        this.customDialogTextProvider = customDialogTextProvider;
    }

    public void launchCountrySelectionDialog() {
        launchCountrySelectionDialog(null);
    }

    public void launchCountrySelectionDialog(final String countryNameCode) {
        CountryCodeDialog.openCountryCodeDialog(this.codePicker, countryNameCode);
    }

    public boolean isValidFullNumber() {
        try {
            if (getEditText_registeredCarrierNumber() != null && getEditText_registeredCarrierNumber().getText().length() != 0) {
                Phonenumber.PhoneNumber phoneNumber = getPhoneUtil().parse(Marker.ANY_NON_NULL_MARKER + this.selectedCCPCountry.getPhoneCode() + getEditText_registeredCarrierNumber().getText().toString(), this.selectedCCPCountry.getNameCode());
                return getPhoneUtil().isValidNumber(phoneNumber);
            }
            if (getEditText_registeredCarrierNumber() == null) {
                Toast.makeText(this.context, "No editText for Carrier number found.", 0).show();
                return false;
            }
            return false;
        } catch (NumberParseException e) {
            return false;
        }
    }

    private PhoneNumberUtil getPhoneUtil() {
        if (this.phoneUtil == null) {
            this.phoneUtil = PhoneNumberUtil.createInstance(this.context);
        }
        return this.phoneUtil;
    }

    public void setAutoDetectedCountry(boolean loadDefaultWhenFails) {
        boolean successfullyDetected = false;
        for (int i = 0; i < this.selectedAutoDetectionPref.representation.length(); i++) {
            try {
                switch (this.selectedAutoDetectionPref.representation.charAt(i)) {
                    case '1':
                        successfullyDetected = detectSIMCountry(false);
                        break;
                    case '2':
                        successfullyDetected = detectNetworkCountry(false);
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                        successfullyDetected = detectLocaleCountry(false);
                        break;
                }
                if (!successfullyDetected) {
                    if (this.failureListener != null) {
                        this.failureListener.onCountryAutoDetectionFailed();
                    }
                } else if (successfullyDetected && loadDefaultWhenFails) {
                    resetToDefaultCountry();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.w(TAG, "setAutoDetectCountry: Exception" + e.getMessage());
                if (loadDefaultWhenFails) {
                    resetToDefaultCountry();
                    return;
                }
                return;
            }
        }
        if (successfullyDetected) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0031, code lost:
    
        resetToDefaultCountry();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean detectSIMCountry(boolean loadDefaultWhenFails) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.context.getSystemService("phone");
            String simCountryISO = telephonyManager.getSimCountryIso();
            if (simCountryISO != null && !simCountryISO.isEmpty() && isNameCodeInCustomMasterList(simCountryISO)) {
                setSelectedCountry(CCPCountry.getCountryForNameCodeFromLibraryMasterList(getContext(), getLanguageToApply(), simCountryISO));
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            if (loadDefaultWhenFails) {
                resetToDefaultCountry();
            }
            return false;
        }
    }

    private boolean isNameCodeInCustomMasterList(String nameCode) {
        List<CCPCountry> allowedList = CCPCountry.getCustomMasterCountryList(this.context, this);
        for (CCPCountry country : allowedList) {
            if (country.nameCode.equalsIgnoreCase(nameCode)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0031, code lost:
    
        resetToDefaultCountry();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean detectNetworkCountry(boolean loadDefaultWhenFails) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.context.getSystemService("phone");
            String networkCountryISO = telephonyManager.getNetworkCountryIso();
            if (networkCountryISO != null && !networkCountryISO.isEmpty() && isNameCodeInCustomMasterList(networkCountryISO)) {
                setSelectedCountry(CCPCountry.getCountryForNameCodeFromLibraryMasterList(getContext(), getLanguageToApply(), networkCountryISO));
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            if (loadDefaultWhenFails) {
                resetToDefaultCountry();
            }
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0033, code lost:
    
        resetToDefaultCountry();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean detectLocaleCountry(boolean loadDefaultWhenFails) {
        try {
            String localeCountryISO = this.context.getResources().getConfiguration().locale.getCountry();
            if (localeCountryISO != null && !localeCountryISO.isEmpty() && isNameCodeInCustomMasterList(localeCountryISO)) {
                setSelectedCountry(CCPCountry.getCountryForNameCodeFromLibraryMasterList(getContext(), getLanguageToApply(), localeCountryISO));
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            if (loadDefaultWhenFails) {
                resetToDefaultCountry();
            }
            return false;
        }
    }

    public void setCountryAutoDetectionPref(AutoDetectionPref selectedAutoDetectionPref) {
        this.selectedAutoDetectionPref = selectedAutoDetectionPref;
    }

    protected void onUserTappedCountry(CCPCountry CCPCountry) {
        if (this.codePicker.rememberLastSelection) {
            this.codePicker.storeSelectedCountryNameCode(CCPCountry.getNameCode());
        }
        setSelectedCountry(CCPCountry);
    }

    public void setDetectCountryWithAreaCode(boolean detectCountryWithAreaCode) {
        this.detectCountryWithAreaCode = detectCountryWithAreaCode;
        updateFormattingTextWatcher();
    }

    public void setHintExampleNumberEnabled(boolean hintExampleNumberEnabled) {
        this.hintExampleNumberEnabled = hintExampleNumberEnabled;
        updateHint();
    }

    public void setHintExampleNumberType(PhoneNumberType hintExampleNumberType) {
        this.hintExampleNumberType = hintExampleNumberType;
        updateHint();
    }

    public boolean isDialogInitialScrollToSelectionEnabled() {
        return this.ccpDialogInitialScrollToSelection;
    }

    public void setTalkBackTextProvider(CCPTalkBackTextProvider talkBackTextProvider) {
        this.talkBackTextProvider = talkBackTextProvider;
        setSelectedCountry(this.selectedCCPCountry);
    }

    public void enableDialogInitialScrollToSelection(boolean initialScrollToSelection) {
        this.ccpDialogInitialScrollToSelection = this.ccpDialogInitialScrollToSelection;
    }

    public void overrideClickListener(View.OnClickListener clickListener) {
        this.customClickListener = clickListener;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        CountryCodeDialog.clear();
        super.onDetachedFromWindow();
    }

    public enum Language {
        AFRIKAANS("af"),
        ARABIC("ar"),
        BENGALI("bn"),
        CHINESE_SIMPLIFIED("zh", "CN", "Hans"),
        CHINESE_TRADITIONAL("zh", "TW", "Hant"),
        CZECH("cs"),
        DANISH("da"),
        DUTCH("nl"),
        ENGLISH("en"),
        FARSI("fa"),
        FRENCH("fr"),
        GERMAN("de"),
        GREEK("el"),
        GUJARATI("gu"),
        HEBREW("iw"),
        HINDI("hi"),
        INDONESIA("in"),
        ITALIAN("it"),
        JAPANESE("ja"),
        KAZAKH("kk"),
        KOREAN("ko"),
        MARATHI("mr"),
        POLISH("pl"),
        PORTUGUESE("pt"),
        PUNJABI("pa"),
        RUSSIAN("ru"),
        SLOVAK("sk"),
        SLOVENIAN("si"),
        SPANISH("es"),
        SWEDISH("sv"),
        TAGALOG("tl"),
        THAI("th"),
        TURKISH("tr"),
        UKRAINIAN("uk"),
        URDU("ur"),
        UZBEK("uz"),
        VIETNAMESE("vi");

        private String code;
        private String country;
        private String script;

        Language(String code, String country, String script) {
            this.code = code;
            this.country = country;
            this.script = script;
        }

        Language(String code) {
            this.code = code;
        }

        public static Language forCountryNameCode(String code) {
            Language lang = ENGLISH;
            for (Language language : values()) {
                if (language.code.equals(code)) {
                    lang = language;
                }
            }
            return lang;
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCountry() {
            return this.country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getScript() {
            return this.script;
        }

        public void setScript(String script) {
            this.script = script;
        }
    }

    public enum AutoDetectionPref {
        SIM_ONLY("1"),
        NETWORK_ONLY("2"),
        LOCALE_ONLY("3"),
        SIM_NETWORK("12"),
        NETWORK_SIM("21"),
        SIM_LOCALE("13"),
        LOCALE_SIM("31"),
        NETWORK_LOCALE("23"),
        LOCALE_NETWORK("32"),
        SIM_NETWORK_LOCALE("123"),
        SIM_LOCALE_NETWORK("132"),
        NETWORK_SIM_LOCALE("213"),
        NETWORK_LOCALE_SIM("231"),
        LOCALE_SIM_NETWORK("312"),
        LOCALE_NETWORK_SIM("321");

        String representation;

        AutoDetectionPref(String representation) {
            this.representation = representation;
        }

        public static AutoDetectionPref getPrefForValue(String value) {
            for (AutoDetectionPref autoDetectionPref : values()) {
                if (autoDetectionPref.representation.equals(value)) {
                    return autoDetectionPref;
                }
            }
            return SIM_NETWORK_LOCALE;
        }
    }

    public enum TextGravity {
        LEFT(-1),
        CENTER(0),
        RIGHT(1);

        int enumIndex;

        TextGravity(int i) {
            this.enumIndex = i;
        }
    }
}
