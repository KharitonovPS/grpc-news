package org.kps.grpcclient.client;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.NewsServiceGrpc;
import org.kps.grpc.TaskService;
import org.kps.grpcclient.mapper.NewsResponseMapper;
import org.kps.grpcclient.utils.FutureConverter;
import org.kps.grpcmodel.model.News;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.VirtualThreadTaskExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsClient {

    private final ManagedChannel managedChannel;
    private final NewsResponseMapper mapper;
    private NewsServiceGrpc.NewsServiceFutureStub futureStub;


    @EventListener(ApplicationReadyEvent.class)
    private void initialize() {
        futureStub = NewsServiceGrpc.newFutureStub(managedChannel);
    }

    public CompletableFuture<News> findNewsByName(String name) {
        TaskService.NewsSelectByNameRequest request = TaskService.NewsSelectByNameRequest.newBuilder()
                .setName(name)
                .build();
        var completableFuture = FutureConverter.toCompletableFuture(futureStub.withDeadline(Deadline.after(
                4000L,
                TimeUnit.MILLISECONDS
        )).findNewsByName(request));
        return completableFuture.thenApply(mapper::toNews);
    }


    public List<News> findAll() {
//        TaskService.Empty request = TaskService.Empty.newBuilder().build();
//        var findAllNewsResponse = blockingStub.findAll(request);
//        return findAllNewsResponse.getSummaryList().stream().map(mapper::toNews).toList();
        return null;
    }
}
