package org.kps.grpcserver.news.repository;

import lombok.extern.slf4j.Slf4j;
import org.kps.grpcmodel.model.Rating;
import org.kps.grpcserver.author.entity.AuthorEntity;
import org.kps.grpcserver.news.entity.NewsEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class NewsRepository {

    public NewsEntity findByName(String name) {
        log.info("find by name {}...", name);
        return new NewsEntity(name, 123, Rating.FIVE_STAR, new AuthorEntity("test", "testov"));

    }

    public List<NewsEntity> findAll() {
        log.info("find all news...");

        return List.of(
                new NewsEntity("name1", 123, Rating.FIVE_STAR, new AuthorEntity("ivan", "ivanov")),
                new NewsEntity("name2", 124, Rating.FOUR_STAR, new AuthorEntity("ivan2", "ivanov2"))
        );
    }

}
