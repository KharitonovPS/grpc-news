package org.kps.grpcclient.mapper;

import org.kps.grpc.TaskService;
import org.kps.grpcmodel.model.NewsDto;
import org.kps.grpcmodel.model.Rating;
import org.springframework.stereotype.Component;

@Component
public class NewsResponseMapper {

    public NewsDto toNews(TaskService.NewsResponse response) {
        return new NewsDto(
                response.getId(),
                response.getName(),
                response.getPageCount(),
                Rating.valueOf(response.getRating().name()),
                response.getAuthor().getId()
        );
    }
}
