package androidx.databinding.adapters;

import android.widget.AbsListView;

/* loaded from: classes.dex */
public class AbsListViewBindingAdapter {

    public interface OnScroll {
        void onScroll(AbsListView absListView, int i, int i2, int i3);
    }

    public interface OnScrollStateChanged {
        void onScrollStateChanged(AbsListView absListView, int i);
    }

    public static void setOnScroll(AbsListView view, final OnScroll scrollListener, final OnScrollStateChanged scrollStateListener) {
        view.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: androidx.databinding.adapters.AbsListViewBindingAdapter.1
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView view2, int scrollState) {
                if (OnScrollStateChanged.this != null) {
                    OnScrollStateChanged.this.onScrollStateChanged(view2, scrollState);
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView view2, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (scrollListener != null) {
                    scrollListener.onScroll(view2, firstVisibleItem, visibleItemCount, totalItemCount);
                }
            }
        });
    }
}
