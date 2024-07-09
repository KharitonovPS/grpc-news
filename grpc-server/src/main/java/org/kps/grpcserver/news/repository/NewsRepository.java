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
        return generateStub(name);

    }

    public List<NewsEntity> findAll() {
        log.info("find all news...");

        return List.of(
                generateStub("name1"),
                generateStub("name2"),
                generateStub("name3")
        );
    }

    private NewsEntity generateStub(String name) {
        return NewsEntity.builder()
                .rating(Rating.FIVE_STAR)
                .id(1L)
                .pageCount(123)
                .name(name)
                .author(AuthorEntity.builder()
                                .lastName("whatever")
                                .firstName("whatever")
                                .id(1L).build())
                .build();
    }

}
