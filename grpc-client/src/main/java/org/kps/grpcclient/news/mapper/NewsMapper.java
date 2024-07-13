package org.kps.grpcclient.news.mapper;

import com.google.protobuf.Descriptors;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.TaskService;
import org.kps.grpcmodel.model.News;
import org.kps.grpcmodel.model.Rating;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class NewsMapper {

    public News toNews(TaskService.NewsResponse response) {
        if (Objects.isNull(response)) {
            log.warn("response is null");
            return null;
        }
        Descriptors.FieldDescriptor idField = response.getDescriptorForType().findFieldByName("id");
        return News.builder()
                .id(response.hasField(idField) ? response.getId() : 0)
                .name(response.hasName() ? response.getName() : null)
                .pageCount(response.hasPageCount() ? response.getPageCount() : 0)
                .authorId(response.hasAuthorId() ? response.getAuthorId() : 0)
                .rating(response.hasRating() ? Rating.valueOf(response.getRating().name()) : null)
                .build();

    }
}
