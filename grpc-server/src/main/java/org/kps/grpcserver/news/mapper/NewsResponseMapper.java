package org.kps.grpcserver.news.mapper;

import org.kps.grpc.TaskService;
import org.kps.grpcserver.news.entity.NewsEntity;
import org.springframework.stereotype.Component;

@Component
public class NewsResponseMapper {

    public TaskService.NewsResponse toNewsResponse(NewsEntity news) {
        return TaskService.NewsResponse
                .newBuilder()
                .setRating(TaskService.Rating.valueOf(news.getRating().toString()))
                .setName(news.getName())
                .setId(news.getId().toString())
                .setAuthor(TaskService.Author.newBuilder()
                                   .setId(news.getAuthor().getId())
                                   .build())
                .build();
    }
}
