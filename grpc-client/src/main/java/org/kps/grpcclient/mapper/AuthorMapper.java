package org.kps.grpcclient.mapper;

import org.kps.grpc.TaskService;
import org.kps.grpcmodel.model.AuthorDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorDto toAuthor(TaskService.Author author) {
        return new AuthorDto(author.getId(), author.getFirstName(), author.getLastName());
    }
}
