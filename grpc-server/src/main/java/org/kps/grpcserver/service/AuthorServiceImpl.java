package org.kps.grpcserver.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.AuthorServiceGrpc;
import org.kps.grpc.TaskService;
import org.kps.grpcmodel.model.Author;
import org.kps.grpcserver.mapper.AuthorResponseMapper;
import org.kps.grpcserver.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl extends AuthorServiceGrpc.AuthorServiceImplBase {

    private final AuthorRepository repository;
    private final AuthorResponseMapper mapper;

    @Override
    public void findAuthor(TaskService.AuthorRequest request, StreamObserver<TaskService.Author> responseObserver) {
        Author response = repository.findById(request.getId());
        TaskService.Author authorResponse = mapper.toAuthorResponse(response);
        responseObserver.onNext(authorResponse);
        responseObserver.onCompleted();
    }

    public Author findById(Long id){
        return repository.findById(id);
    }
}
