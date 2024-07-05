package org.kps.grpcclient.mapper;

import org.kps.grpc.TaskService;
import org.kps.grpcmodel.model.Author;
import org.kps.grpcmodel.model.News;
import org.kps.grpcmodel.model.Rating;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author toAuthor(TaskService.Author response) {
        return new Author(
                response.getId(),
                response.getFirstName(),
                response.getLastName()
        );
    }
}
