package org.kps.grpcserver.mapper;

import org.kps.grpc.TaskService;
import org.kps.grpcmodel.model.NewsDto;
import org.springframework.stereotype.Component;

@Component
public class NewsResponseMapper {

    public TaskService.NewsResponse toNewsResponse(NewsDto news) {
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
