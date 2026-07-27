package com.hbb20;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.futuremind.recyclerviewfastscroll.FastScroller;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes17.dex */
class CountryCodeDialog {
    static Context context;
    static Dialog dialog;
    private static final Field sCursorDrawableField;
    private static final Field sCursorDrawableResourceField;
    private static final Field sEditorField;

    CountryCodeDialog() {
    }

    static {
        Field editorField = null;
        Field cursorDrawableField = null;
        Field cursorDrawableResourceField = null;
        boolean exceptionThrown = false;
        try {
            cursorDrawableResourceField = TextView.class.getDeclaredField("mCursorDrawableRes");
            cursorDrawableResourceField.setAccessible(true);
            editorField = TextView.class.getDeclaredField("mEditor");
            editorField.setAccessible(true);
            Class<?> drawableFieldClass = editorField.getType();
            cursorDrawableField = drawableFieldClass.getDeclaredField("mCursorDrawable");
            cursorDrawableField.setAccessible(true);
        } catch (Exception e) {
            exceptionThrown = true;
        }
        if (exceptionThrown) {
            sEditorField = null;
            sCursorDrawableField = null;
            sCursorDrawableResourceField = null;
        } else {
            sEditorField = editorField;
            sCursorDrawableField = cursorDrawableField;
            sCursorDrawableResourceField = cursorDrawableResourceField;
        }
    }

    public static void openCountryCodeDialog(final CountryCodePicker codePicker) {
        openCountryCodeDialog(codePicker, null);
    }

