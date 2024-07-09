package org.kps.grpcserver.author.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.AuthorServiceGrpc;
import org.kps.grpc.TaskService;
import org.kps.grpcmodel.model.Author;
import org.kps.grpcserver.author.entity.AuthorEntity;
import org.kps.grpcserver.author.mapper.AuthorResponseMapper;
import org.kps.grpcserver.author.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorCompositeService extends AuthorServiceGrpc.AuthorServiceImplBase {

    private final AuthorService authorService;
    private final AuthorResponseMapper mapper;

    @Override
    public void findAuthor(TaskService.AuthorRequest request, StreamObserver<TaskService.Author> responseObserver) {
        AuthorEntity response = authorService.findById(request.getId());
        TaskService.Author authorResponse = mapper.toAuthorResponse(response);
        responseObserver.onNext(authorResponse);
        responseObserver.onCompleted();
    }

    }
