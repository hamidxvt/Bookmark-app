package com.ingenious.androidbookmarksalesupgrade.ui.fragment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.utils.Utils;
import com.ingenious.androidbookmarksalesupgrade.viewModel.VisitViewModel;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/* compiled from: CompleteVisitFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.ui.fragment.CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1", f = "CompleteVisitFragment.kt", i = {0}, l = {138}, m = "invokeSuspend", n = {"images"}, s = {"L$0"})
/* loaded from: classes5.dex */
final class CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ CompleteVisitFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1(CompleteVisitFragment completeVisitFragment, Continuation<? super CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1> continuation) {
        super(2, continuation);
        this.this$0 = completeVisitFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        ArrayList images;
        VisitViewModel viewModel;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.this$0.getBinding().progressBar.setVisibility(0);
                ArrayList images2 = new ArrayList();
                this.L$0 = images2;
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1(this.this$0, images2, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                images = images2;
                break;
            case 1:
                ArrayList images3 = (ArrayList) this.L$0;
                ResultKt.throwOnFailure($result);
                images = images3;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        viewModel = this.this$0.getViewModel();
        viewModel.visitCompletion(Utils.INSTANCE.getSimpleTextBody(this.this$0.getVisitId()), Utils.INSTANCE.getSimpleTextBody(this.this$0.getBinding().title.getText().toString()), Utils.INSTANCE.getSimpleTextBody("test"), (r16 & 8) != 0 ? null : this.this$0.getImageBodySignature(), (r16 & 16) != 0 ? null : images, (r16 & 32) != 0 ? null : null);
        return Unit.INSTANCE;
    }

