package androidx.databinding.adapters;

import android.widget.SeekBar;
import androidx.databinding.InverseBindingListener;

/* loaded from: classes.dex */
public class SeekBarBindingAdapter {

    public interface OnProgressChanged {
        void onProgressChanged(SeekBar seekBar, int i, boolean z);
    }

    public interface OnStartTrackingTouch {
        void onStartTrackingTouch(SeekBar seekBar);
    }

    public interface OnStopTrackingTouch {
        void onStopTrackingTouch(SeekBar seekBar);
    }

    public static void setProgress(SeekBar view, int progress) {
        if (progress != view.getProgress()) {
            view.setProgress(progress);
        }
    }

    public static void setOnSeekBarChangeListener(SeekBar view, final OnStartTrackingTouch start, final OnStopTrackingTouch stop, final OnProgressChanged progressChanged, final InverseBindingListener attrChanged) {
        if (start == null && stop == null && progressChanged == null && attrChanged == null) {
            view.setOnSeekBarChangeListener(null);
        } else {
            view.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: androidx.databinding.adapters.SeekBarBindingAdapter.1
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (OnProgressChanged.this != null) {
                        OnProgressChanged.this.onProgressChanged(seekBar, progress, fromUser);
                    }
                    if (attrChanged != null) {
                        attrChanged.onChange();
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar) {
                    if (start != null) {
                        start.onStartTrackingTouch(seekBar);
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (stop != null) {
                        stop.onStopTrackingTouch(seekBar);
                    }
                }
            });
        }
    }
}
