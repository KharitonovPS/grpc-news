package org.kps.grpcclient.news.client;

import io.grpc.Deadline;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.NewsServiceGrpc;
import org.kps.grpc.TaskService;
import org.kps.grpcclient.news.mapper.NewsMapper;
import org.kps.grpcclient.utils.FutureConverter;
import org.kps.grpcmodel.model.News;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.kps.grpcclient.utils.TaskExecutor.VIRTUAL_THREAD_TASK_EXECUTOR;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsClient {

    private final NewsMapper mapper;
    private final NewsServiceGrpc.NewsServiceFutureStub futureStub;

    public CompletableFuture<News> findNewsByName(String name) {
        TaskService.NewsSelectByNameRequest request = TaskService.NewsSelectByNameRequest.newBuilder()
                .setName(name)
                .build();
        var completableFuture = FutureConverter.toCompletableFuture(futureStub.withDeadline(Deadline.after(4000L,
                                                                                                           TimeUnit.MILLISECONDS
        )).withExecutor(VIRTUAL_THREAD_TASK_EXECUTOR).findNewsByName(request));
        return completableFuture.thenApply(mapper::toNews);
    }


    public CompletableFuture<List<News>> findAll() {
        TaskService.Empty request = TaskService.Empty.newBuilder().build();

        var completableFuture = FutureConverter.toCompletableFuture(futureStub.withDeadline(Deadline.after(4000L,
                                                                                                           TimeUnit.MILLISECONDS
        )).withExecutor(VIRTUAL_THREAD_TASK_EXECUTOR).findAll(request));
        return completableFuture.thenApply(newsList -> newsList.getSummaryList().stream().map(mapper::toNews).toList());
    }
}
