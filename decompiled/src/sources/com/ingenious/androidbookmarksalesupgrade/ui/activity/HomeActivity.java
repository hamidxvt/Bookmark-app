package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityHomeBinding;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HomeActivity.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u0007H\u0002J\b\u0010\u0010\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/HomeActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityHomeBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "safeNavigate", "navController", "Landroidx/navigation/NavController;", "destinationId", "", "resetIcons", "resetTextColors", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityHomeBinding.inflate(getLayoutInflater());
        ActivityHomeBinding activityHomeBinding = this.binding;
        ActivityHomeBinding activityHomeBinding2 = null;
        if (activityHomeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHomeBinding = null;
        }
        setContentView(activityHomeBinding.getRoot());
        ExtensionKt.belowStatusBarText(this);
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type androidx.navigation.fragment.NavHostFragment");
        NavHostFragment navHostFragment = (NavHostFragment) findFragmentById;
        final NavController navController = navHostFragment.getNavController();
        ActivityHomeBinding activityHomeBinding3 = this.binding;
        if (activityHomeBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHomeBinding3 = null;
        }
        activityHomeBinding3.navHome.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.HomeActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeActivity.onCreate$lambda$0(HomeActivity.this, navController, view);
            }
        });
        ActivityHomeBinding activityHomeBinding4 = this.binding;
        if (activityHomeBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHomeBinding4 = null;
        }
        activityHomeBinding4.navInventory.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.HomeActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeActivity.onCreate$lambda$1(HomeActivity.this, navController, view);
            }
        });
        ActivityHomeBinding activityHomeBinding5 = this.binding;
        if (activityHomeBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHomeBinding5 = null;
        }
        activityHomeBinding5.navCustomers.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.HomeActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeActivity.onCreate$lambda$2(HomeActivity.this, navController, view);
            }
        });
        ActivityHomeBinding activityHomeBinding6 = this.binding;
        if (activityHomeBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHomeBinding6 = null;
        }
        activityHomeBinding6.navActivity.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.HomeActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeActivity.onCreate$lambda$3(HomeActivity.this, navController, view);
            }
        });
        ActivityHomeBinding activityHomeBinding7 = this.binding;
        if (activityHomeBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityHomeBinding2 = activityHomeBinding7;
        }
        activityHomeBinding2.navPerformance.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.HomeActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeActivity.onCreate$lambda$4(HomeActivity.this, navController, view);
            }
        });
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.HomeActivity$$ExternalSyntheticLambda5
            @Override // androidx.navigation.NavController.OnDestinationChangedListener
            public final void onDestinationChanged(NavController navController2, NavDestination navDestination, Bundle bundle) {
                HomeActivity.onCreate$lambda$5(HomeActivity.this, navController2, navDestination, bundle);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(HomeActivity this$0, NavController $navController, View it) {
        this$0.safeNavigate($navController, R.id.homeFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(HomeActivity this$0, NavController $navController, View it) {
        this$0.safeNavigate($navController, R.id.inventoryFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(HomeActivity this$0, NavController $navController, View it) {
        this$0.safeNavigate($navController, R.id.customersFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3(HomeActivity this$0, NavController $navController, View it) {
        this$0.safeNavigate($navController, R.id.recentActivityFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$4(HomeActivity this$0, NavController $navController, View it) {
        this$0.safeNavigate($navController, R.id.performanceFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5(HomeActivity this$0, NavController navController, NavDestination destination, Bundle bundle) {
        Intrinsics.checkNotNullParameter(navController, "<unused var>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        this$0.resetIcons();
        this$0.resetTextColors();
        int id = destination.getId();
        ActivityHomeBinding activityHomeBinding = null;
        if (id == R.id.homeFragment) {
            ActivityHomeBinding activityHomeBinding2 = this$0.binding;
            if (activityHomeBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityHomeBinding2 = null;
            }
            activityHomeBinding2.iconHome.setImageResource(R.drawable.fill_home);
            ActivityHomeBinding activityHomeBinding3 = this$0.binding;
            if (activityHomeBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityHomeBinding = activityHomeBinding3;
            }
            activityHomeBinding.homeTxt.setTextColor(this$0.getColor(R.color.app_color));
            return;
        }
        if (id == R.id.inventoryFragment) {
            ActivityHomeBinding activityHomeBinding4 = this$0.binding;
            if (activityHomeBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityHomeBinding4 = null;
            }
            activityHomeBinding4.iconInventory.setImageResource(R.drawable.fill_inventory);
            ActivityHomeBinding activityHomeBinding5 = this$0.binding;
            if (activityHomeBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityHomeBinding = activityHomeBinding5;
            }
            activityHomeBinding.inventoryTxt.setTextColor(this$0.getColor(R.color.app_color));
            return;
        }
        if (id == R.id.customersFragment) {
            ActivityHomeBinding activityHomeBinding6 = this$0.binding;
            if (activityHomeBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityHomeBinding6 = null;
            }
            activityHomeBinding6.iconCustomers.setImageResource(R.drawable.fill_customer);
            ActivityHomeBinding activityHomeBinding7 = this$0.binding;
            if (activityHomeBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityHomeBinding = activityHomeBinding7;
            }
            activityHomeBinding.customerTxt.setTextColor(this$0.getColor(R.color.app_color));
            return;
        }
        if (id == R.id.recentActivityFragment) {
            ActivityHomeBinding activityHomeBinding8 = this$0.binding;
            if (activityHomeBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityHomeBinding8 = null;
            }
            activityHomeBinding8.activityIcon.setImageResource(R.drawable.fill_activity);
            ActivityHomeBinding activityHomeBinding9 = this$0.binding;
            if (activityHomeBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityHomeBinding = activityHomeBinding9;
            }
            activityHomeBinding.activityTxt.setTextColor(this$0.getColor(R.color.app_color));
            return;
        }
        if (id == R.id.performanceFragment) {
            ActivityHomeBinding activityHomeBinding10 = this$0.binding;
            if (activityHomeBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityHomeBinding10 = null;
            }
            activityHomeBinding10.performance.setImageResource(R.drawable.fill_performance);
            ActivityHomeBinding activityHomeBinding11 = this$0.binding;
            if (activityHomeBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityHomeBinding = activityHomeBinding11;
            }
            activityHomeBinding.performanceTxt.setTextColor(this$0.getColor(R.color.app_color));
        }
    }

    private final void safeNavigate(NavController navController, int destinationId) {
        NavDestination currentDestination = navController.getCurrentDestination();
        boolean z = false;
        if (currentDestination != null && currentDestination.getId() == destinationId) {
            z = true;
        }
        if (!z) {
            navController.navigate(destinationId);
        }
    }

    private final void resetIcons() {
        ActivityHomeBinding activityHomeBinding = this.binding;
        ActivityHomeBinding activityHomeBinding2 = null;
        if (activityHomeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHomeBinding = null;
        }
        activityHomeBinding.iconHome.setImageResource(R.drawable.ic_home);
        ActivityHomeBinding activityHomeBinding3 = this.binding;
        if (activityHomeBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHomeBinding3 = null;
        }
        activityHomeBinding3.iconInventory.setImageResource(R.drawable.ic_inventory);
        ActivityHomeBinding activityHomeBinding4 = this.binding;
        if (activityHomeBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHomeBinding4 = null;
        }
        activityHomeBinding4.iconCustomers.setImageResource(R.drawable.customers_icon);
        ActivityHomeBinding activityHomeBinding5 = this.binding;
        if (activityHomeBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHomeBinding5 = null;
        }
        activityHomeBinding5.activityIcon.setImageResource(R.drawable.menu_activity_select);
        ActivityHomeBinding activityHomeBinding6 = this.binding;
        if (activityHomeBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityHomeBinding2 = activityHomeBinding6;
        }
        activityHomeBinding2.performance.setImageResource(R.drawable.ic_performance);
    }

    private final void resetTextColors() {
        ActivityHomeBinding activityHomeBinding = this.binding;
        ActivityHomeBinding activityHomeBinding2 = null;
        if (activityHomeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHomeBinding = null;
        }
        activityHomeBinding.homeTxt.setTextColor(getColor(R.color.gray));
        ActivityHomeBinding activityHomeBinding3 = this.binding;
        if (activityHomeBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHomeBinding3 = null;
        }
        activityHomeBinding3.inventoryTxt.setTextColor(getColor(R.color.gray));
        ActivityHomeBinding activityHomeBinding4 = this.binding;
        if (activityHomeBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHomeBinding4 = null;
        }
        activityHomeBinding4.customerTxt.setTextColor(getColor(R.color.gray));
        ActivityHomeBinding activityHomeBinding5 = this.binding;
        if (activityHomeBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHomeBinding5 = null;
        }
        activityHomeBinding5.activityTxt.setTextColor(getColor(R.color.gray));
        ActivityHomeBinding activityHomeBinding6 = this.binding;
        if (activityHomeBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityHomeBinding2 = activityHomeBinding6;
        }
        activityHomeBinding2.performanceTxt.setTextColor(getColor(R.color.gray));
    }
}
