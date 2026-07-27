package com.google.common.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes16.dex */
public final class FileBackedOutputStream extends OutputStream {

    @CheckForNull
    private File file;
    private final int fileThreshold;

    @CheckForNull
    private MemoryOutput memory;
    private OutputStream out;

    @CheckForNull
    private final File parentDirectory;
    private final boolean resetOnFinalize;
    private final ByteSource source;

    private static class MemoryOutput extends ByteArrayOutputStream {
        private MemoryOutput() {
        }

        byte[] getBuffer() {
            return this.buf;
        }

        int getCount() {
            return this.count;
        }
    }

    @CheckForNull
    synchronized File getFile() {
        return this.file;
    }

    public FileBackedOutputStream(int fileThreshold) {
        this(fileThreshold, false);
    }

    public FileBackedOutputStream(int fileThreshold, boolean resetOnFinalize) {
        this(fileThreshold, resetOnFinalize, null);
    }

    private FileBackedOutputStream(int fileThreshold, boolean resetOnFinalize, @CheckForNull File parentDirectory) {
        this.fileThreshold = fileThreshold;
        this.resetOnFinalize = resetOnFinalize;
        this.parentDirectory = parentDirectory;
        this.memory = new MemoryOutput();
        this.out = this.memory;
        if (resetOnFinalize) {
            this.source = new ByteSource() { // from class: com.google.common.io.FileBackedOutputStream.1
                @Override // com.google.common.io.ByteSource
                public InputStream openStream() throws IOException {
                    return FileBackedOutputStream.this.openInputStream();
                }

                protected void finalize() {
                    try {
                        FileBackedOutputStream.this.reset();
                    } catch (Throwable t) {
                        t.printStackTrace(System.err);
                    }
                }
            };
        } else {
            this.source = new ByteSource() { // from class: com.google.common.io.FileBackedOutputStream.2
                @Override // com.google.common.io.ByteSource
                public InputStream openStream() throws IOException {
                    return FileBackedOutputStream.this.openInputStream();
                }
            };
        }
    }

    public ByteSource asByteSource() {
        return this.source;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized InputStream openInputStream() throws IOException {
        if (this.file != null) {
            return new FileInputStream(this.file);
        }
        Objects.requireNonNull(this.memory);
        return new ByteArrayInputStream(this.memory.getBuffer(), 0, this.memory.getCount());
    }

    public synchronized void reset() throws IOException {
        try {
            close();
            if (this.memory == null) {
                this.memory = new MemoryOutput();
            } else {
                this.memory.reset();
            }
            this.out = this.memory;
            if (this.file != null) {
                File deleteMe = this.file;
                this.file = null;
                if (!deleteMe.delete()) {
                    String valueOf = String.valueOf(deleteMe);
                    throw new IOException(new StringBuilder(String.valueOf(valueOf).length() + 18).append("Could not delete: ").append(valueOf).toString());
                }
            }
        } catch (Throwable th) {
            if (this.memory == null) {
                this.memory = new MemoryOutput();
            } else {
                this.memory.reset();
            }
            this.out = this.memory;
            if (this.file != null) {
                File deleteMe2 = this.file;
                this.file = null;
                if (!deleteMe2.delete()) {
                    String valueOf2 = String.valueOf(deleteMe2);
                    throw new IOException(new StringBuilder(String.valueOf(valueOf2).length() + 18).append("Could not delete: ").append(valueOf2).toString());
                }
            }
            throw th;
        }
    }

    @Override // java.io.OutputStream
    public synchronized void write(int b) throws IOException {
        update(1);
        this.out.write(b);
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] b, int off, int len) throws IOException {
        update(len);
        this.out.write(b, off, len);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.out.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public synchronized void flush() throws IOException {
        this.out.flush();
    }

    private void update(int len) throws IOException {
        if (this.memory != null && this.memory.getCount() + len > this.fileThreshold) {
            File temp = File.createTempFile("FileBackedOutputStream", null, this.parentDirectory);
            if (this.resetOnFinalize) {
                temp.deleteOnExit();
            }
            try {
                FileOutputStream transfer = new FileOutputStream(temp);
                transfer.write(this.memory.getBuffer(), 0, this.memory.getCount());
                transfer.flush();
                this.out = transfer;
                this.file = temp;
                this.memory = null;
            } catch (IOException e) {
                temp.delete();
                throw e;
            }
        }
    }
}
