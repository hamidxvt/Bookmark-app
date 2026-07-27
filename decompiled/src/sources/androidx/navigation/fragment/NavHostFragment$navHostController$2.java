package androidx.navigation.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.os.BundleKt;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.NavHostController;
import androidx.savedstate.SavedStateRegistry;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: NavHostFragment.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/navigation/NavHostController;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
final class NavHostFragment$navHostController$2 extends Lambda implements Function0<NavHostController> {
    final /* synthetic */ NavHostFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    NavHostFragment$navHostController$2(NavHostFragment navHostFragment) {
        super(0);
        this.this$0 = navHostFragment;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final NavHostController invoke() {
        int i;
        int i2;
        Context context = this.this$0.getContext();
        if (context == null) {
            throw new IllegalStateException("NavController cannot be created before the fragment is attached".toString());
        }
        Intrinsics.checkNotNullExpressionValue(context, "checkNotNull(context) {\n…nt is attached\"\n        }");
        final NavHostController $this$invoke_u24lambda_u245 = new NavHostController(context);
        final NavHostFragment navHostFragment = this.this$0;
        $this$invoke_u24lambda_u245.setLifecycleOwner(navHostFragment);
        ViewModelStore viewModelStore = navHostFragment.getViewModelStore();
        Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
        $this$invoke_u24lambda_u245.setViewModelStore(viewModelStore);
        navHostFragment.onCreateNavHostController($this$invoke_u24lambda_u245);
        Bundle it = navHostFragment.getSavedStateRegistry().consumeRestoredStateForKey("android-support-nav:fragment:navControllerState");
        if (it != null) {
            $this$invoke_u24lambda_u245.restoreState(it);
        }
        navHostFragment.getSavedStateRegistry().registerSavedStateProvider("android-support-nav:fragment:navControllerState", new SavedStateRegistry.SavedStateProvider() { // from class: androidx.navigation.fragment.NavHostFragment$navHostController$2$$ExternalSyntheticLambda0
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                Bundle invoke$lambda$5$lambda$2;
                invoke$lambda$5$lambda$2 = NavHostFragment$navHostController$2.invoke$lambda$5$lambda$2(NavHostController.this);
                return invoke$lambda$5$lambda$2;
            }
        });
        Bundle bundle = navHostFragment.getSavedStateRegistry().consumeRestoredStateForKey(NavHostFragment.KEY_GRAPH_ID);
        if (bundle != null) {
            navHostFragment.graphId = bundle.getInt(NavHostFragment.KEY_GRAPH_ID);
        }
        navHostFragment.getSavedStateRegistry().registerSavedStateProvider(NavHostFragment.KEY_GRAPH_ID, new SavedStateRegistry.SavedStateProvider() { // from class: androidx.navigation.fragment.NavHostFragment$navHostController$2$$ExternalSyntheticLambda1
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                Bundle invoke$lambda$5$lambda$4;
                invoke$lambda$5$lambda$4 = NavHostFragment$navHostController$2.invoke$lambda$5$lambda$4(NavHostFragment.this);
                return invoke$lambda$5$lambda$4;
            }
        });
        i = navHostFragment.graphId;
        if (i != 0) {
            i2 = navHostFragment.graphId;
            $this$invoke_u24lambda_u245.setGraph(i2);
        } else {
            Bundle args = navHostFragment.getArguments();
            int graphId = args != null ? args.getInt(NavHostFragment.KEY_GRAPH_ID) : 0;
            Bundle startDestinationArgs = args != null ? args.getBundle(NavHostFragment.KEY_START_DESTINATION_ARGS) : null;
            if (graphId != 0) {
                $this$invoke_u24lambda_u245.setGraph(graphId, startDestinationArgs);
            }
        }
        return $this$invoke_u24lambda_u245;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bundle invoke$lambda$5$lambda$2(NavHostController this_apply) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Bundle saveState = this_apply.saveState();
        if (saveState != null) {
            return saveState;
        }
        Bundle EMPTY = Bundle.EMPTY;
        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
        return EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bundle invoke$lambda$5$lambda$4(NavHostFragment this$0) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        i = this$0.graphId;
        if (i != 0) {
            i2 = this$0.graphId;
            return BundleKt.bundleOf(TuplesKt.to(NavHostFragment.KEY_GRAPH_ID, Integer.valueOf(i2)));
        }
        Bundle bundle = Bundle.EMPTY;
        Intrinsics.checkNotNullExpressionValue(bundle, "{\n                    Bu…e.EMPTY\n                }");
        return bundle;
    }
}
