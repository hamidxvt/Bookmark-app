package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.budiyev.android.codescanner.BarcodeUtils;
import com.ingenious.androidbookmarksalesupgrade.extensions.DialogExtKt;
import com.ingenious.androidbookmarksalesupgrade.listener.DialogListeners;
import com.ingenious.androidbookmarksalesupgrade.utils.Utils;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/* compiled from: CheckIn3Fragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment$onViewCreated$3$1", f = "CheckIn3Fragment.kt", i = {}, l = {171, BarcodeUtils.ROTATION_180}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
final class CheckIn3Fragment$onViewCreated$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $photoPath;
    int label;
    final /* synthetic */ CheckIn3Fragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CheckIn3Fragment$onViewCreated$3$1(String str, CheckIn3Fragment checkIn3Fragment, Continuation<? super CheckIn3Fragment$onViewCreated$3$1> continuation) {
        super(2, continuation);
        this.$photoPath = str;
        this.this$0 = checkIn3Fragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CheckIn3Fragment$onViewCreated$3$1(this.$photoPath, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CheckIn3Fragment$onViewCreated$3$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.coroutines.Continuation] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        File compressImage;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r2 = 0;
        r2 = 0;
        try {
        } catch (Exception e) {
            this.label = 2;
            if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(this.this$0, r2), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                Uri parse = Uri.parse(this.$photoPath);
                CheckIn3Fragment checkIn3Fragment = this.this$0;
                Context requireContext = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
                Intrinsics.checkNotNull(parse);
                compressImage = checkIn3Fragment.compressImage(requireContext, parse);
                Log.d("FILE_SIZE", (compressImage.length() / 1024) + " KB");
                MultipartBody.Part createFormData = MultipartBody.Part.INSTANCE.createFormData("image", compressImage.getName(), RequestBody.INSTANCE.create(compressImage, MediaType.INSTANCE.parse("image/jpeg")));
                this.label = 1;
                Object withContext = BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1(this.this$0, createFormData, null), this);
                r2 = withContext;
                if (withContext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            case 2:
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* compiled from: CheckIn3Fragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment$onViewCreated$3$1$1", f = "CheckIn3Fragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment$onViewCreated$3$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ MultipartBody.Part $imageBody;
        int label;
        final /* synthetic */ CheckIn3Fragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(CheckIn3Fragment checkIn3Fragment, MultipartBody.Part part, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = checkIn3Fragment;
            this.$imageBody = part;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$imageBody, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            VisitViewModel viewModel;
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    viewModel = this.this$0.getViewModel();
                    Utils utils = Utils.INSTANCE;
                    str = this.this$0.visitId;
                    viewModel.imageCheck(utils.getSimpleTextBody(str), this.$imageBody);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: CheckIn3Fragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment$onViewCreated$3$1$2", f = "CheckIn3Fragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CheckIn3Fragment$onViewCreated$3$1$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ CheckIn3Fragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(CheckIn3Fragment checkIn3Fragment, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = checkIn3Fragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    this.this$0.getBinding().btnStartVisit.setText("Start Visit");
                    this.this$0.getBinding().btnStartVisit.setEnabled(true);
                    DialogExtKt.showMaterialDialog$default(this.this$0, "Image processing failed", (DialogListeners) null, 2, (Object) null);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
