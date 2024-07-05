package org.kps.grpcserver.repository;

import lombok.extern.slf4j.Slf4j;
import org.kps.grpcmodel.model.NewsDto;
import org.kps.grpcmodel.model.Rating;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class NewsRepository {

    public NewsDto findByName(String name) {
        log.info("find by name {}...", name);
        return new NewsDto("1", name, 123, Rating.FIVE_STAR, "a1");

    }

    public List<NewsDto> findAll() {
        log.info("find all news...");

        return List.of(new NewsDto("1", "name1", 123, Rating.FIVE_STAR, "a1"),
                new NewsDto("2", "name2", 124, Rating.FOUR_STAR, "a2"));
    }

}
