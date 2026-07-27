package com.denzcoskun.imageslider.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import com.denzcoskun.imageslider.R;
import com.denzcoskun.imageslider.constants.ActionTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.interfaces.TouchListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.denzcoskun.imageslider.transformation.RoundedTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewPagerAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001BE\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eBO\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u0011J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\bH\u0016J\u000e\u0010 \u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rJ\u0018\u0010!\u001a\u00020\"2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\bH\u0016J\u0018\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u001eH\u0016J\u000e\u0010'\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010(\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/denzcoskun/imageslider/adapters/ViewPagerAdapter;", "Landroidx/viewpager/widget/PagerAdapter;", "context", "Landroid/content/Context;", "imageList", "", "Lcom/denzcoskun/imageslider/models/SlideModel;", "radius", "", "errorImage", "placeholder", "titleBackground", "textAlign", "", "(Landroid/content/Context;Ljava/util/List;IIIILjava/lang/String;)V", "scaleType", "Lcom/denzcoskun/imageslider/constants/ScaleTypes;", "(Landroid/content/Context;Ljava/util/List;IIIILcom/denzcoskun/imageslider/constants/ScaleTypes;Ljava/lang/String;)V", "itemClickListener", "Lcom/denzcoskun/imageslider/interfaces/ItemClickListener;", "layoutInflater", "Landroid/view/LayoutInflater;", "touchListener", "Lcom/denzcoskun/imageslider/interfaces/TouchListener;", "destroyItem", "", "container", "Landroid/view/ViewGroup;", "position", "object", "", "getCount", "getGravityFromAlign", "instantiateItem", "Landroid/view/View;", "isViewFromObject", "", "view", "obj", "setItemClickListener", "setTouchListener", "imageslider_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes16.dex */
public final class ViewPagerAdapter extends PagerAdapter {
    private int errorImage;
    private List<SlideModel> imageList;
    private ItemClickListener itemClickListener;
    private LayoutInflater layoutInflater;
    private int placeholder;
    private int radius;
    private ScaleTypes scaleType;
    private String textAlign;
    private int titleBackground;
    private TouchListener touchListener;

