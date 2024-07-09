package org.kps.grpcserver.author.mapper;

import org.kps.grpc.TaskService;
import org.kps.grpcmodel.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorResponseMapper {

    public TaskService.Author toAuthorResponse(Author dto){
        return TaskService.Author.newBuilder()
                .setId(dto.id())
                .setFirstName(dto.firstName())
                .setLastName(dto.lastName())
                .build();
    }
}
