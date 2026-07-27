package com.ingenious.androidbookmarksalesupgrade.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.koin.InjectUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppRepository.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/repository/AppRepository;", "", "<init>", "()V", "userRepository", "Lcom/ingenious/androidbookmarksalesupgrade/repository/UserRepository;", "getUserRepository", "()Lcom/ingenious/androidbookmarksalesupgrade/repository/UserRepository;", "setUserRepository", "(Lcom/ingenious/androidbookmarksalesupgrade/repository/UserRepository;)V", "homeRepository", "Lcom/ingenious/androidbookmarksalesupgrade/repository/HomeRepository;", "getHomeRepository", "()Lcom/ingenious/androidbookmarksalesupgrade/repository/HomeRepository;", "setHomeRepository", "(Lcom/ingenious/androidbookmarksalesupgrade/repository/HomeRepository;)V", "visitRepository", "Lcom/ingenious/androidbookmarksalesupgrade/repository/VisitRepository;", "getVisitRepository", "()Lcom/ingenious/androidbookmarksalesupgrade/repository/VisitRepository;", "setVisitRepository", "(Lcom/ingenious/androidbookmarksalesupgrade/repository/VisitRepository;)V", "chatRepository", "Lcom/ingenious/androidbookmarksalesupgrade/repository/ChatRepository;", "getChatRepository", "()Lcom/ingenious/androidbookmarksalesupgrade/repository/ChatRepository;", "setChatRepository", "(Lcom/ingenious/androidbookmarksalesupgrade/repository/ChatRepository;)V", "productRepository", "Lcom/ingenious/androidbookmarksalesupgrade/repository/ProductRepository;", "getProductRepository", "()Lcom/ingenious/androidbookmarksalesupgrade/repository/ProductRepository;", "inventoryRepository", "Lcom/ingenious/androidbookmarksalesupgrade/repository/InventoryRepository;", "getInventoryRepository", "()Lcom/ingenious/androidbookmarksalesupgrade/repository/InventoryRepository;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes15.dex */
public final class AppRepository {
    private UserRepository userRepository = InjectUtils.INSTANCE.getUserRepository();
    private HomeRepository homeRepository = InjectUtils.INSTANCE.getHomeRepository();
    private VisitRepository visitRepository = InjectUtils.INSTANCE.getVisitRepository();
    private ChatRepository chatRepository = InjectUtils.INSTANCE.getChatRepository();
    private final ProductRepository productRepository = InjectUtils.INSTANCE.getProductRepository();
    private final InventoryRepository inventoryRepository = InjectUtils.INSTANCE.getInventoryRepository();

    public final UserRepository getUserRepository() {
        return this.userRepository;
    }

    public final void setUserRepository(UserRepository userRepository) {
        Intrinsics.checkNotNullParameter(userRepository, "<set-?>");
        this.userRepository = userRepository;
    }

    public final HomeRepository getHomeRepository() {
        return this.homeRepository;
    }

    public final void setHomeRepository(HomeRepository homeRepository) {
        Intrinsics.checkNotNullParameter(homeRepository, "<set-?>");
        this.homeRepository = homeRepository;
    }

    public final VisitRepository getVisitRepository() {
        return this.visitRepository;
    }

    public final void setVisitRepository(VisitRepository visitRepository) {
        Intrinsics.checkNotNullParameter(visitRepository, "<set-?>");
        this.visitRepository = visitRepository;
    }

    public final ChatRepository getChatRepository() {
        return this.chatRepository;
    }

    public final void setChatRepository(ChatRepository chatRepository) {
        Intrinsics.checkNotNullParameter(chatRepository, "<set-?>");
        this.chatRepository = chatRepository;
    }

    public final ProductRepository getProductRepository() {
        return this.productRepository;
    }

    public final InventoryRepository getInventoryRepository() {
        return this.inventoryRepository;
    }
}
