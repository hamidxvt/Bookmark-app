package androidx.databinding.adapters;

import android.R;
import android.widget.AbsSpinner;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import java.util.List;

/* loaded from: classes.dex */
public class AbsSpinnerBindingAdapter {
    public static <T extends CharSequence> void setEntries(AbsSpinner view, T[] entries) {
        if (entries != null) {
            SpinnerAdapter oldAdapter = view.getAdapter();
            boolean changed = true;
            if (oldAdapter != null && oldAdapter.getCount() == entries.length) {
                changed = false;
                int i = 0;
                while (true) {
                    if (i >= entries.length) {
                        break;
                    }
                    if (entries[i].equals(oldAdapter.getItem(i))) {
                        i++;
                    } else {
                        changed = true;
                        break;
                    }
                }
            }
            if (changed) {
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(view.getContext(), R.layout.simple_spinner_item, entries);
                adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                view.setAdapter((SpinnerAdapter) adapter);
                return;
            }
            return;
        }
        view.setAdapter((SpinnerAdapter) null);
    }

    public static <T> void setEntries(AbsSpinner view, List<T> entries) {
        if (entries != null) {
            SpinnerAdapter oldAdapter = view.getAdapter();
            if (oldAdapter instanceof ObservableListAdapter) {
                ((ObservableListAdapter) oldAdapter).setList(entries);
                return;
            } else {
                view.setAdapter((SpinnerAdapter) new ObservableListAdapter(view.getContext(), entries, R.layout.simple_spinner_item, R.layout.simple_spinner_dropdown_item, 0));
                return;
            }
        }
        view.setAdapter((SpinnerAdapter) null);
    }
}
