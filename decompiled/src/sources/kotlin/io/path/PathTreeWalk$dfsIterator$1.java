package kotlin.io.path;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.nio.file.FileSystemLoopException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;

/* compiled from: PathTreeWalk.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Ljava/nio/file/Path;"}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "kotlin.io.path.PathTreeWalk$dfsIterator$1", f = "PathTreeWalk.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3}, l = {191, 197, 210, 216}, m = "invokeSuspend", n = {"$this$iterator", "stack", "entriesReader", "startNode", "this_$iv", "path$iv", "$this$iterator", "stack", "entriesReader", "$this$iterator", "stack", "entriesReader", "pathNode", "this_$iv", "path$iv", "$this$iterator", "stack", "entriesReader"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2"})
/* loaded from: classes17.dex */
final class PathTreeWalk$dfsIterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    final /* synthetic */ PathTreeWalk this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PathTreeWalk$dfsIterator$1(PathTreeWalk pathTreeWalk, Continuation<? super PathTreeWalk$dfsIterator$1> continuation) {
        super(2, continuation);
        this.this$0 = pathTreeWalk;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PathTreeWalk$dfsIterator$1 pathTreeWalk$dfsIterator$1 = new PathTreeWalk$dfsIterator$1(this.this$0, continuation);
        pathTreeWalk$dfsIterator$1.L$0 = obj;
        return pathTreeWalk$dfsIterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super Path> sequenceScope, Continuation<? super Unit> continuation) {
        return ((PathTreeWalk$dfsIterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0178, code lost:
    
        r5 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0132  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x025e -> B:7:0x0260). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        SequenceScope $this$iterator;
        ArrayDeque stack;
        boolean followLinks;
        DirectoryEntriesReader entriesReader;
        PathNode startNode;
        Path path;
        Path path2;
        Object keyOf;
        PathTreeWalk this_$iv;
        Path path$iv;
        PathTreeWalk$dfsIterator$1 pathTreeWalk$dfsIterator$1;
        boolean createsCycle;
        ArrayDeque stack2;
        PathTreeWalk this_$iv2;
        DirectoryEntriesReader entriesReader2;
        Path path$iv2;
        PathNode startNode2;
        PathNode startNode3;
        SequenceScope $this$iterator2;
        LinkOption[] linkOptionArr;
        Path path$iv3;
        PathTreeWalk this_$iv3;
        PathNode pathNode;
        DirectoryEntriesReader entriesReader3;
        ArrayDeque stack3;
        SequenceScope $this$iterator3;
        Path path$iv4;
        PathTreeWalk this_$iv4;
        PathNode pathNode2;
        LinkOption[] linkOptionArr2;
        PathTreeWalk$dfsIterator$1 pathTreeWalk$dfsIterator$12;
        boolean createsCycle2;
        PathTreeWalk$dfsIterator$1 pathTreeWalk$dfsIterator$13;
        PathTreeWalk$dfsIterator$1 pathTreeWalk$dfsIterator$14;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        PathTreeWalk$dfsIterator$1 pathTreeWalk$dfsIterator$15 = null;
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                $this$iterator = (SequenceScope) this.L$0;
                stack = new ArrayDeque();
                followLinks = this.this$0.getFollowLinks();
                entriesReader = new DirectoryEntriesReader(followLinks);
                path = this.this$0.start;
                path2 = this.this$0.start;
                keyOf = PathTreeWalkKt.keyOf(path2, this.this$0.getLinkOptions());
                startNode = new PathNode(path, keyOf, null);
                this_$iv = this.this$0;
                path$iv = startNode.getPath();
                if (startNode.getParent() != null) {
                    PathsKt.checkFileName(path$iv);
                }
                LinkOption[] linkOptions = this_$iv.getLinkOptions();
                LinkOption[] linkOptionArr3 = (LinkOption[]) Arrays.copyOf(linkOptions, linkOptions.length);
                if (!Files.isDirectory(path$iv, (LinkOption[]) Arrays.copyOf(linkOptionArr3, linkOptionArr3.length))) {
                    if (!Files.exists(path$iv, (LinkOption[]) Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1))) {
                        pathTreeWalk$dfsIterator$1 = this;
                        while (!stack.isEmpty()) {
                        }
                        return Unit.INSTANCE;
                    }
                    this.L$0 = $this$iterator;
                    this.L$1 = stack;
                    this.L$2 = entriesReader;
                    this.label = 2;
                    if ($this$iterator.yield(path$iv, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pathTreeWalk$dfsIterator$1 = this;
                    while (!stack.isEmpty()) {
                    }
                    return Unit.INSTANCE;
                }
                createsCycle = PathTreeWalkKt.createsCycle(startNode);
                if (createsCycle) {
                    throw new FileSystemLoopException(path$iv.toString());
                }
                if (this_$iv.getIncludeDirectories()) {
                    this.L$0 = $this$iterator;
                    this.L$1 = stack;
                    this.L$2 = entriesReader;
                    this.L$3 = startNode;
                    this.L$4 = this_$iv;
                    this.L$5 = path$iv;
                    this.label = 1;
                    if ($this$iterator.yield(path$iv, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    stack2 = stack;
                    this_$iv2 = this_$iv;
                    entriesReader2 = entriesReader;
                    path$iv2 = path$iv;
                    startNode2 = startNode;
                    startNode3 = null;
                    $this$iterator2 = $this$iterator;
                    path$iv = path$iv2;
                    entriesReader = entriesReader2;
                    this_$iv = this_$iv2;
                    stack = stack2;
                    startNode = startNode2;
                    $this$iterator = $this$iterator2;
                }
                LinkOption[] linkOptions2 = this_$iv.getLinkOptions();
                linkOptionArr = (LinkOption[]) Arrays.copyOf(linkOptions2, linkOptions2.length);
                if (Files.isDirectory(path$iv, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
                    List entries = entriesReader.readEntries(startNode);
                    startNode.setContentIterator(entries.iterator());
                    stack.addLast(startNode);
                }
                pathTreeWalk$dfsIterator$1 = this;
                while (!stack.isEmpty()) {
                    PathNode topNode = (PathNode) stack.last();
                    Iterator topIterator = topNode.getContentIterator();
                    Intrinsics.checkNotNull(topIterator);
                    if (topIterator.hasNext()) {
                        pathNode2 = topIterator.next();
                        this_$iv4 = pathTreeWalk$dfsIterator$1.this$0;
                        SequenceScope $this$yieldIfNeeded$iv = $this$iterator;
                        path$iv4 = pathNode2.getPath();
                        if (pathNode2.getParent() != null) {
                            PathsKt.checkFileName(path$iv4);
                        }
                        LinkOption[] linkOptions3 = this_$iv4.getLinkOptions();
                        LinkOption[] linkOptionArr4 = (LinkOption[]) Arrays.copyOf(linkOptions3, linkOptions3.length);
                        if (Files.isDirectory(path$iv4, (LinkOption[]) Arrays.copyOf(linkOptionArr4, linkOptionArr4.length))) {
                            createsCycle2 = PathTreeWalkKt.createsCycle(pathNode2);
                            if (createsCycle2) {
                                throw new FileSystemLoopException(path$iv4.toString());
                            }
                            if (this_$iv4.getIncludeDirectories()) {
                                pathTreeWalk$dfsIterator$1.L$0 = $this$iterator;
                                pathTreeWalk$dfsIterator$1.L$1 = stack;
                                pathTreeWalk$dfsIterator$1.L$2 = entriesReader;
                                pathTreeWalk$dfsIterator$1.L$3 = pathNode2;
                                pathTreeWalk$dfsIterator$1.L$4 = this_$iv4;
                                pathTreeWalk$dfsIterator$1.L$5 = path$iv4;
                                pathTreeWalk$dfsIterator$1.label = 3;
                                if ($this$yieldIfNeeded$iv.yield(path$iv4, pathTreeWalk$dfsIterator$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $this$iterator3 = $this$iterator;
                                pathNode = pathNode2;
                                entriesReader3 = entriesReader;
                                path$iv3 = path$iv4;
                                stack3 = stack;
                                this_$iv3 = this_$iv4;
                                path$iv4 = path$iv3;
                                entriesReader = entriesReader3;
                                pathNode2 = pathNode;
                                $this$iterator = $this$iterator3;
                                ArrayDeque arrayDeque = stack3;
                                this_$iv4 = this_$iv3;
                                stack = arrayDeque;
                            }
                            LinkOption[] linkOptions4 = this_$iv4.getLinkOptions();
                            linkOptionArr2 = (LinkOption[]) Arrays.copyOf(linkOptions4, linkOptions4.length);
                            if (Files.isDirectory(path$iv4, (LinkOption[]) Arrays.copyOf(linkOptionArr2, linkOptionArr2.length))) {
                                List entries2 = entriesReader.readEntries(pathNode2);
                                pathNode2.setContentIterator(entries2.iterator());
                                stack.addLast(pathNode2);
                            }
                            pathTreeWalk$dfsIterator$15 = null;
                            while (!stack.isEmpty()) {
                            }
                        } else if (Files.exists(path$iv4, (LinkOption[]) Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1))) {
                            pathTreeWalk$dfsIterator$1.L$0 = $this$iterator;
                            pathTreeWalk$dfsIterator$1.L$1 = stack;
                            pathTreeWalk$dfsIterator$1.L$2 = entriesReader;
                            pathTreeWalk$dfsIterator$13 = null;
                            pathTreeWalk$dfsIterator$1.L$3 = null;
                            pathTreeWalk$dfsIterator$1.L$4 = null;
                            pathTreeWalk$dfsIterator$1.L$5 = null;
                            pathTreeWalk$dfsIterator$1.label = 4;
                            if ($this$yieldIfNeeded$iv.yield(path$iv4, pathTreeWalk$dfsIterator$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            pathTreeWalk$dfsIterator$14 = pathTreeWalk$dfsIterator$1;
                            pathTreeWalk$dfsIterator$1 = pathTreeWalk$dfsIterator$14;
                            pathTreeWalk$dfsIterator$15 = pathTreeWalk$dfsIterator$13;
                            while (!stack.isEmpty()) {
                            }
                        } else {
                            pathTreeWalk$dfsIterator$12 = null;
                        }
                    } else {
                        pathTreeWalk$dfsIterator$12 = pathTreeWalk$dfsIterator$15;
                        stack.removeLast();
                    }
                    pathTreeWalk$dfsIterator$15 = pathTreeWalk$dfsIterator$12;
                }
                return Unit.INSTANCE;
            case 1:
                startNode3 = null;
                path$iv2 = (Path) this.L$5;
                this_$iv2 = (PathTreeWalk) this.L$4;
                startNode2 = (PathNode) this.L$3;
                entriesReader2 = (DirectoryEntriesReader) this.L$2;
                stack2 = (ArrayDeque) this.L$1;
                $this$iterator2 = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure($result);
                path$iv = path$iv2;
                entriesReader = entriesReader2;
                this_$iv = this_$iv2;
                stack = stack2;
                startNode = startNode2;
                $this$iterator = $this$iterator2;
                LinkOption[] linkOptions22 = this_$iv.getLinkOptions();
                linkOptionArr = (LinkOption[]) Arrays.copyOf(linkOptions22, linkOptions22.length);
                if (Files.isDirectory(path$iv, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
                }
                pathTreeWalk$dfsIterator$1 = this;
                while (!stack.isEmpty()) {
                }
                return Unit.INSTANCE;
            case 2:
                entriesReader = (DirectoryEntriesReader) this.L$2;
                stack = (ArrayDeque) this.L$1;
                $this$iterator = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure($result);
                pathTreeWalk$dfsIterator$1 = this;
                while (!stack.isEmpty()) {
                }
                return Unit.INSTANCE;
            case 3:
                path$iv3 = (Path) this.L$5;
                this_$iv3 = (PathTreeWalk) this.L$4;
                pathNode = (PathNode) this.L$3;
                entriesReader3 = (DirectoryEntriesReader) this.L$2;
                stack3 = (ArrayDeque) this.L$1;
                $this$iterator3 = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure($result);
                pathTreeWalk$dfsIterator$1 = this;
                path$iv4 = path$iv3;
                entriesReader = entriesReader3;
                pathNode2 = pathNode;
                $this$iterator = $this$iterator3;
                ArrayDeque arrayDeque2 = stack3;
                this_$iv4 = this_$iv3;
                stack = arrayDeque2;
                LinkOption[] linkOptions42 = this_$iv4.getLinkOptions();
                linkOptionArr2 = (LinkOption[]) Arrays.copyOf(linkOptions42, linkOptions42.length);
                if (Files.isDirectory(path$iv4, (LinkOption[]) Arrays.copyOf(linkOptionArr2, linkOptionArr2.length))) {
                }
                pathTreeWalk$dfsIterator$15 = null;
                while (!stack.isEmpty()) {
                }
                return Unit.INSTANCE;
            case 4:
                entriesReader = (DirectoryEntriesReader) this.L$2;
                stack = (ArrayDeque) this.L$1;
                $this$iterator = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure($result);
                pathTreeWalk$dfsIterator$13 = null;
                pathTreeWalk$dfsIterator$14 = this;
                pathTreeWalk$dfsIterator$1 = pathTreeWalk$dfsIterator$14;
                pathTreeWalk$dfsIterator$15 = pathTreeWalk$dfsIterator$13;
                while (!stack.isEmpty()) {
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
