package org.java_websocket.framing;

import org.java_websocket.enums.Opcode;

/* loaded from: classes17.dex */
public class PingFrame extends ControlFrame {
    public PingFrame() {
        super(Opcode.PING);
    }
}
