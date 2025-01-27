package com.github.pgroyal22;

import com.google.transit.realtime.GtfsRealtime;
import com.google.transit.realtime.GtfsRealtimeNYCT;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LiveFeed {

    private GtfsRealtime.FeedMessage feed;

    public LiveFeed(URL url) throws IOException {
        feed = GtfsRealtime.FeedMessage.parseFrom(url.openStream());
    }

    public GtfsRealtime.FeedMessage getFeed() {
        return feed;
    }
}
