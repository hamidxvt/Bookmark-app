package com.pusher.client.example;

import com.pusher.client.channel.PrivateChannel;

/* loaded from: classes17.dex */
public class PrivateChannelExampleApp {
    private final PrivateChannel channel;
    private final String channelAuthorizationEndpoint = "http://localhost:3030/pusher/auth";
    private String channelName;
    private String channelsKey;
    private String cluster;
    private String eventName;

    public static void main(String[] args) {
        new PrivateChannelExampleApp(args);
    }

    /*  JADX ERROR: NullPointerException in pass: RegionMakerVisitor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.nodes.BlockNode.getId()" because "imPostDom" is null
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:169)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:61)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:49)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    /* JADX INFO: Infinite loop detected, blocks: 16, insns: 0 */
    PrivateChannelExampleApp(java.lang.String[] r10) {
        /*
            r9 = this;
            r9.<init>()
            java.lang.String r0 = "FILL_ME_IN"
            r9.channelsKey = r0
            java.lang.String r0 = "my-channel"
            r9.channelName = r0
            java.lang.String r0 = "my-event"
            r9.eventName = r0
            java.lang.String r0 = "eu"
            r9.cluster = r0
            java.lang.String r0 = "http://localhost:3030/pusher/auth"
            r9.channelAuthorizationEndpoint = r0
            int r1 = r10.length
            r2 = 0
            r3 = 1
            switch(r1) {
                case 1: goto L2c;
                case 2: goto L28;
                case 3: goto L23;
                case 4: goto L1e;
                default: goto L1d;
            }
        L1d:
            goto L30
        L1e:
            r1 = 3
            r1 = r10[r1]
            r9.cluster = r1
        L23:
            r1 = 2
            r1 = r10[r1]
            r9.eventName = r1
        L28:
            r1 = r10[r3]
            r9.channelName = r1
        L2c:
            r1 = r10[r2]
            r9.channelsKey = r1
        L30:
            com.pusher.client.util.HttpChannelAuthorizer r1 = new com.pusher.client.util.HttpChannelAuthorizer
            r1.<init>(r0)
            r0 = r1
            com.pusher.client.PusherOptions r1 = new com.pusher.client.PusherOptions
            r1.<init>()
            com.pusher.client.PusherOptions r1 = r1.setUseTLS(r3)
            java.lang.String r4 = r9.cluster
            com.pusher.client.PusherOptions r1 = r1.setCluster(r4)
            com.pusher.client.PusherOptions r1 = r1.setChannelAuthorizer(r0)
            com.pusher.client.Pusher r4 = new com.pusher.client.Pusher
            java.lang.String r5 = r9.channelsKey
            r4.<init>(r5, r1)
            com.pusher.client.example.PrivateChannelExampleApp$1 r5 = new com.pusher.client.example.PrivateChannelExampleApp$1
            r5.<init>()
            com.pusher.client.connection.ConnectionState[] r6 = new com.pusher.client.connection.ConnectionState[r2]
            r4.connect(r5, r6)
            com.pusher.client.example.PrivateChannelExampleApp$2 r6 = new com.pusher.client.example.PrivateChannelExampleApp$2
            r6.<init>()
            java.lang.String r7 = r9.channelName
            java.lang.String[] r3 = new java.lang.String[r3]
            java.lang.String r8 = r9.eventName
            r3[r2] = r8
            com.pusher.client.channel.PrivateChannel r2 = r4.subscribePrivate(r7, r6, r3)
            r9.channel = r2
        L6d:
            r2 = 1000(0x3e8, double:4.94E-321)
            java.lang.Thread.sleep(r2)     // Catch: java.lang.InterruptedException -> L73
        L72:
            goto L6d
        L73:
            r2 = move-exception
            r2.printStackTrace()
            goto L72
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pusher.client.example.PrivateChannelExampleApp.<init>(java.lang.String[]):void");
    }
}
