package androidx.lifecycle;

import android.app.Application;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

@Deprecated
/* loaded from: classes.dex */
public class ViewModelProviders {
    @Deprecated
    public ViewModelProviders() {
    }

    @Deprecated
    public static ViewModelProvider of(Fragment fragment) {
        return new ViewModelProvider(fragment);
    }

    @Deprecated
    public static ViewModelProvider of(FragmentActivity activity) {
        return new ViewModelProvider(activity);
    }

    @Deprecated
    public static ViewModelProvider of(Fragment fragment, ViewModelProvider.Factory factory) {
        if (factory == null) {
            factory = fragment.getDefaultViewModelProviderFactory();
        }
        return new ViewModelProvider(fragment.getViewModelStore(), factory);
    }

    @Deprecated
    public static ViewModelProvider of(FragmentActivity activity, ViewModelProvider.Factory factory) {
        if (factory == null) {
            factory = activity.getDefaultViewModelProviderFactory();
        }
        return new ViewModelProvider(activity.getViewModelStore(), factory);
    }

    @Deprecated
    public static class DefaultFactory extends ViewModelProvider.AndroidViewModelFactory {
        @Deprecated
        public DefaultFactory(Application application) {
            super(application);
        }
    }
}
