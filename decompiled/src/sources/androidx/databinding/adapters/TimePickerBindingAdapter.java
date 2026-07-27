package androidx.databinding.adapters;

import android.widget.TimePicker;
import androidx.databinding.InverseBindingListener;

/* loaded from: classes.dex */
public class TimePickerBindingAdapter {
    public static void setHour(TimePicker view, int hour) {
        if (view.getHour() != hour) {
            view.setHour(hour);
        }
    }

    public static void setMinute(TimePicker view, int minute) {
        if (view.getMinute() != minute) {
            view.setMinute(minute);
        }
    }

    public static int getHour(TimePicker view) {
        return view.getHour();
    }

    public static int getMinute(TimePicker view) {
        return view.getMinute();
    }

    public static void setListeners(TimePicker view, final TimePicker.OnTimeChangedListener listener, final InverseBindingListener hourChange, final InverseBindingListener minuteChange) {
        if (hourChange == null && minuteChange == null) {
            view.setOnTimeChangedListener(listener);
        } else {
            view.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() { // from class: androidx.databinding.adapters.TimePickerBindingAdapter.1
                @Override // android.widget.TimePicker.OnTimeChangedListener
                public void onTimeChanged(TimePicker view2, int hourOfDay, int minute) {
                    if (listener != null) {
                        listener.onTimeChanged(view2, hourOfDay, minute);
                    }
                    if (hourChange != null) {
                        hourChange.onChange();
                    }
                    if (minuteChange != null) {
                        minuteChange.onChange();
                    }
                }
            });
        }
    }
}
