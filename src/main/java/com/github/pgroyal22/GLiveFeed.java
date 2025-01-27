package com.github.pgroyal22;

import com.google.transit.realtime.GtfsRealtime;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Singleton
public class GLiveFeed {

    private GtfsRealtime.FeedMessage feed;

    public GLiveFeed() throws IOException, URISyntaxException {
        URL url = new URI("https://api-endpoint.mta.info/Dataservice/mtagtfsfeeds/nyct%2Fgtfs-g").toURL();
        feed = GtfsRealtime.FeedMessage.parseFrom(url.openStream());

    }

    public GtfsRealtime.FeedMessage getFeed() {
        return feed;
    }
}
