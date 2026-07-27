package androidx.navigation.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.FloatingWindow;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.NavigatorState;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: DialogFragmentNavigator.kt */
@Navigator.Name("dialog")
@Metadata(d1 = {"\u0000w\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u000f\b\u0007\u0018\u0000 -2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002-.B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0017\u001a\u00020\u0002H\u0016J\u0010\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u000bH\u0002J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0019\u001a\u00020\u000bH\u0002J*\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0010\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u000bH\u0016J\u0018\u0010&\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020)H\u0016J \u0010*\u001a\u00020\u001b2\u0006\u0010+\u001a\u00020,2\u0006\u0010'\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020)H\u0002R \u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Landroidx/navigation/fragment/DialogFragmentNavigator;", "Landroidx/navigation/Navigator;", "Landroidx/navigation/fragment/DialogFragmentNavigator$Destination;", "context", "Landroid/content/Context;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "(Landroid/content/Context;Landroidx/fragment/app/FragmentManager;)V", "backStack", "Lkotlinx/coroutines/flow/StateFlow;", "", "Landroidx/navigation/NavBackStackEntry;", "getBackStack$navigation_fragment_release", "()Lkotlinx/coroutines/flow/StateFlow;", "observer", "androidx/navigation/fragment/DialogFragmentNavigator$observer$1", "Landroidx/navigation/fragment/DialogFragmentNavigator$observer$1;", "restoredTagsAwaitingAttach", "", "", "transitioningFragments", "", "Landroidx/fragment/app/DialogFragment;", "createDestination", "createDialogFragment", "entry", "navigate", "", "entries", "navOptions", "Landroidx/navigation/NavOptions;", "navigatorExtras", "Landroidx/navigation/Navigator$Extras;", "onAttach", "state", "Landroidx/navigation/NavigatorState;", "onLaunchSingleTop", "backStackEntry", "popBackStack", "popUpTo", "savedState", "", "popWithTransition", "popUpToIndex", "", "Companion", "Destination", "navigation-fragment_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class DialogFragmentNavigator extends Navigator<Destination> {
    private static final Companion Companion = new Companion(null);
    private static final String TAG = "DialogFragmentNavigator";
    private final Context context;
    private final FragmentManager fragmentManager;
    private final DialogFragmentNavigator$observer$1 observer;
    private final Set<String> restoredTagsAwaitingAttach;
    private final Map<String, DialogFragment> transitioningFragments;

    /* JADX WARN: Type inference failed for: r0v4, types: [androidx.navigation.fragment.DialogFragmentNavigator$observer$1] */
    public DialogFragmentNavigator(Context context, FragmentManager fragmentManager) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.restoredTagsAwaitingAttach = new LinkedHashSet();
        this.observer = new LifecycleEventObserver() { // from class: androidx.navigation.fragment.DialogFragmentNavigator$observer$1

            /* compiled from: DialogFragmentNavigator.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[Lifecycle.Event.values().length];
                    try {
                        iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[Lifecycle.Event.ON_RESUME.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[Lifecycle.Event.ON_STOP.ordinal()] = 3;
                    } catch (NoSuchFieldError e3) {
                    }
                    try {
                        iArr[Lifecycle.Event.ON_DESTROY.ordinal()] = 4;
                    } catch (NoSuchFieldError e4) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
                NavigatorState state;
                NavigatorState state2;
                NavigatorState state3;
                NavigatorState state4;
                int i;
                NavigatorState state5;
                NavigatorState state6;
                Intrinsics.checkNotNullParameter(source, "source");
                Intrinsics.checkNotNullParameter(event, "event");
                boolean dialogOnBackStack = false;
                switch (WhenMappings.$EnumSwitchMapping$0[event.ordinal()]) {
                    case 1:
                        DialogFragment dialogFragment = (DialogFragment) source;
                        state = DialogFragmentNavigator.this.getState();
                        Iterable $this$any$iv = state.getBackStack().getValue();
                        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                            Iterator it = $this$any$iv.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    NavBackStackEntry it2 = (NavBackStackEntry) it.next();
                                    if (Intrinsics.areEqual(it2.getId(), dialogFragment.getTag())) {
                                        dialogOnBackStack = true;
                                    }
                                }
                            }
                        }
                        if (!dialogOnBackStack) {
                            dialogFragment.dismiss();
                            break;
                        }
                        break;
                    case 2:
                        DialogFragment dialogFragment2 = (DialogFragment) source;
                        state2 = DialogFragmentNavigator.this.getState();
                        Iterable $this$lastOrNull$iv = state2.getTransitionsInProgress().getValue();
                        Object last$iv = null;
                        for (Object element$iv : $this$lastOrNull$iv) {
                            if (Intrinsics.areEqual(((NavBackStackEntry) element$iv).getId(), dialogFragment2.getTag())) {
                                last$iv = element$iv;
                            }
                        }
                        NavBackStackEntry entry = (NavBackStackEntry) last$iv;
                        if (entry != null) {
                            state3 = DialogFragmentNavigator.this.getState();
                            state3.markTransitionComplete(entry);
                            break;
                        }
                        break;
                    case 3:
                        DialogFragment dialogFragment3 = (DialogFragment) source;
                        if (!dialogFragment3.requireDialog().isShowing()) {
                            state4 = DialogFragmentNavigator.this.getState();
                            List beforePopList = state4.getBackStack().getValue();
                            ListIterator iterator$iv = beforePopList.listIterator(beforePopList.size());
                            while (true) {
                                if (iterator$iv.hasPrevious()) {
                                    NavBackStackEntry it3 = iterator$iv.previous();
                                    if (Intrinsics.areEqual(it3.getId(), dialogFragment3.getTag())) {
                                        i = iterator$iv.nextIndex();
                                    }
                                } else {
                                    i = -1;
                                }
                            }
                            int popIndex = i;
                            NavBackStackEntry poppedEntry = (NavBackStackEntry) CollectionsKt.getOrNull(beforePopList, popIndex);
                            if (!Intrinsics.areEqual(CollectionsKt.lastOrNull(beforePopList), poppedEntry)) {
                                Log.i("DialogFragmentNavigator", "Dialog " + dialogFragment3 + " was dismissed while it was not the top of the back stack, popping all dialogs above this dismissed dialog");
                            }
                            if (poppedEntry != null) {
                                DialogFragmentNavigator.this.popWithTransition(popIndex, poppedEntry, false);
                                break;
                            }
                        }
                        break;
                    case 4:
                        DialogFragment dialogFragment4 = (DialogFragment) source;
                        state5 = DialogFragmentNavigator.this.getState();
                        Iterable $this$lastOrNull$iv2 = state5.getTransitionsInProgress().getValue();
                        Object last$iv2 = null;
                        for (Object element$iv2 : $this$lastOrNull$iv2) {
                            if (Intrinsics.areEqual(((NavBackStackEntry) element$iv2).getId(), dialogFragment4.getTag())) {
                                last$iv2 = element$iv2;
                            }
                        }
                        NavBackStackEntry entry2 = (NavBackStackEntry) last$iv2;
                        if (entry2 != null) {
                            state6 = DialogFragmentNavigator.this.getState();
                            state6.markTransitionComplete(entry2);
                        }
                        dialogFragment4.getLifecycle().removeObserver(this);
                        break;
                }
            }
        };
        this.transitioningFragments = new LinkedHashMap();
    }

    public final StateFlow<List<NavBackStackEntry>> getBackStack$navigation_fragment_release() {
        return getState().getBackStack();
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
        List poppedList = beforePopList.subList(popUpToIndex, beforePopList.size());
        for (NavBackStackEntry entry : CollectionsKt.reversed(poppedList)) {
            Fragment existingFragment = this.fragmentManager.findFragmentByTag(entry.getId());
            if (existingFragment != null) {
                ((DialogFragment) existingFragment).dismiss();
            }
        }
        popWithTransition(popUpToIndex, popUpTo, savedState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void popWithTransition(int popUpToIndex, NavBackStackEntry popUpTo, boolean savedState) {
        NavBackStackEntry incomingEntry = (NavBackStackEntry) CollectionsKt.getOrNull(getState().getBackStack().getValue(), popUpToIndex - 1);
        boolean incomingEntryTransitioning = CollectionsKt.contains(getState().getTransitionsInProgress().getValue(), incomingEntry);
        getState().popWithTransition(popUpTo, savedState);
        if (incomingEntry != null && !incomingEntryTransitioning) {
            getState().markTransitionComplete(incomingEntry);
        }
    }

    @Override // androidx.navigation.Navigator
    public Destination createDestination() {
        return new Destination(this);
    }

    @Override // androidx.navigation.Navigator
    public void navigate(List<NavBackStackEntry> entries, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        Intrinsics.checkNotNullParameter(entries, "entries");
        if (this.fragmentManager.isStateSaved()) {
            Log.i(TAG, "Ignoring navigate() call: FragmentManager has already saved its state");
            return;
        }
        for (NavBackStackEntry entry : entries) {
            navigate(entry);
        }
    }

    private final void navigate(NavBackStackEntry entry) {
        DialogFragment dialogFragment = createDialogFragment(entry);
        dialogFragment.show(this.fragmentManager, entry.getId());
        NavBackStackEntry outGoingEntry = (NavBackStackEntry) CollectionsKt.lastOrNull((List) getState().getBackStack().getValue());
        boolean outGoingEntryTransitioning = CollectionsKt.contains(getState().getTransitionsInProgress().getValue(), outGoingEntry);
        getState().pushWithTransition(entry);
        if (outGoingEntry != null && !outGoingEntryTransitioning) {
            getState().markTransitionComplete(outGoingEntry);
        }
    }

    @Override // androidx.navigation.Navigator
    public void onLaunchSingleTop(NavBackStackEntry backStackEntry) {
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        if (this.fragmentManager.isStateSaved()) {
            Log.i(TAG, "Ignoring onLaunchSingleTop() call: FragmentManager has already saved its state");
            return;
        }
        DialogFragment oldFragment = this.transitioningFragments.get(backStackEntry.getId());
        if (oldFragment == null) {
            Fragment findFragmentByTag = this.fragmentManager.findFragmentByTag(backStackEntry.getId());
            oldFragment = findFragmentByTag instanceof DialogFragment ? (DialogFragment) findFragmentByTag : null;
        }
        if (oldFragment != null) {
            oldFragment.getLifecycle().removeObserver(this.observer);
            oldFragment.dismiss();
        }
        DialogFragment newFragment = createDialogFragment(backStackEntry);
        newFragment.show(this.fragmentManager, backStackEntry.getId());
        getState().onLaunchSingleTopWithTransition(backStackEntry);
    }

    private final DialogFragment createDialogFragment(NavBackStackEntry entry) {
        NavDestination destination = entry.getDestination();
        Intrinsics.checkNotNull(destination, "null cannot be cast to non-null type androidx.navigation.fragment.DialogFragmentNavigator.Destination");
        Destination destination2 = (Destination) destination;
        String className = destination2.getClassName();
        if (className.charAt(0) == '.') {
            className = this.context.getPackageName() + className;
        }
        Fragment frag = this.fragmentManager.getFragmentFactory().instantiate(this.context.getClassLoader(), className);
        Intrinsics.checkNotNullExpressionValue(frag, "fragmentManager.fragment…ader, className\n        )");
        if (!DialogFragment.class.isAssignableFrom(frag.getClass())) {
            throw new IllegalArgumentException(("Dialog destination " + destination2.getClassName() + " is not an instance of DialogFragment").toString());
        }
        DialogFragment dialogFragment = (DialogFragment) frag;
        dialogFragment.setArguments(entry.getArguments());
        dialogFragment.getLifecycle().addObserver(this.observer);
        this.transitioningFragments.put(entry.getId(), dialogFragment);
        return dialogFragment;
    }

    @Override // androidx.navigation.Navigator
    public void onAttach(NavigatorState state) {
        Lifecycle lifecycle;
        Intrinsics.checkNotNullParameter(state, "state");
        super.onAttach(state);
        for (NavBackStackEntry entry : state.getBackStack().getValue()) {
            DialogFragment fragment = (DialogFragment) this.fragmentManager.findFragmentByTag(entry.getId());
            if (fragment == null || (lifecycle = fragment.getLifecycle()) == null) {
                this.restoredTagsAwaitingAttach.add(entry.getId());
            } else {
                lifecycle.addObserver(this.observer);
            }
        }
        this.fragmentManager.addFragmentOnAttachListener(new FragmentOnAttachListener() { // from class: androidx.navigation.fragment.DialogFragmentNavigator$$ExternalSyntheticLambda0
            @Override // androidx.fragment.app.FragmentOnAttachListener
            public final void onAttachFragment(FragmentManager fragmentManager, Fragment fragment2) {
                DialogFragmentNavigator.onAttach$lambda$1(DialogFragmentNavigator.this, fragmentManager, fragment2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onAttach$lambda$1(DialogFragmentNavigator this$0, FragmentManager fragmentManager, Fragment childFragment) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fragmentManager, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(childFragment, "childFragment");
        Set<String> set = this$0.restoredTagsAwaitingAttach;
        boolean needToAddObserver = TypeIntrinsics.asMutableCollection(set).remove(childFragment.getTag());
        if (needToAddObserver) {
            childFragment.getLifecycle().addObserver(this$0.observer);
        }
        Map<String, DialogFragment> map = this$0.transitioningFragments;
        TypeIntrinsics.asMutableMap(map).remove(childFragment.getTag());
    }

    /* compiled from: DialogFragmentNavigator.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0015\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u0007¢\u0006\u0002\u0010\bJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0017J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u001b"}, d2 = {"Landroidx/navigation/fragment/DialogFragmentNavigator$Destination;", "Landroidx/navigation/NavDestination;", "Landroidx/navigation/FloatingWindow;", "navigatorProvider", "Landroidx/navigation/NavigatorProvider;", "(Landroidx/navigation/NavigatorProvider;)V", "fragmentNavigator", "Landroidx/navigation/Navigator;", "(Landroidx/navigation/Navigator;)V", "_className", "", "className", "getClassName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "onInflate", "", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "setClassName", "navigation-fragment_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static class Destination extends NavDestination implements FloatingWindow {
        private String _className;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Destination(Navigator<? extends Destination> fragmentNavigator) {
            super(fragmentNavigator);
            Intrinsics.checkNotNullParameter(fragmentNavigator, "fragmentNavigator");
        }

        public final String getClassName() {
            if (this._className == null) {
                throw new IllegalStateException("DialogFragment class was not set".toString());
            }
            String str = this._className;
            Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
            return str;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Destination(NavigatorProvider navigatorProvider) {
            this((Navigator<? extends Destination>) navigatorProvider.getNavigator(DialogFragmentNavigator.class));
            Intrinsics.checkNotNullParameter(navigatorProvider, "navigatorProvider");
        }

        @Override // androidx.navigation.NavDestination
        public void onInflate(Context context, AttributeSet attrs) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(attrs, "attrs");
            super.onInflate(context, attrs);
            TypedArray $this$use$iv = context.getResources().obtainAttributes(attrs, R.styleable.DialogFragmentNavigator);
            Intrinsics.checkNotNullExpressionValue($this$use$iv, "context.resources.obtain…ntNavigator\n            )");
            String className = $this$use$iv.getString(R.styleable.DialogFragmentNavigator_android_name);
            Destination className2 = className != null ? setClassName(className) : null;
            $this$use$iv.recycle();
        }

        public final Destination setClassName(String className) {
            Intrinsics.checkNotNullParameter(className, "className");
            this._className = className;
            return this;
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

    /* compiled from: DialogFragmentNavigator.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/navigation/fragment/DialogFragmentNavigator$Companion;", "", "()V", "TAG", "", "navigation-fragment_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
