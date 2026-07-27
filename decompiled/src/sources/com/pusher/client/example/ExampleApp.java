package com.pusher.client.example;

/* loaded from: classes17.dex */
public class ExampleApp {
    private String channelName;
    private String channelsKey;
    private String cluster;
    private String eventName;

    public static void main(String[] args) {
        new ExampleApp(args);
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
    public ExampleApp(java.lang.String[] r9) {
        /*
            r8 = this;
            r8.<init>()
            java.lang.String r0 = "FILL_ME_IN"
            r8.channelsKey = r0
            java.lang.String r0 = "my-channel"
            r8.channelName = r0
            java.lang.String r0 = "my-event"
            r8.eventName = r0
            java.lang.String r0 = "eu"
            r8.cluster = r0
            int r0 = r9.length
            r1 = 0
            r2 = 1
            switch(r0) {
                case 1: goto L28;
                case 2: goto L24;
                case 3: goto L1f;
                case 4: goto L1a;
                default: goto L19;
            }
        L19:
            goto L2c
        L1a:
            r0 = 3
            r0 = r9[r0]
            r8.cluster = r0
        L1f:
            r0 = 2
            r0 = r9[r0]
            r8.eventName = r0
        L24:
            r0 = r9[r2]
            r8.channelName = r0
        L28:
            r0 = r9[r1]
            r8.channelsKey = r0
        L2c:
            com.pusher.client.PusherOptions r0 = new com.pusher.client.PusherOptions
            r0.<init>()
            com.pusher.client.PusherOptions r0 = r0.setUseTLS(r2)
            java.lang.String r3 = r8.cluster
            com.pusher.client.PusherOptions r0 = r0.setCluster(r3)
            com.pusher.client.Pusher r3 = new com.pusher.client.Pusher
            java.lang.String r4 = r8.channelsKey
            r3.<init>(r4, r0)
            com.pusher.client.example.ExampleApp$1 r4 = new com.pusher.client.example.ExampleApp$1
            r4.<init>()
            com.pusher.client.connection.ConnectionState[] r5 = new com.pusher.client.connection.ConnectionState[r1]
            r3.connect(r4, r5)
            com.pusher.client.example.ExampleApp$2 r5 = new com.pusher.client.example.ExampleApp$2
            r5.<init>()
            java.lang.String r6 = r8.channelName
            java.lang.String[] r2 = new java.lang.String[r2]
            java.lang.String r7 = r8.eventName
            r2[r1] = r7
            com.pusher.client.channel.Channel r1 = r3.subscribe(r6, r5, r2)
        L5d:
            r6 = 1000(0x3e8, double:4.94E-321)
            java.lang.Thread.sleep(r6)     // Catch: java.lang.InterruptedException -> L63
        L62:
            goto L5d
        L63:
            r2 = move-exception
            r2.printStackTrace()
            goto L62
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pusher.client.example.ExampleApp.<init>(java.lang.String[]):void");
    }
}
