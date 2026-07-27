package com.ingenious.androidbookmarksalesupgrade.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import com.ingenious.androidbookmarksalesupgrade.model.response.GlobalResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.Messages;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MessageListResponse.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002BQ\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0018\b\u0002\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f¢\u0006\u0004\b\r\u0010\u000eJ\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010\"\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0017J\u0019\u0010#\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fHÆ\u0003JX\u0010$\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0018\b\u0002\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fHÆ\u0001¢\u0006\u0002\u0010%J\u0006\u0010&\u001a\u00020'J\u0013\u0010(\u001a\u00020\b2\b\u0010)\u001a\u0004\u0018\u00010*HÖ\u0003J\t\u0010+\u001a\u00020'HÖ\u0001J\t\u0010,\u001a\u00020\u0004HÖ\u0001J\u0016\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020'R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R \u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\"\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0007\u0010\u0017\"\u0004\b\u0018\u0010\u0019R.\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u00062"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/MessageListResponse;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;", "Landroid/os/Parcelable;", "image", "", "title", "channel", "isLastPage", "", "messages", "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Messages;", "Lkotlin/collections/ArrayList;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/ArrayList;)V", "getImage", "()Ljava/lang/String;", "setImage", "(Ljava/lang/String;)V", "getTitle", "setTitle", "getChannel", "setChannel", "()Ljava/lang/Boolean;", "setLastPage", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getMessages", "()Ljava/util/ArrayList;", "setMessages", "(Ljava/util/ArrayList;)V", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/ArrayList;)Lcom/ingenious/androidbookmarksalesupgrade/model/MessageListResponse;", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", "flags", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes8.dex */
public final /* data */ class MessageListResponse extends GlobalResponse implements Parcelable {
    public static final Parcelable.Creator<MessageListResponse> CREATOR = new Creator();

    @SerializedName("channel")
    private String channel;

    @SerializedName("image")
    private String image;

    @SerializedName("isLastPage")
    private Boolean isLastPage;

    @SerializedName("messages")
    private ArrayList<Messages> messages;

    @SerializedName("title")
    private String title;

    /* compiled from: MessageListResponse.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Creator implements Parcelable.Creator<MessageListResponse> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final MessageListResponse createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            Boolean valueOf = parcel.readInt() == 0 ? null : Boolean.valueOf(parcel.readInt() != 0);
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            for (int i = 0; i != readInt; i++) {
                arrayList.add(Messages.CREATOR.createFromParcel(parcel));
            }
            return new MessageListResponse(readString, readString2, readString3, valueOf, arrayList);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final MessageListResponse[] newArray(int i) {
            return new MessageListResponse[i];
        }
    }

    public MessageListResponse() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ MessageListResponse copy$default(MessageListResponse messageListResponse, String str, String str2, String str3, Boolean bool, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            str = messageListResponse.image;
        }
        if ((i & 2) != 0) {
            str2 = messageListResponse.title;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = messageListResponse.channel;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            bool = messageListResponse.isLastPage;
        }
        Boolean bool2 = bool;
        if ((i & 16) != 0) {
            arrayList = messageListResponse.messages;
        }
        return messageListResponse.copy(str, str4, str5, bool2, arrayList);
    }

    /* renamed from: component1, reason: from getter */
    public final String getImage() {
        return this.image;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component3, reason: from getter */
    public final String getChannel() {
        return this.channel;
    }

    /* renamed from: component4, reason: from getter */
    public final Boolean getIsLastPage() {
        return this.isLastPage;
    }

    public final ArrayList<Messages> component5() {
        return this.messages;
    }

    public final MessageListResponse copy(String image, String title, String channel, Boolean isLastPage, ArrayList<Messages> messages) {
        Intrinsics.checkNotNullParameter(messages, "messages");
        return new MessageListResponse(image, title, channel, isLastPage, messages);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MessageListResponse)) {
            return false;
        }
        MessageListResponse messageListResponse = (MessageListResponse) other;
        return Intrinsics.areEqual(this.image, messageListResponse.image) && Intrinsics.areEqual(this.title, messageListResponse.title) && Intrinsics.areEqual(this.channel, messageListResponse.channel) && Intrinsics.areEqual(this.isLastPage, messageListResponse.isLastPage) && Intrinsics.areEqual(this.messages, messageListResponse.messages);
    }

    public int hashCode() {
        return ((((((((this.image == null ? 0 : this.image.hashCode()) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.channel == null ? 0 : this.channel.hashCode())) * 31) + (this.isLastPage != null ? this.isLastPage.hashCode() : 0)) * 31) + this.messages.hashCode();
    }

    public String toString() {
        return "MessageListResponse(image=" + this.image + ", title=" + this.title + ", channel=" + this.channel + ", isLastPage=" + this.isLastPage + ", messages=" + this.messages + ")";
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public /* synthetic */ MessageListResponse(String str, String str2, String str3, Boolean bool, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) == 0 ? bool : null, (i & 16) != 0 ? new ArrayList() : arrayList);
    }

    public final String getImage() {
        return this.image;
    }

    public final void setImage(String str) {
        this.image = str;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final String getChannel() {
        return this.channel;
    }

    public final void setChannel(String str) {
        this.channel = str;
    }

    public final Boolean isLastPage() {
        return this.isLastPage;
    }

    public final void setLastPage(Boolean bool) {
        this.isLastPage = bool;
    }

    public final ArrayList<Messages> getMessages() {
        return this.messages;
    }

    public final void setMessages(ArrayList<Messages> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.messages = arrayList;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MessageListResponse(String image, String title, String channel, Boolean isLastPage, ArrayList<Messages> messages) {
        super(null, null, null, 7, null);
        Intrinsics.checkNotNullParameter(messages, "messages");
        this.image = image;
        this.title = title;
        this.channel = channel;
        this.isLastPage = isLastPage;
        this.messages = messages;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel dest, int flags) {
        int i;
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.image);
        dest.writeString(this.title);
        dest.writeString(this.channel);
        Boolean bool = this.isLastPage;
        if (bool == null) {
            i = 0;
        } else {
            dest.writeInt(1);
            i = bool.booleanValue();
        }
        dest.writeInt(i);
        ArrayList<Messages> arrayList = this.messages;
        dest.writeInt(arrayList.size());
        Iterator<Messages> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().writeToParcel(dest, flags);
        }
    }
}
