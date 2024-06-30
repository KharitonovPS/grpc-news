package org.kps.grpcclient.mapper;

import org.kps.grpc.TaskService;
import org.kps.grpcclient.service.News;
import org.kps.grpcclient.service.Rating;
import org.springframework.stereotype.Component;

@Component
public class NewsResponseMapper {

    public News toNews(TaskService.NewsResponse response) {
        return new News(
                response.getId(),
                response.getName(),
                response.getPageCount(),
                Rating.valueOf(response.getRating().name()),
                response.getAuthor().getId()
        );
    }
}
