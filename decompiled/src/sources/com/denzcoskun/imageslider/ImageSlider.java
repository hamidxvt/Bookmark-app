package com.denzcoskun.imageslider;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import com.denzcoskun.imageslider.adapters.ViewPagerAdapter;
import com.denzcoskun.imageslider.constants.ActionTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemChangeListener;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.interfaces.TouchListener;
import com.denzcoskun.imageslider.models.SlideModel;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageSlider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010)\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0016J\u0010\u0010*\u001a\u00020+2\u0006\u0010\u001b\u001a\u00020\u000eH\u0002J\u0014\u0010,\u001a\u00020+2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.J \u0010,\u001a\u00020+2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.2\n\b\u0002\u00100\u001a\u0004\u0018\u000101J\u000e\u00102\u001a\u00020+2\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u00103\u001a\u00020+2\u0006\u00104\u001a\u000205J\u000e\u00106\u001a\u00020+2\u0006\u0010\"\u001a\u00020#J\u0010\u00107\u001a\u00020+2\u0006\u00108\u001a\u00020\u0007H\u0002J\u0010\u00109\u001a\u00020+2\b\b\u0002\u0010:\u001a\u00020\u000eJ\u0006\u0010;\u001a\u00020+R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lcom/denzcoskun/imageslider/ImageSlider;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "autoCycle", "", "cornerRadius", "currentPage", "delay", "", "dots", "", "Landroid/widget/ImageView;", "[Landroid/widget/ImageView;", "errorImage", "imageCount", "indicatorAlign", "", "itemChangeListener", "Lcom/denzcoskun/imageslider/interfaces/ItemChangeListener;", "pagerDots", "Landroid/widget/LinearLayout;", TypedValues.CycleType.S_WAVE_PERIOD, "placeholder", "selectedDot", "swipeTimer", "Ljava/util/Timer;", "textAlign", "titleBackground", "touchListener", "Lcom/denzcoskun/imageslider/interfaces/TouchListener;", "unselectedDot", "viewPager", "Landroidx/viewpager/widget/ViewPager;", "viewPagerAdapter", "Lcom/denzcoskun/imageslider/adapters/ViewPagerAdapter;", "getGravityFromAlign", "scheduleTimer", "", "setImageList", "imageList", "", "Lcom/denzcoskun/imageslider/models/SlideModel;", "scaleType", "Lcom/denzcoskun/imageslider/constants/ScaleTypes;", "setItemChangeListener", "setItemClickListener", "itemClickListener", "Lcom/denzcoskun/imageslider/interfaces/ItemClickListener;", "setTouchListener", "setupDots", "size", "startSliding", "changeablePeriod", "stopSliding", "imageslider_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes16.dex */
public final class ImageSlider extends RelativeLayout {
    private boolean autoCycle;
    private int cornerRadius;
    private int currentPage;
    private long delay;
    private ImageView[] dots;
    private int errorImage;
    private int imageCount;
    private String indicatorAlign;
    private ItemChangeListener itemChangeListener;
    private LinearLayout pagerDots;
    private long period;
    private int placeholder;
    private int selectedDot;
    private Timer swipeTimer;
    private String textAlign;
    private int titleBackground;
    private TouchListener touchListener;
    private int unselectedDot;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    public ImageSlider(Context context) {
        this(context, null, 0, 6, null);
    }

    public ImageSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImageSlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.textAlign = "LEFT";
        this.indicatorAlign = "CENTER";
        this.swipeTimer = new Timer();
        LayoutInflater.from(getContext()).inflate(R.layout.image_slider, (ViewGroup) this, true);
        this.viewPager = (ViewPager) findViewById(R.id.view_pager);
        this.pagerDots = (LinearLayout) findViewById(R.id.pager_dots);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ImageSlider, defStyleAttr, defStyleAttr);
        this.cornerRadius = typedArray.getInt(R.styleable.ImageSlider_iss_corner_radius, 1);
        this.period = typedArray.getInt(R.styleable.ImageSlider_iss_period, 1000);
        this.delay = typedArray.getInt(R.styleable.ImageSlider_iss_delay, 1000);
        this.autoCycle = typedArray.getBoolean(R.styleable.ImageSlider_iss_auto_cycle, false);
        this.placeholder = typedArray.getResourceId(R.styleable.ImageSlider_iss_placeholder, R.drawable.loading);
        this.errorImage = typedArray.getResourceId(R.styleable.ImageSlider_iss_error_image, R.drawable.error);
        this.selectedDot = typedArray.getResourceId(R.styleable.ImageSlider_iss_selected_dot, R.drawable.default_selected_dot);
        this.unselectedDot = typedArray.getResourceId(R.styleable.ImageSlider_iss_unselected_dot, R.drawable.default_unselected_dot);
        this.titleBackground = typedArray.getResourceId(R.styleable.ImageSlider_iss_title_background, R.drawable.gradient);
        if (typedArray.getString(R.styleable.ImageSlider_iss_text_align) != null) {
            String string = typedArray.getString(R.styleable.ImageSlider_iss_text_align);
            Intrinsics.checkExpressionValueIsNotNull(string, "typedArray.getString(R.s…ageSlider_iss_text_align)");
            this.textAlign = string;
        }
        if (typedArray.getString(R.styleable.ImageSlider_iss_indicator_align) != null) {
            String string2 = typedArray.getString(R.styleable.ImageSlider_iss_indicator_align);
            Intrinsics.checkExpressionValueIsNotNull(string2, "typedArray.getString(R.s…ider_iss_indicator_align)");
            this.indicatorAlign = string2;
        }
        if (this.touchListener != null) {
            ViewPager viewPager = this.viewPager;
            if (viewPager == null) {
                Intrinsics.throwNpe();
            }
            viewPager.setOnTouchListener(new View.OnTouchListener() { // from class: com.denzcoskun.imageslider.ImageSlider.1
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View v, MotionEvent event) {
                    Intrinsics.checkExpressionValueIsNotNull(event, "event");
                    switch (event.getAction()) {
                        case 0:
                            TouchListener touchListener = ImageSlider.this.touchListener;
                            if (touchListener == null) {
                                Intrinsics.throwNpe();
                            }
                            touchListener.onTouched(ActionTypes.DOWN);
                            break;
                        case 1:
                            TouchListener touchListener2 = ImageSlider.this.touchListener;
                            if (touchListener2 == null) {
                                Intrinsics.throwNpe();
                            }
                            touchListener2.onTouched(ActionTypes.UP);
                            break;
                        case 2:
                            TouchListener touchListener3 = ImageSlider.this.touchListener;
                            if (touchListener3 == null) {
                                Intrinsics.throwNpe();
                            }
                            touchListener3.onTouched(ActionTypes.MOVE);
                            break;
                    }
                    return false;
                }
            });
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ ImageSlider(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, attributeSet, (i2 & 4) != 0 ? 0 : i);
        if ((i2 & 2) != 0) {
            attributeSet = null;
        }
    }

    public final void setImageList(List<SlideModel> imageList) {
        Intrinsics.checkParameterIsNotNull(imageList, "imageList");
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        this.viewPagerAdapter = new ViewPagerAdapter(context, imageList, this.cornerRadius, this.errorImage, this.placeholder, this.titleBackground, this.textAlign);
        ViewPager viewPager = this.viewPager;
        if (viewPager == null) {
            Intrinsics.throwNpe();
        }
        viewPager.setAdapter(this.viewPagerAdapter);
        this.imageCount = imageList.size();
        if (!imageList.isEmpty()) {
            setupDots(imageList.size());
            if (this.autoCycle) {
                stopSliding();
                startSliding$default(this, 0L, 1, null);
            }
        }
    }

    public static /* synthetic */ void setImageList$default(ImageSlider imageSlider, List list, ScaleTypes scaleTypes, int i, Object obj) {
        if ((i & 2) != 0) {
            scaleTypes = null;
        }
        imageSlider.setImageList(list, scaleTypes);
    }

    public final void setImageList(List<SlideModel> imageList, ScaleTypes scaleType) {
        Intrinsics.checkParameterIsNotNull(imageList, "imageList");
        this.viewPagerAdapter = new ViewPagerAdapter(getContext(), imageList, this.cornerRadius, this.errorImage, this.placeholder, this.titleBackground, scaleType, this.textAlign);
        ViewPager viewPager = this.viewPager;
        if (viewPager == null) {
            Intrinsics.throwNpe();
        }
        viewPager.setAdapter(this.viewPagerAdapter);
        this.imageCount = imageList.size();
        if (!imageList.isEmpty()) {
            setupDots(imageList.size());
            if (this.autoCycle) {
                startSliding$default(this, 0L, 1, null);
            }
        }
    }

    private final void setupDots(int size) {
        System.out.println((Object) this.indicatorAlign);
        LinearLayout linearLayout = this.pagerDots;
        if (linearLayout == null) {
            Intrinsics.throwNpe();
        }
        linearLayout.setGravity(getGravityFromAlign(this.indicatorAlign));
        LinearLayout linearLayout2 = this.pagerDots;
        if (linearLayout2 == null) {
            Intrinsics.throwNpe();
        }
        linearLayout2.removeAllViews();
        this.dots = new ImageView[size];
        for (int i = 0; i < size; i++) {
            ImageView[] imageViewArr = this.dots;
            if (imageViewArr == null) {
                Intrinsics.throwNpe();
            }
            imageViewArr[i] = new ImageView(getContext());
            ImageView[] imageViewArr2 = this.dots;
            if (imageViewArr2 == null) {
                Intrinsics.throwNpe();
            }
            ImageView imageView = imageViewArr2[i];
            if (imageView == null) {
                Intrinsics.throwNpe();
            }
            imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), this.unselectedDot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
            params.setMargins(8, 0, 8, 0);
            LinearLayout linearLayout3 = this.pagerDots;
            if (linearLayout3 == null) {
                Intrinsics.throwNpe();
            }
            ImageView[] imageViewArr3 = this.dots;
            if (imageViewArr3 == null) {
                Intrinsics.throwNpe();
            }
            linearLayout3.addView(imageViewArr3[i], params);
        }
        ImageView[] imageViewArr4 = this.dots;
        if (imageViewArr4 == null) {
            Intrinsics.throwNpe();
        }
        ImageView imageView2 = imageViewArr4[0];
        if (imageView2 == null) {
            Intrinsics.throwNpe();
        }
        imageView2.setImageDrawable(ContextCompat.getDrawable(getContext(), this.selectedDot));
        ViewPager viewPager = this.viewPager;
        if (viewPager == null) {
            Intrinsics.throwNpe();
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.denzcoskun.imageslider.ImageSlider$setupDots$1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                ImageView[] imageViewArr5;
                ImageView[] imageViewArr6;
                int i2;
                ItemChangeListener itemChangeListener;
                ItemChangeListener itemChangeListener2;
                int i3;
                ImageSlider.this.currentPage = position;
                imageViewArr5 = ImageSlider.this.dots;
                if (imageViewArr5 == null) {
                    Intrinsics.throwNpe();
                }
                for (ImageView dot : imageViewArr5) {
                    if (dot == null) {
                        Intrinsics.throwNpe();
                    }
                    Context context = ImageSlider.this.getContext();
                    i3 = ImageSlider.this.unselectedDot;
                    dot.setImageDrawable(ContextCompat.getDrawable(context, i3));
                }
                imageViewArr6 = ImageSlider.this.dots;
                if (imageViewArr6 == null) {
                    Intrinsics.throwNpe();
                }
                ImageView imageView3 = imageViewArr6[position];
                if (imageView3 == null) {
                    Intrinsics.throwNpe();
                }
                Context context2 = ImageSlider.this.getContext();
                i2 = ImageSlider.this.selectedDot;
                imageView3.setImageDrawable(ContextCompat.getDrawable(context2, i2));
                itemChangeListener = ImageSlider.this.itemChangeListener;
                if (itemChangeListener != null) {
                    itemChangeListener2 = ImageSlider.this.itemChangeListener;
                    if (itemChangeListener2 == null) {
                        Intrinsics.throwNpe();
                    }
                    itemChangeListener2.onItemChanged(position);
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public static /* synthetic */ void startSliding$default(ImageSlider imageSlider, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = imageSlider.period;
        }
        imageSlider.startSliding(j);
    }

    public final void startSliding(long changeablePeriod) {
        stopSliding();
        scheduleTimer(changeablePeriod);
    }

    public final void stopSliding() {
        this.swipeTimer.cancel();
        this.swipeTimer.purge();
    }

    private final void scheduleTimer(long period) {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() { // from class: com.denzcoskun.imageslider.ImageSlider$scheduleTimer$update$1
            @Override // java.lang.Runnable
            public final void run() {
                int i;
                int i2;
                ViewPager viewPager;
                int i3;
                i = ImageSlider.this.currentPage;
                i2 = ImageSlider.this.imageCount;
                if (i == i2) {
                    ImageSlider.this.currentPage = 0;
                }
                viewPager = ImageSlider.this.viewPager;
                if (viewPager == null) {
                    Intrinsics.throwNpe();
                }
                ImageSlider imageSlider = ImageSlider.this;
                i3 = imageSlider.currentPage;
                imageSlider.currentPage = i3 + 1;
                viewPager.setCurrentItem(i3, true);
            }
        };
        this.swipeTimer = new Timer();
        this.swipeTimer.schedule(new TimerTask() { // from class: com.denzcoskun.imageslider.ImageSlider$scheduleTimer$1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                handler.post(update);
            }
        }, this.delay, period);
    }

    public final void setItemClickListener(ItemClickListener itemClickListener) {
        Intrinsics.checkParameterIsNotNull(itemClickListener, "itemClickListener");
        ViewPagerAdapter viewPagerAdapter = this.viewPagerAdapter;
        if (viewPagerAdapter != null) {
            viewPagerAdapter.setItemClickListener(itemClickListener);
        }
    }

    public final void setItemChangeListener(ItemChangeListener itemChangeListener) {
        Intrinsics.checkParameterIsNotNull(itemChangeListener, "itemChangeListener");
        this.itemChangeListener = itemChangeListener;
    }

    public final void setTouchListener(TouchListener touchListener) {
        Intrinsics.checkParameterIsNotNull(touchListener, "touchListener");
        this.touchListener = touchListener;
        ViewPagerAdapter viewPagerAdapter = this.viewPagerAdapter;
        if (viewPagerAdapter == null) {
            Intrinsics.throwNpe();
        }
        viewPagerAdapter.setTouchListener(touchListener);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0021 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int getGravityFromAlign(String textAlign) {
        Intrinsics.checkParameterIsNotNull(textAlign, "textAlign");
        switch (textAlign.hashCode()) {
            case 2332679:
                if (textAlign.equals("LEFT")) {
                    return 3;
                }
                return 17;
            case 77974012:
                if (textAlign.equals("RIGHT")) {
                    return 5;
                }
                break;
        }
    }
}
