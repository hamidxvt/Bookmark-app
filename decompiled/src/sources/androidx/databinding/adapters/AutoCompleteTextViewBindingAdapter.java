package androidx.databinding.adapters;

import android.widget.AutoCompleteTextView;
import androidx.databinding.adapters.AdapterViewBindingAdapter;

/* loaded from: classes.dex */
public class AutoCompleteTextViewBindingAdapter {

    public interface FixText {
        CharSequence fixText(CharSequence charSequence);
    }

    public interface IsValid {
        boolean isValid(CharSequence charSequence);
    }

    public static void setValidator(AutoCompleteTextView view, final FixText fixText, final IsValid isValid) {
        if (fixText == null && isValid == null) {
            view.setValidator(null);
        } else {
            view.setValidator(new AutoCompleteTextView.Validator() { // from class: androidx.databinding.adapters.AutoCompleteTextViewBindingAdapter.1
                @Override // android.widget.AutoCompleteTextView.Validator
                public boolean isValid(CharSequence text) {
                    if (IsValid.this != null) {
                        return IsValid.this.isValid(text);
                    }
                    return true;
                }

                @Override // android.widget.AutoCompleteTextView.Validator
                public CharSequence fixText(CharSequence invalidText) {
                    if (fixText != null) {
                        return fixText.fixText(invalidText);
                    }
                    return invalidText;
                }
            });
        }
    }

    public static void setOnItemSelectedListener(AutoCompleteTextView view, AdapterViewBindingAdapter.OnItemSelected selected, AdapterViewBindingAdapter.OnNothingSelected nothingSelected) {
        if (selected == null && nothingSelected == null) {
            view.setOnItemSelectedListener(null);
        } else {
            view.setOnItemSelectedListener(new AdapterViewBindingAdapter.OnItemSelectedComponentListener(selected, nothingSelected, null));
        }
    }
}
