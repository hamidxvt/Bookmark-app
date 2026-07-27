package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.activity.BackEventCompat;
import androidx.collection.ArrayMap;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.DefaultSpecialEffectsController;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

/* compiled from: DefaultSpecialEffectsController.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001:\b\u001f !\"#$%&B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0003J\u001e\u0010\n\u001a\u00020\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J2\u0010\u000f\u001a\u00020\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\b2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\fH\u0002J$\u0010\u0014\u001a\u00020\u00062\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u00162\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\u0016\u0010\u001a\u001a\u00020\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\bH\u0002J&\u0010\u001b\u001a\u00020\u0006*\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00170\u001eH\u0002¨\u0006'"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController;", "Landroidx/fragment/app/SpecialEffectsController;", "container", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "collectAnimEffects", "", "animationInfos", "", "Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "collectEffects", "operations", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "isPop", "", "createTransitionEffect", "transitionInfos", "Landroidx/fragment/app/DefaultSpecialEffectsController$TransitionInfo;", "firstOut", "lastIn", "findNamedViews", "namedViews", "", "", "Landroid/view/View;", "view", "syncAnimations", "retainMatchingViews", "Landroidx/collection/ArrayMap;", "names", "", "AnimationEffect", "AnimationInfo", "AnimatorEffect", "Api24Impl", "Api26Impl", "SpecialEffectsInfo", "TransitionEffect", "TransitionInfo", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class DefaultSpecialEffectsController extends SpecialEffectsController {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultSpecialEffectsController(ViewGroup container) {
        super(container);
        Intrinsics.checkNotNullParameter(container, "container");
    }

    @Override // androidx.fragment.app.SpecialEffectsController
    public void collectEffects(List<? extends SpecialEffectsController.Operation> operations, boolean isPop) {
        Object element$iv;
        Object element$iv2;
        Intrinsics.checkNotNullParameter(operations, "operations");
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Collecting Effects");
        }
        List<? extends SpecialEffectsController.Operation> $this$firstOrNull$iv = operations;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                SpecialEffectsController.Operation operation = (SpecialEffectsController.Operation) element$iv;
                SpecialEffectsController.Operation.State.Companion companion = SpecialEffectsController.Operation.State.INSTANCE;
                View view = operation.getFragment().mView;
                Intrinsics.checkNotNullExpressionValue(view, "operation.fragment.mView");
                SpecialEffectsController.Operation.State currentState = companion.asOperationState(view);
                if (currentState == SpecialEffectsController.Operation.State.VISIBLE && operation.getFinalState() != SpecialEffectsController.Operation.State.VISIBLE) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        SpecialEffectsController.Operation firstOut = (SpecialEffectsController.Operation) element$iv;
        ListIterator iterator$iv = operations.listIterator(operations.size());
        while (true) {
            if (iterator$iv.hasPrevious()) {
                element$iv2 = iterator$iv.previous();
                SpecialEffectsController.Operation operation2 = (SpecialEffectsController.Operation) element$iv2;
                SpecialEffectsController.Operation.State.Companion companion2 = SpecialEffectsController.Operation.State.INSTANCE;
                View view2 = operation2.getFragment().mView;
                Intrinsics.checkNotNullExpressionValue(view2, "operation.fragment.mView");
                SpecialEffectsController.Operation.State currentState2 = companion2.asOperationState(view2);
                if (currentState2 != SpecialEffectsController.Operation.State.VISIBLE && operation2.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                    break;
                }
            } else {
                element$iv2 = null;
                break;
            }
        }
        SpecialEffectsController.Operation lastIn = (SpecialEffectsController.Operation) element$iv2;
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Executing operations from " + firstOut + " to " + lastIn);
        }
        List animations = new ArrayList();
        List transitions = new ArrayList();
        syncAnimations(operations);
        Iterator<? extends SpecialEffectsController.Operation> it2 = operations.iterator();
        while (it2.hasNext()) {
            final SpecialEffectsController.Operation operation3 = it2.next();
            animations.add(new AnimationInfo(operation3, isPop));
            transitions.add(new TransitionInfo(operation3, isPop, !isPop ? operation3 != lastIn : operation3 != firstOut));
            operation3.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultSpecialEffectsController.collectEffects$lambda$2(DefaultSpecialEffectsController.this, operation3);
                }
            });
        }
        createTransitionEffect(transitions, isPop, firstOut, lastIn);
        collectAnimEffects(animations);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void collectEffects$lambda$2(DefaultSpecialEffectsController this$0, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        this$0.applyContainerChangesToOperation$fragment_release(operation);
    }

    private final void syncAnimations(List<? extends SpecialEffectsController.Operation> operations) {
        Fragment lastOpFragment = ((SpecialEffectsController.Operation) CollectionsKt.last((List) operations)).getFragment();
        for (SpecialEffectsController.Operation operation : operations) {
            operation.getFragment().mAnimationInfo.mEnterAnim = lastOpFragment.mAnimationInfo.mEnterAnim;
            operation.getFragment().mAnimationInfo.mExitAnim = lastOpFragment.mAnimationInfo.mExitAnim;
            operation.getFragment().mAnimationInfo.mPopEnterAnim = lastOpFragment.mAnimationInfo.mPopEnterAnim;
            operation.getFragment().mAnimationInfo.mPopExitAnim = lastOpFragment.mAnimationInfo.mPopExitAnim;
        }
    }

    private final void collectAnimEffects(List<AnimationInfo> animationInfos) {
        List<AnimationInfo> animationsToRun = new ArrayList();
        List<AnimationInfo> $this$flatMap$iv = animationInfos;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            AnimationInfo it = (AnimationInfo) element$iv$iv;
            Iterable list$iv$iv = it.getOperation().getEffects$fragment_release();
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        boolean startedAnyTransition = !((List) destination$iv$iv).isEmpty();
        boolean startedAnyAnimator = false;
        for (AnimationInfo animatorInfo : animationInfos) {
            Context context = getContainer().getContext();
            SpecialEffectsController.Operation operation = animatorInfo.getOperation();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            FragmentAnim.AnimationOrAnimator anim = animatorInfo.getAnimation(context);
            if (anim != null) {
                AnimatorSet animator = anim.animator;
                if (animator == null) {
                    animationsToRun.add(animatorInfo);
                } else {
                    Fragment fragment = operation.getFragment();
                    boolean startedTransition = !operation.getEffects$fragment_release().isEmpty();
                    if (startedTransition) {
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v(FragmentManager.TAG, "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                        }
                    } else {
                        startedAnyAnimator = true;
                        boolean isHideOperation = operation.getFinalState() == SpecialEffectsController.Operation.State.GONE;
                        if (isHideOperation) {
                            operation.setAwaitingContainerChanges(false);
                        }
                        operation.addEffect(new AnimatorEffect(animatorInfo));
                    }
                }
            }
        }
        for (AnimationInfo animationInfo : animationsToRun) {
            SpecialEffectsController.Operation operation2 = animationInfo.getOperation();
            Fragment fragment2 = operation2.getFragment();
            if (startedAnyTransition) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
            } else if (startedAnyAnimator) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
            } else {
                operation2.addEffect(new AnimationEffect(animationInfo));
            }
        }
    }

    private final void createTransitionEffect(List<TransitionInfo> transitionInfos, boolean isPop, SpecialEffectsController.Operation firstOut, SpecialEffectsController.Operation lastIn) {
        boolean z;
        Iterator it;
        FragmentTransitionImpl transitionImpl;
        ArrayList sharedElementLastInViews;
        Object sharedElementTransition;
        ArrayList sharedElementFirstOutViews;
        ArrayList exitingNames;
        ArrayList enteringNames;
        SharedElementCallback exitingCallback;
        List<TransitionInfo> $this$filterNot$iv = transitionInfos;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterNot$iv) {
            if (!((TransitionInfo) element$iv$iv).isVisibilityUnchanged()) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv) {
            if (((TransitionInfo) element$iv$iv2).getHandlingImpl() != null) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        List filteredInfos = (List) destination$iv$iv2;
        List $this$fold$iv = filteredInfos;
        FragmentTransitionImpl fragmentTransitionImpl = null;
        for (Object element$iv : $this$fold$iv) {
            TransitionInfo transitionInfo = (TransitionInfo) element$iv;
            FragmentTransitionImpl chosenImpl = fragmentTransitionImpl;
            FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl();
            if (!(chosenImpl == null || handlingImpl == chosenImpl)) {
                throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.getOperation().getFragment() + " returned Transition " + transitionInfo.getTransition() + " which uses a different Transition type than other Fragments.").toString());
            }
            fragmentTransitionImpl = handlingImpl;
        }
        if (fragmentTransitionImpl == null) {
            return;
        }
        FragmentTransitionImpl transitionImpl2 = fragmentTransitionImpl;
        ArrayList sharedElementFirstOutViews2 = new ArrayList();
        ArrayList sharedElementLastInViews2 = new ArrayList();
        ArrayMap sharedElementNameMapping = new ArrayMap();
        ArrayList enteringNames2 = new ArrayList();
        ArrayList exitingNames2 = new ArrayList();
        ArrayMap firstOutViews = new ArrayMap();
        ArrayMap lastInViews = new ArrayMap();
        Iterator it2 = filteredInfos.iterator();
        Object sharedElementTransition2 = null;
        ArrayList enteringNames3 = enteringNames2;
        ArrayList firstOutTargetNames = exitingNames2;
        while (it2.hasNext()) {
            TransitionInfo transitionInfo2 = (TransitionInfo) it2.next();
            boolean hasSharedElementTransition = transitionInfo2.hasSharedElementTransition();
            if (!hasSharedElementTransition || firstOut == null || lastIn == null) {
                it2 = it2;
                transitionImpl2 = transitionImpl2;
            } else {
                Object sharedElementTransition3 = transitionImpl2.wrapTransitionInSet(transitionImpl2.cloneTransition(transitionInfo2.getSharedElementTransition()));
                ArrayList exitingNames3 = lastIn.getFragment().getSharedElementSourceNames();
                Intrinsics.checkNotNullExpressionValue(exitingNames3, "lastIn.fragment.sharedElementSourceNames");
                ArrayList firstOutSourceNames = firstOut.getFragment().getSharedElementSourceNames();
                Intrinsics.checkNotNullExpressionValue(firstOutSourceNames, "firstOut.fragment.sharedElementSourceNames");
                ArrayList sharedElementTargetNames = firstOut.getFragment().getSharedElementTargetNames();
                Intrinsics.checkNotNullExpressionValue(sharedElementTargetNames, "firstOut.fragment.sharedElementTargetNames");
                ArrayList firstOutTargetNames2 = sharedElementTargetNames;
                int index = 0;
                int size = firstOutTargetNames2.size();
                while (true) {
                    it = it2;
                    if (index >= size) {
                        break;
                    }
                    int nameIndex = exitingNames3.indexOf(firstOutTargetNames2.get(index));
                    ArrayList firstOutTargetNames3 = firstOutTargetNames2;
                    if (nameIndex != -1) {
                        exitingNames3.set(nameIndex, firstOutSourceNames.get(index));
                    }
                    index++;
                    firstOutTargetNames2 = firstOutTargetNames3;
                    it2 = it;
                }
                ArrayList enteringNames4 = lastIn.getFragment().getSharedElementTargetNames();
                Intrinsics.checkNotNullExpressionValue(enteringNames4, "lastIn.fragment.sharedElementTargetNames");
                Pair pair = !isPop ? TuplesKt.to(firstOut.getFragment().getExitTransitionCallback(), lastIn.getFragment().getEnterTransitionCallback()) : TuplesKt.to(firstOut.getFragment().getEnterTransitionCallback(), lastIn.getFragment().getExitTransitionCallback());
                SharedElementCallback exitingCallback2 = (SharedElementCallback) pair.component1();
                SharedElementCallback enteringCallback = (SharedElementCallback) pair.component2();
                int numSharedElements = exitingNames3.size();
                int i = 0;
                while (true) {
                    transitionImpl = transitionImpl2;
                    sharedElementLastInViews = sharedElementLastInViews2;
                    if (i >= numSharedElements) {
                        break;
                    }
                    int numSharedElements2 = numSharedElements;
                    String str = exitingNames3.get(i);
                    Intrinsics.checkNotNullExpressionValue(str, "exitingNames[i]");
                    String exitingName = str;
                    String str2 = enteringNames4.get(i);
                    Intrinsics.checkNotNullExpressionValue(str2, "enteringNames[i]");
                    String enteringName = str2;
                    sharedElementNameMapping.put(exitingName, enteringName);
                    i++;
                    numSharedElements = numSharedElements2;
                    transitionImpl2 = transitionImpl;
                    sharedElementLastInViews2 = sharedElementLastInViews;
                }
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, ">>> entering view names <<<");
                    Iterator<String> it3 = enteringNames4.iterator();
                    while (true) {
                        sharedElementFirstOutViews = sharedElementFirstOutViews2;
                        if (!it3.hasNext()) {
                            break;
                        }
                        Log.v(FragmentManager.TAG, "Name: " + it3.next());
                        sharedElementTransition3 = sharedElementTransition3;
                        sharedElementFirstOutViews2 = sharedElementFirstOutViews;
                        it3 = it3;
                    }
                    sharedElementTransition = sharedElementTransition3;
                    Log.v(FragmentManager.TAG, ">>> exiting view names <<<");
                    for (Iterator<String> it4 = exitingNames3.iterator(); it4.hasNext(); it4 = it4) {
                        Log.v(FragmentManager.TAG, "Name: " + it4.next());
                    }
                } else {
                    sharedElementTransition = sharedElementTransition3;
                    sharedElementFirstOutViews = sharedElementFirstOutViews2;
                }
                View view = firstOut.getFragment().mView;
                Intrinsics.checkNotNullExpressionValue(view, "firstOut.fragment.mView");
                findNamedViews(firstOutViews, view);
                firstOutViews.retainAll(exitingNames3);
                if (exitingCallback2 != null) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v(FragmentManager.TAG, "Executing exit callback for operation " + firstOut);
                    }
                    exitingCallback2.onMapSharedElements(exitingNames3, firstOutViews);
                    int i2 = -1;
                    int size2 = exitingNames3.size() - 1;
                    if (size2 >= 0) {
                        while (true) {
                            int i3 = size2;
                            size2 += i2;
                            Object obj = exitingNames3.get(i3);
                            Intrinsics.checkNotNullExpressionValue(obj, "exitingNames[i]");
                            String name = (String) obj;
                            View view2 = firstOutViews.get(name);
                            if (view2 == null) {
                                sharedElementNameMapping.remove(name);
                                exitingCallback = exitingCallback2;
                                exitingNames = exitingNames3;
                            } else {
                                exitingCallback = exitingCallback2;
                                if (Intrinsics.areEqual(name, ViewCompat.getTransitionName(view2))) {
                                    exitingNames = exitingNames3;
                                } else {
                                    String targetValue = (String) sharedElementNameMapping.remove(name);
                                    exitingNames = exitingNames3;
                                    sharedElementNameMapping.put(ViewCompat.getTransitionName(view2), targetValue);
                                }
                            }
                            if (size2 < 0) {
                                break;
                            }
                            exitingCallback2 = exitingCallback;
                            exitingNames3 = exitingNames;
                            i2 = -1;
                        }
                    } else {
                        exitingNames = exitingNames3;
                    }
                } else {
                    exitingNames = exitingNames3;
                    sharedElementNameMapping.retainAll(firstOutViews.keySet());
                }
                ArrayMap arrayMap = lastInViews;
                View view3 = lastIn.getFragment().mView;
                Intrinsics.checkNotNullExpressionValue(view3, "lastIn.fragment.mView");
                findNamedViews(arrayMap, view3);
                lastInViews.retainAll(enteringNames4);
                lastInViews.retainAll(sharedElementNameMapping.values());
                if (enteringCallback != null) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v(FragmentManager.TAG, "Executing enter callback for operation " + lastIn);
                    }
                    enteringCallback.onMapSharedElements(enteringNames4, lastInViews);
                    int i4 = -1;
                    int size3 = enteringNames4.size() - 1;
                    if (size3 >= 0) {
                        while (true) {
                            int i5 = size3;
                            size3 += i4;
                            String str3 = enteringNames4.get(i5);
                            Intrinsics.checkNotNullExpressionValue(str3, "enteringNames[i]");
                            String name2 = str3;
                            View view4 = lastInViews.get(name2);
                            if (view4 == null) {
                                String key = FragmentTransition.findKeyForValue(sharedElementNameMapping, name2);
                                if (key != null) {
                                    sharedElementNameMapping.remove(key);
                                    enteringNames = enteringNames4;
                                } else {
                                    enteringNames = enteringNames4;
                                }
                            } else if (Intrinsics.areEqual(name2, ViewCompat.getTransitionName(view4))) {
                                enteringNames = enteringNames4;
                            } else {
                                String key2 = FragmentTransition.findKeyForValue(sharedElementNameMapping, name2);
                                if (key2 != null) {
                                    enteringNames = enteringNames4;
                                    sharedElementNameMapping.put(key2, ViewCompat.getTransitionName(view4));
                                } else {
                                    enteringNames = enteringNames4;
                                }
                            }
                            if (size3 < 0) {
                                break;
                            }
                            enteringNames4 = enteringNames;
                            i4 = -1;
                        }
                    } else {
                        enteringNames = enteringNames4;
                    }
                } else {
                    enteringNames = enteringNames4;
                    FragmentTransition.retainValues(sharedElementNameMapping, lastInViews);
                }
                Set keySet = sharedElementNameMapping.keySet();
                Intrinsics.checkNotNullExpressionValue(keySet, "sharedElementNameMapping.keys");
                retainMatchingViews(firstOutViews, keySet);
                Collection<String> values = sharedElementNameMapping.values();
                Intrinsics.checkNotNullExpressionValue(values, "sharedElementNameMapping.values");
                retainMatchingViews(lastInViews, values);
                if (sharedElementNameMapping.isEmpty()) {
                    Log.i(FragmentManager.TAG, "Ignoring shared elements transition " + sharedElementTransition + " between " + firstOut + " and " + lastIn + " as there are no matching elements in both the entering and exiting fragment. In order to run a SharedElementTransition, both fragments involved must have the element.");
                    sharedElementTransition2 = null;
                    sharedElementFirstOutViews2 = sharedElementFirstOutViews;
                    sharedElementFirstOutViews2.clear();
                    sharedElementLastInViews2 = sharedElementLastInViews;
                    sharedElementLastInViews2.clear();
                    it2 = it;
                    transitionImpl2 = transitionImpl;
                    enteringNames3 = enteringNames;
                    firstOutTargetNames = exitingNames;
                } else {
                    sharedElementLastInViews2 = sharedElementLastInViews;
                    sharedElementFirstOutViews2 = sharedElementFirstOutViews;
                    sharedElementTransition2 = sharedElementTransition;
                    it2 = it;
                    transitionImpl2 = transitionImpl;
                    enteringNames3 = enteringNames;
                    firstOutTargetNames = exitingNames;
                }
            }
        }
        FragmentTransitionImpl transitionImpl3 = transitionImpl2;
        if (sharedElementTransition2 == null) {
            List $this$all$iv = filteredInfos;
            if (!($this$all$iv instanceof Collection) || !$this$all$iv.isEmpty()) {
                Iterator it5 = $this$all$iv.iterator();
                while (true) {
                    if (!it5.hasNext()) {
                        z = true;
                        break;
                    }
                    Object element$iv2 = it5.next();
                    TransitionInfo it6 = (TransitionInfo) element$iv2;
                    if (!(it6.getTransition() == null)) {
                        z = false;
                        break;
                    }
                }
            } else {
                z = true;
            }
            if (z) {
                return;
            }
        }
        TransitionEffect transitionEffect = new TransitionEffect(filteredInfos, firstOut, lastIn, transitionImpl3, sharedElementTransition2, sharedElementFirstOutViews2, sharedElementLastInViews2, sharedElementNameMapping, enteringNames3, firstOutTargetNames, firstOutViews, lastInViews, isPop);
        List $this$forEach$iv = filteredInfos;
        for (Object element$iv3 : $this$forEach$iv) {
            ((TransitionInfo) element$iv3).getOperation().addEffect(transitionEffect);
        }
    }

    private final void retainMatchingViews(ArrayMap<String, View> arrayMap, final Collection<String> collection) {
        Set<Map.Entry<String, View>> entries = arrayMap.entrySet();
        Intrinsics.checkNotNullExpressionValue(entries, "entries");
        CollectionsKt.retainAll(entries, new Function1<Map.Entry<String, View>, Boolean>() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$retainMatchingViews$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Map.Entry<String, View> entry) {
                Intrinsics.checkNotNullParameter(entry, "entry");
                return Boolean.valueOf(CollectionsKt.contains(collection, ViewCompat.getTransitionName(entry.getValue())));
            }
        });
    }

    private final void findNamedViews(Map<String, View> namedViews, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            namedViews.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            int count = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < count; i++) {
                View child = ((ViewGroup) view).getChildAt(i);
                if (child.getVisibility() == 0) {
                    Intrinsics.checkNotNullExpressionValue(child, "child");
                    findNamedViews(namedViews, child);
                }
            }
        }
    }

    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$SpecialEffectsInfo;", "", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "(Landroidx/fragment/app/SpecialEffectsController$Operation;)V", "isVisibilityUnchanged", "", "()Z", "getOperation", "()Landroidx/fragment/app/SpecialEffectsController$Operation;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation operation;

        public SpecialEffectsInfo(SpecialEffectsController.Operation operation) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            this.operation = operation;
        }

        public final SpecialEffectsController.Operation getOperation() {
            return this.operation;
        }

        public final boolean isVisibilityUnchanged() {
            View view = this.operation.getFragment().mView;
            SpecialEffectsController.Operation.State currentState = view != null ? SpecialEffectsController.Operation.State.INSTANCE.asOperationState(view) : null;
            SpecialEffectsController.Operation.State finalState = this.operation.getFinalState();
            return currentState == finalState || !(currentState == SpecialEffectsController.Operation.State.VISIBLE || finalState == SpecialEffectsController.Operation.State.VISIBLE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\n\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\fR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "Landroidx/fragment/app/DefaultSpecialEffectsController$SpecialEffectsInfo;", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "isPop", "", "(Landroidx/fragment/app/SpecialEffectsController$Operation;Z)V", "animation", "Landroidx/fragment/app/FragmentAnim$AnimationOrAnimator;", "isAnimLoaded", "getAnimation", "context", "Landroid/content/Context;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator animation;
        private boolean isAnimLoaded;
        private final boolean isPop;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnimationInfo(SpecialEffectsController.Operation operation, boolean isPop) {
            super(operation);
            Intrinsics.checkNotNullParameter(operation, "operation");
            this.isPop = isPop;
        }

        public final FragmentAnim.AnimationOrAnimator getAnimation(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (this.isAnimLoaded) {
                return this.animation;
            }
            FragmentAnim.AnimationOrAnimator it = FragmentAnim.loadAnimation(context, getOperation().getFragment(), getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE, this.isPop);
            this.animation = it;
            this.isAnimLoaded = true;
            return it;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0014\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000fH\u0002J\u0006\u0010\u0014\u001a\u00020\u0005R\u0013\u0010\b\u001a\u0004\u0018\u00010\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011¨\u0006\u0015"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$TransitionInfo;", "Landroidx/fragment/app/DefaultSpecialEffectsController$SpecialEffectsInfo;", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "isPop", "", "providesSharedElementTransition", "(Landroidx/fragment/app/SpecialEffectsController$Operation;ZZ)V", "handlingImpl", "Landroidx/fragment/app/FragmentTransitionImpl;", "getHandlingImpl", "()Landroidx/fragment/app/FragmentTransitionImpl;", "isOverlapAllowed", "()Z", "sharedElementTransition", "", "getSharedElementTransition", "()Ljava/lang/Object;", "transition", "getTransition", "hasSharedElementTransition", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class TransitionInfo extends SpecialEffectsInfo {
        private final boolean isOverlapAllowed;
        private final Object sharedElementTransition;
        private final Object transition;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TransitionInfo(SpecialEffectsController.Operation operation, boolean isPop, boolean providesSharedElementTransition) {
            super(operation);
            Object returnTransition;
            boolean z;
            Object obj;
            Intrinsics.checkNotNullParameter(operation, "operation");
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                Fragment fragment = operation.getFragment();
                returnTransition = isPop ? fragment.getReenterTransition() : fragment.getEnterTransition();
            } else {
                Fragment fragment2 = operation.getFragment();
                returnTransition = isPop ? fragment2.getReturnTransition() : fragment2.getExitTransition();
            }
            this.transition = returnTransition;
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                if (isPop) {
                    z = operation.getFragment().getAllowReturnTransitionOverlap();
                } else {
                    z = operation.getFragment().getAllowEnterTransitionOverlap();
                }
            } else {
                z = true;
            }
            this.isOverlapAllowed = z;
            if (providesSharedElementTransition) {
                if (isPop) {
                    obj = operation.getFragment().getSharedElementReturnTransition();
                } else {
                    obj = operation.getFragment().getSharedElementEnterTransition();
                }
            } else {
                obj = null;
            }
            this.sharedElementTransition = obj;
        }

        public final Object getTransition() {
            return this.transition;
        }

        /* renamed from: isOverlapAllowed, reason: from getter */
        public final boolean getIsOverlapAllowed() {
            return this.isOverlapAllowed;
        }

        public final Object getSharedElementTransition() {
            return this.sharedElementTransition;
        }

        public final boolean hasSharedElementTransition() {
            return this.sharedElementTransition != null;
        }

        public final FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl transitionImpl = getHandlingImpl(this.transition);
            FragmentTransitionImpl sharedElementTransitionImpl = getHandlingImpl(this.sharedElementTransition);
            if (transitionImpl == null || sharedElementTransitionImpl == null || transitionImpl == sharedElementTransitionImpl) {
                return transitionImpl == null ? sharedElementTransitionImpl : transitionImpl;
            }
            throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.transition + " which uses a different Transition  type than its shared element transition " + this.sharedElementTransition).toString());
        }

        private final FragmentTransitionImpl getHandlingImpl(Object transition) {
            if (transition == null) {
                return null;
            }
            if (FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.PLATFORM_IMPL.canHandle(transition)) {
                return FragmentTransition.PLATFORM_IMPL;
            }
            if (FragmentTransition.SUPPORT_IMPL != null && FragmentTransition.SUPPORT_IMPL.canHandle(transition)) {
                return FragmentTransition.SUPPORT_IMPL;
            }
            throw new IllegalArgumentException("Transition " + transition + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationEffect;", "Landroidx/fragment/app/SpecialEffectsController$Effect;", "animationInfo", "Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "(Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;)V", "getAnimationInfo", "()Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "onCancel", "", "container", "Landroid/view/ViewGroup;", "onCommit", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class AnimationEffect extends SpecialEffectsController.Effect {
        private final AnimationInfo animationInfo;

        public AnimationEffect(AnimationInfo animationInfo) {
            Intrinsics.checkNotNullParameter(animationInfo, "animationInfo");
            this.animationInfo = animationInfo;
        }

        public final AnimationInfo getAnimationInfo() {
            return this.animationInfo;
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        public void onCommit(ViewGroup container) {
            Intrinsics.checkNotNullParameter(container, "container");
            if (this.animationInfo.isVisibilityUnchanged()) {
                this.animationInfo.getOperation().completeEffect(this);
                return;
            }
            Context context = container.getContext();
            SpecialEffectsController.Operation operation = this.animationInfo.getOperation();
            Fragment fragment = operation.getFragment();
            View viewToAnimate = fragment.mView;
            AnimationInfo animationInfo = this.animationInfo;
            Intrinsics.checkNotNullExpressionValue(context, "context");
            FragmentAnim.AnimationOrAnimator animation = animationInfo.getAnimation(context);
            if (animation == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            Animation anim = animation.animation;
            if (anim == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            SpecialEffectsController.Operation.State finalState = operation.getFinalState();
            if (finalState != SpecialEffectsController.Operation.State.REMOVED) {
                viewToAnimate.startAnimation(anim);
                this.animationInfo.getOperation().completeEffect(this);
                return;
            }
            container.startViewTransition(viewToAnimate);
            Animation animation2 = new FragmentAnim.EndViewTransitionAnimation(anim, container, viewToAnimate);
            animation2.setAnimationListener(new DefaultSpecialEffectsController$AnimationEffect$onCommit$1(operation, container, viewToAnimate, this));
            viewToAnimate.startAnimation(animation2);
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v(FragmentManager.TAG, "Animation from operation " + operation + " has started.");
            }
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        public void onCancel(ViewGroup container) {
            Intrinsics.checkNotNullParameter(container, "container");
            SpecialEffectsController.Operation operation = this.animationInfo.getOperation();
            Fragment fragment = operation.getFragment();
            View viewToAnimate = fragment.mView;
            viewToAnimate.clearAnimation();
            container.endViewTransition(viewToAnimate);
            this.animationInfo.getOperation().completeEffect(this);
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v(FragmentManager.TAG, "Animation from operation " + operation + " has been cancelled.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000f¨\u0006\u0019"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$AnimatorEffect;", "Landroidx/fragment/app/SpecialEffectsController$Effect;", "animatorInfo", "Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "(Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;)V", "animator", "Landroid/animation/AnimatorSet;", "getAnimator", "()Landroid/animation/AnimatorSet;", "setAnimator", "(Landroid/animation/AnimatorSet;)V", "getAnimatorInfo", "()Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "isSeekingSupported", "", "()Z", "onCancel", "", "container", "Landroid/view/ViewGroup;", "onCommit", "onProgress", "backEvent", "Landroidx/activity/BackEventCompat;", "onStart", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class AnimatorEffect extends SpecialEffectsController.Effect {
        private AnimatorSet animator;
        private final AnimationInfo animatorInfo;

        public AnimatorEffect(AnimationInfo animatorInfo) {
            Intrinsics.checkNotNullParameter(animatorInfo, "animatorInfo");
            this.animatorInfo = animatorInfo;
        }

        public final AnimationInfo getAnimatorInfo() {
            return this.animatorInfo;
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        /* renamed from: isSeekingSupported */
        public boolean getIsSeekingSupported() {
            return true;
        }

        public final AnimatorSet getAnimator() {
            return this.animator;
        }

        public final void setAnimator(AnimatorSet animatorSet) {
            this.animator = animatorSet;
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        public void onStart(final ViewGroup container) {
            Intrinsics.checkNotNullParameter(container, "container");
            if (this.animatorInfo.isVisibilityUnchanged()) {
                return;
            }
            Context context = container.getContext();
            AnimationInfo animationInfo = this.animatorInfo;
            Intrinsics.checkNotNullExpressionValue(context, "context");
            FragmentAnim.AnimationOrAnimator animation = animationInfo.getAnimation(context);
            this.animator = animation != null ? animation.animator : null;
            final SpecialEffectsController.Operation operation = this.animatorInfo.getOperation();
            Fragment fragment = operation.getFragment();
            final boolean isHideOperation = operation.getFinalState() == SpecialEffectsController.Operation.State.GONE;
            final View viewToAnimate = fragment.mView;
            container.startViewTransition(viewToAnimate);
            AnimatorSet animatorSet = this.animator;
            if (animatorSet != null) {
                animatorSet.addListener(new AnimatorListenerAdapter() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$AnimatorEffect$onStart$1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator anim) {
                        Intrinsics.checkNotNullParameter(anim, "anim");
                        container.endViewTransition(viewToAnimate);
                        if (isHideOperation) {
                            SpecialEffectsController.Operation.State finalState = operation.getFinalState();
                            View viewToAnimate2 = viewToAnimate;
                            Intrinsics.checkNotNullExpressionValue(viewToAnimate2, "viewToAnimate");
                            finalState.applyState(viewToAnimate2, container);
                        }
                        this.getAnimatorInfo().getOperation().completeEffect(this);
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v(FragmentManager.TAG, "Animator from operation " + operation + " has ended.");
                        }
                    }
                });
            }
            AnimatorSet animatorSet2 = this.animator;
            if (animatorSet2 != null) {
                animatorSet2.setTarget(viewToAnimate);
            }
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        public void onProgress(BackEventCompat backEvent, ViewGroup container) {
            Intrinsics.checkNotNullParameter(backEvent, "backEvent");
            Intrinsics.checkNotNullParameter(container, "container");
            SpecialEffectsController.Operation operation = this.animatorInfo.getOperation();
            AnimatorSet animatorSet = this.animator;
            if (animatorSet == null) {
                this.animatorInfo.getOperation().completeEffect(this);
                return;
            }
            if (Build.VERSION.SDK_INT >= 34 && operation.getFragment().mTransitioning) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, "Adding BackProgressCallbacks for Animators to operation " + operation);
                }
                long totalDuration = Api24Impl.INSTANCE.totalDuration(animatorSet);
                long time = (long) (backEvent.getProgress() * totalDuration);
                if (time == 0) {
                    time = 1;
                }
                if (time == totalDuration) {
                    time = totalDuration - 1;
                }
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, "Setting currentPlayTime to " + time + " for Animator " + animatorSet + " on operation " + operation);
                }
                Api26Impl.INSTANCE.setCurrentPlayTime(animatorSet, time);
            }
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        public void onCommit(ViewGroup container) {
            Intrinsics.checkNotNullParameter(container, "container");
            SpecialEffectsController.Operation operation = this.animatorInfo.getOperation();
            AnimatorSet animatorSet = this.animator;
            if (animatorSet == null) {
                this.animatorInfo.getOperation().completeEffect(this);
                return;
            }
            animatorSet.start();
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v(FragmentManager.TAG, "Animator from operation " + operation + " has started.");
            }
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        public void onCancel(ViewGroup container) {
            Intrinsics.checkNotNullParameter(container, "container");
            AnimatorSet animator = this.animator;
            if (animator == null) {
                this.animatorInfo.getOperation().completeEffect(this);
                return;
            }
            SpecialEffectsController.Operation operation = this.animatorInfo.getOperation();
            if (operation.getIsSeeking()) {
                Api26Impl.INSTANCE.reverse(animator);
            } else {
                animator.end();
            }
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v(FragmentManager.TAG, "Animator from operation " + operation + " has been canceled" + (operation.getIsSeeking() ? " with seeking." : ".") + ' ');
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001BÝ\u0001\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f\u0012\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f\u0012\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u0012\u0012\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00130\rj\b\u0012\u0004\u0012\u00020\u0013`\u000f\u0012\u0016\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00130\rj\b\u0012\u0004\u0012\u00020\u0013`\u000f\u0012\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\u0012\u0012\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\u0012\u0012\u0006\u0010\u0018\u001a\u00020\u0019¢\u0006\u0002\u0010\u001aJ(\u0010?\u001a\u00020@2\u0016\u0010A\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f2\u0006\u0010B\u001a\u00020\u000eH\u0002J@\u0010C\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f\u0012\u0004\u0012\u00020\u000b0D2\u0006\u0010E\u001a\u00020F2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010G\u001a\u00020@2\u0006\u0010E\u001a\u00020FH\u0016J\u0010\u0010H\u001a\u00020@2\u0006\u0010E\u001a\u00020FH\u0016J\u0018\u0010I\u001a\u00020@2\u0006\u0010J\u001a\u00020K2\u0006\u0010E\u001a\u00020FH\u0016J\u0010\u0010L\u001a\u00020@2\u0006\u0010E\u001a\u00020FH\u0016J6\u0010M\u001a\u00020@2\u0016\u0010N\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f2\u0006\u0010E\u001a\u00020F2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020@0PH\u0002R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR!\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00130\rj\b\u0012\u0004\u0012\u00020\u0013`\u000f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R!\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00130\rj\b\u0012\u0004\u0012\u00020\u0013`\u000f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001d\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\u0012¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010'R\u0014\u0010(\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010'R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b)\u0010$R\u001d\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\u0012¢\u0006\b\n\u0000\u001a\u0004\b*\u0010&R\u001a\u0010+\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010'\"\u0004\b-\u0010.R!\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f¢\u0006\b\n\u0000\u001a\u0004\b/\u0010!R!\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f¢\u0006\b\n\u0000\u001a\u0004\b0\u0010!R\u001d\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b1\u0010&R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001dR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0017\u00107\u001a\u000208¢\u0006\u000e\n\u0000\u0012\u0004\b9\u0010:\u001a\u0004\b;\u0010<R\u0011\u0010=\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b>\u0010'¨\u0006Q"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$TransitionEffect;", "Landroidx/fragment/app/SpecialEffectsController$Effect;", "transitionInfos", "", "Landroidx/fragment/app/DefaultSpecialEffectsController$TransitionInfo;", "firstOut", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "lastIn", "transitionImpl", "Landroidx/fragment/app/FragmentTransitionImpl;", "sharedElementTransition", "", "sharedElementFirstOutViews", "Ljava/util/ArrayList;", "Landroid/view/View;", "Lkotlin/collections/ArrayList;", "sharedElementLastInViews", "sharedElementNameMapping", "Landroidx/collection/ArrayMap;", "", "enteringNames", "exitingNames", "firstOutViews", "lastInViews", "isPop", "", "(Ljava/util/List;Landroidx/fragment/app/SpecialEffectsController$Operation;Landroidx/fragment/app/SpecialEffectsController$Operation;Landroidx/fragment/app/FragmentTransitionImpl;Ljava/lang/Object;Ljava/util/ArrayList;Ljava/util/ArrayList;Landroidx/collection/ArrayMap;Ljava/util/ArrayList;Ljava/util/ArrayList;Landroidx/collection/ArrayMap;Landroidx/collection/ArrayMap;Z)V", "controller", "getController", "()Ljava/lang/Object;", "setController", "(Ljava/lang/Object;)V", "getEnteringNames", "()Ljava/util/ArrayList;", "getExitingNames", "getFirstOut", "()Landroidx/fragment/app/SpecialEffectsController$Operation;", "getFirstOutViews", "()Landroidx/collection/ArrayMap;", "()Z", "isSeekingSupported", "getLastIn", "getLastInViews", "noControllerReturned", "getNoControllerReturned", "setNoControllerReturned", "(Z)V", "getSharedElementFirstOutViews", "getSharedElementLastInViews", "getSharedElementNameMapping", "getSharedElementTransition", "getTransitionImpl", "()Landroidx/fragment/app/FragmentTransitionImpl;", "getTransitionInfos", "()Ljava/util/List;", "transitionSignal", "Landroidx/core/os/CancellationSignal;", "getTransitionSignal$annotations", "()V", "getTransitionSignal", "()Landroidx/core/os/CancellationSignal;", "transitioning", "getTransitioning", "captureTransitioningViews", "", "transitioningViews", "view", "createMergedTransition", "Lkotlin/Pair;", "container", "Landroid/view/ViewGroup;", "onCancel", "onCommit", "onProgress", "backEvent", "Landroidx/activity/BackEventCompat;", "onStart", "runTransition", "enteringViews", "executeTransition", "Lkotlin/Function0;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class TransitionEffect extends SpecialEffectsController.Effect {
        private Object controller;
        private final ArrayList<String> enteringNames;
        private final ArrayList<String> exitingNames;
        private final SpecialEffectsController.Operation firstOut;
        private final ArrayMap<String, View> firstOutViews;
        private final boolean isPop;
        private final SpecialEffectsController.Operation lastIn;
        private final ArrayMap<String, View> lastInViews;
        private boolean noControllerReturned;
        private final ArrayList<View> sharedElementFirstOutViews;
        private final ArrayList<View> sharedElementLastInViews;
        private final ArrayMap<String, String> sharedElementNameMapping;
        private final Object sharedElementTransition;
        private final FragmentTransitionImpl transitionImpl;
        private final List<TransitionInfo> transitionInfos;
        private final CancellationSignal transitionSignal;

        public static /* synthetic */ void getTransitionSignal$annotations() {
        }

        public final List<TransitionInfo> getTransitionInfos() {
            return this.transitionInfos;
        }

        public final SpecialEffectsController.Operation getFirstOut() {
            return this.firstOut;
        }

        public final SpecialEffectsController.Operation getLastIn() {
            return this.lastIn;
        }

        public final FragmentTransitionImpl getTransitionImpl() {
            return this.transitionImpl;
        }

        public final Object getSharedElementTransition() {
            return this.sharedElementTransition;
        }

        public final ArrayList<View> getSharedElementFirstOutViews() {
            return this.sharedElementFirstOutViews;
        }

        public final ArrayList<View> getSharedElementLastInViews() {
            return this.sharedElementLastInViews;
        }

        public final ArrayMap<String, String> getSharedElementNameMapping() {
            return this.sharedElementNameMapping;
        }

        public final ArrayList<String> getEnteringNames() {
            return this.enteringNames;
        }

        public final ArrayList<String> getExitingNames() {
            return this.exitingNames;
        }

        public final ArrayMap<String, View> getFirstOutViews() {
            return this.firstOutViews;
        }

        public final ArrayMap<String, View> getLastInViews() {
            return this.lastInViews;
        }

        /* renamed from: isPop, reason: from getter */
        public final boolean getIsPop() {
            return this.isPop;
        }

        public TransitionEffect(List<TransitionInfo> transitionInfos, SpecialEffectsController.Operation firstOut, SpecialEffectsController.Operation lastIn, FragmentTransitionImpl transitionImpl, Object sharedElementTransition, ArrayList<View> sharedElementFirstOutViews, ArrayList<View> sharedElementLastInViews, ArrayMap<String, String> sharedElementNameMapping, ArrayList<String> enteringNames, ArrayList<String> exitingNames, ArrayMap<String, View> firstOutViews, ArrayMap<String, View> lastInViews, boolean isPop) {
            Intrinsics.checkNotNullParameter(transitionInfos, "transitionInfos");
            Intrinsics.checkNotNullParameter(transitionImpl, "transitionImpl");
            Intrinsics.checkNotNullParameter(sharedElementFirstOutViews, "sharedElementFirstOutViews");
            Intrinsics.checkNotNullParameter(sharedElementLastInViews, "sharedElementLastInViews");
            Intrinsics.checkNotNullParameter(sharedElementNameMapping, "sharedElementNameMapping");
            Intrinsics.checkNotNullParameter(enteringNames, "enteringNames");
            Intrinsics.checkNotNullParameter(exitingNames, "exitingNames");
            Intrinsics.checkNotNullParameter(firstOutViews, "firstOutViews");
            Intrinsics.checkNotNullParameter(lastInViews, "lastInViews");
            this.transitionInfos = transitionInfos;
            this.firstOut = firstOut;
            this.lastIn = lastIn;
            this.transitionImpl = transitionImpl;
            this.sharedElementTransition = sharedElementTransition;
            this.sharedElementFirstOutViews = sharedElementFirstOutViews;
            this.sharedElementLastInViews = sharedElementLastInViews;
            this.sharedElementNameMapping = sharedElementNameMapping;
            this.enteringNames = enteringNames;
            this.exitingNames = exitingNames;
            this.firstOutViews = firstOutViews;
            this.lastInViews = lastInViews;
            this.isPop = isPop;
            this.transitionSignal = new CancellationSignal();
        }

        public final CancellationSignal getTransitionSignal() {
            return this.transitionSignal;
        }

        public final Object getController() {
            return this.controller;
        }

        public final void setController(Object obj) {
            this.controller = obj;
        }

        public final boolean getNoControllerReturned() {
            return this.noControllerReturned;
        }

        public final void setNoControllerReturned(boolean z) {
            this.noControllerReturned = z;
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        /* renamed from: isSeekingSupported */
        public boolean getIsSeekingSupported() {
            Iterable $this$all$iv;
            if (!this.transitionImpl.isSeekingSupported()) {
                return false;
            }
            Iterable $this$all$iv2 = this.transitionInfos;
            if (!($this$all$iv2 instanceof Collection) || !((Collection) $this$all$iv2).isEmpty()) {
                Iterator it = $this$all$iv2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        TransitionInfo it2 = (TransitionInfo) element$iv;
                        if (!(Build.VERSION.SDK_INT >= 34 && it2.getTransition() != null && this.transitionImpl.isSeekingSupported(it2.getTransition()))) {
                            $this$all$iv = null;
                            break;
                        }
                    } else {
                        $this$all$iv = 1;
                        break;
                    }
                }
            } else {
                $this$all$iv = 1;
            }
            if ($this$all$iv != null) {
                return this.sharedElementTransition == null || this.transitionImpl.isSeekingSupported(this.sharedElementTransition);
            }
            return false;
        }

        public final boolean getTransitioning() {
            Iterable $this$all$iv = this.transitionInfos;
            if (($this$all$iv instanceof Collection) && ((Collection) $this$all$iv).isEmpty()) {
                return true;
            }
            for (Object element$iv : $this$all$iv) {
                TransitionInfo it = (TransitionInfo) element$iv;
                if (!it.getOperation().getFragment().mTransitioning) {
                    return false;
                }
            }
            return true;
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        public void onStart(final ViewGroup container) {
            Intrinsics.checkNotNullParameter(container, "container");
            if (!container.isLaidOut()) {
                Iterable $this$forEach$iv = this.transitionInfos;
                for (Object element$iv : $this$forEach$iv) {
                    TransitionInfo transitionInfo = (TransitionInfo) element$iv;
                    SpecialEffectsController.Operation operation = transitionInfo.getOperation();
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v(FragmentManager.TAG, "SpecialEffectsController: Container " + container + " has not been laid out. Skipping onStart for operation " + operation);
                    }
                }
                return;
            }
            if (getTransitioning() && this.sharedElementTransition != null && !getIsSeekingSupported()) {
                Log.i(FragmentManager.TAG, "Ignoring shared elements transition " + this.sharedElementTransition + " between " + this.firstOut + " and " + this.lastIn + " as neither fragment has set a Transition. In order to run a SharedElementTransition, you must also set either an enter or exit transition on a fragment involved in the transaction. The sharedElementTransition will run after the back gesture has been committed.");
            }
            if (getIsSeekingSupported() && getTransitioning()) {
                final Ref.ObjectRef seekCancelLambda = new Ref.ObjectRef();
                Pair<ArrayList<View>, Object> createMergedTransition = createMergedTransition(container, this.lastIn, this.firstOut);
                ArrayList enteringViews = createMergedTransition.component1();
                final Object mergedTransition = createMergedTransition.component2();
                Iterable $this$map$iv = this.transitionInfos;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    TransitionInfo it = (TransitionInfo) item$iv$iv;
                    destination$iv$iv.add(it.getOperation());
                }
                Iterable $this$forEach$iv2 = (List) destination$iv$iv;
                for (Object element$iv2 : $this$forEach$iv2) {
                    final SpecialEffectsController.Operation operation2 = (SpecialEffectsController.Operation) element$iv2;
                    Runnable cancelRunnable = new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            DefaultSpecialEffectsController.TransitionEffect.onStart$lambda$6$lambda$4(Ref.ObjectRef.this);
                        }
                    };
                    this.transitionImpl.setListenerForTransitionEnd(operation2.getFragment(), mergedTransition, this.transitionSignal, cancelRunnable, new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4
                        @Override // java.lang.Runnable
                        public final void run() {
                            DefaultSpecialEffectsController.TransitionEffect.onStart$lambda$6$lambda$5(SpecialEffectsController.Operation.this, this);
                        }
                    });
                }
                runTransition(enteringViews, container, new Function0<Unit>() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Type inference failed for: r3v3, types: [T, androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4$1] */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v(FragmentManager.TAG, "Attempting to create TransitionSeekController");
                        }
                        DefaultSpecialEffectsController.TransitionEffect.this.setController(DefaultSpecialEffectsController.TransitionEffect.this.getTransitionImpl().controlDelayedTransition(container, mergedTransition));
                        if (DefaultSpecialEffectsController.TransitionEffect.this.getController() == null) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "TransitionSeekController was not created.");
                            }
                            DefaultSpecialEffectsController.TransitionEffect.this.setNoControllerReturned(true);
                        } else {
                            seekCancelLambda.element = new AnonymousClass1(DefaultSpecialEffectsController.TransitionEffect.this, mergedTransition, container);
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "Started executing operations from " + DefaultSpecialEffectsController.TransitionEffect.this.getFirstOut() + " to " + DefaultSpecialEffectsController.TransitionEffect.this.getLastIn());
                            }
                        }
                    }

                    /* compiled from: DefaultSpecialEffectsController.kt */
                    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4$1, reason: invalid class name */
                    static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
                        final /* synthetic */ ViewGroup $container;
                        final /* synthetic */ Object $mergedTransition;
                        final /* synthetic */ DefaultSpecialEffectsController.TransitionEffect this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(DefaultSpecialEffectsController.TransitionEffect transitionEffect, Object obj, ViewGroup viewGroup) {
                            super(0);
                            this.this$0 = transitionEffect;
                            this.$mergedTransition = obj;
                            this.$container = viewGroup;
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            Iterable $this$all$iv = this.this$0.getTransitionInfos();
                            boolean z = true;
                            if (!($this$all$iv instanceof Collection) || !((Collection) $this$all$iv).isEmpty()) {
                                Iterator it = $this$all$iv.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    Object element$iv = it.next();
                                    DefaultSpecialEffectsController.TransitionInfo it2 = (DefaultSpecialEffectsController.TransitionInfo) element$iv;
                                    if (!it2.getOperation().getIsSeeking()) {
                                        z = false;
                                        break;
                                    }
                                }
                            }
                            if (z) {
                                if (FragmentManager.isLoggingEnabled(2)) {
                                    Log.v(FragmentManager.TAG, "Animating to start");
                                }
                                FragmentTransitionImpl transitionImpl = this.this$0.getTransitionImpl();
                                Object controller = this.this$0.getController();
                                Intrinsics.checkNotNull(controller);
                                final DefaultSpecialEffectsController.TransitionEffect transitionEffect = this.this$0;
                                final ViewGroup viewGroup = this.$container;
                                transitionImpl.animateToStart(controller, 
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0060: INVOKE 
                                      (r0v6 'transitionImpl' androidx.fragment.app.FragmentTransitionImpl)
                                      (r1v8 'controller' java.lang.Object)
                                      (wrap:java.lang.Runnable:0x005d: CONSTRUCTOR 
                                      (r2v8 'transitionEffect' androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect A[DONT_INLINE])
                                      (r3v2 'viewGroup' android.view.ViewGroup A[DONT_INLINE])
                                     A[MD:(androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect, android.view.ViewGroup):void (m), WRAPPED] call: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4$1$$ExternalSyntheticLambda0.<init>(androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect, android.view.ViewGroup):void type: CONSTRUCTOR)
                                     VIRTUAL call: androidx.fragment.app.FragmentTransitionImpl.animateToStart(java.lang.Object, java.lang.Runnable):void A[MD:(java.lang.Object, java.lang.Runnable):void (m)] in method: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4.1.invoke():void, file: classes.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4$1$$ExternalSyntheticLambda0, state: NOT_LOADED
                                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:305)
                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                    	... 23 more
                                    */
                                /*
                                    this = this;
                                    androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect r0 = r9.this$0
                                    java.util.List r0 = r0.getTransitionInfos()
                                    java.lang.Iterable r0 = (java.lang.Iterable) r0
                                    r1 = 0
                                    boolean r2 = r0 instanceof java.util.Collection
                                    r3 = 0
                                    r4 = 1
                                    if (r2 == 0) goto L19
                                    r2 = r0
                                    java.util.Collection r2 = (java.util.Collection) r2
                                    boolean r2 = r2.isEmpty()
                                    if (r2 == 0) goto L19
                                    goto L38
                                L19:
                                    java.util.Iterator r2 = r0.iterator()
                                L1d:
                                    boolean r5 = r2.hasNext()
                                    if (r5 == 0) goto L37
                                    java.lang.Object r5 = r2.next()
                                    r6 = r5
                                    androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r6 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r6
                                    r7 = 0
                                    androidx.fragment.app.SpecialEffectsController$Operation r8 = r6.getOperation()
                                    boolean r6 = r8.getIsSeeking()
                                    if (r6 != 0) goto L1d
                                    r4 = r3
                                    goto L38
                                L37:
                                L38:
                                    java.lang.String r0 = "FragmentManager"
                                    r1 = 2
                                    if (r4 == 0) goto L64
                                    boolean r1 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r1)
                                    if (r1 == 0) goto L48
                                    java.lang.String r1 = "Animating to start"
                                    android.util.Log.v(r0, r1)
                                L48:
                                    androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect r0 = r9.this$0
                                    androidx.fragment.app.FragmentTransitionImpl r0 = r0.getTransitionImpl()
                                    androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect r1 = r9.this$0
                                    java.lang.Object r1 = r1.getController()
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                                    androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect r2 = r9.this$0
                                    android.view.ViewGroup r3 = r9.$container
                                    androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4$1$$ExternalSyntheticLambda0 r4 = new androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4$1$$ExternalSyntheticLambda0
                                    r4.<init>(r2, r3)
                                    r0.animateToStart(r1, r4)
                                    goto L9e
                                L64:
                                    boolean r1 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r1)
                                    if (r1 == 0) goto L6f
                                    java.lang.String r1 = "Completing animating immediately"
                                    android.util.Log.v(r0, r1)
                                L6f:
                                    androidx.core.os.CancellationSignal r0 = new androidx.core.os.CancellationSignal
                                    r0.<init>()
                                    androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect r1 = r9.this$0
                                    androidx.fragment.app.FragmentTransitionImpl r1 = r1.getTransitionImpl()
                                    androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect r2 = r9.this$0
                                    java.util.List r2 = r2.getTransitionInfos()
                                    java.lang.Object r2 = r2.get(r3)
                                    androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r2 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r2
                                    androidx.fragment.app.SpecialEffectsController$Operation r2 = r2.getOperation()
                                    androidx.fragment.app.Fragment r2 = r2.getFragment()
                                    java.lang.Object r3 = r9.$mergedTransition
                                    androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect r4 = r9.this$0
                                    androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4$1$$ExternalSyntheticLambda1 r5 = new androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4$1$$ExternalSyntheticLambda1
                                    r5.<init>(r4)
                                    r1.setListenerForTransitionEnd(r2, r3, r0, r5)
                                    r0.cancel()
                                L9e:
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4.AnonymousClass1.invoke2():void");
                            }

                            /* JADX INFO: Access modifiers changed from: private */
                            public static final void invoke$lambda$2(DefaultSpecialEffectsController.TransitionEffect this$0, ViewGroup container) {
                                Intrinsics.checkNotNullParameter(this$0, "this$0");
                                Intrinsics.checkNotNullParameter(container, "$container");
                                Iterable $this$forEach$iv = this$0.getTransitionInfos();
                                for (Object element$iv : $this$forEach$iv) {
                                    DefaultSpecialEffectsController.TransitionInfo transitionInfo = (DefaultSpecialEffectsController.TransitionInfo) element$iv;
                                    SpecialEffectsController.Operation operation = transitionInfo.getOperation();
                                    View view = operation.getFragment().getView();
                                    if (view != null) {
                                        operation.getFinalState().applyState(view, container);
                                    }
                                }
                            }

                            /* JADX INFO: Access modifiers changed from: private */
                            public static final void invoke$lambda$4(DefaultSpecialEffectsController.TransitionEffect this$0) {
                                Intrinsics.checkNotNullParameter(this$0, "this$0");
                                if (FragmentManager.isLoggingEnabled(2)) {
                                    Log.v(FragmentManager.TAG, "Transition for all operations has completed");
                                }
                                Iterable $this$forEach$iv = this$0.getTransitionInfos();
                                for (Object element$iv : $this$forEach$iv) {
                                    DefaultSpecialEffectsController.TransitionInfo it = (DefaultSpecialEffectsController.TransitionInfo) element$iv;
                                    it.getOperation().completeEffect(this$0);
                                }
                            }
                        }
                    });
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void onStart$lambda$6$lambda$4(Ref.ObjectRef seekCancelLambda) {
                Intrinsics.checkNotNullParameter(seekCancelLambda, "$seekCancelLambda");
                Function0 function0 = (Function0) seekCancelLambda.element;
                if (function0 != null) {
                    function0.invoke();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void onStart$lambda$6$lambda$5(SpecialEffectsController.Operation operation, TransitionEffect this$0) {
                Intrinsics.checkNotNullParameter(operation, "$operation");
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, "Transition for operation " + operation + " has completed");
                }
                operation.completeEffect(this$0);
            }

            @Override // androidx.fragment.app.SpecialEffectsController.Effect
            public void onProgress(BackEventCompat backEvent, ViewGroup container) {
                Intrinsics.checkNotNullParameter(backEvent, "backEvent");
                Intrinsics.checkNotNullParameter(container, "container");
                Object it = this.controller;
                if (it != null) {
                    this.transitionImpl.setCurrentPlayTime(it, backEvent.getProgress());
                }
            }

            @Override // androidx.fragment.app.SpecialEffectsController.Effect
            public void onCommit(final ViewGroup container) {
                Intrinsics.checkNotNullParameter(container, "container");
                if (!container.isLaidOut() || this.noControllerReturned) {
                    Iterable $this$forEach$iv = this.transitionInfos;
                    for (Object element$iv : $this$forEach$iv) {
                        TransitionInfo transitionInfo = (TransitionInfo) element$iv;
                        SpecialEffectsController.Operation operation = transitionInfo.getOperation();
                        if (FragmentManager.isLoggingEnabled(2)) {
                            if (this.noControllerReturned) {
                                Log.v(FragmentManager.TAG, "SpecialEffectsController: TransitionSeekController was not created. Completing operation " + operation);
                            } else {
                                Log.v(FragmentManager.TAG, "SpecialEffectsController: Container " + container + " has not been laid out. Completing operation " + operation);
                            }
                        }
                        transitionInfo.getOperation().completeEffect(this);
                    }
                    this.noControllerReturned = false;
                    return;
                }
                if (this.controller != null) {
                    FragmentTransitionImpl fragmentTransitionImpl = this.transitionImpl;
                    Object obj = this.controller;
                    Intrinsics.checkNotNull(obj);
                    fragmentTransitionImpl.animateToEnd(obj);
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v(FragmentManager.TAG, "Ending execution of operations from " + this.firstOut + " to " + this.lastIn);
                        return;
                    }
                    return;
                }
                Pair<ArrayList<View>, Object> createMergedTransition = createMergedTransition(container, this.lastIn, this.firstOut);
                ArrayList enteringViews = createMergedTransition.component1();
                final Object mergedTransition = createMergedTransition.component2();
                Iterable $this$map$iv = this.transitionInfos;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    TransitionInfo it = (TransitionInfo) item$iv$iv;
                    destination$iv$iv.add(it.getOperation());
                }
                Iterable $this$forEach$iv2 = (List) destination$iv$iv;
                for (Object element$iv2 : $this$forEach$iv2) {
                    final SpecialEffectsController.Operation operation2 = (SpecialEffectsController.Operation) element$iv2;
                    this.transitionImpl.setListenerForTransitionEnd(operation2.getFragment(), mergedTransition, this.transitionSignal, new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda5
                        @Override // java.lang.Runnable
                        public final void run() {
                            DefaultSpecialEffectsController.TransitionEffect.onCommit$lambda$11$lambda$10(SpecialEffectsController.Operation.this, this);
                        }
                    });
                }
                runTransition(enteringViews, container, new Function0<Unit>() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onCommit$4
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
                        DefaultSpecialEffectsController.TransitionEffect.this.getTransitionImpl().beginDelayedTransition(container, mergedTransition);
                    }
                });
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, "Completed executing operations from " + this.firstOut + " to " + this.lastIn);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void onCommit$lambda$11$lambda$10(SpecialEffectsController.Operation operation, TransitionEffect this$0) {
                Intrinsics.checkNotNullParameter(operation, "$operation");
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, "Transition for operation " + operation + " has completed");
                }
                operation.completeEffect(this$0);
            }

            private final Pair<ArrayList<View>, Object> createMergedTransition(ViewGroup container, SpecialEffectsController.Operation lastIn, SpecialEffectsController.Operation firstOut) {
                final SpecialEffectsController.Operation operation = lastIn;
                final SpecialEffectsController.Operation operation2 = firstOut;
                View nonExistentView = new View(container.getContext());
                View firstOutEpicenterView = null;
                boolean hasLastInEpicenter = false;
                final Rect lastInEpicenterRect = new Rect();
                Iterator<TransitionInfo> it = this.transitionInfos.iterator();
                while (it.hasNext()) {
                    boolean hasSharedElementTransition = it.next().hasSharedElementTransition();
                    if (hasSharedElementTransition && operation2 != null && operation != null && !this.sharedElementNameMapping.isEmpty() && this.sharedElementTransition != null) {
                        FragmentTransition.callSharedElementStartEnd(lastIn.getFragment(), firstOut.getFragment(), this.isPop, this.firstOutViews, true);
                        OneShotPreDrawListener.add(container, new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                DefaultSpecialEffectsController.TransitionEffect.createMergedTransition$lambda$12(SpecialEffectsController.Operation.this, operation2, this);
                            }
                        });
                        this.sharedElementFirstOutViews.addAll(this.firstOutViews.values());
                        if (!this.exitingNames.isEmpty()) {
                            String str = this.exitingNames.get(0);
                            Intrinsics.checkNotNullExpressionValue(str, "exitingNames[0]");
                            String epicenterViewName = str;
                            View firstOutEpicenterView2 = this.firstOutViews.get(epicenterViewName);
                            firstOutEpicenterView = firstOutEpicenterView2;
                            this.transitionImpl.setEpicenter(this.sharedElementTransition, firstOutEpicenterView);
                        }
                        this.sharedElementLastInViews.addAll(this.lastInViews.values());
                        if (!this.enteringNames.isEmpty()) {
                            String str2 = this.enteringNames.get(0);
                            Intrinsics.checkNotNullExpressionValue(str2, "enteringNames[0]");
                            String epicenterViewName2 = str2;
                            final View lastInEpicenterView = this.lastInViews.get(epicenterViewName2);
                            if (lastInEpicenterView != null) {
                                hasLastInEpicenter = true;
                                final FragmentTransitionImpl impl = this.transitionImpl;
                                OneShotPreDrawListener.add(container, new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda1
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        DefaultSpecialEffectsController.TransitionEffect.createMergedTransition$lambda$13(FragmentTransitionImpl.this, lastInEpicenterView, lastInEpicenterRect);
                                    }
                                });
                            }
                        }
                        this.transitionImpl.setSharedElementTargets(this.sharedElementTransition, nonExistentView, this.sharedElementFirstOutViews);
                        this.transitionImpl.scheduleRemoveTargets(this.sharedElementTransition, null, null, null, null, this.sharedElementTransition, this.sharedElementLastInViews);
                    }
                }
                ArrayList enteringViews = new ArrayList();
                Object mergedTransition = null;
                Object mergedNonOverlappingTransition = null;
                Iterator<TransitionInfo> it2 = this.transitionInfos.iterator();
                while (it2.hasNext()) {
                    TransitionInfo transitionInfo = it2.next();
                    SpecialEffectsController.Operation operation3 = transitionInfo.getOperation();
                    Iterator<TransitionInfo> it3 = it2;
                    Object transition = this.transitionImpl.cloneTransition(transitionInfo.getTransition());
                    if (transition == null) {
                        operation = lastIn;
                        operation2 = firstOut;
                        it2 = it3;
                        nonExistentView = nonExistentView;
                    } else {
                        final ArrayList transitioningViews = new ArrayList();
                        Object mergedNonOverlappingTransition2 = mergedNonOverlappingTransition;
                        View view = operation3.getFragment().mView;
                        Object mergedTransition2 = mergedTransition;
                        Intrinsics.checkNotNullExpressionValue(view, "operation.fragment.mView");
                        captureTransitioningViews(transitioningViews, view);
                        boolean involvedInSharedElementTransition = this.sharedElementTransition != null && (operation3 == operation2 || operation3 == operation);
                        if (involvedInSharedElementTransition) {
                            if (operation3 == operation2) {
                                transitioningViews.removeAll(CollectionsKt.toSet(this.sharedElementFirstOutViews));
                            } else {
                                transitioningViews.removeAll(CollectionsKt.toSet(this.sharedElementLastInViews));
                            }
                        }
                        if (transitioningViews.isEmpty()) {
                            this.transitionImpl.addTarget(transition, nonExistentView);
                        } else {
                            this.transitionImpl.addTargets(transition, transitioningViews);
                            this.transitionImpl.scheduleRemoveTargets(transition, transition, transitioningViews, null, null, null, null);
                            if (operation3.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                                operation3.setAwaitingContainerChanges(false);
                                ArrayList transitioningViewsToHide = new ArrayList(transitioningViews);
                                transitioningViewsToHide.remove(operation3.getFragment().mView);
                                this.transitionImpl.scheduleHideFragmentView(transition, operation3.getFragment().mView, transitioningViewsToHide);
                                OneShotPreDrawListener.add(container, new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda2
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        DefaultSpecialEffectsController.TransitionEffect.createMergedTransition$lambda$14(transitioningViews);
                                    }
                                });
                            }
                        }
                        View nonExistentView2 = nonExistentView;
                        if (operation3.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                            enteringViews.addAll(transitioningViews);
                            if (hasLastInEpicenter) {
                                this.transitionImpl.setEpicenter(transition, lastInEpicenterRect);
                            }
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "Entering Transition: " + transition);
                                Log.v(FragmentManager.TAG, ">>>>> EnteringViews <<<<<");
                                for (Iterator<View> it4 = transitioningViews.iterator(); it4.hasNext(); it4 = it4) {
                                    View transitioningViews2 = it4.next();
                                    Intrinsics.checkNotNullExpressionValue(transitioningViews2, "transitioningViews");
                                    View view2 = transitioningViews2;
                                    Log.v(FragmentManager.TAG, "View: " + view2);
                                }
                            }
                        } else {
                            this.transitionImpl.setEpicenter(transition, firstOutEpicenterView);
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "Exiting Transition: " + transition);
                                Log.v(FragmentManager.TAG, ">>>>> ExitingViews <<<<<");
                                for (Iterator<View> it5 = transitioningViews.iterator(); it5.hasNext(); it5 = it5) {
                                    View transitioningViews3 = it5.next();
                                    Intrinsics.checkNotNullExpressionValue(transitioningViews3, "transitioningViews");
                                    View view3 = transitioningViews3;
                                    Log.v(FragmentManager.TAG, "View: " + view3);
                                }
                            }
                        }
                        if (transitionInfo.getIsOverlapAllowed()) {
                            operation2 = firstOut;
                            mergedTransition = this.transitionImpl.mergeTransitionsTogether(mergedTransition2, transition, null);
                            it2 = it3;
                            nonExistentView = nonExistentView2;
                            mergedNonOverlappingTransition = mergedNonOverlappingTransition2;
                            operation = lastIn;
                        } else {
                            mergedNonOverlappingTransition = this.transitionImpl.mergeTransitionsTogether(mergedNonOverlappingTransition2, transition, null);
                            operation = lastIn;
                            operation2 = firstOut;
                            mergedTransition = mergedTransition2;
                            it2 = it3;
                            nonExistentView = nonExistentView2;
                        }
                    }
                }
                Object mergedTransition3 = this.transitionImpl.mergeTransitionsInSequence(mergedTransition, mergedNonOverlappingTransition, this.sharedElementTransition);
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, "Final merged transition: " + mergedTransition3 + " for container " + container);
                }
                return new Pair<>(enteringViews, mergedTransition3);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void createMergedTransition$lambda$12(SpecialEffectsController.Operation $lastIn, SpecialEffectsController.Operation $firstOut, TransitionEffect this$0) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                FragmentTransition.callSharedElementStartEnd($lastIn.getFragment(), $firstOut.getFragment(), this$0.isPop, this$0.lastInViews, false);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void createMergedTransition$lambda$13(FragmentTransitionImpl impl, View $lastInEpicenterView, Rect lastInEpicenterRect) {
                Intrinsics.checkNotNullParameter(impl, "$impl");
                Intrinsics.checkNotNullParameter(lastInEpicenterRect, "$lastInEpicenterRect");
                impl.getBoundsOnScreen($lastInEpicenterView, lastInEpicenterRect);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void createMergedTransition$lambda$14(ArrayList transitioningViews) {
                Intrinsics.checkNotNullParameter(transitioningViews, "$transitioningViews");
                FragmentTransition.setViewVisibility(transitioningViews, 4);
            }

            private final void runTransition(ArrayList<View> enteringViews, ViewGroup container, Function0<Unit> executeTransition) {
                FragmentTransition.setViewVisibility(enteringViews, 4);
                ArrayList inNames = this.transitionImpl.prepareSetNameOverridesReordered(this.sharedElementLastInViews);
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, ">>>>> Beginning transition <<<<<");
                    Log.v(FragmentManager.TAG, ">>>>> SharedElementFirstOutViews <<<<<");
                    Iterator<View> it = this.sharedElementFirstOutViews.iterator();
                    while (it.hasNext()) {
                        View sharedElementFirstOutViews = it.next();
                        Intrinsics.checkNotNullExpressionValue(sharedElementFirstOutViews, "sharedElementFirstOutViews");
                        View view = sharedElementFirstOutViews;
                        Log.v(FragmentManager.TAG, "View: " + view + " Name: " + ViewCompat.getTransitionName(view));
                    }
                    Log.v(FragmentManager.TAG, ">>>>> SharedElementLastInViews <<<<<");
                    Iterator<View> it2 = this.sharedElementLastInViews.iterator();
                    while (it2.hasNext()) {
                        View sharedElementLastInViews = it2.next();
                        Intrinsics.checkNotNullExpressionValue(sharedElementLastInViews, "sharedElementLastInViews");
                        View view2 = sharedElementLastInViews;
                        Log.v(FragmentManager.TAG, "View: " + view2 + " Name: " + ViewCompat.getTransitionName(view2));
                    }
                }
                executeTransition.invoke();
                this.transitionImpl.setNameOverridesReordered(container, this.sharedElementFirstOutViews, this.sharedElementLastInViews, inNames, this.sharedElementNameMapping);
                FragmentTransition.setViewVisibility(enteringViews, 0);
                this.transitionImpl.swapSharedElementTargets(this.sharedElementTransition, this.sharedElementFirstOutViews, this.sharedElementLastInViews);
            }

            @Override // androidx.fragment.app.SpecialEffectsController.Effect
            public void onCancel(ViewGroup container) {
                Intrinsics.checkNotNullParameter(container, "container");
                this.transitionSignal.cancel();
            }

            private final void captureTransitioningViews(ArrayList<View> transitioningViews, View view) {
                if (view instanceof ViewGroup) {
                    if (ViewGroupCompat.isTransitionGroup((ViewGroup) view)) {
                        if (!transitioningViews.contains(view)) {
                            transitioningViews.add(view);
                            return;
                        }
                        return;
                    }
                    int count = ((ViewGroup) view).getChildCount();
                    for (int i = 0; i < count; i++) {
                        View child = ((ViewGroup) view).getChildAt(i);
                        if (child.getVisibility() == 0) {
                            Intrinsics.checkNotNullExpressionValue(child, "child");
                            captureTransitioningViews(transitioningViews, child);
                        }
                    }
                    return;
                }
                if (!transitioningViews.contains(view)) {
                    transitioningViews.add(view);
                }
            }
        }

        /* compiled from: DefaultSpecialEffectsController.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$Api24Impl;", "", "()V", "totalDuration", "", "animatorSet", "Landroid/animation/AnimatorSet;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Api24Impl {
            public static final Api24Impl INSTANCE = new Api24Impl();

            private Api24Impl() {
            }

            public final long totalDuration(AnimatorSet animatorSet) {
                Intrinsics.checkNotNullParameter(animatorSet, "animatorSet");
                return animatorSet.getTotalDuration();
            }
        }

        /* compiled from: DefaultSpecialEffectsController.kt */
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$Api26Impl;", "", "()V", "reverse", "", "animatorSet", "Landroid/animation/AnimatorSet;", "setCurrentPlayTime", "time", "", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Api26Impl {
            public static final Api26Impl INSTANCE = new Api26Impl();

            private Api26Impl() {
            }

            public final void reverse(AnimatorSet animatorSet) {
                Intrinsics.checkNotNullParameter(animatorSet, "animatorSet");
                animatorSet.reverse();
            }

            public final void setCurrentPlayTime(AnimatorSet animatorSet, long time) {
                Intrinsics.checkNotNullParameter(animatorSet, "animatorSet");
                animatorSet.setCurrentPlayTime(time);
            }
        }
    }
