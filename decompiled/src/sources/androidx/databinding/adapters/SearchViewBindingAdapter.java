package androidx.databinding.adapters;

import android.widget.SearchView;

/* loaded from: classes.dex */
public class SearchViewBindingAdapter {

    public interface OnQueryTextChange {
        boolean onQueryTextChange(String str);
    }

    public interface OnQueryTextSubmit {
        boolean onQueryTextSubmit(String str);
    }

    public interface OnSuggestionClick {
        boolean onSuggestionClick(int i);
    }

    public interface OnSuggestionSelect {
        boolean onSuggestionSelect(int i);
    }

    public static void setOnQueryTextListener(SearchView view, final OnQueryTextSubmit submit, final OnQueryTextChange change) {
        if (submit == null && change == null) {
            view.setOnQueryTextListener(null);
        } else {
            view.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: androidx.databinding.adapters.SearchViewBindingAdapter.1
                @Override // android.widget.SearchView.OnQueryTextListener
                public boolean onQueryTextSubmit(String query) {
                    if (OnQueryTextSubmit.this != null) {
                        return OnQueryTextSubmit.this.onQueryTextSubmit(query);
                    }
                    return false;
                }

                @Override // android.widget.SearchView.OnQueryTextListener
                public boolean onQueryTextChange(String newText) {
                    if (change != null) {
                        return change.onQueryTextChange(newText);
                    }
                    return false;
                }
            });
        }
    }

    public static void setOnSuggestListener(SearchView view, final OnSuggestionSelect submit, final OnSuggestionClick change) {
        if (submit == null && change == null) {
            view.setOnSuggestionListener(null);
        } else {
            view.setOnSuggestionListener(new SearchView.OnSuggestionListener() { // from class: androidx.databinding.adapters.SearchViewBindingAdapter.2
                @Override // android.widget.SearchView.OnSuggestionListener
                public boolean onSuggestionSelect(int position) {
                    if (OnSuggestionSelect.this != null) {
                        return OnSuggestionSelect.this.onSuggestionSelect(position);
                    }
                    return false;
                }

                @Override // android.widget.SearchView.OnSuggestionListener
                public boolean onSuggestionClick(int position) {
                    if (change != null) {
                        return change.onSuggestionClick(position);
                    }
                    return false;
                }
            });
        }
    }
}
