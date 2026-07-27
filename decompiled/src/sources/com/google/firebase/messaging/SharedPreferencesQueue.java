package com.google.firebase.messaging;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes16.dex */
final class SharedPreferencesQueue {
    private final String itemSeparator;
    private final String queueName;
    private final SharedPreferences sharedPreferences;
    private final Executor syncExecutor;
    final ArrayDeque<String> internalQueue = new ArrayDeque<>();
    private boolean bulkOperation = false;

    private SharedPreferencesQueue(SharedPreferences sharedPreferences, String queueName, String itemSeparator, Executor syncExecutor) {
        this.sharedPreferences = sharedPreferences;
        this.queueName = queueName;
        this.itemSeparator = itemSeparator;
        this.syncExecutor = syncExecutor;
    }

    static SharedPreferencesQueue createInstance(SharedPreferences sharedPreferences, String queueName, String itemSeparator, Executor syncExecutor) {
        SharedPreferencesQueue queue = new SharedPreferencesQueue(sharedPreferences, queueName, itemSeparator, syncExecutor);
        queue.initQueue();
        return queue;
    }

    private void initQueue() {
        synchronized (this.internalQueue) {
            this.internalQueue.clear();
            String queue = this.sharedPreferences.getString(this.queueName, "");
            if (!TextUtils.isEmpty(queue) && queue.contains(this.itemSeparator)) {
                String[] queueItems = queue.split(this.itemSeparator, -1);
                if (queueItems.length == 0) {
                    Log.e(Constants.TAG, "Corrupted queue. Please check the queue contents and item separator provided");
                }
                for (String item : queueItems) {
                    if (!TextUtils.isEmpty(item)) {
                        this.internalQueue.add(item);
                    }
                }
            }
        }
    }

    public List<String> toList() {
        ArrayList arrayList;
        synchronized (this.internalQueue) {
            arrayList = new ArrayList(this.internalQueue);
        }
        return arrayList;
    }

    public boolean add(String item) {
        boolean checkAndSyncState;
        if (TextUtils.isEmpty(item) || item.contains(this.itemSeparator)) {
            return false;
        }
        synchronized (this.internalQueue) {
            checkAndSyncState = checkAndSyncState(this.internalQueue.add(item));
        }
        return checkAndSyncState;
    }

    private String checkAndSyncState(String transactionValue) {
        checkAndSyncState(transactionValue != null);
        return transactionValue;
    }

    private boolean checkAndSyncState(boolean transactionState) {
        if (transactionState && !this.bulkOperation) {
            syncStateAsync();
        }
        return transactionState;
    }

    private void syncStateAsync() {
        this.syncExecutor.execute(new Runnable() { // from class: com.google.firebase.messaging.SharedPreferencesQueue$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SharedPreferencesQueue.this.syncState();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncState() {
        synchronized (this.internalQueue) {
            this.sharedPreferences.edit().putString(this.queueName, serialize()).commit();
        }
    }

    public String serialize() {
        StringBuilder builder = new StringBuilder();
        Iterator<String> it = this.internalQueue.iterator();
        while (it.hasNext()) {
            String item = it.next();
            builder.append(item).append(this.itemSeparator);
        }
        return builder.toString();
    }

    public void beginTransaction() {
        this.bulkOperation = true;
    }

    void beginTransactionSync() {
        synchronized (this.internalQueue) {
            beginTransaction();
        }
    }

    public void commitTransaction() {
        this.bulkOperation = false;
        syncStateAsync();
    }

    void commitTransactionSync() {
        synchronized (this.internalQueue) {
            commitTransaction();
        }
    }

    public String serializeSync() {
        String serialize;
        synchronized (this.internalQueue) {
            serialize = serialize();
        }
        return serialize;
    }

    public boolean remove(Object o) {
        boolean checkAndSyncState;
        synchronized (this.internalQueue) {
            checkAndSyncState = checkAndSyncState(this.internalQueue.remove(o));
        }
        return checkAndSyncState;
    }

    public String remove() {
        String checkAndSyncState;
        synchronized (this.internalQueue) {
            checkAndSyncState = checkAndSyncState(this.internalQueue.remove());
        }
        return checkAndSyncState;
    }

    public void clear() {
        synchronized (this.internalQueue) {
            this.internalQueue.clear();
            checkAndSyncState(true);
        }
    }

    public String peek() {
        String peek;
        synchronized (this.internalQueue) {
            peek = this.internalQueue.peek();
        }
        return peek;
    }

    public int size() {
        int size;
        synchronized (this.internalQueue) {
            size = this.internalQueue.size();
        }
        return size;
    }
}
