package org.kps.grpcserver.service;

import io.grpc.stub.StreamObserver;
import org.kps.grpc.NewsServiceGrpc;
import org.kps.grpc.TaskService;

public class NewsServiceImpl extends NewsServiceGrpc.NewsServiceImplBase {
    @Override
    public void findNewsByName(TaskService.NewsSelectByNameRequest request, StreamObserver<TaskService.NewsResponse> responseObserver) {
        super.findNewsByName(request, responseObserver);
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
