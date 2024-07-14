package org.kps.grpcserver.author.mapper;

import org.kps.grpc.TaskService;
import org.kps.grpcserver.author.entity.AuthorEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorResponseMapper {

    public TaskService.Author toAuthorResponse(AuthorEntity entity) {
        return TaskService.Author.newBuilder()
                .setId(entity.getId())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .build();
    }

    public TaskService.AuthorListResponse toAuthorListResponse(List<AuthorEntity> entities) {
        return TaskService.AuthorListResponse.newBuilder()
                .addAllAuthor(entities.stream()
                        .map(this::toAuthorResponse)
                        .toList()
                )
                .build();
    }
}
