package org.kps.grpcserver.author.service;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.AuthorServiceGrpc;
import org.kps.grpc.TaskService;
import org.kps.grpcserver.author.entity.AuthorEntity;
import org.kps.grpcserver.author.mapper.AuthorResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorCompositeService extends AuthorServiceGrpc.AuthorServiceImplBase {

    private final AuthorService authorService;
    private final AuthorResponseMapper mapper;

    private static final float UNAVAILABLE_PERCENTAGE = 0.5F; // 50% запросов возвращают ошибку UNAVAILABLE
    private static final Random random = new Random();


    @Override
    public void findAuthor(TaskService.AuthorRequest request, StreamObserver<TaskService.Author> responseObserver) {
        //имитация ошибок/нагрузки
        if (random.nextFloat() < UNAVAILABLE_PERCENTAGE) {
            responseObserver.onError(Status.UNAVAILABLE
                                             .withDescription("AuthorService temporarily unavailable...")
                                             .asRuntimeException());
        } else {
            AuthorEntity response = authorService.findById(request.getId());
            TaskService.Author authorResponse = mapper.toAuthorResponse(response);
            responseObserver.onNext(authorResponse);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void findAuthors(TaskService.AuthorIdsRequest request, StreamObserver<TaskService.AuthorListResponse> responseObserver) {
        //имитация ошибок/нагрузки
        if (random.nextFloat() < UNAVAILABLE_PERCENTAGE) {
            responseObserver.onError(Status.UNAVAILABLE
                                             .withDescription("AuthorService temporarily unavailable...")
                                             .asRuntimeException());
        } else {
            List<AuthorEntity> authorsByIds = authorService.findAuthorsByIds(request.getIdList());
            TaskService.AuthorListResponse authorListResponse = mapper.toAuthorListResponse(authorsByIds);
            responseObserver.onNext(authorListResponse);
            responseObserver.onCompleted();
        }
    }
}
