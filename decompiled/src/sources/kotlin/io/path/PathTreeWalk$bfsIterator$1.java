package kotlin.io.path;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.nio.file.FileSystemLoopException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* compiled from: PathTreeWalk.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Ljava/nio/file/Path;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "kotlin.io.path.PathTreeWalk$bfsIterator$1", f = "PathTreeWalk.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1}, l = {191, 197}, m = "invokeSuspend", n = {"$this$iterator", "queue", "entriesReader", "pathNode", "this_$iv", "path$iv", "$this$iterator", "queue", "entriesReader"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2"})
/* loaded from: classes17.dex */
final class PathTreeWalk$bfsIterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    final /* synthetic */ PathTreeWalk this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PathTreeWalk$bfsIterator$1(PathTreeWalk pathTreeWalk, Continuation<? super PathTreeWalk$bfsIterator$1> continuation) {
        super(2, continuation);
        this.this$0 = pathTreeWalk;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PathTreeWalk$bfsIterator$1 pathTreeWalk$bfsIterator$1 = new PathTreeWalk$bfsIterator$1(this.this$0, continuation);
        pathTreeWalk$bfsIterator$1.L$0 = obj;
        return pathTreeWalk$bfsIterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super Path> sequenceScope, Continuation<? super Unit> continuation) {
        return ((PathTreeWalk$bfsIterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0105  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0103 -> B:8:0x0082). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0105 -> B:8:0x0082). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x0149 -> B:7:0x014e). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        boolean followLinks;
        Path path;
        Path path2;
        Object keyOf;
        SequenceScope $this$iterator;
        PathTreeWalk$bfsIterator$1 pathTreeWalk$bfsIterator$1;
        ArrayDeque queue;
        DirectoryEntriesReader entriesReader;
        Path path$iv;
        PathTreeWalk this_$iv;
        PathNode pathNode;
        DirectoryEntriesReader entriesReader2;
        ArrayDeque queue2;
        SequenceScope $this$iterator2;
        Path path$iv2;
        PathTreeWalk this_$iv2;
        PathNode pathNode2;
        LinkOption[] linkOptionArr;
        boolean createsCycle;
        SequenceScope $this$iterator3;
        ArrayDeque queue3;
        DirectoryEntriesReader entriesReader3;
        PathTreeWalk$bfsIterator$1 pathTreeWalk$bfsIterator$12;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                SequenceScope $this$iterator4 = (SequenceScope) this.L$0;
                ArrayDeque queue4 = new ArrayDeque();
                followLinks = this.this$0.getFollowLinks();
                DirectoryEntriesReader entriesReader4 = new DirectoryEntriesReader(followLinks);
                path = this.this$0.start;
                path2 = this.this$0.start;
                keyOf = PathTreeWalkKt.keyOf(path2, this.this$0.getLinkOptions());
                queue4.addLast(new PathNode(path, keyOf, null));
                $this$iterator = $this$iterator4;
                pathTreeWalk$bfsIterator$1 = this;
                queue = queue4;
                entriesReader = entriesReader4;
                while (!queue.isEmpty()) {
                    pathNode2 = (PathNode) queue.removeFirst();
                    this_$iv2 = pathTreeWalk$bfsIterator$1.this$0;
                    SequenceScope $this$yieldIfNeeded$iv = $this$iterator;
                    path$iv2 = pathNode2.getPath();
                    if (pathNode2.getParent() != null) {
                        PathsKt.checkFileName(path$iv2);
                    }
                    LinkOption[] linkOptions = this_$iv2.getLinkOptions();
                    LinkOption[] linkOptionArr2 = (LinkOption[]) Arrays.copyOf(linkOptions, linkOptions.length);
                    if (Files.isDirectory(path$iv2, (LinkOption[]) Arrays.copyOf(linkOptionArr2, linkOptionArr2.length))) {
                        createsCycle = PathTreeWalkKt.createsCycle(pathNode2);
                        if (!createsCycle) {
                            if (this_$iv2.getIncludeDirectories()) {
                                pathTreeWalk$bfsIterator$1.L$0 = $this$iterator;
                                pathTreeWalk$bfsIterator$1.L$1 = queue;
                                pathTreeWalk$bfsIterator$1.L$2 = entriesReader;
                                pathTreeWalk$bfsIterator$1.L$3 = pathNode2;
                                pathTreeWalk$bfsIterator$1.L$4 = this_$iv2;
                                pathTreeWalk$bfsIterator$1.L$5 = path$iv2;
                                pathTreeWalk$bfsIterator$1.label = 1;
                                if ($this$yieldIfNeeded$iv.yield(path$iv2, pathTreeWalk$bfsIterator$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $this$iterator2 = $this$iterator;
                                pathNode = pathNode2;
                                entriesReader2 = entriesReader;
                                path$iv = path$iv2;
                                queue2 = queue;
                                this_$iv = this_$iv2;
                                path$iv2 = path$iv;
                                entriesReader = entriesReader2;
                                pathNode2 = pathNode;
                                $this$iterator = $this$iterator2;
                                ArrayDeque arrayDeque = queue2;
                                this_$iv2 = this_$iv;
                                queue = arrayDeque;
                            }
                            LinkOption[] linkOptions2 = this_$iv2.getLinkOptions();
                            linkOptionArr = (LinkOption[]) Arrays.copyOf(linkOptions2, linkOptions2.length);
                            if (Files.isDirectory(path$iv2, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
                                List entries = entriesReader.readEntries(pathNode2);
                                queue.addAll(entries);
                            }
                            while (!queue.isEmpty()) {
                            }
                        } else {
                            throw new FileSystemLoopException(path$iv2.toString());
                        }
                    } else if (Files.exists(path$iv2, (LinkOption[]) Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1))) {
                        pathTreeWalk$bfsIterator$1.L$0 = $this$iterator;
                        pathTreeWalk$bfsIterator$1.L$1 = queue;
                        pathTreeWalk$bfsIterator$1.L$2 = entriesReader;
                        pathTreeWalk$bfsIterator$1.L$3 = null;
                        pathTreeWalk$bfsIterator$1.L$4 = null;
                        pathTreeWalk$bfsIterator$1.L$5 = null;
                        pathTreeWalk$bfsIterator$1.label = 2;
                        if ($this$yieldIfNeeded$iv.yield(path$iv2, pathTreeWalk$bfsIterator$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $this$iterator3 = $this$iterator;
                        queue3 = queue;
                        entriesReader3 = entriesReader;
                        pathTreeWalk$bfsIterator$12 = pathTreeWalk$bfsIterator$1;
                        pathTreeWalk$bfsIterator$1 = pathTreeWalk$bfsIterator$12;
                        entriesReader = entriesReader3;
                        queue = queue3;
                        $this$iterator = $this$iterator3;
                        while (!queue.isEmpty()) {
                        }
                    }
                }
                return Unit.INSTANCE;
            case 1:
                path$iv = (Path) this.L$5;
                this_$iv = (PathTreeWalk) this.L$4;
                pathNode = (PathNode) this.L$3;
                entriesReader2 = (DirectoryEntriesReader) this.L$2;
                queue2 = (ArrayDeque) this.L$1;
                $this$iterator2 = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure($result);
                pathTreeWalk$bfsIterator$1 = this;
                path$iv2 = path$iv;
                entriesReader = entriesReader2;
                pathNode2 = pathNode;
                $this$iterator = $this$iterator2;
                ArrayDeque arrayDeque2 = queue2;
                this_$iv2 = this_$iv;
                queue = arrayDeque2;
                LinkOption[] linkOptions22 = this_$iv2.getLinkOptions();
                linkOptionArr = (LinkOption[]) Arrays.copyOf(linkOptions22, linkOptions22.length);
                if (Files.isDirectory(path$iv2, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
                }
                while (!queue.isEmpty()) {
                }
                return Unit.INSTANCE;
            case 2:
                DirectoryEntriesReader entriesReader5 = (DirectoryEntriesReader) this.L$2;
                ArrayDeque queue5 = (ArrayDeque) this.L$1;
                SequenceScope $this$iterator5 = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure($result);
                $this$iterator3 = $this$iterator5;
                queue3 = queue5;
                entriesReader3 = entriesReader5;
                pathTreeWalk$bfsIterator$12 = this;
                pathTreeWalk$bfsIterator$1 = pathTreeWalk$bfsIterator$12;
                entriesReader = entriesReader3;
                queue = queue3;
                $this$iterator = $this$iterator3;
                while (!queue.isEmpty()) {
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
