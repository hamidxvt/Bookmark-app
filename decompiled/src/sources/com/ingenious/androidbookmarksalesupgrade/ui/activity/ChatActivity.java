package com.ingenious.androidbookmarksalesupgrade.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.activity.ComponentActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.savedstate.SavedStateRegistryOwner;
import com.google.gson.Gson;
import com.ingenious.androidbookmarksalesupgrade.adapter.ChatAdapter;
import com.ingenious.androidbookmarksalesupgrade.databinding.ActivityChatBinding;
import com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners;
import com.ingenious.androidbookmarksalesupgrade.model.MessageListResponse;
import com.ingenious.androidbookmarksalesupgrade.model.request.SendMessageRequest;
import com.ingenious.androidbookmarksalesupgrade.model.response.GlobalResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.LoginResponse;
import com.ingenious.androidbookmarksalesupgrade.model.response.Messages;
import com.ingenious.androidbookmarksalesupgrade.network.ApiResponseCallback;
import com.ingenious.androidbookmarksalesupgrade.network.domain.ErrorHandler;
import com.ingenious.androidbookmarksalesupgrade.storage.AppPreferences;
import com.ingenious.androidbookmarksalesupgrade.utils.AppToast;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import com.ingenious.androidbookmarksalesupgrade.utils.ExtensionKt;
import com.ingenious.androidbookmarksalesupgrade.viewModel.UserViewModel;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.androidx.viewmodel.ViewModelOwner;
import org.koin.androidx.viewmodel.ViewModelParameter;
import org.koin.androidx.viewmodel.ViewModelResolverKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

