package com.github.pgroyal22;

import io.quarkus.runtime.Startup;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class UpdatesService {


    private static final Logger LOGGER = Logger.getLogger(UpdatesService.class);
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UpdatesService.class);

    private final List<LiveFeed> feeds = new ArrayList<>();
    private final List<String> feedUrls = Arrays.asList(
        "https://api-endpoint.mta.info/Dataservice/mtagtfsfeeds/nyct%2Fgtfs",
        "https://api-endpoint.mta.info/Dataservice/mtagtfsfeeds/nyct%2Fgtfs-ace",
        "https://api-endpoint.mta.info/Dataservice/mtagtfsfeeds/nyct%2Fgtfs-g",
        "https://api-endpoint.mta.info/Dataservice/mtagtfsfeeds/nyct%2Fgtfs-jz",
        "https://api-endpoint.mta.info/Dataservice/mtagtfsfeeds/nyct%2Fgtfs-nqrw",
        "https://api-endpoint.mta.info/Dataservice/mtagtfsfeeds/nyct%2Fgtfs-nqrw",
        "https://api-endpoint.mta.info/Dataservice/mtagtfsfeeds/nyct%2Fgtfs-l"
    );

    @Startup
    void init() {
        log.info("Initializing UpdatesService");
        try {
            for (String feedUrl : feedUrls) {
                feeds.add(new LiveFeed(new URI(feedUrl).toURL()));
            }
        } catch (MalformedURLException | URISyntaxException e) {
            log.error("Incorrect URL syntax on a GTFS feed", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("Error starting feed connection", e);
            throw new RuntimeException(e);
        }
    }

    @Scheduled(cron = "0/15 * * * * ?" )
    void fetchUpdates() {

        LiveFeed gFeed = feeds.get(2);

        for (var feedEntity : gFeed.getFeed().getEntityList()) {
           if (feedEntity.hasTripUpdate())  {
               System.out.println(feedEntity);
           }
        }
    }
}
