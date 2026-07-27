package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Priority;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.runtime.logging.Logging;

/* loaded from: classes16.dex */
public final class ForcedSender {
    private static final String LOG_TAG = "ForcedSender";

    public static void sendBlocking(Transport<?> transport, Priority priority) {
        if (transport instanceof TransportImpl) {
            TransportContext context = ((TransportImpl) transport).getTransportContext().withPriority(priority);
            TransportRuntime.getInstance().getUploader().logAndUpdateState(context, 1);
        } else {
            Logging.w(LOG_TAG, "Expected instance of `TransportImpl`, got `%s`.", transport);
        }
    }

    private ForcedSender() {
    }
}
