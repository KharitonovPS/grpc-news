package org.kps.grpcserver.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.NewsServiceGrpc;
import org.kps.grpc.TaskService;
import org.kps.grpcserver.mapper.NewsResponseMapper;
import org.kps.grpcserver.repository.NewsRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsServiceImpl extends NewsServiceGrpc.NewsServiceImplBase {

    private final NewsRepository newsRepository;
    private final NewsResponseMapper newsResponseMapper;

    @Override
    public void findNewsByName(TaskService.NewsSelectByNameRequest request, StreamObserver<TaskService.NewsResponse> responseObserver) {
        var news = newsRepository.findByName(request.getName());
        var response = newsResponseMapper.toNewsResponse(news);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findAll(TaskService.Empty request, StreamObserver<TaskService.FindAllNewsResponse> responseObserver) {
        var newsList = newsRepository.findAll();
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