    public ViewPagerAdapter(Context context, List<SlideModel> imageList, int radius, int errorImage, int placeholder, int titleBackground, ScaleTypes scaleType, String textAlign) {
        Intrinsics.checkParameterIsNotNull(imageList, "imageList");
        Intrinsics.checkParameterIsNotNull(textAlign, "textAlign");
        this.radius = radius;
        this.errorImage = errorImage;
        this.placeholder = placeholder;
        this.titleBackground = titleBackground;
        this.scaleType = scaleType;
        this.textAlign = textAlign;
        this.imageList = imageList;
        if (context == null) {
            Intrinsics.throwNpe();
        }
        this.layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ViewPagerAdapter(Context context, List<SlideModel> imageList, int radius, int errorImage, int placeholder, int titleBackground, String textAlign) {
        this(context, imageList, radius, errorImage, placeholder, titleBackground, null, textAlign);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(imageList, "imageList");
        Intrinsics.checkParameterIsNotNull(textAlign, "textAlign");
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(obj, "obj");
        return Intrinsics.areEqual(view, obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        List<SlideModel> list = this.imageList;
        if (list == null) {
            Intrinsics.throwNpe();
        }
        return list.size();
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x013e, code lost:
    
        if (r5.get(r15).getScaleType() == com.denzcoskun.imageslider.constants.ScaleTypes.FIT) goto L63;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x017c  */
    @Override // androidx.viewpager.widget.PagerAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public View instantiateItem(ViewGroup container, final int position) {
        RequestCreator loader;
        Intrinsics.checkParameterIsNotNull(container, "container");
        LayoutInflater layoutInflater = this.layoutInflater;
        if (layoutInflater == null) {
            Intrinsics.throwNpe();
        }
        View itemView = layoutInflater.inflate(R.layout.pager_row, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.image_view);
        LinearLayout linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
        TextView textView = (TextView) itemView.findViewById(R.id.text_view);
        List<SlideModel> list = this.imageList;
        if (list == null) {
            Intrinsics.throwNpe();
        }
        if (list.get(position).getTitle() != null) {
            Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
            List<SlideModel> list2 = this.imageList;
            if (list2 == null) {
                Intrinsics.throwNpe();
            }
            textView.setText(list2.get(position).getTitle());
            linearLayout.setBackgroundResource(this.titleBackground);
            textView.setGravity(getGravityFromAlign(this.textAlign));
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "linearLayout");
            linearLayout.setGravity(getGravityFromAlign(this.textAlign));
        } else {
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "linearLayout");
            linearLayout.setVisibility(4);
        }
        List<SlideModel> list3 = this.imageList;
        if (list3 == null) {
            Intrinsics.throwNpe();
        }
        if (list3.get(position).getImageUrl() == null) {
            Picasso picasso = Picasso.get();
            List<SlideModel> list4 = this.imageList;
            if (list4 == null) {
                Intrinsics.throwNpe();
            }
            Integer imagePath = list4.get(position).getImagePath();
            if (imagePath == null) {
                Intrinsics.throwNpe();
            }
            loader = picasso.load(imagePath.intValue());
        } else {
            Picasso picasso2 = Picasso.get();
            List<SlideModel> list5 = this.imageList;
            if (list5 == null) {
                Intrinsics.throwNpe();
            }
            String imageUrl = list5.get(position).getImageUrl();
            if (imageUrl == null) {
                Intrinsics.throwNpe();
            }
            loader = picasso2.load(imageUrl);
        }
        if (this.scaleType == null || this.scaleType != ScaleTypes.CENTER_CROP) {
            List<SlideModel> list6 = this.imageList;
            if (list6 == null) {
                Intrinsics.throwNpe();
            }
            if (list6.get(position).getScaleType() != ScaleTypes.CENTER_CROP) {
                if (this.scaleType == null || this.scaleType != ScaleTypes.CENTER_INSIDE) {
                    List<SlideModel> list7 = this.imageList;
                    if (list7 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (list7.get(position).getScaleType() != ScaleTypes.CENTER_INSIDE) {
                        if (this.scaleType == null || this.scaleType != ScaleTypes.FIT) {
                            List<SlideModel> list8 = this.imageList;
                            if (list8 == null) {
                                Intrinsics.throwNpe();
                            }
                        }
                        loader.fit();
                        loader.transform(new RoundedTransformation(this.radius, 0, null, 4, null)).placeholder(this.placeholder).error(this.errorImage).into(imageView);
                        container.addView(itemView);
                        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.denzcoskun.imageslider.adapters.ViewPagerAdapter$instantiateItem$1
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View it) {
                                ItemClickListener itemClickListener;
                                itemClickListener = ViewPagerAdapter.this.itemClickListener;
                                if (itemClickListener != null) {
                                    itemClickListener.onItemSelected(position);
                                }
                            }
                        });
                        if (this.touchListener != null) {
                            if (imageView == null) {
                                Intrinsics.throwNpe();
                            }
                            imageView.setOnTouchListener(new View.OnTouchListener() { // from class: com.denzcoskun.imageslider.adapters.ViewPagerAdapter$instantiateItem$2
                                @Override // android.view.View.OnTouchListener
                                public final boolean onTouch(View v, MotionEvent event) {
                                    TouchListener touchListener;
                                    TouchListener touchListener2;
                                    TouchListener touchListener3;
                                    Intrinsics.checkExpressionValueIsNotNull(event, "event");
                                    switch (event.getAction()) {
                                        case 0:
                                            touchListener = ViewPagerAdapter.this.touchListener;
                                            if (touchListener == null) {
                                                Intrinsics.throwNpe();
                                            }
                                            touchListener.onTouched(ActionTypes.DOWN);
                                            break;
                                        case 1:
                                            touchListener2 = ViewPagerAdapter.this.touchListener;
                                            if (touchListener2 == null) {
                                                Intrinsics.throwNpe();
                                            }
                                            touchListener2.onTouched(ActionTypes.UP);
                                            break;
                                        case 2:
                                            touchListener3 = ViewPagerAdapter.this.touchListener;
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
                        Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
                        return itemView;
                    }
                }
                loader.fit().centerInside();
                loader.transform(new RoundedTransformation(this.radius, 0, null, 4, null)).placeholder(this.placeholder).error(this.errorImage).into(imageView);
                container.addView(itemView);
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.denzcoskun.imageslider.adapters.ViewPagerAdapter$instantiateItem$1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View it) {
                        ItemClickListener itemClickListener;
                        itemClickListener = ViewPagerAdapter.this.itemClickListener;
                        if (itemClickListener != null) {
                            itemClickListener.onItemSelected(position);
                        }
                    }
                });
                if (this.touchListener != null) {
                }
                Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
                return itemView;
            }
        }
        loader.fit().centerCrop();
        loader.transform(new RoundedTransformation(this.radius, 0, null, 4, null)).placeholder(this.placeholder).error(this.errorImage).into(imageView);
        container.addView(itemView);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.denzcoskun.imageslider.adapters.ViewPagerAdapter$instantiateItem$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                ItemClickListener itemClickListener;
                itemClickListener = ViewPagerAdapter.this.itemClickListener;
                if (itemClickListener != null) {
                    itemClickListener.onItemSelected(position);
                }
            }
        });
        if (this.touchListener != null) {
        }
        Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
        return itemView;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0022 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int getGravityFromAlign(String textAlign) {
        Intrinsics.checkParameterIsNotNull(textAlign, "textAlign");
        switch (textAlign.hashCode()) {
            case 77974012:
                if (textAlign.equals("RIGHT")) {
                    return 5;
                }
                return 3;
            case 1984282709:
                if (textAlign.equals("CENTER")) {
                    return 17;
                }
                break;
        }
    }

    public final void setItemClickListener(ItemClickListener itemClickListener) {
        Intrinsics.checkParameterIsNotNull(itemClickListener, "itemClickListener");
        this.itemClickListener = itemClickListener;
    }

    public final void setTouchListener(TouchListener touchListener) {
        Intrinsics.checkParameterIsNotNull(touchListener, "touchListener");
        this.touchListener = touchListener;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup container, int position, Object object) {
        Intrinsics.checkParameterIsNotNull(container, "container");
        Intrinsics.checkParameterIsNotNull(object, "object");
        container.removeView((RelativeLayout) object);
    }
}
