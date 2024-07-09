package org.kps.grpcserver.news.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.NewsServiceGrpc;
import org.kps.grpc.TaskService;
import org.kps.grpcserver.news.mapper.NewsResponseMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsCompositeService extends NewsServiceGrpc.NewsServiceImplBase {

    private final NewsService newsService;
    private final NewsResponseMapper newsResponseMapper;

    @Override
    public void findNewsByName(TaskService.NewsSelectByNameRequest request, StreamObserver<TaskService.NewsResponse> responseObserver) {
        var news = newsService.findByName(request.getName());
//        news.builder().authorId()
        var response = newsResponseMapper.toNewsResponse(news);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findAll(TaskService.Empty request, StreamObserver<TaskService.FindAllNewsResponse> responseObserver) {
        var newsList = newsService.findAll();
        var responses = newsList.stream().map(newsResponseMapper::toNewsResponse).toList();
        responseObserver.onNext(TaskService.FindAllNewsResponse.newBuilder()
                .addAllSummary(responses)
                .build());
        responseObserver.onCompleted();

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
