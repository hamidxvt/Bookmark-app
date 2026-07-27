package com.ingenious.androidbookmarksalesupgrade.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.google.android.material.chip.Chip;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.bindingAdapter.AppBindingAdapters;
import com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.response.ProfileData;
import io.github.florent37.shapeofview.shapes.CircleView;

/* loaded from: classes13.dex */
public class ActivityProfileBindingImpl extends ActivityProfileBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private final View.OnClickListener mCallback1;
    private final View.OnClickListener mCallback2;
    private long mDirtyFlags;
    private final TextView mboundView1;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView12;
    private final TextView mboundView13;
    private final LinearLayout mboundView14;
    private final TextView mboundView2;
    private final Chip mboundView3;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;
    private final TextView mboundView8;
    private final TextView mboundView9;

    static {
        sViewsWithIds.put(R.id.back, 15);
        sViewsWithIds.put(R.id.imgCircleProfile, 16);
        sViewsWithIds.put(R.id.requestToEdit, 17);
    }

    public ActivityProfileBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }

    private ActivityProfileBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[15], (CircleView) bindings[16], (ImageView) bindings[4], (NestedScrollView) bindings[0], (Chip) bindings[17]);
        this.mDirtyFlags = -1L;
        this.imgProfile.setTag(null);
        this.main.setTag(null);
        this.mboundView1 = (TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView10 = (TextView) bindings[10];
        this.mboundView10.setTag(null);
        this.mboundView11 = (TextView) bindings[11];
        this.mboundView11.setTag(null);
        this.mboundView12 = (TextView) bindings[12];
        this.mboundView12.setTag(null);
        this.mboundView13 = (TextView) bindings[13];
        this.mboundView13.setTag(null);
        this.mboundView14 = (LinearLayout) bindings[14];
        this.mboundView14.setTag(null);
        this.mboundView2 = (TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (Chip) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView5 = (TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (TextView) bindings[6];
        this.mboundView6.setTag(null);
        this.mboundView7 = (TextView) bindings[7];
        this.mboundView7.setTag(null);
        this.mboundView8 = (TextView) bindings[8];
        this.mboundView8.setTag(null);
        this.mboundView9 = (TextView) bindings[9];
        this.mboundView9.setTag(null);
        setRootTag(root);
        this.mCallback2 = new OnClickListener(this, 2);
        this.mCallback1 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (2 == variableId) {
            setItem((ProfileData) variable);
            return true;
        }
        if (3 == variableId) {
            setListener((GenericListeners) variable);
            return true;
        }
        return false;
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityProfileBinding
    public void setItem(ProfileData Item) {
        this.mItem = Item;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.databinding.ActivityProfileBinding
    public void setListener(GenericListeners Listener) {
        this.mListener = Listener;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long dirtyFlags;
        String itemCity;
        String itemNumberOfInvoices;
        String itemName;
        String itemImage;
        String itemEmail;
        String itemOutstandingBalance;
        String itemCity2;
        String itemOutstandingBalance2;
        synchronized (this) {
            dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean itemCityJavaLangObjectNullItemCityEqualsIgnoreCaseJavaLangStringKarachiBooleanFalse = false;
        ProfileData item = this.mItem;
        String itemRole = null;
        String itemPhone = null;
        String stringValueOfItemId = null;
        String itemAddress = null;
        String itemCreatedAt = null;
        boolean itemCityEqualsIgnoreCaseJavaLangStringKarachi = false;
        boolean itemCityJavaLangObjectNull = false;
        GenericListeners genericListeners = this.mListener;
        int itemCityJavaLangObjectNullItemCityEqualsIgnoreCaseJavaLangStringKarachiBooleanFalseViewVISIBLEViewINVISIBLE = 0;
        String itemCity3 = null;
        String itemNumberOfInvoices2 = null;
        String itemName2 = null;
        String itemImage2 = null;
        Integer itemId = null;
        String itemEmail2 = null;
        String itemOutstandingBalance3 = null;
        if ((dirtyFlags & 5) == 0) {
            itemCity = null;
            itemNumberOfInvoices = null;
            itemName = null;
            itemImage = null;
            itemEmail = null;
            itemOutstandingBalance = null;
        } else {
            if (item != null) {
                itemRole = item.getRole();
                itemPhone = item.getPhone();
                itemAddress = item.getAddress();
                itemCreatedAt = item.getCreatedAt();
                itemCity3 = item.getCity();
                itemNumberOfInvoices2 = item.getNumberOfInvoices();
                itemName2 = item.getName();
                itemImage2 = item.getImage();
                itemId = item.getId();
                itemEmail2 = item.getEmail();
                itemOutstandingBalance3 = item.getOutstandingBalance();
            }
            itemCityJavaLangObjectNull = itemCity3 != null;
            int androidxDatabindingViewDataBindingSafeUnboxItemId = ViewDataBinding.safeUnbox(itemId);
            if ((dirtyFlags & 5) != 0) {
                if (itemCityJavaLangObjectNull) {
                    dirtyFlags |= 16;
                } else {
                    dirtyFlags |= 8;
                }
            }
            stringValueOfItemId = String.valueOf(androidxDatabindingViewDataBindingSafeUnboxItemId);
            itemCity = itemCity3;
            itemNumberOfInvoices = itemNumberOfInvoices2;
            itemName = itemName2;
            itemImage = itemImage2;
            itemEmail = itemEmail2;
            itemOutstandingBalance = itemOutstandingBalance3;
        }
        if ((dirtyFlags & 16) == 0) {
            itemCity2 = itemCity;
        } else {
            itemCity2 = itemCity;
            if (itemCity2 != null) {
                itemCityEqualsIgnoreCaseJavaLangStringKarachi = itemCity2.equalsIgnoreCase("Karachi");
            }
        }
        if ((dirtyFlags & 5) != 0) {
            itemCityJavaLangObjectNullItemCityEqualsIgnoreCaseJavaLangStringKarachiBooleanFalse = itemCityJavaLangObjectNull ? itemCityEqualsIgnoreCaseJavaLangStringKarachi : false;
            if ((dirtyFlags & 5) != 0) {
                if (itemCityJavaLangObjectNullItemCityEqualsIgnoreCaseJavaLangStringKarachiBooleanFalse) {
                    dirtyFlags |= 64;
                } else {
                    dirtyFlags |= 32;
                }
            }
            itemCityJavaLangObjectNullItemCityEqualsIgnoreCaseJavaLangStringKarachiBooleanFalseViewVISIBLEViewINVISIBLE = itemCityJavaLangObjectNullItemCityEqualsIgnoreCaseJavaLangStringKarachiBooleanFalse ? 0 : 4;
        }
        if ((dirtyFlags & 5) != 0) {
            AppBindingAdapters.setImageUsingGlide(this.imgProfile, itemImage);
            String itemImage3 = itemName;
            TextViewBindingAdapter.setText(this.mboundView1, itemImage3);
            TextViewBindingAdapter.setText(this.mboundView10, itemCreatedAt);
            TextViewBindingAdapter.setText(this.mboundView11, itemRole);
            String itemName3 = itemNumberOfInvoices;
            TextViewBindingAdapter.setText(this.mboundView12, itemName3);
            String itemNumberOfInvoices3 = itemOutstandingBalance;
            TextViewBindingAdapter.setText(this.mboundView13, itemNumberOfInvoices3);
            TextViewBindingAdapter.setText(this.mboundView2, stringValueOfItemId);
            TextViewBindingAdapter.setText(this.mboundView5, itemCity2);
            this.mboundView6.setVisibility(itemCityJavaLangObjectNullItemCityEqualsIgnoreCaseJavaLangStringKarachiBooleanFalseViewVISIBLEViewINVISIBLE);
            TextViewBindingAdapter.setText(this.mboundView7, itemAddress);
            itemOutstandingBalance2 = itemEmail;
            TextViewBindingAdapter.setText(this.mboundView8, itemOutstandingBalance2);
            TextViewBindingAdapter.setText(this.mboundView9, itemPhone);
        } else {
            itemOutstandingBalance2 = itemEmail;
        }
        if ((dirtyFlags & 4) != 0) {
            this.mboundView14.setOnClickListener(this.mCallback2);
            this.mboundView3.setOnClickListener(this.mCallback1);
        }
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        boolean listenerJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                GenericListeners listener = this.mListener;
                listenerJavaLangObjectNull = listener != null;
                if (listenerJavaLangObjectNull) {
                    listener.onSettingClick();
                    break;
                }
                break;
            case 2:
                GenericListeners listener2 = this.mListener;
                listenerJavaLangObjectNull = listener2 != null;
                if (listenerJavaLangObjectNull) {
                    listener2.onTapLogout();
                    break;
                }
                break;
        }
    }
}
