package org.kps.grpcserver.mapper;

import org.kps.grpc.TaskService;
import org.kps.grpcmodel.model.News;
import org.springframework.stereotype.Component;

@Component
public class NewsResponseMapper {

    public TaskService.NewsResponse toNewsResponse(News news) {
        return TaskService.NewsResponse
                .newBuilder()
                .setRating(TaskService.Rating.valueOf(news.rating().toString()))
                .setName(news.name())
                .setId(news.id())
                .setAuthor(TaskService.Author.newBuilder()
                        .setFirstName(news.authorId())
                        .setLastName(news.authorId())
                        .setId(news.authorId())
                        .build())
                .build();
    }
}
