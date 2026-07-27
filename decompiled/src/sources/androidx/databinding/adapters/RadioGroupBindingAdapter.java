package androidx.databinding.adapters;

import android.widget.RadioGroup;
import androidx.databinding.InverseBindingListener;

/* loaded from: classes.dex */
public class RadioGroupBindingAdapter {
    public static void setCheckedButton(RadioGroup view, int id) {
        if (id != view.getCheckedRadioButtonId()) {
            view.check(id);
        }
    }

    public static void setListeners(RadioGroup view, final RadioGroup.OnCheckedChangeListener listener, final InverseBindingListener attrChange) {
        if (attrChange == null) {
            view.setOnCheckedChangeListener(listener);
        } else {
            view.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: androidx.databinding.adapters.RadioGroupBindingAdapter.1
                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (listener != null) {
                        listener.onCheckedChanged(group, checkedId);
                    }
                    attrChange.onChange();
                }
            });
        }
    }
}
