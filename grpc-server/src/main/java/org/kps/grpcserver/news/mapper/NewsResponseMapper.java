package org.kps.grpcserver.news.mapper;

import org.kps.grpc.TaskService;
import org.kps.grpcserver.news.entity.NewsEntity;
import org.springframework.stereotype.Component;

@Component
public class NewsResponseMapper {

    // убрал автора, т.к. GQL тащит его отдельным запросом

    public TaskService.NewsResponse toNewsResponse(NewsEntity news) {
        return TaskService.NewsResponse
                .newBuilder()
                .setRating(TaskService.Rating.valueOf(news.getRating().toString()))
                .setName(news.getName())
                .setId(news.getId())
                .setAuthorId(news.getAuthorId())
                .build();
    }
}
