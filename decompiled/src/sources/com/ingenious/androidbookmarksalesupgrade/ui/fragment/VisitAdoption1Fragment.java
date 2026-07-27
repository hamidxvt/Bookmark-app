package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.ingenious.androidbookmarksalesupgrade.databinding.FragmentVisitAdoption1Binding;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.VisitAdoptionActivity;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitAdoptionViewModel;
import java.util.Calendar;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

/* compiled from: VisitAdoption1Fragment.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0013H\u0002J\u0010\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0013H\u0002R.\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/VisitAdoption1Fragment;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/fragment/BaseFragment;", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/FragmentVisitAdoption1Binding;", "<init>", "()V", "bindingInflater", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "", "getBindingInflater", "()Lkotlin/jvm/functions/Function3;", "sharedViewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitAdoptionViewModel;", "getSharedViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitAdoptionViewModel;", "sharedViewModel$delegate", "Lkotlin/Lazy;", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "showDatePickerDialog", "displaySelectedDate", "timeInMillis", "", "setCurrentDateInField", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class VisitAdoption1Fragment extends BaseFragment<FragmentVisitAdoption1Binding> {

    /* renamed from: sharedViewModel$delegate, reason: from kotlin metadata */
    private final Lazy sharedViewModel;

    public VisitAdoption1Fragment() {
        final VisitAdoption1Fragment $this$activityViewModels_u24default$iv = this;
        final Function0 extrasProducer$iv = null;
        this.sharedViewModel = FragmentViewModelLazyKt.createViewModelLazy($this$activityViewModels_u24default$iv, Reflection.getOrCreateKotlinClass(VisitAdoptionViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoption1Fragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return Fragment.this.requireActivity().getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoption1Fragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function0 = Function0.this;
                return (function0 == null || (creationExtras = (CreationExtras) function0.invoke()) == null) ? $this$activityViewModels_u24default$iv.requireActivity().getDefaultViewModelCreationExtras() : creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoption1Fragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return Fragment.this.requireActivity().getDefaultViewModelProviderFactory();
            }
        });
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.fragment.BaseFragment
    public Function3<LayoutInflater, ViewGroup, Boolean, FragmentVisitAdoption1Binding> getBindingInflater() {
        return VisitAdoption1Fragment$bindingInflater$1.INSTANCE;
    }

    private final VisitAdoptionViewModel getSharedViewModel() {
        return (VisitAdoptionViewModel) this.sharedViewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        setCurrentDateInField();
        getBinding().dateEt.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoption1Fragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VisitAdoption1Fragment.onViewCreated$lambda$0(view2);
            }
        });
        getBinding().btnContinue.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoption1Fragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VisitAdoption1Fragment.onViewCreated$lambda$1(VisitAdoption1Fragment.this, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(View it) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(VisitAdoption1Fragment this$0, View it) {
        String name = StringsKt.trim((CharSequence) this$0.getBinding().adoptionNameEt.getText().toString()).toString();
        String date = StringsKt.trim((CharSequence) this$0.getBinding().dateEt.getText().toString()).toString();
        String notes = StringsKt.trim((CharSequence) this$0.getBinding().noteEt.getText().toString()).toString();
        if (name.length() == 0) {
            this$0.getBinding().adoptionNameEt.setError("Name is required");
            return;
        }
        if (date.length() == 0) {
            this$0.getBinding().dateEt.setError("Date is required");
            return;
        }
        this$0.getSharedViewModel().getName().setValue(name);
        this$0.getSharedViewModel().getDate().setValue(date);
        this$0.getSharedViewModel().getNotes().setValue(notes);
        FragmentActivity activity = this$0.getActivity();
        VisitAdoptionActivity visitAdoptionActivity = activity instanceof VisitAdoptionActivity ? (VisitAdoptionActivity) activity : null;
        if (visitAdoptionActivity != null) {
            visitAdoptionActivity.next();
        }
    }

    private final void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.fragment.VisitAdoption1Fragment$$ExternalSyntheticLambda2
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                VisitAdoption1Fragment.showDatePickerDialog$lambda$2(calendar, this, datePicker, i, i2, i3);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5));
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDatePickerDialog$lambda$2(Calendar $calendar, VisitAdoption1Fragment this$0, DatePicker datePicker, int year, int month, int dayOfMonth) {
        $calendar.set(year, month, dayOfMonth);
        this$0.displaySelectedDate($calendar.getTimeInMillis());
    }

    private final void displaySelectedDate(long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        String dateString = DateFormat.format("MMMM dd, yyyy", calendar.getTime()).toString();
        getBinding().dateEt.setText(dateString);
    }

    private final void setCurrentDateInField() {
        Calendar calendar = Calendar.getInstance();
        displaySelectedDate(calendar.getTimeInMillis());
    }
}