    /* compiled from: CompleteVisitFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.ui.fragment.CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1$1", f = "CompleteVisitFragment.kt", i = {}, l = {169, 193}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ArrayList<MultipartBody.Part> $images;
        int label;
        final /* synthetic */ CompleteVisitFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(CompleteVisitFragment completeVisitFragment, ArrayList<MultipartBody.Part> arrayList, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = completeVisitFragment;
            this.$images = arrayList;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$images, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Unreachable blocks removed: 2, instructions: 4 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Iterable iterable;
            Object $result2;
            int $i$f$forEachIndexed;
            int i;
            Iterator it;
            int index$iv;
            Throwable th;
            FileOutputStream fileOutputStream;
            Throwable th2;
            long copyTo$default;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    Object $result3 = $result;
                    iterable = this.this$0.pictureUriList;
                    Iterable $this$forEachIndexed$iv = iterable;
                    CompleteVisitFragment completeVisitFragment = this.this$0;
                    ArrayList<MultipartBody.Part> arrayList = this.$images;
                    int $i$f$forEachIndexed2 = 0;
                    int index$iv2 = 0;
                    Iterator it2 = $this$forEachIndexed$iv.iterator();
                    while (it2.hasNext()) {
                        Object item$iv = it2.next();
                        int index$iv3 = index$iv2 + 1;
                        if (index$iv2 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        Uri uri = (Uri) item$iv;
                        int index = index$iv2;
                        try {
                            String mimeType = completeVisitFragment.requireContext().getContentResolver().getType(uri);
                            if (mimeType == null) {
                                mimeType = "image/*";
                            }
                            String extension = StringsKt.substringAfterLast$default(mimeType, '/', (String) null, 2, (Object) null);
                            $result2 = $result3;
                            try {
                                File tempFile = new File(completeVisitFragment.requireContext().getCacheDir(), "upload_" + index + "." + extension);
                                InputStream openInputStream = completeVisitFragment.requireContext().getContentResolver().openInputStream(uri);
                                if (openInputStream != null) {
                                    InputStream inputStream = openInputStream;
                                    try {
                                        InputStream input = inputStream;
                                        fileOutputStream = new FileOutputStream(tempFile);
                                        try {
                                            FileOutputStream output = fileOutputStream;
                                            $i$f$forEachIndexed = $i$f$forEachIndexed2;
                                            try {
                                                i = 0;
                                                it = it2;
                                                index$iv = index$iv3;
                                                try {
                                                    copyTo$default = ByteStreamsKt.copyTo$default(input, output, 0, 2, null);
                                                } catch (Throwable th3) {
                                                    th2 = th3;
                                                    try {
                                                        throw th2;
                                                    } finally {
                                                    }
                                                }
                                            } catch (Throwable th4) {
                                                i = 0;
                                                th2 = th4;
                                            }
                                        } catch (Throwable th5) {
                                            $i$f$forEachIndexed = $i$f$forEachIndexed2;
                                            i = 0;
                                            th2 = th5;
                                        }
                                    } catch (Throwable th6) {
                                        $i$f$forEachIndexed = $i$f$forEachIndexed2;
                                        i = 0;
                                        th = th6;
                                    }
                                    try {
                                        CloseableKt.closeFinally(fileOutputStream, null);
                                        Boxing.boxLong(copyTo$default);
                                        try {
                                            CloseableKt.closeFinally(inputStream, null);
                                        } catch (Exception e) {
                                            e = e;
                                            e.printStackTrace();
                                            MainCoroutineDispatcher main = Dispatchers.getMain();
                                            CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1$1$1$2 completeVisitFragment$onViewCreated$3$onTapCompleteVisit$1$1$1$2 = new CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1$1$1$2(e, completeVisitFragment, null);
                                            this.label = 1;
                                            return BuildersKt.withContext(main, completeVisitFragment$onViewCreated$3$onTapCompleteVisit$1$1$1$2, this) == coroutine_suspended ? coroutine_suspended : Unit.INSTANCE;
                                        }
                                    } catch (Throwable th7) {
                                        th = th7;
                                        try {
                                            throw th;
                                        } finally {
                                        }
                                    }
                                } else {
                                    $i$f$forEachIndexed = $i$f$forEachIndexed2;
                                    it = it2;
                                    index$iv = index$iv3;
                                }
                                RequestBody imageBody = RequestBody.INSTANCE.create(tempFile, MediaType.INSTANCE.parse(mimeType));
                                MultipartBody.Part imagePart = MultipartBody.Part.INSTANCE.createFormData("images[" + index + "]", tempFile.getName(), imageBody);
                                arrayList.add(imagePart);
                                index$iv2 = index$iv;
                                $result3 = $result2;
                                $i$f$forEachIndexed2 = $i$f$forEachIndexed;
                                it2 = it;
                            } catch (Exception e2) {
                                e = e2;
                                $i$f$forEachIndexed = $i$f$forEachIndexed2;
                                i = 0;
                            }
                        } catch (Exception e3) {
                            e = e3;
                            $result2 = $result3;
                            $i$f$forEachIndexed = $i$f$forEachIndexed2;
                            i = 0;
                        }
                    }
                    Bitmap signatureBitmap = this.this$0.getBinding().signaturePad.getSignatureBitmap();
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    signatureBitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArray);
                    byte[] imageData = byteArray.toByteArray();
                    File signatureFile = new File(this.this$0.requireContext().getCacheDir(), "signature.jpg");
                    try {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(signatureFile);
                        try {
                            FileOutputStream it3 = fileOutputStream2;
                            it3.write(imageData);
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(fileOutputStream2, null);
                            RequestBody requestBody = RequestBody.INSTANCE.create(signatureFile, MediaType.INSTANCE.parse("image/jpeg"));
                            this.this$0.setImageBodySignature(MultipartBody.Part.INSTANCE.createFormData("signature", signatureFile.getName(), requestBody));
                            return Unit.INSTANCE;
                        } finally {
                        }
                    } catch (FileNotFoundException e4) {
                        e4.printStackTrace();
                        this.label = 2;
                        if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass3(e4, this.this$0, null), this) != coroutine_suspended) {
                            break;
                        } else {
                            return coroutine_suspended;
                        }
                    }
                case 1:
                    ResultKt.throwOnFailure($result);
                case 2:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* compiled from: CompleteVisitFragment.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "com.ingenious.androidbookmarksalesupgrade.ui.fragment.CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1$1$3", f = "CompleteVisitFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.ingenious.androidbookmarksalesupgrade.ui.fragment.CompleteVisitFragment$onViewCreated$3$onTapCompleteVisit$1$1$3, reason: invalid class name */
        static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ FileNotFoundException $e;
            int label;
            final /* synthetic */ CompleteVisitFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(FileNotFoundException fileNotFoundException, CompleteVisitFragment completeVisitFragment, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.$e = fileNotFoundException;
                this.this$0 = completeVisitFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass3(this.$e, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        Log.d("CompleteVisitFragment", "File not found: " + this.$e.getMessage());
                        Toast.makeText(this.this$0.requireContext(), "File not found", 0).show();
                        this.this$0.getBinding().progressBar.setVisibility(8);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }
}
