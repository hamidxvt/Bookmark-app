package androidx.databinding.adapters;

import android.widget.CalendarView;
import androidx.databinding.InverseBindingListener;

/* loaded from: classes.dex */
public class CalendarViewBindingAdapter {
    public static void setDate(CalendarView view, long date) {
        if (view.getDate() != date) {
            view.setDate(date);
        }
    }

    public static void setListeners(CalendarView view, final CalendarView.OnDateChangeListener onDayChange, final InverseBindingListener attrChange) {
        if (attrChange == null) {
            view.setOnDateChangeListener(onDayChange);
        } else {
            view.setOnDateChangeListener(new CalendarView.OnDateChangeListener() { // from class: androidx.databinding.adapters.CalendarViewBindingAdapter.1
                @Override // android.widget.CalendarView.OnDateChangeListener
                public void onSelectedDayChange(CalendarView view2, int year, int month, int dayOfMonth) {
                    if (onDayChange != null) {
                        onDayChange.onSelectedDayChange(view2, year, month, dayOfMonth);
                    }
                    attrChange.onChange();
                }
            });
        }
    }
}
