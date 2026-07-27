package com.google.android.libraries.places.widget.internal.ui;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.libraries.places.R;
import com.google.android.libraries.places.internal.zzev;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzo extends DefaultItemAnimator {
    private final List zza = new ArrayList();
    private final List zzb = new ArrayList();
    private final List zzc = new ArrayList();
    private final int zzd;

    public zzo(Resources resources) {
        this.zzd = resources.getDimensionPixelSize(R.dimen.places_autocomplete_vertical_dropdown);
    }

    private final void zzd(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        this.zzc.add(viewHolder);
        long moveDuration = getMoveDuration();
        int layoutPosition = viewHolder.getLayoutPosition();
        view.setTranslationY(-this.zzd);
        view.setAlpha(0.0f);
        ViewPropertyAnimator animate = view.animate();
        animate.cancel();
        animate.translationY(0.0f).alpha(1.0f).setDuration(133L).setInterpolator(new FastOutSlowInInterpolator()).setStartDelay(moveDuration + (layoutPosition * 67));
        animate.setListener(new zzn(this, view, viewHolder, animate)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zze() {
        if (isRunning()) {
            return;
        }
        dispatchAnimationsFinished();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzf(View view) {
        view.setAlpha(1.0f);
        view.setTranslationY(0.0f);
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.SimpleItemAnimator
    public final boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        try {
            endAnimation(viewHolder);
            viewHolder.itemView.setAlpha(0.0f);
            if (((zzs) viewHolder).zzb()) {
                this.zza.add(viewHolder);
                return true;
            }
            this.zzb.add(viewHolder);
            return true;
        } catch (Error | RuntimeException e) {
            zzev.zzb(e);
            throw e;
        }
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public final void endAnimation(RecyclerView.ViewHolder viewHolder) {
        try {
            super.endAnimation(viewHolder);
            if (this.zza.remove(viewHolder)) {
                zzf(viewHolder.itemView);
                dispatchAddFinished(viewHolder);
            }
            zze();
        } catch (Error | RuntimeException e) {
            zzev.zzb(e);
            throw e;
        }
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public final void endAnimations() {
        try {
            for (int size = this.zza.size() - 1; size >= 0; size--) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) this.zza.get(size);
                zzf(viewHolder.itemView);
                dispatchAddFinished(viewHolder);
                this.zza.remove(size);
            }
            List list = this.zzc;
            for (int size2 = list.size() - 1; size2 >= 0; size2--) {
                ((RecyclerView.ViewHolder) list.get(size2)).itemView.animate().cancel();
            }
            super.endAnimations();
        } catch (Error | RuntimeException e) {
            zzev.zzb(e);
            throw e;
        }
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public final boolean isRunning() {
        try {
            if (!super.isRunning() && this.zzb.isEmpty() && this.zza.isEmpty()) {
                return !this.zzc.isEmpty();
            }
            return true;
        } catch (Error | RuntimeException e) {
            zzev.zzb(e);
            throw e;
        }
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public final void runPendingAnimations() {
        try {
            Iterator it = this.zzb.iterator();
            while (it.hasNext()) {
                super.animateAdd((RecyclerView.ViewHolder) it.next());
            }
            this.zzb.clear();
            super.runPendingAnimations();
            if (this.zza.isEmpty()) {
                return;
            }
            ArrayList<RecyclerView.ViewHolder> arrayList = new ArrayList(this.zza);
            this.zza.clear();
            for (RecyclerView.ViewHolder viewHolder : arrayList) {
                View view = viewHolder.itemView;
                this.zzc.add(viewHolder);
                long moveDuration = getMoveDuration() + (viewHolder.getLayoutPosition() * 67);
                view.setTranslationY(-this.zzd);
                view.setAlpha(0.0f);
                ViewPropertyAnimator animate = view.animate();
                animate.cancel();
                animate.translationY(0.0f).alpha(1.0f).setDuration(133L).setInterpolator(new FastOutSlowInInterpolator()).setStartDelay(moveDuration);
                animate.setListener(new zzn(this, view, viewHolder, animate)).start();
            }
        } catch (Error | RuntimeException e) {
            zzev.zzb(e);
            throw e;
        }
    }
}
