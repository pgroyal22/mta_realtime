package com.github.pgroyal22;

import com.google.transit.realtime.GtfsRealtime;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdatesService {

    @Inject
    GLiveFeed gliveFeed;

    @Scheduled(cron = "0/5 * * * * ?" )
    void fetchUpdates() {

        for (GtfsRealtime.FeedEntity entity : gliveFeed.getFeed().getEntityList()) {
           if (entity.hasTripUpdate())  {
               System.out.println(entity.getTripUpdate());
           }
        }
    }
}
