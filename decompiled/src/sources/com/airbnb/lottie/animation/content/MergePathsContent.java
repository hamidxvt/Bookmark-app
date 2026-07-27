package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import com.airbnb.lottie.model.content.MergePaths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/* loaded from: classes.dex */
public class MergePathsContent implements PathContent, GreedyContent {
    private final MergePaths mergePaths;
    private final String name;
    private final Path firstPath = new Path();
    private final Path remainderPath = new Path();
    private final Path path = new Path();
    private final List<PathContent> pathContents = new ArrayList();

    public MergePathsContent(MergePaths mergePaths) {
        this.name = mergePaths.getName();
        this.mergePaths = mergePaths;
    }

    @Override // com.airbnb.lottie.animation.content.GreedyContent
    public void absorbContent(ListIterator<Content> contents) {
        while (contents.hasPrevious() && contents.previous() != this) {
        }
        while (contents.hasPrevious()) {
            Content content = contents.previous();
            if (content instanceof PathContent) {
                this.pathContents.add((PathContent) content);
                contents.remove();
            }
        }
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> contentsBefore, List<Content> contentsAfter) {
        for (int i = 0; i < this.pathContents.size(); i++) {
            this.pathContents.get(i).setContents(contentsBefore, contentsAfter);
        }
    }

    @Override // com.airbnb.lottie.animation.content.PathContent
    public Path getPath() {
        this.path.reset();
        if (this.mergePaths.isHidden()) {
            return this.path;
        }
        switch (this.mergePaths.getMode()) {
            case MERGE:
                addPaths();
                break;
            case ADD:
                opFirstPathWithRest(Path.Op.UNION);
                break;
            case SUBTRACT:
                opFirstPathWithRest(Path.Op.REVERSE_DIFFERENCE);
                break;
            case INTERSECT:
                opFirstPathWithRest(Path.Op.INTERSECT);
                break;
            case EXCLUDE_INTERSECTIONS:
                opFirstPathWithRest(Path.Op.XOR);
                break;
        }
        return this.path;
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    private void addPaths() {
        for (int i = 0; i < this.pathContents.size(); i++) {
            this.path.addPath(this.pathContents.get(i).getPath());
        }
    }

    private void opFirstPathWithRest(Path.Op op) {
        this.remainderPath.reset();
        this.firstPath.reset();
        for (int i = this.pathContents.size() - 1; i >= 1; i--) {
            PathContent content = this.pathContents.get(i);
            if (content instanceof ContentGroup) {
                List<PathContent> pathList = ((ContentGroup) content).getPathList();
                for (int j = pathList.size() - 1; j >= 0; j--) {
                    Path path = pathList.get(j).getPath();
                    path.transform(((ContentGroup) content).getTransformationMatrix());
                    this.remainderPath.addPath(path);
                }
            } else {
                this.remainderPath.addPath(content.getPath());
            }
        }
        PathContent lastContent = this.pathContents.get(0);
        if (lastContent instanceof ContentGroup) {
            List<PathContent> pathList2 = ((ContentGroup) lastContent).getPathList();
            for (int j2 = 0; j2 < pathList2.size(); j2++) {
                Path path2 = pathList2.get(j2).getPath();
                path2.transform(((ContentGroup) lastContent).getTransformationMatrix());
                this.firstPath.addPath(path2);
            }
        } else {
            this.firstPath.set(lastContent.getPath());
        }
        this.path.op(this.firstPath, this.remainderPath, op);
    }
}
