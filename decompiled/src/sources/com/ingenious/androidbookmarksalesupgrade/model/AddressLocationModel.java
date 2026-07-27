package com.ingenious.androidbookmarksalesupgrade.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddressLocationModel.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b-\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B_\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u0006¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0006HÆ\u0003J\t\u0010+\u001a\u00020\u0006HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0006HÆ\u0003J\t\u0010.\u001a\u00020\u0006HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0006HÆ\u0003J\t\u00101\u001a\u00020\u0006HÆ\u0003Jm\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u0006HÆ\u0001J\u0006\u00103\u001a\u000204J\u0013\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u000108HÖ\u0003J\t\u00109\u001a\u000204HÖ\u0001J\t\u0010:\u001a\u00020\u0003HÖ\u0001J\u0016\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u000204R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0011\"\u0004\b\u001d\u0010\u0013R\u001a\u0010\t\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\u001a\u0010\n\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0017\"\u0004\b!\u0010\u0019R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0011\"\u0004\b#\u0010\u0013R\u001a\u0010\f\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0017\"\u0004\b%\u0010\u0019R\u001a\u0010\r\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0017\"\u0004\b'\u0010\u0019¨\u0006@"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/AddressLocationModel;", "Landroid/os/Parcelable;", "locationAddress", "", "wareHouseAddress", "wareHouseLatitude", "", "wareHouseLongitude", "shopAddress", "shopLatitude", "shopLongitude", "deliveryAddress", "deliveryLatitude", "deliveryLongitude", "<init>", "(Ljava/lang/String;Ljava/lang/String;DDLjava/lang/String;DDLjava/lang/String;DD)V", "getLocationAddress", "()Ljava/lang/String;", "setLocationAddress", "(Ljava/lang/String;)V", "getWareHouseAddress", "setWareHouseAddress", "getWareHouseLatitude", "()D", "setWareHouseLatitude", "(D)V", "getWareHouseLongitude", "setWareHouseLongitude", "getShopAddress", "setShopAddress", "getShopLatitude", "setShopLatitude", "getShopLongitude", "setShopLongitude", "getDeliveryAddress", "setDeliveryAddress", "getDeliveryLatitude", "setDeliveryLatitude", "getDeliveryLongitude", "setDeliveryLongitude", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", "flags", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes8.dex */
public final /* data */ class AddressLocationModel implements Parcelable {
    public static final Parcelable.Creator<AddressLocationModel> CREATOR = new Creator();
    private String deliveryAddress;
    private double deliveryLatitude;
    private double deliveryLongitude;
    private String locationAddress;
    private String shopAddress;
    private double shopLatitude;
    private double shopLongitude;
    private String wareHouseAddress;
    private double wareHouseLatitude;
    private double wareHouseLongitude;

    /* compiled from: AddressLocationModel.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Creator implements Parcelable.Creator<AddressLocationModel> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AddressLocationModel createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new AddressLocationModel(parcel.readString(), parcel.readString(), parcel.readDouble(), parcel.readDouble(), parcel.readString(), parcel.readDouble(), parcel.readDouble(), parcel.readString(), parcel.readDouble(), parcel.readDouble());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AddressLocationModel[] newArray(int i) {
            return new AddressLocationModel[i];
        }
    }

    /* renamed from: component1, reason: from getter */
    public final String getLocationAddress() {
        return this.locationAddress;
    }

    /* renamed from: component10, reason: from getter */
    public final double getDeliveryLongitude() {
        return this.deliveryLongitude;
    }

    /* renamed from: component2, reason: from getter */
    public final String getWareHouseAddress() {
        return this.wareHouseAddress;
    }

    /* renamed from: component3, reason: from getter */
    public final double getWareHouseLatitude() {
        return this.wareHouseLatitude;
    }

    /* renamed from: component4, reason: from getter */
    public final double getWareHouseLongitude() {
        return this.wareHouseLongitude;
    }

    /* renamed from: component5, reason: from getter */
    public final String getShopAddress() {
        return this.shopAddress;
    }

    /* renamed from: component6, reason: from getter */
    public final double getShopLatitude() {
        return this.shopLatitude;
    }

    /* renamed from: component7, reason: from getter */
    public final double getShopLongitude() {
        return this.shopLongitude;
    }

    /* renamed from: component8, reason: from getter */
    public final String getDeliveryAddress() {
        return this.deliveryAddress;
    }

    /* renamed from: component9, reason: from getter */
    public final double getDeliveryLatitude() {
        return this.deliveryLatitude;
    }

    public final AddressLocationModel copy(String locationAddress, String wareHouseAddress, double wareHouseLatitude, double wareHouseLongitude, String shopAddress, double shopLatitude, double shopLongitude, String deliveryAddress, double deliveryLatitude, double deliveryLongitude) {
        Intrinsics.checkNotNullParameter(locationAddress, "locationAddress");
        Intrinsics.checkNotNullParameter(wareHouseAddress, "wareHouseAddress");
        Intrinsics.checkNotNullParameter(shopAddress, "shopAddress");
        Intrinsics.checkNotNullParameter(deliveryAddress, "deliveryAddress");
        return new AddressLocationModel(locationAddress, wareHouseAddress, wareHouseLatitude, wareHouseLongitude, shopAddress, shopLatitude, shopLongitude, deliveryAddress, deliveryLatitude, deliveryLongitude);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddressLocationModel)) {
            return false;
        }
        AddressLocationModel addressLocationModel = (AddressLocationModel) other;
        return Intrinsics.areEqual(this.locationAddress, addressLocationModel.locationAddress) && Intrinsics.areEqual(this.wareHouseAddress, addressLocationModel.wareHouseAddress) && Double.compare(this.wareHouseLatitude, addressLocationModel.wareHouseLatitude) == 0 && Double.compare(this.wareHouseLongitude, addressLocationModel.wareHouseLongitude) == 0 && Intrinsics.areEqual(this.shopAddress, addressLocationModel.shopAddress) && Double.compare(this.shopLatitude, addressLocationModel.shopLatitude) == 0 && Double.compare(this.shopLongitude, addressLocationModel.shopLongitude) == 0 && Intrinsics.areEqual(this.deliveryAddress, addressLocationModel.deliveryAddress) && Double.compare(this.deliveryLatitude, addressLocationModel.deliveryLatitude) == 0 && Double.compare(this.deliveryLongitude, addressLocationModel.deliveryLongitude) == 0;
    }

    public int hashCode() {
        return (((((((((((((((((this.locationAddress.hashCode() * 31) + this.wareHouseAddress.hashCode()) * 31) + Double.hashCode(this.wareHouseLatitude)) * 31) + Double.hashCode(this.wareHouseLongitude)) * 31) + this.shopAddress.hashCode()) * 31) + Double.hashCode(this.shopLatitude)) * 31) + Double.hashCode(this.shopLongitude)) * 31) + this.deliveryAddress.hashCode()) * 31) + Double.hashCode(this.deliveryLatitude)) * 31) + Double.hashCode(this.deliveryLongitude);
    }

    public String toString() {
        return "AddressLocationModel(locationAddress=" + this.locationAddress + ", wareHouseAddress=" + this.wareHouseAddress + ", wareHouseLatitude=" + this.wareHouseLatitude + ", wareHouseLongitude=" + this.wareHouseLongitude + ", shopAddress=" + this.shopAddress + ", shopLatitude=" + this.shopLatitude + ", shopLongitude=" + this.shopLongitude + ", deliveryAddress=" + this.deliveryAddress + ", deliveryLatitude=" + this.deliveryLatitude + ", deliveryLongitude=" + this.deliveryLongitude + ")";
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public AddressLocationModel(String locationAddress, String wareHouseAddress, double wareHouseLatitude, double wareHouseLongitude, String shopAddress, double shopLatitude, double shopLongitude, String deliveryAddress, double deliveryLatitude, double deliveryLongitude) {
        Intrinsics.checkNotNullParameter(locationAddress, "locationAddress");
        Intrinsics.checkNotNullParameter(wareHouseAddress, "wareHouseAddress");
        Intrinsics.checkNotNullParameter(shopAddress, "shopAddress");
        Intrinsics.checkNotNullParameter(deliveryAddress, "deliveryAddress");
        this.locationAddress = locationAddress;
        this.wareHouseAddress = wareHouseAddress;
        this.wareHouseLatitude = wareHouseLatitude;
        this.wareHouseLongitude = wareHouseLongitude;
        this.shopAddress = shopAddress;
        this.shopLatitude = shopLatitude;
        this.shopLongitude = shopLongitude;
        this.deliveryAddress = deliveryAddress;
        this.deliveryLatitude = deliveryLatitude;
        this.deliveryLongitude = deliveryLongitude;
    }

    public /* synthetic */ AddressLocationModel(String str, String str2, double d, double d2, String str3, double d3, double d4, String str4, double d5, double d6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, d, d2, (i & 16) != 0 ? "" : str3, d3, d4, (i & 128) != 0 ? "" : str4, d5, d6);
    }

    public final String getLocationAddress() {
        return this.locationAddress;
    }

    public final void setLocationAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.locationAddress = str;
    }

    public final String getWareHouseAddress() {
        return this.wareHouseAddress;
    }

    public final void setWareHouseAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.wareHouseAddress = str;
    }

    public final double getWareHouseLatitude() {
        return this.wareHouseLatitude;
    }

    public final void setWareHouseLatitude(double d) {
        this.wareHouseLatitude = d;
    }

    public final double getWareHouseLongitude() {
        return this.wareHouseLongitude;
    }

    public final void setWareHouseLongitude(double d) {
        this.wareHouseLongitude = d;
    }

    public final String getShopAddress() {
        return this.shopAddress;
    }

    public final void setShopAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.shopAddress = str;
    }

    public final double getShopLatitude() {
        return this.shopLatitude;
    }

    public final void setShopLatitude(double d) {
        this.shopLatitude = d;
    }

    public final double getShopLongitude() {
        return this.shopLongitude;
    }

    public final void setShopLongitude(double d) {
        this.shopLongitude = d;
    }

    public final String getDeliveryAddress() {
        return this.deliveryAddress;
    }

    public final void setDeliveryAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deliveryAddress = str;
    }

    public final double getDeliveryLatitude() {
        return this.deliveryLatitude;
    }

    public final void setDeliveryLatitude(double d) {
        this.deliveryLatitude = d;
    }

    public final double getDeliveryLongitude() {
        return this.deliveryLongitude;
    }

    public final void setDeliveryLongitude(double d) {
        this.deliveryLongitude = d;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.locationAddress);
        dest.writeString(this.wareHouseAddress);
        dest.writeDouble(this.wareHouseLatitude);
        dest.writeDouble(this.wareHouseLongitude);
        dest.writeString(this.shopAddress);
        dest.writeDouble(this.shopLatitude);
        dest.writeDouble(this.shopLongitude);
        dest.writeString(this.deliveryAddress);
        dest.writeDouble(this.deliveryLatitude);
        dest.writeDouble(this.deliveryLongitude);
    }
}
