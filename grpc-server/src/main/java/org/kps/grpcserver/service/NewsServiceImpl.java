package org.kps.grpcserver.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.NewsServiceGrpc;
import org.kps.grpc.TaskService;
import org.kps.grpcmodel.model.News;
import org.kps.grpcmodel.model.Rating;
import org.kps.grpcserver.repository.NewsRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsServiceImpl extends NewsServiceGrpc.NewsServiceImplBase {

    private final NewsRepository newsRepository;

    @Override
    public void findNewsByName(TaskService.NewsSelectByNameRequest request, StreamObserver<TaskService.NewsResponse> responseObserver) {
        var check = newsRepository.check(request.getName());
        var response = TaskService.NewsResponse
                .newBuilder()
                .setRating(TaskService.Rating.valueOf(check.rating().toString()))
                .setName(check.name())
                .setId(check.id())
                .setAuthor(TaskService.Author.newBuilder()
                        .setFirstName(check.authorId())
                        .setLastName(check.authorId())
                        .setId(check.authorId())
                        .build())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findAll(TaskService.Empty request, StreamObserver<TaskService.FindAllNewsResponse> responseObserver) {
        super.findAll(request, responseObserver);
    }

    @Override
    public void addNews(TaskService.NewsInputRequest request, StreamObserver<TaskService.NewsResponse> responseObserver) {
        super.addNews(request, responseObserver);
    }

    @Override
    public void updateNews(TaskService.NewsBaseRequest request, StreamObserver<TaskService.NewsResponse> responseObserver) {
        super.updateNews(request, responseObserver);
    }

    @Override
    public void deleteNewsById(TaskService.NewsSelectByNameRequest request, StreamObserver<TaskService.NewsResponse> responseObserver) {
        super.deleteNewsById(request, responseObserver);
    }
}
