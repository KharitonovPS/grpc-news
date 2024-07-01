package org.kps.grpcserver.repository;

import lombok.extern.slf4j.Slf4j;
import org.kps.grpcmodel.model.News;
import org.kps.grpcmodel.model.Rating;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NewsRepository {

    public News check(String name) {
        log.info("Checking news repository...");
        return new News("1", name, 123, Rating.FIVE_STAR, "a1");

    }

}
