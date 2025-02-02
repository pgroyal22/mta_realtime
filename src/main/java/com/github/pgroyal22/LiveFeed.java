package com.github.pgroyal22;

import com.google.protobuf.ExtensionRegistry;
import com.google.transit.realtime.GtfsRealtime;
import com.google.transit.realtime.GtfsRealtimeNYCT;

import java.io.IOException;
import java.net.URL;

public class LiveFeed {

    private final GtfsRealtime.FeedMessage feed;

    public LiveFeed(URL url) throws IOException {

        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(GtfsRealtimeNYCT.nyctFeedHeader);
        registry.add(GtfsRealtimeNYCT.nyctStopTimeUpdate);
        registry.add(GtfsRealtimeNYCT.nyctTripDescriptor);
        feed = GtfsRealtime.FeedMessage.parseFrom(url.openStream(), registry);
    }

    public GtfsRealtime.FeedMessage getFeed() {
        return feed;
    }
}
