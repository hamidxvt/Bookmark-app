package com.budiyev.android.codescanner;

import android.os.Process;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import java.lang.Thread;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
final class Decoder {
    private volatile DecodeCallback mCallback;
    private final Map<DecodeHintType, Object> mHints;
    private volatile State mState;
    private final StateListener mStateListener;
    private volatile DecodeTask mTask;
    private final Object mTaskLock = new Object();
    private final MultiFormatReader mReader = new MultiFormatReader();
    private final DecoderThread mDecoderThread = new DecoderThread();

    public enum State {
        INITIALIZED,
        IDLE,
        DECODING,
        DECODED,
        STOPPED
    }

    public interface StateListener {
        boolean onStateChanged(State state);
    }

    public Decoder(StateListener stateListener, Thread.UncaughtExceptionHandler exceptionHandler, List<BarcodeFormat> formats, DecodeCallback callback) {
        this.mDecoderThread.setUncaughtExceptionHandler(exceptionHandler);
        this.mHints = new EnumMap(DecodeHintType.class);
        this.mHints.put(DecodeHintType.POSSIBLE_FORMATS, formats);
        this.mReader.setHints(this.mHints);
        this.mCallback = callback;
        this.mStateListener = stateListener;
        this.mState = State.INITIALIZED;
    }

    public void setFormats(List<BarcodeFormat> formats) {
        this.mHints.put(DecodeHintType.POSSIBLE_FORMATS, formats);
        this.mReader.setHints(this.mHints);
    }

    public void setCallback(DecodeCallback callback) {
        this.mCallback = callback;
    }

    public void decode(DecodeTask task) {
        synchronized (this.mTaskLock) {
            if (this.mState != State.STOPPED) {
                this.mTask = task;
                this.mTaskLock.notify();
            }
        }
    }

    public void start() {
        if (this.mState != State.INITIALIZED) {
            throw new IllegalStateException("Illegal decoder state");
        }
        this.mDecoderThread.start();
    }

    public void shutdown() {
        this.mDecoderThread.interrupt();
        this.mTask = null;
    }

    public State getState() {
        return this.mState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setState(State state) {
        this.mState = state;
        return this.mStateListener.onStateChanged(state);
    }

    private final class DecoderThread extends Thread {
        public DecoderThread() {
            super("cs-decoder");
        }

        /* JADX WARN: Removed duplicated region for block: B:37:0x0038 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0005 A[SYNTHETIC] */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            DecodeCallback callback;
            DecodeCallback callback2;
            DecodeTask t;
            DecodeCallback callback3;
            Process.setThreadPriority(10);
            while (true) {
                Decoder.this.setState(State.IDLE);
                Result result = null;
                while (true) {
                    try {
                        synchronized (Decoder.this.mTaskLock) {
                            t = Decoder.this.mTask;
                            if (t != null) {
                                break;
                            }
                            try {
                                Decoder.this.mTaskLock.wait();
                            } catch (InterruptedException e) {
                                Decoder.this.setState(State.STOPPED);
                                if (0 != 0) {
                                    Decoder.this.mTask = null;
                                    if (!Decoder.this.setState(State.DECODED) || (callback3 = Decoder.this.mCallback) == null) {
                                        return;
                                    }
                                    callback3.onDecoded(null);
                                    return;
                                }
                                return;
                            }
                        }
                        Decoder.this.setState(State.DECODING);
                        result = t.decode(Decoder.this.mReader);
                    } catch (ReaderException e2) {
                        if (0 != 0) {
                            Decoder.this.mTask = null;
                            if (Decoder.this.setState(State.DECODED) && (callback2 = Decoder.this.mCallback) != null) {
                            }
                        }
                    } catch (Throwable th) {
                        if (0 != 0) {
                            Decoder.this.mTask = null;
                            if (Decoder.this.setState(State.DECODED) && (callback = Decoder.this.mCallback) != null) {
                                callback.onDecoded(null);
                            }
                        }
                        throw th;
                    }
                    if (result == null) {
                        Decoder.this.mTask = null;
                        if (Decoder.this.setState(State.DECODED) && (callback2 = Decoder.this.mCallback) != null) {
                            callback2.onDecoded(result);
                        }
                    }
                }
                Decoder.this.mTask = null;
                Decoder.this.setState(State.DECODING);
                result = t.decode(Decoder.this.mReader);
                if (result == null) {
                }
            }
        }
    }
}