/* compiled from: ChatActivity.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\b\u0010!\u001a\u00020\u001eH\u0002J\b\u0010\"\u001a\u00020\u001eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/ChatActivity;", "Lcom/ingenious/androidbookmarksalesupgrade/ui/activity/BaseActivity;", "<init>", "()V", "binding", "Lcom/ingenious/androidbookmarksalesupgrade/databinding/ActivityChatBinding;", "viewModel", "Lcom/ingenious/androidbookmarksalesupgrade/viewModel/UserViewModel;", "getViewModel", "()Lcom/ingenious/androidbookmarksalesupgrade/viewModel/UserViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "chatAdapter", "Lcom/ingenious/androidbookmarksalesupgrade/adapter/ChatAdapter;", "messageList", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Messages;", "message", "", "manager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "pageNo", "", "visibleItemCount", "totalItemCount", "firstVisibleItemPosition", "isLastPage", "", "isScrolling", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "realTime", "setAdapter", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class ChatActivity extends BaseActivity {
    private ActivityChatBinding binding;
    private ChatAdapter chatAdapter;
    private int firstVisibleItemPosition;
    private boolean isLastPage;
    private boolean isScrolling;
    private LinearLayoutManager manager;
    private String message;
    private List<Messages> messageList;
    private int pageNo;
    private int totalItemCount;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private int visibleItemCount;

    public ChatActivity() {
        final ChatActivity $this$viewModel_u24default$iv = this;
        final Qualifier qualifier$iv = null;
        final Function0 owner$iv = new Function0<ViewModelOwner>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ChatActivity$special$$inlined$viewModel$default$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ViewModelOwner invoke() {
                ViewModelOwner.Companion companion = ViewModelOwner.INSTANCE;
                ComponentActivity componentActivity = ComponentActivity.this;
                ComponentActivity componentActivity2 = ComponentActivity.this;
                return companion.from(componentActivity, componentActivity2 instanceof SavedStateRegistryOwner ? componentActivity2 : null);
            }
        };
        final Function0 parameters$iv = null;
        final Scope scope$iv = AndroidKoinScopeExtKt.getKoinScope($this$viewModel_u24default$iv);
        Function0 factoryProducer$iv$iv = new Function0<ViewModelProvider.Factory>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ChatActivity$special$$inlined$viewModel$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                Function0 owner$iv2 = Function0.this;
                Qualifier qualifier$iv2 = qualifier$iv;
                Function0 parameters$iv2 = parameters$iv;
                Scope scope$iv2 = scope$iv;
                ViewModelOwner ownerValue$iv = (ViewModelOwner) owner$iv2.invoke();
                ViewModelParameter viewModelParameters$iv = new ViewModelParameter(Reflection.getOrCreateKotlinClass(UserViewModel.class), qualifier$iv2, null, parameters$iv2, ownerValue$iv.getStoreOwner(), ownerValue$iv.getStateRegistry());
                return ViewModelResolverKt.pickFactory(scope$iv2, viewModelParameters$iv);
            }
        };
        this.viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(UserViewModel.class), new Function0<ViewModelStore>() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ChatActivity$special$$inlined$viewModel$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, factoryProducer$iv$iv);
        this.messageList = new ArrayList();
        this.message = "";
        this.pageNo = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final UserViewModel getViewModel() {
        return (UserViewModel) this.viewModel.getValue();
    }

    @Override // com.ingenious.androidbookmarksalesupgrade.ui.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityChatBinding.inflate(getLayoutInflater());
        ActivityChatBinding activityChatBinding = this.binding;
        ActivityChatBinding activityChatBinding2 = null;
        if (activityChatBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityChatBinding = null;
        }
        setContentView(activityChatBinding.getRoot());
        realTime();
        setAdapter();
        getViewModel().listMessageRequest(this.pageNo);
        ExtensionKt.belowStatusBarText(this);
        ActivityChatBinding activityChatBinding3 = this.binding;
        if (activityChatBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityChatBinding3 = null;
        }
        activityChatBinding3.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ChatActivity$onCreate$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 1) {
                    ChatActivity.this.isScrolling = true;
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager;
                LinearLayoutManager linearLayoutManager2;
                LinearLayoutManager linearLayoutManager3;
                boolean z;
                boolean z2;
                int i;
                int i2;
                int i3;
                int i4;
                UserViewModel viewModel;
                int i5;
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                super.onScrolled(recyclerView, dx, dy);
                ChatActivity chatActivity = ChatActivity.this;
                linearLayoutManager = ChatActivity.this.manager;
                Intrinsics.checkNotNull(linearLayoutManager);
                chatActivity.visibleItemCount = linearLayoutManager.getChildCount();
                ChatActivity chatActivity2 = ChatActivity.this;
                linearLayoutManager2 = ChatActivity.this.manager;
                Intrinsics.checkNotNull(linearLayoutManager2);
                chatActivity2.totalItemCount = linearLayoutManager2.getItemCount();
                ChatActivity chatActivity3 = ChatActivity.this;
                linearLayoutManager3 = ChatActivity.this.manager;
                Intrinsics.checkNotNull(linearLayoutManager3);
                chatActivity3.firstVisibleItemPosition = linearLayoutManager3.findFirstVisibleItemPosition();
                if (dy < 0) {
                    z = ChatActivity.this.isLastPage;
                    if (!z) {
                        z2 = ChatActivity.this.isScrolling;
                        if (z2) {
                            i = ChatActivity.this.visibleItemCount;
                            i2 = ChatActivity.this.firstVisibleItemPosition;
                            int i6 = i + i2;
                            i3 = ChatActivity.this.totalItemCount;
                            if (i6 == i3) {
                                ChatActivity.this.isScrolling = false;
                                ChatActivity chatActivity4 = ChatActivity.this;
                                i4 = ChatActivity.this.pageNo;
                                chatActivity4.pageNo = i4 + 1;
                                viewModel = ChatActivity.this.getViewModel();
                                i5 = ChatActivity.this.pageNo;
                                viewModel.listMessageRequest(i5);
                            }
                        }
                    }
                }
            }
        });
        getViewModel().getListMessageResponse().observe(this, new ChatActivity$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ChatActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onCreate$lambda$3;
                onCreate$lambda$3 = ChatActivity.onCreate$lambda$3(ChatActivity.this, (ApiResponseCallback) obj);
                return onCreate$lambda$3;
            }
        }));
        getViewModel().getSendMessageResponse().observe(this, new ChatActivity$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ChatActivity$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onCreate$lambda$7;
                onCreate$lambda$7 = ChatActivity.onCreate$lambda$7(ChatActivity.this, (ApiResponseCallback) obj);
                return onCreate$lambda$7;
            }
        }));
        ActivityChatBinding activityChatBinding4 = this.binding;
        if (activityChatBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityChatBinding4 = null;
        }
        activityChatBinding4.setListener(new GenericListeners() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ChatActivity$onCreate$4
            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onNotificationClick() {
                GenericListeners.DefaultImpls.onNotificationClick(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onSettingClick() {
                GenericListeners.DefaultImpls.onSettingClick(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapAddBooks() {
                GenericListeners.DefaultImpls.onTapAddBooks(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapAddCustomer() {
                GenericListeners.DefaultImpls.onTapAddCustomer(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapAddHome() {
                GenericListeners.DefaultImpls.onTapAddHome(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapAddVisit() {
                GenericListeners.DefaultImpls.onTapAddVisit(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapBack() {
                GenericListeners.DefaultImpls.onTapBack(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapCaptureImage() {
                GenericListeners.DefaultImpls.onTapCaptureImage(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapCheckIn() {
                GenericListeners.DefaultImpls.onTapCheckIn(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapCompleteVisit() {
                GenericListeners.DefaultImpls.onTapCompleteVisit(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapDate() {
                GenericListeners.DefaultImpls.onTapDate(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapDateNext() {
                GenericListeners.DefaultImpls.onTapDateNext(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapDatePrevious() {
                GenericListeners.DefaultImpls.onTapDatePrevious(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapDismiss() {
                GenericListeners.DefaultImpls.onTapDismiss(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapDone() {
                GenericListeners.DefaultImpls.onTapDone(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapFilter() {
                GenericListeners.DefaultImpls.onTapFilter(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapForgetPassword() {
                GenericListeners.DefaultImpls.onTapForgetPassword(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapLocation() {
                GenericListeners.DefaultImpls.onTapLocation(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapLocationFab() {
                GenericListeners.DefaultImpls.onTapLocationFab(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapLogin() {
                GenericListeners.DefaultImpls.onTapLogin(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapLogout() {
                GenericListeners.DefaultImpls.onTapLogout(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapLowStock() {
                GenericListeners.DefaultImpls.onTapLowStock(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapNewAccount() {
                GenericListeners.DefaultImpls.onTapNewAccount(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapOTP() {
                GenericListeners.DefaultImpls.onTapOTP(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapProfile() {
                GenericListeners.DefaultImpls.onTapProfile(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapRefill() {
                GenericListeners.DefaultImpls.onTapRefill(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapRefillRequests() {
                GenericListeners.DefaultImpls.onTapRefillRequests(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapResetPassword() {
                GenericListeners.DefaultImpls.onTapResetPassword(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapSettings() {
                GenericListeners.DefaultImpls.onTapSettings(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapSwitch() {
                GenericListeners.DefaultImpls.onTapSwitch(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapViewSelection() {
                GenericListeners.DefaultImpls.onTapViewSelection(this);
            }

            @Override // com.ingenious.androidbookmarksalesupgrade.listener.GenericListeners
            public void onTapSendMessage() {
                ActivityChatBinding activityChatBinding5;
                String str;
                ChatAdapter chatAdapter;
                ActivityChatBinding activityChatBinding6;
                UserViewModel viewModel;
                String str2;
                ActivityChatBinding activityChatBinding7;
                ChatAdapter chatAdapter2;
                ChatActivity chatActivity = ChatActivity.this;
                activityChatBinding5 = ChatActivity.this.binding;
                ChatAdapter chatAdapter3 = null;
                if (activityChatBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityChatBinding5 = null;
                }
                chatActivity.message = activityChatBinding5.etMsg.getText().toString();
                str = ChatActivity.this.message;
                Messages newMessage = new Messages(str, "", "booker");
                chatAdapter = ChatActivity.this.chatAdapter;
                if (chatAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                    chatAdapter = null;
                }
                chatAdapter.addNewMessage(newMessage);
                activityChatBinding6 = ChatActivity.this.binding;
                if (activityChatBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityChatBinding6 = null;
                }
                activityChatBinding6.etMsg.setText("");
                viewModel = ChatActivity.this.getViewModel();
                str2 = ChatActivity.this.message;
                viewModel.sendMessageRequest(new SendMessageRequest(str2));
                activityChatBinding7 = ChatActivity.this.binding;
                if (activityChatBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityChatBinding7 = null;
                }
                RecyclerView recyclerView = activityChatBinding7.recyclerView;
                chatAdapter2 = ChatActivity.this.chatAdapter;
                if (chatAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                } else {
                    chatAdapter3 = chatAdapter2;
                }
                recyclerView.smoothScrollToPosition(chatAdapter3.getTabCount() - 1);
            }
        });
        ActivityChatBinding activityChatBinding5 = this.binding;
        if (activityChatBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityChatBinding5 = null;
        }
        activityChatBinding5.cancel.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ChatActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChatActivity.this.finish();
            }
        });
        ActivityChatBinding activityChatBinding6 = this.binding;
        if (activityChatBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityChatBinding2 = activityChatBinding6;
        }
        activityChatBinding2.call.setOnClickListener(new View.OnClickListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ChatActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChatActivity.onCreate$lambda$9(ChatActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit onCreate$lambda$3(ChatActivity this$0, ApiResponseCallback it) {
        if (it != null && !(it instanceof ApiResponseCallback.Loading)) {
            if (it instanceof ApiResponseCallback.Success) {
                MessageListResponse data = (MessageListResponse) ((ApiResponseCallback.Success) it).getData();
                if (data != null) {
                    Boolean isLastPage = data.isLastPage();
                    Intrinsics.checkNotNull(isLastPage);
                    this$0.isLastPage = isLastPage.booleanValue();
                    Boolean success = data.getSuccess();
                    Intrinsics.checkNotNull(success);
                    if (success.booleanValue()) {
                        this$0.messageList = data.getMessages();
                        ChatAdapter chatAdapter = this$0.chatAdapter;
                        ChatAdapter chatAdapter2 = null;
                        if (chatAdapter == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                            chatAdapter = null;
                        }
                        chatAdapter.addNewMessageList(this$0.messageList);
                        ActivityChatBinding activityChatBinding = this$0.binding;
                        if (activityChatBinding == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityChatBinding = null;
                        }
                        activityChatBinding.setItem(data);
                        ActivityChatBinding activityChatBinding2 = this$0.binding;
                        if (activityChatBinding2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityChatBinding2 = null;
                        }
                        RecyclerView recyclerView = activityChatBinding2.recyclerView;
                        ChatAdapter chatAdapter3 = this$0.chatAdapter;
                        if (chatAdapter3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
                        } else {
                            chatAdapter2 = chatAdapter3;
                        }
                        recyclerView.smoothScrollToPosition(chatAdapter2.getTabCount() - 1);
                        Log.i("TAG", "onCreate: " + data.getMessages());
                    }
                }
            } else {
                if (!(it instanceof ApiResponseCallback.Error)) {
                    throw new NoWhenBranchMatchedException();
                }
                this$0.genericNetworkErrorHandler(it, new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ChatActivity$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit onCreate$lambda$3$lambda$2$lambda$1;
                        onCreate$lambda$3$lambda$2$lambda$1 = ChatActivity.onCreate$lambda$3$lambda$2$lambda$1((ErrorHandler) obj);
                        return onCreate$lambda$3$lambda$2$lambda$1;
                    }
                });
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$3$lambda$2$lambda$1(ErrorHandler it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit onCreate$lambda$7(ChatActivity this$0, ApiResponseCallback it) {
        if (it != null && !(it instanceof ApiResponseCallback.Loading)) {
            if (it instanceof ApiResponseCallback.Success) {
                GlobalResponse data = (GlobalResponse) ((ApiResponseCallback.Success) it).getData();
                if (data != null) {
                    Boolean success = data.getSuccess();
                    Intrinsics.checkNotNull(success);
                    if (success.booleanValue()) {
                        AppToast.INSTANCE.showToast(String.valueOf(data.getMessage()));
                    } else {
                        AppToast.INSTANCE.showToast(String.valueOf(data.getMessage()));
                    }
                }
            } else {
                if (!(it instanceof ApiResponseCallback.Error)) {
                    throw new NoWhenBranchMatchedException();
                }
                this$0.genericNetworkErrorHandler(it, new Function1() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ChatActivity$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit onCreate$lambda$7$lambda$6$lambda$5;
                        onCreate$lambda$7$lambda$6$lambda$5 = ChatActivity.onCreate$lambda$7$lambda$6$lambda$5((ErrorHandler) obj);
                        return onCreate$lambda$7$lambda$6$lambda$5;
                    }
                });
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$7$lambda$6$lambda$5(ErrorHandler it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$9(ChatActivity this$0, View it) {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:03118875054"));
        this$0.startActivity(intent);
    }

    private final void realTime() {
        String str;
        PusherOptions options = new PusherOptions();
        options.setCluster(Constant.PusherConstant.PUSHER_CLUSTER);
        Pusher pusher = new Pusher(Constant.PusherConstant.PUSHER_KEY, options);
        pusher.connect(new ConnectionEventListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ChatActivity$realTime$1
            @Override // com.pusher.client.connection.ConnectionEventListener
            public void onConnectionStateChange(ConnectionStateChange change) {
                Intrinsics.checkNotNullParameter(change, "change");
                Log.i("Pusher", "State changed from " + change.getPreviousState() + " to " + change.getCurrentState());
            }

            @Override // com.pusher.client.connection.ConnectionEventListener
            public void onError(String message, String code, Exception e) {
                Log.i("Pusher", "There was a problem connecting! \ncode (" + code + "), \nmessage (" + message + "), \n exception(" + e + ")");
            }
        }, ConnectionState.ALL);
        LoginResponse loginData = AppPreferences.INSTANCE.getLoginData();
        if (loginData == null || (str = loginData.getChannel()) == null) {
            str = "";
        }
        Channel channel = pusher.subscribe(str);
        channel.bind(Constant.PusherConstant.PUSHER_EVENT_NEW_JOB, new SubscriptionEventListener() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ChatActivity$$ExternalSyntheticLambda3
            @Override // com.pusher.client.channel.SubscriptionEventListener
            public final void onEvent(PusherEvent pusherEvent) {
                ChatActivity.realTime$lambda$11(ChatActivity.this, pusherEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void realTime$lambda$11(final ChatActivity this$0, final PusherEvent event) {
        this$0.runOnUiThread(new Runnable() { // from class: com.ingenious.androidbookmarksalesupgrade.ui.activity.ChatActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ChatActivity.realTime$lambda$11$lambda$10(PusherEvent.this, this$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void realTime$lambda$11$lambda$10(PusherEvent $event, ChatActivity this$0) {
        Log.i("Pusher", "Received event with data: " + $event + " \nchannelName (" + $event.getChannelName() + "), \neventName (" + $event.getEventName() + "), \n Data (" + $event.getData() + ")");
        Gson gson = new Gson();
        Messages mewMsg = (Messages) gson.fromJson($event.getData(), Messages.class);
        ChatAdapter chatAdapter = this$0.chatAdapter;
        ChatAdapter chatAdapter2 = null;
        if (chatAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
            chatAdapter = null;
        }
        Intrinsics.checkNotNull(mewMsg);
        chatAdapter.addNewMessage(mewMsg);
        ActivityChatBinding activityChatBinding = this$0.binding;
        if (activityChatBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityChatBinding = null;
        }
        RecyclerView recyclerView = activityChatBinding.recyclerView;
        ChatAdapter chatAdapter3 = this$0.chatAdapter;
        if (chatAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
        } else {
            chatAdapter2 = chatAdapter3;
        }
        recyclerView.smoothScrollToPosition(chatAdapter2.getTabCount() - 1);
    }

    private final void setAdapter() {
        this.chatAdapter = new ChatAdapter();
        ActivityChatBinding activityChatBinding = this.binding;
        ActivityChatBinding activityChatBinding2 = null;
        if (activityChatBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityChatBinding = null;
        }
        RecyclerView recyclerView = activityChatBinding.recyclerView;
        ChatAdapter chatAdapter = this.chatAdapter;
        if (chatAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chatAdapter");
            chatAdapter = null;
        }
        recyclerView.setAdapter(chatAdapter);
        this.manager = new LinearLayoutManager(this);
        LinearLayoutManager linearLayoutManager = this.manager;
        Intrinsics.checkNotNull(linearLayoutManager);
        linearLayoutManager.setOrientation(1);
        ActivityChatBinding activityChatBinding3 = this.binding;
        if (activityChatBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityChatBinding2 = activityChatBinding3;
        }
        activityChatBinding2.recyclerView.setLayoutManager(this.manager);
    }
}
