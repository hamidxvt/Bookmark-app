package androidx.navigation.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.NavigatorState;
import androidx.navigation.fragment.FragmentNavigator;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: FragmentNavigator.kt */
@Navigator.Name("fragment")
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010#\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0017\u0018\u0000 @2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0004?@ABB\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ$\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00172\b\b\u0002\u0010 \u001a\u00020\u00182\b\b\u0002\u0010!\u001a\u00020\u0018H\u0002J%\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020'H\u0000¢\u0006\u0002\b(J\u0018\u0010)\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\r2\u0006\u0010#\u001a\u00020$H\u0002J\b\u0010*\u001a\u00020\u0002H\u0016J\u001a\u0010+\u001a\u00020,2\u0006\u0010%\u001a\u00020\r2\b\u0010-\u001a\u0004\u0018\u00010.H\u0002J*\u0010/\u001a\u00020$2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u00172\b\u00101\u001a\u0004\u0018\u000102H\u0017J$\u00103\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\r2\b\u0010-\u001a\u0004\u0018\u00010.2\b\u00104\u001a\u0004\u0018\u000105H\u0002J*\u00103\u001a\u00020\u001e2\f\u00106\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\u0010-\u001a\u0004\u0018\u00010.2\b\u00104\u001a\u0004\u0018\u000105H\u0016J\u0010\u00107\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020'H\u0016J\u0010\u00108\u001a\u00020\u001e2\u0006\u00109\u001a\u00020\rH\u0016J\u0010\u0010:\u001a\u00020\u001e2\u0006\u0010;\u001a\u000202H\u0016J\n\u0010<\u001a\u0004\u0018\u000102H\u0016J\u0018\u0010=\u001a\u00020\u001e2\u0006\u0010>\u001a\u00020\r2\u0006\u0010;\u001a\u00020\u0018H\u0016R \u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00110\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u00160\u0015X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00170\u001cX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Landroidx/navigation/fragment/FragmentNavigator;", "Landroidx/navigation/Navigator;", "Landroidx/navigation/fragment/FragmentNavigator$Destination;", "context", "Landroid/content/Context;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "containerId", "", "(Landroid/content/Context;Landroidx/fragment/app/FragmentManager;I)V", "backStack", "Lkotlinx/coroutines/flow/StateFlow;", "", "Landroidx/navigation/NavBackStackEntry;", "getBackStack$navigation_fragment_release", "()Lkotlinx/coroutines/flow/StateFlow;", "fragmentObserver", "Landroidx/lifecycle/LifecycleEventObserver;", "fragmentViewObserver", "Lkotlin/Function1;", "pendingOps", "", "Lkotlin/Pair;", "", "", "getPendingOps$navigation_fragment_release", "()Ljava/util/List;", "savedIds", "", "addPendingOps", "", Constant.VISIT_ID, "isPop", "deduplicate", "attachClearViewModel", "fragment", "Landroidx/fragment/app/Fragment;", "entry", "state", "Landroidx/navigation/NavigatorState;", "attachClearViewModel$navigation_fragment_release", "attachObservers", "createDestination", "createFragmentTransaction", "Landroidx/fragment/app/FragmentTransaction;", "navOptions", "Landroidx/navigation/NavOptions;", "instantiateFragment", "className", "args", "Landroid/os/Bundle;", "navigate", "navigatorExtras", "Landroidx/navigation/Navigator$Extras;", "entries", "onAttach", "onLaunchSingleTop", "backStackEntry", "onRestoreState", "savedState", "onSaveState", "popBackStack", "popUpTo", "ClearEntryStateViewModel", "Companion", "Destination", "Extras", "navigation-fragment_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public class FragmentNavigator extends Navigator<Destination> {
    private static final Companion Companion = new Companion(null);
    private static final String KEY_SAVED_IDS = "androidx-nav-fragment:navigator:savedIds";
    private static final String TAG = "FragmentNavigator";
    private final int containerId;
    private final Context context;
    private final FragmentManager fragmentManager;
    private final LifecycleEventObserver fragmentObserver;
    private final Function1<NavBackStackEntry, LifecycleEventObserver> fragmentViewObserver;
    private final List<Pair<String, Boolean>> pendingOps;
    private final Set<String> savedIds;

    public FragmentNavigator(Context context, FragmentManager fragmentManager, int containerId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
        this.savedIds = new LinkedHashSet();
        this.pendingOps = new ArrayList();
        this.fragmentObserver = new LifecycleEventObserver() { // from class: androidx.navigation.fragment.FragmentNavigator$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                FragmentNavigator.fragmentObserver$lambda$1(FragmentNavigator.this, lifecycleOwner, event);
            }
        };
        this.fragmentViewObserver = new FragmentNavigator$fragmentViewObserver$1(this);
    }

    public final List<Pair<String, Boolean>> getPendingOps$navigation_fragment_release() {
        return this.pendingOps;
    }

    public final StateFlow<List<NavBackStackEntry>> getBackStack$navigation_fragment_release() {
        return getState().getBackStack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fragmentObserver$lambda$1(FragmentNavigator this$0, LifecycleOwner source, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event == Lifecycle.Event.ON_DESTROY) {
            Fragment fragment = (Fragment) source;
            Iterable $this$lastOrNull$iv = this$0.getState().getTransitionsInProgress().getValue();
            Object last$iv = null;
            for (Object element$iv : $this$lastOrNull$iv) {
                if (Intrinsics.areEqual(((NavBackStackEntry) element$iv).getId(), fragment.getTag())) {
                    last$iv = element$iv;
                }
            }
            NavBackStackEntry entry = (NavBackStackEntry) last$iv;
            if (entry != null) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(TAG, "Marking transition complete for entry " + entry + " due to fragment " + source + " lifecycle reaching DESTROYED");
                }
                this$0.getState().markTransitionComplete(entry);
            }
        }
    }

    @Override // androidx.navigation.Navigator
    public void onAttach(final NavigatorState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        super.onAttach(state);
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(TAG, "onAttach");
        }
        this.fragmentManager.addFragmentOnAttachListener(new FragmentOnAttachListener() { // from class: androidx.navigation.fragment.FragmentNavigator$$ExternalSyntheticLambda1
            @Override // androidx.fragment.app.FragmentOnAttachListener
            public final void onAttachFragment(FragmentManager fragmentManager, Fragment fragment) {
                FragmentNavigator.onAttach$lambda$3(NavigatorState.this, this, fragmentManager, fragment);
            }
        });
        this.fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() { // from class: androidx.navigation.fragment.FragmentNavigator$onAttach$2
            @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
            public void onBackStackChanged() {
            }

            @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
            public void onBackStackChangeStarted(Fragment fragment, boolean pop) {
                Object element$iv;
                Intrinsics.checkNotNullParameter(fragment, "fragment");
                if (pop) {
                    List $this$lastOrNull$iv = NavigatorState.this.getBackStack().getValue();
                    ListIterator iterator$iv = $this$lastOrNull$iv.listIterator($this$lastOrNull$iv.size());
                    while (true) {
                        if (!iterator$iv.hasPrevious()) {
                            element$iv = null;
                            break;
                        }
                        element$iv = iterator$iv.previous();
                        NavBackStackEntry it = (NavBackStackEntry) element$iv;
                        if (Intrinsics.areEqual(it.getId(), fragment.getTag())) {
                            break;
                        }
                    }
                    NavBackStackEntry entry = (NavBackStackEntry) element$iv;
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentNavigator", "OnBackStackChangedStarted for fragment " + fragment + " associated with entry " + entry);
                    }
                    if (entry != null) {
                        NavigatorState.this.prepareForTransition(entry);
                    }
                }
            }

            @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
            public void onBackStackChangeCommitted(Fragment fragment, boolean pop) {
                Object obj;
                Object element$iv;
                Intrinsics.checkNotNullParameter(fragment, "fragment");
                List $this$lastOrNull$iv = CollectionsKt.plus((Collection) NavigatorState.this.getBackStack().getValue(), (Iterable) NavigatorState.this.getTransitionsInProgress().getValue());
                ListIterator iterator$iv = $this$lastOrNull$iv.listIterator($this$lastOrNull$iv.size());
                while (true) {
                    obj = null;
                    if (!iterator$iv.hasPrevious()) {
                        element$iv = null;
                        break;
                    }
                    element$iv = iterator$iv.previous();
                    NavBackStackEntry it = (NavBackStackEntry) element$iv;
                    if (Intrinsics.areEqual(it.getId(), fragment.getTag())) {
                        break;
                    }
                }
                NavBackStackEntry entry = (NavBackStackEntry) element$iv;
                boolean isSystemBack = pop && this.getPendingOps$navigation_fragment_release().isEmpty() && fragment.isRemoving();
                Iterable $this$firstOrNull$iv = this.getPendingOps$navigation_fragment_release();
                Iterator it2 = $this$firstOrNull$iv.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Object element$iv2 = it2.next();
                    Pair it3 = (Pair) element$iv2;
                    if (Intrinsics.areEqual(it3.getFirst(), fragment.getTag())) {
                        obj = element$iv2;
                        break;
                    }
                }
                Pair op = (Pair) obj;
                if (op != null) {
                    this.getPendingOps$navigation_fragment_release().remove(op);
                }
                if (!isSystemBack && FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentNavigator", "OnBackStackChangedCommitted for fragment " + fragment + " associated with entry " + entry);
                }
                boolean popOp = op != null && ((Boolean) op.getSecond()).booleanValue();
                if (!pop && !popOp && entry == null) {
                    throw new IllegalArgumentException(("The fragment " + fragment + " is unknown to the FragmentNavigator. Please use the navigate() function to add fragments to the FragmentNavigator managed FragmentManager.").toString());
                }
                if (entry != null) {
                    this.attachClearViewModel$navigation_fragment_release(fragment, entry, NavigatorState.this);
                    if (isSystemBack) {
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentNavigator", "OnBackStackChangedCommitted for fragment " + fragment + " popping associated entry " + entry + " via system back");
                        }
                        NavigatorState.this.popWithTransition(entry, false);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onAttach$lambda$3(NavigatorState state, FragmentNavigator this$0, FragmentManager fragmentManager, Fragment fragment) {
        Object element$iv;
        Intrinsics.checkNotNullParameter(state, "$state");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fragmentManager, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        List $this$lastOrNull$iv = state.getBackStack().getValue();
        ListIterator iterator$iv = $this$lastOrNull$iv.listIterator($this$lastOrNull$iv.size());
        while (true) {
            if (!iterator$iv.hasPrevious()) {
                element$iv = null;
                break;
            }
            element$iv = iterator$iv.previous();
            NavBackStackEntry it = (NavBackStackEntry) element$iv;
            if (Intrinsics.areEqual(it.getId(), fragment.getTag())) {
                break;
            }
        }
        NavBackStackEntry entry = (NavBackStackEntry) element$iv;
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(TAG, "Attaching fragment " + fragment + " associated with entry " + entry + " to FragmentManager " + this$0.fragmentManager);
        }
        if (entry != null) {
            this$0.attachObservers(entry, fragment);
            this$0.attachClearViewModel$navigation_fragment_release(fragment, entry, state);
        }
    }

    private final void attachObservers(final NavBackStackEntry entry, final Fragment fragment) {
        fragment.getViewLifecycleOwnerLiveData().observe(fragment, new FragmentNavigator$sam$androidx_lifecycle_Observer$0(new Function1<LifecycleOwner, Unit>() { // from class: androidx.navigation.fragment.FragmentNavigator$attachObservers$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LifecycleOwner lifecycleOwner) {
                invoke2(lifecycleOwner);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LifecycleOwner lifecycleOwner) {
                Function1 function1;
                List<Pair<String, Boolean>> pendingOps$navigation_fragment_release = FragmentNavigator.this.getPendingOps$navigation_fragment_release();
                Fragment fragment2 = fragment;
                char c = 0;
                c = 0;
                if (!(pendingOps$navigation_fragment_release instanceof Collection) || !pendingOps$navigation_fragment_release.isEmpty()) {
                    Iterator<T> it = pendingOps$navigation_fragment_release.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        } else if (Intrinsics.areEqual(((Pair) it.next()).getFirst(), fragment2.getTag())) {
                            c = 1;
                            break;
                        }
                    }
                }
                char c2 = c;
                if (lifecycleOwner != null && c2 == null) {
                    Lifecycle lifecycle = fragment.getViewLifecycleOwner().getLifecycle();
                    if (lifecycle.getState().isAtLeast(Lifecycle.State.CREATED)) {
                        function1 = FragmentNavigator.this.fragmentViewObserver;
                        lifecycle.addObserver((LifecycleObserver) function1.invoke(entry));
                    }
                }
            }
        }));
        fragment.getLifecycle().addObserver(this.fragmentObserver);
    }

    public final void attachClearViewModel$navigation_fragment_release(final Fragment fragment, final NavBackStackEntry entry, final NavigatorState state) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(entry, "entry");
        Intrinsics.checkNotNullParameter(state, "state");
        ViewModelStore viewModelStore = fragment.getViewModelStore();
        Intrinsics.checkNotNullExpressionValue(viewModelStore, "fragment.viewModelStore");
        InitializerViewModelFactoryBuilder $this$attachClearViewModel_u24lambda_u244 = new InitializerViewModelFactoryBuilder();
        $this$attachClearViewModel_u24lambda_u244.addInitializer(Reflection.getOrCreateKotlinClass(ClearEntryStateViewModel.class), new Function1<CreationExtras, ClearEntryStateViewModel>() { // from class: androidx.navigation.fragment.FragmentNavigator$attachClearViewModel$viewModel$1$1
            @Override // kotlin.jvm.functions.Function1
            public final FragmentNavigator.ClearEntryStateViewModel invoke(CreationExtras initializer) {
                Intrinsics.checkNotNullParameter(initializer, "$this$initializer");
                return new FragmentNavigator.ClearEntryStateViewModel();
            }
        });
        ClearEntryStateViewModel viewModel = (ClearEntryStateViewModel) new ViewModelProvider(viewModelStore, $this$attachClearViewModel_u24lambda_u244.build(), CreationExtras.Empty.INSTANCE).get(ClearEntryStateViewModel.class);
        viewModel.setCompleteTransition(new WeakReference<>(new Function0<Unit>() { // from class: androidx.navigation.fragment.FragmentNavigator$attachClearViewModel$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                NavBackStackEntry navBackStackEntry = NavBackStackEntry.this;
                NavigatorState navigatorState = state;
                Fragment fragment2 = fragment;
                Iterable $this$forEach$iv = navigatorState.getTransitionsInProgress().getValue();
                for (Object element$iv : $this$forEach$iv) {
                    NavBackStackEntry entry2 = (NavBackStackEntry) element$iv;
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentNavigator", "Marking transition complete for entry " + entry2 + " due to fragment " + fragment2 + " viewmodel being cleared");
                    }
                    navigatorState.markTransitionComplete(entry2);
                }
            }
        }));
    }

    @Override // androidx.navigation.Navigator
    public void popBackStack(NavBackStackEntry popUpTo, boolean savedState) {
        Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
        if (this.fragmentManager.isStateSaved()) {
            Log.i(TAG, "Ignoring popBackStack() call: FragmentManager has already saved its state");
            return;
        }
        List beforePopList = getState().getBackStack().getValue();
        int popUpToIndex = beforePopList.indexOf(popUpTo);
        Iterable poppedList = beforePopList.subList(popUpToIndex, beforePopList.size());
        NavBackStackEntry initialEntry = (NavBackStackEntry) CollectionsKt.first(beforePopList);
        if (savedState) {
            for (NavBackStackEntry entry : CollectionsKt.reversed(poppedList)) {
                if (Intrinsics.areEqual(entry, initialEntry)) {
                    Log.i(TAG, "FragmentManager cannot save the state of the initial destination " + entry);
                } else {
                    this.fragmentManager.saveBackStack(entry.getId());
                    this.savedIds.add(entry.getId());
                }
            }
        } else {
            this.fragmentManager.popBackStack(popUpTo.getId(), 1);
        }
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(TAG, "Calling popWithTransition via popBackStack() on entry " + popUpTo + " with savedState " + savedState);
        }
        NavBackStackEntry incomingEntry = (NavBackStackEntry) CollectionsKt.getOrNull(beforePopList, popUpToIndex - 1);
        if (incomingEntry != null) {
            addPendingOps$default(this, incomingEntry.getId(), false, false, 6, null);
        }
        Iterable $this$filter$iv = poppedList;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            NavBackStackEntry entry2 = (NavBackStackEntry) element$iv$iv;
            Iterable $this$filter$iv2 = $this$filter$iv;
            if (SequencesKt.contains(SequencesKt.map(CollectionsKt.asSequence(this.pendingOps), new Function1<Pair<? extends String, ? extends Boolean>, String>() { // from class: androidx.navigation.fragment.FragmentNavigator$popBackStack$1$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ String invoke(Pair<? extends String, ? extends Boolean> pair) {
                    return invoke2((Pair<String, Boolean>) pair);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final String invoke2(Pair<String, Boolean> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it.getFirst();
                }
            }), entry2.getId()) || !Intrinsics.areEqual(entry2.getId(), initialEntry.getId())) {
                destination$iv$iv.add(element$iv$iv);
            }
            $this$filter$iv = $this$filter$iv2;
        }
        Iterable $this$forEach$iv = (List) destination$iv$iv;
        for (Object element$iv : $this$forEach$iv) {
            addPendingOps$default(this, ((NavBackStackEntry) element$iv).getId(), true, false, 4, null);
        }
        getState().popWithTransition(popUpTo, savedState);
    }

    @Override // androidx.navigation.Navigator
    public Destination createDestination() {
        return new Destination(this);
    }

    @Deprecated(message = "Set a custom {@link androidx.fragment.app.FragmentFactory} via\n      {@link FragmentManager#setFragmentFactory(FragmentFactory)} to control\n      instantiation of Fragments.")
    public Fragment instantiateFragment(Context context, FragmentManager fragmentManager, String className, Bundle args) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        Intrinsics.checkNotNullParameter(className, "className");
        Fragment instantiate = fragmentManager.getFragmentFactory().instantiate(context.getClassLoader(), className);
        Intrinsics.checkNotNullExpressionValue(instantiate, "fragmentManager.fragment…t.classLoader, className)");
        return instantiate;
    }

    @Override // androidx.navigation.Navigator
    public void navigate(List<NavBackStackEntry> entries, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        Intrinsics.checkNotNullParameter(entries, "entries");
        if (this.fragmentManager.isStateSaved()) {
            Log.i(TAG, "Ignoring navigate() call: FragmentManager has already saved its state");
            return;
        }
        for (NavBackStackEntry entry : entries) {
            navigate(entry, navOptions, navigatorExtras);
        }
    }

    private final void navigate(NavBackStackEntry entry, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        boolean initialNavigation = getState().getBackStack().getValue().isEmpty();
        boolean restoreState = navOptions != null && !initialNavigation && navOptions.getRestoreState() && this.savedIds.remove(entry.getId());
        if (restoreState) {
            this.fragmentManager.restoreBackStack(entry.getId());
            getState().pushWithTransition(entry);
            return;
        }
        FragmentTransaction ft = createFragmentTransaction(entry, navOptions);
        if (!initialNavigation) {
            NavBackStackEntry outgoingEntry = (NavBackStackEntry) CollectionsKt.lastOrNull((List) getState().getBackStack().getValue());
            if (outgoingEntry != null) {
                addPendingOps$default(this, outgoingEntry.getId(), false, false, 6, null);
            }
            addPendingOps$default(this, entry.getId(), false, false, 6, null);
            ft.addToBackStack(entry.getId());
        }
        if (navigatorExtras instanceof Extras) {
            for (Map.Entry<View, String> entry2 : ((Extras) navigatorExtras).getSharedElements().entrySet()) {
                View key = entry2.getKey();
                String value = entry2.getValue();
                ft.addSharedElement(key, value);
            }
        }
        ft.commit();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(TAG, "Calling pushWithTransition via navigate() on entry " + entry);
        }
        getState().pushWithTransition(entry);
    }

    @Override // androidx.navigation.Navigator
    public void onLaunchSingleTop(NavBackStackEntry backStackEntry) {
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        if (this.fragmentManager.isStateSaved()) {
            Log.i(TAG, "Ignoring onLaunchSingleTop() call: FragmentManager has already saved its state");
            return;
        }
        FragmentTransaction ft = createFragmentTransaction(backStackEntry, null);
        List backstack = getState().getBackStack().getValue();
        if (backstack.size() > 1) {
            NavBackStackEntry incomingEntry = (NavBackStackEntry) CollectionsKt.getOrNull(backstack, CollectionsKt.getLastIndex(backstack) - 1);
            if (incomingEntry != null) {
                addPendingOps$default(this, incomingEntry.getId(), false, false, 6, null);
            }
            addPendingOps$default(this, backStackEntry.getId(), true, false, 4, null);
            this.fragmentManager.popBackStack(backStackEntry.getId(), 1);
            addPendingOps$default(this, backStackEntry.getId(), false, false, 2, null);
            ft.addToBackStack(backStackEntry.getId());
        }
        ft.commit();
        getState().onLaunchSingleTop(backStackEntry);
    }

    private final FragmentTransaction createFragmentTransaction(NavBackStackEntry entry, NavOptions navOptions) {
        NavDestination destination = entry.getDestination();
        Intrinsics.checkNotNull(destination, "null cannot be cast to non-null type androidx.navigation.fragment.FragmentNavigator.Destination");
        Destination destination2 = (Destination) destination;
        Bundle args = entry.getArguments();
        String className = destination2.getClassName();
        if (className.charAt(0) == '.') {
            className = this.context.getPackageName() + className;
        }
        Fragment frag = this.fragmentManager.getFragmentFactory().instantiate(this.context.getClassLoader(), className);
        Intrinsics.checkNotNullExpressionValue(frag, "fragmentManager.fragment…t.classLoader, className)");
        frag.setArguments(args);
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(ft, "fragmentManager.beginTransaction()");
        int enterAnim = navOptions != null ? navOptions.getEnterAnim() : -1;
        int exitAnim = navOptions != null ? navOptions.getExitAnim() : -1;
        int popEnterAnim = navOptions != null ? navOptions.getPopEnterAnim() : -1;
        int popExitAnim = navOptions != null ? navOptions.getPopExitAnim() : -1;
        if (enterAnim != -1 || exitAnim != -1 || popEnterAnim != -1 || popExitAnim != -1) {
            ft.setCustomAnimations(enterAnim != -1 ? enterAnim : 0, exitAnim != -1 ? exitAnim : 0, popEnterAnim != -1 ? popEnterAnim : 0, popExitAnim != -1 ? popExitAnim : 0);
        }
        ft.replace(this.containerId, frag, entry.getId());
        ft.setPrimaryNavigationFragment(frag);
        ft.setReorderingAllowed(true);
        return ft;
    }

    @Override // androidx.navigation.Navigator
    public Bundle onSaveState() {
        if (this.savedIds.isEmpty()) {
            return null;
        }
        return BundleKt.bundleOf(TuplesKt.to(KEY_SAVED_IDS, new ArrayList(this.savedIds)));
    }

    @Override // androidx.navigation.Navigator
    public void onRestoreState(Bundle savedState) {
        Intrinsics.checkNotNullParameter(savedState, "savedState");
        ArrayList savedIds = savedState.getStringArrayList(KEY_SAVED_IDS);
        if (savedIds != null) {
            this.savedIds.clear();
            CollectionsKt.addAll(this.savedIds, savedIds);
        }
    }

    /* compiled from: FragmentNavigator.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0015\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0017J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\tJ\b\u0010\u001a\u001a\u00020\tH\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u001b"}, d2 = {"Landroidx/navigation/fragment/FragmentNavigator$Destination;", "Landroidx/navigation/NavDestination;", "navigatorProvider", "Landroidx/navigation/NavigatorProvider;", "(Landroidx/navigation/NavigatorProvider;)V", "fragmentNavigator", "Landroidx/navigation/Navigator;", "(Landroidx/navigation/Navigator;)V", "_className", "", "className", "getClassName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "onInflate", "", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "setClassName", "toString", "navigation-fragment_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static class Destination extends NavDestination {
        private String _className;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Destination(Navigator<? extends Destination> fragmentNavigator) {
            super(fragmentNavigator);
            Intrinsics.checkNotNullParameter(fragmentNavigator, "fragmentNavigator");
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Destination(NavigatorProvider navigatorProvider) {
            this((Navigator<? extends Destination>) navigatorProvider.getNavigator(FragmentNavigator.class));
            Intrinsics.checkNotNullParameter(navigatorProvider, "navigatorProvider");
        }

        @Override // androidx.navigation.NavDestination
        public void onInflate(Context context, AttributeSet attrs) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(attrs, "attrs");
            super.onInflate(context, attrs);
            TypedArray $this$use$iv = context.getResources().obtainAttributes(attrs, R.styleable.FragmentNavigator);
            Intrinsics.checkNotNullExpressionValue($this$use$iv, "context.resources.obtain…leable.FragmentNavigator)");
            String className = $this$use$iv.getString(R.styleable.FragmentNavigator_android_name);
            if (className != null) {
                setClassName(className);
            }
            Unit unit = Unit.INSTANCE;
            $this$use$iv.recycle();
        }

        public final Destination setClassName(String className) {
            Intrinsics.checkNotNullParameter(className, "className");
            this._className = className;
            return this;
        }

        public final String getClassName() {
            if (this._className == null) {
                throw new IllegalStateException("Fragment class was not set".toString());
            }
            String str = this._className;
            Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
            return str;
        }

        @Override // androidx.navigation.NavDestination
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(super.toString());
            sb.append(" class=");
            if (this._className == null) {
                sb.append("null");
            } else {
                sb.append(this._className);
            }
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "sb.toString()");
            return sb2;
        }

        @Override // androidx.navigation.NavDestination
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || !(other instanceof Destination)) {
                return false;
            }
            if (super.equals(other) && Intrinsics.areEqual(this._className, ((Destination) other)._className)) {
                return true;
            }
            return false;
        }

        @Override // androidx.navigation.NavDestination
        public int hashCode() {
            int result = super.hashCode();
            int i = result * 31;
            String str = this._className;
            int result2 = i + (str != null ? str.hashCode() : 0);
            return result2;
        }
    }

    /* compiled from: FragmentNavigator.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\fB\u001b\b\u0000\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\u0006R*\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\bj\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00038F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Landroidx/navigation/fragment/FragmentNavigator$Extras;", "Landroidx/navigation/Navigator$Extras;", "sharedElements", "", "Landroid/view/View;", "", "(Ljava/util/Map;)V", "_sharedElements", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "getSharedElements", "()Ljava/util/Map;", "Builder", "navigation-fragment_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Extras implements Navigator.Extras {
        private final LinkedHashMap<View, String> _sharedElements;

        public Extras(Map<View, String> sharedElements) {
            Intrinsics.checkNotNullParameter(sharedElements, "sharedElements");
            this._sharedElements = new LinkedHashMap<>();
            this._sharedElements.putAll(sharedElements);
        }

        public final Map<View, String> getSharedElements() {
            return MapsKt.toMap(this._sharedElements);
        }

        /* compiled from: FragmentNavigator.kt */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0006J\u001a\u0010\u000b\u001a\u00020\u00002\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\rJ\u0006\u0010\u000e\u001a\u00020\u000fR*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/navigation/fragment/FragmentNavigator$Extras$Builder;", "", "()V", "_sharedElements", "Ljava/util/LinkedHashMap;", "Landroid/view/View;", "", "Lkotlin/collections/LinkedHashMap;", "addSharedElement", "sharedElement", AppMeasurementSdk.ConditionalUserProperty.NAME, "addSharedElements", "sharedElements", "", "build", "Landroidx/navigation/fragment/FragmentNavigator$Extras;", "navigation-fragment_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Builder {
            private final LinkedHashMap<View, String> _sharedElements = new LinkedHashMap<>();

            public final Builder addSharedElements(Map<View, String> sharedElements) {
                Intrinsics.checkNotNullParameter(sharedElements, "sharedElements");
                for (Map.Entry<View, String> entry : sharedElements.entrySet()) {
                    View view = entry.getKey();
                    String name = entry.getValue();
                    addSharedElement(view, name);
                }
                return this;
            }

            public final Builder addSharedElement(View sharedElement, String name) {
                Intrinsics.checkNotNullParameter(sharedElement, "sharedElement");
                Intrinsics.checkNotNullParameter(name, "name");
                this._sharedElements.put(sharedElement, name);
                return this;
            }

            public final Extras build() {
                return new Extras(this._sharedElements);
            }
        }
    }

    /* compiled from: FragmentNavigator.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/navigation/fragment/FragmentNavigator$Companion;", "", "()V", "KEY_SAVED_IDS", "", "TAG", "navigation-fragment_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* compiled from: FragmentNavigator.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\u0006H\u0014R&\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Landroidx/navigation/fragment/FragmentNavigator$ClearEntryStateViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "completeTransition", "Ljava/lang/ref/WeakReference;", "Lkotlin/Function0;", "", "getCompleteTransition", "()Ljava/lang/ref/WeakReference;", "setCompleteTransition", "(Ljava/lang/ref/WeakReference;)V", "onCleared", "navigation-fragment_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class ClearEntryStateViewModel extends ViewModel {
        public WeakReference<Function0<Unit>> completeTransition;

        public final WeakReference<Function0<Unit>> getCompleteTransition() {
            WeakReference<Function0<Unit>> weakReference = this.completeTransition;
            if (weakReference != null) {
                return weakReference;
            }
            Intrinsics.throwUninitializedPropertyAccessException("completeTransition");
            return null;
        }

        public final void setCompleteTransition(WeakReference<Function0<Unit>> weakReference) {
            Intrinsics.checkNotNullParameter(weakReference, "<set-?>");
            this.completeTransition = weakReference;
        }

        @Override // androidx.lifecycle.ViewModel
        protected void onCleared() {
            super.onCleared();
            Function0<Unit> function0 = getCompleteTransition().get();
            if (function0 != null) {
                function0.invoke();
            }
        }
    }

    static /* synthetic */ void addPendingOps$default(FragmentNavigator fragmentNavigator, String str, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addPendingOps");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        fragmentNavigator.addPendingOps(str, z, z2);
    }

    private final void addPendingOps(final String id, boolean isPop, boolean deduplicate) {
        if (deduplicate) {
            CollectionsKt.removeAll((List) this.pendingOps, (Function1) new Function1<Pair<? extends String, ? extends Boolean>, Boolean>() { // from class: androidx.navigation.fragment.FragmentNavigator$addPendingOps$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final Boolean invoke2(Pair<String, Boolean> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Boolean.valueOf(Intrinsics.areEqual(it.getFirst(), id));
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Pair<? extends String, ? extends Boolean> pair) {
                    return invoke2((Pair<String, Boolean>) pair);
                }
            });
        }
        this.pendingOps.add(TuplesKt.to(id, Boolean.valueOf(isPop)));
    }
}