    public static void openCountryCodeDialog(final CountryCodePicker codePicker, final String countryNameCode) {
        context = codePicker.getContext();
        dialog = new Dialog(context);
        codePicker.refreshCustomMasterList();
        codePicker.refreshPreferredCountries();
        List<CCPCountry> masterCountries = CCPCountry.getCustomMasterCountryList(context, codePicker);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setContentView(R.layout.layout_picker_dialog);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, android.R.color.transparent));
        RecyclerView recyclerView_countryDialog = (RecyclerView) dialog.findViewById(R.id.recycler_countryDialog);
        TextView textViewTitle = (TextView) dialog.findViewById(R.id.textView_title);
        RelativeLayout rlQueryHolder = (RelativeLayout) dialog.findViewById(R.id.rl_query_holder);
        ImageView imgClearQuery = (ImageView) dialog.findViewById(R.id.img_clear_query);
        EditText editText_search = (EditText) dialog.findViewById(R.id.editText_search);
        TextView textView_noResult = (TextView) dialog.findViewById(R.id.textView_noresult);
        CardView dialogRoot = (CardView) dialog.findViewById(R.id.cardViewRoot);
        ImageView imgDismiss = (ImageView) dialog.findViewById(R.id.img_dismiss);
        if (codePicker.isSearchAllowed() && codePicker.isDialogKeyboardAutoPopup()) {
            new WindowInsetsControllerCompat(dialog.getWindow(), editText_search).show(WindowInsetsCompat.Type.ime());
            editText_search.requestFocus();
        } else {
            dialog.getWindow().setSoftInputMode(2);
        }
        try {
            if (codePicker.getDialogTypeFace() != null) {
                if (codePicker.getDialogTypeFaceStyle() != -99) {
                    textView_noResult.setTypeface(codePicker.getDialogTypeFace(), codePicker.getDialogTypeFaceStyle());
                    editText_search.setTypeface(codePicker.getDialogTypeFace(), codePicker.getDialogTypeFaceStyle());
                    textViewTitle.setTypeface(codePicker.getDialogTypeFace(), codePicker.getDialogTypeFaceStyle());
                } else {
                    textView_noResult.setTypeface(codePicker.getDialogTypeFace());
                    editText_search.setTypeface(codePicker.getDialogTypeFace());
                    textViewTitle.setTypeface(codePicker.getDialogTypeFace());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (codePicker.getDialogBackgroundColor() != 0) {
            dialogRoot.setCardBackgroundColor(codePicker.getDialogBackgroundColor());
        }
        if (codePicker.getDialogBackgroundResId() != 0) {
            dialogRoot.setBackgroundResource(codePicker.getDialogBackgroundResId());
        }
        dialogRoot.setRadius(codePicker.getDialogCornerRadius());
        if (codePicker.isShowCloseIcon()) {
            imgDismiss.setVisibility(0);
            imgDismiss.setOnClickListener(new View.OnClickListener() { // from class: com.hbb20.CountryCodeDialog.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CountryCodeDialog.dialog.dismiss();
                }
            });
        } else {
            imgDismiss.setVisibility(8);
        }
        if (!codePicker.getCcpDialogShowTitle()) {
            textViewTitle.setVisibility(8);
        }
        if (codePicker.getDialogTextColor() != 0) {
            int textColor = codePicker.getDialogTextColor();
            imgClearQuery.setColorFilter(textColor);
            imgDismiss.setColorFilter(textColor);
            textViewTitle.setTextColor(textColor);
            textView_noResult.setTextColor(textColor);
            editText_search.setTextColor(textColor);
            editText_search.setHintTextColor(Color.argb(100, Color.red(textColor), Color.green(textColor), Color.blue(textColor)));
        }
        if (codePicker.getDialogSearchEditTextTintColor() != 0) {
            editText_search.setBackgroundTintList(ColorStateList.valueOf(codePicker.getDialogSearchEditTextTintColor()));
            setCursorColor(editText_search, codePicker.getDialogSearchEditTextTintColor());
        }
        textViewTitle.setText(codePicker.getDialogTitle());
        editText_search.setHint(codePicker.getSearchHintText());
        textView_noResult.setText(codePicker.getNoResultACK());
        if (!codePicker.isSearchAllowed()) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) recyclerView_countryDialog.getLayoutParams();
            params.height = -2;
            recyclerView_countryDialog.setLayoutParams(params);
        }
        CountryCodeAdapter cca = new CountryCodeAdapter(context, masterCountries, codePicker, rlQueryHolder, editText_search, textView_noResult, dialog, imgClearQuery);
        recyclerView_countryDialog.setLayoutManager(new LinearLayoutManager(context));
        recyclerView_countryDialog.setAdapter(cca);
        FastScroller fastScroller = (FastScroller) dialog.findViewById(R.id.fastscroll);
        fastScroller.setRecyclerView(recyclerView_countryDialog);
        if (codePicker.isShowFastScroller()) {
            if (codePicker.getFastScrollerBubbleColor() != 0) {
                fastScroller.setBubbleColor(codePicker.getFastScrollerBubbleColor());
            }
            if (codePicker.getFastScrollerHandleColor() != 0) {
                fastScroller.setHandleColor(codePicker.getFastScrollerHandleColor());
            }
            if (codePicker.getFastScrollerBubbleTextAppearance() != 0) {
                try {
                    fastScroller.setBubbleTextAppearance(codePicker.getFastScrollerBubbleTextAppearance());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } else {
            fastScroller.setVisibility(8);
        }
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.hbb20.CountryCodeDialog.2
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                CountryCodeDialog.hideKeyboard(CountryCodeDialog.context);
                if (CountryCodePicker.this.getDialogEventsListener() != null) {
                    CountryCodePicker.this.getDialogEventsListener().onCcpDialogDismiss(dialogInterface);
                }
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.hbb20.CountryCodeDialog.3
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                CountryCodeDialog.hideKeyboard(CountryCodeDialog.context);
                if (CountryCodePicker.this.getDialogEventsListener() != null) {
                    CountryCodePicker.this.getDialogEventsListener().onCcpDialogCancel(dialogInterface);
                }
            }
        });
        if (countryNameCode != null) {
            boolean isPreferredCountry = false;
            if (codePicker.preferredCountries != null) {
                Iterator<CCPCountry> it = codePicker.preferredCountries.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    CCPCountry preferredCountry = it.next();
                    if (preferredCountry.nameCode.equalsIgnoreCase(countryNameCode)) {
                        isPreferredCountry = true;
                        break;
                    }
                }
            }
            if (!isPreferredCountry) {
                int preferredCountriesOffset = 0;
                if (codePicker.preferredCountries != null && codePicker.preferredCountries.size() > 0) {
                    preferredCountriesOffset = codePicker.preferredCountries.size() + 1;
                }
                int i = 0;
                while (true) {
                    if (i >= masterCountries.size()) {
                        break;
                    }
                    if (!masterCountries.get(i).nameCode.equalsIgnoreCase(countryNameCode)) {
                        i++;
                    } else {
                        recyclerView_countryDialog.scrollToPosition(i + preferredCountriesOffset);
                        break;
                    }
                }
            }
        }
        dialog.show();
        if (codePicker.getDialogEventsListener() != null) {
            codePicker.getDialogEventsListener().onCcpDialogOpen(dialog);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void hideKeyboard(Context context2) {
        if (context2 instanceof Activity) {
            Activity activity = (Activity) context2;
            InputMethodManager imm = (InputMethodManager) activity.getSystemService("input_method");
            View view = activity.getCurrentFocus();
            if (view == null) {
                view = new View(activity);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    static void setCursorColor(EditText editText, int color) {
        if (sCursorDrawableField == null) {
            return;
        }
        try {
            Drawable drawable = getDrawable(editText.getContext(), sCursorDrawableResourceField.getInt(editText));
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            sCursorDrawableField.set(sEditorField.get(editText), new Drawable[]{drawable, drawable});
        } catch (Exception e) {
        }
    }

    static void clear() {
        if (dialog != null) {
            dialog.dismiss();
        }
        dialog = null;
        context = null;
    }

    private static Drawable getDrawable(Context context2, int id) {
        return context2.getDrawable(id);
    }
}
