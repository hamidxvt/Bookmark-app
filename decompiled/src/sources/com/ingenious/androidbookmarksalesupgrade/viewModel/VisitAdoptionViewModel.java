package com.ingenious.androidbookmarksalesupgrade.viewModel;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ingenious.androidbookmarksalesupgrade.model.response.AdoptionBooksData;
import java.util.List;
import kotlin.Metadata;

/* compiled from: VisitAdoptionViewModel.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\tR\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\tR\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\tR\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\tR\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\tR\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\t¨\u0006\u001c"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/viewModel/VisitAdoptionViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "selectedSegmentsData", "Landroidx/lifecycle/MutableLiveData;", "", "", "getSelectedSegmentsData", "()Landroidx/lifecycle/MutableLiveData;", "selectedGradesData", "getSelectedGradesData", "selectedSubjectsData", "getSelectedSubjectsData", "selectedGradeNames", "", "getSelectedGradeNames", "selectedSubjectNames", "getSelectedSubjectNames", "selectedBooksList", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionBooksData;", "getSelectedBooksList", AppMeasurementSdk.ConditionalUserProperty.NAME, "getName", "date", "getDate", "notes", "getNotes", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class VisitAdoptionViewModel extends ViewModel {
    private final MutableLiveData<List<Integer>> selectedSegmentsData = new MutableLiveData<>();
    private final MutableLiveData<List<Integer>> selectedGradesData = new MutableLiveData<>();
    private final MutableLiveData<List<Integer>> selectedSubjectsData = new MutableLiveData<>();
    private final MutableLiveData<List<String>> selectedGradeNames = new MutableLiveData<>();
    private final MutableLiveData<List<String>> selectedSubjectNames = new MutableLiveData<>();
    private final MutableLiveData<List<AdoptionBooksData>> selectedBooksList = new MutableLiveData<>();
    private final MutableLiveData<String> name = new MutableLiveData<>();
    private final MutableLiveData<String> date = new MutableLiveData<>();
    private final MutableLiveData<String> notes = new MutableLiveData<>();

    public final MutableLiveData<List<Integer>> getSelectedSegmentsData() {
        return this.selectedSegmentsData;
    }

    public final MutableLiveData<List<Integer>> getSelectedGradesData() {
        return this.selectedGradesData;
    }

    public final MutableLiveData<List<Integer>> getSelectedSubjectsData() {
        return this.selectedSubjectsData;
    }

    public final MutableLiveData<List<String>> getSelectedGradeNames() {
        return this.selectedGradeNames;
    }

    public final MutableLiveData<List<String>> getSelectedSubjectNames() {
        return this.selectedSubjectNames;
    }

    public final MutableLiveData<List<AdoptionBooksData>> getSelectedBooksList() {
        return this.selectedBooksList;
    }

    public final MutableLiveData<String> getName() {
        return this.name;
    }

    public final MutableLiveData<String> getDate() {
        return this.date;
    }

    public final MutableLiveData<String> getNotes() {
        return this.notes;
    }
}
